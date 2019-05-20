# Withdrawal

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**updatedTimestamp** | **Int** | The timestamp (seconds since the Unix epoch, with millisecond precision) | 
**fee** | **Double** | Fee in currency | [optional] 
**confirmedTimestamp** | **Int** | The timestamp (seconds since the Unix epoch, with millisecond precision) of withdrawal confirmation, &#x60;null&#x60; when not confirmed | [optional] 
**amount** | **Double** | Amount of funds in given currency | 
**priority** | **Double** | Id of priority level | [optional] 
**currency** | **String** | Currency, i.e &#x60;\&quot;BTC\&quot;&#x60;, &#x60;\&quot;ETH\&quot;&#x60; | 
**state** | **String** | Withdrawal state, allowed values : &#x60;unconfirmed&#x60;, &#x60;confirmed&#x60;, &#x60;cancelled&#x60;, &#x60;completed&#x60;, &#x60;interrupted&#x60;, &#x60;rejected&#x60; | 
**address** | **String** | Address in proper format for currency | 
**createdTimestamp** | **Int** | The timestamp (seconds since the Unix epoch, with millisecond precision) | [optional] 
**id** | **Int** | Withdrawal id in Deribit system | [optional] 
**transactionId** | **String** | Transaction id in proper format for currency, &#x60;null&#x60; if id is not available | 

[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)


