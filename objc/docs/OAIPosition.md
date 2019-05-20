# OAIPosition

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**direction** | **NSString*** | direction, &#x60;buy&#x60; or &#x60;sell&#x60; | 
**averagePriceUsd** | **NSNumber*** | Only for options, average price in USD | [optional] 
**estimatedLiquidationPrice** | **NSNumber*** | Only for futures, estimated liquidation price | [optional] 
**floatingProfitLoss** | **NSNumber*** | Floating profit or loss | 
**floatingProfitLossUsd** | **NSNumber*** | Only for options, floating profit or loss in USD | [optional] 
**openOrdersMargin** | **NSNumber*** | Open orders margin | 
**totalProfitLoss** | **NSNumber*** | Profit or loss from position | 
**realizedProfitLoss** | **NSNumber*** | Realized profit or loss | [optional] 
**delta** | **NSNumber*** | Delta parameter | 
**initialMargin** | **NSNumber*** | Initial margin | 
**size** | **NSNumber*** | Position size for futures size in quote currency (e.g. USD), for options size is in base currency (e.g. BTC) | 
**maintenanceMargin** | **NSNumber*** | Maintenance margin | 
**kind** | **NSString*** | Instrument kind, &#x60;\&quot;future\&quot;&#x60; or &#x60;\&quot;option\&quot;&#x60; | 
**markPrice** | **NSNumber*** | Current mark price for position&#39;s instrument | 
**averagePrice** | **NSNumber*** | Average price of trades that built this position | 
**settlementPrice** | **NSNumber*** | Last settlement price for position&#39;s instrument 0 if instrument wasn&#39;t settled yet | 
**indexPrice** | **NSNumber*** | Current index price | 
**instrumentName** | **NSString*** | Unique instrument identifier | 
**sizeCurrency** | **NSNumber*** | Only for futures, position size in base currency | [optional] 

[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)


