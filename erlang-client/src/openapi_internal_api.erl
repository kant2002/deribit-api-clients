-module(openapi_internal_api).

-export([private_add_to_address_book_get/5, private_add_to_address_book_get/6,
         private_disable_tfa_with_recovery_code_get/3, private_disable_tfa_with_recovery_code_get/4,
         private_get_address_book_get/3, private_get_address_book_get/4,
         private_remove_from_address_book_get/4, private_remove_from_address_book_get/5,
         private_submit_transfer_to_subaccount_get/4, private_submit_transfer_to_subaccount_get/5,
         private_submit_transfer_to_user_get/4, private_submit_transfer_to_user_get/5,
         private_toggle_deposit_address_creation_get/3, private_toggle_deposit_address_creation_get/4,
         public_get_footer_get/1, public_get_footer_get/2,
         public_get_option_mark_prices_get/2, public_get_option_mark_prices_get/3,
         public_validate_field_get/3, public_validate_field_get/4]).

-define(BASE_URL, "/api/v2").

%% @doc Adds new entry to address book of given type
%% 
-spec private_add_to_address_book_get(ctx:ctx(), binary(), binary(), binary(), binary()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
private_add_to_address_book_get(Ctx, Currency, Type, Address, Name) ->
    private_add_to_address_book_get(Ctx, Currency, Type, Address, Name, #{}).

-spec private_add_to_address_book_get(ctx:ctx(), binary(), binary(), binary(), binary(), maps:map()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
private_add_to_address_book_get(Ctx, Currency, Type, Address, Name, Optional) ->
    _OptionalParams = maps:get(params, Optional, #{}),
    Cfg = maps:get(cfg, Optional, application:get_env(kuberl, config, #{})),

    Method = get,
    Path = ["/private/add_to_address_book"],
    QS = lists:flatten([{<<"currency">>, Currency}, {<<"type">>, Type}, {<<"address">>, Address}, {<<"name">>, Name}])++openapi_utils:optional_params(['tfa'], _OptionalParams),
    Headers = [],
    Body1 = [],
    ContentTypeHeader = openapi_utils:select_header_content_type([]),
    Opts = maps:get(hackney_opts, Optional, []),

    openapi_utils:request(Ctx, Method, [?BASE_URL, Path], QS, ContentTypeHeader++Headers, Body1, Opts, Cfg).

%% @doc Disables TFA with one time recovery code
%% 
-spec private_disable_tfa_with_recovery_code_get(ctx:ctx(), binary(), binary()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
private_disable_tfa_with_recovery_code_get(Ctx, Password, Code) ->
    private_disable_tfa_with_recovery_code_get(Ctx, Password, Code, #{}).

-spec private_disable_tfa_with_recovery_code_get(ctx:ctx(), binary(), binary(), maps:map()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
private_disable_tfa_with_recovery_code_get(Ctx, Password, Code, Optional) ->
    _OptionalParams = maps:get(params, Optional, #{}),
    Cfg = maps:get(cfg, Optional, application:get_env(kuberl, config, #{})),

    Method = get,
    Path = ["/private/disable_tfa_with_recovery_code"],
    QS = lists:flatten([{<<"password">>, Password}, {<<"code">>, Code}])++openapi_utils:optional_params([], _OptionalParams),
    Headers = [],
    Body1 = [],
    ContentTypeHeader = openapi_utils:select_header_content_type([]),
    Opts = maps:get(hackney_opts, Optional, []),

    openapi_utils:request(Ctx, Method, [?BASE_URL, Path], QS, ContentTypeHeader++Headers, Body1, Opts, Cfg).

%% @doc Retrieves address book of given type
%% 
-spec private_get_address_book_get(ctx:ctx(), binary(), binary()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
private_get_address_book_get(Ctx, Currency, Type) ->
    private_get_address_book_get(Ctx, Currency, Type, #{}).

-spec private_get_address_book_get(ctx:ctx(), binary(), binary(), maps:map()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
private_get_address_book_get(Ctx, Currency, Type, Optional) ->
    _OptionalParams = maps:get(params, Optional, #{}),
    Cfg = maps:get(cfg, Optional, application:get_env(kuberl, config, #{})),

    Method = get,
    Path = ["/private/get_address_book"],
    QS = lists:flatten([{<<"currency">>, Currency}, {<<"type">>, Type}])++openapi_utils:optional_params([], _OptionalParams),
    Headers = [],
    Body1 = [],
    ContentTypeHeader = openapi_utils:select_header_content_type([]),
    Opts = maps:get(hackney_opts, Optional, []),

    openapi_utils:request(Ctx, Method, [?BASE_URL, Path], QS, ContentTypeHeader++Headers, Body1, Opts, Cfg).

%% @doc Adds new entry to address book of given type
%% 
-spec private_remove_from_address_book_get(ctx:ctx(), binary(), binary(), binary()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
private_remove_from_address_book_get(Ctx, Currency, Type, Address) ->
    private_remove_from_address_book_get(Ctx, Currency, Type, Address, #{}).

-spec private_remove_from_address_book_get(ctx:ctx(), binary(), binary(), binary(), maps:map()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
private_remove_from_address_book_get(Ctx, Currency, Type, Address, Optional) ->
    _OptionalParams = maps:get(params, Optional, #{}),
    Cfg = maps:get(cfg, Optional, application:get_env(kuberl, config, #{})),

    Method = get,
    Path = ["/private/remove_from_address_book"],
    QS = lists:flatten([{<<"currency">>, Currency}, {<<"type">>, Type}, {<<"address">>, Address}])++openapi_utils:optional_params(['tfa'], _OptionalParams),
    Headers = [],
    Body1 = [],
    ContentTypeHeader = openapi_utils:select_header_content_type([]),
    Opts = maps:get(hackney_opts, Optional, []),

    openapi_utils:request(Ctx, Method, [?BASE_URL, Path], QS, ContentTypeHeader++Headers, Body1, Opts, Cfg).

%% @doc Transfer funds to a subaccount.
%% 
-spec private_submit_transfer_to_subaccount_get(ctx:ctx(), binary(), integer(), integer()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
private_submit_transfer_to_subaccount_get(Ctx, Currency, Amount, Destination) ->
    private_submit_transfer_to_subaccount_get(Ctx, Currency, Amount, Destination, #{}).

-spec private_submit_transfer_to_subaccount_get(ctx:ctx(), binary(), integer(), integer(), maps:map()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
private_submit_transfer_to_subaccount_get(Ctx, Currency, Amount, Destination, Optional) ->
    _OptionalParams = maps:get(params, Optional, #{}),
    Cfg = maps:get(cfg, Optional, application:get_env(kuberl, config, #{})),

    Method = get,
    Path = ["/private/submit_transfer_to_subaccount"],
    QS = lists:flatten([{<<"currency">>, Currency}, {<<"amount">>, Amount}, {<<"destination">>, Destination}])++openapi_utils:optional_params([], _OptionalParams),
    Headers = [],
    Body1 = [],
    ContentTypeHeader = openapi_utils:select_header_content_type([]),
    Opts = maps:get(hackney_opts, Optional, []),

    openapi_utils:request(Ctx, Method, [?BASE_URL, Path], QS, ContentTypeHeader++Headers, Body1, Opts, Cfg).

%% @doc Transfer funds to a another user.
%% 
-spec private_submit_transfer_to_user_get(ctx:ctx(), binary(), integer(), binary()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
private_submit_transfer_to_user_get(Ctx, Currency, Amount, Destination) ->
    private_submit_transfer_to_user_get(Ctx, Currency, Amount, Destination, #{}).

-spec private_submit_transfer_to_user_get(ctx:ctx(), binary(), integer(), binary(), maps:map()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
private_submit_transfer_to_user_get(Ctx, Currency, Amount, Destination, Optional) ->
    _OptionalParams = maps:get(params, Optional, #{}),
    Cfg = maps:get(cfg, Optional, application:get_env(kuberl, config, #{})),

    Method = get,
    Path = ["/private/submit_transfer_to_user"],
    QS = lists:flatten([{<<"currency">>, Currency}, {<<"amount">>, Amount}, {<<"destination">>, Destination}])++openapi_utils:optional_params(['tfa'], _OptionalParams),
    Headers = [],
    Body1 = [],
    ContentTypeHeader = openapi_utils:select_header_content_type([]),
    Opts = maps:get(hackney_opts, Optional, []),

    openapi_utils:request(Ctx, Method, [?BASE_URL, Path], QS, ContentTypeHeader++Headers, Body1, Opts, Cfg).

%% @doc Enable or disable deposit address creation
%% 
-spec private_toggle_deposit_address_creation_get(ctx:ctx(), binary(), boolean()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
private_toggle_deposit_address_creation_get(Ctx, Currency, State) ->
    private_toggle_deposit_address_creation_get(Ctx, Currency, State, #{}).

-spec private_toggle_deposit_address_creation_get(ctx:ctx(), binary(), boolean(), maps:map()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
private_toggle_deposit_address_creation_get(Ctx, Currency, State, Optional) ->
    _OptionalParams = maps:get(params, Optional, #{}),
    Cfg = maps:get(cfg, Optional, application:get_env(kuberl, config, #{})),

    Method = get,
    Path = ["/private/toggle_deposit_address_creation"],
    QS = lists:flatten([{<<"currency">>, Currency}, {<<"state">>, State}])++openapi_utils:optional_params([], _OptionalParams),
    Headers = [],
    Body1 = [],
    ContentTypeHeader = openapi_utils:select_header_content_type([]),
    Opts = maps:get(hackney_opts, Optional, []),

    openapi_utils:request(Ctx, Method, [?BASE_URL, Path], QS, ContentTypeHeader++Headers, Body1, Opts, Cfg).

%% @doc Get information to be displayed in the footer of the website.
%% 
-spec public_get_footer_get(ctx:ctx()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
public_get_footer_get(Ctx) ->
    public_get_footer_get(Ctx, #{}).

-spec public_get_footer_get(ctx:ctx(), maps:map()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
public_get_footer_get(Ctx, Optional) ->
    _OptionalParams = maps:get(params, Optional, #{}),
    Cfg = maps:get(cfg, Optional, application:get_env(kuberl, config, #{})),

    Method = get,
    Path = ["/public/get_footer"],
    QS = [],
    Headers = [],
    Body1 = [],
    ContentTypeHeader = openapi_utils:select_header_content_type([]),
    Opts = maps:get(hackney_opts, Optional, []),

    openapi_utils:request(Ctx, Method, [?BASE_URL, Path], QS, ContentTypeHeader++Headers, Body1, Opts, Cfg).

%% @doc Retrives market prices and its implied volatility of options instruments
%% 
-spec public_get_option_mark_prices_get(ctx:ctx(), binary()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
public_get_option_mark_prices_get(Ctx, Currency) ->
    public_get_option_mark_prices_get(Ctx, Currency, #{}).

-spec public_get_option_mark_prices_get(ctx:ctx(), binary(), maps:map()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
public_get_option_mark_prices_get(Ctx, Currency, Optional) ->
    _OptionalParams = maps:get(params, Optional, #{}),
    Cfg = maps:get(cfg, Optional, application:get_env(kuberl, config, #{})),

    Method = get,
    Path = ["/public/get_option_mark_prices"],
    QS = lists:flatten([{<<"currency">>, Currency}])++openapi_utils:optional_params([], _OptionalParams),
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


