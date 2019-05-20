
# Deposit

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**updatedTimestamp** | **kotlin.Int** | The timestamp (seconds since the Unix epoch, with millisecond precision) | 
**state** | [**inline**](#StateEnum) | Deposit state, allowed values : &#x60;pending&#x60;, &#x60;completed&#x60;, &#x60;rejected&#x60;, &#x60;replaced&#x60; | 
**receivedTimestamp** | **kotlin.Int** | The timestamp (seconds since the Unix epoch, with millisecond precision) | 
**currency** | [**inline**](#CurrencyEnum) | Currency, i.e &#x60;\&quot;BTC\&quot;&#x60;, &#x60;\&quot;ETH\&quot;&#x60; | 
**address** | **kotlin.String** | Address in proper format for currency | 
**amount** | [**java.math.BigDecimal**](java.math.BigDecimal.md) | Amount of funds in given currency | 
**transactionId** | **kotlin.String** | Transaction id in proper format for currency, &#x60;null&#x60; if id is not available | 


<a name="StateEnum"></a>
## Enum: state
Name | Value
---- | -----
state | pending, completed, rejected, replaced


<a name="CurrencyEnum"></a>
## Enum: currency
Name | Value
---- | -----
currency | BTC, ETH



