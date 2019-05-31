# \PrivateApi

All URIs are relative to *https://www.deribit.com/api/v2*

Method | HTTP request | Description
------------- | ------------- | -------------
[**PrivateAddToAddressBookGet**](PrivateApi.md#PrivateAddToAddressBookGet) | **Get** /private/add_to_address_book | Adds new entry to address book of given type
[**PrivateBuyGet**](PrivateApi.md#PrivateBuyGet) | **Get** /private/buy | Places a buy order for an instrument.
[**PrivateCancelAllByCurrencyGet**](PrivateApi.md#PrivateCancelAllByCurrencyGet) | **Get** /private/cancel_all_by_currency | Cancels all orders by currency, optionally filtered by instrument kind and/or order type.
[**PrivateCancelAllByInstrumentGet**](PrivateApi.md#PrivateCancelAllByInstrumentGet) | **Get** /private/cancel_all_by_instrument | Cancels all orders by instrument, optionally filtered by order type.
[**PrivateCancelAllGet**](PrivateApi.md#PrivateCancelAllGet) | **Get** /private/cancel_all | This method cancels all users orders and stop orders within all currencies and instrument kinds.
[**PrivateCancelGet**](PrivateApi.md#PrivateCancelGet) | **Get** /private/cancel | Cancel an order, specified by order id
[**PrivateCancelTransferByIdGet**](PrivateApi.md#PrivateCancelTransferByIdGet) | **Get** /private/cancel_transfer_by_id | Cancel transfer
[**PrivateCancelWithdrawalGet**](PrivateApi.md#PrivateCancelWithdrawalGet) | **Get** /private/cancel_withdrawal | Cancels withdrawal request
[**PrivateChangeSubaccountNameGet**](PrivateApi.md#PrivateChangeSubaccountNameGet) | **Get** /private/change_subaccount_name | Change the user name for a subaccount
[**PrivateClosePositionGet**](PrivateApi.md#PrivateClosePositionGet) | **Get** /private/close_position | Makes closing position reduce only order .
[**PrivateCreateDepositAddressGet**](PrivateApi.md#PrivateCreateDepositAddressGet) | **Get** /private/create_deposit_address | Creates deposit address in currency
[**PrivateCreateSubaccountGet**](PrivateApi.md#PrivateCreateSubaccountGet) | **Get** /private/create_subaccount | Create a new subaccount
[**PrivateDisableTfaForSubaccountGet**](PrivateApi.md#PrivateDisableTfaForSubaccountGet) | **Get** /private/disable_tfa_for_subaccount | Disable two factor authentication for a subaccount.
[**PrivateDisableTfaWithRecoveryCodeGet**](PrivateApi.md#PrivateDisableTfaWithRecoveryCodeGet) | **Get** /private/disable_tfa_with_recovery_code | Disables TFA with one time recovery code
[**PrivateEditGet**](PrivateApi.md#PrivateEditGet) | **Get** /private/edit | Change price, amount and/or other properties of an order.
[**PrivateGetAccountSummaryGet**](PrivateApi.md#PrivateGetAccountSummaryGet) | **Get** /private/get_account_summary | Retrieves user account summary.
[**PrivateGetAddressBookGet**](PrivateApi.md#PrivateGetAddressBookGet) | **Get** /private/get_address_book | Retrieves address book of given type
[**PrivateGetCurrentDepositAddressGet**](PrivateApi.md#PrivateGetCurrentDepositAddressGet) | **Get** /private/get_current_deposit_address | Retrieve deposit address for currency
[**PrivateGetDepositsGet**](PrivateApi.md#PrivateGetDepositsGet) | **Get** /private/get_deposits | Retrieve the latest users deposits
[**PrivateGetEmailLanguageGet**](PrivateApi.md#PrivateGetEmailLanguageGet) | **Get** /private/get_email_language | Retrieves the language to be used for emails.
[**PrivateGetMarginsGet**](PrivateApi.md#PrivateGetMarginsGet) | **Get** /private/get_margins | Get margins for given instrument, amount and price.
[**PrivateGetNewAnnouncementsGet**](PrivateApi.md#PrivateGetNewAnnouncementsGet) | **Get** /private/get_new_announcements | Retrieves announcements that have not been marked read by the user.
[**PrivateGetOpenOrdersByCurrencyGet**](PrivateApi.md#PrivateGetOpenOrdersByCurrencyGet) | **Get** /private/get_open_orders_by_currency | Retrieves list of user&#39;s open orders.
[**PrivateGetOpenOrdersByInstrumentGet**](PrivateApi.md#PrivateGetOpenOrdersByInstrumentGet) | **Get** /private/get_open_orders_by_instrument | Retrieves list of user&#39;s open orders within given Instrument.
[**PrivateGetOrderHistoryByCurrencyGet**](PrivateApi.md#PrivateGetOrderHistoryByCurrencyGet) | **Get** /private/get_order_history_by_currency | Retrieves history of orders that have been partially or fully filled.
[**PrivateGetOrderHistoryByInstrumentGet**](PrivateApi.md#PrivateGetOrderHistoryByInstrumentGet) | **Get** /private/get_order_history_by_instrument | Retrieves history of orders that have been partially or fully filled.
[**PrivateGetOrderMarginByIdsGet**](PrivateApi.md#PrivateGetOrderMarginByIdsGet) | **Get** /private/get_order_margin_by_ids | Retrieves initial margins of given orders
[**PrivateGetOrderStateGet**](PrivateApi.md#PrivateGetOrderStateGet) | **Get** /private/get_order_state | Retrieve the current state of an order.
[**PrivateGetPositionGet**](PrivateApi.md#PrivateGetPositionGet) | **Get** /private/get_position | Retrieve user position.
[**PrivateGetPositionsGet**](PrivateApi.md#PrivateGetPositionsGet) | **Get** /private/get_positions | Retrieve user positions.
[**PrivateGetSettlementHistoryByCurrencyGet**](PrivateApi.md#PrivateGetSettlementHistoryByCurrencyGet) | **Get** /private/get_settlement_history_by_currency | Retrieves settlement, delivery and bankruptcy events that have affected your account.
[**PrivateGetSettlementHistoryByInstrumentGet**](PrivateApi.md#PrivateGetSettlementHistoryByInstrumentGet) | **Get** /private/get_settlement_history_by_instrument | Retrieves public settlement, delivery and bankruptcy events filtered by instrument name
[**PrivateGetSubaccountsGet**](PrivateApi.md#PrivateGetSubaccountsGet) | **Get** /private/get_subaccounts | Get information about subaccounts
[**PrivateGetTransfersGet**](PrivateApi.md#PrivateGetTransfersGet) | **Get** /private/get_transfers | Adds new entry to address book of given type
[**PrivateGetUserTradesByCurrencyAndTimeGet**](PrivateApi.md#PrivateGetUserTradesByCurrencyAndTimeGet) | **Get** /private/get_user_trades_by_currency_and_time | Retrieve the latest user trades that have occurred for instruments in a specific currency symbol and within given time range.
[**PrivateGetUserTradesByCurrencyGet**](PrivateApi.md#PrivateGetUserTradesByCurrencyGet) | **Get** /private/get_user_trades_by_currency | Retrieve the latest user trades that have occurred for instruments in a specific currency symbol.
[**PrivateGetUserTradesByInstrumentAndTimeGet**](PrivateApi.md#PrivateGetUserTradesByInstrumentAndTimeGet) | **Get** /private/get_user_trades_by_instrument_and_time | Retrieve the latest user trades that have occurred for a specific instrument and within given time range.
[**PrivateGetUserTradesByInstrumentGet**](PrivateApi.md#PrivateGetUserTradesByInstrumentGet) | **Get** /private/get_user_trades_by_instrument | Retrieve the latest user trades that have occurred for a specific instrument.
[**PrivateGetUserTradesByOrderGet**](PrivateApi.md#PrivateGetUserTradesByOrderGet) | **Get** /private/get_user_trades_by_order | Retrieve the list of user trades that was created for given order
[**PrivateGetWithdrawalsGet**](PrivateApi.md#PrivateGetWithdrawalsGet) | **Get** /private/get_withdrawals | Retrieve the latest users withdrawals
[**PrivateRemoveFromAddressBookGet**](PrivateApi.md#PrivateRemoveFromAddressBookGet) | **Get** /private/remove_from_address_book | Adds new entry to address book of given type
[**PrivateSellGet**](PrivateApi.md#PrivateSellGet) | **Get** /private/sell | Places a sell order for an instrument.
[**PrivateSetAnnouncementAsReadGet**](PrivateApi.md#PrivateSetAnnouncementAsReadGet) | **Get** /private/set_announcement_as_read | Marks an announcement as read, so it will not be shown in &#x60;get_new_announcements&#x60;.
[**PrivateSetEmailForSubaccountGet**](PrivateApi.md#PrivateSetEmailForSubaccountGet) | **Get** /private/set_email_for_subaccount | Assign an email address to a subaccount. User will receive an email with confirmation link.
[**PrivateSetEmailLanguageGet**](PrivateApi.md#PrivateSetEmailLanguageGet) | **Get** /private/set_email_language | Changes the language to be used for emails.
[**PrivateSetPasswordForSubaccountGet**](PrivateApi.md#PrivateSetPasswordForSubaccountGet) | **Get** /private/set_password_for_subaccount | Set the password for the subaccount
[**PrivateSubmitTransferToSubaccountGet**](PrivateApi.md#PrivateSubmitTransferToSubaccountGet) | **Get** /private/submit_transfer_to_subaccount | Transfer funds to a subaccount.
[**PrivateSubmitTransferToUserGet**](PrivateApi.md#PrivateSubmitTransferToUserGet) | **Get** /private/submit_transfer_to_user | Transfer funds to a another user.
[**PrivateToggleDepositAddressCreationGet**](PrivateApi.md#PrivateToggleDepositAddressCreationGet) | **Get** /private/toggle_deposit_address_creation | Enable or disable deposit address creation
[**PrivateToggleNotificationsFromSubaccountGet**](PrivateApi.md#PrivateToggleNotificationsFromSubaccountGet) | **Get** /private/toggle_notifications_from_subaccount | Enable or disable sending of notifications for the subaccount.
[**PrivateToggleSubaccountLoginGet**](PrivateApi.md#PrivateToggleSubaccountLoginGet) | **Get** /private/toggle_subaccount_login | Enable or disable login for a subaccount. If login is disabled and a session for the subaccount exists, this session will be terminated.
[**PrivateWithdrawGet**](PrivateApi.md#PrivateWithdrawGet) | **Get** /private/withdraw | Creates a new withdrawal request



## PrivateAddToAddressBookGet

> map[string]interface{} PrivateAddToAddressBookGet(ctx, currency, type_, address, name, optional)
Adds new entry to address book of given type

### Required Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
**ctx** | **context.Context** | context for authentication, logging, cancellation, deadlines, tracing, etc.
**currency** | **string**| The currency symbol | 
**type_** | **string**| Address book type | 
**address** | **string**| Address in currency format, it must be in address book | 
**name** | **string**| Name of address book entry | 
 **optional** | ***PrivateAddToAddressBookGetOpts** | optional parameters | nil if no parameters

### Optional Parameters

Optional parameters are passed through a pointer to a PrivateAddToAddressBookGetOpts struct


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------




 **tfa** | **optional.String**| TFA code, required when TFA is enabled for current account | 

### Return type

[**map[string]interface{}**](map[string]interface{}.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateBuyGet

> map[string]interface{} PrivateBuyGet(ctx, instrumentName, amount, optional)
Places a buy order for an instrument.

### Required Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
**ctx** | **context.Context** | context for authentication, logging, cancellation, deadlines, tracing, etc.
**instrumentName** | **string**| Instrument name | 
**amount** | **float32**| It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH | 
 **optional** | ***PrivateBuyGetOpts** | optional parameters | nil if no parameters

### Optional Parameters

Optional parameters are passed through a pointer to a PrivateBuyGetOpts struct


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------


 **type_** | **optional.String**| The order type, default: &#x60;\&quot;limit\&quot;&#x60; | 
 **label** | **optional.String**| user defined label for the order (maximum 32 characters) | 
 **price** | **optional.Float32**| &lt;p&gt;The order price in base currency (Only for limit and stop_limit orders)&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;usd, the field price should be the option price value in USD.&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;implv, the field price should be a value of implied volatility in percentages. For example,  price&#x3D;100, means implied volatility of 100%&lt;/p&gt; | 
 **timeInForce** | **optional.String**| &lt;p&gt;Specifies how long the order remains in effect. Default &#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; &lt;ul&gt; &lt;li&gt;&#x60;\&quot;good_til_cancelled\&quot;&#x60; - unfilled order remains in order book until cancelled&lt;/li&gt; &lt;li&gt;&#x60;\&quot;fill_or_kill\&quot;&#x60; - execute a transaction immediately and completely or not at all&lt;/li&gt; &lt;li&gt;&#x60;\&quot;immediate_or_cancel\&quot;&#x60; - execute a transaction immediately, and any portion of the order that cannot be immediately filled is cancelled&lt;/li&gt; &lt;/ul&gt; | [default to good_til_cancelled]
 **maxShow** | **optional.Float32**| Maximum amount within an order to be shown to other customers, &#x60;0&#x60; for invisible order | [default to 1]
 **postOnly** | **optional.Bool**| &lt;p&gt;If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.&lt;/p&gt; &lt;p&gt;Only valid in combination with time_in_force&#x3D;&#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; | [default to true]
 **reduceOnly** | **optional.Bool**| If &#x60;true&#x60;, the order is considered reduce-only which is intended to only reduce a current position | [default to false]
 **stopPrice** | **optional.Float32**| Stop price, required for stop limit orders (Only for stop orders) | 
 **trigger** | **optional.String**| Defines trigger type, required for &#x60;\&quot;stop_limit\&quot;&#x60; order type | 
 **advanced** | **optional.String**| Advanced option order type. (Only for options) | 

### Return type

[**map[string]interface{}**](map[string]interface{}.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateCancelAllByCurrencyGet

> map[string]interface{} PrivateCancelAllByCurrencyGet(ctx, currency, optional)
Cancels all orders by currency, optionally filtered by instrument kind and/or order type.

### Required Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
**ctx** | **context.Context** | context for authentication, logging, cancellation, deadlines, tracing, etc.
**currency** | **string**| The currency symbol | 
 **optional** | ***PrivateCancelAllByCurrencyGetOpts** | optional parameters | nil if no parameters

### Optional Parameters

Optional parameters are passed through a pointer to a PrivateCancelAllByCurrencyGetOpts struct


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------

 **kind** | **optional.String**| Instrument kind, if not provided instruments of all kinds are considered | 
 **type_** | **optional.String**| Order type - limit, stop or all, default - &#x60;all&#x60; | 

### Return type

[**map[string]interface{}**](map[string]interface{}.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateCancelAllByInstrumentGet

> map[string]interface{} PrivateCancelAllByInstrumentGet(ctx, instrumentName, optional)
Cancels all orders by instrument, optionally filtered by order type.

### Required Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
**ctx** | **context.Context** | context for authentication, logging, cancellation, deadlines, tracing, etc.
**instrumentName** | **string**| Instrument name | 
 **optional** | ***PrivateCancelAllByInstrumentGetOpts** | optional parameters | nil if no parameters

### Optional Parameters

Optional parameters are passed through a pointer to a PrivateCancelAllByInstrumentGetOpts struct


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------

 **type_** | **optional.String**| Order type - limit, stop or all, default - &#x60;all&#x60; | 

### Return type

[**map[string]interface{}**](map[string]interface{}.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateCancelAllGet

> map[string]interface{} PrivateCancelAllGet(ctx, )
This method cancels all users orders and stop orders within all currencies and instrument kinds.

### Required Parameters

This endpoint does not need any parameter.

### Return type

[**map[string]interface{}**](map[string]interface{}.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateCancelGet

> map[string]interface{} PrivateCancelGet(ctx, orderId)
Cancel an order, specified by order id

### Required Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
**ctx** | **context.Context** | context for authentication, logging, cancellation, deadlines, tracing, etc.
**orderId** | **string**| The order id | 

### Return type

[**map[string]interface{}**](map[string]interface{}.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateCancelTransferByIdGet

> map[string]interface{} PrivateCancelTransferByIdGet(ctx, currency, id, optional)
Cancel transfer

### Required Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
**ctx** | **context.Context** | context for authentication, logging, cancellation, deadlines, tracing, etc.
**currency** | **string**| The currency symbol | 
**id** | **int32**| Id of transfer | 
 **optional** | ***PrivateCancelTransferByIdGetOpts** | optional parameters | nil if no parameters

### Optional Parameters

Optional parameters are passed through a pointer to a PrivateCancelTransferByIdGetOpts struct


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------


 **tfa** | **optional.String**| TFA code, required when TFA is enabled for current account | 

### Return type

[**map[string]interface{}**](map[string]interface{}.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateCancelWithdrawalGet

> map[string]interface{} PrivateCancelWithdrawalGet(ctx, currency, id)
Cancels withdrawal request

### Required Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
**ctx** | **context.Context** | context for authentication, logging, cancellation, deadlines, tracing, etc.
**currency** | **string**| The currency symbol | 
**id** | **float32**| The withdrawal id | 

### Return type

[**map[string]interface{}**](map[string]interface{}.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateChangeSubaccountNameGet

> map[string]interface{} PrivateChangeSubaccountNameGet(ctx, sid, name)
Change the user name for a subaccount

### Required Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
**ctx** | **context.Context** | context for authentication, logging, cancellation, deadlines, tracing, etc.
**sid** | **int32**| The user id for the subaccount | 
**name** | **string**| The new user name | 

### Return type

[**map[string]interface{}**](map[string]interface{}.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateClosePositionGet

> map[string]interface{} PrivateClosePositionGet(ctx, instrumentName, type_, optional)
Makes closing position reduce only order .

### Required Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
**ctx** | **context.Context** | context for authentication, logging, cancellation, deadlines, tracing, etc.
**instrumentName** | **string**| Instrument name | 
**type_** | **string**| The order type | 
 **optional** | ***PrivateClosePositionGetOpts** | optional parameters | nil if no parameters

### Optional Parameters

Optional parameters are passed through a pointer to a PrivateClosePositionGetOpts struct


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------


 **price** | **optional.Float32**| Optional price for limit order. | 

### Return type

[**map[string]interface{}**](map[string]interface{}.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateCreateDepositAddressGet

> map[string]interface{} PrivateCreateDepositAddressGet(ctx, currency)
Creates deposit address in currency

### Required Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
**ctx** | **context.Context** | context for authentication, logging, cancellation, deadlines, tracing, etc.
**currency** | **string**| The currency symbol | 

### Return type

[**map[string]interface{}**](map[string]interface{}.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateCreateSubaccountGet

> map[string]interface{} PrivateCreateSubaccountGet(ctx, )
Create a new subaccount

### Required Parameters

This endpoint does not need any parameter.

### Return type

[**map[string]interface{}**](map[string]interface{}.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateDisableTfaForSubaccountGet

> map[string]interface{} PrivateDisableTfaForSubaccountGet(ctx, sid)
Disable two factor authentication for a subaccount.

### Required Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
**ctx** | **context.Context** | context for authentication, logging, cancellation, deadlines, tracing, etc.
**sid** | **int32**| The user id for the subaccount | 

### Return type

[**map[string]interface{}**](map[string]interface{}.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateDisableTfaWithRecoveryCodeGet

> map[string]interface{} PrivateDisableTfaWithRecoveryCodeGet(ctx, password, code)
Disables TFA with one time recovery code

### Required Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
**ctx** | **context.Context** | context for authentication, logging, cancellation, deadlines, tracing, etc.
**password** | **string**| The password for the subaccount | 
**code** | **string**| One time recovery code | 

### Return type

[**map[string]interface{}**](map[string]interface{}.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateEditGet

> map[string]interface{} PrivateEditGet(ctx, orderId, amount, price, optional)
Change price, amount and/or other properties of an order.

### Required Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
**ctx** | **context.Context** | context for authentication, logging, cancellation, deadlines, tracing, etc.
**orderId** | **string**| The order id | 
**amount** | **float32**| It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH | 
**price** | **float32**| &lt;p&gt;The order price in base currency.&lt;/p&gt; &lt;p&gt;When editing an option order with advanced&#x3D;usd, the field price should be the option price value in USD.&lt;/p&gt; &lt;p&gt;When editing an option order with advanced&#x3D;implv, the field price should be a value of implied volatility in percentages. For example,  price&#x3D;100, means implied volatility of 100%&lt;/p&gt; | 
 **optional** | ***PrivateEditGetOpts** | optional parameters | nil if no parameters

### Optional Parameters

Optional parameters are passed through a pointer to a PrivateEditGetOpts struct


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------



 **postOnly** | **optional.Bool**| &lt;p&gt;If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.&lt;/p&gt; &lt;p&gt;Only valid in combination with time_in_force&#x3D;&#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; | [default to true]
 **advanced** | **optional.String**| Advanced option order type. If you have posted an advanced option order, it is necessary to re-supply this parameter when editing it (Only for options) | 
 **stopPrice** | **optional.Float32**| Stop price, required for stop limit orders (Only for stop orders) | 

### Return type

[**map[string]interface{}**](map[string]interface{}.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateGetAccountSummaryGet

> map[string]interface{} PrivateGetAccountSummaryGet(ctx, currency, optional)
Retrieves user account summary.

### Required Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
**ctx** | **context.Context** | context for authentication, logging, cancellation, deadlines, tracing, etc.
**currency** | **string**| The currency symbol | 
 **optional** | ***PrivateGetAccountSummaryGetOpts** | optional parameters | nil if no parameters

### Optional Parameters

Optional parameters are passed through a pointer to a PrivateGetAccountSummaryGetOpts struct


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------

 **extended** | **optional.Bool**| Include additional fields | 

### Return type

[**map[string]interface{}**](map[string]interface{}.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateGetAddressBookGet

> map[string]interface{} PrivateGetAddressBookGet(ctx, currency, type_)
Retrieves address book of given type

### Required Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
**ctx** | **context.Context** | context for authentication, logging, cancellation, deadlines, tracing, etc.
**currency** | **string**| The currency symbol | 
**type_** | **string**| Address book type | 

### Return type

[**map[string]interface{}**](map[string]interface{}.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateGetCurrentDepositAddressGet

> map[string]interface{} PrivateGetCurrentDepositAddressGet(ctx, currency)
Retrieve deposit address for currency

### Required Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
**ctx** | **context.Context** | context for authentication, logging, cancellation, deadlines, tracing, etc.
**currency** | **string**| The currency symbol | 

### Return type

[**map[string]interface{}**](map[string]interface{}.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateGetDepositsGet

> map[string]interface{} PrivateGetDepositsGet(ctx, currency, optional)
Retrieve the latest users deposits

### Required Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
**ctx** | **context.Context** | context for authentication, logging, cancellation, deadlines, tracing, etc.
**currency** | **string**| The currency symbol | 
 **optional** | ***PrivateGetDepositsGetOpts** | optional parameters | nil if no parameters

### Optional Parameters

Optional parameters are passed through a pointer to a PrivateGetDepositsGetOpts struct


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------

 **count** | **optional.Int32**| Number of requested items, default - &#x60;10&#x60; | 
 **offset** | **optional.Int32**| The offset for pagination, default - &#x60;0&#x60; | 

### Return type

[**map[string]interface{}**](map[string]interface{}.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateGetEmailLanguageGet

> map[string]interface{} PrivateGetEmailLanguageGet(ctx, )
Retrieves the language to be used for emails.

### Required Parameters

This endpoint does not need any parameter.

### Return type

[**map[string]interface{}**](map[string]interface{}.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateGetMarginsGet

> map[string]interface{} PrivateGetMarginsGet(ctx, instrumentName, amount, price)
Get margins for given instrument, amount and price.

### Required Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
**ctx** | **context.Context** | context for authentication, logging, cancellation, deadlines, tracing, etc.
**instrumentName** | **string**| Instrument name | 
**amount** | **float32**| Amount, integer for future, float for option. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH. | 
**price** | **float32**| Price | 

### Return type

[**map[string]interface{}**](map[string]interface{}.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateGetNewAnnouncementsGet

> map[string]interface{} PrivateGetNewAnnouncementsGet(ctx, )
Retrieves announcements that have not been marked read by the user.

### Required Parameters

This endpoint does not need any parameter.

### Return type

[**map[string]interface{}**](map[string]interface{}.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateGetOpenOrdersByCurrencyGet

> map[string]interface{} PrivateGetOpenOrdersByCurrencyGet(ctx, currency, optional)
Retrieves list of user's open orders.

### Required Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
**ctx** | **context.Context** | context for authentication, logging, cancellation, deadlines, tracing, etc.
**currency** | **string**| The currency symbol | 
 **optional** | ***PrivateGetOpenOrdersByCurrencyGetOpts** | optional parameters | nil if no parameters

### Optional Parameters

Optional parameters are passed through a pointer to a PrivateGetOpenOrdersByCurrencyGetOpts struct


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------

 **kind** | **optional.String**| Instrument kind, if not provided instruments of all kinds are considered | 
 **type_** | **optional.String**| Order type, default - &#x60;all&#x60; | 

### Return type

[**map[string]interface{}**](map[string]interface{}.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateGetOpenOrdersByInstrumentGet

> map[string]interface{} PrivateGetOpenOrdersByInstrumentGet(ctx, instrumentName, optional)
Retrieves list of user's open orders within given Instrument.

### Required Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
**ctx** | **context.Context** | context for authentication, logging, cancellation, deadlines, tracing, etc.
**instrumentName** | **string**| Instrument name | 
 **optional** | ***PrivateGetOpenOrdersByInstrumentGetOpts** | optional parameters | nil if no parameters

### Optional Parameters

Optional parameters are passed through a pointer to a PrivateGetOpenOrdersByInstrumentGetOpts struct


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------

 **type_** | **optional.String**| Order type, default - &#x60;all&#x60; | 

### Return type

[**map[string]interface{}**](map[string]interface{}.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateGetOrderHistoryByCurrencyGet

> map[string]interface{} PrivateGetOrderHistoryByCurrencyGet(ctx, currency, optional)
Retrieves history of orders that have been partially or fully filled.

### Required Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
**ctx** | **context.Context** | context for authentication, logging, cancellation, deadlines, tracing, etc.
**currency** | **string**| The currency symbol | 
 **optional** | ***PrivateGetOrderHistoryByCurrencyGetOpts** | optional parameters | nil if no parameters

### Optional Parameters

Optional parameters are passed through a pointer to a PrivateGetOrderHistoryByCurrencyGetOpts struct


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------

 **kind** | **optional.String**| Instrument kind, if not provided instruments of all kinds are considered | 
 **count** | **optional.Int32**| Number of requested items, default - &#x60;20&#x60; | 
 **offset** | **optional.Int32**| The offset for pagination, default - &#x60;0&#x60; | 
 **includeOld** | **optional.Bool**| Include in result orders older than 2 days, default - &#x60;false&#x60; | 
 **includeUnfilled** | **optional.Bool**| Include in result fully unfilled closed orders, default - &#x60;false&#x60; | 

### Return type

[**map[string]interface{}**](map[string]interface{}.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateGetOrderHistoryByInstrumentGet

> map[string]interface{} PrivateGetOrderHistoryByInstrumentGet(ctx, instrumentName, optional)
Retrieves history of orders that have been partially or fully filled.

### Required Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
**ctx** | **context.Context** | context for authentication, logging, cancellation, deadlines, tracing, etc.
**instrumentName** | **string**| Instrument name | 
 **optional** | ***PrivateGetOrderHistoryByInstrumentGetOpts** | optional parameters | nil if no parameters

### Optional Parameters

Optional parameters are passed through a pointer to a PrivateGetOrderHistoryByInstrumentGetOpts struct


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------

 **count** | **optional.Int32**| Number of requested items, default - &#x60;20&#x60; | 
 **offset** | **optional.Int32**| The offset for pagination, default - &#x60;0&#x60; | 
 **includeOld** | **optional.Bool**| Include in result orders older than 2 days, default - &#x60;false&#x60; | 
 **includeUnfilled** | **optional.Bool**| Include in result fully unfilled closed orders, default - &#x60;false&#x60; | 

### Return type

[**map[string]interface{}**](map[string]interface{}.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateGetOrderMarginByIdsGet

> map[string]interface{} PrivateGetOrderMarginByIdsGet(ctx, ids)
Retrieves initial margins of given orders

### Required Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
**ctx** | **context.Context** | context for authentication, logging, cancellation, deadlines, tracing, etc.
**ids** | [**[]string**](string.md)| Ids of orders | 

### Return type

[**map[string]interface{}**](map[string]interface{}.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateGetOrderStateGet

> map[string]interface{} PrivateGetOrderStateGet(ctx, orderId)
Retrieve the current state of an order.

### Required Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
**ctx** | **context.Context** | context for authentication, logging, cancellation, deadlines, tracing, etc.
**orderId** | **string**| The order id | 

### Return type

[**map[string]interface{}**](map[string]interface{}.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateGetPositionGet

> map[string]interface{} PrivateGetPositionGet(ctx, instrumentName)
Retrieve user position.

### Required Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
**ctx** | **context.Context** | context for authentication, logging, cancellation, deadlines, tracing, etc.
**instrumentName** | **string**| Instrument name | 

### Return type

[**map[string]interface{}**](map[string]interface{}.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateGetPositionsGet

> map[string]interface{} PrivateGetPositionsGet(ctx, currency, optional)
Retrieve user positions.

### Required Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
**ctx** | **context.Context** | context for authentication, logging, cancellation, deadlines, tracing, etc.
**currency** | **string**|  | 
 **optional** | ***PrivateGetPositionsGetOpts** | optional parameters | nil if no parameters

### Optional Parameters

Optional parameters are passed through a pointer to a PrivateGetPositionsGetOpts struct


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------

 **kind** | **optional.String**| Kind filter on positions | 

### Return type

[**map[string]interface{}**](map[string]interface{}.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateGetSettlementHistoryByCurrencyGet

> map[string]interface{} PrivateGetSettlementHistoryByCurrencyGet(ctx, currency, optional)
Retrieves settlement, delivery and bankruptcy events that have affected your account.

### Required Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
**ctx** | **context.Context** | context for authentication, logging, cancellation, deadlines, tracing, etc.
**currency** | **string**| The currency symbol | 
 **optional** | ***PrivateGetSettlementHistoryByCurrencyGetOpts** | optional parameters | nil if no parameters

### Optional Parameters

Optional parameters are passed through a pointer to a PrivateGetSettlementHistoryByCurrencyGetOpts struct


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------

 **type_** | **optional.String**| Settlement type | 
 **count** | **optional.Int32**| Number of requested items, default - &#x60;20&#x60; | 

### Return type

[**map[string]interface{}**](map[string]interface{}.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateGetSettlementHistoryByInstrumentGet

> map[string]interface{} PrivateGetSettlementHistoryByInstrumentGet(ctx, instrumentName, optional)
Retrieves public settlement, delivery and bankruptcy events filtered by instrument name

### Required Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
**ctx** | **context.Context** | context for authentication, logging, cancellation, deadlines, tracing, etc.
**instrumentName** | **string**| Instrument name | 
 **optional** | ***PrivateGetSettlementHistoryByInstrumentGetOpts** | optional parameters | nil if no parameters

### Optional Parameters

Optional parameters are passed through a pointer to a PrivateGetSettlementHistoryByInstrumentGetOpts struct


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------

 **type_** | **optional.String**| Settlement type | 
 **count** | **optional.Int32**| Number of requested items, default - &#x60;20&#x60; | 

### Return type

[**map[string]interface{}**](map[string]interface{}.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateGetSubaccountsGet

> map[string]interface{} PrivateGetSubaccountsGet(ctx, optional)
Get information about subaccounts

### Required Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
**ctx** | **context.Context** | context for authentication, logging, cancellation, deadlines, tracing, etc.
 **optional** | ***PrivateGetSubaccountsGetOpts** | optional parameters | nil if no parameters

### Optional Parameters

Optional parameters are passed through a pointer to a PrivateGetSubaccountsGetOpts struct


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **withPortfolio** | **optional.Bool**|  | 

### Return type

[**map[string]interface{}**](map[string]interface{}.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateGetTransfersGet

> map[string]interface{} PrivateGetTransfersGet(ctx, currency, optional)
Adds new entry to address book of given type

### Required Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
**ctx** | **context.Context** | context for authentication, logging, cancellation, deadlines, tracing, etc.
**currency** | **string**| The currency symbol | 
 **optional** | ***PrivateGetTransfersGetOpts** | optional parameters | nil if no parameters

### Optional Parameters

Optional parameters are passed through a pointer to a PrivateGetTransfersGetOpts struct


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------

 **count** | **optional.Int32**| Number of requested items, default - &#x60;10&#x60; | 
 **offset** | **optional.Int32**| The offset for pagination, default - &#x60;0&#x60; | 

### Return type

[**map[string]interface{}**](map[string]interface{}.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateGetUserTradesByCurrencyAndTimeGet

> map[string]interface{} PrivateGetUserTradesByCurrencyAndTimeGet(ctx, currency, startTimestamp, endTimestamp, optional)
Retrieve the latest user trades that have occurred for instruments in a specific currency symbol and within given time range.

### Required Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
**ctx** | **context.Context** | context for authentication, logging, cancellation, deadlines, tracing, etc.
**currency** | **string**| The currency symbol | 
**startTimestamp** | **int32**| The earliest timestamp to return result for | 
**endTimestamp** | **int32**| The most recent timestamp to return result for | 
 **optional** | ***PrivateGetUserTradesByCurrencyAndTimeGetOpts** | optional parameters | nil if no parameters

### Optional Parameters

Optional parameters are passed through a pointer to a PrivateGetUserTradesByCurrencyAndTimeGetOpts struct


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------



 **kind** | **optional.String**| Instrument kind, if not provided instruments of all kinds are considered | 
 **count** | **optional.Int32**| Number of requested items, default - &#x60;10&#x60; | 
 **includeOld** | **optional.Bool**| Include trades older than 7 days, default - &#x60;false&#x60; | 
 **sorting** | **optional.String**| Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) | 

### Return type

[**map[string]interface{}**](map[string]interface{}.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateGetUserTradesByCurrencyGet

> map[string]interface{} PrivateGetUserTradesByCurrencyGet(ctx, currency, optional)
Retrieve the latest user trades that have occurred for instruments in a specific currency symbol.

### Required Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
**ctx** | **context.Context** | context for authentication, logging, cancellation, deadlines, tracing, etc.
**currency** | **string**| The currency symbol | 
 **optional** | ***PrivateGetUserTradesByCurrencyGetOpts** | optional parameters | nil if no parameters

### Optional Parameters

Optional parameters are passed through a pointer to a PrivateGetUserTradesByCurrencyGetOpts struct


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------

 **kind** | **optional.String**| Instrument kind, if not provided instruments of all kinds are considered | 
 **startId** | **optional.String**| The ID number of the first trade to be returned | 
 **endId** | **optional.String**| The ID number of the last trade to be returned | 
 **count** | **optional.Int32**| Number of requested items, default - &#x60;10&#x60; | 
 **includeOld** | **optional.Bool**| Include trades older than 7 days, default - &#x60;false&#x60; | 
 **sorting** | **optional.String**| Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) | 

### Return type

[**map[string]interface{}**](map[string]interface{}.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateGetUserTradesByInstrumentAndTimeGet

> map[string]interface{} PrivateGetUserTradesByInstrumentAndTimeGet(ctx, instrumentName, startTimestamp, endTimestamp, optional)
Retrieve the latest user trades that have occurred for a specific instrument and within given time range.

### Required Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
**ctx** | **context.Context** | context for authentication, logging, cancellation, deadlines, tracing, etc.
**instrumentName** | **string**| Instrument name | 
**startTimestamp** | **int32**| The earliest timestamp to return result for | 
**endTimestamp** | **int32**| The most recent timestamp to return result for | 
 **optional** | ***PrivateGetUserTradesByInstrumentAndTimeGetOpts** | optional parameters | nil if no parameters

### Optional Parameters

Optional parameters are passed through a pointer to a PrivateGetUserTradesByInstrumentAndTimeGetOpts struct


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------



 **count** | **optional.Int32**| Number of requested items, default - &#x60;10&#x60; | 
 **includeOld** | **optional.Bool**| Include trades older than 7 days, default - &#x60;false&#x60; | 
 **sorting** | **optional.String**| Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) | 

### Return type

[**map[string]interface{}**](map[string]interface{}.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateGetUserTradesByInstrumentGet

> map[string]interface{} PrivateGetUserTradesByInstrumentGet(ctx, instrumentName, optional)
Retrieve the latest user trades that have occurred for a specific instrument.

### Required Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
**ctx** | **context.Context** | context for authentication, logging, cancellation, deadlines, tracing, etc.
**instrumentName** | **string**| Instrument name | 
 **optional** | ***PrivateGetUserTradesByInstrumentGetOpts** | optional parameters | nil if no parameters

### Optional Parameters

Optional parameters are passed through a pointer to a PrivateGetUserTradesByInstrumentGetOpts struct


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------

 **startSeq** | **optional.Int32**| The sequence number of the first trade to be returned | 
 **endSeq** | **optional.Int32**| The sequence number of the last trade to be returned | 
 **count** | **optional.Int32**| Number of requested items, default - &#x60;10&#x60; | 
 **includeOld** | **optional.Bool**| Include trades older than 7 days, default - &#x60;false&#x60; | 
 **sorting** | **optional.String**| Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) | 

### Return type

[**map[string]interface{}**](map[string]interface{}.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateGetUserTradesByOrderGet

> map[string]interface{} PrivateGetUserTradesByOrderGet(ctx, orderId, optional)
Retrieve the list of user trades that was created for given order

### Required Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
**ctx** | **context.Context** | context for authentication, logging, cancellation, deadlines, tracing, etc.
**orderId** | **string**| The order id | 
 **optional** | ***PrivateGetUserTradesByOrderGetOpts** | optional parameters | nil if no parameters

### Optional Parameters

Optional parameters are passed through a pointer to a PrivateGetUserTradesByOrderGetOpts struct


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------

 **sorting** | **optional.String**| Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) | 

### Return type

[**map[string]interface{}**](map[string]interface{}.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateGetWithdrawalsGet

> map[string]interface{} PrivateGetWithdrawalsGet(ctx, currency, optional)
Retrieve the latest users withdrawals

### Required Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
**ctx** | **context.Context** | context for authentication, logging, cancellation, deadlines, tracing, etc.
**currency** | **string**| The currency symbol | 
 **optional** | ***PrivateGetWithdrawalsGetOpts** | optional parameters | nil if no parameters

### Optional Parameters

Optional parameters are passed through a pointer to a PrivateGetWithdrawalsGetOpts struct


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------

 **count** | **optional.Int32**| Number of requested items, default - &#x60;10&#x60; | 
 **offset** | **optional.Int32**| The offset for pagination, default - &#x60;0&#x60; | 

### Return type

[**map[string]interface{}**](map[string]interface{}.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateRemoveFromAddressBookGet

> map[string]interface{} PrivateRemoveFromAddressBookGet(ctx, currency, type_, address, optional)
Adds new entry to address book of given type

### Required Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
**ctx** | **context.Context** | context for authentication, logging, cancellation, deadlines, tracing, etc.
**currency** | **string**| The currency symbol | 
**type_** | **string**| Address book type | 
**address** | **string**| Address in currency format, it must be in address book | 
 **optional** | ***PrivateRemoveFromAddressBookGetOpts** | optional parameters | nil if no parameters

### Optional Parameters

Optional parameters are passed through a pointer to a PrivateRemoveFromAddressBookGetOpts struct


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------



 **tfa** | **optional.String**| TFA code, required when TFA is enabled for current account | 

### Return type

[**map[string]interface{}**](map[string]interface{}.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateSellGet

> map[string]interface{} PrivateSellGet(ctx, instrumentName, amount, optional)
Places a sell order for an instrument.

### Required Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
**ctx** | **context.Context** | context for authentication, logging, cancellation, deadlines, tracing, etc.
**instrumentName** | **string**| Instrument name | 
**amount** | **float32**| It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH | 
 **optional** | ***PrivateSellGetOpts** | optional parameters | nil if no parameters

### Optional Parameters

Optional parameters are passed through a pointer to a PrivateSellGetOpts struct


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------


 **type_** | **optional.String**| The order type, default: &#x60;\&quot;limit\&quot;&#x60; | 
 **label** | **optional.String**| user defined label for the order (maximum 32 characters) | 
 **price** | **optional.Float32**| &lt;p&gt;The order price in base currency (Only for limit and stop_limit orders)&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;usd, the field price should be the option price value in USD.&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;implv, the field price should be a value of implied volatility in percentages. For example,  price&#x3D;100, means implied volatility of 100%&lt;/p&gt; | 
 **timeInForce** | **optional.String**| &lt;p&gt;Specifies how long the order remains in effect. Default &#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; &lt;ul&gt; &lt;li&gt;&#x60;\&quot;good_til_cancelled\&quot;&#x60; - unfilled order remains in order book until cancelled&lt;/li&gt; &lt;li&gt;&#x60;\&quot;fill_or_kill\&quot;&#x60; - execute a transaction immediately and completely or not at all&lt;/li&gt; &lt;li&gt;&#x60;\&quot;immediate_or_cancel\&quot;&#x60; - execute a transaction immediately, and any portion of the order that cannot be immediately filled is cancelled&lt;/li&gt; &lt;/ul&gt; | [default to good_til_cancelled]
 **maxShow** | **optional.Float32**| Maximum amount within an order to be shown to other customers, &#x60;0&#x60; for invisible order | [default to 1]
 **postOnly** | **optional.Bool**| &lt;p&gt;If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.&lt;/p&gt; &lt;p&gt;Only valid in combination with time_in_force&#x3D;&#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; | [default to true]
 **reduceOnly** | **optional.Bool**| If &#x60;true&#x60;, the order is considered reduce-only which is intended to only reduce a current position | [default to false]
 **stopPrice** | **optional.Float32**| Stop price, required for stop limit orders (Only for stop orders) | 
 **trigger** | **optional.String**| Defines trigger type, required for &#x60;\&quot;stop_limit\&quot;&#x60; order type | 
 **advanced** | **optional.String**| Advanced option order type. (Only for options) | 

### Return type

[**map[string]interface{}**](map[string]interface{}.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateSetAnnouncementAsReadGet

> map[string]interface{} PrivateSetAnnouncementAsReadGet(ctx, announcementId)
Marks an announcement as read, so it will not be shown in `get_new_announcements`.

### Required Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
**ctx** | **context.Context** | context for authentication, logging, cancellation, deadlines, tracing, etc.
**announcementId** | **float32**| the ID of the announcement | 

### Return type

[**map[string]interface{}**](map[string]interface{}.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateSetEmailForSubaccountGet

> map[string]interface{} PrivateSetEmailForSubaccountGet(ctx, sid, email)
Assign an email address to a subaccount. User will receive an email with confirmation link.

### Required Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
**ctx** | **context.Context** | context for authentication, logging, cancellation, deadlines, tracing, etc.
**sid** | **int32**| The user id for the subaccount | 
**email** | **string**| The email address for the subaccount | 

### Return type

[**map[string]interface{}**](map[string]interface{}.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateSetEmailLanguageGet

> map[string]interface{} PrivateSetEmailLanguageGet(ctx, language)
Changes the language to be used for emails.

### Required Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
**ctx** | **context.Context** | context for authentication, logging, cancellation, deadlines, tracing, etc.
**language** | **string**| The abbreviated language name. Valid values include &#x60;\&quot;en\&quot;&#x60;, &#x60;\&quot;ko\&quot;&#x60;, &#x60;\&quot;zh\&quot;&#x60; | 

### Return type

[**map[string]interface{}**](map[string]interface{}.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateSetPasswordForSubaccountGet

> map[string]interface{} PrivateSetPasswordForSubaccountGet(ctx, sid, password)
Set the password for the subaccount

### Required Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
**ctx** | **context.Context** | context for authentication, logging, cancellation, deadlines, tracing, etc.
**sid** | **int32**| The user id for the subaccount | 
**password** | **string**| The password for the subaccount | 

### Return type

[**map[string]interface{}**](map[string]interface{}.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateSubmitTransferToSubaccountGet

> map[string]interface{} PrivateSubmitTransferToSubaccountGet(ctx, currency, amount, destination)
Transfer funds to a subaccount.

### Required Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
**ctx** | **context.Context** | context for authentication, logging, cancellation, deadlines, tracing, etc.
**currency** | **string**| The currency symbol | 
**amount** | **float32**| Amount of funds to be transferred | 
**destination** | **int32**| Id of destination subaccount | 

### Return type

[**map[string]interface{}**](map[string]interface{}.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateSubmitTransferToUserGet

> map[string]interface{} PrivateSubmitTransferToUserGet(ctx, currency, amount, destination, optional)
Transfer funds to a another user.

### Required Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
**ctx** | **context.Context** | context for authentication, logging, cancellation, deadlines, tracing, etc.
**currency** | **string**| The currency symbol | 
**amount** | **float32**| Amount of funds to be transferred | 
**destination** | **string**| Destination address from address book | 
 **optional** | ***PrivateSubmitTransferToUserGetOpts** | optional parameters | nil if no parameters

### Optional Parameters

Optional parameters are passed through a pointer to a PrivateSubmitTransferToUserGetOpts struct


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------



 **tfa** | **optional.String**| TFA code, required when TFA is enabled for current account | 

### Return type

[**map[string]interface{}**](map[string]interface{}.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateToggleDepositAddressCreationGet

> map[string]interface{} PrivateToggleDepositAddressCreationGet(ctx, currency, state)
Enable or disable deposit address creation

### Required Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
**ctx** | **context.Context** | context for authentication, logging, cancellation, deadlines, tracing, etc.
**currency** | **string**| The currency symbol | 
**state** | **bool**|  | 

### Return type

[**map[string]interface{}**](map[string]interface{}.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateToggleNotificationsFromSubaccountGet

> map[string]interface{} PrivateToggleNotificationsFromSubaccountGet(ctx, sid, state)
Enable or disable sending of notifications for the subaccount.

### Required Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
**ctx** | **context.Context** | context for authentication, logging, cancellation, deadlines, tracing, etc.
**sid** | **int32**| The user id for the subaccount | 
**state** | **bool**| enable (&#x60;true&#x60;) or disable (&#x60;false&#x60;) notifications | 

### Return type

[**map[string]interface{}**](map[string]interface{}.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateToggleSubaccountLoginGet

> map[string]interface{} PrivateToggleSubaccountLoginGet(ctx, sid, state)
Enable or disable login for a subaccount. If login is disabled and a session for the subaccount exists, this session will be terminated.

### Required Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
**ctx** | **context.Context** | context for authentication, logging, cancellation, deadlines, tracing, etc.
**sid** | **int32**| The user id for the subaccount | 
**state** | **string**| enable or disable login. | 

### Return type

[**map[string]interface{}**](map[string]interface{}.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateWithdrawGet

> map[string]interface{} PrivateWithdrawGet(ctx, currency, address, amount, optional)
Creates a new withdrawal request

### Required Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
**ctx** | **context.Context** | context for authentication, logging, cancellation, deadlines, tracing, etc.
**currency** | **string**| The currency symbol | 
**address** | **string**| Address in currency format, it must be in address book | 
**amount** | **float32**| Amount of funds to be withdrawn | 
 **optional** | ***PrivateWithdrawGetOpts** | optional parameters | nil if no parameters

### Optional Parameters

Optional parameters are passed through a pointer to a PrivateWithdrawGetOpts struct


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------



 **priority** | **optional.String**| Withdrawal priority, optional for BTC, default: &#x60;high&#x60; | 
 **tfa** | **optional.String**| TFA code, required when TFA is enabled for current account | 

### Return type

[**map[string]interface{}**](map[string]interface{}.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)

