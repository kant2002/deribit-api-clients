# DeribitApi.PrivateApi

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

> Object privateAddToAddressBookGet(currency, type, address, name, opts)

Adds new entry to address book of given type

### Example

```javascript
import DeribitApi from 'deribit_api';
let defaultClient = DeribitApi.ApiClient.instance;
// Configure Bearer (Auth. Token) access token for authorization: bearerAuth
let bearerAuth = defaultClient.authentications['bearerAuth'];
bearerAuth.accessToken = "YOUR ACCESS TOKEN"

let apiInstance = new DeribitApi.PrivateApi();
let currency = "currency_example"; // String | The currency symbol
let type = "type_example"; // String | Address book type
let address = "address_example"; // String | Address in currency format, it must be in address book
let name = Main address; // String | Name of address book entry
let opts = {
  'tfa': "tfa_example" // String | TFA code, required when TFA is enabled for current account
};
apiInstance.privateAddToAddressBookGet(currency, type, address, name, opts, (error, data, response) => {
  if (error) {
    console.error(error);
  } else {
    console.log('API called successfully. Returned data: ' + data);
  }
});
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **String**| The currency symbol | 
 **type** | **String**| Address book type | 
 **address** | **String**| Address in currency format, it must be in address book | 
 **name** | **String**| Name of address book entry | 
 **tfa** | **String**| TFA code, required when TFA is enabled for current account | [optional] 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## privateBuyGet

> Object privateBuyGet(instrumentName, amount, opts)

Places a buy order for an instrument.

### Example

```javascript
import DeribitApi from 'deribit_api';
let defaultClient = DeribitApi.ApiClient.instance;
// Configure Bearer (Auth. Token) access token for authorization: bearerAuth
let bearerAuth = defaultClient.authentications['bearerAuth'];
bearerAuth.accessToken = "YOUR ACCESS TOKEN"

let apiInstance = new DeribitApi.PrivateApi();
let instrumentName = BTC-PERPETUAL; // String | Instrument name
let amount = 3.4; // Number | It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH
let opts = {
  'type': "type_example", // String | The order type, default: `\"limit\"`
  'label': "label_example", // String | user defined label for the order (maximum 32 characters)
  'price': 3.4, // Number | <p>The order price in base currency (Only for limit and stop_limit orders)</p> <p>When adding order with advanced=usd, the field price should be the option price value in USD.</p> <p>When adding order with advanced=implv, the field price should be a value of implied volatility in percentages. For example,  price=100, means implied volatility of 100%</p>
  'timeInForce': "'good_til_cancelled'", // String | <p>Specifies how long the order remains in effect. Default `\"good_til_cancelled\"`</p> <ul> <li>`\"good_til_cancelled\"` - unfilled order remains in order book until cancelled</li> <li>`\"fill_or_kill\"` - execute a transaction immediately and completely or not at all</li> <li>`\"immediate_or_cancel\"` - execute a transaction immediately, and any portion of the order that cannot be immediately filled is cancelled</li> </ul>
  'maxShow': 1, // Number | Maximum amount within an order to be shown to other customers, `0` for invisible order
  'postOnly': true, // Boolean | <p>If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.</p> <p>Only valid in combination with time_in_force=`\"good_til_cancelled\"`</p>
  'reduceOnly': false, // Boolean | If `true`, the order is considered reduce-only which is intended to only reduce a current position
  'stopPrice': 3.4, // Number | Stop price, required for stop limit orders (Only for stop orders)
  'trigger': "trigger_example", // String | Defines trigger type, required for `\"stop_limit\"` order type
  'advanced': "advanced_example" // String | Advanced option order type. (Only for options)
};
apiInstance.privateBuyGet(instrumentName, amount, opts, (error, data, response) => {
  if (error) {
    console.error(error);
  } else {
    console.log('API called successfully. Returned data: ' + data);
  }
});
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrumentName** | **String**| Instrument name | 
 **amount** | **Number**| It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH | 
 **type** | **String**| The order type, default: &#x60;\&quot;limit\&quot;&#x60; | [optional] 
 **label** | **String**| user defined label for the order (maximum 32 characters) | [optional] 
 **price** | **Number**| &lt;p&gt;The order price in base currency (Only for limit and stop_limit orders)&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;usd, the field price should be the option price value in USD.&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;implv, the field price should be a value of implied volatility in percentages. For example,  price&#x3D;100, means implied volatility of 100%&lt;/p&gt; | [optional] 
 **timeInForce** | **String**| &lt;p&gt;Specifies how long the order remains in effect. Default &#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; &lt;ul&gt; &lt;li&gt;&#x60;\&quot;good_til_cancelled\&quot;&#x60; - unfilled order remains in order book until cancelled&lt;/li&gt; &lt;li&gt;&#x60;\&quot;fill_or_kill\&quot;&#x60; - execute a transaction immediately and completely or not at all&lt;/li&gt; &lt;li&gt;&#x60;\&quot;immediate_or_cancel\&quot;&#x60; - execute a transaction immediately, and any portion of the order that cannot be immediately filled is cancelled&lt;/li&gt; &lt;/ul&gt; | [optional] [default to &#39;good_til_cancelled&#39;]
 **maxShow** | **Number**| Maximum amount within an order to be shown to other customers, &#x60;0&#x60; for invisible order | [optional] [default to 1]
 **postOnly** | **Boolean**| &lt;p&gt;If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.&lt;/p&gt; &lt;p&gt;Only valid in combination with time_in_force&#x3D;&#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; | [optional] [default to true]
 **reduceOnly** | **Boolean**| If &#x60;true&#x60;, the order is considered reduce-only which is intended to only reduce a current position | [optional] [default to false]
 **stopPrice** | **Number**| Stop price, required for stop limit orders (Only for stop orders) | [optional] 
 **trigger** | **String**| Defines trigger type, required for &#x60;\&quot;stop_limit\&quot;&#x60; order type | [optional] 
 **advanced** | **String**| Advanced option order type. (Only for options) | [optional] 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## privateCancelAllByCurrencyGet

> Object privateCancelAllByCurrencyGet(currency, opts)

Cancels all orders by currency, optionally filtered by instrument kind and/or order type.

### Example

```javascript
import DeribitApi from 'deribit_api';
let defaultClient = DeribitApi.ApiClient.instance;
// Configure Bearer (Auth. Token) access token for authorization: bearerAuth
let bearerAuth = defaultClient.authentications['bearerAuth'];
bearerAuth.accessToken = "YOUR ACCESS TOKEN"

let apiInstance = new DeribitApi.PrivateApi();
let currency = "currency_example"; // String | The currency symbol
let opts = {
  'kind': "kind_example", // String | Instrument kind, if not provided instruments of all kinds are considered
  'type': "type_example" // String | Order type - limit, stop or all, default - `all`
};
apiInstance.privateCancelAllByCurrencyGet(currency, opts, (error, data, response) => {
  if (error) {
    console.error(error);
  } else {
    console.log('API called successfully. Returned data: ' + data);
  }
});
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **String**| The currency symbol | 
 **kind** | **String**| Instrument kind, if not provided instruments of all kinds are considered | [optional] 
 **type** | **String**| Order type - limit, stop or all, default - &#x60;all&#x60; | [optional] 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## privateCancelAllByInstrumentGet

> Object privateCancelAllByInstrumentGet(instrumentName, opts)

Cancels all orders by instrument, optionally filtered by order type.

### Example

```javascript
import DeribitApi from 'deribit_api';
let defaultClient = DeribitApi.ApiClient.instance;
// Configure Bearer (Auth. Token) access token for authorization: bearerAuth
let bearerAuth = defaultClient.authentications['bearerAuth'];
bearerAuth.accessToken = "YOUR ACCESS TOKEN"

let apiInstance = new DeribitApi.PrivateApi();
let instrumentName = BTC-PERPETUAL; // String | Instrument name
let opts = {
  'type': "type_example" // String | Order type - limit, stop or all, default - `all`
};
apiInstance.privateCancelAllByInstrumentGet(instrumentName, opts, (error, data, response) => {
  if (error) {
    console.error(error);
  } else {
    console.log('API called successfully. Returned data: ' + data);
  }
});
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrumentName** | **String**| Instrument name | 
 **type** | **String**| Order type - limit, stop or all, default - &#x60;all&#x60; | [optional] 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## privateCancelAllGet

