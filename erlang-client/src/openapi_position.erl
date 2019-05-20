-module(openapi_position).

-export([encode/1]).

-export_type([openapi_position/0]).

-type openapi_position() ::
    #{ 'direction' := binary(),
       'average_price_usd' => integer(),
       'estimated_liquidation_price' => integer(),
       'floating_profit_loss' := integer(),
       'floating_profit_loss_usd' => integer(),
       'open_orders_margin' := integer(),
       'total_profit_loss' := integer(),
       'realized_profit_loss' => integer(),
       'delta' := integer(),
       'initial_margin' := integer(),
       'size' := integer(),
       'maintenance_margin' := integer(),
       'kind' := binary(),
       'mark_price' := integer(),
       'average_price' := integer(),
       'settlement_price' := integer(),
       'index_price' := integer(),
       'instrument_name' := binary(),
       'size_currency' => integer()
     }.

encode(#{ 'direction' := Direction,
          'average_price_usd' := AveragePriceUsd,
          'estimated_liquidation_price' := EstimatedLiquidationPrice,
          'floating_profit_loss' := FloatingProfitLoss,
          'floating_profit_loss_usd' := FloatingProfitLossUsd,
          'open_orders_margin' := OpenOrdersMargin,
          'total_profit_loss' := TotalProfitLoss,
          'realized_profit_loss' := RealizedProfitLoss,
          'delta' := Delta,
          'initial_margin' := InitialMargin,
          'size' := Size,
          'maintenance_margin' := MaintenanceMargin,
          'kind' := Kind,
          'mark_price' := MarkPrice,
          'average_price' := AveragePrice,
          'settlement_price' := SettlementPrice,
          'index_price' := IndexPrice,
          'instrument_name' := InstrumentName,
          'size_currency' := SizeCurrency
        }) ->
    #{ 'direction' => Direction,
       'average_price_usd' => AveragePriceUsd,
       'estimated_liquidation_price' => EstimatedLiquidationPrice,
       'floating_profit_loss' => FloatingProfitLoss,
       'floating_profit_loss_usd' => FloatingProfitLossUsd,
       'open_orders_margin' => OpenOrdersMargin,
       'total_profit_loss' => TotalProfitLoss,
       'realized_profit_loss' => RealizedProfitLoss,
       'delta' => Delta,
       'initial_margin' => InitialMargin,
       'size' => Size,
       'maintenance_margin' => MaintenanceMargin,
       'kind' => Kind,
       'mark_price' => MarkPrice,
       'average_price' => AveragePrice,
       'settlement_price' => SettlementPrice,
       'index_price' => IndexPrice,
       'instrument_name' => InstrumentName,
       'size_currency' => SizeCurrency
     }.
