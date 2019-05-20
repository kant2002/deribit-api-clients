-module(openapi_user_trade).

-export([encode/1]).

-export_type([openapi_user_trade/0]).

-type openapi_user_trade() ::
    #{ 'direction' := binary(),
       'fee_currency' := binary(),
       'order_id' := binary(),
       'timestamp' := integer(),
       'price' := integer(),
       'iv' => integer(),
       'trade_id' := binary(),
       'fee' := integer(),
       'order_type' => binary(),
       'trade_seq' := integer(),
       'self_trade' := boolean(),
       'state' := binary(),
       'label' => binary(),
       'index_price' := integer(),
       'amount' := integer(),
       'instrument_name' := binary(),
       'tick_direction' := integer(),
       'matching_id' := binary(),
       'liquidity' => binary()
     }.

encode(#{ 'direction' := Direction,
          'fee_currency' := FeeCurrency,
          'order_id' := OrderId,
          'timestamp' := Timestamp,
          'price' := Price,
          'iv' := Iv,
          'trade_id' := TradeId,
          'fee' := Fee,
          'order_type' := OrderType,
          'trade_seq' := TradeSeq,
          'self_trade' := SelfTrade,
          'state' := State,
          'label' := Label,
          'index_price' := IndexPrice,
          'amount' := Amount,
          'instrument_name' := InstrumentName,
          'tick_direction' := TickDirection,
          'matching_id' := MatchingId,
          'liquidity' := Liquidity
        }) ->
    #{ 'direction' => Direction,
       'fee_currency' => FeeCurrency,
       'order_id' => OrderId,
       'timestamp' => Timestamp,
       'price' => Price,
       'iv' => Iv,
       'trade_id' => TradeId,
       'fee' => Fee,
       'order_type' => OrderType,
       'trade_seq' => TradeSeq,
       'self_trade' => SelfTrade,
       'state' => State,
       'label' => Label,
       'index_price' => IndexPrice,
       'amount' => Amount,
       'instrument_name' => InstrumentName,
       'tick_direction' => TickDirection,
       'matching_id' => MatchingId,
       'liquidity' => Liquidity
     }.
