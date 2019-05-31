#import "OAIPrivateApi.h"
#import "OAIQueryParamCollection.h"
#import "OAIApiClient.h"


@interface OAIPrivateApi ()

@property (nonatomic, strong, readwrite) NSMutableDictionary *mutableDefaultHeaders;

@end

@implementation OAIPrivateApi

NSString* kOAIPrivateApiErrorDomain = @"OAIPrivateApiErrorDomain";
NSInteger kOAIPrivateApiMissingParamErrorCode = 234513;

@synthesize apiClient = _apiClient;

#pragma mark - Initialize methods

- (instancetype) init {
    return [self initWithApiClient:[OAIApiClient sharedClient]];
}


-(instancetype) initWithApiClient:(OAIApiClient *)apiClient {
    self = [super init];
    if (self) {
        _apiClient = apiClient;
        _mutableDefaultHeaders = [NSMutableDictionary dictionary];
    }
    return self;
}

#pragma mark -

-(NSString*) defaultHeaderForKey:(NSString*)key {
    return self.mutableDefaultHeaders[key];
}

-(void) setDefaultHeaderValue:(NSString*) value forKey:(NSString*)key {
    [self.mutableDefaultHeaders setValue:value forKey:key];
}

-(NSDictionary *)defaultHeaders {
    return self.mutableDefaultHeaders;
}

#pragma mark - Api Methods

///
/// Adds new entry to address book of given type
/// 
///  @param currency The currency symbol 
///
///  @param type Address book type 
///
///  @param address Address in currency format, it must be in address book 
///
///  @param name Name of address book entry 
///
///  @param tfa TFA code, required when TFA is enabled for current account (optional)
///
///  @returns NSObject*
///
-(NSURLSessionTask*) privateAddToAddressBookGetWithCurrency: (NSString*) currency
    type: (NSString*) type
    address: (NSString*) address
    name: (NSString*) name
    tfa: (NSString*) tfa
    completionHandler: (void (^)(NSObject* output, NSError* error)) handler {
    // verify the required parameter 'currency' is set
    if (currency == nil) {
        NSParameterAssert(currency);
        if(handler) {
            NSDictionary * userInfo = @{NSLocalizedDescriptionKey : [NSString stringWithFormat:NSLocalizedString(@"Missing required parameter '%@'", nil),@"currency"] };
            NSError* error = [NSError errorWithDomain:kOAIPrivateApiErrorDomain code:kOAIPrivateApiMissingParamErrorCode userInfo:userInfo];
            handler(nil, error);
        }
        return nil;
    }

    // verify the required parameter 'type' is set
    if (type == nil) {
        NSParameterAssert(type);
        if(handler) {
            NSDictionary * userInfo = @{NSLocalizedDescriptionKey : [NSString stringWithFormat:NSLocalizedString(@"Missing required parameter '%@'", nil),@"type"] };
            NSError* error = [NSError errorWithDomain:kOAIPrivateApiErrorDomain code:kOAIPrivateApiMissingParamErrorCode userInfo:userInfo];
            handler(nil, error);
        }
        return nil;
    }

    // verify the required parameter 'address' is set
    if (address == nil) {
        NSParameterAssert(address);
        if(handler) {
            NSDictionary * userInfo = @{NSLocalizedDescriptionKey : [NSString stringWithFormat:NSLocalizedString(@"Missing required parameter '%@'", nil),@"address"] };
            NSError* error = [NSError errorWithDomain:kOAIPrivateApiErrorDomain code:kOAIPrivateApiMissingParamErrorCode userInfo:userInfo];
            handler(nil, error);
        }
        return nil;
    }

    // verify the required parameter 'name' is set
    if (name == nil) {
        NSParameterAssert(name);
        if(handler) {
            NSDictionary * userInfo = @{NSLocalizedDescriptionKey : [NSString stringWithFormat:NSLocalizedString(@"Missing required parameter '%@'", nil),@"name"] };
            NSError* error = [NSError errorWithDomain:kOAIPrivateApiErrorDomain code:kOAIPrivateApiMissingParamErrorCode userInfo:userInfo];
            handler(nil, error);
        }
        return nil;
    }

    NSMutableString* resourcePath = [NSMutableString stringWithFormat:@"/private/add_to_address_book"];

    NSMutableDictionary *pathParams = [[NSMutableDictionary alloc] init];

    NSMutableDictionary* queryParams = [[NSMutableDictionary alloc] init];
    if (currency != nil) {
        queryParams[@"currency"] = currency;
    }
    if (type != nil) {
        queryParams[@"type"] = type;
    }
    if (address != nil) {
        queryParams[@"address"] = address;
    }
    if (name != nil) {
        queryParams[@"name"] = name;
    }
    if (tfa != nil) {
        queryParams[@"tfa"] = tfa;
    }
    NSMutableDictionary* headerParams = [NSMutableDictionary dictionaryWithDictionary:self.apiClient.configuration.defaultHeaders];
    [headerParams addEntriesFromDictionary:self.defaultHeaders];
    // HTTP header `Accept`
    NSString *acceptHeader = [self.apiClient.sanitizer selectHeaderAccept:@[@"application/json"]];
    if(acceptHeader.length > 0) {
        headerParams[@"Accept"] = acceptHeader;
    }

    // response content type
    NSString *responseContentType = [[acceptHeader componentsSeparatedByString:@", "] firstObject] ?: @"";

    // request content type
    NSString *requestContentType = [self.apiClient.sanitizer selectHeaderContentType:@[]];

    // Authentication setting
    NSArray *authSettings = @[@"bearerAuth"];

    id bodyParam = nil;
    NSMutableDictionary *formParams = [[NSMutableDictionary alloc] init];
    NSMutableDictionary *localVarFiles = [[NSMutableDictionary alloc] init];

    return [self.apiClient requestWithPath: resourcePath
                                    method: @"GET"
                                pathParams: pathParams
                               queryParams: queryParams
                                formParams: formParams
                                     files: localVarFiles
                                      body: bodyParam
                              headerParams: headerParams
                              authSettings: authSettings
                        requestContentType: requestContentType
                       responseContentType: responseContentType
                              responseType: @"NSObject*"
                           completionBlock: ^(id data, NSError *error) {
                                if(handler) {
                                    handler((NSObject*)data, error);
                                }
                            }];
}

///
/// Places a buy order for an instrument.
/// 
///  @param instrumentName Instrument name 
///
///  @param amount It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH 
///
///  @param type The order type, default: `\"limit\"` (optional)
///
///  @param label user defined label for the order (maximum 32 characters) (optional)
///
///  @param price <p>The order price in base currency (Only for limit and stop_limit orders)</p> <p>When adding order with advanced=usd, the field price should be the option price value in USD.</p> <p>When adding order with advanced=implv, the field price should be a value of implied volatility in percentages. For example,  price=100, means implied volatility of 100%</p> (optional)
///
///  @param timeInForce <p>Specifies how long the order remains in effect. Default `\"good_til_cancelled\"`</p> <ul> <li>`\"good_til_cancelled\"` - unfilled order remains in order book until cancelled</li> <li>`\"fill_or_kill\"` - execute a transaction immediately and completely or not at all</li> <li>`\"immediate_or_cancel\"` - execute a transaction immediately, and any portion of the order that cannot be immediately filled is cancelled</li> </ul> (optional, default to @"good_til_cancelled")
///
///  @param maxShow Maximum amount within an order to be shown to other customers, `0` for invisible order (optional, default to @1)
///
///  @param postOnly <p>If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.</p> <p>Only valid in combination with time_in_force=`\"good_til_cancelled\"`</p> (optional, default to @(YES))
///
///  @param reduceOnly If `true`, the order is considered reduce-only which is intended to only reduce a current position (optional, default to @(NO))
///
///  @param stopPrice Stop price, required for stop limit orders (Only for stop orders) (optional)
///
///  @param trigger Defines trigger type, required for `\"stop_limit\"` order type (optional)
///
///  @param advanced Advanced option order type. (Only for options) (optional)
///
///  @returns NSObject*
///
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
    completionHandler: (void (^)(NSObject* output, NSError* error)) handler {
    // verify the required parameter 'instrumentName' is set
    if (instrumentName == nil) {
        NSParameterAssert(instrumentName);
        if(handler) {
            NSDictionary * userInfo = @{NSLocalizedDescriptionKey : [NSString stringWithFormat:NSLocalizedString(@"Missing required parameter '%@'", nil),@"instrumentName"] };
            NSError* error = [NSError errorWithDomain:kOAIPrivateApiErrorDomain code:kOAIPrivateApiMissingParamErrorCode userInfo:userInfo];
            handler(nil, error);
        }
        return nil;
    }

    // verify the required parameter 'amount' is set
    if (amount == nil) {
        NSParameterAssert(amount);
        if(handler) {
            NSDictionary * userInfo = @{NSLocalizedDescriptionKey : [NSString stringWithFormat:NSLocalizedString(@"Missing required parameter '%@'", nil),@"amount"] };
            NSError* error = [NSError errorWithDomain:kOAIPrivateApiErrorDomain code:kOAIPrivateApiMissingParamErrorCode userInfo:userInfo];
            handler(nil, error);
        }
        return nil;
    }

    NSMutableString* resourcePath = [NSMutableString stringWithFormat:@"/private/buy"];

    NSMutableDictionary *pathParams = [[NSMutableDictionary alloc] init];

    NSMutableDictionary* queryParams = [[NSMutableDictionary alloc] init];
    if (instrumentName != nil) {
        queryParams[@"instrument_name"] = instrumentName;
    }
    if (amount != nil) {
        queryParams[@"amount"] = amount;
    }
    if (type != nil) {
        queryParams[@"type"] = type;
    }
    if (label != nil) {
        queryParams[@"label"] = label;
    }
    if (price != nil) {
        queryParams[@"price"] = price;
    }
    if (timeInForce != nil) {
        queryParams[@"time_in_force"] = timeInForce;
    }
    if (maxShow != nil) {
        queryParams[@"max_show"] = maxShow;
    }
    if (postOnly != nil) {
        queryParams[@"post_only"] = [postOnly isEqual:@(YES)] ? @"true" : @"false";
    }
    if (reduceOnly != nil) {
        queryParams[@"reduce_only"] = [reduceOnly isEqual:@(YES)] ? @"true" : @"false";
    }
    if (stopPrice != nil) {
        queryParams[@"stop_price"] = stopPrice;
    }
    if (trigger != nil) {
        queryParams[@"trigger"] = trigger;
    }
    if (advanced != nil) {
        queryParams[@"advanced"] = advanced;
    }
    NSMutableDictionary* headerParams = [NSMutableDictionary dictionaryWithDictionary:self.apiClient.configuration.defaultHeaders];
    [headerParams addEntriesFromDictionary:self.defaultHeaders];
    // HTTP header `Accept`
    NSString *acceptHeader = [self.apiClient.sanitizer selectHeaderAccept:@[@"application/json"]];
    if(acceptHeader.length > 0) {
        headerParams[@"Accept"] = acceptHeader;
    }

    // response content type
    NSString *responseContentType = [[acceptHeader componentsSeparatedByString:@", "] firstObject] ?: @"";

    // request content type
    NSString *requestContentType = [self.apiClient.sanitizer selectHeaderContentType:@[]];

    // Authentication setting
    NSArray *authSettings = @[@"bearerAuth"];

    id bodyParam = nil;
    NSMutableDictionary *formParams = [[NSMutableDictionary alloc] init];
    NSMutableDictionary *localVarFiles = [[NSMutableDictionary alloc] init];

    return [self.apiClient requestWithPath: resourcePath
                                    method: @"GET"
                                pathParams: pathParams
                               queryParams: queryParams
                                formParams: formParams
                                     files: localVarFiles
                                      body: bodyParam
                              headerParams: headerParams
                              authSettings: authSettings
                        requestContentType: requestContentType
                       responseContentType: responseContentType
                              responseType: @"NSObject*"
                           completionBlock: ^(id data, NSError *error) {
                                if(handler) {
                                    handler((NSObject*)data, error);
                                }
                            }];
}

///
/// Cancels all orders by currency, optionally filtered by instrument kind and/or order type.
/// 
///  @param currency The currency symbol 
///
///  @param kind Instrument kind, if not provided instruments of all kinds are considered (optional)
///
///  @param type Order type - limit, stop or all, default - `all` (optional)
///
///  @returns NSObject*
///
-(NSURLSessionTask*) privateCancelAllByCurrencyGetWithCurrency: (NSString*) currency
    kind: (NSString*) kind
    type: (NSString*) type
    completionHandler: (void (^)(NSObject* output, NSError* error)) handler {
    // verify the required parameter 'currency' is set
    if (currency == nil) {
        NSParameterAssert(currency);
        if(handler) {
            NSDictionary * userInfo = @{NSLocalizedDescriptionKey : [NSString stringWithFormat:NSLocalizedString(@"Missing required parameter '%@'", nil),@"currency"] };
            NSError* error = [NSError errorWithDomain:kOAIPrivateApiErrorDomain code:kOAIPrivateApiMissingParamErrorCode userInfo:userInfo];
            handler(nil, error);
        }
        return nil;
    }

    NSMutableString* resourcePath = [NSMutableString stringWithFormat:@"/private/cancel_all_by_currency"];

    NSMutableDictionary *pathParams = [[NSMutableDictionary alloc] init];

    NSMutableDictionary* queryParams = [[NSMutableDictionary alloc] init];
    if (currency != nil) {
        queryParams[@"currency"] = currency;
    }
    if (kind != nil) {
        queryParams[@"kind"] = kind;
    }
    if (type != nil) {
        queryParams[@"type"] = type;
    }
    NSMutableDictionary* headerParams = [NSMutableDictionary dictionaryWithDictionary:self.apiClient.configuration.defaultHeaders];
    [headerParams addEntriesFromDictionary:self.defaultHeaders];
    // HTTP header `Accept`
    NSString *acceptHeader = [self.apiClient.sanitizer selectHeaderAccept:@[@"application/json"]];
    if(acceptHeader.length > 0) {
        headerParams[@"Accept"] = acceptHeader;
    }

    // response content type
    NSString *responseContentType = [[acceptHeader componentsSeparatedByString:@", "] firstObject] ?: @"";

    // request content type
    NSString *requestContentType = [self.apiClient.sanitizer selectHeaderContentType:@[]];

    // Authentication setting
    NSArray *authSettings = @[@"bearerAuth"];

    id bodyParam = nil;
    NSMutableDictionary *formParams = [[NSMutableDictionary alloc] init];
    NSMutableDictionary *localVarFiles = [[NSMutableDictionary alloc] init];

    return [self.apiClient requestWithPath: resourcePath
                                    method: @"GET"
                                pathParams: pathParams
                               queryParams: queryParams
                                formParams: formParams
                                     files: localVarFiles
                                      body: bodyParam
                              headerParams: headerParams
                              authSettings: authSettings
                        requestContentType: requestContentType
                       responseContentType: responseContentType
                              responseType: @"NSObject*"
                           completionBlock: ^(id data, NSError *error) {
                                if(handler) {
                                    handler((NSObject*)data, error);
                                }
                            }];
}

///
/// Cancels all orders by instrument, optionally filtered by order type.
/// 
///  @param instrumentName Instrument name 
///
///  @param type Order type - limit, stop or all, default - `all` (optional)
///
///  @returns NSObject*
///
-(NSURLSessionTask*) privateCancelAllByInstrumentGetWithInstrumentName: (NSString*) instrumentName
    type: (NSString*) type
    completionHandler: (void (^)(NSObject* output, NSError* error)) handler {
    // verify the required parameter 'instrumentName' is set
    if (instrumentName == nil) {
        NSParameterAssert(instrumentName);
        if(handler) {
            NSDictionary * userInfo = @{NSLocalizedDescriptionKey : [NSString stringWithFormat:NSLocalizedString(@"Missing required parameter '%@'", nil),@"instrumentName"] };
            NSError* error = [NSError errorWithDomain:kOAIPrivateApiErrorDomain code:kOAIPrivateApiMissingParamErrorCode userInfo:userInfo];
            handler(nil, error);
        }
        return nil;
    }

    NSMutableString* resourcePath = [NSMutableString stringWithFormat:@"/private/cancel_all_by_instrument"];

    NSMutableDictionary *pathParams = [[NSMutableDictionary alloc] init];

    NSMutableDictionary* queryParams = [[NSMutableDictionary alloc] init];
    if (instrumentName != nil) {
        queryParams[@"instrument_name"] = instrumentName;
    }
    if (type != nil) {
        queryParams[@"type"] = type;
    }
    NSMutableDictionary* headerParams = [NSMutableDictionary dictionaryWithDictionary:self.apiClient.configuration.defaultHeaders];
    [headerParams addEntriesFromDictionary:self.defaultHeaders];
    // HTTP header `Accept`
    NSString *acceptHeader = [self.apiClient.sanitizer selectHeaderAccept:@[@"application/json"]];
    if(acceptHeader.length > 0) {
        headerParams[@"Accept"] = acceptHeader;
    }

    // response content type
    NSString *responseContentType = [[acceptHeader componentsSeparatedByString:@", "] firstObject] ?: @"";

    // request content type
    NSString *requestContentType = [self.apiClient.sanitizer selectHeaderContentType:@[]];

    // Authentication setting
    NSArray *authSettings = @[@"bearerAuth"];

    id bodyParam = nil;
    NSMutableDictionary *formParams = [[NSMutableDictionary alloc] init];
    NSMutableDictionary *localVarFiles = [[NSMutableDictionary alloc] init];

    return [self.apiClient requestWithPath: resourcePath
                                    method: @"GET"
                                pathParams: pathParams
                               queryParams: queryParams
                                formParams: formParams
                                     files: localVarFiles
                                      body: bodyParam
                              headerParams: headerParams
                              authSettings: authSettings
                        requestContentType: requestContentType
                       responseContentType: responseContentType
                              responseType: @"NSObject*"
                           completionBlock: ^(id data, NSError *error) {
                                if(handler) {
                                    handler((NSObject*)data, error);
                                }
                            }];
}

///
/// This method cancels all users orders and stop orders within all currencies and instrument kinds.
/// 
///  @returns NSObject*
///
-(NSURLSessionTask*) privateCancelAllGetWithCompletionHandler: 
    (void (^)(NSObject* output, NSError* error)) handler {
    NSMutableString* resourcePath = [NSMutableString stringWithFormat:@"/private/cancel_all"];

    NSMutableDictionary *pathParams = [[NSMutableDictionary alloc] init];

    NSMutableDictionary* queryParams = [[NSMutableDictionary alloc] init];
    NSMutableDictionary* headerParams = [NSMutableDictionary dictionaryWithDictionary:self.apiClient.configuration.defaultHeaders];
    [headerParams addEntriesFromDictionary:self.defaultHeaders];
    // HTTP header `Accept`
    NSString *acceptHeader = [self.apiClient.sanitizer selectHeaderAccept:@[@"application/json"]];
    if(acceptHeader.length > 0) {
        headerParams[@"Accept"] = acceptHeader;
    }

    // response content type
    NSString *responseContentType = [[acceptHeader componentsSeparatedByString:@", "] firstObject] ?: @"";

    // request content type
    NSString *requestContentType = [self.apiClient.sanitizer selectHeaderContentType:@[]];

    // Authentication setting
    NSArray *authSettings = @[@"bearerAuth"];

    id bodyParam = nil;
    NSMutableDictionary *formParams = [[NSMutableDictionary alloc] init];
    NSMutableDictionary *localVarFiles = [[NSMutableDictionary alloc] init];

    return [self.apiClient requestWithPath: resourcePath
                                    method: @"GET"
                                pathParams: pathParams
                               queryParams: queryParams
                                formParams: formParams
                                     files: localVarFiles
                                      body: bodyParam
                              headerParams: headerParams
                              authSettings: authSettings
                        requestContentType: requestContentType
                       responseContentType: responseContentType
                              responseType: @"NSObject*"
                           completionBlock: ^(id data, NSError *error) {
                                if(handler) {
                                    handler((NSObject*)data, error);
                                }
                            }];
}

///
/// Cancel an order, specified by order id
/// 
///  @param orderId The order id 
///
///  @returns NSObject*
///
-(NSURLSessionTask*) privateCancelGetWithOrderId: (NSString*) orderId
    completionHandler: (void (^)(NSObject* output, NSError* error)) handler {
    // verify the required parameter 'orderId' is set
    if (orderId == nil) {
        NSParameterAssert(orderId);
        if(handler) {
            NSDictionary * userInfo = @{NSLocalizedDescriptionKey : [NSString stringWithFormat:NSLocalizedString(@"Missing required parameter '%@'", nil),@"orderId"] };
            NSError* error = [NSError errorWithDomain:kOAIPrivateApiErrorDomain code:kOAIPrivateApiMissingParamErrorCode userInfo:userInfo];
            handler(nil, error);
        }
        return nil;
    }

    NSMutableString* resourcePath = [NSMutableString stringWithFormat:@"/private/cancel"];

    NSMutableDictionary *pathParams = [[NSMutableDictionary alloc] init];

    NSMutableDictionary* queryParams = [[NSMutableDictionary alloc] init];
    if (orderId != nil) {
        queryParams[@"order_id"] = orderId;
    }
    NSMutableDictionary* headerParams = [NSMutableDictionary dictionaryWithDictionary:self.apiClient.configuration.defaultHeaders];
    [headerParams addEntriesFromDictionary:self.defaultHeaders];
    // HTTP header `Accept`
    NSString *acceptHeader = [self.apiClient.sanitizer selectHeaderAccept:@[@"application/json"]];
    if(acceptHeader.length > 0) {
        headerParams[@"Accept"] = acceptHeader;
    }

    // response content type
    NSString *responseContentType = [[acceptHeader componentsSeparatedByString:@", "] firstObject] ?: @"";

    // request content type
    NSString *requestContentType = [self.apiClient.sanitizer selectHeaderContentType:@[]];

    // Authentication setting
    NSArray *authSettings = @[@"bearerAuth"];

    id bodyParam = nil;
    NSMutableDictionary *formParams = [[NSMutableDictionary alloc] init];
    NSMutableDictionary *localVarFiles = [[NSMutableDictionary alloc] init];

    return [self.apiClient requestWithPath: resourcePath
                                    method: @"GET"
                                pathParams: pathParams
                               queryParams: queryParams
                                formParams: formParams
                                     files: localVarFiles
                                      body: bodyParam
                              headerParams: headerParams
                              authSettings: authSettings
                        requestContentType: requestContentType
                       responseContentType: responseContentType
                              responseType: @"NSObject*"
                           completionBlock: ^(id data, NSError *error) {
                                if(handler) {
                                    handler((NSObject*)data, error);
                                }
                            }];
}

