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

public class WalletApi {
    private ApiClient localVarApiClient;

    public WalletApi() {
        this(Configuration.getDefaultApiClient());
    }

    public WalletApi(ApiClient apiClient) {
        this.localVarApiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return localVarApiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.localVarApiClient = apiClient;
    }

    /**
     * Build call for privateAddToAddressBookGet
     * @param currency The currency symbol (required)
     * @param type Address book type (required)
     * @param address Address in currency format, it must be in address book (required)
     * @param name Name of address book entry (required)
     * @param tfa TFA code, required when TFA is enabled for current account (optional)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call privateAddToAddressBookGetCall(String currency, String type, String address, String name, String tfa, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = new Object();

        // create path and map variables
        String localVarPath = "/private/add_to_address_book";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        if (currency != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("currency", currency));
        }

        if (type != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("type", type));
        }

        if (address != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("address", address));
        }

        if (name != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("name", name));
        }

        if (tfa != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("tfa", tfa));
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
    private okhttp3.Call privateAddToAddressBookGetValidateBeforeCall(String currency, String type, String address, String name, String tfa, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'currency' is set
        if (currency == null) {
            throw new ApiException("Missing the required parameter 'currency' when calling privateAddToAddressBookGet(Async)");
        }
        
        // verify the required parameter 'type' is set
        if (type == null) {
            throw new ApiException("Missing the required parameter 'type' when calling privateAddToAddressBookGet(Async)");
        }
        
        // verify the required parameter 'address' is set
        if (address == null) {
            throw new ApiException("Missing the required parameter 'address' when calling privateAddToAddressBookGet(Async)");
        }
        
        // verify the required parameter 'name' is set
        if (name == null) {
            throw new ApiException("Missing the required parameter 'name' when calling privateAddToAddressBookGet(Async)");
        }
        

        okhttp3.Call localVarCall = privateAddToAddressBookGetCall(currency, type, address, name, tfa, _callback);
        return localVarCall;

    }

    /**
     * Adds new entry to address book of given type
     * 
     * @param currency The currency symbol (required)
     * @param type Address book type (required)
     * @param address Address in currency format, it must be in address book (required)
     * @param name Name of address book entry (required)
     * @param tfa TFA code, required when TFA is enabled for current account (optional)
     * @return Object
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public Object privateAddToAddressBookGet(String currency, String type, String address, String name, String tfa) throws ApiException {
        ApiResponse<Object> localVarResp = privateAddToAddressBookGetWithHttpInfo(currency, type, address, name, tfa);
        return localVarResp.getData();
    }

    /**
     * Adds new entry to address book of given type
     * 
     * @param currency The currency symbol (required)
     * @param type Address book type (required)
     * @param address Address in currency format, it must be in address book (required)
     * @param name Name of address book entry (required)
     * @param tfa TFA code, required when TFA is enabled for current account (optional)
     * @return ApiResponse&lt;Object&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<Object> privateAddToAddressBookGetWithHttpInfo(String currency, String type, String address, String name, String tfa) throws ApiException {
        okhttp3.Call localVarCall = privateAddToAddressBookGetValidateBeforeCall(currency, type, address, name, tfa, null);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Adds new entry to address book of given type (asynchronously)
     * 
     * @param currency The currency symbol (required)
     * @param type Address book type (required)
     * @param address Address in currency format, it must be in address book (required)
     * @param name Name of address book entry (required)
     * @param tfa TFA code, required when TFA is enabled for current account (optional)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call privateAddToAddressBookGetAsync(String currency, String type, String address, String name, String tfa, final ApiCallback<Object> _callback) throws ApiException {

        okhttp3.Call localVarCall = privateAddToAddressBookGetValidateBeforeCall(currency, type, address, name, tfa, _callback);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for privateCancelTransferByIdGet
     * @param currency The currency symbol (required)
     * @param id Id of transfer (required)
     * @param tfa TFA code, required when TFA is enabled for current account (optional)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> ok response </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call privateCancelTransferByIdGetCall(String currency, Integer id, String tfa, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = new Object();

        // create path and map variables
        String localVarPath = "/private/cancel_transfer_by_id";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        if (currency != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("currency", currency));
        }

        if (id != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("id", id));
        }

        if (tfa != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("tfa", tfa));
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
    private okhttp3.Call privateCancelTransferByIdGetValidateBeforeCall(String currency, Integer id, String tfa, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'currency' is set
        if (currency == null) {
            throw new ApiException("Missing the required parameter 'currency' when calling privateCancelTransferByIdGet(Async)");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new ApiException("Missing the required parameter 'id' when calling privateCancelTransferByIdGet(Async)");
        }
        

        okhttp3.Call localVarCall = privateCancelTransferByIdGetCall(currency, id, tfa, _callback);
        return localVarCall;

    }

    /**
     * Cancel transfer
     * 
     * @param currency The currency symbol (required)
     * @param id Id of transfer (required)
     * @param tfa TFA code, required when TFA is enabled for current account (optional)
     * @return Object
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> ok response </td><td>  -  </td></tr>
     </table>
     */
    public Object privateCancelTransferByIdGet(String currency, Integer id, String tfa) throws ApiException {
        ApiResponse<Object> localVarResp = privateCancelTransferByIdGetWithHttpInfo(currency, id, tfa);
        return localVarResp.getData();
    }

