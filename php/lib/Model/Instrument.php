<?php
/**
 * Instrument
 *
 * PHP version 5
 *
 * @category Class
 * @package  OpenAPI\Client
 * @author   OpenAPI Generator team
 * @link     https://openapi-generator.tech
 */

/**
 * Deribit API
 *
 * #Overview  Deribit provides three different interfaces to access the API:  * [JSON-RPC over Websocket](#json-rpc) * [JSON-RPC over HTTP](#json-rpc) * [FIX](#fix-api) (Financial Information eXchange)  With the API Console you can use and test the JSON-RPC API, both via HTTP and  via Websocket. To visit the API console, go to __Account > API tab >  API Console tab.__   ##Naming Deribit tradeable assets or instruments use the following system of naming:  |Kind|Examples|Template|Comments| |----|--------|--------|--------| |Future|<code>BTC-25MAR16</code>, <code>BTC-5AUG16</code>|<code>BTC-DMMMYY</code>|<code>BTC</code> is currency, <code>DMMMYY</code> is expiration date, <code>D</code> stands for day of month (1 or 2 digits), <code>MMM</code> - month (3 first letters in English), <code>YY</code> stands for year.| |Perpetual|<code>BTC-PERPETUAL</code>                        ||Perpetual contract for currency <code>BTC</code>.| |Option|<code>BTC-25MAR16-420-C</code>, <code>BTC-5AUG16-580-P</code>|<code>BTC-DMMMYY-STRIKE-K</code>|<code>STRIKE</code> is option strike price in USD. Template <code>K</code> is option kind: <code>C</code> for call options or <code>P</code> for put options.|   # JSON-RPC JSON-RPC is a light-weight remote procedure call (RPC) protocol. The  [JSON-RPC specification](https://www.jsonrpc.org/specification) defines the data structures that are used for the messages that are exchanged between client and server, as well as the rules around their processing. JSON-RPC uses JSON (RFC 4627) as data format.  JSON-RPC is transport agnostic: it does not specify which transport mechanism must be used. The Deribit API supports both Websocket (preferred) and HTTP (with limitations: subscriptions are not supported over HTTP).  ## Request messages > An example of a request message:  ```json {     \"jsonrpc\": \"2.0\",     \"id\": 8066,     \"method\": \"public/ticker\",     \"params\": {         \"instrument\": \"BTC-24AUG18-6500-P\"     } } ```  According to the JSON-RPC sepcification the requests must be JSON objects with the following fields.  |Name|Type|Description| |----|----|-----------| |jsonrpc|string|The version of the JSON-RPC spec: \"2.0\"| |id|integer or string|An identifier of the request. If it is included, then the response will contain the same identifier| |method|string|The method to be invoked| |params|object|The parameters values for the method. The field names must match with the expected parameter names. The parameters that are expected are described in the documentation for the methods, below.|  <aside class=\"warning\"> The JSON-RPC specification describes two features that are currently not supported by the API:  <ul> <li>Specification of parameter values by position</li> <li>Batch requests</li> </ul>  </aside>   ## Response messages > An example of a response message:  ```json {     \"jsonrpc\": \"2.0\",     \"id\": 5239,     \"testnet\": false,     \"result\": [         {             \"currency\": \"BTC\",             \"currencyLong\": \"Bitcoin\",             \"minConfirmation\": 2,             \"txFee\": 0.0006,             \"isActive\": true,             \"coinType\": \"BITCOIN\",             \"baseAddress\": null         }     ],     \"usIn\": 1535043730126248,     \"usOut\": 1535043730126250,     \"usDiff\": 2 } ```  The JSON-RPC API always responds with a JSON object with the following fields.   |Name|Type|Description| |----|----|-----------| |id|integer|This is the same id that was sent in the request.| |result|any|If successful, the result of the API call. The format for the result is described with each method.| |error|error object|Only present if there was an error invoking the method. The error object is described below.| |testnet|boolean|Indicates whether the API in use is actually the test API.  <code>false</code> for production server, <code>true</code> for test server.| |usIn|integer|The timestamp when the requests was received (microseconds since the Unix epoch)| |usOut|integer|The timestamp when the response was sent (microseconds since the Unix epoch)| |usDiff|integer|The number of microseconds that was spent handling the request|  <aside class=\"notice\"> The fields <code>testnet</code>, <code>usIn</code>, <code>usOut</code> and <code>usDiff</code> are not part of the JSON-RPC standard.  <p>In order not to clutter the examples they will generally be omitted from the example code.</p> </aside>  > An example of a response with an error:  ```json {     \"jsonrpc\": \"2.0\",     \"id\": 8163,     \"error\": {         \"code\": 11050,         \"message\": \"bad_request\"     },     \"testnet\": false,     \"usIn\": 1535037392434763,     \"usOut\": 1535037392448119,     \"usDiff\": 13356 } ``` In case of an error the response message will contain the error field, with as value an object with the following with the following fields:  |Name|Type|Description |----|----|-----------| |code|integer|A number that indicates the kind of error.| |message|string|A short description that indicates the kind of error.| |data|any|Additional data about the error. This field may be omitted.|  ## Notifications  > An example of a notification:  ```json {     \"jsonrpc\": \"2.0\",     \"method\": \"subscription\",     \"params\": {         \"channel\": \"deribit_price_index.btc_usd\",         \"data\": {             \"timestamp\": 1535098298227,             \"price\": 6521.17,             \"index_name\": \"btc_usd\"         }     } } ```  API users can subscribe to certain types of notifications. This means that they will receive JSON-RPC notification-messages from the server when certain events occur, such as changes to the index price or changes to the order book for a certain instrument.   The API methods [public/subscribe](#public-subscribe) and [private/subscribe](#private-subscribe) are used to set up a subscription. Since HTTP does not support the sending of messages from server to client, these methods are only availble when using the Websocket transport mechanism.  At the moment of subscription a \"channel\" must be specified. The channel determines the type of events that will be received.  See [Subscriptions](#subscriptions) for more details about the channels.  In accordance with the JSON-RPC specification, the format of a notification  is that of a request message without an <code>id</code> field. The value of the <code>method</code> field will always be <code>\"subscription\"</code>. The <code>params</code> field will always be an object with 2 members: <code>channel</code> and <code>data</code>. The value of the <code>channel</code> member is the name of the channel (a string). The value of the <code>data</code> member is an object that contains data  that is specific for the channel.   ## Authentication  > An example of a JSON request with token:  ```json {     \"id\": 5647,     \"method\": \"private/get_subaccounts\",     \"params\": {         \"access_token\": \"67SVutDoVZSzkUStHSuk51WntMNBJ5mh5DYZhwzpiqDF\"     } } ```  The API consists of `public` and `private` methods. The public methods do not require authentication. The private methods use OAuth 2.0 authentication. This means that a valid OAuth access token must be included in the request, which can get achived by calling method [public/auth](#public-auth).  When the token was assigned to the user, it should be passed along, with other request parameters, back to the server:  |Connection type|Access token placement |----|-----------| |**Websocket**|Inside request JSON parameters, as an `access_token` field| |**HTTP (REST)**|Header `Authorization: bearer ```Token``` ` value|  ### Additional authorization method - basic user credentials  <span style=\"color:red\"><b> ! Not recommended - however, it could be useful for quick testing API</b></span></br>  Every `private` method could be accessed by providing, inside HTTP `Authorization: Basic XXX` header, values with user `ClientId` and assigned `ClientSecret` (both values can be found on the API page on the Deribit website) encoded with `Base64`:  <code>Authorization: Basic BASE64(`ClientId` + `:` + `ClientSecret`)</code>   ### Additional authorization method - Deribit signature credentials  The Derbit service provides dedicated authorization method, which harness user generated signature to increase security level for passing request data. Generated value is passed inside `Authorization` header, coded as:  <code>Authorization: deri-hmac-sha256 id=```ClientId```,ts=```Timestamp```,sig=```Signature```,nonce=```Nonce```</code>  where:  |Deribit credential|Description |----|-----------| |*ClientId*|Can be found on the API page on the Deribit website| |*Timestamp*|Time when the request was generated - given as **miliseconds**. It's valid for **60 seconds** since generation, after that time any request with an old timestamp will be rejected.| |*Signature*|Value for signature calculated as described below | |*Nonce*|Single usage, user generated initialization vector for the server token|  The signature is generated by the following formula:  <code> Signature = HEX_STRING( HMAC-SHA256( ClientSecret, StringToSign ) );</code></br>  <code> StringToSign =  Timestamp + \"\\n\" + Nonce + \"\\n\" + RequestData;</code></br>  <code> RequestData =  UPPERCASE(HTTP_METHOD())  + \"\\n\" + URI() + \"\\n\" + RequestBody + \"\\n\";</code></br>   e.g. (using shell with ```openssl``` tool):  <code>&nbsp;&nbsp;&nbsp;&nbsp;ClientId=AAAAAAAAAAA</code></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;ClientSecret=ABCD</code></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;Timestamp=$( date +%s000 )</code></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;Nonce=$( cat /dev/urandom | tr -dc 'a-z0-9' | head -c8 )</code></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;URI=\"/api/v2/private/get_account_summary?currency=BTC\"</code></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;HttpMethod=GET</code></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;Body=\"\"</code></br></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;Signature=$( echo -ne \"${Timestamp}\\n${Nonce}\\n${HttpMethod}\\n${URI}\\n${Body}\\n\" | openssl sha256 -r -hmac \"$ClientSecret\" | cut -f1 -d' ' )</code></br></br> <code>&nbsp;&nbsp;&nbsp;&nbsp;echo $Signature</code></br></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;shell output> ea40d5e5e4fae235ab22b61da98121fbf4acdc06db03d632e23c66bcccb90d2c  (**WARNING**: Exact value depends on current timestamp and client credentials</code></br></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;curl -s -X ${HttpMethod} -H \"Authorization: deri-hmac-sha256 id=${ClientId},ts=${Timestamp},nonce=${Nonce},sig=${Signature}\" \"https://www.deribit.com${URI}\"</code></br></br>    ### Additional authorization method - signature credentials (WebSocket API)  When connecting through Websocket, user can request for authorization using ```client_credential``` method, which requires providing following parameters (as a part of JSON request):  |JSON parameter|Description |----|-----------| |*grant_type*|Must be **client_signature**| |*client_id*|Can be found on the API page on the Deribit website| |*timestamp*|Time when the request was generated - given as **miliseconds**. It's valid for **60 seconds** since generation, after that time any request with an old timestamp will be rejected.| |*signature*|Value for signature calculated as described below | |*nonce*|Single usage, user generated initialization vector for the server token| |*data*|**Optional** field, which contains any user specific value|  The signature is generated by the following formula:  <code> StringToSign =  Timestamp + \"\\n\" + Nonce + \"\\n\" + Data;</code></br>  <code> Signature = HEX_STRING( HMAC-SHA256( ClientSecret, StringToSign ) );</code></br>   e.g. (using shell with ```openssl``` tool):  <code>&nbsp;&nbsp;&nbsp;&nbsp;ClientId=AAAAAAAAAAA</code></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;ClientSecret=ABCD</code></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;Timestamp=$( date +%s000 ) # e.g. 1554883365000 </code></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;Nonce=$( cat /dev/urandom | tr -dc 'a-z0-9' | head -c8 ) # e.g. fdbmmz79 </code></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;Data=\"\"</code></br></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;Signature=$( echo -ne \"${Timestamp}\\n${Nonce}\\n${Data}\\n\" | openssl sha256 -r -hmac \"$ClientSecret\" | cut -f1 -d' ' )</code></br></br> <code>&nbsp;&nbsp;&nbsp;&nbsp;echo $Signature</code></br></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;shell output> e20c9cd5639d41f8bbc88f4d699c4baf94a4f0ee320e9a116b72743c449eb994  (**WARNING**: Exact value depends on current timestamp and client credentials</code></br></br>   You can also check the signature value using some online tools like, e.g: [https://codebeautify.org/hmac-generator](https://codebeautify.org/hmac-generator) (but don't forget about adding *newline* after each part of the hashed text and remember that you **should use** it only with your **test credentials**).   Here's a sample JSON request created using the values from the example above:  <code> {                            </br> &nbsp;&nbsp;\"jsonrpc\" : \"2.0\",         </br> &nbsp;&nbsp;\"id\" : 9929,               </br> &nbsp;&nbsp;\"method\" : \"public/auth\",  </br> &nbsp;&nbsp;\"params\" :                 </br> &nbsp;&nbsp;{                        </br> &nbsp;&nbsp;&nbsp;&nbsp;\"grant_type\" : \"client_signature\",   </br> &nbsp;&nbsp;&nbsp;&nbsp;\"client_id\" : \"AAAAAAAAAAA\",         </br> &nbsp;&nbsp;&nbsp;&nbsp;\"timestamp\": \"1554883365000\",        </br> &nbsp;&nbsp;&nbsp;&nbsp;\"nonce\": \"fdbmmz79\",                 </br> &nbsp;&nbsp;&nbsp;&nbsp;\"data\": \"\",                          </br> &nbsp;&nbsp;&nbsp;&nbsp;\"signature\" : \"e20c9cd5639d41f8bbc88f4d699c4baf94a4f0ee320e9a116b72743c449eb994\"  </br> &nbsp;&nbsp;}                        </br> }                            </br> </code>   ### Access scope  When asking for `access token` user can provide the required access level (called `scope`) which defines what type of functionality he/she wants to use, and whether requests are only going to check for some data or also to update them.  Scopes are required and checked for `private` methods, so if you plan to use only `public` information you can stay with values assigned by default.  |Scope|Description |----|-----------| |*account:read*|Access to **account** methods - read only data| |*account:read_write*|Access to **account** methods - allows to manage account settings, add subaccounts, etc.| |*trade:read*|Access to **trade** methods - read only data| |*trade:read_write*|Access to **trade** methods - required to create and modify orders| |*wallet:read*|Access to **wallet** methods - read only data| |*wallet:read_write*|Access to **wallet** methods - allows to withdraw, generate new deposit address, etc.| |*wallet:none*, *account:none*, *trade:none*|Blocked access to specified functionality|    <span style=\"color:red\">**NOTICE:**</span> Depending on choosing an authentication method (```grant type```) some scopes could be narrowed by the server. e.g. when ```grant_type = client_credentials``` and ```scope = wallet:read_write``` it's modified by the server as ```scope = wallet:read```\"   ## JSON-RPC over websocket Websocket is the prefered transport mechanism for the JSON-RPC API, because it is faster and because it can support [subscriptions](#subscriptions) and [cancel on disconnect](#private-enable_cancel_on_disconnect). The code examples that can be found next to each of the methods show how websockets can be used from Python or Javascript/node.js.  ## JSON-RPC over HTTP Besides websockets it is also possible to use the API via HTTP. The code examples for 'shell' show how this can be done using curl. Note that subscriptions and cancel on disconnect are not supported via HTTP.  #Methods
 *
 * The version of the OpenAPI document: 2.0.0
 * 
 * Generated by: https://openapi-generator.tech
 * OpenAPI Generator version: 4.0.2-SNAPSHOT
 */

