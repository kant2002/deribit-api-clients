# DeribitApi.Order

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**direction** | **String** | direction, &#x60;buy&#x60; or &#x60;sell&#x60; | 
**reduceOnly** | **Boolean** | &#x60;true&#x60; for reduce-only orders only | [optional] 
**triggered** | **Boolean** | Whether the stop order has been triggered (Only for stop orders) | [optional] 
**orderId** | **String** | Unique order identifier | 
**price** | **Number** | Price in base currency | 
**timeInForce** | **String** | Order time in force: &#x60;\&quot;good_til_cancelled\&quot;&#x60;, &#x60;\&quot;fill_or_kill\&quot;&#x60;, &#x60;\&quot;immediate_or_cancel\&quot;&#x60; | 
**api** | **Boolean** | &#x60;true&#x60; if created with API | 
**orderState** | **String** | order state, &#x60;\&quot;open\&quot;&#x60;, &#x60;\&quot;filled\&quot;&#x60;, &#x60;\&quot;rejected\&quot;&#x60;, &#x60;\&quot;cancelled\&quot;&#x60;, &#x60;\&quot;untriggered\&quot;&#x60; | 
**implv** | **Number** | Implied volatility in percent. (Only if &#x60;advanced&#x3D;\&quot;implv\&quot;&#x60;) | [optional] 
**advanced** | **String** | advanced type: &#x60;\&quot;usd\&quot;&#x60; or &#x60;\&quot;implv\&quot;&#x60; (Only for options; field is omitted if not applicable).  | [optional] 
**postOnly** | **Boolean** | &#x60;true&#x60; for post-only orders only | 
**usd** | **Number** | Option price in USD (Only if &#x60;advanced&#x3D;\&quot;usd\&quot;&#x60;) | [optional] 
**stopPrice** | **Number** | stop price (Only for future stop orders) | [optional] 
**orderType** | **String** | order type, &#x60;\&quot;limit\&quot;&#x60;, &#x60;\&quot;market\&quot;&#x60;, &#x60;\&quot;stop_limit\&quot;&#x60;, &#x60;\&quot;stop_market\&quot;&#x60; | 
**lastUpdateTimestamp** | **Number** | The timestamp (seconds since the Unix epoch, with millisecond precision) | 
**originalOrderType** | **String** | Original order type. Optional field | [optional] 
**maxShow** | **Number** | Maximum amount within an order to be shown to other traders, 0 for invisible order. | 
**profitLoss** | **Number** | Profit and loss in base currency. | [optional] 
**isLiquidation** | **Boolean** | &#x60;true&#x60; if order was automatically created during liquidation | 
**filledAmount** | **Number** | Filled amount of the order. For perpetual and futures the filled_amount is in USD units, for options - in units or corresponding cryptocurrency contracts, e.g., BTC or ETH. | [optional] 
**label** | **String** | user defined label (up to 32 characters) | 
**commission** | **Number** | Commission paid so far (in base currency) | [optional] 
**amount** | **Number** | It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH. | [optional] 
**trigger** | **String** | Trigger type (Only for stop orders). Allowed values: &#x60;\&quot;index_price\&quot;&#x60;, &#x60;\&quot;mark_price\&quot;&#x60;, &#x60;\&quot;last_price\&quot;&#x60;. | [optional] 
**instrumentName** | **String** | Unique instrument identifier | [optional] 
**creationTimestamp** | **Number** | The timestamp (seconds since the Unix epoch, with millisecond precision) | 
**averagePrice** | **Number** | Average fill price of the order | [optional] 



## Enum: DirectionEnum


* `buy` (value: `"buy"`)

* `sell` (value: `"sell"`)





## Enum: TimeInForceEnum


* `good_til_cancelled` (value: `"good_til_cancelled"`)

* `fill_or_kill` (value: `"fill_or_kill"`)

* `immediate_or_cancel` (value: `"immediate_or_cancel"`)





## Enum: OrderStateEnum


* `open` (value: `"open"`)

* `filled` (value: `"filled"`)

* `rejected` (value: `"rejected"`)

* `cancelled` (value: `"cancelled"`)

* `untriggered` (value: `"untriggered"`)

* `triggered` (value: `"triggered"`)





## Enum: AdvancedEnum


* `usd` (value: `"usd"`)

* `implv` (value: `"implv"`)





## Enum: OrderTypeEnum


* `market` (value: `"market"`)

* `limit` (value: `"limit"`)

* `stop_market` (value: `"stop_market"`)

* `stop_limit` (value: `"stop_limit"`)





## Enum: OriginalOrderTypeEnum


* `market` (value: `"market"`)





## Enum: TriggerEnum


* `index_price` (value: `"index_price"`)

* `mark_price` (value: `"mark_price"`)

* `last_price` (value: `"last_price"`)




