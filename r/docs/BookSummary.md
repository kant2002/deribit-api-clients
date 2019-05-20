# openapi::BookSummary

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**underlying_index** | **character** | Name of the underlying future, or &#x60;&#39;index_price&#39;&#x60; (options only) | [optional] 
**volume** | **numeric** | The total 24h traded volume (in base currency) | 
**volume_usd** | **numeric** | Volume in usd (futures only) | [optional] 
**underlying_price** | **numeric** | underlying price for implied volatility calculations (options only) | [optional] 
**bid_price** | **numeric** | The current best bid price, &#x60;null&#x60; if there aren&#39;t any bids | 
**open_interest** | **numeric** | The total amount of outstanding contracts in the corresponding amount units. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH. | 
**quote_currency** | **character** | Quote currency | 
**high** | **numeric** | Price of the 24h highest trade | 
**estimated_delivery_price** | **numeric** | Estimated delivery price, in USD (futures only). For more details, see Documentation &gt; General &gt; Expiration Price | [optional] 
**last** | **numeric** | The price of the latest trade, &#x60;null&#x60; if there weren&#39;t any trades | 
**mid_price** | **numeric** | The average of the best bid and ask, &#x60;null&#x60; if there aren&#39;t any asks or bids | 
**interest_rate** | **numeric** | Interest rate used in implied volatility calculations (options only) | [optional] 
**funding_8h** | **numeric** | Funding 8h (perpetual only) | [optional] 
**mark_price** | **numeric** | The current instrument market price | 
**ask_price** | **numeric** | The current best ask price, &#x60;null&#x60; if there aren&#39;t any asks | 
**instrument_name** | **character** | Unique instrument identifier | 
**low** | **numeric** | Price of the 24h lowest trade, &#x60;null&#x60; if there weren&#39;t any trades | 
**base_currency** | **character** | Base currency | [optional] 
**creation_timestamp** | **integer** | The timestamp (seconds since the Unix epoch, with millisecond precision) | 
**current_funding** | **numeric** | Current funding (perpetual only) | [optional] 


