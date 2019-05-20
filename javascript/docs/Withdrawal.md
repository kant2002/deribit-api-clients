# DeribitApi.Withdrawal

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**updatedTimestamp** | **Number** | The timestamp (seconds since the Unix epoch, with millisecond precision) | 
**fee** | **Number** | Fee in currency | [optional] 
**confirmedTimestamp** | **Number** | The timestamp (seconds since the Unix epoch, with millisecond precision) of withdrawal confirmation, &#x60;null&#x60; when not confirmed | [optional] 
**amount** | **Number** | Amount of funds in given currency | 
**priority** | **Number** | Id of priority level | [optional] 
**currency** | **String** | Currency, i.e &#x60;\&quot;BTC\&quot;&#x60;, &#x60;\&quot;ETH\&quot;&#x60; | 
**state** | **String** | Withdrawal state, allowed values : &#x60;unconfirmed&#x60;, &#x60;confirmed&#x60;, &#x60;cancelled&#x60;, &#x60;completed&#x60;, &#x60;interrupted&#x60;, &#x60;rejected&#x60; | 
**address** | **String** | Address in proper format for currency | 
**createdTimestamp** | **Number** | The timestamp (seconds since the Unix epoch, with millisecond precision) | [optional] 
**id** | **Number** | Withdrawal id in Deribit system | [optional] 
**transactionId** | **String** | Transaction id in proper format for currency, &#x60;null&#x60; if id is not available | 



## Enum: CurrencyEnum


* `BTC` (value: `"BTC"`)

* `ETH` (value: `"ETH"`)





## Enum: StateEnum


* `unconfirmed` (value: `"unconfirmed"`)

* `confirmed` (value: `"confirmed"`)

* `cancelled` (value: `"cancelled"`)

* `completed` (value: `"completed"`)

* `interrupted` (value: `"interrupted"`)

* `rejected` (value: `"rejected"`)