/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */

namespace OpenAPI\Client\Model;

use \ArrayAccess;
use \OpenAPI\Client\ObjectSerializer;

/**
 * Instrument Class Doc Comment
 *
 * @category Class
 * @package  OpenAPI\Client
 * @author   OpenAPI Generator team
 * @link     https://openapi-generator.tech
 */
class Instrument implements ModelInterface, ArrayAccess
{
    const DISCRIMINATOR = null;

    /**
      * The original name of the model.
      *
      * @var string
      */
    protected static $openAPIModelName = 'instrument';

    /**
      * Array of property to type mappings. Used for (de)serialization
      *
      * @var string[]
      */
    protected static $openAPITypes = [
        'quote_currency' => 'string',
        'kind' => 'string',
        'tick_size' => 'float',
        'contract_size' => 'int',
        'is_active' => 'bool',
        'option_type' => 'string',
        'min_trade_amount' => 'float',
        'instrument_name' => 'string',
        'settlement_period' => 'string',
        'strike' => 'float',
        'base_currency' => 'string',
        'creation_timestamp' => 'int',
        'expiration_timestamp' => 'int'
    ];

    /**
      * Array of property to format mappings. Used for (de)serialization
      *
      * @var string[]
      */
    protected static $openAPIFormats = [
        'quote_currency' => null,
        'kind' => null,
        'tick_size' => null,
        'contract_size' => null,
        'is_active' => null,
        'option_type' => null,
        'min_trade_amount' => null,
        'instrument_name' => null,
        'settlement_period' => null,
        'strike' => null,
        'base_currency' => null,
        'creation_timestamp' => null,
        'expiration_timestamp' => null
    ];

