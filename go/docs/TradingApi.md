# \TradingApi

All URIs are relative to *https://www.deribit.com/api/v2*

Method | HTTP request | Description
------------- | ------------- | -------------
[**PrivateBuyGet**](TradingApi.md#PrivateBuyGet) | **Get** /private/buy | Places a buy order for an instrument.
[**PrivateCancelAllByCurrencyGet**](TradingApi.md#PrivateCancelAllByCurrencyGet) | **Get** /private/cancel_all_by_currency | Cancels all orders by currency, optionally filtered by instrument kind and/or order type.
[**PrivateCancelAllByInstrumentGet**](TradingApi.md#PrivateCancelAllByInstrumentGet) | **Get** /private/cancel_all_by_instrument | Cancels all orders by instrument, optionally filtered by order type.
[**PrivateCancelAllGet**](TradingApi.md#PrivateCancelAllGet) | **Get** /private/cancel_all | This method cancels all users orders and stop orders within all currencies and instrument kinds.
[**PrivateCancelGet**](TradingApi.md#PrivateCancelGet) | **Get** /private/cancel | Cancel an order, specified by order id
[**PrivateClosePositionGet**](TradingApi.md#PrivateClosePositionGet) | **Get** /private/close_position | Makes closing position reduce only order .
[**PrivateEditGet**](TradingApi.md#PrivateEditGet) | **Get** /private/edit | Change price, amount and/or other properties of an order.
[**PrivateGetMarginsGet**](TradingApi.md#PrivateGetMarginsGet) | **Get** /private/get_margins | Get margins for given instrument, amount and price.
[**PrivateGetOpenOrdersByCurrencyGet**](TradingApi.md#PrivateGetOpenOrdersByCurrencyGet) | **Get** /private/get_open_orders_by_currency | Retrieves list of user&#39;s open orders.
[**PrivateGetOpenOrdersByInstrumentGet**](TradingApi.md#PrivateGetOpenOrdersByInstrumentGet) | **Get** /private/get_open_orders_by_instrument | Retrieves list of user&#39;s open orders within given Instrument.
[**PrivateGetOrderHistoryByCurrencyGet**](TradingApi.md#PrivateGetOrderHistoryByCurrencyGet) | **Get** /private/get_order_history_by_currency | Retrieves history of orders that have been partially or fully filled.
[**PrivateGetOrderHistoryByInstrumentGet**](TradingApi.md#PrivateGetOrderHistoryByInstrumentGet) | **Get** /private/get_order_history_by_instrument | Retrieves history of orders that have been partially or fully filled.
[**PrivateGetOrderMarginByIdsGet**](TradingApi.md#PrivateGetOrderMarginByIdsGet) | **Get** /private/get_order_margin_by_ids | Retrieves initial margins of given orders
[**PrivateGetOrderStateGet**](TradingApi.md#PrivateGetOrderStateGet) | **Get** /private/get_order_state | Retrieve the current state of an order.
[**PrivateGetSettlementHistoryByCurrencyGet**](TradingApi.md#PrivateGetSettlementHistoryByCurrencyGet) | **Get** /private/get_settlement_history_by_currency | Retrieves settlement, delivery and bankruptcy events that have affected your account.
[**PrivateGetSettlementHistoryByInstrumentGet**](TradingApi.md#PrivateGetSettlementHistoryByInstrumentGet) | **Get** /private/get_settlement_history_by_instrument | Retrieves public settlement, delivery and bankruptcy events filtered by instrument name
[**PrivateGetUserTradesByCurrencyAndTimeGet**](TradingApi.md#PrivateGetUserTradesByCurrencyAndTimeGet) | **Get** /private/get_user_trades_by_currency_and_time | Retrieve the latest user trades that have occurred for instruments in a specific currency symbol and within given time range.
[**PrivateGetUserTradesByCurrencyGet**](TradingApi.md#PrivateGetUserTradesByCurrencyGet) | **Get** /private/get_user_trades_by_currency | Retrieve the latest user trades that have occurred for instruments in a specific currency symbol.
[**PrivateGetUserTradesByInstrumentAndTimeGet**](TradingApi.md#PrivateGetUserTradesByInstrumentAndTimeGet) | **Get** /private/get_user_trades_by_instrument_and_time | Retrieve the latest user trades that have occurred for a specific instrument and within given time range.
[**PrivateGetUserTradesByInstrumentGet**](TradingApi.md#PrivateGetUserTradesByInstrumentGet) | **Get** /private/get_user_trades_by_instrument | Retrieve the latest user trades that have occurred for a specific instrument.
[**PrivateGetUserTradesByOrderGet**](TradingApi.md#PrivateGetUserTradesByOrderGet) | **Get** /private/get_user_trades_by_order | Retrieve the list of user trades that was created for given order
[**PrivateSellGet**](TradingApi.md#PrivateSellGet) | **Get** /private/sell | Places a sell order for an instrument.



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

