# OAIPublicTrade

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**direction** | **NSString*** | Trade direction of the taker | 
**tickDirection** | **NSNumber*** | Direction of the \&quot;tick\&quot; (&#x60;0&#x60; &#x3D; Plus Tick, &#x60;1&#x60; &#x3D; Zero-Plus Tick, &#x60;2&#x60; &#x3D; Minus Tick, &#x60;3&#x60; &#x3D; Zero-Minus Tick). | 
**timestamp** | **NSNumber*** | The timestamp of the trade | 
**price** | **NSNumber*** | The price of the trade | 
**tradeSeq** | **NSNumber*** | The sequence number of the trade within instrument | 
**tradeId** | **NSString*** | Unique (per currency) trade identifier | 
**iv** | **NSNumber*** | Option implied volatility for the price (Option only) | [optional] 
**indexPrice** | **NSNumber*** | Index Price at the moment of trade | 
**amount** | **NSNumber*** | Trade amount. For perpetual and futures - in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH. | 
**instrumentName** | **NSString*** | Unique instrument identifier | 

[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)


