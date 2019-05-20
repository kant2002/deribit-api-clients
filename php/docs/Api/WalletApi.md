# OpenAPI\Client\WalletApi

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

> object privateAddToAddressBookGet($currency, $type, $address, $name, $tfa)

Adds new entry to address book of given type

### Example

```php
<?php
require_once(__DIR__ . '/vendor/autoload.php');


// Configure Bearer (Auth. Token) authorization: bearerAuth
$config = OpenAPI\Client\Configuration::getDefaultConfiguration()->setAccessToken('YOUR_ACCESS_TOKEN');


$apiInstance = new OpenAPI\Client\Api\WalletApi(
    // If you want use custom http client, pass your client which implements `GuzzleHttp\ClientInterface`.
    // This is optional, `GuzzleHttp\Client` will be used as default.
    new GuzzleHttp\Client(),
    $config
);
$currency = 'currency_example'; // string | The currency symbol
$type = 'type_example'; // string | Address book type
$address = 'address_example'; // string | Address in currency format, it must be in address book
$name = Main address; // string | Name of address book entry
$tfa = 'tfa_example'; // string | TFA code, required when TFA is enabled for current account

try {
    $result = $apiInstance->privateAddToAddressBookGet($currency, $type, $address, $name, $tfa);
    print_r($result);
} catch (Exception $e) {
    echo 'Exception when calling WalletApi->privateAddToAddressBookGet: ', $e->getMessage(), PHP_EOL;
}
?>
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **string**| The currency symbol |
 **type** | **string**| Address book type |
 **address** | **string**| Address in currency format, it must be in address book |
 **name** | **string**| Name of address book entry |
 **tfa** | **string**| TFA code, required when TFA is enabled for current account | [optional]

### Return type

**object**

### Authorization

[bearerAuth](../../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../../README.md#documentation-for-models)
[[Back to README]](../../README.md)


## privateCancelTransferByIdGet

> object privateCancelTransferByIdGet($currency, $id, $tfa)

Cancel transfer

### Example

```php
<?php
require_once(__DIR__ . '/vendor/autoload.php');


// Configure Bearer (Auth. Token) authorization: bearerAuth
$config = OpenAPI\Client\Configuration::getDefaultConfiguration()->setAccessToken('YOUR_ACCESS_TOKEN');


$apiInstance = new OpenAPI\Client\Api\WalletApi(
    // If you want use custom http client, pass your client which implements `GuzzleHttp\ClientInterface`.
    // This is optional, `GuzzleHttp\Client` will be used as default.
    new GuzzleHttp\Client(),
    $config
);
$currency = 'currency_example'; // string | The currency symbol
$id = 12; // int | Id of transfer
$tfa = 'tfa_example'; // string | TFA code, required when TFA is enabled for current account

try {
    $result = $apiInstance->privateCancelTransferByIdGet($currency, $id, $tfa);
    print_r($result);
} catch (Exception $e) {
    echo 'Exception when calling WalletApi->privateCancelTransferByIdGet: ', $e->getMessage(), PHP_EOL;
}
?>
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **string**| The currency symbol |
 **id** | **int**| Id of transfer |
 **tfa** | **string**| TFA code, required when TFA is enabled for current account | [optional]

### Return type

**object**

### Authorization

