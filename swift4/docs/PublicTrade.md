# PublicTrade

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**direction** | **String** | Trade direction of the taker | 
**tickDirection** | **Int** | Direction of the \&quot;tick\&quot; (&#x60;0&#x60; &#x3D; Plus Tick, &#x60;1&#x60; &#x3D; Zero-Plus Tick, &#x60;2&#x60; &#x3D; Minus Tick, &#x60;3&#x60; &#x3D; Zero-Minus Tick). | 
**timestamp** | **Int** | The timestamp of the trade | 
**price** | **Double** | The price of the trade | 
**tradeSeq** | **Int** | The sequence number of the trade within instrument | 
**tradeId** | **String** | Unique (per currency) trade identifier | 
**iv** | **Double** | Option implied volatility for the price (Option only) | [optional] 
**indexPrice** | **Double** | Index Price at the moment of trade | 
**amount** | **Double** | Trade amount. For perpetual and futures - in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH. | 
**instrumentName** | **String** | Unique instrument identifier | 

[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)


