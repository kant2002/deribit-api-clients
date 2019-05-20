

# AddressBookItem

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**currency** | [**CurrencyEnum**](#CurrencyEnum) | Currency, i.e &#x60;\&quot;BTC\&quot;&#x60;, &#x60;\&quot;ETH\&quot;&#x60; | 
**address** | **String** | Address in proper format for currency | 
**type** | [**TypeEnum**](#TypeEnum) | Address book type |  [optional]
**creationTimestamp** | **Integer** | The timestamp (seconds since the Unix epoch, with millisecond precision) | 



## Enum: CurrencyEnum

Name | Value
---- | -----
BTC | &quot;BTC&quot;
ETH | &quot;ETH&quot;



## Enum: TypeEnum

Name | Value
---- | -----
TRANSFER | &quot;transfer&quot;
WITHDRAWAL | &quot;withdrawal&quot;



