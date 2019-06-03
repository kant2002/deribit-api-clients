/**
 * Deribit API
 * #Overview  Deribit provides three different interfaces to access the API:  * [JSON-RPC over Websocket](#json-rpc) * [JSON-RPC over HTTP](#json-rpc) * [FIX](#fix-api) (Financial Information eXchange)  With the API Console you can use and test the JSON-RPC API, both via HTTP and  via Websocket. To visit the API console, go to __Account > API tab >  API Console tab.__   ##Naming Deribit tradeable assets or instruments use the following system of naming:  |Kind|Examples|Template|Comments| |----|--------|--------|--------| |Future|<code>BTC-25MAR16</code>, <code>BTC-5AUG16</code>|<code>BTC-DMMMYY</code>|<code>BTC</code> is currency, <code>DMMMYY</code> is expiration date, <code>D</code> stands for day of month (1 or 2 digits), <code>MMM</code> - month (3 first letters in English), <code>YY</code> stands for year.| |Perpetual|<code>BTC-PERPETUAL</code>                        ||Perpetual contract for currency <code>BTC</code>.| |Option|<code>BTC-25MAR16-420-C</code>, <code>BTC-5AUG16-580-P</code>|<code>BTC-DMMMYY-STRIKE-K</code>|<code>STRIKE</code> is option strike price in USD. Template <code>K</code> is option kind: <code>C</code> for call options or <code>P</code> for put options.|   # JSON-RPC JSON-RPC is a light-weight remote procedure call (RPC) protocol. The  [JSON-RPC specification](https://www.jsonrpc.org/specification) defines the data structures that are used for the messages that are exchanged between client and server, as well as the rules around their processing. JSON-RPC uses JSON (RFC 4627) as data format.  JSON-RPC is transport agnostic: it does not specify which transport mechanism must be used. The Deribit API supports both Websocket (preferred) and HTTP (with limitations: subscriptions are not supported over HTTP).  ## Request messages > An example of a request message:  ```json {     \"jsonrpc\": \"2.0\",     \"id\": 8066,     \"method\": \"public/ticker\",     \"params\": {         \"instrument\": \"BTC-24AUG18-6500-P\"     } } ```  According to the JSON-RPC sepcification the requests must be JSON objects with the following fields.  |Name|Type|Description| |----|----|-----------| |jsonrpc|string|The version of the JSON-RPC spec: \"2.0\"| |id|integer or string|An identifier of the request. If it is included, then the response will contain the same identifier| |method|string|The method to be invoked| |params|object|The parameters values for the method. The field names must match with the expected parameter names. The parameters that are expected are described in the documentation for the methods, below.|  <aside class=\"warning\"> The JSON-RPC specification describes two features that are currently not supported by the API:  <ul> <li>Specification of parameter values by position</li> <li>Batch requests</li> </ul>  </aside>   ## Response messages > An example of a response message:  ```json {     \"jsonrpc\": \"2.0\",     \"id\": 5239,     \"testnet\": false,     \"result\": [         {             \"currency\": \"BTC\",             \"currencyLong\": \"Bitcoin\",             \"minConfirmation\": 2,             \"txFee\": 0.0006,             \"isActive\": true,             \"coinType\": \"BITCOIN\",             \"baseAddress\": null         }     ],     \"usIn\": 1535043730126248,     \"usOut\": 1535043730126250,     \"usDiff\": 2 } ```  The JSON-RPC API always responds with a JSON object with the following fields.   |Name|Type|Description| |----|----|-----------| |id|integer|This is the same id that was sent in the request.| |result|any|If successful, the result of the API call. The format for the result is described with each method.| |error|error object|Only present if there was an error invoking the method. The error object is described below.| |testnet|boolean|Indicates whether the API in use is actually the test API.  <code>false</code> for production server, <code>true</code> for test server.| |usIn|integer|The timestamp when the requests was received (microseconds since the Unix epoch)| |usOut|integer|The timestamp when the response was sent (microseconds since the Unix epoch)| |usDiff|integer|The number of microseconds that was spent handling the request|  <aside class=\"notice\"> The fields <code>testnet</code>, <code>usIn</code>, <code>usOut</code> and <code>usDiff</code> are not part of the JSON-RPC standard.  <p>In order not to clutter the examples they will generally be omitted from the example code.</p> </aside>  > An example of a response with an error:  ```json {     \"jsonrpc\": \"2.0\",     \"id\": 8163,     \"error\": {         \"code\": 11050,         \"message\": \"bad_request\"     },     \"testnet\": false,     \"usIn\": 1535037392434763,     \"usOut\": 1535037392448119,     \"usDiff\": 13356 } ``` In case of an error the response message will contain the error field, with as value an object with the following with the following fields:  |Name|Type|Description |----|----|-----------| |code|integer|A number that indicates the kind of error.| |message|string|A short description that indicates the kind of error.| |data|any|Additional data about the error. This field may be omitted.|  ## Notifications  > An example of a notification:  ```json {     \"jsonrpc\": \"2.0\",     \"method\": \"subscription\",     \"params\": {         \"channel\": \"deribit_price_index.btc_usd\",         \"data\": {             \"timestamp\": 1535098298227,             \"price\": 6521.17,             \"index_name\": \"btc_usd\"         }     } } ```  API users can subscribe to certain types of notifications. This means that they will receive JSON-RPC notification-messages from the server when certain events occur, such as changes to the index price or changes to the order book for a certain instrument.   The API methods [public/subscribe](#public-subscribe) and [private/subscribe](#private-subscribe) are used to set up a subscription. Since HTTP does not support the sending of messages from server to client, these methods are only availble when using the Websocket transport mechanism.  At the moment of subscription a \"channel\" must be specified. The channel determines the type of events that will be received.  See [Subscriptions](#subscriptions) for more details about the channels.  In accordance with the JSON-RPC specification, the format of a notification  is that of a request message without an <code>id</code> field. The value of the <code>method</code> field will always be <code>\"subscription\"</code>. The <code>params</code> field will always be an object with 2 members: <code>channel</code> and <code>data</code>. The value of the <code>channel</code> member is the name of the channel (a string). The value of the <code>data</code> member is an object that contains data  that is specific for the channel.   ## Authentication  > An example of a JSON request with token:  ```json {     \"id\": 5647,     \"method\": \"private/get_subaccounts\",     \"params\": {         \"access_token\": \"67SVutDoVZSzkUStHSuk51WntMNBJ5mh5DYZhwzpiqDF\"     } } ```  The API consists of `public` and `private` methods. The public methods do not require authentication. The private methods use OAuth 2.0 authentication. This means that a valid OAuth access token must be included in the request, which can get achived by calling method [public/auth](#public-auth).  When the token was assigned to the user, it should be passed along, with other request parameters, back to the server:  |Connection type|Access token placement |----|-----------| |**Websocket**|Inside request JSON parameters, as an `access_token` field| |**HTTP (REST)**|Header `Authorization: bearer ```Token``` ` value|  ### Additional authorization method - basic user credentials  <span style=\"color:red\"><b> ! Not recommended - however, it could be useful for quick testing API</b></span></br>  Every `private` method could be accessed by providing, inside HTTP `Authorization: Basic XXX` header, values with user `ClientId` and assigned `ClientSecret` (both values can be found on the API page on the Deribit website) encoded with `Base64`:  <code>Authorization: Basic BASE64(`ClientId` + `:` + `ClientSecret`)</code>   ### Additional authorization method - Deribit signature credentials  The Derbit service provides dedicated authorization method, which harness user generated signature to increase security level for passing request data. Generated value is passed inside `Authorization` header, coded as:  <code>Authorization: deri-hmac-sha256 id=```ClientId```,ts=```Timestamp```,sig=```Signature```,nonce=```Nonce```</code>  where:  |Deribit credential|Description |----|-----------| |*ClientId*|Can be found on the API page on the Deribit website| |*Timestamp*|Time when the request was generated - given as **miliseconds**. It's valid for **60 seconds** since generation, after that time any request with an old timestamp will be rejected.| |*Signature*|Value for signature calculated as described below | |*Nonce*|Single usage, user generated initialization vector for the server token|  The signature is generated by the following formula:  <code> Signature = HEX_STRING( HMAC-SHA256( ClientSecret, StringToSign ) );</code></br>  <code> StringToSign =  Timestamp + \"\\n\" + Nonce + \"\\n\" + RequestData;</code></br>  <code> RequestData =  UPPERCASE(HTTP_METHOD())  + \"\\n\" + URI() + \"\\n\" + RequestBody + \"\\n\";</code></br>   e.g. (using shell with ```openssl``` tool):  <code>&nbsp;&nbsp;&nbsp;&nbsp;ClientId=AAAAAAAAAAA</code></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;ClientSecret=ABCD</code></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;Timestamp=$( date +%s000 )</code></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;Nonce=$( cat /dev/urandom | tr -dc 'a-z0-9' | head -c8 )</code></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;URI=\"/api/v2/private/get_account_summary?currency=BTC\"</code></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;HttpMethod=GET</code></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;Body=\"\"</code></br></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;Signature=$( echo -ne \"${Timestamp}\\n${Nonce}\\n${HttpMethod}\\n${URI}\\n${Body}\\n\" | openssl sha256 -r -hmac \"$ClientSecret\" | cut -f1 -d' ' )</code></br></br> <code>&nbsp;&nbsp;&nbsp;&nbsp;echo $Signature</code></br></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;shell output> ea40d5e5e4fae235ab22b61da98121fbf4acdc06db03d632e23c66bcccb90d2c  (**WARNING**: Exact value depends on current timestamp and client credentials</code></br></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;curl -s -X ${HttpMethod} -H \"Authorization: deri-hmac-sha256 id=${ClientId},ts=${Timestamp},nonce=${Nonce},sig=${Signature}\" \"https://www.deribit.com${URI}\"</code></br></br>    ### Additional authorization method - signature credentials (WebSocket API)  When connecting through Websocket, user can request for authorization using ```client_credential``` method, which requires providing following parameters (as a part of JSON request):  |JSON parameter|Description |----|-----------| |*grant_type*|Must be **client_signature**| |*client_id*|Can be found on the API page on the Deribit website| |*timestamp*|Time when the request was generated - given as **miliseconds**. It's valid for **60 seconds** since generation, after that time any request with an old timestamp will be rejected.| |*signature*|Value for signature calculated as described below | |*nonce*|Single usage, user generated initialization vector for the server token| |*data*|**Optional** field, which contains any user specific value|  The signature is generated by the following formula:  <code> StringToSign =  Timestamp + \"\\n\" + Nonce + \"\\n\" + Data;</code></br>  <code> Signature = HEX_STRING( HMAC-SHA256( ClientSecret, StringToSign ) );</code></br>   e.g. (using shell with ```openssl``` tool):  <code>&nbsp;&nbsp;&nbsp;&nbsp;ClientId=AAAAAAAAAAA</code></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;ClientSecret=ABCD</code></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;Timestamp=$( date +%s000 ) # e.g. 1554883365000 </code></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;Nonce=$( cat /dev/urandom | tr -dc 'a-z0-9' | head -c8 ) # e.g. fdbmmz79 </code></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;Data=\"\"</code></br></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;Signature=$( echo -ne \"${Timestamp}\\n${Nonce}\\n${Data}\\n\" | openssl sha256 -r -hmac \"$ClientSecret\" | cut -f1 -d' ' )</code></br></br> <code>&nbsp;&nbsp;&nbsp;&nbsp;echo $Signature</code></br></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;shell output> e20c9cd5639d41f8bbc88f4d699c4baf94a4f0ee320e9a116b72743c449eb994  (**WARNING**: Exact value depends on current timestamp and client credentials</code></br></br>   You can also check the signature value using some online tools like, e.g: [https://codebeautify.org/hmac-generator](https://codebeautify.org/hmac-generator) (but don't forget about adding *newline* after each part of the hashed text and remember that you **should use** it only with your **test credentials**).   Here's a sample JSON request created using the values from the example above:  <code> {                            </br> &nbsp;&nbsp;\"jsonrpc\" : \"2.0\",         </br> &nbsp;&nbsp;\"id\" : 9929,               </br> &nbsp;&nbsp;\"method\" : \"public/auth\",  </br> &nbsp;&nbsp;\"params\" :                 </br> &nbsp;&nbsp;{                        </br> &nbsp;&nbsp;&nbsp;&nbsp;\"grant_type\" : \"client_signature\",   </br> &nbsp;&nbsp;&nbsp;&nbsp;\"client_id\" : \"AAAAAAAAAAA\",         </br> &nbsp;&nbsp;&nbsp;&nbsp;\"timestamp\": \"1554883365000\",        </br> &nbsp;&nbsp;&nbsp;&nbsp;\"nonce\": \"fdbmmz79\",                 </br> &nbsp;&nbsp;&nbsp;&nbsp;\"data\": \"\",                          </br> &nbsp;&nbsp;&nbsp;&nbsp;\"signature\" : \"e20c9cd5639d41f8bbc88f4d699c4baf94a4f0ee320e9a116b72743c449eb994\"  </br> &nbsp;&nbsp;}                        </br> }                            </br> </code>   ### Access scope  When asking for `access token` user can provide the required access level (called `scope`) which defines what type of functionality he/she wants to use, and whether requests are only going to check for some data or also to update them.  Scopes are required and checked for `private` methods, so if you plan to use only `public` information you can stay with values assigned by default.  |Scope|Description |----|-----------| |*account:read*|Access to **account** methods - read only data| |*account:read_write*|Access to **account** methods - allows to manage account settings, add subaccounts, etc.| |*trade:read*|Access to **trade** methods - read only data| |*trade:read_write*|Access to **trade** methods - required to create and modify orders| |*wallet:read*|Access to **wallet** methods - read only data| |*wallet:read_write*|Access to **wallet** methods - allows to withdraw, generate new deposit address, etc.| |*wallet:none*, *account:none*, *trade:none*|Blocked access to specified functionality|    <span style=\"color:red\">**NOTICE:**</span> Depending on choosing an authentication method (```grant type```) some scopes could be narrowed by the server. e.g. when ```grant_type = client_credentials``` and ```scope = wallet:read_write``` it's modified by the server as ```scope = wallet:read```\"   ## JSON-RPC over websocket Websocket is the prefered transport mechanism for the JSON-RPC API, because it is faster and because it can support [subscriptions](#subscriptions) and [cancel on disconnect](#private-enable_cancel_on_disconnect). The code examples that can be found next to each of the methods show how websockets can be used from Python or Javascript/node.js.  ## JSON-RPC over HTTP Besides websockets it is also possible to use the API via HTTP. The code examples for 'shell' show how this can be done using curl. Note that subscriptions and cancel on disconnect are not supported via HTTP.  #Methods 
 *
 * The version of the OpenAPI document: 2.0.0
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 *
 */


