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
using System.Linq;
using System.IO;
using System.Text;
using System.Text.RegularExpressions;
using System.Collections;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Runtime.Serialization;
using Newtonsoft.Json;
using Newtonsoft.Json.Converters;
using System.ComponentModel.DataAnnotations;
using OpenAPIDateConverter = Org.OpenAPITools.Client.OpenAPIDateConverter;

namespace Org.OpenAPITools.Model
{
    /// <summary>
    /// Order
    /// </summary>
    [DataContract]
    public partial class Order :  IEquatable<Order>, IValidatableObject
    {
        /// <summary>
        /// direction, &#x60;buy&#x60; or &#x60;sell&#x60;
        /// </summary>
        /// <value>direction, &#x60;buy&#x60; or &#x60;sell&#x60;</value>
        [JsonConverter(typeof(StringEnumConverter))]
        public enum DirectionEnum
        {
            /// <summary>
            /// Enum Buy for value: buy
            /// </summary>
            [EnumMember(Value = "buy")]
            Buy = 1,

            /// <summary>
            /// Enum Sell for value: sell
            /// </summary>
            [EnumMember(Value = "sell")]
            Sell = 2

        }

        /// <summary>
        /// direction, &#x60;buy&#x60; or &#x60;sell&#x60;
        /// </summary>
        /// <value>direction, &#x60;buy&#x60; or &#x60;sell&#x60;</value>
        [DataMember(Name="direction", EmitDefaultValue=false)]
        public DirectionEnum Direction { get; set; }
        /// <summary>
        /// Order time in force: &#x60;\&quot;good_til_cancelled\&quot;&#x60;, &#x60;\&quot;fill_or_kill\&quot;&#x60;, &#x60;\&quot;immediate_or_cancel\&quot;&#x60;
        /// </summary>
        /// <value>Order time in force: &#x60;\&quot;good_til_cancelled\&quot;&#x60;, &#x60;\&quot;fill_or_kill\&quot;&#x60;, &#x60;\&quot;immediate_or_cancel\&quot;&#x60;</value>
        [JsonConverter(typeof(StringEnumConverter))]
        public enum TimeInForceEnum
        {
            /// <summary>
            /// Enum Goodtilcancelled for value: good_til_cancelled
            /// </summary>
            [EnumMember(Value = "good_til_cancelled")]
            Goodtilcancelled = 1,

            /// <summary>
            /// Enum Fillorkill for value: fill_or_kill
            /// </summary>
            [EnumMember(Value = "fill_or_kill")]
            Fillorkill = 2,

            /// <summary>
            /// Enum Immediateorcancel for value: immediate_or_cancel
            /// </summary>
            [EnumMember(Value = "immediate_or_cancel")]
            Immediateorcancel = 3

        }

        /// <summary>
        /// Order time in force: &#x60;\&quot;good_til_cancelled\&quot;&#x60;, &#x60;\&quot;fill_or_kill\&quot;&#x60;, &#x60;\&quot;immediate_or_cancel\&quot;&#x60;
        /// </summary>
        /// <value>Order time in force: &#x60;\&quot;good_til_cancelled\&quot;&#x60;, &#x60;\&quot;fill_or_kill\&quot;&#x60;, &#x60;\&quot;immediate_or_cancel\&quot;&#x60;</value>
        [DataMember(Name="time_in_force", EmitDefaultValue=false)]
        public TimeInForceEnum TimeInForce { get; set; }
        /// <summary>
        /// order state, &#x60;\&quot;open\&quot;&#x60;, &#x60;\&quot;filled\&quot;&#x60;, &#x60;\&quot;rejected\&quot;&#x60;, &#x60;\&quot;cancelled\&quot;&#x60;, &#x60;\&quot;untriggered\&quot;&#x60;
        /// </summary>
        /// <value>order state, &#x60;\&quot;open\&quot;&#x60;, &#x60;\&quot;filled\&quot;&#x60;, &#x60;\&quot;rejected\&quot;&#x60;, &#x60;\&quot;cancelled\&quot;&#x60;, &#x60;\&quot;untriggered\&quot;&#x60;</value>
        [JsonConverter(typeof(StringEnumConverter))]
        public enum OrderStateEnum
        {
            /// <summary>
            /// Enum Open for value: open
            /// </summary>
            [EnumMember(Value = "open")]
            Open = 1,

            /// <summary>
            /// Enum Filled for value: filled
            /// </summary>
            [EnumMember(Value = "filled")]
            Filled = 2,

            /// <summary>
            /// Enum Rejected for value: rejected
            /// </summary>
            [EnumMember(Value = "rejected")]
            Rejected = 3,

