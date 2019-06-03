/* 
 * Deribit API
 *
 * #Overview  Deribit provides three different interfaces to access the API:  * [JSON-RPC over Websocket](#json-rpc) * [JSON-RPC over HTTP](#json-rpc) * [FIX](#fix-api) (Financial Information eXchange)  With the API Console you can use and test the JSON-RPC API, both via HTTP and  via Websocket. To visit the API console, go to __Account > API tab >  API Console tab.__   ##Naming Deribit tradeable assets or instruments use the following system of naming:  |Kind|Examples|Template|Comments| |- -- -|- -- -- -- -|- -- -- -- -|- -- -- -- -| |Future|<code>BTC-25MAR16</code>, <code>BTC-5AUG16</code>|<code>BTC-DMMMYY</code>|<code>BTC</code> is currency, <code>DMMMYY</code> is expiration date, <code>D</code> stands for day of month (1 or 2 digits), <code>MMM</code> - month (3 first letters in English), <code>YY</code> stands for year.| |Perpetual|<code>BTC-PERPETUAL</code>                        ||Perpetual contract for currency <code>BTC</code>.| |Option|<code>BTC-25MAR16-420-C</code>, <code>BTC-5AUG16-580-P</code>|<code>BTC-DMMMYY-STRIKE-K</code>|<code>STRIKE</code> is option strike price in USD. Template <code>K</code> is option kind: <code>C</code> for call options or <code>P</code> for put options.|   # JSON-RPC JSON-RPC is a light-weight remote procedure call (RPC) protocol. The  [JSON-RPC specification](https://www.jsonrpc.org/specification) defines the data structures that are used for the messages that are exchanged between client and server, as well as the rules around their processing. JSON-RPC uses JSON (RFC 4627) as data format.  JSON-RPC is transport agnostic: it does not specify which transport mechanism must be used. The Deribit API supports both Websocket (preferred) and HTTP (with limitations: subscriptions are not supported over HTTP).  ## Request messages > An example of a request message:  ```json {     \"jsonrpc\": \"2.0\",     \"id\": 8066,     \"method\": \"public/ticker\",     \"params\": {         \"instrument\": \"BTC-24AUG18-6500-P\"     } } ```  According to the JSON-RPC sepcification the requests must be JSON objects with the following fields.  |Name|Type|Description| |- -- -|- -- -|- -- -- -- -- --| |jsonrpc|string|The version of the JSON-RPC spec: \"2.0\"| |id|integer or string|An identifier of the request. If it is included, then the response will contain the same identifier| |method|string|The method to be invoked| |params|object|The parameters values for the method. The field names must match with the expected parameter names. The parameters that are expected are described in the documentation for the methods, below.|  <aside class=\"warning\"> The JSON-RPC specification describes two features that are currently not supported by the API:  <ul> <li>Specification of parameter values by position</li> <li>Batch requests</li> </ul>  </aside>   ## Response messages > An example of a response message:  ```json {     \"jsonrpc\": \"2.0\",     \"id\": 5239,     \"testnet\": false,     \"result\": [         {             \"currency\": \"BTC\",             \"currencyLong\": \"Bitcoin\",             \"minConfirmation\": 2,             \"txFee\": 0.0006,             \"isActive\": true,             \"coinType\": \"BITCOIN\",             \"baseAddress\": null         }     ],     \"usIn\": 1535043730126248,     \"usOut\": 1535043730126250,     \"usDiff\": 2 } ```  The JSON-RPC API always responds with a JSON object with the following fields.   |Name|Type|Description| |- -- -|- -- -|- -- -- -- -- --| |id|integer|This is the same id that was sent in the request.| |result|any|If successful, the result of the API call. The format for the result is described with each method.| |error|error object|Only present if there was an error invoking the method. The error object is described below.| |testnet|boolean|Indicates whether the API in use is actually the test API.  <code>false</code> for production server, <code>true</code> for test server.| |usIn|integer|The timestamp when the requests was received (microseconds since the Unix epoch)| |usOut|integer|The timestamp when the response was sent (microseconds since the Unix epoch)| |usDiff|integer|The number of microseconds that was spent handling the request|  <aside class=\"notice\"> The fields <code>testnet</code>, <code>usIn</code>, <code>usOut</code> and <code>usDiff</code> are not part of the JSON-RPC standard.  <p>In order not to clutter the examples they will generally be omitted from the example code.</p> </aside>  > An example of a response with an error:  ```json {     \"jsonrpc\": \"2.0\",     \"id\": 8163,     \"error\": {         \"code\": 11050,         \"message\": \"bad_request\"     },     \"testnet\": false,     \"usIn\": 1535037392434763,     \"usOut\": 1535037392448119,     \"usDiff\": 13356 } ``` In case of an error the response message will contain the error field, with as value an object with the following with the following fields:  |Name|Type|Description |- -- -|- -- -|- -- -- -- -- --| |code|integer|A number that indicates the kind of error.| |message|string|A short description that indicates the kind of error.| |data|any|Additional data about the error. This field may be omitted.|  ## Notifications  > An example of a notification:  ```json {     \"jsonrpc\": \"2.0\",     \"method\": \"subscription\",     \"params\": {         \"channel\": \"deribit_price_index.btc_usd\",         \"data\": {             \"timestamp\": 1535098298227,             \"price\": 6521.17,             \"index_name\": \"btc_usd\"         }     } } ```  API users can subscribe to certain types of notifications. This means that they will receive JSON-RPC notification-messages from the server when certain events occur, such as changes to the index price or changes to the order book for a certain instrument.   The API methods [public/subscribe](#public-subscribe) and [private/subscribe](#private-subscribe) are used to set up a subscription. Since HTTP does not support the sending of messages from server to client, these methods are only availble when using the Websocket transport mechanism.  At the moment of subscription a \"channel\" must be specified. The channel determines the type of events that will be received.  See [Subscriptions](#subscriptions) for more details about the channels.  In accordance with the JSON-RPC specification, the format of a notification  is that of a request message without an <code>id</code> field. The value of the <code>method</code> field will always be <code>\"subscription\"</code>. The <code>params</code> field will always be an object with 2 members: <code>channel</code> and <code>data</code>. The value of the <code>channel</code> member is the name of the channel (a string). The value of the <code>data</code> member is an object that contains data  that is specific for the channel.   ## Authentication  > An example of a JSON request with token:  ```json {     \"id\": 5647,     \"method\": \"private/get_subaccounts\",     \"params\": {         \"access_token\": \"67SVutDoVZSzkUStHSuk51WntMNBJ5mh5DYZhwzpiqDF\"     } } ```  The API consists of `public` and `private` methods. The public methods do not require authentication. The private methods use OAuth 2.0 authentication. This means that a valid OAuth access token must be included in the request, which can get achived by calling method [public/auth](#public-auth).  When the token was assigned to the user, it should be passed along, with other request parameters, back to the server:  |Connection type|Access token placement |- -- -|- -- -- -- -- --| |**Websocket**|Inside request JSON parameters, as an `access_token` field| |**HTTP (REST)**|Header `Authorization: bearer ```Token``` ` value|  ### Additional authorization method - basic user credentials  <span style=\"color:red\"><b> ! Not recommended - however, it could be useful for quick testing API</b></span></br>  Every `private` method could be accessed by providing, inside HTTP `Authorization: Basic XXX` header, values with user `ClientId` and assigned `ClientSecret` (both values can be found on the API page on the Deribit website) encoded with `Base64`:  <code>Authorization: Basic BASE64(`ClientId` + `:` + `ClientSecret`)</code>   ### Additional authorization method - Deribit signature credentials  The Derbit service provides dedicated authorization method, which harness user generated signature to increase security level for passing request data. Generated value is passed inside `Authorization` header, coded as:  <code>Authorization: deri-hmac-sha256 id=```ClientId```,ts=```Timestamp```,sig=```Signature```,nonce=```Nonce```</code>  where:  |Deribit credential|Description |- -- -|- -- -- -- -- --| |*ClientId*|Can be found on the API page on the Deribit website| |*Timestamp*|Time when the request was generated - given as **miliseconds**. It's valid for **60 seconds** since generation, after that time any request with an old timestamp will be rejected.| |*Signature*|Value for signature calculated as described below | |*Nonce*|Single usage, user generated initialization vector for the server token|  The signature is generated by the following formula:  <code> Signature = HEX_STRING( HMAC-SHA256( ClientSecret, StringToSign ) );</code></br>  <code> StringToSign =  Timestamp + \"\\n\" + Nonce + \"\\n\" + RequestData;</code></br>  <code> RequestData =  UPPERCASE(HTTP_METHOD())  + \"\\n\" + URI() + \"\\n\" + RequestBody + \"\\n\";</code></br>   e.g. (using shell with ```openssl``` tool):  <code>&nbsp;&nbsp;&nbsp;&nbsp;ClientId=AAAAAAAAAAA</code></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;ClientSecret=ABCD</code></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;Timestamp=$( date +%s000 )</code></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;Nonce=$( cat /dev/urandom | tr -dc 'a-z0-9' | head -c8 )</code></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;URI=\"/api/v2/private/get_account_summary?currency=BTC\"</code></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;HttpMethod=GET</code></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;Body=\"\"</code></br></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;Signature=$( echo -ne \"${Timestamp}\\n${Nonce}\\n${HttpMethod}\\n${URI}\\n${Body}\\n\" | openssl sha256 -r -hmac \"$ClientSecret\" | cut -f1 -d' ' )</code></br></br> <code>&nbsp;&nbsp;&nbsp;&nbsp;echo $Signature</code></br></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;shell output> ea40d5e5e4fae235ab22b61da98121fbf4acdc06db03d632e23c66bcccb90d2c  (**WARNING**: Exact value depends on current timestamp and client credentials</code></br></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;curl -s -X ${HttpMethod} -H \"Authorization: deri-hmac-sha256 id=${ClientId},ts=${Timestamp},nonce=${Nonce},sig=${Signature}\" \"https://www.deribit.com${URI}\"</code></br></br>    ### Additional authorization method - signature credentials (WebSocket API)  When connecting through Websocket, user can request for authorization using ```client_credential``` method, which requires providing following parameters (as a part of JSON request):  |JSON parameter|Description |- -- -|- -- -- -- -- --| |*grant_type*|Must be **client_signature**| |*client_id*|Can be found on the API page on the Deribit website| |*timestamp*|Time when the request was generated - given as **miliseconds**. It's valid for **60 seconds** since generation, after that time any request with an old timestamp will be rejected.| |*signature*|Value for signature calculated as described below | |*nonce*|Single usage, user generated initialization vector for the server token| |*data*|**Optional** field, which contains any user specific value|  The signature is generated by the following formula:  <code> StringToSign =  Timestamp + \"\\n\" + Nonce + \"\\n\" + Data;</code></br>  <code> Signature = HEX_STRING( HMAC-SHA256( ClientSecret, StringToSign ) );</code></br>   e.g. (using shell with ```openssl``` tool):  <code>&nbsp;&nbsp;&nbsp;&nbsp;ClientId=AAAAAAAAAAA</code></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;ClientSecret=ABCD</code></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;Timestamp=$( date +%s000 ) # e.g. 1554883365000 </code></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;Nonce=$( cat /dev/urandom | tr -dc 'a-z0-9' | head -c8 ) # e.g. fdbmmz79 </code></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;Data=\"\"</code></br></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;Signature=$( echo -ne \"${Timestamp}\\n${Nonce}\\n${Data}\\n\" | openssl sha256 -r -hmac \"$ClientSecret\" | cut -f1 -d' ' )</code></br></br> <code>&nbsp;&nbsp;&nbsp;&nbsp;echo $Signature</code></br></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;shell output> e20c9cd5639d41f8bbc88f4d699c4baf94a4f0ee320e9a116b72743c449eb994  (**WARNING**: Exact value depends on current timestamp and client credentials</code></br></br>   You can also check the signature value using some online tools like, e.g: [https://codebeautify.org/hmac-generator](https://codebeautify.org/hmac-generator) (but don't forget about adding *newline* after each part of the hashed text and remember that you **should use** it only with your **test credentials**).   Here's a sample JSON request created using the values from the example above:  <code> {                            </br> &nbsp;&nbsp;\"jsonrpc\" : \"2.0\",         </br> &nbsp;&nbsp;\"id\" : 9929,               </br> &nbsp;&nbsp;\"method\" : \"public/auth\",  </br> &nbsp;&nbsp;\"params\" :                 </br> &nbsp;&nbsp;{                        </br> &nbsp;&nbsp;&nbsp;&nbsp;\"grant_type\" : \"client_signature\",   </br> &nbsp;&nbsp;&nbsp;&nbsp;\"client_id\" : \"AAAAAAAAAAA\",         </br> &nbsp;&nbsp;&nbsp;&nbsp;\"timestamp\": \"1554883365000\",        </br> &nbsp;&nbsp;&nbsp;&nbsp;\"nonce\": \"fdbmmz79\",                 </br> &nbsp;&nbsp;&nbsp;&nbsp;\"data\": \"\",                          </br> &nbsp;&nbsp;&nbsp;&nbsp;\"signature\" : \"e20c9cd5639d41f8bbc88f4d699c4baf94a4f0ee320e9a116b72743c449eb994\"  </br> &nbsp;&nbsp;}                        </br> }                            </br> </code>   ### Access scope  When asking for `access token` user can provide the required access level (called `scope`) which defines what type of functionality he/she wants to use, and whether requests are only going to check for some data or also to update them.  Scopes are required and checked for `private` methods, so if you plan to use only `public` information you can stay with values assigned by default.  |Scope|Description |- -- -|- -- -- -- -- --| |*account:read*|Access to **account** methods - read only data| |*account:read_write*|Access to **account** methods - allows to manage account settings, add subaccounts, etc.| |*trade:read*|Access to **trade** methods - read only data| |*trade:read_write*|Access to **trade** methods - required to create and modify orders| |*wallet:read*|Access to **wallet** methods - read only data| |*wallet:read_write*|Access to **wallet** methods - allows to withdraw, generate new deposit address, etc.| |*wallet:none*, *account:none*, *trade:none*|Blocked access to specified functionality|    <span style=\"color:red\">**NOTICE:**</span> Depending on choosing an authentication method (```grant type```) some scopes could be narrowed by the server. e.g. when ```grant_type = client_credentials``` and ```scope = wallet:read_write``` it's modified by the server as ```scope = wallet:read```\"   ## JSON-RPC over websocket Websocket is the prefered transport mechanism for the JSON-RPC API, because it is faster and because it can support [subscriptions](#subscriptions) and [cancel on disconnect](#private-enable_cancel_on_disconnect). The code examples that can be found next to each of the methods show how websockets can be used from Python or Javascript/node.js.  ## JSON-RPC over HTTP Besides websockets it is also possible to use the API via HTTP. The code examples for 'shell' show how this can be done using curl. Note that subscriptions and cancel on disconnect are not supported via HTTP.  #Methods 
 *
 * The version of the OpenAPI document: 2.0.0
 * 
 * Generated by: https://github.com/openapitools/openapi-generator.git
 */

using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Linq;
using RestSharp;
using Org.OpenAPITools.Client;

namespace Org.OpenAPITools.Api
{
    /// <summary>
    /// Represents a collection of functions to interact with the API endpoints
    /// </summary>
    public interface ITradingApi : IApiAccessor
    {
        #region Synchronous Operations
        /// <summary>
        /// Places a buy order for an instrument.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">Instrument name</param>
        /// <param name="amount">It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH</param>
        /// <param name="type">The order type, default: &#x60;\&quot;limit\&quot;&#x60; (optional)</param>
        /// <param name="label">user defined label for the order (maximum 32 characters) (optional)</param>
        /// <param name="price">&lt;p&gt;The order price in base currency (Only for limit and stop_limit orders)&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;usd, the field price should be the option price value in USD.&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;implv, the field price should be a value of implied volatility in percentages. For example,  price&#x3D;100, means implied volatility of 100%&lt;/p&gt; (optional)</param>
        /// <param name="timeInForce">&lt;p&gt;Specifies how long the order remains in effect. Default &#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; &lt;ul&gt; &lt;li&gt;&#x60;\&quot;good_til_cancelled\&quot;&#x60; - unfilled order remains in order book until cancelled&lt;/li&gt; &lt;li&gt;&#x60;\&quot;fill_or_kill\&quot;&#x60; - execute a transaction immediately and completely or not at all&lt;/li&gt; &lt;li&gt;&#x60;\&quot;immediate_or_cancel\&quot;&#x60; - execute a transaction immediately, and any portion of the order that cannot be immediately filled is cancelled&lt;/li&gt; &lt;/ul&gt; (optional, default to good_til_cancelled)</param>
        /// <param name="maxShow">Maximum amount within an order to be shown to other customers, &#x60;0&#x60; for invisible order (optional, default to 1M)</param>
        /// <param name="postOnly">&lt;p&gt;If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.&lt;/p&gt; &lt;p&gt;Only valid in combination with time_in_force&#x3D;&#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; (optional, default to true)</param>
        /// <param name="reduceOnly">If &#x60;true&#x60;, the order is considered reduce-only which is intended to only reduce a current position (optional, default to false)</param>
        /// <param name="stopPrice">Stop price, required for stop limit orders (Only for stop orders) (optional)</param>
        /// <param name="trigger">Defines trigger type, required for &#x60;\&quot;stop_limit\&quot;&#x60; order type (optional)</param>
        /// <param name="advanced">Advanced option order type. (Only for options) (optional)</param>
        /// <returns>Object</returns>
        Object PrivateBuyGet (string instrumentName, decimal? amount, string type = null, string label = null, decimal? price = null, string timeInForce = null, decimal? maxShow = null, bool? postOnly = null, bool? reduceOnly = null, decimal? stopPrice = null, string trigger = null, string advanced = null);

        /// <summary>
        /// Places a buy order for an instrument.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">Instrument name</param>
        /// <param name="amount">It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH</param>
        /// <param name="type">The order type, default: &#x60;\&quot;limit\&quot;&#x60; (optional)</param>
        /// <param name="label">user defined label for the order (maximum 32 characters) (optional)</param>
        /// <param name="price">&lt;p&gt;The order price in base currency (Only for limit and stop_limit orders)&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;usd, the field price should be the option price value in USD.&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;implv, the field price should be a value of implied volatility in percentages. For example,  price&#x3D;100, means implied volatility of 100%&lt;/p&gt; (optional)</param>
        /// <param name="timeInForce">&lt;p&gt;Specifies how long the order remains in effect. Default &#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; &lt;ul&gt; &lt;li&gt;&#x60;\&quot;good_til_cancelled\&quot;&#x60; - unfilled order remains in order book until cancelled&lt;/li&gt; &lt;li&gt;&#x60;\&quot;fill_or_kill\&quot;&#x60; - execute a transaction immediately and completely or not at all&lt;/li&gt; &lt;li&gt;&#x60;\&quot;immediate_or_cancel\&quot;&#x60; - execute a transaction immediately, and any portion of the order that cannot be immediately filled is cancelled&lt;/li&gt; &lt;/ul&gt; (optional, default to good_til_cancelled)</param>
        /// <param name="maxShow">Maximum amount within an order to be shown to other customers, &#x60;0&#x60; for invisible order (optional, default to 1M)</param>
        /// <param name="postOnly">&lt;p&gt;If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.&lt;/p&gt; &lt;p&gt;Only valid in combination with time_in_force&#x3D;&#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; (optional, default to true)</param>
        /// <param name="reduceOnly">If &#x60;true&#x60;, the order is considered reduce-only which is intended to only reduce a current position (optional, default to false)</param>
        /// <param name="stopPrice">Stop price, required for stop limit orders (Only for stop orders) (optional)</param>
        /// <param name="trigger">Defines trigger type, required for &#x60;\&quot;stop_limit\&quot;&#x60; order type (optional)</param>
        /// <param name="advanced">Advanced option order type. (Only for options) (optional)</param>
        /// <returns>ApiResponse of Object</returns>
        ApiResponse<Object> PrivateBuyGetWithHttpInfo (string instrumentName, decimal? amount, string type = null, string label = null, decimal? price = null, string timeInForce = null, decimal? maxShow = null, bool? postOnly = null, bool? reduceOnly = null, decimal? stopPrice = null, string trigger = null, string advanced = null);
        /// <summary>
        /// Cancels all orders by currency, optionally filtered by instrument kind and/or order type.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="kind">Instrument kind, if not provided instruments of all kinds are considered (optional)</param>
        /// <param name="type">Order type - limit, stop or all, default - &#x60;all&#x60; (optional)</param>
        /// <returns>Object</returns>
        Object PrivateCancelAllByCurrencyGet (string currency, string kind = null, string type = null);

        /// <summary>
        /// Cancels all orders by currency, optionally filtered by instrument kind and/or order type.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="kind">Instrument kind, if not provided instruments of all kinds are considered (optional)</param>
        /// <param name="type">Order type - limit, stop or all, default - &#x60;all&#x60; (optional)</param>
        /// <returns>ApiResponse of Object</returns>
        ApiResponse<Object> PrivateCancelAllByCurrencyGetWithHttpInfo (string currency, string kind = null, string type = null);
        /// <summary>
        /// Cancels all orders by instrument, optionally filtered by order type.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">Instrument name</param>
        /// <param name="type">Order type - limit, stop or all, default - &#x60;all&#x60; (optional)</param>
        /// <returns>Object</returns>
        Object PrivateCancelAllByInstrumentGet (string instrumentName, string type = null);

        /// <summary>
        /// Cancels all orders by instrument, optionally filtered by order type.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">Instrument name</param>
        /// <param name="type">Order type - limit, stop or all, default - &#x60;all&#x60; (optional)</param>
        /// <returns>ApiResponse of Object</returns>
        ApiResponse<Object> PrivateCancelAllByInstrumentGetWithHttpInfo (string instrumentName, string type = null);
        /// <summary>
        /// This method cancels all users orders and stop orders within all currencies and instrument kinds.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <returns>Object</returns>
        Object PrivateCancelAllGet ();

        /// <summary>
        /// This method cancels all users orders and stop orders within all currencies and instrument kinds.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <returns>ApiResponse of Object</returns>
        ApiResponse<Object> PrivateCancelAllGetWithHttpInfo ();
        /// <summary>
        /// Cancel an order, specified by order id
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="orderId">The order id</param>
        /// <returns>Object</returns>
        Object PrivateCancelGet (string orderId);

        /// <summary>
        /// Cancel an order, specified by order id
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="orderId">The order id</param>
        /// <returns>ApiResponse of Object</returns>
        ApiResponse<Object> PrivateCancelGetWithHttpInfo (string orderId);
        /// <summary>
        /// Makes closing position reduce only order .
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">Instrument name</param>
        /// <param name="type">The order type</param>
        /// <param name="price">Optional price for limit order. (optional)</param>
        /// <returns>Object</returns>
        Object PrivateClosePositionGet (string instrumentName, string type, decimal? price = null);

        /// <summary>
        /// Makes closing position reduce only order .
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">Instrument name</param>
        /// <param name="type">The order type</param>
        /// <param name="price">Optional price for limit order. (optional)</param>
        /// <returns>ApiResponse of Object</returns>
        ApiResponse<Object> PrivateClosePositionGetWithHttpInfo (string instrumentName, string type, decimal? price = null);
        /// <summary>
        /// Change price, amount and/or other properties of an order.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="orderId">The order id</param>
        /// <param name="amount">It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH</param>
        /// <param name="price">&lt;p&gt;The order price in base currency.&lt;/p&gt; &lt;p&gt;When editing an option order with advanced&#x3D;usd, the field price should be the option price value in USD.&lt;/p&gt; &lt;p&gt;When editing an option order with advanced&#x3D;implv, the field price should be a value of implied volatility in percentages. For example,  price&#x3D;100, means implied volatility of 100%&lt;/p&gt;</param>
        /// <param name="postOnly">&lt;p&gt;If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.&lt;/p&gt; &lt;p&gt;Only valid in combination with time_in_force&#x3D;&#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; (optional, default to true)</param>
        /// <param name="advanced">Advanced option order type. If you have posted an advanced option order, it is necessary to re-supply this parameter when editing it (Only for options) (optional)</param>
        /// <param name="stopPrice">Stop price, required for stop limit orders (Only for stop orders) (optional)</param>
        /// <returns>Object</returns>
        Object PrivateEditGet (string orderId, decimal? amount, decimal? price, bool? postOnly = null, string advanced = null, decimal? stopPrice = null);

        /// <summary>
        /// Change price, amount and/or other properties of an order.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="orderId">The order id</param>
        /// <param name="amount">It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH</param>
        /// <param name="price">&lt;p&gt;The order price in base currency.&lt;/p&gt; &lt;p&gt;When editing an option order with advanced&#x3D;usd, the field price should be the option price value in USD.&lt;/p&gt; &lt;p&gt;When editing an option order with advanced&#x3D;implv, the field price should be a value of implied volatility in percentages. For example,  price&#x3D;100, means implied volatility of 100%&lt;/p&gt;</param>
        /// <param name="postOnly">&lt;p&gt;If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.&lt;/p&gt; &lt;p&gt;Only valid in combination with time_in_force&#x3D;&#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; (optional, default to true)</param>
        /// <param name="advanced">Advanced option order type. If you have posted an advanced option order, it is necessary to re-supply this parameter when editing it (Only for options) (optional)</param>
        /// <param name="stopPrice">Stop price, required for stop limit orders (Only for stop orders) (optional)</param>
        /// <returns>ApiResponse of Object</returns>
        ApiResponse<Object> PrivateEditGetWithHttpInfo (string orderId, decimal? amount, decimal? price, bool? postOnly = null, string advanced = null, decimal? stopPrice = null);
        /// <summary>
        /// Get margins for given instrument, amount and price.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">Instrument name</param>
        /// <param name="amount">Amount, integer for future, float for option. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH.</param>
        /// <param name="price">Price</param>
        /// <returns>Object</returns>
        Object PrivateGetMarginsGet (string instrumentName, decimal? amount, decimal? price);

        /// <summary>
        /// Get margins for given instrument, amount and price.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">Instrument name</param>
        /// <param name="amount">Amount, integer for future, float for option. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH.</param>
        /// <param name="price">Price</param>
        /// <returns>ApiResponse of Object</returns>
        ApiResponse<Object> PrivateGetMarginsGetWithHttpInfo (string instrumentName, decimal? amount, decimal? price);
        /// <summary>
        /// Retrieves list of user&#39;s open orders.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="kind">Instrument kind, if not provided instruments of all kinds are considered (optional)</param>
        /// <param name="type">Order type, default - &#x60;all&#x60; (optional)</param>
        /// <returns>Object</returns>
        Object PrivateGetOpenOrdersByCurrencyGet (string currency, string kind = null, string type = null);

        /// <summary>
        /// Retrieves list of user&#39;s open orders.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="kind">Instrument kind, if not provided instruments of all kinds are considered (optional)</param>
        /// <param name="type">Order type, default - &#x60;all&#x60; (optional)</param>
        /// <returns>ApiResponse of Object</returns>
        ApiResponse<Object> PrivateGetOpenOrdersByCurrencyGetWithHttpInfo (string currency, string kind = null, string type = null);
        /// <summary>
        /// Retrieves list of user&#39;s open orders within given Instrument.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">Instrument name</param>
        /// <param name="type">Order type, default - &#x60;all&#x60; (optional)</param>
        /// <returns>Object</returns>
        Object PrivateGetOpenOrdersByInstrumentGet (string instrumentName, string type = null);

        /// <summary>
        /// Retrieves list of user&#39;s open orders within given Instrument.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">Instrument name</param>
        /// <param name="type">Order type, default - &#x60;all&#x60; (optional)</param>
        /// <returns>ApiResponse of Object</returns>
        ApiResponse<Object> PrivateGetOpenOrdersByInstrumentGetWithHttpInfo (string instrumentName, string type = null);
        /// <summary>
        /// Retrieves history of orders that have been partially or fully filled.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="kind">Instrument kind, if not provided instruments of all kinds are considered (optional)</param>
        /// <param name="count">Number of requested items, default - &#x60;20&#x60; (optional)</param>
        /// <param name="offset">The offset for pagination, default - &#x60;0&#x60; (optional)</param>
        /// <param name="includeOld">Include in result orders older than 2 days, default - &#x60;false&#x60; (optional)</param>
        /// <param name="includeUnfilled">Include in result fully unfilled closed orders, default - &#x60;false&#x60; (optional)</param>
        /// <returns>Object</returns>
        Object PrivateGetOrderHistoryByCurrencyGet (string currency, string kind = null, int? count = null, int? offset = null, bool? includeOld = null, bool? includeUnfilled = null);

        /// <summary>
        /// Retrieves history of orders that have been partially or fully filled.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="kind">Instrument kind, if not provided instruments of all kinds are considered (optional)</param>
        /// <param name="count">Number of requested items, default - &#x60;20&#x60; (optional)</param>
        /// <param name="offset">The offset for pagination, default - &#x60;0&#x60; (optional)</param>
        /// <param name="includeOld">Include in result orders older than 2 days, default - &#x60;false&#x60; (optional)</param>
        /// <param name="includeUnfilled">Include in result fully unfilled closed orders, default - &#x60;false&#x60; (optional)</param>
        /// <returns>ApiResponse of Object</returns>
        ApiResponse<Object> PrivateGetOrderHistoryByCurrencyGetWithHttpInfo (string currency, string kind = null, int? count = null, int? offset = null, bool? includeOld = null, bool? includeUnfilled = null);
        /// <summary>
        /// Retrieves history of orders that have been partially or fully filled.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">Instrument name</param>
        /// <param name="count">Number of requested items, default - &#x60;20&#x60; (optional)</param>
        /// <param name="offset">The offset for pagination, default - &#x60;0&#x60; (optional)</param>
        /// <param name="includeOld">Include in result orders older than 2 days, default - &#x60;false&#x60; (optional)</param>
        /// <param name="includeUnfilled">Include in result fully unfilled closed orders, default - &#x60;false&#x60; (optional)</param>
        /// <returns>Object</returns>
        Object PrivateGetOrderHistoryByInstrumentGet (string instrumentName, int? count = null, int? offset = null, bool? includeOld = null, bool? includeUnfilled = null);

        /// <summary>
        /// Retrieves history of orders that have been partially or fully filled.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">Instrument name</param>
        /// <param name="count">Number of requested items, default - &#x60;20&#x60; (optional)</param>
        /// <param name="offset">The offset for pagination, default - &#x60;0&#x60; (optional)</param>
        /// <param name="includeOld">Include in result orders older than 2 days, default - &#x60;false&#x60; (optional)</param>
        /// <param name="includeUnfilled">Include in result fully unfilled closed orders, default - &#x60;false&#x60; (optional)</param>
        /// <returns>ApiResponse of Object</returns>
        ApiResponse<Object> PrivateGetOrderHistoryByInstrumentGetWithHttpInfo (string instrumentName, int? count = null, int? offset = null, bool? includeOld = null, bool? includeUnfilled = null);
        /// <summary>
        /// Retrieves initial margins of given orders
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="ids">Ids of orders</param>
        /// <returns>Object</returns>
        Object PrivateGetOrderMarginByIdsGet (List<string> ids);

        /// <summary>
        /// Retrieves initial margins of given orders
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="ids">Ids of orders</param>
        /// <returns>ApiResponse of Object</returns>
        ApiResponse<Object> PrivateGetOrderMarginByIdsGetWithHttpInfo (List<string> ids);
        /// <summary>
        /// Retrieve the current state of an order.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="orderId">The order id</param>
        /// <returns>Object</returns>
        Object PrivateGetOrderStateGet (string orderId);

        /// <summary>
        /// Retrieve the current state of an order.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="orderId">The order id</param>
        /// <returns>ApiResponse of Object</returns>
        ApiResponse<Object> PrivateGetOrderStateGetWithHttpInfo (string orderId);
        /// <summary>
        /// Retrieves settlement, delivery and bankruptcy events that have affected your account.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="type">Settlement type (optional)</param>
        /// <param name="count">Number of requested items, default - &#x60;20&#x60; (optional)</param>
        /// <returns>Object</returns>
        Object PrivateGetSettlementHistoryByCurrencyGet (string currency, string type = null, int? count = null);

        /// <summary>
        /// Retrieves settlement, delivery and bankruptcy events that have affected your account.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="type">Settlement type (optional)</param>
        /// <param name="count">Number of requested items, default - &#x60;20&#x60; (optional)</param>
        /// <returns>ApiResponse of Object</returns>
        ApiResponse<Object> PrivateGetSettlementHistoryByCurrencyGetWithHttpInfo (string currency, string type = null, int? count = null);
        /// <summary>
        /// Retrieves public settlement, delivery and bankruptcy events filtered by instrument name
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">Instrument name</param>
        /// <param name="type">Settlement type (optional)</param>
        /// <param name="count">Number of requested items, default - &#x60;20&#x60; (optional)</param>
        /// <returns>Object</returns>
        Object PrivateGetSettlementHistoryByInstrumentGet (string instrumentName, string type = null, int? count = null);

        /// <summary>
        /// Retrieves public settlement, delivery and bankruptcy events filtered by instrument name
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">Instrument name</param>
        /// <param name="type">Settlement type (optional)</param>
        /// <param name="count">Number of requested items, default - &#x60;20&#x60; (optional)</param>
        /// <returns>ApiResponse of Object</returns>
        ApiResponse<Object> PrivateGetSettlementHistoryByInstrumentGetWithHttpInfo (string instrumentName, string type = null, int? count = null);
        /// <summary>
        /// Retrieve the latest user trades that have occurred for instruments in a specific currency symbol and within given time range.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="startTimestamp">The earliest timestamp to return result for</param>
        /// <param name="endTimestamp">The most recent timestamp to return result for</param>
        /// <param name="kind">Instrument kind, if not provided instruments of all kinds are considered (optional)</param>
        /// <param name="count">Number of requested items, default - &#x60;10&#x60; (optional)</param>
        /// <param name="includeOld">Include trades older than 7 days, default - &#x60;false&#x60; (optional)</param>
        /// <param name="sorting">Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) (optional)</param>
        /// <returns>Object</returns>
        Object PrivateGetUserTradesByCurrencyAndTimeGet (string currency, int? startTimestamp, int? endTimestamp, string kind = null, int? count = null, bool? includeOld = null, string sorting = null);

