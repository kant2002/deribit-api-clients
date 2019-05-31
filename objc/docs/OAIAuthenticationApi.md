# OAIAuthenticationApi

All URIs are relative to *https://www.deribit.com/api/v2*

Method | HTTP request | Description
------------- | ------------- | -------------
[**publicAuthGet**](OAIAuthenticationApi.md#publicauthget) | **GET** /public/auth | Authenticate


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

OAIAuthenticationApi*apiInstance = [[OAIAuthenticationApi alloc] init];

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
                            NSLog(@"Error calling OAIAuthenticationApi->publicAuthGet: %@", error);
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

