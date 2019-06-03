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

package org.openapitools.client.model;

import java.math.BigDecimal;
import io.swagger.annotations.*;
import com.google.gson.annotations.SerializedName;

@ApiModel(description = "")
public class Order {
  
  public enum DirectionEnum {
     buy,  sell, 
  };
  @SerializedName("direction")
  private DirectionEnum direction = null;
  @SerializedName("reduce_only")
  private Boolean reduceOnly = null;
  @SerializedName("triggered")
  private Boolean triggered = null;
  @SerializedName("order_id")
  private String orderId = null;
  @SerializedName("price")
  private BigDecimal price = null;
  public enum TimeInForceEnum {
     good_til_cancelled,  fill_or_kill,  immediate_or_cancel, 
  };
  @SerializedName("time_in_force")
  private TimeInForceEnum timeInForce = null;
  @SerializedName("api")
  private Boolean api = null;
  public enum OrderStateEnum {
     open,  filled,  rejected,  cancelled,  untriggered,  triggered, 
  };
  @SerializedName("order_state")
  private OrderStateEnum orderState = null;
  @SerializedName("implv")
  private BigDecimal implv = null;
  public enum AdvancedEnum {
     usd,  implv, 
  };
  @SerializedName("advanced")
  private AdvancedEnum advanced = null;
  @SerializedName("post_only")
  private Boolean postOnly = null;
  @SerializedName("usd")
  private BigDecimal usd = null;
  @SerializedName("stop_price")
  private BigDecimal stopPrice = null;
  public enum OrderTypeEnum {
     market,  limit,  stop_market,  stop_limit, 
  };
  @SerializedName("order_type")
  private OrderTypeEnum orderType = null;
  @SerializedName("last_update_timestamp")
  private Integer lastUpdateTimestamp = null;
  public enum OriginalOrderTypeEnum {
     market, 
  };
  @SerializedName("original_order_type")
  private OriginalOrderTypeEnum originalOrderType = null;
  @SerializedName("max_show")
  private BigDecimal maxShow = null;
  @SerializedName("profit_loss")
  private BigDecimal profitLoss = null;
  @SerializedName("is_liquidation")
  private Boolean isLiquidation = null;
  @SerializedName("filled_amount")
  private BigDecimal filledAmount = null;
  @SerializedName("label")
  private String label = null;
  @SerializedName("commission")
  private BigDecimal commission = null;
  @SerializedName("amount")
  private BigDecimal amount = null;
  public enum TriggerEnum {
     index_price,  mark_price,  last_price, 
  };
  @SerializedName("trigger")
  private TriggerEnum trigger = null;
  @SerializedName("instrument_name")
  private String instrumentName = null;
  @SerializedName("creation_timestamp")
  private Integer creationTimestamp = null;
  @SerializedName("average_price")
  private BigDecimal averagePrice = null;

  /**
   * direction, `buy` or `sell`
   **/
  @ApiModelProperty(required = true, value = "direction, `buy` or `sell`")
  public DirectionEnum getDirection() {
    return direction;
  }
  public void setDirection(DirectionEnum direction) {
    this.direction = direction;
  }

  /**
   * `true` for reduce-only orders only
   **/
  @ApiModelProperty(value = "`true` for reduce-only orders only")
  public Boolean getReduceOnly() {
    return reduceOnly;
  }
  public void setReduceOnly(Boolean reduceOnly) {
    this.reduceOnly = reduceOnly;
  }

  /**
   * Whether the stop order has been triggered (Only for stop orders)
   **/
  @ApiModelProperty(value = "Whether the stop order has been triggered (Only for stop orders)")
  public Boolean getTriggered() {
    return triggered;
  }
  public void setTriggered(Boolean triggered) {
    this.triggered = triggered;
  }

  /**
   * Unique order identifier
   **/
  @ApiModelProperty(required = true, value = "Unique order identifier")
  public String getOrderId() {
    return orderId;
  }
  public void setOrderId(String orderId) {
    this.orderId = orderId;
  }

