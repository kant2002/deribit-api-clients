# openapi::Instrument

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**quote_currency** | **character** | The currency in which the instrument prices are quoted. | 
**kind** | **character** | Instrument kind, &#x60;\&quot;future\&quot;&#x60; or &#x60;\&quot;option\&quot;&#x60; | 
**tick_size** | **numeric** | specifies minimal price change and, as follows, the number of decimal places for instrument prices | 
**contract_size** | **integer** | Contract size for instrument | 
**is_active** | **character** | Indicates if the instrument can currently be traded. | 
**option_type** | **character** | The option type (only for options) | [optional] 
**min_trade_amount** | **numeric** | Minimum amount for trading. For perpetual and futures - in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH. | 
**instrument_name** | **character** | Unique instrument identifier | 
**settlement_period** | **character** | The settlement period. | 
**strike** | **numeric** | The strike value. (only for options) | [optional] 
**base_currency** | **character** | The underlying currency being traded. | 
**creation_timestamp** | **integer** | The time when the instrument was first created (milliseconds) | 
**expiration_timestamp** | **integer** | The time when the instrument will expire (milliseconds) | 


