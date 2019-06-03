# OpenapiClient::WalletApi

All URIs are relative to *https://www.deribit.com/api/v2*

Method | HTTP request | Description
------------- | ------------- | -------------
[**private_add_to_address_book_get**](WalletApi.md#private_add_to_address_book_get) | **GET** /private/add_to_address_book | Adds new entry to address book of given type
[**private_cancel_transfer_by_id_get**](WalletApi.md#private_cancel_transfer_by_id_get) | **GET** /private/cancel_transfer_by_id | Cancel transfer
[**private_cancel_withdrawal_get**](WalletApi.md#private_cancel_withdrawal_get) | **GET** /private/cancel_withdrawal | Cancels withdrawal request
[**private_create_deposit_address_get**](WalletApi.md#private_create_deposit_address_get) | **GET** /private/create_deposit_address | Creates deposit address in currency
[**private_get_address_book_get**](WalletApi.md#private_get_address_book_get) | **GET** /private/get_address_book | Retrieves address book of given type
[**private_get_current_deposit_address_get**](WalletApi.md#private_get_current_deposit_address_get) | **GET** /private/get_current_deposit_address | Retrieve deposit address for currency
[**private_get_deposits_get**](WalletApi.md#private_get_deposits_get) | **GET** /private/get_deposits | Retrieve the latest users deposits
[**private_get_transfers_get**](WalletApi.md#private_get_transfers_get) | **GET** /private/get_transfers | Adds new entry to address book of given type
[**private_get_withdrawals_get**](WalletApi.md#private_get_withdrawals_get) | **GET** /private/get_withdrawals | Retrieve the latest users withdrawals
[**private_remove_from_address_book_get**](WalletApi.md#private_remove_from_address_book_get) | **GET** /private/remove_from_address_book | Adds new entry to address book of given type
[**private_submit_transfer_to_subaccount_get**](WalletApi.md#private_submit_transfer_to_subaccount_get) | **GET** /private/submit_transfer_to_subaccount | Transfer funds to a subaccount.
[**private_submit_transfer_to_user_get**](WalletApi.md#private_submit_transfer_to_user_get) | **GET** /private/submit_transfer_to_user | Transfer funds to a another user.
[**private_toggle_deposit_address_creation_get**](WalletApi.md#private_toggle_deposit_address_creation_get) | **GET** /private/toggle_deposit_address_creation | Enable or disable deposit address creation
[**private_withdraw_get**](WalletApi.md#private_withdraw_get) | **GET** /private/withdraw | Creates a new withdrawal request



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

api_instance = OpenapiClient::WalletApi.new
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
  puts "Exception when calling WalletApi->private_add_to_address_book_get: #{e}"
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


## private_cancel_transfer_by_id_get

> Object private_cancel_transfer_by_id_get(currency, id, opts)

Cancel transfer

### Example

```ruby
# load the gem
require 'openapi_client'
# setup authorization
OpenapiClient.configure do |config|
  # Configure Bearer authorization (Auth. Token): bearerAuth
  config.access_token = 'YOUR_BEARER_TOKEN'
end

api_instance = OpenapiClient::WalletApi.new
currency = 'currency_example' # String | The currency symbol
id = 12 # Integer | Id of transfer
opts = {
  tfa: 'tfa_example' # String | TFA code, required when TFA is enabled for current account
}

begin
  #Cancel transfer
  result = api_instance.private_cancel_transfer_by_id_get(currency, id, opts)
  p result
rescue OpenapiClient::ApiError => e
  puts "Exception when calling WalletApi->private_cancel_transfer_by_id_get: #{e}"
end
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **String**| The currency symbol | 
 **id** | **Integer**| Id of transfer | 
 **tfa** | **String**| TFA code, required when TFA is enabled for current account | [optional] 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## private_cancel_withdrawal_get

> Object private_cancel_withdrawal_get(currency, id)

Cancels withdrawal request

### Example

```ruby
# load the gem
require 'openapi_client'
# setup authorization
OpenapiClient.configure do |config|
  # Configure Bearer authorization (Auth. Token): bearerAuth
  config.access_token = 'YOUR_BEARER_TOKEN'
end

api_instance = OpenapiClient::WalletApi.new
currency = 'currency_example' # String | The currency symbol
id = 1 # Float | The withdrawal id

begin
  #Cancels withdrawal request
  result = api_instance.private_cancel_withdrawal_get(currency, id)
  p result
rescue OpenapiClient::ApiError => e
  puts "Exception when calling WalletApi->private_cancel_withdrawal_get: #{e}"
end
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **String**| The currency symbol | 
 **id** | **Float**| The withdrawal id | 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## private_create_deposit_address_get

> Object private_create_deposit_address_get(currency)

Creates deposit address in currency

### Example

```ruby
# load the gem
require 'openapi_client'
# setup authorization
OpenapiClient.configure do |config|
  # Configure Bearer authorization (Auth. Token): bearerAuth
  config.access_token = 'YOUR_BEARER_TOKEN'
end

api_instance = OpenapiClient::WalletApi.new
currency = 'currency_example' # String | The currency symbol

begin
  #Creates deposit address in currency
  result = api_instance.private_create_deposit_address_get(currency)
  p result
rescue OpenapiClient::ApiError => e
  puts "Exception when calling WalletApi->private_create_deposit_address_get: #{e}"
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

api_instance = OpenapiClient::WalletApi.new
currency = 'currency_example' # String | The currency symbol
type = 'type_example' # String | Address book type

begin
  #Retrieves address book of given type
  result = api_instance.private_get_address_book_get(currency, type)
  p result
rescue OpenapiClient::ApiError => e
  puts "Exception when calling WalletApi->private_get_address_book_get: #{e}"
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


## private_get_current_deposit_address_get

> Object private_get_current_deposit_address_get(currency)

Retrieve deposit address for currency

### Example

```ruby
# load the gem
require 'openapi_client'
# setup authorization
OpenapiClient.configure do |config|
  # Configure Bearer authorization (Auth. Token): bearerAuth
  config.access_token = 'YOUR_BEARER_TOKEN'
end

api_instance = OpenapiClient::WalletApi.new
currency = 'currency_example' # String | The currency symbol

begin
  #Retrieve deposit address for currency
  result = api_instance.private_get_current_deposit_address_get(currency)
  p result
rescue OpenapiClient::ApiError => e
  puts "Exception when calling WalletApi->private_get_current_deposit_address_get: #{e}"
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


## private_get_deposits_get

> Object private_get_deposits_get(currency, opts)

Retrieve the latest users deposits

### Example

```ruby
# load the gem
require 'openapi_client'
# setup authorization
OpenapiClient.configure do |config|
  # Configure Bearer authorization (Auth. Token): bearerAuth
  config.access_token = 'YOUR_BEARER_TOKEN'
end

api_instance = OpenapiClient::WalletApi.new
currency = 'currency_example' # String | The currency symbol
opts = {
  count: 56, # Integer | Number of requested items, default - `10`
  offset: 10 # Integer | The offset for pagination, default - `0`
}

begin
  #Retrieve the latest users deposits
  result = api_instance.private_get_deposits_get(currency, opts)
  p result
rescue OpenapiClient::ApiError => e
  puts "Exception when calling WalletApi->private_get_deposits_get: #{e}"
end
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **String**| The currency symbol | 
 **count** | **Integer**| Number of requested items, default - &#x60;10&#x60; | [optional] 
 **offset** | **Integer**| The offset for pagination, default - &#x60;0&#x60; | [optional] 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## private_get_transfers_get

> Object private_get_transfers_get(currency, opts)

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

api_instance = OpenapiClient::WalletApi.new
currency = 'currency_example' # String | The currency symbol
opts = {
  count: 56, # Integer | Number of requested items, default - `10`
  offset: 10 # Integer | The offset for pagination, default - `0`
}

begin
  #Adds new entry to address book of given type
  result = api_instance.private_get_transfers_get(currency, opts)
  p result
rescue OpenapiClient::ApiError => e
  puts "Exception when calling WalletApi->private_get_transfers_get: #{e}"
end
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **String**| The currency symbol | 
 **count** | **Integer**| Number of requested items, default - &#x60;10&#x60; | [optional] 
 **offset** | **Integer**| The offset for pagination, default - &#x60;0&#x60; | [optional] 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## private_get_withdrawals_get

> Object private_get_withdrawals_get(currency, opts)

Retrieve the latest users withdrawals

### Example

```ruby
# load the gem
require 'openapi_client'
# setup authorization
OpenapiClient.configure do |config|
  # Configure Bearer authorization (Auth. Token): bearerAuth
  config.access_token = 'YOUR_BEARER_TOKEN'
end

api_instance = OpenapiClient::WalletApi.new
currency = 'currency_example' # String | The currency symbol
opts = {
  count: 56, # Integer | Number of requested items, default - `10`
  offset: 10 # Integer | The offset for pagination, default - `0`
}

begin
  #Retrieve the latest users withdrawals
  result = api_instance.private_get_withdrawals_get(currency, opts)
  p result
rescue OpenapiClient::ApiError => e
  puts "Exception when calling WalletApi->private_get_withdrawals_get: #{e}"
end
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **String**| The currency symbol | 
 **count** | **Integer**| Number of requested items, default - &#x60;10&#x60; | [optional] 
 **offset** | **Integer**| The offset for pagination, default - &#x60;0&#x60; | [optional] 

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

api_instance = OpenapiClient::WalletApi.new
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
  puts "Exception when calling WalletApi->private_remove_from_address_book_get: #{e}"
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

api_instance = OpenapiClient::WalletApi.new
currency = 'currency_example' # String | The currency symbol
amount = 3.4 # Float | Amount of funds to be transferred
destination = 1 # Integer | Id of destination subaccount

begin
  #Transfer funds to a subaccount.
  result = api_instance.private_submit_transfer_to_subaccount_get(currency, amount, destination)
  p result
rescue OpenapiClient::ApiError => e
  puts "Exception when calling WalletApi->private_submit_transfer_to_subaccount_get: #{e}"
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

api_instance = OpenapiClient::WalletApi.new
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
  puts "Exception when calling WalletApi->private_submit_transfer_to_user_get: #{e}"
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

api_instance = OpenapiClient::WalletApi.new
currency = 'currency_example' # String | The currency symbol
state = true # Boolean | 

begin
  #Enable or disable deposit address creation
  result = api_instance.private_toggle_deposit_address_creation_get(currency, state)
  p result
rescue OpenapiClient::ApiError => e
  puts "Exception when calling WalletApi->private_toggle_deposit_address_creation_get: #{e}"
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


## private_withdraw_get

> Object private_withdraw_get(currency, address, amount, opts)

Creates a new withdrawal request

### Example

```ruby
# load the gem
require 'openapi_client'
# setup authorization
OpenapiClient.configure do |config|
  # Configure Bearer authorization (Auth. Token): bearerAuth
  config.access_token = 'YOUR_BEARER_TOKEN'
end

api_instance = OpenapiClient::WalletApi.new
currency = 'currency_example' # String | The currency symbol
address = 'address_example' # String | Address in currency format, it must be in address book
amount = 3.4 # Float | Amount of funds to be withdrawn
opts = {
  priority: 'priority_example', # String | Withdrawal priority, optional for BTC, default: `high`
  tfa: 'tfa_example' # String | TFA code, required when TFA is enabled for current account
}

begin
  #Creates a new withdrawal request
  result = api_instance.private_withdraw_get(currency, address, amount, opts)
  p result
rescue OpenapiClient::ApiError => e
  puts "Exception when calling WalletApi->private_withdraw_get: #{e}"
end
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **String**| The currency symbol | 
 **address** | **String**| Address in currency format, it must be in address book | 
 **amount** | **Float**| Amount of funds to be withdrawn | 
 **priority** | **String**| Withdrawal priority, optional for BTC, default: &#x60;high&#x60; | [optional] 
 **tfa** | **String**| TFA code, required when TFA is enabled for current account | [optional] 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

