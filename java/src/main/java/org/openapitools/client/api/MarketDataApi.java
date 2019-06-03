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

public class MarketDataApi {
    private ApiClient localVarApiClient;

    public MarketDataApi() {
        this(Configuration.getDefaultApiClient());
    }

    public MarketDataApi(ApiClient apiClient) {
        this.localVarApiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return localVarApiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.localVarApiClient = apiClient;
    }

    /**
     * Build call for publicGetBookSummaryByCurrencyGet
     * @param currency The currency symbol (required)
     * @param kind Instrument kind, if not provided instruments of all kinds are considered (optional)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call publicGetBookSummaryByCurrencyGetCall(String currency, String kind, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = new Object();

        // create path and map variables
        String localVarPath = "/public/get_book_summary_by_currency";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        if (currency != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("currency", currency));
        }

        if (kind != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("kind", kind));
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
    private okhttp3.Call publicGetBookSummaryByCurrencyGetValidateBeforeCall(String currency, String kind, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'currency' is set
        if (currency == null) {
            throw new ApiException("Missing the required parameter 'currency' when calling publicGetBookSummaryByCurrencyGet(Async)");
        }
        

        okhttp3.Call localVarCall = publicGetBookSummaryByCurrencyGetCall(currency, kind, _callback);
        return localVarCall;

    }

    /**
     * Retrieves the summary information such as open interest, 24h volume, etc. for all instruments for the currency (optionally filtered by kind).
     * 
     * @param currency The currency symbol (required)
     * @param kind Instrument kind, if not provided instruments of all kinds are considered (optional)
     * @return Object
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public Object publicGetBookSummaryByCurrencyGet(String currency, String kind) throws ApiException {
        ApiResponse<Object> localVarResp = publicGetBookSummaryByCurrencyGetWithHttpInfo(currency, kind);
        return localVarResp.getData();
    }

    /**
     * Retrieves the summary information such as open interest, 24h volume, etc. for all instruments for the currency (optionally filtered by kind).
     * 
     * @param currency The currency symbol (required)
     * @param kind Instrument kind, if not provided instruments of all kinds are considered (optional)
     * @return ApiResponse&lt;Object&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<Object> publicGetBookSummaryByCurrencyGetWithHttpInfo(String currency, String kind) throws ApiException {
        okhttp3.Call localVarCall = publicGetBookSummaryByCurrencyGetValidateBeforeCall(currency, kind, null);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Retrieves the summary information such as open interest, 24h volume, etc. for all instruments for the currency (optionally filtered by kind). (asynchronously)
     * 
     * @param currency The currency symbol (required)
     * @param kind Instrument kind, if not provided instruments of all kinds are considered (optional)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call publicGetBookSummaryByCurrencyGetAsync(String currency, String kind, final ApiCallback<Object> _callback) throws ApiException {

        okhttp3.Call localVarCall = publicGetBookSummaryByCurrencyGetValidateBeforeCall(currency, kind, _callback);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for publicGetBookSummaryByInstrumentGet
     * @param instrumentName Instrument name (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call publicGetBookSummaryByInstrumentGetCall(String instrumentName, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = new Object();

        // create path and map variables
        String localVarPath = "/public/get_book_summary_by_instrument";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        if (instrumentName != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("instrument_name", instrumentName));
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
    private okhttp3.Call publicGetBookSummaryByInstrumentGetValidateBeforeCall(String instrumentName, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'instrumentName' is set
        if (instrumentName == null) {
            throw new ApiException("Missing the required parameter 'instrumentName' when calling publicGetBookSummaryByInstrumentGet(Async)");
        }
        

        okhttp3.Call localVarCall = publicGetBookSummaryByInstrumentGetCall(instrumentName, _callback);
        return localVarCall;

    }

    /**
     * Retrieves the summary information such as open interest, 24h volume, etc. for a specific instrument.
     * 
     * @param instrumentName Instrument name (required)
     * @return Object
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public Object publicGetBookSummaryByInstrumentGet(String instrumentName) throws ApiException {
        ApiResponse<Object> localVarResp = publicGetBookSummaryByInstrumentGetWithHttpInfo(instrumentName);
        return localVarResp.getData();
    }

    /**
     * Retrieves the summary information such as open interest, 24h volume, etc. for a specific instrument.
     * 
     * @param instrumentName Instrument name (required)
     * @return ApiResponse&lt;Object&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<Object> publicGetBookSummaryByInstrumentGetWithHttpInfo(String instrumentName) throws ApiException {
        okhttp3.Call localVarCall = publicGetBookSummaryByInstrumentGetValidateBeforeCall(instrumentName, null);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Retrieves the summary information such as open interest, 24h volume, etc. for a specific instrument. (asynchronously)
     * 
     * @param instrumentName Instrument name (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call publicGetBookSummaryByInstrumentGetAsync(String instrumentName, final ApiCallback<Object> _callback) throws ApiException {

        okhttp3.Call localVarCall = publicGetBookSummaryByInstrumentGetValidateBeforeCall(instrumentName, _callback);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for publicGetContractSizeGet
     * @param instrumentName Instrument name (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> ok response </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call publicGetContractSizeGetCall(String instrumentName, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = new Object();

        // create path and map variables
        String localVarPath = "/public/get_contract_size";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        if (instrumentName != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("instrument_name", instrumentName));
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
    private okhttp3.Call publicGetContractSizeGetValidateBeforeCall(String instrumentName, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'instrumentName' is set
        if (instrumentName == null) {
            throw new ApiException("Missing the required parameter 'instrumentName' when calling publicGetContractSizeGet(Async)");
        }
        

        okhttp3.Call localVarCall = publicGetContractSizeGetCall(instrumentName, _callback);
        return localVarCall;

    }

    /**
     * Retrieves contract size of provided instrument.
     * 
     * @param instrumentName Instrument name (required)
     * @return Object
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> ok response </td><td>  -  </td></tr>
     </table>
     */
    public Object publicGetContractSizeGet(String instrumentName) throws ApiException {
        ApiResponse<Object> localVarResp = publicGetContractSizeGetWithHttpInfo(instrumentName);
        return localVarResp.getData();
    }

