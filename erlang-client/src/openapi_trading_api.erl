-module(openapi_trading_api).

-export([private_buy_get/3, private_buy_get/4,
         private_cancel_all_by_currency_get/2, private_cancel_all_by_currency_get/3,
         private_cancel_all_by_instrument_get/2, private_cancel_all_by_instrument_get/3,
         private_cancel_all_get/1, private_cancel_all_get/2,
         private_cancel_get/2, private_cancel_get/3,
         private_close_position_get/3, private_close_position_get/4,
         private_edit_get/4, private_edit_get/5,
         private_get_margins_get/4, private_get_margins_get/5,
         private_get_open_orders_by_currency_get/2, private_get_open_orders_by_currency_get/3,
         private_get_open_orders_by_instrument_get/2, private_get_open_orders_by_instrument_get/3,
         private_get_order_history_by_currency_get/2, private_get_order_history_by_currency_get/3,
         private_get_order_history_by_instrument_get/2, private_get_order_history_by_instrument_get/3,
         private_get_order_margin_by_ids_get/2, private_get_order_margin_by_ids_get/3,
         private_get_order_state_get/2, private_get_order_state_get/3,
         private_get_settlement_history_by_currency_get/2, private_get_settlement_history_by_currency_get/3,
         private_get_settlement_history_by_instrument_get/2, private_get_settlement_history_by_instrument_get/3,
         private_get_user_trades_by_currency_and_time_get/4, private_get_user_trades_by_currency_and_time_get/5,
         private_get_user_trades_by_currency_get/2, private_get_user_trades_by_currency_get/3,
         private_get_user_trades_by_instrument_and_time_get/4, private_get_user_trades_by_instrument_and_time_get/5,
         private_get_user_trades_by_instrument_get/2, private_get_user_trades_by_instrument_get/3,
         private_get_user_trades_by_order_get/2, private_get_user_trades_by_order_get/3,
         private_sell_get/3, private_sell_get/4]).

-define(BASE_URL, "/api/v2").

