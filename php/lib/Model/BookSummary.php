<?php
/**
 * BookSummary
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
 * BookSummary Class Doc Comment
 *
 * @category Class
 * @package  OpenAPI\Client
 * @author   OpenAPI Generator team
 * @link     https://openapi-generator.tech
 */
class BookSummary implements ModelInterface, ArrayAccess
{
    const DISCRIMINATOR = null;

    /**
      * The original name of the model.
      *
      * @var string
      */
    protected static $openAPIModelName = 'book_summary';

    /**
      * Array of property to type mappings. Used for (de)serialization
      *
      * @var string[]
      */
    protected static $openAPITypes = [
        'underlying_index' => 'string',
        'volume' => 'float',
        'volume_usd' => 'float',
        'underlying_price' => 'float',
        'bid_price' => 'float',
        'open_interest' => 'float',
        'quote_currency' => 'string',
        'high' => 'float',
        'estimated_delivery_price' => 'float',
        'last' => 'float',
        'mid_price' => 'float',
        'interest_rate' => 'float',
        'funding_8h' => 'float',
        'mark_price' => 'float',
        'ask_price' => 'float',
        'instrument_name' => 'string',
        'low' => 'float',
        'base_currency' => 'string',
        'creation_timestamp' => 'int',
        'current_funding' => 'float'
    ];

    /**
      * Array of property to format mappings. Used for (de)serialization
      *
      * @var string[]
      */
    protected static $openAPIFormats = [
        'underlying_index' => null,
        'volume' => null,
        'volume_usd' => null,
        'underlying_price' => null,
        'bid_price' => null,
        'open_interest' => null,
        'quote_currency' => null,
        'high' => null,
        'estimated_delivery_price' => null,
        'last' => null,
        'mid_price' => null,
        'interest_rate' => null,
        'funding_8h' => null,
        'mark_price' => null,
        'ask_price' => null,
        'instrument_name' => null,
        'low' => null,
        'base_currency' => null,
        'creation_timestamp' => null,
        'current_funding' => null
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
        'underlying_index' => 'underlying_index',
        'volume' => 'volume',
        'volume_usd' => 'volume_usd',
        'underlying_price' => 'underlying_price',
        'bid_price' => 'bid_price',
        'open_interest' => 'open_interest',
        'quote_currency' => 'quote_currency',
        'high' => 'high',
        'estimated_delivery_price' => 'estimated_delivery_price',
        'last' => 'last',
        'mid_price' => 'mid_price',
        'interest_rate' => 'interest_rate',
        'funding_8h' => 'funding_8h',
        'mark_price' => 'mark_price',
        'ask_price' => 'ask_price',
        'instrument_name' => 'instrument_name',
        'low' => 'low',
        'base_currency' => 'base_currency',
        'creation_timestamp' => 'creation_timestamp',
        'current_funding' => 'current_funding'
    ];

    /**
     * Array of attributes to setter functions (for deserialization of responses)
     *
     * @var string[]
     */
    protected static $setters = [
        'underlying_index' => 'setUnderlyingIndex',
        'volume' => 'setVolume',
        'volume_usd' => 'setVolumeUsd',
        'underlying_price' => 'setUnderlyingPrice',
        'bid_price' => 'setBidPrice',
        'open_interest' => 'setOpenInterest',
        'quote_currency' => 'setQuoteCurrency',
        'high' => 'setHigh',
        'estimated_delivery_price' => 'setEstimatedDeliveryPrice',
        'last' => 'setLast',
        'mid_price' => 'setMidPrice',
        'interest_rate' => 'setInterestRate',
        'funding_8h' => 'setFunding8h',
        'mark_price' => 'setMarkPrice',
        'ask_price' => 'setAskPrice',
        'instrument_name' => 'setInstrumentName',
        'low' => 'setLow',
        'base_currency' => 'setBaseCurrency',
        'creation_timestamp' => 'setCreationTimestamp',
        'current_funding' => 'setCurrentFunding'
    ];