    /**
     * Array of property to type mappings. Used for (de)serialization
     *
     * @return array
     */
    public static function openAPITypes()
    {
        return self::$openAPITypes;
    }

    /**
     * Array of property to format mappings. Used for (de)serialization
     *
     * @return array
     */
    public static function openAPIFormats()
    {
        return self::$openAPIFormats;
    }

    /**
     * Array of attributes where the key is the local name,
     * and the value is the original name
     *
     * @var string[]
     */
    protected static $attributeMap = [
        'quote_currency' => 'quote_currency',
        'kind' => 'kind',
        'tick_size' => 'tick_size',
        'contract_size' => 'contract_size',
        'is_active' => 'is_active',
        'option_type' => 'option_type',
        'min_trade_amount' => 'min_trade_amount',
        'instrument_name' => 'instrument_name',
        'settlement_period' => 'settlement_period',
        'strike' => 'strike',
        'base_currency' => 'base_currency',
        'creation_timestamp' => 'creation_timestamp',
        'expiration_timestamp' => 'expiration_timestamp'
    ];

    /**
     * Array of attributes to setter functions (for deserialization of responses)
     *
     * @var string[]
     */
    protected static $setters = [
        'quote_currency' => 'setQuoteCurrency',
        'kind' => 'setKind',
        'tick_size' => 'setTickSize',
        'contract_size' => 'setContractSize',
        'is_active' => 'setIsActive',
        'option_type' => 'setOptionType',
        'min_trade_amount' => 'setMinTradeAmount',
        'instrument_name' => 'setInstrumentName',
        'settlement_period' => 'setSettlementPeriod',
        'strike' => 'setStrike',
        'base_currency' => 'setBaseCurrency',
        'creation_timestamp' => 'setCreationTimestamp',
        'expiration_timestamp' => 'setExpirationTimestamp'
    ];

