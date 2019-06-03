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


package org.openapitools.client.model;

import java.util.Objects;
import java.util.Arrays;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;
import java.math.BigDecimal;

/**
 * Order
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2019-06-03T12:41:14.884+02:00[Europe/Paris]")
public class Order {
  /**
   * direction, &#x60;buy&#x60; or &#x60;sell&#x60;
   */
  @JsonAdapter(DirectionEnum.Adapter.class)
  public enum DirectionEnum {
    BUY("buy"),
    
    SELL("sell");

    private String value;

    DirectionEnum(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    public static DirectionEnum fromValue(String value) {
      for (DirectionEnum b : DirectionEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }

    public static class Adapter extends TypeAdapter<DirectionEnum> {
      @Override
      public void write(final JsonWriter jsonWriter, final DirectionEnum enumeration) throws IOException {
        jsonWriter.value(enumeration.getValue());
      }

      @Override
      public DirectionEnum read(final JsonReader jsonReader) throws IOException {
        String value = jsonReader.nextString();
        return DirectionEnum.fromValue(value);
      }
    }
  }

  public static final String SERIALIZED_NAME_DIRECTION = "direction";
  @SerializedName(SERIALIZED_NAME_DIRECTION)
  private DirectionEnum direction;

  public static final String SERIALIZED_NAME_REDUCE_ONLY = "reduce_only";
  @SerializedName(SERIALIZED_NAME_REDUCE_ONLY)
  private Boolean reduceOnly;

  public static final String SERIALIZED_NAME_TRIGGERED = "triggered";
  @SerializedName(SERIALIZED_NAME_TRIGGERED)
  private Boolean triggered;

  public static final String SERIALIZED_NAME_ORDER_ID = "order_id";
  @SerializedName(SERIALIZED_NAME_ORDER_ID)
  private String orderId;

  public static final String SERIALIZED_NAME_PRICE = "price";
  @SerializedName(SERIALIZED_NAME_PRICE)
  private BigDecimal price;

  /**
   * Order time in force: &#x60;\&quot;good_til_cancelled\&quot;&#x60;, &#x60;\&quot;fill_or_kill\&quot;&#x60;, &#x60;\&quot;immediate_or_cancel\&quot;&#x60;
   */
  @JsonAdapter(TimeInForceEnum.Adapter.class)
  public enum TimeInForceEnum {
    GOOD_TIL_CANCELLED("good_til_cancelled"),
    
    FILL_OR_KILL("fill_or_kill"),
    
    IMMEDIATE_OR_CANCEL("immediate_or_cancel");

    private String value;

    TimeInForceEnum(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    public static TimeInForceEnum fromValue(String value) {
      for (TimeInForceEnum b : TimeInForceEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }

    public static class Adapter extends TypeAdapter<TimeInForceEnum> {
      @Override
      public void write(final JsonWriter jsonWriter, final TimeInForceEnum enumeration) throws IOException {
        jsonWriter.value(enumeration.getValue());
      }

      @Override
      public TimeInForceEnum read(final JsonReader jsonReader) throws IOException {
        String value = jsonReader.nextString();
        return TimeInForceEnum.fromValue(value);
      }
    }
  }

  public static final String SERIALIZED_NAME_TIME_IN_FORCE = "time_in_force";
  @SerializedName(SERIALIZED_NAME_TIME_IN_FORCE)
  private TimeInForceEnum timeInForce;

  public static final String SERIALIZED_NAME_API = "api";
  @SerializedName(SERIALIZED_NAME_API)
  private Boolean api;

  /**
   * order state, &#x60;\&quot;open\&quot;&#x60;, &#x60;\&quot;filled\&quot;&#x60;, &#x60;\&quot;rejected\&quot;&#x60;, &#x60;\&quot;cancelled\&quot;&#x60;, &#x60;\&quot;untriggered\&quot;&#x60;
   */
  @JsonAdapter(OrderStateEnum.Adapter.class)
  public enum OrderStateEnum {
    OPEN("open"),
    
    FILLED("filled"),
    
    REJECTED("rejected"),
    
    CANCELLED("cancelled"),
    
    UNTRIGGERED("untriggered"),
    
    TRIGGERED("triggered");

    private String value;

    OrderStateEnum(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    public static OrderStateEnum fromValue(String value) {
      for (OrderStateEnum b : OrderStateEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }

    public static class Adapter extends TypeAdapter<OrderStateEnum> {
      @Override
      public void write(final JsonWriter jsonWriter, final OrderStateEnum enumeration) throws IOException {
        jsonWriter.value(enumeration.getValue());
      }

      @Override
      public OrderStateEnum read(final JsonReader jsonReader) throws IOException {
        String value = jsonReader.nextString();
        return OrderStateEnum.fromValue(value);
      }
    }
  }

  public static final String SERIALIZED_NAME_ORDER_STATE = "order_state";
  @SerializedName(SERIALIZED_NAME_ORDER_STATE)
  private OrderStateEnum orderState;

  public static final String SERIALIZED_NAME_IMPLV = "implv";
  @SerializedName(SERIALIZED_NAME_IMPLV)
  private BigDecimal implv;

  /**
   * advanced type: &#x60;\&quot;usd\&quot;&#x60; or &#x60;\&quot;implv\&quot;&#x60; (Only for options; field is omitted if not applicable). 
   */
  @JsonAdapter(AdvancedEnum.Adapter.class)
  public enum AdvancedEnum {
    USD("usd"),
    
    IMPLV("implv");

    private String value;

    AdvancedEnum(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    public static AdvancedEnum fromValue(String value) {
      for (AdvancedEnum b : AdvancedEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }

    public static class Adapter extends TypeAdapter<AdvancedEnum> {
      @Override
      public void write(final JsonWriter jsonWriter, final AdvancedEnum enumeration) throws IOException {
        jsonWriter.value(enumeration.getValue());
      }

      @Override
      public AdvancedEnum read(final JsonReader jsonReader) throws IOException {
        String value = jsonReader.nextString();
        return AdvancedEnum.fromValue(value);
      }
    }
  }

  public static final String SERIALIZED_NAME_ADVANCED = "advanced";
  @SerializedName(SERIALIZED_NAME_ADVANCED)
  private AdvancedEnum advanced;

  public static final String SERIALIZED_NAME_POST_ONLY = "post_only";
  @SerializedName(SERIALIZED_NAME_POST_ONLY)
  private Boolean postOnly;

  public static final String SERIALIZED_NAME_USD = "usd";
  @SerializedName(SERIALIZED_NAME_USD)
  private BigDecimal usd;

  public static final String SERIALIZED_NAME_STOP_PRICE = "stop_price";
  @SerializedName(SERIALIZED_NAME_STOP_PRICE)
  private BigDecimal stopPrice;

  /**
   * order type, &#x60;\&quot;limit\&quot;&#x60;, &#x60;\&quot;market\&quot;&#x60;, &#x60;\&quot;stop_limit\&quot;&#x60;, &#x60;\&quot;stop_market\&quot;&#x60;
   */
  @JsonAdapter(OrderTypeEnum.Adapter.class)
  public enum OrderTypeEnum {
    MARKET("market"),
    
    LIMIT("limit"),
    
    STOP_MARKET("stop_market"),
    
    STOP_LIMIT("stop_limit");

    private String value;

    OrderTypeEnum(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    public static OrderTypeEnum fromValue(String value) {
      for (OrderTypeEnum b : OrderTypeEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }

    public static class Adapter extends TypeAdapter<OrderTypeEnum> {
      @Override
      public void write(final JsonWriter jsonWriter, final OrderTypeEnum enumeration) throws IOException {
        jsonWriter.value(enumeration.getValue());
      }

      @Override
      public OrderTypeEnum read(final JsonReader jsonReader) throws IOException {
        String value = jsonReader.nextString();
        return OrderTypeEnum.fromValue(value);
      }
    }
  }

  public static final String SERIALIZED_NAME_ORDER_TYPE = "order_type";
  @SerializedName(SERIALIZED_NAME_ORDER_TYPE)
  private OrderTypeEnum orderType;

  public static final String SERIALIZED_NAME_LAST_UPDATE_TIMESTAMP = "last_update_timestamp";
  @SerializedName(SERIALIZED_NAME_LAST_UPDATE_TIMESTAMP)
  private Integer lastUpdateTimestamp;

  /**
   * Original order type. Optional field
   */
  @JsonAdapter(OriginalOrderTypeEnum.Adapter.class)
  public enum OriginalOrderTypeEnum {
    MARKET("market");

    private String value;

    OriginalOrderTypeEnum(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    public static OriginalOrderTypeEnum fromValue(String value) {
      for (OriginalOrderTypeEnum b : OriginalOrderTypeEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }

    public static class Adapter extends TypeAdapter<OriginalOrderTypeEnum> {
      @Override
      public void write(final JsonWriter jsonWriter, final OriginalOrderTypeEnum enumeration) throws IOException {
        jsonWriter.value(enumeration.getValue());
      }

      @Override
      public OriginalOrderTypeEnum read(final JsonReader jsonReader) throws IOException {
        String value = jsonReader.nextString();
        return OriginalOrderTypeEnum.fromValue(value);
      }
    }
  }

  public static final String SERIALIZED_NAME_ORIGINAL_ORDER_TYPE = "original_order_type";
  @SerializedName(SERIALIZED_NAME_ORIGINAL_ORDER_TYPE)
  private OriginalOrderTypeEnum originalOrderType;

  public static final String SERIALIZED_NAME_MAX_SHOW = "max_show";
  @SerializedName(SERIALIZED_NAME_MAX_SHOW)
  private BigDecimal maxShow;

  public static final String SERIALIZED_NAME_PROFIT_LOSS = "profit_loss";
  @SerializedName(SERIALIZED_NAME_PROFIT_LOSS)
  private BigDecimal profitLoss;

  public static final String SERIALIZED_NAME_IS_LIQUIDATION = "is_liquidation";
  @SerializedName(SERIALIZED_NAME_IS_LIQUIDATION)
  private Boolean isLiquidation;

  public static final String SERIALIZED_NAME_FILLED_AMOUNT = "filled_amount";
  @SerializedName(SERIALIZED_NAME_FILLED_AMOUNT)
  private BigDecimal filledAmount;

  public static final String SERIALIZED_NAME_LABEL = "label";
  @SerializedName(SERIALIZED_NAME_LABEL)
  private String label;

  public static final String SERIALIZED_NAME_COMMISSION = "commission";
  @SerializedName(SERIALIZED_NAME_COMMISSION)
  private BigDecimal commission;

  public static final String SERIALIZED_NAME_AMOUNT = "amount";
  @SerializedName(SERIALIZED_NAME_AMOUNT)
  private BigDecimal amount;

  /**
   * Trigger type (Only for stop orders). Allowed values: &#x60;\&quot;index_price\&quot;&#x60;, &#x60;\&quot;mark_price\&quot;&#x60;, &#x60;\&quot;last_price\&quot;&#x60;.
   */
  @JsonAdapter(TriggerEnum.Adapter.class)
  public enum TriggerEnum {
    INDEX_PRICE("index_price"),
    
    MARK_PRICE("mark_price"),
    
    LAST_PRICE("last_price");

    private String value;

    TriggerEnum(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    public static TriggerEnum fromValue(String value) {
      for (TriggerEnum b : TriggerEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }

    public static class Adapter extends TypeAdapter<TriggerEnum> {
      @Override
      public void write(final JsonWriter jsonWriter, final TriggerEnum enumeration) throws IOException {
        jsonWriter.value(enumeration.getValue());
      }

      @Override
      public TriggerEnum read(final JsonReader jsonReader) throws IOException {
        String value = jsonReader.nextString();
        return TriggerEnum.fromValue(value);
      }
    }
  }

  public static final String SERIALIZED_NAME_TRIGGER = "trigger";
  @SerializedName(SERIALIZED_NAME_TRIGGER)
  private TriggerEnum trigger;

  public static final String SERIALIZED_NAME_INSTRUMENT_NAME = "instrument_name";
  @SerializedName(SERIALIZED_NAME_INSTRUMENT_NAME)
  private String instrumentName;

  public static final String SERIALIZED_NAME_CREATION_TIMESTAMP = "creation_timestamp";
  @SerializedName(SERIALIZED_NAME_CREATION_TIMESTAMP)
  private Integer creationTimestamp;

  public static final String SERIALIZED_NAME_AVERAGE_PRICE = "average_price";
  @SerializedName(SERIALIZED_NAME_AVERAGE_PRICE)
  private BigDecimal averagePrice;

  public Order direction(DirectionEnum direction) {
    this.direction = direction;
    return this;
  }

   /**
   * direction, &#x60;buy&#x60; or &#x60;sell&#x60;
   * @return direction
  **/
  @ApiModelProperty(required = true, value = "direction, `buy` or `sell`")
  public DirectionEnum getDirection() {
    return direction;
  }

  public void setDirection(DirectionEnum direction) {
    this.direction = direction;
  }

  public Order reduceOnly(Boolean reduceOnly) {
    this.reduceOnly = reduceOnly;
    return this;
  }

   /**
   * &#x60;true&#x60; for reduce-only orders only
   * @return reduceOnly
  **/
  @ApiModelProperty(value = "`true` for reduce-only orders only")
  public Boolean getReduceOnly() {
    return reduceOnly;
  }

  public void setReduceOnly(Boolean reduceOnly) {
    this.reduceOnly = reduceOnly;
  }

  public Order triggered(Boolean triggered) {
    this.triggered = triggered;
    return this;
  }

   /**
   * Whether the stop order has been triggered (Only for stop orders)
   * @return triggered
  **/
  @ApiModelProperty(value = "Whether the stop order has been triggered (Only for stop orders)")
  public Boolean getTriggered() {
    return triggered;
  }

  public void setTriggered(Boolean triggered) {
    this.triggered = triggered;
  }

  public Order orderId(String orderId) {
    this.orderId = orderId;
    return this;
  }

   /**
   * Unique order identifier
   * @return orderId
  **/
  @ApiModelProperty(example = "ETH-100234", required = true, value = "Unique order identifier")
  public String getOrderId() {
    return orderId;
  }

  public void setOrderId(String orderId) {
    this.orderId = orderId;
  }

  public Order price(BigDecimal price) {
    this.price = price;
    return this;
  }

   /**
   * Price in base currency
   * @return price
  **/
  @ApiModelProperty(required = true, value = "Price in base currency")
  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public Order timeInForce(TimeInForceEnum timeInForce) {
    this.timeInForce = timeInForce;
    return this;
  }

   /**
   * Order time in force: &#x60;\&quot;good_til_cancelled\&quot;&#x60;, &#x60;\&quot;fill_or_kill\&quot;&#x60;, &#x60;\&quot;immediate_or_cancel\&quot;&#x60;
   * @return timeInForce
  **/
  @ApiModelProperty(required = true, value = "Order time in force: `\"good_til_cancelled\"`, `\"fill_or_kill\"`, `\"immediate_or_cancel\"`")
  public TimeInForceEnum getTimeInForce() {
    return timeInForce;
  }

  public void setTimeInForce(TimeInForceEnum timeInForce) {
    this.timeInForce = timeInForce;
  }

  public Order api(Boolean api) {
    this.api = api;
    return this;
  }

   /**
   * &#x60;true&#x60; if created with API
   * @return api
  **/
  @ApiModelProperty(required = true, value = "`true` if created with API")
  public Boolean getApi() {
    return api;
  }

  public void setApi(Boolean api) {
    this.api = api;
  }

  public Order orderState(OrderStateEnum orderState) {
    this.orderState = orderState;
    return this;
  }

   /**
   * order state, &#x60;\&quot;open\&quot;&#x60;, &#x60;\&quot;filled\&quot;&#x60;, &#x60;\&quot;rejected\&quot;&#x60;, &#x60;\&quot;cancelled\&quot;&#x60;, &#x60;\&quot;untriggered\&quot;&#x60;
   * @return orderState
  **/
  @ApiModelProperty(required = true, value = "order state, `\"open\"`, `\"filled\"`, `\"rejected\"`, `\"cancelled\"`, `\"untriggered\"`")
  public OrderStateEnum getOrderState() {
    return orderState;
  }

  public void setOrderState(OrderStateEnum orderState) {
    this.orderState = orderState;
  }

  public Order implv(BigDecimal implv) {
    this.implv = implv;
    return this;
  }

   /**
   * Implied volatility in percent. (Only if &#x60;advanced&#x3D;\&quot;implv\&quot;&#x60;)
   * @return implv
  **/
  @ApiModelProperty(value = "Implied volatility in percent. (Only if `advanced=\"implv\"`)")
  public BigDecimal getImplv() {
    return implv;
  }

  public void setImplv(BigDecimal implv) {
    this.implv = implv;
  }

  public Order advanced(AdvancedEnum advanced) {
    this.advanced = advanced;
    return this;
  }

   /**
   * advanced type: &#x60;\&quot;usd\&quot;&#x60; or &#x60;\&quot;implv\&quot;&#x60; (Only for options; field is omitted if not applicable). 
   * @return advanced
  **/
  @ApiModelProperty(value = "advanced type: `\"usd\"` or `\"implv\"` (Only for options; field is omitted if not applicable). ")
  public AdvancedEnum getAdvanced() {
    return advanced;
  }

  public void setAdvanced(AdvancedEnum advanced) {
    this.advanced = advanced;
  }

  public Order postOnly(Boolean postOnly) {
    this.postOnly = postOnly;
    return this;
  }

   /**
   * &#x60;true&#x60; for post-only orders only
   * @return postOnly
  **/
  @ApiModelProperty(required = true, value = "`true` for post-only orders only")
  public Boolean getPostOnly() {
    return postOnly;
  }

  public void setPostOnly(Boolean postOnly) {
    this.postOnly = postOnly;
  }

  public Order usd(BigDecimal usd) {
    this.usd = usd;
    return this;
  }

   /**
   * Option price in USD (Only if &#x60;advanced&#x3D;\&quot;usd\&quot;&#x60;)
   * @return usd
  **/
  @ApiModelProperty(value = "Option price in USD (Only if `advanced=\"usd\"`)")
  public BigDecimal getUsd() {
    return usd;
  }

  public void setUsd(BigDecimal usd) {
    this.usd = usd;
  }

  public Order stopPrice(BigDecimal stopPrice) {
    this.stopPrice = stopPrice;
    return this;
  }

   /**
   * stop price (Only for future stop orders)
   * @return stopPrice
  **/
  @ApiModelProperty(value = "stop price (Only for future stop orders)")
  public BigDecimal getStopPrice() {
    return stopPrice;
  }

  public void setStopPrice(BigDecimal stopPrice) {
    this.stopPrice = stopPrice;
  }

  public Order orderType(OrderTypeEnum orderType) {
    this.orderType = orderType;
    return this;
  }

   /**
   * order type, &#x60;\&quot;limit\&quot;&#x60;, &#x60;\&quot;market\&quot;&#x60;, &#x60;\&quot;stop_limit\&quot;&#x60;, &#x60;\&quot;stop_market\&quot;&#x60;
   * @return orderType
  **/
  @ApiModelProperty(required = true, value = "order type, `\"limit\"`, `\"market\"`, `\"stop_limit\"`, `\"stop_market\"`")
  public OrderTypeEnum getOrderType() {
    return orderType;
  }

  public void setOrderType(OrderTypeEnum orderType) {
    this.orderType = orderType;
  }

  public Order lastUpdateTimestamp(Integer lastUpdateTimestamp) {
    this.lastUpdateTimestamp = lastUpdateTimestamp;
    return this;
  }

   /**
   * The timestamp (seconds since the Unix epoch, with millisecond precision)
   * @return lastUpdateTimestamp
  **/
  @ApiModelProperty(example = "1536569522277", required = true, value = "The timestamp (seconds since the Unix epoch, with millisecond precision)")
  public Integer getLastUpdateTimestamp() {
    return lastUpdateTimestamp;
  }

  public void setLastUpdateTimestamp(Integer lastUpdateTimestamp) {
    this.lastUpdateTimestamp = lastUpdateTimestamp;
  }

  public Order originalOrderType(OriginalOrderTypeEnum originalOrderType) {
    this.originalOrderType = originalOrderType;
    return this;
  }

   /**
   * Original order type. Optional field
   * @return originalOrderType
  **/
  @ApiModelProperty(value = "Original order type. Optional field")
  public OriginalOrderTypeEnum getOriginalOrderType() {
    return originalOrderType;
  }

  public void setOriginalOrderType(OriginalOrderTypeEnum originalOrderType) {
    this.originalOrderType = originalOrderType;
  }

  public Order maxShow(BigDecimal maxShow) {
    this.maxShow = maxShow;
    return this;
  }

   /**
   * Maximum amount within an order to be shown to other traders, 0 for invisible order.
   * @return maxShow
  **/
  @ApiModelProperty(required = true, value = "Maximum amount within an order to be shown to other traders, 0 for invisible order.")
  public BigDecimal getMaxShow() {
    return maxShow;
  }

  public void setMaxShow(BigDecimal maxShow) {
    this.maxShow = maxShow;
  }

  public Order profitLoss(BigDecimal profitLoss) {
    this.profitLoss = profitLoss;
    return this;
  }

   /**
   * Profit and loss in base currency.
   * @return profitLoss
  **/
  @ApiModelProperty(value = "Profit and loss in base currency.")
  public BigDecimal getProfitLoss() {
    return profitLoss;
  }

  public void setProfitLoss(BigDecimal profitLoss) {
    this.profitLoss = profitLoss;
  }

  public Order isLiquidation(Boolean isLiquidation) {
    this.isLiquidation = isLiquidation;
    return this;
  }

   /**
   * &#x60;true&#x60; if order was automatically created during liquidation
   * @return isLiquidation
  **/
  @ApiModelProperty(required = true, value = "`true` if order was automatically created during liquidation")
  public Boolean getIsLiquidation() {
    return isLiquidation;
  }

  public void setIsLiquidation(Boolean isLiquidation) {
    this.isLiquidation = isLiquidation;
  }

  public Order filledAmount(BigDecimal filledAmount) {
    this.filledAmount = filledAmount;
    return this;
  }

   /**
   * Filled amount of the order. For perpetual and futures the filled_amount is in USD units, for options - in units or corresponding cryptocurrency contracts, e.g., BTC or ETH.
   * @return filledAmount
  **/
  @ApiModelProperty(value = "Filled amount of the order. For perpetual and futures the filled_amount is in USD units, for options - in units or corresponding cryptocurrency contracts, e.g., BTC or ETH.")
  public BigDecimal getFilledAmount() {
    return filledAmount;
  }

  public void setFilledAmount(BigDecimal filledAmount) {
    this.filledAmount = filledAmount;
  }

  public Order label(String label) {
    this.label = label;
    return this;
  }

   /**
   * user defined label (up to 32 characters)
   * @return label
  **/
  @ApiModelProperty(required = true, value = "user defined label (up to 32 characters)")
  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  public Order commission(BigDecimal commission) {
    this.commission = commission;
    return this;
  }

   /**
   * Commission paid so far (in base currency)
   * @return commission
  **/
  @ApiModelProperty(value = "Commission paid so far (in base currency)")
  public BigDecimal getCommission() {
    return commission;
  }

  public void setCommission(BigDecimal commission) {
    this.commission = commission;
  }

  public Order amount(BigDecimal amount) {
    this.amount = amount;
    return this;
  }

   /**
   * It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH.
   * @return amount
  **/
  @ApiModelProperty(value = "It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH.")
  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  public Order trigger(TriggerEnum trigger) {
    this.trigger = trigger;
    return this;
  }

   /**
   * Trigger type (Only for stop orders). Allowed values: &#x60;\&quot;index_price\&quot;&#x60;, &#x60;\&quot;mark_price\&quot;&#x60;, &#x60;\&quot;last_price\&quot;&#x60;.
   * @return trigger
  **/
  @ApiModelProperty(value = "Trigger type (Only for stop orders). Allowed values: `\"index_price\"`, `\"mark_price\"`, `\"last_price\"`.")
  public TriggerEnum getTrigger() {
    return trigger;
  }

  public void setTrigger(TriggerEnum trigger) {
    this.trigger = trigger;
  }

  public Order instrumentName(String instrumentName) {
    this.instrumentName = instrumentName;
    return this;
  }

   /**
   * Unique instrument identifier
   * @return instrumentName
  **/
  @ApiModelProperty(example = "BTC-PERPETUAL", value = "Unique instrument identifier")
  public String getInstrumentName() {
    return instrumentName;
  }

  public void setInstrumentName(String instrumentName) {
    this.instrumentName = instrumentName;
  }

  public Order creationTimestamp(Integer creationTimestamp) {
    this.creationTimestamp = creationTimestamp;
    return this;
  }

   /**
   * The timestamp (seconds since the Unix epoch, with millisecond precision)
   * @return creationTimestamp
  **/
  @ApiModelProperty(example = "1536569522277", required = true, value = "The timestamp (seconds since the Unix epoch, with millisecond precision)")
  public Integer getCreationTimestamp() {
    return creationTimestamp;
  }

  public void setCreationTimestamp(Integer creationTimestamp) {
    this.creationTimestamp = creationTimestamp;
  }

  public Order averagePrice(BigDecimal averagePrice) {
    this.averagePrice = averagePrice;
    return this;
  }

   /**
   * Average fill price of the order
   * @return averagePrice
  **/
  @ApiModelProperty(value = "Average fill price of the order")
  public BigDecimal getAveragePrice() {
    return averagePrice;
  }

  public void setAveragePrice(BigDecimal averagePrice) {
    this.averagePrice = averagePrice;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Order order = (Order) o;
    return Objects.equals(this.direction, order.direction) &&
        Objects.equals(this.reduceOnly, order.reduceOnly) &&
        Objects.equals(this.triggered, order.triggered) &&
        Objects.equals(this.orderId, order.orderId) &&
        Objects.equals(this.price, order.price) &&
        Objects.equals(this.timeInForce, order.timeInForce) &&
        Objects.equals(this.api, order.api) &&
        Objects.equals(this.orderState, order.orderState) &&
        Objects.equals(this.implv, order.implv) &&
        Objects.equals(this.advanced, order.advanced) &&
        Objects.equals(this.postOnly, order.postOnly) &&
        Objects.equals(this.usd, order.usd) &&
        Objects.equals(this.stopPrice, order.stopPrice) &&
        Objects.equals(this.orderType, order.orderType) &&
        Objects.equals(this.lastUpdateTimestamp, order.lastUpdateTimestamp) &&
        Objects.equals(this.originalOrderType, order.originalOrderType) &&
        Objects.equals(this.maxShow, order.maxShow) &&
        Objects.equals(this.profitLoss, order.profitLoss) &&
        Objects.equals(this.isLiquidation, order.isLiquidation) &&
        Objects.equals(this.filledAmount, order.filledAmount) &&
        Objects.equals(this.label, order.label) &&
        Objects.equals(this.commission, order.commission) &&
        Objects.equals(this.amount, order.amount) &&
        Objects.equals(this.trigger, order.trigger) &&
        Objects.equals(this.instrumentName, order.instrumentName) &&
        Objects.equals(this.creationTimestamp, order.creationTimestamp) &&
        Objects.equals(this.averagePrice, order.averagePrice);
  }

  @Override
  public int hashCode() {
    return Objects.hash(direction, reduceOnly, triggered, orderId, price, timeInForce, api, orderState, implv, advanced, postOnly, usd, stopPrice, orderType, lastUpdateTimestamp, originalOrderType, maxShow, profitLoss, isLiquidation, filledAmount, label, commission, amount, trigger, instrumentName, creationTimestamp, averagePrice);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Order {\n");
    sb.append("    direction: ").append(toIndentedString(direction)).append("\n");
    sb.append("    reduceOnly: ").append(toIndentedString(reduceOnly)).append("\n");
    sb.append("    triggered: ").append(toIndentedString(triggered)).append("\n");
    sb.append("    orderId: ").append(toIndentedString(orderId)).append("\n");
    sb.append("    price: ").append(toIndentedString(price)).append("\n");
    sb.append("    timeInForce: ").append(toIndentedString(timeInForce)).append("\n");
    sb.append("    api: ").append(toIndentedString(api)).append("\n");
    sb.append("    orderState: ").append(toIndentedString(orderState)).append("\n");
    sb.append("    implv: ").append(toIndentedString(implv)).append("\n");
    sb.append("    advanced: ").append(toIndentedString(advanced)).append("\n");
    sb.append("    postOnly: ").append(toIndentedString(postOnly)).append("\n");
    sb.append("    usd: ").append(toIndentedString(usd)).append("\n");
    sb.append("    stopPrice: ").append(toIndentedString(stopPrice)).append("\n");
    sb.append("    orderType: ").append(toIndentedString(orderType)).append("\n");
    sb.append("    lastUpdateTimestamp: ").append(toIndentedString(lastUpdateTimestamp)).append("\n");
    sb.append("    originalOrderType: ").append(toIndentedString(originalOrderType)).append("\n");
    sb.append("    maxShow: ").append(toIndentedString(maxShow)).append("\n");
    sb.append("    profitLoss: ").append(toIndentedString(profitLoss)).append("\n");
    sb.append("    isLiquidation: ").append(toIndentedString(isLiquidation)).append("\n");
    sb.append("    filledAmount: ").append(toIndentedString(filledAmount)).append("\n");
    sb.append("    label: ").append(toIndentedString(label)).append("\n");
    sb.append("    commission: ").append(toIndentedString(commission)).append("\n");
    sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
    sb.append("    trigger: ").append(toIndentedString(trigger)).append("\n");
    sb.append("    instrumentName: ").append(toIndentedString(instrumentName)).append("\n");
    sb.append("    creationTimestamp: ").append(toIndentedString(creationTimestamp)).append("\n");
    sb.append("    averagePrice: ").append(toIndentedString(averagePrice)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}

