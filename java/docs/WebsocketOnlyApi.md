# WebsocketOnlyApi

All URIs are relative to *https://www.deribit.com/api/v2*

Method | HTTP request | Description
------------- | ------------- | -------------
[**privateDisableCancelOnDisconnectGet**](WebsocketOnlyApi.md#privateDisableCancelOnDisconnectGet) | **GET** /private/disable_cancel_on_disconnect | Disable Cancel On Disconnect for the connection. This does not change the default account setting.
[**privateEnableCancelOnDisconnectGet**](WebsocketOnlyApi.md#privateEnableCancelOnDisconnectGet) | **GET** /private/enable_cancel_on_disconnect | Enable Cancel On Disconnect for the connection. This does not change the default account setting.
[**privateLogoutGet**](WebsocketOnlyApi.md#privateLogoutGet) | **GET** /private/logout | Gracefully close websocket connection, when COD (Cancel On Disconnect) is enabled orders are not cancelled
[**privateSubscribeGet**](WebsocketOnlyApi.md#privateSubscribeGet) | **GET** /private/subscribe | Subscribe to one or more channels.
[**privateUnsubscribeGet**](WebsocketOnlyApi.md#privateUnsubscribeGet) | **GET** /private/unsubscribe | Unsubscribe from one or more channels.
[**publicDisableHeartbeatGet**](WebsocketOnlyApi.md#publicDisableHeartbeatGet) | **GET** /public/disable_heartbeat | Stop sending heartbeat messages.
[**publicHelloGet**](WebsocketOnlyApi.md#publicHelloGet) | **GET** /public/hello | Method used to introduce the client software connected to Deribit platform over websocket. Provided data may have an impact on the maintained connection and will be collected for internal statistical purposes. In response, Deribit will also introduce itself.
[**publicSetHeartbeatGet**](WebsocketOnlyApi.md#publicSetHeartbeatGet) | **GET** /public/set_heartbeat | Signals the Websocket connection to send and request heartbeats. Heartbeats can be used to detect stale connections. When heartbeats have been set up, the API server will send &#x60;heartbeat&#x60; messages and &#x60;test_request&#x60; messages. Your software should respond to &#x60;test_request&#x60; messages by sending a &#x60;/api/v2/public/test&#x60; request. If your software fails to do so, the API server will immediately close the connection. If your account is configured to cancel on disconnect, any orders opened over the connection will be cancelled.
[**publicSubscribeGet**](WebsocketOnlyApi.md#publicSubscribeGet) | **GET** /public/subscribe | Subscribe to one or more channels.
[**publicUnsubscribeGet**](WebsocketOnlyApi.md#publicUnsubscribeGet) | **GET** /public/unsubscribe | Unsubscribe from one or more channels.


<a name="privateDisableCancelOnDisconnectGet"></a>
# **privateDisableCancelOnDisconnectGet**
> Object privateDisableCancelOnDisconnectGet()

Disable Cancel On Disconnect for the connection. This does not change the default account setting.

### Example
```java
// Import classes:
//import org.openapitools.client.ApiClient;
//import org.openapitools.client.ApiException;
//import org.openapitools.client.Configuration;
//import org.openapitools.client.auth.*;
//import org.openapitools.client.api.WebsocketOnlyApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure HTTP basic authorization: bearerAuth
HttpBasicAuth bearerAuth = (HttpBasicAuth) defaultClient.getAuthentication("bearerAuth");
bearerAuth.setUsername("YOUR USERNAME");
bearerAuth.setPassword("YOUR PASSWORD");

WebsocketOnlyApi apiInstance = new WebsocketOnlyApi();
try {
    Object result = apiInstance.privateDisableCancelOnDisconnectGet();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling WebsocketOnlyApi#privateDisableCancelOnDisconnectGet");
    e.printStackTrace();
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

<a name="privateEnableCancelOnDisconnectGet"></a>
# **privateEnableCancelOnDisconnectGet**
> Object privateEnableCancelOnDisconnectGet()

Enable Cancel On Disconnect for the connection. This does not change the default account setting.

### Example
```java
// Import classes:
//import org.openapitools.client.ApiClient;
//import org.openapitools.client.ApiException;
//import org.openapitools.client.Configuration;
//import org.openapitools.client.auth.*;
//import org.openapitools.client.api.WebsocketOnlyApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure HTTP basic authorization: bearerAuth
HttpBasicAuth bearerAuth = (HttpBasicAuth) defaultClient.getAuthentication("bearerAuth");
bearerAuth.setUsername("YOUR USERNAME");
bearerAuth.setPassword("YOUR PASSWORD");

WebsocketOnlyApi apiInstance = new WebsocketOnlyApi();
try {
    Object result = apiInstance.privateEnableCancelOnDisconnectGet();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling WebsocketOnlyApi#privateEnableCancelOnDisconnectGet");
    e.printStackTrace();
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

<a name="privateLogoutGet"></a>
# **privateLogoutGet**
> privateLogoutGet()

Gracefully close websocket connection, when COD (Cancel On Disconnect) is enabled orders are not cancelled

### Example
```java
// Import classes:
//import org.openapitools.client.ApiClient;
//import org.openapitools.client.ApiException;
//import org.openapitools.client.Configuration;
//import org.openapitools.client.auth.*;
//import org.openapitools.client.api.WebsocketOnlyApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure HTTP basic authorization: bearerAuth
HttpBasicAuth bearerAuth = (HttpBasicAuth) defaultClient.getAuthentication("bearerAuth");
bearerAuth.setUsername("YOUR USERNAME");
bearerAuth.setPassword("YOUR PASSWORD");

WebsocketOnlyApi apiInstance = new WebsocketOnlyApi();
try {
    apiInstance.privateLogoutGet();
} catch (ApiException e) {
    System.err.println("Exception when calling WebsocketOnlyApi#privateLogoutGet");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

null (empty response body)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="privateSubscribeGet"></a>
# **privateSubscribeGet**
> Object privateSubscribeGet(channels)

Subscribe to one or more channels.

Subscribe to one or more channels.  The name of the channel determines what information will be provided, and in what form. 

### Example
```java
// Import classes:
//import org.openapitools.client.ApiClient;
//import org.openapitools.client.ApiException;
//import org.openapitools.client.Configuration;
//import org.openapitools.client.auth.*;
//import org.openapitools.client.api.WebsocketOnlyApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure HTTP basic authorization: bearerAuth
HttpBasicAuth bearerAuth = (HttpBasicAuth) defaultClient.getAuthentication("bearerAuth");
bearerAuth.setUsername("YOUR USERNAME");
bearerAuth.setPassword("YOUR PASSWORD");

WebsocketOnlyApi apiInstance = new WebsocketOnlyApi();
List<String> channels = Arrays.asList(); // List<String> | A list of channels to subscribe to.
try {
    Object result = apiInstance.privateSubscribeGet(channels);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling WebsocketOnlyApi#privateSubscribeGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **channels** | [**List&lt;String&gt;**](String.md)| A list of channels to subscribe to. |

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="privateUnsubscribeGet"></a>
# **privateUnsubscribeGet**
> Object privateUnsubscribeGet(channels)

Unsubscribe from one or more channels.

### Example
```java
// Import classes:
//import org.openapitools.client.ApiClient;
//import org.openapitools.client.ApiException;
//import org.openapitools.client.Configuration;
//import org.openapitools.client.auth.*;
//import org.openapitools.client.api.WebsocketOnlyApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure HTTP basic authorization: bearerAuth
HttpBasicAuth bearerAuth = (HttpBasicAuth) defaultClient.getAuthentication("bearerAuth");
bearerAuth.setUsername("YOUR USERNAME");
bearerAuth.setPassword("YOUR PASSWORD");

WebsocketOnlyApi apiInstance = new WebsocketOnlyApi();
List<String> channels = Arrays.asList(); // List<String> | A list of channels to unsubscribe from.
try {
    Object result = apiInstance.privateUnsubscribeGet(channels);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling WebsocketOnlyApi#privateUnsubscribeGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **channels** | [**List&lt;String&gt;**](String.md)| A list of channels to unsubscribe from. |

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="publicDisableHeartbeatGet"></a>
# **publicDisableHeartbeatGet**
> Object publicDisableHeartbeatGet()

Stop sending heartbeat messages.

### Example
```java
// Import classes:
//import org.openapitools.client.ApiClient;
//import org.openapitools.client.ApiException;
//import org.openapitools.client.Configuration;
//import org.openapitools.client.auth.*;
//import org.openapitools.client.api.WebsocketOnlyApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure HTTP basic authorization: bearerAuth
HttpBasicAuth bearerAuth = (HttpBasicAuth) defaultClient.getAuthentication("bearerAuth");
bearerAuth.setUsername("YOUR USERNAME");
bearerAuth.setPassword("YOUR PASSWORD");

WebsocketOnlyApi apiInstance = new WebsocketOnlyApi();
try {
    Object result = apiInstance.publicDisableHeartbeatGet();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling WebsocketOnlyApi#publicDisableHeartbeatGet");
    e.printStackTrace();
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

<a name="publicHelloGet"></a>
# **publicHelloGet**
> Object publicHelloGet(clientName, clientVersion)

Method used to introduce the client software connected to Deribit platform over websocket. Provided data may have an impact on the maintained connection and will be collected for internal statistical purposes. In response, Deribit will also introduce itself.

### Example
```java
// Import classes:
//import org.openapitools.client.ApiClient;
//import org.openapitools.client.ApiException;
//import org.openapitools.client.Configuration;
//import org.openapitools.client.auth.*;
//import org.openapitools.client.api.WebsocketOnlyApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure HTTP basic authorization: bearerAuth
HttpBasicAuth bearerAuth = (HttpBasicAuth) defaultClient.getAuthentication("bearerAuth");
bearerAuth.setUsername("YOUR USERNAME");
bearerAuth.setPassword("YOUR PASSWORD");

WebsocketOnlyApi apiInstance = new WebsocketOnlyApi();
String clientName = My Trading Software; // String | Client software name
String clientVersion = 1.0.2; // String | Client software version
try {
    Object result = apiInstance.publicHelloGet(clientName, clientVersion);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling WebsocketOnlyApi#publicHelloGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **clientName** | **String**| Client software name |
 **clientVersion** | **String**| Client software version |

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="publicSetHeartbeatGet"></a>
# **publicSetHeartbeatGet**
> Object publicSetHeartbeatGet(interval)

Signals the Websocket connection to send and request heartbeats. Heartbeats can be used to detect stale connections. When heartbeats have been set up, the API server will send &#x60;heartbeat&#x60; messages and &#x60;test_request&#x60; messages. Your software should respond to &#x60;test_request&#x60; messages by sending a &#x60;/api/v2/public/test&#x60; request. If your software fails to do so, the API server will immediately close the connection. If your account is configured to cancel on disconnect, any orders opened over the connection will be cancelled.

### Example
```java
// Import classes:
//import org.openapitools.client.ApiClient;
//import org.openapitools.client.ApiException;
//import org.openapitools.client.Configuration;
//import org.openapitools.client.auth.*;
//import org.openapitools.client.api.WebsocketOnlyApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure HTTP basic authorization: bearerAuth
HttpBasicAuth bearerAuth = (HttpBasicAuth) defaultClient.getAuthentication("bearerAuth");
bearerAuth.setUsername("YOUR USERNAME");
bearerAuth.setPassword("YOUR PASSWORD");

WebsocketOnlyApi apiInstance = new WebsocketOnlyApi();
BigDecimal interval = 60; // BigDecimal | The heartbeat interval in seconds, but not less than 10
try {
    Object result = apiInstance.publicSetHeartbeatGet(interval);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling WebsocketOnlyApi#publicSetHeartbeatGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **interval** | **BigDecimal**| The heartbeat interval in seconds, but not less than 10 |

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="publicSubscribeGet"></a>
# **publicSubscribeGet**
> Object publicSubscribeGet(channels)

Subscribe to one or more channels.

Subscribe to one or more channels.  This is the same method as [/private/subscribe](#private_subscribe), but it can only be used for &#39;public&#39; channels. 

### Example
```java
// Import classes:
//import org.openapitools.client.ApiClient;
//import org.openapitools.client.ApiException;
//import org.openapitools.client.Configuration;
//import org.openapitools.client.auth.*;
//import org.openapitools.client.api.WebsocketOnlyApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure HTTP basic authorization: bearerAuth
HttpBasicAuth bearerAuth = (HttpBasicAuth) defaultClient.getAuthentication("bearerAuth");
bearerAuth.setUsername("YOUR USERNAME");
bearerAuth.setPassword("YOUR PASSWORD");

WebsocketOnlyApi apiInstance = new WebsocketOnlyApi();
List<String> channels = Arrays.asList(); // List<String> | A list of channels to subscribe to.
try {
    Object result = apiInstance.publicSubscribeGet(channels);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling WebsocketOnlyApi#publicSubscribeGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **channels** | [**List&lt;String&gt;**](String.md)| A list of channels to subscribe to. |

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="publicUnsubscribeGet"></a>
# **publicUnsubscribeGet**
> Object publicUnsubscribeGet(channels)

Unsubscribe from one or more channels.

### Example
```java
// Import classes:
//import org.openapitools.client.ApiClient;
//import org.openapitools.client.ApiException;
//import org.openapitools.client.Configuration;
//import org.openapitools.client.auth.*;
//import org.openapitools.client.api.WebsocketOnlyApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure HTTP basic authorization: bearerAuth
HttpBasicAuth bearerAuth = (HttpBasicAuth) defaultClient.getAuthentication("bearerAuth");
bearerAuth.setUsername("YOUR USERNAME");
bearerAuth.setPassword("YOUR PASSWORD");

WebsocketOnlyApi apiInstance = new WebsocketOnlyApi();
List<String> channels = Arrays.asList(); // List<String> | A list of channels to unsubscribe from.
try {
    Object result = apiInstance.publicUnsubscribeGet(channels);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling WebsocketOnlyApi#publicUnsubscribeGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **channels** | [**List&lt;String&gt;**](String.md)| A list of channels to unsubscribe from. |

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

