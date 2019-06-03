<?php
/**
 * TradingApi
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

namespace OpenAPI\Client\Api;

use GuzzleHttp\Client;
use GuzzleHttp\ClientInterface;
use GuzzleHttp\Exception\RequestException;
use GuzzleHttp\Psr7\MultipartStream;
use GuzzleHttp\Psr7\Request;
use GuzzleHttp\RequestOptions;
use OpenAPI\Client\ApiException;
use OpenAPI\Client\Configuration;
use OpenAPI\Client\HeaderSelector;
use OpenAPI\Client\ObjectSerializer;

/**
 * TradingApi Class Doc Comment
 *
 * @category Class
 * @package  OpenAPI\Client
 * @author   OpenAPI Generator team
 * @link     https://openapi-generator.tech
 */
class TradingApi
{
    /**
     * @var ClientInterface
     */
    protected $client;

    /**
     * @var Configuration
     */
    protected $config;

    /**
     * @var HeaderSelector
     */
    protected $headerSelector;

    /**
     * @var int Host index
     */
    protected $hostIndex;

    /**
     * @param ClientInterface $client
     * @param Configuration   $config
     * @param HeaderSelector  $selector
     * @param int             $host_index (Optional) host index to select the list of hosts if defined in the OpenAPI spec
     */
    public function __construct(
        ClientInterface $client = null,
        Configuration $config = null,
        HeaderSelector $selector = null,
        $host_index = 0
    ) {
        $this->client = $client ?: new Client();
        $this->config = $config ?: new Configuration();
        $this->headerSelector = $selector ?: new HeaderSelector();
        $this->hostIndex = $host_index;
    }

    /**
     * Set the host index
     *
     * @param  int Host index (required)
     */
    public function setHostIndex($host_index)
    {
        $this->hostIndex = $host_index;
    }

    /**
     * Get the host index
     *
     * @return Host index
     */
    public function getHostIndex()
    {
        return $this->hostIndex;
    }

    /**
     * @return Configuration
     */
    public function getConfig()
    {
        return $this->config;
    }

    /**
     * Operation privateBuyGet
     *
     * Places a buy order for an instrument.
     *
     * @param  string $instrument_name Instrument name (required)
     * @param  float $amount It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH (required)
     * @param  string $type The order type, default: &#x60;\&quot;limit\&quot;&#x60; (optional)
     * @param  string $label user defined label for the order (maximum 32 characters) (optional)
     * @param  float $price &lt;p&gt;The order price in base currency (Only for limit and stop_limit orders)&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;usd, the field price should be the option price value in USD.&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;implv, the field price should be a value of implied volatility in percentages. For example,  price&#x3D;100, means implied volatility of 100%&lt;/p&gt; (optional)
     * @param  string $time_in_force &lt;p&gt;Specifies how long the order remains in effect. Default &#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; &lt;ul&gt; &lt;li&gt;&#x60;\&quot;good_til_cancelled\&quot;&#x60; - unfilled order remains in order book until cancelled&lt;/li&gt; &lt;li&gt;&#x60;\&quot;fill_or_kill\&quot;&#x60; - execute a transaction immediately and completely or not at all&lt;/li&gt; &lt;li&gt;&#x60;\&quot;immediate_or_cancel\&quot;&#x60; - execute a transaction immediately, and any portion of the order that cannot be immediately filled is cancelled&lt;/li&gt; &lt;/ul&gt; (optional, default to 'good_til_cancelled')
     * @param  float $max_show Maximum amount within an order to be shown to other customers, &#x60;0&#x60; for invisible order (optional, default to 1)
     * @param  bool $post_only &lt;p&gt;If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.&lt;/p&gt; &lt;p&gt;Only valid in combination with time_in_force&#x3D;&#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; (optional, default to true)
     * @param  bool $reduce_only If &#x60;true&#x60;, the order is considered reduce-only which is intended to only reduce a current position (optional, default to false)
     * @param  float $stop_price Stop price, required for stop limit orders (Only for stop orders) (optional)
     * @param  string $trigger Defines trigger type, required for &#x60;\&quot;stop_limit\&quot;&#x60; order type (optional)
     * @param  string $advanced Advanced option order type. (Only for options) (optional)
     *
     * @throws \OpenAPI\Client\ApiException on non-2xx response
     * @throws \InvalidArgumentException
     * @return object
     */
    public function privateBuyGet($instrument_name, $amount, $type = null, $label = null, $price = null, $time_in_force = 'good_til_cancelled', $max_show = 1, $post_only = true, $reduce_only = false, $stop_price = null, $trigger = null, $advanced = null)
    {
        list($response) = $this->privateBuyGetWithHttpInfo($instrument_name, $amount, $type, $label, $price, $time_in_force, $max_show, $post_only, $reduce_only, $stop_price, $trigger, $advanced);
        return $response;
    }

    /**
     * Operation privateBuyGetWithHttpInfo
     *
     * Places a buy order for an instrument.
     *
     * @param  string $instrument_name Instrument name (required)
     * @param  float $amount It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH (required)
     * @param  string $type The order type, default: &#x60;\&quot;limit\&quot;&#x60; (optional)
     * @param  string $label user defined label for the order (maximum 32 characters) (optional)
     * @param  float $price &lt;p&gt;The order price in base currency (Only for limit and stop_limit orders)&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;usd, the field price should be the option price value in USD.&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;implv, the field price should be a value of implied volatility in percentages. For example,  price&#x3D;100, means implied volatility of 100%&lt;/p&gt; (optional)
     * @param  string $time_in_force &lt;p&gt;Specifies how long the order remains in effect. Default &#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; &lt;ul&gt; &lt;li&gt;&#x60;\&quot;good_til_cancelled\&quot;&#x60; - unfilled order remains in order book until cancelled&lt;/li&gt; &lt;li&gt;&#x60;\&quot;fill_or_kill\&quot;&#x60; - execute a transaction immediately and completely or not at all&lt;/li&gt; &lt;li&gt;&#x60;\&quot;immediate_or_cancel\&quot;&#x60; - execute a transaction immediately, and any portion of the order that cannot be immediately filled is cancelled&lt;/li&gt; &lt;/ul&gt; (optional, default to 'good_til_cancelled')
     * @param  float $max_show Maximum amount within an order to be shown to other customers, &#x60;0&#x60; for invisible order (optional, default to 1)
     * @param  bool $post_only &lt;p&gt;If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.&lt;/p&gt; &lt;p&gt;Only valid in combination with time_in_force&#x3D;&#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; (optional, default to true)
     * @param  bool $reduce_only If &#x60;true&#x60;, the order is considered reduce-only which is intended to only reduce a current position (optional, default to false)
     * @param  float $stop_price Stop price, required for stop limit orders (Only for stop orders) (optional)
     * @param  string $trigger Defines trigger type, required for &#x60;\&quot;stop_limit\&quot;&#x60; order type (optional)
     * @param  string $advanced Advanced option order type. (Only for options) (optional)
     *
     * @throws \OpenAPI\Client\ApiException on non-2xx response
     * @throws \InvalidArgumentException
     * @return array of object, HTTP status code, HTTP response headers (array of strings)
     */
    public function privateBuyGetWithHttpInfo($instrument_name, $amount, $type = null, $label = null, $price = null, $time_in_force = 'good_til_cancelled', $max_show = 1, $post_only = true, $reduce_only = false, $stop_price = null, $trigger = null, $advanced = null)
    {
        $request = $this->privateBuyGetRequest($instrument_name, $amount, $type, $label, $price, $time_in_force, $max_show, $post_only, $reduce_only, $stop_price, $trigger, $advanced);

        try {
            $options = $this->createHttpClientOption();
            try {
                $response = $this->client->send($request, $options);
            } catch (RequestException $e) {
                throw new ApiException(
                    "[{$e->getCode()}] {$e->getMessage()}",
                    $e->getCode(),
                    $e->getResponse() ? $e->getResponse()->getHeaders() : null,
                    $e->getResponse() ? $e->getResponse()->getBody()->getContents() : null
                );
            }

            $statusCode = $response->getStatusCode();

            if ($statusCode < 200 || $statusCode > 299) {
                throw new ApiException(
                    sprintf(
                        '[%d] Error connecting to the API (%s)',
                        $statusCode,
                        $request->getUri()
                    ),
                    $statusCode,
                    $response->getHeaders(),
                    $response->getBody()
                );
            }

            $responseBody = $response->getBody();
            switch($statusCode) {
                case 200:
                    if ('object' === '\SplFileObject') {
                        $content = $responseBody; //stream goes to serializer
                    } else {
                        $content = $responseBody->getContents();
                    }

                    return [
                        ObjectSerializer::deserialize($content, 'object', []),
                        $response->getStatusCode(),
                        $response->getHeaders()
                    ];
            }

            $returnType = 'object';
            $responseBody = $response->getBody();
            if ($returnType === '\SplFileObject') {
                $content = $responseBody; //stream goes to serializer
            } else {
                $content = $responseBody->getContents();
            }

            return [
                ObjectSerializer::deserialize($content, $returnType, []),
                $response->getStatusCode(),
                $response->getHeaders()
            ];

        } catch (ApiException $e) {
            switch ($e->getCode()) {
                case 200:
                    $data = ObjectSerializer::deserialize(
                        $e->getResponseBody(),
                        'object',
                        $e->getResponseHeaders()
                    );
                    $e->setResponseObject($data);
                    break;
            }
            throw $e;
        }
    }

    /**
     * Operation privateBuyGetAsync
     *
     * Places a buy order for an instrument.
     *
     * @param  string $instrument_name Instrument name (required)
     * @param  float $amount It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH (required)
     * @param  string $type The order type, default: &#x60;\&quot;limit\&quot;&#x60; (optional)
     * @param  string $label user defined label for the order (maximum 32 characters) (optional)
     * @param  float $price &lt;p&gt;The order price in base currency (Only for limit and stop_limit orders)&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;usd, the field price should be the option price value in USD.&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;implv, the field price should be a value of implied volatility in percentages. For example,  price&#x3D;100, means implied volatility of 100%&lt;/p&gt; (optional)
     * @param  string $time_in_force &lt;p&gt;Specifies how long the order remains in effect. Default &#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; &lt;ul&gt; &lt;li&gt;&#x60;\&quot;good_til_cancelled\&quot;&#x60; - unfilled order remains in order book until cancelled&lt;/li&gt; &lt;li&gt;&#x60;\&quot;fill_or_kill\&quot;&#x60; - execute a transaction immediately and completely or not at all&lt;/li&gt; &lt;li&gt;&#x60;\&quot;immediate_or_cancel\&quot;&#x60; - execute a transaction immediately, and any portion of the order that cannot be immediately filled is cancelled&lt;/li&gt; &lt;/ul&gt; (optional, default to 'good_til_cancelled')
     * @param  float $max_show Maximum amount within an order to be shown to other customers, &#x60;0&#x60; for invisible order (optional, default to 1)
     * @param  bool $post_only &lt;p&gt;If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.&lt;/p&gt; &lt;p&gt;Only valid in combination with time_in_force&#x3D;&#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; (optional, default to true)
     * @param  bool $reduce_only If &#x60;true&#x60;, the order is considered reduce-only which is intended to only reduce a current position (optional, default to false)
     * @param  float $stop_price Stop price, required for stop limit orders (Only for stop orders) (optional)
     * @param  string $trigger Defines trigger type, required for &#x60;\&quot;stop_limit\&quot;&#x60; order type (optional)
     * @param  string $advanced Advanced option order type. (Only for options) (optional)
     *
     * @throws \InvalidArgumentException
     * @return \GuzzleHttp\Promise\PromiseInterface
     */
    public function privateBuyGetAsync($instrument_name, $amount, $type = null, $label = null, $price = null, $time_in_force = 'good_til_cancelled', $max_show = 1, $post_only = true, $reduce_only = false, $stop_price = null, $trigger = null, $advanced = null)
    {
        return $this->privateBuyGetAsyncWithHttpInfo($instrument_name, $amount, $type, $label, $price, $time_in_force, $max_show, $post_only, $reduce_only, $stop_price, $trigger, $advanced)
            ->then(
                function ($response) {
                    return $response[0];
                }
            );
    }

    /**
     * Operation privateBuyGetAsyncWithHttpInfo
     *
     * Places a buy order for an instrument.
     *
     * @param  string $instrument_name Instrument name (required)
     * @param  float $amount It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH (required)
     * @param  string $type The order type, default: &#x60;\&quot;limit\&quot;&#x60; (optional)
     * @param  string $label user defined label for the order (maximum 32 characters) (optional)
     * @param  float $price &lt;p&gt;The order price in base currency (Only for limit and stop_limit orders)&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;usd, the field price should be the option price value in USD.&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;implv, the field price should be a value of implied volatility in percentages. For example,  price&#x3D;100, means implied volatility of 100%&lt;/p&gt; (optional)
     * @param  string $time_in_force &lt;p&gt;Specifies how long the order remains in effect. Default &#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; &lt;ul&gt; &lt;li&gt;&#x60;\&quot;good_til_cancelled\&quot;&#x60; - unfilled order remains in order book until cancelled&lt;/li&gt; &lt;li&gt;&#x60;\&quot;fill_or_kill\&quot;&#x60; - execute a transaction immediately and completely or not at all&lt;/li&gt; &lt;li&gt;&#x60;\&quot;immediate_or_cancel\&quot;&#x60; - execute a transaction immediately, and any portion of the order that cannot be immediately filled is cancelled&lt;/li&gt; &lt;/ul&gt; (optional, default to 'good_til_cancelled')
     * @param  float $max_show Maximum amount within an order to be shown to other customers, &#x60;0&#x60; for invisible order (optional, default to 1)
     * @param  bool $post_only &lt;p&gt;If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.&lt;/p&gt; &lt;p&gt;Only valid in combination with time_in_force&#x3D;&#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; (optional, default to true)
     * @param  bool $reduce_only If &#x60;true&#x60;, the order is considered reduce-only which is intended to only reduce a current position (optional, default to false)
     * @param  float $stop_price Stop price, required for stop limit orders (Only for stop orders) (optional)
     * @param  string $trigger Defines trigger type, required for &#x60;\&quot;stop_limit\&quot;&#x60; order type (optional)
     * @param  string $advanced Advanced option order type. (Only for options) (optional)
     *
     * @throws \InvalidArgumentException
     * @return \GuzzleHttp\Promise\PromiseInterface
     */
    public function privateBuyGetAsyncWithHttpInfo($instrument_name, $amount, $type = null, $label = null, $price = null, $time_in_force = 'good_til_cancelled', $max_show = 1, $post_only = true, $reduce_only = false, $stop_price = null, $trigger = null, $advanced = null)
    {
        $returnType = 'object';
        $request = $this->privateBuyGetRequest($instrument_name, $amount, $type, $label, $price, $time_in_force, $max_show, $post_only, $reduce_only, $stop_price, $trigger, $advanced);

        return $this->client
            ->sendAsync($request, $this->createHttpClientOption())
            ->then(
                function ($response) use ($returnType) {
                    $responseBody = $response->getBody();
                    if ($returnType === '\SplFileObject') {
                        $content = $responseBody; //stream goes to serializer
                    } else {
                        $content = $responseBody->getContents();
                    }

                    return [
                        ObjectSerializer::deserialize($content, $returnType, []),
                        $response->getStatusCode(),
                        $response->getHeaders()
                    ];
                },
                function ($exception) {
                    $response = $exception->getResponse();
                    $statusCode = $response->getStatusCode();
                    throw new ApiException(
                        sprintf(
                            '[%d] Error connecting to the API (%s)',
                            $statusCode,
                            $exception->getRequest()->getUri()
                        ),
                        $statusCode,
                        $response->getHeaders(),
                        $response->getBody()
                    );
                }
            );
    }

    /**
     * Create request for operation 'privateBuyGet'
     *
     * @param  string $instrument_name Instrument name (required)
     * @param  float $amount It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH (required)
     * @param  string $type The order type, default: &#x60;\&quot;limit\&quot;&#x60; (optional)
     * @param  string $label user defined label for the order (maximum 32 characters) (optional)
     * @param  float $price &lt;p&gt;The order price in base currency (Only for limit and stop_limit orders)&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;usd, the field price should be the option price value in USD.&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;implv, the field price should be a value of implied volatility in percentages. For example,  price&#x3D;100, means implied volatility of 100%&lt;/p&gt; (optional)
     * @param  string $time_in_force &lt;p&gt;Specifies how long the order remains in effect. Default &#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; &lt;ul&gt; &lt;li&gt;&#x60;\&quot;good_til_cancelled\&quot;&#x60; - unfilled order remains in order book until cancelled&lt;/li&gt; &lt;li&gt;&#x60;\&quot;fill_or_kill\&quot;&#x60; - execute a transaction immediately and completely or not at all&lt;/li&gt; &lt;li&gt;&#x60;\&quot;immediate_or_cancel\&quot;&#x60; - execute a transaction immediately, and any portion of the order that cannot be immediately filled is cancelled&lt;/li&gt; &lt;/ul&gt; (optional, default to 'good_til_cancelled')
     * @param  float $max_show Maximum amount within an order to be shown to other customers, &#x60;0&#x60; for invisible order (optional, default to 1)
     * @param  bool $post_only &lt;p&gt;If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.&lt;/p&gt; &lt;p&gt;Only valid in combination with time_in_force&#x3D;&#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; (optional, default to true)
     * @param  bool $reduce_only If &#x60;true&#x60;, the order is considered reduce-only which is intended to only reduce a current position (optional, default to false)
     * @param  float $stop_price Stop price, required for stop limit orders (Only for stop orders) (optional)
     * @param  string $trigger Defines trigger type, required for &#x60;\&quot;stop_limit\&quot;&#x60; order type (optional)
     * @param  string $advanced Advanced option order type. (Only for options) (optional)
     *
     * @throws \InvalidArgumentException
     * @return \GuzzleHttp\Psr7\Request
     */
    protected function privateBuyGetRequest($instrument_name, $amount, $type = null, $label = null, $price = null, $time_in_force = 'good_til_cancelled', $max_show = 1, $post_only = true, $reduce_only = false, $stop_price = null, $trigger = null, $advanced = null)
    {
        // verify the required parameter 'instrument_name' is set
        if ($instrument_name === null || (is_array($instrument_name) && count($instrument_name) === 0)) {
            throw new \InvalidArgumentException(
                'Missing the required parameter $instrument_name when calling privateBuyGet'
            );
        }
        // verify the required parameter 'amount' is set
        if ($amount === null || (is_array($amount) && count($amount) === 0)) {
            throw new \InvalidArgumentException(
                'Missing the required parameter $amount when calling privateBuyGet'
            );
        }

        $resourcePath = '/private/buy';
        $formParams = [];
        $queryParams = [];
        $headerParams = [];
        $httpBody = '';
        $multipart = false;

        // query params
        if ($instrument_name !== null) {
            $queryParams['instrument_name'] = ObjectSerializer::toQueryValue($instrument_name);
        }
        // query params
        if ($amount !== null) {
            $queryParams['amount'] = ObjectSerializer::toQueryValue($amount);
        }
        // query params
        if ($type !== null) {
            $queryParams['type'] = ObjectSerializer::toQueryValue($type);
        }
        // query params
        if ($label !== null) {
            $queryParams['label'] = ObjectSerializer::toQueryValue($label);
        }
        // query params
        if ($price !== null) {
            $queryParams['price'] = ObjectSerializer::toQueryValue($price);
        }
        // query params
        if ($time_in_force !== null) {
            $queryParams['time_in_force'] = ObjectSerializer::toQueryValue($time_in_force);
        }
        // query params
        if ($max_show !== null) {
            $queryParams['max_show'] = ObjectSerializer::toQueryValue($max_show);
        }
        // query params
        if ($post_only !== null) {
            $queryParams['post_only'] = ObjectSerializer::toQueryValue($post_only);
        }
        // query params
        if ($reduce_only !== null) {
            $queryParams['reduce_only'] = ObjectSerializer::toQueryValue($reduce_only);
        }
        // query params
        if ($stop_price !== null) {
            $queryParams['stop_price'] = ObjectSerializer::toQueryValue($stop_price);
        }
        // query params
        if ($trigger !== null) {
            $queryParams['trigger'] = ObjectSerializer::toQueryValue($trigger);
        }
        // query params
        if ($advanced !== null) {
            $queryParams['advanced'] = ObjectSerializer::toQueryValue($advanced);
        }


        // body params
        $_tempBody = null;

        if ($multipart) {
            $headers = $this->headerSelector->selectHeadersForMultipart(
                ['application/json']
            );
        } else {
            $headers = $this->headerSelector->selectHeaders(
                ['application/json'],
                []
            );
        }

        // for model (json/xml)
        if (isset($_tempBody)) {
            // $_tempBody is the method argument, if present
            if ($headers['Content-Type'] === 'application/json') {
                $httpBody = \GuzzleHttp\json_encode(ObjectSerializer::sanitizeForSerialization($_tempBody));
            } else {
                $httpBody = $_tempBody;
            }
        } elseif (count($formParams) > 0) {
            if ($multipart) {
                $multipartContents = [];
                foreach ($formParams as $formParamName => $formParamValue) {
                    $multipartContents[] = [
                        'name' => $formParamName,
                        'contents' => $formParamValue
                    ];
                }
                // for HTTP post (form)
                $httpBody = new MultipartStream($multipartContents);

            } elseif ($headers['Content-Type'] === 'application/json') {
                $httpBody = \GuzzleHttp\json_encode($formParams);

            } else {
                // for HTTP post (form)
                $httpBody = \GuzzleHttp\Psr7\build_query($formParams);
            }
        }

        // this endpoint requires Bearer (Auth. Token) authentication (access token)
        if ($this->config->getAccessToken() !== null) {
            $headers['Authorization'] = 'Bearer ' . $this->config->getAccessToken();
        }

        $defaultHeaders = [];
        if ($this->config->getUserAgent()) {
            $defaultHeaders['User-Agent'] = $this->config->getUserAgent();
        }

        $headers = array_merge(
            $defaultHeaders,
            $headerParams,
            $headers
        );

        $query = \GuzzleHttp\Psr7\build_query($queryParams);
        return new Request(
            'GET',
            $this->config->getHost() . $resourcePath . ($query ? "?{$query}" : ''),
            $headers,
            $httpBody
        );
    }

    /**
     * Operation privateCancelAllByCurrencyGet
     *
     * Cancels all orders by currency, optionally filtered by instrument kind and/or order type.
     *
     * @param  string $currency The currency symbol (required)
     * @param  string $kind Instrument kind, if not provided instruments of all kinds are considered (optional)
     * @param  string $type Order type - limit, stop or all, default - &#x60;all&#x60; (optional)
     *
     * @throws \OpenAPI\Client\ApiException on non-2xx response
     * @throws \InvalidArgumentException
     * @return object
     */
    public function privateCancelAllByCurrencyGet($currency, $kind = null, $type = null)
    {
        list($response) = $this->privateCancelAllByCurrencyGetWithHttpInfo($currency, $kind, $type);
        return $response;
    }

    /**
     * Operation privateCancelAllByCurrencyGetWithHttpInfo
     *
     * Cancels all orders by currency, optionally filtered by instrument kind and/or order type.
     *
     * @param  string $currency The currency symbol (required)
     * @param  string $kind Instrument kind, if not provided instruments of all kinds are considered (optional)
     * @param  string $type Order type - limit, stop or all, default - &#x60;all&#x60; (optional)
     *
     * @throws \OpenAPI\Client\ApiException on non-2xx response
     * @throws \InvalidArgumentException
     * @return array of object, HTTP status code, HTTP response headers (array of strings)
     */
    public function privateCancelAllByCurrencyGetWithHttpInfo($currency, $kind = null, $type = null)
    {
        $request = $this->privateCancelAllByCurrencyGetRequest($currency, $kind, $type);

        try {
            $options = $this->createHttpClientOption();
            try {
                $response = $this->client->send($request, $options);
            } catch (RequestException $e) {
                throw new ApiException(
                    "[{$e->getCode()}] {$e->getMessage()}",
                    $e->getCode(),
                    $e->getResponse() ? $e->getResponse()->getHeaders() : null,
                    $e->getResponse() ? $e->getResponse()->getBody()->getContents() : null
                );
            }

            $statusCode = $response->getStatusCode();

            if ($statusCode < 200 || $statusCode > 299) {
                throw new ApiException(
                    sprintf(
                        '[%d] Error connecting to the API (%s)',
                        $statusCode,
                        $request->getUri()
                    ),
                    $statusCode,
                    $response->getHeaders(),
                    $response->getBody()
                );
            }

            $responseBody = $response->getBody();
            switch($statusCode) {
                case 200:
                    if ('object' === '\SplFileObject') {
                        $content = $responseBody; //stream goes to serializer
                    } else {
                        $content = $responseBody->getContents();
                    }

                    return [
                        ObjectSerializer::deserialize($content, 'object', []),
                        $response->getStatusCode(),
                        $response->getHeaders()
                    ];
            }

            $returnType = 'object';
            $responseBody = $response->getBody();
            if ($returnType === '\SplFileObject') {
                $content = $responseBody; //stream goes to serializer
            } else {
                $content = $responseBody->getContents();
            }

            return [
                ObjectSerializer::deserialize($content, $returnType, []),
                $response->getStatusCode(),
                $response->getHeaders()
            ];

        } catch (ApiException $e) {
            switch ($e->getCode()) {
                case 200:
                    $data = ObjectSerializer::deserialize(
                        $e->getResponseBody(),
                        'object',
                        $e->getResponseHeaders()
                    );
                    $e->setResponseObject($data);
                    break;
            }
            throw $e;
        }
    }

    /**
     * Operation privateCancelAllByCurrencyGetAsync
     *
     * Cancels all orders by currency, optionally filtered by instrument kind and/or order type.
     *
     * @param  string $currency The currency symbol (required)
     * @param  string $kind Instrument kind, if not provided instruments of all kinds are considered (optional)
     * @param  string $type Order type - limit, stop or all, default - &#x60;all&#x60; (optional)
     *
     * @throws \InvalidArgumentException
     * @return \GuzzleHttp\Promise\PromiseInterface
     */
    public function privateCancelAllByCurrencyGetAsync($currency, $kind = null, $type = null)
    {
        return $this->privateCancelAllByCurrencyGetAsyncWithHttpInfo($currency, $kind, $type)
            ->then(
                function ($response) {
                    return $response[0];
                }
            );
    }

    /**
     * Operation privateCancelAllByCurrencyGetAsyncWithHttpInfo
     *
     * Cancels all orders by currency, optionally filtered by instrument kind and/or order type.
     *
     * @param  string $currency The currency symbol (required)
     * @param  string $kind Instrument kind, if not provided instruments of all kinds are considered (optional)
     * @param  string $type Order type - limit, stop or all, default - &#x60;all&#x60; (optional)
     *
     * @throws \InvalidArgumentException
     * @return \GuzzleHttp\Promise\PromiseInterface
     */
    public function privateCancelAllByCurrencyGetAsyncWithHttpInfo($currency, $kind = null, $type = null)
    {
        $returnType = 'object';
        $request = $this->privateCancelAllByCurrencyGetRequest($currency, $kind, $type);

        return $this->client
            ->sendAsync($request, $this->createHttpClientOption())
            ->then(
                function ($response) use ($returnType) {
                    $responseBody = $response->getBody();
                    if ($returnType === '\SplFileObject') {
                        $content = $responseBody; //stream goes to serializer
                    } else {
                        $content = $responseBody->getContents();
                    }

                    return [
                        ObjectSerializer::deserialize($content, $returnType, []),
                        $response->getStatusCode(),
                        $response->getHeaders()
                    ];
                },
                function ($exception) {
                    $response = $exception->getResponse();
                    $statusCode = $response->getStatusCode();
                    throw new ApiException(
                        sprintf(
                            '[%d] Error connecting to the API (%s)',
                            $statusCode,
                            $exception->getRequest()->getUri()
                        ),
                        $statusCode,
                        $response->getHeaders(),
                        $response->getBody()
                    );
                }
            );
    }

