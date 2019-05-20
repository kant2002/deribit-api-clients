
# Settlement

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**sessionProfitLoss** | [**java.math.BigDecimal**](java.math.BigDecimal.md) | total value of session profit and losses (in base currency) | 
**markPrice** | [**java.math.BigDecimal**](java.math.BigDecimal.md) | mark price for at the settlement time (in quote currency; settlement and delivery only) |  [optional]
**funding** | [**java.math.BigDecimal**](java.math.BigDecimal.md) | funding (in base currency ; settlement for perpetual product only) | 
**socialized** | [**java.math.BigDecimal**](java.math.BigDecimal.md) | the amount of the socialized losses (in base currency; bankruptcy only) |  [optional]
**sessionBankrupcy** | [**java.math.BigDecimal**](java.math.BigDecimal.md) | value of session bankrupcy (in base currency; bankruptcy only) |  [optional]
**timestamp** | **kotlin.Int** | The timestamp (seconds since the Unix epoch, with millisecond precision) | 
**profitLoss** | [**java.math.BigDecimal**](java.math.BigDecimal.md) | profit and loss (in base currency; settlement and delivery only) |  [optional]
**funded** | [**java.math.BigDecimal**](java.math.BigDecimal.md) | funded amount (bankruptcy only) |  [optional]
**indexPrice** | [**java.math.BigDecimal**](java.math.BigDecimal.md) | underlying index price at time of event (in quote currency; settlement and delivery only) | 
**sessionTax** | [**java.math.BigDecimal**](java.math.BigDecimal.md) | total amount of paid taxes/fees (in base currency; bankruptcy only) |  [optional]
**sessionTaxRate** | [**java.math.BigDecimal**](java.math.BigDecimal.md) | rate of paid texes/fees (in base currency; bankruptcy only) |  [optional]
**instrumentName** | **kotlin.String** | instrument name (settlement and delivery only) | 
**position** | [**java.math.BigDecimal**](java.math.BigDecimal.md) | position size (in quote currency; settlement and delivery only) | 
**type** | [**inline**](#TypeEnum) | The type of settlement. &#x60;settlement&#x60;, &#x60;delivery&#x60; or &#x60;bankruptcy&#x60;. | 


<a name="TypeEnum"></a>
## Enum: type
Name | Value
---- | -----
type | settlement, delivery, bankruptcy



