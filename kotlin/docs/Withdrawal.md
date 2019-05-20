
# Withdrawal

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**updatedTimestamp** | **kotlin.Int** | The timestamp (seconds since the Unix epoch, with millisecond precision) | 
**fee** | [**java.math.BigDecimal**](java.math.BigDecimal.md) | Fee in currency |  [optional]
**confirmedTimestamp** | **kotlin.Int** | The timestamp (seconds since the Unix epoch, with millisecond precision) of withdrawal confirmation, &#x60;null&#x60; when not confirmed |  [optional]
**amount** | [**java.math.BigDecimal**](java.math.BigDecimal.md) | Amount of funds in given currency | 
**priority** | [**java.math.BigDecimal**](java.math.BigDecimal.md) | Id of priority level |  [optional]
**currency** | [**inline**](#CurrencyEnum) | Currency, i.e &#x60;\&quot;BTC\&quot;&#x60;, &#x60;\&quot;ETH\&quot;&#x60; | 
**state** | [**inline**](#StateEnum) | Withdrawal state, allowed values : &#x60;unconfirmed&#x60;, &#x60;confirmed&#x60;, &#x60;cancelled&#x60;, &#x60;completed&#x60;, &#x60;interrupted&#x60;, &#x60;rejected&#x60; | 
**address** | **kotlin.String** | Address in proper format for currency | 
**createdTimestamp** | **kotlin.Int** | The timestamp (seconds since the Unix epoch, with millisecond precision) |  [optional]
**id** | **kotlin.Int** | Withdrawal id in Deribit system |  [optional]
**transactionId** | **kotlin.String** | Transaction id in proper format for currency, &#x60;null&#x60; if id is not available | 


<a name="CurrencyEnum"></a>
## Enum: currency
Name | Value
---- | -----
currency | BTC, ETH


<a name="StateEnum"></a>
## Enum: state
Name | Value
---- | -----
state | unconfirmed, confirmed, cancelled, completed, interrupted, rejected