    /**
     * Create request for operation 'privateCancelAllByCurrencyGet'
     *
     * @param  string $currency The currency symbol (required)
     * @param  string $kind Instrument kind, if not provided instruments of all kinds are considered (optional)
     * @param  string $type Order type - limit, stop or all, default - &#x60;all&#x60; (optional)
     *
     * @throws \InvalidArgumentException
     * @return \GuzzleHttp\Psr7\Request
     */
    protected function privateCancelAllByCurrencyGetRequest($currency, $kind = null, $type = null)
    {
        // verify the required parameter 'currency' is set
        if ($currency === null || (is_array($currency) && count($currency) === 0)) {
            throw new \InvalidArgumentException(
                'Missing the required parameter $currency when calling privateCancelAllByCurrencyGet'
            );
        }

        $resourcePath = '/private/cancel_all_by_currency';
        $formParams = [];
        $queryParams = [];
        $headerParams = [];
        $httpBody = '';
        $multipart = false;

        // query params
        if ($currency !== null) {
            $queryParams['currency'] = ObjectSerializer::toQueryValue($currency);
        }
        // query params
        if ($kind !== null) {
            $queryParams['kind'] = ObjectSerializer::toQueryValue($kind);
        }
        // query params
        if ($type !== null) {
            $queryParams['type'] = ObjectSerializer::toQueryValue($type);
        }


        // body params
        $_tempBody = null;

        if ($multipart) {
            $headers = $this->headerSelector->selectHeadersForMultipart(
                ['application/json']
            );
        } else {
            $headers = $this->headerSelector->selectHeaders(
                ['application/json'],
                []
            );
        }

        // for model (json/xml)
        if (isset($_tempBody)) {
            // $_tempBody is the method argument, if present
            if ($headers['Content-Type'] === 'application/json') {
                $httpBody = \GuzzleHttp\json_encode(ObjectSerializer::sanitizeForSerialization($_tempBody));
            } else {
                $httpBody = $_tempBody;
            }
        } elseif (count($formParams) > 0) {
            if ($multipart) {
                $multipartContents = [];
                foreach ($formParams as $formParamName => $formParamValue) {
                    $multipartContents[] = [
                        'name' => $formParamName,
                        'contents' => $formParamValue
                    ];
                }
                // for HTTP post (form)
                $httpBody = new MultipartStream($multipartContents);

            } elseif ($headers['Content-Type'] === 'application/json') {
                $httpBody = \GuzzleHttp\json_encode($formParams);

            } else {
                // for HTTP post (form)
                $httpBody = \GuzzleHttp\Psr7\build_query($formParams);
            }
        }

        // this endpoint requires Bearer (Auth. Token) authentication (access token)
        if ($this->config->getAccessToken() !== null) {
            $headers['Authorization'] = 'Bearer ' . $this->config->getAccessToken();
        }

        $defaultHeaders = [];
        if ($this->config->getUserAgent()) {
            $defaultHeaders['User-Agent'] = $this->config->getUserAgent();
        }

        $headers = array_merge(
            $defaultHeaders,
            $headerParams,
            $headers
        );

        $query = \GuzzleHttp\Psr7\build_query($queryParams);
        return new Request(
            'GET',
            $this->config->getHost() . $resourcePath . ($query ? "?{$query}" : ''),
            $headers,
            $httpBody
        );
    }

    /**
     * Operation privateCancelAllByInstrumentGet
     *
     * Cancels all orders by instrument, optionally filtered by order type.
     *
     * @param  string $instrument_name Instrument name (required)
     * @param  string $type Order type - limit, stop or all, default - &#x60;all&#x60; (optional)
     *
     * @throws \OpenAPI\Client\ApiException on non-2xx response
     * @throws \InvalidArgumentException
     * @return object
     */
    public function privateCancelAllByInstrumentGet($instrument_name, $type = null)
    {
        list($response) = $this->privateCancelAllByInstrumentGetWithHttpInfo($instrument_name, $type);
        return $response;
    }

    /**
     * Operation privateCancelAllByInstrumentGetWithHttpInfo
     *
     * Cancels all orders by instrument, optionally filtered by order type.
     *
     * @param  string $instrument_name Instrument name (required)
     * @param  string $type Order type - limit, stop or all, default - &#x60;all&#x60; (optional)
     *
     * @throws \OpenAPI\Client\ApiException on non-2xx response
     * @throws \InvalidArgumentException
     * @return array of object, HTTP status code, HTTP response headers (array of strings)
     */
    public function privateCancelAllByInstrumentGetWithHttpInfo($instrument_name, $type = null)
    {
        $request = $this->privateCancelAllByInstrumentGetRequest($instrument_name, $type);

        try {
            $options = $this->createHttpClientOption();
            try {
                $response = $this->client->send($request, $options);
            } catch (RequestException $e) {
                throw new ApiException(
                    "[{$e->getCode()}] {$e->getMessage()}",
                    $e->getCode(),
                    $e->getResponse() ? $e->getResponse()->getHeaders() : null,
                    $e->getResponse() ? $e->getResponse()->getBody()->getContents() : null
                );
            }

            $statusCode = $response->getStatusCode();

            if ($statusCode < 200 || $statusCode > 299) {
                throw new ApiException(
                    sprintf(
                        '[%d] Error connecting to the API (%s)',
                        $statusCode,
                        $request->getUri()
                    ),
                    $statusCode,
                    $response->getHeaders(),
                    $response->getBody()
                );
            }

            $responseBody = $response->getBody();
            switch($statusCode) {
                case 200:
                    if ('object' === '\SplFileObject') {
                        $content = $responseBody; //stream goes to serializer
                    } else {
                        $content = $responseBody->getContents();
                    }

                    return [
                        ObjectSerializer::deserialize($content, 'object', []),
                        $response->getStatusCode(),
                        $response->getHeaders()
                    ];
            }

            $returnType = 'object';
            $responseBody = $response->getBody();
            if ($returnType === '\SplFileObject') {
                $content = $responseBody; //stream goes to serializer
            } else {
                $content = $responseBody->getContents();
            }

            return [
                ObjectSerializer::deserialize($content, $returnType, []),
                $response->getStatusCode(),
                $response->getHeaders()
            ];

        } catch (ApiException $e) {
            switch ($e->getCode()) {
                case 200:
                    $data = ObjectSerializer::deserialize(
                        $e->getResponseBody(),
                        'object',
                        $e->getResponseHeaders()
                    );
                    $e->setResponseObject($data);
                    break;
            }
            throw $e;
        }
    }

    /**
     * Operation privateCancelAllByInstrumentGetAsync
     *
     * Cancels all orders by instrument, optionally filtered by order type.
     *
     * @param  string $instrument_name Instrument name (required)
     * @param  string $type Order type - limit, stop or all, default - &#x60;all&#x60; (optional)
     *
     * @throws \InvalidArgumentException
     * @return \GuzzleHttp\Promise\PromiseInterface
     */
    public function privateCancelAllByInstrumentGetAsync($instrument_name, $type = null)
    {
        return $this->privateCancelAllByInstrumentGetAsyncWithHttpInfo($instrument_name, $type)
            ->then(
                function ($response) {
                    return $response[0];
                }
            );
    }

    /**
     * Operation privateCancelAllByInstrumentGetAsyncWithHttpInfo
     *
     * Cancels all orders by instrument, optionally filtered by order type.
     *
     * @param  string $instrument_name Instrument name (required)
     * @param  string $type Order type - limit, stop or all, default - &#x60;all&#x60; (optional)
     *
     * @throws \InvalidArgumentException
     * @return \GuzzleHttp\Promise\PromiseInterface
     */
    public function privateCancelAllByInstrumentGetAsyncWithHttpInfo($instrument_name, $type = null)
    {
        $returnType = 'object';
        $request = $this->privateCancelAllByInstrumentGetRequest($instrument_name, $type);

        return $this->client
            ->sendAsync($request, $this->createHttpClientOption())
            ->then(
                function ($response) use ($returnType) {
                    $responseBody = $response->getBody();
                    if ($returnType === '\SplFileObject') {
                        $content = $responseBody; //stream goes to serializer
                    } else {
                        $content = $responseBody->getContents();
                    }

                    return [
                        ObjectSerializer::deserialize($content, $returnType, []),
                        $response->getStatusCode(),
                        $response->getHeaders()
                    ];
                },
                function ($exception) {
                    $response = $exception->getResponse();
                    $statusCode = $response->getStatusCode();
                    throw new ApiException(
                        sprintf(
                            '[%d] Error connecting to the API (%s)',
                            $statusCode,
                            $exception->getRequest()->getUri()
                        ),
                        $statusCode,
                        $response->getHeaders(),
                        $response->getBody()
                    );
                }
            );
    }

    /**
     * Create request for operation 'privateCancelAllByInstrumentGet'
     *
     * @param  string $instrument_name Instrument name (required)
     * @param  string $type Order type - limit, stop or all, default - &#x60;all&#x60; (optional)
     *
     * @throws \InvalidArgumentException
     * @return \GuzzleHttp\Psr7\Request
     */
    protected function privateCancelAllByInstrumentGetRequest($instrument_name, $type = null)
    {
        // verify the required parameter 'instrument_name' is set
        if ($instrument_name === null || (is_array($instrument_name) && count($instrument_name) === 0)) {
            throw new \InvalidArgumentException(
                'Missing the required parameter $instrument_name when calling privateCancelAllByInstrumentGet'
            );
        }

        $resourcePath = '/private/cancel_all_by_instrument';
        $formParams = [];
        $queryParams = [];
        $headerParams = [];
        $httpBody = '';
        $multipart = false;

        // query params
        if ($instrument_name !== null) {
            $queryParams['instrument_name'] = ObjectSerializer::toQueryValue($instrument_name);
        }
        // query params
        if ($type !== null) {
            $queryParams['type'] = ObjectSerializer::toQueryValue($type);
        }


        // body params
        $_tempBody = null;

        if ($multipart) {
            $headers = $this->headerSelector->selectHeadersForMultipart(
                ['application/json']
            );
        } else {
            $headers = $this->headerSelector->selectHeaders(
                ['application/json'],
                []
            );
        }

        // for model (json/xml)
        if (isset($_tempBody)) {
            // $_tempBody is the method argument, if present
            if ($headers['Content-Type'] === 'application/json') {
                $httpBody = \GuzzleHttp\json_encode(ObjectSerializer::sanitizeForSerialization($_tempBody));
            } else {
                $httpBody = $_tempBody;
            }
        } elseif (count($formParams) > 0) {
            if ($multipart) {
                $multipartContents = [];
                foreach ($formParams as $formParamName => $formParamValue) {
                    $multipartContents[] = [
                        'name' => $formParamName,
                        'contents' => $formParamValue
                    ];
                }
                // for HTTP post (form)
                $httpBody = new MultipartStream($multipartContents);

            } elseif ($headers['Content-Type'] === 'application/json') {
                $httpBody = \GuzzleHttp\json_encode($formParams);

            } else {
                // for HTTP post (form)
                $httpBody = \GuzzleHttp\Psr7\build_query($formParams);
            }
        }

        // this endpoint requires Bearer (Auth. Token) authentication (access token)
        if ($this->config->getAccessToken() !== null) {
            $headers['Authorization'] = 'Bearer ' . $this->config->getAccessToken();
        }

        $defaultHeaders = [];
        if ($this->config->getUserAgent()) {
            $defaultHeaders['User-Agent'] = $this->config->getUserAgent();
        }

        $headers = array_merge(
            $defaultHeaders,
            $headerParams,
            $headers
        );

        $query = \GuzzleHttp\Psr7\build_query($queryParams);
        return new Request(
            'GET',
            $this->config->getHost() . $resourcePath . ($query ? "?{$query}" : ''),
            $headers,
            $httpBody
        );
    }

    /**
     * Operation privateCancelAllGet
     *
     * This method cancels all users orders and stop orders within all currencies and instrument kinds.
     *
     *
     * @throws \OpenAPI\Client\ApiException on non-2xx response
     * @throws \InvalidArgumentException
     * @return object
     */
    public function privateCancelAllGet()
    {
        list($response) = $this->privateCancelAllGetWithHttpInfo();
        return $response;
    }

    /**
     * Operation privateCancelAllGetWithHttpInfo
     *
     * This method cancels all users orders and stop orders within all currencies and instrument kinds.
     *
     *
     * @throws \OpenAPI\Client\ApiException on non-2xx response
     * @throws \InvalidArgumentException
     * @return array of object, HTTP status code, HTTP response headers (array of strings)
     */
    public function privateCancelAllGetWithHttpInfo()
    {
        $request = $this->privateCancelAllGetRequest();

        try {
            $options = $this->createHttpClientOption();
            try {
                $response = $this->client->send($request, $options);
            } catch (RequestException $e) {
                throw new ApiException(
                    "[{$e->getCode()}] {$e->getMessage()}",
                    $e->getCode(),
                    $e->getResponse() ? $e->getResponse()->getHeaders() : null,
                    $e->getResponse() ? $e->getResponse()->getBody()->getContents() : null
                );
            }

            $statusCode = $response->getStatusCode();

            if ($statusCode < 200 || $statusCode > 299) {
                throw new ApiException(
                    sprintf(
                        '[%d] Error connecting to the API (%s)',
                        $statusCode,
                        $request->getUri()
                    ),
                    $statusCode,
                    $response->getHeaders(),
                    $response->getBody()
                );
            }

            $responseBody = $response->getBody();
            switch($statusCode) {
                case 200:
                    if ('object' === '\SplFileObject') {
                        $content = $responseBody; //stream goes to serializer
                    } else {
                        $content = $responseBody->getContents();
                    }

                    return [
                        ObjectSerializer::deserialize($content, 'object', []),
                        $response->getStatusCode(),
                        $response->getHeaders()
                    ];
            }

            $returnType = 'object';
            $responseBody = $response->getBody();
            if ($returnType === '\SplFileObject') {
                $content = $responseBody; //stream goes to serializer
            } else {
                $content = $responseBody->getContents();
            }

            return [
                ObjectSerializer::deserialize($content, $returnType, []),
                $response->getStatusCode(),
                $response->getHeaders()
            ];

        } catch (ApiException $e) {
            switch ($e->getCode()) {
                case 200:
                    $data = ObjectSerializer::deserialize(
                        $e->getResponseBody(),
                        'object',
                        $e->getResponseHeaders()
                    );
                    $e->setResponseObject($data);
                    break;
            }
            throw $e;
        }
    }

    /**
     * Operation privateCancelAllGetAsync
     *
     * This method cancels all users orders and stop orders within all currencies and instrument kinds.
     *
     *
     * @throws \InvalidArgumentException
     * @return \GuzzleHttp\Promise\PromiseInterface
     */
    public function privateCancelAllGetAsync()
    {
        return $this->privateCancelAllGetAsyncWithHttpInfo()
            ->then(
                function ($response) {
                    return $response[0];
                }
            );
    }

    /**
     * Operation privateCancelAllGetAsyncWithHttpInfo
     *
     * This method cancels all users orders and stop orders within all currencies and instrument kinds.
     *
     *
     * @throws \InvalidArgumentException
     * @return \GuzzleHttp\Promise\PromiseInterface
     */
    public function privateCancelAllGetAsyncWithHttpInfo()
    {
        $returnType = 'object';
        $request = $this->privateCancelAllGetRequest();

        return $this->client
            ->sendAsync($request, $this->createHttpClientOption())
            ->then(
                function ($response) use ($returnType) {
                    $responseBody = $response->getBody();
                    if ($returnType === '\SplFileObject') {
                        $content = $responseBody; //stream goes to serializer
                    } else {
                        $content = $responseBody->getContents();
                    }

                    return [
                        ObjectSerializer::deserialize($content, $returnType, []),
                        $response->getStatusCode(),
                        $response->getHeaders()
                    ];
                },
                function ($exception) {
                    $response = $exception->getResponse();
                    $statusCode = $response->getStatusCode();
                    throw new ApiException(
                        sprintf(
                            '[%d] Error connecting to the API (%s)',
                            $statusCode,
                            $exception->getRequest()->getUri()
                        ),
                        $statusCode,
                        $response->getHeaders(),
                        $response->getBody()
                    );
                }
            );
    }

    /**
     * Create request for operation 'privateCancelAllGet'
     *
     *
     * @throws \InvalidArgumentException
     * @return \GuzzleHttp\Psr7\Request
     */
    protected function privateCancelAllGetRequest()
    {

        $resourcePath = '/private/cancel_all';
        $formParams = [];
        $queryParams = [];
        $headerParams = [];
        $httpBody = '';
        $multipart = false;



        // body params
        $_tempBody = null;

        if ($multipart) {
            $headers = $this->headerSelector->selectHeadersForMultipart(
                ['application/json']
            );
        } else {
            $headers = $this->headerSelector->selectHeaders(
                ['application/json'],
                []
            );
        }

        // for model (json/xml)
        if (isset($_tempBody)) {
            // $_tempBody is the method argument, if present
            if ($headers['Content-Type'] === 'application/json') {
                $httpBody = \GuzzleHttp\json_encode(ObjectSerializer::sanitizeForSerialization($_tempBody));
            } else {
                $httpBody = $_tempBody;
            }
        } elseif (count($formParams) > 0) {
            if ($multipart) {
                $multipartContents = [];
                foreach ($formParams as $formParamName => $formParamValue) {
                    $multipartContents[] = [
                        'name' => $formParamName,
                        'contents' => $formParamValue
                    ];
                }
                // for HTTP post (form)
                $httpBody = new MultipartStream($multipartContents);

            } elseif ($headers['Content-Type'] === 'application/json') {
                $httpBody = \GuzzleHttp\json_encode($formParams);

            } else {
                // for HTTP post (form)
                $httpBody = \GuzzleHttp\Psr7\build_query($formParams);
            }
        }

        // this endpoint requires Bearer (Auth. Token) authentication (access token)
        if ($this->config->getAccessToken() !== null) {
            $headers['Authorization'] = 'Bearer ' . $this->config->getAccessToken();
        }

        $defaultHeaders = [];
        if ($this->config->getUserAgent()) {
            $defaultHeaders['User-Agent'] = $this->config->getUserAgent();
        }

        $headers = array_merge(
            $defaultHeaders,
            $headerParams,
            $headers
        );

        $query = \GuzzleHttp\Psr7\build_query($queryParams);
        return new Request(
            'GET',
            $this->config->getHost() . $resourcePath . ($query ? "?{$query}" : ''),
            $headers,
            $httpBody
        );
    }

    /**
     * Operation privateCancelGet
     *
     * Cancel an order, specified by order id
     *
     * @param  string $order_id The order id (required)
     *
     * @throws \OpenAPI\Client\ApiException on non-2xx response
     * @throws \InvalidArgumentException
     * @return object
     */
    public function privateCancelGet($order_id)
    {
        list($response) = $this->privateCancelGetWithHttpInfo($order_id);
        return $response;
    }

    /**
     * Operation privateCancelGetWithHttpInfo
     *
     * Cancel an order, specified by order id
     *
     * @param  string $order_id The order id (required)
     *
     * @throws \OpenAPI\Client\ApiException on non-2xx response
     * @throws \InvalidArgumentException
     * @return array of object, HTTP status code, HTTP response headers (array of strings)
     */
    public function privateCancelGetWithHttpInfo($order_id)
    {
        $request = $this->privateCancelGetRequest($order_id);

        try {
            $options = $this->createHttpClientOption();
            try {
                $response = $this->client->send($request, $options);
            } catch (RequestException $e) {
                throw new ApiException(
                    "[{$e->getCode()}] {$e->getMessage()}",
                    $e->getCode(),
                    $e->getResponse() ? $e->getResponse()->getHeaders() : null,
                    $e->getResponse() ? $e->getResponse()->getBody()->getContents() : null
                );
            }

            $statusCode = $response->getStatusCode();

            if ($statusCode < 200 || $statusCode > 299) {
                throw new ApiException(
                    sprintf(
                        '[%d] Error connecting to the API (%s)',
                        $statusCode,
                        $request->getUri()
                    ),
                    $statusCode,
                    $response->getHeaders(),
                    $response->getBody()
                );
            }

            $responseBody = $response->getBody();
            switch($statusCode) {
                case 200:
                    if ('object' === '\SplFileObject') {
                        $content = $responseBody; //stream goes to serializer
                    } else {
                        $content = $responseBody->getContents();
                    }

                    return [
                        ObjectSerializer::deserialize($content, 'object', []),
                        $response->getStatusCode(),
                        $response->getHeaders()
                    ];
            }

            $returnType = 'object';
            $responseBody = $response->getBody();
            if ($returnType === '\SplFileObject') {
                $content = $responseBody; //stream goes to serializer
            } else {
                $content = $responseBody->getContents();
            }

            return [
                ObjectSerializer::deserialize($content, $returnType, []),
                $response->getStatusCode(),
                $response->getHeaders()
            ];

        } catch (ApiException $e) {
            switch ($e->getCode()) {
                case 200:
                    $data = ObjectSerializer::deserialize(
                        $e->getResponseBody(),
                        'object',
                        $e->getResponseHeaders()
                    );
                    $e->setResponseObject($data);
                    break;
            }
            throw $e;
        }
    }

    /**
     * Operation privateCancelGetAsync
     *
     * Cancel an order, specified by order id
     *
     * @param  string $order_id The order id (required)
     *
     * @throws \InvalidArgumentException
     * @return \GuzzleHttp\Promise\PromiseInterface
     */
    public function privateCancelGetAsync($order_id)
    {
        return $this->privateCancelGetAsyncWithHttpInfo($order_id)
            ->then(
                function ($response) {
                    return $response[0];
                }
            );
    }

    /**
     * Operation privateCancelGetAsyncWithHttpInfo
     *
     * Cancel an order, specified by order id
     *
     * @param  string $order_id The order id (required)
     *
     * @throws \InvalidArgumentException
     * @return \GuzzleHttp\Promise\PromiseInterface
     */
    public function privateCancelGetAsyncWithHttpInfo($order_id)
    {
        $returnType = 'object';
        $request = $this->privateCancelGetRequest($order_id);

        return $this->client
            ->sendAsync($request, $this->createHttpClientOption())
            ->then(
                function ($response) use ($returnType) {
                    $responseBody = $response->getBody();
                    if ($returnType === '\SplFileObject') {
                        $content = $responseBody; //stream goes to serializer
                    } else {
                        $content = $responseBody->getContents();
                    }

                    return [
                        ObjectSerializer::deserialize($content, $returnType, []),
                        $response->getStatusCode(),
                        $response->getHeaders()
                    ];
                },
                function ($exception) {
                    $response = $exception->getResponse();
                    $statusCode = $response->getStatusCode();
                    throw new ApiException(
                        sprintf(
                            '[%d] Error connecting to the API (%s)',
                            $statusCode,
                            $exception->getRequest()->getUri()
                        ),
                        $statusCode,
                        $response->getHeaders(),
                        $response->getBody()
                    );
                }
            );
    }

    /**
     * Create request for operation 'privateCancelGet'
     *
     * @param  string $order_id The order id (required)
     *
     * @throws \InvalidArgumentException
     * @return \GuzzleHttp\Psr7\Request
     */
    protected function privateCancelGetRequest($order_id)
    {
        // verify the required parameter 'order_id' is set
        if ($order_id === null || (is_array($order_id) && count($order_id) === 0)) {
            throw new \InvalidArgumentException(
                'Missing the required parameter $order_id when calling privateCancelGet'
            );
        }

        $resourcePath = '/private/cancel';
        $formParams = [];
        $queryParams = [];
        $headerParams = [];
        $httpBody = '';
        $multipart = false;

        // query params
        if ($order_id !== null) {
            $queryParams['order_id'] = ObjectSerializer::toQueryValue($order_id);
        }


        // body params
        $_tempBody = null;

        if ($multipart) {
            $headers = $this->headerSelector->selectHeadersForMultipart(
                ['application/json']
            );
        } else {
            $headers = $this->headerSelector->selectHeaders(
                ['application/json'],
                []
            );
        }

        // for model (json/xml)
        if (isset($_tempBody)) {
            // $_tempBody is the method argument, if present
            if ($headers['Content-Type'] === 'application/json') {
                $httpBody = \GuzzleHttp\json_encode(ObjectSerializer::sanitizeForSerialization($_tempBody));
            } else {
                $httpBody = $_tempBody;
            }
        } elseif (count($formParams) > 0) {
            if ($multipart) {
                $multipartContents = [];
                foreach ($formParams as $formParamName => $formParamValue) {
                    $multipartContents[] = [
                        'name' => $formParamName,
                        'contents' => $formParamValue
                    ];
                }
                // for HTTP post (form)
                $httpBody = new MultipartStream($multipartContents);

            } elseif ($headers['Content-Type'] === 'application/json') {
                $httpBody = \GuzzleHttp\json_encode($formParams);

            } else {
                // for HTTP post (form)
                $httpBody = \GuzzleHttp\Psr7\build_query($formParams);
            }
        }

        // this endpoint requires Bearer (Auth. Token) authentication (access token)
        if ($this->config->getAccessToken() !== null) {
            $headers['Authorization'] = 'Bearer ' . $this->config->getAccessToken();
        }

        $defaultHeaders = [];
        if ($this->config->getUserAgent()) {
            $defaultHeaders['User-Agent'] = $this->config->getUserAgent();
        }

        $headers = array_merge(
            $defaultHeaders,
            $headerParams,
            $headers
        );

        $query = \GuzzleHttp\Psr7\build_query($queryParams);
        return new Request(
            'GET',
            $this->config->getHost() . $resourcePath . ($query ? "?{$query}" : ''),
            $headers,
            $httpBody
        );
    }

    /**
     * Operation privateClosePositionGet
     *
     * Makes closing position reduce only order .
     *
     * @param  string $instrument_name Instrument name (required)
     * @param  string $type The order type (required)
     * @param  float $price Optional price for limit order. (optional)
     *
     * @throws \OpenAPI\Client\ApiException on non-2xx response
     * @throws \InvalidArgumentException
     * @return object
     */
    public function privateClosePositionGet($instrument_name, $type, $price = null)
    {
        list($response) = $this->privateClosePositionGetWithHttpInfo($instrument_name, $type, $price);
        return $response;
    }

    /**
     * Operation privateClosePositionGetWithHttpInfo
     *
     * Makes closing position reduce only order .
     *
     * @param  string $instrument_name Instrument name (required)
     * @param  string $type The order type (required)
     * @param  float $price Optional price for limit order. (optional)
     *
     * @throws \OpenAPI\Client\ApiException on non-2xx response
     * @throws \InvalidArgumentException
     * @return array of object, HTTP status code, HTTP response headers (array of strings)
     */
    public function privateClosePositionGetWithHttpInfo($instrument_name, $type, $price = null)
    {
        $request = $this->privateClosePositionGetRequest($instrument_name, $type, $price);

        try {
            $options = $this->createHttpClientOption();
            try {
                $response = $this->client->send($request, $options);
            } catch (RequestException $e) {
                throw new ApiException(
                    "[{$e->getCode()}] {$e->getMessage()}",
                    $e->getCode(),
                    $e->getResponse() ? $e->getResponse()->getHeaders() : null,
                    $e->getResponse() ? $e->getResponse()->getBody()->getContents() : null
                );
            }

            $statusCode = $response->getStatusCode();

            if ($statusCode < 200 || $statusCode > 299) {
                throw new ApiException(
                    sprintf(
                        '[%d] Error connecting to the API (%s)',
                        $statusCode,
                        $request->getUri()
                    ),
                    $statusCode,
                    $response->getHeaders(),
                    $response->getBody()
                );
            }

            $responseBody = $response->getBody();
            switch($statusCode) {
                case 200:
                    if ('object' === '\SplFileObject') {
                        $content = $responseBody; //stream goes to serializer
                    } else {
                        $content = $responseBody->getContents();
                    }

                    return [
                        ObjectSerializer::deserialize($content, 'object', []),
                        $response->getStatusCode(),
                        $response->getHeaders()
                    ];
            }

            $returnType = 'object';
            $responseBody = $response->getBody();
            if ($returnType === '\SplFileObject') {
                $content = $responseBody; //stream goes to serializer
            } else {
                $content = $responseBody->getContents();
            }

            return [
                ObjectSerializer::deserialize($content, $returnType, []),
                $response->getStatusCode(),
                $response->getHeaders()
            ];

        } catch (ApiException $e) {
            switch ($e->getCode()) {
                case 200:
                    $data = ObjectSerializer::deserialize(
                        $e->getResponseBody(),
                        'object',
                        $e->getResponseHeaders()
                    );
                    $e->setResponseObject($data);
                    break;
            }
            throw $e;
        }
    }

    /**
     * Operation privateClosePositionGetAsync
     *
     * Makes closing position reduce only order .
     *
     * @param  string $instrument_name Instrument name (required)
     * @param  string $type The order type (required)
     * @param  float $price Optional price for limit order. (optional)
     *
     * @throws \InvalidArgumentException
     * @return \GuzzleHttp\Promise\PromiseInterface
     */
    public function privateClosePositionGetAsync($instrument_name, $type, $price = null)
    {
        return $this->privateClosePositionGetAsyncWithHttpInfo($instrument_name, $type, $price)
            ->then(
                function ($response) {
                    return $response[0];
                }
            );
    }

    /**
     * Operation privateClosePositionGetAsyncWithHttpInfo
     *
     * Makes closing position reduce only order .
     *
     * @param  string $instrument_name Instrument name (required)
     * @param  string $type The order type (required)
     * @param  float $price Optional price for limit order. (optional)
     *
     * @throws \InvalidArgumentException
     * @return \GuzzleHttp\Promise\PromiseInterface
     */
    public function privateClosePositionGetAsyncWithHttpInfo($instrument_name, $type, $price = null)
    {
        $returnType = 'object';
        $request = $this->privateClosePositionGetRequest($instrument_name, $type, $price);

        return $this->client
            ->sendAsync($request, $this->createHttpClientOption())
            ->then(
                function ($response) use ($returnType) {
                    $responseBody = $response->getBody();
                    if ($returnType === '\SplFileObject') {
                        $content = $responseBody; //stream goes to serializer
                    } else {
                        $content = $responseBody->getContents();
                    }

                    return [
                        ObjectSerializer::deserialize($content, $returnType, []),
                        $response->getStatusCode(),
                        $response->getHeaders()
                    ];
                },
                function ($exception) {
                    $response = $exception->getResponse();
                    $statusCode = $response->getStatusCode();
                    throw new ApiException(
                        sprintf(
                            '[%d] Error connecting to the API (%s)',
                            $statusCode,
                            $exception->getRequest()->getUri()
                        ),
                        $statusCode,
                        $response->getHeaders(),
                        $response->getBody()
                    );
                }
            );
    }

