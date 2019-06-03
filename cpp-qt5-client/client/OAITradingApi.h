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

#ifndef OAI_OAITradingApi_H
#define OAI_OAITradingApi_H

#include "OAIHttpRequest.h"

#include "OAIObject.h"
#include <QString>

#include <QObject>

namespace OpenAPI {

class OAITradingApi: public QObject {
    Q_OBJECT

public:
    OAITradingApi();
    OAITradingApi(const QString& host, const QString& basePath);
    ~OAITradingApi();

    void setBasePath(const QString& basePath);
    void setHost(const QString& host);
    void addHeaders(const QString& key, const QString& value);
    
    void privateBuyGet(const QString& instrument_name, const double& amount, const QString& type, const QString& label, const double& price, const QString& time_in_force, const double& max_show, const bool& post_only, const bool& reduce_only, const double& stop_price, const QString& trigger, const QString& advanced);
    void privateCancelAllByCurrencyGet(const QString& currency, const QString& kind, const QString& type);
    void privateCancelAllByInstrumentGet(const QString& instrument_name, const QString& type);
    void privateCancelAllGet();
    void privateCancelGet(const QString& order_id);
    void privateClosePositionGet(const QString& instrument_name, const QString& type, const double& price);
    void privateEditGet(const QString& order_id, const double& amount, const double& price, const bool& post_only, const QString& advanced, const double& stop_price);
    void privateGetMarginsGet(const QString& instrument_name, const double& amount, const double& price);
    void privateGetOpenOrdersByCurrencyGet(const QString& currency, const QString& kind, const QString& type);
    void privateGetOpenOrdersByInstrumentGet(const QString& instrument_name, const QString& type);
    void privateGetOrderHistoryByCurrencyGet(const QString& currency, const QString& kind, const qint32& count, const qint32& offset, const bool& include_old, const bool& include_unfilled);
    void privateGetOrderHistoryByInstrumentGet(const QString& instrument_name, const qint32& count, const qint32& offset, const bool& include_old, const bool& include_unfilled);
    void privateGetOrderMarginByIdsGet(const QList<QString>& ids);
    void privateGetOrderStateGet(const QString& order_id);
    void privateGetSettlementHistoryByCurrencyGet(const QString& currency, const QString& type, const qint32& count);
    void privateGetSettlementHistoryByInstrumentGet(const QString& instrument_name, const QString& type, const qint32& count);
    void privateGetUserTradesByCurrencyAndTimeGet(const QString& currency, const qint32& start_timestamp, const qint32& end_timestamp, const QString& kind, const qint32& count, const bool& include_old, const QString& sorting);
    void privateGetUserTradesByCurrencyGet(const QString& currency, const QString& kind, const QString& start_id, const QString& end_id, const qint32& count, const bool& include_old, const QString& sorting);
    void privateGetUserTradesByInstrumentAndTimeGet(const QString& instrument_name, const qint32& start_timestamp, const qint32& end_timestamp, const qint32& count, const bool& include_old, const QString& sorting);
    void privateGetUserTradesByInstrumentGet(const QString& instrument_name, const qint32& start_seq, const qint32& end_seq, const qint32& count, const bool& include_old, const QString& sorting);
    void privateGetUserTradesByOrderGet(const QString& order_id, const QString& sorting);
    void privateSellGet(const QString& instrument_name, const double& amount, const QString& type, const QString& label, const double& price, const QString& time_in_force, const double& max_show, const bool& post_only, const bool& reduce_only, const double& stop_price, const QString& trigger, const QString& advanced);
    
private:
    QString basePath;
    QString host;
    QMap<QString, QString> defaultHeaders;
    void privateBuyGetCallback (OAIHttpRequestWorker * worker);
    void privateCancelAllByCurrencyGetCallback (OAIHttpRequestWorker * worker);
    void privateCancelAllByInstrumentGetCallback (OAIHttpRequestWorker * worker);
    void privateCancelAllGetCallback (OAIHttpRequestWorker * worker);
    void privateCancelGetCallback (OAIHttpRequestWorker * worker);
    void privateClosePositionGetCallback (OAIHttpRequestWorker * worker);
    void privateEditGetCallback (OAIHttpRequestWorker * worker);
    void privateGetMarginsGetCallback (OAIHttpRequestWorker * worker);
    void privateGetOpenOrdersByCurrencyGetCallback (OAIHttpRequestWorker * worker);
    void privateGetOpenOrdersByInstrumentGetCallback (OAIHttpRequestWorker * worker);
    void privateGetOrderHistoryByCurrencyGetCallback (OAIHttpRequestWorker * worker);
    void privateGetOrderHistoryByInstrumentGetCallback (OAIHttpRequestWorker * worker);
    void privateGetOrderMarginByIdsGetCallback (OAIHttpRequestWorker * worker);
    void privateGetOrderStateGetCallback (OAIHttpRequestWorker * worker);
    void privateGetSettlementHistoryByCurrencyGetCallback (OAIHttpRequestWorker * worker);
    void privateGetSettlementHistoryByInstrumentGetCallback (OAIHttpRequestWorker * worker);
    void privateGetUserTradesByCurrencyAndTimeGetCallback (OAIHttpRequestWorker * worker);
    void privateGetUserTradesByCurrencyGetCallback (OAIHttpRequestWorker * worker);
    void privateGetUserTradesByInstrumentAndTimeGetCallback (OAIHttpRequestWorker * worker);
    void privateGetUserTradesByInstrumentGetCallback (OAIHttpRequestWorker * worker);
    void privateGetUserTradesByOrderGetCallback (OAIHttpRequestWorker * worker);
    void privateSellGetCallback (OAIHttpRequestWorker * worker);
    
signals:
    void privateBuyGetSignal(OAIObject summary);
    void privateCancelAllByCurrencyGetSignal(OAIObject summary);
    void privateCancelAllByInstrumentGetSignal(OAIObject summary);
    void privateCancelAllGetSignal(OAIObject summary);
    void privateCancelGetSignal(OAIObject summary);
    void privateClosePositionGetSignal(OAIObject summary);
    void privateEditGetSignal(OAIObject summary);
    void privateGetMarginsGetSignal(OAIObject summary);
    void privateGetOpenOrdersByCurrencyGetSignal(OAIObject summary);
    void privateGetOpenOrdersByInstrumentGetSignal(OAIObject summary);
    void privateGetOrderHistoryByCurrencyGetSignal(OAIObject summary);
    void privateGetOrderHistoryByInstrumentGetSignal(OAIObject summary);
    void privateGetOrderMarginByIdsGetSignal(OAIObject summary);
    void privateGetOrderStateGetSignal(OAIObject summary);
    void privateGetSettlementHistoryByCurrencyGetSignal(OAIObject summary);
    void privateGetSettlementHistoryByInstrumentGetSignal(OAIObject summary);
    void privateGetUserTradesByCurrencyAndTimeGetSignal(OAIObject summary);
    void privateGetUserTradesByCurrencyGetSignal(OAIObject summary);
    void privateGetUserTradesByInstrumentAndTimeGetSignal(OAIObject summary);
    void privateGetUserTradesByInstrumentGetSignal(OAIObject summary);
    void privateGetUserTradesByOrderGetSignal(OAIObject summary);
    void privateSellGetSignal(OAIObject summary);
    
