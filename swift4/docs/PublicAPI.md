# PublicAPI

All URIs are relative to *https://www.deribit.com/api/v2*

Method | HTTP request | Description
------------- | ------------- | -------------
[**publicAuthGet**](PublicAPI.md#publicauthget) | **GET** /public/auth | Authenticate
[**publicGetAnnouncementsGet**](PublicAPI.md#publicgetannouncementsget) | **GET** /public/get_announcements | Retrieves announcements from the last 30 days.
[**publicGetBookSummaryByCurrencyGet**](PublicAPI.md#publicgetbooksummarybycurrencyget) | **GET** /public/get_book_summary_by_currency | Retrieves the summary information such as open interest, 24h volume, etc. for all instruments for the currency (optionally filtered by kind).
[**publicGetBookSummaryByInstrumentGet**](PublicAPI.md#publicgetbooksummarybyinstrumentget) | **GET** /public/get_book_summary_by_instrument | Retrieves the summary information such as open interest, 24h volume, etc. for a specific instrument.
[**publicGetContractSizeGet**](PublicAPI.md#publicgetcontractsizeget) | **GET** /public/get_contract_size | Retrieves contract size of provided instrument.
[**publicGetCurrenciesGet**](PublicAPI.md#publicgetcurrenciesget) | **GET** /public/get_currencies | Retrieves all cryptocurrencies supported by the API.
[**publicGetFundingChartDataGet**](PublicAPI.md#publicgetfundingchartdataget) | **GET** /public/get_funding_chart_data | Retrieve the latest user trades that have occurred for PERPETUAL instruments in a specific currency symbol and within given time range.
[**publicGetHistoricalVolatilityGet**](PublicAPI.md#publicgethistoricalvolatilityget) | **GET** /public/get_historical_volatility | Provides information about historical volatility for given cryptocurrency.
[**publicGetIndexGet**](PublicAPI.md#publicgetindexget) | **GET** /public/get_index | Retrieves the current index price for the instruments, for the selected currency.
[**publicGetInstrumentsGet**](PublicAPI.md#publicgetinstrumentsget) | **GET** /public/get_instruments | Retrieves available trading instruments. This method can be used to see which instruments are available for trading, or which instruments have existed historically.
[**publicGetLastSettlementsByCurrencyGet**](PublicAPI.md#publicgetlastsettlementsbycurrencyget) | **GET** /public/get_last_settlements_by_currency | Retrieves historical settlement, delivery and bankruptcy events coming from all instruments within given currency.
[**publicGetLastSettlementsByInstrumentGet**](PublicAPI.md#publicgetlastsettlementsbyinstrumentget) | **GET** /public/get_last_settlements_by_instrument | Retrieves historical public settlement, delivery and bankruptcy events filtered by instrument name.
[**publicGetLastTradesByCurrencyAndTimeGet**](PublicAPI.md#publicgetlasttradesbycurrencyandtimeget) | **GET** /public/get_last_trades_by_currency_and_time | Retrieve the latest trades that have occurred for instruments in a specific currency symbol and within given time range.
[**publicGetLastTradesByCurrencyGet**](PublicAPI.md#publicgetlasttradesbycurrencyget) | **GET** /public/get_last_trades_by_currency | Retrieve the latest trades that have occurred for instruments in a specific currency symbol.
[**publicGetLastTradesByInstrumentAndTimeGet**](PublicAPI.md#publicgetlasttradesbyinstrumentandtimeget) | **GET** /public/get_last_trades_by_instrument_and_time | Retrieve the latest trades that have occurred for a specific instrument and within given time range.
[**publicGetLastTradesByInstrumentGet**](PublicAPI.md#publicgetlasttradesbyinstrumentget) | **GET** /public/get_last_trades_by_instrument | Retrieve the latest trades that have occurred for a specific instrument.
[**publicGetOrderBookGet**](PublicAPI.md#publicgetorderbookget) | **GET** /public/get_order_book | Retrieves the order book, along with other market values for a given instrument.
[**publicGetTimeGet**](PublicAPI.md#publicgettimeget) | **GET** /public/get_time | Retrieves the current time (in milliseconds). This API endpoint can be used to check the clock skew between your software and Deribit&#39;s systems.
[**publicGetTradeVolumesGet**](PublicAPI.md#publicgettradevolumesget) | **GET** /public/get_trade_volumes | Retrieves aggregated 24h trade volumes for different instrument types and currencies.
[**publicGetTradingviewChartDataGet**](PublicAPI.md#publicgettradingviewchartdataget) | **GET** /public/get_tradingview_chart_data | Publicly available market data used to generate a TradingView candle chart.
[**publicTestGet**](PublicAPI.md#publictestget) | **GET** /public/test | Tests the connection to the API server, and returns its version. You can use this to make sure the API is reachable, and matches the expected version.
[**publicTickerGet**](PublicAPI.md#publictickerget) | **GET** /public/ticker | Get ticker for an instrument.
[**publicValidateFieldGet**](PublicAPI.md#publicvalidatefieldget) | **GET** /public/validate_field | Method used to introduce the client software connected to Deribit platform over websocket. Provided data may have an impact on the maintained connection and will be collected for internal statistical purposes. In response, Deribit will also introduce itself.


# **publicAuthGet**
```swift
    open class func publicAuthGet(grantType: GrantType_publicAuthGet, username: String, password: String, clientId: String, clientSecret: String, refreshToken: String, timestamp: String, signature: String, nonce: String? = nil, state: String? = nil, scope: String? = nil, completion: @escaping (_ data: Any?, _ error: Error?) -> Void)
```

Authenticate

Retrieve an Oauth access token, to be used for authentication of 'private' requests.  Three methods of authentication are supported:  - <code>password</code> - using email and and password as when logging on to the website - <code>client_credentials</code> - using the access key and access secret that can be found on the API page on the website - <code>client_signature</code> - using the access key that can be found on the API page on the website and user generated signature. The signature is calculated using some fields provided in the request, using formula described here [Deribit signature credentials](#additional-authorization-method-deribit-signature-credentials) - <code>refresh_token</code> - using a refresh token that was received from an earlier invocation  The response will contain an access token, expiration period (number of seconds that the token is valid) and a refresh token that can be used to get a new set of tokens. 

### Example 
```swift
// The following code samples are still beta. For any issue, please report via http://github.com/OpenAPITools/openapi-generator/issues/new
import OpenAPIClient

let grantType = "grantType_example" // String | Method of authentication
let username = "username_example" // String | Required for grant type `password`
let password = "password_example" // String | Required for grant type `password`
let clientId = "clientId_example" // String | Required for grant type `client_credentials` and `client_signature`
let clientSecret = "clientSecret_example" // String | Required for grant type `client_credentials`
let refreshToken = "refreshToken_example" // String | Required for grant type `refresh_token`
let timestamp = "timestamp_example" // String | Required for grant type `client_signature`, provides time when request has been generated
let signature = "signature_example" // String | Required for grant type `client_signature`; it's a cryptographic signature calculated over provided fields using user **secret key**. The signature should be calculated as an HMAC (Hash-based Message Authentication Code) with `SHA256` hash algorithm
let nonce = "nonce_example" // String | Optional for grant type `client_signature`; delivers user generated initialization vector for the server token (optional)
let state = "state_example" // String | Will be passed back in the response (optional)
let scope = "scope_example" // String | Describes type of the access for assigned token, possible values: `connection`, `session`, `session:name`, `trade:[read, read_write, none]`, `wallet:[read, read_write, none]`, `account:[read, read_write, none]`, `expires:NUMBER` (token will expire after `NUMBER` of seconds).</BR></BR> **NOTICE:** Depending on choosing an authentication method (```grant type```) some scopes could be narrowed by the server. e.g. when ```grant_type = client_credentials``` and ```scope = wallet:read_write``` it's modified by the server as ```scope = wallet:read``` (optional)

// Authenticate
PublicAPI.publicAuthGet(grantType: grantType, username: username, password: password, clientId: clientId, clientSecret: clientSecret, refreshToken: refreshToken, timestamp: timestamp, signature: signature, nonce: nonce, state: state, scope: scope) { (response, error) in
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
 **grantType** | **String** | Method of authentication | 
 **username** | **String** | Required for grant type &#x60;password&#x60; | 
 **password** | **String** | Required for grant type &#x60;password&#x60; | 
 **clientId** | **String** | Required for grant type &#x60;client_credentials&#x60; and &#x60;client_signature&#x60; | 
 **clientSecret** | **String** | Required for grant type &#x60;client_credentials&#x60; | 
 **refreshToken** | **String** | Required for grant type &#x60;refresh_token&#x60; | 
 **timestamp** | **String** | Required for grant type &#x60;client_signature&#x60;, provides time when request has been generated | 
 **signature** | **String** | Required for grant type &#x60;client_signature&#x60;; it&#39;s a cryptographic signature calculated over provided fields using user **secret key**. The signature should be calculated as an HMAC (Hash-based Message Authentication Code) with &#x60;SHA256&#x60; hash algorithm | 
 **nonce** | **String** | Optional for grant type &#x60;client_signature&#x60;; delivers user generated initialization vector for the server token | [optional] 
 **state** | **String** | Will be passed back in the response | [optional] 
 **scope** | **String** | Describes type of the access for assigned token, possible values: &#x60;connection&#x60;, &#x60;session&#x60;, &#x60;session:name&#x60;, &#x60;trade:[read, read_write, none]&#x60;, &#x60;wallet:[read, read_write, none]&#x60;, &#x60;account:[read, read_write, none]&#x60;, &#x60;expires:NUMBER&#x60; (token will expire after &#x60;NUMBER&#x60; of seconds).&lt;/BR&gt;&lt;/BR&gt; **NOTICE:** Depending on choosing an authentication method (&#x60;&#x60;&#x60;grant type&#x60;&#x60;&#x60;) some scopes could be narrowed by the server. e.g. when &#x60;&#x60;&#x60;grant_type &#x3D; client_credentials&#x60;&#x60;&#x60; and &#x60;&#x60;&#x60;scope &#x3D; wallet:read_write&#x60;&#x60;&#x60; it&#39;s modified by the server as &#x60;&#x60;&#x60;scope &#x3D; wallet:read&#x60;&#x60;&#x60; | [optional] 

### Return type

**Any**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **publicGetAnnouncementsGet**
```swift
    open class func publicGetAnnouncementsGet(completion: @escaping (_ data: Any?, _ error: Error?) -> Void)
```

Retrieves announcements from the last 30 days.

### Example 
```swift
// The following code samples are still beta. For any issue, please report via http://github.com/OpenAPITools/openapi-generator/issues/new
import OpenAPIClient


// Retrieves announcements from the last 30 days.
PublicAPI.publicGetAnnouncementsGet() { (response, error) in
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
PublicAPI.publicGetBookSummaryByCurrencyGet(currency: currency, kind: kind) { (response, error) in
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
PublicAPI.publicGetBookSummaryByInstrumentGet(instrumentName: instrumentName) { (response, error) in
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
PublicAPI.publicGetContractSizeGet(instrumentName: instrumentName) { (response, error) in
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
PublicAPI.publicGetCurrenciesGet() { (response, error) in
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
PublicAPI.publicGetFundingChartDataGet(instrumentName: instrumentName, length: length) { (response, error) in
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
PublicAPI.publicGetHistoricalVolatilityGet(currency: currency) { (response, error) in
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
PublicAPI.publicGetIndexGet(currency: currency) { (response, error) in
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
PublicAPI.publicGetInstrumentsGet(currency: currency, kind: kind, expired: expired) { (response, error) in
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
PublicAPI.publicGetLastSettlementsByCurrencyGet(currency: currency, type: type, count: count, continuation: continuation, searchStartTimestamp: searchStartTimestamp) { (response, error) in
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
PublicAPI.publicGetLastSettlementsByInstrumentGet(instrumentName: instrumentName, type: type, count: count, continuation: continuation, searchStartTimestamp: searchStartTimestamp) { (response, error) in
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
PublicAPI.publicGetLastTradesByCurrencyAndTimeGet(currency: currency, startTimestamp: startTimestamp, endTimestamp: endTimestamp, kind: kind, count: count, includeOld: includeOld, sorting: sorting) { (response, error) in
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
PublicAPI.publicGetLastTradesByCurrencyGet(currency: currency, kind: kind, startId: startId, endId: endId, count: count, includeOld: includeOld, sorting: sorting) { (response, error) in
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
PublicAPI.publicGetLastTradesByInstrumentAndTimeGet(instrumentName: instrumentName, startTimestamp: startTimestamp, endTimestamp: endTimestamp, count: count, includeOld: includeOld, sorting: sorting) { (response, error) in
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
PublicAPI.publicGetLastTradesByInstrumentGet(instrumentName: instrumentName, startSeq: startSeq, endSeq: endSeq, count: count, includeOld: includeOld, sorting: sorting) { (response, error) in
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
PublicAPI.publicGetOrderBookGet(instrumentName: instrumentName, depth: depth) { (response, error) in
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

# **publicGetTimeGet**
```swift
    open class func publicGetTimeGet(completion: @escaping (_ data: Any?, _ error: Error?) -> Void)
```

Retrieves the current time (in milliseconds). This API endpoint can be used to check the clock skew between your software and Deribit's systems.

### Example 
```swift
// The following code samples are still beta. For any issue, please report via http://github.com/OpenAPITools/openapi-generator/issues/new
import OpenAPIClient


// Retrieves the current time (in milliseconds). This API endpoint can be used to check the clock skew between your software and Deribit's systems.
PublicAPI.publicGetTimeGet() { (response, error) in
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
PublicAPI.publicGetTradeVolumesGet() { (response, error) in
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
PublicAPI.publicGetTradingviewChartDataGet(instrumentName: instrumentName, startTimestamp: startTimestamp, endTimestamp: endTimestamp) { (response, error) in
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

# **publicTestGet**
```swift
    open class func publicTestGet(expectedResult: ExpectedResult_publicTestGet? = nil, completion: @escaping (_ data: Any?, _ error: Error?) -> Void)
```

Tests the connection to the API server, and returns its version. You can use this to make sure the API is reachable, and matches the expected version.

### Example 
```swift
// The following code samples are still beta. For any issue, please report via http://github.com/OpenAPITools/openapi-generator/issues/new
import OpenAPIClient

let expectedResult = "expectedResult_example" // String | The value \"exception\" will trigger an error response. This may be useful for testing wrapper libraries. (optional)

// Tests the connection to the API server, and returns its version. You can use this to make sure the API is reachable, and matches the expected version.
PublicAPI.publicTestGet(expectedResult: expectedResult) { (response, error) in
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
 **expectedResult** | **String** | The value \&quot;exception\&quot; will trigger an error response. This may be useful for testing wrapper libraries. | [optional] 

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
PublicAPI.publicTickerGet(instrumentName: instrumentName) { (response, error) in
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

# **publicValidateFieldGet**
```swift
    open class func publicValidateFieldGet(field: String, value: String, value2: String? = nil, completion: @escaping (_ data: Any?, _ error: Error?) -> Void)
```

Method used to introduce the client software connected to Deribit platform over websocket. Provided data may have an impact on the maintained connection and will be collected for internal statistical purposes. In response, Deribit will also introduce itself.

### Example 
```swift
// The following code samples are still beta. For any issue, please report via http://github.com/OpenAPITools/openapi-generator/issues/new
import OpenAPIClient

let field = "field_example" // String | Name of the field to be validated, examples: postal_code, date_of_birth
let value = "value_example" // String | Value to be checked
let value2 = "value2_example" // String | Additional value to be compared with (optional)

// Method used to introduce the client software connected to Deribit platform over websocket. Provided data may have an impact on the maintained connection and will be collected for internal statistical purposes. In response, Deribit will also introduce itself.
PublicAPI.publicValidateFieldGet(field: field, value: value, value2: value2) { (response, error) in
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
 **field** | **String** | Name of the field to be validated, examples: postal_code, date_of_birth | 
 **value** | **String** | Value to be checked | 
 **value2** | **String** | Additional value to be compared with | [optional] 

### Return type

**Any**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