    /**
     * Array of attributes to getter functions (for serialization of requests)
     *
     * @var string[]
     */
    protected static $getters = [
        'underlying_index' => 'getUnderlyingIndex',
        'volume' => 'getVolume',
        'volume_usd' => 'getVolumeUsd',
        'underlying_price' => 'getUnderlyingPrice',
        'bid_price' => 'getBidPrice',
        'open_interest' => 'getOpenInterest',
        'quote_currency' => 'getQuoteCurrency',
        'high' => 'getHigh',
        'estimated_delivery_price' => 'getEstimatedDeliveryPrice',
        'last' => 'getLast',
        'mid_price' => 'getMidPrice',
        'interest_rate' => 'getInterestRate',
        'funding_8h' => 'getFunding8h',
        'mark_price' => 'getMarkPrice',
        'ask_price' => 'getAskPrice',
        'instrument_name' => 'getInstrumentName',
        'low' => 'getLow',
        'base_currency' => 'getBaseCurrency',
        'creation_timestamp' => 'getCreationTimestamp',
        'current_funding' => 'getCurrentFunding'
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
        $this->container['underlying_index'] = isset($data['underlying_index']) ? $data['underlying_index'] : null;
        $this->container['volume'] = isset($data['volume']) ? $data['volume'] : null;
        $this->container['volume_usd'] = isset($data['volume_usd']) ? $data['volume_usd'] : null;
        $this->container['underlying_price'] = isset($data['underlying_price']) ? $data['underlying_price'] : null;
        $this->container['bid_price'] = isset($data['bid_price']) ? $data['bid_price'] : null;
        $this->container['open_interest'] = isset($data['open_interest']) ? $data['open_interest'] : null;
        $this->container['quote_currency'] = isset($data['quote_currency']) ? $data['quote_currency'] : null;
        $this->container['high'] = isset($data['high']) ? $data['high'] : null;
        $this->container['estimated_delivery_price'] = isset($data['estimated_delivery_price']) ? $data['estimated_delivery_price'] : null;
        $this->container['last'] = isset($data['last']) ? $data['last'] : null;
        $this->container['mid_price'] = isset($data['mid_price']) ? $data['mid_price'] : null;
        $this->container['interest_rate'] = isset($data['interest_rate']) ? $data['interest_rate'] : null;
        $this->container['funding_8h'] = isset($data['funding_8h']) ? $data['funding_8h'] : null;
        $this->container['mark_price'] = isset($data['mark_price']) ? $data['mark_price'] : null;
        $this->container['ask_price'] = isset($data['ask_price']) ? $data['ask_price'] : null;
        $this->container['instrument_name'] = isset($data['instrument_name']) ? $data['instrument_name'] : null;
        $this->container['low'] = isset($data['low']) ? $data['low'] : null;
        $this->container['base_currency'] = isset($data['base_currency']) ? $data['base_currency'] : null;
        $this->container['creation_timestamp'] = isset($data['creation_timestamp']) ? $data['creation_timestamp'] : null;
        $this->container['current_funding'] = isset($data['current_funding']) ? $data['current_funding'] : null;
    }