///
/// Cancel transfer
/// 
///  @param currency The currency symbol 
///
///  @param _id Id of transfer 
///
///  @param tfa TFA code, required when TFA is enabled for current account (optional)
///
///  @returns NSObject*
///
-(NSURLSessionTask*) privateCancelTransferByIdGetWithCurrency: (NSString*) currency
    _id: (NSNumber*) _id
    tfa: (NSString*) tfa
    completionHandler: (void (^)(NSObject* output, NSError* error)) handler {
    // verify the required parameter 'currency' is set
    if (currency == nil) {
        NSParameterAssert(currency);
        if(handler) {
            NSDictionary * userInfo = @{NSLocalizedDescriptionKey : [NSString stringWithFormat:NSLocalizedString(@"Missing required parameter '%@'", nil),@"currency"] };
            NSError* error = [NSError errorWithDomain:kOAIPrivateApiErrorDomain code:kOAIPrivateApiMissingParamErrorCode userInfo:userInfo];
            handler(nil, error);
        }
        return nil;
    }

    // verify the required parameter '_id' is set
    if (_id == nil) {
        NSParameterAssert(_id);
        if(handler) {
            NSDictionary * userInfo = @{NSLocalizedDescriptionKey : [NSString stringWithFormat:NSLocalizedString(@"Missing required parameter '%@'", nil),@"_id"] };
            NSError* error = [NSError errorWithDomain:kOAIPrivateApiErrorDomain code:kOAIPrivateApiMissingParamErrorCode userInfo:userInfo];
            handler(nil, error);
        }
        return nil;
    }

    NSMutableString* resourcePath = [NSMutableString stringWithFormat:@"/private/cancel_transfer_by_id"];

    NSMutableDictionary *pathParams = [[NSMutableDictionary alloc] init];

    NSMutableDictionary* queryParams = [[NSMutableDictionary alloc] init];
    if (currency != nil) {
        queryParams[@"currency"] = currency;
    }
    if (_id != nil) {
        queryParams[@"id"] = _id;
    }
    if (tfa != nil) {
        queryParams[@"tfa"] = tfa;
    }
    NSMutableDictionary* headerParams = [NSMutableDictionary dictionaryWithDictionary:self.apiClient.configuration.defaultHeaders];
    [headerParams addEntriesFromDictionary:self.defaultHeaders];
    // HTTP header `Accept`
    NSString *acceptHeader = [self.apiClient.sanitizer selectHeaderAccept:@[@"application/json"]];
    if(acceptHeader.length > 0) {
        headerParams[@"Accept"] = acceptHeader;
    }

    // response content type
    NSString *responseContentType = [[acceptHeader componentsSeparatedByString:@", "] firstObject] ?: @"";

    // request content type
    NSString *requestContentType = [self.apiClient.sanitizer selectHeaderContentType:@[]];

    // Authentication setting
    NSArray *authSettings = @[@"bearerAuth"];

    id bodyParam = nil;
    NSMutableDictionary *formParams = [[NSMutableDictionary alloc] init];
    NSMutableDictionary *localVarFiles = [[NSMutableDictionary alloc] init];

    return [self.apiClient requestWithPath: resourcePath
                                    method: @"GET"
                                pathParams: pathParams
                               queryParams: queryParams
                                formParams: formParams
                                     files: localVarFiles
                                      body: bodyParam
                              headerParams: headerParams
                              authSettings: authSettings
                        requestContentType: requestContentType
                       responseContentType: responseContentType
                              responseType: @"NSObject*"
                           completionBlock: ^(id data, NSError *error) {
                                if(handler) {
                                    handler((NSObject*)data, error);
                                }
                            }];
}

///
/// Cancels withdrawal request
/// 
///  @param currency The currency symbol 
///
///  @param _id The withdrawal id 
///
///  @returns NSObject*
///
-(NSURLSessionTask*) privateCancelWithdrawalGetWithCurrency: (NSString*) currency
    _id: (NSNumber*) _id
    completionHandler: (void (^)(NSObject* output, NSError* error)) handler {
    // verify the required parameter 'currency' is set
    if (currency == nil) {
        NSParameterAssert(currency);
        if(handler) {
            NSDictionary * userInfo = @{NSLocalizedDescriptionKey : [NSString stringWithFormat:NSLocalizedString(@"Missing required parameter '%@'", nil),@"currency"] };
            NSError* error = [NSError errorWithDomain:kOAIPrivateApiErrorDomain code:kOAIPrivateApiMissingParamErrorCode userInfo:userInfo];
            handler(nil, error);
        }
        return nil;
    }

    // verify the required parameter '_id' is set
    if (_id == nil) {
        NSParameterAssert(_id);
        if(handler) {
            NSDictionary * userInfo = @{NSLocalizedDescriptionKey : [NSString stringWithFormat:NSLocalizedString(@"Missing required parameter '%@'", nil),@"_id"] };
            NSError* error = [NSError errorWithDomain:kOAIPrivateApiErrorDomain code:kOAIPrivateApiMissingParamErrorCode userInfo:userInfo];
            handler(nil, error);
        }
        return nil;
    }

    NSMutableString* resourcePath = [NSMutableString stringWithFormat:@"/private/cancel_withdrawal"];

    NSMutableDictionary *pathParams = [[NSMutableDictionary alloc] init];

    NSMutableDictionary* queryParams = [[NSMutableDictionary alloc] init];
    if (currency != nil) {
        queryParams[@"currency"] = currency;
    }
    if (_id != nil) {
        queryParams[@"id"] = _id;
    }
    NSMutableDictionary* headerParams = [NSMutableDictionary dictionaryWithDictionary:self.apiClient.configuration.defaultHeaders];
    [headerParams addEntriesFromDictionary:self.defaultHeaders];
    // HTTP header `Accept`
    NSString *acceptHeader = [self.apiClient.sanitizer selectHeaderAccept:@[@"application/json"]];
    if(acceptHeader.length > 0) {
        headerParams[@"Accept"] = acceptHeader;
    }

    // response content type
    NSString *responseContentType = [[acceptHeader componentsSeparatedByString:@", "] firstObject] ?: @"";

    // request content type
    NSString *requestContentType = [self.apiClient.sanitizer selectHeaderContentType:@[]];

    // Authentication setting
    NSArray *authSettings = @[@"bearerAuth"];

    id bodyParam = nil;
    NSMutableDictionary *formParams = [[NSMutableDictionary alloc] init];
    NSMutableDictionary *localVarFiles = [[NSMutableDictionary alloc] init];

    return [self.apiClient requestWithPath: resourcePath
                                    method: @"GET"
                                pathParams: pathParams
                               queryParams: queryParams
                                formParams: formParams
                                     files: localVarFiles
                                      body: bodyParam
                              headerParams: headerParams
                              authSettings: authSettings
                        requestContentType: requestContentType
                       responseContentType: responseContentType
                              responseType: @"NSObject*"
                           completionBlock: ^(id data, NSError *error) {
                                if(handler) {
                                    handler((NSObject*)data, error);
                                }
                            }];
}

///
/// Change the user name for a subaccount
/// 
///  @param sid The user id for the subaccount 
///
///  @param name The new user name 
///
///  @returns NSObject*
///
-(NSURLSessionTask*) privateChangeSubaccountNameGetWithSid: (NSNumber*) sid
    name: (NSString*) name
    completionHandler: (void (^)(NSObject* output, NSError* error)) handler {
    // verify the required parameter 'sid' is set
    if (sid == nil) {
        NSParameterAssert(sid);
        if(handler) {
            NSDictionary * userInfo = @{NSLocalizedDescriptionKey : [NSString stringWithFormat:NSLocalizedString(@"Missing required parameter '%@'", nil),@"sid"] };
            NSError* error = [NSError errorWithDomain:kOAIPrivateApiErrorDomain code:kOAIPrivateApiMissingParamErrorCode userInfo:userInfo];
            handler(nil, error);
        }
        return nil;
    }

    // verify the required parameter 'name' is set
    if (name == nil) {
        NSParameterAssert(name);
        if(handler) {
            NSDictionary * userInfo = @{NSLocalizedDescriptionKey : [NSString stringWithFormat:NSLocalizedString(@"Missing required parameter '%@'", nil),@"name"] };
            NSError* error = [NSError errorWithDomain:kOAIPrivateApiErrorDomain code:kOAIPrivateApiMissingParamErrorCode userInfo:userInfo];
            handler(nil, error);
        }
        return nil;
    }

    NSMutableString* resourcePath = [NSMutableString stringWithFormat:@"/private/change_subaccount_name"];

    NSMutableDictionary *pathParams = [[NSMutableDictionary alloc] init];

    NSMutableDictionary* queryParams = [[NSMutableDictionary alloc] init];
    if (sid != nil) {
        queryParams[@"sid"] = sid;
    }
    if (name != nil) {
        queryParams[@"name"] = name;
    }
    NSMutableDictionary* headerParams = [NSMutableDictionary dictionaryWithDictionary:self.apiClient.configuration.defaultHeaders];
    [headerParams addEntriesFromDictionary:self.defaultHeaders];
    // HTTP header `Accept`
    NSString *acceptHeader = [self.apiClient.sanitizer selectHeaderAccept:@[@"application/json"]];
    if(acceptHeader.length > 0) {
        headerParams[@"Accept"] = acceptHeader;
    }

    // response content type
    NSString *responseContentType = [[acceptHeader componentsSeparatedByString:@", "] firstObject] ?: @"";

    // request content type
    NSString *requestContentType = [self.apiClient.sanitizer selectHeaderContentType:@[]];

    // Authentication setting
    NSArray *authSettings = @[@"bearerAuth"];

    id bodyParam = nil;
    NSMutableDictionary *formParams = [[NSMutableDictionary alloc] init];
    NSMutableDictionary *localVarFiles = [[NSMutableDictionary alloc] init];

    return [self.apiClient requestWithPath: resourcePath
                                    method: @"GET"
                                pathParams: pathParams
                               queryParams: queryParams
                                formParams: formParams
                                     files: localVarFiles
                                      body: bodyParam
                              headerParams: headerParams
                              authSettings: authSettings
                        requestContentType: requestContentType
                       responseContentType: responseContentType
                              responseType: @"NSObject*"
                           completionBlock: ^(id data, NSError *error) {
                                if(handler) {
                                    handler((NSObject*)data, error);
                                }
                            }];
}

///
/// Makes closing position reduce only order .
/// 
///  @param instrumentName Instrument name 
///
///  @param type The order type 
///
///  @param price Optional price for limit order. (optional)
///
///  @returns NSObject*
///
-(NSURLSessionTask*) privateClosePositionGetWithInstrumentName: (NSString*) instrumentName
    type: (NSString*) type
    price: (NSNumber*) price
    completionHandler: (void (^)(NSObject* output, NSError* error)) handler {
    // verify the required parameter 'instrumentName' is set
    if (instrumentName == nil) {
        NSParameterAssert(instrumentName);
        if(handler) {
            NSDictionary * userInfo = @{NSLocalizedDescriptionKey : [NSString stringWithFormat:NSLocalizedString(@"Missing required parameter '%@'", nil),@"instrumentName"] };
            NSError* error = [NSError errorWithDomain:kOAIPrivateApiErrorDomain code:kOAIPrivateApiMissingParamErrorCode userInfo:userInfo];
            handler(nil, error);
        }
        return nil;
    }

    // verify the required parameter 'type' is set
    if (type == nil) {
        NSParameterAssert(type);
        if(handler) {
            NSDictionary * userInfo = @{NSLocalizedDescriptionKey : [NSString stringWithFormat:NSLocalizedString(@"Missing required parameter '%@'", nil),@"type"] };
            NSError* error = [NSError errorWithDomain:kOAIPrivateApiErrorDomain code:kOAIPrivateApiMissingParamErrorCode userInfo:userInfo];
            handler(nil, error);
        }
        return nil;
    }

    NSMutableString* resourcePath = [NSMutableString stringWithFormat:@"/private/close_position"];

    NSMutableDictionary *pathParams = [[NSMutableDictionary alloc] init];

    NSMutableDictionary* queryParams = [[NSMutableDictionary alloc] init];
    if (instrumentName != nil) {
        queryParams[@"instrument_name"] = instrumentName;
    }
    if (type != nil) {
        queryParams[@"type"] = type;
    }
    if (price != nil) {
        queryParams[@"price"] = price;
    }
    NSMutableDictionary* headerParams = [NSMutableDictionary dictionaryWithDictionary:self.apiClient.configuration.defaultHeaders];
    [headerParams addEntriesFromDictionary:self.defaultHeaders];
    // HTTP header `Accept`
    NSString *acceptHeader = [self.apiClient.sanitizer selectHeaderAccept:@[@"application/json"]];
    if(acceptHeader.length > 0) {
        headerParams[@"Accept"] = acceptHeader;
    }

    // response content type
    NSString *responseContentType = [[acceptHeader componentsSeparatedByString:@", "] firstObject] ?: @"";

    // request content type
    NSString *requestContentType = [self.apiClient.sanitizer selectHeaderContentType:@[]];

    // Authentication setting
    NSArray *authSettings = @[@"bearerAuth"];

    id bodyParam = nil;
    NSMutableDictionary *formParams = [[NSMutableDictionary alloc] init];
    NSMutableDictionary *localVarFiles = [[NSMutableDictionary alloc] init];

    return [self.apiClient requestWithPath: resourcePath
                                    method: @"GET"
                                pathParams: pathParams
                               queryParams: queryParams
                                formParams: formParams
                                     files: localVarFiles
                                      body: bodyParam
                              headerParams: headerParams
                              authSettings: authSettings
                        requestContentType: requestContentType
                       responseContentType: responseContentType
                              responseType: @"NSObject*"
                           completionBlock: ^(id data, NSError *error) {
                                if(handler) {
                                    handler((NSObject*)data, error);
                                }
                            }];
}

///
/// Creates deposit address in currency
/// 
///  @param currency The currency symbol 
///
///  @returns NSObject*
///
-(NSURLSessionTask*) privateCreateDepositAddressGetWithCurrency: (NSString*) currency
    completionHandler: (void (^)(NSObject* output, NSError* error)) handler {
    // verify the required parameter 'currency' is set
    if (currency == nil) {
        NSParameterAssert(currency);
        if(handler) {
            NSDictionary * userInfo = @{NSLocalizedDescriptionKey : [NSString stringWithFormat:NSLocalizedString(@"Missing required parameter '%@'", nil),@"currency"] };
            NSError* error = [NSError errorWithDomain:kOAIPrivateApiErrorDomain code:kOAIPrivateApiMissingParamErrorCode userInfo:userInfo];
            handler(nil, error);
        }
        return nil;
    }

    NSMutableString* resourcePath = [NSMutableString stringWithFormat:@"/private/create_deposit_address"];

    NSMutableDictionary *pathParams = [[NSMutableDictionary alloc] init];

    NSMutableDictionary* queryParams = [[NSMutableDictionary alloc] init];
    if (currency != nil) {
        queryParams[@"currency"] = currency;
    }
    NSMutableDictionary* headerParams = [NSMutableDictionary dictionaryWithDictionary:self.apiClient.configuration.defaultHeaders];
    [headerParams addEntriesFromDictionary:self.defaultHeaders];
    // HTTP header `Accept`
    NSString *acceptHeader = [self.apiClient.sanitizer selectHeaderAccept:@[@"application/json"]];
    if(acceptHeader.length > 0) {
        headerParams[@"Accept"] = acceptHeader;
    }

    // response content type
    NSString *responseContentType = [[acceptHeader componentsSeparatedByString:@", "] firstObject] ?: @"";

    // request content type
    NSString *requestContentType = [self.apiClient.sanitizer selectHeaderContentType:@[]];

    // Authentication setting
    NSArray *authSettings = @[@"bearerAuth"];

    id bodyParam = nil;
    NSMutableDictionary *formParams = [[NSMutableDictionary alloc] init];
    NSMutableDictionary *localVarFiles = [[NSMutableDictionary alloc] init];

    return [self.apiClient requestWithPath: resourcePath
                                    method: @"GET"
                                pathParams: pathParams
                               queryParams: queryParams
                                formParams: formParams
                                     files: localVarFiles
                                      body: bodyParam
                              headerParams: headerParams
                              authSettings: authSettings
                        requestContentType: requestContentType
                       responseContentType: responseContentType
                              responseType: @"NSObject*"
                           completionBlock: ^(id data, NSError *error) {
                                if(handler) {
                                    handler((NSObject*)data, error);
                                }
                            }];
}

///
/// Create a new subaccount
/// 
///  @returns NSObject*
///
-(NSURLSessionTask*) privateCreateSubaccountGetWithCompletionHandler: 
    (void (^)(NSObject* output, NSError* error)) handler {
    NSMutableString* resourcePath = [NSMutableString stringWithFormat:@"/private/create_subaccount"];

    NSMutableDictionary *pathParams = [[NSMutableDictionary alloc] init];

    NSMutableDictionary* queryParams = [[NSMutableDictionary alloc] init];
    NSMutableDictionary* headerParams = [NSMutableDictionary dictionaryWithDictionary:self.apiClient.configuration.defaultHeaders];
    [headerParams addEntriesFromDictionary:self.defaultHeaders];
    // HTTP header `Accept`
    NSString *acceptHeader = [self.apiClient.sanitizer selectHeaderAccept:@[@"application/json"]];
    if(acceptHeader.length > 0) {
        headerParams[@"Accept"] = acceptHeader;
    }

    // response content type
    NSString *responseContentType = [[acceptHeader componentsSeparatedByString:@", "] firstObject] ?: @"";

    // request content type
    NSString *requestContentType = [self.apiClient.sanitizer selectHeaderContentType:@[]];

    // Authentication setting
    NSArray *authSettings = @[@"bearerAuth"];

    id bodyParam = nil;
    NSMutableDictionary *formParams = [[NSMutableDictionary alloc] init];
    NSMutableDictionary *localVarFiles = [[NSMutableDictionary alloc] init];

    return [self.apiClient requestWithPath: resourcePath
                                    method: @"GET"
                                pathParams: pathParams
                               queryParams: queryParams
                                formParams: formParams
                                     files: localVarFiles
                                      body: bodyParam
                              headerParams: headerParams
                              authSettings: authSettings
                        requestContentType: requestContentType
                       responseContentType: responseContentType
                              responseType: @"NSObject*"
                           completionBlock: ^(id data, NSError *error) {
                                if(handler) {
                                    handler((NSObject*)data, error);
                                }
                            }];
}

///
/// Disable two factor authentication for a subaccount.
/// 
///  @param sid The user id for the subaccount 
///
///  @returns NSObject*
///
-(NSURLSessionTask*) privateDisableTfaForSubaccountGetWithSid: (NSNumber*) sid
    completionHandler: (void (^)(NSObject* output, NSError* error)) handler {
    // verify the required parameter 'sid' is set
    if (sid == nil) {
        NSParameterAssert(sid);
        if(handler) {
            NSDictionary * userInfo = @{NSLocalizedDescriptionKey : [NSString stringWithFormat:NSLocalizedString(@"Missing required parameter '%@'", nil),@"sid"] };
            NSError* error = [NSError errorWithDomain:kOAIPrivateApiErrorDomain code:kOAIPrivateApiMissingParamErrorCode userInfo:userInfo];
            handler(nil, error);
        }
        return nil;
    }

    NSMutableString* resourcePath = [NSMutableString stringWithFormat:@"/private/disable_tfa_for_subaccount"];

    NSMutableDictionary *pathParams = [[NSMutableDictionary alloc] init];

    NSMutableDictionary* queryParams = [[NSMutableDictionary alloc] init];
    if (sid != nil) {
        queryParams[@"sid"] = sid;
    }
    NSMutableDictionary* headerParams = [NSMutableDictionary dictionaryWithDictionary:self.apiClient.configuration.defaultHeaders];
    [headerParams addEntriesFromDictionary:self.defaultHeaders];
    // HTTP header `Accept`
    NSString *acceptHeader = [self.apiClient.sanitizer selectHeaderAccept:@[@"application/json"]];
    if(acceptHeader.length > 0) {
        headerParams[@"Accept"] = acceptHeader;
    }

    // response content type
    NSString *responseContentType = [[acceptHeader componentsSeparatedByString:@", "] firstObject] ?: @"";

    // request content type
    NSString *requestContentType = [self.apiClient.sanitizer selectHeaderContentType:@[]];

    // Authentication setting
    NSArray *authSettings = @[@"bearerAuth"];

    id bodyParam = nil;
    NSMutableDictionary *formParams = [[NSMutableDictionary alloc] init];
    NSMutableDictionary *localVarFiles = [[NSMutableDictionary alloc] init];

    return [self.apiClient requestWithPath: resourcePath
                                    method: @"GET"
                                pathParams: pathParams
                               queryParams: queryParams
                                formParams: formParams
                                     files: localVarFiles
                                      body: bodyParam
                              headerParams: headerParams
                              authSettings: authSettings
                        requestContentType: requestContentType
                       responseContentType: responseContentType
                              responseType: @"NSObject*"
                           completionBlock: ^(id data, NSError *error) {
                                if(handler) {
                                    handler((NSObject*)data, error);
                                }
                            }];
}

