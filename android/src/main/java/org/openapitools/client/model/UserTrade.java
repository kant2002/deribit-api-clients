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
public class UserTrade {
  
  public enum DirectionEnum {
     buy,  sell, 
  };
  @SerializedName("direction")
  private DirectionEnum direction = null;
  public enum FeeCurrencyEnum {
     BTC,  ETH, 
  };
  @SerializedName("fee_currency")
  private FeeCurrencyEnum feeCurrency = null;
  @SerializedName("order_id")
  private String orderId = null;
  @SerializedName("timestamp")
  private Integer timestamp = null;
  @SerializedName("price")
  private BigDecimal price = null;
  @SerializedName("iv")
  private BigDecimal iv = null;
  @SerializedName("trade_id")
  private String tradeId = null;
  @SerializedName("fee")
  private BigDecimal fee = null;
  public enum OrderTypeEnum {
     limit,  market,  liquidation, 
  };
  @SerializedName("order_type")
  private OrderTypeEnum orderType = null;
  @SerializedName("trade_seq")
  private Integer tradeSeq = null;
  @SerializedName("self_trade")
  private Boolean selfTrade = null;
  public enum StateEnum {
     open,  filled,  rejected,  cancelled,  untriggered,  archive, 
  };
  @SerializedName("state")
  private StateEnum state = null;
  @SerializedName("label")
  private String label = null;
  @SerializedName("index_price")
  private BigDecimal indexPrice = null;
  @SerializedName("amount")
  private BigDecimal amount = null;
  @SerializedName("instrument_name")
  private String instrumentName = null;
  public enum TickDirectionEnum {
     0,  1,  2,  3, 
  };
  @SerializedName("tick_direction")
  private TickDirectionEnum tickDirection = null;
  @SerializedName("matching_id")
  private String matchingId = null;
  public enum LiquidityEnum {
     M,  T, 
  };
  @SerializedName("liquidity")
  private LiquidityEnum liquidity = null;

  /**
   * Trade direction of the taker
   **/
  @ApiModelProperty(required = true, value = "Trade direction of the taker")
  public DirectionEnum getDirection() {
    return direction;
  }
  public void setDirection(DirectionEnum direction) {
    this.direction = direction;
  }

  /**
   * Currency, i.e `\"BTC\"`, `\"ETH\"`
   **/
  @ApiModelProperty(required = true, value = "Currency, i.e `\"BTC\"`, `\"ETH\"`")
  public FeeCurrencyEnum getFeeCurrency() {
    return feeCurrency;
  }
  public void setFeeCurrency(FeeCurrencyEnum feeCurrency) {
    this.feeCurrency = feeCurrency;
  }

  /**
   * Id of the user order (maker or taker), i.e. subscriber's order id that took part in the trade
   **/
  @ApiModelProperty(required = true, value = "Id of the user order (maker or taker), i.e. subscriber's order id that took part in the trade")
  public String getOrderId() {
    return orderId;
  }
  public void setOrderId(String orderId) {
    this.orderId = orderId;
  }

  /**
   * The timestamp of the trade
   **/
  @ApiModelProperty(required = true, value = "The timestamp of the trade")
  public Integer getTimestamp() {
    return timestamp;
  }
  public void setTimestamp(Integer timestamp) {
    this.timestamp = timestamp;
  }

  /**
   * The price of the trade
   **/
  @ApiModelProperty(required = true, value = "The price of the trade")
  public BigDecimal getPrice() {
    return price;
  }
  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  /**
   * Option implied volatility for the price (Option only)
   **/
  @ApiModelProperty(value = "Option implied volatility for the price (Option only)")
  public BigDecimal getIv() {
    return iv;
  }
  public void setIv(BigDecimal iv) {
    this.iv = iv;
  }

  /**
   * Unique (per currency) trade identifier
   **/
  @ApiModelProperty(required = true, value = "Unique (per currency) trade identifier")
  public String getTradeId() {
    return tradeId;
  }
  public void setTradeId(String tradeId) {
    this.tradeId = tradeId;
  }

  /**
   * User's fee in units of the specified `fee_currency`
   **/
  @ApiModelProperty(required = true, value = "User's fee in units of the specified `fee_currency`")
  public BigDecimal getFee() {
    return fee;
  }
  public void setFee(BigDecimal fee) {
    this.fee = fee;
  }

  /**
   * Order type: `\"limit`, `\"market\"`, or `\"liquidation\"`
   **/
  @ApiModelProperty(value = "Order type: `\"limit`, `\"market\"`, or `\"liquidation\"`")
  public OrderTypeEnum getOrderType() {
    return orderType;
  }
  public void setOrderType(OrderTypeEnum orderType) {
    this.orderType = orderType;
  }

