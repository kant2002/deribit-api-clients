# OAIOrder

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**direction** | **NSString*** | direction, &#x60;buy&#x60; or &#x60;sell&#x60; | 
**reduceOnly** | **NSNumber*** | &#x60;true&#x60; for reduce-only orders only | [optional] 
**triggered** | **NSNumber*** | Whether the stop order has been triggered (Only for stop orders) | [optional] 
**orderId** | **NSString*** | Unique order identifier | 
**price** | **NSNumber*** | Price in base currency | 
**timeInForce** | **NSString*** | Order time in force: &#x60;\&quot;good_til_cancelled\&quot;&#x60;, &#x60;\&quot;fill_or_kill\&quot;&#x60;, &#x60;\&quot;immediate_or_cancel\&quot;&#x60; | 
**api** | **NSNumber*** | &#x60;true&#x60; if created with API | 
**orderState** | **NSString*** | order state, &#x60;\&quot;open\&quot;&#x60;, &#x60;\&quot;filled\&quot;&#x60;, &#x60;\&quot;rejected\&quot;&#x60;, &#x60;\&quot;cancelled\&quot;&#x60;, &#x60;\&quot;untriggered\&quot;&#x60; | 
**implv** | **NSNumber*** | Implied volatility in percent. (Only if &#x60;advanced&#x3D;\&quot;implv\&quot;&#x60;) | [optional] 
**advanced** | **NSString*** | advanced type: &#x60;\&quot;usd\&quot;&#x60; or &#x60;\&quot;implv\&quot;&#x60; (Only for options; field is omitted if not applicable).  | [optional] 
**postOnly** | **NSNumber*** | &#x60;true&#x60; for post-only orders only | 
**usd** | **NSNumber*** | Option price in USD (Only if &#x60;advanced&#x3D;\&quot;usd\&quot;&#x60;) | [optional] 
**stopPrice** | **NSNumber*** | stop price (Only for future stop orders) | [optional] 
**orderType** | **NSString*** | order type, &#x60;\&quot;limit\&quot;&#x60;, &#x60;\&quot;market\&quot;&#x60;, &#x60;\&quot;stop_limit\&quot;&#x60;, &#x60;\&quot;stop_market\&quot;&#x60; | 
**lastUpdateTimestamp** | **NSNumber*** | The timestamp (seconds since the Unix epoch, with millisecond precision) | 
**originalOrderType** | **NSString*** | Original order type. Optional field | [optional] 
**maxShow** | **NSNumber*** | Maximum amount within an order to be shown to other traders, 0 for invisible order. | 
**profitLoss** | **NSNumber*** | Profit and loss in base currency. | [optional] 
**isLiquidation** | **NSNumber*** | &#x60;true&#x60; if order was automatically created during liquidation | 
**filledAmount** | **NSNumber*** | Filled amount of the order. For perpetual and futures the filled_amount is in USD units, for options - in units or corresponding cryptocurrency contracts, e.g., BTC or ETH. | [optional] 
**label** | **NSString*** | user defined label (up to 32 characters) | 
**commission** | **NSNumber*** | Commission paid so far (in base currency) | [optional] 
**amount** | **NSNumber*** | It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH. | [optional] 
**trigger** | **NSString*** | Trigger type (Only for stop orders). Allowed values: &#x60;\&quot;index_price\&quot;&#x60;, &#x60;\&quot;mark_price\&quot;&#x60;, &#x60;\&quot;last_price\&quot;&#x60;. | [optional] 
**instrumentName** | **NSString*** | Unique instrument identifier | [optional] 
**creationTimestamp** | **NSNumber*** | The timestamp (seconds since the Unix epoch, with millisecond precision) | 
**averagePrice** | **NSNumber*** | Average fill price of the order | [optional] 

[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)


