# openapi::PublicTrade

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**direction** | **character** | Trade direction of the taker | 
**tick_direction** | **integer** | Direction of the \&quot;tick\&quot; (&#x60;0&#x60; &#x3D; Plus Tick, &#x60;1&#x60; &#x3D; Zero-Plus Tick, &#x60;2&#x60; &#x3D; Minus Tick, &#x60;3&#x60; &#x3D; Zero-Minus Tick). | 
**timestamp** | **integer** | The timestamp of the trade | 
**price** | **numeric** | The price of the trade | 
**trade_seq** | **integer** | The sequence number of the trade within instrument | 
**trade_id** | **character** | Unique (per currency) trade identifier | 
**iv** | **numeric** | Option implied volatility for the price (Option only) | [optional] 
**index_price** | **numeric** | Index Price at the moment of trade | 
**amount** | **numeric** | Trade amount. For perpetual and futures - in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH. | 
**instrument_name** | **character** | Unique instrument identifier | 


