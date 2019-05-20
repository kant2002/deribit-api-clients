

# Withdrawal

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**updatedTimestamp** | **Integer** | The timestamp (seconds since the Unix epoch, with millisecond precision) | 
**fee** | [**BigDecimal**](BigDecimal.md) | Fee in currency |  [optional]
**confirmedTimestamp** | **Integer** | The timestamp (seconds since the Unix epoch, with millisecond precision) of withdrawal confirmation, &#x60;null&#x60; when not confirmed |  [optional]
**amount** | [**BigDecimal**](BigDecimal.md) | Amount of funds in given currency | 
**priority** | [**BigDecimal**](BigDecimal.md) | Id of priority level |  [optional]
**currency** | [**CurrencyEnum**](#CurrencyEnum) | Currency, i.e &#x60;\&quot;BTC\&quot;&#x60;, &#x60;\&quot;ETH\&quot;&#x60; | 
**state** | [**StateEnum**](#StateEnum) | Withdrawal state, allowed values : &#x60;unconfirmed&#x60;, &#x60;confirmed&#x60;, &#x60;cancelled&#x60;, &#x60;completed&#x60;, &#x60;interrupted&#x60;, &#x60;rejected&#x60; | 
**address** | **String** | Address in proper format for currency | 
**createdTimestamp** | **Integer** | The timestamp (seconds since the Unix epoch, with millisecond precision) |  [optional]
**id** | **Integer** | Withdrawal id in Deribit system |  [optional]
**transactionId** | **String** | Transaction id in proper format for currency, &#x60;null&#x60; if id is not available | 



## Enum: CurrencyEnum

Name | Value
---- | -----
BTC | &quot;BTC&quot;
ETH | &quot;ETH&quot;



## Enum: StateEnum

Name | Value
---- | -----
UNCONFIRMED | &quot;unconfirmed&quot;
CONFIRMED | &quot;confirmed&quot;
CANCELLED | &quot;cancelled&quot;
COMPLETED | &quot;completed&quot;
INTERRUPTED | &quot;interrupted&quot;
REJECTED | &quot;rejected&quot;