            /// <summary>
            /// Enum Cancelled for value: cancelled
            /// </summary>
            [EnumMember(Value = "cancelled")]
            Cancelled = 4,

            /// <summary>
            /// Enum Untriggered for value: untriggered
            /// </summary>
            [EnumMember(Value = "untriggered")]
            Untriggered = 5,

            /// <summary>
            /// Enum Triggered for value: triggered
            /// </summary>
            [EnumMember(Value = "triggered")]
            Triggered = 6

        }

        /// <summary>
        /// order state, &#x60;\&quot;open\&quot;&#x60;, &#x60;\&quot;filled\&quot;&#x60;, &#x60;\&quot;rejected\&quot;&#x60;, &#x60;\&quot;cancelled\&quot;&#x60;, &#x60;\&quot;untriggered\&quot;&#x60;
        /// </summary>
        /// <value>order state, &#x60;\&quot;open\&quot;&#x60;, &#x60;\&quot;filled\&quot;&#x60;, &#x60;\&quot;rejected\&quot;&#x60;, &#x60;\&quot;cancelled\&quot;&#x60;, &#x60;\&quot;untriggered\&quot;&#x60;</value>
        [DataMember(Name="order_state", EmitDefaultValue=false)]
        public OrderStateEnum OrderState { get; set; }
        /// <summary>
        /// advanced type: &#x60;\&quot;usd\&quot;&#x60; or &#x60;\&quot;implv\&quot;&#x60; (Only for options; field is omitted if not applicable). 
        /// </summary>
        /// <value>advanced type: &#x60;\&quot;usd\&quot;&#x60; or &#x60;\&quot;implv\&quot;&#x60; (Only for options; field is omitted if not applicable). </value>
        [JsonConverter(typeof(StringEnumConverter))]
        public enum AdvancedEnum
        {
            /// <summary>
            /// Enum Usd for value: usd
            /// </summary>
            [EnumMember(Value = "usd")]
            Usd = 1,

            /// <summary>
            /// Enum Implv for value: implv
            /// </summary>
            [EnumMember(Value = "implv")]
            Implv = 2

        }

        /// <summary>
        /// advanced type: &#x60;\&quot;usd\&quot;&#x60; or &#x60;\&quot;implv\&quot;&#x60; (Only for options; field is omitted if not applicable). 
        /// </summary>
        /// <value>advanced type: &#x60;\&quot;usd\&quot;&#x60; or &#x60;\&quot;implv\&quot;&#x60; (Only for options; field is omitted if not applicable). </value>
        [DataMember(Name="advanced", EmitDefaultValue=false)]
        public AdvancedEnum? Advanced { get; set; }
        /// <summary>
        /// order type, &#x60;\&quot;limit\&quot;&#x60;, &#x60;\&quot;market\&quot;&#x60;, &#x60;\&quot;stop_limit\&quot;&#x60;, &#x60;\&quot;stop_market\&quot;&#x60;
        /// </summary>
        /// <value>order type, &#x60;\&quot;limit\&quot;&#x60;, &#x60;\&quot;market\&quot;&#x60;, &#x60;\&quot;stop_limit\&quot;&#x60;, &#x60;\&quot;stop_market\&quot;&#x60;</value>
        [JsonConverter(typeof(StringEnumConverter))]
        public enum OrderTypeEnum
        {
            /// <summary>
            /// Enum Market for value: market
            /// </summary>
            [EnumMember(Value = "market")]
            Market = 1,

            /// <summary>
            /// Enum Limit for value: limit
            /// </summary>
            [EnumMember(Value = "limit")]
            Limit = 2,

            /// <summary>
            /// Enum Stopmarket for value: stop_market
            /// </summary>
            [EnumMember(Value = "stop_market")]
            Stopmarket = 3,

            /// <summary>
            /// Enum Stoplimit for value: stop_limit
            /// </summary>
            [EnumMember(Value = "stop_limit")]
            Stoplimit = 4

        }

        /// <summary>
        /// order type, &#x60;\&quot;limit\&quot;&#x60;, &#x60;\&quot;market\&quot;&#x60;, &#x60;\&quot;stop_limit\&quot;&#x60;, &#x60;\&quot;stop_market\&quot;&#x60;
        /// </summary>
        /// <value>order type, &#x60;\&quot;limit\&quot;&#x60;, &#x60;\&quot;market\&quot;&#x60;, &#x60;\&quot;stop_limit\&quot;&#x60;, &#x60;\&quot;stop_market\&quot;&#x60;</value>
        [DataMember(Name="order_type", EmitDefaultValue=false)]
        public OrderTypeEnum OrderType { get; set; }
        /// <summary>
        /// Original order type. Optional field
        /// </summary>
        /// <value>Original order type. Optional field</value>
        [JsonConverter(typeof(StringEnumConverter))]
        public enum OriginalOrderTypeEnum
        {
            /// <summary>
            /// Enum Market for value: market
            /// </summary>
            [EnumMember(Value = "market")]
            Market = 1

        }

