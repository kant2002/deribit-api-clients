<?php
/**
 * Position
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
 * Position Class Doc Comment
 *
 * @category Class
 * @package  OpenAPI\Client
 * @author   OpenAPI Generator team
 * @link     https://openapi-generator.tech
 */
class Position implements ModelInterface, ArrayAccess
{
    const DISCRIMINATOR = null;

    /**
      * The original name of the model.
      *
      * @var string
      */
    protected static $openAPIModelName = 'position';

    /**
      * Array of property to type mappings. Used for (de)serialization
      *
      * @var string[]
      */
    protected static $openAPITypes = [
        'direction' => 'string',
        'average_price_usd' => 'float',
        'estimated_liquidation_price' => 'float',
        'floating_profit_loss' => 'float',
        'floating_profit_loss_usd' => 'float',
        'open_orders_margin' => 'float',
        'total_profit_loss' => 'float',
        'realized_profit_loss' => 'float',
        'delta' => 'float',
        'initial_margin' => 'float',
        'size' => 'float',
        'maintenance_margin' => 'float',
        'kind' => 'string',
        'mark_price' => 'float',
        'average_price' => 'float',
        'settlement_price' => 'float',
        'index_price' => 'float',
        'instrument_name' => 'string',
        'size_currency' => 'float'
    ];

    /**
      * Array of property to format mappings. Used for (de)serialization
      *
      * @var string[]
      */
    protected static $openAPIFormats = [
        'direction' => null,
        'average_price_usd' => null,
        'estimated_liquidation_price' => null,
        'floating_profit_loss' => null,
        'floating_profit_loss_usd' => null,
        'open_orders_margin' => null,
        'total_profit_loss' => null,
        'realized_profit_loss' => null,
        'delta' => null,
        'initial_margin' => null,
        'size' => null,
        'maintenance_margin' => null,
        'kind' => null,
        'mark_price' => null,
        'average_price' => null,
        'settlement_price' => null,
        'index_price' => null,
        'instrument_name' => null,
        'size_currency' => null
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
        'average_price_usd' => 'average_price_usd',
        'estimated_liquidation_price' => 'estimated_liquidation_price',
        'floating_profit_loss' => 'floating_profit_loss',
        'floating_profit_loss_usd' => 'floating_profit_loss_usd',
        'open_orders_margin' => 'open_orders_margin',
        'total_profit_loss' => 'total_profit_loss',
        'realized_profit_loss' => 'realized_profit_loss',
        'delta' => 'delta',
        'initial_margin' => 'initial_margin',
        'size' => 'size',
        'maintenance_margin' => 'maintenance_margin',
        'kind' => 'kind',
        'mark_price' => 'mark_price',
        'average_price' => 'average_price',
        'settlement_price' => 'settlement_price',
        'index_price' => 'index_price',
        'instrument_name' => 'instrument_name',
        'size_currency' => 'size_currency'
    ];

    /**
     * Array of attributes to setter functions (for deserialization of responses)
     *
     * @var string[]
     */
    protected static $setters = [
        'direction' => 'setDirection',
        'average_price_usd' => 'setAveragePriceUsd',
        'estimated_liquidation_price' => 'setEstimatedLiquidationPrice',
        'floating_profit_loss' => 'setFloatingProfitLoss',
        'floating_profit_loss_usd' => 'setFloatingProfitLossUsd',
        'open_orders_margin' => 'setOpenOrdersMargin',
        'total_profit_loss' => 'setTotalProfitLoss',
        'realized_profit_loss' => 'setRealizedProfitLoss',
        'delta' => 'setDelta',
        'initial_margin' => 'setInitialMargin',
        'size' => 'setSize',
        'maintenance_margin' => 'setMaintenanceMargin',
        'kind' => 'setKind',
        'mark_price' => 'setMarkPrice',
        'average_price' => 'setAveragePrice',
        'settlement_price' => 'setSettlementPrice',
        'index_price' => 'setIndexPrice',
        'instrument_name' => 'setInstrumentName',
        'size_currency' => 'setSizeCurrency'
    ];

