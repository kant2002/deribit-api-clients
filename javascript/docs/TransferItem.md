# DeribitApi.TransferItem

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**updatedTimestamp** | **Number** | The timestamp (seconds since the Unix epoch, with millisecond precision) | 
**direction** | **String** | Transfer direction | [optional] 
**amount** | **Number** | Amount of funds in given currency | 
**otherSide** | **String** | For transfer from/to subaccount returns this subaccount name, for transfer to other account returns address, for transfer from other account returns that accounts username. | 
**currency** | **String** | Currency, i.e &#x60;\&quot;BTC\&quot;&#x60;, &#x60;\&quot;ETH\&quot;&#x60; | 
**state** | **String** | Transfer state, allowed values : &#x60;prepared&#x60;, &#x60;confirmed&#x60;, &#x60;cancelled&#x60;, &#x60;waiting_for_admin&#x60;, &#x60;rejection_reason&#x60; | 
**createdTimestamp** | **Number** | The timestamp (seconds since the Unix epoch, with millisecond precision) | 
**type** | **String** | Type of transfer: &#x60;user&#x60; - sent to user, &#x60;subaccount&#x60; - sent to subaccount | 
**id** | **Number** | Id of transfer | 



## Enum: DirectionEnum


* `payment` (value: `"payment"`)

* `income` (value: `"income"`)





## Enum: CurrencyEnum


* `BTC` (value: `"BTC"`)

* `ETH` (value: `"ETH"`)





## Enum: StateEnum


* `prepared` (value: `"prepared"`)

* `confirmed` (value: `"confirmed"`)

* `cancelled` (value: `"cancelled"`)

* `waiting_for_admin` (value: `"waiting_for_admin"`)

* `rejection_reason` (value: `"rejection_reason"`)





## Enum: TypeEnum


* `user` (value: `"user"`)

* `subaccount` (value: `"subaccount"`)