        /// <summary>
        /// Original order type. Optional field
        /// </summary>
        /// <value>Original order type. Optional field</value>
        [DataMember(Name="original_order_type", EmitDefaultValue=false)]
        public OriginalOrderTypeEnum? OriginalOrderType { get; set; }
        /// <summary>
        /// Trigger type (Only for stop orders). Allowed values: &#x60;\&quot;index_price\&quot;&#x60;, &#x60;\&quot;mark_price\&quot;&#x60;, &#x60;\&quot;last_price\&quot;&#x60;.
        /// </summary>
        /// <value>Trigger type (Only for stop orders). Allowed values: &#x60;\&quot;index_price\&quot;&#x60;, &#x60;\&quot;mark_price\&quot;&#x60;, &#x60;\&quot;last_price\&quot;&#x60;.</value>
        [JsonConverter(typeof(StringEnumConverter))]
        public enum TriggerEnum
        {
            /// <summary>
            /// Enum Indexprice for value: index_price
            /// </summary>
            [EnumMember(Value = "index_price")]
            Indexprice = 1,

            /// <summary>
            /// Enum Markprice for value: mark_price
            /// </summary>
            [EnumMember(Value = "mark_price")]
            Markprice = 2,

            /// <summary>
            /// Enum Lastprice for value: last_price
            /// </summary>
            [EnumMember(Value = "last_price")]
            Lastprice = 3

        }

