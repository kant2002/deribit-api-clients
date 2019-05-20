# DeribitApi.SubscriptionManagementApi

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

```javascript
import DeribitApi from 'deribit_api';
let defaultClient = DeribitApi.ApiClient.instance;
// Configure Bearer (Auth. Token) access token for authorization: bearerAuth
let bearerAuth = defaultClient.authentications['bearerAuth'];
bearerAuth.accessToken = "YOUR ACCESS TOKEN"

let apiInstance = new DeribitApi.SubscriptionManagementApi();
let channels = ["deribit_price_index.btc_usd"]; // [String] | A list of channels to subscribe to.
apiInstance.privateSubscribeGet(channels, (error, data, response) => {
  if (error) {
    console.error(error);
  } else {
    console.log('API called successfully. Returned data: ' + data);
  }
});
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **channels** | [**[String]**](String.md)| A list of channels to subscribe to. | 

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

```javascript
import DeribitApi from 'deribit_api';
let defaultClient = DeribitApi.ApiClient.instance;
// Configure Bearer (Auth. Token) access token for authorization: bearerAuth
let bearerAuth = defaultClient.authentications['bearerAuth'];
bearerAuth.accessToken = "YOUR ACCESS TOKEN"

let apiInstance = new DeribitApi.SubscriptionManagementApi();
let channels = ["deribit_price_index.btc_usd"]; // [String] | A list of channels to unsubscribe from.
apiInstance.privateUnsubscribeGet(channels, (error, data, response) => {
  if (error) {
    console.error(error);
  } else {
    console.log('API called successfully. Returned data: ' + data);
  }
});
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **channels** | [**[String]**](String.md)| A list of channels to unsubscribe from. | 

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

```javascript
import DeribitApi from 'deribit_api';
let defaultClient = DeribitApi.ApiClient.instance;
// Configure Bearer (Auth. Token) access token for authorization: bearerAuth
let bearerAuth = defaultClient.authentications['bearerAuth'];
bearerAuth.accessToken = "YOUR ACCESS TOKEN"

let apiInstance = new DeribitApi.SubscriptionManagementApi();
let channels = ["deribit_price_index.btc_usd"]; // [String] | A list of channels to subscribe to.
apiInstance.publicSubscribeGet(channels, (error, data, response) => {
  if (error) {
    console.error(error);
  } else {
    console.log('API called successfully. Returned data: ' + data);
  }
});
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **channels** | [**[String]**](String.md)| A list of channels to subscribe to. | 

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

```javascript
import DeribitApi from 'deribit_api';
let defaultClient = DeribitApi.ApiClient.instance;
// Configure Bearer (Auth. Token) access token for authorization: bearerAuth
let bearerAuth = defaultClient.authentications['bearerAuth'];
bearerAuth.accessToken = "YOUR ACCESS TOKEN"

let apiInstance = new DeribitApi.SubscriptionManagementApi();
let channels = ["deribit_price_index.btc_usd"]; // [String] | A list of channels to unsubscribe from.
apiInstance.publicUnsubscribeGet(channels, (error, data, response) => {
  if (error) {
    console.error(error);
  } else {
    console.log('API called successfully. Returned data: ' + data);
  }
});
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **channels** | [**[String]**](String.md)| A list of channels to unsubscribe from. | 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