import ApiClient from "../ApiClient";

/**
* Public service.
* @module api/PublicApi
* @version 2.0.0
*/
export default class PublicApi {

    /**
    * Constructs a new PublicApi. 
    * @alias module:api/PublicApi
    * @class
    * @param {module:ApiClient} [apiClient] Optional API client implementation to use,
    * default to {@link module:ApiClient#instance} if unspecified.
    */
    constructor(apiClient) {
        this.apiClient = apiClient || ApiClient.instance;
    }


    /**
     * Callback function to receive the result of the publicAuthGet operation.
     * @callback module:api/PublicApi~publicAuthGetCallback
     * @param {String} error Error message, if any.
     * @param {Object} data The data returned by the service call.
     * @param {String} response The complete HTTP response.
     */

    /**
     * Authenticate
     * Retrieve an Oauth access token, to be used for authentication of 'private' requests.  Three methods of authentication are supported:  - <code>password</code> - using email and and password as when logging on to the website - <code>client_credentials</code> - using the access key and access secret that can be found on the API page on the website - <code>client_signature</code> - using the access key that can be found on the API page on the website and user generated signature. The signature is calculated using some fields provided in the request, using formula described here [Deribit signature credentials](#additional-authorization-method-deribit-signature-credentials) - <code>refresh_token</code> - using a refresh token that was received from an earlier invocation  The response will contain an access token, expiration period (number of seconds that the token is valid) and a refresh token that can be used to get a new set of tokens. 
     * @param {module:model/String} grantType Method of authentication
     * @param {String} username Required for grant type `password`
     * @param {String} password Required for grant type `password`
     * @param {String} clientId Required for grant type `client_credentials` and `client_signature`
     * @param {String} clientSecret Required for grant type `client_credentials`
     * @param {String} refreshToken Required for grant type `refresh_token`
     * @param {String} timestamp Required for grant type `client_signature`, provides time when request has been generated
     * @param {String} signature Required for grant type `client_signature`; it's a cryptographic signature calculated over provided fields using user **secret key**. The signature should be calculated as an HMAC (Hash-based Message Authentication Code) with `SHA256` hash algorithm
     * @param {Object} opts Optional parameters
     * @param {String} opts.nonce Optional for grant type `client_signature`; delivers user generated initialization vector for the server token
     * @param {String} opts.state Will be passed back in the response
     * @param {String} opts.scope Describes type of the access for assigned token, possible values: `connection`, `session`, `session:name`, `trade:[read, read_write, none]`, `wallet:[read, read_write, none]`, `account:[read, read_write, none]`, `expires:NUMBER` (token will expire after `NUMBER` of seconds).</BR></BR> **NOTICE:** Depending on choosing an authentication method (```grant type```) some scopes could be narrowed by the server. e.g. when ```grant_type = client_credentials``` and ```scope = wallet:read_write``` it's modified by the server as ```scope = wallet:read```
     * @param {module:api/PublicApi~publicAuthGetCallback} callback The callback function, accepting three arguments: error, data, response
     * data is of type: {@link Object}
     */
    publicAuthGet(grantType, username, password, clientId, clientSecret, refreshToken, timestamp, signature, opts, callback) {
      opts = opts || {};
      let postBody = null;
      // verify the required parameter 'grantType' is set
      if (grantType === undefined || grantType === null) {
        throw new Error("Missing the required parameter 'grantType' when calling publicAuthGet");
      }
      // verify the required parameter 'username' is set
      if (username === undefined || username === null) {
        throw new Error("Missing the required parameter 'username' when calling publicAuthGet");
      }
      // verify the required parameter 'password' is set
      if (password === undefined || password === null) {
        throw new Error("Missing the required parameter 'password' when calling publicAuthGet");
      }
      // verify the required parameter 'clientId' is set
      if (clientId === undefined || clientId === null) {
        throw new Error("Missing the required parameter 'clientId' when calling publicAuthGet");
      }
      // verify the required parameter 'clientSecret' is set
      if (clientSecret === undefined || clientSecret === null) {
        throw new Error("Missing the required parameter 'clientSecret' when calling publicAuthGet");
      }
      // verify the required parameter 'refreshToken' is set
      if (refreshToken === undefined || refreshToken === null) {
        throw new Error("Missing the required parameter 'refreshToken' when calling publicAuthGet");
      }
      // verify the required parameter 'timestamp' is set
      if (timestamp === undefined || timestamp === null) {
        throw new Error("Missing the required parameter 'timestamp' when calling publicAuthGet");
      }
      // verify the required parameter 'signature' is set
      if (signature === undefined || signature === null) {
        throw new Error("Missing the required parameter 'signature' when calling publicAuthGet");
      }

      let pathParams = {
      };
      let queryParams = {
        'grant_type': grantType,
        'username': username,
        'password': password,
        'client_id': clientId,
        'client_secret': clientSecret,
        'refresh_token': refreshToken,
        'timestamp': timestamp,
        'signature': signature,
        'nonce': opts['nonce'],
        'state': opts['state'],
        'scope': opts['scope']
      };
      let headerParams = {
      };
      let formParams = {
      };

      let authNames = ['bearerAuth'];
      let contentTypes = [];
      let accepts = ['application/json'];
      let returnType = Object;
      return this.apiClient.callApi(
        '/public/auth', 'GET',
        pathParams, queryParams, headerParams, formParams, postBody,
        authNames, contentTypes, accepts, returnType, null, callback
      );
    }

