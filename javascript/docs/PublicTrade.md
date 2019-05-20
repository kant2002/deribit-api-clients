# DeribitApi.PublicTrade

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**direction** | **String** | Trade direction of the taker | 
**tickDirection** | **Number** | Direction of the \&quot;tick\&quot; (&#x60;0&#x60; &#x3D; Plus Tick, &#x60;1&#x60; &#x3D; Zero-Plus Tick, &#x60;2&#x60; &#x3D; Minus Tick, &#x60;3&#x60; &#x3D; Zero-Minus Tick). | 
**timestamp** | **Number** | The timestamp of the trade | 
**price** | **Number** | The price of the trade | 
**tradeSeq** | **Number** | The sequence number of the trade within instrument | 
**tradeId** | **String** | Unique (per currency) trade identifier | 
**iv** | **Number** | Option implied volatility for the price (Option only) | [optional] 
**indexPrice** | **Number** | Index Price at the moment of trade | 
**amount** | **Number** | Trade amount. For perpetual and futures - in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH. | 
**instrumentName** | **String** | Unique instrument identifier | 



## Enum: DirectionEnum


* `buy` (value: `"buy"`)

* `sell` (value: `"sell"`)





## Enum: TickDirectionEnum


* `0` (value: `0`)

* `1` (value: `1`)

* `2` (value: `2`)

* `3` (value: `3`)




