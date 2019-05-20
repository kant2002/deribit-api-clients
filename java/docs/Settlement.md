

# Settlement

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**sessionProfitLoss** | [**BigDecimal**](BigDecimal.md) | total value of session profit and losses (in base currency) | 
**markPrice** | [**BigDecimal**](BigDecimal.md) | mark price for at the settlement time (in quote currency; settlement and delivery only) |  [optional]
**funding** | [**BigDecimal**](BigDecimal.md) | funding (in base currency ; settlement for perpetual product only) | 
**socialized** | [**BigDecimal**](BigDecimal.md) | the amount of the socialized losses (in base currency; bankruptcy only) |  [optional]
**sessionBankrupcy** | [**BigDecimal**](BigDecimal.md) | value of session bankrupcy (in base currency; bankruptcy only) |  [optional]
**timestamp** | **Integer** | The timestamp (seconds since the Unix epoch, with millisecond precision) | 
**profitLoss** | [**BigDecimal**](BigDecimal.md) | profit and loss (in base currency; settlement and delivery only) |  [optional]
**funded** | [**BigDecimal**](BigDecimal.md) | funded amount (bankruptcy only) |  [optional]
**indexPrice** | [**BigDecimal**](BigDecimal.md) | underlying index price at time of event (in quote currency; settlement and delivery only) | 
**sessionTax** | [**BigDecimal**](BigDecimal.md) | total amount of paid taxes/fees (in base currency; bankruptcy only) |  [optional]
**sessionTaxRate** | [**BigDecimal**](BigDecimal.md) | rate of paid texes/fees (in base currency; bankruptcy only) |  [optional]
**instrumentName** | **String** | instrument name (settlement and delivery only) | 
**position** | [**BigDecimal**](BigDecimal.md) | position size (in quote currency; settlement and delivery only) | 
**type** | [**TypeEnum**](#TypeEnum) | The type of settlement. &#x60;settlement&#x60;, &#x60;delivery&#x60; or &#x60;bankruptcy&#x60;. | 



## Enum: TypeEnum

Name | Value
---- | -----
SETTLEMENT | &quot;settlement&quot;
DELIVERY | &quot;delivery&quot;
BANKRUPTCY | &quot;bankruptcy&quot;



