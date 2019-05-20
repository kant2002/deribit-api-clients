

# TransferItem

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**updatedTimestamp** | **Integer** | The timestamp (seconds since the Unix epoch, with millisecond precision) | 
**direction** | [**DirectionEnum**](#DirectionEnum) | Transfer direction |  [optional]
**amount** | [**BigDecimal**](BigDecimal.md) | Amount of funds in given currency | 
**otherSide** | **String** | For transfer from/to subaccount returns this subaccount name, for transfer to other account returns address, for transfer from other account returns that accounts username. | 
**currency** | [**CurrencyEnum**](#CurrencyEnum) | Currency, i.e &#x60;\&quot;BTC\&quot;&#x60;, &#x60;\&quot;ETH\&quot;&#x60; | 
**state** | [**StateEnum**](#StateEnum) | Transfer state, allowed values : &#x60;prepared&#x60;, &#x60;confirmed&#x60;, &#x60;cancelled&#x60;, &#x60;waiting_for_admin&#x60;, &#x60;rejection_reason&#x60; | 
**createdTimestamp** | **Integer** | The timestamp (seconds since the Unix epoch, with millisecond precision) | 
**type** | [**TypeEnum**](#TypeEnum) | Type of transfer: &#x60;user&#x60; - sent to user, &#x60;subaccount&#x60; - sent to subaccount | 
**id** | **Integer** | Id of transfer | 


## Enum: DirectionEnum

Name | Value
---- | -----


## Enum: CurrencyEnum

Name | Value
---- | -----


## Enum: StateEnum

Name | Value
---- | -----


## Enum: TypeEnum

Name | Value
---- | -----




