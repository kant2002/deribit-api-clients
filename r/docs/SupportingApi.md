# SupportingApi

All URIs are relative to *https://www.deribit.com/api/v2*

Method | HTTP request | Description
------------- | ------------- | -------------
[**PublicGetTimeGet**](SupportingApi.md#PublicGetTimeGet) | **GET** /public/get_time | Retrieves the current time (in milliseconds). This API endpoint can be used to check the clock skew between your software and Deribit&#39;s systems.
[**PublicTestGet**](SupportingApi.md#PublicTestGet) | **GET** /public/test | Tests the connection to the API server, and returns its version. You can use this to make sure the API is reachable, and matches the expected version.


# **PublicGetTimeGet**
> object PublicGetTimeGet()

Retrieves the current time (in milliseconds). This API endpoint can be used to check the clock skew between your software and Deribit's systems.

### Example
```R
library(openapi)


#Retrieves the current time (in milliseconds). This API endpoint can be used to check the clock skew between your software and Deribit's systems.
api.instance <- SupportingApi$new()
# Configure HTTP basic authorization: bearerAuth
api.instance$apiClient$username <- 'TODO_YOUR_USERNAME';
api.instance$apiClient$password <- 'TODO_YOUR_PASSWORD';
result <- api.instance$PublicGetTimeGet()
dput(result)
```

### Parameters
This endpoint does not need any parameter.

### Return type

**object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json



# **PublicTestGet**
> object PublicTestGet(expected.result=var.expected.result)

Tests the connection to the API server, and returns its version. You can use this to make sure the API is reachable, and matches the expected version.

### Example
```R
library(openapi)

var.expected.result <- 'expected.result_example' # character | The value \"exception\" will trigger an error response. This may be useful for testing wrapper libraries.

#Tests the connection to the API server, and returns its version. You can use this to make sure the API is reachable, and matches the expected version.
api.instance <- SupportingApi$new()
# Configure HTTP basic authorization: bearerAuth
api.instance$apiClient$username <- 'TODO_YOUR_USERNAME';
api.instance$apiClient$password <- 'TODO_YOUR_PASSWORD';
result <- api.instance$PublicTestGet(expected.result=var.expected.result)
dput(result)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **expected.result** | **character**| The value \&quot;exception\&quot; will trigger an error response. This may be useful for testing wrapper libraries. | [optional] 

### Return type

**object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json



