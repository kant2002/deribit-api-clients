-module(openapi_authentication_api).

-export([public_auth_get/9, public_auth_get/10]).

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


