# openapi::Position

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**direction** | **character** | direction, &#x60;buy&#x60; or &#x60;sell&#x60; | 
**average_price_usd** | **numeric** | Only for options, average price in USD | [optional] 
**estimated_liquidation_price** | **numeric** | Only for futures, estimated liquidation price | [optional] 
**floating_profit_loss** | **numeric** | Floating profit or loss | 
**floating_profit_loss_usd** | **numeric** | Only for options, floating profit or loss in USD | [optional] 
**open_orders_margin** | **numeric** | Open orders margin | 
**total_profit_loss** | **numeric** | Profit or loss from position | 
**realized_profit_loss** | **numeric** | Realized profit or loss | [optional] 
**delta** | **numeric** | Delta parameter | 
**initial_margin** | **numeric** | Initial margin | 
**size** | **numeric** | Position size for futures size in quote currency (e.g. USD), for options size is in base currency (e.g. BTC) | 
**maintenance_margin** | **numeric** | Maintenance margin | 
**kind** | **character** | Instrument kind, &#x60;\&quot;future\&quot;&#x60; or &#x60;\&quot;option\&quot;&#x60; | 
**mark_price** | **numeric** | Current mark price for position&#39;s instrument | 
**average_price** | **numeric** | Average price of trades that built this position | 
**settlement_price** | **numeric** | Last settlement price for position&#39;s instrument 0 if instrument wasn&#39;t settled yet | 
**index_price** | **numeric** | Current index price | 
**instrument_name** | **character** | Unique instrument identifier | 
**size_currency** | **numeric** | Only for futures, position size in base currency | [optional] 


