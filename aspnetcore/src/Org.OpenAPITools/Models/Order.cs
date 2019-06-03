/*
 * Deribit API
 *
 * #Overview  Deribit provides three different interfaces to access the API:  * [JSON-RPC over Websocket](#json-rpc) * [JSON-RPC over HTTP](#json-rpc) * [FIX](#fix-api) (Financial Information eXchange)  With the API Console you can use and test the JSON-RPC API, both via HTTP and  via Websocket. To visit the API console, go to __Account > API tab >  API Console tab.__   ##Naming Deribit tradeable assets or instruments use the following system of naming:  |Kind|Examples|Template|Comments| |- -- -|- -- -- -- -|- -- -- -- -|- -- -- -- -| |Future|<code>BTC-25MAR16</code>, <code>BTC-5AUG16</code>|<code>BTC-DMMMYY</code>|<code>BTC</code> is currency, <code>DMMMYY</code> is expiration date, <code>D</code> stands for day of month (1 or 2 digits), <code>MMM</code> - month (3 first letters in English), <code>YY</code> stands for year.| |Perpetual|<code>BTC-PERPETUAL</code>                        ||Perpetual contract for currency <code>BTC</code>.| |Option|<code>BTC-25MAR16-420-C</code>, <code>BTC-5AUG16-580-P</code>|<code>BTC-DMMMYY-STRIKE-K</code>|<code>STRIKE</code> is option strike price in USD. Template <code>K</code> is option kind: <code>C</code> for call options or <code>P</code> for put options.|   # JSON-RPC JSON-RPC is a light-weight remote procedure call (RPC) protocol. The  [JSON-RPC specification](https://www.jsonrpc.org/specification) defines the data structures that are used for the messages that are exchanged between client and server, as well as the rules around their processing. JSON-RPC uses JSON (RFC 4627) as data format.  JSON-RPC is transport agnostic: it does not specify which transport mechanism must be used. The Deribit API supports both Websocket (preferred) and HTTP (with limitations: subscriptions are not supported over HTTP).  ## Request messages > An example of a request message:  ```json {     \"jsonrpc\": \"2.0\",     \"id\": 8066,     \"method\": \"public/ticker\",     \"params\": {         \"instrument\": \"BTC-24AUG18-6500-P\"     } } ```  According to the JSON-RPC sepcification the requests must be JSON objects with the following fields.  |Name|Type|Description| |- -- -|- -- -|- -- -- -- -- --| |jsonrpc|string|The version of the JSON-RPC spec: \"2.0\"| |id|integer or string|An identifier of the request. If it is included, then the response will contain the same identifier| |method|string|The method to be invoked| |params|object|The parameters values for the method. The field names must match with the expected parameter names. The parameters that are expected are described in the documentation for the methods, below.|  <aside class=\"warning\"> The JSON-RPC specification describes two features that are currently not supported by the API:  <ul> <li>Specification of parameter values by position</li> <li>Batch requests</li> </ul>  </aside>   ## Response messages > An example of a response message:  ```json {     \"jsonrpc\": \"2.0\",     \"id\": 5239,     \"testnet\": false,     \"result\": [         {             \"currency\": \"BTC\",             \"currencyLong\": \"Bitcoin\",             \"minConfirmation\": 2,             \"txFee\": 0.0006,             \"isActive\": true,             \"coinType\": \"BITCOIN\",             \"baseAddress\": null         }     ],     \"usIn\": 1535043730126248,     \"usOut\": 1535043730126250,     \"usDiff\": 2 } ```  The JSON-RPC API always responds with a JSON object with the following fields.   |Name|Type|Description| |- -- -|- -- -|- -- -- -- -- --| |id|integer|This is the same id that was sent in the request.| |result|any|If successful, the result of the API call. The format for the result is described with each method.| |error|error object|Only present if there was an error invoking the method. The error object is described below.| |testnet|boolean|Indicates whether the API in use is actually the test API.  <code>false</code> for production server, <code>true</code> for test server.| |usIn|integer|The timestamp when the requests was received (microseconds since the Unix epoch)| |usOut|integer|The timestamp when the response was sent (microseconds since the Unix epoch)| |usDiff|integer|The number of microseconds that was spent handling the request|  <aside class=\"notice\"> The fields <code>testnet</code>, <code>usIn</code>, <code>usOut</code> and <code>usDiff</code> are not part of the JSON-RPC standard.  <p>In order not to clutter the examples they will generally be omitted from the example code.</p> </aside>  > An example of a response with an error:  ```json {     \"jsonrpc\": \"2.0\",     \"id\": 8163,     \"error\": {         \"code\": 11050,         \"message\": \"bad_request\"     },     \"testnet\": false,     \"usIn\": 1535037392434763,     \"usOut\": 1535037392448119,     \"usDiff\": 13356 } ``` In case of an error the response message will contain the error field, with as value an object with the following with the following fields:  |Name|Type|Description |- -- -|- -- -|- -- -- -- -- --| |code|integer|A number that indicates the kind of error.| |message|string|A short description that indicates the kind of error.| |data|any|Additional data about the error. This field may be omitted.|  ## Notifications  > An example of a notification:  ```json {     \"jsonrpc\": \"2.0\",     \"method\": \"subscription\",     \"params\": {         \"channel\": \"deribit_price_index.btc_usd\",         \"data\": {             \"timestamp\": 1535098298227,             \"price\": 6521.17,             \"index_name\": \"btc_usd\"         }     } } ```  API users can subscribe to certain types of notifications. This means that they will receive JSON-RPC notification-messages from the server when certain events occur, such as changes to the index price or changes to the order book for a certain instrument.   The API methods [public/subscribe](#public-subscribe) and [private/subscribe](#private-subscribe) are used to set up a subscription. Since HTTP does not support the sending of messages from server to client, these methods are only availble when using the Websocket transport mechanism.  At the moment of subscription a \"channel\" must be specified. The channel determines the type of events that will be received.  See [Subscriptions](#subscriptions) for more details about the channels.  In accordance with the JSON-RPC specification, the format of a notification  is that of a request message without an <code>id</code> field. The value of the <code>method</code> field will always be <code>\"subscription\"</code>. The <code>params</code> field will always be an object with 2 members: <code>channel</code> and <code>data</code>. The value of the <code>channel</code> member is the name of the channel (a string). The value of the <code>data</code> member is an object that contains data  that is specific for the channel.   ## Authentication  > An example of a JSON request with token:  ```json {     \"id\": 5647,     \"method\": \"private/get_subaccounts\",     \"params\": {         \"access_token\": \"67SVutDoVZSzkUStHSuk51WntMNBJ5mh5DYZhwzpiqDF\"     } } ```  The API consists of `public` and `private` methods. The public methods do not require authentication. The private methods use OAuth 2.0 authentication. This means that a valid OAuth access token must be included in the request, which can get achived by calling method [public/auth](#public-auth).  When the token was assigned to the user, it should be passed along, with other request parameters, back to the server:  |Connection type|Access token placement |- -- -|- -- -- -- -- --| |**Websocket**|Inside request JSON parameters, as an `access_token` field| |**HTTP (REST)**|Header `Authorization: bearer ```Token``` ` value|  ### Additional authorization method - basic user credentials  <span style=\"color:red\"><b> ! Not recommended - however, it could be useful for quick testing API</b></span></br>  Every `private` method could be accessed by providing, inside HTTP `Authorization: Basic XXX` header, values with user `ClientId` and assigned `ClientSecret` (both values can be found on the API page on the Deribit website) encoded with `Base64`:  <code>Authorization: Basic BASE64(`ClientId` + `:` + `ClientSecret`)</code>   ### Additional authorization method - Deribit signature credentials  The Derbit service provides dedicated authorization method, which harness user generated signature to increase security level for passing request data. Generated value is passed inside `Authorization` header, coded as:  <code>Authorization: deri-hmac-sha256 id=```ClientId```,ts=```Timestamp```,sig=```Signature```,nonce=```Nonce```</code>  where:  |Deribit credential|Description |- -- -|- -- -- -- -- --| |*ClientId*|Can be found on the API page on the Deribit website| |*Timestamp*|Time when the request was generated - given as **miliseconds**. It's valid for **60 seconds** since generation, after that time any request with an old timestamp will be rejected.| |*Signature*|Value for signature calculated as described below | |*Nonce*|Single usage, user generated initialization vector for the server token|  The signature is generated by the following formula:  <code> Signature = HEX_STRING( HMAC-SHA256( ClientSecret, StringToSign ) );</code></br>  <code> StringToSign =  Timestamp + \"\\n\" + Nonce + \"\\n\" + RequestData;</code></br>  <code> RequestData =  UPPERCASE(HTTP_METHOD())  + \"\\n\" + URI() + \"\\n\" + RequestBody + \"\\n\";</code></br>   e.g. (using shell with ```openssl``` tool):  <code>&nbsp;&nbsp;&nbsp;&nbsp;ClientId=AAAAAAAAAAA</code></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;ClientSecret=ABCD</code></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;Timestamp=$( date +%s000 )</code></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;Nonce=$( cat /dev/urandom | tr -dc 'a-z0-9' | head -c8 )</code></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;URI=\"/api/v2/private/get_account_summary?currency=BTC\"</code></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;HttpMethod=GET</code></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;Body=\"\"</code></br></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;Signature=$( echo -ne \"${Timestamp}\\n${Nonce}\\n${HttpMethod}\\n${URI}\\n${Body}\\n\" | openssl sha256 -r -hmac \"$ClientSecret\" | cut -f1 -d' ' )</code></br></br> <code>&nbsp;&nbsp;&nbsp;&nbsp;echo $Signature</code></br></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;shell output> ea40d5e5e4fae235ab22b61da98121fbf4acdc06db03d632e23c66bcccb90d2c  (**WARNING**: Exact value depends on current timestamp and client credentials</code></br></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;curl -s -X ${HttpMethod} -H \"Authorization: deri-hmac-sha256 id=${ClientId},ts=${Timestamp},nonce=${Nonce},sig=${Signature}\" \"https://www.deribit.com${URI}\"</code></br></br>    ### Additional authorization method - signature credentials (WebSocket API)  When connecting through Websocket, user can request for authorization using ```client_credential``` method, which requires providing following parameters (as a part of JSON request):  |JSON parameter|Description |- -- -|- -- -- -- -- --| |*grant_type*|Must be **client_signature**| |*client_id*|Can be found on the API page on the Deribit website| |*timestamp*|Time when the request was generated - given as **miliseconds**. It's valid for **60 seconds** since generation, after that time any request with an old timestamp will be rejected.| |*signature*|Value for signature calculated as described below | |*nonce*|Single usage, user generated initialization vector for the server token| |*data*|**Optional** field, which contains any user specific value|  The signature is generated by the following formula:  <code> StringToSign =  Timestamp + \"\\n\" + Nonce + \"\\n\" + Data;</code></br>  <code> Signature = HEX_STRING( HMAC-SHA256( ClientSecret, StringToSign ) );</code></br>   e.g. (using shell with ```openssl``` tool):  <code>&nbsp;&nbsp;&nbsp;&nbsp;ClientId=AAAAAAAAAAA</code></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;ClientSecret=ABCD</code></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;Timestamp=$( date +%s000 ) # e.g. 1554883365000 </code></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;Nonce=$( cat /dev/urandom | tr -dc 'a-z0-9' | head -c8 ) # e.g. fdbmmz79 </code></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;Data=\"\"</code></br></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;Signature=$( echo -ne \"${Timestamp}\\n${Nonce}\\n${Data}\\n\" | openssl sha256 -r -hmac \"$ClientSecret\" | cut -f1 -d' ' )</code></br></br> <code>&nbsp;&nbsp;&nbsp;&nbsp;echo $Signature</code></br></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;shell output> e20c9cd5639d41f8bbc88f4d699c4baf94a4f0ee320e9a116b72743c449eb994  (**WARNING**: Exact value depends on current timestamp and client credentials</code></br></br>   You can also check the signature value using some online tools like, e.g: [https://codebeautify.org/hmac-generator](https://codebeautify.org/hmac-generator) (but don't forget about adding *newline* after each part of the hashed text and remember that you **should use** it only with your **test credentials**).   Here's a sample JSON request created using the values from the example above:  <code> {                            </br> &nbsp;&nbsp;\"jsonrpc\" : \"2.0\",         </br> &nbsp;&nbsp;\"id\" : 9929,               </br> &nbsp;&nbsp;\"method\" : \"public/auth\",  </br> &nbsp;&nbsp;\"params\" :                 </br> &nbsp;&nbsp;{                        </br> &nbsp;&nbsp;&nbsp;&nbsp;\"grant_type\" : \"client_signature\",   </br> &nbsp;&nbsp;&nbsp;&nbsp;\"client_id\" : \"AAAAAAAAAAA\",         </br> &nbsp;&nbsp;&nbsp;&nbsp;\"timestamp\": \"1554883365000\",        </br> &nbsp;&nbsp;&nbsp;&nbsp;\"nonce\": \"fdbmmz79\",                 </br> &nbsp;&nbsp;&nbsp;&nbsp;\"data\": \"\",                          </br> &nbsp;&nbsp;&nbsp;&nbsp;\"signature\" : \"e20c9cd5639d41f8bbc88f4d699c4baf94a4f0ee320e9a116b72743c449eb994\"  </br> &nbsp;&nbsp;}                        </br> }                            </br> </code>   ### Access scope  When asking for `access token` user can provide the required access level (called `scope`) which defines what type of functionality he/she wants to use, and whether requests are only going to check for some data or also to update them.  Scopes are required and checked for `private` methods, so if you plan to use only `public` information you can stay with values assigned by default.  |Scope|Description |- -- -|- -- -- -- -- --| |*account:read*|Access to **account** methods - read only data| |*account:read_write*|Access to **account** methods - allows to manage account settings, add subaccounts, etc.| |*trade:read*|Access to **trade** methods - read only data| |*trade:read_write*|Access to **trade** methods - required to create and modify orders| |*wallet:read*|Access to **wallet** methods - read only data| |*wallet:read_write*|Access to **wallet** methods - allows to withdraw, generate new deposit address, etc.| |*wallet:none*, *account:none*, *trade:none*|Blocked access to specified functionality|    <span style=\"color:red\">**NOTICE:**</span> Depending on choosing an authentication method (```grant type```) some scopes could be narrowed by the server. e.g. when ```grant_type = client_credentials``` and ```scope = wallet:read_write``` it's modified by the server as ```scope = wallet:read```\"   ## JSON-RPC over websocket Websocket is the prefered transport mechanism for the JSON-RPC API, because it is faster and because it can support [subscriptions](#subscriptions) and [cancel on disconnect](#private-enable_cancel_on_disconnect). The code examples that can be found next to each of the methods show how websockets can be used from Python or Javascript/node.js.  ## JSON-RPC over HTTP Besides websockets it is also possible to use the API via HTTP. The code examples for 'shell' show how this can be done using curl. Note that subscriptions and cancel on disconnect are not supported via HTTP.  #Methods 
 *
 * The version of the OpenAPI document: 2.0.0
 * 
 * Generated by: https://openapi-generator.tech
 */

