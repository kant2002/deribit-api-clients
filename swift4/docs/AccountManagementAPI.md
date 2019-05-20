# AccountManagementAPI

All URIs are relative to *https://www.deribit.com/api/v2*

Method | HTTP request | Description
------------- | ------------- | -------------
[**privateChangeSubaccountNameGet**](AccountManagementAPI.md#privatechangesubaccountnameget) | **GET** /private/change_subaccount_name | Change the user name for a subaccount
[**privateCreateSubaccountGet**](AccountManagementAPI.md#privatecreatesubaccountget) | **GET** /private/create_subaccount | Create a new subaccount
[**privateDisableTfaForSubaccountGet**](AccountManagementAPI.md#privatedisabletfaforsubaccountget) | **GET** /private/disable_tfa_for_subaccount | Disable two factor authentication for a subaccount.
[**privateGetAccountSummaryGet**](AccountManagementAPI.md#privategetaccountsummaryget) | **GET** /private/get_account_summary | Retrieves user account summary.
[**privateGetEmailLanguageGet**](AccountManagementAPI.md#privategetemaillanguageget) | **GET** /private/get_email_language | Retrieves the language to be used for emails.
[**privateGetNewAnnouncementsGet**](AccountManagementAPI.md#privategetnewannouncementsget) | **GET** /private/get_new_announcements | Retrieves announcements that have not been marked read by the user.
[**privateGetPositionGet**](AccountManagementAPI.md#privategetpositionget) | **GET** /private/get_position | Retrieve user position.
[**privateGetPositionsGet**](AccountManagementAPI.md#privategetpositionsget) | **GET** /private/get_positions | Retrieve user positions.
[**privateGetSubaccountsGet**](AccountManagementAPI.md#privategetsubaccountsget) | **GET** /private/get_subaccounts | Get information about subaccounts
[**privateSetAnnouncementAsReadGet**](AccountManagementAPI.md#privatesetannouncementasreadget) | **GET** /private/set_announcement_as_read | Marks an announcement as read, so it will not be shown in &#x60;get_new_announcements&#x60;.
[**privateSetEmailForSubaccountGet**](AccountManagementAPI.md#privatesetemailforsubaccountget) | **GET** /private/set_email_for_subaccount | Assign an email address to a subaccount. User will receive an email with confirmation link.
[**privateSetEmailLanguageGet**](AccountManagementAPI.md#privatesetemaillanguageget) | **GET** /private/set_email_language | Changes the language to be used for emails.
[**privateSetPasswordForSubaccountGet**](AccountManagementAPI.md#privatesetpasswordforsubaccountget) | **GET** /private/set_password_for_subaccount | Set the password for the subaccount
[**privateToggleNotificationsFromSubaccountGet**](AccountManagementAPI.md#privatetogglenotificationsfromsubaccountget) | **GET** /private/toggle_notifications_from_subaccount | Enable or disable sending of notifications for the subaccount.
[**privateToggleSubaccountLoginGet**](AccountManagementAPI.md#privatetogglesubaccountloginget) | **GET** /private/toggle_subaccount_login | Enable or disable login for a subaccount. If login is disabled and a session for the subaccount exists, this session will be terminated.
[**publicGetAnnouncementsGet**](AccountManagementAPI.md#publicgetannouncementsget) | **GET** /public/get_announcements | Retrieves announcements from the last 30 days.


# **privateChangeSubaccountNameGet**
```swift
    open class func privateChangeSubaccountNameGet(sid: Int, name: String, completion: @escaping (_ data: Any?, _ error: Error?) -> Void)
```

Change the user name for a subaccount

### Example 
```swift
// The following code samples are still beta. For any issue, please report via http://github.com/OpenAPITools/openapi-generator/issues/new
import OpenAPIClient

let sid = 987 // Int | The user id for the subaccount
let name = "name_example" // String | The new user name

// Change the user name for a subaccount
AccountManagementAPI.privateChangeSubaccountNameGet(sid: sid, name: name) { (response, error) in
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
 **sid** | **Int** | The user id for the subaccount | 
 **name** | **String** | The new user name | 

### Return type

**Any**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateCreateSubaccountGet**
```swift
    open class func privateCreateSubaccountGet(completion: @escaping (_ data: Any?, _ error: Error?) -> Void)
```

Create a new subaccount

### Example 
```swift
// The following code samples are still beta. For any issue, please report via http://github.com/OpenAPITools/openapi-generator/issues/new
import OpenAPIClient


// Create a new subaccount
AccountManagementAPI.privateCreateSubaccountGet() { (response, error) in
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

# **privateDisableTfaForSubaccountGet**
```swift
    open class func privateDisableTfaForSubaccountGet(sid: Int, completion: @escaping (_ data: Any?, _ error: Error?) -> Void)
```

Disable two factor authentication for a subaccount.

### Example 
```swift
// The following code samples are still beta. For any issue, please report via http://github.com/OpenAPITools/openapi-generator/issues/new
import OpenAPIClient

let sid = 987 // Int | The user id for the subaccount

// Disable two factor authentication for a subaccount.
AccountManagementAPI.privateDisableTfaForSubaccountGet(sid: sid) { (response, error) in
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
 **sid** | **Int** | The user id for the subaccount | 

### Return type

**Any**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateGetAccountSummaryGet**
```swift
    open class func privateGetAccountSummaryGet(currency: Currency_privateGetAccountSummaryGet, extended: Bool? = nil, completion: @escaping (_ data: Any?, _ error: Error?) -> Void)
```

Retrieves user account summary.

### Example 
```swift
// The following code samples are still beta. For any issue, please report via http://github.com/OpenAPITools/openapi-generator/issues/new
import OpenAPIClient

let currency = "currency_example" // String | The currency symbol
let extended = false // Bool | Include additional fields (optional)

// Retrieves user account summary.
AccountManagementAPI.privateGetAccountSummaryGet(currency: currency, extended: extended) { (response, error) in
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
 **extended** | **Bool** | Include additional fields | [optional] 

### Return type

**Any**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateGetEmailLanguageGet**
```swift
    open class func privateGetEmailLanguageGet(completion: @escaping (_ data: Any?, _ error: Error?) -> Void)
```

Retrieves the language to be used for emails.

### Example 
```swift
// The following code samples are still beta. For any issue, please report via http://github.com/OpenAPITools/openapi-generator/issues/new
import OpenAPIClient


// Retrieves the language to be used for emails.
AccountManagementAPI.privateGetEmailLanguageGet() { (response, error) in
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

# **privateGetNewAnnouncementsGet**
```swift
    open class func privateGetNewAnnouncementsGet(completion: @escaping (_ data: Any?, _ error: Error?) -> Void)
```

Retrieves announcements that have not been marked read by the user.

### Example 
```swift
// The following code samples are still beta. For any issue, please report via http://github.com/OpenAPITools/openapi-generator/issues/new
import OpenAPIClient


// Retrieves announcements that have not been marked read by the user.
AccountManagementAPI.privateGetNewAnnouncementsGet() { (response, error) in
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

# **privateGetPositionGet**
```swift
    open class func privateGetPositionGet(instrumentName: String, completion: @escaping (_ data: Any?, _ error: Error?) -> Void)
```

Retrieve user position.

### Example 
```swift
// The following code samples are still beta. For any issue, please report via http://github.com/OpenAPITools/openapi-generator/issues/new
import OpenAPIClient

let instrumentName = "instrumentName_example" // String | Instrument name

// Retrieve user position.
AccountManagementAPI.privateGetPositionGet(instrumentName: instrumentName) { (response, error) in
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
 **instrumentName** | **String** | Instrument name | 

### Return type

**Any**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateGetPositionsGet**
```swift
    open class func privateGetPositionsGet(currency: Currency_privateGetPositionsGet, kind: Kind_privateGetPositionsGet? = nil, completion: @escaping (_ data: Any?, _ error: Error?) -> Void)
```

Retrieve user positions.

### Example 
```swift
// The following code samples are still beta. For any issue, please report via http://github.com/OpenAPITools/openapi-generator/issues/new
import OpenAPIClient

let currency = "currency_example" // String | 
let kind = "kind_example" // String | Kind filter on positions (optional)

// Retrieve user positions.
AccountManagementAPI.privateGetPositionsGet(currency: currency, kind: kind) { (response, error) in
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
 **currency** | **String** |  | 
 **kind** | **String** | Kind filter on positions | [optional] 

### Return type

**Any**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateGetSubaccountsGet**
```swift
    open class func privateGetSubaccountsGet(withPortfolio: Bool? = nil, completion: @escaping (_ data: Any?, _ error: Error?) -> Void)
```

Get information about subaccounts

### Example 
```swift
// The following code samples are still beta. For any issue, please report via http://github.com/OpenAPITools/openapi-generator/issues/new
import OpenAPIClient

let withPortfolio = false // Bool |  (optional)

// Get information about subaccounts
AccountManagementAPI.privateGetSubaccountsGet(withPortfolio: withPortfolio) { (response, error) in
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
 **withPortfolio** | **Bool** |  | [optional] 

### Return type

**Any**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateSetAnnouncementAsReadGet**
```swift
    open class func privateSetAnnouncementAsReadGet(announcementId: Double, completion: @escaping (_ data: Any?, _ error: Error?) -> Void)
```

Marks an announcement as read, so it will not be shown in `get_new_announcements`.

### Example 
```swift
// The following code samples are still beta. For any issue, please report via http://github.com/OpenAPITools/openapi-generator/issues/new
import OpenAPIClient

let announcementId = 987 // Double | the ID of the announcement

// Marks an announcement as read, so it will not be shown in `get_new_announcements`.
AccountManagementAPI.privateSetAnnouncementAsReadGet(announcementId: announcementId) { (response, error) in
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
 **announcementId** | **Double** | the ID of the announcement | 

### Return type

**Any**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateSetEmailForSubaccountGet**
```swift
    open class func privateSetEmailForSubaccountGet(sid: Int, email: String, completion: @escaping (_ data: Any?, _ error: Error?) -> Void)
```

Assign an email address to a subaccount. User will receive an email with confirmation link.

### Example 
```swift
// The following code samples are still beta. For any issue, please report via http://github.com/OpenAPITools/openapi-generator/issues/new
import OpenAPIClient

let sid = 987 // Int | The user id for the subaccount
let email = "email_example" // String | The email address for the subaccount

// Assign an email address to a subaccount. User will receive an email with confirmation link.
AccountManagementAPI.privateSetEmailForSubaccountGet(sid: sid, email: email) { (response, error) in
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
 **sid** | **Int** | The user id for the subaccount | 
 **email** | **String** | The email address for the subaccount | 

### Return type

**Any**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateSetEmailLanguageGet**
```swift
    open class func privateSetEmailLanguageGet(language: String, completion: @escaping (_ data: Any?, _ error: Error?) -> Void)
```

Changes the language to be used for emails.

### Example 
```swift
// The following code samples are still beta. For any issue, please report via http://github.com/OpenAPITools/openapi-generator/issues/new
import OpenAPIClient

let language = "language_example" // String | The abbreviated language name. Valid values include `\"en\"`, `\"ko\"`, `\"zh\"`

// Changes the language to be used for emails.
AccountManagementAPI.privateSetEmailLanguageGet(language: language) { (response, error) in
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
 **language** | **String** | The abbreviated language name. Valid values include &#x60;\&quot;en\&quot;&#x60;, &#x60;\&quot;ko\&quot;&#x60;, &#x60;\&quot;zh\&quot;&#x60; | 

### Return type

**Any**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateSetPasswordForSubaccountGet**
```swift
    open class func privateSetPasswordForSubaccountGet(sid: Int, password: String, completion: @escaping (_ data: Any?, _ error: Error?) -> Void)
```

Set the password for the subaccount

### Example 
```swift
// The following code samples are still beta. For any issue, please report via http://github.com/OpenAPITools/openapi-generator/issues/new
import OpenAPIClient

let sid = 987 // Int | The user id for the subaccount
let password = "password_example" // String | The password for the subaccount

// Set the password for the subaccount
AccountManagementAPI.privateSetPasswordForSubaccountGet(sid: sid, password: password) { (response, error) in
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
 **sid** | **Int** | The user id for the subaccount | 
 **password** | **String** | The password for the subaccount | 

### Return type

**Any**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateToggleNotificationsFromSubaccountGet**
```swift
    open class func privateToggleNotificationsFromSubaccountGet(sid: Int, state: Bool, completion: @escaping (_ data: Any?, _ error: Error?) -> Void)
```

Enable or disable sending of notifications for the subaccount.

### Example 
```swift
// The following code samples are still beta. For any issue, please report via http://github.com/OpenAPITools/openapi-generator/issues/new
import OpenAPIClient

let sid = 987 // Int | The user id for the subaccount
let state = false // Bool | enable (`true`) or disable (`false`) notifications

// Enable or disable sending of notifications for the subaccount.
AccountManagementAPI.privateToggleNotificationsFromSubaccountGet(sid: sid, state: state) { (response, error) in
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
 **sid** | **Int** | The user id for the subaccount | 
 **state** | **Bool** | enable (&#x60;true&#x60;) or disable (&#x60;false&#x60;) notifications | 

### Return type

**Any**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateToggleSubaccountLoginGet**
```swift
    open class func privateToggleSubaccountLoginGet(sid: Int, state: State_privateToggleSubaccountLoginGet, completion: @escaping (_ data: Any?, _ error: Error?) -> Void)
```

Enable or disable login for a subaccount. If login is disabled and a session for the subaccount exists, this session will be terminated.

### Example 
```swift
// The following code samples are still beta. For any issue, please report via http://github.com/OpenAPITools/openapi-generator/issues/new
import OpenAPIClient

let sid = 987 // Int | The user id for the subaccount
let state = "state_example" // String | enable or disable login.

// Enable or disable login for a subaccount. If login is disabled and a session for the subaccount exists, this session will be terminated.
AccountManagementAPI.privateToggleSubaccountLoginGet(sid: sid, state: state) { (response, error) in
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
 **sid** | **Int** | The user id for the subaccount | 
 **state** | **String** | enable or disable login. | 

### Return type

**Any**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **publicGetAnnouncementsGet**
```swift
    open class func publicGetAnnouncementsGet(completion: @escaping (_ data: Any?, _ error: Error?) -> Void)
```

Retrieves announcements from the last 30 days.

### Example 
```swift
// The following code samples are still beta. For any issue, please report via http://github.com/OpenAPITools/openapi-generator/issues/new
import OpenAPIClient


// Retrieves announcements from the last 30 days.
AccountManagementAPI.publicGetAnnouncementsGet() { (response, error) in
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

