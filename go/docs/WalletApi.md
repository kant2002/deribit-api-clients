# \WalletApi

All URIs are relative to *https://www.deribit.com/api/v2*

Method | HTTP request | Description
------------- | ------------- | -------------
[**PrivateAddToAddressBookGet**](WalletApi.md#PrivateAddToAddressBookGet) | **Get** /private/add_to_address_book | Adds new entry to address book of given type
[**PrivateCancelTransferByIdGet**](WalletApi.md#PrivateCancelTransferByIdGet) | **Get** /private/cancel_transfer_by_id | Cancel transfer
[**PrivateCancelWithdrawalGet**](WalletApi.md#PrivateCancelWithdrawalGet) | **Get** /private/cancel_withdrawal | Cancels withdrawal request
[**PrivateCreateDepositAddressGet**](WalletApi.md#PrivateCreateDepositAddressGet) | **Get** /private/create_deposit_address | Creates deposit address in currency
[**PrivateGetAddressBookGet**](WalletApi.md#PrivateGetAddressBookGet) | **Get** /private/get_address_book | Retrieves address book of given type
[**PrivateGetCurrentDepositAddressGet**](WalletApi.md#PrivateGetCurrentDepositAddressGet) | **Get** /private/get_current_deposit_address | Retrieve deposit address for currency
[**PrivateGetDepositsGet**](WalletApi.md#PrivateGetDepositsGet) | **Get** /private/get_deposits | Retrieve the latest users deposits
[**PrivateGetTransfersGet**](WalletApi.md#PrivateGetTransfersGet) | **Get** /private/get_transfers | Adds new entry to address book of given type
[**PrivateGetWithdrawalsGet**](WalletApi.md#PrivateGetWithdrawalsGet) | **Get** /private/get_withdrawals | Retrieve the latest users withdrawals
[**PrivateRemoveFromAddressBookGet**](WalletApi.md#PrivateRemoveFromAddressBookGet) | **Get** /private/remove_from_address_book | Adds new entry to address book of given type
[**PrivateSubmitTransferToSubaccountGet**](WalletApi.md#PrivateSubmitTransferToSubaccountGet) | **Get** /private/submit_transfer_to_subaccount | Transfer funds to a subaccount.
[**PrivateSubmitTransferToUserGet**](WalletApi.md#PrivateSubmitTransferToUserGet) | **Get** /private/submit_transfer_to_user | Transfer funds to a another user.
[**PrivateToggleDepositAddressCreationGet**](WalletApi.md#PrivateToggleDepositAddressCreationGet) | **Get** /private/toggle_deposit_address_creation | Enable or disable deposit address creation
[**PrivateWithdrawGet**](WalletApi.md#PrivateWithdrawGet) | **Get** /private/withdraw | Creates a new withdrawal request



## PrivateAddToAddressBookGet

> map[string]interface{} PrivateAddToAddressBookGet(ctx, currency, type_, address, name, optional)
Adds new entry to address book of given type

### Required Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
**ctx** | **context.Context** | context for authentication, logging, cancellation, deadlines, tracing, etc.
**currency** | **string**| The currency symbol | 
**type_** | **string**| Address book type | 
**address** | **string**| Address in currency format, it must be in address book | 
**name** | **string**| Name of address book entry | 
 **optional** | ***PrivateAddToAddressBookGetOpts** | optional parameters | nil if no parameters

### Optional Parameters

Optional parameters are passed through a pointer to a PrivateAddToAddressBookGetOpts struct


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------




 **tfa** | **optional.String**| TFA code, required when TFA is enabled for current account | 

### Return type

[**map[string]interface{}**](map[string]interface{}.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateCancelTransferByIdGet

> map[string]interface{} PrivateCancelTransferByIdGet(ctx, currency, id, optional)
Cancel transfer

### Required Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
**ctx** | **context.Context** | context for authentication, logging, cancellation, deadlines, tracing, etc.
**currency** | **string**| The currency symbol | 
**id** | **int32**| Id of transfer | 
 **optional** | ***PrivateCancelTransferByIdGetOpts** | optional parameters | nil if no parameters

### Optional Parameters

Optional parameters are passed through a pointer to a PrivateCancelTransferByIdGetOpts struct


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------


 **tfa** | **optional.String**| TFA code, required when TFA is enabled for current account | 

### Return type

[**map[string]interface{}**](map[string]interface{}.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateCancelWithdrawalGet

> map[string]interface{} PrivateCancelWithdrawalGet(ctx, currency, id)
Cancels withdrawal request

### Required Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
**ctx** | **context.Context** | context for authentication, logging, cancellation, deadlines, tracing, etc.
**currency** | **string**| The currency symbol | 
**id** | **float32**| The withdrawal id | 

### Return type

[**map[string]interface{}**](map[string]interface{}.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateCreateDepositAddressGet

> map[string]interface{} PrivateCreateDepositAddressGet(ctx, currency)
Creates deposit address in currency

### Required Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
**ctx** | **context.Context** | context for authentication, logging, cancellation, deadlines, tracing, etc.
**currency** | **string**| The currency symbol | 

### Return type

[**map[string]interface{}**](map[string]interface{}.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateGetAddressBookGet

> map[string]interface{} PrivateGetAddressBookGet(ctx, currency, type_)
Retrieves address book of given type

### Required Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
**ctx** | **context.Context** | context for authentication, logging, cancellation, deadlines, tracing, etc.
**currency** | **string**| The currency symbol | 
**type_** | **string**| Address book type | 

### Return type

[**map[string]interface{}**](map[string]interface{}.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateGetCurrentDepositAddressGet

> map[string]interface{} PrivateGetCurrentDepositAddressGet(ctx, currency)
Retrieve deposit address for currency

### Required Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
**ctx** | **context.Context** | context for authentication, logging, cancellation, deadlines, tracing, etc.
**currency** | **string**| The currency symbol | 

### Return type

[**map[string]interface{}**](map[string]interface{}.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateGetDepositsGet

> map[string]interface{} PrivateGetDepositsGet(ctx, currency, optional)
Retrieve the latest users deposits

### Required Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
**ctx** | **context.Context** | context for authentication, logging, cancellation, deadlines, tracing, etc.
**currency** | **string**| The currency symbol | 
 **optional** | ***PrivateGetDepositsGetOpts** | optional parameters | nil if no parameters

### Optional Parameters

Optional parameters are passed through a pointer to a PrivateGetDepositsGetOpts struct


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------

 **count** | **optional.Int32**| Number of requested items, default - &#x60;10&#x60; | 
 **offset** | **optional.Int32**| The offset for pagination, default - &#x60;0&#x60; | 

### Return type

[**map[string]interface{}**](map[string]interface{}.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateGetTransfersGet

> map[string]interface{} PrivateGetTransfersGet(ctx, currency, optional)
Adds new entry to address book of given type

### Required Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
**ctx** | **context.Context** | context for authentication, logging, cancellation, deadlines, tracing, etc.
**currency** | **string**| The currency symbol | 
 **optional** | ***PrivateGetTransfersGetOpts** | optional parameters | nil if no parameters

### Optional Parameters

Optional parameters are passed through a pointer to a PrivateGetTransfersGetOpts struct


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------

 **count** | **optional.Int32**| Number of requested items, default - &#x60;10&#x60; | 
 **offset** | **optional.Int32**| The offset for pagination, default - &#x60;0&#x60; | 

### Return type

[**map[string]interface{}**](map[string]interface{}.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateGetWithdrawalsGet

> map[string]interface{} PrivateGetWithdrawalsGet(ctx, currency, optional)
Retrieve the latest users withdrawals

### Required Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
**ctx** | **context.Context** | context for authentication, logging, cancellation, deadlines, tracing, etc.
**currency** | **string**| The currency symbol | 
 **optional** | ***PrivateGetWithdrawalsGetOpts** | optional parameters | nil if no parameters

### Optional Parameters

Optional parameters are passed through a pointer to a PrivateGetWithdrawalsGetOpts struct


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------

 **count** | **optional.Int32**| Number of requested items, default - &#x60;10&#x60; | 
 **offset** | **optional.Int32**| The offset for pagination, default - &#x60;0&#x60; | 

### Return type

[**map[string]interface{}**](map[string]interface{}.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateRemoveFromAddressBookGet

> map[string]interface{} PrivateRemoveFromAddressBookGet(ctx, currency, type_, address, optional)
Adds new entry to address book of given type

### Required Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
**ctx** | **context.Context** | context for authentication, logging, cancellation, deadlines, tracing, etc.
**currency** | **string**| The currency symbol | 
**type_** | **string**| Address book type | 
**address** | **string**| Address in currency format, it must be in address book | 
 **optional** | ***PrivateRemoveFromAddressBookGetOpts** | optional parameters | nil if no parameters

### Optional Parameters

Optional parameters are passed through a pointer to a PrivateRemoveFromAddressBookGetOpts struct


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------



 **tfa** | **optional.String**| TFA code, required when TFA is enabled for current account | 

### Return type

[**map[string]interface{}**](map[string]interface{}.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateSubmitTransferToSubaccountGet

> map[string]interface{} PrivateSubmitTransferToSubaccountGet(ctx, currency, amount, destination)
Transfer funds to a subaccount.

### Required Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
**ctx** | **context.Context** | context for authentication, logging, cancellation, deadlines, tracing, etc.
**currency** | **string**| The currency symbol | 
**amount** | **float32**| Amount of funds to be transferred | 
**destination** | **int32**| Id of destination subaccount | 

### Return type

[**map[string]interface{}**](map[string]interface{}.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateSubmitTransferToUserGet

> map[string]interface{} PrivateSubmitTransferToUserGet(ctx, currency, amount, destination, optional)
Transfer funds to a another user.

### Required Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
**ctx** | **context.Context** | context for authentication, logging, cancellation, deadlines, tracing, etc.
**currency** | **string**| The currency symbol | 
**amount** | **float32**| Amount of funds to be transferred | 
**destination** | **string**| Destination address from address book | 
 **optional** | ***PrivateSubmitTransferToUserGetOpts** | optional parameters | nil if no parameters

### Optional Parameters

Optional parameters are passed through a pointer to a PrivateSubmitTransferToUserGetOpts struct


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------



 **tfa** | **optional.String**| TFA code, required when TFA is enabled for current account | 

### Return type

[**map[string]interface{}**](map[string]interface{}.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateToggleDepositAddressCreationGet

> map[string]interface{} PrivateToggleDepositAddressCreationGet(ctx, currency, state)
Enable or disable deposit address creation

### Required Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
**ctx** | **context.Context** | context for authentication, logging, cancellation, deadlines, tracing, etc.
**currency** | **string**| The currency symbol | 
**state** | **bool**|  | 

### Return type

[**map[string]interface{}**](map[string]interface{}.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateWithdrawGet

> map[string]interface{} PrivateWithdrawGet(ctx, currency, address, amount, optional)
Creates a new withdrawal request

### Required Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
**ctx** | **context.Context** | context for authentication, logging, cancellation, deadlines, tracing, etc.
**currency** | **string**| The currency symbol | 
**address** | **string**| Address in currency format, it must be in address book | 
**amount** | **float32**| Amount of funds to be withdrawn | 
 **optional** | ***PrivateWithdrawGetOpts** | optional parameters | nil if no parameters

### Optional Parameters

Optional parameters are passed through a pointer to a PrivateWithdrawGetOpts struct


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------



 **priority** | **optional.String**| Withdrawal priority, optional for BTC, default: &#x60;high&#x60; | 
 **tfa** | **optional.String**| TFA code, required when TFA is enabled for current account | 

### Return type

[**map[string]interface{}**](map[string]interface{}.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)

