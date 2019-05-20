

# Instrument

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**quoteCurrency** | [**QuoteCurrencyEnum**](#QuoteCurrencyEnum) | The currency in which the instrument prices are quoted. | 
**kind** | [**KindEnum**](#KindEnum) | Instrument kind, &#x60;\&quot;future\&quot;&#x60; or &#x60;\&quot;option\&quot;&#x60; | 
**tickSize** | [**BigDecimal**](BigDecimal.md) | specifies minimal price change and, as follows, the number of decimal places for instrument prices | 
**contractSize** | **Integer** | Contract size for instrument | 
**isActive** | **Boolean** | Indicates if the instrument can currently be traded. | 
**optionType** | [**OptionTypeEnum**](#OptionTypeEnum) | The option type (only for options) |  [optional]
**minTradeAmount** | [**BigDecimal**](BigDecimal.md) | Minimum amount for trading. For perpetual and futures - in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH. | 
**instrumentName** | **String** | Unique instrument identifier | 
**settlementPeriod** | [**SettlementPeriodEnum**](#SettlementPeriodEnum) | The settlement period. | 
**strike** | [**BigDecimal**](BigDecimal.md) | The strike value. (only for options) |  [optional]
**baseCurrency** | [**BaseCurrencyEnum**](#BaseCurrencyEnum) | The underlying currency being traded. | 
**creationTimestamp** | **Integer** | The time when the instrument was first created (milliseconds) | 
**expirationTimestamp** | **Integer** | The time when the instrument will expire (milliseconds) | 



## Enum: QuoteCurrencyEnum

Name | Value
---- | -----
USD | &quot;USD&quot;



## Enum: KindEnum

Name | Value
---- | -----
FUTURE | &quot;future&quot;
OPTION | &quot;option&quot;



## Enum: OptionTypeEnum

Name | Value
---- | -----
CALL | &quot;call&quot;
PUT | &quot;put&quot;



## Enum: SettlementPeriodEnum

Name | Value
---- | -----
MONTH | &quot;month&quot;
WEEK | &quot;week&quot;
PERPETUAL | &quot;perpetual&quot;



## Enum: BaseCurrencyEnum

Name | Value
---- | -----
BTC | &quot;BTC&quot;
ETH | &quot;ETH&quot;



