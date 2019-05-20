# OAIWithdrawal

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**updatedTimestamp** | **NSNumber*** | The timestamp (seconds since the Unix epoch, with millisecond precision) | 
**fee** | **NSNumber*** | Fee in currency | [optional] 
**confirmedTimestamp** | **NSNumber*** | The timestamp (seconds since the Unix epoch, with millisecond precision) of withdrawal confirmation, &#x60;null&#x60; when not confirmed | [optional] 
**amount** | **NSNumber*** | Amount of funds in given currency | 
**priority** | **NSNumber*** | Id of priority level | [optional] 
**currency** | **NSString*** | Currency, i.e &#x60;\&quot;BTC\&quot;&#x60;, &#x60;\&quot;ETH\&quot;&#x60; | 
**state** | **NSString*** | Withdrawal state, allowed values : &#x60;unconfirmed&#x60;, &#x60;confirmed&#x60;, &#x60;cancelled&#x60;, &#x60;completed&#x60;, &#x60;interrupted&#x60;, &#x60;rejected&#x60; | 
**address** | **NSString*** | Address in proper format for currency | 
**createdTimestamp** | **NSNumber*** | The timestamp (seconds since the Unix epoch, with millisecond precision) | [optional] 
**_id** | **NSNumber*** | Withdrawal id in Deribit system | [optional] 
**transactionId** | **NSString*** | Transaction id in proper format for currency, &#x60;null&#x60; if id is not available | 

[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)