    /**
     * Callback function to receive the result of the publicGetAnnouncementsGet operation.
     * @callback module:api/PublicApi~publicGetAnnouncementsGetCallback
     * @param {String} error Error message, if any.
     * @param {Object} data The data returned by the service call.
     * @param {String} response The complete HTTP response.
     */

    /**
     * Retrieves announcements from the last 30 days.
     * @param {module:api/PublicApi~publicGetAnnouncementsGetCallback} callback The callback function, accepting three arguments: error, data, response
     * data is of type: {@link Object}
     */
    publicGetAnnouncementsGet(callback) {
      let postBody = null;

      let pathParams = {
      };
      let queryParams = {
      };
      let headerParams = {
      };
      let formParams = {
      };

      let authNames = ['bearerAuth'];
      let contentTypes = [];
      let accepts = ['application/json'];
      let returnType = Object;
      return this.apiClient.callApi(
        '/public/get_announcements', 'GET',
        pathParams, queryParams, headerParams, formParams, postBody,
        authNames, contentTypes, accepts, returnType, null, callback
      );
    }

    /**
     * Callback function to receive the result of the publicGetBookSummaryByCurrencyGet operation.
     * @callback module:api/PublicApi~publicGetBookSummaryByCurrencyGetCallback
     * @param {String} error Error message, if any.
     * @param {Object} data The data returned by the service call.
     * @param {String} response The complete HTTP response.
     */

