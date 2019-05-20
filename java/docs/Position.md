

# Position

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**direction** | [**DirectionEnum**](#DirectionEnum) | direction, &#x60;buy&#x60; or &#x60;sell&#x60; | 
**averagePriceUsd** | [**BigDecimal**](BigDecimal.md) | Only for options, average price in USD |  [optional]
**estimatedLiquidationPrice** | [**BigDecimal**](BigDecimal.md) | Only for futures, estimated liquidation price |  [optional]
**floatingProfitLoss** | [**BigDecimal**](BigDecimal.md) | Floating profit or loss | 
**floatingProfitLossUsd** | [**BigDecimal**](BigDecimal.md) | Only for options, floating profit or loss in USD |  [optional]
**openOrdersMargin** | [**BigDecimal**](BigDecimal.md) | Open orders margin | 
**totalProfitLoss** | [**BigDecimal**](BigDecimal.md) | Profit or loss from position | 
**realizedProfitLoss** | [**BigDecimal**](BigDecimal.md) | Realized profit or loss |  [optional]
**delta** | [**BigDecimal**](BigDecimal.md) | Delta parameter | 
**initialMargin** | [**BigDecimal**](BigDecimal.md) | Initial margin | 
**size** | [**BigDecimal**](BigDecimal.md) | Position size for futures size in quote currency (e.g. USD), for options size is in base currency (e.g. BTC) | 
**maintenanceMargin** | [**BigDecimal**](BigDecimal.md) | Maintenance margin | 
**kind** | [**KindEnum**](#KindEnum) | Instrument kind, &#x60;\&quot;future\&quot;&#x60; or &#x60;\&quot;option\&quot;&#x60; | 
**markPrice** | [**BigDecimal**](BigDecimal.md) | Current mark price for position&#39;s instrument | 
**averagePrice** | [**BigDecimal**](BigDecimal.md) | Average price of trades that built this position | 
**settlementPrice** | [**BigDecimal**](BigDecimal.md) | Last settlement price for position&#39;s instrument 0 if instrument wasn&#39;t settled yet | 
**indexPrice** | [**BigDecimal**](BigDecimal.md) | Current index price | 
**instrumentName** | **String** | Unique instrument identifier | 
**sizeCurrency** | [**BigDecimal**](BigDecimal.md) | Only for futures, position size in base currency |  [optional]



## Enum: DirectionEnum

Name | Value
---- | -----
BUY | &quot;buy&quot;
SELL | &quot;sell&quot;



## Enum: KindEnum

Name | Value
---- | -----
FUTURE | &quot;future&quot;
OPTION | &quot;option&quot;



