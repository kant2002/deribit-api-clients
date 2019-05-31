# PrivateAPI

All URIs are relative to *https://www.deribit.com/api/v2*

Method | HTTP request | Description
------------- | ------------- | -------------
[**privateAddToAddressBookGet**](PrivateAPI.md#privateaddtoaddressbookget) | **GET** /private/add_to_address_book | Adds new entry to address book of given type
[**privateBuyGet**](PrivateAPI.md#privatebuyget) | **GET** /private/buy | Places a buy order for an instrument.
[**privateCancelAllByCurrencyGet**](PrivateAPI.md#privatecancelallbycurrencyget) | **GET** /private/cancel_all_by_currency | Cancels all orders by currency, optionally filtered by instrument kind and/or order type.
[**privateCancelAllByInstrumentGet**](PrivateAPI.md#privatecancelallbyinstrumentget) | **GET** /private/cancel_all_by_instrument | Cancels all orders by instrument, optionally filtered by order type.
[**privateCancelAllGet**](PrivateAPI.md#privatecancelallget) | **GET** /private/cancel_all | This method cancels all users orders and stop orders within all currencies and instrument kinds.
[**privateCancelGet**](PrivateAPI.md#privatecancelget) | **GET** /private/cancel | Cancel an order, specified by order id
[**privateCancelTransferByIdGet**](PrivateAPI.md#privatecanceltransferbyidget) | **GET** /private/cancel_transfer_by_id | Cancel transfer
[**privateCancelWithdrawalGet**](PrivateAPI.md#privatecancelwithdrawalget) | **GET** /private/cancel_withdrawal | Cancels withdrawal request
[**privateChangeSubaccountNameGet**](PrivateAPI.md#privatechangesubaccountnameget) | **GET** /private/change_subaccount_name | Change the user name for a subaccount
[**privateClosePositionGet**](PrivateAPI.md#privateclosepositionget) | **GET** /private/close_position | Makes closing position reduce only order .
[**privateCreateDepositAddressGet**](PrivateAPI.md#privatecreatedepositaddressget) | **GET** /private/create_deposit_address | Creates deposit address in currency
[**privateCreateSubaccountGet**](PrivateAPI.md#privatecreatesubaccountget) | **GET** /private/create_subaccount | Create a new subaccount
[**privateDisableTfaForSubaccountGet**](PrivateAPI.md#privatedisabletfaforsubaccountget) | **GET** /private/disable_tfa_for_subaccount | Disable two factor authentication for a subaccount.
[**privateDisableTfaWithRecoveryCodeGet**](PrivateAPI.md#privatedisabletfawithrecoverycodeget) | **GET** /private/disable_tfa_with_recovery_code | Disables TFA with one time recovery code
[**privateEditGet**](PrivateAPI.md#privateeditget) | **GET** /private/edit | Change price, amount and/or other properties of an order.
[**privateGetAccountSummaryGet**](PrivateAPI.md#privategetaccountsummaryget) | **GET** /private/get_account_summary | Retrieves user account summary.
[**privateGetAddressBookGet**](PrivateAPI.md#privategetaddressbookget) | **GET** /private/get_address_book | Retrieves address book of given type
[**privateGetCurrentDepositAddressGet**](PrivateAPI.md#privategetcurrentdepositaddressget) | **GET** /private/get_current_deposit_address | Retrieve deposit address for currency
[**privateGetDepositsGet**](PrivateAPI.md#privategetdepositsget) | **GET** /private/get_deposits | Retrieve the latest users deposits
[**privateGetEmailLanguageGet**](PrivateAPI.md#privategetemaillanguageget) | **GET** /private/get_email_language | Retrieves the language to be used for emails.
[**privateGetMarginsGet**](PrivateAPI.md#privategetmarginsget) | **GET** /private/get_margins | Get margins for given instrument, amount and price.
[**privateGetNewAnnouncementsGet**](PrivateAPI.md#privategetnewannouncementsget) | **GET** /private/get_new_announcements | Retrieves announcements that have not been marked read by the user.
[**privateGetOpenOrdersByCurrencyGet**](PrivateAPI.md#privategetopenordersbycurrencyget) | **GET** /private/get_open_orders_by_currency | Retrieves list of user&#39;s open orders.
[**privateGetOpenOrdersByInstrumentGet**](PrivateAPI.md#privategetopenordersbyinstrumentget) | **GET** /private/get_open_orders_by_instrument | Retrieves list of user&#39;s open orders within given Instrument.
[**privateGetOrderHistoryByCurrencyGet**](PrivateAPI.md#privategetorderhistorybycurrencyget) | **GET** /private/get_order_history_by_currency | Retrieves history of orders that have been partially or fully filled.
[**privateGetOrderHistoryByInstrumentGet**](PrivateAPI.md#privategetorderhistorybyinstrumentget) | **GET** /private/get_order_history_by_instrument | Retrieves history of orders that have been partially or fully filled.
[**privateGetOrderMarginByIdsGet**](PrivateAPI.md#privategetordermarginbyidsget) | **GET** /private/get_order_margin_by_ids | Retrieves initial margins of given orders
[**privateGetOrderStateGet**](PrivateAPI.md#privategetorderstateget) | **GET** /private/get_order_state | Retrieve the current state of an order.
[**privateGetPositionGet**](PrivateAPI.md#privategetpositionget) | **GET** /private/get_position | Retrieve user position.
[**privateGetPositionsGet**](PrivateAPI.md#privategetpositionsget) | **GET** /private/get_positions | Retrieve user positions.
[**privateGetSettlementHistoryByCurrencyGet**](PrivateAPI.md#privategetsettlementhistorybycurrencyget) | **GET** /private/get_settlement_history_by_currency | Retrieves settlement, delivery and bankruptcy events that have affected your account.
[**privateGetSettlementHistoryByInstrumentGet**](PrivateAPI.md#privategetsettlementhistorybyinstrumentget) | **GET** /private/get_settlement_history_by_instrument | Retrieves public settlement, delivery and bankruptcy events filtered by instrument name
[**privateGetSubaccountsGet**](PrivateAPI.md#privategetsubaccountsget) | **GET** /private/get_subaccounts | Get information about subaccounts
[**privateGetTransfersGet**](PrivateAPI.md#privategettransfersget) | **GET** /private/get_transfers | Adds new entry to address book of given type
[**privateGetUserTradesByCurrencyAndTimeGet**](PrivateAPI.md#privategetusertradesbycurrencyandtimeget) | **GET** /private/get_user_trades_by_currency_and_time | Retrieve the latest user trades that have occurred for instruments in a specific currency symbol and within given time range.
[**privateGetUserTradesByCurrencyGet**](PrivateAPI.md#privategetusertradesbycurrencyget) | **GET** /private/get_user_trades_by_currency | Retrieve the latest user trades that have occurred for instruments in a specific currency symbol.
[**privateGetUserTradesByInstrumentAndTimeGet**](PrivateAPI.md#privategetusertradesbyinstrumentandtimeget) | **GET** /private/get_user_trades_by_instrument_and_time | Retrieve the latest user trades that have occurred for a specific instrument and within given time range.
[**privateGetUserTradesByInstrumentGet**](PrivateAPI.md#privategetusertradesbyinstrumentget) | **GET** /private/get_user_trades_by_instrument | Retrieve the latest user trades that have occurred for a specific instrument.
[**privateGetUserTradesByOrderGet**](PrivateAPI.md#privategetusertradesbyorderget) | **GET** /private/get_user_trades_by_order | Retrieve the list of user trades that was created for given order
[**privateGetWithdrawalsGet**](PrivateAPI.md#privategetwithdrawalsget) | **GET** /private/get_withdrawals | Retrieve the latest users withdrawals
[**privateRemoveFromAddressBookGet**](PrivateAPI.md#privateremovefromaddressbookget) | **GET** /private/remove_from_address_book | Adds new entry to address book of given type
[**privateSellGet**](PrivateAPI.md#privatesellget) | **GET** /private/sell | Places a sell order for an instrument.
[**privateSetAnnouncementAsReadGet**](PrivateAPI.md#privatesetannouncementasreadget) | **GET** /private/set_announcement_as_read | Marks an announcement as read, so it will not be shown in &#x60;get_new_announcements&#x60;.
[**privateSetEmailForSubaccountGet**](PrivateAPI.md#privatesetemailforsubaccountget) | **GET** /private/set_email_for_subaccount | Assign an email address to a subaccount. User will receive an email with confirmation link.
[**privateSetEmailLanguageGet**](PrivateAPI.md#privatesetemaillanguageget) | **GET** /private/set_email_language | Changes the language to be used for emails.
[**privateSetPasswordForSubaccountGet**](PrivateAPI.md#privatesetpasswordforsubaccountget) | **GET** /private/set_password_for_subaccount | Set the password for the subaccount
[**privateSubmitTransferToSubaccountGet**](PrivateAPI.md#privatesubmittransfertosubaccountget) | **GET** /private/submit_transfer_to_subaccount | Transfer funds to a subaccount.
[**privateSubmitTransferToUserGet**](PrivateAPI.md#privatesubmittransfertouserget) | **GET** /private/submit_transfer_to_user | Transfer funds to a another user.
[**privateToggleDepositAddressCreationGet**](PrivateAPI.md#privatetoggledepositaddresscreationget) | **GET** /private/toggle_deposit_address_creation | Enable or disable deposit address creation
[**privateToggleNotificationsFromSubaccountGet**](PrivateAPI.md#privatetogglenotificationsfromsubaccountget) | **GET** /private/toggle_notifications_from_subaccount | Enable or disable sending of notifications for the subaccount.
[**privateToggleSubaccountLoginGet**](PrivateAPI.md#privatetogglesubaccountloginget) | **GET** /private/toggle_subaccount_login | Enable or disable login for a subaccount. If login is disabled and a session for the subaccount exists, this session will be terminated.
[**privateWithdrawGet**](PrivateAPI.md#privatewithdrawget) | **GET** /private/withdraw | Creates a new withdrawal request


# **privateAddToAddressBookGet**
```swift
    open class func privateAddToAddressBookGet(currency: Currency_privateAddToAddressBookGet, type: ModelType_privateAddToAddressBookGet, address: String, name: String, tfa: String? = nil, completion: @escaping (_ data: Any?, _ error: Error?) -> Void)
```

Adds new entry to address book of given type

### Example 
```swift
// The following code samples are still beta. For any issue, please report via http://github.com/OpenAPITools/openapi-generator/issues/new
import OpenAPIClient

let currency = "currency_example" // String | The currency symbol
let type = "type_example" // String | Address book type
let address = "address_example" // String | Address in currency format, it must be in address book
let name = "name_example" // String | Name of address book entry
let tfa = "tfa_example" // String | TFA code, required when TFA is enabled for current account (optional)

// Adds new entry to address book of given type
PrivateAPI.privateAddToAddressBookGet(currency: currency, type: type, address: address, name: name, tfa: tfa) { (response, error) in
    guard error == nil else {
        print(error)
        return
    }

    if (response) {
        dump(response)
    }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **String** | The currency symbol | 
 **type** | **String** | Address book type | 
 **address** | **String** | Address in currency format, it must be in address book | 
 **name** | **String** | Name of address book entry | 
 **tfa** | **String** | TFA code, required when TFA is enabled for current account | [optional] 

### Return type

**Any**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateBuyGet**
```swift
    open class func privateBuyGet(instrumentName: String, amount: Double, type: ModelType_privateBuyGet? = nil, label: String? = nil, price: Double? = nil, timeInForce: TimeInForce_privateBuyGet? = nil, maxShow: Double? = nil, postOnly: Bool? = nil, reduceOnly: Bool? = nil, stopPrice: Double? = nil, trigger: Trigger_privateBuyGet? = nil, advanced: Advanced_privateBuyGet? = nil, completion: @escaping (_ data: Any?, _ error: Error?) -> Void)
```

Places a buy order for an instrument.

### Example 
```swift
// The following code samples are still beta. For any issue, please report via http://github.com/OpenAPITools/openapi-generator/issues/new
import OpenAPIClient

let instrumentName = "instrumentName_example" // String | Instrument name
let amount = 987 // Double | It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH
let type = "type_example" // String | The order type, default: `\"limit\"` (optional)
let label = "label_example" // String | user defined label for the order (maximum 32 characters) (optional)
let price = 987 // Double | <p>The order price in base currency (Only for limit and stop_limit orders)</p> <p>When adding order with advanced=usd, the field price should be the option price value in USD.</p> <p>When adding order with advanced=implv, the field price should be a value of implied volatility in percentages. For example,  price=100, means implied volatility of 100%</p> (optional)
let timeInForce = "timeInForce_example" // String | <p>Specifies how long the order remains in effect. Default `\"good_til_cancelled\"`</p> <ul> <li>`\"good_til_cancelled\"` - unfilled order remains in order book until cancelled</li> <li>`\"fill_or_kill\"` - execute a transaction immediately and completely or not at all</li> <li>`\"immediate_or_cancel\"` - execute a transaction immediately, and any portion of the order that cannot be immediately filled is cancelled</li> </ul> (optional) (default to .good_til_cancelled)
let maxShow = 987 // Double | Maximum amount within an order to be shown to other customers, `0` for invisible order (optional) (default to 1)
let postOnly = false // Bool | <p>If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.</p> <p>Only valid in combination with time_in_force=`\"good_til_cancelled\"`</p> (optional) (default to true)
let reduceOnly = false // Bool | If `true`, the order is considered reduce-only which is intended to only reduce a current position (optional) (default to false)
let stopPrice = 987 // Double | Stop price, required for stop limit orders (Only for stop orders) (optional)
let trigger = "trigger_example" // String | Defines trigger type, required for `\"stop_limit\"` order type (optional)
let advanced = "advanced_example" // String | Advanced option order type. (Only for options) (optional)

// Places a buy order for an instrument.
PrivateAPI.privateBuyGet(instrumentName: instrumentName, amount: amount, type: type, label: label, price: price, timeInForce: timeInForce, maxShow: maxShow, postOnly: postOnly, reduceOnly: reduceOnly, stopPrice: stopPrice, trigger: trigger, advanced: advanced) { (response, error) in
    guard error == nil else {
        print(error)
        return
    }

    if (response) {
        dump(response)
    }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrumentName** | **String** | Instrument name | 
 **amount** | **Double** | It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH | 
 **type** | **String** | The order type, default: &#x60;\&quot;limit\&quot;&#x60; | [optional] 
 **label** | **String** | user defined label for the order (maximum 32 characters) | [optional] 
 **price** | **Double** | &lt;p&gt;The order price in base currency (Only for limit and stop_limit orders)&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;usd, the field price should be the option price value in USD.&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;implv, the field price should be a value of implied volatility in percentages. For example,  price&#x3D;100, means implied volatility of 100%&lt;/p&gt; | [optional] 
 **timeInForce** | **String** | &lt;p&gt;Specifies how long the order remains in effect. Default &#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; &lt;ul&gt; &lt;li&gt;&#x60;\&quot;good_til_cancelled\&quot;&#x60; - unfilled order remains in order book until cancelled&lt;/li&gt; &lt;li&gt;&#x60;\&quot;fill_or_kill\&quot;&#x60; - execute a transaction immediately and completely or not at all&lt;/li&gt; &lt;li&gt;&#x60;\&quot;immediate_or_cancel\&quot;&#x60; - execute a transaction immediately, and any portion of the order that cannot be immediately filled is cancelled&lt;/li&gt; &lt;/ul&gt; | [optional] [default to .good_til_cancelled]
 **maxShow** | **Double** | Maximum amount within an order to be shown to other customers, &#x60;0&#x60; for invisible order | [optional] [default to 1]
 **postOnly** | **Bool** | &lt;p&gt;If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.&lt;/p&gt; &lt;p&gt;Only valid in combination with time_in_force&#x3D;&#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; | [optional] [default to true]
 **reduceOnly** | **Bool** | If &#x60;true&#x60;, the order is considered reduce-only which is intended to only reduce a current position | [optional] [default to false]
 **stopPrice** | **Double** | Stop price, required for stop limit orders (Only for stop orders) | [optional] 
 **trigger** | **String** | Defines trigger type, required for &#x60;\&quot;stop_limit\&quot;&#x60; order type | [optional] 
 **advanced** | **String** | Advanced option order type. (Only for options) | [optional] 

### Return type

**Any**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateCancelAllByCurrencyGet**
```swift
    open class func privateCancelAllByCurrencyGet(currency: Currency_privateCancelAllByCurrencyGet, kind: Kind_privateCancelAllByCurrencyGet? = nil, type: ModelType_privateCancelAllByCurrencyGet? = nil, completion: @escaping (_ data: Any?, _ error: Error?) -> Void)
```

Cancels all orders by currency, optionally filtered by instrument kind and/or order type.

### Example 
```swift
// The following code samples are still beta. For any issue, please report via http://github.com/OpenAPITools/openapi-generator/issues/new
import OpenAPIClient

let currency = "currency_example" // String | The currency symbol
let kind = "kind_example" // String | Instrument kind, if not provided instruments of all kinds are considered (optional)
let type = "type_example" // String | Order type - limit, stop or all, default - `all` (optional)

// Cancels all orders by currency, optionally filtered by instrument kind and/or order type.
PrivateAPI.privateCancelAllByCurrencyGet(currency: currency, kind: kind, type: type) { (response, error) in
    guard error == nil else {
        print(error)
        return
    }

    if (response) {
        dump(response)
    }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **String** | The currency symbol | 
 **kind** | **String** | Instrument kind, if not provided instruments of all kinds are considered | [optional] 
 **type** | **String** | Order type - limit, stop or all, default - &#x60;all&#x60; | [optional] 

### Return type

**Any**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateCancelAllByInstrumentGet**
```swift
    open class func privateCancelAllByInstrumentGet(instrumentName: String, type: ModelType_privateCancelAllByInstrumentGet? = nil, completion: @escaping (_ data: Any?, _ error: Error?) -> Void)
```

Cancels all orders by instrument, optionally filtered by order type.

### Example 
```swift
// The following code samples are still beta. For any issue, please report via http://github.com/OpenAPITools/openapi-generator/issues/new
import OpenAPIClient

let instrumentName = "instrumentName_example" // String | Instrument name
let type = "type_example" // String | Order type - limit, stop or all, default - `all` (optional)

// Cancels all orders by instrument, optionally filtered by order type.
PrivateAPI.privateCancelAllByInstrumentGet(instrumentName: instrumentName, type: type) { (response, error) in
    guard error == nil else {
        print(error)
        return
    }

    if (response) {
        dump(response)
    }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrumentName** | **String** | Instrument name | 
 **type** | **String** | Order type - limit, stop or all, default - &#x60;all&#x60; | [optional] 

### Return type

**Any**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateCancelAllGet**
```swift
    open class func privateCancelAllGet(completion: @escaping (_ data: Any?, _ error: Error?) -> Void)
```

This method cancels all users orders and stop orders within all currencies and instrument kinds.

### Example 
```swift
// The following code samples are still beta. For any issue, please report via http://github.com/OpenAPITools/openapi-generator/issues/new
import OpenAPIClient


// This method cancels all users orders and stop orders within all currencies and instrument kinds.
PrivateAPI.privateCancelAllGet() { (response, error) in
    guard error == nil else {
        print(error)
        return
    }

    if (response) {
        dump(response)
    }
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

**Any**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateCancelGet**
```swift
    open class func privateCancelGet(orderId: String, completion: @escaping (_ data: Any?, _ error: Error?) -> Void)
```

Cancel an order, specified by order id

### Example 
```swift
// The following code samples are still beta. For any issue, please report via http://github.com/OpenAPITools/openapi-generator/issues/new
import OpenAPIClient

let orderId = "orderId_example" // String | The order id

// Cancel an order, specified by order id
PrivateAPI.privateCancelGet(orderId: orderId) { (response, error) in
    guard error == nil else {
        print(error)
        return
    }

    if (response) {
        dump(response)
    }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **orderId** | **String** | The order id | 

### Return type

**Any**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateCancelTransferByIdGet**
```swift
    open class func privateCancelTransferByIdGet(currency: Currency_privateCancelTransferByIdGet, id: Int, tfa: String? = nil, completion: @escaping (_ data: Any?, _ error: Error?) -> Void)
```

Cancel transfer

### Example 
```swift
// The following code samples are still beta. For any issue, please report via http://github.com/OpenAPITools/openapi-generator/issues/new
import OpenAPIClient

let currency = "currency_example" // String | The currency symbol
let id = 987 // Int | Id of transfer
let tfa = "tfa_example" // String | TFA code, required when TFA is enabled for current account (optional)

// Cancel transfer
PrivateAPI.privateCancelTransferByIdGet(currency: currency, id: id, tfa: tfa) { (response, error) in
    guard error == nil else {
        print(error)
        return
    }

    if (response) {
        dump(response)
    }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **String** | The currency symbol | 
 **id** | **Int** | Id of transfer | 
 **tfa** | **String** | TFA code, required when TFA is enabled for current account | [optional] 

### Return type

**Any**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateCancelWithdrawalGet**
```swift
    open class func privateCancelWithdrawalGet(currency: Currency_privateCancelWithdrawalGet, id: Double, completion: @escaping (_ data: Any?, _ error: Error?) -> Void)
```

Cancels withdrawal request

### Example 
```swift
// The following code samples are still beta. For any issue, please report via http://github.com/OpenAPITools/openapi-generator/issues/new
import OpenAPIClient

let currency = "currency_example" // String | The currency symbol
let id = 987 // Double | The withdrawal id

// Cancels withdrawal request
PrivateAPI.privateCancelWithdrawalGet(currency: currency, id: id) { (response, error) in
    guard error == nil else {
        print(error)
        return
    }

    if (response) {
        dump(response)
    }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **String** | The currency symbol | 
 **id** | **Double** | The withdrawal id | 

### Return type

**Any**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateChangeSubaccountNameGet**
```swift
    open class func privateChangeSubaccountNameGet(sid: Int, name: String, completion: @escaping (_ data: Any?, _ error: Error?) -> Void)
```

Change the user name for a subaccount

### Example 
```swift
// The following code samples are still beta. For any issue, please report via http://github.com/OpenAPITools/openapi-generator/issues/new
import OpenAPIClient

let sid = 987 // Int | The user id for the subaccount
let name = "name_example" // String | The new user name

// Change the user name for a subaccount
PrivateAPI.privateChangeSubaccountNameGet(sid: sid, name: name) { (response, error) in
    guard error == nil else {
        print(error)
        return
    }

    if (response) {
        dump(response)
    }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **sid** | **Int** | The user id for the subaccount | 
 **name** | **String** | The new user name | 

### Return type

**Any**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateClosePositionGet**
```swift
    open class func privateClosePositionGet(instrumentName: String, type: ModelType_privateClosePositionGet, price: Double? = nil, completion: @escaping (_ data: Any?, _ error: Error?) -> Void)
```

Makes closing position reduce only order .

### Example 
```swift
// The following code samples are still beta. For any issue, please report via http://github.com/OpenAPITools/openapi-generator/issues/new
import OpenAPIClient

let instrumentName = "instrumentName_example" // String | Instrument name
let type = "type_example" // String | The order type
let price = 987 // Double | Optional price for limit order. (optional)

// Makes closing position reduce only order .
PrivateAPI.privateClosePositionGet(instrumentName: instrumentName, type: type, price: price) { (response, error) in
    guard error == nil else {
        print(error)
        return
    }

    if (response) {
        dump(response)
    }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrumentName** | **String** | Instrument name | 
 **type** | **String** | The order type | 
 **price** | **Double** | Optional price for limit order. | [optional] 

### Return type

**Any**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateCreateDepositAddressGet**
```swift
    open class func privateCreateDepositAddressGet(currency: Currency_privateCreateDepositAddressGet, completion: @escaping (_ data: Any?, _ error: Error?) -> Void)
```

Creates deposit address in currency

### Example 
```swift
// The following code samples are still beta. For any issue, please report via http://github.com/OpenAPITools/openapi-generator/issues/new
import OpenAPIClient

let currency = "currency_example" // String | The currency symbol

// Creates deposit address in currency
PrivateAPI.privateCreateDepositAddressGet(currency: currency) { (response, error) in
    guard error == nil else {
        print(error)
        return
    }

    if (response) {
        dump(response)
    }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **String** | The currency symbol | 

### Return type

**Any**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateCreateSubaccountGet**
```swift
    open class func privateCreateSubaccountGet(completion: @escaping (_ data: Any?, _ error: Error?) -> Void)
```

Create a new subaccount

### Example 
```swift
// The following code samples are still beta. For any issue, please report via http://github.com/OpenAPITools/openapi-generator/issues/new
import OpenAPIClient


// Create a new subaccount
PrivateAPI.privateCreateSubaccountGet() { (response, error) in
    guard error == nil else {
        print(error)
        return
    }

    if (response) {
        dump(response)
    }
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

**Any**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateDisableTfaForSubaccountGet**
```swift
    open class func privateDisableTfaForSubaccountGet(sid: Int, completion: @escaping (_ data: Any?, _ error: Error?) -> Void)
```

Disable two factor authentication for a subaccount.

### Example 
```swift
// The following code samples are still beta. For any issue, please report via http://github.com/OpenAPITools/openapi-generator/issues/new
import OpenAPIClient

let sid = 987 // Int | The user id for the subaccount

// Disable two factor authentication for a subaccount.
PrivateAPI.privateDisableTfaForSubaccountGet(sid: sid) { (response, error) in
    guard error == nil else {
        print(error)
        return
    }

    if (response) {
        dump(response)
    }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **sid** | **Int** | The user id for the subaccount | 

### Return type

**Any**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateDisableTfaWithRecoveryCodeGet**
```swift
    open class func privateDisableTfaWithRecoveryCodeGet(password: String, code: String, completion: @escaping (_ data: Any?, _ error: Error?) -> Void)
```

Disables TFA with one time recovery code

### Example 
```swift
// The following code samples are still beta. For any issue, please report via http://github.com/OpenAPITools/openapi-generator/issues/new
import OpenAPIClient

let password = "password_example" // String | The password for the subaccount
let code = "code_example" // String | One time recovery code

// Disables TFA with one time recovery code
PrivateAPI.privateDisableTfaWithRecoveryCodeGet(password: password, code: code) { (response, error) in
    guard error == nil else {
        print(error)
        return
    }

    if (response) {
        dump(response)
    }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **password** | **String** | The password for the subaccount | 
 **code** | **String** | One time recovery code | 

### Return type

**Any**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateEditGet**
```swift
    open class func privateEditGet(orderId: String, amount: Double, price: Double, postOnly: Bool? = nil, advanced: Advanced_privateEditGet? = nil, stopPrice: Double? = nil, completion: @escaping (_ data: Any?, _ error: Error?) -> Void)
```

Change price, amount and/or other properties of an order.

### Example 
```swift
// The following code samples are still beta. For any issue, please report via http://github.com/OpenAPITools/openapi-generator/issues/new
import OpenAPIClient

let orderId = "orderId_example" // String | The order id
let amount = 987 // Double | It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH
let price = 987 // Double | <p>The order price in base currency.</p> <p>When editing an option order with advanced=usd, the field price should be the option price value in USD.</p> <p>When editing an option order with advanced=implv, the field price should be a value of implied volatility in percentages. For example,  price=100, means implied volatility of 100%</p>
let postOnly = false // Bool | <p>If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.</p> <p>Only valid in combination with time_in_force=`\"good_til_cancelled\"`</p> (optional) (default to true)
let advanced = "advanced_example" // String | Advanced option order type. If you have posted an advanced option order, it is necessary to re-supply this parameter when editing it (Only for options) (optional)
let stopPrice = 987 // Double | Stop price, required for stop limit orders (Only for stop orders) (optional)

// Change price, amount and/or other properties of an order.
PrivateAPI.privateEditGet(orderId: orderId, amount: amount, price: price, postOnly: postOnly, advanced: advanced, stopPrice: stopPrice) { (response, error) in
    guard error == nil else {
        print(error)
        return
    }

    if (response) {
        dump(response)
    }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **orderId** | **String** | The order id | 
 **amount** | **Double** | It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH | 
 **price** | **Double** | &lt;p&gt;The order price in base currency.&lt;/p&gt; &lt;p&gt;When editing an option order with advanced&#x3D;usd, the field price should be the option price value in USD.&lt;/p&gt; &lt;p&gt;When editing an option order with advanced&#x3D;implv, the field price should be a value of implied volatility in percentages. For example,  price&#x3D;100, means implied volatility of 100%&lt;/p&gt; | 
 **postOnly** | **Bool** | &lt;p&gt;If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.&lt;/p&gt; &lt;p&gt;Only valid in combination with time_in_force&#x3D;&#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; | [optional] [default to true]
 **advanced** | **String** | Advanced option order type. If you have posted an advanced option order, it is necessary to re-supply this parameter when editing it (Only for options) | [optional] 
 **stopPrice** | **Double** | Stop price, required for stop limit orders (Only for stop orders) | [optional] 

### Return type

**Any**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateGetAccountSummaryGet**
```swift
    open class func privateGetAccountSummaryGet(currency: Currency_privateGetAccountSummaryGet, extended: Bool? = nil, completion: @escaping (_ data: Any?, _ error: Error?) -> Void)
```

Retrieves user account summary.

### Example 
```swift
// The following code samples are still beta. For any issue, please report via http://github.com/OpenAPITools/openapi-generator/issues/new
import OpenAPIClient

let currency = "currency_example" // String | The currency symbol
let extended = false // Bool | Include additional fields (optional)

// Retrieves user account summary.
PrivateAPI.privateGetAccountSummaryGet(currency: currency, extended: extended) { (response, error) in
    guard error == nil else {
        print(error)
        return
    }

    if (response) {
        dump(response)
    }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **String** | The currency symbol | 
 **extended** | **Bool** | Include additional fields | [optional] 

### Return type

**Any**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateGetAddressBookGet**
```swift
    open class func privateGetAddressBookGet(currency: Currency_privateGetAddressBookGet, type: ModelType_privateGetAddressBookGet, completion: @escaping (_ data: Any?, _ error: Error?) -> Void)
```

Retrieves address book of given type

### Example 
```swift
// The following code samples are still beta. For any issue, please report via http://github.com/OpenAPITools/openapi-generator/issues/new
import OpenAPIClient

let currency = "currency_example" // String | The currency symbol
let type = "type_example" // String | Address book type

// Retrieves address book of given type
PrivateAPI.privateGetAddressBookGet(currency: currency, type: type) { (response, error) in
    guard error == nil else {
        print(error)
        return
    }

    if (response) {
        dump(response)
    }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **String** | The currency symbol | 
 **type** | **String** | Address book type | 

### Return type

**Any**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateGetCurrentDepositAddressGet**
```swift
    open class func privateGetCurrentDepositAddressGet(currency: Currency_privateGetCurrentDepositAddressGet, completion: @escaping (_ data: Any?, _ error: Error?) -> Void)
```

Retrieve deposit address for currency

### Example 
```swift
// The following code samples are still beta. For any issue, please report via http://github.com/OpenAPITools/openapi-generator/issues/new
import OpenAPIClient

let currency = "currency_example" // String | The currency symbol

// Retrieve deposit address for currency
PrivateAPI.privateGetCurrentDepositAddressGet(currency: currency) { (response, error) in
    guard error == nil else {
        print(error)
        return
    }

    if (response) {
        dump(response)
    }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **String** | The currency symbol | 

### Return type

**Any**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateGetDepositsGet**
```swift
    open class func privateGetDepositsGet(currency: Currency_privateGetDepositsGet, count: Int? = nil, offset: Int? = nil, completion: @escaping (_ data: Any?, _ error: Error?) -> Void)
```

Retrieve the latest users deposits

### Example 
```swift
// The following code samples are still beta. For any issue, please report via http://github.com/OpenAPITools/openapi-generator/issues/new
import OpenAPIClient

let currency = "currency_example" // String | The currency symbol
let count = 987 // Int | Number of requested items, default - `10` (optional)
let offset = 987 // Int | The offset for pagination, default - `0` (optional)

// Retrieve the latest users deposits
PrivateAPI.privateGetDepositsGet(currency: currency, count: count, offset: offset) { (response, error) in
    guard error == nil else {
        print(error)
        return
    }

    if (response) {
        dump(response)
    }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **String** | The currency symbol | 
 **count** | **Int** | Number of requested items, default - &#x60;10&#x60; | [optional] 
 **offset** | **Int** | The offset for pagination, default - &#x60;0&#x60; | [optional] 

### Return type

**Any**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateGetEmailLanguageGet**
```swift
    open class func privateGetEmailLanguageGet(completion: @escaping (_ data: Any?, _ error: Error?) -> Void)
```

Retrieves the language to be used for emails.

### Example 
```swift
// The following code samples are still beta. For any issue, please report via http://github.com/OpenAPITools/openapi-generator/issues/new
import OpenAPIClient


// Retrieves the language to be used for emails.
PrivateAPI.privateGetEmailLanguageGet() { (response, error) in
    guard error == nil else {
        print(error)
        return
    }

    if (response) {
        dump(response)
    }
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

**Any**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateGetMarginsGet**
```swift
    open class func privateGetMarginsGet(instrumentName: String, amount: Double, price: Double, completion: @escaping (_ data: Any?, _ error: Error?) -> Void)
```

Get margins for given instrument, amount and price.

### Example 
```swift
// The following code samples are still beta. For any issue, please report via http://github.com/OpenAPITools/openapi-generator/issues/new
import OpenAPIClient

let instrumentName = "instrumentName_example" // String | Instrument name
let amount = 987 // Double | Amount, integer for future, float for option. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH.
let price = 987 // Double | Price

// Get margins for given instrument, amount and price.
PrivateAPI.privateGetMarginsGet(instrumentName: instrumentName, amount: amount, price: price) { (response, error) in
    guard error == nil else {
        print(error)
        return
    }

    if (response) {
        dump(response)
    }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrumentName** | **String** | Instrument name | 
 **amount** | **Double** | Amount, integer for future, float for option. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH. | 
 **price** | **Double** | Price | 

### Return type

**Any**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateGetNewAnnouncementsGet**
```swift
    open class func privateGetNewAnnouncementsGet(completion: @escaping (_ data: Any?, _ error: Error?) -> Void)
```

Retrieves announcements that have not been marked read by the user.

### Example 
```swift
// The following code samples are still beta. For any issue, please report via http://github.com/OpenAPITools/openapi-generator/issues/new
import OpenAPIClient


// Retrieves announcements that have not been marked read by the user.
PrivateAPI.privateGetNewAnnouncementsGet() { (response, error) in
    guard error == nil else {
        print(error)
        return
    }

    if (response) {
        dump(response)
    }
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

**Any**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateGetOpenOrdersByCurrencyGet**
```swift
    open class func privateGetOpenOrdersByCurrencyGet(currency: Currency_privateGetOpenOrdersByCurrencyGet, kind: Kind_privateGetOpenOrdersByCurrencyGet? = nil, type: ModelType_privateGetOpenOrdersByCurrencyGet? = nil, completion: @escaping (_ data: Any?, _ error: Error?) -> Void)
```

Retrieves list of user's open orders.

### Example 
```swift
// The following code samples are still beta. For any issue, please report via http://github.com/OpenAPITools/openapi-generator/issues/new
import OpenAPIClient

let currency = "currency_example" // String | The currency symbol
let kind = "kind_example" // String | Instrument kind, if not provided instruments of all kinds are considered (optional)
let type = "type_example" // String | Order type, default - `all` (optional)

// Retrieves list of user's open orders.
PrivateAPI.privateGetOpenOrdersByCurrencyGet(currency: currency, kind: kind, type: type) { (response, error) in
    guard error == nil else {
        print(error)
        return
    }

    if (response) {
        dump(response)
    }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **String** | The currency symbol | 
 **kind** | **String** | Instrument kind, if not provided instruments of all kinds are considered | [optional] 
 **type** | **String** | Order type, default - &#x60;all&#x60; | [optional] 

### Return type

**Any**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateGetOpenOrdersByInstrumentGet**
```swift
    open class func privateGetOpenOrdersByInstrumentGet(instrumentName: String, type: ModelType_privateGetOpenOrdersByInstrumentGet? = nil, completion: @escaping (_ data: Any?, _ error: Error?) -> Void)
```

Retrieves list of user's open orders within given Instrument.

### Example 
```swift
// The following code samples are still beta. For any issue, please report via http://github.com/OpenAPITools/openapi-generator/issues/new
import OpenAPIClient

let instrumentName = "instrumentName_example" // String | Instrument name
let type = "type_example" // String | Order type, default - `all` (optional)

// Retrieves list of user's open orders within given Instrument.
PrivateAPI.privateGetOpenOrdersByInstrumentGet(instrumentName: instrumentName, type: type) { (response, error) in
    guard error == nil else {
        print(error)
        return
    }

    if (response) {
        dump(response)
    }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrumentName** | **String** | Instrument name | 
 **type** | **String** | Order type, default - &#x60;all&#x60; | [optional] 

### Return type

**Any**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateGetOrderHistoryByCurrencyGet**
```swift
    open class func privateGetOrderHistoryByCurrencyGet(currency: Currency_privateGetOrderHistoryByCurrencyGet, kind: Kind_privateGetOrderHistoryByCurrencyGet? = nil, count: Int? = nil, offset: Int? = nil, includeOld: Bool? = nil, includeUnfilled: Bool? = nil, completion: @escaping (_ data: Any?, _ error: Error?) -> Void)
```

Retrieves history of orders that have been partially or fully filled.

### Example 
```swift
// The following code samples are still beta. For any issue, please report via http://github.com/OpenAPITools/openapi-generator/issues/new
import OpenAPIClient

let currency = "currency_example" // String | The currency symbol
let kind = "kind_example" // String | Instrument kind, if not provided instruments of all kinds are considered (optional)
let count = 987 // Int | Number of requested items, default - `20` (optional)
let offset = 987 // Int | The offset for pagination, default - `0` (optional)
let includeOld = false // Bool | Include in result orders older than 2 days, default - `false` (optional)
let includeUnfilled = false // Bool | Include in result fully unfilled closed orders, default - `false` (optional)

// Retrieves history of orders that have been partially or fully filled.
PrivateAPI.privateGetOrderHistoryByCurrencyGet(currency: currency, kind: kind, count: count, offset: offset, includeOld: includeOld, includeUnfilled: includeUnfilled) { (response, error) in
    guard error == nil else {
        print(error)
        return
    }

    if (response) {
        dump(response)
    }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **String** | The currency symbol | 
 **kind** | **String** | Instrument kind, if not provided instruments of all kinds are considered | [optional] 
 **count** | **Int** | Number of requested items, default - &#x60;20&#x60; | [optional] 
 **offset** | **Int** | The offset for pagination, default - &#x60;0&#x60; | [optional] 
 **includeOld** | **Bool** | Include in result orders older than 2 days, default - &#x60;false&#x60; | [optional] 
 **includeUnfilled** | **Bool** | Include in result fully unfilled closed orders, default - &#x60;false&#x60; | [optional] 

### Return type

**Any**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateGetOrderHistoryByInstrumentGet**
```swift
    open class func privateGetOrderHistoryByInstrumentGet(instrumentName: String, count: Int? = nil, offset: Int? = nil, includeOld: Bool? = nil, includeUnfilled: Bool? = nil, completion: @escaping (_ data: Any?, _ error: Error?) -> Void)
```

Retrieves history of orders that have been partially or fully filled.

### Example 
```swift
// The following code samples are still beta. For any issue, please report via http://github.com/OpenAPITools/openapi-generator/issues/new
import OpenAPIClient

let instrumentName = "instrumentName_example" // String | Instrument name
let count = 987 // Int | Number of requested items, default - `20` (optional)
let offset = 987 // Int | The offset for pagination, default - `0` (optional)
let includeOld = false // Bool | Include in result orders older than 2 days, default - `false` (optional)
let includeUnfilled = false // Bool | Include in result fully unfilled closed orders, default - `false` (optional)

// Retrieves history of orders that have been partially or fully filled.
PrivateAPI.privateGetOrderHistoryByInstrumentGet(instrumentName: instrumentName, count: count, offset: offset, includeOld: includeOld, includeUnfilled: includeUnfilled) { (response, error) in
    guard error == nil else {
        print(error)
        return
    }

    if (response) {
        dump(response)
    }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrumentName** | **String** | Instrument name | 
 **count** | **Int** | Number of requested items, default - &#x60;20&#x60; | [optional] 
 **offset** | **Int** | The offset for pagination, default - &#x60;0&#x60; | [optional] 
 **includeOld** | **Bool** | Include in result orders older than 2 days, default - &#x60;false&#x60; | [optional] 
 **includeUnfilled** | **Bool** | Include in result fully unfilled closed orders, default - &#x60;false&#x60; | [optional] 

### Return type

**Any**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateGetOrderMarginByIdsGet**
```swift
    open class func privateGetOrderMarginByIdsGet(ids: [String], completion: @escaping (_ data: Any?, _ error: Error?) -> Void)
```

Retrieves initial margins of given orders

### Example 
```swift
// The following code samples are still beta. For any issue, please report via http://github.com/OpenAPITools/openapi-generator/issues/new
import OpenAPIClient

let ids = ["inner_example"] // [String] | Ids of orders

// Retrieves initial margins of given orders
PrivateAPI.privateGetOrderMarginByIdsGet(ids: ids) { (response, error) in
    guard error == nil else {
        print(error)
        return
    }

    if (response) {
        dump(response)
    }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **ids** | [**[String]**](String.md) | Ids of orders | 

### Return type

**Any**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateGetOrderStateGet**
```swift
    open class func privateGetOrderStateGet(orderId: String, completion: @escaping (_ data: Any?, _ error: Error?) -> Void)
```

Retrieve the current state of an order.

### Example 
```swift
// The following code samples are still beta. For any issue, please report via http://github.com/OpenAPITools/openapi-generator/issues/new
import OpenAPIClient

let orderId = "orderId_example" // String | The order id

// Retrieve the current state of an order.
PrivateAPI.privateGetOrderStateGet(orderId: orderId) { (response, error) in
    guard error == nil else {
        print(error)
        return
    }

    if (response) {
        dump(response)
    }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **orderId** | **String** | The order id | 

### Return type

**Any**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateGetPositionGet**
```swift
    open class func privateGetPositionGet(instrumentName: String, completion: @escaping (_ data: Any?, _ error: Error?) -> Void)
```

Retrieve user position.

### Example 
```swift
// The following code samples are still beta. For any issue, please report via http://github.com/OpenAPITools/openapi-generator/issues/new
import OpenAPIClient

let instrumentName = "instrumentName_example" // String | Instrument name

// Retrieve user position.
PrivateAPI.privateGetPositionGet(instrumentName: instrumentName) { (response, error) in
    guard error == nil else {
        print(error)
        return
    }

    if (response) {
        dump(response)
    }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrumentName** | **String** | Instrument name | 

### Return type

**Any**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateGetPositionsGet**
```swift
    open class func privateGetPositionsGet(currency: Currency_privateGetPositionsGet, kind: Kind_privateGetPositionsGet? = nil, completion: @escaping (_ data: Any?, _ error: Error?) -> Void)
```

Retrieve user positions.

### Example 
```swift
// The following code samples are still beta. For any issue, please report via http://github.com/OpenAPITools/openapi-generator/issues/new
import OpenAPIClient

let currency = "currency_example" // String | 
let kind = "kind_example" // String | Kind filter on positions (optional)

// Retrieve user positions.
PrivateAPI.privateGetPositionsGet(currency: currency, kind: kind) { (response, error) in
    guard error == nil else {
        print(error)
        return
    }

    if (response) {
        dump(response)
    }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **String** |  | 
 **kind** | **String** | Kind filter on positions | [optional] 

### Return type

**Any**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateGetSettlementHistoryByCurrencyGet**
```swift
    open class func privateGetSettlementHistoryByCurrencyGet(currency: Currency_privateGetSettlementHistoryByCurrencyGet, type: ModelType_privateGetSettlementHistoryByCurrencyGet? = nil, count: Int? = nil, completion: @escaping (_ data: Any?, _ error: Error?) -> Void)
```

Retrieves settlement, delivery and bankruptcy events that have affected your account.

### Example 
```swift
// The following code samples are still beta. For any issue, please report via http://github.com/OpenAPITools/openapi-generator/issues/new
import OpenAPIClient

let currency = "currency_example" // String | The currency symbol
let type = "type_example" // String | Settlement type (optional)
let count = 987 // Int | Number of requested items, default - `20` (optional)

// Retrieves settlement, delivery and bankruptcy events that have affected your account.
PrivateAPI.privateGetSettlementHistoryByCurrencyGet(currency: currency, type: type, count: count) { (response, error) in
    guard error == nil else {
        print(error)
        return
    }

    if (response) {
        dump(response)
    }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **String** | The currency symbol | 
 **type** | **String** | Settlement type | [optional] 
 **count** | **Int** | Number of requested items, default - &#x60;20&#x60; | [optional] 

### Return type

**Any**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateGetSettlementHistoryByInstrumentGet**
```swift
    open class func privateGetSettlementHistoryByInstrumentGet(instrumentName: String, type: ModelType_privateGetSettlementHistoryByInstrumentGet? = nil, count: Int? = nil, completion: @escaping (_ data: Any?, _ error: Error?) -> Void)
```

Retrieves public settlement, delivery and bankruptcy events filtered by instrument name

### Example 
```swift
// The following code samples are still beta. For any issue, please report via http://github.com/OpenAPITools/openapi-generator/issues/new
import OpenAPIClient

let instrumentName = "instrumentName_example" // String | Instrument name
let type = "type_example" // String | Settlement type (optional)
let count = 987 // Int | Number of requested items, default - `20` (optional)

// Retrieves public settlement, delivery and bankruptcy events filtered by instrument name
PrivateAPI.privateGetSettlementHistoryByInstrumentGet(instrumentName: instrumentName, type: type, count: count) { (response, error) in
    guard error == nil else {
        print(error)
        return
    }

    if (response) {
        dump(response)
    }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrumentName** | **String** | Instrument name | 
 **type** | **String** | Settlement type | [optional] 
 **count** | **Int** | Number of requested items, default - &#x60;20&#x60; | [optional] 

### Return type

**Any**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateGetSubaccountsGet**
```swift
    open class func privateGetSubaccountsGet(withPortfolio: Bool? = nil, completion: @escaping (_ data: Any?, _ error: Error?) -> Void)
```

Get information about subaccounts

### Example 
```swift
// The following code samples are still beta. For any issue, please report via http://github.com/OpenAPITools/openapi-generator/issues/new
import OpenAPIClient

let withPortfolio = false // Bool |  (optional)

// Get information about subaccounts
PrivateAPI.privateGetSubaccountsGet(withPortfolio: withPortfolio) { (response, error) in
    guard error == nil else {
        print(error)
        return
    }

    if (response) {
        dump(response)
    }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **withPortfolio** | **Bool** |  | [optional] 

### Return type

**Any**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateGetTransfersGet**
```swift
    open class func privateGetTransfersGet(currency: Currency_privateGetTransfersGet, count: Int? = nil, offset: Int? = nil, completion: @escaping (_ data: Any?, _ error: Error?) -> Void)
```

Adds new entry to address book of given type

### Example 
```swift
// The following code samples are still beta. For any issue, please report via http://github.com/OpenAPITools/openapi-generator/issues/new
import OpenAPIClient

let currency = "currency_example" // String | The currency symbol
let count = 987 // Int | Number of requested items, default - `10` (optional)
let offset = 987 // Int | The offset for pagination, default - `0` (optional)

// Adds new entry to address book of given type
PrivateAPI.privateGetTransfersGet(currency: currency, count: count, offset: offset) { (response, error) in
    guard error == nil else {
        print(error)
        return
    }

    if (response) {
        dump(response)
    }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **String** | The currency symbol | 
 **count** | **Int** | Number of requested items, default - &#x60;10&#x60; | [optional] 
 **offset** | **Int** | The offset for pagination, default - &#x60;0&#x60; | [optional] 

### Return type

**Any**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateGetUserTradesByCurrencyAndTimeGet**
```swift
    open class func privateGetUserTradesByCurrencyAndTimeGet(currency: Currency_privateGetUserTradesByCurrencyAndTimeGet, startTimestamp: Int, endTimestamp: Int, kind: Kind_privateGetUserTradesByCurrencyAndTimeGet? = nil, count: Int? = nil, includeOld: Bool? = nil, sorting: Sorting_privateGetUserTradesByCurrencyAndTimeGet? = nil, completion: @escaping (_ data: Any?, _ error: Error?) -> Void)
```

Retrieve the latest user trades that have occurred for instruments in a specific currency symbol and within given time range.

### Example 
```swift
// The following code samples are still beta. For any issue, please report via http://github.com/OpenAPITools/openapi-generator/issues/new
import OpenAPIClient

let currency = "currency_example" // String | The currency symbol
let startTimestamp = 987 // Int | The earliest timestamp to return result for
let endTimestamp = 987 // Int | The most recent timestamp to return result for
let kind = "kind_example" // String | Instrument kind, if not provided instruments of all kinds are considered (optional)
let count = 987 // Int | Number of requested items, default - `10` (optional)
let includeOld = false // Bool | Include trades older than 7 days, default - `false` (optional)
let sorting = "sorting_example" // String | Direction of results sorting (`default` value means no sorting, results will be returned in order in which they left the database) (optional)

// Retrieve the latest user trades that have occurred for instruments in a specific currency symbol and within given time range.
PrivateAPI.privateGetUserTradesByCurrencyAndTimeGet(currency: currency, startTimestamp: startTimestamp, endTimestamp: endTimestamp, kind: kind, count: count, includeOld: includeOld, sorting: sorting) { (response, error) in
    guard error == nil else {
        print(error)
        return
    }

    if (response) {
        dump(response)
    }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **String** | The currency symbol | 
 **startTimestamp** | **Int** | The earliest timestamp to return result for | 
 **endTimestamp** | **Int** | The most recent timestamp to return result for | 
 **kind** | **String** | Instrument kind, if not provided instruments of all kinds are considered | [optional] 
 **count** | **Int** | Number of requested items, default - &#x60;10&#x60; | [optional] 
 **includeOld** | **Bool** | Include trades older than 7 days, default - &#x60;false&#x60; | [optional] 
 **sorting** | **String** | Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) | [optional] 

### Return type

**Any**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateGetUserTradesByCurrencyGet**
```swift
    open class func privateGetUserTradesByCurrencyGet(currency: Currency_privateGetUserTradesByCurrencyGet, kind: Kind_privateGetUserTradesByCurrencyGet? = nil, startId: String? = nil, endId: String? = nil, count: Int? = nil, includeOld: Bool? = nil, sorting: Sorting_privateGetUserTradesByCurrencyGet? = nil, completion: @escaping (_ data: Any?, _ error: Error?) -> Void)
```

Retrieve the latest user trades that have occurred for instruments in a specific currency symbol.

### Example 
```swift
// The following code samples are still beta. For any issue, please report via http://github.com/OpenAPITools/openapi-generator/issues/new
import OpenAPIClient

let currency = "currency_example" // String | The currency symbol
let kind = "kind_example" // String | Instrument kind, if not provided instruments of all kinds are considered (optional)
let startId = "startId_example" // String | The ID number of the first trade to be returned (optional)
let endId = "endId_example" // String | The ID number of the last trade to be returned (optional)
let count = 987 // Int | Number of requested items, default - `10` (optional)
let includeOld = false // Bool | Include trades older than 7 days, default - `false` (optional)
let sorting = "sorting_example" // String | Direction of results sorting (`default` value means no sorting, results will be returned in order in which they left the database) (optional)

// Retrieve the latest user trades that have occurred for instruments in a specific currency symbol.
PrivateAPI.privateGetUserTradesByCurrencyGet(currency: currency, kind: kind, startId: startId, endId: endId, count: count, includeOld: includeOld, sorting: sorting) { (response, error) in
    guard error == nil else {
        print(error)
        return
    }

    if (response) {
        dump(response)
    }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **String** | The currency symbol | 
 **kind** | **String** | Instrument kind, if not provided instruments of all kinds are considered | [optional] 
 **startId** | **String** | The ID number of the first trade to be returned | [optional] 
 **endId** | **String** | The ID number of the last trade to be returned | [optional] 
 **count** | **Int** | Number of requested items, default - &#x60;10&#x60; | [optional] 
 **includeOld** | **Bool** | Include trades older than 7 days, default - &#x60;false&#x60; | [optional] 
 **sorting** | **String** | Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) | [optional] 

### Return type

**Any**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateGetUserTradesByInstrumentAndTimeGet**
```swift
    open class func privateGetUserTradesByInstrumentAndTimeGet(instrumentName: String, startTimestamp: Int, endTimestamp: Int, count: Int? = nil, includeOld: Bool? = nil, sorting: Sorting_privateGetUserTradesByInstrumentAndTimeGet? = nil, completion: @escaping (_ data: Any?, _ error: Error?) -> Void)
```

Retrieve the latest user trades that have occurred for a specific instrument and within given time range.

### Example 
```swift
// The following code samples are still beta. For any issue, please report via http://github.com/OpenAPITools/openapi-generator/issues/new
import OpenAPIClient

let instrumentName = "instrumentName_example" // String | Instrument name
let startTimestamp = 987 // Int | The earliest timestamp to return result for
let endTimestamp = 987 // Int | The most recent timestamp to return result for
let count = 987 // Int | Number of requested items, default - `10` (optional)
let includeOld = false // Bool | Include trades older than 7 days, default - `false` (optional)
let sorting = "sorting_example" // String | Direction of results sorting (`default` value means no sorting, results will be returned in order in which they left the database) (optional)

// Retrieve the latest user trades that have occurred for a specific instrument and within given time range.
PrivateAPI.privateGetUserTradesByInstrumentAndTimeGet(instrumentName: instrumentName, startTimestamp: startTimestamp, endTimestamp: endTimestamp, count: count, includeOld: includeOld, sorting: sorting) { (response, error) in
    guard error == nil else {
        print(error)
        return
    }

    if (response) {
        dump(response)
    }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrumentName** | **String** | Instrument name | 
 **startTimestamp** | **Int** | The earliest timestamp to return result for | 
 **endTimestamp** | **Int** | The most recent timestamp to return result for | 
 **count** | **Int** | Number of requested items, default - &#x60;10&#x60; | [optional] 
 **includeOld** | **Bool** | Include trades older than 7 days, default - &#x60;false&#x60; | [optional] 
 **sorting** | **String** | Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) | [optional] 

### Return type

**Any**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateGetUserTradesByInstrumentGet**
```swift
    open class func privateGetUserTradesByInstrumentGet(instrumentName: String, startSeq: Int? = nil, endSeq: Int? = nil, count: Int? = nil, includeOld: Bool? = nil, sorting: Sorting_privateGetUserTradesByInstrumentGet? = nil, completion: @escaping (_ data: Any?, _ error: Error?) -> Void)
```

Retrieve the latest user trades that have occurred for a specific instrument.

### Example 
```swift
// The following code samples are still beta. For any issue, please report via http://github.com/OpenAPITools/openapi-generator/issues/new
import OpenAPIClient

let instrumentName = "instrumentName_example" // String | Instrument name
let startSeq = 987 // Int | The sequence number of the first trade to be returned (optional)
let endSeq = 987 // Int | The sequence number of the last trade to be returned (optional)
let count = 987 // Int | Number of requested items, default - `10` (optional)
let includeOld = false // Bool | Include trades older than 7 days, default - `false` (optional)
let sorting = "sorting_example" // String | Direction of results sorting (`default` value means no sorting, results will be returned in order in which they left the database) (optional)

// Retrieve the latest user trades that have occurred for a specific instrument.
PrivateAPI.privateGetUserTradesByInstrumentGet(instrumentName: instrumentName, startSeq: startSeq, endSeq: endSeq, count: count, includeOld: includeOld, sorting: sorting) { (response, error) in
    guard error == nil else {
        print(error)
        return
    }

    if (response) {
        dump(response)
    }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrumentName** | **String** | Instrument name | 
 **startSeq** | **Int** | The sequence number of the first trade to be returned | [optional] 
 **endSeq** | **Int** | The sequence number of the last trade to be returned | [optional] 
 **count** | **Int** | Number of requested items, default - &#x60;10&#x60; | [optional] 
 **includeOld** | **Bool** | Include trades older than 7 days, default - &#x60;false&#x60; | [optional] 
 **sorting** | **String** | Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) | [optional] 

### Return type

**Any**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateGetUserTradesByOrderGet**
```swift
    open class func privateGetUserTradesByOrderGet(orderId: String, sorting: Sorting_privateGetUserTradesByOrderGet? = nil, completion: @escaping (_ data: Any?, _ error: Error?) -> Void)
```

Retrieve the list of user trades that was created for given order

### Example 
```swift
// The following code samples are still beta. For any issue, please report via http://github.com/OpenAPITools/openapi-generator/issues/new
import OpenAPIClient

let orderId = "orderId_example" // String | The order id
let sorting = "sorting_example" // String | Direction of results sorting (`default` value means no sorting, results will be returned in order in which they left the database) (optional)

// Retrieve the list of user trades that was created for given order
PrivateAPI.privateGetUserTradesByOrderGet(orderId: orderId, sorting: sorting) { (response, error) in
    guard error == nil else {
        print(error)
        return
    }

    if (response) {
        dump(response)
    }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **orderId** | **String** | The order id | 
 **sorting** | **String** | Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) | [optional] 

### Return type

**Any**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateGetWithdrawalsGet**
```swift
    open class func privateGetWithdrawalsGet(currency: Currency_privateGetWithdrawalsGet, count: Int? = nil, offset: Int? = nil, completion: @escaping (_ data: Any?, _ error: Error?) -> Void)
```

Retrieve the latest users withdrawals

### Example 
```swift
// The following code samples are still beta. For any issue, please report via http://github.com/OpenAPITools/openapi-generator/issues/new
import OpenAPIClient

let currency = "currency_example" // String | The currency symbol
let count = 987 // Int | Number of requested items, default - `10` (optional)
let offset = 987 // Int | The offset for pagination, default - `0` (optional)

// Retrieve the latest users withdrawals
PrivateAPI.privateGetWithdrawalsGet(currency: currency, count: count, offset: offset) { (response, error) in
    guard error == nil else {
        print(error)
        return
    }

    if (response) {
        dump(response)
    }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **String** | The currency symbol | 
 **count** | **Int** | Number of requested items, default - &#x60;10&#x60; | [optional] 
 **offset** | **Int** | The offset for pagination, default - &#x60;0&#x60; | [optional] 

### Return type

**Any**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateRemoveFromAddressBookGet**
```swift
    open class func privateRemoveFromAddressBookGet(currency: Currency_privateRemoveFromAddressBookGet, type: ModelType_privateRemoveFromAddressBookGet, address: String, tfa: String? = nil, completion: @escaping (_ data: Any?, _ error: Error?) -> Void)
```

Adds new entry to address book of given type

### Example 
```swift
// The following code samples are still beta. For any issue, please report via http://github.com/OpenAPITools/openapi-generator/issues/new
import OpenAPIClient

let currency = "currency_example" // String | The currency symbol
let type = "type_example" // String | Address book type
let address = "address_example" // String | Address in currency format, it must be in address book
let tfa = "tfa_example" // String | TFA code, required when TFA is enabled for current account (optional)

// Adds new entry to address book of given type
PrivateAPI.privateRemoveFromAddressBookGet(currency: currency, type: type, address: address, tfa: tfa) { (response, error) in
    guard error == nil else {
        print(error)
        return
    }

    if (response) {
        dump(response)
    }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **String** | The currency symbol | 
 **type** | **String** | Address book type | 
 **address** | **String** | Address in currency format, it must be in address book | 
 **tfa** | **String** | TFA code, required when TFA is enabled for current account | [optional] 

### Return type

**Any**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateSellGet**
```swift
    open class func privateSellGet(instrumentName: String, amount: Double, type: ModelType_privateSellGet? = nil, label: String? = nil, price: Double? = nil, timeInForce: TimeInForce_privateSellGet? = nil, maxShow: Double? = nil, postOnly: Bool? = nil, reduceOnly: Bool? = nil, stopPrice: Double? = nil, trigger: Trigger_privateSellGet? = nil, advanced: Advanced_privateSellGet? = nil, completion: @escaping (_ data: Any?, _ error: Error?) -> Void)
```

Places a sell order for an instrument.

### Example 
```swift
// The following code samples are still beta. For any issue, please report via http://github.com/OpenAPITools/openapi-generator/issues/new
import OpenAPIClient

let instrumentName = "instrumentName_example" // String | Instrument name
let amount = 987 // Double | It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH
let type = "type_example" // String | The order type, default: `\"limit\"` (optional)
let label = "label_example" // String | user defined label for the order (maximum 32 characters) (optional)
let price = 987 // Double | <p>The order price in base currency (Only for limit and stop_limit orders)</p> <p>When adding order with advanced=usd, the field price should be the option price value in USD.</p> <p>When adding order with advanced=implv, the field price should be a value of implied volatility in percentages. For example,  price=100, means implied volatility of 100%</p> (optional)
let timeInForce = "timeInForce_example" // String | <p>Specifies how long the order remains in effect. Default `\"good_til_cancelled\"`</p> <ul> <li>`\"good_til_cancelled\"` - unfilled order remains in order book until cancelled</li> <li>`\"fill_or_kill\"` - execute a transaction immediately and completely or not at all</li> <li>`\"immediate_or_cancel\"` - execute a transaction immediately, and any portion of the order that cannot be immediately filled is cancelled</li> </ul> (optional) (default to .good_til_cancelled)
let maxShow = 987 // Double | Maximum amount within an order to be shown to other customers, `0` for invisible order (optional) (default to 1)
let postOnly = false // Bool | <p>If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.</p> <p>Only valid in combination with time_in_force=`\"good_til_cancelled\"`</p> (optional) (default to true)
let reduceOnly = false // Bool | If `true`, the order is considered reduce-only which is intended to only reduce a current position (optional) (default to false)
let stopPrice = 987 // Double | Stop price, required for stop limit orders (Only for stop orders) (optional)
let trigger = "trigger_example" // String | Defines trigger type, required for `\"stop_limit\"` order type (optional)
let advanced = "advanced_example" // String | Advanced option order type. (Only for options) (optional)

// Places a sell order for an instrument.
PrivateAPI.privateSellGet(instrumentName: instrumentName, amount: amount, type: type, label: label, price: price, timeInForce: timeInForce, maxShow: maxShow, postOnly: postOnly, reduceOnly: reduceOnly, stopPrice: stopPrice, trigger: trigger, advanced: advanced) { (response, error) in
    guard error == nil else {
        print(error)
        return
    }

    if (response) {
        dump(response)
    }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrumentName** | **String** | Instrument name | 
 **amount** | **Double** | It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH | 
 **type** | **String** | The order type, default: &#x60;\&quot;limit\&quot;&#x60; | [optional] 
 **label** | **String** | user defined label for the order (maximum 32 characters) | [optional] 
 **price** | **Double** | &lt;p&gt;The order price in base currency (Only for limit and stop_limit orders)&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;usd, the field price should be the option price value in USD.&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;implv, the field price should be a value of implied volatility in percentages. For example,  price&#x3D;100, means implied volatility of 100%&lt;/p&gt; | [optional] 
 **timeInForce** | **String** | &lt;p&gt;Specifies how long the order remains in effect. Default &#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; &lt;ul&gt; &lt;li&gt;&#x60;\&quot;good_til_cancelled\&quot;&#x60; - unfilled order remains in order book until cancelled&lt;/li&gt; &lt;li&gt;&#x60;\&quot;fill_or_kill\&quot;&#x60; - execute a transaction immediately and completely or not at all&lt;/li&gt; &lt;li&gt;&#x60;\&quot;immediate_or_cancel\&quot;&#x60; - execute a transaction immediately, and any portion of the order that cannot be immediately filled is cancelled&lt;/li&gt; &lt;/ul&gt; | [optional] [default to .good_til_cancelled]
 **maxShow** | **Double** | Maximum amount within an order to be shown to other customers, &#x60;0&#x60; for invisible order | [optional] [default to 1]
 **postOnly** | **Bool** | &lt;p&gt;If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.&lt;/p&gt; &lt;p&gt;Only valid in combination with time_in_force&#x3D;&#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; | [optional] [default to true]
 **reduceOnly** | **Bool** | If &#x60;true&#x60;, the order is considered reduce-only which is intended to only reduce a current position | [optional] [default to false]
 **stopPrice** | **Double** | Stop price, required for stop limit orders (Only for stop orders) | [optional] 
 **trigger** | **String** | Defines trigger type, required for &#x60;\&quot;stop_limit\&quot;&#x60; order type | [optional] 
 **advanced** | **String** | Advanced option order type. (Only for options) | [optional] 

### Return type

**Any**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateSetAnnouncementAsReadGet**
```swift
    open class func privateSetAnnouncementAsReadGet(announcementId: Double, completion: @escaping (_ data: Any?, _ error: Error?) -> Void)
```

Marks an announcement as read, so it will not be shown in `get_new_announcements`.

### Example 
```swift
// The following code samples are still beta. For any issue, please report via http://github.com/OpenAPITools/openapi-generator/issues/new
import OpenAPIClient

let announcementId = 987 // Double | the ID of the announcement

// Marks an announcement as read, so it will not be shown in `get_new_announcements`.
PrivateAPI.privateSetAnnouncementAsReadGet(announcementId: announcementId) { (response, error) in
    guard error == nil else {
        print(error)
        return
    }

    if (response) {
        dump(response)
    }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **announcementId** | **Double** | the ID of the announcement | 

### Return type

**Any**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateSetEmailForSubaccountGet**
```swift
    open class func privateSetEmailForSubaccountGet(sid: Int, email: String, completion: @escaping (_ data: Any?, _ error: Error?) -> Void)
```

Assign an email address to a subaccount. User will receive an email with confirmation link.

### Example 
```swift
// The following code samples are still beta. For any issue, please report via http://github.com/OpenAPITools/openapi-generator/issues/new
import OpenAPIClient

let sid = 987 // Int | The user id for the subaccount
let email = "email_example" // String | The email address for the subaccount

// Assign an email address to a subaccount. User will receive an email with confirmation link.
PrivateAPI.privateSetEmailForSubaccountGet(sid: sid, email: email) { (response, error) in
    guard error == nil else {
        print(error)
        return
    }

    if (response) {
        dump(response)
    }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **sid** | **Int** | The user id for the subaccount | 
 **email** | **String** | The email address for the subaccount | 

### Return type

**Any**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateSetEmailLanguageGet**
```swift
    open class func privateSetEmailLanguageGet(language: String, completion: @escaping (_ data: Any?, _ error: Error?) -> Void)
```

Changes the language to be used for emails.

### Example 
```swift
// The following code samples are still beta. For any issue, please report via http://github.com/OpenAPITools/openapi-generator/issues/new
import OpenAPIClient

let language = "language_example" // String | The abbreviated language name. Valid values include `\"en\"`, `\"ko\"`, `\"zh\"`

// Changes the language to be used for emails.
PrivateAPI.privateSetEmailLanguageGet(language: language) { (response, error) in
    guard error == nil else {
        print(error)
        return
    }

    if (response) {
        dump(response)
    }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **language** | **String** | The abbreviated language name. Valid values include &#x60;\&quot;en\&quot;&#x60;, &#x60;\&quot;ko\&quot;&#x60;, &#x60;\&quot;zh\&quot;&#x60; | 

### Return type

**Any**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateSetPasswordForSubaccountGet**
```swift
    open class func privateSetPasswordForSubaccountGet(sid: Int, password: String, completion: @escaping (_ data: Any?, _ error: Error?) -> Void)
```

Set the password for the subaccount

### Example 
```swift
// The following code samples are still beta. For any issue, please report via http://github.com/OpenAPITools/openapi-generator/issues/new
import OpenAPIClient

let sid = 987 // Int | The user id for the subaccount
let password = "password_example" // String | The password for the subaccount

// Set the password for the subaccount
PrivateAPI.privateSetPasswordForSubaccountGet(sid: sid, password: password) { (response, error) in
    guard error == nil else {
        print(error)
        return
    }

    if (response) {
        dump(response)
    }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **sid** | **Int** | The user id for the subaccount | 
 **password** | **String** | The password for the subaccount | 

### Return type

**Any**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateSubmitTransferToSubaccountGet**
```swift
    open class func privateSubmitTransferToSubaccountGet(currency: Currency_privateSubmitTransferToSubaccountGet, amount: Double, destination: Int, completion: @escaping (_ data: Any?, _ error: Error?) -> Void)
```

Transfer funds to a subaccount.

### Example 
```swift
// The following code samples are still beta. For any issue, please report via http://github.com/OpenAPITools/openapi-generator/issues/new
import OpenAPIClient

let currency = "currency_example" // String | The currency symbol
let amount = 987 // Double | Amount of funds to be transferred
let destination = 987 // Int | Id of destination subaccount

// Transfer funds to a subaccount.
PrivateAPI.privateSubmitTransferToSubaccountGet(currency: currency, amount: amount, destination: destination) { (response, error) in
    guard error == nil else {
        print(error)
        return
    }

    if (response) {
        dump(response)
    }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **String** | The currency symbol | 
 **amount** | **Double** | Amount of funds to be transferred | 
 **destination** | **Int** | Id of destination subaccount | 

### Return type

**Any**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateSubmitTransferToUserGet**
```swift
    open class func privateSubmitTransferToUserGet(currency: Currency_privateSubmitTransferToUserGet, amount: Double, destination: String, tfa: String? = nil, completion: @escaping (_ data: Any?, _ error: Error?) -> Void)
```

Transfer funds to a another user.

### Example 
```swift
// The following code samples are still beta. For any issue, please report via http://github.com/OpenAPITools/openapi-generator/issues/new
import OpenAPIClient

let currency = "currency_example" // String | The currency symbol
let amount = 987 // Double | Amount of funds to be transferred
let destination = "destination_example" // String | Destination address from address book
let tfa = "tfa_example" // String | TFA code, required when TFA is enabled for current account (optional)

// Transfer funds to a another user.
PrivateAPI.privateSubmitTransferToUserGet(currency: currency, amount: amount, destination: destination, tfa: tfa) { (response, error) in
    guard error == nil else {
        print(error)
        return
    }

    if (response) {
        dump(response)
    }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **String** | The currency symbol | 
 **amount** | **Double** | Amount of funds to be transferred | 
 **destination** | **String** | Destination address from address book | 
 **tfa** | **String** | TFA code, required when TFA is enabled for current account | [optional] 

### Return type

**Any**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateToggleDepositAddressCreationGet**
```swift
    open class func privateToggleDepositAddressCreationGet(currency: Currency_privateToggleDepositAddressCreationGet, state: Bool, completion: @escaping (_ data: Any?, _ error: Error?) -> Void)
```

Enable or disable deposit address creation

### Example 
```swift
// The following code samples are still beta. For any issue, please report via http://github.com/OpenAPITools/openapi-generator/issues/new
import OpenAPIClient

let currency = "currency_example" // String | The currency symbol
let state = false // Bool | 

// Enable or disable deposit address creation
PrivateAPI.privateToggleDepositAddressCreationGet(currency: currency, state: state) { (response, error) in
    guard error == nil else {
        print(error)
        return
    }

    if (response) {
        dump(response)
    }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **String** | The currency symbol | 
 **state** | **Bool** |  | 

### Return type

**Any**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateToggleNotificationsFromSubaccountGet**
```swift
    open class func privateToggleNotificationsFromSubaccountGet(sid: Int, state: Bool, completion: @escaping (_ data: Any?, _ error: Error?) -> Void)
```

Enable or disable sending of notifications for the subaccount.

### Example 
```swift
// The following code samples are still beta. For any issue, please report via http://github.com/OpenAPITools/openapi-generator/issues/new
import OpenAPIClient

let sid = 987 // Int | The user id for the subaccount
let state = false // Bool | enable (`true`) or disable (`false`) notifications

// Enable or disable sending of notifications for the subaccount.
PrivateAPI.privateToggleNotificationsFromSubaccountGet(sid: sid, state: state) { (response, error) in
    guard error == nil else {
        print(error)
        return
    }

    if (response) {
        dump(response)
    }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **sid** | **Int** | The user id for the subaccount | 
 **state** | **Bool** | enable (&#x60;true&#x60;) or disable (&#x60;false&#x60;) notifications | 

### Return type

**Any**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateToggleSubaccountLoginGet**
```swift
    open class func privateToggleSubaccountLoginGet(sid: Int, state: State_privateToggleSubaccountLoginGet, completion: @escaping (_ data: Any?, _ error: Error?) -> Void)
```

Enable or disable login for a subaccount. If login is disabled and a session for the subaccount exists, this session will be terminated.

### Example 
```swift
// The following code samples are still beta. For any issue, please report via http://github.com/OpenAPITools/openapi-generator/issues/new
import OpenAPIClient

let sid = 987 // Int | The user id for the subaccount
let state = "state_example" // String | enable or disable login.

// Enable or disable login for a subaccount. If login is disabled and a session for the subaccount exists, this session will be terminated.
PrivateAPI.privateToggleSubaccountLoginGet(sid: sid, state: state) { (response, error) in
    guard error == nil else {
        print(error)
        return
    }

    if (response) {
        dump(response)
    }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **sid** | **Int** | The user id for the subaccount | 
 **state** | **String** | enable or disable login. | 

### Return type

**Any**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateWithdrawGet**
```swift
    open class func privateWithdrawGet(currency: Currency_privateWithdrawGet, address: String, amount: Double, priority: Priority_privateWithdrawGet? = nil, tfa: String? = nil, completion: @escaping (_ data: Any?, _ error: Error?) -> Void)
```

Creates a new withdrawal request

### Example 
```swift
// The following code samples are still beta. For any issue, please report via http://github.com/OpenAPITools/openapi-generator/issues/new
import OpenAPIClient

let currency = "currency_example" // String | The currency symbol
let address = "address_example" // String | Address in currency format, it must be in address book
let amount = 987 // Double | Amount of funds to be withdrawn
let priority = "priority_example" // String | Withdrawal priority, optional for BTC, default: `high` (optional)
let tfa = "tfa_example" // String | TFA code, required when TFA is enabled for current account (optional)

// Creates a new withdrawal request
PrivateAPI.privateWithdrawGet(currency: currency, address: address, amount: amount, priority: priority, tfa: tfa) { (response, error) in
    guard error == nil else {
        print(error)
        return
    }

    if (response) {
        dump(response)
    }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **String** | The currency symbol | 
 **address** | **String** | Address in currency format, it must be in address book | 
 **amount** | **Double** | Amount of funds to be withdrawn | 
 **priority** | **String** | Withdrawal priority, optional for BTC, default: &#x60;high&#x60; | [optional] 
 **tfa** | **String** | TFA code, required when TFA is enabled for current account | [optional] 

### Return type

**Any**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

