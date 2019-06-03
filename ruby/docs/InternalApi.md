# OpenapiClient::InternalApi

All URIs are relative to *https://www.deribit.com/api/v2*

Method | HTTP request | Description
------------- | ------------- | -------------
[**private_add_to_address_book_get**](InternalApi.md#private_add_to_address_book_get) | **GET** /private/add_to_address_book | Adds new entry to address book of given type
[**private_disable_tfa_with_recovery_code_get**](InternalApi.md#private_disable_tfa_with_recovery_code_get) | **GET** /private/disable_tfa_with_recovery_code | Disables TFA with one time recovery code
[**private_get_address_book_get**](InternalApi.md#private_get_address_book_get) | **GET** /private/get_address_book | Retrieves address book of given type
[**private_remove_from_address_book_get**](InternalApi.md#private_remove_from_address_book_get) | **GET** /private/remove_from_address_book | Adds new entry to address book of given type
[**private_submit_transfer_to_subaccount_get**](InternalApi.md#private_submit_transfer_to_subaccount_get) | **GET** /private/submit_transfer_to_subaccount | Transfer funds to a subaccount.
[**private_submit_transfer_to_user_get**](InternalApi.md#private_submit_transfer_to_user_get) | **GET** /private/submit_transfer_to_user | Transfer funds to a another user.
[**private_toggle_deposit_address_creation_get**](InternalApi.md#private_toggle_deposit_address_creation_get) | **GET** /private/toggle_deposit_address_creation | Enable or disable deposit address creation
[**public_get_footer_get**](InternalApi.md#public_get_footer_get) | **GET** /public/get_footer | Get information to be displayed in the footer of the website.
[**public_get_option_mark_prices_get**](InternalApi.md#public_get_option_mark_prices_get) | **GET** /public/get_option_mark_prices | Retrives market prices and its implied volatility of options instruments
[**public_validate_field_get**](InternalApi.md#public_validate_field_get) | **GET** /public/validate_field | Method used to introduce the client software connected to Deribit platform over websocket. Provided data may have an impact on the maintained connection and will be collected for internal statistical purposes. In response, Deribit will also introduce itself.



## private_add_to_address_book_get

> Object private_add_to_address_book_get(currency, type, address, name, opts)

Adds new entry to address book of given type

### Example

```ruby
# load the gem
require 'openapi_client'
# setup authorization
OpenapiClient.configure do |config|
  # Configure Bearer authorization (Auth. Token): bearerAuth
  config.access_token = 'YOUR_BEARER_TOKEN'
end

api_instance = OpenapiClient::InternalApi.new
currency = 'currency_example' # String | The currency symbol
type = 'type_example' # String | Address book type
address = 'address_example' # String | Address in currency format, it must be in address book
name = 'Main address' # String | Name of address book entry
opts = {
  tfa: 'tfa_example' # String | TFA code, required when TFA is enabled for current account
}

begin
  #Adds new entry to address book of given type
  result = api_instance.private_add_to_address_book_get(currency, type, address, name, opts)
  p result
rescue OpenapiClient::ApiError => e
  puts "Exception when calling InternalApi->private_add_to_address_book_get: #{e}"
end
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **String**| The currency symbol | 
 **type** | **String**| Address book type | 
 **address** | **String**| Address in currency format, it must be in address book | 
 **name** | **String**| Name of address book entry | 
 **tfa** | **String**| TFA code, required when TFA is enabled for current account | [optional] 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## private_disable_tfa_with_recovery_code_get

> Object private_disable_tfa_with_recovery_code_get(password, code)

Disables TFA with one time recovery code

### Example

```ruby
# load the gem
require 'openapi_client'
# setup authorization
OpenapiClient.configure do |config|
  # Configure Bearer authorization (Auth. Token): bearerAuth
  config.access_token = 'YOUR_BEARER_TOKEN'
end

api_instance = OpenapiClient::InternalApi.new
password = 'password_example' # String | The password for the subaccount
code = 'code_example' # String | One time recovery code

begin
  #Disables TFA with one time recovery code
  result = api_instance.private_disable_tfa_with_recovery_code_get(password, code)
  p result
rescue OpenapiClient::ApiError => e
  puts "Exception when calling InternalApi->private_disable_tfa_with_recovery_code_get: #{e}"
end
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **password** | **String**| The password for the subaccount | 
 **code** | **String**| One time recovery code | 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## private_get_address_book_get

> Object private_get_address_book_get(currency, type)

Retrieves address book of given type

### Example

```ruby
# load the gem
require 'openapi_client'
# setup authorization
OpenapiClient.configure do |config|
  # Configure Bearer authorization (Auth. Token): bearerAuth
  config.access_token = 'YOUR_BEARER_TOKEN'
end

api_instance = OpenapiClient::InternalApi.new
currency = 'currency_example' # String | The currency symbol
type = 'type_example' # String | Address book type

begin
  #Retrieves address book of given type
  result = api_instance.private_get_address_book_get(currency, type)
  p result
rescue OpenapiClient::ApiError => e
  puts "Exception when calling InternalApi->private_get_address_book_get: #{e}"
end
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **String**| The currency symbol | 
 **type** | **String**| Address book type | 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## private_remove_from_address_book_get

> Object private_remove_from_address_book_get(currency, type, address, opts)

Adds new entry to address book of given type

### Example

```ruby
# load the gem
require 'openapi_client'
# setup authorization
OpenapiClient.configure do |config|
  # Configure Bearer authorization (Auth. Token): bearerAuth
  config.access_token = 'YOUR_BEARER_TOKEN'
end

api_instance = OpenapiClient::InternalApi.new
currency = 'currency_example' # String | The currency symbol
type = 'type_example' # String | Address book type
address = 'address_example' # String | Address in currency format, it must be in address book
opts = {
  tfa: 'tfa_example' # String | TFA code, required when TFA is enabled for current account
}

begin
  #Adds new entry to address book of given type
  result = api_instance.private_remove_from_address_book_get(currency, type, address, opts)
  p result
rescue OpenapiClient::ApiError => e
  puts "Exception when calling InternalApi->private_remove_from_address_book_get: #{e}"
end
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **String**| The currency symbol | 
 **type** | **String**| Address book type | 
 **address** | **String**| Address in currency format, it must be in address book | 
 **tfa** | **String**| TFA code, required when TFA is enabled for current account | [optional] 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## private_submit_transfer_to_subaccount_get

> Object private_submit_transfer_to_subaccount_get(currency, amount, destination)

Transfer funds to a subaccount.

### Example

```ruby
# load the gem
require 'openapi_client'
# setup authorization
OpenapiClient.configure do |config|
  # Configure Bearer authorization (Auth. Token): bearerAuth
  config.access_token = 'YOUR_BEARER_TOKEN'
end

api_instance = OpenapiClient::InternalApi.new
currency = 'currency_example' # String | The currency symbol
amount = 3.4 # Float | Amount of funds to be transferred
destination = 1 # Integer | Id of destination subaccount

begin
  #Transfer funds to a subaccount.
  result = api_instance.private_submit_transfer_to_subaccount_get(currency, amount, destination)
  p result
rescue OpenapiClient::ApiError => e
  puts "Exception when calling InternalApi->private_submit_transfer_to_subaccount_get: #{e}"
end
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **String**| The currency symbol | 
 **amount** | **Float**| Amount of funds to be transferred | 
 **destination** | **Integer**| Id of destination subaccount | 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## private_submit_transfer_to_user_get

> Object private_submit_transfer_to_user_get(currency, amount, destination, opts)

Transfer funds to a another user.

### Example

```ruby
# load the gem
require 'openapi_client'
# setup authorization
OpenapiClient.configure do |config|
  # Configure Bearer authorization (Auth. Token): bearerAuth
  config.access_token = 'YOUR_BEARER_TOKEN'
end

api_instance = OpenapiClient::InternalApi.new
currency = 'currency_example' # String | The currency symbol
amount = 3.4 # Float | Amount of funds to be transferred
destination = 'destination_example' # String | Destination address from address book
opts = {
  tfa: 'tfa_example' # String | TFA code, required when TFA is enabled for current account
}

begin
  #Transfer funds to a another user.
  result = api_instance.private_submit_transfer_to_user_get(currency, amount, destination, opts)
  p result
rescue OpenapiClient::ApiError => e
  puts "Exception when calling InternalApi->private_submit_transfer_to_user_get: #{e}"
end
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **String**| The currency symbol | 
 **amount** | **Float**| Amount of funds to be transferred | 
 **destination** | **String**| Destination address from address book | 
 **tfa** | **String**| TFA code, required when TFA is enabled for current account | [optional] 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## private_toggle_deposit_address_creation_get

> Object private_toggle_deposit_address_creation_get(currency, state)

Enable or disable deposit address creation

### Example

```ruby
# load the gem
require 'openapi_client'
# setup authorization
OpenapiClient.configure do |config|
  # Configure Bearer authorization (Auth. Token): bearerAuth
  config.access_token = 'YOUR_BEARER_TOKEN'
end

api_instance = OpenapiClient::InternalApi.new
currency = 'currency_example' # String | The currency symbol
state = true # Boolean | 

begin
  #Enable or disable deposit address creation
  result = api_instance.private_toggle_deposit_address_creation_get(currency, state)
  p result
rescue OpenapiClient::ApiError => e
  puts "Exception when calling InternalApi->private_toggle_deposit_address_creation_get: #{e}"
end
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **String**| The currency symbol | 
 **state** | **Boolean**|  | 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## public_get_footer_get

> Object public_get_footer_get

Get information to be displayed in the footer of the website.

### Example

```ruby
# load the gem
require 'openapi_client'
# setup authorization
OpenapiClient.configure do |config|
  # Configure Bearer authorization (Auth. Token): bearerAuth
  config.access_token = 'YOUR_BEARER_TOKEN'
end

api_instance = OpenapiClient::InternalApi.new

begin
  #Get information to be displayed in the footer of the website.
  result = api_instance.public_get_footer_get
  p result
rescue OpenapiClient::ApiError => e
  puts "Exception when calling InternalApi->public_get_footer_get: #{e}"
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


## public_get_option_mark_prices_get

> Object public_get_option_mark_prices_get(currency)

Retrives market prices and its implied volatility of options instruments

### Example

```ruby
# load the gem
require 'openapi_client'
# setup authorization
OpenapiClient.configure do |config|
  # Configure Bearer authorization (Auth. Token): bearerAuth
  config.access_token = 'YOUR_BEARER_TOKEN'
end

api_instance = OpenapiClient::InternalApi.new
currency = 'currency_example' # String | The currency symbol

begin
  #Retrives market prices and its implied volatility of options instruments
  result = api_instance.public_get_option_mark_prices_get(currency)
  p result
rescue OpenapiClient::ApiError => e
  puts "Exception when calling InternalApi->public_get_option_mark_prices_get: #{e}"
end
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **String**| The currency symbol | 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## public_validate_field_get

> Object public_validate_field_get(field, value, opts)

Method used to introduce the client software connected to Deribit platform over websocket. Provided data may have an impact on the maintained connection and will be collected for internal statistical purposes. In response, Deribit will also introduce itself.

### Example

```ruby
# load the gem
require 'openapi_client'
# setup authorization
OpenapiClient.configure do |config|
  # Configure Bearer authorization (Auth. Token): bearerAuth
  config.access_token = 'YOUR_BEARER_TOKEN'
end

api_instance = OpenapiClient::InternalApi.new
field = 'field_example' # String | Name of the field to be validated, examples: postal_code, date_of_birth
value = 'value_example' # String | Value to be checked
opts = {
  value2: 'value2_example' # String | Additional value to be compared with
}

begin
  #Method used to introduce the client software connected to Deribit platform over websocket. Provided data may have an impact on the maintained connection and will be collected for internal statistical purposes. In response, Deribit will also introduce itself.
  result = api_instance.public_validate_field_get(field, value, opts)
  p result
rescue OpenapiClient::ApiError => e
  puts "Exception when calling InternalApi->public_validate_field_get: #{e}"
end
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **field** | **String**| Name of the field to be validated, examples: postal_code, date_of_birth | 
 **value** | **String**| Value to be checked | 
 **value2** | **String**| Additional value to be compared with | [optional] 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

