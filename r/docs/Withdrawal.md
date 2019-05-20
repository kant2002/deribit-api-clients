# openapi::Withdrawal

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**updated_timestamp** | **integer** | The timestamp (seconds since the Unix epoch, with millisecond precision) | 
**fee** | **numeric** | Fee in currency | [optional] 
**confirmed_timestamp** | **integer** | The timestamp (seconds since the Unix epoch, with millisecond precision) of withdrawal confirmation, &#x60;null&#x60; when not confirmed | [optional] 
**amount** | **numeric** | Amount of funds in given currency | 
**priority** | **numeric** | Id of priority level | [optional] 
**currency** | **character** | Currency, i.e &#x60;\&quot;BTC\&quot;&#x60;, &#x60;\&quot;ETH\&quot;&#x60; | 
**state** | **character** | Withdrawal state, allowed values : &#x60;unconfirmed&#x60;, &#x60;confirmed&#x60;, &#x60;cancelled&#x60;, &#x60;completed&#x60;, &#x60;interrupted&#x60;, &#x60;rejected&#x60; | 
**address** | **character** | Address in proper format for currency | 
**created_timestamp** | **integer** | The timestamp (seconds since the Unix epoch, with millisecond precision) | [optional] 
**id** | **integer** | Withdrawal id in Deribit system | [optional] 
**transaction_id** | **character** | Transaction id in proper format for currency, &#x60;null&#x60; if id is not available | 


