# SubscriptionManagementApi

All URIs are relative to *https://www.deribit.com/api/v2*

Method | HTTP request | Description
------------- | ------------- | -------------
[**privateSubscribeGet**](SubscriptionManagementApi.md#privateSubscribeGet) | **GET** /private/subscribe | Subscribe to one or more channels.
[**privateUnsubscribeGet**](SubscriptionManagementApi.md#privateUnsubscribeGet) | **GET** /private/unsubscribe | Unsubscribe from one or more channels.
[**publicSubscribeGet**](SubscriptionManagementApi.md#publicSubscribeGet) | **GET** /public/subscribe | Subscribe to one or more channels.
[**publicUnsubscribeGet**](SubscriptionManagementApi.md#publicUnsubscribeGet) | **GET** /public/unsubscribe | Unsubscribe from one or more channels.



## privateSubscribeGet

> Object privateSubscribeGet(channels)

Subscribe to one or more channels.

Subscribe to one or more channels.  The name of the channel determines what information will be provided, and in what form. 

### Example

```java
// Import classes:
//import org.openapitools.client.api.SubscriptionManagementApi;

SubscriptionManagementApi apiInstance = new SubscriptionManagementApi();
List<String> channels = null; // List<String> | A list of channels to subscribe to.
try {
    Object result = apiInstance.privateSubscribeGet(channels);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling SubscriptionManagementApi#privateSubscribeGet");
    e.printStackTrace();
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **channels** | [**List&lt;String&gt;**](String.md)| A list of channels to subscribe to. | [default to null]

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## privateUnsubscribeGet

> Object privateUnsubscribeGet(channels)

Unsubscribe from one or more channels.

### Example

```java
// Import classes:
//import org.openapitools.client.api.SubscriptionManagementApi;

SubscriptionManagementApi apiInstance = new SubscriptionManagementApi();
List<String> channels = null; // List<String> | A list of channels to unsubscribe from.
try {
    Object result = apiInstance.privateUnsubscribeGet(channels);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling SubscriptionManagementApi#privateUnsubscribeGet");
    e.printStackTrace();
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **channels** | [**List&lt;String&gt;**](String.md)| A list of channels to unsubscribe from. | [default to null]

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## publicSubscribeGet

> Object publicSubscribeGet(channels)

Subscribe to one or more channels.

Subscribe to one or more channels.  This is the same method as [/private/subscribe](#private_subscribe), but it can only be used for &#39;public&#39; channels. 

### Example

```java
// Import classes:
//import org.openapitools.client.api.SubscriptionManagementApi;

SubscriptionManagementApi apiInstance = new SubscriptionManagementApi();
List<String> channels = null; // List<String> | A list of channels to subscribe to.
try {
    Object result = apiInstance.publicSubscribeGet(channels);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling SubscriptionManagementApi#publicSubscribeGet");
    e.printStackTrace();
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **channels** | [**List&lt;String&gt;**](String.md)| A list of channels to subscribe to. | [default to null]

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## publicUnsubscribeGet

> Object publicUnsubscribeGet(channels)

Unsubscribe from one or more channels.

### Example

```java
// Import classes:
//import org.openapitools.client.api.SubscriptionManagementApi;

SubscriptionManagementApi apiInstance = new SubscriptionManagementApi();
List<String> channels = null; // List<String> | A list of channels to unsubscribe from.
try {
    Object result = apiInstance.publicUnsubscribeGet(channels);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling SubscriptionManagementApi#publicUnsubscribeGet");
    e.printStackTrace();
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **channels** | [**List&lt;String&gt;**](String.md)| A list of channels to unsubscribe from. | [default to null]

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

