# TransferItem

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**updatedTimestamp** | **Int** | The timestamp (seconds since the Unix epoch, with millisecond precision) | 
**direction** | **String** | Transfer direction | [optional] 
**amount** | **Double** | Amount of funds in given currency | 
**otherSide** | **String** | For transfer from/to subaccount returns this subaccount name, for transfer to other account returns address, for transfer from other account returns that accounts username. | 
**currency** | **String** | Currency, i.e &#x60;\&quot;BTC\&quot;&#x60;, &#x60;\&quot;ETH\&quot;&#x60; | 
**state** | **String** | Transfer state, allowed values : &#x60;prepared&#x60;, &#x60;confirmed&#x60;, &#x60;cancelled&#x60;, &#x60;waiting_for_admin&#x60;, &#x60;rejection_reason&#x60; | 
**createdTimestamp** | **Int** | The timestamp (seconds since the Unix epoch, with millisecond precision) | 
**type** | **String** | Type of transfer: &#x60;user&#x60; - sent to user, &#x60;subaccount&#x60; - sent to subaccount | 
**id** | **Int** | Id of transfer | 

[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)


