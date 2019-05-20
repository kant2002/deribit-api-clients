# OpenapiClient::SubscriptionManagementApi

All URIs are relative to *https://www.deribit.com/api/v2*

Method | HTTP request | Description
------------- | ------------- | -------------
[**private_subscribe_get**](SubscriptionManagementApi.md#private_subscribe_get) | **GET** /private/subscribe | Subscribe to one or more channels.
[**private_unsubscribe_get**](SubscriptionManagementApi.md#private_unsubscribe_get) | **GET** /private/unsubscribe | Unsubscribe from one or more channels.
[**public_subscribe_get**](SubscriptionManagementApi.md#public_subscribe_get) | **GET** /public/subscribe | Subscribe to one or more channels.
[**public_unsubscribe_get**](SubscriptionManagementApi.md#public_unsubscribe_get) | **GET** /public/unsubscribe | Unsubscribe from one or more channels.



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

api_instance = OpenapiClient::SubscriptionManagementApi.new
channels = ['channels_example'] # Array<String> | A list of channels to subscribe to.

begin
  #Subscribe to one or more channels.
  result = api_instance.private_subscribe_get(channels)
  p result
rescue OpenapiClient::ApiError => e
  puts "Exception when calling SubscriptionManagementApi->private_subscribe_get: #{e}"
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

api_instance = OpenapiClient::SubscriptionManagementApi.new
channels = ['channels_example'] # Array<String> | A list of channels to unsubscribe from.

begin
  #Unsubscribe from one or more channels.
  result = api_instance.private_unsubscribe_get(channels)
  p result
rescue OpenapiClient::ApiError => e
  puts "Exception when calling SubscriptionManagementApi->private_unsubscribe_get: #{e}"
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

api_instance = OpenapiClient::SubscriptionManagementApi.new
channels = ['channels_example'] # Array<String> | A list of channels to subscribe to.

begin
  #Subscribe to one or more channels.
  result = api_instance.public_subscribe_get(channels)
  p result
rescue OpenapiClient::ApiError => e
  puts "Exception when calling SubscriptionManagementApi->public_subscribe_get: #{e}"
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

api_instance = OpenapiClient::SubscriptionManagementApi.new
channels = ['channels_example'] # Array<String> | A list of channels to unsubscribe from.

begin
  #Unsubscribe from one or more channels.
  result = api_instance.public_unsubscribe_get(channels)
  p result
rescue OpenapiClient::ApiError => e
  puts "Exception when calling SubscriptionManagementApi->public_unsubscribe_get: #{e}"
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

