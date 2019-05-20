# BookSummary

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**underlyingIndex** | **String** | Name of the underlying future, or &#x60;&#39;index_price&#39;&#x60; (options only) | [optional] 
**volume** | **Double** | The total 24h traded volume (in base currency) | 
**volumeUsd** | **Double** | Volume in usd (futures only) | [optional] 
**underlyingPrice** | **Double** | underlying price for implied volatility calculations (options only) | [optional] 
**bidPrice** | **Double** | The current best bid price, &#x60;null&#x60; if there aren&#39;t any bids | 
**openInterest** | **Double** | The total amount of outstanding contracts in the corresponding amount units. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH. | 
**quoteCurrency** | **String** | Quote currency | 
**high** | **Double** | Price of the 24h highest trade | 
**estimatedDeliveryPrice** | **Double** | Estimated delivery price, in USD (futures only). For more details, see Documentation &gt; General &gt; Expiration Price | [optional] 
**last** | **Double** | The price of the latest trade, &#x60;null&#x60; if there weren&#39;t any trades | 
**midPrice** | **Double** | The average of the best bid and ask, &#x60;null&#x60; if there aren&#39;t any asks or bids | 
**interestRate** | **Double** | Interest rate used in implied volatility calculations (options only) | [optional] 
**funding8h** | **Double** | Funding 8h (perpetual only) | [optional] 
**markPrice** | **Double** | The current instrument market price | 
**askPrice** | **Double** | The current best ask price, &#x60;null&#x60; if there aren&#39;t any asks | 
**instrumentName** | **String** | Unique instrument identifier | 
**low** | **Double** | Price of the 24h lowest trade, &#x60;null&#x60; if there weren&#39;t any trades | 
**baseCurrency** | **String** | Base currency | [optional] 
**creationTimestamp** | **Int** | The timestamp (seconds since the Unix epoch, with millisecond precision) | 
**currentFunding** | **Double** | Current funding (perpetual only) | [optional] 

[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)


