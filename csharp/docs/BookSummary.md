
# Org.OpenAPITools.Model.BookSummary

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**UnderlyingIndex** | **string** | Name of the underlying future, or &#x60;&#39;index_price&#39;&#x60; (options only) | [optional] 
**Volume** | **decimal?** | The total 24h traded volume (in base currency) | 
**VolumeUsd** | **decimal?** | Volume in usd (futures only) | [optional] 
**UnderlyingPrice** | **decimal?** | underlying price for implied volatility calculations (options only) | [optional] 
**BidPrice** | **decimal?** | The current best bid price, &#x60;null&#x60; if there aren&#39;t any bids | 
**OpenInterest** | **decimal?** | The total amount of outstanding contracts in the corresponding amount units. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH. | 
**QuoteCurrency** | **string** | Quote currency | 
**High** | **decimal?** | Price of the 24h highest trade | 
**EstimatedDeliveryPrice** | **decimal?** | Estimated delivery price, in USD (futures only). For more details, see Documentation &gt; General &gt; Expiration Price | [optional] 
**Last** | **decimal?** | The price of the latest trade, &#x60;null&#x60; if there weren&#39;t any trades | 
**MidPrice** | **decimal?** | The average of the best bid and ask, &#x60;null&#x60; if there aren&#39;t any asks or bids | 
**InterestRate** | **decimal?** | Interest rate used in implied volatility calculations (options only) | [optional] 
**Funding8h** | **decimal?** | Funding 8h (perpetual only) | [optional] 
**MarkPrice** | **decimal?** | The current instrument market price | 
**AskPrice** | **decimal?** | The current best ask price, &#x60;null&#x60; if there aren&#39;t any asks | 
**InstrumentName** | **string** | Unique instrument identifier | 
**Low** | **decimal?** | Price of the 24h lowest trade, &#x60;null&#x60; if there weren&#39;t any trades | 
**BaseCurrency** | **string** | Base currency | [optional] 
**CreationTimestamp** | **int?** | The timestamp (seconds since the Unix epoch, with millisecond precision) | 
**CurrentFunding** | **decimal?** | Current funding (perpetual only) | [optional] 

[[Back to Model list]](../README.md#documentation-for-models)
[[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to README]](../README.md)

