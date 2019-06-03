# Org.OpenAPITools.Api.MarketDataApi

All URIs are relative to *https://www.deribit.com/api/v2*

Method | HTTP request | Description
------------- | ------------- | -------------
[**PublicGetBookSummaryByCurrencyGet**](MarketDataApi.md#publicgetbooksummarybycurrencyget) | **GET** /public/get_book_summary_by_currency | Retrieves the summary information such as open interest, 24h volume, etc. for all instruments for the currency (optionally filtered by kind).
[**PublicGetBookSummaryByInstrumentGet**](MarketDataApi.md#publicgetbooksummarybyinstrumentget) | **GET** /public/get_book_summary_by_instrument | Retrieves the summary information such as open interest, 24h volume, etc. for a specific instrument.
[**PublicGetContractSizeGet**](MarketDataApi.md#publicgetcontractsizeget) | **GET** /public/get_contract_size | Retrieves contract size of provided instrument.
[**PublicGetCurrenciesGet**](MarketDataApi.md#publicgetcurrenciesget) | **GET** /public/get_currencies | Retrieves all cryptocurrencies supported by the API.
[**PublicGetFundingChartDataGet**](MarketDataApi.md#publicgetfundingchartdataget) | **GET** /public/get_funding_chart_data | Retrieve the latest user trades that have occurred for PERPETUAL instruments in a specific currency symbol and within given time range.
[**PublicGetHistoricalVolatilityGet**](MarketDataApi.md#publicgethistoricalvolatilityget) | **GET** /public/get_historical_volatility | Provides information about historical volatility for given cryptocurrency.
[**PublicGetIndexGet**](MarketDataApi.md#publicgetindexget) | **GET** /public/get_index | Retrieves the current index price for the instruments, for the selected currency.
[**PublicGetInstrumentsGet**](MarketDataApi.md#publicgetinstrumentsget) | **GET** /public/get_instruments | Retrieves available trading instruments. This method can be used to see which instruments are available for trading, or which instruments have existed historically.
[**PublicGetLastSettlementsByCurrencyGet**](MarketDataApi.md#publicgetlastsettlementsbycurrencyget) | **GET** /public/get_last_settlements_by_currency | Retrieves historical settlement, delivery and bankruptcy events coming from all instruments within given currency.
[**PublicGetLastSettlementsByInstrumentGet**](MarketDataApi.md#publicgetlastsettlementsbyinstrumentget) | **GET** /public/get_last_settlements_by_instrument | Retrieves historical public settlement, delivery and bankruptcy events filtered by instrument name.
[**PublicGetLastTradesByCurrencyAndTimeGet**](MarketDataApi.md#publicgetlasttradesbycurrencyandtimeget) | **GET** /public/get_last_trades_by_currency_and_time | Retrieve the latest trades that have occurred for instruments in a specific currency symbol and within given time range.
[**PublicGetLastTradesByCurrencyGet**](MarketDataApi.md#publicgetlasttradesbycurrencyget) | **GET** /public/get_last_trades_by_currency | Retrieve the latest trades that have occurred for instruments in a specific currency symbol.
[**PublicGetLastTradesByInstrumentAndTimeGet**](MarketDataApi.md#publicgetlasttradesbyinstrumentandtimeget) | **GET** /public/get_last_trades_by_instrument_and_time | Retrieve the latest trades that have occurred for a specific instrument and within given time range.
[**PublicGetLastTradesByInstrumentGet**](MarketDataApi.md#publicgetlasttradesbyinstrumentget) | **GET** /public/get_last_trades_by_instrument | Retrieve the latest trades that have occurred for a specific instrument.
[**PublicGetOrderBookGet**](MarketDataApi.md#publicgetorderbookget) | **GET** /public/get_order_book | Retrieves the order book, along with other market values for a given instrument.
[**PublicGetTradeVolumesGet**](MarketDataApi.md#publicgettradevolumesget) | **GET** /public/get_trade_volumes | Retrieves aggregated 24h trade volumes for different instrument types and currencies.
[**PublicGetTradingviewChartDataGet**](MarketDataApi.md#publicgettradingviewchartdataget) | **GET** /public/get_tradingview_chart_data | Publicly available market data used to generate a TradingView candle chart.
[**PublicTickerGet**](MarketDataApi.md#publictickerget) | **GET** /public/ticker | Get ticker for an instrument.



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

            var apiInstance = new MarketDataApi(Configuration.Default);
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
                Debug.Print("Exception when calling MarketDataApi.PublicGetBookSummaryByCurrencyGet: " + e.Message );
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

            var apiInstance = new MarketDataApi(Configuration.Default);
            var instrumentName = BTC-PERPETUAL;  // string | Instrument name

            try
            {
                // Retrieves the summary information such as open interest, 24h volume, etc. for a specific instrument.
                Object result = apiInstance.PublicGetBookSummaryByInstrumentGet(instrumentName);
                Debug.WriteLine(result);
            }
            catch (ApiException e)
            {
                Debug.Print("Exception when calling MarketDataApi.PublicGetBookSummaryByInstrumentGet: " + e.Message );
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

            var apiInstance = new MarketDataApi(Configuration.Default);
            var instrumentName = BTC-PERPETUAL;  // string | Instrument name

            try
            {
                // Retrieves contract size of provided instrument.
                Object result = apiInstance.PublicGetContractSizeGet(instrumentName);
                Debug.WriteLine(result);
            }
            catch (ApiException e)
            {
                Debug.Print("Exception when calling MarketDataApi.PublicGetContractSizeGet: " + e.Message );
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

            var apiInstance = new MarketDataApi(Configuration.Default);

            try
            {
                // Retrieves all cryptocurrencies supported by the API.
                Object result = apiInstance.PublicGetCurrenciesGet();
                Debug.WriteLine(result);
            }
            catch (ApiException e)
            {
                Debug.Print("Exception when calling MarketDataApi.PublicGetCurrenciesGet: " + e.Message );
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

            var apiInstance = new MarketDataApi(Configuration.Default);
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
                Debug.Print("Exception when calling MarketDataApi.PublicGetFundingChartDataGet: " + e.Message );
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

            var apiInstance = new MarketDataApi(Configuration.Default);
            var currency = currency_example;  // string | The currency symbol

            try
            {
                // Provides information about historical volatility for given cryptocurrency.
                Object result = apiInstance.PublicGetHistoricalVolatilityGet(currency);
                Debug.WriteLine(result);
            }
            catch (ApiException e)
            {
                Debug.Print("Exception when calling MarketDataApi.PublicGetHistoricalVolatilityGet: " + e.Message );
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

            var apiInstance = new MarketDataApi(Configuration.Default);
            var currency = currency_example;  // string | The currency symbol

            try
            {
                // Retrieves the current index price for the instruments, for the selected currency.
                Object result = apiInstance.PublicGetIndexGet(currency);
                Debug.WriteLine(result);
            }
            catch (ApiException e)
            {
                Debug.Print("Exception when calling MarketDataApi.PublicGetIndexGet: " + e.Message );
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

            var apiInstance = new MarketDataApi(Configuration.Default);
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
                Debug.Print("Exception when calling MarketDataApi.PublicGetInstrumentsGet: " + e.Message );
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

            var apiInstance = new MarketDataApi(Configuration.Default);
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
                Debug.Print("Exception when calling MarketDataApi.PublicGetLastSettlementsByCurrencyGet: " + e.Message );
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

            var apiInstance = new MarketDataApi(Configuration.Default);
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
                Debug.Print("Exception when calling MarketDataApi.PublicGetLastSettlementsByInstrumentGet: " + e.Message );
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

            var apiInstance = new MarketDataApi(Configuration.Default);
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
                Debug.Print("Exception when calling MarketDataApi.PublicGetLastTradesByCurrencyAndTimeGet: " + e.Message );
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

            var apiInstance = new MarketDataApi(Configuration.Default);
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
                Debug.Print("Exception when calling MarketDataApi.PublicGetLastTradesByCurrencyGet: " + e.Message );
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

            var apiInstance = new MarketDataApi(Configuration.Default);
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
                Debug.Print("Exception when calling MarketDataApi.PublicGetLastTradesByInstrumentAndTimeGet: " + e.Message );
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

            var apiInstance = new MarketDataApi(Configuration.Default);
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
                Debug.Print("Exception when calling MarketDataApi.PublicGetLastTradesByInstrumentGet: " + e.Message );
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

            var apiInstance = new MarketDataApi(Configuration.Default);
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
                Debug.Print("Exception when calling MarketDataApi.PublicGetOrderBookGet: " + e.Message );
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

            var apiInstance = new MarketDataApi(Configuration.Default);

            try
            {
                // Retrieves aggregated 24h trade volumes for different instrument types and currencies.
                Object result = apiInstance.PublicGetTradeVolumesGet();
                Debug.WriteLine(result);
            }
            catch (ApiException e)
            {
                Debug.Print("Exception when calling MarketDataApi.PublicGetTradeVolumesGet: " + e.Message );
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

            var apiInstance = new MarketDataApi(Configuration.Default);
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
                Debug.Print("Exception when calling MarketDataApi.PublicGetTradingviewChartDataGet: " + e.Message );
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

            var apiInstance = new MarketDataApi(Configuration.Default);
            var instrumentName = BTC-PERPETUAL;  // string | Instrument name

            try
            {
                // Get ticker for an instrument.
                Object result = apiInstance.PublicTickerGet(instrumentName);
                Debug.WriteLine(result);
            }
            catch (ApiException e)
            {
                Debug.Print("Exception when calling MarketDataApi.PublicTickerGet: " + e.Message );
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

