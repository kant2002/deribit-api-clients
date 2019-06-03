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
public class Position {
  
  public enum DirectionEnum {
     buy,  sell, 
  };
  @SerializedName("direction")
  private DirectionEnum direction = null;
  @SerializedName("average_price_usd")
  private BigDecimal averagePriceUsd = null;
  @SerializedName("estimated_liquidation_price")
  private BigDecimal estimatedLiquidationPrice = null;
  @SerializedName("floating_profit_loss")
  private BigDecimal floatingProfitLoss = null;
  @SerializedName("floating_profit_loss_usd")
  private BigDecimal floatingProfitLossUsd = null;
  @SerializedName("open_orders_margin")
  private BigDecimal openOrdersMargin = null;
  @SerializedName("total_profit_loss")
  private BigDecimal totalProfitLoss = null;
  @SerializedName("realized_profit_loss")
  private BigDecimal realizedProfitLoss = null;
  @SerializedName("delta")
  private BigDecimal delta = null;
  @SerializedName("initial_margin")
  private BigDecimal initialMargin = null;
  @SerializedName("size")
  private BigDecimal size = null;
  @SerializedName("maintenance_margin")
  private BigDecimal maintenanceMargin = null;
  public enum KindEnum {
     future,  option, 
  };
  @SerializedName("kind")
  private KindEnum kind = null;
  @SerializedName("mark_price")
  private BigDecimal markPrice = null;
  @SerializedName("average_price")
  private BigDecimal averagePrice = null;
  @SerializedName("settlement_price")
  private BigDecimal settlementPrice = null;
  @SerializedName("index_price")
  private BigDecimal indexPrice = null;
  @SerializedName("instrument_name")
  private String instrumentName = null;
  @SerializedName("size_currency")
  private BigDecimal sizeCurrency = null;

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
   * Only for options, average price in USD
   **/
  @ApiModelProperty(value = "Only for options, average price in USD")
  public BigDecimal getAveragePriceUsd() {
    return averagePriceUsd;
  }
  public void setAveragePriceUsd(BigDecimal averagePriceUsd) {
    this.averagePriceUsd = averagePriceUsd;
  }

  /**
   * Only for futures, estimated liquidation price
   **/
  @ApiModelProperty(value = "Only for futures, estimated liquidation price")
  public BigDecimal getEstimatedLiquidationPrice() {
    return estimatedLiquidationPrice;
  }
  public void setEstimatedLiquidationPrice(BigDecimal estimatedLiquidationPrice) {
    this.estimatedLiquidationPrice = estimatedLiquidationPrice;
  }

  /**
   * Floating profit or loss
   **/
  @ApiModelProperty(required = true, value = "Floating profit or loss")
  public BigDecimal getFloatingProfitLoss() {
    return floatingProfitLoss;
  }
  public void setFloatingProfitLoss(BigDecimal floatingProfitLoss) {
    this.floatingProfitLoss = floatingProfitLoss;
  }

  /**
   * Only for options, floating profit or loss in USD
   **/
  @ApiModelProperty(value = "Only for options, floating profit or loss in USD")
  public BigDecimal getFloatingProfitLossUsd() {
    return floatingProfitLossUsd;
  }
  public void setFloatingProfitLossUsd(BigDecimal floatingProfitLossUsd) {
    this.floatingProfitLossUsd = floatingProfitLossUsd;
  }

  /**
   * Open orders margin
   **/
  @ApiModelProperty(required = true, value = "Open orders margin")
  public BigDecimal getOpenOrdersMargin() {
    return openOrdersMargin;
  }
  public void setOpenOrdersMargin(BigDecimal openOrdersMargin) {
    this.openOrdersMargin = openOrdersMargin;
  }

  /**
   * Profit or loss from position
   **/
  @ApiModelProperty(required = true, value = "Profit or loss from position")
  public BigDecimal getTotalProfitLoss() {
    return totalProfitLoss;
  }
  public void setTotalProfitLoss(BigDecimal totalProfitLoss) {
    this.totalProfitLoss = totalProfitLoss;
  }

  /**
   * Realized profit or loss
   **/
  @ApiModelProperty(value = "Realized profit or loss")
  public BigDecimal getRealizedProfitLoss() {
    return realizedProfitLoss;
  }
  public void setRealizedProfitLoss(BigDecimal realizedProfitLoss) {
    this.realizedProfitLoss = realizedProfitLoss;
  }

  /**
   * Delta parameter
   **/
  @ApiModelProperty(required = true, value = "Delta parameter")
  public BigDecimal getDelta() {
    return delta;
  }
  public void setDelta(BigDecimal delta) {
    this.delta = delta;
  }

