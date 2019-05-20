# OAIInstrument

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**quoteCurrency** | **NSString*** | The currency in which the instrument prices are quoted. | 
**kind** | **NSString*** | Instrument kind, &#x60;\&quot;future\&quot;&#x60; or &#x60;\&quot;option\&quot;&#x60; | 
**tickSize** | **NSNumber*** | specifies minimal price change and, as follows, the number of decimal places for instrument prices | 
**contractSize** | **NSNumber*** | Contract size for instrument | 
**isActive** | **NSNumber*** | Indicates if the instrument can currently be traded. | 
**optionType** | **NSString*** | The option type (only for options) | [optional] 
**minTradeAmount** | **NSNumber*** | Minimum amount for trading. For perpetual and futures - in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH. | 
**instrumentName** | **NSString*** | Unique instrument identifier | 
**settlementPeriod** | **NSString*** | The settlement period. | 
**strike** | **NSNumber*** | The strike value. (only for options) | [optional] 
**baseCurrency** | **NSString*** | The underlying currency being traded. | 
**creationTimestamp** | **NSNumber*** | The time when the instrument was first created (milliseconds) | 
**expirationTimestamp** | **NSNumber*** | The time when the instrument will expire (milliseconds) | 

[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)


