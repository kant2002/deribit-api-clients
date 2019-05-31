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



## privateAddToAddressBookGet

> Object privateAddToAddressBookGet(currency, type, address, name, tfa)

Adds new entry to address book of given type

### Example

```java
// Import classes:
//import org.openapitools.client.api.PrivateApi;

PrivateApi apiInstance = new PrivateApi();
String currency = null; // String | The currency symbol
String type = null; // String | Address book type
String address = null; // String | Address in currency format, it must be in address book
String name = Main address; // String | Name of address book entry
String tfa = null; // String | TFA code, required when TFA is enabled for current account
try {
    Object result = apiInstance.privateAddToAddressBookGet(currency, type, address, name, tfa);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling PrivateApi#privateAddToAddressBookGet");
    e.printStackTrace();
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **String**| The currency symbol | [default to null] [enum: BTC, ETH]
 **type** | **String**| Address book type | [default to null] [enum: transfer, withdrawal]
 **address** | **String**| Address in currency format, it must be in address book | [default to null]
 **name** | **String**| Name of address book entry | [default to null]
 **tfa** | **String**| TFA code, required when TFA is enabled for current account | [optional] [default to null]

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## privateBuyGet

> Object privateBuyGet(instrumentName, amount, type, label, price, timeInForce, maxShow, postOnly, reduceOnly, stopPrice, trigger, advanced)

Places a buy order for an instrument.

### Example

```java
// Import classes:
//import org.openapitools.client.api.PrivateApi;

