# Deribit API
#
# #Overview  Deribit provides three different interfaces to access the API:  * [JSON-RPC over Websocket](#json-rpc) * [JSON-RPC over HTTP](#json-rpc) * [FIX](#fix-api) (Financial Information eXchange)  With the API Console you can use and test the JSON-RPC API, both via HTTP and  via Websocket. To visit the API console, go to __Account > API tab >  API Console tab.__   ##Naming Deribit tradeable assets or instruments use the following system of naming:  |Kind|Examples|Template|Comments| |----|--------|--------|--------| |Future|<code>BTC-25MAR16</code>, <code>BTC-5AUG16</code>|<code>BTC-DMMMYY</code>|<code>BTC</code> is currency, <code>DMMMYY</code> is expiration date, <code>D</code> stands for day of month (1 or 2 digits), <code>MMM</code> - month (3 first letters in English), <code>YY</code> stands for year.| |Perpetual|<code>BTC-PERPETUAL</code>                        ||Perpetual contract for currency <code>BTC</code>.| |Option|<code>BTC-25MAR16-420-C</code>, <code>BTC-5AUG16-580-P</code>|<code>BTC-DMMMYY-STRIKE-K</code>|<code>STRIKE</code> is option strike price in USD. Template <code>K</code> is option kind: <code>C</code> for call options or <code>P</code> for put options.|   # JSON-RPC JSON-RPC is a light-weight remote procedure call (RPC) protocol. The  [JSON-RPC specification](https://www.jsonrpc.org/specification) defines the data structures that are used for the messages that are exchanged between client and server, as well as the rules around their processing. JSON-RPC uses JSON (RFC 4627) as data format.  JSON-RPC is transport agnostic: it does not specify which transport mechanism must be used. The Deribit API supports both Websocket (preferred) and HTTP (with limitations: subscriptions are not supported over HTTP).  ## Request messages > An example of a request message:  ```json {     \"jsonrpc\": \"2.0\",     \"id\": 8066,     \"method\": \"public/ticker\",     \"params\": {         \"instrument\": \"BTC-24AUG18-6500-P\"     } } ```  According to the JSON-RPC sepcification the requests must be JSON objects with the following fields.  |Name|Type|Description| |----|----|-----------| |jsonrpc|string|The version of the JSON-RPC spec: \"2.0\"| |id|integer or string|An identifier of the request. If it is included, then the response will contain the same identifier| |method|string|The method to be invoked| |params|object|The parameters values for the method. The field names must match with the expected parameter names. The parameters that are expected are described in the documentation for the methods, below.|  <aside class=\"warning\"> The JSON-RPC specification describes two features that are currently not supported by the API:  <ul> <li>Specification of parameter values by position</li> <li>Batch requests</li> </ul>  </aside>   ## Response messages > An example of a response message:  ```json {     \"jsonrpc\": \"2.0\",     \"id\": 5239,     \"testnet\": false,     \"result\": [         {             \"currency\": \"BTC\",             \"currencyLong\": \"Bitcoin\",             \"minConfirmation\": 2,             \"txFee\": 0.0006,             \"isActive\": true,             \"coinType\": \"BITCOIN\",             \"baseAddress\": null         }     ],     \"usIn\": 1535043730126248,     \"usOut\": 1535043730126250,     \"usDiff\": 2 } ```  The JSON-RPC API always responds with a JSON object with the following fields.   |Name|Type|Description| |----|----|-----------| |id|integer|This is the same id that was sent in the request.| |result|any|If successful, the result of the API call. The format for the result is described with each method.| |error|error object|Only present if there was an error invoking the method. The error object is described below.| |testnet|boolean|Indicates whether the API in use is actually the test API.  <code>false</code> for production server, <code>true</code> for test server.| |usIn|integer|The timestamp when the requests was received (microseconds since the Unix epoch)| |usOut|integer|The timestamp when the response was sent (microseconds since the Unix epoch)| |usDiff|integer|The number of microseconds that was spent handling the request|  <aside class=\"notice\"> The fields <code>testnet</code>, <code>usIn</code>, <code>usOut</code> and <code>usDiff</code> are not part of the JSON-RPC standard.  <p>In order not to clutter the examples they will generally be omitted from the example code.</p> </aside>  > An example of a response with an error:  ```json {     \"jsonrpc\": \"2.0\",     \"id\": 8163,     \"error\": {         \"code\": 11050,         \"message\": \"bad_request\"     },     \"testnet\": false,     \"usIn\": 1535037392434763,     \"usOut\": 1535037392448119,     \"usDiff\": 13356 } ``` In case of an error the response message will contain the error field, with as value an object with the following with the following fields:  |Name|Type|Description |----|----|-----------| |code|integer|A number that indicates the kind of error.| |message|string|A short description that indicates the kind of error.| |data|any|Additional data about the error. This field may be omitted.|  ## Notifications  > An example of a notification:  ```json {     \"jsonrpc\": \"2.0\",     \"method\": \"subscription\",     \"params\": {         \"channel\": \"deribit_price_index.btc_usd\",         \"data\": {             \"timestamp\": 1535098298227,             \"price\": 6521.17,             \"index_name\": \"btc_usd\"         }     } } ```  API users can subscribe to certain types of notifications. This means that they will receive JSON-RPC notification-messages from the server when certain events occur, such as changes to the index price or changes to the order book for a certain instrument.   The API methods [public/subscribe](#public-subscribe) and [private/subscribe](#private-subscribe) are used to set up a subscription. Since HTTP does not support the sending of messages from server to client, these methods are only availble when using the Websocket transport mechanism.  At the moment of subscription a \"channel\" must be specified. The channel determines the type of events that will be received.  See [Subscriptions](#subscriptions) for more details about the channels.  In accordance with the JSON-RPC specification, the format of a notification  is that of a request message without an <code>id</code> field. The value of the <code>method</code> field will always be <code>\"subscription\"</code>. The <code>params</code> field will always be an object with 2 members: <code>channel</code> and <code>data</code>. The value of the <code>channel</code> member is the name of the channel (a string). The value of the <code>data</code> member is an object that contains data  that is specific for the channel.   ## Authentication  > An example of a JSON request with token:  ```json {     \"id\": 5647,     \"method\": \"private/get_subaccounts\",     \"params\": {         \"access_token\": \"67SVutDoVZSzkUStHSuk51WntMNBJ5mh5DYZhwzpiqDF\"     } } ```  The API consists of `public` and `private` methods. The public methods do not require authentication. The private methods use OAuth 2.0 authentication. This means that a valid OAuth access token must be included in the request, which can get achived by calling method [public/auth](#public-auth).  When the token was assigned to the user, it should be passed along, with other request parameters, back to the server:  |Connection type|Access token placement |----|-----------| |**Websocket**|Inside request JSON parameters, as an `access_token` field| |**HTTP (REST)**|Header `Authorization: bearer ```Token``` ` value|  ### Additional authorization method - basic user credentials  <span style=\"color:red\"><b> ! Not recommended - however, it could be useful for quick testing API</b></span></br>  Every `private` method could be accessed by providing, inside HTTP `Authorization: Basic XXX` header, values with user `ClientId` and assigned `ClientSecret` (both values can be found on the API page on the Deribit website) encoded with `Base64`:  <code>Authorization: Basic BASE64(`ClientId` + `:` + `ClientSecret`)</code>   ### Additional authorization method - Deribit signature credentials  The Derbit service provides dedicated authorization method, which harness user generated signature to increase security level for passing request data. Generated value is passed inside `Authorization` header, coded as:  <code>Authorization: deri-hmac-sha256 id=```ClientId```,ts=```Timestamp```,sig=```Signature```,nonce=```Nonce```</code>  where:  |Deribit credential|Description |----|-----------| |*ClientId*|Can be found on the API page on the Deribit website| |*Timestamp*|Time when the request was generated - given as **miliseconds**. It's valid for **60 seconds** since generation, after that time any request with an old timestamp will be rejected.| |*Signature*|Value for signature calculated as described below | |*Nonce*|Single usage, user generated initialization vector for the server token|  The signature is generated by the following formula:  <code> Signature = HEX_STRING( HMAC-SHA256( ClientSecret, StringToSign ) );</code></br>  <code> StringToSign =  Timestamp + \"\\n\" + Nonce + \"\\n\" + RequestData;</code></br>  <code> RequestData =  UPPERCASE(HTTP_METHOD())  + \"\\n\" + URI() + \"\\n\" + RequestBody + \"\\n\";</code></br>   e.g. (using shell with ```openssl``` tool):  <code>&nbsp;&nbsp;&nbsp;&nbsp;ClientId=AAAAAAAAAAA</code></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;ClientSecret=ABCD</code></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;Timestamp=$( date +%s000 )</code></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;Nonce=$( cat /dev/urandom | tr -dc 'a-z0-9' | head -c8 )</code></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;URI=\"/api/v2/private/get_account_summary?currency=BTC\"</code></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;HttpMethod=GET</code></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;Body=\"\"</code></br></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;Signature=$( echo -ne \"${Timestamp}\\n${Nonce}\\n${HttpMethod}\\n${URI}\\n${Body}\\n\" | openssl sha256 -r -hmac \"$ClientSecret\" | cut -f1 -d' ' )</code></br></br> <code>&nbsp;&nbsp;&nbsp;&nbsp;echo $Signature</code></br></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;shell output> ea40d5e5e4fae235ab22b61da98121fbf4acdc06db03d632e23c66bcccb90d2c  (**WARNING**: Exact value depends on current timestamp and client credentials</code></br></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;curl -s -X ${HttpMethod} -H \"Authorization: deri-hmac-sha256 id=${ClientId},ts=${Timestamp},nonce=${Nonce},sig=${Signature}\" \"https://www.deribit.com${URI}\"</code></br></br>    ### Additional authorization method - signature credentials (WebSocket API)  When connecting through Websocket, user can request for authorization using ```client_credential``` method, which requires providing following parameters (as a part of JSON request):  |JSON parameter|Description |----|-----------| |*grant_type*|Must be **client_signature**| |*client_id*|Can be found on the API page on the Deribit website| |*timestamp*|Time when the request was generated - given as **miliseconds**. It's valid for **60 seconds** since generation, after that time any request with an old timestamp will be rejected.| |*signature*|Value for signature calculated as described below | |*nonce*|Single usage, user generated initialization vector for the server token| |*data*|**Optional** field, which contains any user specific value|  The signature is generated by the following formula:  <code> StringToSign =  Timestamp + \"\\n\" + Nonce + \"\\n\" + Data;</code></br>  <code> Signature = HEX_STRING( HMAC-SHA256( ClientSecret, StringToSign ) );</code></br>   e.g. (using shell with ```openssl``` tool):  <code>&nbsp;&nbsp;&nbsp;&nbsp;ClientId=AAAAAAAAAAA</code></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;ClientSecret=ABCD</code></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;Timestamp=$( date +%s000 ) # e.g. 1554883365000 </code></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;Nonce=$( cat /dev/urandom | tr -dc 'a-z0-9' | head -c8 ) # e.g. fdbmmz79 </code></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;Data=\"\"</code></br></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;Signature=$( echo -ne \"${Timestamp}\\n${Nonce}\\n${Data}\\n\" | openssl sha256 -r -hmac \"$ClientSecret\" | cut -f1 -d' ' )</code></br></br> <code>&nbsp;&nbsp;&nbsp;&nbsp;echo $Signature</code></br></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;shell output> e20c9cd5639d41f8bbc88f4d699c4baf94a4f0ee320e9a116b72743c449eb994  (**WARNING**: Exact value depends on current timestamp and client credentials</code></br></br>   You can also check the signature value using some online tools like, e.g: [https://codebeautify.org/hmac-generator](https://codebeautify.org/hmac-generator) (but don't forget about adding *newline* after each part of the hashed text and remember that you **should use** it only with your **test credentials**).   Here's a sample JSON request created using the values from the example above:  <code> {                            </br> &nbsp;&nbsp;\"jsonrpc\" : \"2.0\",         </br> &nbsp;&nbsp;\"id\" : 9929,               </br> &nbsp;&nbsp;\"method\" : \"public/auth\",  </br> &nbsp;&nbsp;\"params\" :                 </br> &nbsp;&nbsp;{                        </br> &nbsp;&nbsp;&nbsp;&nbsp;\"grant_type\" : \"client_signature\",   </br> &nbsp;&nbsp;&nbsp;&nbsp;\"client_id\" : \"AAAAAAAAAAA\",         </br> &nbsp;&nbsp;&nbsp;&nbsp;\"timestamp\": \"1554883365000\",        </br> &nbsp;&nbsp;&nbsp;&nbsp;\"nonce\": \"fdbmmz79\",                 </br> &nbsp;&nbsp;&nbsp;&nbsp;\"data\": \"\",                          </br> &nbsp;&nbsp;&nbsp;&nbsp;\"signature\" : \"e20c9cd5639d41f8bbc88f4d699c4baf94a4f0ee320e9a116b72743c449eb994\"  </br> &nbsp;&nbsp;}                        </br> }                            </br> </code>   ### Access scope  When asking for `access token` user can provide the required access level (called `scope`) which defines what type of functionality he/she wants to use, and whether requests are only going to check for some data or also to update them.  Scopes are required and checked for `private` methods, so if you plan to use only `public` information you can stay with values assigned by default.  |Scope|Description |----|-----------| |*account:read*|Access to **account** methods - read only data| |*account:read_write*|Access to **account** methods - allows to manage account settings, add subaccounts, etc.| |*trade:read*|Access to **trade** methods - read only data| |*trade:read_write*|Access to **trade** methods - required to create and modify orders| |*wallet:read*|Access to **wallet** methods - read only data| |*wallet:read_write*|Access to **wallet** methods - allows to withdraw, generate new deposit address, etc.| |*wallet:none*, *account:none*, *trade:none*|Blocked access to specified functionality|    <span style=\"color:red\">**NOTICE:**</span> Depending on choosing an authentication method (```grant type```) some scopes could be narrowed by the server. e.g. when ```grant_type = client_credentials``` and ```scope = wallet:read_write``` it's modified by the server as ```scope = wallet:read```\"   ## JSON-RPC over websocket Websocket is the prefered transport mechanism for the JSON-RPC API, because it is faster and because it can support [subscriptions](#subscriptions) and [cancel on disconnect](#private-enable_cancel_on_disconnect). The code examples that can be found next to each of the methods show how websockets can be used from Python or Javascript/node.js.  ## JSON-RPC over HTTP Besides websockets it is also possible to use the API via HTTP. The code examples for 'shell' show how this can be done using curl. Note that subscriptions and cancel on disconnect are not supported via HTTP.  #Methods 
#
# The version of the OpenAPI document: 2.0.0
# 
# Generated by: https://openapi-generator.tech


