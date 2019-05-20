# WalletAPI

All URIs are relative to *https://www.deribit.com/api/v2*

Method | HTTP request | Description
------------- | ------------- | -------------
[**privateAddToAddressBookGet**](WalletAPI.md#privateaddtoaddressbookget) | **GET** /private/add_to_address_book | Adds new entry to address book of given type
[**privateCancelTransferByIdGet**](WalletAPI.md#privatecanceltransferbyidget) | **GET** /private/cancel_transfer_by_id | Cancel transfer
[**privateCancelWithdrawalGet**](WalletAPI.md#privatecancelwithdrawalget) | **GET** /private/cancel_withdrawal | Cancels withdrawal request
[**privateCreateDepositAddressGet**](WalletAPI.md#privatecreatedepositaddressget) | **GET** /private/create_deposit_address | Creates deposit address in currency
[**privateGetAddressBookGet**](WalletAPI.md#privategetaddressbookget) | **GET** /private/get_address_book | Retrieves address book of given type
[**privateGetCurrentDepositAddressGet**](WalletAPI.md#privategetcurrentdepositaddressget) | **GET** /private/get_current_deposit_address | Retrieve deposit address for currency
[**privateGetDepositsGet**](WalletAPI.md#privategetdepositsget) | **GET** /private/get_deposits | Retrieve the latest users deposits
[**privateGetTransfersGet**](WalletAPI.md#privategettransfersget) | **GET** /private/get_transfers | Adds new entry to address book of given type
[**privateGetWithdrawalsGet**](WalletAPI.md#privategetwithdrawalsget) | **GET** /private/get_withdrawals | Retrieve the latest users withdrawals
[**privateRemoveFromAddressBookGet**](WalletAPI.md#privateremovefromaddressbookget) | **GET** /private/remove_from_address_book | Adds new entry to address book of given type
[**privateSubmitTransferToSubaccountGet**](WalletAPI.md#privatesubmittransfertosubaccountget) | **GET** /private/submit_transfer_to_subaccount | Transfer funds to a subaccount.
[**privateSubmitTransferToUserGet**](WalletAPI.md#privatesubmittransfertouserget) | **GET** /private/submit_transfer_to_user | Transfer funds to a another user.
[**privateToggleDepositAddressCreationGet**](WalletAPI.md#privatetoggledepositaddresscreationget) | **GET** /private/toggle_deposit_address_creation | Enable or disable deposit address creation
[**privateWithdrawGet**](WalletAPI.md#privatewithdrawget) | **GET** /private/withdraw | Creates a new withdrawal request


# **privateAddToAddressBookGet**
```swift
    open class func privateAddToAddressBookGet(currency: Currency_privateAddToAddressBookGet, type: ModelType_privateAddToAddressBookGet, address: String, name: String, tfa: String? = nil, completion: @escaping (_ data: Any?, _ error: Error?) -> Void)
```

Adds new entry to address book of given type

### Example 
```swift
// The following code samples are still beta. For any issue, please report via http://github.com/OpenAPITools/openapi-generator/issues/new
import OpenAPIClient

let currency = "currency_example" // String | The currency symbol
let type = "type_example" // String | Address book type
let address = "address_example" // String | Address in currency format, it must be in address book
let name = "name_example" // String | Name of address book entry
let tfa = "tfa_example" // String | TFA code, required when TFA is enabled for current account (optional)

// Adds new entry to address book of given type
WalletAPI.privateAddToAddressBookGet(currency: currency, type: type, address: address, name: name, tfa: tfa) { (response, error) in
    guard error == nil else {
        print(error)
        return
    }

    if (response) {
        dump(response)
    }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **String** | The currency symbol | 
 **type** | **String** | Address book type | 
 **address** | **String** | Address in currency format, it must be in address book | 
 **name** | **String** | Name of address book entry | 
 **tfa** | **String** | TFA code, required when TFA is enabled for current account | [optional] 

### Return type

**Any**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateCancelTransferByIdGet**
```swift
    open class func privateCancelTransferByIdGet(currency: Currency_privateCancelTransferByIdGet, id: Int, tfa: String? = nil, completion: @escaping (_ data: Any?, _ error: Error?) -> Void)
```

Cancel transfer

### Example 
```swift
// The following code samples are still beta. For any issue, please report via http://github.com/OpenAPITools/openapi-generator/issues/new
import OpenAPIClient

let currency = "currency_example" // String | The currency symbol
let id = 987 // Int | Id of transfer
let tfa = "tfa_example" // String | TFA code, required when TFA is enabled for current account (optional)

// Cancel transfer
WalletAPI.privateCancelTransferByIdGet(currency: currency, id: id, tfa: tfa) { (response, error) in
    guard error == nil else {
        print(error)
        return
    }

    if (response) {
        dump(response)
    }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **String** | The currency symbol | 
 **id** | **Int** | Id of transfer | 
 **tfa** | **String** | TFA code, required when TFA is enabled for current account | [optional] 

### Return type

**Any**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateCancelWithdrawalGet**
```swift
    open class func privateCancelWithdrawalGet(currency: Currency_privateCancelWithdrawalGet, id: Double, completion: @escaping (_ data: Any?, _ error: Error?) -> Void)
```

Cancels withdrawal request

### Example 
```swift
// The following code samples are still beta. For any issue, please report via http://github.com/OpenAPITools/openapi-generator/issues/new
import OpenAPIClient

let currency = "currency_example" // String | The currency symbol
let id = 987 // Double | The withdrawal id

// Cancels withdrawal request
WalletAPI.privateCancelWithdrawalGet(currency: currency, id: id) { (response, error) in
    guard error == nil else {
        print(error)
        return
    }

    if (response) {
        dump(response)
    }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **String** | The currency symbol | 
 **id** | **Double** | The withdrawal id | 

### Return type

**Any**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateCreateDepositAddressGet**
```swift
    open class func privateCreateDepositAddressGet(currency: Currency_privateCreateDepositAddressGet, completion: @escaping (_ data: Any?, _ error: Error?) -> Void)
```

Creates deposit address in currency

### Example 
```swift
// The following code samples are still beta. For any issue, please report via http://github.com/OpenAPITools/openapi-generator/issues/new
import OpenAPIClient

let currency = "currency_example" // String | The currency symbol

// Creates deposit address in currency
WalletAPI.privateCreateDepositAddressGet(currency: currency) { (response, error) in
    guard error == nil else {
        print(error)
        return
    }

    if (response) {
        dump(response)
    }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **String** | The currency symbol | 

### Return type

**Any**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateGetAddressBookGet**
```swift
    open class func privateGetAddressBookGet(currency: Currency_privateGetAddressBookGet, type: ModelType_privateGetAddressBookGet, completion: @escaping (_ data: Any?, _ error: Error?) -> Void)
```

Retrieves address book of given type

### Example 
```swift
// The following code samples are still beta. For any issue, please report via http://github.com/OpenAPITools/openapi-generator/issues/new
import OpenAPIClient

let currency = "currency_example" // String | The currency symbol
let type = "type_example" // String | Address book type

// Retrieves address book of given type
WalletAPI.privateGetAddressBookGet(currency: currency, type: type) { (response, error) in
    guard error == nil else {
        print(error)
        return
    }

    if (response) {
        dump(response)
    }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **String** | The currency symbol | 
 **type** | **String** | Address book type | 

### Return type

**Any**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateGetCurrentDepositAddressGet**
```swift
    open class func privateGetCurrentDepositAddressGet(currency: Currency_privateGetCurrentDepositAddressGet, completion: @escaping (_ data: Any?, _ error: Error?) -> Void)
```

Retrieve deposit address for currency

### Example 
```swift
// The following code samples are still beta. For any issue, please report via http://github.com/OpenAPITools/openapi-generator/issues/new
import OpenAPIClient

let currency = "currency_example" // String | The currency symbol

// Retrieve deposit address for currency
WalletAPI.privateGetCurrentDepositAddressGet(currency: currency) { (response, error) in
    guard error == nil else {
        print(error)
        return
    }

    if (response) {
        dump(response)
    }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **String** | The currency symbol | 

### Return type

**Any**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateGetDepositsGet**
```swift
    open class func privateGetDepositsGet(currency: Currency_privateGetDepositsGet, count: Int? = nil, offset: Int? = nil, completion: @escaping (_ data: Any?, _ error: Error?) -> Void)
```

Retrieve the latest users deposits

### Example 
```swift
// The following code samples are still beta. For any issue, please report via http://github.com/OpenAPITools/openapi-generator/issues/new
import OpenAPIClient

let currency = "currency_example" // String | The currency symbol
let count = 987 // Int | Number of requested items, default - `10` (optional)
let offset = 987 // Int | The offset for pagination, default - `0` (optional)

// Retrieve the latest users deposits
WalletAPI.privateGetDepositsGet(currency: currency, count: count, offset: offset) { (response, error) in
    guard error == nil else {
        print(error)
        return
    }

    if (response) {
        dump(response)
    }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **String** | The currency symbol | 
 **count** | **Int** | Number of requested items, default - &#x60;10&#x60; | [optional] 
 **offset** | **Int** | The offset for pagination, default - &#x60;0&#x60; | [optional] 

### Return type

**Any**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateGetTransfersGet**
```swift
    open class func privateGetTransfersGet(currency: Currency_privateGetTransfersGet, count: Int? = nil, offset: Int? = nil, completion: @escaping (_ data: Any?, _ error: Error?) -> Void)
```

Adds new entry to address book of given type

### Example 
```swift
// The following code samples are still beta. For any issue, please report via http://github.com/OpenAPITools/openapi-generator/issues/new
import OpenAPIClient

let currency = "currency_example" // String | The currency symbol
let count = 987 // Int | Number of requested items, default - `10` (optional)
let offset = 987 // Int | The offset for pagination, default - `0` (optional)

// Adds new entry to address book of given type
WalletAPI.privateGetTransfersGet(currency: currency, count: count, offset: offset) { (response, error) in
    guard error == nil else {
        print(error)
        return
    }

    if (response) {
        dump(response)
    }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **String** | The currency symbol | 
 **count** | **Int** | Number of requested items, default - &#x60;10&#x60; | [optional] 
 **offset** | **Int** | The offset for pagination, default - &#x60;0&#x60; | [optional] 

### Return type

**Any**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateGetWithdrawalsGet**
```swift
    open class func privateGetWithdrawalsGet(currency: Currency_privateGetWithdrawalsGet, count: Int? = nil, offset: Int? = nil, completion: @escaping (_ data: Any?, _ error: Error?) -> Void)
```

Retrieve the latest users withdrawals

### Example 
```swift
// The following code samples are still beta. For any issue, please report via http://github.com/OpenAPITools/openapi-generator/issues/new
import OpenAPIClient

let currency = "currency_example" // String | The currency symbol
let count = 987 // Int | Number of requested items, default - `10` (optional)
let offset = 987 // Int | The offset for pagination, default - `0` (optional)

// Retrieve the latest users withdrawals
WalletAPI.privateGetWithdrawalsGet(currency: currency, count: count, offset: offset) { (response, error) in
    guard error == nil else {
        print(error)
        return
    }

    if (response) {
        dump(response)
    }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **String** | The currency symbol | 
 **count** | **Int** | Number of requested items, default - &#x60;10&#x60; | [optional] 
 **offset** | **Int** | The offset for pagination, default - &#x60;0&#x60; | [optional] 

### Return type

**Any**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateRemoveFromAddressBookGet**
```swift
    open class func privateRemoveFromAddressBookGet(currency: Currency_privateRemoveFromAddressBookGet, type: ModelType_privateRemoveFromAddressBookGet, address: String, tfa: String? = nil, completion: @escaping (_ data: Any?, _ error: Error?) -> Void)
```

Adds new entry to address book of given type

### Example 
```swift
// The following code samples are still beta. For any issue, please report via http://github.com/OpenAPITools/openapi-generator/issues/new
import OpenAPIClient

let currency = "currency_example" // String | The currency symbol
let type = "type_example" // String | Address book type
let address = "address_example" // String | Address in currency format, it must be in address book
let tfa = "tfa_example" // String | TFA code, required when TFA is enabled for current account (optional)

// Adds new entry to address book of given type
WalletAPI.privateRemoveFromAddressBookGet(currency: currency, type: type, address: address, tfa: tfa) { (response, error) in
    guard error == nil else {
        print(error)
        return
    }

    if (response) {
        dump(response)
    }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **String** | The currency symbol | 
 **type** | **String** | Address book type | 
 **address** | **String** | Address in currency format, it must be in address book | 
 **tfa** | **String** | TFA code, required when TFA is enabled for current account | [optional] 

### Return type

**Any**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateSubmitTransferToSubaccountGet**
```swift
    open class func privateSubmitTransferToSubaccountGet(currency: Currency_privateSubmitTransferToSubaccountGet, amount: Double, destination: Int, completion: @escaping (_ data: Any?, _ error: Error?) -> Void)
```

Transfer funds to a subaccount.

### Example 
```swift
// The following code samples are still beta. For any issue, please report via http://github.com/OpenAPITools/openapi-generator/issues/new
import OpenAPIClient

let currency = "currency_example" // String | The currency symbol
let amount = 987 // Double | Amount of funds to be transferred
let destination = 987 // Int | Id of destination subaccount

// Transfer funds to a subaccount.
WalletAPI.privateSubmitTransferToSubaccountGet(currency: currency, amount: amount, destination: destination) { (response, error) in
    guard error == nil else {
        print(error)
        return
    }

    if (response) {
        dump(response)
    }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **String** | The currency symbol | 
 **amount** | **Double** | Amount of funds to be transferred | 
 **destination** | **Int** | Id of destination subaccount | 

### Return type

**Any**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateSubmitTransferToUserGet**
```swift
    open class func privateSubmitTransferToUserGet(currency: Currency_privateSubmitTransferToUserGet, amount: Double, destination: String, tfa: String? = nil, completion: @escaping (_ data: Any?, _ error: Error?) -> Void)
```

Transfer funds to a another user.

### Example 
```swift
// The following code samples are still beta. For any issue, please report via http://github.com/OpenAPITools/openapi-generator/issues/new
import OpenAPIClient

let currency = "currency_example" // String | The currency symbol
let amount = 987 // Double | Amount of funds to be transferred
let destination = "destination_example" // String | Destination address from address book
let tfa = "tfa_example" // String | TFA code, required when TFA is enabled for current account (optional)

// Transfer funds to a another user.
WalletAPI.privateSubmitTransferToUserGet(currency: currency, amount: amount, destination: destination, tfa: tfa) { (response, error) in
    guard error == nil else {
        print(error)
        return
    }

    if (response) {
        dump(response)
    }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **String** | The currency symbol | 
 **amount** | **Double** | Amount of funds to be transferred | 
 **destination** | **String** | Destination address from address book | 
 **tfa** | **String** | TFA code, required when TFA is enabled for current account | [optional] 

### Return type

**Any**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateToggleDepositAddressCreationGet**
```swift
    open class func privateToggleDepositAddressCreationGet(currency: Currency_privateToggleDepositAddressCreationGet, state: Bool, completion: @escaping (_ data: Any?, _ error: Error?) -> Void)
```

Enable or disable deposit address creation

### Example 
```swift
// The following code samples are still beta. For any issue, please report via http://github.com/OpenAPITools/openapi-generator/issues/new
import OpenAPIClient

let currency = "currency_example" // String | The currency symbol
let state = false // Bool | 

// Enable or disable deposit address creation
WalletAPI.privateToggleDepositAddressCreationGet(currency: currency, state: state) { (response, error) in
    guard error == nil else {
        print(error)
        return
    }

    if (response) {
        dump(response)
    }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **String** | The currency symbol | 
 **state** | **Bool** |  | 

### Return type

**Any**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateWithdrawGet**
```swift
    open class func privateWithdrawGet(currency: Currency_privateWithdrawGet, address: String, amount: Double, priority: Priority_privateWithdrawGet? = nil, tfa: String? = nil, completion: @escaping (_ data: Any?, _ error: Error?) -> Void)
```

Creates a new withdrawal request

### Example 
```swift
// The following code samples are still beta. For any issue, please report via http://github.com/OpenAPITools/openapi-generator/issues/new
import OpenAPIClient

let currency = "currency_example" // String | The currency symbol
let address = "address_example" // String | Address in currency format, it must be in address book
let amount = 987 // Double | Amount of funds to be withdrawn
let priority = "priority_example" // String | Withdrawal priority, optional for BTC, default: `high` (optional)
let tfa = "tfa_example" // String | TFA code, required when TFA is enabled for current account (optional)

// Creates a new withdrawal request
WalletAPI.privateWithdrawGet(currency: currency, address: address, amount: amount, priority: priority, tfa: tfa) { (response, error) in
    guard error == nil else {
        print(error)
        return
    }

    if (response) {
        dump(response)
    }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **String** | The currency symbol | 
 **address** | **String** | Address in currency format, it must be in address book | 
 **amount** | **Double** | Amount of funds to be withdrawn | 
 **priority** | **String** | Withdrawal priority, optional for BTC, default: &#x60;high&#x60; | [optional] 
 **tfa** | **String** | TFA code, required when TFA is enabled for current account | [optional] 

### Return type

**Any**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