    void privateBuyGetSignalFull(OAIHttpRequestWorker* worker, OAIObject summary);
    void privateCancelAllByCurrencyGetSignalFull(OAIHttpRequestWorker* worker, OAIObject summary);
    void privateCancelAllByInstrumentGetSignalFull(OAIHttpRequestWorker* worker, OAIObject summary);
    void privateCancelAllGetSignalFull(OAIHttpRequestWorker* worker, OAIObject summary);
    void privateCancelGetSignalFull(OAIHttpRequestWorker* worker, OAIObject summary);
    void privateClosePositionGetSignalFull(OAIHttpRequestWorker* worker, OAIObject summary);
    void privateEditGetSignalFull(OAIHttpRequestWorker* worker, OAIObject summary);
    void privateGetMarginsGetSignalFull(OAIHttpRequestWorker* worker, OAIObject summary);
    void privateGetOpenOrdersByCurrencyGetSignalFull(OAIHttpRequestWorker* worker, OAIObject summary);
    void privateGetOpenOrdersByInstrumentGetSignalFull(OAIHttpRequestWorker* worker, OAIObject summary);
    void privateGetOrderHistoryByCurrencyGetSignalFull(OAIHttpRequestWorker* worker, OAIObject summary);
    void privateGetOrderHistoryByInstrumentGetSignalFull(OAIHttpRequestWorker* worker, OAIObject summary);
    void privateGetOrderMarginByIdsGetSignalFull(OAIHttpRequestWorker* worker, OAIObject summary);
    void privateGetOrderStateGetSignalFull(OAIHttpRequestWorker* worker, OAIObject summary);
    void privateGetSettlementHistoryByCurrencyGetSignalFull(OAIHttpRequestWorker* worker, OAIObject summary);
    void privateGetSettlementHistoryByInstrumentGetSignalFull(OAIHttpRequestWorker* worker, OAIObject summary);
    void privateGetUserTradesByCurrencyAndTimeGetSignalFull(OAIHttpRequestWorker* worker, OAIObject summary);
    void privateGetUserTradesByCurrencyGetSignalFull(OAIHttpRequestWorker* worker, OAIObject summary);
    void privateGetUserTradesByInstrumentAndTimeGetSignalFull(OAIHttpRequestWorker* worker, OAIObject summary);
    void privateGetUserTradesByInstrumentGetSignalFull(OAIHttpRequestWorker* worker, OAIObject summary);
    void privateGetUserTradesByOrderGetSignalFull(OAIHttpRequestWorker* worker, OAIObject summary);
    void privateSellGetSignalFull(OAIHttpRequestWorker* worker, OAIObject summary);
    
