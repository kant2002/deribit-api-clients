-module(openapi_wallet_api).

-export([private_add_to_address_book_get/5, private_add_to_address_book_get/6,
         private_cancel_transfer_by_id_get/3, private_cancel_transfer_by_id_get/4,
         private_cancel_withdrawal_get/3, private_cancel_withdrawal_get/4,
         private_create_deposit_address_get/2, private_create_deposit_address_get/3,
         private_get_address_book_get/3, private_get_address_book_get/4,
         private_get_current_deposit_address_get/2, private_get_current_deposit_address_get/3,
         private_get_deposits_get/2, private_get_deposits_get/3,
         private_get_transfers_get/2, private_get_transfers_get/3,
         private_get_withdrawals_get/2, private_get_withdrawals_get/3,
         private_remove_from_address_book_get/4, private_remove_from_address_book_get/5,
         private_submit_transfer_to_subaccount_get/4, private_submit_transfer_to_subaccount_get/5,
         private_submit_transfer_to_user_get/4, private_submit_transfer_to_user_get/5,
         private_toggle_deposit_address_creation_get/3, private_toggle_deposit_address_creation_get/4,
         private_withdraw_get/4, private_withdraw_get/5]).

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

%% @doc Cancel transfer
%% 
-spec private_cancel_transfer_by_id_get(ctx:ctx(), binary(), integer()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
private_cancel_transfer_by_id_get(Ctx, Currency, Id) ->
    private_cancel_transfer_by_id_get(Ctx, Currency, Id, #{}).

-spec private_cancel_transfer_by_id_get(ctx:ctx(), binary(), integer(), maps:map()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
private_cancel_transfer_by_id_get(Ctx, Currency, Id, Optional) ->
    _OptionalParams = maps:get(params, Optional, #{}),
    Cfg = maps:get(cfg, Optional, application:get_env(kuberl, config, #{})),

    Method = get,
    Path = ["/private/cancel_transfer_by_id"],
    QS = lists:flatten([{<<"currency">>, Currency}, {<<"id">>, Id}])++openapi_utils:optional_params(['tfa'], _OptionalParams),
    Headers = [],
    Body1 = [],
    ContentTypeHeader = openapi_utils:select_header_content_type([]),
    Opts = maps:get(hackney_opts, Optional, []),

    openapi_utils:request(Ctx, Method, [?BASE_URL, Path], QS, ContentTypeHeader++Headers, Body1, Opts, Cfg).

%% @doc Cancels withdrawal request
%% 
-spec private_cancel_withdrawal_get(ctx:ctx(), binary(), integer()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
private_cancel_withdrawal_get(Ctx, Currency, Id) ->
    private_cancel_withdrawal_get(Ctx, Currency, Id, #{}).

-spec private_cancel_withdrawal_get(ctx:ctx(), binary(), integer(), maps:map()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
private_cancel_withdrawal_get(Ctx, Currency, Id, Optional) ->
    _OptionalParams = maps:get(params, Optional, #{}),
    Cfg = maps:get(cfg, Optional, application:get_env(kuberl, config, #{})),

    Method = get,
    Path = ["/private/cancel_withdrawal"],
    QS = lists:flatten([{<<"currency">>, Currency}, {<<"id">>, Id}])++openapi_utils:optional_params([], _OptionalParams),
    Headers = [],
    Body1 = [],
    ContentTypeHeader = openapi_utils:select_header_content_type([]),
    Opts = maps:get(hackney_opts, Optional, []),

    openapi_utils:request(Ctx, Method, [?BASE_URL, Path], QS, ContentTypeHeader++Headers, Body1, Opts, Cfg).

%% @doc Creates deposit address in currency
%% 
-spec private_create_deposit_address_get(ctx:ctx(), binary()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
private_create_deposit_address_get(Ctx, Currency) ->
    private_create_deposit_address_get(Ctx, Currency, #{}).

-spec private_create_deposit_address_get(ctx:ctx(), binary(), maps:map()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
private_create_deposit_address_get(Ctx, Currency, Optional) ->
    _OptionalParams = maps:get(params, Optional, #{}),
    Cfg = maps:get(cfg, Optional, application:get_env(kuberl, config, #{})),

    Method = get,
    Path = ["/private/create_deposit_address"],
    QS = lists:flatten([{<<"currency">>, Currency}])++openapi_utils:optional_params([], _OptionalParams),
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

%% @doc Retrieve deposit address for currency
%% 
-spec private_get_current_deposit_address_get(ctx:ctx(), binary()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
private_get_current_deposit_address_get(Ctx, Currency) ->
    private_get_current_deposit_address_get(Ctx, Currency, #{}).

-spec private_get_current_deposit_address_get(ctx:ctx(), binary(), maps:map()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
private_get_current_deposit_address_get(Ctx, Currency, Optional) ->
    _OptionalParams = maps:get(params, Optional, #{}),
    Cfg = maps:get(cfg, Optional, application:get_env(kuberl, config, #{})),

    Method = get,
    Path = ["/private/get_current_deposit_address"],
    QS = lists:flatten([{<<"currency">>, Currency}])++openapi_utils:optional_params([], _OptionalParams),
    Headers = [],
    Body1 = [],
    ContentTypeHeader = openapi_utils:select_header_content_type([]),
    Opts = maps:get(hackney_opts, Optional, []),

    openapi_utils:request(Ctx, Method, [?BASE_URL, Path], QS, ContentTypeHeader++Headers, Body1, Opts, Cfg).

%% @doc Retrieve the latest users deposits
%% 
-spec private_get_deposits_get(ctx:ctx(), binary()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
private_get_deposits_get(Ctx, Currency) ->
    private_get_deposits_get(Ctx, Currency, #{}).

-spec private_get_deposits_get(ctx:ctx(), binary(), maps:map()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
private_get_deposits_get(Ctx, Currency, Optional) ->
    _OptionalParams = maps:get(params, Optional, #{}),
    Cfg = maps:get(cfg, Optional, application:get_env(kuberl, config, #{})),

    Method = get,
    Path = ["/private/get_deposits"],
    QS = lists:flatten([{<<"currency">>, Currency}])++openapi_utils:optional_params(['count', 'offset'], _OptionalParams),
    Headers = [],
    Body1 = [],
    ContentTypeHeader = openapi_utils:select_header_content_type([]),
    Opts = maps:get(hackney_opts, Optional, []),

    openapi_utils:request(Ctx, Method, [?BASE_URL, Path], QS, ContentTypeHeader++Headers, Body1, Opts, Cfg).

%% @doc Adds new entry to address book of given type
%% 
-spec private_get_transfers_get(ctx:ctx(), binary()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
private_get_transfers_get(Ctx, Currency) ->
    private_get_transfers_get(Ctx, Currency, #{}).

-spec private_get_transfers_get(ctx:ctx(), binary(), maps:map()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
private_get_transfers_get(Ctx, Currency, Optional) ->
    _OptionalParams = maps:get(params, Optional, #{}),
    Cfg = maps:get(cfg, Optional, application:get_env(kuberl, config, #{})),

    Method = get,
    Path = ["/private/get_transfers"],
    QS = lists:flatten([{<<"currency">>, Currency}])++openapi_utils:optional_params(['count', 'offset'], _OptionalParams),
    Headers = [],
    Body1 = [],
    ContentTypeHeader = openapi_utils:select_header_content_type([]),
    Opts = maps:get(hackney_opts, Optional, []),

    openapi_utils:request(Ctx, Method, [?BASE_URL, Path], QS, ContentTypeHeader++Headers, Body1, Opts, Cfg).

%% @doc Retrieve the latest users withdrawals
%% 
-spec private_get_withdrawals_get(ctx:ctx(), binary()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
private_get_withdrawals_get(Ctx, Currency) ->
    private_get_withdrawals_get(Ctx, Currency, #{}).

-spec private_get_withdrawals_get(ctx:ctx(), binary(), maps:map()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
private_get_withdrawals_get(Ctx, Currency, Optional) ->
    _OptionalParams = maps:get(params, Optional, #{}),
    Cfg = maps:get(cfg, Optional, application:get_env(kuberl, config, #{})),

    Method = get,
    Path = ["/private/get_withdrawals"],
    QS = lists:flatten([{<<"currency">>, Currency}])++openapi_utils:optional_params(['count', 'offset'], _OptionalParams),
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

%% @doc Creates a new withdrawal request
%% 
-spec private_withdraw_get(ctx:ctx(), binary(), binary(), integer()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
private_withdraw_get(Ctx, Currency, Address, Amount) ->
    private_withdraw_get(Ctx, Currency, Address, Amount, #{}).

-spec private_withdraw_get(ctx:ctx(), binary(), binary(), integer(), maps:map()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
private_withdraw_get(Ctx, Currency, Address, Amount, Optional) ->
    _OptionalParams = maps:get(params, Optional, #{}),
    Cfg = maps:get(cfg, Optional, application:get_env(kuberl, config, #{})),

    Method = get,
    Path = ["/private/withdraw"],
    QS = lists:flatten([{<<"currency">>, Currency}, {<<"address">>, Address}, {<<"amount">>, Amount}])++openapi_utils:optional_params(['priority', 'tfa'], _OptionalParams),
    Headers = [],
    Body1 = [],
    ContentTypeHeader = openapi_utils:select_header_content_type([]),
    Opts = maps:get(hackney_opts, Optional, []),

    openapi_utils:request(Ctx, Method, [?BASE_URL, Path], QS, ContentTypeHeader++Headers, Body1, Opts, Cfg).


