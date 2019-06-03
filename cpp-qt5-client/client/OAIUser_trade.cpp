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


#include "OAIUser_trade.h"

#include "OAIHelpers.h"

#include <QJsonDocument>
#include <QJsonArray>
#include <QObject>
#include <QDebug>

namespace OpenAPI {

OAIUser_trade::OAIUser_trade(QString json) {
    this->init();
    this->fromJson(json);
}

OAIUser_trade::OAIUser_trade() {
    this->init();
}

OAIUser_trade::~OAIUser_trade() {

}

void
OAIUser_trade::init() {
    
    m_direction_isSet = false;
    m_direction_isValid = false;
    
    m_fee_currency_isSet = false;
    m_fee_currency_isValid = false;
    
    m_order_id_isSet = false;
    m_order_id_isValid = false;
    
    m_timestamp_isSet = false;
    m_timestamp_isValid = false;
    
    m_price_isSet = false;
    m_price_isValid = false;
    
    m_iv_isSet = false;
    m_iv_isValid = false;
    
    m_trade_id_isSet = false;
    m_trade_id_isValid = false;
    
    m_fee_isSet = false;
    m_fee_isValid = false;
    
    m_order_type_isSet = false;
    m_order_type_isValid = false;
    
    m_trade_seq_isSet = false;
    m_trade_seq_isValid = false;
    
    m_self_trade_isSet = false;
    m_self_trade_isValid = false;
    
    m_state_isSet = false;
    m_state_isValid = false;
    
    m_label_isSet = false;
    m_label_isValid = false;
    
    m_index_price_isSet = false;
    m_index_price_isValid = false;
    
    m_amount_isSet = false;
    m_amount_isValid = false;
    
    m_instrument_name_isSet = false;
    m_instrument_name_isValid = false;
    
    m_tick_direction_isSet = false;
    m_tick_direction_isValid = false;
    
    m_matching_id_isSet = false;
    m_matching_id_isValid = false;
    
    m_liquidity_isSet = false;
    m_liquidity_isValid = false;
    }

void
OAIUser_trade::fromJson(QString jsonString) {
    QByteArray array (jsonString.toStdString().c_str());
    QJsonDocument doc = QJsonDocument::fromJson(array);
    QJsonObject jsonObject = doc.object();
    this->fromJsonObject(jsonObject);
}

void
OAIUser_trade::fromJsonObject(QJsonObject json) {
    
    m_direction_isValid = ::OpenAPI::fromJsonValue(direction, json[QString("direction")]);
    
    
    m_fee_currency_isValid = ::OpenAPI::fromJsonValue(fee_currency, json[QString("fee_currency")]);
    
    
    m_order_id_isValid = ::OpenAPI::fromJsonValue(order_id, json[QString("order_id")]);
    
    
    m_timestamp_isValid = ::OpenAPI::fromJsonValue(timestamp, json[QString("timestamp")]);
    
    
    m_price_isValid = ::OpenAPI::fromJsonValue(price, json[QString("price")]);
    
    
    m_iv_isValid = ::OpenAPI::fromJsonValue(iv, json[QString("iv")]);
    
    
    m_trade_id_isValid = ::OpenAPI::fromJsonValue(trade_id, json[QString("trade_id")]);
    
    
    m_fee_isValid = ::OpenAPI::fromJsonValue(fee, json[QString("fee")]);
    
    
    m_order_type_isValid = ::OpenAPI::fromJsonValue(order_type, json[QString("order_type")]);
    
    
    m_trade_seq_isValid = ::OpenAPI::fromJsonValue(trade_seq, json[QString("trade_seq")]);
    
    
    m_self_trade_isValid = ::OpenAPI::fromJsonValue(self_trade, json[QString("self_trade")]);
    
    
    m_state_isValid = ::OpenAPI::fromJsonValue(state, json[QString("state")]);
    
    
    m_label_isValid = ::OpenAPI::fromJsonValue(label, json[QString("label")]);
    
    
    m_index_price_isValid = ::OpenAPI::fromJsonValue(index_price, json[QString("index_price")]);
    
    
    m_amount_isValid = ::OpenAPI::fromJsonValue(amount, json[QString("amount")]);
    
    
    m_instrument_name_isValid = ::OpenAPI::fromJsonValue(instrument_name, json[QString("instrument_name")]);
    
    
    m_tick_direction_isValid = ::OpenAPI::fromJsonValue(tick_direction, json[QString("tick_direction")]);
    
    
    m_matching_id_isValid = ::OpenAPI::fromJsonValue(matching_id, json[QString("matching_id")]);
    
    
    m_liquidity_isValid = ::OpenAPI::fromJsonValue(liquidity, json[QString("liquidity")]);
    
    
}

QString
OAIUser_trade::asJson () const {
    QJsonObject obj = this->asJsonObject();
    QJsonDocument doc(obj);
    QByteArray bytes = doc.toJson();
    return QString(bytes);
}

QJsonObject
OAIUser_trade::asJsonObject() const {
    QJsonObject obj;
	if(m_direction_isSet){
        obj.insert(QString("direction"), ::OpenAPI::toJsonValue(direction));
    }
	if(m_fee_currency_isSet){
        obj.insert(QString("fee_currency"), ::OpenAPI::toJsonValue(fee_currency));
    }
	if(m_order_id_isSet){
        obj.insert(QString("order_id"), ::OpenAPI::toJsonValue(order_id));
    }
	if(m_timestamp_isSet){
        obj.insert(QString("timestamp"), ::OpenAPI::toJsonValue(timestamp));
    }
	if(m_price_isSet){
        obj.insert(QString("price"), ::OpenAPI::toJsonValue(price));
    }
	if(m_iv_isSet){
        obj.insert(QString("iv"), ::OpenAPI::toJsonValue(iv));
    }
	if(m_trade_id_isSet){
        obj.insert(QString("trade_id"), ::OpenAPI::toJsonValue(trade_id));
    }
	if(m_fee_isSet){
        obj.insert(QString("fee"), ::OpenAPI::toJsonValue(fee));
    }
	if(m_order_type_isSet){
        obj.insert(QString("order_type"), ::OpenAPI::toJsonValue(order_type));
    }
	if(m_trade_seq_isSet){
        obj.insert(QString("trade_seq"), ::OpenAPI::toJsonValue(trade_seq));
    }
	if(m_self_trade_isSet){
        obj.insert(QString("self_trade"), ::OpenAPI::toJsonValue(self_trade));
    }
	if(m_state_isSet){
        obj.insert(QString("state"), ::OpenAPI::toJsonValue(state));
    }
	if(m_label_isSet){
        obj.insert(QString("label"), ::OpenAPI::toJsonValue(label));
    }
	if(m_index_price_isSet){
        obj.insert(QString("index_price"), ::OpenAPI::toJsonValue(index_price));
    }
	if(m_amount_isSet){
        obj.insert(QString("amount"), ::OpenAPI::toJsonValue(amount));
    }
	if(m_instrument_name_isSet){
        obj.insert(QString("instrument_name"), ::OpenAPI::toJsonValue(instrument_name));
    }
	if(m_tick_direction_isSet){
        obj.insert(QString("tick_direction"), ::OpenAPI::toJsonValue(tick_direction));
    }
	if(m_matching_id_isSet){
        obj.insert(QString("matching_id"), ::OpenAPI::toJsonValue(matching_id));
    }
	if(m_liquidity_isSet){
        obj.insert(QString("liquidity"), ::OpenAPI::toJsonValue(liquidity));
    }
    return obj;
}


QString
OAIUser_trade::getDirection() const {
    return direction;
}
void
OAIUser_trade::setDirection(const QString &direction) {
    this->direction = direction;
    this->m_direction_isSet = true;
}


QString
OAIUser_trade::getFeeCurrency() const {
    return fee_currency;
}
void
OAIUser_trade::setFeeCurrency(const QString &fee_currency) {
    this->fee_currency = fee_currency;
    this->m_fee_currency_isSet = true;
}


QString
OAIUser_trade::getOrderId() const {
    return order_id;
}
void
OAIUser_trade::setOrderId(const QString &order_id) {
    this->order_id = order_id;
    this->m_order_id_isSet = true;
}


qint32
OAIUser_trade::getTimestamp() const {
    return timestamp;
}
void
OAIUser_trade::setTimestamp(const qint32 &timestamp) {
    this->timestamp = timestamp;
    this->m_timestamp_isSet = true;
}


double
OAIUser_trade::getPrice() const {
    return price;
}
void
OAIUser_trade::setPrice(const double &price) {
    this->price = price;
    this->m_price_isSet = true;
}


double
OAIUser_trade::getIv() const {
    return iv;
}
void
OAIUser_trade::setIv(const double &iv) {
    this->iv = iv;
    this->m_iv_isSet = true;
}


QString
OAIUser_trade::getTradeId() const {
    return trade_id;
}
void
OAIUser_trade::setTradeId(const QString &trade_id) {
    this->trade_id = trade_id;
    this->m_trade_id_isSet = true;
}


double
OAIUser_trade::getFee() const {
    return fee;
}
void
OAIUser_trade::setFee(const double &fee) {
    this->fee = fee;
    this->m_fee_isSet = true;
}


QString
OAIUser_trade::getOrderType() const {
    return order_type;
}
void
OAIUser_trade::setOrderType(const QString &order_type) {
    this->order_type = order_type;
    this->m_order_type_isSet = true;
}


qint32
OAIUser_trade::getTradeSeq() const {
    return trade_seq;
}
void
OAIUser_trade::setTradeSeq(const qint32 &trade_seq) {
    this->trade_seq = trade_seq;
    this->m_trade_seq_isSet = true;
}


bool
OAIUser_trade::isSelfTrade() const {
    return self_trade;
}
void
OAIUser_trade::setSelfTrade(const bool &self_trade) {
    this->self_trade = self_trade;
    this->m_self_trade_isSet = true;
}


QString
OAIUser_trade::getState() const {
    return state;
}
void
OAIUser_trade::setState(const QString &state) {
    this->state = state;
    this->m_state_isSet = true;
}


QString
OAIUser_trade::getLabel() const {
    return label;
}
void
OAIUser_trade::setLabel(const QString &label) {
    this->label = label;
    this->m_label_isSet = true;
}


double
OAIUser_trade::getIndexPrice() const {
    return index_price;
}
void
OAIUser_trade::setIndexPrice(const double &index_price) {
    this->index_price = index_price;
    this->m_index_price_isSet = true;
}


double
OAIUser_trade::getAmount() const {
    return amount;
}
void
OAIUser_trade::setAmount(const double &amount) {
    this->amount = amount;
    this->m_amount_isSet = true;
}


QString
OAIUser_trade::getInstrumentName() const {
    return instrument_name;
}
void
OAIUser_trade::setInstrumentName(const QString &instrument_name) {
    this->instrument_name = instrument_name;
    this->m_instrument_name_isSet = true;
}


qint32
OAIUser_trade::getTickDirection() const {
    return tick_direction;
}
void
OAIUser_trade::setTickDirection(const qint32 &tick_direction) {
    this->tick_direction = tick_direction;
    this->m_tick_direction_isSet = true;
}


QString
OAIUser_trade::getMatchingId() const {
    return matching_id;
}
void
OAIUser_trade::setMatchingId(const QString &matching_id) {
    this->matching_id = matching_id;
    this->m_matching_id_isSet = true;
}


QString
OAIUser_trade::getLiquidity() const {
    return liquidity;
}
void
OAIUser_trade::setLiquidity(const QString &liquidity) {
    this->liquidity = liquidity;
    this->m_liquidity_isSet = true;
}

bool
OAIUser_trade::isSet() const {
    bool isObjectUpdated = false;
    do{ 
        if(m_direction_isSet){ isObjectUpdated = true; break;}
    
        if(m_fee_currency_isSet){ isObjectUpdated = true; break;}
    
        if(m_order_id_isSet){ isObjectUpdated = true; break;}
    
        if(m_timestamp_isSet){ isObjectUpdated = true; break;}
    
        if(m_price_isSet){ isObjectUpdated = true; break;}
    
        if(m_iv_isSet){ isObjectUpdated = true; break;}
    
        if(m_trade_id_isSet){ isObjectUpdated = true; break;}
    
        if(m_fee_isSet){ isObjectUpdated = true; break;}
    
        if(m_order_type_isSet){ isObjectUpdated = true; break;}
    
        if(m_trade_seq_isSet){ isObjectUpdated = true; break;}
    
        if(m_self_trade_isSet){ isObjectUpdated = true; break;}
    
        if(m_state_isSet){ isObjectUpdated = true; break;}
    
        if(m_label_isSet){ isObjectUpdated = true; break;}
    
        if(m_index_price_isSet){ isObjectUpdated = true; break;}
    
        if(m_amount_isSet){ isObjectUpdated = true; break;}
    
        if(m_instrument_name_isSet){ isObjectUpdated = true; break;}
    
        if(m_tick_direction_isSet){ isObjectUpdated = true; break;}
    
        if(m_matching_id_isSet){ isObjectUpdated = true; break;}
    
        if(m_liquidity_isSet){ isObjectUpdated = true; break;}
    }while(false);
    return isObjectUpdated;
}

bool
OAIUser_trade::isValid() const {
    // only required properties are required for the object to be considered valid
    return m_direction_isValid && m_fee_currency_isValid && m_order_id_isValid && m_timestamp_isValid && m_price_isValid && m_trade_id_isValid && m_fee_isValid && m_trade_seq_isValid && m_self_trade_isValid && m_state_isValid && m_index_price_isValid && m_amount_isValid && m_instrument_name_isValid && m_tick_direction_isValid && m_matching_id_isValid && true;
}

}

