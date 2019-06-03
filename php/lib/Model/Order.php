<?php
/**
 * Order
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
 * Order Class Doc Comment
 *
 * @category Class
 * @package  OpenAPI\Client
 * @author   OpenAPI Generator team
 * @link     https://openapi-generator.tech
 */
class Order implements ModelInterface, ArrayAccess
{
    const DISCRIMINATOR = null;

    /**
      * The original name of the model.
      *
      * @var string
      */
    protected static $openAPIModelName = 'order';

    /**
      * Array of property to type mappings. Used for (de)serialization
      *
      * @var string[]
      */
    protected static $openAPITypes = [
        'direction' => 'string',
        'reduce_only' => 'bool',
        'triggered' => 'bool',
        'order_id' => 'string',
        'price' => 'float',
        'time_in_force' => 'string',
        'api' => 'bool',
        'order_state' => 'string',
        'implv' => 'float',
        'advanced' => 'string',
        'post_only' => 'bool',
        'usd' => 'float',
        'stop_price' => 'float',
        'order_type' => 'string',
        'last_update_timestamp' => 'int',
        'original_order_type' => 'string',
        'max_show' => 'float',
        'profit_loss' => 'float',
        'is_liquidation' => 'bool',
        'filled_amount' => 'float',
        'label' => 'string',
        'commission' => 'float',
        'amount' => 'float',
        'trigger' => 'string',
        'instrument_name' => 'string',
        'creation_timestamp' => 'int',
        'average_price' => 'float'
    ];

    /**
      * Array of property to format mappings. Used for (de)serialization
      *
      * @var string[]
      */
    protected static $openAPIFormats = [
        'direction' => null,
        'reduce_only' => null,
        'triggered' => null,
        'order_id' => null,
        'price' => null,
        'time_in_force' => null,
        'api' => null,
        'order_state' => null,
        'implv' => null,
        'advanced' => null,
        'post_only' => null,
        'usd' => null,
        'stop_price' => null,
        'order_type' => null,
        'last_update_timestamp' => null,
        'original_order_type' => null,
        'max_show' => null,
        'profit_loss' => null,
        'is_liquidation' => null,
        'filled_amount' => null,
        'label' => null,
        'commission' => null,
        'amount' => null,
        'trigger' => null,
        'instrument_name' => null,
        'creation_timestamp' => null,
        'average_price' => null
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
        'reduce_only' => 'reduce_only',
        'triggered' => 'triggered',
        'order_id' => 'order_id',
        'price' => 'price',
        'time_in_force' => 'time_in_force',
        'api' => 'api',
        'order_state' => 'order_state',
        'implv' => 'implv',
        'advanced' => 'advanced',
        'post_only' => 'post_only',
        'usd' => 'usd',
        'stop_price' => 'stop_price',
        'order_type' => 'order_type',
        'last_update_timestamp' => 'last_update_timestamp',
        'original_order_type' => 'original_order_type',
        'max_show' => 'max_show',
        'profit_loss' => 'profit_loss',
        'is_liquidation' => 'is_liquidation',
        'filled_amount' => 'filled_amount',
        'label' => 'label',
        'commission' => 'commission',
        'amount' => 'amount',
        'trigger' => 'trigger',
        'instrument_name' => 'instrument_name',
        'creation_timestamp' => 'creation_timestamp',
        'average_price' => 'average_price'
    ];

