# OpenapiClient::PublicTrade

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**direction** | **String** | Trade direction of the taker | 
**tick_direction** | **Integer** | Direction of the \&quot;tick\&quot; (&#x60;0&#x60; &#x3D; Plus Tick, &#x60;1&#x60; &#x3D; Zero-Plus Tick, &#x60;2&#x60; &#x3D; Minus Tick, &#x60;3&#x60; &#x3D; Zero-Minus Tick). | 
**timestamp** | **Integer** | The timestamp of the trade | 
**price** | **Float** | The price of the trade | 
**trade_seq** | **Integer** | The sequence number of the trade within instrument | 
**trade_id** | **String** | Unique (per currency) trade identifier | 
**iv** | **Float** | Option implied volatility for the price (Option only) | [optional] 
**index_price** | **Float** | Index Price at the moment of trade | 
**amount** | **Float** | Trade amount. For perpetual and futures - in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH. | 
**instrument_name** | **String** | Unique instrument identifier | 

## Code Sample

```ruby
require 'OpenapiClient'

instance = OpenapiClient::PublicTrade.new(direction: null,
                                 tick_direction: null,
                                 timestamp: 1517329113791,
                                 price: null,
                                 trade_seq: null,
                                 trade_id: null,
                                 iv: null,
                                 index_price: null,
                                 amount: null,
                                 instrument_name: BTC-PERPETUAL)
```


