# OpenAPI\Client\MarketDataApi

All URIs are relative to *https://www.deribit.com/api/v2*

Method | HTTP request | Description
------------- | ------------- | -------------
[**publicGetBookSummaryByCurrencyGet**](MarketDataApi.md#publicGetBookSummaryByCurrencyGet) | **GET** /public/get_book_summary_by_currency | Retrieves the summary information such as open interest, 24h volume, etc. for all instruments for the currency (optionally filtered by kind).
[**publicGetBookSummaryByInstrumentGet**](MarketDataApi.md#publicGetBookSummaryByInstrumentGet) | **GET** /public/get_book_summary_by_instrument | Retrieves the summary information such as open interest, 24h volume, etc. for a specific instrument.
[**publicGetContractSizeGet**](MarketDataApi.md#publicGetContractSizeGet) | **GET** /public/get_contract_size | Retrieves contract size of provided instrument.
[**publicGetCurrenciesGet**](MarketDataApi.md#publicGetCurrenciesGet) | **GET** /public/get_currencies | Retrieves all cryptocurrencies supported by the API.
[**publicGetFundingChartDataGet**](MarketDataApi.md#publicGetFundingChartDataGet) | **GET** /public/get_funding_chart_data | Retrieve the latest user trades that have occurred for PERPETUAL instruments in a specific currency symbol and within given time range.
[**publicGetHistoricalVolatilityGet**](MarketDataApi.md#publicGetHistoricalVolatilityGet) | **GET** /public/get_historical_volatility | Provides information about historical volatility for given cryptocurrency.
[**publicGetIndexGet**](MarketDataApi.md#publicGetIndexGet) | **GET** /public/get_index | Retrieves the current index price for the instruments, for the selected currency.
[**publicGetInstrumentsGet**](MarketDataApi.md#publicGetInstrumentsGet) | **GET** /public/get_instruments | Retrieves available trading instruments. This method can be used to see which instruments are available for trading, or which instruments have existed historically.
[**publicGetLastSettlementsByCurrencyGet**](MarketDataApi.md#publicGetLastSettlementsByCurrencyGet) | **GET** /public/get_last_settlements_by_currency | Retrieves historical settlement, delivery and bankruptcy events coming from all instruments within given currency.
[**publicGetLastSettlementsByInstrumentGet**](MarketDataApi.md#publicGetLastSettlementsByInstrumentGet) | **GET** /public/get_last_settlements_by_instrument | Retrieves historical public settlement, delivery and bankruptcy events filtered by instrument name.
[**publicGetLastTradesByCurrencyAndTimeGet**](MarketDataApi.md#publicGetLastTradesByCurrencyAndTimeGet) | **GET** /public/get_last_trades_by_currency_and_time | Retrieve the latest trades that have occurred for instruments in a specific currency symbol and within given time range.
[**publicGetLastTradesByCurrencyGet**](MarketDataApi.md#publicGetLastTradesByCurrencyGet) | **GET** /public/get_last_trades_by_currency | Retrieve the latest trades that have occurred for instruments in a specific currency symbol.
[**publicGetLastTradesByInstrumentAndTimeGet**](MarketDataApi.md#publicGetLastTradesByInstrumentAndTimeGet) | **GET** /public/get_last_trades_by_instrument_and_time | Retrieve the latest trades that have occurred for a specific instrument and within given time range.
[**publicGetLastTradesByInstrumentGet**](MarketDataApi.md#publicGetLastTradesByInstrumentGet) | **GET** /public/get_last_trades_by_instrument | Retrieve the latest trades that have occurred for a specific instrument.
[**publicGetOrderBookGet**](MarketDataApi.md#publicGetOrderBookGet) | **GET** /public/get_order_book | Retrieves the order book, along with other market values for a given instrument.
[**publicGetTradeVolumesGet**](MarketDataApi.md#publicGetTradeVolumesGet) | **GET** /public/get_trade_volumes | Retrieves aggregated 24h trade volumes for different instrument types and currencies.
[**publicGetTradingviewChartDataGet**](MarketDataApi.md#publicGetTradingviewChartDataGet) | **GET** /public/get_tradingview_chart_data | Publicly available market data used to generate a TradingView candle chart.
[**publicTickerGet**](MarketDataApi.md#publicTickerGet) | **GET** /public/ticker | Get ticker for an instrument.



## publicGetBookSummaryByCurrencyGet

> object publicGetBookSummaryByCurrencyGet($currency, $kind)

Retrieves the summary information such as open interest, 24h volume, etc. for all instruments for the currency (optionally filtered by kind).

### Example

```php
<?php
require_once(__DIR__ . '/vendor/autoload.php');


// Configure Bearer (Auth. Token) authorization: bearerAuth
$config = OpenAPI\Client\Configuration::getDefaultConfiguration()->setAccessToken('YOUR_ACCESS_TOKEN');


$apiInstance = new OpenAPI\Client\Api\MarketDataApi(
    // If you want use custom http client, pass your client which implements `GuzzleHttp\ClientInterface`.
    // This is optional, `GuzzleHttp\Client` will be used as default.
    new GuzzleHttp\Client(),
    $config
);
$currency = 'currency_example'; // string | The currency symbol
$kind = 'kind_example'; // string | Instrument kind, if not provided instruments of all kinds are considered

try {
    $result = $apiInstance->publicGetBookSummaryByCurrencyGet($currency, $kind);
    print_r($result);
} catch (Exception $e) {
    echo 'Exception when calling MarketDataApi->publicGetBookSummaryByCurrencyGet: ', $e->getMessage(), PHP_EOL;
}
?>
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **string**| The currency symbol |
 **kind** | **string**| Instrument kind, if not provided instruments of all kinds are considered | [optional]

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


## publicGetBookSummaryByInstrumentGet

> object publicGetBookSummaryByInstrumentGet($instrument_name)

Retrieves the summary information such as open interest, 24h volume, etc. for a specific instrument.

### Example

```php
<?php
require_once(__DIR__ . '/vendor/autoload.php');


// Configure Bearer (Auth. Token) authorization: bearerAuth
$config = OpenAPI\Client\Configuration::getDefaultConfiguration()->setAccessToken('YOUR_ACCESS_TOKEN');


$apiInstance = new OpenAPI\Client\Api\MarketDataApi(
    // If you want use custom http client, pass your client which implements `GuzzleHttp\ClientInterface`.
    // This is optional, `GuzzleHttp\Client` will be used as default.
    new GuzzleHttp\Client(),
    $config
);
$instrument_name = BTC-PERPETUAL; // string | Instrument name

try {
    $result = $apiInstance->publicGetBookSummaryByInstrumentGet($instrument_name);
    print_r($result);
} catch (Exception $e) {
    echo 'Exception when calling MarketDataApi->publicGetBookSummaryByInstrumentGet: ', $e->getMessage(), PHP_EOL;
}
?>
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrument_name** | **string**| Instrument name |

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


## publicGetContractSizeGet

> object publicGetContractSizeGet($instrument_name)

Retrieves contract size of provided instrument.

### Example

```php
<?php
require_once(__DIR__ . '/vendor/autoload.php');


// Configure Bearer (Auth. Token) authorization: bearerAuth
$config = OpenAPI\Client\Configuration::getDefaultConfiguration()->setAccessToken('YOUR_ACCESS_TOKEN');


$apiInstance = new OpenAPI\Client\Api\MarketDataApi(
    // If you want use custom http client, pass your client which implements `GuzzleHttp\ClientInterface`.
    // This is optional, `GuzzleHttp\Client` will be used as default.
    new GuzzleHttp\Client(),
    $config
);
$instrument_name = BTC-PERPETUAL; // string | Instrument name

try {
    $result = $apiInstance->publicGetContractSizeGet($instrument_name);
    print_r($result);
} catch (Exception $e) {
    echo 'Exception when calling MarketDataApi->publicGetContractSizeGet: ', $e->getMessage(), PHP_EOL;
}
?>
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrument_name** | **string**| Instrument name |

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


## publicGetCurrenciesGet

> object publicGetCurrenciesGet()

Retrieves all cryptocurrencies supported by the API.

### Example

```php
<?php
require_once(__DIR__ . '/vendor/autoload.php');


// Configure Bearer (Auth. Token) authorization: bearerAuth
$config = OpenAPI\Client\Configuration::getDefaultConfiguration()->setAccessToken('YOUR_ACCESS_TOKEN');


$apiInstance = new OpenAPI\Client\Api\MarketDataApi(
    // If you want use custom http client, pass your client which implements `GuzzleHttp\ClientInterface`.
    // This is optional, `GuzzleHttp\Client` will be used as default.
    new GuzzleHttp\Client(),
    $config
);

try {
    $result = $apiInstance->publicGetCurrenciesGet();
    print_r($result);
} catch (Exception $e) {
    echo 'Exception when calling MarketDataApi->publicGetCurrenciesGet: ', $e->getMessage(), PHP_EOL;
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


## publicGetFundingChartDataGet

> object publicGetFundingChartDataGet($instrument_name, $length)

Retrieve the latest user trades that have occurred for PERPETUAL instruments in a specific currency symbol and within given time range.

### Example

```php
<?php
require_once(__DIR__ . '/vendor/autoload.php');


// Configure Bearer (Auth. Token) authorization: bearerAuth
$config = OpenAPI\Client\Configuration::getDefaultConfiguration()->setAccessToken('YOUR_ACCESS_TOKEN');


$apiInstance = new OpenAPI\Client\Api\MarketDataApi(
    // If you want use custom http client, pass your client which implements `GuzzleHttp\ClientInterface`.
    // This is optional, `GuzzleHttp\Client` will be used as default.
    new GuzzleHttp\Client(),
    $config
);
$instrument_name = BTC-PERPETUAL; // string | Instrument name
$length = 'length_example'; // string | Specifies time period. `8h` - 8 hours, `24h` - 24 hours

try {
    $result = $apiInstance->publicGetFundingChartDataGet($instrument_name, $length);
    print_r($result);
} catch (Exception $e) {
    echo 'Exception when calling MarketDataApi->publicGetFundingChartDataGet: ', $e->getMessage(), PHP_EOL;
}
?>
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrument_name** | **string**| Instrument name |
 **length** | **string**| Specifies time period. &#x60;8h&#x60; - 8 hours, &#x60;24h&#x60; - 24 hours | [optional]

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


## publicGetHistoricalVolatilityGet

> object publicGetHistoricalVolatilityGet($currency)

Provides information about historical volatility for given cryptocurrency.

### Example

```php
<?php
require_once(__DIR__ . '/vendor/autoload.php');


// Configure Bearer (Auth. Token) authorization: bearerAuth
$config = OpenAPI\Client\Configuration::getDefaultConfiguration()->setAccessToken('YOUR_ACCESS_TOKEN');


$apiInstance = new OpenAPI\Client\Api\MarketDataApi(
    // If you want use custom http client, pass your client which implements `GuzzleHttp\ClientInterface`.
    // This is optional, `GuzzleHttp\Client` will be used as default.
    new GuzzleHttp\Client(),
    $config
);
$currency = 'currency_example'; // string | The currency symbol

try {
    $result = $apiInstance->publicGetHistoricalVolatilityGet($currency);
    print_r($result);
} catch (Exception $e) {
    echo 'Exception when calling MarketDataApi->publicGetHistoricalVolatilityGet: ', $e->getMessage(), PHP_EOL;
}
?>
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **string**| The currency symbol |

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


## publicGetIndexGet

> object publicGetIndexGet($currency)

Retrieves the current index price for the instruments, for the selected currency.

### Example

```php
<?php
require_once(__DIR__ . '/vendor/autoload.php');


// Configure Bearer (Auth. Token) authorization: bearerAuth
$config = OpenAPI\Client\Configuration::getDefaultConfiguration()->setAccessToken('YOUR_ACCESS_TOKEN');


$apiInstance = new OpenAPI\Client\Api\MarketDataApi(
    // If you want use custom http client, pass your client which implements `GuzzleHttp\ClientInterface`.
    // This is optional, `GuzzleHttp\Client` will be used as default.
    new GuzzleHttp\Client(),
    $config
);
$currency = 'currency_example'; // string | The currency symbol

try {
    $result = $apiInstance->publicGetIndexGet($currency);
    print_r($result);
} catch (Exception $e) {
    echo 'Exception when calling MarketDataApi->publicGetIndexGet: ', $e->getMessage(), PHP_EOL;
}
?>
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **string**| The currency symbol |

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


## publicGetInstrumentsGet

> object publicGetInstrumentsGet($currency, $kind, $expired)

Retrieves available trading instruments. This method can be used to see which instruments are available for trading, or which instruments have existed historically.

### Example

```php
<?php
require_once(__DIR__ . '/vendor/autoload.php');


// Configure Bearer (Auth. Token) authorization: bearerAuth
$config = OpenAPI\Client\Configuration::getDefaultConfiguration()->setAccessToken('YOUR_ACCESS_TOKEN');


$apiInstance = new OpenAPI\Client\Api\MarketDataApi(
    // If you want use custom http client, pass your client which implements `GuzzleHttp\ClientInterface`.
    // This is optional, `GuzzleHttp\Client` will be used as default.
    new GuzzleHttp\Client(),
    $config
);
$currency = 'currency_example'; // string | The currency symbol
$kind = 'kind_example'; // string | Instrument kind, if not provided instruments of all kinds are considered
$expired = false; // bool | Set to true to show expired instruments instead of active ones.

try {
    $result = $apiInstance->publicGetInstrumentsGet($currency, $kind, $expired);
    print_r($result);
} catch (Exception $e) {
    echo 'Exception when calling MarketDataApi->publicGetInstrumentsGet: ', $e->getMessage(), PHP_EOL;
}
?>
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **string**| The currency symbol |
 **kind** | **string**| Instrument kind, if not provided instruments of all kinds are considered | [optional]
 **expired** | **bool**| Set to true to show expired instruments instead of active ones. | [optional] [default to false]

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


## publicGetLastSettlementsByCurrencyGet

> object publicGetLastSettlementsByCurrencyGet($currency, $type, $count, $continuation, $search_start_timestamp)

Retrieves historical settlement, delivery and bankruptcy events coming from all instruments within given currency.

### Example

```php
<?php
require_once(__DIR__ . '/vendor/autoload.php');


// Configure Bearer (Auth. Token) authorization: bearerAuth
$config = OpenAPI\Client\Configuration::getDefaultConfiguration()->setAccessToken('YOUR_ACCESS_TOKEN');


$apiInstance = new OpenAPI\Client\Api\MarketDataApi(
    // If you want use custom http client, pass your client which implements `GuzzleHttp\ClientInterface`.
    // This is optional, `GuzzleHttp\Client` will be used as default.
    new GuzzleHttp\Client(),
    $config
);
$currency = 'currency_example'; // string | The currency symbol
$type = 'type_example'; // string | Settlement type
$count = 56; // int | Number of requested items, default - `20`
$continuation = xY7T6cutS3t2B9YtaDkE6TS379oKnkzTvmEDUnEUP2Msa9xKWNNaT; // string | Continuation token for pagination
$search_start_timestamp = 1536569522277; // int | The latest timestamp to return result for

try {
    $result = $apiInstance->publicGetLastSettlementsByCurrencyGet($currency, $type, $count, $continuation, $search_start_timestamp);
    print_r($result);
} catch (Exception $e) {
    echo 'Exception when calling MarketDataApi->publicGetLastSettlementsByCurrencyGet: ', $e->getMessage(), PHP_EOL;
}
?>
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **string**| The currency symbol |
 **type** | **string**| Settlement type | [optional]
 **count** | **int**| Number of requested items, default - &#x60;20&#x60; | [optional]
 **continuation** | **string**| Continuation token for pagination | [optional]
 **search_start_timestamp** | **int**| The latest timestamp to return result for | [optional]

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


## publicGetLastSettlementsByInstrumentGet

> object publicGetLastSettlementsByInstrumentGet($instrument_name, $type, $count, $continuation, $search_start_timestamp)

Retrieves historical public settlement, delivery and bankruptcy events filtered by instrument name.

### Example

```php
<?php
require_once(__DIR__ . '/vendor/autoload.php');


// Configure Bearer (Auth. Token) authorization: bearerAuth
$config = OpenAPI\Client\Configuration::getDefaultConfiguration()->setAccessToken('YOUR_ACCESS_TOKEN');


$apiInstance = new OpenAPI\Client\Api\MarketDataApi(
    // If you want use custom http client, pass your client which implements `GuzzleHttp\ClientInterface`.
    // This is optional, `GuzzleHttp\Client` will be used as default.
    new GuzzleHttp\Client(),
    $config
);
$instrument_name = BTC-PERPETUAL; // string | Instrument name
$type = 'type_example'; // string | Settlement type
$count = 56; // int | Number of requested items, default - `20`
$continuation = xY7T6cutS3t2B9YtaDkE6TS379oKnkzTvmEDUnEUP2Msa9xKWNNaT; // string | Continuation token for pagination
$search_start_timestamp = 1536569522277; // int | The latest timestamp to return result for

try {
    $result = $apiInstance->publicGetLastSettlementsByInstrumentGet($instrument_name, $type, $count, $continuation, $search_start_timestamp);
    print_r($result);
} catch (Exception $e) {
    echo 'Exception when calling MarketDataApi->publicGetLastSettlementsByInstrumentGet: ', $e->getMessage(), PHP_EOL;
}
?>
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrument_name** | **string**| Instrument name |
 **type** | **string**| Settlement type | [optional]
 **count** | **int**| Number of requested items, default - &#x60;20&#x60; | [optional]
 **continuation** | **string**| Continuation token for pagination | [optional]
 **search_start_timestamp** | **int**| The latest timestamp to return result for | [optional]

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


## publicGetLastTradesByCurrencyAndTimeGet

> object publicGetLastTradesByCurrencyAndTimeGet($currency, $start_timestamp, $end_timestamp, $kind, $count, $include_old, $sorting)

Retrieve the latest trades that have occurred for instruments in a specific currency symbol and within given time range.

### Example

```php
<?php
require_once(__DIR__ . '/vendor/autoload.php');


// Configure Bearer (Auth. Token) authorization: bearerAuth
$config = OpenAPI\Client\Configuration::getDefaultConfiguration()->setAccessToken('YOUR_ACCESS_TOKEN');


$apiInstance = new OpenAPI\Client\Api\MarketDataApi(
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
    $result = $apiInstance->publicGetLastTradesByCurrencyAndTimeGet($currency, $start_timestamp, $end_timestamp, $kind, $count, $include_old, $sorting);
    print_r($result);
} catch (Exception $e) {
    echo 'Exception when calling MarketDataApi->publicGetLastTradesByCurrencyAndTimeGet: ', $e->getMessage(), PHP_EOL;
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


## publicGetLastTradesByCurrencyGet

> object publicGetLastTradesByCurrencyGet($currency, $kind, $start_id, $end_id, $count, $include_old, $sorting)

Retrieve the latest trades that have occurred for instruments in a specific currency symbol.

### Example

```php
<?php
require_once(__DIR__ . '/vendor/autoload.php');


// Configure Bearer (Auth. Token) authorization: bearerAuth
$config = OpenAPI\Client\Configuration::getDefaultConfiguration()->setAccessToken('YOUR_ACCESS_TOKEN');


$apiInstance = new OpenAPI\Client\Api\MarketDataApi(
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
    $result = $apiInstance->publicGetLastTradesByCurrencyGet($currency, $kind, $start_id, $end_id, $count, $include_old, $sorting);
    print_r($result);
} catch (Exception $e) {
    echo 'Exception when calling MarketDataApi->publicGetLastTradesByCurrencyGet: ', $e->getMessage(), PHP_EOL;
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


## publicGetLastTradesByInstrumentAndTimeGet

> object publicGetLastTradesByInstrumentAndTimeGet($instrument_name, $start_timestamp, $end_timestamp, $count, $include_old, $sorting)

Retrieve the latest trades that have occurred for a specific instrument and within given time range.

### Example

```php
<?php
require_once(__DIR__ . '/vendor/autoload.php');


// Configure Bearer (Auth. Token) authorization: bearerAuth
$config = OpenAPI\Client\Configuration::getDefaultConfiguration()->setAccessToken('YOUR_ACCESS_TOKEN');


$apiInstance = new OpenAPI\Client\Api\MarketDataApi(
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
    $result = $apiInstance->publicGetLastTradesByInstrumentAndTimeGet($instrument_name, $start_timestamp, $end_timestamp, $count, $include_old, $sorting);
    print_r($result);
} catch (Exception $e) {
    echo 'Exception when calling MarketDataApi->publicGetLastTradesByInstrumentAndTimeGet: ', $e->getMessage(), PHP_EOL;
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


## publicGetLastTradesByInstrumentGet

> object publicGetLastTradesByInstrumentGet($instrument_name, $start_seq, $end_seq, $count, $include_old, $sorting)

Retrieve the latest trades that have occurred for a specific instrument.

### Example

```php
<?php
require_once(__DIR__ . '/vendor/autoload.php');


// Configure Bearer (Auth. Token) authorization: bearerAuth
$config = OpenAPI\Client\Configuration::getDefaultConfiguration()->setAccessToken('YOUR_ACCESS_TOKEN');


$apiInstance = new OpenAPI\Client\Api\MarketDataApi(
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
    $result = $apiInstance->publicGetLastTradesByInstrumentGet($instrument_name, $start_seq, $end_seq, $count, $include_old, $sorting);
    print_r($result);
} catch (Exception $e) {
    echo 'Exception when calling MarketDataApi->publicGetLastTradesByInstrumentGet: ', $e->getMessage(), PHP_EOL;
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


## publicGetOrderBookGet

> object publicGetOrderBookGet($instrument_name, $depth)

Retrieves the order book, along with other market values for a given instrument.

### Example

```php
<?php
require_once(__DIR__ . '/vendor/autoload.php');


// Configure Bearer (Auth. Token) authorization: bearerAuth
$config = OpenAPI\Client\Configuration::getDefaultConfiguration()->setAccessToken('YOUR_ACCESS_TOKEN');


$apiInstance = new OpenAPI\Client\Api\MarketDataApi(
    // If you want use custom http client, pass your client which implements `GuzzleHttp\ClientInterface`.
    // This is optional, `GuzzleHttp\Client` will be used as default.
    new GuzzleHttp\Client(),
    $config
);
$instrument_name = 'instrument_name_example'; // string | The instrument name for which to retrieve the order book, see [`getinstruments`](#getinstruments) to obtain instrument names.
$depth = 3.4; // float | The number of entries to return for bids and asks.

try {
    $result = $apiInstance->publicGetOrderBookGet($instrument_name, $depth);
    print_r($result);
} catch (Exception $e) {
    echo 'Exception when calling MarketDataApi->publicGetOrderBookGet: ', $e->getMessage(), PHP_EOL;
}
?>
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrument_name** | **string**| The instrument name for which to retrieve the order book, see [&#x60;getinstruments&#x60;](#getinstruments) to obtain instrument names. |
 **depth** | **float**| The number of entries to return for bids and asks. | [optional]

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


## publicGetTradeVolumesGet

> object publicGetTradeVolumesGet()

Retrieves aggregated 24h trade volumes for different instrument types and currencies.

### Example

```php
<?php
require_once(__DIR__ . '/vendor/autoload.php');


// Configure Bearer (Auth. Token) authorization: bearerAuth
$config = OpenAPI\Client\Configuration::getDefaultConfiguration()->setAccessToken('YOUR_ACCESS_TOKEN');


$apiInstance = new OpenAPI\Client\Api\MarketDataApi(
    // If you want use custom http client, pass your client which implements `GuzzleHttp\ClientInterface`.
    // This is optional, `GuzzleHttp\Client` will be used as default.
    new GuzzleHttp\Client(),
    $config
);

try {
    $result = $apiInstance->publicGetTradeVolumesGet();
    print_r($result);
} catch (Exception $e) {
    echo 'Exception when calling MarketDataApi->publicGetTradeVolumesGet: ', $e->getMessage(), PHP_EOL;
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


## publicGetTradingviewChartDataGet

> object publicGetTradingviewChartDataGet($instrument_name, $start_timestamp, $end_timestamp)

Publicly available market data used to generate a TradingView candle chart.

### Example

```php
<?php
require_once(__DIR__ . '/vendor/autoload.php');


// Configure Bearer (Auth. Token) authorization: bearerAuth
$config = OpenAPI\Client\Configuration::getDefaultConfiguration()->setAccessToken('YOUR_ACCESS_TOKEN');


$apiInstance = new OpenAPI\Client\Api\MarketDataApi(
    // If you want use custom http client, pass your client which implements `GuzzleHttp\ClientInterface`.
    // This is optional, `GuzzleHttp\Client` will be used as default.
    new GuzzleHttp\Client(),
    $config
);
$instrument_name = BTC-PERPETUAL; // string | Instrument name
$start_timestamp = 1536569522277; // int | The earliest timestamp to return result for
$end_timestamp = 1536569522277; // int | The most recent timestamp to return result for

try {
    $result = $apiInstance->publicGetTradingviewChartDataGet($instrument_name, $start_timestamp, $end_timestamp);
    print_r($result);
} catch (Exception $e) {
    echo 'Exception when calling MarketDataApi->publicGetTradingviewChartDataGet: ', $e->getMessage(), PHP_EOL;
}
?>
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrument_name** | **string**| Instrument name |
 **start_timestamp** | **int**| The earliest timestamp to return result for |
 **end_timestamp** | **int**| The most recent timestamp to return result for |

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


## publicTickerGet

> object publicTickerGet($instrument_name)

Get ticker for an instrument.

### Example

```php
<?php
require_once(__DIR__ . '/vendor/autoload.php');


// Configure Bearer (Auth. Token) authorization: bearerAuth
$config = OpenAPI\Client\Configuration::getDefaultConfiguration()->setAccessToken('YOUR_ACCESS_TOKEN');


$apiInstance = new OpenAPI\Client\Api\MarketDataApi(
    // If you want use custom http client, pass your client which implements `GuzzleHttp\ClientInterface`.
    // This is optional, `GuzzleHttp\Client` will be used as default.
    new GuzzleHttp\Client(),
    $config
);
$instrument_name = BTC-PERPETUAL; // string | Instrument name

try {
    $result = $apiInstance->publicTickerGet($instrument_name);
    print_r($result);
} catch (Exception $e) {
    echo 'Exception when calling MarketDataApi->publicTickerGet: ', $e->getMessage(), PHP_EOL;
}
?>
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrument_name** | **string**| Instrument name |

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

