# DeribitApi.AuthenticationApi

All URIs are relative to *https://www.deribit.com/api/v2*

Method | HTTP request | Description
------------- | ------------- | -------------
[**publicAuthGet**](AuthenticationApi.md#publicAuthGet) | **GET** /public/auth | Authenticate



## publicAuthGet

> Object publicAuthGet(grantType, username, password, clientId, clientSecret, refreshToken, timestamp, signature, opts)

Authenticate

Retrieve an Oauth access token, to be used for authentication of &#39;private&#39; requests.  Three methods of authentication are supported:  - &lt;code&gt;password&lt;/code&gt; - using email and and password as when logging on to the website - &lt;code&gt;client_credentials&lt;/code&gt; - using the access key and access secret that can be found on the API page on the website - &lt;code&gt;client_signature&lt;/code&gt; - using the access key that can be found on the API page on the website and user generated signature. The signature is calculated using some fields provided in the request, using formula described here [Deribit signature credentials](#additional-authorization-method-deribit-signature-credentials) - &lt;code&gt;refresh_token&lt;/code&gt; - using a refresh token that was received from an earlier invocation  The response will contain an access token, expiration period (number of seconds that the token is valid) and a refresh token that can be used to get a new set of tokens. 

### Example

```javascript
import DeribitApi from 'deribit_api';
let defaultClient = DeribitApi.ApiClient.instance;
// Configure Bearer (Auth. Token) access token for authorization: bearerAuth
let bearerAuth = defaultClient.authentications['bearerAuth'];
bearerAuth.accessToken = "YOUR ACCESS TOKEN"

let apiInstance = new DeribitApi.AuthenticationApi();
let grantType = "grantType_example"; // String | Method of authentication
let username = your_email@mail.com; // String | Required for grant type `password`
let password = your_password; // String | Required for grant type `password`
let clientId = "clientId_example"; // String | Required for grant type `client_credentials` and `client_signature`
let clientSecret = "clientSecret_example"; // String | Required for grant type `client_credentials`
let refreshToken = "refreshToken_example"; // String | Required for grant type `refresh_token`
let timestamp = "timestamp_example"; // String | Required for grant type `client_signature`, provides time when request has been generated
let signature = "signature_example"; // String | Required for grant type `client_signature`; it's a cryptographic signature calculated over provided fields using user **secret key**. The signature should be calculated as an HMAC (Hash-based Message Authentication Code) with `SHA256` hash algorithm
let opts = {
  'nonce': "nonce_example", // String | Optional for grant type `client_signature`; delivers user generated initialization vector for the server token
  'state': "state_example", // String | Will be passed back in the response
  'scope': connection // String | Describes type of the access for assigned token, possible values: `connection`, `session`, `session:name`, `trade:[read, read_write, none]`, `wallet:[read, read_write, none]`, `account:[read, read_write, none]`, `expires:NUMBER` (token will expire after `NUMBER` of seconds).</BR></BR> **NOTICE:** Depending on choosing an authentication method (```grant type```) some scopes could be narrowed by the server. e.g. when ```grant_type = client_credentials``` and ```scope = wallet:read_write``` it's modified by the server as ```scope = wallet:read```
};
apiInstance.publicAuthGet(grantType, username, password, clientId, clientSecret, refreshToken, timestamp, signature, opts, (error, data, response) => {
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
 **grantType** | **String**| Method of authentication | 
 **username** | **String**| Required for grant type &#x60;password&#x60; | 
 **password** | **String**| Required for grant type &#x60;password&#x60; | 
 **clientId** | **String**| Required for grant type &#x60;client_credentials&#x60; and &#x60;client_signature&#x60; | 
 **clientSecret** | **String**| Required for grant type &#x60;client_credentials&#x60; | 
 **refreshToken** | **String**| Required for grant type &#x60;refresh_token&#x60; | 
 **timestamp** | **String**| Required for grant type &#x60;client_signature&#x60;, provides time when request has been generated | 
 **signature** | **String**| Required for grant type &#x60;client_signature&#x60;; it&#39;s a cryptographic signature calculated over provided fields using user **secret key**. The signature should be calculated as an HMAC (Hash-based Message Authentication Code) with &#x60;SHA256&#x60; hash algorithm | 
 **nonce** | **String**| Optional for grant type &#x60;client_signature&#x60;; delivers user generated initialization vector for the server token | [optional] 
 **state** | **String**| Will be passed back in the response | [optional] 
 **scope** | **String**| Describes type of the access for assigned token, possible values: &#x60;connection&#x60;, &#x60;session&#x60;, &#x60;session:name&#x60;, &#x60;trade:[read, read_write, none]&#x60;, &#x60;wallet:[read, read_write, none]&#x60;, &#x60;account:[read, read_write, none]&#x60;, &#x60;expires:NUMBER&#x60; (token will expire after &#x60;NUMBER&#x60; of seconds).&lt;/BR&gt;&lt;/BR&gt; **NOTICE:** Depending on choosing an authentication method (&#x60;&#x60;&#x60;grant type&#x60;&#x60;&#x60;) some scopes could be narrowed by the server. e.g. when &#x60;&#x60;&#x60;grant_type &#x3D; client_credentials&#x60;&#x60;&#x60; and &#x60;&#x60;&#x60;scope &#x3D; wallet:read_write&#x60;&#x60;&#x60; it&#39;s modified by the server as &#x60;&#x60;&#x60;scope &#x3D; wallet:read&#x60;&#x60;&#x60; | [optional] 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

