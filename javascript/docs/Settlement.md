# DeribitApi.Settlement

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**sessionProfitLoss** | **Number** | total value of session profit and losses (in base currency) | 
**markPrice** | **Number** | mark price for at the settlement time (in quote currency; settlement and delivery only) | [optional] 
**funding** | **Number** | funding (in base currency ; settlement for perpetual product only) | 
**socialized** | **Number** | the amount of the socialized losses (in base currency; bankruptcy only) | [optional] 
**sessionBankrupcy** | **Number** | value of session bankrupcy (in base currency; bankruptcy only) | [optional] 
**timestamp** | **Number** | The timestamp (seconds since the Unix epoch, with millisecond precision) | 
**profitLoss** | **Number** | profit and loss (in base currency; settlement and delivery only) | [optional] 
**funded** | **Number** | funded amount (bankruptcy only) | [optional] 
**indexPrice** | **Number** | underlying index price at time of event (in quote currency; settlement and delivery only) | 
**sessionTax** | **Number** | total amount of paid taxes/fees (in base currency; bankruptcy only) | [optional] 
**sessionTaxRate** | **Number** | rate of paid texes/fees (in base currency; bankruptcy only) | [optional] 
**instrumentName** | **String** | instrument name (settlement and delivery only) | 
**position** | **Number** | position size (in quote currency; settlement and delivery only) | 
**type** | **String** | The type of settlement. &#x60;settlement&#x60;, &#x60;delivery&#x60; or &#x60;bankruptcy&#x60;. | 



## Enum: TypeEnum


* `settlement` (value: `"settlement"`)

* `delivery` (value: `"delivery"`)

* `bankruptcy` (value: `"bankruptcy"`)




