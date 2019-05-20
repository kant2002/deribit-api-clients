# OAISessionManagementApi

All URIs are relative to *https://www.deribit.com/api/v2*

Method | HTTP request | Description
------------- | ------------- | -------------
[**privateDisableCancelOnDisconnectGet**](OAISessionManagementApi.md#privatedisablecancelondisconnectget) | **GET** /private/disable_cancel_on_disconnect | Disable Cancel On Disconnect for the connection. This does not change the default account setting.
[**privateEnableCancelOnDisconnectGet**](OAISessionManagementApi.md#privateenablecancelondisconnectget) | **GET** /private/enable_cancel_on_disconnect | Enable Cancel On Disconnect for the connection. This does not change the default account setting.
[**publicDisableHeartbeatGet**](OAISessionManagementApi.md#publicdisableheartbeatget) | **GET** /public/disable_heartbeat | Stop sending heartbeat messages.
[**publicSetHeartbeatGet**](OAISessionManagementApi.md#publicsetheartbeatget) | **GET** /public/set_heartbeat | Signals the Websocket connection to send and request heartbeats. Heartbeats can be used to detect stale connections. When heartbeats have been set up, the API server will send &#x60;heartbeat&#x60; messages and &#x60;test_request&#x60; messages. Your software should respond to &#x60;test_request&#x60; messages by sending a &#x60;/api/v2/public/test&#x60; request. If your software fails to do so, the API server will immediately close the connection. If your account is configured to cancel on disconnect, any orders opened over the connection will be cancelled.


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



OAISessionManagementApi*apiInstance = [[OAISessionManagementApi alloc] init];

// Disable Cancel On Disconnect for the connection. This does not change the default account setting.
[apiInstance privateDisableCancelOnDisconnectGetWithCompletionHandler: 
          ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAISessionManagementApi->privateDisableCancelOnDisconnectGet: %@", error);
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



OAISessionManagementApi*apiInstance = [[OAISessionManagementApi alloc] init];

// Enable Cancel On Disconnect for the connection. This does not change the default account setting.
[apiInstance privateEnableCancelOnDisconnectGetWithCompletionHandler: 
          ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAISessionManagementApi->privateEnableCancelOnDisconnectGet: %@", error);
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



OAISessionManagementApi*apiInstance = [[OAISessionManagementApi alloc] init];

// Stop sending heartbeat messages.
[apiInstance publicDisableHeartbeatGetWithCompletionHandler: 
          ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAISessionManagementApi->publicDisableHeartbeatGet: %@", error);
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

OAISessionManagementApi*apiInstance = [[OAISessionManagementApi alloc] init];

// Signals the Websocket connection to send and request heartbeats. Heartbeats can be used to detect stale connections. When heartbeats have been set up, the API server will send `heartbeat` messages and `test_request` messages. Your software should respond to `test_request` messages by sending a `/api/v2/public/test` request. If your software fails to do so, the API server will immediately close the connection. If your account is configured to cancel on disconnect, any orders opened over the connection will be cancelled.
[apiInstance publicSetHeartbeatGetWithInterval:interval
          completionHandler: ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAISessionManagementApi->publicSetHeartbeatGet: %@", error);
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

