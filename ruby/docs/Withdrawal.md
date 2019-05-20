# OpenapiClient::Withdrawal

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**updated_timestamp** | **Integer** | The timestamp (seconds since the Unix epoch, with millisecond precision) | 
**fee** | **Float** | Fee in currency | [optional] 
**confirmed_timestamp** | **Integer** | The timestamp (seconds since the Unix epoch, with millisecond precision) of withdrawal confirmation, &#x60;null&#x60; when not confirmed | [optional] 
**amount** | **Float** | Amount of funds in given currency | 
**priority** | **Float** | Id of priority level | [optional] 
**currency** | **String** | Currency, i.e &#x60;\&quot;BTC\&quot;&#x60;, &#x60;\&quot;ETH\&quot;&#x60; | 
**state** | **String** | Withdrawal state, allowed values : &#x60;unconfirmed&#x60;, &#x60;confirmed&#x60;, &#x60;cancelled&#x60;, &#x60;completed&#x60;, &#x60;interrupted&#x60;, &#x60;rejected&#x60; | 
**address** | **String** | Address in proper format for currency | 
**created_timestamp** | **Integer** | The timestamp (seconds since the Unix epoch, with millisecond precision) | [optional] 
**id** | **Integer** | Withdrawal id in Deribit system | [optional] 
**transaction_id** | **String** | Transaction id in proper format for currency, &#x60;null&#x60; if id is not available | 

## Code Sample

```ruby
require 'OpenapiClient'

instance = OpenapiClient::Withdrawal.new(updated_timestamp: 1536569522277,
                                 fee: 0.000023,
                                 confirmed_timestamp: 1536569522277,
                                 amount: 1,
                                 priority: 1,
                                 currency: null,
                                 state: null,
                                 address: 1A1zP1eP5QGefi2DMPTfTL5SLmv7DivfNa,
                                 created_timestamp: 1536569522277,
                                 id: 1,
                                 transaction_id: 1b1fb5568515e2b79503501e3d3680b2d0838d5dfc2d15a04eb8cd9fbbe0b572)
```


