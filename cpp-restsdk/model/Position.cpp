/**
 * Deribit API
 * #Overview  Deribit provides three different interfaces to access the API:  * [JSON-RPC over Websocket](#json-rpc) * [JSON-RPC over HTTP](#json-rpc) * [FIX](#fix-api) (Financial Information eXchange)  With the API Console you can use and test the JSON-RPC API, both via HTTP and  via Websocket. To visit the API console, go to __Account > API tab >  API Console tab.__   ##Naming Deribit tradeable assets or instruments use the following system of naming:  |Kind|Examples|Template|Comments| |----|--------|--------|--------| |Future|<code>BTC-25MAR16</code>, <code>BTC-5AUG16</code>|<code>BTC-DMMMYY</code>|<code>BTC</code> is currency, <code>DMMMYY</code> is expiration date, <code>D</code> stands for day of month (1 or 2 digits), <code>MMM</code> - month (3 first letters in English), <code>YY</code> stands for year.| |Perpetual|<code>BTC-PERPETUAL</code>                        ||Perpetual contract for currency <code>BTC</code>.| |Option|<code>BTC-25MAR16-420-C</code>, <code>BTC-5AUG16-580-P</code>|<code>BTC-DMMMYY-STRIKE-K</code>|<code>STRIKE</code> is option strike price in USD. Template <code>K</code> is option kind: <code>C</code> for call options or <code>P</code> for put options.|   # JSON-RPC JSON-RPC is a light-weight remote procedure call (RPC) protocol. The  [JSON-RPC specification](https://www.jsonrpc.org/specification) defines the data structures that are used for the messages that are exchanged between client and server, as well as the rules around their processing. JSON-RPC uses JSON (RFC 4627) as data format.  JSON-RPC is transport agnostic: it does not specify which transport mechanism must be used. The Deribit API supports both Websocket (preferred) and HTTP (with limitations: subscriptions are not supported over HTTP).  ## Request messages > An example of a request message:  ```json {     \"jsonrpc\": \"2.0\",     \"id\": 8066,     \"method\": \"public/ticker\",     \"params\": {         \"instrument\": \"BTC-24AUG18-6500-P\"     } } ```  According to the JSON-RPC sepcification the requests must be JSON objects with the following fields.  |Name|Type|Description| |----|----|-----------| |jsonrpc|string|The version of the JSON-RPC spec: \"2.0\"| |id|integer or string|An identifier of the request. If it is included, then the response will contain the same identifier| |method|string|The method to be invoked| |params|object|The parameters values for the method. The field names must match with the expected parameter names. The parameters that are expected are described in the documentation for the methods, below.|  <aside class=\"warning\"> The JSON-RPC specification describes two features that are currently not supported by the API:  <ul> <li>Specification of parameter values by position</li> <li>Batch requests</li> </ul>  </aside>   ## Response messages > An example of a response message:  ```json {     \"jsonrpc\": \"2.0\",     \"id\": 5239,     \"testnet\": false,     \"result\": [         {             \"currency\": \"BTC\",             \"currencyLong\": \"Bitcoin\",             \"minConfirmation\": 2,             \"txFee\": 0.0006,             \"isActive\": true,             \"coinType\": \"BITCOIN\",             \"baseAddress\": null         }     ],     \"usIn\": 1535043730126248,     \"usOut\": 1535043730126250,     \"usDiff\": 2 } ```  The JSON-RPC API always responds with a JSON object with the following fields.   |Name|Type|Description| |----|----|-----------| |id|integer|This is the same id that was sent in the request.| |result|any|If successful, the result of the API call. The format for the result is described with each method.| |error|error object|Only present if there was an error invoking the method. The error object is described below.| |testnet|boolean|Indicates whether the API in use is actually the test API.  <code>false</code> for production server, <code>true</code> for test server.| |usIn|integer|The timestamp when the requests was received (microseconds since the Unix epoch)| |usOut|integer|The timestamp when the response was sent (microseconds since the Unix epoch)| |usDiff|integer|The number of microseconds that was spent handling the request|  <aside class=\"notice\"> The fields <code>testnet</code>, <code>usIn</code>, <code>usOut</code> and <code>usDiff</code> are not part of the JSON-RPC standard.  <p>In order not to clutter the examples they will generally be omitted from the example code.</p> </aside>  > An example of a response with an error:  ```json {     \"jsonrpc\": \"2.0\",     \"id\": 8163,     \"error\": {         \"code\": 11050,         \"message\": \"bad_request\"     },     \"testnet\": false,     \"usIn\": 1535037392434763,     \"usOut\": 1535037392448119,     \"usDiff\": 13356 } ``` In case of an error the response message will contain the error field, with as value an object with the following with the following fields:  |Name|Type|Description |----|----|-----------| |code|integer|A number that indicates the kind of error.| |message|string|A short description that indicates the kind of error.| |data|any|Additional data about the error. This field may be omitted.|  ## Notifications  > An example of a notification:  ```json {     \"jsonrpc\": \"2.0\",     \"method\": \"subscription\",     \"params\": {         \"channel\": \"deribit_price_index.btc_usd\",         \"data\": {             \"timestamp\": 1535098298227,             \"price\": 6521.17,             \"index_name\": \"btc_usd\"         }     } } ```  API users can subscribe to certain types of notifications. This means that they will receive JSON-RPC notification-messages from the server when certain events occur, such as changes to the index price or changes to the order book for a certain instrument.   The API methods [public/subscribe](#public-subscribe) and [private/subscribe](#private-subscribe) are used to set up a subscription. Since HTTP does not support the sending of messages from server to client, these methods are only availble when using the Websocket transport mechanism.  At the moment of subscription a \"channel\" must be specified. The channel determines the type of events that will be received.  See [Subscriptions](#subscriptions) for more details about the channels.  In accordance with the JSON-RPC specification, the format of a notification  is that of a request message without an <code>id</code> field. The value of the <code>method</code> field will always be <code>\"subscription\"</code>. The <code>params</code> field will always be an object with 2 members: <code>channel</code> and <code>data</code>. The value of the <code>channel</code> member is the name of the channel (a string). The value of the <code>data</code> member is an object that contains data  that is specific for the channel.   ## Authentication  > An example of a JSON request with token:  ```json {     \"id\": 5647,     \"method\": \"private/get_subaccounts\",     \"params\": {         \"access_token\": \"67SVutDoVZSzkUStHSuk51WntMNBJ5mh5DYZhwzpiqDF\"     } } ```  The API consists of `public` and `private` methods. The public methods do not require authentication. The private methods use OAuth 2.0 authentication. This means that a valid OAuth access token must be included in the request, which can get achived by calling method [public/auth](#public-auth).  When the token was assigned to the user, it should be passed along, with other request parameters, back to the server:  |Connection type|Access token placement |----|-----------| |**Websocket**|Inside request JSON parameters, as an `access_token` field| |**HTTP (REST)**|Header `Authorization: bearer ```Token``` ` value|  ### Additional authorization method - basic user credentials  <span style=\"color:red\"><b> ! Not recommended - however, it could be useful for quick testing API</b></span></br>  Every `private` method could be accessed by providing, inside HTTP `Authorization: Basic XXX` header, values with user `ClientId` and assigned `ClientSecret` (both values can be found on the API page on the Deribit website) encoded with `Base64`:  <code>Authorization: Basic BASE64(`ClientId` + `:` + `ClientSecret`)</code>   ### Additional authorization method - Deribit signature credentials  The Derbit service provides dedicated authorization method, which harness user generated signature to increase security level for passing request data. Generated value is passed inside `Authorization` header, coded as:  <code>Authorization: deri-hmac-sha256 id=```ClientId```,ts=```Timestamp```,sig=```Signature```,nonce=```Nonce```</code>  where:  |Deribit credential|Description |----|-----------| |*ClientId*|Can be found on the API page on the Deribit website| |*Timestamp*|Time when the request was generated - given as **miliseconds**. It's valid for **60 seconds** since generation, after that time any request with an old timestamp will be rejected.| |*Signature*|Value for signature calculated as described below | |*Nonce*|Single usage, user generated initialization vector for the server token|  The signature is generated by the following formula:  <code> Signature = HEX_STRING( HMAC-SHA256( ClientSecret, StringToSign ) );</code></br>  <code> StringToSign =  Timestamp + \"\\n\" + Nonce + \"\\n\" + RequestData;</code></br>  <code> RequestData =  UPPERCASE(HTTP_METHOD())  + \"\\n\" + URI() + \"\\n\" + RequestBody + \"\\n\";</code></br>   e.g. (using shell with ```openssl``` tool):  <code>&nbsp;&nbsp;&nbsp;&nbsp;ClientId=AAAAAAAAAAA</code></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;ClientSecret=ABCD</code></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;Timestamp=$( date +%s000 )</code></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;Nonce=$( cat /dev/urandom | tr -dc 'a-z0-9' | head -c8 )</code></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;URI=\"/api/v2/private/get_account_summary?currency=BTC\"</code></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;HttpMethod=GET</code></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;Body=\"\"</code></br></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;Signature=$( echo -ne \"${Timestamp}\\n${Nonce}\\n${HttpMethod}\\n${URI}\\n${Body}\\n\" | openssl sha256 -r -hmac \"$ClientSecret\" | cut -f1 -d' ' )</code></br></br> <code>&nbsp;&nbsp;&nbsp;&nbsp;echo $Signature</code></br></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;shell output> ea40d5e5e4fae235ab22b61da98121fbf4acdc06db03d632e23c66bcccb90d2c  (**WARNING**: Exact value depends on current timestamp and client credentials</code></br></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;curl -s -X ${HttpMethod} -H \"Authorization: deri-hmac-sha256 id=${ClientId},ts=${Timestamp},nonce=${Nonce},sig=${Signature}\" \"https://www.deribit.com${URI}\"</code></br></br>    ### Additional authorization method - signature credentials (WebSocket API)  When connecting through Websocket, user can request for authorization using ```client_credential``` method, which requires providing following parameters (as a part of JSON request):  |JSON parameter|Description |----|-----------| |*grant_type*|Must be **client_signature**| |*client_id*|Can be found on the API page on the Deribit website| |*timestamp*|Time when the request was generated - given as **miliseconds**. It's valid for **60 seconds** since generation, after that time any request with an old timestamp will be rejected.| |*signature*|Value for signature calculated as described below | |*nonce*|Single usage, user generated initialization vector for the server token| |*data*|**Optional** field, which contains any user specific value|  The signature is generated by the following formula:  <code> StringToSign =  Timestamp + \"\\n\" + Nonce + \"\\n\" + Data;</code></br>  <code> Signature = HEX_STRING( HMAC-SHA256( ClientSecret, StringToSign ) );</code></br>   e.g. (using shell with ```openssl``` tool):  <code>&nbsp;&nbsp;&nbsp;&nbsp;ClientId=AAAAAAAAAAA</code></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;ClientSecret=ABCD</code></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;Timestamp=$( date +%s000 ) # e.g. 1554883365000 </code></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;Nonce=$( cat /dev/urandom | tr -dc 'a-z0-9' | head -c8 ) # e.g. fdbmmz79 </code></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;Data=\"\"</code></br></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;Signature=$( echo -ne \"${Timestamp}\\n${Nonce}\\n${Data}\\n\" | openssl sha256 -r -hmac \"$ClientSecret\" | cut -f1 -d' ' )</code></br></br> <code>&nbsp;&nbsp;&nbsp;&nbsp;echo $Signature</code></br></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;shell output> e20c9cd5639d41f8bbc88f4d699c4baf94a4f0ee320e9a116b72743c449eb994  (**WARNING**: Exact value depends on current timestamp and client credentials</code></br></br>   You can also check the signature value using some online tools like, e.g: [https://codebeautify.org/hmac-generator](https://codebeautify.org/hmac-generator) (but don't forget about adding *newline* after each part of the hashed text and remember that you **should use** it only with your **test credentials**).   Here's a sample JSON request created using the values from the example above:  <code> {                            </br> &nbsp;&nbsp;\"jsonrpc\" : \"2.0\",         </br> &nbsp;&nbsp;\"id\" : 9929,               </br> &nbsp;&nbsp;\"method\" : \"public/auth\",  </br> &nbsp;&nbsp;\"params\" :                 </br> &nbsp;&nbsp;{                        </br> &nbsp;&nbsp;&nbsp;&nbsp;\"grant_type\" : \"client_signature\",   </br> &nbsp;&nbsp;&nbsp;&nbsp;\"client_id\" : \"AAAAAAAAAAA\",         </br> &nbsp;&nbsp;&nbsp;&nbsp;\"timestamp\": \"1554883365000\",        </br> &nbsp;&nbsp;&nbsp;&nbsp;\"nonce\": \"fdbmmz79\",                 </br> &nbsp;&nbsp;&nbsp;&nbsp;\"data\": \"\",                          </br> &nbsp;&nbsp;&nbsp;&nbsp;\"signature\" : \"e20c9cd5639d41f8bbc88f4d699c4baf94a4f0ee320e9a116b72743c449eb994\"  </br> &nbsp;&nbsp;}                        </br> }                            </br> </code>   ### Access scope  When asking for `access token` user can provide the required access level (called `scope`) which defines what type of functionality he/she wants to use, and whether requests are only going to check for some data or also to update them.  Scopes are required and checked for `private` methods, so if you plan to use only `public` information you can stay with values assigned by default.  |Scope|Description |----|-----------| |*account:read*|Access to **account** methods - read only data| |*account:read_write*|Access to **account** methods - allows to manage account settings, add subaccounts, etc.| |*trade:read*|Access to **trade** methods - read only data| |*trade:read_write*|Access to **trade** methods - required to create and modify orders| |*wallet:read*|Access to **wallet** methods - read only data| |*wallet:read_write*|Access to **wallet** methods - allows to withdraw, generate new deposit address, etc.| |*wallet:none*, *account:none*, *trade:none*|Blocked access to specified functionality|    <span style=\"color:red\">**NOTICE:**</span> Depending on choosing an authentication method (```grant type```) some scopes could be narrowed by the server. e.g. when ```grant_type = client_credentials``` and ```scope = wallet:read_write``` it's modified by the server as ```scope = wallet:read```\"   ## JSON-RPC over websocket Websocket is the prefered transport mechanism for the JSON-RPC API, because it is faster and because it can support [subscriptions](#subscriptions) and [cancel on disconnect](#private-enable_cancel_on_disconnect). The code examples that can be found next to each of the methods show how websockets can be used from Python or Javascript/node.js.  ## JSON-RPC over HTTP Besides websockets it is also possible to use the API via HTTP. The code examples for 'shell' show how this can be done using curl. Note that subscriptions and cancel on disconnect are not supported via HTTP.  #Methods 
 *
 * The version of the OpenAPI document: 2.0.0
 *
 * NOTE: This class is auto generated by OpenAPI-Generator 4.0.2-SNAPSHOT.
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */



