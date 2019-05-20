# Order

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**direction** | **String** | direction, &#x60;buy&#x60; or &#x60;sell&#x60; | 
**reduceOnly** | **Bool** | &#x60;true&#x60; for reduce-only orders only | [optional] 
**triggered** | **Bool** | Whether the stop order has been triggered (Only for stop orders) | [optional] 
**orderId** | **String** | Unique order identifier | 
**price** | **Double** | Price in base currency | 
**timeInForce** | **String** | Order time in force: &#x60;\&quot;good_til_cancelled\&quot;&#x60;, &#x60;\&quot;fill_or_kill\&quot;&#x60;, &#x60;\&quot;immediate_or_cancel\&quot;&#x60; | 
**api** | **Bool** | &#x60;true&#x60; if created with API | 
**orderState** | **String** | order state, &#x60;\&quot;open\&quot;&#x60;, &#x60;\&quot;filled\&quot;&#x60;, &#x60;\&quot;rejected\&quot;&#x60;, &#x60;\&quot;cancelled\&quot;&#x60;, &#x60;\&quot;untriggered\&quot;&#x60; | 
**implv** | **Double** | Implied volatility in percent. (Only if &#x60;advanced&#x3D;\&quot;implv\&quot;&#x60;) | [optional] 
**advanced** | **String** | advanced type: &#x60;\&quot;usd\&quot;&#x60; or &#x60;\&quot;implv\&quot;&#x60; (Only for options; field is omitted if not applicable).  | [optional] 
**postOnly** | **Bool** | &#x60;true&#x60; for post-only orders only | 
**usd** | **Double** | Option price in USD (Only if &#x60;advanced&#x3D;\&quot;usd\&quot;&#x60;) | [optional] 
**stopPrice** | **Double** | stop price (Only for future stop orders) | [optional] 
**orderType** | **String** | order type, &#x60;\&quot;limit\&quot;&#x60;, &#x60;\&quot;market\&quot;&#x60;, &#x60;\&quot;stop_limit\&quot;&#x60;, &#x60;\&quot;stop_market\&quot;&#x60; | 
**lastUpdateTimestamp** | **Int** | The timestamp (seconds since the Unix epoch, with millisecond precision) | 
**originalOrderType** | **String** | Original order type. Optional field | [optional] 
**maxShow** | **Double** | Maximum amount within an order to be shown to other traders, 0 for invisible order. | 
**profitLoss** | **Double** | Profit and loss in base currency. | [optional] 
**isLiquidation** | **Bool** | &#x60;true&#x60; if order was automatically created during liquidation | 
**filledAmount** | **Double** | Filled amount of the order. For perpetual and futures the filled_amount is in USD units, for options - in units or corresponding cryptocurrency contracts, e.g., BTC or ETH. | [optional] 
**label** | **String** | user defined label (up to 32 characters) | 
**commission** | **Double** | Commission paid so far (in base currency) | [optional] 
**amount** | **Double** | It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH. | [optional] 
**trigger** | **String** | Trigger type (Only for stop orders). Allowed values: &#x60;\&quot;index_price\&quot;&#x60;, &#x60;\&quot;mark_price\&quot;&#x60;, &#x60;\&quot;last_price\&quot;&#x60;. | [optional] 
**instrumentName** | **String** | Unique instrument identifier | [optional] 
**creationTimestamp** | **Int** | The timestamp (seconds since the Unix epoch, with millisecond precision) | 
**averagePrice** | **Double** | Average fill price of the order | [optional] 

[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)


