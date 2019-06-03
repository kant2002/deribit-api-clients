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
    public partial class BookSummary : IEquatable<BookSummary>
    { 
        /// <summary>
        /// Name of the underlying future, or &#x60;&#39;index_price&#39;&#x60; (options only)
        /// </summary>
        /// <value>Name of the underlying future, or &#x60;&#39;index_price&#39;&#x60; (options only)</value>
        [DataMember(Name="underlying_index", EmitDefaultValue=false)]
        public string UnderlyingIndex { get; set; }

        /// <summary>
        /// The total 24h traded volume (in base currency)
        /// </summary>
        /// <value>The total 24h traded volume (in base currency)</value>
        [Required]
        [DataMember(Name="volume", EmitDefaultValue=false)]
        public decimal? Volume { get; set; }

        /// <summary>
        /// Volume in usd (futures only)
        /// </summary>
        /// <value>Volume in usd (futures only)</value>
        [DataMember(Name="volume_usd", EmitDefaultValue=false)]
        public decimal? VolumeUsd { get; set; }

        /// <summary>
        /// underlying price for implied volatility calculations (options only)
        /// </summary>
        /// <value>underlying price for implied volatility calculations (options only)</value>
        [DataMember(Name="underlying_price", EmitDefaultValue=false)]
        public decimal? UnderlyingPrice { get; set; }

        /// <summary>
        /// The current best bid price, &#x60;null&#x60; if there aren&#39;t any bids
        /// </summary>
        /// <value>The current best bid price, &#x60;null&#x60; if there aren&#39;t any bids</value>
        [Required]
        [DataMember(Name="bid_price", EmitDefaultValue=false)]
        public decimal? BidPrice { get; set; }

        /// <summary>
        /// The total amount of outstanding contracts in the corresponding amount units. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH.
        /// </summary>
        /// <value>The total amount of outstanding contracts in the corresponding amount units. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH.</value>
        [Required]
        [DataMember(Name="open_interest", EmitDefaultValue=false)]
        public decimal? OpenInterest { get; set; }

        /// <summary>
        /// Quote currency
        /// </summary>
        /// <value>Quote currency</value>
        [Required]
        [DataMember(Name="quote_currency", EmitDefaultValue=false)]
        public string QuoteCurrency { get; set; }

        /// <summary>
        /// Price of the 24h highest trade
        /// </summary>
        /// <value>Price of the 24h highest trade</value>
        [Required]
        [DataMember(Name="high", EmitDefaultValue=false)]
        public decimal? High { get; set; }

        /// <summary>
        /// Estimated delivery price, in USD (futures only). For more details, see Documentation &gt; General &gt; Expiration Price
        /// </summary>
        /// <value>Estimated delivery price, in USD (futures only). For more details, see Documentation &gt; General &gt; Expiration Price</value>
        [DataMember(Name="estimated_delivery_price", EmitDefaultValue=false)]
        public decimal? EstimatedDeliveryPrice { get; set; }

        /// <summary>
        /// The price of the latest trade, &#x60;null&#x60; if there weren&#39;t any trades
        /// </summary>
        /// <value>The price of the latest trade, &#x60;null&#x60; if there weren&#39;t any trades</value>
        [Required]
        [DataMember(Name="last", EmitDefaultValue=false)]
        public decimal? Last { get; set; }

        /// <summary>
        /// The average of the best bid and ask, &#x60;null&#x60; if there aren&#39;t any asks or bids
        /// </summary>
        /// <value>The average of the best bid and ask, &#x60;null&#x60; if there aren&#39;t any asks or bids</value>
        [Required]
        [DataMember(Name="mid_price", EmitDefaultValue=false)]
        public decimal? MidPrice { get; set; }

        /// <summary>
        /// Interest rate used in implied volatility calculations (options only)
        /// </summary>
        /// <value>Interest rate used in implied volatility calculations (options only)</value>
        [DataMember(Name="interest_rate", EmitDefaultValue=false)]
        public decimal? InterestRate { get; set; }

        /// <summary>
        /// Funding 8h (perpetual only)
        /// </summary>
        /// <value>Funding 8h (perpetual only)</value>
        [DataMember(Name="funding_8h", EmitDefaultValue=false)]
        public decimal? Funding8h { get; set; }

        /// <summary>
        /// The current instrument market price
        /// </summary>
        /// <value>The current instrument market price</value>
        [Required]
        [DataMember(Name="mark_price", EmitDefaultValue=false)]
        public decimal? MarkPrice { get; set; }

        /// <summary>
        /// The current best ask price, &#x60;null&#x60; if there aren&#39;t any asks
        /// </summary>
        /// <value>The current best ask price, &#x60;null&#x60; if there aren&#39;t any asks</value>
        [Required]
        [DataMember(Name="ask_price", EmitDefaultValue=false)]
        public decimal? AskPrice { get; set; }

        /// <summary>
        /// Unique instrument identifier
        /// </summary>
        /// <value>Unique instrument identifier</value>
        [Required]
        [DataMember(Name="instrument_name", EmitDefaultValue=false)]
        public string InstrumentName { get; set; }

        /// <summary>
        /// Price of the 24h lowest trade, &#x60;null&#x60; if there weren&#39;t any trades
        /// </summary>
        /// <value>Price of the 24h lowest trade, &#x60;null&#x60; if there weren&#39;t any trades</value>
        [Required]
        [DataMember(Name="low", EmitDefaultValue=false)]
        public decimal? Low { get; set; }

        /// <summary>
        /// Base currency
        /// </summary>
        /// <value>Base currency</value>
        [DataMember(Name="base_currency", EmitDefaultValue=false)]
        public string BaseCurrency { get; set; }

        /// <summary>
        /// The timestamp (seconds since the Unix epoch, with millisecond precision)
        /// </summary>
        /// <value>The timestamp (seconds since the Unix epoch, with millisecond precision)</value>
        [Required]
        [DataMember(Name="creation_timestamp", EmitDefaultValue=false)]
        public int? CreationTimestamp { get; set; }

        /// <summary>
        /// Current funding (perpetual only)
        /// </summary>
        /// <value>Current funding (perpetual only)</value>
        [DataMember(Name="current_funding", EmitDefaultValue=false)]
        public decimal? CurrentFunding { get; set; }

        /// <summary>
        /// Returns the string presentation of the object
        /// </summary>
        /// <returns>String presentation of the object</returns>
        public override string ToString()
        {
            var sb = new StringBuilder();
            sb.Append("class BookSummary {\n");
            sb.Append("  UnderlyingIndex: ").Append(UnderlyingIndex).Append("\n");
            sb.Append("  Volume: ").Append(Volume).Append("\n");
            sb.Append("  VolumeUsd: ").Append(VolumeUsd).Append("\n");
            sb.Append("  UnderlyingPrice: ").Append(UnderlyingPrice).Append("\n");
            sb.Append("  BidPrice: ").Append(BidPrice).Append("\n");
            sb.Append("  OpenInterest: ").Append(OpenInterest).Append("\n");
            sb.Append("  QuoteCurrency: ").Append(QuoteCurrency).Append("\n");
            sb.Append("  High: ").Append(High).Append("\n");
            sb.Append("  EstimatedDeliveryPrice: ").Append(EstimatedDeliveryPrice).Append("\n");
            sb.Append("  Last: ").Append(Last).Append("\n");
            sb.Append("  MidPrice: ").Append(MidPrice).Append("\n");
            sb.Append("  InterestRate: ").Append(InterestRate).Append("\n");
            sb.Append("  Funding8h: ").Append(Funding8h).Append("\n");
            sb.Append("  MarkPrice: ").Append(MarkPrice).Append("\n");
            sb.Append("  AskPrice: ").Append(AskPrice).Append("\n");
            sb.Append("  InstrumentName: ").Append(InstrumentName).Append("\n");
            sb.Append("  Low: ").Append(Low).Append("\n");
            sb.Append("  BaseCurrency: ").Append(BaseCurrency).Append("\n");
            sb.Append("  CreationTimestamp: ").Append(CreationTimestamp).Append("\n");
            sb.Append("  CurrentFunding: ").Append(CurrentFunding).Append("\n");
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
            return obj.GetType() == GetType() && Equals((BookSummary)obj);
        }

        /// <summary>
        /// Returns true if BookSummary instances are equal
        /// </summary>
        /// <param name="other">Instance of BookSummary to be compared</param>
        /// <returns>Boolean</returns>
        public bool Equals(BookSummary other)
        {
            if (other is null) return false;
            if (ReferenceEquals(this, other)) return true;

            return 
                (
                    UnderlyingIndex == other.UnderlyingIndex ||
                    UnderlyingIndex != null &&
                    UnderlyingIndex.Equals(other.UnderlyingIndex)
                ) && 
                (
                    Volume == other.Volume ||
                    Volume != null &&
                    Volume.Equals(other.Volume)
                ) && 
                (
                    VolumeUsd == other.VolumeUsd ||
                    VolumeUsd != null &&
                    VolumeUsd.Equals(other.VolumeUsd)
                ) && 
                (
                    UnderlyingPrice == other.UnderlyingPrice ||
                    UnderlyingPrice != null &&
                    UnderlyingPrice.Equals(other.UnderlyingPrice)
                ) && 
                (
                    BidPrice == other.BidPrice ||
                    BidPrice != null &&
                    BidPrice.Equals(other.BidPrice)
                ) && 
                (
                    OpenInterest == other.OpenInterest ||
                    OpenInterest != null &&
                    OpenInterest.Equals(other.OpenInterest)
                ) && 
                (
                    QuoteCurrency == other.QuoteCurrency ||
                    QuoteCurrency != null &&
                    QuoteCurrency.Equals(other.QuoteCurrency)
                ) && 
                (
                    High == other.High ||
                    High != null &&
                    High.Equals(other.High)
                ) && 
                (
                    EstimatedDeliveryPrice == other.EstimatedDeliveryPrice ||
                    EstimatedDeliveryPrice != null &&
                    EstimatedDeliveryPrice.Equals(other.EstimatedDeliveryPrice)
                ) && 
                (
                    Last == other.Last ||
                    Last != null &&
                    Last.Equals(other.Last)
                ) && 
                (
                    MidPrice == other.MidPrice ||
                    MidPrice != null &&
                    MidPrice.Equals(other.MidPrice)
                ) && 
                (
                    InterestRate == other.InterestRate ||
                    InterestRate != null &&
                    InterestRate.Equals(other.InterestRate)
                ) && 
                (
                    Funding8h == other.Funding8h ||
                    Funding8h != null &&
                    Funding8h.Equals(other.Funding8h)
                ) && 
                (
                    MarkPrice == other.MarkPrice ||
                    MarkPrice != null &&
                    MarkPrice.Equals(other.MarkPrice)
                ) && 
                (
                    AskPrice == other.AskPrice ||
                    AskPrice != null &&
                    AskPrice.Equals(other.AskPrice)
                ) && 
                (
                    InstrumentName == other.InstrumentName ||
                    InstrumentName != null &&
                    InstrumentName.Equals(other.InstrumentName)
                ) && 
                (
                    Low == other.Low ||
                    Low != null &&
                    Low.Equals(other.Low)
                ) && 
                (
                    BaseCurrency == other.BaseCurrency ||
                    BaseCurrency != null &&
                    BaseCurrency.Equals(other.BaseCurrency)
                ) && 
                (
                    CreationTimestamp == other.CreationTimestamp ||
                    CreationTimestamp != null &&
                    CreationTimestamp.Equals(other.CreationTimestamp)
                ) && 
                (
                    CurrentFunding == other.CurrentFunding ||
                    CurrentFunding != null &&
                    CurrentFunding.Equals(other.CurrentFunding)
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
                    if (UnderlyingIndex != null)
                    hashCode = hashCode * 59 + UnderlyingIndex.GetHashCode();
                    if (Volume != null)
                    hashCode = hashCode * 59 + Volume.GetHashCode();
                    if (VolumeUsd != null)
                    hashCode = hashCode * 59 + VolumeUsd.GetHashCode();
                    if (UnderlyingPrice != null)
                    hashCode = hashCode * 59 + UnderlyingPrice.GetHashCode();
                    if (BidPrice != null)
                    hashCode = hashCode * 59 + BidPrice.GetHashCode();
                    if (OpenInterest != null)
                    hashCode = hashCode * 59 + OpenInterest.GetHashCode();
                    if (QuoteCurrency != null)
                    hashCode = hashCode * 59 + QuoteCurrency.GetHashCode();
                    if (High != null)
                    hashCode = hashCode * 59 + High.GetHashCode();
                    if (EstimatedDeliveryPrice != null)
                    hashCode = hashCode * 59 + EstimatedDeliveryPrice.GetHashCode();
                    if (Last != null)
                    hashCode = hashCode * 59 + Last.GetHashCode();
                    if (MidPrice != null)
                    hashCode = hashCode * 59 + MidPrice.GetHashCode();
                    if (InterestRate != null)
                    hashCode = hashCode * 59 + InterestRate.GetHashCode();
                    if (Funding8h != null)
                    hashCode = hashCode * 59 + Funding8h.GetHashCode();
                    if (MarkPrice != null)
                    hashCode = hashCode * 59 + MarkPrice.GetHashCode();
                    if (AskPrice != null)
                    hashCode = hashCode * 59 + AskPrice.GetHashCode();
                    if (InstrumentName != null)
                    hashCode = hashCode * 59 + InstrumentName.GetHashCode();
                    if (Low != null)
                    hashCode = hashCode * 59 + Low.GetHashCode();
                    if (BaseCurrency != null)
                    hashCode = hashCode * 59 + BaseCurrency.GetHashCode();
                    if (CreationTimestamp != null)
                    hashCode = hashCode * 59 + CreationTimestamp.GetHashCode();
                    if (CurrentFunding != null)
                    hashCode = hashCode * 59 + CurrentFunding.GetHashCode();
                return hashCode;
            }
        }

        #region Operators
        #pragma warning disable 1591

        public static bool operator ==(BookSummary left, BookSummary right)
        {
            return Equals(left, right);
        }

        public static bool operator !=(BookSummary left, BookSummary right)
        {
            return !Equals(left, right);
        }

        #pragma warning restore 1591
        #endregion Operators
    }
}
