#import "OAIPublicApi.h"
#import "OAIQueryParamCollection.h"
#import "OAIApiClient.h"


@interface OAIPublicApi ()

@property (nonatomic, strong, readwrite) NSMutableDictionary *mutableDefaultHeaders;

@end

@implementation OAIPublicApi

NSString* kOAIPublicApiErrorDomain = @"OAIPublicApiErrorDomain";
NSInteger kOAIPublicApiMissingParamErrorCode = 234513;

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
/// Authenticate
/// Retrieve an Oauth access token, to be used for authentication of 'private' requests.  Three methods of authentication are supported:  - <code>password</code> - using email and and password as when logging on to the website - <code>client_credentials</code> - using the access key and access secret that can be found on the API page on the website - <code>client_signature</code> - using the access key that can be found on the API page on the website and user generated signature. The signature is calculated using some fields provided in the request, using formula described here [Deribit signature credentials](#additional-authorization-method-deribit-signature-credentials) - <code>refresh_token</code> - using a refresh token that was received from an earlier invocation  The response will contain an access token, expiration period (number of seconds that the token is valid) and a refresh token that can be used to get a new set of tokens. 
///  @param grantType Method of authentication 
///
///  @param username Required for grant type `password` 
///
///  @param password Required for grant type `password` 
///
///  @param clientId Required for grant type `client_credentials` and `client_signature` 
///
///  @param clientSecret Required for grant type `client_credentials` 
///
///  @param refreshToken Required for grant type `refresh_token` 
///
///  @param timestamp Required for grant type `client_signature`, provides time when request has been generated 
///
///  @param signature Required for grant type `client_signature`; it's a cryptographic signature calculated over provided fields using user **secret key**. The signature should be calculated as an HMAC (Hash-based Message Authentication Code) with `SHA256` hash algorithm 
///
///  @param nonce Optional for grant type `client_signature`; delivers user generated initialization vector for the server token (optional)
///
///  @param state Will be passed back in the response (optional)
///
///  @param scope Describes type of the access for assigned token, possible values: `connection`, `session`, `session:name`, `trade:[read, read_write, none]`, `wallet:[read, read_write, none]`, `account:[read, read_write, none]`, `expires:NUMBER` (token will expire after `NUMBER` of seconds).</BR></BR> **NOTICE:** Depending on choosing an authentication method (```grant type```) some scopes could be narrowed by the server. e.g. when ```grant_type = client_credentials``` and ```scope = wallet:read_write``` it's modified by the server as ```scope = wallet:read``` (optional)
///
///  @returns NSObject*
///
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
    completionHandler: (void (^)(NSObject* output, NSError* error)) handler {
    // verify the required parameter 'grantType' is set
    if (grantType == nil) {
        NSParameterAssert(grantType);
        if(handler) {
            NSDictionary * userInfo = @{NSLocalizedDescriptionKey : [NSString stringWithFormat:NSLocalizedString(@"Missing required parameter '%@'", nil),@"grantType"] };
            NSError* error = [NSError errorWithDomain:kOAIPublicApiErrorDomain code:kOAIPublicApiMissingParamErrorCode userInfo:userInfo];
            handler(nil, error);
        }
        return nil;
    }

    // verify the required parameter 'username' is set
    if (username == nil) {
        NSParameterAssert(username);
        if(handler) {
            NSDictionary * userInfo = @{NSLocalizedDescriptionKey : [NSString stringWithFormat:NSLocalizedString(@"Missing required parameter '%@'", nil),@"username"] };
            NSError* error = [NSError errorWithDomain:kOAIPublicApiErrorDomain code:kOAIPublicApiMissingParamErrorCode userInfo:userInfo];
            handler(nil, error);
        }
        return nil;
    }

    // verify the required parameter 'password' is set
    if (password == nil) {
        NSParameterAssert(password);
        if(handler) {
            NSDictionary * userInfo = @{NSLocalizedDescriptionKey : [NSString stringWithFormat:NSLocalizedString(@"Missing required parameter '%@'", nil),@"password"] };
            NSError* error = [NSError errorWithDomain:kOAIPublicApiErrorDomain code:kOAIPublicApiMissingParamErrorCode userInfo:userInfo];
            handler(nil, error);
        }
        return nil;
    }

    // verify the required parameter 'clientId' is set
    if (clientId == nil) {
        NSParameterAssert(clientId);
        if(handler) {
            NSDictionary * userInfo = @{NSLocalizedDescriptionKey : [NSString stringWithFormat:NSLocalizedString(@"Missing required parameter '%@'", nil),@"clientId"] };
            NSError* error = [NSError errorWithDomain:kOAIPublicApiErrorDomain code:kOAIPublicApiMissingParamErrorCode userInfo:userInfo];
            handler(nil, error);
        }
        return nil;
    }

    // verify the required parameter 'clientSecret' is set
    if (clientSecret == nil) {
        NSParameterAssert(clientSecret);
        if(handler) {
            NSDictionary * userInfo = @{NSLocalizedDescriptionKey : [NSString stringWithFormat:NSLocalizedString(@"Missing required parameter '%@'", nil),@"clientSecret"] };
            NSError* error = [NSError errorWithDomain:kOAIPublicApiErrorDomain code:kOAIPublicApiMissingParamErrorCode userInfo:userInfo];
            handler(nil, error);
        }
        return nil;
    }

    // verify the required parameter 'refreshToken' is set
    if (refreshToken == nil) {
        NSParameterAssert(refreshToken);
        if(handler) {
            NSDictionary * userInfo = @{NSLocalizedDescriptionKey : [NSString stringWithFormat:NSLocalizedString(@"Missing required parameter '%@'", nil),@"refreshToken"] };
            NSError* error = [NSError errorWithDomain:kOAIPublicApiErrorDomain code:kOAIPublicApiMissingParamErrorCode userInfo:userInfo];
            handler(nil, error);
        }
        return nil;
    }

    // verify the required parameter 'timestamp' is set
    if (timestamp == nil) {
        NSParameterAssert(timestamp);
        if(handler) {
            NSDictionary * userInfo = @{NSLocalizedDescriptionKey : [NSString stringWithFormat:NSLocalizedString(@"Missing required parameter '%@'", nil),@"timestamp"] };
            NSError* error = [NSError errorWithDomain:kOAIPublicApiErrorDomain code:kOAIPublicApiMissingParamErrorCode userInfo:userInfo];
            handler(nil, error);
        }
        return nil;
    }

    // verify the required parameter 'signature' is set
    if (signature == nil) {
        NSParameterAssert(signature);
        if(handler) {
            NSDictionary * userInfo = @{NSLocalizedDescriptionKey : [NSString stringWithFormat:NSLocalizedString(@"Missing required parameter '%@'", nil),@"signature"] };
            NSError* error = [NSError errorWithDomain:kOAIPublicApiErrorDomain code:kOAIPublicApiMissingParamErrorCode userInfo:userInfo];
            handler(nil, error);
        }
        return nil;
    }

    NSMutableString* resourcePath = [NSMutableString stringWithFormat:@"/public/auth"];

    NSMutableDictionary *pathParams = [[NSMutableDictionary alloc] init];

    NSMutableDictionary* queryParams = [[NSMutableDictionary alloc] init];
    if (grantType != nil) {
        queryParams[@"grant_type"] = grantType;
    }
    if (username != nil) {
        queryParams[@"username"] = username;
    }
    if (password != nil) {
        queryParams[@"password"] = password;
    }
    if (clientId != nil) {
        queryParams[@"client_id"] = clientId;
    }
    if (clientSecret != nil) {
        queryParams[@"client_secret"] = clientSecret;
    }
    if (refreshToken != nil) {
        queryParams[@"refresh_token"] = refreshToken;
    }
    if (timestamp != nil) {
        queryParams[@"timestamp"] = timestamp;
    }
    if (signature != nil) {
        queryParams[@"signature"] = signature;
    }
    if (nonce != nil) {
        queryParams[@"nonce"] = nonce;
    }
    if (state != nil) {
        queryParams[@"state"] = state;
    }
    if (scope != nil) {
        queryParams[@"scope"] = scope;
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
/// Retrieves announcements from the last 30 days.
/// 
///  @returns NSObject*
///
-(NSURLSessionTask*) publicGetAnnouncementsGetWithCompletionHandler: 
    (void (^)(NSObject* output, NSError* error)) handler {
    NSMutableString* resourcePath = [NSMutableString stringWithFormat:@"/public/get_announcements"];

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
/// Retrieves the summary information such as open interest, 24h volume, etc. for all instruments for the currency (optionally filtered by kind).
/// 
///  @param currency The currency symbol 
///
///  @param kind Instrument kind, if not provided instruments of all kinds are considered (optional)
///
///  @returns NSObject*
///
-(NSURLSessionTask*) publicGetBookSummaryByCurrencyGetWithCurrency: (NSString*) currency
    kind: (NSString*) kind
    completionHandler: (void (^)(NSObject* output, NSError* error)) handler {
    // verify the required parameter 'currency' is set
    if (currency == nil) {
        NSParameterAssert(currency);
        if(handler) {
            NSDictionary * userInfo = @{NSLocalizedDescriptionKey : [NSString stringWithFormat:NSLocalizedString(@"Missing required parameter '%@'", nil),@"currency"] };
            NSError* error = [NSError errorWithDomain:kOAIPublicApiErrorDomain code:kOAIPublicApiMissingParamErrorCode userInfo:userInfo];
            handler(nil, error);
        }
        return nil;
    }

    NSMutableString* resourcePath = [NSMutableString stringWithFormat:@"/public/get_book_summary_by_currency"];

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
/// Retrieves the summary information such as open interest, 24h volume, etc. for a specific instrument.
/// 
///  @param instrumentName Instrument name 
///
///  @returns NSObject*
///
-(NSURLSessionTask*) publicGetBookSummaryByInstrumentGetWithInstrumentName: (NSString*) instrumentName
    completionHandler: (void (^)(NSObject* output, NSError* error)) handler {
    // verify the required parameter 'instrumentName' is set
    if (instrumentName == nil) {
        NSParameterAssert(instrumentName);
        if(handler) {
            NSDictionary * userInfo = @{NSLocalizedDescriptionKey : [NSString stringWithFormat:NSLocalizedString(@"Missing required parameter '%@'", nil),@"instrumentName"] };
            NSError* error = [NSError errorWithDomain:kOAIPublicApiErrorDomain code:kOAIPublicApiMissingParamErrorCode userInfo:userInfo];
            handler(nil, error);
        }
        return nil;
    }

    NSMutableString* resourcePath = [NSMutableString stringWithFormat:@"/public/get_book_summary_by_instrument"];

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
/// Retrieves contract size of provided instrument.
/// 
///  @param instrumentName Instrument name 
///
///  @returns NSObject*
///
-(NSURLSessionTask*) publicGetContractSizeGetWithInstrumentName: (NSString*) instrumentName
    completionHandler: (void (^)(NSObject* output, NSError* error)) handler {
    // verify the required parameter 'instrumentName' is set
    if (instrumentName == nil) {
        NSParameterAssert(instrumentName);
        if(handler) {
            NSDictionary * userInfo = @{NSLocalizedDescriptionKey : [NSString stringWithFormat:NSLocalizedString(@"Missing required parameter '%@'", nil),@"instrumentName"] };
            NSError* error = [NSError errorWithDomain:kOAIPublicApiErrorDomain code:kOAIPublicApiMissingParamErrorCode userInfo:userInfo];
            handler(nil, error);
        }
        return nil;
    }

    NSMutableString* resourcePath = [NSMutableString stringWithFormat:@"/public/get_contract_size"];

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
/// Retrieves all cryptocurrencies supported by the API.
/// 
///  @returns NSObject*
///
-(NSURLSessionTask*) publicGetCurrenciesGetWithCompletionHandler: 
    (void (^)(NSObject* output, NSError* error)) handler {
    NSMutableString* resourcePath = [NSMutableString stringWithFormat:@"/public/get_currencies"];

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
/// Retrieve the latest user trades that have occurred for PERPETUAL instruments in a specific currency symbol and within given time range.
/// 
///  @param instrumentName Instrument name 
///
///  @param length Specifies time period. `8h` - 8 hours, `24h` - 24 hours (optional)
///
///  @returns NSObject*
///
-(NSURLSessionTask*) publicGetFundingChartDataGetWithInstrumentName: (NSString*) instrumentName
    length: (NSString*) length
    completionHandler: (void (^)(NSObject* output, NSError* error)) handler {
    // verify the required parameter 'instrumentName' is set
    if (instrumentName == nil) {
        NSParameterAssert(instrumentName);
        if(handler) {
            NSDictionary * userInfo = @{NSLocalizedDescriptionKey : [NSString stringWithFormat:NSLocalizedString(@"Missing required parameter '%@'", nil),@"instrumentName"] };
            NSError* error = [NSError errorWithDomain:kOAIPublicApiErrorDomain code:kOAIPublicApiMissingParamErrorCode userInfo:userInfo];
            handler(nil, error);
        }
        return nil;
    }

    NSMutableString* resourcePath = [NSMutableString stringWithFormat:@"/public/get_funding_chart_data"];

    NSMutableDictionary *pathParams = [[NSMutableDictionary alloc] init];

    NSMutableDictionary* queryParams = [[NSMutableDictionary alloc] init];
    if (instrumentName != nil) {
        queryParams[@"instrument_name"] = instrumentName;
    }
    if (length != nil) {
        queryParams[@"length"] = length;
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
/// Provides information about historical volatility for given cryptocurrency.
/// 
///  @param currency The currency symbol 
///
///  @returns NSObject*
///
-(NSURLSessionTask*) publicGetHistoricalVolatilityGetWithCurrency: (NSString*) currency
    completionHandler: (void (^)(NSObject* output, NSError* error)) handler {
    // verify the required parameter 'currency' is set
    if (currency == nil) {
        NSParameterAssert(currency);
        if(handler) {
            NSDictionary * userInfo = @{NSLocalizedDescriptionKey : [NSString stringWithFormat:NSLocalizedString(@"Missing required parameter '%@'", nil),@"currency"] };
            NSError* error = [NSError errorWithDomain:kOAIPublicApiErrorDomain code:kOAIPublicApiMissingParamErrorCode userInfo:userInfo];
            handler(nil, error);
        }
        return nil;
    }

    NSMutableString* resourcePath = [NSMutableString stringWithFormat:@"/public/get_historical_volatility"];

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
/// Retrieves the current index price for the instruments, for the selected currency.
/// 
///  @param currency The currency symbol 
///
///  @returns NSObject*
///
-(NSURLSessionTask*) publicGetIndexGetWithCurrency: (NSString*) currency
    completionHandler: (void (^)(NSObject* output, NSError* error)) handler {
    // verify the required parameter 'currency' is set
    if (currency == nil) {
        NSParameterAssert(currency);
        if(handler) {
            NSDictionary * userInfo = @{NSLocalizedDescriptionKey : [NSString stringWithFormat:NSLocalizedString(@"Missing required parameter '%@'", nil),@"currency"] };
            NSError* error = [NSError errorWithDomain:kOAIPublicApiErrorDomain code:kOAIPublicApiMissingParamErrorCode userInfo:userInfo];
            handler(nil, error);
        }
        return nil;
    }

    NSMutableString* resourcePath = [NSMutableString stringWithFormat:@"/public/get_index"];

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
/// Retrieves available trading instruments. This method can be used to see which instruments are available for trading, or which instruments have existed historically.
/// 
///  @param currency The currency symbol 
///
///  @param kind Instrument kind, if not provided instruments of all kinds are considered (optional)
///
///  @param expired Set to true to show expired instruments instead of active ones. (optional, default to @(NO))
///
///  @returns NSObject*
///
-(NSURLSessionTask*) publicGetInstrumentsGetWithCurrency: (NSString*) currency
    kind: (NSString*) kind
    expired: (NSNumber*) expired
    completionHandler: (void (^)(NSObject* output, NSError* error)) handler {
    // verify the required parameter 'currency' is set
    if (currency == nil) {
        NSParameterAssert(currency);
        if(handler) {
            NSDictionary * userInfo = @{NSLocalizedDescriptionKey : [NSString stringWithFormat:NSLocalizedString(@"Missing required parameter '%@'", nil),@"currency"] };
            NSError* error = [NSError errorWithDomain:kOAIPublicApiErrorDomain code:kOAIPublicApiMissingParamErrorCode userInfo:userInfo];
            handler(nil, error);
        }
        return nil;
    }

    NSMutableString* resourcePath = [NSMutableString stringWithFormat:@"/public/get_instruments"];

    NSMutableDictionary *pathParams = [[NSMutableDictionary alloc] init];

    NSMutableDictionary* queryParams = [[NSMutableDictionary alloc] init];
    if (currency != nil) {
        queryParams[@"currency"] = currency;
    }
    if (kind != nil) {
        queryParams[@"kind"] = kind;
    }
    if (expired != nil) {
        queryParams[@"expired"] = [expired isEqual:@(YES)] ? @"true" : @"false";
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
/// Retrieves historical settlement, delivery and bankruptcy events coming from all instruments within given currency.
/// 
///  @param currency The currency symbol 
///
///  @param type Settlement type (optional)
///
///  @param count Number of requested items, default - `20` (optional)
///
///  @param continuation Continuation token for pagination (optional)
///
///  @param searchStartTimestamp The latest timestamp to return result for (optional)
///
///  @returns NSObject*
///
-(NSURLSessionTask*) publicGetLastSettlementsByCurrencyGetWithCurrency: (NSString*) currency
    type: (NSString*) type
    count: (NSNumber*) count
    continuation: (NSString*) continuation
    searchStartTimestamp: (NSNumber*) searchStartTimestamp
    completionHandler: (void (^)(NSObject* output, NSError* error)) handler {
    // verify the required parameter 'currency' is set
    if (currency == nil) {
        NSParameterAssert(currency);
        if(handler) {
            NSDictionary * userInfo = @{NSLocalizedDescriptionKey : [NSString stringWithFormat:NSLocalizedString(@"Missing required parameter '%@'", nil),@"currency"] };
            NSError* error = [NSError errorWithDomain:kOAIPublicApiErrorDomain code:kOAIPublicApiMissingParamErrorCode userInfo:userInfo];
            handler(nil, error);
        }
        return nil;
    }

    NSMutableString* resourcePath = [NSMutableString stringWithFormat:@"/public/get_last_settlements_by_currency"];

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
    if (continuation != nil) {
        queryParams[@"continuation"] = continuation;
    }
    if (searchStartTimestamp != nil) {
        queryParams[@"search_start_timestamp"] = searchStartTimestamp;
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
/// Retrieves historical public settlement, delivery and bankruptcy events filtered by instrument name.
/// 
///  @param instrumentName Instrument name 
///
///  @param type Settlement type (optional)
///
///  @param count Number of requested items, default - `20` (optional)
///
///  @param continuation Continuation token for pagination (optional)
///
///  @param searchStartTimestamp The latest timestamp to return result for (optional)
///
///  @returns NSObject*
///
-(NSURLSessionTask*) publicGetLastSettlementsByInstrumentGetWithInstrumentName: (NSString*) instrumentName
    type: (NSString*) type
    count: (NSNumber*) count
    continuation: (NSString*) continuation
    searchStartTimestamp: (NSNumber*) searchStartTimestamp
    completionHandler: (void (^)(NSObject* output, NSError* error)) handler {
    // verify the required parameter 'instrumentName' is set
    if (instrumentName == nil) {
        NSParameterAssert(instrumentName);
        if(handler) {
            NSDictionary * userInfo = @{NSLocalizedDescriptionKey : [NSString stringWithFormat:NSLocalizedString(@"Missing required parameter '%@'", nil),@"instrumentName"] };
            NSError* error = [NSError errorWithDomain:kOAIPublicApiErrorDomain code:kOAIPublicApiMissingParamErrorCode userInfo:userInfo];
            handler(nil, error);
        }
        return nil;
    }

    NSMutableString* resourcePath = [NSMutableString stringWithFormat:@"/public/get_last_settlements_by_instrument"];

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
    if (continuation != nil) {
        queryParams[@"continuation"] = continuation;
    }
    if (searchStartTimestamp != nil) {
        queryParams[@"search_start_timestamp"] = searchStartTimestamp;
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
/// Retrieve the latest trades that have occurred for instruments in a specific currency symbol and within given time range.
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
-(NSURLSessionTask*) publicGetLastTradesByCurrencyAndTimeGetWithCurrency: (NSString*) currency
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
            NSError* error = [NSError errorWithDomain:kOAIPublicApiErrorDomain code:kOAIPublicApiMissingParamErrorCode userInfo:userInfo];
            handler(nil, error);
        }
        return nil;
    }

    // verify the required parameter 'startTimestamp' is set
    if (startTimestamp == nil) {
        NSParameterAssert(startTimestamp);
        if(handler) {
            NSDictionary * userInfo = @{NSLocalizedDescriptionKey : [NSString stringWithFormat:NSLocalizedString(@"Missing required parameter '%@'", nil),@"startTimestamp"] };
            NSError* error = [NSError errorWithDomain:kOAIPublicApiErrorDomain code:kOAIPublicApiMissingParamErrorCode userInfo:userInfo];
            handler(nil, error);
        }
        return nil;
    }

    // verify the required parameter 'endTimestamp' is set
    if (endTimestamp == nil) {
        NSParameterAssert(endTimestamp);
        if(handler) {
            NSDictionary * userInfo = @{NSLocalizedDescriptionKey : [NSString stringWithFormat:NSLocalizedString(@"Missing required parameter '%@'", nil),@"endTimestamp"] };
            NSError* error = [NSError errorWithDomain:kOAIPublicApiErrorDomain code:kOAIPublicApiMissingParamErrorCode userInfo:userInfo];
            handler(nil, error);
        }
        return nil;
    }

    NSMutableString* resourcePath = [NSMutableString stringWithFormat:@"/public/get_last_trades_by_currency_and_time"];

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
/// Retrieve the latest trades that have occurred for instruments in a specific currency symbol.
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
-(NSURLSessionTask*) publicGetLastTradesByCurrencyGetWithCurrency: (NSString*) currency
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
            NSError* error = [NSError errorWithDomain:kOAIPublicApiErrorDomain code:kOAIPublicApiMissingParamErrorCode userInfo:userInfo];
            handler(nil, error);
        }
        return nil;
    }

    NSMutableString* resourcePath = [NSMutableString stringWithFormat:@"/public/get_last_trades_by_currency"];

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
/// Retrieve the latest trades that have occurred for a specific instrument and within given time range.
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
-(NSURLSessionTask*) publicGetLastTradesByInstrumentAndTimeGetWithInstrumentName: (NSString*) instrumentName
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
            NSError* error = [NSError errorWithDomain:kOAIPublicApiErrorDomain code:kOAIPublicApiMissingParamErrorCode userInfo:userInfo];
            handler(nil, error);
        }
        return nil;
    }

    // verify the required parameter 'startTimestamp' is set
    if (startTimestamp == nil) {
        NSParameterAssert(startTimestamp);
        if(handler) {
            NSDictionary * userInfo = @{NSLocalizedDescriptionKey : [NSString stringWithFormat:NSLocalizedString(@"Missing required parameter '%@'", nil),@"startTimestamp"] };
            NSError* error = [NSError errorWithDomain:kOAIPublicApiErrorDomain code:kOAIPublicApiMissingParamErrorCode userInfo:userInfo];
            handler(nil, error);
        }
        return nil;
    }

    // verify the required parameter 'endTimestamp' is set
    if (endTimestamp == nil) {
        NSParameterAssert(endTimestamp);
        if(handler) {
            NSDictionary * userInfo = @{NSLocalizedDescriptionKey : [NSString stringWithFormat:NSLocalizedString(@"Missing required parameter '%@'", nil),@"endTimestamp"] };
            NSError* error = [NSError errorWithDomain:kOAIPublicApiErrorDomain code:kOAIPublicApiMissingParamErrorCode userInfo:userInfo];
            handler(nil, error);
        }
        return nil;
    }

    NSMutableString* resourcePath = [NSMutableString stringWithFormat:@"/public/get_last_trades_by_instrument_and_time"];

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
/// Retrieve the latest trades that have occurred for a specific instrument.
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
-(NSURLSessionTask*) publicGetLastTradesByInstrumentGetWithInstrumentName: (NSString*) instrumentName
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
            NSError* error = [NSError errorWithDomain:kOAIPublicApiErrorDomain code:kOAIPublicApiMissingParamErrorCode userInfo:userInfo];
            handler(nil, error);
        }
        return nil;
    }

    NSMutableString* resourcePath = [NSMutableString stringWithFormat:@"/public/get_last_trades_by_instrument"];

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
/// Retrieves the order book, along with other market values for a given instrument.
/// 
///  @param instrumentName The instrument name for which to retrieve the order book, see [`getinstruments`](#getinstruments) to obtain instrument names. 
///
///  @param depth The number of entries to return for bids and asks. (optional)
///
///  @returns NSObject*
///
-(NSURLSessionTask*) publicGetOrderBookGetWithInstrumentName: (NSString*) instrumentName
    depth: (NSNumber*) depth
    completionHandler: (void (^)(NSObject* output, NSError* error)) handler {
    // verify the required parameter 'instrumentName' is set
    if (instrumentName == nil) {
        NSParameterAssert(instrumentName);
        if(handler) {
            NSDictionary * userInfo = @{NSLocalizedDescriptionKey : [NSString stringWithFormat:NSLocalizedString(@"Missing required parameter '%@'", nil),@"instrumentName"] };
            NSError* error = [NSError errorWithDomain:kOAIPublicApiErrorDomain code:kOAIPublicApiMissingParamErrorCode userInfo:userInfo];
            handler(nil, error);
        }
        return nil;
    }

    NSMutableString* resourcePath = [NSMutableString stringWithFormat:@"/public/get_order_book"];

    NSMutableDictionary *pathParams = [[NSMutableDictionary alloc] init];

    NSMutableDictionary* queryParams = [[NSMutableDictionary alloc] init];
    if (instrumentName != nil) {
        queryParams[@"instrument_name"] = instrumentName;
    }
    if (depth != nil) {
        queryParams[@"depth"] = depth;
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
/// Retrieves the current time (in milliseconds). This API endpoint can be used to check the clock skew between your software and Deribit's systems.
/// 
///  @returns NSObject*
///
-(NSURLSessionTask*) publicGetTimeGetWithCompletionHandler: 
    (void (^)(NSObject* output, NSError* error)) handler {
    NSMutableString* resourcePath = [NSMutableString stringWithFormat:@"/public/get_time"];

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
/// Retrieves aggregated 24h trade volumes for different instrument types and currencies.
/// 
///  @returns NSObject*
///
-(NSURLSessionTask*) publicGetTradeVolumesGetWithCompletionHandler: 
    (void (^)(NSObject* output, NSError* error)) handler {
    NSMutableString* resourcePath = [NSMutableString stringWithFormat:@"/public/get_trade_volumes"];

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
/// Publicly available market data used to generate a TradingView candle chart.
/// 
///  @param instrumentName Instrument name 
///
///  @param startTimestamp The earliest timestamp to return result for 
///
///  @param endTimestamp The most recent timestamp to return result for 
///
///  @returns NSObject*
///
-(NSURLSessionTask*) publicGetTradingviewChartDataGetWithInstrumentName: (NSString*) instrumentName
    startTimestamp: (NSNumber*) startTimestamp
    endTimestamp: (NSNumber*) endTimestamp
    completionHandler: (void (^)(NSObject* output, NSError* error)) handler {
    // verify the required parameter 'instrumentName' is set
    if (instrumentName == nil) {
        NSParameterAssert(instrumentName);
        if(handler) {
            NSDictionary * userInfo = @{NSLocalizedDescriptionKey : [NSString stringWithFormat:NSLocalizedString(@"Missing required parameter '%@'", nil),@"instrumentName"] };
            NSError* error = [NSError errorWithDomain:kOAIPublicApiErrorDomain code:kOAIPublicApiMissingParamErrorCode userInfo:userInfo];
            handler(nil, error);
        }
        return nil;
    }

    // verify the required parameter 'startTimestamp' is set
    if (startTimestamp == nil) {
        NSParameterAssert(startTimestamp);
        if(handler) {
            NSDictionary * userInfo = @{NSLocalizedDescriptionKey : [NSString stringWithFormat:NSLocalizedString(@"Missing required parameter '%@'", nil),@"startTimestamp"] };
            NSError* error = [NSError errorWithDomain:kOAIPublicApiErrorDomain code:kOAIPublicApiMissingParamErrorCode userInfo:userInfo];
            handler(nil, error);
        }
        return nil;
    }

    // verify the required parameter 'endTimestamp' is set
    if (endTimestamp == nil) {
        NSParameterAssert(endTimestamp);
        if(handler) {
            NSDictionary * userInfo = @{NSLocalizedDescriptionKey : [NSString stringWithFormat:NSLocalizedString(@"Missing required parameter '%@'", nil),@"endTimestamp"] };
            NSError* error = [NSError errorWithDomain:kOAIPublicApiErrorDomain code:kOAIPublicApiMissingParamErrorCode userInfo:userInfo];
            handler(nil, error);
        }
        return nil;
    }

    NSMutableString* resourcePath = [NSMutableString stringWithFormat:@"/public/get_tradingview_chart_data"];

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
/// Tests the connection to the API server, and returns its version. You can use this to make sure the API is reachable, and matches the expected version.
/// 
///  @param expectedResult The value \"exception\" will trigger an error response. This may be useful for testing wrapper libraries. (optional)
///
///  @returns NSObject*
///
-(NSURLSessionTask*) publicTestGetWithExpectedResult: (NSString*) expectedResult
    completionHandler: (void (^)(NSObject* output, NSError* error)) handler {
    NSMutableString* resourcePath = [NSMutableString stringWithFormat:@"/public/test"];

    NSMutableDictionary *pathParams = [[NSMutableDictionary alloc] init];

    NSMutableDictionary* queryParams = [[NSMutableDictionary alloc] init];
    if (expectedResult != nil) {
        queryParams[@"expected_result"] = expectedResult;
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
/// Get ticker for an instrument.
/// 
///  @param instrumentName Instrument name 
///
///  @returns NSObject*
///
-(NSURLSessionTask*) publicTickerGetWithInstrumentName: (NSString*) instrumentName
    completionHandler: (void (^)(NSObject* output, NSError* error)) handler {
    // verify the required parameter 'instrumentName' is set
    if (instrumentName == nil) {
        NSParameterAssert(instrumentName);
        if(handler) {
            NSDictionary * userInfo = @{NSLocalizedDescriptionKey : [NSString stringWithFormat:NSLocalizedString(@"Missing required parameter '%@'", nil),@"instrumentName"] };
            NSError* error = [NSError errorWithDomain:kOAIPublicApiErrorDomain code:kOAIPublicApiMissingParamErrorCode userInfo:userInfo];
            handler(nil, error);
        }
        return nil;
    }

    NSMutableString* resourcePath = [NSMutableString stringWithFormat:@"/public/ticker"];

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
/// Method used to introduce the client software connected to Deribit platform over websocket. Provided data may have an impact on the maintained connection and will be collected for internal statistical purposes. In response, Deribit will also introduce itself.
/// 
///  @param field Name of the field to be validated, examples: postal_code, date_of_birth 
///
///  @param value Value to be checked 
///
///  @param value2 Additional value to be compared with (optional)
///
///  @returns NSObject*
///
-(NSURLSessionTask*) publicValidateFieldGetWithField: (NSString*) field
    value: (NSString*) value
    value2: (NSString*) value2
    completionHandler: (void (^)(NSObject* output, NSError* error)) handler {
    // verify the required parameter 'field' is set
    if (field == nil) {
        NSParameterAssert(field);
        if(handler) {
            NSDictionary * userInfo = @{NSLocalizedDescriptionKey : [NSString stringWithFormat:NSLocalizedString(@"Missing required parameter '%@'", nil),@"field"] };
            NSError* error = [NSError errorWithDomain:kOAIPublicApiErrorDomain code:kOAIPublicApiMissingParamErrorCode userInfo:userInfo];
            handler(nil, error);
        }
        return nil;
    }

    // verify the required parameter 'value' is set
    if (value == nil) {
        NSParameterAssert(value);
        if(handler) {
            NSDictionary * userInfo = @{NSLocalizedDescriptionKey : [NSString stringWithFormat:NSLocalizedString(@"Missing required parameter '%@'", nil),@"value"] };
            NSError* error = [NSError errorWithDomain:kOAIPublicApiErrorDomain code:kOAIPublicApiMissingParamErrorCode userInfo:userInfo];
            handler(nil, error);
        }
        return nil;
    }

    NSMutableString* resourcePath = [NSMutableString stringWithFormat:@"/public/validate_field"];

    NSMutableDictionary *pathParams = [[NSMutableDictionary alloc] init];

    NSMutableDictionary* queryParams = [[NSMutableDictionary alloc] init];
    if (field != nil) {
        queryParams[@"field"] = field;
    }
    if (value != nil) {
        queryParams[@"value"] = value;
    }
    if (value2 != nil) {
        queryParams[@"value2"] = value2;
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