///
/// Disables TFA with one time recovery code
/// 
///  @param password The password for the subaccount 
///
///  @param code One time recovery code 
///
///  @returns NSObject*
///
-(NSURLSessionTask*) privateDisableTfaWithRecoveryCodeGetWithPassword: (NSString*) password
    code: (NSString*) code
    completionHandler: (void (^)(NSObject* output, NSError* error)) handler {
    // verify the required parameter 'password' is set
    if (password == nil) {
        NSParameterAssert(password);
        if(handler) {
            NSDictionary * userInfo = @{NSLocalizedDescriptionKey : [NSString stringWithFormat:NSLocalizedString(@"Missing required parameter '%@'", nil),@"password"] };
            NSError* error = [NSError errorWithDomain:kOAIPrivateApiErrorDomain code:kOAIPrivateApiMissingParamErrorCode userInfo:userInfo];
            handler(nil, error);
        }
        return nil;
    }

    // verify the required parameter 'code' is set
    if (code == nil) {
        NSParameterAssert(code);
        if(handler) {
            NSDictionary * userInfo = @{NSLocalizedDescriptionKey : [NSString stringWithFormat:NSLocalizedString(@"Missing required parameter '%@'", nil),@"code"] };
            NSError* error = [NSError errorWithDomain:kOAIPrivateApiErrorDomain code:kOAIPrivateApiMissingParamErrorCode userInfo:userInfo];
            handler(nil, error);
        }
        return nil;
    }

    NSMutableString* resourcePath = [NSMutableString stringWithFormat:@"/private/disable_tfa_with_recovery_code"];

    NSMutableDictionary *pathParams = [[NSMutableDictionary alloc] init];

    NSMutableDictionary* queryParams = [[NSMutableDictionary alloc] init];
    if (password != nil) {
        queryParams[@"password"] = password;
    }
    if (code != nil) {
        queryParams[@"code"] = code;
    }
    NSMutableDictionary* headerParams = [NSMutableDictionary dictionaryWithDictionary:self.apiClient.configuration.defaultHeaders];
    [headerParams addEntriesFromDictionary:self.defaultHeaders];
    // HTTP header `Accept`
    NSString *acceptHeader = [self.apiClient.sanitizer selectHeaderAccept:@[@"application/json"]];
    if(acceptHeader.length > 0) {
        headerParams[@"Accept"] = acceptHeader;
    }

    // response content type
    NSString *responseContentType = [[acceptHeader componentsSeparatedByString:@", "] firstObject] ?: @"";

    // request content type
    NSString *requestContentType = [self.apiClient.sanitizer selectHeaderContentType:@[]];

    // Authentication setting
    NSArray *authSettings = @[@"bearerAuth"];

    id bodyParam = nil;
    NSMutableDictionary *formParams = [[NSMutableDictionary alloc] init];
    NSMutableDictionary *localVarFiles = [[NSMutableDictionary alloc] init];

    return [self.apiClient requestWithPath: resourcePath
                                    method: @"GET"
                                pathParams: pathParams
                               queryParams: queryParams
                                formParams: formParams
                                     files: localVarFiles
                                      body: bodyParam
                              headerParams: headerParams
                              authSettings: authSettings
                        requestContentType: requestContentType
                       responseContentType: responseContentType
                              responseType: @"NSObject*"
                           completionBlock: ^(id data, NSError *error) {
                                if(handler) {
                                    handler((NSObject*)data, error);
                                }
                            }];
}

///
/// Change price, amount and/or other properties of an order.
/// 
///  @param orderId The order id 
///
///  @param amount It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH 
///
///  @param price <p>The order price in base currency.</p> <p>When editing an option order with advanced=usd, the field price should be the option price value in USD.</p> <p>When editing an option order with advanced=implv, the field price should be a value of implied volatility in percentages. For example,  price=100, means implied volatility of 100%</p> 
///
///  @param postOnly <p>If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.</p> <p>Only valid in combination with time_in_force=`\"good_til_cancelled\"`</p> (optional, default to @(YES))
///
///  @param advanced Advanced option order type. If you have posted an advanced option order, it is necessary to re-supply this parameter when editing it (Only for options) (optional)
///
///  @param stopPrice Stop price, required for stop limit orders (Only for stop orders) (optional)
///
///  @returns NSObject*
///
-(NSURLSessionTask*) privateEditGetWithOrderId: (NSString*) orderId
    amount: (NSNumber*) amount
    price: (NSNumber*) price
    postOnly: (NSNumber*) postOnly
    advanced: (NSString*) advanced
    stopPrice: (NSNumber*) stopPrice
    completionHandler: (void (^)(NSObject* output, NSError* error)) handler {
    // verify the required parameter 'orderId' is set
    if (orderId == nil) {
        NSParameterAssert(orderId);
        if(handler) {
            NSDictionary * userInfo = @{NSLocalizedDescriptionKey : [NSString stringWithFormat:NSLocalizedString(@"Missing required parameter '%@'", nil),@"orderId"] };
            NSError* error = [NSError errorWithDomain:kOAIPrivateApiErrorDomain code:kOAIPrivateApiMissingParamErrorCode userInfo:userInfo];
            handler(nil, error);
        }
        return nil;
    }

    // verify the required parameter 'amount' is set
    if (amount == nil) {
        NSParameterAssert(amount);
        if(handler) {
            NSDictionary * userInfo = @{NSLocalizedDescriptionKey : [NSString stringWithFormat:NSLocalizedString(@"Missing required parameter '%@'", nil),@"amount"] };
            NSError* error = [NSError errorWithDomain:kOAIPrivateApiErrorDomain code:kOAIPrivateApiMissingParamErrorCode userInfo:userInfo];
            handler(nil, error);
        }
        return nil;
    }

    // verify the required parameter 'price' is set
    if (price == nil) {
        NSParameterAssert(price);
        if(handler) {
            NSDictionary * userInfo = @{NSLocalizedDescriptionKey : [NSString stringWithFormat:NSLocalizedString(@"Missing required parameter '%@'", nil),@"price"] };
            NSError* error = [NSError errorWithDomain:kOAIPrivateApiErrorDomain code:kOAIPrivateApiMissingParamErrorCode userInfo:userInfo];
            handler(nil, error);
        }
        return nil;
    }

    NSMutableString* resourcePath = [NSMutableString stringWithFormat:@"/private/edit"];

    NSMutableDictionary *pathParams = [[NSMutableDictionary alloc] init];

    NSMutableDictionary* queryParams = [[NSMutableDictionary alloc] init];
    if (orderId != nil) {
        queryParams[@"order_id"] = orderId;
    }
    if (amount != nil) {
        queryParams[@"amount"] = amount;
    }
    if (price != nil) {
        queryParams[@"price"] = price;
    }
    if (postOnly != nil) {
        queryParams[@"post_only"] = [postOnly isEqual:@(YES)] ? @"true" : @"false";
    }
    if (advanced != nil) {
        queryParams[@"advanced"] = advanced;
    }
    if (stopPrice != nil) {
        queryParams[@"stop_price"] = stopPrice;
    }
    NSMutableDictionary* headerParams = [NSMutableDictionary dictionaryWithDictionary:self.apiClient.configuration.defaultHeaders];
    [headerParams addEntriesFromDictionary:self.defaultHeaders];
    // HTTP header `Accept`
    NSString *acceptHeader = [self.apiClient.sanitizer selectHeaderAccept:@[@"application/json"]];
    if(acceptHeader.length > 0) {
        headerParams[@"Accept"] = acceptHeader;
    }

    // response content type
    NSString *responseContentType = [[acceptHeader componentsSeparatedByString:@", "] firstObject] ?: @"";

    // request content type
    NSString *requestContentType = [self.apiClient.sanitizer selectHeaderContentType:@[]];

    // Authentication setting
    NSArray *authSettings = @[@"bearerAuth"];

    id bodyParam = nil;
    NSMutableDictionary *formParams = [[NSMutableDictionary alloc] init];
    NSMutableDictionary *localVarFiles = [[NSMutableDictionary alloc] init];

    return [self.apiClient requestWithPath: resourcePath
                                    method: @"GET"
                                pathParams: pathParams
                               queryParams: queryParams
                                formParams: formParams
                                     files: localVarFiles
                                      body: bodyParam
                              headerParams: headerParams
                              authSettings: authSettings
                        requestContentType: requestContentType
                       responseContentType: responseContentType
                              responseType: @"NSObject*"
                           completionBlock: ^(id data, NSError *error) {
                                if(handler) {
                                    handler((NSObject*)data, error);
                                }
                            }];
}

///
/// Retrieves user account summary.
/// 
///  @param currency The currency symbol 
///
///  @param extended Include additional fields (optional)
///
///  @returns NSObject*
///
-(NSURLSessionTask*) privateGetAccountSummaryGetWithCurrency: (NSString*) currency
    extended: (NSNumber*) extended
    completionHandler: (void (^)(NSObject* output, NSError* error)) handler {
    // verify the required parameter 'currency' is set
    if (currency == nil) {
        NSParameterAssert(currency);
        if(handler) {
            NSDictionary * userInfo = @{NSLocalizedDescriptionKey : [NSString stringWithFormat:NSLocalizedString(@"Missing required parameter '%@'", nil),@"currency"] };
            NSError* error = [NSError errorWithDomain:kOAIPrivateApiErrorDomain code:kOAIPrivateApiMissingParamErrorCode userInfo:userInfo];
            handler(nil, error);
        }
        return nil;
    }

    NSMutableString* resourcePath = [NSMutableString stringWithFormat:@"/private/get_account_summary"];

    NSMutableDictionary *pathParams = [[NSMutableDictionary alloc] init];

    NSMutableDictionary* queryParams = [[NSMutableDictionary alloc] init];
    if (currency != nil) {
        queryParams[@"currency"] = currency;
    }
    if (extended != nil) {
        queryParams[@"extended"] = [extended isEqual:@(YES)] ? @"true" : @"false";
    }
    NSMutableDictionary* headerParams = [NSMutableDictionary dictionaryWithDictionary:self.apiClient.configuration.defaultHeaders];
    [headerParams addEntriesFromDictionary:self.defaultHeaders];
    // HTTP header `Accept`
    NSString *acceptHeader = [self.apiClient.sanitizer selectHeaderAccept:@[@"application/json"]];
    if(acceptHeader.length > 0) {
        headerParams[@"Accept"] = acceptHeader;
    }

    // response content type
    NSString *responseContentType = [[acceptHeader componentsSeparatedByString:@", "] firstObject] ?: @"";

    // request content type
    NSString *requestContentType = [self.apiClient.sanitizer selectHeaderContentType:@[]];

    // Authentication setting
    NSArray *authSettings = @[@"bearerAuth"];

    id bodyParam = nil;
    NSMutableDictionary *formParams = [[NSMutableDictionary alloc] init];
    NSMutableDictionary *localVarFiles = [[NSMutableDictionary alloc] init];

    return [self.apiClient requestWithPath: resourcePath
                                    method: @"GET"
                                pathParams: pathParams
                               queryParams: queryParams
                                formParams: formParams
                                     files: localVarFiles
                                      body: bodyParam
                              headerParams: headerParams
                              authSettings: authSettings
                        requestContentType: requestContentType
                       responseContentType: responseContentType
                              responseType: @"NSObject*"
                           completionBlock: ^(id data, NSError *error) {
                                if(handler) {
                                    handler((NSObject*)data, error);
                                }
                            }];
}

///
/// Retrieves address book of given type
/// 
///  @param currency The currency symbol 
///
///  @param type Address book type 
///
///  @returns NSObject*
///
-(NSURLSessionTask*) privateGetAddressBookGetWithCurrency: (NSString*) currency
    type: (NSString*) type
    completionHandler: (void (^)(NSObject* output, NSError* error)) handler {
    // verify the required parameter 'currency' is set
    if (currency == nil) {
        NSParameterAssert(currency);
        if(handler) {
            NSDictionary * userInfo = @{NSLocalizedDescriptionKey : [NSString stringWithFormat:NSLocalizedString(@"Missing required parameter '%@'", nil),@"currency"] };
            NSError* error = [NSError errorWithDomain:kOAIPrivateApiErrorDomain code:kOAIPrivateApiMissingParamErrorCode userInfo:userInfo];
            handler(nil, error);
        }
        return nil;
    }

    // verify the required parameter 'type' is set
    if (type == nil) {
        NSParameterAssert(type);
        if(handler) {
            NSDictionary * userInfo = @{NSLocalizedDescriptionKey : [NSString stringWithFormat:NSLocalizedString(@"Missing required parameter '%@'", nil),@"type"] };
            NSError* error = [NSError errorWithDomain:kOAIPrivateApiErrorDomain code:kOAIPrivateApiMissingParamErrorCode userInfo:userInfo];
            handler(nil, error);
        }
        return nil;
    }

    NSMutableString* resourcePath = [NSMutableString stringWithFormat:@"/private/get_address_book"];

    NSMutableDictionary *pathParams = [[NSMutableDictionary alloc] init];

    NSMutableDictionary* queryParams = [[NSMutableDictionary alloc] init];
    if (currency != nil) {
        queryParams[@"currency"] = currency;
    }
    if (type != nil) {
        queryParams[@"type"] = type;
    }
    NSMutableDictionary* headerParams = [NSMutableDictionary dictionaryWithDictionary:self.apiClient.configuration.defaultHeaders];
    [headerParams addEntriesFromDictionary:self.defaultHeaders];
    // HTTP header `Accept`
    NSString *acceptHeader = [self.apiClient.sanitizer selectHeaderAccept:@[@"application/json"]];
    if(acceptHeader.length > 0) {
        headerParams[@"Accept"] = acceptHeader;
    }

    // response content type
    NSString *responseContentType = [[acceptHeader componentsSeparatedByString:@", "] firstObject] ?: @"";

    // request content type
    NSString *requestContentType = [self.apiClient.sanitizer selectHeaderContentType:@[]];

    // Authentication setting
    NSArray *authSettings = @[@"bearerAuth"];

    id bodyParam = nil;
    NSMutableDictionary *formParams = [[NSMutableDictionary alloc] init];
    NSMutableDictionary *localVarFiles = [[NSMutableDictionary alloc] init];

    return [self.apiClient requestWithPath: resourcePath
                                    method: @"GET"
                                pathParams: pathParams
                               queryParams: queryParams
                                formParams: formParams
                                     files: localVarFiles
                                      body: bodyParam
                              headerParams: headerParams
                              authSettings: authSettings
                        requestContentType: requestContentType
                       responseContentType: responseContentType
                              responseType: @"NSObject*"
                           completionBlock: ^(id data, NSError *error) {
                                if(handler) {
                                    handler((NSObject*)data, error);
                                }
                            }];
}

///
/// Retrieve deposit address for currency
/// 
///  @param currency The currency symbol 
///
///  @returns NSObject*
///
-(NSURLSessionTask*) privateGetCurrentDepositAddressGetWithCurrency: (NSString*) currency
    completionHandler: (void (^)(NSObject* output, NSError* error)) handler {
    // verify the required parameter 'currency' is set
    if (currency == nil) {
        NSParameterAssert(currency);
        if(handler) {
            NSDictionary * userInfo = @{NSLocalizedDescriptionKey : [NSString stringWithFormat:NSLocalizedString(@"Missing required parameter '%@'", nil),@"currency"] };
            NSError* error = [NSError errorWithDomain:kOAIPrivateApiErrorDomain code:kOAIPrivateApiMissingParamErrorCode userInfo:userInfo];
            handler(nil, error);
        }
        return nil;
    }

    NSMutableString* resourcePath = [NSMutableString stringWithFormat:@"/private/get_current_deposit_address"];

    NSMutableDictionary *pathParams = [[NSMutableDictionary alloc] init];

    NSMutableDictionary* queryParams = [[NSMutableDictionary alloc] init];
    if (currency != nil) {
        queryParams[@"currency"] = currency;
    }
    NSMutableDictionary* headerParams = [NSMutableDictionary dictionaryWithDictionary:self.apiClient.configuration.defaultHeaders];
    [headerParams addEntriesFromDictionary:self.defaultHeaders];
    // HTTP header `Accept`
    NSString *acceptHeader = [self.apiClient.sanitizer selectHeaderAccept:@[@"application/json"]];
    if(acceptHeader.length > 0) {
        headerParams[@"Accept"] = acceptHeader;
    }

    // response content type
    NSString *responseContentType = [[acceptHeader componentsSeparatedByString:@", "] firstObject] ?: @"";

    // request content type
    NSString *requestContentType = [self.apiClient.sanitizer selectHeaderContentType:@[]];

    // Authentication setting
    NSArray *authSettings = @[@"bearerAuth"];

    id bodyParam = nil;
    NSMutableDictionary *formParams = [[NSMutableDictionary alloc] init];
    NSMutableDictionary *localVarFiles = [[NSMutableDictionary alloc] init];

    return [self.apiClient requestWithPath: resourcePath
                                    method: @"GET"
                                pathParams: pathParams
                               queryParams: queryParams
                                formParams: formParams
                                     files: localVarFiles
                                      body: bodyParam
                              headerParams: headerParams
                              authSettings: authSettings
                        requestContentType: requestContentType
                       responseContentType: responseContentType
                              responseType: @"NSObject*"
                           completionBlock: ^(id data, NSError *error) {
                                if(handler) {
                                    handler((NSObject*)data, error);
                                }
                            }];
}

///
/// Retrieve the latest users deposits
/// 
///  @param currency The currency symbol 
///
///  @param count Number of requested items, default - `10` (optional)
///
///  @param offset The offset for pagination, default - `0` (optional)
///
///  @returns NSObject*
///
-(NSURLSessionTask*) privateGetDepositsGetWithCurrency: (NSString*) currency
    count: (NSNumber*) count
    offset: (NSNumber*) offset
    completionHandler: (void (^)(NSObject* output, NSError* error)) handler {
    // verify the required parameter 'currency' is set
    if (currency == nil) {
        NSParameterAssert(currency);
        if(handler) {
            NSDictionary * userInfo = @{NSLocalizedDescriptionKey : [NSString stringWithFormat:NSLocalizedString(@"Missing required parameter '%@'", nil),@"currency"] };
            NSError* error = [NSError errorWithDomain:kOAIPrivateApiErrorDomain code:kOAIPrivateApiMissingParamErrorCode userInfo:userInfo];
            handler(nil, error);
        }
        return nil;
    }

    NSMutableString* resourcePath = [NSMutableString stringWithFormat:@"/private/get_deposits"];

    NSMutableDictionary *pathParams = [[NSMutableDictionary alloc] init];

    NSMutableDictionary* queryParams = [[NSMutableDictionary alloc] init];
    if (currency != nil) {
        queryParams[@"currency"] = currency;
    }
    if (count != nil) {
        queryParams[@"count"] = count;
    }
    if (offset != nil) {
        queryParams[@"offset"] = offset;
    }
    NSMutableDictionary* headerParams = [NSMutableDictionary dictionaryWithDictionary:self.apiClient.configuration.defaultHeaders];
    [headerParams addEntriesFromDictionary:self.defaultHeaders];
    // HTTP header `Accept`
    NSString *acceptHeader = [self.apiClient.sanitizer selectHeaderAccept:@[@"application/json"]];
    if(acceptHeader.length > 0) {
        headerParams[@"Accept"] = acceptHeader;
    }

    // response content type
    NSString *responseContentType = [[acceptHeader componentsSeparatedByString:@", "] firstObject] ?: @"";

    // request content type
    NSString *requestContentType = [self.apiClient.sanitizer selectHeaderContentType:@[]];

    // Authentication setting
    NSArray *authSettings = @[@"bearerAuth"];

    id bodyParam = nil;
    NSMutableDictionary *formParams = [[NSMutableDictionary alloc] init];
    NSMutableDictionary *localVarFiles = [[NSMutableDictionary alloc] init];

    return [self.apiClient requestWithPath: resourcePath
                                    method: @"GET"
                                pathParams: pathParams
                               queryParams: queryParams
                                formParams: formParams
                                     files: localVarFiles
                                      body: bodyParam
                              headerParams: headerParams
                              authSettings: authSettings
                        requestContentType: requestContentType
                       responseContentType: responseContentType
                              responseType: @"NSObject*"
                           completionBlock: ^(id data, NSError *error) {
                                if(handler) {
                                    handler((NSObject*)data, error);
                                }
                            }];
}

///
/// Retrieves the language to be used for emails.
/// 
///  @returns NSObject*
///
-(NSURLSessionTask*) privateGetEmailLanguageGetWithCompletionHandler: 
    (void (^)(NSObject* output, NSError* error)) handler {
    NSMutableString* resourcePath = [NSMutableString stringWithFormat:@"/private/get_email_language"];

    NSMutableDictionary *pathParams = [[NSMutableDictionary alloc] init];

    NSMutableDictionary* queryParams = [[NSMutableDictionary alloc] init];
    NSMutableDictionary* headerParams = [NSMutableDictionary dictionaryWithDictionary:self.apiClient.configuration.defaultHeaders];
    [headerParams addEntriesFromDictionary:self.defaultHeaders];
    // HTTP header `Accept`
    NSString *acceptHeader = [self.apiClient.sanitizer selectHeaderAccept:@[@"application/json"]];
    if(acceptHeader.length > 0) {
        headerParams[@"Accept"] = acceptHeader;
    }

    // response content type
    NSString *responseContentType = [[acceptHeader componentsSeparatedByString:@", "] firstObject] ?: @"";

    // request content type
    NSString *requestContentType = [self.apiClient.sanitizer selectHeaderContentType:@[]];

    // Authentication setting
    NSArray *authSettings = @[@"bearerAuth"];

    id bodyParam = nil;
    NSMutableDictionary *formParams = [[NSMutableDictionary alloc] init];
    NSMutableDictionary *localVarFiles = [[NSMutableDictionary alloc] init];

    return [self.apiClient requestWithPath: resourcePath
                                    method: @"GET"
                                pathParams: pathParams
                               queryParams: queryParams
                                formParams: formParams
                                     files: localVarFiles
                                      body: bodyParam
                              headerParams: headerParams
                              authSettings: authSettings
                        requestContentType: requestContentType
                       responseContentType: responseContentType
                              responseType: @"NSObject*"
                           completionBlock: ^(id data, NSError *error) {
                                if(handler) {
                                    handler((NSObject*)data, error);
                                }
                            }];
}