    /**
     * Array of attributes to getter functions (for serialization of requests)
     *
     * @var string[]
     */
    protected static $getters = [
        'direction' => 'getDirection',
        'average_price_usd' => 'getAveragePriceUsd',
        'estimated_liquidation_price' => 'getEstimatedLiquidationPrice',
        'floating_profit_loss' => 'getFloatingProfitLoss',
        'floating_profit_loss_usd' => 'getFloatingProfitLossUsd',
        'open_orders_margin' => 'getOpenOrdersMargin',
        'total_profit_loss' => 'getTotalProfitLoss',
        'realized_profit_loss' => 'getRealizedProfitLoss',
        'delta' => 'getDelta',
        'initial_margin' => 'getInitialMargin',
        'size' => 'getSize',
        'maintenance_margin' => 'getMaintenanceMargin',
        'kind' => 'getKind',
        'mark_price' => 'getMarkPrice',
        'average_price' => 'getAveragePrice',
        'settlement_price' => 'getSettlementPrice',
        'index_price' => 'getIndexPrice',
        'instrument_name' => 'getInstrumentName',
        'size_currency' => 'getSizeCurrency'
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
    const KIND_FUTURE = 'future';
    const KIND_OPTION = 'option';
    

    
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
    public function getKindAllowableValues()
    {
        return [
            self::KIND_FUTURE,
            self::KIND_OPTION,
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
        $this->container['average_price_usd'] = isset($data['average_price_usd']) ? $data['average_price_usd'] : null;
        $this->container['estimated_liquidation_price'] = isset($data['estimated_liquidation_price']) ? $data['estimated_liquidation_price'] : null;
        $this->container['floating_profit_loss'] = isset($data['floating_profit_loss']) ? $data['floating_profit_loss'] : null;
        $this->container['floating_profit_loss_usd'] = isset($data['floating_profit_loss_usd']) ? $data['floating_profit_loss_usd'] : null;
        $this->container['open_orders_margin'] = isset($data['open_orders_margin']) ? $data['open_orders_margin'] : null;
        $this->container['total_profit_loss'] = isset($data['total_profit_loss']) ? $data['total_profit_loss'] : null;
        $this->container['realized_profit_loss'] = isset($data['realized_profit_loss']) ? $data['realized_profit_loss'] : null;
        $this->container['delta'] = isset($data['delta']) ? $data['delta'] : null;
        $this->container['initial_margin'] = isset($data['initial_margin']) ? $data['initial_margin'] : null;
        $this->container['size'] = isset($data['size']) ? $data['size'] : null;
        $this->container['maintenance_margin'] = isset($data['maintenance_margin']) ? $data['maintenance_margin'] : null;
        $this->container['kind'] = isset($data['kind']) ? $data['kind'] : null;
        $this->container['mark_price'] = isset($data['mark_price']) ? $data['mark_price'] : null;
        $this->container['average_price'] = isset($data['average_price']) ? $data['average_price'] : null;
        $this->container['settlement_price'] = isset($data['settlement_price']) ? $data['settlement_price'] : null;
        $this->container['index_price'] = isset($data['index_price']) ? $data['index_price'] : null;
        $this->container['instrument_name'] = isset($data['instrument_name']) ? $data['instrument_name'] : null;
        $this->container['size_currency'] = isset($data['size_currency']) ? $data['size_currency'] : null;
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

        if ($this->container['floating_profit_loss'] === null) {
            $invalidProperties[] = "'floating_profit_loss' can't be null";
        }
        if ($this->container['open_orders_margin'] === null) {
            $invalidProperties[] = "'open_orders_margin' can't be null";
        }
        if ($this->container['total_profit_loss'] === null) {
            $invalidProperties[] = "'total_profit_loss' can't be null";
        }
        if ($this->container['delta'] === null) {
            $invalidProperties[] = "'delta' can't be null";
        }
        if ($this->container['initial_margin'] === null) {
            $invalidProperties[] = "'initial_margin' can't be null";
        }
        if ($this->container['size'] === null) {
            $invalidProperties[] = "'size' can't be null";
        }
        if ($this->container['maintenance_margin'] === null) {
            $invalidProperties[] = "'maintenance_margin' can't be null";
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

        if ($this->container['mark_price'] === null) {
            $invalidProperties[] = "'mark_price' can't be null";
        }
        if ($this->container['average_price'] === null) {
            $invalidProperties[] = "'average_price' can't be null";
        }
        if ($this->container['settlement_price'] === null) {
            $invalidProperties[] = "'settlement_price' can't be null";
        }
        if ($this->container['index_price'] === null) {
            $invalidProperties[] = "'index_price' can't be null";
        }
        if ($this->container['instrument_name'] === null) {
            $invalidProperties[] = "'instrument_name' can't be null";
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
     * Gets average_price_usd
     *
     * @return float|null
     */
    public function getAveragePriceUsd()
    {
        return $this->container['average_price_usd'];
    }

    /**
     * Sets average_price_usd
     *
     * @param float|null $average_price_usd Only for options, average price in USD
     *
     * @return $this
     */
    public function setAveragePriceUsd($average_price_usd)
    {
        $this->container['average_price_usd'] = $average_price_usd;

        return $this;
    }

    /**
     * Gets estimated_liquidation_price
     *
     * @return float|null
     */
    public function getEstimatedLiquidationPrice()
    {
        return $this->container['estimated_liquidation_price'];
    }

    /**
     * Sets estimated_liquidation_price
     *
     * @param float|null $estimated_liquidation_price Only for futures, estimated liquidation price
     *
     * @return $this
     */
    public function setEstimatedLiquidationPrice($estimated_liquidation_price)
    {
        $this->container['estimated_liquidation_price'] = $estimated_liquidation_price;

        return $this;
    }

    /**
     * Gets floating_profit_loss
     *
     * @return float
     */
    public function getFloatingProfitLoss()
    {
        return $this->container['floating_profit_loss'];
    }

    /**
     * Sets floating_profit_loss
     *
     * @param float $floating_profit_loss Floating profit or loss
     *
     * @return $this
     */
    public function setFloatingProfitLoss($floating_profit_loss)
    {
        $this->container['floating_profit_loss'] = $floating_profit_loss;

        return $this;
    }

    /**
     * Gets floating_profit_loss_usd
     *
     * @return float|null
     */
    public function getFloatingProfitLossUsd()
    {
        return $this->container['floating_profit_loss_usd'];
    }

    /**
     * Sets floating_profit_loss_usd
     *
     * @param float|null $floating_profit_loss_usd Only for options, floating profit or loss in USD
     *
     * @return $this
     */
    public function setFloatingProfitLossUsd($floating_profit_loss_usd)
    {
        $this->container['floating_profit_loss_usd'] = $floating_profit_loss_usd;

        return $this;
    }

    /**
     * Gets open_orders_margin
     *
     * @return float
     */
    public function getOpenOrdersMargin()
    {
        return $this->container['open_orders_margin'];
    }

    /**
     * Sets open_orders_margin
     *
     * @param float $open_orders_margin Open orders margin
     *
     * @return $this
     */
    public function setOpenOrdersMargin($open_orders_margin)
    {
        $this->container['open_orders_margin'] = $open_orders_margin;

        return $this;
    }

    /**
     * Gets total_profit_loss
     *
     * @return float
     */
    public function getTotalProfitLoss()
    {
        return $this->container['total_profit_loss'];
    }

    /**
     * Sets total_profit_loss
     *
     * @param float $total_profit_loss Profit or loss from position
     *
     * @return $this
     */
    public function setTotalProfitLoss($total_profit_loss)
    {
        $this->container['total_profit_loss'] = $total_profit_loss;

        return $this;
    }

    /**
     * Gets realized_profit_loss
     *
     * @return float|null
     */
    public function getRealizedProfitLoss()
    {
        return $this->container['realized_profit_loss'];
    }

    /**
     * Sets realized_profit_loss
     *
     * @param float|null $realized_profit_loss Realized profit or loss
     *
     * @return $this
     */
    public function setRealizedProfitLoss($realized_profit_loss)
    {
        $this->container['realized_profit_loss'] = $realized_profit_loss;

        return $this;
    }

    /**
     * Gets delta
     *
     * @return float
     */
    public function getDelta()
    {
        return $this->container['delta'];
    }

    /**
     * Sets delta
     *
     * @param float $delta Delta parameter
     *
     * @return $this
     */
    public function setDelta($delta)
    {
        $this->container['delta'] = $delta;

        return $this;
    }

    /**
     * Gets initial_margin
     *
     * @return float
     */
    public function getInitialMargin()
    {
        return $this->container['initial_margin'];
    }

    /**
     * Sets initial_margin
     *
     * @param float $initial_margin Initial margin
     *
     * @return $this
     */
    public function setInitialMargin($initial_margin)
    {
        $this->container['initial_margin'] = $initial_margin;

        return $this;
    }

    /**
     * Gets size
     *
     * @return float
     */
    public function getSize()
    {
        return $this->container['size'];
    }

    /**
     * Sets size
     *
     * @param float $size Position size for futures size in quote currency (e.g. USD), for options size is in base currency (e.g. BTC)
     *
     * @return $this
     */
    public function setSize($size)
    {
        $this->container['size'] = $size;

        return $this;
    }

    /**
     * Gets maintenance_margin
     *
     * @return float
     */
    public function getMaintenanceMargin()
    {
        return $this->container['maintenance_margin'];
    }

    /**
     * Sets maintenance_margin
     *
     * @param float $maintenance_margin Maintenance margin
     *
     * @return $this
     */
    public function setMaintenanceMargin($maintenance_margin)
    {
        $this->container['maintenance_margin'] = $maintenance_margin;

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
     * Gets mark_price
     *
     * @return float
     */
    public function getMarkPrice()
    {
        return $this->container['mark_price'];
    }

    /**
     * Sets mark_price
     *
     * @param float $mark_price Current mark price for position's instrument
     *
     * @return $this
     */
    public function setMarkPrice($mark_price)
    {
        $this->container['mark_price'] = $mark_price;

        return $this;
    }

    /**
     * Gets average_price
     *
     * @return float
     */
    public function getAveragePrice()
    {
        return $this->container['average_price'];
    }

    /**
     * Sets average_price
     *
     * @param float $average_price Average price of trades that built this position
     *
     * @return $this
     */
    public function setAveragePrice($average_price)
    {
        $this->container['average_price'] = $average_price;

        return $this;
    }

    /**
     * Gets settlement_price
     *
     * @return float
     */
    public function getSettlementPrice()
    {
        return $this->container['settlement_price'];
    }

    /**
     * Sets settlement_price
     *
     * @param float $settlement_price Last settlement price for position's instrument 0 if instrument wasn't settled yet
     *
     * @return $this
     */
    public function setSettlementPrice($settlement_price)
    {
        $this->container['settlement_price'] = $settlement_price;

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
     * @param float $index_price Current index price
     *
     * @return $this
     */
    public function setIndexPrice($index_price)
    {
        $this->container['index_price'] = $index_price;

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
     * Gets size_currency
     *
     * @return float|null
     */
    public function getSizeCurrency()
    {
        return $this->container['size_currency'];
    }

    /**
     * Sets size_currency
     *
     * @param float|null $size_currency Only for futures, position size in base currency
     *
     * @return $this
     */
    public function setSizeCurrency($size_currency)
    {
        $this->container['size_currency'] = $size_currency;

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


