# InternalApi

All URIs are relative to *https://www.deribit.com/api/v2*

Method | HTTP request | Description
------------- | ------------- | -------------
[**PrivateAddToAddressBookGet**](InternalApi.md#PrivateAddToAddressBookGet) | **GET** /private/add_to_address_book | Adds new entry to address book of given type
[**PrivateDisableTfaWithRecoveryCodeGet**](InternalApi.md#PrivateDisableTfaWithRecoveryCodeGet) | **GET** /private/disable_tfa_with_recovery_code | Disables TFA with one time recovery code
[**PrivateGetAddressBookGet**](InternalApi.md#PrivateGetAddressBookGet) | **GET** /private/get_address_book | Retrieves address book of given type
[**PrivateRemoveFromAddressBookGet**](InternalApi.md#PrivateRemoveFromAddressBookGet) | **GET** /private/remove_from_address_book | Adds new entry to address book of given type
[**PrivateSubmitTransferToSubaccountGet**](InternalApi.md#PrivateSubmitTransferToSubaccountGet) | **GET** /private/submit_transfer_to_subaccount | Transfer funds to a subaccount.
[**PrivateSubmitTransferToUserGet**](InternalApi.md#PrivateSubmitTransferToUserGet) | **GET** /private/submit_transfer_to_user | Transfer funds to a another user.
[**PrivateToggleDepositAddressCreationGet**](InternalApi.md#PrivateToggleDepositAddressCreationGet) | **GET** /private/toggle_deposit_address_creation | Enable or disable deposit address creation
[**PublicGetFooterGet**](InternalApi.md#PublicGetFooterGet) | **GET** /public/get_footer | Get information to be displayed in the footer of the website.
[**PublicGetOptionMarkPricesGet**](InternalApi.md#PublicGetOptionMarkPricesGet) | **GET** /public/get_option_mark_prices | Retrives market prices and its implied volatility of options instruments
[**PublicValidateFieldGet**](InternalApi.md#PublicValidateFieldGet) | **GET** /public/validate_field | Method used to introduce the client software connected to Deribit platform over websocket. Provided data may have an impact on the maintained connection and will be collected for internal statistical purposes. In response, Deribit will also introduce itself.


# **PrivateAddToAddressBookGet**
> object PrivateAddToAddressBookGet(currency, type, address, name, tfa=var.tfa)

Adds new entry to address book of given type

### Example
```R
library(openapi)

var.currency <- 'currency_example' # character | The currency symbol
var.type <- 'type_example' # character | Address book type
var.address <- 'address_example' # character | Address in currency format, it must be in address book
var.name <- 'Main address' # character | Name of address book entry
var.tfa <- 'tfa_example' # character | TFA code, required when TFA is enabled for current account

#Adds new entry to address book of given type
api.instance <- InternalApi$new()
# Configure HTTP basic authorization: bearerAuth
api.instance$apiClient$username <- 'TODO_YOUR_USERNAME';
api.instance$apiClient$password <- 'TODO_YOUR_PASSWORD';
result <- api.instance$PrivateAddToAddressBookGet(var.currency, var.type, var.address, var.name, tfa=var.tfa)
dput(result)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **character**| The currency symbol | 
 **type** | **character**| Address book type | 
 **address** | **character**| Address in currency format, it must be in address book | 
 **name** | **character**| Name of address book entry | 
 **tfa** | **character**| TFA code, required when TFA is enabled for current account | [optional] 

### Return type

**object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json



# **PrivateDisableTfaWithRecoveryCodeGet**
> object PrivateDisableTfaWithRecoveryCodeGet(password, code)

Disables TFA with one time recovery code

### Example
```R
library(openapi)

var.password <- 'password_example' # character | The password for the subaccount
var.code <- 'code_example' # character | One time recovery code

#Disables TFA with one time recovery code
api.instance <- InternalApi$new()
# Configure HTTP basic authorization: bearerAuth
api.instance$apiClient$username <- 'TODO_YOUR_USERNAME';
api.instance$apiClient$password <- 'TODO_YOUR_PASSWORD';
result <- api.instance$PrivateDisableTfaWithRecoveryCodeGet(var.password, var.code)
dput(result)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **password** | **character**| The password for the subaccount | 
 **code** | **character**| One time recovery code | 

### Return type

**object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json



# **PrivateGetAddressBookGet**
> object PrivateGetAddressBookGet(currency, type)

Retrieves address book of given type

### Example
```R
library(openapi)

var.currency <- 'currency_example' # character | The currency symbol
var.type <- 'type_example' # character | Address book type

#Retrieves address book of given type
api.instance <- InternalApi$new()
# Configure HTTP basic authorization: bearerAuth
api.instance$apiClient$username <- 'TODO_YOUR_USERNAME';
api.instance$apiClient$password <- 'TODO_YOUR_PASSWORD';
result <- api.instance$PrivateGetAddressBookGet(var.currency, var.type)
dput(result)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **character**| The currency symbol | 
 **type** | **character**| Address book type | 

### Return type

**object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json



# **PrivateRemoveFromAddressBookGet**
> object PrivateRemoveFromAddressBookGet(currency, type, address, tfa=var.tfa)

Adds new entry to address book of given type

### Example
```R
library(openapi)

var.currency <- 'currency_example' # character | The currency symbol
var.type <- 'type_example' # character | Address book type
var.address <- 'address_example' # character | Address in currency format, it must be in address book
var.tfa <- 'tfa_example' # character | TFA code, required when TFA is enabled for current account

#Adds new entry to address book of given type
api.instance <- InternalApi$new()
# Configure HTTP basic authorization: bearerAuth
api.instance$apiClient$username <- 'TODO_YOUR_USERNAME';
api.instance$apiClient$password <- 'TODO_YOUR_PASSWORD';
result <- api.instance$PrivateRemoveFromAddressBookGet(var.currency, var.type, var.address, tfa=var.tfa)
dput(result)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **character**| The currency symbol | 
 **type** | **character**| Address book type | 
 **address** | **character**| Address in currency format, it must be in address book | 
 **tfa** | **character**| TFA code, required when TFA is enabled for current account | [optional] 

### Return type

**object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json



# **PrivateSubmitTransferToSubaccountGet**
> object PrivateSubmitTransferToSubaccountGet(currency, amount, destination)

Transfer funds to a subaccount.

### Example
```R
library(openapi)

var.currency <- 'currency_example' # character | The currency symbol
var.amount <- 3.4 # numeric | Amount of funds to be transferred
var.destination <- 1 # integer | Id of destination subaccount

#Transfer funds to a subaccount.
api.instance <- InternalApi$new()
# Configure HTTP basic authorization: bearerAuth
api.instance$apiClient$username <- 'TODO_YOUR_USERNAME';
api.instance$apiClient$password <- 'TODO_YOUR_PASSWORD';
result <- api.instance$PrivateSubmitTransferToSubaccountGet(var.currency, var.amount, var.destination)
dput(result)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **character**| The currency symbol | 
 **amount** | **numeric**| Amount of funds to be transferred | 
 **destination** | **integer**| Id of destination subaccount | 

### Return type

**object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json



# **PrivateSubmitTransferToUserGet**
> object PrivateSubmitTransferToUserGet(currency, amount, destination, tfa=var.tfa)

Transfer funds to a another user.

### Example
```R
library(openapi)

var.currency <- 'currency_example' # character | The currency symbol
var.amount <- 3.4 # numeric | Amount of funds to be transferred
var.destination <- 'destination_example' # character | Destination address from address book
var.tfa <- 'tfa_example' # character | TFA code, required when TFA is enabled for current account

#Transfer funds to a another user.
api.instance <- InternalApi$new()
# Configure HTTP basic authorization: bearerAuth
api.instance$apiClient$username <- 'TODO_YOUR_USERNAME';
api.instance$apiClient$password <- 'TODO_YOUR_PASSWORD';
result <- api.instance$PrivateSubmitTransferToUserGet(var.currency, var.amount, var.destination, tfa=var.tfa)
dput(result)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **character**| The currency symbol | 
 **amount** | **numeric**| Amount of funds to be transferred | 
 **destination** | **character**| Destination address from address book | 
 **tfa** | **character**| TFA code, required when TFA is enabled for current account | [optional] 

### Return type

**object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json



# **PrivateToggleDepositAddressCreationGet**
> object PrivateToggleDepositAddressCreationGet(currency, state)

Enable or disable deposit address creation

### Example
```R
library(openapi)

var.currency <- 'currency_example' # character | The currency symbol
var.state <- 'state_example' # character | 

#Enable or disable deposit address creation
api.instance <- InternalApi$new()
# Configure HTTP basic authorization: bearerAuth
api.instance$apiClient$username <- 'TODO_YOUR_USERNAME';
api.instance$apiClient$password <- 'TODO_YOUR_PASSWORD';
result <- api.instance$PrivateToggleDepositAddressCreationGet(var.currency, var.state)
dput(result)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **character**| The currency symbol | 
 **state** | **character**|  | 

### Return type

**object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json



# **PublicGetFooterGet**
> object PublicGetFooterGet()

Get information to be displayed in the footer of the website.

### Example
```R
library(openapi)


#Get information to be displayed in the footer of the website.
api.instance <- InternalApi$new()
# Configure HTTP basic authorization: bearerAuth
api.instance$apiClient$username <- 'TODO_YOUR_USERNAME';
api.instance$apiClient$password <- 'TODO_YOUR_PASSWORD';
result <- api.instance$PublicGetFooterGet()
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



# **PublicGetOptionMarkPricesGet**
> object PublicGetOptionMarkPricesGet(currency)

Retrives market prices and its implied volatility of options instruments

### Example
```R
library(openapi)

var.currency <- 'currency_example' # character | The currency symbol

#Retrives market prices and its implied volatility of options instruments
api.instance <- InternalApi$new()
# Configure HTTP basic authorization: bearerAuth
api.instance$apiClient$username <- 'TODO_YOUR_USERNAME';
api.instance$apiClient$password <- 'TODO_YOUR_PASSWORD';
result <- api.instance$PublicGetOptionMarkPricesGet(var.currency)
dput(result)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **character**| The currency symbol | 

### Return type

**object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json



# **PublicValidateFieldGet**
> object PublicValidateFieldGet(field, value, value2=var.value2)

Method used to introduce the client software connected to Deribit platform over websocket. Provided data may have an impact on the maintained connection and will be collected for internal statistical purposes. In response, Deribit will also introduce itself.

### Example
```R
library(openapi)

var.field <- 'field_example' # character | Name of the field to be validated, examples: postal_code, date_of_birth
var.value <- 'value_example' # character | Value to be checked
var.value2 <- 'value2_example' # character | Additional value to be compared with

#Method used to introduce the client software connected to Deribit platform over websocket. Provided data may have an impact on the maintained connection and will be collected for internal statistical purposes. In response, Deribit will also introduce itself.
api.instance <- InternalApi$new()
# Configure HTTP basic authorization: bearerAuth
api.instance$apiClient$username <- 'TODO_YOUR_USERNAME';
api.instance$apiClient$password <- 'TODO_YOUR_PASSWORD';
result <- api.instance$PublicValidateFieldGet(var.field, var.value, value2=var.value2)
dput(result)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **field** | **character**| Name of the field to be validated, examples: postal_code, date_of_birth | 
 **value** | **character**| Value to be checked | 
 **value2** | **character**| Additional value to be compared with | [optional] 

### Return type

**object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json



