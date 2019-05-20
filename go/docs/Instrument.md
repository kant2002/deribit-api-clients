# Instrument

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**QuoteCurrency** | **string** | The currency in which the instrument prices are quoted. | 
**Kind** | **string** | Instrument kind, &#x60;\&quot;future\&quot;&#x60; or &#x60;\&quot;option\&quot;&#x60; | 
**TickSize** | **float32** | specifies minimal price change and, as follows, the number of decimal places for instrument prices | 
**ContractSize** | **int32** | Contract size for instrument | 
**IsActive** | **bool** | Indicates if the instrument can currently be traded. | 
**OptionType** | **string** | The option type (only for options) | [optional] 
**MinTradeAmount** | **float32** | Minimum amount for trading. For perpetual and futures - in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH. | 
**InstrumentName** | **string** | Unique instrument identifier | 
**SettlementPeriod** | **string** | The settlement period. | 
**Strike** | **float32** | The strike value. (only for options) | [optional] 
**BaseCurrency** | **string** | The underlying currency being traded. | 
**CreationTimestamp** | **int32** | The time when the instrument was first created (milliseconds) | 
**ExpirationTimestamp** | **int32** | The time when the instrument will expire (milliseconds) | 

[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)


