
# UserTrade

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**direction** | [**inline**](#DirectionEnum) | Trade direction of the taker | 
**feeCurrency** | [**inline**](#FeeCurrencyEnum) | Currency, i.e &#x60;\&quot;BTC\&quot;&#x60;, &#x60;\&quot;ETH\&quot;&#x60; | 
**orderId** | **kotlin.String** | Id of the user order (maker or taker), i.e. subscriber&#39;s order id that took part in the trade | 
**timestamp** | **kotlin.Int** | The timestamp of the trade | 
**price** | [**java.math.BigDecimal**](java.math.BigDecimal.md) | The price of the trade | 
**iv** | [**java.math.BigDecimal**](java.math.BigDecimal.md) | Option implied volatility for the price (Option only) |  [optional]
**tradeId** | **kotlin.String** | Unique (per currency) trade identifier | 
**fee** | [**java.math.BigDecimal**](java.math.BigDecimal.md) | User&#39;s fee in units of the specified &#x60;fee_currency&#x60; | 
**orderType** | [**inline**](#OrderTypeEnum) | Order type: &#x60;\&quot;limit&#x60;, &#x60;\&quot;market\&quot;&#x60;, or &#x60;\&quot;liquidation\&quot;&#x60; |  [optional]
**tradeSeq** | **kotlin.Int** | The sequence number of the trade within instrument | 
**selfTrade** | **kotlin.Boolean** | &#x60;true&#x60; if the trade is against own order. This can only happen when your account has self-trading enabled. Contact an administrator if you think you need that | 
**state** | [**inline**](#StateEnum) | order state, &#x60;\&quot;open\&quot;&#x60;, &#x60;\&quot;filled\&quot;&#x60;, &#x60;\&quot;rejected\&quot;&#x60;, &#x60;\&quot;cancelled\&quot;&#x60;, &#x60;\&quot;untriggered\&quot;&#x60; or &#x60;\&quot;archive\&quot;&#x60; (if order was archived) | 
**label** | **kotlin.String** | User defined label (presented only when previously set for order by user) |  [optional]
**indexPrice** | [**java.math.BigDecimal**](java.math.BigDecimal.md) | Index Price at the moment of trade | 
**amount** | [**java.math.BigDecimal**](java.math.BigDecimal.md) | Trade amount. For perpetual and futures - in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH. | 
**instrumentName** | **kotlin.String** | Unique instrument identifier | 
**tickDirection** | [**inline**](#TickDirectionEnum) | Direction of the \&quot;tick\&quot; (&#x60;0&#x60; &#x3D; Plus Tick, &#x60;1&#x60; &#x3D; Zero-Plus Tick, &#x60;2&#x60; &#x3D; Minus Tick, &#x60;3&#x60; &#x3D; Zero-Minus Tick). | 
**matchingId** | **kotlin.String** | Always &#x60;null&#x60;, except for a self-trade which is possible only if self-trading is switched on for the account (in that case this will be id of the maker order of the subscriber) | 
**liquidity** | [**inline**](#LiquidityEnum) | Describes what was role of users order: &#x60;\&quot;M\&quot;&#x60; when it was maker order, &#x60;\&quot;T\&quot;&#x60; when it was taker order |  [optional]


<a name="DirectionEnum"></a>
## Enum: direction
Name | Value
---- | -----
direction | buy, sell


<a name="FeeCurrencyEnum"></a>
## Enum: fee_currency
Name | Value
---- | -----
feeCurrency | BTC, ETH


<a name="OrderTypeEnum"></a>
## Enum: order_type
Name | Value
---- | -----
orderType | limit, market, liquidation


<a name="StateEnum"></a>
## Enum: state
Name | Value
---- | -----
state | open, filled, rejected, cancelled, untriggered, archive


<a name="TickDirectionEnum"></a>
## Enum: tick_direction
Name | Value
---- | -----
tickDirection | 0, 1, 2, 3


<a name="LiquidityEnum"></a>
## Enum: liquidity
Name | Value
---- | -----
liquidity | M, T



