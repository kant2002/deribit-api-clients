-module(openapi_currency_withdrawal_priorities).

-export([encode/1]).

-export_type([openapi_currency_withdrawal_priorities/0]).

-type openapi_currency_withdrawal_priorities() ::
    #{ 'name' := binary(),
       'value' := integer()
     }.

encode(#{ 'name' := Name,
          'value' := Value
        }) ->
    #{ 'name' => Name,
       'value' => Value
     }.
