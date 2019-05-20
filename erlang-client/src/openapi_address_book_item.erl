-module(openapi_address_book_item).

-export([encode/1]).

-export_type([openapi_address_book_item/0]).

-type openapi_address_book_item() ::
    #{ 'currency' := binary(),
       'address' := binary(),
       'type' => binary(),
       'creation_timestamp' := integer()
     }.

encode(#{ 'currency' := Currency,
          'address' := Address,
          'type' := Type,
          'creation_timestamp' := CreationTimestamp
        }) ->
    #{ 'currency' => Currency,
       'address' => Address,
       'type' => Type,
       'creation_timestamp' => CreationTimestamp
     }.
