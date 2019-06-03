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
    /// UserTrade
    /// </summary>
    [DataContract]
    public partial class UserTrade :  IEquatable<UserTrade>, IValidatableObject
    {
        /// <summary>
        /// Trade direction of the taker
        /// </summary>
        /// <value>Trade direction of the taker</value>
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
        /// Trade direction of the taker
        /// </summary>
        /// <value>Trade direction of the taker</value>
        [DataMember(Name="direction", EmitDefaultValue=false)]
        public DirectionEnum Direction { get; set; }
        /// <summary>
        /// Currency, i.e &#x60;\&quot;BTC\&quot;&#x60;, &#x60;\&quot;ETH\&quot;&#x60;
        /// </summary>
        /// <value>Currency, i.e &#x60;\&quot;BTC\&quot;&#x60;, &#x60;\&quot;ETH\&quot;&#x60;</value>
        [JsonConverter(typeof(StringEnumConverter))]
        public enum FeeCurrencyEnum
        {
            /// <summary>
            /// Enum BTC for value: BTC
            /// </summary>
            [EnumMember(Value = "BTC")]
            BTC = 1,

            /// <summary>
            /// Enum ETH for value: ETH
            /// </summary>
            [EnumMember(Value = "ETH")]
            ETH = 2

        }

        /// <summary>
        /// Currency, i.e &#x60;\&quot;BTC\&quot;&#x60;, &#x60;\&quot;ETH\&quot;&#x60;
        /// </summary>
        /// <value>Currency, i.e &#x60;\&quot;BTC\&quot;&#x60;, &#x60;\&quot;ETH\&quot;&#x60;</value>
        [DataMember(Name="fee_currency", EmitDefaultValue=false)]
        public FeeCurrencyEnum FeeCurrency { get; set; }
        /// <summary>
        /// Order type: &#x60;\&quot;limit&#x60;, &#x60;\&quot;market\&quot;&#x60;, or &#x60;\&quot;liquidation\&quot;&#x60;
        /// </summary>
        /// <value>Order type: &#x60;\&quot;limit&#x60;, &#x60;\&quot;market\&quot;&#x60;, or &#x60;\&quot;liquidation\&quot;&#x60;</value>
        [JsonConverter(typeof(StringEnumConverter))]
        public enum OrderTypeEnum
        {
            /// <summary>
            /// Enum Limit for value: limit
            /// </summary>
            [EnumMember(Value = "limit")]
            Limit = 1,

            /// <summary>
            /// Enum Market for value: market
            /// </summary>
            [EnumMember(Value = "market")]
            Market = 2,

            /// <summary>
            /// Enum Liquidation for value: liquidation
            /// </summary>
            [EnumMember(Value = "liquidation")]
            Liquidation = 3

        }

        /// <summary>
        /// Order type: &#x60;\&quot;limit&#x60;, &#x60;\&quot;market\&quot;&#x60;, or &#x60;\&quot;liquidation\&quot;&#x60;
        /// </summary>
        /// <value>Order type: &#x60;\&quot;limit&#x60;, &#x60;\&quot;market\&quot;&#x60;, or &#x60;\&quot;liquidation\&quot;&#x60;</value>
        [DataMember(Name="order_type", EmitDefaultValue=false)]
        public OrderTypeEnum? OrderType { get; set; }
        /// <summary>
        /// order state, &#x60;\&quot;open\&quot;&#x60;, &#x60;\&quot;filled\&quot;&#x60;, &#x60;\&quot;rejected\&quot;&#x60;, &#x60;\&quot;cancelled\&quot;&#x60;, &#x60;\&quot;untriggered\&quot;&#x60; or &#x60;\&quot;archive\&quot;&#x60; (if order was archived)
        /// </summary>
        /// <value>order state, &#x60;\&quot;open\&quot;&#x60;, &#x60;\&quot;filled\&quot;&#x60;, &#x60;\&quot;rejected\&quot;&#x60;, &#x60;\&quot;cancelled\&quot;&#x60;, &#x60;\&quot;untriggered\&quot;&#x60; or &#x60;\&quot;archive\&quot;&#x60; (if order was archived)</value>
        [JsonConverter(typeof(StringEnumConverter))]
        public enum StateEnum
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
            /// Enum Archive for value: archive
            /// </summary>
            [EnumMember(Value = "archive")]
            Archive = 6

        }

        /// <summary>
        /// order state, &#x60;\&quot;open\&quot;&#x60;, &#x60;\&quot;filled\&quot;&#x60;, &#x60;\&quot;rejected\&quot;&#x60;, &#x60;\&quot;cancelled\&quot;&#x60;, &#x60;\&quot;untriggered\&quot;&#x60; or &#x60;\&quot;archive\&quot;&#x60; (if order was archived)
        /// </summary>
        /// <value>order state, &#x60;\&quot;open\&quot;&#x60;, &#x60;\&quot;filled\&quot;&#x60;, &#x60;\&quot;rejected\&quot;&#x60;, &#x60;\&quot;cancelled\&quot;&#x60;, &#x60;\&quot;untriggered\&quot;&#x60; or &#x60;\&quot;archive\&quot;&#x60; (if order was archived)</value>
        [DataMember(Name="state", EmitDefaultValue=false)]
        public StateEnum State { get; set; }
        /// <summary>
        /// Direction of the \&quot;tick\&quot; (&#x60;0&#x60; &#x3D; Plus Tick, &#x60;1&#x60; &#x3D; Zero-Plus Tick, &#x60;2&#x60; &#x3D; Minus Tick, &#x60;3&#x60; &#x3D; Zero-Minus Tick).
        /// </summary>
        /// <value>Direction of the \&quot;tick\&quot; (&#x60;0&#x60; &#x3D; Plus Tick, &#x60;1&#x60; &#x3D; Zero-Plus Tick, &#x60;2&#x60; &#x3D; Minus Tick, &#x60;3&#x60; &#x3D; Zero-Minus Tick).</value>
        [JsonConverter(typeof(StringEnumConverter))]
        public enum TickDirectionEnum
        {
            /// <summary>
            /// Enum NUMBER_0 for value: 0
            /// </summary>
            NUMBER_0 = 0,

            /// <summary>
            /// Enum NUMBER_1 for value: 1
            /// </summary>
            NUMBER_1 = 1,

            /// <summary>
            /// Enum NUMBER_2 for value: 2
            /// </summary>
            NUMBER_2 = 2,

            /// <summary>
            /// Enum NUMBER_3 for value: 3
            /// </summary>
            NUMBER_3 = 3

        }

        /// <summary>
        /// Direction of the \&quot;tick\&quot; (&#x60;0&#x60; &#x3D; Plus Tick, &#x60;1&#x60; &#x3D; Zero-Plus Tick, &#x60;2&#x60; &#x3D; Minus Tick, &#x60;3&#x60; &#x3D; Zero-Minus Tick).
        /// </summary>
        /// <value>Direction of the \&quot;tick\&quot; (&#x60;0&#x60; &#x3D; Plus Tick, &#x60;1&#x60; &#x3D; Zero-Plus Tick, &#x60;2&#x60; &#x3D; Minus Tick, &#x60;3&#x60; &#x3D; Zero-Minus Tick).</value>
        [DataMember(Name="tick_direction", EmitDefaultValue=false)]
        public TickDirectionEnum TickDirection { get; set; }
        /// <summary>
        /// Describes what was role of users order: &#x60;\&quot;M\&quot;&#x60; when it was maker order, &#x60;\&quot;T\&quot;&#x60; when it was taker order
        /// </summary>
        /// <value>Describes what was role of users order: &#x60;\&quot;M\&quot;&#x60; when it was maker order, &#x60;\&quot;T\&quot;&#x60; when it was taker order</value>
        [JsonConverter(typeof(StringEnumConverter))]
        public enum LiquidityEnum
        {
            /// <summary>
            /// Enum M for value: M
            /// </summary>
            [EnumMember(Value = "M")]
            M = 1,

            /// <summary>
            /// Enum T for value: T
            /// </summary>
            [EnumMember(Value = "T")]
            T = 2

        }

        /// <summary>
        /// Describes what was role of users order: &#x60;\&quot;M\&quot;&#x60; when it was maker order, &#x60;\&quot;T\&quot;&#x60; when it was taker order
        /// </summary>
        /// <value>Describes what was role of users order: &#x60;\&quot;M\&quot;&#x60; when it was maker order, &#x60;\&quot;T\&quot;&#x60; when it was taker order</value>
        [DataMember(Name="liquidity", EmitDefaultValue=false)]
        public LiquidityEnum? Liquidity { get; set; }
        /// <summary>
        /// Initializes a new instance of the <see cref="UserTrade" /> class.
        /// </summary>
        [JsonConstructorAttribute]
        protected UserTrade() { }
        /// <summary>
        /// Initializes a new instance of the <see cref="UserTrade" /> class.
        /// </summary>
        /// <param name="direction">Trade direction of the taker (required).</param>
        /// <param name="feeCurrency">Currency, i.e &#x60;\&quot;BTC\&quot;&#x60;, &#x60;\&quot;ETH\&quot;&#x60; (required).</param>
        /// <param name="orderId">Id of the user order (maker or taker), i.e. subscriber&#39;s order id that took part in the trade (required).</param>
        /// <param name="timestamp">The timestamp of the trade (required).</param>
        /// <param name="price">The price of the trade (required).</param>
        /// <param name="iv">Option implied volatility for the price (Option only).</param>
        /// <param name="tradeId">Unique (per currency) trade identifier (required).</param>
        /// <param name="fee">User&#39;s fee in units of the specified &#x60;fee_currency&#x60; (required).</param>
        /// <param name="orderType">Order type: &#x60;\&quot;limit&#x60;, &#x60;\&quot;market\&quot;&#x60;, or &#x60;\&quot;liquidation\&quot;&#x60;.</param>
        /// <param name="tradeSeq">The sequence number of the trade within instrument (required).</param>
        /// <param name="selfTrade">&#x60;true&#x60; if the trade is against own order. This can only happen when your account has self-trading enabled. Contact an administrator if you think you need that (required).</param>
        /// <param name="state">order state, &#x60;\&quot;open\&quot;&#x60;, &#x60;\&quot;filled\&quot;&#x60;, &#x60;\&quot;rejected\&quot;&#x60;, &#x60;\&quot;cancelled\&quot;&#x60;, &#x60;\&quot;untriggered\&quot;&#x60; or &#x60;\&quot;archive\&quot;&#x60; (if order was archived) (required).</param>
        /// <param name="label">User defined label (presented only when previously set for order by user).</param>
        /// <param name="indexPrice">Index Price at the moment of trade (required).</param>
        /// <param name="amount">Trade amount. For perpetual and futures - in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH. (required).</param>
        /// <param name="instrumentName">Unique instrument identifier (required).</param>
        /// <param name="tickDirection">Direction of the \&quot;tick\&quot; (&#x60;0&#x60; &#x3D; Plus Tick, &#x60;1&#x60; &#x3D; Zero-Plus Tick, &#x60;2&#x60; &#x3D; Minus Tick, &#x60;3&#x60; &#x3D; Zero-Minus Tick). (required).</param>
        /// <param name="matchingId">Always &#x60;null&#x60;, except for a self-trade which is possible only if self-trading is switched on for the account (in that case this will be id of the maker order of the subscriber) (required).</param>
        /// <param name="liquidity">Describes what was role of users order: &#x60;\&quot;M\&quot;&#x60; when it was maker order, &#x60;\&quot;T\&quot;&#x60; when it was taker order.</param>
        public UserTrade(DirectionEnum direction = default(DirectionEnum), FeeCurrencyEnum feeCurrency = default(FeeCurrencyEnum), string orderId = default(string), int? timestamp = default(int?), decimal? price = default(decimal?), decimal? iv = default(decimal?), string tradeId = default(string), decimal? fee = default(decimal?), OrderTypeEnum? orderType = default(OrderTypeEnum?), int? tradeSeq = default(int?), bool? selfTrade = default(bool?), StateEnum state = default(StateEnum), string label = default(string), decimal? indexPrice = default(decimal?), decimal? amount = default(decimal?), string instrumentName = default(string), TickDirectionEnum tickDirection = default(TickDirectionEnum), string matchingId = default(string), LiquidityEnum? liquidity = default(LiquidityEnum?))
        {
            // to ensure "direction" is required (not null)
            if (direction == null)
            {
                throw new InvalidDataException("direction is a required property for UserTrade and cannot be null");
            }
            else
            {
                this.Direction = direction;
            }
            
            // to ensure "feeCurrency" is required (not null)
            if (feeCurrency == null)
            {
                throw new InvalidDataException("feeCurrency is a required property for UserTrade and cannot be null");
            }
            else
            {
                this.FeeCurrency = feeCurrency;
            }
            
            // to ensure "orderId" is required (not null)
            if (orderId == null)
            {
                throw new InvalidDataException("orderId is a required property for UserTrade and cannot be null");
            }
            else
            {
                this.OrderId = orderId;
            }
            
            // to ensure "timestamp" is required (not null)
            if (timestamp == null)
            {
                throw new InvalidDataException("timestamp is a required property for UserTrade and cannot be null");
            }
            else
            {
                this.Timestamp = timestamp;
            }
            
            // to ensure "price" is required (not null)
            if (price == null)
            {
                throw new InvalidDataException("price is a required property for UserTrade and cannot be null");
            }
            else
            {
                this.Price = price;
            }
            
            // to ensure "tradeId" is required (not null)
            if (tradeId == null)
            {
                throw new InvalidDataException("tradeId is a required property for UserTrade and cannot be null");
            }
            else
            {
                this.TradeId = tradeId;
            }
            
            // to ensure "fee" is required (not null)
            if (fee == null)
            {
                throw new InvalidDataException("fee is a required property for UserTrade and cannot be null");
            }
            else
            {
                this.Fee = fee;
            }
            
            // to ensure "tradeSeq" is required (not null)
            if (tradeSeq == null)
            {
                throw new InvalidDataException("tradeSeq is a required property for UserTrade and cannot be null");
            }
            else
            {
                this.TradeSeq = tradeSeq;
            }
            
            // to ensure "selfTrade" is required (not null)
            if (selfTrade == null)
            {
                throw new InvalidDataException("selfTrade is a required property for UserTrade and cannot be null");
            }
            else
            {
                this.SelfTrade = selfTrade;
            }
            
            // to ensure "state" is required (not null)
            if (state == null)
            {
                throw new InvalidDataException("state is a required property for UserTrade and cannot be null");
            }
            else
            {
                this.State = state;
            }
            
            // to ensure "indexPrice" is required (not null)
            if (indexPrice == null)
            {
                throw new InvalidDataException("indexPrice is a required property for UserTrade and cannot be null");
            }
            else
            {
                this.IndexPrice = indexPrice;
            }
            
            // to ensure "amount" is required (not null)
            if (amount == null)
            {
                throw new InvalidDataException("amount is a required property for UserTrade and cannot be null");
            }
            else
            {
                this.Amount = amount;
            }
            
            // to ensure "instrumentName" is required (not null)
            if (instrumentName == null)
            {
                throw new InvalidDataException("instrumentName is a required property for UserTrade and cannot be null");
            }
            else
            {
                this.InstrumentName = instrumentName;
            }
            
            // to ensure "tickDirection" is required (not null)
            if (tickDirection == null)
            {
                throw new InvalidDataException("tickDirection is a required property for UserTrade and cannot be null");
            }
            else
            {
                this.TickDirection = tickDirection;
            }
            
            // to ensure "matchingId" is required (not null)
            if (matchingId == null)
            {
                throw new InvalidDataException("matchingId is a required property for UserTrade and cannot be null");
            }
            else
            {
                this.MatchingId = matchingId;
            }
            
            this.Iv = iv;
            this.OrderType = orderType;
            this.Label = label;
            this.Liquidity = liquidity;
        }
        


        /// <summary>
        /// Id of the user order (maker or taker), i.e. subscriber&#39;s order id that took part in the trade
        /// </summary>
        /// <value>Id of the user order (maker or taker), i.e. subscriber&#39;s order id that took part in the trade</value>
        [DataMember(Name="order_id", EmitDefaultValue=false)]
        public string OrderId { get; set; }

        /// <summary>
        /// The timestamp of the trade
        /// </summary>
        /// <value>The timestamp of the trade</value>
        [DataMember(Name="timestamp", EmitDefaultValue=false)]
        public int? Timestamp { get; set; }

        /// <summary>
        /// The price of the trade
        /// </summary>
        /// <value>The price of the trade</value>
        [DataMember(Name="price", EmitDefaultValue=false)]
        public decimal? Price { get; set; }

        /// <summary>
        /// Option implied volatility for the price (Option only)
        /// </summary>
        /// <value>Option implied volatility for the price (Option only)</value>
        [DataMember(Name="iv", EmitDefaultValue=false)]
        public decimal? Iv { get; set; }

        /// <summary>
        /// Unique (per currency) trade identifier
        /// </summary>
        /// <value>Unique (per currency) trade identifier</value>
        [DataMember(Name="trade_id", EmitDefaultValue=false)]
        public string TradeId { get; set; }

        /// <summary>
        /// User&#39;s fee in units of the specified &#x60;fee_currency&#x60;
        /// </summary>
        /// <value>User&#39;s fee in units of the specified &#x60;fee_currency&#x60;</value>
        [DataMember(Name="fee", EmitDefaultValue=false)]
        public decimal? Fee { get; set; }


        /// <summary>
        /// The sequence number of the trade within instrument
        /// </summary>
        /// <value>The sequence number of the trade within instrument</value>
        [DataMember(Name="trade_seq", EmitDefaultValue=false)]
        public int? TradeSeq { get; set; }

        /// <summary>
        /// &#x60;true&#x60; if the trade is against own order. This can only happen when your account has self-trading enabled. Contact an administrator if you think you need that
        /// </summary>
        /// <value>&#x60;true&#x60; if the trade is against own order. This can only happen when your account has self-trading enabled. Contact an administrator if you think you need that</value>
        [DataMember(Name="self_trade", EmitDefaultValue=false)]
        public bool? SelfTrade { get; set; }


        /// <summary>
        /// User defined label (presented only when previously set for order by user)
        /// </summary>
        /// <value>User defined label (presented only when previously set for order by user)</value>
        [DataMember(Name="label", EmitDefaultValue=false)]
        public string Label { get; set; }

        /// <summary>
        /// Index Price at the moment of trade
        /// </summary>
        /// <value>Index Price at the moment of trade</value>
        [DataMember(Name="index_price", EmitDefaultValue=false)]
        public decimal? IndexPrice { get; set; }

        /// <summary>
        /// Trade amount. For perpetual and futures - in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH.
        /// </summary>
        /// <value>Trade amount. For perpetual and futures - in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH.</value>
        [DataMember(Name="amount", EmitDefaultValue=false)]
        public decimal? Amount { get; set; }

        /// <summary>
        /// Unique instrument identifier
        /// </summary>
        /// <value>Unique instrument identifier</value>
        [DataMember(Name="instrument_name", EmitDefaultValue=false)]
        public string InstrumentName { get; set; }


        /// <summary>
        /// Always &#x60;null&#x60;, except for a self-trade which is possible only if self-trading is switched on for the account (in that case this will be id of the maker order of the subscriber)
        /// </summary>
        /// <value>Always &#x60;null&#x60;, except for a self-trade which is possible only if self-trading is switched on for the account (in that case this will be id of the maker order of the subscriber)</value>
        [DataMember(Name="matching_id", EmitDefaultValue=false)]
        public string MatchingId { get; set; }


        /// <summary>
        /// Returns the string presentation of the object
        /// </summary>
        /// <returns>String presentation of the object</returns>
        public override string ToString()
        {
            var sb = new StringBuilder();
            sb.Append("class UserTrade {\n");
            sb.Append("  Direction: ").Append(Direction).Append("\n");
            sb.Append("  FeeCurrency: ").Append(FeeCurrency).Append("\n");
            sb.Append("  OrderId: ").Append(OrderId).Append("\n");
            sb.Append("  Timestamp: ").Append(Timestamp).Append("\n");
            sb.Append("  Price: ").Append(Price).Append("\n");
            sb.Append("  Iv: ").Append(Iv).Append("\n");
            sb.Append("  TradeId: ").Append(TradeId).Append("\n");
            sb.Append("  Fee: ").Append(Fee).Append("\n");
            sb.Append("  OrderType: ").Append(OrderType).Append("\n");
            sb.Append("  TradeSeq: ").Append(TradeSeq).Append("\n");
            sb.Append("  SelfTrade: ").Append(SelfTrade).Append("\n");
            sb.Append("  State: ").Append(State).Append("\n");
            sb.Append("  Label: ").Append(Label).Append("\n");
            sb.Append("  IndexPrice: ").Append(IndexPrice).Append("\n");
            sb.Append("  Amount: ").Append(Amount).Append("\n");
            sb.Append("  InstrumentName: ").Append(InstrumentName).Append("\n");
            sb.Append("  TickDirection: ").Append(TickDirection).Append("\n");
            sb.Append("  MatchingId: ").Append(MatchingId).Append("\n");
            sb.Append("  Liquidity: ").Append(Liquidity).Append("\n");
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
            return this.Equals(input as UserTrade);
        }

        /// <summary>
        /// Returns true if UserTrade instances are equal
        /// </summary>
        /// <param name="input">Instance of UserTrade to be compared</param>
        /// <returns>Boolean</returns>
        public bool Equals(UserTrade input)
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
                    this.FeeCurrency == input.FeeCurrency ||
                    (this.FeeCurrency != null &&
                    this.FeeCurrency.Equals(input.FeeCurrency))
                ) && 
                (
                    this.OrderId == input.OrderId ||
                    (this.OrderId != null &&
                    this.OrderId.Equals(input.OrderId))
                ) && 
                (
                    this.Timestamp == input.Timestamp ||
                    (this.Timestamp != null &&
                    this.Timestamp.Equals(input.Timestamp))
                ) && 
                (
                    this.Price == input.Price ||
                    (this.Price != null &&
                    this.Price.Equals(input.Price))
                ) && 
                (
                    this.Iv == input.Iv ||
                    (this.Iv != null &&
                    this.Iv.Equals(input.Iv))
                ) && 
                (
                    this.TradeId == input.TradeId ||
                    (this.TradeId != null &&
                    this.TradeId.Equals(input.TradeId))
                ) && 
                (
                    this.Fee == input.Fee ||
                    (this.Fee != null &&
                    this.Fee.Equals(input.Fee))
                ) && 
                (
                    this.OrderType == input.OrderType ||
                    (this.OrderType != null &&
                    this.OrderType.Equals(input.OrderType))
                ) && 
                (
                    this.TradeSeq == input.TradeSeq ||
                    (this.TradeSeq != null &&
                    this.TradeSeq.Equals(input.TradeSeq))
                ) && 
                (
                    this.SelfTrade == input.SelfTrade ||
                    (this.SelfTrade != null &&
                    this.SelfTrade.Equals(input.SelfTrade))
                ) && 
                (
                    this.State == input.State ||
                    (this.State != null &&
                    this.State.Equals(input.State))
                ) && 
                (
                    this.Label == input.Label ||
                    (this.Label != null &&
                    this.Label.Equals(input.Label))
                ) && 
                (
                    this.IndexPrice == input.IndexPrice ||
                    (this.IndexPrice != null &&
                    this.IndexPrice.Equals(input.IndexPrice))
                ) && 
                (
                    this.Amount == input.Amount ||
                    (this.Amount != null &&
                    this.Amount.Equals(input.Amount))
                ) && 
                (
                    this.InstrumentName == input.InstrumentName ||
                    (this.InstrumentName != null &&
                    this.InstrumentName.Equals(input.InstrumentName))
                ) && 
                (
                    this.TickDirection == input.TickDirection ||
                    (this.TickDirection != null &&
                    this.TickDirection.Equals(input.TickDirection))
                ) && 
                (
                    this.MatchingId == input.MatchingId ||
                    (this.MatchingId != null &&
                    this.MatchingId.Equals(input.MatchingId))
                ) && 
                (
                    this.Liquidity == input.Liquidity ||
                    (this.Liquidity != null &&
                    this.Liquidity.Equals(input.Liquidity))
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
                if (this.FeeCurrency != null)
                    hashCode = hashCode * 59 + this.FeeCurrency.GetHashCode();
                if (this.OrderId != null)
                    hashCode = hashCode * 59 + this.OrderId.GetHashCode();
                if (this.Timestamp != null)
                    hashCode = hashCode * 59 + this.Timestamp.GetHashCode();
                if (this.Price != null)
                    hashCode = hashCode * 59 + this.Price.GetHashCode();
                if (this.Iv != null)
                    hashCode = hashCode * 59 + this.Iv.GetHashCode();
                if (this.TradeId != null)
                    hashCode = hashCode * 59 + this.TradeId.GetHashCode();
                if (this.Fee != null)
                    hashCode = hashCode * 59 + this.Fee.GetHashCode();
                if (this.OrderType != null)
                    hashCode = hashCode * 59 + this.OrderType.GetHashCode();
                if (this.TradeSeq != null)
                    hashCode = hashCode * 59 + this.TradeSeq.GetHashCode();
                if (this.SelfTrade != null)
                    hashCode = hashCode * 59 + this.SelfTrade.GetHashCode();
                if (this.State != null)
                    hashCode = hashCode * 59 + this.State.GetHashCode();
                if (this.Label != null)
                    hashCode = hashCode * 59 + this.Label.GetHashCode();
                if (this.IndexPrice != null)
                    hashCode = hashCode * 59 + this.IndexPrice.GetHashCode();
                if (this.Amount != null)
                    hashCode = hashCode * 59 + this.Amount.GetHashCode();
                if (this.InstrumentName != null)
                    hashCode = hashCode * 59 + this.InstrumentName.GetHashCode();
                if (this.TickDirection != null)
                    hashCode = hashCode * 59 + this.TickDirection.GetHashCode();
                if (this.MatchingId != null)
                    hashCode = hashCode * 59 + this.MatchingId.GetHashCode();
                if (this.Liquidity != null)
                    hashCode = hashCode * 59 + this.Liquidity.GetHashCode();
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