  /**
   * Price in base currency
   **/
  @ApiModelProperty(required = true, value = "Price in base currency")
  public BigDecimal getPrice() {
    return price;
  }
  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  /**
   * Order time in force: `\"good_til_cancelled\"`, `\"fill_or_kill\"`, `\"immediate_or_cancel\"`
   **/
  @ApiModelProperty(required = true, value = "Order time in force: `\"good_til_cancelled\"`, `\"fill_or_kill\"`, `\"immediate_or_cancel\"`")
  public TimeInForceEnum getTimeInForce() {
    return timeInForce;
  }
  public void setTimeInForce(TimeInForceEnum timeInForce) {
    this.timeInForce = timeInForce;
  }

  /**
   * `true` if created with API
   **/
  @ApiModelProperty(required = true, value = "`true` if created with API")
  public Boolean getApi() {
    return api;
  }
  public void setApi(Boolean api) {
    this.api = api;
  }

  /**
   * order state, `\"open\"`, `\"filled\"`, `\"rejected\"`, `\"cancelled\"`, `\"untriggered\"`
   **/
  @ApiModelProperty(required = true, value = "order state, `\"open\"`, `\"filled\"`, `\"rejected\"`, `\"cancelled\"`, `\"untriggered\"`")
  public OrderStateEnum getOrderState() {
    return orderState;
  }
  public void setOrderState(OrderStateEnum orderState) {
    this.orderState = orderState;
  }

  /**
   * Implied volatility in percent. (Only if `advanced=\"implv\"`)
   **/
  @ApiModelProperty(value = "Implied volatility in percent. (Only if `advanced=\"implv\"`)")
  public BigDecimal getImplv() {
    return implv;
  }
  public void setImplv(BigDecimal implv) {
    this.implv = implv;
  }

  /**
   * advanced type: `\"usd\"` or `\"implv\"` (Only for options; field is omitted if not applicable). 
   **/
  @ApiModelProperty(value = "advanced type: `\"usd\"` or `\"implv\"` (Only for options; field is omitted if not applicable). ")
  public AdvancedEnum getAdvanced() {
    return advanced;
  }
  public void setAdvanced(AdvancedEnum advanced) {
    this.advanced = advanced;
  }

  /**
   * `true` for post-only orders only
   **/
  @ApiModelProperty(required = true, value = "`true` for post-only orders only")
  public Boolean getPostOnly() {
    return postOnly;
  }
  public void setPostOnly(Boolean postOnly) {
    this.postOnly = postOnly;
  }

  /**
   * Option price in USD (Only if `advanced=\"usd\"`)
   **/
  @ApiModelProperty(value = "Option price in USD (Only if `advanced=\"usd\"`)")
  public BigDecimal getUsd() {
    return usd;
  }
  public void setUsd(BigDecimal usd) {
    this.usd = usd;
  }

  /**
   * stop price (Only for future stop orders)
   **/
  @ApiModelProperty(value = "stop price (Only for future stop orders)")
  public BigDecimal getStopPrice() {
    return stopPrice;
  }
  public void setStopPrice(BigDecimal stopPrice) {
    this.stopPrice = stopPrice;
  }

  /**
   * order type, `\"limit\"`, `\"market\"`, `\"stop_limit\"`, `\"stop_market\"`
   **/
  @ApiModelProperty(required = true, value = "order type, `\"limit\"`, `\"market\"`, `\"stop_limit\"`, `\"stop_market\"`")
  public OrderTypeEnum getOrderType() {
    return orderType;
  }
  public void setOrderType(OrderTypeEnum orderType) {
    this.orderType = orderType;
  }