    /**
     * Retrieves the summary information such as open interest, 24h volume, etc. for all instruments for the currency (optionally filtered by kind).
     * @param {module:model/String} currency The currency symbol
     * @param {Object} opts Optional parameters
     * @param {module:model/String} opts.kind Instrument kind, if not provided instruments of all kinds are considered
     * @param {module:api/PublicApi~publicGetBookSummaryByCurrencyGetCallback} callback The callback function, accepting three arguments: error, data, response
     * data is of type: {@link Object}
     */
    publicGetBookSummaryByCurrencyGet(currency, opts, callback) {
      opts = opts || {};
      let postBody = null;
      // verify the required parameter 'currency' is set
      if (currency === undefined || currency === null) {
        throw new Error("Missing the required parameter 'currency' when calling publicGetBookSummaryByCurrencyGet");
      }

      let pathParams = {
      };
      let queryParams = {
        'currency': currency,
        'kind': opts['kind']
      };
      let headerParams = {
      };
      let formParams = {
      };

      let authNames = ['bearerAuth'];
      let contentTypes = [];
      let accepts = ['application/json'];
      let returnType = Object;
      return this.apiClient.callApi(
        '/public/get_book_summary_by_currency', 'GET',
        pathParams, queryParams, headerParams, formParams, postBody,
        authNames, contentTypes, accepts, returnType, null, callback
      );
    }

    /**
     * Callback function to receive the result of the publicGetBookSummaryByInstrumentGet operation.
     * @callback module:api/PublicApi~publicGetBookSummaryByInstrumentGetCallback
     * @param {String} error Error message, if any.
     * @param {Object} data The data returned by the service call.
     * @param {String} response The complete HTTP response.
     */

    /**
     * Retrieves the summary information such as open interest, 24h volume, etc. for a specific instrument.
     * @param {String} instrumentName Instrument name
     * @param {module:api/PublicApi~publicGetBookSummaryByInstrumentGetCallback} callback The callback function, accepting three arguments: error, data, response
     * data is of type: {@link Object}
     */
    publicGetBookSummaryByInstrumentGet(instrumentName, callback) {
      let postBody = null;
      // verify the required parameter 'instrumentName' is set
      if (instrumentName === undefined || instrumentName === null) {
        throw new Error("Missing the required parameter 'instrumentName' when calling publicGetBookSummaryByInstrumentGet");
      }

      let pathParams = {
      };
      let queryParams = {
        'instrument_name': instrumentName
      };
      let headerParams = {
      };
      let formParams = {
      };

      let authNames = ['bearerAuth'];
      let contentTypes = [];
      let accepts = ['application/json'];
      let returnType = Object;
      return this.apiClient.callApi(
        '/public/get_book_summary_by_instrument', 'GET',
        pathParams, queryParams, headerParams, formParams, postBody,
        authNames, contentTypes, accepts, returnType, null, callback
      );
    }

    /**
     * Callback function to receive the result of the publicGetContractSizeGet operation.
     * @callback module:api/PublicApi~publicGetContractSizeGetCallback
     * @param {String} error Error message, if any.
     * @param {Object} data The data returned by the service call.
     * @param {String} response The complete HTTP response.
     */

    /**
     * Retrieves contract size of provided instrument.
     * @param {String} instrumentName Instrument name
     * @param {module:api/PublicApi~publicGetContractSizeGetCallback} callback The callback function, accepting three arguments: error, data, response
     * data is of type: {@link Object}
     */
    publicGetContractSizeGet(instrumentName, callback) {
      let postBody = null;
      // verify the required parameter 'instrumentName' is set
      if (instrumentName === undefined || instrumentName === null) {
        throw new Error("Missing the required parameter 'instrumentName' when calling publicGetContractSizeGet");
      }

      let pathParams = {
      };
      let queryParams = {
        'instrument_name': instrumentName
      };
      let headerParams = {
      };
      let formParams = {
      };

      let authNames = ['bearerAuth'];
      let contentTypes = [];
      let accepts = ['application/json'];
      let returnType = Object;
      return this.apiClient.callApi(
        '/public/get_contract_size', 'GET',
        pathParams, queryParams, headerParams, formParams, postBody,
        authNames, contentTypes, accepts, returnType, null, callback
      );
    }

    /**
     * Callback function to receive the result of the publicGetCurrenciesGet operation.
     * @callback module:api/PublicApi~publicGetCurrenciesGetCallback
     * @param {String} error Error message, if any.
     * @param {Object} data The data returned by the service call.
     * @param {String} response The complete HTTP response.
     */

    /**
     * Retrieves all cryptocurrencies supported by the API.
     * @param {module:api/PublicApi~publicGetCurrenciesGetCallback} callback The callback function, accepting three arguments: error, data, response
     * data is of type: {@link Object}
     */
    publicGetCurrenciesGet(callback) {
      let postBody = null;

      let pathParams = {
      };
      let queryParams = {
      };
      let headerParams = {
      };
      let formParams = {
      };

      let authNames = ['bearerAuth'];
      let contentTypes = [];
      let accepts = ['application/json'];
      let returnType = Object;
      return this.apiClient.callApi(
        '/public/get_currencies', 'GET',
        pathParams, queryParams, headerParams, formParams, postBody,
        authNames, contentTypes, accepts, returnType, null, callback
      );
    }

    /**
     * Callback function to receive the result of the publicGetFundingChartDataGet operation.
     * @callback module:api/PublicApi~publicGetFundingChartDataGetCallback
     * @param {String} error Error message, if any.
     * @param {Object} data The data returned by the service call.
     * @param {String} response The complete HTTP response.
     */

    /**
     * Retrieve the latest user trades that have occurred for PERPETUAL instruments in a specific currency symbol and within given time range.
     * @param {String} instrumentName Instrument name
     * @param {Object} opts Optional parameters
     * @param {module:model/String} opts.length Specifies time period. `8h` - 8 hours, `24h` - 24 hours
     * @param {module:api/PublicApi~publicGetFundingChartDataGetCallback} callback The callback function, accepting three arguments: error, data, response
     * data is of type: {@link Object}
     */
    publicGetFundingChartDataGet(instrumentName, opts, callback) {
      opts = opts || {};
      let postBody = null;
      // verify the required parameter 'instrumentName' is set
      if (instrumentName === undefined || instrumentName === null) {
        throw new Error("Missing the required parameter 'instrumentName' when calling publicGetFundingChartDataGet");
      }

      let pathParams = {
      };
      let queryParams = {
        'instrument_name': instrumentName,
        'length': opts['length']
      };
      let headerParams = {
      };
      let formParams = {
      };

      let authNames = ['bearerAuth'];
      let contentTypes = [];
      let accepts = ['application/json'];
      let returnType = Object;
      return this.apiClient.callApi(
        '/public/get_funding_chart_data', 'GET',
        pathParams, queryParams, headerParams, formParams, postBody,
        authNames, contentTypes, accepts, returnType, null, callback
      );
    }

