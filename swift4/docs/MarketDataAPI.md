# MarketDataAPI

All URIs are relative to *https://www.deribit.com/api/v2*

Method | HTTP request | Description
------------- | ------------- | -------------
[**publicGetBookSummaryByCurrencyGet**](MarketDataAPI.md#publicgetbooksummarybycurrencyget) | **GET** /public/get_book_summary_by_currency | Retrieves the summary information such as open interest, 24h volume, etc. for all instruments for the currency (optionally filtered by kind).
[**publicGetBookSummaryByInstrumentGet**](MarketDataAPI.md#publicgetbooksummarybyinstrumentget) | **GET** /public/get_book_summary_by_instrument | Retrieves the summary information such as open interest, 24h volume, etc. for a specific instrument.
[**publicGetContractSizeGet**](MarketDataAPI.md#publicgetcontractsizeget) | **GET** /public/get_contract_size | Retrieves contract size of provided instrument.
[**publicGetCurrenciesGet**](MarketDataAPI.md#publicgetcurrenciesget) | **GET** /public/get_currencies | Retrieves all cryptocurrencies supported by the API.
[**publicGetFundingChartDataGet**](MarketDataAPI.md#publicgetfundingchartdataget) | **GET** /public/get_funding_chart_data | Retrieve the latest user trades that have occurred for PERPETUAL instruments in a specific currency symbol and within given time range.
[**publicGetHistoricalVolatilityGet**](MarketDataAPI.md#publicgethistoricalvolatilityget) | **GET** /public/get_historical_volatility | Provides information about historical volatility for given cryptocurrency.
[**publicGetIndexGet**](MarketDataAPI.md#publicgetindexget) | **GET** /public/get_index | Retrieves the current index price for the instruments, for the selected currency.
[**publicGetInstrumentsGet**](MarketDataAPI.md#publicgetinstrumentsget) | **GET** /public/get_instruments | Retrieves available trading instruments. This method can be used to see which instruments are available for trading, or which instruments have existed historically.
[**publicGetLastSettlementsByCurrencyGet**](MarketDataAPI.md#publicgetlastsettlementsbycurrencyget) | **GET** /public/get_last_settlements_by_currency | Retrieves historical settlement, delivery and bankruptcy events coming from all instruments within given currency.
[**publicGetLastSettlementsByInstrumentGet**](MarketDataAPI.md#publicgetlastsettlementsbyinstrumentget) | **GET** /public/get_last_settlements_by_instrument | Retrieves historical public settlement, delivery and bankruptcy events filtered by instrument name.
[**publicGetLastTradesByCurrencyAndTimeGet**](MarketDataAPI.md#publicgetlasttradesbycurrencyandtimeget) | **GET** /public/get_last_trades_by_currency_and_time | Retrieve the latest trades that have occurred for instruments in a specific currency symbol and within given time range.
[**publicGetLastTradesByCurrencyGet**](MarketDataAPI.md#publicgetlasttradesbycurrencyget) | **GET** /public/get_last_trades_by_currency | Retrieve the latest trades that have occurred for instruments in a specific currency symbol.
[**publicGetLastTradesByInstrumentAndTimeGet**](MarketDataAPI.md#publicgetlasttradesbyinstrumentandtimeget) | **GET** /public/get_last_trades_by_instrument_and_time | Retrieve the latest trades that have occurred for a specific instrument and within given time range.
[**publicGetLastTradesByInstrumentGet**](MarketDataAPI.md#publicgetlasttradesbyinstrumentget) | **GET** /public/get_last_trades_by_instrument | Retrieve the latest trades that have occurred for a specific instrument.
[**publicGetOrderBookGet**](MarketDataAPI.md#publicgetorderbookget) | **GET** /public/get_order_book | Retrieves the order book, along with other market values for a given instrument.
[**publicGetTradeVolumesGet**](MarketDataAPI.md#publicgettradevolumesget) | **GET** /public/get_trade_volumes | Retrieves aggregated 24h trade volumes for different instrument types and currencies.
[**publicGetTradingviewChartDataGet**](MarketDataAPI.md#publicgettradingviewchartdataget) | **GET** /public/get_tradingview_chart_data | Publicly available market data used to generate a TradingView candle chart.
[**publicTickerGet**](MarketDataAPI.md#publictickerget) | **GET** /public/ticker | Get ticker for an instrument.


# **publicGetBookSummaryByCurrencyGet**
```swift
    open class func publicGetBookSummaryByCurrencyGet(currency: Currency_publicGetBookSummaryByCurrencyGet, kind: Kind_publicGetBookSummaryByCurrencyGet? = nil, completion: @escaping (_ data: Any?, _ error: Error?) -> Void)
```

Retrieves the summary information such as open interest, 24h volume, etc. for all instruments for the currency (optionally filtered by kind).

### Example 
```swift
// The following code samples are still beta. For any issue, please report via http://github.com/OpenAPITools/openapi-generator/issues/new
import OpenAPIClient

let currency = "currency_example" // String | The currency symbol
let kind = "kind_example" // String | Instrument kind, if not provided instruments of all kinds are considered (optional)

// Retrieves the summary information such as open interest, 24h volume, etc. for all instruments for the currency (optionally filtered by kind).
MarketDataAPI.publicGetBookSummaryByCurrencyGet(currency: currency, kind: kind) { (response, error) in
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

### Return type

**Any**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **publicGetBookSummaryByInstrumentGet**
```swift
    open class func publicGetBookSummaryByInstrumentGet(instrumentName: String, completion: @escaping (_ data: Any?, _ error: Error?) -> Void)
```

Retrieves the summary information such as open interest, 24h volume, etc. for a specific instrument.

### Example 
```swift
// The following code samples are still beta. For any issue, please report via http://github.com/OpenAPITools/openapi-generator/issues/new
import OpenAPIClient

let instrumentName = "instrumentName_example" // String | Instrument name

// Retrieves the summary information such as open interest, 24h volume, etc. for a specific instrument.
MarketDataAPI.publicGetBookSummaryByInstrumentGet(instrumentName: instrumentName) { (response, error) in
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

### Return type

**Any**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **publicGetContractSizeGet**
```swift
    open class func publicGetContractSizeGet(instrumentName: String, completion: @escaping (_ data: Any?, _ error: Error?) -> Void)
```

Retrieves contract size of provided instrument.

### Example 
```swift
// The following code samples are still beta. For any issue, please report via http://github.com/OpenAPITools/openapi-generator/issues/new
import OpenAPIClient

let instrumentName = "instrumentName_example" // String | Instrument name

// Retrieves contract size of provided instrument.
MarketDataAPI.publicGetContractSizeGet(instrumentName: instrumentName) { (response, error) in
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

### Return type

**Any**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **publicGetCurrenciesGet**
```swift
    open class func publicGetCurrenciesGet(completion: @escaping (_ data: Any?, _ error: Error?) -> Void)
```

Retrieves all cryptocurrencies supported by the API.

### Example 
```swift
// The following code samples are still beta. For any issue, please report via http://github.com/OpenAPITools/openapi-generator/issues/new
import OpenAPIClient


// Retrieves all cryptocurrencies supported by the API.
MarketDataAPI.publicGetCurrenciesGet() { (response, error) in
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

# **publicGetFundingChartDataGet**
```swift
    open class func publicGetFundingChartDataGet(instrumentName: String, length: Length_publicGetFundingChartDataGet? = nil, completion: @escaping (_ data: Any?, _ error: Error?) -> Void)
```

Retrieve the latest user trades that have occurred for PERPETUAL instruments in a specific currency symbol and within given time range.

### Example 
```swift
// The following code samples are still beta. For any issue, please report via http://github.com/OpenAPITools/openapi-generator/issues/new
import OpenAPIClient

let instrumentName = "instrumentName_example" // String | Instrument name
let length = "length_example" // String | Specifies time period. `8h` - 8 hours, `24h` - 24 hours (optional)

// Retrieve the latest user trades that have occurred for PERPETUAL instruments in a specific currency symbol and within given time range.
MarketDataAPI.publicGetFundingChartDataGet(instrumentName: instrumentName, length: length) { (response, error) in
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
 **length** | **String** | Specifies time period. &#x60;8h&#x60; - 8 hours, &#x60;24h&#x60; - 24 hours | [optional] 

### Return type

**Any**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **publicGetHistoricalVolatilityGet**
```swift
    open class func publicGetHistoricalVolatilityGet(currency: Currency_publicGetHistoricalVolatilityGet, completion: @escaping (_ data: Any?, _ error: Error?) -> Void)
```

Provides information about historical volatility for given cryptocurrency.

### Example 
```swift
// The following code samples are still beta. For any issue, please report via http://github.com/OpenAPITools/openapi-generator/issues/new
import OpenAPIClient

let currency = "currency_example" // String | The currency symbol

// Provides information about historical volatility for given cryptocurrency.
MarketDataAPI.publicGetHistoricalVolatilityGet(currency: currency) { (response, error) in
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

### Return type

**Any**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **publicGetIndexGet**
```swift
    open class func publicGetIndexGet(currency: Currency_publicGetIndexGet, completion: @escaping (_ data: Any?, _ error: Error?) -> Void)
```

Retrieves the current index price for the instruments, for the selected currency.

### Example 
```swift
// The following code samples are still beta. For any issue, please report via http://github.com/OpenAPITools/openapi-generator/issues/new
import OpenAPIClient

let currency = "currency_example" // String | The currency symbol

// Retrieves the current index price for the instruments, for the selected currency.
MarketDataAPI.publicGetIndexGet(currency: currency) { (response, error) in
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

### Return type

**Any**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **publicGetInstrumentsGet**
```swift
    open class func publicGetInstrumentsGet(currency: Currency_publicGetInstrumentsGet, kind: Kind_publicGetInstrumentsGet? = nil, expired: Bool? = nil, completion: @escaping (_ data: Any?, _ error: Error?) -> Void)
```

Retrieves available trading instruments. This method can be used to see which instruments are available for trading, or which instruments have existed historically.

### Example 
```swift
// The following code samples are still beta. For any issue, please report via http://github.com/OpenAPITools/openapi-generator/issues/new
import OpenAPIClient

let currency = "currency_example" // String | The currency symbol
let kind = "kind_example" // String | Instrument kind, if not provided instruments of all kinds are considered (optional)
let expired = false // Bool | Set to true to show expired instruments instead of active ones. (optional) (default to false)

// Retrieves available trading instruments. This method can be used to see which instruments are available for trading, or which instruments have existed historically.
MarketDataAPI.publicGetInstrumentsGet(currency: currency, kind: kind, expired: expired) { (response, error) in
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
 **expired** | **Bool** | Set to true to show expired instruments instead of active ones. | [optional] [default to false]

### Return type

**Any**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **publicGetLastSettlementsByCurrencyGet**
```swift
    open class func publicGetLastSettlementsByCurrencyGet(currency: Currency_publicGetLastSettlementsByCurrencyGet, type: ModelType_publicGetLastSettlementsByCurrencyGet? = nil, count: Int? = nil, continuation: String? = nil, searchStartTimestamp: Int? = nil, completion: @escaping (_ data: Any?, _ error: Error?) -> Void)
```

Retrieves historical settlement, delivery and bankruptcy events coming from all instruments within given currency.

### Example 
```swift
// The following code samples are still beta. For any issue, please report via http://github.com/OpenAPITools/openapi-generator/issues/new
import OpenAPIClient

let currency = "currency_example" // String | The currency symbol
let type = "type_example" // String | Settlement type (optional)
let count = 987 // Int | Number of requested items, default - `20` (optional)
let continuation = "continuation_example" // String | Continuation token for pagination (optional)
let searchStartTimestamp = 987 // Int | The latest timestamp to return result for (optional)

// Retrieves historical settlement, delivery and bankruptcy events coming from all instruments within given currency.
MarketDataAPI.publicGetLastSettlementsByCurrencyGet(currency: currency, type: type, count: count, continuation: continuation, searchStartTimestamp: searchStartTimestamp) { (response, error) in
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
 **continuation** | **String** | Continuation token for pagination | [optional] 
 **searchStartTimestamp** | **Int** | The latest timestamp to return result for | [optional] 

### Return type

**Any**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **publicGetLastSettlementsByInstrumentGet**
```swift
    open class func publicGetLastSettlementsByInstrumentGet(instrumentName: String, type: ModelType_publicGetLastSettlementsByInstrumentGet? = nil, count: Int? = nil, continuation: String? = nil, searchStartTimestamp: Int? = nil, completion: @escaping (_ data: Any?, _ error: Error?) -> Void)
```

Retrieves historical public settlement, delivery and bankruptcy events filtered by instrument name.

### Example 
```swift
// The following code samples are still beta. For any issue, please report via http://github.com/OpenAPITools/openapi-generator/issues/new
import OpenAPIClient

let instrumentName = "instrumentName_example" // String | Instrument name
let type = "type_example" // String | Settlement type (optional)
let count = 987 // Int | Number of requested items, default - `20` (optional)
let continuation = "continuation_example" // String | Continuation token for pagination (optional)
let searchStartTimestamp = 987 // Int | The latest timestamp to return result for (optional)

// Retrieves historical public settlement, delivery and bankruptcy events filtered by instrument name.
MarketDataAPI.publicGetLastSettlementsByInstrumentGet(instrumentName: instrumentName, type: type, count: count, continuation: continuation, searchStartTimestamp: searchStartTimestamp) { (response, error) in
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
 **continuation** | **String** | Continuation token for pagination | [optional] 
 **searchStartTimestamp** | **Int** | The latest timestamp to return result for | [optional] 

### Return type

**Any**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **publicGetLastTradesByCurrencyAndTimeGet**
```swift
    open class func publicGetLastTradesByCurrencyAndTimeGet(currency: Currency_publicGetLastTradesByCurrencyAndTimeGet, startTimestamp: Int, endTimestamp: Int, kind: Kind_publicGetLastTradesByCurrencyAndTimeGet? = nil, count: Int? = nil, includeOld: Bool? = nil, sorting: Sorting_publicGetLastTradesByCurrencyAndTimeGet? = nil, completion: @escaping (_ data: Any?, _ error: Error?) -> Void)
```

Retrieve the latest trades that have occurred for instruments in a specific currency symbol and within given time range.

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

// Retrieve the latest trades that have occurred for instruments in a specific currency symbol and within given time range.
MarketDataAPI.publicGetLastTradesByCurrencyAndTimeGet(currency: currency, startTimestamp: startTimestamp, endTimestamp: endTimestamp, kind: kind, count: count, includeOld: includeOld, sorting: sorting) { (response, error) in
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

# **publicGetLastTradesByCurrencyGet**
```swift
    open class func publicGetLastTradesByCurrencyGet(currency: Currency_publicGetLastTradesByCurrencyGet, kind: Kind_publicGetLastTradesByCurrencyGet? = nil, startId: String? = nil, endId: String? = nil, count: Int? = nil, includeOld: Bool? = nil, sorting: Sorting_publicGetLastTradesByCurrencyGet? = nil, completion: @escaping (_ data: Any?, _ error: Error?) -> Void)
```

Retrieve the latest trades that have occurred for instruments in a specific currency symbol.

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

// Retrieve the latest trades that have occurred for instruments in a specific currency symbol.
MarketDataAPI.publicGetLastTradesByCurrencyGet(currency: currency, kind: kind, startId: startId, endId: endId, count: count, includeOld: includeOld, sorting: sorting) { (response, error) in
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

# **publicGetLastTradesByInstrumentAndTimeGet**
```swift
    open class func publicGetLastTradesByInstrumentAndTimeGet(instrumentName: String, startTimestamp: Int, endTimestamp: Int, count: Int? = nil, includeOld: Bool? = nil, sorting: Sorting_publicGetLastTradesByInstrumentAndTimeGet? = nil, completion: @escaping (_ data: Any?, _ error: Error?) -> Void)
```

Retrieve the latest trades that have occurred for a specific instrument and within given time range.

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

// Retrieve the latest trades that have occurred for a specific instrument and within given time range.
MarketDataAPI.publicGetLastTradesByInstrumentAndTimeGet(instrumentName: instrumentName, startTimestamp: startTimestamp, endTimestamp: endTimestamp, count: count, includeOld: includeOld, sorting: sorting) { (response, error) in
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

# **publicGetLastTradesByInstrumentGet**
```swift
    open class func publicGetLastTradesByInstrumentGet(instrumentName: String, startSeq: Int? = nil, endSeq: Int? = nil, count: Int? = nil, includeOld: Bool? = nil, sorting: Sorting_publicGetLastTradesByInstrumentGet? = nil, completion: @escaping (_ data: Any?, _ error: Error?) -> Void)
```

Retrieve the latest trades that have occurred for a specific instrument.

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

// Retrieve the latest trades that have occurred for a specific instrument.
MarketDataAPI.publicGetLastTradesByInstrumentGet(instrumentName: instrumentName, startSeq: startSeq, endSeq: endSeq, count: count, includeOld: includeOld, sorting: sorting) { (response, error) in
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

# **publicGetOrderBookGet**
```swift
    open class func publicGetOrderBookGet(instrumentName: String, depth: Double? = nil, completion: @escaping (_ data: Any?, _ error: Error?) -> Void)
```

Retrieves the order book, along with other market values for a given instrument.

### Example 
```swift
// The following code samples are still beta. For any issue, please report via http://github.com/OpenAPITools/openapi-generator/issues/new
import OpenAPIClient

let instrumentName = "instrumentName_example" // String | The instrument name for which to retrieve the order book, see [`getinstruments`](#getinstruments) to obtain instrument names.
let depth = 987 // Double | The number of entries to return for bids and asks. (optional)

// Retrieves the order book, along with other market values for a given instrument.
MarketDataAPI.publicGetOrderBookGet(instrumentName: instrumentName, depth: depth) { (response, error) in
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
 **instrumentName** | **String** | The instrument name for which to retrieve the order book, see [&#x60;getinstruments&#x60;](#getinstruments) to obtain instrument names. | 
 **depth** | **Double** | The number of entries to return for bids and asks. | [optional] 

### Return type

**Any**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **publicGetTradeVolumesGet**
```swift
    open class func publicGetTradeVolumesGet(completion: @escaping (_ data: Any?, _ error: Error?) -> Void)
```

Retrieves aggregated 24h trade volumes for different instrument types and currencies.

### Example 
```swift
// The following code samples are still beta. For any issue, please report via http://github.com/OpenAPITools/openapi-generator/issues/new
import OpenAPIClient


// Retrieves aggregated 24h trade volumes for different instrument types and currencies.
MarketDataAPI.publicGetTradeVolumesGet() { (response, error) in
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

# **publicGetTradingviewChartDataGet**
```swift
    open class func publicGetTradingviewChartDataGet(instrumentName: String, startTimestamp: Int, endTimestamp: Int, completion: @escaping (_ data: Any?, _ error: Error?) -> Void)
```

Publicly available market data used to generate a TradingView candle chart.

### Example 
```swift
// The following code samples are still beta. For any issue, please report via http://github.com/OpenAPITools/openapi-generator/issues/new
import OpenAPIClient

let instrumentName = "instrumentName_example" // String | Instrument name
let startTimestamp = 987 // Int | The earliest timestamp to return result for
let endTimestamp = 987 // Int | The most recent timestamp to return result for

// Publicly available market data used to generate a TradingView candle chart.
MarketDataAPI.publicGetTradingviewChartDataGet(instrumentName: instrumentName, startTimestamp: startTimestamp, endTimestamp: endTimestamp) { (response, error) in
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

### Return type

**Any**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **publicTickerGet**
```swift
    open class func publicTickerGet(instrumentName: String, completion: @escaping (_ data: Any?, _ error: Error?) -> Void)
```

Get ticker for an instrument.

### Example 
```swift
// The following code samples are still beta. For any issue, please report via http://github.com/OpenAPITools/openapi-generator/issues/new
import OpenAPIClient

let instrumentName = "instrumentName_example" // String | Instrument name

// Get ticker for an instrument.
MarketDataAPI.publicTickerGet(instrumentName: instrumentName) { (response, error) in
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

### Return type

**Any**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

