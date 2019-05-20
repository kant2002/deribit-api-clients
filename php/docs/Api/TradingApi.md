# OpenAPI\Client\TradingApi

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



## privateBuyGet

> object privateBuyGet($instrument_name, $amount, $type, $label, $price, $time_in_force, $max_show, $post_only, $reduce_only, $stop_price, $trigger, $advanced)

Places a buy order for an instrument.

### Example

```php
<?php
require_once(__DIR__ . '/vendor/autoload.php');


// Configure Bearer (Auth. Token) authorization: bearerAuth
$config = OpenAPI\Client\Configuration::getDefaultConfiguration()->setAccessToken('YOUR_ACCESS_TOKEN');


$apiInstance = new OpenAPI\Client\Api\TradingApi(
    // If you want use custom http client, pass your client which implements `GuzzleHttp\ClientInterface`.
    // This is optional, `GuzzleHttp\Client` will be used as default.
    new GuzzleHttp\Client(),
    $config
);
$instrument_name = BTC-PERPETUAL; // string | Instrument name
$amount = 3.4; // float | It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH
$type = 'type_example'; // string | The order type, default: `\"limit\"`
$label = 'label_example'; // string | user defined label for the order (maximum 32 characters)
$price = 3.4; // float | <p>The order price in base currency (Only for limit and stop_limit orders)</p> <p>When adding order with advanced=usd, the field price should be the option price value in USD.</p> <p>When adding order with advanced=implv, the field price should be a value of implied volatility in percentages. For example,  price=100, means implied volatility of 100%</p>
$time_in_force = 'good_til_cancelled'; // string | <p>Specifies how long the order remains in effect. Default `\"good_til_cancelled\"`</p> <ul> <li>`\"good_til_cancelled\"` - unfilled order remains in order book until cancelled</li> <li>`\"fill_or_kill\"` - execute a transaction immediately and completely or not at all</li> <li>`\"immediate_or_cancel\"` - execute a transaction immediately, and any portion of the order that cannot be immediately filled is cancelled</li> </ul>
$max_show = 1; // float | Maximum amount within an order to be shown to other customers, `0` for invisible order
$post_only = true; // bool | <p>If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.</p> <p>Only valid in combination with time_in_force=`\"good_til_cancelled\"`</p>
$reduce_only = false; // bool | If `true`, the order is considered reduce-only which is intended to only reduce a current position
$stop_price = 3.4; // float | Stop price, required for stop limit orders (Only for stop orders)
$trigger = 'trigger_example'; // string | Defines trigger type, required for `\"stop_limit\"` order type
$advanced = 'advanced_example'; // string | Advanced option order type. (Only for options)

try {
    $result = $apiInstance->privateBuyGet($instrument_name, $amount, $type, $label, $price, $time_in_force, $max_show, $post_only, $reduce_only, $stop_price, $trigger, $advanced);
    print_r($result);
} catch (Exception $e) {
    echo 'Exception when calling TradingApi->privateBuyGet: ', $e->getMessage(), PHP_EOL;
}
?>
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrument_name** | **string**| Instrument name |
 **amount** | **float**| It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH |
 **type** | **string**| The order type, default: &#x60;\&quot;limit\&quot;&#x60; | [optional]
 **label** | **string**| user defined label for the order (maximum 32 characters) | [optional]
 **price** | **float**| &lt;p&gt;The order price in base currency (Only for limit and stop_limit orders)&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;usd, the field price should be the option price value in USD.&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;implv, the field price should be a value of implied volatility in percentages. For example,  price&#x3D;100, means implied volatility of 100%&lt;/p&gt; | [optional]
 **time_in_force** | **string**| &lt;p&gt;Specifies how long the order remains in effect. Default &#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; &lt;ul&gt; &lt;li&gt;&#x60;\&quot;good_til_cancelled\&quot;&#x60; - unfilled order remains in order book until cancelled&lt;/li&gt; &lt;li&gt;&#x60;\&quot;fill_or_kill\&quot;&#x60; - execute a transaction immediately and completely or not at all&lt;/li&gt; &lt;li&gt;&#x60;\&quot;immediate_or_cancel\&quot;&#x60; - execute a transaction immediately, and any portion of the order that cannot be immediately filled is cancelled&lt;/li&gt; &lt;/ul&gt; | [optional] [default to &#39;good_til_cancelled&#39;]
 **max_show** | **float**| Maximum amount within an order to be shown to other customers, &#x60;0&#x60; for invisible order | [optional] [default to 1]
 **post_only** | **bool**| &lt;p&gt;If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.&lt;/p&gt; &lt;p&gt;Only valid in combination with time_in_force&#x3D;&#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; | [optional] [default to true]
 **reduce_only** | **bool**| If &#x60;true&#x60;, the order is considered reduce-only which is intended to only reduce a current position | [optional] [default to false]
 **stop_price** | **float**| Stop price, required for stop limit orders (Only for stop orders) | [optional]
 **trigger** | **string**| Defines trigger type, required for &#x60;\&quot;stop_limit\&quot;&#x60; order type | [optional]
 **advanced** | **string**| Advanced option order type. (Only for options) | [optional]

### Return type

**object**

### Authorization

