

# PublicTrade

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**direction** | [**DirectionEnum**](#DirectionEnum) | Trade direction of the taker | 
**tickDirection** | [**TickDirectionEnum**](#TickDirectionEnum) | Direction of the \&quot;tick\&quot; (&#x60;0&#x60; &#x3D; Plus Tick, &#x60;1&#x60; &#x3D; Zero-Plus Tick, &#x60;2&#x60; &#x3D; Minus Tick, &#x60;3&#x60; &#x3D; Zero-Minus Tick). | 
**timestamp** | **Integer** | The timestamp of the trade | 
**price** | [**BigDecimal**](BigDecimal.md) | The price of the trade | 
**tradeSeq** | **Integer** | The sequence number of the trade within instrument | 
**tradeId** | **String** | Unique (per currency) trade identifier | 
**iv** | [**BigDecimal**](BigDecimal.md) | Option implied volatility for the price (Option only) |  [optional]
**indexPrice** | [**BigDecimal**](BigDecimal.md) | Index Price at the moment of trade | 
**amount** | [**BigDecimal**](BigDecimal.md) | Trade amount. For perpetual and futures - in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH. | 
**instrumentName** | **String** | Unique instrument identifier | 


## Enum: DirectionEnum

Name | Value
---- | -----


## Enum: TickDirectionEnum

Name | Value
---- | -----




