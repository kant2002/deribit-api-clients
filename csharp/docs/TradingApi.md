# Org.OpenAPITools.Api.TradingApi

All URIs are relative to *https://www.deribit.com/api/v2*

Method | HTTP request | Description
------------- | ------------- | -------------
[**PrivateBuyGet**](TradingApi.md#privatebuyget) | **GET** /private/buy | Places a buy order for an instrument.
[**PrivateCancelAllByCurrencyGet**](TradingApi.md#privatecancelallbycurrencyget) | **GET** /private/cancel_all_by_currency | Cancels all orders by currency, optionally filtered by instrument kind and/or order type.
[**PrivateCancelAllByInstrumentGet**](TradingApi.md#privatecancelallbyinstrumentget) | **GET** /private/cancel_all_by_instrument | Cancels all orders by instrument, optionally filtered by order type.
[**PrivateCancelAllGet**](TradingApi.md#privatecancelallget) | **GET** /private/cancel_all | This method cancels all users orders and stop orders within all currencies and instrument kinds.
[**PrivateCancelGet**](TradingApi.md#privatecancelget) | **GET** /private/cancel | Cancel an order, specified by order id
[**PrivateClosePositionGet**](TradingApi.md#privateclosepositionget) | **GET** /private/close_position | Makes closing position reduce only order .
[**PrivateEditGet**](TradingApi.md#privateeditget) | **GET** /private/edit | Change price, amount and/or other properties of an order.
[**PrivateGetMarginsGet**](TradingApi.md#privategetmarginsget) | **GET** /private/get_margins | Get margins for given instrument, amount and price.
[**PrivateGetOpenOrdersByCurrencyGet**](TradingApi.md#privategetopenordersbycurrencyget) | **GET** /private/get_open_orders_by_currency | Retrieves list of user&#39;s open orders.
[**PrivateGetOpenOrdersByInstrumentGet**](TradingApi.md#privategetopenordersbyinstrumentget) | **GET** /private/get_open_orders_by_instrument | Retrieves list of user&#39;s open orders within given Instrument.
[**PrivateGetOrderHistoryByCurrencyGet**](TradingApi.md#privategetorderhistorybycurrencyget) | **GET** /private/get_order_history_by_currency | Retrieves history of orders that have been partially or fully filled.
[**PrivateGetOrderHistoryByInstrumentGet**](TradingApi.md#privategetorderhistorybyinstrumentget) | **GET** /private/get_order_history_by_instrument | Retrieves history of orders that have been partially or fully filled.
[**PrivateGetOrderMarginByIdsGet**](TradingApi.md#privategetordermarginbyidsget) | **GET** /private/get_order_margin_by_ids | Retrieves initial margins of given orders
[**PrivateGetOrderStateGet**](TradingApi.md#privategetorderstateget) | **GET** /private/get_order_state | Retrieve the current state of an order.
[**PrivateGetSettlementHistoryByCurrencyGet**](TradingApi.md#privategetsettlementhistorybycurrencyget) | **GET** /private/get_settlement_history_by_currency | Retrieves settlement, delivery and bankruptcy events that have affected your account.
[**PrivateGetSettlementHistoryByInstrumentGet**](TradingApi.md#privategetsettlementhistorybyinstrumentget) | **GET** /private/get_settlement_history_by_instrument | Retrieves public settlement, delivery and bankruptcy events filtered by instrument name
[**PrivateGetUserTradesByCurrencyAndTimeGet**](TradingApi.md#privategetusertradesbycurrencyandtimeget) | **GET** /private/get_user_trades_by_currency_and_time | Retrieve the latest user trades that have occurred for instruments in a specific currency symbol and within given time range.
[**PrivateGetUserTradesByCurrencyGet**](TradingApi.md#privategetusertradesbycurrencyget) | **GET** /private/get_user_trades_by_currency | Retrieve the latest user trades that have occurred for instruments in a specific currency symbol.
[**PrivateGetUserTradesByInstrumentAndTimeGet**](TradingApi.md#privategetusertradesbyinstrumentandtimeget) | **GET** /private/get_user_trades_by_instrument_and_time | Retrieve the latest user trades that have occurred for a specific instrument and within given time range.
[**PrivateGetUserTradesByInstrumentGet**](TradingApi.md#privategetusertradesbyinstrumentget) | **GET** /private/get_user_trades_by_instrument | Retrieve the latest user trades that have occurred for a specific instrument.
[**PrivateGetUserTradesByOrderGet**](TradingApi.md#privategetusertradesbyorderget) | **GET** /private/get_user_trades_by_order | Retrieve the list of user trades that was created for given order
[**PrivateSellGet**](TradingApi.md#privatesellget) | **GET** /private/sell | Places a sell order for an instrument.



## PrivateBuyGet

> Object PrivateBuyGet (string instrumentName, decimal? amount, string type = null, string label = null, decimal? price = null, string timeInForce = null, decimal? maxShow = null, bool? postOnly = null, bool? reduceOnly = null, decimal? stopPrice = null, string trigger = null, string advanced = null)

Places a buy order for an instrument.

### Example

```csharp
using System.Diagnostics;
using Org.OpenAPITools.Api;
using Org.OpenAPITools.Client;
using Org.OpenAPITools.Model;

namespace Example
{
    public class PrivateBuyGetExample
    {
        public static void Main()
        {
            Configuration.Default.BasePath = "https://www.deribit.com/api/v2";
            // Configure HTTP basic authorization: bearerAuth
            Configuration.Default.Username = "YOUR_USERNAME";
            Configuration.Default.Password = "YOUR_PASSWORD";

            var apiInstance = new TradingApi(Configuration.Default);
            var instrumentName = BTC-PERPETUAL;  // string | Instrument name
            var amount = 8.14;  // decimal? | It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH
            var type = type_example;  // string | The order type, default: `\"limit\"` (optional) 
            var label = label_example;  // string | user defined label for the order (maximum 32 characters) (optional) 
            var price = 8.14;  // decimal? | <p>The order price in base currency (Only for limit and stop_limit orders)</p> <p>When adding order with advanced=usd, the field price should be the option price value in USD.</p> <p>When adding order with advanced=implv, the field price should be a value of implied volatility in percentages. For example,  price=100, means implied volatility of 100%</p> (optional) 
            var timeInForce = timeInForce_example;  // string | <p>Specifies how long the order remains in effect. Default `\"good_til_cancelled\"`</p> <ul> <li>`\"good_til_cancelled\"` - unfilled order remains in order book until cancelled</li> <li>`\"fill_or_kill\"` - execute a transaction immediately and completely or not at all</li> <li>`\"immediate_or_cancel\"` - execute a transaction immediately, and any portion of the order that cannot be immediately filled is cancelled</li> </ul> (optional)  (default to good_til_cancelled)
            var maxShow = 8.14;  // decimal? | Maximum amount within an order to be shown to other customers, `0` for invisible order (optional)  (default to 1M)
            var postOnly = true;  // bool? | <p>If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.</p> <p>Only valid in combination with time_in_force=`\"good_til_cancelled\"`</p> (optional)  (default to true)
            var reduceOnly = true;  // bool? | If `true`, the order is considered reduce-only which is intended to only reduce a current position (optional)  (default to false)
            var stopPrice = 8.14;  // decimal? | Stop price, required for stop limit orders (Only for stop orders) (optional) 
            var trigger = trigger_example;  // string | Defines trigger type, required for `\"stop_limit\"` order type (optional) 
            var advanced = advanced_example;  // string | Advanced option order type. (Only for options) (optional) 

            try
            {
                // Places a buy order for an instrument.
                Object result = apiInstance.PrivateBuyGet(instrumentName, amount, type, label, price, timeInForce, maxShow, postOnly, reduceOnly, stopPrice, trigger, advanced);
                Debug.WriteLine(result);
            }
            catch (ApiException e)
            {
                Debug.Print("Exception when calling TradingApi.PrivateBuyGet: " + e.Message );
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
 **amount** | **decimal?**| It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH | 
 **type** | **string**| The order type, default: &#x60;\&quot;limit\&quot;&#x60; | [optional] 
 **label** | **string**| user defined label for the order (maximum 32 characters) | [optional] 
 **price** | **decimal?**| &lt;p&gt;The order price in base currency (Only for limit and stop_limit orders)&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;usd, the field price should be the option price value in USD.&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;implv, the field price should be a value of implied volatility in percentages. For example,  price&#x3D;100, means implied volatility of 100%&lt;/p&gt; | [optional] 
 **timeInForce** | **string**| &lt;p&gt;Specifies how long the order remains in effect. Default &#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; &lt;ul&gt; &lt;li&gt;&#x60;\&quot;good_til_cancelled\&quot;&#x60; - unfilled order remains in order book until cancelled&lt;/li&gt; &lt;li&gt;&#x60;\&quot;fill_or_kill\&quot;&#x60; - execute a transaction immediately and completely or not at all&lt;/li&gt; &lt;li&gt;&#x60;\&quot;immediate_or_cancel\&quot;&#x60; - execute a transaction immediately, and any portion of the order that cannot be immediately filled is cancelled&lt;/li&gt; &lt;/ul&gt; | [optional] [default to good_til_cancelled]
 **maxShow** | **decimal?**| Maximum amount within an order to be shown to other customers, &#x60;0&#x60; for invisible order | [optional] [default to 1M]
 **postOnly** | **bool?**| &lt;p&gt;If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.&lt;/p&gt; &lt;p&gt;Only valid in combination with time_in_force&#x3D;&#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; | [optional] [default to true]
 **reduceOnly** | **bool?**| If &#x60;true&#x60;, the order is considered reduce-only which is intended to only reduce a current position | [optional] [default to false]
 **stopPrice** | **decimal?**| Stop price, required for stop limit orders (Only for stop orders) | [optional] 
 **trigger** | **string**| Defines trigger type, required for &#x60;\&quot;stop_limit\&quot;&#x60; order type | [optional] 
 **advanced** | **string**| Advanced option order type. (Only for options) | [optional] 

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


## PrivateCancelAllByCurrencyGet

> Object PrivateCancelAllByCurrencyGet (string currency, string kind = null, string type = null)

Cancels all orders by currency, optionally filtered by instrument kind and/or order type.

### Example

```csharp
using System.Diagnostics;
using Org.OpenAPITools.Api;
using Org.OpenAPITools.Client;
using Org.OpenAPITools.Model;

namespace Example
{
    public class PrivateCancelAllByCurrencyGetExample
    {
        public static void Main()
        {
            Configuration.Default.BasePath = "https://www.deribit.com/api/v2";
            // Configure HTTP basic authorization: bearerAuth
            Configuration.Default.Username = "YOUR_USERNAME";
            Configuration.Default.Password = "YOUR_PASSWORD";

            var apiInstance = new TradingApi(Configuration.Default);
            var currency = currency_example;  // string | The currency symbol
            var kind = kind_example;  // string | Instrument kind, if not provided instruments of all kinds are considered (optional) 
            var type = type_example;  // string | Order type - limit, stop or all, default - `all` (optional) 

            try
            {
                // Cancels all orders by currency, optionally filtered by instrument kind and/or order type.
                Object result = apiInstance.PrivateCancelAllByCurrencyGet(currency, kind, type);
                Debug.WriteLine(result);
            }
            catch (ApiException e)
            {
                Debug.Print("Exception when calling TradingApi.PrivateCancelAllByCurrencyGet: " + e.Message );
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
 **type** | **string**| Order type - limit, stop or all, default - &#x60;all&#x60; | [optional] 

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


## PrivateCancelAllByInstrumentGet

> Object PrivateCancelAllByInstrumentGet (string instrumentName, string type = null)

Cancels all orders by instrument, optionally filtered by order type.

### Example

```csharp
using System.Diagnostics;
using Org.OpenAPITools.Api;
using Org.OpenAPITools.Client;
using Org.OpenAPITools.Model;

namespace Example
{
    public class PrivateCancelAllByInstrumentGetExample
    {
        public static void Main()
        {
            Configuration.Default.BasePath = "https://www.deribit.com/api/v2";
            // Configure HTTP basic authorization: bearerAuth
            Configuration.Default.Username = "YOUR_USERNAME";
            Configuration.Default.Password = "YOUR_PASSWORD";

            var apiInstance = new TradingApi(Configuration.Default);
            var instrumentName = BTC-PERPETUAL;  // string | Instrument name
            var type = type_example;  // string | Order type - limit, stop or all, default - `all` (optional) 

            try
            {
                // Cancels all orders by instrument, optionally filtered by order type.
                Object result = apiInstance.PrivateCancelAllByInstrumentGet(instrumentName, type);
                Debug.WriteLine(result);
            }
            catch (ApiException e)
            {
                Debug.Print("Exception when calling TradingApi.PrivateCancelAllByInstrumentGet: " + e.Message );
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
 **type** | **string**| Order type - limit, stop or all, default - &#x60;all&#x60; | [optional] 

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


## PrivateCancelAllGet

> Object PrivateCancelAllGet ()

This method cancels all users orders and stop orders within all currencies and instrument kinds.

### Example

```csharp
using System.Diagnostics;
using Org.OpenAPITools.Api;
using Org.OpenAPITools.Client;
using Org.OpenAPITools.Model;

namespace Example
{
    public class PrivateCancelAllGetExample
    {
        public static void Main()
        {
            Configuration.Default.BasePath = "https://www.deribit.com/api/v2";
            // Configure HTTP basic authorization: bearerAuth
            Configuration.Default.Username = "YOUR_USERNAME";
            Configuration.Default.Password = "YOUR_PASSWORD";

            var apiInstance = new TradingApi(Configuration.Default);

            try
            {
                // This method cancels all users orders and stop orders within all currencies and instrument kinds.
                Object result = apiInstance.PrivateCancelAllGet();
                Debug.WriteLine(result);
            }
            catch (ApiException e)
            {
                Debug.Print("Exception when calling TradingApi.PrivateCancelAllGet: " + e.Message );
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


## PrivateCancelGet

> Object PrivateCancelGet (string orderId)

Cancel an order, specified by order id

### Example

```csharp
using System.Diagnostics;
using Org.OpenAPITools.Api;
using Org.OpenAPITools.Client;
using Org.OpenAPITools.Model;

namespace Example
{
    public class PrivateCancelGetExample
    {
        public static void Main()
        {
            Configuration.Default.BasePath = "https://www.deribit.com/api/v2";
            // Configure HTTP basic authorization: bearerAuth
            Configuration.Default.Username = "YOUR_USERNAME";
            Configuration.Default.Password = "YOUR_PASSWORD";

            var apiInstance = new TradingApi(Configuration.Default);
            var orderId = ETH-100234;  // string | The order id

            try
            {
                // Cancel an order, specified by order id
                Object result = apiInstance.PrivateCancelGet(orderId);
                Debug.WriteLine(result);
            }
            catch (ApiException e)
            {
                Debug.Print("Exception when calling TradingApi.PrivateCancelGet: " + e.Message );
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
 **orderId** | **string**| The order id | 

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


## PrivateClosePositionGet

> Object PrivateClosePositionGet (string instrumentName, string type, decimal? price = null)

Makes closing position reduce only order .

### Example

```csharp
using System.Diagnostics;
using Org.OpenAPITools.Api;
using Org.OpenAPITools.Client;
using Org.OpenAPITools.Model;

namespace Example
{
    public class PrivateClosePositionGetExample
    {
        public static void Main()
        {
            Configuration.Default.BasePath = "https://www.deribit.com/api/v2";
            // Configure HTTP basic authorization: bearerAuth
            Configuration.Default.Username = "YOUR_USERNAME";
            Configuration.Default.Password = "YOUR_PASSWORD";

            var apiInstance = new TradingApi(Configuration.Default);
            var instrumentName = BTC-PERPETUAL;  // string | Instrument name
            var type = type_example;  // string | The order type
            var price = 8.14;  // decimal? | Optional price for limit order. (optional) 

            try
            {
                // Makes closing position reduce only order .
                Object result = apiInstance.PrivateClosePositionGet(instrumentName, type, price);
                Debug.WriteLine(result);
            }
            catch (ApiException e)
            {
                Debug.Print("Exception when calling TradingApi.PrivateClosePositionGet: " + e.Message );
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
 **type** | **string**| The order type | 
 **price** | **decimal?**| Optional price for limit order. | [optional] 

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


## PrivateEditGet

> Object PrivateEditGet (string orderId, decimal? amount, decimal? price, bool? postOnly = null, string advanced = null, decimal? stopPrice = null)

Change price, amount and/or other properties of an order.

### Example

```csharp
using System.Diagnostics;
using Org.OpenAPITools.Api;
using Org.OpenAPITools.Client;
using Org.OpenAPITools.Model;

namespace Example
{
    public class PrivateEditGetExample
    {
        public static void Main()
        {
            Configuration.Default.BasePath = "https://www.deribit.com/api/v2";
            // Configure HTTP basic authorization: bearerAuth
            Configuration.Default.Username = "YOUR_USERNAME";
            Configuration.Default.Password = "YOUR_PASSWORD";

            var apiInstance = new TradingApi(Configuration.Default);
            var orderId = ETH-100234;  // string | The order id
            var amount = 8.14;  // decimal? | It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH
            var price = 8.14;  // decimal? | <p>The order price in base currency.</p> <p>When editing an option order with advanced=usd, the field price should be the option price value in USD.</p> <p>When editing an option order with advanced=implv, the field price should be a value of implied volatility in percentages. For example,  price=100, means implied volatility of 100%</p>
            var postOnly = true;  // bool? | <p>If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.</p> <p>Only valid in combination with time_in_force=`\"good_til_cancelled\"`</p> (optional)  (default to true)
            var advanced = advanced_example;  // string | Advanced option order type. If you have posted an advanced option order, it is necessary to re-supply this parameter when editing it (Only for options) (optional) 
            var stopPrice = 8.14;  // decimal? | Stop price, required for stop limit orders (Only for stop orders) (optional) 

            try
            {
                // Change price, amount and/or other properties of an order.
                Object result = apiInstance.PrivateEditGet(orderId, amount, price, postOnly, advanced, stopPrice);
                Debug.WriteLine(result);
            }
            catch (ApiException e)
            {
                Debug.Print("Exception when calling TradingApi.PrivateEditGet: " + e.Message );
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
 **orderId** | **string**| The order id | 
 **amount** | **decimal?**| It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH | 
 **price** | **decimal?**| &lt;p&gt;The order price in base currency.&lt;/p&gt; &lt;p&gt;When editing an option order with advanced&#x3D;usd, the field price should be the option price value in USD.&lt;/p&gt; &lt;p&gt;When editing an option order with advanced&#x3D;implv, the field price should be a value of implied volatility in percentages. For example,  price&#x3D;100, means implied volatility of 100%&lt;/p&gt; | 
 **postOnly** | **bool?**| &lt;p&gt;If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.&lt;/p&gt; &lt;p&gt;Only valid in combination with time_in_force&#x3D;&#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; | [optional] [default to true]
 **advanced** | **string**| Advanced option order type. If you have posted an advanced option order, it is necessary to re-supply this parameter when editing it (Only for options) | [optional] 
 **stopPrice** | **decimal?**| Stop price, required for stop limit orders (Only for stop orders) | [optional] 

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


## PrivateGetMarginsGet

> Object PrivateGetMarginsGet (string instrumentName, decimal? amount, decimal? price)

Get margins for given instrument, amount and price.

### Example

```csharp
using System.Diagnostics;
using Org.OpenAPITools.Api;
using Org.OpenAPITools.Client;
using Org.OpenAPITools.Model;

namespace Example
{
    public class PrivateGetMarginsGetExample
    {
        public static void Main()
        {
            Configuration.Default.BasePath = "https://www.deribit.com/api/v2";
            // Configure HTTP basic authorization: bearerAuth
            Configuration.Default.Username = "YOUR_USERNAME";
            Configuration.Default.Password = "YOUR_PASSWORD";

            var apiInstance = new TradingApi(Configuration.Default);
            var instrumentName = BTC-PERPETUAL;  // string | Instrument name
            var amount = 1;  // decimal? | Amount, integer for future, float for option. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH.
            var price = 8.14;  // decimal? | Price

            try
            {
                // Get margins for given instrument, amount and price.
                Object result = apiInstance.PrivateGetMarginsGet(instrumentName, amount, price);
                Debug.WriteLine(result);
            }
            catch (ApiException e)
            {
                Debug.Print("Exception when calling TradingApi.PrivateGetMarginsGet: " + e.Message );
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
 **amount** | **decimal?**| Amount, integer for future, float for option. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH. | 
 **price** | **decimal?**| Price | 

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


## PrivateGetOpenOrdersByCurrencyGet

> Object PrivateGetOpenOrdersByCurrencyGet (string currency, string kind = null, string type = null)

Retrieves list of user's open orders.

### Example

```csharp
using System.Diagnostics;
using Org.OpenAPITools.Api;
using Org.OpenAPITools.Client;
using Org.OpenAPITools.Model;

namespace Example
{
    public class PrivateGetOpenOrdersByCurrencyGetExample
    {
        public static void Main()
        {
            Configuration.Default.BasePath = "https://www.deribit.com/api/v2";
            // Configure HTTP basic authorization: bearerAuth
            Configuration.Default.Username = "YOUR_USERNAME";
            Configuration.Default.Password = "YOUR_PASSWORD";

            var apiInstance = new TradingApi(Configuration.Default);
            var currency = currency_example;  // string | The currency symbol
            var kind = kind_example;  // string | Instrument kind, if not provided instruments of all kinds are considered (optional) 
            var type = type_example;  // string | Order type, default - `all` (optional) 

            try
            {
                // Retrieves list of user's open orders.
                Object result = apiInstance.PrivateGetOpenOrdersByCurrencyGet(currency, kind, type);
                Debug.WriteLine(result);
            }
            catch (ApiException e)
            {
                Debug.Print("Exception when calling TradingApi.PrivateGetOpenOrdersByCurrencyGet: " + e.Message );
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
 **type** | **string**| Order type, default - &#x60;all&#x60; | [optional] 

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


## PrivateGetOpenOrdersByInstrumentGet

> Object PrivateGetOpenOrdersByInstrumentGet (string instrumentName, string type = null)

Retrieves list of user's open orders within given Instrument.

### Example

```csharp
using System.Diagnostics;
using Org.OpenAPITools.Api;
using Org.OpenAPITools.Client;
using Org.OpenAPITools.Model;

namespace Example
{
    public class PrivateGetOpenOrdersByInstrumentGetExample
    {
        public static void Main()
        {
            Configuration.Default.BasePath = "https://www.deribit.com/api/v2";
            // Configure HTTP basic authorization: bearerAuth
            Configuration.Default.Username = "YOUR_USERNAME";
            Configuration.Default.Password = "YOUR_PASSWORD";

            var apiInstance = new TradingApi(Configuration.Default);
            var instrumentName = BTC-PERPETUAL;  // string | Instrument name
            var type = type_example;  // string | Order type, default - `all` (optional) 

            try
            {
                // Retrieves list of user's open orders within given Instrument.
                Object result = apiInstance.PrivateGetOpenOrdersByInstrumentGet(instrumentName, type);
                Debug.WriteLine(result);
            }
            catch (ApiException e)
            {
                Debug.Print("Exception when calling TradingApi.PrivateGetOpenOrdersByInstrumentGet: " + e.Message );
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
 **type** | **string**| Order type, default - &#x60;all&#x60; | [optional] 

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


## PrivateGetOrderHistoryByCurrencyGet

> Object PrivateGetOrderHistoryByCurrencyGet (string currency, string kind = null, int? count = null, int? offset = null, bool? includeOld = null, bool? includeUnfilled = null)

Retrieves history of orders that have been partially or fully filled.

### Example

```csharp
using System.Diagnostics;
using Org.OpenAPITools.Api;
using Org.OpenAPITools.Client;
using Org.OpenAPITools.Model;

namespace Example
{
    public class PrivateGetOrderHistoryByCurrencyGetExample
    {
        public static void Main()
        {
            Configuration.Default.BasePath = "https://www.deribit.com/api/v2";
            // Configure HTTP basic authorization: bearerAuth
            Configuration.Default.Username = "YOUR_USERNAME";
            Configuration.Default.Password = "YOUR_PASSWORD";

            var apiInstance = new TradingApi(Configuration.Default);
            var currency = currency_example;  // string | The currency symbol
            var kind = kind_example;  // string | Instrument kind, if not provided instruments of all kinds are considered (optional) 
            var count = 56;  // int? | Number of requested items, default - `20` (optional) 
            var offset = 10;  // int? | The offset for pagination, default - `0` (optional) 
            var includeOld = false;  // bool? | Include in result orders older than 2 days, default - `false` (optional) 
            var includeUnfilled = false;  // bool? | Include in result fully unfilled closed orders, default - `false` (optional) 

            try
            {
                // Retrieves history of orders that have been partially or fully filled.
                Object result = apiInstance.PrivateGetOrderHistoryByCurrencyGet(currency, kind, count, offset, includeOld, includeUnfilled);
                Debug.WriteLine(result);
            }
            catch (ApiException e)
            {
                Debug.Print("Exception when calling TradingApi.PrivateGetOrderHistoryByCurrencyGet: " + e.Message );
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
 **count** | **int?**| Number of requested items, default - &#x60;20&#x60; | [optional] 
 **offset** | **int?**| The offset for pagination, default - &#x60;0&#x60; | [optional] 
 **includeOld** | **bool?**| Include in result orders older than 2 days, default - &#x60;false&#x60; | [optional] 
 **includeUnfilled** | **bool?**| Include in result fully unfilled closed orders, default - &#x60;false&#x60; | [optional] 

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


## PrivateGetOrderHistoryByInstrumentGet

> Object PrivateGetOrderHistoryByInstrumentGet (string instrumentName, int? count = null, int? offset = null, bool? includeOld = null, bool? includeUnfilled = null)

Retrieves history of orders that have been partially or fully filled.

### Example

```csharp
using System.Diagnostics;
using Org.OpenAPITools.Api;
using Org.OpenAPITools.Client;
using Org.OpenAPITools.Model;

namespace Example
{
    public class PrivateGetOrderHistoryByInstrumentGetExample
    {
        public static void Main()
        {
            Configuration.Default.BasePath = "https://www.deribit.com/api/v2";
            // Configure HTTP basic authorization: bearerAuth
            Configuration.Default.Username = "YOUR_USERNAME";
            Configuration.Default.Password = "YOUR_PASSWORD";

            var apiInstance = new TradingApi(Configuration.Default);
            var instrumentName = BTC-PERPETUAL;  // string | Instrument name
            var count = 56;  // int? | Number of requested items, default - `20` (optional) 
            var offset = 10;  // int? | The offset for pagination, default - `0` (optional) 
            var includeOld = false;  // bool? | Include in result orders older than 2 days, default - `false` (optional) 
            var includeUnfilled = false;  // bool? | Include in result fully unfilled closed orders, default - `false` (optional) 

            try
            {
                // Retrieves history of orders that have been partially or fully filled.
                Object result = apiInstance.PrivateGetOrderHistoryByInstrumentGet(instrumentName, count, offset, includeOld, includeUnfilled);
                Debug.WriteLine(result);
            }
            catch (ApiException e)
            {
                Debug.Print("Exception when calling TradingApi.PrivateGetOrderHistoryByInstrumentGet: " + e.Message );
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
 **count** | **int?**| Number of requested items, default - &#x60;20&#x60; | [optional] 
 **offset** | **int?**| The offset for pagination, default - &#x60;0&#x60; | [optional] 
 **includeOld** | **bool?**| Include in result orders older than 2 days, default - &#x60;false&#x60; | [optional] 
 **includeUnfilled** | **bool?**| Include in result fully unfilled closed orders, default - &#x60;false&#x60; | [optional] 

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


## PrivateGetOrderMarginByIdsGet

> Object PrivateGetOrderMarginByIdsGet (List<string> ids)

Retrieves initial margins of given orders

### Example

```csharp
using System.Diagnostics;
using Org.OpenAPITools.Api;
using Org.OpenAPITools.Client;
using Org.OpenAPITools.Model;

namespace Example
{
    public class PrivateGetOrderMarginByIdsGetExample
    {
        public static void Main()
        {
            Configuration.Default.BasePath = "https://www.deribit.com/api/v2";
            // Configure HTTP basic authorization: bearerAuth
            Configuration.Default.Username = "YOUR_USERNAME";
            Configuration.Default.Password = "YOUR_PASSWORD";

            var apiInstance = new TradingApi(Configuration.Default);
            var ids = new List<string>(); // List<string> | Ids of orders

            try
            {
                // Retrieves initial margins of given orders
                Object result = apiInstance.PrivateGetOrderMarginByIdsGet(ids);
                Debug.WriteLine(result);
            }
            catch (ApiException e)
            {
                Debug.Print("Exception when calling TradingApi.PrivateGetOrderMarginByIdsGet: " + e.Message );
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
 **ids** | [**List&lt;string&gt;**](string.md)| Ids of orders | 

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


## PrivateGetOrderStateGet

> Object PrivateGetOrderStateGet (string orderId)

Retrieve the current state of an order.

### Example

```csharp
using System.Diagnostics;
using Org.OpenAPITools.Api;
using Org.OpenAPITools.Client;
using Org.OpenAPITools.Model;

namespace Example
{
    public class PrivateGetOrderStateGetExample
    {
        public static void Main()
        {
            Configuration.Default.BasePath = "https://www.deribit.com/api/v2";
            // Configure HTTP basic authorization: bearerAuth
            Configuration.Default.Username = "YOUR_USERNAME";
            Configuration.Default.Password = "YOUR_PASSWORD";

            var apiInstance = new TradingApi(Configuration.Default);
            var orderId = ETH-100234;  // string | The order id

            try
            {
                // Retrieve the current state of an order.
                Object result = apiInstance.PrivateGetOrderStateGet(orderId);
                Debug.WriteLine(result);
            }
            catch (ApiException e)
            {
                Debug.Print("Exception when calling TradingApi.PrivateGetOrderStateGet: " + e.Message );
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
 **orderId** | **string**| The order id | 

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


## PrivateGetSettlementHistoryByCurrencyGet

> Object PrivateGetSettlementHistoryByCurrencyGet (string currency, string type = null, int? count = null)

Retrieves settlement, delivery and bankruptcy events that have affected your account.

### Example

```csharp
using System.Diagnostics;
using Org.OpenAPITools.Api;
using Org.OpenAPITools.Client;
using Org.OpenAPITools.Model;

namespace Example
{
    public class PrivateGetSettlementHistoryByCurrencyGetExample
    {
        public static void Main()
        {
            Configuration.Default.BasePath = "https://www.deribit.com/api/v2";
            // Configure HTTP basic authorization: bearerAuth
            Configuration.Default.Username = "YOUR_USERNAME";
            Configuration.Default.Password = "YOUR_PASSWORD";

            var apiInstance = new TradingApi(Configuration.Default);
            var currency = currency_example;  // string | The currency symbol
            var type = type_example;  // string | Settlement type (optional) 
            var count = 56;  // int? | Number of requested items, default - `20` (optional) 

            try
            {
                // Retrieves settlement, delivery and bankruptcy events that have affected your account.
                Object result = apiInstance.PrivateGetSettlementHistoryByCurrencyGet(currency, type, count);
                Debug.WriteLine(result);
            }
            catch (ApiException e)
            {
                Debug.Print("Exception when calling TradingApi.PrivateGetSettlementHistoryByCurrencyGet: " + e.Message );
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


## PrivateGetSettlementHistoryByInstrumentGet

> Object PrivateGetSettlementHistoryByInstrumentGet (string instrumentName, string type = null, int? count = null)

Retrieves public settlement, delivery and bankruptcy events filtered by instrument name

### Example

```csharp
using System.Diagnostics;
using Org.OpenAPITools.Api;
using Org.OpenAPITools.Client;
using Org.OpenAPITools.Model;

namespace Example
{
    public class PrivateGetSettlementHistoryByInstrumentGetExample
    {
        public static void Main()
        {
            Configuration.Default.BasePath = "https://www.deribit.com/api/v2";
            // Configure HTTP basic authorization: bearerAuth
            Configuration.Default.Username = "YOUR_USERNAME";
            Configuration.Default.Password = "YOUR_PASSWORD";

            var apiInstance = new TradingApi(Configuration.Default);
            var instrumentName = BTC-PERPETUAL;  // string | Instrument name
            var type = type_example;  // string | Settlement type (optional) 
            var count = 56;  // int? | Number of requested items, default - `20` (optional) 

            try
            {
                // Retrieves public settlement, delivery and bankruptcy events filtered by instrument name
                Object result = apiInstance.PrivateGetSettlementHistoryByInstrumentGet(instrumentName, type, count);
                Debug.WriteLine(result);
            }
            catch (ApiException e)
            {
                Debug.Print("Exception when calling TradingApi.PrivateGetSettlementHistoryByInstrumentGet: " + e.Message );
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


## PrivateGetUserTradesByCurrencyAndTimeGet

> Object PrivateGetUserTradesByCurrencyAndTimeGet (string currency, int? startTimestamp, int? endTimestamp, string kind = null, int? count = null, bool? includeOld = null, string sorting = null)

Retrieve the latest user trades that have occurred for instruments in a specific currency symbol and within given time range.

### Example

```csharp
using System.Diagnostics;
using Org.OpenAPITools.Api;
using Org.OpenAPITools.Client;
using Org.OpenAPITools.Model;

namespace Example
{
    public class PrivateGetUserTradesByCurrencyAndTimeGetExample
    {
        public static void Main()
        {
            Configuration.Default.BasePath = "https://www.deribit.com/api/v2";
            // Configure HTTP basic authorization: bearerAuth
            Configuration.Default.Username = "YOUR_USERNAME";
            Configuration.Default.Password = "YOUR_PASSWORD";

            var apiInstance = new TradingApi(Configuration.Default);
            var currency = currency_example;  // string | The currency symbol
            var startTimestamp = 1536569522277;  // int? | The earliest timestamp to return result for
            var endTimestamp = 1536569522277;  // int? | The most recent timestamp to return result for
            var kind = kind_example;  // string | Instrument kind, if not provided instruments of all kinds are considered (optional) 
            var count = 56;  // int? | Number of requested items, default - `10` (optional) 
            var includeOld = true;  // bool? | Include trades older than 7 days, default - `false` (optional) 
            var sorting = sorting_example;  // string | Direction of results sorting (`default` value means no sorting, results will be returned in order in which they left the database) (optional) 

            try
            {
                // Retrieve the latest user trades that have occurred for instruments in a specific currency symbol and within given time range.
                Object result = apiInstance.PrivateGetUserTradesByCurrencyAndTimeGet(currency, startTimestamp, endTimestamp, kind, count, includeOld, sorting);
                Debug.WriteLine(result);
            }
            catch (ApiException e)
            {
                Debug.Print("Exception when calling TradingApi.PrivateGetUserTradesByCurrencyAndTimeGet: " + e.Message );
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


## PrivateGetUserTradesByCurrencyGet

> Object PrivateGetUserTradesByCurrencyGet (string currency, string kind = null, string startId = null, string endId = null, int? count = null, bool? includeOld = null, string sorting = null)

Retrieve the latest user trades that have occurred for instruments in a specific currency symbol.

### Example

```csharp
using System.Diagnostics;
using Org.OpenAPITools.Api;
using Org.OpenAPITools.Client;
using Org.OpenAPITools.Model;

namespace Example
{
    public class PrivateGetUserTradesByCurrencyGetExample
    {
        public static void Main()
        {
            Configuration.Default.BasePath = "https://www.deribit.com/api/v2";
            // Configure HTTP basic authorization: bearerAuth
            Configuration.Default.Username = "YOUR_USERNAME";
            Configuration.Default.Password = "YOUR_PASSWORD";

            var apiInstance = new TradingApi(Configuration.Default);
            var currency = currency_example;  // string | The currency symbol
            var kind = kind_example;  // string | Instrument kind, if not provided instruments of all kinds are considered (optional) 
            var startId = startId_example;  // string | The ID number of the first trade to be returned (optional) 
            var endId = endId_example;  // string | The ID number of the last trade to be returned (optional) 
            var count = 56;  // int? | Number of requested items, default - `10` (optional) 
            var includeOld = true;  // bool? | Include trades older than 7 days, default - `false` (optional) 
            var sorting = sorting_example;  // string | Direction of results sorting (`default` value means no sorting, results will be returned in order in which they left the database) (optional) 

            try
            {
                // Retrieve the latest user trades that have occurred for instruments in a specific currency symbol.
                Object result = apiInstance.PrivateGetUserTradesByCurrencyGet(currency, kind, startId, endId, count, includeOld, sorting);
                Debug.WriteLine(result);
            }
            catch (ApiException e)
            {
                Debug.Print("Exception when calling TradingApi.PrivateGetUserTradesByCurrencyGet: " + e.Message );
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


## PrivateGetUserTradesByInstrumentAndTimeGet

> Object PrivateGetUserTradesByInstrumentAndTimeGet (string instrumentName, int? startTimestamp, int? endTimestamp, int? count = null, bool? includeOld = null, string sorting = null)

Retrieve the latest user trades that have occurred for a specific instrument and within given time range.

### Example

```csharp
using System.Diagnostics;
using Org.OpenAPITools.Api;
using Org.OpenAPITools.Client;
using Org.OpenAPITools.Model;

namespace Example
{
    public class PrivateGetUserTradesByInstrumentAndTimeGetExample
    {
        public static void Main()
        {
            Configuration.Default.BasePath = "https://www.deribit.com/api/v2";
            // Configure HTTP basic authorization: bearerAuth
            Configuration.Default.Username = "YOUR_USERNAME";
            Configuration.Default.Password = "YOUR_PASSWORD";

            var apiInstance = new TradingApi(Configuration.Default);
            var instrumentName = BTC-PERPETUAL;  // string | Instrument name
            var startTimestamp = 1536569522277;  // int? | The earliest timestamp to return result for
            var endTimestamp = 1536569522277;  // int? | The most recent timestamp to return result for
            var count = 56;  // int? | Number of requested items, default - `10` (optional) 
            var includeOld = true;  // bool? | Include trades older than 7 days, default - `false` (optional) 
            var sorting = sorting_example;  // string | Direction of results sorting (`default` value means no sorting, results will be returned in order in which they left the database) (optional) 

            try
            {
                // Retrieve the latest user trades that have occurred for a specific instrument and within given time range.
                Object result = apiInstance.PrivateGetUserTradesByInstrumentAndTimeGet(instrumentName, startTimestamp, endTimestamp, count, includeOld, sorting);
                Debug.WriteLine(result);
            }
            catch (ApiException e)
            {
                Debug.Print("Exception when calling TradingApi.PrivateGetUserTradesByInstrumentAndTimeGet: " + e.Message );
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


## PrivateGetUserTradesByInstrumentGet

> Object PrivateGetUserTradesByInstrumentGet (string instrumentName, int? startSeq = null, int? endSeq = null, int? count = null, bool? includeOld = null, string sorting = null)

Retrieve the latest user trades that have occurred for a specific instrument.

### Example

```csharp
using System.Diagnostics;
using Org.OpenAPITools.Api;
using Org.OpenAPITools.Client;
using Org.OpenAPITools.Model;

namespace Example
{
    public class PrivateGetUserTradesByInstrumentGetExample
    {
        public static void Main()
        {
            Configuration.Default.BasePath = "https://www.deribit.com/api/v2";
            // Configure HTTP basic authorization: bearerAuth
            Configuration.Default.Username = "YOUR_USERNAME";
            Configuration.Default.Password = "YOUR_PASSWORD";

            var apiInstance = new TradingApi(Configuration.Default);
            var instrumentName = BTC-PERPETUAL;  // string | Instrument name
            var startSeq = 56;  // int? | The sequence number of the first trade to be returned (optional) 
            var endSeq = 56;  // int? | The sequence number of the last trade to be returned (optional) 
            var count = 56;  // int? | Number of requested items, default - `10` (optional) 
            var includeOld = true;  // bool? | Include trades older than 7 days, default - `false` (optional) 
            var sorting = sorting_example;  // string | Direction of results sorting (`default` value means no sorting, results will be returned in order in which they left the database) (optional) 

            try
            {
                // Retrieve the latest user trades that have occurred for a specific instrument.
                Object result = apiInstance.PrivateGetUserTradesByInstrumentGet(instrumentName, startSeq, endSeq, count, includeOld, sorting);
                Debug.WriteLine(result);
            }
            catch (ApiException e)
            {
                Debug.Print("Exception when calling TradingApi.PrivateGetUserTradesByInstrumentGet: " + e.Message );
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


## PrivateGetUserTradesByOrderGet

> Object PrivateGetUserTradesByOrderGet (string orderId, string sorting = null)

Retrieve the list of user trades that was created for given order

### Example

```csharp
using System.Diagnostics;
using Org.OpenAPITools.Api;
using Org.OpenAPITools.Client;
using Org.OpenAPITools.Model;

namespace Example
{
    public class PrivateGetUserTradesByOrderGetExample
    {
        public static void Main()
        {
            Configuration.Default.BasePath = "https://www.deribit.com/api/v2";
            // Configure HTTP basic authorization: bearerAuth
            Configuration.Default.Username = "YOUR_USERNAME";
            Configuration.Default.Password = "YOUR_PASSWORD";

            var apiInstance = new TradingApi(Configuration.Default);
            var orderId = ETH-100234;  // string | The order id
            var sorting = sorting_example;  // string | Direction of results sorting (`default` value means no sorting, results will be returned in order in which they left the database) (optional) 

            try
            {
                // Retrieve the list of user trades that was created for given order
                Object result = apiInstance.PrivateGetUserTradesByOrderGet(orderId, sorting);
                Debug.WriteLine(result);
            }
            catch (ApiException e)
            {
                Debug.Print("Exception when calling TradingApi.PrivateGetUserTradesByOrderGet: " + e.Message );
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
 **orderId** | **string**| The order id | 
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


## PrivateSellGet

> Object PrivateSellGet (string instrumentName, decimal? amount, string type = null, string label = null, decimal? price = null, string timeInForce = null, decimal? maxShow = null, bool? postOnly = null, bool? reduceOnly = null, decimal? stopPrice = null, string trigger = null, string advanced = null)

Places a sell order for an instrument.

### Example

```csharp
using System.Diagnostics;
using Org.OpenAPITools.Api;
using Org.OpenAPITools.Client;
using Org.OpenAPITools.Model;

namespace Example
{
    public class PrivateSellGetExample
    {
        public static void Main()
        {
            Configuration.Default.BasePath = "https://www.deribit.com/api/v2";
            // Configure HTTP basic authorization: bearerAuth
            Configuration.Default.Username = "YOUR_USERNAME";
            Configuration.Default.Password = "YOUR_PASSWORD";

            var apiInstance = new TradingApi(Configuration.Default);
            var instrumentName = BTC-PERPETUAL;  // string | Instrument name
            var amount = 8.14;  // decimal? | It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH
            var type = type_example;  // string | The order type, default: `\"limit\"` (optional) 
            var label = label_example;  // string | user defined label for the order (maximum 32 characters) (optional) 
            var price = 8.14;  // decimal? | <p>The order price in base currency (Only for limit and stop_limit orders)</p> <p>When adding order with advanced=usd, the field price should be the option price value in USD.</p> <p>When adding order with advanced=implv, the field price should be a value of implied volatility in percentages. For example,  price=100, means implied volatility of 100%</p> (optional) 
            var timeInForce = timeInForce_example;  // string | <p>Specifies how long the order remains in effect. Default `\"good_til_cancelled\"`</p> <ul> <li>`\"good_til_cancelled\"` - unfilled order remains in order book until cancelled</li> <li>`\"fill_or_kill\"` - execute a transaction immediately and completely or not at all</li> <li>`\"immediate_or_cancel\"` - execute a transaction immediately, and any portion of the order that cannot be immediately filled is cancelled</li> </ul> (optional)  (default to good_til_cancelled)
            var maxShow = 8.14;  // decimal? | Maximum amount within an order to be shown to other customers, `0` for invisible order (optional)  (default to 1M)
            var postOnly = true;  // bool? | <p>If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.</p> <p>Only valid in combination with time_in_force=`\"good_til_cancelled\"`</p> (optional)  (default to true)
            var reduceOnly = true;  // bool? | If `true`, the order is considered reduce-only which is intended to only reduce a current position (optional)  (default to false)
            var stopPrice = 8.14;  // decimal? | Stop price, required for stop limit orders (Only for stop orders) (optional) 
            var trigger = trigger_example;  // string | Defines trigger type, required for `\"stop_limit\"` order type (optional) 
            var advanced = advanced_example;  // string | Advanced option order type. (Only for options) (optional) 

            try
            {
                // Places a sell order for an instrument.
                Object result = apiInstance.PrivateSellGet(instrumentName, amount, type, label, price, timeInForce, maxShow, postOnly, reduceOnly, stopPrice, trigger, advanced);
                Debug.WriteLine(result);
            }
            catch (ApiException e)
            {
                Debug.Print("Exception when calling TradingApi.PrivateSellGet: " + e.Message );
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
 **amount** | **decimal?**| It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH | 
 **type** | **string**| The order type, default: &#x60;\&quot;limit\&quot;&#x60; | [optional] 
 **label** | **string**| user defined label for the order (maximum 32 characters) | [optional] 
 **price** | **decimal?**| &lt;p&gt;The order price in base currency (Only for limit and stop_limit orders)&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;usd, the field price should be the option price value in USD.&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;implv, the field price should be a value of implied volatility in percentages. For example,  price&#x3D;100, means implied volatility of 100%&lt;/p&gt; | [optional] 
 **timeInForce** | **string**| &lt;p&gt;Specifies how long the order remains in effect. Default &#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; &lt;ul&gt; &lt;li&gt;&#x60;\&quot;good_til_cancelled\&quot;&#x60; - unfilled order remains in order book until cancelled&lt;/li&gt; &lt;li&gt;&#x60;\&quot;fill_or_kill\&quot;&#x60; - execute a transaction immediately and completely or not at all&lt;/li&gt; &lt;li&gt;&#x60;\&quot;immediate_or_cancel\&quot;&#x60; - execute a transaction immediately, and any portion of the order that cannot be immediately filled is cancelled&lt;/li&gt; &lt;/ul&gt; | [optional] [default to good_til_cancelled]
 **maxShow** | **decimal?**| Maximum amount within an order to be shown to other customers, &#x60;0&#x60; for invisible order | [optional] [default to 1M]
 **postOnly** | **bool?**| &lt;p&gt;If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.&lt;/p&gt; &lt;p&gt;Only valid in combination with time_in_force&#x3D;&#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; | [optional] [default to true]
 **reduceOnly** | **bool?**| If &#x60;true&#x60;, the order is considered reduce-only which is intended to only reduce a current position | [optional] [default to false]
 **stopPrice** | **decimal?**| Stop price, required for stop limit orders (Only for stop orders) | [optional] 
 **trigger** | **string**| Defines trigger type, required for &#x60;\&quot;stop_limit\&quot;&#x60; order type | [optional] 
 **advanced** | **string**| Advanced option order type. (Only for options) | [optional] 

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

