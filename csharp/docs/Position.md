
# Org.OpenAPITools.Model.Position

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**Direction** | **string** | direction, &#x60;buy&#x60; or &#x60;sell&#x60; | 
**AveragePriceUsd** | **decimal?** | Only for options, average price in USD | [optional] 
**EstimatedLiquidationPrice** | **decimal?** | Only for futures, estimated liquidation price | [optional] 
**FloatingProfitLoss** | **decimal?** | Floating profit or loss | 
**FloatingProfitLossUsd** | **decimal?** | Only for options, floating profit or loss in USD | [optional] 
**OpenOrdersMargin** | **decimal?** | Open orders margin | 
**TotalProfitLoss** | **decimal?** | Profit or loss from position | 
**RealizedProfitLoss** | **decimal?** | Realized profit or loss | [optional] 
**Delta** | **decimal?** | Delta parameter | 
**InitialMargin** | **decimal?** | Initial margin | 
**Size** | **decimal?** | Position size for futures size in quote currency (e.g. USD), for options size is in base currency (e.g. BTC) | 
**MaintenanceMargin** | **decimal?** | Maintenance margin | 
**Kind** | **string** | Instrument kind, &#x60;\&quot;future\&quot;&#x60; or &#x60;\&quot;option\&quot;&#x60; | 
**MarkPrice** | **decimal?** | Current mark price for position&#39;s instrument | 
**AveragePrice** | **decimal?** | Average price of trades that built this position | 
**SettlementPrice** | **decimal?** | Last settlement price for position&#39;s instrument 0 if instrument wasn&#39;t settled yet | 
**IndexPrice** | **decimal?** | Current index price | 
**InstrumentName** | **string** | Unique instrument identifier | 
**SizeCurrency** | **decimal?** | Only for futures, position size in base currency | [optional] 

[[Back to Model list]](../README.md#documentation-for-models)
[[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to README]](../README.md)