        /// <summary>
        /// Trigger type (Only for stop orders). Allowed values: &#x60;\&quot;index_price\&quot;&#x60;, &#x60;\&quot;mark_price\&quot;&#x60;, &#x60;\&quot;last_price\&quot;&#x60;.
        /// </summary>
        /// <value>Trigger type (Only for stop orders). Allowed values: &#x60;\&quot;index_price\&quot;&#x60;, &#x60;\&quot;mark_price\&quot;&#x60;, &#x60;\&quot;last_price\&quot;&#x60;.</value>
        [DataMember(Name="trigger", EmitDefaultValue=false)]
        public TriggerEnum? Trigger { get; set; }
        /// <summary>
        /// Initializes a new instance of the <see cref="Order" /> class.
        /// </summary>
        [JsonConstructorAttribute]
        protected Order() { }
        /// <summary>
        /// Initializes a new instance of the <see cref="Order" /> class.
        /// </summary>
        /// <param name="direction">direction, &#x60;buy&#x60; or &#x60;sell&#x60; (required).</param>
        /// <param name="reduceOnly">&#x60;true&#x60; for reduce-only orders only.</param>
        /// <param name="triggered">Whether the stop order has been triggered (Only for stop orders).</param>
        /// <param name="orderId">Unique order identifier (required).</param>
        /// <param name="price">Price in base currency (required).</param>
        /// <param name="timeInForce">Order time in force: &#x60;\&quot;good_til_cancelled\&quot;&#x60;, &#x60;\&quot;fill_or_kill\&quot;&#x60;, &#x60;\&quot;immediate_or_cancel\&quot;&#x60; (required).</param>
        /// <param name="api">&#x60;true&#x60; if created with API (required).</param>
        /// <param name="orderState">order state, &#x60;\&quot;open\&quot;&#x60;, &#x60;\&quot;filled\&quot;&#x60;, &#x60;\&quot;rejected\&quot;&#x60;, &#x60;\&quot;cancelled\&quot;&#x60;, &#x60;\&quot;untriggered\&quot;&#x60; (required).</param>
        /// <param name="implv">Implied volatility in percent. (Only if &#x60;advanced&#x3D;\&quot;implv\&quot;&#x60;).</param>
        /// <param name="advanced">advanced type: &#x60;\&quot;usd\&quot;&#x60; or &#x60;\&quot;implv\&quot;&#x60; (Only for options; field is omitted if not applicable). .</param>
        /// <param name="postOnly">&#x60;true&#x60; for post-only orders only (required).</param>
        /// <param name="usd">Option price in USD (Only if &#x60;advanced&#x3D;\&quot;usd\&quot;&#x60;).</param>
        /// <param name="stopPrice">stop price (Only for future stop orders).</param>
        /// <param name="orderType">order type, &#x60;\&quot;limit\&quot;&#x60;, &#x60;\&quot;market\&quot;&#x60;, &#x60;\&quot;stop_limit\&quot;&#x60;, &#x60;\&quot;stop_market\&quot;&#x60; (required).</param>
        /// <param name="lastUpdateTimestamp">The timestamp (seconds since the Unix epoch, with millisecond precision) (required).</param>
        /// <param name="originalOrderType">Original order type. Optional field.</param>
        /// <param name="maxShow">Maximum amount within an order to be shown to other traders, 0 for invisible order. (required).</param>
        /// <param name="profitLoss">Profit and loss in base currency..</param>
        /// <param name="isLiquidation">&#x60;true&#x60; if order was automatically created during liquidation (required).</param>
        /// <param name="filledAmount">Filled amount of the order. For perpetual and futures the filled_amount is in USD units, for options - in units or corresponding cryptocurrency contracts, e.g., BTC or ETH..</param>
        /// <param name="label">user defined label (up to 32 characters) (required).</param>
        /// <param name="commission">Commission paid so far (in base currency).</param>
        /// <param name="amount">It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH..</param>
        /// <param name="trigger">Trigger type (Only for stop orders). Allowed values: &#x60;\&quot;index_price\&quot;&#x60;, &#x60;\&quot;mark_price\&quot;&#x60;, &#x60;\&quot;last_price\&quot;&#x60;..</param>
        /// <param name="instrumentName">Unique instrument identifier.</param>
        /// <param name="creationTimestamp">The timestamp (seconds since the Unix epoch, with millisecond precision) (required).</param>
        /// <param name="averagePrice">Average fill price of the order.</param>
        public Order(DirectionEnum direction = default(DirectionEnum), bool? reduceOnly = default(bool?), bool? triggered = default(bool?), string orderId = default(string), decimal? price = default(decimal?), TimeInForceEnum timeInForce = default(TimeInForceEnum), bool? api = default(bool?), OrderStateEnum orderState = default(OrderStateEnum), decimal? implv = default(decimal?), AdvancedEnum? advanced = default(AdvancedEnum?), bool? postOnly = default(bool?), decimal? usd = default(decimal?), decimal? stopPrice = default(decimal?), OrderTypeEnum orderType = default(OrderTypeEnum), int? lastUpdateTimestamp = default(int?), OriginalOrderTypeEnum? originalOrderType = default(OriginalOrderTypeEnum?), decimal? maxShow = default(decimal?), decimal? profitLoss = default(decimal?), bool? isLiquidation = default(bool?), decimal? filledAmount = default(decimal?), string label = default(string), decimal? commission = default(decimal?), decimal? amount = default(decimal?), TriggerEnum? trigger = default(TriggerEnum?), string instrumentName = default(string), int? creationTimestamp = default(int?), decimal? averagePrice = default(decimal?))
        {
            // to ensure "direction" is required (not null)
            if (direction == null)
            {
                throw new InvalidDataException("direction is a required property for Order and cannot be null");
            }
            else
            {
                this.Direction = direction;
            }
            
            // to ensure "orderId" is required (not null)
            if (orderId == null)
            {
                throw new InvalidDataException("orderId is a required property for Order and cannot be null");
            }
            else
            {
                this.OrderId = orderId;
            }
            
            // to ensure "price" is required (not null)
            if (price == null)
            {
                throw new InvalidDataException("price is a required property for Order and cannot be null");
            }
            else
            {
                this.Price = price;
            }
            
            // to ensure "timeInForce" is required (not null)
            if (timeInForce == null)
            {
                throw new InvalidDataException("timeInForce is a required property for Order and cannot be null");
            }
            else
            {
                this.TimeInForce = timeInForce;
            }
            
            // to ensure "api" is required (not null)
            if (api == null)
            {
                throw new InvalidDataException("api is a required property for Order and cannot be null");
            }
            else
            {
                this.Api = api;
            }
            
            // to ensure "orderState" is required (not null)
            if (orderState == null)
            {
                throw new InvalidDataException("orderState is a required property for Order and cannot be null");
            }
            else
            {
                this.OrderState = orderState;
            }
            
            // to ensure "postOnly" is required (not null)
            if (postOnly == null)
            {
                throw new InvalidDataException("postOnly is a required property for Order and cannot be null");
            }
            else
            {
                this.PostOnly = postOnly;
            }
            
            // to ensure "orderType" is required (not null)
            if (orderType == null)
            {
                throw new InvalidDataException("orderType is a required property for Order and cannot be null");
            }
            else
            {
                this.OrderType = orderType;
            }
            
            // to ensure "lastUpdateTimestamp" is required (not null)
            if (lastUpdateTimestamp == null)
            {
                throw new InvalidDataException("lastUpdateTimestamp is a required property for Order and cannot be null");
            }
            else
            {
                this.LastUpdateTimestamp = lastUpdateTimestamp;
            }
            
            // to ensure "maxShow" is required (not null)
            if (maxShow == null)
            {
                throw new InvalidDataException("maxShow is a required property for Order and cannot be null");
            }
            else
            {
                this.MaxShow = maxShow;
            }
            
            // to ensure "isLiquidation" is required (not null)
            if (isLiquidation == null)
            {
                throw new InvalidDataException("isLiquidation is a required property for Order and cannot be null");
            }
            else
            {
                this.IsLiquidation = isLiquidation;
            }
            
            // to ensure "label" is required (not null)
            if (label == null)
            {
                throw new InvalidDataException("label is a required property for Order and cannot be null");
            }
            else
            {
                this.Label = label;
            }
            
            // to ensure "creationTimestamp" is required (not null)
            if (creationTimestamp == null)
            {
                throw new InvalidDataException("creationTimestamp is a required property for Order and cannot be null");
            }
            else
            {
                this.CreationTimestamp = creationTimestamp;
            }
            
            this.ReduceOnly = reduceOnly;
            this.Triggered = triggered;
            this.Implv = implv;
            this.Advanced = advanced;
            this.Usd = usd;
            this.StopPrice = stopPrice;
            this.OriginalOrderType = originalOrderType;
            this.ProfitLoss = profitLoss;
            this.FilledAmount = filledAmount;
            this.Commission = commission;
            this.Amount = amount;
            this.Trigger = trigger;
            this.InstrumentName = instrumentName;
            this.AveragePrice = averagePrice;
        }
        