[bearerAuth](../../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../../README.md#documentation-for-models)
[[Back to README]](../../README.md)


## privateCancelWithdrawalGet

> object privateCancelWithdrawalGet($currency, $id)

Cancels withdrawal request

### Example

```php
<?php
require_once(__DIR__ . '/vendor/autoload.php');


// Configure Bearer (Auth. Token) authorization: bearerAuth
$config = OpenAPI\Client\Configuration::getDefaultConfiguration()->setAccessToken('YOUR_ACCESS_TOKEN');


$apiInstance = new OpenAPI\Client\Api\WalletApi(
    // If you want use custom http client, pass your client which implements `GuzzleHttp\ClientInterface`.
    // This is optional, `GuzzleHttp\Client` will be used as default.
    new GuzzleHttp\Client(),
    $config
);
$currency = 'currency_example'; // string | The currency symbol
$id = 1; // float | The withdrawal id

try {
    $result = $apiInstance->privateCancelWithdrawalGet($currency, $id);
    print_r($result);
} catch (Exception $e) {
    echo 'Exception when calling WalletApi->privateCancelWithdrawalGet: ', $e->getMessage(), PHP_EOL;
}
?>
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **string**| The currency symbol |
 **id** | **float**| The withdrawal id |

### Return type

**object**

### Authorization

[bearerAuth](../../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../../README.md#documentation-for-models)
[[Back to README]](../../README.md)


## privateCreateDepositAddressGet

> object privateCreateDepositAddressGet($currency)

Creates deposit address in currency

### Example

```php
<?php
require_once(__DIR__ . '/vendor/autoload.php');


// Configure Bearer (Auth. Token) authorization: bearerAuth
$config = OpenAPI\Client\Configuration::getDefaultConfiguration()->setAccessToken('YOUR_ACCESS_TOKEN');


$apiInstance = new OpenAPI\Client\Api\WalletApi(
    // If you want use custom http client, pass your client which implements `GuzzleHttp\ClientInterface`.
    // This is optional, `GuzzleHttp\Client` will be used as default.
    new GuzzleHttp\Client(),
    $config
);
$currency = 'currency_example'; // string | The currency symbol

try {
    $result = $apiInstance->privateCreateDepositAddressGet($currency);
    print_r($result);
} catch (Exception $e) {
    echo 'Exception when calling WalletApi->privateCreateDepositAddressGet: ', $e->getMessage(), PHP_EOL;
}
?>
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **string**| The currency symbol |

### Return type

**object**

### Authorization

[bearerAuth](../../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../../README.md#documentation-for-models)
[[Back to README]](../../README.md)


## privateGetAddressBookGet

> object privateGetAddressBookGet($currency, $type)

Retrieves address book of given type

### Example

```php
<?php
require_once(__DIR__ . '/vendor/autoload.php');


// Configure Bearer (Auth. Token) authorization: bearerAuth
$config = OpenAPI\Client\Configuration::getDefaultConfiguration()->setAccessToken('YOUR_ACCESS_TOKEN');


$apiInstance = new OpenAPI\Client\Api\WalletApi(
    // If you want use custom http client, pass your client which implements `GuzzleHttp\ClientInterface`.
    // This is optional, `GuzzleHttp\Client` will be used as default.
    new GuzzleHttp\Client(),
    $config
);
$currency = 'currency_example'; // string | The currency symbol
$type = 'type_example'; // string | Address book type

try {
    $result = $apiInstance->privateGetAddressBookGet($currency, $type);
    print_r($result);
} catch (Exception $e) {
    echo 'Exception when calling WalletApi->privateGetAddressBookGet: ', $e->getMessage(), PHP_EOL;
}
?>
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **string**| The currency symbol |
 **type** | **string**| Address book type |

### Return type

**object**

### Authorization

[bearerAuth](../../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../../README.md#documentation-for-models)
[[Back to README]](../../README.md)


## privateGetCurrentDepositAddressGet

> object privateGetCurrentDepositAddressGet($currency)

Retrieve deposit address for currency

### Example

```php
<?php
require_once(__DIR__ . '/vendor/autoload.php');


// Configure Bearer (Auth. Token) authorization: bearerAuth
$config = OpenAPI\Client\Configuration::getDefaultConfiguration()->setAccessToken('YOUR_ACCESS_TOKEN');


$apiInstance = new OpenAPI\Client\Api\WalletApi(
    // If you want use custom http client, pass your client which implements `GuzzleHttp\ClientInterface`.
    // This is optional, `GuzzleHttp\Client` will be used as default.
    new GuzzleHttp\Client(),
    $config
);
$currency = 'currency_example'; // string | The currency symbol

try {
    $result = $apiInstance->privateGetCurrentDepositAddressGet($currency);
    print_r($result);
} catch (Exception $e) {
    echo 'Exception when calling WalletApi->privateGetCurrentDepositAddressGet: ', $e->getMessage(), PHP_EOL;
}
?>
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **string**| The currency symbol |

### Return type

**object**

### Authorization

[bearerAuth](../../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../../README.md#documentation-for-models)
[[Back to README]](../../README.md)


## privateGetDepositsGet

> object privateGetDepositsGet($currency, $count, $offset)

Retrieve the latest users deposits

### Example

```php
<?php
require_once(__DIR__ . '/vendor/autoload.php');


// Configure Bearer (Auth. Token) authorization: bearerAuth
$config = OpenAPI\Client\Configuration::getDefaultConfiguration()->setAccessToken('YOUR_ACCESS_TOKEN');


$apiInstance = new OpenAPI\Client\Api\WalletApi(
    // If you want use custom http client, pass your client which implements `GuzzleHttp\ClientInterface`.
    // This is optional, `GuzzleHttp\Client` will be used as default.
    new GuzzleHttp\Client(),
    $config
);
$currency = 'currency_example'; // string | The currency symbol
$count = 56; // int | Number of requested items, default - `10`
$offset = 10; // int | The offset for pagination, default - `0`

try {
    $result = $apiInstance->privateGetDepositsGet($currency, $count, $offset);
    print_r($result);
} catch (Exception $e) {
    echo 'Exception when calling WalletApi->privateGetDepositsGet: ', $e->getMessage(), PHP_EOL;
}
?>
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **string**| The currency symbol |
 **count** | **int**| Number of requested items, default - &#x60;10&#x60; | [optional]
 **offset** | **int**| The offset for pagination, default - &#x60;0&#x60; | [optional]

### Return type

**object**

### Authorization

[bearerAuth](../../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../../README.md#documentation-for-models)
[[Back to README]](../../README.md)


## privateGetTransfersGet

> object privateGetTransfersGet($currency, $count, $offset)

Adds new entry to address book of given type

### Example

```php
<?php
require_once(__DIR__ . '/vendor/autoload.php');


// Configure Bearer (Auth. Token) authorization: bearerAuth
$config = OpenAPI\Client\Configuration::getDefaultConfiguration()->setAccessToken('YOUR_ACCESS_TOKEN');


$apiInstance = new OpenAPI\Client\Api\WalletApi(
    // If you want use custom http client, pass your client which implements `GuzzleHttp\ClientInterface`.
    // This is optional, `GuzzleHttp\Client` will be used as default.
    new GuzzleHttp\Client(),
    $config
);
$currency = 'currency_example'; // string | The currency symbol
$count = 56; // int | Number of requested items, default - `10`
$offset = 10; // int | The offset for pagination, default - `0`

try {
    $result = $apiInstance->privateGetTransfersGet($currency, $count, $offset);
    print_r($result);
} catch (Exception $e) {
    echo 'Exception when calling WalletApi->privateGetTransfersGet: ', $e->getMessage(), PHP_EOL;
}
?>
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **string**| The currency symbol |
 **count** | **int**| Number of requested items, default - &#x60;10&#x60; | [optional]
 **offset** | **int**| The offset for pagination, default - &#x60;0&#x60; | [optional]

### Return type

**object**

### Authorization

[bearerAuth](../../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../../README.md#documentation-for-models)
[[Back to README]](../../README.md)


## privateGetWithdrawalsGet

> object privateGetWithdrawalsGet($currency, $count, $offset)

Retrieve the latest users withdrawals

### Example

```php
<?php
require_once(__DIR__ . '/vendor/autoload.php');


// Configure Bearer (Auth. Token) authorization: bearerAuth
$config = OpenAPI\Client\Configuration::getDefaultConfiguration()->setAccessToken('YOUR_ACCESS_TOKEN');


$apiInstance = new OpenAPI\Client\Api\WalletApi(
    // If you want use custom http client, pass your client which implements `GuzzleHttp\ClientInterface`.
    // This is optional, `GuzzleHttp\Client` will be used as default.
    new GuzzleHttp\Client(),
    $config
);
$currency = 'currency_example'; // string | The currency symbol
$count = 56; // int | Number of requested items, default - `10`
$offset = 10; // int | The offset for pagination, default - `0`

try {
    $result = $apiInstance->privateGetWithdrawalsGet($currency, $count, $offset);
    print_r($result);
} catch (Exception $e) {
    echo 'Exception when calling WalletApi->privateGetWithdrawalsGet: ', $e->getMessage(), PHP_EOL;
}
?>
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **string**| The currency symbol |
 **count** | **int**| Number of requested items, default - &#x60;10&#x60; | [optional]
 **offset** | **int**| The offset for pagination, default - &#x60;0&#x60; | [optional]

### Return type

**object**

### Authorization

[bearerAuth](../../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../../README.md#documentation-for-models)
[[Back to README]](../../README.md)


## privateRemoveFromAddressBookGet

> object privateRemoveFromAddressBookGet($currency, $type, $address, $tfa)

Adds new entry to address book of given type

### Example

```php
<?php
require_once(__DIR__ . '/vendor/autoload.php');


// Configure Bearer (Auth. Token) authorization: bearerAuth
$config = OpenAPI\Client\Configuration::getDefaultConfiguration()->setAccessToken('YOUR_ACCESS_TOKEN');


$apiInstance = new OpenAPI\Client\Api\WalletApi(
    // If you want use custom http client, pass your client which implements `GuzzleHttp\ClientInterface`.
    // This is optional, `GuzzleHttp\Client` will be used as default.
    new GuzzleHttp\Client(),
    $config
);
$currency = 'currency_example'; // string | The currency symbol
$type = 'type_example'; // string | Address book type
$address = 'address_example'; // string | Address in currency format, it must be in address book
$tfa = 'tfa_example'; // string | TFA code, required when TFA is enabled for current account

try {
    $result = $apiInstance->privateRemoveFromAddressBookGet($currency, $type, $address, $tfa);
    print_r($result);
} catch (Exception $e) {
    echo 'Exception when calling WalletApi->privateRemoveFromAddressBookGet: ', $e->getMessage(), PHP_EOL;
}
?>
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **string**| The currency symbol |
 **type** | **string**| Address book type |
 **address** | **string**| Address in currency format, it must be in address book |
 **tfa** | **string**| TFA code, required when TFA is enabled for current account | [optional]

### Return type

**object**

### Authorization

[bearerAuth](../../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../../README.md#documentation-for-models)
[[Back to README]](../../README.md)


## privateSubmitTransferToSubaccountGet

> object privateSubmitTransferToSubaccountGet($currency, $amount, $destination)

Transfer funds to a subaccount.

### Example

```php
<?php
require_once(__DIR__ . '/vendor/autoload.php');


// Configure Bearer (Auth. Token) authorization: bearerAuth
$config = OpenAPI\Client\Configuration::getDefaultConfiguration()->setAccessToken('YOUR_ACCESS_TOKEN');


$apiInstance = new OpenAPI\Client\Api\WalletApi(
    // If you want use custom http client, pass your client which implements `GuzzleHttp\ClientInterface`.
    // This is optional, `GuzzleHttp\Client` will be used as default.
    new GuzzleHttp\Client(),
    $config
);
$currency = 'currency_example'; // string | The currency symbol
$amount = 3.4; // float | Amount of funds to be transferred
$destination = 1; // int | Id of destination subaccount

try {
    $result = $apiInstance->privateSubmitTransferToSubaccountGet($currency, $amount, $destination);
    print_r($result);
} catch (Exception $e) {
    echo 'Exception when calling WalletApi->privateSubmitTransferToSubaccountGet: ', $e->getMessage(), PHP_EOL;
}
?>
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **string**| The currency symbol |
 **amount** | **float**| Amount of funds to be transferred |
 **destination** | **int**| Id of destination subaccount |

### Return type

**object**

### Authorization

[bearerAuth](../../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../../README.md#documentation-for-models)
[[Back to README]](../../README.md)


## privateSubmitTransferToUserGet

> object privateSubmitTransferToUserGet($currency, $amount, $destination, $tfa)

Transfer funds to a another user.

### Example

```php
<?php
require_once(__DIR__ . '/vendor/autoload.php');


// Configure Bearer (Auth. Token) authorization: bearerAuth
$config = OpenAPI\Client\Configuration::getDefaultConfiguration()->setAccessToken('YOUR_ACCESS_TOKEN');


$apiInstance = new OpenAPI\Client\Api\WalletApi(
    // If you want use custom http client, pass your client which implements `GuzzleHttp\ClientInterface`.
    // This is optional, `GuzzleHttp\Client` will be used as default.
    new GuzzleHttp\Client(),
    $config
);
$currency = 'currency_example'; // string | The currency symbol
$amount = 3.4; // float | Amount of funds to be transferred
$destination = 'destination_example'; // string | Destination address from address book
$tfa = 'tfa_example'; // string | TFA code, required when TFA is enabled for current account

try {
    $result = $apiInstance->privateSubmitTransferToUserGet($currency, $amount, $destination, $tfa);
    print_r($result);
} catch (Exception $e) {
    echo 'Exception when calling WalletApi->privateSubmitTransferToUserGet: ', $e->getMessage(), PHP_EOL;
}
?>
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **string**| The currency symbol |
 **amount** | **float**| Amount of funds to be transferred |
 **destination** | **string**| Destination address from address book |
 **tfa** | **string**| TFA code, required when TFA is enabled for current account | [optional]

### Return type

**object**

### Authorization

[bearerAuth](../../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../../README.md#documentation-for-models)
[[Back to README]](../../README.md)


## privateToggleDepositAddressCreationGet

> object privateToggleDepositAddressCreationGet($currency, $state)

Enable or disable deposit address creation

### Example

```php
<?php
require_once(__DIR__ . '/vendor/autoload.php');


// Configure Bearer (Auth. Token) authorization: bearerAuth
$config = OpenAPI\Client\Configuration::getDefaultConfiguration()->setAccessToken('YOUR_ACCESS_TOKEN');


$apiInstance = new OpenAPI\Client\Api\WalletApi(
    // If you want use custom http client, pass your client which implements `GuzzleHttp\ClientInterface`.
    // This is optional, `GuzzleHttp\Client` will be used as default.
    new GuzzleHttp\Client(),
    $config
);
$currency = 'currency_example'; // string | The currency symbol
$state = True; // bool | 

try {
    $result = $apiInstance->privateToggleDepositAddressCreationGet($currency, $state);
    print_r($result);
} catch (Exception $e) {
    echo 'Exception when calling WalletApi->privateToggleDepositAddressCreationGet: ', $e->getMessage(), PHP_EOL;
}
?>
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **string**| The currency symbol |
 **state** | **bool**|  |

### Return type

**object**

### Authorization

[bearerAuth](../../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../../README.md#documentation-for-models)
[[Back to README]](../../README.md)


## privateWithdrawGet

> object privateWithdrawGet($currency, $address, $amount, $priority, $tfa)

Creates a new withdrawal request

### Example

```php
<?php
require_once(__DIR__ . '/vendor/autoload.php');


// Configure Bearer (Auth. Token) authorization: bearerAuth
$config = OpenAPI\Client\Configuration::getDefaultConfiguration()->setAccessToken('YOUR_ACCESS_TOKEN');


$apiInstance = new OpenAPI\Client\Api\WalletApi(
    // If you want use custom http client, pass your client which implements `GuzzleHttp\ClientInterface`.
    // This is optional, `GuzzleHttp\Client` will be used as default.
    new GuzzleHttp\Client(),
    $config
);
$currency = 'currency_example'; // string | The currency symbol
$address = 'address_example'; // string | Address in currency format, it must be in address book
$amount = 3.4; // float | Amount of funds to be withdrawn
$priority = 'priority_example'; // string | Withdrawal priority, optional for BTC, default: `high`
$tfa = 'tfa_example'; // string | TFA code, required when TFA is enabled for current account

try {
    $result = $apiInstance->privateWithdrawGet($currency, $address, $amount, $priority, $tfa);
    print_r($result);
} catch (Exception $e) {
    echo 'Exception when calling WalletApi->privateWithdrawGet: ', $e->getMessage(), PHP_EOL;
}
?>
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **string**| The currency symbol |
 **address** | **string**| Address in currency format, it must be in address book |
 **amount** | **float**| Amount of funds to be withdrawn |
 **priority** | **string**| Withdrawal priority, optional for BTC, default: &#x60;high&#x60; | [optional]
 **tfa** | **string**| TFA code, required when TFA is enabled for current account | [optional]

### Return type

**object**

### Authorization

[bearerAuth](../../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../../README.md#documentation-for-models)
[[Back to README]](../../README.md)

