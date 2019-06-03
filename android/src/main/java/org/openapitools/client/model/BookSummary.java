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
public class BookSummary {
  
  @SerializedName("underlying_index")
  private String underlyingIndex = null;
  @SerializedName("volume")
  private BigDecimal volume = null;
  @SerializedName("volume_usd")
  private BigDecimal volumeUsd = null;
  @SerializedName("underlying_price")
  private BigDecimal underlyingPrice = null;
  @SerializedName("bid_price")
  private BigDecimal bidPrice = null;
  @SerializedName("open_interest")
  private BigDecimal openInterest = null;
  @SerializedName("quote_currency")
  private String quoteCurrency = null;
  @SerializedName("high")
  private BigDecimal high = null;
  @SerializedName("estimated_delivery_price")
  private BigDecimal estimatedDeliveryPrice = null;
  @SerializedName("last")
  private BigDecimal last = null;
  @SerializedName("mid_price")
  private BigDecimal midPrice = null;
  @SerializedName("interest_rate")
  private BigDecimal interestRate = null;
  @SerializedName("funding_8h")
  private BigDecimal funding8h = null;
  @SerializedName("mark_price")
  private BigDecimal markPrice = null;
  @SerializedName("ask_price")
  private BigDecimal askPrice = null;
  @SerializedName("instrument_name")
  private String instrumentName = null;
  @SerializedName("low")
  private BigDecimal low = null;
  @SerializedName("base_currency")
  private String baseCurrency = null;
  @SerializedName("creation_timestamp")
  private Integer creationTimestamp = null;
  @SerializedName("current_funding")
  private BigDecimal currentFunding = null;

  /**
   * Name of the underlying future, or `'index_price'` (options only)
   **/
  @ApiModelProperty(value = "Name of the underlying future, or `'index_price'` (options only)")
  public String getUnderlyingIndex() {
    return underlyingIndex;
  }
  public void setUnderlyingIndex(String underlyingIndex) {
    this.underlyingIndex = underlyingIndex;
  }

  /**
   * The total 24h traded volume (in base currency)
   **/
  @ApiModelProperty(required = true, value = "The total 24h traded volume (in base currency)")
  public BigDecimal getVolume() {
    return volume;
  }
  public void setVolume(BigDecimal volume) {
    this.volume = volume;
  }

  /**
   * Volume in usd (futures only)
   **/
  @ApiModelProperty(value = "Volume in usd (futures only)")
  public BigDecimal getVolumeUsd() {
    return volumeUsd;
  }
  public void setVolumeUsd(BigDecimal volumeUsd) {
    this.volumeUsd = volumeUsd;
  }

  /**
   * underlying price for implied volatility calculations (options only)
   **/
  @ApiModelProperty(value = "underlying price for implied volatility calculations (options only)")
  public BigDecimal getUnderlyingPrice() {
    return underlyingPrice;
  }
  public void setUnderlyingPrice(BigDecimal underlyingPrice) {
    this.underlyingPrice = underlyingPrice;
  }

  /**
   * The current best bid price, `null` if there aren't any bids
   **/
  @ApiModelProperty(required = true, value = "The current best bid price, `null` if there aren't any bids")
  public BigDecimal getBidPrice() {
    return bidPrice;
  }
  public void setBidPrice(BigDecimal bidPrice) {
    this.bidPrice = bidPrice;
  }

  /**
   * The total amount of outstanding contracts in the corresponding amount units. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH.
   **/
  @ApiModelProperty(required = true, value = "The total amount of outstanding contracts in the corresponding amount units. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH.")
  public BigDecimal getOpenInterest() {
    return openInterest;
  }
  public void setOpenInterest(BigDecimal openInterest) {
    this.openInterest = openInterest;
  }

  /**
   * Quote currency
   **/
  @ApiModelProperty(required = true, value = "Quote currency")
  public String getQuoteCurrency() {
    return quoteCurrency;
  }
  public void setQuoteCurrency(String quoteCurrency) {
    this.quoteCurrency = quoteCurrency;
  }

  /**
   * Price of the 24h highest trade
   **/
  @ApiModelProperty(required = true, value = "Price of the 24h highest trade")
  public BigDecimal getHigh() {
    return high;
  }
  public void setHigh(BigDecimal high) {
    this.high = high;
  }

  /**
   * Estimated delivery price, in USD (futures only). For more details, see Documentation > General > Expiration Price
   **/
  @ApiModelProperty(value = "Estimated delivery price, in USD (futures only). For more details, see Documentation > General > Expiration Price")
  public BigDecimal getEstimatedDeliveryPrice() {
    return estimatedDeliveryPrice;
  }
  public void setEstimatedDeliveryPrice(BigDecimal estimatedDeliveryPrice) {
    this.estimatedDeliveryPrice = estimatedDeliveryPrice;
  }

