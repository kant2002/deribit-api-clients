# SessionManagementApi

All URIs are relative to *https://www.deribit.com/api/v2*

Method | HTTP request | Description
------------- | ------------- | -------------
[**privateDisableCancelOnDisconnectGet**](SessionManagementApi.md#privateDisableCancelOnDisconnectGet) | **GET** /private/disable_cancel_on_disconnect | Disable Cancel On Disconnect for the connection. This does not change the default account setting.
[**privateEnableCancelOnDisconnectGet**](SessionManagementApi.md#privateEnableCancelOnDisconnectGet) | **GET** /private/enable_cancel_on_disconnect | Enable Cancel On Disconnect for the connection. This does not change the default account setting.
[**publicDisableHeartbeatGet**](SessionManagementApi.md#publicDisableHeartbeatGet) | **GET** /public/disable_heartbeat | Stop sending heartbeat messages.
[**publicSetHeartbeatGet**](SessionManagementApi.md#publicSetHeartbeatGet) | **GET** /public/set_heartbeat | Signals the Websocket connection to send and request heartbeats. Heartbeats can be used to detect stale connections. When heartbeats have been set up, the API server will send &#x60;heartbeat&#x60; messages and &#x60;test_request&#x60; messages. Your software should respond to &#x60;test_request&#x60; messages by sending a &#x60;/api/v2/public/test&#x60; request. If your software fails to do so, the API server will immediately close the connection. If your account is configured to cancel on disconnect, any orders opened over the connection will be cancelled.


<a name="privateDisableCancelOnDisconnectGet"></a>
# **privateDisableCancelOnDisconnectGet**
> kotlin.Any privateDisableCancelOnDisconnectGet()

Disable Cancel On Disconnect for the connection. This does not change the default account setting.

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = SessionManagementApi()
try {
    val result : kotlin.Any = apiInstance.privateDisableCancelOnDisconnectGet()
    println(result)
} catch (e: ClientException) {
    println("4xx response calling SessionManagementApi#privateDisableCancelOnDisconnectGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling SessionManagementApi#privateDisableCancelOnDisconnectGet")
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

<a name="privateEnableCancelOnDisconnectGet"></a>
# **privateEnableCancelOnDisconnectGet**
> kotlin.Any privateEnableCancelOnDisconnectGet()

Enable Cancel On Disconnect for the connection. This does not change the default account setting.

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = SessionManagementApi()
try {
    val result : kotlin.Any = apiInstance.privateEnableCancelOnDisconnectGet()
    println(result)
} catch (e: ClientException) {
    println("4xx response calling SessionManagementApi#privateEnableCancelOnDisconnectGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling SessionManagementApi#privateEnableCancelOnDisconnectGet")
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

<a name="publicDisableHeartbeatGet"></a>
# **publicDisableHeartbeatGet**
> kotlin.Any publicDisableHeartbeatGet()

Stop sending heartbeat messages.

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = SessionManagementApi()
try {
    val result : kotlin.Any = apiInstance.publicDisableHeartbeatGet()
    println(result)
} catch (e: ClientException) {
    println("4xx response calling SessionManagementApi#publicDisableHeartbeatGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling SessionManagementApi#publicDisableHeartbeatGet")
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

<a name="publicSetHeartbeatGet"></a>
# **publicSetHeartbeatGet**
> kotlin.Any publicSetHeartbeatGet(interval)

Signals the Websocket connection to send and request heartbeats. Heartbeats can be used to detect stale connections. When heartbeats have been set up, the API server will send &#x60;heartbeat&#x60; messages and &#x60;test_request&#x60; messages. Your software should respond to &#x60;test_request&#x60; messages by sending a &#x60;/api/v2/public/test&#x60; request. If your software fails to do so, the API server will immediately close the connection. If your account is configured to cancel on disconnect, any orders opened over the connection will be cancelled.

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = SessionManagementApi()
val interval : java.math.BigDecimal = 60 // java.math.BigDecimal | The heartbeat interval in seconds, but not less than 10
try {
    val result : kotlin.Any = apiInstance.publicSetHeartbeatGet(interval)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling SessionManagementApi#publicSetHeartbeatGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling SessionManagementApi#publicSetHeartbeatGet")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **interval** | **java.math.BigDecimal**| The heartbeat interval in seconds, but not less than 10 |

### Return type

[**kotlin.Any**](kotlin.Any.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

