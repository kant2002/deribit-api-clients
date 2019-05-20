# DeribitApi.Position

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**direction** | **String** | direction, &#x60;buy&#x60; or &#x60;sell&#x60; | 
**averagePriceUsd** | **Number** | Only for options, average price in USD | [optional] 
**estimatedLiquidationPrice** | **Number** | Only for futures, estimated liquidation price | [optional] 
**floatingProfitLoss** | **Number** | Floating profit or loss | 
**floatingProfitLossUsd** | **Number** | Only for options, floating profit or loss in USD | [optional] 
**openOrdersMargin** | **Number** | Open orders margin | 
**totalProfitLoss** | **Number** | Profit or loss from position | 
**realizedProfitLoss** | **Number** | Realized profit or loss | [optional] 
**delta** | **Number** | Delta parameter | 
**initialMargin** | **Number** | Initial margin | 
**size** | **Number** | Position size for futures size in quote currency (e.g. USD), for options size is in base currency (e.g. BTC) | 
**maintenanceMargin** | **Number** | Maintenance margin | 
**kind** | **String** | Instrument kind, &#x60;\&quot;future\&quot;&#x60; or &#x60;\&quot;option\&quot;&#x60; | 
**markPrice** | **Number** | Current mark price for position&#39;s instrument | 
**averagePrice** | **Number** | Average price of trades that built this position | 
**settlementPrice** | **Number** | Last settlement price for position&#39;s instrument 0 if instrument wasn&#39;t settled yet | 
**indexPrice** | **Number** | Current index price | 
**instrumentName** | **String** | Unique instrument identifier | 
**sizeCurrency** | **Number** | Only for futures, position size in base currency | [optional] 



## Enum: DirectionEnum


* `buy` (value: `"buy"`)

* `sell` (value: `"sell"`)





## Enum: KindEnum


* `future` (value: `"future"`)

* `option` (value: `"option"`)




