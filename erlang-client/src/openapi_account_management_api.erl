-module(openapi_account_management_api).

-export([private_change_subaccount_name_get/3, private_change_subaccount_name_get/4,
         private_create_subaccount_get/1, private_create_subaccount_get/2,
         private_disable_tfa_for_subaccount_get/2, private_disable_tfa_for_subaccount_get/3,
         private_get_account_summary_get/2, private_get_account_summary_get/3,
         private_get_email_language_get/1, private_get_email_language_get/2,
         private_get_new_announcements_get/1, private_get_new_announcements_get/2,
         private_get_position_get/2, private_get_position_get/3,
         private_get_positions_get/2, private_get_positions_get/3,
         private_get_subaccounts_get/1, private_get_subaccounts_get/2,
         private_set_announcement_as_read_get/2, private_set_announcement_as_read_get/3,
         private_set_email_for_subaccount_get/3, private_set_email_for_subaccount_get/4,
         private_set_email_language_get/2, private_set_email_language_get/3,
         private_set_password_for_subaccount_get/3, private_set_password_for_subaccount_get/4,
         private_toggle_notifications_from_subaccount_get/3, private_toggle_notifications_from_subaccount_get/4,
         private_toggle_subaccount_login_get/3, private_toggle_subaccount_login_get/4,
         public_get_announcements_get/1, public_get_announcements_get/2]).

-define(BASE_URL, "/api/v2").

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


