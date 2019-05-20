

# BookSummary

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**underlyingIndex** | **String** | Name of the underlying future, or &#x60;&#39;index_price&#39;&#x60; (options only) |  [optional]
**volume** | [**BigDecimal**](BigDecimal.md) | The total 24h traded volume (in base currency) | 
**volumeUsd** | [**BigDecimal**](BigDecimal.md) | Volume in usd (futures only) |  [optional]
**underlyingPrice** | [**BigDecimal**](BigDecimal.md) | underlying price for implied volatility calculations (options only) |  [optional]
**bidPrice** | [**BigDecimal**](BigDecimal.md) | The current best bid price, &#x60;null&#x60; if there aren&#39;t any bids | 
**openInterest** | [**BigDecimal**](BigDecimal.md) | The total amount of outstanding contracts in the corresponding amount units. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH. | 
**quoteCurrency** | **String** | Quote currency | 
**high** | [**BigDecimal**](BigDecimal.md) | Price of the 24h highest trade | 
**estimatedDeliveryPrice** | [**BigDecimal**](BigDecimal.md) | Estimated delivery price, in USD (futures only). For more details, see Documentation &gt; General &gt; Expiration Price |  [optional]
**last** | [**BigDecimal**](BigDecimal.md) | The price of the latest trade, &#x60;null&#x60; if there weren&#39;t any trades | 
**midPrice** | [**BigDecimal**](BigDecimal.md) | The average of the best bid and ask, &#x60;null&#x60; if there aren&#39;t any asks or bids | 
**interestRate** | [**BigDecimal**](BigDecimal.md) | Interest rate used in implied volatility calculations (options only) |  [optional]
**funding8h** | [**BigDecimal**](BigDecimal.md) | Funding 8h (perpetual only) |  [optional]
**markPrice** | [**BigDecimal**](BigDecimal.md) | The current instrument market price | 
**askPrice** | [**BigDecimal**](BigDecimal.md) | The current best ask price, &#x60;null&#x60; if there aren&#39;t any asks | 
**instrumentName** | **String** | Unique instrument identifier | 
**low** | [**BigDecimal**](BigDecimal.md) | Price of the 24h lowest trade, &#x60;null&#x60; if there weren&#39;t any trades | 
**baseCurrency** | **String** | Base currency |  [optional]
**creationTimestamp** | **Integer** | The timestamp (seconds since the Unix epoch, with millisecond precision) | 
**currentFunding** | [**BigDecimal**](BigDecimal.md) | Current funding (perpetual only) |  [optional]