///
/// Get margins for given instrument, amount and price.
/// 
///  @param instrumentName Instrument name 
///
///  @param amount Amount, integer for future, float for option. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH. 
///
///  @param price Price 
///
///  @returns NSObject*
///
-(NSURLSessionTask*) privateGetMarginsGetWithInstrumentName: (NSString*) instrumentName
    amount: (NSNumber*) amount
    price: (NSNumber*) price
    completionHandler: (void (^)(NSObject* output, NSError* error)) handler {
    // verify the required parameter 'instrumentName' is set
    if (instrumentName == nil) {
        NSParameterAssert(instrumentName);
        if(handler) {
            NSDictionary * userInfo = @{NSLocalizedDescriptionKey : [NSString stringWithFormat:NSLocalizedString(@"Missing required parameter '%@'", nil),@"instrumentName"] };
            NSError* error = [NSError errorWithDomain:kOAIPrivateApiErrorDomain code:kOAIPrivateApiMissingParamErrorCode userInfo:userInfo];
            handler(nil, error);
        }
        return nil;
    }

    // verify the required parameter 'amount' is set
    if (amount == nil) {
        NSParameterAssert(amount);
        if(handler) {
            NSDictionary * userInfo = @{NSLocalizedDescriptionKey : [NSString stringWithFormat:NSLocalizedString(@"Missing required parameter '%@'", nil),@"amount"] };
            NSError* error = [NSError errorWithDomain:kOAIPrivateApiErrorDomain code:kOAIPrivateApiMissingParamErrorCode userInfo:userInfo];
            handler(nil, error);
        }
        return nil;
    }

    // verify the required parameter 'price' is set
    if (price == nil) {
        NSParameterAssert(price);
        if(handler) {
            NSDictionary * userInfo = @{NSLocalizedDescriptionKey : [NSString stringWithFormat:NSLocalizedString(@"Missing required parameter '%@'", nil),@"price"] };
            NSError* error = [NSError errorWithDomain:kOAIPrivateApiErrorDomain code:kOAIPrivateApiMissingParamErrorCode userInfo:userInfo];
            handler(nil, error);
        }
        return nil;
    }

    NSMutableString* resourcePath = [NSMutableString stringWithFormat:@"/private/get_margins"];

    NSMutableDictionary *pathParams = [[NSMutableDictionary alloc] init];

    NSMutableDictionary* queryParams = [[NSMutableDictionary alloc] init];
    if (instrumentName != nil) {
        queryParams[@"instrument_name"] = instrumentName;
    }
    if (amount != nil) {
        queryParams[@"amount"] = amount;
    }
    if (price != nil) {
        queryParams[@"price"] = price;
    }
    NSMutableDictionary* headerParams = [NSMutableDictionary dictionaryWithDictionary:self.apiClient.configuration.defaultHeaders];
    [headerParams addEntriesFromDictionary:self.defaultHeaders];
    // HTTP header `Accept`
    NSString *acceptHeader = [self.apiClient.sanitizer selectHeaderAccept:@[@"application/json"]];
    if(acceptHeader.length > 0) {
        headerParams[@"Accept"] = acceptHeader;
    }

    // response content type
    NSString *responseContentType = [[acceptHeader componentsSeparatedByString:@", "] firstObject] ?: @"";

    // request content type
    NSString *requestContentType = [self.apiClient.sanitizer selectHeaderContentType:@[]];

    // Authentication setting
    NSArray *authSettings = @[@"bearerAuth"];

    id bodyParam = nil;
    NSMutableDictionary *formParams = [[NSMutableDictionary alloc] init];
    NSMutableDictionary *localVarFiles = [[NSMutableDictionary alloc] init];

    return [self.apiClient requestWithPath: resourcePath
                                    method: @"GET"
                                pathParams: pathParams
                               queryParams: queryParams
                                formParams: formParams
                                     files: localVarFiles
                                      body: bodyParam
                              headerParams: headerParams
                              authSettings: authSettings
                        requestContentType: requestContentType
                       responseContentType: responseContentType
                              responseType: @"NSObject*"
                           completionBlock: ^(id data, NSError *error) {
                                if(handler) {
                                    handler((NSObject*)data, error);
                                }
                            }];
}

///
/// Retrieves announcements that have not been marked read by the user.
/// 
///  @returns NSObject*
///
-(NSURLSessionTask*) privateGetNewAnnouncementsGetWithCompletionHandler: 
    (void (^)(NSObject* output, NSError* error)) handler {
    NSMutableString* resourcePath = [NSMutableString stringWithFormat:@"/private/get_new_announcements"];

    NSMutableDictionary *pathParams = [[NSMutableDictionary alloc] init];

    NSMutableDictionary* queryParams = [[NSMutableDictionary alloc] init];
    NSMutableDictionary* headerParams = [NSMutableDictionary dictionaryWithDictionary:self.apiClient.configuration.defaultHeaders];
    [headerParams addEntriesFromDictionary:self.defaultHeaders];
    // HTTP header `Accept`
    NSString *acceptHeader = [self.apiClient.sanitizer selectHeaderAccept:@[@"application/json"]];
    if(acceptHeader.length > 0) {
        headerParams[@"Accept"] = acceptHeader;
    }

    // response content type
    NSString *responseContentType = [[acceptHeader componentsSeparatedByString:@", "] firstObject] ?: @"";

    // request content type
    NSString *requestContentType = [self.apiClient.sanitizer selectHeaderContentType:@[]];

    // Authentication setting
    NSArray *authSettings = @[@"bearerAuth"];

    id bodyParam = nil;
    NSMutableDictionary *formParams = [[NSMutableDictionary alloc] init];
    NSMutableDictionary *localVarFiles = [[NSMutableDictionary alloc] init];

    return [self.apiClient requestWithPath: resourcePath
                                    method: @"GET"
                                pathParams: pathParams
                               queryParams: queryParams
                                formParams: formParams
                                     files: localVarFiles
                                      body: bodyParam
                              headerParams: headerParams
                              authSettings: authSettings
                        requestContentType: requestContentType
                       responseContentType: responseContentType
                              responseType: @"NSObject*"
                           completionBlock: ^(id data, NSError *error) {
                                if(handler) {
                                    handler((NSObject*)data, error);
                                }
                            }];
}

///
/// Retrieves list of user's open orders.
/// 
///  @param currency The currency symbol 
///
///  @param kind Instrument kind, if not provided instruments of all kinds are considered (optional)
///
///  @param type Order type, default - `all` (optional)
///
///  @returns NSObject*
///
-(NSURLSessionTask*) privateGetOpenOrdersByCurrencyGetWithCurrency: (NSString*) currency
    kind: (NSString*) kind
    type: (NSString*) type
    completionHandler: (void (^)(NSObject* output, NSError* error)) handler {
    // verify the required parameter 'currency' is set
    if (currency == nil) {
        NSParameterAssert(currency);
        if(handler) {
            NSDictionary * userInfo = @{NSLocalizedDescriptionKey : [NSString stringWithFormat:NSLocalizedString(@"Missing required parameter '%@'", nil),@"currency"] };
            NSError* error = [NSError errorWithDomain:kOAIPrivateApiErrorDomain code:kOAIPrivateApiMissingParamErrorCode userInfo:userInfo];
            handler(nil, error);
        }
        return nil;
    }

    NSMutableString* resourcePath = [NSMutableString stringWithFormat:@"/private/get_open_orders_by_currency"];

    NSMutableDictionary *pathParams = [[NSMutableDictionary alloc] init];

    NSMutableDictionary* queryParams = [[NSMutableDictionary alloc] init];
    if (currency != nil) {
        queryParams[@"currency"] = currency;
    }
    if (kind != nil) {
        queryParams[@"kind"] = kind;
    }
    if (type != nil) {
        queryParams[@"type"] = type;
    }
    NSMutableDictionary* headerParams = [NSMutableDictionary dictionaryWithDictionary:self.apiClient.configuration.defaultHeaders];
    [headerParams addEntriesFromDictionary:self.defaultHeaders];
    // HTTP header `Accept`
    NSString *acceptHeader = [self.apiClient.sanitizer selectHeaderAccept:@[@"application/json"]];
    if(acceptHeader.length > 0) {
        headerParams[@"Accept"] = acceptHeader;
    }

    // response content type
    NSString *responseContentType = [[acceptHeader componentsSeparatedByString:@", "] firstObject] ?: @"";

    // request content type
    NSString *requestContentType = [self.apiClient.sanitizer selectHeaderContentType:@[]];

    // Authentication setting
    NSArray *authSettings = @[@"bearerAuth"];

    id bodyParam = nil;
    NSMutableDictionary *formParams = [[NSMutableDictionary alloc] init];
    NSMutableDictionary *localVarFiles = [[NSMutableDictionary alloc] init];

    return [self.apiClient requestWithPath: resourcePath
                                    method: @"GET"
                                pathParams: pathParams
                               queryParams: queryParams
                                formParams: formParams
                                     files: localVarFiles
                                      body: bodyParam
                              headerParams: headerParams
                              authSettings: authSettings
                        requestContentType: requestContentType
                       responseContentType: responseContentType
                              responseType: @"NSObject*"
                           completionBlock: ^(id data, NSError *error) {
                                if(handler) {
                                    handler((NSObject*)data, error);
                                }
                            }];
}

///
/// Retrieves list of user's open orders within given Instrument.
/// 
///  @param instrumentName Instrument name 
///
///  @param type Order type, default - `all` (optional)
///
///  @returns NSObject*
///
-(NSURLSessionTask*) privateGetOpenOrdersByInstrumentGetWithInstrumentName: (NSString*) instrumentName
    type: (NSString*) type
    completionHandler: (void (^)(NSObject* output, NSError* error)) handler {
    // verify the required parameter 'instrumentName' is set
    if (instrumentName == nil) {
        NSParameterAssert(instrumentName);
        if(handler) {
            NSDictionary * userInfo = @{NSLocalizedDescriptionKey : [NSString stringWithFormat:NSLocalizedString(@"Missing required parameter '%@'", nil),@"instrumentName"] };
            NSError* error = [NSError errorWithDomain:kOAIPrivateApiErrorDomain code:kOAIPrivateApiMissingParamErrorCode userInfo:userInfo];
            handler(nil, error);
        }
        return nil;
    }

    NSMutableString* resourcePath = [NSMutableString stringWithFormat:@"/private/get_open_orders_by_instrument"];

    NSMutableDictionary *pathParams = [[NSMutableDictionary alloc] init];

    NSMutableDictionary* queryParams = [[NSMutableDictionary alloc] init];
    if (instrumentName != nil) {
        queryParams[@"instrument_name"] = instrumentName;
    }
    if (type != nil) {
        queryParams[@"type"] = type;
    }
    NSMutableDictionary* headerParams = [NSMutableDictionary dictionaryWithDictionary:self.apiClient.configuration.defaultHeaders];
    [headerParams addEntriesFromDictionary:self.defaultHeaders];
    // HTTP header `Accept`
    NSString *acceptHeader = [self.apiClient.sanitizer selectHeaderAccept:@[@"application/json"]];
    if(acceptHeader.length > 0) {
        headerParams[@"Accept"] = acceptHeader;
    }

    // response content type
    NSString *responseContentType = [[acceptHeader componentsSeparatedByString:@", "] firstObject] ?: @"";

    // request content type
    NSString *requestContentType = [self.apiClient.sanitizer selectHeaderContentType:@[]];

    // Authentication setting
    NSArray *authSettings = @[@"bearerAuth"];

    id bodyParam = nil;
    NSMutableDictionary *formParams = [[NSMutableDictionary alloc] init];
    NSMutableDictionary *localVarFiles = [[NSMutableDictionary alloc] init];

    return [self.apiClient requestWithPath: resourcePath
                                    method: @"GET"
                                pathParams: pathParams
                               queryParams: queryParams
                                formParams: formParams
                                     files: localVarFiles
                                      body: bodyParam
                              headerParams: headerParams
                              authSettings: authSettings
                        requestContentType: requestContentType
                       responseContentType: responseContentType
                              responseType: @"NSObject*"
                           completionBlock: ^(id data, NSError *error) {
                                if(handler) {
                                    handler((NSObject*)data, error);
                                }
                            }];
}

///
/// Retrieves history of orders that have been partially or fully filled.
/// 
///  @param currency The currency symbol 
///
///  @param kind Instrument kind, if not provided instruments of all kinds are considered (optional)
///
///  @param count Number of requested items, default - `20` (optional)
///
///  @param offset The offset for pagination, default - `0` (optional)
///
///  @param includeOld Include in result orders older than 2 days, default - `false` (optional)
///
///  @param includeUnfilled Include in result fully unfilled closed orders, default - `false` (optional)
///
///  @returns NSObject*
///
-(NSURLSessionTask*) privateGetOrderHistoryByCurrencyGetWithCurrency: (NSString*) currency
    kind: (NSString*) kind
    count: (NSNumber*) count
    offset: (NSNumber*) offset
    includeOld: (NSNumber*) includeOld
    includeUnfilled: (NSNumber*) includeUnfilled
    completionHandler: (void (^)(NSObject* output, NSError* error)) handler {
    // verify the required parameter 'currency' is set
    if (currency == nil) {
        NSParameterAssert(currency);
        if(handler) {
            NSDictionary * userInfo = @{NSLocalizedDescriptionKey : [NSString stringWithFormat:NSLocalizedString(@"Missing required parameter '%@'", nil),@"currency"] };
            NSError* error = [NSError errorWithDomain:kOAIPrivateApiErrorDomain code:kOAIPrivateApiMissingParamErrorCode userInfo:userInfo];
            handler(nil, error);
        }
        return nil;
    }

    NSMutableString* resourcePath = [NSMutableString stringWithFormat:@"/private/get_order_history_by_currency"];

    NSMutableDictionary *pathParams = [[NSMutableDictionary alloc] init];

    NSMutableDictionary* queryParams = [[NSMutableDictionary alloc] init];
    if (currency != nil) {
        queryParams[@"currency"] = currency;
    }
    if (kind != nil) {
        queryParams[@"kind"] = kind;
    }
    if (count != nil) {
        queryParams[@"count"] = count;
    }
    if (offset != nil) {
        queryParams[@"offset"] = offset;
    }
    if (includeOld != nil) {
        queryParams[@"include_old"] = [includeOld isEqual:@(YES)] ? @"true" : @"false";
    }
    if (includeUnfilled != nil) {
        queryParams[@"include_unfilled"] = [includeUnfilled isEqual:@(YES)] ? @"true" : @"false";
    }
    NSMutableDictionary* headerParams = [NSMutableDictionary dictionaryWithDictionary:self.apiClient.configuration.defaultHeaders];
    [headerParams addEntriesFromDictionary:self.defaultHeaders];
    // HTTP header `Accept`
    NSString *acceptHeader = [self.apiClient.sanitizer selectHeaderAccept:@[@"application/json"]];
    if(acceptHeader.length > 0) {
        headerParams[@"Accept"] = acceptHeader;
    }

    // response content type
    NSString *responseContentType = [[acceptHeader componentsSeparatedByString:@", "] firstObject] ?: @"";

    // request content type
    NSString *requestContentType = [self.apiClient.sanitizer selectHeaderContentType:@[]];

    // Authentication setting
    NSArray *authSettings = @[@"bearerAuth"];

    id bodyParam = nil;
    NSMutableDictionary *formParams = [[NSMutableDictionary alloc] init];
    NSMutableDictionary *localVarFiles = [[NSMutableDictionary alloc] init];

    return [self.apiClient requestWithPath: resourcePath
                                    method: @"GET"
                                pathParams: pathParams
                               queryParams: queryParams
                                formParams: formParams
                                     files: localVarFiles
                                      body: bodyParam
                              headerParams: headerParams
                              authSettings: authSettings
                        requestContentType: requestContentType
                       responseContentType: responseContentType
                              responseType: @"NSObject*"
                           completionBlock: ^(id data, NSError *error) {
                                if(handler) {
                                    handler((NSObject*)data, error);
                                }
                            }];
}

///
/// Retrieves history of orders that have been partially or fully filled.
/// 
///  @param instrumentName Instrument name 
///
///  @param count Number of requested items, default - `20` (optional)
///
///  @param offset The offset for pagination, default - `0` (optional)
///
///  @param includeOld Include in result orders older than 2 days, default - `false` (optional)
///
///  @param includeUnfilled Include in result fully unfilled closed orders, default - `false` (optional)
///
///  @returns NSObject*
///
-(NSURLSessionTask*) privateGetOrderHistoryByInstrumentGetWithInstrumentName: (NSString*) instrumentName
    count: (NSNumber*) count
    offset: (NSNumber*) offset
    includeOld: (NSNumber*) includeOld
    includeUnfilled: (NSNumber*) includeUnfilled
    completionHandler: (void (^)(NSObject* output, NSError* error)) handler {
    // verify the required parameter 'instrumentName' is set
    if (instrumentName == nil) {
        NSParameterAssert(instrumentName);
        if(handler) {
            NSDictionary * userInfo = @{NSLocalizedDescriptionKey : [NSString stringWithFormat:NSLocalizedString(@"Missing required parameter '%@'", nil),@"instrumentName"] };
            NSError* error = [NSError errorWithDomain:kOAIPrivateApiErrorDomain code:kOAIPrivateApiMissingParamErrorCode userInfo:userInfo];
            handler(nil, error);
        }
        return nil;
    }

    NSMutableString* resourcePath = [NSMutableString stringWithFormat:@"/private/get_order_history_by_instrument"];

    NSMutableDictionary *pathParams = [[NSMutableDictionary alloc] init];

    NSMutableDictionary* queryParams = [[NSMutableDictionary alloc] init];
    if (instrumentName != nil) {
        queryParams[@"instrument_name"] = instrumentName;
    }
    if (count != nil) {
        queryParams[@"count"] = count;
    }
    if (offset != nil) {
        queryParams[@"offset"] = offset;
    }
    if (includeOld != nil) {
        queryParams[@"include_old"] = [includeOld isEqual:@(YES)] ? @"true" : @"false";
    }
    if (includeUnfilled != nil) {
        queryParams[@"include_unfilled"] = [includeUnfilled isEqual:@(YES)] ? @"true" : @"false";
    }
    NSMutableDictionary* headerParams = [NSMutableDictionary dictionaryWithDictionary:self.apiClient.configuration.defaultHeaders];
    [headerParams addEntriesFromDictionary:self.defaultHeaders];
    // HTTP header `Accept`
    NSString *acceptHeader = [self.apiClient.sanitizer selectHeaderAccept:@[@"application/json"]];
    if(acceptHeader.length > 0) {
        headerParams[@"Accept"] = acceptHeader;
    }

    // response content type
    NSString *responseContentType = [[acceptHeader componentsSeparatedByString:@", "] firstObject] ?: @"";

    // request content type
    NSString *requestContentType = [self.apiClient.sanitizer selectHeaderContentType:@[]];

    // Authentication setting
    NSArray *authSettings = @[@"bearerAuth"];

    id bodyParam = nil;
    NSMutableDictionary *formParams = [[NSMutableDictionary alloc] init];
    NSMutableDictionary *localVarFiles = [[NSMutableDictionary alloc] init];

    return [self.apiClient requestWithPath: resourcePath
                                    method: @"GET"
                                pathParams: pathParams
                               queryParams: queryParams
                                formParams: formParams
                                     files: localVarFiles
                                      body: bodyParam
                              headerParams: headerParams
                              authSettings: authSettings
                        requestContentType: requestContentType
                       responseContentType: responseContentType
                              responseType: @"NSObject*"
                           completionBlock: ^(id data, NSError *error) {
                                if(handler) {
                                    handler((NSObject*)data, error);
                                }
                            }];
}

///
/// Retrieves initial margins of given orders
/// 
///  @param ids Ids of orders 
///
///  @returns NSObject*
///
-(NSURLSessionTask*) privateGetOrderMarginByIdsGetWithIds: (NSArray<NSString*>*) ids
    completionHandler: (void (^)(NSObject* output, NSError* error)) handler {
    // verify the required parameter 'ids' is set
    if (ids == nil) {
        NSParameterAssert(ids);
        if(handler) {
            NSDictionary * userInfo = @{NSLocalizedDescriptionKey : [NSString stringWithFormat:NSLocalizedString(@"Missing required parameter '%@'", nil),@"ids"] };
            NSError* error = [NSError errorWithDomain:kOAIPrivateApiErrorDomain code:kOAIPrivateApiMissingParamErrorCode userInfo:userInfo];
            handler(nil, error);
        }
        return nil;
    }

    NSMutableString* resourcePath = [NSMutableString stringWithFormat:@"/private/get_order_margin_by_ids"];

    NSMutableDictionary *pathParams = [[NSMutableDictionary alloc] init];

    NSMutableDictionary* queryParams = [[NSMutableDictionary alloc] init];
    if (ids != nil) {
        queryParams[@"ids"] = [[OAIQueryParamCollection alloc] initWithValuesAndFormat: ids format: @"multi"];
    }
    NSMutableDictionary* headerParams = [NSMutableDictionary dictionaryWithDictionary:self.apiClient.configuration.defaultHeaders];
    [headerParams addEntriesFromDictionary:self.defaultHeaders];
    // HTTP header `Accept`
    NSString *acceptHeader = [self.apiClient.sanitizer selectHeaderAccept:@[@"application/json"]];
    if(acceptHeader.length > 0) {
        headerParams[@"Accept"] = acceptHeader;
    }

    // response content type
    NSString *responseContentType = [[acceptHeader componentsSeparatedByString:@", "] firstObject] ?: @"";

    // request content type
    NSString *requestContentType = [self.apiClient.sanitizer selectHeaderContentType:@[]];

    // Authentication setting
    NSArray *authSettings = @[@"bearerAuth"];

    id bodyParam = nil;
    NSMutableDictionary *formParams = [[NSMutableDictionary alloc] init];
    NSMutableDictionary *localVarFiles = [[NSMutableDictionary alloc] init];

    return [self.apiClient requestWithPath: resourcePath
                                    method: @"GET"
                                pathParams: pathParams
                               queryParams: queryParams
                                formParams: formParams
                                     files: localVarFiles
                                      body: bodyParam
                              headerParams: headerParams
                              authSettings: authSettings
                        requestContentType: requestContentType
                       responseContentType: responseContentType
                              responseType: @"NSObject*"
                           completionBlock: ^(id data, NSError *error) {
                                if(handler) {
                                    handler((NSObject*)data, error);
                                }
                            }];
}

