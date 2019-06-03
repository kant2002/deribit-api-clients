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

#include "OAIPrivateApi.h"
#include "OAIHelpers.h"

#include <QJsonArray>
#include <QJsonDocument>

namespace OpenAPI {

OAIPrivateApi::OAIPrivateApi() : basePath("/api/v2"),
    host("www.deribit.com") {

}

OAIPrivateApi::~OAIPrivateApi() {

}

OAIPrivateApi::OAIPrivateApi(const QString& host, const QString& basePath) {
    this->host = host;
    this->basePath = basePath;
}

void OAIPrivateApi::setBasePath(const QString& basePath){
    this->basePath = basePath;
}

void OAIPrivateApi::setHost(const QString& host){
    this->host = host;
}

void OAIPrivateApi::addHeaders(const QString& key, const QString& value){
    defaultHeaders.insert(key, value);
}


void
OAIPrivateApi::privateAddToAddressBookGet(const QString& currency, const QString& type, const QString& address, const QString& name, const QString& tfa) {
    QString fullPath;
    fullPath.append(this->host).append(this->basePath).append("/private/add_to_address_book");
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("currency"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(currency)));
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("type"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(type)));
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("address"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(address)));
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("name"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(name)));
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("tfa"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(tfa)));
    
    OAIHttpRequestWorker *worker = new OAIHttpRequestWorker();
    OAIHttpRequestInput input(fullPath, "GET");


    foreach(QString key, this->defaultHeaders.keys()) {
        input.headers.insert(key, this->defaultHeaders.value(key));
    }

    connect(worker,
            &OAIHttpRequestWorker::on_execution_finished,
            this,
            &OAIPrivateApi::privateAddToAddressBookGetCallback);

    worker->execute(&input);
}

void
OAIPrivateApi::privateAddToAddressBookGetCallback(OAIHttpRequestWorker * worker) {
    QString msg;
    QString error_str = worker->error_str;
    QNetworkReply::NetworkError error_type = worker->error_type;

    if (worker->error_type == QNetworkReply::NoError) {
        msg = QString("Success! %1 bytes").arg(worker->response.length());
    }
    else {
        msg = "Error: " + worker->error_str;
    }
    OAIObject output(QString(worker->response));
    worker->deleteLater();

    if (worker->error_type == QNetworkReply::NoError) {
        emit privateAddToAddressBookGetSignal(output);
        emit privateAddToAddressBookGetSignalFull(worker, output);
    } else {
        emit privateAddToAddressBookGetSignalE(output, error_type, error_str);
        emit privateAddToAddressBookGetSignalEFull(worker, error_type, error_str);
    }
}

void
OAIPrivateApi::privateBuyGet(const QString& instrument_name, const double& amount, const QString& type, const QString& label, const double& price, const QString& time_in_force, const double& max_show, const bool& post_only, const bool& reduce_only, const double& stop_price, const QString& trigger, const QString& advanced) {
    QString fullPath;
    fullPath.append(this->host).append(this->basePath).append("/private/buy");
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("instrument_name"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(instrument_name)));
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("amount"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(amount)));
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("type"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(type)));
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("label"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(label)));
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("price"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(price)));
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("time_in_force"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(time_in_force)));
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("max_show"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(max_show)));
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("post_only"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(post_only)));
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("reduce_only"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(reduce_only)));
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("stop_price"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(stop_price)));
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("trigger"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(trigger)));
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("advanced"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(advanced)));
    
    OAIHttpRequestWorker *worker = new OAIHttpRequestWorker();
    OAIHttpRequestInput input(fullPath, "GET");


    foreach(QString key, this->defaultHeaders.keys()) {
        input.headers.insert(key, this->defaultHeaders.value(key));
    }

    connect(worker,
            &OAIHttpRequestWorker::on_execution_finished,
            this,
            &OAIPrivateApi::privateBuyGetCallback);

    worker->execute(&input);
}

void
OAIPrivateApi::privateBuyGetCallback(OAIHttpRequestWorker * worker) {
    QString msg;
    QString error_str = worker->error_str;
    QNetworkReply::NetworkError error_type = worker->error_type;

    if (worker->error_type == QNetworkReply::NoError) {
        msg = QString("Success! %1 bytes").arg(worker->response.length());
    }
    else {
        msg = "Error: " + worker->error_str;
    }
    OAIObject output(QString(worker->response));
    worker->deleteLater();

    if (worker->error_type == QNetworkReply::NoError) {
        emit privateBuyGetSignal(output);
        emit privateBuyGetSignalFull(worker, output);
    } else {
        emit privateBuyGetSignalE(output, error_type, error_str);
        emit privateBuyGetSignalEFull(worker, error_type, error_str);
    }
}

void
OAIPrivateApi::privateCancelAllByCurrencyGet(const QString& currency, const QString& kind, const QString& type) {
    QString fullPath;
    fullPath.append(this->host).append(this->basePath).append("/private/cancel_all_by_currency");
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("currency"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(currency)));
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("kind"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(kind)));
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("type"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(type)));
    
    OAIHttpRequestWorker *worker = new OAIHttpRequestWorker();
    OAIHttpRequestInput input(fullPath, "GET");


    foreach(QString key, this->defaultHeaders.keys()) {
        input.headers.insert(key, this->defaultHeaders.value(key));
    }

    connect(worker,
            &OAIHttpRequestWorker::on_execution_finished,
            this,
            &OAIPrivateApi::privateCancelAllByCurrencyGetCallback);

    worker->execute(&input);
}

void
OAIPrivateApi::privateCancelAllByCurrencyGetCallback(OAIHttpRequestWorker * worker) {
    QString msg;
    QString error_str = worker->error_str;
    QNetworkReply::NetworkError error_type = worker->error_type;

    if (worker->error_type == QNetworkReply::NoError) {
        msg = QString("Success! %1 bytes").arg(worker->response.length());
    }
    else {
        msg = "Error: " + worker->error_str;
    }
    OAIObject output(QString(worker->response));
    worker->deleteLater();

    if (worker->error_type == QNetworkReply::NoError) {
        emit privateCancelAllByCurrencyGetSignal(output);
        emit privateCancelAllByCurrencyGetSignalFull(worker, output);
    } else {
        emit privateCancelAllByCurrencyGetSignalE(output, error_type, error_str);
        emit privateCancelAllByCurrencyGetSignalEFull(worker, error_type, error_str);
    }
}

void
OAIPrivateApi::privateCancelAllByInstrumentGet(const QString& instrument_name, const QString& type) {
    QString fullPath;
    fullPath.append(this->host).append(this->basePath).append("/private/cancel_all_by_instrument");
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("instrument_name"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(instrument_name)));
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("type"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(type)));
    
    OAIHttpRequestWorker *worker = new OAIHttpRequestWorker();
    OAIHttpRequestInput input(fullPath, "GET");


    foreach(QString key, this->defaultHeaders.keys()) {
        input.headers.insert(key, this->defaultHeaders.value(key));
    }

    connect(worker,
            &OAIHttpRequestWorker::on_execution_finished,
            this,
            &OAIPrivateApi::privateCancelAllByInstrumentGetCallback);

    worker->execute(&input);
}

void
OAIPrivateApi::privateCancelAllByInstrumentGetCallback(OAIHttpRequestWorker * worker) {
    QString msg;
    QString error_str = worker->error_str;
    QNetworkReply::NetworkError error_type = worker->error_type;

    if (worker->error_type == QNetworkReply::NoError) {
        msg = QString("Success! %1 bytes").arg(worker->response.length());
    }
    else {
        msg = "Error: " + worker->error_str;
    }
    OAIObject output(QString(worker->response));
    worker->deleteLater();

    if (worker->error_type == QNetworkReply::NoError) {
        emit privateCancelAllByInstrumentGetSignal(output);
        emit privateCancelAllByInstrumentGetSignalFull(worker, output);
    } else {
        emit privateCancelAllByInstrumentGetSignalE(output, error_type, error_str);
        emit privateCancelAllByInstrumentGetSignalEFull(worker, error_type, error_str);
    }
}

void
OAIPrivateApi::privateCancelAllGet() {
    QString fullPath;
    fullPath.append(this->host).append(this->basePath).append("/private/cancel_all");
    
    OAIHttpRequestWorker *worker = new OAIHttpRequestWorker();
    OAIHttpRequestInput input(fullPath, "GET");


    foreach(QString key, this->defaultHeaders.keys()) {
        input.headers.insert(key, this->defaultHeaders.value(key));
    }

    connect(worker,
            &OAIHttpRequestWorker::on_execution_finished,
            this,
            &OAIPrivateApi::privateCancelAllGetCallback);

    worker->execute(&input);
}

void
OAIPrivateApi::privateCancelAllGetCallback(OAIHttpRequestWorker * worker) {
    QString msg;
    QString error_str = worker->error_str;
    QNetworkReply::NetworkError error_type = worker->error_type;

    if (worker->error_type == QNetworkReply::NoError) {
        msg = QString("Success! %1 bytes").arg(worker->response.length());
    }
    else {
        msg = "Error: " + worker->error_str;
    }
    OAIObject output(QString(worker->response));
    worker->deleteLater();

    if (worker->error_type == QNetworkReply::NoError) {
        emit privateCancelAllGetSignal(output);
        emit privateCancelAllGetSignalFull(worker, output);
    } else {
        emit privateCancelAllGetSignalE(output, error_type, error_str);
        emit privateCancelAllGetSignalEFull(worker, error_type, error_str);
    }
}

void
OAIPrivateApi::privateCancelGet(const QString& order_id) {
    QString fullPath;
    fullPath.append(this->host).append(this->basePath).append("/private/cancel");
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("order_id"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(order_id)));
    
    OAIHttpRequestWorker *worker = new OAIHttpRequestWorker();
    OAIHttpRequestInput input(fullPath, "GET");


    foreach(QString key, this->defaultHeaders.keys()) {
        input.headers.insert(key, this->defaultHeaders.value(key));
    }

    connect(worker,
            &OAIHttpRequestWorker::on_execution_finished,
            this,
            &OAIPrivateApi::privateCancelGetCallback);

    worker->execute(&input);
}

void
OAIPrivateApi::privateCancelGetCallback(OAIHttpRequestWorker * worker) {
    QString msg;
    QString error_str = worker->error_str;
    QNetworkReply::NetworkError error_type = worker->error_type;

    if (worker->error_type == QNetworkReply::NoError) {
        msg = QString("Success! %1 bytes").arg(worker->response.length());
    }
    else {
        msg = "Error: " + worker->error_str;
    }
    OAIObject output(QString(worker->response));
    worker->deleteLater();

    if (worker->error_type == QNetworkReply::NoError) {
        emit privateCancelGetSignal(output);
        emit privateCancelGetSignalFull(worker, output);
    } else {
        emit privateCancelGetSignalE(output, error_type, error_str);
        emit privateCancelGetSignalEFull(worker, error_type, error_str);
    }
}