using System;
using System.Linq;
using System.Text;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Runtime.Serialization;
using Newtonsoft.Json;

namespace Org.OpenAPITools.Models
{ 
    /// <summary>
    /// 
    /// </summary>
    [DataContract]
    public partial class Order : IEquatable<Order>
    { 
        /// <summary>
        /// direction, `buy` or `sell`
        /// </summary>
        /// <value>direction, `buy` or `sell`</value>
        [JsonConverter(typeof(Newtonsoft.Json.Converters.StringEnumConverter))]
        public enum DirectionEnum
        {
            
            /// <summary>
            /// Enum BuyEnum for buy
            /// </summary>
            [EnumMember(Value = "buy")]
            BuyEnum = 1,
            
            /// <summary>
            /// Enum SellEnum for sell
            /// </summary>
            [EnumMember(Value = "sell")]
            SellEnum = 2
        }

        /// <summary>
        /// direction, &#x60;buy&#x60; or &#x60;sell&#x60;
        /// </summary>
        /// <value>direction, &#x60;buy&#x60; or &#x60;sell&#x60;</value>
        [Required]
        [DataMember(Name="direction", EmitDefaultValue=false)]
        public DirectionEnum? Direction { get; set; }

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
        [Required]
        [DataMember(Name="order_id", EmitDefaultValue=false)]
        public string OrderId { get; set; }