    /**
     * Retrieves contract size of provided instrument.
     * 
     * @param instrumentName Instrument name (required)
     * @return ApiResponse&lt;Object&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> ok response </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<Object> publicGetContractSizeGetWithHttpInfo(String instrumentName) throws ApiException {
        okhttp3.Call localVarCall = publicGetContractSizeGetValidateBeforeCall(instrumentName, null);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Retrieves contract size of provided instrument. (asynchronously)
     * 
     * @param instrumentName Instrument name (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> ok response </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call publicGetContractSizeGetAsync(String instrumentName, final ApiCallback<Object> _callback) throws ApiException {

        okhttp3.Call localVarCall = publicGetContractSizeGetValidateBeforeCall(instrumentName, _callback);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for publicGetCurrenciesGet
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> ok response </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call publicGetCurrenciesGetCall(final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = new Object();

        // create path and map variables
        String localVarPath = "/public/get_currencies";

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
    private okhttp3.Call publicGetCurrenciesGetValidateBeforeCall(final ApiCallback _callback) throws ApiException {
        

        okhttp3.Call localVarCall = publicGetCurrenciesGetCall(_callback);
        return localVarCall;

    }

    /**
     * Retrieves all cryptocurrencies supported by the API.
     * 
     * @return Object
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> ok response </td><td>  -  </td></tr>
     </table>
     */
    public Object publicGetCurrenciesGet() throws ApiException {
        ApiResponse<Object> localVarResp = publicGetCurrenciesGetWithHttpInfo();
        return localVarResp.getData();
    }

    /**
     * Retrieves all cryptocurrencies supported by the API.
     * 
     * @return ApiResponse&lt;Object&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> ok response </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<Object> publicGetCurrenciesGetWithHttpInfo() throws ApiException {
        okhttp3.Call localVarCall = publicGetCurrenciesGetValidateBeforeCall(null);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Retrieves all cryptocurrencies supported by the API. (asynchronously)
     * 
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> ok response </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call publicGetCurrenciesGetAsync(final ApiCallback<Object> _callback) throws ApiException {

        okhttp3.Call localVarCall = publicGetCurrenciesGetValidateBeforeCall(_callback);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for publicGetFundingChartDataGet
     * @param instrumentName Instrument name (required)
     * @param length Specifies time period. &#x60;8h&#x60; - 8 hours, &#x60;24h&#x60; - 24 hours (optional)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call publicGetFundingChartDataGetCall(String instrumentName, String length, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = new Object();

        // create path and map variables
        String localVarPath = "/public/get_funding_chart_data";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        if (instrumentName != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("instrument_name", instrumentName));
        }

        if (length != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("length", length));
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
    private okhttp3.Call publicGetFundingChartDataGetValidateBeforeCall(String instrumentName, String length, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'instrumentName' is set
        if (instrumentName == null) {
            throw new ApiException("Missing the required parameter 'instrumentName' when calling publicGetFundingChartDataGet(Async)");
        }
        

        okhttp3.Call localVarCall = publicGetFundingChartDataGetCall(instrumentName, length, _callback);
        return localVarCall;

    }

    /**
     * Retrieve the latest user trades that have occurred for PERPETUAL instruments in a specific currency symbol and within given time range.
     * 
     * @param instrumentName Instrument name (required)
     * @param length Specifies time period. &#x60;8h&#x60; - 8 hours, &#x60;24h&#x60; - 24 hours (optional)
     * @return Object
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public Object publicGetFundingChartDataGet(String instrumentName, String length) throws ApiException {
        ApiResponse<Object> localVarResp = publicGetFundingChartDataGetWithHttpInfo(instrumentName, length);
        return localVarResp.getData();
    }

    /**
     * Retrieve the latest user trades that have occurred for PERPETUAL instruments in a specific currency symbol and within given time range.
     * 
     * @param instrumentName Instrument name (required)
     * @param length Specifies time period. &#x60;8h&#x60; - 8 hours, &#x60;24h&#x60; - 24 hours (optional)
     * @return ApiResponse&lt;Object&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<Object> publicGetFundingChartDataGetWithHttpInfo(String instrumentName, String length) throws ApiException {
        okhttp3.Call localVarCall = publicGetFundingChartDataGetValidateBeforeCall(instrumentName, length, null);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Retrieve the latest user trades that have occurred for PERPETUAL instruments in a specific currency symbol and within given time range. (asynchronously)
     * 
     * @param instrumentName Instrument name (required)
     * @param length Specifies time period. &#x60;8h&#x60; - 8 hours, &#x60;24h&#x60; - 24 hours (optional)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call publicGetFundingChartDataGetAsync(String instrumentName, String length, final ApiCallback<Object> _callback) throws ApiException {

        okhttp3.Call localVarCall = publicGetFundingChartDataGetValidateBeforeCall(instrumentName, length, _callback);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for publicGetHistoricalVolatilityGet
     * @param currency The currency symbol (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> ok response </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call publicGetHistoricalVolatilityGetCall(String currency, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = new Object();

        // create path and map variables
        String localVarPath = "/public/get_historical_volatility";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        if (currency != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("currency", currency));
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
    private okhttp3.Call publicGetHistoricalVolatilityGetValidateBeforeCall(String currency, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'currency' is set
        if (currency == null) {
            throw new ApiException("Missing the required parameter 'currency' when calling publicGetHistoricalVolatilityGet(Async)");
        }
        

        okhttp3.Call localVarCall = publicGetHistoricalVolatilityGetCall(currency, _callback);
        return localVarCall;

    }

    /**
     * Provides information about historical volatility for given cryptocurrency.
     * 
     * @param currency The currency symbol (required)
     * @return Object
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> ok response </td><td>  -  </td></tr>
     </table>
     */
    public Object publicGetHistoricalVolatilityGet(String currency) throws ApiException {
        ApiResponse<Object> localVarResp = publicGetHistoricalVolatilityGetWithHttpInfo(currency);
        return localVarResp.getData();
    }

