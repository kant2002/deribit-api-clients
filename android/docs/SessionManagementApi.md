# SessionManagementApi

All URIs are relative to *https://www.deribit.com/api/v2*

Method | HTTP request | Description
------------- | ------------- | -------------
[**privateDisableCancelOnDisconnectGet**](SessionManagementApi.md#privateDisableCancelOnDisconnectGet) | **GET** /private/disable_cancel_on_disconnect | Disable Cancel On Disconnect for the connection. This does not change the default account setting.
[**privateEnableCancelOnDisconnectGet**](SessionManagementApi.md#privateEnableCancelOnDisconnectGet) | **GET** /private/enable_cancel_on_disconnect | Enable Cancel On Disconnect for the connection. This does not change the default account setting.
[**publicDisableHeartbeatGet**](SessionManagementApi.md#publicDisableHeartbeatGet) | **GET** /public/disable_heartbeat | Stop sending heartbeat messages.
[**publicSetHeartbeatGet**](SessionManagementApi.md#publicSetHeartbeatGet) | **GET** /public/set_heartbeat | Signals the Websocket connection to send and request heartbeats. Heartbeats can be used to detect stale connections. When heartbeats have been set up, the API server will send &#x60;heartbeat&#x60; messages and &#x60;test_request&#x60; messages. Your software should respond to &#x60;test_request&#x60; messages by sending a &#x60;/api/v2/public/test&#x60; request. If your software fails to do so, the API server will immediately close the connection. If your account is configured to cancel on disconnect, any orders opened over the connection will be cancelled.



## privateDisableCancelOnDisconnectGet

> Object privateDisableCancelOnDisconnectGet()

Disable Cancel On Disconnect for the connection. This does not change the default account setting.

### Example

```java
// Import classes:
//import org.openapitools.client.api.SessionManagementApi;

SessionManagementApi apiInstance = new SessionManagementApi();
try {
    Object result = apiInstance.privateDisableCancelOnDisconnectGet();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling SessionManagementApi#privateDisableCancelOnDisconnectGet");
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


## privateEnableCancelOnDisconnectGet

> Object privateEnableCancelOnDisconnectGet()

Enable Cancel On Disconnect for the connection. This does not change the default account setting.

### Example

```java
// Import classes:
//import org.openapitools.client.api.SessionManagementApi;

SessionManagementApi apiInstance = new SessionManagementApi();
try {
    Object result = apiInstance.privateEnableCancelOnDisconnectGet();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling SessionManagementApi#privateEnableCancelOnDisconnectGet");
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


## publicDisableHeartbeatGet

> Object publicDisableHeartbeatGet()

Stop sending heartbeat messages.

### Example

```java
// Import classes:
//import org.openapitools.client.api.SessionManagementApi;

SessionManagementApi apiInstance = new SessionManagementApi();
try {
    Object result = apiInstance.publicDisableHeartbeatGet();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling SessionManagementApi#publicDisableHeartbeatGet");
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


## publicSetHeartbeatGet

> Object publicSetHeartbeatGet(interval)

Signals the Websocket connection to send and request heartbeats. Heartbeats can be used to detect stale connections. When heartbeats have been set up, the API server will send &#x60;heartbeat&#x60; messages and &#x60;test_request&#x60; messages. Your software should respond to &#x60;test_request&#x60; messages by sending a &#x60;/api/v2/public/test&#x60; request. If your software fails to do so, the API server will immediately close the connection. If your account is configured to cancel on disconnect, any orders opened over the connection will be cancelled.

### Example

```java
// Import classes:
//import org.openapitools.client.api.SessionManagementApi;

SessionManagementApi apiInstance = new SessionManagementApi();
BigDecimal interval = 60; // BigDecimal | The heartbeat interval in seconds, but not less than 10
try {
    Object result = apiInstance.publicSetHeartbeatGet(interval);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling SessionManagementApi#publicSetHeartbeatGet");
    e.printStackTrace();
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **interval** | **BigDecimal**| The heartbeat interval in seconds, but not less than 10 | [default to null]

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

