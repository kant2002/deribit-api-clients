-module(openapi_key_number_pair).

-export([encode/1]).

-export_type([openapi_key_number_pair/0]).

-type openapi_key_number_pair() ::
    #{ 'name' := binary(),
       'value' := integer()
     }.

encode(#{ 'name' := Name,
          'value' := Value
        }) ->
    #{ 'name' => Name,
       'value' => Value
     }.
