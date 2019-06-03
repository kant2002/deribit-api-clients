<?php
/**
 * UserTrade
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
 * UserTrade Class Doc Comment
 *
 * @category Class
 * @package  OpenAPI\Client
 * @author   OpenAPI Generator team
 * @link     https://openapi-generator.tech
 */
class UserTrade implements ModelInterface, ArrayAccess
{
    const DISCRIMINATOR = null;

    /**
      * The original name of the model.
      *
      * @var string
      */
    protected static $openAPIModelName = 'user_trade';

    /**
      * Array of property to type mappings. Used for (de)serialization
      *
      * @var string[]
      */
    protected static $openAPITypes = [
        'direction' => 'string',
        'fee_currency' => 'string',
        'order_id' => 'string',
        'timestamp' => 'int',
        'price' => 'float',
        'iv' => 'float',
        'trade_id' => 'string',
        'fee' => 'float',
        'order_type' => 'string',
        'trade_seq' => 'int',
        'self_trade' => 'bool',
        'state' => 'string',
        'label' => 'string',
        'index_price' => 'float',
        'amount' => 'float',
        'instrument_name' => 'string',
        'tick_direction' => 'int',
        'matching_id' => 'string',
        'liquidity' => 'string'
    ];

    /**
      * Array of property to format mappings. Used for (de)serialization
      *
      * @var string[]
      */
    protected static $openAPIFormats = [
        'direction' => null,
        'fee_currency' => null,
        'order_id' => null,
        'timestamp' => null,
        'price' => null,
        'iv' => null,
        'trade_id' => null,
        'fee' => null,
        'order_type' => null,
        'trade_seq' => null,
        'self_trade' => null,
        'state' => null,
        'label' => null,
        'index_price' => null,
        'amount' => null,
        'instrument_name' => null,
        'tick_direction' => null,
        'matching_id' => null,
        'liquidity' => null
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
        'direction' => 'direction',
        'fee_currency' => 'fee_currency',
        'order_id' => 'order_id',
        'timestamp' => 'timestamp',
        'price' => 'price',
        'iv' => 'iv',
        'trade_id' => 'trade_id',
        'fee' => 'fee',
        'order_type' => 'order_type',
        'trade_seq' => 'trade_seq',
        'self_trade' => 'self_trade',
        'state' => 'state',
        'label' => 'label',
        'index_price' => 'index_price',
        'amount' => 'amount',
        'instrument_name' => 'instrument_name',
        'tick_direction' => 'tick_direction',
        'matching_id' => 'matching_id',
        'liquidity' => 'liquidity'
    ];

    /**
     * Array of attributes to setter functions (for deserialization of responses)
     *
     * @var string[]
     */
    protected static $setters = [
        'direction' => 'setDirection',
        'fee_currency' => 'setFeeCurrency',
        'order_id' => 'setOrderId',
        'timestamp' => 'setTimestamp',
        'price' => 'setPrice',
        'iv' => 'setIv',
        'trade_id' => 'setTradeId',
        'fee' => 'setFee',
        'order_type' => 'setOrderType',
        'trade_seq' => 'setTradeSeq',
        'self_trade' => 'setSelfTrade',
        'state' => 'setState',
        'label' => 'setLabel',
        'index_price' => 'setIndexPrice',
        'amount' => 'setAmount',
        'instrument_name' => 'setInstrumentName',
        'tick_direction' => 'setTickDirection',
        'matching_id' => 'setMatchingId',
        'liquidity' => 'setLiquidity'
    ];

