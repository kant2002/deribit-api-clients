# MarketDataApi

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


<a name="publicGetBookSummaryByCurrencyGet"></a>
# **publicGetBookSummaryByCurrencyGet**
> kotlin.Any publicGetBookSummaryByCurrencyGet(currency, kind)

Retrieves the summary information such as open interest, 24h volume, etc. for all instruments for the currency (optionally filtered by kind).

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = MarketDataApi()
val currency : kotlin.String = currency_example // kotlin.String | The currency symbol
val kind : kotlin.String = kind_example // kotlin.String | Instrument kind, if not provided instruments of all kinds are considered
try {
    val result : kotlin.Any = apiInstance.publicGetBookSummaryByCurrencyGet(currency, kind)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling MarketDataApi#publicGetBookSummaryByCurrencyGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling MarketDataApi#publicGetBookSummaryByCurrencyGet")
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

val apiInstance = MarketDataApi()
val instrumentName : kotlin.String = BTC-PERPETUAL // kotlin.String | Instrument name
try {
    val result : kotlin.Any = apiInstance.publicGetBookSummaryByInstrumentGet(instrumentName)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling MarketDataApi#publicGetBookSummaryByInstrumentGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling MarketDataApi#publicGetBookSummaryByInstrumentGet")
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

val apiInstance = MarketDataApi()
val instrumentName : kotlin.String = BTC-PERPETUAL // kotlin.String | Instrument name
try {
    val result : kotlin.Any = apiInstance.publicGetContractSizeGet(instrumentName)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling MarketDataApi#publicGetContractSizeGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling MarketDataApi#publicGetContractSizeGet")
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

val apiInstance = MarketDataApi()
try {
    val result : kotlin.Any = apiInstance.publicGetCurrenciesGet()
    println(result)
} catch (e: ClientException) {
    println("4xx response calling MarketDataApi#publicGetCurrenciesGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling MarketDataApi#publicGetCurrenciesGet")
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

val apiInstance = MarketDataApi()
val instrumentName : kotlin.String = BTC-PERPETUAL // kotlin.String | Instrument name
val length : kotlin.String = length_example // kotlin.String | Specifies time period. `8h` - 8 hours, `24h` - 24 hours
try {
    val result : kotlin.Any = apiInstance.publicGetFundingChartDataGet(instrumentName, length)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling MarketDataApi#publicGetFundingChartDataGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling MarketDataApi#publicGetFundingChartDataGet")
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

val apiInstance = MarketDataApi()
val currency : kotlin.String = currency_example // kotlin.String | The currency symbol
try {
    val result : kotlin.Any = apiInstance.publicGetHistoricalVolatilityGet(currency)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling MarketDataApi#publicGetHistoricalVolatilityGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling MarketDataApi#publicGetHistoricalVolatilityGet")
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

val apiInstance = MarketDataApi()
val currency : kotlin.String = currency_example // kotlin.String | The currency symbol
try {
    val result : kotlin.Any = apiInstance.publicGetIndexGet(currency)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling MarketDataApi#publicGetIndexGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling MarketDataApi#publicGetIndexGet")
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

val apiInstance = MarketDataApi()
val currency : kotlin.String = currency_example // kotlin.String | The currency symbol
val kind : kotlin.String = kind_example // kotlin.String | Instrument kind, if not provided instruments of all kinds are considered
val expired : kotlin.Boolean = true // kotlin.Boolean | Set to true to show expired instruments instead of active ones.
try {
    val result : kotlin.Any = apiInstance.publicGetInstrumentsGet(currency, kind, expired)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling MarketDataApi#publicGetInstrumentsGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling MarketDataApi#publicGetInstrumentsGet")
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

val apiInstance = MarketDataApi()
val currency : kotlin.String = currency_example // kotlin.String | The currency symbol
val type : kotlin.String = type_example // kotlin.String | Settlement type
val count : kotlin.Int = 56 // kotlin.Int | Number of requested items, default - `20`
val continuation : kotlin.String = xY7T6cutS3t2B9YtaDkE6TS379oKnkzTvmEDUnEUP2Msa9xKWNNaT // kotlin.String | Continuation token for pagination
val searchStartTimestamp : kotlin.Int = 1536569522277 // kotlin.Int | The latest timestamp to return result for
try {
    val result : kotlin.Any = apiInstance.publicGetLastSettlementsByCurrencyGet(currency, type, count, continuation, searchStartTimestamp)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling MarketDataApi#publicGetLastSettlementsByCurrencyGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling MarketDataApi#publicGetLastSettlementsByCurrencyGet")
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

val apiInstance = MarketDataApi()
val instrumentName : kotlin.String = BTC-PERPETUAL // kotlin.String | Instrument name
val type : kotlin.String = type_example // kotlin.String | Settlement type
val count : kotlin.Int = 56 // kotlin.Int | Number of requested items, default - `20`
val continuation : kotlin.String = xY7T6cutS3t2B9YtaDkE6TS379oKnkzTvmEDUnEUP2Msa9xKWNNaT // kotlin.String | Continuation token for pagination
val searchStartTimestamp : kotlin.Int = 1536569522277 // kotlin.Int | The latest timestamp to return result for
try {
    val result : kotlin.Any = apiInstance.publicGetLastSettlementsByInstrumentGet(instrumentName, type, count, continuation, searchStartTimestamp)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling MarketDataApi#publicGetLastSettlementsByInstrumentGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling MarketDataApi#publicGetLastSettlementsByInstrumentGet")
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

val apiInstance = MarketDataApi()
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
    println("4xx response calling MarketDataApi#publicGetLastTradesByCurrencyAndTimeGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling MarketDataApi#publicGetLastTradesByCurrencyAndTimeGet")
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

val apiInstance = MarketDataApi()
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
    println("4xx response calling MarketDataApi#publicGetLastTradesByCurrencyGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling MarketDataApi#publicGetLastTradesByCurrencyGet")
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

val apiInstance = MarketDataApi()
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
    println("4xx response calling MarketDataApi#publicGetLastTradesByInstrumentAndTimeGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling MarketDataApi#publicGetLastTradesByInstrumentAndTimeGet")
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

val apiInstance = MarketDataApi()
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
    println("4xx response calling MarketDataApi#publicGetLastTradesByInstrumentGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling MarketDataApi#publicGetLastTradesByInstrumentGet")
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

val apiInstance = MarketDataApi()
val instrumentName : kotlin.String = instrumentName_example // kotlin.String | The instrument name for which to retrieve the order book, see [`getinstruments`](#getinstruments) to obtain instrument names.
val depth : java.math.BigDecimal = 8.14 // java.math.BigDecimal | The number of entries to return for bids and asks.
try {
    val result : kotlin.Any = apiInstance.publicGetOrderBookGet(instrumentName, depth)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling MarketDataApi#publicGetOrderBookGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling MarketDataApi#publicGetOrderBookGet")
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

<a name="publicGetTradeVolumesGet"></a>
# **publicGetTradeVolumesGet**
> kotlin.Any publicGetTradeVolumesGet()

Retrieves aggregated 24h trade volumes for different instrument types and currencies.

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = MarketDataApi()
try {
    val result : kotlin.Any = apiInstance.publicGetTradeVolumesGet()
    println(result)
} catch (e: ClientException) {
    println("4xx response calling MarketDataApi#publicGetTradeVolumesGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling MarketDataApi#publicGetTradeVolumesGet")
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

val apiInstance = MarketDataApi()
val instrumentName : kotlin.String = BTC-PERPETUAL // kotlin.String | Instrument name
val startTimestamp : kotlin.Int = 1536569522277 // kotlin.Int | The earliest timestamp to return result for
val endTimestamp : kotlin.Int = 1536569522277 // kotlin.Int | The most recent timestamp to return result for
try {
    val result : kotlin.Any = apiInstance.publicGetTradingviewChartDataGet(instrumentName, startTimestamp, endTimestamp)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling MarketDataApi#publicGetTradingviewChartDataGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling MarketDataApi#publicGetTradingviewChartDataGet")
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

<a name="publicTickerGet"></a>
# **publicTickerGet**
> kotlin.Any publicTickerGet(instrumentName)

Get ticker for an instrument.

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = MarketDataApi()
val instrumentName : kotlin.String = BTC-PERPETUAL // kotlin.String | Instrument name
try {
    val result : kotlin.Any = apiInstance.publicTickerGet(instrumentName)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling MarketDataApi#publicTickerGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling MarketDataApi#publicTickerGet")
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

