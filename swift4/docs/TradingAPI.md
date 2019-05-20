# TradingAPI

All URIs are relative to *https://www.deribit.com/api/v2*

Method | HTTP request | Description
------------- | ------------- | -------------
[**privateBuyGet**](TradingAPI.md#privatebuyget) | **GET** /private/buy | Places a buy order for an instrument.
[**privateCancelAllByCurrencyGet**](TradingAPI.md#privatecancelallbycurrencyget) | **GET** /private/cancel_all_by_currency | Cancels all orders by currency, optionally filtered by instrument kind and/or order type.
[**privateCancelAllByInstrumentGet**](TradingAPI.md#privatecancelallbyinstrumentget) | **GET** /private/cancel_all_by_instrument | Cancels all orders by instrument, optionally filtered by order type.
[**privateCancelAllGet**](TradingAPI.md#privatecancelallget) | **GET** /private/cancel_all | This method cancels all users orders and stop orders within all currencies and instrument kinds.
[**privateCancelGet**](TradingAPI.md#privatecancelget) | **GET** /private/cancel | Cancel an order, specified by order id
[**privateClosePositionGet**](TradingAPI.md#privateclosepositionget) | **GET** /private/close_position | Makes closing position reduce only order .
[**privateEditGet**](TradingAPI.md#privateeditget) | **GET** /private/edit | Change price, amount and/or other properties of an order.
[**privateGetMarginsGet**](TradingAPI.md#privategetmarginsget) | **GET** /private/get_margins | Get margins for given instrument, amount and price.
[**privateGetOpenOrdersByCurrencyGet**](TradingAPI.md#privategetopenordersbycurrencyget) | **GET** /private/get_open_orders_by_currency | Retrieves list of user&#39;s open orders.
[**privateGetOpenOrdersByInstrumentGet**](TradingAPI.md#privategetopenordersbyinstrumentget) | **GET** /private/get_open_orders_by_instrument | Retrieves list of user&#39;s open orders within given Instrument.
[**privateGetOrderHistoryByCurrencyGet**](TradingAPI.md#privategetorderhistorybycurrencyget) | **GET** /private/get_order_history_by_currency | Retrieves history of orders that have been partially or fully filled.
[**privateGetOrderHistoryByInstrumentGet**](TradingAPI.md#privategetorderhistorybyinstrumentget) | **GET** /private/get_order_history_by_instrument | Retrieves history of orders that have been partially or fully filled.
[**privateGetOrderMarginByIdsGet**](TradingAPI.md#privategetordermarginbyidsget) | **GET** /private/get_order_margin_by_ids | Retrieves initial margins of given orders
[**privateGetOrderStateGet**](TradingAPI.md#privategetorderstateget) | **GET** /private/get_order_state | Retrieve the current state of an order.
[**privateGetSettlementHistoryByCurrencyGet**](TradingAPI.md#privategetsettlementhistorybycurrencyget) | **GET** /private/get_settlement_history_by_currency | Retrieves settlement, delivery and bankruptcy events that have affected your account.
[**privateGetSettlementHistoryByInstrumentGet**](TradingAPI.md#privategetsettlementhistorybyinstrumentget) | **GET** /private/get_settlement_history_by_instrument | Retrieves public settlement, delivery and bankruptcy events filtered by instrument name
[**privateGetUserTradesByCurrencyAndTimeGet**](TradingAPI.md#privategetusertradesbycurrencyandtimeget) | **GET** /private/get_user_trades_by_currency_and_time | Retrieve the latest user trades that have occurred for instruments in a specific currency symbol and within given time range.
[**privateGetUserTradesByCurrencyGet**](TradingAPI.md#privategetusertradesbycurrencyget) | **GET** /private/get_user_trades_by_currency | Retrieve the latest user trades that have occurred for instruments in a specific currency symbol.
[**privateGetUserTradesByInstrumentAndTimeGet**](TradingAPI.md#privategetusertradesbyinstrumentandtimeget) | **GET** /private/get_user_trades_by_instrument_and_time | Retrieve the latest user trades that have occurred for a specific instrument and within given time range.
[**privateGetUserTradesByInstrumentGet**](TradingAPI.md#privategetusertradesbyinstrumentget) | **GET** /private/get_user_trades_by_instrument | Retrieve the latest user trades that have occurred for a specific instrument.
[**privateGetUserTradesByOrderGet**](TradingAPI.md#privategetusertradesbyorderget) | **GET** /private/get_user_trades_by_order | Retrieve the list of user trades that was created for given order
[**privateSellGet**](TradingAPI.md#privatesellget) | **GET** /private/sell | Places a sell order for an instrument.


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
TradingAPI.privateBuyGet(instrumentName: instrumentName, amount: amount, type: type, label: label, price: price, timeInForce: timeInForce, maxShow: maxShow, postOnly: postOnly, reduceOnly: reduceOnly, stopPrice: stopPrice, trigger: trigger, advanced: advanced) { (response, error) in
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
TradingAPI.privateCancelAllByCurrencyGet(currency: currency, kind: kind, type: type) { (response, error) in
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
TradingAPI.privateCancelAllByInstrumentGet(instrumentName: instrumentName, type: type) { (response, error) in
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
TradingAPI.privateCancelAllGet() { (response, error) in
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
TradingAPI.privateCancelGet(orderId: orderId) { (response, error) in
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
TradingAPI.privateClosePositionGet(instrumentName: instrumentName, type: type, price: price) { (response, error) in
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
TradingAPI.privateEditGet(orderId: orderId, amount: amount, price: price, postOnly: postOnly, advanced: advanced, stopPrice: stopPrice) { (response, error) in
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
TradingAPI.privateGetMarginsGet(instrumentName: instrumentName, amount: amount, price: price) { (response, error) in
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
TradingAPI.privateGetOpenOrdersByCurrencyGet(currency: currency, kind: kind, type: type) { (response, error) in
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
TradingAPI.privateGetOpenOrdersByInstrumentGet(instrumentName: instrumentName, type: type) { (response, error) in
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
TradingAPI.privateGetOrderHistoryByCurrencyGet(currency: currency, kind: kind, count: count, offset: offset, includeOld: includeOld, includeUnfilled: includeUnfilled) { (response, error) in
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
TradingAPI.privateGetOrderHistoryByInstrumentGet(instrumentName: instrumentName, count: count, offset: offset, includeOld: includeOld, includeUnfilled: includeUnfilled) { (response, error) in
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
TradingAPI.privateGetOrderMarginByIdsGet(ids: ids) { (response, error) in
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
TradingAPI.privateGetOrderStateGet(orderId: orderId) { (response, error) in
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
TradingAPI.privateGetSettlementHistoryByCurrencyGet(currency: currency, type: type, count: count) { (response, error) in
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
TradingAPI.privateGetSettlementHistoryByInstrumentGet(instrumentName: instrumentName, type: type, count: count) { (response, error) in
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
TradingAPI.privateGetUserTradesByCurrencyAndTimeGet(currency: currency, startTimestamp: startTimestamp, endTimestamp: endTimestamp, kind: kind, count: count, includeOld: includeOld, sorting: sorting) { (response, error) in
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
TradingAPI.privateGetUserTradesByCurrencyGet(currency: currency, kind: kind, startId: startId, endId: endId, count: count, includeOld: includeOld, sorting: sorting) { (response, error) in
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
TradingAPI.privateGetUserTradesByInstrumentAndTimeGet(instrumentName: instrumentName, startTimestamp: startTimestamp, endTimestamp: endTimestamp, count: count, includeOld: includeOld, sorting: sorting) { (response, error) in
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
TradingAPI.privateGetUserTradesByInstrumentGet(instrumentName: instrumentName, startSeq: startSeq, endSeq: endSeq, count: count, includeOld: includeOld, sorting: sorting) { (response, error) in
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
TradingAPI.privateGetUserTradesByOrderGet(orderId: orderId, sorting: sorting) { (response, error) in
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
TradingAPI.privateSellGet(instrumentName: instrumentName, amount: amount, type: type, label: label, price: price, timeInForce: timeInForce, maxShow: maxShow, postOnly: postOnly, reduceOnly: reduceOnly, stopPrice: stopPrice, trigger: trigger, advanced: advanced) { (response, error) in
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