  /**
   * The sequence number of the trade within instrument
   **/
  @ApiModelProperty(required = true, value = "The sequence number of the trade within instrument")
  public Integer getTradeSeq() {
    return tradeSeq;
  }
  public void setTradeSeq(Integer tradeSeq) {
    this.tradeSeq = tradeSeq;
  }

  /**
   * `true` if the trade is against own order. This can only happen when your account has self-trading enabled. Contact an administrator if you think you need that
   **/
  @ApiModelProperty(required = true, value = "`true` if the trade is against own order. This can only happen when your account has self-trading enabled. Contact an administrator if you think you need that")
  public Boolean getSelfTrade() {
    return selfTrade;
  }
  public void setSelfTrade(Boolean selfTrade) {
    this.selfTrade = selfTrade;
  }

  /**
   * order state, `\"open\"`, `\"filled\"`, `\"rejected\"`, `\"cancelled\"`, `\"untriggered\"` or `\"archive\"` (if order was archived)
   **/
  @ApiModelProperty(required = true, value = "order state, `\"open\"`, `\"filled\"`, `\"rejected\"`, `\"cancelled\"`, `\"untriggered\"` or `\"archive\"` (if order was archived)")
  public StateEnum getState() {
    return state;
  }
  public void setState(StateEnum state) {
    this.state = state;
  }

  /**
   * User defined label (presented only when previously set for order by user)
   **/
  @ApiModelProperty(value = "User defined label (presented only when previously set for order by user)")
  public String getLabel() {
    return label;
  }
  public void setLabel(String label) {
    this.label = label;
  }

  /**
   * Index Price at the moment of trade
   **/
  @ApiModelProperty(required = true, value = "Index Price at the moment of trade")
  public BigDecimal getIndexPrice() {
    return indexPrice;
  }
  public void setIndexPrice(BigDecimal indexPrice) {
    this.indexPrice = indexPrice;
  }

  /**
   * Trade amount. For perpetual and futures - in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH.
   **/
  @ApiModelProperty(required = true, value = "Trade amount. For perpetual and futures - in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH.")
  public BigDecimal getAmount() {
    return amount;
  }
  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  /**
   * Unique instrument identifier
   **/
  @ApiModelProperty(required = true, value = "Unique instrument identifier")
  public String getInstrumentName() {
    return instrumentName;
  }
  public void setInstrumentName(String instrumentName) {
    this.instrumentName = instrumentName;
  }

  /**
   * Direction of the \"tick\" (`0` = Plus Tick, `1` = Zero-Plus Tick, `2` = Minus Tick, `3` = Zero-Minus Tick).
   **/
  @ApiModelProperty(required = true, value = "Direction of the \"tick\" (`0` = Plus Tick, `1` = Zero-Plus Tick, `2` = Minus Tick, `3` = Zero-Minus Tick).")
  public TickDirectionEnum getTickDirection() {
    return tickDirection;
  }
  public void setTickDirection(TickDirectionEnum tickDirection) {
    this.tickDirection = tickDirection;
  }

  /**
   * Always `null`, except for a self-trade which is possible only if self-trading is switched on for the account (in that case this will be id of the maker order of the subscriber)
   **/
  @ApiModelProperty(required = true, value = "Always `null`, except for a self-trade which is possible only if self-trading is switched on for the account (in that case this will be id of the maker order of the subscriber)")
  public String getMatchingId() {
    return matchingId;
  }
  public void setMatchingId(String matchingId) {
    this.matchingId = matchingId;
  }

