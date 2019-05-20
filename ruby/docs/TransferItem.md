# OpenapiClient::TransferItem

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**updated_timestamp** | **Integer** | The timestamp (seconds since the Unix epoch, with millisecond precision) | 
**direction** | **String** | Transfer direction | [optional] 
**amount** | **Float** | Amount of funds in given currency | 
**other_side** | **String** | For transfer from/to subaccount returns this subaccount name, for transfer to other account returns address, for transfer from other account returns that accounts username. | 
**currency** | **String** | Currency, i.e &#x60;\&quot;BTC\&quot;&#x60;, &#x60;\&quot;ETH\&quot;&#x60; | 
**state** | **String** | Transfer state, allowed values : &#x60;prepared&#x60;, &#x60;confirmed&#x60;, &#x60;cancelled&#x60;, &#x60;waiting_for_admin&#x60;, &#x60;rejection_reason&#x60; | 
**created_timestamp** | **Integer** | The timestamp (seconds since the Unix epoch, with millisecond precision) | 
**type** | **String** | Type of transfer: &#x60;user&#x60; - sent to user, &#x60;subaccount&#x60; - sent to subaccount | 
**id** | **Integer** | Id of transfer | 

## Code Sample

```ruby
require 'OpenapiClient'

instance = OpenapiClient::TransferItem.new(updated_timestamp: 1536569522277,
                                 direction: null,
                                 amount: 1,
                                 other_side: Smith,
                                 currency: null,
                                 state: null,
                                 created_timestamp: 1536569522277,
                                 type: null,
                                 id: 12)
```


