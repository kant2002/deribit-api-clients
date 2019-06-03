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



#include "Order.h"

namespace org {
namespace openapitools {
namespace client {
namespace model {




Order::Order()
{
    m_Direction = utility::conversions::to_string_t("");
    m_Reduce_only = false;
    m_Reduce_onlyIsSet = false;
    m_Triggered = false;
    m_TriggeredIsSet = false;
    m_Order_id = utility::conversions::to_string_t("");
    m_Price = 0.0;
    m_Time_in_force = utility::conversions::to_string_t("");
    m_Api = false;
    m_Order_state = utility::conversions::to_string_t("");
    m_Implv = 0.0;
    m_ImplvIsSet = false;
    m_Advanced = utility::conversions::to_string_t("");
    m_AdvancedIsSet = false;
    m_Post_only = false;
    m_Usd = 0.0;
    m_UsdIsSet = false;
    m_Stop_price = 0.0;
    m_Stop_priceIsSet = false;
    m_Order_type = utility::conversions::to_string_t("");
    m_Last_update_timestamp = 0;
    m_Original_order_type = utility::conversions::to_string_t("");
    m_Original_order_typeIsSet = false;
    m_Max_show = 0.0;
    m_Profit_loss = 0.0;
    m_Profit_lossIsSet = false;
    m_Is_liquidation = false;
    m_Filled_amount = 0.0;
    m_Filled_amountIsSet = false;
    m_Label = utility::conversions::to_string_t("");
    m_Commission = 0.0;
    m_CommissionIsSet = false;
    m_Amount = 0.0;
    m_AmountIsSet = false;
    m_Trigger = utility::conversions::to_string_t("");
    m_TriggerIsSet = false;
    m_Instrument_name = utility::conversions::to_string_t("");
    m_Instrument_nameIsSet = false;
    m_Creation_timestamp = 0;
    m_Average_price = 0.0;
    m_Average_priceIsSet = false;
}

Order::~Order()
{
}

void Order::validate()
{
    // TODO: implement validation
}

web::json::value Order::toJson() const
{
    web::json::value val = web::json::value::object();

    val[utility::conversions::to_string_t("direction")] = ModelBase::toJson(m_Direction);
    if(m_Reduce_onlyIsSet)
    {
        val[utility::conversions::to_string_t("reduce_only")] = ModelBase::toJson(m_Reduce_only);
    }
    if(m_TriggeredIsSet)
    {
        val[utility::conversions::to_string_t("triggered")] = ModelBase::toJson(m_Triggered);
    }
    val[utility::conversions::to_string_t("order_id")] = ModelBase::toJson(m_Order_id);
    val[utility::conversions::to_string_t("price")] = ModelBase::toJson(m_Price);
    val[utility::conversions::to_string_t("time_in_force")] = ModelBase::toJson(m_Time_in_force);
    val[utility::conversions::to_string_t("api")] = ModelBase::toJson(m_Api);
    val[utility::conversions::to_string_t("order_state")] = ModelBase::toJson(m_Order_state);
    if(m_ImplvIsSet)
    {
        val[utility::conversions::to_string_t("implv")] = ModelBase::toJson(m_Implv);
    }
    if(m_AdvancedIsSet)
    {
        val[utility::conversions::to_string_t("advanced")] = ModelBase::toJson(m_Advanced);
    }
    val[utility::conversions::to_string_t("post_only")] = ModelBase::toJson(m_Post_only);
    if(m_UsdIsSet)
    {
        val[utility::conversions::to_string_t("usd")] = ModelBase::toJson(m_Usd);
    }
    if(m_Stop_priceIsSet)
    {
        val[utility::conversions::to_string_t("stop_price")] = ModelBase::toJson(m_Stop_price);
    }
    val[utility::conversions::to_string_t("order_type")] = ModelBase::toJson(m_Order_type);
    val[utility::conversions::to_string_t("last_update_timestamp")] = ModelBase::toJson(m_Last_update_timestamp);
    if(m_Original_order_typeIsSet)
    {
        val[utility::conversions::to_string_t("original_order_type")] = ModelBase::toJson(m_Original_order_type);
    }
    val[utility::conversions::to_string_t("max_show")] = ModelBase::toJson(m_Max_show);
    if(m_Profit_lossIsSet)
    {
        val[utility::conversions::to_string_t("profit_loss")] = ModelBase::toJson(m_Profit_loss);
    }
    val[utility::conversions::to_string_t("is_liquidation")] = ModelBase::toJson(m_Is_liquidation);
    if(m_Filled_amountIsSet)
    {
        val[utility::conversions::to_string_t("filled_amount")] = ModelBase::toJson(m_Filled_amount);
    }
    val[utility::conversions::to_string_t("label")] = ModelBase::toJson(m_Label);
    if(m_CommissionIsSet)
    {
        val[utility::conversions::to_string_t("commission")] = ModelBase::toJson(m_Commission);
    }
    if(m_AmountIsSet)
    {
        val[utility::conversions::to_string_t("amount")] = ModelBase::toJson(m_Amount);
    }
    if(m_TriggerIsSet)
    {
        val[utility::conversions::to_string_t("trigger")] = ModelBase::toJson(m_Trigger);
    }
    if(m_Instrument_nameIsSet)
    {
        val[utility::conversions::to_string_t("instrument_name")] = ModelBase::toJson(m_Instrument_name);
    }
    val[utility::conversions::to_string_t("creation_timestamp")] = ModelBase::toJson(m_Creation_timestamp);
    if(m_Average_priceIsSet)
    {
        val[utility::conversions::to_string_t("average_price")] = ModelBase::toJson(m_Average_price);
    }

    return val;
}

void Order::fromJson(const web::json::value& val)
{
    setDirection(ModelBase::stringFromJson(val.at(utility::conversions::to_string_t("direction"))));
    if(val.has_field(utility::conversions::to_string_t("reduce_only")))
    {
        const web::json::value& fieldValue = val.at(utility::conversions::to_string_t("reduce_only"));
        if(!fieldValue.is_null())
        {
            setReduceOnly(ModelBase::boolFromJson(fieldValue));
        }
    }
    if(val.has_field(utility::conversions::to_string_t("triggered")))
    {
        const web::json::value& fieldValue = val.at(utility::conversions::to_string_t("triggered"));
        if(!fieldValue.is_null())
        {
            setTriggered(ModelBase::boolFromJson(fieldValue));
        }
    }
    setOrderId(ModelBase::stringFromJson(val.at(utility::conversions::to_string_t("order_id"))));
    setPrice(ModelBase::doubleFromJson(val.at(utility::conversions::to_string_t("price"))));
    setTimeInForce(ModelBase::stringFromJson(val.at(utility::conversions::to_string_t("time_in_force"))));
    setApi(ModelBase::boolFromJson(val.at(utility::conversions::to_string_t("api"))));
    setOrderState(ModelBase::stringFromJson(val.at(utility::conversions::to_string_t("order_state"))));
    if(val.has_field(utility::conversions::to_string_t("implv")))
    {
        const web::json::value& fieldValue = val.at(utility::conversions::to_string_t("implv"));
        if(!fieldValue.is_null())
        {
            setImplv(ModelBase::doubleFromJson(fieldValue));
        }
    }
    if(val.has_field(utility::conversions::to_string_t("advanced")))
    {
        const web::json::value& fieldValue = val.at(utility::conversions::to_string_t("advanced"));
        if(!fieldValue.is_null())
        {
            setAdvanced(ModelBase::stringFromJson(fieldValue));
        }
    }
    setPostOnly(ModelBase::boolFromJson(val.at(utility::conversions::to_string_t("post_only"))));
    if(val.has_field(utility::conversions::to_string_t("usd")))
    {
        const web::json::value& fieldValue = val.at(utility::conversions::to_string_t("usd"));
        if(!fieldValue.is_null())
        {
            setUsd(ModelBase::doubleFromJson(fieldValue));
        }
    }
    if(val.has_field(utility::conversions::to_string_t("stop_price")))
    {
        const web::json::value& fieldValue = val.at(utility::conversions::to_string_t("stop_price"));
        if(!fieldValue.is_null())
        {
            setStopPrice(ModelBase::doubleFromJson(fieldValue));
        }
    }
    setOrderType(ModelBase::stringFromJson(val.at(utility::conversions::to_string_t("order_type"))));
    setLastUpdateTimestamp(ModelBase::int32_tFromJson(val.at(utility::conversions::to_string_t("last_update_timestamp"))));
    if(val.has_field(utility::conversions::to_string_t("original_order_type")))
    {
        const web::json::value& fieldValue = val.at(utility::conversions::to_string_t("original_order_type"));
        if(!fieldValue.is_null())
        {
            setOriginalOrderType(ModelBase::stringFromJson(fieldValue));
        }
    }
    setMaxShow(ModelBase::doubleFromJson(val.at(utility::conversions::to_string_t("max_show"))));
    if(val.has_field(utility::conversions::to_string_t("profit_loss")))
    {
        const web::json::value& fieldValue = val.at(utility::conversions::to_string_t("profit_loss"));
        if(!fieldValue.is_null())
        {
            setProfitLoss(ModelBase::doubleFromJson(fieldValue));
        }
    }
    setIsLiquidation(ModelBase::boolFromJson(val.at(utility::conversions::to_string_t("is_liquidation"))));
    if(val.has_field(utility::conversions::to_string_t("filled_amount")))
    {
        const web::json::value& fieldValue = val.at(utility::conversions::to_string_t("filled_amount"));
        if(!fieldValue.is_null())
        {
            setFilledAmount(ModelBase::doubleFromJson(fieldValue));
        }
    }
    setLabel(ModelBase::stringFromJson(val.at(utility::conversions::to_string_t("label"))));
    if(val.has_field(utility::conversions::to_string_t("commission")))
    {
        const web::json::value& fieldValue = val.at(utility::conversions::to_string_t("commission"));
        if(!fieldValue.is_null())
        {
            setCommission(ModelBase::doubleFromJson(fieldValue));
        }
    }
    if(val.has_field(utility::conversions::to_string_t("amount")))
    {
        const web::json::value& fieldValue = val.at(utility::conversions::to_string_t("amount"));
        if(!fieldValue.is_null())
        {
            setAmount(ModelBase::doubleFromJson(fieldValue));
        }
    }
    if(val.has_field(utility::conversions::to_string_t("trigger")))
    {
        const web::json::value& fieldValue = val.at(utility::conversions::to_string_t("trigger"));
        if(!fieldValue.is_null())
        {
            setTrigger(ModelBase::stringFromJson(fieldValue));
        }
    }
    if(val.has_field(utility::conversions::to_string_t("instrument_name")))
    {
        const web::json::value& fieldValue = val.at(utility::conversions::to_string_t("instrument_name"));
        if(!fieldValue.is_null())
        {
            setInstrumentName(ModelBase::stringFromJson(fieldValue));
        }
    }
    setCreationTimestamp(ModelBase::int32_tFromJson(val.at(utility::conversions::to_string_t("creation_timestamp"))));
    if(val.has_field(utility::conversions::to_string_t("average_price")))
    {
        const web::json::value& fieldValue = val.at(utility::conversions::to_string_t("average_price"));
        if(!fieldValue.is_null())
        {
            setAveragePrice(ModelBase::doubleFromJson(fieldValue));
        }
    }
}

void Order::toMultipart(std::shared_ptr<MultipartFormData> multipart, const utility::string_t& prefix) const
{
    utility::string_t namePrefix = prefix;
    if(namePrefix.size() > 0 && namePrefix.substr(namePrefix.size() - 1) != utility::conversions::to_string_t("."))
    {
        namePrefix += utility::conversions::to_string_t(".");
    }

    multipart->add(ModelBase::toHttpContent(namePrefix + utility::conversions::to_string_t("direction"), m_Direction));
    if(m_Reduce_onlyIsSet)
    {
        multipart->add(ModelBase::toHttpContent(namePrefix + utility::conversions::to_string_t("reduce_only"), m_Reduce_only));
    }
    if(m_TriggeredIsSet)
    {
        multipart->add(ModelBase::toHttpContent(namePrefix + utility::conversions::to_string_t("triggered"), m_Triggered));
    }
    multipart->add(ModelBase::toHttpContent(namePrefix + utility::conversions::to_string_t("order_id"), m_Order_id));
    multipart->add(ModelBase::toHttpContent(namePrefix + utility::conversions::to_string_t("price"), m_Price));
    multipart->add(ModelBase::toHttpContent(namePrefix + utility::conversions::to_string_t("time_in_force"), m_Time_in_force));
    multipart->add(ModelBase::toHttpContent(namePrefix + utility::conversions::to_string_t("api"), m_Api));
    multipart->add(ModelBase::toHttpContent(namePrefix + utility::conversions::to_string_t("order_state"), m_Order_state));
    if(m_ImplvIsSet)
    {
        multipart->add(ModelBase::toHttpContent(namePrefix + utility::conversions::to_string_t("implv"), m_Implv));
    }
    if(m_AdvancedIsSet)
    {
        multipart->add(ModelBase::toHttpContent(namePrefix + utility::conversions::to_string_t("advanced"), m_Advanced));
    }
    multipart->add(ModelBase::toHttpContent(namePrefix + utility::conversions::to_string_t("post_only"), m_Post_only));
    if(m_UsdIsSet)
    {
        multipart->add(ModelBase::toHttpContent(namePrefix + utility::conversions::to_string_t("usd"), m_Usd));
    }
    if(m_Stop_priceIsSet)
    {
        multipart->add(ModelBase::toHttpContent(namePrefix + utility::conversions::to_string_t("stop_price"), m_Stop_price));
    }
    multipart->add(ModelBase::toHttpContent(namePrefix + utility::conversions::to_string_t("order_type"), m_Order_type));
    multipart->add(ModelBase::toHttpContent(namePrefix + utility::conversions::to_string_t("last_update_timestamp"), m_Last_update_timestamp));
    if(m_Original_order_typeIsSet)
    {
        multipart->add(ModelBase::toHttpContent(namePrefix + utility::conversions::to_string_t("original_order_type"), m_Original_order_type));
    }
    multipart->add(ModelBase::toHttpContent(namePrefix + utility::conversions::to_string_t("max_show"), m_Max_show));
    if(m_Profit_lossIsSet)
    {
        multipart->add(ModelBase::toHttpContent(namePrefix + utility::conversions::to_string_t("profit_loss"), m_Profit_loss));
    }
    multipart->add(ModelBase::toHttpContent(namePrefix + utility::conversions::to_string_t("is_liquidation"), m_Is_liquidation));
    if(m_Filled_amountIsSet)
    {
        multipart->add(ModelBase::toHttpContent(namePrefix + utility::conversions::to_string_t("filled_amount"), m_Filled_amount));
    }
    multipart->add(ModelBase::toHttpContent(namePrefix + utility::conversions::to_string_t("label"), m_Label));
    if(m_CommissionIsSet)
    {
        multipart->add(ModelBase::toHttpContent(namePrefix + utility::conversions::to_string_t("commission"), m_Commission));
    }
    if(m_AmountIsSet)
    {
        multipart->add(ModelBase::toHttpContent(namePrefix + utility::conversions::to_string_t("amount"), m_Amount));
    }
    if(m_TriggerIsSet)
    {
        multipart->add(ModelBase::toHttpContent(namePrefix + utility::conversions::to_string_t("trigger"), m_Trigger));
    }
    if(m_Instrument_nameIsSet)
    {
        multipart->add(ModelBase::toHttpContent(namePrefix + utility::conversions::to_string_t("instrument_name"), m_Instrument_name));
    }
    multipart->add(ModelBase::toHttpContent(namePrefix + utility::conversions::to_string_t("creation_timestamp"), m_Creation_timestamp));
    if(m_Average_priceIsSet)
    {
        multipart->add(ModelBase::toHttpContent(namePrefix + utility::conversions::to_string_t("average_price"), m_Average_price));
    }
}

void Order::fromMultiPart(std::shared_ptr<MultipartFormData> multipart, const utility::string_t& prefix)
{
    utility::string_t namePrefix = prefix;
    if(namePrefix.size() > 0 && namePrefix.substr(namePrefix.size() - 1) != utility::conversions::to_string_t("."))
    {
        namePrefix += utility::conversions::to_string_t(".");
    }

    setDirection(ModelBase::stringFromHttpContent(multipart->getContent(utility::conversions::to_string_t("direction"))));
    if(multipart->hasContent(utility::conversions::to_string_t("reduce_only")))
    {
        setReduceOnly(ModelBase::boolFromHttpContent(multipart->getContent(utility::conversions::to_string_t("reduce_only"))));
    }
    if(multipart->hasContent(utility::conversions::to_string_t("triggered")))
    {
        setTriggered(ModelBase::boolFromHttpContent(multipart->getContent(utility::conversions::to_string_t("triggered"))));
    }
    setOrderId(ModelBase::stringFromHttpContent(multipart->getContent(utility::conversions::to_string_t("order_id"))));
    setPrice(ModelBase::doubleFromHttpContent(multipart->getContent(utility::conversions::to_string_t("price"))));
    setTimeInForce(ModelBase::stringFromHttpContent(multipart->getContent(utility::conversions::to_string_t("time_in_force"))));
    setApi(ModelBase::boolFromHttpContent(multipart->getContent(utility::conversions::to_string_t("api"))));
    setOrderState(ModelBase::stringFromHttpContent(multipart->getContent(utility::conversions::to_string_t("order_state"))));
    if(multipart->hasContent(utility::conversions::to_string_t("implv")))
    {
        setImplv(ModelBase::doubleFromHttpContent(multipart->getContent(utility::conversions::to_string_t("implv"))));
    }
    if(multipart->hasContent(utility::conversions::to_string_t("advanced")))
    {
        setAdvanced(ModelBase::stringFromHttpContent(multipart->getContent(utility::conversions::to_string_t("advanced"))));
    }
    setPostOnly(ModelBase::boolFromHttpContent(multipart->getContent(utility::conversions::to_string_t("post_only"))));
    if(multipart->hasContent(utility::conversions::to_string_t("usd")))
    {
        setUsd(ModelBase::doubleFromHttpContent(multipart->getContent(utility::conversions::to_string_t("usd"))));
    }
    if(multipart->hasContent(utility::conversions::to_string_t("stop_price")))
    {
        setStopPrice(ModelBase::doubleFromHttpContent(multipart->getContent(utility::conversions::to_string_t("stop_price"))));
    }
    setOrderType(ModelBase::stringFromHttpContent(multipart->getContent(utility::conversions::to_string_t("order_type"))));
    setLastUpdateTimestamp(ModelBase::int32_tFromHttpContent(multipart->getContent(utility::conversions::to_string_t("last_update_timestamp"))));
    if(multipart->hasContent(utility::conversions::to_string_t("original_order_type")))
    {
        setOriginalOrderType(ModelBase::stringFromHttpContent(multipart->getContent(utility::conversions::to_string_t("original_order_type"))));
    }
    setMaxShow(ModelBase::doubleFromHttpContent(multipart->getContent(utility::conversions::to_string_t("max_show"))));
    if(multipart->hasContent(utility::conversions::to_string_t("profit_loss")))
    {
        setProfitLoss(ModelBase::doubleFromHttpContent(multipart->getContent(utility::conversions::to_string_t("profit_loss"))));
    }
    setIsLiquidation(ModelBase::boolFromHttpContent(multipart->getContent(utility::conversions::to_string_t("is_liquidation"))));
    if(multipart->hasContent(utility::conversions::to_string_t("filled_amount")))
    {
        setFilledAmount(ModelBase::doubleFromHttpContent(multipart->getContent(utility::conversions::to_string_t("filled_amount"))));
    }
    setLabel(ModelBase::stringFromHttpContent(multipart->getContent(utility::conversions::to_string_t("label"))));
    if(multipart->hasContent(utility::conversions::to_string_t("commission")))
    {
        setCommission(ModelBase::doubleFromHttpContent(multipart->getContent(utility::conversions::to_string_t("commission"))));
    }
    if(multipart->hasContent(utility::conversions::to_string_t("amount")))
    {
        setAmount(ModelBase::doubleFromHttpContent(multipart->getContent(utility::conversions::to_string_t("amount"))));
    }
    if(multipart->hasContent(utility::conversions::to_string_t("trigger")))
    {
        setTrigger(ModelBase::stringFromHttpContent(multipart->getContent(utility::conversions::to_string_t("trigger"))));
    }
    if(multipart->hasContent(utility::conversions::to_string_t("instrument_name")))
    {
        setInstrumentName(ModelBase::stringFromHttpContent(multipart->getContent(utility::conversions::to_string_t("instrument_name"))));
    }
    setCreationTimestamp(ModelBase::int32_tFromHttpContent(multipart->getContent(utility::conversions::to_string_t("creation_timestamp"))));
    if(multipart->hasContent(utility::conversions::to_string_t("average_price")))
    {
        setAveragePrice(ModelBase::doubleFromHttpContent(multipart->getContent(utility::conversions::to_string_t("average_price"))));
    }
}

utility::string_t Order::getDirection() const
{
    return m_Direction;
}

void Order::setDirection(const utility::string_t& value)
{
    m_Direction = value;
    
}

bool Order::isReduceOnly() const
{
    return m_Reduce_only;
}

void Order::setReduceOnly(bool value)
{
    m_Reduce_only = value;
    m_Reduce_onlyIsSet = true;
}

bool Order::reduceOnlyIsSet() const
{
    return m_Reduce_onlyIsSet;
}

void Order::unsetReduce_only()
{
    m_Reduce_onlyIsSet = false;
}

bool Order::isTriggered() const
{
    return m_Triggered;
}

void Order::setTriggered(bool value)
{
    m_Triggered = value;
    m_TriggeredIsSet = true;
}

bool Order::triggeredIsSet() const
{
    return m_TriggeredIsSet;
}

void Order::unsetTriggered()
{
    m_TriggeredIsSet = false;
}

utility::string_t Order::getOrderId() const
{
    return m_Order_id;
}

void Order::setOrderId(const utility::string_t& value)
{
    m_Order_id = value;
    
}

double Order::getPrice() const
{
    return m_Price;
}

void Order::setPrice(double value)
{
    m_Price = value;
    
}

utility::string_t Order::getTimeInForce() const
{
    return m_Time_in_force;
}

void Order::setTimeInForce(const utility::string_t& value)
{
    m_Time_in_force = value;
    
}

bool Order::isApi() const
{
    return m_Api;
}

void Order::setApi(bool value)
{
    m_Api = value;
    
}

utility::string_t Order::getOrderState() const
{
    return m_Order_state;
}

void Order::setOrderState(const utility::string_t& value)
{
    m_Order_state = value;
    
}

double Order::getImplv() const
{
    return m_Implv;
}

void Order::setImplv(double value)
{
    m_Implv = value;
    m_ImplvIsSet = true;
}

bool Order::implvIsSet() const
{
    return m_ImplvIsSet;
}

void Order::unsetImplv()
{
    m_ImplvIsSet = false;
}

utility::string_t Order::getAdvanced() const
{
    return m_Advanced;
}

void Order::setAdvanced(const utility::string_t& value)
{
    m_Advanced = value;
    m_AdvancedIsSet = true;
}

bool Order::advancedIsSet() const
{
    return m_AdvancedIsSet;
}

void Order::unsetAdvanced()
{
    m_AdvancedIsSet = false;
}

bool Order::isPostOnly() const
{
    return m_Post_only;
}

void Order::setPostOnly(bool value)
{
    m_Post_only = value;
    
}

double Order::getUsd() const
{
    return m_Usd;
}

void Order::setUsd(double value)
{
    m_Usd = value;
    m_UsdIsSet = true;
}

bool Order::usdIsSet() const
{
    return m_UsdIsSet;
}

void Order::unsetUsd()
{
    m_UsdIsSet = false;
}

double Order::getStopPrice() const
{
    return m_Stop_price;
}

void Order::setStopPrice(double value)
{
    m_Stop_price = value;
    m_Stop_priceIsSet = true;
}

bool Order::stopPriceIsSet() const
{
    return m_Stop_priceIsSet;
}

void Order::unsetStop_price()
{
    m_Stop_priceIsSet = false;
}

utility::string_t Order::getOrderType() const
{
    return m_Order_type;
}

void Order::setOrderType(const utility::string_t& value)
{
    m_Order_type = value;
    
}

int32_t Order::getLastUpdateTimestamp() const
{
    return m_Last_update_timestamp;
}

void Order::setLastUpdateTimestamp(int32_t value)
{
    m_Last_update_timestamp = value;
    
}

utility::string_t Order::getOriginalOrderType() const
{
    return m_Original_order_type;
}

void Order::setOriginalOrderType(const utility::string_t& value)
{
    m_Original_order_type = value;
    m_Original_order_typeIsSet = true;
}

bool Order::originalOrderTypeIsSet() const
{
    return m_Original_order_typeIsSet;
}

void Order::unsetOriginal_order_type()
{
    m_Original_order_typeIsSet = false;
}

double Order::getMaxShow() const
{
    return m_Max_show;
}

void Order::setMaxShow(double value)
{
    m_Max_show = value;
    
}

double Order::getProfitLoss() const
{
    return m_Profit_loss;
}

void Order::setProfitLoss(double value)
{
    m_Profit_loss = value;
    m_Profit_lossIsSet = true;
}

bool Order::profitLossIsSet() const
{
    return m_Profit_lossIsSet;
}

void Order::unsetProfit_loss()
{
    m_Profit_lossIsSet = false;
}

bool Order::isIsLiquidation() const
{
    return m_Is_liquidation;
}

void Order::setIsLiquidation(bool value)
{
    m_Is_liquidation = value;
    
}

double Order::getFilledAmount() const
{
    return m_Filled_amount;
}

void Order::setFilledAmount(double value)
{
    m_Filled_amount = value;
    m_Filled_amountIsSet = true;
}

bool Order::filledAmountIsSet() const
{
    return m_Filled_amountIsSet;
}

void Order::unsetFilled_amount()
{
    m_Filled_amountIsSet = false;
}

utility::string_t Order::getLabel() const
{
    return m_Label;
}

void Order::setLabel(const utility::string_t& value)
{
    m_Label = value;
    
}

double Order::getCommission() const
{
    return m_Commission;
}

void Order::setCommission(double value)
{
    m_Commission = value;
    m_CommissionIsSet = true;
}

bool Order::commissionIsSet() const
{
    return m_CommissionIsSet;
}

void Order::unsetCommission()
{
    m_CommissionIsSet = false;
}

double Order::getAmount() const
{
    return m_Amount;
}

void Order::setAmount(double value)
{
    m_Amount = value;
    m_AmountIsSet = true;
}

bool Order::amountIsSet() const
{
    return m_AmountIsSet;
}

void Order::unsetAmount()
{
    m_AmountIsSet = false;
}

utility::string_t Order::getTrigger() const
{
    return m_Trigger;
}

void Order::setTrigger(const utility::string_t& value)
{
    m_Trigger = value;
    m_TriggerIsSet = true;
}

bool Order::triggerIsSet() const
{
    return m_TriggerIsSet;
}

void Order::unsetTrigger()
{
    m_TriggerIsSet = false;
}

utility::string_t Order::getInstrumentName() const
{
    return m_Instrument_name;
}

void Order::setInstrumentName(const utility::string_t& value)
{
    m_Instrument_name = value;
    m_Instrument_nameIsSet = true;
}

bool Order::instrumentNameIsSet() const
{
    return m_Instrument_nameIsSet;
}

void Order::unsetInstrument_name()
{
    m_Instrument_nameIsSet = false;
}

int32_t Order::getCreationTimestamp() const
{
    return m_Creation_timestamp;
}

void Order::setCreationTimestamp(int32_t value)
{
    m_Creation_timestamp = value;
    
}

double Order::getAveragePrice() const
{
    return m_Average_price;
}

void Order::setAveragePrice(double value)
{
    m_Average_price = value;
    m_Average_priceIsSet = true;
}

bool Order::averagePriceIsSet() const
{
    return m_Average_priceIsSet;
}

void Order::unsetAverage_price()
{
    m_Average_priceIsSet = false;
}

}
}
}
}