> Object privateCancelAllGet()

This method cancels all users orders and stop orders within all currencies and instrument kinds.

### Example

```javascript
import DeribitApi from 'deribit_api';
let defaultClient = DeribitApi.ApiClient.instance;
// Configure Bearer (Auth. Token) access token for authorization: bearerAuth
let bearerAuth = defaultClient.authentications['bearerAuth'];
bearerAuth.accessToken = "YOUR ACCESS TOKEN"

let apiInstance = new DeribitApi.PrivateApi();
apiInstance.privateCancelAllGet((error, data, response) => {
  if (error) {
    console.error(error);
  } else {
    console.log('API called successfully. Returned data: ' + data);
  }
});
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


## privateCancelGet

> Object privateCancelGet(orderId)

Cancel an order, specified by order id

### Example

```javascript
import DeribitApi from 'deribit_api';
let defaultClient = DeribitApi.ApiClient.instance;
// Configure Bearer (Auth. Token) access token for authorization: bearerAuth
let bearerAuth = defaultClient.authentications['bearerAuth'];
bearerAuth.accessToken = "YOUR ACCESS TOKEN"

let apiInstance = new DeribitApi.PrivateApi();
let orderId = ETH-100234; // String | The order id
apiInstance.privateCancelGet(orderId, (error, data, response) => {
  if (error) {
    console.error(error);
  } else {
    console.log('API called successfully. Returned data: ' + data);
  }
});
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **orderId** | **String**| The order id | 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## privateCancelTransferByIdGet

> Object privateCancelTransferByIdGet(currency, id, opts)

Cancel transfer

### Example

```javascript
import DeribitApi from 'deribit_api';
let defaultClient = DeribitApi.ApiClient.instance;
// Configure Bearer (Auth. Token) access token for authorization: bearerAuth
let bearerAuth = defaultClient.authentications['bearerAuth'];
bearerAuth.accessToken = "YOUR ACCESS TOKEN"

let apiInstance = new DeribitApi.PrivateApi();
let currency = "currency_example"; // String | The currency symbol
let id = 12; // Number | Id of transfer
let opts = {
  'tfa': "tfa_example" // String | TFA code, required when TFA is enabled for current account
};
apiInstance.privateCancelTransferByIdGet(currency, id, opts, (error, data, response) => {
  if (error) {
    console.error(error);
  } else {
    console.log('API called successfully. Returned data: ' + data);
  }
});
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **String**| The currency symbol | 
 **id** | **Number**| Id of transfer | 
 **tfa** | **String**| TFA code, required when TFA is enabled for current account | [optional] 

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

```javascript
import DeribitApi from 'deribit_api';
let defaultClient = DeribitApi.ApiClient.instance;
// Configure Bearer (Auth. Token) access token for authorization: bearerAuth
let bearerAuth = defaultClient.authentications['bearerAuth'];
bearerAuth.accessToken = "YOUR ACCESS TOKEN"

let apiInstance = new DeribitApi.PrivateApi();
let currency = "currency_example"; // String | The currency symbol
let id = 1; // Number | The withdrawal id
apiInstance.privateCancelWithdrawalGet(currency, id, (error, data, response) => {
  if (error) {
    console.error(error);
  } else {
    console.log('API called successfully. Returned data: ' + data);
  }
});
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **String**| The currency symbol | 
 **id** | **Number**| The withdrawal id | 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## privateChangeSubaccountNameGet

> Object privateChangeSubaccountNameGet(sid, name)

Change the user name for a subaccount

### Example

```javascript
import DeribitApi from 'deribit_api';
let defaultClient = DeribitApi.ApiClient.instance;
// Configure Bearer (Auth. Token) access token for authorization: bearerAuth
let bearerAuth = defaultClient.authentications['bearerAuth'];
bearerAuth.accessToken = "YOUR ACCESS TOKEN"

let apiInstance = new DeribitApi.PrivateApi();
let sid = 56; // Number | The user id for the subaccount
let name = newUserName; // String | The new user name
apiInstance.privateChangeSubaccountNameGet(sid, name, (error, data, response) => {
  if (error) {
    console.error(error);
  } else {
    console.log('API called successfully. Returned data: ' + data);
  }
});
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **sid** | **Number**| The user id for the subaccount | 
 **name** | **String**| The new user name | 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## privateClosePositionGet

> Object privateClosePositionGet(instrumentName, type, opts)

Makes closing position reduce only order .

### Example

```javascript
import DeribitApi from 'deribit_api';
let defaultClient = DeribitApi.ApiClient.instance;
// Configure Bearer (Auth. Token) access token for authorization: bearerAuth
let bearerAuth = defaultClient.authentications['bearerAuth'];
bearerAuth.accessToken = "YOUR ACCESS TOKEN"

let apiInstance = new DeribitApi.PrivateApi();
let instrumentName = BTC-PERPETUAL; // String | Instrument name
let type = "type_example"; // String | The order type
let opts = {
  'price': 3.4 // Number | Optional price for limit order.
};
apiInstance.privateClosePositionGet(instrumentName, type, opts, (error, data, response) => {
  if (error) {
    console.error(error);
  } else {
    console.log('API called successfully. Returned data: ' + data);
  }
});
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrumentName** | **String**| Instrument name | 
 **type** | **String**| The order type | 
 **price** | **Number**| Optional price for limit order. | [optional] 

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

```javascript
import DeribitApi from 'deribit_api';
let defaultClient = DeribitApi.ApiClient.instance;
// Configure Bearer (Auth. Token) access token for authorization: bearerAuth
let bearerAuth = defaultClient.authentications['bearerAuth'];
bearerAuth.accessToken = "YOUR ACCESS TOKEN"

let apiInstance = new DeribitApi.PrivateApi();
let currency = "currency_example"; // String | The currency symbol
apiInstance.privateCreateDepositAddressGet(currency, (error, data, response) => {
  if (error) {
    console.error(error);
  } else {
    console.log('API called successfully. Returned data: ' + data);
  }
});
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **String**| The currency symbol | 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## privateCreateSubaccountGet

> Object privateCreateSubaccountGet()

Create a new subaccount

### Example

```javascript
import DeribitApi from 'deribit_api';
let defaultClient = DeribitApi.ApiClient.instance;
// Configure Bearer (Auth. Token) access token for authorization: bearerAuth
let bearerAuth = defaultClient.authentications['bearerAuth'];
bearerAuth.accessToken = "YOUR ACCESS TOKEN"

let apiInstance = new DeribitApi.PrivateApi();
apiInstance.privateCreateSubaccountGet((error, data, response) => {
  if (error) {
    console.error(error);
  } else {
    console.log('API called successfully. Returned data: ' + data);
  }
});
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


## privateDisableTfaForSubaccountGet

> Object privateDisableTfaForSubaccountGet(sid)

Disable two factor authentication for a subaccount.

### Example

```javascript
import DeribitApi from 'deribit_api';
let defaultClient = DeribitApi.ApiClient.instance;
// Configure Bearer (Auth. Token) access token for authorization: bearerAuth
let bearerAuth = defaultClient.authentications['bearerAuth'];
bearerAuth.accessToken = "YOUR ACCESS TOKEN"

let apiInstance = new DeribitApi.PrivateApi();
let sid = 56; // Number | The user id for the subaccount
apiInstance.privateDisableTfaForSubaccountGet(sid, (error, data, response) => {
  if (error) {
    console.error(error);
  } else {
    console.log('API called successfully. Returned data: ' + data);
  }
});
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **sid** | **Number**| The user id for the subaccount | 

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

```javascript
import DeribitApi from 'deribit_api';
let defaultClient = DeribitApi.ApiClient.instance;
// Configure Bearer (Auth. Token) access token for authorization: bearerAuth
let bearerAuth = defaultClient.authentications['bearerAuth'];
bearerAuth.accessToken = "YOUR ACCESS TOKEN"