        /// <summary>
        /// &#x60;true&#x60; for reduce-only orders only
        /// </summary>
        /// <value>&#x60;true&#x60; for reduce-only orders only</value>
        [DataMember(Name="reduce_only", EmitDefaultValue=false)]
        public bool? ReduceOnly { get; set; }

        /// <summary>
        /// Whether the stop order has been triggered (Only for stop orders)
        /// </summary>
        /// <value>Whether the stop order has been triggered (Only for stop orders)</value>
        [DataMember(Name="triggered", EmitDefaultValue=false)]
        public bool? Triggered { get; set; }

        /// <summary>
        /// Unique order identifier
        /// </summary>
        /// <value>Unique order identifier</value>
        [DataMember(Name="order_id", EmitDefaultValue=false)]
        public string OrderId { get; set; }

        /// <summary>
        /// Price in base currency
        /// </summary>
        /// <value>Price in base currency</value>
        [DataMember(Name="price", EmitDefaultValue=false)]
        public decimal? Price { get; set; }


        /// <summary>
        /// &#x60;true&#x60; if created with API
        /// </summary>
        /// <value>&#x60;true&#x60; if created with API</value>
        [DataMember(Name="api", EmitDefaultValue=false)]
        public bool? Api { get; set; }


        /// <summary>
        /// Implied volatility in percent. (Only if &#x60;advanced&#x3D;\&quot;implv\&quot;&#x60;)
        /// </summary>
        /// <value>Implied volatility in percent. (Only if &#x60;advanced&#x3D;\&quot;implv\&quot;&#x60;)</value>
        [DataMember(Name="implv", EmitDefaultValue=false)]
        public decimal? Implv { get; set; }


        /// <summary>
        /// &#x60;true&#x60; for post-only orders only
        /// </summary>
        /// <value>&#x60;true&#x60; for post-only orders only</value>
        [DataMember(Name="post_only", EmitDefaultValue=false)]
        public bool? PostOnly { get; set; }

        /// <summary>
        /// Option price in USD (Only if &#x60;advanced&#x3D;\&quot;usd\&quot;&#x60;)
        /// </summary>
        /// <value>Option price in USD (Only if &#x60;advanced&#x3D;\&quot;usd\&quot;&#x60;)</value>
        [DataMember(Name="usd", EmitDefaultValue=false)]
        public decimal? Usd { get; set; }

        /// <summary>
        /// stop price (Only for future stop orders)
        /// </summary>
        /// <value>stop price (Only for future stop orders)</value>
        [DataMember(Name="stop_price", EmitDefaultValue=false)]
        public decimal? StopPrice { get; set; }


        /// <summary>
        /// The timestamp (seconds since the Unix epoch, with millisecond precision)
        /// </summary>
        /// <value>The timestamp (seconds since the Unix epoch, with millisecond precision)</value>
        [DataMember(Name="last_update_timestamp", EmitDefaultValue=false)]
        public int? LastUpdateTimestamp { get; set; }


        /// <summary>
        /// Maximum amount within an order to be shown to other traders, 0 for invisible order.
        /// </summary>
        /// <value>Maximum amount within an order to be shown to other traders, 0 for invisible order.</value>
        [DataMember(Name="max_show", EmitDefaultValue=false)]
        public decimal? MaxShow { get; set; }

