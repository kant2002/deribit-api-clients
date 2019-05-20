
# TransferItem

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**updatedTimestamp** | **kotlin.Int** | The timestamp (seconds since the Unix epoch, with millisecond precision) | 
**direction** | [**inline**](#DirectionEnum) | Transfer direction |  [optional]
**amount** | [**java.math.BigDecimal**](java.math.BigDecimal.md) | Amount of funds in given currency | 
**otherSide** | **kotlin.String** | For transfer from/to subaccount returns this subaccount name, for transfer to other account returns address, for transfer from other account returns that accounts username. | 
**currency** | [**inline**](#CurrencyEnum) | Currency, i.e &#x60;\&quot;BTC\&quot;&#x60;, &#x60;\&quot;ETH\&quot;&#x60; | 
**state** | [**inline**](#StateEnum) | Transfer state, allowed values : &#x60;prepared&#x60;, &#x60;confirmed&#x60;, &#x60;cancelled&#x60;, &#x60;waiting_for_admin&#x60;, &#x60;rejection_reason&#x60; | 
**createdTimestamp** | **kotlin.Int** | The timestamp (seconds since the Unix epoch, with millisecond precision) | 
**type** | [**inline**](#TypeEnum) | Type of transfer: &#x60;user&#x60; - sent to user, &#x60;subaccount&#x60; - sent to subaccount | 
**id** | **kotlin.Int** | Id of transfer | 


<a name="DirectionEnum"></a>
## Enum: direction
Name | Value
---- | -----
direction | payment, income


<a name="CurrencyEnum"></a>
## Enum: currency
Name | Value
---- | -----
currency | BTC, ETH


<a name="StateEnum"></a>
## Enum: state
Name | Value
---- | -----
state | prepared, confirmed, cancelled, waiting_for_admin, rejection_reason


<a name="TypeEnum"></a>
## Enum: type
Name | Value
---- | -----
type | user, subaccount



