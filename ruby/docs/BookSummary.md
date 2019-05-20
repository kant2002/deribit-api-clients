# OpenapiClient::BookSummary

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**underlying_index** | **String** | Name of the underlying future, or &#x60;&#39;index_price&#39;&#x60; (options only) | [optional] 
**volume** | **Float** | The total 24h traded volume (in base currency) | 
**volume_usd** | **Float** | Volume in usd (futures only) | [optional] 
**underlying_price** | **Float** | underlying price for implied volatility calculations (options only) | [optional] 
**bid_price** | **Float** | The current best bid price, &#x60;null&#x60; if there aren&#39;t any bids | 
**open_interest** | **Float** | The total amount of outstanding contracts in the corresponding amount units. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH. | 
**quote_currency** | **String** | Quote currency | 
**high** | **Float** | Price of the 24h highest trade | 
**estimated_delivery_price** | **Float** | Estimated delivery price, in USD (futures only). For more details, see Documentation &gt; General &gt; Expiration Price | [optional] 
**last** | **Float** | The price of the latest trade, &#x60;null&#x60; if there weren&#39;t any trades | 
**mid_price** | **Float** | The average of the best bid and ask, &#x60;null&#x60; if there aren&#39;t any asks or bids | 
**interest_rate** | **Float** | Interest rate used in implied volatility calculations (options only) | [optional] 
**funding_8h** | **Float** | Funding 8h (perpetual only) | [optional] 
**mark_price** | **Float** | The current instrument market price | 
**ask_price** | **Float** | The current best ask price, &#x60;null&#x60; if there aren&#39;t any asks | 
**instrument_name** | **String** | Unique instrument identifier | 
**low** | **Float** | Price of the 24h lowest trade, &#x60;null&#x60; if there weren&#39;t any trades | 
**base_currency** | **String** | Base currency | [optional] 
**creation_timestamp** | **Integer** | The timestamp (seconds since the Unix epoch, with millisecond precision) | 
**current_funding** | **Float** | Current funding (perpetual only) | [optional] 

## Code Sample

```ruby
require 'OpenapiClient'

instance = OpenapiClient::BookSummary.new(underlying_index: index_price,
                                 volume: 223,
                                 volume_usd: 102,
                                 underlying_price: 6745.34,
                                 bid_price: 7022.89,
                                 open_interest: 0.5,
                                 quote_currency: USD,
                                 high: 7022.89,
                                 estimated_delivery_price: 10029.5,
                                 last: 7022.89,
                                 mid_price: 7022.89,
                                 interest_rate: 0,
                                 funding_8h: null,
                                 mark_price: 7022.89,
                                 ask_price: 7022.89,
                                 instrument_name: BTC-PERPETUAL,
                                 low: 7022.89,
                                 base_currency: ETH,
                                 creation_timestamp: 1536569522277,
                                 current_funding: null)
```