  /**
   * The price of the latest trade, `null` if there weren't any trades
   **/
  @ApiModelProperty(required = true, value = "The price of the latest trade, `null` if there weren't any trades")
  public BigDecimal getLast() {
    return last;
  }
  public void setLast(BigDecimal last) {
    this.last = last;
  }

  /**
   * The average of the best bid and ask, `null` if there aren't any asks or bids
   **/
  @ApiModelProperty(required = true, value = "The average of the best bid and ask, `null` if there aren't any asks or bids")
  public BigDecimal getMidPrice() {
    return midPrice;
  }
  public void setMidPrice(BigDecimal midPrice) {
    this.midPrice = midPrice;
  }

  /**
   * Interest rate used in implied volatility calculations (options only)
   **/
  @ApiModelProperty(value = "Interest rate used in implied volatility calculations (options only)")
  public BigDecimal getInterestRate() {
    return interestRate;
  }
  public void setInterestRate(BigDecimal interestRate) {
    this.interestRate = interestRate;
  }

  /**
   * Funding 8h (perpetual only)
   **/
  @ApiModelProperty(value = "Funding 8h (perpetual only)")
  public BigDecimal getFunding8h() {
    return funding8h;
  }
  public void setFunding8h(BigDecimal funding8h) {
    this.funding8h = funding8h;
  }

  /**
   * The current instrument market price
   **/
  @ApiModelProperty(required = true, value = "The current instrument market price")
  public BigDecimal getMarkPrice() {
    return markPrice;
  }
  public void setMarkPrice(BigDecimal markPrice) {
    this.markPrice = markPrice;
  }

