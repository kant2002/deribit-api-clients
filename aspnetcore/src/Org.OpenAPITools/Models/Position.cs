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
    public partial class Position : IEquatable<Position>
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
        /// Only for options, average price in USD
        /// </summary>
        /// <value>Only for options, average price in USD</value>
        [DataMember(Name="average_price_usd", EmitDefaultValue=false)]
        public decimal? AveragePriceUsd { get; set; }

        /// <summary>
        /// Only for futures, estimated liquidation price
        /// </summary>
        /// <value>Only for futures, estimated liquidation price</value>
        [DataMember(Name="estimated_liquidation_price", EmitDefaultValue=false)]
        public decimal? EstimatedLiquidationPrice { get; set; }

        /// <summary>
        /// Floating profit or loss
        /// </summary>
        /// <value>Floating profit or loss</value>
        [Required]
        [DataMember(Name="floating_profit_loss", EmitDefaultValue=false)]
        public decimal? FloatingProfitLoss { get; set; }

        /// <summary>
        /// Only for options, floating profit or loss in USD
        /// </summary>
        /// <value>Only for options, floating profit or loss in USD</value>
        [DataMember(Name="floating_profit_loss_usd", EmitDefaultValue=false)]
        public decimal? FloatingProfitLossUsd { get; set; }

        /// <summary>
        /// Open orders margin
        /// </summary>
        /// <value>Open orders margin</value>
        [Required]
        [DataMember(Name="open_orders_margin", EmitDefaultValue=false)]
        public decimal? OpenOrdersMargin { get; set; }

        /// <summary>
        /// Profit or loss from position
        /// </summary>
        /// <value>Profit or loss from position</value>
        [Required]
        [DataMember(Name="total_profit_loss", EmitDefaultValue=false)]
        public decimal? TotalProfitLoss { get; set; }

        /// <summary>
        /// Realized profit or loss
        /// </summary>
        /// <value>Realized profit or loss</value>
        [DataMember(Name="realized_profit_loss", EmitDefaultValue=false)]
        public decimal? RealizedProfitLoss { get; set; }

        /// <summary>
        /// Delta parameter
        /// </summary>
        /// <value>Delta parameter</value>
        [Required]
        [DataMember(Name="delta", EmitDefaultValue=false)]
        public decimal? Delta { get; set; }

        /// <summary>
        /// Initial margin
        /// </summary>
        /// <value>Initial margin</value>
        [Required]
        [DataMember(Name="initial_margin", EmitDefaultValue=false)]
        public decimal? InitialMargin { get; set; }

        /// <summary>
        /// Position size for futures size in quote currency (e.g. USD), for options size is in base currency (e.g. BTC)
        /// </summary>
        /// <value>Position size for futures size in quote currency (e.g. USD), for options size is in base currency (e.g. BTC)</value>
        [Required]
        [DataMember(Name="size", EmitDefaultValue=false)]
        public decimal? Size { get; set; }

        /// <summary>
        /// Maintenance margin
        /// </summary>
        /// <value>Maintenance margin</value>
        [Required]
        [DataMember(Name="maintenance_margin", EmitDefaultValue=false)]
        public decimal? MaintenanceMargin { get; set; }

        /// <summary>
        /// Instrument kind, `\"future\"` or `\"option\"`
        /// </summary>
        /// <value>Instrument kind, `\"future\"` or `\"option\"`</value>
        [JsonConverter(typeof(Newtonsoft.Json.Converters.StringEnumConverter))]
        public enum KindEnum
        {
            
            /// <summary>
            /// Enum FutureEnum for future
            /// </summary>
            [EnumMember(Value = "future")]
            FutureEnum = 1,
            
            /// <summary>
            /// Enum OptionEnum for option
            /// </summary>
            [EnumMember(Value = "option")]
            OptionEnum = 2
        }

        /// <summary>
        /// Instrument kind, &#x60;\&quot;future\&quot;&#x60; or &#x60;\&quot;option\&quot;&#x60;
        /// </summary>
        /// <value>Instrument kind, &#x60;\&quot;future\&quot;&#x60; or &#x60;\&quot;option\&quot;&#x60;</value>
        [Required]
        [DataMember(Name="kind", EmitDefaultValue=false)]
        public KindEnum? Kind { get; set; }

        /// <summary>
        /// Current mark price for position&#39;s instrument
        /// </summary>
        /// <value>Current mark price for position&#39;s instrument</value>
        [Required]
        [DataMember(Name="mark_price", EmitDefaultValue=false)]
        public decimal? MarkPrice { get; set; }

        /// <summary>
        /// Average price of trades that built this position
        /// </summary>
        /// <value>Average price of trades that built this position</value>
        [Required]
        [DataMember(Name="average_price", EmitDefaultValue=false)]
        public decimal? AveragePrice { get; set; }

        /// <summary>
        /// Last settlement price for position&#39;s instrument 0 if instrument wasn&#39;t settled yet
        /// </summary>
        /// <value>Last settlement price for position&#39;s instrument 0 if instrument wasn&#39;t settled yet</value>
        [Required]
        [DataMember(Name="settlement_price", EmitDefaultValue=false)]
        public decimal? SettlementPrice { get; set; }

        /// <summary>
        /// Current index price
        /// </summary>
        /// <value>Current index price</value>
        [Required]
        [DataMember(Name="index_price", EmitDefaultValue=false)]
        public decimal? IndexPrice { get; set; }

        /// <summary>
        /// Unique instrument identifier
        /// </summary>
        /// <value>Unique instrument identifier</value>
        [Required]
        [DataMember(Name="instrument_name", EmitDefaultValue=false)]
        public string InstrumentName { get; set; }

        /// <summary>
        /// Only for futures, position size in base currency
        /// </summary>
        /// <value>Only for futures, position size in base currency</value>
        [DataMember(Name="size_currency", EmitDefaultValue=false)]
        public decimal? SizeCurrency { get; set; }

        /// <summary>
        /// Returns the string presentation of the object
        /// </summary>
        /// <returns>String presentation of the object</returns>
        public override string ToString()
        {
            var sb = new StringBuilder();
            sb.Append("class Position {\n");
            sb.Append("  Direction: ").Append(Direction).Append("\n");
            sb.Append("  AveragePriceUsd: ").Append(AveragePriceUsd).Append("\n");
            sb.Append("  EstimatedLiquidationPrice: ").Append(EstimatedLiquidationPrice).Append("\n");
            sb.Append("  FloatingProfitLoss: ").Append(FloatingProfitLoss).Append("\n");
            sb.Append("  FloatingProfitLossUsd: ").Append(FloatingProfitLossUsd).Append("\n");
            sb.Append("  OpenOrdersMargin: ").Append(OpenOrdersMargin).Append("\n");
            sb.Append("  TotalProfitLoss: ").Append(TotalProfitLoss).Append("\n");
            sb.Append("  RealizedProfitLoss: ").Append(RealizedProfitLoss).Append("\n");
            sb.Append("  Delta: ").Append(Delta).Append("\n");
            sb.Append("  InitialMargin: ").Append(InitialMargin).Append("\n");
            sb.Append("  Size: ").Append(Size).Append("\n");
            sb.Append("  MaintenanceMargin: ").Append(MaintenanceMargin).Append("\n");
            sb.Append("  Kind: ").Append(Kind).Append("\n");
            sb.Append("  MarkPrice: ").Append(MarkPrice).Append("\n");
            sb.Append("  AveragePrice: ").Append(AveragePrice).Append("\n");
            sb.Append("  SettlementPrice: ").Append(SettlementPrice).Append("\n");
            sb.Append("  IndexPrice: ").Append(IndexPrice).Append("\n");
            sb.Append("  InstrumentName: ").Append(InstrumentName).Append("\n");
            sb.Append("  SizeCurrency: ").Append(SizeCurrency).Append("\n");
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
            return obj.GetType() == GetType() && Equals((Position)obj);
        }

        /// <summary>
        /// Returns true if Position instances are equal
        /// </summary>
        /// <param name="other">Instance of Position to be compared</param>
        /// <returns>Boolean</returns>
        public bool Equals(Position other)
        {
            if (other is null) return false;
            if (ReferenceEquals(this, other)) return true;

            return 
                (
                    Direction == other.Direction ||
                    
                    Direction.Equals(other.Direction)
                ) && 
                (
                    AveragePriceUsd == other.AveragePriceUsd ||
                    AveragePriceUsd != null &&
                    AveragePriceUsd.Equals(other.AveragePriceUsd)
                ) && 
                (
                    EstimatedLiquidationPrice == other.EstimatedLiquidationPrice ||
                    EstimatedLiquidationPrice != null &&
                    EstimatedLiquidationPrice.Equals(other.EstimatedLiquidationPrice)
                ) && 
                (
                    FloatingProfitLoss == other.FloatingProfitLoss ||
                    FloatingProfitLoss != null &&
                    FloatingProfitLoss.Equals(other.FloatingProfitLoss)
                ) && 
                (
                    FloatingProfitLossUsd == other.FloatingProfitLossUsd ||
                    FloatingProfitLossUsd != null &&
                    FloatingProfitLossUsd.Equals(other.FloatingProfitLossUsd)
                ) && 
                (
                    OpenOrdersMargin == other.OpenOrdersMargin ||
                    OpenOrdersMargin != null &&
                    OpenOrdersMargin.Equals(other.OpenOrdersMargin)
                ) && 
                (
                    TotalProfitLoss == other.TotalProfitLoss ||
                    TotalProfitLoss != null &&
                    TotalProfitLoss.Equals(other.TotalProfitLoss)
                ) && 
                (
                    RealizedProfitLoss == other.RealizedProfitLoss ||
                    RealizedProfitLoss != null &&
                    RealizedProfitLoss.Equals(other.RealizedProfitLoss)
                ) && 
                (
                    Delta == other.Delta ||
                    Delta != null &&
                    Delta.Equals(other.Delta)
                ) && 
                (
                    InitialMargin == other.InitialMargin ||
                    InitialMargin != null &&
                    InitialMargin.Equals(other.InitialMargin)
                ) && 
                (
                    Size == other.Size ||
                    Size != null &&
                    Size.Equals(other.Size)
                ) && 
                (
                    MaintenanceMargin == other.MaintenanceMargin ||
                    MaintenanceMargin != null &&
                    MaintenanceMargin.Equals(other.MaintenanceMargin)
                ) && 
                (
                    Kind == other.Kind ||
                    
                    Kind.Equals(other.Kind)
                ) && 
                (
                    MarkPrice == other.MarkPrice ||
                    MarkPrice != null &&
                    MarkPrice.Equals(other.MarkPrice)
                ) && 
                (
                    AveragePrice == other.AveragePrice ||
                    AveragePrice != null &&
                    AveragePrice.Equals(other.AveragePrice)
                ) && 
                (
                    SettlementPrice == other.SettlementPrice ||
                    SettlementPrice != null &&
                    SettlementPrice.Equals(other.SettlementPrice)
                ) && 
                (
                    IndexPrice == other.IndexPrice ||
                    IndexPrice != null &&
                    IndexPrice.Equals(other.IndexPrice)
                ) && 
                (
                    InstrumentName == other.InstrumentName ||
                    InstrumentName != null &&
                    InstrumentName.Equals(other.InstrumentName)
                ) && 
                (
                    SizeCurrency == other.SizeCurrency ||
                    SizeCurrency != null &&
                    SizeCurrency.Equals(other.SizeCurrency)
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
                    if (AveragePriceUsd != null)
                    hashCode = hashCode * 59 + AveragePriceUsd.GetHashCode();
                    if (EstimatedLiquidationPrice != null)
                    hashCode = hashCode * 59 + EstimatedLiquidationPrice.GetHashCode();
                    if (FloatingProfitLoss != null)
                    hashCode = hashCode * 59 + FloatingProfitLoss.GetHashCode();
                    if (FloatingProfitLossUsd != null)
                    hashCode = hashCode * 59 + FloatingProfitLossUsd.GetHashCode();
                    if (OpenOrdersMargin != null)
                    hashCode = hashCode * 59 + OpenOrdersMargin.GetHashCode();
                    if (TotalProfitLoss != null)
                    hashCode = hashCode * 59 + TotalProfitLoss.GetHashCode();
                    if (RealizedProfitLoss != null)
                    hashCode = hashCode * 59 + RealizedProfitLoss.GetHashCode();
                    if (Delta != null)
                    hashCode = hashCode * 59 + Delta.GetHashCode();
                    if (InitialMargin != null)
                    hashCode = hashCode * 59 + InitialMargin.GetHashCode();
                    if (Size != null)
                    hashCode = hashCode * 59 + Size.GetHashCode();
                    if (MaintenanceMargin != null)
                    hashCode = hashCode * 59 + MaintenanceMargin.GetHashCode();
                    
                    hashCode = hashCode * 59 + Kind.GetHashCode();
                    if (MarkPrice != null)
                    hashCode = hashCode * 59 + MarkPrice.GetHashCode();
                    if (AveragePrice != null)
                    hashCode = hashCode * 59 + AveragePrice.GetHashCode();
                    if (SettlementPrice != null)
                    hashCode = hashCode * 59 + SettlementPrice.GetHashCode();
                    if (IndexPrice != null)
                    hashCode = hashCode * 59 + IndexPrice.GetHashCode();
                    if (InstrumentName != null)
                    hashCode = hashCode * 59 + InstrumentName.GetHashCode();
                    if (SizeCurrency != null)
                    hashCode = hashCode * 59 + SizeCurrency.GetHashCode();
                return hashCode;
            }
        }

        #region Operators
        #pragma warning disable 1591

        public static bool operator ==(Position left, Position right)
        {
            return Equals(left, right);
        }

        public static bool operator !=(Position left, Position right)
        {
            return !Equals(left, right);
        }

        #pragma warning restore 1591
        #endregion Operators
    }
}