    /**
     * Array of attributes to getter functions (for serialization of requests)
     *
     * @var string[]
     */
    protected static $getters = [
        'quote_currency' => 'getQuoteCurrency',
        'kind' => 'getKind',
        'tick_size' => 'getTickSize',
        'contract_size' => 'getContractSize',
        'is_active' => 'getIsActive',
        'option_type' => 'getOptionType',
        'min_trade_amount' => 'getMinTradeAmount',
        'instrument_name' => 'getInstrumentName',
        'settlement_period' => 'getSettlementPeriod',
        'strike' => 'getStrike',
        'base_currency' => 'getBaseCurrency',
        'creation_timestamp' => 'getCreationTimestamp',
        'expiration_timestamp' => 'getExpirationTimestamp'
    ];

    /**
     * Array of attributes where the key is the local name,
     * and the value is the original name
     *
     * @return array
     */
    public static function attributeMap()
    {
        return self::$attributeMap;
    }

    /**
     * Array of attributes to setter functions (for deserialization of responses)
     *
     * @return array
     */
    public static function setters()
    {
        return self::$setters;
    }

    /**
     * Array of attributes to getter functions (for serialization of requests)
     *
     * @return array
     */
    public static function getters()
    {
        return self::$getters;
    }

    /**
     * The original name of the model.
     *
     * @return string
     */
    public function getModelName()
    {
        return self::$openAPIModelName;
    }

