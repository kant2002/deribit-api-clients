# SubscriptionManagementApi

All URIs are relative to *https://www.deribit.com/api/v2*

Method | HTTP request | Description
------------- | ------------- | -------------
[**PrivateSubscribeGet**](SubscriptionManagementApi.md#PrivateSubscribeGet) | **GET** /private/subscribe | Subscribe to one or more channels.
[**PrivateUnsubscribeGet**](SubscriptionManagementApi.md#PrivateUnsubscribeGet) | **GET** /private/unsubscribe | Unsubscribe from one or more channels.
[**PublicSubscribeGet**](SubscriptionManagementApi.md#PublicSubscribeGet) | **GET** /public/subscribe | Subscribe to one or more channels.
[**PublicUnsubscribeGet**](SubscriptionManagementApi.md#PublicUnsubscribeGet) | **GET** /public/unsubscribe | Unsubscribe from one or more channels.


# **PrivateSubscribeGet**
> object PrivateSubscribeGet(channels)

Subscribe to one or more channels.

Subscribe to one or more channels.  The name of the channel determines what information will be provided, and in what form. 

### Example
```R
library(openapi)

var.channels <- list("inner_example") # character | A list of channels to subscribe to.

#Subscribe to one or more channels.
api.instance <- SubscriptionManagementApi$new()
# Configure HTTP basic authorization: bearerAuth
api.instance$apiClient$username <- 'TODO_YOUR_USERNAME';
api.instance$apiClient$password <- 'TODO_YOUR_PASSWORD';
result <- api.instance$PrivateSubscribeGet(var.channels)
dput(result)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **channels** | [**character**](character.md)| A list of channels to subscribe to. | 

### Return type

**object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json



# **PrivateUnsubscribeGet**
> object PrivateUnsubscribeGet(channels)

Unsubscribe from one or more channels.

### Example
```R
library(openapi)

var.channels <- list("inner_example") # character | A list of channels to unsubscribe from.

#Unsubscribe from one or more channels.
api.instance <- SubscriptionManagementApi$new()
# Configure HTTP basic authorization: bearerAuth
api.instance$apiClient$username <- 'TODO_YOUR_USERNAME';
api.instance$apiClient$password <- 'TODO_YOUR_PASSWORD';
result <- api.instance$PrivateUnsubscribeGet(var.channels)
dput(result)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **channels** | [**character**](character.md)| A list of channels to unsubscribe from. | 

### Return type

**object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json



# **PublicSubscribeGet**
> object PublicSubscribeGet(channels)

Subscribe to one or more channels.

Subscribe to one or more channels.  This is the same method as [/private/subscribe](#private_subscribe), but it can only be used for 'public' channels. 

### Example
```R
library(openapi)

var.channels <- list("inner_example") # character | A list of channels to subscribe to.

#Subscribe to one or more channels.
api.instance <- SubscriptionManagementApi$new()
# Configure HTTP basic authorization: bearerAuth
api.instance$apiClient$username <- 'TODO_YOUR_USERNAME';
api.instance$apiClient$password <- 'TODO_YOUR_PASSWORD';
result <- api.instance$PublicSubscribeGet(var.channels)
dput(result)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **channels** | [**character**](character.md)| A list of channels to subscribe to. | 

### Return type

**object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json



# **PublicUnsubscribeGet**
> object PublicUnsubscribeGet(channels)

Unsubscribe from one or more channels.

### Example
```R
library(openapi)

var.channels <- list("inner_example") # character | A list of channels to unsubscribe from.

#Unsubscribe from one or more channels.
api.instance <- SubscriptionManagementApi$new()
# Configure HTTP basic authorization: bearerAuth
api.instance$apiClient$username <- 'TODO_YOUR_USERNAME';
api.instance$apiClient$password <- 'TODO_YOUR_PASSWORD';
result <- api.instance$PublicUnsubscribeGet(var.channels)
dput(result)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **channels** | [**character**](character.md)| A list of channels to unsubscribe from. | 

### Return type

**object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json



