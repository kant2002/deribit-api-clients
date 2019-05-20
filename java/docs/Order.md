

# Order

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**direction** | [**DirectionEnum**](#DirectionEnum) | direction, &#x60;buy&#x60; or &#x60;sell&#x60; | 
**reduceOnly** | **Boolean** | &#x60;true&#x60; for reduce-only orders only |  [optional]
**triggered** | **Boolean** | Whether the stop order has been triggered (Only for stop orders) |  [optional]
**orderId** | **String** | Unique order identifier | 
**price** | [**BigDecimal**](BigDecimal.md) | Price in base currency | 
**timeInForce** | [**TimeInForceEnum**](#TimeInForceEnum) | Order time in force: &#x60;\&quot;good_til_cancelled\&quot;&#x60;, &#x60;\&quot;fill_or_kill\&quot;&#x60;, &#x60;\&quot;immediate_or_cancel\&quot;&#x60; | 
**api** | **Boolean** | &#x60;true&#x60; if created with API | 
**orderState** | [**OrderStateEnum**](#OrderStateEnum) | order state, &#x60;\&quot;open\&quot;&#x60;, &#x60;\&quot;filled\&quot;&#x60;, &#x60;\&quot;rejected\&quot;&#x60;, &#x60;\&quot;cancelled\&quot;&#x60;, &#x60;\&quot;untriggered\&quot;&#x60; | 
**implv** | [**BigDecimal**](BigDecimal.md) | Implied volatility in percent. (Only if &#x60;advanced&#x3D;\&quot;implv\&quot;&#x60;) |  [optional]
**advanced** | [**AdvancedEnum**](#AdvancedEnum) | advanced type: &#x60;\&quot;usd\&quot;&#x60; or &#x60;\&quot;implv\&quot;&#x60; (Only for options; field is omitted if not applicable).  |  [optional]
**postOnly** | **Boolean** | &#x60;true&#x60; for post-only orders only | 
**usd** | [**BigDecimal**](BigDecimal.md) | Option price in USD (Only if &#x60;advanced&#x3D;\&quot;usd\&quot;&#x60;) |  [optional]
**stopPrice** | [**BigDecimal**](BigDecimal.md) | stop price (Only for future stop orders) |  [optional]
**orderType** | [**OrderTypeEnum**](#OrderTypeEnum) | order type, &#x60;\&quot;limit\&quot;&#x60;, &#x60;\&quot;market\&quot;&#x60;, &#x60;\&quot;stop_limit\&quot;&#x60;, &#x60;\&quot;stop_market\&quot;&#x60; | 
**lastUpdateTimestamp** | **Integer** | The timestamp (seconds since the Unix epoch, with millisecond precision) | 
**originalOrderType** | [**OriginalOrderTypeEnum**](#OriginalOrderTypeEnum) | Original order type. Optional field |  [optional]
**maxShow** | [**BigDecimal**](BigDecimal.md) | Maximum amount within an order to be shown to other traders, 0 for invisible order. | 
**profitLoss** | [**BigDecimal**](BigDecimal.md) | Profit and loss in base currency. |  [optional]
**isLiquidation** | **Boolean** | &#x60;true&#x60; if order was automatically created during liquidation | 
**filledAmount** | [**BigDecimal**](BigDecimal.md) | Filled amount of the order. For perpetual and futures the filled_amount is in USD units, for options - in units or corresponding cryptocurrency contracts, e.g., BTC or ETH. |  [optional]
**label** | **String** | user defined label (up to 32 characters) | 
**commission** | [**BigDecimal**](BigDecimal.md) | Commission paid so far (in base currency) |  [optional]
**amount** | [**BigDecimal**](BigDecimal.md) | It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH. |  [optional]
**trigger** | [**TriggerEnum**](#TriggerEnum) | Trigger type (Only for stop orders). Allowed values: &#x60;\&quot;index_price\&quot;&#x60;, &#x60;\&quot;mark_price\&quot;&#x60;, &#x60;\&quot;last_price\&quot;&#x60;. |  [optional]
**instrumentName** | **String** | Unique instrument identifier |  [optional]
**creationTimestamp** | **Integer** | The timestamp (seconds since the Unix epoch, with millisecond precision) | 
**averagePrice** | [**BigDecimal**](BigDecimal.md) | Average fill price of the order |  [optional]



## Enum: DirectionEnum

Name | Value
---- | -----
BUY | &quot;buy&quot;
SELL | &quot;sell&quot;



## Enum: TimeInForceEnum

Name | Value
---- | -----
GOOD_TIL_CANCELLED | &quot;good_til_cancelled&quot;
FILL_OR_KILL | &quot;fill_or_kill&quot;
IMMEDIATE_OR_CANCEL | &quot;immediate_or_cancel&quot;



## Enum: OrderStateEnum

Name | Value
---- | -----
OPEN | &quot;open&quot;
FILLED | &quot;filled&quot;
REJECTED | &quot;rejected&quot;
CANCELLED | &quot;cancelled&quot;
UNTRIGGERED | &quot;untriggered&quot;
TRIGGERED | &quot;triggered&quot;



## Enum: AdvancedEnum

Name | Value
---- | -----
USD | &quot;usd&quot;
IMPLV | &quot;implv&quot;



## Enum: OrderTypeEnum

Name | Value
---- | -----
MARKET | &quot;market&quot;
LIMIT | &quot;limit&quot;
STOP_MARKET | &quot;stop_market&quot;
STOP_LIMIT | &quot;stop_limit&quot;



## Enum: OriginalOrderTypeEnum

Name | Value
---- | -----
MARKET | &quot;market&quot;



## Enum: TriggerEnum

Name | Value
---- | -----
INDEX_PRICE | &quot;index_price&quot;
MARK_PRICE | &quot;mark_price&quot;
LAST_PRICE | &quot;last_price&quot;



