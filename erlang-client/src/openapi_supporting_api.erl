-module(openapi_supporting_api).

-export([public_get_time_get/1, public_get_time_get/2,
         public_test_get/1, public_test_get/2]).

-define(BASE_URL, "/api/v2").

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


