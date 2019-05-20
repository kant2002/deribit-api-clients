
# Org.OpenAPITools.Model.Deposit

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**UpdatedTimestamp** | **int?** | The timestamp (seconds since the Unix epoch, with millisecond precision) | 
**State** | **string** | Deposit state, allowed values : &#x60;pending&#x60;, &#x60;completed&#x60;, &#x60;rejected&#x60;, &#x60;replaced&#x60; | 
**ReceivedTimestamp** | **int?** | The timestamp (seconds since the Unix epoch, with millisecond precision) | 
**Currency** | **string** | Currency, i.e &#x60;\&quot;BTC\&quot;&#x60;, &#x60;\&quot;ETH\&quot;&#x60; | 
**Address** | **string** | Address in proper format for currency | 
**Amount** | **decimal?** | Amount of funds in given currency | 
**TransactionId** | **string** | Transaction id in proper format for currency, &#x60;null&#x60; if id is not available | 

[[Back to Model list]](../README.md#documentation-for-models)
[[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to README]](../README.md)

