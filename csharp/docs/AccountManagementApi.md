# Org.OpenAPITools.Api.AccountManagementApi

All URIs are relative to *https://www.deribit.com/api/v2*

Method | HTTP request | Description
------------- | ------------- | -------------
[**PrivateChangeSubaccountNameGet**](AccountManagementApi.md#privatechangesubaccountnameget) | **GET** /private/change_subaccount_name | Change the user name for a subaccount
[**PrivateCreateSubaccountGet**](AccountManagementApi.md#privatecreatesubaccountget) | **GET** /private/create_subaccount | Create a new subaccount
[**PrivateDisableTfaForSubaccountGet**](AccountManagementApi.md#privatedisabletfaforsubaccountget) | **GET** /private/disable_tfa_for_subaccount | Disable two factor authentication for a subaccount.
[**PrivateGetAccountSummaryGet**](AccountManagementApi.md#privategetaccountsummaryget) | **GET** /private/get_account_summary | Retrieves user account summary.
[**PrivateGetEmailLanguageGet**](AccountManagementApi.md#privategetemaillanguageget) | **GET** /private/get_email_language | Retrieves the language to be used for emails.
[**PrivateGetNewAnnouncementsGet**](AccountManagementApi.md#privategetnewannouncementsget) | **GET** /private/get_new_announcements | Retrieves announcements that have not been marked read by the user.
[**PrivateGetPositionGet**](AccountManagementApi.md#privategetpositionget) | **GET** /private/get_position | Retrieve user position.
[**PrivateGetPositionsGet**](AccountManagementApi.md#privategetpositionsget) | **GET** /private/get_positions | Retrieve user positions.
[**PrivateGetSubaccountsGet**](AccountManagementApi.md#privategetsubaccountsget) | **GET** /private/get_subaccounts | Get information about subaccounts
[**PrivateSetAnnouncementAsReadGet**](AccountManagementApi.md#privatesetannouncementasreadget) | **GET** /private/set_announcement_as_read | Marks an announcement as read, so it will not be shown in &#x60;get_new_announcements&#x60;.
[**PrivateSetEmailForSubaccountGet**](AccountManagementApi.md#privatesetemailforsubaccountget) | **GET** /private/set_email_for_subaccount | Assign an email address to a subaccount. User will receive an email with confirmation link.
[**PrivateSetEmailLanguageGet**](AccountManagementApi.md#privatesetemaillanguageget) | **GET** /private/set_email_language | Changes the language to be used for emails.
[**PrivateSetPasswordForSubaccountGet**](AccountManagementApi.md#privatesetpasswordforsubaccountget) | **GET** /private/set_password_for_subaccount | Set the password for the subaccount
[**PrivateToggleNotificationsFromSubaccountGet**](AccountManagementApi.md#privatetogglenotificationsfromsubaccountget) | **GET** /private/toggle_notifications_from_subaccount | Enable or disable sending of notifications for the subaccount.
[**PrivateToggleSubaccountLoginGet**](AccountManagementApi.md#privatetogglesubaccountloginget) | **GET** /private/toggle_subaccount_login | Enable or disable login for a subaccount. If login is disabled and a session for the subaccount exists, this session will be terminated.
[**PublicGetAnnouncementsGet**](AccountManagementApi.md#publicgetannouncementsget) | **GET** /public/get_announcements | Retrieves announcements from the last 30 days.



## PrivateChangeSubaccountNameGet

> Object PrivateChangeSubaccountNameGet (int? sid, string name)

Change the user name for a subaccount

### Example

```csharp
using System.Diagnostics;
using Org.OpenAPITools.Api;
using Org.OpenAPITools.Client;
using Org.OpenAPITools.Model;

namespace Example
{
    public class PrivateChangeSubaccountNameGetExample
    {
        public static void Main()
        {
            Configuration.Default.BasePath = "https://www.deribit.com/api/v2";
            // Configure HTTP basic authorization: bearerAuth
            Configuration.Default.Username = "YOUR_USERNAME";
            Configuration.Default.Password = "YOUR_PASSWORD";

            var apiInstance = new AccountManagementApi(Configuration.Default);
            var sid = 56;  // int? | The user id for the subaccount
            var name = newUserName;  // string | The new user name

            try
            {
                // Change the user name for a subaccount
                Object result = apiInstance.PrivateChangeSubaccountNameGet(sid, name);
                Debug.WriteLine(result);
            }
            catch (ApiException e)
            {
                Debug.Print("Exception when calling AccountManagementApi.PrivateChangeSubaccountNameGet: " + e.Message );
                Debug.Print("Status Code: "+ e.ErrorCode);
                Debug.Print(e.StackTrace);
            }
        }
    }
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **sid** | **int?**| The user id for the subaccount | 
 **name** | **string**| The new user name | 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#)
[[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateCreateSubaccountGet

> Object PrivateCreateSubaccountGet ()

Create a new subaccount

### Example

```csharp
using System.Diagnostics;
using Org.OpenAPITools.Api;
using Org.OpenAPITools.Client;
using Org.OpenAPITools.Model;

namespace Example
{
    public class PrivateCreateSubaccountGetExample
    {
        public static void Main()
        {
            Configuration.Default.BasePath = "https://www.deribit.com/api/v2";
            // Configure HTTP basic authorization: bearerAuth
            Configuration.Default.Username = "YOUR_USERNAME";
            Configuration.Default.Password = "YOUR_PASSWORD";

            var apiInstance = new AccountManagementApi(Configuration.Default);

            try
            {
                // Create a new subaccount
                Object result = apiInstance.PrivateCreateSubaccountGet();
                Debug.WriteLine(result);
            }
            catch (ApiException e)
            {
                Debug.Print("Exception when calling AccountManagementApi.PrivateCreateSubaccountGet: " + e.Message );
                Debug.Print("Status Code: "+ e.ErrorCode);
                Debug.Print(e.StackTrace);
            }
        }
    }
}
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

[[Back to top]](#)
[[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateDisableTfaForSubaccountGet

> Object PrivateDisableTfaForSubaccountGet (int? sid)

Disable two factor authentication for a subaccount.

### Example

```csharp
using System.Diagnostics;
using Org.OpenAPITools.Api;
using Org.OpenAPITools.Client;
using Org.OpenAPITools.Model;

namespace Example
{
    public class PrivateDisableTfaForSubaccountGetExample
    {
        public static void Main()
        {
            Configuration.Default.BasePath = "https://www.deribit.com/api/v2";
            // Configure HTTP basic authorization: bearerAuth
            Configuration.Default.Username = "YOUR_USERNAME";
            Configuration.Default.Password = "YOUR_PASSWORD";

            var apiInstance = new AccountManagementApi(Configuration.Default);
            var sid = 56;  // int? | The user id for the subaccount

            try
            {
                // Disable two factor authentication for a subaccount.
                Object result = apiInstance.PrivateDisableTfaForSubaccountGet(sid);
                Debug.WriteLine(result);
            }
            catch (ApiException e)
            {
                Debug.Print("Exception when calling AccountManagementApi.PrivateDisableTfaForSubaccountGet: " + e.Message );
                Debug.Print("Status Code: "+ e.ErrorCode);
                Debug.Print(e.StackTrace);
            }
        }
    }
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **sid** | **int?**| The user id for the subaccount | 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#)
[[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateGetAccountSummaryGet

> Object PrivateGetAccountSummaryGet (string currency, bool? extended = null)

Retrieves user account summary.

### Example

```csharp
using System.Diagnostics;
using Org.OpenAPITools.Api;
using Org.OpenAPITools.Client;
using Org.OpenAPITools.Model;

namespace Example
{
    public class PrivateGetAccountSummaryGetExample
    {
        public static void Main()
        {
            Configuration.Default.BasePath = "https://www.deribit.com/api/v2";
            // Configure HTTP basic authorization: bearerAuth
            Configuration.Default.Username = "YOUR_USERNAME";
            Configuration.Default.Password = "YOUR_PASSWORD";

            var apiInstance = new AccountManagementApi(Configuration.Default);
            var currency = currency_example;  // string | The currency symbol
            var extended = false;  // bool? | Include additional fields (optional) 

            try
            {
                // Retrieves user account summary.
                Object result = apiInstance.PrivateGetAccountSummaryGet(currency, extended);
                Debug.WriteLine(result);
            }
            catch (ApiException e)
            {
                Debug.Print("Exception when calling AccountManagementApi.PrivateGetAccountSummaryGet: " + e.Message );
                Debug.Print("Status Code: "+ e.ErrorCode);
                Debug.Print(e.StackTrace);
            }
        }
    }
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **string**| The currency symbol | 
 **extended** | **bool?**| Include additional fields | [optional] 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#)
[[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateGetEmailLanguageGet

> Object PrivateGetEmailLanguageGet ()

Retrieves the language to be used for emails.

### Example

```csharp
using System.Diagnostics;
using Org.OpenAPITools.Api;
using Org.OpenAPITools.Client;
using Org.OpenAPITools.Model;

namespace Example
{
    public class PrivateGetEmailLanguageGetExample
    {
        public static void Main()
        {
            Configuration.Default.BasePath = "https://www.deribit.com/api/v2";
            // Configure HTTP basic authorization: bearerAuth
            Configuration.Default.Username = "YOUR_USERNAME";
            Configuration.Default.Password = "YOUR_PASSWORD";

            var apiInstance = new AccountManagementApi(Configuration.Default);

            try
            {
                // Retrieves the language to be used for emails.
                Object result = apiInstance.PrivateGetEmailLanguageGet();
                Debug.WriteLine(result);
            }
            catch (ApiException e)
            {
                Debug.Print("Exception when calling AccountManagementApi.PrivateGetEmailLanguageGet: " + e.Message );
                Debug.Print("Status Code: "+ e.ErrorCode);
                Debug.Print(e.StackTrace);
            }
        }
    }
}
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

[[Back to top]](#)
[[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateGetNewAnnouncementsGet

> Object PrivateGetNewAnnouncementsGet ()

Retrieves announcements that have not been marked read by the user.

### Example

```csharp
using System.Diagnostics;
using Org.OpenAPITools.Api;
using Org.OpenAPITools.Client;
using Org.OpenAPITools.Model;

namespace Example
{
    public class PrivateGetNewAnnouncementsGetExample
    {
        public static void Main()
        {
            Configuration.Default.BasePath = "https://www.deribit.com/api/v2";
            // Configure HTTP basic authorization: bearerAuth
            Configuration.Default.Username = "YOUR_USERNAME";
            Configuration.Default.Password = "YOUR_PASSWORD";

            var apiInstance = new AccountManagementApi(Configuration.Default);

            try
            {
                // Retrieves announcements that have not been marked read by the user.
                Object result = apiInstance.PrivateGetNewAnnouncementsGet();
                Debug.WriteLine(result);
            }
            catch (ApiException e)
            {
                Debug.Print("Exception when calling AccountManagementApi.PrivateGetNewAnnouncementsGet: " + e.Message );
                Debug.Print("Status Code: "+ e.ErrorCode);
                Debug.Print(e.StackTrace);
            }
        }
    }
}
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

[[Back to top]](#)
[[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateGetPositionGet

> Object PrivateGetPositionGet (string instrumentName)

Retrieve user position.

### Example

```csharp
using System.Diagnostics;
using Org.OpenAPITools.Api;
using Org.OpenAPITools.Client;
using Org.OpenAPITools.Model;

namespace Example
{
    public class PrivateGetPositionGetExample
    {
        public static void Main()
        {
            Configuration.Default.BasePath = "https://www.deribit.com/api/v2";
            // Configure HTTP basic authorization: bearerAuth
            Configuration.Default.Username = "YOUR_USERNAME";
            Configuration.Default.Password = "YOUR_PASSWORD";

            var apiInstance = new AccountManagementApi(Configuration.Default);
            var instrumentName = BTC-PERPETUAL;  // string | Instrument name

            try
            {
                // Retrieve user position.
                Object result = apiInstance.PrivateGetPositionGet(instrumentName);
                Debug.WriteLine(result);
            }
            catch (ApiException e)
            {
                Debug.Print("Exception when calling AccountManagementApi.PrivateGetPositionGet: " + e.Message );
                Debug.Print("Status Code: "+ e.ErrorCode);
                Debug.Print(e.StackTrace);
            }
        }
    }
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrumentName** | **string**| Instrument name | 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#)
[[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateGetPositionsGet

> Object PrivateGetPositionsGet (string currency, string kind = null)

Retrieve user positions.

### Example

```csharp
using System.Diagnostics;
using Org.OpenAPITools.Api;
using Org.OpenAPITools.Client;
using Org.OpenAPITools.Model;

namespace Example
{
    public class PrivateGetPositionsGetExample
    {
        public static void Main()
        {
            Configuration.Default.BasePath = "https://www.deribit.com/api/v2";
            // Configure HTTP basic authorization: bearerAuth
            Configuration.Default.Username = "YOUR_USERNAME";
            Configuration.Default.Password = "YOUR_PASSWORD";

            var apiInstance = new AccountManagementApi(Configuration.Default);
            var currency = currency_example;  // string | 
            var kind = kind_example;  // string | Kind filter on positions (optional) 

            try
            {
                // Retrieve user positions.
                Object result = apiInstance.PrivateGetPositionsGet(currency, kind);
                Debug.WriteLine(result);
            }
            catch (ApiException e)
            {
                Debug.Print("Exception when calling AccountManagementApi.PrivateGetPositionsGet: " + e.Message );
                Debug.Print("Status Code: "+ e.ErrorCode);
                Debug.Print(e.StackTrace);
            }
        }
    }
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **string**|  | 
 **kind** | **string**| Kind filter on positions | [optional] 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#)
[[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateGetSubaccountsGet

> Object PrivateGetSubaccountsGet (bool? withPortfolio = null)

Get information about subaccounts

### Example

```csharp
using System.Diagnostics;
using Org.OpenAPITools.Api;
using Org.OpenAPITools.Client;
using Org.OpenAPITools.Model;

namespace Example
{
    public class PrivateGetSubaccountsGetExample
    {
        public static void Main()
        {
            Configuration.Default.BasePath = "https://www.deribit.com/api/v2";
            // Configure HTTP basic authorization: bearerAuth
            Configuration.Default.Username = "YOUR_USERNAME";
            Configuration.Default.Password = "YOUR_PASSWORD";

            var apiInstance = new AccountManagementApi(Configuration.Default);
            var withPortfolio = true;  // bool? |  (optional) 

            try
            {
                // Get information about subaccounts
                Object result = apiInstance.PrivateGetSubaccountsGet(withPortfolio);
                Debug.WriteLine(result);
            }
            catch (ApiException e)
            {
                Debug.Print("Exception when calling AccountManagementApi.PrivateGetSubaccountsGet: " + e.Message );
                Debug.Print("Status Code: "+ e.ErrorCode);
                Debug.Print(e.StackTrace);
            }
        }
    }
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **withPortfolio** | **bool?**|  | [optional] 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#)
[[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateSetAnnouncementAsReadGet

> Object PrivateSetAnnouncementAsReadGet (decimal? announcementId)

Marks an announcement as read, so it will not be shown in `get_new_announcements`.

### Example

```csharp
using System.Diagnostics;
using Org.OpenAPITools.Api;
using Org.OpenAPITools.Client;
using Org.OpenAPITools.Model;

namespace Example
{
    public class PrivateSetAnnouncementAsReadGetExample
    {
        public static void Main()
        {
            Configuration.Default.BasePath = "https://www.deribit.com/api/v2";
            // Configure HTTP basic authorization: bearerAuth
            Configuration.Default.Username = "YOUR_USERNAME";
            Configuration.Default.Password = "YOUR_PASSWORD";

            var apiInstance = new AccountManagementApi(Configuration.Default);
            var announcementId = 8.14;  // decimal? | the ID of the announcement

            try
            {
                // Marks an announcement as read, so it will not be shown in `get_new_announcements`.
                Object result = apiInstance.PrivateSetAnnouncementAsReadGet(announcementId);
                Debug.WriteLine(result);
            }
            catch (ApiException e)
            {
                Debug.Print("Exception when calling AccountManagementApi.PrivateSetAnnouncementAsReadGet: " + e.Message );
                Debug.Print("Status Code: "+ e.ErrorCode);
                Debug.Print(e.StackTrace);
            }
        }
    }
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **announcementId** | **decimal?**| the ID of the announcement | 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#)
[[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateSetEmailForSubaccountGet

> Object PrivateSetEmailForSubaccountGet (int? sid, string email)

Assign an email address to a subaccount. User will receive an email with confirmation link.

### Example

```csharp
using System.Diagnostics;
using Org.OpenAPITools.Api;
using Org.OpenAPITools.Client;
using Org.OpenAPITools.Model;

namespace Example
{
    public class PrivateSetEmailForSubaccountGetExample
    {
        public static void Main()
        {
            Configuration.Default.BasePath = "https://www.deribit.com/api/v2";
            // Configure HTTP basic authorization: bearerAuth
            Configuration.Default.Username = "YOUR_USERNAME";
            Configuration.Default.Password = "YOUR_PASSWORD";

            var apiInstance = new AccountManagementApi(Configuration.Default);
            var sid = 56;  // int? | The user id for the subaccount
            var email = subaccount_1@email.com;  // string | The email address for the subaccount

            try
            {
                // Assign an email address to a subaccount. User will receive an email with confirmation link.
                Object result = apiInstance.PrivateSetEmailForSubaccountGet(sid, email);
                Debug.WriteLine(result);
            }
            catch (ApiException e)
            {
                Debug.Print("Exception when calling AccountManagementApi.PrivateSetEmailForSubaccountGet: " + e.Message );
                Debug.Print("Status Code: "+ e.ErrorCode);
                Debug.Print(e.StackTrace);
            }
        }
    }
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **sid** | **int?**| The user id for the subaccount | 
 **email** | **string**| The email address for the subaccount | 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#)
[[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateSetEmailLanguageGet

> Object PrivateSetEmailLanguageGet (string language)

Changes the language to be used for emails.

### Example

```csharp
using System.Diagnostics;
using Org.OpenAPITools.Api;
using Org.OpenAPITools.Client;
using Org.OpenAPITools.Model;

namespace Example
{
    public class PrivateSetEmailLanguageGetExample
    {
        public static void Main()
        {
            Configuration.Default.BasePath = "https://www.deribit.com/api/v2";
            // Configure HTTP basic authorization: bearerAuth
            Configuration.Default.Username = "YOUR_USERNAME";
            Configuration.Default.Password = "YOUR_PASSWORD";

            var apiInstance = new AccountManagementApi(Configuration.Default);
            var language = en;  // string | The abbreviated language name. Valid values include `\"en\"`, `\"ko\"`, `\"zh\"`

            try
            {
                // Changes the language to be used for emails.
                Object result = apiInstance.PrivateSetEmailLanguageGet(language);
                Debug.WriteLine(result);
            }
            catch (ApiException e)
            {
                Debug.Print("Exception when calling AccountManagementApi.PrivateSetEmailLanguageGet: " + e.Message );
                Debug.Print("Status Code: "+ e.ErrorCode);
                Debug.Print(e.StackTrace);
            }
        }
    }
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **language** | **string**| The abbreviated language name. Valid values include &#x60;\&quot;en\&quot;&#x60;, &#x60;\&quot;ko\&quot;&#x60;, &#x60;\&quot;zh\&quot;&#x60; | 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#)
[[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateSetPasswordForSubaccountGet

> Object PrivateSetPasswordForSubaccountGet (int? sid, string password)

Set the password for the subaccount

### Example

```csharp
using System.Diagnostics;
using Org.OpenAPITools.Api;
using Org.OpenAPITools.Client;
using Org.OpenAPITools.Model;

namespace Example
{
    public class PrivateSetPasswordForSubaccountGetExample
    {
        public static void Main()
        {
            Configuration.Default.BasePath = "https://www.deribit.com/api/v2";
            // Configure HTTP basic authorization: bearerAuth
            Configuration.Default.Username = "YOUR_USERNAME";
            Configuration.Default.Password = "YOUR_PASSWORD";

            var apiInstance = new AccountManagementApi(Configuration.Default);
            var sid = 56;  // int? | The user id for the subaccount
            var password = password_example;  // string | The password for the subaccount

            try
            {
                // Set the password for the subaccount
                Object result = apiInstance.PrivateSetPasswordForSubaccountGet(sid, password);
                Debug.WriteLine(result);
            }
            catch (ApiException e)
            {
                Debug.Print("Exception when calling AccountManagementApi.PrivateSetPasswordForSubaccountGet: " + e.Message );
                Debug.Print("Status Code: "+ e.ErrorCode);
                Debug.Print(e.StackTrace);
            }
        }
    }
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **sid** | **int?**| The user id for the subaccount | 
 **password** | **string**| The password for the subaccount | 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#)
[[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateToggleNotificationsFromSubaccountGet

> Object PrivateToggleNotificationsFromSubaccountGet (int? sid, bool? state)

Enable or disable sending of notifications for the subaccount.

### Example

```csharp
using System.Diagnostics;
using Org.OpenAPITools.Api;
using Org.OpenAPITools.Client;
using Org.OpenAPITools.Model;

namespace Example
{
    public class PrivateToggleNotificationsFromSubaccountGetExample
    {
        public static void Main()
        {
            Configuration.Default.BasePath = "https://www.deribit.com/api/v2";
            // Configure HTTP basic authorization: bearerAuth
            Configuration.Default.Username = "YOUR_USERNAME";
            Configuration.Default.Password = "YOUR_PASSWORD";

            var apiInstance = new AccountManagementApi(Configuration.Default);
            var sid = 56;  // int? | The user id for the subaccount
            var state = true;  // bool? | enable (`true`) or disable (`false`) notifications

            try
            {
                // Enable or disable sending of notifications for the subaccount.
                Object result = apiInstance.PrivateToggleNotificationsFromSubaccountGet(sid, state);
                Debug.WriteLine(result);
            }
            catch (ApiException e)
            {
                Debug.Print("Exception when calling AccountManagementApi.PrivateToggleNotificationsFromSubaccountGet: " + e.Message );
                Debug.Print("Status Code: "+ e.ErrorCode);
                Debug.Print(e.StackTrace);
            }
        }
    }
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **sid** | **int?**| The user id for the subaccount | 
 **state** | **bool?**| enable (&#x60;true&#x60;) or disable (&#x60;false&#x60;) notifications | 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#)
[[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateToggleSubaccountLoginGet

> Object PrivateToggleSubaccountLoginGet (int? sid, string state)

Enable or disable login for a subaccount. If login is disabled and a session for the subaccount exists, this session will be terminated.

### Example

```csharp
using System.Diagnostics;
using Org.OpenAPITools.Api;
using Org.OpenAPITools.Client;
using Org.OpenAPITools.Model;

namespace Example
{
    public class PrivateToggleSubaccountLoginGetExample
    {
        public static void Main()
        {
            Configuration.Default.BasePath = "https://www.deribit.com/api/v2";
            // Configure HTTP basic authorization: bearerAuth
            Configuration.Default.Username = "YOUR_USERNAME";
            Configuration.Default.Password = "YOUR_PASSWORD";

            var apiInstance = new AccountManagementApi(Configuration.Default);
            var sid = 56;  // int? | The user id for the subaccount
            var state = state_example;  // string | enable or disable login.

            try
            {
                // Enable or disable login for a subaccount. If login is disabled and a session for the subaccount exists, this session will be terminated.
                Object result = apiInstance.PrivateToggleSubaccountLoginGet(sid, state);
                Debug.WriteLine(result);
            }
            catch (ApiException e)
            {
                Debug.Print("Exception when calling AccountManagementApi.PrivateToggleSubaccountLoginGet: " + e.Message );
                Debug.Print("Status Code: "+ e.ErrorCode);
                Debug.Print(e.StackTrace);
            }
        }
    }
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **sid** | **int?**| The user id for the subaccount | 
 **state** | **string**| enable or disable login. | 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#)
[[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PublicGetAnnouncementsGet

> Object PublicGetAnnouncementsGet ()

Retrieves announcements from the last 30 days.

### Example

```csharp
using System.Diagnostics;
using Org.OpenAPITools.Api;
using Org.OpenAPITools.Client;
using Org.OpenAPITools.Model;

namespace Example
{
    public class PublicGetAnnouncementsGetExample
    {
        public static void Main()
        {
            Configuration.Default.BasePath = "https://www.deribit.com/api/v2";
            // Configure HTTP basic authorization: bearerAuth
            Configuration.Default.Username = "YOUR_USERNAME";
            Configuration.Default.Password = "YOUR_PASSWORD";

            var apiInstance = new AccountManagementApi(Configuration.Default);

            try
            {
                // Retrieves announcements from the last 30 days.
                Object result = apiInstance.PublicGetAnnouncementsGet();
                Debug.WriteLine(result);
            }
            catch (ApiException e)
            {
                Debug.Print("Exception when calling AccountManagementApi.PublicGetAnnouncementsGet: " + e.Message );
                Debug.Print("Status Code: "+ e.ErrorCode);
                Debug.Print(e.StackTrace);
            }
        }
    }
}
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

[[Back to top]](#)
[[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)

