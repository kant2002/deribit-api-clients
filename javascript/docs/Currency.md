# DeribitApi.Currency

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**minConfirmations** | **Number** | Minimum number of block chain confirmations before deposit is accepted. | [optional] 
**minWithdrawalFee** | **Number** | The minimum transaction fee paid for withdrawals | [optional] 
**disabledDepositAddressCreation** | **Boolean** | False if deposit address creation is disabled | [optional] 
**currency** | **String** | The abbreviation of the currency. This abbreviation is used elsewhere in the API to identify the currency. | 
**currencyLong** | **String** | The full name for the currency. | 
**withdrawalFee** | **Number** | The total transaction fee paid for withdrawals | 
**feePrecision** | **Number** | fee precision | [optional] 
**withdrawalPriorities** | [**[CurrencyWithdrawalPriorities]**](CurrencyWithdrawalPriorities.md) |  | [optional] 
**coinType** | **String** | The type of the currency. | 



## Enum: CoinTypeEnum


* `BITCOIN` (value: `"BITCOIN"`)

* `ETHER` (value: `"ETHER"`)




