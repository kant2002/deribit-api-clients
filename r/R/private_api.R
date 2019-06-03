# Deribit API
#
# #Overview  Deribit provides three different interfaces to access the API:  * [JSON-RPC over Websocket](#json-rpc) * [JSON-RPC over HTTP](#json-rpc) * [FIX](#fix-api) (Financial Information eXchange)  With the API Console you can use and test the JSON-RPC API, both via HTTP and  via Websocket. To visit the API console, go to __Account > API tab >  API Console tab.__   ##Naming Deribit tradeable assets or instruments use the following system of naming:  |Kind|Examples|Template|Comments| |----|--------|--------|--------| |Future|<code>BTC-25MAR16</code>, <code>BTC-5AUG16</code>|<code>BTC-DMMMYY</code>|<code>BTC</code> is currency, <code>DMMMYY</code> is expiration date, <code>D</code> stands for day of month (1 or 2 digits), <code>MMM</code> - month (3 first letters in English), <code>YY</code> stands for year.| |Perpetual|<code>BTC-PERPETUAL</code>                        ||Perpetual contract for currency <code>BTC</code>.| |Option|<code>BTC-25MAR16-420-C</code>, <code>BTC-5AUG16-580-P</code>|<code>BTC-DMMMYY-STRIKE-K</code>|<code>STRIKE</code> is option strike price in USD. Template <code>K</code> is option kind: <code>C</code> for call options or <code>P</code> for put options.|   # JSON-RPC JSON-RPC is a light-weight remote procedure call (RPC) protocol. The  [JSON-RPC specification](https://www.jsonrpc.org/specification) defines the data structures that are used for the messages that are exchanged between client and server, as well as the rules around their processing. JSON-RPC uses JSON (RFC 4627) as data format.  JSON-RPC is transport agnostic: it does not specify which transport mechanism must be used. The Deribit API supports both Websocket (preferred) and HTTP (with limitations: subscriptions are not supported over HTTP).  ## Request messages > An example of a request message:  ```json {     \"jsonrpc\": \"2.0\",     \"id\": 8066,     \"method\": \"public/ticker\",     \"params\": {         \"instrument\": \"BTC-24AUG18-6500-P\"     } } ```  According to the JSON-RPC sepcification the requests must be JSON objects with the following fields.  |Name|Type|Description| |----|----|-----------| |jsonrpc|string|The version of the JSON-RPC spec: \"2.0\"| |id|integer or string|An identifier of the request. If it is included, then the response will contain the same identifier| |method|string|The method to be invoked| |params|object|The parameters values for the method. The field names must match with the expected parameter names. The parameters that are expected are described in the documentation for the methods, below.|  <aside class=\"warning\"> The JSON-RPC specification describes two features that are currently not supported by the API:  <ul> <li>Specification of parameter values by position</li> <li>Batch requests</li> </ul>  </aside>   ## Response messages > An example of a response message:  ```json {     \"jsonrpc\": \"2.0\",     \"id\": 5239,     \"testnet\": false,     \"result\": [         {             \"currency\": \"BTC\",             \"currencyLong\": \"Bitcoin\",             \"minConfirmation\": 2,             \"txFee\": 0.0006,             \"isActive\": true,             \"coinType\": \"BITCOIN\",             \"baseAddress\": null         }     ],     \"usIn\": 1535043730126248,     \"usOut\": 1535043730126250,     \"usDiff\": 2 } ```  The JSON-RPC API always responds with a JSON object with the following fields.   |Name|Type|Description| |----|----|-----------| |id|integer|This is the same id that was sent in the request.| |result|any|If successful, the result of the API call. The format for the result is described with each method.| |error|error object|Only present if there was an error invoking the method. The error object is described below.| |testnet|boolean|Indicates whether the API in use is actually the test API.  <code>false</code> for production server, <code>true</code> for test server.| |usIn|integer|The timestamp when the requests was received (microseconds since the Unix epoch)| |usOut|integer|The timestamp when the response was sent (microseconds since the Unix epoch)| |usDiff|integer|The number of microseconds that was spent handling the request|  <aside class=\"notice\"> The fields <code>testnet</code>, <code>usIn</code>, <code>usOut</code> and <code>usDiff</code> are not part of the JSON-RPC standard.  <p>In order not to clutter the examples they will generally be omitted from the example code.</p> </aside>  > An example of a response with an error:  ```json {     \"jsonrpc\": \"2.0\",     \"id\": 8163,     \"error\": {         \"code\": 11050,         \"message\": \"bad_request\"     },     \"testnet\": false,     \"usIn\": 1535037392434763,     \"usOut\": 1535037392448119,     \"usDiff\": 13356 } ``` In case of an error the response message will contain the error field, with as value an object with the following with the following fields:  |Name|Type|Description |----|----|-----------| |code|integer|A number that indicates the kind of error.| |message|string|A short description that indicates the kind of error.| |data|any|Additional data about the error. This field may be omitted.|  ## Notifications  > An example of a notification:  ```json {     \"jsonrpc\": \"2.0\",     \"method\": \"subscription\",     \"params\": {         \"channel\": \"deribit_price_index.btc_usd\",         \"data\": {             \"timestamp\": 1535098298227,             \"price\": 6521.17,             \"index_name\": \"btc_usd\"         }     } } ```  API users can subscribe to certain types of notifications. This means that they will receive JSON-RPC notification-messages from the server when certain events occur, such as changes to the index price or changes to the order book for a certain instrument.   The API methods [public/subscribe](#public-subscribe) and [private/subscribe](#private-subscribe) are used to set up a subscription. Since HTTP does not support the sending of messages from server to client, these methods are only availble when using the Websocket transport mechanism.  At the moment of subscription a \"channel\" must be specified. The channel determines the type of events that will be received.  See [Subscriptions](#subscriptions) for more details about the channels.  In accordance with the JSON-RPC specification, the format of a notification  is that of a request message without an <code>id</code> field. The value of the <code>method</code> field will always be <code>\"subscription\"</code>. The <code>params</code> field will always be an object with 2 members: <code>channel</code> and <code>data</code>. The value of the <code>channel</code> member is the name of the channel (a string). The value of the <code>data</code> member is an object that contains data  that is specific for the channel.   ## Authentication  > An example of a JSON request with token:  ```json {     \"id\": 5647,     \"method\": \"private/get_subaccounts\",     \"params\": {         \"access_token\": \"67SVutDoVZSzkUStHSuk51WntMNBJ5mh5DYZhwzpiqDF\"     } } ```  The API consists of `public` and `private` methods. The public methods do not require authentication. The private methods use OAuth 2.0 authentication. This means that a valid OAuth access token must be included in the request, which can get achived by calling method [public/auth](#public-auth).  When the token was assigned to the user, it should be passed along, with other request parameters, back to the server:  |Connection type|Access token placement |----|-----------| |**Websocket**|Inside request JSON parameters, as an `access_token` field| |**HTTP (REST)**|Header `Authorization: bearer ```Token``` ` value|  ### Additional authorization method - basic user credentials  <span style=\"color:red\"><b> ! Not recommended - however, it could be useful for quick testing API</b></span></br>  Every `private` method could be accessed by providing, inside HTTP `Authorization: Basic XXX` header, values with user `ClientId` and assigned `ClientSecret` (both values can be found on the API page on the Deribit website) encoded with `Base64`:  <code>Authorization: Basic BASE64(`ClientId` + `:` + `ClientSecret`)</code>   ### Additional authorization method - Deribit signature credentials  The Derbit service provides dedicated authorization method, which harness user generated signature to increase security level for passing request data. Generated value is passed inside `Authorization` header, coded as:  <code>Authorization: deri-hmac-sha256 id=```ClientId```,ts=```Timestamp```,sig=```Signature```,nonce=```Nonce```</code>  where:  |Deribit credential|Description |----|-----------| |*ClientId*|Can be found on the API page on the Deribit website| |*Timestamp*|Time when the request was generated - given as **miliseconds**. It's valid for **60 seconds** since generation, after that time any request with an old timestamp will be rejected.| |*Signature*|Value for signature calculated as described below | |*Nonce*|Single usage, user generated initialization vector for the server token|  The signature is generated by the following formula:  <code> Signature = HEX_STRING( HMAC-SHA256( ClientSecret, StringToSign ) );</code></br>  <code> StringToSign =  Timestamp + \"\\n\" + Nonce + \"\\n\" + RequestData;</code></br>  <code> RequestData =  UPPERCASE(HTTP_METHOD())  + \"\\n\" + URI() + \"\\n\" + RequestBody + \"\\n\";</code></br>   e.g. (using shell with ```openssl``` tool):  <code>&nbsp;&nbsp;&nbsp;&nbsp;ClientId=AAAAAAAAAAA</code></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;ClientSecret=ABCD</code></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;Timestamp=$( date +%s000 )</code></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;Nonce=$( cat /dev/urandom | tr -dc 'a-z0-9' | head -c8 )</code></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;URI=\"/api/v2/private/get_account_summary?currency=BTC\"</code></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;HttpMethod=GET</code></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;Body=\"\"</code></br></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;Signature=$( echo -ne \"${Timestamp}\\n${Nonce}\\n${HttpMethod}\\n${URI}\\n${Body}\\n\" | openssl sha256 -r -hmac \"$ClientSecret\" | cut -f1 -d' ' )</code></br></br> <code>&nbsp;&nbsp;&nbsp;&nbsp;echo $Signature</code></br></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;shell output> ea40d5e5e4fae235ab22b61da98121fbf4acdc06db03d632e23c66bcccb90d2c  (**WARNING**: Exact value depends on current timestamp and client credentials</code></br></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;curl -s -X ${HttpMethod} -H \"Authorization: deri-hmac-sha256 id=${ClientId},ts=${Timestamp},nonce=${Nonce},sig=${Signature}\" \"https://www.deribit.com${URI}\"</code></br></br>    ### Additional authorization method - signature credentials (WebSocket API)  When connecting through Websocket, user can request for authorization using ```client_credential``` method, which requires providing following parameters (as a part of JSON request):  |JSON parameter|Description |----|-----------| |*grant_type*|Must be **client_signature**| |*client_id*|Can be found on the API page on the Deribit website| |*timestamp*|Time when the request was generated - given as **miliseconds**. It's valid for **60 seconds** since generation, after that time any request with an old timestamp will be rejected.| |*signature*|Value for signature calculated as described below | |*nonce*|Single usage, user generated initialization vector for the server token| |*data*|**Optional** field, which contains any user specific value|  The signature is generated by the following formula:  <code> StringToSign =  Timestamp + \"\\n\" + Nonce + \"\\n\" + Data;</code></br>  <code> Signature = HEX_STRING( HMAC-SHA256( ClientSecret, StringToSign ) );</code></br>   e.g. (using shell with ```openssl``` tool):  <code>&nbsp;&nbsp;&nbsp;&nbsp;ClientId=AAAAAAAAAAA</code></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;ClientSecret=ABCD</code></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;Timestamp=$( date +%s000 ) # e.g. 1554883365000 </code></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;Nonce=$( cat /dev/urandom | tr -dc 'a-z0-9' | head -c8 ) # e.g. fdbmmz79 </code></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;Data=\"\"</code></br></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;Signature=$( echo -ne \"${Timestamp}\\n${Nonce}\\n${Data}\\n\" | openssl sha256 -r -hmac \"$ClientSecret\" | cut -f1 -d' ' )</code></br></br> <code>&nbsp;&nbsp;&nbsp;&nbsp;echo $Signature</code></br></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;shell output> e20c9cd5639d41f8bbc88f4d699c4baf94a4f0ee320e9a116b72743c449eb994  (**WARNING**: Exact value depends on current timestamp and client credentials</code></br></br>   You can also check the signature value using some online tools like, e.g: [https://codebeautify.org/hmac-generator](https://codebeautify.org/hmac-generator) (but don't forget about adding *newline* after each part of the hashed text and remember that you **should use** it only with your **test credentials**).   Here's a sample JSON request created using the values from the example above:  <code> {                            </br> &nbsp;&nbsp;\"jsonrpc\" : \"2.0\",         </br> &nbsp;&nbsp;\"id\" : 9929,               </br> &nbsp;&nbsp;\"method\" : \"public/auth\",  </br> &nbsp;&nbsp;\"params\" :                 </br> &nbsp;&nbsp;{                        </br> &nbsp;&nbsp;&nbsp;&nbsp;\"grant_type\" : \"client_signature\",   </br> &nbsp;&nbsp;&nbsp;&nbsp;\"client_id\" : \"AAAAAAAAAAA\",         </br> &nbsp;&nbsp;&nbsp;&nbsp;\"timestamp\": \"1554883365000\",        </br> &nbsp;&nbsp;&nbsp;&nbsp;\"nonce\": \"fdbmmz79\",                 </br> &nbsp;&nbsp;&nbsp;&nbsp;\"data\": \"\",                          </br> &nbsp;&nbsp;&nbsp;&nbsp;\"signature\" : \"e20c9cd5639d41f8bbc88f4d699c4baf94a4f0ee320e9a116b72743c449eb994\"  </br> &nbsp;&nbsp;}                        </br> }                            </br> </code>   ### Access scope  When asking for `access token` user can provide the required access level (called `scope`) which defines what type of functionality he/she wants to use, and whether requests are only going to check for some data or also to update them.  Scopes are required and checked for `private` methods, so if you plan to use only `public` information you can stay with values assigned by default.  |Scope|Description |----|-----------| |*account:read*|Access to **account** methods - read only data| |*account:read_write*|Access to **account** methods - allows to manage account settings, add subaccounts, etc.| |*trade:read*|Access to **trade** methods - read only data| |*trade:read_write*|Access to **trade** methods - required to create and modify orders| |*wallet:read*|Access to **wallet** methods - read only data| |*wallet:read_write*|Access to **wallet** methods - allows to withdraw, generate new deposit address, etc.| |*wallet:none*, *account:none*, *trade:none*|Blocked access to specified functionality|    <span style=\"color:red\">**NOTICE:**</span> Depending on choosing an authentication method (```grant type```) some scopes could be narrowed by the server. e.g. when ```grant_type = client_credentials``` and ```scope = wallet:read_write``` it's modified by the server as ```scope = wallet:read```\"   ## JSON-RPC over websocket Websocket is the prefered transport mechanism for the JSON-RPC API, because it is faster and because it can support [subscriptions](#subscriptions) and [cancel on disconnect](#private-enable_cancel_on_disconnect). The code examples that can be found next to each of the methods show how websockets can be used from Python or Javascript/node.js.  ## JSON-RPC over HTTP Besides websockets it is also possible to use the API via HTTP. The code examples for 'shell' show how this can be done using curl. Note that subscriptions and cancel on disconnect are not supported via HTTP.  #Methods 
#
# The version of the OpenAPI document: 2.0.0
# 
# Generated by: https://openapi-generator.tech

