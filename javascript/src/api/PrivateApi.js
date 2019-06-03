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
* Private service.
* @module api/PrivateApi
* @version 2.0.0
*/
export default class PrivateApi {

    /**
    * Constructs a new PrivateApi. 
    * @alias module:api/PrivateApi
    * @class
    * @param {module:ApiClient} [apiClient] Optional API client implementation to use,
    * default to {@link module:ApiClient#instance} if unspecified.
    */
    constructor(apiClient) {
        this.apiClient = apiClient || ApiClient.instance;
    }


    /**
     * Callback function to receive the result of the privateAddToAddressBookGet operation.
     * @callback module:api/PrivateApi~privateAddToAddressBookGetCallback
     * @param {String} error Error message, if any.
     * @param {Object} data The data returned by the service call.
     * @param {String} response The complete HTTP response.
     */

    /**
     * Adds new entry to address book of given type
     * @param {module:model/String} currency The currency symbol
     * @param {module:model/String} type Address book type
     * @param {String} address Address in currency format, it must be in address book
     * @param {String} name Name of address book entry
     * @param {Object} opts Optional parameters
     * @param {String} opts.tfa TFA code, required when TFA is enabled for current account
     * @param {module:api/PrivateApi~privateAddToAddressBookGetCallback} callback The callback function, accepting three arguments: error, data, response
     * data is of type: {@link Object}
     */
    privateAddToAddressBookGet(currency, type, address, name, opts, callback) {
      opts = opts || {};
      let postBody = null;
      // verify the required parameter 'currency' is set
      if (currency === undefined || currency === null) {
        throw new Error("Missing the required parameter 'currency' when calling privateAddToAddressBookGet");
      }
      // verify the required parameter 'type' is set
      if (type === undefined || type === null) {
        throw new Error("Missing the required parameter 'type' when calling privateAddToAddressBookGet");
      }
      // verify the required parameter 'address' is set
      if (address === undefined || address === null) {
        throw new Error("Missing the required parameter 'address' when calling privateAddToAddressBookGet");
      }
      // verify the required parameter 'name' is set
      if (name === undefined || name === null) {
        throw new Error("Missing the required parameter 'name' when calling privateAddToAddressBookGet");
      }

      let pathParams = {
      };
      let queryParams = {
        'currency': currency,
        'type': type,
        'address': address,
        'name': name,
        'tfa': opts['tfa']
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
        '/private/add_to_address_book', 'GET',
        pathParams, queryParams, headerParams, formParams, postBody,
        authNames, contentTypes, accepts, returnType, null, callback
      );
    }

    /**
     * Callback function to receive the result of the privateBuyGet operation.
     * @callback module:api/PrivateApi~privateBuyGetCallback
     * @param {String} error Error message, if any.
     * @param {Object} data The data returned by the service call.
     * @param {String} response The complete HTTP response.
     */

    /**
     * Places a buy order for an instrument.
     * @param {String} instrumentName Instrument name
     * @param {Number} amount It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH
     * @param {Object} opts Optional parameters
     * @param {module:model/String} opts.type The order type, default: `\"limit\"`
     * @param {String} opts.label user defined label for the order (maximum 32 characters)
     * @param {Number} opts.price <p>The order price in base currency (Only for limit and stop_limit orders)</p> <p>When adding order with advanced=usd, the field price should be the option price value in USD.</p> <p>When adding order with advanced=implv, the field price should be a value of implied volatility in percentages. For example,  price=100, means implied volatility of 100%</p>
     * @param {module:model/String} opts.timeInForce <p>Specifies how long the order remains in effect. Default `\"good_til_cancelled\"`</p> <ul> <li>`\"good_til_cancelled\"` - unfilled order remains in order book until cancelled</li> <li>`\"fill_or_kill\"` - execute a transaction immediately and completely or not at all</li> <li>`\"immediate_or_cancel\"` - execute a transaction immediately, and any portion of the order that cannot be immediately filled is cancelled</li> </ul> (default to 'good_til_cancelled')
     * @param {Number} opts.maxShow Maximum amount within an order to be shown to other customers, `0` for invisible order (default to 1)
     * @param {Boolean} opts.postOnly <p>If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.</p> <p>Only valid in combination with time_in_force=`\"good_til_cancelled\"`</p> (default to true)
     * @param {Boolean} opts.reduceOnly If `true`, the order is considered reduce-only which is intended to only reduce a current position (default to false)
     * @param {Number} opts.stopPrice Stop price, required for stop limit orders (Only for stop orders)
     * @param {module:model/String} opts.trigger Defines trigger type, required for `\"stop_limit\"` order type
     * @param {module:model/String} opts.advanced Advanced option order type. (Only for options)
     * @param {module:api/PrivateApi~privateBuyGetCallback} callback The callback function, accepting three arguments: error, data, response
     * data is of type: {@link Object}
     */
    privateBuyGet(instrumentName, amount, opts, callback) {
      opts = opts || {};
      let postBody = null;
      // verify the required parameter 'instrumentName' is set
      if (instrumentName === undefined || instrumentName === null) {
        throw new Error("Missing the required parameter 'instrumentName' when calling privateBuyGet");
      }
      // verify the required parameter 'amount' is set
      if (amount === undefined || amount === null) {
        throw new Error("Missing the required parameter 'amount' when calling privateBuyGet");
      }

      let pathParams = {
      };
      let queryParams = {
        'instrument_name': instrumentName,
        'amount': amount,
        'type': opts['type'],
        'label': opts['label'],
        'price': opts['price'],
        'time_in_force': opts['timeInForce'],
        'max_show': opts['maxShow'],
        'post_only': opts['postOnly'],
        'reduce_only': opts['reduceOnly'],
        'stop_price': opts['stopPrice'],
        'trigger': opts['trigger'],
        'advanced': opts['advanced']
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
        '/private/buy', 'GET',
        pathParams, queryParams, headerParams, formParams, postBody,
        authNames, contentTypes, accepts, returnType, null, callback
      );
    }

    /**
     * Callback function to receive the result of the privateCancelAllByCurrencyGet operation.
     * @callback module:api/PrivateApi~privateCancelAllByCurrencyGetCallback
     * @param {String} error Error message, if any.
     * @param {Object} data The data returned by the service call.
     * @param {String} response The complete HTTP response.
     */

    /**
     * Cancels all orders by currency, optionally filtered by instrument kind and/or order type.
     * @param {module:model/String} currency The currency symbol
     * @param {Object} opts Optional parameters
     * @param {module:model/String} opts.kind Instrument kind, if not provided instruments of all kinds are considered
     * @param {module:model/String} opts.type Order type - limit, stop or all, default - `all`
     * @param {module:api/PrivateApi~privateCancelAllByCurrencyGetCallback} callback The callback function, accepting three arguments: error, data, response
     * data is of type: {@link Object}
     */
    privateCancelAllByCurrencyGet(currency, opts, callback) {
      opts = opts || {};
      let postBody = null;
      // verify the required parameter 'currency' is set
      if (currency === undefined || currency === null) {
        throw new Error("Missing the required parameter 'currency' when calling privateCancelAllByCurrencyGet");
      }

      let pathParams = {
      };
      let queryParams = {
        'currency': currency,
        'kind': opts['kind'],
        'type': opts['type']
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
        '/private/cancel_all_by_currency', 'GET',
        pathParams, queryParams, headerParams, formParams, postBody,
        authNames, contentTypes, accepts, returnType, null, callback
      );
    }

    /**
     * Callback function to receive the result of the privateCancelAllByInstrumentGet operation.
     * @callback module:api/PrivateApi~privateCancelAllByInstrumentGetCallback
     * @param {String} error Error message, if any.
     * @param {Object} data The data returned by the service call.
     * @param {String} response The complete HTTP response.
     */

    /**
     * Cancels all orders by instrument, optionally filtered by order type.
     * @param {String} instrumentName Instrument name
     * @param {Object} opts Optional parameters
     * @param {module:model/String} opts.type Order type - limit, stop or all, default - `all`
     * @param {module:api/PrivateApi~privateCancelAllByInstrumentGetCallback} callback The callback function, accepting three arguments: error, data, response
     * data is of type: {@link Object}
     */
    privateCancelAllByInstrumentGet(instrumentName, opts, callback) {
      opts = opts || {};
      let postBody = null;
      // verify the required parameter 'instrumentName' is set
      if (instrumentName === undefined || instrumentName === null) {
        throw new Error("Missing the required parameter 'instrumentName' when calling privateCancelAllByInstrumentGet");
      }

      let pathParams = {
      };
      let queryParams = {
        'instrument_name': instrumentName,
        'type': opts['type']
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
        '/private/cancel_all_by_instrument', 'GET',
        pathParams, queryParams, headerParams, formParams, postBody,
        authNames, contentTypes, accepts, returnType, null, callback
      );
    }

    /**
     * Callback function to receive the result of the privateCancelAllGet operation.
     * @callback module:api/PrivateApi~privateCancelAllGetCallback
     * @param {String} error Error message, if any.
     * @param {Object} data The data returned by the service call.
     * @param {String} response The complete HTTP response.
     */