    const QUOTE_CURRENCY_USD = 'USD';
    const KIND_FUTURE = 'future';
    const KIND_OPTION = 'option';
    const OPTION_TYPE_CALL = 'call';
    const OPTION_TYPE_PUT = 'put';
    const SETTLEMENT_PERIOD_MONTH = 'month';
    const SETTLEMENT_PERIOD_WEEK = 'week';
    const SETTLEMENT_PERIOD_PERPETUAL = 'perpetual';
    const BASE_CURRENCY_BTC = 'BTC';
    const BASE_CURRENCY_ETH = 'ETH';
    

    
    /**
     * Gets allowable values of the enum
     *
     * @return string[]
     */
    public function getQuoteCurrencyAllowableValues()
    {
        return [
            self::QUOTE_CURRENCY_USD,
        ];
    }
    
    /**
     * Gets allowable values of the enum
     *
     * @return string[]
     */
    public function getKindAllowableValues()
    {
        return [
            self::KIND_FUTURE,
            self::KIND_OPTION,
        ];
    }
    
    /**
     * Gets allowable values of the enum
     *
     * @return string[]
     */
    public function getOptionTypeAllowableValues()
    {
        return [
            self::OPTION_TYPE_CALL,
            self::OPTION_TYPE_PUT,
        ];
    }
    
    /**
     * Gets allowable values of the enum
     *
     * @return string[]
     */
    public function getSettlementPeriodAllowableValues()
    {
        return [
            self::SETTLEMENT_PERIOD_MONTH,
            self::SETTLEMENT_PERIOD_WEEK,
            self::SETTLEMENT_PERIOD_PERPETUAL,
        ];
    }
    
    /**
     * Gets allowable values of the enum
     *
     * @return string[]
     */
    public function getBaseCurrencyAllowableValues()
    {
        return [
            self::BASE_CURRENCY_BTC,
            self::BASE_CURRENCY_ETH,
        ];
    }
    

