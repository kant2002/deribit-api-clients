# OpenapiClient::SessionManagementApi

All URIs are relative to *https://www.deribit.com/api/v2*

Method | HTTP request | Description
------------- | ------------- | -------------
[**private_disable_cancel_on_disconnect_get**](SessionManagementApi.md#private_disable_cancel_on_disconnect_get) | **GET** /private/disable_cancel_on_disconnect | Disable Cancel On Disconnect for the connection. This does not change the default account setting.
[**private_enable_cancel_on_disconnect_get**](SessionManagementApi.md#private_enable_cancel_on_disconnect_get) | **GET** /private/enable_cancel_on_disconnect | Enable Cancel On Disconnect for the connection. This does not change the default account setting.
[**public_disable_heartbeat_get**](SessionManagementApi.md#public_disable_heartbeat_get) | **GET** /public/disable_heartbeat | Stop sending heartbeat messages.
[**public_set_heartbeat_get**](SessionManagementApi.md#public_set_heartbeat_get) | **GET** /public/set_heartbeat | Signals the Websocket connection to send and request heartbeats. Heartbeats can be used to detect stale connections. When heartbeats have been set up, the API server will send &#x60;heartbeat&#x60; messages and &#x60;test_request&#x60; messages. Your software should respond to &#x60;test_request&#x60; messages by sending a &#x60;/api/v2/public/test&#x60; request. If your software fails to do so, the API server will immediately close the connection. If your account is configured to cancel on disconnect, any orders opened over the connection will be cancelled.



## private_disable_cancel_on_disconnect_get

> Object private_disable_cancel_on_disconnect_get

Disable Cancel On Disconnect for the connection. This does not change the default account setting.

### Example

```ruby
# load the gem
require 'openapi_client'
# setup authorization
OpenapiClient.configure do |config|
  # Configure HTTP basic authorization: bearerAuth
  config.username = 'YOUR USERNAME'
  config.password = 'YOUR PASSWORD'
end

api_instance = OpenapiClient::SessionManagementApi.new

begin
  #Disable Cancel On Disconnect for the connection. This does not change the default account setting.
  result = api_instance.private_disable_cancel_on_disconnect_get
  p result
rescue OpenapiClient::ApiError => e
  puts "Exception when calling SessionManagementApi->private_disable_cancel_on_disconnect_get: #{e}"
end
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


## private_enable_cancel_on_disconnect_get

> Object private_enable_cancel_on_disconnect_get

Enable Cancel On Disconnect for the connection. This does not change the default account setting.

### Example

```ruby
# load the gem
require 'openapi_client'
# setup authorization
OpenapiClient.configure do |config|
  # Configure HTTP basic authorization: bearerAuth
  config.username = 'YOUR USERNAME'
  config.password = 'YOUR PASSWORD'
end

api_instance = OpenapiClient::SessionManagementApi.new

begin
  #Enable Cancel On Disconnect for the connection. This does not change the default account setting.
  result = api_instance.private_enable_cancel_on_disconnect_get
  p result
rescue OpenapiClient::ApiError => e
  puts "Exception when calling SessionManagementApi->private_enable_cancel_on_disconnect_get: #{e}"
end
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


## public_disable_heartbeat_get

> Object public_disable_heartbeat_get

Stop sending heartbeat messages.

### Example

```ruby
# load the gem
require 'openapi_client'
# setup authorization
OpenapiClient.configure do |config|
  # Configure HTTP basic authorization: bearerAuth
  config.username = 'YOUR USERNAME'
  config.password = 'YOUR PASSWORD'
end

api_instance = OpenapiClient::SessionManagementApi.new

begin
  #Stop sending heartbeat messages.
  result = api_instance.public_disable_heartbeat_get
  p result
rescue OpenapiClient::ApiError => e
  puts "Exception when calling SessionManagementApi->public_disable_heartbeat_get: #{e}"
end
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


## public_set_heartbeat_get

> Object public_set_heartbeat_get(interval)

Signals the Websocket connection to send and request heartbeats. Heartbeats can be used to detect stale connections. When heartbeats have been set up, the API server will send `heartbeat` messages and `test_request` messages. Your software should respond to `test_request` messages by sending a `/api/v2/public/test` request. If your software fails to do so, the API server will immediately close the connection. If your account is configured to cancel on disconnect, any orders opened over the connection will be cancelled.

### Example

```ruby
# load the gem
require 'openapi_client'
# setup authorization
OpenapiClient.configure do |config|
  # Configure HTTP basic authorization: bearerAuth
  config.username = 'YOUR USERNAME'
  config.password = 'YOUR PASSWORD'
end

api_instance = OpenapiClient::SessionManagementApi.new
interval = 60 # Float | The heartbeat interval in seconds, but not less than 10

begin
  #Signals the Websocket connection to send and request heartbeats. Heartbeats can be used to detect stale connections. When heartbeats have been set up, the API server will send `heartbeat` messages and `test_request` messages. Your software should respond to `test_request` messages by sending a `/api/v2/public/test` request. If your software fails to do so, the API server will immediately close the connection. If your account is configured to cancel on disconnect, any orders opened over the connection will be cancelled.
  result = api_instance.public_set_heartbeat_get(interval)
  p result
rescue OpenapiClient::ApiError => e
  puts "Exception when calling SessionManagementApi->public_set_heartbeat_get: #{e}"
end
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **interval** | **Float**| The heartbeat interval in seconds, but not less than 10 | 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