  /**
   * The timestamp (seconds since the Unix epoch, with millisecond precision)
   **/
  @ApiModelProperty(required = true, value = "The timestamp (seconds since the Unix epoch, with millisecond precision)")
  public Integer getLastUpdateTimestamp() {
    return lastUpdateTimestamp;
  }
  public void setLastUpdateTimestamp(Integer lastUpdateTimestamp) {
    this.lastUpdateTimestamp = lastUpdateTimestamp;
  }

  /**
   * Original order type. Optional field
   **/
  @ApiModelProperty(value = "Original order type. Optional field")
  public OriginalOrderTypeEnum getOriginalOrderType() {
    return originalOrderType;
  }
  public void setOriginalOrderType(OriginalOrderTypeEnum originalOrderType) {
    this.originalOrderType = originalOrderType;
  }

  /**
   * Maximum amount within an order to be shown to other traders, 0 for invisible order.
   **/
  @ApiModelProperty(required = true, value = "Maximum amount within an order to be shown to other traders, 0 for invisible order.")
  public BigDecimal getMaxShow() {
    return maxShow;
  }
  public void setMaxShow(BigDecimal maxShow) {
    this.maxShow = maxShow;
  }

  /**
   * Profit and loss in base currency.
   **/
  @ApiModelProperty(value = "Profit and loss in base currency.")
  public BigDecimal getProfitLoss() {
    return profitLoss;
  }
  public void setProfitLoss(BigDecimal profitLoss) {
    this.profitLoss = profitLoss;
  }

  /**
   * `true` if order was automatically created during liquidation
   **/
  @ApiModelProperty(required = true, value = "`true` if order was automatically created during liquidation")
  public Boolean getIsLiquidation() {
    return isLiquidation;
  }
  public void setIsLiquidation(Boolean isLiquidation) {
    this.isLiquidation = isLiquidation;
  }

  /**
   * Filled amount of the order. For perpetual and futures the filled_amount is in USD units, for options - in units or corresponding cryptocurrency contracts, e.g., BTC or ETH.
   **/
  @ApiModelProperty(value = "Filled amount of the order. For perpetual and futures the filled_amount is in USD units, for options - in units or corresponding cryptocurrency contracts, e.g., BTC or ETH.")
  public BigDecimal getFilledAmount() {
    return filledAmount;
  }
  public void setFilledAmount(BigDecimal filledAmount) {
    this.filledAmount = filledAmount;
  }

  /**
   * user defined label (up to 32 characters)
   **/
  @ApiModelProperty(required = true, value = "user defined label (up to 32 characters)")
  public String getLabel() {
    return label;
  }
  public void setLabel(String label) {
    this.label = label;
  }

  /**
   * Commission paid so far (in base currency)
   **/
  @ApiModelProperty(value = "Commission paid so far (in base currency)")
  public BigDecimal getCommission() {
    return commission;
  }
  public void setCommission(BigDecimal commission) {
    this.commission = commission;
  }

  /**
   * It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH.
   **/
  @ApiModelProperty(value = "It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH.")
  public BigDecimal getAmount() {
    return amount;
  }
  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  /**
   * Trigger type (Only for stop orders). Allowed values: `\"index_price\"`, `\"mark_price\"`, `\"last_price\"`.
   **/
  @ApiModelProperty(value = "Trigger type (Only for stop orders). Allowed values: `\"index_price\"`, `\"mark_price\"`, `\"last_price\"`.")
  public TriggerEnum getTrigger() {
    return trigger;
  }
  public void setTrigger(TriggerEnum trigger) {
    this.trigger = trigger;
  }

  /**
   * Unique instrument identifier
   **/
  @ApiModelProperty(value = "Unique instrument identifier")
  public String getInstrumentName() {
    return instrumentName;
  }
  public void setInstrumentName(String instrumentName) {
    this.instrumentName = instrumentName;
  }

