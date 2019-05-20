-module(openapi_deposit).

-export([encode/1]).

-export_type([openapi_deposit/0]).

-type openapi_deposit() ::
    #{ 'updated_timestamp' := integer(),
       'state' := binary(),
       'received_timestamp' := integer(),
       'currency' := binary(),
       'address' := binary(),
       'amount' := integer(),
       'transaction_id' := binary()
     }.

encode(#{ 'updated_timestamp' := UpdatedTimestamp,
          'state' := State,
          'received_timestamp' := ReceivedTimestamp,
          'currency' := Currency,
          'address' := Address,
          'amount' := Amount,
          'transaction_id' := TransactionId
        }) ->
    #{ 'updated_timestamp' => UpdatedTimestamp,
       'state' => State,
       'received_timestamp' => ReceivedTimestamp,
       'currency' => Currency,
       'address' => Address,
       'amount' => Amount,
       'transaction_id' => TransactionId
     }.
