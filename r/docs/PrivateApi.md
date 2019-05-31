# PrivateApi

All URIs are relative to *https://www.deribit.com/api/v2*

Method | HTTP request | Description
------------- | ------------- | -------------
[**PrivateAddToAddressBookGet**](PrivateApi.md#PrivateAddToAddressBookGet) | **GET** /private/add_to_address_book | Adds new entry to address book of given type
[**PrivateBuyGet**](PrivateApi.md#PrivateBuyGet) | **GET** /private/buy | Places a buy order for an instrument.
[**PrivateCancelAllByCurrencyGet**](PrivateApi.md#PrivateCancelAllByCurrencyGet) | **GET** /private/cancel_all_by_currency | Cancels all orders by currency, optionally filtered by instrument kind and/or order type.
[**PrivateCancelAllByInstrumentGet**](PrivateApi.md#PrivateCancelAllByInstrumentGet) | **GET** /private/cancel_all_by_instrument | Cancels all orders by instrument, optionally filtered by order type.
[**PrivateCancelAllGet**](PrivateApi.md#PrivateCancelAllGet) | **GET** /private/cancel_all | This method cancels all users orders and stop orders within all currencies and instrument kinds.
[**PrivateCancelGet**](PrivateApi.md#PrivateCancelGet) | **GET** /private/cancel | Cancel an order, specified by order id
[**PrivateCancelTransferByIdGet**](PrivateApi.md#PrivateCancelTransferByIdGet) | **GET** /private/cancel_transfer_by_id | Cancel transfer
[**PrivateCancelWithdrawalGet**](PrivateApi.md#PrivateCancelWithdrawalGet) | **GET** /private/cancel_withdrawal | Cancels withdrawal request
[**PrivateChangeSubaccountNameGet**](PrivateApi.md#PrivateChangeSubaccountNameGet) | **GET** /private/change_subaccount_name | Change the user name for a subaccount
[**PrivateClosePositionGet**](PrivateApi.md#PrivateClosePositionGet) | **GET** /private/close_position | Makes closing position reduce only order .
[**PrivateCreateDepositAddressGet**](PrivateApi.md#PrivateCreateDepositAddressGet) | **GET** /private/create_deposit_address | Creates deposit address in currency
[**PrivateCreateSubaccountGet**](PrivateApi.md#PrivateCreateSubaccountGet) | **GET** /private/create_subaccount | Create a new subaccount
[**PrivateDisableTfaForSubaccountGet**](PrivateApi.md#PrivateDisableTfaForSubaccountGet) | **GET** /private/disable_tfa_for_subaccount | Disable two factor authentication for a subaccount.
[**PrivateDisableTfaWithRecoveryCodeGet**](PrivateApi.md#PrivateDisableTfaWithRecoveryCodeGet) | **GET** /private/disable_tfa_with_recovery_code | Disables TFA with one time recovery code
[**PrivateEditGet**](PrivateApi.md#PrivateEditGet) | **GET** /private/edit | Change price, amount and/or other properties of an order.
[**PrivateGetAccountSummaryGet**](PrivateApi.md#PrivateGetAccountSummaryGet) | **GET** /private/get_account_summary | Retrieves user account summary.
[**PrivateGetAddressBookGet**](PrivateApi.md#PrivateGetAddressBookGet) | **GET** /private/get_address_book | Retrieves address book of given type
[**PrivateGetCurrentDepositAddressGet**](PrivateApi.md#PrivateGetCurrentDepositAddressGet) | **GET** /private/get_current_deposit_address | Retrieve deposit address for currency
[**PrivateGetDepositsGet**](PrivateApi.md#PrivateGetDepositsGet) | **GET** /private/get_deposits | Retrieve the latest users deposits
[**PrivateGetEmailLanguageGet**](PrivateApi.md#PrivateGetEmailLanguageGet) | **GET** /private/get_email_language | Retrieves the language to be used for emails.
[**PrivateGetMarginsGet**](PrivateApi.md#PrivateGetMarginsGet) | **GET** /private/get_margins | Get margins for given instrument, amount and price.
[**PrivateGetNewAnnouncementsGet**](PrivateApi.md#PrivateGetNewAnnouncementsGet) | **GET** /private/get_new_announcements | Retrieves announcements that have not been marked read by the user.
[**PrivateGetOpenOrdersByCurrencyGet**](PrivateApi.md#PrivateGetOpenOrdersByCurrencyGet) | **GET** /private/get_open_orders_by_currency | Retrieves list of user&#39;s open orders.
[**PrivateGetOpenOrdersByInstrumentGet**](PrivateApi.md#PrivateGetOpenOrdersByInstrumentGet) | **GET** /private/get_open_orders_by_instrument | Retrieves list of user&#39;s open orders within given Instrument.
[**PrivateGetOrderHistoryByCurrencyGet**](PrivateApi.md#PrivateGetOrderHistoryByCurrencyGet) | **GET** /private/get_order_history_by_currency | Retrieves history of orders that have been partially or fully filled.
[**PrivateGetOrderHistoryByInstrumentGet**](PrivateApi.md#PrivateGetOrderHistoryByInstrumentGet) | **GET** /private/get_order_history_by_instrument | Retrieves history of orders that have been partially or fully filled.
[**PrivateGetOrderMarginByIdsGet**](PrivateApi.md#PrivateGetOrderMarginByIdsGet) | **GET** /private/get_order_margin_by_ids | Retrieves initial margins of given orders
[**PrivateGetOrderStateGet**](PrivateApi.md#PrivateGetOrderStateGet) | **GET** /private/get_order_state | Retrieve the current state of an order.
[**PrivateGetPositionGet**](PrivateApi.md#PrivateGetPositionGet) | **GET** /private/get_position | Retrieve user position.
[**PrivateGetPositionsGet**](PrivateApi.md#PrivateGetPositionsGet) | **GET** /private/get_positions | Retrieve user positions.
[**PrivateGetSettlementHistoryByCurrencyGet**](PrivateApi.md#PrivateGetSettlementHistoryByCurrencyGet) | **GET** /private/get_settlement_history_by_currency | Retrieves settlement, delivery and bankruptcy events that have affected your account.
[**PrivateGetSettlementHistoryByInstrumentGet**](PrivateApi.md#PrivateGetSettlementHistoryByInstrumentGet) | **GET** /private/get_settlement_history_by_instrument | Retrieves public settlement, delivery and bankruptcy events filtered by instrument name
[**PrivateGetSubaccountsGet**](PrivateApi.md#PrivateGetSubaccountsGet) | **GET** /private/get_subaccounts | Get information about subaccounts
[**PrivateGetTransfersGet**](PrivateApi.md#PrivateGetTransfersGet) | **GET** /private/get_transfers | Adds new entry to address book of given type
[**PrivateGetUserTradesByCurrencyAndTimeGet**](PrivateApi.md#PrivateGetUserTradesByCurrencyAndTimeGet) | **GET** /private/get_user_trades_by_currency_and_time | Retrieve the latest user trades that have occurred for instruments in a specific currency symbol and within given time range.
[**PrivateGetUserTradesByCurrencyGet**](PrivateApi.md#PrivateGetUserTradesByCurrencyGet) | **GET** /private/get_user_trades_by_currency | Retrieve the latest user trades that have occurred for instruments in a specific currency symbol.
[**PrivateGetUserTradesByInstrumentAndTimeGet**](PrivateApi.md#PrivateGetUserTradesByInstrumentAndTimeGet) | **GET** /private/get_user_trades_by_instrument_and_time | Retrieve the latest user trades that have occurred for a specific instrument and within given time range.
[**PrivateGetUserTradesByInstrumentGet**](PrivateApi.md#PrivateGetUserTradesByInstrumentGet) | **GET** /private/get_user_trades_by_instrument | Retrieve the latest user trades that have occurred for a specific instrument.
[**PrivateGetUserTradesByOrderGet**](PrivateApi.md#PrivateGetUserTradesByOrderGet) | **GET** /private/get_user_trades_by_order | Retrieve the list of user trades that was created for given order
[**PrivateGetWithdrawalsGet**](PrivateApi.md#PrivateGetWithdrawalsGet) | **GET** /private/get_withdrawals | Retrieve the latest users withdrawals
[**PrivateRemoveFromAddressBookGet**](PrivateApi.md#PrivateRemoveFromAddressBookGet) | **GET** /private/remove_from_address_book | Adds new entry to address book of given type
[**PrivateSellGet**](PrivateApi.md#PrivateSellGet) | **GET** /private/sell | Places a sell order for an instrument.
[**PrivateSetAnnouncementAsReadGet**](PrivateApi.md#PrivateSetAnnouncementAsReadGet) | **GET** /private/set_announcement_as_read | Marks an announcement as read, so it will not be shown in &#x60;get_new_announcements&#x60;.
[**PrivateSetEmailForSubaccountGet**](PrivateApi.md#PrivateSetEmailForSubaccountGet) | **GET** /private/set_email_for_subaccount | Assign an email address to a subaccount. User will receive an email with confirmation link.
[**PrivateSetEmailLanguageGet**](PrivateApi.md#PrivateSetEmailLanguageGet) | **GET** /private/set_email_language | Changes the language to be used for emails.
[**PrivateSetPasswordForSubaccountGet**](PrivateApi.md#PrivateSetPasswordForSubaccountGet) | **GET** /private/set_password_for_subaccount | Set the password for the subaccount
[**PrivateSubmitTransferToSubaccountGet**](PrivateApi.md#PrivateSubmitTransferToSubaccountGet) | **GET** /private/submit_transfer_to_subaccount | Transfer funds to a subaccount.
[**PrivateSubmitTransferToUserGet**](PrivateApi.md#PrivateSubmitTransferToUserGet) | **GET** /private/submit_transfer_to_user | Transfer funds to a another user.
[**PrivateToggleDepositAddressCreationGet**](PrivateApi.md#PrivateToggleDepositAddressCreationGet) | **GET** /private/toggle_deposit_address_creation | Enable or disable deposit address creation
[**PrivateToggleNotificationsFromSubaccountGet**](PrivateApi.md#PrivateToggleNotificationsFromSubaccountGet) | **GET** /private/toggle_notifications_from_subaccount | Enable or disable sending of notifications for the subaccount.
[**PrivateToggleSubaccountLoginGet**](PrivateApi.md#PrivateToggleSubaccountLoginGet) | **GET** /private/toggle_subaccount_login | Enable or disable login for a subaccount. If login is disabled and a session for the subaccount exists, this session will be terminated.
[**PrivateWithdrawGet**](PrivateApi.md#PrivateWithdrawGet) | **GET** /private/withdraw | Creates a new withdrawal request


# **PrivateAddToAddressBookGet**
> object PrivateAddToAddressBookGet(currency, type, address, name, tfa=var.tfa)

Adds new entry to address book of given type

### Example
```R
library(openapi)

var.currency <- 'currency_example' # character | The currency symbol
var.type <- 'type_example' # character | Address book type
var.address <- 'address_example' # character | Address in currency format, it must be in address book
var.name <- 'Main address' # character | Name of address book entry
var.tfa <- 'tfa_example' # character | TFA code, required when TFA is enabled for current account

#Adds new entry to address book of given type
api.instance <- PrivateApi$new()
# Configure HTTP basic authorization: bearerAuth
api.instance$apiClient$username <- 'TODO_YOUR_USERNAME';
api.instance$apiClient$password <- 'TODO_YOUR_PASSWORD';
result <- api.instance$PrivateAddToAddressBookGet(var.currency, var.type, var.address, var.name, tfa=var.tfa)
dput(result)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **character**| The currency symbol | 
 **type** | **character**| Address book type | 
 **address** | **character**| Address in currency format, it must be in address book | 
 **name** | **character**| Name of address book entry | 
 **tfa** | **character**| TFA code, required when TFA is enabled for current account | [optional] 

### Return type

**object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json



# **PrivateBuyGet**
> object PrivateBuyGet(instrument.name, amount, type=var.type, label=var.label, price=var.price, time.in.force='good_til_cancelled', max.show=1, post.only=TRUE, reduce.only=FALSE, stop.price=var.stop.price, trigger=var.trigger, advanced=var.advanced)

Places a buy order for an instrument.

### Example
```R
library(openapi)

var.instrument.name <- 'BTC-PERPETUAL' # character | Instrument name
var.amount <- 3.4 # numeric | It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH
var.type <- 'type_example' # character | The order type, default: `\"limit\"`
var.label <- 'label_example' # character | user defined label for the order (maximum 32 characters)
var.price <- 3.4 # numeric | <p>The order price in base currency (Only for limit and stop_limit orders)</p> <p>When adding order with advanced=usd, the field price should be the option price value in USD.</p> <p>When adding order with advanced=implv, the field price should be a value of implied volatility in percentages. For example,  price=100, means implied volatility of 100%</p>
var.time.in.force <- 'good_til_cancelled' # character | <p>Specifies how long the order remains in effect. Default `\"good_til_cancelled\"`</p> <ul> <li>`\"good_til_cancelled\"` - unfilled order remains in order book until cancelled</li> <li>`\"fill_or_kill\"` - execute a transaction immediately and completely or not at all</li> <li>`\"immediate_or_cancel\"` - execute a transaction immediately, and any portion of the order that cannot be immediately filled is cancelled</li> </ul>
var.max.show <- 1 # numeric | Maximum amount within an order to be shown to other customers, `0` for invisible order
var.post.only <- TRUE # character | <p>If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.</p> <p>Only valid in combination with time_in_force=`\"good_til_cancelled\"`</p>
var.reduce.only <- FALSE # character | If `true`, the order is considered reduce-only which is intended to only reduce a current position
var.stop.price <- 3.4 # numeric | Stop price, required for stop limit orders (Only for stop orders)
var.trigger <- 'trigger_example' # character | Defines trigger type, required for `\"stop_limit\"` order type
var.advanced <- 'advanced_example' # character | Advanced option order type. (Only for options)

#Places a buy order for an instrument.
api.instance <- PrivateApi$new()
# Configure HTTP basic authorization: bearerAuth
api.instance$apiClient$username <- 'TODO_YOUR_USERNAME';
api.instance$apiClient$password <- 'TODO_YOUR_PASSWORD';
result <- api.instance$PrivateBuyGet(var.instrument.name, var.amount, type=var.type, label=var.label, price=var.price, time.in.force=var.time.in.force, max.show=var.max.show, post.only=var.post.only, reduce.only=var.reduce.only, stop.price=var.stop.price, trigger=var.trigger, advanced=var.advanced)
dput(result)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrument.name** | **character**| Instrument name | 
 **amount** | **numeric**| It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH | 
 **type** | **character**| The order type, default: &#x60;\&quot;limit\&quot;&#x60; | [optional] 
 **label** | **character**| user defined label for the order (maximum 32 characters) | [optional] 
 **price** | **numeric**| &lt;p&gt;The order price in base currency (Only for limit and stop_limit orders)&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;usd, the field price should be the option price value in USD.&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;implv, the field price should be a value of implied volatility in percentages. For example,  price&#x3D;100, means implied volatility of 100%&lt;/p&gt; | [optional] 
 **time.in.force** | **character**| &lt;p&gt;Specifies how long the order remains in effect. Default &#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; &lt;ul&gt; &lt;li&gt;&#x60;\&quot;good_til_cancelled\&quot;&#x60; - unfilled order remains in order book until cancelled&lt;/li&gt; &lt;li&gt;&#x60;\&quot;fill_or_kill\&quot;&#x60; - execute a transaction immediately and completely or not at all&lt;/li&gt; &lt;li&gt;&#x60;\&quot;immediate_or_cancel\&quot;&#x60; - execute a transaction immediately, and any portion of the order that cannot be immediately filled is cancelled&lt;/li&gt; &lt;/ul&gt; | [optional] [default to &#39;good_til_cancelled&#39;]
 **max.show** | **numeric**| Maximum amount within an order to be shown to other customers, &#x60;0&#x60; for invisible order | [optional] [default to 1]
 **post.only** | **character**| &lt;p&gt;If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.&lt;/p&gt; &lt;p&gt;Only valid in combination with time_in_force&#x3D;&#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; | [optional] [default to TRUE]
 **reduce.only** | **character**| If &#x60;true&#x60;, the order is considered reduce-only which is intended to only reduce a current position | [optional] [default to FALSE]
 **stop.price** | **numeric**| Stop price, required for stop limit orders (Only for stop orders) | [optional] 
 **trigger** | **character**| Defines trigger type, required for &#x60;\&quot;stop_limit\&quot;&#x60; order type | [optional] 
 **advanced** | **character**| Advanced option order type. (Only for options) | [optional] 

### Return type

**object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json



# **PrivateCancelAllByCurrencyGet**
> object PrivateCancelAllByCurrencyGet(currency, kind=var.kind, type=var.type)

Cancels all orders by currency, optionally filtered by instrument kind and/or order type.

### Example
```R
library(openapi)

var.currency <- 'currency_example' # character | The currency symbol
var.kind <- 'kind_example' # character | Instrument kind, if not provided instruments of all kinds are considered
var.type <- 'type_example' # character | Order type - limit, stop or all, default - `all`

#Cancels all orders by currency, optionally filtered by instrument kind and/or order type.
api.instance <- PrivateApi$new()
# Configure HTTP basic authorization: bearerAuth
api.instance$apiClient$username <- 'TODO_YOUR_USERNAME';
api.instance$apiClient$password <- 'TODO_YOUR_PASSWORD';
result <- api.instance$PrivateCancelAllByCurrencyGet(var.currency, kind=var.kind, type=var.type)
dput(result)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **character**| The currency symbol | 
 **kind** | **character**| Instrument kind, if not provided instruments of all kinds are considered | [optional] 
 **type** | **character**| Order type - limit, stop or all, default - &#x60;all&#x60; | [optional] 

### Return type

**object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json



# **PrivateCancelAllByInstrumentGet**
> object PrivateCancelAllByInstrumentGet(instrument.name, type=var.type)

Cancels all orders by instrument, optionally filtered by order type.

### Example
```R
library(openapi)

var.instrument.name <- 'BTC-PERPETUAL' # character | Instrument name
var.type <- 'type_example' # character | Order type - limit, stop or all, default - `all`

#Cancels all orders by instrument, optionally filtered by order type.
api.instance <- PrivateApi$new()
# Configure HTTP basic authorization: bearerAuth
api.instance$apiClient$username <- 'TODO_YOUR_USERNAME';
api.instance$apiClient$password <- 'TODO_YOUR_PASSWORD';
result <- api.instance$PrivateCancelAllByInstrumentGet(var.instrument.name, type=var.type)
dput(result)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrument.name** | **character**| Instrument name | 
 **type** | **character**| Order type - limit, stop or all, default - &#x60;all&#x60; | [optional] 

### Return type

**object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json



# **PrivateCancelAllGet**
> object PrivateCancelAllGet()

This method cancels all users orders and stop orders within all currencies and instrument kinds.

### Example
```R
library(openapi)


#This method cancels all users orders and stop orders within all currencies and instrument kinds.
api.instance <- PrivateApi$new()
# Configure HTTP basic authorization: bearerAuth
api.instance$apiClient$username <- 'TODO_YOUR_USERNAME';
api.instance$apiClient$password <- 'TODO_YOUR_PASSWORD';
result <- api.instance$PrivateCancelAllGet()
dput(result)
```

### Parameters
This endpoint does not need any parameter.

### Return type

**object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json



# **PrivateCancelGet**
> object PrivateCancelGet(order.id)

Cancel an order, specified by order id

### Example
```R
library(openapi)

var.order.id <- 'ETH-100234' # character | The order id

#Cancel an order, specified by order id
api.instance <- PrivateApi$new()
# Configure HTTP basic authorization: bearerAuth
api.instance$apiClient$username <- 'TODO_YOUR_USERNAME';
api.instance$apiClient$password <- 'TODO_YOUR_PASSWORD';
result <- api.instance$PrivateCancelGet(var.order.id)
dput(result)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **order.id** | **character**| The order id | 

### Return type

**object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json



# **PrivateCancelTransferByIdGet**
> object PrivateCancelTransferByIdGet(currency, id, tfa=var.tfa)

Cancel transfer

### Example
```R
library(openapi)

var.currency <- 'currency_example' # character | The currency symbol
var.id <- 12 # integer | Id of transfer
var.tfa <- 'tfa_example' # character | TFA code, required when TFA is enabled for current account

#Cancel transfer
api.instance <- PrivateApi$new()
# Configure HTTP basic authorization: bearerAuth
api.instance$apiClient$username <- 'TODO_YOUR_USERNAME';
api.instance$apiClient$password <- 'TODO_YOUR_PASSWORD';
result <- api.instance$PrivateCancelTransferByIdGet(var.currency, var.id, tfa=var.tfa)
dput(result)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **character**| The currency symbol | 
 **id** | **integer**| Id of transfer | 
 **tfa** | **character**| TFA code, required when TFA is enabled for current account | [optional] 

### Return type

**object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json



# **PrivateCancelWithdrawalGet**
> object PrivateCancelWithdrawalGet(currency, id)

Cancels withdrawal request

### Example
```R
library(openapi)

var.currency <- 'currency_example' # character | The currency symbol
var.id <- 1 # numeric | The withdrawal id

#Cancels withdrawal request
api.instance <- PrivateApi$new()
# Configure HTTP basic authorization: bearerAuth
api.instance$apiClient$username <- 'TODO_YOUR_USERNAME';
api.instance$apiClient$password <- 'TODO_YOUR_PASSWORD';
result <- api.instance$PrivateCancelWithdrawalGet(var.currency, var.id)
dput(result)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **character**| The currency symbol | 
 **id** | **numeric**| The withdrawal id | 

### Return type

**object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json



# **PrivateChangeSubaccountNameGet**
> object PrivateChangeSubaccountNameGet(sid, name)

Change the user name for a subaccount

### Example
```R
library(openapi)

var.sid <- 56 # integer | The user id for the subaccount
var.name <- 'newUserName' # character | The new user name

#Change the user name for a subaccount
api.instance <- PrivateApi$new()
# Configure HTTP basic authorization: bearerAuth
api.instance$apiClient$username <- 'TODO_YOUR_USERNAME';
api.instance$apiClient$password <- 'TODO_YOUR_PASSWORD';
result <- api.instance$PrivateChangeSubaccountNameGet(var.sid, var.name)
dput(result)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **sid** | **integer**| The user id for the subaccount | 
 **name** | **character**| The new user name | 

### Return type

**object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json



# **PrivateClosePositionGet**
> object PrivateClosePositionGet(instrument.name, type, price=var.price)

Makes closing position reduce only order .

### Example
```R
library(openapi)

var.instrument.name <- 'BTC-PERPETUAL' # character | Instrument name
var.type <- 'type_example' # character | The order type
var.price <- 3.4 # numeric | Optional price for limit order.

#Makes closing position reduce only order .
api.instance <- PrivateApi$new()
# Configure HTTP basic authorization: bearerAuth
api.instance$apiClient$username <- 'TODO_YOUR_USERNAME';
api.instance$apiClient$password <- 'TODO_YOUR_PASSWORD';
result <- api.instance$PrivateClosePositionGet(var.instrument.name, var.type, price=var.price)
dput(result)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrument.name** | **character**| Instrument name | 
 **type** | **character**| The order type | 
 **price** | **numeric**| Optional price for limit order. | [optional] 

### Return type

**object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json



# **PrivateCreateDepositAddressGet**
> object PrivateCreateDepositAddressGet(currency)

Creates deposit address in currency

### Example
```R
library(openapi)

var.currency <- 'currency_example' # character | The currency symbol

#Creates deposit address in currency
api.instance <- PrivateApi$new()
# Configure HTTP basic authorization: bearerAuth
api.instance$apiClient$username <- 'TODO_YOUR_USERNAME';
api.instance$apiClient$password <- 'TODO_YOUR_PASSWORD';
result <- api.instance$PrivateCreateDepositAddressGet(var.currency)
dput(result)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **character**| The currency symbol | 

### Return type

**object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json



# **PrivateCreateSubaccountGet**
> object PrivateCreateSubaccountGet()

Create a new subaccount

### Example
```R
library(openapi)


#Create a new subaccount
api.instance <- PrivateApi$new()
# Configure HTTP basic authorization: bearerAuth
api.instance$apiClient$username <- 'TODO_YOUR_USERNAME';
api.instance$apiClient$password <- 'TODO_YOUR_PASSWORD';
result <- api.instance$PrivateCreateSubaccountGet()
dput(result)
```

### Parameters
This endpoint does not need any parameter.

### Return type

**object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json



# **PrivateDisableTfaForSubaccountGet**
> object PrivateDisableTfaForSubaccountGet(sid)

Disable two factor authentication for a subaccount.

### Example
```R
library(openapi)

var.sid <- 56 # integer | The user id for the subaccount

#Disable two factor authentication for a subaccount.
api.instance <- PrivateApi$new()
# Configure HTTP basic authorization: bearerAuth
api.instance$apiClient$username <- 'TODO_YOUR_USERNAME';
api.instance$apiClient$password <- 'TODO_YOUR_PASSWORD';
result <- api.instance$PrivateDisableTfaForSubaccountGet(var.sid)
dput(result)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **sid** | **integer**| The user id for the subaccount | 

### Return type

**object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json



# **PrivateDisableTfaWithRecoveryCodeGet**
> object PrivateDisableTfaWithRecoveryCodeGet(password, code)

Disables TFA with one time recovery code

### Example
```R
library(openapi)

var.password <- 'password_example' # character | The password for the subaccount
var.code <- 'code_example' # character | One time recovery code

#Disables TFA with one time recovery code
api.instance <- PrivateApi$new()
# Configure HTTP basic authorization: bearerAuth
api.instance$apiClient$username <- 'TODO_YOUR_USERNAME';
api.instance$apiClient$password <- 'TODO_YOUR_PASSWORD';
result <- api.instance$PrivateDisableTfaWithRecoveryCodeGet(var.password, var.code)
dput(result)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **password** | **character**| The password for the subaccount | 
 **code** | **character**| One time recovery code | 

### Return type

**object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json



# **PrivateEditGet**
> object PrivateEditGet(order.id, amount, price, post.only=TRUE, advanced=var.advanced, stop.price=var.stop.price)

Change price, amount and/or other properties of an order.

### Example
```R
library(openapi)

var.order.id <- 'ETH-100234' # character | The order id
var.amount <- 3.4 # numeric | It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH
var.price <- 3.4 # numeric | <p>The order price in base currency.</p> <p>When editing an option order with advanced=usd, the field price should be the option price value in USD.</p> <p>When editing an option order with advanced=implv, the field price should be a value of implied volatility in percentages. For example,  price=100, means implied volatility of 100%</p>
var.post.only <- TRUE # character | <p>If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.</p> <p>Only valid in combination with time_in_force=`\"good_til_cancelled\"`</p>
var.advanced <- 'advanced_example' # character | Advanced option order type. If you have posted an advanced option order, it is necessary to re-supply this parameter when editing it (Only for options)
var.stop.price <- 3.4 # numeric | Stop price, required for stop limit orders (Only for stop orders)

#Change price, amount and/or other properties of an order.
api.instance <- PrivateApi$new()
# Configure HTTP basic authorization: bearerAuth
api.instance$apiClient$username <- 'TODO_YOUR_USERNAME';
api.instance$apiClient$password <- 'TODO_YOUR_PASSWORD';
result <- api.instance$PrivateEditGet(var.order.id, var.amount, var.price, post.only=var.post.only, advanced=var.advanced, stop.price=var.stop.price)
dput(result)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **order.id** | **character**| The order id | 
 **amount** | **numeric**| It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH | 
 **price** | **numeric**| &lt;p&gt;The order price in base currency.&lt;/p&gt; &lt;p&gt;When editing an option order with advanced&#x3D;usd, the field price should be the option price value in USD.&lt;/p&gt; &lt;p&gt;When editing an option order with advanced&#x3D;implv, the field price should be a value of implied volatility in percentages. For example,  price&#x3D;100, means implied volatility of 100%&lt;/p&gt; | 
 **post.only** | **character**| &lt;p&gt;If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.&lt;/p&gt; &lt;p&gt;Only valid in combination with time_in_force&#x3D;&#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; | [optional] [default to TRUE]
 **advanced** | **character**| Advanced option order type. If you have posted an advanced option order, it is necessary to re-supply this parameter when editing it (Only for options) | [optional] 
 **stop.price** | **numeric**| Stop price, required for stop limit orders (Only for stop orders) | [optional] 

### Return type

**object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json



# **PrivateGetAccountSummaryGet**
> object PrivateGetAccountSummaryGet(currency, extended=var.extended)

Retrieves user account summary.

### Example
```R
library(openapi)

var.currency <- 'currency_example' # character | The currency symbol
var.extended <- 'false' # character | Include additional fields

#Retrieves user account summary.
api.instance <- PrivateApi$new()
# Configure HTTP basic authorization: bearerAuth
api.instance$apiClient$username <- 'TODO_YOUR_USERNAME';
api.instance$apiClient$password <- 'TODO_YOUR_PASSWORD';
result <- api.instance$PrivateGetAccountSummaryGet(var.currency, extended=var.extended)
dput(result)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **character**| The currency symbol | 
 **extended** | **character**| Include additional fields | [optional] 

### Return type

**object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json



# **PrivateGetAddressBookGet**
> object PrivateGetAddressBookGet(currency, type)

Retrieves address book of given type

### Example
```R
library(openapi)

var.currency <- 'currency_example' # character | The currency symbol
var.type <- 'type_example' # character | Address book type

#Retrieves address book of given type
api.instance <- PrivateApi$new()
# Configure HTTP basic authorization: bearerAuth
api.instance$apiClient$username <- 'TODO_YOUR_USERNAME';
api.instance$apiClient$password <- 'TODO_YOUR_PASSWORD';
result <- api.instance$PrivateGetAddressBookGet(var.currency, var.type)
dput(result)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **character**| The currency symbol | 
 **type** | **character**| Address book type | 

### Return type

**object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json



# **PrivateGetCurrentDepositAddressGet**
> object PrivateGetCurrentDepositAddressGet(currency)

Retrieve deposit address for currency

### Example
```R
library(openapi)

var.currency <- 'currency_example' # character | The currency symbol

#Retrieve deposit address for currency
api.instance <- PrivateApi$new()
# Configure HTTP basic authorization: bearerAuth
api.instance$apiClient$username <- 'TODO_YOUR_USERNAME';
api.instance$apiClient$password <- 'TODO_YOUR_PASSWORD';
result <- api.instance$PrivateGetCurrentDepositAddressGet(var.currency)
dput(result)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **character**| The currency symbol | 

### Return type

**object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json



# **PrivateGetDepositsGet**
> object PrivateGetDepositsGet(currency, count=var.count, offset=var.offset)

Retrieve the latest users deposits

### Example
```R
library(openapi)

var.currency <- 'currency_example' # character | The currency symbol
var.count <- 56 # integer | Number of requested items, default - `10`
var.offset <- 10 # integer | The offset for pagination, default - `0`

#Retrieve the latest users deposits
api.instance <- PrivateApi$new()
# Configure HTTP basic authorization: bearerAuth
api.instance$apiClient$username <- 'TODO_YOUR_USERNAME';
api.instance$apiClient$password <- 'TODO_YOUR_PASSWORD';
result <- api.instance$PrivateGetDepositsGet(var.currency, count=var.count, offset=var.offset)
dput(result)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **character**| The currency symbol | 
 **count** | **integer**| Number of requested items, default - &#x60;10&#x60; | [optional] 
 **offset** | **integer**| The offset for pagination, default - &#x60;0&#x60; | [optional] 

### Return type

**object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json



# **PrivateGetEmailLanguageGet**
> object PrivateGetEmailLanguageGet()

Retrieves the language to be used for emails.

### Example
```R
library(openapi)


#Retrieves the language to be used for emails.
api.instance <- PrivateApi$new()
# Configure HTTP basic authorization: bearerAuth
api.instance$apiClient$username <- 'TODO_YOUR_USERNAME';
api.instance$apiClient$password <- 'TODO_YOUR_PASSWORD';
result <- api.instance$PrivateGetEmailLanguageGet()
dput(result)
```

### Parameters
This endpoint does not need any parameter.

### Return type

**object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json



# **PrivateGetMarginsGet**
> object PrivateGetMarginsGet(instrument.name, amount, price)

Get margins for given instrument, amount and price.

### Example
```R
library(openapi)

var.instrument.name <- 'BTC-PERPETUAL' # character | Instrument name
var.amount <- 1 # numeric | Amount, integer for future, float for option. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH.
var.price <- 3.4 # numeric | Price

#Get margins for given instrument, amount and price.
api.instance <- PrivateApi$new()
# Configure HTTP basic authorization: bearerAuth
api.instance$apiClient$username <- 'TODO_YOUR_USERNAME';
api.instance$apiClient$password <- 'TODO_YOUR_PASSWORD';
result <- api.instance$PrivateGetMarginsGet(var.instrument.name, var.amount, var.price)
dput(result)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrument.name** | **character**| Instrument name | 
 **amount** | **numeric**| Amount, integer for future, float for option. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH. | 
 **price** | **numeric**| Price | 

### Return type

**object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json



# **PrivateGetNewAnnouncementsGet**
> object PrivateGetNewAnnouncementsGet()

Retrieves announcements that have not been marked read by the user.

### Example
```R
library(openapi)


#Retrieves announcements that have not been marked read by the user.
api.instance <- PrivateApi$new()
# Configure HTTP basic authorization: bearerAuth
api.instance$apiClient$username <- 'TODO_YOUR_USERNAME';
api.instance$apiClient$password <- 'TODO_YOUR_PASSWORD';
result <- api.instance$PrivateGetNewAnnouncementsGet()
dput(result)
```

### Parameters
This endpoint does not need any parameter.

### Return type

**object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json



# **PrivateGetOpenOrdersByCurrencyGet**
> object PrivateGetOpenOrdersByCurrencyGet(currency, kind=var.kind, type=var.type)

Retrieves list of user's open orders.

### Example
```R
library(openapi)

var.currency <- 'currency_example' # character | The currency symbol
var.kind <- 'kind_example' # character | Instrument kind, if not provided instruments of all kinds are considered
var.type <- 'type_example' # character | Order type, default - `all`

#Retrieves list of user's open orders.
api.instance <- PrivateApi$new()
# Configure HTTP basic authorization: bearerAuth
api.instance$apiClient$username <- 'TODO_YOUR_USERNAME';
api.instance$apiClient$password <- 'TODO_YOUR_PASSWORD';
result <- api.instance$PrivateGetOpenOrdersByCurrencyGet(var.currency, kind=var.kind, type=var.type)
dput(result)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **character**| The currency symbol | 
 **kind** | **character**| Instrument kind, if not provided instruments of all kinds are considered | [optional] 
 **type** | **character**| Order type, default - &#x60;all&#x60; | [optional] 

### Return type

**object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json



# **PrivateGetOpenOrdersByInstrumentGet**
> object PrivateGetOpenOrdersByInstrumentGet(instrument.name, type=var.type)

Retrieves list of user's open orders within given Instrument.

### Example
```R
library(openapi)

var.instrument.name <- 'BTC-PERPETUAL' # character | Instrument name
var.type <- 'type_example' # character | Order type, default - `all`

#Retrieves list of user's open orders within given Instrument.
api.instance <- PrivateApi$new()
# Configure HTTP basic authorization: bearerAuth
api.instance$apiClient$username <- 'TODO_YOUR_USERNAME';
api.instance$apiClient$password <- 'TODO_YOUR_PASSWORD';
result <- api.instance$PrivateGetOpenOrdersByInstrumentGet(var.instrument.name, type=var.type)
dput(result)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrument.name** | **character**| Instrument name | 
 **type** | **character**| Order type, default - &#x60;all&#x60; | [optional] 

### Return type

**object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json



# **PrivateGetOrderHistoryByCurrencyGet**
> object PrivateGetOrderHistoryByCurrencyGet(currency, kind=var.kind, count=var.count, offset=var.offset, include.old=var.include.old, include.unfilled=var.include.unfilled)

Retrieves history of orders that have been partially or fully filled.

### Example
```R
library(openapi)

var.currency <- 'currency_example' # character | The currency symbol
var.kind <- 'kind_example' # character | Instrument kind, if not provided instruments of all kinds are considered
var.count <- 56 # integer | Number of requested items, default - `20`
var.offset <- 10 # integer | The offset for pagination, default - `0`
var.include.old <- 'false' # character | Include in result orders older than 2 days, default - `false`
var.include.unfilled <- 'false' # character | Include in result fully unfilled closed orders, default - `false`

#Retrieves history of orders that have been partially or fully filled.
api.instance <- PrivateApi$new()
# Configure HTTP basic authorization: bearerAuth
api.instance$apiClient$username <- 'TODO_YOUR_USERNAME';
api.instance$apiClient$password <- 'TODO_YOUR_PASSWORD';
result <- api.instance$PrivateGetOrderHistoryByCurrencyGet(var.currency, kind=var.kind, count=var.count, offset=var.offset, include.old=var.include.old, include.unfilled=var.include.unfilled)
dput(result)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **character**| The currency symbol | 
 **kind** | **character**| Instrument kind, if not provided instruments of all kinds are considered | [optional] 
 **count** | **integer**| Number of requested items, default - &#x60;20&#x60; | [optional] 
 **offset** | **integer**| The offset for pagination, default - &#x60;0&#x60; | [optional] 
 **include.old** | **character**| Include in result orders older than 2 days, default - &#x60;false&#x60; | [optional] 
 **include.unfilled** | **character**| Include in result fully unfilled closed orders, default - &#x60;false&#x60; | [optional] 

### Return type

**object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json



# **PrivateGetOrderHistoryByInstrumentGet**
> object PrivateGetOrderHistoryByInstrumentGet(instrument.name, count=var.count, offset=var.offset, include.old=var.include.old, include.unfilled=var.include.unfilled)

Retrieves history of orders that have been partially or fully filled.

### Example
```R
library(openapi)

var.instrument.name <- 'BTC-PERPETUAL' # character | Instrument name
var.count <- 56 # integer | Number of requested items, default - `20`
var.offset <- 10 # integer | The offset for pagination, default - `0`
var.include.old <- 'false' # character | Include in result orders older than 2 days, default - `false`
var.include.unfilled <- 'false' # character | Include in result fully unfilled closed orders, default - `false`

#Retrieves history of orders that have been partially or fully filled.
api.instance <- PrivateApi$new()
# Configure HTTP basic authorization: bearerAuth
api.instance$apiClient$username <- 'TODO_YOUR_USERNAME';
api.instance$apiClient$password <- 'TODO_YOUR_PASSWORD';
result <- api.instance$PrivateGetOrderHistoryByInstrumentGet(var.instrument.name, count=var.count, offset=var.offset, include.old=var.include.old, include.unfilled=var.include.unfilled)
dput(result)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrument.name** | **character**| Instrument name | 
 **count** | **integer**| Number of requested items, default - &#x60;20&#x60; | [optional] 
 **offset** | **integer**| The offset for pagination, default - &#x60;0&#x60; | [optional] 
 **include.old** | **character**| Include in result orders older than 2 days, default - &#x60;false&#x60; | [optional] 
 **include.unfilled** | **character**| Include in result fully unfilled closed orders, default - &#x60;false&#x60; | [optional] 

### Return type

**object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json



# **PrivateGetOrderMarginByIdsGet**
> object PrivateGetOrderMarginByIdsGet(ids)

Retrieves initial margins of given orders

### Example
```R
library(openapi)

var.ids <- list("inner_example") # character | Ids of orders

#Retrieves initial margins of given orders
api.instance <- PrivateApi$new()
# Configure HTTP basic authorization: bearerAuth
api.instance$apiClient$username <- 'TODO_YOUR_USERNAME';
api.instance$apiClient$password <- 'TODO_YOUR_PASSWORD';
result <- api.instance$PrivateGetOrderMarginByIdsGet(var.ids)
dput(result)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **ids** | [**character**](character.md)| Ids of orders | 

### Return type

**object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json



# **PrivateGetOrderStateGet**
> object PrivateGetOrderStateGet(order.id)

Retrieve the current state of an order.

### Example
```R
library(openapi)

var.order.id <- 'ETH-100234' # character | The order id

#Retrieve the current state of an order.
api.instance <- PrivateApi$new()
# Configure HTTP basic authorization: bearerAuth
api.instance$apiClient$username <- 'TODO_YOUR_USERNAME';
api.instance$apiClient$password <- 'TODO_YOUR_PASSWORD';
result <- api.instance$PrivateGetOrderStateGet(var.order.id)
dput(result)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **order.id** | **character**| The order id | 

### Return type

**object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json



# **PrivateGetPositionGet**
> object PrivateGetPositionGet(instrument.name)

Retrieve user position.

### Example
```R
library(openapi)

var.instrument.name <- 'BTC-PERPETUAL' # character | Instrument name

#Retrieve user position.
api.instance <- PrivateApi$new()
# Configure HTTP basic authorization: bearerAuth
api.instance$apiClient$username <- 'TODO_YOUR_USERNAME';
api.instance$apiClient$password <- 'TODO_YOUR_PASSWORD';
result <- api.instance$PrivateGetPositionGet(var.instrument.name)
dput(result)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrument.name** | **character**| Instrument name | 

### Return type

**object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json



# **PrivateGetPositionsGet**
> object PrivateGetPositionsGet(currency, kind=var.kind)

Retrieve user positions.

### Example
```R
library(openapi)

var.currency <- 'currency_example' # character | 
var.kind <- 'kind_example' # character | Kind filter on positions

#Retrieve user positions.
api.instance <- PrivateApi$new()
# Configure HTTP basic authorization: bearerAuth
api.instance$apiClient$username <- 'TODO_YOUR_USERNAME';
api.instance$apiClient$password <- 'TODO_YOUR_PASSWORD';
result <- api.instance$PrivateGetPositionsGet(var.currency, kind=var.kind)
dput(result)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **character**|  | 
 **kind** | **character**| Kind filter on positions | [optional] 

### Return type

**object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json



# **PrivateGetSettlementHistoryByCurrencyGet**
> object PrivateGetSettlementHistoryByCurrencyGet(currency, type=var.type, count=var.count)

Retrieves settlement, delivery and bankruptcy events that have affected your account.

### Example
```R
library(openapi)

var.currency <- 'currency_example' # character | The currency symbol
var.type <- 'type_example' # character | Settlement type
var.count <- 56 # integer | Number of requested items, default - `20`

#Retrieves settlement, delivery and bankruptcy events that have affected your account.
api.instance <- PrivateApi$new()
# Configure HTTP basic authorization: bearerAuth
api.instance$apiClient$username <- 'TODO_YOUR_USERNAME';
api.instance$apiClient$password <- 'TODO_YOUR_PASSWORD';
result <- api.instance$PrivateGetSettlementHistoryByCurrencyGet(var.currency, type=var.type, count=var.count)
dput(result)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **character**| The currency symbol | 
 **type** | **character**| Settlement type | [optional] 
 **count** | **integer**| Number of requested items, default - &#x60;20&#x60; | [optional] 

### Return type

**object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json



# **PrivateGetSettlementHistoryByInstrumentGet**
> object PrivateGetSettlementHistoryByInstrumentGet(instrument.name, type=var.type, count=var.count)

Retrieves public settlement, delivery and bankruptcy events filtered by instrument name

### Example
```R
library(openapi)

var.instrument.name <- 'BTC-PERPETUAL' # character | Instrument name
var.type <- 'type_example' # character | Settlement type
var.count <- 56 # integer | Number of requested items, default - `20`

#Retrieves public settlement, delivery and bankruptcy events filtered by instrument name
api.instance <- PrivateApi$new()
# Configure HTTP basic authorization: bearerAuth
api.instance$apiClient$username <- 'TODO_YOUR_USERNAME';
api.instance$apiClient$password <- 'TODO_YOUR_PASSWORD';
result <- api.instance$PrivateGetSettlementHistoryByInstrumentGet(var.instrument.name, type=var.type, count=var.count)
dput(result)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrument.name** | **character**| Instrument name | 
 **type** | **character**| Settlement type | [optional] 
 **count** | **integer**| Number of requested items, default - &#x60;20&#x60; | [optional] 

### Return type

**object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json



# **PrivateGetSubaccountsGet**
> object PrivateGetSubaccountsGet(with.portfolio=var.with.portfolio)

Get information about subaccounts

### Example
```R
library(openapi)

var.with.portfolio <- 'with.portfolio_example' # character | 

#Get information about subaccounts
api.instance <- PrivateApi$new()
# Configure HTTP basic authorization: bearerAuth
api.instance$apiClient$username <- 'TODO_YOUR_USERNAME';
api.instance$apiClient$password <- 'TODO_YOUR_PASSWORD';
result <- api.instance$PrivateGetSubaccountsGet(with.portfolio=var.with.portfolio)
dput(result)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **with.portfolio** | **character**|  | [optional] 

### Return type

**object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json



# **PrivateGetTransfersGet**
> object PrivateGetTransfersGet(currency, count=var.count, offset=var.offset)

Adds new entry to address book of given type

### Example
```R
library(openapi)

var.currency <- 'currency_example' # character | The currency symbol
var.count <- 56 # integer | Number of requested items, default - `10`
var.offset <- 10 # integer | The offset for pagination, default - `0`

#Adds new entry to address book of given type
api.instance <- PrivateApi$new()
# Configure HTTP basic authorization: bearerAuth
api.instance$apiClient$username <- 'TODO_YOUR_USERNAME';
api.instance$apiClient$password <- 'TODO_YOUR_PASSWORD';
result <- api.instance$PrivateGetTransfersGet(var.currency, count=var.count, offset=var.offset)
dput(result)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **character**| The currency symbol | 
 **count** | **integer**| Number of requested items, default - &#x60;10&#x60; | [optional] 
 **offset** | **integer**| The offset for pagination, default - &#x60;0&#x60; | [optional] 

### Return type

**object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json



# **PrivateGetUserTradesByCurrencyAndTimeGet**
> object PrivateGetUserTradesByCurrencyAndTimeGet(currency, start.timestamp, end.timestamp, kind=var.kind, count=var.count, include.old=var.include.old, sorting=var.sorting)

Retrieve the latest user trades that have occurred for instruments in a specific currency symbol and within given time range.

### Example
```R
library(openapi)

var.currency <- 'currency_example' # character | The currency symbol
var.start.timestamp <- 1536569522277 # integer | The earliest timestamp to return result for
var.end.timestamp <- 1536569522277 # integer | The most recent timestamp to return result for
var.kind <- 'kind_example' # character | Instrument kind, if not provided instruments of all kinds are considered
var.count <- 56 # integer | Number of requested items, default - `10`
var.include.old <- 'include.old_example' # character | Include trades older than 7 days, default - `false`
var.sorting <- 'sorting_example' # character | Direction of results sorting (`default` value means no sorting, results will be returned in order in which they left the database)

#Retrieve the latest user trades that have occurred for instruments in a specific currency symbol and within given time range.
api.instance <- PrivateApi$new()
# Configure HTTP basic authorization: bearerAuth
api.instance$apiClient$username <- 'TODO_YOUR_USERNAME';
api.instance$apiClient$password <- 'TODO_YOUR_PASSWORD';
result <- api.instance$PrivateGetUserTradesByCurrencyAndTimeGet(var.currency, var.start.timestamp, var.end.timestamp, kind=var.kind, count=var.count, include.old=var.include.old, sorting=var.sorting)
dput(result)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **character**| The currency symbol | 
 **start.timestamp** | **integer**| The earliest timestamp to return result for | 
 **end.timestamp** | **integer**| The most recent timestamp to return result for | 
 **kind** | **character**| Instrument kind, if not provided instruments of all kinds are considered | [optional] 
 **count** | **integer**| Number of requested items, default - &#x60;10&#x60; | [optional] 
 **include.old** | **character**| Include trades older than 7 days, default - &#x60;false&#x60; | [optional] 
 **sorting** | **character**| Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) | [optional] 

### Return type

**object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json



# **PrivateGetUserTradesByCurrencyGet**
> object PrivateGetUserTradesByCurrencyGet(currency, kind=var.kind, start.id=var.start.id, end.id=var.end.id, count=var.count, include.old=var.include.old, sorting=var.sorting)

Retrieve the latest user trades that have occurred for instruments in a specific currency symbol.

### Example
```R
library(openapi)

var.currency <- 'currency_example' # character | The currency symbol
var.kind <- 'kind_example' # character | Instrument kind, if not provided instruments of all kinds are considered
var.start.id <- 'start.id_example' # character | The ID number of the first trade to be returned
var.end.id <- 'end.id_example' # character | The ID number of the last trade to be returned
var.count <- 56 # integer | Number of requested items, default - `10`
var.include.old <- 'include.old_example' # character | Include trades older than 7 days, default - `false`
var.sorting <- 'sorting_example' # character | Direction of results sorting (`default` value means no sorting, results will be returned in order in which they left the database)

#Retrieve the latest user trades that have occurred for instruments in a specific currency symbol.
api.instance <- PrivateApi$new()
# Configure HTTP basic authorization: bearerAuth
api.instance$apiClient$username <- 'TODO_YOUR_USERNAME';
api.instance$apiClient$password <- 'TODO_YOUR_PASSWORD';
result <- api.instance$PrivateGetUserTradesByCurrencyGet(var.currency, kind=var.kind, start.id=var.start.id, end.id=var.end.id, count=var.count, include.old=var.include.old, sorting=var.sorting)
dput(result)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **character**| The currency symbol | 
 **kind** | **character**| Instrument kind, if not provided instruments of all kinds are considered | [optional] 
 **start.id** | **character**| The ID number of the first trade to be returned | [optional] 
 **end.id** | **character**| The ID number of the last trade to be returned | [optional] 
 **count** | **integer**| Number of requested items, default - &#x60;10&#x60; | [optional] 
 **include.old** | **character**| Include trades older than 7 days, default - &#x60;false&#x60; | [optional] 
 **sorting** | **character**| Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) | [optional] 

### Return type

**object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json



# **PrivateGetUserTradesByInstrumentAndTimeGet**
> object PrivateGetUserTradesByInstrumentAndTimeGet(instrument.name, start.timestamp, end.timestamp, count=var.count, include.old=var.include.old, sorting=var.sorting)

Retrieve the latest user trades that have occurred for a specific instrument and within given time range.

### Example
```R
library(openapi)

var.instrument.name <- 'BTC-PERPETUAL' # character | Instrument name
var.start.timestamp <- 1536569522277 # integer | The earliest timestamp to return result for
var.end.timestamp <- 1536569522277 # integer | The most recent timestamp to return result for
var.count <- 56 # integer | Number of requested items, default - `10`
var.include.old <- 'include.old_example' # character | Include trades older than 7 days, default - `false`
var.sorting <- 'sorting_example' # character | Direction of results sorting (`default` value means no sorting, results will be returned in order in which they left the database)

#Retrieve the latest user trades that have occurred for a specific instrument and within given time range.
api.instance <- PrivateApi$new()
# Configure HTTP basic authorization: bearerAuth
api.instance$apiClient$username <- 'TODO_YOUR_USERNAME';
api.instance$apiClient$password <- 'TODO_YOUR_PASSWORD';
result <- api.instance$PrivateGetUserTradesByInstrumentAndTimeGet(var.instrument.name, var.start.timestamp, var.end.timestamp, count=var.count, include.old=var.include.old, sorting=var.sorting)
dput(result)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrument.name** | **character**| Instrument name | 
 **start.timestamp** | **integer**| The earliest timestamp to return result for | 
 **end.timestamp** | **integer**| The most recent timestamp to return result for | 
 **count** | **integer**| Number of requested items, default - &#x60;10&#x60; | [optional] 
 **include.old** | **character**| Include trades older than 7 days, default - &#x60;false&#x60; | [optional] 
 **sorting** | **character**| Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) | [optional] 

### Return type

**object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json



# **PrivateGetUserTradesByInstrumentGet**
> object PrivateGetUserTradesByInstrumentGet(instrument.name, start.seq=var.start.seq, end.seq=var.end.seq, count=var.count, include.old=var.include.old, sorting=var.sorting)

Retrieve the latest user trades that have occurred for a specific instrument.

### Example
```R
library(openapi)

var.instrument.name <- 'BTC-PERPETUAL' # character | Instrument name
var.start.seq <- 56 # integer | The sequence number of the first trade to be returned
var.end.seq <- 56 # integer | The sequence number of the last trade to be returned
var.count <- 56 # integer | Number of requested items, default - `10`
var.include.old <- 'include.old_example' # character | Include trades older than 7 days, default - `false`
var.sorting <- 'sorting_example' # character | Direction of results sorting (`default` value means no sorting, results will be returned in order in which they left the database)

#Retrieve the latest user trades that have occurred for a specific instrument.
api.instance <- PrivateApi$new()
# Configure HTTP basic authorization: bearerAuth
api.instance$apiClient$username <- 'TODO_YOUR_USERNAME';
api.instance$apiClient$password <- 'TODO_YOUR_PASSWORD';
result <- api.instance$PrivateGetUserTradesByInstrumentGet(var.instrument.name, start.seq=var.start.seq, end.seq=var.end.seq, count=var.count, include.old=var.include.old, sorting=var.sorting)
dput(result)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrument.name** | **character**| Instrument name | 
 **start.seq** | **integer**| The sequence number of the first trade to be returned | [optional] 
 **end.seq** | **integer**| The sequence number of the last trade to be returned | [optional] 
 **count** | **integer**| Number of requested items, default - &#x60;10&#x60; | [optional] 
 **include.old** | **character**| Include trades older than 7 days, default - &#x60;false&#x60; | [optional] 
 **sorting** | **character**| Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) | [optional] 

### Return type

**object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json



# **PrivateGetUserTradesByOrderGet**
> object PrivateGetUserTradesByOrderGet(order.id, sorting=var.sorting)

Retrieve the list of user trades that was created for given order

### Example
```R
library(openapi)

var.order.id <- 'ETH-100234' # character | The order id
var.sorting <- 'sorting_example' # character | Direction of results sorting (`default` value means no sorting, results will be returned in order in which they left the database)

#Retrieve the list of user trades that was created for given order
api.instance <- PrivateApi$new()
# Configure HTTP basic authorization: bearerAuth
api.instance$apiClient$username <- 'TODO_YOUR_USERNAME';
api.instance$apiClient$password <- 'TODO_YOUR_PASSWORD';
result <- api.instance$PrivateGetUserTradesByOrderGet(var.order.id, sorting=var.sorting)
dput(result)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **order.id** | **character**| The order id | 
 **sorting** | **character**| Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) | [optional] 

### Return type

**object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json



# **PrivateGetWithdrawalsGet**
> object PrivateGetWithdrawalsGet(currency, count=var.count, offset=var.offset)

Retrieve the latest users withdrawals

### Example
```R
library(openapi)

var.currency <- 'currency_example' # character | The currency symbol
var.count <- 56 # integer | Number of requested items, default - `10`
var.offset <- 10 # integer | The offset for pagination, default - `0`

#Retrieve the latest users withdrawals
api.instance <- PrivateApi$new()
# Configure HTTP basic authorization: bearerAuth
api.instance$apiClient$username <- 'TODO_YOUR_USERNAME';
api.instance$apiClient$password <- 'TODO_YOUR_PASSWORD';
result <- api.instance$PrivateGetWithdrawalsGet(var.currency, count=var.count, offset=var.offset)
dput(result)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **character**| The currency symbol | 
 **count** | **integer**| Number of requested items, default - &#x60;10&#x60; | [optional] 
 **offset** | **integer**| The offset for pagination, default - &#x60;0&#x60; | [optional] 

### Return type

**object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json



# **PrivateRemoveFromAddressBookGet**
> object PrivateRemoveFromAddressBookGet(currency, type, address, tfa=var.tfa)

Adds new entry to address book of given type

### Example
```R
library(openapi)

var.currency <- 'currency_example' # character | The currency symbol
var.type <- 'type_example' # character | Address book type
var.address <- 'address_example' # character | Address in currency format, it must be in address book
var.tfa <- 'tfa_example' # character | TFA code, required when TFA is enabled for current account

#Adds new entry to address book of given type
api.instance <- PrivateApi$new()
# Configure HTTP basic authorization: bearerAuth
api.instance$apiClient$username <- 'TODO_YOUR_USERNAME';
api.instance$apiClient$password <- 'TODO_YOUR_PASSWORD';
result <- api.instance$PrivateRemoveFromAddressBookGet(var.currency, var.type, var.address, tfa=var.tfa)
dput(result)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **character**| The currency symbol | 
 **type** | **character**| Address book type | 
 **address** | **character**| Address in currency format, it must be in address book | 
 **tfa** | **character**| TFA code, required when TFA is enabled for current account | [optional] 

### Return type

**object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json



# **PrivateSellGet**
> object PrivateSellGet(instrument.name, amount, type=var.type, label=var.label, price=var.price, time.in.force='good_til_cancelled', max.show=1, post.only=TRUE, reduce.only=FALSE, stop.price=var.stop.price, trigger=var.trigger, advanced=var.advanced)

Places a sell order for an instrument.

### Example
```R
library(openapi)

var.instrument.name <- 'BTC-PERPETUAL' # character | Instrument name
var.amount <- 3.4 # numeric | It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH
var.type <- 'type_example' # character | The order type, default: `\"limit\"`
var.label <- 'label_example' # character | user defined label for the order (maximum 32 characters)
var.price <- 3.4 # numeric | <p>The order price in base currency (Only for limit and stop_limit orders)</p> <p>When adding order with advanced=usd, the field price should be the option price value in USD.</p> <p>When adding order with advanced=implv, the field price should be a value of implied volatility in percentages. For example,  price=100, means implied volatility of 100%</p>
var.time.in.force <- 'good_til_cancelled' # character | <p>Specifies how long the order remains in effect. Default `\"good_til_cancelled\"`</p> <ul> <li>`\"good_til_cancelled\"` - unfilled order remains in order book until cancelled</li> <li>`\"fill_or_kill\"` - execute a transaction immediately and completely or not at all</li> <li>`\"immediate_or_cancel\"` - execute a transaction immediately, and any portion of the order that cannot be immediately filled is cancelled</li> </ul>
var.max.show <- 1 # numeric | Maximum amount within an order to be shown to other customers, `0` for invisible order
var.post.only <- TRUE # character | <p>If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.</p> <p>Only valid in combination with time_in_force=`\"good_til_cancelled\"`</p>
var.reduce.only <- FALSE # character | If `true`, the order is considered reduce-only which is intended to only reduce a current position
var.stop.price <- 3.4 # numeric | Stop price, required for stop limit orders (Only for stop orders)
var.trigger <- 'trigger_example' # character | Defines trigger type, required for `\"stop_limit\"` order type
var.advanced <- 'advanced_example' # character | Advanced option order type. (Only for options)

#Places a sell order for an instrument.
api.instance <- PrivateApi$new()
# Configure HTTP basic authorization: bearerAuth
api.instance$apiClient$username <- 'TODO_YOUR_USERNAME';
api.instance$apiClient$password <- 'TODO_YOUR_PASSWORD';
result <- api.instance$PrivateSellGet(var.instrument.name, var.amount, type=var.type, label=var.label, price=var.price, time.in.force=var.time.in.force, max.show=var.max.show, post.only=var.post.only, reduce.only=var.reduce.only, stop.price=var.stop.price, trigger=var.trigger, advanced=var.advanced)
dput(result)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrument.name** | **character**| Instrument name | 
 **amount** | **numeric**| It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH | 
 **type** | **character**| The order type, default: &#x60;\&quot;limit\&quot;&#x60; | [optional] 
 **label** | **character**| user defined label for the order (maximum 32 characters) | [optional] 
 **price** | **numeric**| &lt;p&gt;The order price in base currency (Only for limit and stop_limit orders)&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;usd, the field price should be the option price value in USD.&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;implv, the field price should be a value of implied volatility in percentages. For example,  price&#x3D;100, means implied volatility of 100%&lt;/p&gt; | [optional] 
 **time.in.force** | **character**| &lt;p&gt;Specifies how long the order remains in effect. Default &#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; &lt;ul&gt; &lt;li&gt;&#x60;\&quot;good_til_cancelled\&quot;&#x60; - unfilled order remains in order book until cancelled&lt;/li&gt; &lt;li&gt;&#x60;\&quot;fill_or_kill\&quot;&#x60; - execute a transaction immediately and completely or not at all&lt;/li&gt; &lt;li&gt;&#x60;\&quot;immediate_or_cancel\&quot;&#x60; - execute a transaction immediately, and any portion of the order that cannot be immediately filled is cancelled&lt;/li&gt; &lt;/ul&gt; | [optional] [default to &#39;good_til_cancelled&#39;]
 **max.show** | **numeric**| Maximum amount within an order to be shown to other customers, &#x60;0&#x60; for invisible order | [optional] [default to 1]
 **post.only** | **character**| &lt;p&gt;If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.&lt;/p&gt; &lt;p&gt;Only valid in combination with time_in_force&#x3D;&#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; | [optional] [default to TRUE]
 **reduce.only** | **character**| If &#x60;true&#x60;, the order is considered reduce-only which is intended to only reduce a current position | [optional] [default to FALSE]
 **stop.price** | **numeric**| Stop price, required for stop limit orders (Only for stop orders) | [optional] 
 **trigger** | **character**| Defines trigger type, required for &#x60;\&quot;stop_limit\&quot;&#x60; order type | [optional] 
 **advanced** | **character**| Advanced option order type. (Only for options) | [optional] 

### Return type

**object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json



# **PrivateSetAnnouncementAsReadGet**
> object PrivateSetAnnouncementAsReadGet(announcement.id)

Marks an announcement as read, so it will not be shown in `get_new_announcements`.

### Example
```R
library(openapi)

var.announcement.id <- 3.4 # numeric | the ID of the announcement

#Marks an announcement as read, so it will not be shown in `get_new_announcements`.
api.instance <- PrivateApi$new()
# Configure HTTP basic authorization: bearerAuth
api.instance$apiClient$username <- 'TODO_YOUR_USERNAME';
api.instance$apiClient$password <- 'TODO_YOUR_PASSWORD';
result <- api.instance$PrivateSetAnnouncementAsReadGet(var.announcement.id)
dput(result)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **announcement.id** | **numeric**| the ID of the announcement | 

### Return type

**object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json



# **PrivateSetEmailForSubaccountGet**
> object PrivateSetEmailForSubaccountGet(sid, email)

Assign an email address to a subaccount. User will receive an email with confirmation link.

### Example
```R
library(openapi)

var.sid <- 56 # integer | The user id for the subaccount
var.email <- 'subaccount_1@email.com' # character | The email address for the subaccount

#Assign an email address to a subaccount. User will receive an email with confirmation link.
api.instance <- PrivateApi$new()
# Configure HTTP basic authorization: bearerAuth
api.instance$apiClient$username <- 'TODO_YOUR_USERNAME';
api.instance$apiClient$password <- 'TODO_YOUR_PASSWORD';
result <- api.instance$PrivateSetEmailForSubaccountGet(var.sid, var.email)
dput(result)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **sid** | **integer**| The user id for the subaccount | 
 **email** | **character**| The email address for the subaccount | 

### Return type

**object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json



# **PrivateSetEmailLanguageGet**
> object PrivateSetEmailLanguageGet(language)

Changes the language to be used for emails.

### Example
```R
library(openapi)

var.language <- 'en' # character | The abbreviated language name. Valid values include `\"en\"`, `\"ko\"`, `\"zh\"`

#Changes the language to be used for emails.
api.instance <- PrivateApi$new()
# Configure HTTP basic authorization: bearerAuth
api.instance$apiClient$username <- 'TODO_YOUR_USERNAME';
api.instance$apiClient$password <- 'TODO_YOUR_PASSWORD';
result <- api.instance$PrivateSetEmailLanguageGet(var.language)
dput(result)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **language** | **character**| The abbreviated language name. Valid values include &#x60;\&quot;en\&quot;&#x60;, &#x60;\&quot;ko\&quot;&#x60;, &#x60;\&quot;zh\&quot;&#x60; | 

### Return type

**object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json



# **PrivateSetPasswordForSubaccountGet**
> object PrivateSetPasswordForSubaccountGet(sid, password)

Set the password for the subaccount

### Example
```R
library(openapi)

var.sid <- 56 # integer | The user id for the subaccount
var.password <- 'password_example' # character | The password for the subaccount

#Set the password for the subaccount
api.instance <- PrivateApi$new()
# Configure HTTP basic authorization: bearerAuth
api.instance$apiClient$username <- 'TODO_YOUR_USERNAME';
api.instance$apiClient$password <- 'TODO_YOUR_PASSWORD';
result <- api.instance$PrivateSetPasswordForSubaccountGet(var.sid, var.password)
dput(result)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **sid** | **integer**| The user id for the subaccount | 
 **password** | **character**| The password for the subaccount | 

### Return type

**object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json



# **PrivateSubmitTransferToSubaccountGet**
> object PrivateSubmitTransferToSubaccountGet(currency, amount, destination)

Transfer funds to a subaccount.

### Example
```R
library(openapi)

var.currency <- 'currency_example' # character | The currency symbol
var.amount <- 3.4 # numeric | Amount of funds to be transferred
var.destination <- 1 # integer | Id of destination subaccount

#Transfer funds to a subaccount.
api.instance <- PrivateApi$new()
# Configure HTTP basic authorization: bearerAuth
api.instance$apiClient$username <- 'TODO_YOUR_USERNAME';
api.instance$apiClient$password <- 'TODO_YOUR_PASSWORD';
result <- api.instance$PrivateSubmitTransferToSubaccountGet(var.currency, var.amount, var.destination)
dput(result)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **character**| The currency symbol | 
 **amount** | **numeric**| Amount of funds to be transferred | 
 **destination** | **integer**| Id of destination subaccount | 

### Return type

**object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json



# **PrivateSubmitTransferToUserGet**
> object PrivateSubmitTransferToUserGet(currency, amount, destination, tfa=var.tfa)

Transfer funds to a another user.

### Example
```R
library(openapi)

var.currency <- 'currency_example' # character | The currency symbol
var.amount <- 3.4 # numeric | Amount of funds to be transferred
var.destination <- 'destination_example' # character | Destination address from address book
var.tfa <- 'tfa_example' # character | TFA code, required when TFA is enabled for current account

#Transfer funds to a another user.
api.instance <- PrivateApi$new()
# Configure HTTP basic authorization: bearerAuth
api.instance$apiClient$username <- 'TODO_YOUR_USERNAME';
api.instance$apiClient$password <- 'TODO_YOUR_PASSWORD';
result <- api.instance$PrivateSubmitTransferToUserGet(var.currency, var.amount, var.destination, tfa=var.tfa)
dput(result)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **character**| The currency symbol | 
 **amount** | **numeric**| Amount of funds to be transferred | 
 **destination** | **character**| Destination address from address book | 
 **tfa** | **character**| TFA code, required when TFA is enabled for current account | [optional] 

### Return type

**object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json



# **PrivateToggleDepositAddressCreationGet**
> object PrivateToggleDepositAddressCreationGet(currency, state)

Enable or disable deposit address creation

### Example
```R
library(openapi)

var.currency <- 'currency_example' # character | The currency symbol
var.state <- 'state_example' # character | 

#Enable or disable deposit address creation
api.instance <- PrivateApi$new()
# Configure HTTP basic authorization: bearerAuth
api.instance$apiClient$username <- 'TODO_YOUR_USERNAME';
api.instance$apiClient$password <- 'TODO_YOUR_PASSWORD';
result <- api.instance$PrivateToggleDepositAddressCreationGet(var.currency, var.state)
dput(result)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **character**| The currency symbol | 
 **state** | **character**|  | 

### Return type

**object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json



# **PrivateToggleNotificationsFromSubaccountGet**
> object PrivateToggleNotificationsFromSubaccountGet(sid, state)

Enable or disable sending of notifications for the subaccount.

### Example
```R
library(openapi)

var.sid <- 56 # integer | The user id for the subaccount
var.state <- 'state_example' # character | enable (`true`) or disable (`false`) notifications

#Enable or disable sending of notifications for the subaccount.
api.instance <- PrivateApi$new()
# Configure HTTP basic authorization: bearerAuth
api.instance$apiClient$username <- 'TODO_YOUR_USERNAME';
api.instance$apiClient$password <- 'TODO_YOUR_PASSWORD';
result <- api.instance$PrivateToggleNotificationsFromSubaccountGet(var.sid, var.state)
dput(result)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **sid** | **integer**| The user id for the subaccount | 
 **state** | **character**| enable (&#x60;true&#x60;) or disable (&#x60;false&#x60;) notifications | 

### Return type

**object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json



# **PrivateToggleSubaccountLoginGet**
> object PrivateToggleSubaccountLoginGet(sid, state)

Enable or disable login for a subaccount. If login is disabled and a session for the subaccount exists, this session will be terminated.

### Example
```R
library(openapi)

var.sid <- 56 # integer | The user id for the subaccount
var.state <- 'state_example' # character | enable or disable login.

#Enable or disable login for a subaccount. If login is disabled and a session for the subaccount exists, this session will be terminated.
api.instance <- PrivateApi$new()
# Configure HTTP basic authorization: bearerAuth
api.instance$apiClient$username <- 'TODO_YOUR_USERNAME';
api.instance$apiClient$password <- 'TODO_YOUR_PASSWORD';
result <- api.instance$PrivateToggleSubaccountLoginGet(var.sid, var.state)
dput(result)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **sid** | **integer**| The user id for the subaccount | 
 **state** | **character**| enable or disable login. | 

### Return type

**object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json



# **PrivateWithdrawGet**
> object PrivateWithdrawGet(currency, address, amount, priority=var.priority, tfa=var.tfa)

Creates a new withdrawal request

### Example
```R
library(openapi)

var.currency <- 'currency_example' # character | The currency symbol
var.address <- 'address_example' # character | Address in currency format, it must be in address book
var.amount <- 3.4 # numeric | Amount of funds to be withdrawn
var.priority <- 'priority_example' # character | Withdrawal priority, optional for BTC, default: `high`
var.tfa <- 'tfa_example' # character | TFA code, required when TFA is enabled for current account

#Creates a new withdrawal request
api.instance <- PrivateApi$new()
# Configure HTTP basic authorization: bearerAuth
api.instance$apiClient$username <- 'TODO_YOUR_USERNAME';
api.instance$apiClient$password <- 'TODO_YOUR_PASSWORD';
result <- api.instance$PrivateWithdrawGet(var.currency, var.address, var.amount, priority=var.priority, tfa=var.tfa)
dput(result)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **character**| The currency symbol | 
 **address** | **character**| Address in currency format, it must be in address book | 
 **amount** | **numeric**| Amount of funds to be withdrawn | 
 **priority** | **character**| Withdrawal priority, optional for BTC, default: &#x60;high&#x60; | [optional] 
 **tfa** | **character**| TFA code, required when TFA is enabled for current account | [optional] 

### Return type

**object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json