#' @title Private operations
#' @description openapi.Private
#'
#' @field path Stores url path of the request.
#' @field apiClient Handles the client-server communication.
#'
#' @importFrom R6 R6Class
#'
#' @section Methods:
#' \describe{
#'
#' PrivateAddToAddressBookGet Adds new entry to address book of given type
#'
#'
#' PrivateBuyGet Places a buy order for an instrument.
#'
#'
#' PrivateCancelAllByCurrencyGet Cancels all orders by currency, optionally filtered by instrument kind and/or order type.
#'
#'
#' PrivateCancelAllByInstrumentGet Cancels all orders by instrument, optionally filtered by order type.
#'
#'
#' PrivateCancelAllGet This method cancels all users orders and stop orders within all currencies and instrument kinds.
#'
#'
#' PrivateCancelGet Cancel an order, specified by order id
#'
#'
#' PrivateCancelTransferByIdGet Cancel transfer
#'
#'
#' PrivateCancelWithdrawalGet Cancels withdrawal request
#'
#'
#' PrivateChangeSubaccountNameGet Change the user name for a subaccount
#'
#'
#' PrivateClosePositionGet Makes closing position reduce only order .
#'
#'
#' PrivateCreateDepositAddressGet Creates deposit address in currency
#'
#'
#' PrivateCreateSubaccountGet Create a new subaccount
#'
#'
#' PrivateDisableTfaForSubaccountGet Disable two factor authentication for a subaccount.
#'
#'
#' PrivateDisableTfaWithRecoveryCodeGet Disables TFA with one time recovery code
#'
#'
#' PrivateEditGet Change price, amount and/or other properties of an order.
#'
#'
#' PrivateGetAccountSummaryGet Retrieves user account summary.
#'
#'
#' PrivateGetAddressBookGet Retrieves address book of given type
#'
#'
#' PrivateGetCurrentDepositAddressGet Retrieve deposit address for currency
#'
#'
#' PrivateGetDepositsGet Retrieve the latest users deposits
#'
#'
#' PrivateGetEmailLanguageGet Retrieves the language to be used for emails.
#'
#'
#' PrivateGetMarginsGet Get margins for given instrument, amount and price.
#'
#'
#' PrivateGetNewAnnouncementsGet Retrieves announcements that have not been marked read by the user.
#'
#'
#' PrivateGetOpenOrdersByCurrencyGet Retrieves list of user&#39;s open orders.
#'
#'
#' PrivateGetOpenOrdersByInstrumentGet Retrieves list of user&#39;s open orders within given Instrument.
#'
#'
#' PrivateGetOrderHistoryByCurrencyGet Retrieves history of orders that have been partially or fully filled.
#'
#'
#' PrivateGetOrderHistoryByInstrumentGet Retrieves history of orders that have been partially or fully filled.
#'
#'
#' PrivateGetOrderMarginByIdsGet Retrieves initial margins of given orders
#'
#'
#' PrivateGetOrderStateGet Retrieve the current state of an order.
#'
#'
#' PrivateGetPositionGet Retrieve user position.
#'
#'
#' PrivateGetPositionsGet Retrieve user positions.
#'
#'
#' PrivateGetSettlementHistoryByCurrencyGet Retrieves settlement, delivery and bankruptcy events that have affected your account.
#'
#'
#' PrivateGetSettlementHistoryByInstrumentGet Retrieves public settlement, delivery and bankruptcy events filtered by instrument name
#'
#'
#' PrivateGetSubaccountsGet Get information about subaccounts
#'
#'
#' PrivateGetTransfersGet Adds new entry to address book of given type
#'
#'
#' PrivateGetUserTradesByCurrencyAndTimeGet Retrieve the latest user trades that have occurred for instruments in a specific currency symbol and within given time range.
#'
#'
#' PrivateGetUserTradesByCurrencyGet Retrieve the latest user trades that have occurred for instruments in a specific currency symbol.
#'
#'
#' PrivateGetUserTradesByInstrumentAndTimeGet Retrieve the latest user trades that have occurred for a specific instrument and within given time range.
#'
#'
#' PrivateGetUserTradesByInstrumentGet Retrieve the latest user trades that have occurred for a specific instrument.
#'
#'
#' PrivateGetUserTradesByOrderGet Retrieve the list of user trades that was created for given order
#'
#'
#' PrivateGetWithdrawalsGet Retrieve the latest users withdrawals
#'
#'
#' PrivateRemoveFromAddressBookGet Adds new entry to address book of given type
#'
#'
#' PrivateSellGet Places a sell order for an instrument.
#'
#'
#' PrivateSetAnnouncementAsReadGet Marks an announcement as read, so it will not be shown in &#x60;get_new_announcements&#x60;.
#'
#'
#' PrivateSetEmailForSubaccountGet Assign an email address to a subaccount. User will receive an email with confirmation link.
#'
#'
#' PrivateSetEmailLanguageGet Changes the language to be used for emails.
#'
#'
#' PrivateSetPasswordForSubaccountGet Set the password for the subaccount
#'
#'
#' PrivateSubmitTransferToSubaccountGet Transfer funds to a subaccount.
#'
#'
#' PrivateSubmitTransferToUserGet Transfer funds to a another user.
#'
#'
#' PrivateToggleDepositAddressCreationGet Enable or disable deposit address creation
#'
#'
#' PrivateToggleNotificationsFromSubaccountGet Enable or disable sending of notifications for the subaccount.
#'
#'
#' PrivateToggleSubaccountLoginGet Enable or disable login for a subaccount. If login is disabled and a session for the subaccount exists, this session will be terminated.
#'
#'
#' PrivateWithdrawGet Creates a new withdrawal request
#'
#' }
#'
#' @importFrom caTools base64encode
#' @export
PrivateApi <- R6::R6Class(
  'PrivateApi',
  public = list(
    apiClient = NULL,
    initialize = function(apiClient){
      if (!missing(apiClient)) {
        self$apiClient <- apiClient
      }
      else {
        self$apiClient <- ApiClient$new()
      }
    },
    PrivateAddToAddressBookGet = function(currency, type, address, name, tfa=NULL, ...){
      args <- list(...)
      queryParams <- list()
      headerParams <- c()

      if (missing(`currency`)) {
        stop("Missing required parameter `currency`.")
      }

      if (missing(`type`)) {
        stop("Missing required parameter `type`.")
      }

      if (missing(`address`)) {
        stop("Missing required parameter `address`.")
      }

      if (missing(`name`)) {
        stop("Missing required parameter `name`.")
      }

      queryParams['currency'] <- currency

      queryParams['type'] <- type

      queryParams['address'] <- address

      queryParams['name'] <- name

      queryParams['tfa'] <- tfa

      urlPath <- "/private/add_to_address_book"

      resp <- self$apiClient$CallApi(url = paste0(self$apiClient$basePath, urlPath),
                                 method = "GET",
                                 queryParams = queryParams,
                                 headerParams = headerParams,
                                 body = body,
                                 ...)

      if (httr::status_code(resp) >= 200 && httr::status_code(resp) <= 299) {
        object$new()$fromJSONString(httr::content(resp, "text", encoding = "UTF-8"))
      } else if (httr::status_code(resp) >= 400 && httr::status_code(resp) <= 499) {
        ApiResponse$new("API client error", resp)
      } else if (httr::status_code(resp) >= 500 && httr::status_code(resp) <= 599) {
        ApiResponse$new("API server error", resp)
      }

    },
    PrivateBuyGet = function(instrument.name, amount, type=NULL, label=NULL, price=NULL, time.in.force='good_til_cancelled', max.show=1, post.only=TRUE, reduce.only=FALSE, stop.price=NULL, trigger=NULL, advanced=NULL, ...){
      args <- list(...)
      queryParams <- list()
      headerParams <- c()

      if (missing(`instrument.name`)) {
        stop("Missing required parameter `instrument.name`.")
      }

      if (missing(`amount`)) {
        stop("Missing required parameter `amount`.")
      }

      queryParams['instrument_name'] <- instrument.name

      queryParams['amount'] <- amount

      queryParams['type'] <- type

      queryParams['label'] <- label

      queryParams['price'] <- price

      queryParams['time_in_force'] <- time.in.force

      queryParams['max_show'] <- max.show

      queryParams['post_only'] <- post.only

      queryParams['reduce_only'] <- reduce.only

      queryParams['stop_price'] <- stop.price

      queryParams['trigger'] <- trigger

      queryParams['advanced'] <- advanced

      urlPath <- "/private/buy"

      resp <- self$apiClient$CallApi(url = paste0(self$apiClient$basePath, urlPath),
                                 method = "GET",
                                 queryParams = queryParams,
                                 headerParams = headerParams,
                                 body = body,
                                 ...)

      if (httr::status_code(resp) >= 200 && httr::status_code(resp) <= 299) {
        object$new()$fromJSONString(httr::content(resp, "text", encoding = "UTF-8"))
      } else if (httr::status_code(resp) >= 400 && httr::status_code(resp) <= 499) {
        ApiResponse$new("API client error", resp)
      } else if (httr::status_code(resp) >= 500 && httr::status_code(resp) <= 599) {
        ApiResponse$new("API server error", resp)
      }

    },
    PrivateCancelAllByCurrencyGet = function(currency, kind=NULL, type=NULL, ...){
      args <- list(...)
      queryParams <- list()
      headerParams <- c()

      if (missing(`currency`)) {
        stop("Missing required parameter `currency`.")
      }

      queryParams['currency'] <- currency

      queryParams['kind'] <- kind

      queryParams['type'] <- type

      urlPath <- "/private/cancel_all_by_currency"

      resp <- self$apiClient$CallApi(url = paste0(self$apiClient$basePath, urlPath),
                                 method = "GET",
                                 queryParams = queryParams,
                                 headerParams = headerParams,
                                 body = body,
                                 ...)

      if (httr::status_code(resp) >= 200 && httr::status_code(resp) <= 299) {
        object$new()$fromJSONString(httr::content(resp, "text", encoding = "UTF-8"))
      } else if (httr::status_code(resp) >= 400 && httr::status_code(resp) <= 499) {
        ApiResponse$new("API client error", resp)
      } else if (httr::status_code(resp) >= 500 && httr::status_code(resp) <= 599) {
        ApiResponse$new("API server error", resp)
      }

    },
    PrivateCancelAllByInstrumentGet = function(instrument.name, type=NULL, ...){
      args <- list(...)
      queryParams <- list()
      headerParams <- c()

      if (missing(`instrument.name`)) {
        stop("Missing required parameter `instrument.name`.")
      }

      queryParams['instrument_name'] <- instrument.name

      queryParams['type'] <- type

      urlPath <- "/private/cancel_all_by_instrument"

      resp <- self$apiClient$CallApi(url = paste0(self$apiClient$basePath, urlPath),
                                 method = "GET",
                                 queryParams = queryParams,
                                 headerParams = headerParams,
                                 body = body,
                                 ...)

      if (httr::status_code(resp) >= 200 && httr::status_code(resp) <= 299) {
        object$new()$fromJSONString(httr::content(resp, "text", encoding = "UTF-8"))
      } else if (httr::status_code(resp) >= 400 && httr::status_code(resp) <= 499) {
        ApiResponse$new("API client error", resp)
      } else if (httr::status_code(resp) >= 500 && httr::status_code(resp) <= 599) {
        ApiResponse$new("API server error", resp)
      }

    },
    PrivateCancelAllGet = function(...){
      args <- list(...)
      queryParams <- list()
      headerParams <- c()

      urlPath <- "/private/cancel_all"

      resp <- self$apiClient$CallApi(url = paste0(self$apiClient$basePath, urlPath),
                                 method = "GET",
                                 queryParams = queryParams,
                                 headerParams = headerParams,
                                 body = body,
                                 ...)

      if (httr::status_code(resp) >= 200 && httr::status_code(resp) <= 299) {
        object$new()$fromJSONString(httr::content(resp, "text", encoding = "UTF-8"))
      } else if (httr::status_code(resp) >= 400 && httr::status_code(resp) <= 499) {
        ApiResponse$new("API client error", resp)
      } else if (httr::status_code(resp) >= 500 && httr::status_code(resp) <= 599) {
        ApiResponse$new("API server error", resp)
      }

    },
    PrivateCancelGet = function(order.id, ...){
      args <- list(...)
      queryParams <- list()
      headerParams <- c()

      if (missing(`order.id`)) {
        stop("Missing required parameter `order.id`.")
      }

      queryParams['order_id'] <- order.id

      urlPath <- "/private/cancel"

      resp <- self$apiClient$CallApi(url = paste0(self$apiClient$basePath, urlPath),
                                 method = "GET",
                                 queryParams = queryParams,
                                 headerParams = headerParams,
                                 body = body,
                                 ...)

      if (httr::status_code(resp) >= 200 && httr::status_code(resp) <= 299) {
        object$new()$fromJSONString(httr::content(resp, "text", encoding = "UTF-8"))
      } else if (httr::status_code(resp) >= 400 && httr::status_code(resp) <= 499) {
        ApiResponse$new("API client error", resp)
      } else if (httr::status_code(resp) >= 500 && httr::status_code(resp) <= 599) {
        ApiResponse$new("API server error", resp)
      }

    },
    PrivateCancelTransferByIdGet = function(currency, id, tfa=NULL, ...){
      args <- list(...)
      queryParams <- list()
      headerParams <- c()

      if (missing(`currency`)) {
        stop("Missing required parameter `currency`.")
      }

      if (missing(`id`)) {
        stop("Missing required parameter `id`.")
      }

      queryParams['currency'] <- currency

      queryParams['id'] <- id

      queryParams['tfa'] <- tfa

      urlPath <- "/private/cancel_transfer_by_id"

      resp <- self$apiClient$CallApi(url = paste0(self$apiClient$basePath, urlPath),
                                 method = "GET",
                                 queryParams = queryParams,
                                 headerParams = headerParams,
                                 body = body,
                                 ...)

      if (httr::status_code(resp) >= 200 && httr::status_code(resp) <= 299) {
        object$new()$fromJSONString(httr::content(resp, "text", encoding = "UTF-8"))
      } else if (httr::status_code(resp) >= 400 && httr::status_code(resp) <= 499) {
        ApiResponse$new("API client error", resp)
      } else if (httr::status_code(resp) >= 500 && httr::status_code(resp) <= 599) {
        ApiResponse$new("API server error", resp)
      }

    },
    PrivateCancelWithdrawalGet = function(currency, id, ...){
      args <- list(...)
      queryParams <- list()
      headerParams <- c()

      if (missing(`currency`)) {
        stop("Missing required parameter `currency`.")
      }

      if (missing(`id`)) {
        stop("Missing required parameter `id`.")
      }

      queryParams['currency'] <- currency

      queryParams['id'] <- id

      urlPath <- "/private/cancel_withdrawal"

      resp <- self$apiClient$CallApi(url = paste0(self$apiClient$basePath, urlPath),
                                 method = "GET",
                                 queryParams = queryParams,
                                 headerParams = headerParams,
                                 body = body,
                                 ...)

      if (httr::status_code(resp) >= 200 && httr::status_code(resp) <= 299) {
        object$new()$fromJSONString(httr::content(resp, "text", encoding = "UTF-8"))
      } else if (httr::status_code(resp) >= 400 && httr::status_code(resp) <= 499) {
        ApiResponse$new("API client error", resp)
      } else if (httr::status_code(resp) >= 500 && httr::status_code(resp) <= 599) {
        ApiResponse$new("API server error", resp)
      }

    },
    PrivateChangeSubaccountNameGet = function(sid, name, ...){
      args <- list(...)
      queryParams <- list()
      headerParams <- c()

      if (missing(`sid`)) {
        stop("Missing required parameter `sid`.")
      }

      if (missing(`name`)) {
        stop("Missing required parameter `name`.")
      }

      queryParams['sid'] <- sid

      queryParams['name'] <- name

      urlPath <- "/private/change_subaccount_name"

      resp <- self$apiClient$CallApi(url = paste0(self$apiClient$basePath, urlPath),
                                 method = "GET",
                                 queryParams = queryParams,
                                 headerParams = headerParams,
                                 body = body,
                                 ...)

      if (httr::status_code(resp) >= 200 && httr::status_code(resp) <= 299) {
        object$new()$fromJSONString(httr::content(resp, "text", encoding = "UTF-8"))
      } else if (httr::status_code(resp) >= 400 && httr::status_code(resp) <= 499) {
        ApiResponse$new("API client error", resp)
      } else if (httr::status_code(resp) >= 500 && httr::status_code(resp) <= 599) {
        ApiResponse$new("API server error", resp)
      }

    },
    PrivateClosePositionGet = function(instrument.name, type, price=NULL, ...){
      args <- list(...)
      queryParams <- list()
      headerParams <- c()

      if (missing(`instrument.name`)) {
        stop("Missing required parameter `instrument.name`.")
      }

      if (missing(`type`)) {
        stop("Missing required parameter `type`.")
      }

      queryParams['instrument_name'] <- instrument.name

      queryParams['type'] <- type

      queryParams['price'] <- price

      urlPath <- "/private/close_position"

      resp <- self$apiClient$CallApi(url = paste0(self$apiClient$basePath, urlPath),
                                 method = "GET",
                                 queryParams = queryParams,
                                 headerParams = headerParams,
                                 body = body,
                                 ...)

      if (httr::status_code(resp) >= 200 && httr::status_code(resp) <= 299) {
        object$new()$fromJSONString(httr::content(resp, "text", encoding = "UTF-8"))
      } else if (httr::status_code(resp) >= 400 && httr::status_code(resp) <= 499) {
        ApiResponse$new("API client error", resp)
      } else if (httr::status_code(resp) >= 500 && httr::status_code(resp) <= 599) {
        ApiResponse$new("API server error", resp)
      }

    },
    PrivateCreateDepositAddressGet = function(currency, ...){
      args <- list(...)
      queryParams <- list()
      headerParams <- c()

      if (missing(`currency`)) {
        stop("Missing required parameter `currency`.")
      }

      queryParams['currency'] <- currency

      urlPath <- "/private/create_deposit_address"

      resp <- self$apiClient$CallApi(url = paste0(self$apiClient$basePath, urlPath),
                                 method = "GET",
                                 queryParams = queryParams,
                                 headerParams = headerParams,
                                 body = body,
                                 ...)

      if (httr::status_code(resp) >= 200 && httr::status_code(resp) <= 299) {
        object$new()$fromJSONString(httr::content(resp, "text", encoding = "UTF-8"))
      } else if (httr::status_code(resp) >= 400 && httr::status_code(resp) <= 499) {
        ApiResponse$new("API client error", resp)
      } else if (httr::status_code(resp) >= 500 && httr::status_code(resp) <= 599) {
        ApiResponse$new("API server error", resp)
      }

    },
    PrivateCreateSubaccountGet = function(...){
      args <- list(...)
      queryParams <- list()
      headerParams <- c()

      urlPath <- "/private/create_subaccount"

      resp <- self$apiClient$CallApi(url = paste0(self$apiClient$basePath, urlPath),
                                 method = "GET",
                                 queryParams = queryParams,
                                 headerParams = headerParams,
                                 body = body,
                                 ...)

      if (httr::status_code(resp) >= 200 && httr::status_code(resp) <= 299) {
        object$new()$fromJSONString(httr::content(resp, "text", encoding = "UTF-8"))
      } else if (httr::status_code(resp) >= 400 && httr::status_code(resp) <= 499) {
        ApiResponse$new("API client error", resp)
      } else if (httr::status_code(resp) >= 500 && httr::status_code(resp) <= 599) {
        ApiResponse$new("API server error", resp)
      }

    },
    PrivateDisableTfaForSubaccountGet = function(sid, ...){
      args <- list(...)
      queryParams <- list()
      headerParams <- c()

      if (missing(`sid`)) {
        stop("Missing required parameter `sid`.")
      }

      queryParams['sid'] <- sid

      urlPath <- "/private/disable_tfa_for_subaccount"

      resp <- self$apiClient$CallApi(url = paste0(self$apiClient$basePath, urlPath),
                                 method = "GET",
                                 queryParams = queryParams,
                                 headerParams = headerParams,
                                 body = body,
                                 ...)

      if (httr::status_code(resp) >= 200 && httr::status_code(resp) <= 299) {
        object$new()$fromJSONString(httr::content(resp, "text", encoding = "UTF-8"))
      } else if (httr::status_code(resp) >= 400 && httr::status_code(resp) <= 499) {
        ApiResponse$new("API client error", resp)
      } else if (httr::status_code(resp) >= 500 && httr::status_code(resp) <= 599) {
        ApiResponse$new("API server error", resp)
      }

    },
    PrivateDisableTfaWithRecoveryCodeGet = function(password, code, ...){
      args <- list(...)
      queryParams <- list()
      headerParams <- c()

      if (missing(`password`)) {
        stop("Missing required parameter `password`.")
      }

      if (missing(`code`)) {
        stop("Missing required parameter `code`.")
      }

      queryParams['password'] <- password

      queryParams['code'] <- code

      urlPath <- "/private/disable_tfa_with_recovery_code"

      resp <- self$apiClient$CallApi(url = paste0(self$apiClient$basePath, urlPath),
                                 method = "GET",
                                 queryParams = queryParams,
                                 headerParams = headerParams,
                                 body = body,
                                 ...)

      if (httr::status_code(resp) >= 200 && httr::status_code(resp) <= 299) {
        object$new()$fromJSONString(httr::content(resp, "text", encoding = "UTF-8"))
      } else if (httr::status_code(resp) >= 400 && httr::status_code(resp) <= 499) {
        ApiResponse$new("API client error", resp)
      } else if (httr::status_code(resp) >= 500 && httr::status_code(resp) <= 599) {
        ApiResponse$new("API server error", resp)
      }

    },
    PrivateEditGet = function(order.id, amount, price, post.only=TRUE, advanced=NULL, stop.price=NULL, ...){
      args <- list(...)
      queryParams <- list()
      headerParams <- c()

      if (missing(`order.id`)) {
        stop("Missing required parameter `order.id`.")
      }

      if (missing(`amount`)) {
        stop("Missing required parameter `amount`.")
      }

      if (missing(`price`)) {
        stop("Missing required parameter `price`.")
      }

      queryParams['order_id'] <- order.id

      queryParams['amount'] <- amount

      queryParams['price'] <- price

      queryParams['post_only'] <- post.only

      queryParams['advanced'] <- advanced

      queryParams['stop_price'] <- stop.price

      urlPath <- "/private/edit"

      resp <- self$apiClient$CallApi(url = paste0(self$apiClient$basePath, urlPath),
                                 method = "GET",
                                 queryParams = queryParams,
                                 headerParams = headerParams,
                                 body = body,
                                 ...)

      if (httr::status_code(resp) >= 200 && httr::status_code(resp) <= 299) {
        object$new()$fromJSONString(httr::content(resp, "text", encoding = "UTF-8"))
      } else if (httr::status_code(resp) >= 400 && httr::status_code(resp) <= 499) {
        ApiResponse$new("API client error", resp)
      } else if (httr::status_code(resp) >= 500 && httr::status_code(resp) <= 599) {
        ApiResponse$new("API server error", resp)
      }

    },
    PrivateGetAccountSummaryGet = function(currency, extended=NULL, ...){
      args <- list(...)
      queryParams <- list()
      headerParams <- c()

      if (missing(`currency`)) {
        stop("Missing required parameter `currency`.")
      }

      queryParams['currency'] <- currency

      queryParams['extended'] <- extended

      urlPath <- "/private/get_account_summary"

      resp <- self$apiClient$CallApi(url = paste0(self$apiClient$basePath, urlPath),
                                 method = "GET",
                                 queryParams = queryParams,
                                 headerParams = headerParams,
                                 body = body,
                                 ...)

      if (httr::status_code(resp) >= 200 && httr::status_code(resp) <= 299) {
        object$new()$fromJSONString(httr::content(resp, "text", encoding = "UTF-8"))
      } else if (httr::status_code(resp) >= 400 && httr::status_code(resp) <= 499) {
        ApiResponse$new("API client error", resp)
      } else if (httr::status_code(resp) >= 500 && httr::status_code(resp) <= 599) {
        ApiResponse$new("API server error", resp)
      }

    },
    PrivateGetAddressBookGet = function(currency, type, ...){
      args <- list(...)
      queryParams <- list()
      headerParams <- c()

      if (missing(`currency`)) {
        stop("Missing required parameter `currency`.")
      }

      if (missing(`type`)) {
        stop("Missing required parameter `type`.")
      }

      queryParams['currency'] <- currency

      queryParams['type'] <- type

      urlPath <- "/private/get_address_book"

      resp <- self$apiClient$CallApi(url = paste0(self$apiClient$basePath, urlPath),
                                 method = "GET",
                                 queryParams = queryParams,
                                 headerParams = headerParams,
                                 body = body,
                                 ...)

      if (httr::status_code(resp) >= 200 && httr::status_code(resp) <= 299) {
        object$new()$fromJSONString(httr::content(resp, "text", encoding = "UTF-8"))
      } else if (httr::status_code(resp) >= 400 && httr::status_code(resp) <= 499) {
        ApiResponse$new("API client error", resp)
      } else if (httr::status_code(resp) >= 500 && httr::status_code(resp) <= 599) {
        ApiResponse$new("API server error", resp)
      }

    },
    PrivateGetCurrentDepositAddressGet = function(currency, ...){
      args <- list(...)
      queryParams <- list()
      headerParams <- c()

      if (missing(`currency`)) {
        stop("Missing required parameter `currency`.")
      }

      queryParams['currency'] <- currency

      urlPath <- "/private/get_current_deposit_address"

      resp <- self$apiClient$CallApi(url = paste0(self$apiClient$basePath, urlPath),
                                 method = "GET",
                                 queryParams = queryParams,
                                 headerParams = headerParams,
                                 body = body,
                                 ...)

      if (httr::status_code(resp) >= 200 && httr::status_code(resp) <= 299) {
        object$new()$fromJSONString(httr::content(resp, "text", encoding = "UTF-8"))
      } else if (httr::status_code(resp) >= 400 && httr::status_code(resp) <= 499) {
        ApiResponse$new("API client error", resp)
      } else if (httr::status_code(resp) >= 500 && httr::status_code(resp) <= 599) {
        ApiResponse$new("API server error", resp)
      }

    },
    PrivateGetDepositsGet = function(currency, count=NULL, offset=NULL, ...){
      args <- list(...)
      queryParams <- list()
      headerParams <- c()

      if (missing(`currency`)) {
        stop("Missing required parameter `currency`.")
      }

      queryParams['currency'] <- currency

      queryParams['count'] <- count

      queryParams['offset'] <- offset

      urlPath <- "/private/get_deposits"

      resp <- self$apiClient$CallApi(url = paste0(self$apiClient$basePath, urlPath),
                                 method = "GET",
                                 queryParams = queryParams,
                                 headerParams = headerParams,
                                 body = body,
                                 ...)

      if (httr::status_code(resp) >= 200 && httr::status_code(resp) <= 299) {
        object$new()$fromJSONString(httr::content(resp, "text", encoding = "UTF-8"))
      } else if (httr::status_code(resp) >= 400 && httr::status_code(resp) <= 499) {
        ApiResponse$new("API client error", resp)
      } else if (httr::status_code(resp) >= 500 && httr::status_code(resp) <= 599) {
        ApiResponse$new("API server error", resp)
      }

    },
    PrivateGetEmailLanguageGet = function(...){
      args <- list(...)
      queryParams <- list()
      headerParams <- c()

      urlPath <- "/private/get_email_language"

      resp <- self$apiClient$CallApi(url = paste0(self$apiClient$basePath, urlPath),
                                 method = "GET",
                                 queryParams = queryParams,
                                 headerParams = headerParams,
                                 body = body,
                                 ...)

      if (httr::status_code(resp) >= 200 && httr::status_code(resp) <= 299) {
        object$new()$fromJSONString(httr::content(resp, "text", encoding = "UTF-8"))
      } else if (httr::status_code(resp) >= 400 && httr::status_code(resp) <= 499) {
        ApiResponse$new("API client error", resp)
      } else if (httr::status_code(resp) >= 500 && httr::status_code(resp) <= 599) {
        ApiResponse$new("API server error", resp)
      }

    },
    PrivateGetMarginsGet = function(instrument.name, amount, price, ...){
      args <- list(...)
      queryParams <- list()
      headerParams <- c()

      if (missing(`instrument.name`)) {
        stop("Missing required parameter `instrument.name`.")
      }

      if (missing(`amount`)) {
        stop("Missing required parameter `amount`.")
      }

      if (missing(`price`)) {
        stop("Missing required parameter `price`.")
      }

      queryParams['instrument_name'] <- instrument.name

      queryParams['amount'] <- amount

      queryParams['price'] <- price

      urlPath <- "/private/get_margins"

      resp <- self$apiClient$CallApi(url = paste0(self$apiClient$basePath, urlPath),
                                 method = "GET",
                                 queryParams = queryParams,
                                 headerParams = headerParams,
                                 body = body,
                                 ...)

      if (httr::status_code(resp) >= 200 && httr::status_code(resp) <= 299) {
        object$new()$fromJSONString(httr::content(resp, "text", encoding = "UTF-8"))
      } else if (httr::status_code(resp) >= 400 && httr::status_code(resp) <= 499) {
        ApiResponse$new("API client error", resp)
      } else if (httr::status_code(resp) >= 500 && httr::status_code(resp) <= 599) {
        ApiResponse$new("API server error", resp)
      }

    },
    PrivateGetNewAnnouncementsGet = function(...){
      args <- list(...)
      queryParams <- list()
      headerParams <- c()

      urlPath <- "/private/get_new_announcements"

      resp <- self$apiClient$CallApi(url = paste0(self$apiClient$basePath, urlPath),
                                 method = "GET",
                                 queryParams = queryParams,
                                 headerParams = headerParams,
                                 body = body,
                                 ...)

      if (httr::status_code(resp) >= 200 && httr::status_code(resp) <= 299) {
        object$new()$fromJSONString(httr::content(resp, "text", encoding = "UTF-8"))
      } else if (httr::status_code(resp) >= 400 && httr::status_code(resp) <= 499) {
        ApiResponse$new("API client error", resp)
      } else if (httr::status_code(resp) >= 500 && httr::status_code(resp) <= 599) {
        ApiResponse$new("API server error", resp)
      }

    },
    PrivateGetOpenOrdersByCurrencyGet = function(currency, kind=NULL, type=NULL, ...){
      args <- list(...)
      queryParams <- list()
      headerParams <- c()

      if (missing(`currency`)) {
        stop("Missing required parameter `currency`.")
      }

      queryParams['currency'] <- currency

      queryParams['kind'] <- kind

      queryParams['type'] <- type

      urlPath <- "/private/get_open_orders_by_currency"

      resp <- self$apiClient$CallApi(url = paste0(self$apiClient$basePath, urlPath),
                                 method = "GET",
                                 queryParams = queryParams,
                                 headerParams = headerParams,
                                 body = body,
                                 ...)

      if (httr::status_code(resp) >= 200 && httr::status_code(resp) <= 299) {
        object$new()$fromJSONString(httr::content(resp, "text", encoding = "UTF-8"))
      } else if (httr::status_code(resp) >= 400 && httr::status_code(resp) <= 499) {
        ApiResponse$new("API client error", resp)
      } else if (httr::status_code(resp) >= 500 && httr::status_code(resp) <= 599) {
        ApiResponse$new("API server error", resp)
      }

    },
    PrivateGetOpenOrdersByInstrumentGet = function(instrument.name, type=NULL, ...){
      args <- list(...)
      queryParams <- list()
      headerParams <- c()

      if (missing(`instrument.name`)) {
        stop("Missing required parameter `instrument.name`.")
      }

      queryParams['instrument_name'] <- instrument.name

      queryParams['type'] <- type

      urlPath <- "/private/get_open_orders_by_instrument"

      resp <- self$apiClient$CallApi(url = paste0(self$apiClient$basePath, urlPath),
                                 method = "GET",
                                 queryParams = queryParams,
                                 headerParams = headerParams,
                                 body = body,
                                 ...)

      if (httr::status_code(resp) >= 200 && httr::status_code(resp) <= 299) {
        object$new()$fromJSONString(httr::content(resp, "text", encoding = "UTF-8"))
      } else if (httr::status_code(resp) >= 400 && httr::status_code(resp) <= 499) {
        ApiResponse$new("API client error", resp)
      } else if (httr::status_code(resp) >= 500 && httr::status_code(resp) <= 599) {
        ApiResponse$new("API server error", resp)
      }

    },
    PrivateGetOrderHistoryByCurrencyGet = function(currency, kind=NULL, count=NULL, offset=NULL, include.old=NULL, include.unfilled=NULL, ...){
      args <- list(...)
      queryParams <- list()
      headerParams <- c()

      if (missing(`currency`)) {
        stop("Missing required parameter `currency`.")
      }

      queryParams['currency'] <- currency

      queryParams['kind'] <- kind

      queryParams['count'] <- count

      queryParams['offset'] <- offset

      queryParams['include_old'] <- include.old

      queryParams['include_unfilled'] <- include.unfilled

      urlPath <- "/private/get_order_history_by_currency"

      resp <- self$apiClient$CallApi(url = paste0(self$apiClient$basePath, urlPath),
                                 method = "GET",
                                 queryParams = queryParams,
                                 headerParams = headerParams,
                                 body = body,
                                 ...)

      if (httr::status_code(resp) >= 200 && httr::status_code(resp) <= 299) {
        object$new()$fromJSONString(httr::content(resp, "text", encoding = "UTF-8"))
      } else if (httr::status_code(resp) >= 400 && httr::status_code(resp) <= 499) {
        ApiResponse$new("API client error", resp)
      } else if (httr::status_code(resp) >= 500 && httr::status_code(resp) <= 599) {
        ApiResponse$new("API server error", resp)
      }

    },
    PrivateGetOrderHistoryByInstrumentGet = function(instrument.name, count=NULL, offset=NULL, include.old=NULL, include.unfilled=NULL, ...){
      args <- list(...)
      queryParams <- list()
      headerParams <- c()

      if (missing(`instrument.name`)) {
        stop("Missing required parameter `instrument.name`.")
      }

      queryParams['instrument_name'] <- instrument.name

      queryParams['count'] <- count

      queryParams['offset'] <- offset

      queryParams['include_old'] <- include.old

      queryParams['include_unfilled'] <- include.unfilled

      urlPath <- "/private/get_order_history_by_instrument"

      resp <- self$apiClient$CallApi(url = paste0(self$apiClient$basePath, urlPath),
                                 method = "GET",
                                 queryParams = queryParams,
                                 headerParams = headerParams,
                                 body = body,
                                 ...)

      if (httr::status_code(resp) >= 200 && httr::status_code(resp) <= 299) {
        object$new()$fromJSONString(httr::content(resp, "text", encoding = "UTF-8"))
      } else if (httr::status_code(resp) >= 400 && httr::status_code(resp) <= 499) {
        ApiResponse$new("API client error", resp)
      } else if (httr::status_code(resp) >= 500 && httr::status_code(resp) <= 599) {
        ApiResponse$new("API server error", resp)
      }

    },
    PrivateGetOrderMarginByIdsGet = function(ids, ...){
      args <- list(...)
      queryParams <- list()
      headerParams <- c()

      if (missing(`ids`)) {
        stop("Missing required parameter `ids`.")
      }

      queryParams['ids'] <- ids

      urlPath <- "/private/get_order_margin_by_ids"

      resp <- self$apiClient$CallApi(url = paste0(self$apiClient$basePath, urlPath),
                                 method = "GET",
                                 queryParams = queryParams,
                                 headerParams = headerParams,
                                 body = body,
                                 ...)

      if (httr::status_code(resp) >= 200 && httr::status_code(resp) <= 299) {
        object$new()$fromJSONString(httr::content(resp, "text", encoding = "UTF-8"))
      } else if (httr::status_code(resp) >= 400 && httr::status_code(resp) <= 499) {
        ApiResponse$new("API client error", resp)
      } else if (httr::status_code(resp) >= 500 && httr::status_code(resp) <= 599) {
        ApiResponse$new("API server error", resp)
      }

    },
    PrivateGetOrderStateGet = function(order.id, ...){
      args <- list(...)
      queryParams <- list()
      headerParams <- c()

      if (missing(`order.id`)) {
        stop("Missing required parameter `order.id`.")
      }

      queryParams['order_id'] <- order.id

      urlPath <- "/private/get_order_state"

      resp <- self$apiClient$CallApi(url = paste0(self$apiClient$basePath, urlPath),
                                 method = "GET",
                                 queryParams = queryParams,
                                 headerParams = headerParams,
                                 body = body,
                                 ...)

      if (httr::status_code(resp) >= 200 && httr::status_code(resp) <= 299) {
        object$new()$fromJSONString(httr::content(resp, "text", encoding = "UTF-8"))
      } else if (httr::status_code(resp) >= 400 && httr::status_code(resp) <= 499) {
        ApiResponse$new("API client error", resp)
      } else if (httr::status_code(resp) >= 500 && httr::status_code(resp) <= 599) {
        ApiResponse$new("API server error", resp)
      }

    },
    PrivateGetPositionGet = function(instrument.name, ...){
      args <- list(...)
      queryParams <- list()
      headerParams <- c()

      if (missing(`instrument.name`)) {
        stop("Missing required parameter `instrument.name`.")
      }

      queryParams['instrument_name'] <- instrument.name

      urlPath <- "/private/get_position"

      resp <- self$apiClient$CallApi(url = paste0(self$apiClient$basePath, urlPath),
                                 method = "GET",
                                 queryParams = queryParams,
                                 headerParams = headerParams,
                                 body = body,
                                 ...)

      if (httr::status_code(resp) >= 200 && httr::status_code(resp) <= 299) {
        object$new()$fromJSONString(httr::content(resp, "text", encoding = "UTF-8"))
      } else if (httr::status_code(resp) >= 400 && httr::status_code(resp) <= 499) {
        ApiResponse$new("API client error", resp)
      } else if (httr::status_code(resp) >= 500 && httr::status_code(resp) <= 599) {
        ApiResponse$new("API server error", resp)
      }

    },
    PrivateGetPositionsGet = function(currency, kind=NULL, ...){
      args <- list(...)
      queryParams <- list()
      headerParams <- c()

      if (missing(`currency`)) {
        stop("Missing required parameter `currency`.")
      }

      queryParams['currency'] <- currency

      queryParams['kind'] <- kind

      urlPath <- "/private/get_positions"

      resp <- self$apiClient$CallApi(url = paste0(self$apiClient$basePath, urlPath),
                                 method = "GET",
                                 queryParams = queryParams,
                                 headerParams = headerParams,
                                 body = body,
                                 ...)

      if (httr::status_code(resp) >= 200 && httr::status_code(resp) <= 299) {
        object$new()$fromJSONString(httr::content(resp, "text", encoding = "UTF-8"))
      } else if (httr::status_code(resp) >= 400 && httr::status_code(resp) <= 499) {
        ApiResponse$new("API client error", resp)
      } else if (httr::status_code(resp) >= 500 && httr::status_code(resp) <= 599) {
        ApiResponse$new("API server error", resp)
      }

    },
    PrivateGetSettlementHistoryByCurrencyGet = function(currency, type=NULL, count=NULL, ...){
      args <- list(...)
      queryParams <- list()
      headerParams <- c()

      if (missing(`currency`)) {
        stop("Missing required parameter `currency`.")
      }

      queryParams['currency'] <- currency

      queryParams['type'] <- type

      queryParams['count'] <- count

      urlPath <- "/private/get_settlement_history_by_currency"

      resp <- self$apiClient$CallApi(url = paste0(self$apiClient$basePath, urlPath),
                                 method = "GET",
                                 queryParams = queryParams,
                                 headerParams = headerParams,
                                 body = body,
                                 ...)

      if (httr::status_code(resp) >= 200 && httr::status_code(resp) <= 299) {
        object$new()$fromJSONString(httr::content(resp, "text", encoding = "UTF-8"))
      } else if (httr::status_code(resp) >= 400 && httr::status_code(resp) <= 499) {
        ApiResponse$new("API client error", resp)
      } else if (httr::status_code(resp) >= 500 && httr::status_code(resp) <= 599) {
        ApiResponse$new("API server error", resp)
      }

    },
    PrivateGetSettlementHistoryByInstrumentGet = function(instrument.name, type=NULL, count=NULL, ...){
      args <- list(...)
      queryParams <- list()
      headerParams <- c()

      if (missing(`instrument.name`)) {
        stop("Missing required parameter `instrument.name`.")
      }

      queryParams['instrument_name'] <- instrument.name

      queryParams['type'] <- type

      queryParams['count'] <- count

      urlPath <- "/private/get_settlement_history_by_instrument"

      resp <- self$apiClient$CallApi(url = paste0(self$apiClient$basePath, urlPath),
                                 method = "GET",
                                 queryParams = queryParams,
                                 headerParams = headerParams,
                                 body = body,
                                 ...)

      if (httr::status_code(resp) >= 200 && httr::status_code(resp) <= 299) {
        object$new()$fromJSONString(httr::content(resp, "text", encoding = "UTF-8"))
      } else if (httr::status_code(resp) >= 400 && httr::status_code(resp) <= 499) {
        ApiResponse$new("API client error", resp)
      } else if (httr::status_code(resp) >= 500 && httr::status_code(resp) <= 599) {
        ApiResponse$new("API server error", resp)
      }

    },
    PrivateGetSubaccountsGet = function(with.portfolio=NULL, ...){
      args <- list(...)
      queryParams <- list()
      headerParams <- c()

      queryParams['with_portfolio'] <- with.portfolio

      urlPath <- "/private/get_subaccounts"

      resp <- self$apiClient$CallApi(url = paste0(self$apiClient$basePath, urlPath),
                                 method = "GET",
                                 queryParams = queryParams,
                                 headerParams = headerParams,
                                 body = body,
                                 ...)

      if (httr::status_code(resp) >= 200 && httr::status_code(resp) <= 299) {
        object$new()$fromJSONString(httr::content(resp, "text", encoding = "UTF-8"))
      } else if (httr::status_code(resp) >= 400 && httr::status_code(resp) <= 499) {
        ApiResponse$new("API client error", resp)
      } else if (httr::status_code(resp) >= 500 && httr::status_code(resp) <= 599) {
        ApiResponse$new("API server error", resp)
      }

    },
    PrivateGetTransfersGet = function(currency, count=NULL, offset=NULL, ...){
      args <- list(...)
      queryParams <- list()
      headerParams <- c()

      if (missing(`currency`)) {
        stop("Missing required parameter `currency`.")
      }

      queryParams['currency'] <- currency

      queryParams['count'] <- count

      queryParams['offset'] <- offset

      urlPath <- "/private/get_transfers"

      resp <- self$apiClient$CallApi(url = paste0(self$apiClient$basePath, urlPath),
                                 method = "GET",
                                 queryParams = queryParams,
                                 headerParams = headerParams,
                                 body = body,
                                 ...)

      if (httr::status_code(resp) >= 200 && httr::status_code(resp) <= 299) {
        object$new()$fromJSONString(httr::content(resp, "text", encoding = "UTF-8"))
      } else if (httr::status_code(resp) >= 400 && httr::status_code(resp) <= 499) {
        ApiResponse$new("API client error", resp)
      } else if (httr::status_code(resp) >= 500 && httr::status_code(resp) <= 599) {
        ApiResponse$new("API server error", resp)
      }

    },
    PrivateGetUserTradesByCurrencyAndTimeGet = function(currency, start.timestamp, end.timestamp, kind=NULL, count=NULL, include.old=NULL, sorting=NULL, ...){
      args <- list(...)
      queryParams <- list()
      headerParams <- c()

      if (missing(`currency`)) {
        stop("Missing required parameter `currency`.")
      }

      if (missing(`start.timestamp`)) {
        stop("Missing required parameter `start.timestamp`.")
      }

      if (missing(`end.timestamp`)) {
        stop("Missing required parameter `end.timestamp`.")
      }

      queryParams['currency'] <- currency

      queryParams['kind'] <- kind

      queryParams['start_timestamp'] <- start.timestamp

      queryParams['end_timestamp'] <- end.timestamp

      queryParams['count'] <- count

      queryParams['include_old'] <- include.old

      queryParams['sorting'] <- sorting

      urlPath <- "/private/get_user_trades_by_currency_and_time"

      resp <- self$apiClient$CallApi(url = paste0(self$apiClient$basePath, urlPath),
                                 method = "GET",
                                 queryParams = queryParams,
                                 headerParams = headerParams,
                                 body = body,
                                 ...)

      if (httr::status_code(resp) >= 200 && httr::status_code(resp) <= 299) {
        object$new()$fromJSONString(httr::content(resp, "text", encoding = "UTF-8"))
      } else if (httr::status_code(resp) >= 400 && httr::status_code(resp) <= 499) {
        ApiResponse$new("API client error", resp)
      } else if (httr::status_code(resp) >= 500 && httr::status_code(resp) <= 599) {
        ApiResponse$new("API server error", resp)
      }

    },
    PrivateGetUserTradesByCurrencyGet = function(currency, kind=NULL, start.id=NULL, end.id=NULL, count=NULL, include.old=NULL, sorting=NULL, ...){
      args <- list(...)
      queryParams <- list()
      headerParams <- c()

      if (missing(`currency`)) {
        stop("Missing required parameter `currency`.")
      }

      queryParams['currency'] <- currency

      queryParams['kind'] <- kind

      queryParams['start_id'] <- start.id

      queryParams['end_id'] <- end.id

      queryParams['count'] <- count

      queryParams['include_old'] <- include.old

      queryParams['sorting'] <- sorting

      urlPath <- "/private/get_user_trades_by_currency"

      resp <- self$apiClient$CallApi(url = paste0(self$apiClient$basePath, urlPath),
                                 method = "GET",
                                 queryParams = queryParams,
                                 headerParams = headerParams,
                                 body = body,
                                 ...)

      if (httr::status_code(resp) >= 200 && httr::status_code(resp) <= 299) {
        object$new()$fromJSONString(httr::content(resp, "text", encoding = "UTF-8"))
      } else if (httr::status_code(resp) >= 400 && httr::status_code(resp) <= 499) {
        ApiResponse$new("API client error", resp)
      } else if (httr::status_code(resp) >= 500 && httr::status_code(resp) <= 599) {
        ApiResponse$new("API server error", resp)
      }

    },
    PrivateGetUserTradesByInstrumentAndTimeGet = function(instrument.name, start.timestamp, end.timestamp, count=NULL, include.old=NULL, sorting=NULL, ...){
      args <- list(...)
      queryParams <- list()
      headerParams <- c()

      if (missing(`instrument.name`)) {
        stop("Missing required parameter `instrument.name`.")
      }

      if (missing(`start.timestamp`)) {
        stop("Missing required parameter `start.timestamp`.")
      }

      if (missing(`end.timestamp`)) {
        stop("Missing required parameter `end.timestamp`.")
      }

      queryParams['instrument_name'] <- instrument.name

      queryParams['start_timestamp'] <- start.timestamp

      queryParams['end_timestamp'] <- end.timestamp

      queryParams['count'] <- count

      queryParams['include_old'] <- include.old

      queryParams['sorting'] <- sorting

      urlPath <- "/private/get_user_trades_by_instrument_and_time"

      resp <- self$apiClient$CallApi(url = paste0(self$apiClient$basePath, urlPath),
                                 method = "GET",
                                 queryParams = queryParams,
                                 headerParams = headerParams,
                                 body = body,
                                 ...)

      if (httr::status_code(resp) >= 200 && httr::status_code(resp) <= 299) {
        object$new()$fromJSONString(httr::content(resp, "text", encoding = "UTF-8"))
      } else if (httr::status_code(resp) >= 400 && httr::status_code(resp) <= 499) {
        ApiResponse$new("API client error", resp)
      } else if (httr::status_code(resp) >= 500 && httr::status_code(resp) <= 599) {
        ApiResponse$new("API server error", resp)
      }

    },
    PrivateGetUserTradesByInstrumentGet = function(instrument.name, start.seq=NULL, end.seq=NULL, count=NULL, include.old=NULL, sorting=NULL, ...){
      args <- list(...)
      queryParams <- list()
      headerParams <- c()

      if (missing(`instrument.name`)) {
        stop("Missing required parameter `instrument.name`.")
      }

      queryParams['instrument_name'] <- instrument.name

      queryParams['start_seq'] <- start.seq

      queryParams['end_seq'] <- end.seq

      queryParams['count'] <- count

      queryParams['include_old'] <- include.old

      queryParams['sorting'] <- sorting

      urlPath <- "/private/get_user_trades_by_instrument"

      resp <- self$apiClient$CallApi(url = paste0(self$apiClient$basePath, urlPath),
                                 method = "GET",
                                 queryParams = queryParams,
                                 headerParams = headerParams,
                                 body = body,
                                 ...)

      if (httr::status_code(resp) >= 200 && httr::status_code(resp) <= 299) {
        object$new()$fromJSONString(httr::content(resp, "text", encoding = "UTF-8"))
      } else if (httr::status_code(resp) >= 400 && httr::status_code(resp) <= 499) {
        ApiResponse$new("API client error", resp)
      } else if (httr::status_code(resp) >= 500 && httr::status_code(resp) <= 599) {
        ApiResponse$new("API server error", resp)
      }

    },
    PrivateGetUserTradesByOrderGet = function(order.id, sorting=NULL, ...){
      args <- list(...)
      queryParams <- list()
      headerParams <- c()

      if (missing(`order.id`)) {
        stop("Missing required parameter `order.id`.")
      }

      queryParams['order_id'] <- order.id

      queryParams['sorting'] <- sorting

      urlPath <- "/private/get_user_trades_by_order"

      resp <- self$apiClient$CallApi(url = paste0(self$apiClient$basePath, urlPath),
                                 method = "GET",
                                 queryParams = queryParams,
                                 headerParams = headerParams,
                                 body = body,
                                 ...)

      if (httr::status_code(resp) >= 200 && httr::status_code(resp) <= 299) {
        object$new()$fromJSONString(httr::content(resp, "text", encoding = "UTF-8"))
      } else if (httr::status_code(resp) >= 400 && httr::status_code(resp) <= 499) {
        ApiResponse$new("API client error", resp)
      } else if (httr::status_code(resp) >= 500 && httr::status_code(resp) <= 599) {
        ApiResponse$new("API server error", resp)
      }

    },
    PrivateGetWithdrawalsGet = function(currency, count=NULL, offset=NULL, ...){
      args <- list(...)
      queryParams <- list()
      headerParams <- c()

      if (missing(`currency`)) {
        stop("Missing required parameter `currency`.")
      }

      queryParams['currency'] <- currency

      queryParams['count'] <- count

      queryParams['offset'] <- offset

      urlPath <- "/private/get_withdrawals"

      resp <- self$apiClient$CallApi(url = paste0(self$apiClient$basePath, urlPath),
                                 method = "GET",
                                 queryParams = queryParams,
                                 headerParams = headerParams,
                                 body = body,
                                 ...)

      if (httr::status_code(resp) >= 200 && httr::status_code(resp) <= 299) {
        object$new()$fromJSONString(httr::content(resp, "text", encoding = "UTF-8"))
      } else if (httr::status_code(resp) >= 400 && httr::status_code(resp) <= 499) {
        ApiResponse$new("API client error", resp)
      } else if (httr::status_code(resp) >= 500 && httr::status_code(resp) <= 599) {
        ApiResponse$new("API server error", resp)
      }

    },
    PrivateRemoveFromAddressBookGet = function(currency, type, address, tfa=NULL, ...){
      args <- list(...)
      queryParams <- list()
      headerParams <- c()

      if (missing(`currency`)) {
        stop("Missing required parameter `currency`.")
      }

      if (missing(`type`)) {
        stop("Missing required parameter `type`.")
      }

      if (missing(`address`)) {
        stop("Missing required parameter `address`.")
      }

      queryParams['currency'] <- currency

      queryParams['type'] <- type

      queryParams['address'] <- address

      queryParams['tfa'] <- tfa

      urlPath <- "/private/remove_from_address_book"

      resp <- self$apiClient$CallApi(url = paste0(self$apiClient$basePath, urlPath),
                                 method = "GET",
                                 queryParams = queryParams,
                                 headerParams = headerParams,
                                 body = body,
                                 ...)

      if (httr::status_code(resp) >= 200 && httr::status_code(resp) <= 299) {
        object$new()$fromJSONString(httr::content(resp, "text", encoding = "UTF-8"))
      } else if (httr::status_code(resp) >= 400 && httr::status_code(resp) <= 499) {
        ApiResponse$new("API client error", resp)
      } else if (httr::status_code(resp) >= 500 && httr::status_code(resp) <= 599) {
        ApiResponse$new("API server error", resp)
      }

    },
    PrivateSellGet = function(instrument.name, amount, type=NULL, label=NULL, price=NULL, time.in.force='good_til_cancelled', max.show=1, post.only=TRUE, reduce.only=FALSE, stop.price=NULL, trigger=NULL, advanced=NULL, ...){
      args <- list(...)
      queryParams <- list()
      headerParams <- c()

      if (missing(`instrument.name`)) {
        stop("Missing required parameter `instrument.name`.")
      }

      if (missing(`amount`)) {
        stop("Missing required parameter `amount`.")
      }

      queryParams['instrument_name'] <- instrument.name

      queryParams['amount'] <- amount

      queryParams['type'] <- type

      queryParams['label'] <- label

      queryParams['price'] <- price

      queryParams['time_in_force'] <- time.in.force

      queryParams['max_show'] <- max.show

      queryParams['post_only'] <- post.only

      queryParams['reduce_only'] <- reduce.only

      queryParams['stop_price'] <- stop.price

      queryParams['trigger'] <- trigger

      queryParams['advanced'] <- advanced

      urlPath <- "/private/sell"

      resp <- self$apiClient$CallApi(url = paste0(self$apiClient$basePath, urlPath),
                                 method = "GET",
                                 queryParams = queryParams,
                                 headerParams = headerParams,
                                 body = body,
                                 ...)

      if (httr::status_code(resp) >= 200 && httr::status_code(resp) <= 299) {
        object$new()$fromJSONString(httr::content(resp, "text", encoding = "UTF-8"))
      } else if (httr::status_code(resp) >= 400 && httr::status_code(resp) <= 499) {
        ApiResponse$new("API client error", resp)
      } else if (httr::status_code(resp) >= 500 && httr::status_code(resp) <= 599) {
        ApiResponse$new("API server error", resp)
      }

    },
    PrivateSetAnnouncementAsReadGet = function(announcement.id, ...){
      args <- list(...)
      queryParams <- list()
      headerParams <- c()

      if (missing(`announcement.id`)) {
        stop("Missing required parameter `announcement.id`.")
      }

      queryParams['announcement_id'] <- announcement.id

      urlPath <- "/private/set_announcement_as_read"

      resp <- self$apiClient$CallApi(url = paste0(self$apiClient$basePath, urlPath),
                                 method = "GET",
                                 queryParams = queryParams,
                                 headerParams = headerParams,
                                 body = body,
                                 ...)

      if (httr::status_code(resp) >= 200 && httr::status_code(resp) <= 299) {
        object$new()$fromJSONString(httr::content(resp, "text", encoding = "UTF-8"))
      } else if (httr::status_code(resp) >= 400 && httr::status_code(resp) <= 499) {
        ApiResponse$new("API client error", resp)
      } else if (httr::status_code(resp) >= 500 && httr::status_code(resp) <= 599) {
        ApiResponse$new("API server error", resp)
      }

    },
    PrivateSetEmailForSubaccountGet = function(sid, email, ...){
      args <- list(...)
      queryParams <- list()
      headerParams <- c()

      if (missing(`sid`)) {
        stop("Missing required parameter `sid`.")
      }

      if (missing(`email`)) {
        stop("Missing required parameter `email`.")
      }

      queryParams['sid'] <- sid

      queryParams['email'] <- email

      urlPath <- "/private/set_email_for_subaccount"

      resp <- self$apiClient$CallApi(url = paste0(self$apiClient$basePath, urlPath),
                                 method = "GET",
                                 queryParams = queryParams,
                                 headerParams = headerParams,
                                 body = body,
                                 ...)

      if (httr::status_code(resp) >= 200 && httr::status_code(resp) <= 299) {
        object$new()$fromJSONString(httr::content(resp, "text", encoding = "UTF-8"))
      } else if (httr::status_code(resp) >= 400 && httr::status_code(resp) <= 499) {
        ApiResponse$new("API client error", resp)
      } else if (httr::status_code(resp) >= 500 && httr::status_code(resp) <= 599) {
        ApiResponse$new("API server error", resp)
      }

    },
    PrivateSetEmailLanguageGet = function(language, ...){
      args <- list(...)
      queryParams <- list()
      headerParams <- c()

      if (missing(`language`)) {
        stop("Missing required parameter `language`.")
      }

      queryParams['language'] <- language

      urlPath <- "/private/set_email_language"

      resp <- self$apiClient$CallApi(url = paste0(self$apiClient$basePath, urlPath),
                                 method = "GET",
                                 queryParams = queryParams,
                                 headerParams = headerParams,
                                 body = body,
                                 ...)

      if (httr::status_code(resp) >= 200 && httr::status_code(resp) <= 299) {
        object$new()$fromJSONString(httr::content(resp, "text", encoding = "UTF-8"))
      } else if (httr::status_code(resp) >= 400 && httr::status_code(resp) <= 499) {
        ApiResponse$new("API client error", resp)
      } else if (httr::status_code(resp) >= 500 && httr::status_code(resp) <= 599) {
        ApiResponse$new("API server error", resp)
      }

    },
    PrivateSetPasswordForSubaccountGet = function(sid, password, ...){
      args <- list(...)
      queryParams <- list()
      headerParams <- c()

      if (missing(`sid`)) {
        stop("Missing required parameter `sid`.")
      }

      if (missing(`password`)) {
        stop("Missing required parameter `password`.")
      }

      queryParams['sid'] <- sid

      queryParams['password'] <- password

      urlPath <- "/private/set_password_for_subaccount"

      resp <- self$apiClient$CallApi(url = paste0(self$apiClient$basePath, urlPath),
                                 method = "GET",
                                 queryParams = queryParams,
                                 headerParams = headerParams,
                                 body = body,
                                 ...)

      if (httr::status_code(resp) >= 200 && httr::status_code(resp) <= 299) {
        object$new()$fromJSONString(httr::content(resp, "text", encoding = "UTF-8"))
      } else if (httr::status_code(resp) >= 400 && httr::status_code(resp) <= 499) {
        ApiResponse$new("API client error", resp)
      } else if (httr::status_code(resp) >= 500 && httr::status_code(resp) <= 599) {
        ApiResponse$new("API server error", resp)
      }

    },
    PrivateSubmitTransferToSubaccountGet = function(currency, amount, destination, ...){
      args <- list(...)
      queryParams <- list()
      headerParams <- c()

      if (missing(`currency`)) {
        stop("Missing required parameter `currency`.")
      }

      if (missing(`amount`)) {
        stop("Missing required parameter `amount`.")
      }

      if (missing(`destination`)) {
        stop("Missing required parameter `destination`.")
      }

      queryParams['currency'] <- currency

      queryParams['amount'] <- amount

      queryParams['destination'] <- destination

      urlPath <- "/private/submit_transfer_to_subaccount"

      resp <- self$apiClient$CallApi(url = paste0(self$apiClient$basePath, urlPath),
                                 method = "GET",
                                 queryParams = queryParams,
                                 headerParams = headerParams,
                                 body = body,
                                 ...)

      if (httr::status_code(resp) >= 200 && httr::status_code(resp) <= 299) {
        object$new()$fromJSONString(httr::content(resp, "text", encoding = "UTF-8"))
      } else if (httr::status_code(resp) >= 400 && httr::status_code(resp) <= 499) {
        ApiResponse$new("API client error", resp)
      } else if (httr::status_code(resp) >= 500 && httr::status_code(resp) <= 599) {
        ApiResponse$new("API server error", resp)
      }

    },
    PrivateSubmitTransferToUserGet = function(currency, amount, destination, tfa=NULL, ...){
      args <- list(...)
      queryParams <- list()
      headerParams <- c()

      if (missing(`currency`)) {
        stop("Missing required parameter `currency`.")
      }

      if (missing(`amount`)) {
        stop("Missing required parameter `amount`.")
      }

      if (missing(`destination`)) {
        stop("Missing required parameter `destination`.")
      }

      queryParams['currency'] <- currency

      queryParams['amount'] <- amount

      queryParams['destination'] <- destination

      queryParams['tfa'] <- tfa

      urlPath <- "/private/submit_transfer_to_user"

      resp <- self$apiClient$CallApi(url = paste0(self$apiClient$basePath, urlPath),
                                 method = "GET",
                                 queryParams = queryParams,
                                 headerParams = headerParams,
                                 body = body,
                                 ...)

      if (httr::status_code(resp) >= 200 && httr::status_code(resp) <= 299) {
        object$new()$fromJSONString(httr::content(resp, "text", encoding = "UTF-8"))
      } else if (httr::status_code(resp) >= 400 && httr::status_code(resp) <= 499) {
        ApiResponse$new("API client error", resp)
      } else if (httr::status_code(resp) >= 500 && httr::status_code(resp) <= 599) {
        ApiResponse$new("API server error", resp)
      }

    },
    PrivateToggleDepositAddressCreationGet = function(currency, state, ...){
      args <- list(...)
      queryParams <- list()
      headerParams <- c()

      if (missing(`currency`)) {
        stop("Missing required parameter `currency`.")
      }

      if (missing(`state`)) {
        stop("Missing required parameter `state`.")
      }

      queryParams['currency'] <- currency

      queryParams['state'] <- state

      urlPath <- "/private/toggle_deposit_address_creation"

      resp <- self$apiClient$CallApi(url = paste0(self$apiClient$basePath, urlPath),
                                 method = "GET",
                                 queryParams = queryParams,
                                 headerParams = headerParams,
                                 body = body,
                                 ...)

      if (httr::status_code(resp) >= 200 && httr::status_code(resp) <= 299) {
        object$new()$fromJSONString(httr::content(resp, "text", encoding = "UTF-8"))
      } else if (httr::status_code(resp) >= 400 && httr::status_code(resp) <= 499) {
        ApiResponse$new("API client error", resp)
      } else if (httr::status_code(resp) >= 500 && httr::status_code(resp) <= 599) {
        ApiResponse$new("API server error", resp)
      }

    },
    PrivateToggleNotificationsFromSubaccountGet = function(sid, state, ...){
      args <- list(...)
      queryParams <- list()
      headerParams <- c()

      if (missing(`sid`)) {
        stop("Missing required parameter `sid`.")
      }

      if (missing(`state`)) {
        stop("Missing required parameter `state`.")
      }

      queryParams['sid'] <- sid

      queryParams['state'] <- state

      urlPath <- "/private/toggle_notifications_from_subaccount"

      resp <- self$apiClient$CallApi(url = paste0(self$apiClient$basePath, urlPath),
                                 method = "GET",
                                 queryParams = queryParams,
                                 headerParams = headerParams,
                                 body = body,
                                 ...)

      if (httr::status_code(resp) >= 200 && httr::status_code(resp) <= 299) {
        object$new()$fromJSONString(httr::content(resp, "text", encoding = "UTF-8"))
      } else if (httr::status_code(resp) >= 400 && httr::status_code(resp) <= 499) {
        ApiResponse$new("API client error", resp)
      } else if (httr::status_code(resp) >= 500 && httr::status_code(resp) <= 599) {
        ApiResponse$new("API server error", resp)
      }

    },
    PrivateToggleSubaccountLoginGet = function(sid, state, ...){
      args <- list(...)
      queryParams <- list()
      headerParams <- c()

      if (missing(`sid`)) {
        stop("Missing required parameter `sid`.")
      }

      if (missing(`state`)) {
        stop("Missing required parameter `state`.")
      }

      queryParams['sid'] <- sid

      queryParams['state'] <- state

      urlPath <- "/private/toggle_subaccount_login"

      resp <- self$apiClient$CallApi(url = paste0(self$apiClient$basePath, urlPath),
                                 method = "GET",
                                 queryParams = queryParams,
                                 headerParams = headerParams,
                                 body = body,
                                 ...)

      if (httr::status_code(resp) >= 200 && httr::status_code(resp) <= 299) {
        object$new()$fromJSONString(httr::content(resp, "text", encoding = "UTF-8"))
      } else if (httr::status_code(resp) >= 400 && httr::status_code(resp) <= 499) {
        ApiResponse$new("API client error", resp)
      } else if (httr::status_code(resp) >= 500 && httr::status_code(resp) <= 599) {
        ApiResponse$new("API server error", resp)
      }

    },
    PrivateWithdrawGet = function(currency, address, amount, priority=NULL, tfa=NULL, ...){
      args <- list(...)
      queryParams <- list()
      headerParams <- c()

      if (missing(`currency`)) {
        stop("Missing required parameter `currency`.")
      }

      if (missing(`address`)) {
        stop("Missing required parameter `address`.")
      }

      if (missing(`amount`)) {
        stop("Missing required parameter `amount`.")
      }

      queryParams['currency'] <- currency

      queryParams['address'] <- address

      queryParams['amount'] <- amount

      queryParams['priority'] <- priority

      queryParams['tfa'] <- tfa

      urlPath <- "/private/withdraw"

      resp <- self$apiClient$CallApi(url = paste0(self$apiClient$basePath, urlPath),
                                 method = "GET",
                                 queryParams = queryParams,
                                 headerParams = headerParams,
                                 body = body,
                                 ...)

      if (httr::status_code(resp) >= 200 && httr::status_code(resp) <= 299) {
        object$new()$fromJSONString(httr::content(resp, "text", encoding = "UTF-8"))
      } else if (httr::status_code(resp) >= 400 && httr::status_code(resp) <= 499) {
        ApiResponse$new("API client error", resp)
      } else if (httr::status_code(resp) >= 500 && httr::status_code(resp) <= 599) {
        ApiResponse$new("API server error", resp)
      }

    }
  )
)