    /**
     * Provides information about historical volatility for given cryptocurrency.
     * 
     * @param currency The currency symbol (required)
     * @return ApiResponse&lt;Object&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> ok response </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<Object> publicGetHistoricalVolatilityGetWithHttpInfo(String currency) throws ApiException {
        okhttp3.Call localVarCall = publicGetHistoricalVolatilityGetValidateBeforeCall(currency, null);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Provides information about historical volatility for given cryptocurrency. (asynchronously)
     * 
     * @param currency The currency symbol (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> ok response </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call publicGetHistoricalVolatilityGetAsync(String currency, final ApiCallback<Object> _callback) throws ApiException {

        okhttp3.Call localVarCall = publicGetHistoricalVolatilityGetValidateBeforeCall(currency, _callback);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for publicGetIndexGet
     * @param currency The currency symbol (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call publicGetIndexGetCall(String currency, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = new Object();

        // create path and map variables
        String localVarPath = "/public/get_index";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        if (currency != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("currency", currency));
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
    private okhttp3.Call publicGetIndexGetValidateBeforeCall(String currency, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'currency' is set
        if (currency == null) {
            throw new ApiException("Missing the required parameter 'currency' when calling publicGetIndexGet(Async)");
        }
        

        okhttp3.Call localVarCall = publicGetIndexGetCall(currency, _callback);
        return localVarCall;

    }

    /**
     * Retrieves the current index price for the instruments, for the selected currency.
     * 
     * @param currency The currency symbol (required)
     * @return Object
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public Object publicGetIndexGet(String currency) throws ApiException {
        ApiResponse<Object> localVarResp = publicGetIndexGetWithHttpInfo(currency);
        return localVarResp.getData();
    }

    /**
     * Retrieves the current index price for the instruments, for the selected currency.
     * 
     * @param currency The currency symbol (required)
     * @return ApiResponse&lt;Object&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<Object> publicGetIndexGetWithHttpInfo(String currency) throws ApiException {
        okhttp3.Call localVarCall = publicGetIndexGetValidateBeforeCall(currency, null);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Retrieves the current index price for the instruments, for the selected currency. (asynchronously)
     * 
     * @param currency The currency symbol (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call publicGetIndexGetAsync(String currency, final ApiCallback<Object> _callback) throws ApiException {

        okhttp3.Call localVarCall = publicGetIndexGetValidateBeforeCall(currency, _callback);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for publicGetInstrumentsGet
     * @param currency The currency symbol (required)
     * @param kind Instrument kind, if not provided instruments of all kinds are considered (optional)
     * @param expired Set to true to show expired instruments instead of active ones. (optional, default to false)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> ok response </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call publicGetInstrumentsGetCall(String currency, String kind, Boolean expired, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = new Object();

        // create path and map variables
        String localVarPath = "/public/get_instruments";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        if (currency != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("currency", currency));
        }

        if (kind != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("kind", kind));
        }

        if (expired != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("expired", expired));
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
    private okhttp3.Call publicGetInstrumentsGetValidateBeforeCall(String currency, String kind, Boolean expired, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'currency' is set
        if (currency == null) {
            throw new ApiException("Missing the required parameter 'currency' when calling publicGetInstrumentsGet(Async)");
        }
        

        okhttp3.Call localVarCall = publicGetInstrumentsGetCall(currency, kind, expired, _callback);
        return localVarCall;

    }

    /**
     * Retrieves available trading instruments. This method can be used to see which instruments are available for trading, or which instruments have existed historically.
     * 
     * @param currency The currency symbol (required)
     * @param kind Instrument kind, if not provided instruments of all kinds are considered (optional)
     * @param expired Set to true to show expired instruments instead of active ones. (optional, default to false)
     * @return Object
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> ok response </td><td>  -  </td></tr>
     </table>
     */
    public Object publicGetInstrumentsGet(String currency, String kind, Boolean expired) throws ApiException {
        ApiResponse<Object> localVarResp = publicGetInstrumentsGetWithHttpInfo(currency, kind, expired);
        return localVarResp.getData();
    }

