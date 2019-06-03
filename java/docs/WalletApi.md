# WalletApi

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
import org.openapitools.client.api.WalletApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://www.deribit.com/api/v2");
    
    // Configure HTTP basic authorization: bearerAuth
    HttpBasicAuth bearerAuth = (HttpBasicAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setUsername("YOUR USERNAME");
    bearerAuth.setPassword("YOUR PASSWORD");

    WalletApi apiInstance = new WalletApi(defaultClient);
    String currency = "currency_example"; // String | The currency symbol
    String type = "type_example"; // String | Address book type
    String address = "address_example"; // String | Address in currency format, it must be in address book
    String name = Main address; // String | Name of address book entry
    String tfa = "tfa_example"; // String | TFA code, required when TFA is enabled for current account
    try {
      Object result = apiInstance.privateAddToAddressBookGet(currency, type, address, name, tfa);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling WalletApi#privateAddToAddressBookGet");
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

<a name="privateCancelTransferByIdGet"></a>
# **privateCancelTransferByIdGet**
> Object privateCancelTransferByIdGet(currency, id, tfa)

Cancel transfer

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.WalletApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://www.deribit.com/api/v2");
    
    // Configure HTTP basic authorization: bearerAuth
    HttpBasicAuth bearerAuth = (HttpBasicAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setUsername("YOUR USERNAME");
    bearerAuth.setPassword("YOUR PASSWORD");

    WalletApi apiInstance = new WalletApi(defaultClient);
    String currency = "currency_example"; // String | The currency symbol
    Integer id = 12; // Integer | Id of transfer
    String tfa = "tfa_example"; // String | TFA code, required when TFA is enabled for current account
    try {
      Object result = apiInstance.privateCancelTransferByIdGet(currency, id, tfa);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling WalletApi#privateCancelTransferByIdGet");
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
 **id** | **Integer**| Id of transfer |
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
**200** | ok response |  -  |

<a name="privateCancelWithdrawalGet"></a>
# **privateCancelWithdrawalGet**
> Object privateCancelWithdrawalGet(currency, id)

Cancels withdrawal request

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.WalletApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://www.deribit.com/api/v2");
    
    // Configure HTTP basic authorization: bearerAuth
    HttpBasicAuth bearerAuth = (HttpBasicAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setUsername("YOUR USERNAME");
    bearerAuth.setPassword("YOUR PASSWORD");

    WalletApi apiInstance = new WalletApi(defaultClient);
    String currency = "currency_example"; // String | The currency symbol
    BigDecimal id = 1; // BigDecimal | The withdrawal id
    try {
      Object result = apiInstance.privateCancelWithdrawalGet(currency, id);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling WalletApi#privateCancelWithdrawalGet");
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
 **id** | **BigDecimal**| The withdrawal id |

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

<a name="privateCreateDepositAddressGet"></a>
# **privateCreateDepositAddressGet**
> Object privateCreateDepositAddressGet(currency)

Creates deposit address in currency

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.WalletApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://www.deribit.com/api/v2");
    
    // Configure HTTP basic authorization: bearerAuth
    HttpBasicAuth bearerAuth = (HttpBasicAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setUsername("YOUR USERNAME");
    bearerAuth.setPassword("YOUR PASSWORD");

    WalletApi apiInstance = new WalletApi(defaultClient);
    String currency = "currency_example"; // String | The currency symbol
    try {
      Object result = apiInstance.privateCreateDepositAddressGet(currency);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling WalletApi#privateCreateDepositAddressGet");
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
**200** |  |  -  |

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
import org.openapitools.client.api.WalletApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://www.deribit.com/api/v2");
    
    // Configure HTTP basic authorization: bearerAuth
    HttpBasicAuth bearerAuth = (HttpBasicAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setUsername("YOUR USERNAME");
    bearerAuth.setPassword("YOUR PASSWORD");

    WalletApi apiInstance = new WalletApi(defaultClient);
    String currency = "currency_example"; // String | The currency symbol
    String type = "type_example"; // String | Address book type
    try {
      Object result = apiInstance.privateGetAddressBookGet(currency, type);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling WalletApi#privateGetAddressBookGet");
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

<a name="privateGetCurrentDepositAddressGet"></a>
# **privateGetCurrentDepositAddressGet**
> Object privateGetCurrentDepositAddressGet(currency)

Retrieve deposit address for currency

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.WalletApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://www.deribit.com/api/v2");
    
    // Configure HTTP basic authorization: bearerAuth
    HttpBasicAuth bearerAuth = (HttpBasicAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setUsername("YOUR USERNAME");
    bearerAuth.setPassword("YOUR PASSWORD");

    WalletApi apiInstance = new WalletApi(defaultClient);
    String currency = "currency_example"; // String | The currency symbol
    try {
      Object result = apiInstance.privateGetCurrentDepositAddressGet(currency);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling WalletApi#privateGetCurrentDepositAddressGet");
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
**200** |  |  -  |

<a name="privateGetDepositsGet"></a>
# **privateGetDepositsGet**
> Object privateGetDepositsGet(currency, count, offset)

Retrieve the latest users deposits

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.WalletApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://www.deribit.com/api/v2");
    
    // Configure HTTP basic authorization: bearerAuth
    HttpBasicAuth bearerAuth = (HttpBasicAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setUsername("YOUR USERNAME");
    bearerAuth.setPassword("YOUR PASSWORD");

    WalletApi apiInstance = new WalletApi(defaultClient);
    String currency = "currency_example"; // String | The currency symbol
    Integer count = 56; // Integer | Number of requested items, default - `10`
    Integer offset = 10; // Integer | The offset for pagination, default - `0`
    try {
      Object result = apiInstance.privateGetDepositsGet(currency, count, offset);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling WalletApi#privateGetDepositsGet");
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
 **count** | **Integer**| Number of requested items, default - &#x60;10&#x60; | [optional]
 **offset** | **Integer**| The offset for pagination, default - &#x60;0&#x60; | [optional]

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

<a name="privateGetTransfersGet"></a>
# **privateGetTransfersGet**
> Object privateGetTransfersGet(currency, count, offset)

Adds new entry to address book of given type

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.WalletApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://www.deribit.com/api/v2");
    
    // Configure HTTP basic authorization: bearerAuth
    HttpBasicAuth bearerAuth = (HttpBasicAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setUsername("YOUR USERNAME");
    bearerAuth.setPassword("YOUR PASSWORD");

    WalletApi apiInstance = new WalletApi(defaultClient);
    String currency = "currency_example"; // String | The currency symbol
    Integer count = 56; // Integer | Number of requested items, default - `10`
    Integer offset = 10; // Integer | The offset for pagination, default - `0`
    try {
      Object result = apiInstance.privateGetTransfersGet(currency, count, offset);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling WalletApi#privateGetTransfersGet");
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
 **count** | **Integer**| Number of requested items, default - &#x60;10&#x60; | [optional]
 **offset** | **Integer**| The offset for pagination, default - &#x60;0&#x60; | [optional]

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

<a name="privateGetWithdrawalsGet"></a>
# **privateGetWithdrawalsGet**
> Object privateGetWithdrawalsGet(currency, count, offset)

Retrieve the latest users withdrawals

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.WalletApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://www.deribit.com/api/v2");
    
    // Configure HTTP basic authorization: bearerAuth
    HttpBasicAuth bearerAuth = (HttpBasicAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setUsername("YOUR USERNAME");
    bearerAuth.setPassword("YOUR PASSWORD");

    WalletApi apiInstance = new WalletApi(defaultClient);
    String currency = "currency_example"; // String | The currency symbol
    Integer count = 56; // Integer | Number of requested items, default - `10`
    Integer offset = 10; // Integer | The offset for pagination, default - `0`
    try {
      Object result = apiInstance.privateGetWithdrawalsGet(currency, count, offset);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling WalletApi#privateGetWithdrawalsGet");
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
 **count** | **Integer**| Number of requested items, default - &#x60;10&#x60; | [optional]
 **offset** | **Integer**| The offset for pagination, default - &#x60;0&#x60; | [optional]

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
import org.openapitools.client.api.WalletApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://www.deribit.com/api/v2");
    
    // Configure HTTP basic authorization: bearerAuth
    HttpBasicAuth bearerAuth = (HttpBasicAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setUsername("YOUR USERNAME");
    bearerAuth.setPassword("YOUR PASSWORD");

    WalletApi apiInstance = new WalletApi(defaultClient);
    String currency = "currency_example"; // String | The currency symbol
    String type = "type_example"; // String | Address book type
    String address = "address_example"; // String | Address in currency format, it must be in address book
    String tfa = "tfa_example"; // String | TFA code, required when TFA is enabled for current account
    try {
      Object result = apiInstance.privateRemoveFromAddressBookGet(currency, type, address, tfa);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling WalletApi#privateRemoveFromAddressBookGet");
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
import org.openapitools.client.api.WalletApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://www.deribit.com/api/v2");
    
    // Configure HTTP basic authorization: bearerAuth
    HttpBasicAuth bearerAuth = (HttpBasicAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setUsername("YOUR USERNAME");
    bearerAuth.setPassword("YOUR PASSWORD");

    WalletApi apiInstance = new WalletApi(defaultClient);
    String currency = "currency_example"; // String | The currency symbol
    BigDecimal amount = new BigDecimal(); // BigDecimal | Amount of funds to be transferred
    Integer destination = 1; // Integer | Id of destination subaccount
    try {
      Object result = apiInstance.privateSubmitTransferToSubaccountGet(currency, amount, destination);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling WalletApi#privateSubmitTransferToSubaccountGet");
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
import org.openapitools.client.api.WalletApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://www.deribit.com/api/v2");
    
    // Configure HTTP basic authorization: bearerAuth
    HttpBasicAuth bearerAuth = (HttpBasicAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setUsername("YOUR USERNAME");
    bearerAuth.setPassword("YOUR PASSWORD");

    WalletApi apiInstance = new WalletApi(defaultClient);
    String currency = "currency_example"; // String | The currency symbol
    BigDecimal amount = new BigDecimal(); // BigDecimal | Amount of funds to be transferred
    String destination = "destination_example"; // String | Destination address from address book
    String tfa = "tfa_example"; // String | TFA code, required when TFA is enabled for current account
    try {
      Object result = apiInstance.privateSubmitTransferToUserGet(currency, amount, destination, tfa);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling WalletApi#privateSubmitTransferToUserGet");
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
import org.openapitools.client.api.WalletApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://www.deribit.com/api/v2");
    
    // Configure HTTP basic authorization: bearerAuth
    HttpBasicAuth bearerAuth = (HttpBasicAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setUsername("YOUR USERNAME");
    bearerAuth.setPassword("YOUR PASSWORD");

    WalletApi apiInstance = new WalletApi(defaultClient);
    String currency = "currency_example"; // String | The currency symbol
    Boolean state = true; // Boolean | 
    try {
      Object result = apiInstance.privateToggleDepositAddressCreationGet(currency, state);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling WalletApi#privateToggleDepositAddressCreationGet");
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

<a name="privateWithdrawGet"></a>
# **privateWithdrawGet**
> Object privateWithdrawGet(currency, address, amount, priority, tfa)

Creates a new withdrawal request

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.WalletApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://www.deribit.com/api/v2");
    
    // Configure HTTP basic authorization: bearerAuth
    HttpBasicAuth bearerAuth = (HttpBasicAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setUsername("YOUR USERNAME");
    bearerAuth.setPassword("YOUR PASSWORD");

    WalletApi apiInstance = new WalletApi(defaultClient);
    String currency = "currency_example"; // String | The currency symbol
    String address = "address_example"; // String | Address in currency format, it must be in address book
    BigDecimal amount = new BigDecimal(); // BigDecimal | Amount of funds to be withdrawn
    String priority = "priority_example"; // String | Withdrawal priority, optional for BTC, default: `high`
    String tfa = "tfa_example"; // String | TFA code, required when TFA is enabled for current account
    try {
      Object result = apiInstance.privateWithdrawGet(currency, address, amount, priority, tfa);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling WalletApi#privateWithdrawGet");
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
 **address** | **String**| Address in currency format, it must be in address book |
 **amount** | **BigDecimal**| Amount of funds to be withdrawn |
 **priority** | **String**| Withdrawal priority, optional for BTC, default: &#x60;high&#x60; | [optional] [enum: insane, extreme_high, very_high, high, mid, low, very_low]
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

