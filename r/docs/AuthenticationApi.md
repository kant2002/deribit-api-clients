# AuthenticationApi

All URIs are relative to *https://www.deribit.com/api/v2*

Method | HTTP request | Description
------------- | ------------- | -------------
[**PublicAuthGet**](AuthenticationApi.md#PublicAuthGet) | **GET** /public/auth | Authenticate


# **PublicAuthGet**
> object PublicAuthGet(grant.type, username, password, client.id, client.secret, refresh.token, timestamp, signature, nonce=var.nonce, state=var.state, scope=var.scope)

Authenticate

Retrieve an Oauth access token, to be used for authentication of 'private' requests.  Three methods of authentication are supported:  - <code>password</code> - using email and and password as when logging on to the website - <code>client_credentials</code> - using the access key and access secret that can be found on the API page on the website - <code>client_signature</code> - using the access key that can be found on the API page on the website and user generated signature. The signature is calculated using some fields provided in the request, using formula described here [Deribit signature credentials](#additional-authorization-method-deribit-signature-credentials) - <code>refresh_token</code> - using a refresh token that was received from an earlier invocation  The response will contain an access token, expiration period (number of seconds that the token is valid) and a refresh token that can be used to get a new set of tokens. 

### Example
```R
library(openapi)

var.grant.type <- 'grant.type_example' # character | Method of authentication
var.username <- 'your_email@mail.com' # character | Required for grant type `password`
var.password <- 'your_password' # character | Required for grant type `password`
var.client.id <- 'client.id_example' # character | Required for grant type `client_credentials` and `client_signature`
var.client.secret <- 'client.secret_example' # character | Required for grant type `client_credentials`
var.refresh.token <- 'refresh.token_example' # character | Required for grant type `refresh_token`
var.timestamp <- 'timestamp_example' # character | Required for grant type `client_signature`, provides time when request has been generated
var.signature <- 'signature_example' # character | Required for grant type `client_signature`; it's a cryptographic signature calculated over provided fields using user **secret key**. The signature should be calculated as an HMAC (Hash-based Message Authentication Code) with `SHA256` hash algorithm
var.nonce <- 'nonce_example' # character | Optional for grant type `client_signature`; delivers user generated initialization vector for the server token
var.state <- 'state_example' # character | Will be passed back in the response
var.scope <- 'connection' # character | Describes type of the access for assigned token, possible values: `connection`, `session`, `session:name`, `trade:[read, read_write, none]`, `wallet:[read, read_write, none]`, `account:[read, read_write, none]`, `expires:NUMBER` (token will expire after `NUMBER` of seconds).</BR></BR> **NOTICE:** Depending on choosing an authentication method (```grant type```) some scopes could be narrowed by the server. e.g. when ```grant_type = client_credentials``` and ```scope = wallet:read_write``` it's modified by the server as ```scope = wallet:read```

#Authenticate
api.instance <- AuthenticationApi$new()
# Configure HTTP basic authorization: bearerAuth
api.instance$apiClient$username <- 'TODO_YOUR_USERNAME';
api.instance$apiClient$password <- 'TODO_YOUR_PASSWORD';
result <- api.instance$PublicAuthGet(var.grant.type, var.username, var.password, var.client.id, var.client.secret, var.refresh.token, var.timestamp, var.signature, nonce=var.nonce, state=var.state, scope=var.scope)
dput(result)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **grant.type** | **character**| Method of authentication | 
 **username** | **character**| Required for grant type &#x60;password&#x60; | 
 **password** | **character**| Required for grant type &#x60;password&#x60; | 
 **client.id** | **character**| Required for grant type &#x60;client_credentials&#x60; and &#x60;client_signature&#x60; | 
 **client.secret** | **character**| Required for grant type &#x60;client_credentials&#x60; | 
 **refresh.token** | **character**| Required for grant type &#x60;refresh_token&#x60; | 
 **timestamp** | **character**| Required for grant type &#x60;client_signature&#x60;, provides time when request has been generated | 
 **signature** | **character**| Required for grant type &#x60;client_signature&#x60;; it&#39;s a cryptographic signature calculated over provided fields using user **secret key**. The signature should be calculated as an HMAC (Hash-based Message Authentication Code) with &#x60;SHA256&#x60; hash algorithm | 
 **nonce** | **character**| Optional for grant type &#x60;client_signature&#x60;; delivers user generated initialization vector for the server token | [optional] 
 **state** | **character**| Will be passed back in the response | [optional] 
 **scope** | **character**| Describes type of the access for assigned token, possible values: &#x60;connection&#x60;, &#x60;session&#x60;, &#x60;session:name&#x60;, &#x60;trade:[read, read_write, none]&#x60;, &#x60;wallet:[read, read_write, none]&#x60;, &#x60;account:[read, read_write, none]&#x60;, &#x60;expires:NUMBER&#x60; (token will expire after &#x60;NUMBER&#x60; of seconds).&lt;/BR&gt;&lt;/BR&gt; **NOTICE:** Depending on choosing an authentication method (&#x60;&#x60;&#x60;grant type&#x60;&#x60;&#x60;) some scopes could be narrowed by the server. e.g. when &#x60;&#x60;&#x60;grant_type &#x3D; client_credentials&#x60;&#x60;&#x60; and &#x60;&#x60;&#x60;scope &#x3D; wallet:read_write&#x60;&#x60;&#x60; it&#39;s modified by the server as &#x60;&#x60;&#x60;scope &#x3D; wallet:read&#x60;&#x60;&#x60; | [optional] 

### Return type

**object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json