let apiInstance = new DeribitApi.PrivateApi();
let password = "password_example"; // String | The password for the subaccount
let code = "code_example"; // String | One time recovery code
apiInstance.privateDisableTfaWithRecoveryCodeGet(password, code, (error, data, response) => {
  if (error) {
    console.error(error);
  } else {
    console.log('API called successfully. Returned data: ' + data);
  }
});
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **password** | **String**| The password for the subaccount | 
 **code** | **String**| One time recovery code | 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## privateEditGet

> Object privateEditGet(orderId, amount, price, opts)

Change price, amount and/or other properties of an order.

### Example

```javascript
import DeribitApi from 'deribit_api';
let defaultClient = DeribitApi.ApiClient.instance;
// Configure Bearer (Auth. Token) access token for authorization: bearerAuth
let bearerAuth = defaultClient.authentications['bearerAuth'];
bearerAuth.accessToken = "YOUR ACCESS TOKEN"

let apiInstance = new DeribitApi.PrivateApi();
let orderId = ETH-100234; // String | The order id
let amount = 3.4; // Number | It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH
let price = 3.4; // Number | <p>The order price in base currency.</p> <p>When editing an option order with advanced=usd, the field price should be the option price value in USD.</p> <p>When editing an option order with advanced=implv, the field price should be a value of implied volatility in percentages. For example,  price=100, means implied volatility of 100%</p>
let opts = {
  'postOnly': true, // Boolean | <p>If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.</p> <p>Only valid in combination with time_in_force=`\"good_til_cancelled\"`</p>
  'advanced': "advanced_example", // String | Advanced option order type. If you have posted an advanced option order, it is necessary to re-supply this parameter when editing it (Only for options)
  'stopPrice': 3.4 // Number | Stop price, required for stop limit orders (Only for stop orders)
};
apiInstance.privateEditGet(orderId, amount, price, opts, (error, data, response) => {
  if (error) {
    console.error(error);
  } else {
    console.log('API called successfully. Returned data: ' + data);
  }
});
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **orderId** | **String**| The order id | 
 **amount** | **Number**| It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH | 
 **price** | **Number**| &lt;p&gt;The order price in base currency.&lt;/p&gt; &lt;p&gt;When editing an option order with advanced&#x3D;usd, the field price should be the option price value in USD.&lt;/p&gt; &lt;p&gt;When editing an option order with advanced&#x3D;implv, the field price should be a value of implied volatility in percentages. For example,  price&#x3D;100, means implied volatility of 100%&lt;/p&gt; | 
 **postOnly** | **Boolean**| &lt;p&gt;If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.&lt;/p&gt; &lt;p&gt;Only valid in combination with time_in_force&#x3D;&#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; | [optional] [default to true]
 **advanced** | **String**| Advanced option order type. If you have posted an advanced option order, it is necessary to re-supply this parameter when editing it (Only for options) | [optional] 
 **stopPrice** | **Number**| Stop price, required for stop limit orders (Only for stop orders) | [optional] 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## privateGetAccountSummaryGet

> Object privateGetAccountSummaryGet(currency, opts)

Retrieves user account summary.

### Example

```javascript
import DeribitApi from 'deribit_api';
let defaultClient = DeribitApi.ApiClient.instance;
// Configure Bearer (Auth. Token) access token for authorization: bearerAuth
let bearerAuth = defaultClient.authentications['bearerAuth'];
bearerAuth.accessToken = "YOUR ACCESS TOKEN"

let apiInstance = new DeribitApi.PrivateApi();
let currency = "currency_example"; // String | The currency symbol
let opts = {
  'extended': false // Boolean | Include additional fields
};
apiInstance.privateGetAccountSummaryGet(currency, opts, (error, data, response) => {
  if (error) {
    console.error(error);
  } else {
    console.log('API called successfully. Returned data: ' + data);
  }
});
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **String**| The currency symbol | 
 **extended** | **Boolean**| Include additional fields | [optional] 

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

```javascript
import DeribitApi from 'deribit_api';
let defaultClient = DeribitApi.ApiClient.instance;
// Configure Bearer (Auth. Token) access token for authorization: bearerAuth
let bearerAuth = defaultClient.authentications['bearerAuth'];
bearerAuth.accessToken = "YOUR ACCESS TOKEN"

let apiInstance = new DeribitApi.PrivateApi();
let currency = "currency_example"; // String | The currency symbol
let type = "type_example"; // String | Address book type
apiInstance.privateGetAddressBookGet(currency, type, (error, data, response) => {
  if (error) {
    console.error(error);
  } else {
    console.log('API called successfully. Returned data: ' + data);
  }
});
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **String**| The currency symbol | 
 **type** | **String**| Address book type | 

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

```javascript
import DeribitApi from 'deribit_api';
let defaultClient = DeribitApi.ApiClient.instance;
// Configure Bearer (Auth. Token) access token for authorization: bearerAuth
let bearerAuth = defaultClient.authentications['bearerAuth'];
bearerAuth.accessToken = "YOUR ACCESS TOKEN"

let apiInstance = new DeribitApi.PrivateApi();
let currency = "currency_example"; // String | The currency symbol
apiInstance.privateGetCurrentDepositAddressGet(currency, (error, data, response) => {
  if (error) {
    console.error(error);
  } else {
    console.log('API called successfully. Returned data: ' + data);
  }
});
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **String**| The currency symbol | 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## privateGetDepositsGet

> Object privateGetDepositsGet(currency, opts)

Retrieve the latest users deposits

### Example

```javascript
import DeribitApi from 'deribit_api';
let defaultClient = DeribitApi.ApiClient.instance;
// Configure Bearer (Auth. Token) access token for authorization: bearerAuth
let bearerAuth = defaultClient.authentications['bearerAuth'];
bearerAuth.accessToken = "YOUR ACCESS TOKEN"

let apiInstance = new DeribitApi.PrivateApi();
let currency = "currency_example"; // String | The currency symbol
let opts = {
  'count': 56, // Number | Number of requested items, default - `10`
  'offset': 10 // Number | The offset for pagination, default - `0`
};
apiInstance.privateGetDepositsGet(currency, opts, (error, data, response) => {
  if (error) {
    console.error(error);
  } else {
    console.log('API called successfully. Returned data: ' + data);
  }
});
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **String**| The currency symbol | 
 **count** | **Number**| Number of requested items, default - &#x60;10&#x60; | [optional] 
 **offset** | **Number**| The offset for pagination, default - &#x60;0&#x60; | [optional] 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## privateGetEmailLanguageGet

> Object privateGetEmailLanguageGet()

Retrieves the language to be used for emails.

### Example

```javascript
import DeribitApi from 'deribit_api';
let defaultClient = DeribitApi.ApiClient.instance;
// Configure Bearer (Auth. Token) access token for authorization: bearerAuth
let bearerAuth = defaultClient.authentications['bearerAuth'];
bearerAuth.accessToken = "YOUR ACCESS TOKEN"

let apiInstance = new DeribitApi.PrivateApi();
apiInstance.privateGetEmailLanguageGet((error, data, response) => {
  if (error) {
    console.error(error);
  } else {
    console.log('API called successfully. Returned data: ' + data);
  }
});
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


## privateGetMarginsGet

> Object privateGetMarginsGet(instrumentName, amount, price)

Get margins for given instrument, amount and price.

### Example

```javascript
import DeribitApi from 'deribit_api';
let defaultClient = DeribitApi.ApiClient.instance;
// Configure Bearer (Auth. Token) access token for authorization: bearerAuth
let bearerAuth = defaultClient.authentications['bearerAuth'];
bearerAuth.accessToken = "YOUR ACCESS TOKEN"

let apiInstance = new DeribitApi.PrivateApi();
let instrumentName = BTC-PERPETUAL; // String | Instrument name
let amount = 1; // Number | Amount, integer for future, float for option. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH.
let price = 3.4; // Number | Price
apiInstance.privateGetMarginsGet(instrumentName, amount, price, (error, data, response) => {
  if (error) {
    console.error(error);
  } else {
    console.log('API called successfully. Returned data: ' + data);
  }
});
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrumentName** | **String**| Instrument name | 
 **amount** | **Number**| Amount, integer for future, float for option. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH. | 
 **price** | **Number**| Price | 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## privateGetNewAnnouncementsGet

