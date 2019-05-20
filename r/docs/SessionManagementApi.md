# SessionManagementApi

All URIs are relative to *https://www.deribit.com/api/v2*

Method | HTTP request | Description
------------- | ------------- | -------------
[**PrivateDisableCancelOnDisconnectGet**](SessionManagementApi.md#PrivateDisableCancelOnDisconnectGet) | **GET** /private/disable_cancel_on_disconnect | Disable Cancel On Disconnect for the connection. This does not change the default account setting.
[**PrivateEnableCancelOnDisconnectGet**](SessionManagementApi.md#PrivateEnableCancelOnDisconnectGet) | **GET** /private/enable_cancel_on_disconnect | Enable Cancel On Disconnect for the connection. This does not change the default account setting.
[**PublicDisableHeartbeatGet**](SessionManagementApi.md#PublicDisableHeartbeatGet) | **GET** /public/disable_heartbeat | Stop sending heartbeat messages.
[**PublicSetHeartbeatGet**](SessionManagementApi.md#PublicSetHeartbeatGet) | **GET** /public/set_heartbeat | Signals the Websocket connection to send and request heartbeats. Heartbeats can be used to detect stale connections. When heartbeats have been set up, the API server will send &#x60;heartbeat&#x60; messages and &#x60;test_request&#x60; messages. Your software should respond to &#x60;test_request&#x60; messages by sending a &#x60;/api/v2/public/test&#x60; request. If your software fails to do so, the API server will immediately close the connection. If your account is configured to cancel on disconnect, any orders opened over the connection will be cancelled.


# **PrivateDisableCancelOnDisconnectGet**
> object PrivateDisableCancelOnDisconnectGet()

Disable Cancel On Disconnect for the connection. This does not change the default account setting.

### Example
```R
library(openapi)


#Disable Cancel On Disconnect for the connection. This does not change the default account setting.
api.instance <- SessionManagementApi$new()
# Configure HTTP basic authorization: bearerAuth
api.instance$apiClient$username <- 'TODO_YOUR_USERNAME';
api.instance$apiClient$password <- 'TODO_YOUR_PASSWORD';
result <- api.instance$PrivateDisableCancelOnDisconnectGet()
dput(result)
```

### Parameters
This endpoint does not need any parameter.

### Return type

**object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json



# **PrivateEnableCancelOnDisconnectGet**
> object PrivateEnableCancelOnDisconnectGet()

Enable Cancel On Disconnect for the connection. This does not change the default account setting.

### Example
```R
library(openapi)


#Enable Cancel On Disconnect for the connection. This does not change the default account setting.
api.instance <- SessionManagementApi$new()
# Configure HTTP basic authorization: bearerAuth
api.instance$apiClient$username <- 'TODO_YOUR_USERNAME';
api.instance$apiClient$password <- 'TODO_YOUR_PASSWORD';
result <- api.instance$PrivateEnableCancelOnDisconnectGet()
dput(result)
```

### Parameters
This endpoint does not need any parameter.

### Return type

**object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json



# **PublicDisableHeartbeatGet**
> object PublicDisableHeartbeatGet()

Stop sending heartbeat messages.

### Example
```R
library(openapi)


#Stop sending heartbeat messages.
api.instance <- SessionManagementApi$new()
# Configure HTTP basic authorization: bearerAuth
api.instance$apiClient$username <- 'TODO_YOUR_USERNAME';
api.instance$apiClient$password <- 'TODO_YOUR_PASSWORD';
result <- api.instance$PublicDisableHeartbeatGet()
dput(result)
```

### Parameters
This endpoint does not need any parameter.

### Return type

**object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json



# **PublicSetHeartbeatGet**
> object PublicSetHeartbeatGet(interval)

Signals the Websocket connection to send and request heartbeats. Heartbeats can be used to detect stale connections. When heartbeats have been set up, the API server will send `heartbeat` messages and `test_request` messages. Your software should respond to `test_request` messages by sending a `/api/v2/public/test` request. If your software fails to do so, the API server will immediately close the connection. If your account is configured to cancel on disconnect, any orders opened over the connection will be cancelled.

### Example
```R
library(openapi)

var.interval <- 60 # numeric | The heartbeat interval in seconds, but not less than 10

#Signals the Websocket connection to send and request heartbeats. Heartbeats can be used to detect stale connections. When heartbeats have been set up, the API server will send `heartbeat` messages and `test_request` messages. Your software should respond to `test_request` messages by sending a `/api/v2/public/test` request. If your software fails to do so, the API server will immediately close the connection. If your account is configured to cancel on disconnect, any orders opened over the connection will be cancelled.
api.instance <- SessionManagementApi$new()
# Configure HTTP basic authorization: bearerAuth
api.instance$apiClient$username <- 'TODO_YOUR_USERNAME';
api.instance$apiClient$password <- 'TODO_YOUR_PASSWORD';
result <- api.instance$PublicSetHeartbeatGet(var.interval)
dput(result)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **interval** | **numeric**| The heartbeat interval in seconds, but not less than 10 | 

### Return type

**object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json



