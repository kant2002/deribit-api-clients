# DeribitApi.SupportingApi

All URIs are relative to *https://www.deribit.com/api/v2*

Method | HTTP request | Description
------------- | ------------- | -------------
[**publicGetTimeGet**](SupportingApi.md#publicGetTimeGet) | **GET** /public/get_time | Retrieves the current time (in milliseconds). This API endpoint can be used to check the clock skew between your software and Deribit&#39;s systems.
[**publicTestGet**](SupportingApi.md#publicTestGet) | **GET** /public/test | Tests the connection to the API server, and returns its version. You can use this to make sure the API is reachable, and matches the expected version.



## publicGetTimeGet

> Object publicGetTimeGet()

Retrieves the current time (in milliseconds). This API endpoint can be used to check the clock skew between your software and Deribit&#39;s systems.

### Example

```javascript
import DeribitApi from 'deribit_api';
let defaultClient = DeribitApi.ApiClient.instance;
// Configure Bearer (Auth. Token) access token for authorization: bearerAuth
let bearerAuth = defaultClient.authentications['bearerAuth'];
bearerAuth.accessToken = "YOUR ACCESS TOKEN"

let apiInstance = new DeribitApi.SupportingApi();
apiInstance.publicGetTimeGet((error, data, response) => {
  if (error) {
    console.error(error);
  } else {
    console.log('API called successfully. Returned data: ' + data);
  }
});
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


## publicTestGet

> Object publicTestGet(opts)

Tests the connection to the API server, and returns its version. You can use this to make sure the API is reachable, and matches the expected version.

### Example

```javascript
import DeribitApi from 'deribit_api';
let defaultClient = DeribitApi.ApiClient.instance;
// Configure Bearer (Auth. Token) access token for authorization: bearerAuth
let bearerAuth = defaultClient.authentications['bearerAuth'];
bearerAuth.accessToken = "YOUR ACCESS TOKEN"

let apiInstance = new DeribitApi.SupportingApi();
let opts = {
  'expectedResult': "expectedResult_example" // String | The value \"exception\" will trigger an error response. This may be useful for testing wrapper libraries.
};
apiInstance.publicTestGet(opts, (error, data, response) => {
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
 **expectedResult** | **String**| The value \&quot;exception\&quot; will trigger an error response. This may be useful for testing wrapper libraries. | [optional] 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

