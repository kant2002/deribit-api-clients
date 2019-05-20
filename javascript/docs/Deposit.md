# DeribitApi.Deposit

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**updatedTimestamp** | **Number** | The timestamp (seconds since the Unix epoch, with millisecond precision) | 
**state** | **String** | Deposit state, allowed values : &#x60;pending&#x60;, &#x60;completed&#x60;, &#x60;rejected&#x60;, &#x60;replaced&#x60; | 
**receivedTimestamp** | **Number** | The timestamp (seconds since the Unix epoch, with millisecond precision) | 
**currency** | **String** | Currency, i.e &#x60;\&quot;BTC\&quot;&#x60;, &#x60;\&quot;ETH\&quot;&#x60; | 
**address** | **String** | Address in proper format for currency | 
**amount** | **Number** | Amount of funds in given currency | 
**transactionId** | **String** | Transaction id in proper format for currency, &#x60;null&#x60; if id is not available | 



## Enum: StateEnum


* `pending` (value: `"pending"`)

* `completed` (value: `"completed"`)

* `rejected` (value: `"rejected"`)

* `replaced` (value: `"replaced"`)





## Enum: CurrencyEnum


* `BTC` (value: `"BTC"`)

* `ETH` (value: `"ETH"`)