#' Order Class
#'
#' @field direction 
#' @field reduce_only 
#' @field triggered 
#' @field order_id 
#' @field price 
#' @field time_in_force 
#' @field api 
#' @field order_state 
#' @field implv 
#' @field advanced 
#' @field post_only 
#' @field usd 
#' @field stop_price 
#' @field order_type 
#' @field last_update_timestamp 
#' @field original_order_type 
#' @field max_show 
#' @field profit_loss 
#' @field is_liquidation 
#' @field filled_amount 
#' @field label 
#' @field commission 
#' @field amount 
#' @field trigger 
#' @field instrument_name 
#' @field creation_timestamp 
#' @field average_price 
#'
#' @importFrom R6 R6Class
#' @importFrom jsonlite fromJSON toJSON
#' @export
Order <- R6::R6Class(
  'Order',
  public = list(
    `direction` = NULL,
    `reduce_only` = NULL,
    `triggered` = NULL,
    `order_id` = NULL,
    `price` = NULL,
    `time_in_force` = NULL,
    `api` = NULL,
    `order_state` = NULL,
    `implv` = NULL,
    `advanced` = NULL,
    `post_only` = NULL,
    `usd` = NULL,
    `stop_price` = NULL,
    `order_type` = NULL,
    `last_update_timestamp` = NULL,
    `original_order_type` = NULL,
    `max_show` = NULL,
    `profit_loss` = NULL,
    `is_liquidation` = NULL,
    `filled_amount` = NULL,
    `label` = NULL,
    `commission` = NULL,
    `amount` = NULL,
    `trigger` = NULL,
    `instrument_name` = NULL,
    `creation_timestamp` = NULL,
    `average_price` = NULL,
    initialize = function(`direction`, `order_id`, `price`, `time_in_force`, `api`, `order_state`, `post_only`, `order_type`, `last_update_timestamp`, `max_show`, `is_liquidation`, `label`, `creation_timestamp`, `reduce_only`=NULL, `triggered`=NULL, `implv`=NULL, `advanced`=NULL, `usd`=NULL, `stop_price`=NULL, `original_order_type`=NULL, `profit_loss`=NULL, `filled_amount`=NULL, `commission`=NULL, `amount`=NULL, `trigger`=NULL, `instrument_name`=NULL, `average_price`=NULL, ...){
      local.optional.var <- list(...)
      if (!missing(`direction`)) {
        stopifnot(is.character(`direction`), length(`direction`) == 1)
        self$`direction` <- `direction`
      }
      if (!missing(`order_id`)) {
        stopifnot(is.character(`order_id`), length(`order_id`) == 1)
        self$`order_id` <- `order_id`
      }
      if (!missing(`price`)) {
        self$`price` <- `price`
      }
      if (!missing(`time_in_force`)) {
        stopifnot(is.character(`time_in_force`), length(`time_in_force`) == 1)
        self$`time_in_force` <- `time_in_force`
      }
      if (!missing(`api`)) {
        self$`api` <- `api`
      }
      if (!missing(`order_state`)) {
        stopifnot(is.character(`order_state`), length(`order_state`) == 1)
        self$`order_state` <- `order_state`
      }
      if (!missing(`post_only`)) {
        self$`post_only` <- `post_only`
      }
      if (!missing(`order_type`)) {
        stopifnot(is.character(`order_type`), length(`order_type`) == 1)
        self$`order_type` <- `order_type`
      }
      if (!missing(`last_update_timestamp`)) {
        stopifnot(is.numeric(`last_update_timestamp`), length(`last_update_timestamp`) == 1)
        self$`last_update_timestamp` <- `last_update_timestamp`
      }
      if (!missing(`max_show`)) {
        self$`max_show` <- `max_show`
      }
      if (!missing(`is_liquidation`)) {
        self$`is_liquidation` <- `is_liquidation`
      }
      if (!missing(`label`)) {
        stopifnot(is.character(`label`), length(`label`) == 1)
        self$`label` <- `label`
      }
      if (!missing(`creation_timestamp`)) {
        stopifnot(is.numeric(`creation_timestamp`), length(`creation_timestamp`) == 1)
        self$`creation_timestamp` <- `creation_timestamp`
      }
      if (!is.null(`reduce_only`)) {
        self$`reduce_only` <- `reduce_only`
      }
      if (!is.null(`triggered`)) {
        self$`triggered` <- `triggered`
      }
      if (!is.null(`implv`)) {
        self$`implv` <- `implv`
      }
      if (!is.null(`advanced`)) {
        stopifnot(is.character(`advanced`), length(`advanced`) == 1)
        self$`advanced` <- `advanced`
      }
      if (!is.null(`usd`)) {
        self$`usd` <- `usd`
      }
      if (!is.null(`stop_price`)) {
        self$`stop_price` <- `stop_price`
      }
      if (!is.null(`original_order_type`)) {
        stopifnot(is.character(`original_order_type`), length(`original_order_type`) == 1)
        self$`original_order_type` <- `original_order_type`
      }
      if (!is.null(`profit_loss`)) {
        self$`profit_loss` <- `profit_loss`
      }
      if (!is.null(`filled_amount`)) {
        self$`filled_amount` <- `filled_amount`
      }
      if (!is.null(`commission`)) {
        self$`commission` <- `commission`
      }
      if (!is.null(`amount`)) {
        self$`amount` <- `amount`
      }
      if (!is.null(`trigger`)) {
        stopifnot(is.character(`trigger`), length(`trigger`) == 1)
        self$`trigger` <- `trigger`
      }
      if (!is.null(`instrument_name`)) {
        stopifnot(is.character(`instrument_name`), length(`instrument_name`) == 1)
        self$`instrument_name` <- `instrument_name`
      }
      if (!is.null(`average_price`)) {
        self$`average_price` <- `average_price`
      }
    },
    toJSON = function() {
      OrderObject <- list()
      if (!is.null(self$`direction`)) {
        OrderObject[['direction']] <-
          self$`direction`
      }
      if (!is.null(self$`reduce_only`)) {
        OrderObject[['reduce_only']] <-
          self$`reduce_only`
      }
      if (!is.null(self$`triggered`)) {
        OrderObject[['triggered']] <-
          self$`triggered`
      }
      if (!is.null(self$`order_id`)) {
        OrderObject[['order_id']] <-
          self$`order_id`
      }
      if (!is.null(self$`price`)) {
        OrderObject[['price']] <-
          self$`price`
      }
      if (!is.null(self$`time_in_force`)) {
        OrderObject[['time_in_force']] <-
          self$`time_in_force`
      }
      if (!is.null(self$`api`)) {
        OrderObject[['api']] <-
          self$`api`
      }
      if (!is.null(self$`order_state`)) {
        OrderObject[['order_state']] <-
          self$`order_state`
      }
      if (!is.null(self$`implv`)) {
        OrderObject[['implv']] <-
          self$`implv`
      }
      if (!is.null(self$`advanced`)) {
        OrderObject[['advanced']] <-
          self$`advanced`
      }
      if (!is.null(self$`post_only`)) {
        OrderObject[['post_only']] <-
          self$`post_only`
      }
      if (!is.null(self$`usd`)) {
        OrderObject[['usd']] <-
          self$`usd`
      }
      if (!is.null(self$`stop_price`)) {
        OrderObject[['stop_price']] <-
          self$`stop_price`
      }
      if (!is.null(self$`order_type`)) {
        OrderObject[['order_type']] <-
          self$`order_type`
      }
      if (!is.null(self$`last_update_timestamp`)) {
        OrderObject[['last_update_timestamp']] <-
          self$`last_update_timestamp`
      }
      if (!is.null(self$`original_order_type`)) {
        OrderObject[['original_order_type']] <-
          self$`original_order_type`
      }
      if (!is.null(self$`max_show`)) {
        OrderObject[['max_show']] <-
          self$`max_show`
      }
      if (!is.null(self$`profit_loss`)) {
        OrderObject[['profit_loss']] <-
          self$`profit_loss`
      }
      if (!is.null(self$`is_liquidation`)) {
        OrderObject[['is_liquidation']] <-
          self$`is_liquidation`
      }
      if (!is.null(self$`filled_amount`)) {
        OrderObject[['filled_amount']] <-
          self$`filled_amount`
      }
      if (!is.null(self$`label`)) {
        OrderObject[['label']] <-
          self$`label`
      }
      if (!is.null(self$`commission`)) {
        OrderObject[['commission']] <-
          self$`commission`
      }
      if (!is.null(self$`amount`)) {
        OrderObject[['amount']] <-
          self$`amount`
      }
      if (!is.null(self$`trigger`)) {
        OrderObject[['trigger']] <-
          self$`trigger`
      }
      if (!is.null(self$`instrument_name`)) {
        OrderObject[['instrument_name']] <-
          self$`instrument_name`
      }
      if (!is.null(self$`creation_timestamp`)) {
        OrderObject[['creation_timestamp']] <-
          self$`creation_timestamp`
      }
      if (!is.null(self$`average_price`)) {
        OrderObject[['average_price']] <-
          self$`average_price`
      }

      OrderObject
    },
    fromJSON = function(OrderJson) {
      OrderObject <- jsonlite::fromJSON(OrderJson)
      if (!is.null(OrderObject$`direction`)) {
        self$`direction` <- OrderObject$`direction`
      }
      if (!is.null(OrderObject$`reduce_only`)) {
        self$`reduce_only` <- OrderObject$`reduce_only`
      }
      if (!is.null(OrderObject$`triggered`)) {
        self$`triggered` <- OrderObject$`triggered`
      }
      if (!is.null(OrderObject$`order_id`)) {
        self$`order_id` <- OrderObject$`order_id`
      }
      if (!is.null(OrderObject$`price`)) {
        self$`price` <- OrderObject$`price`
      }
      if (!is.null(OrderObject$`time_in_force`)) {
        self$`time_in_force` <- OrderObject$`time_in_force`
      }
      if (!is.null(OrderObject$`api`)) {
        self$`api` <- OrderObject$`api`
      }
      if (!is.null(OrderObject$`order_state`)) {
        self$`order_state` <- OrderObject$`order_state`
      }
      if (!is.null(OrderObject$`implv`)) {
        self$`implv` <- OrderObject$`implv`
      }
      if (!is.null(OrderObject$`advanced`)) {
        self$`advanced` <- OrderObject$`advanced`
      }
      if (!is.null(OrderObject$`post_only`)) {
        self$`post_only` <- OrderObject$`post_only`
      }
      if (!is.null(OrderObject$`usd`)) {
        self$`usd` <- OrderObject$`usd`
      }
      if (!is.null(OrderObject$`stop_price`)) {
        self$`stop_price` <- OrderObject$`stop_price`
      }
      if (!is.null(OrderObject$`order_type`)) {
        self$`order_type` <- OrderObject$`order_type`
      }
      if (!is.null(OrderObject$`last_update_timestamp`)) {
        self$`last_update_timestamp` <- OrderObject$`last_update_timestamp`
      }
      if (!is.null(OrderObject$`original_order_type`)) {
        self$`original_order_type` <- OrderObject$`original_order_type`
      }
      if (!is.null(OrderObject$`max_show`)) {
        self$`max_show` <- OrderObject$`max_show`
      }
      if (!is.null(OrderObject$`profit_loss`)) {
        self$`profit_loss` <- OrderObject$`profit_loss`
      }
      if (!is.null(OrderObject$`is_liquidation`)) {
        self$`is_liquidation` <- OrderObject$`is_liquidation`
      }
      if (!is.null(OrderObject$`filled_amount`)) {
        self$`filled_amount` <- OrderObject$`filled_amount`
      }
      if (!is.null(OrderObject$`label`)) {
        self$`label` <- OrderObject$`label`
      }
      if (!is.null(OrderObject$`commission`)) {
        self$`commission` <- OrderObject$`commission`
      }
      if (!is.null(OrderObject$`amount`)) {
        self$`amount` <- OrderObject$`amount`
      }
      if (!is.null(OrderObject$`trigger`)) {
        self$`trigger` <- OrderObject$`trigger`
      }
      if (!is.null(OrderObject$`instrument_name`)) {
        self$`instrument_name` <- OrderObject$`instrument_name`
      }
      if (!is.null(OrderObject$`creation_timestamp`)) {
        self$`creation_timestamp` <- OrderObject$`creation_timestamp`
      }
      if (!is.null(OrderObject$`average_price`)) {
        self$`average_price` <- OrderObject$`average_price`
      }
    },
    toJSONString = function() {
      sprintf(
        '{
           "direction":
             "%s",
           "reduce_only":
             "%s",
           "triggered":
             "%s",
           "order_id":
             "%s",
           "price":
             %d,
           "time_in_force":
             "%s",
           "api":
             "%s",
           "order_state":
             "%s",
           "implv":
             %d,
           "advanced":
             "%s",
           "post_only":
             "%s",
           "usd":
             %d,
           "stop_price":
             %d,
           "order_type":
             "%s",
           "last_update_timestamp":
             %d,
           "original_order_type":
             "%s",
           "max_show":
             %d,
           "profit_loss":
             %d,
           "is_liquidation":
             "%s",
           "filled_amount":
             %d,
           "label":
             "%s",
           "commission":
             %d,
           "amount":
             %d,
           "trigger":
             "%s",
           "instrument_name":
             "%s",
           "creation_timestamp":
             %d,
           "average_price":
             %d
        }',
        self$`direction`,
        self$`reduce_only`,
        self$`triggered`,
        self$`order_id`,
        self$`price`,
        self$`time_in_force`,
        self$`api`,
        self$`order_state`,
        self$`implv`,
        self$`advanced`,
        self$`post_only`,
        self$`usd`,
        self$`stop_price`,
        self$`order_type`,
        self$`last_update_timestamp`,
        self$`original_order_type`,
        self$`max_show`,
        self$`profit_loss`,
        self$`is_liquidation`,
        self$`filled_amount`,
        self$`label`,
        self$`commission`,
        self$`amount`,
        self$`trigger`,
        self$`instrument_name`,
        self$`creation_timestamp`,
        self$`average_price`
      )
    },
    fromJSONString = function(OrderJson) {
      OrderObject <- jsonlite::fromJSON(OrderJson)
      self$`direction` <- OrderObject$`direction`
      self$`reduce_only` <- OrderObject$`reduce_only`
      self$`triggered` <- OrderObject$`triggered`
      self$`order_id` <- OrderObject$`order_id`
      self$`price` <- OrderObject$`price`
      self$`time_in_force` <- OrderObject$`time_in_force`
      self$`api` <- OrderObject$`api`
      self$`order_state` <- OrderObject$`order_state`
      self$`implv` <- OrderObject$`implv`
      self$`advanced` <- OrderObject$`advanced`
      self$`post_only` <- OrderObject$`post_only`
      self$`usd` <- OrderObject$`usd`
      self$`stop_price` <- OrderObject$`stop_price`
      self$`order_type` <- OrderObject$`order_type`
      self$`last_update_timestamp` <- OrderObject$`last_update_timestamp`
      self$`original_order_type` <- OrderObject$`original_order_type`
      self$`max_show` <- OrderObject$`max_show`
      self$`profit_loss` <- OrderObject$`profit_loss`
      self$`is_liquidation` <- OrderObject$`is_liquidation`
      self$`filled_amount` <- OrderObject$`filled_amount`
      self$`label` <- OrderObject$`label`
      self$`commission` <- OrderObject$`commission`
      self$`amount` <- OrderObject$`amount`
      self$`trigger` <- OrderObject$`trigger`
      self$`instrument_name` <- OrderObject$`instrument_name`
      self$`creation_timestamp` <- OrderObject$`creation_timestamp`
      self$`average_price` <- OrderObject$`average_price`
      self
    }
  )
)
