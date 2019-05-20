# SubscriptionManagementAPI

All URIs are relative to *https://www.deribit.com/api/v2*

Method | HTTP request | Description
------------- | ------------- | -------------
[**privateSubscribeGet**](SubscriptionManagementAPI.md#privatesubscribeget) | **GET** /private/subscribe | Subscribe to one or more channels.
[**privateUnsubscribeGet**](SubscriptionManagementAPI.md#privateunsubscribeget) | **GET** /private/unsubscribe | Unsubscribe from one or more channels.
[**publicSubscribeGet**](SubscriptionManagementAPI.md#publicsubscribeget) | **GET** /public/subscribe | Subscribe to one or more channels.
[**publicUnsubscribeGet**](SubscriptionManagementAPI.md#publicunsubscribeget) | **GET** /public/unsubscribe | Unsubscribe from one or more channels.


# **privateSubscribeGet**
```swift
    open class func privateSubscribeGet(channels: [String], completion: @escaping (_ data: Any?, _ error: Error?) -> Void)
```

Subscribe to one or more channels.

Subscribe to one or more channels.  The name of the channel determines what information will be provided, and in what form. 

### Example 
```swift
// The following code samples are still beta. For any issue, please report via http://github.com/OpenAPITools/openapi-generator/issues/new
import OpenAPIClient

let channels = ["inner_example"] // [String] | A list of channels to subscribe to.

// Subscribe to one or more channels.
SubscriptionManagementAPI.privateSubscribeGet(channels: channels) { (response, error) in
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
 **channels** | [**[String]**](String.md) | A list of channels to subscribe to. | 

### Return type

**Any**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateUnsubscribeGet**
```swift
    open class func privateUnsubscribeGet(channels: [String], completion: @escaping (_ data: Any?, _ error: Error?) -> Void)
```

Unsubscribe from one or more channels.

### Example 
```swift
// The following code samples are still beta. For any issue, please report via http://github.com/OpenAPITools/openapi-generator/issues/new
import OpenAPIClient

let channels = ["inner_example"] // [String] | A list of channels to unsubscribe from.

// Unsubscribe from one or more channels.
SubscriptionManagementAPI.privateUnsubscribeGet(channels: channels) { (response, error) in
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
 **channels** | [**[String]**](String.md) | A list of channels to unsubscribe from. | 

### Return type

**Any**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **publicSubscribeGet**
```swift
    open class func publicSubscribeGet(channels: [String], completion: @escaping (_ data: Any?, _ error: Error?) -> Void)
```

Subscribe to one or more channels.

Subscribe to one or more channels.  This is the same method as [/private/subscribe](#private_subscribe), but it can only be used for 'public' channels. 

### Example 
```swift
// The following code samples are still beta. For any issue, please report via http://github.com/OpenAPITools/openapi-generator/issues/new
import OpenAPIClient

let channels = ["inner_example"] // [String] | A list of channels to subscribe to.

// Subscribe to one or more channels.
SubscriptionManagementAPI.publicSubscribeGet(channels: channels) { (response, error) in
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
 **channels** | [**[String]**](String.md) | A list of channels to subscribe to. | 

### Return type

**Any**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **publicUnsubscribeGet**
```swift
    open class func publicUnsubscribeGet(channels: [String], completion: @escaping (_ data: Any?, _ error: Error?) -> Void)
```

Unsubscribe from one or more channels.

### Example 
```swift
// The following code samples are still beta. For any issue, please report via http://github.com/OpenAPITools/openapi-generator/issues/new
import OpenAPIClient

let channels = ["inner_example"] // [String] | A list of channels to unsubscribe from.

// Unsubscribe from one or more channels.
SubscriptionManagementAPI.publicUnsubscribeGet(channels: channels) { (response, error) in
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
 **channels** | [**[String]**](String.md) | A list of channels to unsubscribe from. | 

### Return type

**Any**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

