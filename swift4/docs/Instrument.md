# Instrument

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**quoteCurrency** | **String** | The currency in which the instrument prices are quoted. | 
**kind** | **String** | Instrument kind, &#x60;\&quot;future\&quot;&#x60; or &#x60;\&quot;option\&quot;&#x60; | 
**tickSize** | **Double** | specifies minimal price change and, as follows, the number of decimal places for instrument prices | 
**contractSize** | **Int** | Contract size for instrument | 
**isActive** | **Bool** | Indicates if the instrument can currently be traded. | 
**optionType** | **String** | The option type (only for options) | [optional] 
**minTradeAmount** | **Double** | Minimum amount for trading. For perpetual and futures - in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH. | 
**instrumentName** | **String** | Unique instrument identifier | 
**settlementPeriod** | **String** | The settlement period. | 
**strike** | **Double** | The strike value. (only for options) | [optional] 
**baseCurrency** | **String** | The underlying currency being traded. | 
**creationTimestamp** | **Int** | The time when the instrument was first created (milliseconds) | 
**expirationTimestamp** | **Int** | The time when the instrument will expire (milliseconds) | 

[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)


