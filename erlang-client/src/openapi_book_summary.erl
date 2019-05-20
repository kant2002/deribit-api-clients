-module(openapi_book_summary).

-export([encode/1]).

-export_type([openapi_book_summary/0]).

-type openapi_book_summary() ::
    #{ 'underlying_index' => binary(),
       'volume' := integer(),
       'volume_usd' => integer(),
       'underlying_price' => integer(),
       'bid_price' := integer(),
       'open_interest' := integer(),
       'quote_currency' := binary(),
       'high' := integer(),
       'estimated_delivery_price' => integer(),
       'last' := integer(),
       'mid_price' := integer(),
       'interest_rate' => integer(),
       'funding_8h' => integer(),
       'mark_price' := integer(),
       'ask_price' := integer(),
       'instrument_name' := binary(),
       'low' := integer(),
       'base_currency' => binary(),
       'creation_timestamp' := integer(),
       'current_funding' => integer()
     }.

encode(#{ 'underlying_index' := UnderlyingIndex,
          'volume' := Volume,
          'volume_usd' := VolumeUsd,
          'underlying_price' := UnderlyingPrice,
          'bid_price' := BidPrice,
          'open_interest' := OpenInterest,
          'quote_currency' := QuoteCurrency,
          'high' := High,
          'estimated_delivery_price' := EstimatedDeliveryPrice,
          'last' := Last,
          'mid_price' := MidPrice,
          'interest_rate' := InterestRate,
          'funding_8h' := Funding8h,
          'mark_price' := MarkPrice,
          'ask_price' := AskPrice,
          'instrument_name' := InstrumentName,
          'low' := Low,
          'base_currency' := BaseCurrency,
          'creation_timestamp' := CreationTimestamp,
          'current_funding' := CurrentFunding
        }) ->
    #{ 'underlying_index' => UnderlyingIndex,
       'volume' => Volume,
       'volume_usd' => VolumeUsd,
       'underlying_price' => UnderlyingPrice,
       'bid_price' => BidPrice,
       'open_interest' => OpenInterest,
       'quote_currency' => QuoteCurrency,
       'high' => High,
       'estimated_delivery_price' => EstimatedDeliveryPrice,
       'last' => Last,
       'mid_price' => MidPrice,
       'interest_rate' => InterestRate,
       'funding_8h' => Funding8h,
       'mark_price' => MarkPrice,
       'ask_price' => AskPrice,
       'instrument_name' => InstrumentName,
       'low' => Low,
       'base_currency' => BaseCurrency,
       'creation_timestamp' => CreationTimestamp,
       'current_funding' => CurrentFunding
     }.