  /**
   * The current best ask price, `null` if there aren't any asks
   **/
  @ApiModelProperty(required = true, value = "The current best ask price, `null` if there aren't any asks")
  public BigDecimal getAskPrice() {
    return askPrice;
  }
  public void setAskPrice(BigDecimal askPrice) {
    this.askPrice = askPrice;
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
   * Price of the 24h lowest trade, `null` if there weren't any trades
   **/
  @ApiModelProperty(required = true, value = "Price of the 24h lowest trade, `null` if there weren't any trades")
  public BigDecimal getLow() {
    return low;
  }
  public void setLow(BigDecimal low) {
    this.low = low;
  }

  /**
   * Base currency
   **/
  @ApiModelProperty(value = "Base currency")
  public String getBaseCurrency() {
    return baseCurrency;
  }
  public void setBaseCurrency(String baseCurrency) {
    this.baseCurrency = baseCurrency;
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
   * Current funding (perpetual only)
   **/
  @ApiModelProperty(value = "Current funding (perpetual only)")
  public BigDecimal getCurrentFunding() {
    return currentFunding;
  }
  public void setCurrentFunding(BigDecimal currentFunding) {
    this.currentFunding = currentFunding;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BookSummary bookSummary = (BookSummary) o;
    return (this.underlyingIndex == null ? bookSummary.underlyingIndex == null : this.underlyingIndex.equals(bookSummary.underlyingIndex)) &&
        (this.volume == null ? bookSummary.volume == null : this.volume.equals(bookSummary.volume)) &&
        (this.volumeUsd == null ? bookSummary.volumeUsd == null : this.volumeUsd.equals(bookSummary.volumeUsd)) &&
        (this.underlyingPrice == null ? bookSummary.underlyingPrice == null : this.underlyingPrice.equals(bookSummary.underlyingPrice)) &&
        (this.bidPrice == null ? bookSummary.bidPrice == null : this.bidPrice.equals(bookSummary.bidPrice)) &&
        (this.openInterest == null ? bookSummary.openInterest == null : this.openInterest.equals(bookSummary.openInterest)) &&
        (this.quoteCurrency == null ? bookSummary.quoteCurrency == null : this.quoteCurrency.equals(bookSummary.quoteCurrency)) &&
        (this.high == null ? bookSummary.high == null : this.high.equals(bookSummary.high)) &&
        (this.estimatedDeliveryPrice == null ? bookSummary.estimatedDeliveryPrice == null : this.estimatedDeliveryPrice.equals(bookSummary.estimatedDeliveryPrice)) &&
        (this.last == null ? bookSummary.last == null : this.last.equals(bookSummary.last)) &&
        (this.midPrice == null ? bookSummary.midPrice == null : this.midPrice.equals(bookSummary.midPrice)) &&
        (this.interestRate == null ? bookSummary.interestRate == null : this.interestRate.equals(bookSummary.interestRate)) &&
        (this.funding8h == null ? bookSummary.funding8h == null : this.funding8h.equals(bookSummary.funding8h)) &&
        (this.markPrice == null ? bookSummary.markPrice == null : this.markPrice.equals(bookSummary.markPrice)) &&
        (this.askPrice == null ? bookSummary.askPrice == null : this.askPrice.equals(bookSummary.askPrice)) &&
        (this.instrumentName == null ? bookSummary.instrumentName == null : this.instrumentName.equals(bookSummary.instrumentName)) &&
        (this.low == null ? bookSummary.low == null : this.low.equals(bookSummary.low)) &&
        (this.baseCurrency == null ? bookSummary.baseCurrency == null : this.baseCurrency.equals(bookSummary.baseCurrency)) &&
        (this.creationTimestamp == null ? bookSummary.creationTimestamp == null : this.creationTimestamp.equals(bookSummary.creationTimestamp)) &&
        (this.currentFunding == null ? bookSummary.currentFunding == null : this.currentFunding.equals(bookSummary.currentFunding));
  }

  @Override
  public int hashCode() {
    int result = 17;
    result = 31 * result + (this.underlyingIndex == null ? 0: this.underlyingIndex.hashCode());
    result = 31 * result + (this.volume == null ? 0: this.volume.hashCode());
    result = 31 * result + (this.volumeUsd == null ? 0: this.volumeUsd.hashCode());
    result = 31 * result + (this.underlyingPrice == null ? 0: this.underlyingPrice.hashCode());
    result = 31 * result + (this.bidPrice == null ? 0: this.bidPrice.hashCode());
    result = 31 * result + (this.openInterest == null ? 0: this.openInterest.hashCode());
    result = 31 * result + (this.quoteCurrency == null ? 0: this.quoteCurrency.hashCode());
    result = 31 * result + (this.high == null ? 0: this.high.hashCode());
    result = 31 * result + (this.estimatedDeliveryPrice == null ? 0: this.estimatedDeliveryPrice.hashCode());
    result = 31 * result + (this.last == null ? 0: this.last.hashCode());
    result = 31 * result + (this.midPrice == null ? 0: this.midPrice.hashCode());
    result = 31 * result + (this.interestRate == null ? 0: this.interestRate.hashCode());
    result = 31 * result + (this.funding8h == null ? 0: this.funding8h.hashCode());
    result = 31 * result + (this.markPrice == null ? 0: this.markPrice.hashCode());
    result = 31 * result + (this.askPrice == null ? 0: this.askPrice.hashCode());
    result = 31 * result + (this.instrumentName == null ? 0: this.instrumentName.hashCode());
    result = 31 * result + (this.low == null ? 0: this.low.hashCode());
    result = 31 * result + (this.baseCurrency == null ? 0: this.baseCurrency.hashCode());
    result = 31 * result + (this.creationTimestamp == null ? 0: this.creationTimestamp.hashCode());
    result = 31 * result + (this.currentFunding == null ? 0: this.currentFunding.hashCode());
    return result;
  }

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class BookSummary {\n");
    
    sb.append("  underlyingIndex: ").append(underlyingIndex).append("\n");
    sb.append("  volume: ").append(volume).append("\n");
    sb.append("  volumeUsd: ").append(volumeUsd).append("\n");
    sb.append("  underlyingPrice: ").append(underlyingPrice).append("\n");
    sb.append("  bidPrice: ").append(bidPrice).append("\n");
    sb.append("  openInterest: ").append(openInterest).append("\n");
    sb.append("  quoteCurrency: ").append(quoteCurrency).append("\n");
    sb.append("  high: ").append(high).append("\n");
    sb.append("  estimatedDeliveryPrice: ").append(estimatedDeliveryPrice).append("\n");
    sb.append("  last: ").append(last).append("\n");
    sb.append("  midPrice: ").append(midPrice).append("\n");
    sb.append("  interestRate: ").append(interestRate).append("\n");
    sb.append("  funding8h: ").append(funding8h).append("\n");
    sb.append("  markPrice: ").append(markPrice).append("\n");
    sb.append("  askPrice: ").append(askPrice).append("\n");
    sb.append("  instrumentName: ").append(instrumentName).append("\n");
    sb.append("  low: ").append(low).append("\n");
    sb.append("  baseCurrency: ").append(baseCurrency).append("\n");
    sb.append("  creationTimestamp: ").append(creationTimestamp).append("\n");
    sb.append("  currentFunding: ").append(currentFunding).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