        /// <summary>
        /// Price in base currency
        /// </summary>
        /// <value>Price in base currency</value>
        [Required]
        [DataMember(Name="price", EmitDefaultValue=false)]
        public decimal? Price { get; set; }

        /// <summary>
        /// Order time in force: `\"good_til_cancelled\"`, `\"fill_or_kill\"`, `\"immediate_or_cancel\"`
        /// </summary>
        /// <value>Order time in force: `\"good_til_cancelled\"`, `\"fill_or_kill\"`, `\"immediate_or_cancel\"`</value>
        [JsonConverter(typeof(Newtonsoft.Json.Converters.StringEnumConverter))]
        public enum TimeInForceEnum
        {
            
            /// <summary>
            /// Enum GoodTilCancelledEnum for good_til_cancelled
            /// </summary>
            [EnumMember(Value = "good_til_cancelled")]
            GoodTilCancelledEnum = 1,
            
            /// <summary>
            /// Enum FillOrKillEnum for fill_or_kill
            /// </summary>
            [EnumMember(Value = "fill_or_kill")]
            FillOrKillEnum = 2,
            
            /// <summary>
            /// Enum ImmediateOrCancelEnum for immediate_or_cancel
            /// </summary>
            [EnumMember(Value = "immediate_or_cancel")]
            ImmediateOrCancelEnum = 3
        }