        /// <summary>
        /// Retrieve the latest user trades that have occurred for instruments in a specific currency symbol and within given time range.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="startTimestamp">The earliest timestamp to return result for</param>
        /// <param name="endTimestamp">The most recent timestamp to return result for</param>
        /// <param name="kind">Instrument kind, if not provided instruments of all kinds are considered (optional)</param>
        /// <param name="count">Number of requested items, default - &#x60;10&#x60; (optional)</param>
        /// <param name="includeOld">Include trades older than 7 days, default - &#x60;false&#x60; (optional)</param>
        /// <param name="sorting">Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) (optional)</param>
        /// <returns>ApiResponse of Object</returns>
        ApiResponse<Object> PrivateGetUserTradesByCurrencyAndTimeGetWithHttpInfo (string currency, int? startTimestamp, int? endTimestamp, string kind = null, int? count = null, bool? includeOld = null, string sorting = null);
        /// <summary>
        /// Retrieve the latest user trades that have occurred for instruments in a specific currency symbol.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="kind">Instrument kind, if not provided instruments of all kinds are considered (optional)</param>
        /// <param name="startId">The ID number of the first trade to be returned (optional)</param>
        /// <param name="endId">The ID number of the last trade to be returned (optional)</param>
        /// <param name="count">Number of requested items, default - &#x60;10&#x60; (optional)</param>
        /// <param name="includeOld">Include trades older than 7 days, default - &#x60;false&#x60; (optional)</param>
        /// <param name="sorting">Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) (optional)</param>
        /// <returns>Object</returns>
        Object PrivateGetUserTradesByCurrencyGet (string currency, string kind = null, string startId = null, string endId = null, int? count = null, bool? includeOld = null, string sorting = null);

        /// <summary>
        /// Retrieve the latest user trades that have occurred for instruments in a specific currency symbol.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="kind">Instrument kind, if not provided instruments of all kinds are considered (optional)</param>
        /// <param name="startId">The ID number of the first trade to be returned (optional)</param>
        /// <param name="endId">The ID number of the last trade to be returned (optional)</param>
        /// <param name="count">Number of requested items, default - &#x60;10&#x60; (optional)</param>
        /// <param name="includeOld">Include trades older than 7 days, default - &#x60;false&#x60; (optional)</param>
        /// <param name="sorting">Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) (optional)</param>
        /// <returns>ApiResponse of Object</returns>
        ApiResponse<Object> PrivateGetUserTradesByCurrencyGetWithHttpInfo (string currency, string kind = null, string startId = null, string endId = null, int? count = null, bool? includeOld = null, string sorting = null);
        /// <summary>
        /// Retrieve the latest user trades that have occurred for a specific instrument and within given time range.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">Instrument name</param>
        /// <param name="startTimestamp">The earliest timestamp to return result for</param>
        /// <param name="endTimestamp">The most recent timestamp to return result for</param>
        /// <param name="count">Number of requested items, default - &#x60;10&#x60; (optional)</param>
        /// <param name="includeOld">Include trades older than 7 days, default - &#x60;false&#x60; (optional)</param>
        /// <param name="sorting">Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) (optional)</param>
        /// <returns>Object</returns>
        Object PrivateGetUserTradesByInstrumentAndTimeGet (string instrumentName, int? startTimestamp, int? endTimestamp, int? count = null, bool? includeOld = null, string sorting = null);

        /// <summary>
        /// Retrieve the latest user trades that have occurred for a specific instrument and within given time range.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">Instrument name</param>
        /// <param name="startTimestamp">The earliest timestamp to return result for</param>
        /// <param name="endTimestamp">The most recent timestamp to return result for</param>
        /// <param name="count">Number of requested items, default - &#x60;10&#x60; (optional)</param>
        /// <param name="includeOld">Include trades older than 7 days, default - &#x60;false&#x60; (optional)</param>
        /// <param name="sorting">Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) (optional)</param>
        /// <returns>ApiResponse of Object</returns>
        ApiResponse<Object> PrivateGetUserTradesByInstrumentAndTimeGetWithHttpInfo (string instrumentName, int? startTimestamp, int? endTimestamp, int? count = null, bool? includeOld = null, string sorting = null);
        /// <summary>
        /// Retrieve the latest user trades that have occurred for a specific instrument.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">Instrument name</param>
        /// <param name="startSeq">The sequence number of the first trade to be returned (optional)</param>
        /// <param name="endSeq">The sequence number of the last trade to be returned (optional)</param>
        /// <param name="count">Number of requested items, default - &#x60;10&#x60; (optional)</param>
        /// <param name="includeOld">Include trades older than 7 days, default - &#x60;false&#x60; (optional)</param>
        /// <param name="sorting">Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) (optional)</param>
        /// <returns>Object</returns>
        Object PrivateGetUserTradesByInstrumentGet (string instrumentName, int? startSeq = null, int? endSeq = null, int? count = null, bool? includeOld = null, string sorting = null);

        /// <summary>
        /// Retrieve the latest user trades that have occurred for a specific instrument.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">Instrument name</param>
        /// <param name="startSeq">The sequence number of the first trade to be returned (optional)</param>
        /// <param name="endSeq">The sequence number of the last trade to be returned (optional)</param>
        /// <param name="count">Number of requested items, default - &#x60;10&#x60; (optional)</param>
        /// <param name="includeOld">Include trades older than 7 days, default - &#x60;false&#x60; (optional)</param>
        /// <param name="sorting">Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) (optional)</param>
        /// <returns>ApiResponse of Object</returns>
        ApiResponse<Object> PrivateGetUserTradesByInstrumentGetWithHttpInfo (string instrumentName, int? startSeq = null, int? endSeq = null, int? count = null, bool? includeOld = null, string sorting = null);
        /// <summary>
        /// Retrieve the list of user trades that was created for given order
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="orderId">The order id</param>
        /// <param name="sorting">Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) (optional)</param>
        /// <returns>Object</returns>
        Object PrivateGetUserTradesByOrderGet (string orderId, string sorting = null);

        /// <summary>
        /// Retrieve the list of user trades that was created for given order
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="orderId">The order id</param>
        /// <param name="sorting">Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) (optional)</param>
        /// <returns>ApiResponse of Object</returns>
        ApiResponse<Object> PrivateGetUserTradesByOrderGetWithHttpInfo (string orderId, string sorting = null);
        /// <summary>
        /// Places a sell order for an instrument.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">Instrument name</param>
        /// <param name="amount">It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH</param>
        /// <param name="type">The order type, default: &#x60;\&quot;limit\&quot;&#x60; (optional)</param>
        /// <param name="label">user defined label for the order (maximum 32 characters) (optional)</param>
        /// <param name="price">&lt;p&gt;The order price in base currency (Only for limit and stop_limit orders)&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;usd, the field price should be the option price value in USD.&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;implv, the field price should be a value of implied volatility in percentages. For example,  price&#x3D;100, means implied volatility of 100%&lt;/p&gt; (optional)</param>
        /// <param name="timeInForce">&lt;p&gt;Specifies how long the order remains in effect. Default &#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; &lt;ul&gt; &lt;li&gt;&#x60;\&quot;good_til_cancelled\&quot;&#x60; - unfilled order remains in order book until cancelled&lt;/li&gt; &lt;li&gt;&#x60;\&quot;fill_or_kill\&quot;&#x60; - execute a transaction immediately and completely or not at all&lt;/li&gt; &lt;li&gt;&#x60;\&quot;immediate_or_cancel\&quot;&#x60; - execute a transaction immediately, and any portion of the order that cannot be immediately filled is cancelled&lt;/li&gt; &lt;/ul&gt; (optional, default to good_til_cancelled)</param>
        /// <param name="maxShow">Maximum amount within an order to be shown to other customers, &#x60;0&#x60; for invisible order (optional, default to 1M)</param>
        /// <param name="postOnly">&lt;p&gt;If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.&lt;/p&gt; &lt;p&gt;Only valid in combination with time_in_force&#x3D;&#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; (optional, default to true)</param>
        /// <param name="reduceOnly">If &#x60;true&#x60;, the order is considered reduce-only which is intended to only reduce a current position (optional, default to false)</param>
        /// <param name="stopPrice">Stop price, required for stop limit orders (Only for stop orders) (optional)</param>
        /// <param name="trigger">Defines trigger type, required for &#x60;\&quot;stop_limit\&quot;&#x60; order type (optional)</param>
        /// <param name="advanced">Advanced option order type. (Only for options) (optional)</param>
        /// <returns>Object</returns>
        Object PrivateSellGet (string instrumentName, decimal? amount, string type = null, string label = null, decimal? price = null, string timeInForce = null, decimal? maxShow = null, bool? postOnly = null, bool? reduceOnly = null, decimal? stopPrice = null, string trigger = null, string advanced = null);

        /// <summary>
        /// Places a sell order for an instrument.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">Instrument name</param>
        /// <param name="amount">It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH</param>
        /// <param name="type">The order type, default: &#x60;\&quot;limit\&quot;&#x60; (optional)</param>
        /// <param name="label">user defined label for the order (maximum 32 characters) (optional)</param>
        /// <param name="price">&lt;p&gt;The order price in base currency (Only for limit and stop_limit orders)&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;usd, the field price should be the option price value in USD.&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;implv, the field price should be a value of implied volatility in percentages. For example,  price&#x3D;100, means implied volatility of 100%&lt;/p&gt; (optional)</param>
        /// <param name="timeInForce">&lt;p&gt;Specifies how long the order remains in effect. Default &#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; &lt;ul&gt; &lt;li&gt;&#x60;\&quot;good_til_cancelled\&quot;&#x60; - unfilled order remains in order book until cancelled&lt;/li&gt; &lt;li&gt;&#x60;\&quot;fill_or_kill\&quot;&#x60; - execute a transaction immediately and completely or not at all&lt;/li&gt; &lt;li&gt;&#x60;\&quot;immediate_or_cancel\&quot;&#x60; - execute a transaction immediately, and any portion of the order that cannot be immediately filled is cancelled&lt;/li&gt; &lt;/ul&gt; (optional, default to good_til_cancelled)</param>
        /// <param name="maxShow">Maximum amount within an order to be shown to other customers, &#x60;0&#x60; for invisible order (optional, default to 1M)</param>
        /// <param name="postOnly">&lt;p&gt;If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.&lt;/p&gt; &lt;p&gt;Only valid in combination with time_in_force&#x3D;&#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; (optional, default to true)</param>
        /// <param name="reduceOnly">If &#x60;true&#x60;, the order is considered reduce-only which is intended to only reduce a current position (optional, default to false)</param>
        /// <param name="stopPrice">Stop price, required for stop limit orders (Only for stop orders) (optional)</param>
        /// <param name="trigger">Defines trigger type, required for &#x60;\&quot;stop_limit\&quot;&#x60; order type (optional)</param>
        /// <param name="advanced">Advanced option order type. (Only for options) (optional)</param>
        /// <returns>ApiResponse of Object</returns>
        ApiResponse<Object> PrivateSellGetWithHttpInfo (string instrumentName, decimal? amount, string type = null, string label = null, decimal? price = null, string timeInForce = null, decimal? maxShow = null, bool? postOnly = null, bool? reduceOnly = null, decimal? stopPrice = null, string trigger = null, string advanced = null);
        #endregion Synchronous Operations
        #region Asynchronous Operations
        /// <summary>
        /// Places a buy order for an instrument.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">Instrument name</param>
        /// <param name="amount">It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH</param>
        /// <param name="type">The order type, default: &#x60;\&quot;limit\&quot;&#x60; (optional)</param>
        /// <param name="label">user defined label for the order (maximum 32 characters) (optional)</param>
        /// <param name="price">&lt;p&gt;The order price in base currency (Only for limit and stop_limit orders)&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;usd, the field price should be the option price value in USD.&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;implv, the field price should be a value of implied volatility in percentages. For example,  price&#x3D;100, means implied volatility of 100%&lt;/p&gt; (optional)</param>
        /// <param name="timeInForce">&lt;p&gt;Specifies how long the order remains in effect. Default &#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; &lt;ul&gt; &lt;li&gt;&#x60;\&quot;good_til_cancelled\&quot;&#x60; - unfilled order remains in order book until cancelled&lt;/li&gt; &lt;li&gt;&#x60;\&quot;fill_or_kill\&quot;&#x60; - execute a transaction immediately and completely or not at all&lt;/li&gt; &lt;li&gt;&#x60;\&quot;immediate_or_cancel\&quot;&#x60; - execute a transaction immediately, and any portion of the order that cannot be immediately filled is cancelled&lt;/li&gt; &lt;/ul&gt; (optional, default to good_til_cancelled)</param>
        /// <param name="maxShow">Maximum amount within an order to be shown to other customers, &#x60;0&#x60; for invisible order (optional, default to 1M)</param>
        /// <param name="postOnly">&lt;p&gt;If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.&lt;/p&gt; &lt;p&gt;Only valid in combination with time_in_force&#x3D;&#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; (optional, default to true)</param>
        /// <param name="reduceOnly">If &#x60;true&#x60;, the order is considered reduce-only which is intended to only reduce a current position (optional, default to false)</param>
        /// <param name="stopPrice">Stop price, required for stop limit orders (Only for stop orders) (optional)</param>
        /// <param name="trigger">Defines trigger type, required for &#x60;\&quot;stop_limit\&quot;&#x60; order type (optional)</param>
        /// <param name="advanced">Advanced option order type. (Only for options) (optional)</param>
        /// <returns>Task of Object</returns>
        System.Threading.Tasks.Task<Object> PrivateBuyGetAsync (string instrumentName, decimal? amount, string type = null, string label = null, decimal? price = null, string timeInForce = null, decimal? maxShow = null, bool? postOnly = null, bool? reduceOnly = null, decimal? stopPrice = null, string trigger = null, string advanced = null);

        /// <summary>
        /// Places a buy order for an instrument.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">Instrument name</param>
        /// <param name="amount">It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH</param>
        /// <param name="type">The order type, default: &#x60;\&quot;limit\&quot;&#x60; (optional)</param>
        /// <param name="label">user defined label for the order (maximum 32 characters) (optional)</param>
        /// <param name="price">&lt;p&gt;The order price in base currency (Only for limit and stop_limit orders)&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;usd, the field price should be the option price value in USD.&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;implv, the field price should be a value of implied volatility in percentages. For example,  price&#x3D;100, means implied volatility of 100%&lt;/p&gt; (optional)</param>
        /// <param name="timeInForce">&lt;p&gt;Specifies how long the order remains in effect. Default &#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; &lt;ul&gt; &lt;li&gt;&#x60;\&quot;good_til_cancelled\&quot;&#x60; - unfilled order remains in order book until cancelled&lt;/li&gt; &lt;li&gt;&#x60;\&quot;fill_or_kill\&quot;&#x60; - execute a transaction immediately and completely or not at all&lt;/li&gt; &lt;li&gt;&#x60;\&quot;immediate_or_cancel\&quot;&#x60; - execute a transaction immediately, and any portion of the order that cannot be immediately filled is cancelled&lt;/li&gt; &lt;/ul&gt; (optional, default to good_til_cancelled)</param>
        /// <param name="maxShow">Maximum amount within an order to be shown to other customers, &#x60;0&#x60; for invisible order (optional, default to 1M)</param>
        /// <param name="postOnly">&lt;p&gt;If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.&lt;/p&gt; &lt;p&gt;Only valid in combination with time_in_force&#x3D;&#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; (optional, default to true)</param>
        /// <param name="reduceOnly">If &#x60;true&#x60;, the order is considered reduce-only which is intended to only reduce a current position (optional, default to false)</param>
        /// <param name="stopPrice">Stop price, required for stop limit orders (Only for stop orders) (optional)</param>
        /// <param name="trigger">Defines trigger type, required for &#x60;\&quot;stop_limit\&quot;&#x60; order type (optional)</param>
        /// <param name="advanced">Advanced option order type. (Only for options) (optional)</param>
        /// <returns>Task of ApiResponse (Object)</returns>
        System.Threading.Tasks.Task<ApiResponse<Object>> PrivateBuyGetAsyncWithHttpInfo (string instrumentName, decimal? amount, string type = null, string label = null, decimal? price = null, string timeInForce = null, decimal? maxShow = null, bool? postOnly = null, bool? reduceOnly = null, decimal? stopPrice = null, string trigger = null, string advanced = null);
        /// <summary>
        /// Cancels all orders by currency, optionally filtered by instrument kind and/or order type.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="kind">Instrument kind, if not provided instruments of all kinds are considered (optional)</param>
        /// <param name="type">Order type - limit, stop or all, default - &#x60;all&#x60; (optional)</param>
        /// <returns>Task of Object</returns>
        System.Threading.Tasks.Task<Object> PrivateCancelAllByCurrencyGetAsync (string currency, string kind = null, string type = null);

        /// <summary>
        /// Cancels all orders by currency, optionally filtered by instrument kind and/or order type.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="kind">Instrument kind, if not provided instruments of all kinds are considered (optional)</param>
        /// <param name="type">Order type - limit, stop or all, default - &#x60;all&#x60; (optional)</param>
        /// <returns>Task of ApiResponse (Object)</returns>
        System.Threading.Tasks.Task<ApiResponse<Object>> PrivateCancelAllByCurrencyGetAsyncWithHttpInfo (string currency, string kind = null, string type = null);
        /// <summary>
        /// Cancels all orders by instrument, optionally filtered by order type.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">Instrument name</param>
        /// <param name="type">Order type - limit, stop or all, default - &#x60;all&#x60; (optional)</param>
        /// <returns>Task of Object</returns>
        System.Threading.Tasks.Task<Object> PrivateCancelAllByInstrumentGetAsync (string instrumentName, string type = null);

        /// <summary>
        /// Cancels all orders by instrument, optionally filtered by order type.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">Instrument name</param>
        /// <param name="type">Order type - limit, stop or all, default - &#x60;all&#x60; (optional)</param>
        /// <returns>Task of ApiResponse (Object)</returns>
        System.Threading.Tasks.Task<ApiResponse<Object>> PrivateCancelAllByInstrumentGetAsyncWithHttpInfo (string instrumentName, string type = null);
        /// <summary>
        /// This method cancels all users orders and stop orders within all currencies and instrument kinds.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <returns>Task of Object</returns>
        System.Threading.Tasks.Task<Object> PrivateCancelAllGetAsync ();

        /// <summary>
        /// This method cancels all users orders and stop orders within all currencies and instrument kinds.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <returns>Task of ApiResponse (Object)</returns>
        System.Threading.Tasks.Task<ApiResponse<Object>> PrivateCancelAllGetAsyncWithHttpInfo ();
        /// <summary>
        /// Cancel an order, specified by order id
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="orderId">The order id</param>
        /// <returns>Task of Object</returns>
        System.Threading.Tasks.Task<Object> PrivateCancelGetAsync (string orderId);

        /// <summary>
        /// Cancel an order, specified by order id
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="orderId">The order id</param>
        /// <returns>Task of ApiResponse (Object)</returns>
        System.Threading.Tasks.Task<ApiResponse<Object>> PrivateCancelGetAsyncWithHttpInfo (string orderId);
        /// <summary>
        /// Makes closing position reduce only order .
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">Instrument name</param>
        /// <param name="type">The order type</param>
        /// <param name="price">Optional price for limit order. (optional)</param>
        /// <returns>Task of Object</returns>
        System.Threading.Tasks.Task<Object> PrivateClosePositionGetAsync (string instrumentName, string type, decimal? price = null);

        /// <summary>
        /// Makes closing position reduce only order .
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">Instrument name</param>
        /// <param name="type">The order type</param>
        /// <param name="price">Optional price for limit order. (optional)</param>
        /// <returns>Task of ApiResponse (Object)</returns>
        System.Threading.Tasks.Task<ApiResponse<Object>> PrivateClosePositionGetAsyncWithHttpInfo (string instrumentName, string type, decimal? price = null);
        /// <summary>
        /// Change price, amount and/or other properties of an order.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="orderId">The order id</param>
        /// <param name="amount">It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH</param>
        /// <param name="price">&lt;p&gt;The order price in base currency.&lt;/p&gt; &lt;p&gt;When editing an option order with advanced&#x3D;usd, the field price should be the option price value in USD.&lt;/p&gt; &lt;p&gt;When editing an option order with advanced&#x3D;implv, the field price should be a value of implied volatility in percentages. For example,  price&#x3D;100, means implied volatility of 100%&lt;/p&gt;</param>
        /// <param name="postOnly">&lt;p&gt;If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.&lt;/p&gt; &lt;p&gt;Only valid in combination with time_in_force&#x3D;&#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; (optional, default to true)</param>
        /// <param name="advanced">Advanced option order type. If you have posted an advanced option order, it is necessary to re-supply this parameter when editing it (Only for options) (optional)</param>
        /// <param name="stopPrice">Stop price, required for stop limit orders (Only for stop orders) (optional)</param>
        /// <returns>Task of Object</returns>
        System.Threading.Tasks.Task<Object> PrivateEditGetAsync (string orderId, decimal? amount, decimal? price, bool? postOnly = null, string advanced = null, decimal? stopPrice = null);

        /// <summary>
        /// Change price, amount and/or other properties of an order.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="orderId">The order id</param>
        /// <param name="amount">It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH</param>
        /// <param name="price">&lt;p&gt;The order price in base currency.&lt;/p&gt; &lt;p&gt;When editing an option order with advanced&#x3D;usd, the field price should be the option price value in USD.&lt;/p&gt; &lt;p&gt;When editing an option order with advanced&#x3D;implv, the field price should be a value of implied volatility in percentages. For example,  price&#x3D;100, means implied volatility of 100%&lt;/p&gt;</param>
        /// <param name="postOnly">&lt;p&gt;If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.&lt;/p&gt; &lt;p&gt;Only valid in combination with time_in_force&#x3D;&#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; (optional, default to true)</param>
        /// <param name="advanced">Advanced option order type. If you have posted an advanced option order, it is necessary to re-supply this parameter when editing it (Only for options) (optional)</param>
        /// <param name="stopPrice">Stop price, required for stop limit orders (Only for stop orders) (optional)</param>
        /// <returns>Task of ApiResponse (Object)</returns>
        System.Threading.Tasks.Task<ApiResponse<Object>> PrivateEditGetAsyncWithHttpInfo (string orderId, decimal? amount, decimal? price, bool? postOnly = null, string advanced = null, decimal? stopPrice = null);
        /// <summary>
        /// Get margins for given instrument, amount and price.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">Instrument name</param>
        /// <param name="amount">Amount, integer for future, float for option. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH.</param>
        /// <param name="price">Price</param>
        /// <returns>Task of Object</returns>
        System.Threading.Tasks.Task<Object> PrivateGetMarginsGetAsync (string instrumentName, decimal? amount, decimal? price);

        /// <summary>
        /// Get margins for given instrument, amount and price.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">Instrument name</param>
        /// <param name="amount">Amount, integer for future, float for option. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH.</param>
        /// <param name="price">Price</param>
        /// <returns>Task of ApiResponse (Object)</returns>
        System.Threading.Tasks.Task<ApiResponse<Object>> PrivateGetMarginsGetAsyncWithHttpInfo (string instrumentName, decimal? amount, decimal? price);
        /// <summary>
        /// Retrieves list of user&#39;s open orders.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="kind">Instrument kind, if not provided instruments of all kinds are considered (optional)</param>
        /// <param name="type">Order type, default - &#x60;all&#x60; (optional)</param>
        /// <returns>Task of Object</returns>
        System.Threading.Tasks.Task<Object> PrivateGetOpenOrdersByCurrencyGetAsync (string currency, string kind = null, string type = null);

        /// <summary>
        /// Retrieves list of user&#39;s open orders.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="kind">Instrument kind, if not provided instruments of all kinds are considered (optional)</param>
        /// <param name="type">Order type, default - &#x60;all&#x60; (optional)</param>
        /// <returns>Task of ApiResponse (Object)</returns>
        System.Threading.Tasks.Task<ApiResponse<Object>> PrivateGetOpenOrdersByCurrencyGetAsyncWithHttpInfo (string currency, string kind = null, string type = null);
        /// <summary>
        /// Retrieves list of user&#39;s open orders within given Instrument.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">Instrument name</param>
        /// <param name="type">Order type, default - &#x60;all&#x60; (optional)</param>
        /// <returns>Task of Object</returns>
        System.Threading.Tasks.Task<Object> PrivateGetOpenOrdersByInstrumentGetAsync (string instrumentName, string type = null);

        /// <summary>
        /// Retrieves list of user&#39;s open orders within given Instrument.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">Instrument name</param>
        /// <param name="type">Order type, default - &#x60;all&#x60; (optional)</param>
        /// <returns>Task of ApiResponse (Object)</returns>
        System.Threading.Tasks.Task<ApiResponse<Object>> PrivateGetOpenOrdersByInstrumentGetAsyncWithHttpInfo (string instrumentName, string type = null);
        /// <summary>
        /// Retrieves history of orders that have been partially or fully filled.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="kind">Instrument kind, if not provided instruments of all kinds are considered (optional)</param>
        /// <param name="count">Number of requested items, default - &#x60;20&#x60; (optional)</param>
        /// <param name="offset">The offset for pagination, default - &#x60;0&#x60; (optional)</param>
        /// <param name="includeOld">Include in result orders older than 2 days, default - &#x60;false&#x60; (optional)</param>
        /// <param name="includeUnfilled">Include in result fully unfilled closed orders, default - &#x60;false&#x60; (optional)</param>
        /// <returns>Task of Object</returns>
        System.Threading.Tasks.Task<Object> PrivateGetOrderHistoryByCurrencyGetAsync (string currency, string kind = null, int? count = null, int? offset = null, bool? includeOld = null, bool? includeUnfilled = null);

        /// <summary>
        /// Retrieves history of orders that have been partially or fully filled.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="kind">Instrument kind, if not provided instruments of all kinds are considered (optional)</param>
        /// <param name="count">Number of requested items, default - &#x60;20&#x60; (optional)</param>
        /// <param name="offset">The offset for pagination, default - &#x60;0&#x60; (optional)</param>
        /// <param name="includeOld">Include in result orders older than 2 days, default - &#x60;false&#x60; (optional)</param>
        /// <param name="includeUnfilled">Include in result fully unfilled closed orders, default - &#x60;false&#x60; (optional)</param>
        /// <returns>Task of ApiResponse (Object)</returns>
        System.Threading.Tasks.Task<ApiResponse<Object>> PrivateGetOrderHistoryByCurrencyGetAsyncWithHttpInfo (string currency, string kind = null, int? count = null, int? offset = null, bool? includeOld = null, bool? includeUnfilled = null);
        /// <summary>
        /// Retrieves history of orders that have been partially or fully filled.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">Instrument name</param>
        /// <param name="count">Number of requested items, default - &#x60;20&#x60; (optional)</param>
        /// <param name="offset">The offset for pagination, default - &#x60;0&#x60; (optional)</param>
        /// <param name="includeOld">Include in result orders older than 2 days, default - &#x60;false&#x60; (optional)</param>
        /// <param name="includeUnfilled">Include in result fully unfilled closed orders, default - &#x60;false&#x60; (optional)</param>
        /// <returns>Task of Object</returns>
        System.Threading.Tasks.Task<Object> PrivateGetOrderHistoryByInstrumentGetAsync (string instrumentName, int? count = null, int? offset = null, bool? includeOld = null, bool? includeUnfilled = null);

        /// <summary>
        /// Retrieves history of orders that have been partially or fully filled.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">Instrument name</param>
        /// <param name="count">Number of requested items, default - &#x60;20&#x60; (optional)</param>
        /// <param name="offset">The offset for pagination, default - &#x60;0&#x60; (optional)</param>
        /// <param name="includeOld">Include in result orders older than 2 days, default - &#x60;false&#x60; (optional)</param>
        /// <param name="includeUnfilled">Include in result fully unfilled closed orders, default - &#x60;false&#x60; (optional)</param>
        /// <returns>Task of ApiResponse (Object)</returns>
        System.Threading.Tasks.Task<ApiResponse<Object>> PrivateGetOrderHistoryByInstrumentGetAsyncWithHttpInfo (string instrumentName, int? count = null, int? offset = null, bool? includeOld = null, bool? includeUnfilled = null);
        /// <summary>
        /// Retrieves initial margins of given orders
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="ids">Ids of orders</param>
        /// <returns>Task of Object</returns>
        System.Threading.Tasks.Task<Object> PrivateGetOrderMarginByIdsGetAsync (List<string> ids);

        /// <summary>
        /// Retrieves initial margins of given orders
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="ids">Ids of orders</param>
        /// <returns>Task of ApiResponse (Object)</returns>
        System.Threading.Tasks.Task<ApiResponse<Object>> PrivateGetOrderMarginByIdsGetAsyncWithHttpInfo (List<string> ids);
        /// <summary>
        /// Retrieve the current state of an order.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="orderId">The order id</param>
        /// <returns>Task of Object</returns>
        System.Threading.Tasks.Task<Object> PrivateGetOrderStateGetAsync (string orderId);

        /// <summary>
        /// Retrieve the current state of an order.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="orderId">The order id</param>
        /// <returns>Task of ApiResponse (Object)</returns>
        System.Threading.Tasks.Task<ApiResponse<Object>> PrivateGetOrderStateGetAsyncWithHttpInfo (string orderId);
        /// <summary>
        /// Retrieves settlement, delivery and bankruptcy events that have affected your account.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="type">Settlement type (optional)</param>
        /// <param name="count">Number of requested items, default - &#x60;20&#x60; (optional)</param>
        /// <returns>Task of Object</returns>
        System.Threading.Tasks.Task<Object> PrivateGetSettlementHistoryByCurrencyGetAsync (string currency, string type = null, int? count = null);

        /// <summary>
        /// Retrieves settlement, delivery and bankruptcy events that have affected your account.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="type">Settlement type (optional)</param>
        /// <param name="count">Number of requested items, default - &#x60;20&#x60; (optional)</param>
        /// <returns>Task of ApiResponse (Object)</returns>
        System.Threading.Tasks.Task<ApiResponse<Object>> PrivateGetSettlementHistoryByCurrencyGetAsyncWithHttpInfo (string currency, string type = null, int? count = null);
        /// <summary>
        /// Retrieves public settlement, delivery and bankruptcy events filtered by instrument name
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">Instrument name</param>
        /// <param name="type">Settlement type (optional)</param>
        /// <param name="count">Number of requested items, default - &#x60;20&#x60; (optional)</param>
        /// <returns>Task of Object</returns>
        System.Threading.Tasks.Task<Object> PrivateGetSettlementHistoryByInstrumentGetAsync (string instrumentName, string type = null, int? count = null);

        /// <summary>
        /// Retrieves public settlement, delivery and bankruptcy events filtered by instrument name
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">Instrument name</param>
        /// <param name="type">Settlement type (optional)</param>
        /// <param name="count">Number of requested items, default - &#x60;20&#x60; (optional)</param>
        /// <returns>Task of ApiResponse (Object)</returns>
        System.Threading.Tasks.Task<ApiResponse<Object>> PrivateGetSettlementHistoryByInstrumentGetAsyncWithHttpInfo (string instrumentName, string type = null, int? count = null);
        /// <summary>
        /// Retrieve the latest user trades that have occurred for instruments in a specific currency symbol and within given time range.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="startTimestamp">The earliest timestamp to return result for</param>
        /// <param name="endTimestamp">The most recent timestamp to return result for</param>
        /// <param name="kind">Instrument kind, if not provided instruments of all kinds are considered (optional)</param>
        /// <param name="count">Number of requested items, default - &#x60;10&#x60; (optional)</param>
        /// <param name="includeOld">Include trades older than 7 days, default - &#x60;false&#x60; (optional)</param>
        /// <param name="sorting">Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) (optional)</param>
        /// <returns>Task of Object</returns>
        System.Threading.Tasks.Task<Object> PrivateGetUserTradesByCurrencyAndTimeGetAsync (string currency, int? startTimestamp, int? endTimestamp, string kind = null, int? count = null, bool? includeOld = null, string sorting = null);

        /// <summary>
        /// Retrieve the latest user trades that have occurred for instruments in a specific currency symbol and within given time range.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="startTimestamp">The earliest timestamp to return result for</param>
        /// <param name="endTimestamp">The most recent timestamp to return result for</param>
        /// <param name="kind">Instrument kind, if not provided instruments of all kinds are considered (optional)</param>
        /// <param name="count">Number of requested items, default - &#x60;10&#x60; (optional)</param>
        /// <param name="includeOld">Include trades older than 7 days, default - &#x60;false&#x60; (optional)</param>
        /// <param name="sorting">Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) (optional)</param>
        /// <returns>Task of ApiResponse (Object)</returns>
        System.Threading.Tasks.Task<ApiResponse<Object>> PrivateGetUserTradesByCurrencyAndTimeGetAsyncWithHttpInfo (string currency, int? startTimestamp, int? endTimestamp, string kind = null, int? count = null, bool? includeOld = null, string sorting = null);
        /// <summary>
        /// Retrieve the latest user trades that have occurred for instruments in a specific currency symbol.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="kind">Instrument kind, if not provided instruments of all kinds are considered (optional)</param>
        /// <param name="startId">The ID number of the first trade to be returned (optional)</param>
        /// <param name="endId">The ID number of the last trade to be returned (optional)</param>
        /// <param name="count">Number of requested items, default - &#x60;10&#x60; (optional)</param>
        /// <param name="includeOld">Include trades older than 7 days, default - &#x60;false&#x60; (optional)</param>
        /// <param name="sorting">Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) (optional)</param>
        /// <returns>Task of Object</returns>
        System.Threading.Tasks.Task<Object> PrivateGetUserTradesByCurrencyGetAsync (string currency, string kind = null, string startId = null, string endId = null, int? count = null, bool? includeOld = null, string sorting = null);