    /**
     * Create request for operation 'privateClosePositionGet'
     *
     * @param  string $instrument_name Instrument name (required)
     * @param  string $type The order type (required)
     * @param  float $price Optional price for limit order. (optional)
     *
     * @throws \InvalidArgumentException
     * @return \GuzzleHttp\Psr7\Request
     */
    protected function privateClosePositionGetRequest($instrument_name, $type, $price = null)
    {
        // verify the required parameter 'instrument_name' is set
        if ($instrument_name === null || (is_array($instrument_name) && count($instrument_name) === 0)) {
            throw new \InvalidArgumentException(
                'Missing the required parameter $instrument_name when calling privateClosePositionGet'
            );
        }
        // verify the required parameter 'type' is set
        if ($type === null || (is_array($type) && count($type) === 0)) {
            throw new \InvalidArgumentException(
                'Missing the required parameter $type when calling privateClosePositionGet'
            );
        }

        $resourcePath = '/private/close_position';
        $formParams = [];
        $queryParams = [];
        $headerParams = [];
        $httpBody = '';
        $multipart = false;

        // query params
        if ($instrument_name !== null) {
            $queryParams['instrument_name'] = ObjectSerializer::toQueryValue($instrument_name);
        }
        // query params
        if ($type !== null) {
            $queryParams['type'] = ObjectSerializer::toQueryValue($type);
        }
        // query params
        if ($price !== null) {
            $queryParams['price'] = ObjectSerializer::toQueryValue($price);
        }


        // body params
        $_tempBody = null;

        if ($multipart) {
            $headers = $this->headerSelector->selectHeadersForMultipart(
                ['application/json']
            );
        } else {
            $headers = $this->headerSelector->selectHeaders(
                ['application/json'],
                []
            );
        }

        // for model (json/xml)
        if (isset($_tempBody)) {
            // $_tempBody is the method argument, if present
            if ($headers['Content-Type'] === 'application/json') {
                $httpBody = \GuzzleHttp\json_encode(ObjectSerializer::sanitizeForSerialization($_tempBody));
            } else {
                $httpBody = $_tempBody;
            }
        } elseif (count($formParams) > 0) {
            if ($multipart) {
                $multipartContents = [];
                foreach ($formParams as $formParamName => $formParamValue) {
                    $multipartContents[] = [
                        'name' => $formParamName,
                        'contents' => $formParamValue
                    ];
                }
                // for HTTP post (form)
                $httpBody = new MultipartStream($multipartContents);

            } elseif ($headers['Content-Type'] === 'application/json') {
                $httpBody = \GuzzleHttp\json_encode($formParams);

            } else {
                // for HTTP post (form)
                $httpBody = \GuzzleHttp\Psr7\build_query($formParams);
            }
        }

        // this endpoint requires Bearer (Auth. Token) authentication (access token)
        if ($this->config->getAccessToken() !== null) {
            $headers['Authorization'] = 'Bearer ' . $this->config->getAccessToken();
        }

        $defaultHeaders = [];
        if ($this->config->getUserAgent()) {
            $defaultHeaders['User-Agent'] = $this->config->getUserAgent();
        }

        $headers = array_merge(
            $defaultHeaders,
            $headerParams,
            $headers
        );

        $query = \GuzzleHttp\Psr7\build_query($queryParams);
        return new Request(
            'GET',
            $this->config->getHost() . $resourcePath . ($query ? "?{$query}" : ''),
            $headers,
            $httpBody
        );
    }

    /**
     * Operation privateEditGet
     *
     * Change price, amount and/or other properties of an order.
     *
     * @param  string $order_id The order id (required)
     * @param  float $amount It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH (required)
     * @param  float $price &lt;p&gt;The order price in base currency.&lt;/p&gt; &lt;p&gt;When editing an option order with advanced&#x3D;usd, the field price should be the option price value in USD.&lt;/p&gt; &lt;p&gt;When editing an option order with advanced&#x3D;implv, the field price should be a value of implied volatility in percentages. For example,  price&#x3D;100, means implied volatility of 100%&lt;/p&gt; (required)
     * @param  bool $post_only &lt;p&gt;If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.&lt;/p&gt; &lt;p&gt;Only valid in combination with time_in_force&#x3D;&#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; (optional, default to true)
     * @param  string $advanced Advanced option order type. If you have posted an advanced option order, it is necessary to re-supply this parameter when editing it (Only for options) (optional)
     * @param  float $stop_price Stop price, required for stop limit orders (Only for stop orders) (optional)
     *
     * @throws \OpenAPI\Client\ApiException on non-2xx response
     * @throws \InvalidArgumentException
     * @return object
     */
    public function privateEditGet($order_id, $amount, $price, $post_only = true, $advanced = null, $stop_price = null)
    {
        list($response) = $this->privateEditGetWithHttpInfo($order_id, $amount, $price, $post_only, $advanced, $stop_price);
        return $response;
    }

    /**
     * Operation privateEditGetWithHttpInfo
     *
     * Change price, amount and/or other properties of an order.
     *
     * @param  string $order_id The order id (required)
     * @param  float $amount It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH (required)
     * @param  float $price &lt;p&gt;The order price in base currency.&lt;/p&gt; &lt;p&gt;When editing an option order with advanced&#x3D;usd, the field price should be the option price value in USD.&lt;/p&gt; &lt;p&gt;When editing an option order with advanced&#x3D;implv, the field price should be a value of implied volatility in percentages. For example,  price&#x3D;100, means implied volatility of 100%&lt;/p&gt; (required)
     * @param  bool $post_only &lt;p&gt;If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.&lt;/p&gt; &lt;p&gt;Only valid in combination with time_in_force&#x3D;&#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; (optional, default to true)
     * @param  string $advanced Advanced option order type. If you have posted an advanced option order, it is necessary to re-supply this parameter when editing it (Only for options) (optional)
     * @param  float $stop_price Stop price, required for stop limit orders (Only for stop orders) (optional)
     *
     * @throws \OpenAPI\Client\ApiException on non-2xx response
     * @throws \InvalidArgumentException
     * @return array of object, HTTP status code, HTTP response headers (array of strings)
     */
    public function privateEditGetWithHttpInfo($order_id, $amount, $price, $post_only = true, $advanced = null, $stop_price = null)
    {
        $request = $this->privateEditGetRequest($order_id, $amount, $price, $post_only, $advanced, $stop_price);

        try {
            $options = $this->createHttpClientOption();
            try {
                $response = $this->client->send($request, $options);
            } catch (RequestException $e) {
                throw new ApiException(
                    "[{$e->getCode()}] {$e->getMessage()}",
                    $e->getCode(),
                    $e->getResponse() ? $e->getResponse()->getHeaders() : null,
                    $e->getResponse() ? $e->getResponse()->getBody()->getContents() : null
                );
            }

            $statusCode = $response->getStatusCode();

            if ($statusCode < 200 || $statusCode > 299) {
                throw new ApiException(
                    sprintf(
                        '[%d] Error connecting to the API (%s)',
                        $statusCode,
                        $request->getUri()
                    ),
                    $statusCode,
                    $response->getHeaders(),
                    $response->getBody()
                );
            }

            $responseBody = $response->getBody();
            switch($statusCode) {
                case 200:
                    if ('object' === '\SplFileObject') {
                        $content = $responseBody; //stream goes to serializer
                    } else {
                        $content = $responseBody->getContents();
                    }

                    return [
                        ObjectSerializer::deserialize($content, 'object', []),
                        $response->getStatusCode(),
                        $response->getHeaders()
                    ];
            }

            $returnType = 'object';
            $responseBody = $response->getBody();
            if ($returnType === '\SplFileObject') {
                $content = $responseBody; //stream goes to serializer
            } else {
                $content = $responseBody->getContents();
            }

            return [
                ObjectSerializer::deserialize($content, $returnType, []),
                $response->getStatusCode(),
                $response->getHeaders()
            ];

        } catch (ApiException $e) {
            switch ($e->getCode()) {
                case 200:
                    $data = ObjectSerializer::deserialize(
                        $e->getResponseBody(),
                        'object',
                        $e->getResponseHeaders()
                    );
                    $e->setResponseObject($data);
                    break;
            }
            throw $e;
        }
    }

    /**
     * Operation privateEditGetAsync
     *
     * Change price, amount and/or other properties of an order.
     *
     * @param  string $order_id The order id (required)
     * @param  float $amount It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH (required)
     * @param  float $price &lt;p&gt;The order price in base currency.&lt;/p&gt; &lt;p&gt;When editing an option order with advanced&#x3D;usd, the field price should be the option price value in USD.&lt;/p&gt; &lt;p&gt;When editing an option order with advanced&#x3D;implv, the field price should be a value of implied volatility in percentages. For example,  price&#x3D;100, means implied volatility of 100%&lt;/p&gt; (required)
     * @param  bool $post_only &lt;p&gt;If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.&lt;/p&gt; &lt;p&gt;Only valid in combination with time_in_force&#x3D;&#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; (optional, default to true)
     * @param  string $advanced Advanced option order type. If you have posted an advanced option order, it is necessary to re-supply this parameter when editing it (Only for options) (optional)
     * @param  float $stop_price Stop price, required for stop limit orders (Only for stop orders) (optional)
     *
     * @throws \InvalidArgumentException
     * @return \GuzzleHttp\Promise\PromiseInterface
     */
    public function privateEditGetAsync($order_id, $amount, $price, $post_only = true, $advanced = null, $stop_price = null)
    {
        return $this->privateEditGetAsyncWithHttpInfo($order_id, $amount, $price, $post_only, $advanced, $stop_price)
            ->then(
                function ($response) {
                    return $response[0];
                }
            );
    }

    /**
     * Operation privateEditGetAsyncWithHttpInfo
     *
     * Change price, amount and/or other properties of an order.
     *
     * @param  string $order_id The order id (required)
     * @param  float $amount It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH (required)
     * @param  float $price &lt;p&gt;The order price in base currency.&lt;/p&gt; &lt;p&gt;When editing an option order with advanced&#x3D;usd, the field price should be the option price value in USD.&lt;/p&gt; &lt;p&gt;When editing an option order with advanced&#x3D;implv, the field price should be a value of implied volatility in percentages. For example,  price&#x3D;100, means implied volatility of 100%&lt;/p&gt; (required)
     * @param  bool $post_only &lt;p&gt;If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.&lt;/p&gt; &lt;p&gt;Only valid in combination with time_in_force&#x3D;&#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; (optional, default to true)
     * @param  string $advanced Advanced option order type. If you have posted an advanced option order, it is necessary to re-supply this parameter when editing it (Only for options) (optional)
     * @param  float $stop_price Stop price, required for stop limit orders (Only for stop orders) (optional)
     *
     * @throws \InvalidArgumentException
     * @return \GuzzleHttp\Promise\PromiseInterface
     */
    public function privateEditGetAsyncWithHttpInfo($order_id, $amount, $price, $post_only = true, $advanced = null, $stop_price = null)
    {
        $returnType = 'object';
        $request = $this->privateEditGetRequest($order_id, $amount, $price, $post_only, $advanced, $stop_price);

        return $this->client
            ->sendAsync($request, $this->createHttpClientOption())
            ->then(
                function ($response) use ($returnType) {
                    $responseBody = $response->getBody();
                    if ($returnType === '\SplFileObject') {
                        $content = $responseBody; //stream goes to serializer
                    } else {
                        $content = $responseBody->getContents();
                    }

                    return [
                        ObjectSerializer::deserialize($content, $returnType, []),
                        $response->getStatusCode(),
                        $response->getHeaders()
                    ];
                },
                function ($exception) {
                    $response = $exception->getResponse();
                    $statusCode = $response->getStatusCode();
                    throw new ApiException(
                        sprintf(
                            '[%d] Error connecting to the API (%s)',
                            $statusCode,
                            $exception->getRequest()->getUri()
                        ),
                        $statusCode,
                        $response->getHeaders(),
                        $response->getBody()
                    );
                }
            );
    }

    /**
     * Create request for operation 'privateEditGet'
     *
     * @param  string $order_id The order id (required)
     * @param  float $amount It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH (required)
     * @param  float $price &lt;p&gt;The order price in base currency.&lt;/p&gt; &lt;p&gt;When editing an option order with advanced&#x3D;usd, the field price should be the option price value in USD.&lt;/p&gt; &lt;p&gt;When editing an option order with advanced&#x3D;implv, the field price should be a value of implied volatility in percentages. For example,  price&#x3D;100, means implied volatility of 100%&lt;/p&gt; (required)
     * @param  bool $post_only &lt;p&gt;If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.&lt;/p&gt; &lt;p&gt;Only valid in combination with time_in_force&#x3D;&#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; (optional, default to true)
     * @param  string $advanced Advanced option order type. If you have posted an advanced option order, it is necessary to re-supply this parameter when editing it (Only for options) (optional)
     * @param  float $stop_price Stop price, required for stop limit orders (Only for stop orders) (optional)
     *
     * @throws \InvalidArgumentException
     * @return \GuzzleHttp\Psr7\Request
     */
    protected function privateEditGetRequest($order_id, $amount, $price, $post_only = true, $advanced = null, $stop_price = null)
    {
        // verify the required parameter 'order_id' is set
        if ($order_id === null || (is_array($order_id) && count($order_id) === 0)) {
            throw new \InvalidArgumentException(
                'Missing the required parameter $order_id when calling privateEditGet'
            );
        }
        // verify the required parameter 'amount' is set
        if ($amount === null || (is_array($amount) && count($amount) === 0)) {
            throw new \InvalidArgumentException(
                'Missing the required parameter $amount when calling privateEditGet'
            );
        }
        // verify the required parameter 'price' is set
        if ($price === null || (is_array($price) && count($price) === 0)) {
            throw new \InvalidArgumentException(
                'Missing the required parameter $price when calling privateEditGet'
            );
        }

        $resourcePath = '/private/edit';
        $formParams = [];
        $queryParams = [];
        $headerParams = [];
        $httpBody = '';
        $multipart = false;

        // query params
        if ($order_id !== null) {
            $queryParams['order_id'] = ObjectSerializer::toQueryValue($order_id);
        }
        // query params
        if ($amount !== null) {
            $queryParams['amount'] = ObjectSerializer::toQueryValue($amount);
        }
        // query params
        if ($price !== null) {
            $queryParams['price'] = ObjectSerializer::toQueryValue($price);
        }
        // query params
        if ($post_only !== null) {
            $queryParams['post_only'] = ObjectSerializer::toQueryValue($post_only);
        }
        // query params
        if ($advanced !== null) {
            $queryParams['advanced'] = ObjectSerializer::toQueryValue($advanced);
        }
        // query params
        if ($stop_price !== null) {
            $queryParams['stop_price'] = ObjectSerializer::toQueryValue($stop_price);
        }


        // body params
        $_tempBody = null;

        if ($multipart) {
            $headers = $this->headerSelector->selectHeadersForMultipart(
                ['application/json']
            );
        } else {
            $headers = $this->headerSelector->selectHeaders(
                ['application/json'],
                []
            );
        }

        // for model (json/xml)
        if (isset($_tempBody)) {
            // $_tempBody is the method argument, if present
            if ($headers['Content-Type'] === 'application/json') {
                $httpBody = \GuzzleHttp\json_encode(ObjectSerializer::sanitizeForSerialization($_tempBody));
            } else {
                $httpBody = $_tempBody;
            }
        } elseif (count($formParams) > 0) {
            if ($multipart) {
                $multipartContents = [];
                foreach ($formParams as $formParamName => $formParamValue) {
                    $multipartContents[] = [
                        'name' => $formParamName,
                        'contents' => $formParamValue
                    ];
                }
                // for HTTP post (form)
                $httpBody = new MultipartStream($multipartContents);

            } elseif ($headers['Content-Type'] === 'application/json') {
                $httpBody = \GuzzleHttp\json_encode($formParams);

            } else {
                // for HTTP post (form)
                $httpBody = \GuzzleHttp\Psr7\build_query($formParams);
            }
        }

        // this endpoint requires Bearer (Auth. Token) authentication (access token)
        if ($this->config->getAccessToken() !== null) {
            $headers['Authorization'] = 'Bearer ' . $this->config->getAccessToken();
        }

        $defaultHeaders = [];
        if ($this->config->getUserAgent()) {
            $defaultHeaders['User-Agent'] = $this->config->getUserAgent();
        }

        $headers = array_merge(
            $defaultHeaders,
            $headerParams,
            $headers
        );

        $query = \GuzzleHttp\Psr7\build_query($queryParams);
        return new Request(
            'GET',
            $this->config->getHost() . $resourcePath . ($query ? "?{$query}" : ''),
            $headers,
            $httpBody
        );
    }

    /**
     * Operation privateGetMarginsGet
     *
     * Get margins for given instrument, amount and price.
     *
     * @param  string $instrument_name Instrument name (required)
     * @param  float $amount Amount, integer for future, float for option. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH. (required)
     * @param  float $price Price (required)
     *
     * @throws \OpenAPI\Client\ApiException on non-2xx response
     * @throws \InvalidArgumentException
     * @return object
     */
    public function privateGetMarginsGet($instrument_name, $amount, $price)
    {
        list($response) = $this->privateGetMarginsGetWithHttpInfo($instrument_name, $amount, $price);
        return $response;
    }

    /**
     * Operation privateGetMarginsGetWithHttpInfo
     *
     * Get margins for given instrument, amount and price.
     *
     * @param  string $instrument_name Instrument name (required)
     * @param  float $amount Amount, integer for future, float for option. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH. (required)
     * @param  float $price Price (required)
     *
     * @throws \OpenAPI\Client\ApiException on non-2xx response
     * @throws \InvalidArgumentException
     * @return array of object, HTTP status code, HTTP response headers (array of strings)
     */
    public function privateGetMarginsGetWithHttpInfo($instrument_name, $amount, $price)
    {
        $request = $this->privateGetMarginsGetRequest($instrument_name, $amount, $price);

        try {
            $options = $this->createHttpClientOption();
            try {
                $response = $this->client->send($request, $options);
            } catch (RequestException $e) {
                throw new ApiException(
                    "[{$e->getCode()}] {$e->getMessage()}",
                    $e->getCode(),
                    $e->getResponse() ? $e->getResponse()->getHeaders() : null,
                    $e->getResponse() ? $e->getResponse()->getBody()->getContents() : null
                );
            }

            $statusCode = $response->getStatusCode();

            if ($statusCode < 200 || $statusCode > 299) {
                throw new ApiException(
                    sprintf(
                        '[%d] Error connecting to the API (%s)',
                        $statusCode,
                        $request->getUri()
                    ),
                    $statusCode,
                    $response->getHeaders(),
                    $response->getBody()
                );
            }

            $responseBody = $response->getBody();
            switch($statusCode) {
                case 200:
                    if ('object' === '\SplFileObject') {
                        $content = $responseBody; //stream goes to serializer
                    } else {
                        $content = $responseBody->getContents();
                    }

                    return [
                        ObjectSerializer::deserialize($content, 'object', []),
                        $response->getStatusCode(),
                        $response->getHeaders()
                    ];
            }

            $returnType = 'object';
            $responseBody = $response->getBody();
            if ($returnType === '\SplFileObject') {
                $content = $responseBody; //stream goes to serializer
            } else {
                $content = $responseBody->getContents();
            }

            return [
                ObjectSerializer::deserialize($content, $returnType, []),
                $response->getStatusCode(),
                $response->getHeaders()
            ];

        } catch (ApiException $e) {
            switch ($e->getCode()) {
                case 200:
                    $data = ObjectSerializer::deserialize(
                        $e->getResponseBody(),
                        'object',
                        $e->getResponseHeaders()
                    );
                    $e->setResponseObject($data);
                    break;
            }
            throw $e;
        }
    }

    /**
     * Operation privateGetMarginsGetAsync
     *
     * Get margins for given instrument, amount and price.
     *
     * @param  string $instrument_name Instrument name (required)
     * @param  float $amount Amount, integer for future, float for option. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH. (required)
     * @param  float $price Price (required)
     *
     * @throws \InvalidArgumentException
     * @return \GuzzleHttp\Promise\PromiseInterface
     */
    public function privateGetMarginsGetAsync($instrument_name, $amount, $price)
    {
        return $this->privateGetMarginsGetAsyncWithHttpInfo($instrument_name, $amount, $price)
            ->then(
                function ($response) {
                    return $response[0];
                }
            );
    }

    /**
     * Operation privateGetMarginsGetAsyncWithHttpInfo
     *
     * Get margins for given instrument, amount and price.
     *
     * @param  string $instrument_name Instrument name (required)
     * @param  float $amount Amount, integer for future, float for option. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH. (required)
     * @param  float $price Price (required)
     *
     * @throws \InvalidArgumentException
     * @return \GuzzleHttp\Promise\PromiseInterface
     */
    public function privateGetMarginsGetAsyncWithHttpInfo($instrument_name, $amount, $price)
    {
        $returnType = 'object';
        $request = $this->privateGetMarginsGetRequest($instrument_name, $amount, $price);

        return $this->client
            ->sendAsync($request, $this->createHttpClientOption())
            ->then(
                function ($response) use ($returnType) {
                    $responseBody = $response->getBody();
                    if ($returnType === '\SplFileObject') {
                        $content = $responseBody; //stream goes to serializer
                    } else {
                        $content = $responseBody->getContents();
                    }

                    return [
                        ObjectSerializer::deserialize($content, $returnType, []),
                        $response->getStatusCode(),
                        $response->getHeaders()
                    ];
                },
                function ($exception) {
                    $response = $exception->getResponse();
                    $statusCode = $response->getStatusCode();
                    throw new ApiException(
                        sprintf(
                            '[%d] Error connecting to the API (%s)',
                            $statusCode,
                            $exception->getRequest()->getUri()
                        ),
                        $statusCode,
                        $response->getHeaders(),
                        $response->getBody()
                    );
                }
            );
    }

    /**
     * Create request for operation 'privateGetMarginsGet'
     *
     * @param  string $instrument_name Instrument name (required)
     * @param  float $amount Amount, integer for future, float for option. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH. (required)
     * @param  float $price Price (required)
     *
     * @throws \InvalidArgumentException
     * @return \GuzzleHttp\Psr7\Request
     */
    protected function privateGetMarginsGetRequest($instrument_name, $amount, $price)
    {
        // verify the required parameter 'instrument_name' is set
        if ($instrument_name === null || (is_array($instrument_name) && count($instrument_name) === 0)) {
            throw new \InvalidArgumentException(
                'Missing the required parameter $instrument_name when calling privateGetMarginsGet'
            );
        }
        // verify the required parameter 'amount' is set
        if ($amount === null || (is_array($amount) && count($amount) === 0)) {
            throw new \InvalidArgumentException(
                'Missing the required parameter $amount when calling privateGetMarginsGet'
            );
        }
        // verify the required parameter 'price' is set
        if ($price === null || (is_array($price) && count($price) === 0)) {
            throw new \InvalidArgumentException(
                'Missing the required parameter $price when calling privateGetMarginsGet'
            );
        }

        $resourcePath = '/private/get_margins';
        $formParams = [];
        $queryParams = [];
        $headerParams = [];
        $httpBody = '';
        $multipart = false;

        // query params
        if ($instrument_name !== null) {
            $queryParams['instrument_name'] = ObjectSerializer::toQueryValue($instrument_name);
        }
        // query params
        if ($amount !== null) {
            $queryParams['amount'] = ObjectSerializer::toQueryValue($amount);
        }
        // query params
        if ($price !== null) {
            $queryParams['price'] = ObjectSerializer::toQueryValue($price);
        }


        // body params
        $_tempBody = null;

        if ($multipart) {
            $headers = $this->headerSelector->selectHeadersForMultipart(
                ['application/json']
            );
        } else {
            $headers = $this->headerSelector->selectHeaders(
                ['application/json'],
                []
            );
        }

        // for model (json/xml)
        if (isset($_tempBody)) {
            // $_tempBody is the method argument, if present
            if ($headers['Content-Type'] === 'application/json') {
                $httpBody = \GuzzleHttp\json_encode(ObjectSerializer::sanitizeForSerialization($_tempBody));
            } else {
                $httpBody = $_tempBody;
            }
        } elseif (count($formParams) > 0) {
            if ($multipart) {
                $multipartContents = [];
                foreach ($formParams as $formParamName => $formParamValue) {
                    $multipartContents[] = [
                        'name' => $formParamName,
                        'contents' => $formParamValue
                    ];
                }
                // for HTTP post (form)
                $httpBody = new MultipartStream($multipartContents);

            } elseif ($headers['Content-Type'] === 'application/json') {
                $httpBody = \GuzzleHttp\json_encode($formParams);

            } else {
                // for HTTP post (form)
                $httpBody = \GuzzleHttp\Psr7\build_query($formParams);
            }
        }

        // this endpoint requires Bearer (Auth. Token) authentication (access token)
        if ($this->config->getAccessToken() !== null) {
            $headers['Authorization'] = 'Bearer ' . $this->config->getAccessToken();
        }

        $defaultHeaders = [];
        if ($this->config->getUserAgent()) {
            $defaultHeaders['User-Agent'] = $this->config->getUserAgent();
        }

        $headers = array_merge(
            $defaultHeaders,
            $headerParams,
            $headers
        );

        $query = \GuzzleHttp\Psr7\build_query($queryParams);
        return new Request(
            'GET',
            $this->config->getHost() . $resourcePath . ($query ? "?{$query}" : ''),
            $headers,
            $httpBody
        );
    }

    /**
     * Operation privateGetOpenOrdersByCurrencyGet
     *
     * Retrieves list of user's open orders.
     *
     * @param  string $currency The currency symbol (required)
     * @param  string $kind Instrument kind, if not provided instruments of all kinds are considered (optional)
     * @param  string $type Order type, default - &#x60;all&#x60; (optional)
     *
     * @throws \OpenAPI\Client\ApiException on non-2xx response
     * @throws \InvalidArgumentException
     * @return object
     */
    public function privateGetOpenOrdersByCurrencyGet($currency, $kind = null, $type = null)
    {
        list($response) = $this->privateGetOpenOrdersByCurrencyGetWithHttpInfo($currency, $kind, $type);
        return $response;
    }

    /**
     * Operation privateGetOpenOrdersByCurrencyGetWithHttpInfo
     *
     * Retrieves list of user's open orders.
     *
     * @param  string $currency The currency symbol (required)
     * @param  string $kind Instrument kind, if not provided instruments of all kinds are considered (optional)
     * @param  string $type Order type, default - &#x60;all&#x60; (optional)
     *
     * @throws \OpenAPI\Client\ApiException on non-2xx response
     * @throws \InvalidArgumentException
     * @return array of object, HTTP status code, HTTP response headers (array of strings)
     */
    public function privateGetOpenOrdersByCurrencyGetWithHttpInfo($currency, $kind = null, $type = null)
    {
        $request = $this->privateGetOpenOrdersByCurrencyGetRequest($currency, $kind, $type);

        try {
            $options = $this->createHttpClientOption();
            try {
                $response = $this->client->send($request, $options);
            } catch (RequestException $e) {
                throw new ApiException(
                    "[{$e->getCode()}] {$e->getMessage()}",
                    $e->getCode(),
                    $e->getResponse() ? $e->getResponse()->getHeaders() : null,
                    $e->getResponse() ? $e->getResponse()->getBody()->getContents() : null
                );
            }

            $statusCode = $response->getStatusCode();

            if ($statusCode < 200 || $statusCode > 299) {
                throw new ApiException(
                    sprintf(
                        '[%d] Error connecting to the API (%s)',
                        $statusCode,
                        $request->getUri()
                    ),
                    $statusCode,
                    $response->getHeaders(),
                    $response->getBody()
                );
            }

            $responseBody = $response->getBody();
            switch($statusCode) {
                case 200:
                    if ('object' === '\SplFileObject') {
                        $content = $responseBody; //stream goes to serializer
                    } else {
                        $content = $responseBody->getContents();
                    }

                    return [
                        ObjectSerializer::deserialize($content, 'object', []),
                        $response->getStatusCode(),
                        $response->getHeaders()
                    ];
            }

            $returnType = 'object';
            $responseBody = $response->getBody();
            if ($returnType === '\SplFileObject') {
                $content = $responseBody; //stream goes to serializer
            } else {
                $content = $responseBody->getContents();
            }

            return [
                ObjectSerializer::deserialize($content, $returnType, []),
                $response->getStatusCode(),
                $response->getHeaders()
            ];

        } catch (ApiException $e) {
            switch ($e->getCode()) {
                case 200:
                    $data = ObjectSerializer::deserialize(
                        $e->getResponseBody(),
                        'object',
                        $e->getResponseHeaders()
                    );
                    $e->setResponseObject($data);
                    break;
            }
            throw $e;
        }
    }