        /// <summary>
        /// Profit and loss in base currency.
        /// </summary>
        /// <value>Profit and loss in base currency.</value>
        [DataMember(Name="profit_loss", EmitDefaultValue=false)]
        public decimal? ProfitLoss { get; set; }

        /// <summary>
        /// &#x60;true&#x60; if order was automatically created during liquidation
        /// </summary>
        /// <value>&#x60;true&#x60; if order was automatically created during liquidation</value>
        [DataMember(Name="is_liquidation", EmitDefaultValue=false)]
        public bool? IsLiquidation { get; set; }

        /// <summary>
        /// Filled amount of the order. For perpetual and futures the filled_amount is in USD units, for options - in units or corresponding cryptocurrency contracts, e.g., BTC or ETH.
        /// </summary>
        /// <value>Filled amount of the order. For perpetual and futures the filled_amount is in USD units, for options - in units or corresponding cryptocurrency contracts, e.g., BTC or ETH.</value>
        [DataMember(Name="filled_amount", EmitDefaultValue=false)]
        public decimal? FilledAmount { get; set; }

        /// <summary>
        /// user defined label (up to 32 characters)
        /// </summary>
        /// <value>user defined label (up to 32 characters)</value>
        [DataMember(Name="label", EmitDefaultValue=false)]
        public string Label { get; set; }

        /// <summary>
        /// Commission paid so far (in base currency)
        /// </summary>
        /// <value>Commission paid so far (in base currency)</value>
        [DataMember(Name="commission", EmitDefaultValue=false)]
        public decimal? Commission { get; set; }

        /// <summary>
        /// It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH.
        /// </summary>
        /// <value>It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH.</value>
        [DataMember(Name="amount", EmitDefaultValue=false)]
        public decimal? Amount { get; set; }


        /// <summary>
        /// Unique instrument identifier
        /// </summary>
        /// <value>Unique instrument identifier</value>
        [DataMember(Name="instrument_name", EmitDefaultValue=false)]
        public string InstrumentName { get; set; }

        /// <summary>
        /// The timestamp (seconds since the Unix epoch, with millisecond precision)
        /// </summary>
        /// <value>The timestamp (seconds since the Unix epoch, with millisecond precision)</value>
        [DataMember(Name="creation_timestamp", EmitDefaultValue=false)]
        public int? CreationTimestamp { get; set; }

        /// <summary>
        /// Average fill price of the order
        /// </summary>
        /// <value>Average fill price of the order</value>
        [DataMember(Name="average_price", EmitDefaultValue=false)]
        public decimal? AveragePrice { get; set; }

        /// <summary>
        /// Returns the string presentation of the object
        /// </summary>
        /// <returns>String presentation of the object</returns>
        public override string ToString()
        {
            var sb = new StringBuilder();
            sb.Append("class Order {\n");
            sb.Append("  Direction: ").Append(Direction).Append("\n");
            sb.Append("  ReduceOnly: ").Append(ReduceOnly).Append("\n");
            sb.Append("  Triggered: ").Append(Triggered).Append("\n");
            sb.Append("  OrderId: ").Append(OrderId).Append("\n");
            sb.Append("  Price: ").Append(Price).Append("\n");
            sb.Append("  TimeInForce: ").Append(TimeInForce).Append("\n");
            sb.Append("  Api: ").Append(Api).Append("\n");
            sb.Append("  OrderState: ").Append(OrderState).Append("\n");
            sb.Append("  Implv: ").Append(Implv).Append("\n");
            sb.Append("  Advanced: ").Append(Advanced).Append("\n");
            sb.Append("  PostOnly: ").Append(PostOnly).Append("\n");
            sb.Append("  Usd: ").Append(Usd).Append("\n");
            sb.Append("  StopPrice: ").Append(StopPrice).Append("\n");
            sb.Append("  OrderType: ").Append(OrderType).Append("\n");
            sb.Append("  LastUpdateTimestamp: ").Append(LastUpdateTimestamp).Append("\n");
            sb.Append("  OriginalOrderType: ").Append(OriginalOrderType).Append("\n");
            sb.Append("  MaxShow: ").Append(MaxShow).Append("\n");
            sb.Append("  ProfitLoss: ").Append(ProfitLoss).Append("\n");
            sb.Append("  IsLiquidation: ").Append(IsLiquidation).Append("\n");
            sb.Append("  FilledAmount: ").Append(FilledAmount).Append("\n");
            sb.Append("  Label: ").Append(Label).Append("\n");
            sb.Append("  Commission: ").Append(Commission).Append("\n");
            sb.Append("  Amount: ").Append(Amount).Append("\n");
            sb.Append("  Trigger: ").Append(Trigger).Append("\n");
            sb.Append("  InstrumentName: ").Append(InstrumentName).Append("\n");
            sb.Append("  CreationTimestamp: ").Append(CreationTimestamp).Append("\n");
            sb.Append("  AveragePrice: ").Append(AveragePrice).Append("\n");
            sb.Append("}\n");
            return sb.ToString();
        }
  
