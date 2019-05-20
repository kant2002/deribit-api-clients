-module(openapi_market_data_api).

-export([public_get_book_summary_by_currency_get/2, public_get_book_summary_by_currency_get/3,
         public_get_book_summary_by_instrument_get/2, public_get_book_summary_by_instrument_get/3,
         public_get_contract_size_get/2, public_get_contract_size_get/3,
         public_get_currencies_get/1, public_get_currencies_get/2,
         public_get_funding_chart_data_get/2, public_get_funding_chart_data_get/3,
         public_get_historical_volatility_get/2, public_get_historical_volatility_get/3,
         public_get_index_get/2, public_get_index_get/3,
         public_get_instruments_get/2, public_get_instruments_get/3,
         public_get_last_settlements_by_currency_get/2, public_get_last_settlements_by_currency_get/3,
         public_get_last_settlements_by_instrument_get/2, public_get_last_settlements_by_instrument_get/3,
         public_get_last_trades_by_currency_and_time_get/4, public_get_last_trades_by_currency_and_time_get/5,
         public_get_last_trades_by_currency_get/2, public_get_last_trades_by_currency_get/3,
         public_get_last_trades_by_instrument_and_time_get/4, public_get_last_trades_by_instrument_and_time_get/5,
         public_get_last_trades_by_instrument_get/2, public_get_last_trades_by_instrument_get/3,
         public_get_order_book_get/2, public_get_order_book_get/3,
         public_get_trade_volumes_get/1, public_get_trade_volumes_get/2,
         public_get_tradingview_chart_data_get/4, public_get_tradingview_chart_data_get/5,
         public_ticker_get/2, public_ticker_get/3]).

-define(BASE_URL, "/api/v2").

