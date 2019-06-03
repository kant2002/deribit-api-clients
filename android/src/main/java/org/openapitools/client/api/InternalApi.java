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

public class InternalApi {
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
  * Get information to be displayed in the footer of the website.
  * 
   * @return Object
  */
  public Object publicGetFooterGet () throws TimeoutException, ExecutionException, InterruptedException, ApiException {
    Object postBody = null;

    // create path and map variables
    String path = "/public/get_footer";

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
   * Get information to be displayed in the footer of the website.
   * 

  */
  public void publicGetFooterGet (final Response.Listener<Object> responseListener, final Response.ErrorListener errorListener) {
    Object postBody = null;


    // create path and map variables
    String path = "/public/get_footer".replaceAll("\\{format\\}","json");

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
  * Retrives market prices and its implied volatility of options instruments
  * 
   * @param currency The currency symbol
   * @return Object
  */
  public Object publicGetOptionMarkPricesGet (String currency) throws TimeoutException, ExecutionException, InterruptedException, ApiException {
    Object postBody = null;
    // verify the required parameter 'currency' is set
    if (currency == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'currency' when calling publicGetOptionMarkPricesGet",
        new ApiException(400, "Missing the required parameter 'currency' when calling publicGetOptionMarkPricesGet"));
    }

    // create path and map variables
    String path = "/public/get_option_mark_prices";

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
   * Retrives market prices and its implied volatility of options instruments
   * 
   * @param currency The currency symbol
  */
  public void publicGetOptionMarkPricesGet (String currency, final Response.Listener<Object> responseListener, final Response.ErrorListener errorListener) {
    Object postBody = null;

    // verify the required parameter 'currency' is set
    if (currency == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'currency' when calling publicGetOptionMarkPricesGet",
        new ApiException(400, "Missing the required parameter 'currency' when calling publicGetOptionMarkPricesGet"));
    }

    // create path and map variables
    String path = "/public/get_option_mark_prices".replaceAll("\\{format\\}","json");

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
  * Method used to introduce the client software connected to Deribit platform over websocket. Provided data may have an impact on the maintained connection and will be collected for internal statistical purposes. In response, Deribit will also introduce itself.
  * 
   * @param field Name of the field to be validated, examples: postal_code, date_of_birth
   * @param value Value to be checked
   * @param value2 Additional value to be compared with
   * @return Object
  */
  public Object publicValidateFieldGet (String field, String value, String value2) throws TimeoutException, ExecutionException, InterruptedException, ApiException {
    Object postBody = null;
    // verify the required parameter 'field' is set
    if (field == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'field' when calling publicValidateFieldGet",
        new ApiException(400, "Missing the required parameter 'field' when calling publicValidateFieldGet"));
    }
    // verify the required parameter 'value' is set
    if (value == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'value' when calling publicValidateFieldGet",
        new ApiException(400, "Missing the required parameter 'value' when calling publicValidateFieldGet"));
    }

    // create path and map variables
    String path = "/public/validate_field";

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();
    queryParams.addAll(ApiInvoker.parameterToPairs("", "field", field));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "value", value));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "value2", value2));
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
   * Method used to introduce the client software connected to Deribit platform over websocket. Provided data may have an impact on the maintained connection and will be collected for internal statistical purposes. In response, Deribit will also introduce itself.
   * 
   * @param field Name of the field to be validated, examples: postal_code, date_of_birth   * @param value Value to be checked   * @param value2 Additional value to be compared with
  */
  public void publicValidateFieldGet (String field, String value, String value2, final Response.Listener<Object> responseListener, final Response.ErrorListener errorListener) {
    Object postBody = null;

    // verify the required parameter 'field' is set
    if (field == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'field' when calling publicValidateFieldGet",
        new ApiException(400, "Missing the required parameter 'field' when calling publicValidateFieldGet"));
    }
    // verify the required parameter 'value' is set
    if (value == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'value' when calling publicValidateFieldGet",
        new ApiException(400, "Missing the required parameter 'value' when calling publicValidateFieldGet"));
    }

    // create path and map variables
    String path = "/public/validate_field".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    queryParams.addAll(ApiInvoker.parameterToPairs("", "field", field));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "value", value));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "value2", value2));


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
