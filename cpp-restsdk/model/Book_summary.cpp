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



#include "Book_summary.h"

namespace org {
namespace openapitools {
namespace client {
namespace model {




Book_summary::Book_summary()
{
    m_Underlying_index = utility::conversions::to_string_t("");
    m_Underlying_indexIsSet = false;
    m_Volume = 0.0;
    m_Volume_usd = 0.0;
    m_Volume_usdIsSet = false;
    m_Underlying_price = 0.0;
    m_Underlying_priceIsSet = false;
    m_Bid_price = 0.0;
    m_Open_interest = 0.0;
    m_Quote_currency = utility::conversions::to_string_t("");
    m_High = 0.0;
    m_Estimated_delivery_price = 0.0;
    m_Estimated_delivery_priceIsSet = false;
    m_Last = 0.0;
    m_Mid_price = 0.0;
    m_Interest_rate = 0.0;
    m_Interest_rateIsSet = false;
    m_Funding_8h = 0.0;
    m_Funding_8hIsSet = false;
    m_Mark_price = 0.0;
    m_Ask_price = 0.0;
    m_Instrument_name = utility::conversions::to_string_t("");
    m_Low = 0.0;
    m_Base_currency = utility::conversions::to_string_t("");
    m_Base_currencyIsSet = false;
    m_Creation_timestamp = 0;
    m_Current_funding = 0.0;
    m_Current_fundingIsSet = false;
}

Book_summary::~Book_summary()
{
}

void Book_summary::validate()
{
    // TODO: implement validation
}

web::json::value Book_summary::toJson() const
{
    web::json::value val = web::json::value::object();

    if(m_Underlying_indexIsSet)
    {
        val[utility::conversions::to_string_t("underlying_index")] = ModelBase::toJson(m_Underlying_index);
    }
    val[utility::conversions::to_string_t("volume")] = ModelBase::toJson(m_Volume);
    if(m_Volume_usdIsSet)
    {
        val[utility::conversions::to_string_t("volume_usd")] = ModelBase::toJson(m_Volume_usd);
    }
    if(m_Underlying_priceIsSet)
    {
        val[utility::conversions::to_string_t("underlying_price")] = ModelBase::toJson(m_Underlying_price);
    }
    val[utility::conversions::to_string_t("bid_price")] = ModelBase::toJson(m_Bid_price);
    val[utility::conversions::to_string_t("open_interest")] = ModelBase::toJson(m_Open_interest);
    val[utility::conversions::to_string_t("quote_currency")] = ModelBase::toJson(m_Quote_currency);
    val[utility::conversions::to_string_t("high")] = ModelBase::toJson(m_High);
    if(m_Estimated_delivery_priceIsSet)
    {
        val[utility::conversions::to_string_t("estimated_delivery_price")] = ModelBase::toJson(m_Estimated_delivery_price);
    }
    val[utility::conversions::to_string_t("last")] = ModelBase::toJson(m_Last);
    val[utility::conversions::to_string_t("mid_price")] = ModelBase::toJson(m_Mid_price);
    if(m_Interest_rateIsSet)
    {
        val[utility::conversions::to_string_t("interest_rate")] = ModelBase::toJson(m_Interest_rate);
    }
    if(m_Funding_8hIsSet)
    {
        val[utility::conversions::to_string_t("funding_8h")] = ModelBase::toJson(m_Funding_8h);
    }
    val[utility::conversions::to_string_t("mark_price")] = ModelBase::toJson(m_Mark_price);
    val[utility::conversions::to_string_t("ask_price")] = ModelBase::toJson(m_Ask_price);
    val[utility::conversions::to_string_t("instrument_name")] = ModelBase::toJson(m_Instrument_name);
    val[utility::conversions::to_string_t("low")] = ModelBase::toJson(m_Low);
    if(m_Base_currencyIsSet)
    {
        val[utility::conversions::to_string_t("base_currency")] = ModelBase::toJson(m_Base_currency);
    }
    val[utility::conversions::to_string_t("creation_timestamp")] = ModelBase::toJson(m_Creation_timestamp);
    if(m_Current_fundingIsSet)
    {
        val[utility::conversions::to_string_t("current_funding")] = ModelBase::toJson(m_Current_funding);
    }

    return val;
}

void Book_summary::fromJson(const web::json::value& val)
{
    if(val.has_field(utility::conversions::to_string_t("underlying_index")))
    {
        const web::json::value& fieldValue = val.at(utility::conversions::to_string_t("underlying_index"));
        if(!fieldValue.is_null())
        {
            setUnderlyingIndex(ModelBase::stringFromJson(fieldValue));
        }
    }
    setVolume(ModelBase::doubleFromJson(val.at(utility::conversions::to_string_t("volume"))));
    if(val.has_field(utility::conversions::to_string_t("volume_usd")))
    {
        const web::json::value& fieldValue = val.at(utility::conversions::to_string_t("volume_usd"));
        if(!fieldValue.is_null())
        {
            setVolumeUsd(ModelBase::doubleFromJson(fieldValue));
        }
    }
    if(val.has_field(utility::conversions::to_string_t("underlying_price")))
    {
        const web::json::value& fieldValue = val.at(utility::conversions::to_string_t("underlying_price"));
        if(!fieldValue.is_null())
        {
            setUnderlyingPrice(ModelBase::doubleFromJson(fieldValue));
        }
    }
    setBidPrice(ModelBase::doubleFromJson(val.at(utility::conversions::to_string_t("bid_price"))));
    setOpenInterest(ModelBase::doubleFromJson(val.at(utility::conversions::to_string_t("open_interest"))));
    setQuoteCurrency(ModelBase::stringFromJson(val.at(utility::conversions::to_string_t("quote_currency"))));
    setHigh(ModelBase::doubleFromJson(val.at(utility::conversions::to_string_t("high"))));
    if(val.has_field(utility::conversions::to_string_t("estimated_delivery_price")))
    {
        const web::json::value& fieldValue = val.at(utility::conversions::to_string_t("estimated_delivery_price"));
        if(!fieldValue.is_null())
        {
            setEstimatedDeliveryPrice(ModelBase::doubleFromJson(fieldValue));
        }
    }
    setLast(ModelBase::doubleFromJson(val.at(utility::conversions::to_string_t("last"))));
    setMidPrice(ModelBase::doubleFromJson(val.at(utility::conversions::to_string_t("mid_price"))));
    if(val.has_field(utility::conversions::to_string_t("interest_rate")))
    {
        const web::json::value& fieldValue = val.at(utility::conversions::to_string_t("interest_rate"));
        if(!fieldValue.is_null())
        {
            setInterestRate(ModelBase::doubleFromJson(fieldValue));
        }
    }
    if(val.has_field(utility::conversions::to_string_t("funding_8h")))
    {
        const web::json::value& fieldValue = val.at(utility::conversions::to_string_t("funding_8h"));
        if(!fieldValue.is_null())
        {
            setFunding8h(ModelBase::doubleFromJson(fieldValue));
        }
    }
    setMarkPrice(ModelBase::doubleFromJson(val.at(utility::conversions::to_string_t("mark_price"))));
    setAskPrice(ModelBase::doubleFromJson(val.at(utility::conversions::to_string_t("ask_price"))));
    setInstrumentName(ModelBase::stringFromJson(val.at(utility::conversions::to_string_t("instrument_name"))));
    setLow(ModelBase::doubleFromJson(val.at(utility::conversions::to_string_t("low"))));
    if(val.has_field(utility::conversions::to_string_t("base_currency")))
    {
        const web::json::value& fieldValue = val.at(utility::conversions::to_string_t("base_currency"));
        if(!fieldValue.is_null())
        {
            setBaseCurrency(ModelBase::stringFromJson(fieldValue));
        }
    }
    setCreationTimestamp(ModelBase::int32_tFromJson(val.at(utility::conversions::to_string_t("creation_timestamp"))));
    if(val.has_field(utility::conversions::to_string_t("current_funding")))
    {
        const web::json::value& fieldValue = val.at(utility::conversions::to_string_t("current_funding"));
        if(!fieldValue.is_null())
        {
            setCurrentFunding(ModelBase::doubleFromJson(fieldValue));
        }
    }
}

void Book_summary::toMultipart(std::shared_ptr<MultipartFormData> multipart, const utility::string_t& prefix) const
{
    utility::string_t namePrefix = prefix;
    if(namePrefix.size() > 0 && namePrefix.substr(namePrefix.size() - 1) != utility::conversions::to_string_t("."))
    {
        namePrefix += utility::conversions::to_string_t(".");
    }

    if(m_Underlying_indexIsSet)
    {
        multipart->add(ModelBase::toHttpContent(namePrefix + utility::conversions::to_string_t("underlying_index"), m_Underlying_index));
    }
    multipart->add(ModelBase::toHttpContent(namePrefix + utility::conversions::to_string_t("volume"), m_Volume));
    if(m_Volume_usdIsSet)
    {
        multipart->add(ModelBase::toHttpContent(namePrefix + utility::conversions::to_string_t("volume_usd"), m_Volume_usd));
    }
    if(m_Underlying_priceIsSet)
    {
        multipart->add(ModelBase::toHttpContent(namePrefix + utility::conversions::to_string_t("underlying_price"), m_Underlying_price));
    }
    multipart->add(ModelBase::toHttpContent(namePrefix + utility::conversions::to_string_t("bid_price"), m_Bid_price));
    multipart->add(ModelBase::toHttpContent(namePrefix + utility::conversions::to_string_t("open_interest"), m_Open_interest));
    multipart->add(ModelBase::toHttpContent(namePrefix + utility::conversions::to_string_t("quote_currency"), m_Quote_currency));
    multipart->add(ModelBase::toHttpContent(namePrefix + utility::conversions::to_string_t("high"), m_High));
    if(m_Estimated_delivery_priceIsSet)
    {
        multipart->add(ModelBase::toHttpContent(namePrefix + utility::conversions::to_string_t("estimated_delivery_price"), m_Estimated_delivery_price));
    }
    multipart->add(ModelBase::toHttpContent(namePrefix + utility::conversions::to_string_t("last"), m_Last));
    multipart->add(ModelBase::toHttpContent(namePrefix + utility::conversions::to_string_t("mid_price"), m_Mid_price));
    if(m_Interest_rateIsSet)
    {
        multipart->add(ModelBase::toHttpContent(namePrefix + utility::conversions::to_string_t("interest_rate"), m_Interest_rate));
    }
    if(m_Funding_8hIsSet)
    {
        multipart->add(ModelBase::toHttpContent(namePrefix + utility::conversions::to_string_t("funding_8h"), m_Funding_8h));
    }
    multipart->add(ModelBase::toHttpContent(namePrefix + utility::conversions::to_string_t("mark_price"), m_Mark_price));
    multipart->add(ModelBase::toHttpContent(namePrefix + utility::conversions::to_string_t("ask_price"), m_Ask_price));
    multipart->add(ModelBase::toHttpContent(namePrefix + utility::conversions::to_string_t("instrument_name"), m_Instrument_name));
    multipart->add(ModelBase::toHttpContent(namePrefix + utility::conversions::to_string_t("low"), m_Low));
    if(m_Base_currencyIsSet)
    {
        multipart->add(ModelBase::toHttpContent(namePrefix + utility::conversions::to_string_t("base_currency"), m_Base_currency));
    }
    multipart->add(ModelBase::toHttpContent(namePrefix + utility::conversions::to_string_t("creation_timestamp"), m_Creation_timestamp));
    if(m_Current_fundingIsSet)
    {
        multipart->add(ModelBase::toHttpContent(namePrefix + utility::conversions::to_string_t("current_funding"), m_Current_funding));
    }
}

void Book_summary::fromMultiPart(std::shared_ptr<MultipartFormData> multipart, const utility::string_t& prefix)
{
    utility::string_t namePrefix = prefix;
    if(namePrefix.size() > 0 && namePrefix.substr(namePrefix.size() - 1) != utility::conversions::to_string_t("."))
    {
        namePrefix += utility::conversions::to_string_t(".");
    }

    if(multipart->hasContent(utility::conversions::to_string_t("underlying_index")))
    {
        setUnderlyingIndex(ModelBase::stringFromHttpContent(multipart->getContent(utility::conversions::to_string_t("underlying_index"))));
    }
    setVolume(ModelBase::doubleFromHttpContent(multipart->getContent(utility::conversions::to_string_t("volume"))));
    if(multipart->hasContent(utility::conversions::to_string_t("volume_usd")))
    {
        setVolumeUsd(ModelBase::doubleFromHttpContent(multipart->getContent(utility::conversions::to_string_t("volume_usd"))));
    }
    if(multipart->hasContent(utility::conversions::to_string_t("underlying_price")))
    {
        setUnderlyingPrice(ModelBase::doubleFromHttpContent(multipart->getContent(utility::conversions::to_string_t("underlying_price"))));
    }
    setBidPrice(ModelBase::doubleFromHttpContent(multipart->getContent(utility::conversions::to_string_t("bid_price"))));
    setOpenInterest(ModelBase::doubleFromHttpContent(multipart->getContent(utility::conversions::to_string_t("open_interest"))));
    setQuoteCurrency(ModelBase::stringFromHttpContent(multipart->getContent(utility::conversions::to_string_t("quote_currency"))));
    setHigh(ModelBase::doubleFromHttpContent(multipart->getContent(utility::conversions::to_string_t("high"))));
    if(multipart->hasContent(utility::conversions::to_string_t("estimated_delivery_price")))
    {
        setEstimatedDeliveryPrice(ModelBase::doubleFromHttpContent(multipart->getContent(utility::conversions::to_string_t("estimated_delivery_price"))));
    }
    setLast(ModelBase::doubleFromHttpContent(multipart->getContent(utility::conversions::to_string_t("last"))));
    setMidPrice(ModelBase::doubleFromHttpContent(multipart->getContent(utility::conversions::to_string_t("mid_price"))));
    if(multipart->hasContent(utility::conversions::to_string_t("interest_rate")))
    {
        setInterestRate(ModelBase::doubleFromHttpContent(multipart->getContent(utility::conversions::to_string_t("interest_rate"))));
    }
    if(multipart->hasContent(utility::conversions::to_string_t("funding_8h")))
    {
        setFunding8h(ModelBase::doubleFromHttpContent(multipart->getContent(utility::conversions::to_string_t("funding_8h"))));
    }
    setMarkPrice(ModelBase::doubleFromHttpContent(multipart->getContent(utility::conversions::to_string_t("mark_price"))));
    setAskPrice(ModelBase::doubleFromHttpContent(multipart->getContent(utility::conversions::to_string_t("ask_price"))));
    setInstrumentName(ModelBase::stringFromHttpContent(multipart->getContent(utility::conversions::to_string_t("instrument_name"))));
    setLow(ModelBase::doubleFromHttpContent(multipart->getContent(utility::conversions::to_string_t("low"))));
    if(multipart->hasContent(utility::conversions::to_string_t("base_currency")))
    {
        setBaseCurrency(ModelBase::stringFromHttpContent(multipart->getContent(utility::conversions::to_string_t("base_currency"))));
    }
    setCreationTimestamp(ModelBase::int32_tFromHttpContent(multipart->getContent(utility::conversions::to_string_t("creation_timestamp"))));
    if(multipart->hasContent(utility::conversions::to_string_t("current_funding")))
    {
        setCurrentFunding(ModelBase::doubleFromHttpContent(multipart->getContent(utility::conversions::to_string_t("current_funding"))));
    }
}

utility::string_t Book_summary::getUnderlyingIndex() const
{
    return m_Underlying_index;
}

void Book_summary::setUnderlyingIndex(const utility::string_t& value)
{
    m_Underlying_index = value;
    m_Underlying_indexIsSet = true;
}

bool Book_summary::underlyingIndexIsSet() const
{
    return m_Underlying_indexIsSet;
}

void Book_summary::unsetUnderlying_index()
{
    m_Underlying_indexIsSet = false;
}

double Book_summary::getVolume() const
{
    return m_Volume;
}

void Book_summary::setVolume(double value)
{
    m_Volume = value;
    
}

double Book_summary::getVolumeUsd() const
{
    return m_Volume_usd;
}

void Book_summary::setVolumeUsd(double value)
{
    m_Volume_usd = value;
    m_Volume_usdIsSet = true;
}

bool Book_summary::volumeUsdIsSet() const
{
    return m_Volume_usdIsSet;
}

void Book_summary::unsetVolume_usd()
{
    m_Volume_usdIsSet = false;
}

double Book_summary::getUnderlyingPrice() const
{
    return m_Underlying_price;
}

void Book_summary::setUnderlyingPrice(double value)
{
    m_Underlying_price = value;
    m_Underlying_priceIsSet = true;
}

bool Book_summary::underlyingPriceIsSet() const
{
    return m_Underlying_priceIsSet;
}

void Book_summary::unsetUnderlying_price()
{
    m_Underlying_priceIsSet = false;
}

double Book_summary::getBidPrice() const
{
    return m_Bid_price;
}

void Book_summary::setBidPrice(double value)
{
    m_Bid_price = value;
    
}

double Book_summary::getOpenInterest() const
{
    return m_Open_interest;
}

void Book_summary::setOpenInterest(double value)
{
    m_Open_interest = value;
    
}

utility::string_t Book_summary::getQuoteCurrency() const
{
    return m_Quote_currency;
}

void Book_summary::setQuoteCurrency(const utility::string_t& value)
{
    m_Quote_currency = value;
    
}

double Book_summary::getHigh() const
{
    return m_High;
}

void Book_summary::setHigh(double value)
{
    m_High = value;
    
}

double Book_summary::getEstimatedDeliveryPrice() const
{
    return m_Estimated_delivery_price;
}

void Book_summary::setEstimatedDeliveryPrice(double value)
{
    m_Estimated_delivery_price = value;
    m_Estimated_delivery_priceIsSet = true;
}

bool Book_summary::estimatedDeliveryPriceIsSet() const
{
    return m_Estimated_delivery_priceIsSet;
}

void Book_summary::unsetEstimated_delivery_price()
{
    m_Estimated_delivery_priceIsSet = false;
}

double Book_summary::getLast() const
{
    return m_Last;
}

void Book_summary::setLast(double value)
{
    m_Last = value;
    
}

double Book_summary::getMidPrice() const
{
    return m_Mid_price;
}

void Book_summary::setMidPrice(double value)
{
    m_Mid_price = value;
    
}

double Book_summary::getInterestRate() const
{
    return m_Interest_rate;
}

void Book_summary::setInterestRate(double value)
{
    m_Interest_rate = value;
    m_Interest_rateIsSet = true;
}

bool Book_summary::interestRateIsSet() const
{
    return m_Interest_rateIsSet;
}

void Book_summary::unsetInterest_rate()
{
    m_Interest_rateIsSet = false;
}

double Book_summary::getFunding8h() const
{
    return m_Funding_8h;
}

void Book_summary::setFunding8h(double value)
{
    m_Funding_8h = value;
    m_Funding_8hIsSet = true;
}

bool Book_summary::funding8hIsSet() const
{
    return m_Funding_8hIsSet;
}

void Book_summary::unsetFunding_8h()
{
    m_Funding_8hIsSet = false;
}

double Book_summary::getMarkPrice() const
{
    return m_Mark_price;
}

void Book_summary::setMarkPrice(double value)
{
    m_Mark_price = value;
    
}

double Book_summary::getAskPrice() const
{
    return m_Ask_price;
}

void Book_summary::setAskPrice(double value)
{
    m_Ask_price = value;
    
}

utility::string_t Book_summary::getInstrumentName() const
{
    return m_Instrument_name;
}

void Book_summary::setInstrumentName(const utility::string_t& value)
{
    m_Instrument_name = value;
    
}

double Book_summary::getLow() const
{
    return m_Low;
}

void Book_summary::setLow(double value)
{
    m_Low = value;
    
}

utility::string_t Book_summary::getBaseCurrency() const
{
    return m_Base_currency;
}

void Book_summary::setBaseCurrency(const utility::string_t& value)
{
    m_Base_currency = value;
    m_Base_currencyIsSet = true;
}

bool Book_summary::baseCurrencyIsSet() const
{
    return m_Base_currencyIsSet;
}

void Book_summary::unsetBase_currency()
{
    m_Base_currencyIsSet = false;
}

int32_t Book_summary::getCreationTimestamp() const
{
    return m_Creation_timestamp;
}

void Book_summary::setCreationTimestamp(int32_t value)
{
    m_Creation_timestamp = value;
    
}

double Book_summary::getCurrentFunding() const
{
    return m_Current_funding;
}

void Book_summary::setCurrentFunding(double value)
{
    m_Current_funding = value;
    m_Current_fundingIsSet = true;
}

bool Book_summary::currentFundingIsSet() const
{
    return m_Current_fundingIsSet;
}

void Book_summary::unsetCurrent_funding()
{
    m_Current_fundingIsSet = false;
}

}
}
}
}