    /**
     * Operation privateGetOpenOrdersByCurrencyGetAsync
     *
     * Retrieves list of user's open orders.
     *
     * @param  string $currency The currency symbol (required)
     * @param  string $kind Instrument kind, if not provided instruments of all kinds are considered (optional)
     * @param  string $type Order type, default - &#x60;all&#x60; (optional)
     *
     * @throws \InvalidArgumentException
     * @return \GuzzleHttp\Promise\PromiseInterface
     */
    public function privateGetOpenOrdersByCurrencyGetAsync($currency, $kind = null, $type = null)
    {
        return $this->privateGetOpenOrdersByCurrencyGetAsyncWithHttpInfo($currency, $kind, $type)
            ->then(
                function ($response) {
                    return $response[0];
                }
            );
    }

    /**
     * Operation privateGetOpenOrdersByCurrencyGetAsyncWithHttpInfo
     *
     * Retrieves list of user's open orders.
     *
     * @param  string $currency The currency symbol (required)
     * @param  string $kind Instrument kind, if not provided instruments of all kinds are considered (optional)
     * @param  string $type Order type, default - &#x60;all&#x60; (optional)
     *
     * @throws \InvalidArgumentException
     * @return \GuzzleHttp\Promise\PromiseInterface
     */
    public function privateGetOpenOrdersByCurrencyGetAsyncWithHttpInfo($currency, $kind = null, $type = null)
    {
        $returnType = 'object';
        $request = $this->privateGetOpenOrdersByCurrencyGetRequest($currency, $kind, $type);

        return $this->client
            ->sendAsync($request, $this->createHttpClientOption())
            ->then(
                function ($response) use ($returnType) {
                    $responseBody = $response->getBody();
                    if ($returnType === '\SplFileObject') {
                        $content = $responseBody; //stream goes to serializer
                    } else {
                        $content = $responseBody->getContents();
                    }

                    return [
                        ObjectSerializer::deserialize($content, $returnType, []),
                        $response->getStatusCode(),
                        $response->getHeaders()
                    ];
                },
                function ($exception) {
                    $response = $exception->getResponse();
                    $statusCode = $response->getStatusCode();
                    throw new ApiException(
                        sprintf(
                            '[%d] Error connecting to the API (%s)',
                            $statusCode,
                            $exception->getRequest()->getUri()
                        ),
                        $statusCode,
                        $response->getHeaders(),
                        $response->getBody()
                    );
                }
            );
    }

    /**
     * Create request for operation 'privateGetOpenOrdersByCurrencyGet'
     *
     * @param  string $currency The currency symbol (required)
     * @param  string $kind Instrument kind, if not provided instruments of all kinds are considered (optional)
     * @param  string $type Order type, default - &#x60;all&#x60; (optional)
     *
     * @throws \InvalidArgumentException
     * @return \GuzzleHttp\Psr7\Request
     */
    protected function privateGetOpenOrdersByCurrencyGetRequest($currency, $kind = null, $type = null)
    {
        // verify the required parameter 'currency' is set
        if ($currency === null || (is_array($currency) && count($currency) === 0)) {
            throw new \InvalidArgumentException(
                'Missing the required parameter $currency when calling privateGetOpenOrdersByCurrencyGet'
            );
        }

        $resourcePath = '/private/get_open_orders_by_currency';
        $formParams = [];
        $queryParams = [];
        $headerParams = [];
        $httpBody = '';
        $multipart = false;

        // query params
        if ($currency !== null) {
            $queryParams['currency'] = ObjectSerializer::toQueryValue($currency);
        }
        // query params
        if ($kind !== null) {
            $queryParams['kind'] = ObjectSerializer::toQueryValue($kind);
        }
        // query params
        if ($type !== null) {
            $queryParams['type'] = ObjectSerializer::toQueryValue($type);
        }


        // body params
        $_tempBody = null;

        if ($multipart) {
            $headers = $this->headerSelector->selectHeadersForMultipart(
                ['application/json']
            );
        } else {
            $headers = $this->headerSelector->selectHeaders(
                ['application/json'],
                []
            );
        }

        // for model (json/xml)
        if (isset($_tempBody)) {
            // $_tempBody is the method argument, if present
            if ($headers['Content-Type'] === 'application/json') {
                $httpBody = \GuzzleHttp\json_encode(ObjectSerializer::sanitizeForSerialization($_tempBody));
            } else {
                $httpBody = $_tempBody;
            }
        } elseif (count($formParams) > 0) {
            if ($multipart) {
                $multipartContents = [];
                foreach ($formParams as $formParamName => $formParamValue) {
                    $multipartContents[] = [
                        'name' => $formParamName,
                        'contents' => $formParamValue
                    ];
                }
                // for HTTP post (form)
                $httpBody = new MultipartStream($multipartContents);

            } elseif ($headers['Content-Type'] === 'application/json') {
                $httpBody = \GuzzleHttp\json_encode($formParams);

            } else {
                // for HTTP post (form)
                $httpBody = \GuzzleHttp\Psr7\build_query($formParams);
            }
        }

        // this endpoint requires Bearer (Auth. Token) authentication (access token)
        if ($this->config->getAccessToken() !== null) {
            $headers['Authorization'] = 'Bearer ' . $this->config->getAccessToken();
        }

        $defaultHeaders = [];
        if ($this->config->getUserAgent()) {
            $defaultHeaders['User-Agent'] = $this->config->getUserAgent();
        }

        $headers = array_merge(
            $defaultHeaders,
            $headerParams,
            $headers
        );

        $query = \GuzzleHttp\Psr7\build_query($queryParams);
        return new Request(
            'GET',
            $this->config->getHost() . $resourcePath . ($query ? "?{$query}" : ''),
            $headers,
            $httpBody
        );
    }

    /**
     * Operation privateGetOpenOrdersByInstrumentGet
     *
     * Retrieves list of user's open orders within given Instrument.
     *
     * @param  string $instrument_name Instrument name (required)
     * @param  string $type Order type, default - &#x60;all&#x60; (optional)
     *
     * @throws \OpenAPI\Client\ApiException on non-2xx response
     * @throws \InvalidArgumentException
     * @return object
     */
    public function privateGetOpenOrdersByInstrumentGet($instrument_name, $type = null)
    {
        list($response) = $this->privateGetOpenOrdersByInstrumentGetWithHttpInfo($instrument_name, $type);
        return $response;
    }

    /**
     * Operation privateGetOpenOrdersByInstrumentGetWithHttpInfo
     *
     * Retrieves list of user's open orders within given Instrument.
     *
     * @param  string $instrument_name Instrument name (required)
     * @param  string $type Order type, default - &#x60;all&#x60; (optional)
     *
     * @throws \OpenAPI\Client\ApiException on non-2xx response
     * @throws \InvalidArgumentException
     * @return array of object, HTTP status code, HTTP response headers (array of strings)
     */
    public function privateGetOpenOrdersByInstrumentGetWithHttpInfo($instrument_name, $type = null)
    {
        $request = $this->privateGetOpenOrdersByInstrumentGetRequest($instrument_name, $type);

        try {
            $options = $this->createHttpClientOption();
            try {
                $response = $this->client->send($request, $options);
            } catch (RequestException $e) {
                throw new ApiException(
                    "[{$e->getCode()}] {$e->getMessage()}",
                    $e->getCode(),
                    $e->getResponse() ? $e->getResponse()->getHeaders() : null,
                    $e->getResponse() ? $e->getResponse()->getBody()->getContents() : null
                );
            }

            $statusCode = $response->getStatusCode();

            if ($statusCode < 200 || $statusCode > 299) {
                throw new ApiException(
                    sprintf(
                        '[%d] Error connecting to the API (%s)',
                        $statusCode,
                        $request->getUri()
                    ),
                    $statusCode,
                    $response->getHeaders(),
                    $response->getBody()
                );
            }

            $responseBody = $response->getBody();
            switch($statusCode) {
                case 200:
                    if ('object' === '\SplFileObject') {
                        $content = $responseBody; //stream goes to serializer
                    } else {
                        $content = $responseBody->getContents();
                    }

                    return [
                        ObjectSerializer::deserialize($content, 'object', []),
                        $response->getStatusCode(),
                        $response->getHeaders()
                    ];
            }

            $returnType = 'object';
            $responseBody = $response->getBody();
            if ($returnType === '\SplFileObject') {
                $content = $responseBody; //stream goes to serializer
            } else {
                $content = $responseBody->getContents();
            }

            return [
                ObjectSerializer::deserialize($content, $returnType, []),
                $response->getStatusCode(),
                $response->getHeaders()
            ];

        } catch (ApiException $e) {
            switch ($e->getCode()) {
                case 200:
                    $data = ObjectSerializer::deserialize(
                        $e->getResponseBody(),
                        'object',
                        $e->getResponseHeaders()
                    );
                    $e->setResponseObject($data);
                    break;
            }
            throw $e;
        }
    }

    /**
     * Operation privateGetOpenOrdersByInstrumentGetAsync
     *
     * Retrieves list of user's open orders within given Instrument.
     *
     * @param  string $instrument_name Instrument name (required)
     * @param  string $type Order type, default - &#x60;all&#x60; (optional)
     *
     * @throws \InvalidArgumentException
     * @return \GuzzleHttp\Promise\PromiseInterface
     */
    public function privateGetOpenOrdersByInstrumentGetAsync($instrument_name, $type = null)
    {
        return $this->privateGetOpenOrdersByInstrumentGetAsyncWithHttpInfo($instrument_name, $type)
            ->then(
                function ($response) {
                    return $response[0];
                }
            );
    }

    /**
     * Operation privateGetOpenOrdersByInstrumentGetAsyncWithHttpInfo
     *
     * Retrieves list of user's open orders within given Instrument.
     *
     * @param  string $instrument_name Instrument name (required)
     * @param  string $type Order type, default - &#x60;all&#x60; (optional)
     *
     * @throws \InvalidArgumentException
     * @return \GuzzleHttp\Promise\PromiseInterface
     */
    public function privateGetOpenOrdersByInstrumentGetAsyncWithHttpInfo($instrument_name, $type = null)
    {
        $returnType = 'object';
        $request = $this->privateGetOpenOrdersByInstrumentGetRequest($instrument_name, $type);

        return $this->client
            ->sendAsync($request, $this->createHttpClientOption())
            ->then(
                function ($response) use ($returnType) {
                    $responseBody = $response->getBody();
                    if ($returnType === '\SplFileObject') {
                        $content = $responseBody; //stream goes to serializer
                    } else {
                        $content = $responseBody->getContents();
                    }

                    return [
                        ObjectSerializer::deserialize($content, $returnType, []),
                        $response->getStatusCode(),
                        $response->getHeaders()
                    ];
                },
                function ($exception) {
                    $response = $exception->getResponse();
                    $statusCode = $response->getStatusCode();
                    throw new ApiException(
                        sprintf(
                            '[%d] Error connecting to the API (%s)',
                            $statusCode,
                            $exception->getRequest()->getUri()
                        ),
                        $statusCode,
                        $response->getHeaders(),
                        $response->getBody()
                    );
                }
            );
    }

    /**
     * Create request for operation 'privateGetOpenOrdersByInstrumentGet'
     *
     * @param  string $instrument_name Instrument name (required)
     * @param  string $type Order type, default - &#x60;all&#x60; (optional)
     *
     * @throws \InvalidArgumentException
     * @return \GuzzleHttp\Psr7\Request
     */
    protected function privateGetOpenOrdersByInstrumentGetRequest($instrument_name, $type = null)
    {
        // verify the required parameter 'instrument_name' is set
        if ($instrument_name === null || (is_array($instrument_name) && count($instrument_name) === 0)) {
            throw new \InvalidArgumentException(
                'Missing the required parameter $instrument_name when calling privateGetOpenOrdersByInstrumentGet'
            );
        }

        $resourcePath = '/private/get_open_orders_by_instrument';
        $formParams = [];
        $queryParams = [];
        $headerParams = [];
        $httpBody = '';
        $multipart = false;

        // query params
        if ($instrument_name !== null) {
            $queryParams['instrument_name'] = ObjectSerializer::toQueryValue($instrument_name);
        }
        // query params
        if ($type !== null) {
            $queryParams['type'] = ObjectSerializer::toQueryValue($type);
        }


        // body params
        $_tempBody = null;

        if ($multipart) {
            $headers = $this->headerSelector->selectHeadersForMultipart(
                ['application/json']
            );
        } else {
            $headers = $this->headerSelector->selectHeaders(
                ['application/json'],
                []
            );
        }

        // for model (json/xml)
        if (isset($_tempBody)) {
            // $_tempBody is the method argument, if present
            if ($headers['Content-Type'] === 'application/json') {
                $httpBody = \GuzzleHttp\json_encode(ObjectSerializer::sanitizeForSerialization($_tempBody));
            } else {
                $httpBody = $_tempBody;
            }
        } elseif (count($formParams) > 0) {
            if ($multipart) {
                $multipartContents = [];
                foreach ($formParams as $formParamName => $formParamValue) {
                    $multipartContents[] = [
                        'name' => $formParamName,
                        'contents' => $formParamValue
                    ];
                }
                // for HTTP post (form)
                $httpBody = new MultipartStream($multipartContents);

            } elseif ($headers['Content-Type'] === 'application/json') {
                $httpBody = \GuzzleHttp\json_encode($formParams);

            } else {
                // for HTTP post (form)
                $httpBody = \GuzzleHttp\Psr7\build_query($formParams);
            }
        }

        // this endpoint requires Bearer (Auth. Token) authentication (access token)
        if ($this->config->getAccessToken() !== null) {
            $headers['Authorization'] = 'Bearer ' . $this->config->getAccessToken();
        }

        $defaultHeaders = [];
        if ($this->config->getUserAgent()) {
            $defaultHeaders['User-Agent'] = $this->config->getUserAgent();
        }

        $headers = array_merge(
            $defaultHeaders,
            $headerParams,
            $headers
        );

        $query = \GuzzleHttp\Psr7\build_query($queryParams);
        return new Request(
            'GET',
            $this->config->getHost() . $resourcePath . ($query ? "?{$query}" : ''),
            $headers,
            $httpBody
        );
    }

    /**
     * Operation privateGetOrderHistoryByCurrencyGet
     *
     * Retrieves history of orders that have been partially or fully filled.
     *
     * @param  string $currency The currency symbol (required)
     * @param  string $kind Instrument kind, if not provided instruments of all kinds are considered (optional)
     * @param  int $count Number of requested items, default - &#x60;20&#x60; (optional)
     * @param  int $offset The offset for pagination, default - &#x60;0&#x60; (optional)
     * @param  bool $include_old Include in result orders older than 2 days, default - &#x60;false&#x60; (optional)
     * @param  bool $include_unfilled Include in result fully unfilled closed orders, default - &#x60;false&#x60; (optional)
     *
     * @throws \OpenAPI\Client\ApiException on non-2xx response
     * @throws \InvalidArgumentException
     * @return object
     */
    public function privateGetOrderHistoryByCurrencyGet($currency, $kind = null, $count = null, $offset = null, $include_old = null, $include_unfilled = null)
    {
        list($response) = $this->privateGetOrderHistoryByCurrencyGetWithHttpInfo($currency, $kind, $count, $offset, $include_old, $include_unfilled);
        return $response;
    }

    /**
     * Operation privateGetOrderHistoryByCurrencyGetWithHttpInfo
     *
     * Retrieves history of orders that have been partially or fully filled.
     *
     * @param  string $currency The currency symbol (required)
     * @param  string $kind Instrument kind, if not provided instruments of all kinds are considered (optional)
     * @param  int $count Number of requested items, default - &#x60;20&#x60; (optional)
     * @param  int $offset The offset for pagination, default - &#x60;0&#x60; (optional)
     * @param  bool $include_old Include in result orders older than 2 days, default - &#x60;false&#x60; (optional)
     * @param  bool $include_unfilled Include in result fully unfilled closed orders, default - &#x60;false&#x60; (optional)
     *
     * @throws \OpenAPI\Client\ApiException on non-2xx response
     * @throws \InvalidArgumentException
     * @return array of object, HTTP status code, HTTP response headers (array of strings)
     */
    public function privateGetOrderHistoryByCurrencyGetWithHttpInfo($currency, $kind = null, $count = null, $offset = null, $include_old = null, $include_unfilled = null)
    {
        $request = $this->privateGetOrderHistoryByCurrencyGetRequest($currency, $kind, $count, $offset, $include_old, $include_unfilled);

        try {
            $options = $this->createHttpClientOption();
            try {
                $response = $this->client->send($request, $options);
            } catch (RequestException $e) {
                throw new ApiException(
                    "[{$e->getCode()}] {$e->getMessage()}",
                    $e->getCode(),
                    $e->getResponse() ? $e->getResponse()->getHeaders() : null,
                    $e->getResponse() ? $e->getResponse()->getBody()->getContents() : null
                );
            }

            $statusCode = $response->getStatusCode();

            if ($statusCode < 200 || $statusCode > 299) {
                throw new ApiException(
                    sprintf(
                        '[%d] Error connecting to the API (%s)',
                        $statusCode,
                        $request->getUri()
                    ),
                    $statusCode,
                    $response->getHeaders(),
                    $response->getBody()
                );
            }

            $responseBody = $response->getBody();
            switch($statusCode) {
                case 200:
                    if ('object' === '\SplFileObject') {
                        $content = $responseBody; //stream goes to serializer
                    } else {
                        $content = $responseBody->getContents();
                    }

                    return [
                        ObjectSerializer::deserialize($content, 'object', []),
                        $response->getStatusCode(),
                        $response->getHeaders()
                    ];
            }

            $returnType = 'object';
            $responseBody = $response->getBody();
            if ($returnType === '\SplFileObject') {
                $content = $responseBody; //stream goes to serializer
            } else {
                $content = $responseBody->getContents();
            }

            return [
                ObjectSerializer::deserialize($content, $returnType, []),
                $response->getStatusCode(),
                $response->getHeaders()
            ];

        } catch (ApiException $e) {
            switch ($e->getCode()) {
                case 200:
                    $data = ObjectSerializer::deserialize(
                        $e->getResponseBody(),
                        'object',
                        $e->getResponseHeaders()
                    );
                    $e->setResponseObject($data);
                    break;
            }
            throw $e;
        }
    }

    /**
     * Operation privateGetOrderHistoryByCurrencyGetAsync
     *
     * Retrieves history of orders that have been partially or fully filled.
     *
     * @param  string $currency The currency symbol (required)
     * @param  string $kind Instrument kind, if not provided instruments of all kinds are considered (optional)
     * @param  int $count Number of requested items, default - &#x60;20&#x60; (optional)
     * @param  int $offset The offset for pagination, default - &#x60;0&#x60; (optional)
     * @param  bool $include_old Include in result orders older than 2 days, default - &#x60;false&#x60; (optional)
     * @param  bool $include_unfilled Include in result fully unfilled closed orders, default - &#x60;false&#x60; (optional)
     *
     * @throws \InvalidArgumentException
     * @return \GuzzleHttp\Promise\PromiseInterface
     */
    public function privateGetOrderHistoryByCurrencyGetAsync($currency, $kind = null, $count = null, $offset = null, $include_old = null, $include_unfilled = null)
    {
        return $this->privateGetOrderHistoryByCurrencyGetAsyncWithHttpInfo($currency, $kind, $count, $offset, $include_old, $include_unfilled)
            ->then(
                function ($response) {
                    return $response[0];
                }
            );
    }

    /**
     * Operation privateGetOrderHistoryByCurrencyGetAsyncWithHttpInfo
     *
     * Retrieves history of orders that have been partially or fully filled.
     *
     * @param  string $currency The currency symbol (required)
     * @param  string $kind Instrument kind, if not provided instruments of all kinds are considered (optional)
     * @param  int $count Number of requested items, default - &#x60;20&#x60; (optional)
     * @param  int $offset The offset for pagination, default - &#x60;0&#x60; (optional)
     * @param  bool $include_old Include in result orders older than 2 days, default - &#x60;false&#x60; (optional)
     * @param  bool $include_unfilled Include in result fully unfilled closed orders, default - &#x60;false&#x60; (optional)
     *
     * @throws \InvalidArgumentException
     * @return \GuzzleHttp\Promise\PromiseInterface
     */
    public function privateGetOrderHistoryByCurrencyGetAsyncWithHttpInfo($currency, $kind = null, $count = null, $offset = null, $include_old = null, $include_unfilled = null)
    {
        $returnType = 'object';
        $request = $this->privateGetOrderHistoryByCurrencyGetRequest($currency, $kind, $count, $offset, $include_old, $include_unfilled);

        return $this->client
            ->sendAsync($request, $this->createHttpClientOption())
            ->then(
                function ($response) use ($returnType) {
                    $responseBody = $response->getBody();
                    if ($returnType === '\SplFileObject') {
                        $content = $responseBody; //stream goes to serializer
                    } else {
                        $content = $responseBody->getContents();
                    }

                    return [
                        ObjectSerializer::deserialize($content, $returnType, []),
                        $response->getStatusCode(),
                        $response->getHeaders()
                    ];
                },
                function ($exception) {
                    $response = $exception->getResponse();
                    $statusCode = $response->getStatusCode();
                    throw new ApiException(
                        sprintf(
                            '[%d] Error connecting to the API (%s)',
                            $statusCode,
                            $exception->getRequest()->getUri()
                        ),
                        $statusCode,
                        $response->getHeaders(),
                        $response->getBody()
                    );
                }
            );
    }

    /**
     * Create request for operation 'privateGetOrderHistoryByCurrencyGet'
     *
     * @param  string $currency The currency symbol (required)
     * @param  string $kind Instrument kind, if not provided instruments of all kinds are considered (optional)
     * @param  int $count Number of requested items, default - &#x60;20&#x60; (optional)
     * @param  int $offset The offset for pagination, default - &#x60;0&#x60; (optional)
     * @param  bool $include_old Include in result orders older than 2 days, default - &#x60;false&#x60; (optional)
     * @param  bool $include_unfilled Include in result fully unfilled closed orders, default - &#x60;false&#x60; (optional)
     *
     * @throws \InvalidArgumentException
     * @return \GuzzleHttp\Psr7\Request
     */
    protected function privateGetOrderHistoryByCurrencyGetRequest($currency, $kind = null, $count = null, $offset = null, $include_old = null, $include_unfilled = null)
    {
        // verify the required parameter 'currency' is set
        if ($currency === null || (is_array($currency) && count($currency) === 0)) {
            throw new \InvalidArgumentException(
                'Missing the required parameter $currency when calling privateGetOrderHistoryByCurrencyGet'
            );
        }

        $resourcePath = '/private/get_order_history_by_currency';
        $formParams = [];
        $queryParams = [];
        $headerParams = [];
        $httpBody = '';
        $multipart = false;

        // query params
        if ($currency !== null) {
            $queryParams['currency'] = ObjectSerializer::toQueryValue($currency);
        }
        // query params
        if ($kind !== null) {
            $queryParams['kind'] = ObjectSerializer::toQueryValue($kind);
        }
        // query params
        if ($count !== null) {
            $queryParams['count'] = ObjectSerializer::toQueryValue($count);
        }
        // query params
        if ($offset !== null) {
            $queryParams['offset'] = ObjectSerializer::toQueryValue($offset);
        }
        // query params
        if ($include_old !== null) {
            $queryParams['include_old'] = ObjectSerializer::toQueryValue($include_old);
        }
        // query params
        if ($include_unfilled !== null) {
            $queryParams['include_unfilled'] = ObjectSerializer::toQueryValue($include_unfilled);
        }


        // body params
        $_tempBody = null;

        if ($multipart) {
            $headers = $this->headerSelector->selectHeadersForMultipart(
                ['application/json']
            );
        } else {
            $headers = $this->headerSelector->selectHeaders(
                ['application/json'],
                []
            );
        }

        // for model (json/xml)
        if (isset($_tempBody)) {
            // $_tempBody is the method argument, if present
            if ($headers['Content-Type'] === 'application/json') {
                $httpBody = \GuzzleHttp\json_encode(ObjectSerializer::sanitizeForSerialization($_tempBody));
            } else {
                $httpBody = $_tempBody;
            }
        } elseif (count($formParams) > 0) {
            if ($multipart) {
                $multipartContents = [];
                foreach ($formParams as $formParamName => $formParamValue) {
                    $multipartContents[] = [
                        'name' => $formParamName,
                        'contents' => $formParamValue
                    ];
                }
                // for HTTP post (form)
                $httpBody = new MultipartStream($multipartContents);

            } elseif ($headers['Content-Type'] === 'application/json') {
                $httpBody = \GuzzleHttp\json_encode($formParams);

            } else {
                // for HTTP post (form)
                $httpBody = \GuzzleHttp\Psr7\build_query($formParams);
            }
        }

        // this endpoint requires Bearer (Auth. Token) authentication (access token)
        if ($this->config->getAccessToken() !== null) {
            $headers['Authorization'] = 'Bearer ' . $this->config->getAccessToken();
        }

        $defaultHeaders = [];
        if ($this->config->getUserAgent()) {
            $defaultHeaders['User-Agent'] = $this->config->getUserAgent();
        }

        $headers = array_merge(
            $defaultHeaders,
            $headerParams,
            $headers
        );

        $query = \GuzzleHttp\Psr7\build_query($queryParams);
        return new Request(
            'GET',
            $this->config->getHost() . $resourcePath . ($query ? "?{$query}" : ''),
            $headers,
            $httpBody
        );
    }

    /**
     * Operation privateGetOrderHistoryByInstrumentGet
     *
     * Retrieves history of orders that have been partially or fully filled.
     *
     * @param  string $instrument_name Instrument name (required)
     * @param  int $count Number of requested items, default - &#x60;20&#x60; (optional)
     * @param  int $offset The offset for pagination, default - &#x60;0&#x60; (optional)
     * @param  bool $include_old Include in result orders older than 2 days, default - &#x60;false&#x60; (optional)
     * @param  bool $include_unfilled Include in result fully unfilled closed orders, default - &#x60;false&#x60; (optional)
     *
     * @throws \OpenAPI\Client\ApiException on non-2xx response
     * @throws \InvalidArgumentException
     * @return object
     */
    public function privateGetOrderHistoryByInstrumentGet($instrument_name, $count = null, $offset = null, $include_old = null, $include_unfilled = null)
    {
        list($response) = $this->privateGetOrderHistoryByInstrumentGetWithHttpInfo($instrument_name, $count, $offset, $include_old, $include_unfilled);
        return $response;
    }

    /**
     * Operation privateGetOrderHistoryByInstrumentGetWithHttpInfo
     *
     * Retrieves history of orders that have been partially or fully filled.
     *
     * @param  string $instrument_name Instrument name (required)
     * @param  int $count Number of requested items, default - &#x60;20&#x60; (optional)
     * @param  int $offset The offset for pagination, default - &#x60;0&#x60; (optional)
     * @param  bool $include_old Include in result orders older than 2 days, default - &#x60;false&#x60; (optional)
     * @param  bool $include_unfilled Include in result fully unfilled closed orders, default - &#x60;false&#x60; (optional)
     *
     * @throws \OpenAPI\Client\ApiException on non-2xx response
     * @throws \InvalidArgumentException
     * @return array of object, HTTP status code, HTTP response headers (array of strings)
     */
    public function privateGetOrderHistoryByInstrumentGetWithHttpInfo($instrument_name, $count = null, $offset = null, $include_old = null, $include_unfilled = null)
    {
        $request = $this->privateGetOrderHistoryByInstrumentGetRequest($instrument_name, $count, $offset, $include_old, $include_unfilled);

        try {
            $options = $this->createHttpClientOption();
            try {
                $response = $this->client->send($request, $options);
            } catch (RequestException $e) {
                throw new ApiException(
                    "[{$e->getCode()}] {$e->getMessage()}",
                    $e->getCode(),
                    $e->getResponse() ? $e->getResponse()->getHeaders() : null,
                    $e->getResponse() ? $e->getResponse()->getBody()->getContents() : null
                );
            }

            $statusCode = $response->getStatusCode();

            if ($statusCode < 200 || $statusCode > 299) {
                throw new ApiException(
                    sprintf(
                        '[%d] Error connecting to the API (%s)',
                        $statusCode,
                        $request->getUri()
                    ),
                    $statusCode,
                    $response->getHeaders(),
                    $response->getBody()
                );
            }

            $responseBody = $response->getBody();
            switch($statusCode) {
                case 200:
                    if ('object' === '\SplFileObject') {
                        $content = $responseBody; //stream goes to serializer
                    } else {
                        $content = $responseBody->getContents();
                    }

                    return [
                        ObjectSerializer::deserialize($content, 'object', []),
                        $response->getStatusCode(),
                        $response->getHeaders()
                    ];
            }

            $returnType = 'object';
            $responseBody = $response->getBody();
            if ($returnType === '\SplFileObject') {
                $content = $responseBody; //stream goes to serializer
            } else {
                $content = $responseBody->getContents();
            }

            return [
                ObjectSerializer::deserialize($content, $returnType, []),
                $response->getStatusCode(),
                $response->getHeaders()
            ];

        } catch (ApiException $e) {
            switch ($e->getCode()) {
                case 200:
                    $data = ObjectSerializer::deserialize(
                        $e->getResponseBody(),
                        'object',
                        $e->getResponseHeaders()
                    );
                    $e->setResponseObject($data);
                    break;
            }
            throw $e;
        }
    }

