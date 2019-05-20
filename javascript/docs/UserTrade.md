# DeribitApi.UserTrade

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**direction** | **String** | Trade direction of the taker | 
**feeCurrency** | **String** | Currency, i.e &#x60;\&quot;BTC\&quot;&#x60;, &#x60;\&quot;ETH\&quot;&#x60; | 
**orderId** | **String** | Id of the user order (maker or taker), i.e. subscriber&#39;s order id that took part in the trade | 
**timestamp** | **Number** | The timestamp of the trade | 
**price** | **Number** | The price of the trade | 
**iv** | **Number** | Option implied volatility for the price (Option only) | [optional] 
**tradeId** | **String** | Unique (per currency) trade identifier | 
**fee** | **Number** | User&#39;s fee in units of the specified &#x60;fee_currency&#x60; | 
**orderType** | **String** | Order type: &#x60;\&quot;limit&#x60;, &#x60;\&quot;market\&quot;&#x60;, or &#x60;\&quot;liquidation\&quot;&#x60; | [optional] 
**tradeSeq** | **Number** | The sequence number of the trade within instrument | 
**selfTrade** | **Boolean** | &#x60;true&#x60; if the trade is against own order. This can only happen when your account has self-trading enabled. Contact an administrator if you think you need that | 
**state** | **String** | order state, &#x60;\&quot;open\&quot;&#x60;, &#x60;\&quot;filled\&quot;&#x60;, &#x60;\&quot;rejected\&quot;&#x60;, &#x60;\&quot;cancelled\&quot;&#x60;, &#x60;\&quot;untriggered\&quot;&#x60; or &#x60;\&quot;archive\&quot;&#x60; (if order was archived) | 
**label** | **String** | User defined label (presented only when previously set for order by user) | [optional] 
**indexPrice** | **Number** | Index Price at the moment of trade | 
**amount** | **Number** | Trade amount. For perpetual and futures - in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH. | 
**instrumentName** | **String** | Unique instrument identifier | 
**tickDirection** | **Number** | Direction of the \&quot;tick\&quot; (&#x60;0&#x60; &#x3D; Plus Tick, &#x60;1&#x60; &#x3D; Zero-Plus Tick, &#x60;2&#x60; &#x3D; Minus Tick, &#x60;3&#x60; &#x3D; Zero-Minus Tick). | 
**matchingId** | **String** | Always &#x60;null&#x60;, except for a self-trade which is possible only if self-trading is switched on for the account (in that case this will be id of the maker order of the subscriber) | 
**liquidity** | **String** | Describes what was role of users order: &#x60;\&quot;M\&quot;&#x60; when it was maker order, &#x60;\&quot;T\&quot;&#x60; when it was taker order | [optional] 



## Enum: DirectionEnum


* `buy` (value: `"buy"`)

* `sell` (value: `"sell"`)





## Enum: FeeCurrencyEnum


* `BTC` (value: `"BTC"`)

* `ETH` (value: `"ETH"`)





## Enum: OrderTypeEnum


* `limit` (value: `"limit"`)

* `market` (value: `"market"`)

* `liquidation` (value: `"liquidation"`)





## Enum: StateEnum


* `open` (value: `"open"`)

* `filled` (value: `"filled"`)

* `rejected` (value: `"rejected"`)

* `cancelled` (value: `"cancelled"`)

* `untriggered` (value: `"untriggered"`)

* `archive` (value: `"archive"`)





## Enum: TickDirectionEnum


* `0` (value: `0`)

* `1` (value: `1`)

* `2` (value: `2`)

* `3` (value: `3`)





## Enum: LiquidityEnum


* `M` (value: `"M"`)

* `T` (value: `"T"`)




