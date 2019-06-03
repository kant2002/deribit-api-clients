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
    public interface IWalletApi : IApiAccessor
    {
        #region Synchronous Operations
        /// <summary>
        /// Adds new entry to address book of given type
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="type">Address book type</param>
        /// <param name="address">Address in currency format, it must be in address book</param>
        /// <param name="name">Name of address book entry</param>
        /// <param name="tfa">TFA code, required when TFA is enabled for current account (optional)</param>
        /// <returns>Object</returns>
        Object PrivateAddToAddressBookGet (string currency, string type, string address, string name, string tfa = null);

        /// <summary>
        /// Adds new entry to address book of given type
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="type">Address book type</param>
        /// <param name="address">Address in currency format, it must be in address book</param>
        /// <param name="name">Name of address book entry</param>
        /// <param name="tfa">TFA code, required when TFA is enabled for current account (optional)</param>
        /// <returns>ApiResponse of Object</returns>
        ApiResponse<Object> PrivateAddToAddressBookGetWithHttpInfo (string currency, string type, string address, string name, string tfa = null);
        /// <summary>
        /// Cancel transfer
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="id">Id of transfer</param>
        /// <param name="tfa">TFA code, required when TFA is enabled for current account (optional)</param>
        /// <returns>Object</returns>
        Object PrivateCancelTransferByIdGet (string currency, int? id, string tfa = null);

        /// <summary>
        /// Cancel transfer
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="id">Id of transfer</param>
        /// <param name="tfa">TFA code, required when TFA is enabled for current account (optional)</param>
        /// <returns>ApiResponse of Object</returns>
        ApiResponse<Object> PrivateCancelTransferByIdGetWithHttpInfo (string currency, int? id, string tfa = null);
        /// <summary>
        /// Cancels withdrawal request
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="id">The withdrawal id</param>
        /// <returns>Object</returns>
        Object PrivateCancelWithdrawalGet (string currency, decimal? id);

        /// <summary>
        /// Cancels withdrawal request
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="id">The withdrawal id</param>
        /// <returns>ApiResponse of Object</returns>
        ApiResponse<Object> PrivateCancelWithdrawalGetWithHttpInfo (string currency, decimal? id);
        /// <summary>
        /// Creates deposit address in currency
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <returns>Object</returns>
        Object PrivateCreateDepositAddressGet (string currency);

        /// <summary>
        /// Creates deposit address in currency
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <returns>ApiResponse of Object</returns>
        ApiResponse<Object> PrivateCreateDepositAddressGetWithHttpInfo (string currency);
        /// <summary>
        /// Retrieves address book of given type
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="type">Address book type</param>
        /// <returns>Object</returns>
        Object PrivateGetAddressBookGet (string currency, string type);

        /// <summary>
        /// Retrieves address book of given type
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="type">Address book type</param>
        /// <returns>ApiResponse of Object</returns>
        ApiResponse<Object> PrivateGetAddressBookGetWithHttpInfo (string currency, string type);
        /// <summary>
        /// Retrieve deposit address for currency
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <returns>Object</returns>
        Object PrivateGetCurrentDepositAddressGet (string currency);

        /// <summary>
        /// Retrieve deposit address for currency
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <returns>ApiResponse of Object</returns>
        ApiResponse<Object> PrivateGetCurrentDepositAddressGetWithHttpInfo (string currency);
        /// <summary>
        /// Retrieve the latest users deposits
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="count">Number of requested items, default - &#x60;10&#x60; (optional)</param>
        /// <param name="offset">The offset for pagination, default - &#x60;0&#x60; (optional)</param>
        /// <returns>Object</returns>
        Object PrivateGetDepositsGet (string currency, int? count = null, int? offset = null);

        /// <summary>
        /// Retrieve the latest users deposits
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="count">Number of requested items, default - &#x60;10&#x60; (optional)</param>
        /// <param name="offset">The offset for pagination, default - &#x60;0&#x60; (optional)</param>
        /// <returns>ApiResponse of Object</returns>
        ApiResponse<Object> PrivateGetDepositsGetWithHttpInfo (string currency, int? count = null, int? offset = null);
        /// <summary>
        /// Adds new entry to address book of given type
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="count">Number of requested items, default - &#x60;10&#x60; (optional)</param>
        /// <param name="offset">The offset for pagination, default - &#x60;0&#x60; (optional)</param>
        /// <returns>Object</returns>
        Object PrivateGetTransfersGet (string currency, int? count = null, int? offset = null);

        /// <summary>
        /// Adds new entry to address book of given type
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="count">Number of requested items, default - &#x60;10&#x60; (optional)</param>
        /// <param name="offset">The offset for pagination, default - &#x60;0&#x60; (optional)</param>
        /// <returns>ApiResponse of Object</returns>
        ApiResponse<Object> PrivateGetTransfersGetWithHttpInfo (string currency, int? count = null, int? offset = null);
        /// <summary>
        /// Retrieve the latest users withdrawals
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="count">Number of requested items, default - &#x60;10&#x60; (optional)</param>
        /// <param name="offset">The offset for pagination, default - &#x60;0&#x60; (optional)</param>
        /// <returns>Object</returns>
        Object PrivateGetWithdrawalsGet (string currency, int? count = null, int? offset = null);

        /// <summary>
        /// Retrieve the latest users withdrawals
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="count">Number of requested items, default - &#x60;10&#x60; (optional)</param>
        /// <param name="offset">The offset for pagination, default - &#x60;0&#x60; (optional)</param>
        /// <returns>ApiResponse of Object</returns>
        ApiResponse<Object> PrivateGetWithdrawalsGetWithHttpInfo (string currency, int? count = null, int? offset = null);
        /// <summary>
        /// Adds new entry to address book of given type
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="type">Address book type</param>
        /// <param name="address">Address in currency format, it must be in address book</param>
        /// <param name="tfa">TFA code, required when TFA is enabled for current account (optional)</param>
        /// <returns>Object</returns>
        Object PrivateRemoveFromAddressBookGet (string currency, string type, string address, string tfa = null);

        /// <summary>
        /// Adds new entry to address book of given type
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="type">Address book type</param>
        /// <param name="address">Address in currency format, it must be in address book</param>
        /// <param name="tfa">TFA code, required when TFA is enabled for current account (optional)</param>
        /// <returns>ApiResponse of Object</returns>
        ApiResponse<Object> PrivateRemoveFromAddressBookGetWithHttpInfo (string currency, string type, string address, string tfa = null);
        /// <summary>
        /// Transfer funds to a subaccount.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="amount">Amount of funds to be transferred</param>
        /// <param name="destination">Id of destination subaccount</param>
        /// <returns>Object</returns>
        Object PrivateSubmitTransferToSubaccountGet (string currency, decimal? amount, int? destination);

        /// <summary>
        /// Transfer funds to a subaccount.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="amount">Amount of funds to be transferred</param>
        /// <param name="destination">Id of destination subaccount</param>
        /// <returns>ApiResponse of Object</returns>
        ApiResponse<Object> PrivateSubmitTransferToSubaccountGetWithHttpInfo (string currency, decimal? amount, int? destination);
        /// <summary>
        /// Transfer funds to a another user.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="amount">Amount of funds to be transferred</param>
        /// <param name="destination">Destination address from address book</param>
        /// <param name="tfa">TFA code, required when TFA is enabled for current account (optional)</param>
        /// <returns>Object</returns>
        Object PrivateSubmitTransferToUserGet (string currency, decimal? amount, string destination, string tfa = null);

        /// <summary>
        /// Transfer funds to a another user.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="amount">Amount of funds to be transferred</param>
        /// <param name="destination">Destination address from address book</param>
        /// <param name="tfa">TFA code, required when TFA is enabled for current account (optional)</param>
        /// <returns>ApiResponse of Object</returns>
        ApiResponse<Object> PrivateSubmitTransferToUserGetWithHttpInfo (string currency, decimal? amount, string destination, string tfa = null);
        /// <summary>
        /// Enable or disable deposit address creation
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="state"></param>
        /// <returns>Object</returns>
        Object PrivateToggleDepositAddressCreationGet (string currency, bool? state);

        /// <summary>
        /// Enable or disable deposit address creation
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="state"></param>
        /// <returns>ApiResponse of Object</returns>
        ApiResponse<Object> PrivateToggleDepositAddressCreationGetWithHttpInfo (string currency, bool? state);
        /// <summary>
        /// Creates a new withdrawal request
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="address">Address in currency format, it must be in address book</param>
        /// <param name="amount">Amount of funds to be withdrawn</param>
        /// <param name="priority">Withdrawal priority, optional for BTC, default: &#x60;high&#x60; (optional)</param>
        /// <param name="tfa">TFA code, required when TFA is enabled for current account (optional)</param>
        /// <returns>Object</returns>
        Object PrivateWithdrawGet (string currency, string address, decimal? amount, string priority = null, string tfa = null);

        /// <summary>
        /// Creates a new withdrawal request
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="address">Address in currency format, it must be in address book</param>
        /// <param name="amount">Amount of funds to be withdrawn</param>
        /// <param name="priority">Withdrawal priority, optional for BTC, default: &#x60;high&#x60; (optional)</param>
        /// <param name="tfa">TFA code, required when TFA is enabled for current account (optional)</param>
        /// <returns>ApiResponse of Object</returns>
        ApiResponse<Object> PrivateWithdrawGetWithHttpInfo (string currency, string address, decimal? amount, string priority = null, string tfa = null);
        #endregion Synchronous Operations
        #region Asynchronous Operations
        /// <summary>
        /// Adds new entry to address book of given type
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="type">Address book type</param>
        /// <param name="address">Address in currency format, it must be in address book</param>
        /// <param name="name">Name of address book entry</param>
        /// <param name="tfa">TFA code, required when TFA is enabled for current account (optional)</param>
        /// <returns>Task of Object</returns>
        System.Threading.Tasks.Task<Object> PrivateAddToAddressBookGetAsync (string currency, string type, string address, string name, string tfa = null);

        /// <summary>
        /// Adds new entry to address book of given type
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="type">Address book type</param>
        /// <param name="address">Address in currency format, it must be in address book</param>
        /// <param name="name">Name of address book entry</param>
        /// <param name="tfa">TFA code, required when TFA is enabled for current account (optional)</param>
        /// <returns>Task of ApiResponse (Object)</returns>
        System.Threading.Tasks.Task<ApiResponse<Object>> PrivateAddToAddressBookGetAsyncWithHttpInfo (string currency, string type, string address, string name, string tfa = null);
        /// <summary>
        /// Cancel transfer
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="id">Id of transfer</param>
        /// <param name="tfa">TFA code, required when TFA is enabled for current account (optional)</param>
        /// <returns>Task of Object</returns>
        System.Threading.Tasks.Task<Object> PrivateCancelTransferByIdGetAsync (string currency, int? id, string tfa = null);

        /// <summary>
        /// Cancel transfer
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="id">Id of transfer</param>
        /// <param name="tfa">TFA code, required when TFA is enabled for current account (optional)</param>
        /// <returns>Task of ApiResponse (Object)</returns>
        System.Threading.Tasks.Task<ApiResponse<Object>> PrivateCancelTransferByIdGetAsyncWithHttpInfo (string currency, int? id, string tfa = null);
        /// <summary>
        /// Cancels withdrawal request
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="id">The withdrawal id</param>
        /// <returns>Task of Object</returns>
        System.Threading.Tasks.Task<Object> PrivateCancelWithdrawalGetAsync (string currency, decimal? id);

        /// <summary>
        /// Cancels withdrawal request
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="id">The withdrawal id</param>
        /// <returns>Task of ApiResponse (Object)</returns>
        System.Threading.Tasks.Task<ApiResponse<Object>> PrivateCancelWithdrawalGetAsyncWithHttpInfo (string currency, decimal? id);
        /// <summary>
        /// Creates deposit address in currency
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <returns>Task of Object</returns>
        System.Threading.Tasks.Task<Object> PrivateCreateDepositAddressGetAsync (string currency);

        /// <summary>
        /// Creates deposit address in currency
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <returns>Task of ApiResponse (Object)</returns>
        System.Threading.Tasks.Task<ApiResponse<Object>> PrivateCreateDepositAddressGetAsyncWithHttpInfo (string currency);
        /// <summary>
        /// Retrieves address book of given type
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="type">Address book type</param>
        /// <returns>Task of Object</returns>
        System.Threading.Tasks.Task<Object> PrivateGetAddressBookGetAsync (string currency, string type);

        /// <summary>
        /// Retrieves address book of given type
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="type">Address book type</param>
        /// <returns>Task of ApiResponse (Object)</returns>
        System.Threading.Tasks.Task<ApiResponse<Object>> PrivateGetAddressBookGetAsyncWithHttpInfo (string currency, string type);
        /// <summary>
        /// Retrieve deposit address for currency
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <returns>Task of Object</returns>
        System.Threading.Tasks.Task<Object> PrivateGetCurrentDepositAddressGetAsync (string currency);

        /// <summary>
        /// Retrieve deposit address for currency
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <returns>Task of ApiResponse (Object)</returns>
        System.Threading.Tasks.Task<ApiResponse<Object>> PrivateGetCurrentDepositAddressGetAsyncWithHttpInfo (string currency);
        /// <summary>
        /// Retrieve the latest users deposits
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="count">Number of requested items, default - &#x60;10&#x60; (optional)</param>
        /// <param name="offset">The offset for pagination, default - &#x60;0&#x60; (optional)</param>
        /// <returns>Task of Object</returns>
        System.Threading.Tasks.Task<Object> PrivateGetDepositsGetAsync (string currency, int? count = null, int? offset = null);

        /// <summary>
        /// Retrieve the latest users deposits
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="count">Number of requested items, default - &#x60;10&#x60; (optional)</param>
        /// <param name="offset">The offset for pagination, default - &#x60;0&#x60; (optional)</param>
        /// <returns>Task of ApiResponse (Object)</returns>
        System.Threading.Tasks.Task<ApiResponse<Object>> PrivateGetDepositsGetAsyncWithHttpInfo (string currency, int? count = null, int? offset = null);
        /// <summary>
        /// Adds new entry to address book of given type
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="count">Number of requested items, default - &#x60;10&#x60; (optional)</param>
        /// <param name="offset">The offset for pagination, default - &#x60;0&#x60; (optional)</param>
        /// <returns>Task of Object</returns>
        System.Threading.Tasks.Task<Object> PrivateGetTransfersGetAsync (string currency, int? count = null, int? offset = null);

        /// <summary>
        /// Adds new entry to address book of given type
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="count">Number of requested items, default - &#x60;10&#x60; (optional)</param>
        /// <param name="offset">The offset for pagination, default - &#x60;0&#x60; (optional)</param>
        /// <returns>Task of ApiResponse (Object)</returns>
        System.Threading.Tasks.Task<ApiResponse<Object>> PrivateGetTransfersGetAsyncWithHttpInfo (string currency, int? count = null, int? offset = null);
        /// <summary>
        /// Retrieve the latest users withdrawals
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="count">Number of requested items, default - &#x60;10&#x60; (optional)</param>
        /// <param name="offset">The offset for pagination, default - &#x60;0&#x60; (optional)</param>
        /// <returns>Task of Object</returns>
        System.Threading.Tasks.Task<Object> PrivateGetWithdrawalsGetAsync (string currency, int? count = null, int? offset = null);

        /// <summary>
        /// Retrieve the latest users withdrawals
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="count">Number of requested items, default - &#x60;10&#x60; (optional)</param>
        /// <param name="offset">The offset for pagination, default - &#x60;0&#x60; (optional)</param>
        /// <returns>Task of ApiResponse (Object)</returns>
        System.Threading.Tasks.Task<ApiResponse<Object>> PrivateGetWithdrawalsGetAsyncWithHttpInfo (string currency, int? count = null, int? offset = null);
        /// <summary>
        /// Adds new entry to address book of given type
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="type">Address book type</param>
        /// <param name="address">Address in currency format, it must be in address book</param>
        /// <param name="tfa">TFA code, required when TFA is enabled for current account (optional)</param>
        /// <returns>Task of Object</returns>
        System.Threading.Tasks.Task<Object> PrivateRemoveFromAddressBookGetAsync (string currency, string type, string address, string tfa = null);

        /// <summary>
        /// Adds new entry to address book of given type
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="type">Address book type</param>
        /// <param name="address">Address in currency format, it must be in address book</param>
        /// <param name="tfa">TFA code, required when TFA is enabled for current account (optional)</param>
        /// <returns>Task of ApiResponse (Object)</returns>
        System.Threading.Tasks.Task<ApiResponse<Object>> PrivateRemoveFromAddressBookGetAsyncWithHttpInfo (string currency, string type, string address, string tfa = null);
        /// <summary>
        /// Transfer funds to a subaccount.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="amount">Amount of funds to be transferred</param>
        /// <param name="destination">Id of destination subaccount</param>
        /// <returns>Task of Object</returns>
        System.Threading.Tasks.Task<Object> PrivateSubmitTransferToSubaccountGetAsync (string currency, decimal? amount, int? destination);

        /// <summary>
        /// Transfer funds to a subaccount.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="amount">Amount of funds to be transferred</param>
        /// <param name="destination">Id of destination subaccount</param>
        /// <returns>Task of ApiResponse (Object)</returns>
        System.Threading.Tasks.Task<ApiResponse<Object>> PrivateSubmitTransferToSubaccountGetAsyncWithHttpInfo (string currency, decimal? amount, int? destination);
        /// <summary>
        /// Transfer funds to a another user.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="amount">Amount of funds to be transferred</param>
        /// <param name="destination">Destination address from address book</param>
        /// <param name="tfa">TFA code, required when TFA is enabled for current account (optional)</param>
        /// <returns>Task of Object</returns>
        System.Threading.Tasks.Task<Object> PrivateSubmitTransferToUserGetAsync (string currency, decimal? amount, string destination, string tfa = null);

        /// <summary>
        /// Transfer funds to a another user.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="amount">Amount of funds to be transferred</param>
        /// <param name="destination">Destination address from address book</param>
        /// <param name="tfa">TFA code, required when TFA is enabled for current account (optional)</param>
        /// <returns>Task of ApiResponse (Object)</returns>
        System.Threading.Tasks.Task<ApiResponse<Object>> PrivateSubmitTransferToUserGetAsyncWithHttpInfo (string currency, decimal? amount, string destination, string tfa = null);
        /// <summary>
        /// Enable or disable deposit address creation
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="state"></param>
        /// <returns>Task of Object</returns>
        System.Threading.Tasks.Task<Object> PrivateToggleDepositAddressCreationGetAsync (string currency, bool? state);

        /// <summary>
        /// Enable or disable deposit address creation
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="state"></param>
        /// <returns>Task of ApiResponse (Object)</returns>
        System.Threading.Tasks.Task<ApiResponse<Object>> PrivateToggleDepositAddressCreationGetAsyncWithHttpInfo (string currency, bool? state);
        /// <summary>
        /// Creates a new withdrawal request
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="address">Address in currency format, it must be in address book</param>
        /// <param name="amount">Amount of funds to be withdrawn</param>
        /// <param name="priority">Withdrawal priority, optional for BTC, default: &#x60;high&#x60; (optional)</param>
        /// <param name="tfa">TFA code, required when TFA is enabled for current account (optional)</param>
        /// <returns>Task of Object</returns>
        System.Threading.Tasks.Task<Object> PrivateWithdrawGetAsync (string currency, string address, decimal? amount, string priority = null, string tfa = null);

        /// <summary>
        /// Creates a new withdrawal request
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="address">Address in currency format, it must be in address book</param>
        /// <param name="amount">Amount of funds to be withdrawn</param>
        /// <param name="priority">Withdrawal priority, optional for BTC, default: &#x60;high&#x60; (optional)</param>
        /// <param name="tfa">TFA code, required when TFA is enabled for current account (optional)</param>
        /// <returns>Task of ApiResponse (Object)</returns>
        System.Threading.Tasks.Task<ApiResponse<Object>> PrivateWithdrawGetAsyncWithHttpInfo (string currency, string address, decimal? amount, string priority = null, string tfa = null);
        #endregion Asynchronous Operations
    }

    /// <summary>
    /// Represents a collection of functions to interact with the API endpoints
    /// </summary>
    public partial class WalletApi : IWalletApi
    {
        private Org.OpenAPITools.Client.ExceptionFactory _exceptionFactory = (name, response) => null;

        /// <summary>
        /// Initializes a new instance of the <see cref="WalletApi"/> class.
        /// </summary>
        /// <returns></returns>
        public WalletApi(String basePath)
        {
            this.Configuration = new Org.OpenAPITools.Client.Configuration { BasePath = basePath };

            ExceptionFactory = Org.OpenAPITools.Client.Configuration.DefaultExceptionFactory;
        }

        /// <summary>
        /// Initializes a new instance of the <see cref="WalletApi"/> class
        /// </summary>
        /// <returns></returns>
        public WalletApi()
        {
            this.Configuration = Org.OpenAPITools.Client.Configuration.Default;

            ExceptionFactory = Org.OpenAPITools.Client.Configuration.DefaultExceptionFactory;
        }

        /// <summary>
        /// Initializes a new instance of the <see cref="WalletApi"/> class
        /// using Configuration object
        /// </summary>
        /// <param name="configuration">An instance of Configuration</param>
        /// <returns></returns>
        public WalletApi(Org.OpenAPITools.Client.Configuration configuration = null)
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
        /// Adds new entry to address book of given type 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="type">Address book type</param>
        /// <param name="address">Address in currency format, it must be in address book</param>
        /// <param name="name">Name of address book entry</param>
        /// <param name="tfa">TFA code, required when TFA is enabled for current account (optional)</param>
        /// <returns>Object</returns>
        public Object PrivateAddToAddressBookGet (string currency, string type, string address, string name, string tfa = null)
        {
             ApiResponse<Object> localVarResponse = PrivateAddToAddressBookGetWithHttpInfo(currency, type, address, name, tfa);
             return localVarResponse.Data;
        }

        /// <summary>
        /// Adds new entry to address book of given type 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="type">Address book type</param>
        /// <param name="address">Address in currency format, it must be in address book</param>
        /// <param name="name">Name of address book entry</param>
        /// <param name="tfa">TFA code, required when TFA is enabled for current account (optional)</param>
        /// <returns>ApiResponse of Object</returns>
        public ApiResponse< Object > PrivateAddToAddressBookGetWithHttpInfo (string currency, string type, string address, string name, string tfa = null)
        {
            // verify the required parameter 'currency' is set
            if (currency == null)
                throw new ApiException(400, "Missing required parameter 'currency' when calling WalletApi->PrivateAddToAddressBookGet");
            // verify the required parameter 'type' is set
            if (type == null)
                throw new ApiException(400, "Missing required parameter 'type' when calling WalletApi->PrivateAddToAddressBookGet");
            // verify the required parameter 'address' is set
            if (address == null)
                throw new ApiException(400, "Missing required parameter 'address' when calling WalletApi->PrivateAddToAddressBookGet");
            // verify the required parameter 'name' is set
            if (name == null)
                throw new ApiException(400, "Missing required parameter 'name' when calling WalletApi->PrivateAddToAddressBookGet");

            var localVarPath = "/private/add_to_address_book";
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
            if (address != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "address", address)); // query parameter
            if (name != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "name", name)); // query parameter
            if (tfa != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "tfa", tfa)); // query parameter

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
                Exception exception = ExceptionFactory("PrivateAddToAddressBookGet", localVarResponse);
                if (exception != null) throw exception;
            }

            return new ApiResponse<Object>(localVarStatusCode,
                localVarResponse.Headers.ToDictionary(x => x.Name, x => string.Join(",", x.Value)),
                (Object) this.Configuration.ApiClient.Deserialize(localVarResponse, typeof(Object)));
        }

        /// <summary>
        /// Adds new entry to address book of given type 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="type">Address book type</param>
        /// <param name="address">Address in currency format, it must be in address book</param>
        /// <param name="name">Name of address book entry</param>
        /// <param name="tfa">TFA code, required when TFA is enabled for current account (optional)</param>
        /// <returns>Task of Object</returns>
        public async System.Threading.Tasks.Task<Object> PrivateAddToAddressBookGetAsync (string currency, string type, string address, string name, string tfa = null)
        {
             ApiResponse<Object> localVarResponse = await PrivateAddToAddressBookGetAsyncWithHttpInfo(currency, type, address, name, tfa);
             return localVarResponse.Data;

        }

        /// <summary>
        /// Adds new entry to address book of given type 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="type">Address book type</param>
        /// <param name="address">Address in currency format, it must be in address book</param>
        /// <param name="name">Name of address book entry</param>
        /// <param name="tfa">TFA code, required when TFA is enabled for current account (optional)</param>
        /// <returns>Task of ApiResponse (Object)</returns>
        public async System.Threading.Tasks.Task<ApiResponse<Object>> PrivateAddToAddressBookGetAsyncWithHttpInfo (string currency, string type, string address, string name, string tfa = null)
        {
            // verify the required parameter 'currency' is set
            if (currency == null)
                throw new ApiException(400, "Missing required parameter 'currency' when calling WalletApi->PrivateAddToAddressBookGet");
            // verify the required parameter 'type' is set
            if (type == null)
                throw new ApiException(400, "Missing required parameter 'type' when calling WalletApi->PrivateAddToAddressBookGet");
            // verify the required parameter 'address' is set
            if (address == null)
                throw new ApiException(400, "Missing required parameter 'address' when calling WalletApi->PrivateAddToAddressBookGet");
            // verify the required parameter 'name' is set
            if (name == null)
                throw new ApiException(400, "Missing required parameter 'name' when calling WalletApi->PrivateAddToAddressBookGet");

            var localVarPath = "/private/add_to_address_book";
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
            if (address != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "address", address)); // query parameter
            if (name != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "name", name)); // query parameter
            if (tfa != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "tfa", tfa)); // query parameter

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
                Exception exception = ExceptionFactory("PrivateAddToAddressBookGet", localVarResponse);
                if (exception != null) throw exception;
            }

            return new ApiResponse<Object>(localVarStatusCode,
                localVarResponse.Headers.ToDictionary(x => x.Name, x => string.Join(",", x.Value)),
                (Object) this.Configuration.ApiClient.Deserialize(localVarResponse, typeof(Object)));
        }

        /// <summary>
        /// Cancel transfer 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="id">Id of transfer</param>
        /// <param name="tfa">TFA code, required when TFA is enabled for current account (optional)</param>
        /// <returns>Object</returns>
        public Object PrivateCancelTransferByIdGet (string currency, int? id, string tfa = null)
        {
             ApiResponse<Object> localVarResponse = PrivateCancelTransferByIdGetWithHttpInfo(currency, id, tfa);
             return localVarResponse.Data;
        }

        /// <summary>
        /// Cancel transfer 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="id">Id of transfer</param>
        /// <param name="tfa">TFA code, required when TFA is enabled for current account (optional)</param>
        /// <returns>ApiResponse of Object</returns>
        public ApiResponse< Object > PrivateCancelTransferByIdGetWithHttpInfo (string currency, int? id, string tfa = null)
        {
            // verify the required parameter 'currency' is set
            if (currency == null)
                throw new ApiException(400, "Missing required parameter 'currency' when calling WalletApi->PrivateCancelTransferByIdGet");
            // verify the required parameter 'id' is set
            if (id == null)
                throw new ApiException(400, "Missing required parameter 'id' when calling WalletApi->PrivateCancelTransferByIdGet");

            var localVarPath = "/private/cancel_transfer_by_id";
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
            if (id != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "id", id)); // query parameter
            if (tfa != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "tfa", tfa)); // query parameter

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
                Exception exception = ExceptionFactory("PrivateCancelTransferByIdGet", localVarResponse);
                if (exception != null) throw exception;
            }

            return new ApiResponse<Object>(localVarStatusCode,
                localVarResponse.Headers.ToDictionary(x => x.Name, x => string.Join(",", x.Value)),
                (Object) this.Configuration.ApiClient.Deserialize(localVarResponse, typeof(Object)));
        }

        /// <summary>
        /// Cancel transfer 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="id">Id of transfer</param>
        /// <param name="tfa">TFA code, required when TFA is enabled for current account (optional)</param>
        /// <returns>Task of Object</returns>
        public async System.Threading.Tasks.Task<Object> PrivateCancelTransferByIdGetAsync (string currency, int? id, string tfa = null)
        {
             ApiResponse<Object> localVarResponse = await PrivateCancelTransferByIdGetAsyncWithHttpInfo(currency, id, tfa);
             return localVarResponse.Data;

        }

        /// <summary>
        /// Cancel transfer 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="id">Id of transfer</param>
        /// <param name="tfa">TFA code, required when TFA is enabled for current account (optional)</param>
        /// <returns>Task of ApiResponse (Object)</returns>
        public async System.Threading.Tasks.Task<ApiResponse<Object>> PrivateCancelTransferByIdGetAsyncWithHttpInfo (string currency, int? id, string tfa = null)
        {
            // verify the required parameter 'currency' is set
            if (currency == null)
                throw new ApiException(400, "Missing required parameter 'currency' when calling WalletApi->PrivateCancelTransferByIdGet");
            // verify the required parameter 'id' is set
            if (id == null)
                throw new ApiException(400, "Missing required parameter 'id' when calling WalletApi->PrivateCancelTransferByIdGet");

            var localVarPath = "/private/cancel_transfer_by_id";
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
            if (id != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "id", id)); // query parameter
            if (tfa != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "tfa", tfa)); // query parameter

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
                Exception exception = ExceptionFactory("PrivateCancelTransferByIdGet", localVarResponse);
                if (exception != null) throw exception;
            }

            return new ApiResponse<Object>(localVarStatusCode,
                localVarResponse.Headers.ToDictionary(x => x.Name, x => string.Join(",", x.Value)),
                (Object) this.Configuration.ApiClient.Deserialize(localVarResponse, typeof(Object)));
        }

        /// <summary>
        /// Cancels withdrawal request 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="id">The withdrawal id</param>
        /// <returns>Object</returns>
        public Object PrivateCancelWithdrawalGet (string currency, decimal? id)
        {
             ApiResponse<Object> localVarResponse = PrivateCancelWithdrawalGetWithHttpInfo(currency, id);
             return localVarResponse.Data;
        }

        /// <summary>
        /// Cancels withdrawal request 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="id">The withdrawal id</param>
        /// <returns>ApiResponse of Object</returns>
        public ApiResponse< Object > PrivateCancelWithdrawalGetWithHttpInfo (string currency, decimal? id)
        {
            // verify the required parameter 'currency' is set
            if (currency == null)
                throw new ApiException(400, "Missing required parameter 'currency' when calling WalletApi->PrivateCancelWithdrawalGet");
            // verify the required parameter 'id' is set
            if (id == null)
                throw new ApiException(400, "Missing required parameter 'id' when calling WalletApi->PrivateCancelWithdrawalGet");

            var localVarPath = "/private/cancel_withdrawal";
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
            if (id != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "id", id)); // query parameter

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
                Exception exception = ExceptionFactory("PrivateCancelWithdrawalGet", localVarResponse);
                if (exception != null) throw exception;
            }

            return new ApiResponse<Object>(localVarStatusCode,
                localVarResponse.Headers.ToDictionary(x => x.Name, x => string.Join(",", x.Value)),
                (Object) this.Configuration.ApiClient.Deserialize(localVarResponse, typeof(Object)));
        }

        /// <summary>
        /// Cancels withdrawal request 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="id">The withdrawal id</param>
        /// <returns>Task of Object</returns>
        public async System.Threading.Tasks.Task<Object> PrivateCancelWithdrawalGetAsync (string currency, decimal? id)
        {
             ApiResponse<Object> localVarResponse = await PrivateCancelWithdrawalGetAsyncWithHttpInfo(currency, id);
             return localVarResponse.Data;

        }

        /// <summary>
        /// Cancels withdrawal request 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="id">The withdrawal id</param>
        /// <returns>Task of ApiResponse (Object)</returns>
        public async System.Threading.Tasks.Task<ApiResponse<Object>> PrivateCancelWithdrawalGetAsyncWithHttpInfo (string currency, decimal? id)
        {
            // verify the required parameter 'currency' is set
            if (currency == null)
                throw new ApiException(400, "Missing required parameter 'currency' when calling WalletApi->PrivateCancelWithdrawalGet");
            // verify the required parameter 'id' is set
            if (id == null)
                throw new ApiException(400, "Missing required parameter 'id' when calling WalletApi->PrivateCancelWithdrawalGet");

            var localVarPath = "/private/cancel_withdrawal";
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
            if (id != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "id", id)); // query parameter

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
                Exception exception = ExceptionFactory("PrivateCancelWithdrawalGet", localVarResponse);
                if (exception != null) throw exception;
            }

            return new ApiResponse<Object>(localVarStatusCode,
                localVarResponse.Headers.ToDictionary(x => x.Name, x => string.Join(",", x.Value)),
                (Object) this.Configuration.ApiClient.Deserialize(localVarResponse, typeof(Object)));
        }

        /// <summary>
        /// Creates deposit address in currency 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <returns>Object</returns>
        public Object PrivateCreateDepositAddressGet (string currency)
        {
             ApiResponse<Object> localVarResponse = PrivateCreateDepositAddressGetWithHttpInfo(currency);
             return localVarResponse.Data;
        }

        /// <summary>
        /// Creates deposit address in currency 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <returns>ApiResponse of Object</returns>
        public ApiResponse< Object > PrivateCreateDepositAddressGetWithHttpInfo (string currency)
        {
            // verify the required parameter 'currency' is set
            if (currency == null)
                throw new ApiException(400, "Missing required parameter 'currency' when calling WalletApi->PrivateCreateDepositAddressGet");

            var localVarPath = "/private/create_deposit_address";
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
                Exception exception = ExceptionFactory("PrivateCreateDepositAddressGet", localVarResponse);
                if (exception != null) throw exception;
            }

            return new ApiResponse<Object>(localVarStatusCode,
                localVarResponse.Headers.ToDictionary(x => x.Name, x => string.Join(",", x.Value)),
                (Object) this.Configuration.ApiClient.Deserialize(localVarResponse, typeof(Object)));
        }

        /// <summary>
        /// Creates deposit address in currency 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <returns>Task of Object</returns>
        public async System.Threading.Tasks.Task<Object> PrivateCreateDepositAddressGetAsync (string currency)
        {
             ApiResponse<Object> localVarResponse = await PrivateCreateDepositAddressGetAsyncWithHttpInfo(currency);
             return localVarResponse.Data;

        }

        /// <summary>
        /// Creates deposit address in currency 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <returns>Task of ApiResponse (Object)</returns>
        public async System.Threading.Tasks.Task<ApiResponse<Object>> PrivateCreateDepositAddressGetAsyncWithHttpInfo (string currency)
        {
            // verify the required parameter 'currency' is set
            if (currency == null)
                throw new ApiException(400, "Missing required parameter 'currency' when calling WalletApi->PrivateCreateDepositAddressGet");

            var localVarPath = "/private/create_deposit_address";
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
                Exception exception = ExceptionFactory("PrivateCreateDepositAddressGet", localVarResponse);
                if (exception != null) throw exception;
            }

            return new ApiResponse<Object>(localVarStatusCode,
                localVarResponse.Headers.ToDictionary(x => x.Name, x => string.Join(",", x.Value)),
                (Object) this.Configuration.ApiClient.Deserialize(localVarResponse, typeof(Object)));
        }

        /// <summary>
        /// Retrieves address book of given type 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="type">Address book type</param>
        /// <returns>Object</returns>
        public Object PrivateGetAddressBookGet (string currency, string type)
        {
             ApiResponse<Object> localVarResponse = PrivateGetAddressBookGetWithHttpInfo(currency, type);
             return localVarResponse.Data;
        }

        /// <summary>
        /// Retrieves address book of given type 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="type">Address book type</param>
        /// <returns>ApiResponse of Object</returns>
        public ApiResponse< Object > PrivateGetAddressBookGetWithHttpInfo (string currency, string type)
        {
            // verify the required parameter 'currency' is set
            if (currency == null)
                throw new ApiException(400, "Missing required parameter 'currency' when calling WalletApi->PrivateGetAddressBookGet");
            // verify the required parameter 'type' is set
            if (type == null)
                throw new ApiException(400, "Missing required parameter 'type' when calling WalletApi->PrivateGetAddressBookGet");

            var localVarPath = "/private/get_address_book";
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
                Exception exception = ExceptionFactory("PrivateGetAddressBookGet", localVarResponse);
                if (exception != null) throw exception;
            }

            return new ApiResponse<Object>(localVarStatusCode,
                localVarResponse.Headers.ToDictionary(x => x.Name, x => string.Join(",", x.Value)),
                (Object) this.Configuration.ApiClient.Deserialize(localVarResponse, typeof(Object)));
        }

        /// <summary>
        /// Retrieves address book of given type 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="type">Address book type</param>
        /// <returns>Task of Object</returns>
        public async System.Threading.Tasks.Task<Object> PrivateGetAddressBookGetAsync (string currency, string type)
        {
             ApiResponse<Object> localVarResponse = await PrivateGetAddressBookGetAsyncWithHttpInfo(currency, type);
             return localVarResponse.Data;

        }

        /// <summary>
        /// Retrieves address book of given type 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="type">Address book type</param>
        /// <returns>Task of ApiResponse (Object)</returns>
        public async System.Threading.Tasks.Task<ApiResponse<Object>> PrivateGetAddressBookGetAsyncWithHttpInfo (string currency, string type)
        {
            // verify the required parameter 'currency' is set
            if (currency == null)
                throw new ApiException(400, "Missing required parameter 'currency' when calling WalletApi->PrivateGetAddressBookGet");
            // verify the required parameter 'type' is set
            if (type == null)
                throw new ApiException(400, "Missing required parameter 'type' when calling WalletApi->PrivateGetAddressBookGet");

            var localVarPath = "/private/get_address_book";
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
                Exception exception = ExceptionFactory("PrivateGetAddressBookGet", localVarResponse);
                if (exception != null) throw exception;
            }

            return new ApiResponse<Object>(localVarStatusCode,
                localVarResponse.Headers.ToDictionary(x => x.Name, x => string.Join(",", x.Value)),
                (Object) this.Configuration.ApiClient.Deserialize(localVarResponse, typeof(Object)));
        }

        /// <summary>
        /// Retrieve deposit address for currency 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <returns>Object</returns>
        public Object PrivateGetCurrentDepositAddressGet (string currency)
        {
             ApiResponse<Object> localVarResponse = PrivateGetCurrentDepositAddressGetWithHttpInfo(currency);
             return localVarResponse.Data;
        }

        /// <summary>
        /// Retrieve deposit address for currency 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <returns>ApiResponse of Object</returns>
        public ApiResponse< Object > PrivateGetCurrentDepositAddressGetWithHttpInfo (string currency)
        {
            // verify the required parameter 'currency' is set
            if (currency == null)
                throw new ApiException(400, "Missing required parameter 'currency' when calling WalletApi->PrivateGetCurrentDepositAddressGet");

            var localVarPath = "/private/get_current_deposit_address";
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
                Exception exception = ExceptionFactory("PrivateGetCurrentDepositAddressGet", localVarResponse);
                if (exception != null) throw exception;
            }

            return new ApiResponse<Object>(localVarStatusCode,
                localVarResponse.Headers.ToDictionary(x => x.Name, x => string.Join(",", x.Value)),
                (Object) this.Configuration.ApiClient.Deserialize(localVarResponse, typeof(Object)));
        }

        /// <summary>
        /// Retrieve deposit address for currency 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <returns>Task of Object</returns>
        public async System.Threading.Tasks.Task<Object> PrivateGetCurrentDepositAddressGetAsync (string currency)
        {
             ApiResponse<Object> localVarResponse = await PrivateGetCurrentDepositAddressGetAsyncWithHttpInfo(currency);
             return localVarResponse.Data;

        }

        /// <summary>
        /// Retrieve deposit address for currency 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <returns>Task of ApiResponse (Object)</returns>
        public async System.Threading.Tasks.Task<ApiResponse<Object>> PrivateGetCurrentDepositAddressGetAsyncWithHttpInfo (string currency)
        {
            // verify the required parameter 'currency' is set
            if (currency == null)
                throw new ApiException(400, "Missing required parameter 'currency' when calling WalletApi->PrivateGetCurrentDepositAddressGet");

            var localVarPath = "/private/get_current_deposit_address";
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
                Exception exception = ExceptionFactory("PrivateGetCurrentDepositAddressGet", localVarResponse);
                if (exception != null) throw exception;
            }

            return new ApiResponse<Object>(localVarStatusCode,
                localVarResponse.Headers.ToDictionary(x => x.Name, x => string.Join(",", x.Value)),
                (Object) this.Configuration.ApiClient.Deserialize(localVarResponse, typeof(Object)));
        }

        /// <summary>
        /// Retrieve the latest users deposits 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="count">Number of requested items, default - &#x60;10&#x60; (optional)</param>
        /// <param name="offset">The offset for pagination, default - &#x60;0&#x60; (optional)</param>
        /// <returns>Object</returns>
        public Object PrivateGetDepositsGet (string currency, int? count = null, int? offset = null)
        {
             ApiResponse<Object> localVarResponse = PrivateGetDepositsGetWithHttpInfo(currency, count, offset);
             return localVarResponse.Data;
        }

        /// <summary>
        /// Retrieve the latest users deposits 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="count">Number of requested items, default - &#x60;10&#x60; (optional)</param>
        /// <param name="offset">The offset for pagination, default - &#x60;0&#x60; (optional)</param>
        /// <returns>ApiResponse of Object</returns>
        public ApiResponse< Object > PrivateGetDepositsGetWithHttpInfo (string currency, int? count = null, int? offset = null)
        {
            // verify the required parameter 'currency' is set
            if (currency == null)
                throw new ApiException(400, "Missing required parameter 'currency' when calling WalletApi->PrivateGetDepositsGet");

            var localVarPath = "/private/get_deposits";
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
            if (count != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "count", count)); // query parameter
            if (offset != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "offset", offset)); // query parameter

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
                Exception exception = ExceptionFactory("PrivateGetDepositsGet", localVarResponse);
                if (exception != null) throw exception;
            }

            return new ApiResponse<Object>(localVarStatusCode,
                localVarResponse.Headers.ToDictionary(x => x.Name, x => string.Join(",", x.Value)),
                (Object) this.Configuration.ApiClient.Deserialize(localVarResponse, typeof(Object)));
        }

        /// <summary>
        /// Retrieve the latest users deposits 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="count">Number of requested items, default - &#x60;10&#x60; (optional)</param>
        /// <param name="offset">The offset for pagination, default - &#x60;0&#x60; (optional)</param>
        /// <returns>Task of Object</returns>
        public async System.Threading.Tasks.Task<Object> PrivateGetDepositsGetAsync (string currency, int? count = null, int? offset = null)
        {
             ApiResponse<Object> localVarResponse = await PrivateGetDepositsGetAsyncWithHttpInfo(currency, count, offset);
             return localVarResponse.Data;

        }

        /// <summary>
        /// Retrieve the latest users deposits 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="count">Number of requested items, default - &#x60;10&#x60; (optional)</param>
        /// <param name="offset">The offset for pagination, default - &#x60;0&#x60; (optional)</param>
        /// <returns>Task of ApiResponse (Object)</returns>
        public async System.Threading.Tasks.Task<ApiResponse<Object>> PrivateGetDepositsGetAsyncWithHttpInfo (string currency, int? count = null, int? offset = null)
        {
            // verify the required parameter 'currency' is set
            if (currency == null)
                throw new ApiException(400, "Missing required parameter 'currency' when calling WalletApi->PrivateGetDepositsGet");

            var localVarPath = "/private/get_deposits";
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
            if (count != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "count", count)); // query parameter
            if (offset != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "offset", offset)); // query parameter

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
                Exception exception = ExceptionFactory("PrivateGetDepositsGet", localVarResponse);
                if (exception != null) throw exception;
            }

            return new ApiResponse<Object>(localVarStatusCode,
                localVarResponse.Headers.ToDictionary(x => x.Name, x => string.Join(",", x.Value)),
                (Object) this.Configuration.ApiClient.Deserialize(localVarResponse, typeof(Object)));
        }

        /// <summary>
        /// Adds new entry to address book of given type 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="count">Number of requested items, default - &#x60;10&#x60; (optional)</param>
        /// <param name="offset">The offset for pagination, default - &#x60;0&#x60; (optional)</param>
        /// <returns>Object</returns>
        public Object PrivateGetTransfersGet (string currency, int? count = null, int? offset = null)
        {
             ApiResponse<Object> localVarResponse = PrivateGetTransfersGetWithHttpInfo(currency, count, offset);
             return localVarResponse.Data;
        }

        /// <summary>
        /// Adds new entry to address book of given type 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="count">Number of requested items, default - &#x60;10&#x60; (optional)</param>
        /// <param name="offset">The offset for pagination, default - &#x60;0&#x60; (optional)</param>
        /// <returns>ApiResponse of Object</returns>
        public ApiResponse< Object > PrivateGetTransfersGetWithHttpInfo (string currency, int? count = null, int? offset = null)
        {
            // verify the required parameter 'currency' is set
            if (currency == null)
                throw new ApiException(400, "Missing required parameter 'currency' when calling WalletApi->PrivateGetTransfersGet");

            var localVarPath = "/private/get_transfers";
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
            if (count != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "count", count)); // query parameter
            if (offset != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "offset", offset)); // query parameter

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
                Exception exception = ExceptionFactory("PrivateGetTransfersGet", localVarResponse);
                if (exception != null) throw exception;
            }

            return new ApiResponse<Object>(localVarStatusCode,
                localVarResponse.Headers.ToDictionary(x => x.Name, x => string.Join(",", x.Value)),
                (Object) this.Configuration.ApiClient.Deserialize(localVarResponse, typeof(Object)));
        }

        /// <summary>
        /// Adds new entry to address book of given type 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="count">Number of requested items, default - &#x60;10&#x60; (optional)</param>
        /// <param name="offset">The offset for pagination, default - &#x60;0&#x60; (optional)</param>
        /// <returns>Task of Object</returns>
        public async System.Threading.Tasks.Task<Object> PrivateGetTransfersGetAsync (string currency, int? count = null, int? offset = null)
        {
             ApiResponse<Object> localVarResponse = await PrivateGetTransfersGetAsyncWithHttpInfo(currency, count, offset);
             return localVarResponse.Data;

        }

        /// <summary>
        /// Adds new entry to address book of given type 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="count">Number of requested items, default - &#x60;10&#x60; (optional)</param>
        /// <param name="offset">The offset for pagination, default - &#x60;0&#x60; (optional)</param>
        /// <returns>Task of ApiResponse (Object)</returns>
        public async System.Threading.Tasks.Task<ApiResponse<Object>> PrivateGetTransfersGetAsyncWithHttpInfo (string currency, int? count = null, int? offset = null)
        {
            // verify the required parameter 'currency' is set
            if (currency == null)
                throw new ApiException(400, "Missing required parameter 'currency' when calling WalletApi->PrivateGetTransfersGet");

            var localVarPath = "/private/get_transfers";
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
            if (count != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "count", count)); // query parameter
            if (offset != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "offset", offset)); // query parameter

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
                Exception exception = ExceptionFactory("PrivateGetTransfersGet", localVarResponse);
                if (exception != null) throw exception;
            }

            return new ApiResponse<Object>(localVarStatusCode,
                localVarResponse.Headers.ToDictionary(x => x.Name, x => string.Join(",", x.Value)),
                (Object) this.Configuration.ApiClient.Deserialize(localVarResponse, typeof(Object)));
        }

        /// <summary>
        /// Retrieve the latest users withdrawals 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="count">Number of requested items, default - &#x60;10&#x60; (optional)</param>
        /// <param name="offset">The offset for pagination, default - &#x60;0&#x60; (optional)</param>
        /// <returns>Object</returns>
        public Object PrivateGetWithdrawalsGet (string currency, int? count = null, int? offset = null)
        {
             ApiResponse<Object> localVarResponse = PrivateGetWithdrawalsGetWithHttpInfo(currency, count, offset);
             return localVarResponse.Data;
        }

        /// <summary>
        /// Retrieve the latest users withdrawals 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="count">Number of requested items, default - &#x60;10&#x60; (optional)</param>
        /// <param name="offset">The offset for pagination, default - &#x60;0&#x60; (optional)</param>
        /// <returns>ApiResponse of Object</returns>
        public ApiResponse< Object > PrivateGetWithdrawalsGetWithHttpInfo (string currency, int? count = null, int? offset = null)
        {
            // verify the required parameter 'currency' is set
            if (currency == null)
                throw new ApiException(400, "Missing required parameter 'currency' when calling WalletApi->PrivateGetWithdrawalsGet");

            var localVarPath = "/private/get_withdrawals";
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
            if (count != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "count", count)); // query parameter
            if (offset != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "offset", offset)); // query parameter

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
                Exception exception = ExceptionFactory("PrivateGetWithdrawalsGet", localVarResponse);
                if (exception != null) throw exception;
            }

            return new ApiResponse<Object>(localVarStatusCode,
                localVarResponse.Headers.ToDictionary(x => x.Name, x => string.Join(",", x.Value)),
                (Object) this.Configuration.ApiClient.Deserialize(localVarResponse, typeof(Object)));
        }

        /// <summary>
        /// Retrieve the latest users withdrawals 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="count">Number of requested items, default - &#x60;10&#x60; (optional)</param>
        /// <param name="offset">The offset for pagination, default - &#x60;0&#x60; (optional)</param>
        /// <returns>Task of Object</returns>
        public async System.Threading.Tasks.Task<Object> PrivateGetWithdrawalsGetAsync (string currency, int? count = null, int? offset = null)
        {
             ApiResponse<Object> localVarResponse = await PrivateGetWithdrawalsGetAsyncWithHttpInfo(currency, count, offset);
             return localVarResponse.Data;

        }

        /// <summary>
        /// Retrieve the latest users withdrawals 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="count">Number of requested items, default - &#x60;10&#x60; (optional)</param>
        /// <param name="offset">The offset for pagination, default - &#x60;0&#x60; (optional)</param>
        /// <returns>Task of ApiResponse (Object)</returns>
        public async System.Threading.Tasks.Task<ApiResponse<Object>> PrivateGetWithdrawalsGetAsyncWithHttpInfo (string currency, int? count = null, int? offset = null)
        {
            // verify the required parameter 'currency' is set
            if (currency == null)
                throw new ApiException(400, "Missing required parameter 'currency' when calling WalletApi->PrivateGetWithdrawalsGet");

            var localVarPath = "/private/get_withdrawals";
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
            if (count != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "count", count)); // query parameter
            if (offset != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "offset", offset)); // query parameter

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
                Exception exception = ExceptionFactory("PrivateGetWithdrawalsGet", localVarResponse);
                if (exception != null) throw exception;
            }

            return new ApiResponse<Object>(localVarStatusCode,
                localVarResponse.Headers.ToDictionary(x => x.Name, x => string.Join(",", x.Value)),
                (Object) this.Configuration.ApiClient.Deserialize(localVarResponse, typeof(Object)));
        }

        /// <summary>
        /// Adds new entry to address book of given type 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="type">Address book type</param>
        /// <param name="address">Address in currency format, it must be in address book</param>
        /// <param name="tfa">TFA code, required when TFA is enabled for current account (optional)</param>
        /// <returns>Object</returns>
        public Object PrivateRemoveFromAddressBookGet (string currency, string type, string address, string tfa = null)
        {
             ApiResponse<Object> localVarResponse = PrivateRemoveFromAddressBookGetWithHttpInfo(currency, type, address, tfa);
             return localVarResponse.Data;
        }

        /// <summary>
        /// Adds new entry to address book of given type 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="type">Address book type</param>
        /// <param name="address">Address in currency format, it must be in address book</param>
        /// <param name="tfa">TFA code, required when TFA is enabled for current account (optional)</param>
        /// <returns>ApiResponse of Object</returns>
        public ApiResponse< Object > PrivateRemoveFromAddressBookGetWithHttpInfo (string currency, string type, string address, string tfa = null)
        {
            // verify the required parameter 'currency' is set
            if (currency == null)
                throw new ApiException(400, "Missing required parameter 'currency' when calling WalletApi->PrivateRemoveFromAddressBookGet");
            // verify the required parameter 'type' is set
            if (type == null)
                throw new ApiException(400, "Missing required parameter 'type' when calling WalletApi->PrivateRemoveFromAddressBookGet");
            // verify the required parameter 'address' is set
            if (address == null)
                throw new ApiException(400, "Missing required parameter 'address' when calling WalletApi->PrivateRemoveFromAddressBookGet");

            var localVarPath = "/private/remove_from_address_book";
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
            if (address != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "address", address)); // query parameter
            if (tfa != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "tfa", tfa)); // query parameter

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
                Exception exception = ExceptionFactory("PrivateRemoveFromAddressBookGet", localVarResponse);
                if (exception != null) throw exception;
            }

            return new ApiResponse<Object>(localVarStatusCode,
                localVarResponse.Headers.ToDictionary(x => x.Name, x => string.Join(",", x.Value)),
                (Object) this.Configuration.ApiClient.Deserialize(localVarResponse, typeof(Object)));
        }

        /// <summary>
        /// Adds new entry to address book of given type 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="type">Address book type</param>
        /// <param name="address">Address in currency format, it must be in address book</param>
        /// <param name="tfa">TFA code, required when TFA is enabled for current account (optional)</param>
        /// <returns>Task of Object</returns>
        public async System.Threading.Tasks.Task<Object> PrivateRemoveFromAddressBookGetAsync (string currency, string type, string address, string tfa = null)
        {
             ApiResponse<Object> localVarResponse = await PrivateRemoveFromAddressBookGetAsyncWithHttpInfo(currency, type, address, tfa);
             return localVarResponse.Data;

        }

        /// <summary>
        /// Adds new entry to address book of given type 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="type">Address book type</param>
        /// <param name="address">Address in currency format, it must be in address book</param>
        /// <param name="tfa">TFA code, required when TFA is enabled for current account (optional)</param>
        /// <returns>Task of ApiResponse (Object)</returns>
        public async System.Threading.Tasks.Task<ApiResponse<Object>> PrivateRemoveFromAddressBookGetAsyncWithHttpInfo (string currency, string type, string address, string tfa = null)
        {
            // verify the required parameter 'currency' is set
            if (currency == null)
                throw new ApiException(400, "Missing required parameter 'currency' when calling WalletApi->PrivateRemoveFromAddressBookGet");
            // verify the required parameter 'type' is set
            if (type == null)
                throw new ApiException(400, "Missing required parameter 'type' when calling WalletApi->PrivateRemoveFromAddressBookGet");
            // verify the required parameter 'address' is set
            if (address == null)
                throw new ApiException(400, "Missing required parameter 'address' when calling WalletApi->PrivateRemoveFromAddressBookGet");

            var localVarPath = "/private/remove_from_address_book";
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
            if (address != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "address", address)); // query parameter
            if (tfa != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "tfa", tfa)); // query parameter

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
                Exception exception = ExceptionFactory("PrivateRemoveFromAddressBookGet", localVarResponse);
                if (exception != null) throw exception;
            }

            return new ApiResponse<Object>(localVarStatusCode,
                localVarResponse.Headers.ToDictionary(x => x.Name, x => string.Join(",", x.Value)),
                (Object) this.Configuration.ApiClient.Deserialize(localVarResponse, typeof(Object)));
        }

        /// <summary>
        /// Transfer funds to a subaccount. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="amount">Amount of funds to be transferred</param>
        /// <param name="destination">Id of destination subaccount</param>
        /// <returns>Object</returns>
        public Object PrivateSubmitTransferToSubaccountGet (string currency, decimal? amount, int? destination)
        {
             ApiResponse<Object> localVarResponse = PrivateSubmitTransferToSubaccountGetWithHttpInfo(currency, amount, destination);
             return localVarResponse.Data;
        }

        /// <summary>
        /// Transfer funds to a subaccount. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="amount">Amount of funds to be transferred</param>
        /// <param name="destination">Id of destination subaccount</param>
        /// <returns>ApiResponse of Object</returns>
        public ApiResponse< Object > PrivateSubmitTransferToSubaccountGetWithHttpInfo (string currency, decimal? amount, int? destination)
        {
            // verify the required parameter 'currency' is set
            if (currency == null)
                throw new ApiException(400, "Missing required parameter 'currency' when calling WalletApi->PrivateSubmitTransferToSubaccountGet");
            // verify the required parameter 'amount' is set
            if (amount == null)
                throw new ApiException(400, "Missing required parameter 'amount' when calling WalletApi->PrivateSubmitTransferToSubaccountGet");
            // verify the required parameter 'destination' is set
            if (destination == null)
                throw new ApiException(400, "Missing required parameter 'destination' when calling WalletApi->PrivateSubmitTransferToSubaccountGet");

            var localVarPath = "/private/submit_transfer_to_subaccount";
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
            if (amount != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "amount", amount)); // query parameter
            if (destination != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "destination", destination)); // query parameter

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
                Exception exception = ExceptionFactory("PrivateSubmitTransferToSubaccountGet", localVarResponse);
                if (exception != null) throw exception;
            }

            return new ApiResponse<Object>(localVarStatusCode,
                localVarResponse.Headers.ToDictionary(x => x.Name, x => string.Join(",", x.Value)),
                (Object) this.Configuration.ApiClient.Deserialize(localVarResponse, typeof(Object)));
        }

        /// <summary>
        /// Transfer funds to a subaccount. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="amount">Amount of funds to be transferred</param>
        /// <param name="destination">Id of destination subaccount</param>
        /// <returns>Task of Object</returns>
        public async System.Threading.Tasks.Task<Object> PrivateSubmitTransferToSubaccountGetAsync (string currency, decimal? amount, int? destination)
        {
             ApiResponse<Object> localVarResponse = await PrivateSubmitTransferToSubaccountGetAsyncWithHttpInfo(currency, amount, destination);
             return localVarResponse.Data;

        }

        /// <summary>
        /// Transfer funds to a subaccount. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="amount">Amount of funds to be transferred</param>
        /// <param name="destination">Id of destination subaccount</param>
        /// <returns>Task of ApiResponse (Object)</returns>
        public async System.Threading.Tasks.Task<ApiResponse<Object>> PrivateSubmitTransferToSubaccountGetAsyncWithHttpInfo (string currency, decimal? amount, int? destination)
        {
            // verify the required parameter 'currency' is set
            if (currency == null)
                throw new ApiException(400, "Missing required parameter 'currency' when calling WalletApi->PrivateSubmitTransferToSubaccountGet");
            // verify the required parameter 'amount' is set
            if (amount == null)
                throw new ApiException(400, "Missing required parameter 'amount' when calling WalletApi->PrivateSubmitTransferToSubaccountGet");
            // verify the required parameter 'destination' is set
            if (destination == null)
                throw new ApiException(400, "Missing required parameter 'destination' when calling WalletApi->PrivateSubmitTransferToSubaccountGet");

            var localVarPath = "/private/submit_transfer_to_subaccount";
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
            if (amount != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "amount", amount)); // query parameter
            if (destination != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "destination", destination)); // query parameter

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
                Exception exception = ExceptionFactory("PrivateSubmitTransferToSubaccountGet", localVarResponse);
                if (exception != null) throw exception;
            }

            return new ApiResponse<Object>(localVarStatusCode,
                localVarResponse.Headers.ToDictionary(x => x.Name, x => string.Join(",", x.Value)),
                (Object) this.Configuration.ApiClient.Deserialize(localVarResponse, typeof(Object)));
        }

        /// <summary>
        /// Transfer funds to a another user. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="amount">Amount of funds to be transferred</param>
        /// <param name="destination">Destination address from address book</param>
        /// <param name="tfa">TFA code, required when TFA is enabled for current account (optional)</param>
        /// <returns>Object</returns>
        public Object PrivateSubmitTransferToUserGet (string currency, decimal? amount, string destination, string tfa = null)
        {
             ApiResponse<Object> localVarResponse = PrivateSubmitTransferToUserGetWithHttpInfo(currency, amount, destination, tfa);
             return localVarResponse.Data;
        }

        /// <summary>
        /// Transfer funds to a another user. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="amount">Amount of funds to be transferred</param>
        /// <param name="destination">Destination address from address book</param>
        /// <param name="tfa">TFA code, required when TFA is enabled for current account (optional)</param>
        /// <returns>ApiResponse of Object</returns>
        public ApiResponse< Object > PrivateSubmitTransferToUserGetWithHttpInfo (string currency, decimal? amount, string destination, string tfa = null)
        {
            // verify the required parameter 'currency' is set
            if (currency == null)
                throw new ApiException(400, "Missing required parameter 'currency' when calling WalletApi->PrivateSubmitTransferToUserGet");
            // verify the required parameter 'amount' is set
            if (amount == null)
                throw new ApiException(400, "Missing required parameter 'amount' when calling WalletApi->PrivateSubmitTransferToUserGet");
            // verify the required parameter 'destination' is set
            if (destination == null)
                throw new ApiException(400, "Missing required parameter 'destination' when calling WalletApi->PrivateSubmitTransferToUserGet");

            var localVarPath = "/private/submit_transfer_to_user";
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
            if (amount != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "amount", amount)); // query parameter
            if (destination != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "destination", destination)); // query parameter
            if (tfa != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "tfa", tfa)); // query parameter

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
                Exception exception = ExceptionFactory("PrivateSubmitTransferToUserGet", localVarResponse);
                if (exception != null) throw exception;
            }

            return new ApiResponse<Object>(localVarStatusCode,
                localVarResponse.Headers.ToDictionary(x => x.Name, x => string.Join(",", x.Value)),
                (Object) this.Configuration.ApiClient.Deserialize(localVarResponse, typeof(Object)));
        }

        /// <summary>
        /// Transfer funds to a another user. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="amount">Amount of funds to be transferred</param>
        /// <param name="destination">Destination address from address book</param>
        /// <param name="tfa">TFA code, required when TFA is enabled for current account (optional)</param>
        /// <returns>Task of Object</returns>
        public async System.Threading.Tasks.Task<Object> PrivateSubmitTransferToUserGetAsync (string currency, decimal? amount, string destination, string tfa = null)
        {
             ApiResponse<Object> localVarResponse = await PrivateSubmitTransferToUserGetAsyncWithHttpInfo(currency, amount, destination, tfa);
             return localVarResponse.Data;

        }

        /// <summary>
        /// Transfer funds to a another user. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="amount">Amount of funds to be transferred</param>
        /// <param name="destination">Destination address from address book</param>
        /// <param name="tfa">TFA code, required when TFA is enabled for current account (optional)</param>
        /// <returns>Task of ApiResponse (Object)</returns>
        public async System.Threading.Tasks.Task<ApiResponse<Object>> PrivateSubmitTransferToUserGetAsyncWithHttpInfo (string currency, decimal? amount, string destination, string tfa = null)
        {
            // verify the required parameter 'currency' is set
            if (currency == null)
                throw new ApiException(400, "Missing required parameter 'currency' when calling WalletApi->PrivateSubmitTransferToUserGet");
            // verify the required parameter 'amount' is set
            if (amount == null)
                throw new ApiException(400, "Missing required parameter 'amount' when calling WalletApi->PrivateSubmitTransferToUserGet");
            // verify the required parameter 'destination' is set
            if (destination == null)
                throw new ApiException(400, "Missing required parameter 'destination' when calling WalletApi->PrivateSubmitTransferToUserGet");

            var localVarPath = "/private/submit_transfer_to_user";
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
            if (amount != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "amount", amount)); // query parameter
            if (destination != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "destination", destination)); // query parameter
            if (tfa != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "tfa", tfa)); // query parameter

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
                Exception exception = ExceptionFactory("PrivateSubmitTransferToUserGet", localVarResponse);
                if (exception != null) throw exception;
            }

            return new ApiResponse<Object>(localVarStatusCode,
                localVarResponse.Headers.ToDictionary(x => x.Name, x => string.Join(",", x.Value)),
                (Object) this.Configuration.ApiClient.Deserialize(localVarResponse, typeof(Object)));
        }

        /// <summary>
        /// Enable or disable deposit address creation 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="state"></param>
        /// <returns>Object</returns>
        public Object PrivateToggleDepositAddressCreationGet (string currency, bool? state)
        {
             ApiResponse<Object> localVarResponse = PrivateToggleDepositAddressCreationGetWithHttpInfo(currency, state);
             return localVarResponse.Data;
        }

        /// <summary>
        /// Enable or disable deposit address creation 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="state"></param>
        /// <returns>ApiResponse of Object</returns>
        public ApiResponse< Object > PrivateToggleDepositAddressCreationGetWithHttpInfo (string currency, bool? state)
        {
            // verify the required parameter 'currency' is set
            if (currency == null)
                throw new ApiException(400, "Missing required parameter 'currency' when calling WalletApi->PrivateToggleDepositAddressCreationGet");
            // verify the required parameter 'state' is set
            if (state == null)
                throw new ApiException(400, "Missing required parameter 'state' when calling WalletApi->PrivateToggleDepositAddressCreationGet");

            var localVarPath = "/private/toggle_deposit_address_creation";
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
            if (state != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "state", state)); // query parameter

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
                Exception exception = ExceptionFactory("PrivateToggleDepositAddressCreationGet", localVarResponse);
                if (exception != null) throw exception;
            }

            return new ApiResponse<Object>(localVarStatusCode,
                localVarResponse.Headers.ToDictionary(x => x.Name, x => string.Join(",", x.Value)),
                (Object) this.Configuration.ApiClient.Deserialize(localVarResponse, typeof(Object)));
        }

        /// <summary>
        /// Enable or disable deposit address creation 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="state"></param>
        /// <returns>Task of Object</returns>
        public async System.Threading.Tasks.Task<Object> PrivateToggleDepositAddressCreationGetAsync (string currency, bool? state)
        {
             ApiResponse<Object> localVarResponse = await PrivateToggleDepositAddressCreationGetAsyncWithHttpInfo(currency, state);
             return localVarResponse.Data;

        }

        /// <summary>
        /// Enable or disable deposit address creation 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="state"></param>
        /// <returns>Task of ApiResponse (Object)</returns>
        public async System.Threading.Tasks.Task<ApiResponse<Object>> PrivateToggleDepositAddressCreationGetAsyncWithHttpInfo (string currency, bool? state)
        {
            // verify the required parameter 'currency' is set
            if (currency == null)
                throw new ApiException(400, "Missing required parameter 'currency' when calling WalletApi->PrivateToggleDepositAddressCreationGet");
            // verify the required parameter 'state' is set
            if (state == null)
                throw new ApiException(400, "Missing required parameter 'state' when calling WalletApi->PrivateToggleDepositAddressCreationGet");

            var localVarPath = "/private/toggle_deposit_address_creation";
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
            if (state != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "state", state)); // query parameter

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
                Exception exception = ExceptionFactory("PrivateToggleDepositAddressCreationGet", localVarResponse);
                if (exception != null) throw exception;
            }

            return new ApiResponse<Object>(localVarStatusCode,
                localVarResponse.Headers.ToDictionary(x => x.Name, x => string.Join(",", x.Value)),
                (Object) this.Configuration.ApiClient.Deserialize(localVarResponse, typeof(Object)));
        }

        /// <summary>
        /// Creates a new withdrawal request 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="address">Address in currency format, it must be in address book</param>
        /// <param name="amount">Amount of funds to be withdrawn</param>
        /// <param name="priority">Withdrawal priority, optional for BTC, default: &#x60;high&#x60; (optional)</param>
        /// <param name="tfa">TFA code, required when TFA is enabled for current account (optional)</param>
        /// <returns>Object</returns>
        public Object PrivateWithdrawGet (string currency, string address, decimal? amount, string priority = null, string tfa = null)
        {
             ApiResponse<Object> localVarResponse = PrivateWithdrawGetWithHttpInfo(currency, address, amount, priority, tfa);
             return localVarResponse.Data;
        }

        /// <summary>
        /// Creates a new withdrawal request 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="address">Address in currency format, it must be in address book</param>
        /// <param name="amount">Amount of funds to be withdrawn</param>
        /// <param name="priority">Withdrawal priority, optional for BTC, default: &#x60;high&#x60; (optional)</param>
        /// <param name="tfa">TFA code, required when TFA is enabled for current account (optional)</param>
        /// <returns>ApiResponse of Object</returns>
        public ApiResponse< Object > PrivateWithdrawGetWithHttpInfo (string currency, string address, decimal? amount, string priority = null, string tfa = null)
        {
            // verify the required parameter 'currency' is set
            if (currency == null)
                throw new ApiException(400, "Missing required parameter 'currency' when calling WalletApi->PrivateWithdrawGet");
            // verify the required parameter 'address' is set
            if (address == null)
                throw new ApiException(400, "Missing required parameter 'address' when calling WalletApi->PrivateWithdrawGet");
            // verify the required parameter 'amount' is set
            if (amount == null)
                throw new ApiException(400, "Missing required parameter 'amount' when calling WalletApi->PrivateWithdrawGet");

            var localVarPath = "/private/withdraw";
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
            if (address != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "address", address)); // query parameter
            if (amount != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "amount", amount)); // query parameter
            if (priority != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "priority", priority)); // query parameter
            if (tfa != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "tfa", tfa)); // query parameter

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
                Exception exception = ExceptionFactory("PrivateWithdrawGet", localVarResponse);
                if (exception != null) throw exception;
            }

            return new ApiResponse<Object>(localVarStatusCode,
                localVarResponse.Headers.ToDictionary(x => x.Name, x => string.Join(",", x.Value)),
                (Object) this.Configuration.ApiClient.Deserialize(localVarResponse, typeof(Object)));
        }

        /// <summary>
        /// Creates a new withdrawal request 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="address">Address in currency format, it must be in address book</param>
        /// <param name="amount">Amount of funds to be withdrawn</param>
        /// <param name="priority">Withdrawal priority, optional for BTC, default: &#x60;high&#x60; (optional)</param>
        /// <param name="tfa">TFA code, required when TFA is enabled for current account (optional)</param>
        /// <returns>Task of Object</returns>
        public async System.Threading.Tasks.Task<Object> PrivateWithdrawGetAsync (string currency, string address, decimal? amount, string priority = null, string tfa = null)
        {
             ApiResponse<Object> localVarResponse = await PrivateWithdrawGetAsyncWithHttpInfo(currency, address, amount, priority, tfa);
             return localVarResponse.Data;

        }

        /// <summary>
        /// Creates a new withdrawal request 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="address">Address in currency format, it must be in address book</param>
        /// <param name="amount">Amount of funds to be withdrawn</param>
        /// <param name="priority">Withdrawal priority, optional for BTC, default: &#x60;high&#x60; (optional)</param>
        /// <param name="tfa">TFA code, required when TFA is enabled for current account (optional)</param>
        /// <returns>Task of ApiResponse (Object)</returns>
        public async System.Threading.Tasks.Task<ApiResponse<Object>> PrivateWithdrawGetAsyncWithHttpInfo (string currency, string address, decimal? amount, string priority = null, string tfa = null)
        {
            // verify the required parameter 'currency' is set
            if (currency == null)
                throw new ApiException(400, "Missing required parameter 'currency' when calling WalletApi->PrivateWithdrawGet");
            // verify the required parameter 'address' is set
            if (address == null)
                throw new ApiException(400, "Missing required parameter 'address' when calling WalletApi->PrivateWithdrawGet");
            // verify the required parameter 'amount' is set
            if (amount == null)
                throw new ApiException(400, "Missing required parameter 'amount' when calling WalletApi->PrivateWithdrawGet");

            var localVarPath = "/private/withdraw";
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
            if (address != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "address", address)); // query parameter
            if (amount != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "amount", amount)); // query parameter
            if (priority != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "priority", priority)); // query parameter
            if (tfa != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "tfa", tfa)); // query parameter

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
                Exception exception = ExceptionFactory("PrivateWithdrawGet", localVarResponse);
                if (exception != null) throw exception;
            }

            return new ApiResponse<Object>(localVarStatusCode,
                localVarResponse.Headers.ToDictionary(x => x.Name, x => string.Join(",", x.Value)),
                (Object) this.Configuration.ApiClient.Deserialize(localVarResponse, typeof(Object)));
        }

    }
}
