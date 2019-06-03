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
 *
 */


import ApiClient from './ApiClient';
import AddressBookItem from './model/AddressBookItem';
import BookSummary from './model/BookSummary';
import Currency from './model/Currency';
import CurrencyPortfolio from './model/CurrencyPortfolio';
import CurrencyWithdrawalPriorities from './model/CurrencyWithdrawalPriorities';
import Deposit from './model/Deposit';
import Instrument from './model/Instrument';
import KeyNumberPair from './model/KeyNumberPair';
import Order from './model/Order';
import OrderIdInitialMarginPair from './model/OrderIdInitialMarginPair';
import Portfolio from './model/Portfolio';
import PortfolioEth from './model/PortfolioEth';
import Position from './model/Position';
import PublicTrade from './model/PublicTrade';
import Settlement from './model/Settlement';
import TradesVolumes from './model/TradesVolumes';
import TransferItem from './model/TransferItem';
import Types from './model/Types';
import UserTrade from './model/UserTrade';
import Withdrawal from './model/Withdrawal';
import AccountManagementApi from './api/AccountManagementApi';
import AuthenticationApi from './api/AuthenticationApi';
import InternalApi from './api/InternalApi';
import MarketDataApi from './api/MarketDataApi';
import PrivateApi from './api/PrivateApi';
import PublicApi from './api/PublicApi';
import SupportingApi from './api/SupportingApi';
import TradingApi from './api/TradingApi';
import WalletApi from './api/WalletApi';


