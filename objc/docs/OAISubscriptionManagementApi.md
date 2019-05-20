# OAISubscriptionManagementApi

All URIs are relative to *https://www.deribit.com/api/v2*

Method | HTTP request | Description
------------- | ------------- | -------------
[**privateSubscribeGet**](OAISubscriptionManagementApi.md#privatesubscribeget) | **GET** /private/subscribe | Subscribe to one or more channels.
[**privateUnsubscribeGet**](OAISubscriptionManagementApi.md#privateunsubscribeget) | **GET** /private/unsubscribe | Unsubscribe from one or more channels.
[**publicSubscribeGet**](OAISubscriptionManagementApi.md#publicsubscribeget) | **GET** /public/subscribe | Subscribe to one or more channels.
[**publicUnsubscribeGet**](OAISubscriptionManagementApi.md#publicunsubscribeget) | **GET** /public/unsubscribe | Unsubscribe from one or more channels.


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

OAISubscriptionManagementApi*apiInstance = [[OAISubscriptionManagementApi alloc] init];

// Subscribe to one or more channels.
[apiInstance privateSubscribeGetWithChannels:channels
          completionHandler: ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAISubscriptionManagementApi->privateSubscribeGet: %@", error);
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

OAISubscriptionManagementApi*apiInstance = [[OAISubscriptionManagementApi alloc] init];

// Unsubscribe from one or more channels.
[apiInstance privateUnsubscribeGetWithChannels:channels
          completionHandler: ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAISubscriptionManagementApi->privateUnsubscribeGet: %@", error);
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

OAISubscriptionManagementApi*apiInstance = [[OAISubscriptionManagementApi alloc] init];

// Subscribe to one or more channels.
[apiInstance publicSubscribeGetWithChannels:channels
          completionHandler: ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAISubscriptionManagementApi->publicSubscribeGet: %@", error);
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

OAISubscriptionManagementApi*apiInstance = [[OAISubscriptionManagementApi alloc] init];

// Unsubscribe from one or more channels.
[apiInstance publicUnsubscribeGetWithChannels:channels
          completionHandler: ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAISubscriptionManagementApi->publicUnsubscribeGet: %@", error);
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

