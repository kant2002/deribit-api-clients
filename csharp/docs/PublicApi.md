# Org.OpenAPITools.Api.PublicApi

All URIs are relative to *https://www.deribit.com/api/v2*

Method | HTTP request | Description
------------- | ------------- | -------------
[**PublicAuthGet**](PublicApi.md#publicauthget) | **GET** /public/auth | Authenticate
[**PublicGetAnnouncementsGet**](PublicApi.md#publicgetannouncementsget) | **GET** /public/get_announcements | Retrieves announcements from the last 30 days.
[**PublicGetBookSummaryByCurrencyGet**](PublicApi.md#publicgetbooksummarybycurrencyget) | **GET** /public/get_book_summary_by_currency | Retrieves the summary information such as open interest, 24h volume, etc. for all instruments for the currency (optionally filtered by kind).
[**PublicGetBookSummaryByInstrumentGet**](PublicApi.md#publicgetbooksummarybyinstrumentget) | **GET** /public/get_book_summary_by_instrument | Retrieves the summary information such as open interest, 24h volume, etc. for a specific instrument.
[**PublicGetContractSizeGet**](PublicApi.md#publicgetcontractsizeget) | **GET** /public/get_contract_size | Retrieves contract size of provided instrument.
[**PublicGetCurrenciesGet**](PublicApi.md#publicgetcurrenciesget) | **GET** /public/get_currencies | Retrieves all cryptocurrencies supported by the API.
[**PublicGetFundingChartDataGet**](PublicApi.md#publicgetfundingchartdataget) | **GET** /public/get_funding_chart_data | Retrieve the latest user trades that have occurred for PERPETUAL instruments in a specific currency symbol and within given time range.
[**PublicGetHistoricalVolatilityGet**](PublicApi.md#publicgethistoricalvolatilityget) | **GET** /public/get_historical_volatility | Provides information about historical volatility for given cryptocurrency.
[**PublicGetIndexGet**](PublicApi.md#publicgetindexget) | **GET** /public/get_index | Retrieves the current index price for the instruments, for the selected currency.
[**PublicGetInstrumentsGet**](PublicApi.md#publicgetinstrumentsget) | **GET** /public/get_instruments | Retrieves available trading instruments. This method can be used to see which instruments are available for trading, or which instruments have existed historically.
[**PublicGetLastSettlementsByCurrencyGet**](PublicApi.md#publicgetlastsettlementsbycurrencyget) | **GET** /public/get_last_settlements_by_currency | Retrieves historical settlement, delivery and bankruptcy events coming from all instruments within given currency.
[**PublicGetLastSettlementsByInstrumentGet**](PublicApi.md#publicgetlastsettlementsbyinstrumentget) | **GET** /public/get_last_settlements_by_instrument | Retrieves historical public settlement, delivery and bankruptcy events filtered by instrument name.
[**PublicGetLastTradesByCurrencyAndTimeGet**](PublicApi.md#publicgetlasttradesbycurrencyandtimeget) | **GET** /public/get_last_trades_by_currency_and_time | Retrieve the latest trades that have occurred for instruments in a specific currency symbol and within given time range.
[**PublicGetLastTradesByCurrencyGet**](PublicApi.md#publicgetlasttradesbycurrencyget) | **GET** /public/get_last_trades_by_currency | Retrieve the latest trades that have occurred for instruments in a specific currency symbol.
[**PublicGetLastTradesByInstrumentAndTimeGet**](PublicApi.md#publicgetlasttradesbyinstrumentandtimeget) | **GET** /public/get_last_trades_by_instrument_and_time | Retrieve the latest trades that have occurred for a specific instrument and within given time range.
[**PublicGetLastTradesByInstrumentGet**](PublicApi.md#publicgetlasttradesbyinstrumentget) | **GET** /public/get_last_trades_by_instrument | Retrieve the latest trades that have occurred for a specific instrument.
[**PublicGetOrderBookGet**](PublicApi.md#publicgetorderbookget) | **GET** /public/get_order_book | Retrieves the order book, along with other market values for a given instrument.
[**PublicGetTimeGet**](PublicApi.md#publicgettimeget) | **GET** /public/get_time | Retrieves the current time (in milliseconds). This API endpoint can be used to check the clock skew between your software and Deribit&#39;s systems.
[**PublicGetTradeVolumesGet**](PublicApi.md#publicgettradevolumesget) | **GET** /public/get_trade_volumes | Retrieves aggregated 24h trade volumes for different instrument types and currencies.
[**PublicGetTradingviewChartDataGet**](PublicApi.md#publicgettradingviewchartdataget) | **GET** /public/get_tradingview_chart_data | Publicly available market data used to generate a TradingView candle chart.
[**PublicTestGet**](PublicApi.md#publictestget) | **GET** /public/test | Tests the connection to the API server, and returns its version. You can use this to make sure the API is reachable, and matches the expected version.
[**PublicTickerGet**](PublicApi.md#publictickerget) | **GET** /public/ticker | Get ticker for an instrument.
[**PublicValidateFieldGet**](PublicApi.md#publicvalidatefieldget) | **GET** /public/validate_field | Method used to introduce the client software connected to Deribit platform over websocket. Provided data may have an impact on the maintained connection and will be collected for internal statistical purposes. In response, Deribit will also introduce itself.



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

            var apiInstance = new PublicApi(Configuration.Default);
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
                Debug.Print("Exception when calling PublicApi.PublicAuthGet: " + e.Message );
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


## PublicGetAnnouncementsGet

> Object PublicGetAnnouncementsGet ()

Retrieves announcements from the last 30 days.

### Example

```csharp
using System.Diagnostics;
using Org.OpenAPITools.Api;
using Org.OpenAPITools.Client;
using Org.OpenAPITools.Model;

namespace Example
{
    public class PublicGetAnnouncementsGetExample
    {
        public static void Main()
        {
            Configuration.Default.BasePath = "https://www.deribit.com/api/v2";
            // Configure HTTP basic authorization: bearerAuth
            Configuration.Default.Username = "YOUR_USERNAME";
            Configuration.Default.Password = "YOUR_PASSWORD";

            var apiInstance = new PublicApi(Configuration.Default);

            try
            {
                // Retrieves announcements from the last 30 days.
                Object result = apiInstance.PublicGetAnnouncementsGet();
                Debug.WriteLine(result);
            }
            catch (ApiException e)
            {
                Debug.Print("Exception when calling PublicApi.PublicGetAnnouncementsGet: " + e.Message );
                Debug.Print("Status Code: "+ e.ErrorCode);
                Debug.Print(e.StackTrace);
            }
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

[[Back to top]](#)
[[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PublicGetBookSummaryByCurrencyGet

> Object PublicGetBookSummaryByCurrencyGet (string currency, string kind = null)

Retrieves the summary information such as open interest, 24h volume, etc. for all instruments for the currency (optionally filtered by kind).

### Example

```csharp
using System.Diagnostics;
using Org.OpenAPITools.Api;
using Org.OpenAPITools.Client;
using Org.OpenAPITools.Model;

namespace Example
{
    public class PublicGetBookSummaryByCurrencyGetExample
    {
        public static void Main()
        {
            Configuration.Default.BasePath = "https://www.deribit.com/api/v2";
            // Configure HTTP basic authorization: bearerAuth
            Configuration.Default.Username = "YOUR_USERNAME";
            Configuration.Default.Password = "YOUR_PASSWORD";

            var apiInstance = new PublicApi(Configuration.Default);
            var currency = currency_example;  // string | The currency symbol
            var kind = kind_example;  // string | Instrument kind, if not provided instruments of all kinds are considered (optional) 

            try
            {
                // Retrieves the summary information such as open interest, 24h volume, etc. for all instruments for the currency (optionally filtered by kind).
                Object result = apiInstance.PublicGetBookSummaryByCurrencyGet(currency, kind);
                Debug.WriteLine(result);
            }
            catch (ApiException e)
            {
                Debug.Print("Exception when calling PublicApi.PublicGetBookSummaryByCurrencyGet: " + e.Message );
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
 **currency** | **string**| The currency symbol | 
 **kind** | **string**| Instrument kind, if not provided instruments of all kinds are considered | [optional] 

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


## PublicGetBookSummaryByInstrumentGet

> Object PublicGetBookSummaryByInstrumentGet (string instrumentName)

Retrieves the summary information such as open interest, 24h volume, etc. for a specific instrument.

### Example

```csharp
using System.Diagnostics;
using Org.OpenAPITools.Api;
using Org.OpenAPITools.Client;
using Org.OpenAPITools.Model;

namespace Example
{
    public class PublicGetBookSummaryByInstrumentGetExample
    {
        public static void Main()
        {
            Configuration.Default.BasePath = "https://www.deribit.com/api/v2";
            // Configure HTTP basic authorization: bearerAuth
            Configuration.Default.Username = "YOUR_USERNAME";
            Configuration.Default.Password = "YOUR_PASSWORD";

            var apiInstance = new PublicApi(Configuration.Default);
            var instrumentName = BTC-PERPETUAL;  // string | Instrument name

            try
            {
                // Retrieves the summary information such as open interest, 24h volume, etc. for a specific instrument.
                Object result = apiInstance.PublicGetBookSummaryByInstrumentGet(instrumentName);
                Debug.WriteLine(result);
            }
            catch (ApiException e)
            {
                Debug.Print("Exception when calling PublicApi.PublicGetBookSummaryByInstrumentGet: " + e.Message );
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
 **instrumentName** | **string**| Instrument name | 

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


## PublicGetContractSizeGet

> Object PublicGetContractSizeGet (string instrumentName)

Retrieves contract size of provided instrument.

### Example

```csharp
using System.Diagnostics;
using Org.OpenAPITools.Api;
using Org.OpenAPITools.Client;
using Org.OpenAPITools.Model;

namespace Example
{
    public class PublicGetContractSizeGetExample
    {
        public static void Main()
        {
            Configuration.Default.BasePath = "https://www.deribit.com/api/v2";
            // Configure HTTP basic authorization: bearerAuth
            Configuration.Default.Username = "YOUR_USERNAME";
            Configuration.Default.Password = "YOUR_PASSWORD";

            var apiInstance = new PublicApi(Configuration.Default);
            var instrumentName = BTC-PERPETUAL;  // string | Instrument name

            try
            {
                // Retrieves contract size of provided instrument.
                Object result = apiInstance.PublicGetContractSizeGet(instrumentName);
                Debug.WriteLine(result);
            }
            catch (ApiException e)
            {
                Debug.Print("Exception when calling PublicApi.PublicGetContractSizeGet: " + e.Message );
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
 **instrumentName** | **string**| Instrument name | 

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


## PublicGetCurrenciesGet

> Object PublicGetCurrenciesGet ()

Retrieves all cryptocurrencies supported by the API.

### Example

```csharp
using System.Diagnostics;
using Org.OpenAPITools.Api;
using Org.OpenAPITools.Client;
using Org.OpenAPITools.Model;

namespace Example
{
    public class PublicGetCurrenciesGetExample
    {
        public static void Main()
        {
            Configuration.Default.BasePath = "https://www.deribit.com/api/v2";
            // Configure HTTP basic authorization: bearerAuth
            Configuration.Default.Username = "YOUR_USERNAME";
            Configuration.Default.Password = "YOUR_PASSWORD";

            var apiInstance = new PublicApi(Configuration.Default);

            try
            {
                // Retrieves all cryptocurrencies supported by the API.
                Object result = apiInstance.PublicGetCurrenciesGet();
                Debug.WriteLine(result);
            }
            catch (ApiException e)
            {
                Debug.Print("Exception when calling PublicApi.PublicGetCurrenciesGet: " + e.Message );
                Debug.Print("Status Code: "+ e.ErrorCode);
                Debug.Print(e.StackTrace);
            }
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

[[Back to top]](#)
[[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PublicGetFundingChartDataGet

> Object PublicGetFundingChartDataGet (string instrumentName, string length = null)

Retrieve the latest user trades that have occurred for PERPETUAL instruments in a specific currency symbol and within given time range.

### Example

```csharp
using System.Diagnostics;
using Org.OpenAPITools.Api;
using Org.OpenAPITools.Client;
using Org.OpenAPITools.Model;

namespace Example
{
    public class PublicGetFundingChartDataGetExample
    {
        public static void Main()
        {
            Configuration.Default.BasePath = "https://www.deribit.com/api/v2";
            // Configure HTTP basic authorization: bearerAuth
            Configuration.Default.Username = "YOUR_USERNAME";
            Configuration.Default.Password = "YOUR_PASSWORD";

            var apiInstance = new PublicApi(Configuration.Default);
            var instrumentName = BTC-PERPETUAL;  // string | Instrument name
            var length = length_example;  // string | Specifies time period. `8h` - 8 hours, `24h` - 24 hours (optional) 

            try
            {
                // Retrieve the latest user trades that have occurred for PERPETUAL instruments in a specific currency symbol and within given time range.
                Object result = apiInstance.PublicGetFundingChartDataGet(instrumentName, length);
                Debug.WriteLine(result);
            }
            catch (ApiException e)
            {
                Debug.Print("Exception when calling PublicApi.PublicGetFundingChartDataGet: " + e.Message );
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
 **instrumentName** | **string**| Instrument name | 
 **length** | **string**| Specifies time period. &#x60;8h&#x60; - 8 hours, &#x60;24h&#x60; - 24 hours | [optional] 

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


## PublicGetHistoricalVolatilityGet

> Object PublicGetHistoricalVolatilityGet (string currency)

Provides information about historical volatility for given cryptocurrency.

### Example

```csharp
using System.Diagnostics;
using Org.OpenAPITools.Api;
using Org.OpenAPITools.Client;
using Org.OpenAPITools.Model;

namespace Example
{
    public class PublicGetHistoricalVolatilityGetExample
    {
        public static void Main()
        {
            Configuration.Default.BasePath = "https://www.deribit.com/api/v2";
            // Configure HTTP basic authorization: bearerAuth
            Configuration.Default.Username = "YOUR_USERNAME";
            Configuration.Default.Password = "YOUR_PASSWORD";

            var apiInstance = new PublicApi(Configuration.Default);
            var currency = currency_example;  // string | The currency symbol

            try
            {
                // Provides information about historical volatility for given cryptocurrency.
                Object result = apiInstance.PublicGetHistoricalVolatilityGet(currency);
                Debug.WriteLine(result);
            }
            catch (ApiException e)
            {
                Debug.Print("Exception when calling PublicApi.PublicGetHistoricalVolatilityGet: " + e.Message );
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
 **currency** | **string**| The currency symbol | 

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


## PublicGetIndexGet

> Object PublicGetIndexGet (string currency)

Retrieves the current index price for the instruments, for the selected currency.

### Example

```csharp
using System.Diagnostics;
using Org.OpenAPITools.Api;
using Org.OpenAPITools.Client;
using Org.OpenAPITools.Model;

namespace Example
{
    public class PublicGetIndexGetExample
    {
        public static void Main()
        {
            Configuration.Default.BasePath = "https://www.deribit.com/api/v2";
            // Configure HTTP basic authorization: bearerAuth
            Configuration.Default.Username = "YOUR_USERNAME";
            Configuration.Default.Password = "YOUR_PASSWORD";

            var apiInstance = new PublicApi(Configuration.Default);
            var currency = currency_example;  // string | The currency symbol

            try
            {
                // Retrieves the current index price for the instruments, for the selected currency.
                Object result = apiInstance.PublicGetIndexGet(currency);
                Debug.WriteLine(result);
            }
            catch (ApiException e)
            {
                Debug.Print("Exception when calling PublicApi.PublicGetIndexGet: " + e.Message );
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
 **currency** | **string**| The currency symbol | 

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


## PublicGetInstrumentsGet

> Object PublicGetInstrumentsGet (string currency, string kind = null, bool? expired = null)

Retrieves available trading instruments. This method can be used to see which instruments are available for trading, or which instruments have existed historically.

### Example

```csharp
using System.Diagnostics;
using Org.OpenAPITools.Api;
using Org.OpenAPITools.Client;
using Org.OpenAPITools.Model;

namespace Example
{
    public class PublicGetInstrumentsGetExample
    {
        public static void Main()
        {
            Configuration.Default.BasePath = "https://www.deribit.com/api/v2";
            // Configure HTTP basic authorization: bearerAuth
            Configuration.Default.Username = "YOUR_USERNAME";
            Configuration.Default.Password = "YOUR_PASSWORD";

            var apiInstance = new PublicApi(Configuration.Default);
            var currency = currency_example;  // string | The currency symbol
            var kind = kind_example;  // string | Instrument kind, if not provided instruments of all kinds are considered (optional) 
            var expired = true;  // bool? | Set to true to show expired instruments instead of active ones. (optional)  (default to false)

            try
            {
                // Retrieves available trading instruments. This method can be used to see which instruments are available for trading, or which instruments have existed historically.
                Object result = apiInstance.PublicGetInstrumentsGet(currency, kind, expired);
                Debug.WriteLine(result);
            }
            catch (ApiException e)
            {
                Debug.Print("Exception when calling PublicApi.PublicGetInstrumentsGet: " + e.Message );
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
 **currency** | **string**| The currency symbol | 
 **kind** | **string**| Instrument kind, if not provided instruments of all kinds are considered | [optional] 
 **expired** | **bool?**| Set to true to show expired instruments instead of active ones. | [optional] [default to false]

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


## PublicGetLastSettlementsByCurrencyGet

> Object PublicGetLastSettlementsByCurrencyGet (string currency, string type = null, int? count = null, string continuation = null, int? searchStartTimestamp = null)

Retrieves historical settlement, delivery and bankruptcy events coming from all instruments within given currency.

### Example

```csharp
using System.Diagnostics;
using Org.OpenAPITools.Api;
using Org.OpenAPITools.Client;
using Org.OpenAPITools.Model;

namespace Example
{
    public class PublicGetLastSettlementsByCurrencyGetExample
    {
        public static void Main()
        {
            Configuration.Default.BasePath = "https://www.deribit.com/api/v2";
            // Configure HTTP basic authorization: bearerAuth
            Configuration.Default.Username = "YOUR_USERNAME";
            Configuration.Default.Password = "YOUR_PASSWORD";

            var apiInstance = new PublicApi(Configuration.Default);
            var currency = currency_example;  // string | The currency symbol
            var type = type_example;  // string | Settlement type (optional) 
            var count = 56;  // int? | Number of requested items, default - `20` (optional) 
            var continuation = xY7T6cutS3t2B9YtaDkE6TS379oKnkzTvmEDUnEUP2Msa9xKWNNaT;  // string | Continuation token for pagination (optional) 
            var searchStartTimestamp = 1536569522277;  // int? | The latest timestamp to return result for (optional) 

            try
            {
                // Retrieves historical settlement, delivery and bankruptcy events coming from all instruments within given currency.
                Object result = apiInstance.PublicGetLastSettlementsByCurrencyGet(currency, type, count, continuation, searchStartTimestamp);
                Debug.WriteLine(result);
            }
            catch (ApiException e)
            {
                Debug.Print("Exception when calling PublicApi.PublicGetLastSettlementsByCurrencyGet: " + e.Message );
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
 **currency** | **string**| The currency symbol | 
 **type** | **string**| Settlement type | [optional] 
 **count** | **int?**| Number of requested items, default - &#x60;20&#x60; | [optional] 
 **continuation** | **string**| Continuation token for pagination | [optional] 
 **searchStartTimestamp** | **int?**| The latest timestamp to return result for | [optional] 

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


## PublicGetLastSettlementsByInstrumentGet

> Object PublicGetLastSettlementsByInstrumentGet (string instrumentName, string type = null, int? count = null, string continuation = null, int? searchStartTimestamp = null)

Retrieves historical public settlement, delivery and bankruptcy events filtered by instrument name.

### Example

```csharp
using System.Diagnostics;
using Org.OpenAPITools.Api;
using Org.OpenAPITools.Client;
using Org.OpenAPITools.Model;

namespace Example
{
    public class PublicGetLastSettlementsByInstrumentGetExample
    {
        public static void Main()
        {
            Configuration.Default.BasePath = "https://www.deribit.com/api/v2";
            // Configure HTTP basic authorization: bearerAuth
            Configuration.Default.Username = "YOUR_USERNAME";
            Configuration.Default.Password = "YOUR_PASSWORD";

            var apiInstance = new PublicApi(Configuration.Default);
            var instrumentName = BTC-PERPETUAL;  // string | Instrument name
            var type = type_example;  // string | Settlement type (optional) 
            var count = 56;  // int? | Number of requested items, default - `20` (optional) 
            var continuation = xY7T6cutS3t2B9YtaDkE6TS379oKnkzTvmEDUnEUP2Msa9xKWNNaT;  // string | Continuation token for pagination (optional) 
            var searchStartTimestamp = 1536569522277;  // int? | The latest timestamp to return result for (optional) 

            try
            {
                // Retrieves historical public settlement, delivery and bankruptcy events filtered by instrument name.
                Object result = apiInstance.PublicGetLastSettlementsByInstrumentGet(instrumentName, type, count, continuation, searchStartTimestamp);
                Debug.WriteLine(result);
            }
            catch (ApiException e)
            {
                Debug.Print("Exception when calling PublicApi.PublicGetLastSettlementsByInstrumentGet: " + e.Message );
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
 **instrumentName** | **string**| Instrument name | 
 **type** | **string**| Settlement type | [optional] 
 **count** | **int?**| Number of requested items, default - &#x60;20&#x60; | [optional] 
 **continuation** | **string**| Continuation token for pagination | [optional] 
 **searchStartTimestamp** | **int?**| The latest timestamp to return result for | [optional] 

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


## PublicGetLastTradesByCurrencyAndTimeGet

> Object PublicGetLastTradesByCurrencyAndTimeGet (string currency, int? startTimestamp, int? endTimestamp, string kind = null, int? count = null, bool? includeOld = null, string sorting = null)

Retrieve the latest trades that have occurred for instruments in a specific currency symbol and within given time range.

### Example

```csharp
using System.Diagnostics;
using Org.OpenAPITools.Api;
using Org.OpenAPITools.Client;
using Org.OpenAPITools.Model;

namespace Example
{
    public class PublicGetLastTradesByCurrencyAndTimeGetExample
    {
        public static void Main()
        {
            Configuration.Default.BasePath = "https://www.deribit.com/api/v2";
            // Configure HTTP basic authorization: bearerAuth
            Configuration.Default.Username = "YOUR_USERNAME";
            Configuration.Default.Password = "YOUR_PASSWORD";

            var apiInstance = new PublicApi(Configuration.Default);
            var currency = currency_example;  // string | The currency symbol
            var startTimestamp = 1536569522277;  // int? | The earliest timestamp to return result for
            var endTimestamp = 1536569522277;  // int? | The most recent timestamp to return result for
            var kind = kind_example;  // string | Instrument kind, if not provided instruments of all kinds are considered (optional) 
            var count = 56;  // int? | Number of requested items, default - `10` (optional) 
            var includeOld = true;  // bool? | Include trades older than 7 days, default - `false` (optional) 
            var sorting = sorting_example;  // string | Direction of results sorting (`default` value means no sorting, results will be returned in order in which they left the database) (optional) 

            try
            {
                // Retrieve the latest trades that have occurred for instruments in a specific currency symbol and within given time range.
                Object result = apiInstance.PublicGetLastTradesByCurrencyAndTimeGet(currency, startTimestamp, endTimestamp, kind, count, includeOld, sorting);
                Debug.WriteLine(result);
            }
            catch (ApiException e)
            {
                Debug.Print("Exception when calling PublicApi.PublicGetLastTradesByCurrencyAndTimeGet: " + e.Message );
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
 **currency** | **string**| The currency symbol | 
 **startTimestamp** | **int?**| The earliest timestamp to return result for | 
 **endTimestamp** | **int?**| The most recent timestamp to return result for | 
 **kind** | **string**| Instrument kind, if not provided instruments of all kinds are considered | [optional] 
 **count** | **int?**| Number of requested items, default - &#x60;10&#x60; | [optional] 
 **includeOld** | **bool?**| Include trades older than 7 days, default - &#x60;false&#x60; | [optional] 
 **sorting** | **string**| Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) | [optional] 

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


## PublicGetLastTradesByCurrencyGet

> Object PublicGetLastTradesByCurrencyGet (string currency, string kind = null, string startId = null, string endId = null, int? count = null, bool? includeOld = null, string sorting = null)

Retrieve the latest trades that have occurred for instruments in a specific currency symbol.

### Example

```csharp
using System.Diagnostics;
using Org.OpenAPITools.Api;
using Org.OpenAPITools.Client;
using Org.OpenAPITools.Model;

namespace Example
{
    public class PublicGetLastTradesByCurrencyGetExample
    {
        public static void Main()
        {
            Configuration.Default.BasePath = "https://www.deribit.com/api/v2";
            // Configure HTTP basic authorization: bearerAuth
            Configuration.Default.Username = "YOUR_USERNAME";
            Configuration.Default.Password = "YOUR_PASSWORD";

            var apiInstance = new PublicApi(Configuration.Default);
            var currency = currency_example;  // string | The currency symbol
            var kind = kind_example;  // string | Instrument kind, if not provided instruments of all kinds are considered (optional) 
            var startId = startId_example;  // string | The ID number of the first trade to be returned (optional) 
            var endId = endId_example;  // string | The ID number of the last trade to be returned (optional) 
            var count = 56;  // int? | Number of requested items, default - `10` (optional) 
            var includeOld = true;  // bool? | Include trades older than 7 days, default - `false` (optional) 
            var sorting = sorting_example;  // string | Direction of results sorting (`default` value means no sorting, results will be returned in order in which they left the database) (optional) 

            try
            {
                // Retrieve the latest trades that have occurred for instruments in a specific currency symbol.
                Object result = apiInstance.PublicGetLastTradesByCurrencyGet(currency, kind, startId, endId, count, includeOld, sorting);
                Debug.WriteLine(result);
            }
            catch (ApiException e)
            {
                Debug.Print("Exception when calling PublicApi.PublicGetLastTradesByCurrencyGet: " + e.Message );
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
 **currency** | **string**| The currency symbol | 
 **kind** | **string**| Instrument kind, if not provided instruments of all kinds are considered | [optional] 
 **startId** | **string**| The ID number of the first trade to be returned | [optional] 
 **endId** | **string**| The ID number of the last trade to be returned | [optional] 
 **count** | **int?**| Number of requested items, default - &#x60;10&#x60; | [optional] 
 **includeOld** | **bool?**| Include trades older than 7 days, default - &#x60;false&#x60; | [optional] 
 **sorting** | **string**| Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) | [optional] 

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


## PublicGetLastTradesByInstrumentAndTimeGet

> Object PublicGetLastTradesByInstrumentAndTimeGet (string instrumentName, int? startTimestamp, int? endTimestamp, int? count = null, bool? includeOld = null, string sorting = null)

Retrieve the latest trades that have occurred for a specific instrument and within given time range.

### Example

```csharp
using System.Diagnostics;
using Org.OpenAPITools.Api;
using Org.OpenAPITools.Client;
using Org.OpenAPITools.Model;

namespace Example
{
    public class PublicGetLastTradesByInstrumentAndTimeGetExample
    {
        public static void Main()
        {
            Configuration.Default.BasePath = "https://www.deribit.com/api/v2";
            // Configure HTTP basic authorization: bearerAuth
            Configuration.Default.Username = "YOUR_USERNAME";
            Configuration.Default.Password = "YOUR_PASSWORD";

            var apiInstance = new PublicApi(Configuration.Default);
            var instrumentName = BTC-PERPETUAL;  // string | Instrument name
            var startTimestamp = 1536569522277;  // int? | The earliest timestamp to return result for
            var endTimestamp = 1536569522277;  // int? | The most recent timestamp to return result for
            var count = 56;  // int? | Number of requested items, default - `10` (optional) 
            var includeOld = true;  // bool? | Include trades older than 7 days, default - `false` (optional) 
            var sorting = sorting_example;  // string | Direction of results sorting (`default` value means no sorting, results will be returned in order in which they left the database) (optional) 

            try
            {
                // Retrieve the latest trades that have occurred for a specific instrument and within given time range.
                Object result = apiInstance.PublicGetLastTradesByInstrumentAndTimeGet(instrumentName, startTimestamp, endTimestamp, count, includeOld, sorting);
                Debug.WriteLine(result);
            }
            catch (ApiException e)
            {
                Debug.Print("Exception when calling PublicApi.PublicGetLastTradesByInstrumentAndTimeGet: " + e.Message );
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
 **instrumentName** | **string**| Instrument name | 
 **startTimestamp** | **int?**| The earliest timestamp to return result for | 
 **endTimestamp** | **int?**| The most recent timestamp to return result for | 
 **count** | **int?**| Number of requested items, default - &#x60;10&#x60; | [optional] 
 **includeOld** | **bool?**| Include trades older than 7 days, default - &#x60;false&#x60; | [optional] 
 **sorting** | **string**| Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) | [optional] 

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


## PublicGetLastTradesByInstrumentGet

> Object PublicGetLastTradesByInstrumentGet (string instrumentName, int? startSeq = null, int? endSeq = null, int? count = null, bool? includeOld = null, string sorting = null)

Retrieve the latest trades that have occurred for a specific instrument.

### Example

```csharp
using System.Diagnostics;
using Org.OpenAPITools.Api;
using Org.OpenAPITools.Client;
using Org.OpenAPITools.Model;

namespace Example
{
    public class PublicGetLastTradesByInstrumentGetExample
    {
        public static void Main()
        {
            Configuration.Default.BasePath = "https://www.deribit.com/api/v2";
            // Configure HTTP basic authorization: bearerAuth
            Configuration.Default.Username = "YOUR_USERNAME";
            Configuration.Default.Password = "YOUR_PASSWORD";

            var apiInstance = new PublicApi(Configuration.Default);
            var instrumentName = BTC-PERPETUAL;  // string | Instrument name
            var startSeq = 56;  // int? | The sequence number of the first trade to be returned (optional) 
            var endSeq = 56;  // int? | The sequence number of the last trade to be returned (optional) 
            var count = 56;  // int? | Number of requested items, default - `10` (optional) 
            var includeOld = true;  // bool? | Include trades older than 7 days, default - `false` (optional) 
            var sorting = sorting_example;  // string | Direction of results sorting (`default` value means no sorting, results will be returned in order in which they left the database) (optional) 

            try
            {
                // Retrieve the latest trades that have occurred for a specific instrument.
                Object result = apiInstance.PublicGetLastTradesByInstrumentGet(instrumentName, startSeq, endSeq, count, includeOld, sorting);
                Debug.WriteLine(result);
            }
            catch (ApiException e)
            {
                Debug.Print("Exception when calling PublicApi.PublicGetLastTradesByInstrumentGet: " + e.Message );
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
 **instrumentName** | **string**| Instrument name | 
 **startSeq** | **int?**| The sequence number of the first trade to be returned | [optional] 
 **endSeq** | **int?**| The sequence number of the last trade to be returned | [optional] 
 **count** | **int?**| Number of requested items, default - &#x60;10&#x60; | [optional] 
 **includeOld** | **bool?**| Include trades older than 7 days, default - &#x60;false&#x60; | [optional] 
 **sorting** | **string**| Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) | [optional] 

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


## PublicGetOrderBookGet

> Object PublicGetOrderBookGet (string instrumentName, decimal? depth = null)

Retrieves the order book, along with other market values for a given instrument.

### Example

```csharp
using System.Diagnostics;
using Org.OpenAPITools.Api;
using Org.OpenAPITools.Client;
using Org.OpenAPITools.Model;

namespace Example
{
    public class PublicGetOrderBookGetExample
    {
        public static void Main()
        {
            Configuration.Default.BasePath = "https://www.deribit.com/api/v2";
            // Configure HTTP basic authorization: bearerAuth
            Configuration.Default.Username = "YOUR_USERNAME";
            Configuration.Default.Password = "YOUR_PASSWORD";

            var apiInstance = new PublicApi(Configuration.Default);
            var instrumentName = instrumentName_example;  // string | The instrument name for which to retrieve the order book, see [`getinstruments`](#getinstruments) to obtain instrument names.
            var depth = 8.14;  // decimal? | The number of entries to return for bids and asks. (optional) 

            try
            {
                // Retrieves the order book, along with other market values for a given instrument.
                Object result = apiInstance.PublicGetOrderBookGet(instrumentName, depth);
                Debug.WriteLine(result);
            }
            catch (ApiException e)
            {
                Debug.Print("Exception when calling PublicApi.PublicGetOrderBookGet: " + e.Message );
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
 **instrumentName** | **string**| The instrument name for which to retrieve the order book, see [&#x60;getinstruments&#x60;](#getinstruments) to obtain instrument names. | 
 **depth** | **decimal?**| The number of entries to return for bids and asks. | [optional] 

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


## PublicGetTimeGet

> Object PublicGetTimeGet ()

Retrieves the current time (in milliseconds). This API endpoint can be used to check the clock skew between your software and Deribit's systems.

### Example

```csharp
using System.Diagnostics;
using Org.OpenAPITools.Api;
using Org.OpenAPITools.Client;
using Org.OpenAPITools.Model;

namespace Example
{
    public class PublicGetTimeGetExample
    {
        public static void Main()
        {
            Configuration.Default.BasePath = "https://www.deribit.com/api/v2";
            // Configure HTTP basic authorization: bearerAuth
            Configuration.Default.Username = "YOUR_USERNAME";
            Configuration.Default.Password = "YOUR_PASSWORD";

            var apiInstance = new PublicApi(Configuration.Default);

            try
            {
                // Retrieves the current time (in milliseconds). This API endpoint can be used to check the clock skew between your software and Deribit's systems.
                Object result = apiInstance.PublicGetTimeGet();
                Debug.WriteLine(result);
            }
            catch (ApiException e)
            {
                Debug.Print("Exception when calling PublicApi.PublicGetTimeGet: " + e.Message );
                Debug.Print("Status Code: "+ e.ErrorCode);
                Debug.Print(e.StackTrace);
            }
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

[[Back to top]](#)
[[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PublicGetTradeVolumesGet

> Object PublicGetTradeVolumesGet ()

Retrieves aggregated 24h trade volumes for different instrument types and currencies.

### Example

```csharp
using System.Diagnostics;
using Org.OpenAPITools.Api;
using Org.OpenAPITools.Client;
using Org.OpenAPITools.Model;

namespace Example
{
    public class PublicGetTradeVolumesGetExample
    {
        public static void Main()
        {
            Configuration.Default.BasePath = "https://www.deribit.com/api/v2";
            // Configure HTTP basic authorization: bearerAuth
            Configuration.Default.Username = "YOUR_USERNAME";
            Configuration.Default.Password = "YOUR_PASSWORD";

            var apiInstance = new PublicApi(Configuration.Default);

            try
            {
                // Retrieves aggregated 24h trade volumes for different instrument types and currencies.
                Object result = apiInstance.PublicGetTradeVolumesGet();
                Debug.WriteLine(result);
            }
            catch (ApiException e)
            {
                Debug.Print("Exception when calling PublicApi.PublicGetTradeVolumesGet: " + e.Message );
                Debug.Print("Status Code: "+ e.ErrorCode);
                Debug.Print(e.StackTrace);
            }
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

[[Back to top]](#)
[[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PublicGetTradingviewChartDataGet

> Object PublicGetTradingviewChartDataGet (string instrumentName, int? startTimestamp, int? endTimestamp)

Publicly available market data used to generate a TradingView candle chart.

### Example

```csharp
using System.Diagnostics;
using Org.OpenAPITools.Api;
using Org.OpenAPITools.Client;
using Org.OpenAPITools.Model;

namespace Example
{
    public class PublicGetTradingviewChartDataGetExample
    {
        public static void Main()
        {
            Configuration.Default.BasePath = "https://www.deribit.com/api/v2";
            // Configure HTTP basic authorization: bearerAuth
            Configuration.Default.Username = "YOUR_USERNAME";
            Configuration.Default.Password = "YOUR_PASSWORD";

            var apiInstance = new PublicApi(Configuration.Default);
            var instrumentName = BTC-PERPETUAL;  // string | Instrument name
            var startTimestamp = 1536569522277;  // int? | The earliest timestamp to return result for
            var endTimestamp = 1536569522277;  // int? | The most recent timestamp to return result for

            try
            {
                // Publicly available market data used to generate a TradingView candle chart.
                Object result = apiInstance.PublicGetTradingviewChartDataGet(instrumentName, startTimestamp, endTimestamp);
                Debug.WriteLine(result);
            }
            catch (ApiException e)
            {
                Debug.Print("Exception when calling PublicApi.PublicGetTradingviewChartDataGet: " + e.Message );
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
 **instrumentName** | **string**| Instrument name | 
 **startTimestamp** | **int?**| The earliest timestamp to return result for | 
 **endTimestamp** | **int?**| The most recent timestamp to return result for | 

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


## PublicTestGet

> Object PublicTestGet (string expectedResult = null)

Tests the connection to the API server, and returns its version. You can use this to make sure the API is reachable, and matches the expected version.

### Example

```csharp
using System.Diagnostics;
using Org.OpenAPITools.Api;
using Org.OpenAPITools.Client;
using Org.OpenAPITools.Model;

namespace Example
{
    public class PublicTestGetExample
    {
        public static void Main()
        {
            Configuration.Default.BasePath = "https://www.deribit.com/api/v2";
            // Configure HTTP basic authorization: bearerAuth
            Configuration.Default.Username = "YOUR_USERNAME";
            Configuration.Default.Password = "YOUR_PASSWORD";

            var apiInstance = new PublicApi(Configuration.Default);
            var expectedResult = expectedResult_example;  // string | The value \"exception\" will trigger an error response. This may be useful for testing wrapper libraries. (optional) 

            try
            {
                // Tests the connection to the API server, and returns its version. You can use this to make sure the API is reachable, and matches the expected version.
                Object result = apiInstance.PublicTestGet(expectedResult);
                Debug.WriteLine(result);
            }
            catch (ApiException e)
            {
                Debug.Print("Exception when calling PublicApi.PublicTestGet: " + e.Message );
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
 **expectedResult** | **string**| The value \&quot;exception\&quot; will trigger an error response. This may be useful for testing wrapper libraries. | [optional] 

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


## PublicTickerGet

> Object PublicTickerGet (string instrumentName)

Get ticker for an instrument.

### Example

```csharp
using System.Diagnostics;
using Org.OpenAPITools.Api;
using Org.OpenAPITools.Client;
using Org.OpenAPITools.Model;

namespace Example
{
    public class PublicTickerGetExample
    {
        public static void Main()
        {
            Configuration.Default.BasePath = "https://www.deribit.com/api/v2";
            // Configure HTTP basic authorization: bearerAuth
            Configuration.Default.Username = "YOUR_USERNAME";
            Configuration.Default.Password = "YOUR_PASSWORD";

            var apiInstance = new PublicApi(Configuration.Default);
            var instrumentName = BTC-PERPETUAL;  // string | Instrument name

            try
            {
                // Get ticker for an instrument.
                Object result = apiInstance.PublicTickerGet(instrumentName);
                Debug.WriteLine(result);
            }
            catch (ApiException e)
            {
                Debug.Print("Exception when calling PublicApi.PublicTickerGet: " + e.Message );
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
 **instrumentName** | **string**| Instrument name | 

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


## PublicValidateFieldGet

> Object PublicValidateFieldGet (string field, string value, string value2 = null)

Method used to introduce the client software connected to Deribit platform over websocket. Provided data may have an impact on the maintained connection and will be collected for internal statistical purposes. In response, Deribit will also introduce itself.

### Example

```csharp
using System.Diagnostics;
using Org.OpenAPITools.Api;
using Org.OpenAPITools.Client;
using Org.OpenAPITools.Model;

namespace Example
{
    public class PublicValidateFieldGetExample
    {
        public static void Main()
        {
            Configuration.Default.BasePath = "https://www.deribit.com/api/v2";
            // Configure HTTP basic authorization: bearerAuth
            Configuration.Default.Username = "YOUR_USERNAME";
            Configuration.Default.Password = "YOUR_PASSWORD";

            var apiInstance = new PublicApi(Configuration.Default);
            var field = field_example;  // string | Name of the field to be validated, examples: postal_code, date_of_birth
            var value = value_example;  // string | Value to be checked
            var value2 = value2_example;  // string | Additional value to be compared with (optional) 

            try
            {
                // Method used to introduce the client software connected to Deribit platform over websocket. Provided data may have an impact on the maintained connection and will be collected for internal statistical purposes. In response, Deribit will also introduce itself.
                Object result = apiInstance.PublicValidateFieldGet(field, value, value2);
                Debug.WriteLine(result);
            }
            catch (ApiException e)
            {
                Debug.Print("Exception when calling PublicApi.PublicValidateFieldGet: " + e.Message );
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
 **field** | **string**| Name of the field to be validated, examples: postal_code, date_of_birth | 
 **value** | **string**| Value to be checked | 
 **value2** | **string**| Additional value to be compared with | [optional] 

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