void
OAIPrivateApi::privateCancelTransferByIdGet(const QString& currency, const qint32& id, const QString& tfa) {
    QString fullPath;
    fullPath.append(this->host).append(this->basePath).append("/private/cancel_transfer_by_id");
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("currency"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(currency)));
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("id"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(id)));
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("tfa"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(tfa)));
    
    OAIHttpRequestWorker *worker = new OAIHttpRequestWorker();
    OAIHttpRequestInput input(fullPath, "GET");


    foreach(QString key, this->defaultHeaders.keys()) {
        input.headers.insert(key, this->defaultHeaders.value(key));
    }

    connect(worker,
            &OAIHttpRequestWorker::on_execution_finished,
            this,
            &OAIPrivateApi::privateCancelTransferByIdGetCallback);

    worker->execute(&input);
}

void
OAIPrivateApi::privateCancelTransferByIdGetCallback(OAIHttpRequestWorker * worker) {
    QString msg;
    QString error_str = worker->error_str;
    QNetworkReply::NetworkError error_type = worker->error_type;

    if (worker->error_type == QNetworkReply::NoError) {
        msg = QString("Success! %1 bytes").arg(worker->response.length());
    }
    else {
        msg = "Error: " + worker->error_str;
    }
    OAIObject output(QString(worker->response));
    worker->deleteLater();

    if (worker->error_type == QNetworkReply::NoError) {
        emit privateCancelTransferByIdGetSignal(output);
        emit privateCancelTransferByIdGetSignalFull(worker, output);
    } else {
        emit privateCancelTransferByIdGetSignalE(output, error_type, error_str);
        emit privateCancelTransferByIdGetSignalEFull(worker, error_type, error_str);
    }
}

void
OAIPrivateApi::privateCancelWithdrawalGet(const QString& currency, const double& id) {
    QString fullPath;
    fullPath.append(this->host).append(this->basePath).append("/private/cancel_withdrawal");
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("currency"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(currency)));
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("id"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(id)));
    
    OAIHttpRequestWorker *worker = new OAIHttpRequestWorker();
    OAIHttpRequestInput input(fullPath, "GET");


    foreach(QString key, this->defaultHeaders.keys()) {
        input.headers.insert(key, this->defaultHeaders.value(key));
    }

    connect(worker,
            &OAIHttpRequestWorker::on_execution_finished,
            this,
            &OAIPrivateApi::privateCancelWithdrawalGetCallback);

    worker->execute(&input);
}

void
OAIPrivateApi::privateCancelWithdrawalGetCallback(OAIHttpRequestWorker * worker) {
    QString msg;
    QString error_str = worker->error_str;
    QNetworkReply::NetworkError error_type = worker->error_type;

    if (worker->error_type == QNetworkReply::NoError) {
        msg = QString("Success! %1 bytes").arg(worker->response.length());
    }
    else {
        msg = "Error: " + worker->error_str;
    }
    OAIObject output(QString(worker->response));
    worker->deleteLater();

    if (worker->error_type == QNetworkReply::NoError) {
        emit privateCancelWithdrawalGetSignal(output);
        emit privateCancelWithdrawalGetSignalFull(worker, output);
    } else {
        emit privateCancelWithdrawalGetSignalE(output, error_type, error_str);
        emit privateCancelWithdrawalGetSignalEFull(worker, error_type, error_str);
    }
}

void
OAIPrivateApi::privateChangeSubaccountNameGet(const qint32& sid, const QString& name) {
    QString fullPath;
    fullPath.append(this->host).append(this->basePath).append("/private/change_subaccount_name");
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("sid"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(sid)));
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("name"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(name)));
    
    OAIHttpRequestWorker *worker = new OAIHttpRequestWorker();
    OAIHttpRequestInput input(fullPath, "GET");


    foreach(QString key, this->defaultHeaders.keys()) {
        input.headers.insert(key, this->defaultHeaders.value(key));
    }

    connect(worker,
            &OAIHttpRequestWorker::on_execution_finished,
            this,
            &OAIPrivateApi::privateChangeSubaccountNameGetCallback);

    worker->execute(&input);
}

void
OAIPrivateApi::privateChangeSubaccountNameGetCallback(OAIHttpRequestWorker * worker) {
    QString msg;
    QString error_str = worker->error_str;
    QNetworkReply::NetworkError error_type = worker->error_type;

    if (worker->error_type == QNetworkReply::NoError) {
        msg = QString("Success! %1 bytes").arg(worker->response.length());
    }
    else {
        msg = "Error: " + worker->error_str;
    }
    OAIObject output(QString(worker->response));
    worker->deleteLater();

    if (worker->error_type == QNetworkReply::NoError) {
        emit privateChangeSubaccountNameGetSignal(output);
        emit privateChangeSubaccountNameGetSignalFull(worker, output);
    } else {
        emit privateChangeSubaccountNameGetSignalE(output, error_type, error_str);
        emit privateChangeSubaccountNameGetSignalEFull(worker, error_type, error_str);
    }
}

void
OAIPrivateApi::privateClosePositionGet(const QString& instrument_name, const QString& type, const double& price) {
    QString fullPath;
    fullPath.append(this->host).append(this->basePath).append("/private/close_position");
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("instrument_name"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(instrument_name)));
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("type"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(type)));
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("price"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(price)));
    
    OAIHttpRequestWorker *worker = new OAIHttpRequestWorker();
    OAIHttpRequestInput input(fullPath, "GET");


    foreach(QString key, this->defaultHeaders.keys()) {
        input.headers.insert(key, this->defaultHeaders.value(key));
    }

    connect(worker,
            &OAIHttpRequestWorker::on_execution_finished,
            this,
            &OAIPrivateApi::privateClosePositionGetCallback);

    worker->execute(&input);
}

void
OAIPrivateApi::privateClosePositionGetCallback(OAIHttpRequestWorker * worker) {
    QString msg;
    QString error_str = worker->error_str;
    QNetworkReply::NetworkError error_type = worker->error_type;

    if (worker->error_type == QNetworkReply::NoError) {
        msg = QString("Success! %1 bytes").arg(worker->response.length());
    }
    else {
        msg = "Error: " + worker->error_str;
    }
    OAIObject output(QString(worker->response));
    worker->deleteLater();

    if (worker->error_type == QNetworkReply::NoError) {
        emit privateClosePositionGetSignal(output);
        emit privateClosePositionGetSignalFull(worker, output);
    } else {
        emit privateClosePositionGetSignalE(output, error_type, error_str);
        emit privateClosePositionGetSignalEFull(worker, error_type, error_str);
    }
}

void
OAIPrivateApi::privateCreateDepositAddressGet(const QString& currency) {
    QString fullPath;
    fullPath.append(this->host).append(this->basePath).append("/private/create_deposit_address");
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("currency"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(currency)));
    
    OAIHttpRequestWorker *worker = new OAIHttpRequestWorker();
    OAIHttpRequestInput input(fullPath, "GET");


    foreach(QString key, this->defaultHeaders.keys()) {
        input.headers.insert(key, this->defaultHeaders.value(key));
    }

    connect(worker,
            &OAIHttpRequestWorker::on_execution_finished,
            this,
            &OAIPrivateApi::privateCreateDepositAddressGetCallback);

    worker->execute(&input);
}

void
OAIPrivateApi::privateCreateDepositAddressGetCallback(OAIHttpRequestWorker * worker) {
    QString msg;
    QString error_str = worker->error_str;
    QNetworkReply::NetworkError error_type = worker->error_type;

    if (worker->error_type == QNetworkReply::NoError) {
        msg = QString("Success! %1 bytes").arg(worker->response.length());
    }
    else {
        msg = "Error: " + worker->error_str;
    }
    OAIObject output(QString(worker->response));
    worker->deleteLater();

    if (worker->error_type == QNetworkReply::NoError) {
        emit privateCreateDepositAddressGetSignal(output);
        emit privateCreateDepositAddressGetSignalFull(worker, output);
    } else {
        emit privateCreateDepositAddressGetSignalE(output, error_type, error_str);
        emit privateCreateDepositAddressGetSignalEFull(worker, error_type, error_str);
    }
}

void
OAIPrivateApi::privateCreateSubaccountGet() {
    QString fullPath;
    fullPath.append(this->host).append(this->basePath).append("/private/create_subaccount");
    
    OAIHttpRequestWorker *worker = new OAIHttpRequestWorker();
    OAIHttpRequestInput input(fullPath, "GET");


    foreach(QString key, this->defaultHeaders.keys()) {
        input.headers.insert(key, this->defaultHeaders.value(key));
    }

    connect(worker,
            &OAIHttpRequestWorker::on_execution_finished,
            this,
            &OAIPrivateApi::privateCreateSubaccountGetCallback);

    worker->execute(&input);
}

void
OAIPrivateApi::privateCreateSubaccountGetCallback(OAIHttpRequestWorker * worker) {
    QString msg;
    QString error_str = worker->error_str;
    QNetworkReply::NetworkError error_type = worker->error_type;

    if (worker->error_type == QNetworkReply::NoError) {
        msg = QString("Success! %1 bytes").arg(worker->response.length());
    }
    else {
        msg = "Error: " + worker->error_str;
    }
    OAIObject output(QString(worker->response));
    worker->deleteLater();

    if (worker->error_type == QNetworkReply::NoError) {
        emit privateCreateSubaccountGetSignal(output);
        emit privateCreateSubaccountGetSignalFull(worker, output);
    } else {
        emit privateCreateSubaccountGetSignalE(output, error_type, error_str);
        emit privateCreateSubaccountGetSignalEFull(worker, error_type, error_str);
    }
}

void
OAIPrivateApi::privateDisableTfaForSubaccountGet(const qint32& sid) {
    QString fullPath;
    fullPath.append(this->host).append(this->basePath).append("/private/disable_tfa_for_subaccount");
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("sid"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(sid)));
    
    OAIHttpRequestWorker *worker = new OAIHttpRequestWorker();
    OAIHttpRequestInput input(fullPath, "GET");


    foreach(QString key, this->defaultHeaders.keys()) {
        input.headers.insert(key, this->defaultHeaders.value(key));
    }

    connect(worker,
            &OAIHttpRequestWorker::on_execution_finished,
            this,
            &OAIPrivateApi::privateDisableTfaForSubaccountGetCallback);

    worker->execute(&input);
}

void
OAIPrivateApi::privateDisableTfaForSubaccountGetCallback(OAIHttpRequestWorker * worker) {
    QString msg;
    QString error_str = worker->error_str;
    QNetworkReply::NetworkError error_type = worker->error_type;

    if (worker->error_type == QNetworkReply::NoError) {
        msg = QString("Success! %1 bytes").arg(worker->response.length());
    }
    else {
        msg = "Error: " + worker->error_str;
    }
    OAIObject output(QString(worker->response));
    worker->deleteLater();

    if (worker->error_type == QNetworkReply::NoError) {
        emit privateDisableTfaForSubaccountGetSignal(output);
        emit privateDisableTfaForSubaccountGetSignalFull(worker, output);
    } else {
        emit privateDisableTfaForSubaccountGetSignalE(output, error_type, error_str);
        emit privateDisableTfaForSubaccountGetSignalEFull(worker, error_type, error_str);
    }
}

void
OAIPrivateApi::privateDisableTfaWithRecoveryCodeGet(const QString& password, const QString& code) {
    QString fullPath;
    fullPath.append(this->host).append(this->basePath).append("/private/disable_tfa_with_recovery_code");
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("password"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(password)));
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("code"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(code)));
    
    OAIHttpRequestWorker *worker = new OAIHttpRequestWorker();
    OAIHttpRequestInput input(fullPath, "GET");


    foreach(QString key, this->defaultHeaders.keys()) {
        input.headers.insert(key, this->defaultHeaders.value(key));
    }

    connect(worker,
            &OAIHttpRequestWorker::on_execution_finished,
            this,
            &OAIPrivateApi::privateDisableTfaWithRecoveryCodeGetCallback);

    worker->execute(&input);
}

