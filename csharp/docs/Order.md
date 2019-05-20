
# Org.OpenAPITools.Model.Order

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**Direction** | **string** | direction, &#x60;buy&#x60; or &#x60;sell&#x60; | 
**ReduceOnly** | **bool?** | &#x60;true&#x60; for reduce-only orders only | [optional] 
**Triggered** | **bool?** | Whether the stop order has been triggered (Only for stop orders) | [optional] 
**OrderId** | **string** | Unique order identifier | 
**Price** | **decimal?** | Price in base currency | 
**TimeInForce** | **string** | Order time in force: &#x60;\&quot;good_til_cancelled\&quot;&#x60;, &#x60;\&quot;fill_or_kill\&quot;&#x60;, &#x60;\&quot;immediate_or_cancel\&quot;&#x60; | 
**Api** | **bool?** | &#x60;true&#x60; if created with API | 
**OrderState** | **string** | order state, &#x60;\&quot;open\&quot;&#x60;, &#x60;\&quot;filled\&quot;&#x60;, &#x60;\&quot;rejected\&quot;&#x60;, &#x60;\&quot;cancelled\&quot;&#x60;, &#x60;\&quot;untriggered\&quot;&#x60; | 
**Implv** | **decimal?** | Implied volatility in percent. (Only if &#x60;advanced&#x3D;\&quot;implv\&quot;&#x60;) | [optional] 
**Advanced** | **string** | advanced type: &#x60;\&quot;usd\&quot;&#x60; or &#x60;\&quot;implv\&quot;&#x60; (Only for options; field is omitted if not applicable).  | [optional] 
**PostOnly** | **bool?** | &#x60;true&#x60; for post-only orders only | 
**Usd** | **decimal?** | Option price in USD (Only if &#x60;advanced&#x3D;\&quot;usd\&quot;&#x60;) | [optional] 
**StopPrice** | **decimal?** | stop price (Only for future stop orders) | [optional] 
**OrderType** | **string** | order type, &#x60;\&quot;limit\&quot;&#x60;, &#x60;\&quot;market\&quot;&#x60;, &#x60;\&quot;stop_limit\&quot;&#x60;, &#x60;\&quot;stop_market\&quot;&#x60; | 
**LastUpdateTimestamp** | **int?** | The timestamp (seconds since the Unix epoch, with millisecond precision) | 
**OriginalOrderType** | **string** | Original order type. Optional field | [optional] 
**MaxShow** | **decimal?** | Maximum amount within an order to be shown to other traders, 0 for invisible order. | 
**ProfitLoss** | **decimal?** | Profit and loss in base currency. | [optional] 
**IsLiquidation** | **bool?** | &#x60;true&#x60; if order was automatically created during liquidation | 
**FilledAmount** | **decimal?** | Filled amount of the order. For perpetual and futures the filled_amount is in USD units, for options - in units or corresponding cryptocurrency contracts, e.g., BTC or ETH. | [optional] 
**Label** | **string** | user defined label (up to 32 characters) | 
**Commission** | **decimal?** | Commission paid so far (in base currency) | [optional] 
**Amount** | **decimal?** | It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH. | [optional] 
**Trigger** | **string** | Trigger type (Only for stop orders). Allowed values: &#x60;\&quot;index_price\&quot;&#x60;, &#x60;\&quot;mark_price\&quot;&#x60;, &#x60;\&quot;last_price\&quot;&#x60;. | [optional] 
**InstrumentName** | **string** | Unique instrument identifier | [optional] 
**CreationTimestamp** | **int?** | The timestamp (seconds since the Unix epoch, with millisecond precision) | 
**AveragePrice** | **decimal?** | Average fill price of the order | [optional] 

[[Back to Model list]](../README.md#documentation-for-models)
[[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to README]](../README.md)

