# SubscriptionManagementApi

All URIs are relative to *https://www.deribit.com/api/v2*

Method | HTTP request | Description
------------- | ------------- | -------------
[**privateSubscribeGet**](SubscriptionManagementApi.md#privateSubscribeGet) | **GET** /private/subscribe | Subscribe to one or more channels.
[**privateUnsubscribeGet**](SubscriptionManagementApi.md#privateUnsubscribeGet) | **GET** /private/unsubscribe | Unsubscribe from one or more channels.
[**publicSubscribeGet**](SubscriptionManagementApi.md#publicSubscribeGet) | **GET** /public/subscribe | Subscribe to one or more channels.
[**publicUnsubscribeGet**](SubscriptionManagementApi.md#publicUnsubscribeGet) | **GET** /public/unsubscribe | Unsubscribe from one or more channels.


<a name="privateSubscribeGet"></a>
# **privateSubscribeGet**
> kotlin.Any privateSubscribeGet(channels)

Subscribe to one or more channels.

Subscribe to one or more channels.  The name of the channel determines what information will be provided, and in what form. 

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = SubscriptionManagementApi()
val channels : kotlin.Array<kotlin.String> =  // kotlin.Array<kotlin.String> | A list of channels to subscribe to.
try {
    val result : kotlin.Any = apiInstance.privateSubscribeGet(channels)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling SubscriptionManagementApi#privateSubscribeGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling SubscriptionManagementApi#privateSubscribeGet")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **channels** | [**kotlin.Array&lt;kotlin.String&gt;**](kotlin.String.md)| A list of channels to subscribe to. |

### Return type

[**kotlin.Any**](kotlin.Any.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="privateUnsubscribeGet"></a>
# **privateUnsubscribeGet**
> kotlin.Any privateUnsubscribeGet(channels)

Unsubscribe from one or more channels.

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = SubscriptionManagementApi()
val channels : kotlin.Array<kotlin.String> =  // kotlin.Array<kotlin.String> | A list of channels to unsubscribe from.
try {
    val result : kotlin.Any = apiInstance.privateUnsubscribeGet(channels)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling SubscriptionManagementApi#privateUnsubscribeGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling SubscriptionManagementApi#privateUnsubscribeGet")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **channels** | [**kotlin.Array&lt;kotlin.String&gt;**](kotlin.String.md)| A list of channels to unsubscribe from. |

### Return type

[**kotlin.Any**](kotlin.Any.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="publicSubscribeGet"></a>
# **publicSubscribeGet**
> kotlin.Any publicSubscribeGet(channels)

Subscribe to one or more channels.

Subscribe to one or more channels.  This is the same method as [/private/subscribe](#private_subscribe), but it can only be used for &#39;public&#39; channels. 

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = SubscriptionManagementApi()
val channels : kotlin.Array<kotlin.String> =  // kotlin.Array<kotlin.String> | A list of channels to subscribe to.
try {
    val result : kotlin.Any = apiInstance.publicSubscribeGet(channels)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling SubscriptionManagementApi#publicSubscribeGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling SubscriptionManagementApi#publicSubscribeGet")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **channels** | [**kotlin.Array&lt;kotlin.String&gt;**](kotlin.String.md)| A list of channels to subscribe to. |

### Return type

[**kotlin.Any**](kotlin.Any.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="publicUnsubscribeGet"></a>
# **publicUnsubscribeGet**
> kotlin.Any publicUnsubscribeGet(channels)

Unsubscribe from one or more channels.

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = SubscriptionManagementApi()
val channels : kotlin.Array<kotlin.String> =  // kotlin.Array<kotlin.String> | A list of channels to unsubscribe from.
try {
    val result : kotlin.Any = apiInstance.publicUnsubscribeGet(channels)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling SubscriptionManagementApi#publicUnsubscribeGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling SubscriptionManagementApi#publicUnsubscribeGet")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **channels** | [**kotlin.Array&lt;kotlin.String&gt;**](kotlin.String.md)| A list of channels to unsubscribe from. |

### Return type

[**kotlin.Any**](kotlin.Any.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

