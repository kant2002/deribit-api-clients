
# BookSummary

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**underlyingIndex** | **kotlin.String** | Name of the underlying future, or &#x60;&#39;index_price&#39;&#x60; (options only) |  [optional]
**volume** | [**java.math.BigDecimal**](java.math.BigDecimal.md) | The total 24h traded volume (in base currency) | 
**volumeUsd** | [**java.math.BigDecimal**](java.math.BigDecimal.md) | Volume in usd (futures only) |  [optional]
**underlyingPrice** | [**java.math.BigDecimal**](java.math.BigDecimal.md) | underlying price for implied volatility calculations (options only) |  [optional]
**bidPrice** | [**java.math.BigDecimal**](java.math.BigDecimal.md) | The current best bid price, &#x60;null&#x60; if there aren&#39;t any bids | 
**openInterest** | [**java.math.BigDecimal**](java.math.BigDecimal.md) | The total amount of outstanding contracts in the corresponding amount units. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH. | 
**quoteCurrency** | **kotlin.String** | Quote currency | 
**high** | [**java.math.BigDecimal**](java.math.BigDecimal.md) | Price of the 24h highest trade | 
**estimatedDeliveryPrice** | [**java.math.BigDecimal**](java.math.BigDecimal.md) | Estimated delivery price, in USD (futures only). For more details, see Documentation &gt; General &gt; Expiration Price |  [optional]
**last** | [**java.math.BigDecimal**](java.math.BigDecimal.md) | The price of the latest trade, &#x60;null&#x60; if there weren&#39;t any trades | 
**midPrice** | [**java.math.BigDecimal**](java.math.BigDecimal.md) | The average of the best bid and ask, &#x60;null&#x60; if there aren&#39;t any asks or bids | 
**interestRate** | [**java.math.BigDecimal**](java.math.BigDecimal.md) | Interest rate used in implied volatility calculations (options only) |  [optional]
**funding8h** | [**java.math.BigDecimal**](java.math.BigDecimal.md) | Funding 8h (perpetual only) |  [optional]
**markPrice** | [**java.math.BigDecimal**](java.math.BigDecimal.md) | The current instrument market price | 
**askPrice** | [**java.math.BigDecimal**](java.math.BigDecimal.md) | The current best ask price, &#x60;null&#x60; if there aren&#39;t any asks | 
**instrumentName** | **kotlin.String** | Unique instrument identifier | 
**low** | [**java.math.BigDecimal**](java.math.BigDecimal.md) | Price of the 24h lowest trade, &#x60;null&#x60; if there weren&#39;t any trades | 
**baseCurrency** | **kotlin.String** | Base currency |  [optional]
**creationTimestamp** | **kotlin.Int** | The timestamp (seconds since the Unix epoch, with millisecond precision) | 
**currentFunding** | [**java.math.BigDecimal**](java.math.BigDecimal.md) | Current funding (perpetual only) |  [optional]



