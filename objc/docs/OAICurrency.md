# OAICurrency

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**minConfirmations** | **NSNumber*** | Minimum number of block chain confirmations before deposit is accepted. | [optional] 
**minWithdrawalFee** | **NSNumber*** | The minimum transaction fee paid for withdrawals | [optional] 
**disabledDepositAddressCreation** | **NSNumber*** | False if deposit address creation is disabled | [optional] 
**currency** | **NSString*** | The abbreviation of the currency. This abbreviation is used elsewhere in the API to identify the currency. | 
**currencyLong** | **NSString*** | The full name for the currency. | 
**withdrawalFee** | **NSNumber*** | The total transaction fee paid for withdrawals | 
**feePrecision** | **NSNumber*** | fee precision | [optional] 
**withdrawalPriorities** | [**NSArray&lt;OAICurrencyWithdrawalPriorities&gt;***](OAICurrencyWithdrawalPriorities.md) |  | [optional] 
**coinType** | **NSString*** | The type of the currency. | 

[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)