    /**
     * Associative array for storing property values
     *
     * @var mixed[]
     */
    protected $container = [];

    /**
     * Constructor
     *
     * @param mixed[] $data Associated array of property values
     *                      initializing the model
     */
    public function __construct(array $data = null)
    {
        $this->container['quote_currency'] = isset($data['quote_currency']) ? $data['quote_currency'] : null;
        $this->container['kind'] = isset($data['kind']) ? $data['kind'] : null;
        $this->container['tick_size'] = isset($data['tick_size']) ? $data['tick_size'] : null;
        $this->container['contract_size'] = isset($data['contract_size']) ? $data['contract_size'] : null;
        $this->container['is_active'] = isset($data['is_active']) ? $data['is_active'] : null;
        $this->container['option_type'] = isset($data['option_type']) ? $data['option_type'] : null;
        $this->container['min_trade_amount'] = isset($data['min_trade_amount']) ? $data['min_trade_amount'] : null;
        $this->container['instrument_name'] = isset($data['instrument_name']) ? $data['instrument_name'] : null;
        $this->container['settlement_period'] = isset($data['settlement_period']) ? $data['settlement_period'] : null;
        $this->container['strike'] = isset($data['strike']) ? $data['strike'] : null;
        $this->container['base_currency'] = isset($data['base_currency']) ? $data['base_currency'] : null;
        $this->container['creation_timestamp'] = isset($data['creation_timestamp']) ? $data['creation_timestamp'] : null;
        $this->container['expiration_timestamp'] = isset($data['expiration_timestamp']) ? $data['expiration_timestamp'] : null;
    }

    /**
     * Show all the invalid properties with reasons.
     *
     * @return array invalid properties with reasons
     */
    public function listInvalidProperties()
    {
        $invalidProperties = [];

        if ($this->container['quote_currency'] === null) {
            $invalidProperties[] = "'quote_currency' can't be null";
        }
        $allowedValues = $this->getQuoteCurrencyAllowableValues();
        if (!is_null($this->container['quote_currency']) && !in_array($this->container['quote_currency'], $allowedValues, true)) {
            $invalidProperties[] = sprintf(
                "invalid value for 'quote_currency', must be one of '%s'",
                implode("', '", $allowedValues)
            );
        }

        if ($this->container['kind'] === null) {
            $invalidProperties[] = "'kind' can't be null";
        }
        $allowedValues = $this->getKindAllowableValues();
        if (!is_null($this->container['kind']) && !in_array($this->container['kind'], $allowedValues, true)) {
            $invalidProperties[] = sprintf(
                "invalid value for 'kind', must be one of '%s'",
                implode("', '", $allowedValues)
            );
        }

        if ($this->container['tick_size'] === null) {
            $invalidProperties[] = "'tick_size' can't be null";
        }
        if ($this->container['contract_size'] === null) {
            $invalidProperties[] = "'contract_size' can't be null";
        }
        if ($this->container['is_active'] === null) {
            $invalidProperties[] = "'is_active' can't be null";
        }
        $allowedValues = $this->getOptionTypeAllowableValues();
        if (!is_null($this->container['option_type']) && !in_array($this->container['option_type'], $allowedValues, true)) {
            $invalidProperties[] = sprintf(
                "invalid value for 'option_type', must be one of '%s'",
                implode("', '", $allowedValues)
            );
        }

        if ($this->container['min_trade_amount'] === null) {
            $invalidProperties[] = "'min_trade_amount' can't be null";
        }
        if ($this->container['instrument_name'] === null) {
            $invalidProperties[] = "'instrument_name' can't be null";
        }
        if ($this->container['settlement_period'] === null) {
            $invalidProperties[] = "'settlement_period' can't be null";
        }
        $allowedValues = $this->getSettlementPeriodAllowableValues();
        if (!is_null($this->container['settlement_period']) && !in_array($this->container['settlement_period'], $allowedValues, true)) {
            $invalidProperties[] = sprintf(
                "invalid value for 'settlement_period', must be one of '%s'",
                implode("', '", $allowedValues)
            );
        }

        if ($this->container['base_currency'] === null) {
            $invalidProperties[] = "'base_currency' can't be null";
        }
        $allowedValues = $this->getBaseCurrencyAllowableValues();
        if (!is_null($this->container['base_currency']) && !in_array($this->container['base_currency'], $allowedValues, true)) {
            $invalidProperties[] = sprintf(
                "invalid value for 'base_currency', must be one of '%s'",
                implode("', '", $allowedValues)
            );
        }

        if ($this->container['creation_timestamp'] === null) {
            $invalidProperties[] = "'creation_timestamp' can't be null";
        }
        if ($this->container['expiration_timestamp'] === null) {
            $invalidProperties[] = "'expiration_timestamp' can't be null";
        }
        return $invalidProperties;
    }

