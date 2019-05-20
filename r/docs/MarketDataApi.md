# MarketDataApi

All URIs are relative to *https://www.deribit.com/api/v2*

Method | HTTP request | Description
------------- | ------------- | -------------
[**PublicGetBookSummaryByCurrencyGet**](MarketDataApi.md#PublicGetBookSummaryByCurrencyGet) | **GET** /public/get_book_summary_by_currency | Retrieves the summary information such as open interest, 24h volume, etc. for all instruments for the currency (optionally filtered by kind).
[**PublicGetBookSummaryByInstrumentGet**](MarketDataApi.md#PublicGetBookSummaryByInstrumentGet) | **GET** /public/get_book_summary_by_instrument | Retrieves the summary information such as open interest, 24h volume, etc. for a specific instrument.
[**PublicGetContractSizeGet**](MarketDataApi.md#PublicGetContractSizeGet) | **GET** /public/get_contract_size | Retrieves contract size of provided instrument.
[**PublicGetCurrenciesGet**](MarketDataApi.md#PublicGetCurrenciesGet) | **GET** /public/get_currencies | Retrieves all cryptocurrencies supported by the API.
[**PublicGetFundingChartDataGet**](MarketDataApi.md#PublicGetFundingChartDataGet) | **GET** /public/get_funding_chart_data | Retrieve the latest user trades that have occurred for PERPETUAL instruments in a specific currency symbol and within given time range.
[**PublicGetHistoricalVolatilityGet**](MarketDataApi.md#PublicGetHistoricalVolatilityGet) | **GET** /public/get_historical_volatility | Provides information about historical volatility for given cryptocurrency.
[**PublicGetIndexGet**](MarketDataApi.md#PublicGetIndexGet) | **GET** /public/get_index | Retrieves the current index price for the instruments, for the selected currency.
[**PublicGetInstrumentsGet**](MarketDataApi.md#PublicGetInstrumentsGet) | **GET** /public/get_instruments | Retrieves available trading instruments. This method can be used to see which instruments are available for trading, or which instruments have existed historically.
[**PublicGetLastSettlementsByCurrencyGet**](MarketDataApi.md#PublicGetLastSettlementsByCurrencyGet) | **GET** /public/get_last_settlements_by_currency | Retrieves historical settlement, delivery and bankruptcy events coming from all instruments within given currency.
[**PublicGetLastSettlementsByInstrumentGet**](MarketDataApi.md#PublicGetLastSettlementsByInstrumentGet) | **GET** /public/get_last_settlements_by_instrument | Retrieves historical public settlement, delivery and bankruptcy events filtered by instrument name.
[**PublicGetLastTradesByCurrencyAndTimeGet**](MarketDataApi.md#PublicGetLastTradesByCurrencyAndTimeGet) | **GET** /public/get_last_trades_by_currency_and_time | Retrieve the latest trades that have occurred for instruments in a specific currency symbol and within given time range.
[**PublicGetLastTradesByCurrencyGet**](MarketDataApi.md#PublicGetLastTradesByCurrencyGet) | **GET** /public/get_last_trades_by_currency | Retrieve the latest trades that have occurred for instruments in a specific currency symbol.
[**PublicGetLastTradesByInstrumentAndTimeGet**](MarketDataApi.md#PublicGetLastTradesByInstrumentAndTimeGet) | **GET** /public/get_last_trades_by_instrument_and_time | Retrieve the latest trades that have occurred for a specific instrument and within given time range.
[**PublicGetLastTradesByInstrumentGet**](MarketDataApi.md#PublicGetLastTradesByInstrumentGet) | **GET** /public/get_last_trades_by_instrument | Retrieve the latest trades that have occurred for a specific instrument.
[**PublicGetOrderBookGet**](MarketDataApi.md#PublicGetOrderBookGet) | **GET** /public/get_order_book | Retrieves the order book, along with other market values for a given instrument.
[**PublicGetTradeVolumesGet**](MarketDataApi.md#PublicGetTradeVolumesGet) | **GET** /public/get_trade_volumes | Retrieves aggregated 24h trade volumes for different instrument types and currencies.
[**PublicGetTradingviewChartDataGet**](MarketDataApi.md#PublicGetTradingviewChartDataGet) | **GET** /public/get_tradingview_chart_data | Publicly available market data used to generate a TradingView candle chart.
[**PublicTickerGet**](MarketDataApi.md#PublicTickerGet) | **GET** /public/ticker | Get ticker for an instrument.


# **PublicGetBookSummaryByCurrencyGet**
> object PublicGetBookSummaryByCurrencyGet(currency, kind=var.kind)

Retrieves the summary information such as open interest, 24h volume, etc. for all instruments for the currency (optionally filtered by kind).

### Example
```R
library(openapi)

var.currency <- 'currency_example' # character | The currency symbol
var.kind <- 'kind_example' # character | Instrument kind, if not provided instruments of all kinds are considered

#Retrieves the summary information such as open interest, 24h volume, etc. for all instruments for the currency (optionally filtered by kind).
api.instance <- MarketDataApi$new()
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
api.instance <- MarketDataApi$new()
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
api.instance <- MarketDataApi$new()
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
api.instance <- MarketDataApi$new()
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
api.instance <- MarketDataApi$new()
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
api.instance <- MarketDataApi$new()
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
api.instance <- MarketDataApi$new()
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
api.instance <- MarketDataApi$new()
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
api.instance <- MarketDataApi$new()
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
api.instance <- MarketDataApi$new()
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
api.instance <- MarketDataApi$new()
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
api.instance <- MarketDataApi$new()
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
api.instance <- MarketDataApi$new()
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
api.instance <- MarketDataApi$new()
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
api.instance <- MarketDataApi$new()
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



# **PublicGetTradeVolumesGet**
> object PublicGetTradeVolumesGet()

Retrieves aggregated 24h trade volumes for different instrument types and currencies.

### Example
```R
library(openapi)


#Retrieves aggregated 24h trade volumes for different instrument types and currencies.
api.instance <- MarketDataApi$new()
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
api.instance <- MarketDataApi$new()
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



# **PublicTickerGet**
> object PublicTickerGet(instrument.name)

Get ticker for an instrument.

### Example
```R
library(openapi)

var.instrument.name <- 'BTC-PERPETUAL' # character | Instrument name

#Get ticker for an instrument.
api.instance <- MarketDataApi$new()
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



