/**
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

import org.openapitools.client.ApiInvoker;
import org.openapitools.client.ApiException;
import org.openapitools.client.Pair;

import org.openapitools.client.model.*;

import java.util.*;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.math.BigDecimal;

import org.apache.http.HttpEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public class PrivateApi {
  String basePath = "https://www.deribit.com/api/v2";
  ApiInvoker apiInvoker = ApiInvoker.getInstance();

  public void addHeader(String key, String value) {
    getInvoker().addDefaultHeader(key, value);
  }

  public ApiInvoker getInvoker() {
    return apiInvoker;
  }

  public void setBasePath(String basePath) {
    this.basePath = basePath;
  }

  public String getBasePath() {
    return basePath;
  }

  /**
  * Adds new entry to address book of given type
  * 
   * @param currency The currency symbol
   * @param type Address book type
   * @param address Address in currency format, it must be in address book
   * @param name Name of address book entry
   * @param tfa TFA code, required when TFA is enabled for current account
   * @return Object
  */
  public Object privateAddToAddressBookGet (String currency, String type, String address, String name, String tfa) throws TimeoutException, ExecutionException, InterruptedException, ApiException {
    Object postBody = null;
    // verify the required parameter 'currency' is set
    if (currency == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'currency' when calling privateAddToAddressBookGet",
        new ApiException(400, "Missing the required parameter 'currency' when calling privateAddToAddressBookGet"));
    }
    // verify the required parameter 'type' is set
    if (type == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'type' when calling privateAddToAddressBookGet",
        new ApiException(400, "Missing the required parameter 'type' when calling privateAddToAddressBookGet"));
    }
    // verify the required parameter 'address' is set
    if (address == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'address' when calling privateAddToAddressBookGet",
        new ApiException(400, "Missing the required parameter 'address' when calling privateAddToAddressBookGet"));
    }
    // verify the required parameter 'name' is set
    if (name == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'name' when calling privateAddToAddressBookGet",
        new ApiException(400, "Missing the required parameter 'name' when calling privateAddToAddressBookGet"));
    }

    // create path and map variables
    String path = "/private/add_to_address_book";

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();
    queryParams.addAll(ApiInvoker.parameterToPairs("", "currency", currency));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "type", type));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "address", address));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "name", name));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "tfa", tfa));
    String[] contentTypes = {
    };
    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

    if (contentType.startsWith("multipart/form-data")) {
      // file uploading
      MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();
      HttpEntity httpEntity = localVarBuilder.build();
      postBody = httpEntity;
    } else {
      // normal form params
    }

    String[] authNames = new String[] { "bearerAuth" };

    try {
      String localVarResponse = apiInvoker.invokeAPI (basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType, authNames);
      if (localVarResponse != null) {
         return (Object) ApiInvoker.deserialize(localVarResponse, "", Object.class);
      } else {
         return null;
      }
    } catch (ApiException ex) {
       throw ex;
    } catch (InterruptedException ex) {
       throw ex;
    } catch (ExecutionException ex) {
      if (ex.getCause() instanceof VolleyError) {
        VolleyError volleyError = (VolleyError)ex.getCause();
        if (volleyError.networkResponse != null) {
          throw new ApiException(volleyError.networkResponse.statusCode, volleyError.getMessage());
        }
      }
      throw ex;
    } catch (TimeoutException ex) {
      throw ex;
    }
  }

      /**
   * Adds new entry to address book of given type
   * 
   * @param currency The currency symbol   * @param type Address book type   * @param address Address in currency format, it must be in address book   * @param name Name of address book entry   * @param tfa TFA code, required when TFA is enabled for current account
  */
  public void privateAddToAddressBookGet (String currency, String type, String address, String name, String tfa, final Response.Listener<Object> responseListener, final Response.ErrorListener errorListener) {
    Object postBody = null;

    // verify the required parameter 'currency' is set
    if (currency == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'currency' when calling privateAddToAddressBookGet",
        new ApiException(400, "Missing the required parameter 'currency' when calling privateAddToAddressBookGet"));
    }
    // verify the required parameter 'type' is set
    if (type == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'type' when calling privateAddToAddressBookGet",
        new ApiException(400, "Missing the required parameter 'type' when calling privateAddToAddressBookGet"));
    }
    // verify the required parameter 'address' is set
    if (address == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'address' when calling privateAddToAddressBookGet",
        new ApiException(400, "Missing the required parameter 'address' when calling privateAddToAddressBookGet"));
    }
    // verify the required parameter 'name' is set
    if (name == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'name' when calling privateAddToAddressBookGet",
        new ApiException(400, "Missing the required parameter 'name' when calling privateAddToAddressBookGet"));
    }

    // create path and map variables
    String path = "/private/add_to_address_book".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    queryParams.addAll(ApiInvoker.parameterToPairs("", "currency", currency));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "type", type));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "address", address));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "name", name));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "tfa", tfa));


    String[] contentTypes = {
      
    };
    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

    if (contentType.startsWith("multipart/form-data")) {
      // file uploading
      MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();
      

      HttpEntity httpEntity = localVarBuilder.build();
      postBody = httpEntity;
    } else {
      // normal form params
          }

    String[] authNames = new String[] { "bearerAuth" };

    try {
      apiInvoker.invokeAPI(basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType, authNames,
        new Response.Listener<String>() {
          @Override
          public void onResponse(String localVarResponse) {
            try {
              responseListener.onResponse((Object) ApiInvoker.deserialize(localVarResponse,  "", Object.class));
            } catch (ApiException exception) {
               errorListener.onErrorResponse(new VolleyError(exception));
            }
          }
      }, new Response.ErrorListener() {
          @Override
          public void onErrorResponse(VolleyError error) {
            errorListener.onErrorResponse(error);
          }
      });
    } catch (ApiException ex) {
      errorListener.onErrorResponse(new VolleyError(ex));
    }
  }
  /**
  * Places a buy order for an instrument.
  * 
   * @param instrumentName Instrument name
   * @param amount It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH
   * @param type The order type, default: &#x60;\&quot;limit\&quot;&#x60;
   * @param label user defined label for the order (maximum 32 characters)
   * @param price &lt;p&gt;The order price in base currency (Only for limit and stop_limit orders)&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;usd, the field price should be the option price value in USD.&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;implv, the field price should be a value of implied volatility in percentages. For example,  price&#x3D;100, means implied volatility of 100%&lt;/p&gt;
   * @param timeInForce &lt;p&gt;Specifies how long the order remains in effect. Default &#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; &lt;ul&gt; &lt;li&gt;&#x60;\&quot;good_til_cancelled\&quot;&#x60; - unfilled order remains in order book until cancelled&lt;/li&gt; &lt;li&gt;&#x60;\&quot;fill_or_kill\&quot;&#x60; - execute a transaction immediately and completely or not at all&lt;/li&gt; &lt;li&gt;&#x60;\&quot;immediate_or_cancel\&quot;&#x60; - execute a transaction immediately, and any portion of the order that cannot be immediately filled is cancelled&lt;/li&gt; &lt;/ul&gt;
   * @param maxShow Maximum amount within an order to be shown to other customers, &#x60;0&#x60; for invisible order
   * @param postOnly &lt;p&gt;If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.&lt;/p&gt; &lt;p&gt;Only valid in combination with time_in_force&#x3D;&#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt;
   * @param reduceOnly If &#x60;true&#x60;, the order is considered reduce-only which is intended to only reduce a current position
   * @param stopPrice Stop price, required for stop limit orders (Only for stop orders)
   * @param trigger Defines trigger type, required for &#x60;\&quot;stop_limit\&quot;&#x60; order type
   * @param advanced Advanced option order type. (Only for options)
   * @return Object
  */
  public Object privateBuyGet (String instrumentName, BigDecimal amount, String type, String label, BigDecimal price, String timeInForce, BigDecimal maxShow, Boolean postOnly, Boolean reduceOnly, BigDecimal stopPrice, String trigger, String advanced) throws TimeoutException, ExecutionException, InterruptedException, ApiException {
    Object postBody = null;
    // verify the required parameter 'instrumentName' is set
    if (instrumentName == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'instrumentName' when calling privateBuyGet",
        new ApiException(400, "Missing the required parameter 'instrumentName' when calling privateBuyGet"));
    }
    // verify the required parameter 'amount' is set
    if (amount == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'amount' when calling privateBuyGet",
        new ApiException(400, "Missing the required parameter 'amount' when calling privateBuyGet"));
    }

    // create path and map variables
    String path = "/private/buy";

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();
    queryParams.addAll(ApiInvoker.parameterToPairs("", "instrument_name", instrumentName));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "amount", amount));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "type", type));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "label", label));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "price", price));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "time_in_force", timeInForce));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "max_show", maxShow));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "post_only", postOnly));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "reduce_only", reduceOnly));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "stop_price", stopPrice));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "trigger", trigger));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "advanced", advanced));
    String[] contentTypes = {
    };
    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

    if (contentType.startsWith("multipart/form-data")) {
      // file uploading
      MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();
      HttpEntity httpEntity = localVarBuilder.build();
      postBody = httpEntity;
    } else {
      // normal form params
    }

    String[] authNames = new String[] { "bearerAuth" };

    try {
      String localVarResponse = apiInvoker.invokeAPI (basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType, authNames);
      if (localVarResponse != null) {
         return (Object) ApiInvoker.deserialize(localVarResponse, "", Object.class);
      } else {
         return null;
      }
    } catch (ApiException ex) {
       throw ex;
    } catch (InterruptedException ex) {
       throw ex;
    } catch (ExecutionException ex) {
      if (ex.getCause() instanceof VolleyError) {
        VolleyError volleyError = (VolleyError)ex.getCause();
        if (volleyError.networkResponse != null) {
          throw new ApiException(volleyError.networkResponse.statusCode, volleyError.getMessage());
        }
      }
      throw ex;
    } catch (TimeoutException ex) {
      throw ex;
    }
  }

      /**
   * Places a buy order for an instrument.
   * 
   * @param instrumentName Instrument name   * @param amount It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH   * @param type The order type, default: &#x60;\&quot;limit\&quot;&#x60;   * @param label user defined label for the order (maximum 32 characters)   * @param price &lt;p&gt;The order price in base currency (Only for limit and stop_limit orders)&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;usd, the field price should be the option price value in USD.&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;implv, the field price should be a value of implied volatility in percentages. For example,  price&#x3D;100, means implied volatility of 100%&lt;/p&gt;   * @param timeInForce &lt;p&gt;Specifies how long the order remains in effect. Default &#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; &lt;ul&gt; &lt;li&gt;&#x60;\&quot;good_til_cancelled\&quot;&#x60; - unfilled order remains in order book until cancelled&lt;/li&gt; &lt;li&gt;&#x60;\&quot;fill_or_kill\&quot;&#x60; - execute a transaction immediately and completely or not at all&lt;/li&gt; &lt;li&gt;&#x60;\&quot;immediate_or_cancel\&quot;&#x60; - execute a transaction immediately, and any portion of the order that cannot be immediately filled is cancelled&lt;/li&gt; &lt;/ul&gt;   * @param maxShow Maximum amount within an order to be shown to other customers, &#x60;0&#x60; for invisible order   * @param postOnly &lt;p&gt;If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.&lt;/p&gt; &lt;p&gt;Only valid in combination with time_in_force&#x3D;&#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt;   * @param reduceOnly If &#x60;true&#x60;, the order is considered reduce-only which is intended to only reduce a current position   * @param stopPrice Stop price, required for stop limit orders (Only for stop orders)   * @param trigger Defines trigger type, required for &#x60;\&quot;stop_limit\&quot;&#x60; order type   * @param advanced Advanced option order type. (Only for options)
  */
  public void privateBuyGet (String instrumentName, BigDecimal amount, String type, String label, BigDecimal price, String timeInForce, BigDecimal maxShow, Boolean postOnly, Boolean reduceOnly, BigDecimal stopPrice, String trigger, String advanced, final Response.Listener<Object> responseListener, final Response.ErrorListener errorListener) {
    Object postBody = null;

    // verify the required parameter 'instrumentName' is set
    if (instrumentName == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'instrumentName' when calling privateBuyGet",
        new ApiException(400, "Missing the required parameter 'instrumentName' when calling privateBuyGet"));
    }
    // verify the required parameter 'amount' is set
    if (amount == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'amount' when calling privateBuyGet",
        new ApiException(400, "Missing the required parameter 'amount' when calling privateBuyGet"));
    }

    // create path and map variables
    String path = "/private/buy".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    queryParams.addAll(ApiInvoker.parameterToPairs("", "instrument_name", instrumentName));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "amount", amount));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "type", type));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "label", label));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "price", price));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "time_in_force", timeInForce));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "max_show", maxShow));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "post_only", postOnly));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "reduce_only", reduceOnly));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "stop_price", stopPrice));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "trigger", trigger));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "advanced", advanced));


    String[] contentTypes = {
      
    };
    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

    if (contentType.startsWith("multipart/form-data")) {
      // file uploading
      MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();
      

      HttpEntity httpEntity = localVarBuilder.build();
      postBody = httpEntity;
    } else {
      // normal form params
          }

    String[] authNames = new String[] { "bearerAuth" };

    try {
      apiInvoker.invokeAPI(basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType, authNames,
        new Response.Listener<String>() {
          @Override
          public void onResponse(String localVarResponse) {
            try {
              responseListener.onResponse((Object) ApiInvoker.deserialize(localVarResponse,  "", Object.class));
            } catch (ApiException exception) {
               errorListener.onErrorResponse(new VolleyError(exception));
            }
          }
      }, new Response.ErrorListener() {
          @Override
          public void onErrorResponse(VolleyError error) {
            errorListener.onErrorResponse(error);
          }
      });
    } catch (ApiException ex) {
      errorListener.onErrorResponse(new VolleyError(ex));
    }
  }
  /**
  * Cancels all orders by currency, optionally filtered by instrument kind and/or order type.
  * 
   * @param currency The currency symbol
   * @param kind Instrument kind, if not provided instruments of all kinds are considered
   * @param type Order type - limit, stop or all, default - &#x60;all&#x60;
   * @return Object
  */
  public Object privateCancelAllByCurrencyGet (String currency, String kind, String type) throws TimeoutException, ExecutionException, InterruptedException, ApiException {
    Object postBody = null;
    // verify the required parameter 'currency' is set
    if (currency == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'currency' when calling privateCancelAllByCurrencyGet",
        new ApiException(400, "Missing the required parameter 'currency' when calling privateCancelAllByCurrencyGet"));
    }

    // create path and map variables
    String path = "/private/cancel_all_by_currency";

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();
    queryParams.addAll(ApiInvoker.parameterToPairs("", "currency", currency));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "kind", kind));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "type", type));
    String[] contentTypes = {
    };
    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

    if (contentType.startsWith("multipart/form-data")) {
      // file uploading
      MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();
      HttpEntity httpEntity = localVarBuilder.build();
      postBody = httpEntity;
    } else {
      // normal form params
    }

    String[] authNames = new String[] { "bearerAuth" };

    try {
      String localVarResponse = apiInvoker.invokeAPI (basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType, authNames);
      if (localVarResponse != null) {
         return (Object) ApiInvoker.deserialize(localVarResponse, "", Object.class);
      } else {
         return null;
      }
    } catch (ApiException ex) {
       throw ex;
    } catch (InterruptedException ex) {
       throw ex;
    } catch (ExecutionException ex) {
      if (ex.getCause() instanceof VolleyError) {
        VolleyError volleyError = (VolleyError)ex.getCause();
        if (volleyError.networkResponse != null) {
          throw new ApiException(volleyError.networkResponse.statusCode, volleyError.getMessage());
        }
      }
      throw ex;
    } catch (TimeoutException ex) {
      throw ex;
    }
  }

      /**
   * Cancels all orders by currency, optionally filtered by instrument kind and/or order type.
   * 
   * @param currency The currency symbol   * @param kind Instrument kind, if not provided instruments of all kinds are considered   * @param type Order type - limit, stop or all, default - &#x60;all&#x60;
  */
  public void privateCancelAllByCurrencyGet (String currency, String kind, String type, final Response.Listener<Object> responseListener, final Response.ErrorListener errorListener) {
    Object postBody = null;

    // verify the required parameter 'currency' is set
    if (currency == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'currency' when calling privateCancelAllByCurrencyGet",
        new ApiException(400, "Missing the required parameter 'currency' when calling privateCancelAllByCurrencyGet"));
    }

    // create path and map variables
    String path = "/private/cancel_all_by_currency".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    queryParams.addAll(ApiInvoker.parameterToPairs("", "currency", currency));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "kind", kind));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "type", type));


    String[] contentTypes = {
      
    };
    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

    if (contentType.startsWith("multipart/form-data")) {
      // file uploading
      MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();
      

      HttpEntity httpEntity = localVarBuilder.build();
      postBody = httpEntity;
    } else {
      // normal form params
          }

    String[] authNames = new String[] { "bearerAuth" };

    try {
      apiInvoker.invokeAPI(basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType, authNames,
        new Response.Listener<String>() {
          @Override
          public void onResponse(String localVarResponse) {
            try {
              responseListener.onResponse((Object) ApiInvoker.deserialize(localVarResponse,  "", Object.class));
            } catch (ApiException exception) {
               errorListener.onErrorResponse(new VolleyError(exception));
            }
          }
      }, new Response.ErrorListener() {
          @Override
          public void onErrorResponse(VolleyError error) {
            errorListener.onErrorResponse(error);
          }
      });
    } catch (ApiException ex) {
      errorListener.onErrorResponse(new VolleyError(ex));
    }
  }
  /**
  * Cancels all orders by instrument, optionally filtered by order type.
  * 
   * @param instrumentName Instrument name
   * @param type Order type - limit, stop or all, default - &#x60;all&#x60;
   * @return Object
  */
  public Object privateCancelAllByInstrumentGet (String instrumentName, String type) throws TimeoutException, ExecutionException, InterruptedException, ApiException {
    Object postBody = null;
    // verify the required parameter 'instrumentName' is set
    if (instrumentName == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'instrumentName' when calling privateCancelAllByInstrumentGet",
        new ApiException(400, "Missing the required parameter 'instrumentName' when calling privateCancelAllByInstrumentGet"));
    }

    // create path and map variables
    String path = "/private/cancel_all_by_instrument";

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();
    queryParams.addAll(ApiInvoker.parameterToPairs("", "instrument_name", instrumentName));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "type", type));
    String[] contentTypes = {
    };
    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

    if (contentType.startsWith("multipart/form-data")) {
      // file uploading
      MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();
      HttpEntity httpEntity = localVarBuilder.build();
      postBody = httpEntity;
    } else {
      // normal form params
    }

    String[] authNames = new String[] { "bearerAuth" };

    try {
      String localVarResponse = apiInvoker.invokeAPI (basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType, authNames);
      if (localVarResponse != null) {
         return (Object) ApiInvoker.deserialize(localVarResponse, "", Object.class);
      } else {
         return null;
      }
    } catch (ApiException ex) {
       throw ex;
    } catch (InterruptedException ex) {
       throw ex;
    } catch (ExecutionException ex) {
      if (ex.getCause() instanceof VolleyError) {
        VolleyError volleyError = (VolleyError)ex.getCause();
        if (volleyError.networkResponse != null) {
          throw new ApiException(volleyError.networkResponse.statusCode, volleyError.getMessage());
        }
      }
      throw ex;
    } catch (TimeoutException ex) {
      throw ex;
    }
  }

      /**
   * Cancels all orders by instrument, optionally filtered by order type.
   * 
   * @param instrumentName Instrument name   * @param type Order type - limit, stop or all, default - &#x60;all&#x60;
  */
  public void privateCancelAllByInstrumentGet (String instrumentName, String type, final Response.Listener<Object> responseListener, final Response.ErrorListener errorListener) {
    Object postBody = null;

    // verify the required parameter 'instrumentName' is set
    if (instrumentName == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'instrumentName' when calling privateCancelAllByInstrumentGet",
        new ApiException(400, "Missing the required parameter 'instrumentName' when calling privateCancelAllByInstrumentGet"));
    }

    // create path and map variables
    String path = "/private/cancel_all_by_instrument".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    queryParams.addAll(ApiInvoker.parameterToPairs("", "instrument_name", instrumentName));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "type", type));


    String[] contentTypes = {
      
    };
    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

    if (contentType.startsWith("multipart/form-data")) {
      // file uploading
      MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();
      

      HttpEntity httpEntity = localVarBuilder.build();
      postBody = httpEntity;
    } else {
      // normal form params
          }

    String[] authNames = new String[] { "bearerAuth" };

    try {
      apiInvoker.invokeAPI(basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType, authNames,
        new Response.Listener<String>() {
          @Override
          public void onResponse(String localVarResponse) {
            try {
              responseListener.onResponse((Object) ApiInvoker.deserialize(localVarResponse,  "", Object.class));
            } catch (ApiException exception) {
               errorListener.onErrorResponse(new VolleyError(exception));
            }
          }
      }, new Response.ErrorListener() {
          @Override
          public void onErrorResponse(VolleyError error) {
            errorListener.onErrorResponse(error);
          }
      });
    } catch (ApiException ex) {
      errorListener.onErrorResponse(new VolleyError(ex));
    }
  }
  /**
  * This method cancels all users orders and stop orders within all currencies and instrument kinds.
  * 
   * @return Object
  */
  public Object privateCancelAllGet () throws TimeoutException, ExecutionException, InterruptedException, ApiException {
    Object postBody = null;

    // create path and map variables
    String path = "/private/cancel_all";

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();
    String[] contentTypes = {
    };
    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

    if (contentType.startsWith("multipart/form-data")) {
      // file uploading
      MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();
      HttpEntity httpEntity = localVarBuilder.build();
      postBody = httpEntity;
    } else {
      // normal form params
    }

    String[] authNames = new String[] { "bearerAuth" };

    try {
      String localVarResponse = apiInvoker.invokeAPI (basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType, authNames);
      if (localVarResponse != null) {
         return (Object) ApiInvoker.deserialize(localVarResponse, "", Object.class);
      } else {
         return null;
      }
    } catch (ApiException ex) {
       throw ex;
    } catch (InterruptedException ex) {
       throw ex;
    } catch (ExecutionException ex) {
      if (ex.getCause() instanceof VolleyError) {
        VolleyError volleyError = (VolleyError)ex.getCause();
        if (volleyError.networkResponse != null) {
          throw new ApiException(volleyError.networkResponse.statusCode, volleyError.getMessage());
        }
      }
      throw ex;
    } catch (TimeoutException ex) {
      throw ex;
    }
  }

      /**
   * This method cancels all users orders and stop orders within all currencies and instrument kinds.
   * 

  */
  public void privateCancelAllGet (final Response.Listener<Object> responseListener, final Response.ErrorListener errorListener) {
    Object postBody = null;


    // create path and map variables
    String path = "/private/cancel_all".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();



    String[] contentTypes = {
      
    };
    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

    if (contentType.startsWith("multipart/form-data")) {
      // file uploading
      MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();
      

      HttpEntity httpEntity = localVarBuilder.build();
      postBody = httpEntity;
    } else {
      // normal form params
          }

    String[] authNames = new String[] { "bearerAuth" };

    try {
      apiInvoker.invokeAPI(basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType, authNames,
        new Response.Listener<String>() {
          @Override
          public void onResponse(String localVarResponse) {
            try {
              responseListener.onResponse((Object) ApiInvoker.deserialize(localVarResponse,  "", Object.class));
            } catch (ApiException exception) {
               errorListener.onErrorResponse(new VolleyError(exception));
            }
          }
      }, new Response.ErrorListener() {
          @Override
          public void onErrorResponse(VolleyError error) {
            errorListener.onErrorResponse(error);
          }
      });
    } catch (ApiException ex) {
      errorListener.onErrorResponse(new VolleyError(ex));
    }
  }
  /**
  * Cancel an order, specified by order id
  * 
   * @param orderId The order id
   * @return Object
  */
  public Object privateCancelGet (String orderId) throws TimeoutException, ExecutionException, InterruptedException, ApiException {
    Object postBody = null;
    // verify the required parameter 'orderId' is set
    if (orderId == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'orderId' when calling privateCancelGet",
        new ApiException(400, "Missing the required parameter 'orderId' when calling privateCancelGet"));
    }

    // create path and map variables
    String path = "/private/cancel";

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();
    queryParams.addAll(ApiInvoker.parameterToPairs("", "order_id", orderId));
    String[] contentTypes = {
    };
    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

    if (contentType.startsWith("multipart/form-data")) {
      // file uploading
      MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();
      HttpEntity httpEntity = localVarBuilder.build();
      postBody = httpEntity;
    } else {
      // normal form params
    }

    String[] authNames = new String[] { "bearerAuth" };

    try {
      String localVarResponse = apiInvoker.invokeAPI (basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType, authNames);
      if (localVarResponse != null) {
         return (Object) ApiInvoker.deserialize(localVarResponse, "", Object.class);
      } else {
         return null;
      }
    } catch (ApiException ex) {
       throw ex;
    } catch (InterruptedException ex) {
       throw ex;
    } catch (ExecutionException ex) {
      if (ex.getCause() instanceof VolleyError) {
        VolleyError volleyError = (VolleyError)ex.getCause();
        if (volleyError.networkResponse != null) {
          throw new ApiException(volleyError.networkResponse.statusCode, volleyError.getMessage());
        }
      }
      throw ex;
    } catch (TimeoutException ex) {
      throw ex;
    }
  }

      /**
   * Cancel an order, specified by order id
   * 
   * @param orderId The order id
  */
  public void privateCancelGet (String orderId, final Response.Listener<Object> responseListener, final Response.ErrorListener errorListener) {
    Object postBody = null;

    // verify the required parameter 'orderId' is set
    if (orderId == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'orderId' when calling privateCancelGet",
        new ApiException(400, "Missing the required parameter 'orderId' when calling privateCancelGet"));
    }

    // create path and map variables
    String path = "/private/cancel".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    queryParams.addAll(ApiInvoker.parameterToPairs("", "order_id", orderId));


    String[] contentTypes = {
      
    };
    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

    if (contentType.startsWith("multipart/form-data")) {
      // file uploading
      MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();
      

      HttpEntity httpEntity = localVarBuilder.build();
      postBody = httpEntity;
    } else {
      // normal form params
          }

    String[] authNames = new String[] { "bearerAuth" };

    try {
      apiInvoker.invokeAPI(basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType, authNames,
        new Response.Listener<String>() {
          @Override
          public void onResponse(String localVarResponse) {
            try {
              responseListener.onResponse((Object) ApiInvoker.deserialize(localVarResponse,  "", Object.class));
            } catch (ApiException exception) {
               errorListener.onErrorResponse(new VolleyError(exception));
            }
          }
      }, new Response.ErrorListener() {
          @Override
          public void onErrorResponse(VolleyError error) {
            errorListener.onErrorResponse(error);
          }
      });
    } catch (ApiException ex) {
      errorListener.onErrorResponse(new VolleyError(ex));
    }
  }
  /**
  * Cancel transfer
  * 
   * @param currency The currency symbol
   * @param id Id of transfer
   * @param tfa TFA code, required when TFA is enabled for current account
   * @return Object
  */
  public Object privateCancelTransferByIdGet (String currency, Integer id, String tfa) throws TimeoutException, ExecutionException, InterruptedException, ApiException {
    Object postBody = null;
    // verify the required parameter 'currency' is set
    if (currency == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'currency' when calling privateCancelTransferByIdGet",
        new ApiException(400, "Missing the required parameter 'currency' when calling privateCancelTransferByIdGet"));
    }
    // verify the required parameter 'id' is set
    if (id == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'id' when calling privateCancelTransferByIdGet",
        new ApiException(400, "Missing the required parameter 'id' when calling privateCancelTransferByIdGet"));
    }

    // create path and map variables
    String path = "/private/cancel_transfer_by_id";

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();
    queryParams.addAll(ApiInvoker.parameterToPairs("", "currency", currency));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "id", id));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "tfa", tfa));
    String[] contentTypes = {
    };
    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

    if (contentType.startsWith("multipart/form-data")) {
      // file uploading
      MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();
      HttpEntity httpEntity = localVarBuilder.build();
      postBody = httpEntity;
    } else {
      // normal form params
    }

    String[] authNames = new String[] { "bearerAuth" };

    try {
      String localVarResponse = apiInvoker.invokeAPI (basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType, authNames);
      if (localVarResponse != null) {
         return (Object) ApiInvoker.deserialize(localVarResponse, "", Object.class);
      } else {
         return null;
      }
    } catch (ApiException ex) {
       throw ex;
    } catch (InterruptedException ex) {
       throw ex;
    } catch (ExecutionException ex) {
      if (ex.getCause() instanceof VolleyError) {
        VolleyError volleyError = (VolleyError)ex.getCause();
        if (volleyError.networkResponse != null) {
          throw new ApiException(volleyError.networkResponse.statusCode, volleyError.getMessage());
        }
      }
      throw ex;
    } catch (TimeoutException ex) {
      throw ex;
    }
  }

      /**
   * Cancel transfer
   * 
   * @param currency The currency symbol   * @param id Id of transfer   * @param tfa TFA code, required when TFA is enabled for current account
  */
  public void privateCancelTransferByIdGet (String currency, Integer id, String tfa, final Response.Listener<Object> responseListener, final Response.ErrorListener errorListener) {
    Object postBody = null;

    // verify the required parameter 'currency' is set
    if (currency == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'currency' when calling privateCancelTransferByIdGet",
        new ApiException(400, "Missing the required parameter 'currency' when calling privateCancelTransferByIdGet"));
    }
    // verify the required parameter 'id' is set
    if (id == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'id' when calling privateCancelTransferByIdGet",
        new ApiException(400, "Missing the required parameter 'id' when calling privateCancelTransferByIdGet"));
    }

    // create path and map variables
    String path = "/private/cancel_transfer_by_id".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    queryParams.addAll(ApiInvoker.parameterToPairs("", "currency", currency));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "id", id));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "tfa", tfa));


    String[] contentTypes = {
      
    };
    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

    if (contentType.startsWith("multipart/form-data")) {
      // file uploading
      MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();
      

      HttpEntity httpEntity = localVarBuilder.build();
      postBody = httpEntity;
    } else {
      // normal form params
          }

    String[] authNames = new String[] { "bearerAuth" };

    try {
      apiInvoker.invokeAPI(basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType, authNames,
        new Response.Listener<String>() {
          @Override
          public void onResponse(String localVarResponse) {
            try {
              responseListener.onResponse((Object) ApiInvoker.deserialize(localVarResponse,  "", Object.class));
            } catch (ApiException exception) {
               errorListener.onErrorResponse(new VolleyError(exception));
            }
          }
      }, new Response.ErrorListener() {
          @Override
          public void onErrorResponse(VolleyError error) {
            errorListener.onErrorResponse(error);
          }
      });
    } catch (ApiException ex) {
      errorListener.onErrorResponse(new VolleyError(ex));
    }
  }
  /**
  * Cancels withdrawal request
  * 
   * @param currency The currency symbol
   * @param id The withdrawal id
   * @return Object
  */
  public Object privateCancelWithdrawalGet (String currency, BigDecimal id) throws TimeoutException, ExecutionException, InterruptedException, ApiException {
    Object postBody = null;
    // verify the required parameter 'currency' is set
    if (currency == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'currency' when calling privateCancelWithdrawalGet",
        new ApiException(400, "Missing the required parameter 'currency' when calling privateCancelWithdrawalGet"));
    }
    // verify the required parameter 'id' is set
    if (id == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'id' when calling privateCancelWithdrawalGet",
        new ApiException(400, "Missing the required parameter 'id' when calling privateCancelWithdrawalGet"));
    }

    // create path and map variables
    String path = "/private/cancel_withdrawal";

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();
    queryParams.addAll(ApiInvoker.parameterToPairs("", "currency", currency));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "id", id));
    String[] contentTypes = {
    };
    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

    if (contentType.startsWith("multipart/form-data")) {
      // file uploading
      MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();
      HttpEntity httpEntity = localVarBuilder.build();
      postBody = httpEntity;
    } else {
      // normal form params
    }

    String[] authNames = new String[] { "bearerAuth" };

    try {
      String localVarResponse = apiInvoker.invokeAPI (basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType, authNames);
      if (localVarResponse != null) {
         return (Object) ApiInvoker.deserialize(localVarResponse, "", Object.class);
      } else {
         return null;
      }
    } catch (ApiException ex) {
       throw ex;
    } catch (InterruptedException ex) {
       throw ex;
    } catch (ExecutionException ex) {
      if (ex.getCause() instanceof VolleyError) {
        VolleyError volleyError = (VolleyError)ex.getCause();
        if (volleyError.networkResponse != null) {
          throw new ApiException(volleyError.networkResponse.statusCode, volleyError.getMessage());
        }
      }
      throw ex;
    } catch (TimeoutException ex) {
      throw ex;
    }
  }

      /**
   * Cancels withdrawal request
   * 
   * @param currency The currency symbol   * @param id The withdrawal id
  */
  public void privateCancelWithdrawalGet (String currency, BigDecimal id, final Response.Listener<Object> responseListener, final Response.ErrorListener errorListener) {
    Object postBody = null;

    // verify the required parameter 'currency' is set
    if (currency == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'currency' when calling privateCancelWithdrawalGet",
        new ApiException(400, "Missing the required parameter 'currency' when calling privateCancelWithdrawalGet"));
    }
    // verify the required parameter 'id' is set
    if (id == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'id' when calling privateCancelWithdrawalGet",
        new ApiException(400, "Missing the required parameter 'id' when calling privateCancelWithdrawalGet"));
    }

    // create path and map variables
    String path = "/private/cancel_withdrawal".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    queryParams.addAll(ApiInvoker.parameterToPairs("", "currency", currency));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "id", id));


    String[] contentTypes = {
      
    };
    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

    if (contentType.startsWith("multipart/form-data")) {
      // file uploading
      MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();
      

      HttpEntity httpEntity = localVarBuilder.build();
      postBody = httpEntity;
    } else {
      // normal form params
          }

    String[] authNames = new String[] { "bearerAuth" };

    try {
      apiInvoker.invokeAPI(basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType, authNames,
        new Response.Listener<String>() {
          @Override
          public void onResponse(String localVarResponse) {
            try {
              responseListener.onResponse((Object) ApiInvoker.deserialize(localVarResponse,  "", Object.class));
            } catch (ApiException exception) {
               errorListener.onErrorResponse(new VolleyError(exception));
            }
          }
      }, new Response.ErrorListener() {
          @Override
          public void onErrorResponse(VolleyError error) {
            errorListener.onErrorResponse(error);
          }
      });
    } catch (ApiException ex) {
      errorListener.onErrorResponse(new VolleyError(ex));
    }
  }
  /**
  * Change the user name for a subaccount
  * 
   * @param sid The user id for the subaccount
   * @param name The new user name
   * @return Object
  */
  public Object privateChangeSubaccountNameGet (Integer sid, String name) throws TimeoutException, ExecutionException, InterruptedException, ApiException {
    Object postBody = null;
    // verify the required parameter 'sid' is set
    if (sid == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'sid' when calling privateChangeSubaccountNameGet",
        new ApiException(400, "Missing the required parameter 'sid' when calling privateChangeSubaccountNameGet"));
    }
    // verify the required parameter 'name' is set
    if (name == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'name' when calling privateChangeSubaccountNameGet",
        new ApiException(400, "Missing the required parameter 'name' when calling privateChangeSubaccountNameGet"));
    }

    // create path and map variables
    String path = "/private/change_subaccount_name";

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();
    queryParams.addAll(ApiInvoker.parameterToPairs("", "sid", sid));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "name", name));
    String[] contentTypes = {
    };
    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

    if (contentType.startsWith("multipart/form-data")) {
      // file uploading
      MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();
      HttpEntity httpEntity = localVarBuilder.build();
      postBody = httpEntity;
    } else {
      // normal form params
    }

    String[] authNames = new String[] { "bearerAuth" };

    try {
      String localVarResponse = apiInvoker.invokeAPI (basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType, authNames);
      if (localVarResponse != null) {
         return (Object) ApiInvoker.deserialize(localVarResponse, "", Object.class);
      } else {
         return null;
      }
    } catch (ApiException ex) {
       throw ex;
    } catch (InterruptedException ex) {
       throw ex;
    } catch (ExecutionException ex) {
      if (ex.getCause() instanceof VolleyError) {
        VolleyError volleyError = (VolleyError)ex.getCause();
        if (volleyError.networkResponse != null) {
          throw new ApiException(volleyError.networkResponse.statusCode, volleyError.getMessage());
        }
      }
      throw ex;
    } catch (TimeoutException ex) {
      throw ex;
    }
  }

      /**
   * Change the user name for a subaccount
   * 
   * @param sid The user id for the subaccount   * @param name The new user name
  */
  public void privateChangeSubaccountNameGet (Integer sid, String name, final Response.Listener<Object> responseListener, final Response.ErrorListener errorListener) {
    Object postBody = null;

    // verify the required parameter 'sid' is set
    if (sid == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'sid' when calling privateChangeSubaccountNameGet",
        new ApiException(400, "Missing the required parameter 'sid' when calling privateChangeSubaccountNameGet"));
    }
    // verify the required parameter 'name' is set
    if (name == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'name' when calling privateChangeSubaccountNameGet",
        new ApiException(400, "Missing the required parameter 'name' when calling privateChangeSubaccountNameGet"));
    }

    // create path and map variables
    String path = "/private/change_subaccount_name".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    queryParams.addAll(ApiInvoker.parameterToPairs("", "sid", sid));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "name", name));


    String[] contentTypes = {
      
    };
    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

    if (contentType.startsWith("multipart/form-data")) {
      // file uploading
      MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();
      

      HttpEntity httpEntity = localVarBuilder.build();
      postBody = httpEntity;
    } else {
      // normal form params
          }

    String[] authNames = new String[] { "bearerAuth" };

    try {
      apiInvoker.invokeAPI(basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType, authNames,
        new Response.Listener<String>() {
          @Override
          public void onResponse(String localVarResponse) {
            try {
              responseListener.onResponse((Object) ApiInvoker.deserialize(localVarResponse,  "", Object.class));
            } catch (ApiException exception) {
               errorListener.onErrorResponse(new VolleyError(exception));
            }
          }
      }, new Response.ErrorListener() {
          @Override
          public void onErrorResponse(VolleyError error) {
            errorListener.onErrorResponse(error);
          }
      });
    } catch (ApiException ex) {
      errorListener.onErrorResponse(new VolleyError(ex));
    }
  }
  /**
  * Makes closing position reduce only order .
  * 
   * @param instrumentName Instrument name
   * @param type The order type
   * @param price Optional price for limit order.
   * @return Object
  */
  public Object privateClosePositionGet (String instrumentName, String type, BigDecimal price) throws TimeoutException, ExecutionException, InterruptedException, ApiException {
    Object postBody = null;
    // verify the required parameter 'instrumentName' is set
    if (instrumentName == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'instrumentName' when calling privateClosePositionGet",
        new ApiException(400, "Missing the required parameter 'instrumentName' when calling privateClosePositionGet"));
    }
    // verify the required parameter 'type' is set
    if (type == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'type' when calling privateClosePositionGet",
        new ApiException(400, "Missing the required parameter 'type' when calling privateClosePositionGet"));
    }

    // create path and map variables
    String path = "/private/close_position";

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();
    queryParams.addAll(ApiInvoker.parameterToPairs("", "instrument_name", instrumentName));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "type", type));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "price", price));
    String[] contentTypes = {
    };
    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

    if (contentType.startsWith("multipart/form-data")) {
      // file uploading
      MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();
      HttpEntity httpEntity = localVarBuilder.build();
      postBody = httpEntity;
    } else {
      // normal form params
    }

    String[] authNames = new String[] { "bearerAuth" };

    try {
      String localVarResponse = apiInvoker.invokeAPI (basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType, authNames);
      if (localVarResponse != null) {
         return (Object) ApiInvoker.deserialize(localVarResponse, "", Object.class);
      } else {
         return null;
      }
    } catch (ApiException ex) {
       throw ex;
    } catch (InterruptedException ex) {
       throw ex;
    } catch (ExecutionException ex) {
      if (ex.getCause() instanceof VolleyError) {
        VolleyError volleyError = (VolleyError)ex.getCause();
        if (volleyError.networkResponse != null) {
          throw new ApiException(volleyError.networkResponse.statusCode, volleyError.getMessage());
        }
      }
      throw ex;
    } catch (TimeoutException ex) {
      throw ex;
    }
  }

      /**
   * Makes closing position reduce only order .
   * 
   * @param instrumentName Instrument name   * @param type The order type   * @param price Optional price for limit order.
  */
  public void privateClosePositionGet (String instrumentName, String type, BigDecimal price, final Response.Listener<Object> responseListener, final Response.ErrorListener errorListener) {
    Object postBody = null;

    // verify the required parameter 'instrumentName' is set
    if (instrumentName == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'instrumentName' when calling privateClosePositionGet",
        new ApiException(400, "Missing the required parameter 'instrumentName' when calling privateClosePositionGet"));
    }
    // verify the required parameter 'type' is set
    if (type == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'type' when calling privateClosePositionGet",
        new ApiException(400, "Missing the required parameter 'type' when calling privateClosePositionGet"));
    }

    // create path and map variables
    String path = "/private/close_position".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    queryParams.addAll(ApiInvoker.parameterToPairs("", "instrument_name", instrumentName));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "type", type));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "price", price));


    String[] contentTypes = {
      
    };
    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

    if (contentType.startsWith("multipart/form-data")) {
      // file uploading
      MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();
      

      HttpEntity httpEntity = localVarBuilder.build();
      postBody = httpEntity;
    } else {
      // normal form params
          }

    String[] authNames = new String[] { "bearerAuth" };

    try {
      apiInvoker.invokeAPI(basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType, authNames,
        new Response.Listener<String>() {
          @Override
          public void onResponse(String localVarResponse) {
            try {
              responseListener.onResponse((Object) ApiInvoker.deserialize(localVarResponse,  "", Object.class));
            } catch (ApiException exception) {
               errorListener.onErrorResponse(new VolleyError(exception));
            }
          }
      }, new Response.ErrorListener() {
          @Override
          public void onErrorResponse(VolleyError error) {
            errorListener.onErrorResponse(error);
          }
      });
    } catch (ApiException ex) {
      errorListener.onErrorResponse(new VolleyError(ex));
    }
  }
  /**
  * Creates deposit address in currency
  * 
   * @param currency The currency symbol
   * @return Object
  */
  public Object privateCreateDepositAddressGet (String currency) throws TimeoutException, ExecutionException, InterruptedException, ApiException {
    Object postBody = null;
    // verify the required parameter 'currency' is set
    if (currency == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'currency' when calling privateCreateDepositAddressGet",
        new ApiException(400, "Missing the required parameter 'currency' when calling privateCreateDepositAddressGet"));
    }

    // create path and map variables
    String path = "/private/create_deposit_address";

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();
    queryParams.addAll(ApiInvoker.parameterToPairs("", "currency", currency));
    String[] contentTypes = {
    };
    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

    if (contentType.startsWith("multipart/form-data")) {
      // file uploading
      MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();
      HttpEntity httpEntity = localVarBuilder.build();
      postBody = httpEntity;
    } else {
      // normal form params
    }

    String[] authNames = new String[] { "bearerAuth" };

    try {
      String localVarResponse = apiInvoker.invokeAPI (basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType, authNames);
      if (localVarResponse != null) {
         return (Object) ApiInvoker.deserialize(localVarResponse, "", Object.class);
      } else {
         return null;
      }
    } catch (ApiException ex) {
       throw ex;
    } catch (InterruptedException ex) {
       throw ex;
    } catch (ExecutionException ex) {
      if (ex.getCause() instanceof VolleyError) {
        VolleyError volleyError = (VolleyError)ex.getCause();
        if (volleyError.networkResponse != null) {
          throw new ApiException(volleyError.networkResponse.statusCode, volleyError.getMessage());
        }
      }
      throw ex;
    } catch (TimeoutException ex) {
      throw ex;
    }
  }

      /**
   * Creates deposit address in currency
   * 
   * @param currency The currency symbol
  */
  public void privateCreateDepositAddressGet (String currency, final Response.Listener<Object> responseListener, final Response.ErrorListener errorListener) {
    Object postBody = null;

    // verify the required parameter 'currency' is set
    if (currency == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'currency' when calling privateCreateDepositAddressGet",
        new ApiException(400, "Missing the required parameter 'currency' when calling privateCreateDepositAddressGet"));
    }

    // create path and map variables
    String path = "/private/create_deposit_address".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    queryParams.addAll(ApiInvoker.parameterToPairs("", "currency", currency));


    String[] contentTypes = {
      
    };
    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

    if (contentType.startsWith("multipart/form-data")) {
      // file uploading
      MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();
      

      HttpEntity httpEntity = localVarBuilder.build();
      postBody = httpEntity;
    } else {
      // normal form params
          }

    String[] authNames = new String[] { "bearerAuth" };

    try {
      apiInvoker.invokeAPI(basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType, authNames,
        new Response.Listener<String>() {
          @Override
          public void onResponse(String localVarResponse) {
            try {
              responseListener.onResponse((Object) ApiInvoker.deserialize(localVarResponse,  "", Object.class));
            } catch (ApiException exception) {
               errorListener.onErrorResponse(new VolleyError(exception));
            }
          }
      }, new Response.ErrorListener() {
          @Override
          public void onErrorResponse(VolleyError error) {
            errorListener.onErrorResponse(error);
          }
      });
    } catch (ApiException ex) {
      errorListener.onErrorResponse(new VolleyError(ex));
    }
  }
  /**
  * Create a new subaccount
  * 
   * @return Object
  */
  public Object privateCreateSubaccountGet () throws TimeoutException, ExecutionException, InterruptedException, ApiException {
    Object postBody = null;

    // create path and map variables
    String path = "/private/create_subaccount";

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();
    String[] contentTypes = {
    };
    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

    if (contentType.startsWith("multipart/form-data")) {
      // file uploading
      MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();
      HttpEntity httpEntity = localVarBuilder.build();
      postBody = httpEntity;
    } else {
      // normal form params
    }

    String[] authNames = new String[] { "bearerAuth" };

    try {
      String localVarResponse = apiInvoker.invokeAPI (basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType, authNames);
      if (localVarResponse != null) {
         return (Object) ApiInvoker.deserialize(localVarResponse, "", Object.class);
      } else {
         return null;
      }
    } catch (ApiException ex) {
       throw ex;
    } catch (InterruptedException ex) {
       throw ex;
    } catch (ExecutionException ex) {
      if (ex.getCause() instanceof VolleyError) {
        VolleyError volleyError = (VolleyError)ex.getCause();
        if (volleyError.networkResponse != null) {
          throw new ApiException(volleyError.networkResponse.statusCode, volleyError.getMessage());
        }
      }
      throw ex;
    } catch (TimeoutException ex) {
      throw ex;
    }
  }

      /**
   * Create a new subaccount
   * 

  */
  public void privateCreateSubaccountGet (final Response.Listener<Object> responseListener, final Response.ErrorListener errorListener) {
    Object postBody = null;


    // create path and map variables
    String path = "/private/create_subaccount".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();



    String[] contentTypes = {
      
    };
    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

    if (contentType.startsWith("multipart/form-data")) {
      // file uploading
      MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();
      

      HttpEntity httpEntity = localVarBuilder.build();
      postBody = httpEntity;
    } else {
      // normal form params
          }

    String[] authNames = new String[] { "bearerAuth" };

    try {
      apiInvoker.invokeAPI(basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType, authNames,
        new Response.Listener<String>() {
          @Override
          public void onResponse(String localVarResponse) {
            try {
              responseListener.onResponse((Object) ApiInvoker.deserialize(localVarResponse,  "", Object.class));
            } catch (ApiException exception) {
               errorListener.onErrorResponse(new VolleyError(exception));
            }
          }
      }, new Response.ErrorListener() {
          @Override
          public void onErrorResponse(VolleyError error) {
            errorListener.onErrorResponse(error);
          }
      });
    } catch (ApiException ex) {
      errorListener.onErrorResponse(new VolleyError(ex));
    }
  }
  /**
  * Disable two factor authentication for a subaccount.
  * 
   * @param sid The user id for the subaccount
   * @return Object
  */
  public Object privateDisableTfaForSubaccountGet (Integer sid) throws TimeoutException, ExecutionException, InterruptedException, ApiException {
    Object postBody = null;
    // verify the required parameter 'sid' is set
    if (sid == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'sid' when calling privateDisableTfaForSubaccountGet",
        new ApiException(400, "Missing the required parameter 'sid' when calling privateDisableTfaForSubaccountGet"));
    }

    // create path and map variables
    String path = "/private/disable_tfa_for_subaccount";

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();
    queryParams.addAll(ApiInvoker.parameterToPairs("", "sid", sid));
    String[] contentTypes = {
    };
    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

    if (contentType.startsWith("multipart/form-data")) {
      // file uploading
      MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();
      HttpEntity httpEntity = localVarBuilder.build();
      postBody = httpEntity;
    } else {
      // normal form params
    }

    String[] authNames = new String[] { "bearerAuth" };

    try {
      String localVarResponse = apiInvoker.invokeAPI (basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType, authNames);
      if (localVarResponse != null) {
         return (Object) ApiInvoker.deserialize(localVarResponse, "", Object.class);
      } else {
         return null;
      }
    } catch (ApiException ex) {
       throw ex;
    } catch (InterruptedException ex) {
       throw ex;
    } catch (ExecutionException ex) {
      if (ex.getCause() instanceof VolleyError) {
        VolleyError volleyError = (VolleyError)ex.getCause();
        if (volleyError.networkResponse != null) {
          throw new ApiException(volleyError.networkResponse.statusCode, volleyError.getMessage());
        }
      }
      throw ex;
    } catch (TimeoutException ex) {
      throw ex;
    }
  }

      /**
   * Disable two factor authentication for a subaccount.
   * 
   * @param sid The user id for the subaccount
  */
  public void privateDisableTfaForSubaccountGet (Integer sid, final Response.Listener<Object> responseListener, final Response.ErrorListener errorListener) {
    Object postBody = null;

    // verify the required parameter 'sid' is set
    if (sid == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'sid' when calling privateDisableTfaForSubaccountGet",
        new ApiException(400, "Missing the required parameter 'sid' when calling privateDisableTfaForSubaccountGet"));
    }

    // create path and map variables
    String path = "/private/disable_tfa_for_subaccount".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    queryParams.addAll(ApiInvoker.parameterToPairs("", "sid", sid));


    String[] contentTypes = {
      
    };
    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

    if (contentType.startsWith("multipart/form-data")) {
      // file uploading
      MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();
      

      HttpEntity httpEntity = localVarBuilder.build();
      postBody = httpEntity;
    } else {
      // normal form params
          }

    String[] authNames = new String[] { "bearerAuth" };

    try {
      apiInvoker.invokeAPI(basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType, authNames,
        new Response.Listener<String>() {
          @Override
          public void onResponse(String localVarResponse) {
            try {
              responseListener.onResponse((Object) ApiInvoker.deserialize(localVarResponse,  "", Object.class));
            } catch (ApiException exception) {
               errorListener.onErrorResponse(new VolleyError(exception));
            }
          }
      }, new Response.ErrorListener() {
          @Override
          public void onErrorResponse(VolleyError error) {
            errorListener.onErrorResponse(error);
          }
      });
    } catch (ApiException ex) {
      errorListener.onErrorResponse(new VolleyError(ex));
    }
  }
  /**
  * Disables TFA with one time recovery code
  * 
   * @param password The password for the subaccount
   * @param code One time recovery code
   * @return Object
  */
  public Object privateDisableTfaWithRecoveryCodeGet (String password, String code) throws TimeoutException, ExecutionException, InterruptedException, ApiException {
    Object postBody = null;
    // verify the required parameter 'password' is set
    if (password == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'password' when calling privateDisableTfaWithRecoveryCodeGet",
        new ApiException(400, "Missing the required parameter 'password' when calling privateDisableTfaWithRecoveryCodeGet"));
    }
    // verify the required parameter 'code' is set
    if (code == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'code' when calling privateDisableTfaWithRecoveryCodeGet",
        new ApiException(400, "Missing the required parameter 'code' when calling privateDisableTfaWithRecoveryCodeGet"));
    }

    // create path and map variables
    String path = "/private/disable_tfa_with_recovery_code";

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();
    queryParams.addAll(ApiInvoker.parameterToPairs("", "password", password));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "code", code));
    String[] contentTypes = {
    };
    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

    if (contentType.startsWith("multipart/form-data")) {
      // file uploading
      MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();
      HttpEntity httpEntity = localVarBuilder.build();
      postBody = httpEntity;
    } else {
      // normal form params
    }

    String[] authNames = new String[] { "bearerAuth" };

    try {
      String localVarResponse = apiInvoker.invokeAPI (basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType, authNames);
      if (localVarResponse != null) {
         return (Object) ApiInvoker.deserialize(localVarResponse, "", Object.class);
      } else {
         return null;
      }
    } catch (ApiException ex) {
       throw ex;
    } catch (InterruptedException ex) {
       throw ex;
    } catch (ExecutionException ex) {
      if (ex.getCause() instanceof VolleyError) {
        VolleyError volleyError = (VolleyError)ex.getCause();
        if (volleyError.networkResponse != null) {
          throw new ApiException(volleyError.networkResponse.statusCode, volleyError.getMessage());
        }
      }
      throw ex;
    } catch (TimeoutException ex) {
      throw ex;
    }
  }

      /**
   * Disables TFA with one time recovery code
   * 
   * @param password The password for the subaccount   * @param code One time recovery code
  */
  public void privateDisableTfaWithRecoveryCodeGet (String password, String code, final Response.Listener<Object> responseListener, final Response.ErrorListener errorListener) {
    Object postBody = null;

    // verify the required parameter 'password' is set
    if (password == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'password' when calling privateDisableTfaWithRecoveryCodeGet",
        new ApiException(400, "Missing the required parameter 'password' when calling privateDisableTfaWithRecoveryCodeGet"));
    }
    // verify the required parameter 'code' is set
    if (code == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'code' when calling privateDisableTfaWithRecoveryCodeGet",
        new ApiException(400, "Missing the required parameter 'code' when calling privateDisableTfaWithRecoveryCodeGet"));
    }

    // create path and map variables
    String path = "/private/disable_tfa_with_recovery_code".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    queryParams.addAll(ApiInvoker.parameterToPairs("", "password", password));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "code", code));


    String[] contentTypes = {
      
    };
    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

    if (contentType.startsWith("multipart/form-data")) {
      // file uploading
      MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();
      

      HttpEntity httpEntity = localVarBuilder.build();
      postBody = httpEntity;
    } else {
      // normal form params
          }

    String[] authNames = new String[] { "bearerAuth" };

    try {
      apiInvoker.invokeAPI(basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType, authNames,
        new Response.Listener<String>() {
          @Override
          public void onResponse(String localVarResponse) {
            try {
              responseListener.onResponse((Object) ApiInvoker.deserialize(localVarResponse,  "", Object.class));
            } catch (ApiException exception) {
               errorListener.onErrorResponse(new VolleyError(exception));
            }
          }
      }, new Response.ErrorListener() {
          @Override
          public void onErrorResponse(VolleyError error) {
            errorListener.onErrorResponse(error);
          }
      });
    } catch (ApiException ex) {
      errorListener.onErrorResponse(new VolleyError(ex));
    }
  }
  /**
  * Change price, amount and/or other properties of an order.
  * 
   * @param orderId The order id
   * @param amount It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH
   * @param price &lt;p&gt;The order price in base currency.&lt;/p&gt; &lt;p&gt;When editing an option order with advanced&#x3D;usd, the field price should be the option price value in USD.&lt;/p&gt; &lt;p&gt;When editing an option order with advanced&#x3D;implv, the field price should be a value of implied volatility in percentages. For example,  price&#x3D;100, means implied volatility of 100%&lt;/p&gt;
   * @param postOnly &lt;p&gt;If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.&lt;/p&gt; &lt;p&gt;Only valid in combination with time_in_force&#x3D;&#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt;
   * @param advanced Advanced option order type. If you have posted an advanced option order, it is necessary to re-supply this parameter when editing it (Only for options)
   * @param stopPrice Stop price, required for stop limit orders (Only for stop orders)
   * @return Object
  */
  public Object privateEditGet (String orderId, BigDecimal amount, BigDecimal price, Boolean postOnly, String advanced, BigDecimal stopPrice) throws TimeoutException, ExecutionException, InterruptedException, ApiException {
    Object postBody = null;
    // verify the required parameter 'orderId' is set
    if (orderId == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'orderId' when calling privateEditGet",
        new ApiException(400, "Missing the required parameter 'orderId' when calling privateEditGet"));
    }
    // verify the required parameter 'amount' is set
    if (amount == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'amount' when calling privateEditGet",
        new ApiException(400, "Missing the required parameter 'amount' when calling privateEditGet"));
    }
    // verify the required parameter 'price' is set
    if (price == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'price' when calling privateEditGet",
        new ApiException(400, "Missing the required parameter 'price' when calling privateEditGet"));
    }

    // create path and map variables
    String path = "/private/edit";

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();
    queryParams.addAll(ApiInvoker.parameterToPairs("", "order_id", orderId));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "amount", amount));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "price", price));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "post_only", postOnly));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "advanced", advanced));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "stop_price", stopPrice));
    String[] contentTypes = {
    };
    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

    if (contentType.startsWith("multipart/form-data")) {
      // file uploading
      MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();
      HttpEntity httpEntity = localVarBuilder.build();
      postBody = httpEntity;
    } else {
      // normal form params
    }

    String[] authNames = new String[] { "bearerAuth" };

    try {
      String localVarResponse = apiInvoker.invokeAPI (basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType, authNames);
      if (localVarResponse != null) {
         return (Object) ApiInvoker.deserialize(localVarResponse, "", Object.class);
      } else {
         return null;
      }
    } catch (ApiException ex) {
       throw ex;
    } catch (InterruptedException ex) {
       throw ex;
    } catch (ExecutionException ex) {
      if (ex.getCause() instanceof VolleyError) {
        VolleyError volleyError = (VolleyError)ex.getCause();
        if (volleyError.networkResponse != null) {
          throw new ApiException(volleyError.networkResponse.statusCode, volleyError.getMessage());
        }
      }
      throw ex;
    } catch (TimeoutException ex) {
      throw ex;
    }
  }

      /**
   * Change price, amount and/or other properties of an order.
   * 
   * @param orderId The order id   * @param amount It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH   * @param price &lt;p&gt;The order price in base currency.&lt;/p&gt; &lt;p&gt;When editing an option order with advanced&#x3D;usd, the field price should be the option price value in USD.&lt;/p&gt; &lt;p&gt;When editing an option order with advanced&#x3D;implv, the field price should be a value of implied volatility in percentages. For example,  price&#x3D;100, means implied volatility of 100%&lt;/p&gt;   * @param postOnly &lt;p&gt;If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.&lt;/p&gt; &lt;p&gt;Only valid in combination with time_in_force&#x3D;&#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt;   * @param advanced Advanced option order type. If you have posted an advanced option order, it is necessary to re-supply this parameter when editing it (Only for options)   * @param stopPrice Stop price, required for stop limit orders (Only for stop orders)
  */
  public void privateEditGet (String orderId, BigDecimal amount, BigDecimal price, Boolean postOnly, String advanced, BigDecimal stopPrice, final Response.Listener<Object> responseListener, final Response.ErrorListener errorListener) {
    Object postBody = null;

    // verify the required parameter 'orderId' is set
    if (orderId == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'orderId' when calling privateEditGet",
        new ApiException(400, "Missing the required parameter 'orderId' when calling privateEditGet"));
    }
    // verify the required parameter 'amount' is set
    if (amount == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'amount' when calling privateEditGet",
        new ApiException(400, "Missing the required parameter 'amount' when calling privateEditGet"));
    }
    // verify the required parameter 'price' is set
    if (price == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'price' when calling privateEditGet",
        new ApiException(400, "Missing the required parameter 'price' when calling privateEditGet"));
    }

    // create path and map variables
    String path = "/private/edit".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    queryParams.addAll(ApiInvoker.parameterToPairs("", "order_id", orderId));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "amount", amount));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "price", price));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "post_only", postOnly));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "advanced", advanced));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "stop_price", stopPrice));


    String[] contentTypes = {
      
    };
    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

    if (contentType.startsWith("multipart/form-data")) {
      // file uploading
      MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();
      

      HttpEntity httpEntity = localVarBuilder.build();
      postBody = httpEntity;
    } else {
      // normal form params
          }

    String[] authNames = new String[] { "bearerAuth" };

    try {
      apiInvoker.invokeAPI(basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType, authNames,
        new Response.Listener<String>() {
          @Override
          public void onResponse(String localVarResponse) {
            try {
              responseListener.onResponse((Object) ApiInvoker.deserialize(localVarResponse,  "", Object.class));
            } catch (ApiException exception) {
               errorListener.onErrorResponse(new VolleyError(exception));
            }
          }
      }, new Response.ErrorListener() {
          @Override
          public void onErrorResponse(VolleyError error) {
            errorListener.onErrorResponse(error);
          }
      });
    } catch (ApiException ex) {
      errorListener.onErrorResponse(new VolleyError(ex));
    }
  }
  /**
  * Retrieves user account summary.
  * 
   * @param currency The currency symbol
   * @param extended Include additional fields
   * @return Object
  */
  public Object privateGetAccountSummaryGet (String currency, Boolean extended) throws TimeoutException, ExecutionException, InterruptedException, ApiException {
    Object postBody = null;
    // verify the required parameter 'currency' is set
    if (currency == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'currency' when calling privateGetAccountSummaryGet",
        new ApiException(400, "Missing the required parameter 'currency' when calling privateGetAccountSummaryGet"));
    }

    // create path and map variables
    String path = "/private/get_account_summary";

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();
    queryParams.addAll(ApiInvoker.parameterToPairs("", "currency", currency));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "extended", extended));
    String[] contentTypes = {
    };
    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

    if (contentType.startsWith("multipart/form-data")) {
      // file uploading
      MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();
      HttpEntity httpEntity = localVarBuilder.build();
      postBody = httpEntity;
    } else {
      // normal form params
    }

    String[] authNames = new String[] { "bearerAuth" };

    try {
      String localVarResponse = apiInvoker.invokeAPI (basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType, authNames);
      if (localVarResponse != null) {
         return (Object) ApiInvoker.deserialize(localVarResponse, "", Object.class);
      } else {
         return null;
      }
    } catch (ApiException ex) {
       throw ex;
    } catch (InterruptedException ex) {
       throw ex;
    } catch (ExecutionException ex) {
      if (ex.getCause() instanceof VolleyError) {
        VolleyError volleyError = (VolleyError)ex.getCause();
        if (volleyError.networkResponse != null) {
          throw new ApiException(volleyError.networkResponse.statusCode, volleyError.getMessage());
        }
      }
      throw ex;
    } catch (TimeoutException ex) {
      throw ex;
    }
  }

      /**
   * Retrieves user account summary.
   * 
   * @param currency The currency symbol   * @param extended Include additional fields
  */
  public void privateGetAccountSummaryGet (String currency, Boolean extended, final Response.Listener<Object> responseListener, final Response.ErrorListener errorListener) {
    Object postBody = null;

    // verify the required parameter 'currency' is set
    if (currency == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'currency' when calling privateGetAccountSummaryGet",
        new ApiException(400, "Missing the required parameter 'currency' when calling privateGetAccountSummaryGet"));
    }

    // create path and map variables
    String path = "/private/get_account_summary".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    queryParams.addAll(ApiInvoker.parameterToPairs("", "currency", currency));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "extended", extended));


    String[] contentTypes = {
      
    };
    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

    if (contentType.startsWith("multipart/form-data")) {
      // file uploading
      MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();
      

      HttpEntity httpEntity = localVarBuilder.build();
      postBody = httpEntity;
    } else {
      // normal form params
          }

    String[] authNames = new String[] { "bearerAuth" };

    try {
      apiInvoker.invokeAPI(basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType, authNames,
        new Response.Listener<String>() {
          @Override
          public void onResponse(String localVarResponse) {
            try {
              responseListener.onResponse((Object) ApiInvoker.deserialize(localVarResponse,  "", Object.class));
            } catch (ApiException exception) {
               errorListener.onErrorResponse(new VolleyError(exception));
            }
          }
      }, new Response.ErrorListener() {
          @Override
          public void onErrorResponse(VolleyError error) {
            errorListener.onErrorResponse(error);
          }
      });
    } catch (ApiException ex) {
      errorListener.onErrorResponse(new VolleyError(ex));
    }
  }
  /**
  * Retrieves address book of given type
  * 
   * @param currency The currency symbol
   * @param type Address book type
   * @return Object
  */
  public Object privateGetAddressBookGet (String currency, String type) throws TimeoutException, ExecutionException, InterruptedException, ApiException {
    Object postBody = null;
    // verify the required parameter 'currency' is set
    if (currency == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'currency' when calling privateGetAddressBookGet",
        new ApiException(400, "Missing the required parameter 'currency' when calling privateGetAddressBookGet"));
    }
    // verify the required parameter 'type' is set
    if (type == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'type' when calling privateGetAddressBookGet",
        new ApiException(400, "Missing the required parameter 'type' when calling privateGetAddressBookGet"));
    }

    // create path and map variables
    String path = "/private/get_address_book";

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();
    queryParams.addAll(ApiInvoker.parameterToPairs("", "currency", currency));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "type", type));
    String[] contentTypes = {
    };
    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

    if (contentType.startsWith("multipart/form-data")) {
      // file uploading
      MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();
      HttpEntity httpEntity = localVarBuilder.build();
      postBody = httpEntity;
    } else {
      // normal form params
    }

    String[] authNames = new String[] { "bearerAuth" };

    try {
      String localVarResponse = apiInvoker.invokeAPI (basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType, authNames);
      if (localVarResponse != null) {
         return (Object) ApiInvoker.deserialize(localVarResponse, "", Object.class);
      } else {
         return null;
      }
    } catch (ApiException ex) {
       throw ex;
    } catch (InterruptedException ex) {
       throw ex;
    } catch (ExecutionException ex) {
      if (ex.getCause() instanceof VolleyError) {
        VolleyError volleyError = (VolleyError)ex.getCause();
        if (volleyError.networkResponse != null) {
          throw new ApiException(volleyError.networkResponse.statusCode, volleyError.getMessage());
        }
      }
      throw ex;
    } catch (TimeoutException ex) {
      throw ex;
    }
  }

      /**
   * Retrieves address book of given type
   * 
   * @param currency The currency symbol   * @param type Address book type
  */
  public void privateGetAddressBookGet (String currency, String type, final Response.Listener<Object> responseListener, final Response.ErrorListener errorListener) {
    Object postBody = null;

    // verify the required parameter 'currency' is set
    if (currency == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'currency' when calling privateGetAddressBookGet",
        new ApiException(400, "Missing the required parameter 'currency' when calling privateGetAddressBookGet"));
    }
    // verify the required parameter 'type' is set
    if (type == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'type' when calling privateGetAddressBookGet",
        new ApiException(400, "Missing the required parameter 'type' when calling privateGetAddressBookGet"));
    }

    // create path and map variables
    String path = "/private/get_address_book".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    queryParams.addAll(ApiInvoker.parameterToPairs("", "currency", currency));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "type", type));


    String[] contentTypes = {
      
    };
    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

    if (contentType.startsWith("multipart/form-data")) {
      // file uploading
      MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();
      

      HttpEntity httpEntity = localVarBuilder.build();
      postBody = httpEntity;
    } else {
      // normal form params
          }

    String[] authNames = new String[] { "bearerAuth" };

    try {
      apiInvoker.invokeAPI(basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType, authNames,
        new Response.Listener<String>() {
          @Override
          public void onResponse(String localVarResponse) {
            try {
              responseListener.onResponse((Object) ApiInvoker.deserialize(localVarResponse,  "", Object.class));
            } catch (ApiException exception) {
               errorListener.onErrorResponse(new VolleyError(exception));
            }
          }
      }, new Response.ErrorListener() {
          @Override
          public void onErrorResponse(VolleyError error) {
            errorListener.onErrorResponse(error);
          }
      });
    } catch (ApiException ex) {
      errorListener.onErrorResponse(new VolleyError(ex));
    }
  }
  /**
  * Retrieve deposit address for currency
  * 
   * @param currency The currency symbol
   * @return Object
  */
  public Object privateGetCurrentDepositAddressGet (String currency) throws TimeoutException, ExecutionException, InterruptedException, ApiException {
    Object postBody = null;
    // verify the required parameter 'currency' is set
    if (currency == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'currency' when calling privateGetCurrentDepositAddressGet",
        new ApiException(400, "Missing the required parameter 'currency' when calling privateGetCurrentDepositAddressGet"));
    }

    // create path and map variables
    String path = "/private/get_current_deposit_address";

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();
    queryParams.addAll(ApiInvoker.parameterToPairs("", "currency", currency));
    String[] contentTypes = {
    };
    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

    if (contentType.startsWith("multipart/form-data")) {
      // file uploading
      MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();
      HttpEntity httpEntity = localVarBuilder.build();
      postBody = httpEntity;
    } else {
      // normal form params
    }

    String[] authNames = new String[] { "bearerAuth" };

    try {
      String localVarResponse = apiInvoker.invokeAPI (basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType, authNames);
      if (localVarResponse != null) {
         return (Object) ApiInvoker.deserialize(localVarResponse, "", Object.class);
      } else {
         return null;
      }
    } catch (ApiException ex) {
       throw ex;
    } catch (InterruptedException ex) {
       throw ex;
    } catch (ExecutionException ex) {
      if (ex.getCause() instanceof VolleyError) {
        VolleyError volleyError = (VolleyError)ex.getCause();
        if (volleyError.networkResponse != null) {
          throw new ApiException(volleyError.networkResponse.statusCode, volleyError.getMessage());
        }
      }
      throw ex;
    } catch (TimeoutException ex) {
      throw ex;
    }
  }

      /**
   * Retrieve deposit address for currency
   * 
   * @param currency The currency symbol
  */
  public void privateGetCurrentDepositAddressGet (String currency, final Response.Listener<Object> responseListener, final Response.ErrorListener errorListener) {
    Object postBody = null;

    // verify the required parameter 'currency' is set
    if (currency == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'currency' when calling privateGetCurrentDepositAddressGet",
        new ApiException(400, "Missing the required parameter 'currency' when calling privateGetCurrentDepositAddressGet"));
    }

    // create path and map variables
    String path = "/private/get_current_deposit_address".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    queryParams.addAll(ApiInvoker.parameterToPairs("", "currency", currency));


    String[] contentTypes = {
      
    };
    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

    if (contentType.startsWith("multipart/form-data")) {
      // file uploading
      MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();
      

      HttpEntity httpEntity = localVarBuilder.build();
      postBody = httpEntity;
    } else {
      // normal form params
          }

    String[] authNames = new String[] { "bearerAuth" };

    try {
      apiInvoker.invokeAPI(basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType, authNames,
        new Response.Listener<String>() {
          @Override
          public void onResponse(String localVarResponse) {
            try {
              responseListener.onResponse((Object) ApiInvoker.deserialize(localVarResponse,  "", Object.class));
            } catch (ApiException exception) {
               errorListener.onErrorResponse(new VolleyError(exception));
            }
          }
      }, new Response.ErrorListener() {
          @Override
          public void onErrorResponse(VolleyError error) {
            errorListener.onErrorResponse(error);
          }
      });
    } catch (ApiException ex) {
      errorListener.onErrorResponse(new VolleyError(ex));
    }
  }
  /**
  * Retrieve the latest users deposits
  * 
   * @param currency The currency symbol
   * @param count Number of requested items, default - &#x60;10&#x60;
   * @param offset The offset for pagination, default - &#x60;0&#x60;
   * @return Object
  */
  public Object privateGetDepositsGet (String currency, Integer count, Integer offset) throws TimeoutException, ExecutionException, InterruptedException, ApiException {
    Object postBody = null;
    // verify the required parameter 'currency' is set
    if (currency == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'currency' when calling privateGetDepositsGet",
        new ApiException(400, "Missing the required parameter 'currency' when calling privateGetDepositsGet"));
    }

    // create path and map variables
    String path = "/private/get_deposits";

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();
    queryParams.addAll(ApiInvoker.parameterToPairs("", "currency", currency));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "count", count));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "offset", offset));
    String[] contentTypes = {
    };
    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

    if (contentType.startsWith("multipart/form-data")) {
      // file uploading
      MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();
      HttpEntity httpEntity = localVarBuilder.build();
      postBody = httpEntity;
    } else {
      // normal form params
    }

    String[] authNames = new String[] { "bearerAuth" };

    try {
      String localVarResponse = apiInvoker.invokeAPI (basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType, authNames);
      if (localVarResponse != null) {
         return (Object) ApiInvoker.deserialize(localVarResponse, "", Object.class);
      } else {
         return null;
      }
    } catch (ApiException ex) {
       throw ex;
    } catch (InterruptedException ex) {
       throw ex;
    } catch (ExecutionException ex) {
      if (ex.getCause() instanceof VolleyError) {
        VolleyError volleyError = (VolleyError)ex.getCause();
        if (volleyError.networkResponse != null) {
          throw new ApiException(volleyError.networkResponse.statusCode, volleyError.getMessage());
        }
      }
      throw ex;
    } catch (TimeoutException ex) {
      throw ex;
    }
  }

      /**
   * Retrieve the latest users deposits
   * 
   * @param currency The currency symbol   * @param count Number of requested items, default - &#x60;10&#x60;   * @param offset The offset for pagination, default - &#x60;0&#x60;
  */
  public void privateGetDepositsGet (String currency, Integer count, Integer offset, final Response.Listener<Object> responseListener, final Response.ErrorListener errorListener) {
    Object postBody = null;

    // verify the required parameter 'currency' is set
    if (currency == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'currency' when calling privateGetDepositsGet",
        new ApiException(400, "Missing the required parameter 'currency' when calling privateGetDepositsGet"));
    }

    // create path and map variables
    String path = "/private/get_deposits".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    queryParams.addAll(ApiInvoker.parameterToPairs("", "currency", currency));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "count", count));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "offset", offset));


    String[] contentTypes = {
      
    };
    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

    if (contentType.startsWith("multipart/form-data")) {
      // file uploading
      MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();
      

      HttpEntity httpEntity = localVarBuilder.build();
      postBody = httpEntity;
    } else {
      // normal form params
          }

    String[] authNames = new String[] { "bearerAuth" };

    try {
      apiInvoker.invokeAPI(basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType, authNames,
        new Response.Listener<String>() {
          @Override
          public void onResponse(String localVarResponse) {
            try {
              responseListener.onResponse((Object) ApiInvoker.deserialize(localVarResponse,  "", Object.class));
            } catch (ApiException exception) {
               errorListener.onErrorResponse(new VolleyError(exception));
            }
          }
      }, new Response.ErrorListener() {
          @Override
          public void onErrorResponse(VolleyError error) {
            errorListener.onErrorResponse(error);
          }
      });
    } catch (ApiException ex) {
      errorListener.onErrorResponse(new VolleyError(ex));
    }
  }
  /**
  * Retrieves the language to be used for emails.
  * 
   * @return Object
  */
  public Object privateGetEmailLanguageGet () throws TimeoutException, ExecutionException, InterruptedException, ApiException {
    Object postBody = null;

    // create path and map variables
    String path = "/private/get_email_language";

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();
    String[] contentTypes = {
    };
    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

    if (contentType.startsWith("multipart/form-data")) {
      // file uploading
      MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();
      HttpEntity httpEntity = localVarBuilder.build();
      postBody = httpEntity;
    } else {
      // normal form params
    }

    String[] authNames = new String[] { "bearerAuth" };

    try {
      String localVarResponse = apiInvoker.invokeAPI (basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType, authNames);
      if (localVarResponse != null) {
         return (Object) ApiInvoker.deserialize(localVarResponse, "", Object.class);
      } else {
         return null;
      }
    } catch (ApiException ex) {
       throw ex;
    } catch (InterruptedException ex) {
       throw ex;
    } catch (ExecutionException ex) {
      if (ex.getCause() instanceof VolleyError) {
        VolleyError volleyError = (VolleyError)ex.getCause();
        if (volleyError.networkResponse != null) {
          throw new ApiException(volleyError.networkResponse.statusCode, volleyError.getMessage());
        }
      }
      throw ex;
    } catch (TimeoutException ex) {
      throw ex;
    }
  }

      /**
   * Retrieves the language to be used for emails.
   * 

  */
  public void privateGetEmailLanguageGet (final Response.Listener<Object> responseListener, final Response.ErrorListener errorListener) {
    Object postBody = null;


    // create path and map variables
    String path = "/private/get_email_language".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();



    String[] contentTypes = {
      
    };
    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

    if (contentType.startsWith("multipart/form-data")) {
      // file uploading
      MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();
      

      HttpEntity httpEntity = localVarBuilder.build();
      postBody = httpEntity;
    } else {
      // normal form params
          }

    String[] authNames = new String[] { "bearerAuth" };

    try {
      apiInvoker.invokeAPI(basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType, authNames,
        new Response.Listener<String>() {
          @Override
          public void onResponse(String localVarResponse) {
            try {
              responseListener.onResponse((Object) ApiInvoker.deserialize(localVarResponse,  "", Object.class));
            } catch (ApiException exception) {
               errorListener.onErrorResponse(new VolleyError(exception));
            }
          }
      }, new Response.ErrorListener() {
          @Override
          public void onErrorResponse(VolleyError error) {
            errorListener.onErrorResponse(error);
          }
      });
    } catch (ApiException ex) {
      errorListener.onErrorResponse(new VolleyError(ex));
    }
  }
  /**
  * Get margins for given instrument, amount and price.
  * 
   * @param instrumentName Instrument name
   * @param amount Amount, integer for future, float for option. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH.
   * @param price Price
   * @return Object
  */
  public Object privateGetMarginsGet (String instrumentName, BigDecimal amount, BigDecimal price) throws TimeoutException, ExecutionException, InterruptedException, ApiException {
    Object postBody = null;
    // verify the required parameter 'instrumentName' is set
    if (instrumentName == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'instrumentName' when calling privateGetMarginsGet",
        new ApiException(400, "Missing the required parameter 'instrumentName' when calling privateGetMarginsGet"));
    }
    // verify the required parameter 'amount' is set
    if (amount == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'amount' when calling privateGetMarginsGet",
        new ApiException(400, "Missing the required parameter 'amount' when calling privateGetMarginsGet"));
    }
    // verify the required parameter 'price' is set
    if (price == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'price' when calling privateGetMarginsGet",
        new ApiException(400, "Missing the required parameter 'price' when calling privateGetMarginsGet"));
    }

    // create path and map variables
    String path = "/private/get_margins";

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();
    queryParams.addAll(ApiInvoker.parameterToPairs("", "instrument_name", instrumentName));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "amount", amount));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "price", price));
    String[] contentTypes = {
    };
    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

    if (contentType.startsWith("multipart/form-data")) {
      // file uploading
      MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();
      HttpEntity httpEntity = localVarBuilder.build();
      postBody = httpEntity;
    } else {
      // normal form params
    }

    String[] authNames = new String[] { "bearerAuth" };

    try {
      String localVarResponse = apiInvoker.invokeAPI (basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType, authNames);
      if (localVarResponse != null) {
         return (Object) ApiInvoker.deserialize(localVarResponse, "", Object.class);
      } else {
         return null;
      }
    } catch (ApiException ex) {
       throw ex;
    } catch (InterruptedException ex) {
       throw ex;
    } catch (ExecutionException ex) {
      if (ex.getCause() instanceof VolleyError) {
        VolleyError volleyError = (VolleyError)ex.getCause();
        if (volleyError.networkResponse != null) {
          throw new ApiException(volleyError.networkResponse.statusCode, volleyError.getMessage());
        }
      }
      throw ex;
    } catch (TimeoutException ex) {
      throw ex;
    }
  }

      /**
   * Get margins for given instrument, amount and price.
   * 
   * @param instrumentName Instrument name   * @param amount Amount, integer for future, float for option. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH.   * @param price Price
  */
  public void privateGetMarginsGet (String instrumentName, BigDecimal amount, BigDecimal price, final Response.Listener<Object> responseListener, final Response.ErrorListener errorListener) {
    Object postBody = null;

    // verify the required parameter 'instrumentName' is set
    if (instrumentName == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'instrumentName' when calling privateGetMarginsGet",
        new ApiException(400, "Missing the required parameter 'instrumentName' when calling privateGetMarginsGet"));
    }
    // verify the required parameter 'amount' is set
    if (amount == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'amount' when calling privateGetMarginsGet",
        new ApiException(400, "Missing the required parameter 'amount' when calling privateGetMarginsGet"));
    }
    // verify the required parameter 'price' is set
    if (price == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'price' when calling privateGetMarginsGet",
        new ApiException(400, "Missing the required parameter 'price' when calling privateGetMarginsGet"));
    }

    // create path and map variables
    String path = "/private/get_margins".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    queryParams.addAll(ApiInvoker.parameterToPairs("", "instrument_name", instrumentName));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "amount", amount));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "price", price));


    String[] contentTypes = {
      
    };
    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

    if (contentType.startsWith("multipart/form-data")) {
      // file uploading
      MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();
      

      HttpEntity httpEntity = localVarBuilder.build();
      postBody = httpEntity;
    } else {
      // normal form params
          }

    String[] authNames = new String[] { "bearerAuth" };

    try {
      apiInvoker.invokeAPI(basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType, authNames,
        new Response.Listener<String>() {
          @Override
          public void onResponse(String localVarResponse) {
            try {
              responseListener.onResponse((Object) ApiInvoker.deserialize(localVarResponse,  "", Object.class));
            } catch (ApiException exception) {
               errorListener.onErrorResponse(new VolleyError(exception));
            }
          }
      }, new Response.ErrorListener() {
          @Override
          public void onErrorResponse(VolleyError error) {
            errorListener.onErrorResponse(error);
          }
      });
    } catch (ApiException ex) {
      errorListener.onErrorResponse(new VolleyError(ex));
    }
  }
  /**
  * Retrieves announcements that have not been marked read by the user.
  * 
   * @return Object
  */
  public Object privateGetNewAnnouncementsGet () throws TimeoutException, ExecutionException, InterruptedException, ApiException {
    Object postBody = null;

    // create path and map variables
    String path = "/private/get_new_announcements";

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();
    String[] contentTypes = {
    };
    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

    if (contentType.startsWith("multipart/form-data")) {
      // file uploading
      MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();
      HttpEntity httpEntity = localVarBuilder.build();
      postBody = httpEntity;
    } else {
      // normal form params
    }

    String[] authNames = new String[] { "bearerAuth" };

    try {
      String localVarResponse = apiInvoker.invokeAPI (basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType, authNames);
      if (localVarResponse != null) {
         return (Object) ApiInvoker.deserialize(localVarResponse, "", Object.class);
      } else {
         return null;
      }
    } catch (ApiException ex) {
       throw ex;
    } catch (InterruptedException ex) {
       throw ex;
    } catch (ExecutionException ex) {
      if (ex.getCause() instanceof VolleyError) {
        VolleyError volleyError = (VolleyError)ex.getCause();
        if (volleyError.networkResponse != null) {
          throw new ApiException(volleyError.networkResponse.statusCode, volleyError.getMessage());
        }
      }
      throw ex;
    } catch (TimeoutException ex) {
      throw ex;
    }
  }

      /**
   * Retrieves announcements that have not been marked read by the user.
   * 

  */
  public void privateGetNewAnnouncementsGet (final Response.Listener<Object> responseListener, final Response.ErrorListener errorListener) {
    Object postBody = null;


    // create path and map variables
    String path = "/private/get_new_announcements".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();



    String[] contentTypes = {
      
    };
    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

    if (contentType.startsWith("multipart/form-data")) {
      // file uploading
      MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();
      

      HttpEntity httpEntity = localVarBuilder.build();
      postBody = httpEntity;
    } else {
      // normal form params
          }

    String[] authNames = new String[] { "bearerAuth" };

    try {
      apiInvoker.invokeAPI(basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType, authNames,
        new Response.Listener<String>() {
          @Override
          public void onResponse(String localVarResponse) {
            try {
              responseListener.onResponse((Object) ApiInvoker.deserialize(localVarResponse,  "", Object.class));
            } catch (ApiException exception) {
               errorListener.onErrorResponse(new VolleyError(exception));
            }
          }
      }, new Response.ErrorListener() {
          @Override
          public void onErrorResponse(VolleyError error) {
            errorListener.onErrorResponse(error);
          }
      });
    } catch (ApiException ex) {
      errorListener.onErrorResponse(new VolleyError(ex));
    }
  }
  /**
  * Retrieves list of user&#39;s open orders.
  * 
   * @param currency The currency symbol
   * @param kind Instrument kind, if not provided instruments of all kinds are considered
   * @param type Order type, default - &#x60;all&#x60;
   * @return Object
  */
  public Object privateGetOpenOrdersByCurrencyGet (String currency, String kind, String type) throws TimeoutException, ExecutionException, InterruptedException, ApiException {
    Object postBody = null;
    // verify the required parameter 'currency' is set
    if (currency == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'currency' when calling privateGetOpenOrdersByCurrencyGet",
        new ApiException(400, "Missing the required parameter 'currency' when calling privateGetOpenOrdersByCurrencyGet"));
    }

    // create path and map variables
    String path = "/private/get_open_orders_by_currency";

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();
    queryParams.addAll(ApiInvoker.parameterToPairs("", "currency", currency));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "kind", kind));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "type", type));
    String[] contentTypes = {
    };
    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

    if (contentType.startsWith("multipart/form-data")) {
      // file uploading
      MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();
      HttpEntity httpEntity = localVarBuilder.build();
      postBody = httpEntity;
    } else {
      // normal form params
    }

    String[] authNames = new String[] { "bearerAuth" };

    try {
      String localVarResponse = apiInvoker.invokeAPI (basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType, authNames);
      if (localVarResponse != null) {
         return (Object) ApiInvoker.deserialize(localVarResponse, "", Object.class);
      } else {
         return null;
      }
    } catch (ApiException ex) {
       throw ex;
    } catch (InterruptedException ex) {
       throw ex;
    } catch (ExecutionException ex) {
      if (ex.getCause() instanceof VolleyError) {
        VolleyError volleyError = (VolleyError)ex.getCause();
        if (volleyError.networkResponse != null) {
          throw new ApiException(volleyError.networkResponse.statusCode, volleyError.getMessage());
        }
      }
      throw ex;
    } catch (TimeoutException ex) {
      throw ex;
    }
  }

      /**
   * Retrieves list of user&#39;s open orders.
   * 
   * @param currency The currency symbol   * @param kind Instrument kind, if not provided instruments of all kinds are considered   * @param type Order type, default - &#x60;all&#x60;
  */
  public void privateGetOpenOrdersByCurrencyGet (String currency, String kind, String type, final Response.Listener<Object> responseListener, final Response.ErrorListener errorListener) {
    Object postBody = null;

    // verify the required parameter 'currency' is set
    if (currency == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'currency' when calling privateGetOpenOrdersByCurrencyGet",
        new ApiException(400, "Missing the required parameter 'currency' when calling privateGetOpenOrdersByCurrencyGet"));
    }

    // create path and map variables
    String path = "/private/get_open_orders_by_currency".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    queryParams.addAll(ApiInvoker.parameterToPairs("", "currency", currency));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "kind", kind));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "type", type));


    String[] contentTypes = {
      
    };
    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

    if (contentType.startsWith("multipart/form-data")) {
      // file uploading
      MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();
      

      HttpEntity httpEntity = localVarBuilder.build();
      postBody = httpEntity;
    } else {
      // normal form params
          }

    String[] authNames = new String[] { "bearerAuth" };

    try {
      apiInvoker.invokeAPI(basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType, authNames,
        new Response.Listener<String>() {
          @Override
          public void onResponse(String localVarResponse) {
            try {
              responseListener.onResponse((Object) ApiInvoker.deserialize(localVarResponse,  "", Object.class));
            } catch (ApiException exception) {
               errorListener.onErrorResponse(new VolleyError(exception));
            }
          }
      }, new Response.ErrorListener() {
          @Override
          public void onErrorResponse(VolleyError error) {
            errorListener.onErrorResponse(error);
          }
      });
    } catch (ApiException ex) {
      errorListener.onErrorResponse(new VolleyError(ex));
    }
  }
  /**
  * Retrieves list of user&#39;s open orders within given Instrument.
  * 
   * @param instrumentName Instrument name
   * @param type Order type, default - &#x60;all&#x60;
   * @return Object
  */
  public Object privateGetOpenOrdersByInstrumentGet (String instrumentName, String type) throws TimeoutException, ExecutionException, InterruptedException, ApiException {
    Object postBody = null;
    // verify the required parameter 'instrumentName' is set
    if (instrumentName == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'instrumentName' when calling privateGetOpenOrdersByInstrumentGet",
        new ApiException(400, "Missing the required parameter 'instrumentName' when calling privateGetOpenOrdersByInstrumentGet"));
    }

    // create path and map variables
    String path = "/private/get_open_orders_by_instrument";

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();
    queryParams.addAll(ApiInvoker.parameterToPairs("", "instrument_name", instrumentName));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "type", type));
    String[] contentTypes = {
    };
    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

    if (contentType.startsWith("multipart/form-data")) {
      // file uploading
      MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();
      HttpEntity httpEntity = localVarBuilder.build();
      postBody = httpEntity;
    } else {
      // normal form params
    }

    String[] authNames = new String[] { "bearerAuth" };

    try {
      String localVarResponse = apiInvoker.invokeAPI (basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType, authNames);
      if (localVarResponse != null) {
         return (Object) ApiInvoker.deserialize(localVarResponse, "", Object.class);
      } else {
         return null;
      }
    } catch (ApiException ex) {
       throw ex;
    } catch (InterruptedException ex) {
       throw ex;
    } catch (ExecutionException ex) {
      if (ex.getCause() instanceof VolleyError) {
        VolleyError volleyError = (VolleyError)ex.getCause();
        if (volleyError.networkResponse != null) {
          throw new ApiException(volleyError.networkResponse.statusCode, volleyError.getMessage());
        }
      }
      throw ex;
    } catch (TimeoutException ex) {
      throw ex;
    }
  }

      /**
   * Retrieves list of user&#39;s open orders within given Instrument.
   * 
   * @param instrumentName Instrument name   * @param type Order type, default - &#x60;all&#x60;
  */
  public void privateGetOpenOrdersByInstrumentGet (String instrumentName, String type, final Response.Listener<Object> responseListener, final Response.ErrorListener errorListener) {
    Object postBody = null;

    // verify the required parameter 'instrumentName' is set
    if (instrumentName == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'instrumentName' when calling privateGetOpenOrdersByInstrumentGet",
        new ApiException(400, "Missing the required parameter 'instrumentName' when calling privateGetOpenOrdersByInstrumentGet"));
    }

    // create path and map variables
    String path = "/private/get_open_orders_by_instrument".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    queryParams.addAll(ApiInvoker.parameterToPairs("", "instrument_name", instrumentName));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "type", type));


    String[] contentTypes = {
      
    };
    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

    if (contentType.startsWith("multipart/form-data")) {
      // file uploading
      MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();
      

      HttpEntity httpEntity = localVarBuilder.build();
      postBody = httpEntity;
    } else {
      // normal form params
          }

    String[] authNames = new String[] { "bearerAuth" };

    try {
      apiInvoker.invokeAPI(basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType, authNames,
        new Response.Listener<String>() {
          @Override
          public void onResponse(String localVarResponse) {
            try {
              responseListener.onResponse((Object) ApiInvoker.deserialize(localVarResponse,  "", Object.class));
            } catch (ApiException exception) {
               errorListener.onErrorResponse(new VolleyError(exception));
            }
          }
      }, new Response.ErrorListener() {
          @Override
          public void onErrorResponse(VolleyError error) {
            errorListener.onErrorResponse(error);
          }
      });
    } catch (ApiException ex) {
      errorListener.onErrorResponse(new VolleyError(ex));
    }
  }
  /**
  * Retrieves history of orders that have been partially or fully filled.
  * 
   * @param currency The currency symbol
   * @param kind Instrument kind, if not provided instruments of all kinds are considered
   * @param count Number of requested items, default - &#x60;20&#x60;
   * @param offset The offset for pagination, default - &#x60;0&#x60;
   * @param includeOld Include in result orders older than 2 days, default - &#x60;false&#x60;
   * @param includeUnfilled Include in result fully unfilled closed orders, default - &#x60;false&#x60;
   * @return Object
  */
  public Object privateGetOrderHistoryByCurrencyGet (String currency, String kind, Integer count, Integer offset, Boolean includeOld, Boolean includeUnfilled) throws TimeoutException, ExecutionException, InterruptedException, ApiException {
    Object postBody = null;
    // verify the required parameter 'currency' is set
    if (currency == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'currency' when calling privateGetOrderHistoryByCurrencyGet",
        new ApiException(400, "Missing the required parameter 'currency' when calling privateGetOrderHistoryByCurrencyGet"));
    }

    // create path and map variables
    String path = "/private/get_order_history_by_currency";

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();
    queryParams.addAll(ApiInvoker.parameterToPairs("", "currency", currency));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "kind", kind));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "count", count));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "offset", offset));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "include_old", includeOld));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "include_unfilled", includeUnfilled));
    String[] contentTypes = {
    };
    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

    if (contentType.startsWith("multipart/form-data")) {
      // file uploading
      MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();
      HttpEntity httpEntity = localVarBuilder.build();
      postBody = httpEntity;
    } else {
      // normal form params
    }

    String[] authNames = new String[] { "bearerAuth" };

    try {
      String localVarResponse = apiInvoker.invokeAPI (basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType, authNames);
      if (localVarResponse != null) {
         return (Object) ApiInvoker.deserialize(localVarResponse, "", Object.class);
      } else {
         return null;
      }
    } catch (ApiException ex) {
       throw ex;
    } catch (InterruptedException ex) {
       throw ex;
    } catch (ExecutionException ex) {
      if (ex.getCause() instanceof VolleyError) {
        VolleyError volleyError = (VolleyError)ex.getCause();
        if (volleyError.networkResponse != null) {
          throw new ApiException(volleyError.networkResponse.statusCode, volleyError.getMessage());
        }
      }
      throw ex;
    } catch (TimeoutException ex) {
      throw ex;
    }
  }

      /**
   * Retrieves history of orders that have been partially or fully filled.
   * 
   * @param currency The currency symbol   * @param kind Instrument kind, if not provided instruments of all kinds are considered   * @param count Number of requested items, default - &#x60;20&#x60;   * @param offset The offset for pagination, default - &#x60;0&#x60;   * @param includeOld Include in result orders older than 2 days, default - &#x60;false&#x60;   * @param includeUnfilled Include in result fully unfilled closed orders, default - &#x60;false&#x60;
  */
  public void privateGetOrderHistoryByCurrencyGet (String currency, String kind, Integer count, Integer offset, Boolean includeOld, Boolean includeUnfilled, final Response.Listener<Object> responseListener, final Response.ErrorListener errorListener) {
    Object postBody = null;

    // verify the required parameter 'currency' is set
    if (currency == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'currency' when calling privateGetOrderHistoryByCurrencyGet",
        new ApiException(400, "Missing the required parameter 'currency' when calling privateGetOrderHistoryByCurrencyGet"));
    }

    // create path and map variables
    String path = "/private/get_order_history_by_currency".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    queryParams.addAll(ApiInvoker.parameterToPairs("", "currency", currency));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "kind", kind));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "count", count));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "offset", offset));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "include_old", includeOld));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "include_unfilled", includeUnfilled));


    String[] contentTypes = {
      
    };
    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

    if (contentType.startsWith("multipart/form-data")) {
      // file uploading
      MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();
      

      HttpEntity httpEntity = localVarBuilder.build();
      postBody = httpEntity;
    } else {
      // normal form params
          }

    String[] authNames = new String[] { "bearerAuth" };

    try {
      apiInvoker.invokeAPI(basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType, authNames,
        new Response.Listener<String>() {
          @Override
          public void onResponse(String localVarResponse) {
            try {
              responseListener.onResponse((Object) ApiInvoker.deserialize(localVarResponse,  "", Object.class));
            } catch (ApiException exception) {
               errorListener.onErrorResponse(new VolleyError(exception));
            }
          }
      }, new Response.ErrorListener() {
          @Override
          public void onErrorResponse(VolleyError error) {
            errorListener.onErrorResponse(error);
          }
      });
    } catch (ApiException ex) {
      errorListener.onErrorResponse(new VolleyError(ex));
    }
  }
  /**
  * Retrieves history of orders that have been partially or fully filled.
  * 
   * @param instrumentName Instrument name
   * @param count Number of requested items, default - &#x60;20&#x60;
   * @param offset The offset for pagination, default - &#x60;0&#x60;
   * @param includeOld Include in result orders older than 2 days, default - &#x60;false&#x60;
   * @param includeUnfilled Include in result fully unfilled closed orders, default - &#x60;false&#x60;
   * @return Object
  */
  public Object privateGetOrderHistoryByInstrumentGet (String instrumentName, Integer count, Integer offset, Boolean includeOld, Boolean includeUnfilled) throws TimeoutException, ExecutionException, InterruptedException, ApiException {
    Object postBody = null;
    // verify the required parameter 'instrumentName' is set
    if (instrumentName == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'instrumentName' when calling privateGetOrderHistoryByInstrumentGet",
        new ApiException(400, "Missing the required parameter 'instrumentName' when calling privateGetOrderHistoryByInstrumentGet"));
    }

    // create path and map variables
    String path = "/private/get_order_history_by_instrument";

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();
    queryParams.addAll(ApiInvoker.parameterToPairs("", "instrument_name", instrumentName));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "count", count));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "offset", offset));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "include_old", includeOld));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "include_unfilled", includeUnfilled));
    String[] contentTypes = {
    };
    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

    if (contentType.startsWith("multipart/form-data")) {
      // file uploading
      MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();
      HttpEntity httpEntity = localVarBuilder.build();
      postBody = httpEntity;
    } else {
      // normal form params
    }

    String[] authNames = new String[] { "bearerAuth" };

    try {
      String localVarResponse = apiInvoker.invokeAPI (basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType, authNames);
      if (localVarResponse != null) {
         return (Object) ApiInvoker.deserialize(localVarResponse, "", Object.class);
      } else {
         return null;
      }
    } catch (ApiException ex) {
       throw ex;
    } catch (InterruptedException ex) {
       throw ex;
    } catch (ExecutionException ex) {
      if (ex.getCause() instanceof VolleyError) {
        VolleyError volleyError = (VolleyError)ex.getCause();
        if (volleyError.networkResponse != null) {
          throw new ApiException(volleyError.networkResponse.statusCode, volleyError.getMessage());
        }
      }
      throw ex;
    } catch (TimeoutException ex) {
      throw ex;
    }
  }

      /**
   * Retrieves history of orders that have been partially or fully filled.
   * 
   * @param instrumentName Instrument name   * @param count Number of requested items, default - &#x60;20&#x60;   * @param offset The offset for pagination, default - &#x60;0&#x60;   * @param includeOld Include in result orders older than 2 days, default - &#x60;false&#x60;   * @param includeUnfilled Include in result fully unfilled closed orders, default - &#x60;false&#x60;
  */
  public void privateGetOrderHistoryByInstrumentGet (String instrumentName, Integer count, Integer offset, Boolean includeOld, Boolean includeUnfilled, final Response.Listener<Object> responseListener, final Response.ErrorListener errorListener) {
    Object postBody = null;

    // verify the required parameter 'instrumentName' is set
    if (instrumentName == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'instrumentName' when calling privateGetOrderHistoryByInstrumentGet",
        new ApiException(400, "Missing the required parameter 'instrumentName' when calling privateGetOrderHistoryByInstrumentGet"));
    }

    // create path and map variables
    String path = "/private/get_order_history_by_instrument".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    queryParams.addAll(ApiInvoker.parameterToPairs("", "instrument_name", instrumentName));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "count", count));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "offset", offset));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "include_old", includeOld));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "include_unfilled", includeUnfilled));


    String[] contentTypes = {
      
    };
    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

    if (contentType.startsWith("multipart/form-data")) {
      // file uploading
      MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();
      

      HttpEntity httpEntity = localVarBuilder.build();
      postBody = httpEntity;
    } else {
      // normal form params
          }

    String[] authNames = new String[] { "bearerAuth" };

    try {
      apiInvoker.invokeAPI(basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType, authNames,
        new Response.Listener<String>() {
          @Override
          public void onResponse(String localVarResponse) {
            try {
              responseListener.onResponse((Object) ApiInvoker.deserialize(localVarResponse,  "", Object.class));
            } catch (ApiException exception) {
               errorListener.onErrorResponse(new VolleyError(exception));
            }
          }
      }, new Response.ErrorListener() {
          @Override
          public void onErrorResponse(VolleyError error) {
            errorListener.onErrorResponse(error);
          }
      });
    } catch (ApiException ex) {
      errorListener.onErrorResponse(new VolleyError(ex));
    }
  }
  /**
  * Retrieves initial margins of given orders
  * 
   * @param ids Ids of orders
   * @return Object
  */
  public Object privateGetOrderMarginByIdsGet (List<String> ids) throws TimeoutException, ExecutionException, InterruptedException, ApiException {
    Object postBody = null;
    // verify the required parameter 'ids' is set
    if (ids == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'ids' when calling privateGetOrderMarginByIdsGet",
        new ApiException(400, "Missing the required parameter 'ids' when calling privateGetOrderMarginByIdsGet"));
    }

    // create path and map variables
    String path = "/private/get_order_margin_by_ids";

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();
    queryParams.addAll(ApiInvoker.parameterToPairs("multi", "ids", ids));
    String[] contentTypes = {
    };
    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

    if (contentType.startsWith("multipart/form-data")) {
      // file uploading
      MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();
      HttpEntity httpEntity = localVarBuilder.build();
      postBody = httpEntity;
    } else {
      // normal form params
    }

    String[] authNames = new String[] { "bearerAuth" };

    try {
      String localVarResponse = apiInvoker.invokeAPI (basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType, authNames);
      if (localVarResponse != null) {
         return (Object) ApiInvoker.deserialize(localVarResponse, "", Object.class);
      } else {
         return null;
      }
    } catch (ApiException ex) {
       throw ex;
    } catch (InterruptedException ex) {
       throw ex;
    } catch (ExecutionException ex) {
      if (ex.getCause() instanceof VolleyError) {
        VolleyError volleyError = (VolleyError)ex.getCause();
        if (volleyError.networkResponse != null) {
          throw new ApiException(volleyError.networkResponse.statusCode, volleyError.getMessage());
        }
      }
      throw ex;
    } catch (TimeoutException ex) {
      throw ex;
    }
  }

      /**
   * Retrieves initial margins of given orders
   * 
   * @param ids Ids of orders
  */
  public void privateGetOrderMarginByIdsGet (List<String> ids, final Response.Listener<Object> responseListener, final Response.ErrorListener errorListener) {
    Object postBody = null;

    // verify the required parameter 'ids' is set
    if (ids == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'ids' when calling privateGetOrderMarginByIdsGet",
        new ApiException(400, "Missing the required parameter 'ids' when calling privateGetOrderMarginByIdsGet"));
    }

    // create path and map variables
    String path = "/private/get_order_margin_by_ids".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    queryParams.addAll(ApiInvoker.parameterToPairs("multi", "ids", ids));


    String[] contentTypes = {
      
    };
    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

    if (contentType.startsWith("multipart/form-data")) {
      // file uploading
      MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();
      

      HttpEntity httpEntity = localVarBuilder.build();
      postBody = httpEntity;
    } else {
      // normal form params
          }

    String[] authNames = new String[] { "bearerAuth" };

    try {
      apiInvoker.invokeAPI(basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType, authNames,
        new Response.Listener<String>() {
          @Override
          public void onResponse(String localVarResponse) {
            try {
              responseListener.onResponse((Object) ApiInvoker.deserialize(localVarResponse,  "", Object.class));
            } catch (ApiException exception) {
               errorListener.onErrorResponse(new VolleyError(exception));
            }
          }
      }, new Response.ErrorListener() {
          @Override
          public void onErrorResponse(VolleyError error) {
            errorListener.onErrorResponse(error);
          }
      });
    } catch (ApiException ex) {
      errorListener.onErrorResponse(new VolleyError(ex));
    }
  }
  /**
  * Retrieve the current state of an order.
  * 
   * @param orderId The order id
   * @return Object
  */
  public Object privateGetOrderStateGet (String orderId) throws TimeoutException, ExecutionException, InterruptedException, ApiException {
    Object postBody = null;
    // verify the required parameter 'orderId' is set
    if (orderId == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'orderId' when calling privateGetOrderStateGet",
        new ApiException(400, "Missing the required parameter 'orderId' when calling privateGetOrderStateGet"));
    }

    // create path and map variables
    String path = "/private/get_order_state";

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();
    queryParams.addAll(ApiInvoker.parameterToPairs("", "order_id", orderId));
    String[] contentTypes = {
    };
    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

    if (contentType.startsWith("multipart/form-data")) {
      // file uploading
      MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();
      HttpEntity httpEntity = localVarBuilder.build();
      postBody = httpEntity;
    } else {
      // normal form params
    }

    String[] authNames = new String[] { "bearerAuth" };

    try {
      String localVarResponse = apiInvoker.invokeAPI (basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType, authNames);
      if (localVarResponse != null) {
         return (Object) ApiInvoker.deserialize(localVarResponse, "", Object.class);
      } else {
         return null;
      }
    } catch (ApiException ex) {
       throw ex;
    } catch (InterruptedException ex) {
       throw ex;
    } catch (ExecutionException ex) {
      if (ex.getCause() instanceof VolleyError) {
        VolleyError volleyError = (VolleyError)ex.getCause();
        if (volleyError.networkResponse != null) {
          throw new ApiException(volleyError.networkResponse.statusCode, volleyError.getMessage());
        }
      }
      throw ex;
    } catch (TimeoutException ex) {
      throw ex;
    }
  }

      /**
   * Retrieve the current state of an order.
   * 
   * @param orderId The order id
  */
  public void privateGetOrderStateGet (String orderId, final Response.Listener<Object> responseListener, final Response.ErrorListener errorListener) {
    Object postBody = null;

    // verify the required parameter 'orderId' is set
    if (orderId == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'orderId' when calling privateGetOrderStateGet",
        new ApiException(400, "Missing the required parameter 'orderId' when calling privateGetOrderStateGet"));
    }

    // create path and map variables
    String path = "/private/get_order_state".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    queryParams.addAll(ApiInvoker.parameterToPairs("", "order_id", orderId));


    String[] contentTypes = {
      
    };
    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

    if (contentType.startsWith("multipart/form-data")) {
      // file uploading
      MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();
      

      HttpEntity httpEntity = localVarBuilder.build();
      postBody = httpEntity;
    } else {
      // normal form params
          }

    String[] authNames = new String[] { "bearerAuth" };

    try {
      apiInvoker.invokeAPI(basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType, authNames,
        new Response.Listener<String>() {
          @Override
          public void onResponse(String localVarResponse) {
            try {
              responseListener.onResponse((Object) ApiInvoker.deserialize(localVarResponse,  "", Object.class));
            } catch (ApiException exception) {
               errorListener.onErrorResponse(new VolleyError(exception));
            }
          }
      }, new Response.ErrorListener() {
          @Override
          public void onErrorResponse(VolleyError error) {
            errorListener.onErrorResponse(error);
          }
      });
    } catch (ApiException ex) {
      errorListener.onErrorResponse(new VolleyError(ex));
    }
  }
  /**
  * Retrieve user position.
  * 
   * @param instrumentName Instrument name
   * @return Object
  */
  public Object privateGetPositionGet (String instrumentName) throws TimeoutException, ExecutionException, InterruptedException, ApiException {
    Object postBody = null;
    // verify the required parameter 'instrumentName' is set
    if (instrumentName == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'instrumentName' when calling privateGetPositionGet",
        new ApiException(400, "Missing the required parameter 'instrumentName' when calling privateGetPositionGet"));
    }

    // create path and map variables
    String path = "/private/get_position";

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();
    queryParams.addAll(ApiInvoker.parameterToPairs("", "instrument_name", instrumentName));
    String[] contentTypes = {
    };
    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

    if (contentType.startsWith("multipart/form-data")) {
      // file uploading
      MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();
      HttpEntity httpEntity = localVarBuilder.build();
      postBody = httpEntity;
    } else {
      // normal form params
    }

    String[] authNames = new String[] { "bearerAuth" };

    try {
      String localVarResponse = apiInvoker.invokeAPI (basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType, authNames);
      if (localVarResponse != null) {
         return (Object) ApiInvoker.deserialize(localVarResponse, "", Object.class);
      } else {
         return null;
      }
    } catch (ApiException ex) {
       throw ex;
    } catch (InterruptedException ex) {
       throw ex;
    } catch (ExecutionException ex) {
      if (ex.getCause() instanceof VolleyError) {
        VolleyError volleyError = (VolleyError)ex.getCause();
        if (volleyError.networkResponse != null) {
          throw new ApiException(volleyError.networkResponse.statusCode, volleyError.getMessage());
        }
      }
      throw ex;
    } catch (TimeoutException ex) {
      throw ex;
    }
  }

      /**
   * Retrieve user position.
   * 
   * @param instrumentName Instrument name
  */
  public void privateGetPositionGet (String instrumentName, final Response.Listener<Object> responseListener, final Response.ErrorListener errorListener) {
    Object postBody = null;

    // verify the required parameter 'instrumentName' is set
    if (instrumentName == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'instrumentName' when calling privateGetPositionGet",
        new ApiException(400, "Missing the required parameter 'instrumentName' when calling privateGetPositionGet"));
    }

    // create path and map variables
    String path = "/private/get_position".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    queryParams.addAll(ApiInvoker.parameterToPairs("", "instrument_name", instrumentName));


    String[] contentTypes = {
      
    };
    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

    if (contentType.startsWith("multipart/form-data")) {
      // file uploading
      MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();
      

      HttpEntity httpEntity = localVarBuilder.build();
      postBody = httpEntity;
    } else {
      // normal form params
          }

    String[] authNames = new String[] { "bearerAuth" };

    try {
      apiInvoker.invokeAPI(basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType, authNames,
        new Response.Listener<String>() {
          @Override
          public void onResponse(String localVarResponse) {
            try {
              responseListener.onResponse((Object) ApiInvoker.deserialize(localVarResponse,  "", Object.class));
            } catch (ApiException exception) {
               errorListener.onErrorResponse(new VolleyError(exception));
            }
          }
      }, new Response.ErrorListener() {
          @Override
          public void onErrorResponse(VolleyError error) {
            errorListener.onErrorResponse(error);
          }
      });
    } catch (ApiException ex) {
      errorListener.onErrorResponse(new VolleyError(ex));
    }
  }
  /**
  * Retrieve user positions.
  * 
   * @param currency 
   * @param kind Kind filter on positions
   * @return Object
  */
  public Object privateGetPositionsGet (String currency, String kind) throws TimeoutException, ExecutionException, InterruptedException, ApiException {
    Object postBody = null;
    // verify the required parameter 'currency' is set
    if (currency == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'currency' when calling privateGetPositionsGet",
        new ApiException(400, "Missing the required parameter 'currency' when calling privateGetPositionsGet"));
    }

    // create path and map variables
    String path = "/private/get_positions";

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();
    queryParams.addAll(ApiInvoker.parameterToPairs("", "currency", currency));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "kind", kind));
    String[] contentTypes = {
    };
    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

    if (contentType.startsWith("multipart/form-data")) {
      // file uploading
      MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();
      HttpEntity httpEntity = localVarBuilder.build();
      postBody = httpEntity;
    } else {
      // normal form params
    }

    String[] authNames = new String[] { "bearerAuth" };

    try {
      String localVarResponse = apiInvoker.invokeAPI (basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType, authNames);
      if (localVarResponse != null) {
         return (Object) ApiInvoker.deserialize(localVarResponse, "", Object.class);
      } else {
         return null;
      }
    } catch (ApiException ex) {
       throw ex;
    } catch (InterruptedException ex) {
       throw ex;
    } catch (ExecutionException ex) {
      if (ex.getCause() instanceof VolleyError) {
        VolleyError volleyError = (VolleyError)ex.getCause();
        if (volleyError.networkResponse != null) {
          throw new ApiException(volleyError.networkResponse.statusCode, volleyError.getMessage());
        }
      }
      throw ex;
    } catch (TimeoutException ex) {
      throw ex;
    }
  }

      /**
   * Retrieve user positions.
   * 
   * @param currency    * @param kind Kind filter on positions
  */
  public void privateGetPositionsGet (String currency, String kind, final Response.Listener<Object> responseListener, final Response.ErrorListener errorListener) {
    Object postBody = null;

    // verify the required parameter 'currency' is set
    if (currency == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'currency' when calling privateGetPositionsGet",
        new ApiException(400, "Missing the required parameter 'currency' when calling privateGetPositionsGet"));
    }

    // create path and map variables
    String path = "/private/get_positions".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    queryParams.addAll(ApiInvoker.parameterToPairs("", "currency", currency));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "kind", kind));


    String[] contentTypes = {
      
    };
    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

    if (contentType.startsWith("multipart/form-data")) {
      // file uploading
      MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();
      

      HttpEntity httpEntity = localVarBuilder.build();
      postBody = httpEntity;
    } else {
      // normal form params
          }

    String[] authNames = new String[] { "bearerAuth" };

    try {
      apiInvoker.invokeAPI(basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType, authNames,
        new Response.Listener<String>() {
          @Override
          public void onResponse(String localVarResponse) {
            try {
              responseListener.onResponse((Object) ApiInvoker.deserialize(localVarResponse,  "", Object.class));
            } catch (ApiException exception) {
               errorListener.onErrorResponse(new VolleyError(exception));
            }
          }
      }, new Response.ErrorListener() {
          @Override
          public void onErrorResponse(VolleyError error) {
            errorListener.onErrorResponse(error);
          }
      });
    } catch (ApiException ex) {
      errorListener.onErrorResponse(new VolleyError(ex));
    }
  }
  /**
  * Retrieves settlement, delivery and bankruptcy events that have affected your account.
  * 
   * @param currency The currency symbol
   * @param type Settlement type
   * @param count Number of requested items, default - &#x60;20&#x60;
   * @return Object
  */
  public Object privateGetSettlementHistoryByCurrencyGet (String currency, String type, Integer count) throws TimeoutException, ExecutionException, InterruptedException, ApiException {
    Object postBody = null;
    // verify the required parameter 'currency' is set
    if (currency == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'currency' when calling privateGetSettlementHistoryByCurrencyGet",
        new ApiException(400, "Missing the required parameter 'currency' when calling privateGetSettlementHistoryByCurrencyGet"));
    }

    // create path and map variables
    String path = "/private/get_settlement_history_by_currency";

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();
    queryParams.addAll(ApiInvoker.parameterToPairs("", "currency", currency));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "type", type));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "count", count));
    String[] contentTypes = {
    };
    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

    if (contentType.startsWith("multipart/form-data")) {
      // file uploading
      MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();
      HttpEntity httpEntity = localVarBuilder.build();
      postBody = httpEntity;
    } else {
      // normal form params
    }

    String[] authNames = new String[] { "bearerAuth" };

    try {
      String localVarResponse = apiInvoker.invokeAPI (basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType, authNames);
      if (localVarResponse != null) {
         return (Object) ApiInvoker.deserialize(localVarResponse, "", Object.class);
      } else {
         return null;
      }
    } catch (ApiException ex) {
       throw ex;
    } catch (InterruptedException ex) {
       throw ex;
    } catch (ExecutionException ex) {
      if (ex.getCause() instanceof VolleyError) {
        VolleyError volleyError = (VolleyError)ex.getCause();
        if (volleyError.networkResponse != null) {
          throw new ApiException(volleyError.networkResponse.statusCode, volleyError.getMessage());
        }
      }
      throw ex;
    } catch (TimeoutException ex) {
      throw ex;
    }
  }

      /**
   * Retrieves settlement, delivery and bankruptcy events that have affected your account.
   * 
   * @param currency The currency symbol   * @param type Settlement type   * @param count Number of requested items, default - &#x60;20&#x60;
  */
  public void privateGetSettlementHistoryByCurrencyGet (String currency, String type, Integer count, final Response.Listener<Object> responseListener, final Response.ErrorListener errorListener) {
    Object postBody = null;

    // verify the required parameter 'currency' is set
    if (currency == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'currency' when calling privateGetSettlementHistoryByCurrencyGet",
        new ApiException(400, "Missing the required parameter 'currency' when calling privateGetSettlementHistoryByCurrencyGet"));
    }

    // create path and map variables
    String path = "/private/get_settlement_history_by_currency".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    queryParams.addAll(ApiInvoker.parameterToPairs("", "currency", currency));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "type", type));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "count", count));


    String[] contentTypes = {
      
    };
    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

    if (contentType.startsWith("multipart/form-data")) {
      // file uploading
      MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();
      

      HttpEntity httpEntity = localVarBuilder.build();
      postBody = httpEntity;
    } else {
      // normal form params
          }

    String[] authNames = new String[] { "bearerAuth" };

    try {
      apiInvoker.invokeAPI(basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType, authNames,
        new Response.Listener<String>() {
          @Override
          public void onResponse(String localVarResponse) {
            try {
              responseListener.onResponse((Object) ApiInvoker.deserialize(localVarResponse,  "", Object.class));
            } catch (ApiException exception) {
               errorListener.onErrorResponse(new VolleyError(exception));
            }
          }
      }, new Response.ErrorListener() {
          @Override
          public void onErrorResponse(VolleyError error) {
            errorListener.onErrorResponse(error);
          }
      });
    } catch (ApiException ex) {
      errorListener.onErrorResponse(new VolleyError(ex));
    }
  }
  /**
  * Retrieves public settlement, delivery and bankruptcy events filtered by instrument name
  * 
   * @param instrumentName Instrument name
   * @param type Settlement type
   * @param count Number of requested items, default - &#x60;20&#x60;
   * @return Object
  */
  public Object privateGetSettlementHistoryByInstrumentGet (String instrumentName, String type, Integer count) throws TimeoutException, ExecutionException, InterruptedException, ApiException {
    Object postBody = null;
    // verify the required parameter 'instrumentName' is set
    if (instrumentName == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'instrumentName' when calling privateGetSettlementHistoryByInstrumentGet",
        new ApiException(400, "Missing the required parameter 'instrumentName' when calling privateGetSettlementHistoryByInstrumentGet"));
    }

    // create path and map variables
    String path = "/private/get_settlement_history_by_instrument";

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();
    queryParams.addAll(ApiInvoker.parameterToPairs("", "instrument_name", instrumentName));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "type", type));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "count", count));
    String[] contentTypes = {
    };
    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

    if (contentType.startsWith("multipart/form-data")) {
      // file uploading
      MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();
      HttpEntity httpEntity = localVarBuilder.build();
      postBody = httpEntity;
    } else {
      // normal form params
    }

    String[] authNames = new String[] { "bearerAuth" };

    try {
      String localVarResponse = apiInvoker.invokeAPI (basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType, authNames);
      if (localVarResponse != null) {
         return (Object) ApiInvoker.deserialize(localVarResponse, "", Object.class);
      } else {
         return null;
      }
    } catch (ApiException ex) {
       throw ex;
    } catch (InterruptedException ex) {
       throw ex;
    } catch (ExecutionException ex) {
      if (ex.getCause() instanceof VolleyError) {
        VolleyError volleyError = (VolleyError)ex.getCause();
        if (volleyError.networkResponse != null) {
          throw new ApiException(volleyError.networkResponse.statusCode, volleyError.getMessage());
        }
      }
      throw ex;
    } catch (TimeoutException ex) {
      throw ex;
    }
  }

      /**
   * Retrieves public settlement, delivery and bankruptcy events filtered by instrument name
   * 
   * @param instrumentName Instrument name   * @param type Settlement type   * @param count Number of requested items, default - &#x60;20&#x60;
  */
  public void privateGetSettlementHistoryByInstrumentGet (String instrumentName, String type, Integer count, final Response.Listener<Object> responseListener, final Response.ErrorListener errorListener) {
    Object postBody = null;

    // verify the required parameter 'instrumentName' is set
    if (instrumentName == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'instrumentName' when calling privateGetSettlementHistoryByInstrumentGet",
        new ApiException(400, "Missing the required parameter 'instrumentName' when calling privateGetSettlementHistoryByInstrumentGet"));
    }

    // create path and map variables
    String path = "/private/get_settlement_history_by_instrument".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    queryParams.addAll(ApiInvoker.parameterToPairs("", "instrument_name", instrumentName));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "type", type));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "count", count));


    String[] contentTypes = {
      
    };
    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

    if (contentType.startsWith("multipart/form-data")) {
      // file uploading
      MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();
      

      HttpEntity httpEntity = localVarBuilder.build();
      postBody = httpEntity;
    } else {
      // normal form params
          }

    String[] authNames = new String[] { "bearerAuth" };

    try {
      apiInvoker.invokeAPI(basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType, authNames,
        new Response.Listener<String>() {
          @Override
          public void onResponse(String localVarResponse) {
            try {
              responseListener.onResponse((Object) ApiInvoker.deserialize(localVarResponse,  "", Object.class));
            } catch (ApiException exception) {
               errorListener.onErrorResponse(new VolleyError(exception));
            }
          }
      }, new Response.ErrorListener() {
          @Override
          public void onErrorResponse(VolleyError error) {
            errorListener.onErrorResponse(error);
          }
      });
    } catch (ApiException ex) {
      errorListener.onErrorResponse(new VolleyError(ex));
    }
  }
  /**
  * Get information about subaccounts
  * 
   * @param withPortfolio 
   * @return Object
  */
  public Object privateGetSubaccountsGet (Boolean withPortfolio) throws TimeoutException, ExecutionException, InterruptedException, ApiException {
    Object postBody = null;

    // create path and map variables
    String path = "/private/get_subaccounts";

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();
    queryParams.addAll(ApiInvoker.parameterToPairs("", "with_portfolio", withPortfolio));
    String[] contentTypes = {
    };
    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

    if (contentType.startsWith("multipart/form-data")) {
      // file uploading
      MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();
      HttpEntity httpEntity = localVarBuilder.build();
      postBody = httpEntity;
    } else {
      // normal form params
    }

    String[] authNames = new String[] { "bearerAuth" };

    try {
      String localVarResponse = apiInvoker.invokeAPI (basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType, authNames);
      if (localVarResponse != null) {
         return (Object) ApiInvoker.deserialize(localVarResponse, "", Object.class);
      } else {
         return null;
      }
    } catch (ApiException ex) {
       throw ex;
    } catch (InterruptedException ex) {
       throw ex;
    } catch (ExecutionException ex) {
      if (ex.getCause() instanceof VolleyError) {
        VolleyError volleyError = (VolleyError)ex.getCause();
        if (volleyError.networkResponse != null) {
          throw new ApiException(volleyError.networkResponse.statusCode, volleyError.getMessage());
        }
      }
      throw ex;
    } catch (TimeoutException ex) {
      throw ex;
    }
  }

      /**
   * Get information about subaccounts
   * 
   * @param withPortfolio 
  */
  public void privateGetSubaccountsGet (Boolean withPortfolio, final Response.Listener<Object> responseListener, final Response.ErrorListener errorListener) {
    Object postBody = null;


    // create path and map variables
    String path = "/private/get_subaccounts".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    queryParams.addAll(ApiInvoker.parameterToPairs("", "with_portfolio", withPortfolio));


    String[] contentTypes = {
      
    };
    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

    if (contentType.startsWith("multipart/form-data")) {
      // file uploading
      MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();
      

      HttpEntity httpEntity = localVarBuilder.build();
      postBody = httpEntity;
    } else {
      // normal form params
          }

    String[] authNames = new String[] { "bearerAuth" };

    try {
      apiInvoker.invokeAPI(basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType, authNames,
        new Response.Listener<String>() {
          @Override
          public void onResponse(String localVarResponse) {
            try {
              responseListener.onResponse((Object) ApiInvoker.deserialize(localVarResponse,  "", Object.class));
            } catch (ApiException exception) {
               errorListener.onErrorResponse(new VolleyError(exception));
            }
          }
      }, new Response.ErrorListener() {
          @Override
          public void onErrorResponse(VolleyError error) {
            errorListener.onErrorResponse(error);
          }
      });
    } catch (ApiException ex) {
      errorListener.onErrorResponse(new VolleyError(ex));
    }
  }
  /**
  * Adds new entry to address book of given type
  * 
   * @param currency The currency symbol
   * @param count Number of requested items, default - &#x60;10&#x60;
   * @param offset The offset for pagination, default - &#x60;0&#x60;
   * @return Object
  */
  public Object privateGetTransfersGet (String currency, Integer count, Integer offset) throws TimeoutException, ExecutionException, InterruptedException, ApiException {
    Object postBody = null;
    // verify the required parameter 'currency' is set
    if (currency == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'currency' when calling privateGetTransfersGet",
        new ApiException(400, "Missing the required parameter 'currency' when calling privateGetTransfersGet"));
    }

    // create path and map variables
    String path = "/private/get_transfers";

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();
    queryParams.addAll(ApiInvoker.parameterToPairs("", "currency", currency));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "count", count));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "offset", offset));
    String[] contentTypes = {
    };
    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

    if (contentType.startsWith("multipart/form-data")) {
      // file uploading
      MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();
      HttpEntity httpEntity = localVarBuilder.build();
      postBody = httpEntity;
    } else {
      // normal form params
    }

    String[] authNames = new String[] { "bearerAuth" };

    try {
      String localVarResponse = apiInvoker.invokeAPI (basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType, authNames);
      if (localVarResponse != null) {
         return (Object) ApiInvoker.deserialize(localVarResponse, "", Object.class);
      } else {
         return null;
      }
    } catch (ApiException ex) {
       throw ex;
    } catch (InterruptedException ex) {
       throw ex;
    } catch (ExecutionException ex) {
      if (ex.getCause() instanceof VolleyError) {
        VolleyError volleyError = (VolleyError)ex.getCause();
        if (volleyError.networkResponse != null) {
          throw new ApiException(volleyError.networkResponse.statusCode, volleyError.getMessage());
        }
      }
      throw ex;
    } catch (TimeoutException ex) {
      throw ex;
    }
  }

      /**
   * Adds new entry to address book of given type
   * 
   * @param currency The currency symbol   * @param count Number of requested items, default - &#x60;10&#x60;   * @param offset The offset for pagination, default - &#x60;0&#x60;
  */
  public void privateGetTransfersGet (String currency, Integer count, Integer offset, final Response.Listener<Object> responseListener, final Response.ErrorListener errorListener) {
    Object postBody = null;

    // verify the required parameter 'currency' is set
    if (currency == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'currency' when calling privateGetTransfersGet",
        new ApiException(400, "Missing the required parameter 'currency' when calling privateGetTransfersGet"));
    }

    // create path and map variables
    String path = "/private/get_transfers".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    queryParams.addAll(ApiInvoker.parameterToPairs("", "currency", currency));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "count", count));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "offset", offset));


    String[] contentTypes = {
      
    };
    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

    if (contentType.startsWith("multipart/form-data")) {
      // file uploading
      MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();
      

      HttpEntity httpEntity = localVarBuilder.build();
      postBody = httpEntity;
    } else {
      // normal form params
          }

    String[] authNames = new String[] { "bearerAuth" };

    try {
      apiInvoker.invokeAPI(basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType, authNames,
        new Response.Listener<String>() {
          @Override
          public void onResponse(String localVarResponse) {
            try {
              responseListener.onResponse((Object) ApiInvoker.deserialize(localVarResponse,  "", Object.class));
            } catch (ApiException exception) {
               errorListener.onErrorResponse(new VolleyError(exception));
            }
          }
      }, new Response.ErrorListener() {
          @Override
          public void onErrorResponse(VolleyError error) {
            errorListener.onErrorResponse(error);
          }
      });
    } catch (ApiException ex) {
      errorListener.onErrorResponse(new VolleyError(ex));
    }
  }
  /**
  * Retrieve the latest user trades that have occurred for instruments in a specific currency symbol and within given time range.
  * 
   * @param currency The currency symbol
   * @param startTimestamp The earliest timestamp to return result for
   * @param endTimestamp The most recent timestamp to return result for
   * @param kind Instrument kind, if not provided instruments of all kinds are considered
   * @param count Number of requested items, default - &#x60;10&#x60;
   * @param includeOld Include trades older than 7 days, default - &#x60;false&#x60;
   * @param sorting Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database)
   * @return Object
  */
  public Object privateGetUserTradesByCurrencyAndTimeGet (String currency, Integer startTimestamp, Integer endTimestamp, String kind, Integer count, Boolean includeOld, String sorting) throws TimeoutException, ExecutionException, InterruptedException, ApiException {
    Object postBody = null;
    // verify the required parameter 'currency' is set
    if (currency == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'currency' when calling privateGetUserTradesByCurrencyAndTimeGet",
        new ApiException(400, "Missing the required parameter 'currency' when calling privateGetUserTradesByCurrencyAndTimeGet"));
    }
    // verify the required parameter 'startTimestamp' is set
    if (startTimestamp == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'startTimestamp' when calling privateGetUserTradesByCurrencyAndTimeGet",
        new ApiException(400, "Missing the required parameter 'startTimestamp' when calling privateGetUserTradesByCurrencyAndTimeGet"));
    }
    // verify the required parameter 'endTimestamp' is set
    if (endTimestamp == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'endTimestamp' when calling privateGetUserTradesByCurrencyAndTimeGet",
        new ApiException(400, "Missing the required parameter 'endTimestamp' when calling privateGetUserTradesByCurrencyAndTimeGet"));
    }

    // create path and map variables
    String path = "/private/get_user_trades_by_currency_and_time";

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();
    queryParams.addAll(ApiInvoker.parameterToPairs("", "currency", currency));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "kind", kind));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "start_timestamp", startTimestamp));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "end_timestamp", endTimestamp));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "count", count));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "include_old", includeOld));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "sorting", sorting));
    String[] contentTypes = {
    };
    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

    if (contentType.startsWith("multipart/form-data")) {
      // file uploading
      MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();
      HttpEntity httpEntity = localVarBuilder.build();
      postBody = httpEntity;
    } else {
      // normal form params
    }

    String[] authNames = new String[] { "bearerAuth" };

    try {
      String localVarResponse = apiInvoker.invokeAPI (basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType, authNames);
      if (localVarResponse != null) {
         return (Object) ApiInvoker.deserialize(localVarResponse, "", Object.class);
      } else {
         return null;
      }
    } catch (ApiException ex) {
       throw ex;
    } catch (InterruptedException ex) {
       throw ex;
    } catch (ExecutionException ex) {
      if (ex.getCause() instanceof VolleyError) {
        VolleyError volleyError = (VolleyError)ex.getCause();
        if (volleyError.networkResponse != null) {
          throw new ApiException(volleyError.networkResponse.statusCode, volleyError.getMessage());
        }
      }
      throw ex;
    } catch (TimeoutException ex) {
      throw ex;
    }
  }

      /**
   * Retrieve the latest user trades that have occurred for instruments in a specific currency symbol and within given time range.
   * 
   * @param currency The currency symbol   * @param startTimestamp The earliest timestamp to return result for   * @param endTimestamp The most recent timestamp to return result for   * @param kind Instrument kind, if not provided instruments of all kinds are considered   * @param count Number of requested items, default - &#x60;10&#x60;   * @param includeOld Include trades older than 7 days, default - &#x60;false&#x60;   * @param sorting Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database)
  */
  public void privateGetUserTradesByCurrencyAndTimeGet (String currency, Integer startTimestamp, Integer endTimestamp, String kind, Integer count, Boolean includeOld, String sorting, final Response.Listener<Object> responseListener, final Response.ErrorListener errorListener) {
    Object postBody = null;

    // verify the required parameter 'currency' is set
    if (currency == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'currency' when calling privateGetUserTradesByCurrencyAndTimeGet",
        new ApiException(400, "Missing the required parameter 'currency' when calling privateGetUserTradesByCurrencyAndTimeGet"));
    }
    // verify the required parameter 'startTimestamp' is set
    if (startTimestamp == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'startTimestamp' when calling privateGetUserTradesByCurrencyAndTimeGet",
        new ApiException(400, "Missing the required parameter 'startTimestamp' when calling privateGetUserTradesByCurrencyAndTimeGet"));
    }
    // verify the required parameter 'endTimestamp' is set
    if (endTimestamp == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'endTimestamp' when calling privateGetUserTradesByCurrencyAndTimeGet",
        new ApiException(400, "Missing the required parameter 'endTimestamp' when calling privateGetUserTradesByCurrencyAndTimeGet"));
    }

    // create path and map variables
    String path = "/private/get_user_trades_by_currency_and_time".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    queryParams.addAll(ApiInvoker.parameterToPairs("", "currency", currency));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "kind", kind));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "start_timestamp", startTimestamp));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "end_timestamp", endTimestamp));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "count", count));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "include_old", includeOld));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "sorting", sorting));


    String[] contentTypes = {
      
    };
    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

    if (contentType.startsWith("multipart/form-data")) {
      // file uploading
      MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();
      

      HttpEntity httpEntity = localVarBuilder.build();
      postBody = httpEntity;
    } else {
      // normal form params
          }

    String[] authNames = new String[] { "bearerAuth" };

    try {
      apiInvoker.invokeAPI(basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType, authNames,
        new Response.Listener<String>() {
          @Override
          public void onResponse(String localVarResponse) {
            try {
              responseListener.onResponse((Object) ApiInvoker.deserialize(localVarResponse,  "", Object.class));
            } catch (ApiException exception) {
               errorListener.onErrorResponse(new VolleyError(exception));
            }
          }
      }, new Response.ErrorListener() {
          @Override
          public void onErrorResponse(VolleyError error) {
            errorListener.onErrorResponse(error);
          }
      });
    } catch (ApiException ex) {
      errorListener.onErrorResponse(new VolleyError(ex));
    }
  }
  /**
  * Retrieve the latest user trades that have occurred for instruments in a specific currency symbol.
  * 
   * @param currency The currency symbol
   * @param kind Instrument kind, if not provided instruments of all kinds are considered
   * @param startId The ID number of the first trade to be returned
   * @param endId The ID number of the last trade to be returned
   * @param count Number of requested items, default - &#x60;10&#x60;
   * @param includeOld Include trades older than 7 days, default - &#x60;false&#x60;
   * @param sorting Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database)
   * @return Object
  */
  public Object privateGetUserTradesByCurrencyGet (String currency, String kind, String startId, String endId, Integer count, Boolean includeOld, String sorting) throws TimeoutException, ExecutionException, InterruptedException, ApiException {
    Object postBody = null;
    // verify the required parameter 'currency' is set
    if (currency == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'currency' when calling privateGetUserTradesByCurrencyGet",
        new ApiException(400, "Missing the required parameter 'currency' when calling privateGetUserTradesByCurrencyGet"));
    }

    // create path and map variables
    String path = "/private/get_user_trades_by_currency";

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();
    queryParams.addAll(ApiInvoker.parameterToPairs("", "currency", currency));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "kind", kind));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "start_id", startId));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "end_id", endId));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "count", count));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "include_old", includeOld));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "sorting", sorting));
    String[] contentTypes = {
    };
    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

    if (contentType.startsWith("multipart/form-data")) {
      // file uploading
      MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();
      HttpEntity httpEntity = localVarBuilder.build();
      postBody = httpEntity;
    } else {
      // normal form params
    }

    String[] authNames = new String[] { "bearerAuth" };

    try {
      String localVarResponse = apiInvoker.invokeAPI (basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType, authNames);
      if (localVarResponse != null) {
         return (Object) ApiInvoker.deserialize(localVarResponse, "", Object.class);
      } else {
         return null;
      }
    } catch (ApiException ex) {
       throw ex;
    } catch (InterruptedException ex) {
       throw ex;
    } catch (ExecutionException ex) {
      if (ex.getCause() instanceof VolleyError) {
        VolleyError volleyError = (VolleyError)ex.getCause();
        if (volleyError.networkResponse != null) {
          throw new ApiException(volleyError.networkResponse.statusCode, volleyError.getMessage());
        }
      }
      throw ex;
    } catch (TimeoutException ex) {
      throw ex;
    }
  }

      /**
   * Retrieve the latest user trades that have occurred for instruments in a specific currency symbol.
   * 
   * @param currency The currency symbol   * @param kind Instrument kind, if not provided instruments of all kinds are considered   * @param startId The ID number of the first trade to be returned   * @param endId The ID number of the last trade to be returned   * @param count Number of requested items, default - &#x60;10&#x60;   * @param includeOld Include trades older than 7 days, default - &#x60;false&#x60;   * @param sorting Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database)
  */
  public void privateGetUserTradesByCurrencyGet (String currency, String kind, String startId, String endId, Integer count, Boolean includeOld, String sorting, final Response.Listener<Object> responseListener, final Response.ErrorListener errorListener) {
    Object postBody = null;

    // verify the required parameter 'currency' is set
    if (currency == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'currency' when calling privateGetUserTradesByCurrencyGet",
        new ApiException(400, "Missing the required parameter 'currency' when calling privateGetUserTradesByCurrencyGet"));
    }

    // create path and map variables
    String path = "/private/get_user_trades_by_currency".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    queryParams.addAll(ApiInvoker.parameterToPairs("", "currency", currency));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "kind", kind));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "start_id", startId));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "end_id", endId));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "count", count));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "include_old", includeOld));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "sorting", sorting));


    String[] contentTypes = {
      
    };
    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

    if (contentType.startsWith("multipart/form-data")) {
      // file uploading
      MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();
      

      HttpEntity httpEntity = localVarBuilder.build();
      postBody = httpEntity;
    } else {
      // normal form params
          }

    String[] authNames = new String[] { "bearerAuth" };

    try {
      apiInvoker.invokeAPI(basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType, authNames,
        new Response.Listener<String>() {
          @Override
          public void onResponse(String localVarResponse) {
            try {
              responseListener.onResponse((Object) ApiInvoker.deserialize(localVarResponse,  "", Object.class));
            } catch (ApiException exception) {
               errorListener.onErrorResponse(new VolleyError(exception));
            }
          }
      }, new Response.ErrorListener() {
          @Override
          public void onErrorResponse(VolleyError error) {
            errorListener.onErrorResponse(error);
          }
      });
    } catch (ApiException ex) {
      errorListener.onErrorResponse(new VolleyError(ex));
    }
  }
  /**
  * Retrieve the latest user trades that have occurred for a specific instrument and within given time range.
  * 
   * @param instrumentName Instrument name
   * @param startTimestamp The earliest timestamp to return result for
   * @param endTimestamp The most recent timestamp to return result for
   * @param count Number of requested items, default - &#x60;10&#x60;
   * @param includeOld Include trades older than 7 days, default - &#x60;false&#x60;
   * @param sorting Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database)
   * @return Object
  */
  public Object privateGetUserTradesByInstrumentAndTimeGet (String instrumentName, Integer startTimestamp, Integer endTimestamp, Integer count, Boolean includeOld, String sorting) throws TimeoutException, ExecutionException, InterruptedException, ApiException {
    Object postBody = null;
    // verify the required parameter 'instrumentName' is set
    if (instrumentName == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'instrumentName' when calling privateGetUserTradesByInstrumentAndTimeGet",
        new ApiException(400, "Missing the required parameter 'instrumentName' when calling privateGetUserTradesByInstrumentAndTimeGet"));
    }
    // verify the required parameter 'startTimestamp' is set
    if (startTimestamp == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'startTimestamp' when calling privateGetUserTradesByInstrumentAndTimeGet",
        new ApiException(400, "Missing the required parameter 'startTimestamp' when calling privateGetUserTradesByInstrumentAndTimeGet"));
    }
    // verify the required parameter 'endTimestamp' is set
    if (endTimestamp == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'endTimestamp' when calling privateGetUserTradesByInstrumentAndTimeGet",
        new ApiException(400, "Missing the required parameter 'endTimestamp' when calling privateGetUserTradesByInstrumentAndTimeGet"));
    }

    // create path and map variables
    String path = "/private/get_user_trades_by_instrument_and_time";

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();
    queryParams.addAll(ApiInvoker.parameterToPairs("", "instrument_name", instrumentName));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "start_timestamp", startTimestamp));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "end_timestamp", endTimestamp));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "count", count));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "include_old", includeOld));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "sorting", sorting));
    String[] contentTypes = {
    };
    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

    if (contentType.startsWith("multipart/form-data")) {
      // file uploading
      MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();
      HttpEntity httpEntity = localVarBuilder.build();
      postBody = httpEntity;
    } else {
      // normal form params
    }

    String[] authNames = new String[] { "bearerAuth" };

    try {
      String localVarResponse = apiInvoker.invokeAPI (basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType, authNames);
      if (localVarResponse != null) {
         return (Object) ApiInvoker.deserialize(localVarResponse, "", Object.class);
      } else {
         return null;
      }
    } catch (ApiException ex) {
       throw ex;
    } catch (InterruptedException ex) {
       throw ex;
    } catch (ExecutionException ex) {
      if (ex.getCause() instanceof VolleyError) {
        VolleyError volleyError = (VolleyError)ex.getCause();
        if (volleyError.networkResponse != null) {
          throw new ApiException(volleyError.networkResponse.statusCode, volleyError.getMessage());
        }
      }
      throw ex;
    } catch (TimeoutException ex) {
      throw ex;
    }
  }

      /**
   * Retrieve the latest user trades that have occurred for a specific instrument and within given time range.
   * 
   * @param instrumentName Instrument name   * @param startTimestamp The earliest timestamp to return result for   * @param endTimestamp The most recent timestamp to return result for   * @param count Number of requested items, default - &#x60;10&#x60;   * @param includeOld Include trades older than 7 days, default - &#x60;false&#x60;   * @param sorting Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database)
  */
  public void privateGetUserTradesByInstrumentAndTimeGet (String instrumentName, Integer startTimestamp, Integer endTimestamp, Integer count, Boolean includeOld, String sorting, final Response.Listener<Object> responseListener, final Response.ErrorListener errorListener) {
    Object postBody = null;

    // verify the required parameter 'instrumentName' is set
    if (instrumentName == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'instrumentName' when calling privateGetUserTradesByInstrumentAndTimeGet",
        new ApiException(400, "Missing the required parameter 'instrumentName' when calling privateGetUserTradesByInstrumentAndTimeGet"));
    }
    // verify the required parameter 'startTimestamp' is set
    if (startTimestamp == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'startTimestamp' when calling privateGetUserTradesByInstrumentAndTimeGet",
        new ApiException(400, "Missing the required parameter 'startTimestamp' when calling privateGetUserTradesByInstrumentAndTimeGet"));
    }
    // verify the required parameter 'endTimestamp' is set
    if (endTimestamp == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'endTimestamp' when calling privateGetUserTradesByInstrumentAndTimeGet",
        new ApiException(400, "Missing the required parameter 'endTimestamp' when calling privateGetUserTradesByInstrumentAndTimeGet"));
    }

    // create path and map variables
    String path = "/private/get_user_trades_by_instrument_and_time".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    queryParams.addAll(ApiInvoker.parameterToPairs("", "instrument_name", instrumentName));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "start_timestamp", startTimestamp));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "end_timestamp", endTimestamp));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "count", count));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "include_old", includeOld));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "sorting", sorting));


    String[] contentTypes = {
      
    };
    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

    if (contentType.startsWith("multipart/form-data")) {
      // file uploading
      MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();
      

      HttpEntity httpEntity = localVarBuilder.build();
      postBody = httpEntity;
    } else {
      // normal form params
          }

    String[] authNames = new String[] { "bearerAuth" };

    try {
      apiInvoker.invokeAPI(basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType, authNames,
        new Response.Listener<String>() {
          @Override
          public void onResponse(String localVarResponse) {
            try {
              responseListener.onResponse((Object) ApiInvoker.deserialize(localVarResponse,  "", Object.class));
            } catch (ApiException exception) {
               errorListener.onErrorResponse(new VolleyError(exception));
            }
          }
      }, new Response.ErrorListener() {
          @Override
          public void onErrorResponse(VolleyError error) {
            errorListener.onErrorResponse(error);
          }
      });
    } catch (ApiException ex) {
      errorListener.onErrorResponse(new VolleyError(ex));
    }
  }
  /**
  * Retrieve the latest user trades that have occurred for a specific instrument.
  * 
   * @param instrumentName Instrument name
   * @param startSeq The sequence number of the first trade to be returned
   * @param endSeq The sequence number of the last trade to be returned
   * @param count Number of requested items, default - &#x60;10&#x60;
   * @param includeOld Include trades older than 7 days, default - &#x60;false&#x60;
   * @param sorting Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database)
   * @return Object
  */
  public Object privateGetUserTradesByInstrumentGet (String instrumentName, Integer startSeq, Integer endSeq, Integer count, Boolean includeOld, String sorting) throws TimeoutException, ExecutionException, InterruptedException, ApiException {
    Object postBody = null;
    // verify the required parameter 'instrumentName' is set
    if (instrumentName == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'instrumentName' when calling privateGetUserTradesByInstrumentGet",
        new ApiException(400, "Missing the required parameter 'instrumentName' when calling privateGetUserTradesByInstrumentGet"));
    }

    // create path and map variables
    String path = "/private/get_user_trades_by_instrument";

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();
    queryParams.addAll(ApiInvoker.parameterToPairs("", "instrument_name", instrumentName));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "start_seq", startSeq));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "end_seq", endSeq));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "count", count));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "include_old", includeOld));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "sorting", sorting));
    String[] contentTypes = {
    };
    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

    if (contentType.startsWith("multipart/form-data")) {
      // file uploading
      MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();
      HttpEntity httpEntity = localVarBuilder.build();
      postBody = httpEntity;
    } else {
      // normal form params
    }

    String[] authNames = new String[] { "bearerAuth" };

    try {
      String localVarResponse = apiInvoker.invokeAPI (basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType, authNames);
      if (localVarResponse != null) {
         return (Object) ApiInvoker.deserialize(localVarResponse, "", Object.class);
      } else {
         return null;
      }
    } catch (ApiException ex) {
       throw ex;
    } catch (InterruptedException ex) {
       throw ex;
    } catch (ExecutionException ex) {
      if (ex.getCause() instanceof VolleyError) {
        VolleyError volleyError = (VolleyError)ex.getCause();
        if (volleyError.networkResponse != null) {
          throw new ApiException(volleyError.networkResponse.statusCode, volleyError.getMessage());
        }
      }
      throw ex;
    } catch (TimeoutException ex) {
      throw ex;
    }
  }

      /**
   * Retrieve the latest user trades that have occurred for a specific instrument.
   * 
   * @param instrumentName Instrument name   * @param startSeq The sequence number of the first trade to be returned   * @param endSeq The sequence number of the last trade to be returned   * @param count Number of requested items, default - &#x60;10&#x60;   * @param includeOld Include trades older than 7 days, default - &#x60;false&#x60;   * @param sorting Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database)
  */
  public void privateGetUserTradesByInstrumentGet (String instrumentName, Integer startSeq, Integer endSeq, Integer count, Boolean includeOld, String sorting, final Response.Listener<Object> responseListener, final Response.ErrorListener errorListener) {
    Object postBody = null;

    // verify the required parameter 'instrumentName' is set
    if (instrumentName == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'instrumentName' when calling privateGetUserTradesByInstrumentGet",
        new ApiException(400, "Missing the required parameter 'instrumentName' when calling privateGetUserTradesByInstrumentGet"));
    }

    // create path and map variables
    String path = "/private/get_user_trades_by_instrument".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    queryParams.addAll(ApiInvoker.parameterToPairs("", "instrument_name", instrumentName));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "start_seq", startSeq));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "end_seq", endSeq));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "count", count));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "include_old", includeOld));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "sorting", sorting));


    String[] contentTypes = {
      
    };
    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

    if (contentType.startsWith("multipart/form-data")) {
      // file uploading
      MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();
      

      HttpEntity httpEntity = localVarBuilder.build();
      postBody = httpEntity;
    } else {
      // normal form params
          }

    String[] authNames = new String[] { "bearerAuth" };

    try {
      apiInvoker.invokeAPI(basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType, authNames,
        new Response.Listener<String>() {
          @Override
          public void onResponse(String localVarResponse) {
            try {
              responseListener.onResponse((Object) ApiInvoker.deserialize(localVarResponse,  "", Object.class));
            } catch (ApiException exception) {
               errorListener.onErrorResponse(new VolleyError(exception));
            }
          }
      }, new Response.ErrorListener() {
          @Override
          public void onErrorResponse(VolleyError error) {
            errorListener.onErrorResponse(error);
          }
      });
    } catch (ApiException ex) {
      errorListener.onErrorResponse(new VolleyError(ex));
    }
  }
  /**
  * Retrieve the list of user trades that was created for given order
  * 
   * @param orderId The order id
   * @param sorting Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database)
   * @return Object
  */
  public Object privateGetUserTradesByOrderGet (String orderId, String sorting) throws TimeoutException, ExecutionException, InterruptedException, ApiException {
    Object postBody = null;
    // verify the required parameter 'orderId' is set
    if (orderId == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'orderId' when calling privateGetUserTradesByOrderGet",
        new ApiException(400, "Missing the required parameter 'orderId' when calling privateGetUserTradesByOrderGet"));
    }

    // create path and map variables
    String path = "/private/get_user_trades_by_order";

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();
    queryParams.addAll(ApiInvoker.parameterToPairs("", "order_id", orderId));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "sorting", sorting));
    String[] contentTypes = {
    };
    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

    if (contentType.startsWith("multipart/form-data")) {
      // file uploading
      MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();
      HttpEntity httpEntity = localVarBuilder.build();
      postBody = httpEntity;
    } else {
      // normal form params
    }

    String[] authNames = new String[] { "bearerAuth" };

    try {
      String localVarResponse = apiInvoker.invokeAPI (basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType, authNames);
      if (localVarResponse != null) {
         return (Object) ApiInvoker.deserialize(localVarResponse, "", Object.class);
      } else {
         return null;
      }
    } catch (ApiException ex) {
       throw ex;
    } catch (InterruptedException ex) {
       throw ex;
    } catch (ExecutionException ex) {
      if (ex.getCause() instanceof VolleyError) {
        VolleyError volleyError = (VolleyError)ex.getCause();
        if (volleyError.networkResponse != null) {
          throw new ApiException(volleyError.networkResponse.statusCode, volleyError.getMessage());
        }
      }
      throw ex;
    } catch (TimeoutException ex) {
      throw ex;
    }
  }

      /**
   * Retrieve the list of user trades that was created for given order
   * 
   * @param orderId The order id   * @param sorting Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database)
  */
  public void privateGetUserTradesByOrderGet (String orderId, String sorting, final Response.Listener<Object> responseListener, final Response.ErrorListener errorListener) {
    Object postBody = null;

    // verify the required parameter 'orderId' is set
    if (orderId == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'orderId' when calling privateGetUserTradesByOrderGet",
        new ApiException(400, "Missing the required parameter 'orderId' when calling privateGetUserTradesByOrderGet"));
    }

    // create path and map variables
    String path = "/private/get_user_trades_by_order".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    queryParams.addAll(ApiInvoker.parameterToPairs("", "order_id", orderId));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "sorting", sorting));


    String[] contentTypes = {
      
    };
    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

    if (contentType.startsWith("multipart/form-data")) {
      // file uploading
      MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();
      

      HttpEntity httpEntity = localVarBuilder.build();
      postBody = httpEntity;
    } else {
      // normal form params
          }

    String[] authNames = new String[] { "bearerAuth" };

    try {
      apiInvoker.invokeAPI(basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType, authNames,
        new Response.Listener<String>() {
          @Override
          public void onResponse(String localVarResponse) {
            try {
              responseListener.onResponse((Object) ApiInvoker.deserialize(localVarResponse,  "", Object.class));
            } catch (ApiException exception) {
               errorListener.onErrorResponse(new VolleyError(exception));
            }
          }
      }, new Response.ErrorListener() {
          @Override
          public void onErrorResponse(VolleyError error) {
            errorListener.onErrorResponse(error);
          }
      });
    } catch (ApiException ex) {
      errorListener.onErrorResponse(new VolleyError(ex));
    }
  }
  /**
  * Retrieve the latest users withdrawals
  * 
   * @param currency The currency symbol
   * @param count Number of requested items, default - &#x60;10&#x60;
   * @param offset The offset for pagination, default - &#x60;0&#x60;
   * @return Object
  */
  public Object privateGetWithdrawalsGet (String currency, Integer count, Integer offset) throws TimeoutException, ExecutionException, InterruptedException, ApiException {
    Object postBody = null;
    // verify the required parameter 'currency' is set
    if (currency == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'currency' when calling privateGetWithdrawalsGet",
        new ApiException(400, "Missing the required parameter 'currency' when calling privateGetWithdrawalsGet"));
    }

    // create path and map variables
    String path = "/private/get_withdrawals";

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();
    queryParams.addAll(ApiInvoker.parameterToPairs("", "currency", currency));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "count", count));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "offset", offset));
    String[] contentTypes = {
    };
    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

    if (contentType.startsWith("multipart/form-data")) {
      // file uploading
      MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();
      HttpEntity httpEntity = localVarBuilder.build();
      postBody = httpEntity;
    } else {
      // normal form params
    }

    String[] authNames = new String[] { "bearerAuth" };

    try {
      String localVarResponse = apiInvoker.invokeAPI (basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType, authNames);
      if (localVarResponse != null) {
         return (Object) ApiInvoker.deserialize(localVarResponse, "", Object.class);
      } else {
         return null;
      }
    } catch (ApiException ex) {
       throw ex;
    } catch (InterruptedException ex) {
       throw ex;
    } catch (ExecutionException ex) {
      if (ex.getCause() instanceof VolleyError) {
        VolleyError volleyError = (VolleyError)ex.getCause();
        if (volleyError.networkResponse != null) {
          throw new ApiException(volleyError.networkResponse.statusCode, volleyError.getMessage());
        }
      }
      throw ex;
    } catch (TimeoutException ex) {
      throw ex;
    }
  }

      /**
   * Retrieve the latest users withdrawals
   * 
   * @param currency The currency symbol   * @param count Number of requested items, default - &#x60;10&#x60;   * @param offset The offset for pagination, default - &#x60;0&#x60;
  */
  public void privateGetWithdrawalsGet (String currency, Integer count, Integer offset, final Response.Listener<Object> responseListener, final Response.ErrorListener errorListener) {
    Object postBody = null;

    // verify the required parameter 'currency' is set
    if (currency == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'currency' when calling privateGetWithdrawalsGet",
        new ApiException(400, "Missing the required parameter 'currency' when calling privateGetWithdrawalsGet"));
    }

    // create path and map variables
    String path = "/private/get_withdrawals".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    queryParams.addAll(ApiInvoker.parameterToPairs("", "currency", currency));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "count", count));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "offset", offset));


    String[] contentTypes = {
      
    };
    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

    if (contentType.startsWith("multipart/form-data")) {
      // file uploading
      MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();
      

      HttpEntity httpEntity = localVarBuilder.build();
      postBody = httpEntity;
    } else {
      // normal form params
          }

    String[] authNames = new String[] { "bearerAuth" };

    try {
      apiInvoker.invokeAPI(basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType, authNames,
        new Response.Listener<String>() {
          @Override
          public void onResponse(String localVarResponse) {
            try {
              responseListener.onResponse((Object) ApiInvoker.deserialize(localVarResponse,  "", Object.class));
            } catch (ApiException exception) {
               errorListener.onErrorResponse(new VolleyError(exception));
            }
          }
      }, new Response.ErrorListener() {
          @Override
          public void onErrorResponse(VolleyError error) {
            errorListener.onErrorResponse(error);
          }
      });
    } catch (ApiException ex) {
      errorListener.onErrorResponse(new VolleyError(ex));
    }
  }
  /**
  * Adds new entry to address book of given type
  * 
   * @param currency The currency symbol
   * @param type Address book type
   * @param address Address in currency format, it must be in address book
   * @param tfa TFA code, required when TFA is enabled for current account
   * @return Object
  */
  public Object privateRemoveFromAddressBookGet (String currency, String type, String address, String tfa) throws TimeoutException, ExecutionException, InterruptedException, ApiException {
    Object postBody = null;
    // verify the required parameter 'currency' is set
    if (currency == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'currency' when calling privateRemoveFromAddressBookGet",
        new ApiException(400, "Missing the required parameter 'currency' when calling privateRemoveFromAddressBookGet"));
    }
    // verify the required parameter 'type' is set
    if (type == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'type' when calling privateRemoveFromAddressBookGet",
        new ApiException(400, "Missing the required parameter 'type' when calling privateRemoveFromAddressBookGet"));
    }
    // verify the required parameter 'address' is set
    if (address == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'address' when calling privateRemoveFromAddressBookGet",
        new ApiException(400, "Missing the required parameter 'address' when calling privateRemoveFromAddressBookGet"));
    }

    // create path and map variables
    String path = "/private/remove_from_address_book";

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();
    queryParams.addAll(ApiInvoker.parameterToPairs("", "currency", currency));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "type", type));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "address", address));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "tfa", tfa));
    String[] contentTypes = {
    };
    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

    if (contentType.startsWith("multipart/form-data")) {
      // file uploading
      MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();
      HttpEntity httpEntity = localVarBuilder.build();
      postBody = httpEntity;
    } else {
      // normal form params
    }

    String[] authNames = new String[] { "bearerAuth" };

    try {
      String localVarResponse = apiInvoker.invokeAPI (basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType, authNames);
      if (localVarResponse != null) {
         return (Object) ApiInvoker.deserialize(localVarResponse, "", Object.class);
      } else {
         return null;
      }
    } catch (ApiException ex) {
       throw ex;
    } catch (InterruptedException ex) {
       throw ex;
    } catch (ExecutionException ex) {
      if (ex.getCause() instanceof VolleyError) {
        VolleyError volleyError = (VolleyError)ex.getCause();
        if (volleyError.networkResponse != null) {
          throw new ApiException(volleyError.networkResponse.statusCode, volleyError.getMessage());
        }
      }
      throw ex;
    } catch (TimeoutException ex) {
      throw ex;
    }
  }

      /**
   * Adds new entry to address book of given type
   * 
   * @param currency The currency symbol   * @param type Address book type   * @param address Address in currency format, it must be in address book   * @param tfa TFA code, required when TFA is enabled for current account
  */
  public void privateRemoveFromAddressBookGet (String currency, String type, String address, String tfa, final Response.Listener<Object> responseListener, final Response.ErrorListener errorListener) {
    Object postBody = null;

    // verify the required parameter 'currency' is set
    if (currency == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'currency' when calling privateRemoveFromAddressBookGet",
        new ApiException(400, "Missing the required parameter 'currency' when calling privateRemoveFromAddressBookGet"));
    }
    // verify the required parameter 'type' is set
    if (type == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'type' when calling privateRemoveFromAddressBookGet",
        new ApiException(400, "Missing the required parameter 'type' when calling privateRemoveFromAddressBookGet"));
    }
    // verify the required parameter 'address' is set
    if (address == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'address' when calling privateRemoveFromAddressBookGet",
        new ApiException(400, "Missing the required parameter 'address' when calling privateRemoveFromAddressBookGet"));
    }

    // create path and map variables
    String path = "/private/remove_from_address_book".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    queryParams.addAll(ApiInvoker.parameterToPairs("", "currency", currency));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "type", type));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "address", address));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "tfa", tfa));


    String[] contentTypes = {
      
    };
    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

    if (contentType.startsWith("multipart/form-data")) {
      // file uploading
      MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();
      

      HttpEntity httpEntity = localVarBuilder.build();
      postBody = httpEntity;
    } else {
      // normal form params
          }

    String[] authNames = new String[] { "bearerAuth" };

    try {
      apiInvoker.invokeAPI(basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType, authNames,
        new Response.Listener<String>() {
          @Override
          public void onResponse(String localVarResponse) {
            try {
              responseListener.onResponse((Object) ApiInvoker.deserialize(localVarResponse,  "", Object.class));
            } catch (ApiException exception) {
               errorListener.onErrorResponse(new VolleyError(exception));
            }
          }
      }, new Response.ErrorListener() {
          @Override
          public void onErrorResponse(VolleyError error) {
            errorListener.onErrorResponse(error);
          }
      });
    } catch (ApiException ex) {
      errorListener.onErrorResponse(new VolleyError(ex));
    }
  }
  /**
  * Places a sell order for an instrument.
  * 
   * @param instrumentName Instrument name
   * @param amount It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH
   * @param type The order type, default: &#x60;\&quot;limit\&quot;&#x60;
   * @param label user defined label for the order (maximum 32 characters)
   * @param price &lt;p&gt;The order price in base currency (Only for limit and stop_limit orders)&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;usd, the field price should be the option price value in USD.&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;implv, the field price should be a value of implied volatility in percentages. For example,  price&#x3D;100, means implied volatility of 100%&lt;/p&gt;
   * @param timeInForce &lt;p&gt;Specifies how long the order remains in effect. Default &#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; &lt;ul&gt; &lt;li&gt;&#x60;\&quot;good_til_cancelled\&quot;&#x60; - unfilled order remains in order book until cancelled&lt;/li&gt; &lt;li&gt;&#x60;\&quot;fill_or_kill\&quot;&#x60; - execute a transaction immediately and completely or not at all&lt;/li&gt; &lt;li&gt;&#x60;\&quot;immediate_or_cancel\&quot;&#x60; - execute a transaction immediately, and any portion of the order that cannot be immediately filled is cancelled&lt;/li&gt; &lt;/ul&gt;
   * @param maxShow Maximum amount within an order to be shown to other customers, &#x60;0&#x60; for invisible order
   * @param postOnly &lt;p&gt;If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.&lt;/p&gt; &lt;p&gt;Only valid in combination with time_in_force&#x3D;&#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt;
   * @param reduceOnly If &#x60;true&#x60;, the order is considered reduce-only which is intended to only reduce a current position
   * @param stopPrice Stop price, required for stop limit orders (Only for stop orders)
   * @param trigger Defines trigger type, required for &#x60;\&quot;stop_limit\&quot;&#x60; order type
   * @param advanced Advanced option order type. (Only for options)
   * @return Object
  */
  public Object privateSellGet (String instrumentName, BigDecimal amount, String type, String label, BigDecimal price, String timeInForce, BigDecimal maxShow, Boolean postOnly, Boolean reduceOnly, BigDecimal stopPrice, String trigger, String advanced) throws TimeoutException, ExecutionException, InterruptedException, ApiException {
    Object postBody = null;
    // verify the required parameter 'instrumentName' is set
    if (instrumentName == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'instrumentName' when calling privateSellGet",
        new ApiException(400, "Missing the required parameter 'instrumentName' when calling privateSellGet"));
    }
    // verify the required parameter 'amount' is set
    if (amount == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'amount' when calling privateSellGet",
        new ApiException(400, "Missing the required parameter 'amount' when calling privateSellGet"));
    }

    // create path and map variables
    String path = "/private/sell";

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();
    queryParams.addAll(ApiInvoker.parameterToPairs("", "instrument_name", instrumentName));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "amount", amount));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "type", type));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "label", label));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "price", price));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "time_in_force", timeInForce));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "max_show", maxShow));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "post_only", postOnly));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "reduce_only", reduceOnly));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "stop_price", stopPrice));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "trigger", trigger));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "advanced", advanced));
    String[] contentTypes = {
    };
    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

    if (contentType.startsWith("multipart/form-data")) {
      // file uploading
      MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();
      HttpEntity httpEntity = localVarBuilder.build();
      postBody = httpEntity;
    } else {
      // normal form params
    }

    String[] authNames = new String[] { "bearerAuth" };

    try {
      String localVarResponse = apiInvoker.invokeAPI (basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType, authNames);
      if (localVarResponse != null) {
         return (Object) ApiInvoker.deserialize(localVarResponse, "", Object.class);
      } else {
         return null;
      }
    } catch (ApiException ex) {
       throw ex;
    } catch (InterruptedException ex) {
       throw ex;
    } catch (ExecutionException ex) {
      if (ex.getCause() instanceof VolleyError) {
        VolleyError volleyError = (VolleyError)ex.getCause();
        if (volleyError.networkResponse != null) {
          throw new ApiException(volleyError.networkResponse.statusCode, volleyError.getMessage());
        }
      }
      throw ex;
    } catch (TimeoutException ex) {
      throw ex;
    }
  }

      /**
   * Places a sell order for an instrument.
   * 
   * @param instrumentName Instrument name   * @param amount It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH   * @param type The order type, default: &#x60;\&quot;limit\&quot;&#x60;   * @param label user defined label for the order (maximum 32 characters)   * @param price &lt;p&gt;The order price in base currency (Only for limit and stop_limit orders)&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;usd, the field price should be the option price value in USD.&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;implv, the field price should be a value of implied volatility in percentages. For example,  price&#x3D;100, means implied volatility of 100%&lt;/p&gt;   * @param timeInForce &lt;p&gt;Specifies how long the order remains in effect. Default &#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; &lt;ul&gt; &lt;li&gt;&#x60;\&quot;good_til_cancelled\&quot;&#x60; - unfilled order remains in order book until cancelled&lt;/li&gt; &lt;li&gt;&#x60;\&quot;fill_or_kill\&quot;&#x60; - execute a transaction immediately and completely or not at all&lt;/li&gt; &lt;li&gt;&#x60;\&quot;immediate_or_cancel\&quot;&#x60; - execute a transaction immediately, and any portion of the order that cannot be immediately filled is cancelled&lt;/li&gt; &lt;/ul&gt;   * @param maxShow Maximum amount within an order to be shown to other customers, &#x60;0&#x60; for invisible order   * @param postOnly &lt;p&gt;If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.&lt;/p&gt; &lt;p&gt;Only valid in combination with time_in_force&#x3D;&#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt;   * @param reduceOnly If &#x60;true&#x60;, the order is considered reduce-only which is intended to only reduce a current position   * @param stopPrice Stop price, required for stop limit orders (Only for stop orders)   * @param trigger Defines trigger type, required for &#x60;\&quot;stop_limit\&quot;&#x60; order type   * @param advanced Advanced option order type. (Only for options)
  */
  public void privateSellGet (String instrumentName, BigDecimal amount, String type, String label, BigDecimal price, String timeInForce, BigDecimal maxShow, Boolean postOnly, Boolean reduceOnly, BigDecimal stopPrice, String trigger, String advanced, final Response.Listener<Object> responseListener, final Response.ErrorListener errorListener) {
    Object postBody = null;

    // verify the required parameter 'instrumentName' is set
    if (instrumentName == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'instrumentName' when calling privateSellGet",
        new ApiException(400, "Missing the required parameter 'instrumentName' when calling privateSellGet"));
    }
    // verify the required parameter 'amount' is set
    if (amount == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'amount' when calling privateSellGet",
        new ApiException(400, "Missing the required parameter 'amount' when calling privateSellGet"));
    }

    // create path and map variables
    String path = "/private/sell".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    queryParams.addAll(ApiInvoker.parameterToPairs("", "instrument_name", instrumentName));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "amount", amount));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "type", type));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "label", label));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "price", price));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "time_in_force", timeInForce));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "max_show", maxShow));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "post_only", postOnly));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "reduce_only", reduceOnly));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "stop_price", stopPrice));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "trigger", trigger));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "advanced", advanced));


    String[] contentTypes = {
      
    };
    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

    if (contentType.startsWith("multipart/form-data")) {
      // file uploading
      MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();
      

      HttpEntity httpEntity = localVarBuilder.build();
      postBody = httpEntity;
    } else {
      // normal form params
          }

    String[] authNames = new String[] { "bearerAuth" };

    try {
      apiInvoker.invokeAPI(basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType, authNames,
        new Response.Listener<String>() {
          @Override
          public void onResponse(String localVarResponse) {
            try {
              responseListener.onResponse((Object) ApiInvoker.deserialize(localVarResponse,  "", Object.class));
            } catch (ApiException exception) {
               errorListener.onErrorResponse(new VolleyError(exception));
            }
          }
      }, new Response.ErrorListener() {
          @Override
          public void onErrorResponse(VolleyError error) {
            errorListener.onErrorResponse(error);
          }
      });
    } catch (ApiException ex) {
      errorListener.onErrorResponse(new VolleyError(ex));
    }
  }
  /**
  * Marks an announcement as read, so it will not be shown in &#x60;get_new_announcements&#x60;.
  * 
   * @param announcementId the ID of the announcement
   * @return Object
  */
  public Object privateSetAnnouncementAsReadGet (BigDecimal announcementId) throws TimeoutException, ExecutionException, InterruptedException, ApiException {
    Object postBody = null;
    // verify the required parameter 'announcementId' is set
    if (announcementId == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'announcementId' when calling privateSetAnnouncementAsReadGet",
        new ApiException(400, "Missing the required parameter 'announcementId' when calling privateSetAnnouncementAsReadGet"));
    }

    // create path and map variables
    String path = "/private/set_announcement_as_read";

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();
    queryParams.addAll(ApiInvoker.parameterToPairs("", "announcement_id", announcementId));
    String[] contentTypes = {
    };
    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

    if (contentType.startsWith("multipart/form-data")) {
      // file uploading
      MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();
      HttpEntity httpEntity = localVarBuilder.build();
      postBody = httpEntity;
    } else {
      // normal form params
    }

    String[] authNames = new String[] { "bearerAuth" };

    try {
      String localVarResponse = apiInvoker.invokeAPI (basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType, authNames);
      if (localVarResponse != null) {
         return (Object) ApiInvoker.deserialize(localVarResponse, "", Object.class);
      } else {
         return null;
      }
    } catch (ApiException ex) {
       throw ex;
    } catch (InterruptedException ex) {
       throw ex;
    } catch (ExecutionException ex) {
      if (ex.getCause() instanceof VolleyError) {
        VolleyError volleyError = (VolleyError)ex.getCause();
        if (volleyError.networkResponse != null) {
          throw new ApiException(volleyError.networkResponse.statusCode, volleyError.getMessage());
        }
      }
      throw ex;
    } catch (TimeoutException ex) {
      throw ex;
    }
  }

      /**
   * Marks an announcement as read, so it will not be shown in &#x60;get_new_announcements&#x60;.
   * 
   * @param announcementId the ID of the announcement
  */
  public void privateSetAnnouncementAsReadGet (BigDecimal announcementId, final Response.Listener<Object> responseListener, final Response.ErrorListener errorListener) {
    Object postBody = null;

    // verify the required parameter 'announcementId' is set
    if (announcementId == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'announcementId' when calling privateSetAnnouncementAsReadGet",
        new ApiException(400, "Missing the required parameter 'announcementId' when calling privateSetAnnouncementAsReadGet"));
    }

    // create path and map variables
    String path = "/private/set_announcement_as_read".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    queryParams.addAll(ApiInvoker.parameterToPairs("", "announcement_id", announcementId));


    String[] contentTypes = {
      
    };
    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

    if (contentType.startsWith("multipart/form-data")) {
      // file uploading
      MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();
      

      HttpEntity httpEntity = localVarBuilder.build();
      postBody = httpEntity;
    } else {
      // normal form params
          }

    String[] authNames = new String[] { "bearerAuth" };

    try {
      apiInvoker.invokeAPI(basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType, authNames,
        new Response.Listener<String>() {
          @Override
          public void onResponse(String localVarResponse) {
            try {
              responseListener.onResponse((Object) ApiInvoker.deserialize(localVarResponse,  "", Object.class));
            } catch (ApiException exception) {
               errorListener.onErrorResponse(new VolleyError(exception));
            }
          }
      }, new Response.ErrorListener() {
          @Override
          public void onErrorResponse(VolleyError error) {
            errorListener.onErrorResponse(error);
          }
      });
    } catch (ApiException ex) {
      errorListener.onErrorResponse(new VolleyError(ex));
    }
  }
  /**
  * Assign an email address to a subaccount. User will receive an email with confirmation link.
  * 
   * @param sid The user id for the subaccount
   * @param email The email address for the subaccount
   * @return Object
  */
  public Object privateSetEmailForSubaccountGet (Integer sid, String email) throws TimeoutException, ExecutionException, InterruptedException, ApiException {
    Object postBody = null;
    // verify the required parameter 'sid' is set
    if (sid == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'sid' when calling privateSetEmailForSubaccountGet",
        new ApiException(400, "Missing the required parameter 'sid' when calling privateSetEmailForSubaccountGet"));
    }
    // verify the required parameter 'email' is set
    if (email == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'email' when calling privateSetEmailForSubaccountGet",
        new ApiException(400, "Missing the required parameter 'email' when calling privateSetEmailForSubaccountGet"));
    }

    // create path and map variables
    String path = "/private/set_email_for_subaccount";

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();
    queryParams.addAll(ApiInvoker.parameterToPairs("", "sid", sid));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "email", email));
    String[] contentTypes = {
    };
    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

    if (contentType.startsWith("multipart/form-data")) {
      // file uploading
      MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();
      HttpEntity httpEntity = localVarBuilder.build();
      postBody = httpEntity;
    } else {
      // normal form params
    }

    String[] authNames = new String[] { "bearerAuth" };

    try {
      String localVarResponse = apiInvoker.invokeAPI (basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType, authNames);
      if (localVarResponse != null) {
         return (Object) ApiInvoker.deserialize(localVarResponse, "", Object.class);
      } else {
         return null;
      }
    } catch (ApiException ex) {
       throw ex;
    } catch (InterruptedException ex) {
       throw ex;
    } catch (ExecutionException ex) {
      if (ex.getCause() instanceof VolleyError) {
        VolleyError volleyError = (VolleyError)ex.getCause();
        if (volleyError.networkResponse != null) {
          throw new ApiException(volleyError.networkResponse.statusCode, volleyError.getMessage());
        }
      }
      throw ex;
    } catch (TimeoutException ex) {
      throw ex;
    }
  }

      /**
   * Assign an email address to a subaccount. User will receive an email with confirmation link.
   * 
   * @param sid The user id for the subaccount   * @param email The email address for the subaccount
  */
  public void privateSetEmailForSubaccountGet (Integer sid, String email, final Response.Listener<Object> responseListener, final Response.ErrorListener errorListener) {
    Object postBody = null;

    // verify the required parameter 'sid' is set
    if (sid == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'sid' when calling privateSetEmailForSubaccountGet",
        new ApiException(400, "Missing the required parameter 'sid' when calling privateSetEmailForSubaccountGet"));
    }
    // verify the required parameter 'email' is set
    if (email == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'email' when calling privateSetEmailForSubaccountGet",
        new ApiException(400, "Missing the required parameter 'email' when calling privateSetEmailForSubaccountGet"));
    }

    // create path and map variables
    String path = "/private/set_email_for_subaccount".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    queryParams.addAll(ApiInvoker.parameterToPairs("", "sid", sid));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "email", email));


    String[] contentTypes = {
      
    };
    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

    if (contentType.startsWith("multipart/form-data")) {
      // file uploading
      MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();
      

      HttpEntity httpEntity = localVarBuilder.build();
      postBody = httpEntity;
    } else {
      // normal form params
          }

    String[] authNames = new String[] { "bearerAuth" };

    try {
      apiInvoker.invokeAPI(basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType, authNames,
        new Response.Listener<String>() {
          @Override
          public void onResponse(String localVarResponse) {
            try {
              responseListener.onResponse((Object) ApiInvoker.deserialize(localVarResponse,  "", Object.class));
            } catch (ApiException exception) {
               errorListener.onErrorResponse(new VolleyError(exception));
            }
          }
      }, new Response.ErrorListener() {
          @Override
          public void onErrorResponse(VolleyError error) {
            errorListener.onErrorResponse(error);
          }
      });
    } catch (ApiException ex) {
      errorListener.onErrorResponse(new VolleyError(ex));
    }
  }
  /**
  * Changes the language to be used for emails.
  * 
   * @param language The abbreviated language name. Valid values include &#x60;\&quot;en\&quot;&#x60;, &#x60;\&quot;ko\&quot;&#x60;, &#x60;\&quot;zh\&quot;&#x60;
   * @return Object
  */
  public Object privateSetEmailLanguageGet (String language) throws TimeoutException, ExecutionException, InterruptedException, ApiException {
    Object postBody = null;
    // verify the required parameter 'language' is set
    if (language == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'language' when calling privateSetEmailLanguageGet",
        new ApiException(400, "Missing the required parameter 'language' when calling privateSetEmailLanguageGet"));
    }

    // create path and map variables
    String path = "/private/set_email_language";

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();
    queryParams.addAll(ApiInvoker.parameterToPairs("", "language", language));
    String[] contentTypes = {
    };
    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

    if (contentType.startsWith("multipart/form-data")) {
      // file uploading
      MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();
      HttpEntity httpEntity = localVarBuilder.build();
      postBody = httpEntity;
    } else {
      // normal form params
    }

    String[] authNames = new String[] { "bearerAuth" };

    try {
      String localVarResponse = apiInvoker.invokeAPI (basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType, authNames);
      if (localVarResponse != null) {
         return (Object) ApiInvoker.deserialize(localVarResponse, "", Object.class);
      } else {
         return null;
      }
    } catch (ApiException ex) {
       throw ex;
    } catch (InterruptedException ex) {
       throw ex;
    } catch (ExecutionException ex) {
      if (ex.getCause() instanceof VolleyError) {
        VolleyError volleyError = (VolleyError)ex.getCause();
        if (volleyError.networkResponse != null) {
          throw new ApiException(volleyError.networkResponse.statusCode, volleyError.getMessage());
        }
      }
      throw ex;
    } catch (TimeoutException ex) {
      throw ex;
    }
  }

      /**
   * Changes the language to be used for emails.
   * 
   * @param language The abbreviated language name. Valid values include &#x60;\&quot;en\&quot;&#x60;, &#x60;\&quot;ko\&quot;&#x60;, &#x60;\&quot;zh\&quot;&#x60;
  */
  public void privateSetEmailLanguageGet (String language, final Response.Listener<Object> responseListener, final Response.ErrorListener errorListener) {
    Object postBody = null;

    // verify the required parameter 'language' is set
    if (language == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'language' when calling privateSetEmailLanguageGet",
        new ApiException(400, "Missing the required parameter 'language' when calling privateSetEmailLanguageGet"));
    }

    // create path and map variables
    String path = "/private/set_email_language".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    queryParams.addAll(ApiInvoker.parameterToPairs("", "language", language));


    String[] contentTypes = {
      
    };
    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

    if (contentType.startsWith("multipart/form-data")) {
      // file uploading
      MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();
      

      HttpEntity httpEntity = localVarBuilder.build();
      postBody = httpEntity;
    } else {
      // normal form params
          }

    String[] authNames = new String[] { "bearerAuth" };

    try {
      apiInvoker.invokeAPI(basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType, authNames,
        new Response.Listener<String>() {
          @Override
          public void onResponse(String localVarResponse) {
            try {
              responseListener.onResponse((Object) ApiInvoker.deserialize(localVarResponse,  "", Object.class));
            } catch (ApiException exception) {
               errorListener.onErrorResponse(new VolleyError(exception));
            }
          }
      }, new Response.ErrorListener() {
          @Override
          public void onErrorResponse(VolleyError error) {
            errorListener.onErrorResponse(error);
          }
      });
    } catch (ApiException ex) {
      errorListener.onErrorResponse(new VolleyError(ex));
    }
  }
  /**
  * Set the password for the subaccount
  * 
   * @param sid The user id for the subaccount
   * @param password The password for the subaccount
   * @return Object
  */
  public Object privateSetPasswordForSubaccountGet (Integer sid, String password) throws TimeoutException, ExecutionException, InterruptedException, ApiException {
    Object postBody = null;
    // verify the required parameter 'sid' is set
    if (sid == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'sid' when calling privateSetPasswordForSubaccountGet",
        new ApiException(400, "Missing the required parameter 'sid' when calling privateSetPasswordForSubaccountGet"));
    }
    // verify the required parameter 'password' is set
    if (password == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'password' when calling privateSetPasswordForSubaccountGet",
        new ApiException(400, "Missing the required parameter 'password' when calling privateSetPasswordForSubaccountGet"));
    }

    // create path and map variables
    String path = "/private/set_password_for_subaccount";

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();
    queryParams.addAll(ApiInvoker.parameterToPairs("", "sid", sid));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "password", password));
    String[] contentTypes = {
    };
    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

    if (contentType.startsWith("multipart/form-data")) {
      // file uploading
      MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();
      HttpEntity httpEntity = localVarBuilder.build();
      postBody = httpEntity;
    } else {
      // normal form params
    }

    String[] authNames = new String[] { "bearerAuth" };

    try {
      String localVarResponse = apiInvoker.invokeAPI (basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType, authNames);
      if (localVarResponse != null) {
         return (Object) ApiInvoker.deserialize(localVarResponse, "", Object.class);
      } else {
         return null;
      }
    } catch (ApiException ex) {
       throw ex;
    } catch (InterruptedException ex) {
       throw ex;
    } catch (ExecutionException ex) {
      if (ex.getCause() instanceof VolleyError) {
        VolleyError volleyError = (VolleyError)ex.getCause();
        if (volleyError.networkResponse != null) {
          throw new ApiException(volleyError.networkResponse.statusCode, volleyError.getMessage());
        }
      }
      throw ex;
    } catch (TimeoutException ex) {
      throw ex;
    }
  }

      /**
   * Set the password for the subaccount
   * 
   * @param sid The user id for the subaccount   * @param password The password for the subaccount
  */
  public void privateSetPasswordForSubaccountGet (Integer sid, String password, final Response.Listener<Object> responseListener, final Response.ErrorListener errorListener) {
    Object postBody = null;

    // verify the required parameter 'sid' is set
    if (sid == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'sid' when calling privateSetPasswordForSubaccountGet",
        new ApiException(400, "Missing the required parameter 'sid' when calling privateSetPasswordForSubaccountGet"));
    }
    // verify the required parameter 'password' is set
    if (password == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'password' when calling privateSetPasswordForSubaccountGet",
        new ApiException(400, "Missing the required parameter 'password' when calling privateSetPasswordForSubaccountGet"));
    }

    // create path and map variables
    String path = "/private/set_password_for_subaccount".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    queryParams.addAll(ApiInvoker.parameterToPairs("", "sid", sid));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "password", password));


    String[] contentTypes = {
      
    };
    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

    if (contentType.startsWith("multipart/form-data")) {
      // file uploading
      MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();
      

      HttpEntity httpEntity = localVarBuilder.build();
      postBody = httpEntity;
    } else {
      // normal form params
          }

    String[] authNames = new String[] { "bearerAuth" };

    try {
      apiInvoker.invokeAPI(basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType, authNames,
        new Response.Listener<String>() {
          @Override
          public void onResponse(String localVarResponse) {
            try {
              responseListener.onResponse((Object) ApiInvoker.deserialize(localVarResponse,  "", Object.class));
            } catch (ApiException exception) {
               errorListener.onErrorResponse(new VolleyError(exception));
            }
          }
      }, new Response.ErrorListener() {
          @Override
          public void onErrorResponse(VolleyError error) {
            errorListener.onErrorResponse(error);
          }
      });
    } catch (ApiException ex) {
      errorListener.onErrorResponse(new VolleyError(ex));
    }
  }
  /**
  * Transfer funds to a subaccount.
  * 
   * @param currency The currency symbol
   * @param amount Amount of funds to be transferred
   * @param destination Id of destination subaccount
   * @return Object
  */
  public Object privateSubmitTransferToSubaccountGet (String currency, BigDecimal amount, Integer destination) throws TimeoutException, ExecutionException, InterruptedException, ApiException {
    Object postBody = null;
    // verify the required parameter 'currency' is set
    if (currency == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'currency' when calling privateSubmitTransferToSubaccountGet",
        new ApiException(400, "Missing the required parameter 'currency' when calling privateSubmitTransferToSubaccountGet"));
    }
    // verify the required parameter 'amount' is set
    if (amount == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'amount' when calling privateSubmitTransferToSubaccountGet",
        new ApiException(400, "Missing the required parameter 'amount' when calling privateSubmitTransferToSubaccountGet"));
    }
    // verify the required parameter 'destination' is set
    if (destination == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'destination' when calling privateSubmitTransferToSubaccountGet",
        new ApiException(400, "Missing the required parameter 'destination' when calling privateSubmitTransferToSubaccountGet"));
    }

    // create path and map variables
    String path = "/private/submit_transfer_to_subaccount";

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();
    queryParams.addAll(ApiInvoker.parameterToPairs("", "currency", currency));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "amount", amount));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "destination", destination));
    String[] contentTypes = {
    };
    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

    if (contentType.startsWith("multipart/form-data")) {
      // file uploading
      MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();
      HttpEntity httpEntity = localVarBuilder.build();
      postBody = httpEntity;
    } else {
      // normal form params
    }

    String[] authNames = new String[] { "bearerAuth" };

    try {
      String localVarResponse = apiInvoker.invokeAPI (basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType, authNames);
      if (localVarResponse != null) {
         return (Object) ApiInvoker.deserialize(localVarResponse, "", Object.class);
      } else {
         return null;
      }
    } catch (ApiException ex) {
       throw ex;
    } catch (InterruptedException ex) {
       throw ex;
    } catch (ExecutionException ex) {
      if (ex.getCause() instanceof VolleyError) {
        VolleyError volleyError = (VolleyError)ex.getCause();
        if (volleyError.networkResponse != null) {
          throw new ApiException(volleyError.networkResponse.statusCode, volleyError.getMessage());
        }
      }
      throw ex;
    } catch (TimeoutException ex) {
      throw ex;
    }
  }

      /**
   * Transfer funds to a subaccount.
   * 
   * @param currency The currency symbol   * @param amount Amount of funds to be transferred   * @param destination Id of destination subaccount
  */
  public void privateSubmitTransferToSubaccountGet (String currency, BigDecimal amount, Integer destination, final Response.Listener<Object> responseListener, final Response.ErrorListener errorListener) {
    Object postBody = null;

    // verify the required parameter 'currency' is set
    if (currency == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'currency' when calling privateSubmitTransferToSubaccountGet",
        new ApiException(400, "Missing the required parameter 'currency' when calling privateSubmitTransferToSubaccountGet"));
    }
    // verify the required parameter 'amount' is set
    if (amount == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'amount' when calling privateSubmitTransferToSubaccountGet",
        new ApiException(400, "Missing the required parameter 'amount' when calling privateSubmitTransferToSubaccountGet"));
    }
    // verify the required parameter 'destination' is set
    if (destination == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'destination' when calling privateSubmitTransferToSubaccountGet",
        new ApiException(400, "Missing the required parameter 'destination' when calling privateSubmitTransferToSubaccountGet"));
    }

    // create path and map variables
    String path = "/private/submit_transfer_to_subaccount".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    queryParams.addAll(ApiInvoker.parameterToPairs("", "currency", currency));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "amount", amount));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "destination", destination));


    String[] contentTypes = {
      
    };
    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

    if (contentType.startsWith("multipart/form-data")) {
      // file uploading
      MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();
      

      HttpEntity httpEntity = localVarBuilder.build();
      postBody = httpEntity;
    } else {
      // normal form params
          }

    String[] authNames = new String[] { "bearerAuth" };

    try {
      apiInvoker.invokeAPI(basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType, authNames,
        new Response.Listener<String>() {
          @Override
          public void onResponse(String localVarResponse) {
            try {
              responseListener.onResponse((Object) ApiInvoker.deserialize(localVarResponse,  "", Object.class));
            } catch (ApiException exception) {
               errorListener.onErrorResponse(new VolleyError(exception));
            }
          }
      }, new Response.ErrorListener() {
          @Override
          public void onErrorResponse(VolleyError error) {
            errorListener.onErrorResponse(error);
          }
      });
    } catch (ApiException ex) {
      errorListener.onErrorResponse(new VolleyError(ex));
    }
  }
  /**
  * Transfer funds to a another user.
  * 
   * @param currency The currency symbol
   * @param amount Amount of funds to be transferred
   * @param destination Destination address from address book
   * @param tfa TFA code, required when TFA is enabled for current account
   * @return Object
  */
  public Object privateSubmitTransferToUserGet (String currency, BigDecimal amount, String destination, String tfa) throws TimeoutException, ExecutionException, InterruptedException, ApiException {
    Object postBody = null;
    // verify the required parameter 'currency' is set
    if (currency == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'currency' when calling privateSubmitTransferToUserGet",
        new ApiException(400, "Missing the required parameter 'currency' when calling privateSubmitTransferToUserGet"));
    }
    // verify the required parameter 'amount' is set
    if (amount == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'amount' when calling privateSubmitTransferToUserGet",
        new ApiException(400, "Missing the required parameter 'amount' when calling privateSubmitTransferToUserGet"));
    }
    // verify the required parameter 'destination' is set
    if (destination == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'destination' when calling privateSubmitTransferToUserGet",
        new ApiException(400, "Missing the required parameter 'destination' when calling privateSubmitTransferToUserGet"));
    }

    // create path and map variables
    String path = "/private/submit_transfer_to_user";

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();
    queryParams.addAll(ApiInvoker.parameterToPairs("", "currency", currency));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "amount", amount));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "destination", destination));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "tfa", tfa));
    String[] contentTypes = {
    };
    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

    if (contentType.startsWith("multipart/form-data")) {
      // file uploading
      MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();
      HttpEntity httpEntity = localVarBuilder.build();
      postBody = httpEntity;
    } else {
      // normal form params
    }

    String[] authNames = new String[] { "bearerAuth" };

    try {
      String localVarResponse = apiInvoker.invokeAPI (basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType, authNames);
      if (localVarResponse != null) {
         return (Object) ApiInvoker.deserialize(localVarResponse, "", Object.class);
      } else {
         return null;
      }
    } catch (ApiException ex) {
       throw ex;
    } catch (InterruptedException ex) {
       throw ex;
    } catch (ExecutionException ex) {
      if (ex.getCause() instanceof VolleyError) {
        VolleyError volleyError = (VolleyError)ex.getCause();
        if (volleyError.networkResponse != null) {
          throw new ApiException(volleyError.networkResponse.statusCode, volleyError.getMessage());
        }
      }
      throw ex;
    } catch (TimeoutException ex) {
      throw ex;
    }
  }

      /**
   * Transfer funds to a another user.
   * 
   * @param currency The currency symbol   * @param amount Amount of funds to be transferred   * @param destination Destination address from address book   * @param tfa TFA code, required when TFA is enabled for current account
  */
  public void privateSubmitTransferToUserGet (String currency, BigDecimal amount, String destination, String tfa, final Response.Listener<Object> responseListener, final Response.ErrorListener errorListener) {
    Object postBody = null;

    // verify the required parameter 'currency' is set
    if (currency == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'currency' when calling privateSubmitTransferToUserGet",
        new ApiException(400, "Missing the required parameter 'currency' when calling privateSubmitTransferToUserGet"));
    }
    // verify the required parameter 'amount' is set
    if (amount == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'amount' when calling privateSubmitTransferToUserGet",
        new ApiException(400, "Missing the required parameter 'amount' when calling privateSubmitTransferToUserGet"));
    }
    // verify the required parameter 'destination' is set
    if (destination == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'destination' when calling privateSubmitTransferToUserGet",
        new ApiException(400, "Missing the required parameter 'destination' when calling privateSubmitTransferToUserGet"));
    }

    // create path and map variables
    String path = "/private/submit_transfer_to_user".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    queryParams.addAll(ApiInvoker.parameterToPairs("", "currency", currency));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "amount", amount));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "destination", destination));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "tfa", tfa));


    String[] contentTypes = {
      
    };
    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

    if (contentType.startsWith("multipart/form-data")) {
      // file uploading
      MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();
      

      HttpEntity httpEntity = localVarBuilder.build();
      postBody = httpEntity;
    } else {
      // normal form params
          }

    String[] authNames = new String[] { "bearerAuth" };

    try {
      apiInvoker.invokeAPI(basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType, authNames,
        new Response.Listener<String>() {
          @Override
          public void onResponse(String localVarResponse) {
            try {
              responseListener.onResponse((Object) ApiInvoker.deserialize(localVarResponse,  "", Object.class));
            } catch (ApiException exception) {
               errorListener.onErrorResponse(new VolleyError(exception));
            }
          }
      }, new Response.ErrorListener() {
          @Override
          public void onErrorResponse(VolleyError error) {
            errorListener.onErrorResponse(error);
          }
      });
    } catch (ApiException ex) {
      errorListener.onErrorResponse(new VolleyError(ex));
    }
  }
  /**
  * Enable or disable deposit address creation
  * 
   * @param currency The currency symbol
   * @param state 
   * @return Object
  */
  public Object privateToggleDepositAddressCreationGet (String currency, Boolean state) throws TimeoutException, ExecutionException, InterruptedException, ApiException {
    Object postBody = null;
    // verify the required parameter 'currency' is set
    if (currency == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'currency' when calling privateToggleDepositAddressCreationGet",
        new ApiException(400, "Missing the required parameter 'currency' when calling privateToggleDepositAddressCreationGet"));
    }
    // verify the required parameter 'state' is set
    if (state == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'state' when calling privateToggleDepositAddressCreationGet",
        new ApiException(400, "Missing the required parameter 'state' when calling privateToggleDepositAddressCreationGet"));
    }

    // create path and map variables
    String path = "/private/toggle_deposit_address_creation";

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();
    queryParams.addAll(ApiInvoker.parameterToPairs("", "currency", currency));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "state", state));
    String[] contentTypes = {
    };
    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

    if (contentType.startsWith("multipart/form-data")) {
      // file uploading
      MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();
      HttpEntity httpEntity = localVarBuilder.build();
      postBody = httpEntity;
    } else {
      // normal form params
    }

    String[] authNames = new String[] { "bearerAuth" };

    try {
      String localVarResponse = apiInvoker.invokeAPI (basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType, authNames);
      if (localVarResponse != null) {
         return (Object) ApiInvoker.deserialize(localVarResponse, "", Object.class);
      } else {
         return null;
      }
    } catch (ApiException ex) {
       throw ex;
    } catch (InterruptedException ex) {
       throw ex;
    } catch (ExecutionException ex) {
      if (ex.getCause() instanceof VolleyError) {
        VolleyError volleyError = (VolleyError)ex.getCause();
        if (volleyError.networkResponse != null) {
          throw new ApiException(volleyError.networkResponse.statusCode, volleyError.getMessage());
        }
      }
      throw ex;
    } catch (TimeoutException ex) {
      throw ex;
    }
  }

      /**
   * Enable or disable deposit address creation
   * 
   * @param currency The currency symbol   * @param state 
  */
  public void privateToggleDepositAddressCreationGet (String currency, Boolean state, final Response.Listener<Object> responseListener, final Response.ErrorListener errorListener) {
    Object postBody = null;

    // verify the required parameter 'currency' is set
    if (currency == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'currency' when calling privateToggleDepositAddressCreationGet",
        new ApiException(400, "Missing the required parameter 'currency' when calling privateToggleDepositAddressCreationGet"));
    }
    // verify the required parameter 'state' is set
    if (state == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'state' when calling privateToggleDepositAddressCreationGet",
        new ApiException(400, "Missing the required parameter 'state' when calling privateToggleDepositAddressCreationGet"));
    }

    // create path and map variables
    String path = "/private/toggle_deposit_address_creation".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    queryParams.addAll(ApiInvoker.parameterToPairs("", "currency", currency));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "state", state));


    String[] contentTypes = {
      
    };
    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

    if (contentType.startsWith("multipart/form-data")) {
      // file uploading
      MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();
      

      HttpEntity httpEntity = localVarBuilder.build();
      postBody = httpEntity;
    } else {
      // normal form params
          }

    String[] authNames = new String[] { "bearerAuth" };

    try {
      apiInvoker.invokeAPI(basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType, authNames,
        new Response.Listener<String>() {
          @Override
          public void onResponse(String localVarResponse) {
            try {
              responseListener.onResponse((Object) ApiInvoker.deserialize(localVarResponse,  "", Object.class));
            } catch (ApiException exception) {
               errorListener.onErrorResponse(new VolleyError(exception));
            }
          }
      }, new Response.ErrorListener() {
          @Override
          public void onErrorResponse(VolleyError error) {
            errorListener.onErrorResponse(error);
          }
      });
    } catch (ApiException ex) {
      errorListener.onErrorResponse(new VolleyError(ex));
    }
  }
  /**
  * Enable or disable sending of notifications for the subaccount.
  * 
   * @param sid The user id for the subaccount
   * @param state enable (&#x60;true&#x60;) or disable (&#x60;false&#x60;) notifications
   * @return Object
  */
  public Object privateToggleNotificationsFromSubaccountGet (Integer sid, Boolean state) throws TimeoutException, ExecutionException, InterruptedException, ApiException {
    Object postBody = null;
    // verify the required parameter 'sid' is set
    if (sid == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'sid' when calling privateToggleNotificationsFromSubaccountGet",
        new ApiException(400, "Missing the required parameter 'sid' when calling privateToggleNotificationsFromSubaccountGet"));
    }
    // verify the required parameter 'state' is set
    if (state == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'state' when calling privateToggleNotificationsFromSubaccountGet",
        new ApiException(400, "Missing the required parameter 'state' when calling privateToggleNotificationsFromSubaccountGet"));
    }

    // create path and map variables
    String path = "/private/toggle_notifications_from_subaccount";

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();
    queryParams.addAll(ApiInvoker.parameterToPairs("", "sid", sid));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "state", state));
    String[] contentTypes = {
    };
    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

    if (contentType.startsWith("multipart/form-data")) {
      // file uploading
      MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();
      HttpEntity httpEntity = localVarBuilder.build();
      postBody = httpEntity;
    } else {
      // normal form params
    }

    String[] authNames = new String[] { "bearerAuth" };

    try {
      String localVarResponse = apiInvoker.invokeAPI (basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType, authNames);
      if (localVarResponse != null) {
         return (Object) ApiInvoker.deserialize(localVarResponse, "", Object.class);
      } else {
         return null;
      }
    } catch (ApiException ex) {
       throw ex;
    } catch (InterruptedException ex) {
       throw ex;
    } catch (ExecutionException ex) {
      if (ex.getCause() instanceof VolleyError) {
        VolleyError volleyError = (VolleyError)ex.getCause();
        if (volleyError.networkResponse != null) {
          throw new ApiException(volleyError.networkResponse.statusCode, volleyError.getMessage());
        }
      }
      throw ex;
    } catch (TimeoutException ex) {
      throw ex;
    }
  }

      /**
   * Enable or disable sending of notifications for the subaccount.
   * 
   * @param sid The user id for the subaccount   * @param state enable (&#x60;true&#x60;) or disable (&#x60;false&#x60;) notifications
  */
  public void privateToggleNotificationsFromSubaccountGet (Integer sid, Boolean state, final Response.Listener<Object> responseListener, final Response.ErrorListener errorListener) {
    Object postBody = null;

    // verify the required parameter 'sid' is set
    if (sid == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'sid' when calling privateToggleNotificationsFromSubaccountGet",
        new ApiException(400, "Missing the required parameter 'sid' when calling privateToggleNotificationsFromSubaccountGet"));
    }
    // verify the required parameter 'state' is set
    if (state == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'state' when calling privateToggleNotificationsFromSubaccountGet",
        new ApiException(400, "Missing the required parameter 'state' when calling privateToggleNotificationsFromSubaccountGet"));
    }

    // create path and map variables
    String path = "/private/toggle_notifications_from_subaccount".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    queryParams.addAll(ApiInvoker.parameterToPairs("", "sid", sid));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "state", state));


    String[] contentTypes = {
      
    };
    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

    if (contentType.startsWith("multipart/form-data")) {
      // file uploading
      MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();
      

      HttpEntity httpEntity = localVarBuilder.build();
      postBody = httpEntity;
    } else {
      // normal form params
          }

    String[] authNames = new String[] { "bearerAuth" };

    try {
      apiInvoker.invokeAPI(basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType, authNames,
        new Response.Listener<String>() {
          @Override
          public void onResponse(String localVarResponse) {
            try {
              responseListener.onResponse((Object) ApiInvoker.deserialize(localVarResponse,  "", Object.class));
            } catch (ApiException exception) {
               errorListener.onErrorResponse(new VolleyError(exception));
            }
          }
      }, new Response.ErrorListener() {
          @Override
          public void onErrorResponse(VolleyError error) {
            errorListener.onErrorResponse(error);
          }
      });
    } catch (ApiException ex) {
      errorListener.onErrorResponse(new VolleyError(ex));
    }
  }
  /**
  * Enable or disable login for a subaccount. If login is disabled and a session for the subaccount exists, this session will be terminated.
  * 
   * @param sid The user id for the subaccount
   * @param state enable or disable login.
   * @return Object
  */
  public Object privateToggleSubaccountLoginGet (Integer sid, String state) throws TimeoutException, ExecutionException, InterruptedException, ApiException {
    Object postBody = null;
    // verify the required parameter 'sid' is set
    if (sid == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'sid' when calling privateToggleSubaccountLoginGet",
        new ApiException(400, "Missing the required parameter 'sid' when calling privateToggleSubaccountLoginGet"));
    }
    // verify the required parameter 'state' is set
    if (state == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'state' when calling privateToggleSubaccountLoginGet",
        new ApiException(400, "Missing the required parameter 'state' when calling privateToggleSubaccountLoginGet"));
    }

    // create path and map variables
    String path = "/private/toggle_subaccount_login";

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();
    queryParams.addAll(ApiInvoker.parameterToPairs("", "sid", sid));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "state", state));
    String[] contentTypes = {
    };
    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

    if (contentType.startsWith("multipart/form-data")) {
      // file uploading
      MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();
      HttpEntity httpEntity = localVarBuilder.build();
      postBody = httpEntity;
    } else {
      // normal form params
    }

    String[] authNames = new String[] { "bearerAuth" };

    try {
      String localVarResponse = apiInvoker.invokeAPI (basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType, authNames);
      if (localVarResponse != null) {
         return (Object) ApiInvoker.deserialize(localVarResponse, "", Object.class);
      } else {
         return null;
      }
    } catch (ApiException ex) {
       throw ex;
    } catch (InterruptedException ex) {
       throw ex;
    } catch (ExecutionException ex) {
      if (ex.getCause() instanceof VolleyError) {
        VolleyError volleyError = (VolleyError)ex.getCause();
        if (volleyError.networkResponse != null) {
          throw new ApiException(volleyError.networkResponse.statusCode, volleyError.getMessage());
        }
      }
      throw ex;
    } catch (TimeoutException ex) {
      throw ex;
    }
  }

      /**
   * Enable or disable login for a subaccount. If login is disabled and a session for the subaccount exists, this session will be terminated.
   * 
   * @param sid The user id for the subaccount   * @param state enable or disable login.
  */
  public void privateToggleSubaccountLoginGet (Integer sid, String state, final Response.Listener<Object> responseListener, final Response.ErrorListener errorListener) {
    Object postBody = null;

    // verify the required parameter 'sid' is set
    if (sid == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'sid' when calling privateToggleSubaccountLoginGet",
        new ApiException(400, "Missing the required parameter 'sid' when calling privateToggleSubaccountLoginGet"));
    }
    // verify the required parameter 'state' is set
    if (state == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'state' when calling privateToggleSubaccountLoginGet",
        new ApiException(400, "Missing the required parameter 'state' when calling privateToggleSubaccountLoginGet"));
    }

    // create path and map variables
    String path = "/private/toggle_subaccount_login".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    queryParams.addAll(ApiInvoker.parameterToPairs("", "sid", sid));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "state", state));


    String[] contentTypes = {
      
    };
    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

    if (contentType.startsWith("multipart/form-data")) {
      // file uploading
      MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();
      

      HttpEntity httpEntity = localVarBuilder.build();
      postBody = httpEntity;
    } else {
      // normal form params
          }

    String[] authNames = new String[] { "bearerAuth" };

    try {
      apiInvoker.invokeAPI(basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType, authNames,
        new Response.Listener<String>() {
          @Override
          public void onResponse(String localVarResponse) {
            try {
              responseListener.onResponse((Object) ApiInvoker.deserialize(localVarResponse,  "", Object.class));
            } catch (ApiException exception) {
               errorListener.onErrorResponse(new VolleyError(exception));
            }
          }
      }, new Response.ErrorListener() {
          @Override
          public void onErrorResponse(VolleyError error) {
            errorListener.onErrorResponse(error);
          }
      });
    } catch (ApiException ex) {
      errorListener.onErrorResponse(new VolleyError(ex));
    }
  }
  /**
  * Creates a new withdrawal request
  * 
   * @param currency The currency symbol
   * @param address Address in currency format, it must be in address book
   * @param amount Amount of funds to be withdrawn
   * @param priority Withdrawal priority, optional for BTC, default: &#x60;high&#x60;
   * @param tfa TFA code, required when TFA is enabled for current account
   * @return Object
  */
  public Object privateWithdrawGet (String currency, String address, BigDecimal amount, String priority, String tfa) throws TimeoutException, ExecutionException, InterruptedException, ApiException {
    Object postBody = null;
    // verify the required parameter 'currency' is set
    if (currency == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'currency' when calling privateWithdrawGet",
        new ApiException(400, "Missing the required parameter 'currency' when calling privateWithdrawGet"));
    }
    // verify the required parameter 'address' is set
    if (address == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'address' when calling privateWithdrawGet",
        new ApiException(400, "Missing the required parameter 'address' when calling privateWithdrawGet"));
    }
    // verify the required parameter 'amount' is set
    if (amount == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'amount' when calling privateWithdrawGet",
        new ApiException(400, "Missing the required parameter 'amount' when calling privateWithdrawGet"));
    }

    // create path and map variables
    String path = "/private/withdraw";

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();
    queryParams.addAll(ApiInvoker.parameterToPairs("", "currency", currency));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "address", address));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "amount", amount));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "priority", priority));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "tfa", tfa));
    String[] contentTypes = {
    };
    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

    if (contentType.startsWith("multipart/form-data")) {
      // file uploading
      MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();
      HttpEntity httpEntity = localVarBuilder.build();
      postBody = httpEntity;
    } else {
      // normal form params
    }

    String[] authNames = new String[] { "bearerAuth" };

    try {
      String localVarResponse = apiInvoker.invokeAPI (basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType, authNames);
      if (localVarResponse != null) {
         return (Object) ApiInvoker.deserialize(localVarResponse, "", Object.class);
      } else {
         return null;
      }
    } catch (ApiException ex) {
       throw ex;
    } catch (InterruptedException ex) {
       throw ex;
    } catch (ExecutionException ex) {
      if (ex.getCause() instanceof VolleyError) {
        VolleyError volleyError = (VolleyError)ex.getCause();
        if (volleyError.networkResponse != null) {
          throw new ApiException(volleyError.networkResponse.statusCode, volleyError.getMessage());
        }
      }
      throw ex;
    } catch (TimeoutException ex) {
      throw ex;
    }
  }

      /**
   * Creates a new withdrawal request
   * 
   * @param currency The currency symbol   * @param address Address in currency format, it must be in address book   * @param amount Amount of funds to be withdrawn   * @param priority Withdrawal priority, optional for BTC, default: &#x60;high&#x60;   * @param tfa TFA code, required when TFA is enabled for current account
  */
  public void privateWithdrawGet (String currency, String address, BigDecimal amount, String priority, String tfa, final Response.Listener<Object> responseListener, final Response.ErrorListener errorListener) {
    Object postBody = null;

    // verify the required parameter 'currency' is set
    if (currency == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'currency' when calling privateWithdrawGet",
        new ApiException(400, "Missing the required parameter 'currency' when calling privateWithdrawGet"));
    }
    // verify the required parameter 'address' is set
    if (address == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'address' when calling privateWithdrawGet",
        new ApiException(400, "Missing the required parameter 'address' when calling privateWithdrawGet"));
    }
    // verify the required parameter 'amount' is set
    if (amount == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'amount' when calling privateWithdrawGet",
        new ApiException(400, "Missing the required parameter 'amount' when calling privateWithdrawGet"));
    }

    // create path and map variables
    String path = "/private/withdraw".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    queryParams.addAll(ApiInvoker.parameterToPairs("", "currency", currency));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "address", address));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "amount", amount));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "priority", priority));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "tfa", tfa));


    String[] contentTypes = {
      
    };
    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

    if (contentType.startsWith("multipart/form-data")) {
      // file uploading
      MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();
      

      HttpEntity httpEntity = localVarBuilder.build();
      postBody = httpEntity;
    } else {
      // normal form params
          }

    String[] authNames = new String[] { "bearerAuth" };

    try {
      apiInvoker.invokeAPI(basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType, authNames,
        new Response.Listener<String>() {
          @Override
          public void onResponse(String localVarResponse) {
            try {
              responseListener.onResponse((Object) ApiInvoker.deserialize(localVarResponse,  "", Object.class));
            } catch (ApiException exception) {
               errorListener.onErrorResponse(new VolleyError(exception));
            }
          }
      }, new Response.ErrorListener() {
          @Override
          public void onErrorResponse(VolleyError error) {
            errorListener.onErrorResponse(error);
          }
      });
    } catch (ApiException ex) {
      errorListener.onErrorResponse(new VolleyError(ex));
    }
  }
}
