
# Org.OpenAPITools.Model.Withdrawal

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**UpdatedTimestamp** | **int?** | The timestamp (seconds since the Unix epoch, with millisecond precision) | 
**Fee** | **decimal?** | Fee in currency | [optional] 
**ConfirmedTimestamp** | **int?** | The timestamp (seconds since the Unix epoch, with millisecond precision) of withdrawal confirmation, &#x60;null&#x60; when not confirmed | [optional] 
**Amount** | **decimal?** | Amount of funds in given currency | 
**Priority** | **decimal?** | Id of priority level | [optional] 
**Currency** | **string** | Currency, i.e &#x60;\&quot;BTC\&quot;&#x60;, &#x60;\&quot;ETH\&quot;&#x60; | 
**State** | **string** | Withdrawal state, allowed values : &#x60;unconfirmed&#x60;, &#x60;confirmed&#x60;, &#x60;cancelled&#x60;, &#x60;completed&#x60;, &#x60;interrupted&#x60;, &#x60;rejected&#x60; | 
**Address** | **string** | Address in proper format for currency | 
**CreatedTimestamp** | **int?** | The timestamp (seconds since the Unix epoch, with millisecond precision) | [optional] 
**Id** | **int?** | Withdrawal id in Deribit system | [optional] 
**TransactionId** | **string** | Transaction id in proper format for currency, &#x60;null&#x60; if id is not available | 

[[Back to Model list]](../README.md#documentation-for-models)
[[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to README]](../README.md)

