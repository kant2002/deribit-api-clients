# OAIMarketDataApi

All URIs are relative to *https://www.deribit.com/api/v2*

Method | HTTP request | Description
------------- | ------------- | -------------
[**publicGetBookSummaryByCurrencyGet**](OAIMarketDataApi.md#publicgetbooksummarybycurrencyget) | **GET** /public/get_book_summary_by_currency | Retrieves the summary information such as open interest, 24h volume, etc. for all instruments for the currency (optionally filtered by kind).
[**publicGetBookSummaryByInstrumentGet**](OAIMarketDataApi.md#publicgetbooksummarybyinstrumentget) | **GET** /public/get_book_summary_by_instrument | Retrieves the summary information such as open interest, 24h volume, etc. for a specific instrument.
[**publicGetContractSizeGet**](OAIMarketDataApi.md#publicgetcontractsizeget) | **GET** /public/get_contract_size | Retrieves contract size of provided instrument.
[**publicGetCurrenciesGet**](OAIMarketDataApi.md#publicgetcurrenciesget) | **GET** /public/get_currencies | Retrieves all cryptocurrencies supported by the API.
[**publicGetFundingChartDataGet**](OAIMarketDataApi.md#publicgetfundingchartdataget) | **GET** /public/get_funding_chart_data | Retrieve the latest user trades that have occurred for PERPETUAL instruments in a specific currency symbol and within given time range.
[**publicGetHistoricalVolatilityGet**](OAIMarketDataApi.md#publicgethistoricalvolatilityget) | **GET** /public/get_historical_volatility | Provides information about historical volatility for given cryptocurrency.
[**publicGetIndexGet**](OAIMarketDataApi.md#publicgetindexget) | **GET** /public/get_index | Retrieves the current index price for the instruments, for the selected currency.
[**publicGetInstrumentsGet**](OAIMarketDataApi.md#publicgetinstrumentsget) | **GET** /public/get_instruments | Retrieves available trading instruments. This method can be used to see which instruments are available for trading, or which instruments have existed historically.
[**publicGetLastSettlementsByCurrencyGet**](OAIMarketDataApi.md#publicgetlastsettlementsbycurrencyget) | **GET** /public/get_last_settlements_by_currency | Retrieves historical settlement, delivery and bankruptcy events coming from all instruments within given currency.
[**publicGetLastSettlementsByInstrumentGet**](OAIMarketDataApi.md#publicgetlastsettlementsbyinstrumentget) | **GET** /public/get_last_settlements_by_instrument | Retrieves historical public settlement, delivery and bankruptcy events filtered by instrument name.
[**publicGetLastTradesByCurrencyAndTimeGet**](OAIMarketDataApi.md#publicgetlasttradesbycurrencyandtimeget) | **GET** /public/get_last_trades_by_currency_and_time | Retrieve the latest trades that have occurred for instruments in a specific currency symbol and within given time range.
[**publicGetLastTradesByCurrencyGet**](OAIMarketDataApi.md#publicgetlasttradesbycurrencyget) | **GET** /public/get_last_trades_by_currency | Retrieve the latest trades that have occurred for instruments in a specific currency symbol.
[**publicGetLastTradesByInstrumentAndTimeGet**](OAIMarketDataApi.md#publicgetlasttradesbyinstrumentandtimeget) | **GET** /public/get_last_trades_by_instrument_and_time | Retrieve the latest trades that have occurred for a specific instrument and within given time range.
[**publicGetLastTradesByInstrumentGet**](OAIMarketDataApi.md#publicgetlasttradesbyinstrumentget) | **GET** /public/get_last_trades_by_instrument | Retrieve the latest trades that have occurred for a specific instrument.
[**publicGetOrderBookGet**](OAIMarketDataApi.md#publicgetorderbookget) | **GET** /public/get_order_book | Retrieves the order book, along with other market values for a given instrument.
[**publicGetTradeVolumesGet**](OAIMarketDataApi.md#publicgettradevolumesget) | **GET** /public/get_trade_volumes | Retrieves aggregated 24h trade volumes for different instrument types and currencies.
[**publicGetTradingviewChartDataGet**](OAIMarketDataApi.md#publicgettradingviewchartdataget) | **GET** /public/get_tradingview_chart_data | Publicly available market data used to generate a TradingView candle chart.
[**publicTickerGet**](OAIMarketDataApi.md#publictickerget) | **GET** /public/ticker | Get ticker for an instrument.


# **publicGetBookSummaryByCurrencyGet**
```objc
-(NSURLSessionTask*) publicGetBookSummaryByCurrencyGetWithCurrency: (NSString*) currency
    kind: (NSString*) kind
        completionHandler: (void (^)(NSObject* output, NSError* error)) handler;
```

Retrieves the summary information such as open interest, 24h volume, etc. for all instruments for the currency (optionally filtered by kind).

### Example 
```objc
OAIDefaultConfiguration *apiConfig = [OAIDefaultConfiguration sharedConfig];
// Configure HTTP basic authorization (authentication scheme: bearerAuth)
[apiConfig setUsername:@"YOUR_USERNAME"];
[apiConfig setPassword:@"YOUR_PASSWORD"];


NSString* currency = @"currency_example"; // The currency symbol
NSString* kind = @"kind_example"; // Instrument kind, if not provided instruments of all kinds are considered (optional)

OAIMarketDataApi*apiInstance = [[OAIMarketDataApi alloc] init];

// Retrieves the summary information such as open interest, 24h volume, etc. for all instruments for the currency (optionally filtered by kind).
[apiInstance publicGetBookSummaryByCurrencyGetWithCurrency:currency
              kind:kind
          completionHandler: ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAIMarketDataApi->publicGetBookSummaryByCurrencyGet: %@", error);
                        }
                    }];
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **NSString***| The currency symbol | 
 **kind** | **NSString***| Instrument kind, if not provided instruments of all kinds are considered | [optional] 

### Return type

**NSObject***

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **publicGetBookSummaryByInstrumentGet**
```objc
-(NSURLSessionTask*) publicGetBookSummaryByInstrumentGetWithInstrumentName: (NSString*) instrumentName
        completionHandler: (void (^)(NSObject* output, NSError* error)) handler;
```

Retrieves the summary information such as open interest, 24h volume, etc. for a specific instrument.

### Example 
```objc
OAIDefaultConfiguration *apiConfig = [OAIDefaultConfiguration sharedConfig];
// Configure HTTP basic authorization (authentication scheme: bearerAuth)
[apiConfig setUsername:@"YOUR_USERNAME"];
[apiConfig setPassword:@"YOUR_PASSWORD"];


NSString* instrumentName = BTC-PERPETUAL; // Instrument name

OAIMarketDataApi*apiInstance = [[OAIMarketDataApi alloc] init];

// Retrieves the summary information such as open interest, 24h volume, etc. for a specific instrument.
[apiInstance publicGetBookSummaryByInstrumentGetWithInstrumentName:instrumentName
          completionHandler: ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAIMarketDataApi->publicGetBookSummaryByInstrumentGet: %@", error);
                        }
                    }];
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrumentName** | **NSString***| Instrument name | 

### Return type

**NSObject***

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **publicGetContractSizeGet**
```objc
-(NSURLSessionTask*) publicGetContractSizeGetWithInstrumentName: (NSString*) instrumentName
        completionHandler: (void (^)(NSObject* output, NSError* error)) handler;
```

Retrieves contract size of provided instrument.

### Example 
```objc
OAIDefaultConfiguration *apiConfig = [OAIDefaultConfiguration sharedConfig];
// Configure HTTP basic authorization (authentication scheme: bearerAuth)
[apiConfig setUsername:@"YOUR_USERNAME"];
[apiConfig setPassword:@"YOUR_PASSWORD"];


NSString* instrumentName = BTC-PERPETUAL; // Instrument name

OAIMarketDataApi*apiInstance = [[OAIMarketDataApi alloc] init];

// Retrieves contract size of provided instrument.
[apiInstance publicGetContractSizeGetWithInstrumentName:instrumentName
          completionHandler: ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAIMarketDataApi->publicGetContractSizeGet: %@", error);
                        }
                    }];
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrumentName** | **NSString***| Instrument name | 

### Return type

**NSObject***

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **publicGetCurrenciesGet**
```objc
-(NSURLSessionTask*) publicGetCurrenciesGetWithCompletionHandler: 
        (void (^)(NSObject* output, NSError* error)) handler;
```

Retrieves all cryptocurrencies supported by the API.

### Example 
```objc
OAIDefaultConfiguration *apiConfig = [OAIDefaultConfiguration sharedConfig];
// Configure HTTP basic authorization (authentication scheme: bearerAuth)
[apiConfig setUsername:@"YOUR_USERNAME"];
[apiConfig setPassword:@"YOUR_PASSWORD"];



OAIMarketDataApi*apiInstance = [[OAIMarketDataApi alloc] init];

// Retrieves all cryptocurrencies supported by the API.
[apiInstance publicGetCurrenciesGetWithCompletionHandler: 
          ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAIMarketDataApi->publicGetCurrenciesGet: %@", error);
                        }
                    }];
```

### Parameters
This endpoint does not need any parameter.

### Return type

**NSObject***

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **publicGetFundingChartDataGet**
```objc
-(NSURLSessionTask*) publicGetFundingChartDataGetWithInstrumentName: (NSString*) instrumentName
    length: (NSString*) length
        completionHandler: (void (^)(NSObject* output, NSError* error)) handler;
```

Retrieve the latest user trades that have occurred for PERPETUAL instruments in a specific currency symbol and within given time range.

### Example 
```objc
OAIDefaultConfiguration *apiConfig = [OAIDefaultConfiguration sharedConfig];
// Configure HTTP basic authorization (authentication scheme: bearerAuth)
[apiConfig setUsername:@"YOUR_USERNAME"];
[apiConfig setPassword:@"YOUR_PASSWORD"];


NSString* instrumentName = BTC-PERPETUAL; // Instrument name
NSString* length = @"length_example"; // Specifies time period. `8h` - 8 hours, `24h` - 24 hours (optional)

OAIMarketDataApi*apiInstance = [[OAIMarketDataApi alloc] init];

// Retrieve the latest user trades that have occurred for PERPETUAL instruments in a specific currency symbol and within given time range.
[apiInstance publicGetFundingChartDataGetWithInstrumentName:instrumentName
              length:length
          completionHandler: ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAIMarketDataApi->publicGetFundingChartDataGet: %@", error);
                        }
                    }];
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrumentName** | **NSString***| Instrument name | 
 **length** | **NSString***| Specifies time period. &#x60;8h&#x60; - 8 hours, &#x60;24h&#x60; - 24 hours | [optional] 

### Return type

**NSObject***

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **publicGetHistoricalVolatilityGet**
```objc
-(NSURLSessionTask*) publicGetHistoricalVolatilityGetWithCurrency: (NSString*) currency
        completionHandler: (void (^)(NSObject* output, NSError* error)) handler;
```

Provides information about historical volatility for given cryptocurrency.

### Example 
```objc
OAIDefaultConfiguration *apiConfig = [OAIDefaultConfiguration sharedConfig];
// Configure HTTP basic authorization (authentication scheme: bearerAuth)
[apiConfig setUsername:@"YOUR_USERNAME"];
[apiConfig setPassword:@"YOUR_PASSWORD"];


NSString* currency = @"currency_example"; // The currency symbol

OAIMarketDataApi*apiInstance = [[OAIMarketDataApi alloc] init];

// Provides information about historical volatility for given cryptocurrency.
[apiInstance publicGetHistoricalVolatilityGetWithCurrency:currency
          completionHandler: ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAIMarketDataApi->publicGetHistoricalVolatilityGet: %@", error);
                        }
                    }];
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **NSString***| The currency symbol | 

### Return type

**NSObject***

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **publicGetIndexGet**
```objc
-(NSURLSessionTask*) publicGetIndexGetWithCurrency: (NSString*) currency
        completionHandler: (void (^)(NSObject* output, NSError* error)) handler;
```

Retrieves the current index price for the instruments, for the selected currency.

### Example 
```objc
OAIDefaultConfiguration *apiConfig = [OAIDefaultConfiguration sharedConfig];
// Configure HTTP basic authorization (authentication scheme: bearerAuth)
[apiConfig setUsername:@"YOUR_USERNAME"];
[apiConfig setPassword:@"YOUR_PASSWORD"];


NSString* currency = @"currency_example"; // The currency symbol

OAIMarketDataApi*apiInstance = [[OAIMarketDataApi alloc] init];

// Retrieves the current index price for the instruments, for the selected currency.
[apiInstance publicGetIndexGetWithCurrency:currency
          completionHandler: ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAIMarketDataApi->publicGetIndexGet: %@", error);
                        }
                    }];
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **NSString***| The currency symbol | 

### Return type

**NSObject***

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **publicGetInstrumentsGet**
```objc
-(NSURLSessionTask*) publicGetInstrumentsGetWithCurrency: (NSString*) currency
    kind: (NSString*) kind
    expired: (NSNumber*) expired
        completionHandler: (void (^)(NSObject* output, NSError* error)) handler;
```

Retrieves available trading instruments. This method can be used to see which instruments are available for trading, or which instruments have existed historically.

### Example 
```objc
OAIDefaultConfiguration *apiConfig = [OAIDefaultConfiguration sharedConfig];
// Configure HTTP basic authorization (authentication scheme: bearerAuth)
[apiConfig setUsername:@"YOUR_USERNAME"];
[apiConfig setPassword:@"YOUR_PASSWORD"];


NSString* currency = @"currency_example"; // The currency symbol
NSString* kind = @"kind_example"; // Instrument kind, if not provided instruments of all kinds are considered (optional)
NSNumber* expired = @(NO); // Set to true to show expired instruments instead of active ones. (optional) (default to @(NO))

OAIMarketDataApi*apiInstance = [[OAIMarketDataApi alloc] init];

// Retrieves available trading instruments. This method can be used to see which instruments are available for trading, or which instruments have existed historically.
[apiInstance publicGetInstrumentsGetWithCurrency:currency
              kind:kind
              expired:expired
          completionHandler: ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAIMarketDataApi->publicGetInstrumentsGet: %@", error);
                        }
                    }];
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **NSString***| The currency symbol | 
 **kind** | **NSString***| Instrument kind, if not provided instruments of all kinds are considered | [optional] 
 **expired** | **NSNumber***| Set to true to show expired instruments instead of active ones. | [optional] [default to @(NO)]

### Return type

**NSObject***

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **publicGetLastSettlementsByCurrencyGet**
```objc
-(NSURLSessionTask*) publicGetLastSettlementsByCurrencyGetWithCurrency: (NSString*) currency
    type: (NSString*) type
    count: (NSNumber*) count
    continuation: (NSString*) continuation
    searchStartTimestamp: (NSNumber*) searchStartTimestamp
        completionHandler: (void (^)(NSObject* output, NSError* error)) handler;
```

Retrieves historical settlement, delivery and bankruptcy events coming from all instruments within given currency.

### Example 
```objc
OAIDefaultConfiguration *apiConfig = [OAIDefaultConfiguration sharedConfig];
// Configure HTTP basic authorization (authentication scheme: bearerAuth)
[apiConfig setUsername:@"YOUR_USERNAME"];
[apiConfig setPassword:@"YOUR_PASSWORD"];


NSString* currency = @"currency_example"; // The currency symbol
NSString* type = @"type_example"; // Settlement type (optional)
NSNumber* count = @56; // Number of requested items, default - `20` (optional)
NSString* continuation = xY7T6cutS3t2B9YtaDkE6TS379oKnkzTvmEDUnEUP2Msa9xKWNNaT; // Continuation token for pagination (optional)
NSNumber* searchStartTimestamp = 1536569522277; // The latest timestamp to return result for (optional)

OAIMarketDataApi*apiInstance = [[OAIMarketDataApi alloc] init];

// Retrieves historical settlement, delivery and bankruptcy events coming from all instruments within given currency.
[apiInstance publicGetLastSettlementsByCurrencyGetWithCurrency:currency
              type:type
              count:count
              continuation:continuation
              searchStartTimestamp:searchStartTimestamp
          completionHandler: ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAIMarketDataApi->publicGetLastSettlementsByCurrencyGet: %@", error);
                        }
                    }];
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **NSString***| The currency symbol | 
 **type** | **NSString***| Settlement type | [optional] 
 **count** | **NSNumber***| Number of requested items, default - &#x60;20&#x60; | [optional] 
 **continuation** | **NSString***| Continuation token for pagination | [optional] 
 **searchStartTimestamp** | **NSNumber***| The latest timestamp to return result for | [optional] 

### Return type

**NSObject***

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **publicGetLastSettlementsByInstrumentGet**
```objc
-(NSURLSessionTask*) publicGetLastSettlementsByInstrumentGetWithInstrumentName: (NSString*) instrumentName
    type: (NSString*) type
    count: (NSNumber*) count
    continuation: (NSString*) continuation
    searchStartTimestamp: (NSNumber*) searchStartTimestamp
        completionHandler: (void (^)(NSObject* output, NSError* error)) handler;
```

Retrieves historical public settlement, delivery and bankruptcy events filtered by instrument name.

### Example 
```objc
OAIDefaultConfiguration *apiConfig = [OAIDefaultConfiguration sharedConfig];
// Configure HTTP basic authorization (authentication scheme: bearerAuth)
[apiConfig setUsername:@"YOUR_USERNAME"];
[apiConfig setPassword:@"YOUR_PASSWORD"];


NSString* instrumentName = BTC-PERPETUAL; // Instrument name
NSString* type = @"type_example"; // Settlement type (optional)
NSNumber* count = @56; // Number of requested items, default - `20` (optional)
NSString* continuation = xY7T6cutS3t2B9YtaDkE6TS379oKnkzTvmEDUnEUP2Msa9xKWNNaT; // Continuation token for pagination (optional)
NSNumber* searchStartTimestamp = 1536569522277; // The latest timestamp to return result for (optional)

OAIMarketDataApi*apiInstance = [[OAIMarketDataApi alloc] init];

// Retrieves historical public settlement, delivery and bankruptcy events filtered by instrument name.
[apiInstance publicGetLastSettlementsByInstrumentGetWithInstrumentName:instrumentName
              type:type
              count:count
              continuation:continuation
              searchStartTimestamp:searchStartTimestamp
          completionHandler: ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAIMarketDataApi->publicGetLastSettlementsByInstrumentGet: %@", error);
                        }
                    }];
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrumentName** | **NSString***| Instrument name | 
 **type** | **NSString***| Settlement type | [optional] 
 **count** | **NSNumber***| Number of requested items, default - &#x60;20&#x60; | [optional] 
 **continuation** | **NSString***| Continuation token for pagination | [optional] 
 **searchStartTimestamp** | **NSNumber***| The latest timestamp to return result for | [optional] 

### Return type

**NSObject***

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **publicGetLastTradesByCurrencyAndTimeGet**
```objc
-(NSURLSessionTask*) publicGetLastTradesByCurrencyAndTimeGetWithCurrency: (NSString*) currency
    startTimestamp: (NSNumber*) startTimestamp
    endTimestamp: (NSNumber*) endTimestamp
    kind: (NSString*) kind
    count: (NSNumber*) count
    includeOld: (NSNumber*) includeOld
    sorting: (NSString*) sorting
        completionHandler: (void (^)(NSObject* output, NSError* error)) handler;
```

Retrieve the latest trades that have occurred for instruments in a specific currency symbol and within given time range.

### Example 
```objc
OAIDefaultConfiguration *apiConfig = [OAIDefaultConfiguration sharedConfig];
// Configure HTTP basic authorization (authentication scheme: bearerAuth)
[apiConfig setUsername:@"YOUR_USERNAME"];
[apiConfig setPassword:@"YOUR_PASSWORD"];


NSString* currency = @"currency_example"; // The currency symbol
NSNumber* startTimestamp = 1536569522277; // The earliest timestamp to return result for
NSNumber* endTimestamp = 1536569522277; // The most recent timestamp to return result for
NSString* kind = @"kind_example"; // Instrument kind, if not provided instruments of all kinds are considered (optional)
NSNumber* count = @56; // Number of requested items, default - `10` (optional)
NSNumber* includeOld = @56; // Include trades older than 7 days, default - `false` (optional)
NSString* sorting = @"sorting_example"; // Direction of results sorting (`default` value means no sorting, results will be returned in order in which they left the database) (optional)

OAIMarketDataApi*apiInstance = [[OAIMarketDataApi alloc] init];

// Retrieve the latest trades that have occurred for instruments in a specific currency symbol and within given time range.
[apiInstance publicGetLastTradesByCurrencyAndTimeGetWithCurrency:currency
              startTimestamp:startTimestamp
              endTimestamp:endTimestamp
              kind:kind
              count:count
              includeOld:includeOld
              sorting:sorting
          completionHandler: ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAIMarketDataApi->publicGetLastTradesByCurrencyAndTimeGet: %@", error);
                        }
                    }];
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **NSString***| The currency symbol | 
 **startTimestamp** | **NSNumber***| The earliest timestamp to return result for | 
 **endTimestamp** | **NSNumber***| The most recent timestamp to return result for | 
 **kind** | **NSString***| Instrument kind, if not provided instruments of all kinds are considered | [optional] 
 **count** | **NSNumber***| Number of requested items, default - &#x60;10&#x60; | [optional] 
 **includeOld** | **NSNumber***| Include trades older than 7 days, default - &#x60;false&#x60; | [optional] 
 **sorting** | **NSString***| Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) | [optional] 

### Return type

**NSObject***

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **publicGetLastTradesByCurrencyGet**
```objc
-(NSURLSessionTask*) publicGetLastTradesByCurrencyGetWithCurrency: (NSString*) currency
    kind: (NSString*) kind
    startId: (NSString*) startId
    endId: (NSString*) endId
    count: (NSNumber*) count
    includeOld: (NSNumber*) includeOld
    sorting: (NSString*) sorting
        completionHandler: (void (^)(NSObject* output, NSError* error)) handler;
```

Retrieve the latest trades that have occurred for instruments in a specific currency symbol.

### Example 
```objc
OAIDefaultConfiguration *apiConfig = [OAIDefaultConfiguration sharedConfig];
// Configure HTTP basic authorization (authentication scheme: bearerAuth)
[apiConfig setUsername:@"YOUR_USERNAME"];
[apiConfig setPassword:@"YOUR_PASSWORD"];


NSString* currency = @"currency_example"; // The currency symbol
NSString* kind = @"kind_example"; // Instrument kind, if not provided instruments of all kinds are considered (optional)
NSString* startId = @"startId_example"; // The ID number of the first trade to be returned (optional)
NSString* endId = @"endId_example"; // The ID number of the last trade to be returned (optional)
NSNumber* count = @56; // Number of requested items, default - `10` (optional)
NSNumber* includeOld = @56; // Include trades older than 7 days, default - `false` (optional)
NSString* sorting = @"sorting_example"; // Direction of results sorting (`default` value means no sorting, results will be returned in order in which they left the database) (optional)

OAIMarketDataApi*apiInstance = [[OAIMarketDataApi alloc] init];

// Retrieve the latest trades that have occurred for instruments in a specific currency symbol.
[apiInstance publicGetLastTradesByCurrencyGetWithCurrency:currency
              kind:kind
              startId:startId
              endId:endId
              count:count
              includeOld:includeOld
              sorting:sorting
          completionHandler: ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAIMarketDataApi->publicGetLastTradesByCurrencyGet: %@", error);
                        }
                    }];
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **NSString***| The currency symbol | 
 **kind** | **NSString***| Instrument kind, if not provided instruments of all kinds are considered | [optional] 
 **startId** | **NSString***| The ID number of the first trade to be returned | [optional] 
 **endId** | **NSString***| The ID number of the last trade to be returned | [optional] 
 **count** | **NSNumber***| Number of requested items, default - &#x60;10&#x60; | [optional] 
 **includeOld** | **NSNumber***| Include trades older than 7 days, default - &#x60;false&#x60; | [optional] 
 **sorting** | **NSString***| Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) | [optional] 

### Return type

**NSObject***

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **publicGetLastTradesByInstrumentAndTimeGet**
```objc
-(NSURLSessionTask*) publicGetLastTradesByInstrumentAndTimeGetWithInstrumentName: (NSString*) instrumentName
    startTimestamp: (NSNumber*) startTimestamp
    endTimestamp: (NSNumber*) endTimestamp
    count: (NSNumber*) count
    includeOld: (NSNumber*) includeOld
    sorting: (NSString*) sorting
        completionHandler: (void (^)(NSObject* output, NSError* error)) handler;
```

Retrieve the latest trades that have occurred for a specific instrument and within given time range.

### Example 
```objc
OAIDefaultConfiguration *apiConfig = [OAIDefaultConfiguration sharedConfig];
// Configure HTTP basic authorization (authentication scheme: bearerAuth)
[apiConfig setUsername:@"YOUR_USERNAME"];
[apiConfig setPassword:@"YOUR_PASSWORD"];


NSString* instrumentName = BTC-PERPETUAL; // Instrument name
NSNumber* startTimestamp = 1536569522277; // The earliest timestamp to return result for
NSNumber* endTimestamp = 1536569522277; // The most recent timestamp to return result for
NSNumber* count = @56; // Number of requested items, default - `10` (optional)
NSNumber* includeOld = @56; // Include trades older than 7 days, default - `false` (optional)
NSString* sorting = @"sorting_example"; // Direction of results sorting (`default` value means no sorting, results will be returned in order in which they left the database) (optional)

OAIMarketDataApi*apiInstance = [[OAIMarketDataApi alloc] init];

// Retrieve the latest trades that have occurred for a specific instrument and within given time range.
[apiInstance publicGetLastTradesByInstrumentAndTimeGetWithInstrumentName:instrumentName
              startTimestamp:startTimestamp
              endTimestamp:endTimestamp
              count:count
              includeOld:includeOld
              sorting:sorting
          completionHandler: ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAIMarketDataApi->publicGetLastTradesByInstrumentAndTimeGet: %@", error);
                        }
                    }];
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrumentName** | **NSString***| Instrument name | 
 **startTimestamp** | **NSNumber***| The earliest timestamp to return result for | 
 **endTimestamp** | **NSNumber***| The most recent timestamp to return result for | 
 **count** | **NSNumber***| Number of requested items, default - &#x60;10&#x60; | [optional] 
 **includeOld** | **NSNumber***| Include trades older than 7 days, default - &#x60;false&#x60; | [optional] 
 **sorting** | **NSString***| Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) | [optional] 

### Return type

**NSObject***

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **publicGetLastTradesByInstrumentGet**
```objc
-(NSURLSessionTask*) publicGetLastTradesByInstrumentGetWithInstrumentName: (NSString*) instrumentName
    startSeq: (NSNumber*) startSeq
    endSeq: (NSNumber*) endSeq
    count: (NSNumber*) count
    includeOld: (NSNumber*) includeOld
    sorting: (NSString*) sorting
        completionHandler: (void (^)(NSObject* output, NSError* error)) handler;
```

Retrieve the latest trades that have occurred for a specific instrument.

### Example 
```objc
OAIDefaultConfiguration *apiConfig = [OAIDefaultConfiguration sharedConfig];
// Configure HTTP basic authorization (authentication scheme: bearerAuth)
[apiConfig setUsername:@"YOUR_USERNAME"];
[apiConfig setPassword:@"YOUR_PASSWORD"];


NSString* instrumentName = BTC-PERPETUAL; // Instrument name
NSNumber* startSeq = @56; // The sequence number of the first trade to be returned (optional)
NSNumber* endSeq = @56; // The sequence number of the last trade to be returned (optional)
NSNumber* count = @56; // Number of requested items, default - `10` (optional)
NSNumber* includeOld = @56; // Include trades older than 7 days, default - `false` (optional)
NSString* sorting = @"sorting_example"; // Direction of results sorting (`default` value means no sorting, results will be returned in order in which they left the database) (optional)

OAIMarketDataApi*apiInstance = [[OAIMarketDataApi alloc] init];

// Retrieve the latest trades that have occurred for a specific instrument.
[apiInstance publicGetLastTradesByInstrumentGetWithInstrumentName:instrumentName
              startSeq:startSeq
              endSeq:endSeq
              count:count
              includeOld:includeOld
              sorting:sorting
          completionHandler: ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAIMarketDataApi->publicGetLastTradesByInstrumentGet: %@", error);
                        }
                    }];
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrumentName** | **NSString***| Instrument name | 
 **startSeq** | **NSNumber***| The sequence number of the first trade to be returned | [optional] 
 **endSeq** | **NSNumber***| The sequence number of the last trade to be returned | [optional] 
 **count** | **NSNumber***| Number of requested items, default - &#x60;10&#x60; | [optional] 
 **includeOld** | **NSNumber***| Include trades older than 7 days, default - &#x60;false&#x60; | [optional] 
 **sorting** | **NSString***| Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) | [optional] 

### Return type

**NSObject***

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **publicGetOrderBookGet**
```objc
-(NSURLSessionTask*) publicGetOrderBookGetWithInstrumentName: (NSString*) instrumentName
    depth: (NSNumber*) depth
        completionHandler: (void (^)(NSObject* output, NSError* error)) handler;
```

Retrieves the order book, along with other market values for a given instrument.

### Example 
```objc
OAIDefaultConfiguration *apiConfig = [OAIDefaultConfiguration sharedConfig];
// Configure HTTP basic authorization (authentication scheme: bearerAuth)
[apiConfig setUsername:@"YOUR_USERNAME"];
[apiConfig setPassword:@"YOUR_PASSWORD"];


NSString* instrumentName = @"instrumentName_example"; // The instrument name for which to retrieve the order book, see [`getinstruments`](#getinstruments) to obtain instrument names.
NSNumber* depth = @56; // The number of entries to return for bids and asks. (optional)

OAIMarketDataApi*apiInstance = [[OAIMarketDataApi alloc] init];

// Retrieves the order book, along with other market values for a given instrument.
[apiInstance publicGetOrderBookGetWithInstrumentName:instrumentName
              depth:depth
          completionHandler: ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAIMarketDataApi->publicGetOrderBookGet: %@", error);
                        }
                    }];
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrumentName** | **NSString***| The instrument name for which to retrieve the order book, see [&#x60;getinstruments&#x60;](#getinstruments) to obtain instrument names. | 
 **depth** | **NSNumber***| The number of entries to return for bids and asks. | [optional] 

### Return type

**NSObject***

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **publicGetTradeVolumesGet**
```objc
-(NSURLSessionTask*) publicGetTradeVolumesGetWithCompletionHandler: 
        (void (^)(NSObject* output, NSError* error)) handler;
```

Retrieves aggregated 24h trade volumes for different instrument types and currencies.

### Example 
```objc
OAIDefaultConfiguration *apiConfig = [OAIDefaultConfiguration sharedConfig];
// Configure HTTP basic authorization (authentication scheme: bearerAuth)
[apiConfig setUsername:@"YOUR_USERNAME"];
[apiConfig setPassword:@"YOUR_PASSWORD"];



OAIMarketDataApi*apiInstance = [[OAIMarketDataApi alloc] init];

// Retrieves aggregated 24h trade volumes for different instrument types and currencies.
[apiInstance publicGetTradeVolumesGetWithCompletionHandler: 
          ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAIMarketDataApi->publicGetTradeVolumesGet: %@", error);
                        }
                    }];
```

### Parameters
This endpoint does not need any parameter.

### Return type

**NSObject***

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **publicGetTradingviewChartDataGet**
```objc
-(NSURLSessionTask*) publicGetTradingviewChartDataGetWithInstrumentName: (NSString*) instrumentName
    startTimestamp: (NSNumber*) startTimestamp
    endTimestamp: (NSNumber*) endTimestamp
        completionHandler: (void (^)(NSObject* output, NSError* error)) handler;
```

Publicly available market data used to generate a TradingView candle chart.

### Example 
```objc
OAIDefaultConfiguration *apiConfig = [OAIDefaultConfiguration sharedConfig];
// Configure HTTP basic authorization (authentication scheme: bearerAuth)
[apiConfig setUsername:@"YOUR_USERNAME"];
[apiConfig setPassword:@"YOUR_PASSWORD"];


NSString* instrumentName = BTC-PERPETUAL; // Instrument name
NSNumber* startTimestamp = 1536569522277; // The earliest timestamp to return result for
NSNumber* endTimestamp = 1536569522277; // The most recent timestamp to return result for

OAIMarketDataApi*apiInstance = [[OAIMarketDataApi alloc] init];

// Publicly available market data used to generate a TradingView candle chart.
[apiInstance publicGetTradingviewChartDataGetWithInstrumentName:instrumentName
              startTimestamp:startTimestamp
              endTimestamp:endTimestamp
          completionHandler: ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAIMarketDataApi->publicGetTradingviewChartDataGet: %@", error);
                        }
                    }];
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrumentName** | **NSString***| Instrument name | 
 **startTimestamp** | **NSNumber***| The earliest timestamp to return result for | 
 **endTimestamp** | **NSNumber***| The most recent timestamp to return result for | 

### Return type

**NSObject***

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **publicTickerGet**
```objc
-(NSURLSessionTask*) publicTickerGetWithInstrumentName: (NSString*) instrumentName
        completionHandler: (void (^)(NSObject* output, NSError* error)) handler;
```

Get ticker for an instrument.

### Example 
```objc
OAIDefaultConfiguration *apiConfig = [OAIDefaultConfiguration sharedConfig];
// Configure HTTP basic authorization (authentication scheme: bearerAuth)
[apiConfig setUsername:@"YOUR_USERNAME"];
[apiConfig setPassword:@"YOUR_PASSWORD"];


NSString* instrumentName = BTC-PERPETUAL; // Instrument name

OAIMarketDataApi*apiInstance = [[OAIMarketDataApi alloc] init];

// Get ticker for an instrument.
[apiInstance publicTickerGetWithInstrumentName:instrumentName
          completionHandler: ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAIMarketDataApi->publicTickerGet: %@", error);
                        }
                    }];
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrumentName** | **NSString***| Instrument name | 

### Return type

**NSObject***

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