    /**
     * Show all the invalid properties with reasons.
     *
     * @return array invalid properties with reasons
     */
    public function listInvalidProperties()
    {
        $invalidProperties = [];

        if ($this->container['volume'] === null) {
            $invalidProperties[] = "'volume' can't be null";
        }
        if ($this->container['bid_price'] === null) {
            $invalidProperties[] = "'bid_price' can't be null";
        }
        if ($this->container['open_interest'] === null) {
            $invalidProperties[] = "'open_interest' can't be null";
        }
        if ($this->container['quote_currency'] === null) {
            $invalidProperties[] = "'quote_currency' can't be null";
        }
        if ($this->container['high'] === null) {
            $invalidProperties[] = "'high' can't be null";
        }
        if ($this->container['last'] === null) {
            $invalidProperties[] = "'last' can't be null";
        }
        if ($this->container['mid_price'] === null) {
            $invalidProperties[] = "'mid_price' can't be null";
        }
        if ($this->container['mark_price'] === null) {
            $invalidProperties[] = "'mark_price' can't be null";
        }
        if ($this->container['ask_price'] === null) {
            $invalidProperties[] = "'ask_price' can't be null";
        }
        if ($this->container['instrument_name'] === null) {
            $invalidProperties[] = "'instrument_name' can't be null";
        }
        if ($this->container['low'] === null) {
            $invalidProperties[] = "'low' can't be null";
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
     * Gets underlying_index
     *
     * @return string|null
     */
    public function getUnderlyingIndex()
    {
        return $this->container['underlying_index'];
    }

    /**
     * Sets underlying_index
     *
     * @param string|null $underlying_index Name of the underlying future, or `'index_price'` (options only)
     *
     * @return $this
     */
    public function setUnderlyingIndex($underlying_index)
    {
        $this->container['underlying_index'] = $underlying_index;

        return $this;
    }

    /**
     * Gets volume
     *
     * @return float
     */
    public function getVolume()
    {
        return $this->container['volume'];
    }

    /**
     * Sets volume
     *
     * @param float $volume The total 24h traded volume (in base currency)
     *
     * @return $this
     */
    public function setVolume($volume)
    {
        $this->container['volume'] = $volume;

        return $this;
    }

    /**
     * Gets volume_usd
     *
     * @return float|null
     */
    public function getVolumeUsd()
    {
        return $this->container['volume_usd'];
    }

    /**
     * Sets volume_usd
     *
     * @param float|null $volume_usd Volume in usd (futures only)
     *
     * @return $this
     */
    public function setVolumeUsd($volume_usd)
    {
        $this->container['volume_usd'] = $volume_usd;

        return $this;
    }

    /**
     * Gets underlying_price
     *
     * @return float|null
     */
    public function getUnderlyingPrice()
    {
        return $this->container['underlying_price'];
    }

    /**
     * Sets underlying_price
     *
     * @param float|null $underlying_price underlying price for implied volatility calculations (options only)
     *
     * @return $this
     */
    public function setUnderlyingPrice($underlying_price)
    {
        $this->container['underlying_price'] = $underlying_price;

        return $this;
    }

    /**
     * Gets bid_price
     *
     * @return float
     */
    public function getBidPrice()
    {
        return $this->container['bid_price'];
    }

    /**
     * Sets bid_price
     *
     * @param float $bid_price The current best bid price, `null` if there aren't any bids
     *
     * @return $this
     */
    public function setBidPrice($bid_price)
    {
        $this->container['bid_price'] = $bid_price;

        return $this;
    }

    /**
     * Gets open_interest
     *
     * @return float
     */
    public function getOpenInterest()
    {
        return $this->container['open_interest'];
    }

    /**
     * Sets open_interest
     *
     * @param float $open_interest The total amount of outstanding contracts in the corresponding amount units. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH.
     *
     * @return $this
     */
    public function setOpenInterest($open_interest)
    {
        $this->container['open_interest'] = $open_interest;

        return $this;
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
     * @param string $quote_currency Quote currency
     *
     * @return $this
     */
    public function setQuoteCurrency($quote_currency)
    {
        $this->container['quote_currency'] = $quote_currency;

        return $this;
    }

    /**
     * Gets high
     *
     * @return float
     */
    public function getHigh()
    {
        return $this->container['high'];
    }

    /**
     * Sets high
     *
     * @param float $high Price of the 24h highest trade
     *
     * @return $this
     */
    public function setHigh($high)
    {
        $this->container['high'] = $high;

        return $this;
    }

    /**
     * Gets estimated_delivery_price
     *
     * @return float|null
     */
    public function getEstimatedDeliveryPrice()
    {
        return $this->container['estimated_delivery_price'];
    }

    /**
     * Sets estimated_delivery_price
     *
     * @param float|null $estimated_delivery_price Estimated delivery price, in USD (futures only). For more details, see Documentation > General > Expiration Price
     *
     * @return $this
     */
    public function setEstimatedDeliveryPrice($estimated_delivery_price)
    {
        $this->container['estimated_delivery_price'] = $estimated_delivery_price;

        return $this;
    }

    /**
     * Gets last
     *
     * @return float
     */
    public function getLast()
    {
        return $this->container['last'];
    }

    /**
     * Sets last
     *
     * @param float $last The price of the latest trade, `null` if there weren't any trades
     *
     * @return $this
     */
    public function setLast($last)
    {
        $this->container['last'] = $last;

        return $this;
    }

    /**
     * Gets mid_price
     *
     * @return float
     */
    public function getMidPrice()
    {
        return $this->container['mid_price'];
    }

    /**
     * Sets mid_price
     *
     * @param float $mid_price The average of the best bid and ask, `null` if there aren't any asks or bids
     *
     * @return $this
     */
    public function setMidPrice($mid_price)
    {
        $this->container['mid_price'] = $mid_price;

        return $this;
    }

    /**
     * Gets interest_rate
     *
     * @return float|null
     */
    public function getInterestRate()
    {
        return $this->container['interest_rate'];
    }

    /**
     * Sets interest_rate
     *
     * @param float|null $interest_rate Interest rate used in implied volatility calculations (options only)
     *
     * @return $this
     */
    public function setInterestRate($interest_rate)
    {
        $this->container['interest_rate'] = $interest_rate;

        return $this;
    }

    /**
     * Gets funding_8h
     *
     * @return float|null
     */
    public function getFunding8h()
    {
        return $this->container['funding_8h'];
    }

    /**
     * Sets funding_8h
     *
     * @param float|null $funding_8h Funding 8h (perpetual only)
     *
     * @return $this
     */
    public function setFunding8h($funding_8h)
    {
        $this->container['funding_8h'] = $funding_8h;

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
     * @param float $mark_price The current instrument market price
     *
     * @return $this
     */
    public function setMarkPrice($mark_price)
    {
        $this->container['mark_price'] = $mark_price;

        return $this;
    }

    /**
     * Gets ask_price
     *
     * @return float
     */
    public function getAskPrice()
    {
        return $this->container['ask_price'];
    }

    /**
     * Sets ask_price
     *
     * @param float $ask_price The current best ask price, `null` if there aren't any asks
     *
     * @return $this
     */
    public function setAskPrice($ask_price)
    {
        $this->container['ask_price'] = $ask_price;

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
     * Gets low
     *
     * @return float
     */
    public function getLow()
    {
        return $this->container['low'];
    }

    /**
     * Sets low
     *
     * @param float $low Price of the 24h lowest trade, `null` if there weren't any trades
     *
     * @return $this
     */
    public function setLow($low)
    {
        $this->container['low'] = $low;

        return $this;
    }

    /**
     * Gets base_currency
     *
     * @return string|null
     */
    public function getBaseCurrency()
    {
        return $this->container['base_currency'];
    }

    /**
     * Sets base_currency
     *
     * @param string|null $base_currency Base currency
     *
     * @return $this
     */
    public function setBaseCurrency($base_currency)
    {
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
     * Gets current_funding
     *
     * @return float|null
     */
    public function getCurrentFunding()
    {
        return $this->container['current_funding'];
    }

    /**
     * Sets current_funding
     *
     * @param float|null $current_funding Current funding (perpetual only)
     *
     * @return $this
     */
    public function setCurrentFunding($current_funding)
    {
        $this->container['current_funding'] = $current_funding;

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


