# OAISupportingApi

All URIs are relative to *https://www.deribit.com/api/v2*

Method | HTTP request | Description
------------- | ------------- | -------------
[**publicGetTimeGet**](OAISupportingApi.md#publicgettimeget) | **GET** /public/get_time | Retrieves the current time (in milliseconds). This API endpoint can be used to check the clock skew between your software and Deribit&#39;s systems.
[**publicTestGet**](OAISupportingApi.md#publictestget) | **GET** /public/test | Tests the connection to the API server, and returns its version. You can use this to make sure the API is reachable, and matches the expected version.


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



OAISupportingApi*apiInstance = [[OAISupportingApi alloc] init];

// Retrieves the current time (in milliseconds). This API endpoint can be used to check the clock skew between your software and Deribit's systems.
[apiInstance publicGetTimeGetWithCompletionHandler: 
          ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAISupportingApi->publicGetTimeGet: %@", error);
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

OAISupportingApi*apiInstance = [[OAISupportingApi alloc] init];

// Tests the connection to the API server, and returns its version. You can use this to make sure the API is reachable, and matches the expected version.
[apiInstance publicTestGetWithExpectedResult:expectedResult
          completionHandler: ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAISupportingApi->publicTestGet: %@", error);
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

