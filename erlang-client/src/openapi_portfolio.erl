-module(openapi_portfolio).

-export([encode/1]).

-export_type([openapi_portfolio/0]).

-type openapi_portfolio() ::
    #{ 'eth' := openapi_portfolio_eth:openapi_portfolio_eth(),
       'btc' := openapi_portfolio_eth:openapi_portfolio_eth()
     }.

encode(#{ 'eth' := Eth,
          'btc' := Btc
        }) ->
    #{ 'eth' => Eth,
       'btc' => Btc
     }.
