# TradingApi

All URIs are relative to *https://www.deribit.com/api/v2*

Method | HTTP request | Description
------------- | ------------- | -------------
[**PrivateBuyGet**](TradingApi.md#PrivateBuyGet) | **GET** /private/buy | Places a buy order for an instrument.
[**PrivateCancelAllByCurrencyGet**](TradingApi.md#PrivateCancelAllByCurrencyGet) | **GET** /private/cancel_all_by_currency | Cancels all orders by currency, optionally filtered by instrument kind and/or order type.
[**PrivateCancelAllByInstrumentGet**](TradingApi.md#PrivateCancelAllByInstrumentGet) | **GET** /private/cancel_all_by_instrument | Cancels all orders by instrument, optionally filtered by order type.
[**PrivateCancelAllGet**](TradingApi.md#PrivateCancelAllGet) | **GET** /private/cancel_all | This method cancels all users orders and stop orders within all currencies and instrument kinds.
[**PrivateCancelGet**](TradingApi.md#PrivateCancelGet) | **GET** /private/cancel | Cancel an order, specified by order id
[**PrivateClosePositionGet**](TradingApi.md#PrivateClosePositionGet) | **GET** /private/close_position | Makes closing position reduce only order .
[**PrivateEditGet**](TradingApi.md#PrivateEditGet) | **GET** /private/edit | Change price, amount and/or other properties of an order.
[**PrivateGetMarginsGet**](TradingApi.md#PrivateGetMarginsGet) | **GET** /private/get_margins | Get margins for given instrument, amount and price.
[**PrivateGetOpenOrdersByCurrencyGet**](TradingApi.md#PrivateGetOpenOrdersByCurrencyGet) | **GET** /private/get_open_orders_by_currency | Retrieves list of user&#39;s open orders.
[**PrivateGetOpenOrdersByInstrumentGet**](TradingApi.md#PrivateGetOpenOrdersByInstrumentGet) | **GET** /private/get_open_orders_by_instrument | Retrieves list of user&#39;s open orders within given Instrument.
[**PrivateGetOrderHistoryByCurrencyGet**](TradingApi.md#PrivateGetOrderHistoryByCurrencyGet) | **GET** /private/get_order_history_by_currency | Retrieves history of orders that have been partially or fully filled.
[**PrivateGetOrderHistoryByInstrumentGet**](TradingApi.md#PrivateGetOrderHistoryByInstrumentGet) | **GET** /private/get_order_history_by_instrument | Retrieves history of orders that have been partially or fully filled.
[**PrivateGetOrderMarginByIdsGet**](TradingApi.md#PrivateGetOrderMarginByIdsGet) | **GET** /private/get_order_margin_by_ids | Retrieves initial margins of given orders
[**PrivateGetOrderStateGet**](TradingApi.md#PrivateGetOrderStateGet) | **GET** /private/get_order_state | Retrieve the current state of an order.
[**PrivateGetSettlementHistoryByCurrencyGet**](TradingApi.md#PrivateGetSettlementHistoryByCurrencyGet) | **GET** /private/get_settlement_history_by_currency | Retrieves settlement, delivery and bankruptcy events that have affected your account.
[**PrivateGetSettlementHistoryByInstrumentGet**](TradingApi.md#PrivateGetSettlementHistoryByInstrumentGet) | **GET** /private/get_settlement_history_by_instrument | Retrieves public settlement, delivery and bankruptcy events filtered by instrument name
[**PrivateGetUserTradesByCurrencyAndTimeGet**](TradingApi.md#PrivateGetUserTradesByCurrencyAndTimeGet) | **GET** /private/get_user_trades_by_currency_and_time | Retrieve the latest user trades that have occurred for instruments in a specific currency symbol and within given time range.
[**PrivateGetUserTradesByCurrencyGet**](TradingApi.md#PrivateGetUserTradesByCurrencyGet) | **GET** /private/get_user_trades_by_currency | Retrieve the latest user trades that have occurred for instruments in a specific currency symbol.
[**PrivateGetUserTradesByInstrumentAndTimeGet**](TradingApi.md#PrivateGetUserTradesByInstrumentAndTimeGet) | **GET** /private/get_user_trades_by_instrument_and_time | Retrieve the latest user trades that have occurred for a specific instrument and within given time range.
[**PrivateGetUserTradesByInstrumentGet**](TradingApi.md#PrivateGetUserTradesByInstrumentGet) | **GET** /private/get_user_trades_by_instrument | Retrieve the latest user trades that have occurred for a specific instrument.
[**PrivateGetUserTradesByOrderGet**](TradingApi.md#PrivateGetUserTradesByOrderGet) | **GET** /private/get_user_trades_by_order | Retrieve the list of user trades that was created for given order
[**PrivateSellGet**](TradingApi.md#PrivateSellGet) | **GET** /private/sell | Places a sell order for an instrument.


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
api.instance <- TradingApi$new()
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
api.instance <- TradingApi$new()
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
api.instance <- TradingApi$new()
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
api.instance <- TradingApi$new()
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
api.instance <- TradingApi$new()
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
api.instance <- TradingApi$new()
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
api.instance <- TradingApi$new()
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
api.instance <- TradingApi$new()
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
api.instance <- TradingApi$new()
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
api.instance <- TradingApi$new()
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
api.instance <- TradingApi$new()
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
api.instance <- TradingApi$new()
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
api.instance <- TradingApi$new()
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
api.instance <- TradingApi$new()
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
api.instance <- TradingApi$new()
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
api.instance <- TradingApi$new()
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
api.instance <- TradingApi$new()
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
api.instance <- TradingApi$new()
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
api.instance <- TradingApi$new()
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
api.instance <- TradingApi$new()
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
api.instance <- TradingApi$new()
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
api.instance <- TradingApi$new()
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



