/*
 * Deribit API
 * #Overview  Deribit provides three different interfaces to access the API:  * [JSON-RPC over Websocket](#json-rpc) * [JSON-RPC over HTTP](#json-rpc) * [FIX](#fix-api) (Financial Information eXchange)  With the API Console you can use and test the JSON-RPC API, both via HTTP and  via Websocket. To visit the API console, go to __Account > API tab >  API Console tab.__   ##Naming Deribit tradeable assets or instruments use the following system of naming:  |Kind|Examples|Template|Comments| |----|--------|--------|--------| |Future|<code>BTC-25MAR16</code>, <code>BTC-5AUG16</code>|<code>BTC-DMMMYY</code>|<code>BTC</code> is currency, <code>DMMMYY</code> is expiration date, <code>D</code> stands for day of month (1 or 2 digits), <code>MMM</code> - month (3 first letters in English), <code>YY</code> stands for year.| |Perpetual|<code>BTC-PERPETUAL</code>                        ||Perpetual contract for currency <code>BTC</code>.| |Option|<code>BTC-25MAR16-420-C</code>, <code>BTC-5AUG16-580-P</code>|<code>BTC-DMMMYY-STRIKE-K</code>|<code>STRIKE</code> is option strike price in USD. Template <code>K</code> is option kind: <code>C</code> for call options or <code>P</code> for put options.|   # JSON-RPC JSON-RPC is a light-weight remote procedure call (RPC) protocol. The  [JSON-RPC specification](https://www.jsonrpc.org/specification) defines the data structures that are used for the messages that are exchanged between client and server, as well as the rules around their processing. JSON-RPC uses JSON (RFC 4627) as data format.  JSON-RPC is transport agnostic: it does not specify which transport mechanism must be used. The Deribit API supports both Websocket (preferred) and HTTP (with limitations: subscriptions are not supported over HTTP).  ## Request messages > An example of a request message:  ```json {     \"jsonrpc\": \"2.0\",     \"id\": 8066,     \"method\": \"public/ticker\",     \"params\": {         \"instrument\": \"BTC-24AUG18-6500-P\"     } } ```  According to the JSON-RPC sepcification the requests must be JSON objects with the following fields.  |Name|Type|Description| |----|----|-----------| |jsonrpc|string|The version of the JSON-RPC spec: \"2.0\"| |id|integer or string|An identifier of the request. If it is included, then the response will contain the same identifier| |method|string|The method to be invoked| |params|object|The parameters values for the method. The field names must match with the expected parameter names. The parameters that are expected are described in the documentation for the methods, below.|  <aside class=\"warning\"> The JSON-RPC specification describes two features that are currently not supported by the API:  <ul> <li>Specification of parameter values by position</li> <li>Batch requests</li> </ul>  </aside>   ## Response messages > An example of a response message:  ```json {     \"jsonrpc\": \"2.0\",     \"id\": 5239,     \"testnet\": false,     \"result\": [         {             \"currency\": \"BTC\",             \"currencyLong\": \"Bitcoin\",             \"minConfirmation\": 2,             \"txFee\": 0.0006,             \"isActive\": true,             \"coinType\": \"BITCOIN\",             \"baseAddress\": null         }     ],     \"usIn\": 1535043730126248,     \"usOut\": 1535043730126250,     \"usDiff\": 2 } ```  The JSON-RPC API always responds with a JSON object with the following fields.   |Name|Type|Description| |----|----|-----------| |id|integer|This is the same id that was sent in the request.| |result|any|If successful, the result of the API call. The format for the result is described with each method.| |error|error object|Only present if there was an error invoking the method. The error object is described below.| |testnet|boolean|Indicates whether the API in use is actually the test API.  <code>false</code> for production server, <code>true</code> for test server.| |usIn|integer|The timestamp when the requests was received (microseconds since the Unix epoch)| |usOut|integer|The timestamp when the response was sent (microseconds since the Unix epoch)| |usDiff|integer|The number of microseconds that was spent handling the request|  <aside class=\"notice\"> The fields <code>testnet</code>, <code>usIn</code>, <code>usOut</code> and <code>usDiff</code> are not part of the JSON-RPC standard.  <p>In order not to clutter the examples they will generally be omitted from the example code.</p> </aside>  > An example of a response with an error:  ```json {     \"jsonrpc\": \"2.0\",     \"id\": 8163,     \"error\": {         \"code\": 11050,         \"message\": \"bad_request\"     },     \"testnet\": false,     \"usIn\": 1535037392434763,     \"usOut\": 1535037392448119,     \"usDiff\": 13356 } ``` In case of an error the response message will contain the error field, with as value an object with the following with the following fields:  |Name|Type|Description |----|----|-----------| |code|integer|A number that indicates the kind of error.| |message|string|A short description that indicates the kind of error.| |data|any|Additional data about the error. This field may be omitted.|  ## Notifications  > An example of a notification:  ```json {     \"jsonrpc\": \"2.0\",     \"method\": \"subscription\",     \"params\": {         \"channel\": \"deribit_price_index.btc_usd\",         \"data\": {             \"timestamp\": 1535098298227,             \"price\": 6521.17,             \"index_name\": \"btc_usd\"         }     } } ```  API users can subscribe to certain types of notifications. This means that they will receive JSON-RPC notification-messages from the server when certain events occur, such as changes to the index price or changes to the order book for a certain instrument.   The API methods [public/subscribe](#public-subscribe) and [private/subscribe](#private-subscribe) are used to set up a subscription. Since HTTP does not support the sending of messages from server to client, these methods are only availble when using the Websocket transport mechanism.  At the moment of subscription a \"channel\" must be specified. The channel determines the type of events that will be received.  See [Subscriptions](#subscriptions) for more details about the channels.  In accordance with the JSON-RPC specification, the format of a notification  is that of a request message without an <code>id</code> field. The value of the <code>method</code> field will always be <code>\"subscription\"</code>. The <code>params</code> field will always be an object with 2 members: <code>channel</code> and <code>data</code>. The value of the <code>channel</code> member is the name of the channel (a string). The value of the <code>data</code> member is an object that contains data  that is specific for the channel.   ## Authentication  > An example of a JSON request with token:  ```json {     \"id\": 5647,     \"method\": \"private/get_subaccounts\",     \"params\": {         \"access_token\": \"67SVutDoVZSzkUStHSuk51WntMNBJ5mh5DYZhwzpiqDF\"     } } ```  The API consists of `public` and `private` methods. The public methods do not require authentication. The private methods use OAuth 2.0 authentication. This means that a valid OAuth access token must be included in the request, which can get achived by calling method [public/auth](#public-auth).  When the token was assigned to the user, it should be passed along, with other request parameters, back to the server:  |Connection type|Access token placement |----|-----------| |**Websocket**|Inside request JSON parameters, as an `access_token` field| |**HTTP (REST)**|Header `Authorization: bearer ```Token``` ` value|  ### Additional authorization method - basic user credentials  <span style=\"color:red\"><b> ! Not recommended - however, it could be useful for quick testing API</b></span></br>  Every `private` method could be accessed by providing, inside HTTP `Authorization: Basic XXX` header, values with user `ClientId` and assigned `ClientSecret` (both values can be found on the API page on the Deribit website) encoded with `Base64`:  <code>Authorization: Basic BASE64(`ClientId` + `:` + `ClientSecret`)</code>   ### Additional authorization method - Deribit signature credentials  The Derbit service provides dedicated authorization method, which harness user generated signature to increase security level for passing request data. Generated value is passed inside `Authorization` header, coded as:  <code>Authorization: deri-hmac-sha256 id=```ClientId```,ts=```Timestamp```,sig=```Signature```,nonce=```Nonce```</code>  where:  |Deribit credential|Description |----|-----------| |*ClientId*|Can be found on the API page on the Deribit website| |*Timestamp*|Time when the request was generated - given as **miliseconds**. It's valid for **60 seconds** since generation, after that time any request with an old timestamp will be rejected.| |*Signature*|Value for signature calculated as described below | |*Nonce*|Single usage, user generated initialization vector for the server token|  The signature is generated by the following formula:  <code> Signature = HEX_STRING( HMAC-SHA256( ClientSecret, StringToSign ) );</code></br>  <code> StringToSign =  Timestamp + \"\\n\" + Nonce + \"\\n\" + RequestData;</code></br>  <code> RequestData =  UPPERCASE(HTTP_METHOD())  + \"\\n\" + URI() + \"\\n\" + RequestBody + \"\\n\";</code></br>   e.g. (using shell with ```openssl``` tool):  <code>&nbsp;&nbsp;&nbsp;&nbsp;ClientId=AAAAAAAAAAA</code></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;ClientSecret=ABCD</code></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;Timestamp=$( date +%s000 )</code></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;Nonce=$( cat /dev/urandom | tr -dc 'a-z0-9' | head -c8 )</code></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;URI=\"/api/v2/private/get_account_summary?currency=BTC\"</code></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;HttpMethod=GET</code></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;Body=\"\"</code></br></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;Signature=$( echo -ne \"${Timestamp}\\n${Nonce}\\n${HttpMethod}\\n${URI}\\n${Body}\\n\" | openssl sha256 -r -hmac \"$ClientSecret\" | cut -f1 -d' ' )</code></br></br> <code>&nbsp;&nbsp;&nbsp;&nbsp;echo $Signature</code></br></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;shell output> ea40d5e5e4fae235ab22b61da98121fbf4acdc06db03d632e23c66bcccb90d2c  (**WARNING**: Exact value depends on current timestamp and client credentials</code></br></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;curl -s -X ${HttpMethod} -H \"Authorization: deri-hmac-sha256 id=${ClientId},ts=${Timestamp},nonce=${Nonce},sig=${Signature}\" \"https://www.deribit.com${URI}\"</code></br></br>    ### Additional authorization method - signature credentials (WebSocket API)  When connecting through Websocket, user can request for authorization using ```client_credential``` method, which requires providing following parameters (as a part of JSON request):  |JSON parameter|Description |----|-----------| |*grant_type*|Must be **client_signature**| |*client_id*|Can be found on the API page on the Deribit website| |*timestamp*|Time when the request was generated - given as **miliseconds**. It's valid for **60 seconds** since generation, after that time any request with an old timestamp will be rejected.| |*signature*|Value for signature calculated as described below | |*nonce*|Single usage, user generated initialization vector for the server token| |*data*|**Optional** field, which contains any user specific value|  The signature is generated by the following formula:  <code> StringToSign =  Timestamp + \"\\n\" + Nonce + \"\\n\" + Data;</code></br>  <code> Signature = HEX_STRING( HMAC-SHA256( ClientSecret, StringToSign ) );</code></br>   e.g. (using shell with ```openssl``` tool):  <code>&nbsp;&nbsp;&nbsp;&nbsp;ClientId=AAAAAAAAAAA</code></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;ClientSecret=ABCD</code></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;Timestamp=$( date +%s000 ) # e.g. 1554883365000 </code></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;Nonce=$( cat /dev/urandom | tr -dc 'a-z0-9' | head -c8 ) # e.g. fdbmmz79 </code></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;Data=\"\"</code></br></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;Signature=$( echo -ne \"${Timestamp}\\n${Nonce}\\n${Data}\\n\" | openssl sha256 -r -hmac \"$ClientSecret\" | cut -f1 -d' ' )</code></br></br> <code>&nbsp;&nbsp;&nbsp;&nbsp;echo $Signature</code></br></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;shell output> e20c9cd5639d41f8bbc88f4d699c4baf94a4f0ee320e9a116b72743c449eb994  (**WARNING**: Exact value depends on current timestamp and client credentials</code></br></br>   You can also check the signature value using some online tools like, e.g: [https://codebeautify.org/hmac-generator](https://codebeautify.org/hmac-generator) (but don't forget about adding *newline* after each part of the hashed text and remember that you **should use** it only with your **test credentials**).   Here's a sample JSON request created using the values from the example above:  <code> {                            </br> &nbsp;&nbsp;\"jsonrpc\" : \"2.0\",         </br> &nbsp;&nbsp;\"id\" : 9929,               </br> &nbsp;&nbsp;\"method\" : \"public/auth\",  </br> &nbsp;&nbsp;\"params\" :                 </br> &nbsp;&nbsp;{                        </br> &nbsp;&nbsp;&nbsp;&nbsp;\"grant_type\" : \"client_signature\",   </br> &nbsp;&nbsp;&nbsp;&nbsp;\"client_id\" : \"AAAAAAAAAAA\",         </br> &nbsp;&nbsp;&nbsp;&nbsp;\"timestamp\": \"1554883365000\",        </br> &nbsp;&nbsp;&nbsp;&nbsp;\"nonce\": \"fdbmmz79\",                 </br> &nbsp;&nbsp;&nbsp;&nbsp;\"data\": \"\",                          </br> &nbsp;&nbsp;&nbsp;&nbsp;\"signature\" : \"e20c9cd5639d41f8bbc88f4d699c4baf94a4f0ee320e9a116b72743c449eb994\"  </br> &nbsp;&nbsp;}                        </br> }                            </br> </code>   ### Access scope  When asking for `access token` user can provide the required access level (called `scope`) which defines what type of functionality he/she wants to use, and whether requests are only going to check for some data or also to update them.  Scopes are required and checked for `private` methods, so if you plan to use only `public` information you can stay with values assigned by default.  |Scope|Description |----|-----------| |*account:read*|Access to **account** methods - read only data| |*account:read_write*|Access to **account** methods - allows to manage account settings, add subaccounts, etc.| |*trade:read*|Access to **trade** methods - read only data| |*trade:read_write*|Access to **trade** methods - required to create and modify orders| |*wallet:read*|Access to **wallet** methods - read only data| |*wallet:read_write*|Access to **wallet** methods - allows to withdraw, generate new deposit address, etc.| |*wallet:none*, *account:none*, *trade:none*|Blocked access to specified functionality|    <span style=\"color:red\">**NOTICE:**</span> Depending on choosing an authentication method (```grant type```) some scopes could be narrowed by the server. e.g. when ```grant_type = client_credentials``` and ```scope = wallet:read_write``` it's modified by the server as ```scope = wallet:read```\"   ## JSON-RPC over websocket Websocket is the prefered transport mechanism for the JSON-RPC API, because it is faster and because it can support [subscriptions](#subscriptions) and [cancel on disconnect](#private-enable_cancel_on_disconnect). The code examples that can be found next to each of the methods show how websockets can be used from Python or Javascript/node.js.  ## JSON-RPC over HTTP Besides websockets it is also possible to use the API via HTTP. The code examples for 'shell' show how this can be done using curl. Note that subscriptions and cancel on disconnect are not supported via HTTP.  #Methods 
 *
 * The version of the OpenAPI document: 2.0.0
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package org.openapitools.client.api;

import org.openapitools.client.ApiCallback;
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.ApiResponse;
import org.openapitools.client.Configuration;
import org.openapitools.client.Pair;
import org.openapitools.client.ProgressRequestBody;
import org.openapitools.client.ProgressResponseBody;

import com.google.gson.reflect.TypeToken;

import java.io.IOException;


import java.math.BigDecimal;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TradingApi {
    private ApiClient localVarApiClient;

    public TradingApi() {
        this(Configuration.getDefaultApiClient());
    }

    public TradingApi(ApiClient apiClient) {
        this.localVarApiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return localVarApiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.localVarApiClient = apiClient;
    }

    /**
     * Build call for privateBuyGet
     * @param instrumentName Instrument name (required)
     * @param amount It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH (required)
     * @param type The order type, default: &#x60;\&quot;limit\&quot;&#x60; (optional)
     * @param label user defined label for the order (maximum 32 characters) (optional)
     * @param price &lt;p&gt;The order price in base currency (Only for limit and stop_limit orders)&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;usd, the field price should be the option price value in USD.&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;implv, the field price should be a value of implied volatility in percentages. For example,  price&#x3D;100, means implied volatility of 100%&lt;/p&gt; (optional)
     * @param timeInForce &lt;p&gt;Specifies how long the order remains in effect. Default &#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; &lt;ul&gt; &lt;li&gt;&#x60;\&quot;good_til_cancelled\&quot;&#x60; - unfilled order remains in order book until cancelled&lt;/li&gt; &lt;li&gt;&#x60;\&quot;fill_or_kill\&quot;&#x60; - execute a transaction immediately and completely or not at all&lt;/li&gt; &lt;li&gt;&#x60;\&quot;immediate_or_cancel\&quot;&#x60; - execute a transaction immediately, and any portion of the order that cannot be immediately filled is cancelled&lt;/li&gt; &lt;/ul&gt; (optional, default to good_til_cancelled)
     * @param maxShow Maximum amount within an order to be shown to other customers, &#x60;0&#x60; for invisible order (optional, default to 1d)
     * @param postOnly &lt;p&gt;If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.&lt;/p&gt; &lt;p&gt;Only valid in combination with time_in_force&#x3D;&#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; (optional, default to true)
     * @param reduceOnly If &#x60;true&#x60;, the order is considered reduce-only which is intended to only reduce a current position (optional, default to false)
     * @param stopPrice Stop price, required for stop limit orders (Only for stop orders) (optional)
     * @param trigger Defines trigger type, required for &#x60;\&quot;stop_limit\&quot;&#x60; order type (optional)
     * @param advanced Advanced option order type. (Only for options) (optional)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> ok response </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call privateBuyGetCall(String instrumentName, BigDecimal amount, String type, String label, BigDecimal price, String timeInForce, BigDecimal maxShow, Boolean postOnly, Boolean reduceOnly, BigDecimal stopPrice, String trigger, String advanced, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = new Object();

        // create path and map variables
        String localVarPath = "/private/buy";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        if (instrumentName != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("instrument_name", instrumentName));
        }

        if (amount != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("amount", amount));
        }

        if (type != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("type", type));
        }

        if (label != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("label", label));
        }

        if (price != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("price", price));
        }

        if (timeInForce != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("time_in_force", timeInForce));
        }

        if (maxShow != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("max_show", maxShow));
        }

        if (postOnly != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("post_only", postOnly));
        }

        if (reduceOnly != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("reduce_only", reduceOnly));
        }

        if (stopPrice != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("stop_price", stopPrice));
        }

        if (trigger != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("trigger", trigger));
        }

        if (advanced != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("advanced", advanced));
        }

        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();
        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
            
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        String[] localVarAuthNames = new String[] { "bearerAuth" };
        return localVarApiClient.buildCall(localVarPath, "GET", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call privateBuyGetValidateBeforeCall(String instrumentName, BigDecimal amount, String type, String label, BigDecimal price, String timeInForce, BigDecimal maxShow, Boolean postOnly, Boolean reduceOnly, BigDecimal stopPrice, String trigger, String advanced, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'instrumentName' is set
        if (instrumentName == null) {
            throw new ApiException("Missing the required parameter 'instrumentName' when calling privateBuyGet(Async)");
        }
        
        // verify the required parameter 'amount' is set
        if (amount == null) {
            throw new ApiException("Missing the required parameter 'amount' when calling privateBuyGet(Async)");
        }
        

        okhttp3.Call localVarCall = privateBuyGetCall(instrumentName, amount, type, label, price, timeInForce, maxShow, postOnly, reduceOnly, stopPrice, trigger, advanced, _callback);
        return localVarCall;

    }

    /**
     * Places a buy order for an instrument.
     * 
     * @param instrumentName Instrument name (required)
     * @param amount It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH (required)
     * @param type The order type, default: &#x60;\&quot;limit\&quot;&#x60; (optional)
     * @param label user defined label for the order (maximum 32 characters) (optional)
     * @param price &lt;p&gt;The order price in base currency (Only for limit and stop_limit orders)&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;usd, the field price should be the option price value in USD.&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;implv, the field price should be a value of implied volatility in percentages. For example,  price&#x3D;100, means implied volatility of 100%&lt;/p&gt; (optional)
     * @param timeInForce &lt;p&gt;Specifies how long the order remains in effect. Default &#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; &lt;ul&gt; &lt;li&gt;&#x60;\&quot;good_til_cancelled\&quot;&#x60; - unfilled order remains in order book until cancelled&lt;/li&gt; &lt;li&gt;&#x60;\&quot;fill_or_kill\&quot;&#x60; - execute a transaction immediately and completely or not at all&lt;/li&gt; &lt;li&gt;&#x60;\&quot;immediate_or_cancel\&quot;&#x60; - execute a transaction immediately, and any portion of the order that cannot be immediately filled is cancelled&lt;/li&gt; &lt;/ul&gt; (optional, default to good_til_cancelled)
     * @param maxShow Maximum amount within an order to be shown to other customers, &#x60;0&#x60; for invisible order (optional, default to 1d)
     * @param postOnly &lt;p&gt;If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.&lt;/p&gt; &lt;p&gt;Only valid in combination with time_in_force&#x3D;&#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; (optional, default to true)
     * @param reduceOnly If &#x60;true&#x60;, the order is considered reduce-only which is intended to only reduce a current position (optional, default to false)
     * @param stopPrice Stop price, required for stop limit orders (Only for stop orders) (optional)
     * @param trigger Defines trigger type, required for &#x60;\&quot;stop_limit\&quot;&#x60; order type (optional)
     * @param advanced Advanced option order type. (Only for options) (optional)
     * @return Object
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> ok response </td><td>  -  </td></tr>
     </table>
     */
    public Object privateBuyGet(String instrumentName, BigDecimal amount, String type, String label, BigDecimal price, String timeInForce, BigDecimal maxShow, Boolean postOnly, Boolean reduceOnly, BigDecimal stopPrice, String trigger, String advanced) throws ApiException {
        ApiResponse<Object> localVarResp = privateBuyGetWithHttpInfo(instrumentName, amount, type, label, price, timeInForce, maxShow, postOnly, reduceOnly, stopPrice, trigger, advanced);
        return localVarResp.getData();
    }

    /**
     * Places a buy order for an instrument.
     * 
     * @param instrumentName Instrument name (required)
     * @param amount It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH (required)
     * @param type The order type, default: &#x60;\&quot;limit\&quot;&#x60; (optional)
     * @param label user defined label for the order (maximum 32 characters) (optional)
     * @param price &lt;p&gt;The order price in base currency (Only for limit and stop_limit orders)&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;usd, the field price should be the option price value in USD.&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;implv, the field price should be a value of implied volatility in percentages. For example,  price&#x3D;100, means implied volatility of 100%&lt;/p&gt; (optional)
     * @param timeInForce &lt;p&gt;Specifies how long the order remains in effect. Default &#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; &lt;ul&gt; &lt;li&gt;&#x60;\&quot;good_til_cancelled\&quot;&#x60; - unfilled order remains in order book until cancelled&lt;/li&gt; &lt;li&gt;&#x60;\&quot;fill_or_kill\&quot;&#x60; - execute a transaction immediately and completely or not at all&lt;/li&gt; &lt;li&gt;&#x60;\&quot;immediate_or_cancel\&quot;&#x60; - execute a transaction immediately, and any portion of the order that cannot be immediately filled is cancelled&lt;/li&gt; &lt;/ul&gt; (optional, default to good_til_cancelled)
     * @param maxShow Maximum amount within an order to be shown to other customers, &#x60;0&#x60; for invisible order (optional, default to 1d)
     * @param postOnly &lt;p&gt;If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.&lt;/p&gt; &lt;p&gt;Only valid in combination with time_in_force&#x3D;&#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; (optional, default to true)
     * @param reduceOnly If &#x60;true&#x60;, the order is considered reduce-only which is intended to only reduce a current position (optional, default to false)
     * @param stopPrice Stop price, required for stop limit orders (Only for stop orders) (optional)
     * @param trigger Defines trigger type, required for &#x60;\&quot;stop_limit\&quot;&#x60; order type (optional)
     * @param advanced Advanced option order type. (Only for options) (optional)
     * @return ApiResponse&lt;Object&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> ok response </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<Object> privateBuyGetWithHttpInfo(String instrumentName, BigDecimal amount, String type, String label, BigDecimal price, String timeInForce, BigDecimal maxShow, Boolean postOnly, Boolean reduceOnly, BigDecimal stopPrice, String trigger, String advanced) throws ApiException {
        okhttp3.Call localVarCall = privateBuyGetValidateBeforeCall(instrumentName, amount, type, label, price, timeInForce, maxShow, postOnly, reduceOnly, stopPrice, trigger, advanced, null);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Places a buy order for an instrument. (asynchronously)
     * 
     * @param instrumentName Instrument name (required)
     * @param amount It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH (required)
     * @param type The order type, default: &#x60;\&quot;limit\&quot;&#x60; (optional)
     * @param label user defined label for the order (maximum 32 characters) (optional)
     * @param price &lt;p&gt;The order price in base currency (Only for limit and stop_limit orders)&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;usd, the field price should be the option price value in USD.&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;implv, the field price should be a value of implied volatility in percentages. For example,  price&#x3D;100, means implied volatility of 100%&lt;/p&gt; (optional)
     * @param timeInForce &lt;p&gt;Specifies how long the order remains in effect. Default &#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; &lt;ul&gt; &lt;li&gt;&#x60;\&quot;good_til_cancelled\&quot;&#x60; - unfilled order remains in order book until cancelled&lt;/li&gt; &lt;li&gt;&#x60;\&quot;fill_or_kill\&quot;&#x60; - execute a transaction immediately and completely or not at all&lt;/li&gt; &lt;li&gt;&#x60;\&quot;immediate_or_cancel\&quot;&#x60; - execute a transaction immediately, and any portion of the order that cannot be immediately filled is cancelled&lt;/li&gt; &lt;/ul&gt; (optional, default to good_til_cancelled)
     * @param maxShow Maximum amount within an order to be shown to other customers, &#x60;0&#x60; for invisible order (optional, default to 1d)
     * @param postOnly &lt;p&gt;If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.&lt;/p&gt; &lt;p&gt;Only valid in combination with time_in_force&#x3D;&#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; (optional, default to true)
     * @param reduceOnly If &#x60;true&#x60;, the order is considered reduce-only which is intended to only reduce a current position (optional, default to false)
     * @param stopPrice Stop price, required for stop limit orders (Only for stop orders) (optional)
     * @param trigger Defines trigger type, required for &#x60;\&quot;stop_limit\&quot;&#x60; order type (optional)
     * @param advanced Advanced option order type. (Only for options) (optional)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> ok response </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call privateBuyGetAsync(String instrumentName, BigDecimal amount, String type, String label, BigDecimal price, String timeInForce, BigDecimal maxShow, Boolean postOnly, Boolean reduceOnly, BigDecimal stopPrice, String trigger, String advanced, final ApiCallback<Object> _callback) throws ApiException {

        okhttp3.Call localVarCall = privateBuyGetValidateBeforeCall(instrumentName, amount, type, label, price, timeInForce, maxShow, postOnly, reduceOnly, stopPrice, trigger, advanced, _callback);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for privateCancelAllByCurrencyGet
     * @param currency The currency symbol (required)
     * @param kind Instrument kind, if not provided instruments of all kinds are considered (optional)
     * @param type Order type - limit, stop or all, default - &#x60;all&#x60; (optional)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call privateCancelAllByCurrencyGetCall(String currency, String kind, String type, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = new Object();

        // create path and map variables
        String localVarPath = "/private/cancel_all_by_currency";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        if (currency != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("currency", currency));
        }

        if (kind != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("kind", kind));
        }

        if (type != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("type", type));
        }

        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();
        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
            
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        String[] localVarAuthNames = new String[] { "bearerAuth" };
        return localVarApiClient.buildCall(localVarPath, "GET", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call privateCancelAllByCurrencyGetValidateBeforeCall(String currency, String kind, String type, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'currency' is set
        if (currency == null) {
            throw new ApiException("Missing the required parameter 'currency' when calling privateCancelAllByCurrencyGet(Async)");
        }
        

        okhttp3.Call localVarCall = privateCancelAllByCurrencyGetCall(currency, kind, type, _callback);
        return localVarCall;

    }

    /**
     * Cancels all orders by currency, optionally filtered by instrument kind and/or order type.
     * 
     * @param currency The currency symbol (required)
     * @param kind Instrument kind, if not provided instruments of all kinds are considered (optional)
     * @param type Order type - limit, stop or all, default - &#x60;all&#x60; (optional)
     * @return Object
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public Object privateCancelAllByCurrencyGet(String currency, String kind, String type) throws ApiException {
        ApiResponse<Object> localVarResp = privateCancelAllByCurrencyGetWithHttpInfo(currency, kind, type);
        return localVarResp.getData();
    }

    /**
     * Cancels all orders by currency, optionally filtered by instrument kind and/or order type.
     * 
     * @param currency The currency symbol (required)
     * @param kind Instrument kind, if not provided instruments of all kinds are considered (optional)
     * @param type Order type - limit, stop or all, default - &#x60;all&#x60; (optional)
     * @return ApiResponse&lt;Object&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<Object> privateCancelAllByCurrencyGetWithHttpInfo(String currency, String kind, String type) throws ApiException {
        okhttp3.Call localVarCall = privateCancelAllByCurrencyGetValidateBeforeCall(currency, kind, type, null);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Cancels all orders by currency, optionally filtered by instrument kind and/or order type. (asynchronously)
     * 
     * @param currency The currency symbol (required)
     * @param kind Instrument kind, if not provided instruments of all kinds are considered (optional)
     * @param type Order type - limit, stop or all, default - &#x60;all&#x60; (optional)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call privateCancelAllByCurrencyGetAsync(String currency, String kind, String type, final ApiCallback<Object> _callback) throws ApiException {

        okhttp3.Call localVarCall = privateCancelAllByCurrencyGetValidateBeforeCall(currency, kind, type, _callback);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for privateCancelAllByInstrumentGet
     * @param instrumentName Instrument name (required)
     * @param type Order type - limit, stop or all, default - &#x60;all&#x60; (optional)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call privateCancelAllByInstrumentGetCall(String instrumentName, String type, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = new Object();

        // create path and map variables
        String localVarPath = "/private/cancel_all_by_instrument";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        if (instrumentName != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("instrument_name", instrumentName));
        }

        if (type != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("type", type));
        }

        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();
        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
            
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        String[] localVarAuthNames = new String[] { "bearerAuth" };
        return localVarApiClient.buildCall(localVarPath, "GET", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call privateCancelAllByInstrumentGetValidateBeforeCall(String instrumentName, String type, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'instrumentName' is set
        if (instrumentName == null) {
            throw new ApiException("Missing the required parameter 'instrumentName' when calling privateCancelAllByInstrumentGet(Async)");
        }
        

        okhttp3.Call localVarCall = privateCancelAllByInstrumentGetCall(instrumentName, type, _callback);
        return localVarCall;

    }

    /**
     * Cancels all orders by instrument, optionally filtered by order type.
     * 
     * @param instrumentName Instrument name (required)
     * @param type Order type - limit, stop or all, default - &#x60;all&#x60; (optional)
     * @return Object
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public Object privateCancelAllByInstrumentGet(String instrumentName, String type) throws ApiException {
        ApiResponse<Object> localVarResp = privateCancelAllByInstrumentGetWithHttpInfo(instrumentName, type);
        return localVarResp.getData();
    }

    /**
     * Cancels all orders by instrument, optionally filtered by order type.
     * 
     * @param instrumentName Instrument name (required)
     * @param type Order type - limit, stop or all, default - &#x60;all&#x60; (optional)
     * @return ApiResponse&lt;Object&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<Object> privateCancelAllByInstrumentGetWithHttpInfo(String instrumentName, String type) throws ApiException {
        okhttp3.Call localVarCall = privateCancelAllByInstrumentGetValidateBeforeCall(instrumentName, type, null);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Cancels all orders by instrument, optionally filtered by order type. (asynchronously)
     * 
     * @param instrumentName Instrument name (required)
     * @param type Order type - limit, stop or all, default - &#x60;all&#x60; (optional)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call privateCancelAllByInstrumentGetAsync(String instrumentName, String type, final ApiCallback<Object> _callback) throws ApiException {

        okhttp3.Call localVarCall = privateCancelAllByInstrumentGetValidateBeforeCall(instrumentName, type, _callback);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for privateCancelAllGet
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call privateCancelAllGetCall(final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = new Object();

        // create path and map variables
        String localVarPath = "/private/cancel_all";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();
        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
            
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        String[] localVarAuthNames = new String[] { "bearerAuth" };
        return localVarApiClient.buildCall(localVarPath, "GET", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call privateCancelAllGetValidateBeforeCall(final ApiCallback _callback) throws ApiException {
        

        okhttp3.Call localVarCall = privateCancelAllGetCall(_callback);
        return localVarCall;

    }

    /**
     * This method cancels all users orders and stop orders within all currencies and instrument kinds.
     * 
     * @return Object
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public Object privateCancelAllGet() throws ApiException {
        ApiResponse<Object> localVarResp = privateCancelAllGetWithHttpInfo();
        return localVarResp.getData();
    }

    /**
     * This method cancels all users orders and stop orders within all currencies and instrument kinds.
     * 
     * @return ApiResponse&lt;Object&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<Object> privateCancelAllGetWithHttpInfo() throws ApiException {
        okhttp3.Call localVarCall = privateCancelAllGetValidateBeforeCall(null);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * This method cancels all users orders and stop orders within all currencies and instrument kinds. (asynchronously)
     * 
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call privateCancelAllGetAsync(final ApiCallback<Object> _callback) throws ApiException {

        okhttp3.Call localVarCall = privateCancelAllGetValidateBeforeCall(_callback);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for privateCancelGet
     * @param orderId The order id (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call privateCancelGetCall(String orderId, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = new Object();

        // create path and map variables
        String localVarPath = "/private/cancel";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        if (orderId != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("order_id", orderId));
        }

        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();
        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
            
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        String[] localVarAuthNames = new String[] { "bearerAuth" };
        return localVarApiClient.buildCall(localVarPath, "GET", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call privateCancelGetValidateBeforeCall(String orderId, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'orderId' is set
        if (orderId == null) {
            throw new ApiException("Missing the required parameter 'orderId' when calling privateCancelGet(Async)");
        }
        

        okhttp3.Call localVarCall = privateCancelGetCall(orderId, _callback);
        return localVarCall;

    }

    /**
     * Cancel an order, specified by order id
     * 
     * @param orderId The order id (required)
     * @return Object
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public Object privateCancelGet(String orderId) throws ApiException {
        ApiResponse<Object> localVarResp = privateCancelGetWithHttpInfo(orderId);
        return localVarResp.getData();
    }

    /**
     * Cancel an order, specified by order id
     * 
     * @param orderId The order id (required)
     * @return ApiResponse&lt;Object&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<Object> privateCancelGetWithHttpInfo(String orderId) throws ApiException {
        okhttp3.Call localVarCall = privateCancelGetValidateBeforeCall(orderId, null);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Cancel an order, specified by order id (asynchronously)
     * 
     * @param orderId The order id (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call privateCancelGetAsync(String orderId, final ApiCallback<Object> _callback) throws ApiException {

        okhttp3.Call localVarCall = privateCancelGetValidateBeforeCall(orderId, _callback);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for privateClosePositionGet
     * @param instrumentName Instrument name (required)
     * @param type The order type (required)
     * @param price Optional price for limit order. (optional)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> ok response </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call privateClosePositionGetCall(String instrumentName, String type, BigDecimal price, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = new Object();

        // create path and map variables
        String localVarPath = "/private/close_position";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        if (instrumentName != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("instrument_name", instrumentName));
        }

        if (type != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("type", type));
        }

        if (price != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("price", price));
        }

        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();
        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
            
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        String[] localVarAuthNames = new String[] { "bearerAuth" };
        return localVarApiClient.buildCall(localVarPath, "GET", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call privateClosePositionGetValidateBeforeCall(String instrumentName, String type, BigDecimal price, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'instrumentName' is set
        if (instrumentName == null) {
            throw new ApiException("Missing the required parameter 'instrumentName' when calling privateClosePositionGet(Async)");
        }
        
        // verify the required parameter 'type' is set
        if (type == null) {
            throw new ApiException("Missing the required parameter 'type' when calling privateClosePositionGet(Async)");
        }
        

        okhttp3.Call localVarCall = privateClosePositionGetCall(instrumentName, type, price, _callback);
        return localVarCall;

    }

    /**
     * Makes closing position reduce only order .
     * 
     * @param instrumentName Instrument name (required)
     * @param type The order type (required)
     * @param price Optional price for limit order. (optional)
     * @return Object
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> ok response </td><td>  -  </td></tr>
     </table>
     */
    public Object privateClosePositionGet(String instrumentName, String type, BigDecimal price) throws ApiException {
        ApiResponse<Object> localVarResp = privateClosePositionGetWithHttpInfo(instrumentName, type, price);
        return localVarResp.getData();
    }

    /**
     * Makes closing position reduce only order .
     * 
     * @param instrumentName Instrument name (required)
     * @param type The order type (required)
     * @param price Optional price for limit order. (optional)
     * @return ApiResponse&lt;Object&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> ok response </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<Object> privateClosePositionGetWithHttpInfo(String instrumentName, String type, BigDecimal price) throws ApiException {
        okhttp3.Call localVarCall = privateClosePositionGetValidateBeforeCall(instrumentName, type, price, null);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Makes closing position reduce only order . (asynchronously)
     * 
     * @param instrumentName Instrument name (required)
     * @param type The order type (required)
     * @param price Optional price for limit order. (optional)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> ok response </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call privateClosePositionGetAsync(String instrumentName, String type, BigDecimal price, final ApiCallback<Object> _callback) throws ApiException {

        okhttp3.Call localVarCall = privateClosePositionGetValidateBeforeCall(instrumentName, type, price, _callback);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for privateEditGet
     * @param orderId The order id (required)
     * @param amount It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH (required)
     * @param price &lt;p&gt;The order price in base currency.&lt;/p&gt; &lt;p&gt;When editing an option order with advanced&#x3D;usd, the field price should be the option price value in USD.&lt;/p&gt; &lt;p&gt;When editing an option order with advanced&#x3D;implv, the field price should be a value of implied volatility in percentages. For example,  price&#x3D;100, means implied volatility of 100%&lt;/p&gt; (required)
     * @param postOnly &lt;p&gt;If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.&lt;/p&gt; &lt;p&gt;Only valid in combination with time_in_force&#x3D;&#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; (optional, default to true)
     * @param advanced Advanced option order type. If you have posted an advanced option order, it is necessary to re-supply this parameter when editing it (Only for options) (optional)
     * @param stopPrice Stop price, required for stop limit orders (Only for stop orders) (optional)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call privateEditGetCall(String orderId, BigDecimal amount, BigDecimal price, Boolean postOnly, String advanced, BigDecimal stopPrice, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = new Object();

        // create path and map variables
        String localVarPath = "/private/edit";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        if (orderId != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("order_id", orderId));
        }

        if (amount != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("amount", amount));
        }

        if (price != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("price", price));
        }

        if (postOnly != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("post_only", postOnly));
        }

        if (advanced != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("advanced", advanced));
        }

        if (stopPrice != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("stop_price", stopPrice));
        }

        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();
        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
            
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        String[] localVarAuthNames = new String[] { "bearerAuth" };
        return localVarApiClient.buildCall(localVarPath, "GET", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call privateEditGetValidateBeforeCall(String orderId, BigDecimal amount, BigDecimal price, Boolean postOnly, String advanced, BigDecimal stopPrice, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'orderId' is set
        if (orderId == null) {
            throw new ApiException("Missing the required parameter 'orderId' when calling privateEditGet(Async)");
        }
        
        // verify the required parameter 'amount' is set
        if (amount == null) {
            throw new ApiException("Missing the required parameter 'amount' when calling privateEditGet(Async)");
        }
        
        // verify the required parameter 'price' is set
        if (price == null) {
            throw new ApiException("Missing the required parameter 'price' when calling privateEditGet(Async)");
        }
        

        okhttp3.Call localVarCall = privateEditGetCall(orderId, amount, price, postOnly, advanced, stopPrice, _callback);
        return localVarCall;

    }

    /**
     * Change price, amount and/or other properties of an order.
     * 
     * @param orderId The order id (required)
     * @param amount It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH (required)
     * @param price &lt;p&gt;The order price in base currency.&lt;/p&gt; &lt;p&gt;When editing an option order with advanced&#x3D;usd, the field price should be the option price value in USD.&lt;/p&gt; &lt;p&gt;When editing an option order with advanced&#x3D;implv, the field price should be a value of implied volatility in percentages. For example,  price&#x3D;100, means implied volatility of 100%&lt;/p&gt; (required)
     * @param postOnly &lt;p&gt;If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.&lt;/p&gt; &lt;p&gt;Only valid in combination with time_in_force&#x3D;&#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; (optional, default to true)
     * @param advanced Advanced option order type. If you have posted an advanced option order, it is necessary to re-supply this parameter when editing it (Only for options) (optional)
     * @param stopPrice Stop price, required for stop limit orders (Only for stop orders) (optional)
     * @return Object
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public Object privateEditGet(String orderId, BigDecimal amount, BigDecimal price, Boolean postOnly, String advanced, BigDecimal stopPrice) throws ApiException {
        ApiResponse<Object> localVarResp = privateEditGetWithHttpInfo(orderId, amount, price, postOnly, advanced, stopPrice);
        return localVarResp.getData();
    }

    /**
     * Change price, amount and/or other properties of an order.
     * 
     * @param orderId The order id (required)
     * @param amount It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH (required)
     * @param price &lt;p&gt;The order price in base currency.&lt;/p&gt; &lt;p&gt;When editing an option order with advanced&#x3D;usd, the field price should be the option price value in USD.&lt;/p&gt; &lt;p&gt;When editing an option order with advanced&#x3D;implv, the field price should be a value of implied volatility in percentages. For example,  price&#x3D;100, means implied volatility of 100%&lt;/p&gt; (required)
     * @param postOnly &lt;p&gt;If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.&lt;/p&gt; &lt;p&gt;Only valid in combination with time_in_force&#x3D;&#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; (optional, default to true)
     * @param advanced Advanced option order type. If you have posted an advanced option order, it is necessary to re-supply this parameter when editing it (Only for options) (optional)
     * @param stopPrice Stop price, required for stop limit orders (Only for stop orders) (optional)
     * @return ApiResponse&lt;Object&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<Object> privateEditGetWithHttpInfo(String orderId, BigDecimal amount, BigDecimal price, Boolean postOnly, String advanced, BigDecimal stopPrice) throws ApiException {
        okhttp3.Call localVarCall = privateEditGetValidateBeforeCall(orderId, amount, price, postOnly, advanced, stopPrice, null);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Change price, amount and/or other properties of an order. (asynchronously)
     * 
     * @param orderId The order id (required)
     * @param amount It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH (required)
     * @param price &lt;p&gt;The order price in base currency.&lt;/p&gt; &lt;p&gt;When editing an option order with advanced&#x3D;usd, the field price should be the option price value in USD.&lt;/p&gt; &lt;p&gt;When editing an option order with advanced&#x3D;implv, the field price should be a value of implied volatility in percentages. For example,  price&#x3D;100, means implied volatility of 100%&lt;/p&gt; (required)
     * @param postOnly &lt;p&gt;If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.&lt;/p&gt; &lt;p&gt;Only valid in combination with time_in_force&#x3D;&#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; (optional, default to true)
     * @param advanced Advanced option order type. If you have posted an advanced option order, it is necessary to re-supply this parameter when editing it (Only for options) (optional)
     * @param stopPrice Stop price, required for stop limit orders (Only for stop orders) (optional)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call privateEditGetAsync(String orderId, BigDecimal amount, BigDecimal price, Boolean postOnly, String advanced, BigDecimal stopPrice, final ApiCallback<Object> _callback) throws ApiException {

        okhttp3.Call localVarCall = privateEditGetValidateBeforeCall(orderId, amount, price, postOnly, advanced, stopPrice, _callback);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for privateGetMarginsGet
     * @param instrumentName Instrument name (required)
     * @param amount Amount, integer for future, float for option. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH. (required)
     * @param price Price (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call privateGetMarginsGetCall(String instrumentName, BigDecimal amount, BigDecimal price, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = new Object();

        // create path and map variables
        String localVarPath = "/private/get_margins";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        if (instrumentName != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("instrument_name", instrumentName));
        }

        if (amount != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("amount", amount));
        }

        if (price != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("price", price));
        }

        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();
        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
            
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        String[] localVarAuthNames = new String[] { "bearerAuth" };
        return localVarApiClient.buildCall(localVarPath, "GET", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call privateGetMarginsGetValidateBeforeCall(String instrumentName, BigDecimal amount, BigDecimal price, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'instrumentName' is set
        if (instrumentName == null) {
            throw new ApiException("Missing the required parameter 'instrumentName' when calling privateGetMarginsGet(Async)");
        }
        
        // verify the required parameter 'amount' is set
        if (amount == null) {
            throw new ApiException("Missing the required parameter 'amount' when calling privateGetMarginsGet(Async)");
        }
        
        // verify the required parameter 'price' is set
        if (price == null) {
            throw new ApiException("Missing the required parameter 'price' when calling privateGetMarginsGet(Async)");
        }
        

        okhttp3.Call localVarCall = privateGetMarginsGetCall(instrumentName, amount, price, _callback);
        return localVarCall;

    }

    /**
     * Get margins for given instrument, amount and price.
     * 
     * @param instrumentName Instrument name (required)
     * @param amount Amount, integer for future, float for option. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH. (required)
     * @param price Price (required)
     * @return Object
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public Object privateGetMarginsGet(String instrumentName, BigDecimal amount, BigDecimal price) throws ApiException {
        ApiResponse<Object> localVarResp = privateGetMarginsGetWithHttpInfo(instrumentName, amount, price);
        return localVarResp.getData();
    }

    /**
     * Get margins for given instrument, amount and price.
     * 
     * @param instrumentName Instrument name (required)
     * @param amount Amount, integer for future, float for option. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH. (required)
     * @param price Price (required)
     * @return ApiResponse&lt;Object&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<Object> privateGetMarginsGetWithHttpInfo(String instrumentName, BigDecimal amount, BigDecimal price) throws ApiException {
        okhttp3.Call localVarCall = privateGetMarginsGetValidateBeforeCall(instrumentName, amount, price, null);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Get margins for given instrument, amount and price. (asynchronously)
     * 
     * @param instrumentName Instrument name (required)
     * @param amount Amount, integer for future, float for option. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH. (required)
     * @param price Price (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call privateGetMarginsGetAsync(String instrumentName, BigDecimal amount, BigDecimal price, final ApiCallback<Object> _callback) throws ApiException {

        okhttp3.Call localVarCall = privateGetMarginsGetValidateBeforeCall(instrumentName, amount, price, _callback);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for privateGetOpenOrdersByCurrencyGet
     * @param currency The currency symbol (required)
     * @param kind Instrument kind, if not provided instruments of all kinds are considered (optional)
     * @param type Order type, default - &#x60;all&#x60; (optional)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call privateGetOpenOrdersByCurrencyGetCall(String currency, String kind, String type, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = new Object();

        // create path and map variables
        String localVarPath = "/private/get_open_orders_by_currency";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        if (currency != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("currency", currency));
        }

        if (kind != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("kind", kind));
        }

        if (type != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("type", type));
        }

        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();
        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
            
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        String[] localVarAuthNames = new String[] { "bearerAuth" };
        return localVarApiClient.buildCall(localVarPath, "GET", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call privateGetOpenOrdersByCurrencyGetValidateBeforeCall(String currency, String kind, String type, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'currency' is set
        if (currency == null) {
            throw new ApiException("Missing the required parameter 'currency' when calling privateGetOpenOrdersByCurrencyGet(Async)");
        }
        

        okhttp3.Call localVarCall = privateGetOpenOrdersByCurrencyGetCall(currency, kind, type, _callback);
        return localVarCall;

    }

    /**
     * Retrieves list of user&#39;s open orders.
     * 
     * @param currency The currency symbol (required)
     * @param kind Instrument kind, if not provided instruments of all kinds are considered (optional)
     * @param type Order type, default - &#x60;all&#x60; (optional)
     * @return Object
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public Object privateGetOpenOrdersByCurrencyGet(String currency, String kind, String type) throws ApiException {
        ApiResponse<Object> localVarResp = privateGetOpenOrdersByCurrencyGetWithHttpInfo(currency, kind, type);
        return localVarResp.getData();
    }

    /**
     * Retrieves list of user&#39;s open orders.
     * 
     * @param currency The currency symbol (required)
     * @param kind Instrument kind, if not provided instruments of all kinds are considered (optional)
     * @param type Order type, default - &#x60;all&#x60; (optional)
     * @return ApiResponse&lt;Object&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<Object> privateGetOpenOrdersByCurrencyGetWithHttpInfo(String currency, String kind, String type) throws ApiException {
        okhttp3.Call localVarCall = privateGetOpenOrdersByCurrencyGetValidateBeforeCall(currency, kind, type, null);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Retrieves list of user&#39;s open orders. (asynchronously)
     * 
     * @param currency The currency symbol (required)
     * @param kind Instrument kind, if not provided instruments of all kinds are considered (optional)
     * @param type Order type, default - &#x60;all&#x60; (optional)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call privateGetOpenOrdersByCurrencyGetAsync(String currency, String kind, String type, final ApiCallback<Object> _callback) throws ApiException {

        okhttp3.Call localVarCall = privateGetOpenOrdersByCurrencyGetValidateBeforeCall(currency, kind, type, _callback);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for privateGetOpenOrdersByInstrumentGet
     * @param instrumentName Instrument name (required)
     * @param type Order type, default - &#x60;all&#x60; (optional)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call privateGetOpenOrdersByInstrumentGetCall(String instrumentName, String type, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = new Object();

        // create path and map variables
        String localVarPath = "/private/get_open_orders_by_instrument";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        if (instrumentName != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("instrument_name", instrumentName));
        }

        if (type != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("type", type));
        }

        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();
        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
            
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        String[] localVarAuthNames = new String[] { "bearerAuth" };
        return localVarApiClient.buildCall(localVarPath, "GET", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call privateGetOpenOrdersByInstrumentGetValidateBeforeCall(String instrumentName, String type, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'instrumentName' is set
        if (instrumentName == null) {
            throw new ApiException("Missing the required parameter 'instrumentName' when calling privateGetOpenOrdersByInstrumentGet(Async)");
        }
        

        okhttp3.Call localVarCall = privateGetOpenOrdersByInstrumentGetCall(instrumentName, type, _callback);
        return localVarCall;

    }

    /**
     * Retrieves list of user&#39;s open orders within given Instrument.
     * 
     * @param instrumentName Instrument name (required)
     * @param type Order type, default - &#x60;all&#x60; (optional)
     * @return Object
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public Object privateGetOpenOrdersByInstrumentGet(String instrumentName, String type) throws ApiException {
        ApiResponse<Object> localVarResp = privateGetOpenOrdersByInstrumentGetWithHttpInfo(instrumentName, type);
        return localVarResp.getData();
    }

    /**
     * Retrieves list of user&#39;s open orders within given Instrument.
     * 
     * @param instrumentName Instrument name (required)
     * @param type Order type, default - &#x60;all&#x60; (optional)
     * @return ApiResponse&lt;Object&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<Object> privateGetOpenOrdersByInstrumentGetWithHttpInfo(String instrumentName, String type) throws ApiException {
        okhttp3.Call localVarCall = privateGetOpenOrdersByInstrumentGetValidateBeforeCall(instrumentName, type, null);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Retrieves list of user&#39;s open orders within given Instrument. (asynchronously)
     * 
     * @param instrumentName Instrument name (required)
     * @param type Order type, default - &#x60;all&#x60; (optional)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call privateGetOpenOrdersByInstrumentGetAsync(String instrumentName, String type, final ApiCallback<Object> _callback) throws ApiException {

        okhttp3.Call localVarCall = privateGetOpenOrdersByInstrumentGetValidateBeforeCall(instrumentName, type, _callback);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for privateGetOrderHistoryByCurrencyGet
     * @param currency The currency symbol (required)
     * @param kind Instrument kind, if not provided instruments of all kinds are considered (optional)
     * @param count Number of requested items, default - &#x60;20&#x60; (optional)
     * @param offset The offset for pagination, default - &#x60;0&#x60; (optional)
     * @param includeOld Include in result orders older than 2 days, default - &#x60;false&#x60; (optional)
     * @param includeUnfilled Include in result fully unfilled closed orders, default - &#x60;false&#x60; (optional)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call privateGetOrderHistoryByCurrencyGetCall(String currency, String kind, Integer count, Integer offset, Boolean includeOld, Boolean includeUnfilled, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = new Object();

        // create path and map variables
        String localVarPath = "/private/get_order_history_by_currency";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        if (currency != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("currency", currency));
        }

        if (kind != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("kind", kind));
        }

        if (count != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("count", count));
        }

        if (offset != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("offset", offset));
        }

        if (includeOld != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("include_old", includeOld));
        }

        if (includeUnfilled != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("include_unfilled", includeUnfilled));
        }

        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();
        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
            
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        String[] localVarAuthNames = new String[] { "bearerAuth" };
        return localVarApiClient.buildCall(localVarPath, "GET", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call privateGetOrderHistoryByCurrencyGetValidateBeforeCall(String currency, String kind, Integer count, Integer offset, Boolean includeOld, Boolean includeUnfilled, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'currency' is set
        if (currency == null) {
            throw new ApiException("Missing the required parameter 'currency' when calling privateGetOrderHistoryByCurrencyGet(Async)");
        }
        

        okhttp3.Call localVarCall = privateGetOrderHistoryByCurrencyGetCall(currency, kind, count, offset, includeOld, includeUnfilled, _callback);
        return localVarCall;

    }

    /**
     * Retrieves history of orders that have been partially or fully filled.
     * 
     * @param currency The currency symbol (required)
     * @param kind Instrument kind, if not provided instruments of all kinds are considered (optional)
     * @param count Number of requested items, default - &#x60;20&#x60; (optional)
     * @param offset The offset for pagination, default - &#x60;0&#x60; (optional)
     * @param includeOld Include in result orders older than 2 days, default - &#x60;false&#x60; (optional)
     * @param includeUnfilled Include in result fully unfilled closed orders, default - &#x60;false&#x60; (optional)
     * @return Object
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public Object privateGetOrderHistoryByCurrencyGet(String currency, String kind, Integer count, Integer offset, Boolean includeOld, Boolean includeUnfilled) throws ApiException {
        ApiResponse<Object> localVarResp = privateGetOrderHistoryByCurrencyGetWithHttpInfo(currency, kind, count, offset, includeOld, includeUnfilled);
        return localVarResp.getData();
    }

    /**
     * Retrieves history of orders that have been partially or fully filled.
     * 
     * @param currency The currency symbol (required)
     * @param kind Instrument kind, if not provided instruments of all kinds are considered (optional)
     * @param count Number of requested items, default - &#x60;20&#x60; (optional)
     * @param offset The offset for pagination, default - &#x60;0&#x60; (optional)
     * @param includeOld Include in result orders older than 2 days, default - &#x60;false&#x60; (optional)
     * @param includeUnfilled Include in result fully unfilled closed orders, default - &#x60;false&#x60; (optional)
     * @return ApiResponse&lt;Object&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<Object> privateGetOrderHistoryByCurrencyGetWithHttpInfo(String currency, String kind, Integer count, Integer offset, Boolean includeOld, Boolean includeUnfilled) throws ApiException {
        okhttp3.Call localVarCall = privateGetOrderHistoryByCurrencyGetValidateBeforeCall(currency, kind, count, offset, includeOld, includeUnfilled, null);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Retrieves history of orders that have been partially or fully filled. (asynchronously)
     * 
     * @param currency The currency symbol (required)
     * @param kind Instrument kind, if not provided instruments of all kinds are considered (optional)
     * @param count Number of requested items, default - &#x60;20&#x60; (optional)
     * @param offset The offset for pagination, default - &#x60;0&#x60; (optional)
     * @param includeOld Include in result orders older than 2 days, default - &#x60;false&#x60; (optional)
     * @param includeUnfilled Include in result fully unfilled closed orders, default - &#x60;false&#x60; (optional)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call privateGetOrderHistoryByCurrencyGetAsync(String currency, String kind, Integer count, Integer offset, Boolean includeOld, Boolean includeUnfilled, final ApiCallback<Object> _callback) throws ApiException {

        okhttp3.Call localVarCall = privateGetOrderHistoryByCurrencyGetValidateBeforeCall(currency, kind, count, offset, includeOld, includeUnfilled, _callback);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for privateGetOrderHistoryByInstrumentGet
     * @param instrumentName Instrument name (required)
     * @param count Number of requested items, default - &#x60;20&#x60; (optional)
     * @param offset The offset for pagination, default - &#x60;0&#x60; (optional)
     * @param includeOld Include in result orders older than 2 days, default - &#x60;false&#x60; (optional)
     * @param includeUnfilled Include in result fully unfilled closed orders, default - &#x60;false&#x60; (optional)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call privateGetOrderHistoryByInstrumentGetCall(String instrumentName, Integer count, Integer offset, Boolean includeOld, Boolean includeUnfilled, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = new Object();

        // create path and map variables
        String localVarPath = "/private/get_order_history_by_instrument";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        if (instrumentName != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("instrument_name", instrumentName));
        }

        if (count != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("count", count));
        }

        if (offset != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("offset", offset));
        }

        if (includeOld != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("include_old", includeOld));
        }

        if (includeUnfilled != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("include_unfilled", includeUnfilled));
        }

        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();
        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
            
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        String[] localVarAuthNames = new String[] { "bearerAuth" };
        return localVarApiClient.buildCall(localVarPath, "GET", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call privateGetOrderHistoryByInstrumentGetValidateBeforeCall(String instrumentName, Integer count, Integer offset, Boolean includeOld, Boolean includeUnfilled, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'instrumentName' is set
        if (instrumentName == null) {
            throw new ApiException("Missing the required parameter 'instrumentName' when calling privateGetOrderHistoryByInstrumentGet(Async)");
        }
        

        okhttp3.Call localVarCall = privateGetOrderHistoryByInstrumentGetCall(instrumentName, count, offset, includeOld, includeUnfilled, _callback);
        return localVarCall;

    }

    /**
     * Retrieves history of orders that have been partially or fully filled.
     * 
     * @param instrumentName Instrument name (required)
     * @param count Number of requested items, default - &#x60;20&#x60; (optional)
     * @param offset The offset for pagination, default - &#x60;0&#x60; (optional)
     * @param includeOld Include in result orders older than 2 days, default - &#x60;false&#x60; (optional)
     * @param includeUnfilled Include in result fully unfilled closed orders, default - &#x60;false&#x60; (optional)
     * @return Object
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public Object privateGetOrderHistoryByInstrumentGet(String instrumentName, Integer count, Integer offset, Boolean includeOld, Boolean includeUnfilled) throws ApiException {
        ApiResponse<Object> localVarResp = privateGetOrderHistoryByInstrumentGetWithHttpInfo(instrumentName, count, offset, includeOld, includeUnfilled);
        return localVarResp.getData();
    }

    /**
     * Retrieves history of orders that have been partially or fully filled.
     * 
     * @param instrumentName Instrument name (required)
     * @param count Number of requested items, default - &#x60;20&#x60; (optional)
     * @param offset The offset for pagination, default - &#x60;0&#x60; (optional)
     * @param includeOld Include in result orders older than 2 days, default - &#x60;false&#x60; (optional)
     * @param includeUnfilled Include in result fully unfilled closed orders, default - &#x60;false&#x60; (optional)
     * @return ApiResponse&lt;Object&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<Object> privateGetOrderHistoryByInstrumentGetWithHttpInfo(String instrumentName, Integer count, Integer offset, Boolean includeOld, Boolean includeUnfilled) throws ApiException {
        okhttp3.Call localVarCall = privateGetOrderHistoryByInstrumentGetValidateBeforeCall(instrumentName, count, offset, includeOld, includeUnfilled, null);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Retrieves history of orders that have been partially or fully filled. (asynchronously)
     * 
     * @param instrumentName Instrument name (required)
     * @param count Number of requested items, default - &#x60;20&#x60; (optional)
     * @param offset The offset for pagination, default - &#x60;0&#x60; (optional)
     * @param includeOld Include in result orders older than 2 days, default - &#x60;false&#x60; (optional)
     * @param includeUnfilled Include in result fully unfilled closed orders, default - &#x60;false&#x60; (optional)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call privateGetOrderHistoryByInstrumentGetAsync(String instrumentName, Integer count, Integer offset, Boolean includeOld, Boolean includeUnfilled, final ApiCallback<Object> _callback) throws ApiException {

        okhttp3.Call localVarCall = privateGetOrderHistoryByInstrumentGetValidateBeforeCall(instrumentName, count, offset, includeOld, includeUnfilled, _callback);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for privateGetOrderMarginByIdsGet
     * @param ids Ids of orders (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call privateGetOrderMarginByIdsGetCall(List<String> ids, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = new Object();

        // create path and map variables
        String localVarPath = "/private/get_order_margin_by_ids";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        if (ids != null) {
            localVarCollectionQueryParams.addAll(localVarApiClient.parameterToPairs("multi", "ids", ids));
        }

        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();
        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
            
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        String[] localVarAuthNames = new String[] { "bearerAuth" };
        return localVarApiClient.buildCall(localVarPath, "GET", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call privateGetOrderMarginByIdsGetValidateBeforeCall(List<String> ids, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'ids' is set
        if (ids == null) {
            throw new ApiException("Missing the required parameter 'ids' when calling privateGetOrderMarginByIdsGet(Async)");
        }
        

        okhttp3.Call localVarCall = privateGetOrderMarginByIdsGetCall(ids, _callback);
        return localVarCall;

    }

    /**
     * Retrieves initial margins of given orders
     * 
     * @param ids Ids of orders (required)
     * @return Object
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public Object privateGetOrderMarginByIdsGet(List<String> ids) throws ApiException {
        ApiResponse<Object> localVarResp = privateGetOrderMarginByIdsGetWithHttpInfo(ids);
        return localVarResp.getData();
    }

    /**
     * Retrieves initial margins of given orders
     * 
     * @param ids Ids of orders (required)
     * @return ApiResponse&lt;Object&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<Object> privateGetOrderMarginByIdsGetWithHttpInfo(List<String> ids) throws ApiException {
        okhttp3.Call localVarCall = privateGetOrderMarginByIdsGetValidateBeforeCall(ids, null);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Retrieves initial margins of given orders (asynchronously)
     * 
     * @param ids Ids of orders (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call privateGetOrderMarginByIdsGetAsync(List<String> ids, final ApiCallback<Object> _callback) throws ApiException {

        okhttp3.Call localVarCall = privateGetOrderMarginByIdsGetValidateBeforeCall(ids, _callback);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for privateGetOrderStateGet
     * @param orderId The order id (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> ok response </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> result when used via rest/HTTP </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call privateGetOrderStateGetCall(String orderId, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = new Object();

        // create path and map variables
        String localVarPath = "/private/get_order_state";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        if (orderId != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("order_id", orderId));
        }

        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();
        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
            
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        String[] localVarAuthNames = new String[] { "bearerAuth" };
        return localVarApiClient.buildCall(localVarPath, "GET", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call privateGetOrderStateGetValidateBeforeCall(String orderId, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'orderId' is set
        if (orderId == null) {
            throw new ApiException("Missing the required parameter 'orderId' when calling privateGetOrderStateGet(Async)");
        }
        

        okhttp3.Call localVarCall = privateGetOrderStateGetCall(orderId, _callback);
        return localVarCall;

    }

    /**
     * Retrieve the current state of an order.
     * 
     * @param orderId The order id (required)
     * @return Object
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> ok response </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> result when used via rest/HTTP </td><td>  -  </td></tr>
     </table>
     */
    public Object privateGetOrderStateGet(String orderId) throws ApiException {
        ApiResponse<Object> localVarResp = privateGetOrderStateGetWithHttpInfo(orderId);
        return localVarResp.getData();
    }

    /**
     * Retrieve the current state of an order.
     * 
     * @param orderId The order id (required)
     * @return ApiResponse&lt;Object&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> ok response </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> result when used via rest/HTTP </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<Object> privateGetOrderStateGetWithHttpInfo(String orderId) throws ApiException {
        okhttp3.Call localVarCall = privateGetOrderStateGetValidateBeforeCall(orderId, null);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Retrieve the current state of an order. (asynchronously)
     * 
     * @param orderId The order id (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> ok response </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> result when used via rest/HTTP </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call privateGetOrderStateGetAsync(String orderId, final ApiCallback<Object> _callback) throws ApiException {

        okhttp3.Call localVarCall = privateGetOrderStateGetValidateBeforeCall(orderId, _callback);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for privateGetSettlementHistoryByCurrencyGet
     * @param currency The currency symbol (required)
     * @param type Settlement type (optional)
     * @param count Number of requested items, default - &#x60;20&#x60; (optional)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call privateGetSettlementHistoryByCurrencyGetCall(String currency, String type, Integer count, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = new Object();

        // create path and map variables
        String localVarPath = "/private/get_settlement_history_by_currency";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        if (currency != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("currency", currency));
        }

        if (type != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("type", type));
        }

        if (count != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("count", count));
        }

        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();
        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
            
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        String[] localVarAuthNames = new String[] { "bearerAuth" };
        return localVarApiClient.buildCall(localVarPath, "GET", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call privateGetSettlementHistoryByCurrencyGetValidateBeforeCall(String currency, String type, Integer count, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'currency' is set
        if (currency == null) {
            throw new ApiException("Missing the required parameter 'currency' when calling privateGetSettlementHistoryByCurrencyGet(Async)");
        }
        

        okhttp3.Call localVarCall = privateGetSettlementHistoryByCurrencyGetCall(currency, type, count, _callback);
        return localVarCall;

    }

    /**
     * Retrieves settlement, delivery and bankruptcy events that have affected your account.
     * 
     * @param currency The currency symbol (required)
     * @param type Settlement type (optional)
     * @param count Number of requested items, default - &#x60;20&#x60; (optional)
     * @return Object
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public Object privateGetSettlementHistoryByCurrencyGet(String currency, String type, Integer count) throws ApiException {
        ApiResponse<Object> localVarResp = privateGetSettlementHistoryByCurrencyGetWithHttpInfo(currency, type, count);
        return localVarResp.getData();
    }

    /**
     * Retrieves settlement, delivery and bankruptcy events that have affected your account.
     * 
     * @param currency The currency symbol (required)
     * @param type Settlement type (optional)
     * @param count Number of requested items, default - &#x60;20&#x60; (optional)
     * @return ApiResponse&lt;Object&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<Object> privateGetSettlementHistoryByCurrencyGetWithHttpInfo(String currency, String type, Integer count) throws ApiException {
        okhttp3.Call localVarCall = privateGetSettlementHistoryByCurrencyGetValidateBeforeCall(currency, type, count, null);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Retrieves settlement, delivery and bankruptcy events that have affected your account. (asynchronously)
     * 
     * @param currency The currency symbol (required)
     * @param type Settlement type (optional)
     * @param count Number of requested items, default - &#x60;20&#x60; (optional)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call privateGetSettlementHistoryByCurrencyGetAsync(String currency, String type, Integer count, final ApiCallback<Object> _callback) throws ApiException {

        okhttp3.Call localVarCall = privateGetSettlementHistoryByCurrencyGetValidateBeforeCall(currency, type, count, _callback);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for privateGetSettlementHistoryByInstrumentGet
     * @param instrumentName Instrument name (required)
     * @param type Settlement type (optional)
     * @param count Number of requested items, default - &#x60;20&#x60; (optional)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call privateGetSettlementHistoryByInstrumentGetCall(String instrumentName, String type, Integer count, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = new Object();

        // create path and map variables
        String localVarPath = "/private/get_settlement_history_by_instrument";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        if (instrumentName != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("instrument_name", instrumentName));
        }

        if (type != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("type", type));
        }

        if (count != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("count", count));
        }

        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();
        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
            
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        String[] localVarAuthNames = new String[] { "bearerAuth" };
        return localVarApiClient.buildCall(localVarPath, "GET", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call privateGetSettlementHistoryByInstrumentGetValidateBeforeCall(String instrumentName, String type, Integer count, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'instrumentName' is set
        if (instrumentName == null) {
            throw new ApiException("Missing the required parameter 'instrumentName' when calling privateGetSettlementHistoryByInstrumentGet(Async)");
        }
        

        okhttp3.Call localVarCall = privateGetSettlementHistoryByInstrumentGetCall(instrumentName, type, count, _callback);
        return localVarCall;

    }

    /**
     * Retrieves public settlement, delivery and bankruptcy events filtered by instrument name
     * 
     * @param instrumentName Instrument name (required)
     * @param type Settlement type (optional)
     * @param count Number of requested items, default - &#x60;20&#x60; (optional)
     * @return Object
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public Object privateGetSettlementHistoryByInstrumentGet(String instrumentName, String type, Integer count) throws ApiException {
        ApiResponse<Object> localVarResp = privateGetSettlementHistoryByInstrumentGetWithHttpInfo(instrumentName, type, count);
        return localVarResp.getData();
    }

    /**
     * Retrieves public settlement, delivery and bankruptcy events filtered by instrument name
     * 
     * @param instrumentName Instrument name (required)
     * @param type Settlement type (optional)
     * @param count Number of requested items, default - &#x60;20&#x60; (optional)
     * @return ApiResponse&lt;Object&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<Object> privateGetSettlementHistoryByInstrumentGetWithHttpInfo(String instrumentName, String type, Integer count) throws ApiException {
        okhttp3.Call localVarCall = privateGetSettlementHistoryByInstrumentGetValidateBeforeCall(instrumentName, type, count, null);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Retrieves public settlement, delivery and bankruptcy events filtered by instrument name (asynchronously)
     * 
     * @param instrumentName Instrument name (required)
     * @param type Settlement type (optional)
     * @param count Number of requested items, default - &#x60;20&#x60; (optional)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call privateGetSettlementHistoryByInstrumentGetAsync(String instrumentName, String type, Integer count, final ApiCallback<Object> _callback) throws ApiException {

        okhttp3.Call localVarCall = privateGetSettlementHistoryByInstrumentGetValidateBeforeCall(instrumentName, type, count, _callback);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for privateGetUserTradesByCurrencyAndTimeGet
     * @param currency The currency symbol (required)
     * @param startTimestamp The earliest timestamp to return result for (required)
     * @param endTimestamp The most recent timestamp to return result for (required)
     * @param kind Instrument kind, if not provided instruments of all kinds are considered (optional)
     * @param count Number of requested items, default - &#x60;10&#x60; (optional)
     * @param includeOld Include trades older than 7 days, default - &#x60;false&#x60; (optional)
     * @param sorting Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) (optional)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call privateGetUserTradesByCurrencyAndTimeGetCall(String currency, Integer startTimestamp, Integer endTimestamp, String kind, Integer count, Boolean includeOld, String sorting, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = new Object();

        // create path and map variables
        String localVarPath = "/private/get_user_trades_by_currency_and_time";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        if (currency != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("currency", currency));
        }

        if (kind != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("kind", kind));
        }

        if (startTimestamp != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("start_timestamp", startTimestamp));
        }

        if (endTimestamp != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("end_timestamp", endTimestamp));
        }

        if (count != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("count", count));
        }

        if (includeOld != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("include_old", includeOld));
        }

        if (sorting != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("sorting", sorting));
        }

        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();
        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
            
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        String[] localVarAuthNames = new String[] { "bearerAuth" };
        return localVarApiClient.buildCall(localVarPath, "GET", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call privateGetUserTradesByCurrencyAndTimeGetValidateBeforeCall(String currency, Integer startTimestamp, Integer endTimestamp, String kind, Integer count, Boolean includeOld, String sorting, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'currency' is set
        if (currency == null) {
            throw new ApiException("Missing the required parameter 'currency' when calling privateGetUserTradesByCurrencyAndTimeGet(Async)");
        }
        
        // verify the required parameter 'startTimestamp' is set
        if (startTimestamp == null) {
            throw new ApiException("Missing the required parameter 'startTimestamp' when calling privateGetUserTradesByCurrencyAndTimeGet(Async)");
        }
        
        // verify the required parameter 'endTimestamp' is set
        if (endTimestamp == null) {
            throw new ApiException("Missing the required parameter 'endTimestamp' when calling privateGetUserTradesByCurrencyAndTimeGet(Async)");
        }
        

        okhttp3.Call localVarCall = privateGetUserTradesByCurrencyAndTimeGetCall(currency, startTimestamp, endTimestamp, kind, count, includeOld, sorting, _callback);
        return localVarCall;

    }

    /**
     * Retrieve the latest user trades that have occurred for instruments in a specific currency symbol and within given time range.
     * 
     * @param currency The currency symbol (required)
     * @param startTimestamp The earliest timestamp to return result for (required)
     * @param endTimestamp The most recent timestamp to return result for (required)
     * @param kind Instrument kind, if not provided instruments of all kinds are considered (optional)
     * @param count Number of requested items, default - &#x60;10&#x60; (optional)
     * @param includeOld Include trades older than 7 days, default - &#x60;false&#x60; (optional)
     * @param sorting Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) (optional)
     * @return Object
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public Object privateGetUserTradesByCurrencyAndTimeGet(String currency, Integer startTimestamp, Integer endTimestamp, String kind, Integer count, Boolean includeOld, String sorting) throws ApiException {
        ApiResponse<Object> localVarResp = privateGetUserTradesByCurrencyAndTimeGetWithHttpInfo(currency, startTimestamp, endTimestamp, kind, count, includeOld, sorting);
        return localVarResp.getData();
    }

    /**
     * Retrieve the latest user trades that have occurred for instruments in a specific currency symbol and within given time range.
     * 
     * @param currency The currency symbol (required)
     * @param startTimestamp The earliest timestamp to return result for (required)
     * @param endTimestamp The most recent timestamp to return result for (required)
     * @param kind Instrument kind, if not provided instruments of all kinds are considered (optional)
     * @param count Number of requested items, default - &#x60;10&#x60; (optional)
     * @param includeOld Include trades older than 7 days, default - &#x60;false&#x60; (optional)
     * @param sorting Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) (optional)
     * @return ApiResponse&lt;Object&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<Object> privateGetUserTradesByCurrencyAndTimeGetWithHttpInfo(String currency, Integer startTimestamp, Integer endTimestamp, String kind, Integer count, Boolean includeOld, String sorting) throws ApiException {
        okhttp3.Call localVarCall = privateGetUserTradesByCurrencyAndTimeGetValidateBeforeCall(currency, startTimestamp, endTimestamp, kind, count, includeOld, sorting, null);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Retrieve the latest user trades that have occurred for instruments in a specific currency symbol and within given time range. (asynchronously)
     * 
     * @param currency The currency symbol (required)
     * @param startTimestamp The earliest timestamp to return result for (required)
     * @param endTimestamp The most recent timestamp to return result for (required)
     * @param kind Instrument kind, if not provided instruments of all kinds are considered (optional)
     * @param count Number of requested items, default - &#x60;10&#x60; (optional)
     * @param includeOld Include trades older than 7 days, default - &#x60;false&#x60; (optional)
     * @param sorting Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) (optional)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call privateGetUserTradesByCurrencyAndTimeGetAsync(String currency, Integer startTimestamp, Integer endTimestamp, String kind, Integer count, Boolean includeOld, String sorting, final ApiCallback<Object> _callback) throws ApiException {

        okhttp3.Call localVarCall = privateGetUserTradesByCurrencyAndTimeGetValidateBeforeCall(currency, startTimestamp, endTimestamp, kind, count, includeOld, sorting, _callback);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for privateGetUserTradesByCurrencyGet
     * @param currency The currency symbol (required)
     * @param kind Instrument kind, if not provided instruments of all kinds are considered (optional)
     * @param startId The ID number of the first trade to be returned (optional)
     * @param endId The ID number of the last trade to be returned (optional)
     * @param count Number of requested items, default - &#x60;10&#x60; (optional)
     * @param includeOld Include trades older than 7 days, default - &#x60;false&#x60; (optional)
     * @param sorting Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) (optional)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call privateGetUserTradesByCurrencyGetCall(String currency, String kind, String startId, String endId, Integer count, Boolean includeOld, String sorting, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = new Object();

        // create path and map variables
        String localVarPath = "/private/get_user_trades_by_currency";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        if (currency != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("currency", currency));
        }

        if (kind != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("kind", kind));
        }

        if (startId != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("start_id", startId));
        }

        if (endId != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("end_id", endId));
        }

        if (count != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("count", count));
        }

        if (includeOld != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("include_old", includeOld));
        }

        if (sorting != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("sorting", sorting));
        }

        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();
        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
            
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        String[] localVarAuthNames = new String[] { "bearerAuth" };
        return localVarApiClient.buildCall(localVarPath, "GET", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call privateGetUserTradesByCurrencyGetValidateBeforeCall(String currency, String kind, String startId, String endId, Integer count, Boolean includeOld, String sorting, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'currency' is set
        if (currency == null) {
            throw new ApiException("Missing the required parameter 'currency' when calling privateGetUserTradesByCurrencyGet(Async)");
        }
        

        okhttp3.Call localVarCall = privateGetUserTradesByCurrencyGetCall(currency, kind, startId, endId, count, includeOld, sorting, _callback);
        return localVarCall;

    }

    /**
     * Retrieve the latest user trades that have occurred for instruments in a specific currency symbol.
     * 
     * @param currency The currency symbol (required)
     * @param kind Instrument kind, if not provided instruments of all kinds are considered (optional)
     * @param startId The ID number of the first trade to be returned (optional)
     * @param endId The ID number of the last trade to be returned (optional)
     * @param count Number of requested items, default - &#x60;10&#x60; (optional)
     * @param includeOld Include trades older than 7 days, default - &#x60;false&#x60; (optional)
     * @param sorting Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) (optional)
     * @return Object
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public Object privateGetUserTradesByCurrencyGet(String currency, String kind, String startId, String endId, Integer count, Boolean includeOld, String sorting) throws ApiException {
        ApiResponse<Object> localVarResp = privateGetUserTradesByCurrencyGetWithHttpInfo(currency, kind, startId, endId, count, includeOld, sorting);
        return localVarResp.getData();
    }

    /**
     * Retrieve the latest user trades that have occurred for instruments in a specific currency symbol.
     * 
     * @param currency The currency symbol (required)
     * @param kind Instrument kind, if not provided instruments of all kinds are considered (optional)
     * @param startId The ID number of the first trade to be returned (optional)
     * @param endId The ID number of the last trade to be returned (optional)
     * @param count Number of requested items, default - &#x60;10&#x60; (optional)
     * @param includeOld Include trades older than 7 days, default - &#x60;false&#x60; (optional)
     * @param sorting Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) (optional)
     * @return ApiResponse&lt;Object&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<Object> privateGetUserTradesByCurrencyGetWithHttpInfo(String currency, String kind, String startId, String endId, Integer count, Boolean includeOld, String sorting) throws ApiException {
        okhttp3.Call localVarCall = privateGetUserTradesByCurrencyGetValidateBeforeCall(currency, kind, startId, endId, count, includeOld, sorting, null);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Retrieve the latest user trades that have occurred for instruments in a specific currency symbol. (asynchronously)
     * 
     * @param currency The currency symbol (required)
     * @param kind Instrument kind, if not provided instruments of all kinds are considered (optional)
     * @param startId The ID number of the first trade to be returned (optional)
     * @param endId The ID number of the last trade to be returned (optional)
     * @param count Number of requested items, default - &#x60;10&#x60; (optional)
     * @param includeOld Include trades older than 7 days, default - &#x60;false&#x60; (optional)
     * @param sorting Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) (optional)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call privateGetUserTradesByCurrencyGetAsync(String currency, String kind, String startId, String endId, Integer count, Boolean includeOld, String sorting, final ApiCallback<Object> _callback) throws ApiException {

        okhttp3.Call localVarCall = privateGetUserTradesByCurrencyGetValidateBeforeCall(currency, kind, startId, endId, count, includeOld, sorting, _callback);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for privateGetUserTradesByInstrumentAndTimeGet
     * @param instrumentName Instrument name (required)
     * @param startTimestamp The earliest timestamp to return result for (required)
     * @param endTimestamp The most recent timestamp to return result for (required)
     * @param count Number of requested items, default - &#x60;10&#x60; (optional)
     * @param includeOld Include trades older than 7 days, default - &#x60;false&#x60; (optional)
     * @param sorting Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) (optional)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call privateGetUserTradesByInstrumentAndTimeGetCall(String instrumentName, Integer startTimestamp, Integer endTimestamp, Integer count, Boolean includeOld, String sorting, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = new Object();

        // create path and map variables
        String localVarPath = "/private/get_user_trades_by_instrument_and_time";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        if (instrumentName != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("instrument_name", instrumentName));
        }

        if (startTimestamp != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("start_timestamp", startTimestamp));
        }

        if (endTimestamp != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("end_timestamp", endTimestamp));
        }

        if (count != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("count", count));
        }

        if (includeOld != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("include_old", includeOld));
        }

        if (sorting != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("sorting", sorting));
        }

        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();
        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
            
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        String[] localVarAuthNames = new String[] { "bearerAuth" };
        return localVarApiClient.buildCall(localVarPath, "GET", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call privateGetUserTradesByInstrumentAndTimeGetValidateBeforeCall(String instrumentName, Integer startTimestamp, Integer endTimestamp, Integer count, Boolean includeOld, String sorting, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'instrumentName' is set
        if (instrumentName == null) {
            throw new ApiException("Missing the required parameter 'instrumentName' when calling privateGetUserTradesByInstrumentAndTimeGet(Async)");
        }
        
        // verify the required parameter 'startTimestamp' is set
        if (startTimestamp == null) {
            throw new ApiException("Missing the required parameter 'startTimestamp' when calling privateGetUserTradesByInstrumentAndTimeGet(Async)");
        }
        
        // verify the required parameter 'endTimestamp' is set
        if (endTimestamp == null) {
            throw new ApiException("Missing the required parameter 'endTimestamp' when calling privateGetUserTradesByInstrumentAndTimeGet(Async)");
        }
        

        okhttp3.Call localVarCall = privateGetUserTradesByInstrumentAndTimeGetCall(instrumentName, startTimestamp, endTimestamp, count, includeOld, sorting, _callback);
        return localVarCall;

    }

    /**
     * Retrieve the latest user trades that have occurred for a specific instrument and within given time range.
     * 
     * @param instrumentName Instrument name (required)
     * @param startTimestamp The earliest timestamp to return result for (required)
     * @param endTimestamp The most recent timestamp to return result for (required)
     * @param count Number of requested items, default - &#x60;10&#x60; (optional)
     * @param includeOld Include trades older than 7 days, default - &#x60;false&#x60; (optional)
     * @param sorting Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) (optional)
     * @return Object
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public Object privateGetUserTradesByInstrumentAndTimeGet(String instrumentName, Integer startTimestamp, Integer endTimestamp, Integer count, Boolean includeOld, String sorting) throws ApiException {
        ApiResponse<Object> localVarResp = privateGetUserTradesByInstrumentAndTimeGetWithHttpInfo(instrumentName, startTimestamp, endTimestamp, count, includeOld, sorting);
        return localVarResp.getData();
    }

    /**
     * Retrieve the latest user trades that have occurred for a specific instrument and within given time range.
     * 
     * @param instrumentName Instrument name (required)
     * @param startTimestamp The earliest timestamp to return result for (required)
     * @param endTimestamp The most recent timestamp to return result for (required)
     * @param count Number of requested items, default - &#x60;10&#x60; (optional)
     * @param includeOld Include trades older than 7 days, default - &#x60;false&#x60; (optional)
     * @param sorting Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) (optional)
     * @return ApiResponse&lt;Object&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<Object> privateGetUserTradesByInstrumentAndTimeGetWithHttpInfo(String instrumentName, Integer startTimestamp, Integer endTimestamp, Integer count, Boolean includeOld, String sorting) throws ApiException {
        okhttp3.Call localVarCall = privateGetUserTradesByInstrumentAndTimeGetValidateBeforeCall(instrumentName, startTimestamp, endTimestamp, count, includeOld, sorting, null);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Retrieve the latest user trades that have occurred for a specific instrument and within given time range. (asynchronously)
     * 
     * @param instrumentName Instrument name (required)
     * @param startTimestamp The earliest timestamp to return result for (required)
     * @param endTimestamp The most recent timestamp to return result for (required)
     * @param count Number of requested items, default - &#x60;10&#x60; (optional)
     * @param includeOld Include trades older than 7 days, default - &#x60;false&#x60; (optional)
     * @param sorting Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) (optional)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call privateGetUserTradesByInstrumentAndTimeGetAsync(String instrumentName, Integer startTimestamp, Integer endTimestamp, Integer count, Boolean includeOld, String sorting, final ApiCallback<Object> _callback) throws ApiException {

        okhttp3.Call localVarCall = privateGetUserTradesByInstrumentAndTimeGetValidateBeforeCall(instrumentName, startTimestamp, endTimestamp, count, includeOld, sorting, _callback);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for privateGetUserTradesByInstrumentGet
     * @param instrumentName Instrument name (required)
     * @param startSeq The sequence number of the first trade to be returned (optional)
     * @param endSeq The sequence number of the last trade to be returned (optional)
     * @param count Number of requested items, default - &#x60;10&#x60; (optional)
     * @param includeOld Include trades older than 7 days, default - &#x60;false&#x60; (optional)
     * @param sorting Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) (optional)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call privateGetUserTradesByInstrumentGetCall(String instrumentName, Integer startSeq, Integer endSeq, Integer count, Boolean includeOld, String sorting, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = new Object();

        // create path and map variables
        String localVarPath = "/private/get_user_trades_by_instrument";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        if (instrumentName != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("instrument_name", instrumentName));
        }

        if (startSeq != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("start_seq", startSeq));
        }

        if (endSeq != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("end_seq", endSeq));
        }

        if (count != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("count", count));
        }

        if (includeOld != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("include_old", includeOld));
        }

        if (sorting != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("sorting", sorting));
        }

        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();
        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
            
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        String[] localVarAuthNames = new String[] { "bearerAuth" };
        return localVarApiClient.buildCall(localVarPath, "GET", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call privateGetUserTradesByInstrumentGetValidateBeforeCall(String instrumentName, Integer startSeq, Integer endSeq, Integer count, Boolean includeOld, String sorting, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'instrumentName' is set
        if (instrumentName == null) {
            throw new ApiException("Missing the required parameter 'instrumentName' when calling privateGetUserTradesByInstrumentGet(Async)");
        }
        

        okhttp3.Call localVarCall = privateGetUserTradesByInstrumentGetCall(instrumentName, startSeq, endSeq, count, includeOld, sorting, _callback);
        return localVarCall;

    }

    /**
     * Retrieve the latest user trades that have occurred for a specific instrument.
     * 
     * @param instrumentName Instrument name (required)
     * @param startSeq The sequence number of the first trade to be returned (optional)
     * @param endSeq The sequence number of the last trade to be returned (optional)
     * @param count Number of requested items, default - &#x60;10&#x60; (optional)
     * @param includeOld Include trades older than 7 days, default - &#x60;false&#x60; (optional)
     * @param sorting Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) (optional)
     * @return Object
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public Object privateGetUserTradesByInstrumentGet(String instrumentName, Integer startSeq, Integer endSeq, Integer count, Boolean includeOld, String sorting) throws ApiException {
        ApiResponse<Object> localVarResp = privateGetUserTradesByInstrumentGetWithHttpInfo(instrumentName, startSeq, endSeq, count, includeOld, sorting);
        return localVarResp.getData();
    }

    /**
     * Retrieve the latest user trades that have occurred for a specific instrument.
     * 
     * @param instrumentName Instrument name (required)
     * @param startSeq The sequence number of the first trade to be returned (optional)
     * @param endSeq The sequence number of the last trade to be returned (optional)
     * @param count Number of requested items, default - &#x60;10&#x60; (optional)
     * @param includeOld Include trades older than 7 days, default - &#x60;false&#x60; (optional)
     * @param sorting Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) (optional)
     * @return ApiResponse&lt;Object&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<Object> privateGetUserTradesByInstrumentGetWithHttpInfo(String instrumentName, Integer startSeq, Integer endSeq, Integer count, Boolean includeOld, String sorting) throws ApiException {
        okhttp3.Call localVarCall = privateGetUserTradesByInstrumentGetValidateBeforeCall(instrumentName, startSeq, endSeq, count, includeOld, sorting, null);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Retrieve the latest user trades that have occurred for a specific instrument. (asynchronously)
     * 
     * @param instrumentName Instrument name (required)
     * @param startSeq The sequence number of the first trade to be returned (optional)
     * @param endSeq The sequence number of the last trade to be returned (optional)
     * @param count Number of requested items, default - &#x60;10&#x60; (optional)
     * @param includeOld Include trades older than 7 days, default - &#x60;false&#x60; (optional)
     * @param sorting Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) (optional)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call privateGetUserTradesByInstrumentGetAsync(String instrumentName, Integer startSeq, Integer endSeq, Integer count, Boolean includeOld, String sorting, final ApiCallback<Object> _callback) throws ApiException {

        okhttp3.Call localVarCall = privateGetUserTradesByInstrumentGetValidateBeforeCall(instrumentName, startSeq, endSeq, count, includeOld, sorting, _callback);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for privateGetUserTradesByOrderGet
     * @param orderId The order id (required)
     * @param sorting Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) (optional)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call privateGetUserTradesByOrderGetCall(String orderId, String sorting, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = new Object();

        // create path and map variables
        String localVarPath = "/private/get_user_trades_by_order";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        if (orderId != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("order_id", orderId));
        }

        if (sorting != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("sorting", sorting));
        }

        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();
        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
            
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        String[] localVarAuthNames = new String[] { "bearerAuth" };
        return localVarApiClient.buildCall(localVarPath, "GET", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call privateGetUserTradesByOrderGetValidateBeforeCall(String orderId, String sorting, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'orderId' is set
        if (orderId == null) {
            throw new ApiException("Missing the required parameter 'orderId' when calling privateGetUserTradesByOrderGet(Async)");
        }
        

        okhttp3.Call localVarCall = privateGetUserTradesByOrderGetCall(orderId, sorting, _callback);
        return localVarCall;

    }

    /**
     * Retrieve the list of user trades that was created for given order
     * 
     * @param orderId The order id (required)
     * @param sorting Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) (optional)
     * @return Object
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public Object privateGetUserTradesByOrderGet(String orderId, String sorting) throws ApiException {
        ApiResponse<Object> localVarResp = privateGetUserTradesByOrderGetWithHttpInfo(orderId, sorting);
        return localVarResp.getData();
    }

    /**
     * Retrieve the list of user trades that was created for given order
     * 
     * @param orderId The order id (required)
     * @param sorting Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) (optional)
     * @return ApiResponse&lt;Object&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<Object> privateGetUserTradesByOrderGetWithHttpInfo(String orderId, String sorting) throws ApiException {
        okhttp3.Call localVarCall = privateGetUserTradesByOrderGetValidateBeforeCall(orderId, sorting, null);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Retrieve the list of user trades that was created for given order (asynchronously)
     * 
     * @param orderId The order id (required)
     * @param sorting Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) (optional)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call privateGetUserTradesByOrderGetAsync(String orderId, String sorting, final ApiCallback<Object> _callback) throws ApiException {

        okhttp3.Call localVarCall = privateGetUserTradesByOrderGetValidateBeforeCall(orderId, sorting, _callback);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for privateSellGet
     * @param instrumentName Instrument name (required)
     * @param amount It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH (required)
     * @param type The order type, default: &#x60;\&quot;limit\&quot;&#x60; (optional)
     * @param label user defined label for the order (maximum 32 characters) (optional)
     * @param price &lt;p&gt;The order price in base currency (Only for limit and stop_limit orders)&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;usd, the field price should be the option price value in USD.&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;implv, the field price should be a value of implied volatility in percentages. For example,  price&#x3D;100, means implied volatility of 100%&lt;/p&gt; (optional)
     * @param timeInForce &lt;p&gt;Specifies how long the order remains in effect. Default &#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; &lt;ul&gt; &lt;li&gt;&#x60;\&quot;good_til_cancelled\&quot;&#x60; - unfilled order remains in order book until cancelled&lt;/li&gt; &lt;li&gt;&#x60;\&quot;fill_or_kill\&quot;&#x60; - execute a transaction immediately and completely or not at all&lt;/li&gt; &lt;li&gt;&#x60;\&quot;immediate_or_cancel\&quot;&#x60; - execute a transaction immediately, and any portion of the order that cannot be immediately filled is cancelled&lt;/li&gt; &lt;/ul&gt; (optional, default to good_til_cancelled)
     * @param maxShow Maximum amount within an order to be shown to other customers, &#x60;0&#x60; for invisible order (optional, default to 1d)
     * @param postOnly &lt;p&gt;If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.&lt;/p&gt; &lt;p&gt;Only valid in combination with time_in_force&#x3D;&#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; (optional, default to true)
     * @param reduceOnly If &#x60;true&#x60;, the order is considered reduce-only which is intended to only reduce a current position (optional, default to false)
     * @param stopPrice Stop price, required for stop limit orders (Only for stop orders) (optional)
     * @param trigger Defines trigger type, required for &#x60;\&quot;stop_limit\&quot;&#x60; order type (optional)
     * @param advanced Advanced option order type. (Only for options) (optional)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> ok response </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call privateSellGetCall(String instrumentName, BigDecimal amount, String type, String label, BigDecimal price, String timeInForce, BigDecimal maxShow, Boolean postOnly, Boolean reduceOnly, BigDecimal stopPrice, String trigger, String advanced, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = new Object();

        // create path and map variables
        String localVarPath = "/private/sell";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        if (instrumentName != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("instrument_name", instrumentName));
        }

        if (amount != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("amount", amount));
        }

        if (type != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("type", type));
        }

        if (label != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("label", label));
        }

        if (price != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("price", price));
        }

        if (timeInForce != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("time_in_force", timeInForce));
        }

        if (maxShow != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("max_show", maxShow));
        }

        if (postOnly != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("post_only", postOnly));
        }

        if (reduceOnly != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("reduce_only", reduceOnly));
        }

        if (stopPrice != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("stop_price", stopPrice));
        }

        if (trigger != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("trigger", trigger));
        }

        if (advanced != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("advanced", advanced));
        }

        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();
        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
            
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        String[] localVarAuthNames = new String[] { "bearerAuth" };
        return localVarApiClient.buildCall(localVarPath, "GET", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call privateSellGetValidateBeforeCall(String instrumentName, BigDecimal amount, String type, String label, BigDecimal price, String timeInForce, BigDecimal maxShow, Boolean postOnly, Boolean reduceOnly, BigDecimal stopPrice, String trigger, String advanced, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'instrumentName' is set
        if (instrumentName == null) {
            throw new ApiException("Missing the required parameter 'instrumentName' when calling privateSellGet(Async)");
        }
        
        // verify the required parameter 'amount' is set
        if (amount == null) {
            throw new ApiException("Missing the required parameter 'amount' when calling privateSellGet(Async)");
        }
        

        okhttp3.Call localVarCall = privateSellGetCall(instrumentName, amount, type, label, price, timeInForce, maxShow, postOnly, reduceOnly, stopPrice, trigger, advanced, _callback);
        return localVarCall;

    }

    /**
     * Places a sell order for an instrument.
     * 
     * @param instrumentName Instrument name (required)
     * @param amount It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH (required)
     * @param type The order type, default: &#x60;\&quot;limit\&quot;&#x60; (optional)
     * @param label user defined label for the order (maximum 32 characters) (optional)
     * @param price &lt;p&gt;The order price in base currency (Only for limit and stop_limit orders)&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;usd, the field price should be the option price value in USD.&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;implv, the field price should be a value of implied volatility in percentages. For example,  price&#x3D;100, means implied volatility of 100%&lt;/p&gt; (optional)
     * @param timeInForce &lt;p&gt;Specifies how long the order remains in effect. Default &#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; &lt;ul&gt; &lt;li&gt;&#x60;\&quot;good_til_cancelled\&quot;&#x60; - unfilled order remains in order book until cancelled&lt;/li&gt; &lt;li&gt;&#x60;\&quot;fill_or_kill\&quot;&#x60; - execute a transaction immediately and completely or not at all&lt;/li&gt; &lt;li&gt;&#x60;\&quot;immediate_or_cancel\&quot;&#x60; - execute a transaction immediately, and any portion of the order that cannot be immediately filled is cancelled&lt;/li&gt; &lt;/ul&gt; (optional, default to good_til_cancelled)
     * @param maxShow Maximum amount within an order to be shown to other customers, &#x60;0&#x60; for invisible order (optional, default to 1d)
     * @param postOnly &lt;p&gt;If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.&lt;/p&gt; &lt;p&gt;Only valid in combination with time_in_force&#x3D;&#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; (optional, default to true)
     * @param reduceOnly If &#x60;true&#x60;, the order is considered reduce-only which is intended to only reduce a current position (optional, default to false)
     * @param stopPrice Stop price, required for stop limit orders (Only for stop orders) (optional)
     * @param trigger Defines trigger type, required for &#x60;\&quot;stop_limit\&quot;&#x60; order type (optional)
     * @param advanced Advanced option order type. (Only for options) (optional)
     * @return Object
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> ok response </td><td>  -  </td></tr>
     </table>
     */
    public Object privateSellGet(String instrumentName, BigDecimal amount, String type, String label, BigDecimal price, String timeInForce, BigDecimal maxShow, Boolean postOnly, Boolean reduceOnly, BigDecimal stopPrice, String trigger, String advanced) throws ApiException {
        ApiResponse<Object> localVarResp = privateSellGetWithHttpInfo(instrumentName, amount, type, label, price, timeInForce, maxShow, postOnly, reduceOnly, stopPrice, trigger, advanced);
        return localVarResp.getData();
    }

    /**
     * Places a sell order for an instrument.
     * 
     * @param instrumentName Instrument name (required)
     * @param amount It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH (required)
     * @param type The order type, default: &#x60;\&quot;limit\&quot;&#x60; (optional)
     * @param label user defined label for the order (maximum 32 characters) (optional)
     * @param price &lt;p&gt;The order price in base currency (Only for limit and stop_limit orders)&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;usd, the field price should be the option price value in USD.&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;implv, the field price should be a value of implied volatility in percentages. For example,  price&#x3D;100, means implied volatility of 100%&lt;/p&gt; (optional)
     * @param timeInForce &lt;p&gt;Specifies how long the order remains in effect. Default &#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; &lt;ul&gt; &lt;li&gt;&#x60;\&quot;good_til_cancelled\&quot;&#x60; - unfilled order remains in order book until cancelled&lt;/li&gt; &lt;li&gt;&#x60;\&quot;fill_or_kill\&quot;&#x60; - execute a transaction immediately and completely or not at all&lt;/li&gt; &lt;li&gt;&#x60;\&quot;immediate_or_cancel\&quot;&#x60; - execute a transaction immediately, and any portion of the order that cannot be immediately filled is cancelled&lt;/li&gt; &lt;/ul&gt; (optional, default to good_til_cancelled)
     * @param maxShow Maximum amount within an order to be shown to other customers, &#x60;0&#x60; for invisible order (optional, default to 1d)
     * @param postOnly &lt;p&gt;If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.&lt;/p&gt; &lt;p&gt;Only valid in combination with time_in_force&#x3D;&#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; (optional, default to true)
     * @param reduceOnly If &#x60;true&#x60;, the order is considered reduce-only which is intended to only reduce a current position (optional, default to false)
     * @param stopPrice Stop price, required for stop limit orders (Only for stop orders) (optional)
     * @param trigger Defines trigger type, required for &#x60;\&quot;stop_limit\&quot;&#x60; order type (optional)
     * @param advanced Advanced option order type. (Only for options) (optional)
     * @return ApiResponse&lt;Object&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> ok response </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<Object> privateSellGetWithHttpInfo(String instrumentName, BigDecimal amount, String type, String label, BigDecimal price, String timeInForce, BigDecimal maxShow, Boolean postOnly, Boolean reduceOnly, BigDecimal stopPrice, String trigger, String advanced) throws ApiException {
        okhttp3.Call localVarCall = privateSellGetValidateBeforeCall(instrumentName, amount, type, label, price, timeInForce, maxShow, postOnly, reduceOnly, stopPrice, trigger, advanced, null);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Places a sell order for an instrument. (asynchronously)
     * 
     * @param instrumentName Instrument name (required)
     * @param amount It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH (required)
     * @param type The order type, default: &#x60;\&quot;limit\&quot;&#x60; (optional)
     * @param label user defined label for the order (maximum 32 characters) (optional)
     * @param price &lt;p&gt;The order price in base currency (Only for limit and stop_limit orders)&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;usd, the field price should be the option price value in USD.&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;implv, the field price should be a value of implied volatility in percentages. For example,  price&#x3D;100, means implied volatility of 100%&lt;/p&gt; (optional)
     * @param timeInForce &lt;p&gt;Specifies how long the order remains in effect. Default &#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; &lt;ul&gt; &lt;li&gt;&#x60;\&quot;good_til_cancelled\&quot;&#x60; - unfilled order remains in order book until cancelled&lt;/li&gt; &lt;li&gt;&#x60;\&quot;fill_or_kill\&quot;&#x60; - execute a transaction immediately and completely or not at all&lt;/li&gt; &lt;li&gt;&#x60;\&quot;immediate_or_cancel\&quot;&#x60; - execute a transaction immediately, and any portion of the order that cannot be immediately filled is cancelled&lt;/li&gt; &lt;/ul&gt; (optional, default to good_til_cancelled)
     * @param maxShow Maximum amount within an order to be shown to other customers, &#x60;0&#x60; for invisible order (optional, default to 1d)
     * @param postOnly &lt;p&gt;If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.&lt;/p&gt; &lt;p&gt;Only valid in combination with time_in_force&#x3D;&#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; (optional, default to true)
     * @param reduceOnly If &#x60;true&#x60;, the order is considered reduce-only which is intended to only reduce a current position (optional, default to false)
     * @param stopPrice Stop price, required for stop limit orders (Only for stop orders) (optional)
     * @param trigger Defines trigger type, required for &#x60;\&quot;stop_limit\&quot;&#x60; order type (optional)
     * @param advanced Advanced option order type. (Only for options) (optional)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> ok response </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call privateSellGetAsync(String instrumentName, BigDecimal amount, String type, String label, BigDecimal price, String timeInForce, BigDecimal maxShow, Boolean postOnly, Boolean reduceOnly, BigDecimal stopPrice, String trigger, String advanced, final ApiCallback<Object> _callback) throws ApiException {

        okhttp3.Call localVarCall = privateSellGetValidateBeforeCall(instrumentName, amount, type, label, price, timeInForce, maxShow, postOnly, reduceOnly, stopPrice, trigger, advanced, _callback);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
}
