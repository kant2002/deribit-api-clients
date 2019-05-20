# \InternalApi

All URIs are relative to *https://www.deribit.com/api/v2*

Method | HTTP request | Description
------------- | ------------- | -------------
[**PrivateAddToAddressBookGet**](InternalApi.md#PrivateAddToAddressBookGet) | **Get** /private/add_to_address_book | Adds new entry to address book of given type
[**PrivateDisableTfaWithRecoveryCodeGet**](InternalApi.md#PrivateDisableTfaWithRecoveryCodeGet) | **Get** /private/disable_tfa_with_recovery_code | Disables TFA with one time recovery code
[**PrivateGetAddressBookGet**](InternalApi.md#PrivateGetAddressBookGet) | **Get** /private/get_address_book | Retrieves address book of given type
[**PrivateRemoveFromAddressBookGet**](InternalApi.md#PrivateRemoveFromAddressBookGet) | **Get** /private/remove_from_address_book | Adds new entry to address book of given type
[**PrivateSubmitTransferToSubaccountGet**](InternalApi.md#PrivateSubmitTransferToSubaccountGet) | **Get** /private/submit_transfer_to_subaccount | Transfer funds to a subaccount.
[**PrivateSubmitTransferToUserGet**](InternalApi.md#PrivateSubmitTransferToUserGet) | **Get** /private/submit_transfer_to_user | Transfer funds to a another user.
[**PrivateToggleDepositAddressCreationGet**](InternalApi.md#PrivateToggleDepositAddressCreationGet) | **Get** /private/toggle_deposit_address_creation | Enable or disable deposit address creation
[**PublicGetFooterGet**](InternalApi.md#PublicGetFooterGet) | **Get** /public/get_footer | Get information to be displayed in the footer of the website.
[**PublicGetOptionMarkPricesGet**](InternalApi.md#PublicGetOptionMarkPricesGet) | **Get** /public/get_option_mark_prices | Retrives market prices and its implied volatility of options instruments
[**PublicValidateFieldGet**](InternalApi.md#PublicValidateFieldGet) | **Get** /public/validate_field | Method used to introduce the client software connected to Deribit platform over websocket. Provided data may have an impact on the maintained connection and will be collected for internal statistical purposes. In response, Deribit will also introduce itself.



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


## PrivateDisableTfaWithRecoveryCodeGet

> map[string]interface{} PrivateDisableTfaWithRecoveryCodeGet(ctx, password, code)
Disables TFA with one time recovery code

### Required Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
**ctx** | **context.Context** | context for authentication, logging, cancellation, deadlines, tracing, etc.
**password** | **string**| The password for the subaccount | 
**code** | **string**| One time recovery code | 

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


## PublicGetFooterGet

> map[string]interface{} PublicGetFooterGet(ctx, )
Get information to be displayed in the footer of the website.

### Required Parameters

This endpoint does not need any parameter.

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


## PublicGetOptionMarkPricesGet

> map[string]interface{} PublicGetOptionMarkPricesGet(ctx, currency)
Retrives market prices and its implied volatility of options instruments

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


## PublicValidateFieldGet

> map[string]interface{} PublicValidateFieldGet(ctx, field, value, optional)
Method used to introduce the client software connected to Deribit platform over websocket. Provided data may have an impact on the maintained connection and will be collected for internal statistical purposes. In response, Deribit will also introduce itself.

### Required Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
**ctx** | **context.Context** | context for authentication, logging, cancellation, deadlines, tracing, etc.
**field** | **string**| Name of the field to be validated, examples: postal_code, date_of_birth | 
**value** | **string**| Value to be checked | 
 **optional** | ***PublicValidateFieldGetOpts** | optional parameters | nil if no parameters

### Optional Parameters

Optional parameters are passed through a pointer to a PublicValidateFieldGetOpts struct


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------


 **value2** | **optional.String**| Additional value to be compared with | 

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

