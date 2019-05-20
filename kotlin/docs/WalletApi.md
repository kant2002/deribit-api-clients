# WalletApi

All URIs are relative to *https://www.deribit.com/api/v2*

Method | HTTP request | Description
------------- | ------------- | -------------
[**privateAddToAddressBookGet**](WalletApi.md#privateAddToAddressBookGet) | **GET** /private/add_to_address_book | Adds new entry to address book of given type
[**privateCancelTransferByIdGet**](WalletApi.md#privateCancelTransferByIdGet) | **GET** /private/cancel_transfer_by_id | Cancel transfer
[**privateCancelWithdrawalGet**](WalletApi.md#privateCancelWithdrawalGet) | **GET** /private/cancel_withdrawal | Cancels withdrawal request
[**privateCreateDepositAddressGet**](WalletApi.md#privateCreateDepositAddressGet) | **GET** /private/create_deposit_address | Creates deposit address in currency
[**privateGetAddressBookGet**](WalletApi.md#privateGetAddressBookGet) | **GET** /private/get_address_book | Retrieves address book of given type
[**privateGetCurrentDepositAddressGet**](WalletApi.md#privateGetCurrentDepositAddressGet) | **GET** /private/get_current_deposit_address | Retrieve deposit address for currency
[**privateGetDepositsGet**](WalletApi.md#privateGetDepositsGet) | **GET** /private/get_deposits | Retrieve the latest users deposits
[**privateGetTransfersGet**](WalletApi.md#privateGetTransfersGet) | **GET** /private/get_transfers | Adds new entry to address book of given type
[**privateGetWithdrawalsGet**](WalletApi.md#privateGetWithdrawalsGet) | **GET** /private/get_withdrawals | Retrieve the latest users withdrawals
[**privateRemoveFromAddressBookGet**](WalletApi.md#privateRemoveFromAddressBookGet) | **GET** /private/remove_from_address_book | Adds new entry to address book of given type
[**privateSubmitTransferToSubaccountGet**](WalletApi.md#privateSubmitTransferToSubaccountGet) | **GET** /private/submit_transfer_to_subaccount | Transfer funds to a subaccount.
[**privateSubmitTransferToUserGet**](WalletApi.md#privateSubmitTransferToUserGet) | **GET** /private/submit_transfer_to_user | Transfer funds to a another user.
[**privateToggleDepositAddressCreationGet**](WalletApi.md#privateToggleDepositAddressCreationGet) | **GET** /private/toggle_deposit_address_creation | Enable or disable deposit address creation
[**privateWithdrawGet**](WalletApi.md#privateWithdrawGet) | **GET** /private/withdraw | Creates a new withdrawal request


<a name="privateAddToAddressBookGet"></a>
# **privateAddToAddressBookGet**
> kotlin.Any privateAddToAddressBookGet(currency, type, address, name, tfa)

Adds new entry to address book of given type

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = WalletApi()
val currency : kotlin.String = currency_example // kotlin.String | The currency symbol
val type : kotlin.String = type_example // kotlin.String | Address book type
val address : kotlin.String = address_example // kotlin.String | Address in currency format, it must be in address book
val name : kotlin.String = Main address // kotlin.String | Name of address book entry
val tfa : kotlin.String = tfa_example // kotlin.String | TFA code, required when TFA is enabled for current account
try {
    val result : kotlin.Any = apiInstance.privateAddToAddressBookGet(currency, type, address, name, tfa)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling WalletApi#privateAddToAddressBookGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling WalletApi#privateAddToAddressBookGet")
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

<a name="privateCancelTransferByIdGet"></a>
# **privateCancelTransferByIdGet**
> kotlin.Any privateCancelTransferByIdGet(currency, id, tfa)

Cancel transfer

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = WalletApi()
val currency : kotlin.String = currency_example // kotlin.String | The currency symbol
val id : kotlin.Int = 12 // kotlin.Int | Id of transfer
val tfa : kotlin.String = tfa_example // kotlin.String | TFA code, required when TFA is enabled for current account
try {
    val result : kotlin.Any = apiInstance.privateCancelTransferByIdGet(currency, id, tfa)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling WalletApi#privateCancelTransferByIdGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling WalletApi#privateCancelTransferByIdGet")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **kotlin.String**| The currency symbol | [enum: BTC, ETH]
 **id** | **kotlin.Int**| Id of transfer |
 **tfa** | **kotlin.String**| TFA code, required when TFA is enabled for current account | [optional]

### Return type

[**kotlin.Any**](kotlin.Any.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="privateCancelWithdrawalGet"></a>
# **privateCancelWithdrawalGet**
> kotlin.Any privateCancelWithdrawalGet(currency, id)

Cancels withdrawal request

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = WalletApi()
val currency : kotlin.String = currency_example // kotlin.String | The currency symbol
val id : java.math.BigDecimal = 1 // java.math.BigDecimal | The withdrawal id
try {
    val result : kotlin.Any = apiInstance.privateCancelWithdrawalGet(currency, id)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling WalletApi#privateCancelWithdrawalGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling WalletApi#privateCancelWithdrawalGet")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **kotlin.String**| The currency symbol | [enum: BTC, ETH]
 **id** | **java.math.BigDecimal**| The withdrawal id |

### Return type

[**kotlin.Any**](kotlin.Any.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="privateCreateDepositAddressGet"></a>
# **privateCreateDepositAddressGet**
> kotlin.Any privateCreateDepositAddressGet(currency)

Creates deposit address in currency

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = WalletApi()
val currency : kotlin.String = currency_example // kotlin.String | The currency symbol
try {
    val result : kotlin.Any = apiInstance.privateCreateDepositAddressGet(currency)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling WalletApi#privateCreateDepositAddressGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling WalletApi#privateCreateDepositAddressGet")
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

<a name="privateGetAddressBookGet"></a>
# **privateGetAddressBookGet**
> kotlin.Any privateGetAddressBookGet(currency, type)

Retrieves address book of given type

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = WalletApi()
val currency : kotlin.String = currency_example // kotlin.String | The currency symbol
val type : kotlin.String = type_example // kotlin.String | Address book type
try {
    val result : kotlin.Any = apiInstance.privateGetAddressBookGet(currency, type)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling WalletApi#privateGetAddressBookGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling WalletApi#privateGetAddressBookGet")
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

<a name="privateGetCurrentDepositAddressGet"></a>
# **privateGetCurrentDepositAddressGet**
> kotlin.Any privateGetCurrentDepositAddressGet(currency)

Retrieve deposit address for currency

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = WalletApi()
val currency : kotlin.String = currency_example // kotlin.String | The currency symbol
try {
    val result : kotlin.Any = apiInstance.privateGetCurrentDepositAddressGet(currency)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling WalletApi#privateGetCurrentDepositAddressGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling WalletApi#privateGetCurrentDepositAddressGet")
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

<a name="privateGetDepositsGet"></a>
# **privateGetDepositsGet**
> kotlin.Any privateGetDepositsGet(currency, count, offset)

Retrieve the latest users deposits

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = WalletApi()
val currency : kotlin.String = currency_example // kotlin.String | The currency symbol
val count : kotlin.Int = 56 // kotlin.Int | Number of requested items, default - `10`
val offset : kotlin.Int = 10 // kotlin.Int | The offset for pagination, default - `0`
try {
    val result : kotlin.Any = apiInstance.privateGetDepositsGet(currency, count, offset)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling WalletApi#privateGetDepositsGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling WalletApi#privateGetDepositsGet")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **kotlin.String**| The currency symbol | [enum: BTC, ETH]
 **count** | **kotlin.Int**| Number of requested items, default - &#x60;10&#x60; | [optional]
 **offset** | **kotlin.Int**| The offset for pagination, default - &#x60;0&#x60; | [optional]

### Return type

[**kotlin.Any**](kotlin.Any.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="privateGetTransfersGet"></a>
# **privateGetTransfersGet**
> kotlin.Any privateGetTransfersGet(currency, count, offset)

Adds new entry to address book of given type

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = WalletApi()
val currency : kotlin.String = currency_example // kotlin.String | The currency symbol
val count : kotlin.Int = 56 // kotlin.Int | Number of requested items, default - `10`
val offset : kotlin.Int = 10 // kotlin.Int | The offset for pagination, default - `0`
try {
    val result : kotlin.Any = apiInstance.privateGetTransfersGet(currency, count, offset)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling WalletApi#privateGetTransfersGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling WalletApi#privateGetTransfersGet")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **kotlin.String**| The currency symbol | [enum: BTC, ETH]
 **count** | **kotlin.Int**| Number of requested items, default - &#x60;10&#x60; | [optional]
 **offset** | **kotlin.Int**| The offset for pagination, default - &#x60;0&#x60; | [optional]

### Return type

[**kotlin.Any**](kotlin.Any.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="privateGetWithdrawalsGet"></a>
# **privateGetWithdrawalsGet**
> kotlin.Any privateGetWithdrawalsGet(currency, count, offset)

Retrieve the latest users withdrawals

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = WalletApi()
val currency : kotlin.String = currency_example // kotlin.String | The currency symbol
val count : kotlin.Int = 56 // kotlin.Int | Number of requested items, default - `10`
val offset : kotlin.Int = 10 // kotlin.Int | The offset for pagination, default - `0`
try {
    val result : kotlin.Any = apiInstance.privateGetWithdrawalsGet(currency, count, offset)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling WalletApi#privateGetWithdrawalsGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling WalletApi#privateGetWithdrawalsGet")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **kotlin.String**| The currency symbol | [enum: BTC, ETH]
 **count** | **kotlin.Int**| Number of requested items, default - &#x60;10&#x60; | [optional]
 **offset** | **kotlin.Int**| The offset for pagination, default - &#x60;0&#x60; | [optional]

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

val apiInstance = WalletApi()
val currency : kotlin.String = currency_example // kotlin.String | The currency symbol
val type : kotlin.String = type_example // kotlin.String | Address book type
val address : kotlin.String = address_example // kotlin.String | Address in currency format, it must be in address book
val tfa : kotlin.String = tfa_example // kotlin.String | TFA code, required when TFA is enabled for current account
try {
    val result : kotlin.Any = apiInstance.privateRemoveFromAddressBookGet(currency, type, address, tfa)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling WalletApi#privateRemoveFromAddressBookGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling WalletApi#privateRemoveFromAddressBookGet")
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

val apiInstance = WalletApi()
val currency : kotlin.String = currency_example // kotlin.String | The currency symbol
val amount : java.math.BigDecimal = 8.14 // java.math.BigDecimal | Amount of funds to be transferred
val destination : kotlin.Int = 1 // kotlin.Int | Id of destination subaccount
try {
    val result : kotlin.Any = apiInstance.privateSubmitTransferToSubaccountGet(currency, amount, destination)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling WalletApi#privateSubmitTransferToSubaccountGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling WalletApi#privateSubmitTransferToSubaccountGet")
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

val apiInstance = WalletApi()
val currency : kotlin.String = currency_example // kotlin.String | The currency symbol
val amount : java.math.BigDecimal = 8.14 // java.math.BigDecimal | Amount of funds to be transferred
val destination : kotlin.String = destination_example // kotlin.String | Destination address from address book
val tfa : kotlin.String = tfa_example // kotlin.String | TFA code, required when TFA is enabled for current account
try {
    val result : kotlin.Any = apiInstance.privateSubmitTransferToUserGet(currency, amount, destination, tfa)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling WalletApi#privateSubmitTransferToUserGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling WalletApi#privateSubmitTransferToUserGet")
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

val apiInstance = WalletApi()
val currency : kotlin.String = currency_example // kotlin.String | The currency symbol
val state : kotlin.Boolean = true // kotlin.Boolean | 
try {
    val result : kotlin.Any = apiInstance.privateToggleDepositAddressCreationGet(currency, state)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling WalletApi#privateToggleDepositAddressCreationGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling WalletApi#privateToggleDepositAddressCreationGet")
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

<a name="privateWithdrawGet"></a>
# **privateWithdrawGet**
> kotlin.Any privateWithdrawGet(currency, address, amount, priority, tfa)

Creates a new withdrawal request

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = WalletApi()
val currency : kotlin.String = currency_example // kotlin.String | The currency symbol
val address : kotlin.String = address_example // kotlin.String | Address in currency format, it must be in address book
val amount : java.math.BigDecimal = 8.14 // java.math.BigDecimal | Amount of funds to be withdrawn
val priority : kotlin.String = priority_example // kotlin.String | Withdrawal priority, optional for BTC, default: `high`
val tfa : kotlin.String = tfa_example // kotlin.String | TFA code, required when TFA is enabled for current account
try {
    val result : kotlin.Any = apiInstance.privateWithdrawGet(currency, address, amount, priority, tfa)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling WalletApi#privateWithdrawGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling WalletApi#privateWithdrawGet")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **kotlin.String**| The currency symbol | [enum: BTC, ETH]
 **address** | **kotlin.String**| Address in currency format, it must be in address book |
 **amount** | **java.math.BigDecimal**| Amount of funds to be withdrawn |
 **priority** | **kotlin.String**| Withdrawal priority, optional for BTC, default: &#x60;high&#x60; | [optional] [enum: insane, extreme_high, very_high, high, mid, low, very_low]
 **tfa** | **kotlin.String**| TFA code, required when TFA is enabled for current account | [optional]

### Return type

[**kotlin.Any**](kotlin.Any.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

