# DeribitApi.PublicApi

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

let apiInstance = new DeribitApi.PublicApi();
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


## publicGetAnnouncementsGet

> Object publicGetAnnouncementsGet()

Retrieves announcements from the last 30 days.

### Example

```javascript
import DeribitApi from 'deribit_api';
let defaultClient = DeribitApi.ApiClient.instance;
// Configure Bearer (Auth. Token) access token for authorization: bearerAuth
let bearerAuth = defaultClient.authentications['bearerAuth'];
bearerAuth.accessToken = "YOUR ACCESS TOKEN"

let apiInstance = new DeribitApi.PublicApi();
apiInstance.publicGetAnnouncementsGet((error, data, response) => {
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


## publicGetBookSummaryByCurrencyGet

> Object publicGetBookSummaryByCurrencyGet(currency, opts)

Retrieves the summary information such as open interest, 24h volume, etc. for all instruments for the currency (optionally filtered by kind).

### Example

```javascript
import DeribitApi from 'deribit_api';
let defaultClient = DeribitApi.ApiClient.instance;
// Configure Bearer (Auth. Token) access token for authorization: bearerAuth
let bearerAuth = defaultClient.authentications['bearerAuth'];
bearerAuth.accessToken = "YOUR ACCESS TOKEN"

let apiInstance = new DeribitApi.PublicApi();
let currency = "currency_example"; // String | The currency symbol
let opts = {
  'kind': "kind_example" // String | Instrument kind, if not provided instruments of all kinds are considered
};
apiInstance.publicGetBookSummaryByCurrencyGet(currency, opts, (error, data, response) => {
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
 **currency** | **String**| The currency symbol | 
 **kind** | **String**| Instrument kind, if not provided instruments of all kinds are considered | [optional] 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## publicGetBookSummaryByInstrumentGet

> Object publicGetBookSummaryByInstrumentGet(instrumentName)

Retrieves the summary information such as open interest, 24h volume, etc. for a specific instrument.

### Example

```javascript
import DeribitApi from 'deribit_api';
let defaultClient = DeribitApi.ApiClient.instance;
// Configure Bearer (Auth. Token) access token for authorization: bearerAuth
let bearerAuth = defaultClient.authentications['bearerAuth'];
bearerAuth.accessToken = "YOUR ACCESS TOKEN"

let apiInstance = new DeribitApi.PublicApi();
let instrumentName = BTC-PERPETUAL; // String | Instrument name
apiInstance.publicGetBookSummaryByInstrumentGet(instrumentName, (error, data, response) => {
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
 **instrumentName** | **String**| Instrument name | 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## publicGetContractSizeGet

> Object publicGetContractSizeGet(instrumentName)

Retrieves contract size of provided instrument.

### Example

```javascript
import DeribitApi from 'deribit_api';
let defaultClient = DeribitApi.ApiClient.instance;
// Configure Bearer (Auth. Token) access token for authorization: bearerAuth
let bearerAuth = defaultClient.authentications['bearerAuth'];
bearerAuth.accessToken = "YOUR ACCESS TOKEN"

let apiInstance = new DeribitApi.PublicApi();
let instrumentName = BTC-PERPETUAL; // String | Instrument name
apiInstance.publicGetContractSizeGet(instrumentName, (error, data, response) => {
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
 **instrumentName** | **String**| Instrument name | 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## publicGetCurrenciesGet

> Object publicGetCurrenciesGet()

Retrieves all cryptocurrencies supported by the API.

### Example

```javascript
import DeribitApi from 'deribit_api';
let defaultClient = DeribitApi.ApiClient.instance;
// Configure Bearer (Auth. Token) access token for authorization: bearerAuth
let bearerAuth = defaultClient.authentications['bearerAuth'];
bearerAuth.accessToken = "YOUR ACCESS TOKEN"

let apiInstance = new DeribitApi.PublicApi();
apiInstance.publicGetCurrenciesGet((error, data, response) => {
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


## publicGetFundingChartDataGet

> Object publicGetFundingChartDataGet(instrumentName, opts)

Retrieve the latest user trades that have occurred for PERPETUAL instruments in a specific currency symbol and within given time range.

### Example

```javascript
import DeribitApi from 'deribit_api';
let defaultClient = DeribitApi.ApiClient.instance;
// Configure Bearer (Auth. Token) access token for authorization: bearerAuth
let bearerAuth = defaultClient.authentications['bearerAuth'];
bearerAuth.accessToken = "YOUR ACCESS TOKEN"

let apiInstance = new DeribitApi.PublicApi();
let instrumentName = BTC-PERPETUAL; // String | Instrument name
let opts = {
  'length': "length_example" // String | Specifies time period. `8h` - 8 hours, `24h` - 24 hours
};
apiInstance.publicGetFundingChartDataGet(instrumentName, opts, (error, data, response) => {
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
 **instrumentName** | **String**| Instrument name | 
 **length** | **String**| Specifies time period. &#x60;8h&#x60; - 8 hours, &#x60;24h&#x60; - 24 hours | [optional] 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## publicGetHistoricalVolatilityGet

> Object publicGetHistoricalVolatilityGet(currency)

Provides information about historical volatility for given cryptocurrency.

### Example

```javascript
import DeribitApi from 'deribit_api';
let defaultClient = DeribitApi.ApiClient.instance;
// Configure Bearer (Auth. Token) access token for authorization: bearerAuth
let bearerAuth = defaultClient.authentications['bearerAuth'];
bearerAuth.accessToken = "YOUR ACCESS TOKEN"

let apiInstance = new DeribitApi.PublicApi();
let currency = "currency_example"; // String | The currency symbol
apiInstance.publicGetHistoricalVolatilityGet(currency, (error, data, response) => {
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
 **currency** | **String**| The currency symbol | 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## publicGetIndexGet

> Object publicGetIndexGet(currency)

Retrieves the current index price for the instruments, for the selected currency.

### Example

```javascript
import DeribitApi from 'deribit_api';
let defaultClient = DeribitApi.ApiClient.instance;
// Configure Bearer (Auth. Token) access token for authorization: bearerAuth
let bearerAuth = defaultClient.authentications['bearerAuth'];
bearerAuth.accessToken = "YOUR ACCESS TOKEN"

let apiInstance = new DeribitApi.PublicApi();
let currency = "currency_example"; // String | The currency symbol
apiInstance.publicGetIndexGet(currency, (error, data, response) => {
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
 **currency** | **String**| The currency symbol | 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## publicGetInstrumentsGet

> Object publicGetInstrumentsGet(currency, opts)

Retrieves available trading instruments. This method can be used to see which instruments are available for trading, or which instruments have existed historically.

### Example

```javascript
import DeribitApi from 'deribit_api';
let defaultClient = DeribitApi.ApiClient.instance;
// Configure Bearer (Auth. Token) access token for authorization: bearerAuth
let bearerAuth = defaultClient.authentications['bearerAuth'];
bearerAuth.accessToken = "YOUR ACCESS TOKEN"

let apiInstance = new DeribitApi.PublicApi();
let currency = "currency_example"; // String | The currency symbol
let opts = {
  'kind': "kind_example", // String | Instrument kind, if not provided instruments of all kinds are considered
  'expired': false // Boolean | Set to true to show expired instruments instead of active ones.
};
apiInstance.publicGetInstrumentsGet(currency, opts, (error, data, response) => {
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
 **currency** | **String**| The currency symbol | 
 **kind** | **String**| Instrument kind, if not provided instruments of all kinds are considered | [optional] 
 **expired** | **Boolean**| Set to true to show expired instruments instead of active ones. | [optional] [default to false]

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## publicGetLastSettlementsByCurrencyGet

> Object publicGetLastSettlementsByCurrencyGet(currency, opts)

Retrieves historical settlement, delivery and bankruptcy events coming from all instruments within given currency.

### Example

```javascript
import DeribitApi from 'deribit_api';
let defaultClient = DeribitApi.ApiClient.instance;
// Configure Bearer (Auth. Token) access token for authorization: bearerAuth
let bearerAuth = defaultClient.authentications['bearerAuth'];
bearerAuth.accessToken = "YOUR ACCESS TOKEN"

let apiInstance = new DeribitApi.PublicApi();
let currency = "currency_example"; // String | The currency symbol
let opts = {
  'type': "type_example", // String | Settlement type
  'count': 56, // Number | Number of requested items, default - `20`
  'continuation': xY7T6cutS3t2B9YtaDkE6TS379oKnkzTvmEDUnEUP2Msa9xKWNNaT, // String | Continuation token for pagination
  'searchStartTimestamp': 1536569522277 // Number | The latest timestamp to return result for
};
apiInstance.publicGetLastSettlementsByCurrencyGet(currency, opts, (error, data, response) => {
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
 **currency** | **String**| The currency symbol | 
 **type** | **String**| Settlement type | [optional] 
 **count** | **Number**| Number of requested items, default - &#x60;20&#x60; | [optional] 
 **continuation** | **String**| Continuation token for pagination | [optional] 
 **searchStartTimestamp** | **Number**| The latest timestamp to return result for | [optional] 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## publicGetLastSettlementsByInstrumentGet

> Object publicGetLastSettlementsByInstrumentGet(instrumentName, opts)

Retrieves historical public settlement, delivery and bankruptcy events filtered by instrument name.

### Example

```javascript
import DeribitApi from 'deribit_api';
let defaultClient = DeribitApi.ApiClient.instance;
// Configure Bearer (Auth. Token) access token for authorization: bearerAuth
let bearerAuth = defaultClient.authentications['bearerAuth'];
bearerAuth.accessToken = "YOUR ACCESS TOKEN"

let apiInstance = new DeribitApi.PublicApi();
let instrumentName = BTC-PERPETUAL; // String | Instrument name
let opts = {
  'type': "type_example", // String | Settlement type
  'count': 56, // Number | Number of requested items, default - `20`
  'continuation': xY7T6cutS3t2B9YtaDkE6TS379oKnkzTvmEDUnEUP2Msa9xKWNNaT, // String | Continuation token for pagination
  'searchStartTimestamp': 1536569522277 // Number | The latest timestamp to return result for
};
apiInstance.publicGetLastSettlementsByInstrumentGet(instrumentName, opts, (error, data, response) => {
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
 **instrumentName** | **String**| Instrument name | 
 **type** | **String**| Settlement type | [optional] 
 **count** | **Number**| Number of requested items, default - &#x60;20&#x60; | [optional] 
 **continuation** | **String**| Continuation token for pagination | [optional] 
 **searchStartTimestamp** | **Number**| The latest timestamp to return result for | [optional] 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## publicGetLastTradesByCurrencyAndTimeGet

> Object publicGetLastTradesByCurrencyAndTimeGet(currency, startTimestamp, endTimestamp, opts)

Retrieve the latest trades that have occurred for instruments in a specific currency symbol and within given time range.

### Example

```javascript
import DeribitApi from 'deribit_api';
let defaultClient = DeribitApi.ApiClient.instance;
// Configure Bearer (Auth. Token) access token for authorization: bearerAuth
let bearerAuth = defaultClient.authentications['bearerAuth'];
bearerAuth.accessToken = "YOUR ACCESS TOKEN"

let apiInstance = new DeribitApi.PublicApi();
let currency = "currency_example"; // String | The currency symbol
let startTimestamp = 1536569522277; // Number | The earliest timestamp to return result for
let endTimestamp = 1536569522277; // Number | The most recent timestamp to return result for
let opts = {
  'kind': "kind_example", // String | Instrument kind, if not provided instruments of all kinds are considered
  'count': 56, // Number | Number of requested items, default - `10`
  'includeOld': true, // Boolean | Include trades older than 7 days, default - `false`
  'sorting': "sorting_example" // String | Direction of results sorting (`default` value means no sorting, results will be returned in order in which they left the database)
};
apiInstance.publicGetLastTradesByCurrencyAndTimeGet(currency, startTimestamp, endTimestamp, opts, (error, data, response) => {
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
 **currency** | **String**| The currency symbol | 
 **startTimestamp** | **Number**| The earliest timestamp to return result for | 
 **endTimestamp** | **Number**| The most recent timestamp to return result for | 
 **kind** | **String**| Instrument kind, if not provided instruments of all kinds are considered | [optional] 
 **count** | **Number**| Number of requested items, default - &#x60;10&#x60; | [optional] 
 **includeOld** | **Boolean**| Include trades older than 7 days, default - &#x60;false&#x60; | [optional] 
 **sorting** | **String**| Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) | [optional] 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## publicGetLastTradesByCurrencyGet

> Object publicGetLastTradesByCurrencyGet(currency, opts)

Retrieve the latest trades that have occurred for instruments in a specific currency symbol.

### Example

```javascript
import DeribitApi from 'deribit_api';
let defaultClient = DeribitApi.ApiClient.instance;
// Configure Bearer (Auth. Token) access token for authorization: bearerAuth
let bearerAuth = defaultClient.authentications['bearerAuth'];
bearerAuth.accessToken = "YOUR ACCESS TOKEN"

let apiInstance = new DeribitApi.PublicApi();
let currency = "currency_example"; // String | The currency symbol
let opts = {
  'kind': "kind_example", // String | Instrument kind, if not provided instruments of all kinds are considered
  'startId': "startId_example", // String | The ID number of the first trade to be returned
  'endId': "endId_example", // String | The ID number of the last trade to be returned
  'count': 56, // Number | Number of requested items, default - `10`
  'includeOld': true, // Boolean | Include trades older than 7 days, default - `false`
  'sorting': "sorting_example" // String | Direction of results sorting (`default` value means no sorting, results will be returned in order in which they left the database)
};
apiInstance.publicGetLastTradesByCurrencyGet(currency, opts, (error, data, response) => {
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
 **currency** | **String**| The currency symbol | 
 **kind** | **String**| Instrument kind, if not provided instruments of all kinds are considered | [optional] 
 **startId** | **String**| The ID number of the first trade to be returned | [optional] 
 **endId** | **String**| The ID number of the last trade to be returned | [optional] 
 **count** | **Number**| Number of requested items, default - &#x60;10&#x60; | [optional] 
 **includeOld** | **Boolean**| Include trades older than 7 days, default - &#x60;false&#x60; | [optional] 
 **sorting** | **String**| Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) | [optional] 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## publicGetLastTradesByInstrumentAndTimeGet

> Object publicGetLastTradesByInstrumentAndTimeGet(instrumentName, startTimestamp, endTimestamp, opts)

Retrieve the latest trades that have occurred for a specific instrument and within given time range.

### Example

```javascript
import DeribitApi from 'deribit_api';
let defaultClient = DeribitApi.ApiClient.instance;
// Configure Bearer (Auth. Token) access token for authorization: bearerAuth
let bearerAuth = defaultClient.authentications['bearerAuth'];
bearerAuth.accessToken = "YOUR ACCESS TOKEN"

let apiInstance = new DeribitApi.PublicApi();
let instrumentName = BTC-PERPETUAL; // String | Instrument name
let startTimestamp = 1536569522277; // Number | The earliest timestamp to return result for
let endTimestamp = 1536569522277; // Number | The most recent timestamp to return result for
let opts = {
  'count': 56, // Number | Number of requested items, default - `10`
  'includeOld': true, // Boolean | Include trades older than 7 days, default - `false`
  'sorting': "sorting_example" // String | Direction of results sorting (`default` value means no sorting, results will be returned in order in which they left the database)
};
apiInstance.publicGetLastTradesByInstrumentAndTimeGet(instrumentName, startTimestamp, endTimestamp, opts, (error, data, response) => {
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
 **instrumentName** | **String**| Instrument name | 
 **startTimestamp** | **Number**| The earliest timestamp to return result for | 
 **endTimestamp** | **Number**| The most recent timestamp to return result for | 
 **count** | **Number**| Number of requested items, default - &#x60;10&#x60; | [optional] 
 **includeOld** | **Boolean**| Include trades older than 7 days, default - &#x60;false&#x60; | [optional] 
 **sorting** | **String**| Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) | [optional] 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## publicGetLastTradesByInstrumentGet

> Object publicGetLastTradesByInstrumentGet(instrumentName, opts)

Retrieve the latest trades that have occurred for a specific instrument.

### Example

```javascript
import DeribitApi from 'deribit_api';
let defaultClient = DeribitApi.ApiClient.instance;
// Configure Bearer (Auth. Token) access token for authorization: bearerAuth
let bearerAuth = defaultClient.authentications['bearerAuth'];
bearerAuth.accessToken = "YOUR ACCESS TOKEN"

let apiInstance = new DeribitApi.PublicApi();
let instrumentName = BTC-PERPETUAL; // String | Instrument name
let opts = {
  'startSeq': 56, // Number | The sequence number of the first trade to be returned
  'endSeq': 56, // Number | The sequence number of the last trade to be returned
  'count': 56, // Number | Number of requested items, default - `10`
  'includeOld': true, // Boolean | Include trades older than 7 days, default - `false`
  'sorting': "sorting_example" // String | Direction of results sorting (`default` value means no sorting, results will be returned in order in which they left the database)
};
apiInstance.publicGetLastTradesByInstrumentGet(instrumentName, opts, (error, data, response) => {
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
 **instrumentName** | **String**| Instrument name | 
 **startSeq** | **Number**| The sequence number of the first trade to be returned | [optional] 
 **endSeq** | **Number**| The sequence number of the last trade to be returned | [optional] 
 **count** | **Number**| Number of requested items, default - &#x60;10&#x60; | [optional] 
 **includeOld** | **Boolean**| Include trades older than 7 days, default - &#x60;false&#x60; | [optional] 
 **sorting** | **String**| Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) | [optional] 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## publicGetOrderBookGet

> Object publicGetOrderBookGet(instrumentName, opts)

Retrieves the order book, along with other market values for a given instrument.

### Example

```javascript
import DeribitApi from 'deribit_api';
let defaultClient = DeribitApi.ApiClient.instance;
// Configure Bearer (Auth. Token) access token for authorization: bearerAuth
let bearerAuth = defaultClient.authentications['bearerAuth'];
bearerAuth.accessToken = "YOUR ACCESS TOKEN"

let apiInstance = new DeribitApi.PublicApi();
let instrumentName = "instrumentName_example"; // String | The instrument name for which to retrieve the order book, see [`getinstruments`](#getinstruments) to obtain instrument names.
let opts = {
  'depth': 3.4 // Number | The number of entries to return for bids and asks.
};
apiInstance.publicGetOrderBookGet(instrumentName, opts, (error, data, response) => {
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
 **instrumentName** | **String**| The instrument name for which to retrieve the order book, see [&#x60;getinstruments&#x60;](#getinstruments) to obtain instrument names. | 
 **depth** | **Number**| The number of entries to return for bids and asks. | [optional] 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


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

let apiInstance = new DeribitApi.PublicApi();
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


## publicGetTradeVolumesGet

> Object publicGetTradeVolumesGet()

Retrieves aggregated 24h trade volumes for different instrument types and currencies.

### Example

```javascript
import DeribitApi from 'deribit_api';
let defaultClient = DeribitApi.ApiClient.instance;
// Configure Bearer (Auth. Token) access token for authorization: bearerAuth
let bearerAuth = defaultClient.authentications['bearerAuth'];
bearerAuth.accessToken = "YOUR ACCESS TOKEN"

let apiInstance = new DeribitApi.PublicApi();
apiInstance.publicGetTradeVolumesGet((error, data, response) => {
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


## publicGetTradingviewChartDataGet

> Object publicGetTradingviewChartDataGet(instrumentName, startTimestamp, endTimestamp)

Publicly available market data used to generate a TradingView candle chart.

### Example

```javascript
import DeribitApi from 'deribit_api';
let defaultClient = DeribitApi.ApiClient.instance;
// Configure Bearer (Auth. Token) access token for authorization: bearerAuth
let bearerAuth = defaultClient.authentications['bearerAuth'];
bearerAuth.accessToken = "YOUR ACCESS TOKEN"

let apiInstance = new DeribitApi.PublicApi();
let instrumentName = BTC-PERPETUAL; // String | Instrument name
let startTimestamp = 1536569522277; // Number | The earliest timestamp to return result for
let endTimestamp = 1536569522277; // Number | The most recent timestamp to return result for
apiInstance.publicGetTradingviewChartDataGet(instrumentName, startTimestamp, endTimestamp, (error, data, response) => {
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
 **instrumentName** | **String**| Instrument name | 
 **startTimestamp** | **Number**| The earliest timestamp to return result for | 
 **endTimestamp** | **Number**| The most recent timestamp to return result for | 

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

let apiInstance = new DeribitApi.PublicApi();
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


## publicTickerGet

> Object publicTickerGet(instrumentName)

Get ticker for an instrument.

### Example

```javascript
import DeribitApi from 'deribit_api';
let defaultClient = DeribitApi.ApiClient.instance;
// Configure Bearer (Auth. Token) access token for authorization: bearerAuth
let bearerAuth = defaultClient.authentications['bearerAuth'];
bearerAuth.accessToken = "YOUR ACCESS TOKEN"

let apiInstance = new DeribitApi.PublicApi();
let instrumentName = BTC-PERPETUAL; // String | Instrument name
apiInstance.publicTickerGet(instrumentName, (error, data, response) => {
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
 **instrumentName** | **String**| Instrument name | 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## publicValidateFieldGet

> Object publicValidateFieldGet(field, value, opts)

Method used to introduce the client software connected to Deribit platform over websocket. Provided data may have an impact on the maintained connection and will be collected for internal statistical purposes. In response, Deribit will also introduce itself.

### Example

```javascript
import DeribitApi from 'deribit_api';
let defaultClient = DeribitApi.ApiClient.instance;
// Configure Bearer (Auth. Token) access token for authorization: bearerAuth
let bearerAuth = defaultClient.authentications['bearerAuth'];
bearerAuth.accessToken = "YOUR ACCESS TOKEN"

let apiInstance = new DeribitApi.PublicApi();
let field = "field_example"; // String | Name of the field to be validated, examples: postal_code, date_of_birth
let value = "value_example"; // String | Value to be checked
let opts = {
  'value2': "value2_example" // String | Additional value to be compared with
};
apiInstance.publicValidateFieldGet(field, value, opts, (error, data, response) => {
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