void
OAIPrivateApi::privateDisableTfaWithRecoveryCodeGetCallback(OAIHttpRequestWorker * worker) {
    QString msg;
    QString error_str = worker->error_str;
    QNetworkReply::NetworkError error_type = worker->error_type;

    if (worker->error_type == QNetworkReply::NoError) {
        msg = QString("Success! %1 bytes").arg(worker->response.length());
    }
    else {
        msg = "Error: " + worker->error_str;
    }
    OAIObject output(QString(worker->response));
    worker->deleteLater();

    if (worker->error_type == QNetworkReply::NoError) {
        emit privateDisableTfaWithRecoveryCodeGetSignal(output);
        emit privateDisableTfaWithRecoveryCodeGetSignalFull(worker, output);
    } else {
        emit privateDisableTfaWithRecoveryCodeGetSignalE(output, error_type, error_str);
        emit privateDisableTfaWithRecoveryCodeGetSignalEFull(worker, error_type, error_str);
    }
}

void
OAIPrivateApi::privateEditGet(const QString& order_id, const double& amount, const double& price, const bool& post_only, const QString& advanced, const double& stop_price) {
    QString fullPath;
    fullPath.append(this->host).append(this->basePath).append("/private/edit");
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("order_id"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(order_id)));
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("amount"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(amount)));
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("price"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(price)));
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("post_only"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(post_only)));
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("advanced"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(advanced)));
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("stop_price"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(stop_price)));
    
    OAIHttpRequestWorker *worker = new OAIHttpRequestWorker();
    OAIHttpRequestInput input(fullPath, "GET");


    foreach(QString key, this->defaultHeaders.keys()) {
        input.headers.insert(key, this->defaultHeaders.value(key));
    }

    connect(worker,
            &OAIHttpRequestWorker::on_execution_finished,
            this,
            &OAIPrivateApi::privateEditGetCallback);

    worker->execute(&input);
}

void
OAIPrivateApi::privateEditGetCallback(OAIHttpRequestWorker * worker) {
    QString msg;
    QString error_str = worker->error_str;
    QNetworkReply::NetworkError error_type = worker->error_type;

    if (worker->error_type == QNetworkReply::NoError) {
        msg = QString("Success! %1 bytes").arg(worker->response.length());
    }
    else {
        msg = "Error: " + worker->error_str;
    }
    OAIObject output(QString(worker->response));
    worker->deleteLater();

    if (worker->error_type == QNetworkReply::NoError) {
        emit privateEditGetSignal(output);
        emit privateEditGetSignalFull(worker, output);
    } else {
        emit privateEditGetSignalE(output, error_type, error_str);
        emit privateEditGetSignalEFull(worker, error_type, error_str);
    }
}

void
OAIPrivateApi::privateGetAccountSummaryGet(const QString& currency, const bool& extended) {
    QString fullPath;
    fullPath.append(this->host).append(this->basePath).append("/private/get_account_summary");
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("currency"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(currency)));
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("extended"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(extended)));
    
    OAIHttpRequestWorker *worker = new OAIHttpRequestWorker();
    OAIHttpRequestInput input(fullPath, "GET");


    foreach(QString key, this->defaultHeaders.keys()) {
        input.headers.insert(key, this->defaultHeaders.value(key));
    }

    connect(worker,
            &OAIHttpRequestWorker::on_execution_finished,
            this,
            &OAIPrivateApi::privateGetAccountSummaryGetCallback);

    worker->execute(&input);
}

void
OAIPrivateApi::privateGetAccountSummaryGetCallback(OAIHttpRequestWorker * worker) {
    QString msg;
    QString error_str = worker->error_str;
    QNetworkReply::NetworkError error_type = worker->error_type;

    if (worker->error_type == QNetworkReply::NoError) {
        msg = QString("Success! %1 bytes").arg(worker->response.length());
    }
    else {
        msg = "Error: " + worker->error_str;
    }
    OAIObject output(QString(worker->response));
    worker->deleteLater();

    if (worker->error_type == QNetworkReply::NoError) {
        emit privateGetAccountSummaryGetSignal(output);
        emit privateGetAccountSummaryGetSignalFull(worker, output);
    } else {
        emit privateGetAccountSummaryGetSignalE(output, error_type, error_str);
        emit privateGetAccountSummaryGetSignalEFull(worker, error_type, error_str);
    }
}

void
OAIPrivateApi::privateGetAddressBookGet(const QString& currency, const QString& type) {
    QString fullPath;
    fullPath.append(this->host).append(this->basePath).append("/private/get_address_book");
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("currency"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(currency)));
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("type"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(type)));
    
    OAIHttpRequestWorker *worker = new OAIHttpRequestWorker();
    OAIHttpRequestInput input(fullPath, "GET");


    foreach(QString key, this->defaultHeaders.keys()) {
        input.headers.insert(key, this->defaultHeaders.value(key));
    }

    connect(worker,
            &OAIHttpRequestWorker::on_execution_finished,
            this,
            &OAIPrivateApi::privateGetAddressBookGetCallback);

    worker->execute(&input);
}

void
OAIPrivateApi::privateGetAddressBookGetCallback(OAIHttpRequestWorker * worker) {
    QString msg;
    QString error_str = worker->error_str;
    QNetworkReply::NetworkError error_type = worker->error_type;

    if (worker->error_type == QNetworkReply::NoError) {
        msg = QString("Success! %1 bytes").arg(worker->response.length());
    }
    else {
        msg = "Error: " + worker->error_str;
    }
    OAIObject output(QString(worker->response));
    worker->deleteLater();

    if (worker->error_type == QNetworkReply::NoError) {
        emit privateGetAddressBookGetSignal(output);
        emit privateGetAddressBookGetSignalFull(worker, output);
    } else {
        emit privateGetAddressBookGetSignalE(output, error_type, error_str);
        emit privateGetAddressBookGetSignalEFull(worker, error_type, error_str);
    }
}

void
OAIPrivateApi::privateGetCurrentDepositAddressGet(const QString& currency) {
    QString fullPath;
    fullPath.append(this->host).append(this->basePath).append("/private/get_current_deposit_address");
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("currency"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(currency)));
    
    OAIHttpRequestWorker *worker = new OAIHttpRequestWorker();
    OAIHttpRequestInput input(fullPath, "GET");


    foreach(QString key, this->defaultHeaders.keys()) {
        input.headers.insert(key, this->defaultHeaders.value(key));
    }

    connect(worker,
            &OAIHttpRequestWorker::on_execution_finished,
            this,
            &OAIPrivateApi::privateGetCurrentDepositAddressGetCallback);

    worker->execute(&input);
}

void
OAIPrivateApi::privateGetCurrentDepositAddressGetCallback(OAIHttpRequestWorker * worker) {
    QString msg;
    QString error_str = worker->error_str;
    QNetworkReply::NetworkError error_type = worker->error_type;

    if (worker->error_type == QNetworkReply::NoError) {
        msg = QString("Success! %1 bytes").arg(worker->response.length());
    }
    else {
        msg = "Error: " + worker->error_str;
    }
    OAIObject output(QString(worker->response));
    worker->deleteLater();

    if (worker->error_type == QNetworkReply::NoError) {
        emit privateGetCurrentDepositAddressGetSignal(output);
        emit privateGetCurrentDepositAddressGetSignalFull(worker, output);
    } else {
        emit privateGetCurrentDepositAddressGetSignalE(output, error_type, error_str);
        emit privateGetCurrentDepositAddressGetSignalEFull(worker, error_type, error_str);
    }
}

void
OAIPrivateApi::privateGetDepositsGet(const QString& currency, const qint32& count, const qint32& offset) {
    QString fullPath;
    fullPath.append(this->host).append(this->basePath).append("/private/get_deposits");
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("currency"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(currency)));
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("count"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(count)));
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("offset"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(offset)));
    
    OAIHttpRequestWorker *worker = new OAIHttpRequestWorker();
    OAIHttpRequestInput input(fullPath, "GET");


    foreach(QString key, this->defaultHeaders.keys()) {
        input.headers.insert(key, this->defaultHeaders.value(key));
    }

    connect(worker,
            &OAIHttpRequestWorker::on_execution_finished,
            this,
            &OAIPrivateApi::privateGetDepositsGetCallback);

    worker->execute(&input);
}

void
OAIPrivateApi::privateGetDepositsGetCallback(OAIHttpRequestWorker * worker) {
    QString msg;
    QString error_str = worker->error_str;
    QNetworkReply::NetworkError error_type = worker->error_type;

    if (worker->error_type == QNetworkReply::NoError) {
        msg = QString("Success! %1 bytes").arg(worker->response.length());
    }
    else {
        msg = "Error: " + worker->error_str;
    }
    OAIObject output(QString(worker->response));
    worker->deleteLater();

    if (worker->error_type == QNetworkReply::NoError) {
        emit privateGetDepositsGetSignal(output);
        emit privateGetDepositsGetSignalFull(worker, output);
    } else {
        emit privateGetDepositsGetSignalE(output, error_type, error_str);
        emit privateGetDepositsGetSignalEFull(worker, error_type, error_str);
    }
}

void
OAIPrivateApi::privateGetEmailLanguageGet() {
    QString fullPath;
    fullPath.append(this->host).append(this->basePath).append("/private/get_email_language");
    
    OAIHttpRequestWorker *worker = new OAIHttpRequestWorker();
    OAIHttpRequestInput input(fullPath, "GET");


    foreach(QString key, this->defaultHeaders.keys()) {
        input.headers.insert(key, this->defaultHeaders.value(key));
    }

    connect(worker,
            &OAIHttpRequestWorker::on_execution_finished,
            this,
            &OAIPrivateApi::privateGetEmailLanguageGetCallback);

    worker->execute(&input);
}

void
OAIPrivateApi::privateGetEmailLanguageGetCallback(OAIHttpRequestWorker * worker) {
    QString msg;
    QString error_str = worker->error_str;
    QNetworkReply::NetworkError error_type = worker->error_type;

    if (worker->error_type == QNetworkReply::NoError) {
        msg = QString("Success! %1 bytes").arg(worker->response.length());
    }
    else {
        msg = "Error: " + worker->error_str;
    }
    OAIObject output(QString(worker->response));
    worker->deleteLater();

    if (worker->error_type == QNetworkReply::NoError) {
        emit privateGetEmailLanguageGetSignal(output);
        emit privateGetEmailLanguageGetSignalFull(worker, output);
    } else {
        emit privateGetEmailLanguageGetSignalE(output, error_type, error_str);
        emit privateGetEmailLanguageGetSignalEFull(worker, error_type, error_str);
    }
}

void
OAIPrivateApi::privateGetMarginsGet(const QString& instrument_name, const double& amount, const double& price) {
    QString fullPath;
    fullPath.append(this->host).append(this->basePath).append("/private/get_margins");
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("instrument_name"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(instrument_name)));
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("amount"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(amount)));
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("price"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(price)));
    
    OAIHttpRequestWorker *worker = new OAIHttpRequestWorker();
    OAIHttpRequestInput input(fullPath, "GET");


    foreach(QString key, this->defaultHeaders.keys()) {
        input.headers.insert(key, this->defaultHeaders.value(key));
    }

    connect(worker,
            &OAIHttpRequestWorker::on_execution_finished,
            this,
            &OAIPrivateApi::privateGetMarginsGetCallback);

    worker->execute(&input);
}