        /// <summary>
        /// Retrieve the latest user trades that have occurred for instruments in a specific currency symbol.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="kind">Instrument kind, if not provided instruments of all kinds are considered (optional)</param>
        /// <param name="startId">The ID number of the first trade to be returned (optional)</param>
        /// <param name="endId">The ID number of the last trade to be returned (optional)</param>
        /// <param name="count">Number of requested items, default - &#x60;10&#x60; (optional)</param>
        /// <param name="includeOld">Include trades older than 7 days, default - &#x60;false&#x60; (optional)</param>
        /// <param name="sorting">Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) (optional)</param>
        /// <returns>Task of ApiResponse (Object)</returns>
        System.Threading.Tasks.Task<ApiResponse<Object>> PrivateGetUserTradesByCurrencyGetAsyncWithHttpInfo (string currency, string kind = null, string startId = null, string endId = null, int? count = null, bool? includeOld = null, string sorting = null);
        /// <summary>
        /// Retrieve the latest user trades that have occurred for a specific instrument and within given time range.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">Instrument name</param>
        /// <param name="startTimestamp">The earliest timestamp to return result for</param>
        /// <param name="endTimestamp">The most recent timestamp to return result for</param>
        /// <param name="count">Number of requested items, default - &#x60;10&#x60; (optional)</param>
        /// <param name="includeOld">Include trades older than 7 days, default - &#x60;false&#x60; (optional)</param>
        /// <param name="sorting">Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) (optional)</param>
        /// <returns>Task of Object</returns>
        System.Threading.Tasks.Task<Object> PrivateGetUserTradesByInstrumentAndTimeGetAsync (string instrumentName, int? startTimestamp, int? endTimestamp, int? count = null, bool? includeOld = null, string sorting = null);

        /// <summary>
        /// Retrieve the latest user trades that have occurred for a specific instrument and within given time range.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">Instrument name</param>
        /// <param name="startTimestamp">The earliest timestamp to return result for</param>
        /// <param name="endTimestamp">The most recent timestamp to return result for</param>
        /// <param name="count">Number of requested items, default - &#x60;10&#x60; (optional)</param>
        /// <param name="includeOld">Include trades older than 7 days, default - &#x60;false&#x60; (optional)</param>
        /// <param name="sorting">Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) (optional)</param>
        /// <returns>Task of ApiResponse (Object)</returns>
        System.Threading.Tasks.Task<ApiResponse<Object>> PrivateGetUserTradesByInstrumentAndTimeGetAsyncWithHttpInfo (string instrumentName, int? startTimestamp, int? endTimestamp, int? count = null, bool? includeOld = null, string sorting = null);
        /// <summary>
        /// Retrieve the latest user trades that have occurred for a specific instrument.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">Instrument name</param>
        /// <param name="startSeq">The sequence number of the first trade to be returned (optional)</param>
        /// <param name="endSeq">The sequence number of the last trade to be returned (optional)</param>
        /// <param name="count">Number of requested items, default - &#x60;10&#x60; (optional)</param>
        /// <param name="includeOld">Include trades older than 7 days, default - &#x60;false&#x60; (optional)</param>
        /// <param name="sorting">Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) (optional)</param>
        /// <returns>Task of Object</returns>
        System.Threading.Tasks.Task<Object> PrivateGetUserTradesByInstrumentGetAsync (string instrumentName, int? startSeq = null, int? endSeq = null, int? count = null, bool? includeOld = null, string sorting = null);

        /// <summary>
        /// Retrieve the latest user trades that have occurred for a specific instrument.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">Instrument name</param>
        /// <param name="startSeq">The sequence number of the first trade to be returned (optional)</param>
        /// <param name="endSeq">The sequence number of the last trade to be returned (optional)</param>
        /// <param name="count">Number of requested items, default - &#x60;10&#x60; (optional)</param>
        /// <param name="includeOld">Include trades older than 7 days, default - &#x60;false&#x60; (optional)</param>
        /// <param name="sorting">Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) (optional)</param>
        /// <returns>Task of ApiResponse (Object)</returns>
        System.Threading.Tasks.Task<ApiResponse<Object>> PrivateGetUserTradesByInstrumentGetAsyncWithHttpInfo (string instrumentName, int? startSeq = null, int? endSeq = null, int? count = null, bool? includeOld = null, string sorting = null);
        /// <summary>
        /// Retrieve the list of user trades that was created for given order
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="orderId">The order id</param>
        /// <param name="sorting">Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) (optional)</param>
        /// <returns>Task of Object</returns>
        System.Threading.Tasks.Task<Object> PrivateGetUserTradesByOrderGetAsync (string orderId, string sorting = null);

        /// <summary>
        /// Retrieve the list of user trades that was created for given order
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="orderId">The order id</param>
        /// <param name="sorting">Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) (optional)</param>
        /// <returns>Task of ApiResponse (Object)</returns>
        System.Threading.Tasks.Task<ApiResponse<Object>> PrivateGetUserTradesByOrderGetAsyncWithHttpInfo (string orderId, string sorting = null);
        /// <summary>
        /// Places a sell order for an instrument.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">Instrument name</param>
        /// <param name="amount">It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH</param>
        /// <param name="type">The order type, default: &#x60;\&quot;limit\&quot;&#x60; (optional)</param>
        /// <param name="label">user defined label for the order (maximum 32 characters) (optional)</param>
        /// <param name="price">&lt;p&gt;The order price in base currency (Only for limit and stop_limit orders)&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;usd, the field price should be the option price value in USD.&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;implv, the field price should be a value of implied volatility in percentages. For example,  price&#x3D;100, means implied volatility of 100%&lt;/p&gt; (optional)</param>
        /// <param name="timeInForce">&lt;p&gt;Specifies how long the order remains in effect. Default &#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; &lt;ul&gt; &lt;li&gt;&#x60;\&quot;good_til_cancelled\&quot;&#x60; - unfilled order remains in order book until cancelled&lt;/li&gt; &lt;li&gt;&#x60;\&quot;fill_or_kill\&quot;&#x60; - execute a transaction immediately and completely or not at all&lt;/li&gt; &lt;li&gt;&#x60;\&quot;immediate_or_cancel\&quot;&#x60; - execute a transaction immediately, and any portion of the order that cannot be immediately filled is cancelled&lt;/li&gt; &lt;/ul&gt; (optional, default to good_til_cancelled)</param>
        /// <param name="maxShow">Maximum amount within an order to be shown to other customers, &#x60;0&#x60; for invisible order (optional, default to 1M)</param>
        /// <param name="postOnly">&lt;p&gt;If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.&lt;/p&gt; &lt;p&gt;Only valid in combination with time_in_force&#x3D;&#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; (optional, default to true)</param>
        /// <param name="reduceOnly">If &#x60;true&#x60;, the order is considered reduce-only which is intended to only reduce a current position (optional, default to false)</param>
        /// <param name="stopPrice">Stop price, required for stop limit orders (Only for stop orders) (optional)</param>
        /// <param name="trigger">Defines trigger type, required for &#x60;\&quot;stop_limit\&quot;&#x60; order type (optional)</param>
        /// <param name="advanced">Advanced option order type. (Only for options) (optional)</param>
        /// <returns>Task of Object</returns>
        System.Threading.Tasks.Task<Object> PrivateSellGetAsync (string instrumentName, decimal? amount, string type = null, string label = null, decimal? price = null, string timeInForce = null, decimal? maxShow = null, bool? postOnly = null, bool? reduceOnly = null, decimal? stopPrice = null, string trigger = null, string advanced = null);

        /// <summary>
        /// Places a sell order for an instrument.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">Instrument name</param>
        /// <param name="amount">It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH</param>
        /// <param name="type">The order type, default: &#x60;\&quot;limit\&quot;&#x60; (optional)</param>
        /// <param name="label">user defined label for the order (maximum 32 characters) (optional)</param>
        /// <param name="price">&lt;p&gt;The order price in base currency (Only for limit and stop_limit orders)&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;usd, the field price should be the option price value in USD.&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;implv, the field price should be a value of implied volatility in percentages. For example,  price&#x3D;100, means implied volatility of 100%&lt;/p&gt; (optional)</param>
        /// <param name="timeInForce">&lt;p&gt;Specifies how long the order remains in effect. Default &#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; &lt;ul&gt; &lt;li&gt;&#x60;\&quot;good_til_cancelled\&quot;&#x60; - unfilled order remains in order book until cancelled&lt;/li&gt; &lt;li&gt;&#x60;\&quot;fill_or_kill\&quot;&#x60; - execute a transaction immediately and completely or not at all&lt;/li&gt; &lt;li&gt;&#x60;\&quot;immediate_or_cancel\&quot;&#x60; - execute a transaction immediately, and any portion of the order that cannot be immediately filled is cancelled&lt;/li&gt; &lt;/ul&gt; (optional, default to good_til_cancelled)</param>
        /// <param name="maxShow">Maximum amount within an order to be shown to other customers, &#x60;0&#x60; for invisible order (optional, default to 1M)</param>
        /// <param name="postOnly">&lt;p&gt;If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.&lt;/p&gt; &lt;p&gt;Only valid in combination with time_in_force&#x3D;&#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; (optional, default to true)</param>
        /// <param name="reduceOnly">If &#x60;true&#x60;, the order is considered reduce-only which is intended to only reduce a current position (optional, default to false)</param>
        /// <param name="stopPrice">Stop price, required for stop limit orders (Only for stop orders) (optional)</param>
        /// <param name="trigger">Defines trigger type, required for &#x60;\&quot;stop_limit\&quot;&#x60; order type (optional)</param>
        /// <param name="advanced">Advanced option order type. (Only for options) (optional)</param>
        /// <returns>Task of ApiResponse (Object)</returns>
        System.Threading.Tasks.Task<ApiResponse<Object>> PrivateSellGetAsyncWithHttpInfo (string instrumentName, decimal? amount, string type = null, string label = null, decimal? price = null, string timeInForce = null, decimal? maxShow = null, bool? postOnly = null, bool? reduceOnly = null, decimal? stopPrice = null, string trigger = null, string advanced = null);
        #endregion Asynchronous Operations
    }

    /// <summary>
    /// Represents a collection of functions to interact with the API endpoints
    /// </summary>
    public partial class TradingApi : ITradingApi
    {
        private Org.OpenAPITools.Client.ExceptionFactory _exceptionFactory = (name, response) => null;

        /// <summary>
        /// Initializes a new instance of the <see cref="TradingApi"/> class.
        /// </summary>
        /// <returns></returns>
        public TradingApi(String basePath)
        {
            this.Configuration = new Org.OpenAPITools.Client.Configuration { BasePath = basePath };

            ExceptionFactory = Org.OpenAPITools.Client.Configuration.DefaultExceptionFactory;
        }

        /// <summary>
        /// Initializes a new instance of the <see cref="TradingApi"/> class
        /// </summary>
        /// <returns></returns>
        public TradingApi()
        {
            this.Configuration = Org.OpenAPITools.Client.Configuration.Default;

            ExceptionFactory = Org.OpenAPITools.Client.Configuration.DefaultExceptionFactory;
        }

        /// <summary>
        /// Initializes a new instance of the <see cref="TradingApi"/> class
        /// using Configuration object
        /// </summary>
        /// <param name="configuration">An instance of Configuration</param>
        /// <returns></returns>
        public TradingApi(Org.OpenAPITools.Client.Configuration configuration = null)
        {
            if (configuration == null) // use the default one in Configuration
                this.Configuration = Org.OpenAPITools.Client.Configuration.Default;
            else
                this.Configuration = configuration;

            ExceptionFactory = Org.OpenAPITools.Client.Configuration.DefaultExceptionFactory;
        }

        /// <summary>
        /// Gets the base path of the API client.
        /// </summary>
        /// <value>The base path</value>
        public String GetBasePath()
        {
            return this.Configuration.ApiClient.RestClient.BaseUrl.ToString();
        }

        /// <summary>
        /// Sets the base path of the API client.
        /// </summary>
        /// <value>The base path</value>
        [Obsolete("SetBasePath is deprecated, please do 'Configuration.ApiClient = new ApiClient(\"http://new-path\")' instead.")]
        public void SetBasePath(String basePath)
        {
            // do nothing
        }

        /// <summary>
        /// Gets or sets the configuration object
        /// </summary>
        /// <value>An instance of the Configuration</value>
        public Org.OpenAPITools.Client.Configuration Configuration {get; set;}

        /// <summary>
        /// Provides a factory method hook for the creation of exceptions.
        /// </summary>
        public Org.OpenAPITools.Client.ExceptionFactory ExceptionFactory
        {
            get
            {
                if (_exceptionFactory != null && _exceptionFactory.GetInvocationList().Length > 1)
                {
                    throw new InvalidOperationException("Multicast delegate for ExceptionFactory is unsupported.");
                }
                return _exceptionFactory;
            }
            set { _exceptionFactory = value; }
        }

        /// <summary>
        /// Gets the default header.
        /// </summary>
        /// <returns>Dictionary of HTTP header</returns>
        [Obsolete("DefaultHeader is deprecated, please use Configuration.DefaultHeader instead.")]
        public IDictionary<String, String> DefaultHeader()
        {
            return new ReadOnlyDictionary<string, string>(this.Configuration.DefaultHeader);
        }

        /// <summary>
        /// Add default header.
        /// </summary>
        /// <param name="key">Header field name.</param>
        /// <param name="value">Header field value.</param>
        /// <returns></returns>
        [Obsolete("AddDefaultHeader is deprecated, please use Configuration.AddDefaultHeader instead.")]
        public void AddDefaultHeader(string key, string value)
        {
            this.Configuration.AddDefaultHeader(key, value);
        }

        /// <summary>
        /// Places a buy order for an instrument. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">Instrument name</param>
        /// <param name="amount">It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH</param>
        /// <param name="type">The order type, default: &#x60;\&quot;limit\&quot;&#x60; (optional)</param>
        /// <param name="label">user defined label for the order (maximum 32 characters) (optional)</param>
        /// <param name="price">&lt;p&gt;The order price in base currency (Only for limit and stop_limit orders)&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;usd, the field price should be the option price value in USD.&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;implv, the field price should be a value of implied volatility in percentages. For example,  price&#x3D;100, means implied volatility of 100%&lt;/p&gt; (optional)</param>
        /// <param name="timeInForce">&lt;p&gt;Specifies how long the order remains in effect. Default &#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; &lt;ul&gt; &lt;li&gt;&#x60;\&quot;good_til_cancelled\&quot;&#x60; - unfilled order remains in order book until cancelled&lt;/li&gt; &lt;li&gt;&#x60;\&quot;fill_or_kill\&quot;&#x60; - execute a transaction immediately and completely or not at all&lt;/li&gt; &lt;li&gt;&#x60;\&quot;immediate_or_cancel\&quot;&#x60; - execute a transaction immediately, and any portion of the order that cannot be immediately filled is cancelled&lt;/li&gt; &lt;/ul&gt; (optional, default to good_til_cancelled)</param>
        /// <param name="maxShow">Maximum amount within an order to be shown to other customers, &#x60;0&#x60; for invisible order (optional, default to 1M)</param>
        /// <param name="postOnly">&lt;p&gt;If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.&lt;/p&gt; &lt;p&gt;Only valid in combination with time_in_force&#x3D;&#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; (optional, default to true)</param>
        /// <param name="reduceOnly">If &#x60;true&#x60;, the order is considered reduce-only which is intended to only reduce a current position (optional, default to false)</param>
        /// <param name="stopPrice">Stop price, required for stop limit orders (Only for stop orders) (optional)</param>
        /// <param name="trigger">Defines trigger type, required for &#x60;\&quot;stop_limit\&quot;&#x60; order type (optional)</param>
        /// <param name="advanced">Advanced option order type. (Only for options) (optional)</param>
        /// <returns>Object</returns>
        public Object PrivateBuyGet (string instrumentName, decimal? amount, string type = null, string label = null, decimal? price = null, string timeInForce = null, decimal? maxShow = null, bool? postOnly = null, bool? reduceOnly = null, decimal? stopPrice = null, string trigger = null, string advanced = null)
        {
             ApiResponse<Object> localVarResponse = PrivateBuyGetWithHttpInfo(instrumentName, amount, type, label, price, timeInForce, maxShow, postOnly, reduceOnly, stopPrice, trigger, advanced);
             return localVarResponse.Data;
        }

