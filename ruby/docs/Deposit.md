# OpenapiClient::Deposit

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**updated_timestamp** | **Integer** | The timestamp (seconds since the Unix epoch, with millisecond precision) | 
**state** | **String** | Deposit state, allowed values : &#x60;pending&#x60;, &#x60;completed&#x60;, &#x60;rejected&#x60;, &#x60;replaced&#x60; | 
**received_timestamp** | **Integer** | The timestamp (seconds since the Unix epoch, with millisecond precision) | 
**currency** | **String** | Currency, i.e &#x60;\&quot;BTC\&quot;&#x60;, &#x60;\&quot;ETH\&quot;&#x60; | 
**address** | **String** | Address in proper format for currency | 
**amount** | **Float** | Amount of funds in given currency | 
**transaction_id** | **String** | Transaction id in proper format for currency, &#x60;null&#x60; if id is not available | 

## Code Sample

```ruby
require 'OpenapiClient'

instance = OpenapiClient::Deposit.new(updated_timestamp: 1536569522277,
                                 state: null,
                                 received_timestamp: 1536569522277,
                                 currency: null,
                                 address: 1A1zP1eP5QGefi2DMPTfTL5SLmv7DivfNa,
                                 amount: 1,
                                 transaction_id: 1b1fb5568515e2b79503501e3d3680b2d0838d5dfc2d15a04eb8cd9fbbe0b572)
```


