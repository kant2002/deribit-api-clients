# Position

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**direction** | **String** | direction, &#x60;buy&#x60; or &#x60;sell&#x60; | 
**averagePriceUsd** | **Double** | Only for options, average price in USD | [optional] 
**estimatedLiquidationPrice** | **Double** | Only for futures, estimated liquidation price | [optional] 
**floatingProfitLoss** | **Double** | Floating profit or loss | 
**floatingProfitLossUsd** | **Double** | Only for options, floating profit or loss in USD | [optional] 
**openOrdersMargin** | **Double** | Open orders margin | 
**totalProfitLoss** | **Double** | Profit or loss from position | 
**realizedProfitLoss** | **Double** | Realized profit or loss | [optional] 
**delta** | **Double** | Delta parameter | 
**initialMargin** | **Double** | Initial margin | 
**size** | **Double** | Position size for futures size in quote currency (e.g. USD), for options size is in base currency (e.g. BTC) | 
**maintenanceMargin** | **Double** | Maintenance margin | 
**kind** | **String** | Instrument kind, &#x60;\&quot;future\&quot;&#x60; or &#x60;\&quot;option\&quot;&#x60; | 
**markPrice** | **Double** | Current mark price for position&#39;s instrument | 
**averagePrice** | **Double** | Average price of trades that built this position | 
**settlementPrice** | **Double** | Last settlement price for position&#39;s instrument 0 if instrument wasn&#39;t settled yet | 
**indexPrice** | **Double** | Current index price | 
**instrumentName** | **String** | Unique instrument identifier | 
**sizeCurrency** | **Double** | Only for futures, position size in base currency | [optional] 

[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)