> Object privateGetNewAnnouncementsGet()

Retrieves announcements that have not been marked read by the user.

### Example

```javascript
import DeribitApi from 'deribit_api';
let defaultClient = DeribitApi.ApiClient.instance;
// Configure Bearer (Auth. Token) access token for authorization: bearerAuth
let bearerAuth = defaultClient.authentications['bearerAuth'];
bearerAuth.accessToken = "YOUR ACCESS TOKEN"

let apiInstance = new DeribitApi.PrivateApi();
apiInstance.privateGetNewAnnouncementsGet((error, data, response) => {
  if (error) {
    console.error(error);
  } else {
    console.log('API called successfully. Returned data: ' + data);
  }
});
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


## privateGetOpenOrdersByCurrencyGet

> Object privateGetOpenOrdersByCurrencyGet(currency, opts)

Retrieves list of user&#39;s open orders.

### Example

```javascript
import DeribitApi from 'deribit_api';
let defaultClient = DeribitApi.ApiClient.instance;
// Configure Bearer (Auth. Token) access token for authorization: bearerAuth
let bearerAuth = defaultClient.authentications['bearerAuth'];
bearerAuth.accessToken = "YOUR ACCESS TOKEN"

let apiInstance = new DeribitApi.PrivateApi();
let currency = "currency_example"; // String | The currency symbol
let opts = {
  'kind': "kind_example", // String | Instrument kind, if not provided instruments of all kinds are considered
  'type': "type_example" // String | Order type, default - `all`
};
apiInstance.privateGetOpenOrdersByCurrencyGet(currency, opts, (error, data, response) => {
  if (error) {
    console.error(error);
  } else {
    console.log('API called successfully. Returned data: ' + data);
  }
});
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **String**| The currency symbol | 
 **kind** | **String**| Instrument kind, if not provided instruments of all kinds are considered | [optional] 
 **type** | **String**| Order type, default - &#x60;all&#x60; | [optional] 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## privateGetOpenOrdersByInstrumentGet

> Object privateGetOpenOrdersByInstrumentGet(instrumentName, opts)

Retrieves list of user&#39;s open orders within given Instrument.

### Example

```javascript
import DeribitApi from 'deribit_api';
let defaultClient = DeribitApi.ApiClient.instance;
// Configure Bearer (Auth. Token) access token for authorization: bearerAuth
let bearerAuth = defaultClient.authentications['bearerAuth'];
bearerAuth.accessToken = "YOUR ACCESS TOKEN"

let apiInstance = new DeribitApi.PrivateApi();
let instrumentName = BTC-PERPETUAL; // String | Instrument name
let opts = {
  'type': "type_example" // String | Order type, default - `all`
};
apiInstance.privateGetOpenOrdersByInstrumentGet(instrumentName, opts, (error, data, response) => {
  if (error) {
    console.error(error);
  } else {
    console.log('API called successfully. Returned data: ' + data);
  }
});
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrumentName** | **String**| Instrument name | 
 **type** | **String**| Order type, default - &#x60;all&#x60; | [optional] 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## privateGetOrderHistoryByCurrencyGet

> Object privateGetOrderHistoryByCurrencyGet(currency, opts)

Retrieves history of orders that have been partially or fully filled.

### Example

```javascript
import DeribitApi from 'deribit_api';
let defaultClient = DeribitApi.ApiClient.instance;
// Configure Bearer (Auth. Token) access token for authorization: bearerAuth
let bearerAuth = defaultClient.authentications['bearerAuth'];
bearerAuth.accessToken = "YOUR ACCESS TOKEN"

let apiInstance = new DeribitApi.PrivateApi();
let currency = "currency_example"; // String | The currency symbol
let opts = {
  'kind': "kind_example", // String | Instrument kind, if not provided instruments of all kinds are considered
  'count': 56, // Number | Number of requested items, default - `20`
  'offset': 10, // Number | The offset for pagination, default - `0`
  'includeOld': false, // Boolean | Include in result orders older than 2 days, default - `false`
  'includeUnfilled': false // Boolean | Include in result fully unfilled closed orders, default - `false`
};
apiInstance.privateGetOrderHistoryByCurrencyGet(currency, opts, (error, data, response) => {
  if (error) {
    console.error(error);
  } else {
    console.log('API called successfully. Returned data: ' + data);
  }
});
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **String**| The currency symbol | 
 **kind** | **String**| Instrument kind, if not provided instruments of all kinds are considered | [optional] 
 **count** | **Number**| Number of requested items, default - &#x60;20&#x60; | [optional] 
 **offset** | **Number**| The offset for pagination, default - &#x60;0&#x60; | [optional] 
 **includeOld** | **Boolean**| Include in result orders older than 2 days, default - &#x60;false&#x60; | [optional] 
 **includeUnfilled** | **Boolean**| Include in result fully unfilled closed orders, default - &#x60;false&#x60; | [optional] 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## privateGetOrderHistoryByInstrumentGet

> Object privateGetOrderHistoryByInstrumentGet(instrumentName, opts)

Retrieves history of orders that have been partially or fully filled.

### Example

```javascript
import DeribitApi from 'deribit_api';
let defaultClient = DeribitApi.ApiClient.instance;
// Configure Bearer (Auth. Token) access token for authorization: bearerAuth
let bearerAuth = defaultClient.authentications['bearerAuth'];
bearerAuth.accessToken = "YOUR ACCESS TOKEN"

let apiInstance = new DeribitApi.PrivateApi();
let instrumentName = BTC-PERPETUAL; // String | Instrument name
let opts = {
  'count': 56, // Number | Number of requested items, default - `20`
  'offset': 10, // Number | The offset for pagination, default - `0`
  'includeOld': false, // Boolean | Include in result orders older than 2 days, default - `false`
  'includeUnfilled': false // Boolean | Include in result fully unfilled closed orders, default - `false`
};
apiInstance.privateGetOrderHistoryByInstrumentGet(instrumentName, opts, (error, data, response) => {
  if (error) {
    console.error(error);
  } else {
    console.log('API called successfully. Returned data: ' + data);
  }
});
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrumentName** | **String**| Instrument name | 
 **count** | **Number**| Number of requested items, default - &#x60;20&#x60; | [optional] 
 **offset** | **Number**| The offset for pagination, default - &#x60;0&#x60; | [optional] 
 **includeOld** | **Boolean**| Include in result orders older than 2 days, default - &#x60;false&#x60; | [optional] 
 **includeUnfilled** | **Boolean**| Include in result fully unfilled closed orders, default - &#x60;false&#x60; | [optional] 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## privateGetOrderMarginByIdsGet

> Object privateGetOrderMarginByIdsGet(ids)

Retrieves initial margins of given orders

### Example

```javascript
import DeribitApi from 'deribit_api';
let defaultClient = DeribitApi.ApiClient.instance;
// Configure Bearer (Auth. Token) access token for authorization: bearerAuth
let bearerAuth = defaultClient.authentications['bearerAuth'];
bearerAuth.accessToken = "YOUR ACCESS TOKEN"

let apiInstance = new DeribitApi.PrivateApi();
let ids = ["123456"]; // [String] | Ids of orders
apiInstance.privateGetOrderMarginByIdsGet(ids, (error, data, response) => {
  if (error) {
    console.error(error);
  } else {
    console.log('API called successfully. Returned data: ' + data);
  }
});
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **ids** | [**[String]**](String.md)| Ids of orders | 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## privateGetOrderStateGet

> Object privateGetOrderStateGet(orderId)

Retrieve the current state of an order.

### Example