///
/// Retrieve the current state of an order.
/// 
///  @param orderId The order id 
///
///  @returns NSObject*
///
-(NSURLSessionTask*) privateGetOrderStateGetWithOrderId: (NSString*) orderId
    completionHandler: (void (^)(NSObject* output, NSError* error)) handler {
    // verify the required parameter 'orderId' is set
    if (orderId == nil) {
        NSParameterAssert(orderId);
        if(handler) {
            NSDictionary * userInfo = @{NSLocalizedDescriptionKey : [NSString stringWithFormat:NSLocalizedString(@"Missing required parameter '%@'", nil),@"orderId"] };
            NSError* error = [NSError errorWithDomain:kOAIPrivateApiErrorDomain code:kOAIPrivateApiMissingParamErrorCode userInfo:userInfo];
            handler(nil, error);
        }
        return nil;
    }

    NSMutableString* resourcePath = [NSMutableString stringWithFormat:@"/private/get_order_state"];

    NSMutableDictionary *pathParams = [[NSMutableDictionary alloc] init];

    NSMutableDictionary* queryParams = [[NSMutableDictionary alloc] init];
    if (orderId != nil) {
        queryParams[@"order_id"] = orderId;
    }
    NSMutableDictionary* headerParams = [NSMutableDictionary dictionaryWithDictionary:self.apiClient.configuration.defaultHeaders];
    [headerParams addEntriesFromDictionary:self.defaultHeaders];
    // HTTP header `Accept`
    NSString *acceptHeader = [self.apiClient.sanitizer selectHeaderAccept:@[@"application/json"]];
    if(acceptHeader.length > 0) {
        headerParams[@"Accept"] = acceptHeader;
    }

    // response content type
    NSString *responseContentType = [[acceptHeader componentsSeparatedByString:@", "] firstObject] ?: @"";

    // request content type
    NSString *requestContentType = [self.apiClient.sanitizer selectHeaderContentType:@[]];

    // Authentication setting
    NSArray *authSettings = @[@"bearerAuth"];

    id bodyParam = nil;
    NSMutableDictionary *formParams = [[NSMutableDictionary alloc] init];
    NSMutableDictionary *localVarFiles = [[NSMutableDictionary alloc] init];

    return [self.apiClient requestWithPath: resourcePath
                                    method: @"GET"
                                pathParams: pathParams
                               queryParams: queryParams
                                formParams: formParams
                                     files: localVarFiles
                                      body: bodyParam
                              headerParams: headerParams
                              authSettings: authSettings
                        requestContentType: requestContentType
                       responseContentType: responseContentType
                              responseType: @"NSObject*"
                           completionBlock: ^(id data, NSError *error) {
                                if(handler) {
                                    handler((NSObject*)data, error);
                                }
                            }];
}

///
/// Retrieve user position.
/// 
///  @param instrumentName Instrument name 
///
///  @returns NSObject*
///
-(NSURLSessionTask*) privateGetPositionGetWithInstrumentName: (NSString*) instrumentName
    completionHandler: (void (^)(NSObject* output, NSError* error)) handler {
    // verify the required parameter 'instrumentName' is set
    if (instrumentName == nil) {
        NSParameterAssert(instrumentName);
        if(handler) {
            NSDictionary * userInfo = @{NSLocalizedDescriptionKey : [NSString stringWithFormat:NSLocalizedString(@"Missing required parameter '%@'", nil),@"instrumentName"] };
            NSError* error = [NSError errorWithDomain:kOAIPrivateApiErrorDomain code:kOAIPrivateApiMissingParamErrorCode userInfo:userInfo];
            handler(nil, error);
        }
        return nil;
    }

    NSMutableString* resourcePath = [NSMutableString stringWithFormat:@"/private/get_position"];

    NSMutableDictionary *pathParams = [[NSMutableDictionary alloc] init];

    NSMutableDictionary* queryParams = [[NSMutableDictionary alloc] init];
    if (instrumentName != nil) {
        queryParams[@"instrument_name"] = instrumentName;
    }
    NSMutableDictionary* headerParams = [NSMutableDictionary dictionaryWithDictionary:self.apiClient.configuration.defaultHeaders];
    [headerParams addEntriesFromDictionary:self.defaultHeaders];
    // HTTP header `Accept`
    NSString *acceptHeader = [self.apiClient.sanitizer selectHeaderAccept:@[@"application/json"]];
    if(acceptHeader.length > 0) {
        headerParams[@"Accept"] = acceptHeader;
    }

    // response content type
    NSString *responseContentType = [[acceptHeader componentsSeparatedByString:@", "] firstObject] ?: @"";

    // request content type
    NSString *requestContentType = [self.apiClient.sanitizer selectHeaderContentType:@[]];

    // Authentication setting
    NSArray *authSettings = @[@"bearerAuth"];

    id bodyParam = nil;
    NSMutableDictionary *formParams = [[NSMutableDictionary alloc] init];
    NSMutableDictionary *localVarFiles = [[NSMutableDictionary alloc] init];

    return [self.apiClient requestWithPath: resourcePath
                                    method: @"GET"
                                pathParams: pathParams
                               queryParams: queryParams
                                formParams: formParams
                                     files: localVarFiles
                                      body: bodyParam
                              headerParams: headerParams
                              authSettings: authSettings
                        requestContentType: requestContentType
                       responseContentType: responseContentType
                              responseType: @"NSObject*"
                           completionBlock: ^(id data, NSError *error) {
                                if(handler) {
                                    handler((NSObject*)data, error);
                                }
                            }];
}

///
/// Retrieve user positions.
/// 
///  @param currency  
///
///  @param kind Kind filter on positions (optional)
///
///  @returns NSObject*
///
-(NSURLSessionTask*) privateGetPositionsGetWithCurrency: (NSString*) currency
    kind: (NSString*) kind
    completionHandler: (void (^)(NSObject* output, NSError* error)) handler {
    // verify the required parameter 'currency' is set
    if (currency == nil) {
        NSParameterAssert(currency);
        if(handler) {
            NSDictionary * userInfo = @{NSLocalizedDescriptionKey : [NSString stringWithFormat:NSLocalizedString(@"Missing required parameter '%@'", nil),@"currency"] };
            NSError* error = [NSError errorWithDomain:kOAIPrivateApiErrorDomain code:kOAIPrivateApiMissingParamErrorCode userInfo:userInfo];
            handler(nil, error);
        }
        return nil;
    }

    NSMutableString* resourcePath = [NSMutableString stringWithFormat:@"/private/get_positions"];

    NSMutableDictionary *pathParams = [[NSMutableDictionary alloc] init];

    NSMutableDictionary* queryParams = [[NSMutableDictionary alloc] init];
    if (currency != nil) {
        queryParams[@"currency"] = currency;
    }
    if (kind != nil) {
        queryParams[@"kind"] = kind;
    }
    NSMutableDictionary* headerParams = [NSMutableDictionary dictionaryWithDictionary:self.apiClient.configuration.defaultHeaders];
    [headerParams addEntriesFromDictionary:self.defaultHeaders];
    // HTTP header `Accept`
    NSString *acceptHeader = [self.apiClient.sanitizer selectHeaderAccept:@[@"application/json"]];
    if(acceptHeader.length > 0) {
        headerParams[@"Accept"] = acceptHeader;
    }

    // response content type
    NSString *responseContentType = [[acceptHeader componentsSeparatedByString:@", "] firstObject] ?: @"";

    // request content type
    NSString *requestContentType = [self.apiClient.sanitizer selectHeaderContentType:@[]];

    // Authentication setting
    NSArray *authSettings = @[@"bearerAuth"];

    id bodyParam = nil;
    NSMutableDictionary *formParams = [[NSMutableDictionary alloc] init];
    NSMutableDictionary *localVarFiles = [[NSMutableDictionary alloc] init];

    return [self.apiClient requestWithPath: resourcePath
                                    method: @"GET"
                                pathParams: pathParams
                               queryParams: queryParams
                                formParams: formParams
                                     files: localVarFiles
                                      body: bodyParam
                              headerParams: headerParams
                              authSettings: authSettings
                        requestContentType: requestContentType
                       responseContentType: responseContentType
                              responseType: @"NSObject*"
                           completionBlock: ^(id data, NSError *error) {
                                if(handler) {
                                    handler((NSObject*)data, error);
                                }
                            }];
}

///
/// Retrieves settlement, delivery and bankruptcy events that have affected your account.
/// 
///  @param currency The currency symbol 
///
///  @param type Settlement type (optional)
///
///  @param count Number of requested items, default - `20` (optional)
///
///  @returns NSObject*
///
-(NSURLSessionTask*) privateGetSettlementHistoryByCurrencyGetWithCurrency: (NSString*) currency
    type: (NSString*) type
    count: (NSNumber*) count
    completionHandler: (void (^)(NSObject* output, NSError* error)) handler {
    // verify the required parameter 'currency' is set
    if (currency == nil) {
        NSParameterAssert(currency);
        if(handler) {
            NSDictionary * userInfo = @{NSLocalizedDescriptionKey : [NSString stringWithFormat:NSLocalizedString(@"Missing required parameter '%@'", nil),@"currency"] };
            NSError* error = [NSError errorWithDomain:kOAIPrivateApiErrorDomain code:kOAIPrivateApiMissingParamErrorCode userInfo:userInfo];
            handler(nil, error);
        }
        return nil;
    }

    NSMutableString* resourcePath = [NSMutableString stringWithFormat:@"/private/get_settlement_history_by_currency"];

    NSMutableDictionary *pathParams = [[NSMutableDictionary alloc] init];

    NSMutableDictionary* queryParams = [[NSMutableDictionary alloc] init];
    if (currency != nil) {
        queryParams[@"currency"] = currency;
    }
    if (type != nil) {
        queryParams[@"type"] = type;
    }
    if (count != nil) {
        queryParams[@"count"] = count;
    }
    NSMutableDictionary* headerParams = [NSMutableDictionary dictionaryWithDictionary:self.apiClient.configuration.defaultHeaders];
    [headerParams addEntriesFromDictionary:self.defaultHeaders];
    // HTTP header `Accept`
    NSString *acceptHeader = [self.apiClient.sanitizer selectHeaderAccept:@[@"application/json"]];
    if(acceptHeader.length > 0) {
        headerParams[@"Accept"] = acceptHeader;
    }

    // response content type
    NSString *responseContentType = [[acceptHeader componentsSeparatedByString:@", "] firstObject] ?: @"";

    // request content type
    NSString *requestContentType = [self.apiClient.sanitizer selectHeaderContentType:@[]];

    // Authentication setting
    NSArray *authSettings = @[@"bearerAuth"];

    id bodyParam = nil;
    NSMutableDictionary *formParams = [[NSMutableDictionary alloc] init];
    NSMutableDictionary *localVarFiles = [[NSMutableDictionary alloc] init];

    return [self.apiClient requestWithPath: resourcePath
                                    method: @"GET"
                                pathParams: pathParams
                               queryParams: queryParams
                                formParams: formParams
                                     files: localVarFiles
                                      body: bodyParam
                              headerParams: headerParams
                              authSettings: authSettings
                        requestContentType: requestContentType
                       responseContentType: responseContentType
                              responseType: @"NSObject*"
                           completionBlock: ^(id data, NSError *error) {
                                if(handler) {
                                    handler((NSObject*)data, error);
                                }
                            }];
}

///
/// Retrieves public settlement, delivery and bankruptcy events filtered by instrument name
/// 
///  @param instrumentName Instrument name 
///
///  @param type Settlement type (optional)
///
///  @param count Number of requested items, default - `20` (optional)
///
///  @returns NSObject*
///
-(NSURLSessionTask*) privateGetSettlementHistoryByInstrumentGetWithInstrumentName: (NSString*) instrumentName
    type: (NSString*) type
    count: (NSNumber*) count
    completionHandler: (void (^)(NSObject* output, NSError* error)) handler {
    // verify the required parameter 'instrumentName' is set
    if (instrumentName == nil) {
        NSParameterAssert(instrumentName);
        if(handler) {
            NSDictionary * userInfo = @{NSLocalizedDescriptionKey : [NSString stringWithFormat:NSLocalizedString(@"Missing required parameter '%@'", nil),@"instrumentName"] };
            NSError* error = [NSError errorWithDomain:kOAIPrivateApiErrorDomain code:kOAIPrivateApiMissingParamErrorCode userInfo:userInfo];
            handler(nil, error);
        }
        return nil;
    }

    NSMutableString* resourcePath = [NSMutableString stringWithFormat:@"/private/get_settlement_history_by_instrument"];

    NSMutableDictionary *pathParams = [[NSMutableDictionary alloc] init];

    NSMutableDictionary* queryParams = [[NSMutableDictionary alloc] init];
    if (instrumentName != nil) {
        queryParams[@"instrument_name"] = instrumentName;
    }
    if (type != nil) {
        queryParams[@"type"] = type;
    }
    if (count != nil) {
        queryParams[@"count"] = count;
    }
    NSMutableDictionary* headerParams = [NSMutableDictionary dictionaryWithDictionary:self.apiClient.configuration.defaultHeaders];
    [headerParams addEntriesFromDictionary:self.defaultHeaders];
    // HTTP header `Accept`
    NSString *acceptHeader = [self.apiClient.sanitizer selectHeaderAccept:@[@"application/json"]];
    if(acceptHeader.length > 0) {
        headerParams[@"Accept"] = acceptHeader;
    }

    // response content type
    NSString *responseContentType = [[acceptHeader componentsSeparatedByString:@", "] firstObject] ?: @"";

    // request content type
    NSString *requestContentType = [self.apiClient.sanitizer selectHeaderContentType:@[]];

    // Authentication setting
    NSArray *authSettings = @[@"bearerAuth"];

    id bodyParam = nil;
    NSMutableDictionary *formParams = [[NSMutableDictionary alloc] init];
    NSMutableDictionary *localVarFiles = [[NSMutableDictionary alloc] init];

    return [self.apiClient requestWithPath: resourcePath
                                    method: @"GET"
                                pathParams: pathParams
                               queryParams: queryParams
                                formParams: formParams
                                     files: localVarFiles
                                      body: bodyParam
                              headerParams: headerParams
                              authSettings: authSettings
                        requestContentType: requestContentType
                       responseContentType: responseContentType
                              responseType: @"NSObject*"
                           completionBlock: ^(id data, NSError *error) {
                                if(handler) {
                                    handler((NSObject*)data, error);
                                }
                            }];
}

///
/// Get information about subaccounts
/// 
///  @param withPortfolio  (optional)
///
///  @returns NSObject*
///
-(NSURLSessionTask*) privateGetSubaccountsGetWithWithPortfolio: (NSNumber*) withPortfolio
    completionHandler: (void (^)(NSObject* output, NSError* error)) handler {
    NSMutableString* resourcePath = [NSMutableString stringWithFormat:@"/private/get_subaccounts"];

    NSMutableDictionary *pathParams = [[NSMutableDictionary alloc] init];

    NSMutableDictionary* queryParams = [[NSMutableDictionary alloc] init];
    if (withPortfolio != nil) {
        queryParams[@"with_portfolio"] = [withPortfolio isEqual:@(YES)] ? @"true" : @"false";
    }
    NSMutableDictionary* headerParams = [NSMutableDictionary dictionaryWithDictionary:self.apiClient.configuration.defaultHeaders];
    [headerParams addEntriesFromDictionary:self.defaultHeaders];
    // HTTP header `Accept`
    NSString *acceptHeader = [self.apiClient.sanitizer selectHeaderAccept:@[@"application/json"]];
    if(acceptHeader.length > 0) {
        headerParams[@"Accept"] = acceptHeader;
    }

    // response content type
    NSString *responseContentType = [[acceptHeader componentsSeparatedByString:@", "] firstObject] ?: @"";

    // request content type
    NSString *requestContentType = [self.apiClient.sanitizer selectHeaderContentType:@[]];

    // Authentication setting
    NSArray *authSettings = @[@"bearerAuth"];

    id bodyParam = nil;
    NSMutableDictionary *formParams = [[NSMutableDictionary alloc] init];
    NSMutableDictionary *localVarFiles = [[NSMutableDictionary alloc] init];

    return [self.apiClient requestWithPath: resourcePath
                                    method: @"GET"
                                pathParams: pathParams
                               queryParams: queryParams
                                formParams: formParams
                                     files: localVarFiles
                                      body: bodyParam
                              headerParams: headerParams
                              authSettings: authSettings
                        requestContentType: requestContentType
                       responseContentType: responseContentType
                              responseType: @"NSObject*"
                           completionBlock: ^(id data, NSError *error) {
                                if(handler) {
                                    handler((NSObject*)data, error);
                                }
                            }];
}

///
/// Adds new entry to address book of given type
/// 
///  @param currency The currency symbol 
///
///  @param count Number of requested items, default - `10` (optional)
///
///  @param offset The offset for pagination, default - `0` (optional)
///
///  @returns NSObject*
///
-(NSURLSessionTask*) privateGetTransfersGetWithCurrency: (NSString*) currency
    count: (NSNumber*) count
    offset: (NSNumber*) offset
    completionHandler: (void (^)(NSObject* output, NSError* error)) handler {
    // verify the required parameter 'currency' is set
    if (currency == nil) {
        NSParameterAssert(currency);
        if(handler) {
            NSDictionary * userInfo = @{NSLocalizedDescriptionKey : [NSString stringWithFormat:NSLocalizedString(@"Missing required parameter '%@'", nil),@"currency"] };
            NSError* error = [NSError errorWithDomain:kOAIPrivateApiErrorDomain code:kOAIPrivateApiMissingParamErrorCode userInfo:userInfo];
            handler(nil, error);
        }
        return nil;
    }

    NSMutableString* resourcePath = [NSMutableString stringWithFormat:@"/private/get_transfers"];

    NSMutableDictionary *pathParams = [[NSMutableDictionary alloc] init];

    NSMutableDictionary* queryParams = [[NSMutableDictionary alloc] init];
    if (currency != nil) {
        queryParams[@"currency"] = currency;
    }
    if (count != nil) {
        queryParams[@"count"] = count;
    }
    if (offset != nil) {
        queryParams[@"offset"] = offset;
    }
    NSMutableDictionary* headerParams = [NSMutableDictionary dictionaryWithDictionary:self.apiClient.configuration.defaultHeaders];
    [headerParams addEntriesFromDictionary:self.defaultHeaders];
    // HTTP header `Accept`
    NSString *acceptHeader = [self.apiClient.sanitizer selectHeaderAccept:@[@"application/json"]];
    if(acceptHeader.length > 0) {
        headerParams[@"Accept"] = acceptHeader;
    }

    // response content type
    NSString *responseContentType = [[acceptHeader componentsSeparatedByString:@", "] firstObject] ?: @"";

    // request content type
    NSString *requestContentType = [self.apiClient.sanitizer selectHeaderContentType:@[]];

    // Authentication setting
    NSArray *authSettings = @[@"bearerAuth"];

    id bodyParam = nil;
    NSMutableDictionary *formParams = [[NSMutableDictionary alloc] init];
    NSMutableDictionary *localVarFiles = [[NSMutableDictionary alloc] init];

    return [self.apiClient requestWithPath: resourcePath
                                    method: @"GET"
                                pathParams: pathParams
                               queryParams: queryParams
                                formParams: formParams
                                     files: localVarFiles
                                      body: bodyParam
                              headerParams: headerParams
                              authSettings: authSettings
                        requestContentType: requestContentType
                       responseContentType: responseContentType
                              responseType: @"NSObject*"
                           completionBlock: ^(id data, NSError *error) {
                                if(handler) {
                                    handler((NSObject*)data, error);
                                }
                            }];
}

///
/// Retrieve the latest user trades that have occurred for instruments in a specific currency symbol and within given time range.
/// 
///  @param currency The currency symbol 
///
///  @param startTimestamp The earliest timestamp to return result for 
///
///  @param endTimestamp The most recent timestamp to return result for 
///
///  @param kind Instrument kind, if not provided instruments of all kinds are considered (optional)
///
///  @param count Number of requested items, default - `10` (optional)
///
///  @param includeOld Include trades older than 7 days, default - `false` (optional)
///
///  @param sorting Direction of results sorting (`default` value means no sorting, results will be returned in order in which they left the database) (optional)
///
///  @returns NSObject*
///
-(NSURLSessionTask*) privateGetUserTradesByCurrencyAndTimeGetWithCurrency: (NSString*) currency
    startTimestamp: (NSNumber*) startTimestamp
    endTimestamp: (NSNumber*) endTimestamp
    kind: (NSString*) kind
    count: (NSNumber*) count
    includeOld: (NSNumber*) includeOld
    sorting: (NSString*) sorting
    completionHandler: (void (^)(NSObject* output, NSError* error)) handler {
    // verify the required parameter 'currency' is set
    if (currency == nil) {
        NSParameterAssert(currency);
        if(handler) {
            NSDictionary * userInfo = @{NSLocalizedDescriptionKey : [NSString stringWithFormat:NSLocalizedString(@"Missing required parameter '%@'", nil),@"currency"] };
            NSError* error = [NSError errorWithDomain:kOAIPrivateApiErrorDomain code:kOAIPrivateApiMissingParamErrorCode userInfo:userInfo];
            handler(nil, error);
        }
        return nil;
    }

    // verify the required parameter 'startTimestamp' is set
    if (startTimestamp == nil) {
        NSParameterAssert(startTimestamp);
        if(handler) {
            NSDictionary * userInfo = @{NSLocalizedDescriptionKey : [NSString stringWithFormat:NSLocalizedString(@"Missing required parameter '%@'", nil),@"startTimestamp"] };
            NSError* error = [NSError errorWithDomain:kOAIPrivateApiErrorDomain code:kOAIPrivateApiMissingParamErrorCode userInfo:userInfo];
            handler(nil, error);
        }
        return nil;
    }

    // verify the required parameter 'endTimestamp' is set
    if (endTimestamp == nil) {
        NSParameterAssert(endTimestamp);
        if(handler) {
            NSDictionary * userInfo = @{NSLocalizedDescriptionKey : [NSString stringWithFormat:NSLocalizedString(@"Missing required parameter '%@'", nil),@"endTimestamp"] };
            NSError* error = [NSError errorWithDomain:kOAIPrivateApiErrorDomain code:kOAIPrivateApiMissingParamErrorCode userInfo:userInfo];
            handler(nil, error);
        }
        return nil;
    }

    NSMutableString* resourcePath = [NSMutableString stringWithFormat:@"/private/get_user_trades_by_currency_and_time"];

    NSMutableDictionary *pathParams = [[NSMutableDictionary alloc] init];

    NSMutableDictionary* queryParams = [[NSMutableDictionary alloc] init];
    if (currency != nil) {
        queryParams[@"currency"] = currency;
    }
    if (kind != nil) {
        queryParams[@"kind"] = kind;
    }
    if (startTimestamp != nil) {
        queryParams[@"start_timestamp"] = startTimestamp;
    }
    if (endTimestamp != nil) {
        queryParams[@"end_timestamp"] = endTimestamp;
    }
    if (count != nil) {
        queryParams[@"count"] = count;
    }
    if (includeOld != nil) {
        queryParams[@"include_old"] = [includeOld isEqual:@(YES)] ? @"true" : @"false";
    }
    if (sorting != nil) {
        queryParams[@"sorting"] = sorting;
    }
    NSMutableDictionary* headerParams = [NSMutableDictionary dictionaryWithDictionary:self.apiClient.configuration.defaultHeaders];
    [headerParams addEntriesFromDictionary:self.defaultHeaders];
    // HTTP header `Accept`
    NSString *acceptHeader = [self.apiClient.sanitizer selectHeaderAccept:@[@"application/json"]];
    if(acceptHeader.length > 0) {
        headerParams[@"Accept"] = acceptHeader;
    }

    // response content type
    NSString *responseContentType = [[acceptHeader componentsSeparatedByString:@", "] firstObject] ?: @"";

    // request content type
    NSString *requestContentType = [self.apiClient.sanitizer selectHeaderContentType:@[]];

    // Authentication setting
    NSArray *authSettings = @[@"bearerAuth"];

    id bodyParam = nil;
    NSMutableDictionary *formParams = [[NSMutableDictionary alloc] init];
    NSMutableDictionary *localVarFiles = [[NSMutableDictionary alloc] init];

    return [self.apiClient requestWithPath: resourcePath
                                    method: @"GET"
                                pathParams: pathParams
                               queryParams: queryParams
                                formParams: formParams
                                     files: localVarFiles
                                      body: bodyParam
                              headerParams: headerParams
                              authSettings: authSettings
                        requestContentType: requestContentType
                       responseContentType: responseContentType
                              responseType: @"NSObject*"
                           completionBlock: ^(id data, NSError *error) {
                                if(handler) {
                                    handler((NSObject*)data, error);
                                }
                            }];
}