    /**
     * Operation privateGetOrderHistoryByInstrumentGetAsync
     *
     * Retrieves history of orders that have been partially or fully filled.
     *
     * @param  string $instrument_name Instrument name (required)
     * @param  int $count Number of requested items, default - &#x60;20&#x60; (optional)
     * @param  int $offset The offset for pagination, default - &#x60;0&#x60; (optional)
     * @param  bool $include_old Include in result orders older than 2 days, default - &#x60;false&#x60; (optional)
     * @param  bool $include_unfilled Include in result fully unfilled closed orders, default - &#x60;false&#x60; (optional)
     *
     * @throws \InvalidArgumentException
     * @return \GuzzleHttp\Promise\PromiseInterface
     */
    public function privateGetOrderHistoryByInstrumentGetAsync($instrument_name, $count = null, $offset = null, $include_old = null, $include_unfilled = null)
    {
        return $this->privateGetOrderHistoryByInstrumentGetAsyncWithHttpInfo($instrument_name, $count, $offset, $include_old, $include_unfilled)
            ->then(
                function ($response) {
                    return $response[0];
                }
            );
    }

    /**
     * Operation privateGetOrderHistoryByInstrumentGetAsyncWithHttpInfo
     *
     * Retrieves history of orders that have been partially or fully filled.
     *
     * @param  string $instrument_name Instrument name (required)
     * @param  int $count Number of requested items, default - &#x60;20&#x60; (optional)
     * @param  int $offset The offset for pagination, default - &#x60;0&#x60; (optional)
     * @param  bool $include_old Include in result orders older than 2 days, default - &#x60;false&#x60; (optional)
     * @param  bool $include_unfilled Include in result fully unfilled closed orders, default - &#x60;false&#x60; (optional)
     *
     * @throws \InvalidArgumentException
     * @return \GuzzleHttp\Promise\PromiseInterface
     */
    public function privateGetOrderHistoryByInstrumentGetAsyncWithHttpInfo($instrument_name, $count = null, $offset = null, $include_old = null, $include_unfilled = null)
    {
        $returnType = 'object';
        $request = $this->privateGetOrderHistoryByInstrumentGetRequest($instrument_name, $count, $offset, $include_old, $include_unfilled);

        return $this->client
            ->sendAsync($request, $this->createHttpClientOption())
            ->then(
                function ($response) use ($returnType) {
                    $responseBody = $response->getBody();
                    if ($returnType === '\SplFileObject') {
                        $content = $responseBody; //stream goes to serializer
                    } else {
                        $content = $responseBody->getContents();
                    }

                    return [
                        ObjectSerializer::deserialize($content, $returnType, []),
                        $response->getStatusCode(),
                        $response->getHeaders()
                    ];
                },
                function ($exception) {
                    $response = $exception->getResponse();
                    $statusCode = $response->getStatusCode();
                    throw new ApiException(
                        sprintf(
                            '[%d] Error connecting to the API (%s)',
                            $statusCode,
                            $exception->getRequest()->getUri()
                        ),
                        $statusCode,
                        $response->getHeaders(),
                        $response->getBody()
                    );
                }
            );
    }

    /**
     * Create request for operation 'privateGetOrderHistoryByInstrumentGet'
     *
     * @param  string $instrument_name Instrument name (required)
     * @param  int $count Number of requested items, default - &#x60;20&#x60; (optional)
     * @param  int $offset The offset for pagination, default - &#x60;0&#x60; (optional)
     * @param  bool $include_old Include in result orders older than 2 days, default - &#x60;false&#x60; (optional)
     * @param  bool $include_unfilled Include in result fully unfilled closed orders, default - &#x60;false&#x60; (optional)
     *
     * @throws \InvalidArgumentException
     * @return \GuzzleHttp\Psr7\Request
     */
    protected function privateGetOrderHistoryByInstrumentGetRequest($instrument_name, $count = null, $offset = null, $include_old = null, $include_unfilled = null)
    {
        // verify the required parameter 'instrument_name' is set
        if ($instrument_name === null || (is_array($instrument_name) && count($instrument_name) === 0)) {
            throw new \InvalidArgumentException(
                'Missing the required parameter $instrument_name when calling privateGetOrderHistoryByInstrumentGet'
            );
        }

        $resourcePath = '/private/get_order_history_by_instrument';
        $formParams = [];
        $queryParams = [];
        $headerParams = [];
        $httpBody = '';
        $multipart = false;

        // query params
        if ($instrument_name !== null) {
            $queryParams['instrument_name'] = ObjectSerializer::toQueryValue($instrument_name);
        }
        // query params
        if ($count !== null) {
            $queryParams['count'] = ObjectSerializer::toQueryValue($count);
        }
        // query params
        if ($offset !== null) {
            $queryParams['offset'] = ObjectSerializer::toQueryValue($offset);
        }
        // query params
        if ($include_old !== null) {
            $queryParams['include_old'] = ObjectSerializer::toQueryValue($include_old);
        }
        // query params
        if ($include_unfilled !== null) {
            $queryParams['include_unfilled'] = ObjectSerializer::toQueryValue($include_unfilled);
        }


        // body params
        $_tempBody = null;

        if ($multipart) {
            $headers = $this->headerSelector->selectHeadersForMultipart(
                ['application/json']
            );
        } else {
            $headers = $this->headerSelector->selectHeaders(
                ['application/json'],
                []
            );
        }

        // for model (json/xml)
        if (isset($_tempBody)) {
            // $_tempBody is the method argument, if present
            if ($headers['Content-Type'] === 'application/json') {
                $httpBody = \GuzzleHttp\json_encode(ObjectSerializer::sanitizeForSerialization($_tempBody));
            } else {
                $httpBody = $_tempBody;
            }
        } elseif (count($formParams) > 0) {
            if ($multipart) {
                $multipartContents = [];
                foreach ($formParams as $formParamName => $formParamValue) {
                    $multipartContents[] = [
                        'name' => $formParamName,
                        'contents' => $formParamValue
                    ];
                }
                // for HTTP post (form)
                $httpBody = new MultipartStream($multipartContents);

            } elseif ($headers['Content-Type'] === 'application/json') {
                $httpBody = \GuzzleHttp\json_encode($formParams);

            } else {
                // for HTTP post (form)
                $httpBody = \GuzzleHttp\Psr7\build_query($formParams);
            }
        }

        // this endpoint requires Bearer (Auth. Token) authentication (access token)
        if ($this->config->getAccessToken() !== null) {
            $headers['Authorization'] = 'Bearer ' . $this->config->getAccessToken();
        }

        $defaultHeaders = [];
        if ($this->config->getUserAgent()) {
            $defaultHeaders['User-Agent'] = $this->config->getUserAgent();
        }

        $headers = array_merge(
            $defaultHeaders,
            $headerParams,
            $headers
        );

        $query = \GuzzleHttp\Psr7\build_query($queryParams);
        return new Request(
            'GET',
            $this->config->getHost() . $resourcePath . ($query ? "?{$query}" : ''),
            $headers,
            $httpBody
        );
    }

    /**
     * Operation privateGetOrderMarginByIdsGet
     *
     * Retrieves initial margins of given orders
     *
     * @param  string[] $ids Ids of orders (required)
     *
     * @throws \OpenAPI\Client\ApiException on non-2xx response
     * @throws \InvalidArgumentException
     * @return object
     */
    public function privateGetOrderMarginByIdsGet($ids)
    {
        list($response) = $this->privateGetOrderMarginByIdsGetWithHttpInfo($ids);
        return $response;
    }

    /**
     * Operation privateGetOrderMarginByIdsGetWithHttpInfo
     *
     * Retrieves initial margins of given orders
     *
     * @param  string[] $ids Ids of orders (required)
     *
     * @throws \OpenAPI\Client\ApiException on non-2xx response
     * @throws \InvalidArgumentException
     * @return array of object, HTTP status code, HTTP response headers (array of strings)
     */
    public function privateGetOrderMarginByIdsGetWithHttpInfo($ids)
    {
        $request = $this->privateGetOrderMarginByIdsGetRequest($ids);

        try {
            $options = $this->createHttpClientOption();
            try {
                $response = $this->client->send($request, $options);
            } catch (RequestException $e) {
                throw new ApiException(
                    "[{$e->getCode()}] {$e->getMessage()}",
                    $e->getCode(),
                    $e->getResponse() ? $e->getResponse()->getHeaders() : null,
                    $e->getResponse() ? $e->getResponse()->getBody()->getContents() : null
                );
            }

            $statusCode = $response->getStatusCode();

            if ($statusCode < 200 || $statusCode > 299) {
                throw new ApiException(
                    sprintf(
                        '[%d] Error connecting to the API (%s)',
                        $statusCode,
                        $request->getUri()
                    ),
                    $statusCode,
                    $response->getHeaders(),
                    $response->getBody()
                );
            }

            $responseBody = $response->getBody();
            switch($statusCode) {
                case 200:
                    if ('object' === '\SplFileObject') {
                        $content = $responseBody; //stream goes to serializer
                    } else {
                        $content = $responseBody->getContents();
                    }

                    return [
                        ObjectSerializer::deserialize($content, 'object', []),
                        $response->getStatusCode(),
                        $response->getHeaders()
                    ];
            }

            $returnType = 'object';
            $responseBody = $response->getBody();
            if ($returnType === '\SplFileObject') {
                $content = $responseBody; //stream goes to serializer
            } else {
                $content = $responseBody->getContents();
            }

            return [
                ObjectSerializer::deserialize($content, $returnType, []),
                $response->getStatusCode(),
                $response->getHeaders()
            ];

        } catch (ApiException $e) {
            switch ($e->getCode()) {
                case 200:
                    $data = ObjectSerializer::deserialize(
                        $e->getResponseBody(),
                        'object',
                        $e->getResponseHeaders()
                    );
                    $e->setResponseObject($data);
                    break;
            }
            throw $e;
        }
    }

    /**
     * Operation privateGetOrderMarginByIdsGetAsync
     *
     * Retrieves initial margins of given orders
     *
     * @param  string[] $ids Ids of orders (required)
     *
     * @throws \InvalidArgumentException
     * @return \GuzzleHttp\Promise\PromiseInterface
     */
    public function privateGetOrderMarginByIdsGetAsync($ids)
    {
        return $this->privateGetOrderMarginByIdsGetAsyncWithHttpInfo($ids)
            ->then(
                function ($response) {
                    return $response[0];
                }
            );
    }

    /**
     * Operation privateGetOrderMarginByIdsGetAsyncWithHttpInfo
     *
     * Retrieves initial margins of given orders
     *
     * @param  string[] $ids Ids of orders (required)
     *
     * @throws \InvalidArgumentException
     * @return \GuzzleHttp\Promise\PromiseInterface
     */
    public function privateGetOrderMarginByIdsGetAsyncWithHttpInfo($ids)
    {
        $returnType = 'object';
        $request = $this->privateGetOrderMarginByIdsGetRequest($ids);

        return $this->client
            ->sendAsync($request, $this->createHttpClientOption())
            ->then(
                function ($response) use ($returnType) {
                    $responseBody = $response->getBody();
                    if ($returnType === '\SplFileObject') {
                        $content = $responseBody; //stream goes to serializer
                    } else {
                        $content = $responseBody->getContents();
                    }

                    return [
                        ObjectSerializer::deserialize($content, $returnType, []),
                        $response->getStatusCode(),
                        $response->getHeaders()
                    ];
                },
                function ($exception) {
                    $response = $exception->getResponse();
                    $statusCode = $response->getStatusCode();
                    throw new ApiException(
                        sprintf(
                            '[%d] Error connecting to the API (%s)',
                            $statusCode,
                            $exception->getRequest()->getUri()
                        ),
                        $statusCode,
                        $response->getHeaders(),
                        $response->getBody()
                    );
                }
            );
    }

    /**
     * Create request for operation 'privateGetOrderMarginByIdsGet'
     *
     * @param  string[] $ids Ids of orders (required)
     *
     * @throws \InvalidArgumentException
     * @return \GuzzleHttp\Psr7\Request
     */
    protected function privateGetOrderMarginByIdsGetRequest($ids)
    {
        // verify the required parameter 'ids' is set
        if ($ids === null || (is_array($ids) && count($ids) === 0)) {
            throw new \InvalidArgumentException(
                'Missing the required parameter $ids when calling privateGetOrderMarginByIdsGet'
            );
        }

        $resourcePath = '/private/get_order_margin_by_ids';
        $formParams = [];
        $queryParams = [];
        $headerParams = [];
        $httpBody = '';
        $multipart = false;

        // query params
        if (is_array($ids)) {
            $ids = ObjectSerializer::serializeCollection($ids, 'multi', true);
        }
        if ($ids !== null) {
            $queryParams['ids'] = ObjectSerializer::toQueryValue($ids);
        }


        // body params
        $_tempBody = null;

        if ($multipart) {
            $headers = $this->headerSelector->selectHeadersForMultipart(
                ['application/json']
            );
        } else {
            $headers = $this->headerSelector->selectHeaders(
                ['application/json'],
                []
            );
        }

        // for model (json/xml)
        if (isset($_tempBody)) {
            // $_tempBody is the method argument, if present
            if ($headers['Content-Type'] === 'application/json') {
                $httpBody = \GuzzleHttp\json_encode(ObjectSerializer::sanitizeForSerialization($_tempBody));
            } else {
                $httpBody = $_tempBody;
            }
        } elseif (count($formParams) > 0) {
            if ($multipart) {
                $multipartContents = [];
                foreach ($formParams as $formParamName => $formParamValue) {
                    $multipartContents[] = [
                        'name' => $formParamName,
                        'contents' => $formParamValue
                    ];
                }
                // for HTTP post (form)
                $httpBody = new MultipartStream($multipartContents);

            } elseif ($headers['Content-Type'] === 'application/json') {
                $httpBody = \GuzzleHttp\json_encode($formParams);

            } else {
                // for HTTP post (form)
                $httpBody = \GuzzleHttp\Psr7\build_query($formParams);
            }
        }

        // this endpoint requires Bearer (Auth. Token) authentication (access token)
        if ($this->config->getAccessToken() !== null) {
            $headers['Authorization'] = 'Bearer ' . $this->config->getAccessToken();
        }

        $defaultHeaders = [];
        if ($this->config->getUserAgent()) {
            $defaultHeaders['User-Agent'] = $this->config->getUserAgent();
        }

        $headers = array_merge(
            $defaultHeaders,
            $headerParams,
            $headers
        );

        $query = \GuzzleHttp\Psr7\build_query($queryParams);
        return new Request(
            'GET',
            $this->config->getHost() . $resourcePath . ($query ? "?{$query}" : ''),
            $headers,
            $httpBody
        );
    }

    /**
     * Operation privateGetOrderStateGet
     *
     * Retrieve the current state of an order.
     *
     * @param  string $order_id The order id (required)
     *
     * @throws \OpenAPI\Client\ApiException on non-2xx response
     * @throws \InvalidArgumentException
     * @return object|object
     */
    public function privateGetOrderStateGet($order_id)
    {
        list($response) = $this->privateGetOrderStateGetWithHttpInfo($order_id);
        return $response;
    }

    /**
     * Operation privateGetOrderStateGetWithHttpInfo
     *
     * Retrieve the current state of an order.
     *
     * @param  string $order_id The order id (required)
     *
     * @throws \OpenAPI\Client\ApiException on non-2xx response
     * @throws \InvalidArgumentException
     * @return array of object|object, HTTP status code, HTTP response headers (array of strings)
     */
    public function privateGetOrderStateGetWithHttpInfo($order_id)
    {
        $request = $this->privateGetOrderStateGetRequest($order_id);

        try {
            $options = $this->createHttpClientOption();
            try {
                $response = $this->client->send($request, $options);
            } catch (RequestException $e) {
                throw new ApiException(
                    "[{$e->getCode()}] {$e->getMessage()}",
                    $e->getCode(),
                    $e->getResponse() ? $e->getResponse()->getHeaders() : null,
                    $e->getResponse() ? $e->getResponse()->getBody()->getContents() : null
                );
            }

            $statusCode = $response->getStatusCode();

            if ($statusCode < 200 || $statusCode > 299) {
                throw new ApiException(
                    sprintf(
                        '[%d] Error connecting to the API (%s)',
                        $statusCode,
                        $request->getUri()
                    ),
                    $statusCode,
                    $response->getHeaders(),
                    $response->getBody()
                );
            }

            $responseBody = $response->getBody();
            switch($statusCode) {
                case 200:
                    if ('object' === '\SplFileObject') {
                        $content = $responseBody; //stream goes to serializer
                    } else {
                        $content = $responseBody->getContents();
                    }

                    return [
                        ObjectSerializer::deserialize($content, 'object', []),
                        $response->getStatusCode(),
                        $response->getHeaders()
                    ];
                case 400:
                    if ('object' === '\SplFileObject') {
                        $content = $responseBody; //stream goes to serializer
                    } else {
                        $content = $responseBody->getContents();
                    }

                    return [
                        ObjectSerializer::deserialize($content, 'object', []),
                        $response->getStatusCode(),
                        $response->getHeaders()
                    ];
            }

            $returnType = 'object';
            $responseBody = $response->getBody();
            if ($returnType === '\SplFileObject') {
                $content = $responseBody; //stream goes to serializer
            } else {
                $content = $responseBody->getContents();
            }

            return [
                ObjectSerializer::deserialize($content, $returnType, []),
                $response->getStatusCode(),
                $response->getHeaders()
            ];

        } catch (ApiException $e) {
            switch ($e->getCode()) {
                case 200:
                    $data = ObjectSerializer::deserialize(
                        $e->getResponseBody(),
                        'object',
                        $e->getResponseHeaders()
                    );
                    $e->setResponseObject($data);
                    break;
                case 400:
                    $data = ObjectSerializer::deserialize(
                        $e->getResponseBody(),
                        'object',
                        $e->getResponseHeaders()
                    );
                    $e->setResponseObject($data);
                    break;
            }
            throw $e;
        }
    }

    /**
     * Operation privateGetOrderStateGetAsync
     *
     * Retrieve the current state of an order.
     *
     * @param  string $order_id The order id (required)
     *
     * @throws \InvalidArgumentException
     * @return \GuzzleHttp\Promise\PromiseInterface
     */
    public function privateGetOrderStateGetAsync($order_id)
    {
        return $this->privateGetOrderStateGetAsyncWithHttpInfo($order_id)
            ->then(
                function ($response) {
                    return $response[0];
                }
            );
    }

    /**
     * Operation privateGetOrderStateGetAsyncWithHttpInfo
     *
     * Retrieve the current state of an order.
     *
     * @param  string $order_id The order id (required)
     *
     * @throws \InvalidArgumentException
     * @return \GuzzleHttp\Promise\PromiseInterface
     */
    public function privateGetOrderStateGetAsyncWithHttpInfo($order_id)
    {
        $returnType = 'object';
        $request = $this->privateGetOrderStateGetRequest($order_id);

        return $this->client
            ->sendAsync($request, $this->createHttpClientOption())
            ->then(
                function ($response) use ($returnType) {
                    $responseBody = $response->getBody();
                    if ($returnType === '\SplFileObject') {
                        $content = $responseBody; //stream goes to serializer
                    } else {
                        $content = $responseBody->getContents();
                    }

                    return [
                        ObjectSerializer::deserialize($content, $returnType, []),
                        $response->getStatusCode(),
                        $response->getHeaders()
                    ];
                },
                function ($exception) {
                    $response = $exception->getResponse();
                    $statusCode = $response->getStatusCode();
                    throw new ApiException(
                        sprintf(
                            '[%d] Error connecting to the API (%s)',
                            $statusCode,
                            $exception->getRequest()->getUri()
                        ),
                        $statusCode,
                        $response->getHeaders(),
                        $response->getBody()
                    );
                }
            );
    }

    /**
     * Create request for operation 'privateGetOrderStateGet'
     *
     * @param  string $order_id The order id (required)
     *
     * @throws \InvalidArgumentException
     * @return \GuzzleHttp\Psr7\Request
     */
    protected function privateGetOrderStateGetRequest($order_id)
    {
        // verify the required parameter 'order_id' is set
        if ($order_id === null || (is_array($order_id) && count($order_id) === 0)) {
            throw new \InvalidArgumentException(
                'Missing the required parameter $order_id when calling privateGetOrderStateGet'
            );
        }

        $resourcePath = '/private/get_order_state';
        $formParams = [];
        $queryParams = [];
        $headerParams = [];
        $httpBody = '';
        $multipart = false;

        // query params
        if ($order_id !== null) {
            $queryParams['order_id'] = ObjectSerializer::toQueryValue($order_id);
        }


        // body params
        $_tempBody = null;

        if ($multipart) {
            $headers = $this->headerSelector->selectHeadersForMultipart(
                ['application/json']
            );
        } else {
            $headers = $this->headerSelector->selectHeaders(
                ['application/json'],
                []
            );
        }

        // for model (json/xml)
        if (isset($_tempBody)) {
            // $_tempBody is the method argument, if present
            if ($headers['Content-Type'] === 'application/json') {
                $httpBody = \GuzzleHttp\json_encode(ObjectSerializer::sanitizeForSerialization($_tempBody));
            } else {
                $httpBody = $_tempBody;
            }
        } elseif (count($formParams) > 0) {
            if ($multipart) {
                $multipartContents = [];
                foreach ($formParams as $formParamName => $formParamValue) {
                    $multipartContents[] = [
                        'name' => $formParamName,
                        'contents' => $formParamValue
                    ];
                }
                // for HTTP post (form)
                $httpBody = new MultipartStream($multipartContents);

            } elseif ($headers['Content-Type'] === 'application/json') {
                $httpBody = \GuzzleHttp\json_encode($formParams);

            } else {
                // for HTTP post (form)
                $httpBody = \GuzzleHttp\Psr7\build_query($formParams);
            }
        }

        // this endpoint requires Bearer (Auth. Token) authentication (access token)
        if ($this->config->getAccessToken() !== null) {
            $headers['Authorization'] = 'Bearer ' . $this->config->getAccessToken();
        }

        $defaultHeaders = [];
        if ($this->config->getUserAgent()) {
            $defaultHeaders['User-Agent'] = $this->config->getUserAgent();
        }

        $headers = array_merge(
            $defaultHeaders,
            $headerParams,
            $headers
        );

        $query = \GuzzleHttp\Psr7\build_query($queryParams);
        return new Request(
            'GET',
            $this->config->getHost() . $resourcePath . ($query ? "?{$query}" : ''),
            $headers,
            $httpBody
        );
    }

    /**
     * Operation privateGetSettlementHistoryByCurrencyGet
     *
     * Retrieves settlement, delivery and bankruptcy events that have affected your account.
     *
     * @param  string $currency The currency symbol (required)
     * @param  string $type Settlement type (optional)
     * @param  int $count Number of requested items, default - &#x60;20&#x60; (optional)
     *
     * @throws \OpenAPI\Client\ApiException on non-2xx response
     * @throws \InvalidArgumentException
     * @return object
     */
    public function privateGetSettlementHistoryByCurrencyGet($currency, $type = null, $count = null)
    {
        list($response) = $this->privateGetSettlementHistoryByCurrencyGetWithHttpInfo($currency, $type, $count);
        return $response;
    }

    /**
     * Operation privateGetSettlementHistoryByCurrencyGetWithHttpInfo
     *
     * Retrieves settlement, delivery and bankruptcy events that have affected your account.
     *
     * @param  string $currency The currency symbol (required)
     * @param  string $type Settlement type (optional)
     * @param  int $count Number of requested items, default - &#x60;20&#x60; (optional)
     *
     * @throws \OpenAPI\Client\ApiException on non-2xx response
     * @throws \InvalidArgumentException
     * @return array of object, HTTP status code, HTTP response headers (array of strings)
     */
    public function privateGetSettlementHistoryByCurrencyGetWithHttpInfo($currency, $type = null, $count = null)
    {
        $request = $this->privateGetSettlementHistoryByCurrencyGetRequest($currency, $type, $count);

        try {
            $options = $this->createHttpClientOption();
            try {
                $response = $this->client->send($request, $options);
            } catch (RequestException $e) {
                throw new ApiException(
                    "[{$e->getCode()}] {$e->getMessage()}",
                    $e->getCode(),
                    $e->getResponse() ? $e->getResponse()->getHeaders() : null,
                    $e->getResponse() ? $e->getResponse()->getBody()->getContents() : null
                );
            }

            $statusCode = $response->getStatusCode();

            if ($statusCode < 200 || $statusCode > 299) {
                throw new ApiException(
                    sprintf(
                        '[%d] Error connecting to the API (%s)',
                        $statusCode,
                        $request->getUri()
                    ),
                    $statusCode,
                    $response->getHeaders(),
                    $response->getBody()
                );
            }

            $responseBody = $response->getBody();
            switch($statusCode) {
                case 200:
                    if ('object' === '\SplFileObject') {
                        $content = $responseBody; //stream goes to serializer
                    } else {
                        $content = $responseBody->getContents();
                    }

                    return [
                        ObjectSerializer::deserialize($content, 'object', []),
                        $response->getStatusCode(),
                        $response->getHeaders()
                    ];
            }

            $returnType = 'object';
            $responseBody = $response->getBody();
            if ($returnType === '\SplFileObject') {
                $content = $responseBody; //stream goes to serializer
            } else {
                $content = $responseBody->getContents();
            }

            return [
                ObjectSerializer::deserialize($content, $returnType, []),
                $response->getStatusCode(),
                $response->getHeaders()
            ];

        } catch (ApiException $e) {
            switch ($e->getCode()) {
                case 200:
                    $data = ObjectSerializer::deserialize(
                        $e->getResponseBody(),
                        'object',
                        $e->getResponseHeaders()
                    );
                    $e->setResponseObject($data);
                    break;
            }
            throw $e;
        }
    }

    /**
     * Operation privateGetSettlementHistoryByCurrencyGetAsync
     *
     * Retrieves settlement, delivery and bankruptcy events that have affected your account.
     *
     * @param  string $currency The currency symbol (required)
     * @param  string $type Settlement type (optional)
     * @param  int $count Number of requested items, default - &#x60;20&#x60; (optional)
     *
     * @throws \InvalidArgumentException
     * @return \GuzzleHttp\Promise\PromiseInterface
     */
    public function privateGetSettlementHistoryByCurrencyGetAsync($currency, $type = null, $count = null)
    {
        return $this->privateGetSettlementHistoryByCurrencyGetAsyncWithHttpInfo($currency, $type, $count)
            ->then(
                function ($response) {
                    return $response[0];
                }
            );
    }

    /**
     * Operation privateGetSettlementHistoryByCurrencyGetAsyncWithHttpInfo
     *
     * Retrieves settlement, delivery and bankruptcy events that have affected your account.
     *
     * @param  string $currency The currency symbol (required)
     * @param  string $type Settlement type (optional)
     * @param  int $count Number of requested items, default - &#x60;20&#x60; (optional)
     *
     * @throws \InvalidArgumentException
     * @return \GuzzleHttp\Promise\PromiseInterface
     */
    public function privateGetSettlementHistoryByCurrencyGetAsyncWithHttpInfo($currency, $type = null, $count = null)
    {
        $returnType = 'object';
        $request = $this->privateGetSettlementHistoryByCurrencyGetRequest($currency, $type, $count);

        return $this->client
            ->sendAsync($request, $this->createHttpClientOption())
            ->then(
                function ($response) use ($returnType) {
                    $responseBody = $response->getBody();
                    if ($returnType === '\SplFileObject') {
                        $content = $responseBody; //stream goes to serializer
                    } else {
                        $content = $responseBody->getContents();
                    }

                    return [
                        ObjectSerializer::deserialize($content, $returnType, []),
                        $response->getStatusCode(),
                        $response->getHeaders()
                    ];
                },
                function ($exception) {
                    $response = $exception->getResponse();
                    $statusCode = $response->getStatusCode();
                    throw new ApiException(
                        sprintf(
                            '[%d] Error connecting to the API (%s)',
                            $statusCode,
                            $exception->getRequest()->getUri()
                        ),
                        $statusCode,
                        $response->getHeaders(),
                        $response->getBody()
                    );
                }
            );
    }

