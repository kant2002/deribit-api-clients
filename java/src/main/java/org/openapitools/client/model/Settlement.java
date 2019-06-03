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
 * Settlement
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2019-06-03T12:41:14.884+02:00[Europe/Paris]")
public class Settlement {
  public static final String SERIALIZED_NAME_SESSION_PROFIT_LOSS = "session_profit_loss";
  @SerializedName(SERIALIZED_NAME_SESSION_PROFIT_LOSS)
  private BigDecimal sessionProfitLoss;

  public static final String SERIALIZED_NAME_MARK_PRICE = "mark_price";
  @SerializedName(SERIALIZED_NAME_MARK_PRICE)
  private BigDecimal markPrice;

  public static final String SERIALIZED_NAME_FUNDING = "funding";
  @SerializedName(SERIALIZED_NAME_FUNDING)
  private BigDecimal funding;

  public static final String SERIALIZED_NAME_SOCIALIZED = "socialized";
  @SerializedName(SERIALIZED_NAME_SOCIALIZED)
  private BigDecimal socialized;

  public static final String SERIALIZED_NAME_SESSION_BANKRUPCY = "session_bankrupcy";
  @SerializedName(SERIALIZED_NAME_SESSION_BANKRUPCY)
  private BigDecimal sessionBankrupcy;

  public static final String SERIALIZED_NAME_TIMESTAMP = "timestamp";
  @SerializedName(SERIALIZED_NAME_TIMESTAMP)
  private Integer timestamp;

  public static final String SERIALIZED_NAME_PROFIT_LOSS = "profit_loss";
  @SerializedName(SERIALIZED_NAME_PROFIT_LOSS)
  private BigDecimal profitLoss;

  public static final String SERIALIZED_NAME_FUNDED = "funded";
  @SerializedName(SERIALIZED_NAME_FUNDED)
  private BigDecimal funded;

  public static final String SERIALIZED_NAME_INDEX_PRICE = "index_price";
  @SerializedName(SERIALIZED_NAME_INDEX_PRICE)
  private BigDecimal indexPrice;

  public static final String SERIALIZED_NAME_SESSION_TAX = "session_tax";
  @SerializedName(SERIALIZED_NAME_SESSION_TAX)
  private BigDecimal sessionTax;

  public static final String SERIALIZED_NAME_SESSION_TAX_RATE = "session_tax_rate";
  @SerializedName(SERIALIZED_NAME_SESSION_TAX_RATE)
  private BigDecimal sessionTaxRate;

  public static final String SERIALIZED_NAME_INSTRUMENT_NAME = "instrument_name";
  @SerializedName(SERIALIZED_NAME_INSTRUMENT_NAME)
  private String instrumentName;

  public static final String SERIALIZED_NAME_POSITION = "position";
  @SerializedName(SERIALIZED_NAME_POSITION)
  private BigDecimal position;

  /**
   * The type of settlement. &#x60;settlement&#x60;, &#x60;delivery&#x60; or &#x60;bankruptcy&#x60;.
   */
  @JsonAdapter(TypeEnum.Adapter.class)
  public enum TypeEnum {
    SETTLEMENT("settlement"),
    
    DELIVERY("delivery"),
    
    BANKRUPTCY("bankruptcy");

    private String value;

