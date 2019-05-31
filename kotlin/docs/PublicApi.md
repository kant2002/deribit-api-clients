# PublicApi

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


<a name="publicAuthGet"></a>
# **publicAuthGet**
> kotlin.Any publicAuthGet(grantType, username, password, clientId, clientSecret, refreshToken, timestamp, signature, nonce, state, scope)

Authenticate

Retrieve an Oauth access token, to be used for authentication of &#39;private&#39; requests.  Three methods of authentication are supported:  - &lt;code&gt;password&lt;/code&gt; - using email and and password as when logging on to the website - &lt;code&gt;client_credentials&lt;/code&gt; - using the access key and access secret that can be found on the API page on the website - &lt;code&gt;client_signature&lt;/code&gt; - using the access key that can be found on the API page on the website and user generated signature. The signature is calculated using some fields provided in the request, using formula described here [Deribit signature credentials](#additional-authorization-method-deribit-signature-credentials) - &lt;code&gt;refresh_token&lt;/code&gt; - using a refresh token that was received from an earlier invocation  The response will contain an access token, expiration period (number of seconds that the token is valid) and a refresh token that can be used to get a new set of tokens. 

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = PublicApi()
val grantType : kotlin.String = grantType_example // kotlin.String | Method of authentication
val username : kotlin.String = your_email@mail.com // kotlin.String | Required for grant type `password`
val password : kotlin.String = your_password // kotlin.String | Required for grant type `password`
val clientId : kotlin.String = clientId_example // kotlin.String | Required for grant type `client_credentials` and `client_signature`
val clientSecret : kotlin.String = clientSecret_example // kotlin.String | Required for grant type `client_credentials`
val refreshToken : kotlin.String = refreshToken_example // kotlin.String | Required for grant type `refresh_token`
val timestamp : kotlin.String = timestamp_example // kotlin.String | Required for grant type `client_signature`, provides time when request has been generated
val signature : kotlin.String = signature_example // kotlin.String | Required for grant type `client_signature`; it's a cryptographic signature calculated over provided fields using user **secret key**. The signature should be calculated as an HMAC (Hash-based Message Authentication Code) with `SHA256` hash algorithm
val nonce : kotlin.String = nonce_example // kotlin.String | Optional for grant type `client_signature`; delivers user generated initialization vector for the server token
val state : kotlin.String = state_example // kotlin.String | Will be passed back in the response
val scope : kotlin.String = connection // kotlin.String | Describes type of the access for assigned token, possible values: `connection`, `session`, `session:name`, `trade:[read, read_write, none]`, `wallet:[read, read_write, none]`, `account:[read, read_write, none]`, `expires:NUMBER` (token will expire after `NUMBER` of seconds).</BR></BR> **NOTICE:** Depending on choosing an authentication method (```grant type```) some scopes could be narrowed by the server. e.g. when ```grant_type = client_credentials``` and ```scope = wallet:read_write``` it's modified by the server as ```scope = wallet:read```
try {
    val result : kotlin.Any = apiInstance.publicAuthGet(grantType, username, password, clientId, clientSecret, refreshToken, timestamp, signature, nonce, state, scope)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling PublicApi#publicAuthGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling PublicApi#publicAuthGet")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **grantType** | **kotlin.String**| Method of authentication | [enum: password, client_credentials, client_signature, refresh_token]
 **username** | **kotlin.String**| Required for grant type &#x60;password&#x60; |
 **password** | **kotlin.String**| Required for grant type &#x60;password&#x60; |
 **clientId** | **kotlin.String**| Required for grant type &#x60;client_credentials&#x60; and &#x60;client_signature&#x60; |
 **clientSecret** | **kotlin.String**| Required for grant type &#x60;client_credentials&#x60; |
 **refreshToken** | **kotlin.String**| Required for grant type &#x60;refresh_token&#x60; |
 **timestamp** | **kotlin.String**| Required for grant type &#x60;client_signature&#x60;, provides time when request has been generated |
 **signature** | **kotlin.String**| Required for grant type &#x60;client_signature&#x60;; it&#39;s a cryptographic signature calculated over provided fields using user **secret key**. The signature should be calculated as an HMAC (Hash-based Message Authentication Code) with &#x60;SHA256&#x60; hash algorithm |
 **nonce** | **kotlin.String**| Optional for grant type &#x60;client_signature&#x60;; delivers user generated initialization vector for the server token | [optional]
 **state** | **kotlin.String**| Will be passed back in the response | [optional]
 **scope** | **kotlin.String**| Describes type of the access for assigned token, possible values: &#x60;connection&#x60;, &#x60;session&#x60;, &#x60;session:name&#x60;, &#x60;trade:[read, read_write, none]&#x60;, &#x60;wallet:[read, read_write, none]&#x60;, &#x60;account:[read, read_write, none]&#x60;, &#x60;expires:NUMBER&#x60; (token will expire after &#x60;NUMBER&#x60; of seconds).&lt;/BR&gt;&lt;/BR&gt; **NOTICE:** Depending on choosing an authentication method (&#x60;&#x60;&#x60;grant type&#x60;&#x60;&#x60;) some scopes could be narrowed by the server. e.g. when &#x60;&#x60;&#x60;grant_type &#x3D; client_credentials&#x60;&#x60;&#x60; and &#x60;&#x60;&#x60;scope &#x3D; wallet:read_write&#x60;&#x60;&#x60; it&#39;s modified by the server as &#x60;&#x60;&#x60;scope &#x3D; wallet:read&#x60;&#x60;&#x60; | [optional]

### Return type

[**kotlin.Any**](kotlin.Any.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="publicGetAnnouncementsGet"></a>
# **publicGetAnnouncementsGet**
> kotlin.Any publicGetAnnouncementsGet()

Retrieves announcements from the last 30 days.

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = PublicApi()
try {
    val result : kotlin.Any = apiInstance.publicGetAnnouncementsGet()
    println(result)
} catch (e: ClientException) {
    println("4xx response calling PublicApi#publicGetAnnouncementsGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling PublicApi#publicGetAnnouncementsGet")
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

<a name="publicGetBookSummaryByCurrencyGet"></a>
# **publicGetBookSummaryByCurrencyGet**
> kotlin.Any publicGetBookSummaryByCurrencyGet(currency, kind)

Retrieves the summary information such as open interest, 24h volume, etc. for all instruments for the currency (optionally filtered by kind).

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = PublicApi()
val currency : kotlin.String = currency_example // kotlin.String | The currency symbol
val kind : kotlin.String = kind_example // kotlin.String | Instrument kind, if not provided instruments of all kinds are considered
try {
    val result : kotlin.Any = apiInstance.publicGetBookSummaryByCurrencyGet(currency, kind)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling PublicApi#publicGetBookSummaryByCurrencyGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling PublicApi#publicGetBookSummaryByCurrencyGet")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **kotlin.String**| The currency symbol | [enum: BTC, ETH]
 **kind** | **kotlin.String**| Instrument kind, if not provided instruments of all kinds are considered | [optional] [enum: future, option]

### Return type

[**kotlin.Any**](kotlin.Any.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="publicGetBookSummaryByInstrumentGet"></a>
# **publicGetBookSummaryByInstrumentGet**
> kotlin.Any publicGetBookSummaryByInstrumentGet(instrumentName)

Retrieves the summary information such as open interest, 24h volume, etc. for a specific instrument.

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = PublicApi()
val instrumentName : kotlin.String = BTC-PERPETUAL // kotlin.String | Instrument name
try {
    val result : kotlin.Any = apiInstance.publicGetBookSummaryByInstrumentGet(instrumentName)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling PublicApi#publicGetBookSummaryByInstrumentGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling PublicApi#publicGetBookSummaryByInstrumentGet")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrumentName** | **kotlin.String**| Instrument name |

### Return type

[**kotlin.Any**](kotlin.Any.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="publicGetContractSizeGet"></a>
# **publicGetContractSizeGet**
> kotlin.Any publicGetContractSizeGet(instrumentName)

Retrieves contract size of provided instrument.

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = PublicApi()
val instrumentName : kotlin.String = BTC-PERPETUAL // kotlin.String | Instrument name
try {
    val result : kotlin.Any = apiInstance.publicGetContractSizeGet(instrumentName)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling PublicApi#publicGetContractSizeGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling PublicApi#publicGetContractSizeGet")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrumentName** | **kotlin.String**| Instrument name |

### Return type

[**kotlin.Any**](kotlin.Any.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="publicGetCurrenciesGet"></a>
# **publicGetCurrenciesGet**
> kotlin.Any publicGetCurrenciesGet()

Retrieves all cryptocurrencies supported by the API.

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = PublicApi()
try {
    val result : kotlin.Any = apiInstance.publicGetCurrenciesGet()
    println(result)
} catch (e: ClientException) {
    println("4xx response calling PublicApi#publicGetCurrenciesGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling PublicApi#publicGetCurrenciesGet")
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

<a name="publicGetFundingChartDataGet"></a>
# **publicGetFundingChartDataGet**
> kotlin.Any publicGetFundingChartDataGet(instrumentName, length)

Retrieve the latest user trades that have occurred for PERPETUAL instruments in a specific currency symbol and within given time range.

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = PublicApi()
val instrumentName : kotlin.String = BTC-PERPETUAL // kotlin.String | Instrument name
val length : kotlin.String = length_example // kotlin.String | Specifies time period. `8h` - 8 hours, `24h` - 24 hours
try {
    val result : kotlin.Any = apiInstance.publicGetFundingChartDataGet(instrumentName, length)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling PublicApi#publicGetFundingChartDataGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling PublicApi#publicGetFundingChartDataGet")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrumentName** | **kotlin.String**| Instrument name |
 **length** | **kotlin.String**| Specifies time period. &#x60;8h&#x60; - 8 hours, &#x60;24h&#x60; - 24 hours | [optional] [enum: 8h, 24h]

### Return type

[**kotlin.Any**](kotlin.Any.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="publicGetHistoricalVolatilityGet"></a>
# **publicGetHistoricalVolatilityGet**
> kotlin.Any publicGetHistoricalVolatilityGet(currency)

Provides information about historical volatility for given cryptocurrency.

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = PublicApi()
val currency : kotlin.String = currency_example // kotlin.String | The currency symbol
try {
    val result : kotlin.Any = apiInstance.publicGetHistoricalVolatilityGet(currency)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling PublicApi#publicGetHistoricalVolatilityGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling PublicApi#publicGetHistoricalVolatilityGet")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **kotlin.String**| The currency symbol | [enum: BTC, ETH]

### Return type

[**kotlin.Any**](kotlin.Any.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="publicGetIndexGet"></a>
# **publicGetIndexGet**
> kotlin.Any publicGetIndexGet(currency)

Retrieves the current index price for the instruments, for the selected currency.

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = PublicApi()
val currency : kotlin.String = currency_example // kotlin.String | The currency symbol
try {
    val result : kotlin.Any = apiInstance.publicGetIndexGet(currency)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling PublicApi#publicGetIndexGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling PublicApi#publicGetIndexGet")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **kotlin.String**| The currency symbol | [enum: BTC, ETH]

### Return type

[**kotlin.Any**](kotlin.Any.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="publicGetInstrumentsGet"></a>
# **publicGetInstrumentsGet**
> kotlin.Any publicGetInstrumentsGet(currency, kind, expired)

Retrieves available trading instruments. This method can be used to see which instruments are available for trading, or which instruments have existed historically.

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = PublicApi()
val currency : kotlin.String = currency_example // kotlin.String | The currency symbol
val kind : kotlin.String = kind_example // kotlin.String | Instrument kind, if not provided instruments of all kinds are considered
val expired : kotlin.Boolean = true // kotlin.Boolean | Set to true to show expired instruments instead of active ones.
try {
    val result : kotlin.Any = apiInstance.publicGetInstrumentsGet(currency, kind, expired)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling PublicApi#publicGetInstrumentsGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling PublicApi#publicGetInstrumentsGet")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **kotlin.String**| The currency symbol | [enum: BTC, ETH]
 **kind** | **kotlin.String**| Instrument kind, if not provided instruments of all kinds are considered | [optional] [enum: future, option]
 **expired** | **kotlin.Boolean**| Set to true to show expired instruments instead of active ones. | [optional] [default to false]

### Return type

[**kotlin.Any**](kotlin.Any.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="publicGetLastSettlementsByCurrencyGet"></a>
# **publicGetLastSettlementsByCurrencyGet**
> kotlin.Any publicGetLastSettlementsByCurrencyGet(currency, type, count, continuation, searchStartTimestamp)

Retrieves historical settlement, delivery and bankruptcy events coming from all instruments within given currency.

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = PublicApi()
val currency : kotlin.String = currency_example // kotlin.String | The currency symbol
val type : kotlin.String = type_example // kotlin.String | Settlement type
val count : kotlin.Int = 56 // kotlin.Int | Number of requested items, default - `20`
val continuation : kotlin.String = xY7T6cutS3t2B9YtaDkE6TS379oKnkzTvmEDUnEUP2Msa9xKWNNaT // kotlin.String | Continuation token for pagination
val searchStartTimestamp : kotlin.Int = 1536569522277 // kotlin.Int | The latest timestamp to return result for
try {
    val result : kotlin.Any = apiInstance.publicGetLastSettlementsByCurrencyGet(currency, type, count, continuation, searchStartTimestamp)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling PublicApi#publicGetLastSettlementsByCurrencyGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling PublicApi#publicGetLastSettlementsByCurrencyGet")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **kotlin.String**| The currency symbol | [enum: BTC, ETH]
 **type** | **kotlin.String**| Settlement type | [optional] [enum: settlement, delivery, bankruptcy]
 **count** | **kotlin.Int**| Number of requested items, default - &#x60;20&#x60; | [optional]
 **continuation** | **kotlin.String**| Continuation token for pagination | [optional]
 **searchStartTimestamp** | **kotlin.Int**| The latest timestamp to return result for | [optional]

### Return type

[**kotlin.Any**](kotlin.Any.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="publicGetLastSettlementsByInstrumentGet"></a>
# **publicGetLastSettlementsByInstrumentGet**
> kotlin.Any publicGetLastSettlementsByInstrumentGet(instrumentName, type, count, continuation, searchStartTimestamp)

Retrieves historical public settlement, delivery and bankruptcy events filtered by instrument name.

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = PublicApi()
val instrumentName : kotlin.String = BTC-PERPETUAL // kotlin.String | Instrument name
val type : kotlin.String = type_example // kotlin.String | Settlement type
val count : kotlin.Int = 56 // kotlin.Int | Number of requested items, default - `20`
val continuation : kotlin.String = xY7T6cutS3t2B9YtaDkE6TS379oKnkzTvmEDUnEUP2Msa9xKWNNaT // kotlin.String | Continuation token for pagination
val searchStartTimestamp : kotlin.Int = 1536569522277 // kotlin.Int | The latest timestamp to return result for
try {
    val result : kotlin.Any = apiInstance.publicGetLastSettlementsByInstrumentGet(instrumentName, type, count, continuation, searchStartTimestamp)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling PublicApi#publicGetLastSettlementsByInstrumentGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling PublicApi#publicGetLastSettlementsByInstrumentGet")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrumentName** | **kotlin.String**| Instrument name |
 **type** | **kotlin.String**| Settlement type | [optional] [enum: settlement, delivery, bankruptcy]
 **count** | **kotlin.Int**| Number of requested items, default - &#x60;20&#x60; | [optional]
 **continuation** | **kotlin.String**| Continuation token for pagination | [optional]
 **searchStartTimestamp** | **kotlin.Int**| The latest timestamp to return result for | [optional]

### Return type

[**kotlin.Any**](kotlin.Any.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="publicGetLastTradesByCurrencyAndTimeGet"></a>
# **publicGetLastTradesByCurrencyAndTimeGet**
> kotlin.Any publicGetLastTradesByCurrencyAndTimeGet(currency, startTimestamp, endTimestamp, kind, count, includeOld, sorting)

Retrieve the latest trades that have occurred for instruments in a specific currency symbol and within given time range.

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = PublicApi()
val currency : kotlin.String = currency_example // kotlin.String | The currency symbol
val startTimestamp : kotlin.Int = 1536569522277 // kotlin.Int | The earliest timestamp to return result for
val endTimestamp : kotlin.Int = 1536569522277 // kotlin.Int | The most recent timestamp to return result for
val kind : kotlin.String = kind_example // kotlin.String | Instrument kind, if not provided instruments of all kinds are considered
val count : kotlin.Int = 56 // kotlin.Int | Number of requested items, default - `10`
val includeOld : kotlin.Boolean = true // kotlin.Boolean | Include trades older than 7 days, default - `false`
val sorting : kotlin.String = sorting_example // kotlin.String | Direction of results sorting (`default` value means no sorting, results will be returned in order in which they left the database)
try {
    val result : kotlin.Any = apiInstance.publicGetLastTradesByCurrencyAndTimeGet(currency, startTimestamp, endTimestamp, kind, count, includeOld, sorting)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling PublicApi#publicGetLastTradesByCurrencyAndTimeGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling PublicApi#publicGetLastTradesByCurrencyAndTimeGet")
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

<a name="publicGetLastTradesByCurrencyGet"></a>
# **publicGetLastTradesByCurrencyGet**
> kotlin.Any publicGetLastTradesByCurrencyGet(currency, kind, startId, endId, count, includeOld, sorting)

Retrieve the latest trades that have occurred for instruments in a specific currency symbol.

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = PublicApi()
val currency : kotlin.String = currency_example // kotlin.String | The currency symbol
val kind : kotlin.String = kind_example // kotlin.String | Instrument kind, if not provided instruments of all kinds are considered
val startId : kotlin.String = startId_example // kotlin.String | The ID number of the first trade to be returned
val endId : kotlin.String = endId_example // kotlin.String | The ID number of the last trade to be returned
val count : kotlin.Int = 56 // kotlin.Int | Number of requested items, default - `10`
val includeOld : kotlin.Boolean = true // kotlin.Boolean | Include trades older than 7 days, default - `false`
val sorting : kotlin.String = sorting_example // kotlin.String | Direction of results sorting (`default` value means no sorting, results will be returned in order in which they left the database)
try {
    val result : kotlin.Any = apiInstance.publicGetLastTradesByCurrencyGet(currency, kind, startId, endId, count, includeOld, sorting)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling PublicApi#publicGetLastTradesByCurrencyGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling PublicApi#publicGetLastTradesByCurrencyGet")
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

<a name="publicGetLastTradesByInstrumentAndTimeGet"></a>
# **publicGetLastTradesByInstrumentAndTimeGet**
> kotlin.Any publicGetLastTradesByInstrumentAndTimeGet(instrumentName, startTimestamp, endTimestamp, count, includeOld, sorting)

Retrieve the latest trades that have occurred for a specific instrument and within given time range.

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = PublicApi()
val instrumentName : kotlin.String = BTC-PERPETUAL // kotlin.String | Instrument name
val startTimestamp : kotlin.Int = 1536569522277 // kotlin.Int | The earliest timestamp to return result for
val endTimestamp : kotlin.Int = 1536569522277 // kotlin.Int | The most recent timestamp to return result for
val count : kotlin.Int = 56 // kotlin.Int | Number of requested items, default - `10`
val includeOld : kotlin.Boolean = true // kotlin.Boolean | Include trades older than 7 days, default - `false`
val sorting : kotlin.String = sorting_example // kotlin.String | Direction of results sorting (`default` value means no sorting, results will be returned in order in which they left the database)
try {
    val result : kotlin.Any = apiInstance.publicGetLastTradesByInstrumentAndTimeGet(instrumentName, startTimestamp, endTimestamp, count, includeOld, sorting)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling PublicApi#publicGetLastTradesByInstrumentAndTimeGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling PublicApi#publicGetLastTradesByInstrumentAndTimeGet")
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

<a name="publicGetLastTradesByInstrumentGet"></a>
# **publicGetLastTradesByInstrumentGet**
> kotlin.Any publicGetLastTradesByInstrumentGet(instrumentName, startSeq, endSeq, count, includeOld, sorting)

Retrieve the latest trades that have occurred for a specific instrument.

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = PublicApi()
val instrumentName : kotlin.String = BTC-PERPETUAL // kotlin.String | Instrument name
val startSeq : kotlin.Int = 56 // kotlin.Int | The sequence number of the first trade to be returned
val endSeq : kotlin.Int = 56 // kotlin.Int | The sequence number of the last trade to be returned
val count : kotlin.Int = 56 // kotlin.Int | Number of requested items, default - `10`
val includeOld : kotlin.Boolean = true // kotlin.Boolean | Include trades older than 7 days, default - `false`
val sorting : kotlin.String = sorting_example // kotlin.String | Direction of results sorting (`default` value means no sorting, results will be returned in order in which they left the database)
try {
    val result : kotlin.Any = apiInstance.publicGetLastTradesByInstrumentGet(instrumentName, startSeq, endSeq, count, includeOld, sorting)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling PublicApi#publicGetLastTradesByInstrumentGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling PublicApi#publicGetLastTradesByInstrumentGet")
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

<a name="publicGetOrderBookGet"></a>
# **publicGetOrderBookGet**
> kotlin.Any publicGetOrderBookGet(instrumentName, depth)

Retrieves the order book, along with other market values for a given instrument.

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = PublicApi()
val instrumentName : kotlin.String = instrumentName_example // kotlin.String | The instrument name for which to retrieve the order book, see [`getinstruments`](#getinstruments) to obtain instrument names.
val depth : java.math.BigDecimal = 8.14 // java.math.BigDecimal | The number of entries to return for bids and asks.
try {
    val result : kotlin.Any = apiInstance.publicGetOrderBookGet(instrumentName, depth)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling PublicApi#publicGetOrderBookGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling PublicApi#publicGetOrderBookGet")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrumentName** | **kotlin.String**| The instrument name for which to retrieve the order book, see [&#x60;getinstruments&#x60;](#getinstruments) to obtain instrument names. |
 **depth** | **java.math.BigDecimal**| The number of entries to return for bids and asks. | [optional]

### Return type

[**kotlin.Any**](kotlin.Any.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="publicGetTimeGet"></a>
# **publicGetTimeGet**
> kotlin.Any publicGetTimeGet()

Retrieves the current time (in milliseconds). This API endpoint can be used to check the clock skew between your software and Deribit&#39;s systems.

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = PublicApi()
try {
    val result : kotlin.Any = apiInstance.publicGetTimeGet()
    println(result)
} catch (e: ClientException) {
    println("4xx response calling PublicApi#publicGetTimeGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling PublicApi#publicGetTimeGet")
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

<a name="publicGetTradeVolumesGet"></a>
# **publicGetTradeVolumesGet**
> kotlin.Any publicGetTradeVolumesGet()

Retrieves aggregated 24h trade volumes for different instrument types and currencies.

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = PublicApi()
try {
    val result : kotlin.Any = apiInstance.publicGetTradeVolumesGet()
    println(result)
} catch (e: ClientException) {
    println("4xx response calling PublicApi#publicGetTradeVolumesGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling PublicApi#publicGetTradeVolumesGet")
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

<a name="publicGetTradingviewChartDataGet"></a>
# **publicGetTradingviewChartDataGet**
> kotlin.Any publicGetTradingviewChartDataGet(instrumentName, startTimestamp, endTimestamp)

Publicly available market data used to generate a TradingView candle chart.

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = PublicApi()
val instrumentName : kotlin.String = BTC-PERPETUAL // kotlin.String | Instrument name
val startTimestamp : kotlin.Int = 1536569522277 // kotlin.Int | The earliest timestamp to return result for
val endTimestamp : kotlin.Int = 1536569522277 // kotlin.Int | The most recent timestamp to return result for
try {
    val result : kotlin.Any = apiInstance.publicGetTradingviewChartDataGet(instrumentName, startTimestamp, endTimestamp)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling PublicApi#publicGetTradingviewChartDataGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling PublicApi#publicGetTradingviewChartDataGet")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrumentName** | **kotlin.String**| Instrument name |
 **startTimestamp** | **kotlin.Int**| The earliest timestamp to return result for |
 **endTimestamp** | **kotlin.Int**| The most recent timestamp to return result for |

### Return type

[**kotlin.Any**](kotlin.Any.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="publicTestGet"></a>
# **publicTestGet**
> kotlin.Any publicTestGet(expectedResult)

Tests the connection to the API server, and returns its version. You can use this to make sure the API is reachable, and matches the expected version.

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = PublicApi()
val expectedResult : kotlin.String = expectedResult_example // kotlin.String | The value \"exception\" will trigger an error response. This may be useful for testing wrapper libraries.
try {
    val result : kotlin.Any = apiInstance.publicTestGet(expectedResult)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling PublicApi#publicTestGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling PublicApi#publicTestGet")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **expectedResult** | **kotlin.String**| The value \&quot;exception\&quot; will trigger an error response. This may be useful for testing wrapper libraries. | [optional] [enum: exception]

### Return type

[**kotlin.Any**](kotlin.Any.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="publicTickerGet"></a>
# **publicTickerGet**
> kotlin.Any publicTickerGet(instrumentName)

Get ticker for an instrument.

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = PublicApi()
val instrumentName : kotlin.String = BTC-PERPETUAL // kotlin.String | Instrument name
try {
    val result : kotlin.Any = apiInstance.publicTickerGet(instrumentName)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling PublicApi#publicTickerGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling PublicApi#publicTickerGet")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrumentName** | **kotlin.String**| Instrument name |

### Return type

[**kotlin.Any**](kotlin.Any.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="publicValidateFieldGet"></a>
# **publicValidateFieldGet**
> kotlin.Any publicValidateFieldGet(field, value, value2)

Method used to introduce the client software connected to Deribit platform over websocket. Provided data may have an impact on the maintained connection and will be collected for internal statistical purposes. In response, Deribit will also introduce itself.

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = PublicApi()
val field : kotlin.String = field_example // kotlin.String | Name of the field to be validated, examples: postal_code, date_of_birth
val value : kotlin.String = value_example // kotlin.String | Value to be checked
val value2 : kotlin.String = value2_example // kotlin.String | Additional value to be compared with
try {
    val result : kotlin.Any = apiInstance.publicValidateFieldGet(field, value, value2)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling PublicApi#publicValidateFieldGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling PublicApi#publicValidateFieldGet")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **field** | **kotlin.String**| Name of the field to be validated, examples: postal_code, date_of_birth |
 **value** | **kotlin.String**| Value to be checked |
 **value2** | **kotlin.String**| Additional value to be compared with | [optional]

### Return type

[**kotlin.Any**](kotlin.Any.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