  /**
   * Describes what was role of users order: `\"M\"` when it was maker order, `\"T\"` when it was taker order
   **/
  @ApiModelProperty(value = "Describes what was role of users order: `\"M\"` when it was maker order, `\"T\"` when it was taker order")
  public LiquidityEnum getLiquidity() {
    return liquidity;
  }
  public void setLiquidity(LiquidityEnum liquidity) {
    this.liquidity = liquidity;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserTrade userTrade = (UserTrade) o;
    return (this.direction == null ? userTrade.direction == null : this.direction.equals(userTrade.direction)) &&
        (this.feeCurrency == null ? userTrade.feeCurrency == null : this.feeCurrency.equals(userTrade.feeCurrency)) &&
        (this.orderId == null ? userTrade.orderId == null : this.orderId.equals(userTrade.orderId)) &&
        (this.timestamp == null ? userTrade.timestamp == null : this.timestamp.equals(userTrade.timestamp)) &&
        (this.price == null ? userTrade.price == null : this.price.equals(userTrade.price)) &&
        (this.iv == null ? userTrade.iv == null : this.iv.equals(userTrade.iv)) &&
        (this.tradeId == null ? userTrade.tradeId == null : this.tradeId.equals(userTrade.tradeId)) &&
        (this.fee == null ? userTrade.fee == null : this.fee.equals(userTrade.fee)) &&
        (this.orderType == null ? userTrade.orderType == null : this.orderType.equals(userTrade.orderType)) &&
        (this.tradeSeq == null ? userTrade.tradeSeq == null : this.tradeSeq.equals(userTrade.tradeSeq)) &&
        (this.selfTrade == null ? userTrade.selfTrade == null : this.selfTrade.equals(userTrade.selfTrade)) &&
        (this.state == null ? userTrade.state == null : this.state.equals(userTrade.state)) &&
        (this.label == null ? userTrade.label == null : this.label.equals(userTrade.label)) &&
        (this.indexPrice == null ? userTrade.indexPrice == null : this.indexPrice.equals(userTrade.indexPrice)) &&
        (this.amount == null ? userTrade.amount == null : this.amount.equals(userTrade.amount)) &&
        (this.instrumentName == null ? userTrade.instrumentName == null : this.instrumentName.equals(userTrade.instrumentName)) &&
        (this.tickDirection == null ? userTrade.tickDirection == null : this.tickDirection.equals(userTrade.tickDirection)) &&
        (this.matchingId == null ? userTrade.matchingId == null : this.matchingId.equals(userTrade.matchingId)) &&
        (this.liquidity == null ? userTrade.liquidity == null : this.liquidity.equals(userTrade.liquidity));
  }

  @Override
  public int hashCode() {
    int result = 17;
    result = 31 * result + (this.direction == null ? 0: this.direction.hashCode());
    result = 31 * result + (this.feeCurrency == null ? 0: this.feeCurrency.hashCode());
    result = 31 * result + (this.orderId == null ? 0: this.orderId.hashCode());
    result = 31 * result + (this.timestamp == null ? 0: this.timestamp.hashCode());
    result = 31 * result + (this.price == null ? 0: this.price.hashCode());
    result = 31 * result + (this.iv == null ? 0: this.iv.hashCode());
    result = 31 * result + (this.tradeId == null ? 0: this.tradeId.hashCode());
    result = 31 * result + (this.fee == null ? 0: this.fee.hashCode());
    result = 31 * result + (this.orderType == null ? 0: this.orderType.hashCode());
    result = 31 * result + (this.tradeSeq == null ? 0: this.tradeSeq.hashCode());
    result = 31 * result + (this.selfTrade == null ? 0: this.selfTrade.hashCode());
    result = 31 * result + (this.state == null ? 0: this.state.hashCode());
    result = 31 * result + (this.label == null ? 0: this.label.hashCode());
    result = 31 * result + (this.indexPrice == null ? 0: this.indexPrice.hashCode());
    result = 31 * result + (this.amount == null ? 0: this.amount.hashCode());
    result = 31 * result + (this.instrumentName == null ? 0: this.instrumentName.hashCode());
    result = 31 * result + (this.tickDirection == null ? 0: this.tickDirection.hashCode());
    result = 31 * result + (this.matchingId == null ? 0: this.matchingId.hashCode());
    result = 31 * result + (this.liquidity == null ? 0: this.liquidity.hashCode());
    return result;
  }

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class UserTrade {\n");
    
    sb.append("  direction: ").append(direction).append("\n");
    sb.append("  feeCurrency: ").append(feeCurrency).append("\n");
    sb.append("  orderId: ").append(orderId).append("\n");
    sb.append("  timestamp: ").append(timestamp).append("\n");
    sb.append("  price: ").append(price).append("\n");
    sb.append("  iv: ").append(iv).append("\n");
    sb.append("  tradeId: ").append(tradeId).append("\n");
    sb.append("  fee: ").append(fee).append("\n");
    sb.append("  orderType: ").append(orderType).append("\n");
    sb.append("  tradeSeq: ").append(tradeSeq).append("\n");
    sb.append("  selfTrade: ").append(selfTrade).append("\n");
    sb.append("  state: ").append(state).append("\n");
    sb.append("  label: ").append(label).append("\n");
    sb.append("  indexPrice: ").append(indexPrice).append("\n");
    sb.append("  amount: ").append(amount).append("\n");
    sb.append("  instrumentName: ").append(instrumentName).append("\n");
    sb.append("  tickDirection: ").append(tickDirection).append("\n");
    sb.append("  matchingId: ").append(matchingId).append("\n");
    sb.append("  liquidity: ").append(liquidity).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
