# SupportingApi

All URIs are relative to *https://www.deribit.com/api/v2*

Method | HTTP request | Description
------------- | ------------- | -------------
[**publicGetTimeGet**](SupportingApi.md#publicGetTimeGet) | **GET** /public/get_time | Retrieves the current time (in milliseconds). This API endpoint can be used to check the clock skew between your software and Deribit&#39;s systems.
[**publicTestGet**](SupportingApi.md#publicTestGet) | **GET** /public/test | Tests the connection to the API server, and returns its version. You can use this to make sure the API is reachable, and matches the expected version.


<a name="publicGetTimeGet"></a>
# **publicGetTimeGet**
> kotlin.Any publicGetTimeGet()

Retrieves the current time (in milliseconds). This API endpoint can be used to check the clock skew between your software and Deribit&#39;s systems.

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = SupportingApi()
try {
    val result : kotlin.Any = apiInstance.publicGetTimeGet()
    println(result)
} catch (e: ClientException) {
    println("4xx response calling SupportingApi#publicGetTimeGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling SupportingApi#publicGetTimeGet")
    e.printStackTrace()
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**kotlin.Any**](kotlin.Any.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="publicTestGet"></a>
# **publicTestGet**
> kotlin.Any publicTestGet(expectedResult)

Tests the connection to the API server, and returns its version. You can use this to make sure the API is reachable, and matches the expected version.

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = SupportingApi()
val expectedResult : kotlin.String = expectedResult_example // kotlin.String | The value \"exception\" will trigger an error response. This may be useful for testing wrapper libraries.
try {
    val result : kotlin.Any = apiInstance.publicTestGet(expectedResult)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling SupportingApi#publicTestGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling SupportingApi#publicTestGet")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **expectedResult** | **kotlin.String**| The value \&quot;exception\&quot; will trigger an error response. This may be useful for testing wrapper libraries. | [optional] [enum: exception]

### Return type

[**kotlin.Any**](kotlin.Any.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

