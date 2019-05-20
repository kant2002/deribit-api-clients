# AccountManagementApi

All URIs are relative to *https://www.deribit.com/api/v2*

Method | HTTP request | Description
------------- | ------------- | -------------
[**PrivateChangeSubaccountNameGet**](AccountManagementApi.md#PrivateChangeSubaccountNameGet) | **GET** /private/change_subaccount_name | Change the user name for a subaccount
[**PrivateCreateSubaccountGet**](AccountManagementApi.md#PrivateCreateSubaccountGet) | **GET** /private/create_subaccount | Create a new subaccount
[**PrivateDisableTfaForSubaccountGet**](AccountManagementApi.md#PrivateDisableTfaForSubaccountGet) | **GET** /private/disable_tfa_for_subaccount | Disable two factor authentication for a subaccount.
[**PrivateGetAccountSummaryGet**](AccountManagementApi.md#PrivateGetAccountSummaryGet) | **GET** /private/get_account_summary | Retrieves user account summary.
[**PrivateGetEmailLanguageGet**](AccountManagementApi.md#PrivateGetEmailLanguageGet) | **GET** /private/get_email_language | Retrieves the language to be used for emails.
[**PrivateGetNewAnnouncementsGet**](AccountManagementApi.md#PrivateGetNewAnnouncementsGet) | **GET** /private/get_new_announcements | Retrieves announcements that have not been marked read by the user.
[**PrivateGetPositionGet**](AccountManagementApi.md#PrivateGetPositionGet) | **GET** /private/get_position | Retrieve user position.
[**PrivateGetPositionsGet**](AccountManagementApi.md#PrivateGetPositionsGet) | **GET** /private/get_positions | Retrieve user positions.
[**PrivateGetSubaccountsGet**](AccountManagementApi.md#PrivateGetSubaccountsGet) | **GET** /private/get_subaccounts | Get information about subaccounts
[**PrivateSetAnnouncementAsReadGet**](AccountManagementApi.md#PrivateSetAnnouncementAsReadGet) | **GET** /private/set_announcement_as_read | Marks an announcement as read, so it will not be shown in &#x60;get_new_announcements&#x60;.
[**PrivateSetEmailForSubaccountGet**](AccountManagementApi.md#PrivateSetEmailForSubaccountGet) | **GET** /private/set_email_for_subaccount | Assign an email address to a subaccount. User will receive an email with confirmation link.
[**PrivateSetEmailLanguageGet**](AccountManagementApi.md#PrivateSetEmailLanguageGet) | **GET** /private/set_email_language | Changes the language to be used for emails.
[**PrivateSetPasswordForSubaccountGet**](AccountManagementApi.md#PrivateSetPasswordForSubaccountGet) | **GET** /private/set_password_for_subaccount | Set the password for the subaccount
[**PrivateToggleNotificationsFromSubaccountGet**](AccountManagementApi.md#PrivateToggleNotificationsFromSubaccountGet) | **GET** /private/toggle_notifications_from_subaccount | Enable or disable sending of notifications for the subaccount.
[**PrivateToggleSubaccountLoginGet**](AccountManagementApi.md#PrivateToggleSubaccountLoginGet) | **GET** /private/toggle_subaccount_login | Enable or disable login for a subaccount. If login is disabled and a session for the subaccount exists, this session will be terminated.
[**PublicGetAnnouncementsGet**](AccountManagementApi.md#PublicGetAnnouncementsGet) | **GET** /public/get_announcements | Retrieves announcements from the last 30 days.


# **PrivateChangeSubaccountNameGet**
> object PrivateChangeSubaccountNameGet(sid, name)

Change the user name for a subaccount

### Example
```R
library(openapi)

var.sid <- 56 # integer | The user id for the subaccount
var.name <- 'newUserName' # character | The new user name

#Change the user name for a subaccount
api.instance <- AccountManagementApi$new()
# Configure HTTP basic authorization: bearerAuth
api.instance$apiClient$username <- 'TODO_YOUR_USERNAME';
api.instance$apiClient$password <- 'TODO_YOUR_PASSWORD';
result <- api.instance$PrivateChangeSubaccountNameGet(var.sid, var.name)
dput(result)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **sid** | **integer**| The user id for the subaccount | 
 **name** | **character**| The new user name | 

### Return type

**object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json



# **PrivateCreateSubaccountGet**
> object PrivateCreateSubaccountGet()

Create a new subaccount

### Example
```R
library(openapi)


#Create a new subaccount
api.instance <- AccountManagementApi$new()
# Configure HTTP basic authorization: bearerAuth
api.instance$apiClient$username <- 'TODO_YOUR_USERNAME';
api.instance$apiClient$password <- 'TODO_YOUR_PASSWORD';
result <- api.instance$PrivateCreateSubaccountGet()
dput(result)
```

### Parameters
This endpoint does not need any parameter.

### Return type

**object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json



# **PrivateDisableTfaForSubaccountGet**
> object PrivateDisableTfaForSubaccountGet(sid)

Disable two factor authentication for a subaccount.

### Example
```R
library(openapi)

var.sid <- 56 # integer | The user id for the subaccount

#Disable two factor authentication for a subaccount.
api.instance <- AccountManagementApi$new()
# Configure HTTP basic authorization: bearerAuth
api.instance$apiClient$username <- 'TODO_YOUR_USERNAME';
api.instance$apiClient$password <- 'TODO_YOUR_PASSWORD';
result <- api.instance$PrivateDisableTfaForSubaccountGet(var.sid)
dput(result)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **sid** | **integer**| The user id for the subaccount | 

### Return type

**object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json



# **PrivateGetAccountSummaryGet**
> object PrivateGetAccountSummaryGet(currency, extended=var.extended)

Retrieves user account summary.

### Example
```R
library(openapi)

var.currency <- 'currency_example' # character | The currency symbol
var.extended <- 'false' # character | Include additional fields

#Retrieves user account summary.
api.instance <- AccountManagementApi$new()
# Configure HTTP basic authorization: bearerAuth
api.instance$apiClient$username <- 'TODO_YOUR_USERNAME';
api.instance$apiClient$password <- 'TODO_YOUR_PASSWORD';
result <- api.instance$PrivateGetAccountSummaryGet(var.currency, extended=var.extended)
dput(result)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **character**| The currency symbol | 
 **extended** | **character**| Include additional fields | [optional] 

### Return type

**object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json



# **PrivateGetEmailLanguageGet**
> object PrivateGetEmailLanguageGet()

Retrieves the language to be used for emails.

### Example
```R
library(openapi)


#Retrieves the language to be used for emails.
api.instance <- AccountManagementApi$new()
# Configure HTTP basic authorization: bearerAuth
api.instance$apiClient$username <- 'TODO_YOUR_USERNAME';
api.instance$apiClient$password <- 'TODO_YOUR_PASSWORD';
result <- api.instance$PrivateGetEmailLanguageGet()
dput(result)
```

### Parameters
This endpoint does not need any parameter.

### Return type

**object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json



# **PrivateGetNewAnnouncementsGet**
> object PrivateGetNewAnnouncementsGet()

Retrieves announcements that have not been marked read by the user.

### Example
```R
library(openapi)


#Retrieves announcements that have not been marked read by the user.
api.instance <- AccountManagementApi$new()
# Configure HTTP basic authorization: bearerAuth
api.instance$apiClient$username <- 'TODO_YOUR_USERNAME';
api.instance$apiClient$password <- 'TODO_YOUR_PASSWORD';
result <- api.instance$PrivateGetNewAnnouncementsGet()
dput(result)
```

### Parameters
This endpoint does not need any parameter.

### Return type

**object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json



# **PrivateGetPositionGet**
> object PrivateGetPositionGet(instrument.name)

Retrieve user position.

### Example
```R
library(openapi)

var.instrument.name <- 'BTC-PERPETUAL' # character | Instrument name

#Retrieve user position.
api.instance <- AccountManagementApi$new()
# Configure HTTP basic authorization: bearerAuth
api.instance$apiClient$username <- 'TODO_YOUR_USERNAME';
api.instance$apiClient$password <- 'TODO_YOUR_PASSWORD';
result <- api.instance$PrivateGetPositionGet(var.instrument.name)
dput(result)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrument.name** | **character**| Instrument name | 

### Return type

**object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json



# **PrivateGetPositionsGet**
> object PrivateGetPositionsGet(currency, kind=var.kind)

Retrieve user positions.

### Example
```R
library(openapi)

var.currency <- 'currency_example' # character | 
var.kind <- 'kind_example' # character | Kind filter on positions

#Retrieve user positions.
api.instance <- AccountManagementApi$new()
# Configure HTTP basic authorization: bearerAuth
api.instance$apiClient$username <- 'TODO_YOUR_USERNAME';
api.instance$apiClient$password <- 'TODO_YOUR_PASSWORD';
result <- api.instance$PrivateGetPositionsGet(var.currency, kind=var.kind)
dput(result)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **character**|  | 
 **kind** | **character**| Kind filter on positions | [optional] 

### Return type

**object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json



# **PrivateGetSubaccountsGet**
> object PrivateGetSubaccountsGet(with.portfolio=var.with.portfolio)

Get information about subaccounts

### Example
```R
library(openapi)

var.with.portfolio <- 'with.portfolio_example' # character | 

#Get information about subaccounts
api.instance <- AccountManagementApi$new()
# Configure HTTP basic authorization: bearerAuth
api.instance$apiClient$username <- 'TODO_YOUR_USERNAME';
api.instance$apiClient$password <- 'TODO_YOUR_PASSWORD';
result <- api.instance$PrivateGetSubaccountsGet(with.portfolio=var.with.portfolio)
dput(result)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **with.portfolio** | **character**|  | [optional] 

### Return type

**object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json



# **PrivateSetAnnouncementAsReadGet**
> object PrivateSetAnnouncementAsReadGet(announcement.id)

Marks an announcement as read, so it will not be shown in `get_new_announcements`.

### Example
```R
library(openapi)

var.announcement.id <- 3.4 # numeric | the ID of the announcement

#Marks an announcement as read, so it will not be shown in `get_new_announcements`.
api.instance <- AccountManagementApi$new()
# Configure HTTP basic authorization: bearerAuth
api.instance$apiClient$username <- 'TODO_YOUR_USERNAME';
api.instance$apiClient$password <- 'TODO_YOUR_PASSWORD';
result <- api.instance$PrivateSetAnnouncementAsReadGet(var.announcement.id)
dput(result)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **announcement.id** | **numeric**| the ID of the announcement | 

### Return type

**object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json



# **PrivateSetEmailForSubaccountGet**
> object PrivateSetEmailForSubaccountGet(sid, email)

Assign an email address to a subaccount. User will receive an email with confirmation link.

### Example
```R
library(openapi)

var.sid <- 56 # integer | The user id for the subaccount
var.email <- 'subaccount_1@email.com' # character | The email address for the subaccount

#Assign an email address to a subaccount. User will receive an email with confirmation link.
api.instance <- AccountManagementApi$new()
# Configure HTTP basic authorization: bearerAuth
api.instance$apiClient$username <- 'TODO_YOUR_USERNAME';
api.instance$apiClient$password <- 'TODO_YOUR_PASSWORD';
result <- api.instance$PrivateSetEmailForSubaccountGet(var.sid, var.email)
dput(result)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **sid** | **integer**| The user id for the subaccount | 
 **email** | **character**| The email address for the subaccount | 

### Return type

**object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json



# **PrivateSetEmailLanguageGet**
> object PrivateSetEmailLanguageGet(language)

Changes the language to be used for emails.

### Example
```R
library(openapi)

var.language <- 'en' # character | The abbreviated language name. Valid values include `\"en\"`, `\"ko\"`, `\"zh\"`

#Changes the language to be used for emails.
api.instance <- AccountManagementApi$new()
# Configure HTTP basic authorization: bearerAuth
api.instance$apiClient$username <- 'TODO_YOUR_USERNAME';
api.instance$apiClient$password <- 'TODO_YOUR_PASSWORD';
result <- api.instance$PrivateSetEmailLanguageGet(var.language)
dput(result)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **language** | **character**| The abbreviated language name. Valid values include &#x60;\&quot;en\&quot;&#x60;, &#x60;\&quot;ko\&quot;&#x60;, &#x60;\&quot;zh\&quot;&#x60; | 

### Return type

**object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json



# **PrivateSetPasswordForSubaccountGet**
> object PrivateSetPasswordForSubaccountGet(sid, password)

Set the password for the subaccount

### Example
```R
library(openapi)

var.sid <- 56 # integer | The user id for the subaccount
var.password <- 'password_example' # character | The password for the subaccount

#Set the password for the subaccount
api.instance <- AccountManagementApi$new()
# Configure HTTP basic authorization: bearerAuth
api.instance$apiClient$username <- 'TODO_YOUR_USERNAME';
api.instance$apiClient$password <- 'TODO_YOUR_PASSWORD';
result <- api.instance$PrivateSetPasswordForSubaccountGet(var.sid, var.password)
dput(result)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **sid** | **integer**| The user id for the subaccount | 
 **password** | **character**| The password for the subaccount | 

### Return type

**object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json



# **PrivateToggleNotificationsFromSubaccountGet**
> object PrivateToggleNotificationsFromSubaccountGet(sid, state)

Enable or disable sending of notifications for the subaccount.

### Example
```R
library(openapi)

var.sid <- 56 # integer | The user id for the subaccount
var.state <- 'state_example' # character | enable (`true`) or disable (`false`) notifications

#Enable or disable sending of notifications for the subaccount.
api.instance <- AccountManagementApi$new()
# Configure HTTP basic authorization: bearerAuth
api.instance$apiClient$username <- 'TODO_YOUR_USERNAME';
api.instance$apiClient$password <- 'TODO_YOUR_PASSWORD';
result <- api.instance$PrivateToggleNotificationsFromSubaccountGet(var.sid, var.state)
dput(result)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **sid** | **integer**| The user id for the subaccount | 
 **state** | **character**| enable (&#x60;true&#x60;) or disable (&#x60;false&#x60;) notifications | 

### Return type

**object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json



# **PrivateToggleSubaccountLoginGet**
> object PrivateToggleSubaccountLoginGet(sid, state)

Enable or disable login for a subaccount. If login is disabled and a session for the subaccount exists, this session will be terminated.

### Example
```R
library(openapi)

var.sid <- 56 # integer | The user id for the subaccount
var.state <- 'state_example' # character | enable or disable login.

#Enable or disable login for a subaccount. If login is disabled and a session for the subaccount exists, this session will be terminated.
api.instance <- AccountManagementApi$new()
# Configure HTTP basic authorization: bearerAuth
api.instance$apiClient$username <- 'TODO_YOUR_USERNAME';
api.instance$apiClient$password <- 'TODO_YOUR_PASSWORD';
result <- api.instance$PrivateToggleSubaccountLoginGet(var.sid, var.state)
dput(result)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **sid** | **integer**| The user id for the subaccount | 
 **state** | **character**| enable or disable login. | 

### Return type

**object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json



# **PublicGetAnnouncementsGet**
> object PublicGetAnnouncementsGet()

Retrieves announcements from the last 30 days.

### Example
```R
library(openapi)


#Retrieves announcements from the last 30 days.
api.instance <- AccountManagementApi$new()
# Configure HTTP basic authorization: bearerAuth
api.instance$apiClient$username <- 'TODO_YOUR_USERNAME';
api.instance$apiClient$password <- 'TODO_YOUR_PASSWORD';
result <- api.instance$PublicGetAnnouncementsGet()
dput(result)
```

### Parameters
This endpoint does not need any parameter.

### Return type

**object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json