```javascript
import DeribitApi from 'deribit_api';
let defaultClient = DeribitApi.ApiClient.instance;
// Configure Bearer (Auth. Token) access token for authorization: bearerAuth
let bearerAuth = defaultClient.authentications['bearerAuth'];
bearerAuth.accessToken = "YOUR ACCESS TOKEN"

let apiInstance = new DeribitApi.PrivateApi();
let orderId = ETH-100234; // String | The order id
apiInstance.privateGetOrderStateGet(orderId, (error, data, response) => {
  if (error) {
    console.error(error);
  } else {
    console.log('API called successfully. Returned data: ' + data);
  }
});
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **orderId** | **String**| The order id | 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## privateGetPositionGet

> Object privateGetPositionGet(instrumentName)

Retrieve user position.

### Example

```javascript
import DeribitApi from 'deribit_api';
let defaultClient = DeribitApi.ApiClient.instance;
// Configure Bearer (Auth. Token) access token for authorization: bearerAuth
let bearerAuth = defaultClient.authentications['bearerAuth'];
bearerAuth.accessToken = "YOUR ACCESS TOKEN"

let apiInstance = new DeribitApi.PrivateApi();
let instrumentName = BTC-PERPETUAL; // String | Instrument name
apiInstance.privateGetPositionGet(instrumentName, (error, data, response) => {
  if (error) {
    console.error(error);
  } else {
    console.log('API called successfully. Returned data: ' + data);
  }
});
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrumentName** | **String**| Instrument name | 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## privateGetPositionsGet

> Object privateGetPositionsGet(currency, opts)

Retrieve user positions.

### Example

```javascript
import DeribitApi from 'deribit_api';
let defaultClient = DeribitApi.ApiClient.instance;
// Configure Bearer (Auth. Token) access token for authorization: bearerAuth
let bearerAuth = defaultClient.authentications['bearerAuth'];
bearerAuth.accessToken = "YOUR ACCESS TOKEN"

let apiInstance = new DeribitApi.PrivateApi();
let currency = "currency_example"; // String | 
let opts = {
  'kind': "kind_example" // String | Kind filter on positions
};
apiInstance.privateGetPositionsGet(currency, opts, (error, data, response) => {
  if (error) {
    console.error(error);
  } else {
    console.log('API called successfully. Returned data: ' + data);
  }
});
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **String**|  | 
 **kind** | **String**| Kind filter on positions | [optional] 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## privateGetSettlementHistoryByCurrencyGet

> Object privateGetSettlementHistoryByCurrencyGet(currency, opts)

Retrieves settlement, delivery and bankruptcy events that have affected your account.

### Example

```javascript
import DeribitApi from 'deribit_api';
let defaultClient = DeribitApi.ApiClient.instance;
// Configure Bearer (Auth. Token) access token for authorization: bearerAuth
let bearerAuth = defaultClient.authentications['bearerAuth'];
bearerAuth.accessToken = "YOUR ACCESS TOKEN"

let apiInstance = new DeribitApi.PrivateApi();
let currency = "currency_example"; // String | The currency symbol
let opts = {
  'type': "type_example", // String | Settlement type
  'count': 56 // Number | Number of requested items, default - `20`
};
apiInstance.privateGetSettlementHistoryByCurrencyGet(currency, opts, (error, data, response) => {
  if (error) {
    console.error(error);
  } else {
    console.log('API called successfully. Returned data: ' + data);
  }
});
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **String**| The currency symbol | 
 **type** | **String**| Settlement type | [optional] 
 **count** | **Number**| Number of requested items, default - &#x60;20&#x60; | [optional] 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## privateGetSettlementHistoryByInstrumentGet

> Object privateGetSettlementHistoryByInstrumentGet(instrumentName, opts)

Retrieves public settlement, delivery and bankruptcy events filtered by instrument name

### Example

```javascript
import DeribitApi from 'deribit_api';
let defaultClient = DeribitApi.ApiClient.instance;
// Configure Bearer (Auth. Token) access token for authorization: bearerAuth
let bearerAuth = defaultClient.authentications['bearerAuth'];
bearerAuth.accessToken = "YOUR ACCESS TOKEN"

let apiInstance = new DeribitApi.PrivateApi();
let instrumentName = BTC-PERPETUAL; // String | Instrument name
let opts = {
  'type': "type_example", // String | Settlement type
  'count': 56 // Number | Number of requested items, default - `20`
};
apiInstance.privateGetSettlementHistoryByInstrumentGet(instrumentName, opts, (error, data, response) => {
  if (error) {
    console.error(error);
  } else {
    console.log('API called successfully. Returned data: ' + data);
  }
});
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrumentName** | **String**| Instrument name | 
 **type** | **String**| Settlement type | [optional] 
 **count** | **Number**| Number of requested items, default - &#x60;20&#x60; | [optional] 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## privateGetSubaccountsGet

> Object privateGetSubaccountsGet(opts)

Get information about subaccounts

### Example

```javascript
import DeribitApi from 'deribit_api';
let defaultClient = DeribitApi.ApiClient.instance;
// Configure Bearer (Auth. Token) access token for authorization: bearerAuth
let bearerAuth = defaultClient.authentications['bearerAuth'];
bearerAuth.accessToken = "YOUR ACCESS TOKEN"

let apiInstance = new DeribitApi.PrivateApi();
let opts = {
  'withPortfolio': true // Boolean | 
};
apiInstance.privateGetSubaccountsGet(opts, (error, data, response) => {
  if (error) {
    console.error(error);
  } else {
    console.log('API called successfully. Returned data: ' + data);
  }
});
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **withPortfolio** | **Boolean**|  | [optional] 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## privateGetTransfersGet

> Object privateGetTransfersGet(currency, opts)

Adds new entry to address book of given type

### Example

```javascript
import DeribitApi from 'deribit_api';
let defaultClient = DeribitApi.ApiClient.instance;
// Configure Bearer (Auth. Token) access token for authorization: bearerAuth
let bearerAuth = defaultClient.authentications['bearerAuth'];
bearerAuth.accessToken = "YOUR ACCESS TOKEN"

let apiInstance = new DeribitApi.PrivateApi();
let currency = "currency_example"; // String | The currency symbol
let opts = {
  'count': 56, // Number | Number of requested items, default - `10`
  'offset': 10 // Number | The offset for pagination, default - `0`
};
apiInstance.privateGetTransfersGet(currency, opts, (error, data, response) => {
  if (error) {
    console.error(error);
  } else {
    console.log('API called successfully. Returned data: ' + data);
  }
});
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **String**| The currency symbol | 
 **count** | **Number**| Number of requested items, default - &#x60;10&#x60; | [optional] 
 **offset** | **Number**| The offset for pagination, default - &#x60;0&#x60; | [optional] 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## privateGetUserTradesByCurrencyAndTimeGet

> Object privateGetUserTradesByCurrencyAndTimeGet(currency, startTimestamp, endTimestamp, opts)

Retrieve the latest user trades that have occurred for instruments in a specific currency symbol and within given time range.

### Example

```javascript
import DeribitApi from 'deribit_api';
let defaultClient = DeribitApi.ApiClient.instance;
// Configure Bearer (Auth. Token) access token for authorization: bearerAuth
let bearerAuth = defaultClient.authentications['bearerAuth'];
bearerAuth.accessToken = "YOUR ACCESS TOKEN"

let apiInstance = new DeribitApi.PrivateApi();
let currency = "currency_example"; // String | The currency symbol
let startTimestamp = 1536569522277; // Number | The earliest timestamp to return result for
let endTimestamp = 1536569522277; // Number | The most recent timestamp to return result for
let opts = {
  'kind': "kind_example", // String | Instrument kind, if not provided instruments of all kinds are considered
  'count': 56, // Number | Number of requested items, default - `10`
  'includeOld': true, // Boolean | Include trades older than 7 days, default - `false`
  'sorting': "sorting_example" // String | Direction of results sorting (`default` value means no sorting, results will be returned in order in which they left the database)
};
apiInstance.privateGetUserTradesByCurrencyAndTimeGet(currency, startTimestamp, endTimestamp, opts, (error, data, response) => {
  if (error) {
    console.error(error);
  } else {
    console.log('API called successfully. Returned data: ' + data);
  }
});
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **String**| The currency symbol | 
 **startTimestamp** | **Number**| The earliest timestamp to return result for | 
 **endTimestamp** | **Number**| The most recent timestamp to return result for | 
 **kind** | **String**| Instrument kind, if not provided instruments of all kinds are considered | [optional] 
 **count** | **Number**| Number of requested items, default - &#x60;10&#x60; | [optional] 
 **includeOld** | **Boolean**| Include trades older than 7 days, default - &#x60;false&#x60; | [optional] 
 **sorting** | **String**| Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) | [optional] 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## privateGetUserTradesByCurrencyGet

