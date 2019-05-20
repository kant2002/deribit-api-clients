# openapi::TransferItem

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**updated_timestamp** | **integer** | The timestamp (seconds since the Unix epoch, with millisecond precision) | 
**direction** | **character** | Transfer direction | [optional] 
**amount** | **numeric** | Amount of funds in given currency | 
**other_side** | **character** | For transfer from/to subaccount returns this subaccount name, for transfer to other account returns address, for transfer from other account returns that accounts username. | 
**currency** | **character** | Currency, i.e &#x60;\&quot;BTC\&quot;&#x60;, &#x60;\&quot;ETH\&quot;&#x60; | 
**state** | **character** | Transfer state, allowed values : &#x60;prepared&#x60;, &#x60;confirmed&#x60;, &#x60;cancelled&#x60;, &#x60;waiting_for_admin&#x60;, &#x60;rejection_reason&#x60; | 
**created_timestamp** | **integer** | The timestamp (seconds since the Unix epoch, with millisecond precision) | 
**type** | **character** | Type of transfer: &#x60;user&#x60; - sent to user, &#x60;subaccount&#x60; - sent to subaccount | 
**id** | **integer** | Id of transfer | 


