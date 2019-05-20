-module(openapi_settlement).

-export([encode/1]).

-export_type([openapi_settlement/0]).

-type openapi_settlement() ::
    #{ 'session_profit_loss' := integer(),
       'mark_price' => integer(),
       'funding' := integer(),
       'socialized' => integer(),
       'session_bankrupcy' => integer(),
       'timestamp' := integer(),
       'profit_loss' => integer(),
       'funded' => integer(),
       'index_price' := integer(),
       'session_tax' => integer(),
       'session_tax_rate' => integer(),
       'instrument_name' := binary(),
       'position' := integer(),
       'type' := binary()
     }.

encode(#{ 'session_profit_loss' := SessionProfitLoss,
          'mark_price' := MarkPrice,
          'funding' := Funding,
          'socialized' := Socialized,
          'session_bankrupcy' := SessionBankrupcy,
          'timestamp' := Timestamp,
          'profit_loss' := ProfitLoss,
          'funded' := Funded,
          'index_price' := IndexPrice,
          'session_tax' := SessionTax,
          'session_tax_rate' := SessionTaxRate,
          'instrument_name' := InstrumentName,
          'position' := Position,
          'type' := Type
        }) ->
    #{ 'session_profit_loss' => SessionProfitLoss,
       'mark_price' => MarkPrice,
       'funding' => Funding,
       'socialized' => Socialized,
       'session_bankrupcy' => SessionBankrupcy,
       'timestamp' => Timestamp,
       'profit_loss' => ProfitLoss,
       'funded' => Funded,
       'index_price' => IndexPrice,
       'session_tax' => SessionTax,
       'session_tax_rate' => SessionTaxRate,
       'instrument_name' => InstrumentName,
       'position' => Position,
       'type' => Type
     }.
