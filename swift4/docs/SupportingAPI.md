# SupportingAPI

All URIs are relative to *https://www.deribit.com/api/v2*

Method | HTTP request | Description
------------- | ------------- | -------------
[**publicGetTimeGet**](SupportingAPI.md#publicgettimeget) | **GET** /public/get_time | Retrieves the current time (in milliseconds). This API endpoint can be used to check the clock skew between your software and Deribit&#39;s systems.
[**publicTestGet**](SupportingAPI.md#publictestget) | **GET** /public/test | Tests the connection to the API server, and returns its version. You can use this to make sure the API is reachable, and matches the expected version.


# **publicGetTimeGet**
```swift
    open class func publicGetTimeGet(completion: @escaping (_ data: Any?, _ error: Error?) -> Void)
```

Retrieves the current time (in milliseconds). This API endpoint can be used to check the clock skew between your software and Deribit's systems.

### Example 
```swift
// The following code samples are still beta. For any issue, please report via http://github.com/OpenAPITools/openapi-generator/issues/new
import OpenAPIClient


// Retrieves the current time (in milliseconds). This API endpoint can be used to check the clock skew between your software and Deribit's systems.
SupportingAPI.publicGetTimeGet() { (response, error) in
    guard error == nil else {
        print(error)
        return
    }

    if (response) {
        dump(response)
    }
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

**Any**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **publicTestGet**
```swift
    open class func publicTestGet(expectedResult: ExpectedResult_publicTestGet? = nil, completion: @escaping (_ data: Any?, _ error: Error?) -> Void)
```

Tests the connection to the API server, and returns its version. You can use this to make sure the API is reachable, and matches the expected version.

### Example 
```swift
// The following code samples are still beta. For any issue, please report via http://github.com/OpenAPITools/openapi-generator/issues/new
import OpenAPIClient

let expectedResult = "expectedResult_example" // String | The value \"exception\" will trigger an error response. This may be useful for testing wrapper libraries. (optional)

// Tests the connection to the API server, and returns its version. You can use this to make sure the API is reachable, and matches the expected version.
SupportingAPI.publicTestGet(expectedResult: expectedResult) { (response, error) in
    guard error == nil else {
        print(error)
        return
    }

    if (response) {
        dump(response)
    }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **expectedResult** | **String** | The value \&quot;exception\&quot; will trigger an error response. This may be useful for testing wrapper libraries. | [optional] 

### Return type

**Any**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