    /**
     * Callback function to receive the result of the publicGetHistoricalVolatilityGet operation.
     * @callback module:api/PublicApi~publicGetHistoricalVolatilityGetCallback
     * @param {String} error Error message, if any.
     * @param {Object} data The data returned by the service call.
     * @param {String} response The complete HTTP response.
     */

    /**
     * Provides information about historical volatility for given cryptocurrency.
     * @param {module:model/String} currency The currency symbol
     * @param {module:api/PublicApi~publicGetHistoricalVolatilityGetCallback} callback The callback function, accepting three arguments: error, data, response
     * data is of type: {@link Object}
     */
    publicGetHistoricalVolatilityGet(currency, callback) {
      let postBody = null;
      // verify the required parameter 'currency' is set
      if (currency === undefined || currency === null) {
        throw new Error("Missing the required parameter 'currency' when calling publicGetHistoricalVolatilityGet");
      }

      let pathParams = {
      };
      let queryParams = {
        'currency': currency
      };
      let headerParams = {
      };
      let formParams = {
      };

      let authNames = ['bearerAuth'];
      let contentTypes = [];
      let accepts = ['application/json'];
      let returnType = Object;
      return this.apiClient.callApi(
        '/public/get_historical_volatility', 'GET',
        pathParams, queryParams, headerParams, formParams, postBody,
        authNames, contentTypes, accepts, returnType, null, callback
      );
    }

    /**
     * Callback function to receive the result of the publicGetIndexGet operation.
     * @callback module:api/PublicApi~publicGetIndexGetCallback
     * @param {String} error Error message, if any.
     * @param {Object} data The data returned by the service call.
     * @param {String} response The complete HTTP response.
     */

    /**
     * Retrieves the current index price for the instruments, for the selected currency.
     * @param {module:model/String} currency The currency symbol
     * @param {module:api/PublicApi~publicGetIndexGetCallback} callback The callback function, accepting three arguments: error, data, response
     * data is of type: {@link Object}
     */
    publicGetIndexGet(currency, callback) {
      let postBody = null;
      // verify the required parameter 'currency' is set
      if (currency === undefined || currency === null) {
        throw new Error("Missing the required parameter 'currency' when calling publicGetIndexGet");
      }

      let pathParams = {
      };
      let queryParams = {
        'currency': currency
      };
      let headerParams = {
      };
      let formParams = {
      };

      let authNames = ['bearerAuth'];
      let contentTypes = [];
      let accepts = ['application/json'];
      let returnType = Object;
      return this.apiClient.callApi(
        '/public/get_index', 'GET',
        pathParams, queryParams, headerParams, formParams, postBody,
        authNames, contentTypes, accepts, returnType, null, callback
      );
    }

    /**
     * Callback function to receive the result of the publicGetInstrumentsGet operation.
     * @callback module:api/PublicApi~publicGetInstrumentsGetCallback
     * @param {String} error Error message, if any.
     * @param {Object} data The data returned by the service call.
     * @param {String} response The complete HTTP response.
     */

    /**
     * Retrieves available trading instruments. This method can be used to see which instruments are available for trading, or which instruments have existed historically.
     * @param {module:model/String} currency The currency symbol
     * @param {Object} opts Optional parameters
     * @param {module:model/String} opts.kind Instrument kind, if not provided instruments of all kinds are considered
     * @param {Boolean} opts.expired Set to true to show expired instruments instead of active ones. (default to false)
     * @param {module:api/PublicApi~publicGetInstrumentsGetCallback} callback The callback function, accepting three arguments: error, data, response
     * data is of type: {@link Object}
     */
    publicGetInstrumentsGet(currency, opts, callback) {
      opts = opts || {};
      let postBody = null;
      // verify the required parameter 'currency' is set
      if (currency === undefined || currency === null) {
        throw new Error("Missing the required parameter 'currency' when calling publicGetInstrumentsGet");
      }

      let pathParams = {
      };
      let queryParams = {
        'currency': currency,
        'kind': opts['kind'],
        'expired': opts['expired']
      };
      let headerParams = {
      };
      let formParams = {
      };

      let authNames = ['bearerAuth'];
      let contentTypes = [];
      let accepts = ['application/json'];
      let returnType = Object;
      return this.apiClient.callApi(
        '/public/get_instruments', 'GET',
        pathParams, queryParams, headerParams, formParams, postBody,
        authNames, contentTypes, accepts, returnType, null, callback
      );
    }

    /**
     * Callback function to receive the result of the publicGetLastSettlementsByCurrencyGet operation.
     * @callback module:api/PublicApi~publicGetLastSettlementsByCurrencyGetCallback
     * @param {String} error Error message, if any.
     * @param {Object} data The data returned by the service call.
     * @param {String} response The complete HTTP response.
     */

    /**
     * Retrieves historical settlement, delivery and bankruptcy events coming from all instruments within given currency.
     * @param {module:model/String} currency The currency symbol
     * @param {Object} opts Optional parameters
     * @param {module:model/String} opts.type Settlement type
     * @param {Number} opts.count Number of requested items, default - `20`
     * @param {String} opts.continuation Continuation token for pagination
     * @param {Number} opts.searchStartTimestamp The latest timestamp to return result for
     * @param {module:api/PublicApi~publicGetLastSettlementsByCurrencyGetCallback} callback The callback function, accepting three arguments: error, data, response
     * data is of type: {@link Object}
     */
    publicGetLastSettlementsByCurrencyGet(currency, opts, callback) {
      opts = opts || {};
      let postBody = null;
      // verify the required parameter 'currency' is set
      if (currency === undefined || currency === null) {
        throw new Error("Missing the required parameter 'currency' when calling publicGetLastSettlementsByCurrencyGet");
      }

      let pathParams = {
      };
      let queryParams = {
        'currency': currency,
        'type': opts['type'],
        'count': opts['count'],
        'continuation': opts['continuation'],
        'search_start_timestamp': opts['searchStartTimestamp']
      };
      let headerParams = {
      };
      let formParams = {
      };

      let authNames = ['bearerAuth'];
      let contentTypes = [];
      let accepts = ['application/json'];
      let returnType = Object;
      return this.apiClient.callApi(
        '/public/get_last_settlements_by_currency', 'GET',
        pathParams, queryParams, headerParams, formParams, postBody,
        authNames, contentTypes, accepts, returnType, null, callback
      );
    }

    /**
     * Callback function to receive the result of the publicGetLastSettlementsByInstrumentGet operation.
     * @callback module:api/PublicApi~publicGetLastSettlementsByInstrumentGetCallback
     * @param {String} error Error message, if any.
     * @param {Object} data The data returned by the service call.
     * @param {String} response The complete HTTP response.
     */

