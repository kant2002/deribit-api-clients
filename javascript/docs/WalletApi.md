# DeribitApi.WalletApi

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

> Object privateAddToAddressBookGet(currency, type, address, name, opts)

Adds new entry to address book of given type

### Example

```javascript
import DeribitApi from 'deribit_api';
let defaultClient = DeribitApi.ApiClient.instance;
// Configure Bearer (Auth. Token) access token for authorization: bearerAuth
let bearerAuth = defaultClient.authentications['bearerAuth'];
bearerAuth.accessToken = "YOUR ACCESS TOKEN"

let apiInstance = new DeribitApi.WalletApi();
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

let apiInstance = new DeribitApi.WalletApi();
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

let apiInstance = new DeribitApi.WalletApi();
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

let apiInstance = new DeribitApi.WalletApi();
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

let apiInstance = new DeribitApi.WalletApi();
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

let apiInstance = new DeribitApi.WalletApi();
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

let apiInstance = new DeribitApi.WalletApi();
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

let apiInstance = new DeribitApi.WalletApi();
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

let apiInstance = new DeribitApi.WalletApi();
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

let apiInstance = new DeribitApi.WalletApi();
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

let apiInstance = new DeribitApi.WalletApi();
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

let apiInstance = new DeribitApi.WalletApi();
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

let apiInstance = new DeribitApi.WalletApi();
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

let apiInstance = new DeribitApi.WalletApi();
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

