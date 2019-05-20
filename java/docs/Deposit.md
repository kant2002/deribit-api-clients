

# Deposit

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**updatedTimestamp** | **Integer** | The timestamp (seconds since the Unix epoch, with millisecond precision) | 
**state** | [**StateEnum**](#StateEnum) | Deposit state, allowed values : &#x60;pending&#x60;, &#x60;completed&#x60;, &#x60;rejected&#x60;, &#x60;replaced&#x60; | 
**receivedTimestamp** | **Integer** | The timestamp (seconds since the Unix epoch, with millisecond precision) | 
**currency** | [**CurrencyEnum**](#CurrencyEnum) | Currency, i.e &#x60;\&quot;BTC\&quot;&#x60;, &#x60;\&quot;ETH\&quot;&#x60; | 
**address** | **String** | Address in proper format for currency | 
**amount** | [**BigDecimal**](BigDecimal.md) | Amount of funds in given currency | 
**transactionId** | **String** | Transaction id in proper format for currency, &#x60;null&#x60; if id is not available | 



## Enum: StateEnum

Name | Value
---- | -----
PENDING | &quot;pending&quot;
COMPLETED | &quot;completed&quot;
REJECTED | &quot;rejected&quot;
REPLACED | &quot;replaced&quot;



## Enum: CurrencyEnum

Name | Value
---- | -----
BTC | &quot;BTC&quot;
ETH | &quot;ETH&quot;



