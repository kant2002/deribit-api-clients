#import "OAIAuthenticationApi.h"
#import "OAIQueryParamCollection.h"
#import "OAIApiClient.h"


@interface OAIAuthenticationApi ()

@property (nonatomic, strong, readwrite) NSMutableDictionary *mutableDefaultHeaders;

@end

@implementation OAIAuthenticationApi

NSString* kOAIAuthenticationApiErrorDomain = @"OAIAuthenticationApiErrorDomain";
NSInteger kOAIAuthenticationApiMissingParamErrorCode = 234513;

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
            NSError* error = [NSError errorWithDomain:kOAIAuthenticationApiErrorDomain code:kOAIAuthenticationApiMissingParamErrorCode userInfo:userInfo];
            handler(nil, error);
        }
        return nil;
    }

    // verify the required parameter 'username' is set
    if (username == nil) {
        NSParameterAssert(username);
        if(handler) {
            NSDictionary * userInfo = @{NSLocalizedDescriptionKey : [NSString stringWithFormat:NSLocalizedString(@"Missing required parameter '%@'", nil),@"username"] };
            NSError* error = [NSError errorWithDomain:kOAIAuthenticationApiErrorDomain code:kOAIAuthenticationApiMissingParamErrorCode userInfo:userInfo];
            handler(nil, error);
        }
        return nil;
    }

    // verify the required parameter 'password' is set
    if (password == nil) {
        NSParameterAssert(password);
        if(handler) {
            NSDictionary * userInfo = @{NSLocalizedDescriptionKey : [NSString stringWithFormat:NSLocalizedString(@"Missing required parameter '%@'", nil),@"password"] };
            NSError* error = [NSError errorWithDomain:kOAIAuthenticationApiErrorDomain code:kOAIAuthenticationApiMissingParamErrorCode userInfo:userInfo];
            handler(nil, error);
        }
        return nil;
    }

    // verify the required parameter 'clientId' is set
    if (clientId == nil) {
        NSParameterAssert(clientId);
        if(handler) {
            NSDictionary * userInfo = @{NSLocalizedDescriptionKey : [NSString stringWithFormat:NSLocalizedString(@"Missing required parameter '%@'", nil),@"clientId"] };
            NSError* error = [NSError errorWithDomain:kOAIAuthenticationApiErrorDomain code:kOAIAuthenticationApiMissingParamErrorCode userInfo:userInfo];
            handler(nil, error);
        }
        return nil;
    }

    // verify the required parameter 'clientSecret' is set
    if (clientSecret == nil) {
        NSParameterAssert(clientSecret);
        if(handler) {
            NSDictionary * userInfo = @{NSLocalizedDescriptionKey : [NSString stringWithFormat:NSLocalizedString(@"Missing required parameter '%@'", nil),@"clientSecret"] };
            NSError* error = [NSError errorWithDomain:kOAIAuthenticationApiErrorDomain code:kOAIAuthenticationApiMissingParamErrorCode userInfo:userInfo];
            handler(nil, error);
        }
        return nil;
    }

    // verify the required parameter 'refreshToken' is set
    if (refreshToken == nil) {
        NSParameterAssert(refreshToken);
        if(handler) {
            NSDictionary * userInfo = @{NSLocalizedDescriptionKey : [NSString stringWithFormat:NSLocalizedString(@"Missing required parameter '%@'", nil),@"refreshToken"] };
            NSError* error = [NSError errorWithDomain:kOAIAuthenticationApiErrorDomain code:kOAIAuthenticationApiMissingParamErrorCode userInfo:userInfo];
            handler(nil, error);
        }
        return nil;
    }

    // verify the required parameter 'timestamp' is set
    if (timestamp == nil) {
        NSParameterAssert(timestamp);
        if(handler) {
            NSDictionary * userInfo = @{NSLocalizedDescriptionKey : [NSString stringWithFormat:NSLocalizedString(@"Missing required parameter '%@'", nil),@"timestamp"] };
            NSError* error = [NSError errorWithDomain:kOAIAuthenticationApiErrorDomain code:kOAIAuthenticationApiMissingParamErrorCode userInfo:userInfo];
            handler(nil, error);
        }
        return nil;
    }

    // verify the required parameter 'signature' is set
    if (signature == nil) {
        NSParameterAssert(signature);
        if(handler) {
            NSDictionary * userInfo = @{NSLocalizedDescriptionKey : [NSString stringWithFormat:NSLocalizedString(@"Missing required parameter '%@'", nil),@"signature"] };
            NSError* error = [NSError errorWithDomain:kOAIAuthenticationApiErrorDomain code:kOAIAuthenticationApiMissingParamErrorCode userInfo:userInfo];
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



@end
