# Org.OpenAPITools.Api.AuthenticationApi

All URIs are relative to *https://www.deribit.com/api/v2*

Method | HTTP request | Description
------------- | ------------- | -------------
[**PublicAuthGet**](AuthenticationApi.md#publicauthget) | **GET** /public/auth | Authenticate



## PublicAuthGet

> Object PublicAuthGet (string grantType, string username, string password, string clientId, string clientSecret, string refreshToken, string timestamp, string signature, string nonce = null, string state = null, string scope = null)

Authenticate

Retrieve an Oauth access token, to be used for authentication of 'private' requests.  Three methods of authentication are supported:  - <code>password</code> - using email and and password as when logging on to the website - <code>client_credentials</code> - using the access key and access secret that can be found on the API page on the website - <code>client_signature</code> - using the access key that can be found on the API page on the website and user generated signature. The signature is calculated using some fields provided in the request, using formula described here [Deribit signature credentials](#additional-authorization-method-deribit-signature-credentials) - <code>refresh_token</code> - using a refresh token that was received from an earlier invocation  The response will contain an access token, expiration period (number of seconds that the token is valid) and a refresh token that can be used to get a new set of tokens. 

### Example

```csharp
using System.Diagnostics;
using Org.OpenAPITools.Api;
using Org.OpenAPITools.Client;
using Org.OpenAPITools.Model;

namespace Example
{
    public class PublicAuthGetExample
    {
        public static void Main()
        {
            Configuration.Default.BasePath = "https://www.deribit.com/api/v2";
            // Configure HTTP basic authorization: bearerAuth
            Configuration.Default.Username = "YOUR_USERNAME";
            Configuration.Default.Password = "YOUR_PASSWORD";

            var apiInstance = new AuthenticationApi(Configuration.Default);
            var grantType = grantType_example;  // string | Method of authentication
            var username = your_email@mail.com;  // string | Required for grant type `password`
            var password = your_password;  // string | Required for grant type `password`
            var clientId = clientId_example;  // string | Required for grant type `client_credentials` and `client_signature`
            var clientSecret = clientSecret_example;  // string | Required for grant type `client_credentials`
            var refreshToken = refreshToken_example;  // string | Required for grant type `refresh_token`
            var timestamp = timestamp_example;  // string | Required for grant type `client_signature`, provides time when request has been generated
            var signature = signature_example;  // string | Required for grant type `client_signature`; it's a cryptographic signature calculated over provided fields using user **secret key**. The signature should be calculated as an HMAC (Hash-based Message Authentication Code) with `SHA256` hash algorithm
            var nonce = nonce_example;  // string | Optional for grant type `client_signature`; delivers user generated initialization vector for the server token (optional) 
            var state = state_example;  // string | Will be passed back in the response (optional) 
            var scope = connection;  // string | Describes type of the access for assigned token, possible values: `connection`, `session`, `session:name`, `trade:[read, read_write, none]`, `wallet:[read, read_write, none]`, `account:[read, read_write, none]`, `expires:NUMBER` (token will expire after `NUMBER` of seconds).</BR></BR> **NOTICE:** Depending on choosing an authentication method (```grant type```) some scopes could be narrowed by the server. e.g. when ```grant_type = client_credentials``` and ```scope = wallet:read_write``` it's modified by the server as ```scope = wallet:read``` (optional) 

            try
            {
                // Authenticate
                Object result = apiInstance.PublicAuthGet(grantType, username, password, clientId, clientSecret, refreshToken, timestamp, signature, nonce, state, scope);
                Debug.WriteLine(result);
            }
            catch (ApiException e)
            {
                Debug.Print("Exception when calling AuthenticationApi.PublicAuthGet: " + e.Message );
                Debug.Print("Status Code: "+ e.ErrorCode);
                Debug.Print(e.StackTrace);
            }
        }
    }
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **grantType** | **string**| Method of authentication | 
 **username** | **string**| Required for grant type &#x60;password&#x60; | 
 **password** | **string**| Required for grant type &#x60;password&#x60; | 
 **clientId** | **string**| Required for grant type &#x60;client_credentials&#x60; and &#x60;client_signature&#x60; | 
 **clientSecret** | **string**| Required for grant type &#x60;client_credentials&#x60; | 
 **refreshToken** | **string**| Required for grant type &#x60;refresh_token&#x60; | 
 **timestamp** | **string**| Required for grant type &#x60;client_signature&#x60;, provides time when request has been generated | 
 **signature** | **string**| Required for grant type &#x60;client_signature&#x60;; it&#39;s a cryptographic signature calculated over provided fields using user **secret key**. The signature should be calculated as an HMAC (Hash-based Message Authentication Code) with &#x60;SHA256&#x60; hash algorithm | 
 **nonce** | **string**| Optional for grant type &#x60;client_signature&#x60;; delivers user generated initialization vector for the server token | [optional] 
 **state** | **string**| Will be passed back in the response | [optional] 
 **scope** | **string**| Describes type of the access for assigned token, possible values: &#x60;connection&#x60;, &#x60;session&#x60;, &#x60;session:name&#x60;, &#x60;trade:[read, read_write, none]&#x60;, &#x60;wallet:[read, read_write, none]&#x60;, &#x60;account:[read, read_write, none]&#x60;, &#x60;expires:NUMBER&#x60; (token will expire after &#x60;NUMBER&#x60; of seconds).&lt;/BR&gt;&lt;/BR&gt; **NOTICE:** Depending on choosing an authentication method (&#x60;&#x60;&#x60;grant type&#x60;&#x60;&#x60;) some scopes could be narrowed by the server. e.g. when &#x60;&#x60;&#x60;grant_type &#x3D; client_credentials&#x60;&#x60;&#x60; and &#x60;&#x60;&#x60;scope &#x3D; wallet:read_write&#x60;&#x60;&#x60; it&#39;s modified by the server as &#x60;&#x60;&#x60;scope &#x3D; wallet:read&#x60;&#x60;&#x60; | [optional] 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#)
[[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)