    /**
     * Retrieves historical public settlement, delivery and bankruptcy events filtered by instrument name.
     * @param {String} instrumentName Instrument name
     * @param {Object} opts Optional parameters
     * @param {module:model/String} opts.type Settlement type
     * @param {Number} opts.count Number of requested items, default - `20`
     * @param {String} opts.continuation Continuation token for pagination
     * @param {Number} opts.searchStartTimestamp The latest timestamp to return result for
     * @param {module:api/PublicApi~publicGetLastSettlementsByInstrumentGetCallback} callback The callback function, accepting three arguments: error, data, response
     * data is of type: {@link Object}
     */
    publicGetLastSettlementsByInstrumentGet(instrumentName, opts, callback) {
      opts = opts || {};
      let postBody = null;
      // verify the required parameter 'instrumentName' is set
      if (instrumentName === undefined || instrumentName === null) {
        throw new Error("Missing the required parameter 'instrumentName' when calling publicGetLastSettlementsByInstrumentGet");
      }

      let pathParams = {
      };
      let queryParams = {
        'instrument_name': instrumentName,
        'type': opts['type'],
        'count': opts['count'],
        'continuation': opts['continuation'],
        'search_start_timestamp': opts['searchStartTimestamp']
      };
      let headerParams = {
      };
      let formParams = {
      };

      let authNames = ['bearerAuth'];
      let contentTypes = [];
      let accepts = ['application/json'];
      let returnType = Object;
      return this.apiClient.callApi(
        '/public/get_last_settlements_by_instrument', 'GET',
        pathParams, queryParams, headerParams, formParams, postBody,
        authNames, contentTypes, accepts, returnType, null, callback
      );
    }

    /**
     * Callback function to receive the result of the publicGetLastTradesByCurrencyAndTimeGet operation.
     * @callback module:api/PublicApi~publicGetLastTradesByCurrencyAndTimeGetCallback
     * @param {String} error Error message, if any.
     * @param {Object} data The data returned by the service call.
     * @param {String} response The complete HTTP response.
     */

    /**
     * Retrieve the latest trades that have occurred for instruments in a specific currency symbol and within given time range.
     * @param {module:model/String} currency The currency symbol
     * @param {Number} startTimestamp The earliest timestamp to return result for
     * @param {Number} endTimestamp The most recent timestamp to return result for
     * @param {Object} opts Optional parameters
     * @param {module:model/String} opts.kind Instrument kind, if not provided instruments of all kinds are considered
     * @param {Number} opts.count Number of requested items, default - `10`
     * @param {Boolean} opts.includeOld Include trades older than 7 days, default - `false`
     * @param {module:model/String} opts.sorting Direction of results sorting (`default` value means no sorting, results will be returned in order in which they left the database)
     * @param {module:api/PublicApi~publicGetLastTradesByCurrencyAndTimeGetCallback} callback The callback function, accepting three arguments: error, data, response
     * data is of type: {@link Object}
     */
    publicGetLastTradesByCurrencyAndTimeGet(currency, startTimestamp, endTimestamp, opts, callback) {
      opts = opts || {};
      let postBody = null;
      // verify the required parameter 'currency' is set
      if (currency === undefined || currency === null) {
        throw new Error("Missing the required parameter 'currency' when calling publicGetLastTradesByCurrencyAndTimeGet");
      }
      // verify the required parameter 'startTimestamp' is set
      if (startTimestamp === undefined || startTimestamp === null) {
        throw new Error("Missing the required parameter 'startTimestamp' when calling publicGetLastTradesByCurrencyAndTimeGet");
      }
      // verify the required parameter 'endTimestamp' is set
      if (endTimestamp === undefined || endTimestamp === null) {
        throw new Error("Missing the required parameter 'endTimestamp' when calling publicGetLastTradesByCurrencyAndTimeGet");
      }

      let pathParams = {
      };
      let queryParams = {
        'currency': currency,
        'kind': opts['kind'],
        'start_timestamp': startTimestamp,
        'end_timestamp': endTimestamp,
        'count': opts['count'],
        'include_old': opts['includeOld'],
        'sorting': opts['sorting']
      };
      let headerParams = {
      };
      let formParams = {
      };

      let authNames = ['bearerAuth'];
      let contentTypes = [];
      let accepts = ['application/json'];
      let returnType = Object;
      return this.apiClient.callApi(
        '/public/get_last_trades_by_currency_and_time', 'GET',
        pathParams, queryParams, headerParams, formParams, postBody,
        authNames, contentTypes, accepts, returnType, null, callback
      );
    }

    /**
     * Callback function to receive the result of the publicGetLastTradesByCurrencyGet operation.
     * @callback module:api/PublicApi~publicGetLastTradesByCurrencyGetCallback
     * @param {String} error Error message, if any.
     * @param {Object} data The data returned by the service call.
     * @param {String} response The complete HTTP response.
     */

    /**
     * Retrieve the latest trades that have occurred for instruments in a specific currency symbol.
     * @param {module:model/String} currency The currency symbol
     * @param {Object} opts Optional parameters
     * @param {module:model/String} opts.kind Instrument kind, if not provided instruments of all kinds are considered
     * @param {String} opts.startId The ID number of the first trade to be returned
     * @param {String} opts.endId The ID number of the last trade to be returned
     * @param {Number} opts.count Number of requested items, default - `10`
     * @param {Boolean} opts.includeOld Include trades older than 7 days, default - `false`
     * @param {module:model/String} opts.sorting Direction of results sorting (`default` value means no sorting, results will be returned in order in which they left the database)
     * @param {module:api/PublicApi~publicGetLastTradesByCurrencyGetCallback} callback The callback function, accepting three arguments: error, data, response
     * data is of type: {@link Object}
     */
    publicGetLastTradesByCurrencyGet(currency, opts, callback) {
      opts = opts || {};
      let postBody = null;
      // verify the required parameter 'currency' is set
      if (currency === undefined || currency === null) {
        throw new Error("Missing the required parameter 'currency' when calling publicGetLastTradesByCurrencyGet");
      }

      let pathParams = {
      };
      let queryParams = {
        'currency': currency,
        'kind': opts['kind'],
        'start_id': opts['startId'],
        'end_id': opts['endId'],
        'count': opts['count'],
        'include_old': opts['includeOld'],
        'sorting': opts['sorting']
      };
      let headerParams = {
      };
      let formParams = {
      };

      let authNames = ['bearerAuth'];
      let contentTypes = [];
      let accepts = ['application/json'];
      let returnType = Object;
      return this.apiClient.callApi(
        '/public/get_last_trades_by_currency', 'GET',
        pathParams, queryParams, headerParams, formParams, postBody,
        authNames, contentTypes, accepts, returnType, null, callback
      );
    }

    /**
     * Callback function to receive the result of the publicGetLastTradesByInstrumentAndTimeGet operation.
     * @callback module:api/PublicApi~publicGetLastTradesByInstrumentAndTimeGetCallback
     * @param {String} error Error message, if any.
     * @param {Object} data The data returned by the service call.
     * @param {String} response The complete HTTP response.
     */

