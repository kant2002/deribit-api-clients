# \MarketDataApi

All URIs are relative to *https://www.deribit.com/api/v2*

Method | HTTP request | Description
------------- | ------------- | -------------
[**PublicGetBookSummaryByCurrencyGet**](MarketDataApi.md#PublicGetBookSummaryByCurrencyGet) | **Get** /public/get_book_summary_by_currency | Retrieves the summary information such as open interest, 24h volume, etc. for all instruments for the currency (optionally filtered by kind).
[**PublicGetBookSummaryByInstrumentGet**](MarketDataApi.md#PublicGetBookSummaryByInstrumentGet) | **Get** /public/get_book_summary_by_instrument | Retrieves the summary information such as open interest, 24h volume, etc. for a specific instrument.
[**PublicGetContractSizeGet**](MarketDataApi.md#PublicGetContractSizeGet) | **Get** /public/get_contract_size | Retrieves contract size of provided instrument.
[**PublicGetCurrenciesGet**](MarketDataApi.md#PublicGetCurrenciesGet) | **Get** /public/get_currencies | Retrieves all cryptocurrencies supported by the API.
[**PublicGetFundingChartDataGet**](MarketDataApi.md#PublicGetFundingChartDataGet) | **Get** /public/get_funding_chart_data | Retrieve the latest user trades that have occurred for PERPETUAL instruments in a specific currency symbol and within given time range.
[**PublicGetHistoricalVolatilityGet**](MarketDataApi.md#PublicGetHistoricalVolatilityGet) | **Get** /public/get_historical_volatility | Provides information about historical volatility for given cryptocurrency.
[**PublicGetIndexGet**](MarketDataApi.md#PublicGetIndexGet) | **Get** /public/get_index | Retrieves the current index price for the instruments, for the selected currency.
[**PublicGetInstrumentsGet**](MarketDataApi.md#PublicGetInstrumentsGet) | **Get** /public/get_instruments | Retrieves available trading instruments. This method can be used to see which instruments are available for trading, or which instruments have existed historically.
[**PublicGetLastSettlementsByCurrencyGet**](MarketDataApi.md#PublicGetLastSettlementsByCurrencyGet) | **Get** /public/get_last_settlements_by_currency | Retrieves historical settlement, delivery and bankruptcy events coming from all instruments within given currency.
[**PublicGetLastSettlementsByInstrumentGet**](MarketDataApi.md#PublicGetLastSettlementsByInstrumentGet) | **Get** /public/get_last_settlements_by_instrument | Retrieves historical public settlement, delivery and bankruptcy events filtered by instrument name.
[**PublicGetLastTradesByCurrencyAndTimeGet**](MarketDataApi.md#PublicGetLastTradesByCurrencyAndTimeGet) | **Get** /public/get_last_trades_by_currency_and_time | Retrieve the latest trades that have occurred for instruments in a specific currency symbol and within given time range.
[**PublicGetLastTradesByCurrencyGet**](MarketDataApi.md#PublicGetLastTradesByCurrencyGet) | **Get** /public/get_last_trades_by_currency | Retrieve the latest trades that have occurred for instruments in a specific currency symbol.
[**PublicGetLastTradesByInstrumentAndTimeGet**](MarketDataApi.md#PublicGetLastTradesByInstrumentAndTimeGet) | **Get** /public/get_last_trades_by_instrument_and_time | Retrieve the latest trades that have occurred for a specific instrument and within given time range.
[**PublicGetLastTradesByInstrumentGet**](MarketDataApi.md#PublicGetLastTradesByInstrumentGet) | **Get** /public/get_last_trades_by_instrument | Retrieve the latest trades that have occurred for a specific instrument.
[**PublicGetOrderBookGet**](MarketDataApi.md#PublicGetOrderBookGet) | **Get** /public/get_order_book | Retrieves the order book, along with other market values for a given instrument.
[**PublicGetTradeVolumesGet**](MarketDataApi.md#PublicGetTradeVolumesGet) | **Get** /public/get_trade_volumes | Retrieves aggregated 24h trade volumes for different instrument types and currencies.
[**PublicGetTradingviewChartDataGet**](MarketDataApi.md#PublicGetTradingviewChartDataGet) | **Get** /public/get_tradingview_chart_data | Publicly available market data used to generate a TradingView candle chart.
[**PublicTickerGet**](MarketDataApi.md#PublicTickerGet) | **Get** /public/ticker | Get ticker for an instrument.



## PublicGetBookSummaryByCurrencyGet

> map[string]interface{} PublicGetBookSummaryByCurrencyGet(ctx, currency, optional)
Retrieves the summary information such as open interest, 24h volume, etc. for all instruments for the currency (optionally filtered by kind).

### Required Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
**ctx** | **context.Context** | context for authentication, logging, cancellation, deadlines, tracing, etc.
**currency** | **string**| The currency symbol | 
 **optional** | ***PublicGetBookSummaryByCurrencyGetOpts** | optional parameters | nil if no parameters

### Optional Parameters

Optional parameters are passed through a pointer to a PublicGetBookSummaryByCurrencyGetOpts struct


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------

 **kind** | **optional.String**| Instrument kind, if not provided instruments of all kinds are considered | 

### Return type

[**map[string]interface{}**](map[string]interface{}.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PublicGetBookSummaryByInstrumentGet

> map[string]interface{} PublicGetBookSummaryByInstrumentGet(ctx, instrumentName)
Retrieves the summary information such as open interest, 24h volume, etc. for a specific instrument.

### Required Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
**ctx** | **context.Context** | context for authentication, logging, cancellation, deadlines, tracing, etc.
**instrumentName** | **string**| Instrument name | 

### Return type

[**map[string]interface{}**](map[string]interface{}.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PublicGetContractSizeGet

> map[string]interface{} PublicGetContractSizeGet(ctx, instrumentName)
Retrieves contract size of provided instrument.

### Required Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
**ctx** | **context.Context** | context for authentication, logging, cancellation, deadlines, tracing, etc.
**instrumentName** | **string**| Instrument name | 

### Return type

[**map[string]interface{}**](map[string]interface{}.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PublicGetCurrenciesGet

> map[string]interface{} PublicGetCurrenciesGet(ctx, )
Retrieves all cryptocurrencies supported by the API.

### Required Parameters

This endpoint does not need any parameter.

### Return type

[**map[string]interface{}**](map[string]interface{}.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PublicGetFundingChartDataGet

> map[string]interface{} PublicGetFundingChartDataGet(ctx, instrumentName, optional)
Retrieve the latest user trades that have occurred for PERPETUAL instruments in a specific currency symbol and within given time range.

### Required Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
**ctx** | **context.Context** | context for authentication, logging, cancellation, deadlines, tracing, etc.
**instrumentName** | **string**| Instrument name | 
 **optional** | ***PublicGetFundingChartDataGetOpts** | optional parameters | nil if no parameters

### Optional Parameters

Optional parameters are passed through a pointer to a PublicGetFundingChartDataGetOpts struct


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------

 **length** | **optional.String**| Specifies time period. &#x60;8h&#x60; - 8 hours, &#x60;24h&#x60; - 24 hours | 

### Return type

[**map[string]interface{}**](map[string]interface{}.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PublicGetHistoricalVolatilityGet

> map[string]interface{} PublicGetHistoricalVolatilityGet(ctx, currency)
Provides information about historical volatility for given cryptocurrency.

### Required Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
**ctx** | **context.Context** | context for authentication, logging, cancellation, deadlines, tracing, etc.
**currency** | **string**| The currency symbol | 

### Return type

[**map[string]interface{}**](map[string]interface{}.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PublicGetIndexGet

> map[string]interface{} PublicGetIndexGet(ctx, currency)
Retrieves the current index price for the instruments, for the selected currency.

### Required Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
**ctx** | **context.Context** | context for authentication, logging, cancellation, deadlines, tracing, etc.
**currency** | **string**| The currency symbol | 

### Return type

[**map[string]interface{}**](map[string]interface{}.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PublicGetInstrumentsGet

> map[string]interface{} PublicGetInstrumentsGet(ctx, currency, optional)
Retrieves available trading instruments. This method can be used to see which instruments are available for trading, or which instruments have existed historically.

### Required Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
**ctx** | **context.Context** | context for authentication, logging, cancellation, deadlines, tracing, etc.
**currency** | **string**| The currency symbol | 
 **optional** | ***PublicGetInstrumentsGetOpts** | optional parameters | nil if no parameters

### Optional Parameters

Optional parameters are passed through a pointer to a PublicGetInstrumentsGetOpts struct


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------

 **kind** | **optional.String**| Instrument kind, if not provided instruments of all kinds are considered | 
 **expired** | **optional.Bool**| Set to true to show expired instruments instead of active ones. | [default to false]

### Return type

[**map[string]interface{}**](map[string]interface{}.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PublicGetLastSettlementsByCurrencyGet

> map[string]interface{} PublicGetLastSettlementsByCurrencyGet(ctx, currency, optional)
Retrieves historical settlement, delivery and bankruptcy events coming from all instruments within given currency.

### Required Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
**ctx** | **context.Context** | context for authentication, logging, cancellation, deadlines, tracing, etc.
**currency** | **string**| The currency symbol | 
 **optional** | ***PublicGetLastSettlementsByCurrencyGetOpts** | optional parameters | nil if no parameters

### Optional Parameters

Optional parameters are passed through a pointer to a PublicGetLastSettlementsByCurrencyGetOpts struct


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------

 **type_** | **optional.String**| Settlement type | 
 **count** | **optional.Int32**| Number of requested items, default - &#x60;20&#x60; | 
 **continuation** | **optional.String**| Continuation token for pagination | 
 **searchStartTimestamp** | **optional.Int32**| The latest timestamp to return result for | 

### Return type

[**map[string]interface{}**](map[string]interface{}.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PublicGetLastSettlementsByInstrumentGet

> map[string]interface{} PublicGetLastSettlementsByInstrumentGet(ctx, instrumentName, optional)
Retrieves historical public settlement, delivery and bankruptcy events filtered by instrument name.

### Required Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
**ctx** | **context.Context** | context for authentication, logging, cancellation, deadlines, tracing, etc.
**instrumentName** | **string**| Instrument name | 
 **optional** | ***PublicGetLastSettlementsByInstrumentGetOpts** | optional parameters | nil if no parameters

### Optional Parameters

Optional parameters are passed through a pointer to a PublicGetLastSettlementsByInstrumentGetOpts struct


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------

 **type_** | **optional.String**| Settlement type | 
 **count** | **optional.Int32**| Number of requested items, default - &#x60;20&#x60; | 
 **continuation** | **optional.String**| Continuation token for pagination | 
 **searchStartTimestamp** | **optional.Int32**| The latest timestamp to return result for | 

### Return type

[**map[string]interface{}**](map[string]interface{}.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PublicGetLastTradesByCurrencyAndTimeGet

> map[string]interface{} PublicGetLastTradesByCurrencyAndTimeGet(ctx, currency, startTimestamp, endTimestamp, optional)
Retrieve the latest trades that have occurred for instruments in a specific currency symbol and within given time range.

### Required Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
**ctx** | **context.Context** | context for authentication, logging, cancellation, deadlines, tracing, etc.
**currency** | **string**| The currency symbol | 
**startTimestamp** | **int32**| The earliest timestamp to return result for | 
**endTimestamp** | **int32**| The most recent timestamp to return result for | 
 **optional** | ***PublicGetLastTradesByCurrencyAndTimeGetOpts** | optional parameters | nil if no parameters

### Optional Parameters

Optional parameters are passed through a pointer to a PublicGetLastTradesByCurrencyAndTimeGetOpts struct


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------



 **kind** | **optional.String**| Instrument kind, if not provided instruments of all kinds are considered | 
 **count** | **optional.Int32**| Number of requested items, default - &#x60;10&#x60; | 
 **includeOld** | **optional.Bool**| Include trades older than 7 days, default - &#x60;false&#x60; | 
 **sorting** | **optional.String**| Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) | 

### Return type

[**map[string]interface{}**](map[string]interface{}.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PublicGetLastTradesByCurrencyGet

> map[string]interface{} PublicGetLastTradesByCurrencyGet(ctx, currency, optional)
Retrieve the latest trades that have occurred for instruments in a specific currency symbol.

### Required Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
**ctx** | **context.Context** | context for authentication, logging, cancellation, deadlines, tracing, etc.
**currency** | **string**| The currency symbol | 
 **optional** | ***PublicGetLastTradesByCurrencyGetOpts** | optional parameters | nil if no parameters

### Optional Parameters

Optional parameters are passed through a pointer to a PublicGetLastTradesByCurrencyGetOpts struct


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------

 **kind** | **optional.String**| Instrument kind, if not provided instruments of all kinds are considered | 
 **startId** | **optional.String**| The ID number of the first trade to be returned | 
 **endId** | **optional.String**| The ID number of the last trade to be returned | 
 **count** | **optional.Int32**| Number of requested items, default - &#x60;10&#x60; | 
 **includeOld** | **optional.Bool**| Include trades older than 7 days, default - &#x60;false&#x60; | 
 **sorting** | **optional.String**| Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) | 

### Return type

[**map[string]interface{}**](map[string]interface{}.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PublicGetLastTradesByInstrumentAndTimeGet

> map[string]interface{} PublicGetLastTradesByInstrumentAndTimeGet(ctx, instrumentName, startTimestamp, endTimestamp, optional)
Retrieve the latest trades that have occurred for a specific instrument and within given time range.

### Required Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
**ctx** | **context.Context** | context for authentication, logging, cancellation, deadlines, tracing, etc.
**instrumentName** | **string**| Instrument name | 
**startTimestamp** | **int32**| The earliest timestamp to return result for | 
**endTimestamp** | **int32**| The most recent timestamp to return result for | 
 **optional** | ***PublicGetLastTradesByInstrumentAndTimeGetOpts** | optional parameters | nil if no parameters

### Optional Parameters

Optional parameters are passed through a pointer to a PublicGetLastTradesByInstrumentAndTimeGetOpts struct


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------



 **count** | **optional.Int32**| Number of requested items, default - &#x60;10&#x60; | 
 **includeOld** | **optional.Bool**| Include trades older than 7 days, default - &#x60;false&#x60; | 
 **sorting** | **optional.String**| Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) | 

### Return type

[**map[string]interface{}**](map[string]interface{}.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PublicGetLastTradesByInstrumentGet

> map[string]interface{} PublicGetLastTradesByInstrumentGet(ctx, instrumentName, optional)
Retrieve the latest trades that have occurred for a specific instrument.

### Required Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
**ctx** | **context.Context** | context for authentication, logging, cancellation, deadlines, tracing, etc.
**instrumentName** | **string**| Instrument name | 
 **optional** | ***PublicGetLastTradesByInstrumentGetOpts** | optional parameters | nil if no parameters

### Optional Parameters

Optional parameters are passed through a pointer to a PublicGetLastTradesByInstrumentGetOpts struct


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------

 **startSeq** | **optional.Int32**| The sequence number of the first trade to be returned | 
 **endSeq** | **optional.Int32**| The sequence number of the last trade to be returned | 
 **count** | **optional.Int32**| Number of requested items, default - &#x60;10&#x60; | 
 **includeOld** | **optional.Bool**| Include trades older than 7 days, default - &#x60;false&#x60; | 
 **sorting** | **optional.String**| Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) | 

### Return type

[**map[string]interface{}**](map[string]interface{}.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PublicGetOrderBookGet

> map[string]interface{} PublicGetOrderBookGet(ctx, instrumentName, optional)
Retrieves the order book, along with other market values for a given instrument.

### Required Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
**ctx** | **context.Context** | context for authentication, logging, cancellation, deadlines, tracing, etc.
**instrumentName** | **string**| The instrument name for which to retrieve the order book, see [&#x60;getinstruments&#x60;](#getinstruments) to obtain instrument names. | 
 **optional** | ***PublicGetOrderBookGetOpts** | optional parameters | nil if no parameters

### Optional Parameters

Optional parameters are passed through a pointer to a PublicGetOrderBookGetOpts struct


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------

 **depth** | **optional.Float32**| The number of entries to return for bids and asks. | 

### Return type

[**map[string]interface{}**](map[string]interface{}.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PublicGetTradeVolumesGet

> map[string]interface{} PublicGetTradeVolumesGet(ctx, )
Retrieves aggregated 24h trade volumes for different instrument types and currencies.

### Required Parameters

This endpoint does not need any parameter.

### Return type

[**map[string]interface{}**](map[string]interface{}.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PublicGetTradingviewChartDataGet

> map[string]interface{} PublicGetTradingviewChartDataGet(ctx, instrumentName, startTimestamp, endTimestamp)
Publicly available market data used to generate a TradingView candle chart.

### Required Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
**ctx** | **context.Context** | context for authentication, logging, cancellation, deadlines, tracing, etc.
**instrumentName** | **string**| Instrument name | 
**startTimestamp** | **int32**| The earliest timestamp to return result for | 
**endTimestamp** | **int32**| The most recent timestamp to return result for | 

### Return type

[**map[string]interface{}**](map[string]interface{}.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PublicTickerGet

> map[string]interface{} PublicTickerGet(ctx, instrumentName)
Get ticker for an instrument.

### Required Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
**ctx** | **context.Context** | context for authentication, logging, cancellation, deadlines, tracing, etc.
**instrumentName** | **string**| Instrument name | 

### Return type

[**map[string]interface{}**](map[string]interface{}.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)

