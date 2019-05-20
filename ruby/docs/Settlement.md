# OpenapiClient::Settlement

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**session_profit_loss** | **Float** | total value of session profit and losses (in base currency) | 
**mark_price** | **Float** | mark price for at the settlement time (in quote currency; settlement and delivery only) | [optional] 
**funding** | **Float** | funding (in base currency ; settlement for perpetual product only) | 
**socialized** | **Float** | the amount of the socialized losses (in base currency; bankruptcy only) | [optional] 
**session_bankrupcy** | **Float** | value of session bankrupcy (in base currency; bankruptcy only) | [optional] 
**timestamp** | **Integer** | The timestamp (seconds since the Unix epoch, with millisecond precision) | 
**profit_loss** | **Float** | profit and loss (in base currency; settlement and delivery only) | [optional] 
**funded** | **Float** | funded amount (bankruptcy only) | [optional] 
**index_price** | **Float** | underlying index price at time of event (in quote currency; settlement and delivery only) | 
**session_tax** | **Float** | total amount of paid taxes/fees (in base currency; bankruptcy only) | [optional] 
**session_tax_rate** | **Float** | rate of paid texes/fees (in base currency; bankruptcy only) | [optional] 
**instrument_name** | **String** | instrument name (settlement and delivery only) | 
**position** | **Float** | position size (in quote currency; settlement and delivery only) | 
**type** | **String** | The type of settlement. &#x60;settlement&#x60;, &#x60;delivery&#x60; or &#x60;bankruptcy&#x60;. | 

## Code Sample

```ruby
require 'OpenapiClient'

instance = OpenapiClient::Settlement.new(session_profit_loss: 0.001160788,
                                 mark_price: 11000,
                                 funding: -0.000002511,
                                 socialized: -0.001160788,
                                 session_bankrupcy: 0.001160788,
                                 timestamp: 1536569522277,
                                 profit_loss: 0,
                                 funded: 0,
                                 index_price: 11008.37,
                                 session_tax: -0.001160788,
                                 session_tax_rate: 0.000103333,
                                 instrument_name: BTC-30MAR18,
                                 position: 1000,
                                 type: null)
```


