-module(openapi_instrument).

-export([encode/1]).

-export_type([openapi_instrument/0]).

-type openapi_instrument() ::
    #{ 'quote_currency' := binary(),
       'kind' := binary(),
       'tick_size' := integer(),
       'contract_size' := integer(),
       'is_active' := boolean(),
       'option_type' => binary(),
       'min_trade_amount' := integer(),
       'instrument_name' := binary(),
       'settlement_period' := binary(),
       'strike' => integer(),
       'base_currency' := binary(),
       'creation_timestamp' := integer(),
       'expiration_timestamp' := integer()
     }.

encode(#{ 'quote_currency' := QuoteCurrency,
          'kind' := Kind,
          'tick_size' := TickSize,
          'contract_size' := ContractSize,
          'is_active' := IsActive,
          'option_type' := OptionType,
          'min_trade_amount' := MinTradeAmount,
          'instrument_name' := InstrumentName,
          'settlement_period' := SettlementPeriod,
          'strike' := Strike,
          'base_currency' := BaseCurrency,
          'creation_timestamp' := CreationTimestamp,
          'expiration_timestamp' := ExpirationTimestamp
        }) ->
    #{ 'quote_currency' => QuoteCurrency,
       'kind' => Kind,
       'tick_size' => TickSize,
       'contract_size' => ContractSize,
       'is_active' => IsActive,
       'option_type' => OptionType,
       'min_trade_amount' => MinTradeAmount,
       'instrument_name' => InstrumentName,
       'settlement_period' => SettlementPeriod,
       'strike' => Strike,
       'base_currency' => BaseCurrency,
       'creation_timestamp' => CreationTimestamp,
       'expiration_timestamp' => ExpirationTimestamp
     }.
