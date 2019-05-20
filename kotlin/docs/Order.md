
# Order

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**direction** | [**inline**](#DirectionEnum) | direction, &#x60;buy&#x60; or &#x60;sell&#x60; | 
**reduceOnly** | **kotlin.Boolean** | &#x60;true&#x60; for reduce-only orders only |  [optional]
**triggered** | **kotlin.Boolean** | Whether the stop order has been triggered (Only for stop orders) |  [optional]
**orderId** | **kotlin.String** | Unique order identifier | 
**price** | [**java.math.BigDecimal**](java.math.BigDecimal.md) | Price in base currency | 
**timeInForce** | [**inline**](#TimeInForceEnum) | Order time in force: &#x60;\&quot;good_til_cancelled\&quot;&#x60;, &#x60;\&quot;fill_or_kill\&quot;&#x60;, &#x60;\&quot;immediate_or_cancel\&quot;&#x60; | 
**api** | **kotlin.Boolean** | &#x60;true&#x60; if created with API | 
**orderState** | [**inline**](#OrderStateEnum) | order state, &#x60;\&quot;open\&quot;&#x60;, &#x60;\&quot;filled\&quot;&#x60;, &#x60;\&quot;rejected\&quot;&#x60;, &#x60;\&quot;cancelled\&quot;&#x60;, &#x60;\&quot;untriggered\&quot;&#x60; | 
**implv** | [**java.math.BigDecimal**](java.math.BigDecimal.md) | Implied volatility in percent. (Only if &#x60;advanced&#x3D;\&quot;implv\&quot;&#x60;) |  [optional]
**advanced** | [**inline**](#AdvancedEnum) | advanced type: &#x60;\&quot;usd\&quot;&#x60; or &#x60;\&quot;implv\&quot;&#x60; (Only for options; field is omitted if not applicable).  |  [optional]
**postOnly** | **kotlin.Boolean** | &#x60;true&#x60; for post-only orders only | 
**usd** | [**java.math.BigDecimal**](java.math.BigDecimal.md) | Option price in USD (Only if &#x60;advanced&#x3D;\&quot;usd\&quot;&#x60;) |  [optional]
**stopPrice** | [**java.math.BigDecimal**](java.math.BigDecimal.md) | stop price (Only for future stop orders) |  [optional]
**orderType** | [**inline**](#OrderTypeEnum) | order type, &#x60;\&quot;limit\&quot;&#x60;, &#x60;\&quot;market\&quot;&#x60;, &#x60;\&quot;stop_limit\&quot;&#x60;, &#x60;\&quot;stop_market\&quot;&#x60; | 
**lastUpdateTimestamp** | **kotlin.Int** | The timestamp (seconds since the Unix epoch, with millisecond precision) | 
**originalOrderType** | [**inline**](#OriginalOrderTypeEnum) | Original order type. Optional field |  [optional]
**maxShow** | [**java.math.BigDecimal**](java.math.BigDecimal.md) | Maximum amount within an order to be shown to other traders, 0 for invisible order. | 
**profitLoss** | [**java.math.BigDecimal**](java.math.BigDecimal.md) | Profit and loss in base currency. |  [optional]
**isLiquidation** | **kotlin.Boolean** | &#x60;true&#x60; if order was automatically created during liquidation | 
**filledAmount** | [**java.math.BigDecimal**](java.math.BigDecimal.md) | Filled amount of the order. For perpetual and futures the filled_amount is in USD units, for options - in units or corresponding cryptocurrency contracts, e.g., BTC or ETH. |  [optional]
**label** | **kotlin.String** | user defined label (up to 32 characters) | 
**commission** | [**java.math.BigDecimal**](java.math.BigDecimal.md) | Commission paid so far (in base currency) |  [optional]
**amount** | [**java.math.BigDecimal**](java.math.BigDecimal.md) | It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH. |  [optional]
**trigger** | [**inline**](#TriggerEnum) | Trigger type (Only for stop orders). Allowed values: &#x60;\&quot;index_price\&quot;&#x60;, &#x60;\&quot;mark_price\&quot;&#x60;, &#x60;\&quot;last_price\&quot;&#x60;. |  [optional]
**instrumentName** | **kotlin.String** | Unique instrument identifier |  [optional]
**creationTimestamp** | **kotlin.Int** | The timestamp (seconds since the Unix epoch, with millisecond precision) | 
**averagePrice** | [**java.math.BigDecimal**](java.math.BigDecimal.md) | Average fill price of the order |  [optional]


<a name="DirectionEnum"></a>
## Enum: direction
Name | Value
---- | -----
direction | buy, sell


<a name="TimeInForceEnum"></a>
## Enum: time_in_force
Name | Value
---- | -----
timeInForce | good_til_cancelled, fill_or_kill, immediate_or_cancel


<a name="OrderStateEnum"></a>
## Enum: order_state
Name | Value
---- | -----
orderState | open, filled, rejected, cancelled, untriggered, triggered


<a name="AdvancedEnum"></a>
## Enum: advanced
Name | Value
---- | -----
advanced | usd, implv


<a name="OrderTypeEnum"></a>
## Enum: order_type
Name | Value
---- | -----
orderType | market, limit, stop_market, stop_limit


<a name="OriginalOrderTypeEnum"></a>
## Enum: original_order_type
Name | Value
---- | -----
originalOrderType | market


<a name="TriggerEnum"></a>
## Enum: trigger
Name | Value
---- | -----
trigger | index_price, mark_price, last_price



