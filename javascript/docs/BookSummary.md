# DeribitApi.BookSummary

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**underlyingIndex** | **String** | Name of the underlying future, or &#x60;&#39;index_price&#39;&#x60; (options only) | [optional] 
**volume** | **Number** | The total 24h traded volume (in base currency) | 
**volumeUsd** | **Number** | Volume in usd (futures only) | [optional] 
**underlyingPrice** | **Number** | underlying price for implied volatility calculations (options only) | [optional] 
**bidPrice** | **Number** | The current best bid price, &#x60;null&#x60; if there aren&#39;t any bids | 
**openInterest** | **Number** | The total amount of outstanding contracts in the corresponding amount units. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH. | 
**quoteCurrency** | **String** | Quote currency | 
**high** | **Number** | Price of the 24h highest trade | 
**estimatedDeliveryPrice** | **Number** | Estimated delivery price, in USD (futures only). For more details, see Documentation &gt; General &gt; Expiration Price | [optional] 
**last** | **Number** | The price of the latest trade, &#x60;null&#x60; if there weren&#39;t any trades | 
**midPrice** | **Number** | The average of the best bid and ask, &#x60;null&#x60; if there aren&#39;t any asks or bids | 
**interestRate** | **Number** | Interest rate used in implied volatility calculations (options only) | [optional] 
**funding8h** | **Number** | Funding 8h (perpetual only) | [optional] 
**markPrice** | **Number** | The current instrument market price | 
**askPrice** | **Number** | The current best ask price, &#x60;null&#x60; if there aren&#39;t any asks | 
**instrumentName** | **String** | Unique instrument identifier | 
**low** | **Number** | Price of the 24h lowest trade, &#x60;null&#x60; if there weren&#39;t any trades | 
**baseCurrency** | **String** | Base currency | [optional] 
**creationTimestamp** | **Number** | The timestamp (seconds since the Unix epoch, with millisecond precision) | 
**currentFunding** | **Number** | Current funding (perpetual only) | [optional] 


