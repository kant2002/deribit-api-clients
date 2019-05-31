# PrivateApi

All URIs are relative to *https://www.deribit.com/api/v2*

Method | HTTP request | Description
------------- | ------------- | -------------
[**privateAddToAddressBookGet**](PrivateApi.md#privateAddToAddressBookGet) | **GET** /private/add_to_address_book | Adds new entry to address book of given type
[**privateBuyGet**](PrivateApi.md#privateBuyGet) | **GET** /private/buy | Places a buy order for an instrument.
[**privateCancelAllByCurrencyGet**](PrivateApi.md#privateCancelAllByCurrencyGet) | **GET** /private/cancel_all_by_currency | Cancels all orders by currency, optionally filtered by instrument kind and/or order type.
[**privateCancelAllByInstrumentGet**](PrivateApi.md#privateCancelAllByInstrumentGet) | **GET** /private/cancel_all_by_instrument | Cancels all orders by instrument, optionally filtered by order type.
[**privateCancelAllGet**](PrivateApi.md#privateCancelAllGet) | **GET** /private/cancel_all | This method cancels all users orders and stop orders within all currencies and instrument kinds.
[**privateCancelGet**](PrivateApi.md#privateCancelGet) | **GET** /private/cancel | Cancel an order, specified by order id
[**privateCancelTransferByIdGet**](PrivateApi.md#privateCancelTransferByIdGet) | **GET** /private/cancel_transfer_by_id | Cancel transfer
[**privateCancelWithdrawalGet**](PrivateApi.md#privateCancelWithdrawalGet) | **GET** /private/cancel_withdrawal | Cancels withdrawal request
[**privateChangeSubaccountNameGet**](PrivateApi.md#privateChangeSubaccountNameGet) | **GET** /private/change_subaccount_name | Change the user name for a subaccount
[**privateClosePositionGet**](PrivateApi.md#privateClosePositionGet) | **GET** /private/close_position | Makes closing position reduce only order .
[**privateCreateDepositAddressGet**](PrivateApi.md#privateCreateDepositAddressGet) | **GET** /private/create_deposit_address | Creates deposit address in currency
[**privateCreateSubaccountGet**](PrivateApi.md#privateCreateSubaccountGet) | **GET** /private/create_subaccount | Create a new subaccount
[**privateDisableTfaForSubaccountGet**](PrivateApi.md#privateDisableTfaForSubaccountGet) | **GET** /private/disable_tfa_for_subaccount | Disable two factor authentication for a subaccount.
[**privateDisableTfaWithRecoveryCodeGet**](PrivateApi.md#privateDisableTfaWithRecoveryCodeGet) | **GET** /private/disable_tfa_with_recovery_code | Disables TFA with one time recovery code
[**privateEditGet**](PrivateApi.md#privateEditGet) | **GET** /private/edit | Change price, amount and/or other properties of an order.
[**privateGetAccountSummaryGet**](PrivateApi.md#privateGetAccountSummaryGet) | **GET** /private/get_account_summary | Retrieves user account summary.
[**privateGetAddressBookGet**](PrivateApi.md#privateGetAddressBookGet) | **GET** /private/get_address_book | Retrieves address book of given type
[**privateGetCurrentDepositAddressGet**](PrivateApi.md#privateGetCurrentDepositAddressGet) | **GET** /private/get_current_deposit_address | Retrieve deposit address for currency
[**privateGetDepositsGet**](PrivateApi.md#privateGetDepositsGet) | **GET** /private/get_deposits | Retrieve the latest users deposits
[**privateGetEmailLanguageGet**](PrivateApi.md#privateGetEmailLanguageGet) | **GET** /private/get_email_language | Retrieves the language to be used for emails.
[**privateGetMarginsGet**](PrivateApi.md#privateGetMarginsGet) | **GET** /private/get_margins | Get margins for given instrument, amount and price.
[**privateGetNewAnnouncementsGet**](PrivateApi.md#privateGetNewAnnouncementsGet) | **GET** /private/get_new_announcements | Retrieves announcements that have not been marked read by the user.
[**privateGetOpenOrdersByCurrencyGet**](PrivateApi.md#privateGetOpenOrdersByCurrencyGet) | **GET** /private/get_open_orders_by_currency | Retrieves list of user&#39;s open orders.
[**privateGetOpenOrdersByInstrumentGet**](PrivateApi.md#privateGetOpenOrdersByInstrumentGet) | **GET** /private/get_open_orders_by_instrument | Retrieves list of user&#39;s open orders within given Instrument.
[**privateGetOrderHistoryByCurrencyGet**](PrivateApi.md#privateGetOrderHistoryByCurrencyGet) | **GET** /private/get_order_history_by_currency | Retrieves history of orders that have been partially or fully filled.
[**privateGetOrderHistoryByInstrumentGet**](PrivateApi.md#privateGetOrderHistoryByInstrumentGet) | **GET** /private/get_order_history_by_instrument | Retrieves history of orders that have been partially or fully filled.
[**privateGetOrderMarginByIdsGet**](PrivateApi.md#privateGetOrderMarginByIdsGet) | **GET** /private/get_order_margin_by_ids | Retrieves initial margins of given orders
[**privateGetOrderStateGet**](PrivateApi.md#privateGetOrderStateGet) | **GET** /private/get_order_state | Retrieve the current state of an order.
[**privateGetPositionGet**](PrivateApi.md#privateGetPositionGet) | **GET** /private/get_position | Retrieve user position.
[**privateGetPositionsGet**](PrivateApi.md#privateGetPositionsGet) | **GET** /private/get_positions | Retrieve user positions.
[**privateGetSettlementHistoryByCurrencyGet**](PrivateApi.md#privateGetSettlementHistoryByCurrencyGet) | **GET** /private/get_settlement_history_by_currency | Retrieves settlement, delivery and bankruptcy events that have affected your account.
[**privateGetSettlementHistoryByInstrumentGet**](PrivateApi.md#privateGetSettlementHistoryByInstrumentGet) | **GET** /private/get_settlement_history_by_instrument | Retrieves public settlement, delivery and bankruptcy events filtered by instrument name
[**privateGetSubaccountsGet**](PrivateApi.md#privateGetSubaccountsGet) | **GET** /private/get_subaccounts | Get information about subaccounts
[**privateGetTransfersGet**](PrivateApi.md#privateGetTransfersGet) | **GET** /private/get_transfers | Adds new entry to address book of given type
[**privateGetUserTradesByCurrencyAndTimeGet**](PrivateApi.md#privateGetUserTradesByCurrencyAndTimeGet) | **GET** /private/get_user_trades_by_currency_and_time | Retrieve the latest user trades that have occurred for instruments in a specific currency symbol and within given time range.
[**privateGetUserTradesByCurrencyGet**](PrivateApi.md#privateGetUserTradesByCurrencyGet) | **GET** /private/get_user_trades_by_currency | Retrieve the latest user trades that have occurred for instruments in a specific currency symbol.
[**privateGetUserTradesByInstrumentAndTimeGet**](PrivateApi.md#privateGetUserTradesByInstrumentAndTimeGet) | **GET** /private/get_user_trades_by_instrument_and_time | Retrieve the latest user trades that have occurred for a specific instrument and within given time range.
[**privateGetUserTradesByInstrumentGet**](PrivateApi.md#privateGetUserTradesByInstrumentGet) | **GET** /private/get_user_trades_by_instrument | Retrieve the latest user trades that have occurred for a specific instrument.
[**privateGetUserTradesByOrderGet**](PrivateApi.md#privateGetUserTradesByOrderGet) | **GET** /private/get_user_trades_by_order | Retrieve the list of user trades that was created for given order
[**privateGetWithdrawalsGet**](PrivateApi.md#privateGetWithdrawalsGet) | **GET** /private/get_withdrawals | Retrieve the latest users withdrawals
[**privateRemoveFromAddressBookGet**](PrivateApi.md#privateRemoveFromAddressBookGet) | **GET** /private/remove_from_address_book | Adds new entry to address book of given type
[**privateSellGet**](PrivateApi.md#privateSellGet) | **GET** /private/sell | Places a sell order for an instrument.
[**privateSetAnnouncementAsReadGet**](PrivateApi.md#privateSetAnnouncementAsReadGet) | **GET** /private/set_announcement_as_read | Marks an announcement as read, so it will not be shown in &#x60;get_new_announcements&#x60;.
[**privateSetEmailForSubaccountGet**](PrivateApi.md#privateSetEmailForSubaccountGet) | **GET** /private/set_email_for_subaccount | Assign an email address to a subaccount. User will receive an email with confirmation link.
[**privateSetEmailLanguageGet**](PrivateApi.md#privateSetEmailLanguageGet) | **GET** /private/set_email_language | Changes the language to be used for emails.
[**privateSetPasswordForSubaccountGet**](PrivateApi.md#privateSetPasswordForSubaccountGet) | **GET** /private/set_password_for_subaccount | Set the password for the subaccount
[**privateSubmitTransferToSubaccountGet**](PrivateApi.md#privateSubmitTransferToSubaccountGet) | **GET** /private/submit_transfer_to_subaccount | Transfer funds to a subaccount.
[**privateSubmitTransferToUserGet**](PrivateApi.md#privateSubmitTransferToUserGet) | **GET** /private/submit_transfer_to_user | Transfer funds to a another user.
[**privateToggleDepositAddressCreationGet**](PrivateApi.md#privateToggleDepositAddressCreationGet) | **GET** /private/toggle_deposit_address_creation | Enable or disable deposit address creation
[**privateToggleNotificationsFromSubaccountGet**](PrivateApi.md#privateToggleNotificationsFromSubaccountGet) | **GET** /private/toggle_notifications_from_subaccount | Enable or disable sending of notifications for the subaccount.
[**privateToggleSubaccountLoginGet**](PrivateApi.md#privateToggleSubaccountLoginGet) | **GET** /private/toggle_subaccount_login | Enable or disable login for a subaccount. If login is disabled and a session for the subaccount exists, this session will be terminated.
[**privateWithdrawGet**](PrivateApi.md#privateWithdrawGet) | **GET** /private/withdraw | Creates a new withdrawal request


<a name="privateAddToAddressBookGet"></a>
# **privateAddToAddressBookGet**
> kotlin.Any privateAddToAddressBookGet(currency, type, address, name, tfa)

Adds new entry to address book of given type

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = PrivateApi()
val currency : kotlin.String = currency_example // kotlin.String | The currency symbol
val type : kotlin.String = type_example // kotlin.String | Address book type
val address : kotlin.String = address_example // kotlin.String | Address in currency format, it must be in address book
val name : kotlin.String = Main address // kotlin.String | Name of address book entry
val tfa : kotlin.String = tfa_example // kotlin.String | TFA code, required when TFA is enabled for current account
try {
    val result : kotlin.Any = apiInstance.privateAddToAddressBookGet(currency, type, address, name, tfa)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling PrivateApi#privateAddToAddressBookGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling PrivateApi#privateAddToAddressBookGet")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **kotlin.String**| The currency symbol | [enum: BTC, ETH]
 **type** | **kotlin.String**| Address book type | [enum: transfer, withdrawal]
 **address** | **kotlin.String**| Address in currency format, it must be in address book |
 **name** | **kotlin.String**| Name of address book entry |
 **tfa** | **kotlin.String**| TFA code, required when TFA is enabled for current account | [optional]

### Return type

[**kotlin.Any**](kotlin.Any.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="privateBuyGet"></a>
# **privateBuyGet**
> kotlin.Any privateBuyGet(instrumentName, amount, type, label, price, timeInForce, maxShow, postOnly, reduceOnly, stopPrice, trigger, advanced)

Places a buy order for an instrument.

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = PrivateApi()
val instrumentName : kotlin.String = BTC-PERPETUAL // kotlin.String | Instrument name
val amount : java.math.BigDecimal = 8.14 // java.math.BigDecimal | It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH
val type : kotlin.String = type_example // kotlin.String | The order type, default: `\"limit\"`
val label : kotlin.String = label_example // kotlin.String | user defined label for the order (maximum 32 characters)
val price : java.math.BigDecimal = 8.14 // java.math.BigDecimal | <p>The order price in base currency (Only for limit and stop_limit orders)</p> <p>When adding order with advanced=usd, the field price should be the option price value in USD.</p> <p>When adding order with advanced=implv, the field price should be a value of implied volatility in percentages. For example,  price=100, means implied volatility of 100%</p>
val timeInForce : kotlin.String = timeInForce_example // kotlin.String | <p>Specifies how long the order remains in effect. Default `\"good_til_cancelled\"`</p> <ul> <li>`\"good_til_cancelled\"` - unfilled order remains in order book until cancelled</li> <li>`\"fill_or_kill\"` - execute a transaction immediately and completely or not at all</li> <li>`\"immediate_or_cancel\"` - execute a transaction immediately, and any portion of the order that cannot be immediately filled is cancelled</li> </ul>
val maxShow : java.math.BigDecimal = 8.14 // java.math.BigDecimal | Maximum amount within an order to be shown to other customers, `0` for invisible order
val postOnly : kotlin.Boolean = true // kotlin.Boolean | <p>If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.</p> <p>Only valid in combination with time_in_force=`\"good_til_cancelled\"`</p>
val reduceOnly : kotlin.Boolean = true // kotlin.Boolean | If `true`, the order is considered reduce-only which is intended to only reduce a current position
val stopPrice : java.math.BigDecimal = 8.14 // java.math.BigDecimal | Stop price, required for stop limit orders (Only for stop orders)
val trigger : kotlin.String = trigger_example // kotlin.String | Defines trigger type, required for `\"stop_limit\"` order type
val advanced : kotlin.String = advanced_example // kotlin.String | Advanced option order type. (Only for options)
try {
    val result : kotlin.Any = apiInstance.privateBuyGet(instrumentName, amount, type, label, price, timeInForce, maxShow, postOnly, reduceOnly, stopPrice, trigger, advanced)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling PrivateApi#privateBuyGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling PrivateApi#privateBuyGet")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrumentName** | **kotlin.String**| Instrument name |
 **amount** | **java.math.BigDecimal**| It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH |
 **type** | **kotlin.String**| The order type, default: &#x60;\&quot;limit\&quot;&#x60; | [optional] [enum: limit, stop_limit, market, stop_market]
 **label** | **kotlin.String**| user defined label for the order (maximum 32 characters) | [optional]
 **price** | **java.math.BigDecimal**| &lt;p&gt;The order price in base currency (Only for limit and stop_limit orders)&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;usd, the field price should be the option price value in USD.&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;implv, the field price should be a value of implied volatility in percentages. For example,  price&#x3D;100, means implied volatility of 100%&lt;/p&gt; | [optional]
 **timeInForce** | **kotlin.String**| &lt;p&gt;Specifies how long the order remains in effect. Default &#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; &lt;ul&gt; &lt;li&gt;&#x60;\&quot;good_til_cancelled\&quot;&#x60; - unfilled order remains in order book until cancelled&lt;/li&gt; &lt;li&gt;&#x60;\&quot;fill_or_kill\&quot;&#x60; - execute a transaction immediately and completely or not at all&lt;/li&gt; &lt;li&gt;&#x60;\&quot;immediate_or_cancel\&quot;&#x60; - execute a transaction immediately, and any portion of the order that cannot be immediately filled is cancelled&lt;/li&gt; &lt;/ul&gt; | [optional] [default to &#39;good_til_cancelled&#39;] [enum: good_til_cancelled, fill_or_kill, immediate_or_cancel]
 **maxShow** | **java.math.BigDecimal**| Maximum amount within an order to be shown to other customers, &#x60;0&#x60; for invisible order | [optional] [default to 1]
 **postOnly** | **kotlin.Boolean**| &lt;p&gt;If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.&lt;/p&gt; &lt;p&gt;Only valid in combination with time_in_force&#x3D;&#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; | [optional] [default to true]
 **reduceOnly** | **kotlin.Boolean**| If &#x60;true&#x60;, the order is considered reduce-only which is intended to only reduce a current position | [optional] [default to false]
 **stopPrice** | **java.math.BigDecimal**| Stop price, required for stop limit orders (Only for stop orders) | [optional]
 **trigger** | **kotlin.String**| Defines trigger type, required for &#x60;\&quot;stop_limit\&quot;&#x60; order type | [optional] [enum: index_price, mark_price, last_price]
 **advanced** | **kotlin.String**| Advanced option order type. (Only for options) | [optional] [enum: usd, implv]

### Return type

[**kotlin.Any**](kotlin.Any.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="privateCancelAllByCurrencyGet"></a>
# **privateCancelAllByCurrencyGet**
> kotlin.Any privateCancelAllByCurrencyGet(currency, kind, type)

Cancels all orders by currency, optionally filtered by instrument kind and/or order type.

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = PrivateApi()
val currency : kotlin.String = currency_example // kotlin.String | The currency symbol
val kind : kotlin.String = kind_example // kotlin.String | Instrument kind, if not provided instruments of all kinds are considered
val type : kotlin.String = type_example // kotlin.String | Order type - limit, stop or all, default - `all`
try {
    val result : kotlin.Any = apiInstance.privateCancelAllByCurrencyGet(currency, kind, type)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling PrivateApi#privateCancelAllByCurrencyGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling PrivateApi#privateCancelAllByCurrencyGet")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **kotlin.String**| The currency symbol | [enum: BTC, ETH]
 **kind** | **kotlin.String**| Instrument kind, if not provided instruments of all kinds are considered | [optional] [enum: future, option]
 **type** | **kotlin.String**| Order type - limit, stop or all, default - &#x60;all&#x60; | [optional] [enum: all, limit, stop]

### Return type

[**kotlin.Any**](kotlin.Any.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="privateCancelAllByInstrumentGet"></a>
# **privateCancelAllByInstrumentGet**
> kotlin.Any privateCancelAllByInstrumentGet(instrumentName, type)

Cancels all orders by instrument, optionally filtered by order type.

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = PrivateApi()
val instrumentName : kotlin.String = BTC-PERPETUAL // kotlin.String | Instrument name
val type : kotlin.String = type_example // kotlin.String | Order type - limit, stop or all, default - `all`
try {
    val result : kotlin.Any = apiInstance.privateCancelAllByInstrumentGet(instrumentName, type)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling PrivateApi#privateCancelAllByInstrumentGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling PrivateApi#privateCancelAllByInstrumentGet")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrumentName** | **kotlin.String**| Instrument name |
 **type** | **kotlin.String**| Order type - limit, stop or all, default - &#x60;all&#x60; | [optional] [enum: all, limit, stop]

### Return type

[**kotlin.Any**](kotlin.Any.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="privateCancelAllGet"></a>
# **privateCancelAllGet**
> kotlin.Any privateCancelAllGet()

This method cancels all users orders and stop orders within all currencies and instrument kinds.

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = PrivateApi()
try {
    val result : kotlin.Any = apiInstance.privateCancelAllGet()
    println(result)
} catch (e: ClientException) {
    println("4xx response calling PrivateApi#privateCancelAllGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling PrivateApi#privateCancelAllGet")
    e.printStackTrace()
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**kotlin.Any**](kotlin.Any.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="privateCancelGet"></a>
# **privateCancelGet**
> kotlin.Any privateCancelGet(orderId)

Cancel an order, specified by order id

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = PrivateApi()
val orderId : kotlin.String = ETH-100234 // kotlin.String | The order id
try {
    val result : kotlin.Any = apiInstance.privateCancelGet(orderId)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling PrivateApi#privateCancelGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling PrivateApi#privateCancelGet")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **orderId** | **kotlin.String**| The order id |

### Return type

[**kotlin.Any**](kotlin.Any.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="privateCancelTransferByIdGet"></a>
# **privateCancelTransferByIdGet**
> kotlin.Any privateCancelTransferByIdGet(currency, id, tfa)

Cancel transfer

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = PrivateApi()
val currency : kotlin.String = currency_example // kotlin.String | The currency symbol
val id : kotlin.Int = 12 // kotlin.Int | Id of transfer
val tfa : kotlin.String = tfa_example // kotlin.String | TFA code, required when TFA is enabled for current account
try {
    val result : kotlin.Any = apiInstance.privateCancelTransferByIdGet(currency, id, tfa)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling PrivateApi#privateCancelTransferByIdGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling PrivateApi#privateCancelTransferByIdGet")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **kotlin.String**| The currency symbol | [enum: BTC, ETH]
 **id** | **kotlin.Int**| Id of transfer |
 **tfa** | **kotlin.String**| TFA code, required when TFA is enabled for current account | [optional]

### Return type

[**kotlin.Any**](kotlin.Any.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="privateCancelWithdrawalGet"></a>
# **privateCancelWithdrawalGet**
> kotlin.Any privateCancelWithdrawalGet(currency, id)

Cancels withdrawal request

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = PrivateApi()
val currency : kotlin.String = currency_example // kotlin.String | The currency symbol
val id : java.math.BigDecimal = 1 // java.math.BigDecimal | The withdrawal id
try {
    val result : kotlin.Any = apiInstance.privateCancelWithdrawalGet(currency, id)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling PrivateApi#privateCancelWithdrawalGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling PrivateApi#privateCancelWithdrawalGet")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **kotlin.String**| The currency symbol | [enum: BTC, ETH]
 **id** | **java.math.BigDecimal**| The withdrawal id |

### Return type

[**kotlin.Any**](kotlin.Any.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="privateChangeSubaccountNameGet"></a>
# **privateChangeSubaccountNameGet**
> kotlin.Any privateChangeSubaccountNameGet(sid, name)

Change the user name for a subaccount

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = PrivateApi()
val sid : kotlin.Int = 56 // kotlin.Int | The user id for the subaccount
val name : kotlin.String = newUserName // kotlin.String | The new user name
try {
    val result : kotlin.Any = apiInstance.privateChangeSubaccountNameGet(sid, name)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling PrivateApi#privateChangeSubaccountNameGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling PrivateApi#privateChangeSubaccountNameGet")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **sid** | **kotlin.Int**| The user id for the subaccount |
 **name** | **kotlin.String**| The new user name |

### Return type

[**kotlin.Any**](kotlin.Any.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="privateClosePositionGet"></a>
# **privateClosePositionGet**
> kotlin.Any privateClosePositionGet(instrumentName, type, price)

Makes closing position reduce only order .

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = PrivateApi()
val instrumentName : kotlin.String = BTC-PERPETUAL // kotlin.String | Instrument name
val type : kotlin.String = type_example // kotlin.String | The order type
val price : java.math.BigDecimal = 8.14 // java.math.BigDecimal | Optional price for limit order.
try {
    val result : kotlin.Any = apiInstance.privateClosePositionGet(instrumentName, type, price)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling PrivateApi#privateClosePositionGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling PrivateApi#privateClosePositionGet")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrumentName** | **kotlin.String**| Instrument name |
 **type** | **kotlin.String**| The order type | [enum: limit, market]
 **price** | **java.math.BigDecimal**| Optional price for limit order. | [optional]

### Return type

[**kotlin.Any**](kotlin.Any.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="privateCreateDepositAddressGet"></a>
# **privateCreateDepositAddressGet**
> kotlin.Any privateCreateDepositAddressGet(currency)

Creates deposit address in currency

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = PrivateApi()
val currency : kotlin.String = currency_example // kotlin.String | The currency symbol
try {
    val result : kotlin.Any = apiInstance.privateCreateDepositAddressGet(currency)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling PrivateApi#privateCreateDepositAddressGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling PrivateApi#privateCreateDepositAddressGet")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **kotlin.String**| The currency symbol | [enum: BTC, ETH]

### Return type

[**kotlin.Any**](kotlin.Any.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="privateCreateSubaccountGet"></a>
# **privateCreateSubaccountGet**
> kotlin.Any privateCreateSubaccountGet()

Create a new subaccount

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = PrivateApi()
try {
    val result : kotlin.Any = apiInstance.privateCreateSubaccountGet()
    println(result)
} catch (e: ClientException) {
    println("4xx response calling PrivateApi#privateCreateSubaccountGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling PrivateApi#privateCreateSubaccountGet")
    e.printStackTrace()
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**kotlin.Any**](kotlin.Any.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="privateDisableTfaForSubaccountGet"></a>
# **privateDisableTfaForSubaccountGet**
> kotlin.Any privateDisableTfaForSubaccountGet(sid)

Disable two factor authentication for a subaccount.

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = PrivateApi()
val sid : kotlin.Int = 56 // kotlin.Int | The user id for the subaccount
try {
    val result : kotlin.Any = apiInstance.privateDisableTfaForSubaccountGet(sid)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling PrivateApi#privateDisableTfaForSubaccountGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling PrivateApi#privateDisableTfaForSubaccountGet")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **sid** | **kotlin.Int**| The user id for the subaccount |

### Return type

[**kotlin.Any**](kotlin.Any.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="privateDisableTfaWithRecoveryCodeGet"></a>
# **privateDisableTfaWithRecoveryCodeGet**
> kotlin.Any privateDisableTfaWithRecoveryCodeGet(password, code)

Disables TFA with one time recovery code

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = PrivateApi()
val password : kotlin.String = password_example // kotlin.String | The password for the subaccount
val code : kotlin.String = code_example // kotlin.String | One time recovery code
try {
    val result : kotlin.Any = apiInstance.privateDisableTfaWithRecoveryCodeGet(password, code)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling PrivateApi#privateDisableTfaWithRecoveryCodeGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling PrivateApi#privateDisableTfaWithRecoveryCodeGet")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **password** | **kotlin.String**| The password for the subaccount |
 **code** | **kotlin.String**| One time recovery code |

### Return type

[**kotlin.Any**](kotlin.Any.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="privateEditGet"></a>
# **privateEditGet**
> kotlin.Any privateEditGet(orderId, amount, price, postOnly, advanced, stopPrice)

Change price, amount and/or other properties of an order.

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = PrivateApi()
val orderId : kotlin.String = ETH-100234 // kotlin.String | The order id
val amount : java.math.BigDecimal = 8.14 // java.math.BigDecimal | It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH
val price : java.math.BigDecimal = 8.14 // java.math.BigDecimal | <p>The order price in base currency.</p> <p>When editing an option order with advanced=usd, the field price should be the option price value in USD.</p> <p>When editing an option order with advanced=implv, the field price should be a value of implied volatility in percentages. For example,  price=100, means implied volatility of 100%</p>
val postOnly : kotlin.Boolean = true // kotlin.Boolean | <p>If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.</p> <p>Only valid in combination with time_in_force=`\"good_til_cancelled\"`</p>
val advanced : kotlin.String = advanced_example // kotlin.String | Advanced option order type. If you have posted an advanced option order, it is necessary to re-supply this parameter when editing it (Only for options)
val stopPrice : java.math.BigDecimal = 8.14 // java.math.BigDecimal | Stop price, required for stop limit orders (Only for stop orders)
try {
    val result : kotlin.Any = apiInstance.privateEditGet(orderId, amount, price, postOnly, advanced, stopPrice)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling PrivateApi#privateEditGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling PrivateApi#privateEditGet")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **orderId** | **kotlin.String**| The order id |
 **amount** | **java.math.BigDecimal**| It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH |
 **price** | **java.math.BigDecimal**| &lt;p&gt;The order price in base currency.&lt;/p&gt; &lt;p&gt;When editing an option order with advanced&#x3D;usd, the field price should be the option price value in USD.&lt;/p&gt; &lt;p&gt;When editing an option order with advanced&#x3D;implv, the field price should be a value of implied volatility in percentages. For example,  price&#x3D;100, means implied volatility of 100%&lt;/p&gt; |
 **postOnly** | **kotlin.Boolean**| &lt;p&gt;If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.&lt;/p&gt; &lt;p&gt;Only valid in combination with time_in_force&#x3D;&#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; | [optional] [default to true]
 **advanced** | **kotlin.String**| Advanced option order type. If you have posted an advanced option order, it is necessary to re-supply this parameter when editing it (Only for options) | [optional] [enum: usd, implv]
 **stopPrice** | **java.math.BigDecimal**| Stop price, required for stop limit orders (Only for stop orders) | [optional]

### Return type

[**kotlin.Any**](kotlin.Any.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="privateGetAccountSummaryGet"></a>
# **privateGetAccountSummaryGet**
> kotlin.Any privateGetAccountSummaryGet(currency, extended)

Retrieves user account summary.

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = PrivateApi()
val currency : kotlin.String = currency_example // kotlin.String | The currency symbol
val extended : kotlin.Boolean = false // kotlin.Boolean | Include additional fields
try {
    val result : kotlin.Any = apiInstance.privateGetAccountSummaryGet(currency, extended)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling PrivateApi#privateGetAccountSummaryGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling PrivateApi#privateGetAccountSummaryGet")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **kotlin.String**| The currency symbol | [enum: BTC, ETH]
 **extended** | **kotlin.Boolean**| Include additional fields | [optional]

### Return type

[**kotlin.Any**](kotlin.Any.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="privateGetAddressBookGet"></a>
# **privateGetAddressBookGet**
> kotlin.Any privateGetAddressBookGet(currency, type)

Retrieves address book of given type

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = PrivateApi()
val currency : kotlin.String = currency_example // kotlin.String | The currency symbol
val type : kotlin.String = type_example // kotlin.String | Address book type
try {
    val result : kotlin.Any = apiInstance.privateGetAddressBookGet(currency, type)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling PrivateApi#privateGetAddressBookGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling PrivateApi#privateGetAddressBookGet")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **kotlin.String**| The currency symbol | [enum: BTC, ETH]
 **type** | **kotlin.String**| Address book type | [enum: transfer, withdrawal]

### Return type

[**kotlin.Any**](kotlin.Any.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="privateGetCurrentDepositAddressGet"></a>
# **privateGetCurrentDepositAddressGet**
> kotlin.Any privateGetCurrentDepositAddressGet(currency)

Retrieve deposit address for currency

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = PrivateApi()
val currency : kotlin.String = currency_example // kotlin.String | The currency symbol
try {
    val result : kotlin.Any = apiInstance.privateGetCurrentDepositAddressGet(currency)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling PrivateApi#privateGetCurrentDepositAddressGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling PrivateApi#privateGetCurrentDepositAddressGet")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **kotlin.String**| The currency symbol | [enum: BTC, ETH]

### Return type

[**kotlin.Any**](kotlin.Any.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="privateGetDepositsGet"></a>
# **privateGetDepositsGet**
> kotlin.Any privateGetDepositsGet(currency, count, offset)

Retrieve the latest users deposits

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = PrivateApi()
val currency : kotlin.String = currency_example // kotlin.String | The currency symbol
val count : kotlin.Int = 56 // kotlin.Int | Number of requested items, default - `10`
val offset : kotlin.Int = 10 // kotlin.Int | The offset for pagination, default - `0`
try {
    val result : kotlin.Any = apiInstance.privateGetDepositsGet(currency, count, offset)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling PrivateApi#privateGetDepositsGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling PrivateApi#privateGetDepositsGet")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **kotlin.String**| The currency symbol | [enum: BTC, ETH]
 **count** | **kotlin.Int**| Number of requested items, default - &#x60;10&#x60; | [optional]
 **offset** | **kotlin.Int**| The offset for pagination, default - &#x60;0&#x60; | [optional]

### Return type

[**kotlin.Any**](kotlin.Any.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="privateGetEmailLanguageGet"></a>
# **privateGetEmailLanguageGet**
> kotlin.Any privateGetEmailLanguageGet()

Retrieves the language to be used for emails.

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = PrivateApi()
try {
    val result : kotlin.Any = apiInstance.privateGetEmailLanguageGet()
    println(result)
} catch (e: ClientException) {
    println("4xx response calling PrivateApi#privateGetEmailLanguageGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling PrivateApi#privateGetEmailLanguageGet")
    e.printStackTrace()
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**kotlin.Any**](kotlin.Any.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="privateGetMarginsGet"></a>
# **privateGetMarginsGet**
> kotlin.Any privateGetMarginsGet(instrumentName, amount, price)

Get margins for given instrument, amount and price.

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = PrivateApi()
val instrumentName : kotlin.String = BTC-PERPETUAL // kotlin.String | Instrument name
val amount : java.math.BigDecimal = 1 // java.math.BigDecimal | Amount, integer for future, float for option. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH.
val price : java.math.BigDecimal = 8.14 // java.math.BigDecimal | Price
try {
    val result : kotlin.Any = apiInstance.privateGetMarginsGet(instrumentName, amount, price)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling PrivateApi#privateGetMarginsGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling PrivateApi#privateGetMarginsGet")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrumentName** | **kotlin.String**| Instrument name |
 **amount** | **java.math.BigDecimal**| Amount, integer for future, float for option. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH. |
 **price** | **java.math.BigDecimal**| Price |

### Return type

[**kotlin.Any**](kotlin.Any.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="privateGetNewAnnouncementsGet"></a>
# **privateGetNewAnnouncementsGet**
> kotlin.Any privateGetNewAnnouncementsGet()

Retrieves announcements that have not been marked read by the user.

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = PrivateApi()
try {
    val result : kotlin.Any = apiInstance.privateGetNewAnnouncementsGet()
    println(result)
} catch (e: ClientException) {
    println("4xx response calling PrivateApi#privateGetNewAnnouncementsGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling PrivateApi#privateGetNewAnnouncementsGet")
    e.printStackTrace()
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**kotlin.Any**](kotlin.Any.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="privateGetOpenOrdersByCurrencyGet"></a>
# **privateGetOpenOrdersByCurrencyGet**
> kotlin.Any privateGetOpenOrdersByCurrencyGet(currency, kind, type)

Retrieves list of user&#39;s open orders.

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = PrivateApi()
val currency : kotlin.String = currency_example // kotlin.String | The currency symbol
val kind : kotlin.String = kind_example // kotlin.String | Instrument kind, if not provided instruments of all kinds are considered
val type : kotlin.String = type_example // kotlin.String | Order type, default - `all`
try {
    val result : kotlin.Any = apiInstance.privateGetOpenOrdersByCurrencyGet(currency, kind, type)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling PrivateApi#privateGetOpenOrdersByCurrencyGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling PrivateApi#privateGetOpenOrdersByCurrencyGet")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **kotlin.String**| The currency symbol | [enum: BTC, ETH]
 **kind** | **kotlin.String**| Instrument kind, if not provided instruments of all kinds are considered | [optional] [enum: future, option]
 **type** | **kotlin.String**| Order type, default - &#x60;all&#x60; | [optional] [enum: all, limit, stop_all, stop_limit, stop_market]

### Return type

[**kotlin.Any**](kotlin.Any.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="privateGetOpenOrdersByInstrumentGet"></a>
# **privateGetOpenOrdersByInstrumentGet**
> kotlin.Any privateGetOpenOrdersByInstrumentGet(instrumentName, type)

Retrieves list of user&#39;s open orders within given Instrument.

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = PrivateApi()
val instrumentName : kotlin.String = BTC-PERPETUAL // kotlin.String | Instrument name
val type : kotlin.String = type_example // kotlin.String | Order type, default - `all`
try {
    val result : kotlin.Any = apiInstance.privateGetOpenOrdersByInstrumentGet(instrumentName, type)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling PrivateApi#privateGetOpenOrdersByInstrumentGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling PrivateApi#privateGetOpenOrdersByInstrumentGet")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrumentName** | **kotlin.String**| Instrument name |
 **type** | **kotlin.String**| Order type, default - &#x60;all&#x60; | [optional] [enum: all, limit, stop_all, stop_limit, stop_market]

### Return type

[**kotlin.Any**](kotlin.Any.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="privateGetOrderHistoryByCurrencyGet"></a>
# **privateGetOrderHistoryByCurrencyGet**
> kotlin.Any privateGetOrderHistoryByCurrencyGet(currency, kind, count, offset, includeOld, includeUnfilled)

Retrieves history of orders that have been partially or fully filled.

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = PrivateApi()
val currency : kotlin.String = currency_example // kotlin.String | The currency symbol
val kind : kotlin.String = kind_example // kotlin.String | Instrument kind, if not provided instruments of all kinds are considered
val count : kotlin.Int = 56 // kotlin.Int | Number of requested items, default - `20`
val offset : kotlin.Int = 10 // kotlin.Int | The offset for pagination, default - `0`
val includeOld : kotlin.Boolean = false // kotlin.Boolean | Include in result orders older than 2 days, default - `false`
val includeUnfilled : kotlin.Boolean = false // kotlin.Boolean | Include in result fully unfilled closed orders, default - `false`
try {
    val result : kotlin.Any = apiInstance.privateGetOrderHistoryByCurrencyGet(currency, kind, count, offset, includeOld, includeUnfilled)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling PrivateApi#privateGetOrderHistoryByCurrencyGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling PrivateApi#privateGetOrderHistoryByCurrencyGet")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **kotlin.String**| The currency symbol | [enum: BTC, ETH]
 **kind** | **kotlin.String**| Instrument kind, if not provided instruments of all kinds are considered | [optional] [enum: future, option]
 **count** | **kotlin.Int**| Number of requested items, default - &#x60;20&#x60; | [optional]
 **offset** | **kotlin.Int**| The offset for pagination, default - &#x60;0&#x60; | [optional]
 **includeOld** | **kotlin.Boolean**| Include in result orders older than 2 days, default - &#x60;false&#x60; | [optional]
 **includeUnfilled** | **kotlin.Boolean**| Include in result fully unfilled closed orders, default - &#x60;false&#x60; | [optional]

### Return type

[**kotlin.Any**](kotlin.Any.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="privateGetOrderHistoryByInstrumentGet"></a>
# **privateGetOrderHistoryByInstrumentGet**
> kotlin.Any privateGetOrderHistoryByInstrumentGet(instrumentName, count, offset, includeOld, includeUnfilled)

Retrieves history of orders that have been partially or fully filled.

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = PrivateApi()
val instrumentName : kotlin.String = BTC-PERPETUAL // kotlin.String | Instrument name
val count : kotlin.Int = 56 // kotlin.Int | Number of requested items, default - `20`
val offset : kotlin.Int = 10 // kotlin.Int | The offset for pagination, default - `0`
val includeOld : kotlin.Boolean = false // kotlin.Boolean | Include in result orders older than 2 days, default - `false`
val includeUnfilled : kotlin.Boolean = false // kotlin.Boolean | Include in result fully unfilled closed orders, default - `false`
try {
    val result : kotlin.Any = apiInstance.privateGetOrderHistoryByInstrumentGet(instrumentName, count, offset, includeOld, includeUnfilled)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling PrivateApi#privateGetOrderHistoryByInstrumentGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling PrivateApi#privateGetOrderHistoryByInstrumentGet")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrumentName** | **kotlin.String**| Instrument name |
 **count** | **kotlin.Int**| Number of requested items, default - &#x60;20&#x60; | [optional]
 **offset** | **kotlin.Int**| The offset for pagination, default - &#x60;0&#x60; | [optional]
 **includeOld** | **kotlin.Boolean**| Include in result orders older than 2 days, default - &#x60;false&#x60; | [optional]
 **includeUnfilled** | **kotlin.Boolean**| Include in result fully unfilled closed orders, default - &#x60;false&#x60; | [optional]

### Return type

[**kotlin.Any**](kotlin.Any.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="privateGetOrderMarginByIdsGet"></a>
# **privateGetOrderMarginByIdsGet**
> kotlin.Any privateGetOrderMarginByIdsGet(ids)

Retrieves initial margins of given orders

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = PrivateApi()
val ids : kotlin.Array<kotlin.String> =  // kotlin.Array<kotlin.String> | Ids of orders
try {
    val result : kotlin.Any = apiInstance.privateGetOrderMarginByIdsGet(ids)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling PrivateApi#privateGetOrderMarginByIdsGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling PrivateApi#privateGetOrderMarginByIdsGet")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **ids** | [**kotlin.Array&lt;kotlin.String&gt;**](kotlin.String.md)| Ids of orders |

### Return type

[**kotlin.Any**](kotlin.Any.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="privateGetOrderStateGet"></a>
# **privateGetOrderStateGet**
> kotlin.Any privateGetOrderStateGet(orderId)

Retrieve the current state of an order.

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = PrivateApi()
val orderId : kotlin.String = ETH-100234 // kotlin.String | The order id
try {
    val result : kotlin.Any = apiInstance.privateGetOrderStateGet(orderId)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling PrivateApi#privateGetOrderStateGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling PrivateApi#privateGetOrderStateGet")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **orderId** | **kotlin.String**| The order id |

### Return type

[**kotlin.Any**](kotlin.Any.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="privateGetPositionGet"></a>
# **privateGetPositionGet**
> kotlin.Any privateGetPositionGet(instrumentName)

Retrieve user position.

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = PrivateApi()
val instrumentName : kotlin.String = BTC-PERPETUAL // kotlin.String | Instrument name
try {
    val result : kotlin.Any = apiInstance.privateGetPositionGet(instrumentName)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling PrivateApi#privateGetPositionGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling PrivateApi#privateGetPositionGet")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrumentName** | **kotlin.String**| Instrument name |

### Return type

[**kotlin.Any**](kotlin.Any.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="privateGetPositionsGet"></a>
# **privateGetPositionsGet**
> kotlin.Any privateGetPositionsGet(currency, kind)

Retrieve user positions.

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = PrivateApi()
val currency : kotlin.String = currency_example // kotlin.String | 
val kind : kotlin.String = kind_example // kotlin.String | Kind filter on positions
try {
    val result : kotlin.Any = apiInstance.privateGetPositionsGet(currency, kind)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling PrivateApi#privateGetPositionsGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling PrivateApi#privateGetPositionsGet")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **kotlin.String**|  | [enum: BTC, ETH]
 **kind** | **kotlin.String**| Kind filter on positions | [optional] [enum: future, option]

### Return type

[**kotlin.Any**](kotlin.Any.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="privateGetSettlementHistoryByCurrencyGet"></a>
# **privateGetSettlementHistoryByCurrencyGet**
> kotlin.Any privateGetSettlementHistoryByCurrencyGet(currency, type, count)

Retrieves settlement, delivery and bankruptcy events that have affected your account.

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = PrivateApi()
val currency : kotlin.String = currency_example // kotlin.String | The currency symbol
val type : kotlin.String = type_example // kotlin.String | Settlement type
val count : kotlin.Int = 56 // kotlin.Int | Number of requested items, default - `20`
try {
    val result : kotlin.Any = apiInstance.privateGetSettlementHistoryByCurrencyGet(currency, type, count)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling PrivateApi#privateGetSettlementHistoryByCurrencyGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling PrivateApi#privateGetSettlementHistoryByCurrencyGet")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **kotlin.String**| The currency symbol | [enum: BTC, ETH]
 **type** | **kotlin.String**| Settlement type | [optional] [enum: settlement, delivery, bankruptcy]
 **count** | **kotlin.Int**| Number of requested items, default - &#x60;20&#x60; | [optional]

### Return type

[**kotlin.Any**](kotlin.Any.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="privateGetSettlementHistoryByInstrumentGet"></a>
# **privateGetSettlementHistoryByInstrumentGet**
> kotlin.Any privateGetSettlementHistoryByInstrumentGet(instrumentName, type, count)

Retrieves public settlement, delivery and bankruptcy events filtered by instrument name

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = PrivateApi()
val instrumentName : kotlin.String = BTC-PERPETUAL // kotlin.String | Instrument name
val type : kotlin.String = type_example // kotlin.String | Settlement type
val count : kotlin.Int = 56 // kotlin.Int | Number of requested items, default - `20`
try {
    val result : kotlin.Any = apiInstance.privateGetSettlementHistoryByInstrumentGet(instrumentName, type, count)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling PrivateApi#privateGetSettlementHistoryByInstrumentGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling PrivateApi#privateGetSettlementHistoryByInstrumentGet")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrumentName** | **kotlin.String**| Instrument name |
 **type** | **kotlin.String**| Settlement type | [optional] [enum: settlement, delivery, bankruptcy]
 **count** | **kotlin.Int**| Number of requested items, default - &#x60;20&#x60; | [optional]

### Return type

[**kotlin.Any**](kotlin.Any.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="privateGetSubaccountsGet"></a>
# **privateGetSubaccountsGet**
> kotlin.Any privateGetSubaccountsGet(withPortfolio)

Get information about subaccounts

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = PrivateApi()
val withPortfolio : kotlin.Boolean = true // kotlin.Boolean | 
try {
    val result : kotlin.Any = apiInstance.privateGetSubaccountsGet(withPortfolio)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling PrivateApi#privateGetSubaccountsGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling PrivateApi#privateGetSubaccountsGet")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **withPortfolio** | **kotlin.Boolean**|  | [optional]

### Return type

[**kotlin.Any**](kotlin.Any.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="privateGetTransfersGet"></a>
# **privateGetTransfersGet**
> kotlin.Any privateGetTransfersGet(currency, count, offset)

Adds new entry to address book of given type

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = PrivateApi()
val currency : kotlin.String = currency_example // kotlin.String | The currency symbol
val count : kotlin.Int = 56 // kotlin.Int | Number of requested items, default - `10`
val offset : kotlin.Int = 10 // kotlin.Int | The offset for pagination, default - `0`
try {
    val result : kotlin.Any = apiInstance.privateGetTransfersGet(currency, count, offset)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling PrivateApi#privateGetTransfersGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling PrivateApi#privateGetTransfersGet")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **kotlin.String**| The currency symbol | [enum: BTC, ETH]
 **count** | **kotlin.Int**| Number of requested items, default - &#x60;10&#x60; | [optional]
 **offset** | **kotlin.Int**| The offset for pagination, default - &#x60;0&#x60; | [optional]

### Return type

[**kotlin.Any**](kotlin.Any.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="privateGetUserTradesByCurrencyAndTimeGet"></a>
# **privateGetUserTradesByCurrencyAndTimeGet**
> kotlin.Any privateGetUserTradesByCurrencyAndTimeGet(currency, startTimestamp, endTimestamp, kind, count, includeOld, sorting)

Retrieve the latest user trades that have occurred for instruments in a specific currency symbol and within given time range.

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = PrivateApi()
val currency : kotlin.String = currency_example // kotlin.String | The currency symbol
val startTimestamp : kotlin.Int = 1536569522277 // kotlin.Int | The earliest timestamp to return result for
val endTimestamp : kotlin.Int = 1536569522277 // kotlin.Int | The most recent timestamp to return result for
val kind : kotlin.String = kind_example // kotlin.String | Instrument kind, if not provided instruments of all kinds are considered
val count : kotlin.Int = 56 // kotlin.Int | Number of requested items, default - `10`
val includeOld : kotlin.Boolean = true // kotlin.Boolean | Include trades older than 7 days, default - `false`
val sorting : kotlin.String = sorting_example // kotlin.String | Direction of results sorting (`default` value means no sorting, results will be returned in order in which they left the database)
try {
    val result : kotlin.Any = apiInstance.privateGetUserTradesByCurrencyAndTimeGet(currency, startTimestamp, endTimestamp, kind, count, includeOld, sorting)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling PrivateApi#privateGetUserTradesByCurrencyAndTimeGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling PrivateApi#privateGetUserTradesByCurrencyAndTimeGet")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **kotlin.String**| The currency symbol | [enum: BTC, ETH]
 **startTimestamp** | **kotlin.Int**| The earliest timestamp to return result for |
 **endTimestamp** | **kotlin.Int**| The most recent timestamp to return result for |
 **kind** | **kotlin.String**| Instrument kind, if not provided instruments of all kinds are considered | [optional] [enum: future, option]
 **count** | **kotlin.Int**| Number of requested items, default - &#x60;10&#x60; | [optional]
 **includeOld** | **kotlin.Boolean**| Include trades older than 7 days, default - &#x60;false&#x60; | [optional]
 **sorting** | **kotlin.String**| Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) | [optional] [enum: asc, desc, default]

### Return type

[**kotlin.Any**](kotlin.Any.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="privateGetUserTradesByCurrencyGet"></a>
# **privateGetUserTradesByCurrencyGet**
> kotlin.Any privateGetUserTradesByCurrencyGet(currency, kind, startId, endId, count, includeOld, sorting)

Retrieve the latest user trades that have occurred for instruments in a specific currency symbol.

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = PrivateApi()
val currency : kotlin.String = currency_example // kotlin.String | The currency symbol
val kind : kotlin.String = kind_example // kotlin.String | Instrument kind, if not provided instruments of all kinds are considered
val startId : kotlin.String = startId_example // kotlin.String | The ID number of the first trade to be returned
val endId : kotlin.String = endId_example // kotlin.String | The ID number of the last trade to be returned
val count : kotlin.Int = 56 // kotlin.Int | Number of requested items, default - `10`
val includeOld : kotlin.Boolean = true // kotlin.Boolean | Include trades older than 7 days, default - `false`
val sorting : kotlin.String = sorting_example // kotlin.String | Direction of results sorting (`default` value means no sorting, results will be returned in order in which they left the database)
try {
    val result : kotlin.Any = apiInstance.privateGetUserTradesByCurrencyGet(currency, kind, startId, endId, count, includeOld, sorting)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling PrivateApi#privateGetUserTradesByCurrencyGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling PrivateApi#privateGetUserTradesByCurrencyGet")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **kotlin.String**| The currency symbol | [enum: BTC, ETH]
 **kind** | **kotlin.String**| Instrument kind, if not provided instruments of all kinds are considered | [optional] [enum: future, option]
 **startId** | **kotlin.String**| The ID number of the first trade to be returned | [optional]
 **endId** | **kotlin.String**| The ID number of the last trade to be returned | [optional]
 **count** | **kotlin.Int**| Number of requested items, default - &#x60;10&#x60; | [optional]
 **includeOld** | **kotlin.Boolean**| Include trades older than 7 days, default - &#x60;false&#x60; | [optional]
 **sorting** | **kotlin.String**| Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) | [optional] [enum: asc, desc, default]

### Return type

[**kotlin.Any**](kotlin.Any.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="privateGetUserTradesByInstrumentAndTimeGet"></a>
# **privateGetUserTradesByInstrumentAndTimeGet**
> kotlin.Any privateGetUserTradesByInstrumentAndTimeGet(instrumentName, startTimestamp, endTimestamp, count, includeOld, sorting)

Retrieve the latest user trades that have occurred for a specific instrument and within given time range.

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = PrivateApi()
val instrumentName : kotlin.String = BTC-PERPETUAL // kotlin.String | Instrument name
val startTimestamp : kotlin.Int = 1536569522277 // kotlin.Int | The earliest timestamp to return result for
val endTimestamp : kotlin.Int = 1536569522277 // kotlin.Int | The most recent timestamp to return result for
val count : kotlin.Int = 56 // kotlin.Int | Number of requested items, default - `10`
val includeOld : kotlin.Boolean = true // kotlin.Boolean | Include trades older than 7 days, default - `false`
val sorting : kotlin.String = sorting_example // kotlin.String | Direction of results sorting (`default` value means no sorting, results will be returned in order in which they left the database)
try {
    val result : kotlin.Any = apiInstance.privateGetUserTradesByInstrumentAndTimeGet(instrumentName, startTimestamp, endTimestamp, count, includeOld, sorting)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling PrivateApi#privateGetUserTradesByInstrumentAndTimeGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling PrivateApi#privateGetUserTradesByInstrumentAndTimeGet")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrumentName** | **kotlin.String**| Instrument name |
 **startTimestamp** | **kotlin.Int**| The earliest timestamp to return result for |
 **endTimestamp** | **kotlin.Int**| The most recent timestamp to return result for |
 **count** | **kotlin.Int**| Number of requested items, default - &#x60;10&#x60; | [optional]
 **includeOld** | **kotlin.Boolean**| Include trades older than 7 days, default - &#x60;false&#x60; | [optional]
 **sorting** | **kotlin.String**| Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) | [optional] [enum: asc, desc, default]

### Return type

[**kotlin.Any**](kotlin.Any.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="privateGetUserTradesByInstrumentGet"></a>
# **privateGetUserTradesByInstrumentGet**
> kotlin.Any privateGetUserTradesByInstrumentGet(instrumentName, startSeq, endSeq, count, includeOld, sorting)

Retrieve the latest user trades that have occurred for a specific instrument.

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = PrivateApi()
val instrumentName : kotlin.String = BTC-PERPETUAL // kotlin.String | Instrument name
val startSeq : kotlin.Int = 56 // kotlin.Int | The sequence number of the first trade to be returned
val endSeq : kotlin.Int = 56 // kotlin.Int | The sequence number of the last trade to be returned
val count : kotlin.Int = 56 // kotlin.Int | Number of requested items, default - `10`
val includeOld : kotlin.Boolean = true // kotlin.Boolean | Include trades older than 7 days, default - `false`
val sorting : kotlin.String = sorting_example // kotlin.String | Direction of results sorting (`default` value means no sorting, results will be returned in order in which they left the database)
try {
    val result : kotlin.Any = apiInstance.privateGetUserTradesByInstrumentGet(instrumentName, startSeq, endSeq, count, includeOld, sorting)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling PrivateApi#privateGetUserTradesByInstrumentGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling PrivateApi#privateGetUserTradesByInstrumentGet")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrumentName** | **kotlin.String**| Instrument name |
 **startSeq** | **kotlin.Int**| The sequence number of the first trade to be returned | [optional]
 **endSeq** | **kotlin.Int**| The sequence number of the last trade to be returned | [optional]
 **count** | **kotlin.Int**| Number of requested items, default - &#x60;10&#x60; | [optional]
 **includeOld** | **kotlin.Boolean**| Include trades older than 7 days, default - &#x60;false&#x60; | [optional]
 **sorting** | **kotlin.String**| Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) | [optional] [enum: asc, desc, default]

### Return type

[**kotlin.Any**](kotlin.Any.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="privateGetUserTradesByOrderGet"></a>
# **privateGetUserTradesByOrderGet**
> kotlin.Any privateGetUserTradesByOrderGet(orderId, sorting)

Retrieve the list of user trades that was created for given order

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = PrivateApi()
val orderId : kotlin.String = ETH-100234 // kotlin.String | The order id
val sorting : kotlin.String = sorting_example // kotlin.String | Direction of results sorting (`default` value means no sorting, results will be returned in order in which they left the database)
try {
    val result : kotlin.Any = apiInstance.privateGetUserTradesByOrderGet(orderId, sorting)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling PrivateApi#privateGetUserTradesByOrderGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling PrivateApi#privateGetUserTradesByOrderGet")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **orderId** | **kotlin.String**| The order id |
 **sorting** | **kotlin.String**| Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) | [optional] [enum: asc, desc, default]

### Return type

[**kotlin.Any**](kotlin.Any.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="privateGetWithdrawalsGet"></a>
# **privateGetWithdrawalsGet**
> kotlin.Any privateGetWithdrawalsGet(currency, count, offset)

Retrieve the latest users withdrawals

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = PrivateApi()
val currency : kotlin.String = currency_example // kotlin.String | The currency symbol
val count : kotlin.Int = 56 // kotlin.Int | Number of requested items, default - `10`
val offset : kotlin.Int = 10 // kotlin.Int | The offset for pagination, default - `0`
try {
    val result : kotlin.Any = apiInstance.privateGetWithdrawalsGet(currency, count, offset)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling PrivateApi#privateGetWithdrawalsGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling PrivateApi#privateGetWithdrawalsGet")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **kotlin.String**| The currency symbol | [enum: BTC, ETH]
 **count** | **kotlin.Int**| Number of requested items, default - &#x60;10&#x60; | [optional]
 **offset** | **kotlin.Int**| The offset for pagination, default - &#x60;0&#x60; | [optional]

### Return type

[**kotlin.Any**](kotlin.Any.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="privateRemoveFromAddressBookGet"></a>
# **privateRemoveFromAddressBookGet**
> kotlin.Any privateRemoveFromAddressBookGet(currency, type, address, tfa)

Adds new entry to address book of given type

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = PrivateApi()
val currency : kotlin.String = currency_example // kotlin.String | The currency symbol
val type : kotlin.String = type_example // kotlin.String | Address book type
val address : kotlin.String = address_example // kotlin.String | Address in currency format, it must be in address book
val tfa : kotlin.String = tfa_example // kotlin.String | TFA code, required when TFA is enabled for current account
try {
    val result : kotlin.Any = apiInstance.privateRemoveFromAddressBookGet(currency, type, address, tfa)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling PrivateApi#privateRemoveFromAddressBookGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling PrivateApi#privateRemoveFromAddressBookGet")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **kotlin.String**| The currency symbol | [enum: BTC, ETH]
 **type** | **kotlin.String**| Address book type | [enum: transfer, withdrawal]
 **address** | **kotlin.String**| Address in currency format, it must be in address book |
 **tfa** | **kotlin.String**| TFA code, required when TFA is enabled for current account | [optional]

### Return type

[**kotlin.Any**](kotlin.Any.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="privateSellGet"></a>
# **privateSellGet**
> kotlin.Any privateSellGet(instrumentName, amount, type, label, price, timeInForce, maxShow, postOnly, reduceOnly, stopPrice, trigger, advanced)

Places a sell order for an instrument.

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = PrivateApi()
val instrumentName : kotlin.String = BTC-PERPETUAL // kotlin.String | Instrument name
val amount : java.math.BigDecimal = 8.14 // java.math.BigDecimal | It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH
val type : kotlin.String = type_example // kotlin.String | The order type, default: `\"limit\"`
val label : kotlin.String = label_example // kotlin.String | user defined label for the order (maximum 32 characters)
val price : java.math.BigDecimal = 8.14 // java.math.BigDecimal | <p>The order price in base currency (Only for limit and stop_limit orders)</p> <p>When adding order with advanced=usd, the field price should be the option price value in USD.</p> <p>When adding order with advanced=implv, the field price should be a value of implied volatility in percentages. For example,  price=100, means implied volatility of 100%</p>
val timeInForce : kotlin.String = timeInForce_example // kotlin.String | <p>Specifies how long the order remains in effect. Default `\"good_til_cancelled\"`</p> <ul> <li>`\"good_til_cancelled\"` - unfilled order remains in order book until cancelled</li> <li>`\"fill_or_kill\"` - execute a transaction immediately and completely or not at all</li> <li>`\"immediate_or_cancel\"` - execute a transaction immediately, and any portion of the order that cannot be immediately filled is cancelled</li> </ul>
val maxShow : java.math.BigDecimal = 8.14 // java.math.BigDecimal | Maximum amount within an order to be shown to other customers, `0` for invisible order
val postOnly : kotlin.Boolean = true // kotlin.Boolean | <p>If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.</p> <p>Only valid in combination with time_in_force=`\"good_til_cancelled\"`</p>
val reduceOnly : kotlin.Boolean = true // kotlin.Boolean | If `true`, the order is considered reduce-only which is intended to only reduce a current position
val stopPrice : java.math.BigDecimal = 8.14 // java.math.BigDecimal | Stop price, required for stop limit orders (Only for stop orders)
val trigger : kotlin.String = trigger_example // kotlin.String | Defines trigger type, required for `\"stop_limit\"` order type
val advanced : kotlin.String = advanced_example // kotlin.String | Advanced option order type. (Only for options)
try {
    val result : kotlin.Any = apiInstance.privateSellGet(instrumentName, amount, type, label, price, timeInForce, maxShow, postOnly, reduceOnly, stopPrice, trigger, advanced)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling PrivateApi#privateSellGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling PrivateApi#privateSellGet")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrumentName** | **kotlin.String**| Instrument name |
 **amount** | **java.math.BigDecimal**| It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH |
 **type** | **kotlin.String**| The order type, default: &#x60;\&quot;limit\&quot;&#x60; | [optional] [enum: limit, stop_limit, market, stop_market]
 **label** | **kotlin.String**| user defined label for the order (maximum 32 characters) | [optional]
 **price** | **java.math.BigDecimal**| &lt;p&gt;The order price in base currency (Only for limit and stop_limit orders)&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;usd, the field price should be the option price value in USD.&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;implv, the field price should be a value of implied volatility in percentages. For example,  price&#x3D;100, means implied volatility of 100%&lt;/p&gt; | [optional]
 **timeInForce** | **kotlin.String**| &lt;p&gt;Specifies how long the order remains in effect. Default &#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; &lt;ul&gt; &lt;li&gt;&#x60;\&quot;good_til_cancelled\&quot;&#x60; - unfilled order remains in order book until cancelled&lt;/li&gt; &lt;li&gt;&#x60;\&quot;fill_or_kill\&quot;&#x60; - execute a transaction immediately and completely or not at all&lt;/li&gt; &lt;li&gt;&#x60;\&quot;immediate_or_cancel\&quot;&#x60; - execute a transaction immediately, and any portion of the order that cannot be immediately filled is cancelled&lt;/li&gt; &lt;/ul&gt; | [optional] [default to &#39;good_til_cancelled&#39;] [enum: good_til_cancelled, fill_or_kill, immediate_or_cancel]
 **maxShow** | **java.math.BigDecimal**| Maximum amount within an order to be shown to other customers, &#x60;0&#x60; for invisible order | [optional] [default to 1]
 **postOnly** | **kotlin.Boolean**| &lt;p&gt;If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.&lt;/p&gt; &lt;p&gt;Only valid in combination with time_in_force&#x3D;&#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; | [optional] [default to true]
 **reduceOnly** | **kotlin.Boolean**| If &#x60;true&#x60;, the order is considered reduce-only which is intended to only reduce a current position | [optional] [default to false]
 **stopPrice** | **java.math.BigDecimal**| Stop price, required for stop limit orders (Only for stop orders) | [optional]
 **trigger** | **kotlin.String**| Defines trigger type, required for &#x60;\&quot;stop_limit\&quot;&#x60; order type | [optional] [enum: index_price, mark_price, last_price]
 **advanced** | **kotlin.String**| Advanced option order type. (Only for options) | [optional] [enum: usd, implv]

### Return type

[**kotlin.Any**](kotlin.Any.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="privateSetAnnouncementAsReadGet"></a>
# **privateSetAnnouncementAsReadGet**
> kotlin.Any privateSetAnnouncementAsReadGet(announcementId)

Marks an announcement as read, so it will not be shown in &#x60;get_new_announcements&#x60;.

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = PrivateApi()
val announcementId : java.math.BigDecimal = 8.14 // java.math.BigDecimal | the ID of the announcement
try {
    val result : kotlin.Any = apiInstance.privateSetAnnouncementAsReadGet(announcementId)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling PrivateApi#privateSetAnnouncementAsReadGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling PrivateApi#privateSetAnnouncementAsReadGet")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **announcementId** | **java.math.BigDecimal**| the ID of the announcement |

### Return type

[**kotlin.Any**](kotlin.Any.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="privateSetEmailForSubaccountGet"></a>
# **privateSetEmailForSubaccountGet**
> kotlin.Any privateSetEmailForSubaccountGet(sid, email)

Assign an email address to a subaccount. User will receive an email with confirmation link.

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = PrivateApi()
val sid : kotlin.Int = 56 // kotlin.Int | The user id for the subaccount
val email : kotlin.String = subaccount_1@email.com // kotlin.String | The email address for the subaccount
try {
    val result : kotlin.Any = apiInstance.privateSetEmailForSubaccountGet(sid, email)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling PrivateApi#privateSetEmailForSubaccountGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling PrivateApi#privateSetEmailForSubaccountGet")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **sid** | **kotlin.Int**| The user id for the subaccount |
 **email** | **kotlin.String**| The email address for the subaccount |

### Return type

[**kotlin.Any**](kotlin.Any.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="privateSetEmailLanguageGet"></a>
# **privateSetEmailLanguageGet**
> kotlin.Any privateSetEmailLanguageGet(language)

Changes the language to be used for emails.

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = PrivateApi()
val language : kotlin.String = en // kotlin.String | The abbreviated language name. Valid values include `\"en\"`, `\"ko\"`, `\"zh\"`
try {
    val result : kotlin.Any = apiInstance.privateSetEmailLanguageGet(language)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling PrivateApi#privateSetEmailLanguageGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling PrivateApi#privateSetEmailLanguageGet")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **language** | **kotlin.String**| The abbreviated language name. Valid values include &#x60;\&quot;en\&quot;&#x60;, &#x60;\&quot;ko\&quot;&#x60;, &#x60;\&quot;zh\&quot;&#x60; |

### Return type

[**kotlin.Any**](kotlin.Any.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="privateSetPasswordForSubaccountGet"></a>
# **privateSetPasswordForSubaccountGet**
> kotlin.Any privateSetPasswordForSubaccountGet(sid, password)

Set the password for the subaccount

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = PrivateApi()
val sid : kotlin.Int = 56 // kotlin.Int | The user id for the subaccount
val password : kotlin.String = password_example // kotlin.String | The password for the subaccount
try {
    val result : kotlin.Any = apiInstance.privateSetPasswordForSubaccountGet(sid, password)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling PrivateApi#privateSetPasswordForSubaccountGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling PrivateApi#privateSetPasswordForSubaccountGet")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **sid** | **kotlin.Int**| The user id for the subaccount |
 **password** | **kotlin.String**| The password for the subaccount |

### Return type

[**kotlin.Any**](kotlin.Any.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="privateSubmitTransferToSubaccountGet"></a>
# **privateSubmitTransferToSubaccountGet**
> kotlin.Any privateSubmitTransferToSubaccountGet(currency, amount, destination)

Transfer funds to a subaccount.

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = PrivateApi()
val currency : kotlin.String = currency_example // kotlin.String | The currency symbol
val amount : java.math.BigDecimal = 8.14 // java.math.BigDecimal | Amount of funds to be transferred
val destination : kotlin.Int = 1 // kotlin.Int | Id of destination subaccount
try {
    val result : kotlin.Any = apiInstance.privateSubmitTransferToSubaccountGet(currency, amount, destination)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling PrivateApi#privateSubmitTransferToSubaccountGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling PrivateApi#privateSubmitTransferToSubaccountGet")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **kotlin.String**| The currency symbol | [enum: BTC, ETH]
 **amount** | **java.math.BigDecimal**| Amount of funds to be transferred |
 **destination** | **kotlin.Int**| Id of destination subaccount |

### Return type

[**kotlin.Any**](kotlin.Any.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="privateSubmitTransferToUserGet"></a>
# **privateSubmitTransferToUserGet**
> kotlin.Any privateSubmitTransferToUserGet(currency, amount, destination, tfa)

Transfer funds to a another user.

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = PrivateApi()
val currency : kotlin.String = currency_example // kotlin.String | The currency symbol
val amount : java.math.BigDecimal = 8.14 // java.math.BigDecimal | Amount of funds to be transferred
val destination : kotlin.String = destination_example // kotlin.String | Destination address from address book
val tfa : kotlin.String = tfa_example // kotlin.String | TFA code, required when TFA is enabled for current account
try {
    val result : kotlin.Any = apiInstance.privateSubmitTransferToUserGet(currency, amount, destination, tfa)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling PrivateApi#privateSubmitTransferToUserGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling PrivateApi#privateSubmitTransferToUserGet")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **kotlin.String**| The currency symbol | [enum: BTC, ETH]
 **amount** | **java.math.BigDecimal**| Amount of funds to be transferred |
 **destination** | **kotlin.String**| Destination address from address book |
 **tfa** | **kotlin.String**| TFA code, required when TFA is enabled for current account | [optional]

### Return type

[**kotlin.Any**](kotlin.Any.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="privateToggleDepositAddressCreationGet"></a>
# **privateToggleDepositAddressCreationGet**
> kotlin.Any privateToggleDepositAddressCreationGet(currency, state)

Enable or disable deposit address creation

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = PrivateApi()
val currency : kotlin.String = currency_example // kotlin.String | The currency symbol
val state : kotlin.Boolean = true // kotlin.Boolean | 
try {
    val result : kotlin.Any = apiInstance.privateToggleDepositAddressCreationGet(currency, state)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling PrivateApi#privateToggleDepositAddressCreationGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling PrivateApi#privateToggleDepositAddressCreationGet")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **kotlin.String**| The currency symbol | [enum: BTC, ETH]
 **state** | **kotlin.Boolean**|  |

### Return type

[**kotlin.Any**](kotlin.Any.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="privateToggleNotificationsFromSubaccountGet"></a>
# **privateToggleNotificationsFromSubaccountGet**
> kotlin.Any privateToggleNotificationsFromSubaccountGet(sid, state)

Enable or disable sending of notifications for the subaccount.

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = PrivateApi()
val sid : kotlin.Int = 56 // kotlin.Int | The user id for the subaccount
val state : kotlin.Boolean = true // kotlin.Boolean | enable (`true`) or disable (`false`) notifications
try {
    val result : kotlin.Any = apiInstance.privateToggleNotificationsFromSubaccountGet(sid, state)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling PrivateApi#privateToggleNotificationsFromSubaccountGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling PrivateApi#privateToggleNotificationsFromSubaccountGet")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **sid** | **kotlin.Int**| The user id for the subaccount |
 **state** | **kotlin.Boolean**| enable (&#x60;true&#x60;) or disable (&#x60;false&#x60;) notifications |

### Return type

[**kotlin.Any**](kotlin.Any.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="privateToggleSubaccountLoginGet"></a>
# **privateToggleSubaccountLoginGet**
> kotlin.Any privateToggleSubaccountLoginGet(sid, state)

Enable or disable login for a subaccount. If login is disabled and a session for the subaccount exists, this session will be terminated.

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = PrivateApi()
val sid : kotlin.Int = 56 // kotlin.Int | The user id for the subaccount
val state : kotlin.String = state_example // kotlin.String | enable or disable login.
try {
    val result : kotlin.Any = apiInstance.privateToggleSubaccountLoginGet(sid, state)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling PrivateApi#privateToggleSubaccountLoginGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling PrivateApi#privateToggleSubaccountLoginGet")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **sid** | **kotlin.Int**| The user id for the subaccount |
 **state** | **kotlin.String**| enable or disable login. | [enum: enable, disable]

### Return type

[**kotlin.Any**](kotlin.Any.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="privateWithdrawGet"></a>
# **privateWithdrawGet**
> kotlin.Any privateWithdrawGet(currency, address, amount, priority, tfa)

Creates a new withdrawal request

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = PrivateApi()
val currency : kotlin.String = currency_example // kotlin.String | The currency symbol
val address : kotlin.String = address_example // kotlin.String | Address in currency format, it must be in address book
val amount : java.math.BigDecimal = 8.14 // java.math.BigDecimal | Amount of funds to be withdrawn
val priority : kotlin.String = priority_example // kotlin.String | Withdrawal priority, optional for BTC, default: `high`
val tfa : kotlin.String = tfa_example // kotlin.String | TFA code, required when TFA is enabled for current account
try {
    val result : kotlin.Any = apiInstance.privateWithdrawGet(currency, address, amount, priority, tfa)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling PrivateApi#privateWithdrawGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling PrivateApi#privateWithdrawGet")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **kotlin.String**| The currency symbol | [enum: BTC, ETH]
 **address** | **kotlin.String**| Address in currency format, it must be in address book |
 **amount** | **java.math.BigDecimal**| Amount of funds to be withdrawn |
 **priority** | **kotlin.String**| Withdrawal priority, optional for BTC, default: &#x60;high&#x60; | [optional] [enum: insane, extreme_high, very_high, high, mid, low, very_low]
 **tfa** | **kotlin.String**| TFA code, required when TFA is enabled for current account | [optional]

### Return type

[**kotlin.Any**](kotlin.Any.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

