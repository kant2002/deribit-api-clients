# Org.OpenAPITools.Api.SubscriptionManagementApi

All URIs are relative to *https://www.deribit.com/api/v2*

Method | HTTP request | Description
------------- | ------------- | -------------
[**PrivateSubscribeGet**](SubscriptionManagementApi.md#privatesubscribeget) | **GET** /private/subscribe | Subscribe to one or more channels.
[**PrivateUnsubscribeGet**](SubscriptionManagementApi.md#privateunsubscribeget) | **GET** /private/unsubscribe | Unsubscribe from one or more channels.
[**PublicSubscribeGet**](SubscriptionManagementApi.md#publicsubscribeget) | **GET** /public/subscribe | Subscribe to one or more channels.
[**PublicUnsubscribeGet**](SubscriptionManagementApi.md#publicunsubscribeget) | **GET** /public/unsubscribe | Unsubscribe from one or more channels.



## PrivateSubscribeGet

> Object PrivateSubscribeGet (List<string> channels)

Subscribe to one or more channels.

Subscribe to one or more channels.  The name of the channel determines what information will be provided, and in what form. 

### Example

```csharp
using System;
using System.Diagnostics;
using Org.OpenAPITools.Api;
using Org.OpenAPITools.Client;
using Org.OpenAPITools.Model;

namespace Example
{
    public class PrivateSubscribeGetExample
    {
        public void main()
        {
            // Configure HTTP basic authorization: bearerAuth
            Configuration.Default.Username = "YOUR_USERNAME";
            Configuration.Default.Password = "YOUR_PASSWORD";

            var apiInstance = new SubscriptionManagementApi();
            var channels = new List<string>(); // List<string> | A list of channels to subscribe to.

            try
            {
                // Subscribe to one or more channels.
                Object result = apiInstance.PrivateSubscribeGet(channels);
                Debug.WriteLine(result);
            }
            catch (Exception e)
            {
                Debug.Print("Exception when calling SubscriptionManagementApi.PrivateSubscribeGet: " + e.Message );
            }
        }
    }
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **channels** | [**List&lt;string&gt;**](string.md)| A list of channels to subscribe to. | 

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


## PrivateUnsubscribeGet

> Object PrivateUnsubscribeGet (List<string> channels)

Unsubscribe from one or more channels.

### Example

```csharp
using System;
using System.Diagnostics;
using Org.OpenAPITools.Api;
using Org.OpenAPITools.Client;
using Org.OpenAPITools.Model;

namespace Example
{
    public class PrivateUnsubscribeGetExample
    {
        public void main()
        {
            // Configure HTTP basic authorization: bearerAuth
            Configuration.Default.Username = "YOUR_USERNAME";
            Configuration.Default.Password = "YOUR_PASSWORD";

            var apiInstance = new SubscriptionManagementApi();
            var channels = new List<string>(); // List<string> | A list of channels to unsubscribe from.

            try
            {
                // Unsubscribe from one or more channels.
                Object result = apiInstance.PrivateUnsubscribeGet(channels);
                Debug.WriteLine(result);
            }
            catch (Exception e)
            {
                Debug.Print("Exception when calling SubscriptionManagementApi.PrivateUnsubscribeGet: " + e.Message );
            }
        }
    }
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **channels** | [**List&lt;string&gt;**](string.md)| A list of channels to unsubscribe from. | 

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


## PublicSubscribeGet

> Object PublicSubscribeGet (List<string> channels)

Subscribe to one or more channels.

Subscribe to one or more channels.  This is the same method as [/private/subscribe](#private_subscribe), but it can only be used for 'public' channels. 

### Example

```csharp
using System;
using System.Diagnostics;
using Org.OpenAPITools.Api;
using Org.OpenAPITools.Client;
using Org.OpenAPITools.Model;

namespace Example
{
    public class PublicSubscribeGetExample
    {
        public void main()
        {
            // Configure HTTP basic authorization: bearerAuth
            Configuration.Default.Username = "YOUR_USERNAME";
            Configuration.Default.Password = "YOUR_PASSWORD";

            var apiInstance = new SubscriptionManagementApi();
            var channels = new List<string>(); // List<string> | A list of channels to subscribe to.

            try
            {
                // Subscribe to one or more channels.
                Object result = apiInstance.PublicSubscribeGet(channels);
                Debug.WriteLine(result);
            }
            catch (Exception e)
            {
                Debug.Print("Exception when calling SubscriptionManagementApi.PublicSubscribeGet: " + e.Message );
            }
        }
    }
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **channels** | [**List&lt;string&gt;**](string.md)| A list of channels to subscribe to. | 

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


## PublicUnsubscribeGet

> Object PublicUnsubscribeGet (List<string> channels)

Unsubscribe from one or more channels.

### Example

```csharp
using System;
using System.Diagnostics;
using Org.OpenAPITools.Api;
using Org.OpenAPITools.Client;
using Org.OpenAPITools.Model;

namespace Example
{
    public class PublicUnsubscribeGetExample
    {
        public void main()
        {
            // Configure HTTP basic authorization: bearerAuth
            Configuration.Default.Username = "YOUR_USERNAME";
            Configuration.Default.Password = "YOUR_PASSWORD";

            var apiInstance = new SubscriptionManagementApi();
            var channels = new List<string>(); // List<string> | A list of channels to unsubscribe from.

            try
            {
                // Unsubscribe from one or more channels.
                Object result = apiInstance.PublicUnsubscribeGet(channels);
                Debug.WriteLine(result);
            }
            catch (Exception e)
            {
                Debug.Print("Exception when calling SubscriptionManagementApi.PublicUnsubscribeGet: " + e.Message );
            }
        }
    }
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **channels** | [**List&lt;string&gt;**](string.md)| A list of channels to unsubscribe from. | 

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

