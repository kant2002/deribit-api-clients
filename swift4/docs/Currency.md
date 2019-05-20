# Currency

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**minConfirmations** | **Int** | Minimum number of block chain confirmations before deposit is accepted. | [optional] 
**minWithdrawalFee** | **Double** | The minimum transaction fee paid for withdrawals | [optional] 
**disabledDepositAddressCreation** | **Bool** | False if deposit address creation is disabled | [optional] 
**currency** | **String** | The abbreviation of the currency. This abbreviation is used elsewhere in the API to identify the currency. | 
**currencyLong** | **String** | The full name for the currency. | 
**withdrawalFee** | **Double** | The total transaction fee paid for withdrawals | 
**feePrecision** | **Int** | fee precision | [optional] 
**withdrawalPriorities** | [CurrencyWithdrawalPriorities] |  | [optional] 
**coinType** | **String** | The type of the currency. | 

[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)


