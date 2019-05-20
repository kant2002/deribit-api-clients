# PublicTrade

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**Direction** | **string** | Trade direction of the taker | 
**TickDirection** | **int32** | Direction of the \&quot;tick\&quot; (&#x60;0&#x60; &#x3D; Plus Tick, &#x60;1&#x60; &#x3D; Zero-Plus Tick, &#x60;2&#x60; &#x3D; Minus Tick, &#x60;3&#x60; &#x3D; Zero-Minus Tick). | 
**Timestamp** | **int32** | The timestamp of the trade | 
**Price** | **float32** | The price of the trade | 
**TradeSeq** | **int32** | The sequence number of the trade within instrument | 
**TradeId** | **string** | Unique (per currency) trade identifier | 
**Iv** | **float32** | Option implied volatility for the price (Option only) | [optional] 
**IndexPrice** | **float32** | Index Price at the moment of trade | 
**Amount** | **float32** | Trade amount. For perpetual and futures - in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH. | 
**InstrumentName** | **string** | Unique instrument identifier | 

[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)