        /// <summary>
        /// Order time in force: &#x60;\&quot;good_til_cancelled\&quot;&#x60;, &#x60;\&quot;fill_or_kill\&quot;&#x60;, &#x60;\&quot;immediate_or_cancel\&quot;&#x60;
        /// </summary>
        /// <value>Order time in force: &#x60;\&quot;good_til_cancelled\&quot;&#x60;, &#x60;\&quot;fill_or_kill\&quot;&#x60;, &#x60;\&quot;immediate_or_cancel\&quot;&#x60;</value>
        [Required]
        [DataMember(Name="time_in_force", EmitDefaultValue=false)]
        public TimeInForceEnum? TimeInForce { get; set; }

        /// <summary>
        /// &#x60;true&#x60; if created with API
        /// </summary>
        /// <value>&#x60;true&#x60; if created with API</value>
        [Required]
        [DataMember(Name="api", EmitDefaultValue=false)]
        public bool? Api { get; set; }

        /// <summary>
        /// order state, `\"open\"`, `\"filled\"`, `\"rejected\"`, `\"cancelled\"`, `\"untriggered\"`
        /// </summary>
        /// <value>order state, `\"open\"`, `\"filled\"`, `\"rejected\"`, `\"cancelled\"`, `\"untriggered\"`</value>
        [JsonConverter(typeof(Newtonsoft.Json.Converters.StringEnumConverter))]
        public enum OrderStateEnum
        {
            
