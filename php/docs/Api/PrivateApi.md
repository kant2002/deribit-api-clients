# OpenAPI\Client\PrivateApi

All URIs are relative to *https://www.deribit.com/api/v2*

Method | HTTP request | Description
------------- | ------------- | -------------
[**privateAddToAddressBookGet**](PrivateApi.md#privateAddToAddressBookGet) | **GET** /private/add_to_address_book | Adds new entry to address book of given type
[**privateBuyGet**](PrivateApi.md#privateBuyGet) | **GET** /private/buy | Places a buy order for an instrument.
[**privateCancelAllByCurrencyGet**](PrivateApi.md#privateCancelAllByCurrencyGet) | **GET** /private/cancel_all_by_currency | Cancels all orders by currency, optionally filtered by instrument kind and/or order type.
[**privateCancelAllByInstrumentGet**](PrivateApi.md#privateCancelAllByInstrumentGet) | **GET** /private/cancel_all_by_instrument | Cancels all orders by instrument, optionally filtered by order type.
[**privateCancelAllGet**](PrivateApi.md#privateCancelAllGet) | **GET** /private/cancel_all | This method cancels all users orders and stop orders within all currencies and instrument kinds.
[**privateCancelGet**](PrivateApi.md#privateCancelGet) | **GET** /private/cancel | Cancel an order, specified by order id
[**privateCancelTransferByIdGet**](PrivateApi.md#privateCancelTransferByIdGet) | **GET** /private/cancel_transfer_by_id | Cancel transfer
[**privateCancelWithdrawalGet**](PrivateApi.md#privateCancelWithdrawalGet) | **GET** /private/cancel_withdrawal | Cancels withdrawal request
[**privateChangeSubaccountNameGet**](PrivateApi.md#privateChangeSubaccountNameGet) | **GET** /private/change_subaccount_name | Change the user name for a subaccount
[**privateClosePositionGet**](PrivateApi.md#privateClosePositionGet) | **GET** /private/close_position | Makes closing position reduce only order .
[**privateCreateDepositAddressGet**](PrivateApi.md#privateCreateDepositAddressGet) | **GET** /private/create_deposit_address | Creates deposit address in currency
[**privateCreateSubaccountGet**](PrivateApi.md#privateCreateSubaccountGet) | **GET** /private/create_subaccount | Create a new subaccount
[**privateDisableTfaForSubaccountGet**](PrivateApi.md#privateDisableTfaForSubaccountGet) | **GET** /private/disable_tfa_for_subaccount | Disable two factor authentication for a subaccount.
[**privateDisableTfaWithRecoveryCodeGet**](PrivateApi.md#privateDisableTfaWithRecoveryCodeGet) | **GET** /private/disable_tfa_with_recovery_code | Disables TFA with one time recovery code
[**privateEditGet**](PrivateApi.md#privateEditGet) | **GET** /private/edit | Change price, amount and/or other properties of an order.
[**privateGetAccountSummaryGet**](PrivateApi.md#privateGetAccountSummaryGet) | **GET** /private/get_account_summary | Retrieves user account summary.
[**privateGetAddressBookGet**](PrivateApi.md#privateGetAddressBookGet) | **GET** /private/get_address_book | Retrieves address book of given type
[**privateGetCurrentDepositAddressGet**](PrivateApi.md#privateGetCurrentDepositAddressGet) | **GET** /private/get_current_deposit_address | Retrieve deposit address for currency
[**privateGetDepositsGet**](PrivateApi.md#privateGetDepositsGet) | **GET** /private/get_deposits | Retrieve the latest users deposits
[**privateGetEmailLanguageGet**](PrivateApi.md#privateGetEmailLanguageGet) | **GET** /private/get_email_language | Retrieves the language to be used for emails.
[**privateGetMarginsGet**](PrivateApi.md#privateGetMarginsGet) | **GET** /private/get_margins | Get margins for given instrument, amount and price.
[**privateGetNewAnnouncementsGet**](PrivateApi.md#privateGetNewAnnouncementsGet) | **GET** /private/get_new_announcements | Retrieves announcements that have not been marked read by the user.
[**privateGetOpenOrdersByCurrencyGet**](PrivateApi.md#privateGetOpenOrdersByCurrencyGet) | **GET** /private/get_open_orders_by_currency | Retrieves list of user&#39;s open orders.
[**privateGetOpenOrdersByInstrumentGet**](PrivateApi.md#privateGetOpenOrdersByInstrumentGet) | **GET** /private/get_open_orders_by_instrument | Retrieves list of user&#39;s open orders within given Instrument.
[**privateGetOrderHistoryByCurrencyGet**](PrivateApi.md#privateGetOrderHistoryByCurrencyGet) | **GET** /private/get_order_history_by_currency | Retrieves history of orders that have been partially or fully filled.
[**privateGetOrderHistoryByInstrumentGet**](PrivateApi.md#privateGetOrderHistoryByInstrumentGet) | **GET** /private/get_order_history_by_instrument | Retrieves history of orders that have been partially or fully filled.
[**privateGetOrderMarginByIdsGet**](PrivateApi.md#privateGetOrderMarginByIdsGet) | **GET** /private/get_order_margin_by_ids | Retrieves initial margins of given orders
[**privateGetOrderStateGet**](PrivateApi.md#privateGetOrderStateGet) | **GET** /private/get_order_state | Retrieve the current state of an order.
[**privateGetPositionGet**](PrivateApi.md#privateGetPositionGet) | **GET** /private/get_position | Retrieve user position.
[**privateGetPositionsGet**](PrivateApi.md#privateGetPositionsGet) | **GET** /private/get_positions | Retrieve user positions.
[**privateGetSettlementHistoryByCurrencyGet**](PrivateApi.md#privateGetSettlementHistoryByCurrencyGet) | **GET** /private/get_settlement_history_by_currency | Retrieves settlement, delivery and bankruptcy events that have affected your account.
[**privateGetSettlementHistoryByInstrumentGet**](PrivateApi.md#privateGetSettlementHistoryByInstrumentGet) | **GET** /private/get_settlement_history_by_instrument | Retrieves public settlement, delivery and bankruptcy events filtered by instrument name
[**privateGetSubaccountsGet**](PrivateApi.md#privateGetSubaccountsGet) | **GET** /private/get_subaccounts | Get information about subaccounts
[**privateGetTransfersGet**](PrivateApi.md#privateGetTransfersGet) | **GET** /private/get_transfers | Adds new entry to address book of given type
[**privateGetUserTradesByCurrencyAndTimeGet**](PrivateApi.md#privateGetUserTradesByCurrencyAndTimeGet) | **GET** /private/get_user_trades_by_currency_and_time | Retrieve the latest user trades that have occurred for instruments in a specific currency symbol and within given time range.
[**privateGetUserTradesByCurrencyGet**](PrivateApi.md#privateGetUserTradesByCurrencyGet) | **GET** /private/get_user_trades_by_currency | Retrieve the latest user trades that have occurred for instruments in a specific currency symbol.
[**privateGetUserTradesByInstrumentAndTimeGet**](PrivateApi.md#privateGetUserTradesByInstrumentAndTimeGet) | **GET** /private/get_user_trades_by_instrument_and_time | Retrieve the latest user trades that have occurred for a specific instrument and within given time range.
[**privateGetUserTradesByInstrumentGet**](PrivateApi.md#privateGetUserTradesByInstrumentGet) | **GET** /private/get_user_trades_by_instrument | Retrieve the latest user trades that have occurred for a specific instrument.
[**privateGetUserTradesByOrderGet**](PrivateApi.md#privateGetUserTradesByOrderGet) | **GET** /private/get_user_trades_by_order | Retrieve the list of user trades that was created for given order
[**privateGetWithdrawalsGet**](PrivateApi.md#privateGetWithdrawalsGet) | **GET** /private/get_withdrawals | Retrieve the latest users withdrawals
[**privateRemoveFromAddressBookGet**](PrivateApi.md#privateRemoveFromAddressBookGet) | **GET** /private/remove_from_address_book | Adds new entry to address book of given type
[**privateSellGet**](PrivateApi.md#privateSellGet) | **GET** /private/sell | Places a sell order for an instrument.
[**privateSetAnnouncementAsReadGet**](PrivateApi.md#privateSetAnnouncementAsReadGet) | **GET** /private/set_announcement_as_read | Marks an announcement as read, so it will not be shown in &#x60;get_new_announcements&#x60;.
[**privateSetEmailForSubaccountGet**](PrivateApi.md#privateSetEmailForSubaccountGet) | **GET** /private/set_email_for_subaccount | Assign an email address to a subaccount. User will receive an email with confirmation link.
[**privateSetEmailLanguageGet**](PrivateApi.md#privateSetEmailLanguageGet) | **GET** /private/set_email_language | Changes the language to be used for emails.
[**privateSetPasswordForSubaccountGet**](PrivateApi.md#privateSetPasswordForSubaccountGet) | **GET** /private/set_password_for_subaccount | Set the password for the subaccount
[**privateSubmitTransferToSubaccountGet**](PrivateApi.md#privateSubmitTransferToSubaccountGet) | **GET** /private/submit_transfer_to_subaccount | Transfer funds to a subaccount.
[**privateSubmitTransferToUserGet**](PrivateApi.md#privateSubmitTransferToUserGet) | **GET** /private/submit_transfer_to_user | Transfer funds to a another user.
[**privateToggleDepositAddressCreationGet**](PrivateApi.md#privateToggleDepositAddressCreationGet) | **GET** /private/toggle_deposit_address_creation | Enable or disable deposit address creation
[**privateToggleNotificationsFromSubaccountGet**](PrivateApi.md#privateToggleNotificationsFromSubaccountGet) | **GET** /private/toggle_notifications_from_subaccount | Enable or disable sending of notifications for the subaccount.
[**privateToggleSubaccountLoginGet**](PrivateApi.md#privateToggleSubaccountLoginGet) | **GET** /private/toggle_subaccount_login | Enable or disable login for a subaccount. If login is disabled and a session for the subaccount exists, this session will be terminated.
[**privateWithdrawGet**](PrivateApi.md#privateWithdrawGet) | **GET** /private/withdraw | Creates a new withdrawal request



## privateAddToAddressBookGet

> object privateAddToAddressBookGet($currency, $type, $address, $name, $tfa)

Adds new entry to address book of given type

### Example

```php
<?php
require_once(__DIR__ . '/vendor/autoload.php');


// Configure Bearer (Auth. Token) authorization: bearerAuth
$config = OpenAPI\Client\Configuration::getDefaultConfiguration()->setAccessToken('YOUR_ACCESS_TOKEN');


$apiInstance = new OpenAPI\Client\Api\PrivateApi(
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
    echo 'Exception when calling PrivateApi->privateAddToAddressBookGet: ', $e->getMessage(), PHP_EOL;
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


## privateBuyGet

> object privateBuyGet($instrument_name, $amount, $type, $label, $price, $time_in_force, $max_show, $post_only, $reduce_only, $stop_price, $trigger, $advanced)

Places a buy order for an instrument.

### Example

```php
<?php
require_once(__DIR__ . '/vendor/autoload.php');


// Configure Bearer (Auth. Token) authorization: bearerAuth
$config = OpenAPI\Client\Configuration::getDefaultConfiguration()->setAccessToken('YOUR_ACCESS_TOKEN');


$apiInstance = new OpenAPI\Client\Api\PrivateApi(
    // If you want use custom http client, pass your client which implements `GuzzleHttp\ClientInterface`.
    // This is optional, `GuzzleHttp\Client` will be used as default.
    new GuzzleHttp\Client(),
    $config
);
$instrument_name = BTC-PERPETUAL; // string | Instrument name
$amount = 3.4; // float | It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH
$type = 'type_example'; // string | The order type, default: `\"limit\"`
$label = 'label_example'; // string | user defined label for the order (maximum 32 characters)
$price = 3.4; // float | <p>The order price in base currency (Only for limit and stop_limit orders)</p> <p>When adding order with advanced=usd, the field price should be the option price value in USD.</p> <p>When adding order with advanced=implv, the field price should be a value of implied volatility in percentages. For example,  price=100, means implied volatility of 100%</p>
$time_in_force = 'good_til_cancelled'; // string | <p>Specifies how long the order remains in effect. Default `\"good_til_cancelled\"`</p> <ul> <li>`\"good_til_cancelled\"` - unfilled order remains in order book until cancelled</li> <li>`\"fill_or_kill\"` - execute a transaction immediately and completely or not at all</li> <li>`\"immediate_or_cancel\"` - execute a transaction immediately, and any portion of the order that cannot be immediately filled is cancelled</li> </ul>
$max_show = 1; // float | Maximum amount within an order to be shown to other customers, `0` for invisible order
$post_only = true; // bool | <p>If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.</p> <p>Only valid in combination with time_in_force=`\"good_til_cancelled\"`</p>
$reduce_only = false; // bool | If `true`, the order is considered reduce-only which is intended to only reduce a current position
$stop_price = 3.4; // float | Stop price, required for stop limit orders (Only for stop orders)
$trigger = 'trigger_example'; // string | Defines trigger type, required for `\"stop_limit\"` order type
$advanced = 'advanced_example'; // string | Advanced option order type. (Only for options)

try {
    $result = $apiInstance->privateBuyGet($instrument_name, $amount, $type, $label, $price, $time_in_force, $max_show, $post_only, $reduce_only, $stop_price, $trigger, $advanced);
    print_r($result);
} catch (Exception $e) {
    echo 'Exception when calling PrivateApi->privateBuyGet: ', $e->getMessage(), PHP_EOL;
}
?>
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrument_name** | **string**| Instrument name |
 **amount** | **float**| It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH |
 **type** | **string**| The order type, default: &#x60;\&quot;limit\&quot;&#x60; | [optional]
 **label** | **string**| user defined label for the order (maximum 32 characters) | [optional]
 **price** | **float**| &lt;p&gt;The order price in base currency (Only for limit and stop_limit orders)&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;usd, the field price should be the option price value in USD.&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;implv, the field price should be a value of implied volatility in percentages. For example,  price&#x3D;100, means implied volatility of 100%&lt;/p&gt; | [optional]
 **time_in_force** | **string**| &lt;p&gt;Specifies how long the order remains in effect. Default &#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; &lt;ul&gt; &lt;li&gt;&#x60;\&quot;good_til_cancelled\&quot;&#x60; - unfilled order remains in order book until cancelled&lt;/li&gt; &lt;li&gt;&#x60;\&quot;fill_or_kill\&quot;&#x60; - execute a transaction immediately and completely or not at all&lt;/li&gt; &lt;li&gt;&#x60;\&quot;immediate_or_cancel\&quot;&#x60; - execute a transaction immediately, and any portion of the order that cannot be immediately filled is cancelled&lt;/li&gt; &lt;/ul&gt; | [optional] [default to &#39;good_til_cancelled&#39;]
 **max_show** | **float**| Maximum amount within an order to be shown to other customers, &#x60;0&#x60; for invisible order | [optional] [default to 1]
 **post_only** | **bool**| &lt;p&gt;If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.&lt;/p&gt; &lt;p&gt;Only valid in combination with time_in_force&#x3D;&#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; | [optional] [default to true]
 **reduce_only** | **bool**| If &#x60;true&#x60;, the order is considered reduce-only which is intended to only reduce a current position | [optional] [default to false]
 **stop_price** | **float**| Stop price, required for stop limit orders (Only for stop orders) | [optional]
 **trigger** | **string**| Defines trigger type, required for &#x60;\&quot;stop_limit\&quot;&#x60; order type | [optional]
 **advanced** | **string**| Advanced option order type. (Only for options) | [optional]

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


## privateCancelAllByCurrencyGet

> object privateCancelAllByCurrencyGet($currency, $kind, $type)

Cancels all orders by currency, optionally filtered by instrument kind and/or order type.

### Example

```php
<?php
require_once(__DIR__ . '/vendor/autoload.php');


// Configure Bearer (Auth. Token) authorization: bearerAuth
$config = OpenAPI\Client\Configuration::getDefaultConfiguration()->setAccessToken('YOUR_ACCESS_TOKEN');


$apiInstance = new OpenAPI\Client\Api\PrivateApi(
    // If you want use custom http client, pass your client which implements `GuzzleHttp\ClientInterface`.
    // This is optional, `GuzzleHttp\Client` will be used as default.
    new GuzzleHttp\Client(),
    $config
);
$currency = 'currency_example'; // string | The currency symbol
$kind = 'kind_example'; // string | Instrument kind, if not provided instruments of all kinds are considered
$type = 'type_example'; // string | Order type - limit, stop or all, default - `all`

try {
    $result = $apiInstance->privateCancelAllByCurrencyGet($currency, $kind, $type);
    print_r($result);
} catch (Exception $e) {
    echo 'Exception when calling PrivateApi->privateCancelAllByCurrencyGet: ', $e->getMessage(), PHP_EOL;
}
?>
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **string**| The currency symbol |
 **kind** | **string**| Instrument kind, if not provided instruments of all kinds are considered | [optional]
 **type** | **string**| Order type - limit, stop or all, default - &#x60;all&#x60; | [optional]

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


## privateCancelAllByInstrumentGet

> object privateCancelAllByInstrumentGet($instrument_name, $type)

Cancels all orders by instrument, optionally filtered by order type.

### Example

```php
<?php
require_once(__DIR__ . '/vendor/autoload.php');


// Configure Bearer (Auth. Token) authorization: bearerAuth
$config = OpenAPI\Client\Configuration::getDefaultConfiguration()->setAccessToken('YOUR_ACCESS_TOKEN');


$apiInstance = new OpenAPI\Client\Api\PrivateApi(
    // If you want use custom http client, pass your client which implements `GuzzleHttp\ClientInterface`.
    // This is optional, `GuzzleHttp\Client` will be used as default.
    new GuzzleHttp\Client(),
    $config
);
$instrument_name = BTC-PERPETUAL; // string | Instrument name
$type = 'type_example'; // string | Order type - limit, stop or all, default - `all`

try {
    $result = $apiInstance->privateCancelAllByInstrumentGet($instrument_name, $type);
    print_r($result);
} catch (Exception $e) {
    echo 'Exception when calling PrivateApi->privateCancelAllByInstrumentGet: ', $e->getMessage(), PHP_EOL;
}
?>
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrument_name** | **string**| Instrument name |
 **type** | **string**| Order type - limit, stop or all, default - &#x60;all&#x60; | [optional]

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


## privateCancelAllGet

> object privateCancelAllGet()

This method cancels all users orders and stop orders within all currencies and instrument kinds.

### Example

```php
<?php
require_once(__DIR__ . '/vendor/autoload.php');


// Configure Bearer (Auth. Token) authorization: bearerAuth
$config = OpenAPI\Client\Configuration::getDefaultConfiguration()->setAccessToken('YOUR_ACCESS_TOKEN');


$apiInstance = new OpenAPI\Client\Api\PrivateApi(
    // If you want use custom http client, pass your client which implements `GuzzleHttp\ClientInterface`.
    // This is optional, `GuzzleHttp\Client` will be used as default.
    new GuzzleHttp\Client(),
    $config
);

try {
    $result = $apiInstance->privateCancelAllGet();
    print_r($result);
} catch (Exception $e) {
    echo 'Exception when calling PrivateApi->privateCancelAllGet: ', $e->getMessage(), PHP_EOL;
}
?>
```

### Parameters

This endpoint does not need any parameter.

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


## privateCancelGet

> object privateCancelGet($order_id)

Cancel an order, specified by order id

### Example

```php
<?php
require_once(__DIR__ . '/vendor/autoload.php');


// Configure Bearer (Auth. Token) authorization: bearerAuth
$config = OpenAPI\Client\Configuration::getDefaultConfiguration()->setAccessToken('YOUR_ACCESS_TOKEN');


$apiInstance = new OpenAPI\Client\Api\PrivateApi(
    // If you want use custom http client, pass your client which implements `GuzzleHttp\ClientInterface`.
    // This is optional, `GuzzleHttp\Client` will be used as default.
    new GuzzleHttp\Client(),
    $config
);
$order_id = ETH-100234; // string | The order id

try {
    $result = $apiInstance->privateCancelGet($order_id);
    print_r($result);
} catch (Exception $e) {
    echo 'Exception when calling PrivateApi->privateCancelGet: ', $e->getMessage(), PHP_EOL;
}
?>
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **order_id** | **string**| The order id |

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


$apiInstance = new OpenAPI\Client\Api\PrivateApi(
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
    echo 'Exception when calling PrivateApi->privateCancelTransferByIdGet: ', $e->getMessage(), PHP_EOL;
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


$apiInstance = new OpenAPI\Client\Api\PrivateApi(
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
    echo 'Exception when calling PrivateApi->privateCancelWithdrawalGet: ', $e->getMessage(), PHP_EOL;
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


## privateChangeSubaccountNameGet

> object privateChangeSubaccountNameGet($sid, $name)

Change the user name for a subaccount

### Example

```php
<?php
require_once(__DIR__ . '/vendor/autoload.php');


// Configure Bearer (Auth. Token) authorization: bearerAuth
$config = OpenAPI\Client\Configuration::getDefaultConfiguration()->setAccessToken('YOUR_ACCESS_TOKEN');


$apiInstance = new OpenAPI\Client\Api\PrivateApi(
    // If you want use custom http client, pass your client which implements `GuzzleHttp\ClientInterface`.
    // This is optional, `GuzzleHttp\Client` will be used as default.
    new GuzzleHttp\Client(),
    $config
);
$sid = 56; // int | The user id for the subaccount
$name = newUserName; // string | The new user name

try {
    $result = $apiInstance->privateChangeSubaccountNameGet($sid, $name);
    print_r($result);
} catch (Exception $e) {
    echo 'Exception when calling PrivateApi->privateChangeSubaccountNameGet: ', $e->getMessage(), PHP_EOL;
}
?>
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **sid** | **int**| The user id for the subaccount |
 **name** | **string**| The new user name |

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


## privateClosePositionGet

> object privateClosePositionGet($instrument_name, $type, $price)

Makes closing position reduce only order .

### Example

```php
<?php
require_once(__DIR__ . '/vendor/autoload.php');


// Configure Bearer (Auth. Token) authorization: bearerAuth
$config = OpenAPI\Client\Configuration::getDefaultConfiguration()->setAccessToken('YOUR_ACCESS_TOKEN');


$apiInstance = new OpenAPI\Client\Api\PrivateApi(
    // If you want use custom http client, pass your client which implements `GuzzleHttp\ClientInterface`.
    // This is optional, `GuzzleHttp\Client` will be used as default.
    new GuzzleHttp\Client(),
    $config
);
$instrument_name = BTC-PERPETUAL; // string | Instrument name
$type = 'type_example'; // string | The order type
$price = 3.4; // float | Optional price for limit order.

try {
    $result = $apiInstance->privateClosePositionGet($instrument_name, $type, $price);
    print_r($result);
} catch (Exception $e) {
    echo 'Exception when calling PrivateApi->privateClosePositionGet: ', $e->getMessage(), PHP_EOL;
}
?>
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrument_name** | **string**| Instrument name |
 **type** | **string**| The order type |
 **price** | **float**| Optional price for limit order. | [optional]

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


$apiInstance = new OpenAPI\Client\Api\PrivateApi(
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
    echo 'Exception when calling PrivateApi->privateCreateDepositAddressGet: ', $e->getMessage(), PHP_EOL;
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


## privateCreateSubaccountGet

> object privateCreateSubaccountGet()

Create a new subaccount

### Example

```php
<?php
require_once(__DIR__ . '/vendor/autoload.php');


// Configure Bearer (Auth. Token) authorization: bearerAuth
$config = OpenAPI\Client\Configuration::getDefaultConfiguration()->setAccessToken('YOUR_ACCESS_TOKEN');


$apiInstance = new OpenAPI\Client\Api\PrivateApi(
    // If you want use custom http client, pass your client which implements `GuzzleHttp\ClientInterface`.
    // This is optional, `GuzzleHttp\Client` will be used as default.
    new GuzzleHttp\Client(),
    $config
);

try {
    $result = $apiInstance->privateCreateSubaccountGet();
    print_r($result);
} catch (Exception $e) {
    echo 'Exception when calling PrivateApi->privateCreateSubaccountGet: ', $e->getMessage(), PHP_EOL;
}
?>
```

### Parameters

This endpoint does not need any parameter.

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


## privateDisableTfaForSubaccountGet

> object privateDisableTfaForSubaccountGet($sid)

Disable two factor authentication for a subaccount.

### Example

```php
<?php
require_once(__DIR__ . '/vendor/autoload.php');


// Configure Bearer (Auth. Token) authorization: bearerAuth
$config = OpenAPI\Client\Configuration::getDefaultConfiguration()->setAccessToken('YOUR_ACCESS_TOKEN');


$apiInstance = new OpenAPI\Client\Api\PrivateApi(
    // If you want use custom http client, pass your client which implements `GuzzleHttp\ClientInterface`.
    // This is optional, `GuzzleHttp\Client` will be used as default.
    new GuzzleHttp\Client(),
    $config
);
$sid = 56; // int | The user id for the subaccount

try {
    $result = $apiInstance->privateDisableTfaForSubaccountGet($sid);
    print_r($result);
} catch (Exception $e) {
    echo 'Exception when calling PrivateApi->privateDisableTfaForSubaccountGet: ', $e->getMessage(), PHP_EOL;
}
?>
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **sid** | **int**| The user id for the subaccount |

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


## privateDisableTfaWithRecoveryCodeGet

> object privateDisableTfaWithRecoveryCodeGet($password, $code)

Disables TFA with one time recovery code

### Example

```php
<?php
require_once(__DIR__ . '/vendor/autoload.php');


// Configure Bearer (Auth. Token) authorization: bearerAuth
$config = OpenAPI\Client\Configuration::getDefaultConfiguration()->setAccessToken('YOUR_ACCESS_TOKEN');


$apiInstance = new OpenAPI\Client\Api\PrivateApi(
    // If you want use custom http client, pass your client which implements `GuzzleHttp\ClientInterface`.
    // This is optional, `GuzzleHttp\Client` will be used as default.
    new GuzzleHttp\Client(),
    $config
);
$password = 'password_example'; // string | The password for the subaccount
$code = 'code_example'; // string | One time recovery code

try {
    $result = $apiInstance->privateDisableTfaWithRecoveryCodeGet($password, $code);
    print_r($result);
} catch (Exception $e) {
    echo 'Exception when calling PrivateApi->privateDisableTfaWithRecoveryCodeGet: ', $e->getMessage(), PHP_EOL;
}
?>
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **password** | **string**| The password for the subaccount |
 **code** | **string**| One time recovery code |

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


## privateEditGet

> object privateEditGet($order_id, $amount, $price, $post_only, $advanced, $stop_price)

Change price, amount and/or other properties of an order.

### Example

```php
<?php
require_once(__DIR__ . '/vendor/autoload.php');


// Configure Bearer (Auth. Token) authorization: bearerAuth
$config = OpenAPI\Client\Configuration::getDefaultConfiguration()->setAccessToken('YOUR_ACCESS_TOKEN');


$apiInstance = new OpenAPI\Client\Api\PrivateApi(
    // If you want use custom http client, pass your client which implements `GuzzleHttp\ClientInterface`.
    // This is optional, `GuzzleHttp\Client` will be used as default.
    new GuzzleHttp\Client(),
    $config
);
$order_id = ETH-100234; // string | The order id
$amount = 3.4; // float | It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH
$price = 3.4; // float | <p>The order price in base currency.</p> <p>When editing an option order with advanced=usd, the field price should be the option price value in USD.</p> <p>When editing an option order with advanced=implv, the field price should be a value of implied volatility in percentages. For example,  price=100, means implied volatility of 100%</p>
$post_only = true; // bool | <p>If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.</p> <p>Only valid in combination with time_in_force=`\"good_til_cancelled\"`</p>
$advanced = 'advanced_example'; // string | Advanced option order type. If you have posted an advanced option order, it is necessary to re-supply this parameter when editing it (Only for options)
$stop_price = 3.4; // float | Stop price, required for stop limit orders (Only for stop orders)

try {
    $result = $apiInstance->privateEditGet($order_id, $amount, $price, $post_only, $advanced, $stop_price);
    print_r($result);
} catch (Exception $e) {
    echo 'Exception when calling PrivateApi->privateEditGet: ', $e->getMessage(), PHP_EOL;
}
?>
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **order_id** | **string**| The order id |
 **amount** | **float**| It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH |
 **price** | **float**| &lt;p&gt;The order price in base currency.&lt;/p&gt; &lt;p&gt;When editing an option order with advanced&#x3D;usd, the field price should be the option price value in USD.&lt;/p&gt; &lt;p&gt;When editing an option order with advanced&#x3D;implv, the field price should be a value of implied volatility in percentages. For example,  price&#x3D;100, means implied volatility of 100%&lt;/p&gt; |
 **post_only** | **bool**| &lt;p&gt;If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.&lt;/p&gt; &lt;p&gt;Only valid in combination with time_in_force&#x3D;&#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; | [optional] [default to true]
 **advanced** | **string**| Advanced option order type. If you have posted an advanced option order, it is necessary to re-supply this parameter when editing it (Only for options) | [optional]
 **stop_price** | **float**| Stop price, required for stop limit orders (Only for stop orders) | [optional]

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


## privateGetAccountSummaryGet

> object privateGetAccountSummaryGet($currency, $extended)

Retrieves user account summary.

### Example

```php
<?php
require_once(__DIR__ . '/vendor/autoload.php');


// Configure Bearer (Auth. Token) authorization: bearerAuth
$config = OpenAPI\Client\Configuration::getDefaultConfiguration()->setAccessToken('YOUR_ACCESS_TOKEN');


$apiInstance = new OpenAPI\Client\Api\PrivateApi(
    // If you want use custom http client, pass your client which implements `GuzzleHttp\ClientInterface`.
    // This is optional, `GuzzleHttp\Client` will be used as default.
    new GuzzleHttp\Client(),
    $config
);
$currency = 'currency_example'; // string | The currency symbol
$extended = false; // bool | Include additional fields

try {
    $result = $apiInstance->privateGetAccountSummaryGet($currency, $extended);
    print_r($result);
} catch (Exception $e) {
    echo 'Exception when calling PrivateApi->privateGetAccountSummaryGet: ', $e->getMessage(), PHP_EOL;
}
?>
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **string**| The currency symbol |
 **extended** | **bool**| Include additional fields | [optional]

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


$apiInstance = new OpenAPI\Client\Api\PrivateApi(
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
    echo 'Exception when calling PrivateApi->privateGetAddressBookGet: ', $e->getMessage(), PHP_EOL;
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


$apiInstance = new OpenAPI\Client\Api\PrivateApi(
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
    echo 'Exception when calling PrivateApi->privateGetCurrentDepositAddressGet: ', $e->getMessage(), PHP_EOL;
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


$apiInstance = new OpenAPI\Client\Api\PrivateApi(
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
    echo 'Exception when calling PrivateApi->privateGetDepositsGet: ', $e->getMessage(), PHP_EOL;
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


## privateGetEmailLanguageGet

> object privateGetEmailLanguageGet()

Retrieves the language to be used for emails.

### Example

```php
<?php
require_once(__DIR__ . '/vendor/autoload.php');


// Configure Bearer (Auth. Token) authorization: bearerAuth
$config = OpenAPI\Client\Configuration::getDefaultConfiguration()->setAccessToken('YOUR_ACCESS_TOKEN');


$apiInstance = new OpenAPI\Client\Api\PrivateApi(
    // If you want use custom http client, pass your client which implements `GuzzleHttp\ClientInterface`.
    // This is optional, `GuzzleHttp\Client` will be used as default.
    new GuzzleHttp\Client(),
    $config
);

try {
    $result = $apiInstance->privateGetEmailLanguageGet();
    print_r($result);
} catch (Exception $e) {
    echo 'Exception when calling PrivateApi->privateGetEmailLanguageGet: ', $e->getMessage(), PHP_EOL;
}
?>
```

### Parameters

This endpoint does not need any parameter.

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


## privateGetMarginsGet

> object privateGetMarginsGet($instrument_name, $amount, $price)

Get margins for given instrument, amount and price.

### Example

```php
<?php
require_once(__DIR__ . '/vendor/autoload.php');


// Configure Bearer (Auth. Token) authorization: bearerAuth
$config = OpenAPI\Client\Configuration::getDefaultConfiguration()->setAccessToken('YOUR_ACCESS_TOKEN');


$apiInstance = new OpenAPI\Client\Api\PrivateApi(
    // If you want use custom http client, pass your client which implements `GuzzleHttp\ClientInterface`.
    // This is optional, `GuzzleHttp\Client` will be used as default.
    new GuzzleHttp\Client(),
    $config
);
$instrument_name = BTC-PERPETUAL; // string | Instrument name
$amount = 1; // float | Amount, integer for future, float for option. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH.
$price = 3.4; // float | Price

try {
    $result = $apiInstance->privateGetMarginsGet($instrument_name, $amount, $price);
    print_r($result);
} catch (Exception $e) {
    echo 'Exception when calling PrivateApi->privateGetMarginsGet: ', $e->getMessage(), PHP_EOL;
}
?>
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrument_name** | **string**| Instrument name |
 **amount** | **float**| Amount, integer for future, float for option. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH. |
 **price** | **float**| Price |

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


## privateGetNewAnnouncementsGet

> object privateGetNewAnnouncementsGet()

Retrieves announcements that have not been marked read by the user.

### Example

```php
<?php
require_once(__DIR__ . '/vendor/autoload.php');


// Configure Bearer (Auth. Token) authorization: bearerAuth
$config = OpenAPI\Client\Configuration::getDefaultConfiguration()->setAccessToken('YOUR_ACCESS_TOKEN');


$apiInstance = new OpenAPI\Client\Api\PrivateApi(
    // If you want use custom http client, pass your client which implements `GuzzleHttp\ClientInterface`.
    // This is optional, `GuzzleHttp\Client` will be used as default.
    new GuzzleHttp\Client(),
    $config
);

try {
    $result = $apiInstance->privateGetNewAnnouncementsGet();
    print_r($result);
} catch (Exception $e) {
    echo 'Exception when calling PrivateApi->privateGetNewAnnouncementsGet: ', $e->getMessage(), PHP_EOL;
}
?>
```

### Parameters

This endpoint does not need any parameter.

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


## privateGetOpenOrdersByCurrencyGet

> object privateGetOpenOrdersByCurrencyGet($currency, $kind, $type)

Retrieves list of user's open orders.

### Example

```php
<?php
require_once(__DIR__ . '/vendor/autoload.php');


// Configure Bearer (Auth. Token) authorization: bearerAuth
$config = OpenAPI\Client\Configuration::getDefaultConfiguration()->setAccessToken('YOUR_ACCESS_TOKEN');


$apiInstance = new OpenAPI\Client\Api\PrivateApi(
    // If you want use custom http client, pass your client which implements `GuzzleHttp\ClientInterface`.
    // This is optional, `GuzzleHttp\Client` will be used as default.
    new GuzzleHttp\Client(),
    $config
);
$currency = 'currency_example'; // string | The currency symbol
$kind = 'kind_example'; // string | Instrument kind, if not provided instruments of all kinds are considered
$type = 'type_example'; // string | Order type, default - `all`

try {
    $result = $apiInstance->privateGetOpenOrdersByCurrencyGet($currency, $kind, $type);
    print_r($result);
} catch (Exception $e) {
    echo 'Exception when calling PrivateApi->privateGetOpenOrdersByCurrencyGet: ', $e->getMessage(), PHP_EOL;
}
?>
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **string**| The currency symbol |
 **kind** | **string**| Instrument kind, if not provided instruments of all kinds are considered | [optional]
 **type** | **string**| Order type, default - &#x60;all&#x60; | [optional]

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


## privateGetOpenOrdersByInstrumentGet

> object privateGetOpenOrdersByInstrumentGet($instrument_name, $type)

Retrieves list of user's open orders within given Instrument.

### Example

```php
<?php
require_once(__DIR__ . '/vendor/autoload.php');


// Configure Bearer (Auth. Token) authorization: bearerAuth
$config = OpenAPI\Client\Configuration::getDefaultConfiguration()->setAccessToken('YOUR_ACCESS_TOKEN');


$apiInstance = new OpenAPI\Client\Api\PrivateApi(
    // If you want use custom http client, pass your client which implements `GuzzleHttp\ClientInterface`.
    // This is optional, `GuzzleHttp\Client` will be used as default.
    new GuzzleHttp\Client(),
    $config
);
$instrument_name = BTC-PERPETUAL; // string | Instrument name
$type = 'type_example'; // string | Order type, default - `all`

try {
    $result = $apiInstance->privateGetOpenOrdersByInstrumentGet($instrument_name, $type);
    print_r($result);
} catch (Exception $e) {
    echo 'Exception when calling PrivateApi->privateGetOpenOrdersByInstrumentGet: ', $e->getMessage(), PHP_EOL;
}
?>
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrument_name** | **string**| Instrument name |
 **type** | **string**| Order type, default - &#x60;all&#x60; | [optional]

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


## privateGetOrderHistoryByCurrencyGet

> object privateGetOrderHistoryByCurrencyGet($currency, $kind, $count, $offset, $include_old, $include_unfilled)

Retrieves history of orders that have been partially or fully filled.

### Example

```php
<?php
require_once(__DIR__ . '/vendor/autoload.php');


// Configure Bearer (Auth. Token) authorization: bearerAuth
$config = OpenAPI\Client\Configuration::getDefaultConfiguration()->setAccessToken('YOUR_ACCESS_TOKEN');


$apiInstance = new OpenAPI\Client\Api\PrivateApi(
    // If you want use custom http client, pass your client which implements `GuzzleHttp\ClientInterface`.
    // This is optional, `GuzzleHttp\Client` will be used as default.
    new GuzzleHttp\Client(),
    $config
);
$currency = 'currency_example'; // string | The currency symbol
$kind = 'kind_example'; // string | Instrument kind, if not provided instruments of all kinds are considered
$count = 56; // int | Number of requested items, default - `20`
$offset = 10; // int | The offset for pagination, default - `0`
$include_old = false; // bool | Include in result orders older than 2 days, default - `false`
$include_unfilled = false; // bool | Include in result fully unfilled closed orders, default - `false`

try {
    $result = $apiInstance->privateGetOrderHistoryByCurrencyGet($currency, $kind, $count, $offset, $include_old, $include_unfilled);
    print_r($result);
} catch (Exception $e) {
    echo 'Exception when calling PrivateApi->privateGetOrderHistoryByCurrencyGet: ', $e->getMessage(), PHP_EOL;
}
?>
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **string**| The currency symbol |
 **kind** | **string**| Instrument kind, if not provided instruments of all kinds are considered | [optional]
 **count** | **int**| Number of requested items, default - &#x60;20&#x60; | [optional]
 **offset** | **int**| The offset for pagination, default - &#x60;0&#x60; | [optional]
 **include_old** | **bool**| Include in result orders older than 2 days, default - &#x60;false&#x60; | [optional]
 **include_unfilled** | **bool**| Include in result fully unfilled closed orders, default - &#x60;false&#x60; | [optional]

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


## privateGetOrderHistoryByInstrumentGet

> object privateGetOrderHistoryByInstrumentGet($instrument_name, $count, $offset, $include_old, $include_unfilled)

Retrieves history of orders that have been partially or fully filled.

### Example

```php
<?php
require_once(__DIR__ . '/vendor/autoload.php');


// Configure Bearer (Auth. Token) authorization: bearerAuth
$config = OpenAPI\Client\Configuration::getDefaultConfiguration()->setAccessToken('YOUR_ACCESS_TOKEN');


$apiInstance = new OpenAPI\Client\Api\PrivateApi(
    // If you want use custom http client, pass your client which implements `GuzzleHttp\ClientInterface`.
    // This is optional, `GuzzleHttp\Client` will be used as default.
    new GuzzleHttp\Client(),
    $config
);
$instrument_name = BTC-PERPETUAL; // string | Instrument name
$count = 56; // int | Number of requested items, default - `20`
$offset = 10; // int | The offset for pagination, default - `0`
$include_old = false; // bool | Include in result orders older than 2 days, default - `false`
$include_unfilled = false; // bool | Include in result fully unfilled closed orders, default - `false`

try {
    $result = $apiInstance->privateGetOrderHistoryByInstrumentGet($instrument_name, $count, $offset, $include_old, $include_unfilled);
    print_r($result);
} catch (Exception $e) {
    echo 'Exception when calling PrivateApi->privateGetOrderHistoryByInstrumentGet: ', $e->getMessage(), PHP_EOL;
}
?>
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrument_name** | **string**| Instrument name |
 **count** | **int**| Number of requested items, default - &#x60;20&#x60; | [optional]
 **offset** | **int**| The offset for pagination, default - &#x60;0&#x60; | [optional]
 **include_old** | **bool**| Include in result orders older than 2 days, default - &#x60;false&#x60; | [optional]
 **include_unfilled** | **bool**| Include in result fully unfilled closed orders, default - &#x60;false&#x60; | [optional]

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


## privateGetOrderMarginByIdsGet

> object privateGetOrderMarginByIdsGet($ids)

Retrieves initial margins of given orders

### Example

```php
<?php
require_once(__DIR__ . '/vendor/autoload.php');


// Configure Bearer (Auth. Token) authorization: bearerAuth
$config = OpenAPI\Client\Configuration::getDefaultConfiguration()->setAccessToken('YOUR_ACCESS_TOKEN');


$apiInstance = new OpenAPI\Client\Api\PrivateApi(
    // If you want use custom http client, pass your client which implements `GuzzleHttp\ClientInterface`.
    // This is optional, `GuzzleHttp\Client` will be used as default.
    new GuzzleHttp\Client(),
    $config
);
$ids = array('ids_example'); // string[] | Ids of orders

try {
    $result = $apiInstance->privateGetOrderMarginByIdsGet($ids);
    print_r($result);
} catch (Exception $e) {
    echo 'Exception when calling PrivateApi->privateGetOrderMarginByIdsGet: ', $e->getMessage(), PHP_EOL;
}
?>
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **ids** | [**string[]**](../Model/string.md)| Ids of orders |

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


## privateGetOrderStateGet

> object privateGetOrderStateGet($order_id)

Retrieve the current state of an order.

### Example

```php
<?php
require_once(__DIR__ . '/vendor/autoload.php');


// Configure Bearer (Auth. Token) authorization: bearerAuth
$config = OpenAPI\Client\Configuration::getDefaultConfiguration()->setAccessToken('YOUR_ACCESS_TOKEN');


$apiInstance = new OpenAPI\Client\Api\PrivateApi(
    // If you want use custom http client, pass your client which implements `GuzzleHttp\ClientInterface`.
    // This is optional, `GuzzleHttp\Client` will be used as default.
    new GuzzleHttp\Client(),
    $config
);
$order_id = ETH-100234; // string | The order id

try {
    $result = $apiInstance->privateGetOrderStateGet($order_id);
    print_r($result);
} catch (Exception $e) {
    echo 'Exception when calling PrivateApi->privateGetOrderStateGet: ', $e->getMessage(), PHP_EOL;
}
?>
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **order_id** | **string**| The order id |

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


## privateGetPositionGet

> object privateGetPositionGet($instrument_name)

Retrieve user position.

### Example

```php
<?php
require_once(__DIR__ . '/vendor/autoload.php');


// Configure Bearer (Auth. Token) authorization: bearerAuth
$config = OpenAPI\Client\Configuration::getDefaultConfiguration()->setAccessToken('YOUR_ACCESS_TOKEN');


$apiInstance = new OpenAPI\Client\Api\PrivateApi(
    // If you want use custom http client, pass your client which implements `GuzzleHttp\ClientInterface`.
    // This is optional, `GuzzleHttp\Client` will be used as default.
    new GuzzleHttp\Client(),
    $config
);
$instrument_name = BTC-PERPETUAL; // string | Instrument name

try {
    $result = $apiInstance->privateGetPositionGet($instrument_name);
    print_r($result);
} catch (Exception $e) {
    echo 'Exception when calling PrivateApi->privateGetPositionGet: ', $e->getMessage(), PHP_EOL;
}
?>
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrument_name** | **string**| Instrument name |

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


## privateGetPositionsGet

> object privateGetPositionsGet($currency, $kind)

Retrieve user positions.

### Example

```php
<?php
require_once(__DIR__ . '/vendor/autoload.php');


// Configure Bearer (Auth. Token) authorization: bearerAuth
$config = OpenAPI\Client\Configuration::getDefaultConfiguration()->setAccessToken('YOUR_ACCESS_TOKEN');


$apiInstance = new OpenAPI\Client\Api\PrivateApi(
    // If you want use custom http client, pass your client which implements `GuzzleHttp\ClientInterface`.
    // This is optional, `GuzzleHttp\Client` will be used as default.
    new GuzzleHttp\Client(),
    $config
);
$currency = 'currency_example'; // string | 
$kind = 'kind_example'; // string | Kind filter on positions

try {
    $result = $apiInstance->privateGetPositionsGet($currency, $kind);
    print_r($result);
} catch (Exception $e) {
    echo 'Exception when calling PrivateApi->privateGetPositionsGet: ', $e->getMessage(), PHP_EOL;
}
?>
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **string**|  |
 **kind** | **string**| Kind filter on positions | [optional]

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


## privateGetSettlementHistoryByCurrencyGet

> object privateGetSettlementHistoryByCurrencyGet($currency, $type, $count)

Retrieves settlement, delivery and bankruptcy events that have affected your account.

### Example

```php
<?php
require_once(__DIR__ . '/vendor/autoload.php');


// Configure Bearer (Auth. Token) authorization: bearerAuth
$config = OpenAPI\Client\Configuration::getDefaultConfiguration()->setAccessToken('YOUR_ACCESS_TOKEN');


$apiInstance = new OpenAPI\Client\Api\PrivateApi(
    // If you want use custom http client, pass your client which implements `GuzzleHttp\ClientInterface`.
    // This is optional, `GuzzleHttp\Client` will be used as default.
    new GuzzleHttp\Client(),
    $config
);
$currency = 'currency_example'; // string | The currency symbol
$type = 'type_example'; // string | Settlement type
$count = 56; // int | Number of requested items, default - `20`

try {
    $result = $apiInstance->privateGetSettlementHistoryByCurrencyGet($currency, $type, $count);
    print_r($result);
} catch (Exception $e) {
    echo 'Exception when calling PrivateApi->privateGetSettlementHistoryByCurrencyGet: ', $e->getMessage(), PHP_EOL;
}
?>
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **string**| The currency symbol |
 **type** | **string**| Settlement type | [optional]
 **count** | **int**| Number of requested items, default - &#x60;20&#x60; | [optional]

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


## privateGetSettlementHistoryByInstrumentGet

> object privateGetSettlementHistoryByInstrumentGet($instrument_name, $type, $count)

Retrieves public settlement, delivery and bankruptcy events filtered by instrument name

### Example

```php
<?php
require_once(__DIR__ . '/vendor/autoload.php');


// Configure Bearer (Auth. Token) authorization: bearerAuth
$config = OpenAPI\Client\Configuration::getDefaultConfiguration()->setAccessToken('YOUR_ACCESS_TOKEN');


$apiInstance = new OpenAPI\Client\Api\PrivateApi(
    // If you want use custom http client, pass your client which implements `GuzzleHttp\ClientInterface`.
    // This is optional, `GuzzleHttp\Client` will be used as default.
    new GuzzleHttp\Client(),
    $config
);
$instrument_name = BTC-PERPETUAL; // string | Instrument name
$type = 'type_example'; // string | Settlement type
$count = 56; // int | Number of requested items, default - `20`

try {
    $result = $apiInstance->privateGetSettlementHistoryByInstrumentGet($instrument_name, $type, $count);
    print_r($result);
} catch (Exception $e) {
    echo 'Exception when calling PrivateApi->privateGetSettlementHistoryByInstrumentGet: ', $e->getMessage(), PHP_EOL;
}
?>
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrument_name** | **string**| Instrument name |
 **type** | **string**| Settlement type | [optional]
 **count** | **int**| Number of requested items, default - &#x60;20&#x60; | [optional]

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


## privateGetSubaccountsGet

> object privateGetSubaccountsGet($with_portfolio)

Get information about subaccounts

### Example

```php
<?php
require_once(__DIR__ . '/vendor/autoload.php');


// Configure Bearer (Auth. Token) authorization: bearerAuth
$config = OpenAPI\Client\Configuration::getDefaultConfiguration()->setAccessToken('YOUR_ACCESS_TOKEN');


$apiInstance = new OpenAPI\Client\Api\PrivateApi(
    // If you want use custom http client, pass your client which implements `GuzzleHttp\ClientInterface`.
    // This is optional, `GuzzleHttp\Client` will be used as default.
    new GuzzleHttp\Client(),
    $config
);
$with_portfolio = True; // bool | 

try {
    $result = $apiInstance->privateGetSubaccountsGet($with_portfolio);
    print_r($result);
} catch (Exception $e) {
    echo 'Exception when calling PrivateApi->privateGetSubaccountsGet: ', $e->getMessage(), PHP_EOL;
}
?>
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **with_portfolio** | **bool**|  | [optional]

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


$apiInstance = new OpenAPI\Client\Api\PrivateApi(
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
    echo 'Exception when calling PrivateApi->privateGetTransfersGet: ', $e->getMessage(), PHP_EOL;
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


## privateGetUserTradesByCurrencyAndTimeGet

> object privateGetUserTradesByCurrencyAndTimeGet($currency, $start_timestamp, $end_timestamp, $kind, $count, $include_old, $sorting)

Retrieve the latest user trades that have occurred for instruments in a specific currency symbol and within given time range.

### Example

```php
<?php
require_once(__DIR__ . '/vendor/autoload.php');


// Configure Bearer (Auth. Token) authorization: bearerAuth
$config = OpenAPI\Client\Configuration::getDefaultConfiguration()->setAccessToken('YOUR_ACCESS_TOKEN');


$apiInstance = new OpenAPI\Client\Api\PrivateApi(
    // If you want use custom http client, pass your client which implements `GuzzleHttp\ClientInterface`.
    // This is optional, `GuzzleHttp\Client` will be used as default.
    new GuzzleHttp\Client(),
    $config
);
$currency = 'currency_example'; // string | The currency symbol
$start_timestamp = 1536569522277; // int | The earliest timestamp to return result for
$end_timestamp = 1536569522277; // int | The most recent timestamp to return result for
$kind = 'kind_example'; // string | Instrument kind, if not provided instruments of all kinds are considered
$count = 56; // int | Number of requested items, default - `10`
$include_old = True; // bool | Include trades older than 7 days, default - `false`
$sorting = 'sorting_example'; // string | Direction of results sorting (`default` value means no sorting, results will be returned in order in which they left the database)

try {
    $result = $apiInstance->privateGetUserTradesByCurrencyAndTimeGet($currency, $start_timestamp, $end_timestamp, $kind, $count, $include_old, $sorting);
    print_r($result);
} catch (Exception $e) {
    echo 'Exception when calling PrivateApi->privateGetUserTradesByCurrencyAndTimeGet: ', $e->getMessage(), PHP_EOL;
}
?>
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **string**| The currency symbol |
 **start_timestamp** | **int**| The earliest timestamp to return result for |
 **end_timestamp** | **int**| The most recent timestamp to return result for |
 **kind** | **string**| Instrument kind, if not provided instruments of all kinds are considered | [optional]
 **count** | **int**| Number of requested items, default - &#x60;10&#x60; | [optional]
 **include_old** | **bool**| Include trades older than 7 days, default - &#x60;false&#x60; | [optional]
 **sorting** | **string**| Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) | [optional]

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


## privateGetUserTradesByCurrencyGet

> object privateGetUserTradesByCurrencyGet($currency, $kind, $start_id, $end_id, $count, $include_old, $sorting)

Retrieve the latest user trades that have occurred for instruments in a specific currency symbol.

### Example

```php
<?php
require_once(__DIR__ . '/vendor/autoload.php');


// Configure Bearer (Auth. Token) authorization: bearerAuth
$config = OpenAPI\Client\Configuration::getDefaultConfiguration()->setAccessToken('YOUR_ACCESS_TOKEN');


$apiInstance = new OpenAPI\Client\Api\PrivateApi(
    // If you want use custom http client, pass your client which implements `GuzzleHttp\ClientInterface`.
    // This is optional, `GuzzleHttp\Client` will be used as default.
    new GuzzleHttp\Client(),
    $config
);
$currency = 'currency_example'; // string | The currency symbol
$kind = 'kind_example'; // string | Instrument kind, if not provided instruments of all kinds are considered
$start_id = 'start_id_example'; // string | The ID number of the first trade to be returned
$end_id = 'end_id_example'; // string | The ID number of the last trade to be returned
$count = 56; // int | Number of requested items, default - `10`
$include_old = True; // bool | Include trades older than 7 days, default - `false`
$sorting = 'sorting_example'; // string | Direction of results sorting (`default` value means no sorting, results will be returned in order in which they left the database)

try {
    $result = $apiInstance->privateGetUserTradesByCurrencyGet($currency, $kind, $start_id, $end_id, $count, $include_old, $sorting);
    print_r($result);
} catch (Exception $e) {
    echo 'Exception when calling PrivateApi->privateGetUserTradesByCurrencyGet: ', $e->getMessage(), PHP_EOL;
}
?>
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **string**| The currency symbol |
 **kind** | **string**| Instrument kind, if not provided instruments of all kinds are considered | [optional]
 **start_id** | **string**| The ID number of the first trade to be returned | [optional]
 **end_id** | **string**| The ID number of the last trade to be returned | [optional]
 **count** | **int**| Number of requested items, default - &#x60;10&#x60; | [optional]
 **include_old** | **bool**| Include trades older than 7 days, default - &#x60;false&#x60; | [optional]
 **sorting** | **string**| Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) | [optional]

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


## privateGetUserTradesByInstrumentAndTimeGet

> object privateGetUserTradesByInstrumentAndTimeGet($instrument_name, $start_timestamp, $end_timestamp, $count, $include_old, $sorting)

Retrieve the latest user trades that have occurred for a specific instrument and within given time range.

### Example

```php
<?php
require_once(__DIR__ . '/vendor/autoload.php');


// Configure Bearer (Auth. Token) authorization: bearerAuth
$config = OpenAPI\Client\Configuration::getDefaultConfiguration()->setAccessToken('YOUR_ACCESS_TOKEN');


$apiInstance = new OpenAPI\Client\Api\PrivateApi(
    // If you want use custom http client, pass your client which implements `GuzzleHttp\ClientInterface`.
    // This is optional, `GuzzleHttp\Client` will be used as default.
    new GuzzleHttp\Client(),
    $config
);
$instrument_name = BTC-PERPETUAL; // string | Instrument name
$start_timestamp = 1536569522277; // int | The earliest timestamp to return result for
$end_timestamp = 1536569522277; // int | The most recent timestamp to return result for
$count = 56; // int | Number of requested items, default - `10`
$include_old = True; // bool | Include trades older than 7 days, default - `false`
$sorting = 'sorting_example'; // string | Direction of results sorting (`default` value means no sorting, results will be returned in order in which they left the database)

try {
    $result = $apiInstance->privateGetUserTradesByInstrumentAndTimeGet($instrument_name, $start_timestamp, $end_timestamp, $count, $include_old, $sorting);
    print_r($result);
} catch (Exception $e) {
    echo 'Exception when calling PrivateApi->privateGetUserTradesByInstrumentAndTimeGet: ', $e->getMessage(), PHP_EOL;
}
?>
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrument_name** | **string**| Instrument name |
 **start_timestamp** | **int**| The earliest timestamp to return result for |
 **end_timestamp** | **int**| The most recent timestamp to return result for |
 **count** | **int**| Number of requested items, default - &#x60;10&#x60; | [optional]
 **include_old** | **bool**| Include trades older than 7 days, default - &#x60;false&#x60; | [optional]
 **sorting** | **string**| Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) | [optional]

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


## privateGetUserTradesByInstrumentGet

> object privateGetUserTradesByInstrumentGet($instrument_name, $start_seq, $end_seq, $count, $include_old, $sorting)

Retrieve the latest user trades that have occurred for a specific instrument.

### Example

```php
<?php
require_once(__DIR__ . '/vendor/autoload.php');


// Configure Bearer (Auth. Token) authorization: bearerAuth
$config = OpenAPI\Client\Configuration::getDefaultConfiguration()->setAccessToken('YOUR_ACCESS_TOKEN');


$apiInstance = new OpenAPI\Client\Api\PrivateApi(
    // If you want use custom http client, pass your client which implements `GuzzleHttp\ClientInterface`.
    // This is optional, `GuzzleHttp\Client` will be used as default.
    new GuzzleHttp\Client(),
    $config
);
$instrument_name = BTC-PERPETUAL; // string | Instrument name
$start_seq = 56; // int | The sequence number of the first trade to be returned
$end_seq = 56; // int | The sequence number of the last trade to be returned
$count = 56; // int | Number of requested items, default - `10`
$include_old = True; // bool | Include trades older than 7 days, default - `false`
$sorting = 'sorting_example'; // string | Direction of results sorting (`default` value means no sorting, results will be returned in order in which they left the database)

try {
    $result = $apiInstance->privateGetUserTradesByInstrumentGet($instrument_name, $start_seq, $end_seq, $count, $include_old, $sorting);
    print_r($result);
} catch (Exception $e) {
    echo 'Exception when calling PrivateApi->privateGetUserTradesByInstrumentGet: ', $e->getMessage(), PHP_EOL;
}
?>
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrument_name** | **string**| Instrument name |
 **start_seq** | **int**| The sequence number of the first trade to be returned | [optional]
 **end_seq** | **int**| The sequence number of the last trade to be returned | [optional]
 **count** | **int**| Number of requested items, default - &#x60;10&#x60; | [optional]
 **include_old** | **bool**| Include trades older than 7 days, default - &#x60;false&#x60; | [optional]
 **sorting** | **string**| Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) | [optional]

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


## privateGetUserTradesByOrderGet

> object privateGetUserTradesByOrderGet($order_id, $sorting)

Retrieve the list of user trades that was created for given order

### Example

```php
<?php
require_once(__DIR__ . '/vendor/autoload.php');


// Configure Bearer (Auth. Token) authorization: bearerAuth
$config = OpenAPI\Client\Configuration::getDefaultConfiguration()->setAccessToken('YOUR_ACCESS_TOKEN');


$apiInstance = new OpenAPI\Client\Api\PrivateApi(
    // If you want use custom http client, pass your client which implements `GuzzleHttp\ClientInterface`.
    // This is optional, `GuzzleHttp\Client` will be used as default.
    new GuzzleHttp\Client(),
    $config
);
$order_id = ETH-100234; // string | The order id
$sorting = 'sorting_example'; // string | Direction of results sorting (`default` value means no sorting, results will be returned in order in which they left the database)

try {
    $result = $apiInstance->privateGetUserTradesByOrderGet($order_id, $sorting);
    print_r($result);
} catch (Exception $e) {
    echo 'Exception when calling PrivateApi->privateGetUserTradesByOrderGet: ', $e->getMessage(), PHP_EOL;
}
?>
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **order_id** | **string**| The order id |
 **sorting** | **string**| Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) | [optional]

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


$apiInstance = new OpenAPI\Client\Api\PrivateApi(
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
    echo 'Exception when calling PrivateApi->privateGetWithdrawalsGet: ', $e->getMessage(), PHP_EOL;
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


$apiInstance = new OpenAPI\Client\Api\PrivateApi(
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
    echo 'Exception when calling PrivateApi->privateRemoveFromAddressBookGet: ', $e->getMessage(), PHP_EOL;
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


## privateSellGet

> object privateSellGet($instrument_name, $amount, $type, $label, $price, $time_in_force, $max_show, $post_only, $reduce_only, $stop_price, $trigger, $advanced)

Places a sell order for an instrument.

### Example

```php
<?php
require_once(__DIR__ . '/vendor/autoload.php');


// Configure Bearer (Auth. Token) authorization: bearerAuth
$config = OpenAPI\Client\Configuration::getDefaultConfiguration()->setAccessToken('YOUR_ACCESS_TOKEN');


$apiInstance = new OpenAPI\Client\Api\PrivateApi(
    // If you want use custom http client, pass your client which implements `GuzzleHttp\ClientInterface`.
    // This is optional, `GuzzleHttp\Client` will be used as default.
    new GuzzleHttp\Client(),
    $config
);
$instrument_name = BTC-PERPETUAL; // string | Instrument name
$amount = 3.4; // float | It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH
$type = 'type_example'; // string | The order type, default: `\"limit\"`
$label = 'label_example'; // string | user defined label for the order (maximum 32 characters)
$price = 3.4; // float | <p>The order price in base currency (Only for limit and stop_limit orders)</p> <p>When adding order with advanced=usd, the field price should be the option price value in USD.</p> <p>When adding order with advanced=implv, the field price should be a value of implied volatility in percentages. For example,  price=100, means implied volatility of 100%</p>
$time_in_force = 'good_til_cancelled'; // string | <p>Specifies how long the order remains in effect. Default `\"good_til_cancelled\"`</p> <ul> <li>`\"good_til_cancelled\"` - unfilled order remains in order book until cancelled</li> <li>`\"fill_or_kill\"` - execute a transaction immediately and completely or not at all</li> <li>`\"immediate_or_cancel\"` - execute a transaction immediately, and any portion of the order that cannot be immediately filled is cancelled</li> </ul>
$max_show = 1; // float | Maximum amount within an order to be shown to other customers, `0` for invisible order
$post_only = true; // bool | <p>If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.</p> <p>Only valid in combination with time_in_force=`\"good_til_cancelled\"`</p>
$reduce_only = false; // bool | If `true`, the order is considered reduce-only which is intended to only reduce a current position
$stop_price = 3.4; // float | Stop price, required for stop limit orders (Only for stop orders)
$trigger = 'trigger_example'; // string | Defines trigger type, required for `\"stop_limit\"` order type
$advanced = 'advanced_example'; // string | Advanced option order type. (Only for options)

try {
    $result = $apiInstance->privateSellGet($instrument_name, $amount, $type, $label, $price, $time_in_force, $max_show, $post_only, $reduce_only, $stop_price, $trigger, $advanced);
    print_r($result);
} catch (Exception $e) {
    echo 'Exception when calling PrivateApi->privateSellGet: ', $e->getMessage(), PHP_EOL;
}
?>
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrument_name** | **string**| Instrument name |
 **amount** | **float**| It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH |
 **type** | **string**| The order type, default: &#x60;\&quot;limit\&quot;&#x60; | [optional]
 **label** | **string**| user defined label for the order (maximum 32 characters) | [optional]
 **price** | **float**| &lt;p&gt;The order price in base currency (Only for limit and stop_limit orders)&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;usd, the field price should be the option price value in USD.&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;implv, the field price should be a value of implied volatility in percentages. For example,  price&#x3D;100, means implied volatility of 100%&lt;/p&gt; | [optional]
 **time_in_force** | **string**| &lt;p&gt;Specifies how long the order remains in effect. Default &#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; &lt;ul&gt; &lt;li&gt;&#x60;\&quot;good_til_cancelled\&quot;&#x60; - unfilled order remains in order book until cancelled&lt;/li&gt; &lt;li&gt;&#x60;\&quot;fill_or_kill\&quot;&#x60; - execute a transaction immediately and completely or not at all&lt;/li&gt; &lt;li&gt;&#x60;\&quot;immediate_or_cancel\&quot;&#x60; - execute a transaction immediately, and any portion of the order that cannot be immediately filled is cancelled&lt;/li&gt; &lt;/ul&gt; | [optional] [default to &#39;good_til_cancelled&#39;]
 **max_show** | **float**| Maximum amount within an order to be shown to other customers, &#x60;0&#x60; for invisible order | [optional] [default to 1]
 **post_only** | **bool**| &lt;p&gt;If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.&lt;/p&gt; &lt;p&gt;Only valid in combination with time_in_force&#x3D;&#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; | [optional] [default to true]
 **reduce_only** | **bool**| If &#x60;true&#x60;, the order is considered reduce-only which is intended to only reduce a current position | [optional] [default to false]
 **stop_price** | **float**| Stop price, required for stop limit orders (Only for stop orders) | [optional]
 **trigger** | **string**| Defines trigger type, required for &#x60;\&quot;stop_limit\&quot;&#x60; order type | [optional]
 **advanced** | **string**| Advanced option order type. (Only for options) | [optional]

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


## privateSetAnnouncementAsReadGet

> object privateSetAnnouncementAsReadGet($announcement_id)

Marks an announcement as read, so it will not be shown in `get_new_announcements`.

### Example

```php
<?php
require_once(__DIR__ . '/vendor/autoload.php');


// Configure Bearer (Auth. Token) authorization: bearerAuth
$config = OpenAPI\Client\Configuration::getDefaultConfiguration()->setAccessToken('YOUR_ACCESS_TOKEN');


$apiInstance = new OpenAPI\Client\Api\PrivateApi(
    // If you want use custom http client, pass your client which implements `GuzzleHttp\ClientInterface`.
    // This is optional, `GuzzleHttp\Client` will be used as default.
    new GuzzleHttp\Client(),
    $config
);
$announcement_id = 3.4; // float | the ID of the announcement

try {
    $result = $apiInstance->privateSetAnnouncementAsReadGet($announcement_id);
    print_r($result);
} catch (Exception $e) {
    echo 'Exception when calling PrivateApi->privateSetAnnouncementAsReadGet: ', $e->getMessage(), PHP_EOL;
}
?>
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **announcement_id** | **float**| the ID of the announcement |

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


## privateSetEmailForSubaccountGet

> object privateSetEmailForSubaccountGet($sid, $email)

Assign an email address to a subaccount. User will receive an email with confirmation link.

### Example

```php
<?php
require_once(__DIR__ . '/vendor/autoload.php');


// Configure Bearer (Auth. Token) authorization: bearerAuth
$config = OpenAPI\Client\Configuration::getDefaultConfiguration()->setAccessToken('YOUR_ACCESS_TOKEN');


$apiInstance = new OpenAPI\Client\Api\PrivateApi(
    // If you want use custom http client, pass your client which implements `GuzzleHttp\ClientInterface`.
    // This is optional, `GuzzleHttp\Client` will be used as default.
    new GuzzleHttp\Client(),
    $config
);
$sid = 56; // int | The user id for the subaccount
$email = subaccount_1@email.com; // string | The email address for the subaccount

try {
    $result = $apiInstance->privateSetEmailForSubaccountGet($sid, $email);
    print_r($result);
} catch (Exception $e) {
    echo 'Exception when calling PrivateApi->privateSetEmailForSubaccountGet: ', $e->getMessage(), PHP_EOL;
}
?>
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **sid** | **int**| The user id for the subaccount |
 **email** | **string**| The email address for the subaccount |

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


## privateSetEmailLanguageGet

> object privateSetEmailLanguageGet($language)

Changes the language to be used for emails.

### Example

```php
<?php
require_once(__DIR__ . '/vendor/autoload.php');


// Configure Bearer (Auth. Token) authorization: bearerAuth
$config = OpenAPI\Client\Configuration::getDefaultConfiguration()->setAccessToken('YOUR_ACCESS_TOKEN');


$apiInstance = new OpenAPI\Client\Api\PrivateApi(
    // If you want use custom http client, pass your client which implements `GuzzleHttp\ClientInterface`.
    // This is optional, `GuzzleHttp\Client` will be used as default.
    new GuzzleHttp\Client(),
    $config
);
$language = en; // string | The abbreviated language name. Valid values include `\"en\"`, `\"ko\"`, `\"zh\"`

try {
    $result = $apiInstance->privateSetEmailLanguageGet($language);
    print_r($result);
} catch (Exception $e) {
    echo 'Exception when calling PrivateApi->privateSetEmailLanguageGet: ', $e->getMessage(), PHP_EOL;
}
?>
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **language** | **string**| The abbreviated language name. Valid values include &#x60;\&quot;en\&quot;&#x60;, &#x60;\&quot;ko\&quot;&#x60;, &#x60;\&quot;zh\&quot;&#x60; |

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


## privateSetPasswordForSubaccountGet

> object privateSetPasswordForSubaccountGet($sid, $password)

Set the password for the subaccount

### Example

```php
<?php
require_once(__DIR__ . '/vendor/autoload.php');


// Configure Bearer (Auth. Token) authorization: bearerAuth
$config = OpenAPI\Client\Configuration::getDefaultConfiguration()->setAccessToken('YOUR_ACCESS_TOKEN');


$apiInstance = new OpenAPI\Client\Api\PrivateApi(
    // If you want use custom http client, pass your client which implements `GuzzleHttp\ClientInterface`.
    // This is optional, `GuzzleHttp\Client` will be used as default.
    new GuzzleHttp\Client(),
    $config
);
$sid = 56; // int | The user id for the subaccount
$password = 'password_example'; // string | The password for the subaccount

try {
    $result = $apiInstance->privateSetPasswordForSubaccountGet($sid, $password);
    print_r($result);
} catch (Exception $e) {
    echo 'Exception when calling PrivateApi->privateSetPasswordForSubaccountGet: ', $e->getMessage(), PHP_EOL;
}
?>
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **sid** | **int**| The user id for the subaccount |
 **password** | **string**| The password for the subaccount |

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


$apiInstance = new OpenAPI\Client\Api\PrivateApi(
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
    echo 'Exception when calling PrivateApi->privateSubmitTransferToSubaccountGet: ', $e->getMessage(), PHP_EOL;
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


$apiInstance = new OpenAPI\Client\Api\PrivateApi(
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
    echo 'Exception when calling PrivateApi->privateSubmitTransferToUserGet: ', $e->getMessage(), PHP_EOL;
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


$apiInstance = new OpenAPI\Client\Api\PrivateApi(
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
    echo 'Exception when calling PrivateApi->privateToggleDepositAddressCreationGet: ', $e->getMessage(), PHP_EOL;
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


## privateToggleNotificationsFromSubaccountGet

> object privateToggleNotificationsFromSubaccountGet($sid, $state)

Enable or disable sending of notifications for the subaccount.

### Example

```php
<?php
require_once(__DIR__ . '/vendor/autoload.php');


// Configure Bearer (Auth. Token) authorization: bearerAuth
$config = OpenAPI\Client\Configuration::getDefaultConfiguration()->setAccessToken('YOUR_ACCESS_TOKEN');


$apiInstance = new OpenAPI\Client\Api\PrivateApi(
    // If you want use custom http client, pass your client which implements `GuzzleHttp\ClientInterface`.
    // This is optional, `GuzzleHttp\Client` will be used as default.
    new GuzzleHttp\Client(),
    $config
);
$sid = 56; // int | The user id for the subaccount
$state = True; // bool | enable (`true`) or disable (`false`) notifications

try {
    $result = $apiInstance->privateToggleNotificationsFromSubaccountGet($sid, $state);
    print_r($result);
} catch (Exception $e) {
    echo 'Exception when calling PrivateApi->privateToggleNotificationsFromSubaccountGet: ', $e->getMessage(), PHP_EOL;
}
?>
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **sid** | **int**| The user id for the subaccount |
 **state** | **bool**| enable (&#x60;true&#x60;) or disable (&#x60;false&#x60;) notifications |

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


## privateToggleSubaccountLoginGet

> object privateToggleSubaccountLoginGet($sid, $state)

Enable or disable login for a subaccount. If login is disabled and a session for the subaccount exists, this session will be terminated.

### Example

```php
<?php
require_once(__DIR__ . '/vendor/autoload.php');


// Configure Bearer (Auth. Token) authorization: bearerAuth
$config = OpenAPI\Client\Configuration::getDefaultConfiguration()->setAccessToken('YOUR_ACCESS_TOKEN');


$apiInstance = new OpenAPI\Client\Api\PrivateApi(
    // If you want use custom http client, pass your client which implements `GuzzleHttp\ClientInterface`.
    // This is optional, `GuzzleHttp\Client` will be used as default.
    new GuzzleHttp\Client(),
    $config
);
$sid = 56; // int | The user id for the subaccount
$state = 'state_example'; // string | enable or disable login.

try {
    $result = $apiInstance->privateToggleSubaccountLoginGet($sid, $state);
    print_r($result);
} catch (Exception $e) {
    echo 'Exception when calling PrivateApi->privateToggleSubaccountLoginGet: ', $e->getMessage(), PHP_EOL;
}
?>
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **sid** | **int**| The user id for the subaccount |
 **state** | **string**| enable or disable login. |

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


$apiInstance = new OpenAPI\Client\Api\PrivateApi(
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
    echo 'Exception when calling PrivateApi->privateWithdrawGet: ', $e->getMessage(), PHP_EOL;
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

