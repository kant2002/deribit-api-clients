# MarketDataApi

All URIs are relative to *https://www.deribit.com/api/v2*

Method | HTTP request | Description
------------- | ------------- | -------------
[**publicGetBookSummaryByCurrencyGet**](MarketDataApi.md#publicGetBookSummaryByCurrencyGet) | **GET** /public/get_book_summary_by_currency | Retrieves the summary information such as open interest, 24h volume, etc. for all instruments for the currency (optionally filtered by kind).
[**publicGetBookSummaryByInstrumentGet**](MarketDataApi.md#publicGetBookSummaryByInstrumentGet) | **GET** /public/get_book_summary_by_instrument | Retrieves the summary information such as open interest, 24h volume, etc. for a specific instrument.
[**publicGetContractSizeGet**](MarketDataApi.md#publicGetContractSizeGet) | **GET** /public/get_contract_size | Retrieves contract size of provided instrument.
[**publicGetCurrenciesGet**](MarketDataApi.md#publicGetCurrenciesGet) | **GET** /public/get_currencies | Retrieves all cryptocurrencies supported by the API.
[**publicGetFundingChartDataGet**](MarketDataApi.md#publicGetFundingChartDataGet) | **GET** /public/get_funding_chart_data | Retrieve the latest user trades that have occurred for PERPETUAL instruments in a specific currency symbol and within given time range.
[**publicGetHistoricalVolatilityGet**](MarketDataApi.md#publicGetHistoricalVolatilityGet) | **GET** /public/get_historical_volatility | Provides information about historical volatility for given cryptocurrency.
[**publicGetIndexGet**](MarketDataApi.md#publicGetIndexGet) | **GET** /public/get_index | Retrieves the current index price for the instruments, for the selected currency.
[**publicGetInstrumentsGet**](MarketDataApi.md#publicGetInstrumentsGet) | **GET** /public/get_instruments | Retrieves available trading instruments. This method can be used to see which instruments are available for trading, or which instruments have existed historically.
[**publicGetLastSettlementsByCurrencyGet**](MarketDataApi.md#publicGetLastSettlementsByCurrencyGet) | **GET** /public/get_last_settlements_by_currency | Retrieves historical settlement, delivery and bankruptcy events coming from all instruments within given currency.
[**publicGetLastSettlementsByInstrumentGet**](MarketDataApi.md#publicGetLastSettlementsByInstrumentGet) | **GET** /public/get_last_settlements_by_instrument | Retrieves historical public settlement, delivery and bankruptcy events filtered by instrument name.
[**publicGetLastTradesByCurrencyAndTimeGet**](MarketDataApi.md#publicGetLastTradesByCurrencyAndTimeGet) | **GET** /public/get_last_trades_by_currency_and_time | Retrieve the latest trades that have occurred for instruments in a specific currency symbol and within given time range.
[**publicGetLastTradesByCurrencyGet**](MarketDataApi.md#publicGetLastTradesByCurrencyGet) | **GET** /public/get_last_trades_by_currency | Retrieve the latest trades that have occurred for instruments in a specific currency symbol.
[**publicGetLastTradesByInstrumentAndTimeGet**](MarketDataApi.md#publicGetLastTradesByInstrumentAndTimeGet) | **GET** /public/get_last_trades_by_instrument_and_time | Retrieve the latest trades that have occurred for a specific instrument and within given time range.
[**publicGetLastTradesByInstrumentGet**](MarketDataApi.md#publicGetLastTradesByInstrumentGet) | **GET** /public/get_last_trades_by_instrument | Retrieve the latest trades that have occurred for a specific instrument.
[**publicGetOrderBookGet**](MarketDataApi.md#publicGetOrderBookGet) | **GET** /public/get_order_book | Retrieves the order book, along with other market values for a given instrument.
[**publicGetTradeVolumesGet**](MarketDataApi.md#publicGetTradeVolumesGet) | **GET** /public/get_trade_volumes | Retrieves aggregated 24h trade volumes for different instrument types and currencies.
[**publicGetTradingviewChartDataGet**](MarketDataApi.md#publicGetTradingviewChartDataGet) | **GET** /public/get_tradingview_chart_data | Publicly available market data used to generate a TradingView candle chart.
[**publicTickerGet**](MarketDataApi.md#publicTickerGet) | **GET** /public/ticker | Get ticker for an instrument.


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
import org.openapitools.client.api.MarketDataApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://www.deribit.com/api/v2");
    
    // Configure HTTP basic authorization: bearerAuth
    HttpBasicAuth bearerAuth = (HttpBasicAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setUsername("YOUR USERNAME");
    bearerAuth.setPassword("YOUR PASSWORD");

    MarketDataApi apiInstance = new MarketDataApi(defaultClient);
    String currency = "currency_example"; // String | The currency symbol
    String kind = "kind_example"; // String | Instrument kind, if not provided instruments of all kinds are considered
    try {
      Object result = apiInstance.publicGetBookSummaryByCurrencyGet(currency, kind);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling MarketDataApi#publicGetBookSummaryByCurrencyGet");
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
import org.openapitools.client.api.MarketDataApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://www.deribit.com/api/v2");
    
    // Configure HTTP basic authorization: bearerAuth
    HttpBasicAuth bearerAuth = (HttpBasicAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setUsername("YOUR USERNAME");
    bearerAuth.setPassword("YOUR PASSWORD");

    MarketDataApi apiInstance = new MarketDataApi(defaultClient);
    String instrumentName = BTC-PERPETUAL; // String | Instrument name
    try {
      Object result = apiInstance.publicGetBookSummaryByInstrumentGet(instrumentName);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling MarketDataApi#publicGetBookSummaryByInstrumentGet");
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
import org.openapitools.client.api.MarketDataApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://www.deribit.com/api/v2");
    
    // Configure HTTP basic authorization: bearerAuth
    HttpBasicAuth bearerAuth = (HttpBasicAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setUsername("YOUR USERNAME");
    bearerAuth.setPassword("YOUR PASSWORD");

    MarketDataApi apiInstance = new MarketDataApi(defaultClient);
    String instrumentName = BTC-PERPETUAL; // String | Instrument name
    try {
      Object result = apiInstance.publicGetContractSizeGet(instrumentName);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling MarketDataApi#publicGetContractSizeGet");
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
import org.openapitools.client.api.MarketDataApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://www.deribit.com/api/v2");
    
    // Configure HTTP basic authorization: bearerAuth
    HttpBasicAuth bearerAuth = (HttpBasicAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setUsername("YOUR USERNAME");
    bearerAuth.setPassword("YOUR PASSWORD");

    MarketDataApi apiInstance = new MarketDataApi(defaultClient);
    try {
      Object result = apiInstance.publicGetCurrenciesGet();
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling MarketDataApi#publicGetCurrenciesGet");
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
import org.openapitools.client.api.MarketDataApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://www.deribit.com/api/v2");
    
    // Configure HTTP basic authorization: bearerAuth
    HttpBasicAuth bearerAuth = (HttpBasicAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setUsername("YOUR USERNAME");
    bearerAuth.setPassword("YOUR PASSWORD");

    MarketDataApi apiInstance = new MarketDataApi(defaultClient);
    String instrumentName = BTC-PERPETUAL; // String | Instrument name
    String length = "length_example"; // String | Specifies time period. `8h` - 8 hours, `24h` - 24 hours
    try {
      Object result = apiInstance.publicGetFundingChartDataGet(instrumentName, length);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling MarketDataApi#publicGetFundingChartDataGet");
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
import org.openapitools.client.api.MarketDataApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://www.deribit.com/api/v2");
    
    // Configure HTTP basic authorization: bearerAuth
    HttpBasicAuth bearerAuth = (HttpBasicAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setUsername("YOUR USERNAME");
    bearerAuth.setPassword("YOUR PASSWORD");

    MarketDataApi apiInstance = new MarketDataApi(defaultClient);
    String currency = "currency_example"; // String | The currency symbol
    try {
      Object result = apiInstance.publicGetHistoricalVolatilityGet(currency);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling MarketDataApi#publicGetHistoricalVolatilityGet");
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
import org.openapitools.client.api.MarketDataApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://www.deribit.com/api/v2");
    
    // Configure HTTP basic authorization: bearerAuth
    HttpBasicAuth bearerAuth = (HttpBasicAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setUsername("YOUR USERNAME");
    bearerAuth.setPassword("YOUR PASSWORD");

    MarketDataApi apiInstance = new MarketDataApi(defaultClient);
    String currency = "currency_example"; // String | The currency symbol
    try {
      Object result = apiInstance.publicGetIndexGet(currency);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling MarketDataApi#publicGetIndexGet");
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
import org.openapitools.client.api.MarketDataApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://www.deribit.com/api/v2");
    
    // Configure HTTP basic authorization: bearerAuth
    HttpBasicAuth bearerAuth = (HttpBasicAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setUsername("YOUR USERNAME");
    bearerAuth.setPassword("YOUR PASSWORD");

    MarketDataApi apiInstance = new MarketDataApi(defaultClient);
    String currency = "currency_example"; // String | The currency symbol
    String kind = "kind_example"; // String | Instrument kind, if not provided instruments of all kinds are considered
    Boolean expired = false; // Boolean | Set to true to show expired instruments instead of active ones.
    try {
      Object result = apiInstance.publicGetInstrumentsGet(currency, kind, expired);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling MarketDataApi#publicGetInstrumentsGet");
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
import org.openapitools.client.api.MarketDataApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://www.deribit.com/api/v2");
    
    // Configure HTTP basic authorization: bearerAuth
    HttpBasicAuth bearerAuth = (HttpBasicAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setUsername("YOUR USERNAME");
    bearerAuth.setPassword("YOUR PASSWORD");

    MarketDataApi apiInstance = new MarketDataApi(defaultClient);
    String currency = "currency_example"; // String | The currency symbol
    String type = "type_example"; // String | Settlement type
    Integer count = 56; // Integer | Number of requested items, default - `20`
    String continuation = xY7T6cutS3t2B9YtaDkE6TS379oKnkzTvmEDUnEUP2Msa9xKWNNaT; // String | Continuation token for pagination
    Integer searchStartTimestamp = 1536569522277; // Integer | The latest timestamp to return result for
    try {
      Object result = apiInstance.publicGetLastSettlementsByCurrencyGet(currency, type, count, continuation, searchStartTimestamp);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling MarketDataApi#publicGetLastSettlementsByCurrencyGet");
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
import org.openapitools.client.api.MarketDataApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://www.deribit.com/api/v2");
    
    // Configure HTTP basic authorization: bearerAuth
    HttpBasicAuth bearerAuth = (HttpBasicAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setUsername("YOUR USERNAME");
    bearerAuth.setPassword("YOUR PASSWORD");

    MarketDataApi apiInstance = new MarketDataApi(defaultClient);
    String instrumentName = BTC-PERPETUAL; // String | Instrument name
    String type = "type_example"; // String | Settlement type
    Integer count = 56; // Integer | Number of requested items, default - `20`
    String continuation = xY7T6cutS3t2B9YtaDkE6TS379oKnkzTvmEDUnEUP2Msa9xKWNNaT; // String | Continuation token for pagination
    Integer searchStartTimestamp = 1536569522277; // Integer | The latest timestamp to return result for
    try {
      Object result = apiInstance.publicGetLastSettlementsByInstrumentGet(instrumentName, type, count, continuation, searchStartTimestamp);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling MarketDataApi#publicGetLastSettlementsByInstrumentGet");
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
import org.openapitools.client.api.MarketDataApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://www.deribit.com/api/v2");
    
    // Configure HTTP basic authorization: bearerAuth
    HttpBasicAuth bearerAuth = (HttpBasicAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setUsername("YOUR USERNAME");
    bearerAuth.setPassword("YOUR PASSWORD");

    MarketDataApi apiInstance = new MarketDataApi(defaultClient);
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
      System.err.println("Exception when calling MarketDataApi#publicGetLastTradesByCurrencyAndTimeGet");
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
import org.openapitools.client.api.MarketDataApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://www.deribit.com/api/v2");
    
    // Configure HTTP basic authorization: bearerAuth
    HttpBasicAuth bearerAuth = (HttpBasicAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setUsername("YOUR USERNAME");
    bearerAuth.setPassword("YOUR PASSWORD");

    MarketDataApi apiInstance = new MarketDataApi(defaultClient);
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
      System.err.println("Exception when calling MarketDataApi#publicGetLastTradesByCurrencyGet");
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
import org.openapitools.client.api.MarketDataApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://www.deribit.com/api/v2");
    
    // Configure HTTP basic authorization: bearerAuth
    HttpBasicAuth bearerAuth = (HttpBasicAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setUsername("YOUR USERNAME");
    bearerAuth.setPassword("YOUR PASSWORD");

    MarketDataApi apiInstance = new MarketDataApi(defaultClient);
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
      System.err.println("Exception when calling MarketDataApi#publicGetLastTradesByInstrumentAndTimeGet");
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
import org.openapitools.client.api.MarketDataApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://www.deribit.com/api/v2");
    
    // Configure HTTP basic authorization: bearerAuth
    HttpBasicAuth bearerAuth = (HttpBasicAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setUsername("YOUR USERNAME");
    bearerAuth.setPassword("YOUR PASSWORD");

    MarketDataApi apiInstance = new MarketDataApi(defaultClient);
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
      System.err.println("Exception when calling MarketDataApi#publicGetLastTradesByInstrumentGet");
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
import org.openapitools.client.api.MarketDataApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://www.deribit.com/api/v2");
    
    // Configure HTTP basic authorization: bearerAuth
    HttpBasicAuth bearerAuth = (HttpBasicAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setUsername("YOUR USERNAME");
    bearerAuth.setPassword("YOUR PASSWORD");

    MarketDataApi apiInstance = new MarketDataApi(defaultClient);
    String instrumentName = "instrumentName_example"; // String | The instrument name for which to retrieve the order book, see [`getinstruments`](#getinstruments) to obtain instrument names.
    BigDecimal depth = new BigDecimal(); // BigDecimal | The number of entries to return for bids and asks.
    try {
      Object result = apiInstance.publicGetOrderBookGet(instrumentName, depth);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling MarketDataApi#publicGetOrderBookGet");
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
import org.openapitools.client.api.MarketDataApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://www.deribit.com/api/v2");
    
    // Configure HTTP basic authorization: bearerAuth
    HttpBasicAuth bearerAuth = (HttpBasicAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setUsername("YOUR USERNAME");
    bearerAuth.setPassword("YOUR PASSWORD");

    MarketDataApi apiInstance = new MarketDataApi(defaultClient);
    try {
      Object result = apiInstance.publicGetTradeVolumesGet();
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling MarketDataApi#publicGetTradeVolumesGet");
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
import org.openapitools.client.api.MarketDataApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://www.deribit.com/api/v2");
    
    // Configure HTTP basic authorization: bearerAuth
    HttpBasicAuth bearerAuth = (HttpBasicAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setUsername("YOUR USERNAME");
    bearerAuth.setPassword("YOUR PASSWORD");

    MarketDataApi apiInstance = new MarketDataApi(defaultClient);
    String instrumentName = BTC-PERPETUAL; // String | Instrument name
    Integer startTimestamp = 1536569522277; // Integer | The earliest timestamp to return result for
    Integer endTimestamp = 1536569522277; // Integer | The most recent timestamp to return result for
    try {
      Object result = apiInstance.publicGetTradingviewChartDataGet(instrumentName, startTimestamp, endTimestamp);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling MarketDataApi#publicGetTradingviewChartDataGet");
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
import org.openapitools.client.api.MarketDataApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://www.deribit.com/api/v2");
    
    // Configure HTTP basic authorization: bearerAuth
    HttpBasicAuth bearerAuth = (HttpBasicAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setUsername("YOUR USERNAME");
    bearerAuth.setPassword("YOUR PASSWORD");

    MarketDataApi apiInstance = new MarketDataApi(defaultClient);
    String instrumentName = BTC-PERPETUAL; // String | Instrument name
    try {
      Object result = apiInstance.publicTickerGet(instrumentName);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling MarketDataApi#publicTickerGet");
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