            /// <summary>
            /// Enum OpenEnum for open
            /// </summary>
            [EnumMember(Value = "open")]
            OpenEnum = 1,
            
            /// <summary>
            /// Enum FilledEnum for filled
            /// </summary>
            [EnumMember(Value = "filled")]
            FilledEnum = 2,
            
            /// <summary>
            /// Enum RejectedEnum for rejected
            /// </summary>
            [EnumMember(Value = "rejected")]
            RejectedEnum = 3,
            
            /// <summary>
            /// Enum CancelledEnum for cancelled
            /// </summary>
            [EnumMember(Value = "cancelled")]
            CancelledEnum = 4,
            
            /// <summary>
            /// Enum UntriggeredEnum for untriggered
            /// </summary>
            [EnumMember(Value = "untriggered")]
            UntriggeredEnum = 5,
            
            /// <summary>
            /// Enum TriggeredEnum for triggered
            /// </summary>
            [EnumMember(Value = "triggered")]
            TriggeredEnum = 6
        }

        /// <summary>
        /// order state, &#x60;\&quot;open\&quot;&#x60;, &#x60;\&quot;filled\&quot;&#x60;, &#x60;\&quot;rejected\&quot;&#x60;, &#x60;\&quot;cancelled\&quot;&#x60;, &#x60;\&quot;untriggered\&quot;&#x60;
        /// </summary>
        /// <value>order state, &#x60;\&quot;open\&quot;&#x60;, &#x60;\&quot;filled\&quot;&#x60;, &#x60;\&quot;rejected\&quot;&#x60;, &#x60;\&quot;cancelled\&quot;&#x60;, &#x60;\&quot;untriggered\&quot;&#x60;</value>
        [Required]
        [DataMember(Name="order_state", EmitDefaultValue=false)]
        public OrderStateEnum? OrderState { get; set; }

        /// <summary>
        /// Implied volatility in percent. (Only if &#x60;advanced&#x3D;\&quot;implv\&quot;&#x60;)
        /// </summary>
        /// <value>Implied volatility in percent. (Only if &#x60;advanced&#x3D;\&quot;implv\&quot;&#x60;)</value>
        [DataMember(Name="implv", EmitDefaultValue=false)]
        public decimal? Implv { get; set; }

        /// <summary>
        /// advanced type: `\"usd\"` or `\"implv\"` (Only for options; field is omitted if not applicable). 
        /// </summary>
        /// <value>advanced type: `\"usd\"` or `\"implv\"` (Only for options; field is omitted if not applicable). </value>
        [JsonConverter(typeof(Newtonsoft.Json.Converters.StringEnumConverter))]
        public enum AdvancedEnum
        {
            
            /// <summary>
            /// Enum UsdEnum for usd
            /// </summary>
            [EnumMember(Value = "usd")]
            UsdEnum = 1,
            
