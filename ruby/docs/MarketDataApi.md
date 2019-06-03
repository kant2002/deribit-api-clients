# OpenapiClient::MarketDataApi

All URIs are relative to *https://www.deribit.com/api/v2*

Method | HTTP request | Description
------------- | ------------- | -------------
[**public_get_book_summary_by_currency_get**](MarketDataApi.md#public_get_book_summary_by_currency_get) | **GET** /public/get_book_summary_by_currency | Retrieves the summary information such as open interest, 24h volume, etc. for all instruments for the currency (optionally filtered by kind).
[**public_get_book_summary_by_instrument_get**](MarketDataApi.md#public_get_book_summary_by_instrument_get) | **GET** /public/get_book_summary_by_instrument | Retrieves the summary information such as open interest, 24h volume, etc. for a specific instrument.
[**public_get_contract_size_get**](MarketDataApi.md#public_get_contract_size_get) | **GET** /public/get_contract_size | Retrieves contract size of provided instrument.
[**public_get_currencies_get**](MarketDataApi.md#public_get_currencies_get) | **GET** /public/get_currencies | Retrieves all cryptocurrencies supported by the API.
[**public_get_funding_chart_data_get**](MarketDataApi.md#public_get_funding_chart_data_get) | **GET** /public/get_funding_chart_data | Retrieve the latest user trades that have occurred for PERPETUAL instruments in a specific currency symbol and within given time range.
[**public_get_historical_volatility_get**](MarketDataApi.md#public_get_historical_volatility_get) | **GET** /public/get_historical_volatility | Provides information about historical volatility for given cryptocurrency.
[**public_get_index_get**](MarketDataApi.md#public_get_index_get) | **GET** /public/get_index | Retrieves the current index price for the instruments, for the selected currency.
[**public_get_instruments_get**](MarketDataApi.md#public_get_instruments_get) | **GET** /public/get_instruments | Retrieves available trading instruments. This method can be used to see which instruments are available for trading, or which instruments have existed historically.
[**public_get_last_settlements_by_currency_get**](MarketDataApi.md#public_get_last_settlements_by_currency_get) | **GET** /public/get_last_settlements_by_currency | Retrieves historical settlement, delivery and bankruptcy events coming from all instruments within given currency.
[**public_get_last_settlements_by_instrument_get**](MarketDataApi.md#public_get_last_settlements_by_instrument_get) | **GET** /public/get_last_settlements_by_instrument | Retrieves historical public settlement, delivery and bankruptcy events filtered by instrument name.
[**public_get_last_trades_by_currency_and_time_get**](MarketDataApi.md#public_get_last_trades_by_currency_and_time_get) | **GET** /public/get_last_trades_by_currency_and_time | Retrieve the latest trades that have occurred for instruments in a specific currency symbol and within given time range.
[**public_get_last_trades_by_currency_get**](MarketDataApi.md#public_get_last_trades_by_currency_get) | **GET** /public/get_last_trades_by_currency | Retrieve the latest trades that have occurred for instruments in a specific currency symbol.
[**public_get_last_trades_by_instrument_and_time_get**](MarketDataApi.md#public_get_last_trades_by_instrument_and_time_get) | **GET** /public/get_last_trades_by_instrument_and_time | Retrieve the latest trades that have occurred for a specific instrument and within given time range.
[**public_get_last_trades_by_instrument_get**](MarketDataApi.md#public_get_last_trades_by_instrument_get) | **GET** /public/get_last_trades_by_instrument | Retrieve the latest trades that have occurred for a specific instrument.
[**public_get_order_book_get**](MarketDataApi.md#public_get_order_book_get) | **GET** /public/get_order_book | Retrieves the order book, along with other market values for a given instrument.
[**public_get_trade_volumes_get**](MarketDataApi.md#public_get_trade_volumes_get) | **GET** /public/get_trade_volumes | Retrieves aggregated 24h trade volumes for different instrument types and currencies.
[**public_get_tradingview_chart_data_get**](MarketDataApi.md#public_get_tradingview_chart_data_get) | **GET** /public/get_tradingview_chart_data | Publicly available market data used to generate a TradingView candle chart.
[**public_ticker_get**](MarketDataApi.md#public_ticker_get) | **GET** /public/ticker | Get ticker for an instrument.



## public_get_book_summary_by_currency_get

> Object public_get_book_summary_by_currency_get(currency, opts)

Retrieves the summary information such as open interest, 24h volume, etc. for all instruments for the currency (optionally filtered by kind).

### Example

```ruby
# load the gem
require 'openapi_client'
# setup authorization
OpenapiClient.configure do |config|
  # Configure Bearer authorization (Auth. Token): bearerAuth
  config.access_token = 'YOUR_BEARER_TOKEN'
end

api_instance = OpenapiClient::MarketDataApi.new
currency = 'currency_example' # String | The currency symbol
opts = {
  kind: 'kind_example' # String | Instrument kind, if not provided instruments of all kinds are considered
}

begin
  #Retrieves the summary information such as open interest, 24h volume, etc. for all instruments for the currency (optionally filtered by kind).
  result = api_instance.public_get_book_summary_by_currency_get(currency, opts)
  p result
rescue OpenapiClient::ApiError => e
  puts "Exception when calling MarketDataApi->public_get_book_summary_by_currency_get: #{e}"
end
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


## public_get_book_summary_by_instrument_get

> Object public_get_book_summary_by_instrument_get(instrument_name)

Retrieves the summary information such as open interest, 24h volume, etc. for a specific instrument.

### Example

```ruby
# load the gem
require 'openapi_client'
# setup authorization
OpenapiClient.configure do |config|
  # Configure Bearer authorization (Auth. Token): bearerAuth
  config.access_token = 'YOUR_BEARER_TOKEN'
end

api_instance = OpenapiClient::MarketDataApi.new
instrument_name = 'BTC-PERPETUAL' # String | Instrument name

begin
  #Retrieves the summary information such as open interest, 24h volume, etc. for a specific instrument.
  result = api_instance.public_get_book_summary_by_instrument_get(instrument_name)
  p result
rescue OpenapiClient::ApiError => e
  puts "Exception when calling MarketDataApi->public_get_book_summary_by_instrument_get: #{e}"
end
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrument_name** | **String**| Instrument name | 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## public_get_contract_size_get

> Object public_get_contract_size_get(instrument_name)

Retrieves contract size of provided instrument.

### Example

```ruby
# load the gem
require 'openapi_client'
# setup authorization
OpenapiClient.configure do |config|
  # Configure Bearer authorization (Auth. Token): bearerAuth
  config.access_token = 'YOUR_BEARER_TOKEN'
end

api_instance = OpenapiClient::MarketDataApi.new
instrument_name = 'BTC-PERPETUAL' # String | Instrument name

begin
  #Retrieves contract size of provided instrument.
  result = api_instance.public_get_contract_size_get(instrument_name)
  p result
rescue OpenapiClient::ApiError => e
  puts "Exception when calling MarketDataApi->public_get_contract_size_get: #{e}"
end
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrument_name** | **String**| Instrument name | 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## public_get_currencies_get

> Object public_get_currencies_get

Retrieves all cryptocurrencies supported by the API.

### Example

```ruby
# load the gem
require 'openapi_client'
# setup authorization
OpenapiClient.configure do |config|
  # Configure Bearer authorization (Auth. Token): bearerAuth
  config.access_token = 'YOUR_BEARER_TOKEN'
end

api_instance = OpenapiClient::MarketDataApi.new

begin
  #Retrieves all cryptocurrencies supported by the API.
  result = api_instance.public_get_currencies_get
  p result
rescue OpenapiClient::ApiError => e
  puts "Exception when calling MarketDataApi->public_get_currencies_get: #{e}"
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


## public_get_funding_chart_data_get

> Object public_get_funding_chart_data_get(instrument_name, opts)

Retrieve the latest user trades that have occurred for PERPETUAL instruments in a specific currency symbol and within given time range.

### Example

```ruby
# load the gem
require 'openapi_client'
# setup authorization
OpenapiClient.configure do |config|
  # Configure Bearer authorization (Auth. Token): bearerAuth
  config.access_token = 'YOUR_BEARER_TOKEN'
end

api_instance = OpenapiClient::MarketDataApi.new
instrument_name = 'BTC-PERPETUAL' # String | Instrument name
opts = {
  length: 'length_example' # String | Specifies time period. `8h` - 8 hours, `24h` - 24 hours
}

begin
  #Retrieve the latest user trades that have occurred for PERPETUAL instruments in a specific currency symbol and within given time range.
  result = api_instance.public_get_funding_chart_data_get(instrument_name, opts)
  p result
rescue OpenapiClient::ApiError => e
  puts "Exception when calling MarketDataApi->public_get_funding_chart_data_get: #{e}"
end
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrument_name** | **String**| Instrument name | 
 **length** | **String**| Specifies time period. &#x60;8h&#x60; - 8 hours, &#x60;24h&#x60; - 24 hours | [optional] 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## public_get_historical_volatility_get

> Object public_get_historical_volatility_get(currency)

Provides information about historical volatility for given cryptocurrency.

### Example

```ruby
# load the gem
require 'openapi_client'
# setup authorization
OpenapiClient.configure do |config|
  # Configure Bearer authorization (Auth. Token): bearerAuth
  config.access_token = 'YOUR_BEARER_TOKEN'
end

api_instance = OpenapiClient::MarketDataApi.new
currency = 'currency_example' # String | The currency symbol

begin
  #Provides information about historical volatility for given cryptocurrency.
  result = api_instance.public_get_historical_volatility_get(currency)
  p result
rescue OpenapiClient::ApiError => e
  puts "Exception when calling MarketDataApi->public_get_historical_volatility_get: #{e}"
end
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


## public_get_index_get

> Object public_get_index_get(currency)

Retrieves the current index price for the instruments, for the selected currency.

### Example

```ruby
# load the gem
require 'openapi_client'
# setup authorization
OpenapiClient.configure do |config|
  # Configure Bearer authorization (Auth. Token): bearerAuth
  config.access_token = 'YOUR_BEARER_TOKEN'
end

api_instance = OpenapiClient::MarketDataApi.new
currency = 'currency_example' # String | The currency symbol

begin
  #Retrieves the current index price for the instruments, for the selected currency.
  result = api_instance.public_get_index_get(currency)
  p result
rescue OpenapiClient::ApiError => e
  puts "Exception when calling MarketDataApi->public_get_index_get: #{e}"
end
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


## public_get_instruments_get

> Object public_get_instruments_get(currency, opts)

Retrieves available trading instruments. This method can be used to see which instruments are available for trading, or which instruments have existed historically.

### Example

```ruby
# load the gem
require 'openapi_client'
# setup authorization
OpenapiClient.configure do |config|
  # Configure Bearer authorization (Auth. Token): bearerAuth
  config.access_token = 'YOUR_BEARER_TOKEN'
end

api_instance = OpenapiClient::MarketDataApi.new
currency = 'currency_example' # String | The currency symbol
opts = {
  kind: 'kind_example', # String | Instrument kind, if not provided instruments of all kinds are considered
  expired: false # Boolean | Set to true to show expired instruments instead of active ones.
}

begin
  #Retrieves available trading instruments. This method can be used to see which instruments are available for trading, or which instruments have existed historically.
  result = api_instance.public_get_instruments_get(currency, opts)
  p result
rescue OpenapiClient::ApiError => e
  puts "Exception when calling MarketDataApi->public_get_instruments_get: #{e}"
end
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


## public_get_last_settlements_by_currency_get

> Object public_get_last_settlements_by_currency_get(currency, opts)

Retrieves historical settlement, delivery and bankruptcy events coming from all instruments within given currency.

### Example

```ruby
# load the gem
require 'openapi_client'
# setup authorization
OpenapiClient.configure do |config|
  # Configure Bearer authorization (Auth. Token): bearerAuth
  config.access_token = 'YOUR_BEARER_TOKEN'
end

api_instance = OpenapiClient::MarketDataApi.new
currency = 'currency_example' # String | The currency symbol
opts = {
  type: 'type_example', # String | Settlement type
  count: 56, # Integer | Number of requested items, default - `20`
  continuation: 'xY7T6cutS3t2B9YtaDkE6TS379oKnkzTvmEDUnEUP2Msa9xKWNNaT', # String | Continuation token for pagination
  search_start_timestamp: 1536569522277 # Integer | The latest timestamp to return result for
}

begin
  #Retrieves historical settlement, delivery and bankruptcy events coming from all instruments within given currency.
  result = api_instance.public_get_last_settlements_by_currency_get(currency, opts)
  p result
rescue OpenapiClient::ApiError => e
  puts "Exception when calling MarketDataApi->public_get_last_settlements_by_currency_get: #{e}"
end
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **String**| The currency symbol | 
 **type** | **String**| Settlement type | [optional] 
 **count** | **Integer**| Number of requested items, default - &#x60;20&#x60; | [optional] 
 **continuation** | **String**| Continuation token for pagination | [optional] 
 **search_start_timestamp** | **Integer**| The latest timestamp to return result for | [optional] 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## public_get_last_settlements_by_instrument_get

> Object public_get_last_settlements_by_instrument_get(instrument_name, opts)

Retrieves historical public settlement, delivery and bankruptcy events filtered by instrument name.

### Example

```ruby
# load the gem
require 'openapi_client'
# setup authorization
OpenapiClient.configure do |config|
  # Configure Bearer authorization (Auth. Token): bearerAuth
  config.access_token = 'YOUR_BEARER_TOKEN'
end

api_instance = OpenapiClient::MarketDataApi.new
instrument_name = 'BTC-PERPETUAL' # String | Instrument name
opts = {
  type: 'type_example', # String | Settlement type
  count: 56, # Integer | Number of requested items, default - `20`
  continuation: 'xY7T6cutS3t2B9YtaDkE6TS379oKnkzTvmEDUnEUP2Msa9xKWNNaT', # String | Continuation token for pagination
  search_start_timestamp: 1536569522277 # Integer | The latest timestamp to return result for
}

begin
  #Retrieves historical public settlement, delivery and bankruptcy events filtered by instrument name.
  result = api_instance.public_get_last_settlements_by_instrument_get(instrument_name, opts)
  p result
rescue OpenapiClient::ApiError => e
  puts "Exception when calling MarketDataApi->public_get_last_settlements_by_instrument_get: #{e}"
end
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrument_name** | **String**| Instrument name | 
 **type** | **String**| Settlement type | [optional] 
 **count** | **Integer**| Number of requested items, default - &#x60;20&#x60; | [optional] 
 **continuation** | **String**| Continuation token for pagination | [optional] 
 **search_start_timestamp** | **Integer**| The latest timestamp to return result for | [optional] 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## public_get_last_trades_by_currency_and_time_get

> Object public_get_last_trades_by_currency_and_time_get(currency, start_timestamp, end_timestamp, opts)

Retrieve the latest trades that have occurred for instruments in a specific currency symbol and within given time range.

### Example

```ruby
# load the gem
require 'openapi_client'
# setup authorization
OpenapiClient.configure do |config|
  # Configure Bearer authorization (Auth. Token): bearerAuth
  config.access_token = 'YOUR_BEARER_TOKEN'
end

api_instance = OpenapiClient::MarketDataApi.new
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
  #Retrieve the latest trades that have occurred for instruments in a specific currency symbol and within given time range.
  result = api_instance.public_get_last_trades_by_currency_and_time_get(currency, start_timestamp, end_timestamp, opts)
  p result
rescue OpenapiClient::ApiError => e
  puts "Exception when calling MarketDataApi->public_get_last_trades_by_currency_and_time_get: #{e}"
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


## public_get_last_trades_by_currency_get

> Object public_get_last_trades_by_currency_get(currency, opts)

Retrieve the latest trades that have occurred for instruments in a specific currency symbol.

### Example

```ruby
# load the gem
require 'openapi_client'
# setup authorization
OpenapiClient.configure do |config|
  # Configure Bearer authorization (Auth. Token): bearerAuth
  config.access_token = 'YOUR_BEARER_TOKEN'
end

api_instance = OpenapiClient::MarketDataApi.new
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
  #Retrieve the latest trades that have occurred for instruments in a specific currency symbol.
  result = api_instance.public_get_last_trades_by_currency_get(currency, opts)
  p result
rescue OpenapiClient::ApiError => e
  puts "Exception when calling MarketDataApi->public_get_last_trades_by_currency_get: #{e}"
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


## public_get_last_trades_by_instrument_and_time_get

> Object public_get_last_trades_by_instrument_and_time_get(instrument_name, start_timestamp, end_timestamp, opts)

Retrieve the latest trades that have occurred for a specific instrument and within given time range.

### Example

```ruby
# load the gem
require 'openapi_client'
# setup authorization
OpenapiClient.configure do |config|
  # Configure Bearer authorization (Auth. Token): bearerAuth
  config.access_token = 'YOUR_BEARER_TOKEN'
end

api_instance = OpenapiClient::MarketDataApi.new
instrument_name = 'BTC-PERPETUAL' # String | Instrument name
start_timestamp = 1536569522277 # Integer | The earliest timestamp to return result for
end_timestamp = 1536569522277 # Integer | The most recent timestamp to return result for
opts = {
  count: 56, # Integer | Number of requested items, default - `10`
  include_old: true, # Boolean | Include trades older than 7 days, default - `false`
  sorting: 'sorting_example' # String | Direction of results sorting (`default` value means no sorting, results will be returned in order in which they left the database)
}

begin
  #Retrieve the latest trades that have occurred for a specific instrument and within given time range.
  result = api_instance.public_get_last_trades_by_instrument_and_time_get(instrument_name, start_timestamp, end_timestamp, opts)
  p result
rescue OpenapiClient::ApiError => e
  puts "Exception when calling MarketDataApi->public_get_last_trades_by_instrument_and_time_get: #{e}"
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


## public_get_last_trades_by_instrument_get

> Object public_get_last_trades_by_instrument_get(instrument_name, opts)

Retrieve the latest trades that have occurred for a specific instrument.

### Example

```ruby
# load the gem
require 'openapi_client'
# setup authorization
OpenapiClient.configure do |config|
  # Configure Bearer authorization (Auth. Token): bearerAuth
  config.access_token = 'YOUR_BEARER_TOKEN'
end

api_instance = OpenapiClient::MarketDataApi.new
instrument_name = 'BTC-PERPETUAL' # String | Instrument name
opts = {
  start_seq: 56, # Integer | The sequence number of the first trade to be returned
  end_seq: 56, # Integer | The sequence number of the last trade to be returned
  count: 56, # Integer | Number of requested items, default - `10`
  include_old: true, # Boolean | Include trades older than 7 days, default - `false`
  sorting: 'sorting_example' # String | Direction of results sorting (`default` value means no sorting, results will be returned in order in which they left the database)
}

begin
  #Retrieve the latest trades that have occurred for a specific instrument.
  result = api_instance.public_get_last_trades_by_instrument_get(instrument_name, opts)
  p result
rescue OpenapiClient::ApiError => e
  puts "Exception when calling MarketDataApi->public_get_last_trades_by_instrument_get: #{e}"
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


## public_get_order_book_get

> Object public_get_order_book_get(instrument_name, opts)

Retrieves the order book, along with other market values for a given instrument.

### Example

```ruby
# load the gem
require 'openapi_client'
# setup authorization
OpenapiClient.configure do |config|
  # Configure Bearer authorization (Auth. Token): bearerAuth
  config.access_token = 'YOUR_BEARER_TOKEN'
end

api_instance = OpenapiClient::MarketDataApi.new
instrument_name = 'instrument_name_example' # String | The instrument name for which to retrieve the order book, see [`getinstruments`](#getinstruments) to obtain instrument names.
opts = {
  depth: 3.4 # Float | The number of entries to return for bids and asks.
}

begin
  #Retrieves the order book, along with other market values for a given instrument.
  result = api_instance.public_get_order_book_get(instrument_name, opts)
  p result
rescue OpenapiClient::ApiError => e
  puts "Exception when calling MarketDataApi->public_get_order_book_get: #{e}"
end
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrument_name** | **String**| The instrument name for which to retrieve the order book, see [&#x60;getinstruments&#x60;](#getinstruments) to obtain instrument names. | 
 **depth** | **Float**| The number of entries to return for bids and asks. | [optional] 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## public_get_trade_volumes_get

> Object public_get_trade_volumes_get

Retrieves aggregated 24h trade volumes for different instrument types and currencies.

### Example

```ruby
# load the gem
require 'openapi_client'
# setup authorization
OpenapiClient.configure do |config|
  # Configure Bearer authorization (Auth. Token): bearerAuth
  config.access_token = 'YOUR_BEARER_TOKEN'
end

api_instance = OpenapiClient::MarketDataApi.new

begin
  #Retrieves aggregated 24h trade volumes for different instrument types and currencies.
  result = api_instance.public_get_trade_volumes_get
  p result
rescue OpenapiClient::ApiError => e
  puts "Exception when calling MarketDataApi->public_get_trade_volumes_get: #{e}"
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


## public_get_tradingview_chart_data_get

> Object public_get_tradingview_chart_data_get(instrument_name, start_timestamp, end_timestamp)

Publicly available market data used to generate a TradingView candle chart.

### Example

```ruby
# load the gem
require 'openapi_client'
# setup authorization
OpenapiClient.configure do |config|
  # Configure Bearer authorization (Auth. Token): bearerAuth
  config.access_token = 'YOUR_BEARER_TOKEN'
end

api_instance = OpenapiClient::MarketDataApi.new
instrument_name = 'BTC-PERPETUAL' # String | Instrument name
start_timestamp = 1536569522277 # Integer | The earliest timestamp to return result for
end_timestamp = 1536569522277 # Integer | The most recent timestamp to return result for

begin
  #Publicly available market data used to generate a TradingView candle chart.
  result = api_instance.public_get_tradingview_chart_data_get(instrument_name, start_timestamp, end_timestamp)
  p result
rescue OpenapiClient::ApiError => e
  puts "Exception when calling MarketDataApi->public_get_tradingview_chart_data_get: #{e}"
end
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrument_name** | **String**| Instrument name | 
 **start_timestamp** | **Integer**| The earliest timestamp to return result for | 
 **end_timestamp** | **Integer**| The most recent timestamp to return result for | 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## public_ticker_get

> Object public_ticker_get(instrument_name)

Get ticker for an instrument.

### Example

```ruby
# load the gem
require 'openapi_client'
# setup authorization
OpenapiClient.configure do |config|
  # Configure Bearer authorization (Auth. Token): bearerAuth
  config.access_token = 'YOUR_BEARER_TOKEN'
end

api_instance = OpenapiClient::MarketDataApi.new
instrument_name = 'BTC-PERPETUAL' # String | Instrument name

begin
  #Get ticker for an instrument.
  result = api_instance.public_ticker_get(instrument_name)
  p result
rescue OpenapiClient::ApiError => e
  puts "Exception when calling MarketDataApi->public_ticker_get: #{e}"
end
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrument_name** | **String**| Instrument name | 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

