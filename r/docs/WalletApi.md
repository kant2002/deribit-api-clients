# WalletApi

All URIs are relative to *https://www.deribit.com/api/v2*

Method | HTTP request | Description
------------- | ------------- | -------------
[**PrivateAddToAddressBookGet**](WalletApi.md#PrivateAddToAddressBookGet) | **GET** /private/add_to_address_book | Adds new entry to address book of given type
[**PrivateCancelTransferByIdGet**](WalletApi.md#PrivateCancelTransferByIdGet) | **GET** /private/cancel_transfer_by_id | Cancel transfer
[**PrivateCancelWithdrawalGet**](WalletApi.md#PrivateCancelWithdrawalGet) | **GET** /private/cancel_withdrawal | Cancels withdrawal request
[**PrivateCreateDepositAddressGet**](WalletApi.md#PrivateCreateDepositAddressGet) | **GET** /private/create_deposit_address | Creates deposit address in currency
[**PrivateGetAddressBookGet**](WalletApi.md#PrivateGetAddressBookGet) | **GET** /private/get_address_book | Retrieves address book of given type
[**PrivateGetCurrentDepositAddressGet**](WalletApi.md#PrivateGetCurrentDepositAddressGet) | **GET** /private/get_current_deposit_address | Retrieve deposit address for currency
[**PrivateGetDepositsGet**](WalletApi.md#PrivateGetDepositsGet) | **GET** /private/get_deposits | Retrieve the latest users deposits
[**PrivateGetTransfersGet**](WalletApi.md#PrivateGetTransfersGet) | **GET** /private/get_transfers | Adds new entry to address book of given type
[**PrivateGetWithdrawalsGet**](WalletApi.md#PrivateGetWithdrawalsGet) | **GET** /private/get_withdrawals | Retrieve the latest users withdrawals
[**PrivateRemoveFromAddressBookGet**](WalletApi.md#PrivateRemoveFromAddressBookGet) | **GET** /private/remove_from_address_book | Adds new entry to address book of given type
[**PrivateSubmitTransferToSubaccountGet**](WalletApi.md#PrivateSubmitTransferToSubaccountGet) | **GET** /private/submit_transfer_to_subaccount | Transfer funds to a subaccount.
[**PrivateSubmitTransferToUserGet**](WalletApi.md#PrivateSubmitTransferToUserGet) | **GET** /private/submit_transfer_to_user | Transfer funds to a another user.
[**PrivateToggleDepositAddressCreationGet**](WalletApi.md#PrivateToggleDepositAddressCreationGet) | **GET** /private/toggle_deposit_address_creation | Enable or disable deposit address creation
[**PrivateWithdrawGet**](WalletApi.md#PrivateWithdrawGet) | **GET** /private/withdraw | Creates a new withdrawal request


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
api.instance <- WalletApi$new()
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



# **PrivateCancelTransferByIdGet**
> object PrivateCancelTransferByIdGet(currency, id, tfa=var.tfa)

Cancel transfer

### Example
```R
library(openapi)

var.currency <- 'currency_example' # character | The currency symbol
var.id <- 12 # integer | Id of transfer
var.tfa <- 'tfa_example' # character | TFA code, required when TFA is enabled for current account

#Cancel transfer
api.instance <- WalletApi$new()
# Configure HTTP basic authorization: bearerAuth
api.instance$apiClient$username <- 'TODO_YOUR_USERNAME';
api.instance$apiClient$password <- 'TODO_YOUR_PASSWORD';
result <- api.instance$PrivateCancelTransferByIdGet(var.currency, var.id, tfa=var.tfa)
dput(result)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **character**| The currency symbol | 
 **id** | **integer**| Id of transfer | 
 **tfa** | **character**| TFA code, required when TFA is enabled for current account | [optional] 

### Return type

**object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json



# **PrivateCancelWithdrawalGet**
> object PrivateCancelWithdrawalGet(currency, id)

Cancels withdrawal request

### Example
```R
library(openapi)

var.currency <- 'currency_example' # character | The currency symbol
var.id <- 1 # numeric | The withdrawal id

#Cancels withdrawal request
api.instance <- WalletApi$new()
# Configure HTTP basic authorization: bearerAuth
api.instance$apiClient$username <- 'TODO_YOUR_USERNAME';
api.instance$apiClient$password <- 'TODO_YOUR_PASSWORD';
result <- api.instance$PrivateCancelWithdrawalGet(var.currency, var.id)
dput(result)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **character**| The currency symbol | 
 **id** | **numeric**| The withdrawal id | 

### Return type

**object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json



# **PrivateCreateDepositAddressGet**
> object PrivateCreateDepositAddressGet(currency)

Creates deposit address in currency

### Example
```R
library(openapi)

var.currency <- 'currency_example' # character | The currency symbol

#Creates deposit address in currency
api.instance <- WalletApi$new()
# Configure HTTP basic authorization: bearerAuth
api.instance$apiClient$username <- 'TODO_YOUR_USERNAME';
api.instance$apiClient$password <- 'TODO_YOUR_PASSWORD';
result <- api.instance$PrivateCreateDepositAddressGet(var.currency)
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



# **PrivateGetAddressBookGet**
> object PrivateGetAddressBookGet(currency, type)

Retrieves address book of given type

### Example
```R
library(openapi)

var.currency <- 'currency_example' # character | The currency symbol
var.type <- 'type_example' # character | Address book type

#Retrieves address book of given type
api.instance <- WalletApi$new()
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



# **PrivateGetCurrentDepositAddressGet**
> object PrivateGetCurrentDepositAddressGet(currency)

Retrieve deposit address for currency

### Example
```R
library(openapi)

var.currency <- 'currency_example' # character | The currency symbol

#Retrieve deposit address for currency
api.instance <- WalletApi$new()
# Configure HTTP basic authorization: bearerAuth
api.instance$apiClient$username <- 'TODO_YOUR_USERNAME';
api.instance$apiClient$password <- 'TODO_YOUR_PASSWORD';
result <- api.instance$PrivateGetCurrentDepositAddressGet(var.currency)
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



# **PrivateGetDepositsGet**
> object PrivateGetDepositsGet(currency, count=var.count, offset=var.offset)

Retrieve the latest users deposits

### Example
```R
library(openapi)

var.currency <- 'currency_example' # character | The currency symbol
var.count <- 56 # integer | Number of requested items, default - `10`
var.offset <- 10 # integer | The offset for pagination, default - `0`

#Retrieve the latest users deposits
api.instance <- WalletApi$new()
# Configure HTTP basic authorization: bearerAuth
api.instance$apiClient$username <- 'TODO_YOUR_USERNAME';
api.instance$apiClient$password <- 'TODO_YOUR_PASSWORD';
result <- api.instance$PrivateGetDepositsGet(var.currency, count=var.count, offset=var.offset)
dput(result)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **character**| The currency symbol | 
 **count** | **integer**| Number of requested items, default - &#x60;10&#x60; | [optional] 
 **offset** | **integer**| The offset for pagination, default - &#x60;0&#x60; | [optional] 

### Return type

**object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json



# **PrivateGetTransfersGet**
> object PrivateGetTransfersGet(currency, count=var.count, offset=var.offset)

Adds new entry to address book of given type

### Example
```R
library(openapi)

var.currency <- 'currency_example' # character | The currency symbol
var.count <- 56 # integer | Number of requested items, default - `10`
var.offset <- 10 # integer | The offset for pagination, default - `0`

#Adds new entry to address book of given type
api.instance <- WalletApi$new()
# Configure HTTP basic authorization: bearerAuth
api.instance$apiClient$username <- 'TODO_YOUR_USERNAME';
api.instance$apiClient$password <- 'TODO_YOUR_PASSWORD';
result <- api.instance$PrivateGetTransfersGet(var.currency, count=var.count, offset=var.offset)
dput(result)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **character**| The currency symbol | 
 **count** | **integer**| Number of requested items, default - &#x60;10&#x60; | [optional] 
 **offset** | **integer**| The offset for pagination, default - &#x60;0&#x60; | [optional] 

### Return type

**object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json



# **PrivateGetWithdrawalsGet**
> object PrivateGetWithdrawalsGet(currency, count=var.count, offset=var.offset)

Retrieve the latest users withdrawals

### Example
```R
library(openapi)

var.currency <- 'currency_example' # character | The currency symbol
var.count <- 56 # integer | Number of requested items, default - `10`
var.offset <- 10 # integer | The offset for pagination, default - `0`

#Retrieve the latest users withdrawals
api.instance <- WalletApi$new()
# Configure HTTP basic authorization: bearerAuth
api.instance$apiClient$username <- 'TODO_YOUR_USERNAME';
api.instance$apiClient$password <- 'TODO_YOUR_PASSWORD';
result <- api.instance$PrivateGetWithdrawalsGet(var.currency, count=var.count, offset=var.offset)
dput(result)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **character**| The currency symbol | 
 **count** | **integer**| Number of requested items, default - &#x60;10&#x60; | [optional] 
 **offset** | **integer**| The offset for pagination, default - &#x60;0&#x60; | [optional] 

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
api.instance <- WalletApi$new()
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
api.instance <- WalletApi$new()
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
api.instance <- WalletApi$new()
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
api.instance <- WalletApi$new()
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



# **PrivateWithdrawGet**
> object PrivateWithdrawGet(currency, address, amount, priority=var.priority, tfa=var.tfa)

Creates a new withdrawal request

### Example
```R
library(openapi)

var.currency <- 'currency_example' # character | The currency symbol
var.address <- 'address_example' # character | Address in currency format, it must be in address book
var.amount <- 3.4 # numeric | Amount of funds to be withdrawn
var.priority <- 'priority_example' # character | Withdrawal priority, optional for BTC, default: `high`
var.tfa <- 'tfa_example' # character | TFA code, required when TFA is enabled for current account

#Creates a new withdrawal request
api.instance <- WalletApi$new()
# Configure HTTP basic authorization: bearerAuth
api.instance$apiClient$username <- 'TODO_YOUR_USERNAME';
api.instance$apiClient$password <- 'TODO_YOUR_PASSWORD';
result <- api.instance$PrivateWithdrawGet(var.currency, var.address, var.amount, priority=var.priority, tfa=var.tfa)
dput(result)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **character**| The currency symbol | 
 **address** | **character**| Address in currency format, it must be in address book | 
 **amount** | **numeric**| Amount of funds to be withdrawn | 
 **priority** | **character**| Withdrawal priority, optional for BTC, default: &#x60;high&#x60; | [optional] 
 **tfa** | **character**| TFA code, required when TFA is enabled for current account | [optional] 

### Return type

**object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json