        /// <summary>
        /// Returns the JSON string presentation of the object
        /// </summary>
        /// <returns>JSON string presentation of the object</returns>
        public virtual string ToJson()
        {
            return JsonConvert.SerializeObject(this, Formatting.Indented);
        }

        /// <summary>
        /// Returns true if objects are equal
        /// </summary>
        /// <param name="input">Object to be compared</param>
        /// <returns>Boolean</returns>
        public override bool Equals(object input)
        {
            return this.Equals(input as Order);
        }

        /// <summary>
        /// Returns true if Order instances are equal
        /// </summary>
        /// <param name="input">Instance of Order to be compared</param>
        /// <returns>Boolean</returns>
        public bool Equals(Order input)
        {
            if (input == null)
                return false;

            return 
                (
                    this.Direction == input.Direction ||
                    (this.Direction != null &&
                    this.Direction.Equals(input.Direction))
                ) && 
                (
                    this.ReduceOnly == input.ReduceOnly ||
                    (this.ReduceOnly != null &&
                    this.ReduceOnly.Equals(input.ReduceOnly))
                ) && 
                (
                    this.Triggered == input.Triggered ||
                    (this.Triggered != null &&
                    this.Triggered.Equals(input.Triggered))
                ) && 
                (
                    this.OrderId == input.OrderId ||
                    (this.OrderId != null &&
                    this.OrderId.Equals(input.OrderId))
                ) && 
                (
                    this.Price == input.Price ||
                    (this.Price != null &&
                    this.Price.Equals(input.Price))
                ) && 
                (
                    this.TimeInForce == input.TimeInForce ||
                    (this.TimeInForce != null &&
                    this.TimeInForce.Equals(input.TimeInForce))
                ) && 
                (
                    this.Api == input.Api ||
                    (this.Api != null &&
                    this.Api.Equals(input.Api))
                ) && 
                (
                    this.OrderState == input.OrderState ||
                    (this.OrderState != null &&
                    this.OrderState.Equals(input.OrderState))
                ) && 
                (
                    this.Implv == input.Implv ||
                    (this.Implv != null &&
                    this.Implv.Equals(input.Implv))
                ) && 
                (
                    this.Advanced == input.Advanced ||
                    (this.Advanced != null &&
                    this.Advanced.Equals(input.Advanced))
                ) && 
                (
                    this.PostOnly == input.PostOnly ||
                    (this.PostOnly != null &&
                    this.PostOnly.Equals(input.PostOnly))
                ) && 
                (
                    this.Usd == input.Usd ||
                    (this.Usd != null &&
                    this.Usd.Equals(input.Usd))
                ) && 
                (
                    this.StopPrice == input.StopPrice ||
                    (this.StopPrice != null &&
                    this.StopPrice.Equals(input.StopPrice))
                ) && 
                (
                    this.OrderType == input.OrderType ||
                    (this.OrderType != null &&
                    this.OrderType.Equals(input.OrderType))
                ) && 
                (
                    this.LastUpdateTimestamp == input.LastUpdateTimestamp ||
                    (this.LastUpdateTimestamp != null &&
                    this.LastUpdateTimestamp.Equals(input.LastUpdateTimestamp))
                ) && 
                (
                    this.OriginalOrderType == input.OriginalOrderType ||
                    (this.OriginalOrderType != null &&
                    this.OriginalOrderType.Equals(input.OriginalOrderType))
                ) && 
                (
                    this.MaxShow == input.MaxShow ||
                    (this.MaxShow != null &&
                    this.MaxShow.Equals(input.MaxShow))
                ) && 
                (
                    this.ProfitLoss == input.ProfitLoss ||
                    (this.ProfitLoss != null &&
                    this.ProfitLoss.Equals(input.ProfitLoss))
                ) && 
                (
                    this.IsLiquidation == input.IsLiquidation ||
                    (this.IsLiquidation != null &&
                    this.IsLiquidation.Equals(input.IsLiquidation))
                ) && 
                (
                    this.FilledAmount == input.FilledAmount ||
                    (this.FilledAmount != null &&
                    this.FilledAmount.Equals(input.FilledAmount))
                ) && 
                (
                    this.Label == input.Label ||
                    (this.Label != null &&
                    this.Label.Equals(input.Label))
                ) && 
                (
                    this.Commission == input.Commission ||
                    (this.Commission != null &&
                    this.Commission.Equals(input.Commission))
                ) && 
                (
                    this.Amount == input.Amount ||
                    (this.Amount != null &&
                    this.Amount.Equals(input.Amount))
                ) && 
                (
                    this.Trigger == input.Trigger ||
                    (this.Trigger != null &&
                    this.Trigger.Equals(input.Trigger))
                ) && 
                (
                    this.InstrumentName == input.InstrumentName ||
                    (this.InstrumentName != null &&
                    this.InstrumentName.Equals(input.InstrumentName))
                ) && 
                (
                    this.CreationTimestamp == input.CreationTimestamp ||
                    (this.CreationTimestamp != null &&
                    this.CreationTimestamp.Equals(input.CreationTimestamp))
                ) && 
                (
                    this.AveragePrice == input.AveragePrice ||
                    (this.AveragePrice != null &&
                    this.AveragePrice.Equals(input.AveragePrice))
                );
        }

