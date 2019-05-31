# SupportingApi

All URIs are relative to *https://www.deribit.com/api/v2*

Method | HTTP request | Description
------------- | ------------- | -------------
[**publicGetTimeGet**](SupportingApi.md#publicGetTimeGet) | **GET** /public/get_time | Retrieves the current time (in milliseconds). This API endpoint can be used to check the clock skew between your software and Deribit&#39;s systems.
[**publicTestGet**](SupportingApi.md#publicTestGet) | **GET** /public/test | Tests the connection to the API server, and returns its version. You can use this to make sure the API is reachable, and matches the expected version.



## publicGetTimeGet

> Object publicGetTimeGet()

Retrieves the current time (in milliseconds). This API endpoint can be used to check the clock skew between your software and Deribit&#39;s systems.

### Example

```java
// Import classes:
//import org.openapitools.client.api.SupportingApi;

SupportingApi apiInstance = new SupportingApi();
try {
    Object result = apiInstance.publicGetTimeGet();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling SupportingApi#publicGetTimeGet");
    e.printStackTrace();
}
```

### Parameters

This endpoint does not need any parameter.

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## publicTestGet

> Object publicTestGet(expectedResult)

Tests the connection to the API server, and returns its version. You can use this to make sure the API is reachable, and matches the expected version.

### Example

```java
// Import classes:
//import org.openapitools.client.api.SupportingApi;

SupportingApi apiInstance = new SupportingApi();
String expectedResult = null; // String | The value \"exception\" will trigger an error response. This may be useful for testing wrapper libraries.
try {
    Object result = apiInstance.publicTestGet(expectedResult);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling SupportingApi#publicTestGet");
    e.printStackTrace();
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **expectedResult** | **String**| The value \&quot;exception\&quot; will trigger an error response. This may be useful for testing wrapper libraries. | [optional] [default to null] [enum: exception]

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

