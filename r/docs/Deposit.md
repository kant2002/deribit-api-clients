# openapi::Deposit

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**updated_timestamp** | **integer** | The timestamp (seconds since the Unix epoch, with millisecond precision) | 
**state** | **character** | Deposit state, allowed values : &#x60;pending&#x60;, &#x60;completed&#x60;, &#x60;rejected&#x60;, &#x60;replaced&#x60; | 
**received_timestamp** | **integer** | The timestamp (seconds since the Unix epoch, with millisecond precision) | 
**currency** | **character** | Currency, i.e &#x60;\&quot;BTC\&quot;&#x60;, &#x60;\&quot;ETH\&quot;&#x60; | 
**address** | **character** | Address in proper format for currency | 
**amount** | **numeric** | Amount of funds in given currency | 
**transaction_id** | **character** | Transaction id in proper format for currency, &#x60;null&#x60; if id is not available | 