    /**
     * Retrieves available trading instruments. This method can be used to see which instruments are available for trading, or which instruments have existed historically.
     * 
     * @param currency The currency symbol (required)
     * @param kind Instrument kind, if not provided instruments of all kinds are considered (optional)
     * @param expired Set to true to show expired instruments instead of active ones. (optional, default to false)
     * @return ApiResponse&lt;Object&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> ok response </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<Object> publicGetInstrumentsGetWithHttpInfo(String currency, String kind, Boolean expired) throws ApiException {
        okhttp3.Call localVarCall = publicGetInstrumentsGetValidateBeforeCall(currency, kind, expired, null);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Retrieves available trading instruments. This method can be used to see which instruments are available for trading, or which instruments have existed historically. (asynchronously)
     * 
     * @param currency The currency symbol (required)
     * @param kind Instrument kind, if not provided instruments of all kinds are considered (optional)
     * @param expired Set to true to show expired instruments instead of active ones. (optional, default to false)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> ok response </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call publicGetInstrumentsGetAsync(String currency, String kind, Boolean expired, final ApiCallback<Object> _callback) throws ApiException {

        okhttp3.Call localVarCall = publicGetInstrumentsGetValidateBeforeCall(currency, kind, expired, _callback);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for publicGetLastSettlementsByCurrencyGet
     * @param currency The currency symbol (required)
     * @param type Settlement type (optional)
     * @param count Number of requested items, default - &#x60;20&#x60; (optional)
     * @param continuation Continuation token for pagination (optional)
     * @param searchStartTimestamp The latest timestamp to return result for (optional)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call publicGetLastSettlementsByCurrencyGetCall(String currency, String type, Integer count, String continuation, Integer searchStartTimestamp, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = new Object();

        // create path and map variables
        String localVarPath = "/public/get_last_settlements_by_currency";

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

        if (continuation != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("continuation", continuation));
        }

        if (searchStartTimestamp != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("search_start_timestamp", searchStartTimestamp));
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
    private okhttp3.Call publicGetLastSettlementsByCurrencyGetValidateBeforeCall(String currency, String type, Integer count, String continuation, Integer searchStartTimestamp, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'currency' is set
        if (currency == null) {
            throw new ApiException("Missing the required parameter 'currency' when calling publicGetLastSettlementsByCurrencyGet(Async)");
        }
        

        okhttp3.Call localVarCall = publicGetLastSettlementsByCurrencyGetCall(currency, type, count, continuation, searchStartTimestamp, _callback);
        return localVarCall;

    }

    /**
     * Retrieves historical settlement, delivery and bankruptcy events coming from all instruments within given currency.
     * 
     * @param currency The currency symbol (required)
     * @param type Settlement type (optional)
     * @param count Number of requested items, default - &#x60;20&#x60; (optional)
     * @param continuation Continuation token for pagination (optional)
     * @param searchStartTimestamp The latest timestamp to return result for (optional)
     * @return Object
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public Object publicGetLastSettlementsByCurrencyGet(String currency, String type, Integer count, String continuation, Integer searchStartTimestamp) throws ApiException {
        ApiResponse<Object> localVarResp = publicGetLastSettlementsByCurrencyGetWithHttpInfo(currency, type, count, continuation, searchStartTimestamp);
        return localVarResp.getData();
    }

    /**
     * Retrieves historical settlement, delivery and bankruptcy events coming from all instruments within given currency.
     * 
     * @param currency The currency symbol (required)
     * @param type Settlement type (optional)
     * @param count Number of requested items, default - &#x60;20&#x60; (optional)
     * @param continuation Continuation token for pagination (optional)
     * @param searchStartTimestamp The latest timestamp to return result for (optional)
     * @return ApiResponse&lt;Object&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<Object> publicGetLastSettlementsByCurrencyGetWithHttpInfo(String currency, String type, Integer count, String continuation, Integer searchStartTimestamp) throws ApiException {
        okhttp3.Call localVarCall = publicGetLastSettlementsByCurrencyGetValidateBeforeCall(currency, type, count, continuation, searchStartTimestamp, null);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Retrieves historical settlement, delivery and bankruptcy events coming from all instruments within given currency. (asynchronously)
     * 
     * @param currency The currency symbol (required)
     * @param type Settlement type (optional)
     * @param count Number of requested items, default - &#x60;20&#x60; (optional)
     * @param continuation Continuation token for pagination (optional)
     * @param searchStartTimestamp The latest timestamp to return result for (optional)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call publicGetLastSettlementsByCurrencyGetAsync(String currency, String type, Integer count, String continuation, Integer searchStartTimestamp, final ApiCallback<Object> _callback) throws ApiException {

        okhttp3.Call localVarCall = publicGetLastSettlementsByCurrencyGetValidateBeforeCall(currency, type, count, continuation, searchStartTimestamp, _callback);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for publicGetLastSettlementsByInstrumentGet
     * @param instrumentName Instrument name (required)
     * @param type Settlement type (optional)
     * @param count Number of requested items, default - &#x60;20&#x60; (optional)
     * @param continuation Continuation token for pagination (optional)
     * @param searchStartTimestamp The latest timestamp to return result for (optional)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call publicGetLastSettlementsByInstrumentGetCall(String instrumentName, String type, Integer count, String continuation, Integer searchStartTimestamp, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = new Object();

        // create path and map variables
        String localVarPath = "/public/get_last_settlements_by_instrument";

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

        if (continuation != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("continuation", continuation));
        }

        if (searchStartTimestamp != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("search_start_timestamp", searchStartTimestamp));
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
    private okhttp3.Call publicGetLastSettlementsByInstrumentGetValidateBeforeCall(String instrumentName, String type, Integer count, String continuation, Integer searchStartTimestamp, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'instrumentName' is set
        if (instrumentName == null) {
            throw new ApiException("Missing the required parameter 'instrumentName' when calling publicGetLastSettlementsByInstrumentGet(Async)");
        }
        

        okhttp3.Call localVarCall = publicGetLastSettlementsByInstrumentGetCall(instrumentName, type, count, continuation, searchStartTimestamp, _callback);
        return localVarCall;

    }

    /**
     * Retrieves historical public settlement, delivery and bankruptcy events filtered by instrument name.
     * 
     * @param instrumentName Instrument name (required)
     * @param type Settlement type (optional)
     * @param count Number of requested items, default - &#x60;20&#x60; (optional)
     * @param continuation Continuation token for pagination (optional)
     * @param searchStartTimestamp The latest timestamp to return result for (optional)
     * @return Object
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public Object publicGetLastSettlementsByInstrumentGet(String instrumentName, String type, Integer count, String continuation, Integer searchStartTimestamp) throws ApiException {
        ApiResponse<Object> localVarResp = publicGetLastSettlementsByInstrumentGetWithHttpInfo(instrumentName, type, count, continuation, searchStartTimestamp);
        return localVarResp.getData();
    }

    /**
     * Retrieves historical public settlement, delivery and bankruptcy events filtered by instrument name.
     * 
     * @param instrumentName Instrument name (required)
     * @param type Settlement type (optional)
     * @param count Number of requested items, default - &#x60;20&#x60; (optional)
     * @param continuation Continuation token for pagination (optional)
     * @param searchStartTimestamp The latest timestamp to return result for (optional)
     * @return ApiResponse&lt;Object&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<Object> publicGetLastSettlementsByInstrumentGetWithHttpInfo(String instrumentName, String type, Integer count, String continuation, Integer searchStartTimestamp) throws ApiException {
        okhttp3.Call localVarCall = publicGetLastSettlementsByInstrumentGetValidateBeforeCall(instrumentName, type, count, continuation, searchStartTimestamp, null);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Retrieves historical public settlement, delivery and bankruptcy events filtered by instrument name. (asynchronously)
     * 
     * @param instrumentName Instrument name (required)
     * @param type Settlement type (optional)
     * @param count Number of requested items, default - &#x60;20&#x60; (optional)
     * @param continuation Continuation token for pagination (optional)
     * @param searchStartTimestamp The latest timestamp to return result for (optional)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call publicGetLastSettlementsByInstrumentGetAsync(String instrumentName, String type, Integer count, String continuation, Integer searchStartTimestamp, final ApiCallback<Object> _callback) throws ApiException {

        okhttp3.Call localVarCall = publicGetLastSettlementsByInstrumentGetValidateBeforeCall(instrumentName, type, count, continuation, searchStartTimestamp, _callback);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for publicGetLastTradesByCurrencyAndTimeGet
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
    public okhttp3.Call publicGetLastTradesByCurrencyAndTimeGetCall(String currency, Integer startTimestamp, Integer endTimestamp, String kind, Integer count, Boolean includeOld, String sorting, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = new Object();

        // create path and map variables
        String localVarPath = "/public/get_last_trades_by_currency_and_time";

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
    private okhttp3.Call publicGetLastTradesByCurrencyAndTimeGetValidateBeforeCall(String currency, Integer startTimestamp, Integer endTimestamp, String kind, Integer count, Boolean includeOld, String sorting, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'currency' is set
        if (currency == null) {
            throw new ApiException("Missing the required parameter 'currency' when calling publicGetLastTradesByCurrencyAndTimeGet(Async)");
        }
        
        // verify the required parameter 'startTimestamp' is set
        if (startTimestamp == null) {
            throw new ApiException("Missing the required parameter 'startTimestamp' when calling publicGetLastTradesByCurrencyAndTimeGet(Async)");
        }
        
        // verify the required parameter 'endTimestamp' is set
        if (endTimestamp == null) {
            throw new ApiException("Missing the required parameter 'endTimestamp' when calling publicGetLastTradesByCurrencyAndTimeGet(Async)");
        }
        

        okhttp3.Call localVarCall = publicGetLastTradesByCurrencyAndTimeGetCall(currency, startTimestamp, endTimestamp, kind, count, includeOld, sorting, _callback);
        return localVarCall;

    }

    /**
     * Retrieve the latest trades that have occurred for instruments in a specific currency symbol and within given time range.
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
    public Object publicGetLastTradesByCurrencyAndTimeGet(String currency, Integer startTimestamp, Integer endTimestamp, String kind, Integer count, Boolean includeOld, String sorting) throws ApiException {
        ApiResponse<Object> localVarResp = publicGetLastTradesByCurrencyAndTimeGetWithHttpInfo(currency, startTimestamp, endTimestamp, kind, count, includeOld, sorting);
        return localVarResp.getData();
    }

    /**
     * Retrieve the latest trades that have occurred for instruments in a specific currency symbol and within given time range.
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
    public ApiResponse<Object> publicGetLastTradesByCurrencyAndTimeGetWithHttpInfo(String currency, Integer startTimestamp, Integer endTimestamp, String kind, Integer count, Boolean includeOld, String sorting) throws ApiException {
        okhttp3.Call localVarCall = publicGetLastTradesByCurrencyAndTimeGetValidateBeforeCall(currency, startTimestamp, endTimestamp, kind, count, includeOld, sorting, null);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Retrieve the latest trades that have occurred for instruments in a specific currency symbol and within given time range. (asynchronously)
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
    public okhttp3.Call publicGetLastTradesByCurrencyAndTimeGetAsync(String currency, Integer startTimestamp, Integer endTimestamp, String kind, Integer count, Boolean includeOld, String sorting, final ApiCallback<Object> _callback) throws ApiException {

        okhttp3.Call localVarCall = publicGetLastTradesByCurrencyAndTimeGetValidateBeforeCall(currency, startTimestamp, endTimestamp, kind, count, includeOld, sorting, _callback);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for publicGetLastTradesByCurrencyGet
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
    public okhttp3.Call publicGetLastTradesByCurrencyGetCall(String currency, String kind, String startId, String endId, Integer count, Boolean includeOld, String sorting, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = new Object();

        // create path and map variables
        String localVarPath = "/public/get_last_trades_by_currency";

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
    private okhttp3.Call publicGetLastTradesByCurrencyGetValidateBeforeCall(String currency, String kind, String startId, String endId, Integer count, Boolean includeOld, String sorting, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'currency' is set
        if (currency == null) {
            throw new ApiException("Missing the required parameter 'currency' when calling publicGetLastTradesByCurrencyGet(Async)");
        }
        

        okhttp3.Call localVarCall = publicGetLastTradesByCurrencyGetCall(currency, kind, startId, endId, count, includeOld, sorting, _callback);
        return localVarCall;

    }

    /**
     * Retrieve the latest trades that have occurred for instruments in a specific currency symbol.
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
    public Object publicGetLastTradesByCurrencyGet(String currency, String kind, String startId, String endId, Integer count, Boolean includeOld, String sorting) throws ApiException {
        ApiResponse<Object> localVarResp = publicGetLastTradesByCurrencyGetWithHttpInfo(currency, kind, startId, endId, count, includeOld, sorting);
        return localVarResp.getData();
    }

    /**
     * Retrieve the latest trades that have occurred for instruments in a specific currency symbol.
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
    public ApiResponse<Object> publicGetLastTradesByCurrencyGetWithHttpInfo(String currency, String kind, String startId, String endId, Integer count, Boolean includeOld, String sorting) throws ApiException {
        okhttp3.Call localVarCall = publicGetLastTradesByCurrencyGetValidateBeforeCall(currency, kind, startId, endId, count, includeOld, sorting, null);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Retrieve the latest trades that have occurred for instruments in a specific currency symbol. (asynchronously)
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
    public okhttp3.Call publicGetLastTradesByCurrencyGetAsync(String currency, String kind, String startId, String endId, Integer count, Boolean includeOld, String sorting, final ApiCallback<Object> _callback) throws ApiException {

        okhttp3.Call localVarCall = publicGetLastTradesByCurrencyGetValidateBeforeCall(currency, kind, startId, endId, count, includeOld, sorting, _callback);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for publicGetLastTradesByInstrumentAndTimeGet
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
    public okhttp3.Call publicGetLastTradesByInstrumentAndTimeGetCall(String instrumentName, Integer startTimestamp, Integer endTimestamp, Integer count, Boolean includeOld, String sorting, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = new Object();

        // create path and map variables
        String localVarPath = "/public/get_last_trades_by_instrument_and_time";

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
    private okhttp3.Call publicGetLastTradesByInstrumentAndTimeGetValidateBeforeCall(String instrumentName, Integer startTimestamp, Integer endTimestamp, Integer count, Boolean includeOld, String sorting, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'instrumentName' is set
        if (instrumentName == null) {
            throw new ApiException("Missing the required parameter 'instrumentName' when calling publicGetLastTradesByInstrumentAndTimeGet(Async)");
        }
        
        // verify the required parameter 'startTimestamp' is set
        if (startTimestamp == null) {
            throw new ApiException("Missing the required parameter 'startTimestamp' when calling publicGetLastTradesByInstrumentAndTimeGet(Async)");
        }
        
        // verify the required parameter 'endTimestamp' is set
        if (endTimestamp == null) {
            throw new ApiException("Missing the required parameter 'endTimestamp' when calling publicGetLastTradesByInstrumentAndTimeGet(Async)");
        }
        

        okhttp3.Call localVarCall = publicGetLastTradesByInstrumentAndTimeGetCall(instrumentName, startTimestamp, endTimestamp, count, includeOld, sorting, _callback);
        return localVarCall;

    }

    /**
     * Retrieve the latest trades that have occurred for a specific instrument and within given time range.
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
    public Object publicGetLastTradesByInstrumentAndTimeGet(String instrumentName, Integer startTimestamp, Integer endTimestamp, Integer count, Boolean includeOld, String sorting) throws ApiException {
        ApiResponse<Object> localVarResp = publicGetLastTradesByInstrumentAndTimeGetWithHttpInfo(instrumentName, startTimestamp, endTimestamp, count, includeOld, sorting);
        return localVarResp.getData();
    }

    /**
     * Retrieve the latest trades that have occurred for a specific instrument and within given time range.
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
    public ApiResponse<Object> publicGetLastTradesByInstrumentAndTimeGetWithHttpInfo(String instrumentName, Integer startTimestamp, Integer endTimestamp, Integer count, Boolean includeOld, String sorting) throws ApiException {
        okhttp3.Call localVarCall = publicGetLastTradesByInstrumentAndTimeGetValidateBeforeCall(instrumentName, startTimestamp, endTimestamp, count, includeOld, sorting, null);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Retrieve the latest trades that have occurred for a specific instrument and within given time range. (asynchronously)
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
    public okhttp3.Call publicGetLastTradesByInstrumentAndTimeGetAsync(String instrumentName, Integer startTimestamp, Integer endTimestamp, Integer count, Boolean includeOld, String sorting, final ApiCallback<Object> _callback) throws ApiException {

        okhttp3.Call localVarCall = publicGetLastTradesByInstrumentAndTimeGetValidateBeforeCall(instrumentName, startTimestamp, endTimestamp, count, includeOld, sorting, _callback);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for publicGetLastTradesByInstrumentGet
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
    public okhttp3.Call publicGetLastTradesByInstrumentGetCall(String instrumentName, Integer startSeq, Integer endSeq, Integer count, Boolean includeOld, String sorting, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = new Object();

        // create path and map variables
        String localVarPath = "/public/get_last_trades_by_instrument";

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
    private okhttp3.Call publicGetLastTradesByInstrumentGetValidateBeforeCall(String instrumentName, Integer startSeq, Integer endSeq, Integer count, Boolean includeOld, String sorting, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'instrumentName' is set
        if (instrumentName == null) {
            throw new ApiException("Missing the required parameter 'instrumentName' when calling publicGetLastTradesByInstrumentGet(Async)");
        }
        

        okhttp3.Call localVarCall = publicGetLastTradesByInstrumentGetCall(instrumentName, startSeq, endSeq, count, includeOld, sorting, _callback);
        return localVarCall;

    }

    /**
     * Retrieve the latest trades that have occurred for a specific instrument.
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
    public Object publicGetLastTradesByInstrumentGet(String instrumentName, Integer startSeq, Integer endSeq, Integer count, Boolean includeOld, String sorting) throws ApiException {
        ApiResponse<Object> localVarResp = publicGetLastTradesByInstrumentGetWithHttpInfo(instrumentName, startSeq, endSeq, count, includeOld, sorting);
        return localVarResp.getData();
    }

    /**
     * Retrieve the latest trades that have occurred for a specific instrument.
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
    public ApiResponse<Object> publicGetLastTradesByInstrumentGetWithHttpInfo(String instrumentName, Integer startSeq, Integer endSeq, Integer count, Boolean includeOld, String sorting) throws ApiException {
        okhttp3.Call localVarCall = publicGetLastTradesByInstrumentGetValidateBeforeCall(instrumentName, startSeq, endSeq, count, includeOld, sorting, null);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Retrieve the latest trades that have occurred for a specific instrument. (asynchronously)
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
    public okhttp3.Call publicGetLastTradesByInstrumentGetAsync(String instrumentName, Integer startSeq, Integer endSeq, Integer count, Boolean includeOld, String sorting, final ApiCallback<Object> _callback) throws ApiException {

        okhttp3.Call localVarCall = publicGetLastTradesByInstrumentGetValidateBeforeCall(instrumentName, startSeq, endSeq, count, includeOld, sorting, _callback);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for publicGetOrderBookGet
     * @param instrumentName The instrument name for which to retrieve the order book, see [&#x60;getinstruments&#x60;](#getinstruments) to obtain instrument names. (required)
     * @param depth The number of entries to return for bids and asks. (optional)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call publicGetOrderBookGetCall(String instrumentName, BigDecimal depth, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = new Object();

        // create path and map variables
        String localVarPath = "/public/get_order_book";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        if (instrumentName != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("instrument_name", instrumentName));
        }

        if (depth != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("depth", depth));
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
    private okhttp3.Call publicGetOrderBookGetValidateBeforeCall(String instrumentName, BigDecimal depth, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'instrumentName' is set
        if (instrumentName == null) {
            throw new ApiException("Missing the required parameter 'instrumentName' when calling publicGetOrderBookGet(Async)");
        }
        

        okhttp3.Call localVarCall = publicGetOrderBookGetCall(instrumentName, depth, _callback);
        return localVarCall;

    }

    /**
     * Retrieves the order book, along with other market values for a given instrument.
     * 
     * @param instrumentName The instrument name for which to retrieve the order book, see [&#x60;getinstruments&#x60;](#getinstruments) to obtain instrument names. (required)
     * @param depth The number of entries to return for bids and asks. (optional)
     * @return Object
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public Object publicGetOrderBookGet(String instrumentName, BigDecimal depth) throws ApiException {
        ApiResponse<Object> localVarResp = publicGetOrderBookGetWithHttpInfo(instrumentName, depth);
        return localVarResp.getData();
    }

    /**
     * Retrieves the order book, along with other market values for a given instrument.
     * 
     * @param instrumentName The instrument name for which to retrieve the order book, see [&#x60;getinstruments&#x60;](#getinstruments) to obtain instrument names. (required)
     * @param depth The number of entries to return for bids and asks. (optional)
     * @return ApiResponse&lt;Object&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<Object> publicGetOrderBookGetWithHttpInfo(String instrumentName, BigDecimal depth) throws ApiException {
        okhttp3.Call localVarCall = publicGetOrderBookGetValidateBeforeCall(instrumentName, depth, null);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Retrieves the order book, along with other market values for a given instrument. (asynchronously)
     * 
     * @param instrumentName The instrument name for which to retrieve the order book, see [&#x60;getinstruments&#x60;](#getinstruments) to obtain instrument names. (required)
     * @param depth The number of entries to return for bids and asks. (optional)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call publicGetOrderBookGetAsync(String instrumentName, BigDecimal depth, final ApiCallback<Object> _callback) throws ApiException {

        okhttp3.Call localVarCall = publicGetOrderBookGetValidateBeforeCall(instrumentName, depth, _callback);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for publicGetTradeVolumesGet
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call publicGetTradeVolumesGetCall(final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = new Object();

        // create path and map variables
        String localVarPath = "/public/get_trade_volumes";

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
    private okhttp3.Call publicGetTradeVolumesGetValidateBeforeCall(final ApiCallback _callback) throws ApiException {
        

        okhttp3.Call localVarCall = publicGetTradeVolumesGetCall(_callback);
        return localVarCall;

    }

    /**
     * Retrieves aggregated 24h trade volumes for different instrument types and currencies.
     * 
     * @return Object
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public Object publicGetTradeVolumesGet() throws ApiException {
        ApiResponse<Object> localVarResp = publicGetTradeVolumesGetWithHttpInfo();
        return localVarResp.getData();
    }

    /**
     * Retrieves aggregated 24h trade volumes for different instrument types and currencies.
     * 
     * @return ApiResponse&lt;Object&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<Object> publicGetTradeVolumesGetWithHttpInfo() throws ApiException {
        okhttp3.Call localVarCall = publicGetTradeVolumesGetValidateBeforeCall(null);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Retrieves aggregated 24h trade volumes for different instrument types and currencies. (asynchronously)
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
    public okhttp3.Call publicGetTradeVolumesGetAsync(final ApiCallback<Object> _callback) throws ApiException {

        okhttp3.Call localVarCall = publicGetTradeVolumesGetValidateBeforeCall(_callback);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for publicGetTradingviewChartDataGet
     * @param instrumentName Instrument name (required)
     * @param startTimestamp The earliest timestamp to return result for (required)
     * @param endTimestamp The most recent timestamp to return result for (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call publicGetTradingviewChartDataGetCall(String instrumentName, Integer startTimestamp, Integer endTimestamp, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = new Object();

        // create path and map variables
        String localVarPath = "/public/get_tradingview_chart_data";

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
    private okhttp3.Call publicGetTradingviewChartDataGetValidateBeforeCall(String instrumentName, Integer startTimestamp, Integer endTimestamp, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'instrumentName' is set
        if (instrumentName == null) {
            throw new ApiException("Missing the required parameter 'instrumentName' when calling publicGetTradingviewChartDataGet(Async)");
        }
        
        // verify the required parameter 'startTimestamp' is set
        if (startTimestamp == null) {
            throw new ApiException("Missing the required parameter 'startTimestamp' when calling publicGetTradingviewChartDataGet(Async)");
        }
        
        // verify the required parameter 'endTimestamp' is set
        if (endTimestamp == null) {
            throw new ApiException("Missing the required parameter 'endTimestamp' when calling publicGetTradingviewChartDataGet(Async)");
        }
        

        okhttp3.Call localVarCall = publicGetTradingviewChartDataGetCall(instrumentName, startTimestamp, endTimestamp, _callback);
        return localVarCall;

    }

    /**
     * Publicly available market data used to generate a TradingView candle chart.
     * 
     * @param instrumentName Instrument name (required)
     * @param startTimestamp The earliest timestamp to return result for (required)
     * @param endTimestamp The most recent timestamp to return result for (required)
     * @return Object
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public Object publicGetTradingviewChartDataGet(String instrumentName, Integer startTimestamp, Integer endTimestamp) throws ApiException {
        ApiResponse<Object> localVarResp = publicGetTradingviewChartDataGetWithHttpInfo(instrumentName, startTimestamp, endTimestamp);
        return localVarResp.getData();
    }

    /**
     * Publicly available market data used to generate a TradingView candle chart.
     * 
     * @param instrumentName Instrument name (required)
     * @param startTimestamp The earliest timestamp to return result for (required)
     * @param endTimestamp The most recent timestamp to return result for (required)
     * @return ApiResponse&lt;Object&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<Object> publicGetTradingviewChartDataGetWithHttpInfo(String instrumentName, Integer startTimestamp, Integer endTimestamp) throws ApiException {
        okhttp3.Call localVarCall = publicGetTradingviewChartDataGetValidateBeforeCall(instrumentName, startTimestamp, endTimestamp, null);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Publicly available market data used to generate a TradingView candle chart. (asynchronously)
     * 
     * @param instrumentName Instrument name (required)
     * @param startTimestamp The earliest timestamp to return result for (required)
     * @param endTimestamp The most recent timestamp to return result for (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call publicGetTradingviewChartDataGetAsync(String instrumentName, Integer startTimestamp, Integer endTimestamp, final ApiCallback<Object> _callback) throws ApiException {

        okhttp3.Call localVarCall = publicGetTradingviewChartDataGetValidateBeforeCall(instrumentName, startTimestamp, endTimestamp, _callback);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for publicTickerGet
     * @param instrumentName Instrument name (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> ok response </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call publicTickerGetCall(String instrumentName, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = new Object();

        // create path and map variables
        String localVarPath = "/public/ticker";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        if (instrumentName != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("instrument_name", instrumentName));
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
    private okhttp3.Call publicTickerGetValidateBeforeCall(String instrumentName, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'instrumentName' is set
        if (instrumentName == null) {
            throw new ApiException("Missing the required parameter 'instrumentName' when calling publicTickerGet(Async)");
        }
        

        okhttp3.Call localVarCall = publicTickerGetCall(instrumentName, _callback);
        return localVarCall;

    }

    /**
     * Get ticker for an instrument.
     * 
     * @param instrumentName Instrument name (required)
     * @return Object
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> ok response </td><td>  -  </td></tr>
     </table>
     */
    public Object publicTickerGet(String instrumentName) throws ApiException {
        ApiResponse<Object> localVarResp = publicTickerGetWithHttpInfo(instrumentName);
        return localVarResp.getData();
    }

    /**
     * Get ticker for an instrument.
     * 
     * @param instrumentName Instrument name (required)
     * @return ApiResponse&lt;Object&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> ok response </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<Object> publicTickerGetWithHttpInfo(String instrumentName) throws ApiException {
        okhttp3.Call localVarCall = publicTickerGetValidateBeforeCall(instrumentName, null);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Get ticker for an instrument. (asynchronously)
     * 
     * @param instrumentName Instrument name (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> ok response </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call publicTickerGetAsync(String instrumentName, final ApiCallback<Object> _callback) throws ApiException {

        okhttp3.Call localVarCall = publicTickerGetValidateBeforeCall(instrumentName, _callback);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
}