    /**
     * Create request for operation 'privateGetSettlementHistoryByCurrencyGet'
     *
     * @param  string $currency The currency symbol (required)
     * @param  string $type Settlement type (optional)
     * @param  int $count Number of requested items, default - &#x60;20&#x60; (optional)
     *
     * @throws \InvalidArgumentException
     * @return \GuzzleHttp\Psr7\Request
     */
    protected function privateGetSettlementHistoryByCurrencyGetRequest($currency, $type = null, $count = null)
    {
        // verify the required parameter 'currency' is set
        if ($currency === null || (is_array($currency) && count($currency) === 0)) {
            throw new \InvalidArgumentException(
                'Missing the required parameter $currency when calling privateGetSettlementHistoryByCurrencyGet'
            );
        }

        $resourcePath = '/private/get_settlement_history_by_currency';
        $formParams = [];
        $queryParams = [];
        $headerParams = [];
        $httpBody = '';
        $multipart = false;

        // query params
        if ($currency !== null) {
            $queryParams['currency'] = ObjectSerializer::toQueryValue($currency);
        }
        // query params
        if ($type !== null) {
            $queryParams['type'] = ObjectSerializer::toQueryValue($type);
        }
        // query params
        if ($count !== null) {
            $queryParams['count'] = ObjectSerializer::toQueryValue($count);
        }


        // body params
        $_tempBody = null;

        if ($multipart) {
            $headers = $this->headerSelector->selectHeadersForMultipart(
                ['application/json']
            );
        } else {
            $headers = $this->headerSelector->selectHeaders(
                ['application/json'],
                []
            );
        }

        // for model (json/xml)
        if (isset($_tempBody)) {
            // $_tempBody is the method argument, if present
            if ($headers['Content-Type'] === 'application/json') {
                $httpBody = \GuzzleHttp\json_encode(ObjectSerializer::sanitizeForSerialization($_tempBody));
            } else {
                $httpBody = $_tempBody;
            }
        } elseif (count($formParams) > 0) {
            if ($multipart) {
                $multipartContents = [];
                foreach ($formParams as $formParamName => $formParamValue) {
                    $multipartContents[] = [
                        'name' => $formParamName,
                        'contents' => $formParamValue
                    ];
                }
                // for HTTP post (form)
                $httpBody = new MultipartStream($multipartContents);

            } elseif ($headers['Content-Type'] === 'application/json') {
                $httpBody = \GuzzleHttp\json_encode($formParams);

            } else {
                // for HTTP post (form)
                $httpBody = \GuzzleHttp\Psr7\build_query($formParams);
            }
        }

        // this endpoint requires Bearer (Auth. Token) authentication (access token)
        if ($this->config->getAccessToken() !== null) {
            $headers['Authorization'] = 'Bearer ' . $this->config->getAccessToken();
        }

        $defaultHeaders = [];
        if ($this->config->getUserAgent()) {
            $defaultHeaders['User-Agent'] = $this->config->getUserAgent();
        }

        $headers = array_merge(
            $defaultHeaders,
            $headerParams,
            $headers
        );

        $query = \GuzzleHttp\Psr7\build_query($queryParams);
        return new Request(
            'GET',
            $this->config->getHost() . $resourcePath . ($query ? "?{$query}" : ''),
            $headers,
            $httpBody
        );
    }

    /**
     * Operation privateGetSettlementHistoryByInstrumentGet
     *
     * Retrieves public settlement, delivery and bankruptcy events filtered by instrument name
     *
     * @param  string $instrument_name Instrument name (required)
     * @param  string $type Settlement type (optional)
     * @param  int $count Number of requested items, default - &#x60;20&#x60; (optional)
     *
     * @throws \OpenAPI\Client\ApiException on non-2xx response
     * @throws \InvalidArgumentException
     * @return object
     */
    public function privateGetSettlementHistoryByInstrumentGet($instrument_name, $type = null, $count = null)
    {
        list($response) = $this->privateGetSettlementHistoryByInstrumentGetWithHttpInfo($instrument_name, $type, $count);
        return $response;
    }

    /**
     * Operation privateGetSettlementHistoryByInstrumentGetWithHttpInfo
     *
     * Retrieves public settlement, delivery and bankruptcy events filtered by instrument name
     *
     * @param  string $instrument_name Instrument name (required)
     * @param  string $type Settlement type (optional)
     * @param  int $count Number of requested items, default - &#x60;20&#x60; (optional)
     *
     * @throws \OpenAPI\Client\ApiException on non-2xx response
     * @throws \InvalidArgumentException
     * @return array of object, HTTP status code, HTTP response headers (array of strings)
     */
    public function privateGetSettlementHistoryByInstrumentGetWithHttpInfo($instrument_name, $type = null, $count = null)
    {
        $request = $this->privateGetSettlementHistoryByInstrumentGetRequest($instrument_name, $type, $count);

        try {
            $options = $this->createHttpClientOption();
            try {
                $response = $this->client->send($request, $options);
            } catch (RequestException $e) {
                throw new ApiException(
                    "[{$e->getCode()}] {$e->getMessage()}",
                    $e->getCode(),
                    $e->getResponse() ? $e->getResponse()->getHeaders() : null,
                    $e->getResponse() ? $e->getResponse()->getBody()->getContents() : null
                );
            }

            $statusCode = $response->getStatusCode();

            if ($statusCode < 200 || $statusCode > 299) {
                throw new ApiException(
                    sprintf(
                        '[%d] Error connecting to the API (%s)',
                        $statusCode,
                        $request->getUri()
                    ),
                    $statusCode,
                    $response->getHeaders(),
                    $response->getBody()
                );
            }

            $responseBody = $response->getBody();
            switch($statusCode) {
                case 200:
                    if ('object' === '\SplFileObject') {
                        $content = $responseBody; //stream goes to serializer
                    } else {
                        $content = $responseBody->getContents();
                    }

                    return [
                        ObjectSerializer::deserialize($content, 'object', []),
                        $response->getStatusCode(),
                        $response->getHeaders()
                    ];
            }

            $returnType = 'object';
            $responseBody = $response->getBody();
            if ($returnType === '\SplFileObject') {
                $content = $responseBody; //stream goes to serializer
            } else {
                $content = $responseBody->getContents();
            }

            return [
                ObjectSerializer::deserialize($content, $returnType, []),
                $response->getStatusCode(),
                $response->getHeaders()
            ];

        } catch (ApiException $e) {
            switch ($e->getCode()) {
                case 200:
                    $data = ObjectSerializer::deserialize(
                        $e->getResponseBody(),
                        'object',
                        $e->getResponseHeaders()
                    );
                    $e->setResponseObject($data);
                    break;
            }
            throw $e;
        }
    }

    /**
     * Operation privateGetSettlementHistoryByInstrumentGetAsync
     *
     * Retrieves public settlement, delivery and bankruptcy events filtered by instrument name
     *
     * @param  string $instrument_name Instrument name (required)
     * @param  string $type Settlement type (optional)
     * @param  int $count Number of requested items, default - &#x60;20&#x60; (optional)
     *
     * @throws \InvalidArgumentException
     * @return \GuzzleHttp\Promise\PromiseInterface
     */
    public function privateGetSettlementHistoryByInstrumentGetAsync($instrument_name, $type = null, $count = null)
    {
        return $this->privateGetSettlementHistoryByInstrumentGetAsyncWithHttpInfo($instrument_name, $type, $count)
            ->then(
                function ($response) {
                    return $response[0];
                }
            );
    }

    /**
     * Operation privateGetSettlementHistoryByInstrumentGetAsyncWithHttpInfo
     *
     * Retrieves public settlement, delivery and bankruptcy events filtered by instrument name
     *
     * @param  string $instrument_name Instrument name (required)
     * @param  string $type Settlement type (optional)
     * @param  int $count Number of requested items, default - &#x60;20&#x60; (optional)
     *
     * @throws \InvalidArgumentException
     * @return \GuzzleHttp\Promise\PromiseInterface
     */
    public function privateGetSettlementHistoryByInstrumentGetAsyncWithHttpInfo($instrument_name, $type = null, $count = null)
    {
        $returnType = 'object';
        $request = $this->privateGetSettlementHistoryByInstrumentGetRequest($instrument_name, $type, $count);

        return $this->client
            ->sendAsync($request, $this->createHttpClientOption())
            ->then(
                function ($response) use ($returnType) {
                    $responseBody = $response->getBody();
                    if ($returnType === '\SplFileObject') {
                        $content = $responseBody; //stream goes to serializer
                    } else {
                        $content = $responseBody->getContents();
                    }

                    return [
                        ObjectSerializer::deserialize($content, $returnType, []),
                        $response->getStatusCode(),
                        $response->getHeaders()
                    ];
                },
                function ($exception) {
                    $response = $exception->getResponse();
                    $statusCode = $response->getStatusCode();
                    throw new ApiException(
                        sprintf(
                            '[%d] Error connecting to the API (%s)',
                            $statusCode,
                            $exception->getRequest()->getUri()
                        ),
                        $statusCode,
                        $response->getHeaders(),
                        $response->getBody()
                    );
                }
            );
    }

    /**
     * Create request for operation 'privateGetSettlementHistoryByInstrumentGet'
     *
     * @param  string $instrument_name Instrument name (required)
     * @param  string $type Settlement type (optional)
     * @param  int $count Number of requested items, default - &#x60;20&#x60; (optional)
     *
     * @throws \InvalidArgumentException
     * @return \GuzzleHttp\Psr7\Request
     */
    protected function privateGetSettlementHistoryByInstrumentGetRequest($instrument_name, $type = null, $count = null)
    {
        // verify the required parameter 'instrument_name' is set
        if ($instrument_name === null || (is_array($instrument_name) && count($instrument_name) === 0)) {
            throw new \InvalidArgumentException(
                'Missing the required parameter $instrument_name when calling privateGetSettlementHistoryByInstrumentGet'
            );
        }

        $resourcePath = '/private/get_settlement_history_by_instrument';
        $formParams = [];
        $queryParams = [];
        $headerParams = [];
        $httpBody = '';
        $multipart = false;

        // query params
        if ($instrument_name !== null) {
            $queryParams['instrument_name'] = ObjectSerializer::toQueryValue($instrument_name);
        }
        // query params
        if ($type !== null) {
            $queryParams['type'] = ObjectSerializer::toQueryValue($type);
        }
        // query params
        if ($count !== null) {
            $queryParams['count'] = ObjectSerializer::toQueryValue($count);
        }


        // body params
        $_tempBody = null;

        if ($multipart) {
            $headers = $this->headerSelector->selectHeadersForMultipart(
                ['application/json']
            );
        } else {
            $headers = $this->headerSelector->selectHeaders(
                ['application/json'],
                []
            );
        }

        // for model (json/xml)
        if (isset($_tempBody)) {
            // $_tempBody is the method argument, if present
            if ($headers['Content-Type'] === 'application/json') {
                $httpBody = \GuzzleHttp\json_encode(ObjectSerializer::sanitizeForSerialization($_tempBody));
            } else {
                $httpBody = $_tempBody;
            }
        } elseif (count($formParams) > 0) {
            if ($multipart) {
                $multipartContents = [];
                foreach ($formParams as $formParamName => $formParamValue) {
                    $multipartContents[] = [
                        'name' => $formParamName,
                        'contents' => $formParamValue
                    ];
                }
                // for HTTP post (form)
                $httpBody = new MultipartStream($multipartContents);

            } elseif ($headers['Content-Type'] === 'application/json') {
                $httpBody = \GuzzleHttp\json_encode($formParams);

            } else {
                // for HTTP post (form)
                $httpBody = \GuzzleHttp\Psr7\build_query($formParams);
            }
        }

        // this endpoint requires Bearer (Auth. Token) authentication (access token)
        if ($this->config->getAccessToken() !== null) {
            $headers['Authorization'] = 'Bearer ' . $this->config->getAccessToken();
        }

        $defaultHeaders = [];
        if ($this->config->getUserAgent()) {
            $defaultHeaders['User-Agent'] = $this->config->getUserAgent();
        }

        $headers = array_merge(
            $defaultHeaders,
            $headerParams,
            $headers
        );

        $query = \GuzzleHttp\Psr7\build_query($queryParams);
        return new Request(
            'GET',
            $this->config->getHost() . $resourcePath . ($query ? "?{$query}" : ''),
            $headers,
            $httpBody
        );
    }

    /**
     * Operation privateGetUserTradesByCurrencyAndTimeGet
     *
     * Retrieve the latest user trades that have occurred for instruments in a specific currency symbol and within given time range.
     *
     * @param  string $currency The currency symbol (required)
     * @param  int $start_timestamp The earliest timestamp to return result for (required)
     * @param  int $end_timestamp The most recent timestamp to return result for (required)
     * @param  string $kind Instrument kind, if not provided instruments of all kinds are considered (optional)
     * @param  int $count Number of requested items, default - &#x60;10&#x60; (optional)
     * @param  bool $include_old Include trades older than 7 days, default - &#x60;false&#x60; (optional)
     * @param  string $sorting Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) (optional)
     *
     * @throws \OpenAPI\Client\ApiException on non-2xx response
     * @throws \InvalidArgumentException
     * @return object
     */
    public function privateGetUserTradesByCurrencyAndTimeGet($currency, $start_timestamp, $end_timestamp, $kind = null, $count = null, $include_old = null, $sorting = null)
    {
        list($response) = $this->privateGetUserTradesByCurrencyAndTimeGetWithHttpInfo($currency, $start_timestamp, $end_timestamp, $kind, $count, $include_old, $sorting);
        return $response;
    }

    /**
     * Operation privateGetUserTradesByCurrencyAndTimeGetWithHttpInfo
     *
     * Retrieve the latest user trades that have occurred for instruments in a specific currency symbol and within given time range.
     *
     * @param  string $currency The currency symbol (required)
     * @param  int $start_timestamp The earliest timestamp to return result for (required)
     * @param  int $end_timestamp The most recent timestamp to return result for (required)
     * @param  string $kind Instrument kind, if not provided instruments of all kinds are considered (optional)
     * @param  int $count Number of requested items, default - &#x60;10&#x60; (optional)
     * @param  bool $include_old Include trades older than 7 days, default - &#x60;false&#x60; (optional)
     * @param  string $sorting Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) (optional)
     *
     * @throws \OpenAPI\Client\ApiException on non-2xx response
     * @throws \InvalidArgumentException
     * @return array of object, HTTP status code, HTTP response headers (array of strings)
     */
    public function privateGetUserTradesByCurrencyAndTimeGetWithHttpInfo($currency, $start_timestamp, $end_timestamp, $kind = null, $count = null, $include_old = null, $sorting = null)
    {
        $request = $this->privateGetUserTradesByCurrencyAndTimeGetRequest($currency, $start_timestamp, $end_timestamp, $kind, $count, $include_old, $sorting);

        try {
            $options = $this->createHttpClientOption();
            try {
                $response = $this->client->send($request, $options);
            } catch (RequestException $e) {
                throw new ApiException(
                    "[{$e->getCode()}] {$e->getMessage()}",
                    $e->getCode(),
                    $e->getResponse() ? $e->getResponse()->getHeaders() : null,
                    $e->getResponse() ? $e->getResponse()->getBody()->getContents() : null
                );
            }

            $statusCode = $response->getStatusCode();

            if ($statusCode < 200 || $statusCode > 299) {
                throw new ApiException(
                    sprintf(
                        '[%d] Error connecting to the API (%s)',
                        $statusCode,
                        $request->getUri()
                    ),
                    $statusCode,
                    $response->getHeaders(),
                    $response->getBody()
                );
            }

            $responseBody = $response->getBody();
            switch($statusCode) {
                case 200:
                    if ('object' === '\SplFileObject') {
                        $content = $responseBody; //stream goes to serializer
                    } else {
                        $content = $responseBody->getContents();
                    }

                    return [
                        ObjectSerializer::deserialize($content, 'object', []),
                        $response->getStatusCode(),
                        $response->getHeaders()
                    ];
            }

            $returnType = 'object';
            $responseBody = $response->getBody();
            if ($returnType === '\SplFileObject') {
                $content = $responseBody; //stream goes to serializer
            } else {
                $content = $responseBody->getContents();
            }

            return [
                ObjectSerializer::deserialize($content, $returnType, []),
                $response->getStatusCode(),
                $response->getHeaders()
            ];

        } catch (ApiException $e) {
            switch ($e->getCode()) {
                case 200:
                    $data = ObjectSerializer::deserialize(
                        $e->getResponseBody(),
                        'object',
                        $e->getResponseHeaders()
                    );
                    $e->setResponseObject($data);
                    break;
            }
            throw $e;
        }
    }

    /**
     * Operation privateGetUserTradesByCurrencyAndTimeGetAsync
     *
     * Retrieve the latest user trades that have occurred for instruments in a specific currency symbol and within given time range.
     *
     * @param  string $currency The currency symbol (required)
     * @param  int $start_timestamp The earliest timestamp to return result for (required)
     * @param  int $end_timestamp The most recent timestamp to return result for (required)
     * @param  string $kind Instrument kind, if not provided instruments of all kinds are considered (optional)
     * @param  int $count Number of requested items, default - &#x60;10&#x60; (optional)
     * @param  bool $include_old Include trades older than 7 days, default - &#x60;false&#x60; (optional)
     * @param  string $sorting Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) (optional)
     *
     * @throws \InvalidArgumentException
     * @return \GuzzleHttp\Promise\PromiseInterface
     */
    public function privateGetUserTradesByCurrencyAndTimeGetAsync($currency, $start_timestamp, $end_timestamp, $kind = null, $count = null, $include_old = null, $sorting = null)
    {
        return $this->privateGetUserTradesByCurrencyAndTimeGetAsyncWithHttpInfo($currency, $start_timestamp, $end_timestamp, $kind, $count, $include_old, $sorting)
            ->then(
                function ($response) {
                    return $response[0];
                }
            );
    }

    /**
     * Operation privateGetUserTradesByCurrencyAndTimeGetAsyncWithHttpInfo
     *
     * Retrieve the latest user trades that have occurred for instruments in a specific currency symbol and within given time range.
     *
     * @param  string $currency The currency symbol (required)
     * @param  int $start_timestamp The earliest timestamp to return result for (required)
     * @param  int $end_timestamp The most recent timestamp to return result for (required)
     * @param  string $kind Instrument kind, if not provided instruments of all kinds are considered (optional)
     * @param  int $count Number of requested items, default - &#x60;10&#x60; (optional)
     * @param  bool $include_old Include trades older than 7 days, default - &#x60;false&#x60; (optional)
     * @param  string $sorting Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) (optional)
     *
     * @throws \InvalidArgumentException
     * @return \GuzzleHttp\Promise\PromiseInterface
     */
    public function privateGetUserTradesByCurrencyAndTimeGetAsyncWithHttpInfo($currency, $start_timestamp, $end_timestamp, $kind = null, $count = null, $include_old = null, $sorting = null)
    {
        $returnType = 'object';
        $request = $this->privateGetUserTradesByCurrencyAndTimeGetRequest($currency, $start_timestamp, $end_timestamp, $kind, $count, $include_old, $sorting);

        return $this->client
            ->sendAsync($request, $this->createHttpClientOption())
            ->then(
                function ($response) use ($returnType) {
                    $responseBody = $response->getBody();
                    if ($returnType === '\SplFileObject') {
                        $content = $responseBody; //stream goes to serializer
                    } else {
                        $content = $responseBody->getContents();
                    }

                    return [
                        ObjectSerializer::deserialize($content, $returnType, []),
                        $response->getStatusCode(),
                        $response->getHeaders()
                    ];
                },
                function ($exception) {
                    $response = $exception->getResponse();
                    $statusCode = $response->getStatusCode();
                    throw new ApiException(
                        sprintf(
                            '[%d] Error connecting to the API (%s)',
                            $statusCode,
                            $exception->getRequest()->getUri()
                        ),
                        $statusCode,
                        $response->getHeaders(),
                        $response->getBody()
                    );
                }
            );
    }

    /**
     * Create request for operation 'privateGetUserTradesByCurrencyAndTimeGet'
     *
     * @param  string $currency The currency symbol (required)
     * @param  int $start_timestamp The earliest timestamp to return result for (required)
     * @param  int $end_timestamp The most recent timestamp to return result for (required)
     * @param  string $kind Instrument kind, if not provided instruments of all kinds are considered (optional)
     * @param  int $count Number of requested items, default - &#x60;10&#x60; (optional)
     * @param  bool $include_old Include trades older than 7 days, default - &#x60;false&#x60; (optional)
     * @param  string $sorting Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) (optional)
     *
     * @throws \InvalidArgumentException
     * @return \GuzzleHttp\Psr7\Request
     */
    protected function privateGetUserTradesByCurrencyAndTimeGetRequest($currency, $start_timestamp, $end_timestamp, $kind = null, $count = null, $include_old = null, $sorting = null)
    {
        // verify the required parameter 'currency' is set
        if ($currency === null || (is_array($currency) && count($currency) === 0)) {
            throw new \InvalidArgumentException(
                'Missing the required parameter $currency when calling privateGetUserTradesByCurrencyAndTimeGet'
            );
        }
        // verify the required parameter 'start_timestamp' is set
        if ($start_timestamp === null || (is_array($start_timestamp) && count($start_timestamp) === 0)) {
            throw new \InvalidArgumentException(
                'Missing the required parameter $start_timestamp when calling privateGetUserTradesByCurrencyAndTimeGet'
            );
        }
        // verify the required parameter 'end_timestamp' is set
        if ($end_timestamp === null || (is_array($end_timestamp) && count($end_timestamp) === 0)) {
            throw new \InvalidArgumentException(
                'Missing the required parameter $end_timestamp when calling privateGetUserTradesByCurrencyAndTimeGet'
            );
        }

        $resourcePath = '/private/get_user_trades_by_currency_and_time';
        $formParams = [];
        $queryParams = [];
        $headerParams = [];
        $httpBody = '';
        $multipart = false;

        // query params
        if ($currency !== null) {
            $queryParams['currency'] = ObjectSerializer::toQueryValue($currency);
        }
        // query params
        if ($kind !== null) {
            $queryParams['kind'] = ObjectSerializer::toQueryValue($kind);
        }
        // query params
        if ($start_timestamp !== null) {
            $queryParams['start_timestamp'] = ObjectSerializer::toQueryValue($start_timestamp);
        }
        // query params
        if ($end_timestamp !== null) {
            $queryParams['end_timestamp'] = ObjectSerializer::toQueryValue($end_timestamp);
        }
        // query params
        if ($count !== null) {
            $queryParams['count'] = ObjectSerializer::toQueryValue($count);
        }
        // query params
        if ($include_old !== null) {
            $queryParams['include_old'] = ObjectSerializer::toQueryValue($include_old);
        }
        // query params
        if ($sorting !== null) {
            $queryParams['sorting'] = ObjectSerializer::toQueryValue($sorting);
        }


        // body params
        $_tempBody = null;

        if ($multipart) {
            $headers = $this->headerSelector->selectHeadersForMultipart(
                ['application/json']
            );
        } else {
            $headers = $this->headerSelector->selectHeaders(
                ['application/json'],
                []
            );
        }

        // for model (json/xml)
        if (isset($_tempBody)) {
            // $_tempBody is the method argument, if present
            if ($headers['Content-Type'] === 'application/json') {
                $httpBody = \GuzzleHttp\json_encode(ObjectSerializer::sanitizeForSerialization($_tempBody));
            } else {
                $httpBody = $_tempBody;
            }
        } elseif (count($formParams) > 0) {
            if ($multipart) {
                $multipartContents = [];
                foreach ($formParams as $formParamName => $formParamValue) {
                    $multipartContents[] = [
                        'name' => $formParamName,
                        'contents' => $formParamValue
                    ];
                }
                // for HTTP post (form)
                $httpBody = new MultipartStream($multipartContents);

            } elseif ($headers['Content-Type'] === 'application/json') {
                $httpBody = \GuzzleHttp\json_encode($formParams);

            } else {
                // for HTTP post (form)
                $httpBody = \GuzzleHttp\Psr7\build_query($formParams);
            }
        }

        // this endpoint requires Bearer (Auth. Token) authentication (access token)
        if ($this->config->getAccessToken() !== null) {
            $headers['Authorization'] = 'Bearer ' . $this->config->getAccessToken();
        }

        $defaultHeaders = [];
        if ($this->config->getUserAgent()) {
            $defaultHeaders['User-Agent'] = $this->config->getUserAgent();
        }

        $headers = array_merge(
            $defaultHeaders,
            $headerParams,
            $headers
        );

        $query = \GuzzleHttp\Psr7\build_query($queryParams);
        return new Request(
            'GET',
            $this->config->getHost() . $resourcePath . ($query ? "?{$query}" : ''),
            $headers,
            $httpBody
        );
    }

    /**
     * Operation privateGetUserTradesByCurrencyGet
     *
     * Retrieve the latest user trades that have occurred for instruments in a specific currency symbol.
     *
     * @param  string $currency The currency symbol (required)
     * @param  string $kind Instrument kind, if not provided instruments of all kinds are considered (optional)
     * @param  string $start_id The ID number of the first trade to be returned (optional)
     * @param  string $end_id The ID number of the last trade to be returned (optional)
     * @param  int $count Number of requested items, default - &#x60;10&#x60; (optional)
     * @param  bool $include_old Include trades older than 7 days, default - &#x60;false&#x60; (optional)
     * @param  string $sorting Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) (optional)
     *
     * @throws \OpenAPI\Client\ApiException on non-2xx response
     * @throws \InvalidArgumentException
     * @return object
     */
    public function privateGetUserTradesByCurrencyGet($currency, $kind = null, $start_id = null, $end_id = null, $count = null, $include_old = null, $sorting = null)
    {
        list($response) = $this->privateGetUserTradesByCurrencyGetWithHttpInfo($currency, $kind, $start_id, $end_id, $count, $include_old, $sorting);
        return $response;
    }

    /**
     * Operation privateGetUserTradesByCurrencyGetWithHttpInfo
     *
     * Retrieve the latest user trades that have occurred for instruments in a specific currency symbol.
     *
     * @param  string $currency The currency symbol (required)
     * @param  string $kind Instrument kind, if not provided instruments of all kinds are considered (optional)
     * @param  string $start_id The ID number of the first trade to be returned (optional)
     * @param  string $end_id The ID number of the last trade to be returned (optional)
     * @param  int $count Number of requested items, default - &#x60;10&#x60; (optional)
     * @param  bool $include_old Include trades older than 7 days, default - &#x60;false&#x60; (optional)
     * @param  string $sorting Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) (optional)
     *
     * @throws \OpenAPI\Client\ApiException on non-2xx response
     * @throws \InvalidArgumentException
     * @return array of object, HTTP status code, HTTP response headers (array of strings)
     */
    public function privateGetUserTradesByCurrencyGetWithHttpInfo($currency, $kind = null, $start_id = null, $end_id = null, $count = null, $include_old = null, $sorting = null)
    {
        $request = $this->privateGetUserTradesByCurrencyGetRequest($currency, $kind, $start_id, $end_id, $count, $include_old, $sorting);

        try {
            $options = $this->createHttpClientOption();
            try {
                $response = $this->client->send($request, $options);
            } catch (RequestException $e) {
                throw new ApiException(
                    "[{$e->getCode()}] {$e->getMessage()}",
                    $e->getCode(),
                    $e->getResponse() ? $e->getResponse()->getHeaders() : null,
                    $e->getResponse() ? $e->getResponse()->getBody()->getContents() : null
                );
            }

            $statusCode = $response->getStatusCode();

            if ($statusCode < 200 || $statusCode > 299) {
                throw new ApiException(
                    sprintf(
                        '[%d] Error connecting to the API (%s)',
                        $statusCode,
                        $request->getUri()
                    ),
                    $statusCode,
                    $response->getHeaders(),
                    $response->getBody()
                );
            }

            $responseBody = $response->getBody();
            switch($statusCode) {
                case 200:
                    if ('object' === '\SplFileObject') {
                        $content = $responseBody; //stream goes to serializer
                    } else {
                        $content = $responseBody->getContents();
                    }

                    return [
                        ObjectSerializer::deserialize($content, 'object', []),
                        $response->getStatusCode(),
                        $response->getHeaders()
                    ];
            }

            $returnType = 'object';
            $responseBody = $response->getBody();
            if ($returnType === '\SplFileObject') {
                $content = $responseBody; //stream goes to serializer
            } else {
                $content = $responseBody->getContents();
            }

            return [
                ObjectSerializer::deserialize($content, $returnType, []),
                $response->getStatusCode(),
                $response->getHeaders()
            ];

        } catch (ApiException $e) {
            switch ($e->getCode()) {
                case 200:
                    $data = ObjectSerializer::deserialize(
                        $e->getResponseBody(),
                        'object',
                        $e->getResponseHeaders()
                    );
                    $e->setResponseObject($data);
                    break;
            }
            throw $e;
        }
    }

    /**
     * Operation privateGetUserTradesByCurrencyGetAsync
     *
     * Retrieve the latest user trades that have occurred for instruments in a specific currency symbol.
     *
     * @param  string $currency The currency symbol (required)
     * @param  string $kind Instrument kind, if not provided instruments of all kinds are considered (optional)
     * @param  string $start_id The ID number of the first trade to be returned (optional)
     * @param  string $end_id The ID number of the last trade to be returned (optional)
     * @param  int $count Number of requested items, default - &#x60;10&#x60; (optional)
     * @param  bool $include_old Include trades older than 7 days, default - &#x60;false&#x60; (optional)
     * @param  string $sorting Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) (optional)
     *
     * @throws \InvalidArgumentException
     * @return \GuzzleHttp\Promise\PromiseInterface
     */
    public function privateGetUserTradesByCurrencyGetAsync($currency, $kind = null, $start_id = null, $end_id = null, $count = null, $include_old = null, $sorting = null)
    {
        return $this->privateGetUserTradesByCurrencyGetAsyncWithHttpInfo($currency, $kind, $start_id, $end_id, $count, $include_old, $sorting)
            ->then(
                function ($response) {
                    return $response[0];
                }
            );
    }

