
# Org.OpenAPITools.Model.Settlement

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**SessionProfitLoss** | **decimal?** | total value of session profit and losses (in base currency) | 
**MarkPrice** | **decimal?** | mark price for at the settlement time (in quote currency; settlement and delivery only) | [optional] 
**Funding** | **decimal?** | funding (in base currency ; settlement for perpetual product only) | 
**Socialized** | **decimal?** | the amount of the socialized losses (in base currency; bankruptcy only) | [optional] 
**SessionBankrupcy** | **decimal?** | value of session bankrupcy (in base currency; bankruptcy only) | [optional] 
**Timestamp** | **int?** | The timestamp (seconds since the Unix epoch, with millisecond precision) | 
**ProfitLoss** | **decimal?** | profit and loss (in base currency; settlement and delivery only) | [optional] 
**Funded** | **decimal?** | funded amount (bankruptcy only) | [optional] 
**IndexPrice** | **decimal?** | underlying index price at time of event (in quote currency; settlement and delivery only) | 
**SessionTax** | **decimal?** | total amount of paid taxes/fees (in base currency; bankruptcy only) | [optional] 
**SessionTaxRate** | **decimal?** | rate of paid texes/fees (in base currency; bankruptcy only) | [optional] 
**InstrumentName** | **string** | instrument name (settlement and delivery only) | 
**Position** | **decimal?** | position size (in quote currency; settlement and delivery only) | 
**Type** | **string** | The type of settlement. &#x60;settlement&#x60;, &#x60;delivery&#x60; or &#x60;bankruptcy&#x60;. | 

[[Back to Model list]](../README.md#documentation-for-models)
[[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to README]](../README.md)

