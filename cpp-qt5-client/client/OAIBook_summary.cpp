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


#include "OAIBook_summary.h"

#include "OAIHelpers.h"

#include <QJsonDocument>
#include <QJsonArray>
#include <QObject>
#include <QDebug>

namespace OpenAPI {

OAIBook_summary::OAIBook_summary(QString json) {
    this->init();
    this->fromJson(json);
}

OAIBook_summary::OAIBook_summary() {
    this->init();
}

OAIBook_summary::~OAIBook_summary() {

}

void
OAIBook_summary::init() {
    
    m_underlying_index_isSet = false;
    m_underlying_index_isValid = false;
    
    m_volume_isSet = false;
    m_volume_isValid = false;
    
    m_volume_usd_isSet = false;
    m_volume_usd_isValid = false;
    
    m_underlying_price_isSet = false;
    m_underlying_price_isValid = false;
    
    m_bid_price_isSet = false;
    m_bid_price_isValid = false;
    
    m_open_interest_isSet = false;
    m_open_interest_isValid = false;
    
    m_quote_currency_isSet = false;
    m_quote_currency_isValid = false;
    
    m_high_isSet = false;
    m_high_isValid = false;
    
    m_estimated_delivery_price_isSet = false;
    m_estimated_delivery_price_isValid = false;
    
    m_last_isSet = false;
    m_last_isValid = false;
    
    m_mid_price_isSet = false;
    m_mid_price_isValid = false;
    
    m_interest_rate_isSet = false;
    m_interest_rate_isValid = false;
    
    m_funding_8h_isSet = false;
    m_funding_8h_isValid = false;
    
    m_mark_price_isSet = false;
    m_mark_price_isValid = false;
    
    m_ask_price_isSet = false;
    m_ask_price_isValid = false;
    
    m_instrument_name_isSet = false;
    m_instrument_name_isValid = false;
    
    m_low_isSet = false;
    m_low_isValid = false;
    
    m_base_currency_isSet = false;
    m_base_currency_isValid = false;
    
    m_creation_timestamp_isSet = false;
    m_creation_timestamp_isValid = false;
    
    m_current_funding_isSet = false;
    m_current_funding_isValid = false;
    }

void
OAIBook_summary::fromJson(QString jsonString) {
    QByteArray array (jsonString.toStdString().c_str());
    QJsonDocument doc = QJsonDocument::fromJson(array);
    QJsonObject jsonObject = doc.object();
    this->fromJsonObject(jsonObject);
}

void
OAIBook_summary::fromJsonObject(QJsonObject json) {
    
    m_underlying_index_isValid = ::OpenAPI::fromJsonValue(underlying_index, json[QString("underlying_index")]);
    
    
    m_volume_isValid = ::OpenAPI::fromJsonValue(volume, json[QString("volume")]);
    
    
    m_volume_usd_isValid = ::OpenAPI::fromJsonValue(volume_usd, json[QString("volume_usd")]);
    
    
    m_underlying_price_isValid = ::OpenAPI::fromJsonValue(underlying_price, json[QString("underlying_price")]);
    
    
    m_bid_price_isValid = ::OpenAPI::fromJsonValue(bid_price, json[QString("bid_price")]);
    
    
    m_open_interest_isValid = ::OpenAPI::fromJsonValue(open_interest, json[QString("open_interest")]);
    
    
    m_quote_currency_isValid = ::OpenAPI::fromJsonValue(quote_currency, json[QString("quote_currency")]);
    
    
    m_high_isValid = ::OpenAPI::fromJsonValue(high, json[QString("high")]);
    
    
    m_estimated_delivery_price_isValid = ::OpenAPI::fromJsonValue(estimated_delivery_price, json[QString("estimated_delivery_price")]);
    
    
    m_last_isValid = ::OpenAPI::fromJsonValue(last, json[QString("last")]);
    
    
    m_mid_price_isValid = ::OpenAPI::fromJsonValue(mid_price, json[QString("mid_price")]);
    
    
    m_interest_rate_isValid = ::OpenAPI::fromJsonValue(interest_rate, json[QString("interest_rate")]);
    
    
    m_funding_8h_isValid = ::OpenAPI::fromJsonValue(funding_8h, json[QString("funding_8h")]);
    
    
    m_mark_price_isValid = ::OpenAPI::fromJsonValue(mark_price, json[QString("mark_price")]);
    
    
    m_ask_price_isValid = ::OpenAPI::fromJsonValue(ask_price, json[QString("ask_price")]);
    
    
    m_instrument_name_isValid = ::OpenAPI::fromJsonValue(instrument_name, json[QString("instrument_name")]);
    
    
    m_low_isValid = ::OpenAPI::fromJsonValue(low, json[QString("low")]);
    
    
    m_base_currency_isValid = ::OpenAPI::fromJsonValue(base_currency, json[QString("base_currency")]);
    
    
    m_creation_timestamp_isValid = ::OpenAPI::fromJsonValue(creation_timestamp, json[QString("creation_timestamp")]);
    
    
    m_current_funding_isValid = ::OpenAPI::fromJsonValue(current_funding, json[QString("current_funding")]);
    
    
}

QString
OAIBook_summary::asJson () const {
    QJsonObject obj = this->asJsonObject();
    QJsonDocument doc(obj);
    QByteArray bytes = doc.toJson();
    return QString(bytes);
}

QJsonObject
OAIBook_summary::asJsonObject() const {
    QJsonObject obj;
	if(m_underlying_index_isSet){
        obj.insert(QString("underlying_index"), ::OpenAPI::toJsonValue(underlying_index));
    }
	if(m_volume_isSet){
        obj.insert(QString("volume"), ::OpenAPI::toJsonValue(volume));
    }
	if(m_volume_usd_isSet){
        obj.insert(QString("volume_usd"), ::OpenAPI::toJsonValue(volume_usd));
    }
	if(m_underlying_price_isSet){
        obj.insert(QString("underlying_price"), ::OpenAPI::toJsonValue(underlying_price));
    }
	if(m_bid_price_isSet){
        obj.insert(QString("bid_price"), ::OpenAPI::toJsonValue(bid_price));
    }
	if(m_open_interest_isSet){
        obj.insert(QString("open_interest"), ::OpenAPI::toJsonValue(open_interest));
    }
	if(m_quote_currency_isSet){
        obj.insert(QString("quote_currency"), ::OpenAPI::toJsonValue(quote_currency));
    }
	if(m_high_isSet){
        obj.insert(QString("high"), ::OpenAPI::toJsonValue(high));
    }
	if(m_estimated_delivery_price_isSet){
        obj.insert(QString("estimated_delivery_price"), ::OpenAPI::toJsonValue(estimated_delivery_price));
    }
	if(m_last_isSet){
        obj.insert(QString("last"), ::OpenAPI::toJsonValue(last));
    }
	if(m_mid_price_isSet){
        obj.insert(QString("mid_price"), ::OpenAPI::toJsonValue(mid_price));
    }
	if(m_interest_rate_isSet){
        obj.insert(QString("interest_rate"), ::OpenAPI::toJsonValue(interest_rate));
    }
	if(m_funding_8h_isSet){
        obj.insert(QString("funding_8h"), ::OpenAPI::toJsonValue(funding_8h));
    }
	if(m_mark_price_isSet){
        obj.insert(QString("mark_price"), ::OpenAPI::toJsonValue(mark_price));
    }
	if(m_ask_price_isSet){
        obj.insert(QString("ask_price"), ::OpenAPI::toJsonValue(ask_price));
    }
	if(m_instrument_name_isSet){
        obj.insert(QString("instrument_name"), ::OpenAPI::toJsonValue(instrument_name));
    }
	if(m_low_isSet){
        obj.insert(QString("low"), ::OpenAPI::toJsonValue(low));
    }
	if(m_base_currency_isSet){
        obj.insert(QString("base_currency"), ::OpenAPI::toJsonValue(base_currency));
    }
	if(m_creation_timestamp_isSet){
        obj.insert(QString("creation_timestamp"), ::OpenAPI::toJsonValue(creation_timestamp));
    }
	if(m_current_funding_isSet){
        obj.insert(QString("current_funding"), ::OpenAPI::toJsonValue(current_funding));
    }
    return obj;
}


QString
OAIBook_summary::getUnderlyingIndex() const {
    return underlying_index;
}
void
OAIBook_summary::setUnderlyingIndex(const QString &underlying_index) {
    this->underlying_index = underlying_index;
    this->m_underlying_index_isSet = true;
}


double
OAIBook_summary::getVolume() const {
    return volume;
}
void
OAIBook_summary::setVolume(const double &volume) {
    this->volume = volume;
    this->m_volume_isSet = true;
}


double
OAIBook_summary::getVolumeUsd() const {
    return volume_usd;
}
void
OAIBook_summary::setVolumeUsd(const double &volume_usd) {
    this->volume_usd = volume_usd;
    this->m_volume_usd_isSet = true;
}


double
OAIBook_summary::getUnderlyingPrice() const {
    return underlying_price;
}
void
OAIBook_summary::setUnderlyingPrice(const double &underlying_price) {
    this->underlying_price = underlying_price;
    this->m_underlying_price_isSet = true;
}


double
OAIBook_summary::getBidPrice() const {
    return bid_price;
}
void
OAIBook_summary::setBidPrice(const double &bid_price) {
    this->bid_price = bid_price;
    this->m_bid_price_isSet = true;
}


double
OAIBook_summary::getOpenInterest() const {
    return open_interest;
}
void
OAIBook_summary::setOpenInterest(const double &open_interest) {
    this->open_interest = open_interest;
    this->m_open_interest_isSet = true;
}


QString
OAIBook_summary::getQuoteCurrency() const {
    return quote_currency;
}
void
OAIBook_summary::setQuoteCurrency(const QString &quote_currency) {
    this->quote_currency = quote_currency;
    this->m_quote_currency_isSet = true;
}


double
OAIBook_summary::getHigh() const {
    return high;
}
void
OAIBook_summary::setHigh(const double &high) {
    this->high = high;
    this->m_high_isSet = true;
}


double
OAIBook_summary::getEstimatedDeliveryPrice() const {
    return estimated_delivery_price;
}
void
OAIBook_summary::setEstimatedDeliveryPrice(const double &estimated_delivery_price) {
    this->estimated_delivery_price = estimated_delivery_price;
    this->m_estimated_delivery_price_isSet = true;
}


double
OAIBook_summary::getLast() const {
    return last;
}
void
OAIBook_summary::setLast(const double &last) {
    this->last = last;
    this->m_last_isSet = true;
}


double
OAIBook_summary::getMidPrice() const {
    return mid_price;
}
void
OAIBook_summary::setMidPrice(const double &mid_price) {
    this->mid_price = mid_price;
    this->m_mid_price_isSet = true;
}


double
OAIBook_summary::getInterestRate() const {
    return interest_rate;
}
void
OAIBook_summary::setInterestRate(const double &interest_rate) {
    this->interest_rate = interest_rate;
    this->m_interest_rate_isSet = true;
}


double
OAIBook_summary::getFunding8h() const {
    return funding_8h;
}
void
OAIBook_summary::setFunding8h(const double &funding_8h) {
    this->funding_8h = funding_8h;
    this->m_funding_8h_isSet = true;
}


double
OAIBook_summary::getMarkPrice() const {
    return mark_price;
}
void
OAIBook_summary::setMarkPrice(const double &mark_price) {
    this->mark_price = mark_price;
    this->m_mark_price_isSet = true;
}


double
OAIBook_summary::getAskPrice() const {
    return ask_price;
}
void
OAIBook_summary::setAskPrice(const double &ask_price) {
    this->ask_price = ask_price;
    this->m_ask_price_isSet = true;
}


QString
OAIBook_summary::getInstrumentName() const {
    return instrument_name;
}
void
OAIBook_summary::setInstrumentName(const QString &instrument_name) {
    this->instrument_name = instrument_name;
    this->m_instrument_name_isSet = true;
}


double
OAIBook_summary::getLow() const {
    return low;
}
void
OAIBook_summary::setLow(const double &low) {
    this->low = low;
    this->m_low_isSet = true;
}


QString
OAIBook_summary::getBaseCurrency() const {
    return base_currency;
}
void
OAIBook_summary::setBaseCurrency(const QString &base_currency) {
    this->base_currency = base_currency;
    this->m_base_currency_isSet = true;
}


qint32
OAIBook_summary::getCreationTimestamp() const {
    return creation_timestamp;
}
void
OAIBook_summary::setCreationTimestamp(const qint32 &creation_timestamp) {
    this->creation_timestamp = creation_timestamp;
    this->m_creation_timestamp_isSet = true;
}


double
OAIBook_summary::getCurrentFunding() const {
    return current_funding;
}
void
OAIBook_summary::setCurrentFunding(const double &current_funding) {
    this->current_funding = current_funding;
    this->m_current_funding_isSet = true;
}

bool
OAIBook_summary::isSet() const {
    bool isObjectUpdated = false;
    do{ 
        if(m_underlying_index_isSet){ isObjectUpdated = true; break;}
    
        if(m_volume_isSet){ isObjectUpdated = true; break;}
    
        if(m_volume_usd_isSet){ isObjectUpdated = true; break;}
    
        if(m_underlying_price_isSet){ isObjectUpdated = true; break;}
    
        if(m_bid_price_isSet){ isObjectUpdated = true; break;}
    
        if(m_open_interest_isSet){ isObjectUpdated = true; break;}
    
        if(m_quote_currency_isSet){ isObjectUpdated = true; break;}
    
        if(m_high_isSet){ isObjectUpdated = true; break;}
    
        if(m_estimated_delivery_price_isSet){ isObjectUpdated = true; break;}
    
        if(m_last_isSet){ isObjectUpdated = true; break;}
    
        if(m_mid_price_isSet){ isObjectUpdated = true; break;}
    
        if(m_interest_rate_isSet){ isObjectUpdated = true; break;}
    
        if(m_funding_8h_isSet){ isObjectUpdated = true; break;}
    
        if(m_mark_price_isSet){ isObjectUpdated = true; break;}
    
        if(m_ask_price_isSet){ isObjectUpdated = true; break;}
    
        if(m_instrument_name_isSet){ isObjectUpdated = true; break;}
    
        if(m_low_isSet){ isObjectUpdated = true; break;}
    
        if(m_base_currency_isSet){ isObjectUpdated = true; break;}
    
        if(m_creation_timestamp_isSet){ isObjectUpdated = true; break;}
    
        if(m_current_funding_isSet){ isObjectUpdated = true; break;}
    }while(false);
    return isObjectUpdated;
}

bool
OAIBook_summary::isValid() const {
    // only required properties are required for the object to be considered valid
    return m_volume_isValid && m_bid_price_isValid && m_open_interest_isValid && m_quote_currency_isValid && m_high_isValid && m_last_isValid && m_mid_price_isValid && m_mark_price_isValid && m_ask_price_isValid && m_instrument_name_isValid && m_low_isValid && m_creation_timestamp_isValid && true;
}

}

