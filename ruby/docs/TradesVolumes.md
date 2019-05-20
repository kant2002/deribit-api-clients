# OpenapiClient::TradesVolumes

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**calls_volume** | **Float** | Total 24h trade volume for call options. This is expressed in the base currency, e.g. BTC for &#x60;btc_usd&#x60; | 
**puts_volume** | **Float** | Total 24h trade volume for put options. This is expressed in the base currency, e.g. BTC for &#x60;btc_usd&#x60; | 
**currency_pair** | **String** | Currency pair: &#x60;\&quot;btc_usd\&quot;&#x60; or &#x60;\&quot;eth_usd\&quot;&#x60; | 
**futures_volume** | **Float** | Total 24h trade volume for futures. This is expressed in the base currency, e.g. BTC for &#x60;btc_usd&#x60; | 

## Code Sample

```ruby
require 'OpenapiClient'

instance = OpenapiClient::TradesVolumes.new(calls_volume: 2.1,
                                 puts_volume: 60.2,
                                 currency_pair: null,
                                 futures_volume: 30.5178)
```


