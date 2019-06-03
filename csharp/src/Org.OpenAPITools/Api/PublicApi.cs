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
    public interface IPublicApi : IApiAccessor
    {
        #region Synchronous Operations
        /// <summary>
        /// Authenticate
        /// </summary>
        /// <remarks>
        /// Retrieve an Oauth access token, to be used for authentication of &#39;private&#39; requests.  Three methods of authentication are supported:  - &lt;code&gt;password&lt;/code&gt; - using email and and password as when logging on to the website - &lt;code&gt;client_credentials&lt;/code&gt; - using the access key and access secret that can be found on the API page on the website - &lt;code&gt;client_signature&lt;/code&gt; - using the access key that can be found on the API page on the website and user generated signature. The signature is calculated using some fields provided in the request, using formula described here [Deribit signature credentials](#additional-authorization-method-deribit-signature-credentials) - &lt;code&gt;refresh_token&lt;/code&gt; - using a refresh token that was received from an earlier invocation  The response will contain an access token, expiration period (number of seconds that the token is valid) and a refresh token that can be used to get a new set of tokens. 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="grantType">Method of authentication</param>
        /// <param name="username">Required for grant type &#x60;password&#x60;</param>
        /// <param name="password">Required for grant type &#x60;password&#x60;</param>
        /// <param name="clientId">Required for grant type &#x60;client_credentials&#x60; and &#x60;client_signature&#x60;</param>
        /// <param name="clientSecret">Required for grant type &#x60;client_credentials&#x60;</param>
        /// <param name="refreshToken">Required for grant type &#x60;refresh_token&#x60;</param>
        /// <param name="timestamp">Required for grant type &#x60;client_signature&#x60;, provides time when request has been generated</param>
        /// <param name="signature">Required for grant type &#x60;client_signature&#x60;; it&#39;s a cryptographic signature calculated over provided fields using user **secret key**. The signature should be calculated as an HMAC (Hash-based Message Authentication Code) with &#x60;SHA256&#x60; hash algorithm</param>
        /// <param name="nonce">Optional for grant type &#x60;client_signature&#x60;; delivers user generated initialization vector for the server token (optional)</param>
        /// <param name="state">Will be passed back in the response (optional)</param>
        /// <param name="scope">Describes type of the access for assigned token, possible values: &#x60;connection&#x60;, &#x60;session&#x60;, &#x60;session:name&#x60;, &#x60;trade:[read, read_write, none]&#x60;, &#x60;wallet:[read, read_write, none]&#x60;, &#x60;account:[read, read_write, none]&#x60;, &#x60;expires:NUMBER&#x60; (token will expire after &#x60;NUMBER&#x60; of seconds).&lt;/BR&gt;&lt;/BR&gt; **NOTICE:** Depending on choosing an authentication method (&#x60;&#x60;&#x60;grant type&#x60;&#x60;&#x60;) some scopes could be narrowed by the server. e.g. when &#x60;&#x60;&#x60;grant_type &#x3D; client_credentials&#x60;&#x60;&#x60; and &#x60;&#x60;&#x60;scope &#x3D; wallet:read_write&#x60;&#x60;&#x60; it&#39;s modified by the server as &#x60;&#x60;&#x60;scope &#x3D; wallet:read&#x60;&#x60;&#x60; (optional)</param>
        /// <returns>Object</returns>
        Object PublicAuthGet (string grantType, string username, string password, string clientId, string clientSecret, string refreshToken, string timestamp, string signature, string nonce = null, string state = null, string scope = null);

        /// <summary>
        /// Authenticate
        /// </summary>
        /// <remarks>
        /// Retrieve an Oauth access token, to be used for authentication of &#39;private&#39; requests.  Three methods of authentication are supported:  - &lt;code&gt;password&lt;/code&gt; - using email and and password as when logging on to the website - &lt;code&gt;client_credentials&lt;/code&gt; - using the access key and access secret that can be found on the API page on the website - &lt;code&gt;client_signature&lt;/code&gt; - using the access key that can be found on the API page on the website and user generated signature. The signature is calculated using some fields provided in the request, using formula described here [Deribit signature credentials](#additional-authorization-method-deribit-signature-credentials) - &lt;code&gt;refresh_token&lt;/code&gt; - using a refresh token that was received from an earlier invocation  The response will contain an access token, expiration period (number of seconds that the token is valid) and a refresh token that can be used to get a new set of tokens. 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="grantType">Method of authentication</param>
        /// <param name="username">Required for grant type &#x60;password&#x60;</param>
        /// <param name="password">Required for grant type &#x60;password&#x60;</param>
        /// <param name="clientId">Required for grant type &#x60;client_credentials&#x60; and &#x60;client_signature&#x60;</param>
        /// <param name="clientSecret">Required for grant type &#x60;client_credentials&#x60;</param>
        /// <param name="refreshToken">Required for grant type &#x60;refresh_token&#x60;</param>
        /// <param name="timestamp">Required for grant type &#x60;client_signature&#x60;, provides time when request has been generated</param>
        /// <param name="signature">Required for grant type &#x60;client_signature&#x60;; it&#39;s a cryptographic signature calculated over provided fields using user **secret key**. The signature should be calculated as an HMAC (Hash-based Message Authentication Code) with &#x60;SHA256&#x60; hash algorithm</param>
        /// <param name="nonce">Optional for grant type &#x60;client_signature&#x60;; delivers user generated initialization vector for the server token (optional)</param>
        /// <param name="state">Will be passed back in the response (optional)</param>
        /// <param name="scope">Describes type of the access for assigned token, possible values: &#x60;connection&#x60;, &#x60;session&#x60;, &#x60;session:name&#x60;, &#x60;trade:[read, read_write, none]&#x60;, &#x60;wallet:[read, read_write, none]&#x60;, &#x60;account:[read, read_write, none]&#x60;, &#x60;expires:NUMBER&#x60; (token will expire after &#x60;NUMBER&#x60; of seconds).&lt;/BR&gt;&lt;/BR&gt; **NOTICE:** Depending on choosing an authentication method (&#x60;&#x60;&#x60;grant type&#x60;&#x60;&#x60;) some scopes could be narrowed by the server. e.g. when &#x60;&#x60;&#x60;grant_type &#x3D; client_credentials&#x60;&#x60;&#x60; and &#x60;&#x60;&#x60;scope &#x3D; wallet:read_write&#x60;&#x60;&#x60; it&#39;s modified by the server as &#x60;&#x60;&#x60;scope &#x3D; wallet:read&#x60;&#x60;&#x60; (optional)</param>
        /// <returns>ApiResponse of Object</returns>
        ApiResponse<Object> PublicAuthGetWithHttpInfo (string grantType, string username, string password, string clientId, string clientSecret, string refreshToken, string timestamp, string signature, string nonce = null, string state = null, string scope = null);
        /// <summary>
        /// Retrieves announcements from the last 30 days.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <returns>Object</returns>
        Object PublicGetAnnouncementsGet ();

        /// <summary>
        /// Retrieves announcements from the last 30 days.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <returns>ApiResponse of Object</returns>
        ApiResponse<Object> PublicGetAnnouncementsGetWithHttpInfo ();
        /// <summary>
        /// Retrieves the summary information such as open interest, 24h volume, etc. for all instruments for the currency (optionally filtered by kind).
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="kind">Instrument kind, if not provided instruments of all kinds are considered (optional)</param>
        /// <returns>Object</returns>
        Object PublicGetBookSummaryByCurrencyGet (string currency, string kind = null);

        /// <summary>
        /// Retrieves the summary information such as open interest, 24h volume, etc. for all instruments for the currency (optionally filtered by kind).
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="kind">Instrument kind, if not provided instruments of all kinds are considered (optional)</param>
        /// <returns>ApiResponse of Object</returns>
        ApiResponse<Object> PublicGetBookSummaryByCurrencyGetWithHttpInfo (string currency, string kind = null);
        /// <summary>
        /// Retrieves the summary information such as open interest, 24h volume, etc. for a specific instrument.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">Instrument name</param>
        /// <returns>Object</returns>
        Object PublicGetBookSummaryByInstrumentGet (string instrumentName);

        /// <summary>
        /// Retrieves the summary information such as open interest, 24h volume, etc. for a specific instrument.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">Instrument name</param>
        /// <returns>ApiResponse of Object</returns>
        ApiResponse<Object> PublicGetBookSummaryByInstrumentGetWithHttpInfo (string instrumentName);
        /// <summary>
        /// Retrieves contract size of provided instrument.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">Instrument name</param>
        /// <returns>Object</returns>
        Object PublicGetContractSizeGet (string instrumentName);

        /// <summary>
        /// Retrieves contract size of provided instrument.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">Instrument name</param>
        /// <returns>ApiResponse of Object</returns>
        ApiResponse<Object> PublicGetContractSizeGetWithHttpInfo (string instrumentName);
        /// <summary>
        /// Retrieves all cryptocurrencies supported by the API.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <returns>Object</returns>
        Object PublicGetCurrenciesGet ();

        /// <summary>
        /// Retrieves all cryptocurrencies supported by the API.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <returns>ApiResponse of Object</returns>
        ApiResponse<Object> PublicGetCurrenciesGetWithHttpInfo ();
        /// <summary>
        /// Retrieve the latest user trades that have occurred for PERPETUAL instruments in a specific currency symbol and within given time range.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">Instrument name</param>
        /// <param name="length">Specifies time period. &#x60;8h&#x60; - 8 hours, &#x60;24h&#x60; - 24 hours (optional)</param>
        /// <returns>Object</returns>
        Object PublicGetFundingChartDataGet (string instrumentName, string length = null);

        /// <summary>
        /// Retrieve the latest user trades that have occurred for PERPETUAL instruments in a specific currency symbol and within given time range.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">Instrument name</param>
        /// <param name="length">Specifies time period. &#x60;8h&#x60; - 8 hours, &#x60;24h&#x60; - 24 hours (optional)</param>
        /// <returns>ApiResponse of Object</returns>
        ApiResponse<Object> PublicGetFundingChartDataGetWithHttpInfo (string instrumentName, string length = null);
        /// <summary>
        /// Provides information about historical volatility for given cryptocurrency.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <returns>Object</returns>
        Object PublicGetHistoricalVolatilityGet (string currency);

        /// <summary>
        /// Provides information about historical volatility for given cryptocurrency.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <returns>ApiResponse of Object</returns>
        ApiResponse<Object> PublicGetHistoricalVolatilityGetWithHttpInfo (string currency);
        /// <summary>
        /// Retrieves the current index price for the instruments, for the selected currency.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <returns>Object</returns>
        Object PublicGetIndexGet (string currency);

        /// <summary>
        /// Retrieves the current index price for the instruments, for the selected currency.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <returns>ApiResponse of Object</returns>
        ApiResponse<Object> PublicGetIndexGetWithHttpInfo (string currency);
        /// <summary>
        /// Retrieves available trading instruments. This method can be used to see which instruments are available for trading, or which instruments have existed historically.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="kind">Instrument kind, if not provided instruments of all kinds are considered (optional)</param>
        /// <param name="expired">Set to true to show expired instruments instead of active ones. (optional, default to false)</param>
        /// <returns>Object</returns>
        Object PublicGetInstrumentsGet (string currency, string kind = null, bool? expired = null);

        /// <summary>
        /// Retrieves available trading instruments. This method can be used to see which instruments are available for trading, or which instruments have existed historically.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="kind">Instrument kind, if not provided instruments of all kinds are considered (optional)</param>
        /// <param name="expired">Set to true to show expired instruments instead of active ones. (optional, default to false)</param>
        /// <returns>ApiResponse of Object</returns>
        ApiResponse<Object> PublicGetInstrumentsGetWithHttpInfo (string currency, string kind = null, bool? expired = null);
        /// <summary>
        /// Retrieves historical settlement, delivery and bankruptcy events coming from all instruments within given currency.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="type">Settlement type (optional)</param>
        /// <param name="count">Number of requested items, default - &#x60;20&#x60; (optional)</param>
        /// <param name="continuation">Continuation token for pagination (optional)</param>
        /// <param name="searchStartTimestamp">The latest timestamp to return result for (optional)</param>
        /// <returns>Object</returns>
        Object PublicGetLastSettlementsByCurrencyGet (string currency, string type = null, int? count = null, string continuation = null, int? searchStartTimestamp = null);

        /// <summary>
        /// Retrieves historical settlement, delivery and bankruptcy events coming from all instruments within given currency.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="type">Settlement type (optional)</param>
        /// <param name="count">Number of requested items, default - &#x60;20&#x60; (optional)</param>
        /// <param name="continuation">Continuation token for pagination (optional)</param>
        /// <param name="searchStartTimestamp">The latest timestamp to return result for (optional)</param>
        /// <returns>ApiResponse of Object</returns>
        ApiResponse<Object> PublicGetLastSettlementsByCurrencyGetWithHttpInfo (string currency, string type = null, int? count = null, string continuation = null, int? searchStartTimestamp = null);
        /// <summary>
        /// Retrieves historical public settlement, delivery and bankruptcy events filtered by instrument name.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">Instrument name</param>
        /// <param name="type">Settlement type (optional)</param>
        /// <param name="count">Number of requested items, default - &#x60;20&#x60; (optional)</param>
        /// <param name="continuation">Continuation token for pagination (optional)</param>
        /// <param name="searchStartTimestamp">The latest timestamp to return result for (optional)</param>
        /// <returns>Object</returns>
        Object PublicGetLastSettlementsByInstrumentGet (string instrumentName, string type = null, int? count = null, string continuation = null, int? searchStartTimestamp = null);

        /// <summary>
        /// Retrieves historical public settlement, delivery and bankruptcy events filtered by instrument name.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">Instrument name</param>
        /// <param name="type">Settlement type (optional)</param>
        /// <param name="count">Number of requested items, default - &#x60;20&#x60; (optional)</param>
        /// <param name="continuation">Continuation token for pagination (optional)</param>
        /// <param name="searchStartTimestamp">The latest timestamp to return result for (optional)</param>
        /// <returns>ApiResponse of Object</returns>
        ApiResponse<Object> PublicGetLastSettlementsByInstrumentGetWithHttpInfo (string instrumentName, string type = null, int? count = null, string continuation = null, int? searchStartTimestamp = null);
        /// <summary>
        /// Retrieve the latest trades that have occurred for instruments in a specific currency symbol and within given time range.
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
        Object PublicGetLastTradesByCurrencyAndTimeGet (string currency, int? startTimestamp, int? endTimestamp, string kind = null, int? count = null, bool? includeOld = null, string sorting = null);

        /// <summary>
        /// Retrieve the latest trades that have occurred for instruments in a specific currency symbol and within given time range.
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
        ApiResponse<Object> PublicGetLastTradesByCurrencyAndTimeGetWithHttpInfo (string currency, int? startTimestamp, int? endTimestamp, string kind = null, int? count = null, bool? includeOld = null, string sorting = null);
        /// <summary>
        /// Retrieve the latest trades that have occurred for instruments in a specific currency symbol.
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
        Object PublicGetLastTradesByCurrencyGet (string currency, string kind = null, string startId = null, string endId = null, int? count = null, bool? includeOld = null, string sorting = null);

        /// <summary>
        /// Retrieve the latest trades that have occurred for instruments in a specific currency symbol.
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
        ApiResponse<Object> PublicGetLastTradesByCurrencyGetWithHttpInfo (string currency, string kind = null, string startId = null, string endId = null, int? count = null, bool? includeOld = null, string sorting = null);
        /// <summary>
        /// Retrieve the latest trades that have occurred for a specific instrument and within given time range.
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
        Object PublicGetLastTradesByInstrumentAndTimeGet (string instrumentName, int? startTimestamp, int? endTimestamp, int? count = null, bool? includeOld = null, string sorting = null);

        /// <summary>
        /// Retrieve the latest trades that have occurred for a specific instrument and within given time range.
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
        ApiResponse<Object> PublicGetLastTradesByInstrumentAndTimeGetWithHttpInfo (string instrumentName, int? startTimestamp, int? endTimestamp, int? count = null, bool? includeOld = null, string sorting = null);
        /// <summary>
        /// Retrieve the latest trades that have occurred for a specific instrument.
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
        Object PublicGetLastTradesByInstrumentGet (string instrumentName, int? startSeq = null, int? endSeq = null, int? count = null, bool? includeOld = null, string sorting = null);

        /// <summary>
        /// Retrieve the latest trades that have occurred for a specific instrument.
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
        ApiResponse<Object> PublicGetLastTradesByInstrumentGetWithHttpInfo (string instrumentName, int? startSeq = null, int? endSeq = null, int? count = null, bool? includeOld = null, string sorting = null);
        /// <summary>
        /// Retrieves the order book, along with other market values for a given instrument.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">The instrument name for which to retrieve the order book, see [&#x60;getinstruments&#x60;](#getinstruments) to obtain instrument names.</param>
        /// <param name="depth">The number of entries to return for bids and asks. (optional)</param>
        /// <returns>Object</returns>
        Object PublicGetOrderBookGet (string instrumentName, decimal? depth = null);

        /// <summary>
        /// Retrieves the order book, along with other market values for a given instrument.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">The instrument name for which to retrieve the order book, see [&#x60;getinstruments&#x60;](#getinstruments) to obtain instrument names.</param>
        /// <param name="depth">The number of entries to return for bids and asks. (optional)</param>
        /// <returns>ApiResponse of Object</returns>
        ApiResponse<Object> PublicGetOrderBookGetWithHttpInfo (string instrumentName, decimal? depth = null);
        /// <summary>
        /// Retrieves the current time (in milliseconds). This API endpoint can be used to check the clock skew between your software and Deribit&#39;s systems.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <returns>Object</returns>
        Object PublicGetTimeGet ();

        /// <summary>
        /// Retrieves the current time (in milliseconds). This API endpoint can be used to check the clock skew between your software and Deribit&#39;s systems.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <returns>ApiResponse of Object</returns>
        ApiResponse<Object> PublicGetTimeGetWithHttpInfo ();
        /// <summary>
        /// Retrieves aggregated 24h trade volumes for different instrument types and currencies.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <returns>Object</returns>
        Object PublicGetTradeVolumesGet ();

        /// <summary>
        /// Retrieves aggregated 24h trade volumes for different instrument types and currencies.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <returns>ApiResponse of Object</returns>
        ApiResponse<Object> PublicGetTradeVolumesGetWithHttpInfo ();
        /// <summary>
        /// Publicly available market data used to generate a TradingView candle chart.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">Instrument name</param>
        /// <param name="startTimestamp">The earliest timestamp to return result for</param>
        /// <param name="endTimestamp">The most recent timestamp to return result for</param>
        /// <returns>Object</returns>
        Object PublicGetTradingviewChartDataGet (string instrumentName, int? startTimestamp, int? endTimestamp);

        /// <summary>
        /// Publicly available market data used to generate a TradingView candle chart.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">Instrument name</param>
        /// <param name="startTimestamp">The earliest timestamp to return result for</param>
        /// <param name="endTimestamp">The most recent timestamp to return result for</param>
        /// <returns>ApiResponse of Object</returns>
        ApiResponse<Object> PublicGetTradingviewChartDataGetWithHttpInfo (string instrumentName, int? startTimestamp, int? endTimestamp);
        /// <summary>
        /// Tests the connection to the API server, and returns its version. You can use this to make sure the API is reachable, and matches the expected version.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="expectedResult">The value \&quot;exception\&quot; will trigger an error response. This may be useful for testing wrapper libraries. (optional)</param>
        /// <returns>Object</returns>
        Object PublicTestGet (string expectedResult = null);

        /// <summary>
        /// Tests the connection to the API server, and returns its version. You can use this to make sure the API is reachable, and matches the expected version.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="expectedResult">The value \&quot;exception\&quot; will trigger an error response. This may be useful for testing wrapper libraries. (optional)</param>
        /// <returns>ApiResponse of Object</returns>
        ApiResponse<Object> PublicTestGetWithHttpInfo (string expectedResult = null);
        /// <summary>
        /// Get ticker for an instrument.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">Instrument name</param>
        /// <returns>Object</returns>
        Object PublicTickerGet (string instrumentName);

        /// <summary>
        /// Get ticker for an instrument.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">Instrument name</param>
        /// <returns>ApiResponse of Object</returns>
        ApiResponse<Object> PublicTickerGetWithHttpInfo (string instrumentName);
        /// <summary>
        /// Method used to introduce the client software connected to Deribit platform over websocket. Provided data may have an impact on the maintained connection and will be collected for internal statistical purposes. In response, Deribit will also introduce itself.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="field">Name of the field to be validated, examples: postal_code, date_of_birth</param>
        /// <param name="value">Value to be checked</param>
        /// <param name="value2">Additional value to be compared with (optional)</param>
        /// <returns>Object</returns>
        Object PublicValidateFieldGet (string field, string value, string value2 = null);

        /// <summary>
        /// Method used to introduce the client software connected to Deribit platform over websocket. Provided data may have an impact on the maintained connection and will be collected for internal statistical purposes. In response, Deribit will also introduce itself.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="field">Name of the field to be validated, examples: postal_code, date_of_birth</param>
        /// <param name="value">Value to be checked</param>
        /// <param name="value2">Additional value to be compared with (optional)</param>
        /// <returns>ApiResponse of Object</returns>
        ApiResponse<Object> PublicValidateFieldGetWithHttpInfo (string field, string value, string value2 = null);
        #endregion Synchronous Operations
        #region Asynchronous Operations
        /// <summary>
        /// Authenticate
        /// </summary>
        /// <remarks>
        /// Retrieve an Oauth access token, to be used for authentication of &#39;private&#39; requests.  Three methods of authentication are supported:  - &lt;code&gt;password&lt;/code&gt; - using email and and password as when logging on to the website - &lt;code&gt;client_credentials&lt;/code&gt; - using the access key and access secret that can be found on the API page on the website - &lt;code&gt;client_signature&lt;/code&gt; - using the access key that can be found on the API page on the website and user generated signature. The signature is calculated using some fields provided in the request, using formula described here [Deribit signature credentials](#additional-authorization-method-deribit-signature-credentials) - &lt;code&gt;refresh_token&lt;/code&gt; - using a refresh token that was received from an earlier invocation  The response will contain an access token, expiration period (number of seconds that the token is valid) and a refresh token that can be used to get a new set of tokens. 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="grantType">Method of authentication</param>
        /// <param name="username">Required for grant type &#x60;password&#x60;</param>
        /// <param name="password">Required for grant type &#x60;password&#x60;</param>
        /// <param name="clientId">Required for grant type &#x60;client_credentials&#x60; and &#x60;client_signature&#x60;</param>
        /// <param name="clientSecret">Required for grant type &#x60;client_credentials&#x60;</param>
        /// <param name="refreshToken">Required for grant type &#x60;refresh_token&#x60;</param>
        /// <param name="timestamp">Required for grant type &#x60;client_signature&#x60;, provides time when request has been generated</param>
        /// <param name="signature">Required for grant type &#x60;client_signature&#x60;; it&#39;s a cryptographic signature calculated over provided fields using user **secret key**. The signature should be calculated as an HMAC (Hash-based Message Authentication Code) with &#x60;SHA256&#x60; hash algorithm</param>
        /// <param name="nonce">Optional for grant type &#x60;client_signature&#x60;; delivers user generated initialization vector for the server token (optional)</param>
        /// <param name="state">Will be passed back in the response (optional)</param>
        /// <param name="scope">Describes type of the access for assigned token, possible values: &#x60;connection&#x60;, &#x60;session&#x60;, &#x60;session:name&#x60;, &#x60;trade:[read, read_write, none]&#x60;, &#x60;wallet:[read, read_write, none]&#x60;, &#x60;account:[read, read_write, none]&#x60;, &#x60;expires:NUMBER&#x60; (token will expire after &#x60;NUMBER&#x60; of seconds).&lt;/BR&gt;&lt;/BR&gt; **NOTICE:** Depending on choosing an authentication method (&#x60;&#x60;&#x60;grant type&#x60;&#x60;&#x60;) some scopes could be narrowed by the server. e.g. when &#x60;&#x60;&#x60;grant_type &#x3D; client_credentials&#x60;&#x60;&#x60; and &#x60;&#x60;&#x60;scope &#x3D; wallet:read_write&#x60;&#x60;&#x60; it&#39;s modified by the server as &#x60;&#x60;&#x60;scope &#x3D; wallet:read&#x60;&#x60;&#x60; (optional)</param>
        /// <returns>Task of Object</returns>
        System.Threading.Tasks.Task<Object> PublicAuthGetAsync (string grantType, string username, string password, string clientId, string clientSecret, string refreshToken, string timestamp, string signature, string nonce = null, string state = null, string scope = null);

        /// <summary>
        /// Authenticate
        /// </summary>
        /// <remarks>
        /// Retrieve an Oauth access token, to be used for authentication of &#39;private&#39; requests.  Three methods of authentication are supported:  - &lt;code&gt;password&lt;/code&gt; - using email and and password as when logging on to the website - &lt;code&gt;client_credentials&lt;/code&gt; - using the access key and access secret that can be found on the API page on the website - &lt;code&gt;client_signature&lt;/code&gt; - using the access key that can be found on the API page on the website and user generated signature. The signature is calculated using some fields provided in the request, using formula described here [Deribit signature credentials](#additional-authorization-method-deribit-signature-credentials) - &lt;code&gt;refresh_token&lt;/code&gt; - using a refresh token that was received from an earlier invocation  The response will contain an access token, expiration period (number of seconds that the token is valid) and a refresh token that can be used to get a new set of tokens. 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="grantType">Method of authentication</param>
        /// <param name="username">Required for grant type &#x60;password&#x60;</param>
        /// <param name="password">Required for grant type &#x60;password&#x60;</param>
        /// <param name="clientId">Required for grant type &#x60;client_credentials&#x60; and &#x60;client_signature&#x60;</param>
        /// <param name="clientSecret">Required for grant type &#x60;client_credentials&#x60;</param>
        /// <param name="refreshToken">Required for grant type &#x60;refresh_token&#x60;</param>
        /// <param name="timestamp">Required for grant type &#x60;client_signature&#x60;, provides time when request has been generated</param>
        /// <param name="signature">Required for grant type &#x60;client_signature&#x60;; it&#39;s a cryptographic signature calculated over provided fields using user **secret key**. The signature should be calculated as an HMAC (Hash-based Message Authentication Code) with &#x60;SHA256&#x60; hash algorithm</param>
        /// <param name="nonce">Optional for grant type &#x60;client_signature&#x60;; delivers user generated initialization vector for the server token (optional)</param>
        /// <param name="state">Will be passed back in the response (optional)</param>
        /// <param name="scope">Describes type of the access for assigned token, possible values: &#x60;connection&#x60;, &#x60;session&#x60;, &#x60;session:name&#x60;, &#x60;trade:[read, read_write, none]&#x60;, &#x60;wallet:[read, read_write, none]&#x60;, &#x60;account:[read, read_write, none]&#x60;, &#x60;expires:NUMBER&#x60; (token will expire after &#x60;NUMBER&#x60; of seconds).&lt;/BR&gt;&lt;/BR&gt; **NOTICE:** Depending on choosing an authentication method (&#x60;&#x60;&#x60;grant type&#x60;&#x60;&#x60;) some scopes could be narrowed by the server. e.g. when &#x60;&#x60;&#x60;grant_type &#x3D; client_credentials&#x60;&#x60;&#x60; and &#x60;&#x60;&#x60;scope &#x3D; wallet:read_write&#x60;&#x60;&#x60; it&#39;s modified by the server as &#x60;&#x60;&#x60;scope &#x3D; wallet:read&#x60;&#x60;&#x60; (optional)</param>
        /// <returns>Task of ApiResponse (Object)</returns>
        System.Threading.Tasks.Task<ApiResponse<Object>> PublicAuthGetAsyncWithHttpInfo (string grantType, string username, string password, string clientId, string clientSecret, string refreshToken, string timestamp, string signature, string nonce = null, string state = null, string scope = null);
        /// <summary>
        /// Retrieves announcements from the last 30 days.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <returns>Task of Object</returns>
        System.Threading.Tasks.Task<Object> PublicGetAnnouncementsGetAsync ();

        /// <summary>
        /// Retrieves announcements from the last 30 days.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <returns>Task of ApiResponse (Object)</returns>
        System.Threading.Tasks.Task<ApiResponse<Object>> PublicGetAnnouncementsGetAsyncWithHttpInfo ();
        /// <summary>
        /// Retrieves the summary information such as open interest, 24h volume, etc. for all instruments for the currency (optionally filtered by kind).
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="kind">Instrument kind, if not provided instruments of all kinds are considered (optional)</param>
        /// <returns>Task of Object</returns>
        System.Threading.Tasks.Task<Object> PublicGetBookSummaryByCurrencyGetAsync (string currency, string kind = null);

        /// <summary>
        /// Retrieves the summary information such as open interest, 24h volume, etc. for all instruments for the currency (optionally filtered by kind).
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="kind">Instrument kind, if not provided instruments of all kinds are considered (optional)</param>
        /// <returns>Task of ApiResponse (Object)</returns>
        System.Threading.Tasks.Task<ApiResponse<Object>> PublicGetBookSummaryByCurrencyGetAsyncWithHttpInfo (string currency, string kind = null);
        /// <summary>
        /// Retrieves the summary information such as open interest, 24h volume, etc. for a specific instrument.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">Instrument name</param>
        /// <returns>Task of Object</returns>
        System.Threading.Tasks.Task<Object> PublicGetBookSummaryByInstrumentGetAsync (string instrumentName);

        /// <summary>
        /// Retrieves the summary information such as open interest, 24h volume, etc. for a specific instrument.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">Instrument name</param>
        /// <returns>Task of ApiResponse (Object)</returns>
        System.Threading.Tasks.Task<ApiResponse<Object>> PublicGetBookSummaryByInstrumentGetAsyncWithHttpInfo (string instrumentName);
        /// <summary>
        /// Retrieves contract size of provided instrument.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">Instrument name</param>
        /// <returns>Task of Object</returns>
        System.Threading.Tasks.Task<Object> PublicGetContractSizeGetAsync (string instrumentName);

        /// <summary>
        /// Retrieves contract size of provided instrument.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">Instrument name</param>
        /// <returns>Task of ApiResponse (Object)</returns>
        System.Threading.Tasks.Task<ApiResponse<Object>> PublicGetContractSizeGetAsyncWithHttpInfo (string instrumentName);
        /// <summary>
        /// Retrieves all cryptocurrencies supported by the API.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <returns>Task of Object</returns>
        System.Threading.Tasks.Task<Object> PublicGetCurrenciesGetAsync ();

        /// <summary>
        /// Retrieves all cryptocurrencies supported by the API.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <returns>Task of ApiResponse (Object)</returns>
        System.Threading.Tasks.Task<ApiResponse<Object>> PublicGetCurrenciesGetAsyncWithHttpInfo ();
        /// <summary>
        /// Retrieve the latest user trades that have occurred for PERPETUAL instruments in a specific currency symbol and within given time range.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">Instrument name</param>
        /// <param name="length">Specifies time period. &#x60;8h&#x60; - 8 hours, &#x60;24h&#x60; - 24 hours (optional)</param>
        /// <returns>Task of Object</returns>
        System.Threading.Tasks.Task<Object> PublicGetFundingChartDataGetAsync (string instrumentName, string length = null);

        /// <summary>
        /// Retrieve the latest user trades that have occurred for PERPETUAL instruments in a specific currency symbol and within given time range.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">Instrument name</param>
        /// <param name="length">Specifies time period. &#x60;8h&#x60; - 8 hours, &#x60;24h&#x60; - 24 hours (optional)</param>
        /// <returns>Task of ApiResponse (Object)</returns>
        System.Threading.Tasks.Task<ApiResponse<Object>> PublicGetFundingChartDataGetAsyncWithHttpInfo (string instrumentName, string length = null);
        /// <summary>
        /// Provides information about historical volatility for given cryptocurrency.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <returns>Task of Object</returns>
        System.Threading.Tasks.Task<Object> PublicGetHistoricalVolatilityGetAsync (string currency);

        /// <summary>
        /// Provides information about historical volatility for given cryptocurrency.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <returns>Task of ApiResponse (Object)</returns>
        System.Threading.Tasks.Task<ApiResponse<Object>> PublicGetHistoricalVolatilityGetAsyncWithHttpInfo (string currency);
        /// <summary>
        /// Retrieves the current index price for the instruments, for the selected currency.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <returns>Task of Object</returns>
        System.Threading.Tasks.Task<Object> PublicGetIndexGetAsync (string currency);

        /// <summary>
        /// Retrieves the current index price for the instruments, for the selected currency.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <returns>Task of ApiResponse (Object)</returns>
        System.Threading.Tasks.Task<ApiResponse<Object>> PublicGetIndexGetAsyncWithHttpInfo (string currency);
        /// <summary>
        /// Retrieves available trading instruments. This method can be used to see which instruments are available for trading, or which instruments have existed historically.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="kind">Instrument kind, if not provided instruments of all kinds are considered (optional)</param>
        /// <param name="expired">Set to true to show expired instruments instead of active ones. (optional, default to false)</param>
        /// <returns>Task of Object</returns>
        System.Threading.Tasks.Task<Object> PublicGetInstrumentsGetAsync (string currency, string kind = null, bool? expired = null);

        /// <summary>
        /// Retrieves available trading instruments. This method can be used to see which instruments are available for trading, or which instruments have existed historically.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="kind">Instrument kind, if not provided instruments of all kinds are considered (optional)</param>
        /// <param name="expired">Set to true to show expired instruments instead of active ones. (optional, default to false)</param>
        /// <returns>Task of ApiResponse (Object)</returns>
        System.Threading.Tasks.Task<ApiResponse<Object>> PublicGetInstrumentsGetAsyncWithHttpInfo (string currency, string kind = null, bool? expired = null);
        /// <summary>
        /// Retrieves historical settlement, delivery and bankruptcy events coming from all instruments within given currency.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="type">Settlement type (optional)</param>
        /// <param name="count">Number of requested items, default - &#x60;20&#x60; (optional)</param>
        /// <param name="continuation">Continuation token for pagination (optional)</param>
        /// <param name="searchStartTimestamp">The latest timestamp to return result for (optional)</param>
        /// <returns>Task of Object</returns>
        System.Threading.Tasks.Task<Object> PublicGetLastSettlementsByCurrencyGetAsync (string currency, string type = null, int? count = null, string continuation = null, int? searchStartTimestamp = null);

        /// <summary>
        /// Retrieves historical settlement, delivery and bankruptcy events coming from all instruments within given currency.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="type">Settlement type (optional)</param>
        /// <param name="count">Number of requested items, default - &#x60;20&#x60; (optional)</param>
        /// <param name="continuation">Continuation token for pagination (optional)</param>
        /// <param name="searchStartTimestamp">The latest timestamp to return result for (optional)</param>
        /// <returns>Task of ApiResponse (Object)</returns>
        System.Threading.Tasks.Task<ApiResponse<Object>> PublicGetLastSettlementsByCurrencyGetAsyncWithHttpInfo (string currency, string type = null, int? count = null, string continuation = null, int? searchStartTimestamp = null);
        /// <summary>
        /// Retrieves historical public settlement, delivery and bankruptcy events filtered by instrument name.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">Instrument name</param>
        /// <param name="type">Settlement type (optional)</param>
        /// <param name="count">Number of requested items, default - &#x60;20&#x60; (optional)</param>
        /// <param name="continuation">Continuation token for pagination (optional)</param>
        /// <param name="searchStartTimestamp">The latest timestamp to return result for (optional)</param>
        /// <returns>Task of Object</returns>
        System.Threading.Tasks.Task<Object> PublicGetLastSettlementsByInstrumentGetAsync (string instrumentName, string type = null, int? count = null, string continuation = null, int? searchStartTimestamp = null);

        /// <summary>
        /// Retrieves historical public settlement, delivery and bankruptcy events filtered by instrument name.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">Instrument name</param>
        /// <param name="type">Settlement type (optional)</param>
        /// <param name="count">Number of requested items, default - &#x60;20&#x60; (optional)</param>
        /// <param name="continuation">Continuation token for pagination (optional)</param>
        /// <param name="searchStartTimestamp">The latest timestamp to return result for (optional)</param>
        /// <returns>Task of ApiResponse (Object)</returns>
        System.Threading.Tasks.Task<ApiResponse<Object>> PublicGetLastSettlementsByInstrumentGetAsyncWithHttpInfo (string instrumentName, string type = null, int? count = null, string continuation = null, int? searchStartTimestamp = null);
        /// <summary>
        /// Retrieve the latest trades that have occurred for instruments in a specific currency symbol and within given time range.
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
        System.Threading.Tasks.Task<Object> PublicGetLastTradesByCurrencyAndTimeGetAsync (string currency, int? startTimestamp, int? endTimestamp, string kind = null, int? count = null, bool? includeOld = null, string sorting = null);

        /// <summary>
        /// Retrieve the latest trades that have occurred for instruments in a specific currency symbol and within given time range.
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
        System.Threading.Tasks.Task<ApiResponse<Object>> PublicGetLastTradesByCurrencyAndTimeGetAsyncWithHttpInfo (string currency, int? startTimestamp, int? endTimestamp, string kind = null, int? count = null, bool? includeOld = null, string sorting = null);
        /// <summary>
        /// Retrieve the latest trades that have occurred for instruments in a specific currency symbol.
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
        System.Threading.Tasks.Task<Object> PublicGetLastTradesByCurrencyGetAsync (string currency, string kind = null, string startId = null, string endId = null, int? count = null, bool? includeOld = null, string sorting = null);

        /// <summary>
        /// Retrieve the latest trades that have occurred for instruments in a specific currency symbol.
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
        System.Threading.Tasks.Task<ApiResponse<Object>> PublicGetLastTradesByCurrencyGetAsyncWithHttpInfo (string currency, string kind = null, string startId = null, string endId = null, int? count = null, bool? includeOld = null, string sorting = null);
        /// <summary>
        /// Retrieve the latest trades that have occurred for a specific instrument and within given time range.
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
        System.Threading.Tasks.Task<Object> PublicGetLastTradesByInstrumentAndTimeGetAsync (string instrumentName, int? startTimestamp, int? endTimestamp, int? count = null, bool? includeOld = null, string sorting = null);

        /// <summary>
        /// Retrieve the latest trades that have occurred for a specific instrument and within given time range.
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
        System.Threading.Tasks.Task<ApiResponse<Object>> PublicGetLastTradesByInstrumentAndTimeGetAsyncWithHttpInfo (string instrumentName, int? startTimestamp, int? endTimestamp, int? count = null, bool? includeOld = null, string sorting = null);
        /// <summary>
        /// Retrieve the latest trades that have occurred for a specific instrument.
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
        System.Threading.Tasks.Task<Object> PublicGetLastTradesByInstrumentGetAsync (string instrumentName, int? startSeq = null, int? endSeq = null, int? count = null, bool? includeOld = null, string sorting = null);

        /// <summary>
        /// Retrieve the latest trades that have occurred for a specific instrument.
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
        System.Threading.Tasks.Task<ApiResponse<Object>> PublicGetLastTradesByInstrumentGetAsyncWithHttpInfo (string instrumentName, int? startSeq = null, int? endSeq = null, int? count = null, bool? includeOld = null, string sorting = null);
        /// <summary>
        /// Retrieves the order book, along with other market values for a given instrument.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">The instrument name for which to retrieve the order book, see [&#x60;getinstruments&#x60;](#getinstruments) to obtain instrument names.</param>
        /// <param name="depth">The number of entries to return for bids and asks. (optional)</param>
        /// <returns>Task of Object</returns>
        System.Threading.Tasks.Task<Object> PublicGetOrderBookGetAsync (string instrumentName, decimal? depth = null);

        /// <summary>
        /// Retrieves the order book, along with other market values for a given instrument.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">The instrument name for which to retrieve the order book, see [&#x60;getinstruments&#x60;](#getinstruments) to obtain instrument names.</param>
        /// <param name="depth">The number of entries to return for bids and asks. (optional)</param>
        /// <returns>Task of ApiResponse (Object)</returns>
        System.Threading.Tasks.Task<ApiResponse<Object>> PublicGetOrderBookGetAsyncWithHttpInfo (string instrumentName, decimal? depth = null);
        /// <summary>
        /// Retrieves the current time (in milliseconds). This API endpoint can be used to check the clock skew between your software and Deribit&#39;s systems.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <returns>Task of Object</returns>
        System.Threading.Tasks.Task<Object> PublicGetTimeGetAsync ();

        /// <summary>
        /// Retrieves the current time (in milliseconds). This API endpoint can be used to check the clock skew between your software and Deribit&#39;s systems.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <returns>Task of ApiResponse (Object)</returns>
        System.Threading.Tasks.Task<ApiResponse<Object>> PublicGetTimeGetAsyncWithHttpInfo ();
        /// <summary>
        /// Retrieves aggregated 24h trade volumes for different instrument types and currencies.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <returns>Task of Object</returns>
        System.Threading.Tasks.Task<Object> PublicGetTradeVolumesGetAsync ();

        /// <summary>
        /// Retrieves aggregated 24h trade volumes for different instrument types and currencies.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <returns>Task of ApiResponse (Object)</returns>
        System.Threading.Tasks.Task<ApiResponse<Object>> PublicGetTradeVolumesGetAsyncWithHttpInfo ();
        /// <summary>
        /// Publicly available market data used to generate a TradingView candle chart.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">Instrument name</param>
        /// <param name="startTimestamp">The earliest timestamp to return result for</param>
        /// <param name="endTimestamp">The most recent timestamp to return result for</param>
        /// <returns>Task of Object</returns>
        System.Threading.Tasks.Task<Object> PublicGetTradingviewChartDataGetAsync (string instrumentName, int? startTimestamp, int? endTimestamp);

        /// <summary>
        /// Publicly available market data used to generate a TradingView candle chart.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">Instrument name</param>
        /// <param name="startTimestamp">The earliest timestamp to return result for</param>
        /// <param name="endTimestamp">The most recent timestamp to return result for</param>
        /// <returns>Task of ApiResponse (Object)</returns>
        System.Threading.Tasks.Task<ApiResponse<Object>> PublicGetTradingviewChartDataGetAsyncWithHttpInfo (string instrumentName, int? startTimestamp, int? endTimestamp);
        /// <summary>
        /// Tests the connection to the API server, and returns its version. You can use this to make sure the API is reachable, and matches the expected version.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="expectedResult">The value \&quot;exception\&quot; will trigger an error response. This may be useful for testing wrapper libraries. (optional)</param>
        /// <returns>Task of Object</returns>
        System.Threading.Tasks.Task<Object> PublicTestGetAsync (string expectedResult = null);

        /// <summary>
        /// Tests the connection to the API server, and returns its version. You can use this to make sure the API is reachable, and matches the expected version.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="expectedResult">The value \&quot;exception\&quot; will trigger an error response. This may be useful for testing wrapper libraries. (optional)</param>
        /// <returns>Task of ApiResponse (Object)</returns>
        System.Threading.Tasks.Task<ApiResponse<Object>> PublicTestGetAsyncWithHttpInfo (string expectedResult = null);
        /// <summary>
        /// Get ticker for an instrument.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">Instrument name</param>
        /// <returns>Task of Object</returns>
        System.Threading.Tasks.Task<Object> PublicTickerGetAsync (string instrumentName);

        /// <summary>
        /// Get ticker for an instrument.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">Instrument name</param>
        /// <returns>Task of ApiResponse (Object)</returns>
        System.Threading.Tasks.Task<ApiResponse<Object>> PublicTickerGetAsyncWithHttpInfo (string instrumentName);
        /// <summary>
        /// Method used to introduce the client software connected to Deribit platform over websocket. Provided data may have an impact on the maintained connection and will be collected for internal statistical purposes. In response, Deribit will also introduce itself.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="field">Name of the field to be validated, examples: postal_code, date_of_birth</param>
        /// <param name="value">Value to be checked</param>
        /// <param name="value2">Additional value to be compared with (optional)</param>
        /// <returns>Task of Object</returns>
        System.Threading.Tasks.Task<Object> PublicValidateFieldGetAsync (string field, string value, string value2 = null);

        /// <summary>
        /// Method used to introduce the client software connected to Deribit platform over websocket. Provided data may have an impact on the maintained connection and will be collected for internal statistical purposes. In response, Deribit will also introduce itself.
        /// </summary>
        /// <remarks>
        /// 
        /// </remarks>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="field">Name of the field to be validated, examples: postal_code, date_of_birth</param>
        /// <param name="value">Value to be checked</param>
        /// <param name="value2">Additional value to be compared with (optional)</param>
        /// <returns>Task of ApiResponse (Object)</returns>
        System.Threading.Tasks.Task<ApiResponse<Object>> PublicValidateFieldGetAsyncWithHttpInfo (string field, string value, string value2 = null);
        #endregion Asynchronous Operations
    }

    /// <summary>
    /// Represents a collection of functions to interact with the API endpoints
    /// </summary>
    public partial class PublicApi : IPublicApi
    {
        private Org.OpenAPITools.Client.ExceptionFactory _exceptionFactory = (name, response) => null;

        /// <summary>
        /// Initializes a new instance of the <see cref="PublicApi"/> class.
        /// </summary>
        /// <returns></returns>
        public PublicApi(String basePath)
        {
            this.Configuration = new Org.OpenAPITools.Client.Configuration { BasePath = basePath };

            ExceptionFactory = Org.OpenAPITools.Client.Configuration.DefaultExceptionFactory;
        }

        /// <summary>
        /// Initializes a new instance of the <see cref="PublicApi"/> class
        /// </summary>
        /// <returns></returns>
        public PublicApi()
        {
            this.Configuration = Org.OpenAPITools.Client.Configuration.Default;

            ExceptionFactory = Org.OpenAPITools.Client.Configuration.DefaultExceptionFactory;
        }

        /// <summary>
        /// Initializes a new instance of the <see cref="PublicApi"/> class
        /// using Configuration object
        /// </summary>
        /// <param name="configuration">An instance of Configuration</param>
        /// <returns></returns>
        public PublicApi(Org.OpenAPITools.Client.Configuration configuration = null)
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
        /// Authenticate Retrieve an Oauth access token, to be used for authentication of &#39;private&#39; requests.  Three methods of authentication are supported:  - &lt;code&gt;password&lt;/code&gt; - using email and and password as when logging on to the website - &lt;code&gt;client_credentials&lt;/code&gt; - using the access key and access secret that can be found on the API page on the website - &lt;code&gt;client_signature&lt;/code&gt; - using the access key that can be found on the API page on the website and user generated signature. The signature is calculated using some fields provided in the request, using formula described here [Deribit signature credentials](#additional-authorization-method-deribit-signature-credentials) - &lt;code&gt;refresh_token&lt;/code&gt; - using a refresh token that was received from an earlier invocation  The response will contain an access token, expiration period (number of seconds that the token is valid) and a refresh token that can be used to get a new set of tokens. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="grantType">Method of authentication</param>
        /// <param name="username">Required for grant type &#x60;password&#x60;</param>
        /// <param name="password">Required for grant type &#x60;password&#x60;</param>
        /// <param name="clientId">Required for grant type &#x60;client_credentials&#x60; and &#x60;client_signature&#x60;</param>
        /// <param name="clientSecret">Required for grant type &#x60;client_credentials&#x60;</param>
        /// <param name="refreshToken">Required for grant type &#x60;refresh_token&#x60;</param>
        /// <param name="timestamp">Required for grant type &#x60;client_signature&#x60;, provides time when request has been generated</param>
        /// <param name="signature">Required for grant type &#x60;client_signature&#x60;; it&#39;s a cryptographic signature calculated over provided fields using user **secret key**. The signature should be calculated as an HMAC (Hash-based Message Authentication Code) with &#x60;SHA256&#x60; hash algorithm</param>
        /// <param name="nonce">Optional for grant type &#x60;client_signature&#x60;; delivers user generated initialization vector for the server token (optional)</param>
        /// <param name="state">Will be passed back in the response (optional)</param>
        /// <param name="scope">Describes type of the access for assigned token, possible values: &#x60;connection&#x60;, &#x60;session&#x60;, &#x60;session:name&#x60;, &#x60;trade:[read, read_write, none]&#x60;, &#x60;wallet:[read, read_write, none]&#x60;, &#x60;account:[read, read_write, none]&#x60;, &#x60;expires:NUMBER&#x60; (token will expire after &#x60;NUMBER&#x60; of seconds).&lt;/BR&gt;&lt;/BR&gt; **NOTICE:** Depending on choosing an authentication method (&#x60;&#x60;&#x60;grant type&#x60;&#x60;&#x60;) some scopes could be narrowed by the server. e.g. when &#x60;&#x60;&#x60;grant_type &#x3D; client_credentials&#x60;&#x60;&#x60; and &#x60;&#x60;&#x60;scope &#x3D; wallet:read_write&#x60;&#x60;&#x60; it&#39;s modified by the server as &#x60;&#x60;&#x60;scope &#x3D; wallet:read&#x60;&#x60;&#x60; (optional)</param>
        /// <returns>Object</returns>
        public Object PublicAuthGet (string grantType, string username, string password, string clientId, string clientSecret, string refreshToken, string timestamp, string signature, string nonce = null, string state = null, string scope = null)
        {
             ApiResponse<Object> localVarResponse = PublicAuthGetWithHttpInfo(grantType, username, password, clientId, clientSecret, refreshToken, timestamp, signature, nonce, state, scope);
             return localVarResponse.Data;
        }

        /// <summary>
        /// Authenticate Retrieve an Oauth access token, to be used for authentication of &#39;private&#39; requests.  Three methods of authentication are supported:  - &lt;code&gt;password&lt;/code&gt; - using email and and password as when logging on to the website - &lt;code&gt;client_credentials&lt;/code&gt; - using the access key and access secret that can be found on the API page on the website - &lt;code&gt;client_signature&lt;/code&gt; - using the access key that can be found on the API page on the website and user generated signature. The signature is calculated using some fields provided in the request, using formula described here [Deribit signature credentials](#additional-authorization-method-deribit-signature-credentials) - &lt;code&gt;refresh_token&lt;/code&gt; - using a refresh token that was received from an earlier invocation  The response will contain an access token, expiration period (number of seconds that the token is valid) and a refresh token that can be used to get a new set of tokens. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="grantType">Method of authentication</param>
        /// <param name="username">Required for grant type &#x60;password&#x60;</param>
        /// <param name="password">Required for grant type &#x60;password&#x60;</param>
        /// <param name="clientId">Required for grant type &#x60;client_credentials&#x60; and &#x60;client_signature&#x60;</param>
        /// <param name="clientSecret">Required for grant type &#x60;client_credentials&#x60;</param>
        /// <param name="refreshToken">Required for grant type &#x60;refresh_token&#x60;</param>
        /// <param name="timestamp">Required for grant type &#x60;client_signature&#x60;, provides time when request has been generated</param>
        /// <param name="signature">Required for grant type &#x60;client_signature&#x60;; it&#39;s a cryptographic signature calculated over provided fields using user **secret key**. The signature should be calculated as an HMAC (Hash-based Message Authentication Code) with &#x60;SHA256&#x60; hash algorithm</param>
        /// <param name="nonce">Optional for grant type &#x60;client_signature&#x60;; delivers user generated initialization vector for the server token (optional)</param>
        /// <param name="state">Will be passed back in the response (optional)</param>
        /// <param name="scope">Describes type of the access for assigned token, possible values: &#x60;connection&#x60;, &#x60;session&#x60;, &#x60;session:name&#x60;, &#x60;trade:[read, read_write, none]&#x60;, &#x60;wallet:[read, read_write, none]&#x60;, &#x60;account:[read, read_write, none]&#x60;, &#x60;expires:NUMBER&#x60; (token will expire after &#x60;NUMBER&#x60; of seconds).&lt;/BR&gt;&lt;/BR&gt; **NOTICE:** Depending on choosing an authentication method (&#x60;&#x60;&#x60;grant type&#x60;&#x60;&#x60;) some scopes could be narrowed by the server. e.g. when &#x60;&#x60;&#x60;grant_type &#x3D; client_credentials&#x60;&#x60;&#x60; and &#x60;&#x60;&#x60;scope &#x3D; wallet:read_write&#x60;&#x60;&#x60; it&#39;s modified by the server as &#x60;&#x60;&#x60;scope &#x3D; wallet:read&#x60;&#x60;&#x60; (optional)</param>
        /// <returns>ApiResponse of Object</returns>
        public ApiResponse< Object > PublicAuthGetWithHttpInfo (string grantType, string username, string password, string clientId, string clientSecret, string refreshToken, string timestamp, string signature, string nonce = null, string state = null, string scope = null)
        {
            // verify the required parameter 'grantType' is set
            if (grantType == null)
                throw new ApiException(400, "Missing required parameter 'grantType' when calling PublicApi->PublicAuthGet");
            // verify the required parameter 'username' is set
            if (username == null)
                throw new ApiException(400, "Missing required parameter 'username' when calling PublicApi->PublicAuthGet");
            // verify the required parameter 'password' is set
            if (password == null)
                throw new ApiException(400, "Missing required parameter 'password' when calling PublicApi->PublicAuthGet");
            // verify the required parameter 'clientId' is set
            if (clientId == null)
                throw new ApiException(400, "Missing required parameter 'clientId' when calling PublicApi->PublicAuthGet");
            // verify the required parameter 'clientSecret' is set
            if (clientSecret == null)
                throw new ApiException(400, "Missing required parameter 'clientSecret' when calling PublicApi->PublicAuthGet");
            // verify the required parameter 'refreshToken' is set
            if (refreshToken == null)
                throw new ApiException(400, "Missing required parameter 'refreshToken' when calling PublicApi->PublicAuthGet");
            // verify the required parameter 'timestamp' is set
            if (timestamp == null)
                throw new ApiException(400, "Missing required parameter 'timestamp' when calling PublicApi->PublicAuthGet");
            // verify the required parameter 'signature' is set
            if (signature == null)
                throw new ApiException(400, "Missing required parameter 'signature' when calling PublicApi->PublicAuthGet");

            var localVarPath = "/public/auth";
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

            if (grantType != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "grant_type", grantType)); // query parameter
            if (username != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "username", username)); // query parameter
            if (password != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "password", password)); // query parameter
            if (clientId != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "client_id", clientId)); // query parameter
            if (clientSecret != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "client_secret", clientSecret)); // query parameter
            if (refreshToken != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "refresh_token", refreshToken)); // query parameter
            if (timestamp != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "timestamp", timestamp)); // query parameter
            if (signature != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "signature", signature)); // query parameter
            if (nonce != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "nonce", nonce)); // query parameter
            if (state != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "state", state)); // query parameter
            if (scope != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "scope", scope)); // query parameter

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
                Exception exception = ExceptionFactory("PublicAuthGet", localVarResponse);
                if (exception != null) throw exception;
            }

            return new ApiResponse<Object>(localVarStatusCode,
                localVarResponse.Headers.ToDictionary(x => x.Name, x => string.Join(",", x.Value)),
                (Object) this.Configuration.ApiClient.Deserialize(localVarResponse, typeof(Object)));
        }

        /// <summary>
        /// Authenticate Retrieve an Oauth access token, to be used for authentication of &#39;private&#39; requests.  Three methods of authentication are supported:  - &lt;code&gt;password&lt;/code&gt; - using email and and password as when logging on to the website - &lt;code&gt;client_credentials&lt;/code&gt; - using the access key and access secret that can be found on the API page on the website - &lt;code&gt;client_signature&lt;/code&gt; - using the access key that can be found on the API page on the website and user generated signature. The signature is calculated using some fields provided in the request, using formula described here [Deribit signature credentials](#additional-authorization-method-deribit-signature-credentials) - &lt;code&gt;refresh_token&lt;/code&gt; - using a refresh token that was received from an earlier invocation  The response will contain an access token, expiration period (number of seconds that the token is valid) and a refresh token that can be used to get a new set of tokens. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="grantType">Method of authentication</param>
        /// <param name="username">Required for grant type &#x60;password&#x60;</param>
        /// <param name="password">Required for grant type &#x60;password&#x60;</param>
        /// <param name="clientId">Required for grant type &#x60;client_credentials&#x60; and &#x60;client_signature&#x60;</param>
        /// <param name="clientSecret">Required for grant type &#x60;client_credentials&#x60;</param>
        /// <param name="refreshToken">Required for grant type &#x60;refresh_token&#x60;</param>
        /// <param name="timestamp">Required for grant type &#x60;client_signature&#x60;, provides time when request has been generated</param>
        /// <param name="signature">Required for grant type &#x60;client_signature&#x60;; it&#39;s a cryptographic signature calculated over provided fields using user **secret key**. The signature should be calculated as an HMAC (Hash-based Message Authentication Code) with &#x60;SHA256&#x60; hash algorithm</param>
        /// <param name="nonce">Optional for grant type &#x60;client_signature&#x60;; delivers user generated initialization vector for the server token (optional)</param>
        /// <param name="state">Will be passed back in the response (optional)</param>
        /// <param name="scope">Describes type of the access for assigned token, possible values: &#x60;connection&#x60;, &#x60;session&#x60;, &#x60;session:name&#x60;, &#x60;trade:[read, read_write, none]&#x60;, &#x60;wallet:[read, read_write, none]&#x60;, &#x60;account:[read, read_write, none]&#x60;, &#x60;expires:NUMBER&#x60; (token will expire after &#x60;NUMBER&#x60; of seconds).&lt;/BR&gt;&lt;/BR&gt; **NOTICE:** Depending on choosing an authentication method (&#x60;&#x60;&#x60;grant type&#x60;&#x60;&#x60;) some scopes could be narrowed by the server. e.g. when &#x60;&#x60;&#x60;grant_type &#x3D; client_credentials&#x60;&#x60;&#x60; and &#x60;&#x60;&#x60;scope &#x3D; wallet:read_write&#x60;&#x60;&#x60; it&#39;s modified by the server as &#x60;&#x60;&#x60;scope &#x3D; wallet:read&#x60;&#x60;&#x60; (optional)</param>
        /// <returns>Task of Object</returns>
        public async System.Threading.Tasks.Task<Object> PublicAuthGetAsync (string grantType, string username, string password, string clientId, string clientSecret, string refreshToken, string timestamp, string signature, string nonce = null, string state = null, string scope = null)
        {
             ApiResponse<Object> localVarResponse = await PublicAuthGetAsyncWithHttpInfo(grantType, username, password, clientId, clientSecret, refreshToken, timestamp, signature, nonce, state, scope);
             return localVarResponse.Data;

        }

        /// <summary>
        /// Authenticate Retrieve an Oauth access token, to be used for authentication of &#39;private&#39; requests.  Three methods of authentication are supported:  - &lt;code&gt;password&lt;/code&gt; - using email and and password as when logging on to the website - &lt;code&gt;client_credentials&lt;/code&gt; - using the access key and access secret that can be found on the API page on the website - &lt;code&gt;client_signature&lt;/code&gt; - using the access key that can be found on the API page on the website and user generated signature. The signature is calculated using some fields provided in the request, using formula described here [Deribit signature credentials](#additional-authorization-method-deribit-signature-credentials) - &lt;code&gt;refresh_token&lt;/code&gt; - using a refresh token that was received from an earlier invocation  The response will contain an access token, expiration period (number of seconds that the token is valid) and a refresh token that can be used to get a new set of tokens. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="grantType">Method of authentication</param>
        /// <param name="username">Required for grant type &#x60;password&#x60;</param>
        /// <param name="password">Required for grant type &#x60;password&#x60;</param>
        /// <param name="clientId">Required for grant type &#x60;client_credentials&#x60; and &#x60;client_signature&#x60;</param>
        /// <param name="clientSecret">Required for grant type &#x60;client_credentials&#x60;</param>
        /// <param name="refreshToken">Required for grant type &#x60;refresh_token&#x60;</param>
        /// <param name="timestamp">Required for grant type &#x60;client_signature&#x60;, provides time when request has been generated</param>
        /// <param name="signature">Required for grant type &#x60;client_signature&#x60;; it&#39;s a cryptographic signature calculated over provided fields using user **secret key**. The signature should be calculated as an HMAC (Hash-based Message Authentication Code) with &#x60;SHA256&#x60; hash algorithm</param>
        /// <param name="nonce">Optional for grant type &#x60;client_signature&#x60;; delivers user generated initialization vector for the server token (optional)</param>
        /// <param name="state">Will be passed back in the response (optional)</param>
        /// <param name="scope">Describes type of the access for assigned token, possible values: &#x60;connection&#x60;, &#x60;session&#x60;, &#x60;session:name&#x60;, &#x60;trade:[read, read_write, none]&#x60;, &#x60;wallet:[read, read_write, none]&#x60;, &#x60;account:[read, read_write, none]&#x60;, &#x60;expires:NUMBER&#x60; (token will expire after &#x60;NUMBER&#x60; of seconds).&lt;/BR&gt;&lt;/BR&gt; **NOTICE:** Depending on choosing an authentication method (&#x60;&#x60;&#x60;grant type&#x60;&#x60;&#x60;) some scopes could be narrowed by the server. e.g. when &#x60;&#x60;&#x60;grant_type &#x3D; client_credentials&#x60;&#x60;&#x60; and &#x60;&#x60;&#x60;scope &#x3D; wallet:read_write&#x60;&#x60;&#x60; it&#39;s modified by the server as &#x60;&#x60;&#x60;scope &#x3D; wallet:read&#x60;&#x60;&#x60; (optional)</param>
        /// <returns>Task of ApiResponse (Object)</returns>
        public async System.Threading.Tasks.Task<ApiResponse<Object>> PublicAuthGetAsyncWithHttpInfo (string grantType, string username, string password, string clientId, string clientSecret, string refreshToken, string timestamp, string signature, string nonce = null, string state = null, string scope = null)
        {
            // verify the required parameter 'grantType' is set
            if (grantType == null)
                throw new ApiException(400, "Missing required parameter 'grantType' when calling PublicApi->PublicAuthGet");
            // verify the required parameter 'username' is set
            if (username == null)
                throw new ApiException(400, "Missing required parameter 'username' when calling PublicApi->PublicAuthGet");
            // verify the required parameter 'password' is set
            if (password == null)
                throw new ApiException(400, "Missing required parameter 'password' when calling PublicApi->PublicAuthGet");
            // verify the required parameter 'clientId' is set
            if (clientId == null)
                throw new ApiException(400, "Missing required parameter 'clientId' when calling PublicApi->PublicAuthGet");
            // verify the required parameter 'clientSecret' is set
            if (clientSecret == null)
                throw new ApiException(400, "Missing required parameter 'clientSecret' when calling PublicApi->PublicAuthGet");
            // verify the required parameter 'refreshToken' is set
            if (refreshToken == null)
                throw new ApiException(400, "Missing required parameter 'refreshToken' when calling PublicApi->PublicAuthGet");
            // verify the required parameter 'timestamp' is set
            if (timestamp == null)
                throw new ApiException(400, "Missing required parameter 'timestamp' when calling PublicApi->PublicAuthGet");
            // verify the required parameter 'signature' is set
            if (signature == null)
                throw new ApiException(400, "Missing required parameter 'signature' when calling PublicApi->PublicAuthGet");

            var localVarPath = "/public/auth";
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

            if (grantType != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "grant_type", grantType)); // query parameter
            if (username != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "username", username)); // query parameter
            if (password != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "password", password)); // query parameter
            if (clientId != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "client_id", clientId)); // query parameter
            if (clientSecret != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "client_secret", clientSecret)); // query parameter
            if (refreshToken != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "refresh_token", refreshToken)); // query parameter
            if (timestamp != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "timestamp", timestamp)); // query parameter
            if (signature != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "signature", signature)); // query parameter
            if (nonce != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "nonce", nonce)); // query parameter
            if (state != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "state", state)); // query parameter
            if (scope != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "scope", scope)); // query parameter

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
                Exception exception = ExceptionFactory("PublicAuthGet", localVarResponse);
                if (exception != null) throw exception;
            }

            return new ApiResponse<Object>(localVarStatusCode,
                localVarResponse.Headers.ToDictionary(x => x.Name, x => string.Join(",", x.Value)),
                (Object) this.Configuration.ApiClient.Deserialize(localVarResponse, typeof(Object)));
        }

        /// <summary>
        /// Retrieves announcements from the last 30 days. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <returns>Object</returns>
        public Object PublicGetAnnouncementsGet ()
        {
             ApiResponse<Object> localVarResponse = PublicGetAnnouncementsGetWithHttpInfo();
             return localVarResponse.Data;
        }

        /// <summary>
        /// Retrieves announcements from the last 30 days. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <returns>ApiResponse of Object</returns>
        public ApiResponse< Object > PublicGetAnnouncementsGetWithHttpInfo ()
        {

            var localVarPath = "/public/get_announcements";
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
                Exception exception = ExceptionFactory("PublicGetAnnouncementsGet", localVarResponse);
                if (exception != null) throw exception;
            }

            return new ApiResponse<Object>(localVarStatusCode,
                localVarResponse.Headers.ToDictionary(x => x.Name, x => string.Join(",", x.Value)),
                (Object) this.Configuration.ApiClient.Deserialize(localVarResponse, typeof(Object)));
        }

        /// <summary>
        /// Retrieves announcements from the last 30 days. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <returns>Task of Object</returns>
        public async System.Threading.Tasks.Task<Object> PublicGetAnnouncementsGetAsync ()
        {
             ApiResponse<Object> localVarResponse = await PublicGetAnnouncementsGetAsyncWithHttpInfo();
             return localVarResponse.Data;

        }

        /// <summary>
        /// Retrieves announcements from the last 30 days. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <returns>Task of ApiResponse (Object)</returns>
        public async System.Threading.Tasks.Task<ApiResponse<Object>> PublicGetAnnouncementsGetAsyncWithHttpInfo ()
        {

            var localVarPath = "/public/get_announcements";
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
                Exception exception = ExceptionFactory("PublicGetAnnouncementsGet", localVarResponse);
                if (exception != null) throw exception;
            }

            return new ApiResponse<Object>(localVarStatusCode,
                localVarResponse.Headers.ToDictionary(x => x.Name, x => string.Join(",", x.Value)),
                (Object) this.Configuration.ApiClient.Deserialize(localVarResponse, typeof(Object)));
        }

        /// <summary>
        /// Retrieves the summary information such as open interest, 24h volume, etc. for all instruments for the currency (optionally filtered by kind). 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="kind">Instrument kind, if not provided instruments of all kinds are considered (optional)</param>
        /// <returns>Object</returns>
        public Object PublicGetBookSummaryByCurrencyGet (string currency, string kind = null)
        {
             ApiResponse<Object> localVarResponse = PublicGetBookSummaryByCurrencyGetWithHttpInfo(currency, kind);
             return localVarResponse.Data;
        }

        /// <summary>
        /// Retrieves the summary information such as open interest, 24h volume, etc. for all instruments for the currency (optionally filtered by kind). 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="kind">Instrument kind, if not provided instruments of all kinds are considered (optional)</param>
        /// <returns>ApiResponse of Object</returns>
        public ApiResponse< Object > PublicGetBookSummaryByCurrencyGetWithHttpInfo (string currency, string kind = null)
        {
            // verify the required parameter 'currency' is set
            if (currency == null)
                throw new ApiException(400, "Missing required parameter 'currency' when calling PublicApi->PublicGetBookSummaryByCurrencyGet");

            var localVarPath = "/public/get_book_summary_by_currency";
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
                Exception exception = ExceptionFactory("PublicGetBookSummaryByCurrencyGet", localVarResponse);
                if (exception != null) throw exception;
            }

            return new ApiResponse<Object>(localVarStatusCode,
                localVarResponse.Headers.ToDictionary(x => x.Name, x => string.Join(",", x.Value)),
                (Object) this.Configuration.ApiClient.Deserialize(localVarResponse, typeof(Object)));
        }

        /// <summary>
        /// Retrieves the summary information such as open interest, 24h volume, etc. for all instruments for the currency (optionally filtered by kind). 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="kind">Instrument kind, if not provided instruments of all kinds are considered (optional)</param>
        /// <returns>Task of Object</returns>
        public async System.Threading.Tasks.Task<Object> PublicGetBookSummaryByCurrencyGetAsync (string currency, string kind = null)
        {
             ApiResponse<Object> localVarResponse = await PublicGetBookSummaryByCurrencyGetAsyncWithHttpInfo(currency, kind);
             return localVarResponse.Data;

        }

        /// <summary>
        /// Retrieves the summary information such as open interest, 24h volume, etc. for all instruments for the currency (optionally filtered by kind). 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="kind">Instrument kind, if not provided instruments of all kinds are considered (optional)</param>
        /// <returns>Task of ApiResponse (Object)</returns>
        public async System.Threading.Tasks.Task<ApiResponse<Object>> PublicGetBookSummaryByCurrencyGetAsyncWithHttpInfo (string currency, string kind = null)
        {
            // verify the required parameter 'currency' is set
            if (currency == null)
                throw new ApiException(400, "Missing required parameter 'currency' when calling PublicApi->PublicGetBookSummaryByCurrencyGet");

            var localVarPath = "/public/get_book_summary_by_currency";
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
                Exception exception = ExceptionFactory("PublicGetBookSummaryByCurrencyGet", localVarResponse);
                if (exception != null) throw exception;
            }

            return new ApiResponse<Object>(localVarStatusCode,
                localVarResponse.Headers.ToDictionary(x => x.Name, x => string.Join(",", x.Value)),
                (Object) this.Configuration.ApiClient.Deserialize(localVarResponse, typeof(Object)));
        }

        /// <summary>
        /// Retrieves the summary information such as open interest, 24h volume, etc. for a specific instrument. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">Instrument name</param>
        /// <returns>Object</returns>
        public Object PublicGetBookSummaryByInstrumentGet (string instrumentName)
        {
             ApiResponse<Object> localVarResponse = PublicGetBookSummaryByInstrumentGetWithHttpInfo(instrumentName);
             return localVarResponse.Data;
        }

        /// <summary>
        /// Retrieves the summary information such as open interest, 24h volume, etc. for a specific instrument. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">Instrument name</param>
        /// <returns>ApiResponse of Object</returns>
        public ApiResponse< Object > PublicGetBookSummaryByInstrumentGetWithHttpInfo (string instrumentName)
        {
            // verify the required parameter 'instrumentName' is set
            if (instrumentName == null)
                throw new ApiException(400, "Missing required parameter 'instrumentName' when calling PublicApi->PublicGetBookSummaryByInstrumentGet");

            var localVarPath = "/public/get_book_summary_by_instrument";
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
                Exception exception = ExceptionFactory("PublicGetBookSummaryByInstrumentGet", localVarResponse);
                if (exception != null) throw exception;
            }

            return new ApiResponse<Object>(localVarStatusCode,
                localVarResponse.Headers.ToDictionary(x => x.Name, x => string.Join(",", x.Value)),
                (Object) this.Configuration.ApiClient.Deserialize(localVarResponse, typeof(Object)));
        }

        /// <summary>
        /// Retrieves the summary information such as open interest, 24h volume, etc. for a specific instrument. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">Instrument name</param>
        /// <returns>Task of Object</returns>
        public async System.Threading.Tasks.Task<Object> PublicGetBookSummaryByInstrumentGetAsync (string instrumentName)
        {
             ApiResponse<Object> localVarResponse = await PublicGetBookSummaryByInstrumentGetAsyncWithHttpInfo(instrumentName);
             return localVarResponse.Data;

        }

        /// <summary>
        /// Retrieves the summary information such as open interest, 24h volume, etc. for a specific instrument. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">Instrument name</param>
        /// <returns>Task of ApiResponse (Object)</returns>
        public async System.Threading.Tasks.Task<ApiResponse<Object>> PublicGetBookSummaryByInstrumentGetAsyncWithHttpInfo (string instrumentName)
        {
            // verify the required parameter 'instrumentName' is set
            if (instrumentName == null)
                throw new ApiException(400, "Missing required parameter 'instrumentName' when calling PublicApi->PublicGetBookSummaryByInstrumentGet");

            var localVarPath = "/public/get_book_summary_by_instrument";
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
                Exception exception = ExceptionFactory("PublicGetBookSummaryByInstrumentGet", localVarResponse);
                if (exception != null) throw exception;
            }

            return new ApiResponse<Object>(localVarStatusCode,
                localVarResponse.Headers.ToDictionary(x => x.Name, x => string.Join(",", x.Value)),
                (Object) this.Configuration.ApiClient.Deserialize(localVarResponse, typeof(Object)));
        }

        /// <summary>
        /// Retrieves contract size of provided instrument. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">Instrument name</param>
        /// <returns>Object</returns>
        public Object PublicGetContractSizeGet (string instrumentName)
        {
             ApiResponse<Object> localVarResponse = PublicGetContractSizeGetWithHttpInfo(instrumentName);
             return localVarResponse.Data;
        }

        /// <summary>
        /// Retrieves contract size of provided instrument. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">Instrument name</param>
        /// <returns>ApiResponse of Object</returns>
        public ApiResponse< Object > PublicGetContractSizeGetWithHttpInfo (string instrumentName)
        {
            // verify the required parameter 'instrumentName' is set
            if (instrumentName == null)
                throw new ApiException(400, "Missing required parameter 'instrumentName' when calling PublicApi->PublicGetContractSizeGet");

            var localVarPath = "/public/get_contract_size";
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
                Exception exception = ExceptionFactory("PublicGetContractSizeGet", localVarResponse);
                if (exception != null) throw exception;
            }

            return new ApiResponse<Object>(localVarStatusCode,
                localVarResponse.Headers.ToDictionary(x => x.Name, x => string.Join(",", x.Value)),
                (Object) this.Configuration.ApiClient.Deserialize(localVarResponse, typeof(Object)));
        }

        /// <summary>
        /// Retrieves contract size of provided instrument. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">Instrument name</param>
        /// <returns>Task of Object</returns>
        public async System.Threading.Tasks.Task<Object> PublicGetContractSizeGetAsync (string instrumentName)
        {
             ApiResponse<Object> localVarResponse = await PublicGetContractSizeGetAsyncWithHttpInfo(instrumentName);
             return localVarResponse.Data;

        }

        /// <summary>
        /// Retrieves contract size of provided instrument. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">Instrument name</param>
        /// <returns>Task of ApiResponse (Object)</returns>
        public async System.Threading.Tasks.Task<ApiResponse<Object>> PublicGetContractSizeGetAsyncWithHttpInfo (string instrumentName)
        {
            // verify the required parameter 'instrumentName' is set
            if (instrumentName == null)
                throw new ApiException(400, "Missing required parameter 'instrumentName' when calling PublicApi->PublicGetContractSizeGet");

            var localVarPath = "/public/get_contract_size";
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
                Exception exception = ExceptionFactory("PublicGetContractSizeGet", localVarResponse);
                if (exception != null) throw exception;
            }

            return new ApiResponse<Object>(localVarStatusCode,
                localVarResponse.Headers.ToDictionary(x => x.Name, x => string.Join(",", x.Value)),
                (Object) this.Configuration.ApiClient.Deserialize(localVarResponse, typeof(Object)));
        }

        /// <summary>
        /// Retrieves all cryptocurrencies supported by the API. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <returns>Object</returns>
        public Object PublicGetCurrenciesGet ()
        {
             ApiResponse<Object> localVarResponse = PublicGetCurrenciesGetWithHttpInfo();
             return localVarResponse.Data;
        }

        /// <summary>
        /// Retrieves all cryptocurrencies supported by the API. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <returns>ApiResponse of Object</returns>
        public ApiResponse< Object > PublicGetCurrenciesGetWithHttpInfo ()
        {

            var localVarPath = "/public/get_currencies";
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
                Exception exception = ExceptionFactory("PublicGetCurrenciesGet", localVarResponse);
                if (exception != null) throw exception;
            }

            return new ApiResponse<Object>(localVarStatusCode,
                localVarResponse.Headers.ToDictionary(x => x.Name, x => string.Join(",", x.Value)),
                (Object) this.Configuration.ApiClient.Deserialize(localVarResponse, typeof(Object)));
        }

        /// <summary>
        /// Retrieves all cryptocurrencies supported by the API. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <returns>Task of Object</returns>
        public async System.Threading.Tasks.Task<Object> PublicGetCurrenciesGetAsync ()
        {
             ApiResponse<Object> localVarResponse = await PublicGetCurrenciesGetAsyncWithHttpInfo();
             return localVarResponse.Data;

        }

        /// <summary>
        /// Retrieves all cryptocurrencies supported by the API. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <returns>Task of ApiResponse (Object)</returns>
        public async System.Threading.Tasks.Task<ApiResponse<Object>> PublicGetCurrenciesGetAsyncWithHttpInfo ()
        {

            var localVarPath = "/public/get_currencies";
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
                Exception exception = ExceptionFactory("PublicGetCurrenciesGet", localVarResponse);
                if (exception != null) throw exception;
            }

            return new ApiResponse<Object>(localVarStatusCode,
                localVarResponse.Headers.ToDictionary(x => x.Name, x => string.Join(",", x.Value)),
                (Object) this.Configuration.ApiClient.Deserialize(localVarResponse, typeof(Object)));
        }

        /// <summary>
        /// Retrieve the latest user trades that have occurred for PERPETUAL instruments in a specific currency symbol and within given time range. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">Instrument name</param>
        /// <param name="length">Specifies time period. &#x60;8h&#x60; - 8 hours, &#x60;24h&#x60; - 24 hours (optional)</param>
        /// <returns>Object</returns>
        public Object PublicGetFundingChartDataGet (string instrumentName, string length = null)
        {
             ApiResponse<Object> localVarResponse = PublicGetFundingChartDataGetWithHttpInfo(instrumentName, length);
             return localVarResponse.Data;
        }

        /// <summary>
        /// Retrieve the latest user trades that have occurred for PERPETUAL instruments in a specific currency symbol and within given time range. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">Instrument name</param>
        /// <param name="length">Specifies time period. &#x60;8h&#x60; - 8 hours, &#x60;24h&#x60; - 24 hours (optional)</param>
        /// <returns>ApiResponse of Object</returns>
        public ApiResponse< Object > PublicGetFundingChartDataGetWithHttpInfo (string instrumentName, string length = null)
        {
            // verify the required parameter 'instrumentName' is set
            if (instrumentName == null)
                throw new ApiException(400, "Missing required parameter 'instrumentName' when calling PublicApi->PublicGetFundingChartDataGet");

            var localVarPath = "/public/get_funding_chart_data";
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
            if (length != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "length", length)); // query parameter

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
                Exception exception = ExceptionFactory("PublicGetFundingChartDataGet", localVarResponse);
                if (exception != null) throw exception;
            }

            return new ApiResponse<Object>(localVarStatusCode,
                localVarResponse.Headers.ToDictionary(x => x.Name, x => string.Join(",", x.Value)),
                (Object) this.Configuration.ApiClient.Deserialize(localVarResponse, typeof(Object)));
        }

        /// <summary>
        /// Retrieve the latest user trades that have occurred for PERPETUAL instruments in a specific currency symbol and within given time range. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">Instrument name</param>
        /// <param name="length">Specifies time period. &#x60;8h&#x60; - 8 hours, &#x60;24h&#x60; - 24 hours (optional)</param>
        /// <returns>Task of Object</returns>
        public async System.Threading.Tasks.Task<Object> PublicGetFundingChartDataGetAsync (string instrumentName, string length = null)
        {
             ApiResponse<Object> localVarResponse = await PublicGetFundingChartDataGetAsyncWithHttpInfo(instrumentName, length);
             return localVarResponse.Data;

        }

        /// <summary>
        /// Retrieve the latest user trades that have occurred for PERPETUAL instruments in a specific currency symbol and within given time range. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">Instrument name</param>
        /// <param name="length">Specifies time period. &#x60;8h&#x60; - 8 hours, &#x60;24h&#x60; - 24 hours (optional)</param>
        /// <returns>Task of ApiResponse (Object)</returns>
        public async System.Threading.Tasks.Task<ApiResponse<Object>> PublicGetFundingChartDataGetAsyncWithHttpInfo (string instrumentName, string length = null)
        {
            // verify the required parameter 'instrumentName' is set
            if (instrumentName == null)
                throw new ApiException(400, "Missing required parameter 'instrumentName' when calling PublicApi->PublicGetFundingChartDataGet");

            var localVarPath = "/public/get_funding_chart_data";
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
            if (length != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "length", length)); // query parameter

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
                Exception exception = ExceptionFactory("PublicGetFundingChartDataGet", localVarResponse);
                if (exception != null) throw exception;
            }

            return new ApiResponse<Object>(localVarStatusCode,
                localVarResponse.Headers.ToDictionary(x => x.Name, x => string.Join(",", x.Value)),
                (Object) this.Configuration.ApiClient.Deserialize(localVarResponse, typeof(Object)));
        }

        /// <summary>
        /// Provides information about historical volatility for given cryptocurrency. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <returns>Object</returns>
        public Object PublicGetHistoricalVolatilityGet (string currency)
        {
             ApiResponse<Object> localVarResponse = PublicGetHistoricalVolatilityGetWithHttpInfo(currency);
             return localVarResponse.Data;
        }

        /// <summary>
        /// Provides information about historical volatility for given cryptocurrency. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <returns>ApiResponse of Object</returns>
        public ApiResponse< Object > PublicGetHistoricalVolatilityGetWithHttpInfo (string currency)
        {
            // verify the required parameter 'currency' is set
            if (currency == null)
                throw new ApiException(400, "Missing required parameter 'currency' when calling PublicApi->PublicGetHistoricalVolatilityGet");

            var localVarPath = "/public/get_historical_volatility";
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
                Exception exception = ExceptionFactory("PublicGetHistoricalVolatilityGet", localVarResponse);
                if (exception != null) throw exception;
            }

            return new ApiResponse<Object>(localVarStatusCode,
                localVarResponse.Headers.ToDictionary(x => x.Name, x => string.Join(",", x.Value)),
                (Object) this.Configuration.ApiClient.Deserialize(localVarResponse, typeof(Object)));
        }

        /// <summary>
        /// Provides information about historical volatility for given cryptocurrency. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <returns>Task of Object</returns>
        public async System.Threading.Tasks.Task<Object> PublicGetHistoricalVolatilityGetAsync (string currency)
        {
             ApiResponse<Object> localVarResponse = await PublicGetHistoricalVolatilityGetAsyncWithHttpInfo(currency);
             return localVarResponse.Data;

        }

        /// <summary>
        /// Provides information about historical volatility for given cryptocurrency. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <returns>Task of ApiResponse (Object)</returns>
        public async System.Threading.Tasks.Task<ApiResponse<Object>> PublicGetHistoricalVolatilityGetAsyncWithHttpInfo (string currency)
        {
            // verify the required parameter 'currency' is set
            if (currency == null)
                throw new ApiException(400, "Missing required parameter 'currency' when calling PublicApi->PublicGetHistoricalVolatilityGet");

            var localVarPath = "/public/get_historical_volatility";
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
                Exception exception = ExceptionFactory("PublicGetHistoricalVolatilityGet", localVarResponse);
                if (exception != null) throw exception;
            }

            return new ApiResponse<Object>(localVarStatusCode,
                localVarResponse.Headers.ToDictionary(x => x.Name, x => string.Join(",", x.Value)),
                (Object) this.Configuration.ApiClient.Deserialize(localVarResponse, typeof(Object)));
        }

        /// <summary>
        /// Retrieves the current index price for the instruments, for the selected currency. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <returns>Object</returns>
        public Object PublicGetIndexGet (string currency)
        {
             ApiResponse<Object> localVarResponse = PublicGetIndexGetWithHttpInfo(currency);
             return localVarResponse.Data;
        }

        /// <summary>
        /// Retrieves the current index price for the instruments, for the selected currency. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <returns>ApiResponse of Object</returns>
        public ApiResponse< Object > PublicGetIndexGetWithHttpInfo (string currency)
        {
            // verify the required parameter 'currency' is set
            if (currency == null)
                throw new ApiException(400, "Missing required parameter 'currency' when calling PublicApi->PublicGetIndexGet");

            var localVarPath = "/public/get_index";
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
                Exception exception = ExceptionFactory("PublicGetIndexGet", localVarResponse);
                if (exception != null) throw exception;
            }

            return new ApiResponse<Object>(localVarStatusCode,
                localVarResponse.Headers.ToDictionary(x => x.Name, x => string.Join(",", x.Value)),
                (Object) this.Configuration.ApiClient.Deserialize(localVarResponse, typeof(Object)));
        }

        /// <summary>
        /// Retrieves the current index price for the instruments, for the selected currency. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <returns>Task of Object</returns>
        public async System.Threading.Tasks.Task<Object> PublicGetIndexGetAsync (string currency)
        {
             ApiResponse<Object> localVarResponse = await PublicGetIndexGetAsyncWithHttpInfo(currency);
             return localVarResponse.Data;

        }

        /// <summary>
        /// Retrieves the current index price for the instruments, for the selected currency. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <returns>Task of ApiResponse (Object)</returns>
        public async System.Threading.Tasks.Task<ApiResponse<Object>> PublicGetIndexGetAsyncWithHttpInfo (string currency)
        {
            // verify the required parameter 'currency' is set
            if (currency == null)
                throw new ApiException(400, "Missing required parameter 'currency' when calling PublicApi->PublicGetIndexGet");

            var localVarPath = "/public/get_index";
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
                Exception exception = ExceptionFactory("PublicGetIndexGet", localVarResponse);
                if (exception != null) throw exception;
            }

            return new ApiResponse<Object>(localVarStatusCode,
                localVarResponse.Headers.ToDictionary(x => x.Name, x => string.Join(",", x.Value)),
                (Object) this.Configuration.ApiClient.Deserialize(localVarResponse, typeof(Object)));
        }

        /// <summary>
        /// Retrieves available trading instruments. This method can be used to see which instruments are available for trading, or which instruments have existed historically. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="kind">Instrument kind, if not provided instruments of all kinds are considered (optional)</param>
        /// <param name="expired">Set to true to show expired instruments instead of active ones. (optional, default to false)</param>
        /// <returns>Object</returns>
        public Object PublicGetInstrumentsGet (string currency, string kind = null, bool? expired = null)
        {
             ApiResponse<Object> localVarResponse = PublicGetInstrumentsGetWithHttpInfo(currency, kind, expired);
             return localVarResponse.Data;
        }

        /// <summary>
        /// Retrieves available trading instruments. This method can be used to see which instruments are available for trading, or which instruments have existed historically. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="kind">Instrument kind, if not provided instruments of all kinds are considered (optional)</param>
        /// <param name="expired">Set to true to show expired instruments instead of active ones. (optional, default to false)</param>
        /// <returns>ApiResponse of Object</returns>
        public ApiResponse< Object > PublicGetInstrumentsGetWithHttpInfo (string currency, string kind = null, bool? expired = null)
        {
            // verify the required parameter 'currency' is set
            if (currency == null)
                throw new ApiException(400, "Missing required parameter 'currency' when calling PublicApi->PublicGetInstrumentsGet");

            var localVarPath = "/public/get_instruments";
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
            if (expired != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "expired", expired)); // query parameter

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
                Exception exception = ExceptionFactory("PublicGetInstrumentsGet", localVarResponse);
                if (exception != null) throw exception;
            }

            return new ApiResponse<Object>(localVarStatusCode,
                localVarResponse.Headers.ToDictionary(x => x.Name, x => string.Join(",", x.Value)),
                (Object) this.Configuration.ApiClient.Deserialize(localVarResponse, typeof(Object)));
        }

        /// <summary>
        /// Retrieves available trading instruments. This method can be used to see which instruments are available for trading, or which instruments have existed historically. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="kind">Instrument kind, if not provided instruments of all kinds are considered (optional)</param>
        /// <param name="expired">Set to true to show expired instruments instead of active ones. (optional, default to false)</param>
        /// <returns>Task of Object</returns>
        public async System.Threading.Tasks.Task<Object> PublicGetInstrumentsGetAsync (string currency, string kind = null, bool? expired = null)
        {
             ApiResponse<Object> localVarResponse = await PublicGetInstrumentsGetAsyncWithHttpInfo(currency, kind, expired);
             return localVarResponse.Data;

        }

        /// <summary>
        /// Retrieves available trading instruments. This method can be used to see which instruments are available for trading, or which instruments have existed historically. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="kind">Instrument kind, if not provided instruments of all kinds are considered (optional)</param>
        /// <param name="expired">Set to true to show expired instruments instead of active ones. (optional, default to false)</param>
        /// <returns>Task of ApiResponse (Object)</returns>
        public async System.Threading.Tasks.Task<ApiResponse<Object>> PublicGetInstrumentsGetAsyncWithHttpInfo (string currency, string kind = null, bool? expired = null)
        {
            // verify the required parameter 'currency' is set
            if (currency == null)
                throw new ApiException(400, "Missing required parameter 'currency' when calling PublicApi->PublicGetInstrumentsGet");

            var localVarPath = "/public/get_instruments";
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
            if (expired != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "expired", expired)); // query parameter

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
                Exception exception = ExceptionFactory("PublicGetInstrumentsGet", localVarResponse);
                if (exception != null) throw exception;
            }

            return new ApiResponse<Object>(localVarStatusCode,
                localVarResponse.Headers.ToDictionary(x => x.Name, x => string.Join(",", x.Value)),
                (Object) this.Configuration.ApiClient.Deserialize(localVarResponse, typeof(Object)));
        }

        /// <summary>
        /// Retrieves historical settlement, delivery and bankruptcy events coming from all instruments within given currency. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="type">Settlement type (optional)</param>
        /// <param name="count">Number of requested items, default - &#x60;20&#x60; (optional)</param>
        /// <param name="continuation">Continuation token for pagination (optional)</param>
        /// <param name="searchStartTimestamp">The latest timestamp to return result for (optional)</param>
        /// <returns>Object</returns>
        public Object PublicGetLastSettlementsByCurrencyGet (string currency, string type = null, int? count = null, string continuation = null, int? searchStartTimestamp = null)
        {
             ApiResponse<Object> localVarResponse = PublicGetLastSettlementsByCurrencyGetWithHttpInfo(currency, type, count, continuation, searchStartTimestamp);
             return localVarResponse.Data;
        }

        /// <summary>
        /// Retrieves historical settlement, delivery and bankruptcy events coming from all instruments within given currency. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="type">Settlement type (optional)</param>
        /// <param name="count">Number of requested items, default - &#x60;20&#x60; (optional)</param>
        /// <param name="continuation">Continuation token for pagination (optional)</param>
        /// <param name="searchStartTimestamp">The latest timestamp to return result for (optional)</param>
        /// <returns>ApiResponse of Object</returns>
        public ApiResponse< Object > PublicGetLastSettlementsByCurrencyGetWithHttpInfo (string currency, string type = null, int? count = null, string continuation = null, int? searchStartTimestamp = null)
        {
            // verify the required parameter 'currency' is set
            if (currency == null)
                throw new ApiException(400, "Missing required parameter 'currency' when calling PublicApi->PublicGetLastSettlementsByCurrencyGet");

            var localVarPath = "/public/get_last_settlements_by_currency";
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
            if (continuation != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "continuation", continuation)); // query parameter
            if (searchStartTimestamp != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "search_start_timestamp", searchStartTimestamp)); // query parameter

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
                Exception exception = ExceptionFactory("PublicGetLastSettlementsByCurrencyGet", localVarResponse);
                if (exception != null) throw exception;
            }

            return new ApiResponse<Object>(localVarStatusCode,
                localVarResponse.Headers.ToDictionary(x => x.Name, x => string.Join(",", x.Value)),
                (Object) this.Configuration.ApiClient.Deserialize(localVarResponse, typeof(Object)));
        }

        /// <summary>
        /// Retrieves historical settlement, delivery and bankruptcy events coming from all instruments within given currency. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="type">Settlement type (optional)</param>
        /// <param name="count">Number of requested items, default - &#x60;20&#x60; (optional)</param>
        /// <param name="continuation">Continuation token for pagination (optional)</param>
        /// <param name="searchStartTimestamp">The latest timestamp to return result for (optional)</param>
        /// <returns>Task of Object</returns>
        public async System.Threading.Tasks.Task<Object> PublicGetLastSettlementsByCurrencyGetAsync (string currency, string type = null, int? count = null, string continuation = null, int? searchStartTimestamp = null)
        {
             ApiResponse<Object> localVarResponse = await PublicGetLastSettlementsByCurrencyGetAsyncWithHttpInfo(currency, type, count, continuation, searchStartTimestamp);
             return localVarResponse.Data;

        }

        /// <summary>
        /// Retrieves historical settlement, delivery and bankruptcy events coming from all instruments within given currency. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="currency">The currency symbol</param>
        /// <param name="type">Settlement type (optional)</param>
        /// <param name="count">Number of requested items, default - &#x60;20&#x60; (optional)</param>
        /// <param name="continuation">Continuation token for pagination (optional)</param>
        /// <param name="searchStartTimestamp">The latest timestamp to return result for (optional)</param>
        /// <returns>Task of ApiResponse (Object)</returns>
        public async System.Threading.Tasks.Task<ApiResponse<Object>> PublicGetLastSettlementsByCurrencyGetAsyncWithHttpInfo (string currency, string type = null, int? count = null, string continuation = null, int? searchStartTimestamp = null)
        {
            // verify the required parameter 'currency' is set
            if (currency == null)
                throw new ApiException(400, "Missing required parameter 'currency' when calling PublicApi->PublicGetLastSettlementsByCurrencyGet");

            var localVarPath = "/public/get_last_settlements_by_currency";
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
            if (continuation != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "continuation", continuation)); // query parameter
            if (searchStartTimestamp != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "search_start_timestamp", searchStartTimestamp)); // query parameter

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
                Exception exception = ExceptionFactory("PublicGetLastSettlementsByCurrencyGet", localVarResponse);
                if (exception != null) throw exception;
            }

            return new ApiResponse<Object>(localVarStatusCode,
                localVarResponse.Headers.ToDictionary(x => x.Name, x => string.Join(",", x.Value)),
                (Object) this.Configuration.ApiClient.Deserialize(localVarResponse, typeof(Object)));
        }

        /// <summary>
        /// Retrieves historical public settlement, delivery and bankruptcy events filtered by instrument name. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">Instrument name</param>
        /// <param name="type">Settlement type (optional)</param>
        /// <param name="count">Number of requested items, default - &#x60;20&#x60; (optional)</param>
        /// <param name="continuation">Continuation token for pagination (optional)</param>
        /// <param name="searchStartTimestamp">The latest timestamp to return result for (optional)</param>
        /// <returns>Object</returns>
        public Object PublicGetLastSettlementsByInstrumentGet (string instrumentName, string type = null, int? count = null, string continuation = null, int? searchStartTimestamp = null)
        {
             ApiResponse<Object> localVarResponse = PublicGetLastSettlementsByInstrumentGetWithHttpInfo(instrumentName, type, count, continuation, searchStartTimestamp);
             return localVarResponse.Data;
        }

        /// <summary>
        /// Retrieves historical public settlement, delivery and bankruptcy events filtered by instrument name. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">Instrument name</param>
        /// <param name="type">Settlement type (optional)</param>
        /// <param name="count">Number of requested items, default - &#x60;20&#x60; (optional)</param>
        /// <param name="continuation">Continuation token for pagination (optional)</param>
        /// <param name="searchStartTimestamp">The latest timestamp to return result for (optional)</param>
        /// <returns>ApiResponse of Object</returns>
        public ApiResponse< Object > PublicGetLastSettlementsByInstrumentGetWithHttpInfo (string instrumentName, string type = null, int? count = null, string continuation = null, int? searchStartTimestamp = null)
        {
            // verify the required parameter 'instrumentName' is set
            if (instrumentName == null)
                throw new ApiException(400, "Missing required parameter 'instrumentName' when calling PublicApi->PublicGetLastSettlementsByInstrumentGet");

            var localVarPath = "/public/get_last_settlements_by_instrument";
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
            if (continuation != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "continuation", continuation)); // query parameter
            if (searchStartTimestamp != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "search_start_timestamp", searchStartTimestamp)); // query parameter

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
                Exception exception = ExceptionFactory("PublicGetLastSettlementsByInstrumentGet", localVarResponse);
                if (exception != null) throw exception;
            }

            return new ApiResponse<Object>(localVarStatusCode,
                localVarResponse.Headers.ToDictionary(x => x.Name, x => string.Join(",", x.Value)),
                (Object) this.Configuration.ApiClient.Deserialize(localVarResponse, typeof(Object)));
        }

        /// <summary>
        /// Retrieves historical public settlement, delivery and bankruptcy events filtered by instrument name. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">Instrument name</param>
        /// <param name="type">Settlement type (optional)</param>
        /// <param name="count">Number of requested items, default - &#x60;20&#x60; (optional)</param>
        /// <param name="continuation">Continuation token for pagination (optional)</param>
        /// <param name="searchStartTimestamp">The latest timestamp to return result for (optional)</param>
        /// <returns>Task of Object</returns>
        public async System.Threading.Tasks.Task<Object> PublicGetLastSettlementsByInstrumentGetAsync (string instrumentName, string type = null, int? count = null, string continuation = null, int? searchStartTimestamp = null)
        {
             ApiResponse<Object> localVarResponse = await PublicGetLastSettlementsByInstrumentGetAsyncWithHttpInfo(instrumentName, type, count, continuation, searchStartTimestamp);
             return localVarResponse.Data;

        }

        /// <summary>
        /// Retrieves historical public settlement, delivery and bankruptcy events filtered by instrument name. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">Instrument name</param>
        /// <param name="type">Settlement type (optional)</param>
        /// <param name="count">Number of requested items, default - &#x60;20&#x60; (optional)</param>
        /// <param name="continuation">Continuation token for pagination (optional)</param>
        /// <param name="searchStartTimestamp">The latest timestamp to return result for (optional)</param>
        /// <returns>Task of ApiResponse (Object)</returns>
        public async System.Threading.Tasks.Task<ApiResponse<Object>> PublicGetLastSettlementsByInstrumentGetAsyncWithHttpInfo (string instrumentName, string type = null, int? count = null, string continuation = null, int? searchStartTimestamp = null)
        {
            // verify the required parameter 'instrumentName' is set
            if (instrumentName == null)
                throw new ApiException(400, "Missing required parameter 'instrumentName' when calling PublicApi->PublicGetLastSettlementsByInstrumentGet");

            var localVarPath = "/public/get_last_settlements_by_instrument";
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
            if (continuation != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "continuation", continuation)); // query parameter
            if (searchStartTimestamp != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "search_start_timestamp", searchStartTimestamp)); // query parameter

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
                Exception exception = ExceptionFactory("PublicGetLastSettlementsByInstrumentGet", localVarResponse);
                if (exception != null) throw exception;
            }

            return new ApiResponse<Object>(localVarStatusCode,
                localVarResponse.Headers.ToDictionary(x => x.Name, x => string.Join(",", x.Value)),
                (Object) this.Configuration.ApiClient.Deserialize(localVarResponse, typeof(Object)));
        }

        /// <summary>
        /// Retrieve the latest trades that have occurred for instruments in a specific currency symbol and within given time range. 
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
        public Object PublicGetLastTradesByCurrencyAndTimeGet (string currency, int? startTimestamp, int? endTimestamp, string kind = null, int? count = null, bool? includeOld = null, string sorting = null)
        {
             ApiResponse<Object> localVarResponse = PublicGetLastTradesByCurrencyAndTimeGetWithHttpInfo(currency, startTimestamp, endTimestamp, kind, count, includeOld, sorting);
             return localVarResponse.Data;
        }

        /// <summary>
        /// Retrieve the latest trades that have occurred for instruments in a specific currency symbol and within given time range. 
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
        public ApiResponse< Object > PublicGetLastTradesByCurrencyAndTimeGetWithHttpInfo (string currency, int? startTimestamp, int? endTimestamp, string kind = null, int? count = null, bool? includeOld = null, string sorting = null)
        {
            // verify the required parameter 'currency' is set
            if (currency == null)
                throw new ApiException(400, "Missing required parameter 'currency' when calling PublicApi->PublicGetLastTradesByCurrencyAndTimeGet");
            // verify the required parameter 'startTimestamp' is set
            if (startTimestamp == null)
                throw new ApiException(400, "Missing required parameter 'startTimestamp' when calling PublicApi->PublicGetLastTradesByCurrencyAndTimeGet");
            // verify the required parameter 'endTimestamp' is set
            if (endTimestamp == null)
                throw new ApiException(400, "Missing required parameter 'endTimestamp' when calling PublicApi->PublicGetLastTradesByCurrencyAndTimeGet");

            var localVarPath = "/public/get_last_trades_by_currency_and_time";
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
                Exception exception = ExceptionFactory("PublicGetLastTradesByCurrencyAndTimeGet", localVarResponse);
                if (exception != null) throw exception;
            }

            return new ApiResponse<Object>(localVarStatusCode,
                localVarResponse.Headers.ToDictionary(x => x.Name, x => string.Join(",", x.Value)),
                (Object) this.Configuration.ApiClient.Deserialize(localVarResponse, typeof(Object)));
        }

        /// <summary>
        /// Retrieve the latest trades that have occurred for instruments in a specific currency symbol and within given time range. 
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
        public async System.Threading.Tasks.Task<Object> PublicGetLastTradesByCurrencyAndTimeGetAsync (string currency, int? startTimestamp, int? endTimestamp, string kind = null, int? count = null, bool? includeOld = null, string sorting = null)
        {
             ApiResponse<Object> localVarResponse = await PublicGetLastTradesByCurrencyAndTimeGetAsyncWithHttpInfo(currency, startTimestamp, endTimestamp, kind, count, includeOld, sorting);
             return localVarResponse.Data;

        }

        /// <summary>
        /// Retrieve the latest trades that have occurred for instruments in a specific currency symbol and within given time range. 
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
        public async System.Threading.Tasks.Task<ApiResponse<Object>> PublicGetLastTradesByCurrencyAndTimeGetAsyncWithHttpInfo (string currency, int? startTimestamp, int? endTimestamp, string kind = null, int? count = null, bool? includeOld = null, string sorting = null)
        {
            // verify the required parameter 'currency' is set
            if (currency == null)
                throw new ApiException(400, "Missing required parameter 'currency' when calling PublicApi->PublicGetLastTradesByCurrencyAndTimeGet");
            // verify the required parameter 'startTimestamp' is set
            if (startTimestamp == null)
                throw new ApiException(400, "Missing required parameter 'startTimestamp' when calling PublicApi->PublicGetLastTradesByCurrencyAndTimeGet");
            // verify the required parameter 'endTimestamp' is set
            if (endTimestamp == null)
                throw new ApiException(400, "Missing required parameter 'endTimestamp' when calling PublicApi->PublicGetLastTradesByCurrencyAndTimeGet");

            var localVarPath = "/public/get_last_trades_by_currency_and_time";
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
                Exception exception = ExceptionFactory("PublicGetLastTradesByCurrencyAndTimeGet", localVarResponse);
                if (exception != null) throw exception;
            }

            return new ApiResponse<Object>(localVarStatusCode,
                localVarResponse.Headers.ToDictionary(x => x.Name, x => string.Join(",", x.Value)),
                (Object) this.Configuration.ApiClient.Deserialize(localVarResponse, typeof(Object)));
        }

        /// <summary>
        /// Retrieve the latest trades that have occurred for instruments in a specific currency symbol. 
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
        public Object PublicGetLastTradesByCurrencyGet (string currency, string kind = null, string startId = null, string endId = null, int? count = null, bool? includeOld = null, string sorting = null)
        {
             ApiResponse<Object> localVarResponse = PublicGetLastTradesByCurrencyGetWithHttpInfo(currency, kind, startId, endId, count, includeOld, sorting);
             return localVarResponse.Data;
        }

        /// <summary>
        /// Retrieve the latest trades that have occurred for instruments in a specific currency symbol. 
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
        public ApiResponse< Object > PublicGetLastTradesByCurrencyGetWithHttpInfo (string currency, string kind = null, string startId = null, string endId = null, int? count = null, bool? includeOld = null, string sorting = null)
        {
            // verify the required parameter 'currency' is set
            if (currency == null)
                throw new ApiException(400, "Missing required parameter 'currency' when calling PublicApi->PublicGetLastTradesByCurrencyGet");

            var localVarPath = "/public/get_last_trades_by_currency";
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
                Exception exception = ExceptionFactory("PublicGetLastTradesByCurrencyGet", localVarResponse);
                if (exception != null) throw exception;
            }

            return new ApiResponse<Object>(localVarStatusCode,
                localVarResponse.Headers.ToDictionary(x => x.Name, x => string.Join(",", x.Value)),
                (Object) this.Configuration.ApiClient.Deserialize(localVarResponse, typeof(Object)));
        }

        /// <summary>
        /// Retrieve the latest trades that have occurred for instruments in a specific currency symbol. 
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
        public async System.Threading.Tasks.Task<Object> PublicGetLastTradesByCurrencyGetAsync (string currency, string kind = null, string startId = null, string endId = null, int? count = null, bool? includeOld = null, string sorting = null)
        {
             ApiResponse<Object> localVarResponse = await PublicGetLastTradesByCurrencyGetAsyncWithHttpInfo(currency, kind, startId, endId, count, includeOld, sorting);
             return localVarResponse.Data;

        }

        /// <summary>
        /// Retrieve the latest trades that have occurred for instruments in a specific currency symbol. 
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
        public async System.Threading.Tasks.Task<ApiResponse<Object>> PublicGetLastTradesByCurrencyGetAsyncWithHttpInfo (string currency, string kind = null, string startId = null, string endId = null, int? count = null, bool? includeOld = null, string sorting = null)
        {
            // verify the required parameter 'currency' is set
            if (currency == null)
                throw new ApiException(400, "Missing required parameter 'currency' when calling PublicApi->PublicGetLastTradesByCurrencyGet");

            var localVarPath = "/public/get_last_trades_by_currency";
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
                Exception exception = ExceptionFactory("PublicGetLastTradesByCurrencyGet", localVarResponse);
                if (exception != null) throw exception;
            }

            return new ApiResponse<Object>(localVarStatusCode,
                localVarResponse.Headers.ToDictionary(x => x.Name, x => string.Join(",", x.Value)),
                (Object) this.Configuration.ApiClient.Deserialize(localVarResponse, typeof(Object)));
        }

        /// <summary>
        /// Retrieve the latest trades that have occurred for a specific instrument and within given time range. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">Instrument name</param>
        /// <param name="startTimestamp">The earliest timestamp to return result for</param>
        /// <param name="endTimestamp">The most recent timestamp to return result for</param>
        /// <param name="count">Number of requested items, default - &#x60;10&#x60; (optional)</param>
        /// <param name="includeOld">Include trades older than 7 days, default - &#x60;false&#x60; (optional)</param>
        /// <param name="sorting">Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) (optional)</param>
        /// <returns>Object</returns>
        public Object PublicGetLastTradesByInstrumentAndTimeGet (string instrumentName, int? startTimestamp, int? endTimestamp, int? count = null, bool? includeOld = null, string sorting = null)
        {
             ApiResponse<Object> localVarResponse = PublicGetLastTradesByInstrumentAndTimeGetWithHttpInfo(instrumentName, startTimestamp, endTimestamp, count, includeOld, sorting);
             return localVarResponse.Data;
        }

        /// <summary>
        /// Retrieve the latest trades that have occurred for a specific instrument and within given time range. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">Instrument name</param>
        /// <param name="startTimestamp">The earliest timestamp to return result for</param>
        /// <param name="endTimestamp">The most recent timestamp to return result for</param>
        /// <param name="count">Number of requested items, default - &#x60;10&#x60; (optional)</param>
        /// <param name="includeOld">Include trades older than 7 days, default - &#x60;false&#x60; (optional)</param>
        /// <param name="sorting">Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) (optional)</param>
        /// <returns>ApiResponse of Object</returns>
        public ApiResponse< Object > PublicGetLastTradesByInstrumentAndTimeGetWithHttpInfo (string instrumentName, int? startTimestamp, int? endTimestamp, int? count = null, bool? includeOld = null, string sorting = null)
        {
            // verify the required parameter 'instrumentName' is set
            if (instrumentName == null)
                throw new ApiException(400, "Missing required parameter 'instrumentName' when calling PublicApi->PublicGetLastTradesByInstrumentAndTimeGet");
            // verify the required parameter 'startTimestamp' is set
            if (startTimestamp == null)
                throw new ApiException(400, "Missing required parameter 'startTimestamp' when calling PublicApi->PublicGetLastTradesByInstrumentAndTimeGet");
            // verify the required parameter 'endTimestamp' is set
            if (endTimestamp == null)
                throw new ApiException(400, "Missing required parameter 'endTimestamp' when calling PublicApi->PublicGetLastTradesByInstrumentAndTimeGet");

            var localVarPath = "/public/get_last_trades_by_instrument_and_time";
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
                Exception exception = ExceptionFactory("PublicGetLastTradesByInstrumentAndTimeGet", localVarResponse);
                if (exception != null) throw exception;
            }

            return new ApiResponse<Object>(localVarStatusCode,
                localVarResponse.Headers.ToDictionary(x => x.Name, x => string.Join(",", x.Value)),
                (Object) this.Configuration.ApiClient.Deserialize(localVarResponse, typeof(Object)));
        }

        /// <summary>
        /// Retrieve the latest trades that have occurred for a specific instrument and within given time range. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">Instrument name</param>
        /// <param name="startTimestamp">The earliest timestamp to return result for</param>
        /// <param name="endTimestamp">The most recent timestamp to return result for</param>
        /// <param name="count">Number of requested items, default - &#x60;10&#x60; (optional)</param>
        /// <param name="includeOld">Include trades older than 7 days, default - &#x60;false&#x60; (optional)</param>
        /// <param name="sorting">Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) (optional)</param>
        /// <returns>Task of Object</returns>
        public async System.Threading.Tasks.Task<Object> PublicGetLastTradesByInstrumentAndTimeGetAsync (string instrumentName, int? startTimestamp, int? endTimestamp, int? count = null, bool? includeOld = null, string sorting = null)
        {
             ApiResponse<Object> localVarResponse = await PublicGetLastTradesByInstrumentAndTimeGetAsyncWithHttpInfo(instrumentName, startTimestamp, endTimestamp, count, includeOld, sorting);
             return localVarResponse.Data;

        }

        /// <summary>
        /// Retrieve the latest trades that have occurred for a specific instrument and within given time range. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">Instrument name</param>
        /// <param name="startTimestamp">The earliest timestamp to return result for</param>
        /// <param name="endTimestamp">The most recent timestamp to return result for</param>
        /// <param name="count">Number of requested items, default - &#x60;10&#x60; (optional)</param>
        /// <param name="includeOld">Include trades older than 7 days, default - &#x60;false&#x60; (optional)</param>
        /// <param name="sorting">Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) (optional)</param>
        /// <returns>Task of ApiResponse (Object)</returns>
        public async System.Threading.Tasks.Task<ApiResponse<Object>> PublicGetLastTradesByInstrumentAndTimeGetAsyncWithHttpInfo (string instrumentName, int? startTimestamp, int? endTimestamp, int? count = null, bool? includeOld = null, string sorting = null)
        {
            // verify the required parameter 'instrumentName' is set
            if (instrumentName == null)
                throw new ApiException(400, "Missing required parameter 'instrumentName' when calling PublicApi->PublicGetLastTradesByInstrumentAndTimeGet");
            // verify the required parameter 'startTimestamp' is set
            if (startTimestamp == null)
                throw new ApiException(400, "Missing required parameter 'startTimestamp' when calling PublicApi->PublicGetLastTradesByInstrumentAndTimeGet");
            // verify the required parameter 'endTimestamp' is set
            if (endTimestamp == null)
                throw new ApiException(400, "Missing required parameter 'endTimestamp' when calling PublicApi->PublicGetLastTradesByInstrumentAndTimeGet");

            var localVarPath = "/public/get_last_trades_by_instrument_and_time";
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
                Exception exception = ExceptionFactory("PublicGetLastTradesByInstrumentAndTimeGet", localVarResponse);
                if (exception != null) throw exception;
            }

            return new ApiResponse<Object>(localVarStatusCode,
                localVarResponse.Headers.ToDictionary(x => x.Name, x => string.Join(",", x.Value)),
                (Object) this.Configuration.ApiClient.Deserialize(localVarResponse, typeof(Object)));
        }

        /// <summary>
        /// Retrieve the latest trades that have occurred for a specific instrument. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">Instrument name</param>
        /// <param name="startSeq">The sequence number of the first trade to be returned (optional)</param>
        /// <param name="endSeq">The sequence number of the last trade to be returned (optional)</param>
        /// <param name="count">Number of requested items, default - &#x60;10&#x60; (optional)</param>
        /// <param name="includeOld">Include trades older than 7 days, default - &#x60;false&#x60; (optional)</param>
        /// <param name="sorting">Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) (optional)</param>
        /// <returns>Object</returns>
        public Object PublicGetLastTradesByInstrumentGet (string instrumentName, int? startSeq = null, int? endSeq = null, int? count = null, bool? includeOld = null, string sorting = null)
        {
             ApiResponse<Object> localVarResponse = PublicGetLastTradesByInstrumentGetWithHttpInfo(instrumentName, startSeq, endSeq, count, includeOld, sorting);
             return localVarResponse.Data;
        }

        /// <summary>
        /// Retrieve the latest trades that have occurred for a specific instrument. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">Instrument name</param>
        /// <param name="startSeq">The sequence number of the first trade to be returned (optional)</param>
        /// <param name="endSeq">The sequence number of the last trade to be returned (optional)</param>
        /// <param name="count">Number of requested items, default - &#x60;10&#x60; (optional)</param>
        /// <param name="includeOld">Include trades older than 7 days, default - &#x60;false&#x60; (optional)</param>
        /// <param name="sorting">Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) (optional)</param>
        /// <returns>ApiResponse of Object</returns>
        public ApiResponse< Object > PublicGetLastTradesByInstrumentGetWithHttpInfo (string instrumentName, int? startSeq = null, int? endSeq = null, int? count = null, bool? includeOld = null, string sorting = null)
        {
            // verify the required parameter 'instrumentName' is set
            if (instrumentName == null)
                throw new ApiException(400, "Missing required parameter 'instrumentName' when calling PublicApi->PublicGetLastTradesByInstrumentGet");

            var localVarPath = "/public/get_last_trades_by_instrument";
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
                Exception exception = ExceptionFactory("PublicGetLastTradesByInstrumentGet", localVarResponse);
                if (exception != null) throw exception;
            }

            return new ApiResponse<Object>(localVarStatusCode,
                localVarResponse.Headers.ToDictionary(x => x.Name, x => string.Join(",", x.Value)),
                (Object) this.Configuration.ApiClient.Deserialize(localVarResponse, typeof(Object)));
        }

        /// <summary>
        /// Retrieve the latest trades that have occurred for a specific instrument. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">Instrument name</param>
        /// <param name="startSeq">The sequence number of the first trade to be returned (optional)</param>
        /// <param name="endSeq">The sequence number of the last trade to be returned (optional)</param>
        /// <param name="count">Number of requested items, default - &#x60;10&#x60; (optional)</param>
        /// <param name="includeOld">Include trades older than 7 days, default - &#x60;false&#x60; (optional)</param>
        /// <param name="sorting">Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) (optional)</param>
        /// <returns>Task of Object</returns>
        public async System.Threading.Tasks.Task<Object> PublicGetLastTradesByInstrumentGetAsync (string instrumentName, int? startSeq = null, int? endSeq = null, int? count = null, bool? includeOld = null, string sorting = null)
        {
             ApiResponse<Object> localVarResponse = await PublicGetLastTradesByInstrumentGetAsyncWithHttpInfo(instrumentName, startSeq, endSeq, count, includeOld, sorting);
             return localVarResponse.Data;

        }

        /// <summary>
        /// Retrieve the latest trades that have occurred for a specific instrument. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">Instrument name</param>
        /// <param name="startSeq">The sequence number of the first trade to be returned (optional)</param>
        /// <param name="endSeq">The sequence number of the last trade to be returned (optional)</param>
        /// <param name="count">Number of requested items, default - &#x60;10&#x60; (optional)</param>
        /// <param name="includeOld">Include trades older than 7 days, default - &#x60;false&#x60; (optional)</param>
        /// <param name="sorting">Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) (optional)</param>
        /// <returns>Task of ApiResponse (Object)</returns>
        public async System.Threading.Tasks.Task<ApiResponse<Object>> PublicGetLastTradesByInstrumentGetAsyncWithHttpInfo (string instrumentName, int? startSeq = null, int? endSeq = null, int? count = null, bool? includeOld = null, string sorting = null)
        {
            // verify the required parameter 'instrumentName' is set
            if (instrumentName == null)
                throw new ApiException(400, "Missing required parameter 'instrumentName' when calling PublicApi->PublicGetLastTradesByInstrumentGet");

            var localVarPath = "/public/get_last_trades_by_instrument";
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
                Exception exception = ExceptionFactory("PublicGetLastTradesByInstrumentGet", localVarResponse);
                if (exception != null) throw exception;
            }

            return new ApiResponse<Object>(localVarStatusCode,
                localVarResponse.Headers.ToDictionary(x => x.Name, x => string.Join(",", x.Value)),
                (Object) this.Configuration.ApiClient.Deserialize(localVarResponse, typeof(Object)));
        }

        /// <summary>
        /// Retrieves the order book, along with other market values for a given instrument. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">The instrument name for which to retrieve the order book, see [&#x60;getinstruments&#x60;](#getinstruments) to obtain instrument names.</param>
        /// <param name="depth">The number of entries to return for bids and asks. (optional)</param>
        /// <returns>Object</returns>
        public Object PublicGetOrderBookGet (string instrumentName, decimal? depth = null)
        {
             ApiResponse<Object> localVarResponse = PublicGetOrderBookGetWithHttpInfo(instrumentName, depth);
             return localVarResponse.Data;
        }

        /// <summary>
        /// Retrieves the order book, along with other market values for a given instrument. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">The instrument name for which to retrieve the order book, see [&#x60;getinstruments&#x60;](#getinstruments) to obtain instrument names.</param>
        /// <param name="depth">The number of entries to return for bids and asks. (optional)</param>
        /// <returns>ApiResponse of Object</returns>
        public ApiResponse< Object > PublicGetOrderBookGetWithHttpInfo (string instrumentName, decimal? depth = null)
        {
            // verify the required parameter 'instrumentName' is set
            if (instrumentName == null)
                throw new ApiException(400, "Missing required parameter 'instrumentName' when calling PublicApi->PublicGetOrderBookGet");

            var localVarPath = "/public/get_order_book";
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
            if (depth != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "depth", depth)); // query parameter

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
                Exception exception = ExceptionFactory("PublicGetOrderBookGet", localVarResponse);
                if (exception != null) throw exception;
            }

            return new ApiResponse<Object>(localVarStatusCode,
                localVarResponse.Headers.ToDictionary(x => x.Name, x => string.Join(",", x.Value)),
                (Object) this.Configuration.ApiClient.Deserialize(localVarResponse, typeof(Object)));
        }

        /// <summary>
        /// Retrieves the order book, along with other market values for a given instrument. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">The instrument name for which to retrieve the order book, see [&#x60;getinstruments&#x60;](#getinstruments) to obtain instrument names.</param>
        /// <param name="depth">The number of entries to return for bids and asks. (optional)</param>
        /// <returns>Task of Object</returns>
        public async System.Threading.Tasks.Task<Object> PublicGetOrderBookGetAsync (string instrumentName, decimal? depth = null)
        {
             ApiResponse<Object> localVarResponse = await PublicGetOrderBookGetAsyncWithHttpInfo(instrumentName, depth);
             return localVarResponse.Data;

        }

        /// <summary>
        /// Retrieves the order book, along with other market values for a given instrument. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">The instrument name for which to retrieve the order book, see [&#x60;getinstruments&#x60;](#getinstruments) to obtain instrument names.</param>
        /// <param name="depth">The number of entries to return for bids and asks. (optional)</param>
        /// <returns>Task of ApiResponse (Object)</returns>
        public async System.Threading.Tasks.Task<ApiResponse<Object>> PublicGetOrderBookGetAsyncWithHttpInfo (string instrumentName, decimal? depth = null)
        {
            // verify the required parameter 'instrumentName' is set
            if (instrumentName == null)
                throw new ApiException(400, "Missing required parameter 'instrumentName' when calling PublicApi->PublicGetOrderBookGet");

            var localVarPath = "/public/get_order_book";
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
            if (depth != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "depth", depth)); // query parameter

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
                Exception exception = ExceptionFactory("PublicGetOrderBookGet", localVarResponse);
                if (exception != null) throw exception;
            }

            return new ApiResponse<Object>(localVarStatusCode,
                localVarResponse.Headers.ToDictionary(x => x.Name, x => string.Join(",", x.Value)),
                (Object) this.Configuration.ApiClient.Deserialize(localVarResponse, typeof(Object)));
        }

        /// <summary>
        /// Retrieves the current time (in milliseconds). This API endpoint can be used to check the clock skew between your software and Deribit&#39;s systems. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <returns>Object</returns>
        public Object PublicGetTimeGet ()
        {
             ApiResponse<Object> localVarResponse = PublicGetTimeGetWithHttpInfo();
             return localVarResponse.Data;
        }

        /// <summary>
        /// Retrieves the current time (in milliseconds). This API endpoint can be used to check the clock skew between your software and Deribit&#39;s systems. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <returns>ApiResponse of Object</returns>
        public ApiResponse< Object > PublicGetTimeGetWithHttpInfo ()
        {

            var localVarPath = "/public/get_time";
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
                Exception exception = ExceptionFactory("PublicGetTimeGet", localVarResponse);
                if (exception != null) throw exception;
            }

            return new ApiResponse<Object>(localVarStatusCode,
                localVarResponse.Headers.ToDictionary(x => x.Name, x => string.Join(",", x.Value)),
                (Object) this.Configuration.ApiClient.Deserialize(localVarResponse, typeof(Object)));
        }

        /// <summary>
        /// Retrieves the current time (in milliseconds). This API endpoint can be used to check the clock skew between your software and Deribit&#39;s systems. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <returns>Task of Object</returns>
        public async System.Threading.Tasks.Task<Object> PublicGetTimeGetAsync ()
        {
             ApiResponse<Object> localVarResponse = await PublicGetTimeGetAsyncWithHttpInfo();
             return localVarResponse.Data;

        }

        /// <summary>
        /// Retrieves the current time (in milliseconds). This API endpoint can be used to check the clock skew between your software and Deribit&#39;s systems. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <returns>Task of ApiResponse (Object)</returns>
        public async System.Threading.Tasks.Task<ApiResponse<Object>> PublicGetTimeGetAsyncWithHttpInfo ()
        {

            var localVarPath = "/public/get_time";
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
                Exception exception = ExceptionFactory("PublicGetTimeGet", localVarResponse);
                if (exception != null) throw exception;
            }

            return new ApiResponse<Object>(localVarStatusCode,
                localVarResponse.Headers.ToDictionary(x => x.Name, x => string.Join(",", x.Value)),
                (Object) this.Configuration.ApiClient.Deserialize(localVarResponse, typeof(Object)));
        }

        /// <summary>
        /// Retrieves aggregated 24h trade volumes for different instrument types and currencies. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <returns>Object</returns>
        public Object PublicGetTradeVolumesGet ()
        {
             ApiResponse<Object> localVarResponse = PublicGetTradeVolumesGetWithHttpInfo();
             return localVarResponse.Data;
        }

        /// <summary>
        /// Retrieves aggregated 24h trade volumes for different instrument types and currencies. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <returns>ApiResponse of Object</returns>
        public ApiResponse< Object > PublicGetTradeVolumesGetWithHttpInfo ()
        {

            var localVarPath = "/public/get_trade_volumes";
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
                Exception exception = ExceptionFactory("PublicGetTradeVolumesGet", localVarResponse);
                if (exception != null) throw exception;
            }

            return new ApiResponse<Object>(localVarStatusCode,
                localVarResponse.Headers.ToDictionary(x => x.Name, x => string.Join(",", x.Value)),
                (Object) this.Configuration.ApiClient.Deserialize(localVarResponse, typeof(Object)));
        }

        /// <summary>
        /// Retrieves aggregated 24h trade volumes for different instrument types and currencies. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <returns>Task of Object</returns>
        public async System.Threading.Tasks.Task<Object> PublicGetTradeVolumesGetAsync ()
        {
             ApiResponse<Object> localVarResponse = await PublicGetTradeVolumesGetAsyncWithHttpInfo();
             return localVarResponse.Data;

        }

        /// <summary>
        /// Retrieves aggregated 24h trade volumes for different instrument types and currencies. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <returns>Task of ApiResponse (Object)</returns>
        public async System.Threading.Tasks.Task<ApiResponse<Object>> PublicGetTradeVolumesGetAsyncWithHttpInfo ()
        {

            var localVarPath = "/public/get_trade_volumes";
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
                Exception exception = ExceptionFactory("PublicGetTradeVolumesGet", localVarResponse);
                if (exception != null) throw exception;
            }

            return new ApiResponse<Object>(localVarStatusCode,
                localVarResponse.Headers.ToDictionary(x => x.Name, x => string.Join(",", x.Value)),
                (Object) this.Configuration.ApiClient.Deserialize(localVarResponse, typeof(Object)));
        }

        /// <summary>
        /// Publicly available market data used to generate a TradingView candle chart. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">Instrument name</param>
        /// <param name="startTimestamp">The earliest timestamp to return result for</param>
        /// <param name="endTimestamp">The most recent timestamp to return result for</param>
        /// <returns>Object</returns>
        public Object PublicGetTradingviewChartDataGet (string instrumentName, int? startTimestamp, int? endTimestamp)
        {
             ApiResponse<Object> localVarResponse = PublicGetTradingviewChartDataGetWithHttpInfo(instrumentName, startTimestamp, endTimestamp);
             return localVarResponse.Data;
        }

        /// <summary>
        /// Publicly available market data used to generate a TradingView candle chart. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">Instrument name</param>
        /// <param name="startTimestamp">The earliest timestamp to return result for</param>
        /// <param name="endTimestamp">The most recent timestamp to return result for</param>
        /// <returns>ApiResponse of Object</returns>
        public ApiResponse< Object > PublicGetTradingviewChartDataGetWithHttpInfo (string instrumentName, int? startTimestamp, int? endTimestamp)
        {
            // verify the required parameter 'instrumentName' is set
            if (instrumentName == null)
                throw new ApiException(400, "Missing required parameter 'instrumentName' when calling PublicApi->PublicGetTradingviewChartDataGet");
            // verify the required parameter 'startTimestamp' is set
            if (startTimestamp == null)
                throw new ApiException(400, "Missing required parameter 'startTimestamp' when calling PublicApi->PublicGetTradingviewChartDataGet");
            // verify the required parameter 'endTimestamp' is set
            if (endTimestamp == null)
                throw new ApiException(400, "Missing required parameter 'endTimestamp' when calling PublicApi->PublicGetTradingviewChartDataGet");

            var localVarPath = "/public/get_tradingview_chart_data";
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
                Exception exception = ExceptionFactory("PublicGetTradingviewChartDataGet", localVarResponse);
                if (exception != null) throw exception;
            }

            return new ApiResponse<Object>(localVarStatusCode,
                localVarResponse.Headers.ToDictionary(x => x.Name, x => string.Join(",", x.Value)),
                (Object) this.Configuration.ApiClient.Deserialize(localVarResponse, typeof(Object)));
        }

        /// <summary>
        /// Publicly available market data used to generate a TradingView candle chart. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">Instrument name</param>
        /// <param name="startTimestamp">The earliest timestamp to return result for</param>
        /// <param name="endTimestamp">The most recent timestamp to return result for</param>
        /// <returns>Task of Object</returns>
        public async System.Threading.Tasks.Task<Object> PublicGetTradingviewChartDataGetAsync (string instrumentName, int? startTimestamp, int? endTimestamp)
        {
             ApiResponse<Object> localVarResponse = await PublicGetTradingviewChartDataGetAsyncWithHttpInfo(instrumentName, startTimestamp, endTimestamp);
             return localVarResponse.Data;

        }

        /// <summary>
        /// Publicly available market data used to generate a TradingView candle chart. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">Instrument name</param>
        /// <param name="startTimestamp">The earliest timestamp to return result for</param>
        /// <param name="endTimestamp">The most recent timestamp to return result for</param>
        /// <returns>Task of ApiResponse (Object)</returns>
        public async System.Threading.Tasks.Task<ApiResponse<Object>> PublicGetTradingviewChartDataGetAsyncWithHttpInfo (string instrumentName, int? startTimestamp, int? endTimestamp)
        {
            // verify the required parameter 'instrumentName' is set
            if (instrumentName == null)
                throw new ApiException(400, "Missing required parameter 'instrumentName' when calling PublicApi->PublicGetTradingviewChartDataGet");
            // verify the required parameter 'startTimestamp' is set
            if (startTimestamp == null)
                throw new ApiException(400, "Missing required parameter 'startTimestamp' when calling PublicApi->PublicGetTradingviewChartDataGet");
            // verify the required parameter 'endTimestamp' is set
            if (endTimestamp == null)
                throw new ApiException(400, "Missing required parameter 'endTimestamp' when calling PublicApi->PublicGetTradingviewChartDataGet");

            var localVarPath = "/public/get_tradingview_chart_data";
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
                Exception exception = ExceptionFactory("PublicGetTradingviewChartDataGet", localVarResponse);
                if (exception != null) throw exception;
            }

            return new ApiResponse<Object>(localVarStatusCode,
                localVarResponse.Headers.ToDictionary(x => x.Name, x => string.Join(",", x.Value)),
                (Object) this.Configuration.ApiClient.Deserialize(localVarResponse, typeof(Object)));
        }

        /// <summary>
        /// Tests the connection to the API server, and returns its version. You can use this to make sure the API is reachable, and matches the expected version. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="expectedResult">The value \&quot;exception\&quot; will trigger an error response. This may be useful for testing wrapper libraries. (optional)</param>
        /// <returns>Object</returns>
        public Object PublicTestGet (string expectedResult = null)
        {
             ApiResponse<Object> localVarResponse = PublicTestGetWithHttpInfo(expectedResult);
             return localVarResponse.Data;
        }

        /// <summary>
        /// Tests the connection to the API server, and returns its version. You can use this to make sure the API is reachable, and matches the expected version. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="expectedResult">The value \&quot;exception\&quot; will trigger an error response. This may be useful for testing wrapper libraries. (optional)</param>
        /// <returns>ApiResponse of Object</returns>
        public ApiResponse< Object > PublicTestGetWithHttpInfo (string expectedResult = null)
        {

            var localVarPath = "/public/test";
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

            if (expectedResult != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "expected_result", expectedResult)); // query parameter

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
                Exception exception = ExceptionFactory("PublicTestGet", localVarResponse);
                if (exception != null) throw exception;
            }

            return new ApiResponse<Object>(localVarStatusCode,
                localVarResponse.Headers.ToDictionary(x => x.Name, x => string.Join(",", x.Value)),
                (Object) this.Configuration.ApiClient.Deserialize(localVarResponse, typeof(Object)));
        }

        /// <summary>
        /// Tests the connection to the API server, and returns its version. You can use this to make sure the API is reachable, and matches the expected version. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="expectedResult">The value \&quot;exception\&quot; will trigger an error response. This may be useful for testing wrapper libraries. (optional)</param>
        /// <returns>Task of Object</returns>
        public async System.Threading.Tasks.Task<Object> PublicTestGetAsync (string expectedResult = null)
        {
             ApiResponse<Object> localVarResponse = await PublicTestGetAsyncWithHttpInfo(expectedResult);
             return localVarResponse.Data;

        }

        /// <summary>
        /// Tests the connection to the API server, and returns its version. You can use this to make sure the API is reachable, and matches the expected version. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="expectedResult">The value \&quot;exception\&quot; will trigger an error response. This may be useful for testing wrapper libraries. (optional)</param>
        /// <returns>Task of ApiResponse (Object)</returns>
        public async System.Threading.Tasks.Task<ApiResponse<Object>> PublicTestGetAsyncWithHttpInfo (string expectedResult = null)
        {

            var localVarPath = "/public/test";
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

            if (expectedResult != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "expected_result", expectedResult)); // query parameter

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
                Exception exception = ExceptionFactory("PublicTestGet", localVarResponse);
                if (exception != null) throw exception;
            }

            return new ApiResponse<Object>(localVarStatusCode,
                localVarResponse.Headers.ToDictionary(x => x.Name, x => string.Join(",", x.Value)),
                (Object) this.Configuration.ApiClient.Deserialize(localVarResponse, typeof(Object)));
        }

        /// <summary>
        /// Get ticker for an instrument. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">Instrument name</param>
        /// <returns>Object</returns>
        public Object PublicTickerGet (string instrumentName)
        {
             ApiResponse<Object> localVarResponse = PublicTickerGetWithHttpInfo(instrumentName);
             return localVarResponse.Data;
        }

        /// <summary>
        /// Get ticker for an instrument. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">Instrument name</param>
        /// <returns>ApiResponse of Object</returns>
        public ApiResponse< Object > PublicTickerGetWithHttpInfo (string instrumentName)
        {
            // verify the required parameter 'instrumentName' is set
            if (instrumentName == null)
                throw new ApiException(400, "Missing required parameter 'instrumentName' when calling PublicApi->PublicTickerGet");

            var localVarPath = "/public/ticker";
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
                Exception exception = ExceptionFactory("PublicTickerGet", localVarResponse);
                if (exception != null) throw exception;
            }

            return new ApiResponse<Object>(localVarStatusCode,
                localVarResponse.Headers.ToDictionary(x => x.Name, x => string.Join(",", x.Value)),
                (Object) this.Configuration.ApiClient.Deserialize(localVarResponse, typeof(Object)));
        }

        /// <summary>
        /// Get ticker for an instrument. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">Instrument name</param>
        /// <returns>Task of Object</returns>
        public async System.Threading.Tasks.Task<Object> PublicTickerGetAsync (string instrumentName)
        {
             ApiResponse<Object> localVarResponse = await PublicTickerGetAsyncWithHttpInfo(instrumentName);
             return localVarResponse.Data;

        }

        /// <summary>
        /// Get ticker for an instrument. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="instrumentName">Instrument name</param>
        /// <returns>Task of ApiResponse (Object)</returns>
        public async System.Threading.Tasks.Task<ApiResponse<Object>> PublicTickerGetAsyncWithHttpInfo (string instrumentName)
        {
            // verify the required parameter 'instrumentName' is set
            if (instrumentName == null)
                throw new ApiException(400, "Missing required parameter 'instrumentName' when calling PublicApi->PublicTickerGet");

            var localVarPath = "/public/ticker";
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
                Exception exception = ExceptionFactory("PublicTickerGet", localVarResponse);
                if (exception != null) throw exception;
            }

            return new ApiResponse<Object>(localVarStatusCode,
                localVarResponse.Headers.ToDictionary(x => x.Name, x => string.Join(",", x.Value)),
                (Object) this.Configuration.ApiClient.Deserialize(localVarResponse, typeof(Object)));
        }

        /// <summary>
        /// Method used to introduce the client software connected to Deribit platform over websocket. Provided data may have an impact on the maintained connection and will be collected for internal statistical purposes. In response, Deribit will also introduce itself. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="field">Name of the field to be validated, examples: postal_code, date_of_birth</param>
        /// <param name="value">Value to be checked</param>
        /// <param name="value2">Additional value to be compared with (optional)</param>
        /// <returns>Object</returns>
        public Object PublicValidateFieldGet (string field, string value, string value2 = null)
        {
             ApiResponse<Object> localVarResponse = PublicValidateFieldGetWithHttpInfo(field, value, value2);
             return localVarResponse.Data;
        }

        /// <summary>
        /// Method used to introduce the client software connected to Deribit platform over websocket. Provided data may have an impact on the maintained connection and will be collected for internal statistical purposes. In response, Deribit will also introduce itself. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="field">Name of the field to be validated, examples: postal_code, date_of_birth</param>
        /// <param name="value">Value to be checked</param>
        /// <param name="value2">Additional value to be compared with (optional)</param>
        /// <returns>ApiResponse of Object</returns>
        public ApiResponse< Object > PublicValidateFieldGetWithHttpInfo (string field, string value, string value2 = null)
        {
            // verify the required parameter 'field' is set
            if (field == null)
                throw new ApiException(400, "Missing required parameter 'field' when calling PublicApi->PublicValidateFieldGet");
            // verify the required parameter 'value' is set
            if (value == null)
                throw new ApiException(400, "Missing required parameter 'value' when calling PublicApi->PublicValidateFieldGet");

            var localVarPath = "/public/validate_field";
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

            if (field != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "field", field)); // query parameter
            if (value != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "value", value)); // query parameter
            if (value2 != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "value2", value2)); // query parameter

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
                Exception exception = ExceptionFactory("PublicValidateFieldGet", localVarResponse);
                if (exception != null) throw exception;
            }

            return new ApiResponse<Object>(localVarStatusCode,
                localVarResponse.Headers.ToDictionary(x => x.Name, x => string.Join(",", x.Value)),
                (Object) this.Configuration.ApiClient.Deserialize(localVarResponse, typeof(Object)));
        }

        /// <summary>
        /// Method used to introduce the client software connected to Deribit platform over websocket. Provided data may have an impact on the maintained connection and will be collected for internal statistical purposes. In response, Deribit will also introduce itself. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="field">Name of the field to be validated, examples: postal_code, date_of_birth</param>
        /// <param name="value">Value to be checked</param>
        /// <param name="value2">Additional value to be compared with (optional)</param>
        /// <returns>Task of Object</returns>
        public async System.Threading.Tasks.Task<Object> PublicValidateFieldGetAsync (string field, string value, string value2 = null)
        {
             ApiResponse<Object> localVarResponse = await PublicValidateFieldGetAsyncWithHttpInfo(field, value, value2);
             return localVarResponse.Data;

        }

        /// <summary>
        /// Method used to introduce the client software connected to Deribit platform over websocket. Provided data may have an impact on the maintained connection and will be collected for internal statistical purposes. In response, Deribit will also introduce itself. 
        /// </summary>
        /// <exception cref="Org.OpenAPITools.Client.ApiException">Thrown when fails to make API call</exception>
        /// <param name="field">Name of the field to be validated, examples: postal_code, date_of_birth</param>
        /// <param name="value">Value to be checked</param>
        /// <param name="value2">Additional value to be compared with (optional)</param>
        /// <returns>Task of ApiResponse (Object)</returns>
        public async System.Threading.Tasks.Task<ApiResponse<Object>> PublicValidateFieldGetAsyncWithHttpInfo (string field, string value, string value2 = null)
        {
            // verify the required parameter 'field' is set
            if (field == null)
                throw new ApiException(400, "Missing required parameter 'field' when calling PublicApi->PublicValidateFieldGet");
            // verify the required parameter 'value' is set
            if (value == null)
                throw new ApiException(400, "Missing required parameter 'value' when calling PublicApi->PublicValidateFieldGet");

            var localVarPath = "/public/validate_field";
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

            if (field != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "field", field)); // query parameter
            if (value != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "value", value)); // query parameter
            if (value2 != null) localVarQueryParams.AddRange(this.Configuration.ApiClient.ParameterToKeyValuePairs("", "value2", value2)); // query parameter

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
                Exception exception = ExceptionFactory("PublicValidateFieldGet", localVarResponse);
                if (exception != null) throw exception;
            }

            return new ApiResponse<Object>(localVarStatusCode,
                localVarResponse.Headers.ToDictionary(x => x.Name, x => string.Join(",", x.Value)),
                (Object) this.Configuration.ApiClient.Deserialize(localVarResponse, typeof(Object)));
        }

    }
}