    /**
     * Validate all the properties in the model
     * return true if all passed
     *
     * @return bool True if all properties are valid
     */
    public function valid()
    {
        return count($this->listInvalidProperties()) === 0;
    }


    /**
     * Gets quote_currency
     *
     * @return string
     */
    public function getQuoteCurrency()
    {
        return $this->container['quote_currency'];
    }

    /**
     * Sets quote_currency
     *
     * @param string $quote_currency The currency in which the instrument prices are quoted.
     *
     * @return $this
     */
    public function setQuoteCurrency($quote_currency)
    {
        $allowedValues = $this->getQuoteCurrencyAllowableValues();
        if (!in_array($quote_currency, $allowedValues, true)) {
            throw new \InvalidArgumentException(
                sprintf(
                    "Invalid value for 'quote_currency', must be one of '%s'",
                    implode("', '", $allowedValues)
                )
            );
        }
        $this->container['quote_currency'] = $quote_currency;

        return $this;
    }

    /**
     * Gets kind
     *
     * @return string
     */
    public function getKind()
    {
        return $this->container['kind'];
    }

    /**
     * Sets kind
     *
     * @param string $kind Instrument kind, `\"future\"` or `\"option\"`
     *
     * @return $this
     */
    public function setKind($kind)
    {
        $allowedValues = $this->getKindAllowableValues();
        if (!in_array($kind, $allowedValues, true)) {
            throw new \InvalidArgumentException(
                sprintf(
                    "Invalid value for 'kind', must be one of '%s'",
                    implode("', '", $allowedValues)
                )
            );
        }
        $this->container['kind'] = $kind;

        return $this;
    }

    /**
     * Gets tick_size
     *
     * @return float
     */
    public function getTickSize()
    {
        return $this->container['tick_size'];
    }

    /**
     * Sets tick_size
     *
     * @param float $tick_size specifies minimal price change and, as follows, the number of decimal places for instrument prices
     *
     * @return $this
     */
    public function setTickSize($tick_size)
    {
        $this->container['tick_size'] = $tick_size;

        return $this;
    }

    /**
     * Gets contract_size
     *
     * @return int
     */
    public function getContractSize()
    {
        return $this->container['contract_size'];
    }

    /**
     * Sets contract_size
     *
     * @param int $contract_size Contract size for instrument
     *
     * @return $this
     */
    public function setContractSize($contract_size)
    {
        $this->container['contract_size'] = $contract_size;

        return $this;
    }

    /**
     * Gets is_active
     *
     * @return bool
     */
    public function getIsActive()
    {
        return $this->container['is_active'];
    }

    /**
     * Sets is_active
     *
     * @param bool $is_active Indicates if the instrument can currently be traded.
     *
     * @return $this
     */
    public function setIsActive($is_active)
    {
        $this->container['is_active'] = $is_active;

        return $this;
    }

    /**
     * Gets option_type
     *
     * @return string|null
     */
    public function getOptionType()
    {
        return $this->container['option_type'];
    }

    /**
     * Sets option_type
     *
     * @param string|null $option_type The option type (only for options)
     *
     * @return $this
     */
    public function setOptionType($option_type)
    {
        $allowedValues = $this->getOptionTypeAllowableValues();
        if (!is_null($option_type) && !in_array($option_type, $allowedValues, true)) {
            throw new \InvalidArgumentException(
                sprintf(
                    "Invalid value for 'option_type', must be one of '%s'",
                    implode("', '", $allowedValues)
                )
            );
        }
        $this->container['option_type'] = $option_type;

        return $this;
    }

    /**
     * Gets min_trade_amount
     *
     * @return float
     */
    public function getMinTradeAmount()
    {
        return $this->container['min_trade_amount'];
    }

    /**
     * Sets min_trade_amount
     *
     * @param float $min_trade_amount Minimum amount for trading. For perpetual and futures - in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH.
     *
     * @return $this
     */
    public function setMinTradeAmount($min_trade_amount)
    {
        $this->container['min_trade_amount'] = $min_trade_amount;

        return $this;
    }

