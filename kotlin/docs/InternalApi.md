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


<a name="privateAddToAddressBookGet"></a>
# **privateAddToAddressBookGet**
> kotlin.Any privateAddToAddressBookGet(currency, type, address, name, tfa)

Adds new entry to address book of given type

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = InternalApi()
val currency : kotlin.String = currency_example // kotlin.String | The currency symbol
val type : kotlin.String = type_example // kotlin.String | Address book type
val address : kotlin.String = address_example // kotlin.String | Address in currency format, it must be in address book
val name : kotlin.String = Main address // kotlin.String | Name of address book entry
val tfa : kotlin.String = tfa_example // kotlin.String | TFA code, required when TFA is enabled for current account
try {
    val result : kotlin.Any = apiInstance.privateAddToAddressBookGet(currency, type, address, name, tfa)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling InternalApi#privateAddToAddressBookGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling InternalApi#privateAddToAddressBookGet")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **kotlin.String**| The currency symbol | [enum: BTC, ETH]
 **type** | **kotlin.String**| Address book type | [enum: transfer, withdrawal]
 **address** | **kotlin.String**| Address in currency format, it must be in address book |
 **name** | **kotlin.String**| Name of address book entry |
 **tfa** | **kotlin.String**| TFA code, required when TFA is enabled for current account | [optional]

### Return type

[**kotlin.Any**](kotlin.Any.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="privateDisableTfaWithRecoveryCodeGet"></a>
# **privateDisableTfaWithRecoveryCodeGet**
> kotlin.Any privateDisableTfaWithRecoveryCodeGet(password, code)

Disables TFA with one time recovery code

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = InternalApi()
val password : kotlin.String = password_example // kotlin.String | The password for the subaccount
val code : kotlin.String = code_example // kotlin.String | One time recovery code
try {
    val result : kotlin.Any = apiInstance.privateDisableTfaWithRecoveryCodeGet(password, code)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling InternalApi#privateDisableTfaWithRecoveryCodeGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling InternalApi#privateDisableTfaWithRecoveryCodeGet")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **password** | **kotlin.String**| The password for the subaccount |
 **code** | **kotlin.String**| One time recovery code |

### Return type

[**kotlin.Any**](kotlin.Any.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="privateGetAddressBookGet"></a>
# **privateGetAddressBookGet**
> kotlin.Any privateGetAddressBookGet(currency, type)

Retrieves address book of given type

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = InternalApi()
val currency : kotlin.String = currency_example // kotlin.String | The currency symbol
val type : kotlin.String = type_example // kotlin.String | Address book type
try {
    val result : kotlin.Any = apiInstance.privateGetAddressBookGet(currency, type)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling InternalApi#privateGetAddressBookGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling InternalApi#privateGetAddressBookGet")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **kotlin.String**| The currency symbol | [enum: BTC, ETH]
 **type** | **kotlin.String**| Address book type | [enum: transfer, withdrawal]

### Return type

[**kotlin.Any**](kotlin.Any.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="privateRemoveFromAddressBookGet"></a>
# **privateRemoveFromAddressBookGet**
> kotlin.Any privateRemoveFromAddressBookGet(currency, type, address, tfa)

Adds new entry to address book of given type

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = InternalApi()
val currency : kotlin.String = currency_example // kotlin.String | The currency symbol
val type : kotlin.String = type_example // kotlin.String | Address book type
val address : kotlin.String = address_example // kotlin.String | Address in currency format, it must be in address book
val tfa : kotlin.String = tfa_example // kotlin.String | TFA code, required when TFA is enabled for current account
try {
    val result : kotlin.Any = apiInstance.privateRemoveFromAddressBookGet(currency, type, address, tfa)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling InternalApi#privateRemoveFromAddressBookGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling InternalApi#privateRemoveFromAddressBookGet")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **kotlin.String**| The currency symbol | [enum: BTC, ETH]
 **type** | **kotlin.String**| Address book type | [enum: transfer, withdrawal]
 **address** | **kotlin.String**| Address in currency format, it must be in address book |
 **tfa** | **kotlin.String**| TFA code, required when TFA is enabled for current account | [optional]

### Return type

[**kotlin.Any**](kotlin.Any.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="privateSubmitTransferToSubaccountGet"></a>
# **privateSubmitTransferToSubaccountGet**
> kotlin.Any privateSubmitTransferToSubaccountGet(currency, amount, destination)

Transfer funds to a subaccount.

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = InternalApi()
val currency : kotlin.String = currency_example // kotlin.String | The currency symbol
val amount : java.math.BigDecimal = 8.14 // java.math.BigDecimal | Amount of funds to be transferred
val destination : kotlin.Int = 1 // kotlin.Int | Id of destination subaccount
try {
    val result : kotlin.Any = apiInstance.privateSubmitTransferToSubaccountGet(currency, amount, destination)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling InternalApi#privateSubmitTransferToSubaccountGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling InternalApi#privateSubmitTransferToSubaccountGet")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **kotlin.String**| The currency symbol | [enum: BTC, ETH]
 **amount** | **java.math.BigDecimal**| Amount of funds to be transferred |
 **destination** | **kotlin.Int**| Id of destination subaccount |

### Return type

[**kotlin.Any**](kotlin.Any.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="privateSubmitTransferToUserGet"></a>
# **privateSubmitTransferToUserGet**
> kotlin.Any privateSubmitTransferToUserGet(currency, amount, destination, tfa)

Transfer funds to a another user.

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = InternalApi()
val currency : kotlin.String = currency_example // kotlin.String | The currency symbol
val amount : java.math.BigDecimal = 8.14 // java.math.BigDecimal | Amount of funds to be transferred
val destination : kotlin.String = destination_example // kotlin.String | Destination address from address book
val tfa : kotlin.String = tfa_example // kotlin.String | TFA code, required when TFA is enabled for current account
try {
    val result : kotlin.Any = apiInstance.privateSubmitTransferToUserGet(currency, amount, destination, tfa)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling InternalApi#privateSubmitTransferToUserGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling InternalApi#privateSubmitTransferToUserGet")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **kotlin.String**| The currency symbol | [enum: BTC, ETH]
 **amount** | **java.math.BigDecimal**| Amount of funds to be transferred |
 **destination** | **kotlin.String**| Destination address from address book |
 **tfa** | **kotlin.String**| TFA code, required when TFA is enabled for current account | [optional]

### Return type

[**kotlin.Any**](kotlin.Any.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="privateToggleDepositAddressCreationGet"></a>
# **privateToggleDepositAddressCreationGet**
> kotlin.Any privateToggleDepositAddressCreationGet(currency, state)

Enable or disable deposit address creation

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = InternalApi()
val currency : kotlin.String = currency_example // kotlin.String | The currency symbol
val state : kotlin.Boolean = true // kotlin.Boolean | 
try {
    val result : kotlin.Any = apiInstance.privateToggleDepositAddressCreationGet(currency, state)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling InternalApi#privateToggleDepositAddressCreationGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling InternalApi#privateToggleDepositAddressCreationGet")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **kotlin.String**| The currency symbol | [enum: BTC, ETH]
 **state** | **kotlin.Boolean**|  |

### Return type

[**kotlin.Any**](kotlin.Any.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="publicGetFooterGet"></a>
# **publicGetFooterGet**
> kotlin.Any publicGetFooterGet()

Get information to be displayed in the footer of the website.

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = InternalApi()
try {
    val result : kotlin.Any = apiInstance.publicGetFooterGet()
    println(result)
} catch (e: ClientException) {
    println("4xx response calling InternalApi#publicGetFooterGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling InternalApi#publicGetFooterGet")
    e.printStackTrace()
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**kotlin.Any**](kotlin.Any.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="publicGetOptionMarkPricesGet"></a>
# **publicGetOptionMarkPricesGet**
> kotlin.Any publicGetOptionMarkPricesGet(currency)

Retrives market prices and its implied volatility of options instruments

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = InternalApi()
val currency : kotlin.String = currency_example // kotlin.String | The currency symbol
try {
    val result : kotlin.Any = apiInstance.publicGetOptionMarkPricesGet(currency)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling InternalApi#publicGetOptionMarkPricesGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling InternalApi#publicGetOptionMarkPricesGet")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **kotlin.String**| The currency symbol | [enum: BTC, ETH]

### Return type

[**kotlin.Any**](kotlin.Any.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="publicValidateFieldGet"></a>
# **publicValidateFieldGet**
> kotlin.Any publicValidateFieldGet(field, value, value2)

Method used to introduce the client software connected to Deribit platform over websocket. Provided data may have an impact on the maintained connection and will be collected for internal statistical purposes. In response, Deribit will also introduce itself.

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = InternalApi()
val field : kotlin.String = field_example // kotlin.String | Name of the field to be validated, examples: postal_code, date_of_birth
val value : kotlin.String = value_example // kotlin.String | Value to be checked
val value2 : kotlin.String = value2_example // kotlin.String | Additional value to be compared with
try {
    val result : kotlin.Any = apiInstance.publicValidateFieldGet(field, value, value2)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling InternalApi#publicValidateFieldGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling InternalApi#publicValidateFieldGet")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **field** | **kotlin.String**| Name of the field to be validated, examples: postal_code, date_of_birth |
 **value** | **kotlin.String**| Value to be checked |
 **value2** | **kotlin.String**| Additional value to be compared with | [optional]

### Return type

[**kotlin.Any**](kotlin.Any.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

