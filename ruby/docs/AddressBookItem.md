# OpenapiClient::AddressBookItem

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**currency** | **String** | Currency, i.e &#x60;\&quot;BTC\&quot;&#x60;, &#x60;\&quot;ETH\&quot;&#x60; | 
**address** | **String** | Address in proper format for currency | 
**type** | **String** | Address book type | [optional] 
**creation_timestamp** | **Integer** | The timestamp (seconds since the Unix epoch, with millisecond precision) | 

## Code Sample

```ruby
require 'OpenapiClient'

instance = OpenapiClient::AddressBookItem.new(currency: null,
                                 address: 1A1zP1eP5QGefi2DMPTfTL5SLmv7DivfNa,
                                 type: null,
                                 creation_timestamp: 1536569522277)
```


