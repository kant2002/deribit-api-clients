# \SubscriptionManagementApi

All URIs are relative to *https://www.deribit.com/api/v2*

Method | HTTP request | Description
------------- | ------------- | -------------
[**PrivateSubscribeGet**](SubscriptionManagementApi.md#PrivateSubscribeGet) | **Get** /private/subscribe | Subscribe to one or more channels.
[**PrivateUnsubscribeGet**](SubscriptionManagementApi.md#PrivateUnsubscribeGet) | **Get** /private/unsubscribe | Unsubscribe from one or more channels.
[**PublicSubscribeGet**](SubscriptionManagementApi.md#PublicSubscribeGet) | **Get** /public/subscribe | Subscribe to one or more channels.
[**PublicUnsubscribeGet**](SubscriptionManagementApi.md#PublicUnsubscribeGet) | **Get** /public/unsubscribe | Unsubscribe from one or more channels.



## PrivateSubscribeGet

> map[string]interface{} PrivateSubscribeGet(ctx, channels)
Subscribe to one or more channels.

Subscribe to one or more channels.  The name of the channel determines what information will be provided, and in what form. 

### Required Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
**ctx** | **context.Context** | context for authentication, logging, cancellation, deadlines, tracing, etc.
**channels** | [**[]string**](string.md)| A list of channels to subscribe to. | 

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


## PrivateUnsubscribeGet

> map[string]interface{} PrivateUnsubscribeGet(ctx, channels)
Unsubscribe from one or more channels.

### Required Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
**ctx** | **context.Context** | context for authentication, logging, cancellation, deadlines, tracing, etc.
**channels** | [**[]string**](string.md)| A list of channels to unsubscribe from. | 

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


## PublicSubscribeGet

> map[string]interface{} PublicSubscribeGet(ctx, channels)
Subscribe to one or more channels.

Subscribe to one or more channels.  This is the same method as [/private/subscribe](#private_subscribe), but it can only be used for 'public' channels. 

### Required Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
**ctx** | **context.Context** | context for authentication, logging, cancellation, deadlines, tracing, etc.
**channels** | [**[]string**](string.md)| A list of channels to subscribe to. | 

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


## PublicUnsubscribeGet

> map[string]interface{} PublicUnsubscribeGet(ctx, channels)
Unsubscribe from one or more channels.

### Required Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
**ctx** | **context.Context** | context for authentication, logging, cancellation, deadlines, tracing, etc.
**channels** | [**[]string**](string.md)| A list of channels to unsubscribe from. | 

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

