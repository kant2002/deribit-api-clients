
# AddressBookItem

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**currency** | [**inline**](#CurrencyEnum) | Currency, i.e &#x60;\&quot;BTC\&quot;&#x60;, &#x60;\&quot;ETH\&quot;&#x60; | 
**address** | **kotlin.String** | Address in proper format for currency | 
**type** | [**inline**](#TypeEnum) | Address book type |  [optional]
**creationTimestamp** | **kotlin.Int** | The timestamp (seconds since the Unix epoch, with millisecond precision) | 


<a name="CurrencyEnum"></a>
## Enum: currency
Name | Value
---- | -----
currency | BTC, ETH


<a name="TypeEnum"></a>
## Enum: type
Name | Value
---- | -----
type | transfer, withdrawal



