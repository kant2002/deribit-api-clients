# Currency

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**MinConfirmations** | **int32** | Minimum number of block chain confirmations before deposit is accepted. | [optional] 
**MinWithdrawalFee** | **float32** | The minimum transaction fee paid for withdrawals | [optional] 
**DisabledDepositAddressCreation** | **bool** | False if deposit address creation is disabled | [optional] 
**Currency** | **string** | The abbreviation of the currency. This abbreviation is used elsewhere in the API to identify the currency. | 
**CurrencyLong** | **string** | The full name for the currency. | 
**WithdrawalFee** | **float32** | The total transaction fee paid for withdrawals | 
**FeePrecision** | **int32** | fee precision | [optional] 
**WithdrawalPriorities** | [**[]CurrencyWithdrawalPriorities**](currency_withdrawal_priorities.md) |  | [optional] 
**CoinType** | **string** | The type of the currency. | 

[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)


