
# PublicTrade

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**direction** | [**inline**](#DirectionEnum) | Trade direction of the taker | 
**tickDirection** | [**inline**](#TickDirectionEnum) | Direction of the \&quot;tick\&quot; (&#x60;0&#x60; &#x3D; Plus Tick, &#x60;1&#x60; &#x3D; Zero-Plus Tick, &#x60;2&#x60; &#x3D; Minus Tick, &#x60;3&#x60; &#x3D; Zero-Minus Tick). | 
**timestamp** | **kotlin.Int** | The timestamp of the trade | 
**price** | [**java.math.BigDecimal**](java.math.BigDecimal.md) | The price of the trade | 
**tradeSeq** | **kotlin.Int** | The sequence number of the trade within instrument | 
**tradeId** | **kotlin.String** | Unique (per currency) trade identifier | 
**iv** | [**java.math.BigDecimal**](java.math.BigDecimal.md) | Option implied volatility for the price (Option only) |  [optional]
**indexPrice** | [**java.math.BigDecimal**](java.math.BigDecimal.md) | Index Price at the moment of trade | 
**amount** | [**java.math.BigDecimal**](java.math.BigDecimal.md) | Trade amount. For perpetual and futures - in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH. | 
**instrumentName** | **kotlin.String** | Unique instrument identifier | 


<a name="DirectionEnum"></a>
## Enum: direction
Name | Value
---- | -----
direction | buy, sell


<a name="TickDirectionEnum"></a>
## Enum: tick_direction
Name | Value
---- | -----
tickDirection | 0, 1, 2, 3