void
OAIPrivateApi::privateGetMarginsGetCallback(OAIHttpRequestWorker * worker) {
    QString msg;
    QString error_str = worker->error_str;
    QNetworkReply::NetworkError error_type = worker->error_type;

    if (worker->error_type == QNetworkReply::NoError) {
        msg = QString("Success! %1 bytes").arg(worker->response.length());
    }
    else {
        msg = "Error: " + worker->error_str;
    }
    OAIObject output(QString(worker->response));
    worker->deleteLater();

    if (worker->error_type == QNetworkReply::NoError) {
        emit privateGetMarginsGetSignal(output);
        emit privateGetMarginsGetSignalFull(worker, output);
    } else {
        emit privateGetMarginsGetSignalE(output, error_type, error_str);
        emit privateGetMarginsGetSignalEFull(worker, error_type, error_str);
    }
}

void
OAIPrivateApi::privateGetNewAnnouncementsGet() {
    QString fullPath;
    fullPath.append(this->host).append(this->basePath).append("/private/get_new_announcements");
    
    OAIHttpRequestWorker *worker = new OAIHttpRequestWorker();
    OAIHttpRequestInput input(fullPath, "GET");


    foreach(QString key, this->defaultHeaders.keys()) {
        input.headers.insert(key, this->defaultHeaders.value(key));
    }

    connect(worker,
            &OAIHttpRequestWorker::on_execution_finished,
            this,
            &OAIPrivateApi::privateGetNewAnnouncementsGetCallback);

    worker->execute(&input);
}

void
OAIPrivateApi::privateGetNewAnnouncementsGetCallback(OAIHttpRequestWorker * worker) {
    QString msg;
    QString error_str = worker->error_str;
    QNetworkReply::NetworkError error_type = worker->error_type;

    if (worker->error_type == QNetworkReply::NoError) {
        msg = QString("Success! %1 bytes").arg(worker->response.length());
    }
    else {
        msg = "Error: " + worker->error_str;
    }
    OAIObject output(QString(worker->response));
    worker->deleteLater();

    if (worker->error_type == QNetworkReply::NoError) {
        emit privateGetNewAnnouncementsGetSignal(output);
        emit privateGetNewAnnouncementsGetSignalFull(worker, output);
    } else {
        emit privateGetNewAnnouncementsGetSignalE(output, error_type, error_str);
        emit privateGetNewAnnouncementsGetSignalEFull(worker, error_type, error_str);
    }
}

void
OAIPrivateApi::privateGetOpenOrdersByCurrencyGet(const QString& currency, const QString& kind, const QString& type) {
    QString fullPath;
    fullPath.append(this->host).append(this->basePath).append("/private/get_open_orders_by_currency");
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("currency"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(currency)));
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("kind"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(kind)));
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("type"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(type)));
    
    OAIHttpRequestWorker *worker = new OAIHttpRequestWorker();
    OAIHttpRequestInput input(fullPath, "GET");


    foreach(QString key, this->defaultHeaders.keys()) {
        input.headers.insert(key, this->defaultHeaders.value(key));
    }

    connect(worker,
            &OAIHttpRequestWorker::on_execution_finished,
            this,
            &OAIPrivateApi::privateGetOpenOrdersByCurrencyGetCallback);

    worker->execute(&input);
}

void
OAIPrivateApi::privateGetOpenOrdersByCurrencyGetCallback(OAIHttpRequestWorker * worker) {
    QString msg;
    QString error_str = worker->error_str;
    QNetworkReply::NetworkError error_type = worker->error_type;

    if (worker->error_type == QNetworkReply::NoError) {
        msg = QString("Success! %1 bytes").arg(worker->response.length());
    }
    else {
        msg = "Error: " + worker->error_str;
    }
    OAIObject output(QString(worker->response));
    worker->deleteLater();

    if (worker->error_type == QNetworkReply::NoError) {
        emit privateGetOpenOrdersByCurrencyGetSignal(output);
        emit privateGetOpenOrdersByCurrencyGetSignalFull(worker, output);
    } else {
        emit privateGetOpenOrdersByCurrencyGetSignalE(output, error_type, error_str);
        emit privateGetOpenOrdersByCurrencyGetSignalEFull(worker, error_type, error_str);
    }
}

void
OAIPrivateApi::privateGetOpenOrdersByInstrumentGet(const QString& instrument_name, const QString& type) {
    QString fullPath;
    fullPath.append(this->host).append(this->basePath).append("/private/get_open_orders_by_instrument");
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("instrument_name"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(instrument_name)));
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("type"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(type)));
    
    OAIHttpRequestWorker *worker = new OAIHttpRequestWorker();
    OAIHttpRequestInput input(fullPath, "GET");


    foreach(QString key, this->defaultHeaders.keys()) {
        input.headers.insert(key, this->defaultHeaders.value(key));
    }

    connect(worker,
            &OAIHttpRequestWorker::on_execution_finished,
            this,
            &OAIPrivateApi::privateGetOpenOrdersByInstrumentGetCallback);

    worker->execute(&input);
}

void
OAIPrivateApi::privateGetOpenOrdersByInstrumentGetCallback(OAIHttpRequestWorker * worker) {
    QString msg;
    QString error_str = worker->error_str;
    QNetworkReply::NetworkError error_type = worker->error_type;

    if (worker->error_type == QNetworkReply::NoError) {
        msg = QString("Success! %1 bytes").arg(worker->response.length());
    }
    else {
        msg = "Error: " + worker->error_str;
    }
    OAIObject output(QString(worker->response));
    worker->deleteLater();

    if (worker->error_type == QNetworkReply::NoError) {
        emit privateGetOpenOrdersByInstrumentGetSignal(output);
        emit privateGetOpenOrdersByInstrumentGetSignalFull(worker, output);
    } else {
        emit privateGetOpenOrdersByInstrumentGetSignalE(output, error_type, error_str);
        emit privateGetOpenOrdersByInstrumentGetSignalEFull(worker, error_type, error_str);
    }
}

void
OAIPrivateApi::privateGetOrderHistoryByCurrencyGet(const QString& currency, const QString& kind, const qint32& count, const qint32& offset, const bool& include_old, const bool& include_unfilled) {
    QString fullPath;
    fullPath.append(this->host).append(this->basePath).append("/private/get_order_history_by_currency");
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("currency"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(currency)));
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("kind"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(kind)));
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("count"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(count)));
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("offset"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(offset)));
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("include_old"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(include_old)));
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("include_unfilled"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(include_unfilled)));
    
    OAIHttpRequestWorker *worker = new OAIHttpRequestWorker();
    OAIHttpRequestInput input(fullPath, "GET");


    foreach(QString key, this->defaultHeaders.keys()) {
        input.headers.insert(key, this->defaultHeaders.value(key));
    }

    connect(worker,
            &OAIHttpRequestWorker::on_execution_finished,
            this,
            &OAIPrivateApi::privateGetOrderHistoryByCurrencyGetCallback);

    worker->execute(&input);
}

void
OAIPrivateApi::privateGetOrderHistoryByCurrencyGetCallback(OAIHttpRequestWorker * worker) {
    QString msg;
    QString error_str = worker->error_str;
    QNetworkReply::NetworkError error_type = worker->error_type;

    if (worker->error_type == QNetworkReply::NoError) {
        msg = QString("Success! %1 bytes").arg(worker->response.length());
    }
    else {
        msg = "Error: " + worker->error_str;
    }
    OAIObject output(QString(worker->response));
    worker->deleteLater();

    if (worker->error_type == QNetworkReply::NoError) {
        emit privateGetOrderHistoryByCurrencyGetSignal(output);
        emit privateGetOrderHistoryByCurrencyGetSignalFull(worker, output);
    } else {
        emit privateGetOrderHistoryByCurrencyGetSignalE(output, error_type, error_str);
        emit privateGetOrderHistoryByCurrencyGetSignalEFull(worker, error_type, error_str);
    }
}

void
OAIPrivateApi::privateGetOrderHistoryByInstrumentGet(const QString& instrument_name, const qint32& count, const qint32& offset, const bool& include_old, const bool& include_unfilled) {
    QString fullPath;
    fullPath.append(this->host).append(this->basePath).append("/private/get_order_history_by_instrument");
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("instrument_name"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(instrument_name)));
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("count"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(count)));
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("offset"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(offset)));
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("include_old"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(include_old)));
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("include_unfilled"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(include_unfilled)));
    
    OAIHttpRequestWorker *worker = new OAIHttpRequestWorker();
    OAIHttpRequestInput input(fullPath, "GET");


    foreach(QString key, this->defaultHeaders.keys()) {
        input.headers.insert(key, this->defaultHeaders.value(key));
    }

    connect(worker,
            &OAIHttpRequestWorker::on_execution_finished,
            this,
            &OAIPrivateApi::privateGetOrderHistoryByInstrumentGetCallback);

    worker->execute(&input);
}

void
OAIPrivateApi::privateGetOrderHistoryByInstrumentGetCallback(OAIHttpRequestWorker * worker) {
    QString msg;
    QString error_str = worker->error_str;
    QNetworkReply::NetworkError error_type = worker->error_type;

    if (worker->error_type == QNetworkReply::NoError) {
        msg = QString("Success! %1 bytes").arg(worker->response.length());
    }
    else {
        msg = "Error: " + worker->error_str;
    }
    OAIObject output(QString(worker->response));
    worker->deleteLater();

    if (worker->error_type == QNetworkReply::NoError) {
        emit privateGetOrderHistoryByInstrumentGetSignal(output);
        emit privateGetOrderHistoryByInstrumentGetSignalFull(worker, output);
    } else {
        emit privateGetOrderHistoryByInstrumentGetSignalE(output, error_type, error_str);
        emit privateGetOrderHistoryByInstrumentGetSignalEFull(worker, error_type, error_str);
    }
}

void
OAIPrivateApi::privateGetOrderMarginByIdsGet(const QList<QString>& ids) {
    QString fullPath;
    fullPath.append(this->host).append(this->basePath).append("/private/get_order_margin_by_ids");
    
    if (ids.size() > 0) {
      if (QString("multi").indexOf("multi") == 0) {
        foreach(QString t, ids) {
          if (fullPath.indexOf("?") > 0)
            fullPath.append("&");
          else
            fullPath.append("?");
          fullPath.append("ids=").append(::OpenAPI::toStringValue(t));
        }
      }
      else if (QString("multi").indexOf("ssv") == 0) {
        if (fullPath.indexOf("?") > 0)
          fullPath.append("&");
        else
          fullPath.append("?");
        fullPath.append("ids=");
        qint32 count = 0;
        foreach(QString t, ids) {
          if (count > 0) {
            fullPath.append(" ");
          }
          fullPath.append(::OpenAPI::toStringValue(t));
        }
      }
      else if (QString("multi").indexOf("tsv") == 0) {
        if (fullPath.indexOf("?") > 0)
          fullPath.append("&");
        else
          fullPath.append("?");
        fullPath.append("ids=");
        qint32 count = 0;
        foreach(QString t, ids) {
          if (count > 0) {
            fullPath.append("\t");
          }
          fullPath.append(::OpenAPI::toStringValue(t));
        }
      }
    }
    
    OAIHttpRequestWorker *worker = new OAIHttpRequestWorker();
    OAIHttpRequestInput input(fullPath, "GET");


    foreach(QString key, this->defaultHeaders.keys()) {
        input.headers.insert(key, this->defaultHeaders.value(key));
    }

    connect(worker,
            &OAIHttpRequestWorker::on_execution_finished,
            this,
            &OAIPrivateApi::privateGetOrderMarginByIdsGetCallback);

    worker->execute(&input);
}