    /**
     * Retrieve the latest trades that have occurred for a specific instrument and within given time range.
     * @param {String} instrumentName Instrument name
     * @param {Number} startTimestamp The earliest timestamp to return result for
     * @param {Number} endTimestamp The most recent timestamp to return result for
     * @param {Object} opts Optional parameters
     * @param {Number} opts.count Number of requested items, default - `10`
     * @param {Boolean} opts.includeOld Include trades older than 7 days, default - `false`
     * @param {module:model/String} opts.sorting Direction of results sorting (`default` value means no sorting, results will be returned in order in which they left the database)
     * @param {module:api/PublicApi~publicGetLastTradesByInstrumentAndTimeGetCallback} callback The callback function, accepting three arguments: error, data, response
     * data is of type: {@link Object}
     */
    publicGetLastTradesByInstrumentAndTimeGet(instrumentName, startTimestamp, endTimestamp, opts, callback) {
      opts = opts || {};
      let postBody = null;
      // verify the required parameter 'instrumentName' is set
      if (instrumentName === undefined || instrumentName === null) {
        throw new Error("Missing the required parameter 'instrumentName' when calling publicGetLastTradesByInstrumentAndTimeGet");
      }
      // verify the required parameter 'startTimestamp' is set
      if (startTimestamp === undefined || startTimestamp === null) {
        throw new Error("Missing the required parameter 'startTimestamp' when calling publicGetLastTradesByInstrumentAndTimeGet");
      }
      // verify the required parameter 'endTimestamp' is set
      if (endTimestamp === undefined || endTimestamp === null) {
        throw new Error("Missing the required parameter 'endTimestamp' when calling publicGetLastTradesByInstrumentAndTimeGet");
      }

      let pathParams = {
      };
      let queryParams = {
        'instrument_name': instrumentName,
        'start_timestamp': startTimestamp,
        'end_timestamp': endTimestamp,
        'count': opts['count'],
        'include_old': opts['includeOld'],
        'sorting': opts['sorting']
      };
      let headerParams = {
      };
      let formParams = {
      };

      let authNames = ['bearerAuth'];
      let contentTypes = [];
      let accepts = ['application/json'];
      let returnType = Object;
      return this.apiClient.callApi(
        '/public/get_last_trades_by_instrument_and_time', 'GET',
        pathParams, queryParams, headerParams, formParams, postBody,
        authNames, contentTypes, accepts, returnType, null, callback
      );
    }

    /**
     * Callback function to receive the result of the publicGetLastTradesByInstrumentGet operation.
     * @callback module:api/PublicApi~publicGetLastTradesByInstrumentGetCallback
     * @param {String} error Error message, if any.
     * @param {Object} data The data returned by the service call.
     * @param {String} response The complete HTTP response.
     */

    /**
     * Retrieve the latest trades that have occurred for a specific instrument.
     * @param {String} instrumentName Instrument name
     * @param {Object} opts Optional parameters
     * @param {Number} opts.startSeq The sequence number of the first trade to be returned
     * @param {Number} opts.endSeq The sequence number of the last trade to be returned
     * @param {Number} opts.count Number of requested items, default - `10`
     * @param {Boolean} opts.includeOld Include trades older than 7 days, default - `false`
     * @param {module:model/String} opts.sorting Direction of results sorting (`default` value means no sorting, results will be returned in order in which they left the database)
     * @param {module:api/PublicApi~publicGetLastTradesByInstrumentGetCallback} callback The callback function, accepting three arguments: error, data, response
     * data is of type: {@link Object}
     */
    publicGetLastTradesByInstrumentGet(instrumentName, opts, callback) {
      opts = opts || {};
      let postBody = null;
      // verify the required parameter 'instrumentName' is set
      if (instrumentName === undefined || instrumentName === null) {
        throw new Error("Missing the required parameter 'instrumentName' when calling publicGetLastTradesByInstrumentGet");
      }

      let pathParams = {
      };
      let queryParams = {
        'instrument_name': instrumentName,
        'start_seq': opts['startSeq'],
        'end_seq': opts['endSeq'],
        'count': opts['count'],
        'include_old': opts['includeOld'],
        'sorting': opts['sorting']
      };
      let headerParams = {
      };
      let formParams = {
      };

      let authNames = ['bearerAuth'];
      let contentTypes = [];
      let accepts = ['application/json'];
      let returnType = Object;
      return this.apiClient.callApi(
        '/public/get_last_trades_by_instrument', 'GET',
        pathParams, queryParams, headerParams, formParams, postBody,
        authNames, contentTypes, accepts, returnType, null, callback
      );
    }

    /**
     * Callback function to receive the result of the publicGetOrderBookGet operation.
     * @callback module:api/PublicApi~publicGetOrderBookGetCallback
     * @param {String} error Error message, if any.
     * @param {Object} data The data returned by the service call.
     * @param {String} response The complete HTTP response.
     */

    /**
     * Retrieves the order book, along with other market values for a given instrument.
     * @param {String} instrumentName The instrument name for which to retrieve the order book, see [`getinstruments`](#getinstruments) to obtain instrument names.
     * @param {Object} opts Optional parameters
     * @param {Number} opts.depth The number of entries to return for bids and asks.
     * @param {module:api/PublicApi~publicGetOrderBookGetCallback} callback The callback function, accepting three arguments: error, data, response
     * data is of type: {@link Object}
     */
    publicGetOrderBookGet(instrumentName, opts, callback) {
      opts = opts || {};
      let postBody = null;
      // verify the required parameter 'instrumentName' is set
      if (instrumentName === undefined || instrumentName === null) {
        throw new Error("Missing the required parameter 'instrumentName' when calling publicGetOrderBookGet");
      }

      let pathParams = {
      };
      let queryParams = {
        'instrument_name': instrumentName,
        'depth': opts['depth']
      };
      let headerParams = {
      };
      let formParams = {
      };

      let authNames = ['bearerAuth'];
      let contentTypes = [];
      let accepts = ['application/json'];
      let returnType = Object;
      return this.apiClient.callApi(
        '/public/get_order_book', 'GET',
        pathParams, queryParams, headerParams, formParams, postBody,
        authNames, contentTypes, accepts, returnType, null, callback
      );
    }

    /**
     * Callback function to receive the result of the publicGetTimeGet operation.
     * @callback module:api/PublicApi~publicGetTimeGetCallback
     * @param {String} error Error message, if any.
     * @param {Object} data The data returned by the service call.
     * @param {String} response The complete HTTP response.
     */

    /**
     * Retrieves the current time (in milliseconds). This API endpoint can be used to check the clock skew between your software and Deribit's systems.
     * @param {module:api/PublicApi~publicGetTimeGetCallback} callback The callback function, accepting three arguments: error, data, response
     * data is of type: {@link Object}
     */
    publicGetTimeGet(callback) {
      let postBody = null;

      let pathParams = {
      };
      let queryParams = {
      };
      let headerParams = {
      };
      let formParams = {
      };

      let authNames = ['bearerAuth'];
      let contentTypes = [];
      let accepts = ['application/json'];
      let returnType = Object;
      return this.apiClient.callApi(
        '/public/get_time', 'GET',
        pathParams, queryParams, headerParams, formParams, postBody,
        authNames, contentTypes, accepts, returnType, null, callback
      );
    }

    /**
     * Callback function to receive the result of the publicGetTradeVolumesGet operation.
     * @callback module:api/PublicApi~publicGetTradeVolumesGetCallback
     * @param {String} error Error message, if any.
     * @param {Object} data The data returned by the service call.
     * @param {String} response The complete HTTP response.
     */