            /// <summary>
            /// Enum ImplvEnum for implv
            /// </summary>
            [EnumMember(Value = "implv")]
            ImplvEnum = 2
        }

        /// <summary>
        /// advanced type: &#x60;\&quot;usd\&quot;&#x60; or &#x60;\&quot;implv\&quot;&#x60; (Only for options; field is omitted if not applicable). 
        /// </summary>
        /// <value>advanced type: &#x60;\&quot;usd\&quot;&#x60; or &#x60;\&quot;implv\&quot;&#x60; (Only for options; field is omitted if not applicable). </value>
        [DataMember(Name="advanced", EmitDefaultValue=false)]
        public AdvancedEnum? Advanced { get; set; }

        /// <summary>
        /// &#x60;true&#x60; for post-only orders only
        /// </summary>
        /// <value>&#x60;true&#x60; for post-only orders only</value>
        [Required]
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
        /// order type, `\"limit\"`, `\"market\"`, `\"stop_limit\"`, `\"stop_market\"`
        /// </summary>
        /// <value>order type, `\"limit\"`, `\"market\"`, `\"stop_limit\"`, `\"stop_market\"`</value>
        [JsonConverter(typeof(Newtonsoft.Json.Converters.StringEnumConverter))]
        public enum OrderTypeEnum
        {
            
            /// <summary>
            /// Enum MarketEnum for market
            /// </summary>
            [EnumMember(Value = "market")]
            MarketEnum = 1,
            
            /// <summary>
            /// Enum LimitEnum for limit
            /// </summary>
            [EnumMember(Value = "limit")]
            LimitEnum = 2,
            
            /// <summary>
            /// Enum StopMarketEnum for stop_market
            /// </summary>
            [EnumMember(Value = "stop_market")]
            StopMarketEnum = 3,
            
            /// <summary>
            /// Enum StopLimitEnum for stop_limit
            /// </summary>
            [EnumMember(Value = "stop_limit")]
            StopLimitEnum = 4
        }

        /// <summary>
        /// order type, &#x60;\&quot;limit\&quot;&#x60;, &#x60;\&quot;market\&quot;&#x60;, &#x60;\&quot;stop_limit\&quot;&#x60;, &#x60;\&quot;stop_market\&quot;&#x60;
        /// </summary>
        /// <value>order type, &#x60;\&quot;limit\&quot;&#x60;, &#x60;\&quot;market\&quot;&#x60;, &#x60;\&quot;stop_limit\&quot;&#x60;, &#x60;\&quot;stop_market\&quot;&#x60;</value>
        [Required]
        [DataMember(Name="order_type", EmitDefaultValue=false)]
        public OrderTypeEnum? OrderType { get; set; }

        /// <summary>
        /// The timestamp (seconds since the Unix epoch, with millisecond precision)
        /// </summary>
        /// <value>The timestamp (seconds since the Unix epoch, with millisecond precision)</value>
        [Required]
        [DataMember(Name="last_update_timestamp", EmitDefaultValue=false)]
        public int? LastUpdateTimestamp { get; set; }

        /// <summary>
        /// Original order type. Optional field
        /// </summary>
        /// <value>Original order type. Optional field</value>
        [JsonConverter(typeof(Newtonsoft.Json.Converters.StringEnumConverter))]
        public enum OriginalOrderTypeEnum
        {
            
            /// <summary>
            /// Enum MarketEnum for market
            /// </summary>
            [EnumMember(Value = "market")]
            MarketEnum = 1
        }

        /// <summary>
        /// Original order type. Optional field
        /// </summary>
        /// <value>Original order type. Optional field</value>
        [DataMember(Name="original_order_type", EmitDefaultValue=false)]
        public OriginalOrderTypeEnum? OriginalOrderType { get; set; }

        /// <summary>
        /// Maximum amount within an order to be shown to other traders, 0 for invisible order.
        /// </summary>
        /// <value>Maximum amount within an order to be shown to other traders, 0 for invisible order.</value>
        [Required]
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
        [Required]
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
        [Required]
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
        /// Trigger type (Only for stop orders). Allowed values: `\"index_price\"`, `\"mark_price\"`, `\"last_price\"`.
        /// </summary>
        /// <value>Trigger type (Only for stop orders). Allowed values: `\"index_price\"`, `\"mark_price\"`, `\"last_price\"`.</value>
        [JsonConverter(typeof(Newtonsoft.Json.Converters.StringEnumConverter))]
        public enum TriggerEnum
        {
            