///
/// Retrieve the latest user trades that have occurred for instruments in a specific currency symbol.
/// 
///  @param currency The currency symbol 
///
///  @param kind Instrument kind, if not provided instruments of all kinds are considered (optional)
///
///  @param startId The ID number of the first trade to be returned (optional)
///
///  @param endId The ID number of the last trade to be returned (optional)
///
///  @param count Number of requested items, default - `10` (optional)
///
///  @param includeOld Include trades older than 7 days, default - `false` (optional)
///
///  @param sorting Direction of results sorting (`default` value means no sorting, results will be returned in order in which they left the database) (optional)
///
///  @returns NSObject*
///
-(NSURLSessionTask*) privateGetUserTradesByCurrencyGetWithCurrency: (NSString*) currency
    kind: (NSString*) kind
    startId: (NSString*) startId
    endId: (NSString*) endId
    count: (NSNumber*) count
    includeOld: (NSNumber*) includeOld
    sorting: (NSString*) sorting
    completionHandler: (void (^)(NSObject* output, NSError* error)) handler {
    // verify the required parameter 'currency' is set
    if (currency == nil) {
        NSParameterAssert(currency);
        if(handler) {
            NSDictionary * userInfo = @{NSLocalizedDescriptionKey : [NSString stringWithFormat:NSLocalizedString(@"Missing required parameter '%@'", nil),@"currency"] };
            NSError* error = [NSError errorWithDomain:kOAIPrivateApiErrorDomain code:kOAIPrivateApiMissingParamErrorCode userInfo:userInfo];
            handler(nil, error);
        }
        return nil;
    }

    NSMutableString* resourcePath = [NSMutableString stringWithFormat:@"/private/get_user_trades_by_currency"];

    NSMutableDictionary *pathParams = [[NSMutableDictionary alloc] init];

    NSMutableDictionary* queryParams = [[NSMutableDictionary alloc] init];
    if (currency != nil) {
        queryParams[@"currency"] = currency;
    }
    if (kind != nil) {
        queryParams[@"kind"] = kind;
    }
    if (startId != nil) {
        queryParams[@"start_id"] = startId;
    }
    if (endId != nil) {
        queryParams[@"end_id"] = endId;
    }
    if (count != nil) {
        queryParams[@"count"] = count;
    }
    if (includeOld != nil) {
        queryParams[@"include_old"] = [includeOld isEqual:@(YES)] ? @"true" : @"false";
    }
    if (sorting != nil) {
        queryParams[@"sorting"] = sorting;
    }
    NSMutableDictionary* headerParams = [NSMutableDictionary dictionaryWithDictionary:self.apiClient.configuration.defaultHeaders];
    [headerParams addEntriesFromDictionary:self.defaultHeaders];
    // HTTP header `Accept`
    NSString *acceptHeader = [self.apiClient.sanitizer selectHeaderAccept:@[@"application/json"]];
    if(acceptHeader.length > 0) {
        headerParams[@"Accept"] = acceptHeader;
    }

    // response content type
    NSString *responseContentType = [[acceptHeader componentsSeparatedByString:@", "] firstObject] ?: @"";

    // request content type
    NSString *requestContentType = [self.apiClient.sanitizer selectHeaderContentType:@[]];

    // Authentication setting
    NSArray *authSettings = @[@"bearerAuth"];

    id bodyParam = nil;
    NSMutableDictionary *formParams = [[NSMutableDictionary alloc] init];
    NSMutableDictionary *localVarFiles = [[NSMutableDictionary alloc] init];

    return [self.apiClient requestWithPath: resourcePath
                                    method: @"GET"
                                pathParams: pathParams
                               queryParams: queryParams
                                formParams: formParams
                                     files: localVarFiles
                                      body: bodyParam
                              headerParams: headerParams
                              authSettings: authSettings
                        requestContentType: requestContentType
                       responseContentType: responseContentType
                              responseType: @"NSObject*"
                           completionBlock: ^(id data, NSError *error) {
                                if(handler) {
                                    handler((NSObject*)data, error);
                                }
                            }];
}

///
/// Retrieve the latest user trades that have occurred for a specific instrument and within given time range.
/// 
///  @param instrumentName Instrument name 
///
///  @param startTimestamp The earliest timestamp to return result for 
///
///  @param endTimestamp The most recent timestamp to return result for 
///
///  @param count Number of requested items, default - `10` (optional)
///
///  @param includeOld Include trades older than 7 days, default - `false` (optional)
///
///  @param sorting Direction of results sorting (`default` value means no sorting, results will be returned in order in which they left the database) (optional)
///
///  @returns NSObject*
///
-(NSURLSessionTask*) privateGetUserTradesByInstrumentAndTimeGetWithInstrumentName: (NSString*) instrumentName
    startTimestamp: (NSNumber*) startTimestamp
    endTimestamp: (NSNumber*) endTimestamp
    count: (NSNumber*) count
    includeOld: (NSNumber*) includeOld
    sorting: (NSString*) sorting
    completionHandler: (void (^)(NSObject* output, NSError* error)) handler {
    // verify the required parameter 'instrumentName' is set
    if (instrumentName == nil) {
        NSParameterAssert(instrumentName);
        if(handler) {
            NSDictionary * userInfo = @{NSLocalizedDescriptionKey : [NSString stringWithFormat:NSLocalizedString(@"Missing required parameter '%@'", nil),@"instrumentName"] };
            NSError* error = [NSError errorWithDomain:kOAIPrivateApiErrorDomain code:kOAIPrivateApiMissingParamErrorCode userInfo:userInfo];
            handler(nil, error);
        }
        return nil;
    }

    // verify the required parameter 'startTimestamp' is set
    if (startTimestamp == nil) {
        NSParameterAssert(startTimestamp);
        if(handler) {
            NSDictionary * userInfo = @{NSLocalizedDescriptionKey : [NSString stringWithFormat:NSLocalizedString(@"Missing required parameter '%@'", nil),@"startTimestamp"] };
            NSError* error = [NSError errorWithDomain:kOAIPrivateApiErrorDomain code:kOAIPrivateApiMissingParamErrorCode userInfo:userInfo];
            handler(nil, error);
        }
        return nil;
    }

    // verify the required parameter 'endTimestamp' is set
    if (endTimestamp == nil) {
        NSParameterAssert(endTimestamp);
        if(handler) {
            NSDictionary * userInfo = @{NSLocalizedDescriptionKey : [NSString stringWithFormat:NSLocalizedString(@"Missing required parameter '%@'", nil),@"endTimestamp"] };
            NSError* error = [NSError errorWithDomain:kOAIPrivateApiErrorDomain code:kOAIPrivateApiMissingParamErrorCode userInfo:userInfo];
            handler(nil, error);
        }
        return nil;
    }

    NSMutableString* resourcePath = [NSMutableString stringWithFormat:@"/private/get_user_trades_by_instrument_and_time"];

    NSMutableDictionary *pathParams = [[NSMutableDictionary alloc] init];

    NSMutableDictionary* queryParams = [[NSMutableDictionary alloc] init];
    if (instrumentName != nil) {
        queryParams[@"instrument_name"] = instrumentName;
    }
    if (startTimestamp != nil) {
        queryParams[@"start_timestamp"] = startTimestamp;
    }
    if (endTimestamp != nil) {
        queryParams[@"end_timestamp"] = endTimestamp;
    }
    if (count != nil) {
        queryParams[@"count"] = count;
    }
    if (includeOld != nil) {
        queryParams[@"include_old"] = [includeOld isEqual:@(YES)] ? @"true" : @"false";
    }
    if (sorting != nil) {
        queryParams[@"sorting"] = sorting;
    }
    NSMutableDictionary* headerParams = [NSMutableDictionary dictionaryWithDictionary:self.apiClient.configuration.defaultHeaders];
    [headerParams addEntriesFromDictionary:self.defaultHeaders];
    // HTTP header `Accept`
    NSString *acceptHeader = [self.apiClient.sanitizer selectHeaderAccept:@[@"application/json"]];
    if(acceptHeader.length > 0) {
        headerParams[@"Accept"] = acceptHeader;
    }

    // response content type
    NSString *responseContentType = [[acceptHeader componentsSeparatedByString:@", "] firstObject] ?: @"";

    // request content type
    NSString *requestContentType = [self.apiClient.sanitizer selectHeaderContentType:@[]];

    // Authentication setting
    NSArray *authSettings = @[@"bearerAuth"];

    id bodyParam = nil;
    NSMutableDictionary *formParams = [[NSMutableDictionary alloc] init];
    NSMutableDictionary *localVarFiles = [[NSMutableDictionary alloc] init];

    return [self.apiClient requestWithPath: resourcePath
                                    method: @"GET"
                                pathParams: pathParams
                               queryParams: queryParams
                                formParams: formParams
                                     files: localVarFiles
                                      body: bodyParam
                              headerParams: headerParams
                              authSettings: authSettings
                        requestContentType: requestContentType
                       responseContentType: responseContentType
                              responseType: @"NSObject*"
                           completionBlock: ^(id data, NSError *error) {
                                if(handler) {
                                    handler((NSObject*)data, error);
                                }
                            }];
}

///
/// Retrieve the latest user trades that have occurred for a specific instrument.
/// 
///  @param instrumentName Instrument name 
///
///  @param startSeq The sequence number of the first trade to be returned (optional)
///
///  @param endSeq The sequence number of the last trade to be returned (optional)
///
///  @param count Number of requested items, default - `10` (optional)
///
///  @param includeOld Include trades older than 7 days, default - `false` (optional)
///
///  @param sorting Direction of results sorting (`default` value means no sorting, results will be returned in order in which they left the database) (optional)
///
///  @returns NSObject*
///
-(NSURLSessionTask*) privateGetUserTradesByInstrumentGetWithInstrumentName: (NSString*) instrumentName
    startSeq: (NSNumber*) startSeq
    endSeq: (NSNumber*) endSeq
    count: (NSNumber*) count
    includeOld: (NSNumber*) includeOld
    sorting: (NSString*) sorting
    completionHandler: (void (^)(NSObject* output, NSError* error)) handler {
    // verify the required parameter 'instrumentName' is set
    if (instrumentName == nil) {
        NSParameterAssert(instrumentName);
        if(handler) {
            NSDictionary * userInfo = @{NSLocalizedDescriptionKey : [NSString stringWithFormat:NSLocalizedString(@"Missing required parameter '%@'", nil),@"instrumentName"] };
            NSError* error = [NSError errorWithDomain:kOAIPrivateApiErrorDomain code:kOAIPrivateApiMissingParamErrorCode userInfo:userInfo];
            handler(nil, error);
        }
        return nil;
    }

    NSMutableString* resourcePath = [NSMutableString stringWithFormat:@"/private/get_user_trades_by_instrument"];

    NSMutableDictionary *pathParams = [[NSMutableDictionary alloc] init];

    NSMutableDictionary* queryParams = [[NSMutableDictionary alloc] init];
    if (instrumentName != nil) {
        queryParams[@"instrument_name"] = instrumentName;
    }
    if (startSeq != nil) {
        queryParams[@"start_seq"] = startSeq;
    }
    if (endSeq != nil) {
        queryParams[@"end_seq"] = endSeq;
    }
    if (count != nil) {
        queryParams[@"count"] = count;
    }
    if (includeOld != nil) {
        queryParams[@"include_old"] = [includeOld isEqual:@(YES)] ? @"true" : @"false";
    }
    if (sorting != nil) {
        queryParams[@"sorting"] = sorting;
    }
    NSMutableDictionary* headerParams = [NSMutableDictionary dictionaryWithDictionary:self.apiClient.configuration.defaultHeaders];
    [headerParams addEntriesFromDictionary:self.defaultHeaders];
    // HTTP header `Accept`
    NSString *acceptHeader = [self.apiClient.sanitizer selectHeaderAccept:@[@"application/json"]];
    if(acceptHeader.length > 0) {
        headerParams[@"Accept"] = acceptHeader;
    }

    // response content type
    NSString *responseContentType = [[acceptHeader componentsSeparatedByString:@", "] firstObject] ?: @"";

    // request content type
    NSString *requestContentType = [self.apiClient.sanitizer selectHeaderContentType:@[]];

    // Authentication setting
    NSArray *authSettings = @[@"bearerAuth"];

    id bodyParam = nil;
    NSMutableDictionary *formParams = [[NSMutableDictionary alloc] init];
    NSMutableDictionary *localVarFiles = [[NSMutableDictionary alloc] init];

    return [self.apiClient requestWithPath: resourcePath
                                    method: @"GET"
                                pathParams: pathParams
                               queryParams: queryParams
                                formParams: formParams
                                     files: localVarFiles
                                      body: bodyParam
                              headerParams: headerParams
                              authSettings: authSettings
                        requestContentType: requestContentType
                       responseContentType: responseContentType
                              responseType: @"NSObject*"
                           completionBlock: ^(id data, NSError *error) {
                                if(handler) {
                                    handler((NSObject*)data, error);
                                }
                            }];
}

///
/// Retrieve the list of user trades that was created for given order
/// 
///  @param orderId The order id 
///
///  @param sorting Direction of results sorting (`default` value means no sorting, results will be returned in order in which they left the database) (optional)
///
///  @returns NSObject*
///
-(NSURLSessionTask*) privateGetUserTradesByOrderGetWithOrderId: (NSString*) orderId
    sorting: (NSString*) sorting
    completionHandler: (void (^)(NSObject* output, NSError* error)) handler {
    // verify the required parameter 'orderId' is set
    if (orderId == nil) {
        NSParameterAssert(orderId);
        if(handler) {
            NSDictionary * userInfo = @{NSLocalizedDescriptionKey : [NSString stringWithFormat:NSLocalizedString(@"Missing required parameter '%@'", nil),@"orderId"] };
            NSError* error = [NSError errorWithDomain:kOAIPrivateApiErrorDomain code:kOAIPrivateApiMissingParamErrorCode userInfo:userInfo];
            handler(nil, error);
        }
        return nil;
    }

    NSMutableString* resourcePath = [NSMutableString stringWithFormat:@"/private/get_user_trades_by_order"];

    NSMutableDictionary *pathParams = [[NSMutableDictionary alloc] init];

    NSMutableDictionary* queryParams = [[NSMutableDictionary alloc] init];
    if (orderId != nil) {
        queryParams[@"order_id"] = orderId;
    }
    if (sorting != nil) {
        queryParams[@"sorting"] = sorting;
    }
    NSMutableDictionary* headerParams = [NSMutableDictionary dictionaryWithDictionary:self.apiClient.configuration.defaultHeaders];
    [headerParams addEntriesFromDictionary:self.defaultHeaders];
    // HTTP header `Accept`
    NSString *acceptHeader = [self.apiClient.sanitizer selectHeaderAccept:@[@"application/json"]];
    if(acceptHeader.length > 0) {
        headerParams[@"Accept"] = acceptHeader;
    }

    // response content type
    NSString *responseContentType = [[acceptHeader componentsSeparatedByString:@", "] firstObject] ?: @"";

    // request content type
    NSString *requestContentType = [self.apiClient.sanitizer selectHeaderContentType:@[]];

    // Authentication setting
    NSArray *authSettings = @[@"bearerAuth"];

    id bodyParam = nil;
    NSMutableDictionary *formParams = [[NSMutableDictionary alloc] init];
    NSMutableDictionary *localVarFiles = [[NSMutableDictionary alloc] init];

    return [self.apiClient requestWithPath: resourcePath
                                    method: @"GET"
                                pathParams: pathParams
                               queryParams: queryParams
                                formParams: formParams
                                     files: localVarFiles
                                      body: bodyParam
                              headerParams: headerParams
                              authSettings: authSettings
                        requestContentType: requestContentType
                       responseContentType: responseContentType
                              responseType: @"NSObject*"
                           completionBlock: ^(id data, NSError *error) {
                                if(handler) {
                                    handler((NSObject*)data, error);
                                }
                            }];
}

///
/// Retrieve the latest users withdrawals
/// 
///  @param currency The currency symbol 
///
///  @param count Number of requested items, default - `10` (optional)
///
///  @param offset The offset for pagination, default - `0` (optional)
///
///  @returns NSObject*
///
-(NSURLSessionTask*) privateGetWithdrawalsGetWithCurrency: (NSString*) currency
    count: (NSNumber*) count
    offset: (NSNumber*) offset
    completionHandler: (void (^)(NSObject* output, NSError* error)) handler {
    // verify the required parameter 'currency' is set
    if (currency == nil) {
        NSParameterAssert(currency);
        if(handler) {
            NSDictionary * userInfo = @{NSLocalizedDescriptionKey : [NSString stringWithFormat:NSLocalizedString(@"Missing required parameter '%@'", nil),@"currency"] };
            NSError* error = [NSError errorWithDomain:kOAIPrivateApiErrorDomain code:kOAIPrivateApiMissingParamErrorCode userInfo:userInfo];
            handler(nil, error);
        }
        return nil;
    }

    NSMutableString* resourcePath = [NSMutableString stringWithFormat:@"/private/get_withdrawals"];

    NSMutableDictionary *pathParams = [[NSMutableDictionary alloc] init];

    NSMutableDictionary* queryParams = [[NSMutableDictionary alloc] init];
    if (currency != nil) {
        queryParams[@"currency"] = currency;
    }
    if (count != nil) {
        queryParams[@"count"] = count;
    }
    if (offset != nil) {
        queryParams[@"offset"] = offset;
    }
    NSMutableDictionary* headerParams = [NSMutableDictionary dictionaryWithDictionary:self.apiClient.configuration.defaultHeaders];
    [headerParams addEntriesFromDictionary:self.defaultHeaders];
    // HTTP header `Accept`
    NSString *acceptHeader = [self.apiClient.sanitizer selectHeaderAccept:@[@"application/json"]];
    if(acceptHeader.length > 0) {
        headerParams[@"Accept"] = acceptHeader;
    }

    // response content type
    NSString *responseContentType = [[acceptHeader componentsSeparatedByString:@", "] firstObject] ?: @"";

    // request content type
    NSString *requestContentType = [self.apiClient.sanitizer selectHeaderContentType:@[]];

    // Authentication setting
    NSArray *authSettings = @[@"bearerAuth"];

    id bodyParam = nil;
    NSMutableDictionary *formParams = [[NSMutableDictionary alloc] init];
    NSMutableDictionary *localVarFiles = [[NSMutableDictionary alloc] init];

    return [self.apiClient requestWithPath: resourcePath
                                    method: @"GET"
                                pathParams: pathParams
                               queryParams: queryParams
                                formParams: formParams
                                     files: localVarFiles
                                      body: bodyParam
                              headerParams: headerParams
                              authSettings: authSettings
                        requestContentType: requestContentType
                       responseContentType: responseContentType
                              responseType: @"NSObject*"
                           completionBlock: ^(id data, NSError *error) {
                                if(handler) {
                                    handler((NSObject*)data, error);
                                }
                            }];
}

