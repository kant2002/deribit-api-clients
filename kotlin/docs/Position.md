
# Position

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**direction** | [**inline**](#DirectionEnum) | direction, &#x60;buy&#x60; or &#x60;sell&#x60; | 
**averagePriceUsd** | [**java.math.BigDecimal**](java.math.BigDecimal.md) | Only for options, average price in USD |  [optional]
**estimatedLiquidationPrice** | [**java.math.BigDecimal**](java.math.BigDecimal.md) | Only for futures, estimated liquidation price |  [optional]
**floatingProfitLoss** | [**java.math.BigDecimal**](java.math.BigDecimal.md) | Floating profit or loss | 
**floatingProfitLossUsd** | [**java.math.BigDecimal**](java.math.BigDecimal.md) | Only for options, floating profit or loss in USD |  [optional]
**openOrdersMargin** | [**java.math.BigDecimal**](java.math.BigDecimal.md) | Open orders margin | 
**totalProfitLoss** | [**java.math.BigDecimal**](java.math.BigDecimal.md) | Profit or loss from position | 
**realizedProfitLoss** | [**java.math.BigDecimal**](java.math.BigDecimal.md) | Realized profit or loss |  [optional]
**delta** | [**java.math.BigDecimal**](java.math.BigDecimal.md) | Delta parameter | 
**initialMargin** | [**java.math.BigDecimal**](java.math.BigDecimal.md) | Initial margin | 
**size** | [**java.math.BigDecimal**](java.math.BigDecimal.md) | Position size for futures size in quote currency (e.g. USD), for options size is in base currency (e.g. BTC) | 
**maintenanceMargin** | [**java.math.BigDecimal**](java.math.BigDecimal.md) | Maintenance margin | 
**kind** | [**inline**](#KindEnum) | Instrument kind, &#x60;\&quot;future\&quot;&#x60; or &#x60;\&quot;option\&quot;&#x60; | 
**markPrice** | [**java.math.BigDecimal**](java.math.BigDecimal.md) | Current mark price for position&#39;s instrument | 
**averagePrice** | [**java.math.BigDecimal**](java.math.BigDecimal.md) | Average price of trades that built this position | 
**settlementPrice** | [**java.math.BigDecimal**](java.math.BigDecimal.md) | Last settlement price for position&#39;s instrument 0 if instrument wasn&#39;t settled yet | 
**indexPrice** | [**java.math.BigDecimal**](java.math.BigDecimal.md) | Current index price | 
**instrumentName** | **kotlin.String** | Unique instrument identifier | 
**sizeCurrency** | [**java.math.BigDecimal**](java.math.BigDecimal.md) | Only for futures, position size in base currency |  [optional]


<a name="DirectionEnum"></a>
## Enum: direction
Name | Value
---- | -----
direction | buy, sell


<a name="KindEnum"></a>
## Enum: kind
Name | Value
---- | -----
kind | future, option



