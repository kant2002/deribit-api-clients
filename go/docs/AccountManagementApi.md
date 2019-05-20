# \AccountManagementApi

All URIs are relative to *https://www.deribit.com/api/v2*

Method | HTTP request | Description
------------- | ------------- | -------------
[**PrivateChangeSubaccountNameGet**](AccountManagementApi.md#PrivateChangeSubaccountNameGet) | **Get** /private/change_subaccount_name | Change the user name for a subaccount
[**PrivateCreateSubaccountGet**](AccountManagementApi.md#PrivateCreateSubaccountGet) | **Get** /private/create_subaccount | Create a new subaccount
[**PrivateDisableTfaForSubaccountGet**](AccountManagementApi.md#PrivateDisableTfaForSubaccountGet) | **Get** /private/disable_tfa_for_subaccount | Disable two factor authentication for a subaccount.
[**PrivateGetAccountSummaryGet**](AccountManagementApi.md#PrivateGetAccountSummaryGet) | **Get** /private/get_account_summary | Retrieves user account summary.
[**PrivateGetEmailLanguageGet**](AccountManagementApi.md#PrivateGetEmailLanguageGet) | **Get** /private/get_email_language | Retrieves the language to be used for emails.
[**PrivateGetNewAnnouncementsGet**](AccountManagementApi.md#PrivateGetNewAnnouncementsGet) | **Get** /private/get_new_announcements | Retrieves announcements that have not been marked read by the user.
[**PrivateGetPositionGet**](AccountManagementApi.md#PrivateGetPositionGet) | **Get** /private/get_position | Retrieve user position.
[**PrivateGetPositionsGet**](AccountManagementApi.md#PrivateGetPositionsGet) | **Get** /private/get_positions | Retrieve user positions.
[**PrivateGetSubaccountsGet**](AccountManagementApi.md#PrivateGetSubaccountsGet) | **Get** /private/get_subaccounts | Get information about subaccounts
[**PrivateSetAnnouncementAsReadGet**](AccountManagementApi.md#PrivateSetAnnouncementAsReadGet) | **Get** /private/set_announcement_as_read | Marks an announcement as read, so it will not be shown in &#x60;get_new_announcements&#x60;.
[**PrivateSetEmailForSubaccountGet**](AccountManagementApi.md#PrivateSetEmailForSubaccountGet) | **Get** /private/set_email_for_subaccount | Assign an email address to a subaccount. User will receive an email with confirmation link.
[**PrivateSetEmailLanguageGet**](AccountManagementApi.md#PrivateSetEmailLanguageGet) | **Get** /private/set_email_language | Changes the language to be used for emails.
[**PrivateSetPasswordForSubaccountGet**](AccountManagementApi.md#PrivateSetPasswordForSubaccountGet) | **Get** /private/set_password_for_subaccount | Set the password for the subaccount
[**PrivateToggleNotificationsFromSubaccountGet**](AccountManagementApi.md#PrivateToggleNotificationsFromSubaccountGet) | **Get** /private/toggle_notifications_from_subaccount | Enable or disable sending of notifications for the subaccount.
[**PrivateToggleSubaccountLoginGet**](AccountManagementApi.md#PrivateToggleSubaccountLoginGet) | **Get** /private/toggle_subaccount_login | Enable or disable login for a subaccount. If login is disabled and a session for the subaccount exists, this session will be terminated.
[**PublicGetAnnouncementsGet**](AccountManagementApi.md#PublicGetAnnouncementsGet) | **Get** /public/get_announcements | Retrieves announcements from the last 30 days.



## PrivateChangeSubaccountNameGet

> map[string]interface{} PrivateChangeSubaccountNameGet(ctx, sid, name)
Change the user name for a subaccount

### Required Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
**ctx** | **context.Context** | context for authentication, logging, cancellation, deadlines, tracing, etc.
**sid** | **int32**| The user id for the subaccount | 
**name** | **string**| The new user name | 

### Return type

[**map[string]interface{}**](map[string]interface{}.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateCreateSubaccountGet

> map[string]interface{} PrivateCreateSubaccountGet(ctx, )
Create a new subaccount

### Required Parameters

This endpoint does not need any parameter.

### Return type

[**map[string]interface{}**](map[string]interface{}.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateDisableTfaForSubaccountGet

> map[string]interface{} PrivateDisableTfaForSubaccountGet(ctx, sid)
Disable two factor authentication for a subaccount.

### Required Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
**ctx** | **context.Context** | context for authentication, logging, cancellation, deadlines, tracing, etc.
**sid** | **int32**| The user id for the subaccount | 

### Return type

[**map[string]interface{}**](map[string]interface{}.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateGetAccountSummaryGet

> map[string]interface{} PrivateGetAccountSummaryGet(ctx, currency, optional)
Retrieves user account summary.

### Required Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
**ctx** | **context.Context** | context for authentication, logging, cancellation, deadlines, tracing, etc.
**currency** | **string**| The currency symbol | 
 **optional** | ***PrivateGetAccountSummaryGetOpts** | optional parameters | nil if no parameters

### Optional Parameters

Optional parameters are passed through a pointer to a PrivateGetAccountSummaryGetOpts struct


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------

 **extended** | **optional.Bool**| Include additional fields | 

### Return type

[**map[string]interface{}**](map[string]interface{}.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateGetEmailLanguageGet

> map[string]interface{} PrivateGetEmailLanguageGet(ctx, )
Retrieves the language to be used for emails.

### Required Parameters

This endpoint does not need any parameter.

### Return type

[**map[string]interface{}**](map[string]interface{}.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateGetNewAnnouncementsGet

> map[string]interface{} PrivateGetNewAnnouncementsGet(ctx, )
Retrieves announcements that have not been marked read by the user.

### Required Parameters

This endpoint does not need any parameter.

### Return type

[**map[string]interface{}**](map[string]interface{}.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateGetPositionGet

> map[string]interface{} PrivateGetPositionGet(ctx, instrumentName)
Retrieve user position.

### Required Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
**ctx** | **context.Context** | context for authentication, logging, cancellation, deadlines, tracing, etc.
**instrumentName** | **string**| Instrument name | 

### Return type

[**map[string]interface{}**](map[string]interface{}.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateGetPositionsGet

> map[string]interface{} PrivateGetPositionsGet(ctx, currency, optional)
Retrieve user positions.

### Required Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
**ctx** | **context.Context** | context for authentication, logging, cancellation, deadlines, tracing, etc.
**currency** | **string**|  | 
 **optional** | ***PrivateGetPositionsGetOpts** | optional parameters | nil if no parameters

### Optional Parameters

Optional parameters are passed through a pointer to a PrivateGetPositionsGetOpts struct


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------

 **kind** | **optional.String**| Kind filter on positions | 

### Return type

[**map[string]interface{}**](map[string]interface{}.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateGetSubaccountsGet

> map[string]interface{} PrivateGetSubaccountsGet(ctx, optional)
Get information about subaccounts

### Required Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
**ctx** | **context.Context** | context for authentication, logging, cancellation, deadlines, tracing, etc.
 **optional** | ***PrivateGetSubaccountsGetOpts** | optional parameters | nil if no parameters

### Optional Parameters

Optional parameters are passed through a pointer to a PrivateGetSubaccountsGetOpts struct


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **withPortfolio** | **optional.Bool**|  | 

### Return type

[**map[string]interface{}**](map[string]interface{}.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateSetAnnouncementAsReadGet

> map[string]interface{} PrivateSetAnnouncementAsReadGet(ctx, announcementId)
Marks an announcement as read, so it will not be shown in `get_new_announcements`.

### Required Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
**ctx** | **context.Context** | context for authentication, logging, cancellation, deadlines, tracing, etc.
**announcementId** | **float32**| the ID of the announcement | 

### Return type

[**map[string]interface{}**](map[string]interface{}.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateSetEmailForSubaccountGet

> map[string]interface{} PrivateSetEmailForSubaccountGet(ctx, sid, email)
Assign an email address to a subaccount. User will receive an email with confirmation link.

### Required Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
**ctx** | **context.Context** | context for authentication, logging, cancellation, deadlines, tracing, etc.
**sid** | **int32**| The user id for the subaccount | 
**email** | **string**| The email address for the subaccount | 

### Return type

[**map[string]interface{}**](map[string]interface{}.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateSetEmailLanguageGet

> map[string]interface{} PrivateSetEmailLanguageGet(ctx, language)
Changes the language to be used for emails.

### Required Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
**ctx** | **context.Context** | context for authentication, logging, cancellation, deadlines, tracing, etc.
**language** | **string**| The abbreviated language name. Valid values include &#x60;\&quot;en\&quot;&#x60;, &#x60;\&quot;ko\&quot;&#x60;, &#x60;\&quot;zh\&quot;&#x60; | 

### Return type

[**map[string]interface{}**](map[string]interface{}.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateSetPasswordForSubaccountGet

> map[string]interface{} PrivateSetPasswordForSubaccountGet(ctx, sid, password)
Set the password for the subaccount

### Required Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
**ctx** | **context.Context** | context for authentication, logging, cancellation, deadlines, tracing, etc.
**sid** | **int32**| The user id for the subaccount | 
**password** | **string**| The password for the subaccount | 

### Return type

[**map[string]interface{}**](map[string]interface{}.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateToggleNotificationsFromSubaccountGet

> map[string]interface{} PrivateToggleNotificationsFromSubaccountGet(ctx, sid, state)
Enable or disable sending of notifications for the subaccount.

### Required Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
**ctx** | **context.Context** | context for authentication, logging, cancellation, deadlines, tracing, etc.
**sid** | **int32**| The user id for the subaccount | 
**state** | **bool**| enable (&#x60;true&#x60;) or disable (&#x60;false&#x60;) notifications | 

### Return type

[**map[string]interface{}**](map[string]interface{}.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateToggleSubaccountLoginGet

> map[string]interface{} PrivateToggleSubaccountLoginGet(ctx, sid, state)
Enable or disable login for a subaccount. If login is disabled and a session for the subaccount exists, this session will be terminated.

### Required Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
**ctx** | **context.Context** | context for authentication, logging, cancellation, deadlines, tracing, etc.
**sid** | **int32**| The user id for the subaccount | 
**state** | **string**| enable or disable login. | 

### Return type

[**map[string]interface{}**](map[string]interface{}.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PublicGetAnnouncementsGet

> map[string]interface{} PublicGetAnnouncementsGet(ctx, )
Retrieves announcements from the last 30 days.

### Required Parameters

This endpoint does not need any parameter.

### Return type

[**map[string]interface{}**](map[string]interface{}.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)

