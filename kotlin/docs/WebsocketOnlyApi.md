# WebsocketOnlyApi

All URIs are relative to *https://www.deribit.com/api/v2*

Method | HTTP request | Description
------------- | ------------- | -------------
[**privateDisableCancelOnDisconnectGet**](WebsocketOnlyApi.md#privateDisableCancelOnDisconnectGet) | **GET** /private/disable_cancel_on_disconnect | Disable Cancel On Disconnect for the connection. This does not change the default account setting.
[**privateEnableCancelOnDisconnectGet**](WebsocketOnlyApi.md#privateEnableCancelOnDisconnectGet) | **GET** /private/enable_cancel_on_disconnect | Enable Cancel On Disconnect for the connection. This does not change the default account setting.
[**privateLogoutGet**](WebsocketOnlyApi.md#privateLogoutGet) | **GET** /private/logout | Gracefully close websocket connection, when COD (Cancel On Disconnect) is enabled orders are not cancelled
[**privateSubscribeGet**](WebsocketOnlyApi.md#privateSubscribeGet) | **GET** /private/subscribe | Subscribe to one or more channels.
[**privateUnsubscribeGet**](WebsocketOnlyApi.md#privateUnsubscribeGet) | **GET** /private/unsubscribe | Unsubscribe from one or more channels.
[**publicDisableHeartbeatGet**](WebsocketOnlyApi.md#publicDisableHeartbeatGet) | **GET** /public/disable_heartbeat | Stop sending heartbeat messages.
[**publicHelloGet**](WebsocketOnlyApi.md#publicHelloGet) | **GET** /public/hello | Method used to introduce the client software connected to Deribit platform over websocket. Provided data may have an impact on the maintained connection and will be collected for internal statistical purposes. In response, Deribit will also introduce itself.
[**publicSetHeartbeatGet**](WebsocketOnlyApi.md#publicSetHeartbeatGet) | **GET** /public/set_heartbeat | Signals the Websocket connection to send and request heartbeats. Heartbeats can be used to detect stale connections. When heartbeats have been set up, the API server will send &#x60;heartbeat&#x60; messages and &#x60;test_request&#x60; messages. Your software should respond to &#x60;test_request&#x60; messages by sending a &#x60;/api/v2/public/test&#x60; request. If your software fails to do so, the API server will immediately close the connection. If your account is configured to cancel on disconnect, any orders opened over the connection will be cancelled.
[**publicSubscribeGet**](WebsocketOnlyApi.md#publicSubscribeGet) | **GET** /public/subscribe | Subscribe to one or more channels.
[**publicUnsubscribeGet**](WebsocketOnlyApi.md#publicUnsubscribeGet) | **GET** /public/unsubscribe | Unsubscribe from one or more channels.


<a name="privateDisableCancelOnDisconnectGet"></a>
# **privateDisableCancelOnDisconnectGet**
> kotlin.Any privateDisableCancelOnDisconnectGet()

Disable Cancel On Disconnect for the connection. This does not change the default account setting.

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = WebsocketOnlyApi()
try {
    val result : kotlin.Any = apiInstance.privateDisableCancelOnDisconnectGet()
    println(result)
} catch (e: ClientException) {
    println("4xx response calling WebsocketOnlyApi#privateDisableCancelOnDisconnectGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling WebsocketOnlyApi#privateDisableCancelOnDisconnectGet")
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

val apiInstance = WebsocketOnlyApi()
try {
    val result : kotlin.Any = apiInstance.privateEnableCancelOnDisconnectGet()
    println(result)
} catch (e: ClientException) {
    println("4xx response calling WebsocketOnlyApi#privateEnableCancelOnDisconnectGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling WebsocketOnlyApi#privateEnableCancelOnDisconnectGet")
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

<a name="privateLogoutGet"></a>
# **privateLogoutGet**
> privateLogoutGet()

Gracefully close websocket connection, when COD (Cancel On Disconnect) is enabled orders are not cancelled

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = WebsocketOnlyApi()
try {
    apiInstance.privateLogoutGet()
} catch (e: ClientException) {
    println("4xx response calling WebsocketOnlyApi#privateLogoutGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling WebsocketOnlyApi#privateLogoutGet")
    e.printStackTrace()
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

null (empty response body)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="privateSubscribeGet"></a>
# **privateSubscribeGet**
> kotlin.Any privateSubscribeGet(channels)

Subscribe to one or more channels.

Subscribe to one or more channels.  The name of the channel determines what information will be provided, and in what form. 

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = WebsocketOnlyApi()
val channels : kotlin.Array<kotlin.String> =  // kotlin.Array<kotlin.String> | A list of channels to subscribe to.
try {
    val result : kotlin.Any = apiInstance.privateSubscribeGet(channels)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling WebsocketOnlyApi#privateSubscribeGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling WebsocketOnlyApi#privateSubscribeGet")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **channels** | [**kotlin.Array&lt;kotlin.String&gt;**](kotlin.String.md)| A list of channels to subscribe to. |

### Return type

[**kotlin.Any**](kotlin.Any.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="privateUnsubscribeGet"></a>
# **privateUnsubscribeGet**
> kotlin.Any privateUnsubscribeGet(channels)

Unsubscribe from one or more channels.

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = WebsocketOnlyApi()
val channels : kotlin.Array<kotlin.String> =  // kotlin.Array<kotlin.String> | A list of channels to unsubscribe from.
try {
    val result : kotlin.Any = apiInstance.privateUnsubscribeGet(channels)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling WebsocketOnlyApi#privateUnsubscribeGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling WebsocketOnlyApi#privateUnsubscribeGet")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **channels** | [**kotlin.Array&lt;kotlin.String&gt;**](kotlin.String.md)| A list of channels to unsubscribe from. |

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

val apiInstance = WebsocketOnlyApi()
try {
    val result : kotlin.Any = apiInstance.publicDisableHeartbeatGet()
    println(result)
} catch (e: ClientException) {
    println("4xx response calling WebsocketOnlyApi#publicDisableHeartbeatGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling WebsocketOnlyApi#publicDisableHeartbeatGet")
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

<a name="publicHelloGet"></a>
# **publicHelloGet**
> kotlin.Any publicHelloGet(clientName, clientVersion)

Method used to introduce the client software connected to Deribit platform over websocket. Provided data may have an impact on the maintained connection and will be collected for internal statistical purposes. In response, Deribit will also introduce itself.

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = WebsocketOnlyApi()
val clientName : kotlin.String = My Trading Software // kotlin.String | Client software name
val clientVersion : kotlin.String = 1.0.2 // kotlin.String | Client software version
try {
    val result : kotlin.Any = apiInstance.publicHelloGet(clientName, clientVersion)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling WebsocketOnlyApi#publicHelloGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling WebsocketOnlyApi#publicHelloGet")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **clientName** | **kotlin.String**| Client software name |
 **clientVersion** | **kotlin.String**| Client software version |

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

val apiInstance = WebsocketOnlyApi()
val interval : java.math.BigDecimal = 60 // java.math.BigDecimal | The heartbeat interval in seconds, but not less than 10
try {
    val result : kotlin.Any = apiInstance.publicSetHeartbeatGet(interval)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling WebsocketOnlyApi#publicSetHeartbeatGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling WebsocketOnlyApi#publicSetHeartbeatGet")
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

<a name="publicSubscribeGet"></a>
# **publicSubscribeGet**
> kotlin.Any publicSubscribeGet(channels)

Subscribe to one or more channels.

Subscribe to one or more channels.  This is the same method as [/private/subscribe](#private_subscribe), but it can only be used for &#39;public&#39; channels. 

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = WebsocketOnlyApi()
val channels : kotlin.Array<kotlin.String> =  // kotlin.Array<kotlin.String> | A list of channels to subscribe to.
try {
    val result : kotlin.Any = apiInstance.publicSubscribeGet(channels)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling WebsocketOnlyApi#publicSubscribeGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling WebsocketOnlyApi#publicSubscribeGet")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **channels** | [**kotlin.Array&lt;kotlin.String&gt;**](kotlin.String.md)| A list of channels to subscribe to. |

### Return type

[**kotlin.Any**](kotlin.Any.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="publicUnsubscribeGet"></a>
# **publicUnsubscribeGet**
> kotlin.Any publicUnsubscribeGet(channels)

Unsubscribe from one or more channels.

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = WebsocketOnlyApi()
val channels : kotlin.Array<kotlin.String> =  // kotlin.Array<kotlin.String> | A list of channels to unsubscribe from.
try {
    val result : kotlin.Any = apiInstance.publicUnsubscribeGet(channels)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling WebsocketOnlyApi#publicUnsubscribeGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling WebsocketOnlyApi#publicUnsubscribeGet")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **channels** | [**kotlin.Array&lt;kotlin.String&gt;**](kotlin.String.md)| A list of channels to unsubscribe from. |

### Return type

[**kotlin.Any**](kotlin.Any.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