/**
* OverviewDeribit_provides_three_different_interfaces_to_access_the_API__JSON_RPC_over_Websocket_json_rpc__JSON_RPC_over_HTTP_json_rpc__FIX_fix_api__Financial_Information_eXchangeWith_the_API_Console_you_can_use_and_test_the_JSON_RPC_API_both_via_HTTP_and_via_Websocket__To_visit_the_API_console_go_to___Account__API_tab__API_Console_tab___NamingDeribit_tradeable_assets_or_instruments_use_the_following_system_of_naming_Kind_Examples_Template_Comments___________________________________Future_codeBTC_25MAR16_code_codeBTC_5AUG16_code_codeBTC_DMMMYY_code_codeBTC_code_is_currency_codeDMMMYY_code_is_expiration_date_codeD_code_stands_for_day_of_month__1_or_2_digits_codeMMM_code___month__3_first_letters_in_English_codeYY_code_stands_for_year___Perpetual_codeBTC_PERPETUAL_code__________________________Perpetual_contract_for_currency_codeBTC_code___Option_codeBTC_25MAR16_420_C_code_codeBTC_5AUG16_580_P_code_codeBTC_DMMMYY_STRIKE_K_code_codeSTRIKE_code_is_option_strike_price_in_USD__Template_codeK_code_is_option_kind_codeC_code_for_call_options_or_codeP_code_for_put_options___JSON_RPCJSON_RPC_is_a_light_weight_remote_procedure_call__RPC_protocol__The__JSON_RPC_specification_https__www_jsonrpc_org_specification_defines_the_datastructures_that_are_used_for_the_messages_that_are_exchanged_between_clientand_server_as_well_as_the_rules_around_their_processing__JSON_RPC_usesJSON__RFC_4627_as_data_format_JSON_RPC_is_transport_agnostic_it_does_not_specify_which_transportmechanism_must_be_used__The_Deribit_API_supports_both_Websocket__preferredand_HTTP__with_limitations_subscriptions_are_not_supported_over_HTTP__Request_messages_An_example_of_a_request_messagejson____jsonrpc_2_0____id_8066____method_public_ticker____params_________instrument_BTC_24AUG18_6500_P____According_to_the_JSON_RPC_sepcification_the_requests_must_be_JSON_objects_with_the_following_fields__Name_Type_Description_________________________jsonrpc_string_The_version_of_the_JSON_RPC_spec_2_0__id_integer_or_string_An_identifier_of_the_request__If_it_is_included_then_the_response_will_contain_the_same_identifier__method_string_The_method_to_be_invoked__params_object_The_parameters_values_for_the_method__The_field_names_must_match_with_the_expected_parameter_names__The_parameters_that_are_expected_are_described_in_the_documentation_for_the_methods_below__aside_classwarningThe_JSON_RPC_specification_describes_two_featuresthat_are_currently_not_supported_by_the_APIulliSpecification_of_parameter_values_by_position_liliBatch_requests_li_ul_aside_Response_messages_An_example_of_a_response_messagejson____jsonrpc_2_0____id_5239____testnet_false____result______________________currency_BTC____________currencyLong_Bitcoin____________minConfirmation_2____________txFee_0_0006____________isActive_true____________coinType_BITCOIN____________baseAddress_null________________usIn_1535043730126248____usOut_1535043730126250____usDiff_2The_JSON_RPC_API_always_responds_with_a_JSON_object_with_the_following_fields__Name_Type_Description_________________________id_integer_This_is_the_same_id_that_was_sent_in_the_request___result_any_If_successful_the_result_of_the_API_call__The_format_for_the_result_is_described_with_each_method___error_error_object_Only_present_if_there_was_an_error_invoking_the_method__The_error_object_is_described_below___testnet_boolean_Indicates_whether_the_API_in_use_is_actually_the_test_API___codefalse_code_for_production_server_codetrue_code_for_test_server___usIn_integer_The_timestamp_when_the_requests_was_received__microseconds_since_the_Unix_epoch__usOut_integer_The_timestamp_when_the_response_was_sent__microseconds_since_the_Unix_epoch__usDiff_integer_The_number_of_microseconds_that_was_spent_handling_the_request_aside_classnoticeThe_fields_codetestnet_code_codeusIn_code_codeusOut_code_andcodeusDiff_code_are_not_part_of_the_JSON_RPC_standard__pIn_order_not_to_clutter_the_examples_they_will_generally_be_omitted_fromthe_example_code__p_aside_An_example_of_a_response_with_an_errorjson____jsonrpc_2_0____id_8163____error_________code_11050________message_bad_request________testnet_false____usIn_1535037392434763____usOut_1535037392448119____usDiff_13356In_case_of_an_error_the_response_message_will_contain_the_error_field_withas_value_an_object_with_the_following_with_the_following_fields_Name_Type_Description________________________code_integer_A_number_that_indicates_the_kind_of_error___message_string_A_short_description_that_indicates_the_kind_of_error___data_any_Additional_data_about_the_error__This_field_may_be_omitted___Notifications_An_example_of_a_notificationjson____jsonrpc_2_0____method_subscription____params_________channel_deribit_price_index_btc_usd________data_____________timestamp_1535098298227____________price_6521_17____________index_name_btc_usd____________API_users_can_subscribe_to_certain_types_of_notifications__This_means_that_theywill_receive_JSON_RPC_notification_messages_from_the_server_when_certainevents_occur_such_as_changes_to_the_index_price_or_changes_to_the_orderbook_for_a_certain_instrument__The_API_methods__public_subscribe_public_subscribe_and_private_subscribe_private_subscribe_are_used_to_set_up_a_subscription_Since_HTTP_does_not_support_the_sending_of_messages_from_server_to_clientthese_methods_are_only_availble_when_using_the_Websocket_transportmechanism_At_the_moment_of_subscription_a_channelmust_be_specified__The_channel_determines_the_type_of_events_that_will_bereceived___See__Subscriptions_subscriptions_for_more_details_about_the_channels_In_accordance_with_the_JSON_RPC_specification_the_format_of_a_notification_is_that_of_a_request_message_without_an_codeid_code_field__The_value_of_thecodemethod_code_field_will_always_be_codesubscription_code__Thecodeparams_code_field_will_always_be_an_object_with_2_memberscodechannel_code_and_codedata_code__The_value_of_thecodechannel_code_member_is_the_name_of_the_channel__a_string__Thevalue_of_the_codedata_code_member_is_an_object_that_contains_data_that_is_specific_for_the_channel__Authentication_An_example_of_a_JSON_request_with_tokenjson____id_5647____method_private_get_subaccounts____params_________access_token_67SVutDoVZSzkUStHSuk51WntMNBJ5mh5DYZhwzpiqDF____The_API_consists_of_public_and_private_methods__The_public_methods_do_notrequire_authentication__The_private_methods_use_OAuth_2_0_authentication_This_means_that_a_valid_OAuth_access_token_must_be_included_in_the_request_whichcan_get_achived_by_calling_method__public_auth_public_auth_When_the_token_was_assigned_to_the_user_it_should_be_passed_along_with_other_request_parameters_back_to_the_server_Connection_type_Access_token_placement___________________Websocket_Inside_request_JSON_parameters_as_an_access_token_field__HTTP__REST_Header_Authorization_bearer_Token__value__Additional_authorization_method___basic_user_credentialsspan_stylecolorredb__Not_recommended___however_it_could_be_useful_for_quick_testing_API_b_span_brEvery_private_method_could_be_accessed_by_providing_inside_HTTP_Authorization_Basic_XXX_header_values_withuser_ClientId_and_assigned_ClientSecret__both_values_can_be_found_on_the_API_page_on_the_Deribit_website_encoded_with_Base64codeAuthorization_Basic_BASE64_ClientId____ClientSecret_code_Additional_authorization_method___Deribit_signature_credentialsThe_Derbit_service_provides_dedicated_authorization_method_which_harness_user_generated_signature_to_increasesecurity_level_for_passing_request_data__Generated_value_is_passed_inside_Authorization_header_coded_ascodeAuthorization_deri_hmac_sha256_idClientIdtsTimestampsigSignaturenonceNonce_codewhere_Deribit_credential_Description___________________ClientId_Can_be_found_on_the_API_page_on_the_Deribit_website__Timestamp_Time_when_the_request_was_generated___given_as_miliseconds__Its_valid_for_60_seconds_since_generation_after_that_time_any_request_with_an_old_timestamp_will_be_rejected___Signature_Value_for_signature_calculated_as_described_below___Nonce_Single_usage_user_generated_initialization_vector_for_the_server_token_The_signature_is_generated_by_the_following_formulacode_Signature__HEX_STRING__HMAC_SHA256__ClientSecret_StringToSign___code_br_code_StringToSign___Timestamp___n__Nonce___n__RequestData_code_br_code_RequestData___UPPERCASE_HTTP_METHOD_____n__URI____n__RequestBody___n_code_br_e_g___using_shell_with_openssl_toolcodenbspnbspnbspnbspClientIdAAAAAAAAAAA_code_br_codenbspnbspnbspnbspClientSecretABCD_code_br_codenbspnbspnbspnbspTimestamp__date_s000__code_br_codenbspnbspnbspnbspNonce__cat__dev_urandom___tr__dc_a_z0_9___head__c8__code_br_codenbspnbspnbspnbspURI_api_v2_private_get_account_summarycurrencyBTC_code_br_codenbspnbspnbspnbspHttpMethodGET_code_br_codenbspnbspnbspnbspBody_code_br_br_codenbspnbspnbspnbspSignature__echo__ne_Timestamp_nNonce_nHttpMethod_nURI_nBody_n___openssl_sha256__r__hmac_ClientSecret___cut__f1__d___code_br_brcodenbspnbspnbspnbspecho_Signature_code_br_br_codenbspnbspnbspnbspshell_output_ea40d5e5e4fae235ab22b61da98121fbf4acdc06db03d632e23c66bcccb90d2c___WARNING_Exact_value_depends_on_current_timestamp_and_client_credentials_code_br_br_codenbspnbspnbspnbspcurl__s__X_HttpMethod__H_Authorization_deri_hmac_sha256_idClientIdtsTimestampnonceNoncesigSignature_https__www_deribit_comURI_code_br_br__Additional_authorization_method___signature_credentials__WebSocket_APIWhen_connecting_through_Websocket_user_can_request_for_authorization_using_client_credential_method_which_requires_providing_following_parameters__as_a_part_of_JSON_request_JSON_parameter_Description___________________grant_type_Must_be_client_signature__client_id_Can_be_found_on_the_API_page_on_the_Deribit_website__timestamp_Time_when_the_request_was_generated___given_as_miliseconds__Its_valid_for_60_seconds_since_generation_after_that_time_any_request_with_an_old_timestamp_will_be_rejected___signature_Value_for_signature_calculated_as_described_below___nonce_Single_usage_user_generated_initialization_vector_for_the_server_token__data_Optional_field_which_contains_any_user_specific_value_The_signature_is_generated_by_the_following_formulacode_StringToSign___Timestamp___n__Nonce___n__Data_code_br_code_Signature__HEX_STRING__HMAC_SHA256__ClientSecret_StringToSign___code_br_e_g___using_shell_with_openssl_toolcodenbspnbspnbspnbspClientIdAAAAAAAAAAA_code_br_codenbspnbspnbspnbspClientSecretABCD_code_br_codenbspnbspnbspnbspTimestamp__date_s000___e_g__1554883365000__code_br_codenbspnbspnbspnbspNonce__cat__dev_urandom___tr__dc_a_z0_9___head__c8___e_g__fdbmmz79__code_br_codenbspnbspnbspnbspData_code_br_br_codenbspnbspnbspnbspSignature__echo__ne_Timestamp_nNonce_nData_n___openssl_sha256__r__hmac_ClientSecret___cut__f1__d___code_br_brcodenbspnbspnbspnbspecho_Signature_code_br_br_codenbspnbspnbspnbspshell_output_e20c9cd5639d41f8bbc88f4d699c4baf94a4f0ee320e9a116b72743c449eb994___WARNING_Exact_value_depends_on_current_timestamp_and_client_credentials_code_br_br_You_can_also_check_the_signature_value_using_some_online_tools_like_e_g__https__codebeautify_org_hmac_generator_https__codebeautify_org_hmac_generator__but_dont_forget_about_adding_newline_after_each_part_of_the_hashed_text_and_remember_that_you_should_use_it_only_with_your_test_credentials_Heres_a_sample_JSON_request_created_using_the_values_from_the_example_abovecode_____________________________brnbspnbspjsonrpc__2_0__________brnbspnbspid__9929________________brnbspnbspmethod__public_auth___brnbspnbspparams___________________brnbspnbsp_________________________brnbspnbspnbspnbspgrant_type__client_signature____brnbspnbspnbspnbspclient_id__AAAAAAAAAAA__________brnbspnbspnbspnbsptimestamp_1554883365000_________brnbspnbspnbspnbspnonce_fdbmmz79__________________brnbspnbspnbspnbspdata____________________________brnbspnbspnbspnbspsignature__e20c9cd5639d41f8bbc88f4d699c4baf94a4f0ee320e9a116b72743c449eb994___brnbspnbsp_________________________br_____________________________br_code_Access_scopeWhen_asking_for_access_token_user_can_provide_the_required_access_level__called_scope_which_defineswhat_type_of_functionality_he_she_wants_to_use_and_whether_requests_are_only_going_to_check_for_some_data_oralso_to_update_them__Scopes_are_required_and_checked_for_private_methods_so_if_you_plan_to_use_only_public_information_youcan_stay_with_values_assigned_by_default__Scope_Description___________________accountread_Access_to_account_methods___read_only_data__accountread_write_Access_to_account_methods___allows_to_manage_account_settings_add_subaccounts_etc___traderead_Access_to_trade_methods___read_only_data__traderead_write_Access_to_trade_methods___required_to_create_and_modify_orders__walletread_Access_to_wallet_methods___read_only_data__walletread_write_Access_to_wallet_methods___allows_to_withdraw_generate_new_deposit_address_etc___walletnone_accountnone_tradenone_Blocked_access_to_specified_functionality_span_stylecolorredNOTICE_span_Depending_on_choosing_an_authentication_method__grant_type_some_scopes_could_be_narrowed_by_the_server__e_g__when_grant_type__client_credentials_and_scope__walletread_write_its_modified_by_the_server_as_scope__walletread_JSON_RPC_over_websocketWebsocket_is_the_prefered_transport_mechanism_for_the_JSON_RPC_API_becauseit_is_faster_and_because_it_can_support__subscriptions_subscriptions_and_cancel_on_disconnect_private_enable_cancel_on_disconnect__The_code_examplesthat_can_be_found_next_to_each_of_the_methods_show_how_websockets_can_beused_from_Python_or_Javascript_node_js__JSON_RPC_over_HTTPBesides_websockets_it_is_also_possible_to_use_the_API_via_HTTP__The_codeexamples_for_shell_show_how_this_can_be_done_using_curl__Note_thatsubscriptions_and_cancel_on_disconnect_are_not_supported_via_HTTP_Methods.<br>
* The <code>index</code> module provides access to constructors for all the classes which comprise the public API.
* <p>
* An AMD (recommended!) or CommonJS application will generally do something equivalent to the following:
* <pre>
* var DeribitApi = require('index'); // See note below*.
* var xxxSvc = new DeribitApi.XxxApi(); // Allocate the API class we're going to use.
* var yyyModel = new DeribitApi.Yyy(); // Construct a model instance.
* yyyModel.someProperty = 'someValue';
* ...
* var zzz = xxxSvc.doSomething(yyyModel); // Invoke the service.
* ...
* </pre>
* <em>*NOTE: For a top-level AMD script, use require(['index'], function(){...})
* and put the application logic within the callback function.</em>
* </p>
* <p>
* A non-AMD browser application (discouraged) might do something like this:
* <pre>
* var xxxSvc = new DeribitApi.XxxApi(); // Allocate the API class we're going to use.
* var yyy = new DeribitApi.Yyy(); // Construct a model instance.
* yyyModel.someProperty = 'someValue';
* ...
* var zzz = xxxSvc.doSomething(yyyModel); // Invoke the service.
* ...
* </pre>
* </p>
* @module index
* @version 2.0.0
*/
export {
    /**
     * The ApiClient constructor.
     * @property {module:ApiClient}
     */
    ApiClient,

    /**
     * The AddressBookItem model constructor.
     * @property {module:model/AddressBookItem}
     */
    AddressBookItem,

    /**
     * The BookSummary model constructor.
     * @property {module:model/BookSummary}
     */
    BookSummary,

    /**
     * The Currency model constructor.
     * @property {module:model/Currency}
     */
    Currency,

    /**
     * The CurrencyPortfolio model constructor.
     * @property {module:model/CurrencyPortfolio}
     */
    CurrencyPortfolio,

    /**
     * The CurrencyWithdrawalPriorities model constructor.
     * @property {module:model/CurrencyWithdrawalPriorities}
     */
    CurrencyWithdrawalPriorities,

    /**
     * The Deposit model constructor.
     * @property {module:model/Deposit}
     */
    Deposit,

    /**
     * The Instrument model constructor.
     * @property {module:model/Instrument}
     */
    Instrument,

    /**
     * The KeyNumberPair model constructor.
     * @property {module:model/KeyNumberPair}
     */
    KeyNumberPair,

    /**
     * The Order model constructor.
     * @property {module:model/Order}
     */
    Order,

    /**
     * The OrderIdInitialMarginPair model constructor.
     * @property {module:model/OrderIdInitialMarginPair}
     */
    OrderIdInitialMarginPair,

    /**
     * The Portfolio model constructor.
     * @property {module:model/Portfolio}
     */
    Portfolio,

    /**
     * The PortfolioEth model constructor.
     * @property {module:model/PortfolioEth}
     */
    PortfolioEth,

    /**
     * The Position model constructor.
     * @property {module:model/Position}
     */
    Position,

    /**
     * The PublicTrade model constructor.
     * @property {module:model/PublicTrade}
     */
    PublicTrade,

    /**
     * The Settlement model constructor.
     * @property {module:model/Settlement}
     */
    Settlement,

    /**
     * The TradesVolumes model constructor.
     * @property {module:model/TradesVolumes}
     */
    TradesVolumes,

    /**
     * The TransferItem model constructor.
     * @property {module:model/TransferItem}
     */
    TransferItem,

    /**
     * The Types model constructor.
     * @property {module:model/Types}
     */
    Types,

    /**
     * The UserTrade model constructor.
     * @property {module:model/UserTrade}
     */
    UserTrade,

    /**
     * The Withdrawal model constructor.
     * @property {module:model/Withdrawal}
     */
    Withdrawal,

    /**
    * The AccountManagementApi service constructor.
    * @property {module:api/AccountManagementApi}
    */
    AccountManagementApi,

    /**
    * The AuthenticationApi service constructor.
    * @property {module:api/AuthenticationApi}
    */
    AuthenticationApi,

    /**
    * The InternalApi service constructor.
    * @property {module:api/InternalApi}
    */
    InternalApi,

    /**
    * The MarketDataApi service constructor.
    * @property {module:api/MarketDataApi}
    */
    MarketDataApi,

    /**
    * The PrivateApi service constructor.
    * @property {module:api/PrivateApi}
    */
    PrivateApi,

    /**
    * The PublicApi service constructor.
    * @property {module:api/PublicApi}
    */
    PublicApi,

    /**
    * The SupportingApi service constructor.
    * @property {module:api/SupportingApi}
    */
    SupportingApi,

    /**
    * The TradingApi service constructor.
    * @property {module:api/TradingApi}
    */
    TradingApi,

    /**
    * The WalletApi service constructor.
    * @property {module:api/WalletApi}
    */
    WalletApi
};