> Object privateGetUserTradesByCurrencyGet(currency, opts)

Retrieve the latest user trades that have occurred for instruments in a specific currency symbol.

### Example

```javascript
import DeribitApi from 'deribit_api';
let defaultClient = DeribitApi.ApiClient.instance;
// Configure Bearer (Auth. Token) access token for authorization: bearerAuth
let bearerAuth = defaultClient.authentications['bearerAuth'];
bearerAuth.accessToken = "YOUR ACCESS TOKEN"

let apiInstance = new DeribitApi.PrivateApi();
let currency = "currency_example"; // String | The currency symbol
let opts = {
  'kind': "kind_example", // String | Instrument kind, if not provided instruments of all kinds are considered
  'startId': "startId_example", // String | The ID number of the first trade to be returned
  'endId': "endId_example", // String | The ID number of the last trade to be returned
  'count': 56, // Number | Number of requested items, default - `10`
  'includeOld': true, // Boolean | Include trades older than 7 days, default - `false`
  'sorting': "sorting_example" // String | Direction of results sorting (`default` value means no sorting, results will be returned in order in which they left the database)
};
apiInstance.privateGetUserTradesByCurrencyGet(currency, opts, (error, data, response) => {
  if (error) {
    console.error(error);
  } else {
    console.log('API called successfully. Returned data: ' + data);
  }
});
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **String**| The currency symbol | 
 **kind** | **String**| Instrument kind, if not provided instruments of all kinds are considered | [optional] 
 **startId** | **String**| The ID number of the first trade to be returned | [optional] 
 **endId** | **String**| The ID number of the last trade to be returned | [optional] 
 **count** | **Number**| Number of requested items, default - &#x60;10&#x60; | [optional] 
 **includeOld** | **Boolean**| Include trades older than 7 days, default - &#x60;false&#x60; | [optional] 
 **sorting** | **String**| Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) | [optional] 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## privateGetUserTradesByInstrumentAndTimeGet

> Object privateGetUserTradesByInstrumentAndTimeGet(instrumentName, startTimestamp, endTimestamp, opts)

Retrieve the latest user trades that have occurred for a specific instrument and within given time range.

### Example

```javascript
import DeribitApi from 'deribit_api';
let defaultClient = DeribitApi.ApiClient.instance;
// Configure Bearer (Auth. Token) access token for authorization: bearerAuth
let bearerAuth = defaultClient.authentications['bearerAuth'];
bearerAuth.accessToken = "YOUR ACCESS TOKEN"

let apiInstance = new DeribitApi.PrivateApi();
let instrumentName = BTC-PERPETUAL; // String | Instrument name
let startTimestamp = 1536569522277; // Number | The earliest timestamp to return result for
let endTimestamp = 1536569522277; // Number | The most recent timestamp to return result for
let opts = {
  'count': 56, // Number | Number of requested items, default - `10`
  'includeOld': true, // Boolean | Include trades older than 7 days, default - `false`
  'sorting': "sorting_example" // String | Direction of results sorting (`default` value means no sorting, results will be returned in order in which they left the database)
};
apiInstance.privateGetUserTradesByInstrumentAndTimeGet(instrumentName, startTimestamp, endTimestamp, opts, (error, data, response) => {
  if (error) {
    console.error(error);
  } else {
    console.log('API called successfully. Returned data: ' + data);
  }
});
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrumentName** | **String**| Instrument name | 
 **startTimestamp** | **Number**| The earliest timestamp to return result for | 
 **endTimestamp** | **Number**| The most recent timestamp to return result for | 
 **count** | **Number**| Number of requested items, default - &#x60;10&#x60; | [optional] 
 **includeOld** | **Boolean**| Include trades older than 7 days, default - &#x60;false&#x60; | [optional] 
 **sorting** | **String**| Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) | [optional] 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## privateGetUserTradesByInstrumentGet

> Object privateGetUserTradesByInstrumentGet(instrumentName, opts)

Retrieve the latest user trades that have occurred for a specific instrument.

### Example

```javascript
import DeribitApi from 'deribit_api';
let defaultClient = DeribitApi.ApiClient.instance;
// Configure Bearer (Auth. Token) access token for authorization: bearerAuth
let bearerAuth = defaultClient.authentications['bearerAuth'];
bearerAuth.accessToken = "YOUR ACCESS TOKEN"

let apiInstance = new DeribitApi.PrivateApi();
let instrumentName = BTC-PERPETUAL; // String | Instrument name
let opts = {
  'startSeq': 56, // Number | The sequence number of the first trade to be returned
  'endSeq': 56, // Number | The sequence number of the last trade to be returned
  'count': 56, // Number | Number of requested items, default - `10`
  'includeOld': true, // Boolean | Include trades older than 7 days, default - `false`
  'sorting': "sorting_example" // String | Direction of results sorting (`default` value means no sorting, results will be returned in order in which they left the database)
};
apiInstance.privateGetUserTradesByInstrumentGet(instrumentName, opts, (error, data, response) => {
  if (error) {
    console.error(error);
  } else {
    console.log('API called successfully. Returned data: ' + data);
  }
});
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrumentName** | **String**| Instrument name | 
 **startSeq** | **Number**| The sequence number of the first trade to be returned | [optional] 
 **endSeq** | **Number**| The sequence number of the last trade to be returned | [optional] 
 **count** | **Number**| Number of requested items, default - &#x60;10&#x60; | [optional] 
 **includeOld** | **Boolean**| Include trades older than 7 days, default - &#x60;false&#x60; | [optional] 
 **sorting** | **String**| Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) | [optional] 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## privateGetUserTradesByOrderGet

> Object privateGetUserTradesByOrderGet(orderId, opts)

Retrieve the list of user trades that was created for given order

### Example

```javascript
import DeribitApi from 'deribit_api';
let defaultClient = DeribitApi.ApiClient.instance;
// Configure Bearer (Auth. Token) access token for authorization: bearerAuth
let bearerAuth = defaultClient.authentications['bearerAuth'];
bearerAuth.accessToken = "YOUR ACCESS TOKEN"

let apiInstance = new DeribitApi.PrivateApi();
let orderId = ETH-100234; // String | The order id
let opts = {
  'sorting': "sorting_example" // String | Direction of results sorting (`default` value means no sorting, results will be returned in order in which they left the database)
};
apiInstance.privateGetUserTradesByOrderGet(orderId, opts, (error, data, response) => {
  if (error) {
    console.error(error);
  } else {
    console.log('API called successfully. Returned data: ' + data);
  }
});
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **orderId** | **String**| The order id | 
 **sorting** | **String**| Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) | [optional] 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## privateGetWithdrawalsGet

> Object privateGetWithdrawalsGet(currency, opts)

Retrieve the latest users withdrawals

### Example

```javascript
import DeribitApi from 'deribit_api';
let defaultClient = DeribitApi.ApiClient.instance;
// Configure Bearer (Auth. Token) access token for authorization: bearerAuth
let bearerAuth = defaultClient.authentications['bearerAuth'];
bearerAuth.accessToken = "YOUR ACCESS TOKEN"

let apiInstance = new DeribitApi.PrivateApi();
let currency = "currency_example"; // String | The currency symbol
let opts = {
  'count': 56, // Number | Number of requested items, default - `10`
  'offset': 10 // Number | The offset for pagination, default - `0`
};
apiInstance.privateGetWithdrawalsGet(currency, opts, (error, data, response) => {
  if (error) {
    console.error(error);
  } else {
    console.log('API called successfully. Returned data: ' + data);
  }
});
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **String**| The currency symbol | 
 **count** | **Number**| Number of requested items, default - &#x60;10&#x60; | [optional] 
 **offset** | **Number**| The offset for pagination, default - &#x60;0&#x60; | [optional] 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## privateRemoveFromAddressBookGet

> Object privateRemoveFromAddressBookGet(currency, type, address, opts)

Adds new entry to address book of given type

### Example

```javascript
import DeribitApi from 'deribit_api';
let defaultClient = DeribitApi.ApiClient.instance;
// Configure Bearer (Auth. Token) access token for authorization: bearerAuth
let bearerAuth = defaultClient.authentications['bearerAuth'];
bearerAuth.accessToken = "YOUR ACCESS TOKEN"

