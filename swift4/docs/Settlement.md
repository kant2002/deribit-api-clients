# Settlement

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**sessionProfitLoss** | **Double** | total value of session profit and losses (in base currency) | 
**markPrice** | **Double** | mark price for at the settlement time (in quote currency; settlement and delivery only) | [optional] 
**funding** | **Double** | funding (in base currency ; settlement for perpetual product only) | 
**socialized** | **Double** | the amount of the socialized losses (in base currency; bankruptcy only) | [optional] 
**sessionBankrupcy** | **Double** | value of session bankrupcy (in base currency; bankruptcy only) | [optional] 
**timestamp** | **Int** | The timestamp (seconds since the Unix epoch, with millisecond precision) | 
**profitLoss** | **Double** | profit and loss (in base currency; settlement and delivery only) | [optional] 
**funded** | **Double** | funded amount (bankruptcy only) | [optional] 
**indexPrice** | **Double** | underlying index price at time of event (in quote currency; settlement and delivery only) | 
**sessionTax** | **Double** | total amount of paid taxes/fees (in base currency; bankruptcy only) | [optional] 
**sessionTaxRate** | **Double** | rate of paid texes/fees (in base currency; bankruptcy only) | [optional] 
**instrumentName** | **String** | instrument name (settlement and delivery only) | 
**position** | **Double** | position size (in quote currency; settlement and delivery only) | 
**type** | **String** | The type of settlement. &#x60;settlement&#x60;, &#x60;delivery&#x60; or &#x60;bankruptcy&#x60;. | 

[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)


