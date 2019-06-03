# InternalApi

All URIs are relative to *https://www.deribit.com/api/v2*

Method | HTTP request | Description
------------- | ------------- | -------------
[**privateAddToAddressBookGet**](InternalApi.md#privateAddToAddressBookGet) | **GET** /private/add_to_address_book | Adds new entry to address book of given type
[**privateDisableTfaWithRecoveryCodeGet**](InternalApi.md#privateDisableTfaWithRecoveryCodeGet) | **GET** /private/disable_tfa_with_recovery_code | Disables TFA with one time recovery code
[**privateGetAddressBookGet**](InternalApi.md#privateGetAddressBookGet) | **GET** /private/get_address_book | Retrieves address book of given type
[**privateRemoveFromAddressBookGet**](InternalApi.md#privateRemoveFromAddressBookGet) | **GET** /private/remove_from_address_book | Adds new entry to address book of given type
[**privateSubmitTransferToSubaccountGet**](InternalApi.md#privateSubmitTransferToSubaccountGet) | **GET** /private/submit_transfer_to_subaccount | Transfer funds to a subaccount.
[**privateSubmitTransferToUserGet**](InternalApi.md#privateSubmitTransferToUserGet) | **GET** /private/submit_transfer_to_user | Transfer funds to a another user.
[**privateToggleDepositAddressCreationGet**](InternalApi.md#privateToggleDepositAddressCreationGet) | **GET** /private/toggle_deposit_address_creation | Enable or disable deposit address creation
[**publicGetFooterGet**](InternalApi.md#publicGetFooterGet) | **GET** /public/get_footer | Get information to be displayed in the footer of the website.
[**publicGetOptionMarkPricesGet**](InternalApi.md#publicGetOptionMarkPricesGet) | **GET** /public/get_option_mark_prices | Retrives market prices and its implied volatility of options instruments
[**publicValidateFieldGet**](InternalApi.md#publicValidateFieldGet) | **GET** /public/validate_field | Method used to introduce the client software connected to Deribit platform over websocket. Provided data may have an impact on the maintained connection and will be collected for internal statistical purposes. In response, Deribit will also introduce itself.


<a name="privateAddToAddressBookGet"></a>
# **privateAddToAddressBookGet**
> Object privateAddToAddressBookGet(currency, type, address, name, tfa)

Adds new entry to address book of given type

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.InternalApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://www.deribit.com/api/v2");
    
    // Configure HTTP basic authorization: bearerAuth
    HttpBasicAuth bearerAuth = (HttpBasicAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setUsername("YOUR USERNAME");
    bearerAuth.setPassword("YOUR PASSWORD");

    InternalApi apiInstance = new InternalApi(defaultClient);
    String currency = "currency_example"; // String | The currency symbol
    String type = "type_example"; // String | Address book type
    String address = "address_example"; // String | Address in currency format, it must be in address book
    String name = Main address; // String | Name of address book entry
    String tfa = "tfa_example"; // String | TFA code, required when TFA is enabled for current account
    try {
      Object result = apiInstance.privateAddToAddressBookGet(currency, type, address, name, tfa);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling InternalApi#privateAddToAddressBookGet");
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
 **type** | **String**| Address book type | [enum: transfer, withdrawal]
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

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** |  |  -  |

<a name="privateDisableTfaWithRecoveryCodeGet"></a>
# **privateDisableTfaWithRecoveryCodeGet**
> Object privateDisableTfaWithRecoveryCodeGet(password, code)

Disables TFA with one time recovery code

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.InternalApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://www.deribit.com/api/v2");
    
    // Configure HTTP basic authorization: bearerAuth
    HttpBasicAuth bearerAuth = (HttpBasicAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setUsername("YOUR USERNAME");
    bearerAuth.setPassword("YOUR PASSWORD");

    InternalApi apiInstance = new InternalApi(defaultClient);
    String password = "password_example"; // String | The password for the subaccount
    String code = "code_example"; // String | One time recovery code
    try {
      Object result = apiInstance.privateDisableTfaWithRecoveryCodeGet(password, code);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling InternalApi#privateDisableTfaWithRecoveryCodeGet");
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
 **password** | **String**| The password for the subaccount |
 **code** | **String**| One time recovery code |

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

<a name="privateGetAddressBookGet"></a>
# **privateGetAddressBookGet**
> Object privateGetAddressBookGet(currency, type)

Retrieves address book of given type

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.InternalApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://www.deribit.com/api/v2");
    
    // Configure HTTP basic authorization: bearerAuth
    HttpBasicAuth bearerAuth = (HttpBasicAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setUsername("YOUR USERNAME");
    bearerAuth.setPassword("YOUR PASSWORD");

    InternalApi apiInstance = new InternalApi(defaultClient);
    String currency = "currency_example"; // String | The currency symbol
    String type = "type_example"; // String | Address book type
    try {
      Object result = apiInstance.privateGetAddressBookGet(currency, type);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling InternalApi#privateGetAddressBookGet");
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
 **type** | **String**| Address book type | [enum: transfer, withdrawal]

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

<a name="privateRemoveFromAddressBookGet"></a>
# **privateRemoveFromAddressBookGet**
> Object privateRemoveFromAddressBookGet(currency, type, address, tfa)

Adds new entry to address book of given type

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.InternalApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://www.deribit.com/api/v2");
    
    // Configure HTTP basic authorization: bearerAuth
    HttpBasicAuth bearerAuth = (HttpBasicAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setUsername("YOUR USERNAME");
    bearerAuth.setPassword("YOUR PASSWORD");

    InternalApi apiInstance = new InternalApi(defaultClient);
    String currency = "currency_example"; // String | The currency symbol
    String type = "type_example"; // String | Address book type
    String address = "address_example"; // String | Address in currency format, it must be in address book
    String tfa = "tfa_example"; // String | TFA code, required when TFA is enabled for current account
    try {
      Object result = apiInstance.privateRemoveFromAddressBookGet(currency, type, address, tfa);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling InternalApi#privateRemoveFromAddressBookGet");
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
 **type** | **String**| Address book type | [enum: transfer, withdrawal]
 **address** | **String**| Address in currency format, it must be in address book |
 **tfa** | **String**| TFA code, required when TFA is enabled for current account | [optional]

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

<a name="privateSubmitTransferToSubaccountGet"></a>
# **privateSubmitTransferToSubaccountGet**
> Object privateSubmitTransferToSubaccountGet(currency, amount, destination)

Transfer funds to a subaccount.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.InternalApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://www.deribit.com/api/v2");
    
    // Configure HTTP basic authorization: bearerAuth
    HttpBasicAuth bearerAuth = (HttpBasicAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setUsername("YOUR USERNAME");
    bearerAuth.setPassword("YOUR PASSWORD");

    InternalApi apiInstance = new InternalApi(defaultClient);
    String currency = "currency_example"; // String | The currency symbol
    BigDecimal amount = new BigDecimal(); // BigDecimal | Amount of funds to be transferred
    Integer destination = 1; // Integer | Id of destination subaccount
    try {
      Object result = apiInstance.privateSubmitTransferToSubaccountGet(currency, amount, destination);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling InternalApi#privateSubmitTransferToSubaccountGet");
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
 **amount** | **BigDecimal**| Amount of funds to be transferred |
 **destination** | **Integer**| Id of destination subaccount |

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

<a name="privateSubmitTransferToUserGet"></a>
# **privateSubmitTransferToUserGet**
> Object privateSubmitTransferToUserGet(currency, amount, destination, tfa)

Transfer funds to a another user.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.InternalApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://www.deribit.com/api/v2");
    
    // Configure HTTP basic authorization: bearerAuth
    HttpBasicAuth bearerAuth = (HttpBasicAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setUsername("YOUR USERNAME");
    bearerAuth.setPassword("YOUR PASSWORD");

    InternalApi apiInstance = new InternalApi(defaultClient);
    String currency = "currency_example"; // String | The currency symbol
    BigDecimal amount = new BigDecimal(); // BigDecimal | Amount of funds to be transferred
    String destination = "destination_example"; // String | Destination address from address book
    String tfa = "tfa_example"; // String | TFA code, required when TFA is enabled for current account
    try {
      Object result = apiInstance.privateSubmitTransferToUserGet(currency, amount, destination, tfa);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling InternalApi#privateSubmitTransferToUserGet");
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
 **amount** | **BigDecimal**| Amount of funds to be transferred |
 **destination** | **String**| Destination address from address book |
 **tfa** | **String**| TFA code, required when TFA is enabled for current account | [optional]

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

<a name="privateToggleDepositAddressCreationGet"></a>
# **privateToggleDepositAddressCreationGet**
> Object privateToggleDepositAddressCreationGet(currency, state)

Enable or disable deposit address creation

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.InternalApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://www.deribit.com/api/v2");
    
    // Configure HTTP basic authorization: bearerAuth
    HttpBasicAuth bearerAuth = (HttpBasicAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setUsername("YOUR USERNAME");
    bearerAuth.setPassword("YOUR PASSWORD");

    InternalApi apiInstance = new InternalApi(defaultClient);
    String currency = "currency_example"; // String | The currency symbol
    Boolean state = true; // Boolean | 
    try {
      Object result = apiInstance.privateToggleDepositAddressCreationGet(currency, state);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling InternalApi#privateToggleDepositAddressCreationGet");
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
 **state** | **Boolean**|  |

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

<a name="publicGetFooterGet"></a>
# **publicGetFooterGet**
> Object publicGetFooterGet()

Get information to be displayed in the footer of the website.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.InternalApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://www.deribit.com/api/v2");
    
    // Configure HTTP basic authorization: bearerAuth
    HttpBasicAuth bearerAuth = (HttpBasicAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setUsername("YOUR USERNAME");
    bearerAuth.setPassword("YOUR PASSWORD");

    InternalApi apiInstance = new InternalApi(defaultClient);
    try {
      Object result = apiInstance.publicGetFooterGet();
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling InternalApi#publicGetFooterGet");
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

<a name="publicGetOptionMarkPricesGet"></a>
# **publicGetOptionMarkPricesGet**
> Object publicGetOptionMarkPricesGet(currency)

Retrives market prices and its implied volatility of options instruments

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.InternalApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://www.deribit.com/api/v2");
    
    // Configure HTTP basic authorization: bearerAuth
    HttpBasicAuth bearerAuth = (HttpBasicAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setUsername("YOUR USERNAME");
    bearerAuth.setPassword("YOUR PASSWORD");

    InternalApi apiInstance = new InternalApi(defaultClient);
    String currency = "currency_example"; // String | The currency symbol
    try {
      Object result = apiInstance.publicGetOptionMarkPricesGet(currency);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling InternalApi#publicGetOptionMarkPricesGet");
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

<a name="publicValidateFieldGet"></a>
# **publicValidateFieldGet**
> Object publicValidateFieldGet(field, value, value2)

Method used to introduce the client software connected to Deribit platform over websocket. Provided data may have an impact on the maintained connection and will be collected for internal statistical purposes. In response, Deribit will also introduce itself.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.InternalApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://www.deribit.com/api/v2");
    
    // Configure HTTP basic authorization: bearerAuth
    HttpBasicAuth bearerAuth = (HttpBasicAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setUsername("YOUR USERNAME");
    bearerAuth.setPassword("YOUR PASSWORD");

    InternalApi apiInstance = new InternalApi(defaultClient);
    String field = "field_example"; // String | Name of the field to be validated, examples: postal_code, date_of_birth
    String value = "value_example"; // String | Value to be checked
    String value2 = "value2_example"; // String | Additional value to be compared with
    try {
      Object result = apiInstance.publicValidateFieldGet(field, value, value2);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling InternalApi#publicValidateFieldGet");
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
 **field** | **String**| Name of the field to be validated, examples: postal_code, date_of_birth |
 **value** | **String**| Value to be checked |
 **value2** | **String**| Additional value to be compared with | [optional]

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

