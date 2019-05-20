# OAIBookSummary

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**underlyingIndex** | **NSString*** | Name of the underlying future, or &#x60;&#39;index_price&#39;&#x60; (options only) | [optional] 
**volume** | **NSNumber*** | The total 24h traded volume (in base currency) | 
**volumeUsd** | **NSNumber*** | Volume in usd (futures only) | [optional] 
**underlyingPrice** | **NSNumber*** | underlying price for implied volatility calculations (options only) | [optional] 
**bidPrice** | **NSNumber*** | The current best bid price, &#x60;null&#x60; if there aren&#39;t any bids | 
**openInterest** | **NSNumber*** | The total amount of outstanding contracts in the corresponding amount units. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH. | 
**quoteCurrency** | **NSString*** | Quote currency | 
**high** | **NSNumber*** | Price of the 24h highest trade | 
**estimatedDeliveryPrice** | **NSNumber*** | Estimated delivery price, in USD (futures only). For more details, see Documentation &gt; General &gt; Expiration Price | [optional] 
**last** | **NSNumber*** | The price of the latest trade, &#x60;null&#x60; if there weren&#39;t any trades | 
**midPrice** | **NSNumber*** | The average of the best bid and ask, &#x60;null&#x60; if there aren&#39;t any asks or bids | 
**interestRate** | **NSNumber*** | Interest rate used in implied volatility calculations (options only) | [optional] 
**funding8h** | **NSNumber*** | Funding 8h (perpetual only) | [optional] 
**markPrice** | **NSNumber*** | The current instrument market price | 
**askPrice** | **NSNumber*** | The current best ask price, &#x60;null&#x60; if there aren&#39;t any asks | 
**instrumentName** | **NSString*** | Unique instrument identifier | 
**low** | **NSNumber*** | Price of the 24h lowest trade, &#x60;null&#x60; if there weren&#39;t any trades | 
**baseCurrency** | **NSString*** | Base currency | [optional] 
**creationTimestamp** | **NSNumber*** | The timestamp (seconds since the Unix epoch, with millisecond precision) | 
**currentFunding** | **NSNumber*** | Current funding (perpetual only) | [optional] 

[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)