        /// <summary>
        /// Gets the hash code
        /// </summary>
        /// <returns>Hash code</returns>
        public override int GetHashCode()
        {
            unchecked // Overflow is fine, just wrap
            {
                int hashCode = 41;
                if (this.Direction != null)
                    hashCode = hashCode * 59 + this.Direction.GetHashCode();
                if (this.ReduceOnly != null)
                    hashCode = hashCode * 59 + this.ReduceOnly.GetHashCode();
                if (this.Triggered != null)
                    hashCode = hashCode * 59 + this.Triggered.GetHashCode();
                if (this.OrderId != null)
                    hashCode = hashCode * 59 + this.OrderId.GetHashCode();
                if (this.Price != null)
                    hashCode = hashCode * 59 + this.Price.GetHashCode();
                if (this.TimeInForce != null)
                    hashCode = hashCode * 59 + this.TimeInForce.GetHashCode();
                if (this.Api != null)
                    hashCode = hashCode * 59 + this.Api.GetHashCode();
                if (this.OrderState != null)
                    hashCode = hashCode * 59 + this.OrderState.GetHashCode();
                if (this.Implv != null)
                    hashCode = hashCode * 59 + this.Implv.GetHashCode();
                if (this.Advanced != null)
                    hashCode = hashCode * 59 + this.Advanced.GetHashCode();
                if (this.PostOnly != null)
                    hashCode = hashCode * 59 + this.PostOnly.GetHashCode();
                if (this.Usd != null)
                    hashCode = hashCode * 59 + this.Usd.GetHashCode();
                if (this.StopPrice != null)
                    hashCode = hashCode * 59 + this.StopPrice.GetHashCode();
                if (this.OrderType != null)
                    hashCode = hashCode * 59 + this.OrderType.GetHashCode();
                if (this.LastUpdateTimestamp != null)
                    hashCode = hashCode * 59 + this.LastUpdateTimestamp.GetHashCode();
                if (this.OriginalOrderType != null)
                    hashCode = hashCode * 59 + this.OriginalOrderType.GetHashCode();
                if (this.MaxShow != null)
                    hashCode = hashCode * 59 + this.MaxShow.GetHashCode();
                if (this.ProfitLoss != null)
                    hashCode = hashCode * 59 + this.ProfitLoss.GetHashCode();
                if (this.IsLiquidation != null)
                    hashCode = hashCode * 59 + this.IsLiquidation.GetHashCode();
                if (this.FilledAmount != null)
                    hashCode = hashCode * 59 + this.FilledAmount.GetHashCode();
                if (this.Label != null)
                    hashCode = hashCode * 59 + this.Label.GetHashCode();
                if (this.Commission != null)
                    hashCode = hashCode * 59 + this.Commission.GetHashCode();
                if (this.Amount != null)
                    hashCode = hashCode * 59 + this.Amount.GetHashCode();
                if (this.Trigger != null)
                    hashCode = hashCode * 59 + this.Trigger.GetHashCode();
                if (this.InstrumentName != null)
                    hashCode = hashCode * 59 + this.InstrumentName.GetHashCode();
                if (this.CreationTimestamp != null)
                    hashCode = hashCode * 59 + this.CreationTimestamp.GetHashCode();
                if (this.AveragePrice != null)
                    hashCode = hashCode * 59 + this.AveragePrice.GetHashCode();
                return hashCode;
            }
        }

        /// <summary>
        /// To validate all properties of the instance
        /// </summary>
        /// <param name="validationContext">Validation context</param>
        /// <returns>Validation Result</returns>
        IEnumerable<System.ComponentModel.DataAnnotations.ValidationResult> IValidatableObject.Validate(ValidationContext validationContext)
        {
            yield break;
        }
    }

}