[bearerAuth](../../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../../README.md#documentation-for-models)
[[Back to README]](../../README.md)


## privateCancelAllByCurrencyGet

> object privateCancelAllByCurrencyGet($currency, $kind, $type)

Cancels all orders by currency, optionally filtered by instrument kind and/or order type.

### Example

```php
<?php
require_once(__DIR__ . '/vendor/autoload.php');


// Configure Bearer (Auth. Token) authorization: bearerAuth
$config = OpenAPI\Client\Configuration::getDefaultConfiguration()->setAccessToken('YOUR_ACCESS_TOKEN');


$apiInstance = new OpenAPI\Client\Api\TradingApi(
    // If you want use custom http client, pass your client which implements `GuzzleHttp\ClientInterface`.
    // This is optional, `GuzzleHttp\Client` will be used as default.
    new GuzzleHttp\Client(),
    $config
);
$currency = 'currency_example'; // string | The currency symbol
$kind = 'kind_example'; // string | Instrument kind, if not provided instruments of all kinds are considered
$type = 'type_example'; // string | Order type - limit, stop or all, default - `all`

try {
    $result = $apiInstance->privateCancelAllByCurrencyGet($currency, $kind, $type);
    print_r($result);
} catch (Exception $e) {
    echo 'Exception when calling TradingApi->privateCancelAllByCurrencyGet: ', $e->getMessage(), PHP_EOL;
}
?>
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **string**| The currency symbol |
 **kind** | **string**| Instrument kind, if not provided instruments of all kinds are considered | [optional]
 **type** | **string**| Order type - limit, stop or all, default - &#x60;all&#x60; | [optional]

### Return type

**object**

### Authorization

[bearerAuth](../../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../../README.md#documentation-for-models)
[[Back to README]](../../README.md)


## privateCancelAllByInstrumentGet

> object privateCancelAllByInstrumentGet($instrument_name, $type)

Cancels all orders by instrument, optionally filtered by order type.

### Example

```php
<?php
require_once(__DIR__ . '/vendor/autoload.php');


// Configure Bearer (Auth. Token) authorization: bearerAuth
$config = OpenAPI\Client\Configuration::getDefaultConfiguration()->setAccessToken('YOUR_ACCESS_TOKEN');


$apiInstance = new OpenAPI\Client\Api\TradingApi(
    // If you want use custom http client, pass your client which implements `GuzzleHttp\ClientInterface`.
    // This is optional, `GuzzleHttp\Client` will be used as default.
    new GuzzleHttp\Client(),
    $config
);
$instrument_name = BTC-PERPETUAL; // string | Instrument name
$type = 'type_example'; // string | Order type - limit, stop or all, default - `all`

try {
    $result = $apiInstance->privateCancelAllByInstrumentGet($instrument_name, $type);
    print_r($result);
} catch (Exception $e) {
    echo 'Exception when calling TradingApi->privateCancelAllByInstrumentGet: ', $e->getMessage(), PHP_EOL;
}
?>
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrument_name** | **string**| Instrument name |
 **type** | **string**| Order type - limit, stop or all, default - &#x60;all&#x60; | [optional]

### Return type

**object**

### Authorization

[bearerAuth](../../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../../README.md#documentation-for-models)
[[Back to README]](../../README.md)


## privateCancelAllGet

> object privateCancelAllGet()

This method cancels all users orders and stop orders within all currencies and instrument kinds.

### Example

```php
<?php
require_once(__DIR__ . '/vendor/autoload.php');


// Configure Bearer (Auth. Token) authorization: bearerAuth
$config = OpenAPI\Client\Configuration::getDefaultConfiguration()->setAccessToken('YOUR_ACCESS_TOKEN');


$apiInstance = new OpenAPI\Client\Api\TradingApi(
    // If you want use custom http client, pass your client which implements `GuzzleHttp\ClientInterface`.
    // This is optional, `GuzzleHttp\Client` will be used as default.
    new GuzzleHttp\Client(),
    $config
);

try {
    $result = $apiInstance->privateCancelAllGet();
    print_r($result);
} catch (Exception $e) {
    echo 'Exception when calling TradingApi->privateCancelAllGet: ', $e->getMessage(), PHP_EOL;
}
?>
```

### Parameters

This endpoint does not need any parameter.

### Return type

**object**

### Authorization

[bearerAuth](../../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../../README.md#documentation-for-models)
[[Back to README]](../../README.md)


## privateCancelGet

> object privateCancelGet($order_id)

Cancel an order, specified by order id

### Example

```php
<?php
require_once(__DIR__ . '/vendor/autoload.php');


// Configure Bearer (Auth. Token) authorization: bearerAuth
$config = OpenAPI\Client\Configuration::getDefaultConfiguration()->setAccessToken('YOUR_ACCESS_TOKEN');


$apiInstance = new OpenAPI\Client\Api\TradingApi(
    // If you want use custom http client, pass your client which implements `GuzzleHttp\ClientInterface`.
    // This is optional, `GuzzleHttp\Client` will be used as default.
    new GuzzleHttp\Client(),
    $config
);
$order_id = ETH-100234; // string | The order id

try {
    $result = $apiInstance->privateCancelGet($order_id);
    print_r($result);
} catch (Exception $e) {
    echo 'Exception when calling TradingApi->privateCancelGet: ', $e->getMessage(), PHP_EOL;
}
?>
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **order_id** | **string**| The order id |

### Return type

**object**

### Authorization

[bearerAuth](../../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../../README.md#documentation-for-models)
[[Back to README]](../../README.md)


## privateClosePositionGet

> object privateClosePositionGet($instrument_name, $type, $price)

Makes closing position reduce only order .

### Example

```php
<?php
require_once(__DIR__ . '/vendor/autoload.php');


// Configure Bearer (Auth. Token) authorization: bearerAuth
$config = OpenAPI\Client\Configuration::getDefaultConfiguration()->setAccessToken('YOUR_ACCESS_TOKEN');


$apiInstance = new OpenAPI\Client\Api\TradingApi(
    // If you want use custom http client, pass your client which implements `GuzzleHttp\ClientInterface`.
    // This is optional, `GuzzleHttp\Client` will be used as default.
    new GuzzleHttp\Client(),
    $config
);
$instrument_name = BTC-PERPETUAL; // string | Instrument name
$type = 'type_example'; // string | The order type
$price = 3.4; // float | Optional price for limit order.

try {
    $result = $apiInstance->privateClosePositionGet($instrument_name, $type, $price);
    print_r($result);
} catch (Exception $e) {
    echo 'Exception when calling TradingApi->privateClosePositionGet: ', $e->getMessage(), PHP_EOL;
}
?>
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrument_name** | **string**| Instrument name |
 **type** | **string**| The order type |
 **price** | **float**| Optional price for limit order. | [optional]

### Return type

**object**

### Authorization

[bearerAuth](../../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../../README.md#documentation-for-models)
[[Back to README]](../../README.md)


## privateEditGet

> object privateEditGet($order_id, $amount, $price, $post_only, $advanced, $stop_price)

Change price, amount and/or other properties of an order.

### Example

```php
<?php
require_once(__DIR__ . '/vendor/autoload.php');


// Configure Bearer (Auth. Token) authorization: bearerAuth
$config = OpenAPI\Client\Configuration::getDefaultConfiguration()->setAccessToken('YOUR_ACCESS_TOKEN');


$apiInstance = new OpenAPI\Client\Api\TradingApi(
    // If you want use custom http client, pass your client which implements `GuzzleHttp\ClientInterface`.
    // This is optional, `GuzzleHttp\Client` will be used as default.
    new GuzzleHttp\Client(),
    $config
);
$order_id = ETH-100234; // string | The order id
$amount = 3.4; // float | It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH
$price = 3.4; // float | <p>The order price in base currency.</p> <p>When editing an option order with advanced=usd, the field price should be the option price value in USD.</p> <p>When editing an option order with advanced=implv, the field price should be a value of implied volatility in percentages. For example,  price=100, means implied volatility of 100%</p>
$post_only = true; // bool | <p>If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.</p> <p>Only valid in combination with time_in_force=`\"good_til_cancelled\"`</p>
$advanced = 'advanced_example'; // string | Advanced option order type. If you have posted an advanced option order, it is necessary to re-supply this parameter when editing it (Only for options)
$stop_price = 3.4; // float | Stop price, required for stop limit orders (Only for stop orders)

try {
    $result = $apiInstance->privateEditGet($order_id, $amount, $price, $post_only, $advanced, $stop_price);
    print_r($result);
} catch (Exception $e) {
    echo 'Exception when calling TradingApi->privateEditGet: ', $e->getMessage(), PHP_EOL;
}
?>
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **order_id** | **string**| The order id |
 **amount** | **float**| It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH |
 **price** | **float**| &lt;p&gt;The order price in base currency.&lt;/p&gt; &lt;p&gt;When editing an option order with advanced&#x3D;usd, the field price should be the option price value in USD.&lt;/p&gt; &lt;p&gt;When editing an option order with advanced&#x3D;implv, the field price should be a value of implied volatility in percentages. For example,  price&#x3D;100, means implied volatility of 100%&lt;/p&gt; |
 **post_only** | **bool**| &lt;p&gt;If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.&lt;/p&gt; &lt;p&gt;Only valid in combination with time_in_force&#x3D;&#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; | [optional] [default to true]
 **advanced** | **string**| Advanced option order type. If you have posted an advanced option order, it is necessary to re-supply this parameter when editing it (Only for options) | [optional]
 **stop_price** | **float**| Stop price, required for stop limit orders (Only for stop orders) | [optional]

### Return type

**object**

### Authorization

[bearerAuth](../../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../../README.md#documentation-for-models)
[[Back to README]](../../README.md)


## privateGetMarginsGet

> object privateGetMarginsGet($instrument_name, $amount, $price)

Get margins for given instrument, amount and price.

### Example

```php
<?php
require_once(__DIR__ . '/vendor/autoload.php');


// Configure Bearer (Auth. Token) authorization: bearerAuth
$config = OpenAPI\Client\Configuration::getDefaultConfiguration()->setAccessToken('YOUR_ACCESS_TOKEN');


$apiInstance = new OpenAPI\Client\Api\TradingApi(
    // If you want use custom http client, pass your client which implements `GuzzleHttp\ClientInterface`.
    // This is optional, `GuzzleHttp\Client` will be used as default.
    new GuzzleHttp\Client(),
    $config
);
$instrument_name = BTC-PERPETUAL; // string | Instrument name
$amount = 1; // float | Amount, integer for future, float for option. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH.
$price = 3.4; // float | Price

try {
    $result = $apiInstance->privateGetMarginsGet($instrument_name, $amount, $price);
    print_r($result);
} catch (Exception $e) {
    echo 'Exception when calling TradingApi->privateGetMarginsGet: ', $e->getMessage(), PHP_EOL;
}
?>
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrument_name** | **string**| Instrument name |
 **amount** | **float**| Amount, integer for future, float for option. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH. |
 **price** | **float**| Price |

### Return type

**object**

### Authorization

[bearerAuth](../../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../../README.md#documentation-for-models)
[[Back to README]](../../README.md)


## privateGetOpenOrdersByCurrencyGet

> object privateGetOpenOrdersByCurrencyGet($currency, $kind, $type)

Retrieves list of user's open orders.

### Example

```php
<?php
require_once(__DIR__ . '/vendor/autoload.php');


// Configure Bearer (Auth. Token) authorization: bearerAuth
$config = OpenAPI\Client\Configuration::getDefaultConfiguration()->setAccessToken('YOUR_ACCESS_TOKEN');


$apiInstance = new OpenAPI\Client\Api\TradingApi(
    // If you want use custom http client, pass your client which implements `GuzzleHttp\ClientInterface`.
    // This is optional, `GuzzleHttp\Client` will be used as default.
    new GuzzleHttp\Client(),
    $config
);
$currency = 'currency_example'; // string | The currency symbol
$kind = 'kind_example'; // string | Instrument kind, if not provided instruments of all kinds are considered
$type = 'type_example'; // string | Order type, default - `all`

try {
    $result = $apiInstance->privateGetOpenOrdersByCurrencyGet($currency, $kind, $type);
    print_r($result);
} catch (Exception $e) {
    echo 'Exception when calling TradingApi->privateGetOpenOrdersByCurrencyGet: ', $e->getMessage(), PHP_EOL;
}
?>
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **string**| The currency symbol |
 **kind** | **string**| Instrument kind, if not provided instruments of all kinds are considered | [optional]
 **type** | **string**| Order type, default - &#x60;all&#x60; | [optional]

### Return type

**object**

### Authorization

[bearerAuth](../../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../../README.md#documentation-for-models)
[[Back to README]](../../README.md)


## privateGetOpenOrdersByInstrumentGet

> object privateGetOpenOrdersByInstrumentGet($instrument_name, $type)

Retrieves list of user's open orders within given Instrument.

### Example

```php
<?php
require_once(__DIR__ . '/vendor/autoload.php');


// Configure Bearer (Auth. Token) authorization: bearerAuth
$config = OpenAPI\Client\Configuration::getDefaultConfiguration()->setAccessToken('YOUR_ACCESS_TOKEN');


$apiInstance = new OpenAPI\Client\Api\TradingApi(
    // If you want use custom http client, pass your client which implements `GuzzleHttp\ClientInterface`.
    // This is optional, `GuzzleHttp\Client` will be used as default.
    new GuzzleHttp\Client(),
    $config
);
$instrument_name = BTC-PERPETUAL; // string | Instrument name
$type = 'type_example'; // string | Order type, default - `all`

try {
    $result = $apiInstance->privateGetOpenOrdersByInstrumentGet($instrument_name, $type);
    print_r($result);
} catch (Exception $e) {
    echo 'Exception when calling TradingApi->privateGetOpenOrdersByInstrumentGet: ', $e->getMessage(), PHP_EOL;
}
?>
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrument_name** | **string**| Instrument name |
 **type** | **string**| Order type, default - &#x60;all&#x60; | [optional]

### Return type

**object**

### Authorization

[bearerAuth](../../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../../README.md#documentation-for-models)
[[Back to README]](../../README.md)


## privateGetOrderHistoryByCurrencyGet

> object privateGetOrderHistoryByCurrencyGet($currency, $kind, $count, $offset, $include_old, $include_unfilled)

Retrieves history of orders that have been partially or fully filled.

### Example

```php
<?php
require_once(__DIR__ . '/vendor/autoload.php');


// Configure Bearer (Auth. Token) authorization: bearerAuth
$config = OpenAPI\Client\Configuration::getDefaultConfiguration()->setAccessToken('YOUR_ACCESS_TOKEN');


$apiInstance = new OpenAPI\Client\Api\TradingApi(
    // If you want use custom http client, pass your client which implements `GuzzleHttp\ClientInterface`.
    // This is optional, `GuzzleHttp\Client` will be used as default.
    new GuzzleHttp\Client(),
    $config
);
$currency = 'currency_example'; // string | The currency symbol
$kind = 'kind_example'; // string | Instrument kind, if not provided instruments of all kinds are considered
$count = 56; // int | Number of requested items, default - `20`
$offset = 10; // int | The offset for pagination, default - `0`
$include_old = false; // bool | Include in result orders older than 2 days, default - `false`
$include_unfilled = false; // bool | Include in result fully unfilled closed orders, default - `false`

try {
    $result = $apiInstance->privateGetOrderHistoryByCurrencyGet($currency, $kind, $count, $offset, $include_old, $include_unfilled);
    print_r($result);
} catch (Exception $e) {
    echo 'Exception when calling TradingApi->privateGetOrderHistoryByCurrencyGet: ', $e->getMessage(), PHP_EOL;
}
?>
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **string**| The currency symbol |
 **kind** | **string**| Instrument kind, if not provided instruments of all kinds are considered | [optional]
 **count** | **int**| Number of requested items, default - &#x60;20&#x60; | [optional]
 **offset** | **int**| The offset for pagination, default - &#x60;0&#x60; | [optional]
 **include_old** | **bool**| Include in result orders older than 2 days, default - &#x60;false&#x60; | [optional]
 **include_unfilled** | **bool**| Include in result fully unfilled closed orders, default - &#x60;false&#x60; | [optional]

### Return type

**object**

### Authorization

[bearerAuth](../../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../../README.md#documentation-for-models)
[[Back to README]](../../README.md)


## privateGetOrderHistoryByInstrumentGet

> object privateGetOrderHistoryByInstrumentGet($instrument_name, $count, $offset, $include_old, $include_unfilled)

Retrieves history of orders that have been partially or fully filled.

### Example

```php
<?php
require_once(__DIR__ . '/vendor/autoload.php');


// Configure Bearer (Auth. Token) authorization: bearerAuth
$config = OpenAPI\Client\Configuration::getDefaultConfiguration()->setAccessToken('YOUR_ACCESS_TOKEN');


$apiInstance = new OpenAPI\Client\Api\TradingApi(
    // If you want use custom http client, pass your client which implements `GuzzleHttp\ClientInterface`.
    // This is optional, `GuzzleHttp\Client` will be used as default.
    new GuzzleHttp\Client(),
    $config
);
$instrument_name = BTC-PERPETUAL; // string | Instrument name
$count = 56; // int | Number of requested items, default - `20`
$offset = 10; // int | The offset for pagination, default - `0`
$include_old = false; // bool | Include in result orders older than 2 days, default - `false`
$include_unfilled = false; // bool | Include in result fully unfilled closed orders, default - `false`

try {
    $result = $apiInstance->privateGetOrderHistoryByInstrumentGet($instrument_name, $count, $offset, $include_old, $include_unfilled);
    print_r($result);
} catch (Exception $e) {
    echo 'Exception when calling TradingApi->privateGetOrderHistoryByInstrumentGet: ', $e->getMessage(), PHP_EOL;
}
?>
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrument_name** | **string**| Instrument name |
 **count** | **int**| Number of requested items, default - &#x60;20&#x60; | [optional]
 **offset** | **int**| The offset for pagination, default - &#x60;0&#x60; | [optional]
 **include_old** | **bool**| Include in result orders older than 2 days, default - &#x60;false&#x60; | [optional]
 **include_unfilled** | **bool**| Include in result fully unfilled closed orders, default - &#x60;false&#x60; | [optional]

### Return type

**object**

### Authorization

[bearerAuth](../../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../../README.md#documentation-for-models)
[[Back to README]](../../README.md)


## privateGetOrderMarginByIdsGet

> object privateGetOrderMarginByIdsGet($ids)

Retrieves initial margins of given orders

### Example

```php
<?php
require_once(__DIR__ . '/vendor/autoload.php');


// Configure Bearer (Auth. Token) authorization: bearerAuth
$config = OpenAPI\Client\Configuration::getDefaultConfiguration()->setAccessToken('YOUR_ACCESS_TOKEN');


$apiInstance = new OpenAPI\Client\Api\TradingApi(
    // If you want use custom http client, pass your client which implements `GuzzleHttp\ClientInterface`.
    // This is optional, `GuzzleHttp\Client` will be used as default.
    new GuzzleHttp\Client(),
    $config
);
$ids = array('ids_example'); // string[] | Ids of orders

try {
    $result = $apiInstance->privateGetOrderMarginByIdsGet($ids);
    print_r($result);
} catch (Exception $e) {
    echo 'Exception when calling TradingApi->privateGetOrderMarginByIdsGet: ', $e->getMessage(), PHP_EOL;
}
?>
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **ids** | [**string[]**](../Model/string.md)| Ids of orders |

### Return type

**object**

### Authorization

[bearerAuth](../../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../../README.md#documentation-for-models)
[[Back to README]](../../README.md)


## privateGetOrderStateGet

> object privateGetOrderStateGet($order_id)

Retrieve the current state of an order.

### Example

```php
<?php
require_once(__DIR__ . '/vendor/autoload.php');


// Configure Bearer (Auth. Token) authorization: bearerAuth
$config = OpenAPI\Client\Configuration::getDefaultConfiguration()->setAccessToken('YOUR_ACCESS_TOKEN');


$apiInstance = new OpenAPI\Client\Api\TradingApi(
    // If you want use custom http client, pass your client which implements `GuzzleHttp\ClientInterface`.
    // This is optional, `GuzzleHttp\Client` will be used as default.
    new GuzzleHttp\Client(),
    $config
);
$order_id = ETH-100234; // string | The order id

try {
    $result = $apiInstance->privateGetOrderStateGet($order_id);
    print_r($result);
} catch (Exception $e) {
    echo 'Exception when calling TradingApi->privateGetOrderStateGet: ', $e->getMessage(), PHP_EOL;
}
?>
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **order_id** | **string**| The order id |

### Return type

**object**

### Authorization

[bearerAuth](../../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../../README.md#documentation-for-models)
[[Back to README]](../../README.md)


## privateGetSettlementHistoryByCurrencyGet

> object privateGetSettlementHistoryByCurrencyGet($currency, $type, $count)

Retrieves settlement, delivery and bankruptcy events that have affected your account.

### Example

```php
<?php
require_once(__DIR__ . '/vendor/autoload.php');


// Configure Bearer (Auth. Token) authorization: bearerAuth
$config = OpenAPI\Client\Configuration::getDefaultConfiguration()->setAccessToken('YOUR_ACCESS_TOKEN');


$apiInstance = new OpenAPI\Client\Api\TradingApi(
    // If you want use custom http client, pass your client which implements `GuzzleHttp\ClientInterface`.
    // This is optional, `GuzzleHttp\Client` will be used as default.
    new GuzzleHttp\Client(),
    $config
);
$currency = 'currency_example'; // string | The currency symbol
$type = 'type_example'; // string | Settlement type
$count = 56; // int | Number of requested items, default - `20`

try {
    $result = $apiInstance->privateGetSettlementHistoryByCurrencyGet($currency, $type, $count);
    print_r($result);
} catch (Exception $e) {
    echo 'Exception when calling TradingApi->privateGetSettlementHistoryByCurrencyGet: ', $e->getMessage(), PHP_EOL;
}
?>
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **string**| The currency symbol |
 **type** | **string**| Settlement type | [optional]
 **count** | **int**| Number of requested items, default - &#x60;20&#x60; | [optional]

### Return type

**object**

### Authorization

[bearerAuth](../../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../../README.md#documentation-for-models)
[[Back to README]](../../README.md)


## privateGetSettlementHistoryByInstrumentGet

> object privateGetSettlementHistoryByInstrumentGet($instrument_name, $type, $count)

Retrieves public settlement, delivery and bankruptcy events filtered by instrument name

### Example

```php
<?php
require_once(__DIR__ . '/vendor/autoload.php');


// Configure Bearer (Auth. Token) authorization: bearerAuth
$config = OpenAPI\Client\Configuration::getDefaultConfiguration()->setAccessToken('YOUR_ACCESS_TOKEN');


$apiInstance = new OpenAPI\Client\Api\TradingApi(
    // If you want use custom http client, pass your client which implements `GuzzleHttp\ClientInterface`.
    // This is optional, `GuzzleHttp\Client` will be used as default.
    new GuzzleHttp\Client(),
    $config
);
$instrument_name = BTC-PERPETUAL; // string | Instrument name
$type = 'type_example'; // string | Settlement type
$count = 56; // int | Number of requested items, default - `20`

try {
    $result = $apiInstance->privateGetSettlementHistoryByInstrumentGet($instrument_name, $type, $count);
    print_r($result);
} catch (Exception $e) {
    echo 'Exception when calling TradingApi->privateGetSettlementHistoryByInstrumentGet: ', $e->getMessage(), PHP_EOL;
}
?>
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrument_name** | **string**| Instrument name |
 **type** | **string**| Settlement type | [optional]
 **count** | **int**| Number of requested items, default - &#x60;20&#x60; | [optional]

### Return type

**object**

### Authorization

[bearerAuth](../../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../../README.md#documentation-for-models)
[[Back to README]](../../README.md)


## privateGetUserTradesByCurrencyAndTimeGet

> object privateGetUserTradesByCurrencyAndTimeGet($currency, $start_timestamp, $end_timestamp, $kind, $count, $include_old, $sorting)

Retrieve the latest user trades that have occurred for instruments in a specific currency symbol and within given time range.

### Example

```php
<?php
require_once(__DIR__ . '/vendor/autoload.php');


// Configure Bearer (Auth. Token) authorization: bearerAuth
$config = OpenAPI\Client\Configuration::getDefaultConfiguration()->setAccessToken('YOUR_ACCESS_TOKEN');


$apiInstance = new OpenAPI\Client\Api\TradingApi(
    // If you want use custom http client, pass your client which implements `GuzzleHttp\ClientInterface`.
    // This is optional, `GuzzleHttp\Client` will be used as default.
    new GuzzleHttp\Client(),
    $config
);
$currency = 'currency_example'; // string | The currency symbol
$start_timestamp = 1536569522277; // int | The earliest timestamp to return result for
$end_timestamp = 1536569522277; // int | The most recent timestamp to return result for
$kind = 'kind_example'; // string | Instrument kind, if not provided instruments of all kinds are considered
$count = 56; // int | Number of requested items, default - `10`
$include_old = True; // bool | Include trades older than 7 days, default - `false`
$sorting = 'sorting_example'; // string | Direction of results sorting (`default` value means no sorting, results will be returned in order in which they left the database)

try {
    $result = $apiInstance->privateGetUserTradesByCurrencyAndTimeGet($currency, $start_timestamp, $end_timestamp, $kind, $count, $include_old, $sorting);
    print_r($result);
} catch (Exception $e) {
    echo 'Exception when calling TradingApi->privateGetUserTradesByCurrencyAndTimeGet: ', $e->getMessage(), PHP_EOL;
}
?>
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **string**| The currency symbol |
 **start_timestamp** | **int**| The earliest timestamp to return result for |
 **end_timestamp** | **int**| The most recent timestamp to return result for |
 **kind** | **string**| Instrument kind, if not provided instruments of all kinds are considered | [optional]
 **count** | **int**| Number of requested items, default - &#x60;10&#x60; | [optional]
 **include_old** | **bool**| Include trades older than 7 days, default - &#x60;false&#x60; | [optional]
 **sorting** | **string**| Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) | [optional]

### Return type

**object**

### Authorization

[bearerAuth](../../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../../README.md#documentation-for-models)
[[Back to README]](../../README.md)


## privateGetUserTradesByCurrencyGet

> object privateGetUserTradesByCurrencyGet($currency, $kind, $start_id, $end_id, $count, $include_old, $sorting)

Retrieve the latest user trades that have occurred for instruments in a specific currency symbol.

### Example

```php
<?php
require_once(__DIR__ . '/vendor/autoload.php');


// Configure Bearer (Auth. Token) authorization: bearerAuth
$config = OpenAPI\Client\Configuration::getDefaultConfiguration()->setAccessToken('YOUR_ACCESS_TOKEN');


$apiInstance = new OpenAPI\Client\Api\TradingApi(
    // If you want use custom http client, pass your client which implements `GuzzleHttp\ClientInterface`.
    // This is optional, `GuzzleHttp\Client` will be used as default.
    new GuzzleHttp\Client(),
    $config
);
$currency = 'currency_example'; // string | The currency symbol
$kind = 'kind_example'; // string | Instrument kind, if not provided instruments of all kinds are considered
$start_id = 'start_id_example'; // string | The ID number of the first trade to be returned
$end_id = 'end_id_example'; // string | The ID number of the last trade to be returned
$count = 56; // int | Number of requested items, default - `10`
$include_old = True; // bool | Include trades older than 7 days, default - `false`
$sorting = 'sorting_example'; // string | Direction of results sorting (`default` value means no sorting, results will be returned in order in which they left the database)

try {
    $result = $apiInstance->privateGetUserTradesByCurrencyGet($currency, $kind, $start_id, $end_id, $count, $include_old, $sorting);
    print_r($result);
} catch (Exception $e) {
    echo 'Exception when calling TradingApi->privateGetUserTradesByCurrencyGet: ', $e->getMessage(), PHP_EOL;
}
?>
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **string**| The currency symbol |
 **kind** | **string**| Instrument kind, if not provided instruments of all kinds are considered | [optional]
 **start_id** | **string**| The ID number of the first trade to be returned | [optional]
 **end_id** | **string**| The ID number of the last trade to be returned | [optional]
 **count** | **int**| Number of requested items, default - &#x60;10&#x60; | [optional]
 **include_old** | **bool**| Include trades older than 7 days, default - &#x60;false&#x60; | [optional]
 **sorting** | **string**| Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) | [optional]

### Return type

**object**

### Authorization

[bearerAuth](../../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../../README.md#documentation-for-models)
[[Back to README]](../../README.md)


## privateGetUserTradesByInstrumentAndTimeGet

> object privateGetUserTradesByInstrumentAndTimeGet($instrument_name, $start_timestamp, $end_timestamp, $count, $include_old, $sorting)

Retrieve the latest user trades that have occurred for a specific instrument and within given time range.

### Example

```php
<?php
require_once(__DIR__ . '/vendor/autoload.php');


// Configure Bearer (Auth. Token) authorization: bearerAuth
$config = OpenAPI\Client\Configuration::getDefaultConfiguration()->setAccessToken('YOUR_ACCESS_TOKEN');


$apiInstance = new OpenAPI\Client\Api\TradingApi(
    // If you want use custom http client, pass your client which implements `GuzzleHttp\ClientInterface`.
    // This is optional, `GuzzleHttp\Client` will be used as default.
    new GuzzleHttp\Client(),
    $config
);
$instrument_name = BTC-PERPETUAL; // string | Instrument name
$start_timestamp = 1536569522277; // int | The earliest timestamp to return result for
$end_timestamp = 1536569522277; // int | The most recent timestamp to return result for
$count = 56; // int | Number of requested items, default - `10`
$include_old = True; // bool | Include trades older than 7 days, default - `false`
$sorting = 'sorting_example'; // string | Direction of results sorting (`default` value means no sorting, results will be returned in order in which they left the database)

try {
    $result = $apiInstance->privateGetUserTradesByInstrumentAndTimeGet($instrument_name, $start_timestamp, $end_timestamp, $count, $include_old, $sorting);
    print_r($result);
} catch (Exception $e) {
    echo 'Exception when calling TradingApi->privateGetUserTradesByInstrumentAndTimeGet: ', $e->getMessage(), PHP_EOL;
}
?>
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrument_name** | **string**| Instrument name |
 **start_timestamp** | **int**| The earliest timestamp to return result for |
 **end_timestamp** | **int**| The most recent timestamp to return result for |
 **count** | **int**| Number of requested items, default - &#x60;10&#x60; | [optional]
 **include_old** | **bool**| Include trades older than 7 days, default - &#x60;false&#x60; | [optional]
 **sorting** | **string**| Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) | [optional]

### Return type

**object**

### Authorization

[bearerAuth](../../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../../README.md#documentation-for-models)
[[Back to README]](../../README.md)


## privateGetUserTradesByInstrumentGet

> object privateGetUserTradesByInstrumentGet($instrument_name, $start_seq, $end_seq, $count, $include_old, $sorting)

Retrieve the latest user trades that have occurred for a specific instrument.

### Example

```php
<?php
require_once(__DIR__ . '/vendor/autoload.php');


// Configure Bearer (Auth. Token) authorization: bearerAuth
$config = OpenAPI\Client\Configuration::getDefaultConfiguration()->setAccessToken('YOUR_ACCESS_TOKEN');


$apiInstance = new OpenAPI\Client\Api\TradingApi(
    // If you want use custom http client, pass your client which implements `GuzzleHttp\ClientInterface`.
    // This is optional, `GuzzleHttp\Client` will be used as default.
    new GuzzleHttp\Client(),
    $config
);
$instrument_name = BTC-PERPETUAL; // string | Instrument name
$start_seq = 56; // int | The sequence number of the first trade to be returned
$end_seq = 56; // int | The sequence number of the last trade to be returned
$count = 56; // int | Number of requested items, default - `10`
$include_old = True; // bool | Include trades older than 7 days, default - `false`
$sorting = 'sorting_example'; // string | Direction of results sorting (`default` value means no sorting, results will be returned in order in which they left the database)

try {
    $result = $apiInstance->privateGetUserTradesByInstrumentGet($instrument_name, $start_seq, $end_seq, $count, $include_old, $sorting);
    print_r($result);
} catch (Exception $e) {
    echo 'Exception when calling TradingApi->privateGetUserTradesByInstrumentGet: ', $e->getMessage(), PHP_EOL;
}
?>
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrument_name** | **string**| Instrument name |
 **start_seq** | **int**| The sequence number of the first trade to be returned | [optional]
 **end_seq** | **int**| The sequence number of the last trade to be returned | [optional]
 **count** | **int**| Number of requested items, default - &#x60;10&#x60; | [optional]
 **include_old** | **bool**| Include trades older than 7 days, default - &#x60;false&#x60; | [optional]
 **sorting** | **string**| Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) | [optional]

### Return type

**object**

### Authorization

[bearerAuth](../../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../../README.md#documentation-for-models)
[[Back to README]](../../README.md)


## privateGetUserTradesByOrderGet

> object privateGetUserTradesByOrderGet($order_id, $sorting)

Retrieve the list of user trades that was created for given order

### Example

```php
<?php
require_once(__DIR__ . '/vendor/autoload.php');


// Configure Bearer (Auth. Token) authorization: bearerAuth
$config = OpenAPI\Client\Configuration::getDefaultConfiguration()->setAccessToken('YOUR_ACCESS_TOKEN');


$apiInstance = new OpenAPI\Client\Api\TradingApi(
    // If you want use custom http client, pass your client which implements `GuzzleHttp\ClientInterface`.
    // This is optional, `GuzzleHttp\Client` will be used as default.
    new GuzzleHttp\Client(),
    $config
);
$order_id = ETH-100234; // string | The order id
$sorting = 'sorting_example'; // string | Direction of results sorting (`default` value means no sorting, results will be returned in order in which they left the database)

try {
    $result = $apiInstance->privateGetUserTradesByOrderGet($order_id, $sorting);
    print_r($result);
} catch (Exception $e) {
    echo 'Exception when calling TradingApi->privateGetUserTradesByOrderGet: ', $e->getMessage(), PHP_EOL;
}
?>
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **order_id** | **string**| The order id |
 **sorting** | **string**| Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) | [optional]

### Return type

**object**

### Authorization

[bearerAuth](../../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../../README.md#documentation-for-models)
[[Back to README]](../../README.md)


## privateSellGet

> object privateSellGet($instrument_name, $amount, $type, $label, $price, $time_in_force, $max_show, $post_only, $reduce_only, $stop_price, $trigger, $advanced)

Places a sell order for an instrument.

### Example

```php
<?php
require_once(__DIR__ . '/vendor/autoload.php');


// Configure Bearer (Auth. Token) authorization: bearerAuth
$config = OpenAPI\Client\Configuration::getDefaultConfiguration()->setAccessToken('YOUR_ACCESS_TOKEN');


$apiInstance = new OpenAPI\Client\Api\TradingApi(
    // If you want use custom http client, pass your client which implements `GuzzleHttp\ClientInterface`.
    // This is optional, `GuzzleHttp\Client` will be used as default.
    new GuzzleHttp\Client(),
    $config
);
$instrument_name = BTC-PERPETUAL; // string | Instrument name
$amount = 3.4; // float | It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH
$type = 'type_example'; // string | The order type, default: `\"limit\"`
$label = 'label_example'; // string | user defined label for the order (maximum 32 characters)
$price = 3.4; // float | <p>The order price in base currency (Only for limit and stop_limit orders)</p> <p>When adding order with advanced=usd, the field price should be the option price value in USD.</p> <p>When adding order with advanced=implv, the field price should be a value of implied volatility in percentages. For example,  price=100, means implied volatility of 100%</p>
$time_in_force = 'good_til_cancelled'; // string | <p>Specifies how long the order remains in effect. Default `\"good_til_cancelled\"`</p> <ul> <li>`\"good_til_cancelled\"` - unfilled order remains in order book until cancelled</li> <li>`\"fill_or_kill\"` - execute a transaction immediately and completely or not at all</li> <li>`\"immediate_or_cancel\"` - execute a transaction immediately, and any portion of the order that cannot be immediately filled is cancelled</li> </ul>
$max_show = 1; // float | Maximum amount within an order to be shown to other customers, `0` for invisible order
$post_only = true; // bool | <p>If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.</p> <p>Only valid in combination with time_in_force=`\"good_til_cancelled\"`</p>
$reduce_only = false; // bool | If `true`, the order is considered reduce-only which is intended to only reduce a current position
$stop_price = 3.4; // float | Stop price, required for stop limit orders (Only for stop orders)
$trigger = 'trigger_example'; // string | Defines trigger type, required for `\"stop_limit\"` order type
$advanced = 'advanced_example'; // string | Advanced option order type. (Only for options)

try {
    $result = $apiInstance->privateSellGet($instrument_name, $amount, $type, $label, $price, $time_in_force, $max_show, $post_only, $reduce_only, $stop_price, $trigger, $advanced);
    print_r($result);
} catch (Exception $e) {
    echo 'Exception when calling TradingApi->privateSellGet: ', $e->getMessage(), PHP_EOL;
}
?>
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrument_name** | **string**| Instrument name |
 **amount** | **float**| It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH |
 **type** | **string**| The order type, default: &#x60;\&quot;limit\&quot;&#x60; | [optional]
 **label** | **string**| user defined label for the order (maximum 32 characters) | [optional]
 **price** | **float**| &lt;p&gt;The order price in base currency (Only for limit and stop_limit orders)&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;usd, the field price should be the option price value in USD.&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;implv, the field price should be a value of implied volatility in percentages. For example,  price&#x3D;100, means implied volatility of 100%&lt;/p&gt; | [optional]
 **time_in_force** | **string**| &lt;p&gt;Specifies how long the order remains in effect. Default &#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; &lt;ul&gt; &lt;li&gt;&#x60;\&quot;good_til_cancelled\&quot;&#x60; - unfilled order remains in order book until cancelled&lt;/li&gt; &lt;li&gt;&#x60;\&quot;fill_or_kill\&quot;&#x60; - execute a transaction immediately and completely or not at all&lt;/li&gt; &lt;li&gt;&#x60;\&quot;immediate_or_cancel\&quot;&#x60; - execute a transaction immediately, and any portion of the order that cannot be immediately filled is cancelled&lt;/li&gt; &lt;/ul&gt; | [optional] [default to &#39;good_til_cancelled&#39;]
 **max_show** | **float**| Maximum amount within an order to be shown to other customers, &#x60;0&#x60; for invisible order | [optional] [default to 1]
 **post_only** | **bool**| &lt;p&gt;If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.&lt;/p&gt; &lt;p&gt;Only valid in combination with time_in_force&#x3D;&#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; | [optional] [default to true]
 **reduce_only** | **bool**| If &#x60;true&#x60;, the order is considered reduce-only which is intended to only reduce a current position | [optional] [default to false]
 **stop_price** | **float**| Stop price, required for stop limit orders (Only for stop orders) | [optional]
 **trigger** | **string**| Defines trigger type, required for &#x60;\&quot;stop_limit\&quot;&#x60; order type | [optional]
 **advanced** | **string**| Advanced option order type. (Only for options) | [optional]

### Return type

**object**

### Authorization

[bearerAuth](../../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../../README.md#documentation-for-models)
[[Back to README]](../../README.md)

