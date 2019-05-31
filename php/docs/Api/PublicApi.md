# OpenAPI\Client\PublicApi

All URIs are relative to *https://www.deribit.com/api/v2*

Method | HTTP request | Description
------------- | ------------- | -------------
[**publicAuthGet**](PublicApi.md#publicAuthGet) | **GET** /public/auth | Authenticate
[**publicGetAnnouncementsGet**](PublicApi.md#publicGetAnnouncementsGet) | **GET** /public/get_announcements | Retrieves announcements from the last 30 days.
[**publicGetBookSummaryByCurrencyGet**](PublicApi.md#publicGetBookSummaryByCurrencyGet) | **GET** /public/get_book_summary_by_currency | Retrieves the summary information such as open interest, 24h volume, etc. for all instruments for the currency (optionally filtered by kind).
[**publicGetBookSummaryByInstrumentGet**](PublicApi.md#publicGetBookSummaryByInstrumentGet) | **GET** /public/get_book_summary_by_instrument | Retrieves the summary information such as open interest, 24h volume, etc. for a specific instrument.
[**publicGetContractSizeGet**](PublicApi.md#publicGetContractSizeGet) | **GET** /public/get_contract_size | Retrieves contract size of provided instrument.
[**publicGetCurrenciesGet**](PublicApi.md#publicGetCurrenciesGet) | **GET** /public/get_currencies | Retrieves all cryptocurrencies supported by the API.
[**publicGetFundingChartDataGet**](PublicApi.md#publicGetFundingChartDataGet) | **GET** /public/get_funding_chart_data | Retrieve the latest user trades that have occurred for PERPETUAL instruments in a specific currency symbol and within given time range.
[**publicGetHistoricalVolatilityGet**](PublicApi.md#publicGetHistoricalVolatilityGet) | **GET** /public/get_historical_volatility | Provides information about historical volatility for given cryptocurrency.
[**publicGetIndexGet**](PublicApi.md#publicGetIndexGet) | **GET** /public/get_index | Retrieves the current index price for the instruments, for the selected currency.
[**publicGetInstrumentsGet**](PublicApi.md#publicGetInstrumentsGet) | **GET** /public/get_instruments | Retrieves available trading instruments. This method can be used to see which instruments are available for trading, or which instruments have existed historically.
[**publicGetLastSettlementsByCurrencyGet**](PublicApi.md#publicGetLastSettlementsByCurrencyGet) | **GET** /public/get_last_settlements_by_currency | Retrieves historical settlement, delivery and bankruptcy events coming from all instruments within given currency.
[**publicGetLastSettlementsByInstrumentGet**](PublicApi.md#publicGetLastSettlementsByInstrumentGet) | **GET** /public/get_last_settlements_by_instrument | Retrieves historical public settlement, delivery and bankruptcy events filtered by instrument name.
[**publicGetLastTradesByCurrencyAndTimeGet**](PublicApi.md#publicGetLastTradesByCurrencyAndTimeGet) | **GET** /public/get_last_trades_by_currency_and_time | Retrieve the latest trades that have occurred for instruments in a specific currency symbol and within given time range.
[**publicGetLastTradesByCurrencyGet**](PublicApi.md#publicGetLastTradesByCurrencyGet) | **GET** /public/get_last_trades_by_currency | Retrieve the latest trades that have occurred for instruments in a specific currency symbol.
[**publicGetLastTradesByInstrumentAndTimeGet**](PublicApi.md#publicGetLastTradesByInstrumentAndTimeGet) | **GET** /public/get_last_trades_by_instrument_and_time | Retrieve the latest trades that have occurred for a specific instrument and within given time range.
[**publicGetLastTradesByInstrumentGet**](PublicApi.md#publicGetLastTradesByInstrumentGet) | **GET** /public/get_last_trades_by_instrument | Retrieve the latest trades that have occurred for a specific instrument.
[**publicGetOrderBookGet**](PublicApi.md#publicGetOrderBookGet) | **GET** /public/get_order_book | Retrieves the order book, along with other market values for a given instrument.
[**publicGetTimeGet**](PublicApi.md#publicGetTimeGet) | **GET** /public/get_time | Retrieves the current time (in milliseconds). This API endpoint can be used to check the clock skew between your software and Deribit&#39;s systems.
[**publicGetTradeVolumesGet**](PublicApi.md#publicGetTradeVolumesGet) | **GET** /public/get_trade_volumes | Retrieves aggregated 24h trade volumes for different instrument types and currencies.
[**publicGetTradingviewChartDataGet**](PublicApi.md#publicGetTradingviewChartDataGet) | **GET** /public/get_tradingview_chart_data | Publicly available market data used to generate a TradingView candle chart.
[**publicTestGet**](PublicApi.md#publicTestGet) | **GET** /public/test | Tests the connection to the API server, and returns its version. You can use this to make sure the API is reachable, and matches the expected version.
[**publicTickerGet**](PublicApi.md#publicTickerGet) | **GET** /public/ticker | Get ticker for an instrument.
[**publicValidateFieldGet**](PublicApi.md#publicValidateFieldGet) | **GET** /public/validate_field | Method used to introduce the client software connected to Deribit platform over websocket. Provided data may have an impact on the maintained connection and will be collected for internal statistical purposes. In response, Deribit will also introduce itself.



## publicAuthGet

> object publicAuthGet($grant_type, $username, $password, $client_id, $client_secret, $refresh_token, $timestamp, $signature, $nonce, $state, $scope)

Authenticate

Retrieve an Oauth access token, to be used for authentication of 'private' requests.  Three methods of authentication are supported:  - <code>password</code> - using email and and password as when logging on to the website - <code>client_credentials</code> - using the access key and access secret that can be found on the API page on the website - <code>client_signature</code> - using the access key that can be found on the API page on the website and user generated signature. The signature is calculated using some fields provided in the request, using formula described here [Deribit signature credentials](#additional-authorization-method-deribit-signature-credentials) - <code>refresh_token</code> - using a refresh token that was received from an earlier invocation  The response will contain an access token, expiration period (number of seconds that the token is valid) and a refresh token that can be used to get a new set of tokens.

### Example

```php
<?php
require_once(__DIR__ . '/vendor/autoload.php');


// Configure Bearer (Auth. Token) authorization: bearerAuth
$config = OpenAPI\Client\Configuration::getDefaultConfiguration()->setAccessToken('YOUR_ACCESS_TOKEN');


$apiInstance = new OpenAPI\Client\Api\PublicApi(
    // If you want use custom http client, pass your client which implements `GuzzleHttp\ClientInterface`.
    // This is optional, `GuzzleHttp\Client` will be used as default.
    new GuzzleHttp\Client(),
    $config
);
$grant_type = 'grant_type_example'; // string | Method of authentication
$username = your_email@mail.com; // string | Required for grant type `password`
$password = your_password; // string | Required for grant type `password`
$client_id = 'client_id_example'; // string | Required for grant type `client_credentials` and `client_signature`
$client_secret = 'client_secret_example'; // string | Required for grant type `client_credentials`
$refresh_token = 'refresh_token_example'; // string | Required for grant type `refresh_token`
$timestamp = 'timestamp_example'; // string | Required for grant type `client_signature`, provides time when request has been generated
$signature = 'signature_example'; // string | Required for grant type `client_signature`; it's a cryptographic signature calculated over provided fields using user **secret key**. The signature should be calculated as an HMAC (Hash-based Message Authentication Code) with `SHA256` hash algorithm
$nonce = 'nonce_example'; // string | Optional for grant type `client_signature`; delivers user generated initialization vector for the server token
$state = 'state_example'; // string | Will be passed back in the response
$scope = connection; // string | Describes type of the access for assigned token, possible values: `connection`, `session`, `session:name`, `trade:[read, read_write, none]`, `wallet:[read, read_write, none]`, `account:[read, read_write, none]`, `expires:NUMBER` (token will expire after `NUMBER` of seconds).</BR></BR> **NOTICE:** Depending on choosing an authentication method (```grant type```) some scopes could be narrowed by the server. e.g. when ```grant_type = client_credentials``` and ```scope = wallet:read_write``` it's modified by the server as ```scope = wallet:read```

try {
    $result = $apiInstance->publicAuthGet($grant_type, $username, $password, $client_id, $client_secret, $refresh_token, $timestamp, $signature, $nonce, $state, $scope);
    print_r($result);
} catch (Exception $e) {
    echo 'Exception when calling PublicApi->publicAuthGet: ', $e->getMessage(), PHP_EOL;
}
?>
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **grant_type** | **string**| Method of authentication |
 **username** | **string**| Required for grant type &#x60;password&#x60; |
 **password** | **string**| Required for grant type &#x60;password&#x60; |
 **client_id** | **string**| Required for grant type &#x60;client_credentials&#x60; and &#x60;client_signature&#x60; |
 **client_secret** | **string**| Required for grant type &#x60;client_credentials&#x60; |
 **refresh_token** | **string**| Required for grant type &#x60;refresh_token&#x60; |
 **timestamp** | **string**| Required for grant type &#x60;client_signature&#x60;, provides time when request has been generated |
 **signature** | **string**| Required for grant type &#x60;client_signature&#x60;; it&#39;s a cryptographic signature calculated over provided fields using user **secret key**. The signature should be calculated as an HMAC (Hash-based Message Authentication Code) with &#x60;SHA256&#x60; hash algorithm |
 **nonce** | **string**| Optional for grant type &#x60;client_signature&#x60;; delivers user generated initialization vector for the server token | [optional]
 **state** | **string**| Will be passed back in the response | [optional]
 **scope** | **string**| Describes type of the access for assigned token, possible values: &#x60;connection&#x60;, &#x60;session&#x60;, &#x60;session:name&#x60;, &#x60;trade:[read, read_write, none]&#x60;, &#x60;wallet:[read, read_write, none]&#x60;, &#x60;account:[read, read_write, none]&#x60;, &#x60;expires:NUMBER&#x60; (token will expire after &#x60;NUMBER&#x60; of seconds).&lt;/BR&gt;&lt;/BR&gt; **NOTICE:** Depending on choosing an authentication method (&#x60;&#x60;&#x60;grant type&#x60;&#x60;&#x60;) some scopes could be narrowed by the server. e.g. when &#x60;&#x60;&#x60;grant_type &#x3D; client_credentials&#x60;&#x60;&#x60; and &#x60;&#x60;&#x60;scope &#x3D; wallet:read_write&#x60;&#x60;&#x60; it&#39;s modified by the server as &#x60;&#x60;&#x60;scope &#x3D; wallet:read&#x60;&#x60;&#x60; | [optional]

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


## publicGetAnnouncementsGet

> object publicGetAnnouncementsGet()

Retrieves announcements from the last 30 days.

### Example

```php
<?php
require_once(__DIR__ . '/vendor/autoload.php');


// Configure Bearer (Auth. Token) authorization: bearerAuth
$config = OpenAPI\Client\Configuration::getDefaultConfiguration()->setAccessToken('YOUR_ACCESS_TOKEN');


$apiInstance = new OpenAPI\Client\Api\PublicApi(
    // If you want use custom http client, pass your client which implements `GuzzleHttp\ClientInterface`.
    // This is optional, `GuzzleHttp\Client` will be used as default.
    new GuzzleHttp\Client(),
    $config
);

try {
    $result = $apiInstance->publicGetAnnouncementsGet();
    print_r($result);
} catch (Exception $e) {
    echo 'Exception when calling PublicApi->publicGetAnnouncementsGet: ', $e->getMessage(), PHP_EOL;
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


## publicGetBookSummaryByCurrencyGet

> object publicGetBookSummaryByCurrencyGet($currency, $kind)

Retrieves the summary information such as open interest, 24h volume, etc. for all instruments for the currency (optionally filtered by kind).

### Example

```php
<?php
require_once(__DIR__ . '/vendor/autoload.php');


// Configure Bearer (Auth. Token) authorization: bearerAuth
$config = OpenAPI\Client\Configuration::getDefaultConfiguration()->setAccessToken('YOUR_ACCESS_TOKEN');


$apiInstance = new OpenAPI\Client\Api\PublicApi(
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
    echo 'Exception when calling PublicApi->publicGetBookSummaryByCurrencyGet: ', $e->getMessage(), PHP_EOL;
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


$apiInstance = new OpenAPI\Client\Api\PublicApi(
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
    echo 'Exception when calling PublicApi->publicGetBookSummaryByInstrumentGet: ', $e->getMessage(), PHP_EOL;
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


$apiInstance = new OpenAPI\Client\Api\PublicApi(
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
    echo 'Exception when calling PublicApi->publicGetContractSizeGet: ', $e->getMessage(), PHP_EOL;
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


$apiInstance = new OpenAPI\Client\Api\PublicApi(
    // If you want use custom http client, pass your client which implements `GuzzleHttp\ClientInterface`.
    // This is optional, `GuzzleHttp\Client` will be used as default.
    new GuzzleHttp\Client(),
    $config
);

try {
    $result = $apiInstance->publicGetCurrenciesGet();
    print_r($result);
} catch (Exception $e) {
    echo 'Exception when calling PublicApi->publicGetCurrenciesGet: ', $e->getMessage(), PHP_EOL;
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


$apiInstance = new OpenAPI\Client\Api\PublicApi(
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
    echo 'Exception when calling PublicApi->publicGetFundingChartDataGet: ', $e->getMessage(), PHP_EOL;
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


$apiInstance = new OpenAPI\Client\Api\PublicApi(
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
    echo 'Exception when calling PublicApi->publicGetHistoricalVolatilityGet: ', $e->getMessage(), PHP_EOL;
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


$apiInstance = new OpenAPI\Client\Api\PublicApi(
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
    echo 'Exception when calling PublicApi->publicGetIndexGet: ', $e->getMessage(), PHP_EOL;
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


$apiInstance = new OpenAPI\Client\Api\PublicApi(
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
    echo 'Exception when calling PublicApi->publicGetInstrumentsGet: ', $e->getMessage(), PHP_EOL;
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


$apiInstance = new OpenAPI\Client\Api\PublicApi(
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
    echo 'Exception when calling PublicApi->publicGetLastSettlementsByCurrencyGet: ', $e->getMessage(), PHP_EOL;
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


$apiInstance = new OpenAPI\Client\Api\PublicApi(
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
    echo 'Exception when calling PublicApi->publicGetLastSettlementsByInstrumentGet: ', $e->getMessage(), PHP_EOL;
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


$apiInstance = new OpenAPI\Client\Api\PublicApi(
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
    echo 'Exception when calling PublicApi->publicGetLastTradesByCurrencyAndTimeGet: ', $e->getMessage(), PHP_EOL;
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


$apiInstance = new OpenAPI\Client\Api\PublicApi(
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
    echo 'Exception when calling PublicApi->publicGetLastTradesByCurrencyGet: ', $e->getMessage(), PHP_EOL;
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


$apiInstance = new OpenAPI\Client\Api\PublicApi(
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
    echo 'Exception when calling PublicApi->publicGetLastTradesByInstrumentAndTimeGet: ', $e->getMessage(), PHP_EOL;
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


$apiInstance = new OpenAPI\Client\Api\PublicApi(
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
    echo 'Exception when calling PublicApi->publicGetLastTradesByInstrumentGet: ', $e->getMessage(), PHP_EOL;
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


$apiInstance = new OpenAPI\Client\Api\PublicApi(
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
    echo 'Exception when calling PublicApi->publicGetOrderBookGet: ', $e->getMessage(), PHP_EOL;
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


## publicGetTimeGet

> object publicGetTimeGet()

Retrieves the current time (in milliseconds). This API endpoint can be used to check the clock skew between your software and Deribit's systems.

### Example

```php
<?php
require_once(__DIR__ . '/vendor/autoload.php');


// Configure Bearer (Auth. Token) authorization: bearerAuth
$config = OpenAPI\Client\Configuration::getDefaultConfiguration()->setAccessToken('YOUR_ACCESS_TOKEN');


$apiInstance = new OpenAPI\Client\Api\PublicApi(
    // If you want use custom http client, pass your client which implements `GuzzleHttp\ClientInterface`.
    // This is optional, `GuzzleHttp\Client` will be used as default.
    new GuzzleHttp\Client(),
    $config
);

try {
    $result = $apiInstance->publicGetTimeGet();
    print_r($result);
} catch (Exception $e) {
    echo 'Exception when calling PublicApi->publicGetTimeGet: ', $e->getMessage(), PHP_EOL;
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


## publicGetTradeVolumesGet

> object publicGetTradeVolumesGet()

Retrieves aggregated 24h trade volumes for different instrument types and currencies.

### Example

```php
<?php
require_once(__DIR__ . '/vendor/autoload.php');


// Configure Bearer (Auth. Token) authorization: bearerAuth
$config = OpenAPI\Client\Configuration::getDefaultConfiguration()->setAccessToken('YOUR_ACCESS_TOKEN');


$apiInstance = new OpenAPI\Client\Api\PublicApi(
    // If you want use custom http client, pass your client which implements `GuzzleHttp\ClientInterface`.
    // This is optional, `GuzzleHttp\Client` will be used as default.
    new GuzzleHttp\Client(),
    $config
);

try {
    $result = $apiInstance->publicGetTradeVolumesGet();
    print_r($result);
} catch (Exception $e) {
    echo 'Exception when calling PublicApi->publicGetTradeVolumesGet: ', $e->getMessage(), PHP_EOL;
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


$apiInstance = new OpenAPI\Client\Api\PublicApi(
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
    echo 'Exception when calling PublicApi->publicGetTradingviewChartDataGet: ', $e->getMessage(), PHP_EOL;
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


## publicTestGet

> object publicTestGet($expected_result)

Tests the connection to the API server, and returns its version. You can use this to make sure the API is reachable, and matches the expected version.

### Example

```php
<?php
require_once(__DIR__ . '/vendor/autoload.php');


// Configure Bearer (Auth. Token) authorization: bearerAuth
$config = OpenAPI\Client\Configuration::getDefaultConfiguration()->setAccessToken('YOUR_ACCESS_TOKEN');


$apiInstance = new OpenAPI\Client\Api\PublicApi(
    // If you want use custom http client, pass your client which implements `GuzzleHttp\ClientInterface`.
    // This is optional, `GuzzleHttp\Client` will be used as default.
    new GuzzleHttp\Client(),
    $config
);
$expected_result = 'expected_result_example'; // string | The value \"exception\" will trigger an error response. This may be useful for testing wrapper libraries.

try {
    $result = $apiInstance->publicTestGet($expected_result);
    print_r($result);
} catch (Exception $e) {
    echo 'Exception when calling PublicApi->publicTestGet: ', $e->getMessage(), PHP_EOL;
}
?>
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **expected_result** | **string**| The value \&quot;exception\&quot; will trigger an error response. This may be useful for testing wrapper libraries. | [optional]

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


$apiInstance = new OpenAPI\Client\Api\PublicApi(
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
    echo 'Exception when calling PublicApi->publicTickerGet: ', $e->getMessage(), PHP_EOL;
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


## publicValidateFieldGet

> object publicValidateFieldGet($field, $value, $value2)

Method used to introduce the client software connected to Deribit platform over websocket. Provided data may have an impact on the maintained connection and will be collected for internal statistical purposes. In response, Deribit will also introduce itself.

### Example

```php
<?php
require_once(__DIR__ . '/vendor/autoload.php');


// Configure Bearer (Auth. Token) authorization: bearerAuth
$config = OpenAPI\Client\Configuration::getDefaultConfiguration()->setAccessToken('YOUR_ACCESS_TOKEN');


$apiInstance = new OpenAPI\Client\Api\PublicApi(
    // If you want use custom http client, pass your client which implements `GuzzleHttp\ClientInterface`.
    // This is optional, `GuzzleHttp\Client` will be used as default.
    new GuzzleHttp\Client(),
    $config
);
$field = 'field_example'; // string | Name of the field to be validated, examples: postal_code, date_of_birth
$value = 'value_example'; // string | Value to be checked
$value2 = 'value2_example'; // string | Additional value to be compared with

try {
    $result = $apiInstance->publicValidateFieldGet($field, $value, $value2);
    print_r($result);
} catch (Exception $e) {
    echo 'Exception when calling PublicApi->publicValidateFieldGet: ', $e->getMessage(), PHP_EOL;
}
?>
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **field** | **string**| Name of the field to be validated, examples: postal_code, date_of_birth |
 **value** | **string**| Value to be checked |
 **value2** | **string**| Additional value to be compared with | [optional]

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

