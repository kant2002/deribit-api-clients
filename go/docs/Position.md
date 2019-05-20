# Position

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**Direction** | **string** | direction, &#x60;buy&#x60; or &#x60;sell&#x60; | 
**AveragePriceUsd** | **float32** | Only for options, average price in USD | [optional] 
**EstimatedLiquidationPrice** | **float32** | Only for futures, estimated liquidation price | [optional] 
**FloatingProfitLoss** | **float32** | Floating profit or loss | 
**FloatingProfitLossUsd** | **float32** | Only for options, floating profit or loss in USD | [optional] 
**OpenOrdersMargin** | **float32** | Open orders margin | 
**TotalProfitLoss** | **float32** | Profit or loss from position | 
**RealizedProfitLoss** | **float32** | Realized profit or loss | [optional] 
**Delta** | **float32** | Delta parameter | 
**InitialMargin** | **float32** | Initial margin | 
**Size** | **float32** | Position size for futures size in quote currency (e.g. USD), for options size is in base currency (e.g. BTC) | 
**MaintenanceMargin** | **float32** | Maintenance margin | 
**Kind** | **string** | Instrument kind, &#x60;\&quot;future\&quot;&#x60; or &#x60;\&quot;option\&quot;&#x60; | 
**MarkPrice** | **float32** | Current mark price for position&#39;s instrument | 
**AveragePrice** | **float32** | Average price of trades that built this position | 
**SettlementPrice** | **float32** | Last settlement price for position&#39;s instrument 0 if instrument wasn&#39;t settled yet | 
**IndexPrice** | **float32** | Current index price | 
**InstrumentName** | **string** | Unique instrument identifier | 
**SizeCurrency** | **float32** | Only for futures, position size in base currency | [optional] 

[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)