    /**
     * Retrieves aggregated 24h trade volumes for different instrument types and currencies.
     * @param {module:api/PublicApi~publicGetTradeVolumesGetCallback} callback The callback function, accepting three arguments: error, data, response
     * data is of type: {@link Object}
     */
    publicGetTradeVolumesGet(callback) {
      let postBody = null;

      let pathParams = {
      };
      let queryParams = {
      };
      let headerParams = {
      };
      let formParams = {
      };

      let authNames = ['bearerAuth'];
      let contentTypes = [];
      let accepts = ['application/json'];
      let returnType = Object;
      return this.apiClient.callApi(
        '/public/get_trade_volumes', 'GET',
        pathParams, queryParams, headerParams, formParams, postBody,
        authNames, contentTypes, accepts, returnType, null, callback
      );
    }

    /**
     * Callback function to receive the result of the publicGetTradingviewChartDataGet operation.
     * @callback module:api/PublicApi~publicGetTradingviewChartDataGetCallback
     * @param {String} error Error message, if any.
     * @param {Object} data The data returned by the service call.
     * @param {String} response The complete HTTP response.
     */

    /**
     * Publicly available market data used to generate a TradingView candle chart.
     * @param {String} instrumentName Instrument name
     * @param {Number} startTimestamp The earliest timestamp to return result for
     * @param {Number} endTimestamp The most recent timestamp to return result for
     * @param {module:api/PublicApi~publicGetTradingviewChartDataGetCallback} callback The callback function, accepting three arguments: error, data, response
     * data is of type: {@link Object}
     */
    publicGetTradingviewChartDataGet(instrumentName, startTimestamp, endTimestamp, callback) {
      let postBody = null;
      // verify the required parameter 'instrumentName' is set
      if (instrumentName === undefined || instrumentName === null) {
        throw new Error("Missing the required parameter 'instrumentName' when calling publicGetTradingviewChartDataGet");
      }
      // verify the required parameter 'startTimestamp' is set
      if (startTimestamp === undefined || startTimestamp === null) {
        throw new Error("Missing the required parameter 'startTimestamp' when calling publicGetTradingviewChartDataGet");
      }
      // verify the required parameter 'endTimestamp' is set
      if (endTimestamp === undefined || endTimestamp === null) {
        throw new Error("Missing the required parameter 'endTimestamp' when calling publicGetTradingviewChartDataGet");
      }

      let pathParams = {
      };
      let queryParams = {
        'instrument_name': instrumentName,
        'start_timestamp': startTimestamp,
        'end_timestamp': endTimestamp
      };
      let headerParams = {
      };
      let formParams = {
      };

      let authNames = ['bearerAuth'];
      let contentTypes = [];
      let accepts = ['application/json'];
      let returnType = Object;
      return this.apiClient.callApi(
        '/public/get_tradingview_chart_data', 'GET',
        pathParams, queryParams, headerParams, formParams, postBody,
        authNames, contentTypes, accepts, returnType, null, callback
      );
    }

    /**
     * Callback function to receive the result of the publicTestGet operation.
     * @callback module:api/PublicApi~publicTestGetCallback
     * @param {String} error Error message, if any.
     * @param {Object} data The data returned by the service call.
     * @param {String} response The complete HTTP response.
     */

    /**
     * Tests the connection to the API server, and returns its version. You can use this to make sure the API is reachable, and matches the expected version.
     * @param {Object} opts Optional parameters
     * @param {module:model/String} opts.expectedResult The value \"exception\" will trigger an error response. This may be useful for testing wrapper libraries.
     * @param {module:api/PublicApi~publicTestGetCallback} callback The callback function, accepting three arguments: error, data, response
     * data is of type: {@link Object}
     */
    publicTestGet(opts, callback) {
      opts = opts || {};
      let postBody = null;

      let pathParams = {
      };
      let queryParams = {
        'expected_result': opts['expectedResult']
      };
      let headerParams = {
      };
      let formParams = {
      };

      let authNames = ['bearerAuth'];
      let contentTypes = [];
      let accepts = ['application/json'];
      let returnType = Object;
      return this.apiClient.callApi(
        '/public/test', 'GET',
        pathParams, queryParams, headerParams, formParams, postBody,
        authNames, contentTypes, accepts, returnType, null, callback
      );
    }

    /**
     * Callback function to receive the result of the publicTickerGet operation.
     * @callback module:api/PublicApi~publicTickerGetCallback
     * @param {String} error Error message, if any.
     * @param {Object} data The data returned by the service call.
     * @param {String} response The complete HTTP response.
     */

    /**
     * Get ticker for an instrument.
     * @param {String} instrumentName Instrument name
     * @param {module:api/PublicApi~publicTickerGetCallback} callback The callback function, accepting three arguments: error, data, response
     * data is of type: {@link Object}
     */
    publicTickerGet(instrumentName, callback) {
      let postBody = null;
      // verify the required parameter 'instrumentName' is set
      if (instrumentName === undefined || instrumentName === null) {
        throw new Error("Missing the required parameter 'instrumentName' when calling publicTickerGet");
      }

      let pathParams = {
      };
      let queryParams = {
        'instrument_name': instrumentName
      };
      let headerParams = {
      };
      let formParams = {
      };

      let authNames = ['bearerAuth'];
      let contentTypes = [];
      let accepts = ['application/json'];
      let returnType = Object;
      return this.apiClient.callApi(
        '/public/ticker', 'GET',
        pathParams, queryParams, headerParams, formParams, postBody,
        authNames, contentTypes, accepts, returnType, null, callback
      );
    }

    /**
     * Callback function to receive the result of the publicValidateFieldGet operation.
     * @callback module:api/PublicApi~publicValidateFieldGetCallback
     * @param {String} error Error message, if any.
     * @param {Object} data The data returned by the service call.
     * @param {String} response The complete HTTP response.
     */

    /**
     * Method used to introduce the client software connected to Deribit platform over websocket. Provided data may have an impact on the maintained connection and will be collected for internal statistical purposes. In response, Deribit will also introduce itself.
     * @param {String} field Name of the field to be validated, examples: postal_code, date_of_birth
     * @param {String} value Value to be checked
     * @param {Object} opts Optional parameters
     * @param {String} opts.value2 Additional value to be compared with
     * @param {module:api/PublicApi~publicValidateFieldGetCallback} callback The callback function, accepting three arguments: error, data, response
     * data is of type: {@link Object}
     */
    publicValidateFieldGet(field, value, opts, callback) {
      opts = opts || {};
      let postBody = null;
      // verify the required parameter 'field' is set
      if (field === undefined || field === null) {
        throw new Error("Missing the required parameter 'field' when calling publicValidateFieldGet");
      }
      // verify the required parameter 'value' is set
      if (value === undefined || value === null) {
        throw new Error("Missing the required parameter 'value' when calling publicValidateFieldGet");
      }

      let pathParams = {
      };
      let queryParams = {
        'field': field,
        'value': value,
        'value2': opts['value2']
      };
      let headerParams = {
      };
      let formParams = {
      };

      let authNames = ['bearerAuth'];
      let contentTypes = [];
      let accepts = ['application/json'];
      let returnType = Object;
      return this.apiClient.callApi(
        '/public/validate_field', 'GET',
        pathParams, queryParams, headerParams, formParams, postBody,
        authNames, contentTypes, accepts, returnType, null, callback
      );
    }


}