    /**
     * Array of attributes to setter functions (for deserialization of responses)
     *
     * @var string[]
     */
    protected static $setters = [
        'direction' => 'setDirection',
        'reduce_only' => 'setReduceOnly',
        'triggered' => 'setTriggered',
        'order_id' => 'setOrderId',
        'price' => 'setPrice',
        'time_in_force' => 'setTimeInForce',
        'api' => 'setApi',
        'order_state' => 'setOrderState',
        'implv' => 'setImplv',
        'advanced' => 'setAdvanced',
        'post_only' => 'setPostOnly',
        'usd' => 'setUsd',
        'stop_price' => 'setStopPrice',
        'order_type' => 'setOrderType',
        'last_update_timestamp' => 'setLastUpdateTimestamp',
        'original_order_type' => 'setOriginalOrderType',
        'max_show' => 'setMaxShow',
        'profit_loss' => 'setProfitLoss',
        'is_liquidation' => 'setIsLiquidation',
        'filled_amount' => 'setFilledAmount',
        'label' => 'setLabel',
        'commission' => 'setCommission',
        'amount' => 'setAmount',
        'trigger' => 'setTrigger',
        'instrument_name' => 'setInstrumentName',
        'creation_timestamp' => 'setCreationTimestamp',
        'average_price' => 'setAveragePrice'
    ];