#include "Position.h"

namespace org {
namespace openapitools {
namespace client {
namespace model {




Position::Position()
{
    m_Direction = utility::conversions::to_string_t("");
    m_Average_price_usd = 0.0;
    m_Average_price_usdIsSet = false;
    m_Estimated_liquidation_price = 0.0;
    m_Estimated_liquidation_priceIsSet = false;
    m_Floating_profit_loss = 0.0;
    m_Floating_profit_loss_usd = 0.0;
    m_Floating_profit_loss_usdIsSet = false;
    m_Open_orders_margin = 0.0;
    m_Total_profit_loss = 0.0;
    m_Realized_profit_loss = 0.0;
    m_Realized_profit_lossIsSet = false;
    m_Delta = 0.0;
    m_Initial_margin = 0.0;
    m_Size = 0.0;
    m_Maintenance_margin = 0.0;
    m_Kind = utility::conversions::to_string_t("");
    m_Mark_price = 0.0;
    m_Average_price = 0.0;
    m_Settlement_price = 0.0;
    m_Index_price = 0.0;
    m_Instrument_name = utility::conversions::to_string_t("");
    m_Size_currency = 0.0;
    m_Size_currencyIsSet = false;
}

Position::~Position()
{
}

void Position::validate()
{
    // TODO: implement validation
}

web::json::value Position::toJson() const
{
    web::json::value val = web::json::value::object();

    val[utility::conversions::to_string_t("direction")] = ModelBase::toJson(m_Direction);
    if(m_Average_price_usdIsSet)
    {
        val[utility::conversions::to_string_t("average_price_usd")] = ModelBase::toJson(m_Average_price_usd);
    }
    if(m_Estimated_liquidation_priceIsSet)
    {
        val[utility::conversions::to_string_t("estimated_liquidation_price")] = ModelBase::toJson(m_Estimated_liquidation_price);
    }
    val[utility::conversions::to_string_t("floating_profit_loss")] = ModelBase::toJson(m_Floating_profit_loss);
    if(m_Floating_profit_loss_usdIsSet)
    {
        val[utility::conversions::to_string_t("floating_profit_loss_usd")] = ModelBase::toJson(m_Floating_profit_loss_usd);
    }
    val[utility::conversions::to_string_t("open_orders_margin")] = ModelBase::toJson(m_Open_orders_margin);
    val[utility::conversions::to_string_t("total_profit_loss")] = ModelBase::toJson(m_Total_profit_loss);
    if(m_Realized_profit_lossIsSet)
    {
        val[utility::conversions::to_string_t("realized_profit_loss")] = ModelBase::toJson(m_Realized_profit_loss);
    }
    val[utility::conversions::to_string_t("delta")] = ModelBase::toJson(m_Delta);
    val[utility::conversions::to_string_t("initial_margin")] = ModelBase::toJson(m_Initial_margin);
    val[utility::conversions::to_string_t("size")] = ModelBase::toJson(m_Size);
    val[utility::conversions::to_string_t("maintenance_margin")] = ModelBase::toJson(m_Maintenance_margin);
    val[utility::conversions::to_string_t("kind")] = ModelBase::toJson(m_Kind);
    val[utility::conversions::to_string_t("mark_price")] = ModelBase::toJson(m_Mark_price);
    val[utility::conversions::to_string_t("average_price")] = ModelBase::toJson(m_Average_price);
    val[utility::conversions::to_string_t("settlement_price")] = ModelBase::toJson(m_Settlement_price);
    val[utility::conversions::to_string_t("index_price")] = ModelBase::toJson(m_Index_price);
    val[utility::conversions::to_string_t("instrument_name")] = ModelBase::toJson(m_Instrument_name);
    if(m_Size_currencyIsSet)
    {
        val[utility::conversions::to_string_t("size_currency")] = ModelBase::toJson(m_Size_currency);
    }

    return val;
}

void Position::fromJson(const web::json::value& val)
{
    setDirection(ModelBase::stringFromJson(val.at(utility::conversions::to_string_t("direction"))));
    if(val.has_field(utility::conversions::to_string_t("average_price_usd")))
    {
        const web::json::value& fieldValue = val.at(utility::conversions::to_string_t("average_price_usd"));
        if(!fieldValue.is_null())
        {
            setAveragePriceUsd(ModelBase::doubleFromJson(fieldValue));
        }
    }
    if(val.has_field(utility::conversions::to_string_t("estimated_liquidation_price")))
    {
        const web::json::value& fieldValue = val.at(utility::conversions::to_string_t("estimated_liquidation_price"));
        if(!fieldValue.is_null())
        {
            setEstimatedLiquidationPrice(ModelBase::doubleFromJson(fieldValue));
        }
    }
    setFloatingProfitLoss(ModelBase::doubleFromJson(val.at(utility::conversions::to_string_t("floating_profit_loss"))));
    if(val.has_field(utility::conversions::to_string_t("floating_profit_loss_usd")))
    {
        const web::json::value& fieldValue = val.at(utility::conversions::to_string_t("floating_profit_loss_usd"));
        if(!fieldValue.is_null())
        {
            setFloatingProfitLossUsd(ModelBase::doubleFromJson(fieldValue));
        }
    }
    setOpenOrdersMargin(ModelBase::doubleFromJson(val.at(utility::conversions::to_string_t("open_orders_margin"))));
    setTotalProfitLoss(ModelBase::doubleFromJson(val.at(utility::conversions::to_string_t("total_profit_loss"))));
    if(val.has_field(utility::conversions::to_string_t("realized_profit_loss")))
    {
        const web::json::value& fieldValue = val.at(utility::conversions::to_string_t("realized_profit_loss"));
        if(!fieldValue.is_null())
        {
            setRealizedProfitLoss(ModelBase::doubleFromJson(fieldValue));
        }
    }
    setDelta(ModelBase::doubleFromJson(val.at(utility::conversions::to_string_t("delta"))));
    setInitialMargin(ModelBase::doubleFromJson(val.at(utility::conversions::to_string_t("initial_margin"))));
    setSize(ModelBase::doubleFromJson(val.at(utility::conversions::to_string_t("size"))));
    setMaintenanceMargin(ModelBase::doubleFromJson(val.at(utility::conversions::to_string_t("maintenance_margin"))));
    setKind(ModelBase::stringFromJson(val.at(utility::conversions::to_string_t("kind"))));
    setMarkPrice(ModelBase::doubleFromJson(val.at(utility::conversions::to_string_t("mark_price"))));
    setAveragePrice(ModelBase::doubleFromJson(val.at(utility::conversions::to_string_t("average_price"))));
    setSettlementPrice(ModelBase::doubleFromJson(val.at(utility::conversions::to_string_t("settlement_price"))));
    setIndexPrice(ModelBase::doubleFromJson(val.at(utility::conversions::to_string_t("index_price"))));
    setInstrumentName(ModelBase::stringFromJson(val.at(utility::conversions::to_string_t("instrument_name"))));
    if(val.has_field(utility::conversions::to_string_t("size_currency")))
    {
        const web::json::value& fieldValue = val.at(utility::conversions::to_string_t("size_currency"));
        if(!fieldValue.is_null())
        {
            setSizeCurrency(ModelBase::doubleFromJson(fieldValue));
        }
    }
}

void Position::toMultipart(std::shared_ptr<MultipartFormData> multipart, const utility::string_t& prefix) const
{
    utility::string_t namePrefix = prefix;
    if(namePrefix.size() > 0 && namePrefix.substr(namePrefix.size() - 1) != utility::conversions::to_string_t("."))
    {
        namePrefix += utility::conversions::to_string_t(".");
    }

    multipart->add(ModelBase::toHttpContent(namePrefix + utility::conversions::to_string_t("direction"), m_Direction));
    if(m_Average_price_usdIsSet)
    {
        multipart->add(ModelBase::toHttpContent(namePrefix + utility::conversions::to_string_t("average_price_usd"), m_Average_price_usd));
    }
    if(m_Estimated_liquidation_priceIsSet)
    {
        multipart->add(ModelBase::toHttpContent(namePrefix + utility::conversions::to_string_t("estimated_liquidation_price"), m_Estimated_liquidation_price));
    }
    multipart->add(ModelBase::toHttpContent(namePrefix + utility::conversions::to_string_t("floating_profit_loss"), m_Floating_profit_loss));
    if(m_Floating_profit_loss_usdIsSet)
    {
        multipart->add(ModelBase::toHttpContent(namePrefix + utility::conversions::to_string_t("floating_profit_loss_usd"), m_Floating_profit_loss_usd));
    }
    multipart->add(ModelBase::toHttpContent(namePrefix + utility::conversions::to_string_t("open_orders_margin"), m_Open_orders_margin));
    multipart->add(ModelBase::toHttpContent(namePrefix + utility::conversions::to_string_t("total_profit_loss"), m_Total_profit_loss));
    if(m_Realized_profit_lossIsSet)
    {
        multipart->add(ModelBase::toHttpContent(namePrefix + utility::conversions::to_string_t("realized_profit_loss"), m_Realized_profit_loss));
    }
    multipart->add(ModelBase::toHttpContent(namePrefix + utility::conversions::to_string_t("delta"), m_Delta));
    multipart->add(ModelBase::toHttpContent(namePrefix + utility::conversions::to_string_t("initial_margin"), m_Initial_margin));
    multipart->add(ModelBase::toHttpContent(namePrefix + utility::conversions::to_string_t("size"), m_Size));
    multipart->add(ModelBase::toHttpContent(namePrefix + utility::conversions::to_string_t("maintenance_margin"), m_Maintenance_margin));
    multipart->add(ModelBase::toHttpContent(namePrefix + utility::conversions::to_string_t("kind"), m_Kind));
    multipart->add(ModelBase::toHttpContent(namePrefix + utility::conversions::to_string_t("mark_price"), m_Mark_price));
    multipart->add(ModelBase::toHttpContent(namePrefix + utility::conversions::to_string_t("average_price"), m_Average_price));
    multipart->add(ModelBase::toHttpContent(namePrefix + utility::conversions::to_string_t("settlement_price"), m_Settlement_price));
    multipart->add(ModelBase::toHttpContent(namePrefix + utility::conversions::to_string_t("index_price"), m_Index_price));
    multipart->add(ModelBase::toHttpContent(namePrefix + utility::conversions::to_string_t("instrument_name"), m_Instrument_name));
    if(m_Size_currencyIsSet)
    {
        multipart->add(ModelBase::toHttpContent(namePrefix + utility::conversions::to_string_t("size_currency"), m_Size_currency));
    }
}

void Position::fromMultiPart(std::shared_ptr<MultipartFormData> multipart, const utility::string_t& prefix)
{
    utility::string_t namePrefix = prefix;
    if(namePrefix.size() > 0 && namePrefix.substr(namePrefix.size() - 1) != utility::conversions::to_string_t("."))
    {
        namePrefix += utility::conversions::to_string_t(".");
    }

    setDirection(ModelBase::stringFromHttpContent(multipart->getContent(utility::conversions::to_string_t("direction"))));
    if(multipart->hasContent(utility::conversions::to_string_t("average_price_usd")))
    {
        setAveragePriceUsd(ModelBase::doubleFromHttpContent(multipart->getContent(utility::conversions::to_string_t("average_price_usd"))));
    }
    if(multipart->hasContent(utility::conversions::to_string_t("estimated_liquidation_price")))
    {
        setEstimatedLiquidationPrice(ModelBase::doubleFromHttpContent(multipart->getContent(utility::conversions::to_string_t("estimated_liquidation_price"))));
    }
    setFloatingProfitLoss(ModelBase::doubleFromHttpContent(multipart->getContent(utility::conversions::to_string_t("floating_profit_loss"))));
    if(multipart->hasContent(utility::conversions::to_string_t("floating_profit_loss_usd")))
    {
        setFloatingProfitLossUsd(ModelBase::doubleFromHttpContent(multipart->getContent(utility::conversions::to_string_t("floating_profit_loss_usd"))));
    }
    setOpenOrdersMargin(ModelBase::doubleFromHttpContent(multipart->getContent(utility::conversions::to_string_t("open_orders_margin"))));
    setTotalProfitLoss(ModelBase::doubleFromHttpContent(multipart->getContent(utility::conversions::to_string_t("total_profit_loss"))));
    if(multipart->hasContent(utility::conversions::to_string_t("realized_profit_loss")))
    {
        setRealizedProfitLoss(ModelBase::doubleFromHttpContent(multipart->getContent(utility::conversions::to_string_t("realized_profit_loss"))));
    }
    setDelta(ModelBase::doubleFromHttpContent(multipart->getContent(utility::conversions::to_string_t("delta"))));
    setInitialMargin(ModelBase::doubleFromHttpContent(multipart->getContent(utility::conversions::to_string_t("initial_margin"))));
    setSize(ModelBase::doubleFromHttpContent(multipart->getContent(utility::conversions::to_string_t("size"))));
    setMaintenanceMargin(ModelBase::doubleFromHttpContent(multipart->getContent(utility::conversions::to_string_t("maintenance_margin"))));
    setKind(ModelBase::stringFromHttpContent(multipart->getContent(utility::conversions::to_string_t("kind"))));
    setMarkPrice(ModelBase::doubleFromHttpContent(multipart->getContent(utility::conversions::to_string_t("mark_price"))));
    setAveragePrice(ModelBase::doubleFromHttpContent(multipart->getContent(utility::conversions::to_string_t("average_price"))));
    setSettlementPrice(ModelBase::doubleFromHttpContent(multipart->getContent(utility::conversions::to_string_t("settlement_price"))));
    setIndexPrice(ModelBase::doubleFromHttpContent(multipart->getContent(utility::conversions::to_string_t("index_price"))));
    setInstrumentName(ModelBase::stringFromHttpContent(multipart->getContent(utility::conversions::to_string_t("instrument_name"))));
    if(multipart->hasContent(utility::conversions::to_string_t("size_currency")))
    {
        setSizeCurrency(ModelBase::doubleFromHttpContent(multipart->getContent(utility::conversions::to_string_t("size_currency"))));
    }
}

utility::string_t Position::getDirection() const
{
    return m_Direction;
}

void Position::setDirection(const utility::string_t& value)
{
    m_Direction = value;
    
}

double Position::getAveragePriceUsd() const
{
    return m_Average_price_usd;
}

void Position::setAveragePriceUsd(double value)
{
    m_Average_price_usd = value;
    m_Average_price_usdIsSet = true;
}

bool Position::averagePriceUsdIsSet() const
{
    return m_Average_price_usdIsSet;
}

void Position::unsetAverage_price_usd()
{
    m_Average_price_usdIsSet = false;
}

double Position::getEstimatedLiquidationPrice() const
{
    return m_Estimated_liquidation_price;
}

void Position::setEstimatedLiquidationPrice(double value)
{
    m_Estimated_liquidation_price = value;
    m_Estimated_liquidation_priceIsSet = true;
}

bool Position::estimatedLiquidationPriceIsSet() const
{
    return m_Estimated_liquidation_priceIsSet;
}

void Position::unsetEstimated_liquidation_price()
{
    m_Estimated_liquidation_priceIsSet = false;
}

double Position::getFloatingProfitLoss() const
{
    return m_Floating_profit_loss;
}

void Position::setFloatingProfitLoss(double value)
{
    m_Floating_profit_loss = value;
    
}

double Position::getFloatingProfitLossUsd() const
{
    return m_Floating_profit_loss_usd;
}

void Position::setFloatingProfitLossUsd(double value)
{
    m_Floating_profit_loss_usd = value;
    m_Floating_profit_loss_usdIsSet = true;
}

bool Position::floatingProfitLossUsdIsSet() const
{
    return m_Floating_profit_loss_usdIsSet;
}

void Position::unsetFloating_profit_loss_usd()
{
    m_Floating_profit_loss_usdIsSet = false;
}

double Position::getOpenOrdersMargin() const
{
    return m_Open_orders_margin;
}

void Position::setOpenOrdersMargin(double value)
{
    m_Open_orders_margin = value;
    
}

double Position::getTotalProfitLoss() const
{
    return m_Total_profit_loss;
}

void Position::setTotalProfitLoss(double value)
{
    m_Total_profit_loss = value;
    
}

double Position::getRealizedProfitLoss() const
{
    return m_Realized_profit_loss;
}

void Position::setRealizedProfitLoss(double value)
{
    m_Realized_profit_loss = value;
    m_Realized_profit_lossIsSet = true;
}

bool Position::realizedProfitLossIsSet() const
{
    return m_Realized_profit_lossIsSet;
}

void Position::unsetRealized_profit_loss()
{
    m_Realized_profit_lossIsSet = false;
}

double Position::getDelta() const
{
    return m_Delta;
}

void Position::setDelta(double value)
{
    m_Delta = value;
    
}

double Position::getInitialMargin() const
{
    return m_Initial_margin;
}

void Position::setInitialMargin(double value)
{
    m_Initial_margin = value;
    
}

double Position::getSize() const
{
    return m_Size;
}

void Position::setSize(double value)
{
    m_Size = value;
    
}

double Position::getMaintenanceMargin() const
{
    return m_Maintenance_margin;
}

void Position::setMaintenanceMargin(double value)
{
    m_Maintenance_margin = value;
    
}

utility::string_t Position::getKind() const
{
    return m_Kind;
}

void Position::setKind(const utility::string_t& value)
{
    m_Kind = value;
    
}

double Position::getMarkPrice() const
{
    return m_Mark_price;
}

void Position::setMarkPrice(double value)
{
    m_Mark_price = value;
    
}

double Position::getAveragePrice() const
{
    return m_Average_price;
}

void Position::setAveragePrice(double value)
{
    m_Average_price = value;
    
}

double Position::getSettlementPrice() const
{
    return m_Settlement_price;
}

void Position::setSettlementPrice(double value)
{
    m_Settlement_price = value;
    
}

double Position::getIndexPrice() const
{
    return m_Index_price;
}

void Position::setIndexPrice(double value)
{
    m_Index_price = value;
    
}

utility::string_t Position::getInstrumentName() const
{
    return m_Instrument_name;
}

void Position::setInstrumentName(const utility::string_t& value)
{
    m_Instrument_name = value;
    
}

double Position::getSizeCurrency() const
{
    return m_Size_currency;
}

void Position::setSizeCurrency(double value)
{
    m_Size_currency = value;
    m_Size_currencyIsSet = true;
}

bool Position::sizeCurrencyIsSet() const
{
    return m_Size_currencyIsSet;
}

void Position::unsetSize_currency()
{
    m_Size_currencyIsSet = false;
}

}
}
}
}