    /**
     * Cancel transfer
     * 
     * @param currency The currency symbol (required)
     * @param id Id of transfer (required)
     * @param tfa TFA code, required when TFA is enabled for current account (optional)
     * @return ApiResponse&lt;Object&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> ok response </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<Object> privateCancelTransferByIdGetWithHttpInfo(String currency, Integer id, String tfa) throws ApiException {
        okhttp3.Call localVarCall = privateCancelTransferByIdGetValidateBeforeCall(currency, id, tfa, null);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Cancel transfer (asynchronously)
     * 
     * @param currency The currency symbol (required)
     * @param id Id of transfer (required)
     * @param tfa TFA code, required when TFA is enabled for current account (optional)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> ok response </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call privateCancelTransferByIdGetAsync(String currency, Integer id, String tfa, final ApiCallback<Object> _callback) throws ApiException {

        okhttp3.Call localVarCall = privateCancelTransferByIdGetValidateBeforeCall(currency, id, tfa, _callback);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for privateCancelWithdrawalGet
     * @param currency The currency symbol (required)
     * @param id The withdrawal id (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call privateCancelWithdrawalGetCall(String currency, BigDecimal id, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = new Object();

        // create path and map variables
        String localVarPath = "/private/cancel_withdrawal";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        if (currency != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("currency", currency));
        }

        if (id != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("id", id));
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
    private okhttp3.Call privateCancelWithdrawalGetValidateBeforeCall(String currency, BigDecimal id, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'currency' is set
        if (currency == null) {
            throw new ApiException("Missing the required parameter 'currency' when calling privateCancelWithdrawalGet(Async)");
        }
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new ApiException("Missing the required parameter 'id' when calling privateCancelWithdrawalGet(Async)");
        }
        

        okhttp3.Call localVarCall = privateCancelWithdrawalGetCall(currency, id, _callback);
        return localVarCall;

    }

    /**
     * Cancels withdrawal request
     * 
     * @param currency The currency symbol (required)
     * @param id The withdrawal id (required)
     * @return Object
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public Object privateCancelWithdrawalGet(String currency, BigDecimal id) throws ApiException {
        ApiResponse<Object> localVarResp = privateCancelWithdrawalGetWithHttpInfo(currency, id);
        return localVarResp.getData();
    }

    /**
     * Cancels withdrawal request
     * 
     * @param currency The currency symbol (required)
     * @param id The withdrawal id (required)
     * @return ApiResponse&lt;Object&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<Object> privateCancelWithdrawalGetWithHttpInfo(String currency, BigDecimal id) throws ApiException {
        okhttp3.Call localVarCall = privateCancelWithdrawalGetValidateBeforeCall(currency, id, null);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Cancels withdrawal request (asynchronously)
     * 
     * @param currency The currency symbol (required)
     * @param id The withdrawal id (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call privateCancelWithdrawalGetAsync(String currency, BigDecimal id, final ApiCallback<Object> _callback) throws ApiException {

        okhttp3.Call localVarCall = privateCancelWithdrawalGetValidateBeforeCall(currency, id, _callback);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for privateCreateDepositAddressGet
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
    public okhttp3.Call privateCreateDepositAddressGetCall(String currency, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = new Object();

        // create path and map variables
        String localVarPath = "/private/create_deposit_address";

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
    private okhttp3.Call privateCreateDepositAddressGetValidateBeforeCall(String currency, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'currency' is set
        if (currency == null) {
            throw new ApiException("Missing the required parameter 'currency' when calling privateCreateDepositAddressGet(Async)");
        }
        

        okhttp3.Call localVarCall = privateCreateDepositAddressGetCall(currency, _callback);
        return localVarCall;

    }

    /**
     * Creates deposit address in currency
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
    public Object privateCreateDepositAddressGet(String currency) throws ApiException {
        ApiResponse<Object> localVarResp = privateCreateDepositAddressGetWithHttpInfo(currency);
        return localVarResp.getData();
    }

    /**
     * Creates deposit address in currency
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
    public ApiResponse<Object> privateCreateDepositAddressGetWithHttpInfo(String currency) throws ApiException {
        okhttp3.Call localVarCall = privateCreateDepositAddressGetValidateBeforeCall(currency, null);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Creates deposit address in currency (asynchronously)
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
    public okhttp3.Call privateCreateDepositAddressGetAsync(String currency, final ApiCallback<Object> _callback) throws ApiException {

        okhttp3.Call localVarCall = privateCreateDepositAddressGetValidateBeforeCall(currency, _callback);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for privateGetAddressBookGet
     * @param currency The currency symbol (required)
     * @param type Address book type (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call privateGetAddressBookGetCall(String currency, String type, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = new Object();

        // create path and map variables
        String localVarPath = "/private/get_address_book";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        if (currency != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("currency", currency));
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
    private okhttp3.Call privateGetAddressBookGetValidateBeforeCall(String currency, String type, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'currency' is set
        if (currency == null) {
            throw new ApiException("Missing the required parameter 'currency' when calling privateGetAddressBookGet(Async)");
        }
        
        // verify the required parameter 'type' is set
        if (type == null) {
            throw new ApiException("Missing the required parameter 'type' when calling privateGetAddressBookGet(Async)");
        }
        

        okhttp3.Call localVarCall = privateGetAddressBookGetCall(currency, type, _callback);
        return localVarCall;

    }

    /**
     * Retrieves address book of given type
     * 
     * @param currency The currency symbol (required)
     * @param type Address book type (required)
     * @return Object
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public Object privateGetAddressBookGet(String currency, String type) throws ApiException {
        ApiResponse<Object> localVarResp = privateGetAddressBookGetWithHttpInfo(currency, type);
        return localVarResp.getData();
    }

    /**
     * Retrieves address book of given type
     * 
     * @param currency The currency symbol (required)
     * @param type Address book type (required)
     * @return ApiResponse&lt;Object&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<Object> privateGetAddressBookGetWithHttpInfo(String currency, String type) throws ApiException {
        okhttp3.Call localVarCall = privateGetAddressBookGetValidateBeforeCall(currency, type, null);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Retrieves address book of given type (asynchronously)
     * 
     * @param currency The currency symbol (required)
     * @param type Address book type (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call privateGetAddressBookGetAsync(String currency, String type, final ApiCallback<Object> _callback) throws ApiException {

        okhttp3.Call localVarCall = privateGetAddressBookGetValidateBeforeCall(currency, type, _callback);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for privateGetCurrentDepositAddressGet
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
    public okhttp3.Call privateGetCurrentDepositAddressGetCall(String currency, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = new Object();

        // create path and map variables
        String localVarPath = "/private/get_current_deposit_address";

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
    private okhttp3.Call privateGetCurrentDepositAddressGetValidateBeforeCall(String currency, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'currency' is set
        if (currency == null) {
            throw new ApiException("Missing the required parameter 'currency' when calling privateGetCurrentDepositAddressGet(Async)");
        }
        

        okhttp3.Call localVarCall = privateGetCurrentDepositAddressGetCall(currency, _callback);
        return localVarCall;

    }

    /**
     * Retrieve deposit address for currency
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
    public Object privateGetCurrentDepositAddressGet(String currency) throws ApiException {
        ApiResponse<Object> localVarResp = privateGetCurrentDepositAddressGetWithHttpInfo(currency);
        return localVarResp.getData();
    }

    /**
     * Retrieve deposit address for currency
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
    public ApiResponse<Object> privateGetCurrentDepositAddressGetWithHttpInfo(String currency) throws ApiException {
        okhttp3.Call localVarCall = privateGetCurrentDepositAddressGetValidateBeforeCall(currency, null);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Retrieve deposit address for currency (asynchronously)
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
    public okhttp3.Call privateGetCurrentDepositAddressGetAsync(String currency, final ApiCallback<Object> _callback) throws ApiException {

        okhttp3.Call localVarCall = privateGetCurrentDepositAddressGetValidateBeforeCall(currency, _callback);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for privateGetDepositsGet
     * @param currency The currency symbol (required)
     * @param count Number of requested items, default - &#x60;10&#x60; (optional)
     * @param offset The offset for pagination, default - &#x60;0&#x60; (optional)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call privateGetDepositsGetCall(String currency, Integer count, Integer offset, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = new Object();

        // create path and map variables
        String localVarPath = "/private/get_deposits";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        if (currency != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("currency", currency));
        }

        if (count != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("count", count));
        }

        if (offset != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("offset", offset));
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
    private okhttp3.Call privateGetDepositsGetValidateBeforeCall(String currency, Integer count, Integer offset, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'currency' is set
        if (currency == null) {
            throw new ApiException("Missing the required parameter 'currency' when calling privateGetDepositsGet(Async)");
        }
        

        okhttp3.Call localVarCall = privateGetDepositsGetCall(currency, count, offset, _callback);
        return localVarCall;

    }

    /**
     * Retrieve the latest users deposits
     * 
     * @param currency The currency symbol (required)
     * @param count Number of requested items, default - &#x60;10&#x60; (optional)
     * @param offset The offset for pagination, default - &#x60;0&#x60; (optional)
     * @return Object
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public Object privateGetDepositsGet(String currency, Integer count, Integer offset) throws ApiException {
        ApiResponse<Object> localVarResp = privateGetDepositsGetWithHttpInfo(currency, count, offset);
        return localVarResp.getData();
    }

    /**
     * Retrieve the latest users deposits
     * 
     * @param currency The currency symbol (required)
     * @param count Number of requested items, default - &#x60;10&#x60; (optional)
     * @param offset The offset for pagination, default - &#x60;0&#x60; (optional)
     * @return ApiResponse&lt;Object&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<Object> privateGetDepositsGetWithHttpInfo(String currency, Integer count, Integer offset) throws ApiException {
        okhttp3.Call localVarCall = privateGetDepositsGetValidateBeforeCall(currency, count, offset, null);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Retrieve the latest users deposits (asynchronously)
     * 
     * @param currency The currency symbol (required)
     * @param count Number of requested items, default - &#x60;10&#x60; (optional)
     * @param offset The offset for pagination, default - &#x60;0&#x60; (optional)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call privateGetDepositsGetAsync(String currency, Integer count, Integer offset, final ApiCallback<Object> _callback) throws ApiException {

        okhttp3.Call localVarCall = privateGetDepositsGetValidateBeforeCall(currency, count, offset, _callback);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for privateGetTransfersGet
     * @param currency The currency symbol (required)
     * @param count Number of requested items, default - &#x60;10&#x60; (optional)
     * @param offset The offset for pagination, default - &#x60;0&#x60; (optional)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call privateGetTransfersGetCall(String currency, Integer count, Integer offset, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = new Object();

        // create path and map variables
        String localVarPath = "/private/get_transfers";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        if (currency != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("currency", currency));
        }

        if (count != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("count", count));
        }

        if (offset != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("offset", offset));
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
    private okhttp3.Call privateGetTransfersGetValidateBeforeCall(String currency, Integer count, Integer offset, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'currency' is set
        if (currency == null) {
            throw new ApiException("Missing the required parameter 'currency' when calling privateGetTransfersGet(Async)");
        }
        

        okhttp3.Call localVarCall = privateGetTransfersGetCall(currency, count, offset, _callback);
        return localVarCall;

    }

    /**
     * Adds new entry to address book of given type
     * 
     * @param currency The currency symbol (required)
     * @param count Number of requested items, default - &#x60;10&#x60; (optional)
     * @param offset The offset for pagination, default - &#x60;0&#x60; (optional)
     * @return Object
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public Object privateGetTransfersGet(String currency, Integer count, Integer offset) throws ApiException {
        ApiResponse<Object> localVarResp = privateGetTransfersGetWithHttpInfo(currency, count, offset);
        return localVarResp.getData();
    }

    /**
     * Adds new entry to address book of given type
     * 
     * @param currency The currency symbol (required)
     * @param count Number of requested items, default - &#x60;10&#x60; (optional)
     * @param offset The offset for pagination, default - &#x60;0&#x60; (optional)
     * @return ApiResponse&lt;Object&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<Object> privateGetTransfersGetWithHttpInfo(String currency, Integer count, Integer offset) throws ApiException {
        okhttp3.Call localVarCall = privateGetTransfersGetValidateBeforeCall(currency, count, offset, null);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Adds new entry to address book of given type (asynchronously)
     * 
     * @param currency The currency symbol (required)
     * @param count Number of requested items, default - &#x60;10&#x60; (optional)
     * @param offset The offset for pagination, default - &#x60;0&#x60; (optional)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call privateGetTransfersGetAsync(String currency, Integer count, Integer offset, final ApiCallback<Object> _callback) throws ApiException {

        okhttp3.Call localVarCall = privateGetTransfersGetValidateBeforeCall(currency, count, offset, _callback);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for privateGetWithdrawalsGet
     * @param currency The currency symbol (required)
     * @param count Number of requested items, default - &#x60;10&#x60; (optional)
     * @param offset The offset for pagination, default - &#x60;0&#x60; (optional)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call privateGetWithdrawalsGetCall(String currency, Integer count, Integer offset, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = new Object();

        // create path and map variables
        String localVarPath = "/private/get_withdrawals";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        if (currency != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("currency", currency));
        }

        if (count != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("count", count));
        }

        if (offset != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("offset", offset));
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
    private okhttp3.Call privateGetWithdrawalsGetValidateBeforeCall(String currency, Integer count, Integer offset, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'currency' is set
        if (currency == null) {
            throw new ApiException("Missing the required parameter 'currency' when calling privateGetWithdrawalsGet(Async)");
        }
        

        okhttp3.Call localVarCall = privateGetWithdrawalsGetCall(currency, count, offset, _callback);
        return localVarCall;

    }

    /**
     * Retrieve the latest users withdrawals
     * 
     * @param currency The currency symbol (required)
     * @param count Number of requested items, default - &#x60;10&#x60; (optional)
     * @param offset The offset for pagination, default - &#x60;0&#x60; (optional)
     * @return Object
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public Object privateGetWithdrawalsGet(String currency, Integer count, Integer offset) throws ApiException {
        ApiResponse<Object> localVarResp = privateGetWithdrawalsGetWithHttpInfo(currency, count, offset);
        return localVarResp.getData();
    }

    /**
     * Retrieve the latest users withdrawals
     * 
     * @param currency The currency symbol (required)
     * @param count Number of requested items, default - &#x60;10&#x60; (optional)
     * @param offset The offset for pagination, default - &#x60;0&#x60; (optional)
     * @return ApiResponse&lt;Object&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<Object> privateGetWithdrawalsGetWithHttpInfo(String currency, Integer count, Integer offset) throws ApiException {
        okhttp3.Call localVarCall = privateGetWithdrawalsGetValidateBeforeCall(currency, count, offset, null);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Retrieve the latest users withdrawals (asynchronously)
     * 
     * @param currency The currency symbol (required)
     * @param count Number of requested items, default - &#x60;10&#x60; (optional)
     * @param offset The offset for pagination, default - &#x60;0&#x60; (optional)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call privateGetWithdrawalsGetAsync(String currency, Integer count, Integer offset, final ApiCallback<Object> _callback) throws ApiException {

        okhttp3.Call localVarCall = privateGetWithdrawalsGetValidateBeforeCall(currency, count, offset, _callback);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for privateRemoveFromAddressBookGet
     * @param currency The currency symbol (required)
     * @param type Address book type (required)
     * @param address Address in currency format, it must be in address book (required)
     * @param tfa TFA code, required when TFA is enabled for current account (optional)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call privateRemoveFromAddressBookGetCall(String currency, String type, String address, String tfa, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = new Object();

        // create path and map variables
        String localVarPath = "/private/remove_from_address_book";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        if (currency != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("currency", currency));
        }

        if (type != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("type", type));
        }

        if (address != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("address", address));
        }

        if (tfa != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("tfa", tfa));
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
    private okhttp3.Call privateRemoveFromAddressBookGetValidateBeforeCall(String currency, String type, String address, String tfa, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'currency' is set
        if (currency == null) {
            throw new ApiException("Missing the required parameter 'currency' when calling privateRemoveFromAddressBookGet(Async)");
        }
        
        // verify the required parameter 'type' is set
        if (type == null) {
            throw new ApiException("Missing the required parameter 'type' when calling privateRemoveFromAddressBookGet(Async)");
        }
        
        // verify the required parameter 'address' is set
        if (address == null) {
            throw new ApiException("Missing the required parameter 'address' when calling privateRemoveFromAddressBookGet(Async)");
        }
        

        okhttp3.Call localVarCall = privateRemoveFromAddressBookGetCall(currency, type, address, tfa, _callback);
        return localVarCall;

    }

    /**
     * Adds new entry to address book of given type
     * 
     * @param currency The currency symbol (required)
     * @param type Address book type (required)
     * @param address Address in currency format, it must be in address book (required)
     * @param tfa TFA code, required when TFA is enabled for current account (optional)
     * @return Object
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public Object privateRemoveFromAddressBookGet(String currency, String type, String address, String tfa) throws ApiException {
        ApiResponse<Object> localVarResp = privateRemoveFromAddressBookGetWithHttpInfo(currency, type, address, tfa);
        return localVarResp.getData();
    }

    /**
     * Adds new entry to address book of given type
     * 
     * @param currency The currency symbol (required)
     * @param type Address book type (required)
     * @param address Address in currency format, it must be in address book (required)
     * @param tfa TFA code, required when TFA is enabled for current account (optional)
     * @return ApiResponse&lt;Object&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<Object> privateRemoveFromAddressBookGetWithHttpInfo(String currency, String type, String address, String tfa) throws ApiException {
        okhttp3.Call localVarCall = privateRemoveFromAddressBookGetValidateBeforeCall(currency, type, address, tfa, null);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Adds new entry to address book of given type (asynchronously)
     * 
     * @param currency The currency symbol (required)
     * @param type Address book type (required)
     * @param address Address in currency format, it must be in address book (required)
     * @param tfa TFA code, required when TFA is enabled for current account (optional)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call privateRemoveFromAddressBookGetAsync(String currency, String type, String address, String tfa, final ApiCallback<Object> _callback) throws ApiException {

        okhttp3.Call localVarCall = privateRemoveFromAddressBookGetValidateBeforeCall(currency, type, address, tfa, _callback);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for privateSubmitTransferToSubaccountGet
     * @param currency The currency symbol (required)
     * @param amount Amount of funds to be transferred (required)
     * @param destination Id of destination subaccount (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> ok response </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call privateSubmitTransferToSubaccountGetCall(String currency, BigDecimal amount, Integer destination, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = new Object();

        // create path and map variables
        String localVarPath = "/private/submit_transfer_to_subaccount";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        if (currency != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("currency", currency));
        }

        if (amount != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("amount", amount));
        }

        if (destination != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("destination", destination));
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
    private okhttp3.Call privateSubmitTransferToSubaccountGetValidateBeforeCall(String currency, BigDecimal amount, Integer destination, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'currency' is set
        if (currency == null) {
            throw new ApiException("Missing the required parameter 'currency' when calling privateSubmitTransferToSubaccountGet(Async)");
        }
        
        // verify the required parameter 'amount' is set
        if (amount == null) {
            throw new ApiException("Missing the required parameter 'amount' when calling privateSubmitTransferToSubaccountGet(Async)");
        }
        
        // verify the required parameter 'destination' is set
        if (destination == null) {
            throw new ApiException("Missing the required parameter 'destination' when calling privateSubmitTransferToSubaccountGet(Async)");
        }
        

        okhttp3.Call localVarCall = privateSubmitTransferToSubaccountGetCall(currency, amount, destination, _callback);
        return localVarCall;

    }

    /**
     * Transfer funds to a subaccount.
     * 
     * @param currency The currency symbol (required)
     * @param amount Amount of funds to be transferred (required)
     * @param destination Id of destination subaccount (required)
     * @return Object
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> ok response </td><td>  -  </td></tr>
     </table>
     */
    public Object privateSubmitTransferToSubaccountGet(String currency, BigDecimal amount, Integer destination) throws ApiException {
        ApiResponse<Object> localVarResp = privateSubmitTransferToSubaccountGetWithHttpInfo(currency, amount, destination);
        return localVarResp.getData();
    }

