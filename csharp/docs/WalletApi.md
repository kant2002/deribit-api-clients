# Org.OpenAPITools.Api.WalletApi

All URIs are relative to *https://www.deribit.com/api/v2*

Method | HTTP request | Description
------------- | ------------- | -------------
[**PrivateAddToAddressBookGet**](WalletApi.md#privateaddtoaddressbookget) | **GET** /private/add_to_address_book | Adds new entry to address book of given type
[**PrivateCancelTransferByIdGet**](WalletApi.md#privatecanceltransferbyidget) | **GET** /private/cancel_transfer_by_id | Cancel transfer
[**PrivateCancelWithdrawalGet**](WalletApi.md#privatecancelwithdrawalget) | **GET** /private/cancel_withdrawal | Cancels withdrawal request
[**PrivateCreateDepositAddressGet**](WalletApi.md#privatecreatedepositaddressget) | **GET** /private/create_deposit_address | Creates deposit address in currency
[**PrivateGetAddressBookGet**](WalletApi.md#privategetaddressbookget) | **GET** /private/get_address_book | Retrieves address book of given type
[**PrivateGetCurrentDepositAddressGet**](WalletApi.md#privategetcurrentdepositaddressget) | **GET** /private/get_current_deposit_address | Retrieve deposit address for currency
[**PrivateGetDepositsGet**](WalletApi.md#privategetdepositsget) | **GET** /private/get_deposits | Retrieve the latest users deposits
[**PrivateGetTransfersGet**](WalletApi.md#privategettransfersget) | **GET** /private/get_transfers | Adds new entry to address book of given type
[**PrivateGetWithdrawalsGet**](WalletApi.md#privategetwithdrawalsget) | **GET** /private/get_withdrawals | Retrieve the latest users withdrawals
[**PrivateRemoveFromAddressBookGet**](WalletApi.md#privateremovefromaddressbookget) | **GET** /private/remove_from_address_book | Adds new entry to address book of given type
[**PrivateSubmitTransferToSubaccountGet**](WalletApi.md#privatesubmittransfertosubaccountget) | **GET** /private/submit_transfer_to_subaccount | Transfer funds to a subaccount.
[**PrivateSubmitTransferToUserGet**](WalletApi.md#privatesubmittransfertouserget) | **GET** /private/submit_transfer_to_user | Transfer funds to a another user.
[**PrivateToggleDepositAddressCreationGet**](WalletApi.md#privatetoggledepositaddresscreationget) | **GET** /private/toggle_deposit_address_creation | Enable or disable deposit address creation
[**PrivateWithdrawGet**](WalletApi.md#privatewithdrawget) | **GET** /private/withdraw | Creates a new withdrawal request



## PrivateAddToAddressBookGet

> Object PrivateAddToAddressBookGet (string currency, string type, string address, string name, string tfa = null)

Adds new entry to address book of given type

### Example

```csharp
using System.Diagnostics;
using Org.OpenAPITools.Api;
using Org.OpenAPITools.Client;
using Org.OpenAPITools.Model;

namespace Example
{
    public class PrivateAddToAddressBookGetExample
    {
        public static void Main()
        {
            Configuration.Default.BasePath = "https://www.deribit.com/api/v2";
            // Configure HTTP basic authorization: bearerAuth
            Configuration.Default.Username = "YOUR_USERNAME";
            Configuration.Default.Password = "YOUR_PASSWORD";

            var apiInstance = new WalletApi(Configuration.Default);
            var currency = currency_example;  // string | The currency symbol
            var type = type_example;  // string | Address book type
            var address = address_example;  // string | Address in currency format, it must be in address book
            var name = Main address;  // string | Name of address book entry
            var tfa = tfa_example;  // string | TFA code, required when TFA is enabled for current account (optional) 

            try
            {
                // Adds new entry to address book of given type
                Object result = apiInstance.PrivateAddToAddressBookGet(currency, type, address, name, tfa);
                Debug.WriteLine(result);
            }
            catch (ApiException e)
            {
                Debug.Print("Exception when calling WalletApi.PrivateAddToAddressBookGet: " + e.Message );
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
 **type** | **string**| Address book type | 
 **address** | **string**| Address in currency format, it must be in address book | 
 **name** | **string**| Name of address book entry | 
 **tfa** | **string**| TFA code, required when TFA is enabled for current account | [optional] 

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


## PrivateCancelTransferByIdGet

> Object PrivateCancelTransferByIdGet (string currency, int? id, string tfa = null)

Cancel transfer

### Example

```csharp
using System.Diagnostics;
using Org.OpenAPITools.Api;
using Org.OpenAPITools.Client;
using Org.OpenAPITools.Model;

namespace Example
{
    public class PrivateCancelTransferByIdGetExample
    {
        public static void Main()
        {
            Configuration.Default.BasePath = "https://www.deribit.com/api/v2";
            // Configure HTTP basic authorization: bearerAuth
            Configuration.Default.Username = "YOUR_USERNAME";
            Configuration.Default.Password = "YOUR_PASSWORD";

            var apiInstance = new WalletApi(Configuration.Default);
            var currency = currency_example;  // string | The currency symbol
            var id = 12;  // int? | Id of transfer
            var tfa = tfa_example;  // string | TFA code, required when TFA is enabled for current account (optional) 

            try
            {
                // Cancel transfer
                Object result = apiInstance.PrivateCancelTransferByIdGet(currency, id, tfa);
                Debug.WriteLine(result);
            }
            catch (ApiException e)
            {
                Debug.Print("Exception when calling WalletApi.PrivateCancelTransferByIdGet: " + e.Message );
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
 **id** | **int?**| Id of transfer | 
 **tfa** | **string**| TFA code, required when TFA is enabled for current account | [optional] 

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


## PrivateCancelWithdrawalGet

> Object PrivateCancelWithdrawalGet (string currency, decimal? id)

Cancels withdrawal request

### Example

```csharp
using System.Diagnostics;
using Org.OpenAPITools.Api;
using Org.OpenAPITools.Client;
using Org.OpenAPITools.Model;

namespace Example
{
    public class PrivateCancelWithdrawalGetExample
    {
        public static void Main()
        {
            Configuration.Default.BasePath = "https://www.deribit.com/api/v2";
            // Configure HTTP basic authorization: bearerAuth
            Configuration.Default.Username = "YOUR_USERNAME";
            Configuration.Default.Password = "YOUR_PASSWORD";

            var apiInstance = new WalletApi(Configuration.Default);
            var currency = currency_example;  // string | The currency symbol
            var id = 1;  // decimal? | The withdrawal id

            try
            {
                // Cancels withdrawal request
                Object result = apiInstance.PrivateCancelWithdrawalGet(currency, id);
                Debug.WriteLine(result);
            }
            catch (ApiException e)
            {
                Debug.Print("Exception when calling WalletApi.PrivateCancelWithdrawalGet: " + e.Message );
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
 **id** | **decimal?**| The withdrawal id | 

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


## PrivateCreateDepositAddressGet

> Object PrivateCreateDepositAddressGet (string currency)

Creates deposit address in currency

### Example

```csharp
using System.Diagnostics;
using Org.OpenAPITools.Api;
using Org.OpenAPITools.Client;
using Org.OpenAPITools.Model;

namespace Example
{
    public class PrivateCreateDepositAddressGetExample
    {
        public static void Main()
        {
            Configuration.Default.BasePath = "https://www.deribit.com/api/v2";
            // Configure HTTP basic authorization: bearerAuth
            Configuration.Default.Username = "YOUR_USERNAME";
            Configuration.Default.Password = "YOUR_PASSWORD";

            var apiInstance = new WalletApi(Configuration.Default);
            var currency = currency_example;  // string | The currency symbol

            try
            {
                // Creates deposit address in currency
                Object result = apiInstance.PrivateCreateDepositAddressGet(currency);
                Debug.WriteLine(result);
            }
            catch (ApiException e)
            {
                Debug.Print("Exception when calling WalletApi.PrivateCreateDepositAddressGet: " + e.Message );
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


## PrivateGetAddressBookGet

> Object PrivateGetAddressBookGet (string currency, string type)

Retrieves address book of given type

### Example

```csharp
using System.Diagnostics;
using Org.OpenAPITools.Api;
using Org.OpenAPITools.Client;
using Org.OpenAPITools.Model;

namespace Example
{
    public class PrivateGetAddressBookGetExample
    {
        public static void Main()
        {
            Configuration.Default.BasePath = "https://www.deribit.com/api/v2";
            // Configure HTTP basic authorization: bearerAuth
            Configuration.Default.Username = "YOUR_USERNAME";
            Configuration.Default.Password = "YOUR_PASSWORD";

            var apiInstance = new WalletApi(Configuration.Default);
            var currency = currency_example;  // string | The currency symbol
            var type = type_example;  // string | Address book type

            try
            {
                // Retrieves address book of given type
                Object result = apiInstance.PrivateGetAddressBookGet(currency, type);
                Debug.WriteLine(result);
            }
            catch (ApiException e)
            {
                Debug.Print("Exception when calling WalletApi.PrivateGetAddressBookGet: " + e.Message );
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
 **type** | **string**| Address book type | 

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


## PrivateGetCurrentDepositAddressGet

> Object PrivateGetCurrentDepositAddressGet (string currency)

Retrieve deposit address for currency

### Example

```csharp
using System.Diagnostics;
using Org.OpenAPITools.Api;
using Org.OpenAPITools.Client;
using Org.OpenAPITools.Model;

namespace Example
{
    public class PrivateGetCurrentDepositAddressGetExample
    {
        public static void Main()
        {
            Configuration.Default.BasePath = "https://www.deribit.com/api/v2";
            // Configure HTTP basic authorization: bearerAuth
            Configuration.Default.Username = "YOUR_USERNAME";
            Configuration.Default.Password = "YOUR_PASSWORD";

            var apiInstance = new WalletApi(Configuration.Default);
            var currency = currency_example;  // string | The currency symbol

            try
            {
                // Retrieve deposit address for currency
                Object result = apiInstance.PrivateGetCurrentDepositAddressGet(currency);
                Debug.WriteLine(result);
            }
            catch (ApiException e)
            {
                Debug.Print("Exception when calling WalletApi.PrivateGetCurrentDepositAddressGet: " + e.Message );
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


## PrivateGetDepositsGet

> Object PrivateGetDepositsGet (string currency, int? count = null, int? offset = null)

Retrieve the latest users deposits

### Example

```csharp
using System.Diagnostics;
using Org.OpenAPITools.Api;
using Org.OpenAPITools.Client;
using Org.OpenAPITools.Model;

namespace Example
{
    public class PrivateGetDepositsGetExample
    {
        public static void Main()
        {
            Configuration.Default.BasePath = "https://www.deribit.com/api/v2";
            // Configure HTTP basic authorization: bearerAuth
            Configuration.Default.Username = "YOUR_USERNAME";
            Configuration.Default.Password = "YOUR_PASSWORD";

            var apiInstance = new WalletApi(Configuration.Default);
            var currency = currency_example;  // string | The currency symbol
            var count = 56;  // int? | Number of requested items, default - `10` (optional) 
            var offset = 10;  // int? | The offset for pagination, default - `0` (optional) 

            try
            {
                // Retrieve the latest users deposits
                Object result = apiInstance.PrivateGetDepositsGet(currency, count, offset);
                Debug.WriteLine(result);
            }
            catch (ApiException e)
            {
                Debug.Print("Exception when calling WalletApi.PrivateGetDepositsGet: " + e.Message );
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
 **count** | **int?**| Number of requested items, default - &#x60;10&#x60; | [optional] 
 **offset** | **int?**| The offset for pagination, default - &#x60;0&#x60; | [optional] 

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


## PrivateGetTransfersGet

> Object PrivateGetTransfersGet (string currency, int? count = null, int? offset = null)

Adds new entry to address book of given type

### Example

```csharp
using System.Diagnostics;
using Org.OpenAPITools.Api;
using Org.OpenAPITools.Client;
using Org.OpenAPITools.Model;

namespace Example
{
    public class PrivateGetTransfersGetExample
    {
        public static void Main()
        {
            Configuration.Default.BasePath = "https://www.deribit.com/api/v2";
            // Configure HTTP basic authorization: bearerAuth
            Configuration.Default.Username = "YOUR_USERNAME";
            Configuration.Default.Password = "YOUR_PASSWORD";

            var apiInstance = new WalletApi(Configuration.Default);
            var currency = currency_example;  // string | The currency symbol
            var count = 56;  // int? | Number of requested items, default - `10` (optional) 
            var offset = 10;  // int? | The offset for pagination, default - `0` (optional) 

            try
            {
                // Adds new entry to address book of given type
                Object result = apiInstance.PrivateGetTransfersGet(currency, count, offset);
                Debug.WriteLine(result);
            }
            catch (ApiException e)
            {
                Debug.Print("Exception when calling WalletApi.PrivateGetTransfersGet: " + e.Message );
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
 **count** | **int?**| Number of requested items, default - &#x60;10&#x60; | [optional] 
 **offset** | **int?**| The offset for pagination, default - &#x60;0&#x60; | [optional] 

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


## PrivateGetWithdrawalsGet

> Object PrivateGetWithdrawalsGet (string currency, int? count = null, int? offset = null)

Retrieve the latest users withdrawals

### Example

```csharp
using System.Diagnostics;
using Org.OpenAPITools.Api;
using Org.OpenAPITools.Client;
using Org.OpenAPITools.Model;

namespace Example
{
    public class PrivateGetWithdrawalsGetExample
    {
        public static void Main()
        {
            Configuration.Default.BasePath = "https://www.deribit.com/api/v2";
            // Configure HTTP basic authorization: bearerAuth
            Configuration.Default.Username = "YOUR_USERNAME";
            Configuration.Default.Password = "YOUR_PASSWORD";

            var apiInstance = new WalletApi(Configuration.Default);
            var currency = currency_example;  // string | The currency symbol
            var count = 56;  // int? | Number of requested items, default - `10` (optional) 
            var offset = 10;  // int? | The offset for pagination, default - `0` (optional) 

            try
            {
                // Retrieve the latest users withdrawals
                Object result = apiInstance.PrivateGetWithdrawalsGet(currency, count, offset);
                Debug.WriteLine(result);
            }
            catch (ApiException e)
            {
                Debug.Print("Exception when calling WalletApi.PrivateGetWithdrawalsGet: " + e.Message );
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
 **count** | **int?**| Number of requested items, default - &#x60;10&#x60; | [optional] 
 **offset** | **int?**| The offset for pagination, default - &#x60;0&#x60; | [optional] 

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


## PrivateRemoveFromAddressBookGet

> Object PrivateRemoveFromAddressBookGet (string currency, string type, string address, string tfa = null)

Adds new entry to address book of given type

### Example

```csharp
using System.Diagnostics;
using Org.OpenAPITools.Api;
using Org.OpenAPITools.Client;
using Org.OpenAPITools.Model;

namespace Example
{
    public class PrivateRemoveFromAddressBookGetExample
    {
        public static void Main()
        {
            Configuration.Default.BasePath = "https://www.deribit.com/api/v2";
            // Configure HTTP basic authorization: bearerAuth
            Configuration.Default.Username = "YOUR_USERNAME";
            Configuration.Default.Password = "YOUR_PASSWORD";

            var apiInstance = new WalletApi(Configuration.Default);
            var currency = currency_example;  // string | The currency symbol
            var type = type_example;  // string | Address book type
            var address = address_example;  // string | Address in currency format, it must be in address book
            var tfa = tfa_example;  // string | TFA code, required when TFA is enabled for current account (optional) 

            try
            {
                // Adds new entry to address book of given type
                Object result = apiInstance.PrivateRemoveFromAddressBookGet(currency, type, address, tfa);
                Debug.WriteLine(result);
            }
            catch (ApiException e)
            {
                Debug.Print("Exception when calling WalletApi.PrivateRemoveFromAddressBookGet: " + e.Message );
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
 **type** | **string**| Address book type | 
 **address** | **string**| Address in currency format, it must be in address book | 
 **tfa** | **string**| TFA code, required when TFA is enabled for current account | [optional] 

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


## PrivateSubmitTransferToSubaccountGet

> Object PrivateSubmitTransferToSubaccountGet (string currency, decimal? amount, int? destination)

Transfer funds to a subaccount.

### Example

```csharp
using System.Diagnostics;
using Org.OpenAPITools.Api;
using Org.OpenAPITools.Client;
using Org.OpenAPITools.Model;

namespace Example
{
    public class PrivateSubmitTransferToSubaccountGetExample
    {
        public static void Main()
        {
            Configuration.Default.BasePath = "https://www.deribit.com/api/v2";
            // Configure HTTP basic authorization: bearerAuth
            Configuration.Default.Username = "YOUR_USERNAME";
            Configuration.Default.Password = "YOUR_PASSWORD";

            var apiInstance = new WalletApi(Configuration.Default);
            var currency = currency_example;  // string | The currency symbol
            var amount = 8.14;  // decimal? | Amount of funds to be transferred
            var destination = 1;  // int? | Id of destination subaccount

            try
            {
                // Transfer funds to a subaccount.
                Object result = apiInstance.PrivateSubmitTransferToSubaccountGet(currency, amount, destination);
                Debug.WriteLine(result);
            }
            catch (ApiException e)
            {
                Debug.Print("Exception when calling WalletApi.PrivateSubmitTransferToSubaccountGet: " + e.Message );
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
 **amount** | **decimal?**| Amount of funds to be transferred | 
 **destination** | **int?**| Id of destination subaccount | 

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


## PrivateSubmitTransferToUserGet

> Object PrivateSubmitTransferToUserGet (string currency, decimal? amount, string destination, string tfa = null)

Transfer funds to a another user.

### Example

```csharp
using System.Diagnostics;
using Org.OpenAPITools.Api;
using Org.OpenAPITools.Client;
using Org.OpenAPITools.Model;

namespace Example
{
    public class PrivateSubmitTransferToUserGetExample
    {
        public static void Main()
        {
            Configuration.Default.BasePath = "https://www.deribit.com/api/v2";
            // Configure HTTP basic authorization: bearerAuth
            Configuration.Default.Username = "YOUR_USERNAME";
            Configuration.Default.Password = "YOUR_PASSWORD";

            var apiInstance = new WalletApi(Configuration.Default);
            var currency = currency_example;  // string | The currency symbol
            var amount = 8.14;  // decimal? | Amount of funds to be transferred
            var destination = destination_example;  // string | Destination address from address book
            var tfa = tfa_example;  // string | TFA code, required when TFA is enabled for current account (optional) 

            try
            {
                // Transfer funds to a another user.
                Object result = apiInstance.PrivateSubmitTransferToUserGet(currency, amount, destination, tfa);
                Debug.WriteLine(result);
            }
            catch (ApiException e)
            {
                Debug.Print("Exception when calling WalletApi.PrivateSubmitTransferToUserGet: " + e.Message );
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
 **amount** | **decimal?**| Amount of funds to be transferred | 
 **destination** | **string**| Destination address from address book | 
 **tfa** | **string**| TFA code, required when TFA is enabled for current account | [optional] 

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


## PrivateToggleDepositAddressCreationGet

> Object PrivateToggleDepositAddressCreationGet (string currency, bool? state)

Enable or disable deposit address creation

### Example

```csharp
using System.Diagnostics;
using Org.OpenAPITools.Api;
using Org.OpenAPITools.Client;
using Org.OpenAPITools.Model;

namespace Example
{
    public class PrivateToggleDepositAddressCreationGetExample
    {
        public static void Main()
        {
            Configuration.Default.BasePath = "https://www.deribit.com/api/v2";
            // Configure HTTP basic authorization: bearerAuth
            Configuration.Default.Username = "YOUR_USERNAME";
            Configuration.Default.Password = "YOUR_PASSWORD";

            var apiInstance = new WalletApi(Configuration.Default);
            var currency = currency_example;  // string | The currency symbol
            var state = true;  // bool? | 

            try
            {
                // Enable or disable deposit address creation
                Object result = apiInstance.PrivateToggleDepositAddressCreationGet(currency, state);
                Debug.WriteLine(result);
            }
            catch (ApiException e)
            {
                Debug.Print("Exception when calling WalletApi.PrivateToggleDepositAddressCreationGet: " + e.Message );
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
 **state** | **bool?**|  | 

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


## PrivateWithdrawGet

> Object PrivateWithdrawGet (string currency, string address, decimal? amount, string priority = null, string tfa = null)

Creates a new withdrawal request

### Example

```csharp
using System.Diagnostics;
using Org.OpenAPITools.Api;
using Org.OpenAPITools.Client;
using Org.OpenAPITools.Model;

namespace Example
{
    public class PrivateWithdrawGetExample
    {
        public static void Main()
        {
            Configuration.Default.BasePath = "https://www.deribit.com/api/v2";
            // Configure HTTP basic authorization: bearerAuth
            Configuration.Default.Username = "YOUR_USERNAME";
            Configuration.Default.Password = "YOUR_PASSWORD";

            var apiInstance = new WalletApi(Configuration.Default);
            var currency = currency_example;  // string | The currency symbol
            var address = address_example;  // string | Address in currency format, it must be in address book
            var amount = 8.14;  // decimal? | Amount of funds to be withdrawn
            var priority = priority_example;  // string | Withdrawal priority, optional for BTC, default: `high` (optional) 
            var tfa = tfa_example;  // string | TFA code, required when TFA is enabled for current account (optional) 

            try
            {
                // Creates a new withdrawal request
                Object result = apiInstance.PrivateWithdrawGet(currency, address, amount, priority, tfa);
                Debug.WriteLine(result);
            }
            catch (ApiException e)
            {
                Debug.Print("Exception when calling WalletApi.PrivateWithdrawGet: " + e.Message );
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
 **address** | **string**| Address in currency format, it must be in address book | 
 **amount** | **decimal?**| Amount of funds to be withdrawn | 
 **priority** | **string**| Withdrawal priority, optional for BTC, default: &#x60;high&#x60; | [optional] 
 **tfa** | **string**| TFA code, required when TFA is enabled for current account | [optional] 

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

