

# TradesVolumes

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**callsVolume** | [**BigDecimal**](BigDecimal.md) | Total 24h trade volume for call options. This is expressed in the base currency, e.g. BTC for &#x60;btc_usd&#x60; | 
**putsVolume** | [**BigDecimal**](BigDecimal.md) | Total 24h trade volume for put options. This is expressed in the base currency, e.g. BTC for &#x60;btc_usd&#x60; | 
**currencyPair** | [**CurrencyPairEnum**](#CurrencyPairEnum) | Currency pair: &#x60;\&quot;btc_usd\&quot;&#x60; or &#x60;\&quot;eth_usd\&quot;&#x60; | 
**futuresVolume** | [**BigDecimal**](BigDecimal.md) | Total 24h trade volume for futures. This is expressed in the base currency, e.g. BTC for &#x60;btc_usd&#x60; | 



## Enum: CurrencyPairEnum

Name | Value
---- | -----
BTC_USD | &quot;btc_usd&quot;
ETH_USD | &quot;eth_usd&quot;



