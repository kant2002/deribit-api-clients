# OpenapiClient::WebsocketOnlyApi

All URIs are relative to *https://www.deribit.com/api/v2*

Method | HTTP request | Description
------------- | ------------- | -------------
[**private_disable_cancel_on_disconnect_get**](WebsocketOnlyApi.md#private_disable_cancel_on_disconnect_get) | **GET** /private/disable_cancel_on_disconnect | Disable Cancel On Disconnect for the connection. This does not change the default account setting.
[**private_enable_cancel_on_disconnect_get**](WebsocketOnlyApi.md#private_enable_cancel_on_disconnect_get) | **GET** /private/enable_cancel_on_disconnect | Enable Cancel On Disconnect for the connection. This does not change the default account setting.
[**private_logout_get**](WebsocketOnlyApi.md#private_logout_get) | **GET** /private/logout | Gracefully close websocket connection, when COD (Cancel On Disconnect) is enabled orders are not cancelled
[**private_subscribe_get**](WebsocketOnlyApi.md#private_subscribe_get) | **GET** /private/subscribe | Subscribe to one or more channels.
[**private_unsubscribe_get**](WebsocketOnlyApi.md#private_unsubscribe_get) | **GET** /private/unsubscribe | Unsubscribe from one or more channels.
[**public_disable_heartbeat_get**](WebsocketOnlyApi.md#public_disable_heartbeat_get) | **GET** /public/disable_heartbeat | Stop sending heartbeat messages.
[**public_hello_get**](WebsocketOnlyApi.md#public_hello_get) | **GET** /public/hello | Method used to introduce the client software connected to Deribit platform over websocket. Provided data may have an impact on the maintained connection and will be collected for internal statistical purposes. In response, Deribit will also introduce itself.
[**public_set_heartbeat_get**](WebsocketOnlyApi.md#public_set_heartbeat_get) | **GET** /public/set_heartbeat | Signals the Websocket connection to send and request heartbeats. Heartbeats can be used to detect stale connections. When heartbeats have been set up, the API server will send &#x60;heartbeat&#x60; messages and &#x60;test_request&#x60; messages. Your software should respond to &#x60;test_request&#x60; messages by sending a &#x60;/api/v2/public/test&#x60; request. If your software fails to do so, the API server will immediately close the connection. If your account is configured to cancel on disconnect, any orders opened over the connection will be cancelled.
[**public_subscribe_get**](WebsocketOnlyApi.md#public_subscribe_get) | **GET** /public/subscribe | Subscribe to one or more channels.
[**public_unsubscribe_get**](WebsocketOnlyApi.md#public_unsubscribe_get) | **GET** /public/unsubscribe | Unsubscribe from one or more channels.



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

api_instance = OpenapiClient::WebsocketOnlyApi.new

begin
  #Disable Cancel On Disconnect for the connection. This does not change the default account setting.
  result = api_instance.private_disable_cancel_on_disconnect_get
  p result
rescue OpenapiClient::ApiError => e
  puts "Exception when calling WebsocketOnlyApi->private_disable_cancel_on_disconnect_get: #{e}"
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

api_instance = OpenapiClient::WebsocketOnlyApi.new

begin
  #Enable Cancel On Disconnect for the connection. This does not change the default account setting.
  result = api_instance.private_enable_cancel_on_disconnect_get
  p result
rescue OpenapiClient::ApiError => e
  puts "Exception when calling WebsocketOnlyApi->private_enable_cancel_on_disconnect_get: #{e}"
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


## private_logout_get

> private_logout_get

Gracefully close websocket connection, when COD (Cancel On Disconnect) is enabled orders are not cancelled

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

api_instance = OpenapiClient::WebsocketOnlyApi.new

begin
  #Gracefully close websocket connection, when COD (Cancel On Disconnect) is enabled orders are not cancelled
  api_instance.private_logout_get
rescue OpenapiClient::ApiError => e
  puts "Exception when calling WebsocketOnlyApi->private_logout_get: #{e}"
end
```

### Parameters

This endpoint does not need any parameter.

### Return type

nil (empty response body)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## private_subscribe_get

> Object private_subscribe_get(channels)

Subscribe to one or more channels.

Subscribe to one or more channels.  The name of the channel determines what information will be provided, and in what form. 

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

api_instance = OpenapiClient::WebsocketOnlyApi.new
channels = ['channels_example'] # Array<String> | A list of channels to subscribe to.

begin
  #Subscribe to one or more channels.
  result = api_instance.private_subscribe_get(channels)
  p result
rescue OpenapiClient::ApiError => e
  puts "Exception when calling WebsocketOnlyApi->private_subscribe_get: #{e}"
end
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **channels** | [**Array&lt;String&gt;**](String.md)| A list of channels to subscribe to. | 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## private_unsubscribe_get

> Object private_unsubscribe_get(channels)

Unsubscribe from one or more channels.

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

api_instance = OpenapiClient::WebsocketOnlyApi.new
channels = ['channels_example'] # Array<String> | A list of channels to unsubscribe from.

begin
  #Unsubscribe from one or more channels.
  result = api_instance.private_unsubscribe_get(channels)
  p result
rescue OpenapiClient::ApiError => e
  puts "Exception when calling WebsocketOnlyApi->private_unsubscribe_get: #{e}"
end
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **channels** | [**Array&lt;String&gt;**](String.md)| A list of channels to unsubscribe from. | 

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

api_instance = OpenapiClient::WebsocketOnlyApi.new

begin
  #Stop sending heartbeat messages.
  result = api_instance.public_disable_heartbeat_get
  p result
rescue OpenapiClient::ApiError => e
  puts "Exception when calling WebsocketOnlyApi->public_disable_heartbeat_get: #{e}"
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


## public_hello_get

> Object public_hello_get(client_name, client_version)

Method used to introduce the client software connected to Deribit platform over websocket. Provided data may have an impact on the maintained connection and will be collected for internal statistical purposes. In response, Deribit will also introduce itself.

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

api_instance = OpenapiClient::WebsocketOnlyApi.new
client_name = 'My Trading Software' # String | Client software name
client_version = '1.0.2' # String | Client software version

begin
  #Method used to introduce the client software connected to Deribit platform over websocket. Provided data may have an impact on the maintained connection and will be collected for internal statistical purposes. In response, Deribit will also introduce itself.
  result = api_instance.public_hello_get(client_name, client_version)
  p result
rescue OpenapiClient::ApiError => e
  puts "Exception when calling WebsocketOnlyApi->public_hello_get: #{e}"
end
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **client_name** | **String**| Client software name | 
 **client_version** | **String**| Client software version | 

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

api_instance = OpenapiClient::WebsocketOnlyApi.new
interval = 60 # Float | The heartbeat interval in seconds, but not less than 10

begin
  #Signals the Websocket connection to send and request heartbeats. Heartbeats can be used to detect stale connections. When heartbeats have been set up, the API server will send `heartbeat` messages and `test_request` messages. Your software should respond to `test_request` messages by sending a `/api/v2/public/test` request. If your software fails to do so, the API server will immediately close the connection. If your account is configured to cancel on disconnect, any orders opened over the connection will be cancelled.
  result = api_instance.public_set_heartbeat_get(interval)
  p result
rescue OpenapiClient::ApiError => e
  puts "Exception when calling WebsocketOnlyApi->public_set_heartbeat_get: #{e}"
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


## public_subscribe_get

> Object public_subscribe_get(channels)

Subscribe to one or more channels.

Subscribe to one or more channels.  This is the same method as [/private/subscribe](#private_subscribe), but it can only be used for 'public' channels. 

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

api_instance = OpenapiClient::WebsocketOnlyApi.new
channels = ['channels_example'] # Array<String> | A list of channels to subscribe to.

begin
  #Subscribe to one or more channels.
  result = api_instance.public_subscribe_get(channels)
  p result
rescue OpenapiClient::ApiError => e
  puts "Exception when calling WebsocketOnlyApi->public_subscribe_get: #{e}"
end
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **channels** | [**Array&lt;String&gt;**](String.md)| A list of channels to subscribe to. | 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## public_unsubscribe_get

> Object public_unsubscribe_get(channels)

Unsubscribe from one or more channels.

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

api_instance = OpenapiClient::WebsocketOnlyApi.new
channels = ['channels_example'] # Array<String> | A list of channels to unsubscribe from.

begin
  #Unsubscribe from one or more channels.
  result = api_instance.public_unsubscribe_get(channels)
  p result
rescue OpenapiClient::ApiError => e
  puts "Exception when calling WebsocketOnlyApi->public_unsubscribe_get: #{e}"
end
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **channels** | [**Array&lt;String&gt;**](String.md)| A list of channels to unsubscribe from. | 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