            /// <summary>
            /// Enum IndexPriceEnum for index_price
            /// </summary>
            [EnumMember(Value = "index_price")]
            IndexPriceEnum = 1,
            
            /// <summary>
            /// Enum MarkPriceEnum for mark_price
            /// </summary>
            [EnumMember(Value = "mark_price")]
            MarkPriceEnum = 2,
            
            /// <summary>
            /// Enum LastPriceEnum for last_price
            /// </summary>
            [EnumMember(Value = "last_price")]
            LastPriceEnum = 3
        }

        /// <summary>
        /// Trigger type (Only for stop orders). Allowed values: &#x60;\&quot;index_price\&quot;&#x60;, &#x60;\&quot;mark_price\&quot;&#x60;, &#x60;\&quot;last_price\&quot;&#x60;.
        /// </summary>
        /// <value>Trigger type (Only for stop orders). Allowed values: &#x60;\&quot;index_price\&quot;&#x60;, &#x60;\&quot;mark_price\&quot;&#x60;, &#x60;\&quot;last_price\&quot;&#x60;.</value>
        [DataMember(Name="trigger", EmitDefaultValue=false)]
        public TriggerEnum? Trigger { get; set; }

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
        [Required]
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
        public string ToJson()
        {
            return JsonConvert.SerializeObject(this, Formatting.Indented);
        }

        /// <summary>
        /// Returns true if objects are equal
        /// </summary>
        /// <param name="obj">Object to be compared</param>
        /// <returns>Boolean</returns>
        public override bool Equals(object obj)
        {
            if (obj is null) return false;
            if (ReferenceEquals(this, obj)) return true;
            return obj.GetType() == GetType() && Equals((Order)obj);
        }