void
OAIPrivateApi::privateGetOrderMarginByIdsGetCallback(OAIHttpRequestWorker * worker) {
    QString msg;
    QString error_str = worker->error_str;
    QNetworkReply::NetworkError error_type = worker->error_type;

    if (worker->error_type == QNetworkReply::NoError) {
        msg = QString("Success! %1 bytes").arg(worker->response.length());
    }
    else {
        msg = "Error: " + worker->error_str;
    }
    OAIObject output(QString(worker->response));
    worker->deleteLater();

    if (worker->error_type == QNetworkReply::NoError) {
        emit privateGetOrderMarginByIdsGetSignal(output);
        emit privateGetOrderMarginByIdsGetSignalFull(worker, output);
    } else {
        emit privateGetOrderMarginByIdsGetSignalE(output, error_type, error_str);
        emit privateGetOrderMarginByIdsGetSignalEFull(worker, error_type, error_str);
    }
}

void
OAIPrivateApi::privateGetOrderStateGet(const QString& order_id) {
    QString fullPath;
    fullPath.append(this->host).append(this->basePath).append("/private/get_order_state");
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("order_id"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(order_id)));
    
    OAIHttpRequestWorker *worker = new OAIHttpRequestWorker();
    OAIHttpRequestInput input(fullPath, "GET");


    foreach(QString key, this->defaultHeaders.keys()) {
        input.headers.insert(key, this->defaultHeaders.value(key));
    }

    connect(worker,
            &OAIHttpRequestWorker::on_execution_finished,
            this,
            &OAIPrivateApi::privateGetOrderStateGetCallback);

    worker->execute(&input);
}

void
OAIPrivateApi::privateGetOrderStateGetCallback(OAIHttpRequestWorker * worker) {
    QString msg;
    QString error_str = worker->error_str;
    QNetworkReply::NetworkError error_type = worker->error_type;

    if (worker->error_type == QNetworkReply::NoError) {
        msg = QString("Success! %1 bytes").arg(worker->response.length());
    }
    else {
        msg = "Error: " + worker->error_str;
    }
    OAIObject output(QString(worker->response));
    worker->deleteLater();

    if (worker->error_type == QNetworkReply::NoError) {
        emit privateGetOrderStateGetSignal(output);
        emit privateGetOrderStateGetSignalFull(worker, output);
    } else {
        emit privateGetOrderStateGetSignalE(output, error_type, error_str);
        emit privateGetOrderStateGetSignalEFull(worker, error_type, error_str);
    }
}

void
OAIPrivateApi::privateGetPositionGet(const QString& instrument_name) {
    QString fullPath;
    fullPath.append(this->host).append(this->basePath).append("/private/get_position");
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("instrument_name"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(instrument_name)));
    
    OAIHttpRequestWorker *worker = new OAIHttpRequestWorker();
    OAIHttpRequestInput input(fullPath, "GET");


    foreach(QString key, this->defaultHeaders.keys()) {
        input.headers.insert(key, this->defaultHeaders.value(key));
    }

    connect(worker,
            &OAIHttpRequestWorker::on_execution_finished,
            this,
            &OAIPrivateApi::privateGetPositionGetCallback);

    worker->execute(&input);
}

void
OAIPrivateApi::privateGetPositionGetCallback(OAIHttpRequestWorker * worker) {
    QString msg;
    QString error_str = worker->error_str;
    QNetworkReply::NetworkError error_type = worker->error_type;

    if (worker->error_type == QNetworkReply::NoError) {
        msg = QString("Success! %1 bytes").arg(worker->response.length());
    }
    else {
        msg = "Error: " + worker->error_str;
    }
    OAIObject output(QString(worker->response));
    worker->deleteLater();

    if (worker->error_type == QNetworkReply::NoError) {
        emit privateGetPositionGetSignal(output);
        emit privateGetPositionGetSignalFull(worker, output);
    } else {
        emit privateGetPositionGetSignalE(output, error_type, error_str);
        emit privateGetPositionGetSignalEFull(worker, error_type, error_str);
    }
}

void
OAIPrivateApi::privateGetPositionsGet(const QString& currency, const QString& kind) {
    QString fullPath;
    fullPath.append(this->host).append(this->basePath).append("/private/get_positions");
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("currency"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(currency)));
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("kind"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(kind)));
    
    OAIHttpRequestWorker *worker = new OAIHttpRequestWorker();
    OAIHttpRequestInput input(fullPath, "GET");


    foreach(QString key, this->defaultHeaders.keys()) {
        input.headers.insert(key, this->defaultHeaders.value(key));
    }

    connect(worker,
            &OAIHttpRequestWorker::on_execution_finished,
            this,
            &OAIPrivateApi::privateGetPositionsGetCallback);

    worker->execute(&input);
}

void
OAIPrivateApi::privateGetPositionsGetCallback(OAIHttpRequestWorker * worker) {
    QString msg;
    QString error_str = worker->error_str;
    QNetworkReply::NetworkError error_type = worker->error_type;

    if (worker->error_type == QNetworkReply::NoError) {
        msg = QString("Success! %1 bytes").arg(worker->response.length());
    }
    else {
        msg = "Error: " + worker->error_str;
    }
    OAIObject output(QString(worker->response));
    worker->deleteLater();

    if (worker->error_type == QNetworkReply::NoError) {
        emit privateGetPositionsGetSignal(output);
        emit privateGetPositionsGetSignalFull(worker, output);
    } else {
        emit privateGetPositionsGetSignalE(output, error_type, error_str);
        emit privateGetPositionsGetSignalEFull(worker, error_type, error_str);
    }
}

void
OAIPrivateApi::privateGetSettlementHistoryByCurrencyGet(const QString& currency, const QString& type, const qint32& count) {
    QString fullPath;
    fullPath.append(this->host).append(this->basePath).append("/private/get_settlement_history_by_currency");
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("currency"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(currency)));
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("type"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(type)));
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("count"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(count)));
    
    OAIHttpRequestWorker *worker = new OAIHttpRequestWorker();
    OAIHttpRequestInput input(fullPath, "GET");


    foreach(QString key, this->defaultHeaders.keys()) {
        input.headers.insert(key, this->defaultHeaders.value(key));
    }

    connect(worker,
            &OAIHttpRequestWorker::on_execution_finished,
            this,
            &OAIPrivateApi::privateGetSettlementHistoryByCurrencyGetCallback);

    worker->execute(&input);
}

void
OAIPrivateApi::privateGetSettlementHistoryByCurrencyGetCallback(OAIHttpRequestWorker * worker) {
    QString msg;
    QString error_str = worker->error_str;
    QNetworkReply::NetworkError error_type = worker->error_type;

    if (worker->error_type == QNetworkReply::NoError) {
        msg = QString("Success! %1 bytes").arg(worker->response.length());
    }
    else {
        msg = "Error: " + worker->error_str;
    }
    OAIObject output(QString(worker->response));
    worker->deleteLater();

    if (worker->error_type == QNetworkReply::NoError) {
        emit privateGetSettlementHistoryByCurrencyGetSignal(output);
        emit privateGetSettlementHistoryByCurrencyGetSignalFull(worker, output);
    } else {
        emit privateGetSettlementHistoryByCurrencyGetSignalE(output, error_type, error_str);
        emit privateGetSettlementHistoryByCurrencyGetSignalEFull(worker, error_type, error_str);
    }
}

void
OAIPrivateApi::privateGetSettlementHistoryByInstrumentGet(const QString& instrument_name, const QString& type, const qint32& count) {
    QString fullPath;
    fullPath.append(this->host).append(this->basePath).append("/private/get_settlement_history_by_instrument");
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("instrument_name"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(instrument_name)));
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("type"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(type)));
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("count"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(count)));
    
    OAIHttpRequestWorker *worker = new OAIHttpRequestWorker();
    OAIHttpRequestInput input(fullPath, "GET");


    foreach(QString key, this->defaultHeaders.keys()) {
        input.headers.insert(key, this->defaultHeaders.value(key));
    }

    connect(worker,
            &OAIHttpRequestWorker::on_execution_finished,
            this,
            &OAIPrivateApi::privateGetSettlementHistoryByInstrumentGetCallback);

    worker->execute(&input);
}

void
OAIPrivateApi::privateGetSettlementHistoryByInstrumentGetCallback(OAIHttpRequestWorker * worker) {
    QString msg;
    QString error_str = worker->error_str;
    QNetworkReply::NetworkError error_type = worker->error_type;

    if (worker->error_type == QNetworkReply::NoError) {
        msg = QString("Success! %1 bytes").arg(worker->response.length());
    }
    else {
        msg = "Error: " + worker->error_str;
    }
    OAIObject output(QString(worker->response));
    worker->deleteLater();

    if (worker->error_type == QNetworkReply::NoError) {
        emit privateGetSettlementHistoryByInstrumentGetSignal(output);
        emit privateGetSettlementHistoryByInstrumentGetSignalFull(worker, output);
    } else {
        emit privateGetSettlementHistoryByInstrumentGetSignalE(output, error_type, error_str);
        emit privateGetSettlementHistoryByInstrumentGetSignalEFull(worker, error_type, error_str);
    }
}

void
OAIPrivateApi::privateGetSubaccountsGet(const bool& with_portfolio) {
    QString fullPath;
    fullPath.append(this->host).append(this->basePath).append("/private/get_subaccounts");
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("with_portfolio"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(with_portfolio)));
    
    OAIHttpRequestWorker *worker = new OAIHttpRequestWorker();
    OAIHttpRequestInput input(fullPath, "GET");


    foreach(QString key, this->defaultHeaders.keys()) {
        input.headers.insert(key, this->defaultHeaders.value(key));
    }

    connect(worker,
            &OAIHttpRequestWorker::on_execution_finished,
            this,
            &OAIPrivateApi::privateGetSubaccountsGetCallback);

    worker->execute(&input);
}

void
OAIPrivateApi::privateGetSubaccountsGetCallback(OAIHttpRequestWorker * worker) {
    QString msg;
    QString error_str = worker->error_str;
    QNetworkReply::NetworkError error_type = worker->error_type;

    if (worker->error_type == QNetworkReply::NoError) {
        msg = QString("Success! %1 bytes").arg(worker->response.length());
    }
    else {
        msg = "Error: " + worker->error_str;
    }
    OAIObject output(QString(worker->response));
    worker->deleteLater();

    if (worker->error_type == QNetworkReply::NoError) {
        emit privateGetSubaccountsGetSignal(output);
        emit privateGetSubaccountsGetSignalFull(worker, output);
    } else {
        emit privateGetSubaccountsGetSignalE(output, error_type, error_str);
        emit privateGetSubaccountsGetSignalEFull(worker, error_type, error_str);
    }
}