        /// <summary>
        /// Places a buy order for an instrument. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">Instrument name</param>
        /// <param name="amount">It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH</param>
        /// <param name="type">The order type, default: &#x60;\&quot;limit\&quot;&#x60; (optional)</param>
        /// <param name="label">user defined label for the order (maximum 32 characters) (optional)</param>
        /// <param name="price">&lt;p&gt;The order price in base currency (Only for limit and stop_limit orders)&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;usd, the field price should be the option price value in USD.&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;implv, the field price should be a value of implied volatility in percentages. For example,  price&#x3D;100, means implied volatility of 100%&lt;/p&gt; (optional)</param>
        /// <param name="timeInForce">&lt;p&gt;Specifies how long the order remains in effect. Default &#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; &lt;ul&gt; &lt;li&gt;&#x60;\&quot;good_til_cancelled\&quot;&#x60; - unfilled order remains in order book until cancelled&lt;/li&gt; &lt;li&gt;&#x60;\&quot;fill_or_kill\&quot;&#x60; - execute a transaction immediately and completely or not at all&lt;/li&gt; &lt;li&gt;&#x60;\&quot;immediate_or_cancel\&quot;&#x60; - execute a transaction immediately, and any portion of the order that cannot be immediately filled is cancelled&lt;/li&gt; &lt;/ul&gt; (optional, default to good_til_cancelled)</param>
        /// <param name="maxShow">Maximum amount within an order to be shown to other customers, &#x60;0&#x60; for invisible order (optional, default to 1M)</param>
        /// <param name="postOnly">&lt;p&gt;If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.&lt;/p&gt; &lt;p&gt;Only valid in combination with time_in_force&#x3D;&#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; (optional, default to true)</param>
        /// <param name="reduceOnly">If &#x60;true&#x60;, the order is considered reduce-only which is intended to only reduce a current position (optional, default to false)</param>
        /// <param name="stopPrice">Stop price, required for stop limit orders (Only for stop orders) (optional)</param>
        /// <param name="trigger">Defines trigger type, required for &#x60;\&quot;stop_limit\&quot;&#x60; order type (optional)</param>
        /// <param name="advanced">Advanced option order type. (Only for options) (optional)</param>
        /// <returns>ApiResponse of Object</returns>
        public ApiResponse< Object > PrivateBuyGetWithHttpInfo (string instrumentName, decimal? amount, string type = null, string label = null, decimal? price = null, string timeInForce = null, decimal? maxShow = null, bool? postOnly = null, bool? reduceOnly = null, decimal? stopPrice = null, string trigger = null, string advanced = null)
        {
            // verify the required parameter 'instrumentName' is set
            if (instrumentName == null)
                throw new ApiException(400, "Missing required parameter 'instrumentName' when calling TradingApi->PrivateBuyGet");
            // verify the required parameter 'amount' is set
            if (amount == null)
                throw new ApiException(400, "Missing required parameter 'amount' when calling TradingApi->PrivateBuyGet");

            var localVarPath = "/private/buy";
            var localVarPathParams = new Dictionary<String, String>();
            var localVarQueryParams = new List<KeyValuePair<String, String>>();
            var localVarHeaderParams = new Dictionary<String, String>(this.Configuration.DefaultHeader);
            var localVarFormParams = new Dictionary<String, String>();
            var localVarFileParams = new Dictionary<String, FileParameter>();
            Object localVarPostBody = null;

            // to determine the Content-Type header
            String[] localVarHttpContentTypes = new String[] {
            };
            String localVarHttpContentType = this.Configuration.ApiClient.SelectHeaderContentType(localVarHttpContentTypes);

            // to determine the Accept header
            String[] localVarHttpHeaderAccepts = new String[] {
                "application/json"
            };
            String localVarHttpHeaderAccept = this.Configuration.ApiClient.SelectHeaderAccept(localVarHttpHeaderAccepts);
            if (localVarHttpHeaderAccept != null)
                localVarHeaderParams.Add("Accept", localVarHttpHeaderAccept);

            if (instrumentName != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "instrument_name", instrumentName)); // query parameter
            if (amount != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "amount", amount)); // query parameter
            if (type != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "type", type)); // query parameter
            if (label != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "label", label)); // query parameter
            if (price != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "price", price)); // query parameter
            if (timeInForce != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "time_in_force", timeInForce)); // query parameter
            if (maxShow != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "max_show", maxShow)); // query parameter
            if (postOnly != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "post_only", postOnly)); // query parameter
            if (reduceOnly != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "reduce_only", reduceOnly)); // query parameter
            if (stopPrice != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "stop_price", stopPrice)); // query parameter
            if (trigger != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "trigger", trigger)); // query parameter
            if (advanced != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "advanced", advanced)); // query parameter

            // authentication (bearerAuth) required
            // http basic authentication required
            if (!String.IsNullOrEmpty(this.Configuration.Username) || !String.IsNullOrEmpty(this.Configuration.Password))
            {
                localVarHeaderParams["Authorization"] = "Basic " + ApiClient.Base64Encode(this.Configuration.Username + ":" + this.Configuration.Password);
            }

            // make the HTTP request
            IRestResponse localVarResponse = (IRestResponse) this.Configuration.ApiClient.CallApi(localVarPath,
                Method.GET, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarFileParams,
                localVarPathParams, localVarHttpContentType);

            int localVarStatusCode = (int) localVarResponse.StatusCode;

            if (ExceptionFactory != null)
            {
                Exception exception = ExceptionFactory("PrivateBuyGet", localVarResponse);
                if (exception != null) throw exception;
            }

            return new ApiResponse<Object>(localVarStatusCode,
                localVarResponse.Headers.ToDictionary(x => x.Name, x => string.Join(",", x.Value)),
                (Object) this.Configuration.ApiClient.Deserialize(localVarResponse, typeof(Object)));
        }

        /// <summary>
        /// Places a buy order for an instrument. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">Instrument name</param>
        /// <param name="amount">It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH</param>
        /// <param name="type">The order type, default: &#x60;\&quot;limit\&quot;&#x60; (optional)</param>
        /// <param name="label">user defined label for the order (maximum 32 characters) (optional)</param>
        /// <param name="price">&lt;p&gt;The order price in base currency (Only for limit and stop_limit orders)&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;usd, the field price should be the option price value in USD.&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;implv, the field price should be a value of implied volatility in percentages. For example,  price&#x3D;100, means implied volatility of 100%&lt;/p&gt; (optional)</param>
        /// <param name="timeInForce">&lt;p&gt;Specifies how long the order remains in effect. Default &#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; &lt;ul&gt; &lt;li&gt;&#x60;\&quot;good_til_cancelled\&quot;&#x60; - unfilled order remains in order book until cancelled&lt;/li&gt; &lt;li&gt;&#x60;\&quot;fill_or_kill\&quot;&#x60; - execute a transaction immediately and completely or not at all&lt;/li&gt; &lt;li&gt;&#x60;\&quot;immediate_or_cancel\&quot;&#x60; - execute a transaction immediately, and any portion of the order that cannot be immediately filled is cancelled&lt;/li&gt; &lt;/ul&gt; (optional, default to good_til_cancelled)</param>
        /// <param name="maxShow">Maximum amount within an order to be shown to other customers, &#x60;0&#x60; for invisible order (optional, default to 1M)</param>
        /// <param name="postOnly">&lt;p&gt;If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.&lt;/p&gt; &lt;p&gt;Only valid in combination with time_in_force&#x3D;&#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; (optional, default to true)</param>
        /// <param name="reduceOnly">If &#x60;true&#x60;, the order is considered reduce-only which is intended to only reduce a current position (optional, default to false)</param>
        /// <param name="stopPrice">Stop price, required for stop limit orders (Only for stop orders) (optional)</param>
        /// <param name="trigger">Defines trigger type, required for &#x60;\&quot;stop_limit\&quot;&#x60; order type (optional)</param>
        /// <param name="advanced">Advanced option order type. (Only for options) (optional)</param>
        /// <returns>Task of Object</returns>
        public async System.Threading.Tasks.Task<Object> PrivateBuyGetAsync (string instrumentName, decimal? amount, string type = null, string label = null, decimal? price = null, string timeInForce = null, decimal? maxShow = null, bool? postOnly = null, bool? reduceOnly = null, decimal? stopPrice = null, string trigger = null, string advanced = null)
        {
             ApiResponse<Object> localVarResponse = await PrivateBuyGetAsyncWithHttpInfo(instrumentName, amount, type, label, price, timeInForce, maxShow, postOnly, reduceOnly, stopPrice, trigger, advanced);
             return localVarResponse.Data;

        }

        /// <summary>
        /// Places a buy order for an instrument. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">Instrument name</param>
        /// <param name="amount">It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH</param>
        /// <param name="type">The order type, default: &#x60;\&quot;limit\&quot;&#x60; (optional)</param>
        /// <param name="label">user defined label for the order (maximum 32 characters) (optional)</param>
        /// <param name="price">&lt;p&gt;The order price in base currency (Only for limit and stop_limit orders)&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;usd, the field price should be the option price value in USD.&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;implv, the field price should be a value of implied volatility in percentages. For example,  price&#x3D;100, means implied volatility of 100%&lt;/p&gt; (optional)</param>
        /// <param name="timeInForce">&lt;p&gt;Specifies how long the order remains in effect. Default &#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; &lt;ul&gt; &lt;li&gt;&#x60;\&quot;good_til_cancelled\&quot;&#x60; - unfilled order remains in order book until cancelled&lt;/li&gt; &lt;li&gt;&#x60;\&quot;fill_or_kill\&quot;&#x60; - execute a transaction immediately and completely or not at all&lt;/li&gt; &lt;li&gt;&#x60;\&quot;immediate_or_cancel\&quot;&#x60; - execute a transaction immediately, and any portion of the order that cannot be immediately filled is cancelled&lt;/li&gt; &lt;/ul&gt; (optional, default to good_til_cancelled)</param>
        /// <param name="maxShow">Maximum amount within an order to be shown to other customers, &#x60;0&#x60; for invisible order (optional, default to 1M)</param>
        /// <param name="postOnly">&lt;p&gt;If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.&lt;/p&gt; &lt;p&gt;Only valid in combination with time_in_force&#x3D;&#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; (optional, default to true)</param>
        /// <param name="reduceOnly">If &#x60;true&#x60;, the order is considered reduce-only which is intended to only reduce a current position (optional, default to false)</param>
        /// <param name="stopPrice">Stop price, required for stop limit orders (Only for stop orders) (optional)</param>
        /// <param name="trigger">Defines trigger type, required for &#x60;\&quot;stop_limit\&quot;&#x60; order type (optional)</param>
        /// <param name="advanced">Advanced option order type. (Only for options) (optional)</param>
        /// <returns>Task of ApiResponse (Object)</returns>
        public async System.Threading.Tasks.Task<ApiResponse<Object>> PrivateBuyGetAsyncWithHttpInfo (string instrumentName, decimal? amount, string type = null, string label = null, decimal? price = null, string timeInForce = null, decimal? maxShow = null, bool? postOnly = null, bool? reduceOnly = null, decimal? stopPrice = null, string trigger = null, string advanced = null)
        {
            // verify the required parameter 'instrumentName' is set
            if (instrumentName == null)
                throw new ApiException(400, "Missing required parameter 'instrumentName' when calling TradingApi->PrivateBuyGet");
            // verify the required parameter 'amount' is set
            if (amount == null)
                throw new ApiException(400, "Missing required parameter 'amount' when calling TradingApi->PrivateBuyGet");

            var localVarPath = "/private/buy";
            var localVarPathParams = new Dictionary<String, String>();
            var localVarQueryParams = new List<KeyValuePair<String, String>>();
            var localVarHeaderParams = new Dictionary<String, String>(this.Configuration.DefaultHeader);
            var localVarFormParams = new Dictionary<String, String>();
            var localVarFileParams = new Dictionary<String, FileParameter>();
            Object localVarPostBody = null;

            // to determine the Content-Type header
            String[] localVarHttpContentTypes = new String[] {
            };
            String localVarHttpContentType = this.Configuration.ApiClient.SelectHeaderContentType(localVarHttpContentTypes);

            // to determine the Accept header
            String[] localVarHttpHeaderAccepts = new String[] {
                "application/json"
            };
            String localVarHttpHeaderAccept = this.Configuration.ApiClient.SelectHeaderAccept(localVarHttpHeaderAccepts);
            if (localVarHttpHeaderAccept != null)
                localVarHeaderParams.Add("Accept", localVarHttpHeaderAccept);

            if (instrumentName != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "instrument_name", instrumentName)); // query parameter
            if (amount != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "amount", amount)); // query parameter
            if (type != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "type", type)); // query parameter
            if (label != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "label", label)); // query parameter
            if (price != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "price", price)); // query parameter
            if (timeInForce != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "time_in_force", timeInForce)); // query parameter
            if (maxShow != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "max_show", maxShow)); // query parameter
            if (postOnly != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "post_only", postOnly)); // query parameter
            if (reduceOnly != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "reduce_only", reduceOnly)); // query parameter
            if (stopPrice != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "stop_price", stopPrice)); // query parameter
            if (trigger != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "trigger", trigger)); // query parameter
            if (advanced != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "advanced", advanced)); // query parameter

            // authentication (bearerAuth) required
            // http basic authentication required
            if (!String.IsNullOrEmpty(this.Configuration.Username) || !String.IsNullOrEmpty(this.Configuration.Password))
            {
                localVarHeaderParams["Authorization"] = "Basic " + ApiClient.Base64Encode(this.Configuration.Username + ":" + this.Configuration.Password);
            }

            // make the HTTP request
            IRestResponse localVarResponse = (IRestResponse) await this.Configuration.ApiClient.CallApiAsync(localVarPath,
                Method.GET, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarFileParams,
                localVarPathParams, localVarHttpContentType);

            int localVarStatusCode = (int) localVarResponse.StatusCode;

            if (ExceptionFactory != null)
            {
                Exception exception = ExceptionFactory("PrivateBuyGet", localVarResponse);
                if (exception != null) throw exception;
            }

            return new ApiResponse<Object>(localVarStatusCode,
                localVarResponse.Headers.ToDictionary(x => x.Name, x => string.Join(",", x.Value)),
                (Object) this.Configuration.ApiClient.Deserialize(localVarResponse, typeof(Object)));
        }

        /// <summary>
        /// Cancels all orders by currency, optionally filtered by instrument kind and/or order type. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="kind">Instrument kind, if not provided instruments of all kinds are considered (optional)</param>
        /// <param name="type">Order type - limit, stop or all, default - &#x60;all&#x60; (optional)</param>
        /// <returns>Object</returns>
        public Object PrivateCancelAllByCurrencyGet (string currency, string kind = null, string type = null)
        {
             ApiResponse<Object> localVarResponse = PrivateCancelAllByCurrencyGetWithHttpInfo(currency, kind, type);
             return localVarResponse.Data;
        }

        /// <summary>
        /// Cancels all orders by currency, optionally filtered by instrument kind and/or order type. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="kind">Instrument kind, if not provided instruments of all kinds are considered (optional)</param>
        /// <param name="type">Order type - limit, stop or all, default - &#x60;all&#x60; (optional)</param>
        /// <returns>ApiResponse of Object</returns>
        public ApiResponse< Object > PrivateCancelAllByCurrencyGetWithHttpInfo (string currency, string kind = null, string type = null)
        {
            // verify the required parameter 'currency' is set
            if (currency == null)
                throw new ApiException(400, "Missing required parameter 'currency' when calling TradingApi->PrivateCancelAllByCurrencyGet");

            var localVarPath = "/private/cancel_all_by_currency";
            var localVarPathParams = new Dictionary<String, String>();
            var localVarQueryParams = new List<KeyValuePair<String, String>>();
            var localVarHeaderParams = new Dictionary<String, String>(this.Configuration.DefaultHeader);
            var localVarFormParams = new Dictionary<String, String>();
            var localVarFileParams = new Dictionary<String, FileParameter>();
            Object localVarPostBody = null;

            // to determine the Content-Type header
            String[] localVarHttpContentTypes = new String[] {
            };
            String localVarHttpContentType = this.Configuration.ApiClient.SelectHeaderContentType(localVarHttpContentTypes);

            // to determine the Accept header
            String[] localVarHttpHeaderAccepts = new String[] {
                "application/json"
            };
            String localVarHttpHeaderAccept = this.Configuration.ApiClient.SelectHeaderAccept(localVarHttpHeaderAccepts);
            if (localVarHttpHeaderAccept != null)
                localVarHeaderParams.Add("Accept", localVarHttpHeaderAccept);

            if (currency != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "currency", currency)); // query parameter
            if (kind != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "kind", kind)); // query parameter
            if (type != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "type", type)); // query parameter

            // authentication (bearerAuth) required
            // http basic authentication required
            if (!String.IsNullOrEmpty(this.Configuration.Username) || !String.IsNullOrEmpty(this.Configuration.Password))
            {
                localVarHeaderParams["Authorization"] = "Basic " + ApiClient.Base64Encode(this.Configuration.Username + ":" + this.Configuration.Password);
            }

            // make the HTTP request
            IRestResponse localVarResponse = (IRestResponse) this.Configuration.ApiClient.CallApi(localVarPath,
                Method.GET, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarFileParams,
                localVarPathParams, localVarHttpContentType);

            int localVarStatusCode = (int) localVarResponse.StatusCode;

            if (ExceptionFactory != null)
            {
                Exception exception = ExceptionFactory("PrivateCancelAllByCurrencyGet", localVarResponse);
                if (exception != null) throw exception;
            }

            return new ApiResponse<Object>(localVarStatusCode,
                localVarResponse.Headers.ToDictionary(x => x.Name, x => string.Join(",", x.Value)),
                (Object) this.Configuration.ApiClient.Deserialize(localVarResponse, typeof(Object)));
        }

        /// <summary>
        /// Cancels all orders by currency, optionally filtered by instrument kind and/or order type. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="kind">Instrument kind, if not provided instruments of all kinds are considered (optional)</param>
        /// <param name="type">Order type - limit, stop or all, default - &#x60;all&#x60; (optional)</param>
        /// <returns>Task of Object</returns>
        public async System.Threading.Tasks.Task<Object> PrivateCancelAllByCurrencyGetAsync (string currency, string kind = null, string type = null)
        {
             ApiResponse<Object> localVarResponse = await PrivateCancelAllByCurrencyGetAsyncWithHttpInfo(currency, kind, type);
             return localVarResponse.Data;

        }

        /// <summary>
        /// Cancels all orders by currency, optionally filtered by instrument kind and/or order type. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="kind">Instrument kind, if not provided instruments of all kinds are considered (optional)</param>
        /// <param name="type">Order type - limit, stop or all, default - &#x60;all&#x60; (optional)</param>
        /// <returns>Task of ApiResponse (Object)</returns>
        public async System.Threading.Tasks.Task<ApiResponse<Object>> PrivateCancelAllByCurrencyGetAsyncWithHttpInfo (string currency, string kind = null, string type = null)
        {
            // verify the required parameter 'currency' is set
            if (currency == null)
                throw new ApiException(400, "Missing required parameter 'currency' when calling TradingApi->PrivateCancelAllByCurrencyGet");

            var localVarPath = "/private/cancel_all_by_currency";
            var localVarPathParams = new Dictionary<String, String>();
            var localVarQueryParams = new List<KeyValuePair<String, String>>();
            var localVarHeaderParams = new Dictionary<String, String>(this.Configuration.DefaultHeader);
            var localVarFormParams = new Dictionary<String, String>();
            var localVarFileParams = new Dictionary<String, FileParameter>();
            Object localVarPostBody = null;

            // to determine the Content-Type header
            String[] localVarHttpContentTypes = new String[] {
            };
            String localVarHttpContentType = this.Configuration.ApiClient.SelectHeaderContentType(localVarHttpContentTypes);

            // to determine the Accept header
            String[] localVarHttpHeaderAccepts = new String[] {
                "application/json"
            };
            String localVarHttpHeaderAccept = this.Configuration.ApiClient.SelectHeaderAccept(localVarHttpHeaderAccepts);
            if (localVarHttpHeaderAccept != null)
                localVarHeaderParams.Add("Accept", localVarHttpHeaderAccept);

            if (currency != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "currency", currency)); // query parameter
            if (kind != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "kind", kind)); // query parameter
            if (type != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "type", type)); // query parameter

            // authentication (bearerAuth) required
            // http basic authentication required
            if (!String.IsNullOrEmpty(this.Configuration.Username) || !String.IsNullOrEmpty(this.Configuration.Password))
            {
                localVarHeaderParams["Authorization"] = "Basic " + ApiClient.Base64Encode(this.Configuration.Username + ":" + this.Configuration.Password);
            }

            // make the HTTP request
            IRestResponse localVarResponse = (IRestResponse) await this.Configuration.ApiClient.CallApiAsync(localVarPath,
                Method.GET, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarFileParams,
                localVarPathParams, localVarHttpContentType);

            int localVarStatusCode = (int) localVarResponse.StatusCode;

            if (ExceptionFactory != null)
            {
                Exception exception = ExceptionFactory("PrivateCancelAllByCurrencyGet", localVarResponse);
                if (exception != null) throw exception;
            }

            return new ApiResponse<Object>(localVarStatusCode,
                localVarResponse.Headers.ToDictionary(x => x.Name, x => string.Join(",", x.Value)),
                (Object) this.Configuration.ApiClient.Deserialize(localVarResponse, typeof(Object)));
        }

        /// <summary>
        /// Cancels all orders by instrument, optionally filtered by order type. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">Instrument name</param>
        /// <param name="type">Order type - limit, stop or all, default - &#x60;all&#x60; (optional)</param>
        /// <returns>Object</returns>
        public Object PrivateCancelAllByInstrumentGet (string instrumentName, string type = null)
        {
             ApiResponse<Object> localVarResponse = PrivateCancelAllByInstrumentGetWithHttpInfo(instrumentName, type);
             return localVarResponse.Data;
        }

        /// <summary>
        /// Cancels all orders by instrument, optionally filtered by order type. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">Instrument name</param>
        /// <param name="type">Order type - limit, stop or all, default - &#x60;all&#x60; (optional)</param>
        /// <returns>ApiResponse of Object</returns>
        public ApiResponse< Object > PrivateCancelAllByInstrumentGetWithHttpInfo (string instrumentName, string type = null)
        {
            // verify the required parameter 'instrumentName' is set
            if (instrumentName == null)
                throw new ApiException(400, "Missing required parameter 'instrumentName' when calling TradingApi->PrivateCancelAllByInstrumentGet");

            var localVarPath = "/private/cancel_all_by_instrument";
            var localVarPathParams = new Dictionary<String, String>();
            var localVarQueryParams = new List<KeyValuePair<String, String>>();
            var localVarHeaderParams = new Dictionary<String, String>(this.Configuration.DefaultHeader);
            var localVarFormParams = new Dictionary<String, String>();
            var localVarFileParams = new Dictionary<String, FileParameter>();
            Object localVarPostBody = null;

            // to determine the Content-Type header
            String[] localVarHttpContentTypes = new String[] {
            };
            String localVarHttpContentType = this.Configuration.ApiClient.SelectHeaderContentType(localVarHttpContentTypes);

            // to determine the Accept header
            String[] localVarHttpHeaderAccepts = new String[] {
                "application/json"
            };
            String localVarHttpHeaderAccept = this.Configuration.ApiClient.SelectHeaderAccept(localVarHttpHeaderAccepts);
            if (localVarHttpHeaderAccept != null)
                localVarHeaderParams.Add("Accept", localVarHttpHeaderAccept);

            if (instrumentName != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "instrument_name", instrumentName)); // query parameter
            if (type != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "type", type)); // query parameter

            // authentication (bearerAuth) required
            // http basic authentication required
            if (!String.IsNullOrEmpty(this.Configuration.Username) || !String.IsNullOrEmpty(this.Configuration.Password))
            {
                localVarHeaderParams["Authorization"] = "Basic " + ApiClient.Base64Encode(this.Configuration.Username + ":" + this.Configuration.Password);
            }

            // make the HTTP request
            IRestResponse localVarResponse = (IRestResponse) this.Configuration.ApiClient.CallApi(localVarPath,
                Method.GET, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarFileParams,
                localVarPathParams, localVarHttpContentType);

            int localVarStatusCode = (int) localVarResponse.StatusCode;

            if (ExceptionFactory != null)
            {
                Exception exception = ExceptionFactory("PrivateCancelAllByInstrumentGet", localVarResponse);
                if (exception != null) throw exception;
            }

            return new ApiResponse<Object>(localVarStatusCode,
                localVarResponse.Headers.ToDictionary(x => x.Name, x => string.Join(",", x.Value)),
                (Object) this.Configuration.ApiClient.Deserialize(localVarResponse, typeof(Object)));
        }

        /// <summary>
        /// Cancels all orders by instrument, optionally filtered by order type. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">Instrument name</param>
        /// <param name="type">Order type - limit, stop or all, default - &#x60;all&#x60; (optional)</param>
        /// <returns>Task of Object</returns>
        public async System.Threading.Tasks.Task<Object> PrivateCancelAllByInstrumentGetAsync (string instrumentName, string type = null)
        {
             ApiResponse<Object> localVarResponse = await PrivateCancelAllByInstrumentGetAsyncWithHttpInfo(instrumentName, type);
             return localVarResponse.Data;

        }

        /// <summary>
        /// Cancels all orders by instrument, optionally filtered by order type. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">Instrument name</param>
        /// <param name="type">Order type - limit, stop or all, default - &#x60;all&#x60; (optional)</param>
        /// <returns>Task of ApiResponse (Object)</returns>
        public async System.Threading.Tasks.Task<ApiResponse<Object>> PrivateCancelAllByInstrumentGetAsyncWithHttpInfo (string instrumentName, string type = null)
        {
            // verify the required parameter 'instrumentName' is set
            if (instrumentName == null)
                throw new ApiException(400, "Missing required parameter 'instrumentName' when calling TradingApi->PrivateCancelAllByInstrumentGet");

            var localVarPath = "/private/cancel_all_by_instrument";
            var localVarPathParams = new Dictionary<String, String>();
            var localVarQueryParams = new List<KeyValuePair<String, String>>();
            var localVarHeaderParams = new Dictionary<String, String>(this.Configuration.DefaultHeader);
            var localVarFormParams = new Dictionary<String, String>();
            var localVarFileParams = new Dictionary<String, FileParameter>();
            Object localVarPostBody = null;

            // to determine the Content-Type header
            String[] localVarHttpContentTypes = new String[] {
            };
            String localVarHttpContentType = this.Configuration.ApiClient.SelectHeaderContentType(localVarHttpContentTypes);

            // to determine the Accept header
            String[] localVarHttpHeaderAccepts = new String[] {
                "application/json"
            };
            String localVarHttpHeaderAccept = this.Configuration.ApiClient.SelectHeaderAccept(localVarHttpHeaderAccepts);
            if (localVarHttpHeaderAccept != null)
                localVarHeaderParams.Add("Accept", localVarHttpHeaderAccept);

            if (instrumentName != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "instrument_name", instrumentName)); // query parameter
            if (type != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "type", type)); // query parameter

            // authentication (bearerAuth) required
            // http basic authentication required
            if (!String.IsNullOrEmpty(this.Configuration.Username) || !String.IsNullOrEmpty(this.Configuration.Password))
            {
                localVarHeaderParams["Authorization"] = "Basic " + ApiClient.Base64Encode(this.Configuration.Username + ":" + this.Configuration.Password);
            }

            // make the HTTP request
            IRestResponse localVarResponse = (IRestResponse) await this.Configuration.ApiClient.CallApiAsync(localVarPath,
                Method.GET, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarFileParams,
                localVarPathParams, localVarHttpContentType);

            int localVarStatusCode = (int) localVarResponse.StatusCode;

            if (ExceptionFactory != null)
            {
                Exception exception = ExceptionFactory("PrivateCancelAllByInstrumentGet", localVarResponse);
                if (exception != null) throw exception;
            }

            return new ApiResponse<Object>(localVarStatusCode,
                localVarResponse.Headers.ToDictionary(x => x.Name, x => string.Join(",", x.Value)),
                (Object) this.Configuration.ApiClient.Deserialize(localVarResponse, typeof(Object)));
        }

        /// <summary>
        /// This method cancels all users orders and stop orders within all currencies and instrument kinds. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <returns>Object</returns>
        public Object PrivateCancelAllGet ()
        {
             ApiResponse<Object> localVarResponse = PrivateCancelAllGetWithHttpInfo();
             return localVarResponse.Data;
        }

        /// <summary>
        /// This method cancels all users orders and stop orders within all currencies and instrument kinds. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <returns>ApiResponse of Object</returns>
        public ApiResponse< Object > PrivateCancelAllGetWithHttpInfo ()
        {

            var localVarPath = "/private/cancel_all";
            var localVarPathParams = new Dictionary<String, String>();
            var localVarQueryParams = new List<KeyValuePair<String, String>>();
            var localVarHeaderParams = new Dictionary<String, String>(this.Configuration.DefaultHeader);
            var localVarFormParams = new Dictionary<String, String>();
            var localVarFileParams = new Dictionary<String, FileParameter>();
            Object localVarPostBody = null;

            // to determine the Content-Type header
            String[] localVarHttpContentTypes = new String[] {
            };
            String localVarHttpContentType = this.Configuration.ApiClient.SelectHeaderContentType(localVarHttpContentTypes);

            // to determine the Accept header
            String[] localVarHttpHeaderAccepts = new String[] {
                "application/json"
            };
            String localVarHttpHeaderAccept = this.Configuration.ApiClient.SelectHeaderAccept(localVarHttpHeaderAccepts);
            if (localVarHttpHeaderAccept != null)
                localVarHeaderParams.Add("Accept", localVarHttpHeaderAccept);


            // authentication (bearerAuth) required
            // http basic authentication required
            if (!String.IsNullOrEmpty(this.Configuration.Username) || !String.IsNullOrEmpty(this.Configuration.Password))
            {
                localVarHeaderParams["Authorization"] = "Basic " + ApiClient.Base64Encode(this.Configuration.Username + ":" + this.Configuration.Password);
            }

            // make the HTTP request
            IRestResponse localVarResponse = (IRestResponse) this.Configuration.ApiClient.CallApi(localVarPath,
                Method.GET, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarFileParams,
                localVarPathParams, localVarHttpContentType);

            int localVarStatusCode = (int) localVarResponse.StatusCode;

            if (ExceptionFactory != null)
            {
                Exception exception = ExceptionFactory("PrivateCancelAllGet", localVarResponse);
                if (exception != null) throw exception;
            }

            return new ApiResponse<Object>(localVarStatusCode,
                localVarResponse.Headers.ToDictionary(x => x.Name, x => string.Join(",", x.Value)),
                (Object) this.Configuration.ApiClient.Deserialize(localVarResponse, typeof(Object)));
        }

        /// <summary>
        /// This method cancels all users orders and stop orders within all currencies and instrument kinds. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <returns>Task of Object</returns>
        public async System.Threading.Tasks.Task<Object> PrivateCancelAllGetAsync ()
        {
             ApiResponse<Object> localVarResponse = await PrivateCancelAllGetAsyncWithHttpInfo();
             return localVarResponse.Data;

        }

        /// <summary>
        /// This method cancels all users orders and stop orders within all currencies and instrument kinds. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <returns>Task of ApiResponse (Object)</returns>
        public async System.Threading.Tasks.Task<ApiResponse<Object>> PrivateCancelAllGetAsyncWithHttpInfo ()
        {

            var localVarPath = "/private/cancel_all";
            var localVarPathParams = new Dictionary<String, String>();
            var localVarQueryParams = new List<KeyValuePair<String, String>>();
            var localVarHeaderParams = new Dictionary<String, String>(this.Configuration.DefaultHeader);
            var localVarFormParams = new Dictionary<String, String>();
            var localVarFileParams = new Dictionary<String, FileParameter>();
            Object localVarPostBody = null;

            // to determine the Content-Type header
            String[] localVarHttpContentTypes = new String[] {
            };
            String localVarHttpContentType = this.Configuration.ApiClient.SelectHeaderContentType(localVarHttpContentTypes);

            // to determine the Accept header
            String[] localVarHttpHeaderAccepts = new String[] {
                "application/json"
            };
            String localVarHttpHeaderAccept = this.Configuration.ApiClient.SelectHeaderAccept(localVarHttpHeaderAccepts);
            if (localVarHttpHeaderAccept != null)
                localVarHeaderParams.Add("Accept", localVarHttpHeaderAccept);


            // authentication (bearerAuth) required
            // http basic authentication required
            if (!String.IsNullOrEmpty(this.Configuration.Username) || !String.IsNullOrEmpty(this.Configuration.Password))
            {
                localVarHeaderParams["Authorization"] = "Basic " + ApiClient.Base64Encode(this.Configuration.Username + ":" + this.Configuration.Password);
            }

            // make the HTTP request
            IRestResponse localVarResponse = (IRestResponse) await this.Configuration.ApiClient.CallApiAsync(localVarPath,
                Method.GET, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarFileParams,
                localVarPathParams, localVarHttpContentType);

            int localVarStatusCode = (int) localVarResponse.StatusCode;

            if (ExceptionFactory != null)
            {
                Exception exception = ExceptionFactory("PrivateCancelAllGet", localVarResponse);
                if (exception != null) throw exception;
            }

            return new ApiResponse<Object>(localVarStatusCode,
                localVarResponse.Headers.ToDictionary(x => x.Name, x => string.Join(",", x.Value)),
                (Object) this.Configuration.ApiClient.Deserialize(localVarResponse, typeof(Object)));
        }

        /// <summary>
        /// Cancel an order, specified by order id 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="orderId">The order id</param>
        /// <returns>Object</returns>
        public Object PrivateCancelGet (string orderId)
        {
             ApiResponse<Object> localVarResponse = PrivateCancelGetWithHttpInfo(orderId);
             return localVarResponse.Data;
        }

        /// <summary>
        /// Cancel an order, specified by order id 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="orderId">The order id</param>
        /// <returns>ApiResponse of Object</returns>
        public ApiResponse< Object > PrivateCancelGetWithHttpInfo (string orderId)
        {
            // verify the required parameter 'orderId' is set
            if (orderId == null)
                throw new ApiException(400, "Missing required parameter 'orderId' when calling TradingApi->PrivateCancelGet");

            var localVarPath = "/private/cancel";
            var localVarPathParams = new Dictionary<String, String>();
            var localVarQueryParams = new List<KeyValuePair<String, String>>();
            var localVarHeaderParams = new Dictionary<String, String>(this.Configuration.DefaultHeader);
            var localVarFormParams = new Dictionary<String, String>();
            var localVarFileParams = new Dictionary<String, FileParameter>();
            Object localVarPostBody = null;

            // to determine the Content-Type header
            String[] localVarHttpContentTypes = new String[] {
            };
            String localVarHttpContentType = this.Configuration.ApiClient.SelectHeaderContentType(localVarHttpContentTypes);

            // to determine the Accept header
            String[] localVarHttpHeaderAccepts = new String[] {
                "application/json"
            };
            String localVarHttpHeaderAccept = this.Configuration.ApiClient.SelectHeaderAccept(localVarHttpHeaderAccepts);
            if (localVarHttpHeaderAccept != null)
                localVarHeaderParams.Add("Accept", localVarHttpHeaderAccept);

            if (orderId != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "order_id", orderId)); // query parameter

            // authentication (bearerAuth) required
            // http basic authentication required
            if (!String.IsNullOrEmpty(this.Configuration.Username) || !String.IsNullOrEmpty(this.Configuration.Password))
            {
                localVarHeaderParams["Authorization"] = "Basic " + ApiClient.Base64Encode(this.Configuration.Username + ":" + this.Configuration.Password);
            }

            // make the HTTP request
            IRestResponse localVarResponse = (IRestResponse) this.Configuration.ApiClient.CallApi(localVarPath,
                Method.GET, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarFileParams,
                localVarPathParams, localVarHttpContentType);

            int localVarStatusCode = (int) localVarResponse.StatusCode;

            if (ExceptionFactory != null)
            {
                Exception exception = ExceptionFactory("PrivateCancelGet", localVarResponse);
                if (exception != null) throw exception;
            }

            return new ApiResponse<Object>(localVarStatusCode,
                localVarResponse.Headers.ToDictionary(x => x.Name, x => string.Join(",", x.Value)),
                (Object) this.Configuration.ApiClient.Deserialize(localVarResponse, typeof(Object)));
        }

        /// <summary>
        /// Cancel an order, specified by order id 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="orderId">The order id</param>
        /// <returns>Task of Object</returns>
        public async System.Threading.Tasks.Task<Object> PrivateCancelGetAsync (string orderId)
        {
             ApiResponse<Object> localVarResponse = await PrivateCancelGetAsyncWithHttpInfo(orderId);
             return localVarResponse.Data;

        }

        /// <summary>
        /// Cancel an order, specified by order id 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="orderId">The order id</param>
        /// <returns>Task of ApiResponse (Object)</returns>
        public async System.Threading.Tasks.Task<ApiResponse<Object>> PrivateCancelGetAsyncWithHttpInfo (string orderId)
        {
            // verify the required parameter 'orderId' is set
            if (orderId == null)
                throw new ApiException(400, "Missing required parameter 'orderId' when calling TradingApi->PrivateCancelGet");

            var localVarPath = "/private/cancel";
            var localVarPathParams = new Dictionary<String, String>();
            var localVarQueryParams = new List<KeyValuePair<String, String>>();
            var localVarHeaderParams = new Dictionary<String, String>(this.Configuration.DefaultHeader);
            var localVarFormParams = new Dictionary<String, String>();
            var localVarFileParams = new Dictionary<String, FileParameter>();
            Object localVarPostBody = null;

            // to determine the Content-Type header
            String[] localVarHttpContentTypes = new String[] {
            };
            String localVarHttpContentType = this.Configuration.ApiClient.SelectHeaderContentType(localVarHttpContentTypes);

            // to determine the Accept header
            String[] localVarHttpHeaderAccepts = new String[] {
                "application/json"
            };
            String localVarHttpHeaderAccept = this.Configuration.ApiClient.SelectHeaderAccept(localVarHttpHeaderAccepts);
            if (localVarHttpHeaderAccept != null)
                localVarHeaderParams.Add("Accept", localVarHttpHeaderAccept);

            if (orderId != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "order_id", orderId)); // query parameter

            // authentication (bearerAuth) required
            // http basic authentication required
            if (!String.IsNullOrEmpty(this.Configuration.Username) || !String.IsNullOrEmpty(this.Configuration.Password))
            {
                localVarHeaderParams["Authorization"] = "Basic " + ApiClient.Base64Encode(this.Configuration.Username + ":" + this.Configuration.Password);
            }

            // make the HTTP request
            IRestResponse localVarResponse = (IRestResponse) await this.Configuration.ApiClient.CallApiAsync(localVarPath,
                Method.GET, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarFileParams,
                localVarPathParams, localVarHttpContentType);

            int localVarStatusCode = (int) localVarResponse.StatusCode;

            if (ExceptionFactory != null)
            {
                Exception exception = ExceptionFactory("PrivateCancelGet", localVarResponse);
                if (exception != null) throw exception;
            }

            return new ApiResponse<Object>(localVarStatusCode,
                localVarResponse.Headers.ToDictionary(x => x.Name, x => string.Join(",", x.Value)),
                (Object) this.Configuration.ApiClient.Deserialize(localVarResponse, typeof(Object)));
        }

        /// <summary>
        /// Makes closing position reduce only order . 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">Instrument name</param>
        /// <param name="type">The order type</param>
        /// <param name="price">Optional price for limit order. (optional)</param>
        /// <returns>Object</returns>
        public Object PrivateClosePositionGet (string instrumentName, string type, decimal? price = null)
        {
             ApiResponse<Object> localVarResponse = PrivateClosePositionGetWithHttpInfo(instrumentName, type, price);
             return localVarResponse.Data;
        }

        /// <summary>
        /// Makes closing position reduce only order . 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">Instrument name</param>
        /// <param name="type">The order type</param>
        /// <param name="price">Optional price for limit order. (optional)</param>
        /// <returns>ApiResponse of Object</returns>
        public ApiResponse< Object > PrivateClosePositionGetWithHttpInfo (string instrumentName, string type, decimal? price = null)
        {
            // verify the required parameter 'instrumentName' is set
            if (instrumentName == null)
                throw new ApiException(400, "Missing required parameter 'instrumentName' when calling TradingApi->PrivateClosePositionGet");
            // verify the required parameter 'type' is set
            if (type == null)
                throw new ApiException(400, "Missing required parameter 'type' when calling TradingApi->PrivateClosePositionGet");

            var localVarPath = "/private/close_position";
            var localVarPathParams = new Dictionary<String, String>();
            var localVarQueryParams = new List<KeyValuePair<String, String>>();
            var localVarHeaderParams = new Dictionary<String, String>(this.Configuration.DefaultHeader);
            var localVarFormParams = new Dictionary<String, String>();
            var localVarFileParams = new Dictionary<String, FileParameter>();
            Object localVarPostBody = null;

            // to determine the Content-Type header
            String[] localVarHttpContentTypes = new String[] {
            };
            String localVarHttpContentType = this.Configuration.ApiClient.SelectHeaderContentType(localVarHttpContentTypes);

            // to determine the Accept header
            String[] localVarHttpHeaderAccepts = new String[] {
                "application/json"
            };
            String localVarHttpHeaderAccept = this.Configuration.ApiClient.SelectHeaderAccept(localVarHttpHeaderAccepts);
            if (localVarHttpHeaderAccept != null)
                localVarHeaderParams.Add("Accept", localVarHttpHeaderAccept);

            if (instrumentName != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "instrument_name", instrumentName)); // query parameter
            if (type != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "type", type)); // query parameter
            if (price != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "price", price)); // query parameter

            // authentication (bearerAuth) required
            // http basic authentication required
            if (!String.IsNullOrEmpty(this.Configuration.Username) || !String.IsNullOrEmpty(this.Configuration.Password))
            {
                localVarHeaderParams["Authorization"] = "Basic " + ApiClient.Base64Encode(this.Configuration.Username + ":" + this.Configuration.Password);
            }

            // make the HTTP request
            IRestResponse localVarResponse = (IRestResponse) this.Configuration.ApiClient.CallApi(localVarPath,
                Method.GET, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarFileParams,
                localVarPathParams, localVarHttpContentType);

            int localVarStatusCode = (int) localVarResponse.StatusCode;

            if (ExceptionFactory != null)
            {
                Exception exception = ExceptionFactory("PrivateClosePositionGet", localVarResponse);
                if (exception != null) throw exception;
            }

            return new ApiResponse<Object>(localVarStatusCode,
                localVarResponse.Headers.ToDictionary(x => x.Name, x => string.Join(",", x.Value)),
                (Object) this.Configuration.ApiClient.Deserialize(localVarResponse, typeof(Object)));
        }

        /// <summary>
        /// Makes closing position reduce only order . 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">Instrument name</param>
        /// <param name="type">The order type</param>
        /// <param name="price">Optional price for limit order. (optional)</param>
        /// <returns>Task of Object</returns>
        public async System.Threading.Tasks.Task<Object> PrivateClosePositionGetAsync (string instrumentName, string type, decimal? price = null)
        {
             ApiResponse<Object> localVarResponse = await PrivateClosePositionGetAsyncWithHttpInfo(instrumentName, type, price);
             return localVarResponse.Data;

        }

        /// <summary>
        /// Makes closing position reduce only order . 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">Instrument name</param>
        /// <param name="type">The order type</param>
        /// <param name="price">Optional price for limit order. (optional)</param>
        /// <returns>Task of ApiResponse (Object)</returns>
        public async System.Threading.Tasks.Task<ApiResponse<Object>> PrivateClosePositionGetAsyncWithHttpInfo (string instrumentName, string type, decimal? price = null)
        {
            // verify the required parameter 'instrumentName' is set
            if (instrumentName == null)
                throw new ApiException(400, "Missing required parameter 'instrumentName' when calling TradingApi->PrivateClosePositionGet");
            // verify the required parameter 'type' is set
            if (type == null)
                throw new ApiException(400, "Missing required parameter 'type' when calling TradingApi->PrivateClosePositionGet");

            var localVarPath = "/private/close_position";
            var localVarPathParams = new Dictionary<String, String>();
            var localVarQueryParams = new List<KeyValuePair<String, String>>();
            var localVarHeaderParams = new Dictionary<String, String>(this.Configuration.DefaultHeader);
            var localVarFormParams = new Dictionary<String, String>();
            var localVarFileParams = new Dictionary<String, FileParameter>();
            Object localVarPostBody = null;

            // to determine the Content-Type header
            String[] localVarHttpContentTypes = new String[] {
            };
            String localVarHttpContentType = this.Configuration.ApiClient.SelectHeaderContentType(localVarHttpContentTypes);

            // to determine the Accept header
            String[] localVarHttpHeaderAccepts = new String[] {
                "application/json"
            };
            String localVarHttpHeaderAccept = this.Configuration.ApiClient.SelectHeaderAccept(localVarHttpHeaderAccepts);
            if (localVarHttpHeaderAccept != null)
                localVarHeaderParams.Add("Accept", localVarHttpHeaderAccept);

            if (instrumentName != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "instrument_name", instrumentName)); // query parameter
            if (type != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "type", type)); // query parameter
            if (price != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "price", price)); // query parameter

            // authentication (bearerAuth) required
            // http basic authentication required
            if (!String.IsNullOrEmpty(this.Configuration.Username) || !String.IsNullOrEmpty(this.Configuration.Password))
            {
                localVarHeaderParams["Authorization"] = "Basic " + ApiClient.Base64Encode(this.Configuration.Username + ":" + this.Configuration.Password);
            }

            // make the HTTP request
            IRestResponse localVarResponse = (IRestResponse) await this.Configuration.ApiClient.CallApiAsync(localVarPath,
                Method.GET, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarFileParams,
                localVarPathParams, localVarHttpContentType);

            int localVarStatusCode = (int) localVarResponse.StatusCode;

            if (ExceptionFactory != null)
            {
                Exception exception = ExceptionFactory("PrivateClosePositionGet", localVarResponse);
                if (exception != null) throw exception;
            }

            return new ApiResponse<Object>(localVarStatusCode,
                localVarResponse.Headers.ToDictionary(x => x.Name, x => string.Join(",", x.Value)),
                (Object) this.Configuration.ApiClient.Deserialize(localVarResponse, typeof(Object)));
        }

        /// <summary>
        /// Change price, amount and/or other properties of an order. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="orderId">The order id</param>
        /// <param name="amount">It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH</param>
        /// <param name="price">&lt;p&gt;The order price in base currency.&lt;/p&gt; &lt;p&gt;When editing an option order with advanced&#x3D;usd, the field price should be the option price value in USD.&lt;/p&gt; &lt;p&gt;When editing an option order with advanced&#x3D;implv, the field price should be a value of implied volatility in percentages. For example,  price&#x3D;100, means implied volatility of 100%&lt;/p&gt;</param>
        /// <param name="postOnly">&lt;p&gt;If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.&lt;/p&gt; &lt;p&gt;Only valid in combination with time_in_force&#x3D;&#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; (optional, default to true)</param>
        /// <param name="advanced">Advanced option order type. If you have posted an advanced option order, it is necessary to re-supply this parameter when editing it (Only for options) (optional)</param>
        /// <param name="stopPrice">Stop price, required for stop limit orders (Only for stop orders) (optional)</param>
        /// <returns>Object</returns>
        public Object PrivateEditGet (string orderId, decimal? amount, decimal? price, bool? postOnly = null, string advanced = null, decimal? stopPrice = null)
        {
             ApiResponse<Object> localVarResponse = PrivateEditGetWithHttpInfo(orderId, amount, price, postOnly, advanced, stopPrice);
             return localVarResponse.Data;
        }

        /// <summary>
        /// Change price, amount and/or other properties of an order. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="orderId">The order id</param>
        /// <param name="amount">It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH</param>
        /// <param name="price">&lt;p&gt;The order price in base currency.&lt;/p&gt; &lt;p&gt;When editing an option order with advanced&#x3D;usd, the field price should be the option price value in USD.&lt;/p&gt; &lt;p&gt;When editing an option order with advanced&#x3D;implv, the field price should be a value of implied volatility in percentages. For example,  price&#x3D;100, means implied volatility of 100%&lt;/p&gt;</param>
        /// <param name="postOnly">&lt;p&gt;If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.&lt;/p&gt; &lt;p&gt;Only valid in combination with time_in_force&#x3D;&#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; (optional, default to true)</param>
        /// <param name="advanced">Advanced option order type. If you have posted an advanced option order, it is necessary to re-supply this parameter when editing it (Only for options) (optional)</param>
        /// <param name="stopPrice">Stop price, required for stop limit orders (Only for stop orders) (optional)</param>
        /// <returns>ApiResponse of Object</returns>
        public ApiResponse< Object > PrivateEditGetWithHttpInfo (string orderId, decimal? amount, decimal? price, bool? postOnly = null, string advanced = null, decimal? stopPrice = null)
        {
            // verify the required parameter 'orderId' is set
            if (orderId == null)
                throw new ApiException(400, "Missing required parameter 'orderId' when calling TradingApi->PrivateEditGet");
            // verify the required parameter 'amount' is set
            if (amount == null)
                throw new ApiException(400, "Missing required parameter 'amount' when calling TradingApi->PrivateEditGet");
            // verify the required parameter 'price' is set
            if (price == null)
                throw new ApiException(400, "Missing required parameter 'price' when calling TradingApi->PrivateEditGet");

            var localVarPath = "/private/edit";
            var localVarPathParams = new Dictionary<String, String>();
            var localVarQueryParams = new List<KeyValuePair<String, String>>();
            var localVarHeaderParams = new Dictionary<String, String>(this.Configuration.DefaultHeader);
            var localVarFormParams = new Dictionary<String, String>();
            var localVarFileParams = new Dictionary<String, FileParameter>();
            Object localVarPostBody = null;

            // to determine the Content-Type header
            String[] localVarHttpContentTypes = new String[] {
            };
            String localVarHttpContentType = this.Configuration.ApiClient.SelectHeaderContentType(localVarHttpContentTypes);

            // to determine the Accept header
            String[] localVarHttpHeaderAccepts = new String[] {
                "application/json"
            };
            String localVarHttpHeaderAccept = this.Configuration.ApiClient.SelectHeaderAccept(localVarHttpHeaderAccepts);
            if (localVarHttpHeaderAccept != null)
                localVarHeaderParams.Add("Accept", localVarHttpHeaderAccept);

            if (orderId != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "order_id", orderId)); // query parameter
            if (amount != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "amount", amount)); // query parameter
            if (price != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "price", price)); // query parameter
            if (postOnly != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "post_only", postOnly)); // query parameter
            if (advanced != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "advanced", advanced)); // query parameter
            if (stopPrice != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "stop_price", stopPrice)); // query parameter

            // authentication (bearerAuth) required
            // http basic authentication required
            if (!String.IsNullOrEmpty(this.Configuration.Username) || !String.IsNullOrEmpty(this.Configuration.Password))
            {
                localVarHeaderParams["Authorization"] = "Basic " + ApiClient.Base64Encode(this.Configuration.Username + ":" + this.Configuration.Password);
            }

            // make the HTTP request
            IRestResponse localVarResponse = (IRestResponse) this.Configuration.ApiClient.CallApi(localVarPath,
                Method.GET, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarFileParams,
                localVarPathParams, localVarHttpContentType);

            int localVarStatusCode = (int) localVarResponse.StatusCode;

            if (ExceptionFactory != null)
            {
                Exception exception = ExceptionFactory("PrivateEditGet", localVarResponse);
                if (exception != null) throw exception;
            }

            return new ApiResponse<Object>(localVarStatusCode,
                localVarResponse.Headers.ToDictionary(x => x.Name, x => string.Join(",", x.Value)),
                (Object) this.Configuration.ApiClient.Deserialize(localVarResponse, typeof(Object)));
        }

        /// <summary>
        /// Change price, amount and/or other properties of an order. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="orderId">The order id</param>
        /// <param name="amount">It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH</param>
        /// <param name="price">&lt;p&gt;The order price in base currency.&lt;/p&gt; &lt;p&gt;When editing an option order with advanced&#x3D;usd, the field price should be the option price value in USD.&lt;/p&gt; &lt;p&gt;When editing an option order with advanced&#x3D;implv, the field price should be a value of implied volatility in percentages. For example,  price&#x3D;100, means implied volatility of 100%&lt;/p&gt;</param>
        /// <param name="postOnly">&lt;p&gt;If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.&lt;/p&gt; &lt;p&gt;Only valid in combination with time_in_force&#x3D;&#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; (optional, default to true)</param>
        /// <param name="advanced">Advanced option order type. If you have posted an advanced option order, it is necessary to re-supply this parameter when editing it (Only for options) (optional)</param>
        /// <param name="stopPrice">Stop price, required for stop limit orders (Only for stop orders) (optional)</param>
        /// <returns>Task of Object</returns>
        public async System.Threading.Tasks.Task<Object> PrivateEditGetAsync (string orderId, decimal? amount, decimal? price, bool? postOnly = null, string advanced = null, decimal? stopPrice = null)
        {
             ApiResponse<Object> localVarResponse = await PrivateEditGetAsyncWithHttpInfo(orderId, amount, price, postOnly, advanced, stopPrice);
             return localVarResponse.Data;

        }

        /// <summary>
        /// Change price, amount and/or other properties of an order. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="orderId">The order id</param>
        /// <param name="amount">It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH</param>
        /// <param name="price">&lt;p&gt;The order price in base currency.&lt;/p&gt; &lt;p&gt;When editing an option order with advanced&#x3D;usd, the field price should be the option price value in USD.&lt;/p&gt; &lt;p&gt;When editing an option order with advanced&#x3D;implv, the field price should be a value of implied volatility in percentages. For example,  price&#x3D;100, means implied volatility of 100%&lt;/p&gt;</param>
        /// <param name="postOnly">&lt;p&gt;If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.&lt;/p&gt; &lt;p&gt;Only valid in combination with time_in_force&#x3D;&#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; (optional, default to true)</param>
        /// <param name="advanced">Advanced option order type. If you have posted an advanced option order, it is necessary to re-supply this parameter when editing it (Only for options) (optional)</param>
        /// <param name="stopPrice">Stop price, required for stop limit orders (Only for stop orders) (optional)</param>
        /// <returns>Task of ApiResponse (Object)</returns>
        public async System.Threading.Tasks.Task<ApiResponse<Object>> PrivateEditGetAsyncWithHttpInfo (string orderId, decimal? amount, decimal? price, bool? postOnly = null, string advanced = null, decimal? stopPrice = null)
        {
            // verify the required parameter 'orderId' is set
            if (orderId == null)
                throw new ApiException(400, "Missing required parameter 'orderId' when calling TradingApi->PrivateEditGet");
            // verify the required parameter 'amount' is set
            if (amount == null)
                throw new ApiException(400, "Missing required parameter 'amount' when calling TradingApi->PrivateEditGet");
            // verify the required parameter 'price' is set
            if (price == null)
                throw new ApiException(400, "Missing required parameter 'price' when calling TradingApi->PrivateEditGet");

            var localVarPath = "/private/edit";
            var localVarPathParams = new Dictionary<String, String>();
            var localVarQueryParams = new List<KeyValuePair<String, String>>();
            var localVarHeaderParams = new Dictionary<String, String>(this.Configuration.DefaultHeader);
            var localVarFormParams = new Dictionary<String, String>();
            var localVarFileParams = new Dictionary<String, FileParameter>();
            Object localVarPostBody = null;

            // to determine the Content-Type header
            String[] localVarHttpContentTypes = new String[] {
            };
            String localVarHttpContentType = this.Configuration.ApiClient.SelectHeaderContentType(localVarHttpContentTypes);

            // to determine the Accept header
            String[] localVarHttpHeaderAccepts = new String[] {
                "application/json"
            };
            String localVarHttpHeaderAccept = this.Configuration.ApiClient.SelectHeaderAccept(localVarHttpHeaderAccepts);
            if (localVarHttpHeaderAccept != null)
                localVarHeaderParams.Add("Accept", localVarHttpHeaderAccept);

            if (orderId != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "order_id", orderId)); // query parameter
            if (amount != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "amount", amount)); // query parameter
            if (price != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "price", price)); // query parameter
            if (postOnly != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "post_only", postOnly)); // query parameter
            if (advanced != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "advanced", advanced)); // query parameter
            if (stopPrice != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "stop_price", stopPrice)); // query parameter

            // authentication (bearerAuth) required
            // http basic authentication required
            if (!String.IsNullOrEmpty(this.Configuration.Username) || !String.IsNullOrEmpty(this.Configuration.Password))
            {
                localVarHeaderParams["Authorization"] = "Basic " + ApiClient.Base64Encode(this.Configuration.Username + ":" + this.Configuration.Password);
            }

            // make the HTTP request
            IRestResponse localVarResponse = (IRestResponse) await this.Configuration.ApiClient.CallApiAsync(localVarPath,
                Method.GET, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarFileParams,
                localVarPathParams, localVarHttpContentType);

            int localVarStatusCode = (int) localVarResponse.StatusCode;

            if (ExceptionFactory != null)
            {
                Exception exception = ExceptionFactory("PrivateEditGet", localVarResponse);
                if (exception != null) throw exception;
            }

            return new ApiResponse<Object>(localVarStatusCode,
                localVarResponse.Headers.ToDictionary(x => x.Name, x => string.Join(",", x.Value)),
                (Object) this.Configuration.ApiClient.Deserialize(localVarResponse, typeof(Object)));
        }

        /// <summary>
        /// Get margins for given instrument, amount and price. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">Instrument name</param>
        /// <param name="amount">Amount, integer for future, float for option. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH.</param>
        /// <param name="price">Price</param>
        /// <returns>Object</returns>
        public Object PrivateGetMarginsGet (string instrumentName, decimal? amount, decimal? price)
        {
             ApiResponse<Object> localVarResponse = PrivateGetMarginsGetWithHttpInfo(instrumentName, amount, price);
             return localVarResponse.Data;
        }

        /// <summary>
        /// Get margins for given instrument, amount and price. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">Instrument name</param>
        /// <param name="amount">Amount, integer for future, float for option. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH.</param>
        /// <param name="price">Price</param>
        /// <returns>ApiResponse of Object</returns>
        public ApiResponse< Object > PrivateGetMarginsGetWithHttpInfo (string instrumentName, decimal? amount, decimal? price)
        {
            // verify the required parameter 'instrumentName' is set
            if (instrumentName == null)
                throw new ApiException(400, "Missing required parameter 'instrumentName' when calling TradingApi->PrivateGetMarginsGet");
            // verify the required parameter 'amount' is set
            if (amount == null)
                throw new ApiException(400, "Missing required parameter 'amount' when calling TradingApi->PrivateGetMarginsGet");
            // verify the required parameter 'price' is set
            if (price == null)
                throw new ApiException(400, "Missing required parameter 'price' when calling TradingApi->PrivateGetMarginsGet");

            var localVarPath = "/private/get_margins";
            var localVarPathParams = new Dictionary<String, String>();
            var localVarQueryParams = new List<KeyValuePair<String, String>>();
            var localVarHeaderParams = new Dictionary<String, String>(this.Configuration.DefaultHeader);
            var localVarFormParams = new Dictionary<String, String>();
            var localVarFileParams = new Dictionary<String, FileParameter>();
            Object localVarPostBody = null;

            // to determine the Content-Type header
            String[] localVarHttpContentTypes = new String[] {
            };
            String localVarHttpContentType = this.Configuration.ApiClient.SelectHeaderContentType(localVarHttpContentTypes);

            // to determine the Accept header
            String[] localVarHttpHeaderAccepts = new String[] {
                "application/json"
            };
            String localVarHttpHeaderAccept = this.Configuration.ApiClient.SelectHeaderAccept(localVarHttpHeaderAccepts);
            if (localVarHttpHeaderAccept != null)
                localVarHeaderParams.Add("Accept", localVarHttpHeaderAccept);

            if (instrumentName != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "instrument_name", instrumentName)); // query parameter
            if (amount != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "amount", amount)); // query parameter
            if (price != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "price", price)); // query parameter

            // authentication (bearerAuth) required
            // http basic authentication required
            if (!String.IsNullOrEmpty(this.Configuration.Username) || !String.IsNullOrEmpty(this.Configuration.Password))
            {
                localVarHeaderParams["Authorization"] = "Basic " + ApiClient.Base64Encode(this.Configuration.Username + ":" + this.Configuration.Password);
            }

            // make the HTTP request
            IRestResponse localVarResponse = (IRestResponse) this.Configuration.ApiClient.CallApi(localVarPath,
                Method.GET, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarFileParams,
                localVarPathParams, localVarHttpContentType);

            int localVarStatusCode = (int) localVarResponse.StatusCode;

            if (ExceptionFactory != null)
            {
                Exception exception = ExceptionFactory("PrivateGetMarginsGet", localVarResponse);
                if (exception != null) throw exception;
            }

            return new ApiResponse<Object>(localVarStatusCode,
                localVarResponse.Headers.ToDictionary(x => x.Name, x => string.Join(",", x.Value)),
                (Object) this.Configuration.ApiClient.Deserialize(localVarResponse, typeof(Object)));
        }

        /// <summary>
        /// Get margins for given instrument, amount and price. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">Instrument name</param>
        /// <param name="amount">Amount, integer for future, float for option. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH.</param>
        /// <param name="price">Price</param>
        /// <returns>Task of Object</returns>
        public async System.Threading.Tasks.Task<Object> PrivateGetMarginsGetAsync (string instrumentName, decimal? amount, decimal? price)
        {
             ApiResponse<Object> localVarResponse = await PrivateGetMarginsGetAsyncWithHttpInfo(instrumentName, amount, price);
             return localVarResponse.Data;

        }

        /// <summary>
        /// Get margins for given instrument, amount and price. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">Instrument name</param>
        /// <param name="amount">Amount, integer for future, float for option. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH.</param>
        /// <param name="price">Price</param>
        /// <returns>Task of ApiResponse (Object)</returns>
        public async System.Threading.Tasks.Task<ApiResponse<Object>> PrivateGetMarginsGetAsyncWithHttpInfo (string instrumentName, decimal? amount, decimal? price)
        {
            // verify the required parameter 'instrumentName' is set
            if (instrumentName == null)
                throw new ApiException(400, "Missing required parameter 'instrumentName' when calling TradingApi->PrivateGetMarginsGet");
            // verify the required parameter 'amount' is set
            if (amount == null)
                throw new ApiException(400, "Missing required parameter 'amount' when calling TradingApi->PrivateGetMarginsGet");
            // verify the required parameter 'price' is set
            if (price == null)
                throw new ApiException(400, "Missing required parameter 'price' when calling TradingApi->PrivateGetMarginsGet");

            var localVarPath = "/private/get_margins";
            var localVarPathParams = new Dictionary<String, String>();
            var localVarQueryParams = new List<KeyValuePair<String, String>>();
            var localVarHeaderParams = new Dictionary<String, String>(this.Configuration.DefaultHeader);
            var localVarFormParams = new Dictionary<String, String>();
            var localVarFileParams = new Dictionary<String, FileParameter>();
            Object localVarPostBody = null;

            // to determine the Content-Type header
            String[] localVarHttpContentTypes = new String[] {
            };
            String localVarHttpContentType = this.Configuration.ApiClient.SelectHeaderContentType(localVarHttpContentTypes);

            // to determine the Accept header
            String[] localVarHttpHeaderAccepts = new String[] {
                "application/json"
            };
            String localVarHttpHeaderAccept = this.Configuration.ApiClient.SelectHeaderAccept(localVarHttpHeaderAccepts);
            if (localVarHttpHeaderAccept != null)
                localVarHeaderParams.Add("Accept", localVarHttpHeaderAccept);

            if (instrumentName != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "instrument_name", instrumentName)); // query parameter
            if (amount != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "amount", amount)); // query parameter
            if (price != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "price", price)); // query parameter

            // authentication (bearerAuth) required
            // http basic authentication required
            if (!String.IsNullOrEmpty(this.Configuration.Username) || !String.IsNullOrEmpty(this.Configuration.Password))
            {
                localVarHeaderParams["Authorization"] = "Basic " + ApiClient.Base64Encode(this.Configuration.Username + ":" + this.Configuration.Password);
            }

            // make the HTTP request
            IRestResponse localVarResponse = (IRestResponse) await this.Configuration.ApiClient.CallApiAsync(localVarPath,
                Method.GET, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarFileParams,
                localVarPathParams, localVarHttpContentType);

            int localVarStatusCode = (int) localVarResponse.StatusCode;

            if (ExceptionFactory != null)
            {
                Exception exception = ExceptionFactory("PrivateGetMarginsGet", localVarResponse);
                if (exception != null) throw exception;
            }

            return new ApiResponse<Object>(localVarStatusCode,
                localVarResponse.Headers.ToDictionary(x => x.Name, x => string.Join(",", x.Value)),
                (Object) this.Configuration.ApiClient.Deserialize(localVarResponse, typeof(Object)));
        }

        /// <summary>
        /// Retrieves list of user&#39;s open orders. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="kind">Instrument kind, if not provided instruments of all kinds are considered (optional)</param>
        /// <param name="type">Order type, default - &#x60;all&#x60; (optional)</param>
        /// <returns>Object</returns>
        public Object PrivateGetOpenOrdersByCurrencyGet (string currency, string kind = null, string type = null)
        {
             ApiResponse<Object> localVarResponse = PrivateGetOpenOrdersByCurrencyGetWithHttpInfo(currency, kind, type);
             return localVarResponse.Data;
        }

        /// <summary>
        /// Retrieves list of user&#39;s open orders. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="kind">Instrument kind, if not provided instruments of all kinds are considered (optional)</param>
        /// <param name="type">Order type, default - &#x60;all&#x60; (optional)</param>
        /// <returns>ApiResponse of Object</returns>
        public ApiResponse< Object > PrivateGetOpenOrdersByCurrencyGetWithHttpInfo (string currency, string kind = null, string type = null)
        {
            // verify the required parameter 'currency' is set
            if (currency == null)
                throw new ApiException(400, "Missing required parameter 'currency' when calling TradingApi->PrivateGetOpenOrdersByCurrencyGet");

            var localVarPath = "/private/get_open_orders_by_currency";
            var localVarPathParams = new Dictionary<String, String>();
            var localVarQueryParams = new List<KeyValuePair<String, String>>();
            var localVarHeaderParams = new Dictionary<String, String>(this.Configuration.DefaultHeader);
            var localVarFormParams = new Dictionary<String, String>();
            var localVarFileParams = new Dictionary<String, FileParameter>();
            Object localVarPostBody = null;

            // to determine the Content-Type header
            String[] localVarHttpContentTypes = new String[] {
            };
            String localVarHttpContentType = this.Configuration.ApiClient.SelectHeaderContentType(localVarHttpContentTypes);

            // to determine the Accept header
            String[] localVarHttpHeaderAccepts = new String[] {
                "application/json"
            };
            String localVarHttpHeaderAccept = this.Configuration.ApiClient.SelectHeaderAccept(localVarHttpHeaderAccepts);
            if (localVarHttpHeaderAccept != null)
                localVarHeaderParams.Add("Accept", localVarHttpHeaderAccept);

            if (currency != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "currency", currency)); // query parameter
            if (kind != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "kind", kind)); // query parameter
            if (type != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "type", type)); // query parameter

            // authentication (bearerAuth) required
            // http basic authentication required
            if (!String.IsNullOrEmpty(this.Configuration.Username) || !String.IsNullOrEmpty(this.Configuration.Password))
            {
                localVarHeaderParams["Authorization"] = "Basic " + ApiClient.Base64Encode(this.Configuration.Username + ":" + this.Configuration.Password);
            }

            // make the HTTP request
            IRestResponse localVarResponse = (IRestResponse) this.Configuration.ApiClient.CallApi(localVarPath,
                Method.GET, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarFileParams,
                localVarPathParams, localVarHttpContentType);

            int localVarStatusCode = (int) localVarResponse.StatusCode;

            if (ExceptionFactory != null)
            {
                Exception exception = ExceptionFactory("PrivateGetOpenOrdersByCurrencyGet", localVarResponse);
                if (exception != null) throw exception;
            }

            return new ApiResponse<Object>(localVarStatusCode,
                localVarResponse.Headers.ToDictionary(x => x.Name, x => string.Join(",", x.Value)),
                (Object) this.Configuration.ApiClient.Deserialize(localVarResponse, typeof(Object)));
        }

        /// <summary>
        /// Retrieves list of user&#39;s open orders. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="kind">Instrument kind, if not provided instruments of all kinds are considered (optional)</param>
        /// <param name="type">Order type, default - &#x60;all&#x60; (optional)</param>
        /// <returns>Task of Object</returns>
        public async System.Threading.Tasks.Task<Object> PrivateGetOpenOrdersByCurrencyGetAsync (string currency, string kind = null, string type = null)
        {
             ApiResponse<Object> localVarResponse = await PrivateGetOpenOrdersByCurrencyGetAsyncWithHttpInfo(currency, kind, type);
             return localVarResponse.Data;

        }

        /// <summary>
        /// Retrieves list of user&#39;s open orders. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="kind">Instrument kind, if not provided instruments of all kinds are considered (optional)</param>
        /// <param name="type">Order type, default - &#x60;all&#x60; (optional)</param>
        /// <returns>Task of ApiResponse (Object)</returns>
        public async System.Threading.Tasks.Task<ApiResponse<Object>> PrivateGetOpenOrdersByCurrencyGetAsyncWithHttpInfo (string currency, string kind = null, string type = null)
        {
            // verify the required parameter 'currency' is set
            if (currency == null)
                throw new ApiException(400, "Missing required parameter 'currency' when calling TradingApi->PrivateGetOpenOrdersByCurrencyGet");

            var localVarPath = "/private/get_open_orders_by_currency";
            var localVarPathParams = new Dictionary<String, String>();
            var localVarQueryParams = new List<KeyValuePair<String, String>>();
            var localVarHeaderParams = new Dictionary<String, String>(this.Configuration.DefaultHeader);
            var localVarFormParams = new Dictionary<String, String>();
            var localVarFileParams = new Dictionary<String, FileParameter>();
            Object localVarPostBody = null;

            // to determine the Content-Type header
            String[] localVarHttpContentTypes = new String[] {
            };
            String localVarHttpContentType = this.Configuration.ApiClient.SelectHeaderContentType(localVarHttpContentTypes);

            // to determine the Accept header
            String[] localVarHttpHeaderAccepts = new String[] {
                "application/json"
            };
            String localVarHttpHeaderAccept = this.Configuration.ApiClient.SelectHeaderAccept(localVarHttpHeaderAccepts);
            if (localVarHttpHeaderAccept != null)
                localVarHeaderParams.Add("Accept", localVarHttpHeaderAccept);

            if (currency != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "currency", currency)); // query parameter
            if (kind != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "kind", kind)); // query parameter
            if (type != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "type", type)); // query parameter

            // authentication (bearerAuth) required
            // http basic authentication required
            if (!String.IsNullOrEmpty(this.Configuration.Username) || !String.IsNullOrEmpty(this.Configuration.Password))
            {
                localVarHeaderParams["Authorization"] = "Basic " + ApiClient.Base64Encode(this.Configuration.Username + ":" + this.Configuration.Password);
            }

            // make the HTTP request
            IRestResponse localVarResponse = (IRestResponse) await this.Configuration.ApiClient.CallApiAsync(localVarPath,
                Method.GET, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarFileParams,
                localVarPathParams, localVarHttpContentType);

            int localVarStatusCode = (int) localVarResponse.StatusCode;

            if (ExceptionFactory != null)
            {
                Exception exception = ExceptionFactory("PrivateGetOpenOrdersByCurrencyGet", localVarResponse);
                if (exception != null) throw exception;
            }

            return new ApiResponse<Object>(localVarStatusCode,
                localVarResponse.Headers.ToDictionary(x => x.Name, x => string.Join(",", x.Value)),
                (Object) this.Configuration.ApiClient.Deserialize(localVarResponse, typeof(Object)));
        }

        /// <summary>
        /// Retrieves list of user&#39;s open orders within given Instrument. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">Instrument name</param>
        /// <param name="type">Order type, default - &#x60;all&#x60; (optional)</param>
        /// <returns>Object</returns>
        public Object PrivateGetOpenOrdersByInstrumentGet (string instrumentName, string type = null)
        {
             ApiResponse<Object> localVarResponse = PrivateGetOpenOrdersByInstrumentGetWithHttpInfo(instrumentName, type);
             return localVarResponse.Data;
        }

        /// <summary>
        /// Retrieves list of user&#39;s open orders within given Instrument. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">Instrument name</param>
        /// <param name="type">Order type, default - &#x60;all&#x60; (optional)</param>
        /// <returns>ApiResponse of Object</returns>
        public ApiResponse< Object > PrivateGetOpenOrdersByInstrumentGetWithHttpInfo (string instrumentName, string type = null)
        {
            // verify the required parameter 'instrumentName' is set
            if (instrumentName == null)
                throw new ApiException(400, "Missing required parameter 'instrumentName' when calling TradingApi->PrivateGetOpenOrdersByInstrumentGet");

            var localVarPath = "/private/get_open_orders_by_instrument";
            var localVarPathParams = new Dictionary<String, String>();
            var localVarQueryParams = new List<KeyValuePair<String, String>>();
            var localVarHeaderParams = new Dictionary<String, String>(this.Configuration.DefaultHeader);
            var localVarFormParams = new Dictionary<String, String>();
            var localVarFileParams = new Dictionary<String, FileParameter>();
            Object localVarPostBody = null;

            // to determine the Content-Type header
            String[] localVarHttpContentTypes = new String[] {
            };
            String localVarHttpContentType = this.Configuration.ApiClient.SelectHeaderContentType(localVarHttpContentTypes);

            // to determine the Accept header
            String[] localVarHttpHeaderAccepts = new String[] {
                "application/json"
            };
            String localVarHttpHeaderAccept = this.Configuration.ApiClient.SelectHeaderAccept(localVarHttpHeaderAccepts);
            if (localVarHttpHeaderAccept != null)
                localVarHeaderParams.Add("Accept", localVarHttpHeaderAccept);

            if (instrumentName != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "instrument_name", instrumentName)); // query parameter
            if (type != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "type", type)); // query parameter

            // authentication (bearerAuth) required
            // http basic authentication required
            if (!String.IsNullOrEmpty(this.Configuration.Username) || !String.IsNullOrEmpty(this.Configuration.Password))
            {
                localVarHeaderParams["Authorization"] = "Basic " + ApiClient.Base64Encode(this.Configuration.Username + ":" + this.Configuration.Password);
            }

            // make the HTTP request
            IRestResponse localVarResponse = (IRestResponse) this.Configuration.ApiClient.CallApi(localVarPath,
                Method.GET, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarFileParams,
                localVarPathParams, localVarHttpContentType);

            int localVarStatusCode = (int) localVarResponse.StatusCode;

            if (ExceptionFactory != null)
            {
                Exception exception = ExceptionFactory("PrivateGetOpenOrdersByInstrumentGet", localVarResponse);
                if (exception != null) throw exception;
            }

            return new ApiResponse<Object>(localVarStatusCode,
                localVarResponse.Headers.ToDictionary(x => x.Name, x => string.Join(",", x.Value)),
                (Object) this.Configuration.ApiClient.Deserialize(localVarResponse, typeof(Object)));
        }

        /// <summary>
        /// Retrieves list of user&#39;s open orders within given Instrument. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">Instrument name</param>
        /// <param name="type">Order type, default - &#x60;all&#x60; (optional)</param>
        /// <returns>Task of Object</returns>
        public async System.Threading.Tasks.Task<Object> PrivateGetOpenOrdersByInstrumentGetAsync (string instrumentName, string type = null)
        {
             ApiResponse<Object> localVarResponse = await PrivateGetOpenOrdersByInstrumentGetAsyncWithHttpInfo(instrumentName, type);
             return localVarResponse.Data;

        }

        /// <summary>
        /// Retrieves list of user&#39;s open orders within given Instrument. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">Instrument name</param>
        /// <param name="type">Order type, default - &#x60;all&#x60; (optional)</param>
        /// <returns>Task of ApiResponse (Object)</returns>
        public async System.Threading.Tasks.Task<ApiResponse<Object>> PrivateGetOpenOrdersByInstrumentGetAsyncWithHttpInfo (string instrumentName, string type = null)
        {
            // verify the required parameter 'instrumentName' is set
            if (instrumentName == null)
                throw new ApiException(400, "Missing required parameter 'instrumentName' when calling TradingApi->PrivateGetOpenOrdersByInstrumentGet");

            var localVarPath = "/private/get_open_orders_by_instrument";
            var localVarPathParams = new Dictionary<String, String>();
            var localVarQueryParams = new List<KeyValuePair<String, String>>();
            var localVarHeaderParams = new Dictionary<String, String>(this.Configuration.DefaultHeader);
            var localVarFormParams = new Dictionary<String, String>();
            var localVarFileParams = new Dictionary<String, FileParameter>();
            Object localVarPostBody = null;

            // to determine the Content-Type header
            String[] localVarHttpContentTypes = new String[] {
            };
            String localVarHttpContentType = this.Configuration.ApiClient.SelectHeaderContentType(localVarHttpContentTypes);

            // to determine the Accept header
            String[] localVarHttpHeaderAccepts = new String[] {
                "application/json"
            };
            String localVarHttpHeaderAccept = this.Configuration.ApiClient.SelectHeaderAccept(localVarHttpHeaderAccepts);
            if (localVarHttpHeaderAccept != null)
                localVarHeaderParams.Add("Accept", localVarHttpHeaderAccept);

            if (instrumentName != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "instrument_name", instrumentName)); // query parameter
            if (type != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "type", type)); // query parameter

            // authentication (bearerAuth) required
            // http basic authentication required
            if (!String.IsNullOrEmpty(this.Configuration.Username) || !String.IsNullOrEmpty(this.Configuration.Password))
            {
                localVarHeaderParams["Authorization"] = "Basic " + ApiClient.Base64Encode(this.Configuration.Username + ":" + this.Configuration.Password);
            }

            // make the HTTP request
            IRestResponse localVarResponse = (IRestResponse) await this.Configuration.ApiClient.CallApiAsync(localVarPath,
                Method.GET, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarFileParams,
                localVarPathParams, localVarHttpContentType);

            int localVarStatusCode = (int) localVarResponse.StatusCode;

            if (ExceptionFactory != null)
            {
                Exception exception = ExceptionFactory("PrivateGetOpenOrdersByInstrumentGet", localVarResponse);
                if (exception != null) throw exception;
            }

            return new ApiResponse<Object>(localVarStatusCode,
                localVarResponse.Headers.ToDictionary(x => x.Name, x => string.Join(",", x.Value)),
                (Object) this.Configuration.ApiClient.Deserialize(localVarResponse, typeof(Object)));
        }

        /// <summary>
        /// Retrieves history of orders that have been partially or fully filled. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="kind">Instrument kind, if not provided instruments of all kinds are considered (optional)</param>
        /// <param name="count">Number of requested items, default - &#x60;20&#x60; (optional)</param>
        /// <param name="offset">The offset for pagination, default - &#x60;0&#x60; (optional)</param>
        /// <param name="includeOld">Include in result orders older than 2 days, default - &#x60;false&#x60; (optional)</param>
        /// <param name="includeUnfilled">Include in result fully unfilled closed orders, default - &#x60;false&#x60; (optional)</param>
        /// <returns>Object</returns>
        public Object PrivateGetOrderHistoryByCurrencyGet (string currency, string kind = null, int? count = null, int? offset = null, bool? includeOld = null, bool? includeUnfilled = null)
        {
             ApiResponse<Object> localVarResponse = PrivateGetOrderHistoryByCurrencyGetWithHttpInfo(currency, kind, count, offset, includeOld, includeUnfilled);
             return localVarResponse.Data;
        }

        /// <summary>
        /// Retrieves history of orders that have been partially or fully filled. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="kind">Instrument kind, if not provided instruments of all kinds are considered (optional)</param>
        /// <param name="count">Number of requested items, default - &#x60;20&#x60; (optional)</param>
        /// <param name="offset">The offset for pagination, default - &#x60;0&#x60; (optional)</param>
        /// <param name="includeOld">Include in result orders older than 2 days, default - &#x60;false&#x60; (optional)</param>
        /// <param name="includeUnfilled">Include in result fully unfilled closed orders, default - &#x60;false&#x60; (optional)</param>
        /// <returns>ApiResponse of Object</returns>
        public ApiResponse< Object > PrivateGetOrderHistoryByCurrencyGetWithHttpInfo (string currency, string kind = null, int? count = null, int? offset = null, bool? includeOld = null, bool? includeUnfilled = null)
        {
            // verify the required parameter 'currency' is set
            if (currency == null)
                throw new ApiException(400, "Missing required parameter 'currency' when calling TradingApi->PrivateGetOrderHistoryByCurrencyGet");

            var localVarPath = "/private/get_order_history_by_currency";
            var localVarPathParams = new Dictionary<String, String>();
            var localVarQueryParams = new List<KeyValuePair<String, String>>();
            var localVarHeaderParams = new Dictionary<String, String>(this.Configuration.DefaultHeader);
            var localVarFormParams = new Dictionary<String, String>();
            var localVarFileParams = new Dictionary<String, FileParameter>();
            Object localVarPostBody = null;

            // to determine the Content-Type header
            String[] localVarHttpContentTypes = new String[] {
            };
            String localVarHttpContentType = this.Configuration.ApiClient.SelectHeaderContentType(localVarHttpContentTypes);

            // to determine the Accept header
            String[] localVarHttpHeaderAccepts = new String[] {
                "application/json"
            };
            String localVarHttpHeaderAccept = this.Configuration.ApiClient.SelectHeaderAccept(localVarHttpHeaderAccepts);
            if (localVarHttpHeaderAccept != null)
                localVarHeaderParams.Add("Accept", localVarHttpHeaderAccept);

            if (currency != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "currency", currency)); // query parameter
            if (kind != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "kind", kind)); // query parameter
            if (count != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "count", count)); // query parameter
            if (offset != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "offset", offset)); // query parameter
            if (includeOld != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "include_old", includeOld)); // query parameter
            if (includeUnfilled != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "include_unfilled", includeUnfilled)); // query parameter

            // authentication (bearerAuth) required
            // http basic authentication required
            if (!String.IsNullOrEmpty(this.Configuration.Username) || !String.IsNullOrEmpty(this.Configuration.Password))
            {
                localVarHeaderParams["Authorization"] = "Basic " + ApiClient.Base64Encode(this.Configuration.Username + ":" + this.Configuration.Password);
            }

            // make the HTTP request
            IRestResponse localVarResponse = (IRestResponse) this.Configuration.ApiClient.CallApi(localVarPath,
                Method.GET, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarFileParams,
                localVarPathParams, localVarHttpContentType);

            int localVarStatusCode = (int) localVarResponse.StatusCode;

            if (ExceptionFactory != null)
            {
                Exception exception = ExceptionFactory("PrivateGetOrderHistoryByCurrencyGet", localVarResponse);
                if (exception != null) throw exception;
            }

            return new ApiResponse<Object>(localVarStatusCode,
                localVarResponse.Headers.ToDictionary(x => x.Name, x => string.Join(",", x.Value)),
                (Object) this.Configuration.ApiClient.Deserialize(localVarResponse, typeof(Object)));
        }

        /// <summary>
        /// Retrieves history of orders that have been partially or fully filled. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="kind">Instrument kind, if not provided instruments of all kinds are considered (optional)</param>
        /// <param name="count">Number of requested items, default - &#x60;20&#x60; (optional)</param>
        /// <param name="offset">The offset for pagination, default - &#x60;0&#x60; (optional)</param>
        /// <param name="includeOld">Include in result orders older than 2 days, default - &#x60;false&#x60; (optional)</param>
        /// <param name="includeUnfilled">Include in result fully unfilled closed orders, default - &#x60;false&#x60; (optional)</param>
        /// <returns>Task of Object</returns>
        public async System.Threading.Tasks.Task<Object> PrivateGetOrderHistoryByCurrencyGetAsync (string currency, string kind = null, int? count = null, int? offset = null, bool? includeOld = null, bool? includeUnfilled = null)
        {
             ApiResponse<Object> localVarResponse = await PrivateGetOrderHistoryByCurrencyGetAsyncWithHttpInfo(currency, kind, count, offset, includeOld, includeUnfilled);
             return localVarResponse.Data;

        }

        /// <summary>
        /// Retrieves history of orders that have been partially or fully filled. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="kind">Instrument kind, if not provided instruments of all kinds are considered (optional)</param>
        /// <param name="count">Number of requested items, default - &#x60;20&#x60; (optional)</param>
        /// <param name="offset">The offset for pagination, default - &#x60;0&#x60; (optional)</param>
        /// <param name="includeOld">Include in result orders older than 2 days, default - &#x60;false&#x60; (optional)</param>
        /// <param name="includeUnfilled">Include in result fully unfilled closed orders, default - &#x60;false&#x60; (optional)</param>
        /// <returns>Task of ApiResponse (Object)</returns>
        public async System.Threading.Tasks.Task<ApiResponse<Object>> PrivateGetOrderHistoryByCurrencyGetAsyncWithHttpInfo (string currency, string kind = null, int? count = null, int? offset = null, bool? includeOld = null, bool? includeUnfilled = null)
        {
            // verify the required parameter 'currency' is set
            if (currency == null)
                throw new ApiException(400, "Missing required parameter 'currency' when calling TradingApi->PrivateGetOrderHistoryByCurrencyGet");

            var localVarPath = "/private/get_order_history_by_currency";
            var localVarPathParams = new Dictionary<String, String>();
            var localVarQueryParams = new List<KeyValuePair<String, String>>();
            var localVarHeaderParams = new Dictionary<String, String>(this.Configuration.DefaultHeader);
            var localVarFormParams = new Dictionary<String, String>();
            var localVarFileParams = new Dictionary<String, FileParameter>();
            Object localVarPostBody = null;

            // to determine the Content-Type header
            String[] localVarHttpContentTypes = new String[] {
            };
            String localVarHttpContentType = this.Configuration.ApiClient.SelectHeaderContentType(localVarHttpContentTypes);

            // to determine the Accept header
            String[] localVarHttpHeaderAccepts = new String[] {
                "application/json"
            };
            String localVarHttpHeaderAccept = this.Configuration.ApiClient.SelectHeaderAccept(localVarHttpHeaderAccepts);
            if (localVarHttpHeaderAccept != null)
                localVarHeaderParams.Add("Accept", localVarHttpHeaderAccept);

            if (currency != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "currency", currency)); // query parameter
            if (kind != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "kind", kind)); // query parameter
            if (count != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "count", count)); // query parameter
            if (offset != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "offset", offset)); // query parameter
            if (includeOld != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "include_old", includeOld)); // query parameter
            if (includeUnfilled != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "include_unfilled", includeUnfilled)); // query parameter

            // authentication (bearerAuth) required
            // http basic authentication required
            if (!String.IsNullOrEmpty(this.Configuration.Username) || !String.IsNullOrEmpty(this.Configuration.Password))
            {
                localVarHeaderParams["Authorization"] = "Basic " + ApiClient.Base64Encode(this.Configuration.Username + ":" + this.Configuration.Password);
            }

            // make the HTTP request
            IRestResponse localVarResponse = (IRestResponse) await this.Configuration.ApiClient.CallApiAsync(localVarPath,
                Method.GET, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarFileParams,
                localVarPathParams, localVarHttpContentType);

            int localVarStatusCode = (int) localVarResponse.StatusCode;

            if (ExceptionFactory != null)
            {
                Exception exception = ExceptionFactory("PrivateGetOrderHistoryByCurrencyGet", localVarResponse);
                if (exception != null) throw exception;
            }

            return new ApiResponse<Object>(localVarStatusCode,
                localVarResponse.Headers.ToDictionary(x => x.Name, x => string.Join(",", x.Value)),
                (Object) this.Configuration.ApiClient.Deserialize(localVarResponse, typeof(Object)));
        }

        /// <summary>
        /// Retrieves history of orders that have been partially or fully filled. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">Instrument name</param>
        /// <param name="count">Number of requested items, default - &#x60;20&#x60; (optional)</param>
        /// <param name="offset">The offset for pagination, default - &#x60;0&#x60; (optional)</param>
        /// <param name="includeOld">Include in result orders older than 2 days, default - &#x60;false&#x60; (optional)</param>
        /// <param name="includeUnfilled">Include in result fully unfilled closed orders, default - &#x60;false&#x60; (optional)</param>
        /// <returns>Object</returns>
        public Object PrivateGetOrderHistoryByInstrumentGet (string instrumentName, int? count = null, int? offset = null, bool? includeOld = null, bool? includeUnfilled = null)
        {
             ApiResponse<Object> localVarResponse = PrivateGetOrderHistoryByInstrumentGetWithHttpInfo(instrumentName, count, offset, includeOld, includeUnfilled);
             return localVarResponse.Data;
        }

        /// <summary>
        /// Retrieves history of orders that have been partially or fully filled. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">Instrument name</param>
        /// <param name="count">Number of requested items, default - &#x60;20&#x60; (optional)</param>
        /// <param name="offset">The offset for pagination, default - &#x60;0&#x60; (optional)</param>
        /// <param name="includeOld">Include in result orders older than 2 days, default - &#x60;false&#x60; (optional)</param>
        /// <param name="includeUnfilled">Include in result fully unfilled closed orders, default - &#x60;false&#x60; (optional)</param>
        /// <returns>ApiResponse of Object</returns>
        public ApiResponse< Object > PrivateGetOrderHistoryByInstrumentGetWithHttpInfo (string instrumentName, int? count = null, int? offset = null, bool? includeOld = null, bool? includeUnfilled = null)
        {
            // verify the required parameter 'instrumentName' is set
            if (instrumentName == null)
                throw new ApiException(400, "Missing required parameter 'instrumentName' when calling TradingApi->PrivateGetOrderHistoryByInstrumentGet");

            var localVarPath = "/private/get_order_history_by_instrument";
            var localVarPathParams = new Dictionary<String, String>();
            var localVarQueryParams = new List<KeyValuePair<String, String>>();
            var localVarHeaderParams = new Dictionary<String, String>(this.Configuration.DefaultHeader);
            var localVarFormParams = new Dictionary<String, String>();
            var localVarFileParams = new Dictionary<String, FileParameter>();
            Object localVarPostBody = null;

            // to determine the Content-Type header
            String[] localVarHttpContentTypes = new String[] {
            };
            String localVarHttpContentType = this.Configuration.ApiClient.SelectHeaderContentType(localVarHttpContentTypes);

            // to determine the Accept header
            String[] localVarHttpHeaderAccepts = new String[] {
                "application/json"
            };
            String localVarHttpHeaderAccept = this.Configuration.ApiClient.SelectHeaderAccept(localVarHttpHeaderAccepts);
            if (localVarHttpHeaderAccept != null)
                localVarHeaderParams.Add("Accept", localVarHttpHeaderAccept);

            if (instrumentName != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "instrument_name", instrumentName)); // query parameter
            if (count != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "count", count)); // query parameter
            if (offset != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "offset", offset)); // query parameter
            if (includeOld != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "include_old", includeOld)); // query parameter
            if (includeUnfilled != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "include_unfilled", includeUnfilled)); // query parameter

            // authentication (bearerAuth) required
            // http basic authentication required
            if (!String.IsNullOrEmpty(this.Configuration.Username) || !String.IsNullOrEmpty(this.Configuration.Password))
            {
                localVarHeaderParams["Authorization"] = "Basic " + ApiClient.Base64Encode(this.Configuration.Username + ":" + this.Configuration.Password);
            }

            // make the HTTP request
            IRestResponse localVarResponse = (IRestResponse) this.Configuration.ApiClient.CallApi(localVarPath,
                Method.GET, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarFileParams,
                localVarPathParams, localVarHttpContentType);

            int localVarStatusCode = (int) localVarResponse.StatusCode;

            if (ExceptionFactory != null)
            {
                Exception exception = ExceptionFactory("PrivateGetOrderHistoryByInstrumentGet", localVarResponse);
                if (exception != null) throw exception;
            }

            return new ApiResponse<Object>(localVarStatusCode,
                localVarResponse.Headers.ToDictionary(x => x.Name, x => string.Join(",", x.Value)),
                (Object) this.Configuration.ApiClient.Deserialize(localVarResponse, typeof(Object)));
        }

        /// <summary>
        /// Retrieves history of orders that have been partially or fully filled. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">Instrument name</param>
        /// <param name="count">Number of requested items, default - &#x60;20&#x60; (optional)</param>
        /// <param name="offset">The offset for pagination, default - &#x60;0&#x60; (optional)</param>
        /// <param name="includeOld">Include in result orders older than 2 days, default - &#x60;false&#x60; (optional)</param>
        /// <param name="includeUnfilled">Include in result fully unfilled closed orders, default - &#x60;false&#x60; (optional)</param>
        /// <returns>Task of Object</returns>
        public async System.Threading.Tasks.Task<Object> PrivateGetOrderHistoryByInstrumentGetAsync (string instrumentName, int? count = null, int? offset = null, bool? includeOld = null, bool? includeUnfilled = null)
        {
             ApiResponse<Object> localVarResponse = await PrivateGetOrderHistoryByInstrumentGetAsyncWithHttpInfo(instrumentName, count, offset, includeOld, includeUnfilled);
             return localVarResponse.Data;

        }

        /// <summary>
        /// Retrieves history of orders that have been partially or fully filled. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">Instrument name</param>
        /// <param name="count">Number of requested items, default - &#x60;20&#x60; (optional)</param>
        /// <param name="offset">The offset for pagination, default - &#x60;0&#x60; (optional)</param>
        /// <param name="includeOld">Include in result orders older than 2 days, default - &#x60;false&#x60; (optional)</param>
        /// <param name="includeUnfilled">Include in result fully unfilled closed orders, default - &#x60;false&#x60; (optional)</param>
        /// <returns>Task of ApiResponse (Object)</returns>
        public async System.Threading.Tasks.Task<ApiResponse<Object>> PrivateGetOrderHistoryByInstrumentGetAsyncWithHttpInfo (string instrumentName, int? count = null, int? offset = null, bool? includeOld = null, bool? includeUnfilled = null)
        {
            // verify the required parameter 'instrumentName' is set
            if (instrumentName == null)
                throw new ApiException(400, "Missing required parameter 'instrumentName' when calling TradingApi->PrivateGetOrderHistoryByInstrumentGet");

            var localVarPath = "/private/get_order_history_by_instrument";
            var localVarPathParams = new Dictionary<String, String>();
            var localVarQueryParams = new List<KeyValuePair<String, String>>();
            var localVarHeaderParams = new Dictionary<String, String>(this.Configuration.DefaultHeader);
            var localVarFormParams = new Dictionary<String, String>();
            var localVarFileParams = new Dictionary<String, FileParameter>();
            Object localVarPostBody = null;

            // to determine the Content-Type header
            String[] localVarHttpContentTypes = new String[] {
            };
            String localVarHttpContentType = this.Configuration.ApiClient.SelectHeaderContentType(localVarHttpContentTypes);

            // to determine the Accept header
            String[] localVarHttpHeaderAccepts = new String[] {
                "application/json"
            };
            String localVarHttpHeaderAccept = this.Configuration.ApiClient.SelectHeaderAccept(localVarHttpHeaderAccepts);
            if (localVarHttpHeaderAccept != null)
                localVarHeaderParams.Add("Accept", localVarHttpHeaderAccept);

            if (instrumentName != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "instrument_name", instrumentName)); // query parameter
            if (count != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "count", count)); // query parameter
            if (offset != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "offset", offset)); // query parameter
            if (includeOld != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "include_old", includeOld)); // query parameter
            if (includeUnfilled != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "include_unfilled", includeUnfilled)); // query parameter

            // authentication (bearerAuth) required
            // http basic authentication required
            if (!String.IsNullOrEmpty(this.Configuration.Username) || !String.IsNullOrEmpty(this.Configuration.Password))
            {
                localVarHeaderParams["Authorization"] = "Basic " + ApiClient.Base64Encode(this.Configuration.Username + ":" + this.Configuration.Password);
            }

            // make the HTTP request
            IRestResponse localVarResponse = (IRestResponse) await this.Configuration.ApiClient.CallApiAsync(localVarPath,
                Method.GET, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarFileParams,
                localVarPathParams, localVarHttpContentType);

            int localVarStatusCode = (int) localVarResponse.StatusCode;

            if (ExceptionFactory != null)
            {
                Exception exception = ExceptionFactory("PrivateGetOrderHistoryByInstrumentGet", localVarResponse);
                if (exception != null) throw exception;
            }

            return new ApiResponse<Object>(localVarStatusCode,
                localVarResponse.Headers.ToDictionary(x => x.Name, x => string.Join(",", x.Value)),
                (Object) this.Configuration.ApiClient.Deserialize(localVarResponse, typeof(Object)));
        }

        /// <summary>
        /// Retrieves initial margins of given orders 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="ids">Ids of orders</param>
        /// <returns>Object</returns>
        public Object PrivateGetOrderMarginByIdsGet (List<string> ids)
        {
             ApiResponse<Object> localVarResponse = PrivateGetOrderMarginByIdsGetWithHttpInfo(ids);
             return localVarResponse.Data;
        }

        /// <summary>
        /// Retrieves initial margins of given orders 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="ids">Ids of orders</param>
        /// <returns>ApiResponse of Object</returns>
        public ApiResponse< Object > PrivateGetOrderMarginByIdsGetWithHttpInfo (List<string> ids)
        {
            // verify the required parameter 'ids' is set
            if (ids == null)
                throw new ApiException(400, "Missing required parameter 'ids' when calling TradingApi->PrivateGetOrderMarginByIdsGet");

            var localVarPath = "/private/get_order_margin_by_ids";
            var localVarPathParams = new Dictionary<String, String>();
            var localVarQueryParams = new List<KeyValuePair<String, String>>();
            var localVarHeaderParams = new Dictionary<String, String>(this.Configuration.DefaultHeader);
            var localVarFormParams = new Dictionary<String, String>();
            var localVarFileParams = new Dictionary<String, FileParameter>();
            Object localVarPostBody = null;

            // to determine the Content-Type header
            String[] localVarHttpContentTypes = new String[] {
            };
            String localVarHttpContentType = this.Configuration.ApiClient.SelectHeaderContentType(localVarHttpContentTypes);

            // to determine the Accept header
            String[] localVarHttpHeaderAccepts = new String[] {
                "application/json"
            };
            String localVarHttpHeaderAccept = this.Configuration.ApiClient.SelectHeaderAccept(localVarHttpHeaderAccepts);
            if (localVarHttpHeaderAccept != null)
                localVarHeaderParams.Add("Accept", localVarHttpHeaderAccept);

            if (ids != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("multi", "ids", ids)); // query parameter

            // authentication (bearerAuth) required
            // http basic authentication required
            if (!String.IsNullOrEmpty(this.Configuration.Username) || !String.IsNullOrEmpty(this.Configuration.Password))
            {
                localVarHeaderParams["Authorization"] = "Basic " + ApiClient.Base64Encode(this.Configuration.Username + ":" + this.Configuration.Password);
            }

            // make the HTTP request
            IRestResponse localVarResponse = (IRestResponse) this.Configuration.ApiClient.CallApi(localVarPath,
                Method.GET, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarFileParams,
                localVarPathParams, localVarHttpContentType);

            int localVarStatusCode = (int) localVarResponse.StatusCode;

            if (ExceptionFactory != null)
            {
                Exception exception = ExceptionFactory("PrivateGetOrderMarginByIdsGet", localVarResponse);
                if (exception != null) throw exception;
            }

            return new ApiResponse<Object>(localVarStatusCode,
                localVarResponse.Headers.ToDictionary(x => x.Name, x => string.Join(",", x.Value)),
                (Object) this.Configuration.ApiClient.Deserialize(localVarResponse, typeof(Object)));
        }

        /// <summary>
        /// Retrieves initial margins of given orders 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="ids">Ids of orders</param>
        /// <returns>Task of Object</returns>
        public async System.Threading.Tasks.Task<Object> PrivateGetOrderMarginByIdsGetAsync (List<string> ids)
        {
             ApiResponse<Object> localVarResponse = await PrivateGetOrderMarginByIdsGetAsyncWithHttpInfo(ids);
             return localVarResponse.Data;

        }

        /// <summary>
        /// Retrieves initial margins of given orders 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="ids">Ids of orders</param>
        /// <returns>Task of ApiResponse (Object)</returns>
        public async System.Threading.Tasks.Task<ApiResponse<Object>> PrivateGetOrderMarginByIdsGetAsyncWithHttpInfo (List<string> ids)
        {
            // verify the required parameter 'ids' is set
            if (ids == null)
                throw new ApiException(400, "Missing required parameter 'ids' when calling TradingApi->PrivateGetOrderMarginByIdsGet");

            var localVarPath = "/private/get_order_margin_by_ids";
            var localVarPathParams = new Dictionary<String, String>();
            var localVarQueryParams = new List<KeyValuePair<String, String>>();
            var localVarHeaderParams = new Dictionary<String, String>(this.Configuration.DefaultHeader);
            var localVarFormParams = new Dictionary<String, String>();
            var localVarFileParams = new Dictionary<String, FileParameter>();
            Object localVarPostBody = null;

            // to determine the Content-Type header
            String[] localVarHttpContentTypes = new String[] {
            };
            String localVarHttpContentType = this.Configuration.ApiClient.SelectHeaderContentType(localVarHttpContentTypes);

            // to determine the Accept header
            String[] localVarHttpHeaderAccepts = new String[] {
                "application/json"
            };
            String localVarHttpHeaderAccept = this.Configuration.ApiClient.SelectHeaderAccept(localVarHttpHeaderAccepts);
            if (localVarHttpHeaderAccept != null)
                localVarHeaderParams.Add("Accept", localVarHttpHeaderAccept);

            if (ids != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("multi", "ids", ids)); // query parameter

            // authentication (bearerAuth) required
            // http basic authentication required
            if (!String.IsNullOrEmpty(this.Configuration.Username) || !String.IsNullOrEmpty(this.Configuration.Password))
            {
                localVarHeaderParams["Authorization"] = "Basic " + ApiClient.Base64Encode(this.Configuration.Username + ":" + this.Configuration.Password);
            }

            // make the HTTP request
            IRestResponse localVarResponse = (IRestResponse) await this.Configuration.ApiClient.CallApiAsync(localVarPath,
                Method.GET, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarFileParams,
                localVarPathParams, localVarHttpContentType);

            int localVarStatusCode = (int) localVarResponse.StatusCode;

            if (ExceptionFactory != null)
            {
                Exception exception = ExceptionFactory("PrivateGetOrderMarginByIdsGet", localVarResponse);
                if (exception != null) throw exception;
            }

            return new ApiResponse<Object>(localVarStatusCode,
                localVarResponse.Headers.ToDictionary(x => x.Name, x => string.Join(",", x.Value)),
                (Object) this.Configuration.ApiClient.Deserialize(localVarResponse, typeof(Object)));
        }

        /// <summary>
        /// Retrieve the current state of an order. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="orderId">The order id</param>
        /// <returns>Object</returns>
        public Object PrivateGetOrderStateGet (string orderId)
        {
             ApiResponse<Object> localVarResponse = PrivateGetOrderStateGetWithHttpInfo(orderId);
             return localVarResponse.Data;
        }

        /// <summary>
        /// Retrieve the current state of an order. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="orderId">The order id</param>
        /// <returns>ApiResponse of Object</returns>
        public ApiResponse< Object > PrivateGetOrderStateGetWithHttpInfo (string orderId)
        {
            // verify the required parameter 'orderId' is set
            if (orderId == null)
                throw new ApiException(400, "Missing required parameter 'orderId' when calling TradingApi->PrivateGetOrderStateGet");

            var localVarPath = "/private/get_order_state";
            var localVarPathParams = new Dictionary<String, String>();
            var localVarQueryParams = new List<KeyValuePair<String, String>>();
            var localVarHeaderParams = new Dictionary<String, String>(this.Configuration.DefaultHeader);
            var localVarFormParams = new Dictionary<String, String>();
            var localVarFileParams = new Dictionary<String, FileParameter>();
            Object localVarPostBody = null;

            // to determine the Content-Type header
            String[] localVarHttpContentTypes = new String[] {
            };
            String localVarHttpContentType = this.Configuration.ApiClient.SelectHeaderContentType(localVarHttpContentTypes);

            // to determine the Accept header
            String[] localVarHttpHeaderAccepts = new String[] {
                "application/json"
            };
            String localVarHttpHeaderAccept = this.Configuration.ApiClient.SelectHeaderAccept(localVarHttpHeaderAccepts);
            if (localVarHttpHeaderAccept != null)
                localVarHeaderParams.Add("Accept", localVarHttpHeaderAccept);

            if (orderId != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "order_id", orderId)); // query parameter

            // authentication (bearerAuth) required
            // http basic authentication required
            if (!String.IsNullOrEmpty(this.Configuration.Username) || !String.IsNullOrEmpty(this.Configuration.Password))
            {
                localVarHeaderParams["Authorization"] = "Basic " + ApiClient.Base64Encode(this.Configuration.Username + ":" + this.Configuration.Password);
            }

            // make the HTTP request
            IRestResponse localVarResponse = (IRestResponse) this.Configuration.ApiClient.CallApi(localVarPath,
                Method.GET, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarFileParams,
                localVarPathParams, localVarHttpContentType);

            int localVarStatusCode = (int) localVarResponse.StatusCode;

            if (ExceptionFactory != null)
            {
                Exception exception = ExceptionFactory("PrivateGetOrderStateGet", localVarResponse);
                if (exception != null) throw exception;
            }

            return new ApiResponse<Object>(localVarStatusCode,
                localVarResponse.Headers.ToDictionary(x => x.Name, x => string.Join(",", x.Value)),
                (Object) this.Configuration.ApiClient.Deserialize(localVarResponse, typeof(Object)));
        }

        /// <summary>
        /// Retrieve the current state of an order. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="orderId">The order id</param>
        /// <returns>Task of Object</returns>
        public async System.Threading.Tasks.Task<Object> PrivateGetOrderStateGetAsync (string orderId)
        {
             ApiResponse<Object> localVarResponse = await PrivateGetOrderStateGetAsyncWithHttpInfo(orderId);
             return localVarResponse.Data;

        }

        /// <summary>
        /// Retrieve the current state of an order. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="orderId">The order id</param>
        /// <returns>Task of ApiResponse (Object)</returns>
        public async System.Threading.Tasks.Task<ApiResponse<Object>> PrivateGetOrderStateGetAsyncWithHttpInfo (string orderId)
        {
            // verify the required parameter 'orderId' is set
            if (orderId == null)
                throw new ApiException(400, "Missing required parameter 'orderId' when calling TradingApi->PrivateGetOrderStateGet");

            var localVarPath = "/private/get_order_state";
            var localVarPathParams = new Dictionary<String, String>();
            var localVarQueryParams = new List<KeyValuePair<String, String>>();
            var localVarHeaderParams = new Dictionary<String, String>(this.Configuration.DefaultHeader);
            var localVarFormParams = new Dictionary<String, String>();
            var localVarFileParams = new Dictionary<String, FileParameter>();
            Object localVarPostBody = null;

            // to determine the Content-Type header
            String[] localVarHttpContentTypes = new String[] {
            };
            String localVarHttpContentType = this.Configuration.ApiClient.SelectHeaderContentType(localVarHttpContentTypes);

            // to determine the Accept header
            String[] localVarHttpHeaderAccepts = new String[] {
                "application/json"
            };
            String localVarHttpHeaderAccept = this.Configuration.ApiClient.SelectHeaderAccept(localVarHttpHeaderAccepts);
            if (localVarHttpHeaderAccept != null)
                localVarHeaderParams.Add("Accept", localVarHttpHeaderAccept);

            if (orderId != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "order_id", orderId)); // query parameter

            // authentication (bearerAuth) required
            // http basic authentication required
            if (!String.IsNullOrEmpty(this.Configuration.Username) || !String.IsNullOrEmpty(this.Configuration.Password))
            {
                localVarHeaderParams["Authorization"] = "Basic " + ApiClient.Base64Encode(this.Configuration.Username + ":" + this.Configuration.Password);
            }

            // make the HTTP request
            IRestResponse localVarResponse = (IRestResponse) await this.Configuration.ApiClient.CallApiAsync(localVarPath,
                Method.GET, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarFileParams,
                localVarPathParams, localVarHttpContentType);

            int localVarStatusCode = (int) localVarResponse.StatusCode;

            if (ExceptionFactory != null)
            {
                Exception exception = ExceptionFactory("PrivateGetOrderStateGet", localVarResponse);
                if (exception != null) throw exception;
            }

            return new ApiResponse<Object>(localVarStatusCode,
                localVarResponse.Headers.ToDictionary(x => x.Name, x => string.Join(",", x.Value)),
                (Object) this.Configuration.ApiClient.Deserialize(localVarResponse, typeof(Object)));
        }

        /// <summary>
        /// Retrieves settlement, delivery and bankruptcy events that have affected your account. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="type">Settlement type (optional)</param>
        /// <param name="count">Number of requested items, default - &#x60;20&#x60; (optional)</param>
        /// <returns>Object</returns>
        public Object PrivateGetSettlementHistoryByCurrencyGet (string currency, string type = null, int? count = null)
        {
             ApiResponse<Object> localVarResponse = PrivateGetSettlementHistoryByCurrencyGetWithHttpInfo(currency, type, count);
             return localVarResponse.Data;
        }

        /// <summary>
        /// Retrieves settlement, delivery and bankruptcy events that have affected your account. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="type">Settlement type (optional)</param>
        /// <param name="count">Number of requested items, default - &#x60;20&#x60; (optional)</param>
        /// <returns>ApiResponse of Object</returns>
        public ApiResponse< Object > PrivateGetSettlementHistoryByCurrencyGetWithHttpInfo (string currency, string type = null, int? count = null)
        {
            // verify the required parameter 'currency' is set
            if (currency == null)
                throw new ApiException(400, "Missing required parameter 'currency' when calling TradingApi->PrivateGetSettlementHistoryByCurrencyGet");

            var localVarPath = "/private/get_settlement_history_by_currency";
            var localVarPathParams = new Dictionary<String, String>();
            var localVarQueryParams = new List<KeyValuePair<String, String>>();
            var localVarHeaderParams = new Dictionary<String, String>(this.Configuration.DefaultHeader);
            var localVarFormParams = new Dictionary<String, String>();
            var localVarFileParams = new Dictionary<String, FileParameter>();
            Object localVarPostBody = null;

            // to determine the Content-Type header
            String[] localVarHttpContentTypes = new String[] {
            };
            String localVarHttpContentType = this.Configuration.ApiClient.SelectHeaderContentType(localVarHttpContentTypes);

            // to determine the Accept header
            String[] localVarHttpHeaderAccepts = new String[] {
                "application/json"
            };
            String localVarHttpHeaderAccept = this.Configuration.ApiClient.SelectHeaderAccept(localVarHttpHeaderAccepts);
            if (localVarHttpHeaderAccept != null)
                localVarHeaderParams.Add("Accept", localVarHttpHeaderAccept);

            if (currency != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "currency", currency)); // query parameter
            if (type != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "type", type)); // query parameter
            if (count != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "count", count)); // query parameter

            // authentication (bearerAuth) required
            // http basic authentication required
            if (!String.IsNullOrEmpty(this.Configuration.Username) || !String.IsNullOrEmpty(this.Configuration.Password))
            {
                localVarHeaderParams["Authorization"] = "Basic " + ApiClient.Base64Encode(this.Configuration.Username + ":" + this.Configuration.Password);
            }

            // make the HTTP request
            IRestResponse localVarResponse = (IRestResponse) this.Configuration.ApiClient.CallApi(localVarPath,
                Method.GET, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarFileParams,
                localVarPathParams, localVarHttpContentType);

            int localVarStatusCode = (int) localVarResponse.StatusCode;

            if (ExceptionFactory != null)
            {
                Exception exception = ExceptionFactory("PrivateGetSettlementHistoryByCurrencyGet", localVarResponse);
                if (exception != null) throw exception;
            }

            return new ApiResponse<Object>(localVarStatusCode,
                localVarResponse.Headers.ToDictionary(x => x.Name, x => string.Join(",", x.Value)),
                (Object) this.Configuration.ApiClient.Deserialize(localVarResponse, typeof(Object)));
        }

        /// <summary>
        /// Retrieves settlement, delivery and bankruptcy events that have affected your account. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="type">Settlement type (optional)</param>
        /// <param name="count">Number of requested items, default - &#x60;20&#x60; (optional)</param>
        /// <returns>Task of Object</returns>
        public async System.Threading.Tasks.Task<Object> PrivateGetSettlementHistoryByCurrencyGetAsync (string currency, string type = null, int? count = null)
        {
             ApiResponse<Object> localVarResponse = await PrivateGetSettlementHistoryByCurrencyGetAsyncWithHttpInfo(currency, type, count);
             return localVarResponse.Data;

        }

        /// <summary>
        /// Retrieves settlement, delivery and bankruptcy events that have affected your account. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="type">Settlement type (optional)</param>
        /// <param name="count">Number of requested items, default - &#x60;20&#x60; (optional)</param>
        /// <returns>Task of ApiResponse (Object)</returns>
        public async System.Threading.Tasks.Task<ApiResponse<Object>> PrivateGetSettlementHistoryByCurrencyGetAsyncWithHttpInfo (string currency, string type = null, int? count = null)
        {
            // verify the required parameter 'currency' is set
            if (currency == null)
                throw new ApiException(400, "Missing required parameter 'currency' when calling TradingApi->PrivateGetSettlementHistoryByCurrencyGet");

            var localVarPath = "/private/get_settlement_history_by_currency";
            var localVarPathParams = new Dictionary<String, String>();
            var localVarQueryParams = new List<KeyValuePair<String, String>>();
            var localVarHeaderParams = new Dictionary<String, String>(this.Configuration.DefaultHeader);
            var localVarFormParams = new Dictionary<String, String>();
            var localVarFileParams = new Dictionary<String, FileParameter>();
            Object localVarPostBody = null;

            // to determine the Content-Type header
            String[] localVarHttpContentTypes = new String[] {
            };
            String localVarHttpContentType = this.Configuration.ApiClient.SelectHeaderContentType(localVarHttpContentTypes);

            // to determine the Accept header
            String[] localVarHttpHeaderAccepts = new String[] {
                "application/json"
            };
            String localVarHttpHeaderAccept = this.Configuration.ApiClient.SelectHeaderAccept(localVarHttpHeaderAccepts);
            if (localVarHttpHeaderAccept != null)
                localVarHeaderParams.Add("Accept", localVarHttpHeaderAccept);

            if (currency != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "currency", currency)); // query parameter
            if (type != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "type", type)); // query parameter
            if (count != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "count", count)); // query parameter

            // authentication (bearerAuth) required
            // http basic authentication required
            if (!String.IsNullOrEmpty(this.Configuration.Username) || !String.IsNullOrEmpty(this.Configuration.Password))
            {
                localVarHeaderParams["Authorization"] = "Basic " + ApiClient.Base64Encode(this.Configuration.Username + ":" + this.Configuration.Password);
            }

            // make the HTTP request
            IRestResponse localVarResponse = (IRestResponse) await this.Configuration.ApiClient.CallApiAsync(localVarPath,
                Method.GET, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarFileParams,
                localVarPathParams, localVarHttpContentType);

            int localVarStatusCode = (int) localVarResponse.StatusCode;

            if (ExceptionFactory != null)
            {
                Exception exception = ExceptionFactory("PrivateGetSettlementHistoryByCurrencyGet", localVarResponse);
                if (exception != null) throw exception;
            }

            return new ApiResponse<Object>(localVarStatusCode,
                localVarResponse.Headers.ToDictionary(x => x.Name, x => string.Join(",", x.Value)),
                (Object) this.Configuration.ApiClient.Deserialize(localVarResponse, typeof(Object)));
        }

        /// <summary>
        /// Retrieves public settlement, delivery and bankruptcy events filtered by instrument name 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">Instrument name</param>
        /// <param name="type">Settlement type (optional)</param>
        /// <param name="count">Number of requested items, default - &#x60;20&#x60; (optional)</param>
        /// <returns>Object</returns>
        public Object PrivateGetSettlementHistoryByInstrumentGet (string instrumentName, string type = null, int? count = null)
        {
             ApiResponse<Object> localVarResponse = PrivateGetSettlementHistoryByInstrumentGetWithHttpInfo(instrumentName, type, count);
             return localVarResponse.Data;
        }

        /// <summary>
        /// Retrieves public settlement, delivery and bankruptcy events filtered by instrument name 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">Instrument name</param>
        /// <param name="type">Settlement type (optional)</param>
        /// <param name="count">Number of requested items, default - &#x60;20&#x60; (optional)</param>
        /// <returns>ApiResponse of Object</returns>
        public ApiResponse< Object > PrivateGetSettlementHistoryByInstrumentGetWithHttpInfo (string instrumentName, string type = null, int? count = null)
        {
            // verify the required parameter 'instrumentName' is set
            if (instrumentName == null)
                throw new ApiException(400, "Missing required parameter 'instrumentName' when calling TradingApi->PrivateGetSettlementHistoryByInstrumentGet");

            var localVarPath = "/private/get_settlement_history_by_instrument";
            var localVarPathParams = new Dictionary<String, String>();
            var localVarQueryParams = new List<KeyValuePair<String, String>>();
            var localVarHeaderParams = new Dictionary<String, String>(this.Configuration.DefaultHeader);
            var localVarFormParams = new Dictionary<String, String>();
            var localVarFileParams = new Dictionary<String, FileParameter>();
            Object localVarPostBody = null;

            // to determine the Content-Type header
            String[] localVarHttpContentTypes = new String[] {
            };
            String localVarHttpContentType = this.Configuration.ApiClient.SelectHeaderContentType(localVarHttpContentTypes);

            // to determine the Accept header
            String[] localVarHttpHeaderAccepts = new String[] {
                "application/json"
            };
            String localVarHttpHeaderAccept = this.Configuration.ApiClient.SelectHeaderAccept(localVarHttpHeaderAccepts);
            if (localVarHttpHeaderAccept != null)
                localVarHeaderParams.Add("Accept", localVarHttpHeaderAccept);

            if (instrumentName != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "instrument_name", instrumentName)); // query parameter
            if (type != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "type", type)); // query parameter
            if (count != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "count", count)); // query parameter

            // authentication (bearerAuth) required
            // http basic authentication required
            if (!String.IsNullOrEmpty(this.Configuration.Username) || !String.IsNullOrEmpty(this.Configuration.Password))
            {
                localVarHeaderParams["Authorization"] = "Basic " + ApiClient.Base64Encode(this.Configuration.Username + ":" + this.Configuration.Password);
            }

            // make the HTTP request
            IRestResponse localVarResponse = (IRestResponse) this.Configuration.ApiClient.CallApi(localVarPath,
                Method.GET, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarFileParams,
                localVarPathParams, localVarHttpContentType);

            int localVarStatusCode = (int) localVarResponse.StatusCode;

            if (ExceptionFactory != null)
            {
                Exception exception = ExceptionFactory("PrivateGetSettlementHistoryByInstrumentGet", localVarResponse);
                if (exception != null) throw exception;
            }

            return new ApiResponse<Object>(localVarStatusCode,
                localVarResponse.Headers.ToDictionary(x => x.Name, x => string.Join(",", x.Value)),
                (Object) this.Configuration.ApiClient.Deserialize(localVarResponse, typeof(Object)));
        }

        /// <summary>
        /// Retrieves public settlement, delivery and bankruptcy events filtered by instrument name 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">Instrument name</param>
        /// <param name="type">Settlement type (optional)</param>
        /// <param name="count">Number of requested items, default - &#x60;20&#x60; (optional)</param>
        /// <returns>Task of Object</returns>
        public async System.Threading.Tasks.Task<Object> PrivateGetSettlementHistoryByInstrumentGetAsync (string instrumentName, string type = null, int? count = null)
        {
             ApiResponse<Object> localVarResponse = await PrivateGetSettlementHistoryByInstrumentGetAsyncWithHttpInfo(instrumentName, type, count);
             return localVarResponse.Data;

        }

        /// <summary>
        /// Retrieves public settlement, delivery and bankruptcy events filtered by instrument name 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">Instrument name</param>
        /// <param name="type">Settlement type (optional)</param>
        /// <param name="count">Number of requested items, default - &#x60;20&#x60; (optional)</param>
        /// <returns>Task of ApiResponse (Object)</returns>
        public async System.Threading.Tasks.Task<ApiResponse<Object>> PrivateGetSettlementHistoryByInstrumentGetAsyncWithHttpInfo (string instrumentName, string type = null, int? count = null)
        {
            // verify the required parameter 'instrumentName' is set
            if (instrumentName == null)
                throw new ApiException(400, "Missing required parameter 'instrumentName' when calling TradingApi->PrivateGetSettlementHistoryByInstrumentGet");

            var localVarPath = "/private/get_settlement_history_by_instrument";
            var localVarPathParams = new Dictionary<String, String>();
            var localVarQueryParams = new List<KeyValuePair<String, String>>();
            var localVarHeaderParams = new Dictionary<String, String>(this.Configuration.DefaultHeader);
            var localVarFormParams = new Dictionary<String, String>();
            var localVarFileParams = new Dictionary<String, FileParameter>();
            Object localVarPostBody = null;

            // to determine the Content-Type header
            String[] localVarHttpContentTypes = new String[] {
            };
            String localVarHttpContentType = this.Configuration.ApiClient.SelectHeaderContentType(localVarHttpContentTypes);

            // to determine the Accept header
            String[] localVarHttpHeaderAccepts = new String[] {
                "application/json"
            };
            String localVarHttpHeaderAccept = this.Configuration.ApiClient.SelectHeaderAccept(localVarHttpHeaderAccepts);
            if (localVarHttpHeaderAccept != null)
                localVarHeaderParams.Add("Accept", localVarHttpHeaderAccept);

            if (instrumentName != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "instrument_name", instrumentName)); // query parameter
            if (type != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "type", type)); // query parameter
            if (count != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "count", count)); // query parameter

            // authentication (bearerAuth) required
            // http basic authentication required
            if (!String.IsNullOrEmpty(this.Configuration.Username) || !String.IsNullOrEmpty(this.Configuration.Password))
            {
                localVarHeaderParams["Authorization"] = "Basic " + ApiClient.Base64Encode(this.Configuration.Username + ":" + this.Configuration.Password);
            }

            // make the HTTP request
            IRestResponse localVarResponse = (IRestResponse) await this.Configuration.ApiClient.CallApiAsync(localVarPath,
                Method.GET, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarFileParams,
                localVarPathParams, localVarHttpContentType);

            int localVarStatusCode = (int) localVarResponse.StatusCode;

            if (ExceptionFactory != null)
            {
                Exception exception = ExceptionFactory("PrivateGetSettlementHistoryByInstrumentGet", localVarResponse);
                if (exception != null) throw exception;
            }

            return new ApiResponse<Object>(localVarStatusCode,
                localVarResponse.Headers.ToDictionary(x => x.Name, x => string.Join(",", x.Value)),
                (Object) this.Configuration.ApiClient.Deserialize(localVarResponse, typeof(Object)));
        }

        /// <summary>
        /// Retrieve the latest user trades that have occurred for instruments in a specific currency symbol and within given time range. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="startTimestamp">The earliest timestamp to return result for</param>
        /// <param name="endTimestamp">The most recent timestamp to return result for</param>
        /// <param name="kind">Instrument kind, if not provided instruments of all kinds are considered (optional)</param>
        /// <param name="count">Number of requested items, default - &#x60;10&#x60; (optional)</param>
        /// <param name="includeOld">Include trades older than 7 days, default - &#x60;false&#x60; (optional)</param>
        /// <param name="sorting">Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) (optional)</param>
        /// <returns>Object</returns>
        public Object PrivateGetUserTradesByCurrencyAndTimeGet (string currency, int? startTimestamp, int? endTimestamp, string kind = null, int? count = null, bool? includeOld = null, string sorting = null)
        {
             ApiResponse<Object> localVarResponse = PrivateGetUserTradesByCurrencyAndTimeGetWithHttpInfo(currency, startTimestamp, endTimestamp, kind, count, includeOld, sorting);
             return localVarResponse.Data;
        }

        /// <summary>
        /// Retrieve the latest user trades that have occurred for instruments in a specific currency symbol and within given time range. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="startTimestamp">The earliest timestamp to return result for</param>
        /// <param name="endTimestamp">The most recent timestamp to return result for</param>
        /// <param name="kind">Instrument kind, if not provided instruments of all kinds are considered (optional)</param>
        /// <param name="count">Number of requested items, default - &#x60;10&#x60; (optional)</param>
        /// <param name="includeOld">Include trades older than 7 days, default - &#x60;false&#x60; (optional)</param>
        /// <param name="sorting">Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) (optional)</param>
        /// <returns>ApiResponse of Object</returns>
        public ApiResponse< Object > PrivateGetUserTradesByCurrencyAndTimeGetWithHttpInfo (string currency, int? startTimestamp, int? endTimestamp, string kind = null, int? count = null, bool? includeOld = null, string sorting = null)
        {
            // verify the required parameter 'currency' is set
            if (currency == null)
                throw new ApiException(400, "Missing required parameter 'currency' when calling TradingApi->PrivateGetUserTradesByCurrencyAndTimeGet");
            // verify the required parameter 'startTimestamp' is set
            if (startTimestamp == null)
                throw new ApiException(400, "Missing required parameter 'startTimestamp' when calling TradingApi->PrivateGetUserTradesByCurrencyAndTimeGet");
            // verify the required parameter 'endTimestamp' is set
            if (endTimestamp == null)
                throw new ApiException(400, "Missing required parameter 'endTimestamp' when calling TradingApi->PrivateGetUserTradesByCurrencyAndTimeGet");

            var localVarPath = "/private/get_user_trades_by_currency_and_time";
            var localVarPathParams = new Dictionary<String, String>();
            var localVarQueryParams = new List<KeyValuePair<String, String>>();
            var localVarHeaderParams = new Dictionary<String, String>(this.Configuration.DefaultHeader);
            var localVarFormParams = new Dictionary<String, String>();
            var localVarFileParams = new Dictionary<String, FileParameter>();
            Object localVarPostBody = null;

            // to determine the Content-Type header
            String[] localVarHttpContentTypes = new String[] {
            };
            String localVarHttpContentType = this.Configuration.ApiClient.SelectHeaderContentType(localVarHttpContentTypes);

            // to determine the Accept header
            String[] localVarHttpHeaderAccepts = new String[] {
                "application/json"
            };
            String localVarHttpHeaderAccept = this.Configuration.ApiClient.SelectHeaderAccept(localVarHttpHeaderAccepts);
            if (localVarHttpHeaderAccept != null)
                localVarHeaderParams.Add("Accept", localVarHttpHeaderAccept);

            if (currency != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "currency", currency)); // query parameter
            if (kind != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "kind", kind)); // query parameter
            if (startTimestamp != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "start_timestamp", startTimestamp)); // query parameter
            if (endTimestamp != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "end_timestamp", endTimestamp)); // query parameter
            if (count != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "count", count)); // query parameter
            if (includeOld != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "include_old", includeOld)); // query parameter
            if (sorting != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "sorting", sorting)); // query parameter

            // authentication (bearerAuth) required
            // http basic authentication required
            if (!String.IsNullOrEmpty(this.Configuration.Username) || !String.IsNullOrEmpty(this.Configuration.Password))
            {
                localVarHeaderParams["Authorization"] = "Basic " + ApiClient.Base64Encode(this.Configuration.Username + ":" + this.Configuration.Password);
            }

            // make the HTTP request
            IRestResponse localVarResponse = (IRestResponse) this.Configuration.ApiClient.CallApi(localVarPath,
                Method.GET, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarFileParams,
                localVarPathParams, localVarHttpContentType);

            int localVarStatusCode = (int) localVarResponse.StatusCode;

            if (ExceptionFactory != null)
            {
                Exception exception = ExceptionFactory("PrivateGetUserTradesByCurrencyAndTimeGet", localVarResponse);
                if (exception != null) throw exception;
            }

            return new ApiResponse<Object>(localVarStatusCode,
                localVarResponse.Headers.ToDictionary(x => x.Name, x => string.Join(",", x.Value)),
                (Object) this.Configuration.ApiClient.Deserialize(localVarResponse, typeof(Object)));
        }

        /// <summary>
        /// Retrieve the latest user trades that have occurred for instruments in a specific currency symbol and within given time range. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="startTimestamp">The earliest timestamp to return result for</param>
        /// <param name="endTimestamp">The most recent timestamp to return result for</param>
        /// <param name="kind">Instrument kind, if not provided instruments of all kinds are considered (optional)</param>
        /// <param name="count">Number of requested items, default - &#x60;10&#x60; (optional)</param>
        /// <param name="includeOld">Include trades older than 7 days, default - &#x60;false&#x60; (optional)</param>
        /// <param name="sorting">Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) (optional)</param>
        /// <returns>Task of Object</returns>
        public async System.Threading.Tasks.Task<Object> PrivateGetUserTradesByCurrencyAndTimeGetAsync (string currency, int? startTimestamp, int? endTimestamp, string kind = null, int? count = null, bool? includeOld = null, string sorting = null)
        {
             ApiResponse<Object> localVarResponse = await PrivateGetUserTradesByCurrencyAndTimeGetAsyncWithHttpInfo(currency, startTimestamp, endTimestamp, kind, count, includeOld, sorting);
             return localVarResponse.Data;

        }

        /// <summary>
        /// Retrieve the latest user trades that have occurred for instruments in a specific currency symbol and within given time range. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="startTimestamp">The earliest timestamp to return result for</param>
        /// <param name="endTimestamp">The most recent timestamp to return result for</param>
        /// <param name="kind">Instrument kind, if not provided instruments of all kinds are considered (optional)</param>
        /// <param name="count">Number of requested items, default - &#x60;10&#x60; (optional)</param>
        /// <param name="includeOld">Include trades older than 7 days, default - &#x60;false&#x60; (optional)</param>
        /// <param name="sorting">Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) (optional)</param>
        /// <returns>Task of ApiResponse (Object)</returns>
        public async System.Threading.Tasks.Task<ApiResponse<Object>> PrivateGetUserTradesByCurrencyAndTimeGetAsyncWithHttpInfo (string currency, int? startTimestamp, int? endTimestamp, string kind = null, int? count = null, bool? includeOld = null, string sorting = null)
        {
            // verify the required parameter 'currency' is set
            if (currency == null)
                throw new ApiException(400, "Missing required parameter 'currency' when calling TradingApi->PrivateGetUserTradesByCurrencyAndTimeGet");
            // verify the required parameter 'startTimestamp' is set
            if (startTimestamp == null)
                throw new ApiException(400, "Missing required parameter 'startTimestamp' when calling TradingApi->PrivateGetUserTradesByCurrencyAndTimeGet");
            // verify the required parameter 'endTimestamp' is set
            if (endTimestamp == null)
                throw new ApiException(400, "Missing required parameter 'endTimestamp' when calling TradingApi->PrivateGetUserTradesByCurrencyAndTimeGet");

            var localVarPath = "/private/get_user_trades_by_currency_and_time";
            var localVarPathParams = new Dictionary<String, String>();
            var localVarQueryParams = new List<KeyValuePair<String, String>>();
            var localVarHeaderParams = new Dictionary<String, String>(this.Configuration.DefaultHeader);
            var localVarFormParams = new Dictionary<String, String>();
            var localVarFileParams = new Dictionary<String, FileParameter>();
            Object localVarPostBody = null;

            // to determine the Content-Type header
            String[] localVarHttpContentTypes = new String[] {
            };
            String localVarHttpContentType = this.Configuration.ApiClient.SelectHeaderContentType(localVarHttpContentTypes);

            // to determine the Accept header
            String[] localVarHttpHeaderAccepts = new String[] {
                "application/json"
            };
            String localVarHttpHeaderAccept = this.Configuration.ApiClient.SelectHeaderAccept(localVarHttpHeaderAccepts);
            if (localVarHttpHeaderAccept != null)
                localVarHeaderParams.Add("Accept", localVarHttpHeaderAccept);

            if (currency != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "currency", currency)); // query parameter
            if (kind != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "kind", kind)); // query parameter
            if (startTimestamp != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "start_timestamp", startTimestamp)); // query parameter
            if (endTimestamp != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "end_timestamp", endTimestamp)); // query parameter
            if (count != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "count", count)); // query parameter
            if (includeOld != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "include_old", includeOld)); // query parameter
            if (sorting != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "sorting", sorting)); // query parameter

            // authentication (bearerAuth) required
            // http basic authentication required
            if (!String.IsNullOrEmpty(this.Configuration.Username) || !String.IsNullOrEmpty(this.Configuration.Password))
            {
                localVarHeaderParams["Authorization"] = "Basic " + ApiClient.Base64Encode(this.Configuration.Username + ":" + this.Configuration.Password);
            }

            // make the HTTP request
            IRestResponse localVarResponse = (IRestResponse) await this.Configuration.ApiClient.CallApiAsync(localVarPath,
                Method.GET, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarFileParams,
                localVarPathParams, localVarHttpContentType);

            int localVarStatusCode = (int) localVarResponse.StatusCode;

            if (ExceptionFactory != null)
            {
                Exception exception = ExceptionFactory("PrivateGetUserTradesByCurrencyAndTimeGet", localVarResponse);
                if (exception != null) throw exception;
            }

            return new ApiResponse<Object>(localVarStatusCode,
                localVarResponse.Headers.ToDictionary(x => x.Name, x => string.Join(",", x.Value)),
                (Object) this.Configuration.ApiClient.Deserialize(localVarResponse, typeof(Object)));
        }

        /// <summary>
        /// Retrieve the latest user trades that have occurred for instruments in a specific currency symbol. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="kind">Instrument kind, if not provided instruments of all kinds are considered (optional)</param>
        /// <param name="startId">The ID number of the first trade to be returned (optional)</param>
        /// <param name="endId">The ID number of the last trade to be returned (optional)</param>
        /// <param name="count">Number of requested items, default - &#x60;10&#x60; (optional)</param>
        /// <param name="includeOld">Include trades older than 7 days, default - &#x60;false&#x60; (optional)</param>
        /// <param name="sorting">Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) (optional)</param>
        /// <returns>Object</returns>
        public Object PrivateGetUserTradesByCurrencyGet (string currency, string kind = null, string startId = null, string endId = null, int? count = null, bool? includeOld = null, string sorting = null)
        {
             ApiResponse<Object> localVarResponse = PrivateGetUserTradesByCurrencyGetWithHttpInfo(currency, kind, startId, endId, count, includeOld, sorting);
             return localVarResponse.Data;
        }

        /// <summary>
        /// Retrieve the latest user trades that have occurred for instruments in a specific currency symbol. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="kind">Instrument kind, if not provided instruments of all kinds are considered (optional)</param>
        /// <param name="startId">The ID number of the first trade to be returned (optional)</param>
        /// <param name="endId">The ID number of the last trade to be returned (optional)</param>
        /// <param name="count">Number of requested items, default - &#x60;10&#x60; (optional)</param>
        /// <param name="includeOld">Include trades older than 7 days, default - &#x60;false&#x60; (optional)</param>
        /// <param name="sorting">Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) (optional)</param>
        /// <returns>ApiResponse of Object</returns>
        public ApiResponse< Object > PrivateGetUserTradesByCurrencyGetWithHttpInfo (string currency, string kind = null, string startId = null, string endId = null, int? count = null, bool? includeOld = null, string sorting = null)
        {
            // verify the required parameter 'currency' is set
            if (currency == null)
                throw new ApiException(400, "Missing required parameter 'currency' when calling TradingApi->PrivateGetUserTradesByCurrencyGet");

            var localVarPath = "/private/get_user_trades_by_currency";
            var localVarPathParams = new Dictionary<String, String>();
            var localVarQueryParams = new List<KeyValuePair<String, String>>();
            var localVarHeaderParams = new Dictionary<String, String>(this.Configuration.DefaultHeader);
            var localVarFormParams = new Dictionary<String, String>();
            var localVarFileParams = new Dictionary<String, FileParameter>();
            Object localVarPostBody = null;

            // to determine the Content-Type header
            String[] localVarHttpContentTypes = new String[] {
            };
            String localVarHttpContentType = this.Configuration.ApiClient.SelectHeaderContentType(localVarHttpContentTypes);

            // to determine the Accept header
            String[] localVarHttpHeaderAccepts = new String[] {
                "application/json"
            };
            String localVarHttpHeaderAccept = this.Configuration.ApiClient.SelectHeaderAccept(localVarHttpHeaderAccepts);
            if (localVarHttpHeaderAccept != null)
                localVarHeaderParams.Add("Accept", localVarHttpHeaderAccept);

            if (currency != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "currency", currency)); // query parameter
            if (kind != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "kind", kind)); // query parameter
            if (startId != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "start_id", startId)); // query parameter
            if (endId != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "end_id", endId)); // query parameter
            if (count != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "count", count)); // query parameter
            if (includeOld != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "include_old", includeOld)); // query parameter
            if (sorting != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "sorting", sorting)); // query parameter

            // authentication (bearerAuth) required
            // http basic authentication required
            if (!String.IsNullOrEmpty(this.Configuration.Username) || !String.IsNullOrEmpty(this.Configuration.Password))
            {
                localVarHeaderParams["Authorization"] = "Basic " + ApiClient.Base64Encode(this.Configuration.Username + ":" + this.Configuration.Password);
            }

            // make the HTTP request
            IRestResponse localVarResponse = (IRestResponse) this.Configuration.ApiClient.CallApi(localVarPath,
                Method.GET, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarFileParams,
                localVarPathParams, localVarHttpContentType);

            int localVarStatusCode = (int) localVarResponse.StatusCode;

            if (ExceptionFactory != null)
            {
                Exception exception = ExceptionFactory("PrivateGetUserTradesByCurrencyGet", localVarResponse);
                if (exception != null) throw exception;
            }

            return new ApiResponse<Object>(localVarStatusCode,
                localVarResponse.Headers.ToDictionary(x => x.Name, x => string.Join(",", x.Value)),
                (Object) this.Configuration.ApiClient.Deserialize(localVarResponse, typeof(Object)));
        }

        /// <summary>
        /// Retrieve the latest user trades that have occurred for instruments in a specific currency symbol. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="kind">Instrument kind, if not provided instruments of all kinds are considered (optional)</param>
        /// <param name="startId">The ID number of the first trade to be returned (optional)</param>
        /// <param name="endId">The ID number of the last trade to be returned (optional)</param>
        /// <param name="count">Number of requested items, default - &#x60;10&#x60; (optional)</param>
        /// <param name="includeOld">Include trades older than 7 days, default - &#x60;false&#x60; (optional)</param>
        /// <param name="sorting">Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) (optional)</param>
        /// <returns>Task of Object</returns>
        public async System.Threading.Tasks.Task<Object> PrivateGetUserTradesByCurrencyGetAsync (string currency, string kind = null, string startId = null, string endId = null, int? count = null, bool? includeOld = null, string sorting = null)
        {
             ApiResponse<Object> localVarResponse = await PrivateGetUserTradesByCurrencyGetAsyncWithHttpInfo(currency, kind, startId, endId, count, includeOld, sorting);
             return localVarResponse.Data;

        }

        /// <summary>
        /// Retrieve the latest user trades that have occurred for instruments in a specific currency symbol. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="kind">Instrument kind, if not provided instruments of all kinds are considered (optional)</param>
        /// <param name="startId">The ID number of the first trade to be returned (optional)</param>
        /// <param name="endId">The ID number of the last trade to be returned (optional)</param>
        /// <param name="count">Number of requested items, default - &#x60;10&#x60; (optional)</param>
        /// <param name="includeOld">Include trades older than 7 days, default - &#x60;false&#x60; (optional)</param>
        /// <param name="sorting">Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) (optional)</param>
        /// <returns>Task of ApiResponse (Object)</returns>
        public async System.Threading.Tasks.Task<ApiResponse<Object>> PrivateGetUserTradesByCurrencyGetAsyncWithHttpInfo (string currency, string kind = null, string startId = null, string endId = null, int? count = null, bool? includeOld = null, string sorting = null)
        {
            // verify the required parameter 'currency' is set
            if (currency == null)
                throw new ApiException(400, "Missing required parameter 'currency' when calling TradingApi->PrivateGetUserTradesByCurrencyGet");

            var localVarPath = "/private/get_user_trades_by_currency";
            var localVarPathParams = new Dictionary<String, String>();
            var localVarQueryParams = new List<KeyValuePair<String, String>>();
            var localVarHeaderParams = new Dictionary<String, String>(this.Configuration.DefaultHeader);
            var localVarFormParams = new Dictionary<String, String>();
            var localVarFileParams = new Dictionary<String, FileParameter>();
            Object localVarPostBody = null;

            // to determine the Content-Type header
            String[] localVarHttpContentTypes = new String[] {
            };
            String localVarHttpContentType = this.Configuration.ApiClient.SelectHeaderContentType(localVarHttpContentTypes);

            // to determine the Accept header
            String[] localVarHttpHeaderAccepts = new String[] {
                "application/json"
            };
            String localVarHttpHeaderAccept = this.Configuration.ApiClient.SelectHeaderAccept(localVarHttpHeaderAccepts);
            if (localVarHttpHeaderAccept != null)
                localVarHeaderParams.Add("Accept", localVarHttpHeaderAccept);

            if (currency != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "currency", currency)); // query parameter
            if (kind != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "kind", kind)); // query parameter
            if (startId != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "start_id", startId)); // query parameter
            if (endId != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "end_id", endId)); // query parameter
            if (count != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "count", count)); // query parameter
            if (includeOld != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "include_old", includeOld)); // query parameter
            if (sorting != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "sorting", sorting)); // query parameter

            // authentication (bearerAuth) required
            // http basic authentication required
            if (!String.IsNullOrEmpty(this.Configuration.Username) || !String.IsNullOrEmpty(this.Configuration.Password))
            {
                localVarHeaderParams["Authorization"] = "Basic " + ApiClient.Base64Encode(this.Configuration.Username + ":" + this.Configuration.Password);
            }

            // make the HTTP request
            IRestResponse localVarResponse = (IRestResponse) await this.Configuration.ApiClient.CallApiAsync(localVarPath,
                Method.GET, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarFileParams,
                localVarPathParams, localVarHttpContentType);

            int localVarStatusCode = (int) localVarResponse.StatusCode;

            if (ExceptionFactory != null)
            {
                Exception exception = ExceptionFactory("PrivateGetUserTradesByCurrencyGet", localVarResponse);
                if (exception != null) throw exception;
            }

            return new ApiResponse<Object>(localVarStatusCode,
                localVarResponse.Headers.ToDictionary(x => x.Name, x => string.Join(",", x.Value)),
                (Object) this.Configuration.ApiClient.Deserialize(localVarResponse, typeof(Object)));
        }

        /// <summary>
        /// Retrieve the latest user trades that have occurred for a specific instrument and within given time range. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">Instrument name</param>
        /// <param name="startTimestamp">The earliest timestamp to return result for</param>
        /// <param name="endTimestamp">The most recent timestamp to return result for</param>
        /// <param name="count">Number of requested items, default - &#x60;10&#x60; (optional)</param>
        /// <param name="includeOld">Include trades older than 7 days, default - &#x60;false&#x60; (optional)</param>
        /// <param name="sorting">Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) (optional)</param>
        /// <returns>Object</returns>
        public Object PrivateGetUserTradesByInstrumentAndTimeGet (string instrumentName, int? startTimestamp, int? endTimestamp, int? count = null, bool? includeOld = null, string sorting = null)
        {
             ApiResponse<Object> localVarResponse = PrivateGetUserTradesByInstrumentAndTimeGetWithHttpInfo(instrumentName, startTimestamp, endTimestamp, count, includeOld, sorting);
             return localVarResponse.Data;
        }

        /// <summary>
        /// Retrieve the latest user trades that have occurred for a specific instrument and within given time range. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">Instrument name</param>
        /// <param name="startTimestamp">The earliest timestamp to return result for</param>
        /// <param name="endTimestamp">The most recent timestamp to return result for</param>
        /// <param name="count">Number of requested items, default - &#x60;10&#x60; (optional)</param>
        /// <param name="includeOld">Include trades older than 7 days, default - &#x60;false&#x60; (optional)</param>
        /// <param name="sorting">Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) (optional)</param>
        /// <returns>ApiResponse of Object</returns>
        public ApiResponse< Object > PrivateGetUserTradesByInstrumentAndTimeGetWithHttpInfo (string instrumentName, int? startTimestamp, int? endTimestamp, int? count = null, bool? includeOld = null, string sorting = null)
        {
            // verify the required parameter 'instrumentName' is set
            if (instrumentName == null)
                throw new ApiException(400, "Missing required parameter 'instrumentName' when calling TradingApi->PrivateGetUserTradesByInstrumentAndTimeGet");
            // verify the required parameter 'startTimestamp' is set
            if (startTimestamp == null)
                throw new ApiException(400, "Missing required parameter 'startTimestamp' when calling TradingApi->PrivateGetUserTradesByInstrumentAndTimeGet");
            // verify the required parameter 'endTimestamp' is set
            if (endTimestamp == null)
                throw new ApiException(400, "Missing required parameter 'endTimestamp' when calling TradingApi->PrivateGetUserTradesByInstrumentAndTimeGet");

            var localVarPath = "/private/get_user_trades_by_instrument_and_time";
            var localVarPathParams = new Dictionary<String, String>();
            var localVarQueryParams = new List<KeyValuePair<String, String>>();
            var localVarHeaderParams = new Dictionary<String, String>(this.Configuration.DefaultHeader);
            var localVarFormParams = new Dictionary<String, String>();
            var localVarFileParams = new Dictionary<String, FileParameter>();
            Object localVarPostBody = null;

            // to determine the Content-Type header
            String[] localVarHttpContentTypes = new String[] {
            };
            String localVarHttpContentType = this.Configuration.ApiClient.SelectHeaderContentType(localVarHttpContentTypes);

            // to determine the Accept header
            String[] localVarHttpHeaderAccepts = new String[] {
                "application/json"
            };
            String localVarHttpHeaderAccept = this.Configuration.ApiClient.SelectHeaderAccept(localVarHttpHeaderAccepts);
            if (localVarHttpHeaderAccept != null)
                localVarHeaderParams.Add("Accept", localVarHttpHeaderAccept);

            if (instrumentName != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "instrument_name", instrumentName)); // query parameter
            if (startTimestamp != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "start_timestamp", startTimestamp)); // query parameter
            if (endTimestamp != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "end_timestamp", endTimestamp)); // query parameter
            if (count != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "count", count)); // query parameter
            if (includeOld != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "include_old", includeOld)); // query parameter
            if (sorting != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "sorting", sorting)); // query parameter

            // authentication (bearerAuth) required
            // http basic authentication required
            if (!String.IsNullOrEmpty(this.Configuration.Username) || !String.IsNullOrEmpty(this.Configuration.Password))
            {
                localVarHeaderParams["Authorization"] = "Basic " + ApiClient.Base64Encode(this.Configuration.Username + ":" + this.Configuration.Password);
            }

            // make the HTTP request
            IRestResponse localVarResponse = (IRestResponse) this.Configuration.ApiClient.CallApi(localVarPath,
                Method.GET, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarFileParams,
                localVarPathParams, localVarHttpContentType);

            int localVarStatusCode = (int) localVarResponse.StatusCode;

            if (ExceptionFactory != null)
            {
                Exception exception = ExceptionFactory("PrivateGetUserTradesByInstrumentAndTimeGet", localVarResponse);
                if (exception != null) throw exception;
            }

            return new ApiResponse<Object>(localVarStatusCode,
                localVarResponse.Headers.ToDictionary(x => x.Name, x => string.Join(",", x.Value)),
                (Object) this.Configuration.ApiClient.Deserialize(localVarResponse, typeof(Object)));
        }

        /// <summary>
        /// Retrieve the latest user trades that have occurred for a specific instrument and within given time range. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">Instrument name</param>
        /// <param name="startTimestamp">The earliest timestamp to return result for</param>
        /// <param name="endTimestamp">The most recent timestamp to return result for</param>
        /// <param name="count">Number of requested items, default - &#x60;10&#x60; (optional)</param>
        /// <param name="includeOld">Include trades older than 7 days, default - &#x60;false&#x60; (optional)</param>
        /// <param name="sorting">Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) (optional)</param>
        /// <returns>Task of Object</returns>
        public async System.Threading.Tasks.Task<Object> PrivateGetUserTradesByInstrumentAndTimeGetAsync (string instrumentName, int? startTimestamp, int? endTimestamp, int? count = null, bool? includeOld = null, string sorting = null)
        {
             ApiResponse<Object> localVarResponse = await PrivateGetUserTradesByInstrumentAndTimeGetAsyncWithHttpInfo(instrumentName, startTimestamp, endTimestamp, count, includeOld, sorting);
             return localVarResponse.Data;

        }

        /// <summary>
        /// Retrieve the latest user trades that have occurred for a specific instrument and within given time range. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">Instrument name</param>
        /// <param name="startTimestamp">The earliest timestamp to return result for</param>
        /// <param name="endTimestamp">The most recent timestamp to return result for</param>
        /// <param name="count">Number of requested items, default - &#x60;10&#x60; (optional)</param>
        /// <param name="includeOld">Include trades older than 7 days, default - &#x60;false&#x60; (optional)</param>
        /// <param name="sorting">Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) (optional)</param>
        /// <returns>Task of ApiResponse (Object)</returns>
        public async System.Threading.Tasks.Task<ApiResponse<Object>> PrivateGetUserTradesByInstrumentAndTimeGetAsyncWithHttpInfo (string instrumentName, int? startTimestamp, int? endTimestamp, int? count = null, bool? includeOld = null, string sorting = null)
        {
            // verify the required parameter 'instrumentName' is set
            if (instrumentName == null)
                throw new ApiException(400, "Missing required parameter 'instrumentName' when calling TradingApi->PrivateGetUserTradesByInstrumentAndTimeGet");
            // verify the required parameter 'startTimestamp' is set
            if (startTimestamp == null)
                throw new ApiException(400, "Missing required parameter 'startTimestamp' when calling TradingApi->PrivateGetUserTradesByInstrumentAndTimeGet");
            // verify the required parameter 'endTimestamp' is set
            if (endTimestamp == null)
                throw new ApiException(400, "Missing required parameter 'endTimestamp' when calling TradingApi->PrivateGetUserTradesByInstrumentAndTimeGet");

            var localVarPath = "/private/get_user_trades_by_instrument_and_time";
            var localVarPathParams = new Dictionary<String, String>();
            var localVarQueryParams = new List<KeyValuePair<String, String>>();
            var localVarHeaderParams = new Dictionary<String, String>(this.Configuration.DefaultHeader);
            var localVarFormParams = new Dictionary<String, String>();
            var localVarFileParams = new Dictionary<String, FileParameter>();
            Object localVarPostBody = null;

            // to determine the Content-Type header
            String[] localVarHttpContentTypes = new String[] {
            };
            String localVarHttpContentType = this.Configuration.ApiClient.SelectHeaderContentType(localVarHttpContentTypes);

            // to determine the Accept header
            String[] localVarHttpHeaderAccepts = new String[] {
                "application/json"
            };
            String localVarHttpHeaderAccept = this.Configuration.ApiClient.SelectHeaderAccept(localVarHttpHeaderAccepts);
            if (localVarHttpHeaderAccept != null)
                localVarHeaderParams.Add("Accept", localVarHttpHeaderAccept);

            if (instrumentName != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "instrument_name", instrumentName)); // query parameter
            if (startTimestamp != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "start_timestamp", startTimestamp)); // query parameter
            if (endTimestamp != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "end_timestamp", endTimestamp)); // query parameter
            if (count != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "count", count)); // query parameter
            if (includeOld != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "include_old", includeOld)); // query parameter
            if (sorting != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "sorting", sorting)); // query parameter

            // authentication (bearerAuth) required
            // http basic authentication required
            if (!String.IsNullOrEmpty(this.Configuration.Username) || !String.IsNullOrEmpty(this.Configuration.Password))
            {
                localVarHeaderParams["Authorization"] = "Basic " + ApiClient.Base64Encode(this.Configuration.Username + ":" + this.Configuration.Password);
            }

            // make the HTTP request
            IRestResponse localVarResponse = (IRestResponse) await this.Configuration.ApiClient.CallApiAsync(localVarPath,
                Method.GET, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarFileParams,
                localVarPathParams, localVarHttpContentType);

            int localVarStatusCode = (int) localVarResponse.StatusCode;

            if (ExceptionFactory != null)
            {
                Exception exception = ExceptionFactory("PrivateGetUserTradesByInstrumentAndTimeGet", localVarResponse);
                if (exception != null) throw exception;
            }

            return new ApiResponse<Object>(localVarStatusCode,
                localVarResponse.Headers.ToDictionary(x => x.Name, x => string.Join(",", x.Value)),
                (Object) this.Configuration.ApiClient.Deserialize(localVarResponse, typeof(Object)));
        }

        /// <summary>
        /// Retrieve the latest user trades that have occurred for a specific instrument. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">Instrument name</param>
        /// <param name="startSeq">The sequence number of the first trade to be returned (optional)</param>
        /// <param name="endSeq">The sequence number of the last trade to be returned (optional)</param>
        /// <param name="count">Number of requested items, default - &#x60;10&#x60; (optional)</param>
        /// <param name="includeOld">Include trades older than 7 days, default - &#x60;false&#x60; (optional)</param>
        /// <param name="sorting">Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) (optional)</param>
        /// <returns>Object</returns>
        public Object PrivateGetUserTradesByInstrumentGet (string instrumentName, int? startSeq = null, int? endSeq = null, int? count = null, bool? includeOld = null, string sorting = null)
        {
             ApiResponse<Object> localVarResponse = PrivateGetUserTradesByInstrumentGetWithHttpInfo(instrumentName, startSeq, endSeq, count, includeOld, sorting);
             return localVarResponse.Data;
        }

        /// <summary>
        /// Retrieve the latest user trades that have occurred for a specific instrument. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">Instrument name</param>
        /// <param name="startSeq">The sequence number of the first trade to be returned (optional)</param>
        /// <param name="endSeq">The sequence number of the last trade to be returned (optional)</param>
        /// <param name="count">Number of requested items, default - &#x60;10&#x60; (optional)</param>
        /// <param name="includeOld">Include trades older than 7 days, default - &#x60;false&#x60; (optional)</param>
        /// <param name="sorting">Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) (optional)</param>
        /// <returns>ApiResponse of Object</returns>
        public ApiResponse< Object > PrivateGetUserTradesByInstrumentGetWithHttpInfo (string instrumentName, int? startSeq = null, int? endSeq = null, int? count = null, bool? includeOld = null, string sorting = null)
        {
            // verify the required parameter 'instrumentName' is set
            if (instrumentName == null)
                throw new ApiException(400, "Missing required parameter 'instrumentName' when calling TradingApi->PrivateGetUserTradesByInstrumentGet");

            var localVarPath = "/private/get_user_trades_by_instrument";
            var localVarPathParams = new Dictionary<String, String>();
            var localVarQueryParams = new List<KeyValuePair<String, String>>();
            var localVarHeaderParams = new Dictionary<String, String>(this.Configuration.DefaultHeader);
            var localVarFormParams = new Dictionary<String, String>();
            var localVarFileParams = new Dictionary<String, FileParameter>();
            Object localVarPostBody = null;

            // to determine the Content-Type header
            String[] localVarHttpContentTypes = new String[] {
            };
            String localVarHttpContentType = this.Configuration.ApiClient.SelectHeaderContentType(localVarHttpContentTypes);

            // to determine the Accept header
            String[] localVarHttpHeaderAccepts = new String[] {
                "application/json"
            };
            String localVarHttpHeaderAccept = this.Configuration.ApiClient.SelectHeaderAccept(localVarHttpHeaderAccepts);
            if (localVarHttpHeaderAccept != null)
                localVarHeaderParams.Add("Accept", localVarHttpHeaderAccept);

            if (instrumentName != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "instrument_name", instrumentName)); // query parameter
            if (startSeq != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "start_seq", startSeq)); // query parameter
            if (endSeq != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "end_seq", endSeq)); // query parameter
            if (count != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "count", count)); // query parameter
            if (includeOld != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "include_old", includeOld)); // query parameter
            if (sorting != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "sorting", sorting)); // query parameter

            // authentication (bearerAuth) required
            // http basic authentication required
            if (!String.IsNullOrEmpty(this.Configuration.Username) || !String.IsNullOrEmpty(this.Configuration.Password))
            {
                localVarHeaderParams["Authorization"] = "Basic " + ApiClient.Base64Encode(this.Configuration.Username + ":" + this.Configuration.Password);
            }

            // make the HTTP request
            IRestResponse localVarResponse = (IRestResponse) this.Configuration.ApiClient.CallApi(localVarPath,
                Method.GET, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarFileParams,
                localVarPathParams, localVarHttpContentType);

            int localVarStatusCode = (int) localVarResponse.StatusCode;

            if (ExceptionFactory != null)
            {
                Exception exception = ExceptionFactory("PrivateGetUserTradesByInstrumentGet", localVarResponse);
                if (exception != null) throw exception;
            }

            return new ApiResponse<Object>(localVarStatusCode,
                localVarResponse.Headers.ToDictionary(x => x.Name, x => string.Join(",", x.Value)),
                (Object) this.Configuration.ApiClient.Deserialize(localVarResponse, typeof(Object)));
        }

        /// <summary>
        /// Retrieve the latest user trades that have occurred for a specific instrument. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">Instrument name</param>
        /// <param name="startSeq">The sequence number of the first trade to be returned (optional)</param>
        /// <param name="endSeq">The sequence number of the last trade to be returned (optional)</param>
        /// <param name="count">Number of requested items, default - &#x60;10&#x60; (optional)</param>
        /// <param name="includeOld">Include trades older than 7 days, default - &#x60;false&#x60; (optional)</param>
        /// <param name="sorting">Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) (optional)</param>
        /// <returns>Task of Object</returns>
        public async System.Threading.Tasks.Task<Object> PrivateGetUserTradesByInstrumentGetAsync (string instrumentName, int? startSeq = null, int? endSeq = null, int? count = null, bool? includeOld = null, string sorting = null)
        {
             ApiResponse<Object> localVarResponse = await PrivateGetUserTradesByInstrumentGetAsyncWithHttpInfo(instrumentName, startSeq, endSeq, count, includeOld, sorting);
             return localVarResponse.Data;

        }

        /// <summary>
        /// Retrieve the latest user trades that have occurred for a specific instrument. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">Instrument name</param>
        /// <param name="startSeq">The sequence number of the first trade to be returned (optional)</param>
        /// <param name="endSeq">The sequence number of the last trade to be returned (optional)</param>
        /// <param name="count">Number of requested items, default - &#x60;10&#x60; (optional)</param>
        /// <param name="includeOld">Include trades older than 7 days, default - &#x60;false&#x60; (optional)</param>
        /// <param name="sorting">Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) (optional)</param>
        /// <returns>Task of ApiResponse (Object)</returns>
        public async System.Threading.Tasks.Task<ApiResponse<Object>> PrivateGetUserTradesByInstrumentGetAsyncWithHttpInfo (string instrumentName, int? startSeq = null, int? endSeq = null, int? count = null, bool? includeOld = null, string sorting = null)
        {
            // verify the required parameter 'instrumentName' is set
            if (instrumentName == null)
                throw new ApiException(400, "Missing required parameter 'instrumentName' when calling TradingApi->PrivateGetUserTradesByInstrumentGet");

            var localVarPath = "/private/get_user_trades_by_instrument";
            var localVarPathParams = new Dictionary<String, String>();
            var localVarQueryParams = new List<KeyValuePair<String, String>>();
            var localVarHeaderParams = new Dictionary<String, String>(this.Configuration.DefaultHeader);
            var localVarFormParams = new Dictionary<String, String>();
            var localVarFileParams = new Dictionary<String, FileParameter>();
            Object localVarPostBody = null;

            // to determine the Content-Type header
            String[] localVarHttpContentTypes = new String[] {
            };
            String localVarHttpContentType = this.Configuration.ApiClient.SelectHeaderContentType(localVarHttpContentTypes);

            // to determine the Accept header
            String[] localVarHttpHeaderAccepts = new String[] {
                "application/json"
            };
            String localVarHttpHeaderAccept = this.Configuration.ApiClient.SelectHeaderAccept(localVarHttpHeaderAccepts);
            if (localVarHttpHeaderAccept != null)
                localVarHeaderParams.Add("Accept", localVarHttpHeaderAccept);

            if (instrumentName != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "instrument_name", instrumentName)); // query parameter
            if (startSeq != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "start_seq", startSeq)); // query parameter
            if (endSeq != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "end_seq", endSeq)); // query parameter
            if (count != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "count", count)); // query parameter
            if (includeOld != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "include_old", includeOld)); // query parameter
            if (sorting != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "sorting", sorting)); // query parameter

            // authentication (bearerAuth) required
            // http basic authentication required
            if (!String.IsNullOrEmpty(this.Configuration.Username) || !String.IsNullOrEmpty(this.Configuration.Password))
            {
                localVarHeaderParams["Authorization"] = "Basic " + ApiClient.Base64Encode(this.Configuration.Username + ":" + this.Configuration.Password);
            }

            // make the HTTP request
            IRestResponse localVarResponse = (IRestResponse) await this.Configuration.ApiClient.CallApiAsync(localVarPath,
                Method.GET, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarFileParams,
                localVarPathParams, localVarHttpContentType);

            int localVarStatusCode = (int) localVarResponse.StatusCode;

            if (ExceptionFactory != null)
            {
                Exception exception = ExceptionFactory("PrivateGetUserTradesByInstrumentGet", localVarResponse);
                if (exception != null) throw exception;
            }

            return new ApiResponse<Object>(localVarStatusCode,
                localVarResponse.Headers.ToDictionary(x => x.Name, x => string.Join(",", x.Value)),
                (Object) this.Configuration.ApiClient.Deserialize(localVarResponse, typeof(Object)));
        }

        /// <summary>
        /// Retrieve the list of user trades that was created for given order 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="orderId">The order id</param>
        /// <param name="sorting">Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) (optional)</param>
        /// <returns>Object</returns>
        public Object PrivateGetUserTradesByOrderGet (string orderId, string sorting = null)
        {
             ApiResponse<Object> localVarResponse = PrivateGetUserTradesByOrderGetWithHttpInfo(orderId, sorting);
             return localVarResponse.Data;
        }

        /// <summary>
        /// Retrieve the list of user trades that was created for given order 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="orderId">The order id</param>
        /// <param name="sorting">Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) (optional)</param>
        /// <returns>ApiResponse of Object</returns>
        public ApiResponse< Object > PrivateGetUserTradesByOrderGetWithHttpInfo (string orderId, string sorting = null)
        {
            // verify the required parameter 'orderId' is set
            if (orderId == null)
                throw new ApiException(400, "Missing required parameter 'orderId' when calling TradingApi->PrivateGetUserTradesByOrderGet");

            var localVarPath = "/private/get_user_trades_by_order";
            var localVarPathParams = new Dictionary<String, String>();
            var localVarQueryParams = new List<KeyValuePair<String, String>>();
            var localVarHeaderParams = new Dictionary<String, String>(this.Configuration.DefaultHeader);
            var localVarFormParams = new Dictionary<String, String>();
            var localVarFileParams = new Dictionary<String, FileParameter>();
            Object localVarPostBody = null;

            // to determine the Content-Type header
            String[] localVarHttpContentTypes = new String[] {
            };
            String localVarHttpContentType = this.Configuration.ApiClient.SelectHeaderContentType(localVarHttpContentTypes);

            // to determine the Accept header
            String[] localVarHttpHeaderAccepts = new String[] {
                "application/json"
            };
            String localVarHttpHeaderAccept = this.Configuration.ApiClient.SelectHeaderAccept(localVarHttpHeaderAccepts);
            if (localVarHttpHeaderAccept != null)
                localVarHeaderParams.Add("Accept", localVarHttpHeaderAccept);

            if (orderId != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "order_id", orderId)); // query parameter
            if (sorting != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "sorting", sorting)); // query parameter

            // authentication (bearerAuth) required
            // http basic authentication required
            if (!String.IsNullOrEmpty(this.Configuration.Username) || !String.IsNullOrEmpty(this.Configuration.Password))
            {
                localVarHeaderParams["Authorization"] = "Basic " + ApiClient.Base64Encode(this.Configuration.Username + ":" + this.Configuration.Password);
            }

            // make the HTTP request
            IRestResponse localVarResponse = (IRestResponse) this.Configuration.ApiClient.CallApi(localVarPath,
                Method.GET, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarFileParams,
                localVarPathParams, localVarHttpContentType);

            int localVarStatusCode = (int) localVarResponse.StatusCode;

            if (ExceptionFactory != null)
            {
                Exception exception = ExceptionFactory("PrivateGetUserTradesByOrderGet", localVarResponse);
                if (exception != null) throw exception;
            }

            return new ApiResponse<Object>(localVarStatusCode,
                localVarResponse.Headers.ToDictionary(x => x.Name, x => string.Join(",", x.Value)),
                (Object) this.Configuration.ApiClient.Deserialize(localVarResponse, typeof(Object)));
        }

        /// <summary>
        /// Retrieve the list of user trades that was created for given order 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="orderId">The order id</param>
        /// <param name="sorting">Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) (optional)</param>
        /// <returns>Task of Object</returns>
        public async System.Threading.Tasks.Task<Object> PrivateGetUserTradesByOrderGetAsync (string orderId, string sorting = null)
        {
             ApiResponse<Object> localVarResponse = await PrivateGetUserTradesByOrderGetAsyncWithHttpInfo(orderId, sorting);
             return localVarResponse.Data;

        }

        /// <summary>
        /// Retrieve the list of user trades that was created for given order 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="orderId">The order id</param>
        /// <param name="sorting">Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) (optional)</param>
        /// <returns>Task of ApiResponse (Object)</returns>
        public async System.Threading.Tasks.Task<ApiResponse<Object>> PrivateGetUserTradesByOrderGetAsyncWithHttpInfo (string orderId, string sorting = null)
        {
            // verify the required parameter 'orderId' is set
            if (orderId == null)
                throw new ApiException(400, "Missing required parameter 'orderId' when calling TradingApi->PrivateGetUserTradesByOrderGet");

            var localVarPath = "/private/get_user_trades_by_order";
            var localVarPathParams = new Dictionary<String, String>();
            var localVarQueryParams = new List<KeyValuePair<String, String>>();
            var localVarHeaderParams = new Dictionary<String, String>(this.Configuration.DefaultHeader);
            var localVarFormParams = new Dictionary<String, String>();
            var localVarFileParams = new Dictionary<String, FileParameter>();
            Object localVarPostBody = null;

            // to determine the Content-Type header
            String[] localVarHttpContentTypes = new String[] {
            };
            String localVarHttpContentType = this.Configuration.ApiClient.SelectHeaderContentType(localVarHttpContentTypes);

            // to determine the Accept header
            String[] localVarHttpHeaderAccepts = new String[] {
                "application/json"
            };
            String localVarHttpHeaderAccept = this.Configuration.ApiClient.SelectHeaderAccept(localVarHttpHeaderAccepts);
            if (localVarHttpHeaderAccept != null)
                localVarHeaderParams.Add("Accept", localVarHttpHeaderAccept);

            if (orderId != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "order_id", orderId)); // query parameter
            if (sorting != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "sorting", sorting)); // query parameter

            // authentication (bearerAuth) required
            // http basic authentication required
            if (!String.IsNullOrEmpty(this.Configuration.Username) || !String.IsNullOrEmpty(this.Configuration.Password))
            {
                localVarHeaderParams["Authorization"] = "Basic " + ApiClient.Base64Encode(this.Configuration.Username + ":" + this.Configuration.Password);
            }

            // make the HTTP request
            IRestResponse localVarResponse = (IRestResponse) await this.Configuration.ApiClient.CallApiAsync(localVarPath,
                Method.GET, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarFileParams,
                localVarPathParams, localVarHttpContentType);

            int localVarStatusCode = (int) localVarResponse.StatusCode;

            if (ExceptionFactory != null)
            {
                Exception exception = ExceptionFactory("PrivateGetUserTradesByOrderGet", localVarResponse);
                if (exception != null) throw exception;
            }

            return new ApiResponse<Object>(localVarStatusCode,
                localVarResponse.Headers.ToDictionary(x => x.Name, x => string.Join(",", x.Value)),
                (Object) this.Configuration.ApiClient.Deserialize(localVarResponse, typeof(Object)));
        }

        /// <summary>
        /// Places a sell order for an instrument. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">Instrument name</param>
        /// <param name="amount">It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH</param>
        /// <param name="type">The order type, default: &#x60;\&quot;limit\&quot;&#x60; (optional)</param>
        /// <param name="label">user defined label for the order (maximum 32 characters) (optional)</param>
        /// <param name="price">&lt;p&gt;The order price in base currency (Only for limit and stop_limit orders)&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;usd, the field price should be the option price value in USD.&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;implv, the field price should be a value of implied volatility in percentages. For example,  price&#x3D;100, means implied volatility of 100%&lt;/p&gt; (optional)</param>
        /// <param name="timeInForce">&lt;p&gt;Specifies how long the order remains in effect. Default &#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; &lt;ul&gt; &lt;li&gt;&#x60;\&quot;good_til_cancelled\&quot;&#x60; - unfilled order remains in order book until cancelled&lt;/li&gt; &lt;li&gt;&#x60;\&quot;fill_or_kill\&quot;&#x60; - execute a transaction immediately and completely or not at all&lt;/li&gt; &lt;li&gt;&#x60;\&quot;immediate_or_cancel\&quot;&#x60; - execute a transaction immediately, and any portion of the order that cannot be immediately filled is cancelled&lt;/li&gt; &lt;/ul&gt; (optional, default to good_til_cancelled)</param>
        /// <param name="maxShow">Maximum amount within an order to be shown to other customers, &#x60;0&#x60; for invisible order (optional, default to 1M)</param>
        /// <param name="postOnly">&lt;p&gt;If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.&lt;/p&gt; &lt;p&gt;Only valid in combination with time_in_force&#x3D;&#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; (optional, default to true)</param>
        /// <param name="reduceOnly">If &#x60;true&#x60;, the order is considered reduce-only which is intended to only reduce a current position (optional, default to false)</param>
        /// <param name="stopPrice">Stop price, required for stop limit orders (Only for stop orders) (optional)</param>
        /// <param name="trigger">Defines trigger type, required for &#x60;\&quot;stop_limit\&quot;&#x60; order type (optional)</param>
        /// <param name="advanced">Advanced option order type. (Only for options) (optional)</param>
        /// <returns>Object</returns>
        public Object PrivateSellGet (string instrumentName, decimal? amount, string type = null, string label = null, decimal? price = null, string timeInForce = null, decimal? maxShow = null, bool? postOnly = null, bool? reduceOnly = null, decimal? stopPrice = null, string trigger = null, string advanced = null)
        {
             ApiResponse<Object> localVarResponse = PrivateSellGetWithHttpInfo(instrumentName, amount, type, label, price, timeInForce, maxShow, postOnly, reduceOnly, stopPrice, trigger, advanced);
             return localVarResponse.Data;
        }

        /// <summary>
        /// Places a sell order for an instrument. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">Instrument name</param>
        /// <param name="amount">It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH</param>
        /// <param name="type">The order type, default: &#x60;\&quot;limit\&quot;&#x60; (optional)</param>
        /// <param name="label">user defined label for the order (maximum 32 characters) (optional)</param>
        /// <param name="price">&lt;p&gt;The order price in base currency (Only for limit and stop_limit orders)&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;usd, the field price should be the option price value in USD.&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;implv, the field price should be a value of implied volatility in percentages. For example,  price&#x3D;100, means implied volatility of 100%&lt;/p&gt; (optional)</param>
        /// <param name="timeInForce">&lt;p&gt;Specifies how long the order remains in effect. Default &#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; &lt;ul&gt; &lt;li&gt;&#x60;\&quot;good_til_cancelled\&quot;&#x60; - unfilled order remains in order book until cancelled&lt;/li&gt; &lt;li&gt;&#x60;\&quot;fill_or_kill\&quot;&#x60; - execute a transaction immediately and completely or not at all&lt;/li&gt; &lt;li&gt;&#x60;\&quot;immediate_or_cancel\&quot;&#x60; - execute a transaction immediately, and any portion of the order that cannot be immediately filled is cancelled&lt;/li&gt; &lt;/ul&gt; (optional, default to good_til_cancelled)</param>
        /// <param name="maxShow">Maximum amount within an order to be shown to other customers, &#x60;0&#x60; for invisible order (optional, default to 1M)</param>
        /// <param name="postOnly">&lt;p&gt;If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.&lt;/p&gt; &lt;p&gt;Only valid in combination with time_in_force&#x3D;&#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; (optional, default to true)</param>
        /// <param name="reduceOnly">If &#x60;true&#x60;, the order is considered reduce-only which is intended to only reduce a current position (optional, default to false)</param>
        /// <param name="stopPrice">Stop price, required for stop limit orders (Only for stop orders) (optional)</param>
        /// <param name="trigger">Defines trigger type, required for &#x60;\&quot;stop_limit\&quot;&#x60; order type (optional)</param>
        /// <param name="advanced">Advanced option order type. (Only for options) (optional)</param>
        /// <returns>ApiResponse of Object</returns>
        public ApiResponse< Object > PrivateSellGetWithHttpInfo (string instrumentName, decimal? amount, string type = null, string label = null, decimal? price = null, string timeInForce = null, decimal? maxShow = null, bool? postOnly = null, bool? reduceOnly = null, decimal? stopPrice = null, string trigger = null, string advanced = null)
        {
            // verify the required parameter 'instrumentName' is set
            if (instrumentName == null)
                throw new ApiException(400, "Missing required parameter 'instrumentName' when calling TradingApi->PrivateSellGet");
            // verify the required parameter 'amount' is set
            if (amount == null)
                throw new ApiException(400, "Missing required parameter 'amount' when calling TradingApi->PrivateSellGet");

            var localVarPath = "/private/sell";
            var localVarPathParams = new Dictionary<String, String>();
            var localVarQueryParams = new List<KeyValuePair<String, String>>();
            var localVarHeaderParams = new Dictionary<String, String>(this.Configuration.DefaultHeader);
            var localVarFormParams = new Dictionary<String, String>();
            var localVarFileParams = new Dictionary<String, FileParameter>();
            Object localVarPostBody = null;

            // to determine the Content-Type header
            String[] localVarHttpContentTypes = new String[] {
            };
            String localVarHttpContentType = this.Configuration.ApiClient.SelectHeaderContentType(localVarHttpContentTypes);

            // to determine the Accept header
            String[] localVarHttpHeaderAccepts = new String[] {
                "application/json"
            };
            String localVarHttpHeaderAccept = this.Configuration.ApiClient.SelectHeaderAccept(localVarHttpHeaderAccepts);
            if (localVarHttpHeaderAccept != null)
                localVarHeaderParams.Add("Accept", localVarHttpHeaderAccept);

            if (instrumentName != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "instrument_name", instrumentName)); // query parameter
            if (amount != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "amount", amount)); // query parameter
            if (type != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "type", type)); // query parameter
            if (label != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "label", label)); // query parameter
            if (price != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "price", price)); // query parameter
            if (timeInForce != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "time_in_force", timeInForce)); // query parameter
            if (maxShow != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "max_show", maxShow)); // query parameter
            if (postOnly != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "post_only", postOnly)); // query parameter
            if (reduceOnly != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "reduce_only", reduceOnly)); // query parameter
            if (stopPrice != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "stop_price", stopPrice)); // query parameter
            if (trigger != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "trigger", trigger)); // query parameter
            if (advanced != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "advanced", advanced)); // query parameter

            // authentication (bearerAuth) required
            // http basic authentication required
            if (!String.IsNullOrEmpty(this.Configuration.Username) || !String.IsNullOrEmpty(this.Configuration.Password))
            {
                localVarHeaderParams["Authorization"] = "Basic " + ApiClient.Base64Encode(this.Configuration.Username + ":" + this.Configuration.Password);
            }

            // make the HTTP request
            IRestResponse localVarResponse = (IRestResponse) this.Configuration.ApiClient.CallApi(localVarPath,
                Method.GET, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarFileParams,
                localVarPathParams, localVarHttpContentType);

            int localVarStatusCode = (int) localVarResponse.StatusCode;

            if (ExceptionFactory != null)
            {
                Exception exception = ExceptionFactory("PrivateSellGet", localVarResponse);
                if (exception != null) throw exception;
            }

            return new ApiResponse<Object>(localVarStatusCode,
                localVarResponse.Headers.ToDictionary(x => x.Name, x => string.Join(",", x.Value)),
                (Object) this.Configuration.ApiClient.Deserialize(localVarResponse, typeof(Object)));
        }

        /// <summary>
        /// Places a sell order for an instrument. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">Instrument name</param>
        /// <param name="amount">It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH</param>
        /// <param name="type">The order type, default: &#x60;\&quot;limit\&quot;&#x60; (optional)</param>
        /// <param name="label">user defined label for the order (maximum 32 characters) (optional)</param>
        /// <param name="price">&lt;p&gt;The order price in base currency (Only for limit and stop_limit orders)&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;usd, the field price should be the option price value in USD.&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;implv, the field price should be a value of implied volatility in percentages. For example,  price&#x3D;100, means implied volatility of 100%&lt;/p&gt; (optional)</param>
        /// <param name="timeInForce">&lt;p&gt;Specifies how long the order remains in effect. Default &#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; &lt;ul&gt; &lt;li&gt;&#x60;\&quot;good_til_cancelled\&quot;&#x60; - unfilled order remains in order book until cancelled&lt;/li&gt; &lt;li&gt;&#x60;\&quot;fill_or_kill\&quot;&#x60; - execute a transaction immediately and completely or not at all&lt;/li&gt; &lt;li&gt;&#x60;\&quot;immediate_or_cancel\&quot;&#x60; - execute a transaction immediately, and any portion of the order that cannot be immediately filled is cancelled&lt;/li&gt; &lt;/ul&gt; (optional, default to good_til_cancelled)</param>
        /// <param name="maxShow">Maximum amount within an order to be shown to other customers, &#x60;0&#x60; for invisible order (optional, default to 1M)</param>
        /// <param name="postOnly">&lt;p&gt;If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.&lt;/p&gt; &lt;p&gt;Only valid in combination with time_in_force&#x3D;&#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; (optional, default to true)</param>
        /// <param name="reduceOnly">If &#x60;true&#x60;, the order is considered reduce-only which is intended to only reduce a current position (optional, default to false)</param>
        /// <param name="stopPrice">Stop price, required for stop limit orders (Only for stop orders) (optional)</param>
        /// <param name="trigger">Defines trigger type, required for &#x60;\&quot;stop_limit\&quot;&#x60; order type (optional)</param>
        /// <param name="advanced">Advanced option order type. (Only for options) (optional)</param>
        /// <returns>Task of Object</returns>
        public async System.Threading.Tasks.Task<Object> PrivateSellGetAsync (string instrumentName, decimal? amount, string type = null, string label = null, decimal? price = null, string timeInForce = null, decimal? maxShow = null, bool? postOnly = null, bool? reduceOnly = null, decimal? stopPrice = null, string trigger = null, string advanced = null)
        {
             ApiResponse<Object> localVarResponse = await PrivateSellGetAsyncWithHttpInfo(instrumentName, amount, type, label, price, timeInForce, maxShow, postOnly, reduceOnly, stopPrice, trigger, advanced);
             return localVarResponse.Data;

        }

        /// <summary>
        /// Places a sell order for an instrument. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">Instrument name</param>
        /// <param name="amount">It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH</param>
        /// <param name="type">The order type, default: &#x60;\&quot;limit\&quot;&#x60; (optional)</param>
        /// <param name="label">user defined label for the order (maximum 32 characters) (optional)</param>
        /// <param name="price">&lt;p&gt;The order price in base currency (Only for limit and stop_limit orders)&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;usd, the field price should be the option price value in USD.&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;implv, the field price should be a value of implied volatility in percentages. For example,  price&#x3D;100, means implied volatility of 100%&lt;/p&gt; (optional)</param>
        /// <param name="timeInForce">&lt;p&gt;Specifies how long the order remains in effect. Default &#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; &lt;ul&gt; &lt;li&gt;&#x60;\&quot;good_til_cancelled\&quot;&#x60; - unfilled order remains in order book until cancelled&lt;/li&gt; &lt;li&gt;&#x60;\&quot;fill_or_kill\&quot;&#x60; - execute a transaction immediately and completely or not at all&lt;/li&gt; &lt;li&gt;&#x60;\&quot;immediate_or_cancel\&quot;&#x60; - execute a transaction immediately, and any portion of the order that cannot be immediately filled is cancelled&lt;/li&gt; &lt;/ul&gt; (optional, default to good_til_cancelled)</param>
        /// <param name="maxShow">Maximum amount within an order to be shown to other customers, &#x60;0&#x60; for invisible order (optional, default to 1M)</param>
        /// <param name="postOnly">&lt;p&gt;If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.&lt;/p&gt; &lt;p&gt;Only valid in combination with time_in_force&#x3D;&#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; (optional, default to true)</param>
        /// <param name="reduceOnly">If &#x60;true&#x60;, the order is considered reduce-only which is intended to only reduce a current position (optional, default to false)</param>
        /// <param name="stopPrice">Stop price, required for stop limit orders (Only for stop orders) (optional)</param>
        /// <param name="trigger">Defines trigger type, required for &#x60;\&quot;stop_limit\&quot;&#x60; order type (optional)</param>
        /// <param name="advanced">Advanced option order type. (Only for options) (optional)</param>
        /// <returns>Task of ApiResponse (Object)</returns>
        public async System.Threading.Tasks.Task<ApiResponse<Object>> PrivateSellGetAsyncWithHttpInfo (string instrumentName, decimal? amount, string type = null, string label = null, decimal? price = null, string timeInForce = null, decimal? maxShow = null, bool? postOnly = null, bool? reduceOnly = null, decimal? stopPrice = null, string trigger = null, string advanced = null)
        {
            // verify the required parameter 'instrumentName' is set
            if (instrumentName == null)
                throw new ApiException(400, "Missing required parameter 'instrumentName' when calling TradingApi->PrivateSellGet");
            // verify the required parameter 'amount' is set
            if (amount == null)
                throw new ApiException(400, "Missing required parameter 'amount' when calling TradingApi->PrivateSellGet");

            var localVarPath = "/private/sell";
            var localVarPathParams = new Dictionary<String, String>();
            var localVarQueryParams = new List<KeyValuePair<String, String>>();
            var localVarHeaderParams = new Dictionary<String, String>(this.Configuration.DefaultHeader);
            var localVarFormParams = new Dictionary<String, String>();
            var localVarFileParams = new Dictionary<String, FileParameter>();
            Object localVarPostBody = null;

            // to determine the Content-Type header
            String[] localVarHttpContentTypes = new String[] {
            };
            String localVarHttpContentType = this.Configuration.ApiClient.SelectHeaderContentType(localVarHttpContentTypes);

            // to determine the Accept header
            String[] localVarHttpHeaderAccepts = new String[] {
                "application/json"
            };
            String localVarHttpHeaderAccept = this.Configuration.ApiClient.SelectHeaderAccept(localVarHttpHeaderAccepts);
            if (localVarHttpHeaderAccept != null)
                localVarHeaderParams.Add("Accept", localVarHttpHeaderAccept);

            if (instrumentName != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "instrument_name", instrumentName)); // query parameter
            if (amount != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "amount", amount)); // query parameter
            if (type != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "type", type)); // query parameter
            if (label != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "label", label)); // query parameter
            if (price != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "price", price)); // query parameter
            if (timeInForce != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "time_in_force", timeInForce)); // query parameter
            if (maxShow != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "max_show", maxShow)); // query parameter
            if (postOnly != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "post_only", postOnly)); // query parameter
            if (reduceOnly != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "reduce_only", reduceOnly)); // query parameter
            if (stopPrice != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "stop_price", stopPrice)); // query parameter
            if (trigger != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "trigger", trigger)); // query parameter
            if (advanced != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "advanced", advanced)); // query parameter

            // authentication (bearerAuth) required
            // http basic authentication required
            if (!String.IsNullOrEmpty(this.Configuration.Username) || !String.IsNullOrEmpty(this.Configuration.Password))
            {
                localVarHeaderParams["Authorization"] = "Basic " + ApiClient.Base64Encode(this.Configuration.Username + ":" + this.Configuration.Password);
            }

            // make the HTTP request
            IRestResponse localVarResponse = (IRestResponse) await this.Configuration.ApiClient.CallApiAsync(localVarPath,
                Method.GET, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarFileParams,
                localVarPathParams, localVarHttpContentType);

            int localVarStatusCode = (int) localVarResponse.StatusCode;

            if (ExceptionFactory != null)
            {
                Exception exception = ExceptionFactory("PrivateSellGet", localVarResponse);
                if (exception != null) throw exception;
            }

            return new ApiResponse<Object>(localVarStatusCode,
                localVarResponse.Headers.ToDictionary(x => x.Name, x => string.Join(",", x.Value)),
                (Object) this.Configuration.ApiClient.Deserialize(localVarResponse, typeof(Object)));
        }

    }
}
