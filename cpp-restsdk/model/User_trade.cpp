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



#include "User_trade.h"

namespace org {
namespace openapitools {
namespace client {
namespace model {




User_trade::User_trade()
{
    m_Direction = utility::conversions::to_string_t("");
    m_Fee_currency = utility::conversions::to_string_t("");
    m_Order_id = utility::conversions::to_string_t("");
    m_Timestamp = 0;
    m_Price = 0.0;
    m_Iv = 0.0;
    m_IvIsSet = false;
    m_Trade_id = utility::conversions::to_string_t("");
    m_Fee = 0.0;
    m_Order_type = utility::conversions::to_string_t("");
    m_Order_typeIsSet = false;
    m_Trade_seq = 0;
    m_Self_trade = false;
    m_State = utility::conversions::to_string_t("");
    m_Label = utility::conversions::to_string_t("");
    m_LabelIsSet = false;
    m_Index_price = 0.0;
    m_Amount = 0.0;
    m_Instrument_name = utility::conversions::to_string_t("");
    m_Tick_direction = 0;
    m_Matching_id = utility::conversions::to_string_t("");
    m_Liquidity = utility::conversions::to_string_t("");
    m_LiquidityIsSet = false;
}

User_trade::~User_trade()
{
}

void User_trade::validate()
{
    // TODO: implement validation
}

web::json::value User_trade::toJson() const
{
    web::json::value val = web::json::value::object();

    val[utility::conversions::to_string_t("direction")] = ModelBase::toJson(m_Direction);
    val[utility::conversions::to_string_t("fee_currency")] = ModelBase::toJson(m_Fee_currency);
    val[utility::conversions::to_string_t("order_id")] = ModelBase::toJson(m_Order_id);
    val[utility::conversions::to_string_t("timestamp")] = ModelBase::toJson(m_Timestamp);
    val[utility::conversions::to_string_t("price")] = ModelBase::toJson(m_Price);
    if(m_IvIsSet)
    {
        val[utility::conversions::to_string_t("iv")] = ModelBase::toJson(m_Iv);
    }
    val[utility::conversions::to_string_t("trade_id")] = ModelBase::toJson(m_Trade_id);
    val[utility::conversions::to_string_t("fee")] = ModelBase::toJson(m_Fee);
    if(m_Order_typeIsSet)
    {
        val[utility::conversions::to_string_t("order_type")] = ModelBase::toJson(m_Order_type);
    }
    val[utility::conversions::to_string_t("trade_seq")] = ModelBase::toJson(m_Trade_seq);
    val[utility::conversions::to_string_t("self_trade")] = ModelBase::toJson(m_Self_trade);
    val[utility::conversions::to_string_t("state")] = ModelBase::toJson(m_State);
    if(m_LabelIsSet)
    {
        val[utility::conversions::to_string_t("label")] = ModelBase::toJson(m_Label);
    }
    val[utility::conversions::to_string_t("index_price")] = ModelBase::toJson(m_Index_price);
    val[utility::conversions::to_string_t("amount")] = ModelBase::toJson(m_Amount);
    val[utility::conversions::to_string_t("instrument_name")] = ModelBase::toJson(m_Instrument_name);
    val[utility::conversions::to_string_t("tick_direction")] = ModelBase::toJson(m_Tick_direction);
    val[utility::conversions::to_string_t("matching_id")] = ModelBase::toJson(m_Matching_id);
    if(m_LiquidityIsSet)
    {
        val[utility::conversions::to_string_t("liquidity")] = ModelBase::toJson(m_Liquidity);
    }

    return val;
}

void User_trade::fromJson(const web::json::value& val)
{
    setDirection(ModelBase::stringFromJson(val.at(utility::conversions::to_string_t("direction"))));
    setFeeCurrency(ModelBase::stringFromJson(val.at(utility::conversions::to_string_t("fee_currency"))));
    setOrderId(ModelBase::stringFromJson(val.at(utility::conversions::to_string_t("order_id"))));
    setTimestamp(ModelBase::int32_tFromJson(val.at(utility::conversions::to_string_t("timestamp"))));
    setPrice(ModelBase::doubleFromJson(val.at(utility::conversions::to_string_t("price"))));
    if(val.has_field(utility::conversions::to_string_t("iv")))
    {
        const web::json::value& fieldValue = val.at(utility::conversions::to_string_t("iv"));
        if(!fieldValue.is_null())
        {
            setIv(ModelBase::doubleFromJson(fieldValue));
        }
    }
    setTradeId(ModelBase::stringFromJson(val.at(utility::conversions::to_string_t("trade_id"))));
    setFee(ModelBase::doubleFromJson(val.at(utility::conversions::to_string_t("fee"))));
    if(val.has_field(utility::conversions::to_string_t("order_type")))
    {
        const web::json::value& fieldValue = val.at(utility::conversions::to_string_t("order_type"));
        if(!fieldValue.is_null())
        {
            setOrderType(ModelBase::stringFromJson(fieldValue));
        }
    }
    setTradeSeq(ModelBase::int32_tFromJson(val.at(utility::conversions::to_string_t("trade_seq"))));
    setSelfTrade(ModelBase::boolFromJson(val.at(utility::conversions::to_string_t("self_trade"))));
    setState(ModelBase::stringFromJson(val.at(utility::conversions::to_string_t("state"))));
    if(val.has_field(utility::conversions::to_string_t("label")))
    {
        const web::json::value& fieldValue = val.at(utility::conversions::to_string_t("label"));
        if(!fieldValue.is_null())
        {
            setLabel(ModelBase::stringFromJson(fieldValue));
        }
    }
    setIndexPrice(ModelBase::doubleFromJson(val.at(utility::conversions::to_string_t("index_price"))));
    setAmount(ModelBase::doubleFromJson(val.at(utility::conversions::to_string_t("amount"))));
    setInstrumentName(ModelBase::stringFromJson(val.at(utility::conversions::to_string_t("instrument_name"))));
    setTickDirection(ModelBase::int32_tFromJson(val.at(utility::conversions::to_string_t("tick_direction"))));
    setMatchingId(ModelBase::stringFromJson(val.at(utility::conversions::to_string_t("matching_id"))));
    if(val.has_field(utility::conversions::to_string_t("liquidity")))
    {
        const web::json::value& fieldValue = val.at(utility::conversions::to_string_t("liquidity"));
        if(!fieldValue.is_null())
        {
            setLiquidity(ModelBase::stringFromJson(fieldValue));
        }
    }
}

void User_trade::toMultipart(std::shared_ptr<MultipartFormData> multipart, const utility::string_t& prefix) const
{
    utility::string_t namePrefix = prefix;
    if(namePrefix.size() > 0 && namePrefix.substr(namePrefix.size() - 1) != utility::conversions::to_string_t("."))
    {
        namePrefix += utility::conversions::to_string_t(".");
    }

    multipart->add(ModelBase::toHttpContent(namePrefix + utility::conversions::to_string_t("direction"), m_Direction));
    multipart->add(ModelBase::toHttpContent(namePrefix + utility::conversions::to_string_t("fee_currency"), m_Fee_currency));
    multipart->add(ModelBase::toHttpContent(namePrefix + utility::conversions::to_string_t("order_id"), m_Order_id));
    multipart->add(ModelBase::toHttpContent(namePrefix + utility::conversions::to_string_t("timestamp"), m_Timestamp));
    multipart->add(ModelBase::toHttpContent(namePrefix + utility::conversions::to_string_t("price"), m_Price));
    if(m_IvIsSet)
    {
        multipart->add(ModelBase::toHttpContent(namePrefix + utility::conversions::to_string_t("iv"), m_Iv));
    }
    multipart->add(ModelBase::toHttpContent(namePrefix + utility::conversions::to_string_t("trade_id"), m_Trade_id));
    multipart->add(ModelBase::toHttpContent(namePrefix + utility::conversions::to_string_t("fee"), m_Fee));
    if(m_Order_typeIsSet)
    {
        multipart->add(ModelBase::toHttpContent(namePrefix + utility::conversions::to_string_t("order_type"), m_Order_type));
    }
    multipart->add(ModelBase::toHttpContent(namePrefix + utility::conversions::to_string_t("trade_seq"), m_Trade_seq));
    multipart->add(ModelBase::toHttpContent(namePrefix + utility::conversions::to_string_t("self_trade"), m_Self_trade));
    multipart->add(ModelBase::toHttpContent(namePrefix + utility::conversions::to_string_t("state"), m_State));
    if(m_LabelIsSet)
    {
        multipart->add(ModelBase::toHttpContent(namePrefix + utility::conversions::to_string_t("label"), m_Label));
    }
    multipart->add(ModelBase::toHttpContent(namePrefix + utility::conversions::to_string_t("index_price"), m_Index_price));
    multipart->add(ModelBase::toHttpContent(namePrefix + utility::conversions::to_string_t("amount"), m_Amount));
    multipart->add(ModelBase::toHttpContent(namePrefix + utility::conversions::to_string_t("instrument_name"), m_Instrument_name));
    multipart->add(ModelBase::toHttpContent(namePrefix + utility::conversions::to_string_t("tick_direction"), m_Tick_direction));
    multipart->add(ModelBase::toHttpContent(namePrefix + utility::conversions::to_string_t("matching_id"), m_Matching_id));
    if(m_LiquidityIsSet)
    {
        multipart->add(ModelBase::toHttpContent(namePrefix + utility::conversions::to_string_t("liquidity"), m_Liquidity));
    }
}

void User_trade::fromMultiPart(std::shared_ptr<MultipartFormData> multipart, const utility::string_t& prefix)
{
    utility::string_t namePrefix = prefix;
    if(namePrefix.size() > 0 && namePrefix.substr(namePrefix.size() - 1) != utility::conversions::to_string_t("."))
    {
        namePrefix += utility::conversions::to_string_t(".");
    }

    setDirection(ModelBase::stringFromHttpContent(multipart->getContent(utility::conversions::to_string_t("direction"))));
    setFeeCurrency(ModelBase::stringFromHttpContent(multipart->getContent(utility::conversions::to_string_t("fee_currency"))));
    setOrderId(ModelBase::stringFromHttpContent(multipart->getContent(utility::conversions::to_string_t("order_id"))));
    setTimestamp(ModelBase::int32_tFromHttpContent(multipart->getContent(utility::conversions::to_string_t("timestamp"))));
    setPrice(ModelBase::doubleFromHttpContent(multipart->getContent(utility::conversions::to_string_t("price"))));
    if(multipart->hasContent(utility::conversions::to_string_t("iv")))
    {
        setIv(ModelBase::doubleFromHttpContent(multipart->getContent(utility::conversions::to_string_t("iv"))));
    }
    setTradeId(ModelBase::stringFromHttpContent(multipart->getContent(utility::conversions::to_string_t("trade_id"))));
    setFee(ModelBase::doubleFromHttpContent(multipart->getContent(utility::conversions::to_string_t("fee"))));
    if(multipart->hasContent(utility::conversions::to_string_t("order_type")))
    {
        setOrderType(ModelBase::stringFromHttpContent(multipart->getContent(utility::conversions::to_string_t("order_type"))));
    }
    setTradeSeq(ModelBase::int32_tFromHttpContent(multipart->getContent(utility::conversions::to_string_t("trade_seq"))));
    setSelfTrade(ModelBase::boolFromHttpContent(multipart->getContent(utility::conversions::to_string_t("self_trade"))));
    setState(ModelBase::stringFromHttpContent(multipart->getContent(utility::conversions::to_string_t("state"))));
    if(multipart->hasContent(utility::conversions::to_string_t("label")))
    {
        setLabel(ModelBase::stringFromHttpContent(multipart->getContent(utility::conversions::to_string_t("label"))));
    }
    setIndexPrice(ModelBase::doubleFromHttpContent(multipart->getContent(utility::conversions::to_string_t("index_price"))));
    setAmount(ModelBase::doubleFromHttpContent(multipart->getContent(utility::conversions::to_string_t("amount"))));
    setInstrumentName(ModelBase::stringFromHttpContent(multipart->getContent(utility::conversions::to_string_t("instrument_name"))));
    setTickDirection(ModelBase::int32_tFromHttpContent(multipart->getContent(utility::conversions::to_string_t("tick_direction"))));
    setMatchingId(ModelBase::stringFromHttpContent(multipart->getContent(utility::conversions::to_string_t("matching_id"))));
    if(multipart->hasContent(utility::conversions::to_string_t("liquidity")))
    {
        setLiquidity(ModelBase::stringFromHttpContent(multipart->getContent(utility::conversions::to_string_t("liquidity"))));
    }
}

utility::string_t User_trade::getDirection() const
{
    return m_Direction;
}

void User_trade::setDirection(const utility::string_t& value)
{
    m_Direction = value;
    
}

utility::string_t User_trade::getFeeCurrency() const
{
    return m_Fee_currency;
}

void User_trade::setFeeCurrency(const utility::string_t& value)
{
    m_Fee_currency = value;
    
}

utility::string_t User_trade::getOrderId() const
{
    return m_Order_id;
}

void User_trade::setOrderId(const utility::string_t& value)
{
    m_Order_id = value;
    
}

int32_t User_trade::getTimestamp() const
{
    return m_Timestamp;
}

void User_trade::setTimestamp(int32_t value)
{
    m_Timestamp = value;
    
}

double User_trade::getPrice() const
{
    return m_Price;
}

void User_trade::setPrice(double value)
{
    m_Price = value;
    
}

double User_trade::getIv() const
{
    return m_Iv;
}

void User_trade::setIv(double value)
{
    m_Iv = value;
    m_IvIsSet = true;
}

bool User_trade::ivIsSet() const
{
    return m_IvIsSet;
}

void User_trade::unsetIv()
{
    m_IvIsSet = false;
}

utility::string_t User_trade::getTradeId() const
{
    return m_Trade_id;
}

void User_trade::setTradeId(const utility::string_t& value)
{
    m_Trade_id = value;
    
}

double User_trade::getFee() const
{
    return m_Fee;
}

void User_trade::setFee(double value)
{
    m_Fee = value;
    
}

utility::string_t User_trade::getOrderType() const
{
    return m_Order_type;
}

void User_trade::setOrderType(const utility::string_t& value)
{
    m_Order_type = value;
    m_Order_typeIsSet = true;
}

bool User_trade::orderTypeIsSet() const
{
    return m_Order_typeIsSet;
}

void User_trade::unsetOrder_type()
{
    m_Order_typeIsSet = false;
}

int32_t User_trade::getTradeSeq() const
{
    return m_Trade_seq;
}

void User_trade::setTradeSeq(int32_t value)
{
    m_Trade_seq = value;
    
}

bool User_trade::isSelfTrade() const
{
    return m_Self_trade;
}

void User_trade::setSelfTrade(bool value)
{
    m_Self_trade = value;
    
}

utility::string_t User_trade::getState() const
{
    return m_State;
}

void User_trade::setState(const utility::string_t& value)
{
    m_State = value;
    
}

utility::string_t User_trade::getLabel() const
{
    return m_Label;
}

void User_trade::setLabel(const utility::string_t& value)
{
    m_Label = value;
    m_LabelIsSet = true;
}

bool User_trade::labelIsSet() const
{
    return m_LabelIsSet;
}

void User_trade::unsetLabel()
{
    m_LabelIsSet = false;
}

double User_trade::getIndexPrice() const
{
    return m_Index_price;
}

void User_trade::setIndexPrice(double value)
{
    m_Index_price = value;
    
}

double User_trade::getAmount() const
{
    return m_Amount;
}

void User_trade::setAmount(double value)
{
    m_Amount = value;
    
}

utility::string_t User_trade::getInstrumentName() const
{
    return m_Instrument_name;
}

void User_trade::setInstrumentName(const utility::string_t& value)
{
    m_Instrument_name = value;
    
}

int32_t User_trade::getTickDirection() const
{
    return m_Tick_direction;
}

void User_trade::setTickDirection(int32_t value)
{
    m_Tick_direction = value;
    
}

utility::string_t User_trade::getMatchingId() const
{
    return m_Matching_id;
}

void User_trade::setMatchingId(const utility::string_t& value)
{
    m_Matching_id = value;
    
}

utility::string_t User_trade::getLiquidity() const
{
    return m_Liquidity;
}

void User_trade::setLiquidity(const utility::string_t& value)
{
    m_Liquidity = value;
    m_LiquidityIsSet = true;
}

bool User_trade::liquidityIsSet() const
{
    return m_LiquidityIsSet;
}

void User_trade::unsetLiquidity()
{
    m_LiquidityIsSet = false;
}

}
}
}
}


