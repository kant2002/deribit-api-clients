# OpenapiClient::Currency

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**min_confirmations** | **Integer** | Minimum number of block chain confirmations before deposit is accepted. | [optional] 
**min_withdrawal_fee** | **Float** | The minimum transaction fee paid for withdrawals | [optional] 
**disabled_deposit_address_creation** | **Boolean** | False if deposit address creation is disabled | [optional] 
**currency** | **String** | The abbreviation of the currency. This abbreviation is used elsewhere in the API to identify the currency. | 
**currency_long** | **String** | The full name for the currency. | 
**withdrawal_fee** | **Float** | The total transaction fee paid for withdrawals | 
**fee_precision** | **Integer** | fee precision | [optional] 
**withdrawal_priorities** | [**Array&lt;CurrencyWithdrawalPriorities&gt;**](CurrencyWithdrawalPriorities.md) |  | [optional] 
**coin_type** | **String** | The type of the currency. | 

## Code Sample

```ruby
require 'OpenapiClient'

instance = OpenapiClient::Currency.new(min_confirmations: 2,
                                 min_withdrawal_fee: 0.00010,
                                 disabled_deposit_address_creation: false,
                                 currency: BTC,
                                 currency_long: Bitcoin,
                                 withdrawal_fee: 0.00010,
                                 fee_precision: 4,
                                 withdrawal_priorities: null,
                                 coin_type: null)
```


