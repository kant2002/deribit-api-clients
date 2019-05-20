# OpenapiClient::Instrument

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**quote_currency** | **String** | The currency in which the instrument prices are quoted. | 
**kind** | **String** | Instrument kind, &#x60;\&quot;future\&quot;&#x60; or &#x60;\&quot;option\&quot;&#x60; | 
**tick_size** | **Float** | specifies minimal price change and, as follows, the number of decimal places for instrument prices | 
**contract_size** | **Integer** | Contract size for instrument | 
**is_active** | **Boolean** | Indicates if the instrument can currently be traded. | 
**option_type** | **String** | The option type (only for options) | [optional] 
**min_trade_amount** | **Float** | Minimum amount for trading. For perpetual and futures - in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH. | 
**instrument_name** | **String** | Unique instrument identifier | 
**settlement_period** | **String** | The settlement period. | 
**strike** | **Float** | The strike value. (only for options) | [optional] 
**base_currency** | **String** | The underlying currency being traded. | 
**creation_timestamp** | **Integer** | The time when the instrument was first created (milliseconds) | 
**expiration_timestamp** | **Integer** | The time when the instrument will expire (milliseconds) | 

## Code Sample

```ruby
require 'OpenapiClient'

instance = OpenapiClient::Instrument.new(quote_currency: null,
                                 kind: null,
                                 tick_size: 0.00010,
                                 contract_size: 1,
                                 is_active: null,
                                 option_type: null,
                                 min_trade_amount: 0.1,
                                 instrument_name: BTC-PERPETUAL,
                                 settlement_period: null,
                                 strike: null,
                                 base_currency: null,
                                 creation_timestamp: 1536569522277,
                                 expiration_timestamp: null)
```


