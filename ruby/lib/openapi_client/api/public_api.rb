=begin
#Deribit API

##Overview  Deribit provides three different interfaces to access the API:  * [JSON-RPC over Websocket](#json-rpc) * [JSON-RPC over HTTP](#json-rpc) * [FIX](#fix-api) (Financial Information eXchange)  With the API Console you can use and test the JSON-RPC API, both via HTTP and  via Websocket. To visit the API console, go to __Account > API tab >  API Console tab.__   ##Naming Deribit tradeable assets or instruments use the following system of naming:  |Kind|Examples|Template|Comments| |----|--------|--------|--------| |Future|<code>BTC-25MAR16</code>, <code>BTC-5AUG16</code>|<code>BTC-DMMMYY</code>|<code>BTC</code> is currency, <code>DMMMYY</code> is expiration date, <code>D</code> stands for day of month (1 or 2 digits), <code>MMM</code> - month (3 first letters in English), <code>YY</code> stands for year.| |Perpetual|<code>BTC-PERPETUAL</code>                        ||Perpetual contract for currency <code>BTC</code>.| |Option|<code>BTC-25MAR16-420-C</code>, <code>BTC-5AUG16-580-P</code>|<code>BTC-DMMMYY-STRIKE-K</code>|<code>STRIKE</code> is option strike price in USD. Template <code>K</code> is option kind: <code>C</code> for call options or <code>P</code> for put options.|   # JSON-RPC JSON-RPC is a light-weight remote procedure call (RPC) protocol. The  [JSON-RPC specification](https://www.jsonrpc.org/specification) defines the data structures that are used for the messages that are exchanged between client and server, as well as the rules around their processing. JSON-RPC uses JSON (RFC 4627) as data format.  JSON-RPC is transport agnostic: it does not specify which transport mechanism must be used. The Deribit API supports both Websocket (preferred) and HTTP (with limitations: subscriptions are not supported over HTTP).  ## Request messages > An example of a request message:  ```json {     \"jsonrpc\": \"2.0\",     \"id\": 8066,     \"method\": \"public/ticker\",     \"params\": {         \"instrument\": \"BTC-24AUG18-6500-P\"     } } ```  According to the JSON-RPC sepcification the requests must be JSON objects with the following fields.  |Name|Type|Description| |----|----|-----------| |jsonrpc|string|The version of the JSON-RPC spec: \"2.0\"| |id|integer or string|An identifier of the request. If it is included, then the response will contain the same identifier| |method|string|The method to be invoked| |params|object|The parameters values for the method. The field names must match with the expected parameter names. The parameters that are expected are described in the documentation for the methods, below.|  <aside class=\"warning\"> The JSON-RPC specification describes two features that are currently not supported by the API:  <ul> <li>Specification of parameter values by position</li> <li>Batch requests</li> </ul>  </aside>   ## Response messages > An example of a response message:  ```json {     \"jsonrpc\": \"2.0\",     \"id\": 5239,     \"testnet\": false,     \"result\": [         {             \"currency\": \"BTC\",             \"currencyLong\": \"Bitcoin\",             \"minConfirmation\": 2,             \"txFee\": 0.0006,             \"isActive\": true,             \"coinType\": \"BITCOIN\",             \"baseAddress\": null         }     ],     \"usIn\": 1535043730126248,     \"usOut\": 1535043730126250,     \"usDiff\": 2 } ```  The JSON-RPC API always responds with a JSON object with the following fields.   |Name|Type|Description| |----|----|-----------| |id|integer|This is the same id that was sent in the request.| |result|any|If successful, the result of the API call. The format for the result is described with each method.| |error|error object|Only present if there was an error invoking the method. The error object is described below.| |testnet|boolean|Indicates whether the API in use is actually the test API.  <code>false</code> for production server, <code>true</code> for test server.| |usIn|integer|The timestamp when the requests was received (microseconds since the Unix epoch)| |usOut|integer|The timestamp when the response was sent (microseconds since the Unix epoch)| |usDiff|integer|The number of microseconds that was spent handling the request|  <aside class=\"notice\"> The fields <code>testnet</code>, <code>usIn</code>, <code>usOut</code> and <code>usDiff</code> are not part of the JSON-RPC standard.  <p>In order not to clutter the examples they will generally be omitted from the example code.</p> </aside>  > An example of a response with an error:  ```json {     \"jsonrpc\": \"2.0\",     \"id\": 8163,     \"error\": {         \"code\": 11050,         \"message\": \"bad_request\"     },     \"testnet\": false,     \"usIn\": 1535037392434763,     \"usOut\": 1535037392448119,     \"usDiff\": 13356 } ``` In case of an error the response message will contain the error field, with as value an object with the following with the following fields:  |Name|Type|Description |----|----|-----------| |code|integer|A number that indicates the kind of error.| |message|string|A short description that indicates the kind of error.| |data|any|Additional data about the error. This field may be omitted.|  ## Notifications  > An example of a notification:  ```json {     \"jsonrpc\": \"2.0\",     \"method\": \"subscription\",     \"params\": {         \"channel\": \"deribit_price_index.btc_usd\",         \"data\": {             \"timestamp\": 1535098298227,             \"price\": 6521.17,             \"index_name\": \"btc_usd\"         }     } } ```  API users can subscribe to certain types of notifications. This means that they will receive JSON-RPC notification-messages from the server when certain events occur, such as changes to the index price or changes to the order book for a certain instrument.   The API methods [public/subscribe](#public-subscribe) and [private/subscribe](#private-subscribe) are used to set up a subscription. Since HTTP does not support the sending of messages from server to client, these methods are only availble when using the Websocket transport mechanism.  At the moment of subscription a \"channel\" must be specified. The channel determines the type of events that will be received.  See [Subscriptions](#subscriptions) for more details about the channels.  In accordance with the JSON-RPC specification, the format of a notification  is that of a request message without an <code>id</code> field. The value of the <code>method</code> field will always be <code>\"subscription\"</code>. The <code>params</code> field will always be an object with 2 members: <code>channel</code> and <code>data</code>. The value of the <code>channel</code> member is the name of the channel (a string). The value of the <code>data</code> member is an object that contains data  that is specific for the channel.   ## Authentication  > An example of a JSON request with token:  ```json {     \"id\": 5647,     \"method\": \"private/get_subaccounts\",     \"params\": {         \"access_token\": \"67SVutDoVZSzkUStHSuk51WntMNBJ5mh5DYZhwzpiqDF\"     } } ```  The API consists of `public` and `private` methods. The public methods do not require authentication. The private methods use OAuth 2.0 authentication. This means that a valid OAuth access token must be included in the request, which can get achived by calling method [public/auth](#public-auth).  When the token was assigned to the user, it should be passed along, with other request parameters, back to the server:  |Connection type|Access token placement |----|-----------| |**Websocket**|Inside request JSON parameters, as an `access_token` field| |**HTTP (REST)**|Header `Authorization: bearer ```Token``` ` value|  ### Additional authorization method - basic user credentials  <span style=\"color:red\"><b> ! Not recommended - however, it could be useful for quick testing API</b></span></br>  Every `private` method could be accessed by providing, inside HTTP `Authorization: Basic XXX` header, values with user `ClientId` and assigned `ClientSecret` (both values can be found on the API page on the Deribit website) encoded with `Base64`:  <code>Authorization: Basic BASE64(`ClientId` + `:` + `ClientSecret`)</code>   ### Additional authorization method - Deribit signature credentials  The Derbit service provides dedicated authorization method, which harness user generated signature to increase security level for passing request data. Generated value is passed inside `Authorization` header, coded as:  <code>Authorization: deri-hmac-sha256 id=```ClientId```,ts=```Timestamp```,sig=```Signature```,nonce=```Nonce```</code>  where:  |Deribit credential|Description |----|-----------| |*ClientId*|Can be found on the API page on the Deribit website| |*Timestamp*|Time when the request was generated - given as **miliseconds**. It's valid for **60 seconds** since generation, after that time any request with an old timestamp will be rejected.| |*Signature*|Value for signature calculated as described below | |*Nonce*|Single usage, user generated initialization vector for the server token|  The signature is generated by the following formula:  <code> Signature = HEX_STRING( HMAC-SHA256( ClientSecret, StringToSign ) );</code></br>  <code> StringToSign =  Timestamp + \"\\n\" + Nonce + \"\\n\" + RequestData;</code></br>  <code> RequestData =  UPPERCASE(HTTP_METHOD())  + \"\\n\" + URI() + \"\\n\" + RequestBody + \"\\n\";</code></br>   e.g. (using shell with ```openssl``` tool):  <code>&nbsp;&nbsp;&nbsp;&nbsp;ClientId=AAAAAAAAAAA</code></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;ClientSecret=ABCD</code></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;Timestamp=$( date +%s000 )</code></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;Nonce=$( cat /dev/urandom | tr -dc 'a-z0-9' | head -c8 )</code></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;URI=\"/api/v2/private/get_account_summary?currency=BTC\"</code></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;HttpMethod=GET</code></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;Body=\"\"</code></br></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;Signature=$( echo -ne \"${Timestamp}\\n${Nonce}\\n${HttpMethod}\\n${URI}\\n${Body}\\n\" | openssl sha256 -r -hmac \"$ClientSecret\" | cut -f1 -d' ' )</code></br></br> <code>&nbsp;&nbsp;&nbsp;&nbsp;echo $Signature</code></br></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;shell output> ea40d5e5e4fae235ab22b61da98121fbf4acdc06db03d632e23c66bcccb90d2c  (**WARNING**: Exact value depends on current timestamp and client credentials</code></br></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;curl -s -X ${HttpMethod} -H \"Authorization: deri-hmac-sha256 id=${ClientId},ts=${Timestamp},nonce=${Nonce},sig=${Signature}\" \"https://www.deribit.com${URI}\"</code></br></br>    ### Additional authorization method - signature credentials (WebSocket API)  When connecting through Websocket, user can request for authorization using ```client_credential``` method, which requires providing following parameters (as a part of JSON request):  |JSON parameter|Description |----|-----------| |*grant_type*|Must be **client_signature**| |*client_id*|Can be found on the API page on the Deribit website| |*timestamp*|Time when the request was generated - given as **miliseconds**. It's valid for **60 seconds** since generation, after that time any request with an old timestamp will be rejected.| |*signature*|Value for signature calculated as described below | |*nonce*|Single usage, user generated initialization vector for the server token| |*data*|**Optional** field, which contains any user specific value|  The signature is generated by the following formula:  <code> StringToSign =  Timestamp + \"\\n\" + Nonce + \"\\n\" + Data;</code></br>  <code> Signature = HEX_STRING( HMAC-SHA256( ClientSecret, StringToSign ) );</code></br>   e.g. (using shell with ```openssl``` tool):  <code>&nbsp;&nbsp;&nbsp;&nbsp;ClientId=AAAAAAAAAAA</code></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;ClientSecret=ABCD</code></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;Timestamp=$( date +%s000 ) # e.g. 1554883365000 </code></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;Nonce=$( cat /dev/urandom | tr -dc 'a-z0-9' | head -c8 ) # e.g. fdbmmz79 </code></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;Data=\"\"</code></br></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;Signature=$( echo -ne \"${Timestamp}\\n${Nonce}\\n${Data}\\n\" | openssl sha256 -r -hmac \"$ClientSecret\" | cut -f1 -d' ' )</code></br></br> <code>&nbsp;&nbsp;&nbsp;&nbsp;echo $Signature</code></br></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;shell output> e20c9cd5639d41f8bbc88f4d699c4baf94a4f0ee320e9a116b72743c449eb994  (**WARNING**: Exact value depends on current timestamp and client credentials</code></br></br>   You can also check the signature value using some online tools like, e.g: [https://codebeautify.org/hmac-generator](https://codebeautify.org/hmac-generator) (but don't forget about adding *newline* after each part of the hashed text and remember that you **should use** it only with your **test credentials**).   Here's a sample JSON request created using the values from the example above:  <code> {                            </br> &nbsp;&nbsp;\"jsonrpc\" : \"2.0\",         </br> &nbsp;&nbsp;\"id\" : 9929,               </br> &nbsp;&nbsp;\"method\" : \"public/auth\",  </br> &nbsp;&nbsp;\"params\" :                 </br> &nbsp;&nbsp;{                        </br> &nbsp;&nbsp;&nbsp;&nbsp;\"grant_type\" : \"client_signature\",   </br> &nbsp;&nbsp;&nbsp;&nbsp;\"client_id\" : \"AAAAAAAAAAA\",         </br> &nbsp;&nbsp;&nbsp;&nbsp;\"timestamp\": \"1554883365000\",        </br> &nbsp;&nbsp;&nbsp;&nbsp;\"nonce\": \"fdbmmz79\",                 </br> &nbsp;&nbsp;&nbsp;&nbsp;\"data\": \"\",                          </br> &nbsp;&nbsp;&nbsp;&nbsp;\"signature\" : \"e20c9cd5639d41f8bbc88f4d699c4baf94a4f0ee320e9a116b72743c449eb994\"  </br> &nbsp;&nbsp;}                        </br> }                            </br> </code>   ### Access scope  When asking for `access token` user can provide the required access level (called `scope`) which defines what type of functionality he/she wants to use, and whether requests are only going to check for some data or also to update them.  Scopes are required and checked for `private` methods, so if you plan to use only `public` information you can stay with values assigned by default.  |Scope|Description |----|-----------| |*account:read*|Access to **account** methods - read only data| |*account:read_write*|Access to **account** methods - allows to manage account settings, add subaccounts, etc.| |*trade:read*|Access to **trade** methods - read only data| |*trade:read_write*|Access to **trade** methods - required to create and modify orders| |*wallet:read*|Access to **wallet** methods - read only data| |*wallet:read_write*|Access to **wallet** methods - allows to withdraw, generate new deposit address, etc.| |*wallet:none*, *account:none*, *trade:none*|Blocked access to specified functionality|    <span style=\"color:red\">**NOTICE:**</span> Depending on choosing an authentication method (```grant type```) some scopes could be narrowed by the server. e.g. when ```grant_type = client_credentials``` and ```scope = wallet:read_write``` it's modified by the server as ```scope = wallet:read```\"   ## JSON-RPC over websocket Websocket is the prefered transport mechanism for the JSON-RPC API, because it is faster and because it can support [subscriptions](#subscriptions) and [cancel on disconnect](#private-enable_cancel_on_disconnect). The code examples that can be found next to each of the methods show how websockets can be used from Python or Javascript/node.js.  ## JSON-RPC over HTTP Besides websockets it is also possible to use the API via HTTP. The code examples for 'shell' show how this can be done using curl. Note that subscriptions and cancel on disconnect are not supported via HTTP.  #Methods 