let apiInstance = new DeribitApi.PrivateApi();
let currency = "currency_example"; // String | The currency symbol
let type = "type_example"; // String | Address book type
let address = "address_example"; // String | Address in currency format, it must be in address book
let opts = {
  'tfa': "tfa_example" // String | TFA code, required when TFA is enabled for current account
};
apiInstance.privateRemoveFromAddressBookGet(currency, type, address, opts, (error, data, response) => {
  if (error) {
    console.error(error);
  } else {
    console.log('API called successfully. Returned data: ' + data);
  }
});
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **String**| The currency symbol | 
 **type** | **String**| Address book type | 
 **address** | **String**| Address in currency format, it must be in address book | 
 **tfa** | **String**| TFA code, required when TFA is enabled for current account | [optional] 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## privateSellGet

> Object privateSellGet(instrumentName, amount, opts)

Places a sell order for an instrument.

### Example

```javascript
import DeribitApi from 'deribit_api';
let defaultClient = DeribitApi.ApiClient.instance;
// Configure Bearer (Auth. Token) access token for authorization: bearerAuth
let bearerAuth = defaultClient.authentications['bearerAuth'];
bearerAuth.accessToken = "YOUR ACCESS TOKEN"

let apiInstance = new DeribitApi.PrivateApi();
let instrumentName = BTC-PERPETUAL; // String | Instrument name
let amount = 3.4; // Number | It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH
let opts = {
  'type': "type_example", // String | The order type, default: `\"limit\"`
  'label': "label_example", // String | user defined label for the order (maximum 32 characters)
  'price': 3.4, // Number | <p>The order price in base currency (Only for limit and stop_limit orders)</p> <p>When adding order with advanced=usd, the field price should be the option price value in USD.</p> <p>When adding order with advanced=implv, the field price should be a value of implied volatility in percentages. For example,  price=100, means implied volatility of 100%</p>
  'timeInForce': "'good_til_cancelled'", // String | <p>Specifies how long the order remains in effect. Default `\"good_til_cancelled\"`</p> <ul> <li>`\"good_til_cancelled\"` - unfilled order remains in order book until cancelled</li> <li>`\"fill_or_kill\"` - execute a transaction immediately and completely or not at all</li> <li>`\"immediate_or_cancel\"` - execute a transaction immediately, and any portion of the order that cannot be immediately filled is cancelled</li> </ul>
  'maxShow': 1, // Number | Maximum amount within an order to be shown to other customers, `0` for invisible order
  'postOnly': true, // Boolean | <p>If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.</p> <p>Only valid in combination with time_in_force=`\"good_til_cancelled\"`</p>
  'reduceOnly': false, // Boolean | If `true`, the order is considered reduce-only which is intended to only reduce a current position
  'stopPrice': 3.4, // Number | Stop price, required for stop limit orders (Only for stop orders)
  'trigger': "trigger_example", // String | Defines trigger type, required for `\"stop_limit\"` order type
  'advanced': "advanced_example" // String | Advanced option order type. (Only for options)
};
apiInstance.privateSellGet(instrumentName, amount, opts, (error, data, response) => {
  if (error) {
    console.error(error);
  } else {
    console.log('API called successfully. Returned data: ' + data);
  }
});
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrumentName** | **String**| Instrument name | 
 **amount** | **Number**| It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH | 
 **type** | **String**| The order type, default: &#x60;\&quot;limit\&quot;&#x60; | [optional] 
 **label** | **String**| user defined label for the order (maximum 32 characters) | [optional] 
 **price** | **Number**| &lt;p&gt;The order price in base currency (Only for limit and stop_limit orders)&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;usd, the field price should be the option price value in USD.&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;implv, the field price should be a value of implied volatility in percentages. For example,  price&#x3D;100, means implied volatility of 100%&lt;/p&gt; | [optional] 
 **timeInForce** | **String**| &lt;p&gt;Specifies how long the order remains in effect. Default &#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; &lt;ul&gt; &lt;li&gt;&#x60;\&quot;good_til_cancelled\&quot;&#x60; - unfilled order remains in order book until cancelled&lt;/li&gt; &lt;li&gt;&#x60;\&quot;fill_or_kill\&quot;&#x60; - execute a transaction immediately and completely or not at all&lt;/li&gt; &lt;li&gt;&#x60;\&quot;immediate_or_cancel\&quot;&#x60; - execute a transaction immediately, and any portion of the order that cannot be immediately filled is cancelled&lt;/li&gt; &lt;/ul&gt; | [optional] [default to &#39;good_til_cancelled&#39;]
 **maxShow** | **Number**| Maximum amount within an order to be shown to other customers, &#x60;0&#x60; for invisible order | [optional] [default to 1]
 **postOnly** | **Boolean**| &lt;p&gt;If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.&lt;/p&gt; &lt;p&gt;Only valid in combination with time_in_force&#x3D;&#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; | [optional] [default to true]
 **reduceOnly** | **Boolean**| If &#x60;true&#x60;, the order is considered reduce-only which is intended to only reduce a current position | [optional] [default to false]
 **stopPrice** | **Number**| Stop price, required for stop limit orders (Only for stop orders) | [optional] 
 **trigger** | **String**| Defines trigger type, required for &#x60;\&quot;stop_limit\&quot;&#x60; order type | [optional] 
 **advanced** | **String**| Advanced option order type. (Only for options) | [optional] 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## privateSetAnnouncementAsReadGet

> Object privateSetAnnouncementAsReadGet(announcementId)

Marks an announcement as read, so it will not be shown in &#x60;get_new_announcements&#x60;.

### Example

```javascript
import DeribitApi from 'deribit_api';
let defaultClient = DeribitApi.ApiClient.instance;
// Configure Bearer (Auth. Token) access token for authorization: bearerAuth
let bearerAuth = defaultClient.authentications['bearerAuth'];
bearerAuth.accessToken = "YOUR ACCESS TOKEN"

let apiInstance = new DeribitApi.PrivateApi();
let announcementId = 3.4; // Number | the ID of the announcement
apiInstance.privateSetAnnouncementAsReadGet(announcementId, (error, data, response) => {
  if (error) {
    console.error(error);
  } else {
    console.log('API called successfully. Returned data: ' + data);
  }
});
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **announcementId** | **Number**| the ID of the announcement | 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## privateSetEmailForSubaccountGet

> Object privateSetEmailForSubaccountGet(sid, email)

Assign an email address to a subaccount. User will receive an email with confirmation link.

### Example

```javascript
import DeribitApi from 'deribit_api';
let defaultClient = DeribitApi.ApiClient.instance;
// Configure Bearer (Auth. Token) access token for authorization: bearerAuth
let bearerAuth = defaultClient.authentications['bearerAuth'];
bearerAuth.accessToken = "YOUR ACCESS TOKEN"

let apiInstance = new DeribitApi.PrivateApi();
let sid = 56; // Number | The user id for the subaccount
let email = subaccount_1@email.com; // String | The email address for the subaccount
apiInstance.privateSetEmailForSubaccountGet(sid, email, (error, data, response) => {
  if (error) {
    console.error(error);
  } else {
    console.log('API called successfully. Returned data: ' + data);
  }
});
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **sid** | **Number**| The user id for the subaccount | 
 **email** | **String**| The email address for the subaccount | 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## privateSetEmailLanguageGet

> Object privateSetEmailLanguageGet(language)

Changes the language to be used for emails.

### Example