    /**
     * Operation privateGetUserTradesByCurrencyGetAsyncWithHttpInfo
     *
     * Retrieve the latest user trades that have occurred for instruments in a specific currency symbol.
     *
     * @param  string $currency The currency symbol (required)
     * @param  string $kind Instrument kind, if not provided instruments of all kinds are considered (optional)
     * @param  string $start_id The ID number of the first trade to be returned (optional)
     * @param  string $end_id The ID number of the last trade to be returned (optional)
     * @param  int $count Number of requested items, default - &#x60;10&#x60; (optional)
     * @param  bool $include_old Include trades older than 7 days, default - &#x60;false&#x60; (optional)
     * @param  string $sorting Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) (optional)
     *
     * @throws \InvalidArgumentException
     * @return \GuzzleHttp\Promise\PromiseInterface
     */
    public function privateGetUserTradesByCurrencyGetAsyncWithHttpInfo($currency, $kind = null, $start_id = null, $end_id = null, $count = null, $include_old = null, $sorting = null)
    {
        $returnType = 'object';
        $request = $this->privateGetUserTradesByCurrencyGetRequest($currency, $kind, $start_id, $end_id, $count, $include_old, $sorting);

        return $this->client
            ->sendAsync($request, $this->createHttpClientOption())
            ->then(
                function ($response) use ($returnType) {
                    $responseBody = $response->getBody();
                    if ($returnType === '\SplFileObject') {
                        $content = $responseBody; //stream goes to serializer
                    } else {
                        $content = $responseBody->getContents();
                    }

                    return [
                        ObjectSerializer::deserialize($content, $returnType, []),
                        $response->getStatusCode(),
                        $response->getHeaders()
                    ];
                },
                function ($exception) {
                    $response = $exception->getResponse();
                    $statusCode = $response->getStatusCode();
                    throw new ApiException(
                        sprintf(
                            '[%d] Error connecting to the API (%s)',
                            $statusCode,
                            $exception->getRequest()->getUri()
                        ),
                        $statusCode,
                        $response->getHeaders(),
                        $response->getBody()
                    );
                }
            );
    }

    /**
     * Create request for operation 'privateGetUserTradesByCurrencyGet'
     *
     * @param  string $currency The currency symbol (required)
     * @param  string $kind Instrument kind, if not provided instruments of all kinds are considered (optional)
     * @param  string $start_id The ID number of the first trade to be returned (optional)
     * @param  string $end_id The ID number of the last trade to be returned (optional)
     * @param  int $count Number of requested items, default - &#x60;10&#x60; (optional)
     * @param  bool $include_old Include trades older than 7 days, default - &#x60;false&#x60; (optional)
     * @param  string $sorting Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) (optional)
     *
     * @throws \InvalidArgumentException
     * @return \GuzzleHttp\Psr7\Request
     */
    protected function privateGetUserTradesByCurrencyGetRequest($currency, $kind = null, $start_id = null, $end_id = null, $count = null, $include_old = null, $sorting = null)
    {
        // verify the required parameter 'currency' is set
        if ($currency === null || (is_array($currency) && count($currency) === 0)) {
            throw new \InvalidArgumentException(
                'Missing the required parameter $currency when calling privateGetUserTradesByCurrencyGet'
            );
        }

        $resourcePath = '/private/get_user_trades_by_currency';
        $formParams = [];
        $queryParams = [];
        $headerParams = [];
        $httpBody = '';
        $multipart = false;

        // query params
        if ($currency !== null) {
            $queryParams['currency'] = ObjectSerializer::toQueryValue($currency);
        }
        // query params
        if ($kind !== null) {
            $queryParams['kind'] = ObjectSerializer::toQueryValue($kind);
        }
        // query params
        if ($start_id !== null) {
            $queryParams['start_id'] = ObjectSerializer::toQueryValue($start_id);
        }
        // query params
        if ($end_id !== null) {
            $queryParams['end_id'] = ObjectSerializer::toQueryValue($end_id);
        }
        // query params
        if ($count !== null) {
            $queryParams['count'] = ObjectSerializer::toQueryValue($count);
        }
        // query params
        if ($include_old !== null) {
            $queryParams['include_old'] = ObjectSerializer::toQueryValue($include_old);
        }
        // query params
        if ($sorting !== null) {
            $queryParams['sorting'] = ObjectSerializer::toQueryValue($sorting);
        }


        // body params
        $_tempBody = null;

        if ($multipart) {
            $headers = $this->headerSelector->selectHeadersForMultipart(
                ['application/json']
            );
        } else {
            $headers = $this->headerSelector->selectHeaders(
                ['application/json'],
                []
            );
        }

        // for model (json/xml)
        if (isset($_tempBody)) {
            // $_tempBody is the method argument, if present
            if ($headers['Content-Type'] === 'application/json') {
                $httpBody = \GuzzleHttp\json_encode(ObjectSerializer::sanitizeForSerialization($_tempBody));
            } else {
                $httpBody = $_tempBody;
            }
        } elseif (count($formParams) > 0) {
            if ($multipart) {
                $multipartContents = [];
                foreach ($formParams as $formParamName => $formParamValue) {
                    $multipartContents[] = [
                        'name' => $formParamName,
                        'contents' => $formParamValue
                    ];
                }
                // for HTTP post (form)
                $httpBody = new MultipartStream($multipartContents);

            } elseif ($headers['Content-Type'] === 'application/json') {
                $httpBody = \GuzzleHttp\json_encode($formParams);

            } else {
                // for HTTP post (form)
                $httpBody = \GuzzleHttp\Psr7\build_query($formParams);
            }
        }

        // this endpoint requires Bearer (Auth. Token) authentication (access token)
        if ($this->config->getAccessToken() !== null) {
            $headers['Authorization'] = 'Bearer ' . $this->config->getAccessToken();
        }

        $defaultHeaders = [];
        if ($this->config->getUserAgent()) {
            $defaultHeaders['User-Agent'] = $this->config->getUserAgent();
        }

        $headers = array_merge(
            $defaultHeaders,
            $headerParams,
            $headers
        );

        $query = \GuzzleHttp\Psr7\build_query($queryParams);
        return new Request(
            'GET',
            $this->config->getHost() . $resourcePath . ($query ? "?{$query}" : ''),
            $headers,
            $httpBody
        );
    }

    /**
     * Operation privateGetUserTradesByInstrumentAndTimeGet
     *
     * Retrieve the latest user trades that have occurred for a specific instrument and within given time range.
     *
     * @param  string $instrument_name Instrument name (required)
     * @param  int $start_timestamp The earliest timestamp to return result for (required)
     * @param  int $end_timestamp The most recent timestamp to return result for (required)
     * @param  int $count Number of requested items, default - &#x60;10&#x60; (optional)
     * @param  bool $include_old Include trades older than 7 days, default - &#x60;false&#x60; (optional)
     * @param  string $sorting Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) (optional)
     *
     * @throws \OpenAPI\Client\ApiException on non-2xx response
     * @throws \InvalidArgumentException
     * @return object
     */
    public function privateGetUserTradesByInstrumentAndTimeGet($instrument_name, $start_timestamp, $end_timestamp, $count = null, $include_old = null, $sorting = null)
    {
        list($response) = $this->privateGetUserTradesByInstrumentAndTimeGetWithHttpInfo($instrument_name, $start_timestamp, $end_timestamp, $count, $include_old, $sorting);
        return $response;
    }

    /**
     * Operation privateGetUserTradesByInstrumentAndTimeGetWithHttpInfo
     *
     * Retrieve the latest user trades that have occurred for a specific instrument and within given time range.
     *
     * @param  string $instrument_name Instrument name (required)
     * @param  int $start_timestamp The earliest timestamp to return result for (required)
     * @param  int $end_timestamp The most recent timestamp to return result for (required)
     * @param  int $count Number of requested items, default - &#x60;10&#x60; (optional)
     * @param  bool $include_old Include trades older than 7 days, default - &#x60;false&#x60; (optional)
     * @param  string $sorting Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) (optional)
     *
     * @throws \OpenAPI\Client\ApiException on non-2xx response
     * @throws \InvalidArgumentException
     * @return array of object, HTTP status code, HTTP response headers (array of strings)
     */
    public function privateGetUserTradesByInstrumentAndTimeGetWithHttpInfo($instrument_name, $start_timestamp, $end_timestamp, $count = null, $include_old = null, $sorting = null)
    {
        $request = $this->privateGetUserTradesByInstrumentAndTimeGetRequest($instrument_name, $start_timestamp, $end_timestamp, $count, $include_old, $sorting);

        try {
            $options = $this->createHttpClientOption();
            try {
                $response = $this->client->send($request, $options);
            } catch (RequestException $e) {
                throw new ApiException(
                    "[{$e->getCode()}] {$e->getMessage()}",
                    $e->getCode(),
                    $e->getResponse() ? $e->getResponse()->getHeaders() : null,
                    $e->getResponse() ? $e->getResponse()->getBody()->getContents() : null
                );
            }

            $statusCode = $response->getStatusCode();

            if ($statusCode < 200 || $statusCode > 299) {
                throw new ApiException(
                    sprintf(
                        '[%d] Error connecting to the API (%s)',
                        $statusCode,
                        $request->getUri()
                    ),
                    $statusCode,
                    $response->getHeaders(),
                    $response->getBody()
                );
            }

            $responseBody = $response->getBody();
            switch($statusCode) {
                case 200:
                    if ('object' === '\SplFileObject') {
                        $content = $responseBody; //stream goes to serializer
                    } else {
                        $content = $responseBody->getContents();
                    }

                    return [
                        ObjectSerializer::deserialize($content, 'object', []),
                        $response->getStatusCode(),
                        $response->getHeaders()
                    ];
            }

            $returnType = 'object';
            $responseBody = $response->getBody();
            if ($returnType === '\SplFileObject') {
                $content = $responseBody; //stream goes to serializer
            } else {
                $content = $responseBody->getContents();
            }

            return [
                ObjectSerializer::deserialize($content, $returnType, []),
                $response->getStatusCode(),
                $response->getHeaders()
            ];

        } catch (ApiException $e) {
            switch ($e->getCode()) {
                case 200:
                    $data = ObjectSerializer::deserialize(
                        $e->getResponseBody(),
                        'object',
                        $e->getResponseHeaders()
                    );
                    $e->setResponseObject($data);
                    break;
            }
            throw $e;
        }
    }

    /**
     * Operation privateGetUserTradesByInstrumentAndTimeGetAsync
     *
     * Retrieve the latest user trades that have occurred for a specific instrument and within given time range.
     *
     * @param  string $instrument_name Instrument name (required)
     * @param  int $start_timestamp The earliest timestamp to return result for (required)
     * @param  int $end_timestamp The most recent timestamp to return result for (required)
     * @param  int $count Number of requested items, default - &#x60;10&#x60; (optional)
     * @param  bool $include_old Include trades older than 7 days, default - &#x60;false&#x60; (optional)
     * @param  string $sorting Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) (optional)
     *
     * @throws \InvalidArgumentException
     * @return \GuzzleHttp\Promise\PromiseInterface
     */
    public function privateGetUserTradesByInstrumentAndTimeGetAsync($instrument_name, $start_timestamp, $end_timestamp, $count = null, $include_old = null, $sorting = null)
    {
        return $this->privateGetUserTradesByInstrumentAndTimeGetAsyncWithHttpInfo($instrument_name, $start_timestamp, $end_timestamp, $count, $include_old, $sorting)
            ->then(
                function ($response) {
                    return $response[0];
                }
            );
    }

    /**
     * Operation privateGetUserTradesByInstrumentAndTimeGetAsyncWithHttpInfo
     *
     * Retrieve the latest user trades that have occurred for a specific instrument and within given time range.
     *
     * @param  string $instrument_name Instrument name (required)
     * @param  int $start_timestamp The earliest timestamp to return result for (required)
     * @param  int $end_timestamp The most recent timestamp to return result for (required)
     * @param  int $count Number of requested items, default - &#x60;10&#x60; (optional)
     * @param  bool $include_old Include trades older than 7 days, default - &#x60;false&#x60; (optional)
     * @param  string $sorting Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) (optional)
     *
     * @throws \InvalidArgumentException
     * @return \GuzzleHttp\Promise\PromiseInterface
     */
    public function privateGetUserTradesByInstrumentAndTimeGetAsyncWithHttpInfo($instrument_name, $start_timestamp, $end_timestamp, $count = null, $include_old = null, $sorting = null)
    {
        $returnType = 'object';
        $request = $this->privateGetUserTradesByInstrumentAndTimeGetRequest($instrument_name, $start_timestamp, $end_timestamp, $count, $include_old, $sorting);

        return $this->client
            ->sendAsync($request, $this->createHttpClientOption())
            ->then(
                function ($response) use ($returnType) {
                    $responseBody = $response->getBody();
                    if ($returnType === '\SplFileObject') {
                        $content = $responseBody; //stream goes to serializer
                    } else {
                        $content = $responseBody->getContents();
                    }

                    return [
                        ObjectSerializer::deserialize($content, $returnType, []),
                        $response->getStatusCode(),
                        $response->getHeaders()
                    ];
                },
                function ($exception) {
                    $response = $exception->getResponse();
                    $statusCode = $response->getStatusCode();
                    throw new ApiException(
                        sprintf(
                            '[%d] Error connecting to the API (%s)',
                            $statusCode,
                            $exception->getRequest()->getUri()
                        ),
                        $statusCode,
                        $response->getHeaders(),
                        $response->getBody()
                    );
                }
            );
    }

    /**
     * Create request for operation 'privateGetUserTradesByInstrumentAndTimeGet'
     *
     * @param  string $instrument_name Instrument name (required)
     * @param  int $start_timestamp The earliest timestamp to return result for (required)
     * @param  int $end_timestamp The most recent timestamp to return result for (required)
     * @param  int $count Number of requested items, default - &#x60;10&#x60; (optional)
     * @param  bool $include_old Include trades older than 7 days, default - &#x60;false&#x60; (optional)
     * @param  string $sorting Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) (optional)
     *
     * @throws \InvalidArgumentException
     * @return \GuzzleHttp\Psr7\Request
     */
    protected function privateGetUserTradesByInstrumentAndTimeGetRequest($instrument_name, $start_timestamp, $end_timestamp, $count = null, $include_old = null, $sorting = null)
    {
        // verify the required parameter 'instrument_name' is set
        if ($instrument_name === null || (is_array($instrument_name) && count($instrument_name) === 0)) {
            throw new \InvalidArgumentException(
                'Missing the required parameter $instrument_name when calling privateGetUserTradesByInstrumentAndTimeGet'
            );
        }
        // verify the required parameter 'start_timestamp' is set
        if ($start_timestamp === null || (is_array($start_timestamp) && count($start_timestamp) === 0)) {
            throw new \InvalidArgumentException(
                'Missing the required parameter $start_timestamp when calling privateGetUserTradesByInstrumentAndTimeGet'
            );
        }
        // verify the required parameter 'end_timestamp' is set
        if ($end_timestamp === null || (is_array($end_timestamp) && count($end_timestamp) === 0)) {
            throw new \InvalidArgumentException(
                'Missing the required parameter $end_timestamp when calling privateGetUserTradesByInstrumentAndTimeGet'
            );
        }

        $resourcePath = '/private/get_user_trades_by_instrument_and_time';
        $formParams = [];
        $queryParams = [];
        $headerParams = [];
        $httpBody = '';
        $multipart = false;

        // query params
        if ($instrument_name !== null) {
            $queryParams['instrument_name'] = ObjectSerializer::toQueryValue($instrument_name);
        }
        // query params
        if ($start_timestamp !== null) {
            $queryParams['start_timestamp'] = ObjectSerializer::toQueryValue($start_timestamp);
        }
        // query params
        if ($end_timestamp !== null) {
            $queryParams['end_timestamp'] = ObjectSerializer::toQueryValue($end_timestamp);
        }
        // query params
        if ($count !== null) {
            $queryParams['count'] = ObjectSerializer::toQueryValue($count);
        }
        // query params
        if ($include_old !== null) {
            $queryParams['include_old'] = ObjectSerializer::toQueryValue($include_old);
        }
        // query params
        if ($sorting !== null) {
            $queryParams['sorting'] = ObjectSerializer::toQueryValue($sorting);
        }


        // body params
        $_tempBody = null;

        if ($multipart) {
            $headers = $this->headerSelector->selectHeadersForMultipart(
                ['application/json']
            );
        } else {
            $headers = $this->headerSelector->selectHeaders(
                ['application/json'],
                []
            );
        }

        // for model (json/xml)
        if (isset($_tempBody)) {
            // $_tempBody is the method argument, if present
            if ($headers['Content-Type'] === 'application/json') {
                $httpBody = \GuzzleHttp\json_encode(ObjectSerializer::sanitizeForSerialization($_tempBody));
            } else {
                $httpBody = $_tempBody;
            }
        } elseif (count($formParams) > 0) {
            if ($multipart) {
                $multipartContents = [];
                foreach ($formParams as $formParamName => $formParamValue) {
                    $multipartContents[] = [
                        'name' => $formParamName,
                        'contents' => $formParamValue
                    ];
                }
                // for HTTP post (form)
                $httpBody = new MultipartStream($multipartContents);

            } elseif ($headers['Content-Type'] === 'application/json') {
                $httpBody = \GuzzleHttp\json_encode($formParams);

            } else {
                // for HTTP post (form)
                $httpBody = \GuzzleHttp\Psr7\build_query($formParams);
            }
        }

        // this endpoint requires Bearer (Auth. Token) authentication (access token)
        if ($this->config->getAccessToken() !== null) {
            $headers['Authorization'] = 'Bearer ' . $this->config->getAccessToken();
        }

        $defaultHeaders = [];
        if ($this->config->getUserAgent()) {
            $defaultHeaders['User-Agent'] = $this->config->getUserAgent();
        }

        $headers = array_merge(
            $defaultHeaders,
            $headerParams,
            $headers
        );

        $query = \GuzzleHttp\Psr7\build_query($queryParams);
        return new Request(
            'GET',
            $this->config->getHost() . $resourcePath . ($query ? "?{$query}" : ''),
            $headers,
            $httpBody
        );
    }

    /**
     * Operation privateGetUserTradesByInstrumentGet
     *
     * Retrieve the latest user trades that have occurred for a specific instrument.
     *
     * @param  string $instrument_name Instrument name (required)
     * @param  int $start_seq The sequence number of the first trade to be returned (optional)
     * @param  int $end_seq The sequence number of the last trade to be returned (optional)
     * @param  int $count Number of requested items, default - &#x60;10&#x60; (optional)
     * @param  bool $include_old Include trades older than 7 days, default - &#x60;false&#x60; (optional)
     * @param  string $sorting Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) (optional)
     *
     * @throws \OpenAPI\Client\ApiException on non-2xx response
     * @throws \InvalidArgumentException
     * @return object
     */
    public function privateGetUserTradesByInstrumentGet($instrument_name, $start_seq = null, $end_seq = null, $count = null, $include_old = null, $sorting = null)
    {
        list($response) = $this->privateGetUserTradesByInstrumentGetWithHttpInfo($instrument_name, $start_seq, $end_seq, $count, $include_old, $sorting);
        return $response;
    }

    /**
     * Operation privateGetUserTradesByInstrumentGetWithHttpInfo
     *
     * Retrieve the latest user trades that have occurred for a specific instrument.
     *
     * @param  string $instrument_name Instrument name (required)
     * @param  int $start_seq The sequence number of the first trade to be returned (optional)
     * @param  int $end_seq The sequence number of the last trade to be returned (optional)
     * @param  int $count Number of requested items, default - &#x60;10&#x60; (optional)
     * @param  bool $include_old Include trades older than 7 days, default - &#x60;false&#x60; (optional)
     * @param  string $sorting Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) (optional)
     *
     * @throws \OpenAPI\Client\ApiException on non-2xx response
     * @throws \InvalidArgumentException
     * @return array of object, HTTP status code, HTTP response headers (array of strings)
     */
    public function privateGetUserTradesByInstrumentGetWithHttpInfo($instrument_name, $start_seq = null, $end_seq = null, $count = null, $include_old = null, $sorting = null)
    {
        $request = $this->privateGetUserTradesByInstrumentGetRequest($instrument_name, $start_seq, $end_seq, $count, $include_old, $sorting);

        try {
            $options = $this->createHttpClientOption();
            try {
                $response = $this->client->send($request, $options);
            } catch (RequestException $e) {
                throw new ApiException(
                    "[{$e->getCode()}] {$e->getMessage()}",
                    $e->getCode(),
                    $e->getResponse() ? $e->getResponse()->getHeaders() : null,
                    $e->getResponse() ? $e->getResponse()->getBody()->getContents() : null
                );
            }

            $statusCode = $response->getStatusCode();

            if ($statusCode < 200 || $statusCode > 299) {
                throw new ApiException(
                    sprintf(
                        '[%d] Error connecting to the API (%s)',
                        $statusCode,
                        $request->getUri()
                    ),
                    $statusCode,
                    $response->getHeaders(),
                    $response->getBody()
                );
            }

            $responseBody = $response->getBody();
            switch($statusCode) {
                case 200:
                    if ('object' === '\SplFileObject') {
                        $content = $responseBody; //stream goes to serializer
                    } else {
                        $content = $responseBody->getContents();
                    }

                    return [
                        ObjectSerializer::deserialize($content, 'object', []),
                        $response->getStatusCode(),
                        $response->getHeaders()
                    ];
            }

            $returnType = 'object';
            $responseBody = $response->getBody();
            if ($returnType === '\SplFileObject') {
                $content = $responseBody; //stream goes to serializer
            } else {
                $content = $responseBody->getContents();
            }

            return [
                ObjectSerializer::deserialize($content, $returnType, []),
                $response->getStatusCode(),
                $response->getHeaders()
            ];

        } catch (ApiException $e) {
            switch ($e->getCode()) {
                case 200:
                    $data = ObjectSerializer::deserialize(
                        $e->getResponseBody(),
                        'object',
                        $e->getResponseHeaders()
                    );
                    $e->setResponseObject($data);
                    break;
            }
            throw $e;
        }
    }

    /**
     * Operation privateGetUserTradesByInstrumentGetAsync
     *
     * Retrieve the latest user trades that have occurred for a specific instrument.
     *
     * @param  string $instrument_name Instrument name (required)
     * @param  int $start_seq The sequence number of the first trade to be returned (optional)
     * @param  int $end_seq The sequence number of the last trade to be returned (optional)
     * @param  int $count Number of requested items, default - &#x60;10&#x60; (optional)
     * @param  bool $include_old Include trades older than 7 days, default - &#x60;false&#x60; (optional)
     * @param  string $sorting Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) (optional)
     *
     * @throws \InvalidArgumentException
     * @return \GuzzleHttp\Promise\PromiseInterface
     */
    public function privateGetUserTradesByInstrumentGetAsync($instrument_name, $start_seq = null, $end_seq = null, $count = null, $include_old = null, $sorting = null)
    {
        return $this->privateGetUserTradesByInstrumentGetAsyncWithHttpInfo($instrument_name, $start_seq, $end_seq, $count, $include_old, $sorting)
            ->then(
                function ($response) {
                    return $response[0];
                }
            );
    }

    /**
     * Operation privateGetUserTradesByInstrumentGetAsyncWithHttpInfo
     *
     * Retrieve the latest user trades that have occurred for a specific instrument.
     *
     * @param  string $instrument_name Instrument name (required)
     * @param  int $start_seq The sequence number of the first trade to be returned (optional)
     * @param  int $end_seq The sequence number of the last trade to be returned (optional)
     * @param  int $count Number of requested items, default - &#x60;10&#x60; (optional)
     * @param  bool $include_old Include trades older than 7 days, default - &#x60;false&#x60; (optional)
     * @param  string $sorting Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) (optional)
     *
     * @throws \InvalidArgumentException
     * @return \GuzzleHttp\Promise\PromiseInterface
     */
    public function privateGetUserTradesByInstrumentGetAsyncWithHttpInfo($instrument_name, $start_seq = null, $end_seq = null, $count = null, $include_old = null, $sorting = null)
    {
        $returnType = 'object';
        $request = $this->privateGetUserTradesByInstrumentGetRequest($instrument_name, $start_seq, $end_seq, $count, $include_old, $sorting);

        return $this->client
            ->sendAsync($request, $this->createHttpClientOption())
            ->then(
                function ($response) use ($returnType) {
                    $responseBody = $response->getBody();
                    if ($returnType === '\SplFileObject') {
                        $content = $responseBody; //stream goes to serializer
                    } else {
                        $content = $responseBody->getContents();
                    }

                    return [
                        ObjectSerializer::deserialize($content, $returnType, []),
                        $response->getStatusCode(),
                        $response->getHeaders()
                    ];
                },
                function ($exception) {
                    $response = $exception->getResponse();
                    $statusCode = $response->getStatusCode();
                    throw new ApiException(
                        sprintf(
                            '[%d] Error connecting to the API (%s)',
                            $statusCode,
                            $exception->getRequest()->getUri()
                        ),
                        $statusCode,
                        $response->getHeaders(),
                        $response->getBody()
                    );
                }
            );
    }

    /**
     * Create request for operation 'privateGetUserTradesByInstrumentGet'
     *
     * @param  string $instrument_name Instrument name (required)
     * @param  int $start_seq The sequence number of the first trade to be returned (optional)
     * @param  int $end_seq The sequence number of the last trade to be returned (optional)
     * @param  int $count Number of requested items, default - &#x60;10&#x60; (optional)
     * @param  bool $include_old Include trades older than 7 days, default - &#x60;false&#x60; (optional)
     * @param  string $sorting Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) (optional)
     *
     * @throws \InvalidArgumentException
     * @return \GuzzleHttp\Psr7\Request
     */
    protected function privateGetUserTradesByInstrumentGetRequest($instrument_name, $start_seq = null, $end_seq = null, $count = null, $include_old = null, $sorting = null)
    {
        // verify the required parameter 'instrument_name' is set
        if ($instrument_name === null || (is_array($instrument_name) && count($instrument_name) === 0)) {
            throw new \InvalidArgumentException(
                'Missing the required parameter $instrument_name when calling privateGetUserTradesByInstrumentGet'
            );
        }

        $resourcePath = '/private/get_user_trades_by_instrument';
        $formParams = [];
        $queryParams = [];
        $headerParams = [];
        $httpBody = '';
        $multipart = false;

        // query params
        if ($instrument_name !== null) {
            $queryParams['instrument_name'] = ObjectSerializer::toQueryValue($instrument_name);
        }
        // query params
        if ($start_seq !== null) {
            $queryParams['start_seq'] = ObjectSerializer::toQueryValue($start_seq);
        }
        // query params
        if ($end_seq !== null) {
            $queryParams['end_seq'] = ObjectSerializer::toQueryValue($end_seq);
        }
        // query params
        if ($count !== null) {
            $queryParams['count'] = ObjectSerializer::toQueryValue($count);
        }
        // query params
        if ($include_old !== null) {
            $queryParams['include_old'] = ObjectSerializer::toQueryValue($include_old);
        }
        // query params
        if ($sorting !== null) {
            $queryParams['sorting'] = ObjectSerializer::toQueryValue($sorting);
        }


        // body params
        $_tempBody = null;

        if ($multipart) {
            $headers = $this->headerSelector->selectHeadersForMultipart(
                ['application/json']
            );
        } else {
            $headers = $this->headerSelector->selectHeaders(
                ['application/json'],
                []
            );
        }

        // for model (json/xml)
        if (isset($_tempBody)) {
            // $_tempBody is the method argument, if present
            if ($headers['Content-Type'] === 'application/json') {
                $httpBody = \GuzzleHttp\json_encode(ObjectSerializer::sanitizeForSerialization($_tempBody));
            } else {
                $httpBody = $_tempBody;
            }
        } elseif (count($formParams) > 0) {
            if ($multipart) {
                $multipartContents = [];
                foreach ($formParams as $formParamName => $formParamValue) {
                    $multipartContents[] = [
                        'name' => $formParamName,
                        'contents' => $formParamValue
                    ];
                }
                // for HTTP post (form)
                $httpBody = new MultipartStream($multipartContents);

            } elseif ($headers['Content-Type'] === 'application/json') {
                $httpBody = \GuzzleHttp\json_encode($formParams);

            } else {
                // for HTTP post (form)
                $httpBody = \GuzzleHttp\Psr7\build_query($formParams);
            }
        }

        // this endpoint requires Bearer (Auth. Token) authentication (access token)
        if ($this->config->getAccessToken() !== null) {
            $headers['Authorization'] = 'Bearer ' . $this->config->getAccessToken();
        }

        $defaultHeaders = [];
        if ($this->config->getUserAgent()) {
            $defaultHeaders['User-Agent'] = $this->config->getUserAgent();
        }

        $headers = array_merge(
            $defaultHeaders,
            $headerParams,
            $headers
        );

        $query = \GuzzleHttp\Psr7\build_query($queryParams);
        return new Request(
            'GET',
            $this->config->getHost() . $resourcePath . ($query ? "?{$query}" : ''),
            $headers,
            $httpBody
        );
    }

