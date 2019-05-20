

# Currency

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**minConfirmations** | **Integer** | Minimum number of block chain confirmations before deposit is accepted. |  [optional]
**minWithdrawalFee** | [**BigDecimal**](BigDecimal.md) | The minimum transaction fee paid for withdrawals |  [optional]
**disabledDepositAddressCreation** | **Boolean** | False if deposit address creation is disabled |  [optional]
**currency** | **String** | The abbreviation of the currency. This abbreviation is used elsewhere in the API to identify the currency. | 
**currencyLong** | **String** | The full name for the currency. | 
**withdrawalFee** | [**BigDecimal**](BigDecimal.md) | The total transaction fee paid for withdrawals | 
**feePrecision** | **Integer** | fee precision |  [optional]
**withdrawalPriorities** | [**List&lt;CurrencyWithdrawalPriorities&gt;**](CurrencyWithdrawalPriorities.md) |  |  [optional]
**coinType** | [**CoinTypeEnum**](#CoinTypeEnum) | The type of the currency. | 



## Enum: CoinTypeEnum

Name | Value
---- | -----
BITCOIN | &quot;BITCOIN&quot;
ETHER | &quot;ETHER&quot;