```javascript
import DeribitApi from 'deribit_api';
let defaultClient = DeribitApi.ApiClient.instance;
// Configure Bearer (Auth. Token) access token for authorization: bearerAuth
let bearerAuth = defaultClient.authentications['bearerAuth'];
bearerAuth.accessToken = "YOUR ACCESS TOKEN"

let apiInstance = new DeribitApi.PrivateApi();
let language = en; // String | The abbreviated language name. Valid values include `\"en\"`, `\"ko\"`, `\"zh\"`
apiInstance.privateSetEmailLanguageGet(language, (error, data, response) => {
  if (error) {
    console.error(error);
  } else {
    console.log('API called successfully. Returned data: ' + data);
  }
});
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **language** | **String**| The abbreviated language name. Valid values include &#x60;\&quot;en\&quot;&#x60;, &#x60;\&quot;ko\&quot;&#x60;, &#x60;\&quot;zh\&quot;&#x60; | 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## privateSetPasswordForSubaccountGet

> Object privateSetPasswordForSubaccountGet(sid, password)

Set the password for the subaccount

### Example

```javascript
import DeribitApi from 'deribit_api';
let defaultClient = DeribitApi.ApiClient.instance;
// Configure Bearer (Auth. Token) access token for authorization: bearerAuth
let bearerAuth = defaultClient.authentications['bearerAuth'];
bearerAuth.accessToken = "YOUR ACCESS TOKEN"

let apiInstance = new DeribitApi.PrivateApi();
let sid = 56; // Number | The user id for the subaccount
let password = "password_example"; // String | The password for the subaccount
apiInstance.privateSetPasswordForSubaccountGet(sid, password, (error, data, response) => {
  if (error) {
    console.error(error);
  } else {
    console.log('API called successfully. Returned data: ' + data);
  }
});
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **sid** | **Number**| The user id for the subaccount | 
 **password** | **String**| The password for the subaccount | 

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

```javascript
import DeribitApi from 'deribit_api';
let defaultClient = DeribitApi.ApiClient.instance;
// Configure Bearer (Auth. Token) access token for authorization: bearerAuth
let bearerAuth = defaultClient.authentications['bearerAuth'];
bearerAuth.accessToken = "YOUR ACCESS TOKEN"

let apiInstance = new DeribitApi.PrivateApi();
let currency = "currency_example"; // String | The currency symbol
let amount = 3.4; // Number | Amount of funds to be transferred
let destination = 1; // Number | Id of destination subaccount
apiInstance.privateSubmitTransferToSubaccountGet(currency, amount, destination, (error, data, response) => {
  if (error) {
    console.error(error);
  } else {
    console.log('API called successfully. Returned data: ' + data);
  }
});
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **String**| The currency symbol | 
 **amount** | **Number**| Amount of funds to be transferred | 
 **destination** | **Number**| Id of destination subaccount | 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## privateSubmitTransferToUserGet

> Object privateSubmitTransferToUserGet(currency, amount, destination, opts)

Transfer funds to a another user.

### Example

```javascript
import DeribitApi from 'deribit_api';
let defaultClient = DeribitApi.ApiClient.instance;
// Configure Bearer (Auth. Token) access token for authorization: bearerAuth
let bearerAuth = defaultClient.authentications['bearerAuth'];
bearerAuth.accessToken = "YOUR ACCESS TOKEN"

let apiInstance = new DeribitApi.PrivateApi();
let currency = "currency_example"; // String | The currency symbol
let amount = 3.4; // Number | Amount of funds to be transferred
let destination = "destination_example"; // String | Destination address from address book
let opts = {
  'tfa': "tfa_example" // String | TFA code, required when TFA is enabled for current account
};
apiInstance.privateSubmitTransferToUserGet(currency, amount, destination, opts, (error, data, response) => {
  if (error) {
    console.error(error);
  } else {
    console.log('API called successfully. Returned data: ' + data);
  }
});
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **String**| The currency symbol | 
 **amount** | **Number**| Amount of funds to be transferred | 
 **destination** | **String**| Destination address from address book | 
 **tfa** | **String**| TFA code, required when TFA is enabled for current account | [optional] 

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

```javascript
import DeribitApi from 'deribit_api';
let defaultClient = DeribitApi.ApiClient.instance;
// Configure Bearer (Auth. Token) access token for authorization: bearerAuth
let bearerAuth = defaultClient.authentications['bearerAuth'];
bearerAuth.accessToken = "YOUR ACCESS TOKEN"

let apiInstance = new DeribitApi.PrivateApi();
let currency = "currency_example"; // String | The currency symbol
let state = true; // Boolean | 
apiInstance.privateToggleDepositAddressCreationGet(currency, state, (error, data, response) => {
  if (error) {
    console.error(error);
  } else {
    console.log('API called successfully. Returned data: ' + data);
  }
});
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **String**| The currency symbol | 
 **state** | **Boolean**|  | 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## privateToggleNotificationsFromSubaccountGet

> Object privateToggleNotificationsFromSubaccountGet(sid, state)

Enable or disable sending of notifications for the subaccount.

### Example

```javascript
import DeribitApi from 'deribit_api';
let defaultClient = DeribitApi.ApiClient.instance;
// Configure Bearer (Auth. Token) access token for authorization: bearerAuth
let bearerAuth = defaultClient.authentications['bearerAuth'];
bearerAuth.accessToken = "YOUR ACCESS TOKEN"

let apiInstance = new DeribitApi.PrivateApi();
let sid = 56; // Number | The user id for the subaccount
let state = true; // Boolean | enable (`true`) or disable (`false`) notifications
apiInstance.privateToggleNotificationsFromSubaccountGet(sid, state, (error, data, response) => {
  if (error) {
    console.error(error);
  } else {
    console.log('API called successfully. Returned data: ' + data);
  }
});
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **sid** | **Number**| The user id for the subaccount | 
 **state** | **Boolean**| enable (&#x60;true&#x60;) or disable (&#x60;false&#x60;) notifications | 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## privateToggleSubaccountLoginGet

> Object privateToggleSubaccountLoginGet(sid, state)

Enable or disable login for a subaccount. If login is disabled and a session for the subaccount exists, this session will be terminated.

### Example

```javascript
import DeribitApi from 'deribit_api';
let defaultClient = DeribitApi.ApiClient.instance;
// Configure Bearer (Auth. Token) access token for authorization: bearerAuth
let bearerAuth = defaultClient.authentications['bearerAuth'];
bearerAuth.accessToken = "YOUR ACCESS TOKEN"

let apiInstance = new DeribitApi.PrivateApi();
let sid = 56; // Number | The user id for the subaccount
let state = "state_example"; // String | enable or disable login.
apiInstance.privateToggleSubaccountLoginGet(sid, state, (error, data, response) => {
  if (error) {
    console.error(error);
  } else {
    console.log('API called successfully. Returned data: ' + data);
  }
});
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **sid** | **Number**| The user id for the subaccount | 
 **state** | **String**| enable or disable login. | 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## privateWithdrawGet

> Object privateWithdrawGet(currency, address, amount, opts)

Creates a new withdrawal request

### Example

```javascript
import DeribitApi from 'deribit_api';
let defaultClient = DeribitApi.ApiClient.instance;
// Configure Bearer (Auth. Token) access token for authorization: bearerAuth
let bearerAuth = defaultClient.authentications['bearerAuth'];
bearerAuth.accessToken = "YOUR ACCESS TOKEN"

let apiInstance = new DeribitApi.PrivateApi();
let currency = "currency_example"; // String | The currency symbol
let address = "address_example"; // String | Address in currency format, it must be in address book
let amount = 3.4; // Number | Amount of funds to be withdrawn
let opts = {
  'priority': "priority_example", // String | Withdrawal priority, optional for BTC, default: `high`
  'tfa': "tfa_example" // String | TFA code, required when TFA is enabled for current account
};
apiInstance.privateWithdrawGet(currency, address, amount, opts, (error, data, response) => {
  if (error) {
    console.error(error);
  } else {
    console.log('API called successfully. Returned data: ' + data);
  }
});
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **String**| The currency symbol | 
 **address** | **String**| Address in currency format, it must be in address book | 
 **amount** | **Number**| Amount of funds to be withdrawn | 
 **priority** | **String**| Withdrawal priority, optional for BTC, default: &#x60;high&#x60; | [optional] 
 **tfa** | **String**| TFA code, required when TFA is enabled for current account | [optional] 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