void
OAIPrivateApi::privateGetTransfersGet(const QString& currency, const qint32& count, const qint32& offset) {
    QString fullPath;
    fullPath.append(this->host).append(this->basePath).append("/private/get_transfers");
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("currency"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(currency)));
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("count"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(count)));
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("offset"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(offset)));
    
    OAIHttpRequestWorker *worker = new OAIHttpRequestWorker();
    OAIHttpRequestInput input(fullPath, "GET");


    foreach(QString key, this->defaultHeaders.keys()) {
        input.headers.insert(key, this->defaultHeaders.value(key));
    }

    connect(worker,
            &OAIHttpRequestWorker::on_execution_finished,
            this,
            &OAIPrivateApi::privateGetTransfersGetCallback);

    worker->execute(&input);
}

void
OAIPrivateApi::privateGetTransfersGetCallback(OAIHttpRequestWorker * worker) {
    QString msg;
    QString error_str = worker->error_str;
    QNetworkReply::NetworkError error_type = worker->error_type;

    if (worker->error_type == QNetworkReply::NoError) {
        msg = QString("Success! %1 bytes").arg(worker->response.length());
    }
    else {
        msg = "Error: " + worker->error_str;
    }
    OAIObject output(QString(worker->response));
    worker->deleteLater();

    if (worker->error_type == QNetworkReply::NoError) {
        emit privateGetTransfersGetSignal(output);
        emit privateGetTransfersGetSignalFull(worker, output);
    } else {
        emit privateGetTransfersGetSignalE(output, error_type, error_str);
        emit privateGetTransfersGetSignalEFull(worker, error_type, error_str);
    }
}

void
OAIPrivateApi::privateGetUserTradesByCurrencyAndTimeGet(const QString& currency, const qint32& start_timestamp, const qint32& end_timestamp, const QString& kind, const qint32& count, const bool& include_old, const QString& sorting) {
    QString fullPath;
    fullPath.append(this->host).append(this->basePath).append("/private/get_user_trades_by_currency_and_time");
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("currency"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(currency)));
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("kind"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(kind)));
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("start_timestamp"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(start_timestamp)));
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("end_timestamp"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(end_timestamp)));
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("count"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(count)));
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("include_old"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(include_old)));
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("sorting"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(sorting)));
    
    OAIHttpRequestWorker *worker = new OAIHttpRequestWorker();
    OAIHttpRequestInput input(fullPath, "GET");


    foreach(QString key, this->defaultHeaders.keys()) {
        input.headers.insert(key, this->defaultHeaders.value(key));
    }

    connect(worker,
            &OAIHttpRequestWorker::on_execution_finished,
            this,
            &OAIPrivateApi::privateGetUserTradesByCurrencyAndTimeGetCallback);

    worker->execute(&input);
}

void
OAIPrivateApi::privateGetUserTradesByCurrencyAndTimeGetCallback(OAIHttpRequestWorker * worker) {
    QString msg;
    QString error_str = worker->error_str;
    QNetworkReply::NetworkError error_type = worker->error_type;

    if (worker->error_type == QNetworkReply::NoError) {
        msg = QString("Success! %1 bytes").arg(worker->response.length());
    }
    else {
        msg = "Error: " + worker->error_str;
    }
    OAIObject output(QString(worker->response));
    worker->deleteLater();

    if (worker->error_type == QNetworkReply::NoError) {
        emit privateGetUserTradesByCurrencyAndTimeGetSignal(output);
        emit privateGetUserTradesByCurrencyAndTimeGetSignalFull(worker, output);
    } else {
        emit privateGetUserTradesByCurrencyAndTimeGetSignalE(output, error_type, error_str);
        emit privateGetUserTradesByCurrencyAndTimeGetSignalEFull(worker, error_type, error_str);
    }
}

void
OAIPrivateApi::privateGetUserTradesByCurrencyGet(const QString& currency, const QString& kind, const QString& start_id, const QString& end_id, const qint32& count, const bool& include_old, const QString& sorting) {
    QString fullPath;
    fullPath.append(this->host).append(this->basePath).append("/private/get_user_trades_by_currency");
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("currency"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(currency)));
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("kind"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(kind)));
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("start_id"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(start_id)));
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("end_id"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(end_id)));
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("count"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(count)));
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("include_old"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(include_old)));
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("sorting"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(sorting)));
    
    OAIHttpRequestWorker *worker = new OAIHttpRequestWorker();
    OAIHttpRequestInput input(fullPath, "GET");


    foreach(QString key, this->defaultHeaders.keys()) {
        input.headers.insert(key, this->defaultHeaders.value(key));
    }

    connect(worker,
            &OAIHttpRequestWorker::on_execution_finished,
            this,
            &OAIPrivateApi::privateGetUserTradesByCurrencyGetCallback);

    worker->execute(&input);
}

void
OAIPrivateApi::privateGetUserTradesByCurrencyGetCallback(OAIHttpRequestWorker * worker) {
    QString msg;
    QString error_str = worker->error_str;
    QNetworkReply::NetworkError error_type = worker->error_type;

    if (worker->error_type == QNetworkReply::NoError) {
        msg = QString("Success! %1 bytes").arg(worker->response.length());
    }
    else {
        msg = "Error: " + worker->error_str;
    }
    OAIObject output(QString(worker->response));
    worker->deleteLater();

    if (worker->error_type == QNetworkReply::NoError) {
        emit privateGetUserTradesByCurrencyGetSignal(output);
        emit privateGetUserTradesByCurrencyGetSignalFull(worker, output);
    } else {
        emit privateGetUserTradesByCurrencyGetSignalE(output, error_type, error_str);
        emit privateGetUserTradesByCurrencyGetSignalEFull(worker, error_type, error_str);
    }
}

void
OAIPrivateApi::privateGetUserTradesByInstrumentAndTimeGet(const QString& instrument_name, const qint32& start_timestamp, const qint32& end_timestamp, const qint32& count, const bool& include_old, const QString& sorting) {
    QString fullPath;
    fullPath.append(this->host).append(this->basePath).append("/private/get_user_trades_by_instrument_and_time");
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("instrument_name"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(instrument_name)));
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("start_timestamp"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(start_timestamp)));
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("end_timestamp"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(end_timestamp)));
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("count"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(count)));
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("include_old"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(include_old)));
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("sorting"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(sorting)));
    
    OAIHttpRequestWorker *worker = new OAIHttpRequestWorker();
    OAIHttpRequestInput input(fullPath, "GET");


    foreach(QString key, this->defaultHeaders.keys()) {
        input.headers.insert(key, this->defaultHeaders.value(key));
    }

    connect(worker,
            &OAIHttpRequestWorker::on_execution_finished,
            this,
            &OAIPrivateApi::privateGetUserTradesByInstrumentAndTimeGetCallback);

    worker->execute(&input);
}

void
OAIPrivateApi::privateGetUserTradesByInstrumentAndTimeGetCallback(OAIHttpRequestWorker * worker) {
    QString msg;
    QString error_str = worker->error_str;
    QNetworkReply::NetworkError error_type = worker->error_type;

    if (worker->error_type == QNetworkReply::NoError) {
        msg = QString("Success! %1 bytes").arg(worker->response.length());
    }
    else {
        msg = "Error: " + worker->error_str;
    }
    OAIObject output(QString(worker->response));
    worker->deleteLater();

    if (worker->error_type == QNetworkReply::NoError) {
        emit privateGetUserTradesByInstrumentAndTimeGetSignal(output);
        emit privateGetUserTradesByInstrumentAndTimeGetSignalFull(worker, output);
    } else {
        emit privateGetUserTradesByInstrumentAndTimeGetSignalE(output, error_type, error_str);
        emit privateGetUserTradesByInstrumentAndTimeGetSignalEFull(worker, error_type, error_str);
    }
}

void
OAIPrivateApi::privateGetUserTradesByInstrumentGet(const QString& instrument_name, const qint32& start_seq, const qint32& end_seq, const qint32& count, const bool& include_old, const QString& sorting) {
    QString fullPath;
    fullPath.append(this->host).append(this->basePath).append("/private/get_user_trades_by_instrument");
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("instrument_name"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(instrument_name)));
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("start_seq"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(start_seq)));
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("end_seq"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(end_seq)));
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("count"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(count)));
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("include_old"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(include_old)));
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("sorting"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(sorting)));
    
    OAIHttpRequestWorker *worker = new OAIHttpRequestWorker();
    OAIHttpRequestInput input(fullPath, "GET");


    foreach(QString key, this->defaultHeaders.keys()) {
        input.headers.insert(key, this->defaultHeaders.value(key));
    }

    connect(worker,
            &OAIHttpRequestWorker::on_execution_finished,
            this,
            &OAIPrivateApi::privateGetUserTradesByInstrumentGetCallback);

    worker->execute(&input);
}

void
OAIPrivateApi::privateGetUserTradesByInstrumentGetCallback(OAIHttpRequestWorker * worker) {
    QString msg;
    QString error_str = worker->error_str;
    QNetworkReply::NetworkError error_type = worker->error_type;

    if (worker->error_type == QNetworkReply::NoError) {
        msg = QString("Success! %1 bytes").arg(worker->response.length());
    }
    else {
        msg = "Error: " + worker->error_str;
    }
    OAIObject output(QString(worker->response));
    worker->deleteLater();

    if (worker->error_type == QNetworkReply::NoError) {
        emit privateGetUserTradesByInstrumentGetSignal(output);
        emit privateGetUserTradesByInstrumentGetSignalFull(worker, output);
    } else {
        emit privateGetUserTradesByInstrumentGetSignalE(output, error_type, error_str);
        emit privateGetUserTradesByInstrumentGetSignalEFull(worker, error_type, error_str);
    }
}

void
OAIPrivateApi::privateGetUserTradesByOrderGet(const QString& order_id, const QString& sorting) {
    QString fullPath;
    fullPath.append(this->host).append(this->basePath).append("/private/get_user_trades_by_order");
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("order_id"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(order_id)));
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("sorting"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(sorting)));
    
    OAIHttpRequestWorker *worker = new OAIHttpRequestWorker();
    OAIHttpRequestInput input(fullPath, "GET");


    foreach(QString key, this->defaultHeaders.keys()) {
        input.headers.insert(key, this->defaultHeaders.value(key));
    }

    connect(worker,
            &OAIHttpRequestWorker::on_execution_finished,
            this,
            &OAIPrivateApi::privateGetUserTradesByOrderGetCallback);

    worker->execute(&input);
}

void
OAIPrivateApi::privateGetUserTradesByOrderGetCallback(OAIHttpRequestWorker * worker) {
    QString msg;
    QString error_str = worker->error_str;
    QNetworkReply::NetworkError error_type = worker->error_type;

    if (worker->error_type == QNetworkReply::NoError) {
        msg = QString("Success! %1 bytes").arg(worker->response.length());
    }
    else {
        msg = "Error: " + worker->error_str;
    }
    OAIObject output(QString(worker->response));
    worker->deleteLater();

    if (worker->error_type == QNetworkReply::NoError) {
        emit privateGetUserTradesByOrderGetSignal(output);
        emit privateGetUserTradesByOrderGetSignalFull(worker, output);
    } else {
        emit privateGetUserTradesByOrderGetSignalE(output, error_type, error_str);
        emit privateGetUserTradesByOrderGetSignalEFull(worker, error_type, error_str);
    }
}

