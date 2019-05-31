# PublicApi

All URIs are relative to *https://www.deribit.com/api/v2*

Method | HTTP request | Description
------------- | ------------- | -------------
[**PublicAuthGet**](PublicApi.md#PublicAuthGet) | **GET** /public/auth | Authenticate
[**PublicGetAnnouncementsGet**](PublicApi.md#PublicGetAnnouncementsGet) | **GET** /public/get_announcements | Retrieves announcements from the last 30 days.
[**PublicGetBookSummaryByCurrencyGet**](PublicApi.md#PublicGetBookSummaryByCurrencyGet) | **GET** /public/get_book_summary_by_currency | Retrieves the summary information such as open interest, 24h volume, etc. for all instruments for the currency (optionally filtered by kind).
[**PublicGetBookSummaryByInstrumentGet**](PublicApi.md#PublicGetBookSummaryByInstrumentGet) | **GET** /public/get_book_summary_by_instrument | Retrieves the summary information such as open interest, 24h volume, etc. for a specific instrument.
[**PublicGetContractSizeGet**](PublicApi.md#PublicGetContractSizeGet) | **GET** /public/get_contract_size | Retrieves contract size of provided instrument.
[**PublicGetCurrenciesGet**](PublicApi.md#PublicGetCurrenciesGet) | **GET** /public/get_currencies | Retrieves all cryptocurrencies supported by the API.
[**PublicGetFundingChartDataGet**](PublicApi.md#PublicGetFundingChartDataGet) | **GET** /public/get_funding_chart_data | Retrieve the latest user trades that have occurred for PERPETUAL instruments in a specific currency symbol and within given time range.
[**PublicGetHistoricalVolatilityGet**](PublicApi.md#PublicGetHistoricalVolatilityGet) | **GET** /public/get_historical_volatility | Provides information about historical volatility for given cryptocurrency.
[**PublicGetIndexGet**](PublicApi.md#PublicGetIndexGet) | **GET** /public/get_index | Retrieves the current index price for the instruments, for the selected currency.
[**PublicGetInstrumentsGet**](PublicApi.md#PublicGetInstrumentsGet) | **GET** /public/get_instruments | Retrieves available trading instruments. This method can be used to see which instruments are available for trading, or which instruments have existed historically.
[**PublicGetLastSettlementsByCurrencyGet**](PublicApi.md#PublicGetLastSettlementsByCurrencyGet) | **GET** /public/get_last_settlements_by_currency | Retrieves historical settlement, delivery and bankruptcy events coming from all instruments within given currency.
[**PublicGetLastSettlementsByInstrumentGet**](PublicApi.md#PublicGetLastSettlementsByInstrumentGet) | **GET** /public/get_last_settlements_by_instrument | Retrieves historical public settlement, delivery and bankruptcy events filtered by instrument name.
[**PublicGetLastTradesByCurrencyAndTimeGet**](PublicApi.md#PublicGetLastTradesByCurrencyAndTimeGet) | **GET** /public/get_last_trades_by_currency_and_time | Retrieve the latest trades that have occurred for instruments in a specific currency symbol and within given time range.
[**PublicGetLastTradesByCurrencyGet**](PublicApi.md#PublicGetLastTradesByCurrencyGet) | **GET** /public/get_last_trades_by_currency | Retrieve the latest trades that have occurred for instruments in a specific currency symbol.
[**PublicGetLastTradesByInstrumentAndTimeGet**](PublicApi.md#PublicGetLastTradesByInstrumentAndTimeGet) | **GET** /public/get_last_trades_by_instrument_and_time | Retrieve the latest trades that have occurred for a specific instrument and within given time range.
[**PublicGetLastTradesByInstrumentGet**](PublicApi.md#PublicGetLastTradesByInstrumentGet) | **GET** /public/get_last_trades_by_instrument | Retrieve the latest trades that have occurred for a specific instrument.
[**PublicGetOrderBookGet**](PublicApi.md#PublicGetOrderBookGet) | **GET** /public/get_order_book | Retrieves the order book, along with other market values for a given instrument.
[**PublicGetTimeGet**](PublicApi.md#PublicGetTimeGet) | **GET** /public/get_time | Retrieves the current time (in milliseconds). This API endpoint can be used to check the clock skew between your software and Deribit&#39;s systems.
[**PublicGetTradeVolumesGet**](PublicApi.md#PublicGetTradeVolumesGet) | **GET** /public/get_trade_volumes | Retrieves aggregated 24h trade volumes for different instrument types and currencies.
[**PublicGetTradingviewChartDataGet**](PublicApi.md#PublicGetTradingviewChartDataGet) | **GET** /public/get_tradingview_chart_data | Publicly available market data used to generate a TradingView candle chart.
[**PublicTestGet**](PublicApi.md#PublicTestGet) | **GET** /public/test | Tests the connection to the API server, and returns its version. You can use this to make sure the API is reachable, and matches the expected version.
[**PublicTickerGet**](PublicApi.md#PublicTickerGet) | **GET** /public/ticker | Get ticker for an instrument.
[**PublicValidateFieldGet**](PublicApi.md#PublicValidateFieldGet) | **GET** /public/validate_field | Method used to introduce the client software connected to Deribit platform over websocket. Provided data may have an impact on the maintained connection and will be collected for internal statistical purposes. In response, Deribit will also introduce itself.


# **PublicAuthGet**
> object PublicAuthGet(grant.type, username, password, client.id, client.secret, refresh.token, timestamp, signature, nonce=var.nonce, state=var.state, scope=var.scope)

Authenticate

Retrieve an Oauth access token, to be used for authentication of 'private' requests.  Three methods of authentication are supported:  - <code>password</code> - using email and and password as when logging on to the website - <code>client_credentials</code> - using the access key and access secret that can be found on the API page on the website - <code>client_signature</code> - using the access key that can be found on the API page on the website and user generated signature. The signature is calculated using some fields provided in the request, using formula described here [Deribit signature credentials](#additional-authorization-method-deribit-signature-credentials) - <code>refresh_token</code> - using a refresh token that was received from an earlier invocation  The response will contain an access token, expiration period (number of seconds that the token is valid) and a refresh token that can be used to get a new set of tokens. 

### Example
```R
library(openapi)

var.grant.type <- 'grant.type_example' # character | Method of authentication
var.username <- 'your_email@mail.com' # character | Required for grant type `password`
var.password <- 'your_password' # character | Required for grant type `password`
var.client.id <- 'client.id_example' # character | Required for grant type `client_credentials` and `client_signature`
var.client.secret <- 'client.secret_example' # character | Required for grant type `client_credentials`
var.refresh.token <- 'refresh.token_example' # character | Required for grant type `refresh_token`
var.timestamp <- 'timestamp_example' # character | Required for grant type `client_signature`, provides time when request has been generated
var.signature <- 'signature_example' # character | Required for grant type `client_signature`; it's a cryptographic signature calculated over provided fields using user **secret key**. The signature should be calculated as an HMAC (Hash-based Message Authentication Code) with `SHA256` hash algorithm
var.nonce <- 'nonce_example' # character | Optional for grant type `client_signature`; delivers user generated initialization vector for the server token
var.state <- 'state_example' # character | Will be passed back in the response
var.scope <- 'connection' # character | Describes type of the access for assigned token, possible values: `connection`, `session`, `session:name`, `trade:[read, read_write, none]`, `wallet:[read, read_write, none]`, `account:[read, read_write, none]`, `expires:NUMBER` (token will expire after `NUMBER` of seconds).</BR></BR> **NOTICE:** Depending on choosing an authentication method (```grant type```) some scopes could be narrowed by the server. e.g. when ```grant_type = client_credentials``` and ```scope = wallet:read_write``` it's modified by the server as ```scope = wallet:read```

#Authenticate
api.instance <- PublicApi$new()
# Configure HTTP basic authorization: bearerAuth
api.instance$apiClient$username <- 'TODO_YOUR_USERNAME';
api.instance$apiClient$password <- 'TODO_YOUR_PASSWORD';
result <- api.instance$PublicAuthGet(var.grant.type, var.username, var.password, var.client.id, var.client.secret, var.refresh.token, var.timestamp, var.signature, nonce=var.nonce, state=var.state, scope=var.scope)
dput(result)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **grant.type** | **character**| Method of authentication | 
 **username** | **character**| Required for grant type &#x60;password&#x60; | 
 **password** | **character**| Required for grant type &#x60;password&#x60; | 
 **client.id** | **character**| Required for grant type &#x60;client_credentials&#x60; and &#x60;client_signature&#x60; | 
 **client.secret** | **character**| Required for grant type &#x60;client_credentials&#x60; | 
 **refresh.token** | **character**| Required for grant type &#x60;refresh_token&#x60; | 
 **timestamp** | **character**| Required for grant type &#x60;client_signature&#x60;, provides time when request has been generated | 
 **signature** | **character**| Required for grant type &#x60;client_signature&#x60;; it&#39;s a cryptographic signature calculated over provided fields using user **secret key**. The signature should be calculated as an HMAC (Hash-based Message Authentication Code) with &#x60;SHA256&#x60; hash algorithm | 
 **nonce** | **character**| Optional for grant type &#x60;client_signature&#x60;; delivers user generated initialization vector for the server token | [optional] 
 **state** | **character**| Will be passed back in the response | [optional] 
 **scope** | **character**| Describes type of the access for assigned token, possible values: &#x60;connection&#x60;, &#x60;session&#x60;, &#x60;session:name&#x60;, &#x60;trade:[read, read_write, none]&#x60;, &#x60;wallet:[read, read_write, none]&#x60;, &#x60;account:[read, read_write, none]&#x60;, &#x60;expires:NUMBER&#x60; (token will expire after &#x60;NUMBER&#x60; of seconds).&lt;/BR&gt;&lt;/BR&gt; **NOTICE:** Depending on choosing an authentication method (&#x60;&#x60;&#x60;grant type&#x60;&#x60;&#x60;) some scopes could be narrowed by the server. e.g. when &#x60;&#x60;&#x60;grant_type &#x3D; client_credentials&#x60;&#x60;&#x60; and &#x60;&#x60;&#x60;scope &#x3D; wallet:read_write&#x60;&#x60;&#x60; it&#39;s modified by the server as &#x60;&#x60;&#x60;scope &#x3D; wallet:read&#x60;&#x60;&#x60; | [optional] 

### Return type

**object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json



# **PublicGetAnnouncementsGet**
> object PublicGetAnnouncementsGet()

Retrieves announcements from the last 30 days.

### Example
```R
library(openapi)


#Retrieves announcements from the last 30 days.
api.instance <- PublicApi$new()
# Configure HTTP basic authorization: bearerAuth
api.instance$apiClient$username <- 'TODO_YOUR_USERNAME';
api.instance$apiClient$password <- 'TODO_YOUR_PASSWORD';
result <- api.instance$PublicGetAnnouncementsGet()
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



# **PublicGetBookSummaryByCurrencyGet**
> object PublicGetBookSummaryByCurrencyGet(currency, kind=var.kind)

Retrieves the summary information such as open interest, 24h volume, etc. for all instruments for the currency (optionally filtered by kind).

### Example
```R
library(openapi)

var.currency <- 'currency_example' # character | The currency symbol
var.kind <- 'kind_example' # character | Instrument kind, if not provided instruments of all kinds are considered

#Retrieves the summary information such as open interest, 24h volume, etc. for all instruments for the currency (optionally filtered by kind).
api.instance <- PublicApi$new()
# Configure HTTP basic authorization: bearerAuth
api.instance$apiClient$username <- 'TODO_YOUR_USERNAME';
api.instance$apiClient$password <- 'TODO_YOUR_PASSWORD';
result <- api.instance$PublicGetBookSummaryByCurrencyGet(var.currency, kind=var.kind)
dput(result)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **character**| The currency symbol | 
 **kind** | **character**| Instrument kind, if not provided instruments of all kinds are considered | [optional] 

### Return type

**object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json



# **PublicGetBookSummaryByInstrumentGet**
> object PublicGetBookSummaryByInstrumentGet(instrument.name)

Retrieves the summary information such as open interest, 24h volume, etc. for a specific instrument.

### Example
```R
library(openapi)

var.instrument.name <- 'BTC-PERPETUAL' # character | Instrument name

#Retrieves the summary information such as open interest, 24h volume, etc. for a specific instrument.
api.instance <- PublicApi$new()
# Configure HTTP basic authorization: bearerAuth
api.instance$apiClient$username <- 'TODO_YOUR_USERNAME';
api.instance$apiClient$password <- 'TODO_YOUR_PASSWORD';
result <- api.instance$PublicGetBookSummaryByInstrumentGet(var.instrument.name)
dput(result)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrument.name** | **character**| Instrument name | 

### Return type

**object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json



# **PublicGetContractSizeGet**
> object PublicGetContractSizeGet(instrument.name)

Retrieves contract size of provided instrument.

### Example
```R
library(openapi)

var.instrument.name <- 'BTC-PERPETUAL' # character | Instrument name

#Retrieves contract size of provided instrument.
api.instance <- PublicApi$new()
# Configure HTTP basic authorization: bearerAuth
api.instance$apiClient$username <- 'TODO_YOUR_USERNAME';
api.instance$apiClient$password <- 'TODO_YOUR_PASSWORD';
result <- api.instance$PublicGetContractSizeGet(var.instrument.name)
dput(result)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrument.name** | **character**| Instrument name | 

### Return type

**object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json



# **PublicGetCurrenciesGet**
> object PublicGetCurrenciesGet()

Retrieves all cryptocurrencies supported by the API.

### Example
```R
library(openapi)


#Retrieves all cryptocurrencies supported by the API.
api.instance <- PublicApi$new()
# Configure HTTP basic authorization: bearerAuth
api.instance$apiClient$username <- 'TODO_YOUR_USERNAME';
api.instance$apiClient$password <- 'TODO_YOUR_PASSWORD';
result <- api.instance$PublicGetCurrenciesGet()
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



# **PublicGetFundingChartDataGet**
> object PublicGetFundingChartDataGet(instrument.name, length=var.length)

Retrieve the latest user trades that have occurred for PERPETUAL instruments in a specific currency symbol and within given time range.

### Example
```R
library(openapi)

var.instrument.name <- 'BTC-PERPETUAL' # character | Instrument name
var.length <- 'length_example' # character | Specifies time period. `8h` - 8 hours, `24h` - 24 hours

#Retrieve the latest user trades that have occurred for PERPETUAL instruments in a specific currency symbol and within given time range.
api.instance <- PublicApi$new()
# Configure HTTP basic authorization: bearerAuth
api.instance$apiClient$username <- 'TODO_YOUR_USERNAME';
api.instance$apiClient$password <- 'TODO_YOUR_PASSWORD';
result <- api.instance$PublicGetFundingChartDataGet(var.instrument.name, length=var.length)
dput(result)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrument.name** | **character**| Instrument name | 
 **length** | **character**| Specifies time period. &#x60;8h&#x60; - 8 hours, &#x60;24h&#x60; - 24 hours | [optional] 

### Return type

**object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json



# **PublicGetHistoricalVolatilityGet**
> object PublicGetHistoricalVolatilityGet(currency)

Provides information about historical volatility for given cryptocurrency.

### Example
```R
library(openapi)

var.currency <- 'currency_example' # character | The currency symbol

#Provides information about historical volatility for given cryptocurrency.
api.instance <- PublicApi$new()
# Configure HTTP basic authorization: bearerAuth
api.instance$apiClient$username <- 'TODO_YOUR_USERNAME';
api.instance$apiClient$password <- 'TODO_YOUR_PASSWORD';
result <- api.instance$PublicGetHistoricalVolatilityGet(var.currency)
dput(result)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **character**| The currency symbol | 

### Return type

**object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json



# **PublicGetIndexGet**
> object PublicGetIndexGet(currency)

Retrieves the current index price for the instruments, for the selected currency.

### Example
```R
library(openapi)

var.currency <- 'currency_example' # character | The currency symbol

#Retrieves the current index price for the instruments, for the selected currency.
api.instance <- PublicApi$new()
# Configure HTTP basic authorization: bearerAuth
api.instance$apiClient$username <- 'TODO_YOUR_USERNAME';
api.instance$apiClient$password <- 'TODO_YOUR_PASSWORD';
result <- api.instance$PublicGetIndexGet(var.currency)
dput(result)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **character**| The currency symbol | 

### Return type

**object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json



# **PublicGetInstrumentsGet**
> object PublicGetInstrumentsGet(currency, kind=var.kind, expired=FALSE)

Retrieves available trading instruments. This method can be used to see which instruments are available for trading, or which instruments have existed historically.

### Example
```R
library(openapi)

var.currency <- 'currency_example' # character | The currency symbol
var.kind <- 'kind_example' # character | Instrument kind, if not provided instruments of all kinds are considered
var.expired <- FALSE # character | Set to true to show expired instruments instead of active ones.

#Retrieves available trading instruments. This method can be used to see which instruments are available for trading, or which instruments have existed historically.
api.instance <- PublicApi$new()
# Configure HTTP basic authorization: bearerAuth
api.instance$apiClient$username <- 'TODO_YOUR_USERNAME';
api.instance$apiClient$password <- 'TODO_YOUR_PASSWORD';
result <- api.instance$PublicGetInstrumentsGet(var.currency, kind=var.kind, expired=var.expired)
dput(result)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **character**| The currency symbol | 
 **kind** | **character**| Instrument kind, if not provided instruments of all kinds are considered | [optional] 
 **expired** | **character**| Set to true to show expired instruments instead of active ones. | [optional] [default to FALSE]

### Return type

**object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json



# **PublicGetLastSettlementsByCurrencyGet**
> object PublicGetLastSettlementsByCurrencyGet(currency, type=var.type, count=var.count, continuation=var.continuation, search.start.timestamp=var.search.start.timestamp)

Retrieves historical settlement, delivery and bankruptcy events coming from all instruments within given currency.

### Example
```R
library(openapi)

var.currency <- 'currency_example' # character | The currency symbol
var.type <- 'type_example' # character | Settlement type
var.count <- 56 # integer | Number of requested items, default - `20`
var.continuation <- 'xY7T6cutS3t2B9YtaDkE6TS379oKnkzTvmEDUnEUP2Msa9xKWNNaT' # character | Continuation token for pagination
var.search.start.timestamp <- 1536569522277 # integer | The latest timestamp to return result for

#Retrieves historical settlement, delivery and bankruptcy events coming from all instruments within given currency.
api.instance <- PublicApi$new()
# Configure HTTP basic authorization: bearerAuth
api.instance$apiClient$username <- 'TODO_YOUR_USERNAME';
api.instance$apiClient$password <- 'TODO_YOUR_PASSWORD';
result <- api.instance$PublicGetLastSettlementsByCurrencyGet(var.currency, type=var.type, count=var.count, continuation=var.continuation, search.start.timestamp=var.search.start.timestamp)
dput(result)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **character**| The currency symbol | 
 **type** | **character**| Settlement type | [optional] 
 **count** | **integer**| Number of requested items, default - &#x60;20&#x60; | [optional] 
 **continuation** | **character**| Continuation token for pagination | [optional] 
 **search.start.timestamp** | **integer**| The latest timestamp to return result for | [optional] 

### Return type

**object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json



# **PublicGetLastSettlementsByInstrumentGet**
> object PublicGetLastSettlementsByInstrumentGet(instrument.name, type=var.type, count=var.count, continuation=var.continuation, search.start.timestamp=var.search.start.timestamp)

Retrieves historical public settlement, delivery and bankruptcy events filtered by instrument name.

### Example
```R
library(openapi)

var.instrument.name <- 'BTC-PERPETUAL' # character | Instrument name
var.type <- 'type_example' # character | Settlement type
var.count <- 56 # integer | Number of requested items, default - `20`
var.continuation <- 'xY7T6cutS3t2B9YtaDkE6TS379oKnkzTvmEDUnEUP2Msa9xKWNNaT' # character | Continuation token for pagination
var.search.start.timestamp <- 1536569522277 # integer | The latest timestamp to return result for

#Retrieves historical public settlement, delivery and bankruptcy events filtered by instrument name.
api.instance <- PublicApi$new()
# Configure HTTP basic authorization: bearerAuth
api.instance$apiClient$username <- 'TODO_YOUR_USERNAME';
api.instance$apiClient$password <- 'TODO_YOUR_PASSWORD';
result <- api.instance$PublicGetLastSettlementsByInstrumentGet(var.instrument.name, type=var.type, count=var.count, continuation=var.continuation, search.start.timestamp=var.search.start.timestamp)
dput(result)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrument.name** | **character**| Instrument name | 
 **type** | **character**| Settlement type | [optional] 
 **count** | **integer**| Number of requested items, default - &#x60;20&#x60; | [optional] 
 **continuation** | **character**| Continuation token for pagination | [optional] 
 **search.start.timestamp** | **integer**| The latest timestamp to return result for | [optional] 

### Return type

**object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json



# **PublicGetLastTradesByCurrencyAndTimeGet**
> object PublicGetLastTradesByCurrencyAndTimeGet(currency, start.timestamp, end.timestamp, kind=var.kind, count=var.count, include.old=var.include.old, sorting=var.sorting)

Retrieve the latest trades that have occurred for instruments in a specific currency symbol and within given time range.

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

#Retrieve the latest trades that have occurred for instruments in a specific currency symbol and within given time range.
api.instance <- PublicApi$new()
# Configure HTTP basic authorization: bearerAuth
api.instance$apiClient$username <- 'TODO_YOUR_USERNAME';
api.instance$apiClient$password <- 'TODO_YOUR_PASSWORD';
result <- api.instance$PublicGetLastTradesByCurrencyAndTimeGet(var.currency, var.start.timestamp, var.end.timestamp, kind=var.kind, count=var.count, include.old=var.include.old, sorting=var.sorting)
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



# **PublicGetLastTradesByCurrencyGet**
> object PublicGetLastTradesByCurrencyGet(currency, kind=var.kind, start.id=var.start.id, end.id=var.end.id, count=var.count, include.old=var.include.old, sorting=var.sorting)

Retrieve the latest trades that have occurred for instruments in a specific currency symbol.

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

#Retrieve the latest trades that have occurred for instruments in a specific currency symbol.
api.instance <- PublicApi$new()
# Configure HTTP basic authorization: bearerAuth
api.instance$apiClient$username <- 'TODO_YOUR_USERNAME';
api.instance$apiClient$password <- 'TODO_YOUR_PASSWORD';
result <- api.instance$PublicGetLastTradesByCurrencyGet(var.currency, kind=var.kind, start.id=var.start.id, end.id=var.end.id, count=var.count, include.old=var.include.old, sorting=var.sorting)
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



# **PublicGetLastTradesByInstrumentAndTimeGet**
> object PublicGetLastTradesByInstrumentAndTimeGet(instrument.name, start.timestamp, end.timestamp, count=var.count, include.old=var.include.old, sorting=var.sorting)

Retrieve the latest trades that have occurred for a specific instrument and within given time range.

### Example
```R
library(openapi)

var.instrument.name <- 'BTC-PERPETUAL' # character | Instrument name
var.start.timestamp <- 1536569522277 # integer | The earliest timestamp to return result for
var.end.timestamp <- 1536569522277 # integer | The most recent timestamp to return result for
var.count <- 56 # integer | Number of requested items, default - `10`
var.include.old <- 'include.old_example' # character | Include trades older than 7 days, default - `false`
var.sorting <- 'sorting_example' # character | Direction of results sorting (`default` value means no sorting, results will be returned in order in which they left the database)

#Retrieve the latest trades that have occurred for a specific instrument and within given time range.
api.instance <- PublicApi$new()
# Configure HTTP basic authorization: bearerAuth
api.instance$apiClient$username <- 'TODO_YOUR_USERNAME';
api.instance$apiClient$password <- 'TODO_YOUR_PASSWORD';
result <- api.instance$PublicGetLastTradesByInstrumentAndTimeGet(var.instrument.name, var.start.timestamp, var.end.timestamp, count=var.count, include.old=var.include.old, sorting=var.sorting)
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



# **PublicGetLastTradesByInstrumentGet**
> object PublicGetLastTradesByInstrumentGet(instrument.name, start.seq=var.start.seq, end.seq=var.end.seq, count=var.count, include.old=var.include.old, sorting=var.sorting)

Retrieve the latest trades that have occurred for a specific instrument.

### Example
```R
library(openapi)

var.instrument.name <- 'BTC-PERPETUAL' # character | Instrument name
var.start.seq <- 56 # integer | The sequence number of the first trade to be returned
var.end.seq <- 56 # integer | The sequence number of the last trade to be returned
var.count <- 56 # integer | Number of requested items, default - `10`
var.include.old <- 'include.old_example' # character | Include trades older than 7 days, default - `false`
var.sorting <- 'sorting_example' # character | Direction of results sorting (`default` value means no sorting, results will be returned in order in which they left the database)

#Retrieve the latest trades that have occurred for a specific instrument.
api.instance <- PublicApi$new()
# Configure HTTP basic authorization: bearerAuth
api.instance$apiClient$username <- 'TODO_YOUR_USERNAME';
api.instance$apiClient$password <- 'TODO_YOUR_PASSWORD';
result <- api.instance$PublicGetLastTradesByInstrumentGet(var.instrument.name, start.seq=var.start.seq, end.seq=var.end.seq, count=var.count, include.old=var.include.old, sorting=var.sorting)
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



# **PublicGetOrderBookGet**
> object PublicGetOrderBookGet(instrument.name, depth=var.depth)

Retrieves the order book, along with other market values for a given instrument.

### Example
```R
library(openapi)

var.instrument.name <- 'instrument.name_example' # character | The instrument name for which to retrieve the order book, see [`getinstruments`](#getinstruments) to obtain instrument names.
var.depth <- 3.4 # numeric | The number of entries to return for bids and asks.

#Retrieves the order book, along with other market values for a given instrument.
api.instance <- PublicApi$new()
# Configure HTTP basic authorization: bearerAuth
api.instance$apiClient$username <- 'TODO_YOUR_USERNAME';
api.instance$apiClient$password <- 'TODO_YOUR_PASSWORD';
result <- api.instance$PublicGetOrderBookGet(var.instrument.name, depth=var.depth)
dput(result)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrument.name** | **character**| The instrument name for which to retrieve the order book, see [&#x60;getinstruments&#x60;](#getinstruments) to obtain instrument names. | 
 **depth** | **numeric**| The number of entries to return for bids and asks. | [optional] 

### Return type

**object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json



# **PublicGetTimeGet**
> object PublicGetTimeGet()

Retrieves the current time (in milliseconds). This API endpoint can be used to check the clock skew between your software and Deribit's systems.

### Example
```R
library(openapi)


#Retrieves the current time (in milliseconds). This API endpoint can be used to check the clock skew between your software and Deribit's systems.
api.instance <- PublicApi$new()
# Configure HTTP basic authorization: bearerAuth
api.instance$apiClient$username <- 'TODO_YOUR_USERNAME';
api.instance$apiClient$password <- 'TODO_YOUR_PASSWORD';
result <- api.instance$PublicGetTimeGet()
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



# **PublicGetTradeVolumesGet**
> object PublicGetTradeVolumesGet()

Retrieves aggregated 24h trade volumes for different instrument types and currencies.

### Example
```R
library(openapi)


#Retrieves aggregated 24h trade volumes for different instrument types and currencies.
api.instance <- PublicApi$new()
# Configure HTTP basic authorization: bearerAuth
api.instance$apiClient$username <- 'TODO_YOUR_USERNAME';
api.instance$apiClient$password <- 'TODO_YOUR_PASSWORD';
result <- api.instance$PublicGetTradeVolumesGet()
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



# **PublicGetTradingviewChartDataGet**
> object PublicGetTradingviewChartDataGet(instrument.name, start.timestamp, end.timestamp)

Publicly available market data used to generate a TradingView candle chart.

### Example
```R
library(openapi)

var.instrument.name <- 'BTC-PERPETUAL' # character | Instrument name
var.start.timestamp <- 1536569522277 # integer | The earliest timestamp to return result for
var.end.timestamp <- 1536569522277 # integer | The most recent timestamp to return result for

#Publicly available market data used to generate a TradingView candle chart.
api.instance <- PublicApi$new()
# Configure HTTP basic authorization: bearerAuth
api.instance$apiClient$username <- 'TODO_YOUR_USERNAME';
api.instance$apiClient$password <- 'TODO_YOUR_PASSWORD';
result <- api.instance$PublicGetTradingviewChartDataGet(var.instrument.name, var.start.timestamp, var.end.timestamp)
dput(result)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrument.name** | **character**| Instrument name | 
 **start.timestamp** | **integer**| The earliest timestamp to return result for | 
 **end.timestamp** | **integer**| The most recent timestamp to return result for | 

### Return type

**object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json



# **PublicTestGet**
> object PublicTestGet(expected.result=var.expected.result)

Tests the connection to the API server, and returns its version. You can use this to make sure the API is reachable, and matches the expected version.

### Example
```R
library(openapi)

var.expected.result <- 'expected.result_example' # character | The value \"exception\" will trigger an error response. This may be useful for testing wrapper libraries.

#Tests the connection to the API server, and returns its version. You can use this to make sure the API is reachable, and matches the expected version.
api.instance <- PublicApi$new()
# Configure HTTP basic authorization: bearerAuth
api.instance$apiClient$username <- 'TODO_YOUR_USERNAME';
api.instance$apiClient$password <- 'TODO_YOUR_PASSWORD';
result <- api.instance$PublicTestGet(expected.result=var.expected.result)
dput(result)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **expected.result** | **character**| The value \&quot;exception\&quot; will trigger an error response. This may be useful for testing wrapper libraries. | [optional] 

### Return type

**object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json



# **PublicTickerGet**
> object PublicTickerGet(instrument.name)

Get ticker for an instrument.

### Example
```R
library(openapi)

var.instrument.name <- 'BTC-PERPETUAL' # character | Instrument name

#Get ticker for an instrument.
api.instance <- PublicApi$new()
# Configure HTTP basic authorization: bearerAuth
api.instance$apiClient$username <- 'TODO_YOUR_USERNAME';
api.instance$apiClient$password <- 'TODO_YOUR_PASSWORD';
result <- api.instance$PublicTickerGet(var.instrument.name)
dput(result)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrument.name** | **character**| Instrument name | 

### Return type

**object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json



# **PublicValidateFieldGet**
> object PublicValidateFieldGet(field, value, value2=var.value2)

Method used to introduce the client software connected to Deribit platform over websocket. Provided data may have an impact on the maintained connection and will be collected for internal statistical purposes. In response, Deribit will also introduce itself.

### Example
```R
library(openapi)

var.field <- 'field_example' # character | Name of the field to be validated, examples: postal_code, date_of_birth
var.value <- 'value_example' # character | Value to be checked
var.value2 <- 'value2_example' # character | Additional value to be compared with

#Method used to introduce the client software connected to Deribit platform over websocket. Provided data may have an impact on the maintained connection and will be collected for internal statistical purposes. In response, Deribit will also introduce itself.
api.instance <- PublicApi$new()
# Configure HTTP basic authorization: bearerAuth
api.instance$apiClient$username <- 'TODO_YOUR_USERNAME';
api.instance$apiClient$password <- 'TODO_YOUR_PASSWORD';
result <- api.instance$PublicValidateFieldGet(var.field, var.value, value2=var.value2)
dput(result)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **field** | **character**| Name of the field to be validated, examples: postal_code, date_of_birth | 
 **value** | **character**| Value to be checked | 
 **value2** | **character**| Additional value to be compared with | [optional] 

### Return type

**object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json



