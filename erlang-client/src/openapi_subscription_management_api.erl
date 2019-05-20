-module(openapi_subscription_management_api).

-export([private_subscribe_get/2, private_subscribe_get/3,
         private_unsubscribe_get/2, private_unsubscribe_get/3,
         public_subscribe_get/2, public_subscribe_get/3,
         public_unsubscribe_get/2, public_unsubscribe_get/3]).

-define(BASE_URL, "/api/v2").

%% @doc Subscribe to one or more channels.
%% Subscribe to one or more channels.  The name of the channel determines what information will be provided, and in what form. 
-spec private_subscribe_get(ctx:ctx(), list()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
private_subscribe_get(Ctx, Channels) ->
    private_subscribe_get(Ctx, Channels, #{}).

-spec private_subscribe_get(ctx:ctx(), list(), maps:map()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
private_subscribe_get(Ctx, Channels, Optional) ->
    _OptionalParams = maps:get(params, Optional, #{}),
    Cfg = maps:get(cfg, Optional, application:get_env(kuberl, config, #{})),

    Method = get,
    Path = ["/private/subscribe"],
    QS = lists:flatten([[{<<"channels">>, X} || X <- Channels]])++openapi_utils:optional_params([], _OptionalParams),
    Headers = [],
    Body1 = [],
    ContentTypeHeader = openapi_utils:select_header_content_type([]),
    Opts = maps:get(hackney_opts, Optional, []),

    openapi_utils:request(Ctx, Method, [?BASE_URL, Path], QS, ContentTypeHeader++Headers, Body1, Opts, Cfg).

%% @doc Unsubscribe from one or more channels.
%% 
-spec private_unsubscribe_get(ctx:ctx(), list()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
private_unsubscribe_get(Ctx, Channels) ->
    private_unsubscribe_get(Ctx, Channels, #{}).

-spec private_unsubscribe_get(ctx:ctx(), list(), maps:map()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
private_unsubscribe_get(Ctx, Channels, Optional) ->
    _OptionalParams = maps:get(params, Optional, #{}),
    Cfg = maps:get(cfg, Optional, application:get_env(kuberl, config, #{})),

    Method = get,
    Path = ["/private/unsubscribe"],
    QS = lists:flatten([[{<<"channels">>, X} || X <- Channels]])++openapi_utils:optional_params([], _OptionalParams),
    Headers = [],
    Body1 = [],
    ContentTypeHeader = openapi_utils:select_header_content_type([]),
    Opts = maps:get(hackney_opts, Optional, []),

    openapi_utils:request(Ctx, Method, [?BASE_URL, Path], QS, ContentTypeHeader++Headers, Body1, Opts, Cfg).

%% @doc Subscribe to one or more channels.
%% Subscribe to one or more channels.  This is the same method as [/private/subscribe](#private_subscribe), but it can only be used for 'public' channels. 
-spec public_subscribe_get(ctx:ctx(), list()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
public_subscribe_get(Ctx, Channels) ->
    public_subscribe_get(Ctx, Channels, #{}).

-spec public_subscribe_get(ctx:ctx(), list(), maps:map()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
public_subscribe_get(Ctx, Channels, Optional) ->
    _OptionalParams = maps:get(params, Optional, #{}),
    Cfg = maps:get(cfg, Optional, application:get_env(kuberl, config, #{})),

    Method = get,
    Path = ["/public/subscribe"],
    QS = lists:flatten([[{<<"channels">>, X} || X <- Channels]])++openapi_utils:optional_params([], _OptionalParams),
    Headers = [],
    Body1 = [],
    ContentTypeHeader = openapi_utils:select_header_content_type([]),
    Opts = maps:get(hackney_opts, Optional, []),

    openapi_utils:request(Ctx, Method, [?BASE_URL, Path], QS, ContentTypeHeader++Headers, Body1, Opts, Cfg).

%% @doc Unsubscribe from one or more channels.
%% 
-spec public_unsubscribe_get(ctx:ctx(), list()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
public_unsubscribe_get(Ctx, Channels) ->
    public_unsubscribe_get(Ctx, Channels, #{}).

-spec public_unsubscribe_get(ctx:ctx(), list(), maps:map()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
public_unsubscribe_get(Ctx, Channels, Optional) ->
    _OptionalParams = maps:get(params, Optional, #{}),
    Cfg = maps:get(cfg, Optional, application:get_env(kuberl, config, #{})),

    Method = get,
    Path = ["/public/unsubscribe"],
    QS = lists:flatten([[{<<"channels">>, X} || X <- Channels]])++openapi_utils:optional_params([], _OptionalParams),
    Headers = [],
    Body1 = [],
    ContentTypeHeader = openapi_utils:select_header_content_type([]),
    Opts = maps:get(hackney_opts, Optional, []),

    openapi_utils:request(Ctx, Method, [?BASE_URL, Path], QS, ContentTypeHeader++Headers, Body1, Opts, Cfg).


