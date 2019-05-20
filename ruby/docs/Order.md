# OpenapiClient::Order

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**direction** | **String** | direction, &#x60;buy&#x60; or &#x60;sell&#x60; | 
**reduce_only** | **Boolean** | &#x60;true&#x60; for reduce-only orders only | [optional] 
**triggered** | **Boolean** | Whether the stop order has been triggered (Only for stop orders) | [optional] 
**order_id** | **String** | Unique order identifier | 
**price** | **Float** | Price in base currency | 
**time_in_force** | **String** | Order time in force: &#x60;\&quot;good_til_cancelled\&quot;&#x60;, &#x60;\&quot;fill_or_kill\&quot;&#x60;, &#x60;\&quot;immediate_or_cancel\&quot;&#x60; | 
**api** | **Boolean** | &#x60;true&#x60; if created with API | 
**order_state** | **String** | order state, &#x60;\&quot;open\&quot;&#x60;, &#x60;\&quot;filled\&quot;&#x60;, &#x60;\&quot;rejected\&quot;&#x60;, &#x60;\&quot;cancelled\&quot;&#x60;, &#x60;\&quot;untriggered\&quot;&#x60; | 
**implv** | **Float** | Implied volatility in percent. (Only if &#x60;advanced&#x3D;\&quot;implv\&quot;&#x60;) | [optional] 
**advanced** | **String** | advanced type: &#x60;\&quot;usd\&quot;&#x60; or &#x60;\&quot;implv\&quot;&#x60; (Only for options; field is omitted if not applicable).  | [optional] 
**post_only** | **Boolean** | &#x60;true&#x60; for post-only orders only | 
**usd** | **Float** | Option price in USD (Only if &#x60;advanced&#x3D;\&quot;usd\&quot;&#x60;) | [optional] 
**stop_price** | **Float** | stop price (Only for future stop orders) | [optional] 
**order_type** | **String** | order type, &#x60;\&quot;limit\&quot;&#x60;, &#x60;\&quot;market\&quot;&#x60;, &#x60;\&quot;stop_limit\&quot;&#x60;, &#x60;\&quot;stop_market\&quot;&#x60; | 
**last_update_timestamp** | **Integer** | The timestamp (seconds since the Unix epoch, with millisecond precision) | 
**original_order_type** | **String** | Original order type. Optional field | [optional] 
**max_show** | **Float** | Maximum amount within an order to be shown to other traders, 0 for invisible order. | 
**profit_loss** | **Float** | Profit and loss in base currency. | [optional] 
**is_liquidation** | **Boolean** | &#x60;true&#x60; if order was automatically created during liquidation | 
**filled_amount** | **Float** | Filled amount of the order. For perpetual and futures the filled_amount is in USD units, for options - in units or corresponding cryptocurrency contracts, e.g., BTC or ETH. | [optional] 
**label** | **String** | user defined label (up to 32 characters) | 
**commission** | **Float** | Commission paid so far (in base currency) | [optional] 
**amount** | **Float** | It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH. | [optional] 
**trigger** | **String** | Trigger type (Only for stop orders). Allowed values: &#x60;\&quot;index_price\&quot;&#x60;, &#x60;\&quot;mark_price\&quot;&#x60;, &#x60;\&quot;last_price\&quot;&#x60;. | [optional] 
**instrument_name** | **String** | Unique instrument identifier | [optional] 
**creation_timestamp** | **Integer** | The timestamp (seconds since the Unix epoch, with millisecond precision) | 
**average_price** | **Float** | Average fill price of the order | [optional] 

## Code Sample

```ruby
require 'OpenapiClient'

instance = OpenapiClient::Order.new(direction: null,
                                 reduce_only: null,
                                 triggered: null,
                                 order_id: ETH-100234,
                                 price: null,
                                 time_in_force: null,
                                 api: null,
                                 order_state: null,
                                 implv: null,
                                 advanced: null,
                                 post_only: null,
                                 usd: null,
                                 stop_price: null,
                                 order_type: null,
                                 last_update_timestamp: 1536569522277,
                                 original_order_type: null,
                                 max_show: null,
                                 profit_loss: null,
                                 is_liquidation: null,
                                 filled_amount: null,
                                 label: null,
                                 commission: null,
                                 amount: null,
                                 trigger: null,
                                 instrument_name: BTC-PERPETUAL,
                                 creation_timestamp: 1536569522277,
                                 average_price: null)
```


