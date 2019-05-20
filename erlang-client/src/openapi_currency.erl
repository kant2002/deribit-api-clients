-module(openapi_currency).

-export([encode/1]).

-export_type([openapi_currency/0]).

-type openapi_currency() ::
    #{ 'min_confirmations' => integer(),
       'min_withdrawal_fee' => integer(),
       'disabled_deposit_address_creation' => boolean(),
       'currency' := binary(),
       'currency_long' := binary(),
       'withdrawal_fee' := integer(),
       'fee_precision' => integer(),
       'withdrawal_priorities' => list(),
       'coin_type' := binary()
     }.

encode(#{ 'min_confirmations' := MinConfirmations,
          'min_withdrawal_fee' := MinWithdrawalFee,
          'disabled_deposit_address_creation' := DisabledDepositAddressCreation,
          'currency' := Currency,
          'currency_long' := CurrencyLong,
          'withdrawal_fee' := WithdrawalFee,
          'fee_precision' := FeePrecision,
          'withdrawal_priorities' := WithdrawalPriorities,
          'coin_type' := CoinType
        }) ->
    #{ 'min_confirmations' => MinConfirmations,
       'min_withdrawal_fee' => MinWithdrawalFee,
       'disabled_deposit_address_creation' => DisabledDepositAddressCreation,
       'currency' => Currency,
       'currency_long' => CurrencyLong,
       'withdrawal_fee' => WithdrawalFee,
       'fee_precision' => FeePrecision,
       'withdrawal_priorities' => WithdrawalPriorities,
       'coin_type' => CoinType
     }.
