# OpenapiClient::Position

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**direction** | **String** | direction, &#x60;buy&#x60; or &#x60;sell&#x60; | 
**average_price_usd** | **Float** | Only for options, average price in USD | [optional] 
**estimated_liquidation_price** | **Float** | Only for futures, estimated liquidation price | [optional] 
**floating_profit_loss** | **Float** | Floating profit or loss | 
**floating_profit_loss_usd** | **Float** | Only for options, floating profit or loss in USD | [optional] 
**open_orders_margin** | **Float** | Open orders margin | 
**total_profit_loss** | **Float** | Profit or loss from position | 
**realized_profit_loss** | **Float** | Realized profit or loss | [optional] 
**delta** | **Float** | Delta parameter | 
**initial_margin** | **Float** | Initial margin | 
**size** | **Float** | Position size for futures size in quote currency (e.g. USD), for options size is in base currency (e.g. BTC) | 
**maintenance_margin** | **Float** | Maintenance margin | 
**kind** | **String** | Instrument kind, &#x60;\&quot;future\&quot;&#x60; or &#x60;\&quot;option\&quot;&#x60; | 
**mark_price** | **Float** | Current mark price for position&#39;s instrument | 
**average_price** | **Float** | Average price of trades that built this position | 
**settlement_price** | **Float** | Last settlement price for position&#39;s instrument 0 if instrument wasn&#39;t settled yet | 
**index_price** | **Float** | Current index price | 
**instrument_name** | **String** | Unique instrument identifier | 
**size_currency** | **Float** | Only for futures, position size in base currency | [optional] 

## Code Sample

```ruby
require 'OpenapiClient'

instance = OpenapiClient::Position.new(direction: null,
                                 average_price_usd: null,
                                 estimated_liquidation_price: null,
                                 floating_profit_loss: null,
                                 floating_profit_loss_usd: null,
                                 open_orders_margin: null,
                                 total_profit_loss: null,
                                 realized_profit_loss: null,
                                 delta: null,
                                 initial_margin: null,
                                 size: null,
                                 maintenance_margin: null,
                                 kind: null,
                                 mark_price: null,
                                 average_price: null,
                                 settlement_price: null,
                                 index_price: null,
                                 instrument_name: BTC-PERPETUAL,
                                 size_currency: null)
```


