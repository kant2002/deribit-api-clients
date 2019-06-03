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
> Object privateChangeSubaccountNameGet(sid, name)

Change the user name for a subaccount

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.AccountManagementApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://www.deribit.com/api/v2");
    
    // Configure HTTP basic authorization: bearerAuth
    HttpBasicAuth bearerAuth = (HttpBasicAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setUsername("YOUR USERNAME");
    bearerAuth.setPassword("YOUR PASSWORD");

    AccountManagementApi apiInstance = new AccountManagementApi(defaultClient);
    Integer sid = 56; // Integer | The user id for the subaccount
    String name = newUserName; // String | The new user name
    try {
      Object result = apiInstance.privateChangeSubaccountNameGet(sid, name);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling AccountManagementApi#privateChangeSubaccountNameGet");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **sid** | **Integer**| The user id for the subaccount |
 **name** | **String**| The new user name |

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | ok response |  -  |

<a name="privateCreateSubaccountGet"></a>
# **privateCreateSubaccountGet**
> Object privateCreateSubaccountGet()

Create a new subaccount

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.AccountManagementApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://www.deribit.com/api/v2");
    
    // Configure HTTP basic authorization: bearerAuth
    HttpBasicAuth bearerAuth = (HttpBasicAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setUsername("YOUR USERNAME");
    bearerAuth.setPassword("YOUR PASSWORD");

    AccountManagementApi apiInstance = new AccountManagementApi(defaultClient);
    try {
      Object result = apiInstance.privateCreateSubaccountGet();
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling AccountManagementApi#privateCreateSubaccountGet");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
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

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | ok response |  -  |

<a name="privateDisableTfaForSubaccountGet"></a>
# **privateDisableTfaForSubaccountGet**
> Object privateDisableTfaForSubaccountGet(sid)

Disable two factor authentication for a subaccount.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.AccountManagementApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://www.deribit.com/api/v2");
    
    // Configure HTTP basic authorization: bearerAuth
    HttpBasicAuth bearerAuth = (HttpBasicAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setUsername("YOUR USERNAME");
    bearerAuth.setPassword("YOUR PASSWORD");

    AccountManagementApi apiInstance = new AccountManagementApi(defaultClient);
    Integer sid = 56; // Integer | The user id for the subaccount
    try {
      Object result = apiInstance.privateDisableTfaForSubaccountGet(sid);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling AccountManagementApi#privateDisableTfaForSubaccountGet");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **sid** | **Integer**| The user id for the subaccount |

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | ok response |  -  |

<a name="privateGetAccountSummaryGet"></a>
# **privateGetAccountSummaryGet**
> Object privateGetAccountSummaryGet(currency, extended)

Retrieves user account summary.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.AccountManagementApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://www.deribit.com/api/v2");
    
    // Configure HTTP basic authorization: bearerAuth
    HttpBasicAuth bearerAuth = (HttpBasicAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setUsername("YOUR USERNAME");
    bearerAuth.setPassword("YOUR PASSWORD");

    AccountManagementApi apiInstance = new AccountManagementApi(defaultClient);
    String currency = "currency_example"; // String | The currency symbol
    Boolean extended = false; // Boolean | Include additional fields
    try {
      Object result = apiInstance.privateGetAccountSummaryGet(currency, extended);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling AccountManagementApi#privateGetAccountSummaryGet");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **String**| The currency symbol | [enum: BTC, ETH]
 **extended** | **Boolean**| Include additional fields | [optional]

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** |  |  -  |

<a name="privateGetEmailLanguageGet"></a>
# **privateGetEmailLanguageGet**
> Object privateGetEmailLanguageGet()

Retrieves the language to be used for emails.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.AccountManagementApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://www.deribit.com/api/v2");
    
    // Configure HTTP basic authorization: bearerAuth
    HttpBasicAuth bearerAuth = (HttpBasicAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setUsername("YOUR USERNAME");
    bearerAuth.setPassword("YOUR PASSWORD");

    AccountManagementApi apiInstance = new AccountManagementApi(defaultClient);
    try {
      Object result = apiInstance.privateGetEmailLanguageGet();
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling AccountManagementApi#privateGetEmailLanguageGet");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
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

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** |  |  -  |

<a name="privateGetNewAnnouncementsGet"></a>
# **privateGetNewAnnouncementsGet**
> Object privateGetNewAnnouncementsGet()

Retrieves announcements that have not been marked read by the user.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.AccountManagementApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://www.deribit.com/api/v2");
    
    // Configure HTTP basic authorization: bearerAuth
    HttpBasicAuth bearerAuth = (HttpBasicAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setUsername("YOUR USERNAME");
    bearerAuth.setPassword("YOUR PASSWORD");

    AccountManagementApi apiInstance = new AccountManagementApi(defaultClient);
    try {
      Object result = apiInstance.privateGetNewAnnouncementsGet();
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling AccountManagementApi#privateGetNewAnnouncementsGet");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
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

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** |  |  -  |

<a name="privateGetPositionGet"></a>
# **privateGetPositionGet**
> Object privateGetPositionGet(instrumentName)

Retrieve user position.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.AccountManagementApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://www.deribit.com/api/v2");
    
    // Configure HTTP basic authorization: bearerAuth
    HttpBasicAuth bearerAuth = (HttpBasicAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setUsername("YOUR USERNAME");
    bearerAuth.setPassword("YOUR PASSWORD");

    AccountManagementApi apiInstance = new AccountManagementApi(defaultClient);
    String instrumentName = BTC-PERPETUAL; // String | Instrument name
    try {
      Object result = apiInstance.privateGetPositionGet(instrumentName);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling AccountManagementApi#privateGetPositionGet");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
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

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | When successful returns position |  -  |
**400** | When some error occurs |  -  |

<a name="privateGetPositionsGet"></a>
# **privateGetPositionsGet**
> Object privateGetPositionsGet(currency, kind)

Retrieve user positions.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.AccountManagementApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://www.deribit.com/api/v2");
    
    // Configure HTTP basic authorization: bearerAuth
    HttpBasicAuth bearerAuth = (HttpBasicAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setUsername("YOUR USERNAME");
    bearerAuth.setPassword("YOUR PASSWORD");

    AccountManagementApi apiInstance = new AccountManagementApi(defaultClient);
    String currency = "currency_example"; // String | 
    String kind = "kind_example"; // String | Kind filter on positions
    try {
      Object result = apiInstance.privateGetPositionsGet(currency, kind);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling AccountManagementApi#privateGetPositionsGet");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **String**|  | [enum: BTC, ETH]
 **kind** | **String**| Kind filter on positions | [optional] [enum: future, option]

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | When successful returns array of positions |  -  |
**400** | When some error occurs |  -  |

<a name="privateGetSubaccountsGet"></a>
# **privateGetSubaccountsGet**
> Object privateGetSubaccountsGet(withPortfolio)

Get information about subaccounts

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.AccountManagementApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://www.deribit.com/api/v2");
    
    // Configure HTTP basic authorization: bearerAuth
    HttpBasicAuth bearerAuth = (HttpBasicAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setUsername("YOUR USERNAME");
    bearerAuth.setPassword("YOUR PASSWORD");

    AccountManagementApi apiInstance = new AccountManagementApi(defaultClient);
    Boolean withPortfolio = true; // Boolean | 
    try {
      Object result = apiInstance.privateGetSubaccountsGet(withPortfolio);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling AccountManagementApi#privateGetSubaccountsGet");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
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

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | ok response |  -  |
**401** | not authorised |  -  |

<a name="privateSetAnnouncementAsReadGet"></a>
# **privateSetAnnouncementAsReadGet**
> Object privateSetAnnouncementAsReadGet(announcementId)

Marks an announcement as read, so it will not be shown in &#x60;get_new_announcements&#x60;.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.AccountManagementApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://www.deribit.com/api/v2");
    
    // Configure HTTP basic authorization: bearerAuth
    HttpBasicAuth bearerAuth = (HttpBasicAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setUsername("YOUR USERNAME");
    bearerAuth.setPassword("YOUR PASSWORD");

    AccountManagementApi apiInstance = new AccountManagementApi(defaultClient);
    BigDecimal announcementId = new BigDecimal(); // BigDecimal | the ID of the announcement
    try {
      Object result = apiInstance.privateSetAnnouncementAsReadGet(announcementId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling AccountManagementApi#privateSetAnnouncementAsReadGet");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **announcementId** | **BigDecimal**| the ID of the announcement |

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** |  |  -  |

<a name="privateSetEmailForSubaccountGet"></a>
# **privateSetEmailForSubaccountGet**
> Object privateSetEmailForSubaccountGet(sid, email)

Assign an email address to a subaccount. User will receive an email with confirmation link.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.AccountManagementApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://www.deribit.com/api/v2");
    
    // Configure HTTP basic authorization: bearerAuth
    HttpBasicAuth bearerAuth = (HttpBasicAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setUsername("YOUR USERNAME");
    bearerAuth.setPassword("YOUR PASSWORD");

    AccountManagementApi apiInstance = new AccountManagementApi(defaultClient);
    Integer sid = 56; // Integer | The user id for the subaccount
    String email = subaccount_1@email.com; // String | The email address for the subaccount
    try {
      Object result = apiInstance.privateSetEmailForSubaccountGet(sid, email);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling AccountManagementApi#privateSetEmailForSubaccountGet");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **sid** | **Integer**| The user id for the subaccount |
 **email** | **String**| The email address for the subaccount |

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | ok response |  -  |

<a name="privateSetEmailLanguageGet"></a>
# **privateSetEmailLanguageGet**
> Object privateSetEmailLanguageGet(language)

Changes the language to be used for emails.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.AccountManagementApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://www.deribit.com/api/v2");
    
    // Configure HTTP basic authorization: bearerAuth
    HttpBasicAuth bearerAuth = (HttpBasicAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setUsername("YOUR USERNAME");
    bearerAuth.setPassword("YOUR PASSWORD");

    AccountManagementApi apiInstance = new AccountManagementApi(defaultClient);
    String language = en; // String | The abbreviated language name. Valid values include `\"en\"`, `\"ko\"`, `\"zh\"`
    try {
      Object result = apiInstance.privateSetEmailLanguageGet(language);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling AccountManagementApi#privateSetEmailLanguageGet");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
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

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** |  |  -  |

<a name="privateSetPasswordForSubaccountGet"></a>
# **privateSetPasswordForSubaccountGet**
> Object privateSetPasswordForSubaccountGet(sid, password)

Set the password for the subaccount

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.AccountManagementApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://www.deribit.com/api/v2");
    
    // Configure HTTP basic authorization: bearerAuth
    HttpBasicAuth bearerAuth = (HttpBasicAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setUsername("YOUR USERNAME");
    bearerAuth.setPassword("YOUR PASSWORD");

    AccountManagementApi apiInstance = new AccountManagementApi(defaultClient);
    Integer sid = 56; // Integer | The user id for the subaccount
    String password = "password_example"; // String | The password for the subaccount
    try {
      Object result = apiInstance.privateSetPasswordForSubaccountGet(sid, password);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling AccountManagementApi#privateSetPasswordForSubaccountGet");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **sid** | **Integer**| The user id for the subaccount |
 **password** | **String**| The password for the subaccount |

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | ok response |  -  |

<a name="privateToggleNotificationsFromSubaccountGet"></a>
# **privateToggleNotificationsFromSubaccountGet**
> Object privateToggleNotificationsFromSubaccountGet(sid, state)

Enable or disable sending of notifications for the subaccount.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.AccountManagementApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://www.deribit.com/api/v2");
    
    // Configure HTTP basic authorization: bearerAuth
    HttpBasicAuth bearerAuth = (HttpBasicAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setUsername("YOUR USERNAME");
    bearerAuth.setPassword("YOUR PASSWORD");

    AccountManagementApi apiInstance = new AccountManagementApi(defaultClient);
    Integer sid = 56; // Integer | The user id for the subaccount
    Boolean state = true; // Boolean | enable (`true`) or disable (`false`) notifications
    try {
      Object result = apiInstance.privateToggleNotificationsFromSubaccountGet(sid, state);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling AccountManagementApi#privateToggleNotificationsFromSubaccountGet");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **sid** | **Integer**| The user id for the subaccount |
 **state** | **Boolean**| enable (&#x60;true&#x60;) or disable (&#x60;false&#x60;) notifications |

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | ok response |  -  |

<a name="privateToggleSubaccountLoginGet"></a>
# **privateToggleSubaccountLoginGet**
> Object privateToggleSubaccountLoginGet(sid, state)

Enable or disable login for a subaccount. If login is disabled and a session for the subaccount exists, this session will be terminated.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.AccountManagementApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://www.deribit.com/api/v2");
    
    // Configure HTTP basic authorization: bearerAuth
    HttpBasicAuth bearerAuth = (HttpBasicAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setUsername("YOUR USERNAME");
    bearerAuth.setPassword("YOUR PASSWORD");

    AccountManagementApi apiInstance = new AccountManagementApi(defaultClient);
    Integer sid = 56; // Integer | The user id for the subaccount
    String state = "state_example"; // String | enable or disable login.
    try {
      Object result = apiInstance.privateToggleSubaccountLoginGet(sid, state);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling AccountManagementApi#privateToggleSubaccountLoginGet");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **sid** | **Integer**| The user id for the subaccount |
 **state** | **String**| enable or disable login. | [enum: enable, disable]

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | ok response |  -  |

<a name="publicGetAnnouncementsGet"></a>
# **publicGetAnnouncementsGet**
> Object publicGetAnnouncementsGet()

Retrieves announcements from the last 30 days.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.AccountManagementApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://www.deribit.com/api/v2");
    
    // Configure HTTP basic authorization: bearerAuth
    HttpBasicAuth bearerAuth = (HttpBasicAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setUsername("YOUR USERNAME");
    bearerAuth.setPassword("YOUR PASSWORD");

    AccountManagementApi apiInstance = new AccountManagementApi(defaultClient);
    try {
      Object result = apiInstance.publicGetAnnouncementsGet();
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling AccountManagementApi#publicGetAnnouncementsGet");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
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

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** |  |  -  |