        /// <summary>
        /// Returns true if Order instances are equal
        /// </summary>
        /// <param name="other">Instance of Order to be compared</param>
        /// <returns>Boolean</returns>
        public bool Equals(Order other)
        {
            if (other is null) return false;
            if (ReferenceEquals(this, other)) return true;

            return 
                (
                    Direction == other.Direction ||
                    
                    Direction.Equals(other.Direction)
                ) && 
                (
                    ReduceOnly == other.ReduceOnly ||
                    ReduceOnly != null &&
                    ReduceOnly.Equals(other.ReduceOnly)
                ) && 
                (
                    Triggered == other.Triggered ||
                    Triggered != null &&
                    Triggered.Equals(other.Triggered)
                ) && 
                (
                    OrderId == other.OrderId ||
                    OrderId != null &&
                    OrderId.Equals(other.OrderId)
                ) && 
                (
                    Price == other.Price ||
                    Price != null &&
                    Price.Equals(other.Price)
                ) && 
                (
                    TimeInForce == other.TimeInForce ||
                    
                    TimeInForce.Equals(other.TimeInForce)
                ) && 
                (
                    Api == other.Api ||
                    Api != null &&
                    Api.Equals(other.Api)
                ) && 
                (
                    OrderState == other.OrderState ||
                    
                    OrderState.Equals(other.OrderState)
                ) && 
                (
                    Implv == other.Implv ||
                    Implv != null &&
                    Implv.Equals(other.Implv)
                ) && 
                (
                    Advanced == other.Advanced ||
                    
                    Advanced.Equals(other.Advanced)
                ) && 
                (
                    PostOnly == other.PostOnly ||
                    PostOnly != null &&
                    PostOnly.Equals(other.PostOnly)
                ) && 
                (
                    Usd == other.Usd ||
                    Usd != null &&
                    Usd.Equals(other.Usd)
                ) && 
                (
                    StopPrice == other.StopPrice ||
                    StopPrice != null &&
                    StopPrice.Equals(other.StopPrice)
                ) && 
                (
                    OrderType == other.OrderType ||
                    
                    OrderType.Equals(other.OrderType)
                ) && 
                (
                    LastUpdateTimestamp == other.LastUpdateTimestamp ||
                    LastUpdateTimestamp != null &&
                    LastUpdateTimestamp.Equals(other.LastUpdateTimestamp)
                ) && 
                (
                    OriginalOrderType == other.OriginalOrderType ||
                    
                    OriginalOrderType.Equals(other.OriginalOrderType)
                ) && 
                (
                    MaxShow == other.MaxShow ||
                    MaxShow != null &&
                    MaxShow.Equals(other.MaxShow)
                ) && 
                (
                    ProfitLoss == other.ProfitLoss ||
                    ProfitLoss != null &&
                    ProfitLoss.Equals(other.ProfitLoss)
                ) && 
                (
                    IsLiquidation == other.IsLiquidation ||
                    IsLiquidation != null &&
                    IsLiquidation.Equals(other.IsLiquidation)
                ) && 
                (
                    FilledAmount == other.FilledAmount ||
                    FilledAmount != null &&
                    FilledAmount.Equals(other.FilledAmount)
                ) && 
                (
                    Label == other.Label ||
                    Label != null &&
                    Label.Equals(other.Label)
                ) && 
                (
                    Commission == other.Commission ||
                    Commission != null &&
                    Commission.Equals(other.Commission)
                ) && 
                (
                    Amount == other.Amount ||
                    Amount != null &&
                    Amount.Equals(other.Amount)
                ) && 
                (
                    Trigger == other.Trigger ||
                    
                    Trigger.Equals(other.Trigger)
                ) && 
                (
                    InstrumentName == other.InstrumentName ||
                    InstrumentName != null &&
                    InstrumentName.Equals(other.InstrumentName)
                ) && 
                (
                    CreationTimestamp == other.CreationTimestamp ||
                    CreationTimestamp != null &&
                    CreationTimestamp.Equals(other.CreationTimestamp)
                ) && 
                (
                    AveragePrice == other.AveragePrice ||
                    AveragePrice != null &&
                    AveragePrice.Equals(other.AveragePrice)
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
                var hashCode = 41;
                // Suitable nullity checks etc, of course :)
                    
                    hashCode = hashCode * 59 + Direction.GetHashCode();
                    if (ReduceOnly != null)
                    hashCode = hashCode * 59 + ReduceOnly.GetHashCode();
                    if (Triggered != null)
                    hashCode = hashCode * 59 + Triggered.GetHashCode();
                    if (OrderId != null)
                    hashCode = hashCode * 59 + OrderId.GetHashCode();
                    if (Price != null)
                    hashCode = hashCode * 59 + Price.GetHashCode();
                    
                    hashCode = hashCode * 59 + TimeInForce.GetHashCode();
                    if (Api != null)
                    hashCode = hashCode * 59 + Api.GetHashCode();
                    
                    hashCode = hashCode * 59 + OrderState.GetHashCode();
                    if (Implv != null)
                    hashCode = hashCode * 59 + Implv.GetHashCode();
                    
                    hashCode = hashCode * 59 + Advanced.GetHashCode();
                    if (PostOnly != null)
                    hashCode = hashCode * 59 + PostOnly.GetHashCode();
                    if (Usd != null)
                    hashCode = hashCode * 59 + Usd.GetHashCode();
                    if (StopPrice != null)
                    hashCode = hashCode * 59 + StopPrice.GetHashCode();
                    
                    hashCode = hashCode * 59 + OrderType.GetHashCode();
                    if (LastUpdateTimestamp != null)
                    hashCode = hashCode * 59 + LastUpdateTimestamp.GetHashCode();
                    
                    hashCode = hashCode * 59 + OriginalOrderType.GetHashCode();
                    if (MaxShow != null)
                    hashCode = hashCode * 59 + MaxShow.GetHashCode();
                    if (ProfitLoss != null)
                    hashCode = hashCode * 59 + ProfitLoss.GetHashCode();
                    if (IsLiquidation != null)
                    hashCode = hashCode * 59 + IsLiquidation.GetHashCode();
                    if (FilledAmount != null)
                    hashCode = hashCode * 59 + FilledAmount.GetHashCode();
                    if (Label != null)
                    hashCode = hashCode * 59 + Label.GetHashCode();
                    if (Commission != null)
                    hashCode = hashCode * 59 + Commission.GetHashCode();
                    if (Amount != null)
                    hashCode = hashCode * 59 + Amount.GetHashCode();
                    
                    hashCode = hashCode * 59 + Trigger.GetHashCode();
                    if (InstrumentName != null)
                    hashCode = hashCode * 59 + InstrumentName.GetHashCode();
                    if (CreationTimestamp != null)
                    hashCode = hashCode * 59 + CreationTimestamp.GetHashCode();
                    if (AveragePrice != null)
                    hashCode = hashCode * 59 + AveragePrice.GetHashCode();
                return hashCode;
            }
        }

        #region Operators
        #pragma warning disable 1591

        public static bool operator ==(Order left, Order right)
        {
            return Equals(left, right);
        }

        public static bool operator !=(Order left, Order right)
        {
            return !Equals(left, right);
        }

        #pragma warning restore 1591
        #endregion Operators
    }
}