  /**
   * The timestamp (seconds since the Unix epoch, with millisecond precision)
   **/
  @ApiModelProperty(required = true, value = "The timestamp (seconds since the Unix epoch, with millisecond precision)")
  public Integer getCreationTimestamp() {
    return creationTimestamp;
  }
  public void setCreationTimestamp(Integer creationTimestamp) {
    this.creationTimestamp = creationTimestamp;
  }

  /**
   * Average fill price of the order
   **/
  @ApiModelProperty(value = "Average fill price of the order")
  public BigDecimal getAveragePrice() {
    return averagePrice;
  }
  public void setAveragePrice(BigDecimal averagePrice) {
    this.averagePrice = averagePrice;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Order order = (Order) o;
    return (this.direction == null ? order.direction == null : this.direction.equals(order.direction)) &&
        (this.reduceOnly == null ? order.reduceOnly == null : this.reduceOnly.equals(order.reduceOnly)) &&
        (this.triggered == null ? order.triggered == null : this.triggered.equals(order.triggered)) &&
        (this.orderId == null ? order.orderId == null : this.orderId.equals(order.orderId)) &&
        (this.price == null ? order.price == null : this.price.equals(order.price)) &&
        (this.timeInForce == null ? order.timeInForce == null : this.timeInForce.equals(order.timeInForce)) &&
        (this.api == null ? order.api == null : this.api.equals(order.api)) &&
        (this.orderState == null ? order.orderState == null : this.orderState.equals(order.orderState)) &&
        (this.implv == null ? order.implv == null : this.implv.equals(order.implv)) &&
        (this.advanced == null ? order.advanced == null : this.advanced.equals(order.advanced)) &&
        (this.postOnly == null ? order.postOnly == null : this.postOnly.equals(order.postOnly)) &&
        (this.usd == null ? order.usd == null : this.usd.equals(order.usd)) &&
        (this.stopPrice == null ? order.stopPrice == null : this.stopPrice.equals(order.stopPrice)) &&
        (this.orderType == null ? order.orderType == null : this.orderType.equals(order.orderType)) &&
        (this.lastUpdateTimestamp == null ? order.lastUpdateTimestamp == null : this.lastUpdateTimestamp.equals(order.lastUpdateTimestamp)) &&
        (this.originalOrderType == null ? order.originalOrderType == null : this.originalOrderType.equals(order.originalOrderType)) &&
        (this.maxShow == null ? order.maxShow == null : this.maxShow.equals(order.maxShow)) &&
        (this.profitLoss == null ? order.profitLoss == null : this.profitLoss.equals(order.profitLoss)) &&
        (this.isLiquidation == null ? order.isLiquidation == null : this.isLiquidation.equals(order.isLiquidation)) &&
        (this.filledAmount == null ? order.filledAmount == null : this.filledAmount.equals(order.filledAmount)) &&
        (this.label == null ? order.label == null : this.label.equals(order.label)) &&
        (this.commission == null ? order.commission == null : this.commission.equals(order.commission)) &&
        (this.amount == null ? order.amount == null : this.amount.equals(order.amount)) &&
        (this.trigger == null ? order.trigger == null : this.trigger.equals(order.trigger)) &&
        (this.instrumentName == null ? order.instrumentName == null : this.instrumentName.equals(order.instrumentName)) &&
        (this.creationTimestamp == null ? order.creationTimestamp == null : this.creationTimestamp.equals(order.creationTimestamp)) &&
        (this.averagePrice == null ? order.averagePrice == null : this.averagePrice.equals(order.averagePrice));
  }