void
OAIPrivateApi::privateGetWithdrawalsGet(const QString& currency, const qint32& count, const qint32& offset) {
    QString fullPath;
    fullPath.append(this->host).append(this->basePath).append("/private/get_withdrawals");
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("currency"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(currency)));
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("count"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(count)));
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("offset"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(offset)));
    
    OAIHttpRequestWorker *worker = new OAIHttpRequestWorker();
    OAIHttpRequestInput input(fullPath, "GET");


    foreach(QString key, this->defaultHeaders.keys()) {
        input.headers.insert(key, this->defaultHeaders.value(key));
    }

    connect(worker,
            &OAIHttpRequestWorker::on_execution_finished,
            this,
            &OAIPrivateApi::privateGetWithdrawalsGetCallback);

    worker->execute(&input);
}

void
OAIPrivateApi::privateGetWithdrawalsGetCallback(OAIHttpRequestWorker * worker) {
    QString msg;
    QString error_str = worker->error_str;
    QNetworkReply::NetworkError error_type = worker->error_type;

    if (worker->error_type == QNetworkReply::NoError) {
        msg = QString("Success! %1 bytes").arg(worker->response.length());
    }
    else {
        msg = "Error: " + worker->error_str;
    }
    OAIObject output(QString(worker->response));
    worker->deleteLater();

    if (worker->error_type == QNetworkReply::NoError) {
        emit privateGetWithdrawalsGetSignal(output);
        emit privateGetWithdrawalsGetSignalFull(worker, output);
    } else {
        emit privateGetWithdrawalsGetSignalE(output, error_type, error_str);
        emit privateGetWithdrawalsGetSignalEFull(worker, error_type, error_str);
    }
}

void
OAIPrivateApi::privateRemoveFromAddressBookGet(const QString& currency, const QString& type, const QString& address, const QString& tfa) {
    QString fullPath;
    fullPath.append(this->host).append(this->basePath).append("/private/remove_from_address_book");
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("currency"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(currency)));
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("type"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(type)));
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("address"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(address)));
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("tfa"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(tfa)));
    
    OAIHttpRequestWorker *worker = new OAIHttpRequestWorker();
    OAIHttpRequestInput input(fullPath, "GET");


    foreach(QString key, this->defaultHeaders.keys()) {
        input.headers.insert(key, this->defaultHeaders.value(key));
    }

    connect(worker,
            &OAIHttpRequestWorker::on_execution_finished,
            this,
            &OAIPrivateApi::privateRemoveFromAddressBookGetCallback);

    worker->execute(&input);
}

void
OAIPrivateApi::privateRemoveFromAddressBookGetCallback(OAIHttpRequestWorker * worker) {
    QString msg;
    QString error_str = worker->error_str;
    QNetworkReply::NetworkError error_type = worker->error_type;

    if (worker->error_type == QNetworkReply::NoError) {
        msg = QString("Success! %1 bytes").arg(worker->response.length());
    }
    else {
        msg = "Error: " + worker->error_str;
    }
    OAIObject output(QString(worker->response));
    worker->deleteLater();

    if (worker->error_type == QNetworkReply::NoError) {
        emit privateRemoveFromAddressBookGetSignal(output);
        emit privateRemoveFromAddressBookGetSignalFull(worker, output);
    } else {
        emit privateRemoveFromAddressBookGetSignalE(output, error_type, error_str);
        emit privateRemoveFromAddressBookGetSignalEFull(worker, error_type, error_str);
    }
}

void
OAIPrivateApi::privateSellGet(const QString& instrument_name, const double& amount, const QString& type, const QString& label, const double& price, const QString& time_in_force, const double& max_show, const bool& post_only, const bool& reduce_only, const double& stop_price, const QString& trigger, const QString& advanced) {
    QString fullPath;
    fullPath.append(this->host).append(this->basePath).append("/private/sell");
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("instrument_name"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(instrument_name)));
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("amount"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(amount)));
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("type"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(type)));
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("label"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(label)));
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("price"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(price)));
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("time_in_force"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(time_in_force)));
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("max_show"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(max_show)));
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("post_only"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(post_only)));
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("reduce_only"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(reduce_only)));
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("stop_price"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(stop_price)));
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("trigger"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(trigger)));
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("advanced"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(advanced)));
    
    OAIHttpRequestWorker *worker = new OAIHttpRequestWorker();
    OAIHttpRequestInput input(fullPath, "GET");


    foreach(QString key, this->defaultHeaders.keys()) {
        input.headers.insert(key, this->defaultHeaders.value(key));
    }

    connect(worker,
            &OAIHttpRequestWorker::on_execution_finished,
            this,
            &OAIPrivateApi::privateSellGetCallback);

    worker->execute(&input);
}

void
OAIPrivateApi::privateSellGetCallback(OAIHttpRequestWorker * worker) {
    QString msg;
    QString error_str = worker->error_str;
    QNetworkReply::NetworkError error_type = worker->error_type;

    if (worker->error_type == QNetworkReply::NoError) {
        msg = QString("Success! %1 bytes").arg(worker->response.length());
    }
    else {
        msg = "Error: " + worker->error_str;
    }
    OAIObject output(QString(worker->response));
    worker->deleteLater();

    if (worker->error_type == QNetworkReply::NoError) {
        emit privateSellGetSignal(output);
        emit privateSellGetSignalFull(worker, output);
    } else {
        emit privateSellGetSignalE(output, error_type, error_str);
        emit privateSellGetSignalEFull(worker, error_type, error_str);
    }
}

void
OAIPrivateApi::privateSetAnnouncementAsReadGet(const double& announcement_id) {
    QString fullPath;
    fullPath.append(this->host).append(this->basePath).append("/private/set_announcement_as_read");
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("announcement_id"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(announcement_id)));
    
    OAIHttpRequestWorker *worker = new OAIHttpRequestWorker();
    OAIHttpRequestInput input(fullPath, "GET");


    foreach(QString key, this->defaultHeaders.keys()) {
        input.headers.insert(key, this->defaultHeaders.value(key));
    }

    connect(worker,
            &OAIHttpRequestWorker::on_execution_finished,
            this,
            &OAIPrivateApi::privateSetAnnouncementAsReadGetCallback);

    worker->execute(&input);
}

void
OAIPrivateApi::privateSetAnnouncementAsReadGetCallback(OAIHttpRequestWorker * worker) {
    QString msg;
    QString error_str = worker->error_str;
    QNetworkReply::NetworkError error_type = worker->error_type;

    if (worker->error_type == QNetworkReply::NoError) {
        msg = QString("Success! %1 bytes").arg(worker->response.length());
    }
    else {
        msg = "Error: " + worker->error_str;
    }
    OAIObject output(QString(worker->response));
    worker->deleteLater();

    if (worker->error_type == QNetworkReply::NoError) {
        emit privateSetAnnouncementAsReadGetSignal(output);
        emit privateSetAnnouncementAsReadGetSignalFull(worker, output);
    } else {
        emit privateSetAnnouncementAsReadGetSignalE(output, error_type, error_str);
        emit privateSetAnnouncementAsReadGetSignalEFull(worker, error_type, error_str);
    }
}

void
OAIPrivateApi::privateSetEmailForSubaccountGet(const qint32& sid, const QString& email) {
    QString fullPath;
    fullPath.append(this->host).append(this->basePath).append("/private/set_email_for_subaccount");
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("sid"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(sid)));
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("email"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(email)));
    
    OAIHttpRequestWorker *worker = new OAIHttpRequestWorker();
    OAIHttpRequestInput input(fullPath, "GET");


    foreach(QString key, this->defaultHeaders.keys()) {
        input.headers.insert(key, this->defaultHeaders.value(key));
    }

    connect(worker,
            &OAIHttpRequestWorker::on_execution_finished,
            this,
            &OAIPrivateApi::privateSetEmailForSubaccountGetCallback);

    worker->execute(&input);
}

void
OAIPrivateApi::privateSetEmailForSubaccountGetCallback(OAIHttpRequestWorker * worker) {
    QString msg;
    QString error_str = worker->error_str;
    QNetworkReply::NetworkError error_type = worker->error_type;

    if (worker->error_type == QNetworkReply::NoError) {
        msg = QString("Success! %1 bytes").arg(worker->response.length());
    }
    else {
        msg = "Error: " + worker->error_str;
    }
    OAIObject output(QString(worker->response));
    worker->deleteLater();

    if (worker->error_type == QNetworkReply::NoError) {
        emit privateSetEmailForSubaccountGetSignal(output);
        emit privateSetEmailForSubaccountGetSignalFull(worker, output);
    } else {
        emit privateSetEmailForSubaccountGetSignalE(output, error_type, error_str);
        emit privateSetEmailForSubaccountGetSignalEFull(worker, error_type, error_str);
    }
}

void
OAIPrivateApi::privateSetEmailLanguageGet(const QString& language) {
    QString fullPath;
    fullPath.append(this->host).append(this->basePath).append("/private/set_email_language");
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("language"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(language)));
    
    OAIHttpRequestWorker *worker = new OAIHttpRequestWorker();
    OAIHttpRequestInput input(fullPath, "GET");


    foreach(QString key, this->defaultHeaders.keys()) {
        input.headers.insert(key, this->defaultHeaders.value(key));
    }

    connect(worker,
            &OAIHttpRequestWorker::on_execution_finished,
            this,
            &OAIPrivateApi::privateSetEmailLanguageGetCallback);

    worker->execute(&input);
}

void
OAIPrivateApi::privateSetEmailLanguageGetCallback(OAIHttpRequestWorker * worker) {
    QString msg;
    QString error_str = worker->error_str;
    QNetworkReply::NetworkError error_type = worker->error_type;

    if (worker->error_type == QNetworkReply::NoError) {
        msg = QString("Success! %1 bytes").arg(worker->response.length());
    }
    else {
        msg = "Error: " + worker->error_str;
    }
    OAIObject output(QString(worker->response));
    worker->deleteLater();

    if (worker->error_type == QNetworkReply::NoError) {
        emit privateSetEmailLanguageGetSignal(output);
        emit privateSetEmailLanguageGetSignalFull(worker, output);
    } else {
        emit privateSetEmailLanguageGetSignalE(output, error_type, error_str);
        emit privateSetEmailLanguageGetSignalEFull(worker, error_type, error_str);
    }
}

void
OAIPrivateApi::privateSetPasswordForSubaccountGet(const qint32& sid, const QString& password) {
    QString fullPath;
    fullPath.append(this->host).append(this->basePath).append("/private/set_password_for_subaccount");
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("sid"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(sid)));
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("password"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(password)));
    
    OAIHttpRequestWorker *worker = new OAIHttpRequestWorker();
    OAIHttpRequestInput input(fullPath, "GET");


    foreach(QString key, this->defaultHeaders.keys()) {
        input.headers.insert(key, this->defaultHeaders.value(key));
    }

    connect(worker,
            &OAIHttpRequestWorker::on_execution_finished,
            this,
            &OAIPrivateApi::privateSetPasswordForSubaccountGetCallback);

    worker->execute(&input);
}