    /**
     * Array of attributes to getter functions (for serialization of requests)
     *
     * @var string[]
     */
    protected static $getters = [
        'direction' => 'getDirection',
        'fee_currency' => 'getFeeCurrency',
        'order_id' => 'getOrderId',
        'timestamp' => 'getTimestamp',
        'price' => 'getPrice',
        'iv' => 'getIv',
        'trade_id' => 'getTradeId',
        'fee' => 'getFee',
        'order_type' => 'getOrderType',
        'trade_seq' => 'getTradeSeq',
        'self_trade' => 'getSelfTrade',
        'state' => 'getState',
        'label' => 'getLabel',
        'index_price' => 'getIndexPrice',
        'amount' => 'getAmount',
        'instrument_name' => 'getInstrumentName',
        'tick_direction' => 'getTickDirection',
        'matching_id' => 'getMatchingId',
        'liquidity' => 'getLiquidity'
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

    const DIRECTION_BUY = 'buy';
    const DIRECTION_SELL = 'sell';
    const FEE_CURRENCY_BTC = 'BTC';
    const FEE_CURRENCY_ETH = 'ETH';
    const ORDER_TYPE_LIMIT = 'limit';
    const ORDER_TYPE_MARKET = 'market';
    const ORDER_TYPE_LIQUIDATION = 'liquidation';
    const STATE_OPEN = 'open';
    const STATE_FILLED = 'filled';
    const STATE_REJECTED = 'rejected';
    const STATE_CANCELLED = 'cancelled';
    const STATE_UNTRIGGERED = 'untriggered';
    const STATE_ARCHIVE = 'archive';
    const TICK_DIRECTION_0 = 0;
    const TICK_DIRECTION_1 = 1;
    const TICK_DIRECTION_2 = 2;
    const TICK_DIRECTION_3 = 3;
    const LIQUIDITY_M = 'M';
    const LIQUIDITY_T = 'T';
    

    
    /**
     * Gets allowable values of the enum
     *
     * @return string[]
     */
    public function getDirectionAllowableValues()
    {
        return [
            self::DIRECTION_BUY,
            self::DIRECTION_SELL,
        ];
    }
    
    /**
     * Gets allowable values of the enum
     *
     * @return string[]
     */
    public function getFeeCurrencyAllowableValues()
    {
        return [
            self::FEE_CURRENCY_BTC,
            self::FEE_CURRENCY_ETH,
        ];
    }
    
    /**
     * Gets allowable values of the enum
     *
     * @return string[]
     */
    public function getOrderTypeAllowableValues()
    {
        return [
            self::ORDER_TYPE_LIMIT,
            self::ORDER_TYPE_MARKET,
            self::ORDER_TYPE_LIQUIDATION,
        ];
    }
    
    /**
     * Gets allowable values of the enum
     *
     * @return string[]
     */
    public function getStateAllowableValues()
    {
        return [
            self::STATE_OPEN,
            self::STATE_FILLED,
            self::STATE_REJECTED,
            self::STATE_CANCELLED,
            self::STATE_UNTRIGGERED,
            self::STATE_ARCHIVE,
        ];
    }
    
    /**
     * Gets allowable values of the enum
     *
     * @return string[]
     */
    public function getTickDirectionAllowableValues()
    {
        return [
            self::TICK_DIRECTION_0,
            self::TICK_DIRECTION_1,
            self::TICK_DIRECTION_2,
            self::TICK_DIRECTION_3,
        ];
    }
    
    /**
     * Gets allowable values of the enum
     *
     * @return string[]
     */
    public function getLiquidityAllowableValues()
    {
        return [
            self::LIQUIDITY_M,
            self::LIQUIDITY_T,
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
        $this->container['direction'] = isset($data['direction']) ? $data['direction'] : null;
        $this->container['fee_currency'] = isset($data['fee_currency']) ? $data['fee_currency'] : null;
        $this->container['order_id'] = isset($data['order_id']) ? $data['order_id'] : null;
        $this->container['timestamp'] = isset($data['timestamp']) ? $data['timestamp'] : null;
        $this->container['price'] = isset($data['price']) ? $data['price'] : null;
        $this->container['iv'] = isset($data['iv']) ? $data['iv'] : null;
        $this->container['trade_id'] = isset($data['trade_id']) ? $data['trade_id'] : null;
        $this->container['fee'] = isset($data['fee']) ? $data['fee'] : null;
        $this->container['order_type'] = isset($data['order_type']) ? $data['order_type'] : null;
        $this->container['trade_seq'] = isset($data['trade_seq']) ? $data['trade_seq'] : null;
        $this->container['self_trade'] = isset($data['self_trade']) ? $data['self_trade'] : null;
        $this->container['state'] = isset($data['state']) ? $data['state'] : null;
        $this->container['label'] = isset($data['label']) ? $data['label'] : null;
        $this->container['index_price'] = isset($data['index_price']) ? $data['index_price'] : null;
        $this->container['amount'] = isset($data['amount']) ? $data['amount'] : null;
        $this->container['instrument_name'] = isset($data['instrument_name']) ? $data['instrument_name'] : null;
        $this->container['tick_direction'] = isset($data['tick_direction']) ? $data['tick_direction'] : null;
        $this->container['matching_id'] = isset($data['matching_id']) ? $data['matching_id'] : null;
        $this->container['liquidity'] = isset($data['liquidity']) ? $data['liquidity'] : null;
    }

    /**
     * Show all the invalid properties with reasons.
     *
     * @return array invalid properties with reasons
     */
    public function listInvalidProperties()
    {
        $invalidProperties = [];

        if ($this->container['direction'] === null) {
            $invalidProperties[] = "'direction' can't be null";
        }
        $allowedValues = $this->getDirectionAllowableValues();
        if (!is_null($this->container['direction']) && !in_array($this->container['direction'], $allowedValues, true)) {
            $invalidProperties[] = sprintf(
                "invalid value for 'direction', must be one of '%s'",
                implode("', '", $allowedValues)
            );
        }

        if ($this->container['fee_currency'] === null) {
            $invalidProperties[] = "'fee_currency' can't be null";
        }
        $allowedValues = $this->getFeeCurrencyAllowableValues();
        if (!is_null($this->container['fee_currency']) && !in_array($this->container['fee_currency'], $allowedValues, true)) {
            $invalidProperties[] = sprintf(
                "invalid value for 'fee_currency', must be one of '%s'",
                implode("', '", $allowedValues)
            );
        }

        if ($this->container['order_id'] === null) {
            $invalidProperties[] = "'order_id' can't be null";
        }
        if ($this->container['timestamp'] === null) {
            $invalidProperties[] = "'timestamp' can't be null";
        }
        if ($this->container['price'] === null) {
            $invalidProperties[] = "'price' can't be null";
        }
        if ($this->container['trade_id'] === null) {
            $invalidProperties[] = "'trade_id' can't be null";
        }
        if ($this->container['fee'] === null) {
            $invalidProperties[] = "'fee' can't be null";
        }
        $allowedValues = $this->getOrderTypeAllowableValues();
        if (!is_null($this->container['order_type']) && !in_array($this->container['order_type'], $allowedValues, true)) {
            $invalidProperties[] = sprintf(
                "invalid value for 'order_type', must be one of '%s'",
                implode("', '", $allowedValues)
            );
        }

        if ($this->container['trade_seq'] === null) {
            $invalidProperties[] = "'trade_seq' can't be null";
        }
        if ($this->container['self_trade'] === null) {
            $invalidProperties[] = "'self_trade' can't be null";
        }
        if ($this->container['state'] === null) {
            $invalidProperties[] = "'state' can't be null";
        }
        $allowedValues = $this->getStateAllowableValues();
        if (!is_null($this->container['state']) && !in_array($this->container['state'], $allowedValues, true)) {
            $invalidProperties[] = sprintf(
                "invalid value for 'state', must be one of '%s'",
                implode("', '", $allowedValues)
            );
        }

        if ($this->container['index_price'] === null) {
            $invalidProperties[] = "'index_price' can't be null";
        }
        if ($this->container['amount'] === null) {
            $invalidProperties[] = "'amount' can't be null";
        }
        if ($this->container['instrument_name'] === null) {
            $invalidProperties[] = "'instrument_name' can't be null";
        }
        if ($this->container['tick_direction'] === null) {
            $invalidProperties[] = "'tick_direction' can't be null";
        }
        $allowedValues = $this->getTickDirectionAllowableValues();
        if (!is_null($this->container['tick_direction']) && !in_array($this->container['tick_direction'], $allowedValues, true)) {
            $invalidProperties[] = sprintf(
                "invalid value for 'tick_direction', must be one of '%s'",
                implode("', '", $allowedValues)
            );
        }

        if ($this->container['matching_id'] === null) {
            $invalidProperties[] = "'matching_id' can't be null";
        }
        $allowedValues = $this->getLiquidityAllowableValues();
        if (!is_null($this->container['liquidity']) && !in_array($this->container['liquidity'], $allowedValues, true)) {
            $invalidProperties[] = sprintf(
                "invalid value for 'liquidity', must be one of '%s'",
                implode("', '", $allowedValues)
            );
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
     * Gets direction
     *
     * @return string
     */
    public function getDirection()
    {
        return $this->container['direction'];
    }

    /**
     * Sets direction
     *
     * @param string $direction Trade direction of the taker
     *
     * @return $this
     */
    public function setDirection($direction)
    {
        $allowedValues = $this->getDirectionAllowableValues();
        if (!in_array($direction, $allowedValues, true)) {
            throw new \InvalidArgumentException(
                sprintf(
                    "Invalid value for 'direction', must be one of '%s'",
                    implode("', '", $allowedValues)
                )
            );
        }
        $this->container['direction'] = $direction;

        return $this;
    }

    /**
     * Gets fee_currency
     *
     * @return string
     */
    public function getFeeCurrency()
    {
        return $this->container['fee_currency'];
    }

    /**
     * Sets fee_currency
     *
     * @param string $fee_currency Currency, i.e `\"BTC\"`, `\"ETH\"`
     *
     * @return $this
     */
    public function setFeeCurrency($fee_currency)
    {
        $allowedValues = $this->getFeeCurrencyAllowableValues();
        if (!in_array($fee_currency, $allowedValues, true)) {
            throw new \InvalidArgumentException(
                sprintf(
                    "Invalid value for 'fee_currency', must be one of '%s'",
                    implode("', '", $allowedValues)
                )
            );
        }
        $this->container['fee_currency'] = $fee_currency;

        return $this;
    }

    /**
     * Gets order_id
     *
     * @return string
     */
    public function getOrderId()
    {
        return $this->container['order_id'];
    }

    /**
     * Sets order_id
     *
     * @param string $order_id Id of the user order (maker or taker), i.e. subscriber's order id that took part in the trade
     *
     * @return $this
     */
    public function setOrderId($order_id)
    {
        $this->container['order_id'] = $order_id;

        return $this;
    }

    /**
     * Gets timestamp
     *
     * @return int
     */
    public function getTimestamp()
    {
        return $this->container['timestamp'];
    }

    /**
     * Sets timestamp
     *
     * @param int $timestamp The timestamp of the trade
     *
     * @return $this
     */
    public function setTimestamp($timestamp)
    {
        $this->container['timestamp'] = $timestamp;

        return $this;
    }

    /**
     * Gets price
     *
     * @return float
     */
    public function getPrice()
    {
        return $this->container['price'];
    }

    /**
     * Sets price
     *
     * @param float $price The price of the trade
     *
     * @return $this
     */
    public function setPrice($price)
    {
        $this->container['price'] = $price;

        return $this;
    }

    /**
     * Gets iv
     *
     * @return float|null
     */
    public function getIv()
    {
        return $this->container['iv'];
    }

    /**
     * Sets iv
     *
     * @param float|null $iv Option implied volatility for the price (Option only)
     *
     * @return $this
     */
    public function setIv($iv)
    {
        $this->container['iv'] = $iv;

        return $this;
    }

    /**
     * Gets trade_id
     *
     * @return string
     */
    public function getTradeId()
    {
        return $this->container['trade_id'];
    }

    /**
     * Sets trade_id
     *
     * @param string $trade_id Unique (per currency) trade identifier
     *
     * @return $this
     */
    public function setTradeId($trade_id)
    {
        $this->container['trade_id'] = $trade_id;

        return $this;
    }

    /**
     * Gets fee
     *
     * @return float
     */
    public function getFee()
    {
        return $this->container['fee'];
    }

    /**
     * Sets fee
     *
     * @param float $fee User's fee in units of the specified `fee_currency`
     *
     * @return $this
     */
    public function setFee($fee)
    {
        $this->container['fee'] = $fee;

        return $this;
    }

    /**
     * Gets order_type
     *
     * @return string|null
     */
    public function getOrderType()
    {
        return $this->container['order_type'];
    }

    /**
     * Sets order_type
     *
     * @param string|null $order_type Order type: `\"limit`, `\"market\"`, or `\"liquidation\"`
     *
     * @return $this
     */
    public function setOrderType($order_type)
    {
        $allowedValues = $this->getOrderTypeAllowableValues();
        if (!is_null($order_type) && !in_array($order_type, $allowedValues, true)) {
            throw new \InvalidArgumentException(
                sprintf(
                    "Invalid value for 'order_type', must be one of '%s'",
                    implode("', '", $allowedValues)
                )
            );
        }
        $this->container['order_type'] = $order_type;

        return $this;
    }

    /**
     * Gets trade_seq
     *
     * @return int
     */
    public function getTradeSeq()
    {
        return $this->container['trade_seq'];
    }

    /**
     * Sets trade_seq
     *
     * @param int $trade_seq The sequence number of the trade within instrument
     *
     * @return $this
     */
    public function setTradeSeq($trade_seq)
    {
        $this->container['trade_seq'] = $trade_seq;

        return $this;
    }

    /**
     * Gets self_trade
     *
     * @return bool
     */
    public function getSelfTrade()
    {
        return $this->container['self_trade'];
    }

    /**
     * Sets self_trade
     *
     * @param bool $self_trade `true` if the trade is against own order. This can only happen when your account has self-trading enabled. Contact an administrator if you think you need that
     *
     * @return $this
     */
    public function setSelfTrade($self_trade)
    {
        $this->container['self_trade'] = $self_trade;

        return $this;
    }

    /**
     * Gets state
     *
     * @return string
     */
    public function getState()
    {
        return $this->container['state'];
    }

    /**
     * Sets state
     *
     * @param string $state order state, `\"open\"`, `\"filled\"`, `\"rejected\"`, `\"cancelled\"`, `\"untriggered\"` or `\"archive\"` (if order was archived)
     *
     * @return $this
     */
    public function setState($state)
    {
        $allowedValues = $this->getStateAllowableValues();
        if (!in_array($state, $allowedValues, true)) {
            throw new \InvalidArgumentException(
                sprintf(
                    "Invalid value for 'state', must be one of '%s'",
                    implode("', '", $allowedValues)
                )
            );
        }
        $this->container['state'] = $state;

        return $this;
    }

    /**
     * Gets label
     *
     * @return string|null
     */
    public function getLabel()
    {
        return $this->container['label'];
    }

    /**
     * Sets label
     *
     * @param string|null $label User defined label (presented only when previously set for order by user)
     *
     * @return $this
     */
    public function setLabel($label)
    {
        $this->container['label'] = $label;

        return $this;
    }

    /**
     * Gets index_price
     *
     * @return float
     */
    public function getIndexPrice()
    {
        return $this->container['index_price'];
    }

    /**
     * Sets index_price
     *
     * @param float $index_price Index Price at the moment of trade
     *
     * @return $this
     */
    public function setIndexPrice($index_price)
    {
        $this->container['index_price'] = $index_price;

        return $this;
    }

    /**
     * Gets amount
     *
     * @return float
     */
    public function getAmount()
    {
        return $this->container['amount'];
    }

    /**
     * Sets amount
     *
     * @param float $amount Trade amount. For perpetual and futures - in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH.
     *
     * @return $this
     */
    public function setAmount($amount)
    {
        $this->container['amount'] = $amount;

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
     * Gets tick_direction
     *
     * @return int
     */
    public function getTickDirection()
    {
        return $this->container['tick_direction'];
    }

    /**
     * Sets tick_direction
     *
     * @param int $tick_direction Direction of the \"tick\" (`0` = Plus Tick, `1` = Zero-Plus Tick, `2` = Minus Tick, `3` = Zero-Minus Tick).
     *
     * @return $this
     */
    public function setTickDirection($tick_direction)
    {
        $allowedValues = $this->getTickDirectionAllowableValues();
        if (!in_array($tick_direction, $allowedValues, true)) {
            throw new \InvalidArgumentException(
                sprintf(
                    "Invalid value for 'tick_direction', must be one of '%s'",
                    implode("', '", $allowedValues)
                )
            );
        }
        $this->container['tick_direction'] = $tick_direction;

        return $this;
    }

    /**
     * Gets matching_id
     *
     * @return string
     */
    public function getMatchingId()
    {
        return $this->container['matching_id'];
    }

    /**
     * Sets matching_id
     *
     * @param string $matching_id Always `null`, except for a self-trade which is possible only if self-trading is switched on for the account (in that case this will be id of the maker order of the subscriber)
     *
     * @return $this
     */
    public function setMatchingId($matching_id)
    {
        $this->container['matching_id'] = $matching_id;

        return $this;
    }

    /**
     * Gets liquidity
     *
     * @return string|null
     */
    public function getLiquidity()
    {
        return $this->container['liquidity'];
    }

    /**
     * Sets liquidity
     *
     * @param string|null $liquidity Describes what was role of users order: `\"M\"` when it was maker order, `\"T\"` when it was taker order
     *
     * @return $this
     */
    public function setLiquidity($liquidity)
    {
        $allowedValues = $this->getLiquidityAllowableValues();
        if (!is_null($liquidity) && !in_array($liquidity, $allowedValues, true)) {
            throw new \InvalidArgumentException(
                sprintf(
                    "Invalid value for 'liquidity', must be one of '%s'",
                    implode("', '", $allowedValues)
                )
            );
        }
        $this->container['liquidity'] = $liquidity;

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


