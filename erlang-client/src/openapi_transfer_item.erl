-module(openapi_transfer_item).

-export([encode/1]).

-export_type([openapi_transfer_item/0]).

-type openapi_transfer_item() ::
    #{ 'updated_timestamp' := integer(),
       'direction' => binary(),
       'amount' := integer(),
       'other_side' := binary(),
       'currency' := binary(),
       'state' := binary(),
       'created_timestamp' := integer(),
       'type' := binary(),
       'id' := integer()
     }.

encode(#{ 'updated_timestamp' := UpdatedTimestamp,
          'direction' := Direction,
          'amount' := Amount,
          'other_side' := OtherSide,
          'currency' := Currency,
          'state' := State,
          'created_timestamp' := CreatedTimestamp,
          'type' := Type,
          'id' := Id
        }) ->
    #{ 'updated_timestamp' => UpdatedTimestamp,
       'direction' => Direction,
       'amount' => Amount,
       'other_side' => OtherSide,
       'currency' => Currency,
       'state' => State,
       'created_timestamp' => CreatedTimestamp,
       'type' => Type,
       'id' => Id
     }.
