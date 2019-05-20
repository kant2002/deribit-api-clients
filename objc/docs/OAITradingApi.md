# OAITradingApi

All URIs are relative to *https://www.deribit.com/api/v2*

Method | HTTP request | Description
------------- | ------------- | -------------
[**privateBuyGet**](OAITradingApi.md#privatebuyget) | **GET** /private/buy | Places a buy order for an instrument.
[**privateCancelAllByCurrencyGet**](OAITradingApi.md#privatecancelallbycurrencyget) | **GET** /private/cancel_all_by_currency | Cancels all orders by currency, optionally filtered by instrument kind and/or order type.
[**privateCancelAllByInstrumentGet**](OAITradingApi.md#privatecancelallbyinstrumentget) | **GET** /private/cancel_all_by_instrument | Cancels all orders by instrument, optionally filtered by order type.
[**privateCancelAllGet**](OAITradingApi.md#privatecancelallget) | **GET** /private/cancel_all | This method cancels all users orders and stop orders within all currencies and instrument kinds.
[**privateCancelGet**](OAITradingApi.md#privatecancelget) | **GET** /private/cancel | Cancel an order, specified by order id
[**privateClosePositionGet**](OAITradingApi.md#privateclosepositionget) | **GET** /private/close_position | Makes closing position reduce only order .
[**privateEditGet**](OAITradingApi.md#privateeditget) | **GET** /private/edit | Change price, amount and/or other properties of an order.
[**privateGetMarginsGet**](OAITradingApi.md#privategetmarginsget) | **GET** /private/get_margins | Get margins for given instrument, amount and price.
[**privateGetOpenOrdersByCurrencyGet**](OAITradingApi.md#privategetopenordersbycurrencyget) | **GET** /private/get_open_orders_by_currency | Retrieves list of user&#39;s open orders.
[**privateGetOpenOrdersByInstrumentGet**](OAITradingApi.md#privategetopenordersbyinstrumentget) | **GET** /private/get_open_orders_by_instrument | Retrieves list of user&#39;s open orders within given Instrument.
[**privateGetOrderHistoryByCurrencyGet**](OAITradingApi.md#privategetorderhistorybycurrencyget) | **GET** /private/get_order_history_by_currency | Retrieves history of orders that have been partially or fully filled.
[**privateGetOrderHistoryByInstrumentGet**](OAITradingApi.md#privategetorderhistorybyinstrumentget) | **GET** /private/get_order_history_by_instrument | Retrieves history of orders that have been partially or fully filled.
[**privateGetOrderMarginByIdsGet**](OAITradingApi.md#privategetordermarginbyidsget) | **GET** /private/get_order_margin_by_ids | Retrieves initial margins of given orders
[**privateGetOrderStateGet**](OAITradingApi.md#privategetorderstateget) | **GET** /private/get_order_state | Retrieve the current state of an order.
[**privateGetSettlementHistoryByCurrencyGet**](OAITradingApi.md#privategetsettlementhistorybycurrencyget) | **GET** /private/get_settlement_history_by_currency | Retrieves settlement, delivery and bankruptcy events that have affected your account.
[**privateGetSettlementHistoryByInstrumentGet**](OAITradingApi.md#privategetsettlementhistorybyinstrumentget) | **GET** /private/get_settlement_history_by_instrument | Retrieves public settlement, delivery and bankruptcy events filtered by instrument name
[**privateGetUserTradesByCurrencyAndTimeGet**](OAITradingApi.md#privategetusertradesbycurrencyandtimeget) | **GET** /private/get_user_trades_by_currency_and_time | Retrieve the latest user trades that have occurred for instruments in a specific currency symbol and within given time range.
[**privateGetUserTradesByCurrencyGet**](OAITradingApi.md#privategetusertradesbycurrencyget) | **GET** /private/get_user_trades_by_currency | Retrieve the latest user trades that have occurred for instruments in a specific currency symbol.
[**privateGetUserTradesByInstrumentAndTimeGet**](OAITradingApi.md#privategetusertradesbyinstrumentandtimeget) | **GET** /private/get_user_trades_by_instrument_and_time | Retrieve the latest user trades that have occurred for a specific instrument and within given time range.
[**privateGetUserTradesByInstrumentGet**](OAITradingApi.md#privategetusertradesbyinstrumentget) | **GET** /private/get_user_trades_by_instrument | Retrieve the latest user trades that have occurred for a specific instrument.
[**privateGetUserTradesByOrderGet**](OAITradingApi.md#privategetusertradesbyorderget) | **GET** /private/get_user_trades_by_order | Retrieve the list of user trades that was created for given order
[**privateSellGet**](OAITradingApi.md#privatesellget) | **GET** /private/sell | Places a sell order for an instrument.


# **privateBuyGet**
```objc
-(NSURLSessionTask*) privateBuyGetWithInstrumentName: (NSString*) instrumentName
    amount: (NSNumber*) amount
    type: (NSString*) type
    label: (NSString*) label
    price: (NSNumber*) price
    timeInForce: (NSString*) timeInForce
    maxShow: (NSNumber*) maxShow
    postOnly: (NSNumber*) postOnly
    reduceOnly: (NSNumber*) reduceOnly
    stopPrice: (NSNumber*) stopPrice
    trigger: (NSString*) trigger
    advanced: (NSString*) advanced
        completionHandler: (void (^)(NSObject* output, NSError* error)) handler;
```

Places a buy order for an instrument.

### Example 
```objc
OAIDefaultConfiguration *apiConfig = [OAIDefaultConfiguration sharedConfig];
// Configure HTTP basic authorization (authentication scheme: bearerAuth)
[apiConfig setUsername:@"YOUR_USERNAME"];
[apiConfig setPassword:@"YOUR_PASSWORD"];


NSString* instrumentName = BTC-PERPETUAL; // Instrument name
NSNumber* amount = @56; // It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH
NSString* type = @"type_example"; // The order type, default: `\"limit\"` (optional)
NSString* label = @"label_example"; // user defined label for the order (maximum 32 characters) (optional)
NSNumber* price = @56; // <p>The order price in base currency (Only for limit and stop_limit orders)</p> <p>When adding order with advanced=usd, the field price should be the option price value in USD.</p> <p>When adding order with advanced=implv, the field price should be a value of implied volatility in percentages. For example,  price=100, means implied volatility of 100%</p> (optional)
NSString* timeInForce = @"good_til_cancelled"; // <p>Specifies how long the order remains in effect. Default `\"good_til_cancelled\"`</p> <ul> <li>`\"good_til_cancelled\"` - unfilled order remains in order book until cancelled</li> <li>`\"fill_or_kill\"` - execute a transaction immediately and completely or not at all</li> <li>`\"immediate_or_cancel\"` - execute a transaction immediately, and any portion of the order that cannot be immediately filled is cancelled</li> </ul> (optional) (default to @"good_til_cancelled")
NSNumber* maxShow = @1; // Maximum amount within an order to be shown to other customers, `0` for invisible order (optional) (default to @1)
NSNumber* postOnly = @(YES); // <p>If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.</p> <p>Only valid in combination with time_in_force=`\"good_til_cancelled\"`</p> (optional) (default to @(YES))
NSNumber* reduceOnly = @(NO); // If `true`, the order is considered reduce-only which is intended to only reduce a current position (optional) (default to @(NO))
NSNumber* stopPrice = @56; // Stop price, required for stop limit orders (Only for stop orders) (optional)
NSString* trigger = @"trigger_example"; // Defines trigger type, required for `\"stop_limit\"` order type (optional)
NSString* advanced = @"advanced_example"; // Advanced option order type. (Only for options) (optional)

OAITradingApi*apiInstance = [[OAITradingApi alloc] init];

// Places a buy order for an instrument.
[apiInstance privateBuyGetWithInstrumentName:instrumentName
              amount:amount
              type:type
              label:label
              price:price
              timeInForce:timeInForce
              maxShow:maxShow
              postOnly:postOnly
              reduceOnly:reduceOnly
              stopPrice:stopPrice
              trigger:trigger
              advanced:advanced
          completionHandler: ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAITradingApi->privateBuyGet: %@", error);
                        }
                    }];
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrumentName** | **NSString***| Instrument name | 
 **amount** | **NSNumber***| It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH | 
 **type** | **NSString***| The order type, default: &#x60;\&quot;limit\&quot;&#x60; | [optional] 
 **label** | **NSString***| user defined label for the order (maximum 32 characters) | [optional] 
 **price** | **NSNumber***| &lt;p&gt;The order price in base currency (Only for limit and stop_limit orders)&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;usd, the field price should be the option price value in USD.&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;implv, the field price should be a value of implied volatility in percentages. For example,  price&#x3D;100, means implied volatility of 100%&lt;/p&gt; | [optional] 
 **timeInForce** | **NSString***| &lt;p&gt;Specifies how long the order remains in effect. Default &#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; &lt;ul&gt; &lt;li&gt;&#x60;\&quot;good_til_cancelled\&quot;&#x60; - unfilled order remains in order book until cancelled&lt;/li&gt; &lt;li&gt;&#x60;\&quot;fill_or_kill\&quot;&#x60; - execute a transaction immediately and completely or not at all&lt;/li&gt; &lt;li&gt;&#x60;\&quot;immediate_or_cancel\&quot;&#x60; - execute a transaction immediately, and any portion of the order that cannot be immediately filled is cancelled&lt;/li&gt; &lt;/ul&gt; | [optional] [default to @&quot;good_til_cancelled&quot;]
 **maxShow** | **NSNumber***| Maximum amount within an order to be shown to other customers, &#x60;0&#x60; for invisible order | [optional] [default to @1]
 **postOnly** | **NSNumber***| &lt;p&gt;If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.&lt;/p&gt; &lt;p&gt;Only valid in combination with time_in_force&#x3D;&#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; | [optional] [default to @(YES)]
 **reduceOnly** | **NSNumber***| If &#x60;true&#x60;, the order is considered reduce-only which is intended to only reduce a current position | [optional] [default to @(NO)]
 **stopPrice** | **NSNumber***| Stop price, required for stop limit orders (Only for stop orders) | [optional] 
 **trigger** | **NSString***| Defines trigger type, required for &#x60;\&quot;stop_limit\&quot;&#x60; order type | [optional] 
 **advanced** | **NSString***| Advanced option order type. (Only for options) | [optional] 

### Return type

**NSObject***

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateCancelAllByCurrencyGet**
```objc
-(NSURLSessionTask*) privateCancelAllByCurrencyGetWithCurrency: (NSString*) currency
    kind: (NSString*) kind
    type: (NSString*) type
        completionHandler: (void (^)(NSObject* output, NSError* error)) handler;
```

Cancels all orders by currency, optionally filtered by instrument kind and/or order type.

### Example 
```objc
OAIDefaultConfiguration *apiConfig = [OAIDefaultConfiguration sharedConfig];
// Configure HTTP basic authorization (authentication scheme: bearerAuth)
[apiConfig setUsername:@"YOUR_USERNAME"];
[apiConfig setPassword:@"YOUR_PASSWORD"];


NSString* currency = @"currency_example"; // The currency symbol
NSString* kind = @"kind_example"; // Instrument kind, if not provided instruments of all kinds are considered (optional)
NSString* type = @"type_example"; // Order type - limit, stop or all, default - `all` (optional)

OAITradingApi*apiInstance = [[OAITradingApi alloc] init];

// Cancels all orders by currency, optionally filtered by instrument kind and/or order type.
[apiInstance privateCancelAllByCurrencyGetWithCurrency:currency
              kind:kind
              type:type
          completionHandler: ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAITradingApi->privateCancelAllByCurrencyGet: %@", error);
                        }
                    }];
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **NSString***| The currency symbol | 
 **kind** | **NSString***| Instrument kind, if not provided instruments of all kinds are considered | [optional] 
 **type** | **NSString***| Order type - limit, stop or all, default - &#x60;all&#x60; | [optional] 

### Return type

**NSObject***

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateCancelAllByInstrumentGet**
```objc
-(NSURLSessionTask*) privateCancelAllByInstrumentGetWithInstrumentName: (NSString*) instrumentName
    type: (NSString*) type
        completionHandler: (void (^)(NSObject* output, NSError* error)) handler;
```

Cancels all orders by instrument, optionally filtered by order type.

### Example 
```objc
OAIDefaultConfiguration *apiConfig = [OAIDefaultConfiguration sharedConfig];
// Configure HTTP basic authorization (authentication scheme: bearerAuth)
[apiConfig setUsername:@"YOUR_USERNAME"];
[apiConfig setPassword:@"YOUR_PASSWORD"];


NSString* instrumentName = BTC-PERPETUAL; // Instrument name
NSString* type = @"type_example"; // Order type - limit, stop or all, default - `all` (optional)

OAITradingApi*apiInstance = [[OAITradingApi alloc] init];

// Cancels all orders by instrument, optionally filtered by order type.
[apiInstance privateCancelAllByInstrumentGetWithInstrumentName:instrumentName
              type:type
          completionHandler: ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAITradingApi->privateCancelAllByInstrumentGet: %@", error);
                        }
                    }];
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrumentName** | **NSString***| Instrument name | 
 **type** | **NSString***| Order type - limit, stop or all, default - &#x60;all&#x60; | [optional] 

### Return type

**NSObject***

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateCancelAllGet**
```objc
-(NSURLSessionTask*) privateCancelAllGetWithCompletionHandler: 
        (void (^)(NSObject* output, NSError* error)) handler;
```

This method cancels all users orders and stop orders within all currencies and instrument kinds.

### Example 
```objc
OAIDefaultConfiguration *apiConfig = [OAIDefaultConfiguration sharedConfig];
// Configure HTTP basic authorization (authentication scheme: bearerAuth)
[apiConfig setUsername:@"YOUR_USERNAME"];
[apiConfig setPassword:@"YOUR_PASSWORD"];



OAITradingApi*apiInstance = [[OAITradingApi alloc] init];

// This method cancels all users orders and stop orders within all currencies and instrument kinds.
[apiInstance privateCancelAllGetWithCompletionHandler: 
          ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAITradingApi->privateCancelAllGet: %@", error);
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

# **privateCancelGet**
```objc
-(NSURLSessionTask*) privateCancelGetWithOrderId: (NSString*) orderId
        completionHandler: (void (^)(NSObject* output, NSError* error)) handler;
```

Cancel an order, specified by order id

### Example 
```objc
OAIDefaultConfiguration *apiConfig = [OAIDefaultConfiguration sharedConfig];
// Configure HTTP basic authorization (authentication scheme: bearerAuth)
[apiConfig setUsername:@"YOUR_USERNAME"];
[apiConfig setPassword:@"YOUR_PASSWORD"];


NSString* orderId = ETH-100234; // The order id

OAITradingApi*apiInstance = [[OAITradingApi alloc] init];

// Cancel an order, specified by order id
[apiInstance privateCancelGetWithOrderId:orderId
          completionHandler: ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAITradingApi->privateCancelGet: %@", error);
                        }
                    }];
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **orderId** | **NSString***| The order id | 

### Return type

**NSObject***

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateClosePositionGet**
```objc
-(NSURLSessionTask*) privateClosePositionGetWithInstrumentName: (NSString*) instrumentName
    type: (NSString*) type
    price: (NSNumber*) price
        completionHandler: (void (^)(NSObject* output, NSError* error)) handler;
```

Makes closing position reduce only order .

### Example 
```objc
OAIDefaultConfiguration *apiConfig = [OAIDefaultConfiguration sharedConfig];
// Configure HTTP basic authorization (authentication scheme: bearerAuth)
[apiConfig setUsername:@"YOUR_USERNAME"];
[apiConfig setPassword:@"YOUR_PASSWORD"];


NSString* instrumentName = BTC-PERPETUAL; // Instrument name
NSString* type = @"type_example"; // The order type
NSNumber* price = @56; // Optional price for limit order. (optional)

OAITradingApi*apiInstance = [[OAITradingApi alloc] init];

// Makes closing position reduce only order .
[apiInstance privateClosePositionGetWithInstrumentName:instrumentName
              type:type
              price:price
          completionHandler: ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAITradingApi->privateClosePositionGet: %@", error);
                        }
                    }];
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrumentName** | **NSString***| Instrument name | 
 **type** | **NSString***| The order type | 
 **price** | **NSNumber***| Optional price for limit order. | [optional] 

### Return type

**NSObject***

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateEditGet**
```objc
-(NSURLSessionTask*) privateEditGetWithOrderId: (NSString*) orderId
    amount: (NSNumber*) amount
    price: (NSNumber*) price
    postOnly: (NSNumber*) postOnly
    advanced: (NSString*) advanced
    stopPrice: (NSNumber*) stopPrice
        completionHandler: (void (^)(NSObject* output, NSError* error)) handler;
```

Change price, amount and/or other properties of an order.

### Example 
```objc
OAIDefaultConfiguration *apiConfig = [OAIDefaultConfiguration sharedConfig];
// Configure HTTP basic authorization (authentication scheme: bearerAuth)
[apiConfig setUsername:@"YOUR_USERNAME"];
[apiConfig setPassword:@"YOUR_PASSWORD"];


NSString* orderId = ETH-100234; // The order id
NSNumber* amount = @56; // It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH
NSNumber* price = @56; // <p>The order price in base currency.</p> <p>When editing an option order with advanced=usd, the field price should be the option price value in USD.</p> <p>When editing an option order with advanced=implv, the field price should be a value of implied volatility in percentages. For example,  price=100, means implied volatility of 100%</p>
NSNumber* postOnly = @(YES); // <p>If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.</p> <p>Only valid in combination with time_in_force=`\"good_til_cancelled\"`</p> (optional) (default to @(YES))
NSString* advanced = @"advanced_example"; // Advanced option order type. If you have posted an advanced option order, it is necessary to re-supply this parameter when editing it (Only for options) (optional)
NSNumber* stopPrice = @56; // Stop price, required for stop limit orders (Only for stop orders) (optional)

OAITradingApi*apiInstance = [[OAITradingApi alloc] init];

// Change price, amount and/or other properties of an order.
[apiInstance privateEditGetWithOrderId:orderId
              amount:amount
              price:price
              postOnly:postOnly
              advanced:advanced
              stopPrice:stopPrice
          completionHandler: ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAITradingApi->privateEditGet: %@", error);
                        }
                    }];
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **orderId** | **NSString***| The order id | 
 **amount** | **NSNumber***| It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH | 
 **price** | **NSNumber***| &lt;p&gt;The order price in base currency.&lt;/p&gt; &lt;p&gt;When editing an option order with advanced&#x3D;usd, the field price should be the option price value in USD.&lt;/p&gt; &lt;p&gt;When editing an option order with advanced&#x3D;implv, the field price should be a value of implied volatility in percentages. For example,  price&#x3D;100, means implied volatility of 100%&lt;/p&gt; | 
 **postOnly** | **NSNumber***| &lt;p&gt;If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.&lt;/p&gt; &lt;p&gt;Only valid in combination with time_in_force&#x3D;&#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; | [optional] [default to @(YES)]
 **advanced** | **NSString***| Advanced option order type. If you have posted an advanced option order, it is necessary to re-supply this parameter when editing it (Only for options) | [optional] 
 **stopPrice** | **NSNumber***| Stop price, required for stop limit orders (Only for stop orders) | [optional] 

### Return type

**NSObject***

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateGetMarginsGet**
```objc
-(NSURLSessionTask*) privateGetMarginsGetWithInstrumentName: (NSString*) instrumentName
    amount: (NSNumber*) amount
    price: (NSNumber*) price
        completionHandler: (void (^)(NSObject* output, NSError* error)) handler;
```

Get margins for given instrument, amount and price.

### Example 
```objc
OAIDefaultConfiguration *apiConfig = [OAIDefaultConfiguration sharedConfig];
// Configure HTTP basic authorization (authentication scheme: bearerAuth)
[apiConfig setUsername:@"YOUR_USERNAME"];
[apiConfig setPassword:@"YOUR_PASSWORD"];


NSString* instrumentName = BTC-PERPETUAL; // Instrument name
NSNumber* amount = 1; // Amount, integer for future, float for option. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH.
NSNumber* price = @56; // Price

OAITradingApi*apiInstance = [[OAITradingApi alloc] init];

// Get margins for given instrument, amount and price.
[apiInstance privateGetMarginsGetWithInstrumentName:instrumentName
              amount:amount
              price:price
          completionHandler: ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAITradingApi->privateGetMarginsGet: %@", error);
                        }
                    }];
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrumentName** | **NSString***| Instrument name | 
 **amount** | **NSNumber***| Amount, integer for future, float for option. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH. | 
 **price** | **NSNumber***| Price | 

### Return type

**NSObject***

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateGetOpenOrdersByCurrencyGet**
```objc
-(NSURLSessionTask*) privateGetOpenOrdersByCurrencyGetWithCurrency: (NSString*) currency
    kind: (NSString*) kind
    type: (NSString*) type
        completionHandler: (void (^)(NSObject* output, NSError* error)) handler;
```

Retrieves list of user's open orders.

### Example 
```objc
OAIDefaultConfiguration *apiConfig = [OAIDefaultConfiguration sharedConfig];
// Configure HTTP basic authorization (authentication scheme: bearerAuth)
[apiConfig setUsername:@"YOUR_USERNAME"];
[apiConfig setPassword:@"YOUR_PASSWORD"];


NSString* currency = @"currency_example"; // The currency symbol
NSString* kind = @"kind_example"; // Instrument kind, if not provided instruments of all kinds are considered (optional)
NSString* type = @"type_example"; // Order type, default - `all` (optional)

OAITradingApi*apiInstance = [[OAITradingApi alloc] init];

// Retrieves list of user's open orders.
[apiInstance privateGetOpenOrdersByCurrencyGetWithCurrency:currency
              kind:kind
              type:type
          completionHandler: ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAITradingApi->privateGetOpenOrdersByCurrencyGet: %@", error);
                        }
                    }];
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **NSString***| The currency symbol | 
 **kind** | **NSString***| Instrument kind, if not provided instruments of all kinds are considered | [optional] 
 **type** | **NSString***| Order type, default - &#x60;all&#x60; | [optional] 

### Return type

**NSObject***

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateGetOpenOrdersByInstrumentGet**
```objc
-(NSURLSessionTask*) privateGetOpenOrdersByInstrumentGetWithInstrumentName: (NSString*) instrumentName
    type: (NSString*) type
        completionHandler: (void (^)(NSObject* output, NSError* error)) handler;
```

Retrieves list of user's open orders within given Instrument.

### Example 
```objc
OAIDefaultConfiguration *apiConfig = [OAIDefaultConfiguration sharedConfig];
// Configure HTTP basic authorization (authentication scheme: bearerAuth)
[apiConfig setUsername:@"YOUR_USERNAME"];
[apiConfig setPassword:@"YOUR_PASSWORD"];


NSString* instrumentName = BTC-PERPETUAL; // Instrument name
NSString* type = @"type_example"; // Order type, default - `all` (optional)

OAITradingApi*apiInstance = [[OAITradingApi alloc] init];

// Retrieves list of user's open orders within given Instrument.
[apiInstance privateGetOpenOrdersByInstrumentGetWithInstrumentName:instrumentName
              type:type
          completionHandler: ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAITradingApi->privateGetOpenOrdersByInstrumentGet: %@", error);
                        }
                    }];
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrumentName** | **NSString***| Instrument name | 
 **type** | **NSString***| Order type, default - &#x60;all&#x60; | [optional] 

### Return type

**NSObject***

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateGetOrderHistoryByCurrencyGet**
```objc
-(NSURLSessionTask*) privateGetOrderHistoryByCurrencyGetWithCurrency: (NSString*) currency
    kind: (NSString*) kind
    count: (NSNumber*) count
    offset: (NSNumber*) offset
    includeOld: (NSNumber*) includeOld
    includeUnfilled: (NSNumber*) includeUnfilled
        completionHandler: (void (^)(NSObject* output, NSError* error)) handler;
```

Retrieves history of orders that have been partially or fully filled.

### Example 
```objc
OAIDefaultConfiguration *apiConfig = [OAIDefaultConfiguration sharedConfig];
// Configure HTTP basic authorization (authentication scheme: bearerAuth)
[apiConfig setUsername:@"YOUR_USERNAME"];
[apiConfig setPassword:@"YOUR_PASSWORD"];


NSString* currency = @"currency_example"; // The currency symbol
NSString* kind = @"kind_example"; // Instrument kind, if not provided instruments of all kinds are considered (optional)
NSNumber* count = @56; // Number of requested items, default - `20` (optional)
NSNumber* offset = 10; // The offset for pagination, default - `0` (optional)
NSNumber* includeOld = false; // Include in result orders older than 2 days, default - `false` (optional)
NSNumber* includeUnfilled = false; // Include in result fully unfilled closed orders, default - `false` (optional)

OAITradingApi*apiInstance = [[OAITradingApi alloc] init];

// Retrieves history of orders that have been partially or fully filled.
[apiInstance privateGetOrderHistoryByCurrencyGetWithCurrency:currency
              kind:kind
              count:count
              offset:offset
              includeOld:includeOld
              includeUnfilled:includeUnfilled
          completionHandler: ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAITradingApi->privateGetOrderHistoryByCurrencyGet: %@", error);
                        }
                    }];
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **NSString***| The currency symbol | 
 **kind** | **NSString***| Instrument kind, if not provided instruments of all kinds are considered | [optional] 
 **count** | **NSNumber***| Number of requested items, default - &#x60;20&#x60; | [optional] 
 **offset** | **NSNumber***| The offset for pagination, default - &#x60;0&#x60; | [optional] 
 **includeOld** | **NSNumber***| Include in result orders older than 2 days, default - &#x60;false&#x60; | [optional] 
 **includeUnfilled** | **NSNumber***| Include in result fully unfilled closed orders, default - &#x60;false&#x60; | [optional] 

### Return type

**NSObject***

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateGetOrderHistoryByInstrumentGet**
```objc
-(NSURLSessionTask*) privateGetOrderHistoryByInstrumentGetWithInstrumentName: (NSString*) instrumentName
    count: (NSNumber*) count
    offset: (NSNumber*) offset
    includeOld: (NSNumber*) includeOld
    includeUnfilled: (NSNumber*) includeUnfilled
        completionHandler: (void (^)(NSObject* output, NSError* error)) handler;
```

Retrieves history of orders that have been partially or fully filled.

### Example 
```objc
OAIDefaultConfiguration *apiConfig = [OAIDefaultConfiguration sharedConfig];
// Configure HTTP basic authorization (authentication scheme: bearerAuth)
[apiConfig setUsername:@"YOUR_USERNAME"];
[apiConfig setPassword:@"YOUR_PASSWORD"];


NSString* instrumentName = BTC-PERPETUAL; // Instrument name
NSNumber* count = @56; // Number of requested items, default - `20` (optional)
NSNumber* offset = 10; // The offset for pagination, default - `0` (optional)
NSNumber* includeOld = false; // Include in result orders older than 2 days, default - `false` (optional)
NSNumber* includeUnfilled = false; // Include in result fully unfilled closed orders, default - `false` (optional)

OAITradingApi*apiInstance = [[OAITradingApi alloc] init];

// Retrieves history of orders that have been partially or fully filled.
[apiInstance privateGetOrderHistoryByInstrumentGetWithInstrumentName:instrumentName
              count:count
              offset:offset
              includeOld:includeOld
              includeUnfilled:includeUnfilled
          completionHandler: ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAITradingApi->privateGetOrderHistoryByInstrumentGet: %@", error);
                        }
                    }];
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrumentName** | **NSString***| Instrument name | 
 **count** | **NSNumber***| Number of requested items, default - &#x60;20&#x60; | [optional] 
 **offset** | **NSNumber***| The offset for pagination, default - &#x60;0&#x60; | [optional] 
 **includeOld** | **NSNumber***| Include in result orders older than 2 days, default - &#x60;false&#x60; | [optional] 
 **includeUnfilled** | **NSNumber***| Include in result fully unfilled closed orders, default - &#x60;false&#x60; | [optional] 

### Return type

**NSObject***

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateGetOrderMarginByIdsGet**
```objc
-(NSURLSessionTask*) privateGetOrderMarginByIdsGetWithIds: (NSArray<NSString*>*) ids
        completionHandler: (void (^)(NSObject* output, NSError* error)) handler;
```

Retrieves initial margins of given orders

### Example 
```objc
OAIDefaultConfiguration *apiConfig = [OAIDefaultConfiguration sharedConfig];
// Configure HTTP basic authorization (authentication scheme: bearerAuth)
[apiConfig setUsername:@"YOUR_USERNAME"];
[apiConfig setPassword:@"YOUR_PASSWORD"];


NSArray<NSString*>* ids = @[@"ids_example"]; // Ids of orders

OAITradingApi*apiInstance = [[OAITradingApi alloc] init];

// Retrieves initial margins of given orders
[apiInstance privateGetOrderMarginByIdsGetWithIds:ids
          completionHandler: ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAITradingApi->privateGetOrderMarginByIdsGet: %@", error);
                        }
                    }];
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **ids** | [**NSArray&lt;NSString*&gt;***](NSString*.md)| Ids of orders | 

### Return type

**NSObject***

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateGetOrderStateGet**
```objc
-(NSURLSessionTask*) privateGetOrderStateGetWithOrderId: (NSString*) orderId
        completionHandler: (void (^)(NSObject* output, NSError* error)) handler;
```

Retrieve the current state of an order.

### Example 
```objc
OAIDefaultConfiguration *apiConfig = [OAIDefaultConfiguration sharedConfig];
// Configure HTTP basic authorization (authentication scheme: bearerAuth)
[apiConfig setUsername:@"YOUR_USERNAME"];
[apiConfig setPassword:@"YOUR_PASSWORD"];


NSString* orderId = ETH-100234; // The order id

OAITradingApi*apiInstance = [[OAITradingApi alloc] init];

// Retrieve the current state of an order.
[apiInstance privateGetOrderStateGetWithOrderId:orderId
          completionHandler: ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAITradingApi->privateGetOrderStateGet: %@", error);
                        }
                    }];
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **orderId** | **NSString***| The order id | 

### Return type

**NSObject***

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateGetSettlementHistoryByCurrencyGet**
```objc
-(NSURLSessionTask*) privateGetSettlementHistoryByCurrencyGetWithCurrency: (NSString*) currency
    type: (NSString*) type
    count: (NSNumber*) count
        completionHandler: (void (^)(NSObject* output, NSError* error)) handler;
```

Retrieves settlement, delivery and bankruptcy events that have affected your account.

### Example 
```objc
OAIDefaultConfiguration *apiConfig = [OAIDefaultConfiguration sharedConfig];
// Configure HTTP basic authorization (authentication scheme: bearerAuth)
[apiConfig setUsername:@"YOUR_USERNAME"];
[apiConfig setPassword:@"YOUR_PASSWORD"];


NSString* currency = @"currency_example"; // The currency symbol
NSString* type = @"type_example"; // Settlement type (optional)
NSNumber* count = @56; // Number of requested items, default - `20` (optional)

OAITradingApi*apiInstance = [[OAITradingApi alloc] init];

// Retrieves settlement, delivery and bankruptcy events that have affected your account.
[apiInstance privateGetSettlementHistoryByCurrencyGetWithCurrency:currency
              type:type
              count:count
          completionHandler: ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAITradingApi->privateGetSettlementHistoryByCurrencyGet: %@", error);
                        }
                    }];
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **NSString***| The currency symbol | 
 **type** | **NSString***| Settlement type | [optional] 
 **count** | **NSNumber***| Number of requested items, default - &#x60;20&#x60; | [optional] 

### Return type

**NSObject***

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateGetSettlementHistoryByInstrumentGet**
```objc
-(NSURLSessionTask*) privateGetSettlementHistoryByInstrumentGetWithInstrumentName: (NSString*) instrumentName
    type: (NSString*) type
    count: (NSNumber*) count
        completionHandler: (void (^)(NSObject* output, NSError* error)) handler;
```

Retrieves public settlement, delivery and bankruptcy events filtered by instrument name

### Example 
```objc
OAIDefaultConfiguration *apiConfig = [OAIDefaultConfiguration sharedConfig];
// Configure HTTP basic authorization (authentication scheme: bearerAuth)
[apiConfig setUsername:@"YOUR_USERNAME"];
[apiConfig setPassword:@"YOUR_PASSWORD"];


NSString* instrumentName = BTC-PERPETUAL; // Instrument name
NSString* type = @"type_example"; // Settlement type (optional)
NSNumber* count = @56; // Number of requested items, default - `20` (optional)

OAITradingApi*apiInstance = [[OAITradingApi alloc] init];

// Retrieves public settlement, delivery and bankruptcy events filtered by instrument name
[apiInstance privateGetSettlementHistoryByInstrumentGetWithInstrumentName:instrumentName
              type:type
              count:count
          completionHandler: ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAITradingApi->privateGetSettlementHistoryByInstrumentGet: %@", error);
                        }
                    }];
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrumentName** | **NSString***| Instrument name | 
 **type** | **NSString***| Settlement type | [optional] 
 **count** | **NSNumber***| Number of requested items, default - &#x60;20&#x60; | [optional] 

### Return type

**NSObject***

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateGetUserTradesByCurrencyAndTimeGet**
```objc
-(NSURLSessionTask*) privateGetUserTradesByCurrencyAndTimeGetWithCurrency: (NSString*) currency
    startTimestamp: (NSNumber*) startTimestamp
    endTimestamp: (NSNumber*) endTimestamp
    kind: (NSString*) kind
    count: (NSNumber*) count
    includeOld: (NSNumber*) includeOld
    sorting: (NSString*) sorting
        completionHandler: (void (^)(NSObject* output, NSError* error)) handler;
```

Retrieve the latest user trades that have occurred for instruments in a specific currency symbol and within given time range.

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

OAITradingApi*apiInstance = [[OAITradingApi alloc] init];

// Retrieve the latest user trades that have occurred for instruments in a specific currency symbol and within given time range.
[apiInstance privateGetUserTradesByCurrencyAndTimeGetWithCurrency:currency
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
                            NSLog(@"Error calling OAITradingApi->privateGetUserTradesByCurrencyAndTimeGet: %@", error);
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

# **privateGetUserTradesByCurrencyGet**
```objc
-(NSURLSessionTask*) privateGetUserTradesByCurrencyGetWithCurrency: (NSString*) currency
    kind: (NSString*) kind
    startId: (NSString*) startId
    endId: (NSString*) endId
    count: (NSNumber*) count
    includeOld: (NSNumber*) includeOld
    sorting: (NSString*) sorting
        completionHandler: (void (^)(NSObject* output, NSError* error)) handler;
```

Retrieve the latest user trades that have occurred for instruments in a specific currency symbol.

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

OAITradingApi*apiInstance = [[OAITradingApi alloc] init];

// Retrieve the latest user trades that have occurred for instruments in a specific currency symbol.
[apiInstance privateGetUserTradesByCurrencyGetWithCurrency:currency
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
                            NSLog(@"Error calling OAITradingApi->privateGetUserTradesByCurrencyGet: %@", error);
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

# **privateGetUserTradesByInstrumentAndTimeGet**
```objc
-(NSURLSessionTask*) privateGetUserTradesByInstrumentAndTimeGetWithInstrumentName: (NSString*) instrumentName
    startTimestamp: (NSNumber*) startTimestamp
    endTimestamp: (NSNumber*) endTimestamp
    count: (NSNumber*) count
    includeOld: (NSNumber*) includeOld
    sorting: (NSString*) sorting
        completionHandler: (void (^)(NSObject* output, NSError* error)) handler;
```

Retrieve the latest user trades that have occurred for a specific instrument and within given time range.

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

OAITradingApi*apiInstance = [[OAITradingApi alloc] init];

// Retrieve the latest user trades that have occurred for a specific instrument and within given time range.
[apiInstance privateGetUserTradesByInstrumentAndTimeGetWithInstrumentName:instrumentName
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
                            NSLog(@"Error calling OAITradingApi->privateGetUserTradesByInstrumentAndTimeGet: %@", error);
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

# **privateGetUserTradesByInstrumentGet**
```objc
-(NSURLSessionTask*) privateGetUserTradesByInstrumentGetWithInstrumentName: (NSString*) instrumentName
    startSeq: (NSNumber*) startSeq
    endSeq: (NSNumber*) endSeq
    count: (NSNumber*) count
    includeOld: (NSNumber*) includeOld
    sorting: (NSString*) sorting
        completionHandler: (void (^)(NSObject* output, NSError* error)) handler;
```

Retrieve the latest user trades that have occurred for a specific instrument.

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

OAITradingApi*apiInstance = [[OAITradingApi alloc] init];

// Retrieve the latest user trades that have occurred for a specific instrument.
[apiInstance privateGetUserTradesByInstrumentGetWithInstrumentName:instrumentName
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
                            NSLog(@"Error calling OAITradingApi->privateGetUserTradesByInstrumentGet: %@", error);
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

# **privateGetUserTradesByOrderGet**
```objc
-(NSURLSessionTask*) privateGetUserTradesByOrderGetWithOrderId: (NSString*) orderId
    sorting: (NSString*) sorting
        completionHandler: (void (^)(NSObject* output, NSError* error)) handler;
```

Retrieve the list of user trades that was created for given order

### Example 
```objc
OAIDefaultConfiguration *apiConfig = [OAIDefaultConfiguration sharedConfig];
// Configure HTTP basic authorization (authentication scheme: bearerAuth)
[apiConfig setUsername:@"YOUR_USERNAME"];
[apiConfig setPassword:@"YOUR_PASSWORD"];


NSString* orderId = ETH-100234; // The order id
NSString* sorting = @"sorting_example"; // Direction of results sorting (`default` value means no sorting, results will be returned in order in which they left the database) (optional)

OAITradingApi*apiInstance = [[OAITradingApi alloc] init];

// Retrieve the list of user trades that was created for given order
[apiInstance privateGetUserTradesByOrderGetWithOrderId:orderId
              sorting:sorting
          completionHandler: ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAITradingApi->privateGetUserTradesByOrderGet: %@", error);
                        }
                    }];
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **orderId** | **NSString***| The order id | 
 **sorting** | **NSString***| Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) | [optional] 

### Return type

**NSObject***

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateSellGet**
```objc
-(NSURLSessionTask*) privateSellGetWithInstrumentName: (NSString*) instrumentName
    amount: (NSNumber*) amount
    type: (NSString*) type
    label: (NSString*) label
    price: (NSNumber*) price
    timeInForce: (NSString*) timeInForce
    maxShow: (NSNumber*) maxShow
    postOnly: (NSNumber*) postOnly
    reduceOnly: (NSNumber*) reduceOnly
    stopPrice: (NSNumber*) stopPrice
    trigger: (NSString*) trigger
    advanced: (NSString*) advanced
        completionHandler: (void (^)(NSObject* output, NSError* error)) handler;
```

Places a sell order for an instrument.

### Example 
```objc
OAIDefaultConfiguration *apiConfig = [OAIDefaultConfiguration sharedConfig];
// Configure HTTP basic authorization (authentication scheme: bearerAuth)
[apiConfig setUsername:@"YOUR_USERNAME"];
[apiConfig setPassword:@"YOUR_PASSWORD"];


NSString* instrumentName = BTC-PERPETUAL; // Instrument name
NSNumber* amount = @56; // It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH
NSString* type = @"type_example"; // The order type, default: `\"limit\"` (optional)
NSString* label = @"label_example"; // user defined label for the order (maximum 32 characters) (optional)
NSNumber* price = @56; // <p>The order price in base currency (Only for limit and stop_limit orders)</p> <p>When adding order with advanced=usd, the field price should be the option price value in USD.</p> <p>When adding order with advanced=implv, the field price should be a value of implied volatility in percentages. For example,  price=100, means implied volatility of 100%</p> (optional)
NSString* timeInForce = @"good_til_cancelled"; // <p>Specifies how long the order remains in effect. Default `\"good_til_cancelled\"`</p> <ul> <li>`\"good_til_cancelled\"` - unfilled order remains in order book until cancelled</li> <li>`\"fill_or_kill\"` - execute a transaction immediately and completely or not at all</li> <li>`\"immediate_or_cancel\"` - execute a transaction immediately, and any portion of the order that cannot be immediately filled is cancelled</li> </ul> (optional) (default to @"good_til_cancelled")
NSNumber* maxShow = @1; // Maximum amount within an order to be shown to other customers, `0` for invisible order (optional) (default to @1)
NSNumber* postOnly = @(YES); // <p>If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.</p> <p>Only valid in combination with time_in_force=`\"good_til_cancelled\"`</p> (optional) (default to @(YES))
NSNumber* reduceOnly = @(NO); // If `true`, the order is considered reduce-only which is intended to only reduce a current position (optional) (default to @(NO))
NSNumber* stopPrice = @56; // Stop price, required for stop limit orders (Only for stop orders) (optional)
NSString* trigger = @"trigger_example"; // Defines trigger type, required for `\"stop_limit\"` order type (optional)
NSString* advanced = @"advanced_example"; // Advanced option order type. (Only for options) (optional)

OAITradingApi*apiInstance = [[OAITradingApi alloc] init];

// Places a sell order for an instrument.
[apiInstance privateSellGetWithInstrumentName:instrumentName
              amount:amount
              type:type
              label:label
              price:price
              timeInForce:timeInForce
              maxShow:maxShow
              postOnly:postOnly
              reduceOnly:reduceOnly
              stopPrice:stopPrice
              trigger:trigger
              advanced:advanced
          completionHandler: ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAITradingApi->privateSellGet: %@", error);
                        }
                    }];
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrumentName** | **NSString***| Instrument name | 
 **amount** | **NSNumber***| It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH | 
 **type** | **NSString***| The order type, default: &#x60;\&quot;limit\&quot;&#x60; | [optional] 
 **label** | **NSString***| user defined label for the order (maximum 32 characters) | [optional] 
 **price** | **NSNumber***| &lt;p&gt;The order price in base currency (Only for limit and stop_limit orders)&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;usd, the field price should be the option price value in USD.&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;implv, the field price should be a value of implied volatility in percentages. For example,  price&#x3D;100, means implied volatility of 100%&lt;/p&gt; | [optional] 
 **timeInForce** | **NSString***| &lt;p&gt;Specifies how long the order remains in effect. Default &#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; &lt;ul&gt; &lt;li&gt;&#x60;\&quot;good_til_cancelled\&quot;&#x60; - unfilled order remains in order book until cancelled&lt;/li&gt; &lt;li&gt;&#x60;\&quot;fill_or_kill\&quot;&#x60; - execute a transaction immediately and completely or not at all&lt;/li&gt; &lt;li&gt;&#x60;\&quot;immediate_or_cancel\&quot;&#x60; - execute a transaction immediately, and any portion of the order that cannot be immediately filled is cancelled&lt;/li&gt; &lt;/ul&gt; | [optional] [default to @&quot;good_til_cancelled&quot;]
 **maxShow** | **NSNumber***| Maximum amount within an order to be shown to other customers, &#x60;0&#x60; for invisible order | [optional] [default to @1]
 **postOnly** | **NSNumber***| &lt;p&gt;If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.&lt;/p&gt; &lt;p&gt;Only valid in combination with time_in_force&#x3D;&#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; | [optional] [default to @(YES)]
 **reduceOnly** | **NSNumber***| If &#x60;true&#x60;, the order is considered reduce-only which is intended to only reduce a current position | [optional] [default to @(NO)]
 **stopPrice** | **NSNumber***| Stop price, required for stop limit orders (Only for stop orders) | [optional] 
 **trigger** | **NSString***| Defines trigger type, required for &#x60;\&quot;stop_limit\&quot;&#x60; order type | [optional] 
 **advanced** | **NSString***| Advanced option order type. (Only for options) | [optional] 

### Return type

**NSObject***

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

