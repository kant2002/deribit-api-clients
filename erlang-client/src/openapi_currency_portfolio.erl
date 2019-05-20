-module(openapi_currency_portfolio).

-export([encode/1]).

-export_type([openapi_currency_portfolio/0]).

-type openapi_currency_portfolio() ::
    #{ 'maintenance_margin' := integer(),
       'available_withdrawal_funds' := integer(),
       'initial_margin' := integer(),
       'available_funds' := integer(),
       'currency' := binary(),
       'margin_balance' := integer(),
       'equity' := integer(),
       'balance' := integer()
     }.

encode(#{ 'maintenance_margin' := MaintenanceMargin,
          'available_withdrawal_funds' := AvailableWithdrawalFunds,
          'initial_margin' := InitialMargin,
          'available_funds' := AvailableFunds,
          'currency' := Currency,
          'margin_balance' := MarginBalance,
          'equity' := Equity,
          'balance' := Balance
        }) ->
    #{ 'maintenance_margin' => MaintenanceMargin,
       'available_withdrawal_funds' => AvailableWithdrawalFunds,
       'initial_margin' => InitialMargin,
       'available_funds' => AvailableFunds,
       'currency' => Currency,
       'margin_balance' => MarginBalance,
       'equity' => Equity,
       'balance' => Balance
     }.
