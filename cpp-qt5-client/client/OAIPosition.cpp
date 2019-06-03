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


#include "OAIPosition.h"

#include "OAIHelpers.h"

#include <QJsonDocument>
#include <QJsonArray>
#include <QObject>
#include <QDebug>

namespace OpenAPI {

OAIPosition::OAIPosition(QString json) {
    this->init();
    this->fromJson(json);
}

OAIPosition::OAIPosition() {
    this->init();
}

OAIPosition::~OAIPosition() {

}

void
OAIPosition::init() {
    
    m_direction_isSet = false;
    m_direction_isValid = false;
    
    m_average_price_usd_isSet = false;
    m_average_price_usd_isValid = false;
    
    m_estimated_liquidation_price_isSet = false;
    m_estimated_liquidation_price_isValid = false;
    
    m_floating_profit_loss_isSet = false;
    m_floating_profit_loss_isValid = false;
    
    m_floating_profit_loss_usd_isSet = false;
    m_floating_profit_loss_usd_isValid = false;
    
    m_open_orders_margin_isSet = false;
    m_open_orders_margin_isValid = false;
    
    m_total_profit_loss_isSet = false;
    m_total_profit_loss_isValid = false;
    
    m_realized_profit_loss_isSet = false;
    m_realized_profit_loss_isValid = false;
    
    m_delta_isSet = false;
    m_delta_isValid = false;
    
    m_initial_margin_isSet = false;
    m_initial_margin_isValid = false;
    
    m_size_isSet = false;
    m_size_isValid = false;
    
    m_maintenance_margin_isSet = false;
    m_maintenance_margin_isValid = false;
    
    m_kind_isSet = false;
    m_kind_isValid = false;
    
    m_mark_price_isSet = false;
    m_mark_price_isValid = false;
    
    m_average_price_isSet = false;
    m_average_price_isValid = false;
    
    m_settlement_price_isSet = false;
    m_settlement_price_isValid = false;
    
    m_index_price_isSet = false;
    m_index_price_isValid = false;
    
    m_instrument_name_isSet = false;
    m_instrument_name_isValid = false;
    
    m_size_currency_isSet = false;
    m_size_currency_isValid = false;
    }

void
OAIPosition::fromJson(QString jsonString) {
    QByteArray array (jsonString.toStdString().c_str());
    QJsonDocument doc = QJsonDocument::fromJson(array);
    QJsonObject jsonObject = doc.object();
    this->fromJsonObject(jsonObject);
}

void
OAIPosition::fromJsonObject(QJsonObject json) {
    
    m_direction_isValid = ::OpenAPI::fromJsonValue(direction, json[QString("direction")]);
    
    
    m_average_price_usd_isValid = ::OpenAPI::fromJsonValue(average_price_usd, json[QString("average_price_usd")]);
    
    
    m_estimated_liquidation_price_isValid = ::OpenAPI::fromJsonValue(estimated_liquidation_price, json[QString("estimated_liquidation_price")]);
    
    
    m_floating_profit_loss_isValid = ::OpenAPI::fromJsonValue(floating_profit_loss, json[QString("floating_profit_loss")]);
    
    
    m_floating_profit_loss_usd_isValid = ::OpenAPI::fromJsonValue(floating_profit_loss_usd, json[QString("floating_profit_loss_usd")]);
    
    
    m_open_orders_margin_isValid = ::OpenAPI::fromJsonValue(open_orders_margin, json[QString("open_orders_margin")]);
    
    
    m_total_profit_loss_isValid = ::OpenAPI::fromJsonValue(total_profit_loss, json[QString("total_profit_loss")]);
    
    
    m_realized_profit_loss_isValid = ::OpenAPI::fromJsonValue(realized_profit_loss, json[QString("realized_profit_loss")]);
    
    
    m_delta_isValid = ::OpenAPI::fromJsonValue(delta, json[QString("delta")]);
    
    
    m_initial_margin_isValid = ::OpenAPI::fromJsonValue(initial_margin, json[QString("initial_margin")]);
    
    
    m_size_isValid = ::OpenAPI::fromJsonValue(size, json[QString("size")]);
    
    
    m_maintenance_margin_isValid = ::OpenAPI::fromJsonValue(maintenance_margin, json[QString("maintenance_margin")]);
    
    
    m_kind_isValid = ::OpenAPI::fromJsonValue(kind, json[QString("kind")]);
    
    
    m_mark_price_isValid = ::OpenAPI::fromJsonValue(mark_price, json[QString("mark_price")]);
    
    
    m_average_price_isValid = ::OpenAPI::fromJsonValue(average_price, json[QString("average_price")]);
    
    
    m_settlement_price_isValid = ::OpenAPI::fromJsonValue(settlement_price, json[QString("settlement_price")]);
    
    
    m_index_price_isValid = ::OpenAPI::fromJsonValue(index_price, json[QString("index_price")]);
    
    
    m_instrument_name_isValid = ::OpenAPI::fromJsonValue(instrument_name, json[QString("instrument_name")]);
    
    
    m_size_currency_isValid = ::OpenAPI::fromJsonValue(size_currency, json[QString("size_currency")]);
    
    
}

QString
OAIPosition::asJson () const {
    QJsonObject obj = this->asJsonObject();
    QJsonDocument doc(obj);
    QByteArray bytes = doc.toJson();
    return QString(bytes);
}

QJsonObject
OAIPosition::asJsonObject() const {
    QJsonObject obj;
	if(m_direction_isSet){
        obj.insert(QString("direction"), ::OpenAPI::toJsonValue(direction));
    }
	if(m_average_price_usd_isSet){
        obj.insert(QString("average_price_usd"), ::OpenAPI::toJsonValue(average_price_usd));
    }
	if(m_estimated_liquidation_price_isSet){
        obj.insert(QString("estimated_liquidation_price"), ::OpenAPI::toJsonValue(estimated_liquidation_price));
    }
	if(m_floating_profit_loss_isSet){
        obj.insert(QString("floating_profit_loss"), ::OpenAPI::toJsonValue(floating_profit_loss));
    }
	if(m_floating_profit_loss_usd_isSet){
        obj.insert(QString("floating_profit_loss_usd"), ::OpenAPI::toJsonValue(floating_profit_loss_usd));
    }
	if(m_open_orders_margin_isSet){
        obj.insert(QString("open_orders_margin"), ::OpenAPI::toJsonValue(open_orders_margin));
    }
	if(m_total_profit_loss_isSet){
        obj.insert(QString("total_profit_loss"), ::OpenAPI::toJsonValue(total_profit_loss));
    }
	if(m_realized_profit_loss_isSet){
        obj.insert(QString("realized_profit_loss"), ::OpenAPI::toJsonValue(realized_profit_loss));
    }
	if(m_delta_isSet){
        obj.insert(QString("delta"), ::OpenAPI::toJsonValue(delta));
    }
	if(m_initial_margin_isSet){
        obj.insert(QString("initial_margin"), ::OpenAPI::toJsonValue(initial_margin));
    }
	if(m_size_isSet){
        obj.insert(QString("size"), ::OpenAPI::toJsonValue(size));
    }
	if(m_maintenance_margin_isSet){
        obj.insert(QString("maintenance_margin"), ::OpenAPI::toJsonValue(maintenance_margin));
    }
	if(m_kind_isSet){
        obj.insert(QString("kind"), ::OpenAPI::toJsonValue(kind));
    }
	if(m_mark_price_isSet){
        obj.insert(QString("mark_price"), ::OpenAPI::toJsonValue(mark_price));
    }
	if(m_average_price_isSet){
        obj.insert(QString("average_price"), ::OpenAPI::toJsonValue(average_price));
    }
	if(m_settlement_price_isSet){
        obj.insert(QString("settlement_price"), ::OpenAPI::toJsonValue(settlement_price));
    }
	if(m_index_price_isSet){
        obj.insert(QString("index_price"), ::OpenAPI::toJsonValue(index_price));
    }
	if(m_instrument_name_isSet){
        obj.insert(QString("instrument_name"), ::OpenAPI::toJsonValue(instrument_name));
    }
	if(m_size_currency_isSet){
        obj.insert(QString("size_currency"), ::OpenAPI::toJsonValue(size_currency));
    }
    return obj;
}


QString
OAIPosition::getDirection() const {
    return direction;
}
void
OAIPosition::setDirection(const QString &direction) {
    this->direction = direction;
    this->m_direction_isSet = true;
}


double
OAIPosition::getAveragePriceUsd() const {
    return average_price_usd;
}
void
OAIPosition::setAveragePriceUsd(const double &average_price_usd) {
    this->average_price_usd = average_price_usd;
    this->m_average_price_usd_isSet = true;
}


double
OAIPosition::getEstimatedLiquidationPrice() const {
    return estimated_liquidation_price;
}
void
OAIPosition::setEstimatedLiquidationPrice(const double &estimated_liquidation_price) {
    this->estimated_liquidation_price = estimated_liquidation_price;
    this->m_estimated_liquidation_price_isSet = true;
}


double
OAIPosition::getFloatingProfitLoss() const {
    return floating_profit_loss;
}
void
OAIPosition::setFloatingProfitLoss(const double &floating_profit_loss) {
    this->floating_profit_loss = floating_profit_loss;
    this->m_floating_profit_loss_isSet = true;
}


double
OAIPosition::getFloatingProfitLossUsd() const {
    return floating_profit_loss_usd;
}
void
OAIPosition::setFloatingProfitLossUsd(const double &floating_profit_loss_usd) {
    this->floating_profit_loss_usd = floating_profit_loss_usd;
    this->m_floating_profit_loss_usd_isSet = true;
}


double
OAIPosition::getOpenOrdersMargin() const {
    return open_orders_margin;
}
void
OAIPosition::setOpenOrdersMargin(const double &open_orders_margin) {
    this->open_orders_margin = open_orders_margin;
    this->m_open_orders_margin_isSet = true;
}


double
OAIPosition::getTotalProfitLoss() const {
    return total_profit_loss;
}
void
OAIPosition::setTotalProfitLoss(const double &total_profit_loss) {
    this->total_profit_loss = total_profit_loss;
    this->m_total_profit_loss_isSet = true;
}


double
OAIPosition::getRealizedProfitLoss() const {
    return realized_profit_loss;
}
void
OAIPosition::setRealizedProfitLoss(const double &realized_profit_loss) {
    this->realized_profit_loss = realized_profit_loss;
    this->m_realized_profit_loss_isSet = true;
}


double
OAIPosition::getDelta() const {
    return delta;
}
void
OAIPosition::setDelta(const double &delta) {
    this->delta = delta;
    this->m_delta_isSet = true;
}


double
OAIPosition::getInitialMargin() const {
    return initial_margin;
}
void
OAIPosition::setInitialMargin(const double &initial_margin) {
    this->initial_margin = initial_margin;
    this->m_initial_margin_isSet = true;
}


double
OAIPosition::getSize() const {
    return size;
}
void
OAIPosition::setSize(const double &size) {
    this->size = size;
    this->m_size_isSet = true;
}


double
OAIPosition::getMaintenanceMargin() const {
    return maintenance_margin;
}
void
OAIPosition::setMaintenanceMargin(const double &maintenance_margin) {
    this->maintenance_margin = maintenance_margin;
    this->m_maintenance_margin_isSet = true;
}


QString
OAIPosition::getKind() const {
    return kind;
}
void
OAIPosition::setKind(const QString &kind) {
    this->kind = kind;
    this->m_kind_isSet = true;
}


double
OAIPosition::getMarkPrice() const {
    return mark_price;
}
void
OAIPosition::setMarkPrice(const double &mark_price) {
    this->mark_price = mark_price;
    this->m_mark_price_isSet = true;
}


double
OAIPosition::getAveragePrice() const {
    return average_price;
}
void
OAIPosition::setAveragePrice(const double &average_price) {
    this->average_price = average_price;
    this->m_average_price_isSet = true;
}


double
OAIPosition::getSettlementPrice() const {
    return settlement_price;
}
void
OAIPosition::setSettlementPrice(const double &settlement_price) {
    this->settlement_price = settlement_price;
    this->m_settlement_price_isSet = true;
}


double
OAIPosition::getIndexPrice() const {
    return index_price;
}
void
OAIPosition::setIndexPrice(const double &index_price) {
    this->index_price = index_price;
    this->m_index_price_isSet = true;
}


QString
OAIPosition::getInstrumentName() const {
    return instrument_name;
}
void
OAIPosition::setInstrumentName(const QString &instrument_name) {
    this->instrument_name = instrument_name;
    this->m_instrument_name_isSet = true;
}


double
OAIPosition::getSizeCurrency() const {
    return size_currency;
}
void
OAIPosition::setSizeCurrency(const double &size_currency) {
    this->size_currency = size_currency;
    this->m_size_currency_isSet = true;
}

bool
OAIPosition::isSet() const {
    bool isObjectUpdated = false;
    do{ 
        if(m_direction_isSet){ isObjectUpdated = true; break;}
    
        if(m_average_price_usd_isSet){ isObjectUpdated = true; break;}
    
        if(m_estimated_liquidation_price_isSet){ isObjectUpdated = true; break;}
    
        if(m_floating_profit_loss_isSet){ isObjectUpdated = true; break;}
    
        if(m_floating_profit_loss_usd_isSet){ isObjectUpdated = true; break;}
    
        if(m_open_orders_margin_isSet){ isObjectUpdated = true; break;}
    
        if(m_total_profit_loss_isSet){ isObjectUpdated = true; break;}
    
        if(m_realized_profit_loss_isSet){ isObjectUpdated = true; break;}
    
        if(m_delta_isSet){ isObjectUpdated = true; break;}
    
        if(m_initial_margin_isSet){ isObjectUpdated = true; break;}
    
        if(m_size_isSet){ isObjectUpdated = true; break;}
    
        if(m_maintenance_margin_isSet){ isObjectUpdated = true; break;}
    
        if(m_kind_isSet){ isObjectUpdated = true; break;}
    
        if(m_mark_price_isSet){ isObjectUpdated = true; break;}
    
        if(m_average_price_isSet){ isObjectUpdated = true; break;}
    
        if(m_settlement_price_isSet){ isObjectUpdated = true; break;}
    
        if(m_index_price_isSet){ isObjectUpdated = true; break;}
    
        if(m_instrument_name_isSet){ isObjectUpdated = true; break;}
    
        if(m_size_currency_isSet){ isObjectUpdated = true; break;}
    }while(false);
    return isObjectUpdated;
}

bool
OAIPosition::isValid() const {
    // only required properties are required for the object to be considered valid
    return m_direction_isValid && m_floating_profit_loss_isValid && m_open_orders_margin_isValid && m_total_profit_loss_isValid && m_delta_isValid && m_initial_margin_isValid && m_size_isValid && m_maintenance_margin_isValid && m_kind_isValid && m_mark_price_isValid && m_average_price_isValid && m_settlement_price_isValid && m_index_price_isValid && m_instrument_name_isValid && true;
}

}

