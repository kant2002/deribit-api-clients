-module(openapi_order_id_initial_margin_pair).

-export([encode/1]).

-export_type([openapi_order_id_initial_margin_pair/0]).

-type openapi_order_id_initial_margin_pair() ::
    #{ 'order_id' := binary(),
       'initial_margin' := integer()
     }.

encode(#{ 'order_id' := OrderId,
          'initial_margin' := InitialMargin
        }) ->
    #{ 'order_id' => OrderId,
       'initial_margin' => InitialMargin
     }.
