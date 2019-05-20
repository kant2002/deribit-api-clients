# WebsocketOnlyApi

All URIs are relative to *https://www.deribit.com/api/v2*

Method | HTTP request | Description
------------- | ------------- | -------------
[**PrivateDisableCancelOnDisconnectGet**](WebsocketOnlyApi.md#PrivateDisableCancelOnDisconnectGet) | **GET** /private/disable_cancel_on_disconnect | Disable Cancel On Disconnect for the connection. This does not change the default account setting.
[**PrivateEnableCancelOnDisconnectGet**](WebsocketOnlyApi.md#PrivateEnableCancelOnDisconnectGet) | **GET** /private/enable_cancel_on_disconnect | Enable Cancel On Disconnect for the connection. This does not change the default account setting.
[**PrivateLogoutGet**](WebsocketOnlyApi.md#PrivateLogoutGet) | **GET** /private/logout | Gracefully close websocket connection, when COD (Cancel On Disconnect) is enabled orders are not cancelled
[**PrivateSubscribeGet**](WebsocketOnlyApi.md#PrivateSubscribeGet) | **GET** /private/subscribe | Subscribe to one or more channels.
[**PrivateUnsubscribeGet**](WebsocketOnlyApi.md#PrivateUnsubscribeGet) | **GET** /private/unsubscribe | Unsubscribe from one or more channels.
[**PublicDisableHeartbeatGet**](WebsocketOnlyApi.md#PublicDisableHeartbeatGet) | **GET** /public/disable_heartbeat | Stop sending heartbeat messages.
[**PublicHelloGet**](WebsocketOnlyApi.md#PublicHelloGet) | **GET** /public/hello | Method used to introduce the client software connected to Deribit platform over websocket. Provided data may have an impact on the maintained connection and will be collected for internal statistical purposes. In response, Deribit will also introduce itself.
[**PublicSetHeartbeatGet**](WebsocketOnlyApi.md#PublicSetHeartbeatGet) | **GET** /public/set_heartbeat | Signals the Websocket connection to send and request heartbeats. Heartbeats can be used to detect stale connections. When heartbeats have been set up, the API server will send &#x60;heartbeat&#x60; messages and &#x60;test_request&#x60; messages. Your software should respond to &#x60;test_request&#x60; messages by sending a &#x60;/api/v2/public/test&#x60; request. If your software fails to do so, the API server will immediately close the connection. If your account is configured to cancel on disconnect, any orders opened over the connection will be cancelled.
[**PublicSubscribeGet**](WebsocketOnlyApi.md#PublicSubscribeGet) | **GET** /public/subscribe | Subscribe to one or more channels.
[**PublicUnsubscribeGet**](WebsocketOnlyApi.md#PublicUnsubscribeGet) | **GET** /public/unsubscribe | Unsubscribe from one or more channels.


# **PrivateDisableCancelOnDisconnectGet**
> object PrivateDisableCancelOnDisconnectGet()

Disable Cancel On Disconnect for the connection. This does not change the default account setting.

### Example
```R
library(openapi)


#Disable Cancel On Disconnect for the connection. This does not change the default account setting.
api.instance <- WebsocketOnlyApi$new()
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
api.instance <- WebsocketOnlyApi$new()
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



# **PrivateLogoutGet**
> PrivateLogoutGet()

Gracefully close websocket connection, when COD (Cancel On Disconnect) is enabled orders are not cancelled

### Example
```R
library(openapi)


#Gracefully close websocket connection, when COD (Cancel On Disconnect) is enabled orders are not cancelled
api.instance <- WebsocketOnlyApi$new()
# Configure HTTP basic authorization: bearerAuth
api.instance$apiClient$username <- 'TODO_YOUR_USERNAME';
api.instance$apiClient$password <- 'TODO_YOUR_PASSWORD';
api.instance$PrivateLogoutGet()
```

### Parameters
This endpoint does not need any parameter.

### Return type

void (empty response body)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json



# **PrivateSubscribeGet**
> object PrivateSubscribeGet(channels)

Subscribe to one or more channels.

Subscribe to one or more channels.  The name of the channel determines what information will be provided, and in what form. 

### Example
```R
library(openapi)

var.channels <- list("inner_example") # character | A list of channels to subscribe to.

#Subscribe to one or more channels.
api.instance <- WebsocketOnlyApi$new()
# Configure HTTP basic authorization: bearerAuth
api.instance$apiClient$username <- 'TODO_YOUR_USERNAME';
api.instance$apiClient$password <- 'TODO_YOUR_PASSWORD';
result <- api.instance$PrivateSubscribeGet(var.channels)
dput(result)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **channels** | [**character**](character.md)| A list of channels to subscribe to. | 

### Return type

**object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json



# **PrivateUnsubscribeGet**
> object PrivateUnsubscribeGet(channels)

Unsubscribe from one or more channels.

### Example
```R
library(openapi)

var.channels <- list("inner_example") # character | A list of channels to unsubscribe from.

#Unsubscribe from one or more channels.
api.instance <- WebsocketOnlyApi$new()
# Configure HTTP basic authorization: bearerAuth
api.instance$apiClient$username <- 'TODO_YOUR_USERNAME';
api.instance$apiClient$password <- 'TODO_YOUR_PASSWORD';
result <- api.instance$PrivateUnsubscribeGet(var.channels)
dput(result)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **channels** | [**character**](character.md)| A list of channels to unsubscribe from. | 

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
api.instance <- WebsocketOnlyApi$new()
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



# **PublicHelloGet**
> object PublicHelloGet(client.name, client.version)

Method used to introduce the client software connected to Deribit platform over websocket. Provided data may have an impact on the maintained connection and will be collected for internal statistical purposes. In response, Deribit will also introduce itself.

### Example
```R
library(openapi)

var.client.name <- 'My Trading Software' # character | Client software name
var.client.version <- '1.0.2' # character | Client software version

#Method used to introduce the client software connected to Deribit platform over websocket. Provided data may have an impact on the maintained connection and will be collected for internal statistical purposes. In response, Deribit will also introduce itself.
api.instance <- WebsocketOnlyApi$new()
# Configure HTTP basic authorization: bearerAuth
api.instance$apiClient$username <- 'TODO_YOUR_USERNAME';
api.instance$apiClient$password <- 'TODO_YOUR_PASSWORD';
result <- api.instance$PublicHelloGet(var.client.name, var.client.version)
dput(result)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **client.name** | **character**| Client software name | 
 **client.version** | **character**| Client software version | 

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
api.instance <- WebsocketOnlyApi$new()
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



# **PublicSubscribeGet**
> object PublicSubscribeGet(channels)

Subscribe to one or more channels.

Subscribe to one or more channels.  This is the same method as [/private/subscribe](#private_subscribe), but it can only be used for 'public' channels. 

### Example
```R
library(openapi)

var.channels <- list("inner_example") # character | A list of channels to subscribe to.

#Subscribe to one or more channels.
api.instance <- WebsocketOnlyApi$new()
# Configure HTTP basic authorization: bearerAuth
api.instance$apiClient$username <- 'TODO_YOUR_USERNAME';
api.instance$apiClient$password <- 'TODO_YOUR_PASSWORD';
result <- api.instance$PublicSubscribeGet(var.channels)
dput(result)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **channels** | [**character**](character.md)| A list of channels to subscribe to. | 

### Return type

**object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json



# **PublicUnsubscribeGet**
> object PublicUnsubscribeGet(channels)

Unsubscribe from one or more channels.

### Example
```R
library(openapi)

var.channels <- list("inner_example") # character | A list of channels to unsubscribe from.

#Unsubscribe from one or more channels.
api.instance <- WebsocketOnlyApi$new()
# Configure HTTP basic authorization: bearerAuth
api.instance$apiClient$username <- 'TODO_YOUR_USERNAME';
api.instance$apiClient$password <- 'TODO_YOUR_PASSWORD';
result <- api.instance$PublicUnsubscribeGet(var.channels)
dput(result)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **channels** | [**character**](character.md)| A list of channels to unsubscribe from. | 

### Return type

**object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json



