# InternalApi

All URIs are relative to *https://www.deribit.com/api/v2*

Method | HTTP request | Description
------------- | ------------- | -------------
[**privateAddToAddressBookGet**](InternalApi.md#privateAddToAddressBookGet) | **GET** /private/add_to_address_book | Adds new entry to address book of given type
[**privateDisableTfaWithRecoveryCodeGet**](InternalApi.md#privateDisableTfaWithRecoveryCodeGet) | **GET** /private/disable_tfa_with_recovery_code | Disables TFA with one time recovery code
[**privateGetAddressBookGet**](InternalApi.md#privateGetAddressBookGet) | **GET** /private/get_address_book | Retrieves address book of given type
[**privateRemoveFromAddressBookGet**](InternalApi.md#privateRemoveFromAddressBookGet) | **GET** /private/remove_from_address_book | Adds new entry to address book of given type
[**privateSubmitTransferToSubaccountGet**](InternalApi.md#privateSubmitTransferToSubaccountGet) | **GET** /private/submit_transfer_to_subaccount | Transfer funds to a subaccount.
[**privateSubmitTransferToUserGet**](InternalApi.md#privateSubmitTransferToUserGet) | **GET** /private/submit_transfer_to_user | Transfer funds to a another user.
[**privateToggleDepositAddressCreationGet**](InternalApi.md#privateToggleDepositAddressCreationGet) | **GET** /private/toggle_deposit_address_creation | Enable or disable deposit address creation
[**publicGetFooterGet**](InternalApi.md#publicGetFooterGet) | **GET** /public/get_footer | Get information to be displayed in the footer of the website.
[**publicGetOptionMarkPricesGet**](InternalApi.md#publicGetOptionMarkPricesGet) | **GET** /public/get_option_mark_prices | Retrives market prices and its implied volatility of options instruments
[**publicValidateFieldGet**](InternalApi.md#publicValidateFieldGet) | **GET** /public/validate_field | Method used to introduce the client software connected to Deribit platform over websocket. Provided data may have an impact on the maintained connection and will be collected for internal statistical purposes. In response, Deribit will also introduce itself.



## privateAddToAddressBookGet

> Object privateAddToAddressBookGet(currency, type, address, name, tfa)

Adds new entry to address book of given type

### Example

```java
// Import classes:
//import org.openapitools.client.api.InternalApi;

InternalApi apiInstance = new InternalApi();
String currency = null; // String | The currency symbol
String type = null; // String | Address book type
String address = null; // String | Address in currency format, it must be in address book
String name = Main address; // String | Name of address book entry
String tfa = null; // String | TFA code, required when TFA is enabled for current account
try {
    Object result = apiInstance.privateAddToAddressBookGet(currency, type, address, name, tfa);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling InternalApi#privateAddToAddressBookGet");
    e.printStackTrace();
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **String**| The currency symbol | [default to null] [enum: BTC, ETH]
 **type** | **String**| Address book type | [default to null] [enum: transfer, withdrawal]
 **address** | **String**| Address in currency format, it must be in address book | [default to null]
 **name** | **String**| Name of address book entry | [default to null]
 **tfa** | **String**| TFA code, required when TFA is enabled for current account | [optional] [default to null]

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## privateDisableTfaWithRecoveryCodeGet

> Object privateDisableTfaWithRecoveryCodeGet(password, code)

Disables TFA with one time recovery code

### Example

```java
// Import classes:
//import org.openapitools.client.api.InternalApi;

InternalApi apiInstance = new InternalApi();
String password = null; // String | The password for the subaccount
String code = null; // String | One time recovery code
try {
    Object result = apiInstance.privateDisableTfaWithRecoveryCodeGet(password, code);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling InternalApi#privateDisableTfaWithRecoveryCodeGet");
    e.printStackTrace();
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **password** | **String**| The password for the subaccount | [default to null]
 **code** | **String**| One time recovery code | [default to null]

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## privateGetAddressBookGet

> Object privateGetAddressBookGet(currency, type)

Retrieves address book of given type

### Example

```java
// Import classes:
//import org.openapitools.client.api.InternalApi;

InternalApi apiInstance = new InternalApi();
String currency = null; // String | The currency symbol
String type = null; // String | Address book type
try {
    Object result = apiInstance.privateGetAddressBookGet(currency, type);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling InternalApi#privateGetAddressBookGet");
    e.printStackTrace();
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **String**| The currency symbol | [default to null] [enum: BTC, ETH]
 **type** | **String**| Address book type | [default to null] [enum: transfer, withdrawal]

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## privateRemoveFromAddressBookGet

> Object privateRemoveFromAddressBookGet(currency, type, address, tfa)

Adds new entry to address book of given type

### Example

```java
// Import classes:
//import org.openapitools.client.api.InternalApi;

InternalApi apiInstance = new InternalApi();
String currency = null; // String | The currency symbol
String type = null; // String | Address book type
String address = null; // String | Address in currency format, it must be in address book
String tfa = null; // String | TFA code, required when TFA is enabled for current account
try {
    Object result = apiInstance.privateRemoveFromAddressBookGet(currency, type, address, tfa);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling InternalApi#privateRemoveFromAddressBookGet");
    e.printStackTrace();
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **String**| The currency symbol | [default to null] [enum: BTC, ETH]
 **type** | **String**| Address book type | [default to null] [enum: transfer, withdrawal]
 **address** | **String**| Address in currency format, it must be in address book | [default to null]
 **tfa** | **String**| TFA code, required when TFA is enabled for current account | [optional] [default to null]

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## privateSubmitTransferToSubaccountGet

> Object privateSubmitTransferToSubaccountGet(currency, amount, destination)

Transfer funds to a subaccount.

### Example

```java
// Import classes:
//import org.openapitools.client.api.InternalApi;

InternalApi apiInstance = new InternalApi();
String currency = null; // String | The currency symbol
BigDecimal amount = null; // BigDecimal | Amount of funds to be transferred
Integer destination = 1; // Integer | Id of destination subaccount
try {
    Object result = apiInstance.privateSubmitTransferToSubaccountGet(currency, amount, destination);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling InternalApi#privateSubmitTransferToSubaccountGet");
    e.printStackTrace();
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **String**| The currency symbol | [default to null] [enum: BTC, ETH]
 **amount** | **BigDecimal**| Amount of funds to be transferred | [default to null]
 **destination** | **Integer**| Id of destination subaccount | [default to null]

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## privateSubmitTransferToUserGet

> Object privateSubmitTransferToUserGet(currency, amount, destination, tfa)

Transfer funds to a another user.

### Example

```java
// Import classes:
//import org.openapitools.client.api.InternalApi;

InternalApi apiInstance = new InternalApi();
String currency = null; // String | The currency symbol
BigDecimal amount = null; // BigDecimal | Amount of funds to be transferred
String destination = null; // String | Destination address from address book
String tfa = null; // String | TFA code, required when TFA is enabled for current account
try {
    Object result = apiInstance.privateSubmitTransferToUserGet(currency, amount, destination, tfa);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling InternalApi#privateSubmitTransferToUserGet");
    e.printStackTrace();
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **String**| The currency symbol | [default to null] [enum: BTC, ETH]
 **amount** | **BigDecimal**| Amount of funds to be transferred | [default to null]
 **destination** | **String**| Destination address from address book | [default to null]
 **tfa** | **String**| TFA code, required when TFA is enabled for current account | [optional] [default to null]

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## privateToggleDepositAddressCreationGet

> Object privateToggleDepositAddressCreationGet(currency, state)

Enable or disable deposit address creation

### Example

```java
// Import classes:
//import org.openapitools.client.api.InternalApi;

InternalApi apiInstance = new InternalApi();
String currency = null; // String | The currency symbol
Boolean state = null; // Boolean | 
try {
    Object result = apiInstance.privateToggleDepositAddressCreationGet(currency, state);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling InternalApi#privateToggleDepositAddressCreationGet");
    e.printStackTrace();
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **String**| The currency symbol | [default to null] [enum: BTC, ETH]
 **state** | **Boolean**|  | [default to null]

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## publicGetFooterGet

> Object publicGetFooterGet()

Get information to be displayed in the footer of the website.

### Example

```java
// Import classes:
//import org.openapitools.client.api.InternalApi;

InternalApi apiInstance = new InternalApi();
try {
    Object result = apiInstance.publicGetFooterGet();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling InternalApi#publicGetFooterGet");
    e.printStackTrace();
}
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


## publicGetOptionMarkPricesGet

> Object publicGetOptionMarkPricesGet(currency)

Retrives market prices and its implied volatility of options instruments

### Example

```java
// Import classes:
//import org.openapitools.client.api.InternalApi;

InternalApi apiInstance = new InternalApi();
String currency = null; // String | The currency symbol
try {
    Object result = apiInstance.publicGetOptionMarkPricesGet(currency);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling InternalApi#publicGetOptionMarkPricesGet");
    e.printStackTrace();
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **String**| The currency symbol | [default to null] [enum: BTC, ETH]

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## publicValidateFieldGet

> Object publicValidateFieldGet(field, value, value2)

Method used to introduce the client software connected to Deribit platform over websocket. Provided data may have an impact on the maintained connection and will be collected for internal statistical purposes. In response, Deribit will also introduce itself.

### Example

```java
// Import classes:
//import org.openapitools.client.api.InternalApi;

InternalApi apiInstance = new InternalApi();
String field = null; // String | Name of the field to be validated, examples: postal_code, date_of_birth
String value = null; // String | Value to be checked
String value2 = null; // String | Additional value to be compared with
try {
    Object result = apiInstance.publicValidateFieldGet(field, value, value2);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling InternalApi#publicValidateFieldGet");
    e.printStackTrace();
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **field** | **String**| Name of the field to be validated, examples: postal_code, date_of_birth | [default to null]
 **value** | **String**| Value to be checked | [default to null]
 **value2** | **String**| Additional value to be compared with | [optional] [default to null]

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