    /**
     * Transfer funds to a subaccount.
     * 
     * @param currency The currency symbol (required)
     * @param amount Amount of funds to be transferred (required)
     * @param destination Id of destination subaccount (required)
     * @return ApiResponse&lt;Object&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> ok response </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<Object> privateSubmitTransferToSubaccountGetWithHttpInfo(String currency, BigDecimal amount, Integer destination) throws ApiException {
        okhttp3.Call localVarCall = privateSubmitTransferToSubaccountGetValidateBeforeCall(currency, amount, destination, null);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Transfer funds to a subaccount. (asynchronously)
     * 
     * @param currency The currency symbol (required)
     * @param amount Amount of funds to be transferred (required)
     * @param destination Id of destination subaccount (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> ok response </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call privateSubmitTransferToSubaccountGetAsync(String currency, BigDecimal amount, Integer destination, final ApiCallback<Object> _callback) throws ApiException {

        okhttp3.Call localVarCall = privateSubmitTransferToSubaccountGetValidateBeforeCall(currency, amount, destination, _callback);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for privateSubmitTransferToUserGet
     * @param currency The currency symbol (required)
     * @param amount Amount of funds to be transferred (required)
     * @param destination Destination address from address book (required)
     * @param tfa TFA code, required when TFA is enabled for current account (optional)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call privateSubmitTransferToUserGetCall(String currency, BigDecimal amount, String destination, String tfa, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = new Object();

        // create path and map variables
        String localVarPath = "/private/submit_transfer_to_user";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        if (currency != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("currency", currency));
        }

        if (amount != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("amount", amount));
        }

        if (destination != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("destination", destination));
        }

        if (tfa != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("tfa", tfa));
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
    private okhttp3.Call privateSubmitTransferToUserGetValidateBeforeCall(String currency, BigDecimal amount, String destination, String tfa, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'currency' is set
        if (currency == null) {
            throw new ApiException("Missing the required parameter 'currency' when calling privateSubmitTransferToUserGet(Async)");
        }
        
        // verify the required parameter 'amount' is set
        if (amount == null) {
            throw new ApiException("Missing the required parameter 'amount' when calling privateSubmitTransferToUserGet(Async)");
        }
        
        // verify the required parameter 'destination' is set
        if (destination == null) {
            throw new ApiException("Missing the required parameter 'destination' when calling privateSubmitTransferToUserGet(Async)");
        }
        

        okhttp3.Call localVarCall = privateSubmitTransferToUserGetCall(currency, amount, destination, tfa, _callback);
        return localVarCall;

    }

    /**
     * Transfer funds to a another user.
     * 
     * @param currency The currency symbol (required)
     * @param amount Amount of funds to be transferred (required)
     * @param destination Destination address from address book (required)
     * @param tfa TFA code, required when TFA is enabled for current account (optional)
     * @return Object
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public Object privateSubmitTransferToUserGet(String currency, BigDecimal amount, String destination, String tfa) throws ApiException {
        ApiResponse<Object> localVarResp = privateSubmitTransferToUserGetWithHttpInfo(currency, amount, destination, tfa);
        return localVarResp.getData();
    }

    /**
     * Transfer funds to a another user.
     * 
     * @param currency The currency symbol (required)
     * @param amount Amount of funds to be transferred (required)
     * @param destination Destination address from address book (required)
     * @param tfa TFA code, required when TFA is enabled for current account (optional)
     * @return ApiResponse&lt;Object&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<Object> privateSubmitTransferToUserGetWithHttpInfo(String currency, BigDecimal amount, String destination, String tfa) throws ApiException {
        okhttp3.Call localVarCall = privateSubmitTransferToUserGetValidateBeforeCall(currency, amount, destination, tfa, null);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Transfer funds to a another user. (asynchronously)
     * 
     * @param currency The currency symbol (required)
     * @param amount Amount of funds to be transferred (required)
     * @param destination Destination address from address book (required)
     * @param tfa TFA code, required when TFA is enabled for current account (optional)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call privateSubmitTransferToUserGetAsync(String currency, BigDecimal amount, String destination, String tfa, final ApiCallback<Object> _callback) throws ApiException {

        okhttp3.Call localVarCall = privateSubmitTransferToUserGetValidateBeforeCall(currency, amount, destination, tfa, _callback);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for privateToggleDepositAddressCreationGet
     * @param currency The currency symbol (required)
     * @param state  (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call privateToggleDepositAddressCreationGetCall(String currency, Boolean state, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = new Object();

        // create path and map variables
        String localVarPath = "/private/toggle_deposit_address_creation";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        if (currency != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("currency", currency));
        }

        if (state != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("state", state));
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
    private okhttp3.Call privateToggleDepositAddressCreationGetValidateBeforeCall(String currency, Boolean state, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'currency' is set
        if (currency == null) {
            throw new ApiException("Missing the required parameter 'currency' when calling privateToggleDepositAddressCreationGet(Async)");
        }
        
        // verify the required parameter 'state' is set
        if (state == null) {
            throw new ApiException("Missing the required parameter 'state' when calling privateToggleDepositAddressCreationGet(Async)");
        }
        

        okhttp3.Call localVarCall = privateToggleDepositAddressCreationGetCall(currency, state, _callback);
        return localVarCall;

    }

    /**
     * Enable or disable deposit address creation
     * 
     * @param currency The currency symbol (required)
     * @param state  (required)
     * @return Object
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public Object privateToggleDepositAddressCreationGet(String currency, Boolean state) throws ApiException {
        ApiResponse<Object> localVarResp = privateToggleDepositAddressCreationGetWithHttpInfo(currency, state);
        return localVarResp.getData();
    }

    /**
     * Enable or disable deposit address creation
     * 
     * @param currency The currency symbol (required)
     * @param state  (required)
     * @return ApiResponse&lt;Object&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<Object> privateToggleDepositAddressCreationGetWithHttpInfo(String currency, Boolean state) throws ApiException {
        okhttp3.Call localVarCall = privateToggleDepositAddressCreationGetValidateBeforeCall(currency, state, null);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Enable or disable deposit address creation (asynchronously)
     * 
     * @param currency The currency symbol (required)
     * @param state  (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call privateToggleDepositAddressCreationGetAsync(String currency, Boolean state, final ApiCallback<Object> _callback) throws ApiException {

        okhttp3.Call localVarCall = privateToggleDepositAddressCreationGetValidateBeforeCall(currency, state, _callback);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for privateWithdrawGet
     * @param currency The currency symbol (required)
     * @param address Address in currency format, it must be in address book (required)
     * @param amount Amount of funds to be withdrawn (required)
     * @param priority Withdrawal priority, optional for BTC, default: &#x60;high&#x60; (optional)
     * @param tfa TFA code, required when TFA is enabled for current account (optional)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call privateWithdrawGetCall(String currency, String address, BigDecimal amount, String priority, String tfa, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = new Object();

        // create path and map variables
        String localVarPath = "/private/withdraw";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        if (currency != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("currency", currency));
        }

        if (address != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("address", address));
        }

        if (amount != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("amount", amount));
        }

        if (priority != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("priority", priority));
        }

        if (tfa != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("tfa", tfa));
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
    private okhttp3.Call privateWithdrawGetValidateBeforeCall(String currency, String address, BigDecimal amount, String priority, String tfa, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'currency' is set
        if (currency == null) {
            throw new ApiException("Missing the required parameter 'currency' when calling privateWithdrawGet(Async)");
        }
        
        // verify the required parameter 'address' is set
        if (address == null) {
            throw new ApiException("Missing the required parameter 'address' when calling privateWithdrawGet(Async)");
        }
        
        // verify the required parameter 'amount' is set
        if (amount == null) {
            throw new ApiException("Missing the required parameter 'amount' when calling privateWithdrawGet(Async)");
        }
        

        okhttp3.Call localVarCall = privateWithdrawGetCall(currency, address, amount, priority, tfa, _callback);
        return localVarCall;

    }

    /**
     * Creates a new withdrawal request
     * 
     * @param currency The currency symbol (required)
     * @param address Address in currency format, it must be in address book (required)
     * @param amount Amount of funds to be withdrawn (required)
     * @param priority Withdrawal priority, optional for BTC, default: &#x60;high&#x60; (optional)
     * @param tfa TFA code, required when TFA is enabled for current account (optional)
     * @return Object
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public Object privateWithdrawGet(String currency, String address, BigDecimal amount, String priority, String tfa) throws ApiException {
        ApiResponse<Object> localVarResp = privateWithdrawGetWithHttpInfo(currency, address, amount, priority, tfa);
        return localVarResp.getData();
    }

    /**
     * Creates a new withdrawal request
     * 
     * @param currency The currency symbol (required)
     * @param address Address in currency format, it must be in address book (required)
     * @param amount Amount of funds to be withdrawn (required)
     * @param priority Withdrawal priority, optional for BTC, default: &#x60;high&#x60; (optional)
     * @param tfa TFA code, required when TFA is enabled for current account (optional)
     * @return ApiResponse&lt;Object&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<Object> privateWithdrawGetWithHttpInfo(String currency, String address, BigDecimal amount, String priority, String tfa) throws ApiException {
        okhttp3.Call localVarCall = privateWithdrawGetValidateBeforeCall(currency, address, amount, priority, tfa, null);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Creates a new withdrawal request (asynchronously)
     * 
     * @param currency The currency symbol (required)
     * @param address Address in currency format, it must be in address book (required)
     * @param amount Amount of funds to be withdrawn (required)
     * @param priority Withdrawal priority, optional for BTC, default: &#x60;high&#x60; (optional)
     * @param tfa TFA code, required when TFA is enabled for current account (optional)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td>  </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call privateWithdrawGetAsync(String currency, String address, BigDecimal amount, String priority, String tfa, final ApiCallback<Object> _callback) throws ApiException {

        okhttp3.Call localVarCall = privateWithdrawGetValidateBeforeCall(currency, address, amount, priority, tfa, _callback);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
}
