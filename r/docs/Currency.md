# openapi::Currency

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**min_confirmations** | **integer** | Minimum number of block chain confirmations before deposit is accepted. | [optional] 
**min_withdrawal_fee** | **numeric** | The minimum transaction fee paid for withdrawals | [optional] 
**disabled_deposit_address_creation** | **character** | False if deposit address creation is disabled | [optional] 
**currency** | **character** | The abbreviation of the currency. This abbreviation is used elsewhere in the API to identify the currency. | 
**currency_long** | **character** | The full name for the currency. | 
**withdrawal_fee** | **numeric** | The total transaction fee paid for withdrawals | 
**fee_precision** | **integer** | fee precision | [optional] 
**withdrawal_priorities** | [**CurrencyWithdrawalPriorities**](currency_withdrawal_priorities.md) |  | [optional] 
**coin_type** | **character** | The type of the currency. | 