PrivateApi apiInstance = new PrivateApi();
String instrumentName = BTC-PERPETUAL; // String | Instrument name
BigDecimal amount = null; // BigDecimal | It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH
String type = null; // String | The order type, default: `\"limit\"`
String label = null; // String | user defined label for the order (maximum 32 characters)
BigDecimal price = null; // BigDecimal | <p>The order price in base currency (Only for limit and stop_limit orders)</p> <p>When adding order with advanced=usd, the field price should be the option price value in USD.</p> <p>When adding order with advanced=implv, the field price should be a value of implied volatility in percentages. For example,  price=100, means implied volatility of 100%</p>
String timeInForce = good_til_cancelled; // String | <p>Specifies how long the order remains in effect. Default `\"good_til_cancelled\"`</p> <ul> <li>`\"good_til_cancelled\"` - unfilled order remains in order book until cancelled</li> <li>`\"fill_or_kill\"` - execute a transaction immediately and completely or not at all</li> <li>`\"immediate_or_cancel\"` - execute a transaction immediately, and any portion of the order that cannot be immediately filled is cancelled</li> </ul>
BigDecimal maxShow = 1; // BigDecimal | Maximum amount within an order to be shown to other customers, `0` for invisible order
Boolean postOnly = true; // Boolean | <p>If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.</p> <p>Only valid in combination with time_in_force=`\"good_til_cancelled\"`</p>
Boolean reduceOnly = false; // Boolean | If `true`, the order is considered reduce-only which is intended to only reduce a current position
BigDecimal stopPrice = null; // BigDecimal | Stop price, required for stop limit orders (Only for stop orders)
String trigger = null; // String | Defines trigger type, required for `\"stop_limit\"` order type
String advanced = null; // String | Advanced option order type. (Only for options)
try {
    Object result = apiInstance.privateBuyGet(instrumentName, amount, type, label, price, timeInForce, maxShow, postOnly, reduceOnly, stopPrice, trigger, advanced);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling PrivateApi#privateBuyGet");
    e.printStackTrace();
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrumentName** | **String**| Instrument name | [default to null]
 **amount** | **BigDecimal**| It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH | [default to null]
 **type** | **String**| The order type, default: &#x60;\&quot;limit\&quot;&#x60; | [optional] [default to null] [enum: limit, stop_limit, market, stop_market]
 **label** | **String**| user defined label for the order (maximum 32 characters) | [optional] [default to null]
 **price** | **BigDecimal**| &lt;p&gt;The order price in base currency (Only for limit and stop_limit orders)&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;usd, the field price should be the option price value in USD.&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;implv, the field price should be a value of implied volatility in percentages. For example,  price&#x3D;100, means implied volatility of 100%&lt;/p&gt; | [optional] [default to null]
 **timeInForce** | **String**| &lt;p&gt;Specifies how long the order remains in effect. Default &#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; &lt;ul&gt; &lt;li&gt;&#x60;\&quot;good_til_cancelled\&quot;&#x60; - unfilled order remains in order book until cancelled&lt;/li&gt; &lt;li&gt;&#x60;\&quot;fill_or_kill\&quot;&#x60; - execute a transaction immediately and completely or not at all&lt;/li&gt; &lt;li&gt;&#x60;\&quot;immediate_or_cancel\&quot;&#x60; - execute a transaction immediately, and any portion of the order that cannot be immediately filled is cancelled&lt;/li&gt; &lt;/ul&gt; | [optional] [default to good_til_cancelled] [enum: good_til_cancelled, fill_or_kill, immediate_or_cancel]
 **maxShow** | **BigDecimal**| Maximum amount within an order to be shown to other customers, &#x60;0&#x60; for invisible order | [optional] [default to 1]
 **postOnly** | **Boolean**| &lt;p&gt;If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.&lt;/p&gt; &lt;p&gt;Only valid in combination with time_in_force&#x3D;&#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; | [optional] [default to true]
 **reduceOnly** | **Boolean**| If &#x60;true&#x60;, the order is considered reduce-only which is intended to only reduce a current position | [optional] [default to false]
 **stopPrice** | **BigDecimal**| Stop price, required for stop limit orders (Only for stop orders) | [optional] [default to null]
 **trigger** | **String**| Defines trigger type, required for &#x60;\&quot;stop_limit\&quot;&#x60; order type | [optional] [default to null] [enum: index_price, mark_price, last_price]
 **advanced** | **String**| Advanced option order type. (Only for options) | [optional] [default to null] [enum: usd, implv]

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## privateCancelAllByCurrencyGet

> Object privateCancelAllByCurrencyGet(currency, kind, type)

Cancels all orders by currency, optionally filtered by instrument kind and/or order type.

### Example

```java
// Import classes:
//import org.openapitools.client.api.PrivateApi;

PrivateApi apiInstance = new PrivateApi();
String currency = null; // String | The currency symbol
String kind = null; // String | Instrument kind, if not provided instruments of all kinds are considered
String type = null; // String | Order type - limit, stop or all, default - `all`
try {
    Object result = apiInstance.privateCancelAllByCurrencyGet(currency, kind, type);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling PrivateApi#privateCancelAllByCurrencyGet");
    e.printStackTrace();
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **String**| The currency symbol | [default to null] [enum: BTC, ETH]
 **kind** | **String**| Instrument kind, if not provided instruments of all kinds are considered | [optional] [default to null] [enum: future, option]
 **type** | **String**| Order type - limit, stop or all, default - &#x60;all&#x60; | [optional] [default to null] [enum: all, limit, stop]

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## privateCancelAllByInstrumentGet

> Object privateCancelAllByInstrumentGet(instrumentName, type)

Cancels all orders by instrument, optionally filtered by order type.

### Example

```java
// Import classes:
//import org.openapitools.client.api.PrivateApi;

PrivateApi apiInstance = new PrivateApi();
String instrumentName = BTC-PERPETUAL; // String | Instrument name
String type = null; // String | Order type - limit, stop or all, default - `all`
try {
    Object result = apiInstance.privateCancelAllByInstrumentGet(instrumentName, type);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling PrivateApi#privateCancelAllByInstrumentGet");
    e.printStackTrace();
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrumentName** | **String**| Instrument name | [default to null]
 **type** | **String**| Order type - limit, stop or all, default - &#x60;all&#x60; | [optional] [default to null] [enum: all, limit, stop]

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## privateCancelAllGet

> Object privateCancelAllGet()

This method cancels all users orders and stop orders within all currencies and instrument kinds.

### Example

```java
// Import classes:
//import org.openapitools.client.api.PrivateApi;

PrivateApi apiInstance = new PrivateApi();
try {
    Object result = apiInstance.privateCancelAllGet();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling PrivateApi#privateCancelAllGet");
    e.printStackTrace();
}
```

### Parameters

This endpoint does not need any parameter.

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## privateCancelGet

> Object privateCancelGet(orderId)

Cancel an order, specified by order id

### Example

```java
// Import classes:
//import org.openapitools.client.api.PrivateApi;

PrivateApi apiInstance = new PrivateApi();
String orderId = ETH-100234; // String | The order id
try {
    Object result = apiInstance.privateCancelGet(orderId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling PrivateApi#privateCancelGet");
    e.printStackTrace();
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **orderId** | **String**| The order id | [default to null]

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## privateCancelTransferByIdGet

> Object privateCancelTransferByIdGet(currency, id, tfa)

Cancel transfer

### Example

```java
// Import classes:
//import org.openapitools.client.api.PrivateApi;

PrivateApi apiInstance = new PrivateApi();
String currency = null; // String | The currency symbol
Integer id = 12; // Integer | Id of transfer
String tfa = null; // String | TFA code, required when TFA is enabled for current account
try {
    Object result = apiInstance.privateCancelTransferByIdGet(currency, id, tfa);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling PrivateApi#privateCancelTransferByIdGet");
    e.printStackTrace();
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **String**| The currency symbol | [default to null] [enum: BTC, ETH]
 **id** | **Integer**| Id of transfer | [default to null]
 **tfa** | **String**| TFA code, required when TFA is enabled for current account | [optional] [default to null]

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## privateCancelWithdrawalGet

> Object privateCancelWithdrawalGet(currency, id)

Cancels withdrawal request

### Example

```java
// Import classes:
//import org.openapitools.client.api.PrivateApi;

PrivateApi apiInstance = new PrivateApi();
String currency = null; // String | The currency symbol
BigDecimal id = 1; // BigDecimal | The withdrawal id
try {
    Object result = apiInstance.privateCancelWithdrawalGet(currency, id);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling PrivateApi#privateCancelWithdrawalGet");
    e.printStackTrace();
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **String**| The currency symbol | [default to null] [enum: BTC, ETH]
 **id** | **BigDecimal**| The withdrawal id | [default to null]

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## privateChangeSubaccountNameGet

> Object privateChangeSubaccountNameGet(sid, name)

Change the user name for a subaccount

### Example

```java
// Import classes:
//import org.openapitools.client.api.PrivateApi;

PrivateApi apiInstance = new PrivateApi();
Integer sid = null; // Integer | The user id for the subaccount
String name = newUserName; // String | The new user name
try {
    Object result = apiInstance.privateChangeSubaccountNameGet(sid, name);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling PrivateApi#privateChangeSubaccountNameGet");
    e.printStackTrace();
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **sid** | **Integer**| The user id for the subaccount | [default to null]
 **name** | **String**| The new user name | [default to null]

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## privateClosePositionGet

> Object privateClosePositionGet(instrumentName, type, price)

Makes closing position reduce only order .

### Example

```java
// Import classes:
//import org.openapitools.client.api.PrivateApi;

PrivateApi apiInstance = new PrivateApi();
String instrumentName = BTC-PERPETUAL; // String | Instrument name
String type = null; // String | The order type
BigDecimal price = null; // BigDecimal | Optional price for limit order.
try {
    Object result = apiInstance.privateClosePositionGet(instrumentName, type, price);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling PrivateApi#privateClosePositionGet");
    e.printStackTrace();
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrumentName** | **String**| Instrument name | [default to null]
 **type** | **String**| The order type | [default to null] [enum: limit, market]
 **price** | **BigDecimal**| Optional price for limit order. | [optional] [default to null]

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## privateCreateDepositAddressGet

> Object privateCreateDepositAddressGet(currency)

Creates deposit address in currency

### Example

```java
// Import classes:
//import org.openapitools.client.api.PrivateApi;

PrivateApi apiInstance = new PrivateApi();
String currency = null; // String | The currency symbol
try {
    Object result = apiInstance.privateCreateDepositAddressGet(currency);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling PrivateApi#privateCreateDepositAddressGet");
    e.printStackTrace();
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **String**| The currency symbol | [default to null] [enum: BTC, ETH]

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## privateCreateSubaccountGet

> Object privateCreateSubaccountGet()

Create a new subaccount

### Example

```java
// Import classes:
//import org.openapitools.client.api.PrivateApi;

PrivateApi apiInstance = new PrivateApi();
try {
    Object result = apiInstance.privateCreateSubaccountGet();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling PrivateApi#privateCreateSubaccountGet");
    e.printStackTrace();
}
```

### Parameters

This endpoint does not need any parameter.

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## privateDisableTfaForSubaccountGet

> Object privateDisableTfaForSubaccountGet(sid)

Disable two factor authentication for a subaccount.

### Example

```java
// Import classes:
//import org.openapitools.client.api.PrivateApi;

PrivateApi apiInstance = new PrivateApi();
Integer sid = null; // Integer | The user id for the subaccount
try {
    Object result = apiInstance.privateDisableTfaForSubaccountGet(sid);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling PrivateApi#privateDisableTfaForSubaccountGet");
    e.printStackTrace();
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **sid** | **Integer**| The user id for the subaccount | [default to null]

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## privateDisableTfaWithRecoveryCodeGet

> Object privateDisableTfaWithRecoveryCodeGet(password, code)

Disables TFA with one time recovery code

### Example

```java
// Import classes:
//import org.openapitools.client.api.PrivateApi;

PrivateApi apiInstance = new PrivateApi();
String password = null; // String | The password for the subaccount
String code = null; // String | One time recovery code
try {
    Object result = apiInstance.privateDisableTfaWithRecoveryCodeGet(password, code);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling PrivateApi#privateDisableTfaWithRecoveryCodeGet");
    e.printStackTrace();
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **password** | **String**| The password for the subaccount | [default to null]
 **code** | **String**| One time recovery code | [default to null]

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## privateEditGet

> Object privateEditGet(orderId, amount, price, postOnly, advanced, stopPrice)

Change price, amount and/or other properties of an order.

### Example

```java
// Import classes:
//import org.openapitools.client.api.PrivateApi;

PrivateApi apiInstance = new PrivateApi();
String orderId = ETH-100234; // String | The order id
BigDecimal amount = null; // BigDecimal | It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH
BigDecimal price = null; // BigDecimal | <p>The order price in base currency.</p> <p>When editing an option order with advanced=usd, the field price should be the option price value in USD.</p> <p>When editing an option order with advanced=implv, the field price should be a value of implied volatility in percentages. For example,  price=100, means implied volatility of 100%</p>
Boolean postOnly = true; // Boolean | <p>If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.</p> <p>Only valid in combination with time_in_force=`\"good_til_cancelled\"`</p>
String advanced = null; // String | Advanced option order type. If you have posted an advanced option order, it is necessary to re-supply this parameter when editing it (Only for options)
BigDecimal stopPrice = null; // BigDecimal | Stop price, required for stop limit orders (Only for stop orders)
try {
    Object result = apiInstance.privateEditGet(orderId, amount, price, postOnly, advanced, stopPrice);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling PrivateApi#privateEditGet");
    e.printStackTrace();
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **orderId** | **String**| The order id | [default to null]
 **amount** | **BigDecimal**| It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH | [default to null]
 **price** | **BigDecimal**| &lt;p&gt;The order price in base currency.&lt;/p&gt; &lt;p&gt;When editing an option order with advanced&#x3D;usd, the field price should be the option price value in USD.&lt;/p&gt; &lt;p&gt;When editing an option order with advanced&#x3D;implv, the field price should be a value of implied volatility in percentages. For example,  price&#x3D;100, means implied volatility of 100%&lt;/p&gt; | [default to null]
 **postOnly** | **Boolean**| &lt;p&gt;If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.&lt;/p&gt; &lt;p&gt;Only valid in combination with time_in_force&#x3D;&#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; | [optional] [default to true]
 **advanced** | **String**| Advanced option order type. If you have posted an advanced option order, it is necessary to re-supply this parameter when editing it (Only for options) | [optional] [default to null] [enum: usd, implv]
 **stopPrice** | **BigDecimal**| Stop price, required for stop limit orders (Only for stop orders) | [optional] [default to null]

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## privateGetAccountSummaryGet

> Object privateGetAccountSummaryGet(currency, extended)

Retrieves user account summary.

### Example

```java
// Import classes:
//import org.openapitools.client.api.PrivateApi;

PrivateApi apiInstance = new PrivateApi();
String currency = null; // String | The currency symbol
Boolean extended = false; // Boolean | Include additional fields
try {
    Object result = apiInstance.privateGetAccountSummaryGet(currency, extended);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling PrivateApi#privateGetAccountSummaryGet");
    e.printStackTrace();
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **String**| The currency symbol | [default to null] [enum: BTC, ETH]
 **extended** | **Boolean**| Include additional fields | [optional] [default to null]

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## privateGetAddressBookGet

> Object privateGetAddressBookGet(currency, type)

Retrieves address book of given type

### Example

```java
// Import classes:
//import org.openapitools.client.api.PrivateApi;

PrivateApi apiInstance = new PrivateApi();
String currency = null; // String | The currency symbol
String type = null; // String | Address book type
try {
    Object result = apiInstance.privateGetAddressBookGet(currency, type);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling PrivateApi#privateGetAddressBookGet");
    e.printStackTrace();
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **String**| The currency symbol | [default to null] [enum: BTC, ETH]
 **type** | **String**| Address book type | [default to null] [enum: transfer, withdrawal]

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## privateGetCurrentDepositAddressGet

> Object privateGetCurrentDepositAddressGet(currency)

Retrieve deposit address for currency

### Example

```java
// Import classes:
//import org.openapitools.client.api.PrivateApi;

PrivateApi apiInstance = new PrivateApi();
String currency = null; // String | The currency symbol
try {
    Object result = apiInstance.privateGetCurrentDepositAddressGet(currency);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling PrivateApi#privateGetCurrentDepositAddressGet");
    e.printStackTrace();
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **String**| The currency symbol | [default to null] [enum: BTC, ETH]

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## privateGetDepositsGet

> Object privateGetDepositsGet(currency, count, offset)

Retrieve the latest users deposits

### Example

```java
// Import classes:
//import org.openapitools.client.api.PrivateApi;

PrivateApi apiInstance = new PrivateApi();
String currency = null; // String | The currency symbol
Integer count = null; // Integer | Number of requested items, default - `10`
Integer offset = 10; // Integer | The offset for pagination, default - `0`
try {
    Object result = apiInstance.privateGetDepositsGet(currency, count, offset);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling PrivateApi#privateGetDepositsGet");
    e.printStackTrace();
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **String**| The currency symbol | [default to null] [enum: BTC, ETH]
 **count** | **Integer**| Number of requested items, default - &#x60;10&#x60; | [optional] [default to null]
 **offset** | **Integer**| The offset for pagination, default - &#x60;0&#x60; | [optional] [default to null]

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## privateGetEmailLanguageGet

> Object privateGetEmailLanguageGet()

Retrieves the language to be used for emails.

### Example

```java
// Import classes:
//import org.openapitools.client.api.PrivateApi;

PrivateApi apiInstance = new PrivateApi();
try {
    Object result = apiInstance.privateGetEmailLanguageGet();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling PrivateApi#privateGetEmailLanguageGet");
    e.printStackTrace();
}
```

### Parameters

This endpoint does not need any parameter.

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## privateGetMarginsGet

> Object privateGetMarginsGet(instrumentName, amount, price)

Get margins for given instrument, amount and price.

### Example

```java
// Import classes:
//import org.openapitools.client.api.PrivateApi;

PrivateApi apiInstance = new PrivateApi();
String instrumentName = BTC-PERPETUAL; // String | Instrument name
BigDecimal amount = 1; // BigDecimal | Amount, integer for future, float for option. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH.
BigDecimal price = null; // BigDecimal | Price
try {
    Object result = apiInstance.privateGetMarginsGet(instrumentName, amount, price);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling PrivateApi#privateGetMarginsGet");
    e.printStackTrace();
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrumentName** | **String**| Instrument name | [default to null]
 **amount** | **BigDecimal**| Amount, integer for future, float for option. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH. | [default to null]
 **price** | **BigDecimal**| Price | [default to null]

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## privateGetNewAnnouncementsGet

> Object privateGetNewAnnouncementsGet()

Retrieves announcements that have not been marked read by the user.

### Example

```java
// Import classes:
//import org.openapitools.client.api.PrivateApi;

PrivateApi apiInstance = new PrivateApi();
try {
    Object result = apiInstance.privateGetNewAnnouncementsGet();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling PrivateApi#privateGetNewAnnouncementsGet");
    e.printStackTrace();
}
```

### Parameters

This endpoint does not need any parameter.

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## privateGetOpenOrdersByCurrencyGet

> Object privateGetOpenOrdersByCurrencyGet(currency, kind, type)

Retrieves list of user&#39;s open orders.

### Example

```java
// Import classes:
//import org.openapitools.client.api.PrivateApi;

PrivateApi apiInstance = new PrivateApi();
String currency = null; // String | The currency symbol
String kind = null; // String | Instrument kind, if not provided instruments of all kinds are considered
String type = null; // String | Order type, default - `all`
try {
    Object result = apiInstance.privateGetOpenOrdersByCurrencyGet(currency, kind, type);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling PrivateApi#privateGetOpenOrdersByCurrencyGet");
    e.printStackTrace();
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **String**| The currency symbol | [default to null] [enum: BTC, ETH]
 **kind** | **String**| Instrument kind, if not provided instruments of all kinds are considered | [optional] [default to null] [enum: future, option]
 **type** | **String**| Order type, default - &#x60;all&#x60; | [optional] [default to null] [enum: all, limit, stop_all, stop_limit, stop_market]

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## privateGetOpenOrdersByInstrumentGet

> Object privateGetOpenOrdersByInstrumentGet(instrumentName, type)

Retrieves list of user&#39;s open orders within given Instrument.

### Example

```java
// Import classes:
//import org.openapitools.client.api.PrivateApi;

PrivateApi apiInstance = new PrivateApi();
String instrumentName = BTC-PERPETUAL; // String | Instrument name
String type = null; // String | Order type, default - `all`
try {
    Object result = apiInstance.privateGetOpenOrdersByInstrumentGet(instrumentName, type);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling PrivateApi#privateGetOpenOrdersByInstrumentGet");
    e.printStackTrace();
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrumentName** | **String**| Instrument name | [default to null]
 **type** | **String**| Order type, default - &#x60;all&#x60; | [optional] [default to null] [enum: all, limit, stop_all, stop_limit, stop_market]

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## privateGetOrderHistoryByCurrencyGet

> Object privateGetOrderHistoryByCurrencyGet(currency, kind, count, offset, includeOld, includeUnfilled)

Retrieves history of orders that have been partially or fully filled.

### Example

```java
// Import classes:
//import org.openapitools.client.api.PrivateApi;

PrivateApi apiInstance = new PrivateApi();
String currency = null; // String | The currency symbol
String kind = null; // String | Instrument kind, if not provided instruments of all kinds are considered
Integer count = null; // Integer | Number of requested items, default - `20`
Integer offset = 10; // Integer | The offset for pagination, default - `0`
Boolean includeOld = false; // Boolean | Include in result orders older than 2 days, default - `false`
Boolean includeUnfilled = false; // Boolean | Include in result fully unfilled closed orders, default - `false`
try {
    Object result = apiInstance.privateGetOrderHistoryByCurrencyGet(currency, kind, count, offset, includeOld, includeUnfilled);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling PrivateApi#privateGetOrderHistoryByCurrencyGet");
    e.printStackTrace();
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **String**| The currency symbol | [default to null] [enum: BTC, ETH]
 **kind** | **String**| Instrument kind, if not provided instruments of all kinds are considered | [optional] [default to null] [enum: future, option]
 **count** | **Integer**| Number of requested items, default - &#x60;20&#x60; | [optional] [default to null]
 **offset** | **Integer**| The offset for pagination, default - &#x60;0&#x60; | [optional] [default to null]
 **includeOld** | **Boolean**| Include in result orders older than 2 days, default - &#x60;false&#x60; | [optional] [default to null]
 **includeUnfilled** | **Boolean**| Include in result fully unfilled closed orders, default - &#x60;false&#x60; | [optional] [default to null]

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## privateGetOrderHistoryByInstrumentGet

> Object privateGetOrderHistoryByInstrumentGet(instrumentName, count, offset, includeOld, includeUnfilled)

Retrieves history of orders that have been partially or fully filled.

### Example

```java
// Import classes:
//import org.openapitools.client.api.PrivateApi;

PrivateApi apiInstance = new PrivateApi();
String instrumentName = BTC-PERPETUAL; // String | Instrument name
Integer count = null; // Integer | Number of requested items, default - `20`
Integer offset = 10; // Integer | The offset for pagination, default - `0`
Boolean includeOld = false; // Boolean | Include in result orders older than 2 days, default - `false`
Boolean includeUnfilled = false; // Boolean | Include in result fully unfilled closed orders, default - `false`
try {
    Object result = apiInstance.privateGetOrderHistoryByInstrumentGet(instrumentName, count, offset, includeOld, includeUnfilled);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling PrivateApi#privateGetOrderHistoryByInstrumentGet");
    e.printStackTrace();
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrumentName** | **String**| Instrument name | [default to null]
 **count** | **Integer**| Number of requested items, default - &#x60;20&#x60; | [optional] [default to null]
 **offset** | **Integer**| The offset for pagination, default - &#x60;0&#x60; | [optional] [default to null]
 **includeOld** | **Boolean**| Include in result orders older than 2 days, default - &#x60;false&#x60; | [optional] [default to null]
 **includeUnfilled** | **Boolean**| Include in result fully unfilled closed orders, default - &#x60;false&#x60; | [optional] [default to null]

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## privateGetOrderMarginByIdsGet

> Object privateGetOrderMarginByIdsGet(ids)

Retrieves initial margins of given orders

### Example

```java
// Import classes:
//import org.openapitools.client.api.PrivateApi;

PrivateApi apiInstance = new PrivateApi();
List<String> ids = null; // List<String> | Ids of orders
try {
    Object result = apiInstance.privateGetOrderMarginByIdsGet(ids);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling PrivateApi#privateGetOrderMarginByIdsGet");
    e.printStackTrace();
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **ids** | [**List&lt;String&gt;**](String.md)| Ids of orders | [default to null]

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## privateGetOrderStateGet

> Object privateGetOrderStateGet(orderId)

Retrieve the current state of an order.

### Example

```java
// Import classes:
//import org.openapitools.client.api.PrivateApi;

PrivateApi apiInstance = new PrivateApi();
String orderId = ETH-100234; // String | The order id
try {
    Object result = apiInstance.privateGetOrderStateGet(orderId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling PrivateApi#privateGetOrderStateGet");
    e.printStackTrace();
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **orderId** | **String**| The order id | [default to null]

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## privateGetPositionGet

> Object privateGetPositionGet(instrumentName)

Retrieve user position.

### Example

```java
// Import classes:
//import org.openapitools.client.api.PrivateApi;

PrivateApi apiInstance = new PrivateApi();
String instrumentName = BTC-PERPETUAL; // String | Instrument name
try {
    Object result = apiInstance.privateGetPositionGet(instrumentName);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling PrivateApi#privateGetPositionGet");
    e.printStackTrace();
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrumentName** | **String**| Instrument name | [default to null]

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## privateGetPositionsGet

> Object privateGetPositionsGet(currency, kind)

Retrieve user positions.

### Example

```java
// Import classes:
//import org.openapitools.client.api.PrivateApi;

PrivateApi apiInstance = new PrivateApi();
String currency = null; // String | 
String kind = null; // String | Kind filter on positions
try {
    Object result = apiInstance.privateGetPositionsGet(currency, kind);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling PrivateApi#privateGetPositionsGet");
    e.printStackTrace();
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **String**|  | [default to null] [enum: BTC, ETH]
 **kind** | **String**| Kind filter on positions | [optional] [default to null] [enum: future, option]

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## privateGetSettlementHistoryByCurrencyGet

> Object privateGetSettlementHistoryByCurrencyGet(currency, type, count)

Retrieves settlement, delivery and bankruptcy events that have affected your account.

### Example

```java
// Import classes:
//import org.openapitools.client.api.PrivateApi;

PrivateApi apiInstance = new PrivateApi();
String currency = null; // String | The currency symbol
String type = null; // String | Settlement type
Integer count = null; // Integer | Number of requested items, default - `20`
try {
    Object result = apiInstance.privateGetSettlementHistoryByCurrencyGet(currency, type, count);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling PrivateApi#privateGetSettlementHistoryByCurrencyGet");
    e.printStackTrace();
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **String**| The currency symbol | [default to null] [enum: BTC, ETH]
 **type** | **String**| Settlement type | [optional] [default to null] [enum: settlement, delivery, bankruptcy]
 **count** | **Integer**| Number of requested items, default - &#x60;20&#x60; | [optional] [default to null]

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## privateGetSettlementHistoryByInstrumentGet

> Object privateGetSettlementHistoryByInstrumentGet(instrumentName, type, count)

Retrieves public settlement, delivery and bankruptcy events filtered by instrument name

### Example

```java
// Import classes:
//import org.openapitools.client.api.PrivateApi;

PrivateApi apiInstance = new PrivateApi();
String instrumentName = BTC-PERPETUAL; // String | Instrument name
String type = null; // String | Settlement type
Integer count = null; // Integer | Number of requested items, default - `20`
try {
    Object result = apiInstance.privateGetSettlementHistoryByInstrumentGet(instrumentName, type, count);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling PrivateApi#privateGetSettlementHistoryByInstrumentGet");
    e.printStackTrace();
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrumentName** | **String**| Instrument name | [default to null]
 **type** | **String**| Settlement type | [optional] [default to null] [enum: settlement, delivery, bankruptcy]
 **count** | **Integer**| Number of requested items, default - &#x60;20&#x60; | [optional] [default to null]

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## privateGetSubaccountsGet

> Object privateGetSubaccountsGet(withPortfolio)

Get information about subaccounts

### Example

```java
// Import classes:
//import org.openapitools.client.api.PrivateApi;

PrivateApi apiInstance = new PrivateApi();
Boolean withPortfolio = null; // Boolean | 
try {
    Object result = apiInstance.privateGetSubaccountsGet(withPortfolio);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling PrivateApi#privateGetSubaccountsGet");
    e.printStackTrace();
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **withPortfolio** | **Boolean**|  | [optional] [default to null]

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## privateGetTransfersGet

> Object privateGetTransfersGet(currency, count, offset)

Adds new entry to address book of given type

### Example

```java
// Import classes:
//import org.openapitools.client.api.PrivateApi;

PrivateApi apiInstance = new PrivateApi();
String currency = null; // String | The currency symbol
Integer count = null; // Integer | Number of requested items, default - `10`
Integer offset = 10; // Integer | The offset for pagination, default - `0`
try {
    Object result = apiInstance.privateGetTransfersGet(currency, count, offset);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling PrivateApi#privateGetTransfersGet");
    e.printStackTrace();
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **String**| The currency symbol | [default to null] [enum: BTC, ETH]
 **count** | **Integer**| Number of requested items, default - &#x60;10&#x60; | [optional] [default to null]
 **offset** | **Integer**| The offset for pagination, default - &#x60;0&#x60; | [optional] [default to null]

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## privateGetUserTradesByCurrencyAndTimeGet

> Object privateGetUserTradesByCurrencyAndTimeGet(currency, startTimestamp, endTimestamp, kind, count, includeOld, sorting)

Retrieve the latest user trades that have occurred for instruments in a specific currency symbol and within given time range.

### Example

```java
// Import classes:
//import org.openapitools.client.api.PrivateApi;

PrivateApi apiInstance = new PrivateApi();
String currency = null; // String | The currency symbol
Integer startTimestamp = 1536569522277; // Integer | The earliest timestamp to return result for
Integer endTimestamp = 1536569522277; // Integer | The most recent timestamp to return result for
String kind = null; // String | Instrument kind, if not provided instruments of all kinds are considered
Integer count = null; // Integer | Number of requested items, default - `10`
Boolean includeOld = null; // Boolean | Include trades older than 7 days, default - `false`
String sorting = null; // String | Direction of results sorting (`default` value means no sorting, results will be returned in order in which they left the database)
try {
    Object result = apiInstance.privateGetUserTradesByCurrencyAndTimeGet(currency, startTimestamp, endTimestamp, kind, count, includeOld, sorting);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling PrivateApi#privateGetUserTradesByCurrencyAndTimeGet");
    e.printStackTrace();
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **String**| The currency symbol | [default to null] [enum: BTC, ETH]
 **startTimestamp** | **Integer**| The earliest timestamp to return result for | [default to null]
 **endTimestamp** | **Integer**| The most recent timestamp to return result for | [default to null]
 **kind** | **String**| Instrument kind, if not provided instruments of all kinds are considered | [optional] [default to null] [enum: future, option]
 **count** | **Integer**| Number of requested items, default - &#x60;10&#x60; | [optional] [default to null]
 **includeOld** | **Boolean**| Include trades older than 7 days, default - &#x60;false&#x60; | [optional] [default to null]
 **sorting** | **String**| Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) | [optional] [default to null] [enum: asc, desc, default]

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## privateGetUserTradesByCurrencyGet

> Object privateGetUserTradesByCurrencyGet(currency, kind, startId, endId, count, includeOld, sorting)

Retrieve the latest user trades that have occurred for instruments in a specific currency symbol.

### Example

```java
// Import classes:
//import org.openapitools.client.api.PrivateApi;

PrivateApi apiInstance = new PrivateApi();
String currency = null; // String | The currency symbol
String kind = null; // String | Instrument kind, if not provided instruments of all kinds are considered
String startId = null; // String | The ID number of the first trade to be returned
String endId = null; // String | The ID number of the last trade to be returned
Integer count = null; // Integer | Number of requested items, default - `10`
Boolean includeOld = null; // Boolean | Include trades older than 7 days, default - `false`
String sorting = null; // String | Direction of results sorting (`default` value means no sorting, results will be returned in order in which they left the database)
try {
    Object result = apiInstance.privateGetUserTradesByCurrencyGet(currency, kind, startId, endId, count, includeOld, sorting);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling PrivateApi#privateGetUserTradesByCurrencyGet");
    e.printStackTrace();
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **String**| The currency symbol | [default to null] [enum: BTC, ETH]
 **kind** | **String**| Instrument kind, if not provided instruments of all kinds are considered | [optional] [default to null] [enum: future, option]
 **startId** | **String**| The ID number of the first trade to be returned | [optional] [default to null]
 **endId** | **String**| The ID number of the last trade to be returned | [optional] [default to null]
 **count** | **Integer**| Number of requested items, default - &#x60;10&#x60; | [optional] [default to null]
 **includeOld** | **Boolean**| Include trades older than 7 days, default - &#x60;false&#x60; | [optional] [default to null]
 **sorting** | **String**| Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) | [optional] [default to null] [enum: asc, desc, default]

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## privateGetUserTradesByInstrumentAndTimeGet

> Object privateGetUserTradesByInstrumentAndTimeGet(instrumentName, startTimestamp, endTimestamp, count, includeOld, sorting)

Retrieve the latest user trades that have occurred for a specific instrument and within given time range.

### Example

```java
// Import classes:
//import org.openapitools.client.api.PrivateApi;

PrivateApi apiInstance = new PrivateApi();
String instrumentName = BTC-PERPETUAL; // String | Instrument name
Integer startTimestamp = 1536569522277; // Integer | The earliest timestamp to return result for
Integer endTimestamp = 1536569522277; // Integer | The most recent timestamp to return result for
Integer count = null; // Integer | Number of requested items, default - `10`
Boolean includeOld = null; // Boolean | Include trades older than 7 days, default - `false`
String sorting = null; // String | Direction of results sorting (`default` value means no sorting, results will be returned in order in which they left the database)
try {
    Object result = apiInstance.privateGetUserTradesByInstrumentAndTimeGet(instrumentName, startTimestamp, endTimestamp, count, includeOld, sorting);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling PrivateApi#privateGetUserTradesByInstrumentAndTimeGet");
    e.printStackTrace();
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrumentName** | **String**| Instrument name | [default to null]
 **startTimestamp** | **Integer**| The earliest timestamp to return result for | [default to null]
 **endTimestamp** | **Integer**| The most recent timestamp to return result for | [default to null]
 **count** | **Integer**| Number of requested items, default - &#x60;10&#x60; | [optional] [default to null]
 **includeOld** | **Boolean**| Include trades older than 7 days, default - &#x60;false&#x60; | [optional] [default to null]
 **sorting** | **String**| Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) | [optional] [default to null] [enum: asc, desc, default]

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## privateGetUserTradesByInstrumentGet

> Object privateGetUserTradesByInstrumentGet(instrumentName, startSeq, endSeq, count, includeOld, sorting)

Retrieve the latest user trades that have occurred for a specific instrument.

### Example

```java
// Import classes:
//import org.openapitools.client.api.PrivateApi;

PrivateApi apiInstance = new PrivateApi();
String instrumentName = BTC-PERPETUAL; // String | Instrument name
Integer startSeq = null; // Integer | The sequence number of the first trade to be returned
Integer endSeq = null; // Integer | The sequence number of the last trade to be returned
Integer count = null; // Integer | Number of requested items, default - `10`
Boolean includeOld = null; // Boolean | Include trades older than 7 days, default - `false`
String sorting = null; // String | Direction of results sorting (`default` value means no sorting, results will be returned in order in which they left the database)
try {
    Object result = apiInstance.privateGetUserTradesByInstrumentGet(instrumentName, startSeq, endSeq, count, includeOld, sorting);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling PrivateApi#privateGetUserTradesByInstrumentGet");
    e.printStackTrace();
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrumentName** | **String**| Instrument name | [default to null]
 **startSeq** | **Integer**| The sequence number of the first trade to be returned | [optional] [default to null]
 **endSeq** | **Integer**| The sequence number of the last trade to be returned | [optional] [default to null]
 **count** | **Integer**| Number of requested items, default - &#x60;10&#x60; | [optional] [default to null]
 **includeOld** | **Boolean**| Include trades older than 7 days, default - &#x60;false&#x60; | [optional] [default to null]
 **sorting** | **String**| Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) | [optional] [default to null] [enum: asc, desc, default]

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## privateGetUserTradesByOrderGet

> Object privateGetUserTradesByOrderGet(orderId, sorting)

Retrieve the list of user trades that was created for given order

### Example

```java
// Import classes:
//import org.openapitools.client.api.PrivateApi;

PrivateApi apiInstance = new PrivateApi();
String orderId = ETH-100234; // String | The order id
String sorting = null; // String | Direction of results sorting (`default` value means no sorting, results will be returned in order in which they left the database)
try {
    Object result = apiInstance.privateGetUserTradesByOrderGet(orderId, sorting);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling PrivateApi#privateGetUserTradesByOrderGet");
    e.printStackTrace();
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **orderId** | **String**| The order id | [default to null]
 **sorting** | **String**| Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) | [optional] [default to null] [enum: asc, desc, default]

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## privateGetWithdrawalsGet

> Object privateGetWithdrawalsGet(currency, count, offset)

Retrieve the latest users withdrawals

### Example

```java
// Import classes:
//import org.openapitools.client.api.PrivateApi;

PrivateApi apiInstance = new PrivateApi();
String currency = null; // String | The currency symbol
Integer count = null; // Integer | Number of requested items, default - `10`
Integer offset = 10; // Integer | The offset for pagination, default - `0`
try {
    Object result = apiInstance.privateGetWithdrawalsGet(currency, count, offset);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling PrivateApi#privateGetWithdrawalsGet");
    e.printStackTrace();
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **String**| The currency symbol | [default to null] [enum: BTC, ETH]
 **count** | **Integer**| Number of requested items, default - &#x60;10&#x60; | [optional] [default to null]
 **offset** | **Integer**| The offset for pagination, default - &#x60;0&#x60; | [optional] [default to null]

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## privateRemoveFromAddressBookGet

> Object privateRemoveFromAddressBookGet(currency, type, address, tfa)

Adds new entry to address book of given type

### Example

```java
// Import classes:
//import org.openapitools.client.api.PrivateApi;

PrivateApi apiInstance = new PrivateApi();
String currency = null; // String | The currency symbol
String type = null; // String | Address book type
String address = null; // String | Address in currency format, it must be in address book
String tfa = null; // String | TFA code, required when TFA is enabled for current account
try {
    Object result = apiInstance.privateRemoveFromAddressBookGet(currency, type, address, tfa);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling PrivateApi#privateRemoveFromAddressBookGet");
    e.printStackTrace();
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **String**| The currency symbol | [default to null] [enum: BTC, ETH]
 **type** | **String**| Address book type | [default to null] [enum: transfer, withdrawal]
 **address** | **String**| Address in currency format, it must be in address book | [default to null]
 **tfa** | **String**| TFA code, required when TFA is enabled for current account | [optional] [default to null]

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## privateSellGet

> Object privateSellGet(instrumentName, amount, type, label, price, timeInForce, maxShow, postOnly, reduceOnly, stopPrice, trigger, advanced)

Places a sell order for an instrument.

### Example

```java
// Import classes:
//import org.openapitools.client.api.PrivateApi;

PrivateApi apiInstance = new PrivateApi();
String instrumentName = BTC-PERPETUAL; // String | Instrument name
BigDecimal amount = null; // BigDecimal | It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH
String type = null; // String | The order type, default: `\"limit\"`
String label = null; // String | user defined label for the order (maximum 32 characters)
BigDecimal price = null; // BigDecimal | <p>The order price in base currency (Only for limit and stop_limit orders)</p> <p>When adding order with advanced=usd, the field price should be the option price value in USD.</p> <p>When adding order with advanced=implv, the field price should be a value of implied volatility in percentages. For example,  price=100, means implied volatility of 100%</p>
String timeInForce = good_til_cancelled; // String | <p>Specifies how long the order remains in effect. Default `\"good_til_cancelled\"`</p> <ul> <li>`\"good_til_cancelled\"` - unfilled order remains in order book until cancelled</li> <li>`\"fill_or_kill\"` - execute a transaction immediately and completely or not at all</li> <li>`\"immediate_or_cancel\"` - execute a transaction immediately, and any portion of the order that cannot be immediately filled is cancelled</li> </ul>
BigDecimal maxShow = 1; // BigDecimal | Maximum amount within an order to be shown to other customers, `0` for invisible order
Boolean postOnly = true; // Boolean | <p>If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.</p> <p>Only valid in combination with time_in_force=`\"good_til_cancelled\"`</p>
Boolean reduceOnly = false; // Boolean | If `true`, the order is considered reduce-only which is intended to only reduce a current position
BigDecimal stopPrice = null; // BigDecimal | Stop price, required for stop limit orders (Only for stop orders)
String trigger = null; // String | Defines trigger type, required for `\"stop_limit\"` order type
String advanced = null; // String | Advanced option order type. (Only for options)
try {
    Object result = apiInstance.privateSellGet(instrumentName, amount, type, label, price, timeInForce, maxShow, postOnly, reduceOnly, stopPrice, trigger, advanced);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling PrivateApi#privateSellGet");
    e.printStackTrace();
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrumentName** | **String**| Instrument name | [default to null]
 **amount** | **BigDecimal**| It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH | [default to null]
 **type** | **String**| The order type, default: &#x60;\&quot;limit\&quot;&#x60; | [optional] [default to null] [enum: limit, stop_limit, market, stop_market]
 **label** | **String**| user defined label for the order (maximum 32 characters) | [optional] [default to null]
 **price** | **BigDecimal**| &lt;p&gt;The order price in base currency (Only for limit and stop_limit orders)&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;usd, the field price should be the option price value in USD.&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;implv, the field price should be a value of implied volatility in percentages. For example,  price&#x3D;100, means implied volatility of 100%&lt;/p&gt; | [optional] [default to null]
 **timeInForce** | **String**| &lt;p&gt;Specifies how long the order remains in effect. Default &#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; &lt;ul&gt; &lt;li&gt;&#x60;\&quot;good_til_cancelled\&quot;&#x60; - unfilled order remains in order book until cancelled&lt;/li&gt; &lt;li&gt;&#x60;\&quot;fill_or_kill\&quot;&#x60; - execute a transaction immediately and completely or not at all&lt;/li&gt; &lt;li&gt;&#x60;\&quot;immediate_or_cancel\&quot;&#x60; - execute a transaction immediately, and any portion of the order that cannot be immediately filled is cancelled&lt;/li&gt; &lt;/ul&gt; | [optional] [default to good_til_cancelled] [enum: good_til_cancelled, fill_or_kill, immediate_or_cancel]
 **maxShow** | **BigDecimal**| Maximum amount within an order to be shown to other customers, &#x60;0&#x60; for invisible order | [optional] [default to 1]
 **postOnly** | **Boolean**| &lt;p&gt;If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.&lt;/p&gt; &lt;p&gt;Only valid in combination with time_in_force&#x3D;&#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; | [optional] [default to true]
 **reduceOnly** | **Boolean**| If &#x60;true&#x60;, the order is considered reduce-only which is intended to only reduce a current position | [optional] [default to false]
 **stopPrice** | **BigDecimal**| Stop price, required for stop limit orders (Only for stop orders) | [optional] [default to null]
 **trigger** | **String**| Defines trigger type, required for &#x60;\&quot;stop_limit\&quot;&#x60; order type | [optional] [default to null] [enum: index_price, mark_price, last_price]
 **advanced** | **String**| Advanced option order type. (Only for options) | [optional] [default to null] [enum: usd, implv]

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## privateSetAnnouncementAsReadGet

> Object privateSetAnnouncementAsReadGet(announcementId)

Marks an announcement as read, so it will not be shown in &#x60;get_new_announcements&#x60;.

### Example

```java
// Import classes:
//import org.openapitools.client.api.PrivateApi;

PrivateApi apiInstance = new PrivateApi();
BigDecimal announcementId = null; // BigDecimal | the ID of the announcement
try {
    Object result = apiInstance.privateSetAnnouncementAsReadGet(announcementId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling PrivateApi#privateSetAnnouncementAsReadGet");
    e.printStackTrace();
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **announcementId** | **BigDecimal**| the ID of the announcement | [default to null]

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## privateSetEmailForSubaccountGet

> Object privateSetEmailForSubaccountGet(sid, email)

Assign an email address to a subaccount. User will receive an email with confirmation link.

### Example

```java
// Import classes:
//import org.openapitools.client.api.PrivateApi;

PrivateApi apiInstance = new PrivateApi();
Integer sid = null; // Integer | The user id for the subaccount
String email = subaccount_1@email.com; // String | The email address for the subaccount
try {
    Object result = apiInstance.privateSetEmailForSubaccountGet(sid, email);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling PrivateApi#privateSetEmailForSubaccountGet");
    e.printStackTrace();
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **sid** | **Integer**| The user id for the subaccount | [default to null]
 **email** | **String**| The email address for the subaccount | [default to null]

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## privateSetEmailLanguageGet

> Object privateSetEmailLanguageGet(language)

Changes the language to be used for emails.

### Example

```java
// Import classes:
//import org.openapitools.client.api.PrivateApi;

PrivateApi apiInstance = new PrivateApi();
String language = en; // String | The abbreviated language name. Valid values include `\"en\"`, `\"ko\"`, `\"zh\"`
try {
    Object result = apiInstance.privateSetEmailLanguageGet(language);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling PrivateApi#privateSetEmailLanguageGet");
    e.printStackTrace();
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **language** | **String**| The abbreviated language name. Valid values include &#x60;\&quot;en\&quot;&#x60;, &#x60;\&quot;ko\&quot;&#x60;, &#x60;\&quot;zh\&quot;&#x60; | [default to null]

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## privateSetPasswordForSubaccountGet

> Object privateSetPasswordForSubaccountGet(sid, password)

Set the password for the subaccount

### Example

```java
// Import classes:
//import org.openapitools.client.api.PrivateApi;

PrivateApi apiInstance = new PrivateApi();
Integer sid = null; // Integer | The user id for the subaccount
String password = null; // String | The password for the subaccount
try {
    Object result = apiInstance.privateSetPasswordForSubaccountGet(sid, password);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling PrivateApi#privateSetPasswordForSubaccountGet");
    e.printStackTrace();
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **sid** | **Integer**| The user id for the subaccount | [default to null]
 **password** | **String**| The password for the subaccount | [default to null]

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## privateSubmitTransferToSubaccountGet

> Object privateSubmitTransferToSubaccountGet(currency, amount, destination)

Transfer funds to a subaccount.

### Example

```java
// Import classes:
//import org.openapitools.client.api.PrivateApi;

PrivateApi apiInstance = new PrivateApi();
String currency = null; // String | The currency symbol
BigDecimal amount = null; // BigDecimal | Amount of funds to be transferred
Integer destination = 1; // Integer | Id of destination subaccount
try {
    Object result = apiInstance.privateSubmitTransferToSubaccountGet(currency, amount, destination);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling PrivateApi#privateSubmitTransferToSubaccountGet");
    e.printStackTrace();
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **String**| The currency symbol | [default to null] [enum: BTC, ETH]
 **amount** | **BigDecimal**| Amount of funds to be transferred | [default to null]
 **destination** | **Integer**| Id of destination subaccount | [default to null]

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## privateSubmitTransferToUserGet

> Object privateSubmitTransferToUserGet(currency, amount, destination, tfa)

Transfer funds to a another user.

### Example

```java
// Import classes:
//import org.openapitools.client.api.PrivateApi;

PrivateApi apiInstance = new PrivateApi();
String currency = null; // String | The currency symbol
BigDecimal amount = null; // BigDecimal | Amount of funds to be transferred
String destination = null; // String | Destination address from address book
String tfa = null; // String | TFA code, required when TFA is enabled for current account
try {
    Object result = apiInstance.privateSubmitTransferToUserGet(currency, amount, destination, tfa);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling PrivateApi#privateSubmitTransferToUserGet");
    e.printStackTrace();
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **String**| The currency symbol | [default to null] [enum: BTC, ETH]
 **amount** | **BigDecimal**| Amount of funds to be transferred | [default to null]
 **destination** | **String**| Destination address from address book | [default to null]
 **tfa** | **String**| TFA code, required when TFA is enabled for current account | [optional] [default to null]

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## privateToggleDepositAddressCreationGet

> Object privateToggleDepositAddressCreationGet(currency, state)

Enable or disable deposit address creation

### Example

```java
// Import classes:
//import org.openapitools.client.api.PrivateApi;

PrivateApi apiInstance = new PrivateApi();
String currency = null; // String | The currency symbol
Boolean state = null; // Boolean | 
try {
    Object result = apiInstance.privateToggleDepositAddressCreationGet(currency, state);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling PrivateApi#privateToggleDepositAddressCreationGet");
    e.printStackTrace();
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **String**| The currency symbol | [default to null] [enum: BTC, ETH]
 **state** | **Boolean**|  | [default to null]

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## privateToggleNotificationsFromSubaccountGet

> Object privateToggleNotificationsFromSubaccountGet(sid, state)

Enable or disable sending of notifications for the subaccount.

### Example

```java
// Import classes:
//import org.openapitools.client.api.PrivateApi;

PrivateApi apiInstance = new PrivateApi();
Integer sid = null; // Integer | The user id for the subaccount
Boolean state = null; // Boolean | enable (`true`) or disable (`false`) notifications
try {
    Object result = apiInstance.privateToggleNotificationsFromSubaccountGet(sid, state);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling PrivateApi#privateToggleNotificationsFromSubaccountGet");
    e.printStackTrace();
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **sid** | **Integer**| The user id for the subaccount | [default to null]
 **state** | **Boolean**| enable (&#x60;true&#x60;) or disable (&#x60;false&#x60;) notifications | [default to null]

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## privateToggleSubaccountLoginGet

> Object privateToggleSubaccountLoginGet(sid, state)

Enable or disable login for a subaccount. If login is disabled and a session for the subaccount exists, this session will be terminated.

### Example

```java
// Import classes:
//import org.openapitools.client.api.PrivateApi;

PrivateApi apiInstance = new PrivateApi();
Integer sid = null; // Integer | The user id for the subaccount
String state = null; // String | enable or disable login.
try {
    Object result = apiInstance.privateToggleSubaccountLoginGet(sid, state);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling PrivateApi#privateToggleSubaccountLoginGet");
    e.printStackTrace();
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **sid** | **Integer**| The user id for the subaccount | [default to null]
 **state** | **String**| enable or disable login. | [default to null] [enum: enable, disable]

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## privateWithdrawGet

> Object privateWithdrawGet(currency, address, amount, priority, tfa)

Creates a new withdrawal request

### Example

```java
// Import classes:
//import org.openapitools.client.api.PrivateApi;

PrivateApi apiInstance = new PrivateApi();
String currency = null; // String | The currency symbol
String address = null; // String | Address in currency format, it must be in address book
BigDecimal amount = null; // BigDecimal | Amount of funds to be withdrawn
String priority = null; // String | Withdrawal priority, optional for BTC, default: `high`
String tfa = null; // String | TFA code, required when TFA is enabled for current account
try {
    Object result = apiInstance.privateWithdrawGet(currency, address, amount, priority, tfa);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling PrivateApi#privateWithdrawGet");
    e.printStackTrace();
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **String**| The currency symbol | [default to null] [enum: BTC, ETH]
 **address** | **String**| Address in currency format, it must be in address book | [default to null]
 **amount** | **BigDecimal**| Amount of funds to be withdrawn | [default to null]
 **priority** | **String**| Withdrawal priority, optional for BTC, default: &#x60;high&#x60; | [optional] [default to null] [enum: insane, extreme_high, very_high, high, mid, low, very_low]
 **tfa** | **String**| TFA code, required when TFA is enabled for current account | [optional] [default to null]

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

