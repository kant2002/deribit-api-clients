# InternalAPI

All URIs are relative to *https://www.deribit.com/api/v2*

Method | HTTP request | Description
------------- | ------------- | -------------
[**privateAddToAddressBookGet**](InternalAPI.md#privateaddtoaddressbookget) | **GET** /private/add_to_address_book | Adds new entry to address book of given type
[**privateDisableTfaWithRecoveryCodeGet**](InternalAPI.md#privatedisabletfawithrecoverycodeget) | **GET** /private/disable_tfa_with_recovery_code | Disables TFA with one time recovery code
[**privateGetAddressBookGet**](InternalAPI.md#privategetaddressbookget) | **GET** /private/get_address_book | Retrieves address book of given type
[**privateRemoveFromAddressBookGet**](InternalAPI.md#privateremovefromaddressbookget) | **GET** /private/remove_from_address_book | Adds new entry to address book of given type
[**privateSubmitTransferToSubaccountGet**](InternalAPI.md#privatesubmittransfertosubaccountget) | **GET** /private/submit_transfer_to_subaccount | Transfer funds to a subaccount.
[**privateSubmitTransferToUserGet**](InternalAPI.md#privatesubmittransfertouserget) | **GET** /private/submit_transfer_to_user | Transfer funds to a another user.
[**privateToggleDepositAddressCreationGet**](InternalAPI.md#privatetoggledepositaddresscreationget) | **GET** /private/toggle_deposit_address_creation | Enable or disable deposit address creation
[**publicGetFooterGet**](InternalAPI.md#publicgetfooterget) | **GET** /public/get_footer | Get information to be displayed in the footer of the website.
[**publicGetOptionMarkPricesGet**](InternalAPI.md#publicgetoptionmarkpricesget) | **GET** /public/get_option_mark_prices | Retrives market prices and its implied volatility of options instruments
[**publicValidateFieldGet**](InternalAPI.md#publicvalidatefieldget) | **GET** /public/validate_field | Method used to introduce the client software connected to Deribit platform over websocket. Provided data may have an impact on the maintained connection and will be collected for internal statistical purposes. In response, Deribit will also introduce itself.


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
InternalAPI.privateAddToAddressBookGet(currency: currency, type: type, address: address, name: name, tfa: tfa) { (response, error) in
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

# **privateDisableTfaWithRecoveryCodeGet**
```swift
    open class func privateDisableTfaWithRecoveryCodeGet(password: String, code: String, completion: @escaping (_ data: Any?, _ error: Error?) -> Void)
```

Disables TFA with one time recovery code

### Example 
```swift
// The following code samples are still beta. For any issue, please report via http://github.com/OpenAPITools/openapi-generator/issues/new
import OpenAPIClient

let password = "password_example" // String | The password for the subaccount
let code = "code_example" // String | One time recovery code

// Disables TFA with one time recovery code
InternalAPI.privateDisableTfaWithRecoveryCodeGet(password: password, code: code) { (response, error) in
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
 **password** | **String** | The password for the subaccount | 
 **code** | **String** | One time recovery code | 

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
InternalAPI.privateGetAddressBookGet(currency: currency, type: type) { (response, error) in
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
InternalAPI.privateRemoveFromAddressBookGet(currency: currency, type: type, address: address, tfa: tfa) { (response, error) in
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
InternalAPI.privateSubmitTransferToSubaccountGet(currency: currency, amount: amount, destination: destination) { (response, error) in
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
InternalAPI.privateSubmitTransferToUserGet(currency: currency, amount: amount, destination: destination, tfa: tfa) { (response, error) in
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
InternalAPI.privateToggleDepositAddressCreationGet(currency: currency, state: state) { (response, error) in
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

# **publicGetFooterGet**
```swift
    open class func publicGetFooterGet(completion: @escaping (_ data: Any?, _ error: Error?) -> Void)
```

Get information to be displayed in the footer of the website.

### Example 
```swift
// The following code samples are still beta. For any issue, please report via http://github.com/OpenAPITools/openapi-generator/issues/new
import OpenAPIClient


// Get information to be displayed in the footer of the website.
InternalAPI.publicGetFooterGet() { (response, error) in
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
This endpoint does not need any parameter.

### Return type

**Any**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **publicGetOptionMarkPricesGet**
```swift
    open class func publicGetOptionMarkPricesGet(currency: Currency_publicGetOptionMarkPricesGet, completion: @escaping (_ data: Any?, _ error: Error?) -> Void)
```

Retrives market prices and its implied volatility of options instruments

### Example 
```swift
// The following code samples are still beta. For any issue, please report via http://github.com/OpenAPITools/openapi-generator/issues/new
import OpenAPIClient

let currency = "currency_example" // String | The currency symbol

// Retrives market prices and its implied volatility of options instruments
InternalAPI.publicGetOptionMarkPricesGet(currency: currency) { (response, error) in
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

# **publicValidateFieldGet**
```swift
    open class func publicValidateFieldGet(field: String, value: String, value2: String? = nil, completion: @escaping (_ data: Any?, _ error: Error?) -> Void)
```

Method used to introduce the client software connected to Deribit platform over websocket. Provided data may have an impact on the maintained connection and will be collected for internal statistical purposes. In response, Deribit will also introduce itself.

### Example 
```swift
// The following code samples are still beta. For any issue, please report via http://github.com/OpenAPITools/openapi-generator/issues/new
import OpenAPIClient

let field = "field_example" // String | Name of the field to be validated, examples: postal_code, date_of_birth
let value = "value_example" // String | Value to be checked
let value2 = "value2_example" // String | Additional value to be compared with (optional)

// Method used to introduce the client software connected to Deribit platform over websocket. Provided data may have an impact on the maintained connection and will be collected for internal statistical purposes. In response, Deribit will also introduce itself.
InternalAPI.publicValidateFieldGet(field: field, value: value, value2: value2) { (response, error) in
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
 **field** | **String** | Name of the field to be validated, examples: postal_code, date_of_birth | 
 **value** | **String** | Value to be checked | 
 **value2** | **String** | Additional value to be compared with | [optional] 

### Return type

**Any**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