void
OAIPrivateApi::privateSetPasswordForSubaccountGetCallback(OAIHttpRequestWorker * worker) {
    QString msg;
    QString error_str = worker->error_str;
    QNetworkReply::NetworkError error_type = worker->error_type;

    if (worker->error_type == QNetworkReply::NoError) {
        msg = QString("Success! %1 bytes").arg(worker->response.length());
    }
    else {
        msg = "Error: " + worker->error_str;
    }
    OAIObject output(QString(worker->response));
    worker->deleteLater();

    if (worker->error_type == QNetworkReply::NoError) {
        emit privateSetPasswordForSubaccountGetSignal(output);
        emit privateSetPasswordForSubaccountGetSignalFull(worker, output);
    } else {
        emit privateSetPasswordForSubaccountGetSignalE(output, error_type, error_str);
        emit privateSetPasswordForSubaccountGetSignalEFull(worker, error_type, error_str);
    }
}

void
OAIPrivateApi::privateSubmitTransferToSubaccountGet(const QString& currency, const double& amount, const qint32& destination) {
    QString fullPath;
    fullPath.append(this->host).append(this->basePath).append("/private/submit_transfer_to_subaccount");
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("currency"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(currency)));
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("amount"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(amount)));
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("destination"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(destination)));
    
    OAIHttpRequestWorker *worker = new OAIHttpRequestWorker();
    OAIHttpRequestInput input(fullPath, "GET");


    foreach(QString key, this->defaultHeaders.keys()) {
        input.headers.insert(key, this->defaultHeaders.value(key));
    }

    connect(worker,
            &OAIHttpRequestWorker::on_execution_finished,
            this,
            &OAIPrivateApi::privateSubmitTransferToSubaccountGetCallback);

    worker->execute(&input);
}

void
OAIPrivateApi::privateSubmitTransferToSubaccountGetCallback(OAIHttpRequestWorker * worker) {
    QString msg;
    QString error_str = worker->error_str;
    QNetworkReply::NetworkError error_type = worker->error_type;

    if (worker->error_type == QNetworkReply::NoError) {
        msg = QString("Success! %1 bytes").arg(worker->response.length());
    }
    else {
        msg = "Error: " + worker->error_str;
    }
    OAIObject output(QString(worker->response));
    worker->deleteLater();

    if (worker->error_type == QNetworkReply::NoError) {
        emit privateSubmitTransferToSubaccountGetSignal(output);
        emit privateSubmitTransferToSubaccountGetSignalFull(worker, output);
    } else {
        emit privateSubmitTransferToSubaccountGetSignalE(output, error_type, error_str);
        emit privateSubmitTransferToSubaccountGetSignalEFull(worker, error_type, error_str);
    }
}

void
OAIPrivateApi::privateSubmitTransferToUserGet(const QString& currency, const double& amount, const QString& destination, const QString& tfa) {
    QString fullPath;
    fullPath.append(this->host).append(this->basePath).append("/private/submit_transfer_to_user");
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("currency"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(currency)));
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("amount"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(amount)));
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("destination"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(destination)));
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("tfa"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(tfa)));
    
    OAIHttpRequestWorker *worker = new OAIHttpRequestWorker();
    OAIHttpRequestInput input(fullPath, "GET");


    foreach(QString key, this->defaultHeaders.keys()) {
        input.headers.insert(key, this->defaultHeaders.value(key));
    }

    connect(worker,
            &OAIHttpRequestWorker::on_execution_finished,
            this,
            &OAIPrivateApi::privateSubmitTransferToUserGetCallback);

    worker->execute(&input);
}

void
OAIPrivateApi::privateSubmitTransferToUserGetCallback(OAIHttpRequestWorker * worker) {
    QString msg;
    QString error_str = worker->error_str;
    QNetworkReply::NetworkError error_type = worker->error_type;

    if (worker->error_type == QNetworkReply::NoError) {
        msg = QString("Success! %1 bytes").arg(worker->response.length());
    }
    else {
        msg = "Error: " + worker->error_str;
    }
    OAIObject output(QString(worker->response));
    worker->deleteLater();

    if (worker->error_type == QNetworkReply::NoError) {
        emit privateSubmitTransferToUserGetSignal(output);
        emit privateSubmitTransferToUserGetSignalFull(worker, output);
    } else {
        emit privateSubmitTransferToUserGetSignalE(output, error_type, error_str);
        emit privateSubmitTransferToUserGetSignalEFull(worker, error_type, error_str);
    }
}

void
OAIPrivateApi::privateToggleDepositAddressCreationGet(const QString& currency, const bool& state) {
    QString fullPath;
    fullPath.append(this->host).append(this->basePath).append("/private/toggle_deposit_address_creation");
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("currency"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(currency)));
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("state"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(state)));
    
    OAIHttpRequestWorker *worker = new OAIHttpRequestWorker();
    OAIHttpRequestInput input(fullPath, "GET");


    foreach(QString key, this->defaultHeaders.keys()) {
        input.headers.insert(key, this->defaultHeaders.value(key));
    }

    connect(worker,
            &OAIHttpRequestWorker::on_execution_finished,
            this,
            &OAIPrivateApi::privateToggleDepositAddressCreationGetCallback);

    worker->execute(&input);
}

void
OAIPrivateApi::privateToggleDepositAddressCreationGetCallback(OAIHttpRequestWorker * worker) {
    QString msg;
    QString error_str = worker->error_str;
    QNetworkReply::NetworkError error_type = worker->error_type;

    if (worker->error_type == QNetworkReply::NoError) {
        msg = QString("Success! %1 bytes").arg(worker->response.length());
    }
    else {
        msg = "Error: " + worker->error_str;
    }
    OAIObject output(QString(worker->response));
    worker->deleteLater();

    if (worker->error_type == QNetworkReply::NoError) {
        emit privateToggleDepositAddressCreationGetSignal(output);
        emit privateToggleDepositAddressCreationGetSignalFull(worker, output);
    } else {
        emit privateToggleDepositAddressCreationGetSignalE(output, error_type, error_str);
        emit privateToggleDepositAddressCreationGetSignalEFull(worker, error_type, error_str);
    }
}

void
OAIPrivateApi::privateToggleNotificationsFromSubaccountGet(const qint32& sid, const bool& state) {
    QString fullPath;
    fullPath.append(this->host).append(this->basePath).append("/private/toggle_notifications_from_subaccount");
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("sid"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(sid)));
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("state"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(state)));
    
    OAIHttpRequestWorker *worker = new OAIHttpRequestWorker();
    OAIHttpRequestInput input(fullPath, "GET");


    foreach(QString key, this->defaultHeaders.keys()) {
        input.headers.insert(key, this->defaultHeaders.value(key));
    }

    connect(worker,
            &OAIHttpRequestWorker::on_execution_finished,
            this,
            &OAIPrivateApi::privateToggleNotificationsFromSubaccountGetCallback);

    worker->execute(&input);
}

void
OAIPrivateApi::privateToggleNotificationsFromSubaccountGetCallback(OAIHttpRequestWorker * worker) {
    QString msg;
    QString error_str = worker->error_str;
    QNetworkReply::NetworkError error_type = worker->error_type;

    if (worker->error_type == QNetworkReply::NoError) {
        msg = QString("Success! %1 bytes").arg(worker->response.length());
    }
    else {
        msg = "Error: " + worker->error_str;
    }
    OAIObject output(QString(worker->response));
    worker->deleteLater();

    if (worker->error_type == QNetworkReply::NoError) {
        emit privateToggleNotificationsFromSubaccountGetSignal(output);
        emit privateToggleNotificationsFromSubaccountGetSignalFull(worker, output);
    } else {
        emit privateToggleNotificationsFromSubaccountGetSignalE(output, error_type, error_str);
        emit privateToggleNotificationsFromSubaccountGetSignalEFull(worker, error_type, error_str);
    }
}

void
OAIPrivateApi::privateToggleSubaccountLoginGet(const qint32& sid, const QString& state) {
    QString fullPath;
    fullPath.append(this->host).append(this->basePath).append("/private/toggle_subaccount_login");
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("sid"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(sid)));
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("state"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(state)));
    
    OAIHttpRequestWorker *worker = new OAIHttpRequestWorker();
    OAIHttpRequestInput input(fullPath, "GET");


    foreach(QString key, this->defaultHeaders.keys()) {
        input.headers.insert(key, this->defaultHeaders.value(key));
    }

    connect(worker,
            &OAIHttpRequestWorker::on_execution_finished,
            this,
            &OAIPrivateApi::privateToggleSubaccountLoginGetCallback);

    worker->execute(&input);
}

void
OAIPrivateApi::privateToggleSubaccountLoginGetCallback(OAIHttpRequestWorker * worker) {
    QString msg;
    QString error_str = worker->error_str;
    QNetworkReply::NetworkError error_type = worker->error_type;

    if (worker->error_type == QNetworkReply::NoError) {
        msg = QString("Success! %1 bytes").arg(worker->response.length());
    }
    else {
        msg = "Error: " + worker->error_str;
    }
    OAIObject output(QString(worker->response));
    worker->deleteLater();

    if (worker->error_type == QNetworkReply::NoError) {
        emit privateToggleSubaccountLoginGetSignal(output);
        emit privateToggleSubaccountLoginGetSignalFull(worker, output);
    } else {
        emit privateToggleSubaccountLoginGetSignalE(output, error_type, error_str);
        emit privateToggleSubaccountLoginGetSignalEFull(worker, error_type, error_str);
    }
}

void
OAIPrivateApi::privateWithdrawGet(const QString& currency, const QString& address, const double& amount, const QString& priority, const QString& tfa) {
    QString fullPath;
    fullPath.append(this->host).append(this->basePath).append("/private/withdraw");
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("currency"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(currency)));
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("address"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(address)));
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("amount"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(amount)));
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("priority"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(priority)));
    
    if (fullPath.indexOf("?") > 0)
      fullPath.append("&");
    else
      fullPath.append("?");
    fullPath.append(QUrl::toPercentEncoding("tfa"))
        .append("=")
        .append(QUrl::toPercentEncoding(::OpenAPI::toStringValue(tfa)));
    
    OAIHttpRequestWorker *worker = new OAIHttpRequestWorker();
    OAIHttpRequestInput input(fullPath, "GET");


    foreach(QString key, this->defaultHeaders.keys()) {
        input.headers.insert(key, this->defaultHeaders.value(key));
    }

    connect(worker,
            &OAIHttpRequestWorker::on_execution_finished,
            this,
            &OAIPrivateApi::privateWithdrawGetCallback);

    worker->execute(&input);
}

void
OAIPrivateApi::privateWithdrawGetCallback(OAIHttpRequestWorker * worker) {
    QString msg;
    QString error_str = worker->error_str;
    QNetworkReply::NetworkError error_type = worker->error_type;

    if (worker->error_type == QNetworkReply::NoError) {
        msg = QString("Success! %1 bytes").arg(worker->response.length());
    }
    else {
        msg = "Error: " + worker->error_str;
    }
    OAIObject output(QString(worker->response));
    worker->deleteLater();

    if (worker->error_type == QNetworkReply::NoError) {
        emit privateWithdrawGetSignal(output);
        emit privateWithdrawGetSignalFull(worker, output);
    } else {
        emit privateWithdrawGetSignalE(output, error_type, error_str);
        emit privateWithdrawGetSignalEFull(worker, error_type, error_str);
    }
}


}