    TypeEnum(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    public static TypeEnum fromValue(String value) {
      for (TypeEnum b : TypeEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }

    public static class Adapter extends TypeAdapter<TypeEnum> {
      @Override
      public void write(final JsonWriter jsonWriter, final TypeEnum enumeration) throws IOException {
        jsonWriter.value(enumeration.getValue());
      }

      @Override
      public TypeEnum read(final JsonReader jsonReader) throws IOException {
        String value = jsonReader.nextString();
        return TypeEnum.fromValue(value);
      }
    }
  }

  public static final String SERIALIZED_NAME_TYPE = "type";
  @SerializedName(SERIALIZED_NAME_TYPE)
  private TypeEnum type;

  public Settlement sessionProfitLoss(BigDecimal sessionProfitLoss) {
    this.sessionProfitLoss = sessionProfitLoss;
    return this;
  }

   /**
   * total value of session profit and losses (in base currency)
   * @return sessionProfitLoss
  **/
  @ApiModelProperty(example = "0.001160788", required = true, value = "total value of session profit and losses (in base currency)")
  public BigDecimal getSessionProfitLoss() {
    return sessionProfitLoss;
  }

  public void setSessionProfitLoss(BigDecimal sessionProfitLoss) {
    this.sessionProfitLoss = sessionProfitLoss;
  }

  public Settlement markPrice(BigDecimal markPrice) {
    this.markPrice = markPrice;
    return this;
  }

   /**
   * mark price for at the settlement time (in quote currency; settlement and delivery only)
   * @return markPrice
  **/
  @ApiModelProperty(example = "11000", value = "mark price for at the settlement time (in quote currency; settlement and delivery only)")
  public BigDecimal getMarkPrice() {
    return markPrice;
  }

  public void setMarkPrice(BigDecimal markPrice) {
    this.markPrice = markPrice;
  }

  public Settlement funding(BigDecimal funding) {
    this.funding = funding;
    return this;
  }

   /**
   * funding (in base currency ; settlement for perpetual product only)
   * @return funding
  **/
  @ApiModelProperty(example = "-0.000002511", required = true, value = "funding (in base currency ; settlement for perpetual product only)")
  public BigDecimal getFunding() {
    return funding;
  }

  public void setFunding(BigDecimal funding) {
    this.funding = funding;
  }

  public Settlement socialized(BigDecimal socialized) {
    this.socialized = socialized;
    return this;
  }

   /**
   * the amount of the socialized losses (in base currency; bankruptcy only)
   * @return socialized
  **/
  @ApiModelProperty(example = "-0.001160788", value = "the amount of the socialized losses (in base currency; bankruptcy only)")
  public BigDecimal getSocialized() {
    return socialized;
  }

  public void setSocialized(BigDecimal socialized) {
    this.socialized = socialized;
  }

  public Settlement sessionBankrupcy(BigDecimal sessionBankrupcy) {
    this.sessionBankrupcy = sessionBankrupcy;
    return this;
  }

   /**
   * value of session bankrupcy (in base currency; bankruptcy only)
   * @return sessionBankrupcy
  **/
  @ApiModelProperty(example = "0.001160788", value = "value of session bankrupcy (in base currency; bankruptcy only)")
  public BigDecimal getSessionBankrupcy() {
    return sessionBankrupcy;
  }

  public void setSessionBankrupcy(BigDecimal sessionBankrupcy) {
    this.sessionBankrupcy = sessionBankrupcy;
  }

  public Settlement timestamp(Integer timestamp) {
    this.timestamp = timestamp;
    return this;
  }

   /**
   * The timestamp (seconds since the Unix epoch, with millisecond precision)
   * @return timestamp
  **/
  @ApiModelProperty(example = "1536569522277", required = true, value = "The timestamp (seconds since the Unix epoch, with millisecond precision)")
  public Integer getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Integer timestamp) {
    this.timestamp = timestamp;
  }

  public Settlement profitLoss(BigDecimal profitLoss) {
    this.profitLoss = profitLoss;
    return this;
  }

   /**
   * profit and loss (in base currency; settlement and delivery only)
   * @return profitLoss
  **/
  @ApiModelProperty(example = "0", value = "profit and loss (in base currency; settlement and delivery only)")
  public BigDecimal getProfitLoss() {
    return profitLoss;
  }

  public void setProfitLoss(BigDecimal profitLoss) {
    this.profitLoss = profitLoss;
  }

  public Settlement funded(BigDecimal funded) {
    this.funded = funded;
    return this;
  }

   /**
   * funded amount (bankruptcy only)
   * @return funded
  **/
  @ApiModelProperty(example = "0", value = "funded amount (bankruptcy only)")
  public BigDecimal getFunded() {
    return funded;
  }

  public void setFunded(BigDecimal funded) {
    this.funded = funded;
  }

  public Settlement indexPrice(BigDecimal indexPrice) {
    this.indexPrice = indexPrice;
    return this;
  }

   /**
   * underlying index price at time of event (in quote currency; settlement and delivery only)
   * @return indexPrice
  **/
  @ApiModelProperty(example = "11008.37", required = true, value = "underlying index price at time of event (in quote currency; settlement and delivery only)")
  public BigDecimal getIndexPrice() {
    return indexPrice;
  }

  public void setIndexPrice(BigDecimal indexPrice) {
    this.indexPrice = indexPrice;
  }

  public Settlement sessionTax(BigDecimal sessionTax) {
    this.sessionTax = sessionTax;
    return this;
  }

   /**
   * total amount of paid taxes/fees (in base currency; bankruptcy only)
   * @return sessionTax
  **/
  @ApiModelProperty(example = "-0.001160788", value = "total amount of paid taxes/fees (in base currency; bankruptcy only)")
  public BigDecimal getSessionTax() {
    return sessionTax;
  }

