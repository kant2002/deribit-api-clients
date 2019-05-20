# OAISettlement

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**sessionProfitLoss** | **NSNumber*** | total value of session profit and losses (in base currency) | 
**markPrice** | **NSNumber*** | mark price for at the settlement time (in quote currency; settlement and delivery only) | [optional] 
**funding** | **NSNumber*** | funding (in base currency ; settlement for perpetual product only) | 
**socialized** | **NSNumber*** | the amount of the socialized losses (in base currency; bankruptcy only) | [optional] 
**sessionBankrupcy** | **NSNumber*** | value of session bankrupcy (in base currency; bankruptcy only) | [optional] 
**timestamp** | **NSNumber*** | The timestamp (seconds since the Unix epoch, with millisecond precision) | 
**profitLoss** | **NSNumber*** | profit and loss (in base currency; settlement and delivery only) | [optional] 
**funded** | **NSNumber*** | funded amount (bankruptcy only) | [optional] 
**indexPrice** | **NSNumber*** | underlying index price at time of event (in quote currency; settlement and delivery only) | 
**sessionTax** | **NSNumber*** | total amount of paid taxes/fees (in base currency; bankruptcy only) | [optional] 
**sessionTaxRate** | **NSNumber*** | rate of paid texes/fees (in base currency; bankruptcy only) | [optional] 
**instrumentName** | **NSString*** | instrument name (settlement and delivery only) | 
**position** | **NSNumber*** | position size (in quote currency; settlement and delivery only) | 
**type** | **NSString*** | The type of settlement. &#x60;settlement&#x60;, &#x60;delivery&#x60; or &#x60;bankruptcy&#x60;. | 

[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)


