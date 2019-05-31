# OAIPublicApi

All URIs are relative to *https://www.deribit.com/api/v2*

Method | HTTP request | Description
------------- | ------------- | -------------
[**publicAuthGet**](OAIPublicApi.md#publicauthget) | **GET** /public/auth | Authenticate
[**publicGetAnnouncementsGet**](OAIPublicApi.md#publicgetannouncementsget) | **GET** /public/get_announcements | Retrieves announcements from the last 30 days.
[**publicGetBookSummaryByCurrencyGet**](OAIPublicApi.md#publicgetbooksummarybycurrencyget) | **GET** /public/get_book_summary_by_currency | Retrieves the summary information such as open interest, 24h volume, etc. for all instruments for the currency (optionally filtered by kind).
[**publicGetBookSummaryByInstrumentGet**](OAIPublicApi.md#publicgetbooksummarybyinstrumentget) | **GET** /public/get_book_summary_by_instrument | Retrieves the summary information such as open interest, 24h volume, etc. for a specific instrument.
[**publicGetContractSizeGet**](OAIPublicApi.md#publicgetcontractsizeget) | **GET** /public/get_contract_size | Retrieves contract size of provided instrument.
[**publicGetCurrenciesGet**](OAIPublicApi.md#publicgetcurrenciesget) | **GET** /public/get_currencies | Retrieves all cryptocurrencies supported by the API.
[**publicGetFundingChartDataGet**](OAIPublicApi.md#publicgetfundingchartdataget) | **GET** /public/get_funding_chart_data | Retrieve the latest user trades that have occurred for PERPETUAL instruments in a specific currency symbol and within given time range.
[**publicGetHistoricalVolatilityGet**](OAIPublicApi.md#publicgethistoricalvolatilityget) | **GET** /public/get_historical_volatility | Provides information about historical volatility for given cryptocurrency.
[**publicGetIndexGet**](OAIPublicApi.md#publicgetindexget) | **GET** /public/get_index | Retrieves the current index price for the instruments, for the selected currency.
[**publicGetInstrumentsGet**](OAIPublicApi.md#publicgetinstrumentsget) | **GET** /public/get_instruments | Retrieves available trading instruments. This method can be used to see which instruments are available for trading, or which instruments have existed historically.
[**publicGetLastSettlementsByCurrencyGet**](OAIPublicApi.md#publicgetlastsettlementsbycurrencyget) | **GET** /public/get_last_settlements_by_currency | Retrieves historical settlement, delivery and bankruptcy events coming from all instruments within given currency.
[**publicGetLastSettlementsByInstrumentGet**](OAIPublicApi.md#publicgetlastsettlementsbyinstrumentget) | **GET** /public/get_last_settlements_by_instrument | Retrieves historical public settlement, delivery and bankruptcy events filtered by instrument name.
[**publicGetLastTradesByCurrencyAndTimeGet**](OAIPublicApi.md#publicgetlasttradesbycurrencyandtimeget) | **GET** /public/get_last_trades_by_currency_and_time | Retrieve the latest trades that have occurred for instruments in a specific currency symbol and within given time range.
[**publicGetLastTradesByCurrencyGet**](OAIPublicApi.md#publicgetlasttradesbycurrencyget) | **GET** /public/get_last_trades_by_currency | Retrieve the latest trades that have occurred for instruments in a specific currency symbol.
[**publicGetLastTradesByInstrumentAndTimeGet**](OAIPublicApi.md#publicgetlasttradesbyinstrumentandtimeget) | **GET** /public/get_last_trades_by_instrument_and_time | Retrieve the latest trades that have occurred for a specific instrument and within given time range.
[**publicGetLastTradesByInstrumentGet**](OAIPublicApi.md#publicgetlasttradesbyinstrumentget) | **GET** /public/get_last_trades_by_instrument | Retrieve the latest trades that have occurred for a specific instrument.
[**publicGetOrderBookGet**](OAIPublicApi.md#publicgetorderbookget) | **GET** /public/get_order_book | Retrieves the order book, along with other market values for a given instrument.
[**publicGetTimeGet**](OAIPublicApi.md#publicgettimeget) | **GET** /public/get_time | Retrieves the current time (in milliseconds). This API endpoint can be used to check the clock skew between your software and Deribit&#39;s systems.
[**publicGetTradeVolumesGet**](OAIPublicApi.md#publicgettradevolumesget) | **GET** /public/get_trade_volumes | Retrieves aggregated 24h trade volumes for different instrument types and currencies.
[**publicGetTradingviewChartDataGet**](OAIPublicApi.md#publicgettradingviewchartdataget) | **GET** /public/get_tradingview_chart_data | Publicly available market data used to generate a TradingView candle chart.
[**publicTestGet**](OAIPublicApi.md#publictestget) | **GET** /public/test | Tests the connection to the API server, and returns its version. You can use this to make sure the API is reachable, and matches the expected version.
[**publicTickerGet**](OAIPublicApi.md#publictickerget) | **GET** /public/ticker | Get ticker for an instrument.
[**publicValidateFieldGet**](OAIPublicApi.md#publicvalidatefieldget) | **GET** /public/validate_field | Method used to introduce the client software connected to Deribit platform over websocket. Provided data may have an impact on the maintained connection and will be collected for internal statistical purposes. In response, Deribit will also introduce itself.


# **publicAuthGet**
```objc
-(NSURLSessionTask*) publicAuthGetWithGrantType: (NSString*) grantType
    username: (NSString*) username
    password: (NSString*) password
    clientId: (NSString*) clientId
    clientSecret: (NSString*) clientSecret
    refreshToken: (NSString*) refreshToken
    timestamp: (NSString*) timestamp
    signature: (NSString*) signature
    nonce: (NSString*) nonce
    state: (NSString*) state
    scope: (NSString*) scope
        completionHandler: (void (^)(NSObject* output, NSError* error)) handler;
```

Authenticate

Retrieve an Oauth access token, to be used for authentication of 'private' requests.  Three methods of authentication are supported:  - <code>password</code> - using email and and password as when logging on to the website - <code>client_credentials</code> - using the access key and access secret that can be found on the API page on the website - <code>client_signature</code> - using the access key that can be found on the API page on the website and user generated signature. The signature is calculated using some fields provided in the request, using formula described here [Deribit signature credentials](#additional-authorization-method-deribit-signature-credentials) - <code>refresh_token</code> - using a refresh token that was received from an earlier invocation  The response will contain an access token, expiration period (number of seconds that the token is valid) and a refresh token that can be used to get a new set of tokens. 

### Example 
```objc
OAIDefaultConfiguration *apiConfig = [OAIDefaultConfiguration sharedConfig];
// Configure HTTP basic authorization (authentication scheme: bearerAuth)
[apiConfig setUsername:@"YOUR_USERNAME"];
[apiConfig setPassword:@"YOUR_PASSWORD"];


NSString* grantType = @"grantType_example"; // Method of authentication
NSString* username = your_email@mail.com; // Required for grant type `password`
NSString* password = your_password; // Required for grant type `password`
NSString* clientId = @"clientId_example"; // Required for grant type `client_credentials` and `client_signature`
NSString* clientSecret = @"clientSecret_example"; // Required for grant type `client_credentials`
NSString* refreshToken = @"refreshToken_example"; // Required for grant type `refresh_token`
NSString* timestamp = @"timestamp_example"; // Required for grant type `client_signature`, provides time when request has been generated
NSString* signature = @"signature_example"; // Required for grant type `client_signature`; it's a cryptographic signature calculated over provided fields using user **secret key**. The signature should be calculated as an HMAC (Hash-based Message Authentication Code) with `SHA256` hash algorithm
NSString* nonce = @"nonce_example"; // Optional for grant type `client_signature`; delivers user generated initialization vector for the server token (optional)
NSString* state = @"state_example"; // Will be passed back in the response (optional)
NSString* scope = connection; // Describes type of the access for assigned token, possible values: `connection`, `session`, `session:name`, `trade:[read, read_write, none]`, `wallet:[read, read_write, none]`, `account:[read, read_write, none]`, `expires:NUMBER` (token will expire after `NUMBER` of seconds).</BR></BR> **NOTICE:** Depending on choosing an authentication method (```grant type```) some scopes could be narrowed by the server. e.g. when ```grant_type = client_credentials``` and ```scope = wallet:read_write``` it's modified by the server as ```scope = wallet:read``` (optional)

OAIPublicApi*apiInstance = [[OAIPublicApi alloc] init];

// Authenticate
[apiInstance publicAuthGetWithGrantType:grantType
              username:username
              password:password
              clientId:clientId
              clientSecret:clientSecret
              refreshToken:refreshToken
              timestamp:timestamp
              signature:signature
              nonce:nonce
              state:state
              scope:scope
          completionHandler: ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAIPublicApi->publicAuthGet: %@", error);
                        }
                    }];
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **grantType** | **NSString***| Method of authentication | 
 **username** | **NSString***| Required for grant type &#x60;password&#x60; | 
 **password** | **NSString***| Required for grant type &#x60;password&#x60; | 
 **clientId** | **NSString***| Required for grant type &#x60;client_credentials&#x60; and &#x60;client_signature&#x60; | 
 **clientSecret** | **NSString***| Required for grant type &#x60;client_credentials&#x60; | 
 **refreshToken** | **NSString***| Required for grant type &#x60;refresh_token&#x60; | 
 **timestamp** | **NSString***| Required for grant type &#x60;client_signature&#x60;, provides time when request has been generated | 
 **signature** | **NSString***| Required for grant type &#x60;client_signature&#x60;; it&#39;s a cryptographic signature calculated over provided fields using user **secret key**. The signature should be calculated as an HMAC (Hash-based Message Authentication Code) with &#x60;SHA256&#x60; hash algorithm | 
 **nonce** | **NSString***| Optional for grant type &#x60;client_signature&#x60;; delivers user generated initialization vector for the server token | [optional] 
 **state** | **NSString***| Will be passed back in the response | [optional] 
 **scope** | **NSString***| Describes type of the access for assigned token, possible values: &#x60;connection&#x60;, &#x60;session&#x60;, &#x60;session:name&#x60;, &#x60;trade:[read, read_write, none]&#x60;, &#x60;wallet:[read, read_write, none]&#x60;, &#x60;account:[read, read_write, none]&#x60;, &#x60;expires:NUMBER&#x60; (token will expire after &#x60;NUMBER&#x60; of seconds).&lt;/BR&gt;&lt;/BR&gt; **NOTICE:** Depending on choosing an authentication method (&#x60;&#x60;&#x60;grant type&#x60;&#x60;&#x60;) some scopes could be narrowed by the server. e.g. when &#x60;&#x60;&#x60;grant_type &#x3D; client_credentials&#x60;&#x60;&#x60; and &#x60;&#x60;&#x60;scope &#x3D; wallet:read_write&#x60;&#x60;&#x60; it&#39;s modified by the server as &#x60;&#x60;&#x60;scope &#x3D; wallet:read&#x60;&#x60;&#x60; | [optional] 

### Return type

**NSObject***

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **publicGetAnnouncementsGet**
```objc
-(NSURLSessionTask*) publicGetAnnouncementsGetWithCompletionHandler: 
        (void (^)(NSObject* output, NSError* error)) handler;
```

Retrieves announcements from the last 30 days.

### Example 
```objc
OAIDefaultConfiguration *apiConfig = [OAIDefaultConfiguration sharedConfig];
// Configure HTTP basic authorization (authentication scheme: bearerAuth)
[apiConfig setUsername:@"YOUR_USERNAME"];
[apiConfig setPassword:@"YOUR_PASSWORD"];



OAIPublicApi*apiInstance = [[OAIPublicApi alloc] init];

// Retrieves announcements from the last 30 days.
[apiInstance publicGetAnnouncementsGetWithCompletionHandler: 
          ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAIPublicApi->publicGetAnnouncementsGet: %@", error);
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

OAIPublicApi*apiInstance = [[OAIPublicApi alloc] init];

// Retrieves the summary information such as open interest, 24h volume, etc. for all instruments for the currency (optionally filtered by kind).
[apiInstance publicGetBookSummaryByCurrencyGetWithCurrency:currency
              kind:kind
          completionHandler: ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAIPublicApi->publicGetBookSummaryByCurrencyGet: %@", error);
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

OAIPublicApi*apiInstance = [[OAIPublicApi alloc] init];

// Retrieves the summary information such as open interest, 24h volume, etc. for a specific instrument.
[apiInstance publicGetBookSummaryByInstrumentGetWithInstrumentName:instrumentName
          completionHandler: ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAIPublicApi->publicGetBookSummaryByInstrumentGet: %@", error);
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

OAIPublicApi*apiInstance = [[OAIPublicApi alloc] init];

// Retrieves contract size of provided instrument.
[apiInstance publicGetContractSizeGetWithInstrumentName:instrumentName
          completionHandler: ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAIPublicApi->publicGetContractSizeGet: %@", error);
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



OAIPublicApi*apiInstance = [[OAIPublicApi alloc] init];

// Retrieves all cryptocurrencies supported by the API.
[apiInstance publicGetCurrenciesGetWithCompletionHandler: 
          ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAIPublicApi->publicGetCurrenciesGet: %@", error);
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

OAIPublicApi*apiInstance = [[OAIPublicApi alloc] init];

// Retrieve the latest user trades that have occurred for PERPETUAL instruments in a specific currency symbol and within given time range.
[apiInstance publicGetFundingChartDataGetWithInstrumentName:instrumentName
              length:length
          completionHandler: ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAIPublicApi->publicGetFundingChartDataGet: %@", error);
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

OAIPublicApi*apiInstance = [[OAIPublicApi alloc] init];

// Provides information about historical volatility for given cryptocurrency.
[apiInstance publicGetHistoricalVolatilityGetWithCurrency:currency
          completionHandler: ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAIPublicApi->publicGetHistoricalVolatilityGet: %@", error);
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

OAIPublicApi*apiInstance = [[OAIPublicApi alloc] init];

// Retrieves the current index price for the instruments, for the selected currency.
[apiInstance publicGetIndexGetWithCurrency:currency
          completionHandler: ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAIPublicApi->publicGetIndexGet: %@", error);
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

OAIPublicApi*apiInstance = [[OAIPublicApi alloc] init];

// Retrieves available trading instruments. This method can be used to see which instruments are available for trading, or which instruments have existed historically.
[apiInstance publicGetInstrumentsGetWithCurrency:currency
              kind:kind
              expired:expired
          completionHandler: ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAIPublicApi->publicGetInstrumentsGet: %@", error);
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

OAIPublicApi*apiInstance = [[OAIPublicApi alloc] init];

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
                            NSLog(@"Error calling OAIPublicApi->publicGetLastSettlementsByCurrencyGet: %@", error);
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

OAIPublicApi*apiInstance = [[OAIPublicApi alloc] init];

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
                            NSLog(@"Error calling OAIPublicApi->publicGetLastSettlementsByInstrumentGet: %@", error);
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

OAIPublicApi*apiInstance = [[OAIPublicApi alloc] init];

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
                            NSLog(@"Error calling OAIPublicApi->publicGetLastTradesByCurrencyAndTimeGet: %@", error);
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

OAIPublicApi*apiInstance = [[OAIPublicApi alloc] init];

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
                            NSLog(@"Error calling OAIPublicApi->publicGetLastTradesByCurrencyGet: %@", error);
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

OAIPublicApi*apiInstance = [[OAIPublicApi alloc] init];

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
                            NSLog(@"Error calling OAIPublicApi->publicGetLastTradesByInstrumentAndTimeGet: %@", error);
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

OAIPublicApi*apiInstance = [[OAIPublicApi alloc] init];

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
                            NSLog(@"Error calling OAIPublicApi->publicGetLastTradesByInstrumentGet: %@", error);
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

OAIPublicApi*apiInstance = [[OAIPublicApi alloc] init];

// Retrieves the order book, along with other market values for a given instrument.
[apiInstance publicGetOrderBookGetWithInstrumentName:instrumentName
              depth:depth
          completionHandler: ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAIPublicApi->publicGetOrderBookGet: %@", error);
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

# **publicGetTimeGet**
```objc
-(NSURLSessionTask*) publicGetTimeGetWithCompletionHandler: 
        (void (^)(NSObject* output, NSError* error)) handler;
```

Retrieves the current time (in milliseconds). This API endpoint can be used to check the clock skew between your software and Deribit's systems.

### Example 
```objc
OAIDefaultConfiguration *apiConfig = [OAIDefaultConfiguration sharedConfig];
// Configure HTTP basic authorization (authentication scheme: bearerAuth)
[apiConfig setUsername:@"YOUR_USERNAME"];
[apiConfig setPassword:@"YOUR_PASSWORD"];



OAIPublicApi*apiInstance = [[OAIPublicApi alloc] init];

// Retrieves the current time (in milliseconds). This API endpoint can be used to check the clock skew between your software and Deribit's systems.
[apiInstance publicGetTimeGetWithCompletionHandler: 
          ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAIPublicApi->publicGetTimeGet: %@", error);
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



OAIPublicApi*apiInstance = [[OAIPublicApi alloc] init];

// Retrieves aggregated 24h trade volumes for different instrument types and currencies.
[apiInstance publicGetTradeVolumesGetWithCompletionHandler: 
          ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAIPublicApi->publicGetTradeVolumesGet: %@", error);
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

OAIPublicApi*apiInstance = [[OAIPublicApi alloc] init];

// Publicly available market data used to generate a TradingView candle chart.
[apiInstance publicGetTradingviewChartDataGetWithInstrumentName:instrumentName
              startTimestamp:startTimestamp
              endTimestamp:endTimestamp
          completionHandler: ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAIPublicApi->publicGetTradingviewChartDataGet: %@", error);
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

# **publicTestGet**
```objc
-(NSURLSessionTask*) publicTestGetWithExpectedResult: (NSString*) expectedResult
        completionHandler: (void (^)(NSObject* output, NSError* error)) handler;
```

Tests the connection to the API server, and returns its version. You can use this to make sure the API is reachable, and matches the expected version.

### Example 
```objc
OAIDefaultConfiguration *apiConfig = [OAIDefaultConfiguration sharedConfig];
// Configure HTTP basic authorization (authentication scheme: bearerAuth)
[apiConfig setUsername:@"YOUR_USERNAME"];
[apiConfig setPassword:@"YOUR_PASSWORD"];


NSString* expectedResult = @"expectedResult_example"; // The value \"exception\" will trigger an error response. This may be useful for testing wrapper libraries. (optional)

OAIPublicApi*apiInstance = [[OAIPublicApi alloc] init];

// Tests the connection to the API server, and returns its version. You can use this to make sure the API is reachable, and matches the expected version.
[apiInstance publicTestGetWithExpectedResult:expectedResult
          completionHandler: ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAIPublicApi->publicTestGet: %@", error);
                        }
                    }];
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **expectedResult** | **NSString***| The value \&quot;exception\&quot; will trigger an error response. This may be useful for testing wrapper libraries. | [optional] 

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

OAIPublicApi*apiInstance = [[OAIPublicApi alloc] init];

// Get ticker for an instrument.
[apiInstance publicTickerGetWithInstrumentName:instrumentName
          completionHandler: ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAIPublicApi->publicTickerGet: %@", error);
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

# **publicValidateFieldGet**
```objc
-(NSURLSessionTask*) publicValidateFieldGetWithField: (NSString*) field
    value: (NSString*) value
    value2: (NSString*) value2
        completionHandler: (void (^)(NSObject* output, NSError* error)) handler;
```

Method used to introduce the client software connected to Deribit platform over websocket. Provided data may have an impact on the maintained connection and will be collected for internal statistical purposes. In response, Deribit will also introduce itself.

### Example 
```objc
OAIDefaultConfiguration *apiConfig = [OAIDefaultConfiguration sharedConfig];
// Configure HTTP basic authorization (authentication scheme: bearerAuth)
[apiConfig setUsername:@"YOUR_USERNAME"];
[apiConfig setPassword:@"YOUR_PASSWORD"];


NSString* field = @"field_example"; // Name of the field to be validated, examples: postal_code, date_of_birth
NSString* value = @"value_example"; // Value to be checked
NSString* value2 = @"value2_example"; // Additional value to be compared with (optional)

OAIPublicApi*apiInstance = [[OAIPublicApi alloc] init];

// Method used to introduce the client software connected to Deribit platform over websocket. Provided data may have an impact on the maintained connection and will be collected for internal statistical purposes. In response, Deribit will also introduce itself.
[apiInstance publicValidateFieldGetWithField:field
              value:value
              value2:value2
          completionHandler: ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAIPublicApi->publicValidateFieldGet: %@", error);
                        }
                    }];
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **field** | **NSString***| Name of the field to be validated, examples: postal_code, date_of_birth | 
 **value** | **NSString***| Value to be checked | 
 **value2** | **NSString***| Additional value to be compared with | [optional] 

### Return type

**NSObject***

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

