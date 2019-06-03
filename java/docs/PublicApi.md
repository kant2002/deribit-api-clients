# PublicApi

All URIs are relative to *https://www.deribit.com/api/v2*

Method | HTTP request | Description
------------- | ------------- | -------------
[**publicAuthGet**](PublicApi.md#publicAuthGet) | **GET** /public/auth | Authenticate
[**publicGetAnnouncementsGet**](PublicApi.md#publicGetAnnouncementsGet) | **GET** /public/get_announcements | Retrieves announcements from the last 30 days.
[**publicGetBookSummaryByCurrencyGet**](PublicApi.md#publicGetBookSummaryByCurrencyGet) | **GET** /public/get_book_summary_by_currency | Retrieves the summary information such as open interest, 24h volume, etc. for all instruments for the currency (optionally filtered by kind).
[**publicGetBookSummaryByInstrumentGet**](PublicApi.md#publicGetBookSummaryByInstrumentGet) | **GET** /public/get_book_summary_by_instrument | Retrieves the summary information such as open interest, 24h volume, etc. for a specific instrument.
[**publicGetContractSizeGet**](PublicApi.md#publicGetContractSizeGet) | **GET** /public/get_contract_size | Retrieves contract size of provided instrument.
[**publicGetCurrenciesGet**](PublicApi.md#publicGetCurrenciesGet) | **GET** /public/get_currencies | Retrieves all cryptocurrencies supported by the API.
[**publicGetFundingChartDataGet**](PublicApi.md#publicGetFundingChartDataGet) | **GET** /public/get_funding_chart_data | Retrieve the latest user trades that have occurred for PERPETUAL instruments in a specific currency symbol and within given time range.
[**publicGetHistoricalVolatilityGet**](PublicApi.md#publicGetHistoricalVolatilityGet) | **GET** /public/get_historical_volatility | Provides information about historical volatility for given cryptocurrency.
[**publicGetIndexGet**](PublicApi.md#publicGetIndexGet) | **GET** /public/get_index | Retrieves the current index price for the instruments, for the selected currency.
[**publicGetInstrumentsGet**](PublicApi.md#publicGetInstrumentsGet) | **GET** /public/get_instruments | Retrieves available trading instruments. This method can be used to see which instruments are available for trading, or which instruments have existed historically.
[**publicGetLastSettlementsByCurrencyGet**](PublicApi.md#publicGetLastSettlementsByCurrencyGet) | **GET** /public/get_last_settlements_by_currency | Retrieves historical settlement, delivery and bankruptcy events coming from all instruments within given currency.
[**publicGetLastSettlementsByInstrumentGet**](PublicApi.md#publicGetLastSettlementsByInstrumentGet) | **GET** /public/get_last_settlements_by_instrument | Retrieves historical public settlement, delivery and bankruptcy events filtered by instrument name.
[**publicGetLastTradesByCurrencyAndTimeGet**](PublicApi.md#publicGetLastTradesByCurrencyAndTimeGet) | **GET** /public/get_last_trades_by_currency_and_time | Retrieve the latest trades that have occurred for instruments in a specific currency symbol and within given time range.
[**publicGetLastTradesByCurrencyGet**](PublicApi.md#publicGetLastTradesByCurrencyGet) | **GET** /public/get_last_trades_by_currency | Retrieve the latest trades that have occurred for instruments in a specific currency symbol.
[**publicGetLastTradesByInstrumentAndTimeGet**](PublicApi.md#publicGetLastTradesByInstrumentAndTimeGet) | **GET** /public/get_last_trades_by_instrument_and_time | Retrieve the latest trades that have occurred for a specific instrument and within given time range.
[**publicGetLastTradesByInstrumentGet**](PublicApi.md#publicGetLastTradesByInstrumentGet) | **GET** /public/get_last_trades_by_instrument | Retrieve the latest trades that have occurred for a specific instrument.
[**publicGetOrderBookGet**](PublicApi.md#publicGetOrderBookGet) | **GET** /public/get_order_book | Retrieves the order book, along with other market values for a given instrument.
[**publicGetTimeGet**](PublicApi.md#publicGetTimeGet) | **GET** /public/get_time | Retrieves the current time (in milliseconds). This API endpoint can be used to check the clock skew between your software and Deribit&#39;s systems.
[**publicGetTradeVolumesGet**](PublicApi.md#publicGetTradeVolumesGet) | **GET** /public/get_trade_volumes | Retrieves aggregated 24h trade volumes for different instrument types and currencies.
[**publicGetTradingviewChartDataGet**](PublicApi.md#publicGetTradingviewChartDataGet) | **GET** /public/get_tradingview_chart_data | Publicly available market data used to generate a TradingView candle chart.
[**publicTestGet**](PublicApi.md#publicTestGet) | **GET** /public/test | Tests the connection to the API server, and returns its version. You can use this to make sure the API is reachable, and matches the expected version.
[**publicTickerGet**](PublicApi.md#publicTickerGet) | **GET** /public/ticker | Get ticker for an instrument.
[**publicValidateFieldGet**](PublicApi.md#publicValidateFieldGet) | **GET** /public/validate_field | Method used to introduce the client software connected to Deribit platform over websocket. Provided data may have an impact on the maintained connection and will be collected for internal statistical purposes. In response, Deribit will also introduce itself.


<a name="publicAuthGet"></a>
# **publicAuthGet**
> Object publicAuthGet(grantType, username, password, clientId, clientSecret, refreshToken, timestamp, signature, nonce, state, scope)

Authenticate

Retrieve an Oauth access token, to be used for authentication of &#39;private&#39; requests.  Three methods of authentication are supported:  - &lt;code&gt;password&lt;/code&gt; - using email and and password as when logging on to the website - &lt;code&gt;client_credentials&lt;/code&gt; - using the access key and access secret that can be found on the API page on the website - &lt;code&gt;client_signature&lt;/code&gt; - using the access key that can be found on the API page on the website and user generated signature. The signature is calculated using some fields provided in the request, using formula described here [Deribit signature credentials](#additional-authorization-method-deribit-signature-credentials) - &lt;code&gt;refresh_token&lt;/code&gt; - using a refresh token that was received from an earlier invocation  The response will contain an access token, expiration period (number of seconds that the token is valid) and a refresh token that can be used to get a new set of tokens. 

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.PublicApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://www.deribit.com/api/v2");
    
    // Configure HTTP basic authorization: bearerAuth
    HttpBasicAuth bearerAuth = (HttpBasicAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setUsername("YOUR USERNAME");
    bearerAuth.setPassword("YOUR PASSWORD");

    PublicApi apiInstance = new PublicApi(defaultClient);
    String grantType = "grantType_example"; // String | Method of authentication
    String username = your_email@mail.com; // String | Required for grant type `password`
    String password = your_password; // String | Required for grant type `password`
    String clientId = "clientId_example"; // String | Required for grant type `client_credentials` and `client_signature`
    String clientSecret = "clientSecret_example"; // String | Required for grant type `client_credentials`
    String refreshToken = "refreshToken_example"; // String | Required for grant type `refresh_token`
    String timestamp = "timestamp_example"; // String | Required for grant type `client_signature`, provides time when request has been generated
    String signature = "signature_example"; // String | Required for grant type `client_signature`; it's a cryptographic signature calculated over provided fields using user **secret key**. The signature should be calculated as an HMAC (Hash-based Message Authentication Code) with `SHA256` hash algorithm
    String nonce = "nonce_example"; // String | Optional for grant type `client_signature`; delivers user generated initialization vector for the server token
    String state = "state_example"; // String | Will be passed back in the response
    String scope = connection; // String | Describes type of the access for assigned token, possible values: `connection`, `session`, `session:name`, `trade:[read, read_write, none]`, `wallet:[read, read_write, none]`, `account:[read, read_write, none]`, `expires:NUMBER` (token will expire after `NUMBER` of seconds).</BR></BR> **NOTICE:** Depending on choosing an authentication method (```grant type```) some scopes could be narrowed by the server. e.g. when ```grant_type = client_credentials``` and ```scope = wallet:read_write``` it's modified by the server as ```scope = wallet:read```
    try {
      Object result = apiInstance.publicAuthGet(grantType, username, password, clientId, clientSecret, refreshToken, timestamp, signature, nonce, state, scope);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling PublicApi#publicAuthGet");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **grantType** | **String**| Method of authentication | [enum: password, client_credentials, client_signature, refresh_token]
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

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | ok response |  -  |
**429** | over limit |  -  |

<a name="publicGetAnnouncementsGet"></a>
# **publicGetAnnouncementsGet**
> Object publicGetAnnouncementsGet()

Retrieves announcements from the last 30 days.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.PublicApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://www.deribit.com/api/v2");
    
    // Configure HTTP basic authorization: bearerAuth
    HttpBasicAuth bearerAuth = (HttpBasicAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setUsername("YOUR USERNAME");
    bearerAuth.setPassword("YOUR PASSWORD");

    PublicApi apiInstance = new PublicApi(defaultClient);
    try {
      Object result = apiInstance.publicGetAnnouncementsGet();
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling PublicApi#publicGetAnnouncementsGet");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
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

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** |  |  -  |

<a name="publicGetBookSummaryByCurrencyGet"></a>
# **publicGetBookSummaryByCurrencyGet**
> Object publicGetBookSummaryByCurrencyGet(currency, kind)

Retrieves the summary information such as open interest, 24h volume, etc. for all instruments for the currency (optionally filtered by kind).

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.PublicApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://www.deribit.com/api/v2");
    
    // Configure HTTP basic authorization: bearerAuth
    HttpBasicAuth bearerAuth = (HttpBasicAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setUsername("YOUR USERNAME");
    bearerAuth.setPassword("YOUR PASSWORD");

    PublicApi apiInstance = new PublicApi(defaultClient);
    String currency = "currency_example"; // String | The currency symbol
    String kind = "kind_example"; // String | Instrument kind, if not provided instruments of all kinds are considered
    try {
      Object result = apiInstance.publicGetBookSummaryByCurrencyGet(currency, kind);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling PublicApi#publicGetBookSummaryByCurrencyGet");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **String**| The currency symbol | [enum: BTC, ETH]
 **kind** | **String**| Instrument kind, if not provided instruments of all kinds are considered | [optional] [enum: future, option]

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** |  |  -  |

<a name="publicGetBookSummaryByInstrumentGet"></a>
# **publicGetBookSummaryByInstrumentGet**
> Object publicGetBookSummaryByInstrumentGet(instrumentName)

Retrieves the summary information such as open interest, 24h volume, etc. for a specific instrument.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.PublicApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://www.deribit.com/api/v2");
    
    // Configure HTTP basic authorization: bearerAuth
    HttpBasicAuth bearerAuth = (HttpBasicAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setUsername("YOUR USERNAME");
    bearerAuth.setPassword("YOUR PASSWORD");

    PublicApi apiInstance = new PublicApi(defaultClient);
    String instrumentName = BTC-PERPETUAL; // String | Instrument name
    try {
      Object result = apiInstance.publicGetBookSummaryByInstrumentGet(instrumentName);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling PublicApi#publicGetBookSummaryByInstrumentGet");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrumentName** | **String**| Instrument name |

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** |  |  -  |

<a name="publicGetContractSizeGet"></a>
# **publicGetContractSizeGet**
> Object publicGetContractSizeGet(instrumentName)

Retrieves contract size of provided instrument.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.PublicApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://www.deribit.com/api/v2");
    
    // Configure HTTP basic authorization: bearerAuth
    HttpBasicAuth bearerAuth = (HttpBasicAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setUsername("YOUR USERNAME");
    bearerAuth.setPassword("YOUR PASSWORD");

    PublicApi apiInstance = new PublicApi(defaultClient);
    String instrumentName = BTC-PERPETUAL; // String | Instrument name
    try {
      Object result = apiInstance.publicGetContractSizeGet(instrumentName);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling PublicApi#publicGetContractSizeGet");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrumentName** | **String**| Instrument name |

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | ok response |  -  |

<a name="publicGetCurrenciesGet"></a>
# **publicGetCurrenciesGet**
> Object publicGetCurrenciesGet()

Retrieves all cryptocurrencies supported by the API.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.PublicApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://www.deribit.com/api/v2");
    
    // Configure HTTP basic authorization: bearerAuth
    HttpBasicAuth bearerAuth = (HttpBasicAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setUsername("YOUR USERNAME");
    bearerAuth.setPassword("YOUR PASSWORD");

    PublicApi apiInstance = new PublicApi(defaultClient);
    try {
      Object result = apiInstance.publicGetCurrenciesGet();
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling PublicApi#publicGetCurrenciesGet");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
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

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | ok response |  -  |

<a name="publicGetFundingChartDataGet"></a>
# **publicGetFundingChartDataGet**
> Object publicGetFundingChartDataGet(instrumentName, length)

Retrieve the latest user trades that have occurred for PERPETUAL instruments in a specific currency symbol and within given time range.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.PublicApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://www.deribit.com/api/v2");
    
    // Configure HTTP basic authorization: bearerAuth
    HttpBasicAuth bearerAuth = (HttpBasicAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setUsername("YOUR USERNAME");
    bearerAuth.setPassword("YOUR PASSWORD");

    PublicApi apiInstance = new PublicApi(defaultClient);
    String instrumentName = BTC-PERPETUAL; // String | Instrument name
    String length = "length_example"; // String | Specifies time period. `8h` - 8 hours, `24h` - 24 hours
    try {
      Object result = apiInstance.publicGetFundingChartDataGet(instrumentName, length);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling PublicApi#publicGetFundingChartDataGet");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrumentName** | **String**| Instrument name |
 **length** | **String**| Specifies time period. &#x60;8h&#x60; - 8 hours, &#x60;24h&#x60; - 24 hours | [optional] [enum: 8h, 24h]

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** |  |  -  |

<a name="publicGetHistoricalVolatilityGet"></a>
# **publicGetHistoricalVolatilityGet**
> Object publicGetHistoricalVolatilityGet(currency)

Provides information about historical volatility for given cryptocurrency.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.PublicApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://www.deribit.com/api/v2");
    
    // Configure HTTP basic authorization: bearerAuth
    HttpBasicAuth bearerAuth = (HttpBasicAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setUsername("YOUR USERNAME");
    bearerAuth.setPassword("YOUR PASSWORD");

    PublicApi apiInstance = new PublicApi(defaultClient);
    String currency = "currency_example"; // String | The currency symbol
    try {
      Object result = apiInstance.publicGetHistoricalVolatilityGet(currency);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling PublicApi#publicGetHistoricalVolatilityGet");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **String**| The currency symbol | [enum: BTC, ETH]

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | ok response |  -  |

<a name="publicGetIndexGet"></a>
# **publicGetIndexGet**
> Object publicGetIndexGet(currency)

Retrieves the current index price for the instruments, for the selected currency.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.PublicApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://www.deribit.com/api/v2");
    
    // Configure HTTP basic authorization: bearerAuth
    HttpBasicAuth bearerAuth = (HttpBasicAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setUsername("YOUR USERNAME");
    bearerAuth.setPassword("YOUR PASSWORD");

    PublicApi apiInstance = new PublicApi(defaultClient);
    String currency = "currency_example"; // String | The currency symbol
    try {
      Object result = apiInstance.publicGetIndexGet(currency);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling PublicApi#publicGetIndexGet");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **String**| The currency symbol | [enum: BTC, ETH]

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** |  |  -  |

<a name="publicGetInstrumentsGet"></a>
# **publicGetInstrumentsGet**
> Object publicGetInstrumentsGet(currency, kind, expired)

Retrieves available trading instruments. This method can be used to see which instruments are available for trading, or which instruments have existed historically.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.PublicApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://www.deribit.com/api/v2");
    
    // Configure HTTP basic authorization: bearerAuth
    HttpBasicAuth bearerAuth = (HttpBasicAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setUsername("YOUR USERNAME");
    bearerAuth.setPassword("YOUR PASSWORD");

    PublicApi apiInstance = new PublicApi(defaultClient);
    String currency = "currency_example"; // String | The currency symbol
    String kind = "kind_example"; // String | Instrument kind, if not provided instruments of all kinds are considered
    Boolean expired = false; // Boolean | Set to true to show expired instruments instead of active ones.
    try {
      Object result = apiInstance.publicGetInstrumentsGet(currency, kind, expired);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling PublicApi#publicGetInstrumentsGet");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **String**| The currency symbol | [enum: BTC, ETH]
 **kind** | **String**| Instrument kind, if not provided instruments of all kinds are considered | [optional] [enum: future, option]
 **expired** | **Boolean**| Set to true to show expired instruments instead of active ones. | [optional] [default to false]

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | ok response |  -  |

<a name="publicGetLastSettlementsByCurrencyGet"></a>
# **publicGetLastSettlementsByCurrencyGet**
> Object publicGetLastSettlementsByCurrencyGet(currency, type, count, continuation, searchStartTimestamp)

Retrieves historical settlement, delivery and bankruptcy events coming from all instruments within given currency.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.PublicApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://www.deribit.com/api/v2");
    
    // Configure HTTP basic authorization: bearerAuth
    HttpBasicAuth bearerAuth = (HttpBasicAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setUsername("YOUR USERNAME");
    bearerAuth.setPassword("YOUR PASSWORD");

    PublicApi apiInstance = new PublicApi(defaultClient);
    String currency = "currency_example"; // String | The currency symbol
    String type = "type_example"; // String | Settlement type
    Integer count = 56; // Integer | Number of requested items, default - `20`
    String continuation = xY7T6cutS3t2B9YtaDkE6TS379oKnkzTvmEDUnEUP2Msa9xKWNNaT; // String | Continuation token for pagination
    Integer searchStartTimestamp = 1536569522277; // Integer | The latest timestamp to return result for
    try {
      Object result = apiInstance.publicGetLastSettlementsByCurrencyGet(currency, type, count, continuation, searchStartTimestamp);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling PublicApi#publicGetLastSettlementsByCurrencyGet");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **String**| The currency symbol | [enum: BTC, ETH]
 **type** | **String**| Settlement type | [optional] [enum: settlement, delivery, bankruptcy]
 **count** | **Integer**| Number of requested items, default - &#x60;20&#x60; | [optional]
 **continuation** | **String**| Continuation token for pagination | [optional]
 **searchStartTimestamp** | **Integer**| The latest timestamp to return result for | [optional]

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** |  |  -  |

<a name="publicGetLastSettlementsByInstrumentGet"></a>
# **publicGetLastSettlementsByInstrumentGet**
> Object publicGetLastSettlementsByInstrumentGet(instrumentName, type, count, continuation, searchStartTimestamp)

Retrieves historical public settlement, delivery and bankruptcy events filtered by instrument name.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.PublicApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://www.deribit.com/api/v2");
    
    // Configure HTTP basic authorization: bearerAuth
    HttpBasicAuth bearerAuth = (HttpBasicAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setUsername("YOUR USERNAME");
    bearerAuth.setPassword("YOUR PASSWORD");

    PublicApi apiInstance = new PublicApi(defaultClient);
    String instrumentName = BTC-PERPETUAL; // String | Instrument name
    String type = "type_example"; // String | Settlement type
    Integer count = 56; // Integer | Number of requested items, default - `20`
    String continuation = xY7T6cutS3t2B9YtaDkE6TS379oKnkzTvmEDUnEUP2Msa9xKWNNaT; // String | Continuation token for pagination
    Integer searchStartTimestamp = 1536569522277; // Integer | The latest timestamp to return result for
    try {
      Object result = apiInstance.publicGetLastSettlementsByInstrumentGet(instrumentName, type, count, continuation, searchStartTimestamp);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling PublicApi#publicGetLastSettlementsByInstrumentGet");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrumentName** | **String**| Instrument name |
 **type** | **String**| Settlement type | [optional] [enum: settlement, delivery, bankruptcy]
 **count** | **Integer**| Number of requested items, default - &#x60;20&#x60; | [optional]
 **continuation** | **String**| Continuation token for pagination | [optional]
 **searchStartTimestamp** | **Integer**| The latest timestamp to return result for | [optional]

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** |  |  -  |

<a name="publicGetLastTradesByCurrencyAndTimeGet"></a>
# **publicGetLastTradesByCurrencyAndTimeGet**
> Object publicGetLastTradesByCurrencyAndTimeGet(currency, startTimestamp, endTimestamp, kind, count, includeOld, sorting)

Retrieve the latest trades that have occurred for instruments in a specific currency symbol and within given time range.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.PublicApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://www.deribit.com/api/v2");
    
    // Configure HTTP basic authorization: bearerAuth
    HttpBasicAuth bearerAuth = (HttpBasicAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setUsername("YOUR USERNAME");
    bearerAuth.setPassword("YOUR PASSWORD");

    PublicApi apiInstance = new PublicApi(defaultClient);
    String currency = "currency_example"; // String | The currency symbol
    Integer startTimestamp = 1536569522277; // Integer | The earliest timestamp to return result for
    Integer endTimestamp = 1536569522277; // Integer | The most recent timestamp to return result for
    String kind = "kind_example"; // String | Instrument kind, if not provided instruments of all kinds are considered
    Integer count = 56; // Integer | Number of requested items, default - `10`
    Boolean includeOld = true; // Boolean | Include trades older than 7 days, default - `false`
    String sorting = "sorting_example"; // String | Direction of results sorting (`default` value means no sorting, results will be returned in order in which they left the database)
    try {
      Object result = apiInstance.publicGetLastTradesByCurrencyAndTimeGet(currency, startTimestamp, endTimestamp, kind, count, includeOld, sorting);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling PublicApi#publicGetLastTradesByCurrencyAndTimeGet");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **String**| The currency symbol | [enum: BTC, ETH]
 **startTimestamp** | **Integer**| The earliest timestamp to return result for |
 **endTimestamp** | **Integer**| The most recent timestamp to return result for |
 **kind** | **String**| Instrument kind, if not provided instruments of all kinds are considered | [optional] [enum: future, option]
 **count** | **Integer**| Number of requested items, default - &#x60;10&#x60; | [optional]
 **includeOld** | **Boolean**| Include trades older than 7 days, default - &#x60;false&#x60; | [optional]
 **sorting** | **String**| Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) | [optional] [enum: asc, desc, default]

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** |  |  -  |

<a name="publicGetLastTradesByCurrencyGet"></a>
# **publicGetLastTradesByCurrencyGet**
> Object publicGetLastTradesByCurrencyGet(currency, kind, startId, endId, count, includeOld, sorting)

Retrieve the latest trades that have occurred for instruments in a specific currency symbol.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.PublicApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://www.deribit.com/api/v2");
    
    // Configure HTTP basic authorization: bearerAuth
    HttpBasicAuth bearerAuth = (HttpBasicAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setUsername("YOUR USERNAME");
    bearerAuth.setPassword("YOUR PASSWORD");

    PublicApi apiInstance = new PublicApi(defaultClient);
    String currency = "currency_example"; // String | The currency symbol
    String kind = "kind_example"; // String | Instrument kind, if not provided instruments of all kinds are considered
    String startId = "startId_example"; // String | The ID number of the first trade to be returned
    String endId = "endId_example"; // String | The ID number of the last trade to be returned
    Integer count = 56; // Integer | Number of requested items, default - `10`
    Boolean includeOld = true; // Boolean | Include trades older than 7 days, default - `false`
    String sorting = "sorting_example"; // String | Direction of results sorting (`default` value means no sorting, results will be returned in order in which they left the database)
    try {
      Object result = apiInstance.publicGetLastTradesByCurrencyGet(currency, kind, startId, endId, count, includeOld, sorting);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling PublicApi#publicGetLastTradesByCurrencyGet");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **String**| The currency symbol | [enum: BTC, ETH]
 **kind** | **String**| Instrument kind, if not provided instruments of all kinds are considered | [optional] [enum: future, option]
 **startId** | **String**| The ID number of the first trade to be returned | [optional]
 **endId** | **String**| The ID number of the last trade to be returned | [optional]
 **count** | **Integer**| Number of requested items, default - &#x60;10&#x60; | [optional]
 **includeOld** | **Boolean**| Include trades older than 7 days, default - &#x60;false&#x60; | [optional]
 **sorting** | **String**| Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) | [optional] [enum: asc, desc, default]

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** |  |  -  |

<a name="publicGetLastTradesByInstrumentAndTimeGet"></a>
# **publicGetLastTradesByInstrumentAndTimeGet**
> Object publicGetLastTradesByInstrumentAndTimeGet(instrumentName, startTimestamp, endTimestamp, count, includeOld, sorting)

Retrieve the latest trades that have occurred for a specific instrument and within given time range.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.PublicApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://www.deribit.com/api/v2");
    
    // Configure HTTP basic authorization: bearerAuth
    HttpBasicAuth bearerAuth = (HttpBasicAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setUsername("YOUR USERNAME");
    bearerAuth.setPassword("YOUR PASSWORD");

    PublicApi apiInstance = new PublicApi(defaultClient);
    String instrumentName = BTC-PERPETUAL; // String | Instrument name
    Integer startTimestamp = 1536569522277; // Integer | The earliest timestamp to return result for
    Integer endTimestamp = 1536569522277; // Integer | The most recent timestamp to return result for
    Integer count = 56; // Integer | Number of requested items, default - `10`
    Boolean includeOld = true; // Boolean | Include trades older than 7 days, default - `false`
    String sorting = "sorting_example"; // String | Direction of results sorting (`default` value means no sorting, results will be returned in order in which they left the database)
    try {
      Object result = apiInstance.publicGetLastTradesByInstrumentAndTimeGet(instrumentName, startTimestamp, endTimestamp, count, includeOld, sorting);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling PublicApi#publicGetLastTradesByInstrumentAndTimeGet");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrumentName** | **String**| Instrument name |
 **startTimestamp** | **Integer**| The earliest timestamp to return result for |
 **endTimestamp** | **Integer**| The most recent timestamp to return result for |
 **count** | **Integer**| Number of requested items, default - &#x60;10&#x60; | [optional]
 **includeOld** | **Boolean**| Include trades older than 7 days, default - &#x60;false&#x60; | [optional]
 **sorting** | **String**| Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) | [optional] [enum: asc, desc, default]

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** |  |  -  |

<a name="publicGetLastTradesByInstrumentGet"></a>
# **publicGetLastTradesByInstrumentGet**
> Object publicGetLastTradesByInstrumentGet(instrumentName, startSeq, endSeq, count, includeOld, sorting)

Retrieve the latest trades that have occurred for a specific instrument.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.PublicApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://www.deribit.com/api/v2");
    
    // Configure HTTP basic authorization: bearerAuth
    HttpBasicAuth bearerAuth = (HttpBasicAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setUsername("YOUR USERNAME");
    bearerAuth.setPassword("YOUR PASSWORD");

    PublicApi apiInstance = new PublicApi(defaultClient);
    String instrumentName = BTC-PERPETUAL; // String | Instrument name
    Integer startSeq = 56; // Integer | The sequence number of the first trade to be returned
    Integer endSeq = 56; // Integer | The sequence number of the last trade to be returned
    Integer count = 56; // Integer | Number of requested items, default - `10`
    Boolean includeOld = true; // Boolean | Include trades older than 7 days, default - `false`
    String sorting = "sorting_example"; // String | Direction of results sorting (`default` value means no sorting, results will be returned in order in which they left the database)
    try {
      Object result = apiInstance.publicGetLastTradesByInstrumentGet(instrumentName, startSeq, endSeq, count, includeOld, sorting);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling PublicApi#publicGetLastTradesByInstrumentGet");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrumentName** | **String**| Instrument name |
 **startSeq** | **Integer**| The sequence number of the first trade to be returned | [optional]
 **endSeq** | **Integer**| The sequence number of the last trade to be returned | [optional]
 **count** | **Integer**| Number of requested items, default - &#x60;10&#x60; | [optional]
 **includeOld** | **Boolean**| Include trades older than 7 days, default - &#x60;false&#x60; | [optional]
 **sorting** | **String**| Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) | [optional] [enum: asc, desc, default]

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** |  |  -  |

<a name="publicGetOrderBookGet"></a>
# **publicGetOrderBookGet**
> Object publicGetOrderBookGet(instrumentName, depth)

Retrieves the order book, along with other market values for a given instrument.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.PublicApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://www.deribit.com/api/v2");
    
    // Configure HTTP basic authorization: bearerAuth
    HttpBasicAuth bearerAuth = (HttpBasicAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setUsername("YOUR USERNAME");
    bearerAuth.setPassword("YOUR PASSWORD");

    PublicApi apiInstance = new PublicApi(defaultClient);
    String instrumentName = "instrumentName_example"; // String | The instrument name for which to retrieve the order book, see [`getinstruments`](#getinstruments) to obtain instrument names.
    BigDecimal depth = new BigDecimal(); // BigDecimal | The number of entries to return for bids and asks.
    try {
      Object result = apiInstance.publicGetOrderBookGet(instrumentName, depth);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling PublicApi#publicGetOrderBookGet");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrumentName** | **String**| The instrument name for which to retrieve the order book, see [&#x60;getinstruments&#x60;](#getinstruments) to obtain instrument names. |
 **depth** | **BigDecimal**| The number of entries to return for bids and asks. | [optional]

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** |  |  -  |

<a name="publicGetTimeGet"></a>
# **publicGetTimeGet**
> Object publicGetTimeGet()

Retrieves the current time (in milliseconds). This API endpoint can be used to check the clock skew between your software and Deribit&#39;s systems.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.PublicApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://www.deribit.com/api/v2");
    
    // Configure HTTP basic authorization: bearerAuth
    HttpBasicAuth bearerAuth = (HttpBasicAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setUsername("YOUR USERNAME");
    bearerAuth.setPassword("YOUR PASSWORD");

    PublicApi apiInstance = new PublicApi(defaultClient);
    try {
      Object result = apiInstance.publicGetTimeGet();
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling PublicApi#publicGetTimeGet");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
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

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** |  |  -  |

<a name="publicGetTradeVolumesGet"></a>
# **publicGetTradeVolumesGet**
> Object publicGetTradeVolumesGet()

Retrieves aggregated 24h trade volumes for different instrument types and currencies.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.PublicApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://www.deribit.com/api/v2");
    
    // Configure HTTP basic authorization: bearerAuth
    HttpBasicAuth bearerAuth = (HttpBasicAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setUsername("YOUR USERNAME");
    bearerAuth.setPassword("YOUR PASSWORD");

    PublicApi apiInstance = new PublicApi(defaultClient);
    try {
      Object result = apiInstance.publicGetTradeVolumesGet();
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling PublicApi#publicGetTradeVolumesGet");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
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

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** |  |  -  |

<a name="publicGetTradingviewChartDataGet"></a>
# **publicGetTradingviewChartDataGet**
> Object publicGetTradingviewChartDataGet(instrumentName, startTimestamp, endTimestamp)

Publicly available market data used to generate a TradingView candle chart.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.PublicApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://www.deribit.com/api/v2");
    
    // Configure HTTP basic authorization: bearerAuth
    HttpBasicAuth bearerAuth = (HttpBasicAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setUsername("YOUR USERNAME");
    bearerAuth.setPassword("YOUR PASSWORD");

    PublicApi apiInstance = new PublicApi(defaultClient);
    String instrumentName = BTC-PERPETUAL; // String | Instrument name
    Integer startTimestamp = 1536569522277; // Integer | The earliest timestamp to return result for
    Integer endTimestamp = 1536569522277; // Integer | The most recent timestamp to return result for
    try {
      Object result = apiInstance.publicGetTradingviewChartDataGet(instrumentName, startTimestamp, endTimestamp);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling PublicApi#publicGetTradingviewChartDataGet");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrumentName** | **String**| Instrument name |
 **startTimestamp** | **Integer**| The earliest timestamp to return result for |
 **endTimestamp** | **Integer**| The most recent timestamp to return result for |

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** |  |  -  |

<a name="publicTestGet"></a>
# **publicTestGet**
> Object publicTestGet(expectedResult)

Tests the connection to the API server, and returns its version. You can use this to make sure the API is reachable, and matches the expected version.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.PublicApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://www.deribit.com/api/v2");
    
    // Configure HTTP basic authorization: bearerAuth
    HttpBasicAuth bearerAuth = (HttpBasicAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setUsername("YOUR USERNAME");
    bearerAuth.setPassword("YOUR PASSWORD");

    PublicApi apiInstance = new PublicApi(defaultClient);
    String expectedResult = "expectedResult_example"; // String | The value \"exception\" will trigger an error response. This may be useful for testing wrapper libraries.
    try {
      Object result = apiInstance.publicTestGet(expectedResult);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling PublicApi#publicTestGet");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **expectedResult** | **String**| The value \&quot;exception\&quot; will trigger an error response. This may be useful for testing wrapper libraries. | [optional] [enum: exception]

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** |  |  -  |

<a name="publicTickerGet"></a>
# **publicTickerGet**
> Object publicTickerGet(instrumentName)

Get ticker for an instrument.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.PublicApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://www.deribit.com/api/v2");
    
    // Configure HTTP basic authorization: bearerAuth
    HttpBasicAuth bearerAuth = (HttpBasicAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setUsername("YOUR USERNAME");
    bearerAuth.setPassword("YOUR PASSWORD");

    PublicApi apiInstance = new PublicApi(defaultClient);
    String instrumentName = BTC-PERPETUAL; // String | Instrument name
    try {
      Object result = apiInstance.publicTickerGet(instrumentName);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling PublicApi#publicTickerGet");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrumentName** | **String**| Instrument name |

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | ok response |  -  |

<a name="publicValidateFieldGet"></a>
# **publicValidateFieldGet**
> Object publicValidateFieldGet(field, value, value2)

Method used to introduce the client software connected to Deribit platform over websocket. Provided data may have an impact on the maintained connection and will be collected for internal statistical purposes. In response, Deribit will also introduce itself.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.PublicApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://www.deribit.com/api/v2");
    
    // Configure HTTP basic authorization: bearerAuth
    HttpBasicAuth bearerAuth = (HttpBasicAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setUsername("YOUR USERNAME");
    bearerAuth.setPassword("YOUR PASSWORD");

    PublicApi apiInstance = new PublicApi(defaultClient);
    String field = "field_example"; // String | Name of the field to be validated, examples: postal_code, date_of_birth
    String value = "value_example"; // String | Value to be checked
    String value2 = "value2_example"; // String | Additional value to be compared with
    try {
      Object result = apiInstance.publicValidateFieldGet(field, value, value2);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling PublicApi#publicValidateFieldGet");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **field** | **String**| Name of the field to be validated, examples: postal_code, date_of_birth |
 **value** | **String**| Value to be checked |
 **value2** | **String**| Additional value to be compared with | [optional]

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** |  |  -  |

