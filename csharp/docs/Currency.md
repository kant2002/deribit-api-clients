
# Org.OpenAPITools.Model.Currency

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**MinConfirmations** | **int?** | Minimum number of block chain confirmations before deposit is accepted. | [optional] 
**MinWithdrawalFee** | **decimal?** | The minimum transaction fee paid for withdrawals | [optional] 
**DisabledDepositAddressCreation** | **bool?** | False if deposit address creation is disabled | [optional] 
**_Currency** | **string** | The abbreviation of the currency. This abbreviation is used elsewhere in the API to identify the currency. | 
**CurrencyLong** | **string** | The full name for the currency. | 
**WithdrawalFee** | **decimal?** | The total transaction fee paid for withdrawals | 
**FeePrecision** | **int?** | fee precision | [optional] 
**WithdrawalPriorities** | [**List&lt;CurrencyWithdrawalPriorities&gt;**](CurrencyWithdrawalPriorities.md) |  | [optional] 
**CoinType** | **string** | The type of the currency. | 

[[Back to Model list]](../README.md#documentation-for-models)
[[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to README]](../README.md)

