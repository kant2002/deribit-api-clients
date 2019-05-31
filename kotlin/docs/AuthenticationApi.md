# AuthenticationApi

All URIs are relative to *https://www.deribit.com/api/v2*

Method | HTTP request | Description
------------- | ------------- | -------------
[**publicAuthGet**](AuthenticationApi.md#publicAuthGet) | **GET** /public/auth | Authenticate


<a name="publicAuthGet"></a>
# **publicAuthGet**
> kotlin.Any publicAuthGet(grantType, username, password, clientId, clientSecret, refreshToken, timestamp, signature, nonce, state, scope)

Authenticate

Retrieve an Oauth access token, to be used for authentication of &#39;private&#39; requests.  Three methods of authentication are supported:  - &lt;code&gt;password&lt;/code&gt; - using email and and password as when logging on to the website - &lt;code&gt;client_credentials&lt;/code&gt; - using the access key and access secret that can be found on the API page on the website - &lt;code&gt;client_signature&lt;/code&gt; - using the access key that can be found on the API page on the website and user generated signature. The signature is calculated using some fields provided in the request, using formula described here [Deribit signature credentials](#additional-authorization-method-deribit-signature-credentials) - &lt;code&gt;refresh_token&lt;/code&gt; - using a refresh token that was received from an earlier invocation  The response will contain an access token, expiration period (number of seconds that the token is valid) and a refresh token that can be used to get a new set of tokens. 

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = AuthenticationApi()
val grantType : kotlin.String = grantType_example // kotlin.String | Method of authentication
val username : kotlin.String = your_email@mail.com // kotlin.String | Required for grant type `password`
val password : kotlin.String = your_password // kotlin.String | Required for grant type `password`
val clientId : kotlin.String = clientId_example // kotlin.String | Required for grant type `client_credentials` and `client_signature`
val clientSecret : kotlin.String = clientSecret_example // kotlin.String | Required for grant type `client_credentials`
val refreshToken : kotlin.String = refreshToken_example // kotlin.String | Required for grant type `refresh_token`
val timestamp : kotlin.String = timestamp_example // kotlin.String | Required for grant type `client_signature`, provides time when request has been generated
val signature : kotlin.String = signature_example // kotlin.String | Required for grant type `client_signature`; it's a cryptographic signature calculated over provided fields using user **secret key**. The signature should be calculated as an HMAC (Hash-based Message Authentication Code) with `SHA256` hash algorithm
val nonce : kotlin.String = nonce_example // kotlin.String | Optional for grant type `client_signature`; delivers user generated initialization vector for the server token
val state : kotlin.String = state_example // kotlin.String | Will be passed back in the response
val scope : kotlin.String = connection // kotlin.String | Describes type of the access for assigned token, possible values: `connection`, `session`, `session:name`, `trade:[read, read_write, none]`, `wallet:[read, read_write, none]`, `account:[read, read_write, none]`, `expires:NUMBER` (token will expire after `NUMBER` of seconds).</BR></BR> **NOTICE:** Depending on choosing an authentication method (```grant type```) some scopes could be narrowed by the server. e.g. when ```grant_type = client_credentials``` and ```scope = wallet:read_write``` it's modified by the server as ```scope = wallet:read```
try {
    val result : kotlin.Any = apiInstance.publicAuthGet(grantType, username, password, clientId, clientSecret, refreshToken, timestamp, signature, nonce, state, scope)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling AuthenticationApi#publicAuthGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling AuthenticationApi#publicAuthGet")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **grantType** | **kotlin.String**| Method of authentication | [enum: password, client_credentials, client_signature, refresh_token]
 **username** | **kotlin.String**| Required for grant type &#x60;password&#x60; |
 **password** | **kotlin.String**| Required for grant type &#x60;password&#x60; |
 **clientId** | **kotlin.String**| Required for grant type &#x60;client_credentials&#x60; and &#x60;client_signature&#x60; |
 **clientSecret** | **kotlin.String**| Required for grant type &#x60;client_credentials&#x60; |
 **refreshToken** | **kotlin.String**| Required for grant type &#x60;refresh_token&#x60; |
 **timestamp** | **kotlin.String**| Required for grant type &#x60;client_signature&#x60;, provides time when request has been generated |
 **signature** | **kotlin.String**| Required for grant type &#x60;client_signature&#x60;; it&#39;s a cryptographic signature calculated over provided fields using user **secret key**. The signature should be calculated as an HMAC (Hash-based Message Authentication Code) with &#x60;SHA256&#x60; hash algorithm |
 **nonce** | **kotlin.String**| Optional for grant type &#x60;client_signature&#x60;; delivers user generated initialization vector for the server token | [optional]
 **state** | **kotlin.String**| Will be passed back in the response | [optional]
 **scope** | **kotlin.String**| Describes type of the access for assigned token, possible values: &#x60;connection&#x60;, &#x60;session&#x60;, &#x60;session:name&#x60;, &#x60;trade:[read, read_write, none]&#x60;, &#x60;wallet:[read, read_write, none]&#x60;, &#x60;account:[read, read_write, none]&#x60;, &#x60;expires:NUMBER&#x60; (token will expire after &#x60;NUMBER&#x60; of seconds).&lt;/BR&gt;&lt;/BR&gt; **NOTICE:** Depending on choosing an authentication method (&#x60;&#x60;&#x60;grant type&#x60;&#x60;&#x60;) some scopes could be narrowed by the server. e.g. when &#x60;&#x60;&#x60;grant_type &#x3D; client_credentials&#x60;&#x60;&#x60; and &#x60;&#x60;&#x60;scope &#x3D; wallet:read_write&#x60;&#x60;&#x60; it&#39;s modified by the server as &#x60;&#x60;&#x60;scope &#x3D; wallet:read&#x60;&#x60;&#x60; | [optional]

### Return type

[**kotlin.Any**](kotlin.Any.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

