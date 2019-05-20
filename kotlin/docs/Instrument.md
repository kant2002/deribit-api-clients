
# Instrument

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**quoteCurrency** | [**inline**](#QuoteCurrencyEnum) | The currency in which the instrument prices are quoted. | 
**kind** | [**inline**](#KindEnum) | Instrument kind, &#x60;\&quot;future\&quot;&#x60; or &#x60;\&quot;option\&quot;&#x60; | 
**tickSize** | [**java.math.BigDecimal**](java.math.BigDecimal.md) | specifies minimal price change and, as follows, the number of decimal places for instrument prices | 
**contractSize** | **kotlin.Int** | Contract size for instrument | 
**isActive** | **kotlin.Boolean** | Indicates if the instrument can currently be traded. | 
**optionType** | [**inline**](#OptionTypeEnum) | The option type (only for options) |  [optional]
**minTradeAmount** | [**java.math.BigDecimal**](java.math.BigDecimal.md) | Minimum amount for trading. For perpetual and futures - in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH. | 
**instrumentName** | **kotlin.String** | Unique instrument identifier | 
**settlementPeriod** | [**inline**](#SettlementPeriodEnum) | The settlement period. | 
**strike** | [**java.math.BigDecimal**](java.math.BigDecimal.md) | The strike value. (only for options) |  [optional]
**baseCurrency** | [**inline**](#BaseCurrencyEnum) | The underlying currency being traded. | 
**creationTimestamp** | **kotlin.Int** | The time when the instrument was first created (milliseconds) | 
**expirationTimestamp** | **kotlin.Int** | The time when the instrument will expire (milliseconds) | 


<a name="QuoteCurrencyEnum"></a>
## Enum: quote_currency
Name | Value
---- | -----
quoteCurrency | USD


<a name="KindEnum"></a>
## Enum: kind
Name | Value
---- | -----
kind | future, option


<a name="OptionTypeEnum"></a>
## Enum: option_type
Name | Value
---- | -----
optionType | call, put


<a name="SettlementPeriodEnum"></a>
## Enum: settlement_period
Name | Value
---- | -----
settlementPeriod | month, week, perpetual


<a name="BaseCurrencyEnum"></a>
## Enum: base_currency
Name | Value
---- | -----
baseCurrency | BTC, ETH