  public void setSessionTax(BigDecimal sessionTax) {
    this.sessionTax = sessionTax;
  }

  public Settlement sessionTaxRate(BigDecimal sessionTaxRate) {
    this.sessionTaxRate = sessionTaxRate;
    return this;
  }

   /**
   * rate of paid texes/fees (in base currency; bankruptcy only)
   * @return sessionTaxRate
  **/
  @ApiModelProperty(example = "0.000103333", value = "rate of paid texes/fees (in base currency; bankruptcy only)")
  public BigDecimal getSessionTaxRate() {
    return sessionTaxRate;
  }

  public void setSessionTaxRate(BigDecimal sessionTaxRate) {
    this.sessionTaxRate = sessionTaxRate;
  }

  public Settlement instrumentName(String instrumentName) {
    this.instrumentName = instrumentName;
    return this;
  }

   /**
   * instrument name (settlement and delivery only)
   * @return instrumentName
  **/
  @ApiModelProperty(example = "BTC-30MAR18", required = true, value = "instrument name (settlement and delivery only)")
  public String getInstrumentName() {
    return instrumentName;
  }

  public void setInstrumentName(String instrumentName) {
    this.instrumentName = instrumentName;
  }

  public Settlement position(BigDecimal position) {
    this.position = position;
    return this;
  }

   /**
   * position size (in quote currency; settlement and delivery only)
   * @return position
  **/
  @ApiModelProperty(example = "1000", required = true, value = "position size (in quote currency; settlement and delivery only)")
  public BigDecimal getPosition() {
    return position;
  }

  public void setPosition(BigDecimal position) {
    this.position = position;
  }

  public Settlement type(TypeEnum type) {
    this.type = type;
    return this;
  }

   /**
   * The type of settlement. &#x60;settlement&#x60;, &#x60;delivery&#x60; or &#x60;bankruptcy&#x60;.
   * @return type
  **/
  @ApiModelProperty(required = true, value = "The type of settlement. `settlement`, `delivery` or `bankruptcy`.")
  public TypeEnum getType() {
    return type;
  }

  public void setType(TypeEnum type) {
    this.type = type;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Settlement settlement = (Settlement) o;
    return Objects.equals(this.sessionProfitLoss, settlement.sessionProfitLoss) &&
        Objects.equals(this.markPrice, settlement.markPrice) &&
        Objects.equals(this.funding, settlement.funding) &&
        Objects.equals(this.socialized, settlement.socialized) &&
        Objects.equals(this.sessionBankrupcy, settlement.sessionBankrupcy) &&
        Objects.equals(this.timestamp, settlement.timestamp) &&
        Objects.equals(this.profitLoss, settlement.profitLoss) &&
        Objects.equals(this.funded, settlement.funded) &&
        Objects.equals(this.indexPrice, settlement.indexPrice) &&
        Objects.equals(this.sessionTax, settlement.sessionTax) &&
        Objects.equals(this.sessionTaxRate, settlement.sessionTaxRate) &&
        Objects.equals(this.instrumentName, settlement.instrumentName) &&
        Objects.equals(this.position, settlement.position) &&
        Objects.equals(this.type, settlement.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(sessionProfitLoss, markPrice, funding, socialized, sessionBankrupcy, timestamp, profitLoss, funded, indexPrice, sessionTax, sessionTaxRate, instrumentName, position, type);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Settlement {\n");
    sb.append("    sessionProfitLoss: ").append(toIndentedString(sessionProfitLoss)).append("\n");
    sb.append("    markPrice: ").append(toIndentedString(markPrice)).append("\n");
    sb.append("    funding: ").append(toIndentedString(funding)).append("\n");
    sb.append("    socialized: ").append(toIndentedString(socialized)).append("\n");
    sb.append("    sessionBankrupcy: ").append(toIndentedString(sessionBankrupcy)).append("\n");
    sb.append("    timestamp: ").append(toIndentedString(timestamp)).append("\n");
    sb.append("    profitLoss: ").append(toIndentedString(profitLoss)).append("\n");
    sb.append("    funded: ").append(toIndentedString(funded)).append("\n");
    sb.append("    indexPrice: ").append(toIndentedString(indexPrice)).append("\n");
    sb.append("    sessionTax: ").append(toIndentedString(sessionTax)).append("\n");
    sb.append("    sessionTaxRate: ").append(toIndentedString(sessionTaxRate)).append("\n");
    sb.append("    instrumentName: ").append(toIndentedString(instrumentName)).append("\n");
    sb.append("    position: ").append(toIndentedString(position)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
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