    void privateBuyGetSignalE(OAIObject summary, QNetworkReply::NetworkError error_type, QString& error_str);
    void privateCancelAllByCurrencyGetSignalE(OAIObject summary, QNetworkReply::NetworkError error_type, QString& error_str);
    void privateCancelAllByInstrumentGetSignalE(OAIObject summary, QNetworkReply::NetworkError error_type, QString& error_str);
    void privateCancelAllGetSignalE(OAIObject summary, QNetworkReply::NetworkError error_type, QString& error_str);
    void privateCancelGetSignalE(OAIObject summary, QNetworkReply::NetworkError error_type, QString& error_str);
    void privateClosePositionGetSignalE(OAIObject summary, QNetworkReply::NetworkError error_type, QString& error_str);
    void privateEditGetSignalE(OAIObject summary, QNetworkReply::NetworkError error_type, QString& error_str);
    void privateGetMarginsGetSignalE(OAIObject summary, QNetworkReply::NetworkError error_type, QString& error_str);
    void privateGetOpenOrdersByCurrencyGetSignalE(OAIObject summary, QNetworkReply::NetworkError error_type, QString& error_str);
    void privateGetOpenOrdersByInstrumentGetSignalE(OAIObject summary, QNetworkReply::NetworkError error_type, QString& error_str);
    void privateGetOrderHistoryByCurrencyGetSignalE(OAIObject summary, QNetworkReply::NetworkError error_type, QString& error_str);
    void privateGetOrderHistoryByInstrumentGetSignalE(OAIObject summary, QNetworkReply::NetworkError error_type, QString& error_str);
    void privateGetOrderMarginByIdsGetSignalE(OAIObject summary, QNetworkReply::NetworkError error_type, QString& error_str);
    void privateGetOrderStateGetSignalE(OAIObject summary, QNetworkReply::NetworkError error_type, QString& error_str);
    void privateGetSettlementHistoryByCurrencyGetSignalE(OAIObject summary, QNetworkReply::NetworkError error_type, QString& error_str);
    void privateGetSettlementHistoryByInstrumentGetSignalE(OAIObject summary, QNetworkReply::NetworkError error_type, QString& error_str);
    void privateGetUserTradesByCurrencyAndTimeGetSignalE(OAIObject summary, QNetworkReply::NetworkError error_type, QString& error_str);
    void privateGetUserTradesByCurrencyGetSignalE(OAIObject summary, QNetworkReply::NetworkError error_type, QString& error_str);
    void privateGetUserTradesByInstrumentAndTimeGetSignalE(OAIObject summary, QNetworkReply::NetworkError error_type, QString& error_str);
    void privateGetUserTradesByInstrumentGetSignalE(OAIObject summary, QNetworkReply::NetworkError error_type, QString& error_str);
    void privateGetUserTradesByOrderGetSignalE(OAIObject summary, QNetworkReply::NetworkError error_type, QString& error_str);
    void privateSellGetSignalE(OAIObject summary, QNetworkReply::NetworkError error_type, QString& error_str);
    