  @Override
  public int hashCode() {
    int result = 17;
    result = 31 * result + (this.direction == null ? 0: this.direction.hashCode());
    result = 31 * result + (this.reduceOnly == null ? 0: this.reduceOnly.hashCode());
    result = 31 * result + (this.triggered == null ? 0: this.triggered.hashCode());
    result = 31 * result + (this.orderId == null ? 0: this.orderId.hashCode());
    result = 31 * result + (this.price == null ? 0: this.price.hashCode());
    result = 31 * result + (this.timeInForce == null ? 0: this.timeInForce.hashCode());
    result = 31 * result + (this.api == null ? 0: this.api.hashCode());
    result = 31 * result + (this.orderState == null ? 0: this.orderState.hashCode());
    result = 31 * result + (this.implv == null ? 0: this.implv.hashCode());
    result = 31 * result + (this.advanced == null ? 0: this.advanced.hashCode());
    result = 31 * result + (this.postOnly == null ? 0: this.postOnly.hashCode());
    result = 31 * result + (this.usd == null ? 0: this.usd.hashCode());
    result = 31 * result + (this.stopPrice == null ? 0: this.stopPrice.hashCode());
    result = 31 * result + (this.orderType == null ? 0: this.orderType.hashCode());
    result = 31 * result + (this.lastUpdateTimestamp == null ? 0: this.lastUpdateTimestamp.hashCode());
    result = 31 * result + (this.originalOrderType == null ? 0: this.originalOrderType.hashCode());
    result = 31 * result + (this.maxShow == null ? 0: this.maxShow.hashCode());
    result = 31 * result + (this.profitLoss == null ? 0: this.profitLoss.hashCode());
    result = 31 * result + (this.isLiquidation == null ? 0: this.isLiquidation.hashCode());
    result = 31 * result + (this.filledAmount == null ? 0: this.filledAmount.hashCode());
    result = 31 * result + (this.label == null ? 0: this.label.hashCode());
    result = 31 * result + (this.commission == null ? 0: this.commission.hashCode());
    result = 31 * result + (this.amount == null ? 0: this.amount.hashCode());
    result = 31 * result + (this.trigger == null ? 0: this.trigger.hashCode());
    result = 31 * result + (this.instrumentName == null ? 0: this.instrumentName.hashCode());
    result = 31 * result + (this.creationTimestamp == null ? 0: this.creationTimestamp.hashCode());
    result = 31 * result + (this.averagePrice == null ? 0: this.averagePrice.hashCode());
    return result;
  }

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class Order {\n");
    
    sb.append("  direction: ").append(direction).append("\n");
    sb.append("  reduceOnly: ").append(reduceOnly).append("\n");
    sb.append("  triggered: ").append(triggered).append("\n");
    sb.append("  orderId: ").append(orderId).append("\n");
    sb.append("  price: ").append(price).append("\n");
    sb.append("  timeInForce: ").append(timeInForce).append("\n");
    sb.append("  api: ").append(api).append("\n");
    sb.append("  orderState: ").append(orderState).append("\n");
    sb.append("  implv: ").append(implv).append("\n");
    sb.append("  advanced: ").append(advanced).append("\n");
    sb.append("  postOnly: ").append(postOnly).append("\n");
    sb.append("  usd: ").append(usd).append("\n");
    sb.append("  stopPrice: ").append(stopPrice).append("\n");
    sb.append("  orderType: ").append(orderType).append("\n");
    sb.append("  lastUpdateTimestamp: ").append(lastUpdateTimestamp).append("\n");
    sb.append("  originalOrderType: ").append(originalOrderType).append("\n");
    sb.append("  maxShow: ").append(maxShow).append("\n");
    sb.append("  profitLoss: ").append(profitLoss).append("\n");
    sb.append("  isLiquidation: ").append(isLiquidation).append("\n");
    sb.append("  filledAmount: ").append(filledAmount).append("\n");
    sb.append("  label: ").append(label).append("\n");
    sb.append("  commission: ").append(commission).append("\n");
    sb.append("  amount: ").append(amount).append("\n");
    sb.append("  trigger: ").append(trigger).append("\n");
    sb.append("  instrumentName: ").append(instrumentName).append("\n");
    sb.append("  creationTimestamp: ").append(creationTimestamp).append("\n");
    sb.append("  averagePrice: ").append(averagePrice).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
