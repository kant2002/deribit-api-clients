# OAIDeposit

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**updatedTimestamp** | **NSNumber*** | The timestamp (seconds since the Unix epoch, with millisecond precision) | 
**state** | **NSString*** | Deposit state, allowed values : &#x60;pending&#x60;, &#x60;completed&#x60;, &#x60;rejected&#x60;, &#x60;replaced&#x60; | 
**receivedTimestamp** | **NSNumber*** | The timestamp (seconds since the Unix epoch, with millisecond precision) | 
**currency** | **NSString*** | Currency, i.e &#x60;\&quot;BTC\&quot;&#x60;, &#x60;\&quot;ETH\&quot;&#x60; | 
**address** | **NSString*** | Address in proper format for currency | 
**amount** | **NSNumber*** | Amount of funds in given currency | 
**transactionId** | **NSString*** | Transaction id in proper format for currency, &#x60;null&#x60; if id is not available | 

[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)


