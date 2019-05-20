# AccountManagementApi

All URIs are relative to *https://www.deribit.com/api/v2*

Method | HTTP request | Description
------------- | ------------- | -------------
[**privateChangeSubaccountNameGet**](AccountManagementApi.md#privateChangeSubaccountNameGet) | **GET** /private/change_subaccount_name | Change the user name for a subaccount
[**privateCreateSubaccountGet**](AccountManagementApi.md#privateCreateSubaccountGet) | **GET** /private/create_subaccount | Create a new subaccount
[**privateDisableTfaForSubaccountGet**](AccountManagementApi.md#privateDisableTfaForSubaccountGet) | **GET** /private/disable_tfa_for_subaccount | Disable two factor authentication for a subaccount.
[**privateGetAccountSummaryGet**](AccountManagementApi.md#privateGetAccountSummaryGet) | **GET** /private/get_account_summary | Retrieves user account summary.
[**privateGetEmailLanguageGet**](AccountManagementApi.md#privateGetEmailLanguageGet) | **GET** /private/get_email_language | Retrieves the language to be used for emails.
[**privateGetNewAnnouncementsGet**](AccountManagementApi.md#privateGetNewAnnouncementsGet) | **GET** /private/get_new_announcements | Retrieves announcements that have not been marked read by the user.
[**privateGetPositionGet**](AccountManagementApi.md#privateGetPositionGet) | **GET** /private/get_position | Retrieve user position.
[**privateGetPositionsGet**](AccountManagementApi.md#privateGetPositionsGet) | **GET** /private/get_positions | Retrieve user positions.
[**privateGetSubaccountsGet**](AccountManagementApi.md#privateGetSubaccountsGet) | **GET** /private/get_subaccounts | Get information about subaccounts
[**privateSetAnnouncementAsReadGet**](AccountManagementApi.md#privateSetAnnouncementAsReadGet) | **GET** /private/set_announcement_as_read | Marks an announcement as read, so it will not be shown in &#x60;get_new_announcements&#x60;.
[**privateSetEmailForSubaccountGet**](AccountManagementApi.md#privateSetEmailForSubaccountGet) | **GET** /private/set_email_for_subaccount | Assign an email address to a subaccount. User will receive an email with confirmation link.
[**privateSetEmailLanguageGet**](AccountManagementApi.md#privateSetEmailLanguageGet) | **GET** /private/set_email_language | Changes the language to be used for emails.
[**privateSetPasswordForSubaccountGet**](AccountManagementApi.md#privateSetPasswordForSubaccountGet) | **GET** /private/set_password_for_subaccount | Set the password for the subaccount
[**privateToggleNotificationsFromSubaccountGet**](AccountManagementApi.md#privateToggleNotificationsFromSubaccountGet) | **GET** /private/toggle_notifications_from_subaccount | Enable or disable sending of notifications for the subaccount.
[**privateToggleSubaccountLoginGet**](AccountManagementApi.md#privateToggleSubaccountLoginGet) | **GET** /private/toggle_subaccount_login | Enable or disable login for a subaccount. If login is disabled and a session for the subaccount exists, this session will be terminated.
[**publicGetAnnouncementsGet**](AccountManagementApi.md#publicGetAnnouncementsGet) | **GET** /public/get_announcements | Retrieves announcements from the last 30 days.


<a name="privateChangeSubaccountNameGet"></a>
# **privateChangeSubaccountNameGet**
> kotlin.Any privateChangeSubaccountNameGet(sid, name)

Change the user name for a subaccount

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = AccountManagementApi()
val sid : kotlin.Int = 56 // kotlin.Int | The user id for the subaccount
val name : kotlin.String = newUserName // kotlin.String | The new user name
try {
    val result : kotlin.Any = apiInstance.privateChangeSubaccountNameGet(sid, name)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling AccountManagementApi#privateChangeSubaccountNameGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling AccountManagementApi#privateChangeSubaccountNameGet")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **sid** | **kotlin.Int**| The user id for the subaccount |
 **name** | **kotlin.String**| The new user name |

### Return type

[**kotlin.Any**](kotlin.Any.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="privateCreateSubaccountGet"></a>
# **privateCreateSubaccountGet**
> kotlin.Any privateCreateSubaccountGet()

Create a new subaccount

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = AccountManagementApi()
try {
    val result : kotlin.Any = apiInstance.privateCreateSubaccountGet()
    println(result)
} catch (e: ClientException) {
    println("4xx response calling AccountManagementApi#privateCreateSubaccountGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling AccountManagementApi#privateCreateSubaccountGet")
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

<a name="privateDisableTfaForSubaccountGet"></a>
# **privateDisableTfaForSubaccountGet**
> kotlin.Any privateDisableTfaForSubaccountGet(sid)

Disable two factor authentication for a subaccount.

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = AccountManagementApi()
val sid : kotlin.Int = 56 // kotlin.Int | The user id for the subaccount
try {
    val result : kotlin.Any = apiInstance.privateDisableTfaForSubaccountGet(sid)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling AccountManagementApi#privateDisableTfaForSubaccountGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling AccountManagementApi#privateDisableTfaForSubaccountGet")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **sid** | **kotlin.Int**| The user id for the subaccount |

### Return type

[**kotlin.Any**](kotlin.Any.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="privateGetAccountSummaryGet"></a>
# **privateGetAccountSummaryGet**
> kotlin.Any privateGetAccountSummaryGet(currency, extended)

Retrieves user account summary.

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = AccountManagementApi()
val currency : kotlin.String = currency_example // kotlin.String | The currency symbol
val extended : kotlin.Boolean = false // kotlin.Boolean | Include additional fields
try {
    val result : kotlin.Any = apiInstance.privateGetAccountSummaryGet(currency, extended)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling AccountManagementApi#privateGetAccountSummaryGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling AccountManagementApi#privateGetAccountSummaryGet")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **kotlin.String**| The currency symbol | [enum: BTC, ETH]
 **extended** | **kotlin.Boolean**| Include additional fields | [optional]

### Return type

[**kotlin.Any**](kotlin.Any.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="privateGetEmailLanguageGet"></a>
# **privateGetEmailLanguageGet**
> kotlin.Any privateGetEmailLanguageGet()

Retrieves the language to be used for emails.

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = AccountManagementApi()
try {
    val result : kotlin.Any = apiInstance.privateGetEmailLanguageGet()
    println(result)
} catch (e: ClientException) {
    println("4xx response calling AccountManagementApi#privateGetEmailLanguageGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling AccountManagementApi#privateGetEmailLanguageGet")
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

<a name="privateGetNewAnnouncementsGet"></a>
# **privateGetNewAnnouncementsGet**
> kotlin.Any privateGetNewAnnouncementsGet()

Retrieves announcements that have not been marked read by the user.

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = AccountManagementApi()
try {
    val result : kotlin.Any = apiInstance.privateGetNewAnnouncementsGet()
    println(result)
} catch (e: ClientException) {
    println("4xx response calling AccountManagementApi#privateGetNewAnnouncementsGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling AccountManagementApi#privateGetNewAnnouncementsGet")
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

<a name="privateGetPositionGet"></a>
# **privateGetPositionGet**
> kotlin.Any privateGetPositionGet(instrumentName)

Retrieve user position.

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = AccountManagementApi()
val instrumentName : kotlin.String = BTC-PERPETUAL // kotlin.String | Instrument name
try {
    val result : kotlin.Any = apiInstance.privateGetPositionGet(instrumentName)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling AccountManagementApi#privateGetPositionGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling AccountManagementApi#privateGetPositionGet")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrumentName** | **kotlin.String**| Instrument name |

### Return type

[**kotlin.Any**](kotlin.Any.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="privateGetPositionsGet"></a>
# **privateGetPositionsGet**
> kotlin.Any privateGetPositionsGet(currency, kind)

Retrieve user positions.

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = AccountManagementApi()
val currency : kotlin.String = currency_example // kotlin.String | 
val kind : kotlin.String = kind_example // kotlin.String | Kind filter on positions
try {
    val result : kotlin.Any = apiInstance.privateGetPositionsGet(currency, kind)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling AccountManagementApi#privateGetPositionsGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling AccountManagementApi#privateGetPositionsGet")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **kotlin.String**|  | [enum: BTC, ETH]
 **kind** | **kotlin.String**| Kind filter on positions | [optional] [enum: future, option]

### Return type

[**kotlin.Any**](kotlin.Any.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="privateGetSubaccountsGet"></a>
# **privateGetSubaccountsGet**
> kotlin.Any privateGetSubaccountsGet(withPortfolio)

Get information about subaccounts

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = AccountManagementApi()
val withPortfolio : kotlin.Boolean = true // kotlin.Boolean | 
try {
    val result : kotlin.Any = apiInstance.privateGetSubaccountsGet(withPortfolio)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling AccountManagementApi#privateGetSubaccountsGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling AccountManagementApi#privateGetSubaccountsGet")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **withPortfolio** | **kotlin.Boolean**|  | [optional]

### Return type

[**kotlin.Any**](kotlin.Any.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="privateSetAnnouncementAsReadGet"></a>
# **privateSetAnnouncementAsReadGet**
> kotlin.Any privateSetAnnouncementAsReadGet(announcementId)

Marks an announcement as read, so it will not be shown in &#x60;get_new_announcements&#x60;.

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = AccountManagementApi()
val announcementId : java.math.BigDecimal = 8.14 // java.math.BigDecimal | the ID of the announcement
try {
    val result : kotlin.Any = apiInstance.privateSetAnnouncementAsReadGet(announcementId)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling AccountManagementApi#privateSetAnnouncementAsReadGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling AccountManagementApi#privateSetAnnouncementAsReadGet")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **announcementId** | **java.math.BigDecimal**| the ID of the announcement |

### Return type

[**kotlin.Any**](kotlin.Any.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="privateSetEmailForSubaccountGet"></a>
# **privateSetEmailForSubaccountGet**
> kotlin.Any privateSetEmailForSubaccountGet(sid, email)

Assign an email address to a subaccount. User will receive an email with confirmation link.

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = AccountManagementApi()
val sid : kotlin.Int = 56 // kotlin.Int | The user id for the subaccount
val email : kotlin.String = subaccount_1@email.com // kotlin.String | The email address for the subaccount
try {
    val result : kotlin.Any = apiInstance.privateSetEmailForSubaccountGet(sid, email)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling AccountManagementApi#privateSetEmailForSubaccountGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling AccountManagementApi#privateSetEmailForSubaccountGet")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **sid** | **kotlin.Int**| The user id for the subaccount |
 **email** | **kotlin.String**| The email address for the subaccount |

### Return type

[**kotlin.Any**](kotlin.Any.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="privateSetEmailLanguageGet"></a>
# **privateSetEmailLanguageGet**
> kotlin.Any privateSetEmailLanguageGet(language)

Changes the language to be used for emails.

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = AccountManagementApi()
val language : kotlin.String = en // kotlin.String | The abbreviated language name. Valid values include `\"en\"`, `\"ko\"`, `\"zh\"`
try {
    val result : kotlin.Any = apiInstance.privateSetEmailLanguageGet(language)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling AccountManagementApi#privateSetEmailLanguageGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling AccountManagementApi#privateSetEmailLanguageGet")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **language** | **kotlin.String**| The abbreviated language name. Valid values include &#x60;\&quot;en\&quot;&#x60;, &#x60;\&quot;ko\&quot;&#x60;, &#x60;\&quot;zh\&quot;&#x60; |

### Return type

[**kotlin.Any**](kotlin.Any.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="privateSetPasswordForSubaccountGet"></a>
# **privateSetPasswordForSubaccountGet**
> kotlin.Any privateSetPasswordForSubaccountGet(sid, password)

Set the password for the subaccount

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = AccountManagementApi()
val sid : kotlin.Int = 56 // kotlin.Int | The user id for the subaccount
val password : kotlin.String = password_example // kotlin.String | The password for the subaccount
try {
    val result : kotlin.Any = apiInstance.privateSetPasswordForSubaccountGet(sid, password)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling AccountManagementApi#privateSetPasswordForSubaccountGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling AccountManagementApi#privateSetPasswordForSubaccountGet")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **sid** | **kotlin.Int**| The user id for the subaccount |
 **password** | **kotlin.String**| The password for the subaccount |

### Return type

[**kotlin.Any**](kotlin.Any.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="privateToggleNotificationsFromSubaccountGet"></a>
# **privateToggleNotificationsFromSubaccountGet**
> kotlin.Any privateToggleNotificationsFromSubaccountGet(sid, state)

Enable or disable sending of notifications for the subaccount.

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = AccountManagementApi()
val sid : kotlin.Int = 56 // kotlin.Int | The user id for the subaccount
val state : kotlin.Boolean = true // kotlin.Boolean | enable (`true`) or disable (`false`) notifications
try {
    val result : kotlin.Any = apiInstance.privateToggleNotificationsFromSubaccountGet(sid, state)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling AccountManagementApi#privateToggleNotificationsFromSubaccountGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling AccountManagementApi#privateToggleNotificationsFromSubaccountGet")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **sid** | **kotlin.Int**| The user id for the subaccount |
 **state** | **kotlin.Boolean**| enable (&#x60;true&#x60;) or disable (&#x60;false&#x60;) notifications |

### Return type

[**kotlin.Any**](kotlin.Any.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="privateToggleSubaccountLoginGet"></a>
# **privateToggleSubaccountLoginGet**
> kotlin.Any privateToggleSubaccountLoginGet(sid, state)

Enable or disable login for a subaccount. If login is disabled and a session for the subaccount exists, this session will be terminated.

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = AccountManagementApi()
val sid : kotlin.Int = 56 // kotlin.Int | The user id for the subaccount
val state : kotlin.String = state_example // kotlin.String | enable or disable login.
try {
    val result : kotlin.Any = apiInstance.privateToggleSubaccountLoginGet(sid, state)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling AccountManagementApi#privateToggleSubaccountLoginGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling AccountManagementApi#privateToggleSubaccountLoginGet")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **sid** | **kotlin.Int**| The user id for the subaccount |
 **state** | **kotlin.String**| enable or disable login. | [enum: enable, disable]

### Return type

[**kotlin.Any**](kotlin.Any.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="publicGetAnnouncementsGet"></a>
# **publicGetAnnouncementsGet**
> kotlin.Any publicGetAnnouncementsGet()

Retrieves announcements from the last 30 days.

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = AccountManagementApi()
try {
    val result : kotlin.Any = apiInstance.publicGetAnnouncementsGet()
    println(result)
} catch (e: ClientException) {
    println("4xx response calling AccountManagementApi#publicGetAnnouncementsGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling AccountManagementApi#publicGetAnnouncementsGet")
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

