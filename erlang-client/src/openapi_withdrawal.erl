-module(openapi_withdrawal).

-export([encode/1]).

-export_type([openapi_withdrawal/0]).

-type openapi_withdrawal() ::
    #{ 'updated_timestamp' := integer(),
       'fee' => integer(),
       'confirmed_timestamp' => integer(),
       'amount' := integer(),
       'priority' => integer(),
       'currency' := binary(),
       'state' := binary(),
       'address' := binary(),
       'created_timestamp' => integer(),
       'id' => integer(),
       'transaction_id' := binary()
     }.

encode(#{ 'updated_timestamp' := UpdatedTimestamp,
          'fee' := Fee,
          'confirmed_timestamp' := ConfirmedTimestamp,
          'amount' := Amount,
          'priority' := Priority,
          'currency' := Currency,
          'state' := State,
          'address' := Address,
          'created_timestamp' := CreatedTimestamp,
          'id' := Id,
          'transaction_id' := TransactionId
        }) ->
    #{ 'updated_timestamp' => UpdatedTimestamp,
       'fee' => Fee,
       'confirmed_timestamp' => ConfirmedTimestamp,
       'amount' => Amount,
       'priority' => Priority,
       'currency' => Currency,
       'state' => State,
       'address' => Address,
       'created_timestamp' => CreatedTimestamp,
       'id' => Id,
       'transaction_id' => TransactionId
     }.