    /**
     * Array of attributes to getter functions (for serialization of requests)
     *
     * @var string[]
     */
    protected static $getters = [
        'direction' => 'getDirection',
        'reduce_only' => 'getReduceOnly',
        'triggered' => 'getTriggered',
        'order_id' => 'getOrderId',
        'price' => 'getPrice',
        'time_in_force' => 'getTimeInForce',
        'api' => 'getApi',
        'order_state' => 'getOrderState',
        'implv' => 'getImplv',
        'advanced' => 'getAdvanced',
        'post_only' => 'getPostOnly',
        'usd' => 'getUsd',
        'stop_price' => 'getStopPrice',
        'order_type' => 'getOrderType',
        'last_update_timestamp' => 'getLastUpdateTimestamp',
        'original_order_type' => 'getOriginalOrderType',
        'max_show' => 'getMaxShow',
        'profit_loss' => 'getProfitLoss',
        'is_liquidation' => 'getIsLiquidation',
        'filled_amount' => 'getFilledAmount',
        'label' => 'getLabel',
        'commission' => 'getCommission',
        'amount' => 'getAmount',
        'trigger' => 'getTrigger',
        'instrument_name' => 'getInstrumentName',
        'creation_timestamp' => 'getCreationTimestamp',
        'average_price' => 'getAveragePrice'
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
    const TIME_IN_FORCE_GOOD_TIL_CANCELLED = 'good_til_cancelled';
    const TIME_IN_FORCE_FILL_OR_KILL = 'fill_or_kill';
    const TIME_IN_FORCE_IMMEDIATE_OR_CANCEL = 'immediate_or_cancel';
    const ORDER_STATE_OPEN = 'open';
    const ORDER_STATE_FILLED = 'filled';
    const ORDER_STATE_REJECTED = 'rejected';
    const ORDER_STATE_CANCELLED = 'cancelled';
    const ORDER_STATE_UNTRIGGERED = 'untriggered';
    const ORDER_STATE_TRIGGERED = 'triggered';
    const ADVANCED_USD = 'usd';
    const ADVANCED_IMPLV = 'implv';
    const ORDER_TYPE_MARKET = 'market';
    const ORDER_TYPE_LIMIT = 'limit';
    const ORDER_TYPE_STOP_MARKET = 'stop_market';
    const ORDER_TYPE_STOP_LIMIT = 'stop_limit';
    const ORIGINAL_ORDER_TYPE_MARKET = 'market';
    const TRIGGER_INDEX_PRICE = 'index_price';
    const TRIGGER_MARK_PRICE = 'mark_price';
    const TRIGGER_LAST_PRICE = 'last_price';
    

    
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
    public function getTimeInForceAllowableValues()
    {
        return [
            self::TIME_IN_FORCE_GOOD_TIL_CANCELLED,
            self::TIME_IN_FORCE_FILL_OR_KILL,
            self::TIME_IN_FORCE_IMMEDIATE_OR_CANCEL,
        ];
    }
    
    /**
     * Gets allowable values of the enum
     *
     * @return string[]
     */
    public function getOrderStateAllowableValues()
    {
        return [
            self::ORDER_STATE_OPEN,
            self::ORDER_STATE_FILLED,
            self::ORDER_STATE_REJECTED,
            self::ORDER_STATE_CANCELLED,
            self::ORDER_STATE_UNTRIGGERED,
            self::ORDER_STATE_TRIGGERED,
        ];
    }
    
    /**
     * Gets allowable values of the enum
     *
     * @return string[]
     */
    public function getAdvancedAllowableValues()
    {
        return [
            self::ADVANCED_USD,
            self::ADVANCED_IMPLV,
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
            self::ORDER_TYPE_MARKET,
            self::ORDER_TYPE_LIMIT,
            self::ORDER_TYPE_STOP_MARKET,
            self::ORDER_TYPE_STOP_LIMIT,
        ];
    }
    
    /**
     * Gets allowable values of the enum
     *
     * @return string[]
     */
    public function getOriginalOrderTypeAllowableValues()
    {
        return [
            self::ORIGINAL_ORDER_TYPE_MARKET,
        ];
    }
    
    /**
     * Gets allowable values of the enum
     *
     * @return string[]
     */
    public function getTriggerAllowableValues()
    {
        return [
            self::TRIGGER_INDEX_PRICE,
            self::TRIGGER_MARK_PRICE,
            self::TRIGGER_LAST_PRICE,
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
        $this->container['reduce_only'] = isset($data['reduce_only']) ? $data['reduce_only'] : null;
        $this->container['triggered'] = isset($data['triggered']) ? $data['triggered'] : null;
        $this->container['order_id'] = isset($data['order_id']) ? $data['order_id'] : null;
        $this->container['price'] = isset($data['price']) ? $data['price'] : null;
        $this->container['time_in_force'] = isset($data['time_in_force']) ? $data['time_in_force'] : null;
        $this->container['api'] = isset($data['api']) ? $data['api'] : null;
        $this->container['order_state'] = isset($data['order_state']) ? $data['order_state'] : null;
        $this->container['implv'] = isset($data['implv']) ? $data['implv'] : null;
        $this->container['advanced'] = isset($data['advanced']) ? $data['advanced'] : null;
        $this->container['post_only'] = isset($data['post_only']) ? $data['post_only'] : null;
        $this->container['usd'] = isset($data['usd']) ? $data['usd'] : null;
        $this->container['stop_price'] = isset($data['stop_price']) ? $data['stop_price'] : null;
        $this->container['order_type'] = isset($data['order_type']) ? $data['order_type'] : null;
        $this->container['last_update_timestamp'] = isset($data['last_update_timestamp']) ? $data['last_update_timestamp'] : null;
        $this->container['original_order_type'] = isset($data['original_order_type']) ? $data['original_order_type'] : null;
        $this->container['max_show'] = isset($data['max_show']) ? $data['max_show'] : null;
        $this->container['profit_loss'] = isset($data['profit_loss']) ? $data['profit_loss'] : null;
        $this->container['is_liquidation'] = isset($data['is_liquidation']) ? $data['is_liquidation'] : null;
        $this->container['filled_amount'] = isset($data['filled_amount']) ? $data['filled_amount'] : null;
        $this->container['label'] = isset($data['label']) ? $data['label'] : null;
        $this->container['commission'] = isset($data['commission']) ? $data['commission'] : null;
        $this->container['amount'] = isset($data['amount']) ? $data['amount'] : null;
        $this->container['trigger'] = isset($data['trigger']) ? $data['trigger'] : null;
        $this->container['instrument_name'] = isset($data['instrument_name']) ? $data['instrument_name'] : null;
        $this->container['creation_timestamp'] = isset($data['creation_timestamp']) ? $data['creation_timestamp'] : null;
        $this->container['average_price'] = isset($data['average_price']) ? $data['average_price'] : null;
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

        if ($this->container['order_id'] === null) {
            $invalidProperties[] = "'order_id' can't be null";
        }
        if ($this->container['price'] === null) {
            $invalidProperties[] = "'price' can't be null";
        }
        if ($this->container['time_in_force'] === null) {
            $invalidProperties[] = "'time_in_force' can't be null";
        }
        $allowedValues = $this->getTimeInForceAllowableValues();
        if (!is_null($this->container['time_in_force']) && !in_array($this->container['time_in_force'], $allowedValues, true)) {
            $invalidProperties[] = sprintf(
                "invalid value for 'time_in_force', must be one of '%s'",
                implode("', '", $allowedValues)
            );
        }

        if ($this->container['api'] === null) {
            $invalidProperties[] = "'api' can't be null";
        }
        if ($this->container['order_state'] === null) {
            $invalidProperties[] = "'order_state' can't be null";
        }
        $allowedValues = $this->getOrderStateAllowableValues();
        if (!is_null($this->container['order_state']) && !in_array($this->container['order_state'], $allowedValues, true)) {
            $invalidProperties[] = sprintf(
                "invalid value for 'order_state', must be one of '%s'",
                implode("', '", $allowedValues)
            );
        }

        $allowedValues = $this->getAdvancedAllowableValues();
        if (!is_null($this->container['advanced']) && !in_array($this->container['advanced'], $allowedValues, true)) {
            $invalidProperties[] = sprintf(
                "invalid value for 'advanced', must be one of '%s'",
                implode("', '", $allowedValues)
            );
        }

        if ($this->container['post_only'] === null) {
            $invalidProperties[] = "'post_only' can't be null";
        }
        if ($this->container['order_type'] === null) {
            $invalidProperties[] = "'order_type' can't be null";
        }
        $allowedValues = $this->getOrderTypeAllowableValues();
        if (!is_null($this->container['order_type']) && !in_array($this->container['order_type'], $allowedValues, true)) {
            $invalidProperties[] = sprintf(
                "invalid value for 'order_type', must be one of '%s'",
                implode("', '", $allowedValues)
            );
        }

        if ($this->container['last_update_timestamp'] === null) {
            $invalidProperties[] = "'last_update_timestamp' can't be null";
        }
        $allowedValues = $this->getOriginalOrderTypeAllowableValues();
        if (!is_null($this->container['original_order_type']) && !in_array($this->container['original_order_type'], $allowedValues, true)) {
            $invalidProperties[] = sprintf(
                "invalid value for 'original_order_type', must be one of '%s'",
                implode("', '", $allowedValues)
            );
        }

        if ($this->container['max_show'] === null) {
            $invalidProperties[] = "'max_show' can't be null";
        }
        if ($this->container['is_liquidation'] === null) {
            $invalidProperties[] = "'is_liquidation' can't be null";
        }
        if ($this->container['label'] === null) {
            $invalidProperties[] = "'label' can't be null";
        }
        $allowedValues = $this->getTriggerAllowableValues();
        if (!is_null($this->container['trigger']) && !in_array($this->container['trigger'], $allowedValues, true)) {
            $invalidProperties[] = sprintf(
                "invalid value for 'trigger', must be one of '%s'",
                implode("', '", $allowedValues)
            );
        }

        if ($this->container['creation_timestamp'] === null) {
            $invalidProperties[] = "'creation_timestamp' can't be null";
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
     * @param string $direction direction, `buy` or `sell`
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
     * Gets reduce_only
     *
     * @return bool|null
     */
    public function getReduceOnly()
    {
        return $this->container['reduce_only'];
    }

    /**
     * Sets reduce_only
     *
     * @param bool|null $reduce_only `true` for reduce-only orders only
     *
     * @return $this
     */
    public function setReduceOnly($reduce_only)
    {
        $this->container['reduce_only'] = $reduce_only;

        return $this;
    }

    /**
     * Gets triggered
     *
     * @return bool|null
     */
    public function getTriggered()
    {
        return $this->container['triggered'];
    }

    /**
     * Sets triggered
     *
     * @param bool|null $triggered Whether the stop order has been triggered (Only for stop orders)
     *
     * @return $this
     */
    public function setTriggered($triggered)
    {
        $this->container['triggered'] = $triggered;

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
     * @param string $order_id Unique order identifier
     *
     * @return $this
     */
    public function setOrderId($order_id)
    {
        $this->container['order_id'] = $order_id;

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
     * @param float $price Price in base currency
     *
     * @return $this
     */
    public function setPrice($price)
    {
        $this->container['price'] = $price;

        return $this;
    }

    /**
     * Gets time_in_force
     *
     * @return string
     */
    public function getTimeInForce()
    {
        return $this->container['time_in_force'];
    }

    /**
     * Sets time_in_force
     *
     * @param string $time_in_force Order time in force: `\"good_til_cancelled\"`, `\"fill_or_kill\"`, `\"immediate_or_cancel\"`
     *
     * @return $this
     */
    public function setTimeInForce($time_in_force)
    {
        $allowedValues = $this->getTimeInForceAllowableValues();
        if (!in_array($time_in_force, $allowedValues, true)) {
            throw new \InvalidArgumentException(
                sprintf(
                    "Invalid value for 'time_in_force', must be one of '%s'",
                    implode("', '", $allowedValues)
                )
            );
        }
        $this->container['time_in_force'] = $time_in_force;

        return $this;
    }

    /**
     * Gets api
     *
     * @return bool
     */
    public function getApi()
    {
        return $this->container['api'];
    }

    /**
     * Sets api
     *
     * @param bool $api `true` if created with API
     *
     * @return $this
     */
    public function setApi($api)
    {
        $this->container['api'] = $api;

        return $this;
    }

    /**
     * Gets order_state
     *
     * @return string
     */
    public function getOrderState()
    {
        return $this->container['order_state'];
    }

    /**
     * Sets order_state
     *
     * @param string $order_state order state, `\"open\"`, `\"filled\"`, `\"rejected\"`, `\"cancelled\"`, `\"untriggered\"`
     *
     * @return $this
     */
    public function setOrderState($order_state)
    {
        $allowedValues = $this->getOrderStateAllowableValues();
        if (!in_array($order_state, $allowedValues, true)) {
            throw new \InvalidArgumentException(
                sprintf(
                    "Invalid value for 'order_state', must be one of '%s'",
                    implode("', '", $allowedValues)
                )
            );
        }
        $this->container['order_state'] = $order_state;

        return $this;
    }

    /**
     * Gets implv
     *
     * @return float|null
     */
    public function getImplv()
    {
        return $this->container['implv'];
    }

    /**
     * Sets implv
     *
     * @param float|null $implv Implied volatility in percent. (Only if `advanced=\"implv\"`)
     *
     * @return $this
     */
    public function setImplv($implv)
    {
        $this->container['implv'] = $implv;

        return $this;
    }

    /**
     * Gets advanced
     *
     * @return string|null
     */
    public function getAdvanced()
    {
        return $this->container['advanced'];
    }

    /**
     * Sets advanced
     *
     * @param string|null $advanced advanced type: `\"usd\"` or `\"implv\"` (Only for options; field is omitted if not applicable).
     *
     * @return $this
     */
    public function setAdvanced($advanced)
    {
        $allowedValues = $this->getAdvancedAllowableValues();
        if (!is_null($advanced) && !in_array($advanced, $allowedValues, true)) {
            throw new \InvalidArgumentException(
                sprintf(
                    "Invalid value for 'advanced', must be one of '%s'",
                    implode("', '", $allowedValues)
                )
            );
        }
        $this->container['advanced'] = $advanced;

        return $this;
    }

    /**
     * Gets post_only
     *
     * @return bool
     */
    public function getPostOnly()
    {
        return $this->container['post_only'];
    }

    /**
     * Sets post_only
     *
     * @param bool $post_only `true` for post-only orders only
     *
     * @return $this
     */
    public function setPostOnly($post_only)
    {
        $this->container['post_only'] = $post_only;

        return $this;
    }

    /**
     * Gets usd
     *
     * @return float|null
     */
    public function getUsd()
    {
        return $this->container['usd'];
    }

    /**
     * Sets usd
     *
     * @param float|null $usd Option price in USD (Only if `advanced=\"usd\"`)
     *
     * @return $this
     */
    public function setUsd($usd)
    {
        $this->container['usd'] = $usd;

        return $this;
    }

    /**
     * Gets stop_price
     *
     * @return float|null
     */
    public function getStopPrice()
    {
        return $this->container['stop_price'];
    }

    /**
     * Sets stop_price
     *
     * @param float|null $stop_price stop price (Only for future stop orders)
     *
     * @return $this
     */
    public function setStopPrice($stop_price)
    {
        $this->container['stop_price'] = $stop_price;

        return $this;
    }

    /**
     * Gets order_type
     *
     * @return string
     */
    public function getOrderType()
    {
        return $this->container['order_type'];
    }

    /**
     * Sets order_type
     *
     * @param string $order_type order type, `\"limit\"`, `\"market\"`, `\"stop_limit\"`, `\"stop_market\"`
     *
     * @return $this
     */
    public function setOrderType($order_type)
    {
        $allowedValues = $this->getOrderTypeAllowableValues();
        if (!in_array($order_type, $allowedValues, true)) {
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
     * Gets last_update_timestamp
     *
     * @return int
     */
    public function getLastUpdateTimestamp()
    {
        return $this->container['last_update_timestamp'];
    }

    /**
     * Sets last_update_timestamp
     *
     * @param int $last_update_timestamp The timestamp (seconds since the Unix epoch, with millisecond precision)
     *
     * @return $this
     */
    public function setLastUpdateTimestamp($last_update_timestamp)
    {
        $this->container['last_update_timestamp'] = $last_update_timestamp;

        return $this;
    }

    /**
     * Gets original_order_type
     *
     * @return string|null
     */
    public function getOriginalOrderType()
    {
        return $this->container['original_order_type'];
    }

    /**
     * Sets original_order_type
     *
     * @param string|null $original_order_type Original order type. Optional field
     *
     * @return $this
     */
    public function setOriginalOrderType($original_order_type)
    {
        $allowedValues = $this->getOriginalOrderTypeAllowableValues();
        if (!is_null($original_order_type) && !in_array($original_order_type, $allowedValues, true)) {
            throw new \InvalidArgumentException(
                sprintf(
                    "Invalid value for 'original_order_type', must be one of '%s'",
                    implode("', '", $allowedValues)
                )
            );
        }
        $this->container['original_order_type'] = $original_order_type;

        return $this;
    }

    /**
     * Gets max_show
     *
     * @return float
     */
    public function getMaxShow()
    {
        return $this->container['max_show'];
    }

    /**
     * Sets max_show
     *
     * @param float $max_show Maximum amount within an order to be shown to other traders, 0 for invisible order.
     *
     * @return $this
     */
    public function setMaxShow($max_show)
    {
        $this->container['max_show'] = $max_show;

        return $this;
    }

    /**
     * Gets profit_loss
     *
     * @return float|null
     */
    public function getProfitLoss()
    {
        return $this->container['profit_loss'];
    }

    /**
     * Sets profit_loss
     *
     * @param float|null $profit_loss Profit and loss in base currency.
     *
     * @return $this
     */
    public function setProfitLoss($profit_loss)
    {
        $this->container['profit_loss'] = $profit_loss;

        return $this;
    }

    /**
     * Gets is_liquidation
     *
     * @return bool
     */
    public function getIsLiquidation()
    {
        return $this->container['is_liquidation'];
    }

    /**
     * Sets is_liquidation
     *
     * @param bool $is_liquidation `true` if order was automatically created during liquidation
     *
     * @return $this
     */
    public function setIsLiquidation($is_liquidation)
    {
        $this->container['is_liquidation'] = $is_liquidation;

        return $this;
    }

    /**
     * Gets filled_amount
     *
     * @return float|null
     */
    public function getFilledAmount()
    {
        return $this->container['filled_amount'];
    }

    /**
     * Sets filled_amount
     *
     * @param float|null $filled_amount Filled amount of the order. For perpetual and futures the filled_amount is in USD units, for options - in units or corresponding cryptocurrency contracts, e.g., BTC or ETH.
     *
     * @return $this
     */
    public function setFilledAmount($filled_amount)
    {
        $this->container['filled_amount'] = $filled_amount;

        return $this;
    }

    /**
     * Gets label
     *
     * @return string
     */
    public function getLabel()
    {
        return $this->container['label'];
    }

    /**
     * Sets label
     *
     * @param string $label user defined label (up to 32 characters)
     *
     * @return $this
     */
    public function setLabel($label)
    {
        $this->container['label'] = $label;

        return $this;
    }

    /**
     * Gets commission
     *
     * @return float|null
     */
    public function getCommission()
    {
        return $this->container['commission'];
    }

    /**
     * Sets commission
     *
     * @param float|null $commission Commission paid so far (in base currency)
     *
     * @return $this
     */
    public function setCommission($commission)
    {
        $this->container['commission'] = $commission;

        return $this;
    }

    /**
     * Gets amount
     *
     * @return float|null
     */
    public function getAmount()
    {
        return $this->container['amount'];
    }

    /**
     * Sets amount
     *
     * @param float|null $amount It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH.
     *
     * @return $this
     */
    public function setAmount($amount)
    {
        $this->container['amount'] = $amount;

        return $this;
    }

    /**
     * Gets trigger
     *
     * @return string|null
     */
    public function getTrigger()
    {
        return $this->container['trigger'];
    }

    /**
     * Sets trigger
     *
     * @param string|null $trigger Trigger type (Only for stop orders). Allowed values: `\"index_price\"`, `\"mark_price\"`, `\"last_price\"`.
     *
     * @return $this
     */
    public function setTrigger($trigger)
    {
        $allowedValues = $this->getTriggerAllowableValues();
        if (!is_null($trigger) && !in_array($trigger, $allowedValues, true)) {
            throw new \InvalidArgumentException(
                sprintf(
                    "Invalid value for 'trigger', must be one of '%s'",
                    implode("', '", $allowedValues)
                )
            );
        }
        $this->container['trigger'] = $trigger;

        return $this;
    }

    /**
     * Gets instrument_name
     *
     * @return string|null
     */
    public function getInstrumentName()
    {
        return $this->container['instrument_name'];
    }

    /**
     * Sets instrument_name
     *
     * @param string|null $instrument_name Unique instrument identifier
     *
     * @return $this
     */
    public function setInstrumentName($instrument_name)
    {
        $this->container['instrument_name'] = $instrument_name;

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
     * @param int $creation_timestamp The timestamp (seconds since the Unix epoch, with millisecond precision)
     *
     * @return $this
     */
    public function setCreationTimestamp($creation_timestamp)
    {
        $this->container['creation_timestamp'] = $creation_timestamp;

        return $this;
    }

    /**
     * Gets average_price
     *
     * @return float|null
     */
    public function getAveragePrice()
    {
        return $this->container['average_price'];
    }

    /**
     * Sets average_price
     *
     * @param float|null $average_price Average fill price of the order
     *
     * @return $this
     */
    public function setAveragePrice($average_price)
    {
        $this->container['average_price'] = $average_price;

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


