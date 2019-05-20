# DeribitApi.Instrument

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**quoteCurrency** | **String** | The currency in which the instrument prices are quoted. | 
**kind** | **String** | Instrument kind, &#x60;\&quot;future\&quot;&#x60; or &#x60;\&quot;option\&quot;&#x60; | 
**tickSize** | **Number** | specifies minimal price change and, as follows, the number of decimal places for instrument prices | 
**contractSize** | **Number** | Contract size for instrument | 
**isActive** | **Boolean** | Indicates if the instrument can currently be traded. | 
**optionType** | **String** | The option type (only for options) | [optional] 
**minTradeAmount** | **Number** | Minimum amount for trading. For perpetual and futures - in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH. | 
**instrumentName** | **String** | Unique instrument identifier | 
**settlementPeriod** | **String** | The settlement period. | 
**strike** | **Number** | The strike value. (only for options) | [optional] 
**baseCurrency** | **String** | The underlying currency being traded. | 
**creationTimestamp** | **Number** | The time when the instrument was first created (milliseconds) | 
**expirationTimestamp** | **Number** | The time when the instrument will expire (milliseconds) | 



## Enum: QuoteCurrencyEnum


* `USD` (value: `"USD"`)





## Enum: KindEnum


* `future` (value: `"future"`)

* `option` (value: `"option"`)





## Enum: OptionTypeEnum


* `call` (value: `"call"`)

* `put` (value: `"put"`)





## Enum: SettlementPeriodEnum


* `month` (value: `"month"`)

* `week` (value: `"week"`)

* `perpetual` (value: `"perpetual"`)





## Enum: BaseCurrencyEnum


* `BTC` (value: `"BTC"`)

* `ETH` (value: `"ETH"`)




