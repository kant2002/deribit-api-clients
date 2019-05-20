# WebsocketOnlyAPI

All URIs are relative to *https://www.deribit.com/api/v2*

Method | HTTP request | Description
------------- | ------------- | -------------
[**privateDisableCancelOnDisconnectGet**](WebsocketOnlyAPI.md#privatedisablecancelondisconnectget) | **GET** /private/disable_cancel_on_disconnect | Disable Cancel On Disconnect for the connection. This does not change the default account setting.
[**privateEnableCancelOnDisconnectGet**](WebsocketOnlyAPI.md#privateenablecancelondisconnectget) | **GET** /private/enable_cancel_on_disconnect | Enable Cancel On Disconnect for the connection. This does not change the default account setting.
[**privateLogoutGet**](WebsocketOnlyAPI.md#privatelogoutget) | **GET** /private/logout | Gracefully close websocket connection, when COD (Cancel On Disconnect) is enabled orders are not cancelled
[**privateSubscribeGet**](WebsocketOnlyAPI.md#privatesubscribeget) | **GET** /private/subscribe | Subscribe to one or more channels.
[**privateUnsubscribeGet**](WebsocketOnlyAPI.md#privateunsubscribeget) | **GET** /private/unsubscribe | Unsubscribe from one or more channels.
[**publicDisableHeartbeatGet**](WebsocketOnlyAPI.md#publicdisableheartbeatget) | **GET** /public/disable_heartbeat | Stop sending heartbeat messages.
[**publicHelloGet**](WebsocketOnlyAPI.md#publichelloget) | **GET** /public/hello | Method used to introduce the client software connected to Deribit platform over websocket. Provided data may have an impact on the maintained connection and will be collected for internal statistical purposes. In response, Deribit will also introduce itself.
[**publicSetHeartbeatGet**](WebsocketOnlyAPI.md#publicsetheartbeatget) | **GET** /public/set_heartbeat | Signals the Websocket connection to send and request heartbeats. Heartbeats can be used to detect stale connections. When heartbeats have been set up, the API server will send &#x60;heartbeat&#x60; messages and &#x60;test_request&#x60; messages. Your software should respond to &#x60;test_request&#x60; messages by sending a &#x60;/api/v2/public/test&#x60; request. If your software fails to do so, the API server will immediately close the connection. If your account is configured to cancel on disconnect, any orders opened over the connection will be cancelled.
[**publicSubscribeGet**](WebsocketOnlyAPI.md#publicsubscribeget) | **GET** /public/subscribe | Subscribe to one or more channels.
[**publicUnsubscribeGet**](WebsocketOnlyAPI.md#publicunsubscribeget) | **GET** /public/unsubscribe | Unsubscribe from one or more channels.


# **privateDisableCancelOnDisconnectGet**
```swift
    open class func privateDisableCancelOnDisconnectGet(completion: @escaping (_ data: Any?, _ error: Error?) -> Void)
```

Disable Cancel On Disconnect for the connection. This does not change the default account setting.

### Example 
```swift
// The following code samples are still beta. For any issue, please report via http://github.com/OpenAPITools/openapi-generator/issues/new
import OpenAPIClient


// Disable Cancel On Disconnect for the connection. This does not change the default account setting.
WebsocketOnlyAPI.privateDisableCancelOnDisconnectGet() { (response, error) in
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

# **privateEnableCancelOnDisconnectGet**
```swift
    open class func privateEnableCancelOnDisconnectGet(completion: @escaping (_ data: Any?, _ error: Error?) -> Void)
```

Enable Cancel On Disconnect for the connection. This does not change the default account setting.

### Example 
```swift
// The following code samples are still beta. For any issue, please report via http://github.com/OpenAPITools/openapi-generator/issues/new
import OpenAPIClient


// Enable Cancel On Disconnect for the connection. This does not change the default account setting.
WebsocketOnlyAPI.privateEnableCancelOnDisconnectGet() { (response, error) in
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

# **privateLogoutGet**
```swift
    open class func privateLogoutGet(completion: @escaping (_ data: Void?, _ error: Error?) -> Void)
```

Gracefully close websocket connection, when COD (Cancel On Disconnect) is enabled orders are not cancelled

### Example 
```swift
// The following code samples are still beta. For any issue, please report via http://github.com/OpenAPITools/openapi-generator/issues/new
import OpenAPIClient


// Gracefully close websocket connection, when COD (Cancel On Disconnect) is enabled orders are not cancelled
WebsocketOnlyAPI.privateLogoutGet() { (response, error) in
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

Void (empty response body)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

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
WebsocketOnlyAPI.privateSubscribeGet(channels: channels) { (response, error) in
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
WebsocketOnlyAPI.privateUnsubscribeGet(channels: channels) { (response, error) in
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

# **publicDisableHeartbeatGet**
```swift
    open class func publicDisableHeartbeatGet(completion: @escaping (_ data: Any?, _ error: Error?) -> Void)
```

Stop sending heartbeat messages.

### Example 
```swift
// The following code samples are still beta. For any issue, please report via http://github.com/OpenAPITools/openapi-generator/issues/new
import OpenAPIClient


// Stop sending heartbeat messages.
WebsocketOnlyAPI.publicDisableHeartbeatGet() { (response, error) in
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

# **publicHelloGet**
```swift
    open class func publicHelloGet(clientName: String, clientVersion: String, completion: @escaping (_ data: Any?, _ error: Error?) -> Void)
```

Method used to introduce the client software connected to Deribit platform over websocket. Provided data may have an impact on the maintained connection and will be collected for internal statistical purposes. In response, Deribit will also introduce itself.

### Example 
```swift
// The following code samples are still beta. For any issue, please report via http://github.com/OpenAPITools/openapi-generator/issues/new
import OpenAPIClient

let clientName = "clientName_example" // String | Client software name
let clientVersion = "clientVersion_example" // String | Client software version

// Method used to introduce the client software connected to Deribit platform over websocket. Provided data may have an impact on the maintained connection and will be collected for internal statistical purposes. In response, Deribit will also introduce itself.
WebsocketOnlyAPI.publicHelloGet(clientName: clientName, clientVersion: clientVersion) { (response, error) in
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
 **clientName** | **String** | Client software name | 
 **clientVersion** | **String** | Client software version | 

### Return type

**Any**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **publicSetHeartbeatGet**
```swift
    open class func publicSetHeartbeatGet(interval: Double, completion: @escaping (_ data: Any?, _ error: Error?) -> Void)
```

Signals the Websocket connection to send and request heartbeats. Heartbeats can be used to detect stale connections. When heartbeats have been set up, the API server will send `heartbeat` messages and `test_request` messages. Your software should respond to `test_request` messages by sending a `/api/v2/public/test` request. If your software fails to do so, the API server will immediately close the connection. If your account is configured to cancel on disconnect, any orders opened over the connection will be cancelled.

### Example 
```swift
// The following code samples are still beta. For any issue, please report via http://github.com/OpenAPITools/openapi-generator/issues/new
import OpenAPIClient

let interval = 987 // Double | The heartbeat interval in seconds, but not less than 10

// Signals the Websocket connection to send and request heartbeats. Heartbeats can be used to detect stale connections. When heartbeats have been set up, the API server will send `heartbeat` messages and `test_request` messages. Your software should respond to `test_request` messages by sending a `/api/v2/public/test` request. If your software fails to do so, the API server will immediately close the connection. If your account is configured to cancel on disconnect, any orders opened over the connection will be cancelled.
WebsocketOnlyAPI.publicSetHeartbeatGet(interval: interval) { (response, error) in
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
 **interval** | **Double** | The heartbeat interval in seconds, but not less than 10 | 

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
WebsocketOnlyAPI.publicSubscribeGet(channels: channels) { (response, error) in
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
WebsocketOnlyAPI.publicUnsubscribeGet(channels: channels) { (response, error) in
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