%% @doc Places a buy order for an instrument.
%% 
-spec private_buy_get(ctx:ctx(), binary(), integer()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
private_buy_get(Ctx, InstrumentName, Amount) ->
    private_buy_get(Ctx, InstrumentName, Amount, #{}).

-spec private_buy_get(ctx:ctx(), binary(), integer(), maps:map()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
private_buy_get(Ctx, InstrumentName, Amount, Optional) ->
    _OptionalParams = maps:get(params, Optional, #{}),
    Cfg = maps:get(cfg, Optional, application:get_env(kuberl, config, #{})),

    Method = get,
    Path = ["/private/buy"],
    QS = lists:flatten([{<<"instrument_name">>, InstrumentName}, {<<"amount">>, Amount}])++openapi_utils:optional_params(['type', 'label', 'price', 'time_in_force', 'max_show', 'post_only', 'reduce_only', 'stop_price', 'trigger', 'advanced'], _OptionalParams),
    Headers = [],
    Body1 = [],
    ContentTypeHeader = openapi_utils:select_header_content_type([]),
    Opts = maps:get(hackney_opts, Optional, []),

    openapi_utils:request(Ctx, Method, [?BASE_URL, Path], QS, ContentTypeHeader++Headers, Body1, Opts, Cfg).

%% @doc Cancels all orders by currency, optionally filtered by instrument kind and/or order type.
%% 
-spec private_cancel_all_by_currency_get(ctx:ctx(), binary()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
private_cancel_all_by_currency_get(Ctx, Currency) ->
    private_cancel_all_by_currency_get(Ctx, Currency, #{}).

-spec private_cancel_all_by_currency_get(ctx:ctx(), binary(), maps:map()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
private_cancel_all_by_currency_get(Ctx, Currency, Optional) ->
    _OptionalParams = maps:get(params, Optional, #{}),
    Cfg = maps:get(cfg, Optional, application:get_env(kuberl, config, #{})),

    Method = get,
    Path = ["/private/cancel_all_by_currency"],
    QS = lists:flatten([{<<"currency">>, Currency}])++openapi_utils:optional_params(['kind', 'type'], _OptionalParams),
    Headers = [],
    Body1 = [],
    ContentTypeHeader = openapi_utils:select_header_content_type([]),
    Opts = maps:get(hackney_opts, Optional, []),

    openapi_utils:request(Ctx, Method, [?BASE_URL, Path], QS, ContentTypeHeader++Headers, Body1, Opts, Cfg).

%% @doc Cancels all orders by instrument, optionally filtered by order type.
%% 
-spec private_cancel_all_by_instrument_get(ctx:ctx(), binary()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
private_cancel_all_by_instrument_get(Ctx, InstrumentName) ->
    private_cancel_all_by_instrument_get(Ctx, InstrumentName, #{}).

-spec private_cancel_all_by_instrument_get(ctx:ctx(), binary(), maps:map()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
private_cancel_all_by_instrument_get(Ctx, InstrumentName, Optional) ->
    _OptionalParams = maps:get(params, Optional, #{}),
    Cfg = maps:get(cfg, Optional, application:get_env(kuberl, config, #{})),

    Method = get,
    Path = ["/private/cancel_all_by_instrument"],
    QS = lists:flatten([{<<"instrument_name">>, InstrumentName}])++openapi_utils:optional_params(['type'], _OptionalParams),
    Headers = [],
    Body1 = [],
    ContentTypeHeader = openapi_utils:select_header_content_type([]),
    Opts = maps:get(hackney_opts, Optional, []),

    openapi_utils:request(Ctx, Method, [?BASE_URL, Path], QS, ContentTypeHeader++Headers, Body1, Opts, Cfg).

%% @doc This method cancels all users orders and stop orders within all currencies and instrument kinds.
%% 
-spec private_cancel_all_get(ctx:ctx()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
private_cancel_all_get(Ctx) ->
    private_cancel_all_get(Ctx, #{}).

-spec private_cancel_all_get(ctx:ctx(), maps:map()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
private_cancel_all_get(Ctx, Optional) ->
    _OptionalParams = maps:get(params, Optional, #{}),
    Cfg = maps:get(cfg, Optional, application:get_env(kuberl, config, #{})),

    Method = get,
    Path = ["/private/cancel_all"],
    QS = [],
    Headers = [],
    Body1 = [],
    ContentTypeHeader = openapi_utils:select_header_content_type([]),
    Opts = maps:get(hackney_opts, Optional, []),

    openapi_utils:request(Ctx, Method, [?BASE_URL, Path], QS, ContentTypeHeader++Headers, Body1, Opts, Cfg).

%% @doc Cancel an order, specified by order id
%% 
-spec private_cancel_get(ctx:ctx(), binary()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
private_cancel_get(Ctx, OrderId) ->
    private_cancel_get(Ctx, OrderId, #{}).

-spec private_cancel_get(ctx:ctx(), binary(), maps:map()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
private_cancel_get(Ctx, OrderId, Optional) ->
    _OptionalParams = maps:get(params, Optional, #{}),
    Cfg = maps:get(cfg, Optional, application:get_env(kuberl, config, #{})),

    Method = get,
    Path = ["/private/cancel"],
    QS = lists:flatten([{<<"order_id">>, OrderId}])++openapi_utils:optional_params([], _OptionalParams),
    Headers = [],
    Body1 = [],
    ContentTypeHeader = openapi_utils:select_header_content_type([]),
    Opts = maps:get(hackney_opts, Optional, []),

    openapi_utils:request(Ctx, Method, [?BASE_URL, Path], QS, ContentTypeHeader++Headers, Body1, Opts, Cfg).

%% @doc Makes closing position reduce only order .
%% 
-spec private_close_position_get(ctx:ctx(), binary(), binary()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
private_close_position_get(Ctx, InstrumentName, Type) ->
    private_close_position_get(Ctx, InstrumentName, Type, #{}).

-spec private_close_position_get(ctx:ctx(), binary(), binary(), maps:map()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
private_close_position_get(Ctx, InstrumentName, Type, Optional) ->
    _OptionalParams = maps:get(params, Optional, #{}),
    Cfg = maps:get(cfg, Optional, application:get_env(kuberl, config, #{})),

    Method = get,
    Path = ["/private/close_position"],
    QS = lists:flatten([{<<"instrument_name">>, InstrumentName}, {<<"type">>, Type}])++openapi_utils:optional_params(['price'], _OptionalParams),
    Headers = [],
    Body1 = [],
    ContentTypeHeader = openapi_utils:select_header_content_type([]),
    Opts = maps:get(hackney_opts, Optional, []),

    openapi_utils:request(Ctx, Method, [?BASE_URL, Path], QS, ContentTypeHeader++Headers, Body1, Opts, Cfg).

%% @doc Change price, amount and/or other properties of an order.
%% 
-spec private_edit_get(ctx:ctx(), binary(), integer(), integer()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
private_edit_get(Ctx, OrderId, Amount, Price) ->
    private_edit_get(Ctx, OrderId, Amount, Price, #{}).

-spec private_edit_get(ctx:ctx(), binary(), integer(), integer(), maps:map()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
private_edit_get(Ctx, OrderId, Amount, Price, Optional) ->
    _OptionalParams = maps:get(params, Optional, #{}),
    Cfg = maps:get(cfg, Optional, application:get_env(kuberl, config, #{})),

    Method = get,
    Path = ["/private/edit"],
    QS = lists:flatten([{<<"order_id">>, OrderId}, {<<"amount">>, Amount}, {<<"price">>, Price}])++openapi_utils:optional_params(['post_only', 'advanced', 'stop_price'], _OptionalParams),
    Headers = [],
    Body1 = [],
    ContentTypeHeader = openapi_utils:select_header_content_type([]),
    Opts = maps:get(hackney_opts, Optional, []),

    openapi_utils:request(Ctx, Method, [?BASE_URL, Path], QS, ContentTypeHeader++Headers, Body1, Opts, Cfg).

%% @doc Get margins for given instrument, amount and price.
%% 
-spec private_get_margins_get(ctx:ctx(), binary(), integer(), integer()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
private_get_margins_get(Ctx, InstrumentName, Amount, Price) ->
    private_get_margins_get(Ctx, InstrumentName, Amount, Price, #{}).

-spec private_get_margins_get(ctx:ctx(), binary(), integer(), integer(), maps:map()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
private_get_margins_get(Ctx, InstrumentName, Amount, Price, Optional) ->
    _OptionalParams = maps:get(params, Optional, #{}),
    Cfg = maps:get(cfg, Optional, application:get_env(kuberl, config, #{})),

    Method = get,
    Path = ["/private/get_margins"],
    QS = lists:flatten([{<<"instrument_name">>, InstrumentName}, {<<"amount">>, Amount}, {<<"price">>, Price}])++openapi_utils:optional_params([], _OptionalParams),
    Headers = [],
    Body1 = [],
    ContentTypeHeader = openapi_utils:select_header_content_type([]),
    Opts = maps:get(hackney_opts, Optional, []),

    openapi_utils:request(Ctx, Method, [?BASE_URL, Path], QS, ContentTypeHeader++Headers, Body1, Opts, Cfg).

%% @doc Retrieves list of user's open orders.
%% 
-spec private_get_open_orders_by_currency_get(ctx:ctx(), binary()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
private_get_open_orders_by_currency_get(Ctx, Currency) ->
    private_get_open_orders_by_currency_get(Ctx, Currency, #{}).

-spec private_get_open_orders_by_currency_get(ctx:ctx(), binary(), maps:map()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
private_get_open_orders_by_currency_get(Ctx, Currency, Optional) ->
    _OptionalParams = maps:get(params, Optional, #{}),
    Cfg = maps:get(cfg, Optional, application:get_env(kuberl, config, #{})),

    Method = get,
    Path = ["/private/get_open_orders_by_currency"],
    QS = lists:flatten([{<<"currency">>, Currency}])++openapi_utils:optional_params(['kind', 'type'], _OptionalParams),
    Headers = [],
    Body1 = [],
    ContentTypeHeader = openapi_utils:select_header_content_type([]),
    Opts = maps:get(hackney_opts, Optional, []),

    openapi_utils:request(Ctx, Method, [?BASE_URL, Path], QS, ContentTypeHeader++Headers, Body1, Opts, Cfg).

%% @doc Retrieves list of user's open orders within given Instrument.
%% 
-spec private_get_open_orders_by_instrument_get(ctx:ctx(), binary()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
private_get_open_orders_by_instrument_get(Ctx, InstrumentName) ->
    private_get_open_orders_by_instrument_get(Ctx, InstrumentName, #{}).

-spec private_get_open_orders_by_instrument_get(ctx:ctx(), binary(), maps:map()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
private_get_open_orders_by_instrument_get(Ctx, InstrumentName, Optional) ->
    _OptionalParams = maps:get(params, Optional, #{}),
    Cfg = maps:get(cfg, Optional, application:get_env(kuberl, config, #{})),

    Method = get,
    Path = ["/private/get_open_orders_by_instrument"],
    QS = lists:flatten([{<<"instrument_name">>, InstrumentName}])++openapi_utils:optional_params(['type'], _OptionalParams),
    Headers = [],
    Body1 = [],
    ContentTypeHeader = openapi_utils:select_header_content_type([]),
    Opts = maps:get(hackney_opts, Optional, []),

    openapi_utils:request(Ctx, Method, [?BASE_URL, Path], QS, ContentTypeHeader++Headers, Body1, Opts, Cfg).

%% @doc Retrieves history of orders that have been partially or fully filled.
%% 
-spec private_get_order_history_by_currency_get(ctx:ctx(), binary()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
private_get_order_history_by_currency_get(Ctx, Currency) ->
    private_get_order_history_by_currency_get(Ctx, Currency, #{}).

-spec private_get_order_history_by_currency_get(ctx:ctx(), binary(), maps:map()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
private_get_order_history_by_currency_get(Ctx, Currency, Optional) ->
    _OptionalParams = maps:get(params, Optional, #{}),
    Cfg = maps:get(cfg, Optional, application:get_env(kuberl, config, #{})),

    Method = get,
    Path = ["/private/get_order_history_by_currency"],
    QS = lists:flatten([{<<"currency">>, Currency}])++openapi_utils:optional_params(['kind', 'count', 'offset', 'include_old', 'include_unfilled'], _OptionalParams),
    Headers = [],
    Body1 = [],
    ContentTypeHeader = openapi_utils:select_header_content_type([]),
    Opts = maps:get(hackney_opts, Optional, []),

    openapi_utils:request(Ctx, Method, [?BASE_URL, Path], QS, ContentTypeHeader++Headers, Body1, Opts, Cfg).

%% @doc Retrieves history of orders that have been partially or fully filled.
%% 
-spec private_get_order_history_by_instrument_get(ctx:ctx(), binary()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
private_get_order_history_by_instrument_get(Ctx, InstrumentName) ->
    private_get_order_history_by_instrument_get(Ctx, InstrumentName, #{}).

-spec private_get_order_history_by_instrument_get(ctx:ctx(), binary(), maps:map()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
private_get_order_history_by_instrument_get(Ctx, InstrumentName, Optional) ->
    _OptionalParams = maps:get(params, Optional, #{}),
    Cfg = maps:get(cfg, Optional, application:get_env(kuberl, config, #{})),

    Method = get,
    Path = ["/private/get_order_history_by_instrument"],
    QS = lists:flatten([{<<"instrument_name">>, InstrumentName}])++openapi_utils:optional_params(['count', 'offset', 'include_old', 'include_unfilled'], _OptionalParams),
    Headers = [],
    Body1 = [],
    ContentTypeHeader = openapi_utils:select_header_content_type([]),
    Opts = maps:get(hackney_opts, Optional, []),

    openapi_utils:request(Ctx, Method, [?BASE_URL, Path], QS, ContentTypeHeader++Headers, Body1, Opts, Cfg).

%% @doc Retrieves initial margins of given orders
%% 
-spec private_get_order_margin_by_ids_get(ctx:ctx(), list()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
private_get_order_margin_by_ids_get(Ctx, Ids) ->
    private_get_order_margin_by_ids_get(Ctx, Ids, #{}).

-spec private_get_order_margin_by_ids_get(ctx:ctx(), list(), maps:map()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
private_get_order_margin_by_ids_get(Ctx, Ids, Optional) ->
    _OptionalParams = maps:get(params, Optional, #{}),
    Cfg = maps:get(cfg, Optional, application:get_env(kuberl, config, #{})),

    Method = get,
    Path = ["/private/get_order_margin_by_ids"],
    QS = lists:flatten([[{<<"ids">>, X} || X <- Ids]])++openapi_utils:optional_params([], _OptionalParams),
    Headers = [],
    Body1 = [],
    ContentTypeHeader = openapi_utils:select_header_content_type([]),
    Opts = maps:get(hackney_opts, Optional, []),

    openapi_utils:request(Ctx, Method, [?BASE_URL, Path], QS, ContentTypeHeader++Headers, Body1, Opts, Cfg).

%% @doc Retrieve the current state of an order.
%% 
-spec private_get_order_state_get(ctx:ctx(), binary()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
private_get_order_state_get(Ctx, OrderId) ->
    private_get_order_state_get(Ctx, OrderId, #{}).

-spec private_get_order_state_get(ctx:ctx(), binary(), maps:map()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
private_get_order_state_get(Ctx, OrderId, Optional) ->
    _OptionalParams = maps:get(params, Optional, #{}),
    Cfg = maps:get(cfg, Optional, application:get_env(kuberl, config, #{})),

    Method = get,
    Path = ["/private/get_order_state"],
    QS = lists:flatten([{<<"order_id">>, OrderId}])++openapi_utils:optional_params([], _OptionalParams),
    Headers = [],
    Body1 = [],
    ContentTypeHeader = openapi_utils:select_header_content_type([]),
    Opts = maps:get(hackney_opts, Optional, []),

    openapi_utils:request(Ctx, Method, [?BASE_URL, Path], QS, ContentTypeHeader++Headers, Body1, Opts, Cfg).

%% @doc Retrieves settlement, delivery and bankruptcy events that have affected your account.
%% 
-spec private_get_settlement_history_by_currency_get(ctx:ctx(), binary()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
private_get_settlement_history_by_currency_get(Ctx, Currency) ->
    private_get_settlement_history_by_currency_get(Ctx, Currency, #{}).

-spec private_get_settlement_history_by_currency_get(ctx:ctx(), binary(), maps:map()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
private_get_settlement_history_by_currency_get(Ctx, Currency, Optional) ->
    _OptionalParams = maps:get(params, Optional, #{}),
    Cfg = maps:get(cfg, Optional, application:get_env(kuberl, config, #{})),

    Method = get,
    Path = ["/private/get_settlement_history_by_currency"],
    QS = lists:flatten([{<<"currency">>, Currency}])++openapi_utils:optional_params(['type', 'count'], _OptionalParams),
    Headers = [],
    Body1 = [],
    ContentTypeHeader = openapi_utils:select_header_content_type([]),
    Opts = maps:get(hackney_opts, Optional, []),

    openapi_utils:request(Ctx, Method, [?BASE_URL, Path], QS, ContentTypeHeader++Headers, Body1, Opts, Cfg).

%% @doc Retrieves public settlement, delivery and bankruptcy events filtered by instrument name
%% 
-spec private_get_settlement_history_by_instrument_get(ctx:ctx(), binary()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
private_get_settlement_history_by_instrument_get(Ctx, InstrumentName) ->
    private_get_settlement_history_by_instrument_get(Ctx, InstrumentName, #{}).

-spec private_get_settlement_history_by_instrument_get(ctx:ctx(), binary(), maps:map()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
private_get_settlement_history_by_instrument_get(Ctx, InstrumentName, Optional) ->
    _OptionalParams = maps:get(params, Optional, #{}),
    Cfg = maps:get(cfg, Optional, application:get_env(kuberl, config, #{})),

    Method = get,
    Path = ["/private/get_settlement_history_by_instrument"],
    QS = lists:flatten([{<<"instrument_name">>, InstrumentName}])++openapi_utils:optional_params(['type', 'count'], _OptionalParams),
    Headers = [],
    Body1 = [],
    ContentTypeHeader = openapi_utils:select_header_content_type([]),
    Opts = maps:get(hackney_opts, Optional, []),

    openapi_utils:request(Ctx, Method, [?BASE_URL, Path], QS, ContentTypeHeader++Headers, Body1, Opts, Cfg).

%% @doc Retrieve the latest user trades that have occurred for instruments in a specific currency symbol and within given time range.
%% 
-spec private_get_user_trades_by_currency_and_time_get(ctx:ctx(), binary(), integer(), integer()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
private_get_user_trades_by_currency_and_time_get(Ctx, Currency, StartTimestamp, EndTimestamp) ->
    private_get_user_trades_by_currency_and_time_get(Ctx, Currency, StartTimestamp, EndTimestamp, #{}).

-spec private_get_user_trades_by_currency_and_time_get(ctx:ctx(), binary(), integer(), integer(), maps:map()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
private_get_user_trades_by_currency_and_time_get(Ctx, Currency, StartTimestamp, EndTimestamp, Optional) ->
    _OptionalParams = maps:get(params, Optional, #{}),
    Cfg = maps:get(cfg, Optional, application:get_env(kuberl, config, #{})),

    Method = get,
    Path = ["/private/get_user_trades_by_currency_and_time"],
    QS = lists:flatten([{<<"currency">>, Currency}, {<<"start_timestamp">>, StartTimestamp}, {<<"end_timestamp">>, EndTimestamp}])++openapi_utils:optional_params(['kind', 'count', 'include_old', 'sorting'], _OptionalParams),
    Headers = [],
    Body1 = [],
    ContentTypeHeader = openapi_utils:select_header_content_type([]),
    Opts = maps:get(hackney_opts, Optional, []),

    openapi_utils:request(Ctx, Method, [?BASE_URL, Path], QS, ContentTypeHeader++Headers, Body1, Opts, Cfg).

%% @doc Retrieve the latest user trades that have occurred for instruments in a specific currency symbol.
%% 
-spec private_get_user_trades_by_currency_get(ctx:ctx(), binary()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
private_get_user_trades_by_currency_get(Ctx, Currency) ->
    private_get_user_trades_by_currency_get(Ctx, Currency, #{}).

-spec private_get_user_trades_by_currency_get(ctx:ctx(), binary(), maps:map()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
private_get_user_trades_by_currency_get(Ctx, Currency, Optional) ->
    _OptionalParams = maps:get(params, Optional, #{}),
    Cfg = maps:get(cfg, Optional, application:get_env(kuberl, config, #{})),

    Method = get,
    Path = ["/private/get_user_trades_by_currency"],
    QS = lists:flatten([{<<"currency">>, Currency}])++openapi_utils:optional_params(['kind', 'start_id', 'end_id', 'count', 'include_old', 'sorting'], _OptionalParams),
    Headers = [],
    Body1 = [],
    ContentTypeHeader = openapi_utils:select_header_content_type([]),
    Opts = maps:get(hackney_opts, Optional, []),

    openapi_utils:request(Ctx, Method, [?BASE_URL, Path], QS, ContentTypeHeader++Headers, Body1, Opts, Cfg).

%% @doc Retrieve the latest user trades that have occurred for a specific instrument and within given time range.
%% 
-spec private_get_user_trades_by_instrument_and_time_get(ctx:ctx(), binary(), integer(), integer()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
private_get_user_trades_by_instrument_and_time_get(Ctx, InstrumentName, StartTimestamp, EndTimestamp) ->
    private_get_user_trades_by_instrument_and_time_get(Ctx, InstrumentName, StartTimestamp, EndTimestamp, #{}).

-spec private_get_user_trades_by_instrument_and_time_get(ctx:ctx(), binary(), integer(), integer(), maps:map()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
private_get_user_trades_by_instrument_and_time_get(Ctx, InstrumentName, StartTimestamp, EndTimestamp, Optional) ->
    _OptionalParams = maps:get(params, Optional, #{}),
    Cfg = maps:get(cfg, Optional, application:get_env(kuberl, config, #{})),

    Method = get,
    Path = ["/private/get_user_trades_by_instrument_and_time"],
    QS = lists:flatten([{<<"instrument_name">>, InstrumentName}, {<<"start_timestamp">>, StartTimestamp}, {<<"end_timestamp">>, EndTimestamp}])++openapi_utils:optional_params(['count', 'include_old', 'sorting'], _OptionalParams),
    Headers = [],
    Body1 = [],
    ContentTypeHeader = openapi_utils:select_header_content_type([]),
    Opts = maps:get(hackney_opts, Optional, []),

    openapi_utils:request(Ctx, Method, [?BASE_URL, Path], QS, ContentTypeHeader++Headers, Body1, Opts, Cfg).

%% @doc Retrieve the latest user trades that have occurred for a specific instrument.
%% 
-spec private_get_user_trades_by_instrument_get(ctx:ctx(), binary()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
private_get_user_trades_by_instrument_get(Ctx, InstrumentName) ->
    private_get_user_trades_by_instrument_get(Ctx, InstrumentName, #{}).

-spec private_get_user_trades_by_instrument_get(ctx:ctx(), binary(), maps:map()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
private_get_user_trades_by_instrument_get(Ctx, InstrumentName, Optional) ->
    _OptionalParams = maps:get(params, Optional, #{}),
    Cfg = maps:get(cfg, Optional, application:get_env(kuberl, config, #{})),

    Method = get,
    Path = ["/private/get_user_trades_by_instrument"],
    QS = lists:flatten([{<<"instrument_name">>, InstrumentName}])++openapi_utils:optional_params(['start_seq', 'end_seq', 'count', 'include_old', 'sorting'], _OptionalParams),
    Headers = [],
    Body1 = [],
    ContentTypeHeader = openapi_utils:select_header_content_type([]),
    Opts = maps:get(hackney_opts, Optional, []),

    openapi_utils:request(Ctx, Method, [?BASE_URL, Path], QS, ContentTypeHeader++Headers, Body1, Opts, Cfg).

%% @doc Retrieve the list of user trades that was created for given order
%% 
-spec private_get_user_trades_by_order_get(ctx:ctx(), binary()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
private_get_user_trades_by_order_get(Ctx, OrderId) ->
    private_get_user_trades_by_order_get(Ctx, OrderId, #{}).

-spec private_get_user_trades_by_order_get(ctx:ctx(), binary(), maps:map()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
private_get_user_trades_by_order_get(Ctx, OrderId, Optional) ->
    _OptionalParams = maps:get(params, Optional, #{}),
    Cfg = maps:get(cfg, Optional, application:get_env(kuberl, config, #{})),

    Method = get,
    Path = ["/private/get_user_trades_by_order"],
    QS = lists:flatten([{<<"order_id">>, OrderId}])++openapi_utils:optional_params(['sorting'], _OptionalParams),
    Headers = [],
    Body1 = [],
    ContentTypeHeader = openapi_utils:select_header_content_type([]),
    Opts = maps:get(hackney_opts, Optional, []),

    openapi_utils:request(Ctx, Method, [?BASE_URL, Path], QS, ContentTypeHeader++Headers, Body1, Opts, Cfg).

%% @doc Places a sell order for an instrument.
%% 
-spec private_sell_get(ctx:ctx(), binary(), integer()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
private_sell_get(Ctx, InstrumentName, Amount) ->
    private_sell_get(Ctx, InstrumentName, Amount, #{}).

-spec private_sell_get(ctx:ctx(), binary(), integer(), maps:map()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
private_sell_get(Ctx, InstrumentName, Amount, Optional) ->
    _OptionalParams = maps:get(params, Optional, #{}),
    Cfg = maps:get(cfg, Optional, application:get_env(kuberl, config, #{})),

    Method = get,
    Path = ["/private/sell"],
    QS = lists:flatten([{<<"instrument_name">>, InstrumentName}, {<<"amount">>, Amount}])++openapi_utils:optional_params(['type', 'label', 'price', 'time_in_force', 'max_show', 'post_only', 'reduce_only', 'stop_price', 'trigger', 'advanced'], _OptionalParams),
    Headers = [],
    Body1 = [],
    ContentTypeHeader = openapi_utils:select_header_content_type([]),
    Opts = maps:get(hackney_opts, Optional, []),

    openapi_utils:request(Ctx, Method, [?BASE_URL, Path], QS, ContentTypeHeader++Headers, Body1, Opts, Cfg).


