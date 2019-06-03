# OpenapiClient::TradingApi

All URIs are relative to *https://www.deribit.com/api/v2*

Method | HTTP request | Description
------------- | ------------- | -------------
[**private_buy_get**](TradingApi.md#private_buy_get) | **GET** /private/buy | Places a buy order for an instrument.
[**private_cancel_all_by_currency_get**](TradingApi.md#private_cancel_all_by_currency_get) | **GET** /private/cancel_all_by_currency | Cancels all orders by currency, optionally filtered by instrument kind and/or order type.
[**private_cancel_all_by_instrument_get**](TradingApi.md#private_cancel_all_by_instrument_get) | **GET** /private/cancel_all_by_instrument | Cancels all orders by instrument, optionally filtered by order type.
[**private_cancel_all_get**](TradingApi.md#private_cancel_all_get) | **GET** /private/cancel_all | This method cancels all users orders and stop orders within all currencies and instrument kinds.
[**private_cancel_get**](TradingApi.md#private_cancel_get) | **GET** /private/cancel | Cancel an order, specified by order id
[**private_close_position_get**](TradingApi.md#private_close_position_get) | **GET** /private/close_position | Makes closing position reduce only order .
[**private_edit_get**](TradingApi.md#private_edit_get) | **GET** /private/edit | Change price, amount and/or other properties of an order.
[**private_get_margins_get**](TradingApi.md#private_get_margins_get) | **GET** /private/get_margins | Get margins for given instrument, amount and price.
[**private_get_open_orders_by_currency_get**](TradingApi.md#private_get_open_orders_by_currency_get) | **GET** /private/get_open_orders_by_currency | Retrieves list of user&#39;s open orders.
[**private_get_open_orders_by_instrument_get**](TradingApi.md#private_get_open_orders_by_instrument_get) | **GET** /private/get_open_orders_by_instrument | Retrieves list of user&#39;s open orders within given Instrument.
[**private_get_order_history_by_currency_get**](TradingApi.md#private_get_order_history_by_currency_get) | **GET** /private/get_order_history_by_currency | Retrieves history of orders that have been partially or fully filled.
[**private_get_order_history_by_instrument_get**](TradingApi.md#private_get_order_history_by_instrument_get) | **GET** /private/get_order_history_by_instrument | Retrieves history of orders that have been partially or fully filled.
[**private_get_order_margin_by_ids_get**](TradingApi.md#private_get_order_margin_by_ids_get) | **GET** /private/get_order_margin_by_ids | Retrieves initial margins of given orders
[**private_get_order_state_get**](TradingApi.md#private_get_order_state_get) | **GET** /private/get_order_state | Retrieve the current state of an order.
[**private_get_settlement_history_by_currency_get**](TradingApi.md#private_get_settlement_history_by_currency_get) | **GET** /private/get_settlement_history_by_currency | Retrieves settlement, delivery and bankruptcy events that have affected your account.
[**private_get_settlement_history_by_instrument_get**](TradingApi.md#private_get_settlement_history_by_instrument_get) | **GET** /private/get_settlement_history_by_instrument | Retrieves public settlement, delivery and bankruptcy events filtered by instrument name
[**private_get_user_trades_by_currency_and_time_get**](TradingApi.md#private_get_user_trades_by_currency_and_time_get) | **GET** /private/get_user_trades_by_currency_and_time | Retrieve the latest user trades that have occurred for instruments in a specific currency symbol and within given time range.
[**private_get_user_trades_by_currency_get**](TradingApi.md#private_get_user_trades_by_currency_get) | **GET** /private/get_user_trades_by_currency | Retrieve the latest user trades that have occurred for instruments in a specific currency symbol.
[**private_get_user_trades_by_instrument_and_time_get**](TradingApi.md#private_get_user_trades_by_instrument_and_time_get) | **GET** /private/get_user_trades_by_instrument_and_time | Retrieve the latest user trades that have occurred for a specific instrument and within given time range.
[**private_get_user_trades_by_instrument_get**](TradingApi.md#private_get_user_trades_by_instrument_get) | **GET** /private/get_user_trades_by_instrument | Retrieve the latest user trades that have occurred for a specific instrument.
[**private_get_user_trades_by_order_get**](TradingApi.md#private_get_user_trades_by_order_get) | **GET** /private/get_user_trades_by_order | Retrieve the list of user trades that was created for given order
[**private_sell_get**](TradingApi.md#private_sell_get) | **GET** /private/sell | Places a sell order for an instrument.



## private_buy_get

> Object private_buy_get(instrument_name, amount, opts)

Places a buy order for an instrument.

### Example

```ruby
# load the gem
require 'openapi_client'
# setup authorization
OpenapiClient.configure do |config|
  # Configure Bearer authorization (Auth. Token): bearerAuth
  config.access_token = 'YOUR_BEARER_TOKEN'
end

api_instance = OpenapiClient::TradingApi.new
instrument_name = 'BTC-PERPETUAL' # String | Instrument name
amount = 3.4 # Float | It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH
opts = {
  type: 'type_example', # String | The order type, default: `\"limit\"`
  label: 'label_example', # String | user defined label for the order (maximum 32 characters)
  price: 3.4, # Float | <p>The order price in base currency (Only for limit and stop_limit orders)</p> <p>When adding order with advanced=usd, the field price should be the option price value in USD.</p> <p>When adding order with advanced=implv, the field price should be a value of implied volatility in percentages. For example,  price=100, means implied volatility of 100%</p>
  time_in_force: 'good_til_cancelled', # String | <p>Specifies how long the order remains in effect. Default `\"good_til_cancelled\"`</p> <ul> <li>`\"good_til_cancelled\"` - unfilled order remains in order book until cancelled</li> <li>`\"fill_or_kill\"` - execute a transaction immediately and completely or not at all</li> <li>`\"immediate_or_cancel\"` - execute a transaction immediately, and any portion of the order that cannot be immediately filled is cancelled</li> </ul>
  max_show: 1, # Float | Maximum amount within an order to be shown to other customers, `0` for invisible order
  post_only: true, # Boolean | <p>If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.</p> <p>Only valid in combination with time_in_force=`\"good_til_cancelled\"`</p>
  reduce_only: false, # Boolean | If `true`, the order is considered reduce-only which is intended to only reduce a current position
  stop_price: 3.4, # Float | Stop price, required for stop limit orders (Only for stop orders)
  trigger: 'trigger_example', # String | Defines trigger type, required for `\"stop_limit\"` order type
  advanced: 'advanced_example' # String | Advanced option order type. (Only for options)
}

begin
  #Places a buy order for an instrument.
  result = api_instance.private_buy_get(instrument_name, amount, opts)
  p result
rescue OpenapiClient::ApiError => e
  puts "Exception when calling TradingApi->private_buy_get: #{e}"
end
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrument_name** | **String**| Instrument name | 
 **amount** | **Float**| It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH | 
 **type** | **String**| The order type, default: &#x60;\&quot;limit\&quot;&#x60; | [optional] 
 **label** | **String**| user defined label for the order (maximum 32 characters) | [optional] 
 **price** | **Float**| &lt;p&gt;The order price in base currency (Only for limit and stop_limit orders)&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;usd, the field price should be the option price value in USD.&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;implv, the field price should be a value of implied volatility in percentages. For example,  price&#x3D;100, means implied volatility of 100%&lt;/p&gt; | [optional] 
 **time_in_force** | **String**| &lt;p&gt;Specifies how long the order remains in effect. Default &#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; &lt;ul&gt; &lt;li&gt;&#x60;\&quot;good_til_cancelled\&quot;&#x60; - unfilled order remains in order book until cancelled&lt;/li&gt; &lt;li&gt;&#x60;\&quot;fill_or_kill\&quot;&#x60; - execute a transaction immediately and completely or not at all&lt;/li&gt; &lt;li&gt;&#x60;\&quot;immediate_or_cancel\&quot;&#x60; - execute a transaction immediately, and any portion of the order that cannot be immediately filled is cancelled&lt;/li&gt; &lt;/ul&gt; | [optional] [default to &#39;good_til_cancelled&#39;]
 **max_show** | **Float**| Maximum amount within an order to be shown to other customers, &#x60;0&#x60; for invisible order | [optional] [default to 1]
 **post_only** | **Boolean**| &lt;p&gt;If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.&lt;/p&gt; &lt;p&gt;Only valid in combination with time_in_force&#x3D;&#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; | [optional] [default to true]
 **reduce_only** | **Boolean**| If &#x60;true&#x60;, the order is considered reduce-only which is intended to only reduce a current position | [optional] [default to false]
 **stop_price** | **Float**| Stop price, required for stop limit orders (Only for stop orders) | [optional] 
 **trigger** | **String**| Defines trigger type, required for &#x60;\&quot;stop_limit\&quot;&#x60; order type | [optional] 
 **advanced** | **String**| Advanced option order type. (Only for options) | [optional] 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## private_cancel_all_by_currency_get

> Object private_cancel_all_by_currency_get(currency, opts)

Cancels all orders by currency, optionally filtered by instrument kind and/or order type.

### Example

```ruby
# load the gem
require 'openapi_client'
# setup authorization
OpenapiClient.configure do |config|
  # Configure Bearer authorization (Auth. Token): bearerAuth
  config.access_token = 'YOUR_BEARER_TOKEN'
end

api_instance = OpenapiClient::TradingApi.new
currency = 'currency_example' # String | The currency symbol
opts = {
  kind: 'kind_example', # String | Instrument kind, if not provided instruments of all kinds are considered
  type: 'type_example' # String | Order type - limit, stop or all, default - `all`
}

begin
  #Cancels all orders by currency, optionally filtered by instrument kind and/or order type.
  result = api_instance.private_cancel_all_by_currency_get(currency, opts)
  p result
rescue OpenapiClient::ApiError => e
  puts "Exception when calling TradingApi->private_cancel_all_by_currency_get: #{e}"
end
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **String**| The currency symbol | 
 **kind** | **String**| Instrument kind, if not provided instruments of all kinds are considered | [optional] 
 **type** | **String**| Order type - limit, stop or all, default - &#x60;all&#x60; | [optional] 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## private_cancel_all_by_instrument_get

> Object private_cancel_all_by_instrument_get(instrument_name, opts)

Cancels all orders by instrument, optionally filtered by order type.

### Example

```ruby
# load the gem
require 'openapi_client'
# setup authorization
OpenapiClient.configure do |config|
  # Configure Bearer authorization (Auth. Token): bearerAuth
  config.access_token = 'YOUR_BEARER_TOKEN'
end

api_instance = OpenapiClient::TradingApi.new
instrument_name = 'BTC-PERPETUAL' # String | Instrument name
opts = {
  type: 'type_example' # String | Order type - limit, stop or all, default - `all`
}

begin
  #Cancels all orders by instrument, optionally filtered by order type.
  result = api_instance.private_cancel_all_by_instrument_get(instrument_name, opts)
  p result
rescue OpenapiClient::ApiError => e
  puts "Exception when calling TradingApi->private_cancel_all_by_instrument_get: #{e}"
end
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrument_name** | **String**| Instrument name | 
 **type** | **String**| Order type - limit, stop or all, default - &#x60;all&#x60; | [optional] 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## private_cancel_all_get

> Object private_cancel_all_get

This method cancels all users orders and stop orders within all currencies and instrument kinds.

### Example

```ruby
# load the gem
require 'openapi_client'
# setup authorization
OpenapiClient.configure do |config|
  # Configure Bearer authorization (Auth. Token): bearerAuth
  config.access_token = 'YOUR_BEARER_TOKEN'
end

api_instance = OpenapiClient::TradingApi.new

begin
  #This method cancels all users orders and stop orders within all currencies and instrument kinds.
  result = api_instance.private_cancel_all_get
  p result
rescue OpenapiClient::ApiError => e
  puts "Exception when calling TradingApi->private_cancel_all_get: #{e}"
end
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


## private_cancel_get

> Object private_cancel_get(order_id)

Cancel an order, specified by order id

### Example

```ruby
# load the gem
require 'openapi_client'
# setup authorization
OpenapiClient.configure do |config|
  # Configure Bearer authorization (Auth. Token): bearerAuth
  config.access_token = 'YOUR_BEARER_TOKEN'
end

api_instance = OpenapiClient::TradingApi.new
order_id = 'ETH-100234' # String | The order id

begin
  #Cancel an order, specified by order id
  result = api_instance.private_cancel_get(order_id)
  p result
rescue OpenapiClient::ApiError => e
  puts "Exception when calling TradingApi->private_cancel_get: #{e}"
end
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **order_id** | **String**| The order id | 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## private_close_position_get

> Object private_close_position_get(instrument_name, type, opts)

Makes closing position reduce only order .

### Example

```ruby
# load the gem
require 'openapi_client'
# setup authorization
OpenapiClient.configure do |config|
  # Configure Bearer authorization (Auth. Token): bearerAuth
  config.access_token = 'YOUR_BEARER_TOKEN'
end

api_instance = OpenapiClient::TradingApi.new
instrument_name = 'BTC-PERPETUAL' # String | Instrument name
type = 'type_example' # String | The order type
opts = {
  price: 3.4 # Float | Optional price for limit order.
}

begin
  #Makes closing position reduce only order .
  result = api_instance.private_close_position_get(instrument_name, type, opts)
  p result
rescue OpenapiClient::ApiError => e
  puts "Exception when calling TradingApi->private_close_position_get: #{e}"
end
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrument_name** | **String**| Instrument name | 
 **type** | **String**| The order type | 
 **price** | **Float**| Optional price for limit order. | [optional] 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## private_edit_get

> Object private_edit_get(order_id, amount, price, opts)

Change price, amount and/or other properties of an order.

### Example

```ruby
# load the gem
require 'openapi_client'
# setup authorization
OpenapiClient.configure do |config|
  # Configure Bearer authorization (Auth. Token): bearerAuth
  config.access_token = 'YOUR_BEARER_TOKEN'
end

api_instance = OpenapiClient::TradingApi.new
order_id = 'ETH-100234' # String | The order id
amount = 3.4 # Float | It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH
price = 3.4 # Float | <p>The order price in base currency.</p> <p>When editing an option order with advanced=usd, the field price should be the option price value in USD.</p> <p>When editing an option order with advanced=implv, the field price should be a value of implied volatility in percentages. For example,  price=100, means implied volatility of 100%</p>
opts = {
  post_only: true, # Boolean | <p>If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.</p> <p>Only valid in combination with time_in_force=`\"good_til_cancelled\"`</p>
  advanced: 'advanced_example', # String | Advanced option order type. If you have posted an advanced option order, it is necessary to re-supply this parameter when editing it (Only for options)
  stop_price: 3.4 # Float | Stop price, required for stop limit orders (Only for stop orders)
}

begin
  #Change price, amount and/or other properties of an order.
  result = api_instance.private_edit_get(order_id, amount, price, opts)
  p result
rescue OpenapiClient::ApiError => e
  puts "Exception when calling TradingApi->private_edit_get: #{e}"
end
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **order_id** | **String**| The order id | 
 **amount** | **Float**| It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH | 
 **price** | **Float**| &lt;p&gt;The order price in base currency.&lt;/p&gt; &lt;p&gt;When editing an option order with advanced&#x3D;usd, the field price should be the option price value in USD.&lt;/p&gt; &lt;p&gt;When editing an option order with advanced&#x3D;implv, the field price should be a value of implied volatility in percentages. For example,  price&#x3D;100, means implied volatility of 100%&lt;/p&gt; | 
 **post_only** | **Boolean**| &lt;p&gt;If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.&lt;/p&gt; &lt;p&gt;Only valid in combination with time_in_force&#x3D;&#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; | [optional] [default to true]
 **advanced** | **String**| Advanced option order type. If you have posted an advanced option order, it is necessary to re-supply this parameter when editing it (Only for options) | [optional] 
 **stop_price** | **Float**| Stop price, required for stop limit orders (Only for stop orders) | [optional] 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## private_get_margins_get

> Object private_get_margins_get(instrument_name, amount, price)

Get margins for given instrument, amount and price.

### Example

```ruby
# load the gem
require 'openapi_client'
# setup authorization
OpenapiClient.configure do |config|
  # Configure Bearer authorization (Auth. Token): bearerAuth
  config.access_token = 'YOUR_BEARER_TOKEN'
end

api_instance = OpenapiClient::TradingApi.new
instrument_name = 'BTC-PERPETUAL' # String | Instrument name
amount = 1 # Float | Amount, integer for future, float for option. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH.
price = 3.4 # Float | Price

begin
  #Get margins for given instrument, amount and price.
  result = api_instance.private_get_margins_get(instrument_name, amount, price)
  p result
rescue OpenapiClient::ApiError => e
  puts "Exception when calling TradingApi->private_get_margins_get: #{e}"
end
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrument_name** | **String**| Instrument name | 
 **amount** | **Float**| Amount, integer for future, float for option. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH. | 
 **price** | **Float**| Price | 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## private_get_open_orders_by_currency_get

> Object private_get_open_orders_by_currency_get(currency, opts)

Retrieves list of user's open orders.

### Example

```ruby
# load the gem
require 'openapi_client'
# setup authorization
OpenapiClient.configure do |config|
  # Configure Bearer authorization (Auth. Token): bearerAuth
  config.access_token = 'YOUR_BEARER_TOKEN'
end

api_instance = OpenapiClient::TradingApi.new
currency = 'currency_example' # String | The currency symbol
opts = {
  kind: 'kind_example', # String | Instrument kind, if not provided instruments of all kinds are considered
  type: 'type_example' # String | Order type, default - `all`
}

begin
  #Retrieves list of user's open orders.
  result = api_instance.private_get_open_orders_by_currency_get(currency, opts)
  p result
rescue OpenapiClient::ApiError => e
  puts "Exception when calling TradingApi->private_get_open_orders_by_currency_get: #{e}"
end
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **String**| The currency symbol | 
 **kind** | **String**| Instrument kind, if not provided instruments of all kinds are considered | [optional] 
 **type** | **String**| Order type, default - &#x60;all&#x60; | [optional] 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## private_get_open_orders_by_instrument_get

> Object private_get_open_orders_by_instrument_get(instrument_name, opts)

Retrieves list of user's open orders within given Instrument.

### Example

```ruby
# load the gem
require 'openapi_client'
# setup authorization
OpenapiClient.configure do |config|
  # Configure Bearer authorization (Auth. Token): bearerAuth
  config.access_token = 'YOUR_BEARER_TOKEN'
end

api_instance = OpenapiClient::TradingApi.new
instrument_name = 'BTC-PERPETUAL' # String | Instrument name
opts = {
  type: 'type_example' # String | Order type, default - `all`
}

begin
  #Retrieves list of user's open orders within given Instrument.
  result = api_instance.private_get_open_orders_by_instrument_get(instrument_name, opts)
  p result
rescue OpenapiClient::ApiError => e
  puts "Exception when calling TradingApi->private_get_open_orders_by_instrument_get: #{e}"
end
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrument_name** | **String**| Instrument name | 
 **type** | **String**| Order type, default - &#x60;all&#x60; | [optional] 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## private_get_order_history_by_currency_get

> Object private_get_order_history_by_currency_get(currency, opts)

Retrieves history of orders that have been partially or fully filled.

### Example

```ruby
# load the gem
require 'openapi_client'
# setup authorization
OpenapiClient.configure do |config|
  # Configure Bearer authorization (Auth. Token): bearerAuth
  config.access_token = 'YOUR_BEARER_TOKEN'
end

api_instance = OpenapiClient::TradingApi.new
currency = 'currency_example' # String | The currency symbol
opts = {
  kind: 'kind_example', # String | Instrument kind, if not provided instruments of all kinds are considered
  count: 56, # Integer | Number of requested items, default - `20`
  offset: 10, # Integer | The offset for pagination, default - `0`
  include_old: false, # Boolean | Include in result orders older than 2 days, default - `false`
  include_unfilled: false # Boolean | Include in result fully unfilled closed orders, default - `false`
}

begin
  #Retrieves history of orders that have been partially or fully filled.
  result = api_instance.private_get_order_history_by_currency_get(currency, opts)
  p result
rescue OpenapiClient::ApiError => e
  puts "Exception when calling TradingApi->private_get_order_history_by_currency_get: #{e}"
end
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **String**| The currency symbol | 
 **kind** | **String**| Instrument kind, if not provided instruments of all kinds are considered | [optional] 
 **count** | **Integer**| Number of requested items, default - &#x60;20&#x60; | [optional] 
 **offset** | **Integer**| The offset for pagination, default - &#x60;0&#x60; | [optional] 
 **include_old** | **Boolean**| Include in result orders older than 2 days, default - &#x60;false&#x60; | [optional] 
 **include_unfilled** | **Boolean**| Include in result fully unfilled closed orders, default - &#x60;false&#x60; | [optional] 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## private_get_order_history_by_instrument_get

> Object private_get_order_history_by_instrument_get(instrument_name, opts)

Retrieves history of orders that have been partially or fully filled.

### Example

```ruby
# load the gem
require 'openapi_client'
# setup authorization
OpenapiClient.configure do |config|
  # Configure Bearer authorization (Auth. Token): bearerAuth
  config.access_token = 'YOUR_BEARER_TOKEN'
end

api_instance = OpenapiClient::TradingApi.new
instrument_name = 'BTC-PERPETUAL' # String | Instrument name
opts = {
  count: 56, # Integer | Number of requested items, default - `20`
  offset: 10, # Integer | The offset for pagination, default - `0`
  include_old: false, # Boolean | Include in result orders older than 2 days, default - `false`
  include_unfilled: false # Boolean | Include in result fully unfilled closed orders, default - `false`
}

begin
  #Retrieves history of orders that have been partially or fully filled.
  result = api_instance.private_get_order_history_by_instrument_get(instrument_name, opts)
  p result
rescue OpenapiClient::ApiError => e
  puts "Exception when calling TradingApi->private_get_order_history_by_instrument_get: #{e}"
end
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrument_name** | **String**| Instrument name | 
 **count** | **Integer**| Number of requested items, default - &#x60;20&#x60; | [optional] 
 **offset** | **Integer**| The offset for pagination, default - &#x60;0&#x60; | [optional] 
 **include_old** | **Boolean**| Include in result orders older than 2 days, default - &#x60;false&#x60; | [optional] 
 **include_unfilled** | **Boolean**| Include in result fully unfilled closed orders, default - &#x60;false&#x60; | [optional] 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## private_get_order_margin_by_ids_get

> Object private_get_order_margin_by_ids_get(ids)

Retrieves initial margins of given orders

### Example

```ruby
# load the gem
require 'openapi_client'
# setup authorization
OpenapiClient.configure do |config|
  # Configure Bearer authorization (Auth. Token): bearerAuth
  config.access_token = 'YOUR_BEARER_TOKEN'
end

api_instance = OpenapiClient::TradingApi.new
ids = ['ids_example'] # Array<String> | Ids of orders

begin
  #Retrieves initial margins of given orders
  result = api_instance.private_get_order_margin_by_ids_get(ids)
  p result
rescue OpenapiClient::ApiError => e
  puts "Exception when calling TradingApi->private_get_order_margin_by_ids_get: #{e}"
end
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **ids** | [**Array&lt;String&gt;**](String.md)| Ids of orders | 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## private_get_order_state_get

> Object private_get_order_state_get(order_id)

Retrieve the current state of an order.

### Example

```ruby
# load the gem
require 'openapi_client'
# setup authorization
OpenapiClient.configure do |config|
  # Configure Bearer authorization (Auth. Token): bearerAuth
  config.access_token = 'YOUR_BEARER_TOKEN'
end

api_instance = OpenapiClient::TradingApi.new
order_id = 'ETH-100234' # String | The order id

begin
  #Retrieve the current state of an order.
  result = api_instance.private_get_order_state_get(order_id)
  p result
rescue OpenapiClient::ApiError => e
  puts "Exception when calling TradingApi->private_get_order_state_get: #{e}"
end
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **order_id** | **String**| The order id | 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## private_get_settlement_history_by_currency_get

> Object private_get_settlement_history_by_currency_get(currency, opts)

Retrieves settlement, delivery and bankruptcy events that have affected your account.

### Example

```ruby
# load the gem
require 'openapi_client'
# setup authorization
OpenapiClient.configure do |config|
  # Configure Bearer authorization (Auth. Token): bearerAuth
  config.access_token = 'YOUR_BEARER_TOKEN'
end

api_instance = OpenapiClient::TradingApi.new
currency = 'currency_example' # String | The currency symbol
opts = {
  type: 'type_example', # String | Settlement type
  count: 56 # Integer | Number of requested items, default - `20`
}

begin
  #Retrieves settlement, delivery and bankruptcy events that have affected your account.
  result = api_instance.private_get_settlement_history_by_currency_get(currency, opts)
  p result
rescue OpenapiClient::ApiError => e
  puts "Exception when calling TradingApi->private_get_settlement_history_by_currency_get: #{e}"
end
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **String**| The currency symbol | 
 **type** | **String**| Settlement type | [optional] 
 **count** | **Integer**| Number of requested items, default - &#x60;20&#x60; | [optional] 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## private_get_settlement_history_by_instrument_get

> Object private_get_settlement_history_by_instrument_get(instrument_name, opts)

Retrieves public settlement, delivery and bankruptcy events filtered by instrument name

### Example

```ruby
# load the gem
require 'openapi_client'
# setup authorization
OpenapiClient.configure do |config|
  # Configure Bearer authorization (Auth. Token): bearerAuth
  config.access_token = 'YOUR_BEARER_TOKEN'
end

api_instance = OpenapiClient::TradingApi.new
instrument_name = 'BTC-PERPETUAL' # String | Instrument name
opts = {
  type: 'type_example', # String | Settlement type
  count: 56 # Integer | Number of requested items, default - `20`
}

begin
  #Retrieves public settlement, delivery and bankruptcy events filtered by instrument name
  result = api_instance.private_get_settlement_history_by_instrument_get(instrument_name, opts)
  p result
rescue OpenapiClient::ApiError => e
  puts "Exception when calling TradingApi->private_get_settlement_history_by_instrument_get: #{e}"
end
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrument_name** | **String**| Instrument name | 
 **type** | **String**| Settlement type | [optional] 
 **count** | **Integer**| Number of requested items, default - &#x60;20&#x60; | [optional] 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## private_get_user_trades_by_currency_and_time_get

> Object private_get_user_trades_by_currency_and_time_get(currency, start_timestamp, end_timestamp, opts)

Retrieve the latest user trades that have occurred for instruments in a specific currency symbol and within given time range.

### Example

```ruby
# load the gem
require 'openapi_client'
# setup authorization
OpenapiClient.configure do |config|
  # Configure Bearer authorization (Auth. Token): bearerAuth
  config.access_token = 'YOUR_BEARER_TOKEN'
end

api_instance = OpenapiClient::TradingApi.new
currency = 'currency_example' # String | The currency symbol
start_timestamp = 1536569522277 # Integer | The earliest timestamp to return result for
end_timestamp = 1536569522277 # Integer | The most recent timestamp to return result for
opts = {
  kind: 'kind_example', # String | Instrument kind, if not provided instruments of all kinds are considered
  count: 56, # Integer | Number of requested items, default - `10`
  include_old: true, # Boolean | Include trades older than 7 days, default - `false`
  sorting: 'sorting_example' # String | Direction of results sorting (`default` value means no sorting, results will be returned in order in which they left the database)
}

begin
  #Retrieve the latest user trades that have occurred for instruments in a specific currency symbol and within given time range.
  result = api_instance.private_get_user_trades_by_currency_and_time_get(currency, start_timestamp, end_timestamp, opts)
  p result
rescue OpenapiClient::ApiError => e
  puts "Exception when calling TradingApi->private_get_user_trades_by_currency_and_time_get: #{e}"
end
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **String**| The currency symbol | 
 **start_timestamp** | **Integer**| The earliest timestamp to return result for | 
 **end_timestamp** | **Integer**| The most recent timestamp to return result for | 
 **kind** | **String**| Instrument kind, if not provided instruments of all kinds are considered | [optional] 
 **count** | **Integer**| Number of requested items, default - &#x60;10&#x60; | [optional] 
 **include_old** | **Boolean**| Include trades older than 7 days, default - &#x60;false&#x60; | [optional] 
 **sorting** | **String**| Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) | [optional] 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## private_get_user_trades_by_currency_get

> Object private_get_user_trades_by_currency_get(currency, opts)

Retrieve the latest user trades that have occurred for instruments in a specific currency symbol.

### Example

```ruby
# load the gem
require 'openapi_client'
# setup authorization
OpenapiClient.configure do |config|
  # Configure Bearer authorization (Auth. Token): bearerAuth
  config.access_token = 'YOUR_BEARER_TOKEN'
end

api_instance = OpenapiClient::TradingApi.new
currency = 'currency_example' # String | The currency symbol
opts = {
  kind: 'kind_example', # String | Instrument kind, if not provided instruments of all kinds are considered
  start_id: 'start_id_example', # String | The ID number of the first trade to be returned
  end_id: 'end_id_example', # String | The ID number of the last trade to be returned
  count: 56, # Integer | Number of requested items, default - `10`
  include_old: true, # Boolean | Include trades older than 7 days, default - `false`
  sorting: 'sorting_example' # String | Direction of results sorting (`default` value means no sorting, results will be returned in order in which they left the database)
}

begin
  #Retrieve the latest user trades that have occurred for instruments in a specific currency symbol.
  result = api_instance.private_get_user_trades_by_currency_get(currency, opts)
  p result
rescue OpenapiClient::ApiError => e
  puts "Exception when calling TradingApi->private_get_user_trades_by_currency_get: #{e}"
end
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **String**| The currency symbol | 
 **kind** | **String**| Instrument kind, if not provided instruments of all kinds are considered | [optional] 
 **start_id** | **String**| The ID number of the first trade to be returned | [optional] 
 **end_id** | **String**| The ID number of the last trade to be returned | [optional] 
 **count** | **Integer**| Number of requested items, default - &#x60;10&#x60; | [optional] 
 **include_old** | **Boolean**| Include trades older than 7 days, default - &#x60;false&#x60; | [optional] 
 **sorting** | **String**| Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) | [optional] 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## private_get_user_trades_by_instrument_and_time_get

> Object private_get_user_trades_by_instrument_and_time_get(instrument_name, start_timestamp, end_timestamp, opts)

Retrieve the latest user trades that have occurred for a specific instrument and within given time range.

### Example

```ruby
# load the gem
require 'openapi_client'
# setup authorization
OpenapiClient.configure do |config|
  # Configure Bearer authorization (Auth. Token): bearerAuth
  config.access_token = 'YOUR_BEARER_TOKEN'
end

api_instance = OpenapiClient::TradingApi.new
instrument_name = 'BTC-PERPETUAL' # String | Instrument name
start_timestamp = 1536569522277 # Integer | The earliest timestamp to return result for
end_timestamp = 1536569522277 # Integer | The most recent timestamp to return result for
opts = {
  count: 56, # Integer | Number of requested items, default - `10`
  include_old: true, # Boolean | Include trades older than 7 days, default - `false`
  sorting: 'sorting_example' # String | Direction of results sorting (`default` value means no sorting, results will be returned in order in which they left the database)
}

begin
  #Retrieve the latest user trades that have occurred for a specific instrument and within given time range.
  result = api_instance.private_get_user_trades_by_instrument_and_time_get(instrument_name, start_timestamp, end_timestamp, opts)
  p result
rescue OpenapiClient::ApiError => e
  puts "Exception when calling TradingApi->private_get_user_trades_by_instrument_and_time_get: #{e}"
end
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrument_name** | **String**| Instrument name | 
 **start_timestamp** | **Integer**| The earliest timestamp to return result for | 
 **end_timestamp** | **Integer**| The most recent timestamp to return result for | 
 **count** | **Integer**| Number of requested items, default - &#x60;10&#x60; | [optional] 
 **include_old** | **Boolean**| Include trades older than 7 days, default - &#x60;false&#x60; | [optional] 
 **sorting** | **String**| Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) | [optional] 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## private_get_user_trades_by_instrument_get

> Object private_get_user_trades_by_instrument_get(instrument_name, opts)

Retrieve the latest user trades that have occurred for a specific instrument.

### Example

```ruby
# load the gem
require 'openapi_client'
# setup authorization
OpenapiClient.configure do |config|
  # Configure Bearer authorization (Auth. Token): bearerAuth
  config.access_token = 'YOUR_BEARER_TOKEN'
end

api_instance = OpenapiClient::TradingApi.new
instrument_name = 'BTC-PERPETUAL' # String | Instrument name
opts = {
  start_seq: 56, # Integer | The sequence number of the first trade to be returned
  end_seq: 56, # Integer | The sequence number of the last trade to be returned
  count: 56, # Integer | Number of requested items, default - `10`
  include_old: true, # Boolean | Include trades older than 7 days, default - `false`
  sorting: 'sorting_example' # String | Direction of results sorting (`default` value means no sorting, results will be returned in order in which they left the database)
}

begin
  #Retrieve the latest user trades that have occurred for a specific instrument.
  result = api_instance.private_get_user_trades_by_instrument_get(instrument_name, opts)
  p result
rescue OpenapiClient::ApiError => e
  puts "Exception when calling TradingApi->private_get_user_trades_by_instrument_get: #{e}"
end
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrument_name** | **String**| Instrument name | 
 **start_seq** | **Integer**| The sequence number of the first trade to be returned | [optional] 
 **end_seq** | **Integer**| The sequence number of the last trade to be returned | [optional] 
 **count** | **Integer**| Number of requested items, default - &#x60;10&#x60; | [optional] 
 **include_old** | **Boolean**| Include trades older than 7 days, default - &#x60;false&#x60; | [optional] 
 **sorting** | **String**| Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) | [optional] 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## private_get_user_trades_by_order_get

> Object private_get_user_trades_by_order_get(order_id, opts)

Retrieve the list of user trades that was created for given order

### Example

```ruby
# load the gem
require 'openapi_client'
# setup authorization
OpenapiClient.configure do |config|
  # Configure Bearer authorization (Auth. Token): bearerAuth
  config.access_token = 'YOUR_BEARER_TOKEN'
end

api_instance = OpenapiClient::TradingApi.new
order_id = 'ETH-100234' # String | The order id
opts = {
  sorting: 'sorting_example' # String | Direction of results sorting (`default` value means no sorting, results will be returned in order in which they left the database)
}

begin
  #Retrieve the list of user trades that was created for given order
  result = api_instance.private_get_user_trades_by_order_get(order_id, opts)
  p result
rescue OpenapiClient::ApiError => e
  puts "Exception when calling TradingApi->private_get_user_trades_by_order_get: #{e}"
end
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **order_id** | **String**| The order id | 
 **sorting** | **String**| Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) | [optional] 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## private_sell_get

> Object private_sell_get(instrument_name, amount, opts)

Places a sell order for an instrument.

### Example

```ruby
# load the gem
require 'openapi_client'
# setup authorization
OpenapiClient.configure do |config|
  # Configure Bearer authorization (Auth. Token): bearerAuth
  config.access_token = 'YOUR_BEARER_TOKEN'
end

api_instance = OpenapiClient::TradingApi.new
instrument_name = 'BTC-PERPETUAL' # String | Instrument name
amount = 3.4 # Float | It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH
opts = {
  type: 'type_example', # String | The order type, default: `\"limit\"`
  label: 'label_example', # String | user defined label for the order (maximum 32 characters)
  price: 3.4, # Float | <p>The order price in base currency (Only for limit and stop_limit orders)</p> <p>When adding order with advanced=usd, the field price should be the option price value in USD.</p> <p>When adding order with advanced=implv, the field price should be a value of implied volatility in percentages. For example,  price=100, means implied volatility of 100%</p>
  time_in_force: 'good_til_cancelled', # String | <p>Specifies how long the order remains in effect. Default `\"good_til_cancelled\"`</p> <ul> <li>`\"good_til_cancelled\"` - unfilled order remains in order book until cancelled</li> <li>`\"fill_or_kill\"` - execute a transaction immediately and completely or not at all</li> <li>`\"immediate_or_cancel\"` - execute a transaction immediately, and any portion of the order that cannot be immediately filled is cancelled</li> </ul>
  max_show: 1, # Float | Maximum amount within an order to be shown to other customers, `0` for invisible order
  post_only: true, # Boolean | <p>If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.</p> <p>Only valid in combination with time_in_force=`\"good_til_cancelled\"`</p>
  reduce_only: false, # Boolean | If `true`, the order is considered reduce-only which is intended to only reduce a current position
  stop_price: 3.4, # Float | Stop price, required for stop limit orders (Only for stop orders)
  trigger: 'trigger_example', # String | Defines trigger type, required for `\"stop_limit\"` order type
  advanced: 'advanced_example' # String | Advanced option order type. (Only for options)
}

begin
  #Places a sell order for an instrument.
  result = api_instance.private_sell_get(instrument_name, amount, opts)
  p result
rescue OpenapiClient::ApiError => e
  puts "Exception when calling TradingApi->private_sell_get: #{e}"
end
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrument_name** | **String**| Instrument name | 
 **amount** | **Float**| It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH | 
 **type** | **String**| The order type, default: &#x60;\&quot;limit\&quot;&#x60; | [optional] 
 **label** | **String**| user defined label for the order (maximum 32 characters) | [optional] 
 **price** | **Float**| &lt;p&gt;The order price in base currency (Only for limit and stop_limit orders)&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;usd, the field price should be the option price value in USD.&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;implv, the field price should be a value of implied volatility in percentages. For example,  price&#x3D;100, means implied volatility of 100%&lt;/p&gt; | [optional] 
 **time_in_force** | **String**| &lt;p&gt;Specifies how long the order remains in effect. Default &#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; &lt;ul&gt; &lt;li&gt;&#x60;\&quot;good_til_cancelled\&quot;&#x60; - unfilled order remains in order book until cancelled&lt;/li&gt; &lt;li&gt;&#x60;\&quot;fill_or_kill\&quot;&#x60; - execute a transaction immediately and completely or not at all&lt;/li&gt; &lt;li&gt;&#x60;\&quot;immediate_or_cancel\&quot;&#x60; - execute a transaction immediately, and any portion of the order that cannot be immediately filled is cancelled&lt;/li&gt; &lt;/ul&gt; | [optional] [default to &#39;good_til_cancelled&#39;]
 **max_show** | **Float**| Maximum amount within an order to be shown to other customers, &#x60;0&#x60; for invisible order | [optional] [default to 1]
 **post_only** | **Boolean**| &lt;p&gt;If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.&lt;/p&gt; &lt;p&gt;Only valid in combination with time_in_force&#x3D;&#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; | [optional] [default to true]
 **reduce_only** | **Boolean**| If &#x60;true&#x60;, the order is considered reduce-only which is intended to only reduce a current position | [optional] [default to false]
 **stop_price** | **Float**| Stop price, required for stop limit orders (Only for stop orders) | [optional] 
 **trigger** | **String**| Defines trigger type, required for &#x60;\&quot;stop_limit\&quot;&#x60; order type | [optional] 
 **advanced** | **String**| Advanced option order type. (Only for options) | [optional] 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

