# Settlement

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**SessionProfitLoss** | **float32** | total value of session profit and losses (in base currency) | 
**MarkPrice** | **float32** | mark price for at the settlement time (in quote currency; settlement and delivery only) | [optional] 
**Funding** | **float32** | funding (in base currency ; settlement for perpetual product only) | 
**Socialized** | **float32** | the amount of the socialized losses (in base currency; bankruptcy only) | [optional] 
**SessionBankrupcy** | **float32** | value of session bankrupcy (in base currency; bankruptcy only) | [optional] 
**Timestamp** | **int32** | The timestamp (seconds since the Unix epoch, with millisecond precision) | 
**ProfitLoss** | **float32** | profit and loss (in base currency; settlement and delivery only) | [optional] 
**Funded** | **float32** | funded amount (bankruptcy only) | [optional] 
**IndexPrice** | **float32** | underlying index price at time of event (in quote currency; settlement and delivery only) | 
**SessionTax** | **float32** | total amount of paid taxes/fees (in base currency; bankruptcy only) | [optional] 
**SessionTaxRate** | **float32** | rate of paid texes/fees (in base currency; bankruptcy only) | [optional] 
**InstrumentName** | **string** | instrument name (settlement and delivery only) | 
**Position** | **float32** | position size (in quote currency; settlement and delivery only) | 
**Type** | **string** | The type of settlement. &#x60;settlement&#x60;, &#x60;delivery&#x60; or &#x60;bankruptcy&#x60;. | 

[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)


