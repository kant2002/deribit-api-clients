# OAIWebsocketOnlyApi

All URIs are relative to *https://www.deribit.com/api/v2*

Method | HTTP request | Description
------------- | ------------- | -------------
[**privateDisableCancelOnDisconnectGet**](OAIWebsocketOnlyApi.md#privatedisablecancelondisconnectget) | **GET** /private/disable_cancel_on_disconnect | Disable Cancel On Disconnect for the connection. This does not change the default account setting.
[**privateEnableCancelOnDisconnectGet**](OAIWebsocketOnlyApi.md#privateenablecancelondisconnectget) | **GET** /private/enable_cancel_on_disconnect | Enable Cancel On Disconnect for the connection. This does not change the default account setting.
[**privateLogoutGet**](OAIWebsocketOnlyApi.md#privatelogoutget) | **GET** /private/logout | Gracefully close websocket connection, when COD (Cancel On Disconnect) is enabled orders are not cancelled
[**privateSubscribeGet**](OAIWebsocketOnlyApi.md#privatesubscribeget) | **GET** /private/subscribe | Subscribe to one or more channels.
[**privateUnsubscribeGet**](OAIWebsocketOnlyApi.md#privateunsubscribeget) | **GET** /private/unsubscribe | Unsubscribe from one or more channels.
[**publicDisableHeartbeatGet**](OAIWebsocketOnlyApi.md#publicdisableheartbeatget) | **GET** /public/disable_heartbeat | Stop sending heartbeat messages.
[**publicHelloGet**](OAIWebsocketOnlyApi.md#publichelloget) | **GET** /public/hello | Method used to introduce the client software connected to Deribit platform over websocket. Provided data may have an impact on the maintained connection and will be collected for internal statistical purposes. In response, Deribit will also introduce itself.
[**publicSetHeartbeatGet**](OAIWebsocketOnlyApi.md#publicsetheartbeatget) | **GET** /public/set_heartbeat | Signals the Websocket connection to send and request heartbeats. Heartbeats can be used to detect stale connections. When heartbeats have been set up, the API server will send &#x60;heartbeat&#x60; messages and &#x60;test_request&#x60; messages. Your software should respond to &#x60;test_request&#x60; messages by sending a &#x60;/api/v2/public/test&#x60; request. If your software fails to do so, the API server will immediately close the connection. If your account is configured to cancel on disconnect, any orders opened over the connection will be cancelled.
[**publicSubscribeGet**](OAIWebsocketOnlyApi.md#publicsubscribeget) | **GET** /public/subscribe | Subscribe to one or more channels.
[**publicUnsubscribeGet**](OAIWebsocketOnlyApi.md#publicunsubscribeget) | **GET** /public/unsubscribe | Unsubscribe from one or more channels.


# **privateDisableCancelOnDisconnectGet**
```objc
-(NSURLSessionTask*) privateDisableCancelOnDisconnectGetWithCompletionHandler: 
        (void (^)(NSObject* output, NSError* error)) handler;
```

Disable Cancel On Disconnect for the connection. This does not change the default account setting.

### Example 
```objc
OAIDefaultConfiguration *apiConfig = [OAIDefaultConfiguration sharedConfig];
// Configure HTTP basic authorization (authentication scheme: bearerAuth)
[apiConfig setUsername:@"YOUR_USERNAME"];
[apiConfig setPassword:@"YOUR_PASSWORD"];



OAIWebsocketOnlyApi*apiInstance = [[OAIWebsocketOnlyApi alloc] init];

// Disable Cancel On Disconnect for the connection. This does not change the default account setting.
[apiInstance privateDisableCancelOnDisconnectGetWithCompletionHandler: 
          ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAIWebsocketOnlyApi->privateDisableCancelOnDisconnectGet: %@", error);
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

# **privateEnableCancelOnDisconnectGet**
```objc
-(NSURLSessionTask*) privateEnableCancelOnDisconnectGetWithCompletionHandler: 
        (void (^)(NSObject* output, NSError* error)) handler;
```

Enable Cancel On Disconnect for the connection. This does not change the default account setting.

### Example 
```objc
OAIDefaultConfiguration *apiConfig = [OAIDefaultConfiguration sharedConfig];
// Configure HTTP basic authorization (authentication scheme: bearerAuth)
[apiConfig setUsername:@"YOUR_USERNAME"];
[apiConfig setPassword:@"YOUR_PASSWORD"];



OAIWebsocketOnlyApi*apiInstance = [[OAIWebsocketOnlyApi alloc] init];

// Enable Cancel On Disconnect for the connection. This does not change the default account setting.
[apiInstance privateEnableCancelOnDisconnectGetWithCompletionHandler: 
          ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAIWebsocketOnlyApi->privateEnableCancelOnDisconnectGet: %@", error);
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

# **privateLogoutGet**
```objc
-(NSURLSessionTask*) privateLogoutGetWithCompletionHandler: 
        (void (^)(NSError* error)) handler;
```

Gracefully close websocket connection, when COD (Cancel On Disconnect) is enabled orders are not cancelled

### Example 
```objc
OAIDefaultConfiguration *apiConfig = [OAIDefaultConfiguration sharedConfig];
// Configure HTTP basic authorization (authentication scheme: bearerAuth)
[apiConfig setUsername:@"YOUR_USERNAME"];
[apiConfig setPassword:@"YOUR_PASSWORD"];



OAIWebsocketOnlyApi*apiInstance = [[OAIWebsocketOnlyApi alloc] init];

// Gracefully close websocket connection, when COD (Cancel On Disconnect) is enabled orders are not cancelled
[apiInstance privateLogoutGetWithCompletionHandler: 
          ^(NSError* error) {
                        if (error) {
                            NSLog(@"Error calling OAIWebsocketOnlyApi->privateLogoutGet: %@", error);
                        }
                    }];
```

### Parameters
This endpoint does not need any parameter.

### Return type

void (empty response body)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateSubscribeGet**
```objc
-(NSURLSessionTask*) privateSubscribeGetWithChannels: (NSArray<NSString*>*) channels
        completionHandler: (void (^)(NSObject* output, NSError* error)) handler;
```

Subscribe to one or more channels.

Subscribe to one or more channels.  The name of the channel determines what information will be provided, and in what form. 

### Example 
```objc
OAIDefaultConfiguration *apiConfig = [OAIDefaultConfiguration sharedConfig];
// Configure HTTP basic authorization (authentication scheme: bearerAuth)
[apiConfig setUsername:@"YOUR_USERNAME"];
[apiConfig setPassword:@"YOUR_PASSWORD"];


NSArray<NSString*>* channels = @[@"channels_example"]; // A list of channels to subscribe to.

OAIWebsocketOnlyApi*apiInstance = [[OAIWebsocketOnlyApi alloc] init];

// Subscribe to one or more channels.
[apiInstance privateSubscribeGetWithChannels:channels
          completionHandler: ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAIWebsocketOnlyApi->privateSubscribeGet: %@", error);
                        }
                    }];
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **channels** | [**NSArray&lt;NSString*&gt;***](NSString*.md)| A list of channels to subscribe to. | 

### Return type

**NSObject***

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateUnsubscribeGet**
```objc
-(NSURLSessionTask*) privateUnsubscribeGetWithChannels: (NSArray<NSString*>*) channels
        completionHandler: (void (^)(NSObject* output, NSError* error)) handler;
```

Unsubscribe from one or more channels.

### Example 
```objc
OAIDefaultConfiguration *apiConfig = [OAIDefaultConfiguration sharedConfig];
// Configure HTTP basic authorization (authentication scheme: bearerAuth)
[apiConfig setUsername:@"YOUR_USERNAME"];
[apiConfig setPassword:@"YOUR_PASSWORD"];


NSArray<NSString*>* channels = @[@"channels_example"]; // A list of channels to unsubscribe from.

OAIWebsocketOnlyApi*apiInstance = [[OAIWebsocketOnlyApi alloc] init];

// Unsubscribe from one or more channels.
[apiInstance privateUnsubscribeGetWithChannels:channels
          completionHandler: ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAIWebsocketOnlyApi->privateUnsubscribeGet: %@", error);
                        }
                    }];
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **channels** | [**NSArray&lt;NSString*&gt;***](NSString*.md)| A list of channels to unsubscribe from. | 

### Return type

**NSObject***

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **publicDisableHeartbeatGet**
```objc
-(NSURLSessionTask*) publicDisableHeartbeatGetWithCompletionHandler: 
        (void (^)(NSObject* output, NSError* error)) handler;
```

Stop sending heartbeat messages.

### Example 
```objc
OAIDefaultConfiguration *apiConfig = [OAIDefaultConfiguration sharedConfig];
// Configure HTTP basic authorization (authentication scheme: bearerAuth)
[apiConfig setUsername:@"YOUR_USERNAME"];
[apiConfig setPassword:@"YOUR_PASSWORD"];



OAIWebsocketOnlyApi*apiInstance = [[OAIWebsocketOnlyApi alloc] init];

// Stop sending heartbeat messages.
[apiInstance publicDisableHeartbeatGetWithCompletionHandler: 
          ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAIWebsocketOnlyApi->publicDisableHeartbeatGet: %@", error);
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

# **publicHelloGet**
```objc
-(NSURLSessionTask*) publicHelloGetWithClientName: (NSString*) clientName
    clientVersion: (NSString*) clientVersion
        completionHandler: (void (^)(NSObject* output, NSError* error)) handler;
```

Method used to introduce the client software connected to Deribit platform over websocket. Provided data may have an impact on the maintained connection and will be collected for internal statistical purposes. In response, Deribit will also introduce itself.

### Example 
```objc
OAIDefaultConfiguration *apiConfig = [OAIDefaultConfiguration sharedConfig];
// Configure HTTP basic authorization (authentication scheme: bearerAuth)
[apiConfig setUsername:@"YOUR_USERNAME"];
[apiConfig setPassword:@"YOUR_PASSWORD"];


NSString* clientName = My Trading Software; // Client software name
NSString* clientVersion = 1.0.2; // Client software version

OAIWebsocketOnlyApi*apiInstance = [[OAIWebsocketOnlyApi alloc] init];

// Method used to introduce the client software connected to Deribit platform over websocket. Provided data may have an impact on the maintained connection and will be collected for internal statistical purposes. In response, Deribit will also introduce itself.
[apiInstance publicHelloGetWithClientName:clientName
              clientVersion:clientVersion
          completionHandler: ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAIWebsocketOnlyApi->publicHelloGet: %@", error);
                        }
                    }];
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **clientName** | **NSString***| Client software name | 
 **clientVersion** | **NSString***| Client software version | 

### Return type

**NSObject***

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **publicSetHeartbeatGet**
```objc
-(NSURLSessionTask*) publicSetHeartbeatGetWithInterval: (NSNumber*) interval
        completionHandler: (void (^)(NSObject* output, NSError* error)) handler;
```

Signals the Websocket connection to send and request heartbeats. Heartbeats can be used to detect stale connections. When heartbeats have been set up, the API server will send `heartbeat` messages and `test_request` messages. Your software should respond to `test_request` messages by sending a `/api/v2/public/test` request. If your software fails to do so, the API server will immediately close the connection. If your account is configured to cancel on disconnect, any orders opened over the connection will be cancelled.

### Example 
```objc
OAIDefaultConfiguration *apiConfig = [OAIDefaultConfiguration sharedConfig];
// Configure HTTP basic authorization (authentication scheme: bearerAuth)
[apiConfig setUsername:@"YOUR_USERNAME"];
[apiConfig setPassword:@"YOUR_PASSWORD"];


NSNumber* interval = 60; // The heartbeat interval in seconds, but not less than 10

OAIWebsocketOnlyApi*apiInstance = [[OAIWebsocketOnlyApi alloc] init];

// Signals the Websocket connection to send and request heartbeats. Heartbeats can be used to detect stale connections. When heartbeats have been set up, the API server will send `heartbeat` messages and `test_request` messages. Your software should respond to `test_request` messages by sending a `/api/v2/public/test` request. If your software fails to do so, the API server will immediately close the connection. If your account is configured to cancel on disconnect, any orders opened over the connection will be cancelled.
[apiInstance publicSetHeartbeatGetWithInterval:interval
          completionHandler: ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAIWebsocketOnlyApi->publicSetHeartbeatGet: %@", error);
                        }
                    }];
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **interval** | **NSNumber***| The heartbeat interval in seconds, but not less than 10 | 

### Return type

**NSObject***

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **publicSubscribeGet**
```objc
-(NSURLSessionTask*) publicSubscribeGetWithChannels: (NSArray<NSString*>*) channels
        completionHandler: (void (^)(NSObject* output, NSError* error)) handler;
```

Subscribe to one or more channels.

Subscribe to one or more channels.  This is the same method as [/private/subscribe](#private_subscribe), but it can only be used for 'public' channels. 

### Example 
```objc
OAIDefaultConfiguration *apiConfig = [OAIDefaultConfiguration sharedConfig];
// Configure HTTP basic authorization (authentication scheme: bearerAuth)
[apiConfig setUsername:@"YOUR_USERNAME"];
[apiConfig setPassword:@"YOUR_PASSWORD"];


NSArray<NSString*>* channels = @[@"channels_example"]; // A list of channels to subscribe to.

OAIWebsocketOnlyApi*apiInstance = [[OAIWebsocketOnlyApi alloc] init];

// Subscribe to one or more channels.
[apiInstance publicSubscribeGetWithChannels:channels
          completionHandler: ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAIWebsocketOnlyApi->publicSubscribeGet: %@", error);
                        }
                    }];
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **channels** | [**NSArray&lt;NSString*&gt;***](NSString*.md)| A list of channels to subscribe to. | 

### Return type

**NSObject***

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **publicUnsubscribeGet**
```objc
-(NSURLSessionTask*) publicUnsubscribeGetWithChannels: (NSArray<NSString*>*) channels
        completionHandler: (void (^)(NSObject* output, NSError* error)) handler;
```

Unsubscribe from one or more channels.

### Example 
```objc
OAIDefaultConfiguration *apiConfig = [OAIDefaultConfiguration sharedConfig];
// Configure HTTP basic authorization (authentication scheme: bearerAuth)
[apiConfig setUsername:@"YOUR_USERNAME"];
[apiConfig setPassword:@"YOUR_PASSWORD"];


NSArray<NSString*>* channels = @[@"channels_example"]; // A list of channels to unsubscribe from.

OAIWebsocketOnlyApi*apiInstance = [[OAIWebsocketOnlyApi alloc] init];

// Unsubscribe from one or more channels.
[apiInstance publicUnsubscribeGetWithChannels:channels
          completionHandler: ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAIWebsocketOnlyApi->publicUnsubscribeGet: %@", error);
                        }
                    }];
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **channels** | [**NSArray&lt;NSString*&gt;***](NSString*.md)| A list of channels to unsubscribe from. | 

### Return type

**NSObject***

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

