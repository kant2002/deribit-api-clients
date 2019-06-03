# OpenapiClient::AccountManagementApi

All URIs are relative to *https://www.deribit.com/api/v2*

Method | HTTP request | Description
------------- | ------------- | -------------
[**private_change_subaccount_name_get**](AccountManagementApi.md#private_change_subaccount_name_get) | **GET** /private/change_subaccount_name | Change the user name for a subaccount
[**private_create_subaccount_get**](AccountManagementApi.md#private_create_subaccount_get) | **GET** /private/create_subaccount | Create a new subaccount
[**private_disable_tfa_for_subaccount_get**](AccountManagementApi.md#private_disable_tfa_for_subaccount_get) | **GET** /private/disable_tfa_for_subaccount | Disable two factor authentication for a subaccount.
[**private_get_account_summary_get**](AccountManagementApi.md#private_get_account_summary_get) | **GET** /private/get_account_summary | Retrieves user account summary.
[**private_get_email_language_get**](AccountManagementApi.md#private_get_email_language_get) | **GET** /private/get_email_language | Retrieves the language to be used for emails.
[**private_get_new_announcements_get**](AccountManagementApi.md#private_get_new_announcements_get) | **GET** /private/get_new_announcements | Retrieves announcements that have not been marked read by the user.
[**private_get_position_get**](AccountManagementApi.md#private_get_position_get) | **GET** /private/get_position | Retrieve user position.
[**private_get_positions_get**](AccountManagementApi.md#private_get_positions_get) | **GET** /private/get_positions | Retrieve user positions.
[**private_get_subaccounts_get**](AccountManagementApi.md#private_get_subaccounts_get) | **GET** /private/get_subaccounts | Get information about subaccounts
[**private_set_announcement_as_read_get**](AccountManagementApi.md#private_set_announcement_as_read_get) | **GET** /private/set_announcement_as_read | Marks an announcement as read, so it will not be shown in &#x60;get_new_announcements&#x60;.
[**private_set_email_for_subaccount_get**](AccountManagementApi.md#private_set_email_for_subaccount_get) | **GET** /private/set_email_for_subaccount | Assign an email address to a subaccount. User will receive an email with confirmation link.
[**private_set_email_language_get**](AccountManagementApi.md#private_set_email_language_get) | **GET** /private/set_email_language | Changes the language to be used for emails.
[**private_set_password_for_subaccount_get**](AccountManagementApi.md#private_set_password_for_subaccount_get) | **GET** /private/set_password_for_subaccount | Set the password for the subaccount
[**private_toggle_notifications_from_subaccount_get**](AccountManagementApi.md#private_toggle_notifications_from_subaccount_get) | **GET** /private/toggle_notifications_from_subaccount | Enable or disable sending of notifications for the subaccount.
[**private_toggle_subaccount_login_get**](AccountManagementApi.md#private_toggle_subaccount_login_get) | **GET** /private/toggle_subaccount_login | Enable or disable login for a subaccount. If login is disabled and a session for the subaccount exists, this session will be terminated.
[**public_get_announcements_get**](AccountManagementApi.md#public_get_announcements_get) | **GET** /public/get_announcements | Retrieves announcements from the last 30 days.



## private_change_subaccount_name_get

> Object private_change_subaccount_name_get(sid, name)

Change the user name for a subaccount

### Example

```ruby
# load the gem
require 'openapi_client'
# setup authorization
OpenapiClient.configure do |config|
  # Configure Bearer authorization (Auth. Token): bearerAuth
  config.access_token = 'YOUR_BEARER_TOKEN'
end

api_instance = OpenapiClient::AccountManagementApi.new
sid = 56 # Integer | The user id for the subaccount
name = 'newUserName' # String | The new user name

begin
  #Change the user name for a subaccount
  result = api_instance.private_change_subaccount_name_get(sid, name)
  p result
rescue OpenapiClient::ApiError => e
  puts "Exception when calling AccountManagementApi->private_change_subaccount_name_get: #{e}"
end
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **sid** | **Integer**| The user id for the subaccount | 
 **name** | **String**| The new user name | 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## private_create_subaccount_get

> Object private_create_subaccount_get

Create a new subaccount

### Example

```ruby
# load the gem
require 'openapi_client'
# setup authorization
OpenapiClient.configure do |config|
  # Configure Bearer authorization (Auth. Token): bearerAuth
  config.access_token = 'YOUR_BEARER_TOKEN'
end

api_instance = OpenapiClient::AccountManagementApi.new

begin
  #Create a new subaccount
  result = api_instance.private_create_subaccount_get
  p result
rescue OpenapiClient::ApiError => e
  puts "Exception when calling AccountManagementApi->private_create_subaccount_get: #{e}"
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


## private_disable_tfa_for_subaccount_get

> Object private_disable_tfa_for_subaccount_get(sid)

Disable two factor authentication for a subaccount.

### Example

```ruby
# load the gem
require 'openapi_client'
# setup authorization
OpenapiClient.configure do |config|
  # Configure Bearer authorization (Auth. Token): bearerAuth
  config.access_token = 'YOUR_BEARER_TOKEN'
end

api_instance = OpenapiClient::AccountManagementApi.new
sid = 56 # Integer | The user id for the subaccount

begin
  #Disable two factor authentication for a subaccount.
  result = api_instance.private_disable_tfa_for_subaccount_get(sid)
  p result
rescue OpenapiClient::ApiError => e
  puts "Exception when calling AccountManagementApi->private_disable_tfa_for_subaccount_get: #{e}"
end
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **sid** | **Integer**| The user id for the subaccount | 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## private_get_account_summary_get

> Object private_get_account_summary_get(currency, opts)

Retrieves user account summary.

### Example

```ruby
# load the gem
require 'openapi_client'
# setup authorization
OpenapiClient.configure do |config|
  # Configure Bearer authorization (Auth. Token): bearerAuth
  config.access_token = 'YOUR_BEARER_TOKEN'
end

api_instance = OpenapiClient::AccountManagementApi.new
currency = 'currency_example' # String | The currency symbol
opts = {
  extended: false # Boolean | Include additional fields
}

begin
  #Retrieves user account summary.
  result = api_instance.private_get_account_summary_get(currency, opts)
  p result
rescue OpenapiClient::ApiError => e
  puts "Exception when calling AccountManagementApi->private_get_account_summary_get: #{e}"
end
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **String**| The currency symbol | 
 **extended** | **Boolean**| Include additional fields | [optional] 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## private_get_email_language_get

> Object private_get_email_language_get

Retrieves the language to be used for emails.

### Example

```ruby
# load the gem
require 'openapi_client'
# setup authorization
OpenapiClient.configure do |config|
  # Configure Bearer authorization (Auth. Token): bearerAuth
  config.access_token = 'YOUR_BEARER_TOKEN'
end

api_instance = OpenapiClient::AccountManagementApi.new

begin
  #Retrieves the language to be used for emails.
  result = api_instance.private_get_email_language_get
  p result
rescue OpenapiClient::ApiError => e
  puts "Exception when calling AccountManagementApi->private_get_email_language_get: #{e}"
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


## private_get_new_announcements_get

> Object private_get_new_announcements_get

Retrieves announcements that have not been marked read by the user.

### Example

```ruby
# load the gem
require 'openapi_client'
# setup authorization
OpenapiClient.configure do |config|
  # Configure Bearer authorization (Auth. Token): bearerAuth
  config.access_token = 'YOUR_BEARER_TOKEN'
end

api_instance = OpenapiClient::AccountManagementApi.new

begin
  #Retrieves announcements that have not been marked read by the user.
  result = api_instance.private_get_new_announcements_get
  p result
rescue OpenapiClient::ApiError => e
  puts "Exception when calling AccountManagementApi->private_get_new_announcements_get: #{e}"
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


## private_get_position_get

> Object private_get_position_get(instrument_name)

Retrieve user position.

### Example

```ruby
# load the gem
require 'openapi_client'
# setup authorization
OpenapiClient.configure do |config|
  # Configure Bearer authorization (Auth. Token): bearerAuth
  config.access_token = 'YOUR_BEARER_TOKEN'
end

api_instance = OpenapiClient::AccountManagementApi.new
instrument_name = 'BTC-PERPETUAL' # String | Instrument name

begin
  #Retrieve user position.
  result = api_instance.private_get_position_get(instrument_name)
  p result
rescue OpenapiClient::ApiError => e
  puts "Exception when calling AccountManagementApi->private_get_position_get: #{e}"
end
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrument_name** | **String**| Instrument name | 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## private_get_positions_get

> Object private_get_positions_get(currency, opts)

Retrieve user positions.

### Example

```ruby
# load the gem
require 'openapi_client'
# setup authorization
OpenapiClient.configure do |config|
  # Configure Bearer authorization (Auth. Token): bearerAuth
  config.access_token = 'YOUR_BEARER_TOKEN'
end

api_instance = OpenapiClient::AccountManagementApi.new
currency = 'currency_example' # String | 
opts = {
  kind: 'kind_example' # String | Kind filter on positions
}

begin
  #Retrieve user positions.
  result = api_instance.private_get_positions_get(currency, opts)
  p result
rescue OpenapiClient::ApiError => e
  puts "Exception when calling AccountManagementApi->private_get_positions_get: #{e}"
end
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **String**|  | 
 **kind** | **String**| Kind filter on positions | [optional] 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## private_get_subaccounts_get

> Object private_get_subaccounts_get(opts)

Get information about subaccounts

### Example

```ruby
# load the gem
require 'openapi_client'
# setup authorization
OpenapiClient.configure do |config|
  # Configure Bearer authorization (Auth. Token): bearerAuth
  config.access_token = 'YOUR_BEARER_TOKEN'
end

api_instance = OpenapiClient::AccountManagementApi.new
opts = {
  with_portfolio: true # Boolean | 
}

begin
  #Get information about subaccounts
  result = api_instance.private_get_subaccounts_get(opts)
  p result
rescue OpenapiClient::ApiError => e
  puts "Exception when calling AccountManagementApi->private_get_subaccounts_get: #{e}"
end
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **with_portfolio** | **Boolean**|  | [optional] 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## private_set_announcement_as_read_get

> Object private_set_announcement_as_read_get(announcement_id)

Marks an announcement as read, so it will not be shown in `get_new_announcements`.

### Example

```ruby
# load the gem
require 'openapi_client'
# setup authorization
OpenapiClient.configure do |config|
  # Configure Bearer authorization (Auth. Token): bearerAuth
  config.access_token = 'YOUR_BEARER_TOKEN'
end

api_instance = OpenapiClient::AccountManagementApi.new
announcement_id = 3.4 # Float | the ID of the announcement

begin
  #Marks an announcement as read, so it will not be shown in `get_new_announcements`.
  result = api_instance.private_set_announcement_as_read_get(announcement_id)
  p result
rescue OpenapiClient::ApiError => e
  puts "Exception when calling AccountManagementApi->private_set_announcement_as_read_get: #{e}"
end
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **announcement_id** | **Float**| the ID of the announcement | 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## private_set_email_for_subaccount_get

> Object private_set_email_for_subaccount_get(sid, email)

Assign an email address to a subaccount. User will receive an email with confirmation link.

### Example

```ruby
# load the gem
require 'openapi_client'
# setup authorization
OpenapiClient.configure do |config|
  # Configure Bearer authorization (Auth. Token): bearerAuth
  config.access_token = 'YOUR_BEARER_TOKEN'
end

api_instance = OpenapiClient::AccountManagementApi.new
sid = 56 # Integer | The user id for the subaccount
email = 'subaccount_1@email.com' # String | The email address for the subaccount

begin
  #Assign an email address to a subaccount. User will receive an email with confirmation link.
  result = api_instance.private_set_email_for_subaccount_get(sid, email)
  p result
rescue OpenapiClient::ApiError => e
  puts "Exception when calling AccountManagementApi->private_set_email_for_subaccount_get: #{e}"
end
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **sid** | **Integer**| The user id for the subaccount | 
 **email** | **String**| The email address for the subaccount | 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## private_set_email_language_get

> Object private_set_email_language_get(language)

Changes the language to be used for emails.

### Example

```ruby
# load the gem
require 'openapi_client'
# setup authorization
OpenapiClient.configure do |config|
  # Configure Bearer authorization (Auth. Token): bearerAuth
  config.access_token = 'YOUR_BEARER_TOKEN'
end

api_instance = OpenapiClient::AccountManagementApi.new
language = 'en' # String | The abbreviated language name. Valid values include `\"en\"`, `\"ko\"`, `\"zh\"`

begin
  #Changes the language to be used for emails.
  result = api_instance.private_set_email_language_get(language)
  p result
rescue OpenapiClient::ApiError => e
  puts "Exception when calling AccountManagementApi->private_set_email_language_get: #{e}"
end
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **language** | **String**| The abbreviated language name. Valid values include &#x60;\&quot;en\&quot;&#x60;, &#x60;\&quot;ko\&quot;&#x60;, &#x60;\&quot;zh\&quot;&#x60; | 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## private_set_password_for_subaccount_get

> Object private_set_password_for_subaccount_get(sid, password)

Set the password for the subaccount

### Example

```ruby
# load the gem
require 'openapi_client'
# setup authorization
OpenapiClient.configure do |config|
  # Configure Bearer authorization (Auth. Token): bearerAuth
  config.access_token = 'YOUR_BEARER_TOKEN'
end

api_instance = OpenapiClient::AccountManagementApi.new
sid = 56 # Integer | The user id for the subaccount
password = 'password_example' # String | The password for the subaccount

begin
  #Set the password for the subaccount
  result = api_instance.private_set_password_for_subaccount_get(sid, password)
  p result
rescue OpenapiClient::ApiError => e
  puts "Exception when calling AccountManagementApi->private_set_password_for_subaccount_get: #{e}"
end
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **sid** | **Integer**| The user id for the subaccount | 
 **password** | **String**| The password for the subaccount | 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## private_toggle_notifications_from_subaccount_get

> Object private_toggle_notifications_from_subaccount_get(sid, state)

Enable or disable sending of notifications for the subaccount.

### Example

```ruby
# load the gem
require 'openapi_client'
# setup authorization
OpenapiClient.configure do |config|
  # Configure Bearer authorization (Auth. Token): bearerAuth
  config.access_token = 'YOUR_BEARER_TOKEN'
end

api_instance = OpenapiClient::AccountManagementApi.new
sid = 56 # Integer | The user id for the subaccount
state = true # Boolean | enable (`true`) or disable (`false`) notifications

begin
  #Enable or disable sending of notifications for the subaccount.
  result = api_instance.private_toggle_notifications_from_subaccount_get(sid, state)
  p result
rescue OpenapiClient::ApiError => e
  puts "Exception when calling AccountManagementApi->private_toggle_notifications_from_subaccount_get: #{e}"
end
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **sid** | **Integer**| The user id for the subaccount | 
 **state** | **Boolean**| enable (&#x60;true&#x60;) or disable (&#x60;false&#x60;) notifications | 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## private_toggle_subaccount_login_get

> Object private_toggle_subaccount_login_get(sid, state)

Enable or disable login for a subaccount. If login is disabled and a session for the subaccount exists, this session will be terminated.

### Example

```ruby
# load the gem
require 'openapi_client'
# setup authorization
OpenapiClient.configure do |config|
  # Configure Bearer authorization (Auth. Token): bearerAuth
  config.access_token = 'YOUR_BEARER_TOKEN'
end

api_instance = OpenapiClient::AccountManagementApi.new
sid = 56 # Integer | The user id for the subaccount
state = 'state_example' # String | enable or disable login.

begin
  #Enable or disable login for a subaccount. If login is disabled and a session for the subaccount exists, this session will be terminated.
  result = api_instance.private_toggle_subaccount_login_get(sid, state)
  p result
rescue OpenapiClient::ApiError => e
  puts "Exception when calling AccountManagementApi->private_toggle_subaccount_login_get: #{e}"
end
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **sid** | **Integer**| The user id for the subaccount | 
 **state** | **String**| enable or disable login. | 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## public_get_announcements_get

> Object public_get_announcements_get

Retrieves announcements from the last 30 days.

### Example

```ruby
# load the gem
require 'openapi_client'
# setup authorization
OpenapiClient.configure do |config|
  # Configure Bearer authorization (Auth. Token): bearerAuth
  config.access_token = 'YOUR_BEARER_TOKEN'
end

api_instance = OpenapiClient::AccountManagementApi.new

begin
  #Retrieves announcements from the last 30 days.
  result = api_instance.public_get_announcements_get
  p result
rescue OpenapiClient::ApiError => e
  puts "Exception when calling AccountManagementApi->public_get_announcements_get: #{e}"
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