    void privateBuyGetSignalEFull(OAIHttpRequestWorker* worker, QNetworkReply::NetworkError error_type, QString& error_str);
    void privateCancelAllByCurrencyGetSignalEFull(OAIHttpRequestWorker* worker, QNetworkReply::NetworkError error_type, QString& error_str);
    void privateCancelAllByInstrumentGetSignalEFull(OAIHttpRequestWorker* worker, QNetworkReply::NetworkError error_type, QString& error_str);
    void privateCancelAllGetSignalEFull(OAIHttpRequestWorker* worker, QNetworkReply::NetworkError error_type, QString& error_str);
    void privateCancelGetSignalEFull(OAIHttpRequestWorker* worker, QNetworkReply::NetworkError error_type, QString& error_str);
    void privateClosePositionGetSignalEFull(OAIHttpRequestWorker* worker, QNetworkReply::NetworkError error_type, QString& error_str);
    void privateEditGetSignalEFull(OAIHttpRequestWorker* worker, QNetworkReply::NetworkError error_type, QString& error_str);
    void privateGetMarginsGetSignalEFull(OAIHttpRequestWorker* worker, QNetworkReply::NetworkError error_type, QString& error_str);
    void privateGetOpenOrdersByCurrencyGetSignalEFull(OAIHttpRequestWorker* worker, QNetworkReply::NetworkError error_type, QString& error_str);
    void privateGetOpenOrdersByInstrumentGetSignalEFull(OAIHttpRequestWorker* worker, QNetworkReply::NetworkError error_type, QString& error_str);
    void privateGetOrderHistoryByCurrencyGetSignalEFull(OAIHttpRequestWorker* worker, QNetworkReply::NetworkError error_type, QString& error_str);
    void privateGetOrderHistoryByInstrumentGetSignalEFull(OAIHttpRequestWorker* worker, QNetworkReply::NetworkError error_type, QString& error_str);
    void privateGetOrderMarginByIdsGetSignalEFull(OAIHttpRequestWorker* worker, QNetworkReply::NetworkError error_type, QString& error_str);
    void privateGetOrderStateGetSignalEFull(OAIHttpRequestWorker* worker, QNetworkReply::NetworkError error_type, QString& error_str);
    void privateGetSettlementHistoryByCurrencyGetSignalEFull(OAIHttpRequestWorker* worker, QNetworkReply::NetworkError error_type, QString& error_str);
    void privateGetSettlementHistoryByInstrumentGetSignalEFull(OAIHttpRequestWorker* worker, QNetworkReply::NetworkError error_type, QString& error_str);
    void privateGetUserTradesByCurrencyAndTimeGetSignalEFull(OAIHttpRequestWorker* worker, QNetworkReply::NetworkError error_type, QString& error_str);
    void privateGetUserTradesByCurrencyGetSignalEFull(OAIHttpRequestWorker* worker, QNetworkReply::NetworkError error_type, QString& error_str);
    void privateGetUserTradesByInstrumentAndTimeGetSignalEFull(OAIHttpRequestWorker* worker, QNetworkReply::NetworkError error_type, QString& error_str);
    void privateGetUserTradesByInstrumentGetSignalEFull(OAIHttpRequestWorker* worker, QNetworkReply::NetworkError error_type, QString& error_str);
    void privateGetUserTradesByOrderGetSignalEFull(OAIHttpRequestWorker* worker, QNetworkReply::NetworkError error_type, QString& error_str);
    void privateSellGetSignalEFull(OAIHttpRequestWorker* worker, QNetworkReply::NetworkError error_type, QString& error_str);
    
};

}
#endif
