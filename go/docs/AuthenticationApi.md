# \AuthenticationApi

All URIs are relative to *https://www.deribit.com/api/v2*

Method | HTTP request | Description
------------- | ------------- | -------------
[**PublicAuthGet**](AuthenticationApi.md#PublicAuthGet) | **Get** /public/auth | Authenticate



## PublicAuthGet

> map[string]interface{} PublicAuthGet(ctx, grantType, username, password, clientId, clientSecret, refreshToken, timestamp, signature, optional)
Authenticate

Retrieve an Oauth access token, to be used for authentication of 'private' requests.  Three methods of authentication are supported:  - <code>password</code> - using email and and password as when logging on to the website - <code>client_credentials</code> - using the access key and access secret that can be found on the API page on the website - <code>client_signature</code> - using the access key that can be found on the API page on the website and user generated signature. The signature is calculated using some fields provided in the request, using formula described here [Deribit signature credentials](#additional-authorization-method-deribit-signature-credentials) - <code>refresh_token</code> - using a refresh token that was received from an earlier invocation  The response will contain an access token, expiration period (number of seconds that the token is valid) and a refresh token that can be used to get a new set of tokens. 

### Required Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
**ctx** | **context.Context** | context for authentication, logging, cancellation, deadlines, tracing, etc.
**grantType** | **string**| Method of authentication | 
**username** | **string**| Required for grant type &#x60;password&#x60; | 
**password** | **string**| Required for grant type &#x60;password&#x60; | 
**clientId** | **string**| Required for grant type &#x60;client_credentials&#x60; and &#x60;client_signature&#x60; | 
**clientSecret** | **string**| Required for grant type &#x60;client_credentials&#x60; | 
**refreshToken** | **string**| Required for grant type &#x60;refresh_token&#x60; | 
**timestamp** | **string**| Required for grant type &#x60;client_signature&#x60;, provides time when request has been generated | 
**signature** | **string**| Required for grant type &#x60;client_signature&#x60;; it&#39;s a cryptographic signature calculated over provided fields using user **secret key**. The signature should be calculated as an HMAC (Hash-based Message Authentication Code) with &#x60;SHA256&#x60; hash algorithm | 
 **optional** | ***PublicAuthGetOpts** | optional parameters | nil if no parameters

### Optional Parameters

Optional parameters are passed through a pointer to a PublicAuthGetOpts struct


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------








 **nonce** | **optional.String**| Optional for grant type &#x60;client_signature&#x60;; delivers user generated initialization vector for the server token | 
 **state** | **optional.String**| Will be passed back in the response | 
 **scope** | **optional.String**| Describes type of the access for assigned token, possible values: &#x60;connection&#x60;, &#x60;session&#x60;, &#x60;session:name&#x60;, &#x60;trade:[read, read_write, none]&#x60;, &#x60;wallet:[read, read_write, none]&#x60;, &#x60;account:[read, read_write, none]&#x60;, &#x60;expires:NUMBER&#x60; (token will expire after &#x60;NUMBER&#x60; of seconds).&lt;/BR&gt;&lt;/BR&gt; **NOTICE:** Depending on choosing an authentication method (&#x60;&#x60;&#x60;grant type&#x60;&#x60;&#x60;) some scopes could be narrowed by the server. e.g. when &#x60;&#x60;&#x60;grant_type &#x3D; client_credentials&#x60;&#x60;&#x60; and &#x60;&#x60;&#x60;scope &#x3D; wallet:read_write&#x60;&#x60;&#x60; it&#39;s modified by the server as &#x60;&#x60;&#x60;scope &#x3D; wallet:read&#x60;&#x60;&#x60; | 

### Return type

[**map[string]interface{}**](map[string]interface{}.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)