%% @doc Retrieves the summary information such as open interest, 24h volume, etc. for all instruments for the currency (optionally filtered by kind).
%% 
-spec public_get_book_summary_by_currency_get(ctx:ctx(), binary()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
public_get_book_summary_by_currency_get(Ctx, Currency) ->
    public_get_book_summary_by_currency_get(Ctx, Currency, #{}).

-spec public_get_book_summary_by_currency_get(ctx:ctx(), binary(), maps:map()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
public_get_book_summary_by_currency_get(Ctx, Currency, Optional) ->
    _OptionalParams = maps:get(params, Optional, #{}),
    Cfg = maps:get(cfg, Optional, application:get_env(kuberl, config, #{})),

    Method = get,
    Path = ["/public/get_book_summary_by_currency"],
    QS = lists:flatten([{<<"currency">>, Currency}])++openapi_utils:optional_params(['kind'], _OptionalParams),
    Headers = [],
    Body1 = [],
    ContentTypeHeader = openapi_utils:select_header_content_type([]),
    Opts = maps:get(hackney_opts, Optional, []),

    openapi_utils:request(Ctx, Method, [?BASE_URL, Path], QS, ContentTypeHeader++Headers, Body1, Opts, Cfg).

%% @doc Retrieves the summary information such as open interest, 24h volume, etc. for a specific instrument.
%% 
-spec public_get_book_summary_by_instrument_get(ctx:ctx(), binary()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
public_get_book_summary_by_instrument_get(Ctx, InstrumentName) ->
    public_get_book_summary_by_instrument_get(Ctx, InstrumentName, #{}).

-spec public_get_book_summary_by_instrument_get(ctx:ctx(), binary(), maps:map()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
public_get_book_summary_by_instrument_get(Ctx, InstrumentName, Optional) ->
    _OptionalParams = maps:get(params, Optional, #{}),
    Cfg = maps:get(cfg, Optional, application:get_env(kuberl, config, #{})),

    Method = get,
    Path = ["/public/get_book_summary_by_instrument"],
    QS = lists:flatten([{<<"instrument_name">>, InstrumentName}])++openapi_utils:optional_params([], _OptionalParams),
    Headers = [],
    Body1 = [],
    ContentTypeHeader = openapi_utils:select_header_content_type([]),
    Opts = maps:get(hackney_opts, Optional, []),

    openapi_utils:request(Ctx, Method, [?BASE_URL, Path], QS, ContentTypeHeader++Headers, Body1, Opts, Cfg).

%% @doc Retrieves contract size of provided instrument.
%% 
-spec public_get_contract_size_get(ctx:ctx(), binary()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
public_get_contract_size_get(Ctx, InstrumentName) ->
    public_get_contract_size_get(Ctx, InstrumentName, #{}).

-spec public_get_contract_size_get(ctx:ctx(), binary(), maps:map()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
public_get_contract_size_get(Ctx, InstrumentName, Optional) ->
    _OptionalParams = maps:get(params, Optional, #{}),
    Cfg = maps:get(cfg, Optional, application:get_env(kuberl, config, #{})),

    Method = get,
    Path = ["/public/get_contract_size"],
    QS = lists:flatten([{<<"instrument_name">>, InstrumentName}])++openapi_utils:optional_params([], _OptionalParams),
    Headers = [],
    Body1 = [],
    ContentTypeHeader = openapi_utils:select_header_content_type([]),
    Opts = maps:get(hackney_opts, Optional, []),

    openapi_utils:request(Ctx, Method, [?BASE_URL, Path], QS, ContentTypeHeader++Headers, Body1, Opts, Cfg).

%% @doc Retrieves all cryptocurrencies supported by the API.
%% 
-spec public_get_currencies_get(ctx:ctx()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
public_get_currencies_get(Ctx) ->
    public_get_currencies_get(Ctx, #{}).

-spec public_get_currencies_get(ctx:ctx(), maps:map()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
public_get_currencies_get(Ctx, Optional) ->
    _OptionalParams = maps:get(params, Optional, #{}),
    Cfg = maps:get(cfg, Optional, application:get_env(kuberl, config, #{})),

    Method = get,
    Path = ["/public/get_currencies"],
    QS = [],
    Headers = [],
    Body1 = [],
    ContentTypeHeader = openapi_utils:select_header_content_type([]),
    Opts = maps:get(hackney_opts, Optional, []),

    openapi_utils:request(Ctx, Method, [?BASE_URL, Path], QS, ContentTypeHeader++Headers, Body1, Opts, Cfg).

%% @doc Retrieve the latest user trades that have occurred for PERPETUAL instruments in a specific currency symbol and within given time range.
%% 
-spec public_get_funding_chart_data_get(ctx:ctx(), binary()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
public_get_funding_chart_data_get(Ctx, InstrumentName) ->
    public_get_funding_chart_data_get(Ctx, InstrumentName, #{}).

-spec public_get_funding_chart_data_get(ctx:ctx(), binary(), maps:map()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
public_get_funding_chart_data_get(Ctx, InstrumentName, Optional) ->
    _OptionalParams = maps:get(params, Optional, #{}),
    Cfg = maps:get(cfg, Optional, application:get_env(kuberl, config, #{})),

    Method = get,
    Path = ["/public/get_funding_chart_data"],
    QS = lists:flatten([{<<"instrument_name">>, InstrumentName}])++openapi_utils:optional_params(['length'], _OptionalParams),
    Headers = [],
    Body1 = [],
    ContentTypeHeader = openapi_utils:select_header_content_type([]),
    Opts = maps:get(hackney_opts, Optional, []),

    openapi_utils:request(Ctx, Method, [?BASE_URL, Path], QS, ContentTypeHeader++Headers, Body1, Opts, Cfg).

%% @doc Provides information about historical volatility for given cryptocurrency.
%% 
-spec public_get_historical_volatility_get(ctx:ctx(), binary()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
public_get_historical_volatility_get(Ctx, Currency) ->
    public_get_historical_volatility_get(Ctx, Currency, #{}).

-spec public_get_historical_volatility_get(ctx:ctx(), binary(), maps:map()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
public_get_historical_volatility_get(Ctx, Currency, Optional) ->
    _OptionalParams = maps:get(params, Optional, #{}),
    Cfg = maps:get(cfg, Optional, application:get_env(kuberl, config, #{})),

    Method = get,
    Path = ["/public/get_historical_volatility"],
    QS = lists:flatten([{<<"currency">>, Currency}])++openapi_utils:optional_params([], _OptionalParams),
    Headers = [],
    Body1 = [],
    ContentTypeHeader = openapi_utils:select_header_content_type([]),
    Opts = maps:get(hackney_opts, Optional, []),

    openapi_utils:request(Ctx, Method, [?BASE_URL, Path], QS, ContentTypeHeader++Headers, Body1, Opts, Cfg).

%% @doc Retrieves the current index price for the instruments, for the selected currency.
%% 
-spec public_get_index_get(ctx:ctx(), binary()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
public_get_index_get(Ctx, Currency) ->
    public_get_index_get(Ctx, Currency, #{}).

-spec public_get_index_get(ctx:ctx(), binary(), maps:map()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
public_get_index_get(Ctx, Currency, Optional) ->
    _OptionalParams = maps:get(params, Optional, #{}),
    Cfg = maps:get(cfg, Optional, application:get_env(kuberl, config, #{})),

    Method = get,
    Path = ["/public/get_index"],
    QS = lists:flatten([{<<"currency">>, Currency}])++openapi_utils:optional_params([], _OptionalParams),
    Headers = [],
    Body1 = [],
    ContentTypeHeader = openapi_utils:select_header_content_type([]),
    Opts = maps:get(hackney_opts, Optional, []),

    openapi_utils:request(Ctx, Method, [?BASE_URL, Path], QS, ContentTypeHeader++Headers, Body1, Opts, Cfg).

%% @doc Retrieves available trading instruments. This method can be used to see which instruments are available for trading, or which instruments have existed historically.
%% 
-spec public_get_instruments_get(ctx:ctx(), binary()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
public_get_instruments_get(Ctx, Currency) ->
    public_get_instruments_get(Ctx, Currency, #{}).

-spec public_get_instruments_get(ctx:ctx(), binary(), maps:map()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
public_get_instruments_get(Ctx, Currency, Optional) ->
    _OptionalParams = maps:get(params, Optional, #{}),
    Cfg = maps:get(cfg, Optional, application:get_env(kuberl, config, #{})),

    Method = get,
    Path = ["/public/get_instruments"],
    QS = lists:flatten([{<<"currency">>, Currency}])++openapi_utils:optional_params(['kind', 'expired'], _OptionalParams),
    Headers = [],
    Body1 = [],
    ContentTypeHeader = openapi_utils:select_header_content_type([]),
    Opts = maps:get(hackney_opts, Optional, []),

    openapi_utils:request(Ctx, Method, [?BASE_URL, Path], QS, ContentTypeHeader++Headers, Body1, Opts, Cfg).

%% @doc Retrieves historical settlement, delivery and bankruptcy events coming from all instruments within given currency.
%% 
-spec public_get_last_settlements_by_currency_get(ctx:ctx(), binary()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
public_get_last_settlements_by_currency_get(Ctx, Currency) ->
    public_get_last_settlements_by_currency_get(Ctx, Currency, #{}).

-spec public_get_last_settlements_by_currency_get(ctx:ctx(), binary(), maps:map()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
public_get_last_settlements_by_currency_get(Ctx, Currency, Optional) ->
    _OptionalParams = maps:get(params, Optional, #{}),
    Cfg = maps:get(cfg, Optional, application:get_env(kuberl, config, #{})),

    Method = get,
    Path = ["/public/get_last_settlements_by_currency"],
    QS = lists:flatten([{<<"currency">>, Currency}])++openapi_utils:optional_params(['type', 'count', 'continuation', 'search_start_timestamp'], _OptionalParams),
    Headers = [],
    Body1 = [],
    ContentTypeHeader = openapi_utils:select_header_content_type([]),
    Opts = maps:get(hackney_opts, Optional, []),

    openapi_utils:request(Ctx, Method, [?BASE_URL, Path], QS, ContentTypeHeader++Headers, Body1, Opts, Cfg).

%% @doc Retrieves historical public settlement, delivery and bankruptcy events filtered by instrument name.
%% 
-spec public_get_last_settlements_by_instrument_get(ctx:ctx(), binary()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
public_get_last_settlements_by_instrument_get(Ctx, InstrumentName) ->
    public_get_last_settlements_by_instrument_get(Ctx, InstrumentName, #{}).

-spec public_get_last_settlements_by_instrument_get(ctx:ctx(), binary(), maps:map()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
public_get_last_settlements_by_instrument_get(Ctx, InstrumentName, Optional) ->
    _OptionalParams = maps:get(params, Optional, #{}),
    Cfg = maps:get(cfg, Optional, application:get_env(kuberl, config, #{})),

    Method = get,
    Path = ["/public/get_last_settlements_by_instrument"],
    QS = lists:flatten([{<<"instrument_name">>, InstrumentName}])++openapi_utils:optional_params(['type', 'count', 'continuation', 'search_start_timestamp'], _OptionalParams),
    Headers = [],
    Body1 = [],
    ContentTypeHeader = openapi_utils:select_header_content_type([]),
    Opts = maps:get(hackney_opts, Optional, []),

    openapi_utils:request(Ctx, Method, [?BASE_URL, Path], QS, ContentTypeHeader++Headers, Body1, Opts, Cfg).

%% @doc Retrieve the latest trades that have occurred for instruments in a specific currency symbol and within given time range.
%% 
-spec public_get_last_trades_by_currency_and_time_get(ctx:ctx(), binary(), integer(), integer()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
public_get_last_trades_by_currency_and_time_get(Ctx, Currency, StartTimestamp, EndTimestamp) ->
    public_get_last_trades_by_currency_and_time_get(Ctx, Currency, StartTimestamp, EndTimestamp, #{}).

-spec public_get_last_trades_by_currency_and_time_get(ctx:ctx(), binary(), integer(), integer(), maps:map()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
public_get_last_trades_by_currency_and_time_get(Ctx, Currency, StartTimestamp, EndTimestamp, Optional) ->
    _OptionalParams = maps:get(params, Optional, #{}),
    Cfg = maps:get(cfg, Optional, application:get_env(kuberl, config, #{})),

    Method = get,
    Path = ["/public/get_last_trades_by_currency_and_time"],
    QS = lists:flatten([{<<"currency">>, Currency}, {<<"start_timestamp">>, StartTimestamp}, {<<"end_timestamp">>, EndTimestamp}])++openapi_utils:optional_params(['kind', 'count', 'include_old', 'sorting'], _OptionalParams),
    Headers = [],
    Body1 = [],
    ContentTypeHeader = openapi_utils:select_header_content_type([]),
    Opts = maps:get(hackney_opts, Optional, []),

    openapi_utils:request(Ctx, Method, [?BASE_URL, Path], QS, ContentTypeHeader++Headers, Body1, Opts, Cfg).

%% @doc Retrieve the latest trades that have occurred for instruments in a specific currency symbol.
%% 
-spec public_get_last_trades_by_currency_get(ctx:ctx(), binary()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
public_get_last_trades_by_currency_get(Ctx, Currency) ->
    public_get_last_trades_by_currency_get(Ctx, Currency, #{}).

-spec public_get_last_trades_by_currency_get(ctx:ctx(), binary(), maps:map()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
public_get_last_trades_by_currency_get(Ctx, Currency, Optional) ->
    _OptionalParams = maps:get(params, Optional, #{}),
    Cfg = maps:get(cfg, Optional, application:get_env(kuberl, config, #{})),

    Method = get,
    Path = ["/public/get_last_trades_by_currency"],
    QS = lists:flatten([{<<"currency">>, Currency}])++openapi_utils:optional_params(['kind', 'start_id', 'end_id', 'count', 'include_old', 'sorting'], _OptionalParams),
    Headers = [],
    Body1 = [],
    ContentTypeHeader = openapi_utils:select_header_content_type([]),
    Opts = maps:get(hackney_opts, Optional, []),

    openapi_utils:request(Ctx, Method, [?BASE_URL, Path], QS, ContentTypeHeader++Headers, Body1, Opts, Cfg).

%% @doc Retrieve the latest trades that have occurred for a specific instrument and within given time range.
%% 
-spec public_get_last_trades_by_instrument_and_time_get(ctx:ctx(), binary(), integer(), integer()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
public_get_last_trades_by_instrument_and_time_get(Ctx, InstrumentName, StartTimestamp, EndTimestamp) ->
    public_get_last_trades_by_instrument_and_time_get(Ctx, InstrumentName, StartTimestamp, EndTimestamp, #{}).

-spec public_get_last_trades_by_instrument_and_time_get(ctx:ctx(), binary(), integer(), integer(), maps:map()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
public_get_last_trades_by_instrument_and_time_get(Ctx, InstrumentName, StartTimestamp, EndTimestamp, Optional) ->
    _OptionalParams = maps:get(params, Optional, #{}),
    Cfg = maps:get(cfg, Optional, application:get_env(kuberl, config, #{})),

    Method = get,
    Path = ["/public/get_last_trades_by_instrument_and_time"],
    QS = lists:flatten([{<<"instrument_name">>, InstrumentName}, {<<"start_timestamp">>, StartTimestamp}, {<<"end_timestamp">>, EndTimestamp}])++openapi_utils:optional_params(['count', 'include_old', 'sorting'], _OptionalParams),
    Headers = [],
    Body1 = [],
    ContentTypeHeader = openapi_utils:select_header_content_type([]),
    Opts = maps:get(hackney_opts, Optional, []),

    openapi_utils:request(Ctx, Method, [?BASE_URL, Path], QS, ContentTypeHeader++Headers, Body1, Opts, Cfg).

%% @doc Retrieve the latest trades that have occurred for a specific instrument.
%% 
-spec public_get_last_trades_by_instrument_get(ctx:ctx(), binary()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
public_get_last_trades_by_instrument_get(Ctx, InstrumentName) ->
    public_get_last_trades_by_instrument_get(Ctx, InstrumentName, #{}).

-spec public_get_last_trades_by_instrument_get(ctx:ctx(), binary(), maps:map()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
public_get_last_trades_by_instrument_get(Ctx, InstrumentName, Optional) ->
    _OptionalParams = maps:get(params, Optional, #{}),
    Cfg = maps:get(cfg, Optional, application:get_env(kuberl, config, #{})),

    Method = get,
    Path = ["/public/get_last_trades_by_instrument"],
    QS = lists:flatten([{<<"instrument_name">>, InstrumentName}])++openapi_utils:optional_params(['start_seq', 'end_seq', 'count', 'include_old', 'sorting'], _OptionalParams),
    Headers = [],
    Body1 = [],
    ContentTypeHeader = openapi_utils:select_header_content_type([]),
    Opts = maps:get(hackney_opts, Optional, []),

    openapi_utils:request(Ctx, Method, [?BASE_URL, Path], QS, ContentTypeHeader++Headers, Body1, Opts, Cfg).

%% @doc Retrieves the order book, along with other market values for a given instrument.
%% 
-spec public_get_order_book_get(ctx:ctx(), binary()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
public_get_order_book_get(Ctx, InstrumentName) ->
    public_get_order_book_get(Ctx, InstrumentName, #{}).

-spec public_get_order_book_get(ctx:ctx(), binary(), maps:map()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
public_get_order_book_get(Ctx, InstrumentName, Optional) ->
    _OptionalParams = maps:get(params, Optional, #{}),
    Cfg = maps:get(cfg, Optional, application:get_env(kuberl, config, #{})),

    Method = get,
    Path = ["/public/get_order_book"],
    QS = lists:flatten([{<<"instrument_name">>, InstrumentName}])++openapi_utils:optional_params(['depth'], _OptionalParams),
    Headers = [],
    Body1 = [],
    ContentTypeHeader = openapi_utils:select_header_content_type([]),
    Opts = maps:get(hackney_opts, Optional, []),

    openapi_utils:request(Ctx, Method, [?BASE_URL, Path], QS, ContentTypeHeader++Headers, Body1, Opts, Cfg).

%% @doc Retrieves aggregated 24h trade volumes for different instrument types and currencies.
%% 
-spec public_get_trade_volumes_get(ctx:ctx()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
public_get_trade_volumes_get(Ctx) ->
    public_get_trade_volumes_get(Ctx, #{}).

-spec public_get_trade_volumes_get(ctx:ctx(), maps:map()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
public_get_trade_volumes_get(Ctx, Optional) ->
    _OptionalParams = maps:get(params, Optional, #{}),
    Cfg = maps:get(cfg, Optional, application:get_env(kuberl, config, #{})),

    Method = get,
    Path = ["/public/get_trade_volumes"],
    QS = [],
    Headers = [],
    Body1 = [],
    ContentTypeHeader = openapi_utils:select_header_content_type([]),
    Opts = maps:get(hackney_opts, Optional, []),

    openapi_utils:request(Ctx, Method, [?BASE_URL, Path], QS, ContentTypeHeader++Headers, Body1, Opts, Cfg).

%% @doc Publicly available market data used to generate a TradingView candle chart.
%% 
-spec public_get_tradingview_chart_data_get(ctx:ctx(), binary(), integer(), integer()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
public_get_tradingview_chart_data_get(Ctx, InstrumentName, StartTimestamp, EndTimestamp) ->
    public_get_tradingview_chart_data_get(Ctx, InstrumentName, StartTimestamp, EndTimestamp, #{}).

-spec public_get_tradingview_chart_data_get(ctx:ctx(), binary(), integer(), integer(), maps:map()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
public_get_tradingview_chart_data_get(Ctx, InstrumentName, StartTimestamp, EndTimestamp, Optional) ->
    _OptionalParams = maps:get(params, Optional, #{}),
    Cfg = maps:get(cfg, Optional, application:get_env(kuberl, config, #{})),

    Method = get,
    Path = ["/public/get_tradingview_chart_data"],
    QS = lists:flatten([{<<"instrument_name">>, InstrumentName}, {<<"start_timestamp">>, StartTimestamp}, {<<"end_timestamp">>, EndTimestamp}])++openapi_utils:optional_params([], _OptionalParams),
    Headers = [],
    Body1 = [],
    ContentTypeHeader = openapi_utils:select_header_content_type([]),
    Opts = maps:get(hackney_opts, Optional, []),

    openapi_utils:request(Ctx, Method, [?BASE_URL, Path], QS, ContentTypeHeader++Headers, Body1, Opts, Cfg).

%% @doc Get ticker for an instrument.
%% 
-spec public_ticker_get(ctx:ctx(), binary()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
public_ticker_get(Ctx, InstrumentName) ->
    public_ticker_get(Ctx, InstrumentName, #{}).

-spec public_ticker_get(ctx:ctx(), binary(), maps:map()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
public_ticker_get(Ctx, InstrumentName, Optional) ->
    _OptionalParams = maps:get(params, Optional, #{}),
    Cfg = maps:get(cfg, Optional, application:get_env(kuberl, config, #{})),

    Method = get,
    Path = ["/public/ticker"],
    QS = lists:flatten([{<<"instrument_name">>, InstrumentName}])++openapi_utils:optional_params([], _OptionalParams),
    Headers = [],
    Body1 = [],
    ContentTypeHeader = openapi_utils:select_header_content_type([]),
    Opts = maps:get(hackney_opts, Optional, []),

    openapi_utils:request(Ctx, Method, [?BASE_URL, Path], QS, ContentTypeHeader++Headers, Body1, Opts, Cfg).


