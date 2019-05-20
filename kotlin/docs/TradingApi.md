# TradingApi

All URIs are relative to *https://www.deribit.com/api/v2*

Method | HTTP request | Description
------------- | ------------- | -------------
[**privateBuyGet**](TradingApi.md#privateBuyGet) | **GET** /private/buy | Places a buy order for an instrument.
[**privateCancelAllByCurrencyGet**](TradingApi.md#privateCancelAllByCurrencyGet) | **GET** /private/cancel_all_by_currency | Cancels all orders by currency, optionally filtered by instrument kind and/or order type.
[**privateCancelAllByInstrumentGet**](TradingApi.md#privateCancelAllByInstrumentGet) | **GET** /private/cancel_all_by_instrument | Cancels all orders by instrument, optionally filtered by order type.
[**privateCancelAllGet**](TradingApi.md#privateCancelAllGet) | **GET** /private/cancel_all | This method cancels all users orders and stop orders within all currencies and instrument kinds.
[**privateCancelGet**](TradingApi.md#privateCancelGet) | **GET** /private/cancel | Cancel an order, specified by order id
[**privateClosePositionGet**](TradingApi.md#privateClosePositionGet) | **GET** /private/close_position | Makes closing position reduce only order .
[**privateEditGet**](TradingApi.md#privateEditGet) | **GET** /private/edit | Change price, amount and/or other properties of an order.
[**privateGetMarginsGet**](TradingApi.md#privateGetMarginsGet) | **GET** /private/get_margins | Get margins for given instrument, amount and price.
[**privateGetOpenOrdersByCurrencyGet**](TradingApi.md#privateGetOpenOrdersByCurrencyGet) | **GET** /private/get_open_orders_by_currency | Retrieves list of user&#39;s open orders.
[**privateGetOpenOrdersByInstrumentGet**](TradingApi.md#privateGetOpenOrdersByInstrumentGet) | **GET** /private/get_open_orders_by_instrument | Retrieves list of user&#39;s open orders within given Instrument.
[**privateGetOrderHistoryByCurrencyGet**](TradingApi.md#privateGetOrderHistoryByCurrencyGet) | **GET** /private/get_order_history_by_currency | Retrieves history of orders that have been partially or fully filled.
[**privateGetOrderHistoryByInstrumentGet**](TradingApi.md#privateGetOrderHistoryByInstrumentGet) | **GET** /private/get_order_history_by_instrument | Retrieves history of orders that have been partially or fully filled.
[**privateGetOrderMarginByIdsGet**](TradingApi.md#privateGetOrderMarginByIdsGet) | **GET** /private/get_order_margin_by_ids | Retrieves initial margins of given orders
[**privateGetOrderStateGet**](TradingApi.md#privateGetOrderStateGet) | **GET** /private/get_order_state | Retrieve the current state of an order.
[**privateGetSettlementHistoryByCurrencyGet**](TradingApi.md#privateGetSettlementHistoryByCurrencyGet) | **GET** /private/get_settlement_history_by_currency | Retrieves settlement, delivery and bankruptcy events that have affected your account.
[**privateGetSettlementHistoryByInstrumentGet**](TradingApi.md#privateGetSettlementHistoryByInstrumentGet) | **GET** /private/get_settlement_history_by_instrument | Retrieves public settlement, delivery and bankruptcy events filtered by instrument name
[**privateGetUserTradesByCurrencyAndTimeGet**](TradingApi.md#privateGetUserTradesByCurrencyAndTimeGet) | **GET** /private/get_user_trades_by_currency_and_time | Retrieve the latest user trades that have occurred for instruments in a specific currency symbol and within given time range.
[**privateGetUserTradesByCurrencyGet**](TradingApi.md#privateGetUserTradesByCurrencyGet) | **GET** /private/get_user_trades_by_currency | Retrieve the latest user trades that have occurred for instruments in a specific currency symbol.
[**privateGetUserTradesByInstrumentAndTimeGet**](TradingApi.md#privateGetUserTradesByInstrumentAndTimeGet) | **GET** /private/get_user_trades_by_instrument_and_time | Retrieve the latest user trades that have occurred for a specific instrument and within given time range.
[**privateGetUserTradesByInstrumentGet**](TradingApi.md#privateGetUserTradesByInstrumentGet) | **GET** /private/get_user_trades_by_instrument | Retrieve the latest user trades that have occurred for a specific instrument.
[**privateGetUserTradesByOrderGet**](TradingApi.md#privateGetUserTradesByOrderGet) | **GET** /private/get_user_trades_by_order | Retrieve the list of user trades that was created for given order
[**privateSellGet**](TradingApi.md#privateSellGet) | **GET** /private/sell | Places a sell order for an instrument.


<a name="privateBuyGet"></a>
# **privateBuyGet**
> kotlin.Any privateBuyGet(instrumentName, amount, type, label, price, timeInForce, maxShow, postOnly, reduceOnly, stopPrice, trigger, advanced)

Places a buy order for an instrument.

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = TradingApi()
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
    println("4xx response calling TradingApi#privateBuyGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling TradingApi#privateBuyGet")
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

val apiInstance = TradingApi()
val currency : kotlin.String = currency_example // kotlin.String | The currency symbol
val kind : kotlin.String = kind_example // kotlin.String | Instrument kind, if not provided instruments of all kinds are considered
val type : kotlin.String = type_example // kotlin.String | Order type - limit, stop or all, default - `all`
try {
    val result : kotlin.Any = apiInstance.privateCancelAllByCurrencyGet(currency, kind, type)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling TradingApi#privateCancelAllByCurrencyGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling TradingApi#privateCancelAllByCurrencyGet")
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

val apiInstance = TradingApi()
val instrumentName : kotlin.String = BTC-PERPETUAL // kotlin.String | Instrument name
val type : kotlin.String = type_example // kotlin.String | Order type - limit, stop or all, default - `all`
try {
    val result : kotlin.Any = apiInstance.privateCancelAllByInstrumentGet(instrumentName, type)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling TradingApi#privateCancelAllByInstrumentGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling TradingApi#privateCancelAllByInstrumentGet")
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

val apiInstance = TradingApi()
try {
    val result : kotlin.Any = apiInstance.privateCancelAllGet()
    println(result)
} catch (e: ClientException) {
    println("4xx response calling TradingApi#privateCancelAllGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling TradingApi#privateCancelAllGet")
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

val apiInstance = TradingApi()
val orderId : kotlin.String = ETH-100234 // kotlin.String | The order id
try {
    val result : kotlin.Any = apiInstance.privateCancelGet(orderId)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling TradingApi#privateCancelGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling TradingApi#privateCancelGet")
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

<a name="privateClosePositionGet"></a>
# **privateClosePositionGet**
> kotlin.Any privateClosePositionGet(instrumentName, type, price)

Makes closing position reduce only order .

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = TradingApi()
val instrumentName : kotlin.String = BTC-PERPETUAL // kotlin.String | Instrument name
val type : kotlin.String = type_example // kotlin.String | The order type
val price : java.math.BigDecimal = 8.14 // java.math.BigDecimal | Optional price for limit order.
try {
    val result : kotlin.Any = apiInstance.privateClosePositionGet(instrumentName, type, price)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling TradingApi#privateClosePositionGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling TradingApi#privateClosePositionGet")
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

<a name="privateEditGet"></a>
# **privateEditGet**
> kotlin.Any privateEditGet(orderId, amount, price, postOnly, advanced, stopPrice)

Change price, amount and/or other properties of an order.

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = TradingApi()
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
    println("4xx response calling TradingApi#privateEditGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling TradingApi#privateEditGet")
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

<a name="privateGetMarginsGet"></a>
# **privateGetMarginsGet**
> kotlin.Any privateGetMarginsGet(instrumentName, amount, price)

Get margins for given instrument, amount and price.

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = TradingApi()
val instrumentName : kotlin.String = BTC-PERPETUAL // kotlin.String | Instrument name
val amount : java.math.BigDecimal = 1 // java.math.BigDecimal | Amount, integer for future, float for option. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH.
val price : java.math.BigDecimal = 8.14 // java.math.BigDecimal | Price
try {
    val result : kotlin.Any = apiInstance.privateGetMarginsGet(instrumentName, amount, price)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling TradingApi#privateGetMarginsGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling TradingApi#privateGetMarginsGet")
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

<a name="privateGetOpenOrdersByCurrencyGet"></a>
# **privateGetOpenOrdersByCurrencyGet**
> kotlin.Any privateGetOpenOrdersByCurrencyGet(currency, kind, type)

Retrieves list of user&#39;s open orders.

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = TradingApi()
val currency : kotlin.String = currency_example // kotlin.String | The currency symbol
val kind : kotlin.String = kind_example // kotlin.String | Instrument kind, if not provided instruments of all kinds are considered
val type : kotlin.String = type_example // kotlin.String | Order type, default - `all`
try {
    val result : kotlin.Any = apiInstance.privateGetOpenOrdersByCurrencyGet(currency, kind, type)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling TradingApi#privateGetOpenOrdersByCurrencyGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling TradingApi#privateGetOpenOrdersByCurrencyGet")
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

val apiInstance = TradingApi()
val instrumentName : kotlin.String = BTC-PERPETUAL // kotlin.String | Instrument name
val type : kotlin.String = type_example // kotlin.String | Order type, default - `all`
try {
    val result : kotlin.Any = apiInstance.privateGetOpenOrdersByInstrumentGet(instrumentName, type)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling TradingApi#privateGetOpenOrdersByInstrumentGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling TradingApi#privateGetOpenOrdersByInstrumentGet")
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

val apiInstance = TradingApi()
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
    println("4xx response calling TradingApi#privateGetOrderHistoryByCurrencyGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling TradingApi#privateGetOrderHistoryByCurrencyGet")
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

val apiInstance = TradingApi()
val instrumentName : kotlin.String = BTC-PERPETUAL // kotlin.String | Instrument name
val count : kotlin.Int = 56 // kotlin.Int | Number of requested items, default - `20`
val offset : kotlin.Int = 10 // kotlin.Int | The offset for pagination, default - `0`
val includeOld : kotlin.Boolean = false // kotlin.Boolean | Include in result orders older than 2 days, default - `false`
val includeUnfilled : kotlin.Boolean = false // kotlin.Boolean | Include in result fully unfilled closed orders, default - `false`
try {
    val result : kotlin.Any = apiInstance.privateGetOrderHistoryByInstrumentGet(instrumentName, count, offset, includeOld, includeUnfilled)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling TradingApi#privateGetOrderHistoryByInstrumentGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling TradingApi#privateGetOrderHistoryByInstrumentGet")
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

val apiInstance = TradingApi()
val ids : kotlin.Array<kotlin.String> =  // kotlin.Array<kotlin.String> | Ids of orders
try {
    val result : kotlin.Any = apiInstance.privateGetOrderMarginByIdsGet(ids)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling TradingApi#privateGetOrderMarginByIdsGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling TradingApi#privateGetOrderMarginByIdsGet")
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

val apiInstance = TradingApi()
val orderId : kotlin.String = ETH-100234 // kotlin.String | The order id
try {
    val result : kotlin.Any = apiInstance.privateGetOrderStateGet(orderId)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling TradingApi#privateGetOrderStateGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling TradingApi#privateGetOrderStateGet")
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

<a name="privateGetSettlementHistoryByCurrencyGet"></a>
# **privateGetSettlementHistoryByCurrencyGet**
> kotlin.Any privateGetSettlementHistoryByCurrencyGet(currency, type, count)

Retrieves settlement, delivery and bankruptcy events that have affected your account.

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = TradingApi()
val currency : kotlin.String = currency_example // kotlin.String | The currency symbol
val type : kotlin.String = type_example // kotlin.String | Settlement type
val count : kotlin.Int = 56 // kotlin.Int | Number of requested items, default - `20`
try {
    val result : kotlin.Any = apiInstance.privateGetSettlementHistoryByCurrencyGet(currency, type, count)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling TradingApi#privateGetSettlementHistoryByCurrencyGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling TradingApi#privateGetSettlementHistoryByCurrencyGet")
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

val apiInstance = TradingApi()
val instrumentName : kotlin.String = BTC-PERPETUAL // kotlin.String | Instrument name
val type : kotlin.String = type_example // kotlin.String | Settlement type
val count : kotlin.Int = 56 // kotlin.Int | Number of requested items, default - `20`
try {
    val result : kotlin.Any = apiInstance.privateGetSettlementHistoryByInstrumentGet(instrumentName, type, count)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling TradingApi#privateGetSettlementHistoryByInstrumentGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling TradingApi#privateGetSettlementHistoryByInstrumentGet")
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

<a name="privateGetUserTradesByCurrencyAndTimeGet"></a>
# **privateGetUserTradesByCurrencyAndTimeGet**
> kotlin.Any privateGetUserTradesByCurrencyAndTimeGet(currency, startTimestamp, endTimestamp, kind, count, includeOld, sorting)

Retrieve the latest user trades that have occurred for instruments in a specific currency symbol and within given time range.

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = TradingApi()
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
    println("4xx response calling TradingApi#privateGetUserTradesByCurrencyAndTimeGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling TradingApi#privateGetUserTradesByCurrencyAndTimeGet")
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

val apiInstance = TradingApi()
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
    println("4xx response calling TradingApi#privateGetUserTradesByCurrencyGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling TradingApi#privateGetUserTradesByCurrencyGet")
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

val apiInstance = TradingApi()
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
    println("4xx response calling TradingApi#privateGetUserTradesByInstrumentAndTimeGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling TradingApi#privateGetUserTradesByInstrumentAndTimeGet")
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

val apiInstance = TradingApi()
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
    println("4xx response calling TradingApi#privateGetUserTradesByInstrumentGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling TradingApi#privateGetUserTradesByInstrumentGet")
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

val apiInstance = TradingApi()
val orderId : kotlin.String = ETH-100234 // kotlin.String | The order id
val sorting : kotlin.String = sorting_example // kotlin.String | Direction of results sorting (`default` value means no sorting, results will be returned in order in which they left the database)
try {
    val result : kotlin.Any = apiInstance.privateGetUserTradesByOrderGet(orderId, sorting)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling TradingApi#privateGetUserTradesByOrderGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling TradingApi#privateGetUserTradesByOrderGet")
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

<a name="privateSellGet"></a>
# **privateSellGet**
> kotlin.Any privateSellGet(instrumentName, amount, type, label, price, timeInForce, maxShow, postOnly, reduceOnly, stopPrice, trigger, advanced)

Places a sell order for an instrument.

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = TradingApi()
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
    println("4xx response calling TradingApi#privateSellGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling TradingApi#privateSellGet")
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