///
/// Adds new entry to address book of given type
/// 
///  @param currency The currency symbol 
///
///  @param type Address book type 
///
///  @param address Address in currency format, it must be in address book 
///
///  @param tfa TFA code, required when TFA is enabled for current account (optional)
///
///  @returns NSObject*
///
-(NSURLSessionTask*) privateRemoveFromAddressBookGetWithCurrency: (NSString*) currency
    type: (NSString*) type
    address: (NSString*) address
    tfa: (NSString*) tfa
    completionHandler: (void (^)(NSObject* output, NSError* error)) handler {
    // verify the required parameter 'currency' is set
    if (currency == nil) {
        NSParameterAssert(currency);
        if(handler) {
            NSDictionary * userInfo = @{NSLocalizedDescriptionKey : [NSString stringWithFormat:NSLocalizedString(@"Missing required parameter '%@'", nil),@"currency"] };
            NSError* error = [NSError errorWithDomain:kOAIPrivateApiErrorDomain code:kOAIPrivateApiMissingParamErrorCode userInfo:userInfo];
            handler(nil, error);
        }
        return nil;
    }

    // verify the required parameter 'type' is set
    if (type == nil) {
        NSParameterAssert(type);
        if(handler) {
            NSDictionary * userInfo = @{NSLocalizedDescriptionKey : [NSString stringWithFormat:NSLocalizedString(@"Missing required parameter '%@'", nil),@"type"] };
            NSError* error = [NSError errorWithDomain:kOAIPrivateApiErrorDomain code:kOAIPrivateApiMissingParamErrorCode userInfo:userInfo];
            handler(nil, error);
        }
        return nil;
    }

    // verify the required parameter 'address' is set
    if (address == nil) {
        NSParameterAssert(address);
        if(handler) {
            NSDictionary * userInfo = @{NSLocalizedDescriptionKey : [NSString stringWithFormat:NSLocalizedString(@"Missing required parameter '%@'", nil),@"address"] };
            NSError* error = [NSError errorWithDomain:kOAIPrivateApiErrorDomain code:kOAIPrivateApiMissingParamErrorCode userInfo:userInfo];
            handler(nil, error);
        }
        return nil;
    }

    NSMutableString* resourcePath = [NSMutableString stringWithFormat:@"/private/remove_from_address_book"];

    NSMutableDictionary *pathParams = [[NSMutableDictionary alloc] init];

    NSMutableDictionary* queryParams = [[NSMutableDictionary alloc] init];
    if (currency != nil) {
        queryParams[@"currency"] = currency;
    }
    if (type != nil) {
        queryParams[@"type"] = type;
    }
    if (address != nil) {
        queryParams[@"address"] = address;
    }
    if (tfa != nil) {
        queryParams[@"tfa"] = tfa;
    }
    NSMutableDictionary* headerParams = [NSMutableDictionary dictionaryWithDictionary:self.apiClient.configuration.defaultHeaders];
    [headerParams addEntriesFromDictionary:self.defaultHeaders];
    // HTTP header `Accept`
    NSString *acceptHeader = [self.apiClient.sanitizer selectHeaderAccept:@[@"application/json"]];
    if(acceptHeader.length > 0) {
        headerParams[@"Accept"] = acceptHeader;
    }

    // response content type
    NSString *responseContentType = [[acceptHeader componentsSeparatedByString:@", "] firstObject] ?: @"";

    // request content type
    NSString *requestContentType = [self.apiClient.sanitizer selectHeaderContentType:@[]];

    // Authentication setting
    NSArray *authSettings = @[@"bearerAuth"];

    id bodyParam = nil;
    NSMutableDictionary *formParams = [[NSMutableDictionary alloc] init];
    NSMutableDictionary *localVarFiles = [[NSMutableDictionary alloc] init];

    return [self.apiClient requestWithPath: resourcePath
                                    method: @"GET"
                                pathParams: pathParams
                               queryParams: queryParams
                                formParams: formParams
                                     files: localVarFiles
                                      body: bodyParam
                              headerParams: headerParams
                              authSettings: authSettings
                        requestContentType: requestContentType
                       responseContentType: responseContentType
                              responseType: @"NSObject*"
                           completionBlock: ^(id data, NSError *error) {
                                if(handler) {
                                    handler((NSObject*)data, error);
                                }
                            }];
}

///
/// Places a sell order for an instrument.
/// 
///  @param instrumentName Instrument name 
///
///  @param amount It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH 
///
///  @param type The order type, default: `\"limit\"` (optional)
///
///  @param label user defined label for the order (maximum 32 characters) (optional)
///
///  @param price <p>The order price in base currency (Only for limit and stop_limit orders)</p> <p>When adding order with advanced=usd, the field price should be the option price value in USD.</p> <p>When adding order with advanced=implv, the field price should be a value of implied volatility in percentages. For example,  price=100, means implied volatility of 100%</p> (optional)
///
///  @param timeInForce <p>Specifies how long the order remains in effect. Default `\"good_til_cancelled\"`</p> <ul> <li>`\"good_til_cancelled\"` - unfilled order remains in order book until cancelled</li> <li>`\"fill_or_kill\"` - execute a transaction immediately and completely or not at all</li> <li>`\"immediate_or_cancel\"` - execute a transaction immediately, and any portion of the order that cannot be immediately filled is cancelled</li> </ul> (optional, default to @"good_til_cancelled")
///
///  @param maxShow Maximum amount within an order to be shown to other customers, `0` for invisible order (optional, default to @1)
///
///  @param postOnly <p>If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.</p> <p>Only valid in combination with time_in_force=`\"good_til_cancelled\"`</p> (optional, default to @(YES))
///
///  @param reduceOnly If `true`, the order is considered reduce-only which is intended to only reduce a current position (optional, default to @(NO))
///
///  @param stopPrice Stop price, required for stop limit orders (Only for stop orders) (optional)
///
///  @param trigger Defines trigger type, required for `\"stop_limit\"` order type (optional)
///
///  @param advanced Advanced option order type. (Only for options) (optional)
///
///  @returns NSObject*
///
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
    completionHandler: (void (^)(NSObject* output, NSError* error)) handler {
    // verify the required parameter 'instrumentName' is set
    if (instrumentName == nil) {
        NSParameterAssert(instrumentName);
        if(handler) {
            NSDictionary * userInfo = @{NSLocalizedDescriptionKey : [NSString stringWithFormat:NSLocalizedString(@"Missing required parameter '%@'", nil),@"instrumentName"] };
            NSError* error = [NSError errorWithDomain:kOAIPrivateApiErrorDomain code:kOAIPrivateApiMissingParamErrorCode userInfo:userInfo];
            handler(nil, error);
        }
        return nil;
    }

    // verify the required parameter 'amount' is set
    if (amount == nil) {
        NSParameterAssert(amount);
        if(handler) {
            NSDictionary * userInfo = @{NSLocalizedDescriptionKey : [NSString stringWithFormat:NSLocalizedString(@"Missing required parameter '%@'", nil),@"amount"] };
            NSError* error = [NSError errorWithDomain:kOAIPrivateApiErrorDomain code:kOAIPrivateApiMissingParamErrorCode userInfo:userInfo];
            handler(nil, error);
        }
        return nil;
    }

    NSMutableString* resourcePath = [NSMutableString stringWithFormat:@"/private/sell"];

    NSMutableDictionary *pathParams = [[NSMutableDictionary alloc] init];

    NSMutableDictionary* queryParams = [[NSMutableDictionary alloc] init];
    if (instrumentName != nil) {
        queryParams[@"instrument_name"] = instrumentName;
    }
    if (amount != nil) {
        queryParams[@"amount"] = amount;
    }
    if (type != nil) {
        queryParams[@"type"] = type;
    }
    if (label != nil) {
        queryParams[@"label"] = label;
    }
    if (price != nil) {
        queryParams[@"price"] = price;
    }
    if (timeInForce != nil) {
        queryParams[@"time_in_force"] = timeInForce;
    }
    if (maxShow != nil) {
        queryParams[@"max_show"] = maxShow;
    }
    if (postOnly != nil) {
        queryParams[@"post_only"] = [postOnly isEqual:@(YES)] ? @"true" : @"false";
    }
    if (reduceOnly != nil) {
        queryParams[@"reduce_only"] = [reduceOnly isEqual:@(YES)] ? @"true" : @"false";
    }
    if (stopPrice != nil) {
        queryParams[@"stop_price"] = stopPrice;
    }
    if (trigger != nil) {
        queryParams[@"trigger"] = trigger;
    }
    if (advanced != nil) {
        queryParams[@"advanced"] = advanced;
    }
    NSMutableDictionary* headerParams = [NSMutableDictionary dictionaryWithDictionary:self.apiClient.configuration.defaultHeaders];
    [headerParams addEntriesFromDictionary:self.defaultHeaders];
    // HTTP header `Accept`
    NSString *acceptHeader = [self.apiClient.sanitizer selectHeaderAccept:@[@"application/json"]];
    if(acceptHeader.length > 0) {
        headerParams[@"Accept"] = acceptHeader;
    }

    // response content type
    NSString *responseContentType = [[acceptHeader componentsSeparatedByString:@", "] firstObject] ?: @"";

    // request content type
    NSString *requestContentType = [self.apiClient.sanitizer selectHeaderContentType:@[]];

    // Authentication setting
    NSArray *authSettings = @[@"bearerAuth"];

    id bodyParam = nil;
    NSMutableDictionary *formParams = [[NSMutableDictionary alloc] init];
    NSMutableDictionary *localVarFiles = [[NSMutableDictionary alloc] init];

    return [self.apiClient requestWithPath: resourcePath
                                    method: @"GET"
                                pathParams: pathParams
                               queryParams: queryParams
                                formParams: formParams
                                     files: localVarFiles
                                      body: bodyParam
                              headerParams: headerParams
                              authSettings: authSettings
                        requestContentType: requestContentType
                       responseContentType: responseContentType
                              responseType: @"NSObject*"
                           completionBlock: ^(id data, NSError *error) {
                                if(handler) {
                                    handler((NSObject*)data, error);
                                }
                            }];
}

///
/// Marks an announcement as read, so it will not be shown in `get_new_announcements`.
/// 
///  @param announcementId the ID of the announcement 
///
///  @returns NSObject*
///
-(NSURLSessionTask*) privateSetAnnouncementAsReadGetWithAnnouncementId: (NSNumber*) announcementId
    completionHandler: (void (^)(NSObject* output, NSError* error)) handler {
    // verify the required parameter 'announcementId' is set
    if (announcementId == nil) {
        NSParameterAssert(announcementId);
        if(handler) {
            NSDictionary * userInfo = @{NSLocalizedDescriptionKey : [NSString stringWithFormat:NSLocalizedString(@"Missing required parameter '%@'", nil),@"announcementId"] };
            NSError* error = [NSError errorWithDomain:kOAIPrivateApiErrorDomain code:kOAIPrivateApiMissingParamErrorCode userInfo:userInfo];
            handler(nil, error);
        }
        return nil;
    }

    NSMutableString* resourcePath = [NSMutableString stringWithFormat:@"/private/set_announcement_as_read"];

    NSMutableDictionary *pathParams = [[NSMutableDictionary alloc] init];

    NSMutableDictionary* queryParams = [[NSMutableDictionary alloc] init];
    if (announcementId != nil) {
        queryParams[@"announcement_id"] = announcementId;
    }
    NSMutableDictionary* headerParams = [NSMutableDictionary dictionaryWithDictionary:self.apiClient.configuration.defaultHeaders];
    [headerParams addEntriesFromDictionary:self.defaultHeaders];
    // HTTP header `Accept`
    NSString *acceptHeader = [self.apiClient.sanitizer selectHeaderAccept:@[@"application/json"]];
    if(acceptHeader.length > 0) {
        headerParams[@"Accept"] = acceptHeader;
    }

    // response content type
    NSString *responseContentType = [[acceptHeader componentsSeparatedByString:@", "] firstObject] ?: @"";

    // request content type
    NSString *requestContentType = [self.apiClient.sanitizer selectHeaderContentType:@[]];

    // Authentication setting
    NSArray *authSettings = @[@"bearerAuth"];

    id bodyParam = nil;
    NSMutableDictionary *formParams = [[NSMutableDictionary alloc] init];
    NSMutableDictionary *localVarFiles = [[NSMutableDictionary alloc] init];

    return [self.apiClient requestWithPath: resourcePath
                                    method: @"GET"
                                pathParams: pathParams
                               queryParams: queryParams
                                formParams: formParams
                                     files: localVarFiles
                                      body: bodyParam
                              headerParams: headerParams
                              authSettings: authSettings
                        requestContentType: requestContentType
                       responseContentType: responseContentType
                              responseType: @"NSObject*"
                           completionBlock: ^(id data, NSError *error) {
                                if(handler) {
                                    handler((NSObject*)data, error);
                                }
                            }];
}

///
/// Assign an email address to a subaccount. User will receive an email with confirmation link.
/// 
///  @param sid The user id for the subaccount 
///
///  @param email The email address for the subaccount 
///
///  @returns NSObject*
///
-(NSURLSessionTask*) privateSetEmailForSubaccountGetWithSid: (NSNumber*) sid
    email: (NSString*) email
    completionHandler: (void (^)(NSObject* output, NSError* error)) handler {
    // verify the required parameter 'sid' is set
    if (sid == nil) {
        NSParameterAssert(sid);
        if(handler) {
            NSDictionary * userInfo = @{NSLocalizedDescriptionKey : [NSString stringWithFormat:NSLocalizedString(@"Missing required parameter '%@'", nil),@"sid"] };
            NSError* error = [NSError errorWithDomain:kOAIPrivateApiErrorDomain code:kOAIPrivateApiMissingParamErrorCode userInfo:userInfo];
            handler(nil, error);
        }
        return nil;
    }

    // verify the required parameter 'email' is set
    if (email == nil) {
        NSParameterAssert(email);
        if(handler) {
            NSDictionary * userInfo = @{NSLocalizedDescriptionKey : [NSString stringWithFormat:NSLocalizedString(@"Missing required parameter '%@'", nil),@"email"] };
            NSError* error = [NSError errorWithDomain:kOAIPrivateApiErrorDomain code:kOAIPrivateApiMissingParamErrorCode userInfo:userInfo];
            handler(nil, error);
        }
        return nil;
    }

    NSMutableString* resourcePath = [NSMutableString stringWithFormat:@"/private/set_email_for_subaccount"];

    NSMutableDictionary *pathParams = [[NSMutableDictionary alloc] init];

    NSMutableDictionary* queryParams = [[NSMutableDictionary alloc] init];
    if (sid != nil) {
        queryParams[@"sid"] = sid;
    }
    if (email != nil) {
        queryParams[@"email"] = email;
    }
    NSMutableDictionary* headerParams = [NSMutableDictionary dictionaryWithDictionary:self.apiClient.configuration.defaultHeaders];
    [headerParams addEntriesFromDictionary:self.defaultHeaders];
    // HTTP header `Accept`
    NSString *acceptHeader = [self.apiClient.sanitizer selectHeaderAccept:@[@"application/json"]];
    if(acceptHeader.length > 0) {
        headerParams[@"Accept"] = acceptHeader;
    }

    // response content type
    NSString *responseContentType = [[acceptHeader componentsSeparatedByString:@", "] firstObject] ?: @"";

    // request content type
    NSString *requestContentType = [self.apiClient.sanitizer selectHeaderContentType:@[]];

    // Authentication setting
    NSArray *authSettings = @[@"bearerAuth"];

    id bodyParam = nil;
    NSMutableDictionary *formParams = [[NSMutableDictionary alloc] init];
    NSMutableDictionary *localVarFiles = [[NSMutableDictionary alloc] init];

    return [self.apiClient requestWithPath: resourcePath
                                    method: @"GET"
                                pathParams: pathParams
                               queryParams: queryParams
                                formParams: formParams
                                     files: localVarFiles
                                      body: bodyParam
                              headerParams: headerParams
                              authSettings: authSettings
                        requestContentType: requestContentType
                       responseContentType: responseContentType
                              responseType: @"NSObject*"
                           completionBlock: ^(id data, NSError *error) {
                                if(handler) {
                                    handler((NSObject*)data, error);
                                }
                            }];
}

///
/// Changes the language to be used for emails.
/// 
///  @param language The abbreviated language name. Valid values include `\"en\"`, `\"ko\"`, `\"zh\"` 
///
///  @returns NSObject*
///
-(NSURLSessionTask*) privateSetEmailLanguageGetWithLanguage: (NSString*) language
    completionHandler: (void (^)(NSObject* output, NSError* error)) handler {
    // verify the required parameter 'language' is set
    if (language == nil) {
        NSParameterAssert(language);
        if(handler) {
            NSDictionary * userInfo = @{NSLocalizedDescriptionKey : [NSString stringWithFormat:NSLocalizedString(@"Missing required parameter '%@'", nil),@"language"] };
            NSError* error = [NSError errorWithDomain:kOAIPrivateApiErrorDomain code:kOAIPrivateApiMissingParamErrorCode userInfo:userInfo];
            handler(nil, error);
        }
        return nil;
    }

    NSMutableString* resourcePath = [NSMutableString stringWithFormat:@"/private/set_email_language"];

    NSMutableDictionary *pathParams = [[NSMutableDictionary alloc] init];

    NSMutableDictionary* queryParams = [[NSMutableDictionary alloc] init];
    if (language != nil) {
        queryParams[@"language"] = language;
    }
    NSMutableDictionary* headerParams = [NSMutableDictionary dictionaryWithDictionary:self.apiClient.configuration.defaultHeaders];
    [headerParams addEntriesFromDictionary:self.defaultHeaders];
    // HTTP header `Accept`
    NSString *acceptHeader = [self.apiClient.sanitizer selectHeaderAccept:@[@"application/json"]];
    if(acceptHeader.length > 0) {
        headerParams[@"Accept"] = acceptHeader;
    }

    // response content type
    NSString *responseContentType = [[acceptHeader componentsSeparatedByString:@", "] firstObject] ?: @"";

    // request content type
    NSString *requestContentType = [self.apiClient.sanitizer selectHeaderContentType:@[]];

    // Authentication setting
    NSArray *authSettings = @[@"bearerAuth"];

    id bodyParam = nil;
    NSMutableDictionary *formParams = [[NSMutableDictionary alloc] init];
    NSMutableDictionary *localVarFiles = [[NSMutableDictionary alloc] init];

    return [self.apiClient requestWithPath: resourcePath
                                    method: @"GET"
                                pathParams: pathParams
                               queryParams: queryParams
                                formParams: formParams
                                     files: localVarFiles
                                      body: bodyParam
                              headerParams: headerParams
                              authSettings: authSettings
                        requestContentType: requestContentType
                       responseContentType: responseContentType
                              responseType: @"NSObject*"
                           completionBlock: ^(id data, NSError *error) {
                                if(handler) {
                                    handler((NSObject*)data, error);
                                }
                            }];
}

///
/// Set the password for the subaccount
/// 
///  @param sid The user id for the subaccount 
///
///  @param password The password for the subaccount 
///
///  @returns NSObject*
///
-(NSURLSessionTask*) privateSetPasswordForSubaccountGetWithSid: (NSNumber*) sid
    password: (NSString*) password
    completionHandler: (void (^)(NSObject* output, NSError* error)) handler {
    // verify the required parameter 'sid' is set
    if (sid == nil) {
        NSParameterAssert(sid);
        if(handler) {
            NSDictionary * userInfo = @{NSLocalizedDescriptionKey : [NSString stringWithFormat:NSLocalizedString(@"Missing required parameter '%@'", nil),@"sid"] };
            NSError* error = [NSError errorWithDomain:kOAIPrivateApiErrorDomain code:kOAIPrivateApiMissingParamErrorCode userInfo:userInfo];
            handler(nil, error);
        }
        return nil;
    }

    // verify the required parameter 'password' is set
    if (password == nil) {
        NSParameterAssert(password);
        if(handler) {
            NSDictionary * userInfo = @{NSLocalizedDescriptionKey : [NSString stringWithFormat:NSLocalizedString(@"Missing required parameter '%@'", nil),@"password"] };
            NSError* error = [NSError errorWithDomain:kOAIPrivateApiErrorDomain code:kOAIPrivateApiMissingParamErrorCode userInfo:userInfo];
            handler(nil, error);
        }
        return nil;
    }

    NSMutableString* resourcePath = [NSMutableString stringWithFormat:@"/private/set_password_for_subaccount"];

    NSMutableDictionary *pathParams = [[NSMutableDictionary alloc] init];

    NSMutableDictionary* queryParams = [[NSMutableDictionary alloc] init];
    if (sid != nil) {
        queryParams[@"sid"] = sid;
    }
    if (password != nil) {
        queryParams[@"password"] = password;
    }
    NSMutableDictionary* headerParams = [NSMutableDictionary dictionaryWithDictionary:self.apiClient.configuration.defaultHeaders];
    [headerParams addEntriesFromDictionary:self.defaultHeaders];
    // HTTP header `Accept`
    NSString *acceptHeader = [self.apiClient.sanitizer selectHeaderAccept:@[@"application/json"]];
    if(acceptHeader.length > 0) {
        headerParams[@"Accept"] = acceptHeader;
    }

    // response content type
    NSString *responseContentType = [[acceptHeader componentsSeparatedByString:@", "] firstObject] ?: @"";

    // request content type
    NSString *requestContentType = [self.apiClient.sanitizer selectHeaderContentType:@[]];

    // Authentication setting
    NSArray *authSettings = @[@"bearerAuth"];

    id bodyParam = nil;
    NSMutableDictionary *formParams = [[NSMutableDictionary alloc] init];
    NSMutableDictionary *localVarFiles = [[NSMutableDictionary alloc] init];

    return [self.apiClient requestWithPath: resourcePath
                                    method: @"GET"
                                pathParams: pathParams
                               queryParams: queryParams
                                formParams: formParams
                                     files: localVarFiles
                                      body: bodyParam
                              headerParams: headerParams
                              authSettings: authSettings
                        requestContentType: requestContentType
                       responseContentType: responseContentType
                              responseType: @"NSObject*"
                           completionBlock: ^(id data, NSError *error) {
                                if(handler) {
                                    handler((NSObject*)data, error);
                                }
                            }];
}

