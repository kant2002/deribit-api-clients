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
    public partial class Instrument : IEquatable<Instrument>
    { 
        /// <summary>
        /// The currency in which the instrument prices are quoted.
        /// </summary>
        /// <value>The currency in which the instrument prices are quoted.</value>
        [JsonConverter(typeof(Newtonsoft.Json.Converters.StringEnumConverter))]
        public enum QuoteCurrencyEnum
        {
            
            /// <summary>
            /// Enum USDEnum for USD
            /// </summary>
            [EnumMember(Value = "USD")]
            USDEnum = 1
        }

        /// <summary>
        /// The currency in which the instrument prices are quoted.
        /// </summary>
        /// <value>The currency in which the instrument prices are quoted.</value>
        [Required]
        [DataMember(Name="quote_currency", EmitDefaultValue=false)]
        public QuoteCurrencyEnum? QuoteCurrency { get; set; }

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
        /// specifies minimal price change and, as follows, the number of decimal places for instrument prices
        /// </summary>
        /// <value>specifies minimal price change and, as follows, the number of decimal places for instrument prices</value>
        [Required]
        [DataMember(Name="tick_size", EmitDefaultValue=false)]
        public decimal? TickSize { get; set; }

        /// <summary>
        /// Contract size for instrument
        /// </summary>
        /// <value>Contract size for instrument</value>
        [Required]
        [DataMember(Name="contract_size", EmitDefaultValue=false)]
        public int? ContractSize { get; set; }

        /// <summary>
        /// Indicates if the instrument can currently be traded.
        /// </summary>
        /// <value>Indicates if the instrument can currently be traded.</value>
        [Required]
        [DataMember(Name="is_active", EmitDefaultValue=false)]
        public bool? IsActive { get; set; }

        /// <summary>
        /// The option type (only for options)
        /// </summary>
        /// <value>The option type (only for options)</value>
        [JsonConverter(typeof(Newtonsoft.Json.Converters.StringEnumConverter))]
        public enum OptionTypeEnum
        {
            
            /// <summary>
            /// Enum CallEnum for call
            /// </summary>
            [EnumMember(Value = "call")]
            CallEnum = 1,
            
            /// <summary>
            /// Enum PutEnum for put
            /// </summary>
            [EnumMember(Value = "put")]
            PutEnum = 2
        }

        /// <summary>
        /// The option type (only for options)
        /// </summary>
        /// <value>The option type (only for options)</value>
        [DataMember(Name="option_type", EmitDefaultValue=false)]
        public OptionTypeEnum? OptionType { get; set; }

        /// <summary>
        /// Minimum amount for trading. For perpetual and futures - in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH.
        /// </summary>
        /// <value>Minimum amount for trading. For perpetual and futures - in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH.</value>
        [Required]
        [DataMember(Name="min_trade_amount", EmitDefaultValue=false)]
        public decimal? MinTradeAmount { get; set; }

        /// <summary>
        /// Unique instrument identifier
        /// </summary>
        /// <value>Unique instrument identifier</value>
        [Required]
        [DataMember(Name="instrument_name", EmitDefaultValue=false)]
        public string InstrumentName { get; set; }

        /// <summary>
        /// The settlement period.
        /// </summary>
        /// <value>The settlement period.</value>
        [JsonConverter(typeof(Newtonsoft.Json.Converters.StringEnumConverter))]
        public enum SettlementPeriodEnum
        {
            
            /// <summary>
            /// Enum MonthEnum for month
            /// </summary>
            [EnumMember(Value = "month")]
            MonthEnum = 1,
            
            /// <summary>
            /// Enum WeekEnum for week
            /// </summary>
            [EnumMember(Value = "week")]
            WeekEnum = 2,
            
            /// <summary>
            /// Enum PerpetualEnum for perpetual
            /// </summary>
            [EnumMember(Value = "perpetual")]
            PerpetualEnum = 3
        }

        /// <summary>
        /// The settlement period.
        /// </summary>
        /// <value>The settlement period.</value>
        [Required]
        [DataMember(Name="settlement_period", EmitDefaultValue=false)]
        public SettlementPeriodEnum? SettlementPeriod { get; set; }

        /// <summary>
        /// The strike value. (only for options)
        /// </summary>
        /// <value>The strike value. (only for options)</value>
        [DataMember(Name="strike", EmitDefaultValue=false)]
        public decimal? Strike { get; set; }

        /// <summary>
        /// The underlying currency being traded.
        /// </summary>
        /// <value>The underlying currency being traded.</value>
        [JsonConverter(typeof(Newtonsoft.Json.Converters.StringEnumConverter))]
        public enum BaseCurrencyEnum
        {
            
            /// <summary>
            /// Enum BTCEnum for BTC
            /// </summary>
            [EnumMember(Value = "BTC")]
            BTCEnum = 1,
            
            /// <summary>
            /// Enum ETHEnum for ETH
            /// </summary>
            [EnumMember(Value = "ETH")]
            ETHEnum = 2
        }

        /// <summary>
        /// The underlying currency being traded.
        /// </summary>
        /// <value>The underlying currency being traded.</value>
        [Required]
        [DataMember(Name="base_currency", EmitDefaultValue=false)]
        public BaseCurrencyEnum? BaseCurrency { get; set; }

        /// <summary>
        /// The time when the instrument was first created (milliseconds)
        /// </summary>
        /// <value>The time when the instrument was first created (milliseconds)</value>
        [Required]
        [DataMember(Name="creation_timestamp", EmitDefaultValue=false)]
        public int? CreationTimestamp { get; set; }

        /// <summary>
        /// The time when the instrument will expire (milliseconds)
        /// </summary>
        /// <value>The time when the instrument will expire (milliseconds)</value>
        [Required]
        [DataMember(Name="expiration_timestamp", EmitDefaultValue=false)]
        public int? ExpirationTimestamp { get; set; }

        /// <summary>
        /// Returns the string presentation of the object
        /// </summary>
        /// <returns>String presentation of the object</returns>
        public override string ToString()
        {
            var sb = new StringBuilder();
            sb.Append("class Instrument {\n");
            sb.Append("  QuoteCurrency: ").Append(QuoteCurrency).Append("\n");
            sb.Append("  Kind: ").Append(Kind).Append("\n");
            sb.Append("  TickSize: ").Append(TickSize).Append("\n");
            sb.Append("  ContractSize: ").Append(ContractSize).Append("\n");
            sb.Append("  IsActive: ").Append(IsActive).Append("\n");
            sb.Append("  OptionType: ").Append(OptionType).Append("\n");
            sb.Append("  MinTradeAmount: ").Append(MinTradeAmount).Append("\n");
            sb.Append("  InstrumentName: ").Append(InstrumentName).Append("\n");
            sb.Append("  SettlementPeriod: ").Append(SettlementPeriod).Append("\n");
            sb.Append("  Strike: ").Append(Strike).Append("\n");
            sb.Append("  BaseCurrency: ").Append(BaseCurrency).Append("\n");
            sb.Append("  CreationTimestamp: ").Append(CreationTimestamp).Append("\n");
            sb.Append("  ExpirationTimestamp: ").Append(ExpirationTimestamp).Append("\n");
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
            return obj.GetType() == GetType() && Equals((Instrument)obj);
        }

        /// <summary>
        /// Returns true if Instrument instances are equal
        /// </summary>
        /// <param name="other">Instance of Instrument to be compared</param>
        /// <returns>Boolean</returns>
        public bool Equals(Instrument other)
        {
            if (other is null) return false;
            if (ReferenceEquals(this, other)) return true;

            return 
                (
                    QuoteCurrency == other.QuoteCurrency ||
                    
                    QuoteCurrency.Equals(other.QuoteCurrency)
                ) && 
                (
                    Kind == other.Kind ||
                    
                    Kind.Equals(other.Kind)
                ) && 
                (
                    TickSize == other.TickSize ||
                    TickSize != null &&
                    TickSize.Equals(other.TickSize)
                ) && 
                (
                    ContractSize == other.ContractSize ||
                    ContractSize != null &&
                    ContractSize.Equals(other.ContractSize)
                ) && 
                (
                    IsActive == other.IsActive ||
                    IsActive != null &&
                    IsActive.Equals(other.IsActive)
                ) && 
                (
                    OptionType == other.OptionType ||
                    
                    OptionType.Equals(other.OptionType)
                ) && 
                (
                    MinTradeAmount == other.MinTradeAmount ||
                    MinTradeAmount != null &&
                    MinTradeAmount.Equals(other.MinTradeAmount)
                ) && 
                (
                    InstrumentName == other.InstrumentName ||
                    InstrumentName != null &&
                    InstrumentName.Equals(other.InstrumentName)
                ) && 
                (
                    SettlementPeriod == other.SettlementPeriod ||
                    
                    SettlementPeriod.Equals(other.SettlementPeriod)
                ) && 
                (
                    Strike == other.Strike ||
                    Strike != null &&
                    Strike.Equals(other.Strike)
                ) && 
                (
                    BaseCurrency == other.BaseCurrency ||
                    
                    BaseCurrency.Equals(other.BaseCurrency)
                ) && 
                (
                    CreationTimestamp == other.CreationTimestamp ||
                    CreationTimestamp != null &&
                    CreationTimestamp.Equals(other.CreationTimestamp)
                ) && 
                (
                    ExpirationTimestamp == other.ExpirationTimestamp ||
                    ExpirationTimestamp != null &&
                    ExpirationTimestamp.Equals(other.ExpirationTimestamp)
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
                    
                    hashCode = hashCode * 59 + QuoteCurrency.GetHashCode();
                    
                    hashCode = hashCode * 59 + Kind.GetHashCode();
                    if (TickSize != null)
                    hashCode = hashCode * 59 + TickSize.GetHashCode();
                    if (ContractSize != null)
                    hashCode = hashCode * 59 + ContractSize.GetHashCode();
                    if (IsActive != null)
                    hashCode = hashCode * 59 + IsActive.GetHashCode();
                    
                    hashCode = hashCode * 59 + OptionType.GetHashCode();
                    if (MinTradeAmount != null)
                    hashCode = hashCode * 59 + MinTradeAmount.GetHashCode();
                    if (InstrumentName != null)
                    hashCode = hashCode * 59 + InstrumentName.GetHashCode();
                    
                    hashCode = hashCode * 59 + SettlementPeriod.GetHashCode();
                    if (Strike != null)
                    hashCode = hashCode * 59 + Strike.GetHashCode();
                    
                    hashCode = hashCode * 59 + BaseCurrency.GetHashCode();
                    if (CreationTimestamp != null)
                    hashCode = hashCode * 59 + CreationTimestamp.GetHashCode();
                    if (ExpirationTimestamp != null)
                    hashCode = hashCode * 59 + ExpirationTimestamp.GetHashCode();
                return hashCode;
            }
        }

        #region Operators
        #pragma warning disable 1591

        public static bool operator ==(Instrument left, Instrument right)
        {
            return Equals(left, right);
        }

        public static bool operator !=(Instrument left, Instrument right)
        {
            return !Equals(left, right);
        }

        #pragma warning restore 1591
        #endregion Operators
    }
}