The version of the OpenAPI document: 2.0.0

Generated by: https://openapi-generator.tech
OpenAPI Generator version: 4.0.2-SNAPSHOT

=end

require 'uri'
require 'cgi'

module OpenapiClient
  class PublicApi
    attr_accessor :api_client

    def initialize(api_client = ApiClient.default)
      @api_client = api_client
    end
    # Authenticate
    # Retrieve an Oauth access token, to be used for authentication of 'private' requests.  Three methods of authentication are supported:  - <code>password</code> - using email and and password as when logging on to the website - <code>client_credentials</code> - using the access key and access secret that can be found on the API page on the website - <code>client_signature</code> - using the access key that can be found on the API page on the website and user generated signature. The signature is calculated using some fields provided in the request, using formula described here [Deribit signature credentials](#additional-authorization-method-deribit-signature-credentials) - <code>refresh_token</code> - using a refresh token that was received from an earlier invocation  The response will contain an access token, expiration period (number of seconds that the token is valid) and a refresh token that can be used to get a new set of tokens. 
    # @param grant_type [String] Method of authentication
    # @param username [String] Required for grant type &#x60;password&#x60;
    # @param password [String] Required for grant type &#x60;password&#x60;
    # @param client_id [String] Required for grant type &#x60;client_credentials&#x60; and &#x60;client_signature&#x60;
    # @param client_secret [String] Required for grant type &#x60;client_credentials&#x60;
    # @param refresh_token [String] Required for grant type &#x60;refresh_token&#x60;
    # @param timestamp [String] Required for grant type &#x60;client_signature&#x60;, provides time when request has been generated
    # @param signature [String] Required for grant type &#x60;client_signature&#x60;; it&#39;s a cryptographic signature calculated over provided fields using user **secret key**. The signature should be calculated as an HMAC (Hash-based Message Authentication Code) with &#x60;SHA256&#x60; hash algorithm
    # @param [Hash] opts the optional parameters
    # @option opts [String] :nonce Optional for grant type &#x60;client_signature&#x60;; delivers user generated initialization vector for the server token
    # @option opts [String] :state Will be passed back in the response
    # @option opts [String] :scope Describes type of the access for assigned token, possible values: &#x60;connection&#x60;, &#x60;session&#x60;, &#x60;session:name&#x60;, &#x60;trade:[read, read_write, none]&#x60;, &#x60;wallet:[read, read_write, none]&#x60;, &#x60;account:[read, read_write, none]&#x60;, &#x60;expires:NUMBER&#x60; (token will expire after &#x60;NUMBER&#x60; of seconds).&lt;/BR&gt;&lt;/BR&gt; **NOTICE:** Depending on choosing an authentication method (&#x60;&#x60;&#x60;grant type&#x60;&#x60;&#x60;) some scopes could be narrowed by the server. e.g. when &#x60;&#x60;&#x60;grant_type &#x3D; client_credentials&#x60;&#x60;&#x60; and &#x60;&#x60;&#x60;scope &#x3D; wallet:read_write&#x60;&#x60;&#x60; it&#39;s modified by the server as &#x60;&#x60;&#x60;scope &#x3D; wallet:read&#x60;&#x60;&#x60;
    # @return [Object]
    def public_auth_get(grant_type, username, password, client_id, client_secret, refresh_token, timestamp, signature, opts = {})
      data, _status_code, _headers = public_auth_get_with_http_info(grant_type, username, password, client_id, client_secret, refresh_token, timestamp, signature, opts)
      data
    end

    # Authenticate
    # Retrieve an Oauth access token, to be used for authentication of &#39;private&#39; requests.  Three methods of authentication are supported:  - &lt;code&gt;password&lt;/code&gt; - using email and and password as when logging on to the website - &lt;code&gt;client_credentials&lt;/code&gt; - using the access key and access secret that can be found on the API page on the website - &lt;code&gt;client_signature&lt;/code&gt; - using the access key that can be found on the API page on the website and user generated signature. The signature is calculated using some fields provided in the request, using formula described here [Deribit signature credentials](#additional-authorization-method-deribit-signature-credentials) - &lt;code&gt;refresh_token&lt;/code&gt; - using a refresh token that was received from an earlier invocation  The response will contain an access token, expiration period (number of seconds that the token is valid) and a refresh token that can be used to get a new set of tokens. 
    # @param grant_type [String] Method of authentication
    # @param username [String] Required for grant type &#x60;password&#x60;
    # @param password [String] Required for grant type &#x60;password&#x60;
    # @param client_id [String] Required for grant type &#x60;client_credentials&#x60; and &#x60;client_signature&#x60;
    # @param client_secret [String] Required for grant type &#x60;client_credentials&#x60;
    # @param refresh_token [String] Required for grant type &#x60;refresh_token&#x60;
    # @param timestamp [String] Required for grant type &#x60;client_signature&#x60;, provides time when request has been generated
    # @param signature [String] Required for grant type &#x60;client_signature&#x60;; it&#39;s a cryptographic signature calculated over provided fields using user **secret key**. The signature should be calculated as an HMAC (Hash-based Message Authentication Code) with &#x60;SHA256&#x60; hash algorithm
    # @param [Hash] opts the optional parameters
    # @option opts [String] :nonce Optional for grant type &#x60;client_signature&#x60;; delivers user generated initialization vector for the server token
    # @option opts [String] :state Will be passed back in the response
    # @option opts [String] :scope Describes type of the access for assigned token, possible values: &#x60;connection&#x60;, &#x60;session&#x60;, &#x60;session:name&#x60;, &#x60;trade:[read, read_write, none]&#x60;, &#x60;wallet:[read, read_write, none]&#x60;, &#x60;account:[read, read_write, none]&#x60;, &#x60;expires:NUMBER&#x60; (token will expire after &#x60;NUMBER&#x60; of seconds).&lt;/BR&gt;&lt;/BR&gt; **NOTICE:** Depending on choosing an authentication method (&#x60;&#x60;&#x60;grant type&#x60;&#x60;&#x60;) some scopes could be narrowed by the server. e.g. when &#x60;&#x60;&#x60;grant_type &#x3D; client_credentials&#x60;&#x60;&#x60; and &#x60;&#x60;&#x60;scope &#x3D; wallet:read_write&#x60;&#x60;&#x60; it&#39;s modified by the server as &#x60;&#x60;&#x60;scope &#x3D; wallet:read&#x60;&#x60;&#x60;
    # @return [Array<(Object, Integer, Hash)>] Object data, response status code and response headers
    def public_auth_get_with_http_info(grant_type, username, password, client_id, client_secret, refresh_token, timestamp, signature, opts = {})
      if @api_client.config.debugging
        @api_client.config.logger.debug 'Calling API: PublicApi.public_auth_get ...'
      end
      # verify the required parameter 'grant_type' is set
      if @api_client.config.client_side_validation && grant_type.nil?
        fail ArgumentError, "Missing the required parameter 'grant_type' when calling PublicApi.public_auth_get"
      end
      # verify enum value
      allowable_values = ["password", "client_credentials", "client_signature", "refresh_token"]
      if @api_client.config.client_side_validation && !allowable_values.include?(grant_type)
        fail ArgumentError, "invalid value for \"grant_type\", must be one of #{allowable_values}"
      end
      # verify the required parameter 'username' is set
      if @api_client.config.client_side_validation && username.nil?
        fail ArgumentError, "Missing the required parameter 'username' when calling PublicApi.public_auth_get"
      end
      # verify the required parameter 'password' is set
      if @api_client.config.client_side_validation && password.nil?
        fail ArgumentError, "Missing the required parameter 'password' when calling PublicApi.public_auth_get"
      end
      # verify the required parameter 'client_id' is set
      if @api_client.config.client_side_validation && client_id.nil?
        fail ArgumentError, "Missing the required parameter 'client_id' when calling PublicApi.public_auth_get"
      end
      # verify the required parameter 'client_secret' is set
      if @api_client.config.client_side_validation && client_secret.nil?
        fail ArgumentError, "Missing the required parameter 'client_secret' when calling PublicApi.public_auth_get"
      end
      # verify the required parameter 'refresh_token' is set
      if @api_client.config.client_side_validation && refresh_token.nil?
        fail ArgumentError, "Missing the required parameter 'refresh_token' when calling PublicApi.public_auth_get"
      end
      # verify the required parameter 'timestamp' is set
      if @api_client.config.client_side_validation && timestamp.nil?
        fail ArgumentError, "Missing the required parameter 'timestamp' when calling PublicApi.public_auth_get"
      end
      # verify the required parameter 'signature' is set
      if @api_client.config.client_side_validation && signature.nil?
        fail ArgumentError, "Missing the required parameter 'signature' when calling PublicApi.public_auth_get"
      end
      # resource path
      local_var_path = '/public/auth'

      # query parameters
      query_params = opts[:query_params] || {}
      query_params[:'grant_type'] = grant_type
      query_params[:'username'] = username
      query_params[:'password'] = password
      query_params[:'client_id'] = client_id
      query_params[:'client_secret'] = client_secret
      query_params[:'refresh_token'] = refresh_token
      query_params[:'timestamp'] = timestamp
      query_params[:'signature'] = signature
      query_params[:'nonce'] = opts[:'nonce'] if !opts[:'nonce'].nil?
      query_params[:'state'] = opts[:'state'] if !opts[:'state'].nil?
      query_params[:'scope'] = opts[:'scope'] if !opts[:'scope'].nil?

      # header parameters
      header_params = opts[:header_params] || {}
      # HTTP header 'Accept' (if needed)
      header_params['Accept'] = @api_client.select_header_accept(['application/json'])

      # form parameters
      form_params = opts[:form_params] || {}

      # http body (model)
      post_body = opts[:body] 

      # return_type
      return_type = opts[:return_type] || 'Object' 

      # auth_names
      auth_names = opts[:auth_names] || ['bearerAuth']

      new_options = opts.merge(
        :header_params => header_params,
        :query_params => query_params,
        :form_params => form_params,
        :body => post_body,
        :auth_names => auth_names,
        :return_type => return_type
      )

      data, status_code, headers = @api_client.call_api(:GET, local_var_path, new_options)
      if @api_client.config.debugging
        @api_client.config.logger.debug "API called: PublicApi#public_auth_get\nData: #{data.inspect}\nStatus code: #{status_code}\nHeaders: #{headers}"
      end
      return data, status_code, headers
    end

    # Retrieves announcements from the last 30 days.
    # @param [Hash] opts the optional parameters
    # @return [Object]
    def public_get_announcements_get(opts = {})
      data, _status_code, _headers = public_get_announcements_get_with_http_info(opts)
      data
    end

    # Retrieves announcements from the last 30 days.
    # @param [Hash] opts the optional parameters
    # @return [Array<(Object, Integer, Hash)>] Object data, response status code and response headers
    def public_get_announcements_get_with_http_info(opts = {})
      if @api_client.config.debugging
        @api_client.config.logger.debug 'Calling API: PublicApi.public_get_announcements_get ...'
      end
      # resource path
      local_var_path = '/public/get_announcements'

      # query parameters
      query_params = opts[:query_params] || {}

      # header parameters
      header_params = opts[:header_params] || {}
      # HTTP header 'Accept' (if needed)
      header_params['Accept'] = @api_client.select_header_accept(['application/json'])

      # form parameters
      form_params = opts[:form_params] || {}

      # http body (model)
      post_body = opts[:body] 

      # return_type
      return_type = opts[:return_type] || 'Object' 

      # auth_names
      auth_names = opts[:auth_names] || ['bearerAuth']

      new_options = opts.merge(
        :header_params => header_params,
        :query_params => query_params,
        :form_params => form_params,
        :body => post_body,
        :auth_names => auth_names,
        :return_type => return_type
      )

      data, status_code, headers = @api_client.call_api(:GET, local_var_path, new_options)
      if @api_client.config.debugging
        @api_client.config.logger.debug "API called: PublicApi#public_get_announcements_get\nData: #{data.inspect}\nStatus code: #{status_code}\nHeaders: #{headers}"
      end
      return data, status_code, headers
    end

    # Retrieves the summary information such as open interest, 24h volume, etc. for all instruments for the currency (optionally filtered by kind).
    # @param currency [String] The currency symbol
    # @param [Hash] opts the optional parameters
    # @option opts [String] :kind Instrument kind, if not provided instruments of all kinds are considered
    # @return [Object]
    def public_get_book_summary_by_currency_get(currency, opts = {})
      data, _status_code, _headers = public_get_book_summary_by_currency_get_with_http_info(currency, opts)
      data
    end

    # Retrieves the summary information such as open interest, 24h volume, etc. for all instruments for the currency (optionally filtered by kind).
    # @param currency [String] The currency symbol
    # @param [Hash] opts the optional parameters
    # @option opts [String] :kind Instrument kind, if not provided instruments of all kinds are considered
    # @return [Array<(Object, Integer, Hash)>] Object data, response status code and response headers
    def public_get_book_summary_by_currency_get_with_http_info(currency, opts = {})
      if @api_client.config.debugging
        @api_client.config.logger.debug 'Calling API: PublicApi.public_get_book_summary_by_currency_get ...'
      end
      # verify the required parameter 'currency' is set
      if @api_client.config.client_side_validation && currency.nil?
        fail ArgumentError, "Missing the required parameter 'currency' when calling PublicApi.public_get_book_summary_by_currency_get"
      end
      # verify enum value
      allowable_values = ["BTC", "ETH"]
      if @api_client.config.client_side_validation && !allowable_values.include?(currency)
        fail ArgumentError, "invalid value for \"currency\", must be one of #{allowable_values}"
      end
      allowable_values = ["future", "option"]
      if @api_client.config.client_side_validation && opts[:'kind'] && !allowable_values.include?(opts[:'kind'])
        fail ArgumentError, "invalid value for \"kind\", must be one of #{allowable_values}"
      end
      # resource path
      local_var_path = '/public/get_book_summary_by_currency'

      # query parameters
      query_params = opts[:query_params] || {}
      query_params[:'currency'] = currency
      query_params[:'kind'] = opts[:'kind'] if !opts[:'kind'].nil?

      # header parameters
      header_params = opts[:header_params] || {}
      # HTTP header 'Accept' (if needed)
      header_params['Accept'] = @api_client.select_header_accept(['application/json'])

      # form parameters
      form_params = opts[:form_params] || {}

      # http body (model)
      post_body = opts[:body] 

      # return_type
      return_type = opts[:return_type] || 'Object' 

      # auth_names
      auth_names = opts[:auth_names] || ['bearerAuth']

      new_options = opts.merge(
        :header_params => header_params,
        :query_params => query_params,
        :form_params => form_params,
        :body => post_body,
        :auth_names => auth_names,
        :return_type => return_type
      )

      data, status_code, headers = @api_client.call_api(:GET, local_var_path, new_options)
      if @api_client.config.debugging
        @api_client.config.logger.debug "API called: PublicApi#public_get_book_summary_by_currency_get\nData: #{data.inspect}\nStatus code: #{status_code}\nHeaders: #{headers}"
      end
      return data, status_code, headers
    end

    # Retrieves the summary information such as open interest, 24h volume, etc. for a specific instrument.
    # @param instrument_name [String] Instrument name
    # @param [Hash] opts the optional parameters
    # @return [Object]
    def public_get_book_summary_by_instrument_get(instrument_name, opts = {})
      data, _status_code, _headers = public_get_book_summary_by_instrument_get_with_http_info(instrument_name, opts)
      data
    end

    # Retrieves the summary information such as open interest, 24h volume, etc. for a specific instrument.
    # @param instrument_name [String] Instrument name
    # @param [Hash] opts the optional parameters
    # @return [Array<(Object, Integer, Hash)>] Object data, response status code and response headers
    def public_get_book_summary_by_instrument_get_with_http_info(instrument_name, opts = {})
      if @api_client.config.debugging
        @api_client.config.logger.debug 'Calling API: PublicApi.public_get_book_summary_by_instrument_get ...'
      end
      # verify the required parameter 'instrument_name' is set
      if @api_client.config.client_side_validation && instrument_name.nil?
        fail ArgumentError, "Missing the required parameter 'instrument_name' when calling PublicApi.public_get_book_summary_by_instrument_get"
      end
      # resource path
      local_var_path = '/public/get_book_summary_by_instrument'

      # query parameters
      query_params = opts[:query_params] || {}
      query_params[:'instrument_name'] = instrument_name

      # header parameters
      header_params = opts[:header_params] || {}
      # HTTP header 'Accept' (if needed)
      header_params['Accept'] = @api_client.select_header_accept(['application/json'])

      # form parameters
      form_params = opts[:form_params] || {}

      # http body (model)
      post_body = opts[:body] 

      # return_type
      return_type = opts[:return_type] || 'Object' 

      # auth_names
      auth_names = opts[:auth_names] || ['bearerAuth']

      new_options = opts.merge(
        :header_params => header_params,
        :query_params => query_params,
        :form_params => form_params,
        :body => post_body,
        :auth_names => auth_names,
        :return_type => return_type
      )

      data, status_code, headers = @api_client.call_api(:GET, local_var_path, new_options)
      if @api_client.config.debugging
        @api_client.config.logger.debug "API called: PublicApi#public_get_book_summary_by_instrument_get\nData: #{data.inspect}\nStatus code: #{status_code}\nHeaders: #{headers}"
      end
      return data, status_code, headers
    end

    # Retrieves contract size of provided instrument.
    # @param instrument_name [String] Instrument name
    # @param [Hash] opts the optional parameters
    # @return [Object]
    def public_get_contract_size_get(instrument_name, opts = {})
      data, _status_code, _headers = public_get_contract_size_get_with_http_info(instrument_name, opts)
      data
    end

    # Retrieves contract size of provided instrument.
    # @param instrument_name [String] Instrument name
    # @param [Hash] opts the optional parameters
    # @return [Array<(Object, Integer, Hash)>] Object data, response status code and response headers
    def public_get_contract_size_get_with_http_info(instrument_name, opts = {})
      if @api_client.config.debugging
        @api_client.config.logger.debug 'Calling API: PublicApi.public_get_contract_size_get ...'
      end
      # verify the required parameter 'instrument_name' is set
      if @api_client.config.client_side_validation && instrument_name.nil?
        fail ArgumentError, "Missing the required parameter 'instrument_name' when calling PublicApi.public_get_contract_size_get"
      end
      # resource path
      local_var_path = '/public/get_contract_size'

      # query parameters
      query_params = opts[:query_params] || {}
      query_params[:'instrument_name'] = instrument_name

      # header parameters
      header_params = opts[:header_params] || {}
      # HTTP header 'Accept' (if needed)
      header_params['Accept'] = @api_client.select_header_accept(['application/json'])

      # form parameters
      form_params = opts[:form_params] || {}

      # http body (model)
      post_body = opts[:body] 

      # return_type
      return_type = opts[:return_type] || 'Object' 

      # auth_names
      auth_names = opts[:auth_names] || ['bearerAuth']

      new_options = opts.merge(
        :header_params => header_params,
        :query_params => query_params,
        :form_params => form_params,
        :body => post_body,
        :auth_names => auth_names,
        :return_type => return_type
      )

      data, status_code, headers = @api_client.call_api(:GET, local_var_path, new_options)
      if @api_client.config.debugging
        @api_client.config.logger.debug "API called: PublicApi#public_get_contract_size_get\nData: #{data.inspect}\nStatus code: #{status_code}\nHeaders: #{headers}"
      end
      return data, status_code, headers
    end

    # Retrieves all cryptocurrencies supported by the API.
    # @param [Hash] opts the optional parameters
    # @return [Object]
    def public_get_currencies_get(opts = {})
      data, _status_code, _headers = public_get_currencies_get_with_http_info(opts)
      data
    end

    # Retrieves all cryptocurrencies supported by the API.
    # @param [Hash] opts the optional parameters
    # @return [Array<(Object, Integer, Hash)>] Object data, response status code and response headers
    def public_get_currencies_get_with_http_info(opts = {})
      if @api_client.config.debugging
        @api_client.config.logger.debug 'Calling API: PublicApi.public_get_currencies_get ...'
      end
      # resource path
      local_var_path = '/public/get_currencies'

      # query parameters
      query_params = opts[:query_params] || {}

      # header parameters
      header_params = opts[:header_params] || {}
      # HTTP header 'Accept' (if needed)
      header_params['Accept'] = @api_client.select_header_accept(['application/json'])

      # form parameters
      form_params = opts[:form_params] || {}

      # http body (model)
      post_body = opts[:body] 

      # return_type
      return_type = opts[:return_type] || 'Object' 

      # auth_names
      auth_names = opts[:auth_names] || ['bearerAuth']

      new_options = opts.merge(
        :header_params => header_params,
        :query_params => query_params,
        :form_params => form_params,
        :body => post_body,
        :auth_names => auth_names,
        :return_type => return_type
      )

      data, status_code, headers = @api_client.call_api(:GET, local_var_path, new_options)
      if @api_client.config.debugging
        @api_client.config.logger.debug "API called: PublicApi#public_get_currencies_get\nData: #{data.inspect}\nStatus code: #{status_code}\nHeaders: #{headers}"
      end
      return data, status_code, headers
    end

    # Retrieve the latest user trades that have occurred for PERPETUAL instruments in a specific currency symbol and within given time range.
    # @param instrument_name [String] Instrument name
    # @param [Hash] opts the optional parameters
    # @option opts [String] :length Specifies time period. &#x60;8h&#x60; - 8 hours, &#x60;24h&#x60; - 24 hours
    # @return [Object]
    def public_get_funding_chart_data_get(instrument_name, opts = {})
      data, _status_code, _headers = public_get_funding_chart_data_get_with_http_info(instrument_name, opts)
      data
    end

    # Retrieve the latest user trades that have occurred for PERPETUAL instruments in a specific currency symbol and within given time range.
    # @param instrument_name [String] Instrument name
    # @param [Hash] opts the optional parameters
    # @option opts [String] :length Specifies time period. &#x60;8h&#x60; - 8 hours, &#x60;24h&#x60; - 24 hours
    # @return [Array<(Object, Integer, Hash)>] Object data, response status code and response headers
    def public_get_funding_chart_data_get_with_http_info(instrument_name, opts = {})
      if @api_client.config.debugging
        @api_client.config.logger.debug 'Calling API: PublicApi.public_get_funding_chart_data_get ...'
      end
      # verify the required parameter 'instrument_name' is set
      if @api_client.config.client_side_validation && instrument_name.nil?
        fail ArgumentError, "Missing the required parameter 'instrument_name' when calling PublicApi.public_get_funding_chart_data_get"
      end
      allowable_values = ["8h", "24h"]
      if @api_client.config.client_side_validation && opts[:'length'] && !allowable_values.include?(opts[:'length'])
        fail ArgumentError, "invalid value for \"length\", must be one of #{allowable_values}"
      end
      # resource path
      local_var_path = '/public/get_funding_chart_data'

      # query parameters
      query_params = opts[:query_params] || {}
      query_params[:'instrument_name'] = instrument_name
      query_params[:'length'] = opts[:'length'] if !opts[:'length'].nil?

      # header parameters
      header_params = opts[:header_params] || {}
      # HTTP header 'Accept' (if needed)
      header_params['Accept'] = @api_client.select_header_accept(['application/json'])

      # form parameters
      form_params = opts[:form_params] || {}

      # http body (model)
      post_body = opts[:body] 

      # return_type
      return_type = opts[:return_type] || 'Object' 

      # auth_names
      auth_names = opts[:auth_names] || ['bearerAuth']

      new_options = opts.merge(
        :header_params => header_params,
        :query_params => query_params,
        :form_params => form_params,
        :body => post_body,
        :auth_names => auth_names,
        :return_type => return_type
      )

      data, status_code, headers = @api_client.call_api(:GET, local_var_path, new_options)
      if @api_client.config.debugging
        @api_client.config.logger.debug "API called: PublicApi#public_get_funding_chart_data_get\nData: #{data.inspect}\nStatus code: #{status_code}\nHeaders: #{headers}"
      end
      return data, status_code, headers
    end

    # Provides information about historical volatility for given cryptocurrency.
    # @param currency [String] The currency symbol
    # @param [Hash] opts the optional parameters
    # @return [Object]
    def public_get_historical_volatility_get(currency, opts = {})
      data, _status_code, _headers = public_get_historical_volatility_get_with_http_info(currency, opts)
      data
    end

    # Provides information about historical volatility for given cryptocurrency.
    # @param currency [String] The currency symbol
    # @param [Hash] opts the optional parameters
    # @return [Array<(Object, Integer, Hash)>] Object data, response status code and response headers
    def public_get_historical_volatility_get_with_http_info(currency, opts = {})
      if @api_client.config.debugging
        @api_client.config.logger.debug 'Calling API: PublicApi.public_get_historical_volatility_get ...'
      end
      # verify the required parameter 'currency' is set
      if @api_client.config.client_side_validation && currency.nil?
        fail ArgumentError, "Missing the required parameter 'currency' when calling PublicApi.public_get_historical_volatility_get"
      end
      # verify enum value
      allowable_values = ["BTC", "ETH"]
      if @api_client.config.client_side_validation && !allowable_values.include?(currency)
        fail ArgumentError, "invalid value for \"currency\", must be one of #{allowable_values}"
      end
      # resource path
      local_var_path = '/public/get_historical_volatility'

      # query parameters
      query_params = opts[:query_params] || {}
      query_params[:'currency'] = currency

      # header parameters
      header_params = opts[:header_params] || {}
      # HTTP header 'Accept' (if needed)
      header_params['Accept'] = @api_client.select_header_accept(['application/json'])

      # form parameters
      form_params = opts[:form_params] || {}

      # http body (model)
      post_body = opts[:body] 

      # return_type
      return_type = opts[:return_type] || 'Object' 

      # auth_names
      auth_names = opts[:auth_names] || ['bearerAuth']

      new_options = opts.merge(
        :header_params => header_params,
        :query_params => query_params,
        :form_params => form_params,
        :body => post_body,
        :auth_names => auth_names,
        :return_type => return_type
      )

      data, status_code, headers = @api_client.call_api(:GET, local_var_path, new_options)
      if @api_client.config.debugging
        @api_client.config.logger.debug "API called: PublicApi#public_get_historical_volatility_get\nData: #{data.inspect}\nStatus code: #{status_code}\nHeaders: #{headers}"
      end
      return data, status_code, headers
    end

    # Retrieves the current index price for the instruments, for the selected currency.
    # @param currency [String] The currency symbol
    # @param [Hash] opts the optional parameters
    # @return [Object]
    def public_get_index_get(currency, opts = {})
      data, _status_code, _headers = public_get_index_get_with_http_info(currency, opts)
      data
    end

    # Retrieves the current index price for the instruments, for the selected currency.
    # @param currency [String] The currency symbol
    # @param [Hash] opts the optional parameters
    # @return [Array<(Object, Integer, Hash)>] Object data, response status code and response headers
    def public_get_index_get_with_http_info(currency, opts = {})
      if @api_client.config.debugging
        @api_client.config.logger.debug 'Calling API: PublicApi.public_get_index_get ...'
      end
      # verify the required parameter 'currency' is set
      if @api_client.config.client_side_validation && currency.nil?
        fail ArgumentError, "Missing the required parameter 'currency' when calling PublicApi.public_get_index_get"
      end
      # verify enum value
      allowable_values = ["BTC", "ETH"]
      if @api_client.config.client_side_validation && !allowable_values.include?(currency)
        fail ArgumentError, "invalid value for \"currency\", must be one of #{allowable_values}"
      end
      # resource path
      local_var_path = '/public/get_index'

      # query parameters
      query_params = opts[:query_params] || {}
      query_params[:'currency'] = currency

      # header parameters
      header_params = opts[:header_params] || {}
      # HTTP header 'Accept' (if needed)
      header_params['Accept'] = @api_client.select_header_accept(['application/json'])

      # form parameters
      form_params = opts[:form_params] || {}

      # http body (model)
      post_body = opts[:body] 

      # return_type
      return_type = opts[:return_type] || 'Object' 

      # auth_names
      auth_names = opts[:auth_names] || ['bearerAuth']

      new_options = opts.merge(
        :header_params => header_params,
        :query_params => query_params,
        :form_params => form_params,
        :body => post_body,
        :auth_names => auth_names,
        :return_type => return_type
      )

      data, status_code, headers = @api_client.call_api(:GET, local_var_path, new_options)
      if @api_client.config.debugging
        @api_client.config.logger.debug "API called: PublicApi#public_get_index_get\nData: #{data.inspect}\nStatus code: #{status_code}\nHeaders: #{headers}"
      end
      return data, status_code, headers
    end

    # Retrieves available trading instruments. This method can be used to see which instruments are available for trading, or which instruments have existed historically.
    # @param currency [String] The currency symbol
    # @param [Hash] opts the optional parameters
    # @option opts [String] :kind Instrument kind, if not provided instruments of all kinds are considered
    # @option opts [Boolean] :expired Set to true to show expired instruments instead of active ones. (default to false)
    # @return [Object]
    def public_get_instruments_get(currency, opts = {})
      data, _status_code, _headers = public_get_instruments_get_with_http_info(currency, opts)
      data
    end

    # Retrieves available trading instruments. This method can be used to see which instruments are available for trading, or which instruments have existed historically.
    # @param currency [String] The currency symbol
    # @param [Hash] opts the optional parameters
    # @option opts [String] :kind Instrument kind, if not provided instruments of all kinds are considered
    # @option opts [Boolean] :expired Set to true to show expired instruments instead of active ones.
    # @return [Array<(Object, Integer, Hash)>] Object data, response status code and response headers
    def public_get_instruments_get_with_http_info(currency, opts = {})
      if @api_client.config.debugging
        @api_client.config.logger.debug 'Calling API: PublicApi.public_get_instruments_get ...'
      end
      # verify the required parameter 'currency' is set
      if @api_client.config.client_side_validation && currency.nil?
        fail ArgumentError, "Missing the required parameter 'currency' when calling PublicApi.public_get_instruments_get"
      end
      # verify enum value
      allowable_values = ["BTC", "ETH"]
      if @api_client.config.client_side_validation && !allowable_values.include?(currency)
        fail ArgumentError, "invalid value for \"currency\", must be one of #{allowable_values}"
      end
      allowable_values = ["future", "option"]
      if @api_client.config.client_side_validation && opts[:'kind'] && !allowable_values.include?(opts[:'kind'])
        fail ArgumentError, "invalid value for \"kind\", must be one of #{allowable_values}"
      end
      # resource path
      local_var_path = '/public/get_instruments'

      # query parameters
      query_params = opts[:query_params] || {}
      query_params[:'currency'] = currency
      query_params[:'kind'] = opts[:'kind'] if !opts[:'kind'].nil?
      query_params[:'expired'] = opts[:'expired'] if !opts[:'expired'].nil?

      # header parameters
      header_params = opts[:header_params] || {}
      # HTTP header 'Accept' (if needed)
      header_params['Accept'] = @api_client.select_header_accept(['application/json'])

      # form parameters
      form_params = opts[:form_params] || {}

      # http body (model)
      post_body = opts[:body] 

      # return_type
      return_type = opts[:return_type] || 'Object' 

      # auth_names
      auth_names = opts[:auth_names] || ['bearerAuth']

      new_options = opts.merge(
        :header_params => header_params,
        :query_params => query_params,
        :form_params => form_params,
        :body => post_body,
        :auth_names => auth_names,
        :return_type => return_type
      )

      data, status_code, headers = @api_client.call_api(:GET, local_var_path, new_options)
      if @api_client.config.debugging
        @api_client.config.logger.debug "API called: PublicApi#public_get_instruments_get\nData: #{data.inspect}\nStatus code: #{status_code}\nHeaders: #{headers}"
      end
      return data, status_code, headers
    end

    # Retrieves historical settlement, delivery and bankruptcy events coming from all instruments within given currency.
    # @param currency [String] The currency symbol
    # @param [Hash] opts the optional parameters
    # @option opts [String] :type Settlement type
    # @option opts [Integer] :count Number of requested items, default - &#x60;20&#x60;
    # @option opts [String] :continuation Continuation token for pagination
    # @option opts [Integer] :search_start_timestamp The latest timestamp to return result for
    # @return [Object]
    def public_get_last_settlements_by_currency_get(currency, opts = {})
      data, _status_code, _headers = public_get_last_settlements_by_currency_get_with_http_info(currency, opts)
      data
    end

    # Retrieves historical settlement, delivery and bankruptcy events coming from all instruments within given currency.
    # @param currency [String] The currency symbol
    # @param [Hash] opts the optional parameters
    # @option opts [String] :type Settlement type
    # @option opts [Integer] :count Number of requested items, default - &#x60;20&#x60;
    # @option opts [String] :continuation Continuation token for pagination
    # @option opts [Integer] :search_start_timestamp The latest timestamp to return result for
    # @return [Array<(Object, Integer, Hash)>] Object data, response status code and response headers
    def public_get_last_settlements_by_currency_get_with_http_info(currency, opts = {})
      if @api_client.config.debugging
        @api_client.config.logger.debug 'Calling API: PublicApi.public_get_last_settlements_by_currency_get ...'
      end
      # verify the required parameter 'currency' is set
      if @api_client.config.client_side_validation && currency.nil?
        fail ArgumentError, "Missing the required parameter 'currency' when calling PublicApi.public_get_last_settlements_by_currency_get"
      end
      # verify enum value
      allowable_values = ["BTC", "ETH"]
      if @api_client.config.client_side_validation && !allowable_values.include?(currency)
        fail ArgumentError, "invalid value for \"currency\", must be one of #{allowable_values}"
      end
      allowable_values = ["settlement", "delivery", "bankruptcy"]
      if @api_client.config.client_side_validation && opts[:'type'] && !allowable_values.include?(opts[:'type'])
        fail ArgumentError, "invalid value for \"type\", must be one of #{allowable_values}"
      end
      # resource path
      local_var_path = '/public/get_last_settlements_by_currency'

      # query parameters
      query_params = opts[:query_params] || {}
      query_params[:'currency'] = currency
      query_params[:'type'] = opts[:'type'] if !opts[:'type'].nil?
      query_params[:'count'] = opts[:'count'] if !opts[:'count'].nil?
      query_params[:'continuation'] = opts[:'continuation'] if !opts[:'continuation'].nil?
      query_params[:'search_start_timestamp'] = opts[:'search_start_timestamp'] if !opts[:'search_start_timestamp'].nil?

      # header parameters
      header_params = opts[:header_params] || {}
      # HTTP header 'Accept' (if needed)
      header_params['Accept'] = @api_client.select_header_accept(['application/json'])

      # form parameters
      form_params = opts[:form_params] || {}

      # http body (model)
      post_body = opts[:body] 

      # return_type
      return_type = opts[:return_type] || 'Object' 

      # auth_names
      auth_names = opts[:auth_names] || ['bearerAuth']

      new_options = opts.merge(
        :header_params => header_params,
        :query_params => query_params,
        :form_params => form_params,
        :body => post_body,
        :auth_names => auth_names,
        :return_type => return_type
      )

      data, status_code, headers = @api_client.call_api(:GET, local_var_path, new_options)
      if @api_client.config.debugging
        @api_client.config.logger.debug "API called: PublicApi#public_get_last_settlements_by_currency_get\nData: #{data.inspect}\nStatus code: #{status_code}\nHeaders: #{headers}"
      end
      return data, status_code, headers
    end

    # Retrieves historical public settlement, delivery and bankruptcy events filtered by instrument name.
    # @param instrument_name [String] Instrument name
    # @param [Hash] opts the optional parameters
    # @option opts [String] :type Settlement type
    # @option opts [Integer] :count Number of requested items, default - &#x60;20&#x60;
    # @option opts [String] :continuation Continuation token for pagination
    # @option opts [Integer] :search_start_timestamp The latest timestamp to return result for
    # @return [Object]
    def public_get_last_settlements_by_instrument_get(instrument_name, opts = {})
      data, _status_code, _headers = public_get_last_settlements_by_instrument_get_with_http_info(instrument_name, opts)
      data
    end

    # Retrieves historical public settlement, delivery and bankruptcy events filtered by instrument name.
    # @param instrument_name [String] Instrument name
    # @param [Hash] opts the optional parameters
    # @option opts [String] :type Settlement type
    # @option opts [Integer] :count Number of requested items, default - &#x60;20&#x60;
    # @option opts [String] :continuation Continuation token for pagination
    # @option opts [Integer] :search_start_timestamp The latest timestamp to return result for
    # @return [Array<(Object, Integer, Hash)>] Object data, response status code and response headers
    def public_get_last_settlements_by_instrument_get_with_http_info(instrument_name, opts = {})
      if @api_client.config.debugging
        @api_client.config.logger.debug 'Calling API: PublicApi.public_get_last_settlements_by_instrument_get ...'
      end
      # verify the required parameter 'instrument_name' is set
      if @api_client.config.client_side_validation && instrument_name.nil?
        fail ArgumentError, "Missing the required parameter 'instrument_name' when calling PublicApi.public_get_last_settlements_by_instrument_get"
      end
      allowable_values = ["settlement", "delivery", "bankruptcy"]
      if @api_client.config.client_side_validation && opts[:'type'] && !allowable_values.include?(opts[:'type'])
        fail ArgumentError, "invalid value for \"type\", must be one of #{allowable_values}"
      end
      # resource path
      local_var_path = '/public/get_last_settlements_by_instrument'

      # query parameters
      query_params = opts[:query_params] || {}
      query_params[:'instrument_name'] = instrument_name
      query_params[:'type'] = opts[:'type'] if !opts[:'type'].nil?
      query_params[:'count'] = opts[:'count'] if !opts[:'count'].nil?
      query_params[:'continuation'] = opts[:'continuation'] if !opts[:'continuation'].nil?
      query_params[:'search_start_timestamp'] = opts[:'search_start_timestamp'] if !opts[:'search_start_timestamp'].nil?

      # header parameters
      header_params = opts[:header_params] || {}
      # HTTP header 'Accept' (if needed)
      header_params['Accept'] = @api_client.select_header_accept(['application/json'])

      # form parameters
      form_params = opts[:form_params] || {}

      # http body (model)
      post_body = opts[:body] 

      # return_type
      return_type = opts[:return_type] || 'Object' 

      # auth_names
      auth_names = opts[:auth_names] || ['bearerAuth']

      new_options = opts.merge(
        :header_params => header_params,
        :query_params => query_params,
        :form_params => form_params,
        :body => post_body,
        :auth_names => auth_names,
        :return_type => return_type
      )

      data, status_code, headers = @api_client.call_api(:GET, local_var_path, new_options)
      if @api_client.config.debugging
        @api_client.config.logger.debug "API called: PublicApi#public_get_last_settlements_by_instrument_get\nData: #{data.inspect}\nStatus code: #{status_code}\nHeaders: #{headers}"
      end
      return data, status_code, headers
    end

    # Retrieve the latest trades that have occurred for instruments in a specific currency symbol and within given time range.
    # @param currency [String] The currency symbol
    # @param start_timestamp [Integer] The earliest timestamp to return result for
    # @param end_timestamp [Integer] The most recent timestamp to return result for
    # @param [Hash] opts the optional parameters
    # @option opts [String] :kind Instrument kind, if not provided instruments of all kinds are considered
    # @option opts [Integer] :count Number of requested items, default - &#x60;10&#x60;
    # @option opts [Boolean] :include_old Include trades older than 7 days, default - &#x60;false&#x60;
    # @option opts [String] :sorting Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database)
    # @return [Object]
    def public_get_last_trades_by_currency_and_time_get(currency, start_timestamp, end_timestamp, opts = {})
      data, _status_code, _headers = public_get_last_trades_by_currency_and_time_get_with_http_info(currency, start_timestamp, end_timestamp, opts)
      data
    end

    # Retrieve the latest trades that have occurred for instruments in a specific currency symbol and within given time range.
    # @param currency [String] The currency symbol
    # @param start_timestamp [Integer] The earliest timestamp to return result for
    # @param end_timestamp [Integer] The most recent timestamp to return result for
    # @param [Hash] opts the optional parameters
    # @option opts [String] :kind Instrument kind, if not provided instruments of all kinds are considered
    # @option opts [Integer] :count Number of requested items, default - &#x60;10&#x60;
    # @option opts [Boolean] :include_old Include trades older than 7 days, default - &#x60;false&#x60;
    # @option opts [String] :sorting Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database)
    # @return [Array<(Object, Integer, Hash)>] Object data, response status code and response headers
    def public_get_last_trades_by_currency_and_time_get_with_http_info(currency, start_timestamp, end_timestamp, opts = {})
      if @api_client.config.debugging
        @api_client.config.logger.debug 'Calling API: PublicApi.public_get_last_trades_by_currency_and_time_get ...'
      end
      # verify the required parameter 'currency' is set
      if @api_client.config.client_side_validation && currency.nil?
        fail ArgumentError, "Missing the required parameter 'currency' when calling PublicApi.public_get_last_trades_by_currency_and_time_get"
      end
      # verify enum value
      allowable_values = ["BTC", "ETH"]
      if @api_client.config.client_side_validation && !allowable_values.include?(currency)
        fail ArgumentError, "invalid value for \"currency\", must be one of #{allowable_values}"
      end
      # verify the required parameter 'start_timestamp' is set
      if @api_client.config.client_side_validation && start_timestamp.nil?
        fail ArgumentError, "Missing the required parameter 'start_timestamp' when calling PublicApi.public_get_last_trades_by_currency_and_time_get"
      end
      # verify the required parameter 'end_timestamp' is set
      if @api_client.config.client_side_validation && end_timestamp.nil?
        fail ArgumentError, "Missing the required parameter 'end_timestamp' when calling PublicApi.public_get_last_trades_by_currency_and_time_get"
      end
      allowable_values = ["future", "option"]
      if @api_client.config.client_side_validation && opts[:'kind'] && !allowable_values.include?(opts[:'kind'])
        fail ArgumentError, "invalid value for \"kind\", must be one of #{allowable_values}"
      end
      allowable_values = ["asc", "desc", "default"]
      if @api_client.config.client_side_validation && opts[:'sorting'] && !allowable_values.include?(opts[:'sorting'])
        fail ArgumentError, "invalid value for \"sorting\", must be one of #{allowable_values}"
      end
      # resource path
      local_var_path = '/public/get_last_trades_by_currency_and_time'

      # query parameters
      query_params = opts[:query_params] || {}
      query_params[:'currency'] = currency
      query_params[:'start_timestamp'] = start_timestamp
      query_params[:'end_timestamp'] = end_timestamp
      query_params[:'kind'] = opts[:'kind'] if !opts[:'kind'].nil?
      query_params[:'count'] = opts[:'count'] if !opts[:'count'].nil?
      query_params[:'include_old'] = opts[:'include_old'] if !opts[:'include_old'].nil?
      query_params[:'sorting'] = opts[:'sorting'] if !opts[:'sorting'].nil?

      # header parameters
      header_params = opts[:header_params] || {}
      # HTTP header 'Accept' (if needed)
      header_params['Accept'] = @api_client.select_header_accept(['application/json'])

      # form parameters
      form_params = opts[:form_params] || {}

      # http body (model)
      post_body = opts[:body] 

      # return_type
      return_type = opts[:return_type] || 'Object' 

      # auth_names
      auth_names = opts[:auth_names] || ['bearerAuth']

      new_options = opts.merge(
        :header_params => header_params,
        :query_params => query_params,
        :form_params => form_params,
        :body => post_body,
        :auth_names => auth_names,
        :return_type => return_type
      )

      data, status_code, headers = @api_client.call_api(:GET, local_var_path, new_options)
      if @api_client.config.debugging
        @api_client.config.logger.debug "API called: PublicApi#public_get_last_trades_by_currency_and_time_get\nData: #{data.inspect}\nStatus code: #{status_code}\nHeaders: #{headers}"
      end
      return data, status_code, headers
    end

    # Retrieve the latest trades that have occurred for instruments in a specific currency symbol.
    # @param currency [String] The currency symbol
    # @param [Hash] opts the optional parameters
    # @option opts [String] :kind Instrument kind, if not provided instruments of all kinds are considered
    # @option opts [String] :start_id The ID number of the first trade to be returned
    # @option opts [String] :end_id The ID number of the last trade to be returned
    # @option opts [Integer] :count Number of requested items, default - &#x60;10&#x60;
    # @option opts [Boolean] :include_old Include trades older than 7 days, default - &#x60;false&#x60;
    # @option opts [String] :sorting Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database)
    # @return [Object]
    def public_get_last_trades_by_currency_get(currency, opts = {})
      data, _status_code, _headers = public_get_last_trades_by_currency_get_with_http_info(currency, opts)
      data
    end

    # Retrieve the latest trades that have occurred for instruments in a specific currency symbol.
    # @param currency [String] The currency symbol
    # @param [Hash] opts the optional parameters
    # @option opts [String] :kind Instrument kind, if not provided instruments of all kinds are considered
    # @option opts [String] :start_id The ID number of the first trade to be returned
    # @option opts [String] :end_id The ID number of the last trade to be returned
    # @option opts [Integer] :count Number of requested items, default - &#x60;10&#x60;
    # @option opts [Boolean] :include_old Include trades older than 7 days, default - &#x60;false&#x60;
    # @option opts [String] :sorting Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database)
    # @return [Array<(Object, Integer, Hash)>] Object data, response status code and response headers
    def public_get_last_trades_by_currency_get_with_http_info(currency, opts = {})
      if @api_client.config.debugging
        @api_client.config.logger.debug 'Calling API: PublicApi.public_get_last_trades_by_currency_get ...'
      end
      # verify the required parameter 'currency' is set
      if @api_client.config.client_side_validation && currency.nil?
        fail ArgumentError, "Missing the required parameter 'currency' when calling PublicApi.public_get_last_trades_by_currency_get"
      end
      # verify enum value
      allowable_values = ["BTC", "ETH"]
      if @api_client.config.client_side_validation && !allowable_values.include?(currency)
        fail ArgumentError, "invalid value for \"currency\", must be one of #{allowable_values}"
      end
      allowable_values = ["future", "option"]
      if @api_client.config.client_side_validation && opts[:'kind'] && !allowable_values.include?(opts[:'kind'])
        fail ArgumentError, "invalid value for \"kind\", must be one of #{allowable_values}"
      end
      allowable_values = ["asc", "desc", "default"]
      if @api_client.config.client_side_validation && opts[:'sorting'] && !allowable_values.include?(opts[:'sorting'])
        fail ArgumentError, "invalid value for \"sorting\", must be one of #{allowable_values}"
      end
      # resource path
      local_var_path = '/public/get_last_trades_by_currency'

      # query parameters
      query_params = opts[:query_params] || {}
      query_params[:'currency'] = currency
      query_params[:'kind'] = opts[:'kind'] if !opts[:'kind'].nil?
      query_params[:'start_id'] = opts[:'start_id'] if !opts[:'start_id'].nil?
      query_params[:'end_id'] = opts[:'end_id'] if !opts[:'end_id'].nil?
      query_params[:'count'] = opts[:'count'] if !opts[:'count'].nil?
      query_params[:'include_old'] = opts[:'include_old'] if !opts[:'include_old'].nil?
      query_params[:'sorting'] = opts[:'sorting'] if !opts[:'sorting'].nil?

      # header parameters
      header_params = opts[:header_params] || {}
      # HTTP header 'Accept' (if needed)
      header_params['Accept'] = @api_client.select_header_accept(['application/json'])

      # form parameters
      form_params = opts[:form_params] || {}

      # http body (model)
      post_body = opts[:body] 

      # return_type
      return_type = opts[:return_type] || 'Object' 

      # auth_names
      auth_names = opts[:auth_names] || ['bearerAuth']

      new_options = opts.merge(
        :header_params => header_params,
        :query_params => query_params,
        :form_params => form_params,
        :body => post_body,
        :auth_names => auth_names,
        :return_type => return_type
      )

      data, status_code, headers = @api_client.call_api(:GET, local_var_path, new_options)
      if @api_client.config.debugging
        @api_client.config.logger.debug "API called: PublicApi#public_get_last_trades_by_currency_get\nData: #{data.inspect}\nStatus code: #{status_code}\nHeaders: #{headers}"
      end
      return data, status_code, headers
    end

    # Retrieve the latest trades that have occurred for a specific instrument and within given time range.
    # @param instrument_name [String] Instrument name
    # @param start_timestamp [Integer] The earliest timestamp to return result for
    # @param end_timestamp [Integer] The most recent timestamp to return result for
    # @param [Hash] opts the optional parameters
    # @option opts [Integer] :count Number of requested items, default - &#x60;10&#x60;
    # @option opts [Boolean] :include_old Include trades older than 7 days, default - &#x60;false&#x60;
    # @option opts [String] :sorting Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database)
    # @return [Object]
    def public_get_last_trades_by_instrument_and_time_get(instrument_name, start_timestamp, end_timestamp, opts = {})
      data, _status_code, _headers = public_get_last_trades_by_instrument_and_time_get_with_http_info(instrument_name, start_timestamp, end_timestamp, opts)
      data
    end

    # Retrieve the latest trades that have occurred for a specific instrument and within given time range.
    # @param instrument_name [String] Instrument name
    # @param start_timestamp [Integer] The earliest timestamp to return result for
    # @param end_timestamp [Integer] The most recent timestamp to return result for
    # @param [Hash] opts the optional parameters
    # @option opts [Integer] :count Number of requested items, default - &#x60;10&#x60;
    # @option opts [Boolean] :include_old Include trades older than 7 days, default - &#x60;false&#x60;
    # @option opts [String] :sorting Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database)
    # @return [Array<(Object, Integer, Hash)>] Object data, response status code and response headers
    def public_get_last_trades_by_instrument_and_time_get_with_http_info(instrument_name, start_timestamp, end_timestamp, opts = {})
      if @api_client.config.debugging
        @api_client.config.logger.debug 'Calling API: PublicApi.public_get_last_trades_by_instrument_and_time_get ...'
      end
      # verify the required parameter 'instrument_name' is set
      if @api_client.config.client_side_validation && instrument_name.nil?
        fail ArgumentError, "Missing the required parameter 'instrument_name' when calling PublicApi.public_get_last_trades_by_instrument_and_time_get"
      end
      # verify the required parameter 'start_timestamp' is set
      if @api_client.config.client_side_validation && start_timestamp.nil?
        fail ArgumentError, "Missing the required parameter 'start_timestamp' when calling PublicApi.public_get_last_trades_by_instrument_and_time_get"
      end
      # verify the required parameter 'end_timestamp' is set
      if @api_client.config.client_side_validation && end_timestamp.nil?
        fail ArgumentError, "Missing the required parameter 'end_timestamp' when calling PublicApi.public_get_last_trades_by_instrument_and_time_get"
      end
      allowable_values = ["asc", "desc", "default"]
      if @api_client.config.client_side_validation && opts[:'sorting'] && !allowable_values.include?(opts[:'sorting'])
        fail ArgumentError, "invalid value for \"sorting\", must be one of #{allowable_values}"
      end
      # resource path
      local_var_path = '/public/get_last_trades_by_instrument_and_time'

      # query parameters
      query_params = opts[:query_params] || {}
      query_params[:'instrument_name'] = instrument_name
      query_params[:'start_timestamp'] = start_timestamp
      query_params[:'end_timestamp'] = end_timestamp
      query_params[:'count'] = opts[:'count'] if !opts[:'count'].nil?
      query_params[:'include_old'] = opts[:'include_old'] if !opts[:'include_old'].nil?
      query_params[:'sorting'] = opts[:'sorting'] if !opts[:'sorting'].nil?

      # header parameters
      header_params = opts[:header_params] || {}
      # HTTP header 'Accept' (if needed)
      header_params['Accept'] = @api_client.select_header_accept(['application/json'])

      # form parameters
      form_params = opts[:form_params] || {}

      # http body (model)
      post_body = opts[:body] 

      # return_type
      return_type = opts[:return_type] || 'Object' 

      # auth_names
      auth_names = opts[:auth_names] || ['bearerAuth']

      new_options = opts.merge(
        :header_params => header_params,
        :query_params => query_params,
        :form_params => form_params,
        :body => post_body,
        :auth_names => auth_names,
        :return_type => return_type
      )

      data, status_code, headers = @api_client.call_api(:GET, local_var_path, new_options)
      if @api_client.config.debugging
        @api_client.config.logger.debug "API called: PublicApi#public_get_last_trades_by_instrument_and_time_get\nData: #{data.inspect}\nStatus code: #{status_code}\nHeaders: #{headers}"
      end
      return data, status_code, headers
    end

    # Retrieve the latest trades that have occurred for a specific instrument.
    # @param instrument_name [String] Instrument name
    # @param [Hash] opts the optional parameters
    # @option opts [Integer] :start_seq The sequence number of the first trade to be returned
    # @option opts [Integer] :end_seq The sequence number of the last trade to be returned
    # @option opts [Integer] :count Number of requested items, default - &#x60;10&#x60;
    # @option opts [Boolean] :include_old Include trades older than 7 days, default - &#x60;false&#x60;
    # @option opts [String] :sorting Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database)
    # @return [Object]
    def public_get_last_trades_by_instrument_get(instrument_name, opts = {})
      data, _status_code, _headers = public_get_last_trades_by_instrument_get_with_http_info(instrument_name, opts)
      data
    end

    # Retrieve the latest trades that have occurred for a specific instrument.
    # @param instrument_name [String] Instrument name
    # @param [Hash] opts the optional parameters
    # @option opts [Integer] :start_seq The sequence number of the first trade to be returned
    # @option opts [Integer] :end_seq The sequence number of the last trade to be returned
    # @option opts [Integer] :count Number of requested items, default - &#x60;10&#x60;
    # @option opts [Boolean] :include_old Include trades older than 7 days, default - &#x60;false&#x60;
    # @option opts [String] :sorting Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database)
    # @return [Array<(Object, Integer, Hash)>] Object data, response status code and response headers
    def public_get_last_trades_by_instrument_get_with_http_info(instrument_name, opts = {})
      if @api_client.config.debugging
        @api_client.config.logger.debug 'Calling API: PublicApi.public_get_last_trades_by_instrument_get ...'
      end
      # verify the required parameter 'instrument_name' is set
      if @api_client.config.client_side_validation && instrument_name.nil?
        fail ArgumentError, "Missing the required parameter 'instrument_name' when calling PublicApi.public_get_last_trades_by_instrument_get"
      end
      allowable_values = ["asc", "desc", "default"]
      if @api_client.config.client_side_validation && opts[:'sorting'] && !allowable_values.include?(opts[:'sorting'])
        fail ArgumentError, "invalid value for \"sorting\", must be one of #{allowable_values}"
      end
      # resource path
      local_var_path = '/public/get_last_trades_by_instrument'

      # query parameters
      query_params = opts[:query_params] || {}
      query_params[:'instrument_name'] = instrument_name
      query_params[:'start_seq'] = opts[:'start_seq'] if !opts[:'start_seq'].nil?
      query_params[:'end_seq'] = opts[:'end_seq'] if !opts[:'end_seq'].nil?
      query_params[:'count'] = opts[:'count'] if !opts[:'count'].nil?
      query_params[:'include_old'] = opts[:'include_old'] if !opts[:'include_old'].nil?
      query_params[:'sorting'] = opts[:'sorting'] if !opts[:'sorting'].nil?

      # header parameters
      header_params = opts[:header_params] || {}
      # HTTP header 'Accept' (if needed)
      header_params['Accept'] = @api_client.select_header_accept(['application/json'])

      # form parameters
      form_params = opts[:form_params] || {}

      # http body (model)
      post_body = opts[:body] 

      # return_type
      return_type = opts[:return_type] || 'Object' 

      # auth_names
      auth_names = opts[:auth_names] || ['bearerAuth']

      new_options = opts.merge(
        :header_params => header_params,
        :query_params => query_params,
        :form_params => form_params,
        :body => post_body,
        :auth_names => auth_names,
        :return_type => return_type
      )

      data, status_code, headers = @api_client.call_api(:GET, local_var_path, new_options)
      if @api_client.config.debugging
        @api_client.config.logger.debug "API called: PublicApi#public_get_last_trades_by_instrument_get\nData: #{data.inspect}\nStatus code: #{status_code}\nHeaders: #{headers}"
      end
      return data, status_code, headers
    end

    # Retrieves the order book, along with other market values for a given instrument.
    # @param instrument_name [String] The instrument name for which to retrieve the order book, see [&#x60;getinstruments&#x60;](#getinstruments) to obtain instrument names.
    # @param [Hash] opts the optional parameters
    # @option opts [Float] :depth The number of entries to return for bids and asks.
    # @return [Object]
    def public_get_order_book_get(instrument_name, opts = {})
      data, _status_code, _headers = public_get_order_book_get_with_http_info(instrument_name, opts)
      data
    end

    # Retrieves the order book, along with other market values for a given instrument.
    # @param instrument_name [String] The instrument name for which to retrieve the order book, see [&#x60;getinstruments&#x60;](#getinstruments) to obtain instrument names.
    # @param [Hash] opts the optional parameters
    # @option opts [Float] :depth The number of entries to return for bids and asks.
    # @return [Array<(Object, Integer, Hash)>] Object data, response status code and response headers
    def public_get_order_book_get_with_http_info(instrument_name, opts = {})
      if @api_client.config.debugging
        @api_client.config.logger.debug 'Calling API: PublicApi.public_get_order_book_get ...'
      end
      # verify the required parameter 'instrument_name' is set
      if @api_client.config.client_side_validation && instrument_name.nil?
        fail ArgumentError, "Missing the required parameter 'instrument_name' when calling PublicApi.public_get_order_book_get"
      end
      # resource path
      local_var_path = '/public/get_order_book'

      # query parameters
      query_params = opts[:query_params] || {}
      query_params[:'instrument_name'] = instrument_name
      query_params[:'depth'] = opts[:'depth'] if !opts[:'depth'].nil?

      # header parameters
      header_params = opts[:header_params] || {}
      # HTTP header 'Accept' (if needed)
      header_params['Accept'] = @api_client.select_header_accept(['application/json'])

      # form parameters
      form_params = opts[:form_params] || {}

      # http body (model)
      post_body = opts[:body] 

      # return_type
      return_type = opts[:return_type] || 'Object' 

      # auth_names
      auth_names = opts[:auth_names] || ['bearerAuth']

      new_options = opts.merge(
        :header_params => header_params,
        :query_params => query_params,
        :form_params => form_params,
        :body => post_body,
        :auth_names => auth_names,
        :return_type => return_type
      )

      data, status_code, headers = @api_client.call_api(:GET, local_var_path, new_options)
      if @api_client.config.debugging
        @api_client.config.logger.debug "API called: PublicApi#public_get_order_book_get\nData: #{data.inspect}\nStatus code: #{status_code}\nHeaders: #{headers}"
      end
      return data, status_code, headers
    end

    # Retrieves the current time (in milliseconds). This API endpoint can be used to check the clock skew between your software and Deribit's systems.
    # @param [Hash] opts the optional parameters
    # @return [Object]
    def public_get_time_get(opts = {})
      data, _status_code, _headers = public_get_time_get_with_http_info(opts)
      data
    end

    # Retrieves the current time (in milliseconds). This API endpoint can be used to check the clock skew between your software and Deribit&#39;s systems.
    # @param [Hash] opts the optional parameters
    # @return [Array<(Object, Integer, Hash)>] Object data, response status code and response headers
    def public_get_time_get_with_http_info(opts = {})
      if @api_client.config.debugging
        @api_client.config.logger.debug 'Calling API: PublicApi.public_get_time_get ...'
      end
      # resource path
      local_var_path = '/public/get_time'

      # query parameters
      query_params = opts[:query_params] || {}

      # header parameters
      header_params = opts[:header_params] || {}
      # HTTP header 'Accept' (if needed)
      header_params['Accept'] = @api_client.select_header_accept(['application/json'])

      # form parameters
      form_params = opts[:form_params] || {}

      # http body (model)
      post_body = opts[:body] 

      # return_type
      return_type = opts[:return_type] || 'Object' 

      # auth_names
      auth_names = opts[:auth_names] || ['bearerAuth']

      new_options = opts.merge(
        :header_params => header_params,
        :query_params => query_params,
        :form_params => form_params,
        :body => post_body,
        :auth_names => auth_names,
        :return_type => return_type
      )

      data, status_code, headers = @api_client.call_api(:GET, local_var_path, new_options)
      if @api_client.config.debugging
        @api_client.config.logger.debug "API called: PublicApi#public_get_time_get\nData: #{data.inspect}\nStatus code: #{status_code}\nHeaders: #{headers}"
      end
      return data, status_code, headers
    end

    # Retrieves aggregated 24h trade volumes for different instrument types and currencies.
    # @param [Hash] opts the optional parameters
    # @return [Object]
    def public_get_trade_volumes_get(opts = {})
      data, _status_code, _headers = public_get_trade_volumes_get_with_http_info(opts)
      data
    end

    # Retrieves aggregated 24h trade volumes for different instrument types and currencies.
    # @param [Hash] opts the optional parameters
    # @return [Array<(Object, Integer, Hash)>] Object data, response status code and response headers
    def public_get_trade_volumes_get_with_http_info(opts = {})
      if @api_client.config.debugging
        @api_client.config.logger.debug 'Calling API: PublicApi.public_get_trade_volumes_get ...'
      end
      # resource path
      local_var_path = '/public/get_trade_volumes'

      # query parameters
      query_params = opts[:query_params] || {}

      # header parameters
      header_params = opts[:header_params] || {}
      # HTTP header 'Accept' (if needed)
      header_params['Accept'] = @api_client.select_header_accept(['application/json'])

      # form parameters
      form_params = opts[:form_params] || {}

      # http body (model)
      post_body = opts[:body] 

      # return_type
      return_type = opts[:return_type] || 'Object' 

      # auth_names
      auth_names = opts[:auth_names] || ['bearerAuth']

      new_options = opts.merge(
        :header_params => header_params,
        :query_params => query_params,
        :form_params => form_params,
        :body => post_body,
        :auth_names => auth_names,
        :return_type => return_type
      )

      data, status_code, headers = @api_client.call_api(:GET, local_var_path, new_options)
      if @api_client.config.debugging
        @api_client.config.logger.debug "API called: PublicApi#public_get_trade_volumes_get\nData: #{data.inspect}\nStatus code: #{status_code}\nHeaders: #{headers}"
      end
      return data, status_code, headers
    end

    # Publicly available market data used to generate a TradingView candle chart.
    # @param instrument_name [String] Instrument name
    # @param start_timestamp [Integer] The earliest timestamp to return result for
    # @param end_timestamp [Integer] The most recent timestamp to return result for
    # @param [Hash] opts the optional parameters
    # @return [Object]
    def public_get_tradingview_chart_data_get(instrument_name, start_timestamp, end_timestamp, opts = {})
      data, _status_code, _headers = public_get_tradingview_chart_data_get_with_http_info(instrument_name, start_timestamp, end_timestamp, opts)
      data
    end

    # Publicly available market data used to generate a TradingView candle chart.
    # @param instrument_name [String] Instrument name
    # @param start_timestamp [Integer] The earliest timestamp to return result for
    # @param end_timestamp [Integer] The most recent timestamp to return result for
    # @param [Hash] opts the optional parameters
    # @return [Array<(Object, Integer, Hash)>] Object data, response status code and response headers
    def public_get_tradingview_chart_data_get_with_http_info(instrument_name, start_timestamp, end_timestamp, opts = {})
      if @api_client.config.debugging
        @api_client.config.logger.debug 'Calling API: PublicApi.public_get_tradingview_chart_data_get ...'
      end
      # verify the required parameter 'instrument_name' is set
      if @api_client.config.client_side_validation && instrument_name.nil?
        fail ArgumentError, "Missing the required parameter 'instrument_name' when calling PublicApi.public_get_tradingview_chart_data_get"
      end
      # verify the required parameter 'start_timestamp' is set
      if @api_client.config.client_side_validation && start_timestamp.nil?
        fail ArgumentError, "Missing the required parameter 'start_timestamp' when calling PublicApi.public_get_tradingview_chart_data_get"
      end
      # verify the required parameter 'end_timestamp' is set
      if @api_client.config.client_side_validation && end_timestamp.nil?
        fail ArgumentError, "Missing the required parameter 'end_timestamp' when calling PublicApi.public_get_tradingview_chart_data_get"
      end
      # resource path
      local_var_path = '/public/get_tradingview_chart_data'

      # query parameters
      query_params = opts[:query_params] || {}
      query_params[:'instrument_name'] = instrument_name
      query_params[:'start_timestamp'] = start_timestamp
      query_params[:'end_timestamp'] = end_timestamp

      # header parameters
      header_params = opts[:header_params] || {}
      # HTTP header 'Accept' (if needed)
      header_params['Accept'] = @api_client.select_header_accept(['application/json'])

      # form parameters
      form_params = opts[:form_params] || {}

      # http body (model)
      post_body = opts[:body] 

      # return_type
      return_type = opts[:return_type] || 'Object' 

      # auth_names
      auth_names = opts[:auth_names] || ['bearerAuth']

      new_options = opts.merge(
        :header_params => header_params,
        :query_params => query_params,
        :form_params => form_params,
        :body => post_body,
        :auth_names => auth_names,
        :return_type => return_type
      )

      data, status_code, headers = @api_client.call_api(:GET, local_var_path, new_options)
      if @api_client.config.debugging
        @api_client.config.logger.debug "API called: PublicApi#public_get_tradingview_chart_data_get\nData: #{data.inspect}\nStatus code: #{status_code}\nHeaders: #{headers}"
      end
      return data, status_code, headers
    end

    # Tests the connection to the API server, and returns its version. You can use this to make sure the API is reachable, and matches the expected version.
    # @param [Hash] opts the optional parameters
    # @option opts [String] :expected_result The value \&quot;exception\&quot; will trigger an error response. This may be useful for testing wrapper libraries.
    # @return [Object]
    def public_test_get(opts = {})
      data, _status_code, _headers = public_test_get_with_http_info(opts)
      data
    end

    # Tests the connection to the API server, and returns its version. You can use this to make sure the API is reachable, and matches the expected version.
    # @param [Hash] opts the optional parameters
    # @option opts [String] :expected_result The value \&quot;exception\&quot; will trigger an error response. This may be useful for testing wrapper libraries.
    # @return [Array<(Object, Integer, Hash)>] Object data, response status code and response headers
    def public_test_get_with_http_info(opts = {})
      if @api_client.config.debugging
        @api_client.config.logger.debug 'Calling API: PublicApi.public_test_get ...'
      end
      allowable_values = ["exception"]
      if @api_client.config.client_side_validation && opts[:'expected_result'] && !allowable_values.include?(opts[:'expected_result'])
        fail ArgumentError, "invalid value for \"expected_result\", must be one of #{allowable_values}"
      end
      # resource path
      local_var_path = '/public/test'

      # query parameters
      query_params = opts[:query_params] || {}
      query_params[:'expected_result'] = opts[:'expected_result'] if !opts[:'expected_result'].nil?

      # header parameters
      header_params = opts[:header_params] || {}
      # HTTP header 'Accept' (if needed)
      header_params['Accept'] = @api_client.select_header_accept(['application/json'])

      # form parameters
      form_params = opts[:form_params] || {}

      # http body (model)
      post_body = opts[:body] 

      # return_type
      return_type = opts[:return_type] || 'Object' 

      # auth_names
      auth_names = opts[:auth_names] || ['bearerAuth']

      new_options = opts.merge(
        :header_params => header_params,
        :query_params => query_params,
        :form_params => form_params,
        :body => post_body,
        :auth_names => auth_names,
        :return_type => return_type
      )

      data, status_code, headers = @api_client.call_api(:GET, local_var_path, new_options)
      if @api_client.config.debugging
        @api_client.config.logger.debug "API called: PublicApi#public_test_get\nData: #{data.inspect}\nStatus code: #{status_code}\nHeaders: #{headers}"
      end
      return data, status_code, headers
    end

    # Get ticker for an instrument.
    # @param instrument_name [String] Instrument name
    # @param [Hash] opts the optional parameters
    # @return [Object]
    def public_ticker_get(instrument_name, opts = {})
      data, _status_code, _headers = public_ticker_get_with_http_info(instrument_name, opts)
      data
    end

    # Get ticker for an instrument.
    # @param instrument_name [String] Instrument name
    # @param [Hash] opts the optional parameters
    # @return [Array<(Object, Integer, Hash)>] Object data, response status code and response headers
    def public_ticker_get_with_http_info(instrument_name, opts = {})
      if @api_client.config.debugging
        @api_client.config.logger.debug 'Calling API: PublicApi.public_ticker_get ...'
      end
      # verify the required parameter 'instrument_name' is set
      if @api_client.config.client_side_validation && instrument_name.nil?
        fail ArgumentError, "Missing the required parameter 'instrument_name' when calling PublicApi.public_ticker_get"
      end
      # resource path
      local_var_path = '/public/ticker'

      # query parameters
      query_params = opts[:query_params] || {}
      query_params[:'instrument_name'] = instrument_name

      # header parameters
      header_params = opts[:header_params] || {}
      # HTTP header 'Accept' (if needed)
      header_params['Accept'] = @api_client.select_header_accept(['application/json'])

      # form parameters
      form_params = opts[:form_params] || {}

      # http body (model)
      post_body = opts[:body] 

      # return_type
      return_type = opts[:return_type] || 'Object' 

      # auth_names
      auth_names = opts[:auth_names] || ['bearerAuth']

      new_options = opts.merge(
        :header_params => header_params,
        :query_params => query_params,
        :form_params => form_params,
        :body => post_body,
        :auth_names => auth_names,
        :return_type => return_type
      )

      data, status_code, headers = @api_client.call_api(:GET, local_var_path, new_options)
      if @api_client.config.debugging
        @api_client.config.logger.debug "API called: PublicApi#public_ticker_get\nData: #{data.inspect}\nStatus code: #{status_code}\nHeaders: #{headers}"
      end
      return data, status_code, headers
    end

    # Method used to introduce the client software connected to Deribit platform over websocket. Provided data may have an impact on the maintained connection and will be collected for internal statistical purposes. In response, Deribit will also introduce itself.
    # @param field [String] Name of the field to be validated, examples: postal_code, date_of_birth
    # @param value [String] Value to be checked
    # @param [Hash] opts the optional parameters
    # @option opts [String] :value2 Additional value to be compared with
    # @return [Object]
    def public_validate_field_get(field, value, opts = {})
      data, _status_code, _headers = public_validate_field_get_with_http_info(field, value, opts)
      data
    end

    # Method used to introduce the client software connected to Deribit platform over websocket. Provided data may have an impact on the maintained connection and will be collected for internal statistical purposes. In response, Deribit will also introduce itself.
    # @param field [String] Name of the field to be validated, examples: postal_code, date_of_birth
    # @param value [String] Value to be checked
    # @param [Hash] opts the optional parameters
    # @option opts [String] :value2 Additional value to be compared with
    # @return [Array<(Object, Integer, Hash)>] Object data, response status code and response headers
    def public_validate_field_get_with_http_info(field, value, opts = {})
      if @api_client.config.debugging
        @api_client.config.logger.debug 'Calling API: PublicApi.public_validate_field_get ...'
      end
      # verify the required parameter 'field' is set
      if @api_client.config.client_side_validation && field.nil?
        fail ArgumentError, "Missing the required parameter 'field' when calling PublicApi.public_validate_field_get"
      end
      # verify the required parameter 'value' is set
      if @api_client.config.client_side_validation && value.nil?
        fail ArgumentError, "Missing the required parameter 'value' when calling PublicApi.public_validate_field_get"
      end
      # resource path
      local_var_path = '/public/validate_field'

      # query parameters
      query_params = opts[:query_params] || {}
      query_params[:'field'] = field
      query_params[:'value'] = value
      query_params[:'value2'] = opts[:'value2'] if !opts[:'value2'].nil?

      # header parameters
      header_params = opts[:header_params] || {}
      # HTTP header 'Accept' (if needed)
      header_params['Accept'] = @api_client.select_header_accept(['application/json'])

      # form parameters
      form_params = opts[:form_params] || {}

      # http body (model)
      post_body = opts[:body] 

      # return_type
      return_type = opts[:return_type] || 'Object' 

      # auth_names
      auth_names = opts[:auth_names] || ['bearerAuth']

      new_options = opts.merge(
        :header_params => header_params,
        :query_params => query_params,
        :form_params => form_params,
        :body => post_body,
        :auth_names => auth_names,
        :return_type => return_type
      )

      data, status_code, headers = @api_client.call_api(:GET, local_var_path, new_options)
      if @api_client.config.debugging
        @api_client.config.logger.debug "API called: PublicApi#public_validate_field_get\nData: #{data.inspect}\nStatus code: #{status_code}\nHeaders: #{headers}"
      end
      return data, status_code, headers
    end
  end
end
