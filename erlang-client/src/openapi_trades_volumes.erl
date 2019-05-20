-module(openapi_trades_volumes).

-export([encode/1]).

-export_type([openapi_trades_volumes/0]).

-type openapi_trades_volumes() ::
    #{ 'calls_volume' := integer(),
       'puts_volume' := integer(),
       'currency_pair' := binary(),
       'futures_volume' := integer()
     }.

encode(#{ 'calls_volume' := CallsVolume,
          'puts_volume' := PutsVolume,
          'currency_pair' := CurrencyPair,
          'futures_volume' := FuturesVolume
        }) ->
    #{ 'calls_volume' => CallsVolume,
       'puts_volume' => PutsVolume,
       'currency_pair' => CurrencyPair,
       'futures_volume' => FuturesVolume
     }.
