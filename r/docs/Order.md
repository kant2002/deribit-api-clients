# openapi::Order

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**direction** | **character** | direction, &#x60;buy&#x60; or &#x60;sell&#x60; | 
**reduce_only** | **character** | &#x60;true&#x60; for reduce-only orders only | [optional] 
**triggered** | **character** | Whether the stop order has been triggered (Only for stop orders) | [optional] 
**order_id** | **character** | Unique order identifier | 
**price** | **numeric** | Price in base currency | 
**time_in_force** | **character** | Order time in force: &#x60;\&quot;good_til_cancelled\&quot;&#x60;, &#x60;\&quot;fill_or_kill\&quot;&#x60;, &#x60;\&quot;immediate_or_cancel\&quot;&#x60; | 
**api** | **character** | &#x60;true&#x60; if created with API | 
**order_state** | **character** | order state, &#x60;\&quot;open\&quot;&#x60;, &#x60;\&quot;filled\&quot;&#x60;, &#x60;\&quot;rejected\&quot;&#x60;, &#x60;\&quot;cancelled\&quot;&#x60;, &#x60;\&quot;untriggered\&quot;&#x60; | 
**implv** | **numeric** | Implied volatility in percent. (Only if &#x60;advanced&#x3D;\&quot;implv\&quot;&#x60;) | [optional] 
**advanced** | **character** | advanced type: &#x60;\&quot;usd\&quot;&#x60; or &#x60;\&quot;implv\&quot;&#x60; (Only for options; field is omitted if not applicable).  | [optional] 
**post_only** | **character** | &#x60;true&#x60; for post-only orders only | 
**usd** | **numeric** | Option price in USD (Only if &#x60;advanced&#x3D;\&quot;usd\&quot;&#x60;) | [optional] 
**stop_price** | **numeric** | stop price (Only for future stop orders) | [optional] 
**order_type** | **character** | order type, &#x60;\&quot;limit\&quot;&#x60;, &#x60;\&quot;market\&quot;&#x60;, &#x60;\&quot;stop_limit\&quot;&#x60;, &#x60;\&quot;stop_market\&quot;&#x60; | 
**last_update_timestamp** | **integer** | The timestamp (seconds since the Unix epoch, with millisecond precision) | 
**original_order_type** | **character** | Original order type. Optional field | [optional] 
**max_show** | **numeric** | Maximum amount within an order to be shown to other traders, 0 for invisible order. | 
**profit_loss** | **numeric** | Profit and loss in base currency. | [optional] 
**is_liquidation** | **character** | &#x60;true&#x60; if order was automatically created during liquidation | 
**filled_amount** | **numeric** | Filled amount of the order. For perpetual and futures the filled_amount is in USD units, for options - in units or corresponding cryptocurrency contracts, e.g., BTC or ETH. | [optional] 
**label** | **character** | user defined label (up to 32 characters) | 
**commission** | **numeric** | Commission paid so far (in base currency) | [optional] 
**amount** | **numeric** | It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH. | [optional] 
**trigger** | **character** | Trigger type (Only for stop orders). Allowed values: &#x60;\&quot;index_price\&quot;&#x60;, &#x60;\&quot;mark_price\&quot;&#x60;, &#x60;\&quot;last_price\&quot;&#x60;. | [optional] 
**instrument_name** | **character** | Unique instrument identifier | [optional] 
**creation_timestamp** | **integer** | The timestamp (seconds since the Unix epoch, with millisecond precision) | 
**average_price** | **numeric** | Average fill price of the order | [optional] 


