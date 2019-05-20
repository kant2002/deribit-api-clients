-module(openapi_public_trade).

-export([encode/1]).

-export_type([openapi_public_trade/0]).

-type openapi_public_trade() ::
    #{ 'direction' := binary(),
       'tick_direction' := integer(),
       'timestamp' := integer(),
       'price' := integer(),
       'trade_seq' := integer(),
       'trade_id' := binary(),
       'iv' => integer(),
       'index_price' := integer(),
       'amount' := integer(),
       'instrument_name' := binary()
     }.

encode(#{ 'direction' := Direction,
          'tick_direction' := TickDirection,
          'timestamp' := Timestamp,
          'price' := Price,
          'trade_seq' := TradeSeq,
          'trade_id' := TradeId,
          'iv' := Iv,
          'index_price' := IndexPrice,
          'amount' := Amount,
          'instrument_name' := InstrumentName
        }) ->
    #{ 'direction' => Direction,
       'tick_direction' => TickDirection,
       'timestamp' => Timestamp,
       'price' => Price,
       'trade_seq' => TradeSeq,
       'trade_id' => TradeId,
       'iv' => Iv,
       'index_price' => IndexPrice,
       'amount' => Amount,
       'instrument_name' => InstrumentName
     }.
