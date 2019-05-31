# deribit_api

DeribitApi - JavaScript client for deribit_api
#Overview  Deribit provides three different interfaces to access the API:  * [JSON-RPC over Websocket](#json-rpc) * [JSON-RPC over HTTP](#json-rpc) * [FIX](#fix-api) (Financial Information eXchange)  With the API Console you can use and test the JSON-RPC API, both via HTTP and  via Websocket. To visit the API console, go to __Account > API tab >  API Console tab.__   ##Naming Deribit tradeable assets or instruments use the following system of naming:  |Kind|Examples|Template|Comments| |----|--------|--------|--------| |Future|<code>BTC-25MAR16</code>, <code>BTC-5AUG16</code>|<code>BTC-DMMMYY</code>|<code>BTC</code> is currency, <code>DMMMYY</code> is expiration date, <code>D</code> stands for day of month (1 or 2 digits), <code>MMM</code> - month (3 first letters in English), <code>YY</code> stands for year.| |Perpetual|<code>BTC-PERPETUAL</code>                        ||Perpetual contract for currency <code>BTC</code>.| |Option|<code>BTC-25MAR16-420-C</code>, <code>BTC-5AUG16-580-P</code>|<code>BTC-DMMMYY-STRIKE-K</code>|<code>STRIKE</code> is option strike price in USD. Template <code>K</code> is option kind: <code>C</code> for call options or <code>P</code> for put options.|   # JSON-RPC JSON-RPC is a light-weight remote procedure call (RPC) protocol. The  [JSON-RPC specification](https://www.jsonrpc.org/specification) defines the data structures that are used for the messages that are exchanged between client and server, as well as the rules around their processing. JSON-RPC uses JSON (RFC 4627) as data format.  JSON-RPC is transport agnostic: it does not specify which transport mechanism must be used. The Deribit API supports both Websocket (preferred) and HTTP (with limitations: subscriptions are not supported over HTTP).  ## Request messages > An example of a request message:  ```json {     \"jsonrpc\": \"2.0\",     \"id\": 8066,     \"method\": \"public/ticker\",     \"params\": {         \"instrument\": \"BTC-24AUG18-6500-P\"     } } ```  According to the JSON-RPC sepcification the requests must be JSON objects with the following fields.  |Name|Type|Description| |----|----|-----------| |jsonrpc|string|The version of the JSON-RPC spec: \"2.0\"| |id|integer or string|An identifier of the request. If it is included, then the response will contain the same identifier| |method|string|The method to be invoked| |params|object|The parameters values for the method. The field names must match with the expected parameter names. The parameters that are expected are described in the documentation for the methods, below.|  <aside class=\"warning\"> The JSON-RPC specification describes two features that are currently not supported by the API:  <ul> <li>Specification of parameter values by position</li> <li>Batch requests</li> </ul>  </aside>   ## Response messages > An example of a response message:  ```json {     \"jsonrpc\": \"2.0\",     \"id\": 5239,     \"testnet\": false,     \"result\": [         {             \"currency\": \"BTC\",             \"currencyLong\": \"Bitcoin\",             \"minConfirmation\": 2,             \"txFee\": 0.0006,             \"isActive\": true,             \"coinType\": \"BITCOIN\",             \"baseAddress\": null         }     ],     \"usIn\": 1535043730126248,     \"usOut\": 1535043730126250,     \"usDiff\": 2 } ```  The JSON-RPC API always responds with a JSON object with the following fields.   |Name|Type|Description| |----|----|-----------| |id|integer|This is the same id that was sent in the request.| |result|any|If successful, the result of the API call. The format for the result is described with each method.| |error|error object|Only present if there was an error invoking the method. The error object is described below.| |testnet|boolean|Indicates whether the API in use is actually the test API.  <code>false</code> for production server, <code>true</code> for test server.| |usIn|integer|The timestamp when the requests was received (microseconds since the Unix epoch)| |usOut|integer|The timestamp when the response was sent (microseconds since the Unix epoch)| |usDiff|integer|The number of microseconds that was spent handling the request|  <aside class=\"notice\"> The fields <code>testnet</code>, <code>usIn</code>, <code>usOut</code> and <code>usDiff</code> are not part of the JSON-RPC standard.  <p>In order not to clutter the examples they will generally be omitted from the example code.</p> </aside>  > An example of a response with an error:  ```json {     \"jsonrpc\": \"2.0\",     \"id\": 8163,     \"error\": {         \"code\": 11050,         \"message\": \"bad_request\"     },     \"testnet\": false,     \"usIn\": 1535037392434763,     \"usOut\": 1535037392448119,     \"usDiff\": 13356 } ``` In case of an error the response message will contain the error field, with as value an object with the following with the following fields:  |Name|Type|Description |----|----|-----------| |code|integer|A number that indicates the kind of error.| |message|string|A short description that indicates the kind of error.| |data|any|Additional data about the error. This field may be omitted.|  ## Notifications  > An example of a notification:  ```json {     \"jsonrpc\": \"2.0\",     \"method\": \"subscription\",     \"params\": {         \"channel\": \"deribit_price_index.btc_usd\",         \"data\": {             \"timestamp\": 1535098298227,             \"price\": 6521.17,             \"index_name\": \"btc_usd\"         }     } } ```  API users can subscribe to certain types of notifications. This means that they will receive JSON-RPC notification-messages from the server when certain events occur, such as changes to the index price or changes to the order book for a certain instrument.   The API methods [public/subscribe](#public-subscribe) and [private/subscribe](#private-subscribe) are used to set up a subscription. Since HTTP does not support the sending of messages from server to client, these methods are only availble when using the Websocket transport mechanism.  At the moment of subscription a \"channel\" must be specified. The channel determines the type of events that will be received.  See [Subscriptions](#subscriptions) for more details about the channels.  In accordance with the JSON-RPC specification, the format of a notification  is that of a request message without an <code>id</code> field. The value of the <code>method</code> field will always be <code>\"subscription\"</code>. The <code>params</code> field will always be an object with 2 members: <code>channel</code> and <code>data</code>. The value of the <code>channel</code> member is the name of the channel (a string). The value of the <code>data</code> member is an object that contains data  that is specific for the channel.   ## Authentication  > An example of a JSON request with token:  ```json {     \"id\": 5647,     \"method\": \"private/get_subaccounts\",     \"params\": {         \"access_token\": \"67SVutDoVZSzkUStHSuk51WntMNBJ5mh5DYZhwzpiqDF\"     } } ```  The API consists of `public` and `private` methods. The public methods do not require authentication. The private methods use OAuth 2.0 authentication. This means that a valid OAuth access token must be included in the request, which can get achived by calling method [public/auth](#public-auth).  When the token was assigned to the user, it should be passed along, with other request parameters, back to the server:  |Connection type|Access token placement |----|-----------| |**Websocket**|Inside request JSON parameters, as an `access_token` field| |**HTTP (REST)**|Header `Authorization: bearer ```Token``` ` value|  ### Additional authorization method - basic user credentials  <span style=\"color:red\"><b> ! Not recommended - however, it could be useful for quick testing API</b></span></br>  Every `private` method could be accessed by providing, inside HTTP `Authorization: Basic XXX` header, values with user `ClientId` and assigned `ClientSecret` (both values can be found on the API page on the Deribit website) encoded with `Base64`:  <code>Authorization: Basic BASE64(`ClientId` + `:` + `ClientSecret`)</code>   ### Additional authorization method - Deribit signature credentials  The Derbit service provides dedicated authorization method, which harness user generated signature to increase security level for passing request data. Generated value is passed inside `Authorization` header, coded as:  <code>Authorization: deri-hmac-sha256 id=```ClientId```,ts=```Timestamp```,sig=```Signature```,nonce=```Nonce```</code>  where:  |Deribit credential|Description |----|-----------| |*ClientId*|Can be found on the API page on the Deribit website| |*Timestamp*|Time when the request was generated - given as **miliseconds**. It's valid for **60 seconds** since generation, after that time any request with an old timestamp will be rejected.| |*Signature*|Value for signature calculated as described below | |*Nonce*|Single usage, user generated initialization vector for the server token|  The signature is generated by the following formula:  <code> Signature = HEX_STRING( HMAC-SHA256( ClientSecret, StringToSign ) );</code></br>  <code> StringToSign =  Timestamp + \"\\n\" + Nonce + \"\\n\" + RequestData;</code></br>  <code> RequestData =  UPPERCASE(HTTP_METHOD())  + \"\\n\" + URI() + \"\\n\" + RequestBody + \"\\n\";</code></br>   e.g. (using shell with ```openssl``` tool):  <code>&nbsp;&nbsp;&nbsp;&nbsp;ClientId=AAAAAAAAAAA</code></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;ClientSecret=ABCD</code></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;Timestamp=$( date +%s000 )</code></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;Nonce=$( cat /dev/urandom | tr -dc 'a-z0-9' | head -c8 )</code></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;URI=\"/api/v2/private/get_account_summary?currency=BTC\"</code></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;HttpMethod=GET</code></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;Body=\"\"</code></br></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;Signature=$( echo -ne \"${Timestamp}\\n${Nonce}\\n${HttpMethod}\\n${URI}\\n${Body}\\n\" | openssl sha256 -r -hmac \"$ClientSecret\" | cut -f1 -d' ' )</code></br></br> <code>&nbsp;&nbsp;&nbsp;&nbsp;echo $Signature</code></br></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;shell output> ea40d5e5e4fae235ab22b61da98121fbf4acdc06db03d632e23c66bcccb90d2c  (**WARNING**: Exact value depends on current timestamp and client credentials</code></br></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;curl -s -X ${HttpMethod} -H \"Authorization: deri-hmac-sha256 id=${ClientId},ts=${Timestamp},nonce=${Nonce},sig=${Signature}\" \"https://www.deribit.com${URI}\"</code></br></br>    ### Additional authorization method - signature credentials (WebSocket API)  When connecting through Websocket, user can request for authorization using ```client_credential``` method, which requires providing following parameters (as a part of JSON request):  |JSON parameter|Description |----|-----------| |*grant_type*|Must be **client_signature**| |*client_id*|Can be found on the API page on the Deribit website| |*timestamp*|Time when the request was generated - given as **miliseconds**. It's valid for **60 seconds** since generation, after that time any request with an old timestamp will be rejected.| |*signature*|Value for signature calculated as described below | |*nonce*|Single usage, user generated initialization vector for the server token| |*data*|**Optional** field, which contains any user specific value|  The signature is generated by the following formula:  <code> StringToSign =  Timestamp + \"\\n\" + Nonce + \"\\n\" + Data;</code></br>  <code> Signature = HEX_STRING( HMAC-SHA256( ClientSecret, StringToSign ) );</code></br>   e.g. (using shell with ```openssl``` tool):  <code>&nbsp;&nbsp;&nbsp;&nbsp;ClientId=AAAAAAAAAAA</code></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;ClientSecret=ABCD</code></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;Timestamp=$( date +%s000 ) # e.g. 1554883365000 </code></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;Nonce=$( cat /dev/urandom | tr -dc 'a-z0-9' | head -c8 ) # e.g. fdbmmz79 </code></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;Data=\"\"</code></br></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;Signature=$( echo -ne \"${Timestamp}\\n${Nonce}\\n${Data}\\n\" | openssl sha256 -r -hmac \"$ClientSecret\" | cut -f1 -d' ' )</code></br></br> <code>&nbsp;&nbsp;&nbsp;&nbsp;echo $Signature</code></br></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;shell output> e20c9cd5639d41f8bbc88f4d699c4baf94a4f0ee320e9a116b72743c449eb994  (**WARNING**: Exact value depends on current timestamp and client credentials</code></br></br>   You can also check the signature value using some online tools like, e.g: [https://codebeautify.org/hmac-generator](https://codebeautify.org/hmac-generator) (but don't forget about adding *newline* after each part of the hashed text and remember that you **should use** it only with your **test credentials**).   Here's a sample JSON request created using the values from the example above:  <code> {                            </br> &nbsp;&nbsp;\"jsonrpc\" : \"2.0\",         </br> &nbsp;&nbsp;\"id\" : 9929,               </br> &nbsp;&nbsp;\"method\" : \"public/auth\",  </br> &nbsp;&nbsp;\"params\" :                 </br> &nbsp;&nbsp;{                        </br> &nbsp;&nbsp;&nbsp;&nbsp;\"grant_type\" : \"client_signature\",   </br> &nbsp;&nbsp;&nbsp;&nbsp;\"client_id\" : \"AAAAAAAAAAA\",         </br> &nbsp;&nbsp;&nbsp;&nbsp;\"timestamp\": \"1554883365000\",        </br> &nbsp;&nbsp;&nbsp;&nbsp;\"nonce\": \"fdbmmz79\",                 </br> &nbsp;&nbsp;&nbsp;&nbsp;\"data\": \"\",                          </br> &nbsp;&nbsp;&nbsp;&nbsp;\"signature\" : \"e20c9cd5639d41f8bbc88f4d699c4baf94a4f0ee320e9a116b72743c449eb994\"  </br> &nbsp;&nbsp;}                        </br> }                            </br> </code>   ### Access scope  When asking for `access token` user can provide the required access level (called `scope`) which defines what type of functionality he/she wants to use, and whether requests are only going to check for some data or also to update them.  Scopes are required and checked for `private` methods, so if you plan to use only `public` information you can stay with values assigned by default.  |Scope|Description |----|-----------| |*account:read*|Access to **account** methods - read only data| |*account:read_write*|Access to **account** methods - allows to manage account settings, add subaccounts, etc.| |*trade:read*|Access to **trade** methods - read only data| |*trade:read_write*|Access to **trade** methods - required to create and modify orders| |*wallet:read*|Access to **wallet** methods - read only data| |*wallet:read_write*|Access to **wallet** methods - allows to withdraw, generate new deposit address, etc.| |*wallet:none*, *account:none*, *trade:none*|Blocked access to specified functionality|    <span style=\"color:red\">**NOTICE:**</span> Depending on choosing an authentication method (```grant type```) some scopes could be narrowed by the server. e.g. when ```grant_type = client_credentials``` and ```scope = wallet:read_write``` it's modified by the server as ```scope = wallet:read```\"   ## JSON-RPC over websocket Websocket is the prefered transport mechanism for the JSON-RPC API, because it is faster and because it can support [subscriptions](#subscriptions) and [cancel on disconnect](#private-enable_cancel_on_disconnect). The code examples that can be found next to each of the methods show how websockets can be used from Python or Javascript/node.js.  ## JSON-RPC over HTTP Besides websockets it is also possible to use the API via HTTP. The code examples for 'shell' show how this can be done using curl. Note that subscriptions and cancel on disconnect are not supported via HTTP.  #Methods 
This SDK is automatically generated by the [OpenAPI Generator](https://openapi-generator.tech) project:

- API version: 2.0.0
- Package version: 2.0.0
- Build package: org.openapitools.codegen.languages.JavascriptClientCodegen

## Installation

### For [Node.js](https://nodejs.org/)

#### npm

To publish the library as a [npm](https://www.npmjs.com/), please follow the procedure in ["Publishing npm packages"](https://docs.npmjs.com/getting-started/publishing-npm-packages).

Then install it via:

```shell
npm install deribit_api --save
```

Finaly, you need to build the module:

```shell
npm run build
```

##### Local development

To use the library locally without publishing to a remote npm registry, first install the dependencies by changing into the directory containing `package.json` (and this README). Let's call this `JAVASCRIPT_CLIENT_DIR`. Then run:

```shell
npm install
```

Next, [link](https://docs.npmjs.com/cli/link) it globally in npm with the following, also from `JAVASCRIPT_CLIENT_DIR`:

```shell
npm link
```

To use the link you just defined in your project, switch to the directory you want to use your deribit_api from, and run:

```shell
npm link /path/to/<JAVASCRIPT_CLIENT_DIR>
```

Finaly, you need to build the module:

```shell
npm run build
```

#### git

If the library is hosted at a git repository, e.g.https://github.com/GIT_USER_ID/GIT_REPO_ID
then install it via:

```shell
    npm install GIT_USER_ID/GIT_REPO_ID --save
```

### For browser

The library also works in the browser environment via npm and [browserify](http://browserify.org/). After following
the above steps with Node.js and installing browserify with `npm install -g browserify`,
perform the following (assuming *main.js* is your entry file):

```shell
browserify main.js > bundle.js
```

Then include *bundle.js* in the HTML pages.

### Webpack Configuration

Using Webpack you may encounter the following error: "Module not found: Error:
Cannot resolve module", most certainly you should disable AMD loader. Add/merge
the following section to your webpack config:

```javascript
module: {
  rules: [
    {
      parser: {
        amd: false
      }
    }
  ]
}
```

## Getting Started

Please follow the [installation](#installation) instruction and execute the following JS code:

```javascript
var DeribitApi = require('deribit_api');

var defaultClient = DeribitApi.ApiClient.instance;
// Configure Bearer (Auth. Token) access token for authorization: bearerAuth
var bearerAuth = defaultClient.authentications['bearerAuth'];
bearerAuth.accessToken = "YOUR ACCESS TOKEN"

var api = new DeribitApi.AccountManagementApi()
var sid = 56; // {Number} The user id for the subaccount
var name = newUserName; // {String} The new user name
var callback = function(error, data, response) {
  if (error) {
    console.error(error);
  } else {
    console.log('API called successfully. Returned data: ' + data);
  }
};
api.privateChangeSubaccountNameGet(sid, name, callback);

```

## Documentation for API Endpoints

All URIs are relative to *https://www.deribit.com/api/v2*

Class | Method | HTTP request | Description
------------ | ------------- | ------------- | -------------
*DeribitApi.AccountManagementApi* | [**privateChangeSubaccountNameGet**](docs/AccountManagementApi.md#privateChangeSubaccountNameGet) | **GET** /private/change_subaccount_name | Change the user name for a subaccount
*DeribitApi.AccountManagementApi* | [**privateCreateSubaccountGet**](docs/AccountManagementApi.md#privateCreateSubaccountGet) | **GET** /private/create_subaccount | Create a new subaccount
*DeribitApi.AccountManagementApi* | [**privateDisableTfaForSubaccountGet**](docs/AccountManagementApi.md#privateDisableTfaForSubaccountGet) | **GET** /private/disable_tfa_for_subaccount | Disable two factor authentication for a subaccount.
*DeribitApi.AccountManagementApi* | [**privateGetAccountSummaryGet**](docs/AccountManagementApi.md#privateGetAccountSummaryGet) | **GET** /private/get_account_summary | Retrieves user account summary.
*DeribitApi.AccountManagementApi* | [**privateGetEmailLanguageGet**](docs/AccountManagementApi.md#privateGetEmailLanguageGet) | **GET** /private/get_email_language | Retrieves the language to be used for emails.
*DeribitApi.AccountManagementApi* | [**privateGetNewAnnouncementsGet**](docs/AccountManagementApi.md#privateGetNewAnnouncementsGet) | **GET** /private/get_new_announcements | Retrieves announcements that have not been marked read by the user.
*DeribitApi.AccountManagementApi* | [**privateGetPositionGet**](docs/AccountManagementApi.md#privateGetPositionGet) | **GET** /private/get_position | Retrieve user position.
*DeribitApi.AccountManagementApi* | [**privateGetPositionsGet**](docs/AccountManagementApi.md#privateGetPositionsGet) | **GET** /private/get_positions | Retrieve user positions.
*DeribitApi.AccountManagementApi* | [**privateGetSubaccountsGet**](docs/AccountManagementApi.md#privateGetSubaccountsGet) | **GET** /private/get_subaccounts | Get information about subaccounts
*DeribitApi.AccountManagementApi* | [**privateSetAnnouncementAsReadGet**](docs/AccountManagementApi.md#privateSetAnnouncementAsReadGet) | **GET** /private/set_announcement_as_read | Marks an announcement as read, so it will not be shown in &#x60;get_new_announcements&#x60;.
*DeribitApi.AccountManagementApi* | [**privateSetEmailForSubaccountGet**](docs/AccountManagementApi.md#privateSetEmailForSubaccountGet) | **GET** /private/set_email_for_subaccount | Assign an email address to a subaccount. User will receive an email with confirmation link.
*DeribitApi.AccountManagementApi* | [**privateSetEmailLanguageGet**](docs/AccountManagementApi.md#privateSetEmailLanguageGet) | **GET** /private/set_email_language | Changes the language to be used for emails.
*DeribitApi.AccountManagementApi* | [**privateSetPasswordForSubaccountGet**](docs/AccountManagementApi.md#privateSetPasswordForSubaccountGet) | **GET** /private/set_password_for_subaccount | Set the password for the subaccount
*DeribitApi.AccountManagementApi* | [**privateToggleNotificationsFromSubaccountGet**](docs/AccountManagementApi.md#privateToggleNotificationsFromSubaccountGet) | **GET** /private/toggle_notifications_from_subaccount | Enable or disable sending of notifications for the subaccount.
*DeribitApi.AccountManagementApi* | [**privateToggleSubaccountLoginGet**](docs/AccountManagementApi.md#privateToggleSubaccountLoginGet) | **GET** /private/toggle_subaccount_login | Enable or disable login for a subaccount. If login is disabled and a session for the subaccount exists, this session will be terminated.
*DeribitApi.AccountManagementApi* | [**publicGetAnnouncementsGet**](docs/AccountManagementApi.md#publicGetAnnouncementsGet) | **GET** /public/get_announcements | Retrieves announcements from the last 30 days.
*DeribitApi.AuthenticationApi* | [**publicAuthGet**](docs/AuthenticationApi.md#publicAuthGet) | **GET** /public/auth | Authenticate
*DeribitApi.InternalApi* | [**privateAddToAddressBookGet**](docs/InternalApi.md#privateAddToAddressBookGet) | **GET** /private/add_to_address_book | Adds new entry to address book of given type
*DeribitApi.InternalApi* | [**privateDisableTfaWithRecoveryCodeGet**](docs/InternalApi.md#privateDisableTfaWithRecoveryCodeGet) | **GET** /private/disable_tfa_with_recovery_code | Disables TFA with one time recovery code
*DeribitApi.InternalApi* | [**privateGetAddressBookGet**](docs/InternalApi.md#privateGetAddressBookGet) | **GET** /private/get_address_book | Retrieves address book of given type
*DeribitApi.InternalApi* | [**privateRemoveFromAddressBookGet**](docs/InternalApi.md#privateRemoveFromAddressBookGet) | **GET** /private/remove_from_address_book | Adds new entry to address book of given type
*DeribitApi.InternalApi* | [**privateSubmitTransferToSubaccountGet**](docs/InternalApi.md#privateSubmitTransferToSubaccountGet) | **GET** /private/submit_transfer_to_subaccount | Transfer funds to a subaccount.
*DeribitApi.InternalApi* | [**privateSubmitTransferToUserGet**](docs/InternalApi.md#privateSubmitTransferToUserGet) | **GET** /private/submit_transfer_to_user | Transfer funds to a another user.
*DeribitApi.InternalApi* | [**privateToggleDepositAddressCreationGet**](docs/InternalApi.md#privateToggleDepositAddressCreationGet) | **GET** /private/toggle_deposit_address_creation | Enable or disable deposit address creation
*DeribitApi.InternalApi* | [**publicGetFooterGet**](docs/InternalApi.md#publicGetFooterGet) | **GET** /public/get_footer | Get information to be displayed in the footer of the website.
*DeribitApi.InternalApi* | [**publicGetOptionMarkPricesGet**](docs/InternalApi.md#publicGetOptionMarkPricesGet) | **GET** /public/get_option_mark_prices | Retrives market prices and its implied volatility of options instruments
*DeribitApi.InternalApi* | [**publicValidateFieldGet**](docs/InternalApi.md#publicValidateFieldGet) | **GET** /public/validate_field | Method used to introduce the client software connected to Deribit platform over websocket. Provided data may have an impact on the maintained connection and will be collected for internal statistical purposes. In response, Deribit will also introduce itself.
*DeribitApi.MarketDataApi* | [**publicGetBookSummaryByCurrencyGet**](docs/MarketDataApi.md#publicGetBookSummaryByCurrencyGet) | **GET** /public/get_book_summary_by_currency | Retrieves the summary information such as open interest, 24h volume, etc. for all instruments for the currency (optionally filtered by kind).
*DeribitApi.MarketDataApi* | [**publicGetBookSummaryByInstrumentGet**](docs/MarketDataApi.md#publicGetBookSummaryByInstrumentGet) | **GET** /public/get_book_summary_by_instrument | Retrieves the summary information such as open interest, 24h volume, etc. for a specific instrument.
*DeribitApi.MarketDataApi* | [**publicGetContractSizeGet**](docs/MarketDataApi.md#publicGetContractSizeGet) | **GET** /public/get_contract_size | Retrieves contract size of provided instrument.
*DeribitApi.MarketDataApi* | [**publicGetCurrenciesGet**](docs/MarketDataApi.md#publicGetCurrenciesGet) | **GET** /public/get_currencies | Retrieves all cryptocurrencies supported by the API.
*DeribitApi.MarketDataApi* | [**publicGetFundingChartDataGet**](docs/MarketDataApi.md#publicGetFundingChartDataGet) | **GET** /public/get_funding_chart_data | Retrieve the latest user trades that have occurred for PERPETUAL instruments in a specific currency symbol and within given time range.
*DeribitApi.MarketDataApi* | [**publicGetHistoricalVolatilityGet**](docs/MarketDataApi.md#publicGetHistoricalVolatilityGet) | **GET** /public/get_historical_volatility | Provides information about historical volatility for given cryptocurrency.
*DeribitApi.MarketDataApi* | [**publicGetIndexGet**](docs/MarketDataApi.md#publicGetIndexGet) | **GET** /public/get_index | Retrieves the current index price for the instruments, for the selected currency.
*DeribitApi.MarketDataApi* | [**publicGetInstrumentsGet**](docs/MarketDataApi.md#publicGetInstrumentsGet) | **GET** /public/get_instruments | Retrieves available trading instruments. This method can be used to see which instruments are available for trading, or which instruments have existed historically.
*DeribitApi.MarketDataApi* | [**publicGetLastSettlementsByCurrencyGet**](docs/MarketDataApi.md#publicGetLastSettlementsByCurrencyGet) | **GET** /public/get_last_settlements_by_currency | Retrieves historical settlement, delivery and bankruptcy events coming from all instruments within given currency.
*DeribitApi.MarketDataApi* | [**publicGetLastSettlementsByInstrumentGet**](docs/MarketDataApi.md#publicGetLastSettlementsByInstrumentGet) | **GET** /public/get_last_settlements_by_instrument | Retrieves historical public settlement, delivery and bankruptcy events filtered by instrument name.
*DeribitApi.MarketDataApi* | [**publicGetLastTradesByCurrencyAndTimeGet**](docs/MarketDataApi.md#publicGetLastTradesByCurrencyAndTimeGet) | **GET** /public/get_last_trades_by_currency_and_time | Retrieve the latest trades that have occurred for instruments in a specific currency symbol and within given time range.
*DeribitApi.MarketDataApi* | [**publicGetLastTradesByCurrencyGet**](docs/MarketDataApi.md#publicGetLastTradesByCurrencyGet) | **GET** /public/get_last_trades_by_currency | Retrieve the latest trades that have occurred for instruments in a specific currency symbol.
*DeribitApi.MarketDataApi* | [**publicGetLastTradesByInstrumentAndTimeGet**](docs/MarketDataApi.md#publicGetLastTradesByInstrumentAndTimeGet) | **GET** /public/get_last_trades_by_instrument_and_time | Retrieve the latest trades that have occurred for a specific instrument and within given time range.
*DeribitApi.MarketDataApi* | [**publicGetLastTradesByInstrumentGet**](docs/MarketDataApi.md#publicGetLastTradesByInstrumentGet) | **GET** /public/get_last_trades_by_instrument | Retrieve the latest trades that have occurred for a specific instrument.
*DeribitApi.MarketDataApi* | [**publicGetOrderBookGet**](docs/MarketDataApi.md#publicGetOrderBookGet) | **GET** /public/get_order_book | Retrieves the order book, along with other market values for a given instrument.
*DeribitApi.MarketDataApi* | [**publicGetTradeVolumesGet**](docs/MarketDataApi.md#publicGetTradeVolumesGet) | **GET** /public/get_trade_volumes | Retrieves aggregated 24h trade volumes for different instrument types and currencies.
*DeribitApi.MarketDataApi* | [**publicGetTradingviewChartDataGet**](docs/MarketDataApi.md#publicGetTradingviewChartDataGet) | **GET** /public/get_tradingview_chart_data | Publicly available market data used to generate a TradingView candle chart.
*DeribitApi.MarketDataApi* | [**publicTickerGet**](docs/MarketDataApi.md#publicTickerGet) | **GET** /public/ticker | Get ticker for an instrument.
*DeribitApi.PrivateApi* | [**privateAddToAddressBookGet**](docs/PrivateApi.md#privateAddToAddressBookGet) | **GET** /private/add_to_address_book | Adds new entry to address book of given type
*DeribitApi.PrivateApi* | [**privateBuyGet**](docs/PrivateApi.md#privateBuyGet) | **GET** /private/buy | Places a buy order for an instrument.
*DeribitApi.PrivateApi* | [**privateCancelAllByCurrencyGet**](docs/PrivateApi.md#privateCancelAllByCurrencyGet) | **GET** /private/cancel_all_by_currency | Cancels all orders by currency, optionally filtered by instrument kind and/or order type.
*DeribitApi.PrivateApi* | [**privateCancelAllByInstrumentGet**](docs/PrivateApi.md#privateCancelAllByInstrumentGet) | **GET** /private/cancel_all_by_instrument | Cancels all orders by instrument, optionally filtered by order type.
*DeribitApi.PrivateApi* | [**privateCancelAllGet**](docs/PrivateApi.md#privateCancelAllGet) | **GET** /private/cancel_all | This method cancels all users orders and stop orders within all currencies and instrument kinds.
*DeribitApi.PrivateApi* | [**privateCancelGet**](docs/PrivateApi.md#privateCancelGet) | **GET** /private/cancel | Cancel an order, specified by order id
*DeribitApi.PrivateApi* | [**privateCancelTransferByIdGet**](docs/PrivateApi.md#privateCancelTransferByIdGet) | **GET** /private/cancel_transfer_by_id | Cancel transfer
*DeribitApi.PrivateApi* | [**privateCancelWithdrawalGet**](docs/PrivateApi.md#privateCancelWithdrawalGet) | **GET** /private/cancel_withdrawal | Cancels withdrawal request
*DeribitApi.PrivateApi* | [**privateChangeSubaccountNameGet**](docs/PrivateApi.md#privateChangeSubaccountNameGet) | **GET** /private/change_subaccount_name | Change the user name for a subaccount
*DeribitApi.PrivateApi* | [**privateClosePositionGet**](docs/PrivateApi.md#privateClosePositionGet) | **GET** /private/close_position | Makes closing position reduce only order .
*DeribitApi.PrivateApi* | [**privateCreateDepositAddressGet**](docs/PrivateApi.md#privateCreateDepositAddressGet) | **GET** /private/create_deposit_address | Creates deposit address in currency
*DeribitApi.PrivateApi* | [**privateCreateSubaccountGet**](docs/PrivateApi.md#privateCreateSubaccountGet) | **GET** /private/create_subaccount | Create a new subaccount
*DeribitApi.PrivateApi* | [**privateDisableTfaForSubaccountGet**](docs/PrivateApi.md#privateDisableTfaForSubaccountGet) | **GET** /private/disable_tfa_for_subaccount | Disable two factor authentication for a subaccount.
*DeribitApi.PrivateApi* | [**privateDisableTfaWithRecoveryCodeGet**](docs/PrivateApi.md#privateDisableTfaWithRecoveryCodeGet) | **GET** /private/disable_tfa_with_recovery_code | Disables TFA with one time recovery code
*DeribitApi.PrivateApi* | [**privateEditGet**](docs/PrivateApi.md#privateEditGet) | **GET** /private/edit | Change price, amount and/or other properties of an order.
*DeribitApi.PrivateApi* | [**privateGetAccountSummaryGet**](docs/PrivateApi.md#privateGetAccountSummaryGet) | **GET** /private/get_account_summary | Retrieves user account summary.
*DeribitApi.PrivateApi* | [**privateGetAddressBookGet**](docs/PrivateApi.md#privateGetAddressBookGet) | **GET** /private/get_address_book | Retrieves address book of given type
*DeribitApi.PrivateApi* | [**privateGetCurrentDepositAddressGet**](docs/PrivateApi.md#privateGetCurrentDepositAddressGet) | **GET** /private/get_current_deposit_address | Retrieve deposit address for currency
*DeribitApi.PrivateApi* | [**privateGetDepositsGet**](docs/PrivateApi.md#privateGetDepositsGet) | **GET** /private/get_deposits | Retrieve the latest users deposits
*DeribitApi.PrivateApi* | [**privateGetEmailLanguageGet**](docs/PrivateApi.md#privateGetEmailLanguageGet) | **GET** /private/get_email_language | Retrieves the language to be used for emails.
*DeribitApi.PrivateApi* | [**privateGetMarginsGet**](docs/PrivateApi.md#privateGetMarginsGet) | **GET** /private/get_margins | Get margins for given instrument, amount and price.
*DeribitApi.PrivateApi* | [**privateGetNewAnnouncementsGet**](docs/PrivateApi.md#privateGetNewAnnouncementsGet) | **GET** /private/get_new_announcements | Retrieves announcements that have not been marked read by the user.
*DeribitApi.PrivateApi* | [**privateGetOpenOrdersByCurrencyGet**](docs/PrivateApi.md#privateGetOpenOrdersByCurrencyGet) | **GET** /private/get_open_orders_by_currency | Retrieves list of user&#39;s open orders.
*DeribitApi.PrivateApi* | [**privateGetOpenOrdersByInstrumentGet**](docs/PrivateApi.md#privateGetOpenOrdersByInstrumentGet) | **GET** /private/get_open_orders_by_instrument | Retrieves list of user&#39;s open orders within given Instrument.
*DeribitApi.PrivateApi* | [**privateGetOrderHistoryByCurrencyGet**](docs/PrivateApi.md#privateGetOrderHistoryByCurrencyGet) | **GET** /private/get_order_history_by_currency | Retrieves history of orders that have been partially or fully filled.
*DeribitApi.PrivateApi* | [**privateGetOrderHistoryByInstrumentGet**](docs/PrivateApi.md#privateGetOrderHistoryByInstrumentGet) | **GET** /private/get_order_history_by_instrument | Retrieves history of orders that have been partially or fully filled.
*DeribitApi.PrivateApi* | [**privateGetOrderMarginByIdsGet**](docs/PrivateApi.md#privateGetOrderMarginByIdsGet) | **GET** /private/get_order_margin_by_ids | Retrieves initial margins of given orders
*DeribitApi.PrivateApi* | [**privateGetOrderStateGet**](docs/PrivateApi.md#privateGetOrderStateGet) | **GET** /private/get_order_state | Retrieve the current state of an order.
*DeribitApi.PrivateApi* | [**privateGetPositionGet**](docs/PrivateApi.md#privateGetPositionGet) | **GET** /private/get_position | Retrieve user position.
*DeribitApi.PrivateApi* | [**privateGetPositionsGet**](docs/PrivateApi.md#privateGetPositionsGet) | **GET** /private/get_positions | Retrieve user positions.
*DeribitApi.PrivateApi* | [**privateGetSettlementHistoryByCurrencyGet**](docs/PrivateApi.md#privateGetSettlementHistoryByCurrencyGet) | **GET** /private/get_settlement_history_by_currency | Retrieves settlement, delivery and bankruptcy events that have affected your account.
*DeribitApi.PrivateApi* | [**privateGetSettlementHistoryByInstrumentGet**](docs/PrivateApi.md#privateGetSettlementHistoryByInstrumentGet) | **GET** /private/get_settlement_history_by_instrument | Retrieves public settlement, delivery and bankruptcy events filtered by instrument name
*DeribitApi.PrivateApi* | [**privateGetSubaccountsGet**](docs/PrivateApi.md#privateGetSubaccountsGet) | **GET** /private/get_subaccounts | Get information about subaccounts
*DeribitApi.PrivateApi* | [**privateGetTransfersGet**](docs/PrivateApi.md#privateGetTransfersGet) | **GET** /private/get_transfers | Adds new entry to address book of given type
*DeribitApi.PrivateApi* | [**privateGetUserTradesByCurrencyAndTimeGet**](docs/PrivateApi.md#privateGetUserTradesByCurrencyAndTimeGet) | **GET** /private/get_user_trades_by_currency_and_time | Retrieve the latest user trades that have occurred for instruments in a specific currency symbol and within given time range.
*DeribitApi.PrivateApi* | [**privateGetUserTradesByCurrencyGet**](docs/PrivateApi.md#privateGetUserTradesByCurrencyGet) | **GET** /private/get_user_trades_by_currency | Retrieve the latest user trades that have occurred for instruments in a specific currency symbol.
*DeribitApi.PrivateApi* | [**privateGetUserTradesByInstrumentAndTimeGet**](docs/PrivateApi.md#privateGetUserTradesByInstrumentAndTimeGet) | **GET** /private/get_user_trades_by_instrument_and_time | Retrieve the latest user trades that have occurred for a specific instrument and within given time range.
*DeribitApi.PrivateApi* | [**privateGetUserTradesByInstrumentGet**](docs/PrivateApi.md#privateGetUserTradesByInstrumentGet) | **GET** /private/get_user_trades_by_instrument | Retrieve the latest user trades that have occurred for a specific instrument.
*DeribitApi.PrivateApi* | [**privateGetUserTradesByOrderGet**](docs/PrivateApi.md#privateGetUserTradesByOrderGet) | **GET** /private/get_user_trades_by_order | Retrieve the list of user trades that was created for given order
*DeribitApi.PrivateApi* | [**privateGetWithdrawalsGet**](docs/PrivateApi.md#privateGetWithdrawalsGet) | **GET** /private/get_withdrawals | Retrieve the latest users withdrawals
*DeribitApi.PrivateApi* | [**privateRemoveFromAddressBookGet**](docs/PrivateApi.md#privateRemoveFromAddressBookGet) | **GET** /private/remove_from_address_book | Adds new entry to address book of given type
*DeribitApi.PrivateApi* | [**privateSellGet**](docs/PrivateApi.md#privateSellGet) | **GET** /private/sell | Places a sell order for an instrument.
*DeribitApi.PrivateApi* | [**privateSetAnnouncementAsReadGet**](docs/PrivateApi.md#privateSetAnnouncementAsReadGet) | **GET** /private/set_announcement_as_read | Marks an announcement as read, so it will not be shown in &#x60;get_new_announcements&#x60;.
*DeribitApi.PrivateApi* | [**privateSetEmailForSubaccountGet**](docs/PrivateApi.md#privateSetEmailForSubaccountGet) | **GET** /private/set_email_for_subaccount | Assign an email address to a subaccount. User will receive an email with confirmation link.
*DeribitApi.PrivateApi* | [**privateSetEmailLanguageGet**](docs/PrivateApi.md#privateSetEmailLanguageGet) | **GET** /private/set_email_language | Changes the language to be used for emails.
*DeribitApi.PrivateApi* | [**privateSetPasswordForSubaccountGet**](docs/PrivateApi.md#privateSetPasswordForSubaccountGet) | **GET** /private/set_password_for_subaccount | Set the password for the subaccount
*DeribitApi.PrivateApi* | [**privateSubmitTransferToSubaccountGet**](docs/PrivateApi.md#privateSubmitTransferToSubaccountGet) | **GET** /private/submit_transfer_to_subaccount | Transfer funds to a subaccount.
*DeribitApi.PrivateApi* | [**privateSubmitTransferToUserGet**](docs/PrivateApi.md#privateSubmitTransferToUserGet) | **GET** /private/submit_transfer_to_user | Transfer funds to a another user.
*DeribitApi.PrivateApi* | [**privateToggleDepositAddressCreationGet**](docs/PrivateApi.md#privateToggleDepositAddressCreationGet) | **GET** /private/toggle_deposit_address_creation | Enable or disable deposit address creation
*DeribitApi.PrivateApi* | [**privateToggleNotificationsFromSubaccountGet**](docs/PrivateApi.md#privateToggleNotificationsFromSubaccountGet) | **GET** /private/toggle_notifications_from_subaccount | Enable or disable sending of notifications for the subaccount.
*DeribitApi.PrivateApi* | [**privateToggleSubaccountLoginGet**](docs/PrivateApi.md#privateToggleSubaccountLoginGet) | **GET** /private/toggle_subaccount_login | Enable or disable login for a subaccount. If login is disabled and a session for the subaccount exists, this session will be terminated.
*DeribitApi.PrivateApi* | [**privateWithdrawGet**](docs/PrivateApi.md#privateWithdrawGet) | **GET** /private/withdraw | Creates a new withdrawal request
*DeribitApi.PublicApi* | [**publicAuthGet**](docs/PublicApi.md#publicAuthGet) | **GET** /public/auth | Authenticate
*DeribitApi.PublicApi* | [**publicGetAnnouncementsGet**](docs/PublicApi.md#publicGetAnnouncementsGet) | **GET** /public/get_announcements | Retrieves announcements from the last 30 days.
*DeribitApi.PublicApi* | [**publicGetBookSummaryByCurrencyGet**](docs/PublicApi.md#publicGetBookSummaryByCurrencyGet) | **GET** /public/get_book_summary_by_currency | Retrieves the summary information such as open interest, 24h volume, etc. for all instruments for the currency (optionally filtered by kind).
*DeribitApi.PublicApi* | [**publicGetBookSummaryByInstrumentGet**](docs/PublicApi.md#publicGetBookSummaryByInstrumentGet) | **GET** /public/get_book_summary_by_instrument | Retrieves the summary information such as open interest, 24h volume, etc. for a specific instrument.
*DeribitApi.PublicApi* | [**publicGetContractSizeGet**](docs/PublicApi.md#publicGetContractSizeGet) | **GET** /public/get_contract_size | Retrieves contract size of provided instrument.
*DeribitApi.PublicApi* | [**publicGetCurrenciesGet**](docs/PublicApi.md#publicGetCurrenciesGet) | **GET** /public/get_currencies | Retrieves all cryptocurrencies supported by the API.
*DeribitApi.PublicApi* | [**publicGetFundingChartDataGet**](docs/PublicApi.md#publicGetFundingChartDataGet) | **GET** /public/get_funding_chart_data | Retrieve the latest user trades that have occurred for PERPETUAL instruments in a specific currency symbol and within given time range.
*DeribitApi.PublicApi* | [**publicGetHistoricalVolatilityGet**](docs/PublicApi.md#publicGetHistoricalVolatilityGet) | **GET** /public/get_historical_volatility | Provides information about historical volatility for given cryptocurrency.
*DeribitApi.PublicApi* | [**publicGetIndexGet**](docs/PublicApi.md#publicGetIndexGet) | **GET** /public/get_index | Retrieves the current index price for the instruments, for the selected currency.
*DeribitApi.PublicApi* | [**publicGetInstrumentsGet**](docs/PublicApi.md#publicGetInstrumentsGet) | **GET** /public/get_instruments | Retrieves available trading instruments. This method can be used to see which instruments are available for trading, or which instruments have existed historically.
*DeribitApi.PublicApi* | [**publicGetLastSettlementsByCurrencyGet**](docs/PublicApi.md#publicGetLastSettlementsByCurrencyGet) | **GET** /public/get_last_settlements_by_currency | Retrieves historical settlement, delivery and bankruptcy events coming from all instruments within given currency.
*DeribitApi.PublicApi* | [**publicGetLastSettlementsByInstrumentGet**](docs/PublicApi.md#publicGetLastSettlementsByInstrumentGet) | **GET** /public/get_last_settlements_by_instrument | Retrieves historical public settlement, delivery and bankruptcy events filtered by instrument name.
*DeribitApi.PublicApi* | [**publicGetLastTradesByCurrencyAndTimeGet**](docs/PublicApi.md#publicGetLastTradesByCurrencyAndTimeGet) | **GET** /public/get_last_trades_by_currency_and_time | Retrieve the latest trades that have occurred for instruments in a specific currency symbol and within given time range.
*DeribitApi.PublicApi* | [**publicGetLastTradesByCurrencyGet**](docs/PublicApi.md#publicGetLastTradesByCurrencyGet) | **GET** /public/get_last_trades_by_currency | Retrieve the latest trades that have occurred for instruments in a specific currency symbol.
*DeribitApi.PublicApi* | [**publicGetLastTradesByInstrumentAndTimeGet**](docs/PublicApi.md#publicGetLastTradesByInstrumentAndTimeGet) | **GET** /public/get_last_trades_by_instrument_and_time | Retrieve the latest trades that have occurred for a specific instrument and within given time range.
*DeribitApi.PublicApi* | [**publicGetLastTradesByInstrumentGet**](docs/PublicApi.md#publicGetLastTradesByInstrumentGet) | **GET** /public/get_last_trades_by_instrument | Retrieve the latest trades that have occurred for a specific instrument.
*DeribitApi.PublicApi* | [**publicGetOrderBookGet**](docs/PublicApi.md#publicGetOrderBookGet) | **GET** /public/get_order_book | Retrieves the order book, along with other market values for a given instrument.
*DeribitApi.PublicApi* | [**publicGetTimeGet**](docs/PublicApi.md#publicGetTimeGet) | **GET** /public/get_time | Retrieves the current time (in milliseconds). This API endpoint can be used to check the clock skew between your software and Deribit&#39;s systems.
*DeribitApi.PublicApi* | [**publicGetTradeVolumesGet**](docs/PublicApi.md#publicGetTradeVolumesGet) | **GET** /public/get_trade_volumes | Retrieves aggregated 24h trade volumes for different instrument types and currencies.
*DeribitApi.PublicApi* | [**publicGetTradingviewChartDataGet**](docs/PublicApi.md#publicGetTradingviewChartDataGet) | **GET** /public/get_tradingview_chart_data | Publicly available market data used to generate a TradingView candle chart.
*DeribitApi.PublicApi* | [**publicTestGet**](docs/PublicApi.md#publicTestGet) | **GET** /public/test | Tests the connection to the API server, and returns its version. You can use this to make sure the API is reachable, and matches the expected version.
*DeribitApi.PublicApi* | [**publicTickerGet**](docs/PublicApi.md#publicTickerGet) | **GET** /public/ticker | Get ticker for an instrument.
*DeribitApi.PublicApi* | [**publicValidateFieldGet**](docs/PublicApi.md#publicValidateFieldGet) | **GET** /public/validate_field | Method used to introduce the client software connected to Deribit platform over websocket. Provided data may have an impact on the maintained connection and will be collected for internal statistical purposes. In response, Deribit will also introduce itself.
*DeribitApi.SupportingApi* | [**publicGetTimeGet**](docs/SupportingApi.md#publicGetTimeGet) | **GET** /public/get_time | Retrieves the current time (in milliseconds). This API endpoint can be used to check the clock skew between your software and Deribit&#39;s systems.
*DeribitApi.SupportingApi* | [**publicTestGet**](docs/SupportingApi.md#publicTestGet) | **GET** /public/test | Tests the connection to the API server, and returns its version. You can use this to make sure the API is reachable, and matches the expected version.
*DeribitApi.TradingApi* | [**privateBuyGet**](docs/TradingApi.md#privateBuyGet) | **GET** /private/buy | Places a buy order for an instrument.
*DeribitApi.TradingApi* | [**privateCancelAllByCurrencyGet**](docs/TradingApi.md#privateCancelAllByCurrencyGet) | **GET** /private/cancel_all_by_currency | Cancels all orders by currency, optionally filtered by instrument kind and/or order type.
*DeribitApi.TradingApi* | [**privateCancelAllByInstrumentGet**](docs/TradingApi.md#privateCancelAllByInstrumentGet) | **GET** /private/cancel_all_by_instrument | Cancels all orders by instrument, optionally filtered by order type.
*DeribitApi.TradingApi* | [**privateCancelAllGet**](docs/TradingApi.md#privateCancelAllGet) | **GET** /private/cancel_all | This method cancels all users orders and stop orders within all currencies and instrument kinds.
*DeribitApi.TradingApi* | [**privateCancelGet**](docs/TradingApi.md#privateCancelGet) | **GET** /private/cancel | Cancel an order, specified by order id
*DeribitApi.TradingApi* | [**privateClosePositionGet**](docs/TradingApi.md#privateClosePositionGet) | **GET** /private/close_position | Makes closing position reduce only order .
*DeribitApi.TradingApi* | [**privateEditGet**](docs/TradingApi.md#privateEditGet) | **GET** /private/edit | Change price, amount and/or other properties of an order.
*DeribitApi.TradingApi* | [**privateGetMarginsGet**](docs/TradingApi.md#privateGetMarginsGet) | **GET** /private/get_margins | Get margins for given instrument, amount and price.
*DeribitApi.TradingApi* | [**privateGetOpenOrdersByCurrencyGet**](docs/TradingApi.md#privateGetOpenOrdersByCurrencyGet) | **GET** /private/get_open_orders_by_currency | Retrieves list of user&#39;s open orders.
*DeribitApi.TradingApi* | [**privateGetOpenOrdersByInstrumentGet**](docs/TradingApi.md#privateGetOpenOrdersByInstrumentGet) | **GET** /private/get_open_orders_by_instrument | Retrieves list of user&#39;s open orders within given Instrument.
*DeribitApi.TradingApi* | [**privateGetOrderHistoryByCurrencyGet**](docs/TradingApi.md#privateGetOrderHistoryByCurrencyGet) | **GET** /private/get_order_history_by_currency | Retrieves history of orders that have been partially or fully filled.
*DeribitApi.TradingApi* | [**privateGetOrderHistoryByInstrumentGet**](docs/TradingApi.md#privateGetOrderHistoryByInstrumentGet) | **GET** /private/get_order_history_by_instrument | Retrieves history of orders that have been partially or fully filled.
*DeribitApi.TradingApi* | [**privateGetOrderMarginByIdsGet**](docs/TradingApi.md#privateGetOrderMarginByIdsGet) | **GET** /private/get_order_margin_by_ids | Retrieves initial margins of given orders
*DeribitApi.TradingApi* | [**privateGetOrderStateGet**](docs/TradingApi.md#privateGetOrderStateGet) | **GET** /private/get_order_state | Retrieve the current state of an order.
*DeribitApi.TradingApi* | [**privateGetSettlementHistoryByCurrencyGet**](docs/TradingApi.md#privateGetSettlementHistoryByCurrencyGet) | **GET** /private/get_settlement_history_by_currency | Retrieves settlement, delivery and bankruptcy events that have affected your account.
*DeribitApi.TradingApi* | [**privateGetSettlementHistoryByInstrumentGet**](docs/TradingApi.md#privateGetSettlementHistoryByInstrumentGet) | **GET** /private/get_settlement_history_by_instrument | Retrieves public settlement, delivery and bankruptcy events filtered by instrument name
*DeribitApi.TradingApi* | [**privateGetUserTradesByCurrencyAndTimeGet**](docs/TradingApi.md#privateGetUserTradesByCurrencyAndTimeGet) | **GET** /private/get_user_trades_by_currency_and_time | Retrieve the latest user trades that have occurred for instruments in a specific currency symbol and within given time range.
*DeribitApi.TradingApi* | [**privateGetUserTradesByCurrencyGet**](docs/TradingApi.md#privateGetUserTradesByCurrencyGet) | **GET** /private/get_user_trades_by_currency | Retrieve the latest user trades that have occurred for instruments in a specific currency symbol.
*DeribitApi.TradingApi* | [**privateGetUserTradesByInstrumentAndTimeGet**](docs/TradingApi.md#privateGetUserTradesByInstrumentAndTimeGet) | **GET** /private/get_user_trades_by_instrument_and_time | Retrieve the latest user trades that have occurred for a specific instrument and within given time range.
*DeribitApi.TradingApi* | [**privateGetUserTradesByInstrumentGet**](docs/TradingApi.md#privateGetUserTradesByInstrumentGet) | **GET** /private/get_user_trades_by_instrument | Retrieve the latest user trades that have occurred for a specific instrument.
*DeribitApi.TradingApi* | [**privateGetUserTradesByOrderGet**](docs/TradingApi.md#privateGetUserTradesByOrderGet) | **GET** /private/get_user_trades_by_order | Retrieve the list of user trades that was created for given order
*DeribitApi.TradingApi* | [**privateSellGet**](docs/TradingApi.md#privateSellGet) | **GET** /private/sell | Places a sell order for an instrument.
*DeribitApi.WalletApi* | [**privateAddToAddressBookGet**](docs/WalletApi.md#privateAddToAddressBookGet) | **GET** /private/add_to_address_book | Adds new entry to address book of given type
*DeribitApi.WalletApi* | [**privateCancelTransferByIdGet**](docs/WalletApi.md#privateCancelTransferByIdGet) | **GET** /private/cancel_transfer_by_id | Cancel transfer
*DeribitApi.WalletApi* | [**privateCancelWithdrawalGet**](docs/WalletApi.md#privateCancelWithdrawalGet) | **GET** /private/cancel_withdrawal | Cancels withdrawal request
*DeribitApi.WalletApi* | [**privateCreateDepositAddressGet**](docs/WalletApi.md#privateCreateDepositAddressGet) | **GET** /private/create_deposit_address | Creates deposit address in currency
*DeribitApi.WalletApi* | [**privateGetAddressBookGet**](docs/WalletApi.md#privateGetAddressBookGet) | **GET** /private/get_address_book | Retrieves address book of given type
*DeribitApi.WalletApi* | [**privateGetCurrentDepositAddressGet**](docs/WalletApi.md#privateGetCurrentDepositAddressGet) | **GET** /private/get_current_deposit_address | Retrieve deposit address for currency
*DeribitApi.WalletApi* | [**privateGetDepositsGet**](docs/WalletApi.md#privateGetDepositsGet) | **GET** /private/get_deposits | Retrieve the latest users deposits
*DeribitApi.WalletApi* | [**privateGetTransfersGet**](docs/WalletApi.md#privateGetTransfersGet) | **GET** /private/get_transfers | Adds new entry to address book of given type
*DeribitApi.WalletApi* | [**privateGetWithdrawalsGet**](docs/WalletApi.md#privateGetWithdrawalsGet) | **GET** /private/get_withdrawals | Retrieve the latest users withdrawals
*DeribitApi.WalletApi* | [**privateRemoveFromAddressBookGet**](docs/WalletApi.md#privateRemoveFromAddressBookGet) | **GET** /private/remove_from_address_book | Adds new entry to address book of given type
*DeribitApi.WalletApi* | [**privateSubmitTransferToSubaccountGet**](docs/WalletApi.md#privateSubmitTransferToSubaccountGet) | **GET** /private/submit_transfer_to_subaccount | Transfer funds to a subaccount.
*DeribitApi.WalletApi* | [**privateSubmitTransferToUserGet**](docs/WalletApi.md#privateSubmitTransferToUserGet) | **GET** /private/submit_transfer_to_user | Transfer funds to a another user.
*DeribitApi.WalletApi* | [**privateToggleDepositAddressCreationGet**](docs/WalletApi.md#privateToggleDepositAddressCreationGet) | **GET** /private/toggle_deposit_address_creation | Enable or disable deposit address creation
*DeribitApi.WalletApi* | [**privateWithdrawGet**](docs/WalletApi.md#privateWithdrawGet) | **GET** /private/withdraw | Creates a new withdrawal request


## Documentation for Models

 - [DeribitApi.AddressBookItem](docs/AddressBookItem.md)
 - [DeribitApi.BookSummary](docs/BookSummary.md)
 - [DeribitApi.Currency](docs/Currency.md)
 - [DeribitApi.CurrencyPortfolio](docs/CurrencyPortfolio.md)
 - [DeribitApi.CurrencyWithdrawalPriorities](docs/CurrencyWithdrawalPriorities.md)
 - [DeribitApi.Deposit](docs/Deposit.md)
 - [DeribitApi.Instrument](docs/Instrument.md)
 - [DeribitApi.KeyNumberPair](docs/KeyNumberPair.md)
 - [DeribitApi.Order](docs/Order.md)
 - [DeribitApi.OrderIdInitialMarginPair](docs/OrderIdInitialMarginPair.md)
 - [DeribitApi.Portfolio](docs/Portfolio.md)
 - [DeribitApi.PortfolioEth](docs/PortfolioEth.md)
 - [DeribitApi.Position](docs/Position.md)
 - [DeribitApi.PublicTrade](docs/PublicTrade.md)
 - [DeribitApi.Settlement](docs/Settlement.md)
 - [DeribitApi.TradesVolumes](docs/TradesVolumes.md)
 - [DeribitApi.TransferItem](docs/TransferItem.md)
 - [DeribitApi.Types](docs/Types.md)
 - [DeribitApi.UserTrade](docs/UserTrade.md)
 - [DeribitApi.Withdrawal](docs/Withdrawal.md)


## Documentation for Authorization



### bearerAuth

- **Type**: Bearer authentication (Auth. Token)

