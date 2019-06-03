# TradingApi

All URIs are relative to *https://www.deribit.com/api/v2*

Method | HTTP request | Description
------------- | ------------- | -------------
[**privateBuyGet**](TradingApi.md#privateBuyGet) | **GET** /private/buy | Places a buy order for an instrument.
[**privateCancelAllByCurrencyGet**](TradingApi.md#privateCancelAllByCurrencyGet) | **GET** /private/cancel_all_by_currency | Cancels all orders by currency, optionally filtered by instrument kind and/or order type.
[**privateCancelAllByInstrumentGet**](TradingApi.md#privateCancelAllByInstrumentGet) | **GET** /private/cancel_all_by_instrument | Cancels all orders by instrument, optionally filtered by order type.
[**privateCancelAllGet**](TradingApi.md#privateCancelAllGet) | **GET** /private/cancel_all | This method cancels all users orders and stop orders within all currencies and instrument kinds.
[**privateCancelGet**](TradingApi.md#privateCancelGet) | **GET** /private/cancel | Cancel an order, specified by order id
[**privateClosePositionGet**](TradingApi.md#privateClosePositionGet) | **GET** /private/close_position | Makes closing position reduce only order .
[**privateEditGet**](TradingApi.md#privateEditGet) | **GET** /private/edit | Change price, amount and/or other properties of an order.
[**privateGetMarginsGet**](TradingApi.md#privateGetMarginsGet) | **GET** /private/get_margins | Get margins for given instrument, amount and price.
[**privateGetOpenOrdersByCurrencyGet**](TradingApi.md#privateGetOpenOrdersByCurrencyGet) | **GET** /private/get_open_orders_by_currency | Retrieves list of user&#39;s open orders.
[**privateGetOpenOrdersByInstrumentGet**](TradingApi.md#privateGetOpenOrdersByInstrumentGet) | **GET** /private/get_open_orders_by_instrument | Retrieves list of user&#39;s open orders within given Instrument.
[**privateGetOrderHistoryByCurrencyGet**](TradingApi.md#privateGetOrderHistoryByCurrencyGet) | **GET** /private/get_order_history_by_currency | Retrieves history of orders that have been partially or fully filled.
[**privateGetOrderHistoryByInstrumentGet**](TradingApi.md#privateGetOrderHistoryByInstrumentGet) | **GET** /private/get_order_history_by_instrument | Retrieves history of orders that have been partially or fully filled.
[**privateGetOrderMarginByIdsGet**](TradingApi.md#privateGetOrderMarginByIdsGet) | **GET** /private/get_order_margin_by_ids | Retrieves initial margins of given orders
[**privateGetOrderStateGet**](TradingApi.md#privateGetOrderStateGet) | **GET** /private/get_order_state | Retrieve the current state of an order.
[**privateGetSettlementHistoryByCurrencyGet**](TradingApi.md#privateGetSettlementHistoryByCurrencyGet) | **GET** /private/get_settlement_history_by_currency | Retrieves settlement, delivery and bankruptcy events that have affected your account.
[**privateGetSettlementHistoryByInstrumentGet**](TradingApi.md#privateGetSettlementHistoryByInstrumentGet) | **GET** /private/get_settlement_history_by_instrument | Retrieves public settlement, delivery and bankruptcy events filtered by instrument name
[**privateGetUserTradesByCurrencyAndTimeGet**](TradingApi.md#privateGetUserTradesByCurrencyAndTimeGet) | **GET** /private/get_user_trades_by_currency_and_time | Retrieve the latest user trades that have occurred for instruments in a specific currency symbol and within given time range.
[**privateGetUserTradesByCurrencyGet**](TradingApi.md#privateGetUserTradesByCurrencyGet) | **GET** /private/get_user_trades_by_currency | Retrieve the latest user trades that have occurred for instruments in a specific currency symbol.
[**privateGetUserTradesByInstrumentAndTimeGet**](TradingApi.md#privateGetUserTradesByInstrumentAndTimeGet) | **GET** /private/get_user_trades_by_instrument_and_time | Retrieve the latest user trades that have occurred for a specific instrument and within given time range.
[**privateGetUserTradesByInstrumentGet**](TradingApi.md#privateGetUserTradesByInstrumentGet) | **GET** /private/get_user_trades_by_instrument | Retrieve the latest user trades that have occurred for a specific instrument.
[**privateGetUserTradesByOrderGet**](TradingApi.md#privateGetUserTradesByOrderGet) | **GET** /private/get_user_trades_by_order | Retrieve the list of user trades that was created for given order
[**privateSellGet**](TradingApi.md#privateSellGet) | **GET** /private/sell | Places a sell order for an instrument.


<a name="privateBuyGet"></a>
# **privateBuyGet**
> Object privateBuyGet(instrumentName, amount, type, label, price, timeInForce, maxShow, postOnly, reduceOnly, stopPrice, trigger, advanced)

Places a buy order for an instrument.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.TradingApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://www.deribit.com/api/v2");
    
    // Configure HTTP basic authorization: bearerAuth
    HttpBasicAuth bearerAuth = (HttpBasicAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setUsername("YOUR USERNAME");
    bearerAuth.setPassword("YOUR PASSWORD");

    TradingApi apiInstance = new TradingApi(defaultClient);
    String instrumentName = BTC-PERPETUAL; // String | Instrument name
    BigDecimal amount = new BigDecimal(); // BigDecimal | It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH
    String type = "type_example"; // String | The order type, default: `\"limit\"`
    String label = "label_example"; // String | user defined label for the order (maximum 32 characters)
    BigDecimal price = new BigDecimal(); // BigDecimal | <p>The order price in base currency (Only for limit and stop_limit orders)</p> <p>When adding order with advanced=usd, the field price should be the option price value in USD.</p> <p>When adding order with advanced=implv, the field price should be a value of implied volatility in percentages. For example,  price=100, means implied volatility of 100%</p>
    String timeInForce = "good_til_cancelled"; // String | <p>Specifies how long the order remains in effect. Default `\"good_til_cancelled\"`</p> <ul> <li>`\"good_til_cancelled\"` - unfilled order remains in order book until cancelled</li> <li>`\"fill_or_kill\"` - execute a transaction immediately and completely or not at all</li> <li>`\"immediate_or_cancel\"` - execute a transaction immediately, and any portion of the order that cannot be immediately filled is cancelled</li> </ul>
    BigDecimal maxShow = new BigDecimal(); // BigDecimal | Maximum amount within an order to be shown to other customers, `0` for invisible order
    Boolean postOnly = true; // Boolean | <p>If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.</p> <p>Only valid in combination with time_in_force=`\"good_til_cancelled\"`</p>
    Boolean reduceOnly = false; // Boolean | If `true`, the order is considered reduce-only which is intended to only reduce a current position
    BigDecimal stopPrice = new BigDecimal(); // BigDecimal | Stop price, required for stop limit orders (Only for stop orders)
    String trigger = "trigger_example"; // String | Defines trigger type, required for `\"stop_limit\"` order type
    String advanced = "advanced_example"; // String | Advanced option order type. (Only for options)
    try {
      Object result = apiInstance.privateBuyGet(instrumentName, amount, type, label, price, timeInForce, maxShow, postOnly, reduceOnly, stopPrice, trigger, advanced);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling TradingApi#privateBuyGet");
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
 **amount** | **BigDecimal**| It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH |
 **type** | **String**| The order type, default: &#x60;\&quot;limit\&quot;&#x60; | [optional] [enum: limit, stop_limit, market, stop_market]
 **label** | **String**| user defined label for the order (maximum 32 characters) | [optional]
 **price** | **BigDecimal**| &lt;p&gt;The order price in base currency (Only for limit and stop_limit orders)&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;usd, the field price should be the option price value in USD.&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;implv, the field price should be a value of implied volatility in percentages. For example,  price&#x3D;100, means implied volatility of 100%&lt;/p&gt; | [optional]
 **timeInForce** | **String**| &lt;p&gt;Specifies how long the order remains in effect. Default &#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; &lt;ul&gt; &lt;li&gt;&#x60;\&quot;good_til_cancelled\&quot;&#x60; - unfilled order remains in order book until cancelled&lt;/li&gt; &lt;li&gt;&#x60;\&quot;fill_or_kill\&quot;&#x60; - execute a transaction immediately and completely or not at all&lt;/li&gt; &lt;li&gt;&#x60;\&quot;immediate_or_cancel\&quot;&#x60; - execute a transaction immediately, and any portion of the order that cannot be immediately filled is cancelled&lt;/li&gt; &lt;/ul&gt; | [optional] [default to good_til_cancelled] [enum: good_til_cancelled, fill_or_kill, immediate_or_cancel]
 **maxShow** | **BigDecimal**| Maximum amount within an order to be shown to other customers, &#x60;0&#x60; for invisible order | [optional] [default to 1d]
 **postOnly** | **Boolean**| &lt;p&gt;If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.&lt;/p&gt; &lt;p&gt;Only valid in combination with time_in_force&#x3D;&#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; | [optional] [default to true]
 **reduceOnly** | **Boolean**| If &#x60;true&#x60;, the order is considered reduce-only which is intended to only reduce a current position | [optional] [default to false]
 **stopPrice** | **BigDecimal**| Stop price, required for stop limit orders (Only for stop orders) | [optional]
 **trigger** | **String**| Defines trigger type, required for &#x60;\&quot;stop_limit\&quot;&#x60; order type | [optional] [enum: index_price, mark_price, last_price]
 **advanced** | **String**| Advanced option order type. (Only for options) | [optional] [enum: usd, implv]

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

<a name="privateCancelAllByCurrencyGet"></a>
# **privateCancelAllByCurrencyGet**
> Object privateCancelAllByCurrencyGet(currency, kind, type)

Cancels all orders by currency, optionally filtered by instrument kind and/or order type.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.TradingApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://www.deribit.com/api/v2");
    
    // Configure HTTP basic authorization: bearerAuth
    HttpBasicAuth bearerAuth = (HttpBasicAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setUsername("YOUR USERNAME");
    bearerAuth.setPassword("YOUR PASSWORD");

    TradingApi apiInstance = new TradingApi(defaultClient);
    String currency = "currency_example"; // String | The currency symbol
    String kind = "kind_example"; // String | Instrument kind, if not provided instruments of all kinds are considered
    String type = "type_example"; // String | Order type - limit, stop or all, default - `all`
    try {
      Object result = apiInstance.privateCancelAllByCurrencyGet(currency, kind, type);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling TradingApi#privateCancelAllByCurrencyGet");
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
 **type** | **String**| Order type - limit, stop or all, default - &#x60;all&#x60; | [optional] [enum: all, limit, stop]

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

<a name="privateCancelAllByInstrumentGet"></a>
# **privateCancelAllByInstrumentGet**
> Object privateCancelAllByInstrumentGet(instrumentName, type)

Cancels all orders by instrument, optionally filtered by order type.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.TradingApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://www.deribit.com/api/v2");
    
    // Configure HTTP basic authorization: bearerAuth
    HttpBasicAuth bearerAuth = (HttpBasicAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setUsername("YOUR USERNAME");
    bearerAuth.setPassword("YOUR PASSWORD");

    TradingApi apiInstance = new TradingApi(defaultClient);
    String instrumentName = BTC-PERPETUAL; // String | Instrument name
    String type = "type_example"; // String | Order type - limit, stop or all, default - `all`
    try {
      Object result = apiInstance.privateCancelAllByInstrumentGet(instrumentName, type);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling TradingApi#privateCancelAllByInstrumentGet");
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
 **type** | **String**| Order type - limit, stop or all, default - &#x60;all&#x60; | [optional] [enum: all, limit, stop]

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

<a name="privateCancelAllGet"></a>
# **privateCancelAllGet**
> Object privateCancelAllGet()

This method cancels all users orders and stop orders within all currencies and instrument kinds.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.TradingApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://www.deribit.com/api/v2");
    
    // Configure HTTP basic authorization: bearerAuth
    HttpBasicAuth bearerAuth = (HttpBasicAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setUsername("YOUR USERNAME");
    bearerAuth.setPassword("YOUR PASSWORD");

    TradingApi apiInstance = new TradingApi(defaultClient);
    try {
      Object result = apiInstance.privateCancelAllGet();
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling TradingApi#privateCancelAllGet");
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

<a name="privateCancelGet"></a>
# **privateCancelGet**
> Object privateCancelGet(orderId)

Cancel an order, specified by order id

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.TradingApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://www.deribit.com/api/v2");
    
    // Configure HTTP basic authorization: bearerAuth
    HttpBasicAuth bearerAuth = (HttpBasicAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setUsername("YOUR USERNAME");
    bearerAuth.setPassword("YOUR PASSWORD");

    TradingApi apiInstance = new TradingApi(defaultClient);
    String orderId = ETH-100234; // String | The order id
    try {
      Object result = apiInstance.privateCancelGet(orderId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling TradingApi#privateCancelGet");
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
 **orderId** | **String**| The order id |

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

<a name="privateClosePositionGet"></a>
# **privateClosePositionGet**
> Object privateClosePositionGet(instrumentName, type, price)

Makes closing position reduce only order .

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.TradingApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://www.deribit.com/api/v2");
    
    // Configure HTTP basic authorization: bearerAuth
    HttpBasicAuth bearerAuth = (HttpBasicAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setUsername("YOUR USERNAME");
    bearerAuth.setPassword("YOUR PASSWORD");

    TradingApi apiInstance = new TradingApi(defaultClient);
    String instrumentName = BTC-PERPETUAL; // String | Instrument name
    String type = "type_example"; // String | The order type
    BigDecimal price = new BigDecimal(); // BigDecimal | Optional price for limit order.
    try {
      Object result = apiInstance.privateClosePositionGet(instrumentName, type, price);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling TradingApi#privateClosePositionGet");
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
 **type** | **String**| The order type | [enum: limit, market]
 **price** | **BigDecimal**| Optional price for limit order. | [optional]

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

<a name="privateEditGet"></a>
# **privateEditGet**
> Object privateEditGet(orderId, amount, price, postOnly, advanced, stopPrice)

Change price, amount and/or other properties of an order.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.TradingApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://www.deribit.com/api/v2");
    
    // Configure HTTP basic authorization: bearerAuth
    HttpBasicAuth bearerAuth = (HttpBasicAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setUsername("YOUR USERNAME");
    bearerAuth.setPassword("YOUR PASSWORD");

    TradingApi apiInstance = new TradingApi(defaultClient);
    String orderId = ETH-100234; // String | The order id
    BigDecimal amount = new BigDecimal(); // BigDecimal | It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH
    BigDecimal price = new BigDecimal(); // BigDecimal | <p>The order price in base currency.</p> <p>When editing an option order with advanced=usd, the field price should be the option price value in USD.</p> <p>When editing an option order with advanced=implv, the field price should be a value of implied volatility in percentages. For example,  price=100, means implied volatility of 100%</p>
    Boolean postOnly = true; // Boolean | <p>If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.</p> <p>Only valid in combination with time_in_force=`\"good_til_cancelled\"`</p>
    String advanced = "advanced_example"; // String | Advanced option order type. If you have posted an advanced option order, it is necessary to re-supply this parameter when editing it (Only for options)
    BigDecimal stopPrice = new BigDecimal(); // BigDecimal | Stop price, required for stop limit orders (Only for stop orders)
    try {
      Object result = apiInstance.privateEditGet(orderId, amount, price, postOnly, advanced, stopPrice);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling TradingApi#privateEditGet");
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
 **orderId** | **String**| The order id |
 **amount** | **BigDecimal**| It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH |
 **price** | **BigDecimal**| &lt;p&gt;The order price in base currency.&lt;/p&gt; &lt;p&gt;When editing an option order with advanced&#x3D;usd, the field price should be the option price value in USD.&lt;/p&gt; &lt;p&gt;When editing an option order with advanced&#x3D;implv, the field price should be a value of implied volatility in percentages. For example,  price&#x3D;100, means implied volatility of 100%&lt;/p&gt; |
 **postOnly** | **Boolean**| &lt;p&gt;If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.&lt;/p&gt; &lt;p&gt;Only valid in combination with time_in_force&#x3D;&#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; | [optional] [default to true]
 **advanced** | **String**| Advanced option order type. If you have posted an advanced option order, it is necessary to re-supply this parameter when editing it (Only for options) | [optional] [enum: usd, implv]
 **stopPrice** | **BigDecimal**| Stop price, required for stop limit orders (Only for stop orders) | [optional]

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

<a name="privateGetMarginsGet"></a>
# **privateGetMarginsGet**
> Object privateGetMarginsGet(instrumentName, amount, price)

Get margins for given instrument, amount and price.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.TradingApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://www.deribit.com/api/v2");
    
    // Configure HTTP basic authorization: bearerAuth
    HttpBasicAuth bearerAuth = (HttpBasicAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setUsername("YOUR USERNAME");
    bearerAuth.setPassword("YOUR PASSWORD");

    TradingApi apiInstance = new TradingApi(defaultClient);
    String instrumentName = BTC-PERPETUAL; // String | Instrument name
    BigDecimal amount = 1; // BigDecimal | Amount, integer for future, float for option. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH.
    BigDecimal price = new BigDecimal(); // BigDecimal | Price
    try {
      Object result = apiInstance.privateGetMarginsGet(instrumentName, amount, price);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling TradingApi#privateGetMarginsGet");
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
 **amount** | **BigDecimal**| Amount, integer for future, float for option. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH. |
 **price** | **BigDecimal**| Price |

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

<a name="privateGetOpenOrdersByCurrencyGet"></a>
# **privateGetOpenOrdersByCurrencyGet**
> Object privateGetOpenOrdersByCurrencyGet(currency, kind, type)

Retrieves list of user&#39;s open orders.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.TradingApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://www.deribit.com/api/v2");
    
    // Configure HTTP basic authorization: bearerAuth
    HttpBasicAuth bearerAuth = (HttpBasicAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setUsername("YOUR USERNAME");
    bearerAuth.setPassword("YOUR PASSWORD");

    TradingApi apiInstance = new TradingApi(defaultClient);
    String currency = "currency_example"; // String | The currency symbol
    String kind = "kind_example"; // String | Instrument kind, if not provided instruments of all kinds are considered
    String type = "type_example"; // String | Order type, default - `all`
    try {
      Object result = apiInstance.privateGetOpenOrdersByCurrencyGet(currency, kind, type);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling TradingApi#privateGetOpenOrdersByCurrencyGet");
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
 **type** | **String**| Order type, default - &#x60;all&#x60; | [optional] [enum: all, limit, stop_all, stop_limit, stop_market]

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

<a name="privateGetOpenOrdersByInstrumentGet"></a>
# **privateGetOpenOrdersByInstrumentGet**
> Object privateGetOpenOrdersByInstrumentGet(instrumentName, type)

Retrieves list of user&#39;s open orders within given Instrument.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.TradingApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://www.deribit.com/api/v2");
    
    // Configure HTTP basic authorization: bearerAuth
    HttpBasicAuth bearerAuth = (HttpBasicAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setUsername("YOUR USERNAME");
    bearerAuth.setPassword("YOUR PASSWORD");

    TradingApi apiInstance = new TradingApi(defaultClient);
    String instrumentName = BTC-PERPETUAL; // String | Instrument name
    String type = "type_example"; // String | Order type, default - `all`
    try {
      Object result = apiInstance.privateGetOpenOrdersByInstrumentGet(instrumentName, type);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling TradingApi#privateGetOpenOrdersByInstrumentGet");
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
 **type** | **String**| Order type, default - &#x60;all&#x60; | [optional] [enum: all, limit, stop_all, stop_limit, stop_market]

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

<a name="privateGetOrderHistoryByCurrencyGet"></a>
# **privateGetOrderHistoryByCurrencyGet**
> Object privateGetOrderHistoryByCurrencyGet(currency, kind, count, offset, includeOld, includeUnfilled)

Retrieves history of orders that have been partially or fully filled.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.TradingApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://www.deribit.com/api/v2");
    
    // Configure HTTP basic authorization: bearerAuth
    HttpBasicAuth bearerAuth = (HttpBasicAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setUsername("YOUR USERNAME");
    bearerAuth.setPassword("YOUR PASSWORD");

    TradingApi apiInstance = new TradingApi(defaultClient);
    String currency = "currency_example"; // String | The currency symbol
    String kind = "kind_example"; // String | Instrument kind, if not provided instruments of all kinds are considered
    Integer count = 56; // Integer | Number of requested items, default - `20`
    Integer offset = 10; // Integer | The offset for pagination, default - `0`
    Boolean includeOld = false; // Boolean | Include in result orders older than 2 days, default - `false`
    Boolean includeUnfilled = false; // Boolean | Include in result fully unfilled closed orders, default - `false`
    try {
      Object result = apiInstance.privateGetOrderHistoryByCurrencyGet(currency, kind, count, offset, includeOld, includeUnfilled);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling TradingApi#privateGetOrderHistoryByCurrencyGet");
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
 **count** | **Integer**| Number of requested items, default - &#x60;20&#x60; | [optional]
 **offset** | **Integer**| The offset for pagination, default - &#x60;0&#x60; | [optional]
 **includeOld** | **Boolean**| Include in result orders older than 2 days, default - &#x60;false&#x60; | [optional]
 **includeUnfilled** | **Boolean**| Include in result fully unfilled closed orders, default - &#x60;false&#x60; | [optional]

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

<a name="privateGetOrderHistoryByInstrumentGet"></a>
# **privateGetOrderHistoryByInstrumentGet**
> Object privateGetOrderHistoryByInstrumentGet(instrumentName, count, offset, includeOld, includeUnfilled)

Retrieves history of orders that have been partially or fully filled.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.TradingApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://www.deribit.com/api/v2");
    
    // Configure HTTP basic authorization: bearerAuth
    HttpBasicAuth bearerAuth = (HttpBasicAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setUsername("YOUR USERNAME");
    bearerAuth.setPassword("YOUR PASSWORD");

    TradingApi apiInstance = new TradingApi(defaultClient);
    String instrumentName = BTC-PERPETUAL; // String | Instrument name
    Integer count = 56; // Integer | Number of requested items, default - `20`
    Integer offset = 10; // Integer | The offset for pagination, default - `0`
    Boolean includeOld = false; // Boolean | Include in result orders older than 2 days, default - `false`
    Boolean includeUnfilled = false; // Boolean | Include in result fully unfilled closed orders, default - `false`
    try {
      Object result = apiInstance.privateGetOrderHistoryByInstrumentGet(instrumentName, count, offset, includeOld, includeUnfilled);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling TradingApi#privateGetOrderHistoryByInstrumentGet");
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
 **count** | **Integer**| Number of requested items, default - &#x60;20&#x60; | [optional]
 **offset** | **Integer**| The offset for pagination, default - &#x60;0&#x60; | [optional]
 **includeOld** | **Boolean**| Include in result orders older than 2 days, default - &#x60;false&#x60; | [optional]
 **includeUnfilled** | **Boolean**| Include in result fully unfilled closed orders, default - &#x60;false&#x60; | [optional]

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

<a name="privateGetOrderMarginByIdsGet"></a>
# **privateGetOrderMarginByIdsGet**
> Object privateGetOrderMarginByIdsGet(ids)

Retrieves initial margins of given orders

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.TradingApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://www.deribit.com/api/v2");
    
    // Configure HTTP basic authorization: bearerAuth
    HttpBasicAuth bearerAuth = (HttpBasicAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setUsername("YOUR USERNAME");
    bearerAuth.setPassword("YOUR PASSWORD");

    TradingApi apiInstance = new TradingApi(defaultClient);
    List<String> ids = Arrays.asList(); // List<String> | Ids of orders
    try {
      Object result = apiInstance.privateGetOrderMarginByIdsGet(ids);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling TradingApi#privateGetOrderMarginByIdsGet");
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
 **ids** | [**List&lt;String&gt;**](String.md)| Ids of orders |

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

<a name="privateGetOrderStateGet"></a>
# **privateGetOrderStateGet**
> Object privateGetOrderStateGet(orderId)

Retrieve the current state of an order.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.TradingApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://www.deribit.com/api/v2");
    
    // Configure HTTP basic authorization: bearerAuth
    HttpBasicAuth bearerAuth = (HttpBasicAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setUsername("YOUR USERNAME");
    bearerAuth.setPassword("YOUR PASSWORD");

    TradingApi apiInstance = new TradingApi(defaultClient);
    String orderId = ETH-100234; // String | The order id
    try {
      Object result = apiInstance.privateGetOrderStateGet(orderId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling TradingApi#privateGetOrderStateGet");
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
 **orderId** | **String**| The order id |

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
**400** | result when used via rest/HTTP |  -  |

<a name="privateGetSettlementHistoryByCurrencyGet"></a>
# **privateGetSettlementHistoryByCurrencyGet**
> Object privateGetSettlementHistoryByCurrencyGet(currency, type, count)

Retrieves settlement, delivery and bankruptcy events that have affected your account.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.TradingApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://www.deribit.com/api/v2");
    
    // Configure HTTP basic authorization: bearerAuth
    HttpBasicAuth bearerAuth = (HttpBasicAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setUsername("YOUR USERNAME");
    bearerAuth.setPassword("YOUR PASSWORD");

    TradingApi apiInstance = new TradingApi(defaultClient);
    String currency = "currency_example"; // String | The currency symbol
    String type = "type_example"; // String | Settlement type
    Integer count = 56; // Integer | Number of requested items, default - `20`
    try {
      Object result = apiInstance.privateGetSettlementHistoryByCurrencyGet(currency, type, count);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling TradingApi#privateGetSettlementHistoryByCurrencyGet");
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

<a name="privateGetSettlementHistoryByInstrumentGet"></a>
# **privateGetSettlementHistoryByInstrumentGet**
> Object privateGetSettlementHistoryByInstrumentGet(instrumentName, type, count)

Retrieves public settlement, delivery and bankruptcy events filtered by instrument name

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.TradingApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://www.deribit.com/api/v2");
    
    // Configure HTTP basic authorization: bearerAuth
    HttpBasicAuth bearerAuth = (HttpBasicAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setUsername("YOUR USERNAME");
    bearerAuth.setPassword("YOUR PASSWORD");

    TradingApi apiInstance = new TradingApi(defaultClient);
    String instrumentName = BTC-PERPETUAL; // String | Instrument name
    String type = "type_example"; // String | Settlement type
    Integer count = 56; // Integer | Number of requested items, default - `20`
    try {
      Object result = apiInstance.privateGetSettlementHistoryByInstrumentGet(instrumentName, type, count);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling TradingApi#privateGetSettlementHistoryByInstrumentGet");
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

<a name="privateGetUserTradesByCurrencyAndTimeGet"></a>
# **privateGetUserTradesByCurrencyAndTimeGet**
> Object privateGetUserTradesByCurrencyAndTimeGet(currency, startTimestamp, endTimestamp, kind, count, includeOld, sorting)

Retrieve the latest user trades that have occurred for instruments in a specific currency symbol and within given time range.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.TradingApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://www.deribit.com/api/v2");
    
    // Configure HTTP basic authorization: bearerAuth
    HttpBasicAuth bearerAuth = (HttpBasicAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setUsername("YOUR USERNAME");
    bearerAuth.setPassword("YOUR PASSWORD");

    TradingApi apiInstance = new TradingApi(defaultClient);
    String currency = "currency_example"; // String | The currency symbol
    Integer startTimestamp = 1536569522277; // Integer | The earliest timestamp to return result for
    Integer endTimestamp = 1536569522277; // Integer | The most recent timestamp to return result for
    String kind = "kind_example"; // String | Instrument kind, if not provided instruments of all kinds are considered
    Integer count = 56; // Integer | Number of requested items, default - `10`
    Boolean includeOld = true; // Boolean | Include trades older than 7 days, default - `false`
    String sorting = "sorting_example"; // String | Direction of results sorting (`default` value means no sorting, results will be returned in order in which they left the database)
    try {
      Object result = apiInstance.privateGetUserTradesByCurrencyAndTimeGet(currency, startTimestamp, endTimestamp, kind, count, includeOld, sorting);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling TradingApi#privateGetUserTradesByCurrencyAndTimeGet");
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

<a name="privateGetUserTradesByCurrencyGet"></a>
# **privateGetUserTradesByCurrencyGet**
> Object privateGetUserTradesByCurrencyGet(currency, kind, startId, endId, count, includeOld, sorting)

Retrieve the latest user trades that have occurred for instruments in a specific currency symbol.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.TradingApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://www.deribit.com/api/v2");
    
    // Configure HTTP basic authorization: bearerAuth
    HttpBasicAuth bearerAuth = (HttpBasicAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setUsername("YOUR USERNAME");
    bearerAuth.setPassword("YOUR PASSWORD");

    TradingApi apiInstance = new TradingApi(defaultClient);
    String currency = "currency_example"; // String | The currency symbol
    String kind = "kind_example"; // String | Instrument kind, if not provided instruments of all kinds are considered
    String startId = "startId_example"; // String | The ID number of the first trade to be returned
    String endId = "endId_example"; // String | The ID number of the last trade to be returned
    Integer count = 56; // Integer | Number of requested items, default - `10`
    Boolean includeOld = true; // Boolean | Include trades older than 7 days, default - `false`
    String sorting = "sorting_example"; // String | Direction of results sorting (`default` value means no sorting, results will be returned in order in which they left the database)
    try {
      Object result = apiInstance.privateGetUserTradesByCurrencyGet(currency, kind, startId, endId, count, includeOld, sorting);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling TradingApi#privateGetUserTradesByCurrencyGet");
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

<a name="privateGetUserTradesByInstrumentAndTimeGet"></a>
# **privateGetUserTradesByInstrumentAndTimeGet**
> Object privateGetUserTradesByInstrumentAndTimeGet(instrumentName, startTimestamp, endTimestamp, count, includeOld, sorting)

Retrieve the latest user trades that have occurred for a specific instrument and within given time range.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.TradingApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://www.deribit.com/api/v2");
    
    // Configure HTTP basic authorization: bearerAuth
    HttpBasicAuth bearerAuth = (HttpBasicAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setUsername("YOUR USERNAME");
    bearerAuth.setPassword("YOUR PASSWORD");

    TradingApi apiInstance = new TradingApi(defaultClient);
    String instrumentName = BTC-PERPETUAL; // String | Instrument name
    Integer startTimestamp = 1536569522277; // Integer | The earliest timestamp to return result for
    Integer endTimestamp = 1536569522277; // Integer | The most recent timestamp to return result for
    Integer count = 56; // Integer | Number of requested items, default - `10`
    Boolean includeOld = true; // Boolean | Include trades older than 7 days, default - `false`
    String sorting = "sorting_example"; // String | Direction of results sorting (`default` value means no sorting, results will be returned in order in which they left the database)
    try {
      Object result = apiInstance.privateGetUserTradesByInstrumentAndTimeGet(instrumentName, startTimestamp, endTimestamp, count, includeOld, sorting);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling TradingApi#privateGetUserTradesByInstrumentAndTimeGet");
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

<a name="privateGetUserTradesByInstrumentGet"></a>
# **privateGetUserTradesByInstrumentGet**
> Object privateGetUserTradesByInstrumentGet(instrumentName, startSeq, endSeq, count, includeOld, sorting)

Retrieve the latest user trades that have occurred for a specific instrument.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.TradingApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://www.deribit.com/api/v2");
    
    // Configure HTTP basic authorization: bearerAuth
    HttpBasicAuth bearerAuth = (HttpBasicAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setUsername("YOUR USERNAME");
    bearerAuth.setPassword("YOUR PASSWORD");

    TradingApi apiInstance = new TradingApi(defaultClient);
    String instrumentName = BTC-PERPETUAL; // String | Instrument name
    Integer startSeq = 56; // Integer | The sequence number of the first trade to be returned
    Integer endSeq = 56; // Integer | The sequence number of the last trade to be returned
    Integer count = 56; // Integer | Number of requested items, default - `10`
    Boolean includeOld = true; // Boolean | Include trades older than 7 days, default - `false`
    String sorting = "sorting_example"; // String | Direction of results sorting (`default` value means no sorting, results will be returned in order in which they left the database)
    try {
      Object result = apiInstance.privateGetUserTradesByInstrumentGet(instrumentName, startSeq, endSeq, count, includeOld, sorting);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling TradingApi#privateGetUserTradesByInstrumentGet");
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

<a name="privateGetUserTradesByOrderGet"></a>
# **privateGetUserTradesByOrderGet**
> Object privateGetUserTradesByOrderGet(orderId, sorting)

Retrieve the list of user trades that was created for given order

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.TradingApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://www.deribit.com/api/v2");
    
    // Configure HTTP basic authorization: bearerAuth
    HttpBasicAuth bearerAuth = (HttpBasicAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setUsername("YOUR USERNAME");
    bearerAuth.setPassword("YOUR PASSWORD");

    TradingApi apiInstance = new TradingApi(defaultClient);
    String orderId = ETH-100234; // String | The order id
    String sorting = "sorting_example"; // String | Direction of results sorting (`default` value means no sorting, results will be returned in order in which they left the database)
    try {
      Object result = apiInstance.privateGetUserTradesByOrderGet(orderId, sorting);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling TradingApi#privateGetUserTradesByOrderGet");
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
 **orderId** | **String**| The order id |
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

<a name="privateSellGet"></a>
# **privateSellGet**
> Object privateSellGet(instrumentName, amount, type, label, price, timeInForce, maxShow, postOnly, reduceOnly, stopPrice, trigger, advanced)

Places a sell order for an instrument.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.TradingApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://www.deribit.com/api/v2");
    
    // Configure HTTP basic authorization: bearerAuth
    HttpBasicAuth bearerAuth = (HttpBasicAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setUsername("YOUR USERNAME");
    bearerAuth.setPassword("YOUR PASSWORD");

    TradingApi apiInstance = new TradingApi(defaultClient);
    String instrumentName = BTC-PERPETUAL; // String | Instrument name
    BigDecimal amount = new BigDecimal(); // BigDecimal | It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH
    String type = "type_example"; // String | The order type, default: `\"limit\"`
    String label = "label_example"; // String | user defined label for the order (maximum 32 characters)
    BigDecimal price = new BigDecimal(); // BigDecimal | <p>The order price in base currency (Only for limit and stop_limit orders)</p> <p>When adding order with advanced=usd, the field price should be the option price value in USD.</p> <p>When adding order with advanced=implv, the field price should be a value of implied volatility in percentages. For example,  price=100, means implied volatility of 100%</p>
    String timeInForce = "good_til_cancelled"; // String | <p>Specifies how long the order remains in effect. Default `\"good_til_cancelled\"`</p> <ul> <li>`\"good_til_cancelled\"` - unfilled order remains in order book until cancelled</li> <li>`\"fill_or_kill\"` - execute a transaction immediately and completely or not at all</li> <li>`\"immediate_or_cancel\"` - execute a transaction immediately, and any portion of the order that cannot be immediately filled is cancelled</li> </ul>
    BigDecimal maxShow = new BigDecimal(); // BigDecimal | Maximum amount within an order to be shown to other customers, `0` for invisible order
    Boolean postOnly = true; // Boolean | <p>If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.</p> <p>Only valid in combination with time_in_force=`\"good_til_cancelled\"`</p>
    Boolean reduceOnly = false; // Boolean | If `true`, the order is considered reduce-only which is intended to only reduce a current position
    BigDecimal stopPrice = new BigDecimal(); // BigDecimal | Stop price, required for stop limit orders (Only for stop orders)
    String trigger = "trigger_example"; // String | Defines trigger type, required for `\"stop_limit\"` order type
    String advanced = "advanced_example"; // String | Advanced option order type. (Only for options)
    try {
      Object result = apiInstance.privateSellGet(instrumentName, amount, type, label, price, timeInForce, maxShow, postOnly, reduceOnly, stopPrice, trigger, advanced);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling TradingApi#privateSellGet");
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
 **amount** | **BigDecimal**| It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH |
 **type** | **String**| The order type, default: &#x60;\&quot;limit\&quot;&#x60; | [optional] [enum: limit, stop_limit, market, stop_market]
 **label** | **String**| user defined label for the order (maximum 32 characters) | [optional]
 **price** | **BigDecimal**| &lt;p&gt;The order price in base currency (Only for limit and stop_limit orders)&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;usd, the field price should be the option price value in USD.&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;implv, the field price should be a value of implied volatility in percentages. For example,  price&#x3D;100, means implied volatility of 100%&lt;/p&gt; | [optional]
 **timeInForce** | **String**| &lt;p&gt;Specifies how long the order remains in effect. Default &#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; &lt;ul&gt; &lt;li&gt;&#x60;\&quot;good_til_cancelled\&quot;&#x60; - unfilled order remains in order book until cancelled&lt;/li&gt; &lt;li&gt;&#x60;\&quot;fill_or_kill\&quot;&#x60; - execute a transaction immediately and completely or not at all&lt;/li&gt; &lt;li&gt;&#x60;\&quot;immediate_or_cancel\&quot;&#x60; - execute a transaction immediately, and any portion of the order that cannot be immediately filled is cancelled&lt;/li&gt; &lt;/ul&gt; | [optional] [default to good_til_cancelled] [enum: good_til_cancelled, fill_or_kill, immediate_or_cancel]
 **maxShow** | **BigDecimal**| Maximum amount within an order to be shown to other customers, &#x60;0&#x60; for invisible order | [optional] [default to 1d]
 **postOnly** | **Boolean**| &lt;p&gt;If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.&lt;/p&gt; &lt;p&gt;Only valid in combination with time_in_force&#x3D;&#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; | [optional] [default to true]
 **reduceOnly** | **Boolean**| If &#x60;true&#x60;, the order is considered reduce-only which is intended to only reduce a current position | [optional] [default to false]
 **stopPrice** | **BigDecimal**| Stop price, required for stop limit orders (Only for stop orders) | [optional]
 **trigger** | **String**| Defines trigger type, required for &#x60;\&quot;stop_limit\&quot;&#x60; order type | [optional] [enum: index_price, mark_price, last_price]
 **advanced** | **String**| Advanced option order type. (Only for options) | [optional] [enum: usd, implv]

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