    /**
     * This method cancels all users orders and stop orders within all currencies and instrument kinds.
     * @param {module:api/PrivateApi~privateCancelAllGetCallback} callback The callback function, accepting three arguments: error, data, response
     * data is of type: {@link Object}
     */
    privateCancelAllGet(callback) {
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
        '/private/cancel_all', 'GET',
        pathParams, queryParams, headerParams, formParams, postBody,
        authNames, contentTypes, accepts, returnType, null, callback
      );
    }

    /**
     * Callback function to receive the result of the privateCancelGet operation.
     * @callback module:api/PrivateApi~privateCancelGetCallback
     * @param {String} error Error message, if any.
     * @param {Object} data The data returned by the service call.
     * @param {String} response The complete HTTP response.
     */

    /**
     * Cancel an order, specified by order id
     * @param {String} orderId The order id
     * @param {module:api/PrivateApi~privateCancelGetCallback} callback The callback function, accepting three arguments: error, data, response
     * data is of type: {@link Object}
     */
    privateCancelGet(orderId, callback) {
      let postBody = null;
      // verify the required parameter 'orderId' is set
      if (orderId === undefined || orderId === null) {
        throw new Error("Missing the required parameter 'orderId' when calling privateCancelGet");
      }

      let pathParams = {
      };
      let queryParams = {
        'order_id': orderId
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
        '/private/cancel', 'GET',
        pathParams, queryParams, headerParams, formParams, postBody,
        authNames, contentTypes, accepts, returnType, null, callback
      );
    }

    /**
     * Callback function to receive the result of the privateCancelTransferByIdGet operation.
     * @callback module:api/PrivateApi~privateCancelTransferByIdGetCallback
     * @param {String} error Error message, if any.
     * @param {Object} data The data returned by the service call.
     * @param {String} response The complete HTTP response.
     */

    /**
     * Cancel transfer
     * @param {module:model/String} currency The currency symbol
     * @param {Number} id Id of transfer
     * @param {Object} opts Optional parameters
     * @param {String} opts.tfa TFA code, required when TFA is enabled for current account
     * @param {module:api/PrivateApi~privateCancelTransferByIdGetCallback} callback The callback function, accepting three arguments: error, data, response
     * data is of type: {@link Object}
     */
    privateCancelTransferByIdGet(currency, id, opts, callback) {
      opts = opts || {};
      let postBody = null;
      // verify the required parameter 'currency' is set
      if (currency === undefined || currency === null) {
        throw new Error("Missing the required parameter 'currency' when calling privateCancelTransferByIdGet");
      }
      // verify the required parameter 'id' is set
      if (id === undefined || id === null) {
        throw new Error("Missing the required parameter 'id' when calling privateCancelTransferByIdGet");
      }

      let pathParams = {
      };
      let queryParams = {
        'currency': currency,
        'id': id,
        'tfa': opts['tfa']
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
        '/private/cancel_transfer_by_id', 'GET',
        pathParams, queryParams, headerParams, formParams, postBody,
        authNames, contentTypes, accepts, returnType, null, callback
      );
    }

    /**
     * Callback function to receive the result of the privateCancelWithdrawalGet operation.
     * @callback module:api/PrivateApi~privateCancelWithdrawalGetCallback
     * @param {String} error Error message, if any.
     * @param {Object} data The data returned by the service call.
     * @param {String} response The complete HTTP response.
     */

    /**
     * Cancels withdrawal request
     * @param {module:model/String} currency The currency symbol
     * @param {Number} id The withdrawal id
     * @param {module:api/PrivateApi~privateCancelWithdrawalGetCallback} callback The callback function, accepting three arguments: error, data, response
     * data is of type: {@link Object}
     */
    privateCancelWithdrawalGet(currency, id, callback) {
      let postBody = null;
      // verify the required parameter 'currency' is set
      if (currency === undefined || currency === null) {
        throw new Error("Missing the required parameter 'currency' when calling privateCancelWithdrawalGet");
      }
      // verify the required parameter 'id' is set
      if (id === undefined || id === null) {
        throw new Error("Missing the required parameter 'id' when calling privateCancelWithdrawalGet");
      }

      let pathParams = {
      };
      let queryParams = {
        'currency': currency,
        'id': id
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
        '/private/cancel_withdrawal', 'GET',
        pathParams, queryParams, headerParams, formParams, postBody,
        authNames, contentTypes, accepts, returnType, null, callback
      );
    }

    /**
     * Callback function to receive the result of the privateChangeSubaccountNameGet operation.
     * @callback module:api/PrivateApi~privateChangeSubaccountNameGetCallback
     * @param {String} error Error message, if any.
     * @param {Object} data The data returned by the service call.
     * @param {String} response The complete HTTP response.
     */

    /**
     * Change the user name for a subaccount
     * @param {Number} sid The user id for the subaccount
     * @param {String} name The new user name
     * @param {module:api/PrivateApi~privateChangeSubaccountNameGetCallback} callback The callback function, accepting three arguments: error, data, response
     * data is of type: {@link Object}
     */
    privateChangeSubaccountNameGet(sid, name, callback) {
      let postBody = null;
      // verify the required parameter 'sid' is set
      if (sid === undefined || sid === null) {
        throw new Error("Missing the required parameter 'sid' when calling privateChangeSubaccountNameGet");
      }
      // verify the required parameter 'name' is set
      if (name === undefined || name === null) {
        throw new Error("Missing the required parameter 'name' when calling privateChangeSubaccountNameGet");
      }

      let pathParams = {
      };
      let queryParams = {
        'sid': sid,
        'name': name
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
        '/private/change_subaccount_name', 'GET',
        pathParams, queryParams, headerParams, formParams, postBody,
        authNames, contentTypes, accepts, returnType, null, callback
      );
    }

    /**
     * Callback function to receive the result of the privateClosePositionGet operation.
     * @callback module:api/PrivateApi~privateClosePositionGetCallback
     * @param {String} error Error message, if any.
     * @param {Object} data The data returned by the service call.
     * @param {String} response The complete HTTP response.
     */

    /**
     * Makes closing position reduce only order .
     * @param {String} instrumentName Instrument name
     * @param {module:model/String} type The order type
     * @param {Object} opts Optional parameters
     * @param {Number} opts.price Optional price for limit order.
     * @param {module:api/PrivateApi~privateClosePositionGetCallback} callback The callback function, accepting three arguments: error, data, response
     * data is of type: {@link Object}
     */
    privateClosePositionGet(instrumentName, type, opts, callback) {
      opts = opts || {};
      let postBody = null;
      // verify the required parameter 'instrumentName' is set
      if (instrumentName === undefined || instrumentName === null) {
        throw new Error("Missing the required parameter 'instrumentName' when calling privateClosePositionGet");
      }
      // verify the required parameter 'type' is set
      if (type === undefined || type === null) {
        throw new Error("Missing the required parameter 'type' when calling privateClosePositionGet");
      }

      let pathParams = {
      };
      let queryParams = {
        'instrument_name': instrumentName,
        'type': type,
        'price': opts['price']
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
        '/private/close_position', 'GET',
        pathParams, queryParams, headerParams, formParams, postBody,
        authNames, contentTypes, accepts, returnType, null, callback
      );
    }

    /**
     * Callback function to receive the result of the privateCreateDepositAddressGet operation.
     * @callback module:api/PrivateApi~privateCreateDepositAddressGetCallback
     * @param {String} error Error message, if any.
     * @param {Object} data The data returned by the service call.
     * @param {String} response The complete HTTP response.
     */

    /**
     * Creates deposit address in currency
     * @param {module:model/String} currency The currency symbol
     * @param {module:api/PrivateApi~privateCreateDepositAddressGetCallback} callback The callback function, accepting three arguments: error, data, response
     * data is of type: {@link Object}
     */
    privateCreateDepositAddressGet(currency, callback) {
      let postBody = null;
      // verify the required parameter 'currency' is set
      if (currency === undefined || currency === null) {
        throw new Error("Missing the required parameter 'currency' when calling privateCreateDepositAddressGet");
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
        '/private/create_deposit_address', 'GET',
        pathParams, queryParams, headerParams, formParams, postBody,
        authNames, contentTypes, accepts, returnType, null, callback
      );
    }

    /**
     * Callback function to receive the result of the privateCreateSubaccountGet operation.
     * @callback module:api/PrivateApi~privateCreateSubaccountGetCallback
     * @param {String} error Error message, if any.
     * @param {Object} data The data returned by the service call.
     * @param {String} response The complete HTTP response.
     */

    /**
     * Create a new subaccount
     * @param {module:api/PrivateApi~privateCreateSubaccountGetCallback} callback The callback function, accepting three arguments: error, data, response
     * data is of type: {@link Object}
     */
    privateCreateSubaccountGet(callback) {
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
        '/private/create_subaccount', 'GET',
        pathParams, queryParams, headerParams, formParams, postBody,
        authNames, contentTypes, accepts, returnType, null, callback
      );
    }

    /**
     * Callback function to receive the result of the privateDisableTfaForSubaccountGet operation.
     * @callback module:api/PrivateApi~privateDisableTfaForSubaccountGetCallback
     * @param {String} error Error message, if any.
     * @param {Object} data The data returned by the service call.
     * @param {String} response The complete HTTP response.
     */

    /**
     * Disable two factor authentication for a subaccount.
     * @param {Number} sid The user id for the subaccount
     * @param {module:api/PrivateApi~privateDisableTfaForSubaccountGetCallback} callback The callback function, accepting three arguments: error, data, response
     * data is of type: {@link Object}
     */
    privateDisableTfaForSubaccountGet(sid, callback) {
      let postBody = null;
      // verify the required parameter 'sid' is set
      if (sid === undefined || sid === null) {
        throw new Error("Missing the required parameter 'sid' when calling privateDisableTfaForSubaccountGet");
      }

      let pathParams = {
      };
      let queryParams = {
        'sid': sid
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
        '/private/disable_tfa_for_subaccount', 'GET',
        pathParams, queryParams, headerParams, formParams, postBody,
        authNames, contentTypes, accepts, returnType, null, callback
      );
    }

    /**
     * Callback function to receive the result of the privateDisableTfaWithRecoveryCodeGet operation.
     * @callback module:api/PrivateApi~privateDisableTfaWithRecoveryCodeGetCallback
     * @param {String} error Error message, if any.
     * @param {Object} data The data returned by the service call.
     * @param {String} response The complete HTTP response.
     */

    /**
     * Disables TFA with one time recovery code
     * @param {String} password The password for the subaccount
     * @param {String} code One time recovery code
     * @param {module:api/PrivateApi~privateDisableTfaWithRecoveryCodeGetCallback} callback The callback function, accepting three arguments: error, data, response
     * data is of type: {@link Object}
     */
    privateDisableTfaWithRecoveryCodeGet(password, code, callback) {
      let postBody = null;
      // verify the required parameter 'password' is set
      if (password === undefined || password === null) {
        throw new Error("Missing the required parameter 'password' when calling privateDisableTfaWithRecoveryCodeGet");
      }
      // verify the required parameter 'code' is set
      if (code === undefined || code === null) {
        throw new Error("Missing the required parameter 'code' when calling privateDisableTfaWithRecoveryCodeGet");
      }

      let pathParams = {
      };
      let queryParams = {
        'password': password,
        'code': code
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
        '/private/disable_tfa_with_recovery_code', 'GET',
        pathParams, queryParams, headerParams, formParams, postBody,
        authNames, contentTypes, accepts, returnType, null, callback
      );
    }

    /**
     * Callback function to receive the result of the privateEditGet operation.
     * @callback module:api/PrivateApi~privateEditGetCallback
     * @param {String} error Error message, if any.
     * @param {Object} data The data returned by the service call.
     * @param {String} response The complete HTTP response.
     */

    /**
     * Change price, amount and/or other properties of an order.
     * @param {String} orderId The order id
     * @param {Number} amount It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH
     * @param {Number} price <p>The order price in base currency.</p> <p>When editing an option order with advanced=usd, the field price should be the option price value in USD.</p> <p>When editing an option order with advanced=implv, the field price should be a value of implied volatility in percentages. For example,  price=100, means implied volatility of 100%</p>
     * @param {Object} opts Optional parameters
     * @param {Boolean} opts.postOnly <p>If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.</p> <p>Only valid in combination with time_in_force=`\"good_til_cancelled\"`</p> (default to true)
     * @param {module:model/String} opts.advanced Advanced option order type. If you have posted an advanced option order, it is necessary to re-supply this parameter when editing it (Only for options)
     * @param {Number} opts.stopPrice Stop price, required for stop limit orders (Only for stop orders)
     * @param {module:api/PrivateApi~privateEditGetCallback} callback The callback function, accepting three arguments: error, data, response
     * data is of type: {@link Object}
     */
    privateEditGet(orderId, amount, price, opts, callback) {
      opts = opts || {};
      let postBody = null;
      // verify the required parameter 'orderId' is set
      if (orderId === undefined || orderId === null) {
        throw new Error("Missing the required parameter 'orderId' when calling privateEditGet");
      }
      // verify the required parameter 'amount' is set
      if (amount === undefined || amount === null) {
        throw new Error("Missing the required parameter 'amount' when calling privateEditGet");
      }
      // verify the required parameter 'price' is set
      if (price === undefined || price === null) {
        throw new Error("Missing the required parameter 'price' when calling privateEditGet");
      }

      let pathParams = {
      };
      let queryParams = {
        'order_id': orderId,
        'amount': amount,
        'price': price,
        'post_only': opts['postOnly'],
        'advanced': opts['advanced'],
        'stop_price': opts['stopPrice']
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
        '/private/edit', 'GET',
        pathParams, queryParams, headerParams, formParams, postBody,
        authNames, contentTypes, accepts, returnType, null, callback
      );
    }

    /**
     * Callback function to receive the result of the privateGetAccountSummaryGet operation.
     * @callback module:api/PrivateApi~privateGetAccountSummaryGetCallback
     * @param {String} error Error message, if any.
     * @param {Object} data The data returned by the service call.
     * @param {String} response The complete HTTP response.
     */

    /**
     * Retrieves user account summary.
     * @param {module:model/String} currency The currency symbol
     * @param {Object} opts Optional parameters
     * @param {Boolean} opts.extended Include additional fields
     * @param {module:api/PrivateApi~privateGetAccountSummaryGetCallback} callback The callback function, accepting three arguments: error, data, response
     * data is of type: {@link Object}
     */
    privateGetAccountSummaryGet(currency, opts, callback) {
      opts = opts || {};
      let postBody = null;
      // verify the required parameter 'currency' is set
      if (currency === undefined || currency === null) {
        throw new Error("Missing the required parameter 'currency' when calling privateGetAccountSummaryGet");
      }

      let pathParams = {
      };
      let queryParams = {
        'currency': currency,
        'extended': opts['extended']
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
        '/private/get_account_summary', 'GET',
        pathParams, queryParams, headerParams, formParams, postBody,
        authNames, contentTypes, accepts, returnType, null, callback
      );
    }

    /**
     * Callback function to receive the result of the privateGetAddressBookGet operation.
     * @callback module:api/PrivateApi~privateGetAddressBookGetCallback
     * @param {String} error Error message, if any.
     * @param {Object} data The data returned by the service call.
     * @param {String} response The complete HTTP response.
     */

    /**
     * Retrieves address book of given type
     * @param {module:model/String} currency The currency symbol
     * @param {module:model/String} type Address book type
     * @param {module:api/PrivateApi~privateGetAddressBookGetCallback} callback The callback function, accepting three arguments: error, data, response
     * data is of type: {@link Object}
     */
    privateGetAddressBookGet(currency, type, callback) {
      let postBody = null;
      // verify the required parameter 'currency' is set
      if (currency === undefined || currency === null) {
        throw new Error("Missing the required parameter 'currency' when calling privateGetAddressBookGet");
      }
      // verify the required parameter 'type' is set
      if (type === undefined || type === null) {
        throw new Error("Missing the required parameter 'type' when calling privateGetAddressBookGet");
      }

      let pathParams = {
      };
      let queryParams = {
        'currency': currency,
        'type': type
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
        '/private/get_address_book', 'GET',
        pathParams, queryParams, headerParams, formParams, postBody,
        authNames, contentTypes, accepts, returnType, null, callback
      );
    }

    /**
     * Callback function to receive the result of the privateGetCurrentDepositAddressGet operation.
     * @callback module:api/PrivateApi~privateGetCurrentDepositAddressGetCallback
     * @param {String} error Error message, if any.
     * @param {Object} data The data returned by the service call.
     * @param {String} response The complete HTTP response.
     */

    /**
     * Retrieve deposit address for currency
     * @param {module:model/String} currency The currency symbol
     * @param {module:api/PrivateApi~privateGetCurrentDepositAddressGetCallback} callback The callback function, accepting three arguments: error, data, response
     * data is of type: {@link Object}
     */
    privateGetCurrentDepositAddressGet(currency, callback) {
      let postBody = null;
      // verify the required parameter 'currency' is set
      if (currency === undefined || currency === null) {
        throw new Error("Missing the required parameter 'currency' when calling privateGetCurrentDepositAddressGet");
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
        '/private/get_current_deposit_address', 'GET',
        pathParams, queryParams, headerParams, formParams, postBody,
        authNames, contentTypes, accepts, returnType, null, callback
      );
    }

    /**
     * Callback function to receive the result of the privateGetDepositsGet operation.
     * @callback module:api/PrivateApi~privateGetDepositsGetCallback
     * @param {String} error Error message, if any.
     * @param {Object} data The data returned by the service call.
     * @param {String} response The complete HTTP response.
     */

    /**
     * Retrieve the latest users deposits
     * @param {module:model/String} currency The currency symbol
     * @param {Object} opts Optional parameters
     * @param {Number} opts.count Number of requested items, default - `10`
     * @param {Number} opts.offset The offset for pagination, default - `0`
     * @param {module:api/PrivateApi~privateGetDepositsGetCallback} callback The callback function, accepting three arguments: error, data, response
     * data is of type: {@link Object}
     */
    privateGetDepositsGet(currency, opts, callback) {
      opts = opts || {};
      let postBody = null;
      // verify the required parameter 'currency' is set
      if (currency === undefined || currency === null) {
        throw new Error("Missing the required parameter 'currency' when calling privateGetDepositsGet");
      }

      let pathParams = {
      };
      let queryParams = {
        'currency': currency,
        'count': opts['count'],
        'offset': opts['offset']
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
        '/private/get_deposits', 'GET',
        pathParams, queryParams, headerParams, formParams, postBody,
        authNames, contentTypes, accepts, returnType, null, callback
      );
    }

    /**
     * Callback function to receive the result of the privateGetEmailLanguageGet operation.
     * @callback module:api/PrivateApi~privateGetEmailLanguageGetCallback
     * @param {String} error Error message, if any.
     * @param {Object} data The data returned by the service call.
     * @param {String} response The complete HTTP response.
     */

    /**
     * Retrieves the language to be used for emails.
     * @param {module:api/PrivateApi~privateGetEmailLanguageGetCallback} callback The callback function, accepting three arguments: error, data, response
     * data is of type: {@link Object}
     */
    privateGetEmailLanguageGet(callback) {
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
        '/private/get_email_language', 'GET',
        pathParams, queryParams, headerParams, formParams, postBody,
        authNames, contentTypes, accepts, returnType, null, callback
      );
    }

    /**
     * Callback function to receive the result of the privateGetMarginsGet operation.
     * @callback module:api/PrivateApi~privateGetMarginsGetCallback
     * @param {String} error Error message, if any.
     * @param {Object} data The data returned by the service call.
     * @param {String} response The complete HTTP response.
     */

    /**
     * Get margins for given instrument, amount and price.
     * @param {String} instrumentName Instrument name
     * @param {Number} amount Amount, integer for future, float for option. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH.
     * @param {Number} price Price
     * @param {module:api/PrivateApi~privateGetMarginsGetCallback} callback The callback function, accepting three arguments: error, data, response
     * data is of type: {@link Object}
     */
    privateGetMarginsGet(instrumentName, amount, price, callback) {
      let postBody = null;
      // verify the required parameter 'instrumentName' is set
      if (instrumentName === undefined || instrumentName === null) {
        throw new Error("Missing the required parameter 'instrumentName' when calling privateGetMarginsGet");
      }
      // verify the required parameter 'amount' is set
      if (amount === undefined || amount === null) {
        throw new Error("Missing the required parameter 'amount' when calling privateGetMarginsGet");
      }
      // verify the required parameter 'price' is set
      if (price === undefined || price === null) {
        throw new Error("Missing the required parameter 'price' when calling privateGetMarginsGet");
      }

      let pathParams = {
      };
      let queryParams = {
        'instrument_name': instrumentName,
        'amount': amount,
        'price': price
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
        '/private/get_margins', 'GET',
        pathParams, queryParams, headerParams, formParams, postBody,
        authNames, contentTypes, accepts, returnType, null, callback
      );
    }

    /**
     * Callback function to receive the result of the privateGetNewAnnouncementsGet operation.
     * @callback module:api/PrivateApi~privateGetNewAnnouncementsGetCallback
     * @param {String} error Error message, if any.
     * @param {Object} data The data returned by the service call.
     * @param {String} response The complete HTTP response.
     */

    /**
     * Retrieves announcements that have not been marked read by the user.
     * @param {module:api/PrivateApi~privateGetNewAnnouncementsGetCallback} callback The callback function, accepting three arguments: error, data, response
     * data is of type: {@link Object}
     */
    privateGetNewAnnouncementsGet(callback) {
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
        '/private/get_new_announcements', 'GET',
        pathParams, queryParams, headerParams, formParams, postBody,
        authNames, contentTypes, accepts, returnType, null, callback
      );
    }

    /**
     * Callback function to receive the result of the privateGetOpenOrdersByCurrencyGet operation.
     * @callback module:api/PrivateApi~privateGetOpenOrdersByCurrencyGetCallback
     * @param {String} error Error message, if any.
     * @param {Object} data The data returned by the service call.
     * @param {String} response The complete HTTP response.
     */

    /**
     * Retrieves list of user's open orders.
     * @param {module:model/String} currency The currency symbol
     * @param {Object} opts Optional parameters
     * @param {module:model/String} opts.kind Instrument kind, if not provided instruments of all kinds are considered
     * @param {module:model/String} opts.type Order type, default - `all`
     * @param {module:api/PrivateApi~privateGetOpenOrdersByCurrencyGetCallback} callback The callback function, accepting three arguments: error, data, response
     * data is of type: {@link Object}
     */
    privateGetOpenOrdersByCurrencyGet(currency, opts, callback) {
      opts = opts || {};
      let postBody = null;
      // verify the required parameter 'currency' is set
      if (currency === undefined || currency === null) {
        throw new Error("Missing the required parameter 'currency' when calling privateGetOpenOrdersByCurrencyGet");
      }

      let pathParams = {
      };
      let queryParams = {
        'currency': currency,
        'kind': opts['kind'],
        'type': opts['type']
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
        '/private/get_open_orders_by_currency', 'GET',
        pathParams, queryParams, headerParams, formParams, postBody,
        authNames, contentTypes, accepts, returnType, null, callback
      );
    }

    /**
     * Callback function to receive the result of the privateGetOpenOrdersByInstrumentGet operation.
     * @callback module:api/PrivateApi~privateGetOpenOrdersByInstrumentGetCallback
     * @param {String} error Error message, if any.
     * @param {Object} data The data returned by the service call.
     * @param {String} response The complete HTTP response.
     */

    /**
     * Retrieves list of user's open orders within given Instrument.
     * @param {String} instrumentName Instrument name
     * @param {Object} opts Optional parameters
     * @param {module:model/String} opts.type Order type, default - `all`
     * @param {module:api/PrivateApi~privateGetOpenOrdersByInstrumentGetCallback} callback The callback function, accepting three arguments: error, data, response
     * data is of type: {@link Object}
     */
    privateGetOpenOrdersByInstrumentGet(instrumentName, opts, callback) {
      opts = opts || {};
      let postBody = null;
      // verify the required parameter 'instrumentName' is set
      if (instrumentName === undefined || instrumentName === null) {
        throw new Error("Missing the required parameter 'instrumentName' when calling privateGetOpenOrdersByInstrumentGet");
      }

      let pathParams = {
      };
      let queryParams = {
        'instrument_name': instrumentName,
        'type': opts['type']
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
        '/private/get_open_orders_by_instrument', 'GET',
        pathParams, queryParams, headerParams, formParams, postBody,
        authNames, contentTypes, accepts, returnType, null, callback
      );
    }

    /**
     * Callback function to receive the result of the privateGetOrderHistoryByCurrencyGet operation.
     * @callback module:api/PrivateApi~privateGetOrderHistoryByCurrencyGetCallback
     * @param {String} error Error message, if any.
     * @param {Object} data The data returned by the service call.
     * @param {String} response The complete HTTP response.
     */

    /**
     * Retrieves history of orders that have been partially or fully filled.
     * @param {module:model/String} currency The currency symbol
     * @param {Object} opts Optional parameters
     * @param {module:model/String} opts.kind Instrument kind, if not provided instruments of all kinds are considered
     * @param {Number} opts.count Number of requested items, default - `20`
     * @param {Number} opts.offset The offset for pagination, default - `0`
     * @param {Boolean} opts.includeOld Include in result orders older than 2 days, default - `false`
     * @param {Boolean} opts.includeUnfilled Include in result fully unfilled closed orders, default - `false`
     * @param {module:api/PrivateApi~privateGetOrderHistoryByCurrencyGetCallback} callback The callback function, accepting three arguments: error, data, response
     * data is of type: {@link Object}
     */
    privateGetOrderHistoryByCurrencyGet(currency, opts, callback) {
      opts = opts || {};
      let postBody = null;
      // verify the required parameter 'currency' is set
      if (currency === undefined || currency === null) {
        throw new Error("Missing the required parameter 'currency' when calling privateGetOrderHistoryByCurrencyGet");
      }

      let pathParams = {
      };
      let queryParams = {
        'currency': currency,
        'kind': opts['kind'],
        'count': opts['count'],
        'offset': opts['offset'],
        'include_old': opts['includeOld'],
        'include_unfilled': opts['includeUnfilled']
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
        '/private/get_order_history_by_currency', 'GET',
        pathParams, queryParams, headerParams, formParams, postBody,
        authNames, contentTypes, accepts, returnType, null, callback
      );
    }

    /**
     * Callback function to receive the result of the privateGetOrderHistoryByInstrumentGet operation.
     * @callback module:api/PrivateApi~privateGetOrderHistoryByInstrumentGetCallback
     * @param {String} error Error message, if any.
     * @param {Object} data The data returned by the service call.
     * @param {String} response The complete HTTP response.
     */

    /**
     * Retrieves history of orders that have been partially or fully filled.
     * @param {String} instrumentName Instrument name
     * @param {Object} opts Optional parameters
     * @param {Number} opts.count Number of requested items, default - `20`
     * @param {Number} opts.offset The offset for pagination, default - `0`
     * @param {Boolean} opts.includeOld Include in result orders older than 2 days, default - `false`
     * @param {Boolean} opts.includeUnfilled Include in result fully unfilled closed orders, default - `false`
     * @param {module:api/PrivateApi~privateGetOrderHistoryByInstrumentGetCallback} callback The callback function, accepting three arguments: error, data, response
     * data is of type: {@link Object}
     */
    privateGetOrderHistoryByInstrumentGet(instrumentName, opts, callback) {
      opts = opts || {};
      let postBody = null;
      // verify the required parameter 'instrumentName' is set
      if (instrumentName === undefined || instrumentName === null) {
        throw new Error("Missing the required parameter 'instrumentName' when calling privateGetOrderHistoryByInstrumentGet");
      }

      let pathParams = {
      };
      let queryParams = {
        'instrument_name': instrumentName,
        'count': opts['count'],
        'offset': opts['offset'],
        'include_old': opts['includeOld'],
        'include_unfilled': opts['includeUnfilled']
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
        '/private/get_order_history_by_instrument', 'GET',
        pathParams, queryParams, headerParams, formParams, postBody,
        authNames, contentTypes, accepts, returnType, null, callback
      );
    }

    /**
     * Callback function to receive the result of the privateGetOrderMarginByIdsGet operation.
     * @callback module:api/PrivateApi~privateGetOrderMarginByIdsGetCallback
     * @param {String} error Error message, if any.
     * @param {Object} data The data returned by the service call.
     * @param {String} response The complete HTTP response.
     */

    /**
     * Retrieves initial margins of given orders
     * @param {Array.<String>} ids Ids of orders
     * @param {module:api/PrivateApi~privateGetOrderMarginByIdsGetCallback} callback The callback function, accepting three arguments: error, data, response
     * data is of type: {@link Object}
     */
    privateGetOrderMarginByIdsGet(ids, callback) {
      let postBody = null;
      // verify the required parameter 'ids' is set
      if (ids === undefined || ids === null) {
        throw new Error("Missing the required parameter 'ids' when calling privateGetOrderMarginByIdsGet");
      }

      let pathParams = {
      };
      let queryParams = {
        'ids': this.apiClient.buildCollectionParam(ids, 'multi')
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
        '/private/get_order_margin_by_ids', 'GET',
        pathParams, queryParams, headerParams, formParams, postBody,
        authNames, contentTypes, accepts, returnType, null, callback
      );
    }

    /**
     * Callback function to receive the result of the privateGetOrderStateGet operation.
     * @callback module:api/PrivateApi~privateGetOrderStateGetCallback
     * @param {String} error Error message, if any.
     * @param {Object} data The data returned by the service call.
     * @param {String} response The complete HTTP response.
     */

    /**
     * Retrieve the current state of an order.
     * @param {String} orderId The order id
     * @param {module:api/PrivateApi~privateGetOrderStateGetCallback} callback The callback function, accepting three arguments: error, data, response
     * data is of type: {@link Object}
     */
    privateGetOrderStateGet(orderId, callback) {
      let postBody = null;
      // verify the required parameter 'orderId' is set
      if (orderId === undefined || orderId === null) {
        throw new Error("Missing the required parameter 'orderId' when calling privateGetOrderStateGet");
      }

      let pathParams = {
      };
      let queryParams = {
        'order_id': orderId
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
        '/private/get_order_state', 'GET',
        pathParams, queryParams, headerParams, formParams, postBody,
        authNames, contentTypes, accepts, returnType, null, callback
      );
    }

    /**
     * Callback function to receive the result of the privateGetPositionGet operation.
     * @callback module:api/PrivateApi~privateGetPositionGetCallback
     * @param {String} error Error message, if any.
     * @param {Object} data The data returned by the service call.
     * @param {String} response The complete HTTP response.
     */

    /**
     * Retrieve user position.
     * @param {String} instrumentName Instrument name
     * @param {module:api/PrivateApi~privateGetPositionGetCallback} callback The callback function, accepting three arguments: error, data, response
     * data is of type: {@link Object}
     */
    privateGetPositionGet(instrumentName, callback) {
      let postBody = null;
      // verify the required parameter 'instrumentName' is set
      if (instrumentName === undefined || instrumentName === null) {
        throw new Error("Missing the required parameter 'instrumentName' when calling privateGetPositionGet");
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
        '/private/get_position', 'GET',
        pathParams, queryParams, headerParams, formParams, postBody,
        authNames, contentTypes, accepts, returnType, null, callback
      );
    }

    /**
     * Callback function to receive the result of the privateGetPositionsGet operation.
     * @callback module:api/PrivateApi~privateGetPositionsGetCallback
     * @param {String} error Error message, if any.
     * @param {Object} data The data returned by the service call.
     * @param {String} response The complete HTTP response.
     */

    /**
     * Retrieve user positions.
     * @param {module:model/String} currency 
     * @param {Object} opts Optional parameters
     * @param {module:model/String} opts.kind Kind filter on positions
     * @param {module:api/PrivateApi~privateGetPositionsGetCallback} callback The callback function, accepting three arguments: error, data, response
     * data is of type: {@link Object}
     */
    privateGetPositionsGet(currency, opts, callback) {
      opts = opts || {};
      let postBody = null;
      // verify the required parameter 'currency' is set
      if (currency === undefined || currency === null) {
        throw new Error("Missing the required parameter 'currency' when calling privateGetPositionsGet");
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
        '/private/get_positions', 'GET',
        pathParams, queryParams, headerParams, formParams, postBody,
        authNames, contentTypes, accepts, returnType, null, callback
      );
    }

    /**
     * Callback function to receive the result of the privateGetSettlementHistoryByCurrencyGet operation.
     * @callback module:api/PrivateApi~privateGetSettlementHistoryByCurrencyGetCallback
     * @param {String} error Error message, if any.
     * @param {Object} data The data returned by the service call.
     * @param {String} response The complete HTTP response.
     */

    /**
     * Retrieves settlement, delivery and bankruptcy events that have affected your account.
     * @param {module:model/String} currency The currency symbol
     * @param {Object} opts Optional parameters
     * @param {module:model/String} opts.type Settlement type
     * @param {Number} opts.count Number of requested items, default - `20`
     * @param {module:api/PrivateApi~privateGetSettlementHistoryByCurrencyGetCallback} callback The callback function, accepting three arguments: error, data, response
     * data is of type: {@link Object}
     */
    privateGetSettlementHistoryByCurrencyGet(currency, opts, callback) {
      opts = opts || {};
      let postBody = null;
      // verify the required parameter 'currency' is set
      if (currency === undefined || currency === null) {
        throw new Error("Missing the required parameter 'currency' when calling privateGetSettlementHistoryByCurrencyGet");
      }

      let pathParams = {
      };
      let queryParams = {
        'currency': currency,
        'type': opts['type'],
        'count': opts['count']
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
        '/private/get_settlement_history_by_currency', 'GET',
        pathParams, queryParams, headerParams, formParams, postBody,
        authNames, contentTypes, accepts, returnType, null, callback
      );
    }

    /**
     * Callback function to receive the result of the privateGetSettlementHistoryByInstrumentGet operation.
     * @callback module:api/PrivateApi~privateGetSettlementHistoryByInstrumentGetCallback
     * @param {String} error Error message, if any.
     * @param {Object} data The data returned by the service call.
     * @param {String} response The complete HTTP response.
     */

    /**
     * Retrieves public settlement, delivery and bankruptcy events filtered by instrument name
     * @param {String} instrumentName Instrument name
     * @param {Object} opts Optional parameters
     * @param {module:model/String} opts.type Settlement type
     * @param {Number} opts.count Number of requested items, default - `20`
     * @param {module:api/PrivateApi~privateGetSettlementHistoryByInstrumentGetCallback} callback The callback function, accepting three arguments: error, data, response
     * data is of type: {@link Object}
     */
    privateGetSettlementHistoryByInstrumentGet(instrumentName, opts, callback) {
      opts = opts || {};
      let postBody = null;
      // verify the required parameter 'instrumentName' is set
      if (instrumentName === undefined || instrumentName === null) {
        throw new Error("Missing the required parameter 'instrumentName' when calling privateGetSettlementHistoryByInstrumentGet");
      }

      let pathParams = {
      };
      let queryParams = {
        'instrument_name': instrumentName,
        'type': opts['type'],
        'count': opts['count']
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
        '/private/get_settlement_history_by_instrument', 'GET',
        pathParams, queryParams, headerParams, formParams, postBody,
        authNames, contentTypes, accepts, returnType, null, callback
      );
    }

    /**
     * Callback function to receive the result of the privateGetSubaccountsGet operation.
     * @callback module:api/PrivateApi~privateGetSubaccountsGetCallback
     * @param {String} error Error message, if any.
     * @param {Object} data The data returned by the service call.
     * @param {String} response The complete HTTP response.
     */

    /**
     * Get information about subaccounts
     * @param {Object} opts Optional parameters
     * @param {Boolean} opts.withPortfolio 
     * @param {module:api/PrivateApi~privateGetSubaccountsGetCallback} callback The callback function, accepting three arguments: error, data, response
     * data is of type: {@link Object}
     */
    privateGetSubaccountsGet(opts, callback) {
      opts = opts || {};
      let postBody = null;

      let pathParams = {
      };
      let queryParams = {
        'with_portfolio': opts['withPortfolio']
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
        '/private/get_subaccounts', 'GET',
        pathParams, queryParams, headerParams, formParams, postBody,
        authNames, contentTypes, accepts, returnType, null, callback
      );
    }

    /**
     * Callback function to receive the result of the privateGetTransfersGet operation.
     * @callback module:api/PrivateApi~privateGetTransfersGetCallback
     * @param {String} error Error message, if any.
     * @param {Object} data The data returned by the service call.
     * @param {String} response The complete HTTP response.
     */

    /**
     * Adds new entry to address book of given type
     * @param {module:model/String} currency The currency symbol
     * @param {Object} opts Optional parameters
     * @param {Number} opts.count Number of requested items, default - `10`
     * @param {Number} opts.offset The offset for pagination, default - `0`
     * @param {module:api/PrivateApi~privateGetTransfersGetCallback} callback The callback function, accepting three arguments: error, data, response
     * data is of type: {@link Object}
     */
    privateGetTransfersGet(currency, opts, callback) {
      opts = opts || {};
      let postBody = null;
      // verify the required parameter 'currency' is set
      if (currency === undefined || currency === null) {
        throw new Error("Missing the required parameter 'currency' when calling privateGetTransfersGet");
      }

      let pathParams = {
      };
      let queryParams = {
        'currency': currency,
        'count': opts['count'],
        'offset': opts['offset']
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
        '/private/get_transfers', 'GET',
        pathParams, queryParams, headerParams, formParams, postBody,
        authNames, contentTypes, accepts, returnType, null, callback
      );
    }

    /**
     * Callback function to receive the result of the privateGetUserTradesByCurrencyAndTimeGet operation.
     * @callback module:api/PrivateApi~privateGetUserTradesByCurrencyAndTimeGetCallback
     * @param {String} error Error message, if any.
     * @param {Object} data The data returned by the service call.
     * @param {String} response The complete HTTP response.
     */

    /**
     * Retrieve the latest user trades that have occurred for instruments in a specific currency symbol and within given time range.
     * @param {module:model/String} currency The currency symbol
     * @param {Number} startTimestamp The earliest timestamp to return result for
     * @param {Number} endTimestamp The most recent timestamp to return result for
     * @param {Object} opts Optional parameters
     * @param {module:model/String} opts.kind Instrument kind, if not provided instruments of all kinds are considered
     * @param {Number} opts.count Number of requested items, default - `10`
     * @param {Boolean} opts.includeOld Include trades older than 7 days, default - `false`
     * @param {module:model/String} opts.sorting Direction of results sorting (`default` value means no sorting, results will be returned in order in which they left the database)
     * @param {module:api/PrivateApi~privateGetUserTradesByCurrencyAndTimeGetCallback} callback The callback function, accepting three arguments: error, data, response
     * data is of type: {@link Object}
     */
    privateGetUserTradesByCurrencyAndTimeGet(currency, startTimestamp, endTimestamp, opts, callback) {
      opts = opts || {};
      let postBody = null;
      // verify the required parameter 'currency' is set
      if (currency === undefined || currency === null) {
        throw new Error("Missing the required parameter 'currency' when calling privateGetUserTradesByCurrencyAndTimeGet");
      }
      // verify the required parameter 'startTimestamp' is set
      if (startTimestamp === undefined || startTimestamp === null) {
        throw new Error("Missing the required parameter 'startTimestamp' when calling privateGetUserTradesByCurrencyAndTimeGet");
      }
      // verify the required parameter 'endTimestamp' is set
      if (endTimestamp === undefined || endTimestamp === null) {
        throw new Error("Missing the required parameter 'endTimestamp' when calling privateGetUserTradesByCurrencyAndTimeGet");
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
        '/private/get_user_trades_by_currency_and_time', 'GET',
        pathParams, queryParams, headerParams, formParams, postBody,
        authNames, contentTypes, accepts, returnType, null, callback
      );
    }

    /**
     * Callback function to receive the result of the privateGetUserTradesByCurrencyGet operation.
     * @callback module:api/PrivateApi~privateGetUserTradesByCurrencyGetCallback
     * @param {String} error Error message, if any.
     * @param {Object} data The data returned by the service call.
     * @param {String} response The complete HTTP response.
     */

    /**
     * Retrieve the latest user trades that have occurred for instruments in a specific currency symbol.
     * @param {module:model/String} currency The currency symbol
     * @param {Object} opts Optional parameters
     * @param {module:model/String} opts.kind Instrument kind, if not provided instruments of all kinds are considered
     * @param {String} opts.startId The ID number of the first trade to be returned
     * @param {String} opts.endId The ID number of the last trade to be returned
     * @param {Number} opts.count Number of requested items, default - `10`
     * @param {Boolean} opts.includeOld Include trades older than 7 days, default - `false`
     * @param {module:model/String} opts.sorting Direction of results sorting (`default` value means no sorting, results will be returned in order in which they left the database)
     * @param {module:api/PrivateApi~privateGetUserTradesByCurrencyGetCallback} callback The callback function, accepting three arguments: error, data, response
     * data is of type: {@link Object}
     */
    privateGetUserTradesByCurrencyGet(currency, opts, callback) {
      opts = opts || {};
      let postBody = null;
      // verify the required parameter 'currency' is set
      if (currency === undefined || currency === null) {
        throw new Error("Missing the required parameter 'currency' when calling privateGetUserTradesByCurrencyGet");
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
        '/private/get_user_trades_by_currency', 'GET',
        pathParams, queryParams, headerParams, formParams, postBody,
        authNames, contentTypes, accepts, returnType, null, callback
      );
    }

    /**
     * Callback function to receive the result of the privateGetUserTradesByInstrumentAndTimeGet operation.
     * @callback module:api/PrivateApi~privateGetUserTradesByInstrumentAndTimeGetCallback
     * @param {String} error Error message, if any.
     * @param {Object} data The data returned by the service call.
     * @param {String} response The complete HTTP response.
     */

    /**
     * Retrieve the latest user trades that have occurred for a specific instrument and within given time range.
     * @param {String} instrumentName Instrument name
     * @param {Number} startTimestamp The earliest timestamp to return result for
     * @param {Number} endTimestamp The most recent timestamp to return result for
     * @param {Object} opts Optional parameters
     * @param {Number} opts.count Number of requested items, default - `10`
     * @param {Boolean} opts.includeOld Include trades older than 7 days, default - `false`
     * @param {module:model/String} opts.sorting Direction of results sorting (`default` value means no sorting, results will be returned in order in which they left the database)
     * @param {module:api/PrivateApi~privateGetUserTradesByInstrumentAndTimeGetCallback} callback The callback function, accepting three arguments: error, data, response
     * data is of type: {@link Object}
     */
    privateGetUserTradesByInstrumentAndTimeGet(instrumentName, startTimestamp, endTimestamp, opts, callback) {
      opts = opts || {};
      let postBody = null;
      // verify the required parameter 'instrumentName' is set
      if (instrumentName === undefined || instrumentName === null) {
        throw new Error("Missing the required parameter 'instrumentName' when calling privateGetUserTradesByInstrumentAndTimeGet");
      }
      // verify the required parameter 'startTimestamp' is set
      if (startTimestamp === undefined || startTimestamp === null) {
        throw new Error("Missing the required parameter 'startTimestamp' when calling privateGetUserTradesByInstrumentAndTimeGet");
      }
      // verify the required parameter 'endTimestamp' is set
      if (endTimestamp === undefined || endTimestamp === null) {
        throw new Error("Missing the required parameter 'endTimestamp' when calling privateGetUserTradesByInstrumentAndTimeGet");
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
        '/private/get_user_trades_by_instrument_and_time', 'GET',
        pathParams, queryParams, headerParams, formParams, postBody,
        authNames, contentTypes, accepts, returnType, null, callback
      );
    }

    /**
     * Callback function to receive the result of the privateGetUserTradesByInstrumentGet operation.
     * @callback module:api/PrivateApi~privateGetUserTradesByInstrumentGetCallback
     * @param {String} error Error message, if any.
     * @param {Object} data The data returned by the service call.
     * @param {String} response The complete HTTP response.
     */

    /**
     * Retrieve the latest user trades that have occurred for a specific instrument.
     * @param {String} instrumentName Instrument name
     * @param {Object} opts Optional parameters
     * @param {Number} opts.startSeq The sequence number of the first trade to be returned
     * @param {Number} opts.endSeq The sequence number of the last trade to be returned
     * @param {Number} opts.count Number of requested items, default - `10`
     * @param {Boolean} opts.includeOld Include trades older than 7 days, default - `false`
     * @param {module:model/String} opts.sorting Direction of results sorting (`default` value means no sorting, results will be returned in order in which they left the database)
     * @param {module:api/PrivateApi~privateGetUserTradesByInstrumentGetCallback} callback The callback function, accepting three arguments: error, data, response
     * data is of type: {@link Object}
     */
    privateGetUserTradesByInstrumentGet(instrumentName, opts, callback) {
      opts = opts || {};
      let postBody = null;
      // verify the required parameter 'instrumentName' is set
      if (instrumentName === undefined || instrumentName === null) {
        throw new Error("Missing the required parameter 'instrumentName' when calling privateGetUserTradesByInstrumentGet");
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
        '/private/get_user_trades_by_instrument', 'GET',
        pathParams, queryParams, headerParams, formParams, postBody,
        authNames, contentTypes, accepts, returnType, null, callback
      );
    }

    /**
     * Callback function to receive the result of the privateGetUserTradesByOrderGet operation.
     * @callback module:api/PrivateApi~privateGetUserTradesByOrderGetCallback
     * @param {String} error Error message, if any.
     * @param {Object} data The data returned by the service call.
     * @param {String} response The complete HTTP response.
     */

    /**
     * Retrieve the list of user trades that was created for given order
     * @param {String} orderId The order id
     * @param {Object} opts Optional parameters
     * @param {module:model/String} opts.sorting Direction of results sorting (`default` value means no sorting, results will be returned in order in which they left the database)
     * @param {module:api/PrivateApi~privateGetUserTradesByOrderGetCallback} callback The callback function, accepting three arguments: error, data, response
     * data is of type: {@link Object}
     */
    privateGetUserTradesByOrderGet(orderId, opts, callback) {
      opts = opts || {};
      let postBody = null;
      // verify the required parameter 'orderId' is set
      if (orderId === undefined || orderId === null) {
        throw new Error("Missing the required parameter 'orderId' when calling privateGetUserTradesByOrderGet");
      }

      let pathParams = {
      };
      let queryParams = {
        'order_id': orderId,
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
        '/private/get_user_trades_by_order', 'GET',
        pathParams, queryParams, headerParams, formParams, postBody,
        authNames, contentTypes, accepts, returnType, null, callback
      );
    }

    /**
     * Callback function to receive the result of the privateGetWithdrawalsGet operation.
     * @callback module:api/PrivateApi~privateGetWithdrawalsGetCallback
     * @param {String} error Error message, if any.
     * @param {Object} data The data returned by the service call.
     * @param {String} response The complete HTTP response.
     */

    /**
     * Retrieve the latest users withdrawals
     * @param {module:model/String} currency The currency symbol
     * @param {Object} opts Optional parameters
     * @param {Number} opts.count Number of requested items, default - `10`
     * @param {Number} opts.offset The offset for pagination, default - `0`
     * @param {module:api/PrivateApi~privateGetWithdrawalsGetCallback} callback The callback function, accepting three arguments: error, data, response
     * data is of type: {@link Object}
     */
    privateGetWithdrawalsGet(currency, opts, callback) {
      opts = opts || {};
      let postBody = null;
      // verify the required parameter 'currency' is set
      if (currency === undefined || currency === null) {
        throw new Error("Missing the required parameter 'currency' when calling privateGetWithdrawalsGet");
      }

      let pathParams = {
      };
      let queryParams = {
        'currency': currency,
        'count': opts['count'],
        'offset': opts['offset']
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
        '/private/get_withdrawals', 'GET',
        pathParams, queryParams, headerParams, formParams, postBody,
        authNames, contentTypes, accepts, returnType, null, callback
      );
    }

    /**
     * Callback function to receive the result of the privateRemoveFromAddressBookGet operation.
     * @callback module:api/PrivateApi~privateRemoveFromAddressBookGetCallback
     * @param {String} error Error message, if any.
     * @param {Object} data The data returned by the service call.
     * @param {String} response The complete HTTP response.
     */

    /**
     * Adds new entry to address book of given type
     * @param {module:model/String} currency The currency symbol
     * @param {module:model/String} type Address book type
     * @param {String} address Address in currency format, it must be in address book
     * @param {Object} opts Optional parameters
     * @param {String} opts.tfa TFA code, required when TFA is enabled for current account
     * @param {module:api/PrivateApi~privateRemoveFromAddressBookGetCallback} callback The callback function, accepting three arguments: error, data, response
     * data is of type: {@link Object}
     */
    privateRemoveFromAddressBookGet(currency, type, address, opts, callback) {
      opts = opts || {};
      let postBody = null;
      // verify the required parameter 'currency' is set
      if (currency === undefined || currency === null) {
        throw new Error("Missing the required parameter 'currency' when calling privateRemoveFromAddressBookGet");
      }
      // verify the required parameter 'type' is set
      if (type === undefined || type === null) {
        throw new Error("Missing the required parameter 'type' when calling privateRemoveFromAddressBookGet");
      }
      // verify the required parameter 'address' is set
      if (address === undefined || address === null) {
        throw new Error("Missing the required parameter 'address' when calling privateRemoveFromAddressBookGet");
      }

      let pathParams = {
      };
      let queryParams = {
        'currency': currency,
        'type': type,
        'address': address,
        'tfa': opts['tfa']
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
        '/private/remove_from_address_book', 'GET',
        pathParams, queryParams, headerParams, formParams, postBody,
        authNames, contentTypes, accepts, returnType, null, callback
      );
    }

    /**
     * Callback function to receive the result of the privateSellGet operation.
     * @callback module:api/PrivateApi~privateSellGetCallback
     * @param {String} error Error message, if any.
     * @param {Object} data The data returned by the service call.
     * @param {String} response The complete HTTP response.
     */

    /**
     * Places a sell order for an instrument.
     * @param {String} instrumentName Instrument name
     * @param {Number} amount It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH
     * @param {Object} opts Optional parameters
     * @param {module:model/String} opts.type The order type, default: `\"limit\"`
     * @param {String} opts.label user defined label for the order (maximum 32 characters)
     * @param {Number} opts.price <p>The order price in base currency (Only for limit and stop_limit orders)</p> <p>When adding order with advanced=usd, the field price should be the option price value in USD.</p> <p>When adding order with advanced=implv, the field price should be a value of implied volatility in percentages. For example,  price=100, means implied volatility of 100%</p>
     * @param {module:model/String} opts.timeInForce <p>Specifies how long the order remains in effect. Default `\"good_til_cancelled\"`</p> <ul> <li>`\"good_til_cancelled\"` - unfilled order remains in order book until cancelled</li> <li>`\"fill_or_kill\"` - execute a transaction immediately and completely or not at all</li> <li>`\"immediate_or_cancel\"` - execute a transaction immediately, and any portion of the order that cannot be immediately filled is cancelled</li> </ul> (default to 'good_til_cancelled')
     * @param {Number} opts.maxShow Maximum amount within an order to be shown to other customers, `0` for invisible order (default to 1)
     * @param {Boolean} opts.postOnly <p>If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.</p> <p>Only valid in combination with time_in_force=`\"good_til_cancelled\"`</p> (default to true)
     * @param {Boolean} opts.reduceOnly If `true`, the order is considered reduce-only which is intended to only reduce a current position (default to false)
     * @param {Number} opts.stopPrice Stop price, required for stop limit orders (Only for stop orders)
     * @param {module:model/String} opts.trigger Defines trigger type, required for `\"stop_limit\"` order type
     * @param {module:model/String} opts.advanced Advanced option order type. (Only for options)
     * @param {module:api/PrivateApi~privateSellGetCallback} callback The callback function, accepting three arguments: error, data, response
     * data is of type: {@link Object}
     */
    privateSellGet(instrumentName, amount, opts, callback) {
      opts = opts || {};
      let postBody = null;
      // verify the required parameter 'instrumentName' is set
      if (instrumentName === undefined || instrumentName === null) {
        throw new Error("Missing the required parameter 'instrumentName' when calling privateSellGet");
      }
      // verify the required parameter 'amount' is set
      if (amount === undefined || amount === null) {
        throw new Error("Missing the required parameter 'amount' when calling privateSellGet");
      }

      let pathParams = {
      };
      let queryParams = {
        'instrument_name': instrumentName,
        'amount': amount,
        'type': opts['type'],
        'label': opts['label'],
        'price': opts['price'],
        'time_in_force': opts['timeInForce'],
        'max_show': opts['maxShow'],
        'post_only': opts['postOnly'],
        'reduce_only': opts['reduceOnly'],
        'stop_price': opts['stopPrice'],
        'trigger': opts['trigger'],
        'advanced': opts['advanced']
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
        '/private/sell', 'GET',
        pathParams, queryParams, headerParams, formParams, postBody,
        authNames, contentTypes, accepts, returnType, null, callback
      );
    }

    /**
     * Callback function to receive the result of the privateSetAnnouncementAsReadGet operation.
     * @callback module:api/PrivateApi~privateSetAnnouncementAsReadGetCallback
     * @param {String} error Error message, if any.
     * @param {Object} data The data returned by the service call.
     * @param {String} response The complete HTTP response.
     */

    /**
     * Marks an announcement as read, so it will not be shown in `get_new_announcements`.
     * @param {Number} announcementId the ID of the announcement
     * @param {module:api/PrivateApi~privateSetAnnouncementAsReadGetCallback} callback The callback function, accepting three arguments: error, data, response
     * data is of type: {@link Object}
     */
    privateSetAnnouncementAsReadGet(announcementId, callback) {
      let postBody = null;
      // verify the required parameter 'announcementId' is set
      if (announcementId === undefined || announcementId === null) {
        throw new Error("Missing the required parameter 'announcementId' when calling privateSetAnnouncementAsReadGet");
      }

      let pathParams = {
      };
      let queryParams = {
        'announcement_id': announcementId
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
        '/private/set_announcement_as_read', 'GET',
        pathParams, queryParams, headerParams, formParams, postBody,
        authNames, contentTypes, accepts, returnType, null, callback
      );
    }

    /**
     * Callback function to receive the result of the privateSetEmailForSubaccountGet operation.
     * @callback module:api/PrivateApi~privateSetEmailForSubaccountGetCallback
     * @param {String} error Error message, if any.
     * @param {Object} data The data returned by the service call.
     * @param {String} response The complete HTTP response.
     */

    /**
     * Assign an email address to a subaccount. User will receive an email with confirmation link.
     * @param {Number} sid The user id for the subaccount
     * @param {String} email The email address for the subaccount
     * @param {module:api/PrivateApi~privateSetEmailForSubaccountGetCallback} callback The callback function, accepting three arguments: error, data, response
     * data is of type: {@link Object}
     */
    privateSetEmailForSubaccountGet(sid, email, callback) {
      let postBody = null;
      // verify the required parameter 'sid' is set
      if (sid === undefined || sid === null) {
        throw new Error("Missing the required parameter 'sid' when calling privateSetEmailForSubaccountGet");
      }
      // verify the required parameter 'email' is set
      if (email === undefined || email === null) {
        throw new Error("Missing the required parameter 'email' when calling privateSetEmailForSubaccountGet");
      }

      let pathParams = {
      };
      let queryParams = {
        'sid': sid,
        'email': email
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
        '/private/set_email_for_subaccount', 'GET',
        pathParams, queryParams, headerParams, formParams, postBody,
        authNames, contentTypes, accepts, returnType, null, callback
      );
    }

    /**
     * Callback function to receive the result of the privateSetEmailLanguageGet operation.
     * @callback module:api/PrivateApi~privateSetEmailLanguageGetCallback
     * @param {String} error Error message, if any.
     * @param {Object} data The data returned by the service call.
     * @param {String} response The complete HTTP response.
     */

    /**
     * Changes the language to be used for emails.
     * @param {String} language The abbreviated language name. Valid values include `\"en\"`, `\"ko\"`, `\"zh\"`
     * @param {module:api/PrivateApi~privateSetEmailLanguageGetCallback} callback The callback function, accepting three arguments: error, data, response
     * data is of type: {@link Object}
     */
    privateSetEmailLanguageGet(language, callback) {
      let postBody = null;
      // verify the required parameter 'language' is set
      if (language === undefined || language === null) {
        throw new Error("Missing the required parameter 'language' when calling privateSetEmailLanguageGet");
      }

      let pathParams = {
      };
      let queryParams = {
        'language': language
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
        '/private/set_email_language', 'GET',
        pathParams, queryParams, headerParams, formParams, postBody,
        authNames, contentTypes, accepts, returnType, null, callback
      );
    }

    /**
     * Callback function to receive the result of the privateSetPasswordForSubaccountGet operation.
     * @callback module:api/PrivateApi~privateSetPasswordForSubaccountGetCallback
     * @param {String} error Error message, if any.
     * @param {Object} data The data returned by the service call.
     * @param {String} response The complete HTTP response.
     */

    /**
     * Set the password for the subaccount
     * @param {Number} sid The user id for the subaccount
     * @param {String} password The password for the subaccount
     * @param {module:api/PrivateApi~privateSetPasswordForSubaccountGetCallback} callback The callback function, accepting three arguments: error, data, response
     * data is of type: {@link Object}
     */
    privateSetPasswordForSubaccountGet(sid, password, callback) {
      let postBody = null;
      // verify the required parameter 'sid' is set
      if (sid === undefined || sid === null) {
        throw new Error("Missing the required parameter 'sid' when calling privateSetPasswordForSubaccountGet");
      }
      // verify the required parameter 'password' is set
      if (password === undefined || password === null) {
        throw new Error("Missing the required parameter 'password' when calling privateSetPasswordForSubaccountGet");
      }

      let pathParams = {
      };
      let queryParams = {
        'sid': sid,
        'password': password
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
        '/private/set_password_for_subaccount', 'GET',
        pathParams, queryParams, headerParams, formParams, postBody,
        authNames, contentTypes, accepts, returnType, null, callback
      );
    }

    /**
     * Callback function to receive the result of the privateSubmitTransferToSubaccountGet operation.
     * @callback module:api/PrivateApi~privateSubmitTransferToSubaccountGetCallback
     * @param {String} error Error message, if any.
     * @param {Object} data The data returned by the service call.
     * @param {String} response The complete HTTP response.
     */

    /**
     * Transfer funds to a subaccount.
     * @param {module:model/String} currency The currency symbol
     * @param {Number} amount Amount of funds to be transferred
     * @param {Number} destination Id of destination subaccount
     * @param {module:api/PrivateApi~privateSubmitTransferToSubaccountGetCallback} callback The callback function, accepting three arguments: error, data, response
     * data is of type: {@link Object}
     */
    privateSubmitTransferToSubaccountGet(currency, amount, destination, callback) {
      let postBody = null;
      // verify the required parameter 'currency' is set
      if (currency === undefined || currency === null) {
        throw new Error("Missing the required parameter 'currency' when calling privateSubmitTransferToSubaccountGet");
      }
      // verify the required parameter 'amount' is set
      if (amount === undefined || amount === null) {
        throw new Error("Missing the required parameter 'amount' when calling privateSubmitTransferToSubaccountGet");
      }
      // verify the required parameter 'destination' is set
      if (destination === undefined || destination === null) {
        throw new Error("Missing the required parameter 'destination' when calling privateSubmitTransferToSubaccountGet");
      }

      let pathParams = {
      };
      let queryParams = {
        'currency': currency,
        'amount': amount,
        'destination': destination
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
        '/private/submit_transfer_to_subaccount', 'GET',
        pathParams, queryParams, headerParams, formParams, postBody,
        authNames, contentTypes, accepts, returnType, null, callback
      );
    }

    /**
     * Callback function to receive the result of the privateSubmitTransferToUserGet operation.
     * @callback module:api/PrivateApi~privateSubmitTransferToUserGetCallback
     * @param {String} error Error message, if any.
     * @param {Object} data The data returned by the service call.
     * @param {String} response The complete HTTP response.
     */

    /**
     * Transfer funds to a another user.
     * @param {module:model/String} currency The currency symbol
     * @param {Number} amount Amount of funds to be transferred
     * @param {String} destination Destination address from address book
     * @param {Object} opts Optional parameters
     * @param {String} opts.tfa TFA code, required when TFA is enabled for current account
     * @param {module:api/PrivateApi~privateSubmitTransferToUserGetCallback} callback The callback function, accepting three arguments: error, data, response
     * data is of type: {@link Object}
     */
    privateSubmitTransferToUserGet(currency, amount, destination, opts, callback) {
      opts = opts || {};
      let postBody = null;
      // verify the required parameter 'currency' is set
      if (currency === undefined || currency === null) {
        throw new Error("Missing the required parameter 'currency' when calling privateSubmitTransferToUserGet");
      }
      // verify the required parameter 'amount' is set
      if (amount === undefined || amount === null) {
        throw new Error("Missing the required parameter 'amount' when calling privateSubmitTransferToUserGet");
      }
      // verify the required parameter 'destination' is set
      if (destination === undefined || destination === null) {
        throw new Error("Missing the required parameter 'destination' when calling privateSubmitTransferToUserGet");
      }

      let pathParams = {
      };
      let queryParams = {
        'currency': currency,
        'amount': amount,
        'destination': destination,
        'tfa': opts['tfa']
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
        '/private/submit_transfer_to_user', 'GET',
        pathParams, queryParams, headerParams, formParams, postBody,
        authNames, contentTypes, accepts, returnType, null, callback
      );
    }

    /**
     * Callback function to receive the result of the privateToggleDepositAddressCreationGet operation.
     * @callback module:api/PrivateApi~privateToggleDepositAddressCreationGetCallback
     * @param {String} error Error message, if any.
     * @param {Object} data The data returned by the service call.
     * @param {String} response The complete HTTP response.
     */

    /**
     * Enable or disable deposit address creation
     * @param {module:model/String} currency The currency symbol
     * @param {Boolean} state 
     * @param {module:api/PrivateApi~privateToggleDepositAddressCreationGetCallback} callback The callback function, accepting three arguments: error, data, response
     * data is of type: {@link Object}
     */
    privateToggleDepositAddressCreationGet(currency, state, callback) {
      let postBody = null;
      // verify the required parameter 'currency' is set
      if (currency === undefined || currency === null) {
        throw new Error("Missing the required parameter 'currency' when calling privateToggleDepositAddressCreationGet");
      }
      // verify the required parameter 'state' is set
      if (state === undefined || state === null) {
        throw new Error("Missing the required parameter 'state' when calling privateToggleDepositAddressCreationGet");
      }

      let pathParams = {
      };
      let queryParams = {
        'currency': currency,
        'state': state
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
        '/private/toggle_deposit_address_creation', 'GET',
        pathParams, queryParams, headerParams, formParams, postBody,
        authNames, contentTypes, accepts, returnType, null, callback
      );
    }

    /**
     * Callback function to receive the result of the privateToggleNotificationsFromSubaccountGet operation.
     * @callback module:api/PrivateApi~privateToggleNotificationsFromSubaccountGetCallback
     * @param {String} error Error message, if any.
     * @param {Object} data The data returned by the service call.
     * @param {String} response The complete HTTP response.
     */

    /**
     * Enable or disable sending of notifications for the subaccount.
     * @param {Number} sid The user id for the subaccount
     * @param {Boolean} state enable (`true`) or disable (`false`) notifications
     * @param {module:api/PrivateApi~privateToggleNotificationsFromSubaccountGetCallback} callback The callback function, accepting three arguments: error, data, response
     * data is of type: {@link Object}
     */
    privateToggleNotificationsFromSubaccountGet(sid, state, callback) {
      let postBody = null;
      // verify the required parameter 'sid' is set
      if (sid === undefined || sid === null) {
        throw new Error("Missing the required parameter 'sid' when calling privateToggleNotificationsFromSubaccountGet");
      }
      // verify the required parameter 'state' is set
      if (state === undefined || state === null) {
        throw new Error("Missing the required parameter 'state' when calling privateToggleNotificationsFromSubaccountGet");
      }

      let pathParams = {
      };
      let queryParams = {
        'sid': sid,
        'state': state
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
        '/private/toggle_notifications_from_subaccount', 'GET',
        pathParams, queryParams, headerParams, formParams, postBody,
        authNames, contentTypes, accepts, returnType, null, callback
      );
    }

    /**
     * Callback function to receive the result of the privateToggleSubaccountLoginGet operation.
     * @callback module:api/PrivateApi~privateToggleSubaccountLoginGetCallback
     * @param {String} error Error message, if any.
     * @param {Object} data The data returned by the service call.
     * @param {String} response The complete HTTP response.
     */

    /**
     * Enable or disable login for a subaccount. If login is disabled and a session for the subaccount exists, this session will be terminated.
     * @param {Number} sid The user id for the subaccount
     * @param {module:model/String} state enable or disable login.
     * @param {module:api/PrivateApi~privateToggleSubaccountLoginGetCallback} callback The callback function, accepting three arguments: error, data, response
     * data is of type: {@link Object}
     */
    privateToggleSubaccountLoginGet(sid, state, callback) {
      let postBody = null;
      // verify the required parameter 'sid' is set
      if (sid === undefined || sid === null) {
        throw new Error("Missing the required parameter 'sid' when calling privateToggleSubaccountLoginGet");
      }
      // verify the required parameter 'state' is set
      if (state === undefined || state === null) {
        throw new Error("Missing the required parameter 'state' when calling privateToggleSubaccountLoginGet");
      }

      let pathParams = {
      };
      let queryParams = {
        'sid': sid,
        'state': state
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
        '/private/toggle_subaccount_login', 'GET',
        pathParams, queryParams, headerParams, formParams, postBody,
        authNames, contentTypes, accepts, returnType, null, callback
      );
    }

    /**
     * Callback function to receive the result of the privateWithdrawGet operation.
     * @callback module:api/PrivateApi~privateWithdrawGetCallback
     * @param {String} error Error message, if any.
     * @param {Object} data The data returned by the service call.
     * @param {String} response The complete HTTP response.
     */

    /**
     * Creates a new withdrawal request
     * @param {module:model/String} currency The currency symbol
     * @param {String} address Address in currency format, it must be in address book
     * @param {Number} amount Amount of funds to be withdrawn
     * @param {Object} opts Optional parameters
     * @param {module:model/String} opts.priority Withdrawal priority, optional for BTC, default: `high`
     * @param {String} opts.tfa TFA code, required when TFA is enabled for current account
     * @param {module:api/PrivateApi~privateWithdrawGetCallback} callback The callback function, accepting three arguments: error, data, response
     * data is of type: {@link Object}
     */
    privateWithdrawGet(currency, address, amount, opts, callback) {
      opts = opts || {};
      let postBody = null;
      // verify the required parameter 'currency' is set
      if (currency === undefined || currency === null) {
        throw new Error("Missing the required parameter 'currency' when calling privateWithdrawGet");
      }
      // verify the required parameter 'address' is set
      if (address === undefined || address === null) {
        throw new Error("Missing the required parameter 'address' when calling privateWithdrawGet");
      }
      // verify the required parameter 'amount' is set
      if (amount === undefined || amount === null) {
        throw new Error("Missing the required parameter 'amount' when calling privateWithdrawGet");
      }

      let pathParams = {
      };
      let queryParams = {
        'currency': currency,
        'address': address,
        'amount': amount,
        'priority': opts['priority'],
        'tfa': opts['tfa']
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
        '/private/withdraw', 'GET',
        pathParams, queryParams, headerParams, formParams, postBody,
        authNames, contentTypes, accepts, returnType, null, callback
      );
    }


}