  /**
   * Initial margin
   **/
  @ApiModelProperty(required = true, value = "Initial margin")
  public BigDecimal getInitialMargin() {
    return initialMargin;
  }
  public void setInitialMargin(BigDecimal initialMargin) {
    this.initialMargin = initialMargin;
  }

  /**
   * Position size for futures size in quote currency (e.g. USD), for options size is in base currency (e.g. BTC)
   **/
  @ApiModelProperty(required = true, value = "Position size for futures size in quote currency (e.g. USD), for options size is in base currency (e.g. BTC)")
  public BigDecimal getSize() {
    return size;
  }
  public void setSize(BigDecimal size) {
    this.size = size;
  }

  /**
   * Maintenance margin
   **/
  @ApiModelProperty(required = true, value = "Maintenance margin")
  public BigDecimal getMaintenanceMargin() {
    return maintenanceMargin;
  }
  public void setMaintenanceMargin(BigDecimal maintenanceMargin) {
    this.maintenanceMargin = maintenanceMargin;
  }

  /**
   * Instrument kind, `\"future\"` or `\"option\"`
   **/
  @ApiModelProperty(required = true, value = "Instrument kind, `\"future\"` or `\"option\"`")
  public KindEnum getKind() {
    return kind;
  }
  public void setKind(KindEnum kind) {
    this.kind = kind;
  }

  /**
   * Current mark price for position's instrument
   **/
  @ApiModelProperty(required = true, value = "Current mark price for position's instrument")
  public BigDecimal getMarkPrice() {
    return markPrice;
  }
  public void setMarkPrice(BigDecimal markPrice) {
    this.markPrice = markPrice;
  }

  /**
   * Average price of trades that built this position
   **/
  @ApiModelProperty(required = true, value = "Average price of trades that built this position")
  public BigDecimal getAveragePrice() {
    return averagePrice;
  }
  public void setAveragePrice(BigDecimal averagePrice) {
    this.averagePrice = averagePrice;
  }

  /**
   * Last settlement price for position's instrument 0 if instrument wasn't settled yet
   **/
  @ApiModelProperty(required = true, value = "Last settlement price for position's instrument 0 if instrument wasn't settled yet")
  public BigDecimal getSettlementPrice() {
    return settlementPrice;
  }
  public void setSettlementPrice(BigDecimal settlementPrice) {
    this.settlementPrice = settlementPrice;
  }