///
/// Transfer funds to a subaccount.
/// 
///  @param currency The currency symbol 
///
///  @param amount Amount of funds to be transferred 
///
///  @param destination Id of destination subaccount 
///
///  @returns NSObject*
///
-(NSURLSessionTask*) privateSubmitTransferToSubaccountGetWithCurrency: (NSString*) currency
    amount: (NSNumber*) amount
    destination: (NSNumber*) destination
    completionHandler: (void (^)(NSObject* output, NSError* error)) handler {
    // verify the required parameter 'currency' is set
    if (currency == nil) {
        NSParameterAssert(currency);
        if(handler) {
            NSDictionary * userInfo = @{NSLocalizedDescriptionKey : [NSString stringWithFormat:NSLocalizedString(@"Missing required parameter '%@'", nil),@"currency"] };
            NSError* error = [NSError errorWithDomain:kOAIPrivateApiErrorDomain code:kOAIPrivateApiMissingParamErrorCode userInfo:userInfo];
            handler(nil, error);
        }
        return nil;
    }

    // verify the required parameter 'amount' is set
    if (amount == nil) {
        NSParameterAssert(amount);
        if(handler) {
            NSDictionary * userInfo = @{NSLocalizedDescriptionKey : [NSString stringWithFormat:NSLocalizedString(@"Missing required parameter '%@'", nil),@"amount"] };
            NSError* error = [NSError errorWithDomain:kOAIPrivateApiErrorDomain code:kOAIPrivateApiMissingParamErrorCode userInfo:userInfo];
            handler(nil, error);
        }
        return nil;
    }

    // verify the required parameter 'destination' is set
    if (destination == nil) {
        NSParameterAssert(destination);
        if(handler) {
            NSDictionary * userInfo = @{NSLocalizedDescriptionKey : [NSString stringWithFormat:NSLocalizedString(@"Missing required parameter '%@'", nil),@"destination"] };
            NSError* error = [NSError errorWithDomain:kOAIPrivateApiErrorDomain code:kOAIPrivateApiMissingParamErrorCode userInfo:userInfo];
            handler(nil, error);
        }
        return nil;
    }

    NSMutableString* resourcePath = [NSMutableString stringWithFormat:@"/private/submit_transfer_to_subaccount"];

    NSMutableDictionary *pathParams = [[NSMutableDictionary alloc] init];

    NSMutableDictionary* queryParams = [[NSMutableDictionary alloc] init];
    if (currency != nil) {
        queryParams[@"currency"] = currency;
    }
    if (amount != nil) {
        queryParams[@"amount"] = amount;
    }
    if (destination != nil) {
        queryParams[@"destination"] = destination;
    }
    NSMutableDictionary* headerParams = [NSMutableDictionary dictionaryWithDictionary:self.apiClient.configuration.defaultHeaders];
    [headerParams addEntriesFromDictionary:self.defaultHeaders];
    // HTTP header `Accept`
    NSString *acceptHeader = [self.apiClient.sanitizer selectHeaderAccept:@[@"application/json"]];
    if(acceptHeader.length > 0) {
        headerParams[@"Accept"] = acceptHeader;
    }

    // response content type
    NSString *responseContentType = [[acceptHeader componentsSeparatedByString:@", "] firstObject] ?: @"";

    // request content type
    NSString *requestContentType = [self.apiClient.sanitizer selectHeaderContentType:@[]];

    // Authentication setting
    NSArray *authSettings = @[@"bearerAuth"];

    id bodyParam = nil;
    NSMutableDictionary *formParams = [[NSMutableDictionary alloc] init];
    NSMutableDictionary *localVarFiles = [[NSMutableDictionary alloc] init];

    return [self.apiClient requestWithPath: resourcePath
                                    method: @"GET"
                                pathParams: pathParams
                               queryParams: queryParams
                                formParams: formParams
                                     files: localVarFiles
                                      body: bodyParam
                              headerParams: headerParams
                              authSettings: authSettings
                        requestContentType: requestContentType
                       responseContentType: responseContentType
                              responseType: @"NSObject*"
                           completionBlock: ^(id data, NSError *error) {
                                if(handler) {
                                    handler((NSObject*)data, error);
                                }
                            }];
}

///
/// Transfer funds to a another user.
/// 
///  @param currency The currency symbol 
///
///  @param amount Amount of funds to be transferred 
///
///  @param destination Destination address from address book 
///
///  @param tfa TFA code, required when TFA is enabled for current account (optional)
///
///  @returns NSObject*
///
-(NSURLSessionTask*) privateSubmitTransferToUserGetWithCurrency: (NSString*) currency
    amount: (NSNumber*) amount
    destination: (NSString*) destination
    tfa: (NSString*) tfa
    completionHandler: (void (^)(NSObject* output, NSError* error)) handler {
    // verify the required parameter 'currency' is set
    if (currency == nil) {
        NSParameterAssert(currency);
        if(handler) {
            NSDictionary * userInfo = @{NSLocalizedDescriptionKey : [NSString stringWithFormat:NSLocalizedString(@"Missing required parameter '%@'", nil),@"currency"] };
            NSError* error = [NSError errorWithDomain:kOAIPrivateApiErrorDomain code:kOAIPrivateApiMissingParamErrorCode userInfo:userInfo];
            handler(nil, error);
        }
        return nil;
    }

    // verify the required parameter 'amount' is set
    if (amount == nil) {
        NSParameterAssert(amount);
        if(handler) {
            NSDictionary * userInfo = @{NSLocalizedDescriptionKey : [NSString stringWithFormat:NSLocalizedString(@"Missing required parameter '%@'", nil),@"amount"] };
            NSError* error = [NSError errorWithDomain:kOAIPrivateApiErrorDomain code:kOAIPrivateApiMissingParamErrorCode userInfo:userInfo];
            handler(nil, error);
        }
        return nil;
    }

    // verify the required parameter 'destination' is set
    if (destination == nil) {
        NSParameterAssert(destination);
        if(handler) {
            NSDictionary * userInfo = @{NSLocalizedDescriptionKey : [NSString stringWithFormat:NSLocalizedString(@"Missing required parameter '%@'", nil),@"destination"] };
            NSError* error = [NSError errorWithDomain:kOAIPrivateApiErrorDomain code:kOAIPrivateApiMissingParamErrorCode userInfo:userInfo];
            handler(nil, error);
        }
        return nil;
    }

    NSMutableString* resourcePath = [NSMutableString stringWithFormat:@"/private/submit_transfer_to_user"];

    NSMutableDictionary *pathParams = [[NSMutableDictionary alloc] init];

    NSMutableDictionary* queryParams = [[NSMutableDictionary alloc] init];
    if (currency != nil) {
        queryParams[@"currency"] = currency;
    }
    if (amount != nil) {
        queryParams[@"amount"] = amount;
    }
    if (destination != nil) {
        queryParams[@"destination"] = destination;
    }
    if (tfa != nil) {
        queryParams[@"tfa"] = tfa;
    }
    NSMutableDictionary* headerParams = [NSMutableDictionary dictionaryWithDictionary:self.apiClient.configuration.defaultHeaders];
    [headerParams addEntriesFromDictionary:self.defaultHeaders];
    // HTTP header `Accept`
    NSString *acceptHeader = [self.apiClient.sanitizer selectHeaderAccept:@[@"application/json"]];
    if(acceptHeader.length > 0) {
        headerParams[@"Accept"] = acceptHeader;
    }

    // response content type
    NSString *responseContentType = [[acceptHeader componentsSeparatedByString:@", "] firstObject] ?: @"";

    // request content type
    NSString *requestContentType = [self.apiClient.sanitizer selectHeaderContentType:@[]];

    // Authentication setting
    NSArray *authSettings = @[@"bearerAuth"];

    id bodyParam = nil;
    NSMutableDictionary *formParams = [[NSMutableDictionary alloc] init];
    NSMutableDictionary *localVarFiles = [[NSMutableDictionary alloc] init];

    return [self.apiClient requestWithPath: resourcePath
                                    method: @"GET"
                                pathParams: pathParams
                               queryParams: queryParams
                                formParams: formParams
                                     files: localVarFiles
                                      body: bodyParam
                              headerParams: headerParams
                              authSettings: authSettings
                        requestContentType: requestContentType
                       responseContentType: responseContentType
                              responseType: @"NSObject*"
                           completionBlock: ^(id data, NSError *error) {
                                if(handler) {
                                    handler((NSObject*)data, error);
                                }
                            }];
}

///
/// Enable or disable deposit address creation
/// 
///  @param currency The currency symbol 
///
///  @param state  
///
///  @returns NSObject*
///
-(NSURLSessionTask*) privateToggleDepositAddressCreationGetWithCurrency: (NSString*) currency
    state: (NSNumber*) state
    completionHandler: (void (^)(NSObject* output, NSError* error)) handler {
    // verify the required parameter 'currency' is set
    if (currency == nil) {
        NSParameterAssert(currency);
        if(handler) {
            NSDictionary * userInfo = @{NSLocalizedDescriptionKey : [NSString stringWithFormat:NSLocalizedString(@"Missing required parameter '%@'", nil),@"currency"] };
            NSError* error = [NSError errorWithDomain:kOAIPrivateApiErrorDomain code:kOAIPrivateApiMissingParamErrorCode userInfo:userInfo];
            handler(nil, error);
        }
        return nil;
    }

    // verify the required parameter 'state' is set
    if (state == nil) {
        NSParameterAssert(state);
        if(handler) {
            NSDictionary * userInfo = @{NSLocalizedDescriptionKey : [NSString stringWithFormat:NSLocalizedString(@"Missing required parameter '%@'", nil),@"state"] };
            NSError* error = [NSError errorWithDomain:kOAIPrivateApiErrorDomain code:kOAIPrivateApiMissingParamErrorCode userInfo:userInfo];
            handler(nil, error);
        }
        return nil;
    }

    NSMutableString* resourcePath = [NSMutableString stringWithFormat:@"/private/toggle_deposit_address_creation"];

    NSMutableDictionary *pathParams = [[NSMutableDictionary alloc] init];

    NSMutableDictionary* queryParams = [[NSMutableDictionary alloc] init];
    if (currency != nil) {
        queryParams[@"currency"] = currency;
    }
    if (state != nil) {
        queryParams[@"state"] = [state isEqual:@(YES)] ? @"true" : @"false";
    }
    NSMutableDictionary* headerParams = [NSMutableDictionary dictionaryWithDictionary:self.apiClient.configuration.defaultHeaders];
    [headerParams addEntriesFromDictionary:self.defaultHeaders];
    // HTTP header `Accept`
    NSString *acceptHeader = [self.apiClient.sanitizer selectHeaderAccept:@[@"application/json"]];
    if(acceptHeader.length > 0) {
        headerParams[@"Accept"] = acceptHeader;
    }

    // response content type
    NSString *responseContentType = [[acceptHeader componentsSeparatedByString:@", "] firstObject] ?: @"";

    // request content type
    NSString *requestContentType = [self.apiClient.sanitizer selectHeaderContentType:@[]];

    // Authentication setting
    NSArray *authSettings = @[@"bearerAuth"];

    id bodyParam = nil;
    NSMutableDictionary *formParams = [[NSMutableDictionary alloc] init];
    NSMutableDictionary *localVarFiles = [[NSMutableDictionary alloc] init];

    return [self.apiClient requestWithPath: resourcePath
                                    method: @"GET"
                                pathParams: pathParams
                               queryParams: queryParams
                                formParams: formParams
                                     files: localVarFiles
                                      body: bodyParam
                              headerParams: headerParams
                              authSettings: authSettings
                        requestContentType: requestContentType
                       responseContentType: responseContentType
                              responseType: @"NSObject*"
                           completionBlock: ^(id data, NSError *error) {
                                if(handler) {
                                    handler((NSObject*)data, error);
                                }
                            }];
}

///
/// Enable or disable sending of notifications for the subaccount.
/// 
///  @param sid The user id for the subaccount 
///
///  @param state enable (`true`) or disable (`false`) notifications 
///
///  @returns NSObject*
///
-(NSURLSessionTask*) privateToggleNotificationsFromSubaccountGetWithSid: (NSNumber*) sid
    state: (NSNumber*) state
    completionHandler: (void (^)(NSObject* output, NSError* error)) handler {
    // verify the required parameter 'sid' is set
    if (sid == nil) {
        NSParameterAssert(sid);
        if(handler) {
            NSDictionary * userInfo = @{NSLocalizedDescriptionKey : [NSString stringWithFormat:NSLocalizedString(@"Missing required parameter '%@'", nil),@"sid"] };
            NSError* error = [NSError errorWithDomain:kOAIPrivateApiErrorDomain code:kOAIPrivateApiMissingParamErrorCode userInfo:userInfo];
            handler(nil, error);
        }
        return nil;
    }

    // verify the required parameter 'state' is set
    if (state == nil) {
        NSParameterAssert(state);
        if(handler) {
            NSDictionary * userInfo = @{NSLocalizedDescriptionKey : [NSString stringWithFormat:NSLocalizedString(@"Missing required parameter '%@'", nil),@"state"] };
            NSError* error = [NSError errorWithDomain:kOAIPrivateApiErrorDomain code:kOAIPrivateApiMissingParamErrorCode userInfo:userInfo];
            handler(nil, error);
        }
        return nil;
    }

    NSMutableString* resourcePath = [NSMutableString stringWithFormat:@"/private/toggle_notifications_from_subaccount"];

    NSMutableDictionary *pathParams = [[NSMutableDictionary alloc] init];

    NSMutableDictionary* queryParams = [[NSMutableDictionary alloc] init];
    if (sid != nil) {
        queryParams[@"sid"] = sid;
    }
    if (state != nil) {
        queryParams[@"state"] = [state isEqual:@(YES)] ? @"true" : @"false";
    }
    NSMutableDictionary* headerParams = [NSMutableDictionary dictionaryWithDictionary:self.apiClient.configuration.defaultHeaders];
    [headerParams addEntriesFromDictionary:self.defaultHeaders];
    // HTTP header `Accept`
    NSString *acceptHeader = [self.apiClient.sanitizer selectHeaderAccept:@[@"application/json"]];
    if(acceptHeader.length > 0) {
        headerParams[@"Accept"] = acceptHeader;
    }

    // response content type
    NSString *responseContentType = [[acceptHeader componentsSeparatedByString:@", "] firstObject] ?: @"";

    // request content type
    NSString *requestContentType = [self.apiClient.sanitizer selectHeaderContentType:@[]];

    // Authentication setting
    NSArray *authSettings = @[@"bearerAuth"];

    id bodyParam = nil;
    NSMutableDictionary *formParams = [[NSMutableDictionary alloc] init];
    NSMutableDictionary *localVarFiles = [[NSMutableDictionary alloc] init];

    return [self.apiClient requestWithPath: resourcePath
                                    method: @"GET"
                                pathParams: pathParams
                               queryParams: queryParams
                                formParams: formParams
                                     files: localVarFiles
                                      body: bodyParam
                              headerParams: headerParams
                              authSettings: authSettings
                        requestContentType: requestContentType
                       responseContentType: responseContentType
                              responseType: @"NSObject*"
                           completionBlock: ^(id data, NSError *error) {
                                if(handler) {
                                    handler((NSObject*)data, error);
                                }
                            }];
}

///
/// Enable or disable login for a subaccount. If login is disabled and a session for the subaccount exists, this session will be terminated.
/// 
///  @param sid The user id for the subaccount 
///
///  @param state enable or disable login. 
///
///  @returns NSObject*
///
-(NSURLSessionTask*) privateToggleSubaccountLoginGetWithSid: (NSNumber*) sid
    state: (NSString*) state
    completionHandler: (void (^)(NSObject* output, NSError* error)) handler {
    // verify the required parameter 'sid' is set
    if (sid == nil) {
        NSParameterAssert(sid);
        if(handler) {
            NSDictionary * userInfo = @{NSLocalizedDescriptionKey : [NSString stringWithFormat:NSLocalizedString(@"Missing required parameter '%@'", nil),@"sid"] };
            NSError* error = [NSError errorWithDomain:kOAIPrivateApiErrorDomain code:kOAIPrivateApiMissingParamErrorCode userInfo:userInfo];
            handler(nil, error);
        }
        return nil;
    }

    // verify the required parameter 'state' is set
    if (state == nil) {
        NSParameterAssert(state);
        if(handler) {
            NSDictionary * userInfo = @{NSLocalizedDescriptionKey : [NSString stringWithFormat:NSLocalizedString(@"Missing required parameter '%@'", nil),@"state"] };
            NSError* error = [NSError errorWithDomain:kOAIPrivateApiErrorDomain code:kOAIPrivateApiMissingParamErrorCode userInfo:userInfo];
            handler(nil, error);
        }
        return nil;
    }

    NSMutableString* resourcePath = [NSMutableString stringWithFormat:@"/private/toggle_subaccount_login"];

    NSMutableDictionary *pathParams = [[NSMutableDictionary alloc] init];

    NSMutableDictionary* queryParams = [[NSMutableDictionary alloc] init];
    if (sid != nil) {
        queryParams[@"sid"] = sid;
    }
    if (state != nil) {
        queryParams[@"state"] = state;
    }
    NSMutableDictionary* headerParams = [NSMutableDictionary dictionaryWithDictionary:self.apiClient.configuration.defaultHeaders];
    [headerParams addEntriesFromDictionary:self.defaultHeaders];
    // HTTP header `Accept`
    NSString *acceptHeader = [self.apiClient.sanitizer selectHeaderAccept:@[@"application/json"]];
    if(acceptHeader.length > 0) {
        headerParams[@"Accept"] = acceptHeader;
    }

    // response content type
    NSString *responseContentType = [[acceptHeader componentsSeparatedByString:@", "] firstObject] ?: @"";

    // request content type
    NSString *requestContentType = [self.apiClient.sanitizer selectHeaderContentType:@[]];

    // Authentication setting
    NSArray *authSettings = @[@"bearerAuth"];

    id bodyParam = nil;
    NSMutableDictionary *formParams = [[NSMutableDictionary alloc] init];
    NSMutableDictionary *localVarFiles = [[NSMutableDictionary alloc] init];

    return [self.apiClient requestWithPath: resourcePath
                                    method: @"GET"
                                pathParams: pathParams
                               queryParams: queryParams
                                formParams: formParams
                                     files: localVarFiles
                                      body: bodyParam
                              headerParams: headerParams
                              authSettings: authSettings
                        requestContentType: requestContentType
                       responseContentType: responseContentType
                              responseType: @"NSObject*"
                           completionBlock: ^(id data, NSError *error) {
                                if(handler) {
                                    handler((NSObject*)data, error);
                                }
                            }];
}

///
/// Creates a new withdrawal request
/// 
///  @param currency The currency symbol 
///
///  @param address Address in currency format, it must be in address book 
///
///  @param amount Amount of funds to be withdrawn 
///
///  @param priority Withdrawal priority, optional for BTC, default: `high` (optional)
///
///  @param tfa TFA code, required when TFA is enabled for current account (optional)
///
///  @returns NSObject*
///
-(NSURLSessionTask*) privateWithdrawGetWithCurrency: (NSString*) currency
    address: (NSString*) address
    amount: (NSNumber*) amount
    priority: (NSString*) priority
    tfa: (NSString*) tfa
    completionHandler: (void (^)(NSObject* output, NSError* error)) handler {
    // verify the required parameter 'currency' is set
    if (currency == nil) {
        NSParameterAssert(currency);
        if(handler) {
            NSDictionary * userInfo = @{NSLocalizedDescriptionKey : [NSString stringWithFormat:NSLocalizedString(@"Missing required parameter '%@'", nil),@"currency"] };
            NSError* error = [NSError errorWithDomain:kOAIPrivateApiErrorDomain code:kOAIPrivateApiMissingParamErrorCode userInfo:userInfo];
            handler(nil, error);
        }
        return nil;
    }

    // verify the required parameter 'address' is set
    if (address == nil) {
        NSParameterAssert(address);
        if(handler) {
            NSDictionary * userInfo = @{NSLocalizedDescriptionKey : [NSString stringWithFormat:NSLocalizedString(@"Missing required parameter '%@'", nil),@"address"] };
            NSError* error = [NSError errorWithDomain:kOAIPrivateApiErrorDomain code:kOAIPrivateApiMissingParamErrorCode userInfo:userInfo];
            handler(nil, error);
        }
        return nil;
    }

    // verify the required parameter 'amount' is set
    if (amount == nil) {
        NSParameterAssert(amount);
        if(handler) {
            NSDictionary * userInfo = @{NSLocalizedDescriptionKey : [NSString stringWithFormat:NSLocalizedString(@"Missing required parameter '%@'", nil),@"amount"] };
            NSError* error = [NSError errorWithDomain:kOAIPrivateApiErrorDomain code:kOAIPrivateApiMissingParamErrorCode userInfo:userInfo];
            handler(nil, error);
        }
        return nil;
    }

    NSMutableString* resourcePath = [NSMutableString stringWithFormat:@"/private/withdraw"];

    NSMutableDictionary *pathParams = [[NSMutableDictionary alloc] init];

    NSMutableDictionary* queryParams = [[NSMutableDictionary alloc] init];
    if (currency != nil) {
        queryParams[@"currency"] = currency;
    }
    if (address != nil) {
        queryParams[@"address"] = address;
    }
    if (amount != nil) {
        queryParams[@"amount"] = amount;
    }
    if (priority != nil) {
        queryParams[@"priority"] = priority;
    }
    if (tfa != nil) {
        queryParams[@"tfa"] = tfa;
    }
    NSMutableDictionary* headerParams = [NSMutableDictionary dictionaryWithDictionary:self.apiClient.configuration.defaultHeaders];
    [headerParams addEntriesFromDictionary:self.defaultHeaders];
    // HTTP header `Accept`
    NSString *acceptHeader = [self.apiClient.sanitizer selectHeaderAccept:@[@"application/json"]];
    if(acceptHeader.length > 0) {
        headerParams[@"Accept"] = acceptHeader;
    }

    // response content type
    NSString *responseContentType = [[acceptHeader componentsSeparatedByString:@", "] firstObject] ?: @"";

    // request content type
    NSString *requestContentType = [self.apiClient.sanitizer selectHeaderContentType:@[]];

    // Authentication setting
    NSArray *authSettings = @[@"bearerAuth"];

    id bodyParam = nil;
    NSMutableDictionary *formParams = [[NSMutableDictionary alloc] init];
    NSMutableDictionary *localVarFiles = [[NSMutableDictionary alloc] init];

    return [self.apiClient requestWithPath: resourcePath
                                    method: @"GET"
                                pathParams: pathParams
                               queryParams: queryParams
                                formParams: formParams
                                     files: localVarFiles
                                      body: bodyParam
                              headerParams: headerParams
                              authSettings: authSettings
                        requestContentType: requestContentType
                       responseContentType: responseContentType
                              responseType: @"NSObject*"
                           completionBlock: ^(id data, NSError *error) {
                                if(handler) {
                                    handler((NSObject*)data, error);
                                }
                            }];
}



@end
