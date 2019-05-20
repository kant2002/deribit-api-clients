
# Currency

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**minConfirmations** | **kotlin.Int** | Minimum number of block chain confirmations before deposit is accepted. |  [optional]
**minWithdrawalFee** | [**java.math.BigDecimal**](java.math.BigDecimal.md) | The minimum transaction fee paid for withdrawals |  [optional]
**disabledDepositAddressCreation** | **kotlin.Boolean** | False if deposit address creation is disabled |  [optional]
**currency** | **kotlin.String** | The abbreviation of the currency. This abbreviation is used elsewhere in the API to identify the currency. | 
**currencyLong** | **kotlin.String** | The full name for the currency. | 
**withdrawalFee** | [**java.math.BigDecimal**](java.math.BigDecimal.md) | The total transaction fee paid for withdrawals | 
**feePrecision** | **kotlin.Int** | fee precision |  [optional]
**withdrawalPriorities** | [**kotlin.Array&lt;CurrencyWithdrawalPriorities&gt;**](CurrencyWithdrawalPriorities.md) |  |  [optional]
**coinType** | [**inline**](#CoinTypeEnum) | The type of the currency. | 


<a name="CoinTypeEnum"></a>
## Enum: coin_type
Name | Value
---- | -----
coinType | BITCOIN, ETHER



