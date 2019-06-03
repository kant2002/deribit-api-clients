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
    /// Position
    /// </summary>
    [DataContract]
    public partial class Position :  IEquatable<Position>, IValidatableObject
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
        /// Instrument kind, &#x60;\&quot;future\&quot;&#x60; or &#x60;\&quot;option\&quot;&#x60;
        /// </summary>
        /// <value>Instrument kind, &#x60;\&quot;future\&quot;&#x60; or &#x60;\&quot;option\&quot;&#x60;</value>
        [JsonConverter(typeof(StringEnumConverter))]
        public enum KindEnum
        {
            /// <summary>
            /// Enum Future for value: future
            /// </summary>
            [EnumMember(Value = "future")]
            Future = 1,

            /// <summary>
            /// Enum Option for value: option
            /// </summary>
            [EnumMember(Value = "option")]
            Option = 2

        }

        /// <summary>
        /// Instrument kind, &#x60;\&quot;future\&quot;&#x60; or &#x60;\&quot;option\&quot;&#x60;
        /// </summary>
        /// <value>Instrument kind, &#x60;\&quot;future\&quot;&#x60; or &#x60;\&quot;option\&quot;&#x60;</value>
        [DataMember(Name="kind", EmitDefaultValue=false)]
        public KindEnum Kind { get; set; }
        /// <summary>
        /// Initializes a new instance of the <see cref="Position" /> class.
        /// </summary>
        [JsonConstructorAttribute]
        protected Position() { }
        /// <summary>
        /// Initializes a new instance of the <see cref="Position" /> class.
        /// </summary>
        /// <param name="direction">direction, &#x60;buy&#x60; or &#x60;sell&#x60; (required).</param>
        /// <param name="averagePriceUsd">Only for options, average price in USD.</param>
        /// <param name="estimatedLiquidationPrice">Only for futures, estimated liquidation price.</param>
        /// <param name="floatingProfitLoss">Floating profit or loss (required).</param>
        /// <param name="floatingProfitLossUsd">Only for options, floating profit or loss in USD.</param>
        /// <param name="openOrdersMargin">Open orders margin (required).</param>
        /// <param name="totalProfitLoss">Profit or loss from position (required).</param>
        /// <param name="realizedProfitLoss">Realized profit or loss.</param>
        /// <param name="delta">Delta parameter (required).</param>
        /// <param name="initialMargin">Initial margin (required).</param>
        /// <param name="size">Position size for futures size in quote currency (e.g. USD), for options size is in base currency (e.g. BTC) (required).</param>
        /// <param name="maintenanceMargin">Maintenance margin (required).</param>
        /// <param name="kind">Instrument kind, &#x60;\&quot;future\&quot;&#x60; or &#x60;\&quot;option\&quot;&#x60; (required).</param>
        /// <param name="markPrice">Current mark price for position&#39;s instrument (required).</param>
        /// <param name="averagePrice">Average price of trades that built this position (required).</param>
        /// <param name="settlementPrice">Last settlement price for position&#39;s instrument 0 if instrument wasn&#39;t settled yet (required).</param>
        /// <param name="indexPrice">Current index price (required).</param>
        /// <param name="instrumentName">Unique instrument identifier (required).</param>
        /// <param name="sizeCurrency">Only for futures, position size in base currency.</param>
        public Position(DirectionEnum direction = default(DirectionEnum), decimal? averagePriceUsd = default(decimal?), decimal? estimatedLiquidationPrice = default(decimal?), decimal? floatingProfitLoss = default(decimal?), decimal? floatingProfitLossUsd = default(decimal?), decimal? openOrdersMargin = default(decimal?), decimal? totalProfitLoss = default(decimal?), decimal? realizedProfitLoss = default(decimal?), decimal? delta = default(decimal?), decimal? initialMargin = default(decimal?), decimal? size = default(decimal?), decimal? maintenanceMargin = default(decimal?), KindEnum kind = default(KindEnum), decimal? markPrice = default(decimal?), decimal? averagePrice = default(decimal?), decimal? settlementPrice = default(decimal?), decimal? indexPrice = default(decimal?), string instrumentName = default(string), decimal? sizeCurrency = default(decimal?))
        {
            // to ensure "direction" is required (not null)
            if (direction == null)
            {
                throw new InvalidDataException("direction is a required property for Position and cannot be null");
            }
            else
            {
                this.Direction = direction;
            }
            
            // to ensure "floatingProfitLoss" is required (not null)
            if (floatingProfitLoss == null)
            {
                throw new InvalidDataException("floatingProfitLoss is a required property for Position and cannot be null");
            }
            else
            {
                this.FloatingProfitLoss = floatingProfitLoss;
            }
            
            // to ensure "openOrdersMargin" is required (not null)
            if (openOrdersMargin == null)
            {
                throw new InvalidDataException("openOrdersMargin is a required property for Position and cannot be null");
            }
            else
            {
                this.OpenOrdersMargin = openOrdersMargin;
            }
            
            // to ensure "totalProfitLoss" is required (not null)
            if (totalProfitLoss == null)
            {
                throw new InvalidDataException("totalProfitLoss is a required property for Position and cannot be null");
            }
            else
            {
                this.TotalProfitLoss = totalProfitLoss;
            }
            
            // to ensure "delta" is required (not null)
            if (delta == null)
            {
                throw new InvalidDataException("delta is a required property for Position and cannot be null");
            }
            else
            {
                this.Delta = delta;
            }
            
            // to ensure "initialMargin" is required (not null)
            if (initialMargin == null)
            {
                throw new InvalidDataException("initialMargin is a required property for Position and cannot be null");
            }
            else
            {
                this.InitialMargin = initialMargin;
            }
            
            // to ensure "size" is required (not null)
            if (size == null)
            {
                throw new InvalidDataException("size is a required property for Position and cannot be null");
            }
            else
            {
                this.Size = size;
            }
            
            // to ensure "maintenanceMargin" is required (not null)
            if (maintenanceMargin == null)
            {
                throw new InvalidDataException("maintenanceMargin is a required property for Position and cannot be null");
            }
            else
            {
                this.MaintenanceMargin = maintenanceMargin;
            }
            
            // to ensure "kind" is required (not null)
            if (kind == null)
            {
                throw new InvalidDataException("kind is a required property for Position and cannot be null");
            }
            else
            {
                this.Kind = kind;
            }
            
            // to ensure "markPrice" is required (not null)
            if (markPrice == null)
            {
                throw new InvalidDataException("markPrice is a required property for Position and cannot be null");
            }
            else
            {
                this.MarkPrice = markPrice;
            }
            
            // to ensure "averagePrice" is required (not null)
            if (averagePrice == null)
            {
                throw new InvalidDataException("averagePrice is a required property for Position and cannot be null");
            }
            else
            {
                this.AveragePrice = averagePrice;
            }
            
            // to ensure "settlementPrice" is required (not null)
            if (settlementPrice == null)
            {
                throw new InvalidDataException("settlementPrice is a required property for Position and cannot be null");
            }
            else
            {
                this.SettlementPrice = settlementPrice;
            }
            
            // to ensure "indexPrice" is required (not null)
            if (indexPrice == null)
            {
                throw new InvalidDataException("indexPrice is a required property for Position and cannot be null");
            }
            else
            {
                this.IndexPrice = indexPrice;
            }
            
            // to ensure "instrumentName" is required (not null)
            if (instrumentName == null)
            {
                throw new InvalidDataException("instrumentName is a required property for Position and cannot be null");
            }
            else
            {
                this.InstrumentName = instrumentName;
            }
            
            this.AveragePriceUsd = averagePriceUsd;
            this.EstimatedLiquidationPrice = estimatedLiquidationPrice;
            this.FloatingProfitLossUsd = floatingProfitLossUsd;
            this.RealizedProfitLoss = realizedProfitLoss;
            this.SizeCurrency = sizeCurrency;
        }
        

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
        [DataMember(Name="open_orders_margin", EmitDefaultValue=false)]
        public decimal? OpenOrdersMargin { get; set; }

        /// <summary>
        /// Profit or loss from position
        /// </summary>
        /// <value>Profit or loss from position</value>
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
        [DataMember(Name="delta", EmitDefaultValue=false)]
        public decimal? Delta { get; set; }

        /// <summary>
        /// Initial margin
        /// </summary>
        /// <value>Initial margin</value>
        [DataMember(Name="initial_margin", EmitDefaultValue=false)]
        public decimal? InitialMargin { get; set; }

        /// <summary>
        /// Position size for futures size in quote currency (e.g. USD), for options size is in base currency (e.g. BTC)
        /// </summary>
        /// <value>Position size for futures size in quote currency (e.g. USD), for options size is in base currency (e.g. BTC)</value>
        [DataMember(Name="size", EmitDefaultValue=false)]
        public decimal? Size { get; set; }

        /// <summary>
        /// Maintenance margin
        /// </summary>
        /// <value>Maintenance margin</value>
        [DataMember(Name="maintenance_margin", EmitDefaultValue=false)]
        public decimal? MaintenanceMargin { get; set; }


        /// <summary>
        /// Current mark price for position&#39;s instrument
        /// </summary>
        /// <value>Current mark price for position&#39;s instrument</value>
        [DataMember(Name="mark_price", EmitDefaultValue=false)]
        public decimal? MarkPrice { get; set; }

        /// <summary>
        /// Average price of trades that built this position
        /// </summary>
        /// <value>Average price of trades that built this position</value>
        [DataMember(Name="average_price", EmitDefaultValue=false)]
        public decimal? AveragePrice { get; set; }

        /// <summary>
        /// Last settlement price for position&#39;s instrument 0 if instrument wasn&#39;t settled yet
        /// </summary>
        /// <value>Last settlement price for position&#39;s instrument 0 if instrument wasn&#39;t settled yet</value>
        [DataMember(Name="settlement_price", EmitDefaultValue=false)]
        public decimal? SettlementPrice { get; set; }

        /// <summary>
        /// Current index price
        /// </summary>
        /// <value>Current index price</value>
        [DataMember(Name="index_price", EmitDefaultValue=false)]
        public decimal? IndexPrice { get; set; }

        /// <summary>
        /// Unique instrument identifier
        /// </summary>
        /// <value>Unique instrument identifier</value>
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
            return this.Equals(input as Position);
        }

        /// <summary>
        /// Returns true if Position instances are equal
        /// </summary>
        /// <param name="input">Instance of Position to be compared</param>
        /// <returns>Boolean</returns>
        public bool Equals(Position input)
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
                    this.AveragePriceUsd == input.AveragePriceUsd ||
                    (this.AveragePriceUsd != null &&
                    this.AveragePriceUsd.Equals(input.AveragePriceUsd))
                ) && 
                (
                    this.EstimatedLiquidationPrice == input.EstimatedLiquidationPrice ||
                    (this.EstimatedLiquidationPrice != null &&
                    this.EstimatedLiquidationPrice.Equals(input.EstimatedLiquidationPrice))
                ) && 
                (
                    this.FloatingProfitLoss == input.FloatingProfitLoss ||
                    (this.FloatingProfitLoss != null &&
                    this.FloatingProfitLoss.Equals(input.FloatingProfitLoss))
                ) && 
                (
                    this.FloatingProfitLossUsd == input.FloatingProfitLossUsd ||
                    (this.FloatingProfitLossUsd != null &&
                    this.FloatingProfitLossUsd.Equals(input.FloatingProfitLossUsd))
                ) && 
                (
                    this.OpenOrdersMargin == input.OpenOrdersMargin ||
                    (this.OpenOrdersMargin != null &&
                    this.OpenOrdersMargin.Equals(input.OpenOrdersMargin))
                ) && 
                (
                    this.TotalProfitLoss == input.TotalProfitLoss ||
                    (this.TotalProfitLoss != null &&
                    this.TotalProfitLoss.Equals(input.TotalProfitLoss))
                ) && 
                (
                    this.RealizedProfitLoss == input.RealizedProfitLoss ||
                    (this.RealizedProfitLoss != null &&
                    this.RealizedProfitLoss.Equals(input.RealizedProfitLoss))
                ) && 
                (
                    this.Delta == input.Delta ||
                    (this.Delta != null &&
                    this.Delta.Equals(input.Delta))
                ) && 
                (
                    this.InitialMargin == input.InitialMargin ||
                    (this.InitialMargin != null &&
                    this.InitialMargin.Equals(input.InitialMargin))
                ) && 
                (
                    this.Size == input.Size ||
                    (this.Size != null &&
                    this.Size.Equals(input.Size))
                ) && 
                (
                    this.MaintenanceMargin == input.MaintenanceMargin ||
                    (this.MaintenanceMargin != null &&
                    this.MaintenanceMargin.Equals(input.MaintenanceMargin))
                ) && 
                (
                    this.Kind == input.Kind ||
                    (this.Kind != null &&
                    this.Kind.Equals(input.Kind))
                ) && 
                (
                    this.MarkPrice == input.MarkPrice ||
                    (this.MarkPrice != null &&
                    this.MarkPrice.Equals(input.MarkPrice))
                ) && 
                (
                    this.AveragePrice == input.AveragePrice ||
                    (this.AveragePrice != null &&
                    this.AveragePrice.Equals(input.AveragePrice))
                ) && 
                (
                    this.SettlementPrice == input.SettlementPrice ||
                    (this.SettlementPrice != null &&
                    this.SettlementPrice.Equals(input.SettlementPrice))
                ) && 
                (
                    this.IndexPrice == input.IndexPrice ||
                    (this.IndexPrice != null &&
                    this.IndexPrice.Equals(input.IndexPrice))
                ) && 
                (
                    this.InstrumentName == input.InstrumentName ||
                    (this.InstrumentName != null &&
                    this.InstrumentName.Equals(input.InstrumentName))
                ) && 
                (
                    this.SizeCurrency == input.SizeCurrency ||
                    (this.SizeCurrency != null &&
                    this.SizeCurrency.Equals(input.SizeCurrency))
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
                if (this.AveragePriceUsd != null)
                    hashCode = hashCode * 59 + this.AveragePriceUsd.GetHashCode();
                if (this.EstimatedLiquidationPrice != null)
                    hashCode = hashCode * 59 + this.EstimatedLiquidationPrice.GetHashCode();
                if (this.FloatingProfitLoss != null)
                    hashCode = hashCode * 59 + this.FloatingProfitLoss.GetHashCode();
                if (this.FloatingProfitLossUsd != null)
                    hashCode = hashCode * 59 + this.FloatingProfitLossUsd.GetHashCode();
                if (this.OpenOrdersMargin != null)
                    hashCode = hashCode * 59 + this.OpenOrdersMargin.GetHashCode();
                if (this.TotalProfitLoss != null)
                    hashCode = hashCode * 59 + this.TotalProfitLoss.GetHashCode();
                if (this.RealizedProfitLoss != null)
                    hashCode = hashCode * 59 + this.RealizedProfitLoss.GetHashCode();
                if (this.Delta != null)
                    hashCode = hashCode * 59 + this.Delta.GetHashCode();
                if (this.InitialMargin != null)
                    hashCode = hashCode * 59 + this.InitialMargin.GetHashCode();
                if (this.Size != null)
                    hashCode = hashCode * 59 + this.Size.GetHashCode();
                if (this.MaintenanceMargin != null)
                    hashCode = hashCode * 59 + this.MaintenanceMargin.GetHashCode();
                if (this.Kind != null)
                    hashCode = hashCode * 59 + this.Kind.GetHashCode();
                if (this.MarkPrice != null)
                    hashCode = hashCode * 59 + this.MarkPrice.GetHashCode();
                if (this.AveragePrice != null)
                    hashCode = hashCode * 59 + this.AveragePrice.GetHashCode();
                if (this.SettlementPrice != null)
                    hashCode = hashCode * 59 + this.SettlementPrice.GetHashCode();
                if (this.IndexPrice != null)
                    hashCode = hashCode * 59 + this.IndexPrice.GetHashCode();
                if (this.InstrumentName != null)
                    hashCode = hashCode * 59 + this.InstrumentName.GetHashCode();
                if (this.SizeCurrency != null)
                    hashCode = hashCode * 59 + this.SizeCurrency.GetHashCode();
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
