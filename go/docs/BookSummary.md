# BookSummary

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**UnderlyingIndex** | **string** | Name of the underlying future, or &#x60;&#39;index_price&#39;&#x60; (options only) | [optional] 
**Volume** | **float32** | The total 24h traded volume (in base currency) | 
**VolumeUsd** | **float32** | Volume in usd (futures only) | [optional] 
**UnderlyingPrice** | **float32** | underlying price for implied volatility calculations (options only) | [optional] 
**BidPrice** | **float32** | The current best bid price, &#x60;null&#x60; if there aren&#39;t any bids | 
**OpenInterest** | **float32** | The total amount of outstanding contracts in the corresponding amount units. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH. | 
**QuoteCurrency** | **string** | Quote currency | 
**High** | **float32** | Price of the 24h highest trade | 
**EstimatedDeliveryPrice** | **float32** | Estimated delivery price, in USD (futures only). For more details, see Documentation &gt; General &gt; Expiration Price | [optional] 
**Last** | **float32** | The price of the latest trade, &#x60;null&#x60; if there weren&#39;t any trades | 
**MidPrice** | **float32** | The average of the best bid and ask, &#x60;null&#x60; if there aren&#39;t any asks or bids | 
**InterestRate** | **float32** | Interest rate used in implied volatility calculations (options only) | [optional] 
**Funding8h** | **float32** | Funding 8h (perpetual only) | [optional] 
**MarkPrice** | **float32** | The current instrument market price | 
**AskPrice** | **float32** | The current best ask price, &#x60;null&#x60; if there aren&#39;t any asks | 
**InstrumentName** | **string** | Unique instrument identifier | 
**Low** | **float32** | Price of the 24h lowest trade, &#x60;null&#x60; if there weren&#39;t any trades | 
**BaseCurrency** | **string** | Base currency | [optional] 
**CreationTimestamp** | **int32** | The timestamp (seconds since the Unix epoch, with millisecond precision) | 
**CurrentFunding** | **float32** | Current funding (perpetual only) | [optional] 

[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)


