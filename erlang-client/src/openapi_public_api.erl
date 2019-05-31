-module(openapi_public_api).

-export([public_auth_get/9, public_auth_get/10,
         public_get_announcements_get/1, public_get_announcements_get/2,
         public_get_book_summary_by_currency_get/2, public_get_book_summary_by_currency_get/3,
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
         public_get_time_get/1, public_get_time_get/2,
         public_get_trade_volumes_get/1, public_get_trade_volumes_get/2,
         public_get_tradingview_chart_data_get/4, public_get_tradingview_chart_data_get/5,
         public_test_get/1, public_test_get/2,
         public_ticker_get/2, public_ticker_get/3,
         public_validate_field_get/3, public_validate_field_get/4]).

-define(BASE_URL, "/api/v2").

%% @doc Authenticate
%% Retrieve an Oauth access token, to be used for authentication of 'private' requests.  Three methods of authentication are supported:  - <code>password</code> - using email and and password as when logging on to the website - <code>client_credentials</code> - using the access key and access secret that can be found on the API page on the website - <code>client_signature</code> - using the access key that can be found on the API page on the website and user generated signature. The signature is calculated using some fields provided in the request, using formula described here [Deribit signature credentials](#additional-authorization-method-deribit-signature-credentials) - <code>refresh_token</code> - using a refresh token that was received from an earlier invocation  The response will contain an access token, expiration period (number of seconds that the token is valid) and a refresh token that can be used to get a new set of tokens. 
-spec public_auth_get(ctx:ctx(), binary(), binary(), binary(), binary(), binary(), binary(), binary(), binary()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
public_auth_get(Ctx, GrantType, Username, Password, ClientId, ClientSecret, RefreshToken, Timestamp, Signature) ->
    public_auth_get(Ctx, GrantType, Username, Password, ClientId, ClientSecret, RefreshToken, Timestamp, Signature, #{}).

-spec public_auth_get(ctx:ctx(), binary(), binary(), binary(), binary(), binary(), binary(), binary(), binary(), maps:map()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
public_auth_get(Ctx, GrantType, Username, Password, ClientId, ClientSecret, RefreshToken, Timestamp, Signature, Optional) ->
    _OptionalParams = maps:get(params, Optional, #{}),
    Cfg = maps:get(cfg, Optional, application:get_env(kuberl, config, #{})),

    Method = get,
    Path = ["/public/auth"],
    QS = lists:flatten([{<<"grant_type">>, GrantType}, {<<"username">>, Username}, {<<"password">>, Password}, {<<"client_id">>, ClientId}, {<<"client_secret">>, ClientSecret}, {<<"refresh_token">>, RefreshToken}, {<<"timestamp">>, Timestamp}, {<<"signature">>, Signature}])++openapi_utils:optional_params(['nonce', 'state', 'scope'], _OptionalParams),
    Headers = [],
    Body1 = [],
    ContentTypeHeader = openapi_utils:select_header_content_type([]),
    Opts = maps:get(hackney_opts, Optional, []),

    openapi_utils:request(Ctx, Method, [?BASE_URL, Path], QS, ContentTypeHeader++Headers, Body1, Opts, Cfg).

%% @doc Retrieves announcements from the last 30 days.
%% 
-spec public_get_announcements_get(ctx:ctx()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
public_get_announcements_get(Ctx) ->
    public_get_announcements_get(Ctx, #{}).

-spec public_get_announcements_get(ctx:ctx(), maps:map()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
public_get_announcements_get(Ctx, Optional) ->
    _OptionalParams = maps:get(params, Optional, #{}),
    Cfg = maps:get(cfg, Optional, application:get_env(kuberl, config, #{})),

    Method = get,
    Path = ["/public/get_announcements"],
    QS = [],
    Headers = [],
    Body1 = [],
    ContentTypeHeader = openapi_utils:select_header_content_type([]),
    Opts = maps:get(hackney_opts, Optional, []),

    openapi_utils:request(Ctx, Method, [?BASE_URL, Path], QS, ContentTypeHeader++Headers, Body1, Opts, Cfg).

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

%% @doc Retrieves the current time (in milliseconds). This API endpoint can be used to check the clock skew between your software and Deribit's systems.
%% 
-spec public_get_time_get(ctx:ctx()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
public_get_time_get(Ctx) ->
    public_get_time_get(Ctx, #{}).

-spec public_get_time_get(ctx:ctx(), maps:map()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
public_get_time_get(Ctx, Optional) ->
    _OptionalParams = maps:get(params, Optional, #{}),
    Cfg = maps:get(cfg, Optional, application:get_env(kuberl, config, #{})),

    Method = get,
    Path = ["/public/get_time"],
    QS = [],
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

%% @doc Tests the connection to the API server, and returns its version. You can use this to make sure the API is reachable, and matches the expected version.
%% 
-spec public_test_get(ctx:ctx()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
public_test_get(Ctx) ->
    public_test_get(Ctx, #{}).

-spec public_test_get(ctx:ctx(), maps:map()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
public_test_get(Ctx, Optional) ->
    _OptionalParams = maps:get(params, Optional, #{}),
    Cfg = maps:get(cfg, Optional, application:get_env(kuberl, config, #{})),

    Method = get,
    Path = ["/public/test"],
    QS = lists:flatten([])++openapi_utils:optional_params(['expected_result'], _OptionalParams),
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

%% @doc Method used to introduce the client software connected to Deribit platform over websocket. Provided data may have an impact on the maintained connection and will be collected for internal statistical purposes. In response, Deribit will also introduce itself.
%% 
-spec public_validate_field_get(ctx:ctx(), binary(), binary()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
public_validate_field_get(Ctx, Field, Value) ->
    public_validate_field_get(Ctx, Field, Value, #{}).

-spec public_validate_field_get(ctx:ctx(), binary(), binary(), maps:map()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
public_validate_field_get(Ctx, Field, Value, Optional) ->
    _OptionalParams = maps:get(params, Optional, #{}),
    Cfg = maps:get(cfg, Optional, application:get_env(kuberl, config, #{})),

    Method = get,
    Path = ["/public/validate_field"],
    QS = lists:flatten([{<<"field">>, Field}, {<<"value">>, Value}])++openapi_utils:optional_params(['value2'], _OptionalParams),
    Headers = [],
    Body1 = [],
    ContentTypeHeader = openapi_utils:select_header_content_type([]),
    Opts = maps:get(hackney_opts, Optional, []),

    openapi_utils:request(Ctx, Method, [?BASE_URL, Path], QS, ContentTypeHeader++Headers, Body1, Opts, Cfg).