    /**
     * Gets instrument_name
     *
     * @return string
     */
    public function getInstrumentName()
    {
        return $this->container['instrument_name'];
    }

    /**
     * Sets instrument_name
     *
     * @param string $instrument_name Unique instrument identifier
     *
     * @return $this
     */
    public function setInstrumentName($instrument_name)
    {
        $this->container['instrument_name'] = $instrument_name;

        return $this;
    }

    /**
     * Gets settlement_period
     *
     * @return string
     */
    public function getSettlementPeriod()
    {
        return $this->container['settlement_period'];
    }

    /**
     * Sets settlement_period
     *
     * @param string $settlement_period The settlement period.
     *
     * @return $this
     */
    public function setSettlementPeriod($settlement_period)
    {
        $allowedValues = $this->getSettlementPeriodAllowableValues();
        if (!in_array($settlement_period, $allowedValues, true)) {
            throw new \InvalidArgumentException(
                sprintf(
                    "Invalid value for 'settlement_period', must be one of '%s'",
                    implode("', '", $allowedValues)
                )
            );
        }
        $this->container['settlement_period'] = $settlement_period;

        return $this;
    }

    /**
     * Gets strike
     *
     * @return float|null
     */
    public function getStrike()
    {
        return $this->container['strike'];
    }

    /**
     * Sets strike
     *
     * @param float|null $strike The strike value. (only for options)
     *
     * @return $this
     */
    public function setStrike($strike)
    {
        $this->container['strike'] = $strike;

        return $this;
    }

    /**
     * Gets base_currency
     *
     * @return string
     */
    public function getBaseCurrency()
    {
        return $this->container['base_currency'];
    }

    /**
     * Sets base_currency
     *
     * @param string $base_currency The underlying currency being traded.
     *
     * @return $this
     */
    public function setBaseCurrency($base_currency)
    {
        $allowedValues = $this->getBaseCurrencyAllowableValues();
        if (!in_array($base_currency, $allowedValues, true)) {
            throw new \InvalidArgumentException(
                sprintf(
                    "Invalid value for 'base_currency', must be one of '%s'",
                    implode("', '", $allowedValues)
                )
            );
        }
        $this->container['base_currency'] = $base_currency;

        return $this;
    }

    /**
     * Gets creation_timestamp
     *
     * @return int
     */
    public function getCreationTimestamp()
    {
        return $this->container['creation_timestamp'];
    }

    /**
     * Sets creation_timestamp
     *
     * @param int $creation_timestamp The time when the instrument was first created (milliseconds)
     *
     * @return $this
     */
    public function setCreationTimestamp($creation_timestamp)
    {
        $this->container['creation_timestamp'] = $creation_timestamp;

        return $this;
    }

    /**
     * Gets expiration_timestamp
     *
     * @return int
     */
    public function getExpirationTimestamp()
    {
        return $this->container['expiration_timestamp'];
    }

    /**
     * Sets expiration_timestamp
     *
     * @param int $expiration_timestamp The time when the instrument will expire (milliseconds)
     *
     * @return $this
     */
    public function setExpirationTimestamp($expiration_timestamp)
    {
        $this->container['expiration_timestamp'] = $expiration_timestamp;

        return $this;
    }
    /**
     * Returns true if offset exists. False otherwise.
     *
     * @param integer $offset Offset
     *
     * @return boolean
     */
    public function offsetExists($offset)
    {
        return isset($this->container[$offset]);
    }

    /**
     * Gets offset.
     *
     * @param integer $offset Offset
     *
     * @return mixed
     */
    public function offsetGet($offset)
    {
        return isset($this->container[$offset]) ? $this->container[$offset] : null;
    }

    /**
     * Sets value based on offset.
     *
     * @param integer $offset Offset
     * @param mixed   $value  Value to be set
     *
     * @return void
     */
    public function offsetSet($offset, $value)
    {
        if (is_null($offset)) {
            $this->container[] = $value;
        } else {
            $this->container[$offset] = $value;
        }
    }

    /**
     * Unsets offset.
     *
     * @param integer $offset Offset
     *
     * @return void
     */
    public function offsetUnset($offset)
    {
        unset($this->container[$offset]);
    }

    /**
     * Gets the string presentation of the object
     *
     * @return string
     */
    public function __toString()
    {
        return json_encode(
            ObjectSerializer::sanitizeForSerialization($this),
            JSON_PRETTY_PRINT
        );
    }
}