  /**
   * Current index price
   **/
  @ApiModelProperty(required = true, value = "Current index price")
  public BigDecimal getIndexPrice() {
    return indexPrice;
  }
  public void setIndexPrice(BigDecimal indexPrice) {
    this.indexPrice = indexPrice;
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
   * Only for futures, position size in base currency
   **/
  @ApiModelProperty(value = "Only for futures, position size in base currency")
  public BigDecimal getSizeCurrency() {
    return sizeCurrency;
  }
  public void setSizeCurrency(BigDecimal sizeCurrency) {
    this.sizeCurrency = sizeCurrency;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Position position = (Position) o;
    return (this.direction == null ? position.direction == null : this.direction.equals(position.direction)) &&
        (this.averagePriceUsd == null ? position.averagePriceUsd == null : this.averagePriceUsd.equals(position.averagePriceUsd)) &&
        (this.estimatedLiquidationPrice == null ? position.estimatedLiquidationPrice == null : this.estimatedLiquidationPrice.equals(position.estimatedLiquidationPrice)) &&
        (this.floatingProfitLoss == null ? position.floatingProfitLoss == null : this.floatingProfitLoss.equals(position.floatingProfitLoss)) &&
        (this.floatingProfitLossUsd == null ? position.floatingProfitLossUsd == null : this.floatingProfitLossUsd.equals(position.floatingProfitLossUsd)) &&
        (this.openOrdersMargin == null ? position.openOrdersMargin == null : this.openOrdersMargin.equals(position.openOrdersMargin)) &&
        (this.totalProfitLoss == null ? position.totalProfitLoss == null : this.totalProfitLoss.equals(position.totalProfitLoss)) &&
        (this.realizedProfitLoss == null ? position.realizedProfitLoss == null : this.realizedProfitLoss.equals(position.realizedProfitLoss)) &&
        (this.delta == null ? position.delta == null : this.delta.equals(position.delta)) &&
        (this.initialMargin == null ? position.initialMargin == null : this.initialMargin.equals(position.initialMargin)) &&
        (this.size == null ? position.size == null : this.size.equals(position.size)) &&
        (this.maintenanceMargin == null ? position.maintenanceMargin == null : this.maintenanceMargin.equals(position.maintenanceMargin)) &&
        (this.kind == null ? position.kind == null : this.kind.equals(position.kind)) &&
        (this.markPrice == null ? position.markPrice == null : this.markPrice.equals(position.markPrice)) &&
        (this.averagePrice == null ? position.averagePrice == null : this.averagePrice.equals(position.averagePrice)) &&
        (this.settlementPrice == null ? position.settlementPrice == null : this.settlementPrice.equals(position.settlementPrice)) &&
        (this.indexPrice == null ? position.indexPrice == null : this.indexPrice.equals(position.indexPrice)) &&
        (this.instrumentName == null ? position.instrumentName == null : this.instrumentName.equals(position.instrumentName)) &&
        (this.sizeCurrency == null ? position.sizeCurrency == null : this.sizeCurrency.equals(position.sizeCurrency));
  }

  @Override
  public int hashCode() {
    int result = 17;
    result = 31 * result + (this.direction == null ? 0: this.direction.hashCode());
    result = 31 * result + (this.averagePriceUsd == null ? 0: this.averagePriceUsd.hashCode());
    result = 31 * result + (this.estimatedLiquidationPrice == null ? 0: this.estimatedLiquidationPrice.hashCode());
    result = 31 * result + (this.floatingProfitLoss == null ? 0: this.floatingProfitLoss.hashCode());
    result = 31 * result + (this.floatingProfitLossUsd == null ? 0: this.floatingProfitLossUsd.hashCode());
    result = 31 * result + (this.openOrdersMargin == null ? 0: this.openOrdersMargin.hashCode());
    result = 31 * result + (this.totalProfitLoss == null ? 0: this.totalProfitLoss.hashCode());
    result = 31 * result + (this.realizedProfitLoss == null ? 0: this.realizedProfitLoss.hashCode());
    result = 31 * result + (this.delta == null ? 0: this.delta.hashCode());
    result = 31 * result + (this.initialMargin == null ? 0: this.initialMargin.hashCode());
    result = 31 * result + (this.size == null ? 0: this.size.hashCode());
    result = 31 * result + (this.maintenanceMargin == null ? 0: this.maintenanceMargin.hashCode());
    result = 31 * result + (this.kind == null ? 0: this.kind.hashCode());
    result = 31 * result + (this.markPrice == null ? 0: this.markPrice.hashCode());
    result = 31 * result + (this.averagePrice == null ? 0: this.averagePrice.hashCode());
    result = 31 * result + (this.settlementPrice == null ? 0: this.settlementPrice.hashCode());
    result = 31 * result + (this.indexPrice == null ? 0: this.indexPrice.hashCode());
    result = 31 * result + (this.instrumentName == null ? 0: this.instrumentName.hashCode());
    result = 31 * result + (this.sizeCurrency == null ? 0: this.sizeCurrency.hashCode());
    return result;
  }

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class Position {\n");
    
    sb.append("  direction: ").append(direction).append("\n");
    sb.append("  averagePriceUsd: ").append(averagePriceUsd).append("\n");
    sb.append("  estimatedLiquidationPrice: ").append(estimatedLiquidationPrice).append("\n");
    sb.append("  floatingProfitLoss: ").append(floatingProfitLoss).append("\n");
    sb.append("  floatingProfitLossUsd: ").append(floatingProfitLossUsd).append("\n");
    sb.append("  openOrdersMargin: ").append(openOrdersMargin).append("\n");
    sb.append("  totalProfitLoss: ").append(totalProfitLoss).append("\n");
    sb.append("  realizedProfitLoss: ").append(realizedProfitLoss).append("\n");
    sb.append("  delta: ").append(delta).append("\n");
    sb.append("  initialMargin: ").append(initialMargin).append("\n");
    sb.append("  size: ").append(size).append("\n");
    sb.append("  maintenanceMargin: ").append(maintenanceMargin).append("\n");
    sb.append("  kind: ").append(kind).append("\n");
    sb.append("  markPrice: ").append(markPrice).append("\n");
    sb.append("  averagePrice: ").append(averagePrice).append("\n");
    sb.append("  settlementPrice: ").append(settlementPrice).append("\n");
    sb.append("  indexPrice: ").append(indexPrice).append("\n");
    sb.append("  instrumentName: ").append(instrumentName).append("\n");
    sb.append("  sizeCurrency: ").append(sizeCurrency).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
