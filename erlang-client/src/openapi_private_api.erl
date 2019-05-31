-module(openapi_private_api).

-export([private_add_to_address_book_get/5, private_add_to_address_book_get/6,
         private_buy_get/3, private_buy_get/4,
         private_cancel_all_by_currency_get/2, private_cancel_all_by_currency_get/3,
         private_cancel_all_by_instrument_get/2, private_cancel_all_by_instrument_get/3,
         private_cancel_all_get/1, private_cancel_all_get/2,
         private_cancel_get/2, private_cancel_get/3,
         private_cancel_transfer_by_id_get/3, private_cancel_transfer_by_id_get/4,
         private_cancel_withdrawal_get/3, private_cancel_withdrawal_get/4,
         private_change_subaccount_name_get/3, private_change_subaccount_name_get/4,
         private_close_position_get/3, private_close_position_get/4,
         private_create_deposit_address_get/2, private_create_deposit_address_get/3,
         private_create_subaccount_get/1, private_create_subaccount_get/2,
         private_disable_tfa_for_subaccount_get/2, private_disable_tfa_for_subaccount_get/3,
         private_disable_tfa_with_recovery_code_get/3, private_disable_tfa_with_recovery_code_get/4,
         private_edit_get/4, private_edit_get/5,
         private_get_account_summary_get/2, private_get_account_summary_get/3,
         private_get_address_book_get/3, private_get_address_book_get/4,
         private_get_current_deposit_address_get/2, private_get_current_deposit_address_get/3,
         private_get_deposits_get/2, private_get_deposits_get/3,
         private_get_email_language_get/1, private_get_email_language_get/2,
         private_get_margins_get/4, private_get_margins_get/5,
         private_get_new_announcements_get/1, private_get_new_announcements_get/2,
         private_get_open_orders_by_currency_get/2, private_get_open_orders_by_currency_get/3,
         private_get_open_orders_by_instrument_get/2, private_get_open_orders_by_instrument_get/3,
         private_get_order_history_by_currency_get/2, private_get_order_history_by_currency_get/3,
         private_get_order_history_by_instrument_get/2, private_get_order_history_by_instrument_get/3,
         private_get_order_margin_by_ids_get/2, private_get_order_margin_by_ids_get/3,
         private_get_order_state_get/2, private_get_order_state_get/3,
         private_get_position_get/2, private_get_position_get/3,
         private_get_positions_get/2, private_get_positions_get/3,
         private_get_settlement_history_by_currency_get/2, private_get_settlement_history_by_currency_get/3,
         private_get_settlement_history_by_instrument_get/2, private_get_settlement_history_by_instrument_get/3,
         private_get_subaccounts_get/1, private_get_subaccounts_get/2,
         private_get_transfers_get/2, private_get_transfers_get/3,
         private_get_user_trades_by_currency_and_time_get/4, private_get_user_trades_by_currency_and_time_get/5,
         private_get_user_trades_by_currency_get/2, private_get_user_trades_by_currency_get/3,
         private_get_user_trades_by_instrument_and_time_get/4, private_get_user_trades_by_instrument_and_time_get/5,
         private_get_user_trades_by_instrument_get/2, private_get_user_trades_by_instrument_get/3,
         private_get_user_trades_by_order_get/2, private_get_user_trades_by_order_get/3,
         private_get_withdrawals_get/2, private_get_withdrawals_get/3,
         private_remove_from_address_book_get/4, private_remove_from_address_book_get/5,
         private_sell_get/3, private_sell_get/4,
         private_set_announcement_as_read_get/2, private_set_announcement_as_read_get/3,
         private_set_email_for_subaccount_get/3, private_set_email_for_subaccount_get/4,
         private_set_email_language_get/2, private_set_email_language_get/3,
         private_set_password_for_subaccount_get/3, private_set_password_for_subaccount_get/4,
         private_submit_transfer_to_subaccount_get/4, private_submit_transfer_to_subaccount_get/5,
         private_submit_transfer_to_user_get/4, private_submit_transfer_to_user_get/5,
         private_toggle_deposit_address_creation_get/3, private_toggle_deposit_address_creation_get/4,
         private_toggle_notifications_from_subaccount_get/3, private_toggle_notifications_from_subaccount_get/4,
         private_toggle_subaccount_login_get/3, private_toggle_subaccount_login_get/4,
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

%% @doc Change the user name for a subaccount
%% 
-spec private_change_subaccount_name_get(ctx:ctx(), integer(), binary()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
private_change_subaccount_name_get(Ctx, Sid, Name) ->
    private_change_subaccount_name_get(Ctx, Sid, Name, #{}).

-spec private_change_subaccount_name_get(ctx:ctx(), integer(), binary(), maps:map()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
private_change_subaccount_name_get(Ctx, Sid, Name, Optional) ->
    _OptionalParams = maps:get(params, Optional, #{}),
    Cfg = maps:get(cfg, Optional, application:get_env(kuberl, config, #{})),

    Method = get,
    Path = ["/private/change_subaccount_name"],
    QS = lists:flatten([{<<"sid">>, Sid}, {<<"name">>, Name}])++openapi_utils:optional_params([], _OptionalParams),
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

%% @doc Create a new subaccount
%% 
-spec private_create_subaccount_get(ctx:ctx()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
private_create_subaccount_get(Ctx) ->
    private_create_subaccount_get(Ctx, #{}).

-spec private_create_subaccount_get(ctx:ctx(), maps:map()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
private_create_subaccount_get(Ctx, Optional) ->
    _OptionalParams = maps:get(params, Optional, #{}),
    Cfg = maps:get(cfg, Optional, application:get_env(kuberl, config, #{})),

    Method = get,
    Path = ["/private/create_subaccount"],
    QS = [],
    Headers = [],
    Body1 = [],
    ContentTypeHeader = openapi_utils:select_header_content_type([]),
    Opts = maps:get(hackney_opts, Optional, []),

    openapi_utils:request(Ctx, Method, [?BASE_URL, Path], QS, ContentTypeHeader++Headers, Body1, Opts, Cfg).

%% @doc Disable two factor authentication for a subaccount.
%% 
-spec private_disable_tfa_for_subaccount_get(ctx:ctx(), integer()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
private_disable_tfa_for_subaccount_get(Ctx, Sid) ->
    private_disable_tfa_for_subaccount_get(Ctx, Sid, #{}).

-spec private_disable_tfa_for_subaccount_get(ctx:ctx(), integer(), maps:map()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
private_disable_tfa_for_subaccount_get(Ctx, Sid, Optional) ->
    _OptionalParams = maps:get(params, Optional, #{}),
    Cfg = maps:get(cfg, Optional, application:get_env(kuberl, config, #{})),

    Method = get,
    Path = ["/private/disable_tfa_for_subaccount"],
    QS = lists:flatten([{<<"sid">>, Sid}])++openapi_utils:optional_params([], _OptionalParams),
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

%% @doc Retrieves user account summary.
%% 
-spec private_get_account_summary_get(ctx:ctx(), binary()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
private_get_account_summary_get(Ctx, Currency) ->
    private_get_account_summary_get(Ctx, Currency, #{}).

-spec private_get_account_summary_get(ctx:ctx(), binary(), maps:map()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
private_get_account_summary_get(Ctx, Currency, Optional) ->
    _OptionalParams = maps:get(params, Optional, #{}),
    Cfg = maps:get(cfg, Optional, application:get_env(kuberl, config, #{})),

    Method = get,
    Path = ["/private/get_account_summary"],
    QS = lists:flatten([{<<"currency">>, Currency}])++openapi_utils:optional_params(['extended'], _OptionalParams),
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

%% @doc Retrieves the language to be used for emails.
%% 
-spec private_get_email_language_get(ctx:ctx()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
private_get_email_language_get(Ctx) ->
    private_get_email_language_get(Ctx, #{}).

-spec private_get_email_language_get(ctx:ctx(), maps:map()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
private_get_email_language_get(Ctx, Optional) ->
    _OptionalParams = maps:get(params, Optional, #{}),
    Cfg = maps:get(cfg, Optional, application:get_env(kuberl, config, #{})),

    Method = get,
    Path = ["/private/get_email_language"],
    QS = [],
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

%% @doc Retrieves announcements that have not been marked read by the user.
%% 
-spec private_get_new_announcements_get(ctx:ctx()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
private_get_new_announcements_get(Ctx) ->
    private_get_new_announcements_get(Ctx, #{}).

-spec private_get_new_announcements_get(ctx:ctx(), maps:map()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
private_get_new_announcements_get(Ctx, Optional) ->
    _OptionalParams = maps:get(params, Optional, #{}),
    Cfg = maps:get(cfg, Optional, application:get_env(kuberl, config, #{})),

    Method = get,
    Path = ["/private/get_new_announcements"],
    QS = [],
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

%% @doc Retrieve user position.
%% 
-spec private_get_position_get(ctx:ctx(), binary()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
private_get_position_get(Ctx, InstrumentName) ->
    private_get_position_get(Ctx, InstrumentName, #{}).

-spec private_get_position_get(ctx:ctx(), binary(), maps:map()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
private_get_position_get(Ctx, InstrumentName, Optional) ->
    _OptionalParams = maps:get(params, Optional, #{}),
    Cfg = maps:get(cfg, Optional, application:get_env(kuberl, config, #{})),

    Method = get,
    Path = ["/private/get_position"],
    QS = lists:flatten([{<<"instrument_name">>, InstrumentName}])++openapi_utils:optional_params([], _OptionalParams),
    Headers = [],
    Body1 = [],
    ContentTypeHeader = openapi_utils:select_header_content_type([]),
    Opts = maps:get(hackney_opts, Optional, []),

    openapi_utils:request(Ctx, Method, [?BASE_URL, Path], QS, ContentTypeHeader++Headers, Body1, Opts, Cfg).

%% @doc Retrieve user positions.
%% 
-spec private_get_positions_get(ctx:ctx(), binary()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
private_get_positions_get(Ctx, Currency) ->
    private_get_positions_get(Ctx, Currency, #{}).

-spec private_get_positions_get(ctx:ctx(), binary(), maps:map()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
private_get_positions_get(Ctx, Currency, Optional) ->
    _OptionalParams = maps:get(params, Optional, #{}),
    Cfg = maps:get(cfg, Optional, application:get_env(kuberl, config, #{})),

    Method = get,
    Path = ["/private/get_positions"],
    QS = lists:flatten([{<<"currency">>, Currency}])++openapi_utils:optional_params(['kind'], _OptionalParams),
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

%% @doc Get information about subaccounts
%% 
-spec private_get_subaccounts_get(ctx:ctx()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
private_get_subaccounts_get(Ctx) ->
    private_get_subaccounts_get(Ctx, #{}).

-spec private_get_subaccounts_get(ctx:ctx(), maps:map()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
private_get_subaccounts_get(Ctx, Optional) ->
    _OptionalParams = maps:get(params, Optional, #{}),
    Cfg = maps:get(cfg, Optional, application:get_env(kuberl, config, #{})),

    Method = get,
    Path = ["/private/get_subaccounts"],
    QS = lists:flatten([])++openapi_utils:optional_params(['with_portfolio'], _OptionalParams),
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

%% @doc Marks an announcement as read, so it will not be shown in `get_new_announcements`.
%% 
-spec private_set_announcement_as_read_get(ctx:ctx(), integer()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
private_set_announcement_as_read_get(Ctx, AnnouncementId) ->
    private_set_announcement_as_read_get(Ctx, AnnouncementId, #{}).

-spec private_set_announcement_as_read_get(ctx:ctx(), integer(), maps:map()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
private_set_announcement_as_read_get(Ctx, AnnouncementId, Optional) ->
    _OptionalParams = maps:get(params, Optional, #{}),
    Cfg = maps:get(cfg, Optional, application:get_env(kuberl, config, #{})),

    Method = get,
    Path = ["/private/set_announcement_as_read"],
    QS = lists:flatten([{<<"announcement_id">>, AnnouncementId}])++openapi_utils:optional_params([], _OptionalParams),
    Headers = [],
    Body1 = [],
    ContentTypeHeader = openapi_utils:select_header_content_type([]),
    Opts = maps:get(hackney_opts, Optional, []),

    openapi_utils:request(Ctx, Method, [?BASE_URL, Path], QS, ContentTypeHeader++Headers, Body1, Opts, Cfg).

%% @doc Assign an email address to a subaccount. User will receive an email with confirmation link.
%% 
-spec private_set_email_for_subaccount_get(ctx:ctx(), integer(), binary()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
private_set_email_for_subaccount_get(Ctx, Sid, Email) ->
    private_set_email_for_subaccount_get(Ctx, Sid, Email, #{}).

-spec private_set_email_for_subaccount_get(ctx:ctx(), integer(), binary(), maps:map()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
private_set_email_for_subaccount_get(Ctx, Sid, Email, Optional) ->
    _OptionalParams = maps:get(params, Optional, #{}),
    Cfg = maps:get(cfg, Optional, application:get_env(kuberl, config, #{})),

    Method = get,
    Path = ["/private/set_email_for_subaccount"],
    QS = lists:flatten([{<<"sid">>, Sid}, {<<"email">>, Email}])++openapi_utils:optional_params([], _OptionalParams),
    Headers = [],
    Body1 = [],
    ContentTypeHeader = openapi_utils:select_header_content_type([]),
    Opts = maps:get(hackney_opts, Optional, []),

    openapi_utils:request(Ctx, Method, [?BASE_URL, Path], QS, ContentTypeHeader++Headers, Body1, Opts, Cfg).

%% @doc Changes the language to be used for emails.
%% 
-spec private_set_email_language_get(ctx:ctx(), binary()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
private_set_email_language_get(Ctx, Language) ->
    private_set_email_language_get(Ctx, Language, #{}).

-spec private_set_email_language_get(ctx:ctx(), binary(), maps:map()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
private_set_email_language_get(Ctx, Language, Optional) ->
    _OptionalParams = maps:get(params, Optional, #{}),
    Cfg = maps:get(cfg, Optional, application:get_env(kuberl, config, #{})),

    Method = get,
    Path = ["/private/set_email_language"],
    QS = lists:flatten([{<<"language">>, Language}])++openapi_utils:optional_params([], _OptionalParams),
    Headers = [],
    Body1 = [],
    ContentTypeHeader = openapi_utils:select_header_content_type([]),
    Opts = maps:get(hackney_opts, Optional, []),

    openapi_utils:request(Ctx, Method, [?BASE_URL, Path], QS, ContentTypeHeader++Headers, Body1, Opts, Cfg).

%% @doc Set the password for the subaccount
%% 
-spec private_set_password_for_subaccount_get(ctx:ctx(), integer(), binary()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
private_set_password_for_subaccount_get(Ctx, Sid, Password) ->
    private_set_password_for_subaccount_get(Ctx, Sid, Password, #{}).

-spec private_set_password_for_subaccount_get(ctx:ctx(), integer(), binary(), maps:map()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
private_set_password_for_subaccount_get(Ctx, Sid, Password, Optional) ->
    _OptionalParams = maps:get(params, Optional, #{}),
    Cfg = maps:get(cfg, Optional, application:get_env(kuberl, config, #{})),

    Method = get,
    Path = ["/private/set_password_for_subaccount"],
    QS = lists:flatten([{<<"sid">>, Sid}, {<<"password">>, Password}])++openapi_utils:optional_params([], _OptionalParams),
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

%% @doc Enable or disable sending of notifications for the subaccount.
%% 
-spec private_toggle_notifications_from_subaccount_get(ctx:ctx(), integer(), boolean()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
private_toggle_notifications_from_subaccount_get(Ctx, Sid, State) ->
    private_toggle_notifications_from_subaccount_get(Ctx, Sid, State, #{}).

-spec private_toggle_notifications_from_subaccount_get(ctx:ctx(), integer(), boolean(), maps:map()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
private_toggle_notifications_from_subaccount_get(Ctx, Sid, State, Optional) ->
    _OptionalParams = maps:get(params, Optional, #{}),
    Cfg = maps:get(cfg, Optional, application:get_env(kuberl, config, #{})),

    Method = get,
    Path = ["/private/toggle_notifications_from_subaccount"],
    QS = lists:flatten([{<<"sid">>, Sid}, {<<"state">>, State}])++openapi_utils:optional_params([], _OptionalParams),
    Headers = [],
    Body1 = [],
    ContentTypeHeader = openapi_utils:select_header_content_type([]),
    Opts = maps:get(hackney_opts, Optional, []),

    openapi_utils:request(Ctx, Method, [?BASE_URL, Path], QS, ContentTypeHeader++Headers, Body1, Opts, Cfg).

%% @doc Enable or disable login for a subaccount. If login is disabled and a session for the subaccount exists, this session will be terminated.
%% 
-spec private_toggle_subaccount_login_get(ctx:ctx(), integer(), binary()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
private_toggle_subaccount_login_get(Ctx, Sid, State) ->
    private_toggle_subaccount_login_get(Ctx, Sid, State, #{}).

-spec private_toggle_subaccount_login_get(ctx:ctx(), integer(), binary(), maps:map()) -> {ok, maps:map(), openapi_utils:response_info()} | {ok, hackney:client_ref()} | {error, term(), openapi_utils:response_info()}.
private_toggle_subaccount_login_get(Ctx, Sid, State, Optional) ->
    _OptionalParams = maps:get(params, Optional, #{}),
    Cfg = maps:get(cfg, Optional, application:get_env(kuberl, config, #{})),

    Method = get,
    Path = ["/private/toggle_subaccount_login"],
    QS = lists:flatten([{<<"sid">>, Sid}, {<<"state">>, State}])++openapi_utils:optional_params([], _OptionalParams),
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


