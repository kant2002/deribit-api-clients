# \SupportingApi

All URIs are relative to *https://www.deribit.com/api/v2*

Method | HTTP request | Description
------------- | ------------- | -------------
[**PublicGetTimeGet**](SupportingApi.md#PublicGetTimeGet) | **Get** /public/get_time | Retrieves the current time (in milliseconds). This API endpoint can be used to check the clock skew between your software and Deribit&#39;s systems.
[**PublicTestGet**](SupportingApi.md#PublicTestGet) | **Get** /public/test | Tests the connection to the API server, and returns its version. You can use this to make sure the API is reachable, and matches the expected version.



## PublicGetTimeGet

> map[string]interface{} PublicGetTimeGet(ctx, )
Retrieves the current time (in milliseconds). This API endpoint can be used to check the clock skew between your software and Deribit's systems.

### Required Parameters

This endpoint does not need any parameter.

### Return type

[**map[string]interface{}**](map[string]interface{}.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PublicTestGet

> map[string]interface{} PublicTestGet(ctx, optional)
Tests the connection to the API server, and returns its version. You can use this to make sure the API is reachable, and matches the expected version.

### Required Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
**ctx** | **context.Context** | context for authentication, logging, cancellation, deadlines, tracing, etc.
 **optional** | ***PublicTestGetOpts** | optional parameters | nil if no parameters

### Optional Parameters

Optional parameters are passed through a pointer to a PublicTestGetOpts struct


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **expectedResult** | **optional.String**| The value \&quot;exception\&quot; will trigger an error response. This may be useful for testing wrapper libraries. | 

### Return type

[**map[string]interface{}**](map[string]interface{}.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)

