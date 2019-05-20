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



## privateAddToAddressBookGet

> Object privateAddToAddressBookGet(currency, type, address, name, tfa)

Adds new entry to address book of given type

### Example

```java
// Import classes:
//import org.openapitools.client.api.WalletApi;

WalletApi apiInstance = new WalletApi();
String currency = null; // String | The currency symbol
String type = null; // String | Address book type
String address = null; // String | Address in currency format, it must be in address book
String name = Main address; // String | Name of address book entry
String tfa = null; // String | TFA code, required when TFA is enabled for current account
try {
    Object result = apiInstance.privateAddToAddressBookGet(currency, type, address, name, tfa);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling WalletApi#privateAddToAddressBookGet");
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


## privateCancelTransferByIdGet

> Object privateCancelTransferByIdGet(currency, id, tfa)

Cancel transfer

### Example

```java
// Import classes:
//import org.openapitools.client.api.WalletApi;

WalletApi apiInstance = new WalletApi();
String currency = null; // String | The currency symbol
Integer id = 12; // Integer | Id of transfer
String tfa = null; // String | TFA code, required when TFA is enabled for current account
try {
    Object result = apiInstance.privateCancelTransferByIdGet(currency, id, tfa);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling WalletApi#privateCancelTransferByIdGet");
    e.printStackTrace();
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **String**| The currency symbol | [default to null] [enum: BTC, ETH]
 **id** | **Integer**| Id of transfer | [default to null]
 **tfa** | **String**| TFA code, required when TFA is enabled for current account | [optional] [default to null]

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## privateCancelWithdrawalGet

> Object privateCancelWithdrawalGet(currency, id)

Cancels withdrawal request

### Example

```java
// Import classes:
//import org.openapitools.client.api.WalletApi;

WalletApi apiInstance = new WalletApi();
String currency = null; // String | The currency symbol
BigDecimal id = 1; // BigDecimal | The withdrawal id
try {
    Object result = apiInstance.privateCancelWithdrawalGet(currency, id);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling WalletApi#privateCancelWithdrawalGet");
    e.printStackTrace();
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **String**| The currency symbol | [default to null] [enum: BTC, ETH]
 **id** | **BigDecimal**| The withdrawal id | [default to null]

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## privateCreateDepositAddressGet

> Object privateCreateDepositAddressGet(currency)

Creates deposit address in currency

### Example

```java
// Import classes:
//import org.openapitools.client.api.WalletApi;

WalletApi apiInstance = new WalletApi();
String currency = null; // String | The currency symbol
try {
    Object result = apiInstance.privateCreateDepositAddressGet(currency);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling WalletApi#privateCreateDepositAddressGet");
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


## privateGetAddressBookGet

> Object privateGetAddressBookGet(currency, type)

Retrieves address book of given type

### Example

```java
// Import classes:
//import org.openapitools.client.api.WalletApi;

WalletApi apiInstance = new WalletApi();
String currency = null; // String | The currency symbol
String type = null; // String | Address book type
try {
    Object result = apiInstance.privateGetAddressBookGet(currency, type);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling WalletApi#privateGetAddressBookGet");
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


## privateGetCurrentDepositAddressGet

> Object privateGetCurrentDepositAddressGet(currency)

Retrieve deposit address for currency

### Example

```java
// Import classes:
//import org.openapitools.client.api.WalletApi;

WalletApi apiInstance = new WalletApi();
String currency = null; // String | The currency symbol
try {
    Object result = apiInstance.privateGetCurrentDepositAddressGet(currency);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling WalletApi#privateGetCurrentDepositAddressGet");
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


## privateGetDepositsGet

> Object privateGetDepositsGet(currency, count, offset)

Retrieve the latest users deposits

### Example

```java
// Import classes:
//import org.openapitools.client.api.WalletApi;

WalletApi apiInstance = new WalletApi();
String currency = null; // String | The currency symbol
Integer count = null; // Integer | Number of requested items, default - `10`
Integer offset = 10; // Integer | The offset for pagination, default - `0`
try {
    Object result = apiInstance.privateGetDepositsGet(currency, count, offset);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling WalletApi#privateGetDepositsGet");
    e.printStackTrace();
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **String**| The currency symbol | [default to null] [enum: BTC, ETH]
 **count** | **Integer**| Number of requested items, default - &#x60;10&#x60; | [optional] [default to null]
 **offset** | **Integer**| The offset for pagination, default - &#x60;0&#x60; | [optional] [default to null]

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## privateGetTransfersGet

> Object privateGetTransfersGet(currency, count, offset)

Adds new entry to address book of given type

### Example

```java
// Import classes:
//import org.openapitools.client.api.WalletApi;

WalletApi apiInstance = new WalletApi();
String currency = null; // String | The currency symbol
Integer count = null; // Integer | Number of requested items, default - `10`
Integer offset = 10; // Integer | The offset for pagination, default - `0`
try {
    Object result = apiInstance.privateGetTransfersGet(currency, count, offset);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling WalletApi#privateGetTransfersGet");
    e.printStackTrace();
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **String**| The currency symbol | [default to null] [enum: BTC, ETH]
 **count** | **Integer**| Number of requested items, default - &#x60;10&#x60; | [optional] [default to null]
 **offset** | **Integer**| The offset for pagination, default - &#x60;0&#x60; | [optional] [default to null]

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## privateGetWithdrawalsGet

> Object privateGetWithdrawalsGet(currency, count, offset)

Retrieve the latest users withdrawals

### Example

```java
// Import classes:
//import org.openapitools.client.api.WalletApi;

WalletApi apiInstance = new WalletApi();
String currency = null; // String | The currency symbol
Integer count = null; // Integer | Number of requested items, default - `10`
Integer offset = 10; // Integer | The offset for pagination, default - `0`
try {
    Object result = apiInstance.privateGetWithdrawalsGet(currency, count, offset);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling WalletApi#privateGetWithdrawalsGet");
    e.printStackTrace();
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **String**| The currency symbol | [default to null] [enum: BTC, ETH]
 **count** | **Integer**| Number of requested items, default - &#x60;10&#x60; | [optional] [default to null]
 **offset** | **Integer**| The offset for pagination, default - &#x60;0&#x60; | [optional] [default to null]

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
//import org.openapitools.client.api.WalletApi;

WalletApi apiInstance = new WalletApi();
String currency = null; // String | The currency symbol
String type = null; // String | Address book type
String address = null; // String | Address in currency format, it must be in address book
String tfa = null; // String | TFA code, required when TFA is enabled for current account
try {
    Object result = apiInstance.privateRemoveFromAddressBookGet(currency, type, address, tfa);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling WalletApi#privateRemoveFromAddressBookGet");
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
//import org.openapitools.client.api.WalletApi;

WalletApi apiInstance = new WalletApi();
String currency = null; // String | The currency symbol
BigDecimal amount = null; // BigDecimal | Amount of funds to be transferred
Integer destination = 1; // Integer | Id of destination subaccount
try {
    Object result = apiInstance.privateSubmitTransferToSubaccountGet(currency, amount, destination);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling WalletApi#privateSubmitTransferToSubaccountGet");
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
//import org.openapitools.client.api.WalletApi;

WalletApi apiInstance = new WalletApi();
String currency = null; // String | The currency symbol
BigDecimal amount = null; // BigDecimal | Amount of funds to be transferred
String destination = null; // String | Destination address from address book
String tfa = null; // String | TFA code, required when TFA is enabled for current account
try {
    Object result = apiInstance.privateSubmitTransferToUserGet(currency, amount, destination, tfa);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling WalletApi#privateSubmitTransferToUserGet");
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
//import org.openapitools.client.api.WalletApi;

WalletApi apiInstance = new WalletApi();
String currency = null; // String | The currency symbol
Boolean state = null; // Boolean | 
try {
    Object result = apiInstance.privateToggleDepositAddressCreationGet(currency, state);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling WalletApi#privateToggleDepositAddressCreationGet");
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


## privateWithdrawGet

> Object privateWithdrawGet(currency, address, amount, priority, tfa)

Creates a new withdrawal request

### Example

```java
// Import classes:
//import org.openapitools.client.api.WalletApi;

WalletApi apiInstance = new WalletApi();
String currency = null; // String | The currency symbol
String address = null; // String | Address in currency format, it must be in address book
BigDecimal amount = null; // BigDecimal | Amount of funds to be withdrawn
String priority = null; // String | Withdrawal priority, optional for BTC, default: `high`
String tfa = null; // String | TFA code, required when TFA is enabled for current account
try {
    Object result = apiInstance.privateWithdrawGet(currency, address, amount, priority, tfa);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling WalletApi#privateWithdrawGet");
    e.printStackTrace();
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **String**| The currency symbol | [default to null] [enum: BTC, ETH]
 **address** | **String**| Address in currency format, it must be in address book | [default to null]
 **amount** | **BigDecimal**| Amount of funds to be withdrawn | [default to null]
 **priority** | **String**| Withdrawal priority, optional for BTC, default: &#x60;high&#x60; | [optional] [default to null] [enum: insane, extreme_high, very_high, high, mid, low, very_low]
 **tfa** | **String**| TFA code, required when TFA is enabled for current account | [optional] [default to null]

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

