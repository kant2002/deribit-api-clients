# openapi::Settlement

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**session_profit_loss** | **numeric** | total value of session profit and losses (in base currency) | 
**mark_price** | **numeric** | mark price for at the settlement time (in quote currency; settlement and delivery only) | [optional] 
**funding** | **numeric** | funding (in base currency ; settlement for perpetual product only) | 
**socialized** | **numeric** | the amount of the socialized losses (in base currency; bankruptcy only) | [optional] 
**session_bankrupcy** | **numeric** | value of session bankrupcy (in base currency; bankruptcy only) | [optional] 
**timestamp** | **integer** | The timestamp (seconds since the Unix epoch, with millisecond precision) | 
**profit_loss** | **numeric** | profit and loss (in base currency; settlement and delivery only) | [optional] 
**funded** | **numeric** | funded amount (bankruptcy only) | [optional] 
**index_price** | **numeric** | underlying index price at time of event (in quote currency; settlement and delivery only) | 
**session_tax** | **numeric** | total amount of paid taxes/fees (in base currency; bankruptcy only) | [optional] 
**session_tax_rate** | **numeric** | rate of paid texes/fees (in base currency; bankruptcy only) | [optional] 
**instrument_name** | **character** | instrument name (settlement and delivery only) | 
**position** | **numeric** | position size (in quote currency; settlement and delivery only) | 
**type** | **character** | The type of settlement. &#x60;settlement&#x60;, &#x60;delivery&#x60; or &#x60;bankruptcy&#x60;. | 


