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


#include "OAIOrder.h"

#include "OAIHelpers.h"

#include <QJsonDocument>
#include <QJsonArray>
#include <QObject>
#include <QDebug>

namespace OpenAPI {

OAIOrder::OAIOrder(QString json) {
    this->init();
    this->fromJson(json);
}

OAIOrder::OAIOrder() {
    this->init();
}

OAIOrder::~OAIOrder() {

}

void
OAIOrder::init() {
    
    m_direction_isSet = false;
    m_direction_isValid = false;
    
    m_reduce_only_isSet = false;
    m_reduce_only_isValid = false;
    
    m_triggered_isSet = false;
    m_triggered_isValid = false;
    
    m_order_id_isSet = false;
    m_order_id_isValid = false;
    
    m_price_isSet = false;
    m_price_isValid = false;
    
    m_time_in_force_isSet = false;
    m_time_in_force_isValid = false;
    
    m_api_isSet = false;
    m_api_isValid = false;
    
    m_order_state_isSet = false;
    m_order_state_isValid = false;
    
    m_implv_isSet = false;
    m_implv_isValid = false;
    
    m_advanced_isSet = false;
    m_advanced_isValid = false;
    
    m_post_only_isSet = false;
    m_post_only_isValid = false;
    
    m_usd_isSet = false;
    m_usd_isValid = false;
    
    m_stop_price_isSet = false;
    m_stop_price_isValid = false;
    
    m_order_type_isSet = false;
    m_order_type_isValid = false;
    
    m_last_update_timestamp_isSet = false;
    m_last_update_timestamp_isValid = false;
    
    m_original_order_type_isSet = false;
    m_original_order_type_isValid = false;
    
    m_max_show_isSet = false;
    m_max_show_isValid = false;
    
    m_profit_loss_isSet = false;
    m_profit_loss_isValid = false;
    
    m_is_liquidation_isSet = false;
    m_is_liquidation_isValid = false;
    
    m_filled_amount_isSet = false;
    m_filled_amount_isValid = false;
    
    m_label_isSet = false;
    m_label_isValid = false;
    
    m_commission_isSet = false;
    m_commission_isValid = false;
    
    m_amount_isSet = false;
    m_amount_isValid = false;
    
    m_trigger_isSet = false;
    m_trigger_isValid = false;
    
    m_instrument_name_isSet = false;
    m_instrument_name_isValid = false;
    
    m_creation_timestamp_isSet = false;
    m_creation_timestamp_isValid = false;
    
    m_average_price_isSet = false;
    m_average_price_isValid = false;
    }

void
OAIOrder::fromJson(QString jsonString) {
    QByteArray array (jsonString.toStdString().c_str());
    QJsonDocument doc = QJsonDocument::fromJson(array);
    QJsonObject jsonObject = doc.object();
    this->fromJsonObject(jsonObject);
}

void
OAIOrder::fromJsonObject(QJsonObject json) {
    
    m_direction_isValid = ::OpenAPI::fromJsonValue(direction, json[QString("direction")]);
    
    
    m_reduce_only_isValid = ::OpenAPI::fromJsonValue(reduce_only, json[QString("reduce_only")]);
    
    
    m_triggered_isValid = ::OpenAPI::fromJsonValue(triggered, json[QString("triggered")]);
    
    
    m_order_id_isValid = ::OpenAPI::fromJsonValue(order_id, json[QString("order_id")]);
    
    
    m_price_isValid = ::OpenAPI::fromJsonValue(price, json[QString("price")]);
    
    
    m_time_in_force_isValid = ::OpenAPI::fromJsonValue(time_in_force, json[QString("time_in_force")]);
    
    
    m_api_isValid = ::OpenAPI::fromJsonValue(api, json[QString("api")]);
    
    
    m_order_state_isValid = ::OpenAPI::fromJsonValue(order_state, json[QString("order_state")]);
    
    
    m_implv_isValid = ::OpenAPI::fromJsonValue(implv, json[QString("implv")]);
    
    
    m_advanced_isValid = ::OpenAPI::fromJsonValue(advanced, json[QString("advanced")]);
    
    
    m_post_only_isValid = ::OpenAPI::fromJsonValue(post_only, json[QString("post_only")]);
    
    
    m_usd_isValid = ::OpenAPI::fromJsonValue(usd, json[QString("usd")]);
    
    
    m_stop_price_isValid = ::OpenAPI::fromJsonValue(stop_price, json[QString("stop_price")]);
    
    
    m_order_type_isValid = ::OpenAPI::fromJsonValue(order_type, json[QString("order_type")]);
    
    
    m_last_update_timestamp_isValid = ::OpenAPI::fromJsonValue(last_update_timestamp, json[QString("last_update_timestamp")]);
    
    
    m_original_order_type_isValid = ::OpenAPI::fromJsonValue(original_order_type, json[QString("original_order_type")]);
    
    
    m_max_show_isValid = ::OpenAPI::fromJsonValue(max_show, json[QString("max_show")]);
    
    
    m_profit_loss_isValid = ::OpenAPI::fromJsonValue(profit_loss, json[QString("profit_loss")]);
    
    
    m_is_liquidation_isValid = ::OpenAPI::fromJsonValue(is_liquidation, json[QString("is_liquidation")]);
    
    
    m_filled_amount_isValid = ::OpenAPI::fromJsonValue(filled_amount, json[QString("filled_amount")]);
    
    
    m_label_isValid = ::OpenAPI::fromJsonValue(label, json[QString("label")]);
    
    
    m_commission_isValid = ::OpenAPI::fromJsonValue(commission, json[QString("commission")]);
    
    
    m_amount_isValid = ::OpenAPI::fromJsonValue(amount, json[QString("amount")]);
    
    
    m_trigger_isValid = ::OpenAPI::fromJsonValue(trigger, json[QString("trigger")]);
    
    
    m_instrument_name_isValid = ::OpenAPI::fromJsonValue(instrument_name, json[QString("instrument_name")]);
    
    
    m_creation_timestamp_isValid = ::OpenAPI::fromJsonValue(creation_timestamp, json[QString("creation_timestamp")]);
    
    
    m_average_price_isValid = ::OpenAPI::fromJsonValue(average_price, json[QString("average_price")]);
    
    
}

QString
OAIOrder::asJson () const {
    QJsonObject obj = this->asJsonObject();
    QJsonDocument doc(obj);
    QByteArray bytes = doc.toJson();
    return QString(bytes);
}

QJsonObject
OAIOrder::asJsonObject() const {
    QJsonObject obj;
	if(m_direction_isSet){
        obj.insert(QString("direction"), ::OpenAPI::toJsonValue(direction));
    }
	if(m_reduce_only_isSet){
        obj.insert(QString("reduce_only"), ::OpenAPI::toJsonValue(reduce_only));
    }
	if(m_triggered_isSet){
        obj.insert(QString("triggered"), ::OpenAPI::toJsonValue(triggered));
    }
	if(m_order_id_isSet){
        obj.insert(QString("order_id"), ::OpenAPI::toJsonValue(order_id));
    }
	if(m_price_isSet){
        obj.insert(QString("price"), ::OpenAPI::toJsonValue(price));
    }
	if(m_time_in_force_isSet){
        obj.insert(QString("time_in_force"), ::OpenAPI::toJsonValue(time_in_force));
    }
	if(m_api_isSet){
        obj.insert(QString("api"), ::OpenAPI::toJsonValue(api));
    }
	if(m_order_state_isSet){
        obj.insert(QString("order_state"), ::OpenAPI::toJsonValue(order_state));
    }
	if(m_implv_isSet){
        obj.insert(QString("implv"), ::OpenAPI::toJsonValue(implv));
    }
	if(m_advanced_isSet){
        obj.insert(QString("advanced"), ::OpenAPI::toJsonValue(advanced));
    }
	if(m_post_only_isSet){
        obj.insert(QString("post_only"), ::OpenAPI::toJsonValue(post_only));
    }
	if(m_usd_isSet){
        obj.insert(QString("usd"), ::OpenAPI::toJsonValue(usd));
    }
	if(m_stop_price_isSet){
        obj.insert(QString("stop_price"), ::OpenAPI::toJsonValue(stop_price));
    }
	if(m_order_type_isSet){
        obj.insert(QString("order_type"), ::OpenAPI::toJsonValue(order_type));
    }
	if(m_last_update_timestamp_isSet){
        obj.insert(QString("last_update_timestamp"), ::OpenAPI::toJsonValue(last_update_timestamp));
    }
	if(m_original_order_type_isSet){
        obj.insert(QString("original_order_type"), ::OpenAPI::toJsonValue(original_order_type));
    }
	if(m_max_show_isSet){
        obj.insert(QString("max_show"), ::OpenAPI::toJsonValue(max_show));
    }
	if(m_profit_loss_isSet){
        obj.insert(QString("profit_loss"), ::OpenAPI::toJsonValue(profit_loss));
    }
	if(m_is_liquidation_isSet){
        obj.insert(QString("is_liquidation"), ::OpenAPI::toJsonValue(is_liquidation));
    }
	if(m_filled_amount_isSet){
        obj.insert(QString("filled_amount"), ::OpenAPI::toJsonValue(filled_amount));
    }
	if(m_label_isSet){
        obj.insert(QString("label"), ::OpenAPI::toJsonValue(label));
    }
	if(m_commission_isSet){
        obj.insert(QString("commission"), ::OpenAPI::toJsonValue(commission));
    }
	if(m_amount_isSet){
        obj.insert(QString("amount"), ::OpenAPI::toJsonValue(amount));
    }
	if(m_trigger_isSet){
        obj.insert(QString("trigger"), ::OpenAPI::toJsonValue(trigger));
    }
	if(m_instrument_name_isSet){
        obj.insert(QString("instrument_name"), ::OpenAPI::toJsonValue(instrument_name));
    }
	if(m_creation_timestamp_isSet){
        obj.insert(QString("creation_timestamp"), ::OpenAPI::toJsonValue(creation_timestamp));
    }
	if(m_average_price_isSet){
        obj.insert(QString("average_price"), ::OpenAPI::toJsonValue(average_price));
    }
    return obj;
}


QString
OAIOrder::getDirection() const {
    return direction;
}
void
OAIOrder::setDirection(const QString &direction) {
    this->direction = direction;
    this->m_direction_isSet = true;
}


bool
OAIOrder::isReduceOnly() const {
    return reduce_only;
}
void
OAIOrder::setReduceOnly(const bool &reduce_only) {
    this->reduce_only = reduce_only;
    this->m_reduce_only_isSet = true;
}


bool
OAIOrder::isTriggered() const {
    return triggered;
}
void
OAIOrder::setTriggered(const bool &triggered) {
    this->triggered = triggered;
    this->m_triggered_isSet = true;
}


QString
OAIOrder::getOrderId() const {
    return order_id;
}
void
OAIOrder::setOrderId(const QString &order_id) {
    this->order_id = order_id;
    this->m_order_id_isSet = true;
}


double
OAIOrder::getPrice() const {
    return price;
}
void
OAIOrder::setPrice(const double &price) {
    this->price = price;
    this->m_price_isSet = true;
}


QString
OAIOrder::getTimeInForce() const {
    return time_in_force;
}
void
OAIOrder::setTimeInForce(const QString &time_in_force) {
    this->time_in_force = time_in_force;
    this->m_time_in_force_isSet = true;
}


bool
OAIOrder::isApi() const {
    return api;
}
void
OAIOrder::setApi(const bool &api) {
    this->api = api;
    this->m_api_isSet = true;
}


QString
OAIOrder::getOrderState() const {
    return order_state;
}
void
OAIOrder::setOrderState(const QString &order_state) {
    this->order_state = order_state;
    this->m_order_state_isSet = true;
}


double
OAIOrder::getImplv() const {
    return implv;
}
void
OAIOrder::setImplv(const double &implv) {
    this->implv = implv;
    this->m_implv_isSet = true;
}


QString
OAIOrder::getAdvanced() const {
    return advanced;
}
void
OAIOrder::setAdvanced(const QString &advanced) {
    this->advanced = advanced;
    this->m_advanced_isSet = true;
}


bool
OAIOrder::isPostOnly() const {
    return post_only;
}
void
OAIOrder::setPostOnly(const bool &post_only) {
    this->post_only = post_only;
    this->m_post_only_isSet = true;
}


double
OAIOrder::getUsd() const {
    return usd;
}
void
OAIOrder::setUsd(const double &usd) {
    this->usd = usd;
    this->m_usd_isSet = true;
}


double
OAIOrder::getStopPrice() const {
    return stop_price;
}
void
OAIOrder::setStopPrice(const double &stop_price) {
    this->stop_price = stop_price;
    this->m_stop_price_isSet = true;
}


QString
OAIOrder::getOrderType() const {
    return order_type;
}
void
OAIOrder::setOrderType(const QString &order_type) {
    this->order_type = order_type;
    this->m_order_type_isSet = true;
}


qint32
OAIOrder::getLastUpdateTimestamp() const {
    return last_update_timestamp;
}
void
OAIOrder::setLastUpdateTimestamp(const qint32 &last_update_timestamp) {
    this->last_update_timestamp = last_update_timestamp;
    this->m_last_update_timestamp_isSet = true;
}


QString
OAIOrder::getOriginalOrderType() const {
    return original_order_type;
}
void
OAIOrder::setOriginalOrderType(const QString &original_order_type) {
    this->original_order_type = original_order_type;
    this->m_original_order_type_isSet = true;
}


double
OAIOrder::getMaxShow() const {
    return max_show;
}
void
OAIOrder::setMaxShow(const double &max_show) {
    this->max_show = max_show;
    this->m_max_show_isSet = true;
}


double
OAIOrder::getProfitLoss() const {
    return profit_loss;
}
void
OAIOrder::setProfitLoss(const double &profit_loss) {
    this->profit_loss = profit_loss;
    this->m_profit_loss_isSet = true;
}


bool
OAIOrder::isIsLiquidation() const {
    return is_liquidation;
}
void
OAIOrder::setIsLiquidation(const bool &is_liquidation) {
    this->is_liquidation = is_liquidation;
    this->m_is_liquidation_isSet = true;
}


double
OAIOrder::getFilledAmount() const {
    return filled_amount;
}
void
OAIOrder::setFilledAmount(const double &filled_amount) {
    this->filled_amount = filled_amount;
    this->m_filled_amount_isSet = true;
}


QString
OAIOrder::getLabel() const {
    return label;
}
void
OAIOrder::setLabel(const QString &label) {
    this->label = label;
    this->m_label_isSet = true;
}


double
OAIOrder::getCommission() const {
    return commission;
}
void
OAIOrder::setCommission(const double &commission) {
    this->commission = commission;
    this->m_commission_isSet = true;
}


double
OAIOrder::getAmount() const {
    return amount;
}
void
OAIOrder::setAmount(const double &amount) {
    this->amount = amount;
    this->m_amount_isSet = true;
}


QString
OAIOrder::getTrigger() const {
    return trigger;
}
void
OAIOrder::setTrigger(const QString &trigger) {
    this->trigger = trigger;
    this->m_trigger_isSet = true;
}


QString
OAIOrder::getInstrumentName() const {
    return instrument_name;
}
void
OAIOrder::setInstrumentName(const QString &instrument_name) {
    this->instrument_name = instrument_name;
    this->m_instrument_name_isSet = true;
}


qint32
OAIOrder::getCreationTimestamp() const {
    return creation_timestamp;
}
void
OAIOrder::setCreationTimestamp(const qint32 &creation_timestamp) {
    this->creation_timestamp = creation_timestamp;
    this->m_creation_timestamp_isSet = true;
}


double
OAIOrder::getAveragePrice() const {
    return average_price;
}
void
OAIOrder::setAveragePrice(const double &average_price) {
    this->average_price = average_price;
    this->m_average_price_isSet = true;
}

bool
OAIOrder::isSet() const {
    bool isObjectUpdated = false;
    do{ 
        if(m_direction_isSet){ isObjectUpdated = true; break;}
    
        if(m_reduce_only_isSet){ isObjectUpdated = true; break;}
    
        if(m_triggered_isSet){ isObjectUpdated = true; break;}
    
        if(m_order_id_isSet){ isObjectUpdated = true; break;}
    
        if(m_price_isSet){ isObjectUpdated = true; break;}
    
        if(m_time_in_force_isSet){ isObjectUpdated = true; break;}
    
        if(m_api_isSet){ isObjectUpdated = true; break;}
    
        if(m_order_state_isSet){ isObjectUpdated = true; break;}
    
        if(m_implv_isSet){ isObjectUpdated = true; break;}
    
        if(m_advanced_isSet){ isObjectUpdated = true; break;}
    
        if(m_post_only_isSet){ isObjectUpdated = true; break;}
    
        if(m_usd_isSet){ isObjectUpdated = true; break;}
    
        if(m_stop_price_isSet){ isObjectUpdated = true; break;}
    
        if(m_order_type_isSet){ isObjectUpdated = true; break;}
    
        if(m_last_update_timestamp_isSet){ isObjectUpdated = true; break;}
    
        if(m_original_order_type_isSet){ isObjectUpdated = true; break;}
    
        if(m_max_show_isSet){ isObjectUpdated = true; break;}
    
        if(m_profit_loss_isSet){ isObjectUpdated = true; break;}
    
        if(m_is_liquidation_isSet){ isObjectUpdated = true; break;}
    
        if(m_filled_amount_isSet){ isObjectUpdated = true; break;}
    
        if(m_label_isSet){ isObjectUpdated = true; break;}
    
        if(m_commission_isSet){ isObjectUpdated = true; break;}
    
        if(m_amount_isSet){ isObjectUpdated = true; break;}
    
        if(m_trigger_isSet){ isObjectUpdated = true; break;}
    
        if(m_instrument_name_isSet){ isObjectUpdated = true; break;}
    
        if(m_creation_timestamp_isSet){ isObjectUpdated = true; break;}
    
        if(m_average_price_isSet){ isObjectUpdated = true; break;}
    }while(false);
    return isObjectUpdated;
}

bool
OAIOrder::isValid() const {
    // only required properties are required for the object to be considered valid
    return m_direction_isValid && m_order_id_isValid && m_price_isValid && m_time_in_force_isValid && m_api_isValid && m_order_state_isValid && m_post_only_isValid && m_order_type_isValid && m_last_update_timestamp_isValid && m_max_show_isValid && m_is_liquidation_isValid && m_label_isValid && m_creation_timestamp_isValid && true;
}

}

