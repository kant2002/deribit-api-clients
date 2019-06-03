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
    /// Instrument
    /// </summary>
    [DataContract]
    public partial class Instrument :  IEquatable<Instrument>, IValidatableObject
    {
        /// <summary>
        /// The currency in which the instrument prices are quoted.
        /// </summary>
        /// <value>The currency in which the instrument prices are quoted.</value>
        [JsonConverter(typeof(StringEnumConverter))]
        public enum QuoteCurrencyEnum
        {
            /// <summary>
            /// Enum USD for value: USD
            /// </summary>
            [EnumMember(Value = "USD")]
            USD = 1

        }

        /// <summary>
        /// The currency in which the instrument prices are quoted.
        /// </summary>
        /// <value>The currency in which the instrument prices are quoted.</value>
        [DataMember(Name="quote_currency", EmitDefaultValue=false)]
        public QuoteCurrencyEnum QuoteCurrency { get; set; }
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
        /// The option type (only for options)
        /// </summary>
        /// <value>The option type (only for options)</value>
        [JsonConverter(typeof(StringEnumConverter))]
        public enum OptionTypeEnum
        {
            /// <summary>
            /// Enum Call for value: call
            /// </summary>
            [EnumMember(Value = "call")]
            Call = 1,

            /// <summary>
            /// Enum Put for value: put
            /// </summary>
            [EnumMember(Value = "put")]
            Put = 2

        }

        /// <summary>
        /// The option type (only for options)
        /// </summary>
        /// <value>The option type (only for options)</value>
        [DataMember(Name="option_type", EmitDefaultValue=false)]
        public OptionTypeEnum? OptionType { get; set; }
        /// <summary>
        /// The settlement period.
        /// </summary>
        /// <value>The settlement period.</value>
        [JsonConverter(typeof(StringEnumConverter))]
        public enum SettlementPeriodEnum
        {
            /// <summary>
            /// Enum Month for value: month
            /// </summary>
            [EnumMember(Value = "month")]
            Month = 1,

            /// <summary>
            /// Enum Week for value: week
            /// </summary>
            [EnumMember(Value = "week")]
            Week = 2,

            /// <summary>
            /// Enum Perpetual for value: perpetual
            /// </summary>
            [EnumMember(Value = "perpetual")]
            Perpetual = 3

        }

        /// <summary>
        /// The settlement period.
        /// </summary>
        /// <value>The settlement period.</value>
        [DataMember(Name="settlement_period", EmitDefaultValue=false)]
        public SettlementPeriodEnum SettlementPeriod { get; set; }
        /// <summary>
        /// The underlying currency being traded.
        /// </summary>
        /// <value>The underlying currency being traded.</value>
        [JsonConverter(typeof(StringEnumConverter))]
        public enum BaseCurrencyEnum
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
        /// The underlying currency being traded.
        /// </summary>
        /// <value>The underlying currency being traded.</value>
        [DataMember(Name="base_currency", EmitDefaultValue=false)]
        public BaseCurrencyEnum BaseCurrency { get; set; }
        /// <summary>
        /// Initializes a new instance of the <see cref="Instrument" /> class.
        /// </summary>
        [JsonConstructorAttribute]
        protected Instrument() { }
        /// <summary>
        /// Initializes a new instance of the <see cref="Instrument" /> class.
        /// </summary>
        /// <param name="quoteCurrency">The currency in which the instrument prices are quoted. (required).</param>
        /// <param name="kind">Instrument kind, &#x60;\&quot;future\&quot;&#x60; or &#x60;\&quot;option\&quot;&#x60; (required).</param>
        /// <param name="tickSize">specifies minimal price change and, as follows, the number of decimal places for instrument prices (required).</param>
        /// <param name="contractSize">Contract size for instrument (required).</param>
        /// <param name="isActive">Indicates if the instrument can currently be traded. (required).</param>
        /// <param name="optionType">The option type (only for options).</param>
        /// <param name="minTradeAmount">Minimum amount for trading. For perpetual and futures - in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH. (required).</param>
        /// <param name="instrumentName">Unique instrument identifier (required).</param>
        /// <param name="settlementPeriod">The settlement period. (required).</param>
        /// <param name="strike">The strike value. (only for options).</param>
        /// <param name="baseCurrency">The underlying currency being traded. (required).</param>
        /// <param name="creationTimestamp">The time when the instrument was first created (milliseconds) (required).</param>
        /// <param name="expirationTimestamp">The time when the instrument will expire (milliseconds) (required).</param>
        public Instrument(QuoteCurrencyEnum quoteCurrency = default(QuoteCurrencyEnum), KindEnum kind = default(KindEnum), decimal? tickSize = default(decimal?), int? contractSize = default(int?), bool? isActive = default(bool?), OptionTypeEnum? optionType = default(OptionTypeEnum?), decimal? minTradeAmount = default(decimal?), string instrumentName = default(string), SettlementPeriodEnum settlementPeriod = default(SettlementPeriodEnum), decimal? strike = default(decimal?), BaseCurrencyEnum baseCurrency = default(BaseCurrencyEnum), int? creationTimestamp = default(int?), int? expirationTimestamp = default(int?))
        {
            // to ensure "quoteCurrency" is required (not null)
            if (quoteCurrency == null)
            {
                throw new InvalidDataException("quoteCurrency is a required property for Instrument and cannot be null");
            }
            else
            {
                this.QuoteCurrency = quoteCurrency;
            }
            
            // to ensure "kind" is required (not null)
            if (kind == null)
            {
                throw new InvalidDataException("kind is a required property for Instrument and cannot be null");
            }
            else
            {
                this.Kind = kind;
            }
            
            // to ensure "tickSize" is required (not null)
            if (tickSize == null)
            {
                throw new InvalidDataException("tickSize is a required property for Instrument and cannot be null");
            }
            else
            {
                this.TickSize = tickSize;
            }
            
            // to ensure "contractSize" is required (not null)
            if (contractSize == null)
            {
                throw new InvalidDataException("contractSize is a required property for Instrument and cannot be null");
            }
            else
            {
                this.ContractSize = contractSize;
            }
            
            // to ensure "isActive" is required (not null)
            if (isActive == null)
            {
                throw new InvalidDataException("isActive is a required property for Instrument and cannot be null");
            }
            else
            {
                this.IsActive = isActive;
            }
            
            // to ensure "minTradeAmount" is required (not null)
            if (minTradeAmount == null)
            {
                throw new InvalidDataException("minTradeAmount is a required property for Instrument and cannot be null");
            }
            else
            {
                this.MinTradeAmount = minTradeAmount;
            }
            
            // to ensure "instrumentName" is required (not null)
            if (instrumentName == null)
            {
                throw new InvalidDataException("instrumentName is a required property for Instrument and cannot be null");
            }
            else
            {
                this.InstrumentName = instrumentName;
            }
            
            // to ensure "settlementPeriod" is required (not null)
            if (settlementPeriod == null)
            {
                throw new InvalidDataException("settlementPeriod is a required property for Instrument and cannot be null");
            }
            else
            {
                this.SettlementPeriod = settlementPeriod;
            }
            
            // to ensure "baseCurrency" is required (not null)
            if (baseCurrency == null)
            {
                throw new InvalidDataException("baseCurrency is a required property for Instrument and cannot be null");
            }
            else
            {
                this.BaseCurrency = baseCurrency;
            }
            
            // to ensure "creationTimestamp" is required (not null)
            if (creationTimestamp == null)
            {
                throw new InvalidDataException("creationTimestamp is a required property for Instrument and cannot be null");
            }
            else
            {
                this.CreationTimestamp = creationTimestamp;
            }
            
            // to ensure "expirationTimestamp" is required (not null)
            if (expirationTimestamp == null)
            {
                throw new InvalidDataException("expirationTimestamp is a required property for Instrument and cannot be null");
            }
            else
            {
                this.ExpirationTimestamp = expirationTimestamp;
            }
            
            this.OptionType = optionType;
            this.Strike = strike;
        }
        


        /// <summary>
        /// specifies minimal price change and, as follows, the number of decimal places for instrument prices
        /// </summary>
        /// <value>specifies minimal price change and, as follows, the number of decimal places for instrument prices</value>
        [DataMember(Name="tick_size", EmitDefaultValue=false)]
        public decimal? TickSize { get; set; }

        /// <summary>
        /// Contract size for instrument
        /// </summary>
        /// <value>Contract size for instrument</value>
        [DataMember(Name="contract_size", EmitDefaultValue=false)]
        public int? ContractSize { get; set; }

        /// <summary>
        /// Indicates if the instrument can currently be traded.
        /// </summary>
        /// <value>Indicates if the instrument can currently be traded.</value>
        [DataMember(Name="is_active", EmitDefaultValue=false)]
        public bool? IsActive { get; set; }


        /// <summary>
        /// Minimum amount for trading. For perpetual and futures - in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH.
        /// </summary>
        /// <value>Minimum amount for trading. For perpetual and futures - in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH.</value>
        [DataMember(Name="min_trade_amount", EmitDefaultValue=false)]
        public decimal? MinTradeAmount { get; set; }

        /// <summary>
        /// Unique instrument identifier
        /// </summary>
        /// <value>Unique instrument identifier</value>
        [DataMember(Name="instrument_name", EmitDefaultValue=false)]
        public string InstrumentName { get; set; }


        /// <summary>
        /// The strike value. (only for options)
        /// </summary>
        /// <value>The strike value. (only for options)</value>
        [DataMember(Name="strike", EmitDefaultValue=false)]
        public decimal? Strike { get; set; }


        /// <summary>
        /// The time when the instrument was first created (milliseconds)
        /// </summary>
        /// <value>The time when the instrument was first created (milliseconds)</value>
        [DataMember(Name="creation_timestamp", EmitDefaultValue=false)]
        public int? CreationTimestamp { get; set; }

        /// <summary>
        /// The time when the instrument will expire (milliseconds)
        /// </summary>
        /// <value>The time when the instrument will expire (milliseconds)</value>
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
            return this.Equals(input as Instrument);
        }

        /// <summary>
        /// Returns true if Instrument instances are equal
        /// </summary>
        /// <param name="input">Instance of Instrument to be compared</param>
        /// <returns>Boolean</returns>
        public bool Equals(Instrument input)
        {
            if (input == null)
                return false;

            return 
                (
                    this.QuoteCurrency == input.QuoteCurrency ||
                    (this.QuoteCurrency != null &&
                    this.QuoteCurrency.Equals(input.QuoteCurrency))
                ) && 
                (
                    this.Kind == input.Kind ||
                    (this.Kind != null &&
                    this.Kind.Equals(input.Kind))
                ) && 
                (
                    this.TickSize == input.TickSize ||
                    (this.TickSize != null &&
                    this.TickSize.Equals(input.TickSize))
                ) && 
                (
                    this.ContractSize == input.ContractSize ||
                    (this.ContractSize != null &&
                    this.ContractSize.Equals(input.ContractSize))
                ) && 
                (
                    this.IsActive == input.IsActive ||
                    (this.IsActive != null &&
                    this.IsActive.Equals(input.IsActive))
                ) && 
                (
                    this.OptionType == input.OptionType ||
                    (this.OptionType != null &&
                    this.OptionType.Equals(input.OptionType))
                ) && 
                (
                    this.MinTradeAmount == input.MinTradeAmount ||
                    (this.MinTradeAmount != null &&
                    this.MinTradeAmount.Equals(input.MinTradeAmount))
                ) && 
                (
                    this.InstrumentName == input.InstrumentName ||
                    (this.InstrumentName != null &&
                    this.InstrumentName.Equals(input.InstrumentName))
                ) && 
                (
                    this.SettlementPeriod == input.SettlementPeriod ||
                    (this.SettlementPeriod != null &&
                    this.SettlementPeriod.Equals(input.SettlementPeriod))
                ) && 
                (
                    this.Strike == input.Strike ||
                    (this.Strike != null &&
                    this.Strike.Equals(input.Strike))
                ) && 
                (
                    this.BaseCurrency == input.BaseCurrency ||
                    (this.BaseCurrency != null &&
                    this.BaseCurrency.Equals(input.BaseCurrency))
                ) && 
                (
                    this.CreationTimestamp == input.CreationTimestamp ||
                    (this.CreationTimestamp != null &&
                    this.CreationTimestamp.Equals(input.CreationTimestamp))
                ) && 
                (
                    this.ExpirationTimestamp == input.ExpirationTimestamp ||
                    (this.ExpirationTimestamp != null &&
                    this.ExpirationTimestamp.Equals(input.ExpirationTimestamp))
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
                if (this.QuoteCurrency != null)
                    hashCode = hashCode * 59 + this.QuoteCurrency.GetHashCode();
                if (this.Kind != null)
                    hashCode = hashCode * 59 + this.Kind.GetHashCode();
                if (this.TickSize != null)
                    hashCode = hashCode * 59 + this.TickSize.GetHashCode();
                if (this.ContractSize != null)
                    hashCode = hashCode * 59 + this.ContractSize.GetHashCode();
                if (this.IsActive != null)
                    hashCode = hashCode * 59 + this.IsActive.GetHashCode();
                if (this.OptionType != null)
                    hashCode = hashCode * 59 + this.OptionType.GetHashCode();
                if (this.MinTradeAmount != null)
                    hashCode = hashCode * 59 + this.MinTradeAmount.GetHashCode();
                if (this.InstrumentName != null)
                    hashCode = hashCode * 59 + this.InstrumentName.GetHashCode();
                if (this.SettlementPeriod != null)
                    hashCode = hashCode * 59 + this.SettlementPeriod.GetHashCode();
                if (this.Strike != null)
                    hashCode = hashCode * 59 + this.Strike.GetHashCode();
                if (this.BaseCurrency != null)
                    hashCode = hashCode * 59 + this.BaseCurrency.GetHashCode();
                if (this.CreationTimestamp != null)
                    hashCode = hashCode * 59 + this.CreationTimestamp.GetHashCode();
                if (this.ExpirationTimestamp != null)
                    hashCode = hashCode * 59 + this.ExpirationTimestamp.GetHashCode();
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