    /**
     * Operation privateGetUserTradesByOrderGet
     *
     * Retrieve the list of user trades that was created for given order
     *
     * @param  string $order_id The order id (required)
     * @param  string $sorting Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) (optional)
     *
     * @throws \OpenAPI\Client\ApiException on non-2xx response
     * @throws \InvalidArgumentException
     * @return object
     */
    public function privateGetUserTradesByOrderGet($order_id, $sorting = null)
    {
        list($response) = $this->privateGetUserTradesByOrderGetWithHttpInfo($order_id, $sorting);
        return $response;
    }

    /**
     * Operation privateGetUserTradesByOrderGetWithHttpInfo
     *
     * Retrieve the list of user trades that was created for given order
     *
     * @param  string $order_id The order id (required)
     * @param  string $sorting Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) (optional)
     *
     * @throws \OpenAPI\Client\ApiException on non-2xx response
     * @throws \InvalidArgumentException
     * @return array of object, HTTP status code, HTTP response headers (array of strings)
     */
    public function privateGetUserTradesByOrderGetWithHttpInfo($order_id, $sorting = null)
    {
        $request = $this->privateGetUserTradesByOrderGetRequest($order_id, $sorting);

        try {
            $options = $this->createHttpClientOption();
            try {
                $response = $this->client->send($request, $options);
            } catch (RequestException $e) {
                throw new ApiException(
                    "[{$e->getCode()}] {$e->getMessage()}",
                    $e->getCode(),
                    $e->getResponse() ? $e->getResponse()->getHeaders() : null,
                    $e->getResponse() ? $e->getResponse()->getBody()->getContents() : null
                );
            }

            $statusCode = $response->getStatusCode();

            if ($statusCode < 200 || $statusCode > 299) {
                throw new ApiException(
                    sprintf(
                        '[%d] Error connecting to the API (%s)',
                        $statusCode,
                        $request->getUri()
                    ),
                    $statusCode,
                    $response->getHeaders(),
                    $response->getBody()
                );
            }

            $responseBody = $response->getBody();
            switch($statusCode) {
                case 200:
                    if ('object' === '\SplFileObject') {
                        $content = $responseBody; //stream goes to serializer
                    } else {
                        $content = $responseBody->getContents();
                    }

                    return [
                        ObjectSerializer::deserialize($content, 'object', []),
                        $response->getStatusCode(),
                        $response->getHeaders()
                    ];
            }

            $returnType = 'object';
            $responseBody = $response->getBody();
            if ($returnType === '\SplFileObject') {
                $content = $responseBody; //stream goes to serializer
            } else {
                $content = $responseBody->getContents();
            }

            return [
                ObjectSerializer::deserialize($content, $returnType, []),
                $response->getStatusCode(),
                $response->getHeaders()
            ];

        } catch (ApiException $e) {
            switch ($e->getCode()) {
                case 200:
                    $data = ObjectSerializer::deserialize(
                        $e->getResponseBody(),
                        'object',
                        $e->getResponseHeaders()
                    );
                    $e->setResponseObject($data);
                    break;
            }
            throw $e;
        }
    }

    /**
     * Operation privateGetUserTradesByOrderGetAsync
     *
     * Retrieve the list of user trades that was created for given order
     *
     * @param  string $order_id The order id (required)
     * @param  string $sorting Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) (optional)
     *
     * @throws \InvalidArgumentException
     * @return \GuzzleHttp\Promise\PromiseInterface
     */
    public function privateGetUserTradesByOrderGetAsync($order_id, $sorting = null)
    {
        return $this->privateGetUserTradesByOrderGetAsyncWithHttpInfo($order_id, $sorting)
            ->then(
                function ($response) {
                    return $response[0];
                }
            );
    }

    /**
     * Operation privateGetUserTradesByOrderGetAsyncWithHttpInfo
     *
     * Retrieve the list of user trades that was created for given order
     *
     * @param  string $order_id The order id (required)
     * @param  string $sorting Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) (optional)
     *
     * @throws \InvalidArgumentException
     * @return \GuzzleHttp\Promise\PromiseInterface
     */
    public function privateGetUserTradesByOrderGetAsyncWithHttpInfo($order_id, $sorting = null)
    {
        $returnType = 'object';
        $request = $this->privateGetUserTradesByOrderGetRequest($order_id, $sorting);

        return $this->client
            ->sendAsync($request, $this->createHttpClientOption())
            ->then(
                function ($response) use ($returnType) {
                    $responseBody = $response->getBody();
                    if ($returnType === '\SplFileObject') {
                        $content = $responseBody; //stream goes to serializer
                    } else {
                        $content = $responseBody->getContents();
                    }

                    return [
                        ObjectSerializer::deserialize($content, $returnType, []),
                        $response->getStatusCode(),
                        $response->getHeaders()
                    ];
                },
                function ($exception) {
                    $response = $exception->getResponse();
                    $statusCode = $response->getStatusCode();
                    throw new ApiException(
                        sprintf(
                            '[%d] Error connecting to the API (%s)',
                            $statusCode,
                            $exception->getRequest()->getUri()
                        ),
                        $statusCode,
                        $response->getHeaders(),
                        $response->getBody()
                    );
                }
            );
    }

    /**
     * Create request for operation 'privateGetUserTradesByOrderGet'
     *
     * @param  string $order_id The order id (required)
     * @param  string $sorting Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) (optional)
     *
     * @throws \InvalidArgumentException
     * @return \GuzzleHttp\Psr7\Request
     */
    protected function privateGetUserTradesByOrderGetRequest($order_id, $sorting = null)
    {
        // verify the required parameter 'order_id' is set
        if ($order_id === null || (is_array($order_id) && count($order_id) === 0)) {
            throw new \InvalidArgumentException(
                'Missing the required parameter $order_id when calling privateGetUserTradesByOrderGet'
            );
        }

        $resourcePath = '/private/get_user_trades_by_order';
        $formParams = [];
        $queryParams = [];
        $headerParams = [];
        $httpBody = '';
        $multipart = false;

        // query params
        if ($order_id !== null) {
            $queryParams['order_id'] = ObjectSerializer::toQueryValue($order_id);
        }
        // query params
        if ($sorting !== null) {
            $queryParams['sorting'] = ObjectSerializer::toQueryValue($sorting);
        }


        // body params
        $_tempBody = null;

        if ($multipart) {
            $headers = $this->headerSelector->selectHeadersForMultipart(
                ['application/json']
            );
        } else {
            $headers = $this->headerSelector->selectHeaders(
                ['application/json'],
                []
            );
        }

        // for model (json/xml)
        if (isset($_tempBody)) {
            // $_tempBody is the method argument, if present
            if ($headers['Content-Type'] === 'application/json') {
                $httpBody = \GuzzleHttp\json_encode(ObjectSerializer::sanitizeForSerialization($_tempBody));
            } else {
                $httpBody = $_tempBody;
            }
        } elseif (count($formParams) > 0) {
            if ($multipart) {
                $multipartContents = [];
                foreach ($formParams as $formParamName => $formParamValue) {
                    $multipartContents[] = [
                        'name' => $formParamName,
                        'contents' => $formParamValue
                    ];
                }
                // for HTTP post (form)
                $httpBody = new MultipartStream($multipartContents);

            } elseif ($headers['Content-Type'] === 'application/json') {
                $httpBody = \GuzzleHttp\json_encode($formParams);

            } else {
                // for HTTP post (form)
                $httpBody = \GuzzleHttp\Psr7\build_query($formParams);
            }
        }

        // this endpoint requires Bearer (Auth. Token) authentication (access token)
        if ($this->config->getAccessToken() !== null) {
            $headers['Authorization'] = 'Bearer ' . $this->config->getAccessToken();
        }

        $defaultHeaders = [];
        if ($this->config->getUserAgent()) {
            $defaultHeaders['User-Agent'] = $this->config->getUserAgent();
        }

        $headers = array_merge(
            $defaultHeaders,
            $headerParams,
            $headers
        );

        $query = \GuzzleHttp\Psr7\build_query($queryParams);
        return new Request(
            'GET',
            $this->config->getHost() . $resourcePath . ($query ? "?{$query}" : ''),
            $headers,
            $httpBody
        );
    }

    /**
     * Operation privateSellGet
     *
     * Places a sell order for an instrument.
     *
     * @param  string $instrument_name Instrument name (required)
     * @param  float $amount It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH (required)
     * @param  string $type The order type, default: &#x60;\&quot;limit\&quot;&#x60; (optional)
     * @param  string $label user defined label for the order (maximum 32 characters) (optional)
     * @param  float $price &lt;p&gt;The order price in base currency (Only for limit and stop_limit orders)&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;usd, the field price should be the option price value in USD.&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;implv, the field price should be a value of implied volatility in percentages. For example,  price&#x3D;100, means implied volatility of 100%&lt;/p&gt; (optional)
     * @param  string $time_in_force &lt;p&gt;Specifies how long the order remains in effect. Default &#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; &lt;ul&gt; &lt;li&gt;&#x60;\&quot;good_til_cancelled\&quot;&#x60; - unfilled order remains in order book until cancelled&lt;/li&gt; &lt;li&gt;&#x60;\&quot;fill_or_kill\&quot;&#x60; - execute a transaction immediately and completely or not at all&lt;/li&gt; &lt;li&gt;&#x60;\&quot;immediate_or_cancel\&quot;&#x60; - execute a transaction immediately, and any portion of the order that cannot be immediately filled is cancelled&lt;/li&gt; &lt;/ul&gt; (optional, default to 'good_til_cancelled')
     * @param  float $max_show Maximum amount within an order to be shown to other customers, &#x60;0&#x60; for invisible order (optional, default to 1)
     * @param  bool $post_only &lt;p&gt;If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.&lt;/p&gt; &lt;p&gt;Only valid in combination with time_in_force&#x3D;&#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; (optional, default to true)
     * @param  bool $reduce_only If &#x60;true&#x60;, the order is considered reduce-only which is intended to only reduce a current position (optional, default to false)
     * @param  float $stop_price Stop price, required for stop limit orders (Only for stop orders) (optional)
     * @param  string $trigger Defines trigger type, required for &#x60;\&quot;stop_limit\&quot;&#x60; order type (optional)
     * @param  string $advanced Advanced option order type. (Only for options) (optional)
     *
     * @throws \OpenAPI\Client\ApiException on non-2xx response
     * @throws \InvalidArgumentException
     * @return object
     */
    public function privateSellGet($instrument_name, $amount, $type = null, $label = null, $price = null, $time_in_force = 'good_til_cancelled', $max_show = 1, $post_only = true, $reduce_only = false, $stop_price = null, $trigger = null, $advanced = null)
    {
        list($response) = $this->privateSellGetWithHttpInfo($instrument_name, $amount, $type, $label, $price, $time_in_force, $max_show, $post_only, $reduce_only, $stop_price, $trigger, $advanced);
        return $response;
    }

    /**
     * Operation privateSellGetWithHttpInfo
     *
     * Places a sell order for an instrument.
     *
     * @param  string $instrument_name Instrument name (required)
     * @param  float $amount It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH (required)
     * @param  string $type The order type, default: &#x60;\&quot;limit\&quot;&#x60; (optional)
     * @param  string $label user defined label for the order (maximum 32 characters) (optional)
     * @param  float $price &lt;p&gt;The order price in base currency (Only for limit and stop_limit orders)&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;usd, the field price should be the option price value in USD.&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;implv, the field price should be a value of implied volatility in percentages. For example,  price&#x3D;100, means implied volatility of 100%&lt;/p&gt; (optional)
     * @param  string $time_in_force &lt;p&gt;Specifies how long the order remains in effect. Default &#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; &lt;ul&gt; &lt;li&gt;&#x60;\&quot;good_til_cancelled\&quot;&#x60; - unfilled order remains in order book until cancelled&lt;/li&gt; &lt;li&gt;&#x60;\&quot;fill_or_kill\&quot;&#x60; - execute a transaction immediately and completely or not at all&lt;/li&gt; &lt;li&gt;&#x60;\&quot;immediate_or_cancel\&quot;&#x60; - execute a transaction immediately, and any portion of the order that cannot be immediately filled is cancelled&lt;/li&gt; &lt;/ul&gt; (optional, default to 'good_til_cancelled')
     * @param  float $max_show Maximum amount within an order to be shown to other customers, &#x60;0&#x60; for invisible order (optional, default to 1)
     * @param  bool $post_only &lt;p&gt;If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.&lt;/p&gt; &lt;p&gt;Only valid in combination with time_in_force&#x3D;&#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; (optional, default to true)
     * @param  bool $reduce_only If &#x60;true&#x60;, the order is considered reduce-only which is intended to only reduce a current position (optional, default to false)
     * @param  float $stop_price Stop price, required for stop limit orders (Only for stop orders) (optional)
     * @param  string $trigger Defines trigger type, required for &#x60;\&quot;stop_limit\&quot;&#x60; order type (optional)
     * @param  string $advanced Advanced option order type. (Only for options) (optional)
     *
     * @throws \OpenAPI\Client\ApiException on non-2xx response
     * @throws \InvalidArgumentException
     * @return array of object, HTTP status code, HTTP response headers (array of strings)
     */
    public function privateSellGetWithHttpInfo($instrument_name, $amount, $type = null, $label = null, $price = null, $time_in_force = 'good_til_cancelled', $max_show = 1, $post_only = true, $reduce_only = false, $stop_price = null, $trigger = null, $advanced = null)
    {
        $request = $this->privateSellGetRequest($instrument_name, $amount, $type, $label, $price, $time_in_force, $max_show, $post_only, $reduce_only, $stop_price, $trigger, $advanced);

        try {
            $options = $this->createHttpClientOption();
            try {
                $response = $this->client->send($request, $options);
            } catch (RequestException $e) {
                throw new ApiException(
                    "[{$e->getCode()}] {$e->getMessage()}",
                    $e->getCode(),
                    $e->getResponse() ? $e->getResponse()->getHeaders() : null,
                    $e->getResponse() ? $e->getResponse()->getBody()->getContents() : null
                );
            }

            $statusCode = $response->getStatusCode();

            if ($statusCode < 200 || $statusCode > 299) {
                throw new ApiException(
                    sprintf(
                        '[%d] Error connecting to the API (%s)',
                        $statusCode,
                        $request->getUri()
                    ),
                    $statusCode,
                    $response->getHeaders(),
                    $response->getBody()
                );
            }

            $responseBody = $response->getBody();
            switch($statusCode) {
                case 200:
                    if ('object' === '\SplFileObject') {
                        $content = $responseBody; //stream goes to serializer
                    } else {
                        $content = $responseBody->getContents();
                    }

                    return [
                        ObjectSerializer::deserialize($content, 'object', []),
                        $response->getStatusCode(),
                        $response->getHeaders()
                    ];
            }

            $returnType = 'object';
            $responseBody = $response->getBody();
            if ($returnType === '\SplFileObject') {
                $content = $responseBody; //stream goes to serializer
            } else {
                $content = $responseBody->getContents();
            }

            return [
                ObjectSerializer::deserialize($content, $returnType, []),
                $response->getStatusCode(),
                $response->getHeaders()
            ];

        } catch (ApiException $e) {
            switch ($e->getCode()) {
                case 200:
                    $data = ObjectSerializer::deserialize(
                        $e->getResponseBody(),
                        'object',
                        $e->getResponseHeaders()
                    );
                    $e->setResponseObject($data);
                    break;
            }
            throw $e;
        }
    }

    /**
     * Operation privateSellGetAsync
     *
     * Places a sell order for an instrument.
     *
     * @param  string $instrument_name Instrument name (required)
     * @param  float $amount It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH (required)
     * @param  string $type The order type, default: &#x60;\&quot;limit\&quot;&#x60; (optional)
     * @param  string $label user defined label for the order (maximum 32 characters) (optional)
     * @param  float $price &lt;p&gt;The order price in base currency (Only for limit and stop_limit orders)&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;usd, the field price should be the option price value in USD.&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;implv, the field price should be a value of implied volatility in percentages. For example,  price&#x3D;100, means implied volatility of 100%&lt;/p&gt; (optional)
     * @param  string $time_in_force &lt;p&gt;Specifies how long the order remains in effect. Default &#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; &lt;ul&gt; &lt;li&gt;&#x60;\&quot;good_til_cancelled\&quot;&#x60; - unfilled order remains in order book until cancelled&lt;/li&gt; &lt;li&gt;&#x60;\&quot;fill_or_kill\&quot;&#x60; - execute a transaction immediately and completely or not at all&lt;/li&gt; &lt;li&gt;&#x60;\&quot;immediate_or_cancel\&quot;&#x60; - execute a transaction immediately, and any portion of the order that cannot be immediately filled is cancelled&lt;/li&gt; &lt;/ul&gt; (optional, default to 'good_til_cancelled')
     * @param  float $max_show Maximum amount within an order to be shown to other customers, &#x60;0&#x60; for invisible order (optional, default to 1)
     * @param  bool $post_only &lt;p&gt;If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.&lt;/p&gt; &lt;p&gt;Only valid in combination with time_in_force&#x3D;&#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; (optional, default to true)
     * @param  bool $reduce_only If &#x60;true&#x60;, the order is considered reduce-only which is intended to only reduce a current position (optional, default to false)
     * @param  float $stop_price Stop price, required for stop limit orders (Only for stop orders) (optional)
     * @param  string $trigger Defines trigger type, required for &#x60;\&quot;stop_limit\&quot;&#x60; order type (optional)
     * @param  string $advanced Advanced option order type. (Only for options) (optional)
     *
     * @throws \InvalidArgumentException
     * @return \GuzzleHttp\Promise\PromiseInterface
     */
    public function privateSellGetAsync($instrument_name, $amount, $type = null, $label = null, $price = null, $time_in_force = 'good_til_cancelled', $max_show = 1, $post_only = true, $reduce_only = false, $stop_price = null, $trigger = null, $advanced = null)
    {
        return $this->privateSellGetAsyncWithHttpInfo($instrument_name, $amount, $type, $label, $price, $time_in_force, $max_show, $post_only, $reduce_only, $stop_price, $trigger, $advanced)
            ->then(
                function ($response) {
                    return $response[0];
                }
            );
    }

    /**
     * Operation privateSellGetAsyncWithHttpInfo
     *
     * Places a sell order for an instrument.
     *
     * @param  string $instrument_name Instrument name (required)
     * @param  float $amount It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH (required)
     * @param  string $type The order type, default: &#x60;\&quot;limit\&quot;&#x60; (optional)
     * @param  string $label user defined label for the order (maximum 32 characters) (optional)
     * @param  float $price &lt;p&gt;The order price in base currency (Only for limit and stop_limit orders)&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;usd, the field price should be the option price value in USD.&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;implv, the field price should be a value of implied volatility in percentages. For example,  price&#x3D;100, means implied volatility of 100%&lt;/p&gt; (optional)
     * @param  string $time_in_force &lt;p&gt;Specifies how long the order remains in effect. Default &#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; &lt;ul&gt; &lt;li&gt;&#x60;\&quot;good_til_cancelled\&quot;&#x60; - unfilled order remains in order book until cancelled&lt;/li&gt; &lt;li&gt;&#x60;\&quot;fill_or_kill\&quot;&#x60; - execute a transaction immediately and completely or not at all&lt;/li&gt; &lt;li&gt;&#x60;\&quot;immediate_or_cancel\&quot;&#x60; - execute a transaction immediately, and any portion of the order that cannot be immediately filled is cancelled&lt;/li&gt; &lt;/ul&gt; (optional, default to 'good_til_cancelled')
     * @param  float $max_show Maximum amount within an order to be shown to other customers, &#x60;0&#x60; for invisible order (optional, default to 1)
     * @param  bool $post_only &lt;p&gt;If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.&lt;/p&gt; &lt;p&gt;Only valid in combination with time_in_force&#x3D;&#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; (optional, default to true)
     * @param  bool $reduce_only If &#x60;true&#x60;, the order is considered reduce-only which is intended to only reduce a current position (optional, default to false)
     * @param  float $stop_price Stop price, required for stop limit orders (Only for stop orders) (optional)
     * @param  string $trigger Defines trigger type, required for &#x60;\&quot;stop_limit\&quot;&#x60; order type (optional)
     * @param  string $advanced Advanced option order type. (Only for options) (optional)
     *
     * @throws \InvalidArgumentException
     * @return \GuzzleHttp\Promise\PromiseInterface
     */
    public function privateSellGetAsyncWithHttpInfo($instrument_name, $amount, $type = null, $label = null, $price = null, $time_in_force = 'good_til_cancelled', $max_show = 1, $post_only = true, $reduce_only = false, $stop_price = null, $trigger = null, $advanced = null)
    {
        $returnType = 'object';
        $request = $this->privateSellGetRequest($instrument_name, $amount, $type, $label, $price, $time_in_force, $max_show, $post_only, $reduce_only, $stop_price, $trigger, $advanced);

        return $this->client
            ->sendAsync($request, $this->createHttpClientOption())
            ->then(
                function ($response) use ($returnType) {
                    $responseBody = $response->getBody();
                    if ($returnType === '\SplFileObject') {
                        $content = $responseBody; //stream goes to serializer
                    } else {
                        $content = $responseBody->getContents();
                    }

                    return [
                        ObjectSerializer::deserialize($content, $returnType, []),
                        $response->getStatusCode(),
                        $response->getHeaders()
                    ];
                },
                function ($exception) {
                    $response = $exception->getResponse();
                    $statusCode = $response->getStatusCode();
                    throw new ApiException(
                        sprintf(
                            '[%d] Error connecting to the API (%s)',
                            $statusCode,
                            $exception->getRequest()->getUri()
                        ),
                        $statusCode,
                        $response->getHeaders(),
                        $response->getBody()
                    );
                }
            );
    }

    /**
     * Create request for operation 'privateSellGet'
     *
     * @param  string $instrument_name Instrument name (required)
     * @param  float $amount It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH (required)
     * @param  string $type The order type, default: &#x60;\&quot;limit\&quot;&#x60; (optional)
     * @param  string $label user defined label for the order (maximum 32 characters) (optional)
     * @param  float $price &lt;p&gt;The order price in base currency (Only for limit and stop_limit orders)&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;usd, the field price should be the option price value in USD.&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;implv, the field price should be a value of implied volatility in percentages. For example,  price&#x3D;100, means implied volatility of 100%&lt;/p&gt; (optional)
     * @param  string $time_in_force &lt;p&gt;Specifies how long the order remains in effect. Default &#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; &lt;ul&gt; &lt;li&gt;&#x60;\&quot;good_til_cancelled\&quot;&#x60; - unfilled order remains in order book until cancelled&lt;/li&gt; &lt;li&gt;&#x60;\&quot;fill_or_kill\&quot;&#x60; - execute a transaction immediately and completely or not at all&lt;/li&gt; &lt;li&gt;&#x60;\&quot;immediate_or_cancel\&quot;&#x60; - execute a transaction immediately, and any portion of the order that cannot be immediately filled is cancelled&lt;/li&gt; &lt;/ul&gt; (optional, default to 'good_til_cancelled')
     * @param  float $max_show Maximum amount within an order to be shown to other customers, &#x60;0&#x60; for invisible order (optional, default to 1)
     * @param  bool $post_only &lt;p&gt;If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.&lt;/p&gt; &lt;p&gt;Only valid in combination with time_in_force&#x3D;&#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; (optional, default to true)
     * @param  bool $reduce_only If &#x60;true&#x60;, the order is considered reduce-only which is intended to only reduce a current position (optional, default to false)
     * @param  float $stop_price Stop price, required for stop limit orders (Only for stop orders) (optional)
     * @param  string $trigger Defines trigger type, required for &#x60;\&quot;stop_limit\&quot;&#x60; order type (optional)
     * @param  string $advanced Advanced option order type. (Only for options) (optional)
     *
     * @throws \InvalidArgumentException
     * @return \GuzzleHttp\Psr7\Request
     */
    protected function privateSellGetRequest($instrument_name, $amount, $type = null, $label = null, $price = null, $time_in_force = 'good_til_cancelled', $max_show = 1, $post_only = true, $reduce_only = false, $stop_price = null, $trigger = null, $advanced = null)
    {
        // verify the required parameter 'instrument_name' is set
        if ($instrument_name === null || (is_array($instrument_name) && count($instrument_name) === 0)) {
            throw new \InvalidArgumentException(
                'Missing the required parameter $instrument_name when calling privateSellGet'
            );
        }
        // verify the required parameter 'amount' is set
        if ($amount === null || (is_array($amount) && count($amount) === 0)) {
            throw new \InvalidArgumentException(
                'Missing the required parameter $amount when calling privateSellGet'
            );
        }

        $resourcePath = '/private/sell';
        $formParams = [];
        $queryParams = [];
        $headerParams = [];
        $httpBody = '';
        $multipart = false;

        // query params
        if ($instrument_name !== null) {
            $queryParams['instrument_name'] = ObjectSerializer::toQueryValue($instrument_name);
        }
        // query params
        if ($amount !== null) {
            $queryParams['amount'] = ObjectSerializer::toQueryValue($amount);
        }
        // query params
        if ($type !== null) {
            $queryParams['type'] = ObjectSerializer::toQueryValue($type);
        }
        // query params
        if ($label !== null) {
            $queryParams['label'] = ObjectSerializer::toQueryValue($label);
        }
        // query params
        if ($price !== null) {
            $queryParams['price'] = ObjectSerializer::toQueryValue($price);
        }
        // query params
        if ($time_in_force !== null) {
            $queryParams['time_in_force'] = ObjectSerializer::toQueryValue($time_in_force);
        }
        // query params
        if ($max_show !== null) {
            $queryParams['max_show'] = ObjectSerializer::toQueryValue($max_show);
        }
        // query params
        if ($post_only !== null) {
            $queryParams['post_only'] = ObjectSerializer::toQueryValue($post_only);
        }
        // query params
        if ($reduce_only !== null) {
            $queryParams['reduce_only'] = ObjectSerializer::toQueryValue($reduce_only);
        }
        // query params
        if ($stop_price !== null) {
            $queryParams['stop_price'] = ObjectSerializer::toQueryValue($stop_price);
        }
        // query params
        if ($trigger !== null) {
            $queryParams['trigger'] = ObjectSerializer::toQueryValue($trigger);
        }
        // query params
        if ($advanced !== null) {
            $queryParams['advanced'] = ObjectSerializer::toQueryValue($advanced);
        }


        // body params
        $_tempBody = null;

        if ($multipart) {
            $headers = $this->headerSelector->selectHeadersForMultipart(
                ['application/json']
            );
        } else {
            $headers = $this->headerSelector->selectHeaders(
                ['application/json'],
                []
            );
        }

        // for model (json/xml)
        if (isset($_tempBody)) {
            // $_tempBody is the method argument, if present
            if ($headers['Content-Type'] === 'application/json') {
                $httpBody = \GuzzleHttp\json_encode(ObjectSerializer::sanitizeForSerialization($_tempBody));
            } else {
                $httpBody = $_tempBody;
            }
        } elseif (count($formParams) > 0) {
            if ($multipart) {
                $multipartContents = [];
                foreach ($formParams as $formParamName => $formParamValue) {
                    $multipartContents[] = [
                        'name' => $formParamName,
                        'contents' => $formParamValue
                    ];
                }
                // for HTTP post (form)
                $httpBody = new MultipartStream($multipartContents);

            } elseif ($headers['Content-Type'] === 'application/json') {
                $httpBody = \GuzzleHttp\json_encode($formParams);

            } else {
                // for HTTP post (form)
                $httpBody = \GuzzleHttp\Psr7\build_query($formParams);
            }
        }

        // this endpoint requires Bearer (Auth. Token) authentication (access token)
        if ($this->config->getAccessToken() !== null) {
            $headers['Authorization'] = 'Bearer ' . $this->config->getAccessToken();
        }

        $defaultHeaders = [];
        if ($this->config->getUserAgent()) {
            $defaultHeaders['User-Agent'] = $this->config->getUserAgent();
        }

        $headers = array_merge(
            $defaultHeaders,
            $headerParams,
            $headers
        );

        $query = \GuzzleHttp\Psr7\build_query($queryParams);
        return new Request(
            'GET',
            $this->config->getHost() . $resourcePath . ($query ? "?{$query}" : ''),
            $headers,
            $httpBody
        );
    }

    /**
     * Create http client option
     *
     * @throws \RuntimeException on file opening failure
     * @return array of http client options
     */
    protected function createHttpClientOption()
    {
        $options = [];
        if ($this->config->getDebug()) {
            $options[RequestOptions::DEBUG] = fopen($this->config->getDebugFile(), 'a');
            if (!$options[RequestOptions::DEBUG]) {
                throw new \RuntimeException('Failed to open the debug file: ' . $this->config->getDebugFile());
            }
        }

        return $options;
    }
}
