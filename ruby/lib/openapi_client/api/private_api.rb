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
  class PrivateApi
    attr_accessor :api_client

    def initialize(api_client = ApiClient.default)
      @api_client = api_client
    end
    # Adds new entry to address book of given type
    # @param currency [String] The currency symbol
    # @param type [String] Address book type
    # @param address [String] Address in currency format, it must be in address book
    # @param name [String] Name of address book entry
    # @param [Hash] opts the optional parameters
    # @option opts [String] :tfa TFA code, required when TFA is enabled for current account
    # @return [Object]
    def private_add_to_address_book_get(currency, type, address, name, opts = {})
      data, _status_code, _headers = private_add_to_address_book_get_with_http_info(currency, type, address, name, opts)
      data
    end

    # Adds new entry to address book of given type
    # @param currency [String] The currency symbol
    # @param type [String] Address book type
    # @param address [String] Address in currency format, it must be in address book
    # @param name [String] Name of address book entry
    # @param [Hash] opts the optional parameters
    # @option opts [String] :tfa TFA code, required when TFA is enabled for current account
    # @return [Array<(Object, Integer, Hash)>] Object data, response status code and response headers
    def private_add_to_address_book_get_with_http_info(currency, type, address, name, opts = {})
      if @api_client.config.debugging
        @api_client.config.logger.debug 'Calling API: PrivateApi.private_add_to_address_book_get ...'
      end
      # verify the required parameter 'currency' is set
      if @api_client.config.client_side_validation && currency.nil?
        fail ArgumentError, "Missing the required parameter 'currency' when calling PrivateApi.private_add_to_address_book_get"
      end
      # verify enum value
      allowable_values = ["BTC", "ETH"]
      if @api_client.config.client_side_validation && !allowable_values.include?(currency)
        fail ArgumentError, "invalid value for \"currency\", must be one of #{allowable_values}"
      end
      # verify the required parameter 'type' is set
      if @api_client.config.client_side_validation && type.nil?
        fail ArgumentError, "Missing the required parameter 'type' when calling PrivateApi.private_add_to_address_book_get"
      end
      # verify enum value
      allowable_values = ["transfer", "withdrawal"]
      if @api_client.config.client_side_validation && !allowable_values.include?(type)
        fail ArgumentError, "invalid value for \"type\", must be one of #{allowable_values}"
      end
      # verify the required parameter 'address' is set
      if @api_client.config.client_side_validation && address.nil?
        fail ArgumentError, "Missing the required parameter 'address' when calling PrivateApi.private_add_to_address_book_get"
      end
      # verify the required parameter 'name' is set
      if @api_client.config.client_side_validation && name.nil?
        fail ArgumentError, "Missing the required parameter 'name' when calling PrivateApi.private_add_to_address_book_get"
      end
      # resource path
      local_var_path = '/private/add_to_address_book'

      # query parameters
      query_params = opts[:query_params] || {}
      query_params[:'currency'] = currency
      query_params[:'type'] = type
      query_params[:'address'] = address
      query_params[:'name'] = name
      query_params[:'tfa'] = opts[:'tfa'] if !opts[:'tfa'].nil?

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
        @api_client.config.logger.debug "API called: PrivateApi#private_add_to_address_book_get\nData: #{data.inspect}\nStatus code: #{status_code}\nHeaders: #{headers}"
      end
      return data, status_code, headers
    end

    # Places a buy order for an instrument.
    # @param instrument_name [String] Instrument name
    # @param amount [Float] It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH
    # @param [Hash] opts the optional parameters
    # @option opts [String] :type The order type, default: &#x60;\&quot;limit\&quot;&#x60;
    # @option opts [String] :label user defined label for the order (maximum 32 characters)
    # @option opts [Float] :price &lt;p&gt;The order price in base currency (Only for limit and stop_limit orders)&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;usd, the field price should be the option price value in USD.&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;implv, the field price should be a value of implied volatility in percentages. For example,  price&#x3D;100, means implied volatility of 100%&lt;/p&gt;
    # @option opts [String] :time_in_force &lt;p&gt;Specifies how long the order remains in effect. Default &#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; &lt;ul&gt; &lt;li&gt;&#x60;\&quot;good_til_cancelled\&quot;&#x60; - unfilled order remains in order book until cancelled&lt;/li&gt; &lt;li&gt;&#x60;\&quot;fill_or_kill\&quot;&#x60; - execute a transaction immediately and completely or not at all&lt;/li&gt; &lt;li&gt;&#x60;\&quot;immediate_or_cancel\&quot;&#x60; - execute a transaction immediately, and any portion of the order that cannot be immediately filled is cancelled&lt;/li&gt; &lt;/ul&gt; (default to 'good_til_cancelled')
    # @option opts [Float] :max_show Maximum amount within an order to be shown to other customers, &#x60;0&#x60; for invisible order (default to 1)
    # @option opts [Boolean] :post_only &lt;p&gt;If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.&lt;/p&gt; &lt;p&gt;Only valid in combination with time_in_force&#x3D;&#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; (default to true)
    # @option opts [Boolean] :reduce_only If &#x60;true&#x60;, the order is considered reduce-only which is intended to only reduce a current position (default to false)
    # @option opts [Float] :stop_price Stop price, required for stop limit orders (Only for stop orders)
    # @option opts [String] :trigger Defines trigger type, required for &#x60;\&quot;stop_limit\&quot;&#x60; order type
    # @option opts [String] :advanced Advanced option order type. (Only for options)
    # @return [Object]
    def private_buy_get(instrument_name, amount, opts = {})
      data, _status_code, _headers = private_buy_get_with_http_info(instrument_name, amount, opts)
      data
    end

    # Places a buy order for an instrument.
    # @param instrument_name [String] Instrument name
    # @param amount [Float] It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH
    # @param [Hash] opts the optional parameters
    # @option opts [String] :type The order type, default: &#x60;\&quot;limit\&quot;&#x60;
    # @option opts [String] :label user defined label for the order (maximum 32 characters)
    # @option opts [Float] :price &lt;p&gt;The order price in base currency (Only for limit and stop_limit orders)&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;usd, the field price should be the option price value in USD.&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;implv, the field price should be a value of implied volatility in percentages. For example,  price&#x3D;100, means implied volatility of 100%&lt;/p&gt;
    # @option opts [String] :time_in_force &lt;p&gt;Specifies how long the order remains in effect. Default &#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; &lt;ul&gt; &lt;li&gt;&#x60;\&quot;good_til_cancelled\&quot;&#x60; - unfilled order remains in order book until cancelled&lt;/li&gt; &lt;li&gt;&#x60;\&quot;fill_or_kill\&quot;&#x60; - execute a transaction immediately and completely or not at all&lt;/li&gt; &lt;li&gt;&#x60;\&quot;immediate_or_cancel\&quot;&#x60; - execute a transaction immediately, and any portion of the order that cannot be immediately filled is cancelled&lt;/li&gt; &lt;/ul&gt;
    # @option opts [Float] :max_show Maximum amount within an order to be shown to other customers, &#x60;0&#x60; for invisible order
    # @option opts [Boolean] :post_only &lt;p&gt;If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.&lt;/p&gt; &lt;p&gt;Only valid in combination with time_in_force&#x3D;&#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt;
    # @option opts [Boolean] :reduce_only If &#x60;true&#x60;, the order is considered reduce-only which is intended to only reduce a current position
    # @option opts [Float] :stop_price Stop price, required for stop limit orders (Only for stop orders)
    # @option opts [String] :trigger Defines trigger type, required for &#x60;\&quot;stop_limit\&quot;&#x60; order type
    # @option opts [String] :advanced Advanced option order type. (Only for options)
    # @return [Array<(Object, Integer, Hash)>] Object data, response status code and response headers
    def private_buy_get_with_http_info(instrument_name, amount, opts = {})
      if @api_client.config.debugging
        @api_client.config.logger.debug 'Calling API: PrivateApi.private_buy_get ...'
      end
      # verify the required parameter 'instrument_name' is set
      if @api_client.config.client_side_validation && instrument_name.nil?
        fail ArgumentError, "Missing the required parameter 'instrument_name' when calling PrivateApi.private_buy_get"
      end
      # verify the required parameter 'amount' is set
      if @api_client.config.client_side_validation && amount.nil?
        fail ArgumentError, "Missing the required parameter 'amount' when calling PrivateApi.private_buy_get"
      end
      allowable_values = ["limit", "stop_limit", "market", "stop_market"]
      if @api_client.config.client_side_validation && opts[:'type'] && !allowable_values.include?(opts[:'type'])
        fail ArgumentError, "invalid value for \"type\", must be one of #{allowable_values}"
      end
      allowable_values = ["good_til_cancelled", "fill_or_kill", "immediate_or_cancel"]
      if @api_client.config.client_side_validation && opts[:'time_in_force'] && !allowable_values.include?(opts[:'time_in_force'])
        fail ArgumentError, "invalid value for \"time_in_force\", must be one of #{allowable_values}"
      end
      allowable_values = ["index_price", "mark_price", "last_price"]
      if @api_client.config.client_side_validation && opts[:'trigger'] && !allowable_values.include?(opts[:'trigger'])
        fail ArgumentError, "invalid value for \"trigger\", must be one of #{allowable_values}"
      end
      allowable_values = ["usd", "implv"]
      if @api_client.config.client_side_validation && opts[:'advanced'] && !allowable_values.include?(opts[:'advanced'])
        fail ArgumentError, "invalid value for \"advanced\", must be one of #{allowable_values}"
      end
      # resource path
      local_var_path = '/private/buy'

      # query parameters
      query_params = opts[:query_params] || {}
      query_params[:'instrument_name'] = instrument_name
      query_params[:'amount'] = amount
      query_params[:'type'] = opts[:'type'] if !opts[:'type'].nil?
      query_params[:'label'] = opts[:'label'] if !opts[:'label'].nil?
      query_params[:'price'] = opts[:'price'] if !opts[:'price'].nil?
      query_params[:'time_in_force'] = opts[:'time_in_force'] if !opts[:'time_in_force'].nil?
      query_params[:'max_show'] = opts[:'max_show'] if !opts[:'max_show'].nil?
      query_params[:'post_only'] = opts[:'post_only'] if !opts[:'post_only'].nil?
      query_params[:'reduce_only'] = opts[:'reduce_only'] if !opts[:'reduce_only'].nil?
      query_params[:'stop_price'] = opts[:'stop_price'] if !opts[:'stop_price'].nil?
      query_params[:'trigger'] = opts[:'trigger'] if !opts[:'trigger'].nil?
      query_params[:'advanced'] = opts[:'advanced'] if !opts[:'advanced'].nil?

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
        @api_client.config.logger.debug "API called: PrivateApi#private_buy_get\nData: #{data.inspect}\nStatus code: #{status_code}\nHeaders: #{headers}"
      end
      return data, status_code, headers
    end

    # Cancels all orders by currency, optionally filtered by instrument kind and/or order type.
    # @param currency [String] The currency symbol
    # @param [Hash] opts the optional parameters
    # @option opts [String] :kind Instrument kind, if not provided instruments of all kinds are considered
    # @option opts [String] :type Order type - limit, stop or all, default - &#x60;all&#x60;
    # @return [Object]
    def private_cancel_all_by_currency_get(currency, opts = {})
      data, _status_code, _headers = private_cancel_all_by_currency_get_with_http_info(currency, opts)
      data
    end

    # Cancels all orders by currency, optionally filtered by instrument kind and/or order type.
    # @param currency [String] The currency symbol
    # @param [Hash] opts the optional parameters
    # @option opts [String] :kind Instrument kind, if not provided instruments of all kinds are considered
    # @option opts [String] :type Order type - limit, stop or all, default - &#x60;all&#x60;
    # @return [Array<(Object, Integer, Hash)>] Object data, response status code and response headers
    def private_cancel_all_by_currency_get_with_http_info(currency, opts = {})
      if @api_client.config.debugging
        @api_client.config.logger.debug 'Calling API: PrivateApi.private_cancel_all_by_currency_get ...'
      end
      # verify the required parameter 'currency' is set
      if @api_client.config.client_side_validation && currency.nil?
        fail ArgumentError, "Missing the required parameter 'currency' when calling PrivateApi.private_cancel_all_by_currency_get"
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
      allowable_values = ["all", "limit", "stop"]
      if @api_client.config.client_side_validation && opts[:'type'] && !allowable_values.include?(opts[:'type'])
        fail ArgumentError, "invalid value for \"type\", must be one of #{allowable_values}"
      end
      # resource path
      local_var_path = '/private/cancel_all_by_currency'

      # query parameters
      query_params = opts[:query_params] || {}
      query_params[:'currency'] = currency
      query_params[:'kind'] = opts[:'kind'] if !opts[:'kind'].nil?
      query_params[:'type'] = opts[:'type'] if !opts[:'type'].nil?

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
        @api_client.config.logger.debug "API called: PrivateApi#private_cancel_all_by_currency_get\nData: #{data.inspect}\nStatus code: #{status_code}\nHeaders: #{headers}"
      end
      return data, status_code, headers
    end

    # Cancels all orders by instrument, optionally filtered by order type.
    # @param instrument_name [String] Instrument name
    # @param [Hash] opts the optional parameters
    # @option opts [String] :type Order type - limit, stop or all, default - &#x60;all&#x60;
    # @return [Object]
    def private_cancel_all_by_instrument_get(instrument_name, opts = {})
      data, _status_code, _headers = private_cancel_all_by_instrument_get_with_http_info(instrument_name, opts)
      data
    end

    # Cancels all orders by instrument, optionally filtered by order type.
    # @param instrument_name [String] Instrument name
    # @param [Hash] opts the optional parameters
    # @option opts [String] :type Order type - limit, stop or all, default - &#x60;all&#x60;
    # @return [Array<(Object, Integer, Hash)>] Object data, response status code and response headers
    def private_cancel_all_by_instrument_get_with_http_info(instrument_name, opts = {})
      if @api_client.config.debugging
        @api_client.config.logger.debug 'Calling API: PrivateApi.private_cancel_all_by_instrument_get ...'
      end
      # verify the required parameter 'instrument_name' is set
      if @api_client.config.client_side_validation && instrument_name.nil?
        fail ArgumentError, "Missing the required parameter 'instrument_name' when calling PrivateApi.private_cancel_all_by_instrument_get"
      end
      allowable_values = ["all", "limit", "stop"]
      if @api_client.config.client_side_validation && opts[:'type'] && !allowable_values.include?(opts[:'type'])
        fail ArgumentError, "invalid value for \"type\", must be one of #{allowable_values}"
      end
      # resource path
      local_var_path = '/private/cancel_all_by_instrument'

      # query parameters
      query_params = opts[:query_params] || {}
      query_params[:'instrument_name'] = instrument_name
      query_params[:'type'] = opts[:'type'] if !opts[:'type'].nil?

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
        @api_client.config.logger.debug "API called: PrivateApi#private_cancel_all_by_instrument_get\nData: #{data.inspect}\nStatus code: #{status_code}\nHeaders: #{headers}"
      end
      return data, status_code, headers
    end

    # This method cancels all users orders and stop orders within all currencies and instrument kinds.
    # @param [Hash] opts the optional parameters
    # @return [Object]
    def private_cancel_all_get(opts = {})
      data, _status_code, _headers = private_cancel_all_get_with_http_info(opts)
      data
    end

    # This method cancels all users orders and stop orders within all currencies and instrument kinds.
    # @param [Hash] opts the optional parameters
    # @return [Array<(Object, Integer, Hash)>] Object data, response status code and response headers
    def private_cancel_all_get_with_http_info(opts = {})
      if @api_client.config.debugging
        @api_client.config.logger.debug 'Calling API: PrivateApi.private_cancel_all_get ...'
      end
      # resource path
      local_var_path = '/private/cancel_all'

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
        @api_client.config.logger.debug "API called: PrivateApi#private_cancel_all_get\nData: #{data.inspect}\nStatus code: #{status_code}\nHeaders: #{headers}"
      end
      return data, status_code, headers
    end

    # Cancel an order, specified by order id
    # @param order_id [String] The order id
    # @param [Hash] opts the optional parameters
    # @return [Object]
    def private_cancel_get(order_id, opts = {})
      data, _status_code, _headers = private_cancel_get_with_http_info(order_id, opts)
      data
    end

    # Cancel an order, specified by order id
    # @param order_id [String] The order id
    # @param [Hash] opts the optional parameters
    # @return [Array<(Object, Integer, Hash)>] Object data, response status code and response headers
    def private_cancel_get_with_http_info(order_id, opts = {})
      if @api_client.config.debugging
        @api_client.config.logger.debug 'Calling API: PrivateApi.private_cancel_get ...'
      end
      # verify the required parameter 'order_id' is set
      if @api_client.config.client_side_validation && order_id.nil?
        fail ArgumentError, "Missing the required parameter 'order_id' when calling PrivateApi.private_cancel_get"
      end
      # resource path
      local_var_path = '/private/cancel'

      # query parameters
      query_params = opts[:query_params] || {}
      query_params[:'order_id'] = order_id

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
        @api_client.config.logger.debug "API called: PrivateApi#private_cancel_get\nData: #{data.inspect}\nStatus code: #{status_code}\nHeaders: #{headers}"
      end
      return data, status_code, headers
    end

    # Cancel transfer
    # @param currency [String] The currency symbol
    # @param id [Integer] Id of transfer
    # @param [Hash] opts the optional parameters
    # @option opts [String] :tfa TFA code, required when TFA is enabled for current account
    # @return [Object]
    def private_cancel_transfer_by_id_get(currency, id, opts = {})
      data, _status_code, _headers = private_cancel_transfer_by_id_get_with_http_info(currency, id, opts)
      data
    end

    # Cancel transfer
    # @param currency [String] The currency symbol
    # @param id [Integer] Id of transfer
    # @param [Hash] opts the optional parameters
    # @option opts [String] :tfa TFA code, required when TFA is enabled for current account
    # @return [Array<(Object, Integer, Hash)>] Object data, response status code and response headers
    def private_cancel_transfer_by_id_get_with_http_info(currency, id, opts = {})
      if @api_client.config.debugging
        @api_client.config.logger.debug 'Calling API: PrivateApi.private_cancel_transfer_by_id_get ...'
      end
      # verify the required parameter 'currency' is set
      if @api_client.config.client_side_validation && currency.nil?
        fail ArgumentError, "Missing the required parameter 'currency' when calling PrivateApi.private_cancel_transfer_by_id_get"
      end
      # verify enum value
      allowable_values = ["BTC", "ETH"]
      if @api_client.config.client_side_validation && !allowable_values.include?(currency)
        fail ArgumentError, "invalid value for \"currency\", must be one of #{allowable_values}"
      end
      # verify the required parameter 'id' is set
      if @api_client.config.client_side_validation && id.nil?
        fail ArgumentError, "Missing the required parameter 'id' when calling PrivateApi.private_cancel_transfer_by_id_get"
      end
      # resource path
      local_var_path = '/private/cancel_transfer_by_id'

      # query parameters
      query_params = opts[:query_params] || {}
      query_params[:'currency'] = currency
      query_params[:'id'] = id
      query_params[:'tfa'] = opts[:'tfa'] if !opts[:'tfa'].nil?

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
        @api_client.config.logger.debug "API called: PrivateApi#private_cancel_transfer_by_id_get\nData: #{data.inspect}\nStatus code: #{status_code}\nHeaders: #{headers}"
      end
      return data, status_code, headers
    end

    # Cancels withdrawal request
    # @param currency [String] The currency symbol
    # @param id [Float] The withdrawal id
    # @param [Hash] opts the optional parameters
    # @return [Object]
    def private_cancel_withdrawal_get(currency, id, opts = {})
      data, _status_code, _headers = private_cancel_withdrawal_get_with_http_info(currency, id, opts)
      data
    end

    # Cancels withdrawal request
    # @param currency [String] The currency symbol
    # @param id [Float] The withdrawal id
    # @param [Hash] opts the optional parameters
    # @return [Array<(Object, Integer, Hash)>] Object data, response status code and response headers
    def private_cancel_withdrawal_get_with_http_info(currency, id, opts = {})
      if @api_client.config.debugging
        @api_client.config.logger.debug 'Calling API: PrivateApi.private_cancel_withdrawal_get ...'
      end
      # verify the required parameter 'currency' is set
      if @api_client.config.client_side_validation && currency.nil?
        fail ArgumentError, "Missing the required parameter 'currency' when calling PrivateApi.private_cancel_withdrawal_get"
      end
      # verify enum value
      allowable_values = ["BTC", "ETH"]
      if @api_client.config.client_side_validation && !allowable_values.include?(currency)
        fail ArgumentError, "invalid value for \"currency\", must be one of #{allowable_values}"
      end
      # verify the required parameter 'id' is set
      if @api_client.config.client_side_validation && id.nil?
        fail ArgumentError, "Missing the required parameter 'id' when calling PrivateApi.private_cancel_withdrawal_get"
      end
      # resource path
      local_var_path = '/private/cancel_withdrawal'

      # query parameters
      query_params = opts[:query_params] || {}
      query_params[:'currency'] = currency
      query_params[:'id'] = id

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
        @api_client.config.logger.debug "API called: PrivateApi#private_cancel_withdrawal_get\nData: #{data.inspect}\nStatus code: #{status_code}\nHeaders: #{headers}"
      end
      return data, status_code, headers
    end

    # Change the user name for a subaccount
    # @param sid [Integer] The user id for the subaccount
    # @param name [String] The new user name
    # @param [Hash] opts the optional parameters
    # @return [Object]
    def private_change_subaccount_name_get(sid, name, opts = {})
      data, _status_code, _headers = private_change_subaccount_name_get_with_http_info(sid, name, opts)
      data
    end

    # Change the user name for a subaccount
    # @param sid [Integer] The user id for the subaccount
    # @param name [String] The new user name
    # @param [Hash] opts the optional parameters
    # @return [Array<(Object, Integer, Hash)>] Object data, response status code and response headers
    def private_change_subaccount_name_get_with_http_info(sid, name, opts = {})
      if @api_client.config.debugging
        @api_client.config.logger.debug 'Calling API: PrivateApi.private_change_subaccount_name_get ...'
      end
      # verify the required parameter 'sid' is set
      if @api_client.config.client_side_validation && sid.nil?
        fail ArgumentError, "Missing the required parameter 'sid' when calling PrivateApi.private_change_subaccount_name_get"
      end
      # verify the required parameter 'name' is set
      if @api_client.config.client_side_validation && name.nil?
        fail ArgumentError, "Missing the required parameter 'name' when calling PrivateApi.private_change_subaccount_name_get"
      end
      # resource path
      local_var_path = '/private/change_subaccount_name'

      # query parameters
      query_params = opts[:query_params] || {}
      query_params[:'sid'] = sid
      query_params[:'name'] = name

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
        @api_client.config.logger.debug "API called: PrivateApi#private_change_subaccount_name_get\nData: #{data.inspect}\nStatus code: #{status_code}\nHeaders: #{headers}"
      end
      return data, status_code, headers
    end

    # Makes closing position reduce only order .
    # @param instrument_name [String] Instrument name
    # @param type [String] The order type
    # @param [Hash] opts the optional parameters
    # @option opts [Float] :price Optional price for limit order.
    # @return [Object]
    def private_close_position_get(instrument_name, type, opts = {})
      data, _status_code, _headers = private_close_position_get_with_http_info(instrument_name, type, opts)
      data
    end

    # Makes closing position reduce only order .
    # @param instrument_name [String] Instrument name
    # @param type [String] The order type
    # @param [Hash] opts the optional parameters
    # @option opts [Float] :price Optional price for limit order.
    # @return [Array<(Object, Integer, Hash)>] Object data, response status code and response headers
    def private_close_position_get_with_http_info(instrument_name, type, opts = {})
      if @api_client.config.debugging
        @api_client.config.logger.debug 'Calling API: PrivateApi.private_close_position_get ...'
      end
      # verify the required parameter 'instrument_name' is set
      if @api_client.config.client_side_validation && instrument_name.nil?
        fail ArgumentError, "Missing the required parameter 'instrument_name' when calling PrivateApi.private_close_position_get"
      end
      # verify the required parameter 'type' is set
      if @api_client.config.client_side_validation && type.nil?
        fail ArgumentError, "Missing the required parameter 'type' when calling PrivateApi.private_close_position_get"
      end
      # verify enum value
      allowable_values = ["limit", "market"]
      if @api_client.config.client_side_validation && !allowable_values.include?(type)
        fail ArgumentError, "invalid value for \"type\", must be one of #{allowable_values}"
      end
      # resource path
      local_var_path = '/private/close_position'

      # query parameters
      query_params = opts[:query_params] || {}
      query_params[:'instrument_name'] = instrument_name
      query_params[:'type'] = type
      query_params[:'price'] = opts[:'price'] if !opts[:'price'].nil?

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
        @api_client.config.logger.debug "API called: PrivateApi#private_close_position_get\nData: #{data.inspect}\nStatus code: #{status_code}\nHeaders: #{headers}"
      end
      return data, status_code, headers
    end

    # Creates deposit address in currency
    # @param currency [String] The currency symbol
    # @param [Hash] opts the optional parameters
    # @return [Object]
    def private_create_deposit_address_get(currency, opts = {})
      data, _status_code, _headers = private_create_deposit_address_get_with_http_info(currency, opts)
      data
    end

    # Creates deposit address in currency
    # @param currency [String] The currency symbol
    # @param [Hash] opts the optional parameters
    # @return [Array<(Object, Integer, Hash)>] Object data, response status code and response headers
    def private_create_deposit_address_get_with_http_info(currency, opts = {})
      if @api_client.config.debugging
        @api_client.config.logger.debug 'Calling API: PrivateApi.private_create_deposit_address_get ...'
      end
      # verify the required parameter 'currency' is set
      if @api_client.config.client_side_validation && currency.nil?
        fail ArgumentError, "Missing the required parameter 'currency' when calling PrivateApi.private_create_deposit_address_get"
      end
      # verify enum value
      allowable_values = ["BTC", "ETH"]
      if @api_client.config.client_side_validation && !allowable_values.include?(currency)
        fail ArgumentError, "invalid value for \"currency\", must be one of #{allowable_values}"
      end
      # resource path
      local_var_path = '/private/create_deposit_address'

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
        @api_client.config.logger.debug "API called: PrivateApi#private_create_deposit_address_get\nData: #{data.inspect}\nStatus code: #{status_code}\nHeaders: #{headers}"
      end
      return data, status_code, headers
    end

    # Create a new subaccount
    # @param [Hash] opts the optional parameters
    # @return [Object]
    def private_create_subaccount_get(opts = {})
      data, _status_code, _headers = private_create_subaccount_get_with_http_info(opts)
      data
    end

    # Create a new subaccount
    # @param [Hash] opts the optional parameters
    # @return [Array<(Object, Integer, Hash)>] Object data, response status code and response headers
    def private_create_subaccount_get_with_http_info(opts = {})
      if @api_client.config.debugging
        @api_client.config.logger.debug 'Calling API: PrivateApi.private_create_subaccount_get ...'
      end
      # resource path
      local_var_path = '/private/create_subaccount'

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
        @api_client.config.logger.debug "API called: PrivateApi#private_create_subaccount_get\nData: #{data.inspect}\nStatus code: #{status_code}\nHeaders: #{headers}"
      end
      return data, status_code, headers
    end

    # Disable two factor authentication for a subaccount.
    # @param sid [Integer] The user id for the subaccount
    # @param [Hash] opts the optional parameters
    # @return [Object]
    def private_disable_tfa_for_subaccount_get(sid, opts = {})
      data, _status_code, _headers = private_disable_tfa_for_subaccount_get_with_http_info(sid, opts)
      data
    end

    # Disable two factor authentication for a subaccount.
    # @param sid [Integer] The user id for the subaccount
    # @param [Hash] opts the optional parameters
    # @return [Array<(Object, Integer, Hash)>] Object data, response status code and response headers
    def private_disable_tfa_for_subaccount_get_with_http_info(sid, opts = {})
      if @api_client.config.debugging
        @api_client.config.logger.debug 'Calling API: PrivateApi.private_disable_tfa_for_subaccount_get ...'
      end
      # verify the required parameter 'sid' is set
      if @api_client.config.client_side_validation && sid.nil?
        fail ArgumentError, "Missing the required parameter 'sid' when calling PrivateApi.private_disable_tfa_for_subaccount_get"
      end
      # resource path
      local_var_path = '/private/disable_tfa_for_subaccount'

      # query parameters
      query_params = opts[:query_params] || {}
      query_params[:'sid'] = sid

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
        @api_client.config.logger.debug "API called: PrivateApi#private_disable_tfa_for_subaccount_get\nData: #{data.inspect}\nStatus code: #{status_code}\nHeaders: #{headers}"
      end
      return data, status_code, headers
    end

    # Disables TFA with one time recovery code
    # @param password [String] The password for the subaccount
    # @param code [String] One time recovery code
    # @param [Hash] opts the optional parameters
    # @return [Object]
    def private_disable_tfa_with_recovery_code_get(password, code, opts = {})
      data, _status_code, _headers = private_disable_tfa_with_recovery_code_get_with_http_info(password, code, opts)
      data
    end

    # Disables TFA with one time recovery code
    # @param password [String] The password for the subaccount
    # @param code [String] One time recovery code
    # @param [Hash] opts the optional parameters
    # @return [Array<(Object, Integer, Hash)>] Object data, response status code and response headers
    def private_disable_tfa_with_recovery_code_get_with_http_info(password, code, opts = {})
      if @api_client.config.debugging
        @api_client.config.logger.debug 'Calling API: PrivateApi.private_disable_tfa_with_recovery_code_get ...'
      end
      # verify the required parameter 'password' is set
      if @api_client.config.client_side_validation && password.nil?
        fail ArgumentError, "Missing the required parameter 'password' when calling PrivateApi.private_disable_tfa_with_recovery_code_get"
      end
      # verify the required parameter 'code' is set
      if @api_client.config.client_side_validation && code.nil?
        fail ArgumentError, "Missing the required parameter 'code' when calling PrivateApi.private_disable_tfa_with_recovery_code_get"
      end
      # resource path
      local_var_path = '/private/disable_tfa_with_recovery_code'

      # query parameters
      query_params = opts[:query_params] || {}
      query_params[:'password'] = password
      query_params[:'code'] = code

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
        @api_client.config.logger.debug "API called: PrivateApi#private_disable_tfa_with_recovery_code_get\nData: #{data.inspect}\nStatus code: #{status_code}\nHeaders: #{headers}"
      end
      return data, status_code, headers
    end

    # Change price, amount and/or other properties of an order.
    # @param order_id [String] The order id
    # @param amount [Float] It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH
    # @param price [Float] &lt;p&gt;The order price in base currency.&lt;/p&gt; &lt;p&gt;When editing an option order with advanced&#x3D;usd, the field price should be the option price value in USD.&lt;/p&gt; &lt;p&gt;When editing an option order with advanced&#x3D;implv, the field price should be a value of implied volatility in percentages. For example,  price&#x3D;100, means implied volatility of 100%&lt;/p&gt;
    # @param [Hash] opts the optional parameters
    # @option opts [Boolean] :post_only &lt;p&gt;If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.&lt;/p&gt; &lt;p&gt;Only valid in combination with time_in_force&#x3D;&#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; (default to true)
    # @option opts [String] :advanced Advanced option order type. If you have posted an advanced option order, it is necessary to re-supply this parameter when editing it (Only for options)
    # @option opts [Float] :stop_price Stop price, required for stop limit orders (Only for stop orders)
    # @return [Object]
    def private_edit_get(order_id, amount, price, opts = {})
      data, _status_code, _headers = private_edit_get_with_http_info(order_id, amount, price, opts)
      data
    end

    # Change price, amount and/or other properties of an order.
    # @param order_id [String] The order id
    # @param amount [Float] It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH
    # @param price [Float] &lt;p&gt;The order price in base currency.&lt;/p&gt; &lt;p&gt;When editing an option order with advanced&#x3D;usd, the field price should be the option price value in USD.&lt;/p&gt; &lt;p&gt;When editing an option order with advanced&#x3D;implv, the field price should be a value of implied volatility in percentages. For example,  price&#x3D;100, means implied volatility of 100%&lt;/p&gt;
    # @param [Hash] opts the optional parameters
    # @option opts [Boolean] :post_only &lt;p&gt;If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.&lt;/p&gt; &lt;p&gt;Only valid in combination with time_in_force&#x3D;&#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt;
    # @option opts [String] :advanced Advanced option order type. If you have posted an advanced option order, it is necessary to re-supply this parameter when editing it (Only for options)
    # @option opts [Float] :stop_price Stop price, required for stop limit orders (Only for stop orders)
    # @return [Array<(Object, Integer, Hash)>] Object data, response status code and response headers
    def private_edit_get_with_http_info(order_id, amount, price, opts = {})
      if @api_client.config.debugging
        @api_client.config.logger.debug 'Calling API: PrivateApi.private_edit_get ...'
      end
      # verify the required parameter 'order_id' is set
      if @api_client.config.client_side_validation && order_id.nil?
        fail ArgumentError, "Missing the required parameter 'order_id' when calling PrivateApi.private_edit_get"
      end
      # verify the required parameter 'amount' is set
      if @api_client.config.client_side_validation && amount.nil?
        fail ArgumentError, "Missing the required parameter 'amount' when calling PrivateApi.private_edit_get"
      end
      # verify the required parameter 'price' is set
      if @api_client.config.client_side_validation && price.nil?
        fail ArgumentError, "Missing the required parameter 'price' when calling PrivateApi.private_edit_get"
      end
      allowable_values = ["usd", "implv"]
      if @api_client.config.client_side_validation && opts[:'advanced'] && !allowable_values.include?(opts[:'advanced'])
        fail ArgumentError, "invalid value for \"advanced\", must be one of #{allowable_values}"
      end
      # resource path
      local_var_path = '/private/edit'

      # query parameters
      query_params = opts[:query_params] || {}
      query_params[:'order_id'] = order_id
      query_params[:'amount'] = amount
      query_params[:'price'] = price
      query_params[:'post_only'] = opts[:'post_only'] if !opts[:'post_only'].nil?
      query_params[:'advanced'] = opts[:'advanced'] if !opts[:'advanced'].nil?
      query_params[:'stop_price'] = opts[:'stop_price'] if !opts[:'stop_price'].nil?

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
        @api_client.config.logger.debug "API called: PrivateApi#private_edit_get\nData: #{data.inspect}\nStatus code: #{status_code}\nHeaders: #{headers}"
      end
      return data, status_code, headers
    end

    # Retrieves user account summary.
    # @param currency [String] The currency symbol
    # @param [Hash] opts the optional parameters
    # @option opts [Boolean] :extended Include additional fields
    # @return [Object]
    def private_get_account_summary_get(currency, opts = {})
      data, _status_code, _headers = private_get_account_summary_get_with_http_info(currency, opts)
      data
    end

    # Retrieves user account summary.
    # @param currency [String] The currency symbol
    # @param [Hash] opts the optional parameters
    # @option opts [Boolean] :extended Include additional fields
    # @return [Array<(Object, Integer, Hash)>] Object data, response status code and response headers
    def private_get_account_summary_get_with_http_info(currency, opts = {})
      if @api_client.config.debugging
        @api_client.config.logger.debug 'Calling API: PrivateApi.private_get_account_summary_get ...'
      end
      # verify the required parameter 'currency' is set
      if @api_client.config.client_side_validation && currency.nil?
        fail ArgumentError, "Missing the required parameter 'currency' when calling PrivateApi.private_get_account_summary_get"
      end
      # verify enum value
      allowable_values = ["BTC", "ETH"]
      if @api_client.config.client_side_validation && !allowable_values.include?(currency)
        fail ArgumentError, "invalid value for \"currency\", must be one of #{allowable_values}"
      end
      # resource path
      local_var_path = '/private/get_account_summary'

      # query parameters
      query_params = opts[:query_params] || {}
      query_params[:'currency'] = currency
      query_params[:'extended'] = opts[:'extended'] if !opts[:'extended'].nil?

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
        @api_client.config.logger.debug "API called: PrivateApi#private_get_account_summary_get\nData: #{data.inspect}\nStatus code: #{status_code}\nHeaders: #{headers}"
      end
      return data, status_code, headers
    end

    # Retrieves address book of given type
    # @param currency [String] The currency symbol
    # @param type [String] Address book type
    # @param [Hash] opts the optional parameters
    # @return [Object]
    def private_get_address_book_get(currency, type, opts = {})
      data, _status_code, _headers = private_get_address_book_get_with_http_info(currency, type, opts)
      data
    end

    # Retrieves address book of given type
    # @param currency [String] The currency symbol
    # @param type [String] Address book type
    # @param [Hash] opts the optional parameters
    # @return [Array<(Object, Integer, Hash)>] Object data, response status code and response headers
    def private_get_address_book_get_with_http_info(currency, type, opts = {})
      if @api_client.config.debugging
        @api_client.config.logger.debug 'Calling API: PrivateApi.private_get_address_book_get ...'
      end
      # verify the required parameter 'currency' is set
      if @api_client.config.client_side_validation && currency.nil?
        fail ArgumentError, "Missing the required parameter 'currency' when calling PrivateApi.private_get_address_book_get"
      end
      # verify enum value
      allowable_values = ["BTC", "ETH"]
      if @api_client.config.client_side_validation && !allowable_values.include?(currency)
        fail ArgumentError, "invalid value for \"currency\", must be one of #{allowable_values}"
      end
      # verify the required parameter 'type' is set
      if @api_client.config.client_side_validation && type.nil?
        fail ArgumentError, "Missing the required parameter 'type' when calling PrivateApi.private_get_address_book_get"
      end
      # verify enum value
      allowable_values = ["transfer", "withdrawal"]
      if @api_client.config.client_side_validation && !allowable_values.include?(type)
        fail ArgumentError, "invalid value for \"type\", must be one of #{allowable_values}"
      end
      # resource path
      local_var_path = '/private/get_address_book'

      # query parameters
      query_params = opts[:query_params] || {}
      query_params[:'currency'] = currency
      query_params[:'type'] = type

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
        @api_client.config.logger.debug "API called: PrivateApi#private_get_address_book_get\nData: #{data.inspect}\nStatus code: #{status_code}\nHeaders: #{headers}"
      end
      return data, status_code, headers
    end

    # Retrieve deposit address for currency
    # @param currency [String] The currency symbol
    # @param [Hash] opts the optional parameters
    # @return [Object]
    def private_get_current_deposit_address_get(currency, opts = {})
      data, _status_code, _headers = private_get_current_deposit_address_get_with_http_info(currency, opts)
      data
    end

    # Retrieve deposit address for currency
    # @param currency [String] The currency symbol
    # @param [Hash] opts the optional parameters
    # @return [Array<(Object, Integer, Hash)>] Object data, response status code and response headers
    def private_get_current_deposit_address_get_with_http_info(currency, opts = {})
      if @api_client.config.debugging
        @api_client.config.logger.debug 'Calling API: PrivateApi.private_get_current_deposit_address_get ...'
      end
      # verify the required parameter 'currency' is set
      if @api_client.config.client_side_validation && currency.nil?
        fail ArgumentError, "Missing the required parameter 'currency' when calling PrivateApi.private_get_current_deposit_address_get"
      end
      # verify enum value
      allowable_values = ["BTC", "ETH"]
      if @api_client.config.client_side_validation && !allowable_values.include?(currency)
        fail ArgumentError, "invalid value for \"currency\", must be one of #{allowable_values}"
      end
      # resource path
      local_var_path = '/private/get_current_deposit_address'

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
        @api_client.config.logger.debug "API called: PrivateApi#private_get_current_deposit_address_get\nData: #{data.inspect}\nStatus code: #{status_code}\nHeaders: #{headers}"
      end
      return data, status_code, headers
    end

    # Retrieve the latest users deposits
    # @param currency [String] The currency symbol
    # @param [Hash] opts the optional parameters
    # @option opts [Integer] :count Number of requested items, default - &#x60;10&#x60;
    # @option opts [Integer] :offset The offset for pagination, default - &#x60;0&#x60;
    # @return [Object]
    def private_get_deposits_get(currency, opts = {})
      data, _status_code, _headers = private_get_deposits_get_with_http_info(currency, opts)
      data
    end

    # Retrieve the latest users deposits
    # @param currency [String] The currency symbol
    # @param [Hash] opts the optional parameters
    # @option opts [Integer] :count Number of requested items, default - &#x60;10&#x60;
    # @option opts [Integer] :offset The offset for pagination, default - &#x60;0&#x60;
    # @return [Array<(Object, Integer, Hash)>] Object data, response status code and response headers
    def private_get_deposits_get_with_http_info(currency, opts = {})
      if @api_client.config.debugging
        @api_client.config.logger.debug 'Calling API: PrivateApi.private_get_deposits_get ...'
      end
      # verify the required parameter 'currency' is set
      if @api_client.config.client_side_validation && currency.nil?
        fail ArgumentError, "Missing the required parameter 'currency' when calling PrivateApi.private_get_deposits_get"
      end
      # verify enum value
      allowable_values = ["BTC", "ETH"]
      if @api_client.config.client_side_validation && !allowable_values.include?(currency)
        fail ArgumentError, "invalid value for \"currency\", must be one of #{allowable_values}"
      end
      # resource path
      local_var_path = '/private/get_deposits'

      # query parameters
      query_params = opts[:query_params] || {}
      query_params[:'currency'] = currency
      query_params[:'count'] = opts[:'count'] if !opts[:'count'].nil?
      query_params[:'offset'] = opts[:'offset'] if !opts[:'offset'].nil?

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
        @api_client.config.logger.debug "API called: PrivateApi#private_get_deposits_get\nData: #{data.inspect}\nStatus code: #{status_code}\nHeaders: #{headers}"
      end
      return data, status_code, headers
    end

    # Retrieves the language to be used for emails.
    # @param [Hash] opts the optional parameters
    # @return [Object]
    def private_get_email_language_get(opts = {})
      data, _status_code, _headers = private_get_email_language_get_with_http_info(opts)
      data
    end

    # Retrieves the language to be used for emails.
    # @param [Hash] opts the optional parameters
    # @return [Array<(Object, Integer, Hash)>] Object data, response status code and response headers
    def private_get_email_language_get_with_http_info(opts = {})
      if @api_client.config.debugging
        @api_client.config.logger.debug 'Calling API: PrivateApi.private_get_email_language_get ...'
      end
      # resource path
      local_var_path = '/private/get_email_language'

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
        @api_client.config.logger.debug "API called: PrivateApi#private_get_email_language_get\nData: #{data.inspect}\nStatus code: #{status_code}\nHeaders: #{headers}"
      end
      return data, status_code, headers
    end

    # Get margins for given instrument, amount and price.
    # @param instrument_name [String] Instrument name
    # @param amount [Float] Amount, integer for future, float for option. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH.
    # @param price [Float] Price
    # @param [Hash] opts the optional parameters
    # @return [Object]
    def private_get_margins_get(instrument_name, amount, price, opts = {})
      data, _status_code, _headers = private_get_margins_get_with_http_info(instrument_name, amount, price, opts)
      data
    end

    # Get margins for given instrument, amount and price.
    # @param instrument_name [String] Instrument name
    # @param amount [Float] Amount, integer for future, float for option. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH.
    # @param price [Float] Price
    # @param [Hash] opts the optional parameters
    # @return [Array<(Object, Integer, Hash)>] Object data, response status code and response headers
    def private_get_margins_get_with_http_info(instrument_name, amount, price, opts = {})
      if @api_client.config.debugging
        @api_client.config.logger.debug 'Calling API: PrivateApi.private_get_margins_get ...'
      end
      # verify the required parameter 'instrument_name' is set
      if @api_client.config.client_side_validation && instrument_name.nil?
        fail ArgumentError, "Missing the required parameter 'instrument_name' when calling PrivateApi.private_get_margins_get"
      end
      # verify the required parameter 'amount' is set
      if @api_client.config.client_side_validation && amount.nil?
        fail ArgumentError, "Missing the required parameter 'amount' when calling PrivateApi.private_get_margins_get"
      end
      # verify the required parameter 'price' is set
      if @api_client.config.client_side_validation && price.nil?
        fail ArgumentError, "Missing the required parameter 'price' when calling PrivateApi.private_get_margins_get"
      end
      # resource path
      local_var_path = '/private/get_margins'

      # query parameters
      query_params = opts[:query_params] || {}
      query_params[:'instrument_name'] = instrument_name
      query_params[:'amount'] = amount
      query_params[:'price'] = price

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
        @api_client.config.logger.debug "API called: PrivateApi#private_get_margins_get\nData: #{data.inspect}\nStatus code: #{status_code}\nHeaders: #{headers}"
      end
      return data, status_code, headers
    end

    # Retrieves announcements that have not been marked read by the user.
    # @param [Hash] opts the optional parameters
    # @return [Object]
    def private_get_new_announcements_get(opts = {})
      data, _status_code, _headers = private_get_new_announcements_get_with_http_info(opts)
      data
    end

    # Retrieves announcements that have not been marked read by the user.
    # @param [Hash] opts the optional parameters
    # @return [Array<(Object, Integer, Hash)>] Object data, response status code and response headers
    def private_get_new_announcements_get_with_http_info(opts = {})
      if @api_client.config.debugging
        @api_client.config.logger.debug 'Calling API: PrivateApi.private_get_new_announcements_get ...'
      end
      # resource path
      local_var_path = '/private/get_new_announcements'

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
        @api_client.config.logger.debug "API called: PrivateApi#private_get_new_announcements_get\nData: #{data.inspect}\nStatus code: #{status_code}\nHeaders: #{headers}"
      end
      return data, status_code, headers
    end

    # Retrieves list of user's open orders.
    # @param currency [String] The currency symbol
    # @param [Hash] opts the optional parameters
    # @option opts [String] :kind Instrument kind, if not provided instruments of all kinds are considered
    # @option opts [String] :type Order type, default - &#x60;all&#x60;
    # @return [Object]
    def private_get_open_orders_by_currency_get(currency, opts = {})
      data, _status_code, _headers = private_get_open_orders_by_currency_get_with_http_info(currency, opts)
      data
    end

    # Retrieves list of user&#39;s open orders.
    # @param currency [String] The currency symbol
    # @param [Hash] opts the optional parameters
    # @option opts [String] :kind Instrument kind, if not provided instruments of all kinds are considered
    # @option opts [String] :type Order type, default - &#x60;all&#x60;
    # @return [Array<(Object, Integer, Hash)>] Object data, response status code and response headers
    def private_get_open_orders_by_currency_get_with_http_info(currency, opts = {})
      if @api_client.config.debugging
        @api_client.config.logger.debug 'Calling API: PrivateApi.private_get_open_orders_by_currency_get ...'
      end
      # verify the required parameter 'currency' is set
      if @api_client.config.client_side_validation && currency.nil?
        fail ArgumentError, "Missing the required parameter 'currency' when calling PrivateApi.private_get_open_orders_by_currency_get"
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
      allowable_values = ["all", "limit", "stop_all", "stop_limit", "stop_market"]
      if @api_client.config.client_side_validation && opts[:'type'] && !allowable_values.include?(opts[:'type'])
        fail ArgumentError, "invalid value for \"type\", must be one of #{allowable_values}"
      end
      # resource path
      local_var_path = '/private/get_open_orders_by_currency'

      # query parameters
      query_params = opts[:query_params] || {}
      query_params[:'currency'] = currency
      query_params[:'kind'] = opts[:'kind'] if !opts[:'kind'].nil?
      query_params[:'type'] = opts[:'type'] if !opts[:'type'].nil?

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
        @api_client.config.logger.debug "API called: PrivateApi#private_get_open_orders_by_currency_get\nData: #{data.inspect}\nStatus code: #{status_code}\nHeaders: #{headers}"
      end
      return data, status_code, headers
    end

    # Retrieves list of user's open orders within given Instrument.
    # @param instrument_name [String] Instrument name
    # @param [Hash] opts the optional parameters
    # @option opts [String] :type Order type, default - &#x60;all&#x60;
    # @return [Object]
    def private_get_open_orders_by_instrument_get(instrument_name, opts = {})
      data, _status_code, _headers = private_get_open_orders_by_instrument_get_with_http_info(instrument_name, opts)
      data
    end

    # Retrieves list of user&#39;s open orders within given Instrument.
    # @param instrument_name [String] Instrument name
    # @param [Hash] opts the optional parameters
    # @option opts [String] :type Order type, default - &#x60;all&#x60;
    # @return [Array<(Object, Integer, Hash)>] Object data, response status code and response headers
    def private_get_open_orders_by_instrument_get_with_http_info(instrument_name, opts = {})
      if @api_client.config.debugging
        @api_client.config.logger.debug 'Calling API: PrivateApi.private_get_open_orders_by_instrument_get ...'
      end
      # verify the required parameter 'instrument_name' is set
      if @api_client.config.client_side_validation && instrument_name.nil?
        fail ArgumentError, "Missing the required parameter 'instrument_name' when calling PrivateApi.private_get_open_orders_by_instrument_get"
      end
      allowable_values = ["all", "limit", "stop_all", "stop_limit", "stop_market"]
      if @api_client.config.client_side_validation && opts[:'type'] && !allowable_values.include?(opts[:'type'])
        fail ArgumentError, "invalid value for \"type\", must be one of #{allowable_values}"
      end
      # resource path
      local_var_path = '/private/get_open_orders_by_instrument'

      # query parameters
      query_params = opts[:query_params] || {}
      query_params[:'instrument_name'] = instrument_name
      query_params[:'type'] = opts[:'type'] if !opts[:'type'].nil?

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
        @api_client.config.logger.debug "API called: PrivateApi#private_get_open_orders_by_instrument_get\nData: #{data.inspect}\nStatus code: #{status_code}\nHeaders: #{headers}"
      end
      return data, status_code, headers
    end

    # Retrieves history of orders that have been partially or fully filled.
    # @param currency [String] The currency symbol
    # @param [Hash] opts the optional parameters
    # @option opts [String] :kind Instrument kind, if not provided instruments of all kinds are considered
    # @option opts [Integer] :count Number of requested items, default - &#x60;20&#x60;
    # @option opts [Integer] :offset The offset for pagination, default - &#x60;0&#x60;
    # @option opts [Boolean] :include_old Include in result orders older than 2 days, default - &#x60;false&#x60;
    # @option opts [Boolean] :include_unfilled Include in result fully unfilled closed orders, default - &#x60;false&#x60;
    # @return [Object]
    def private_get_order_history_by_currency_get(currency, opts = {})
      data, _status_code, _headers = private_get_order_history_by_currency_get_with_http_info(currency, opts)
      data
    end

    # Retrieves history of orders that have been partially or fully filled.
    # @param currency [String] The currency symbol
    # @param [Hash] opts the optional parameters
    # @option opts [String] :kind Instrument kind, if not provided instruments of all kinds are considered
    # @option opts [Integer] :count Number of requested items, default - &#x60;20&#x60;
    # @option opts [Integer] :offset The offset for pagination, default - &#x60;0&#x60;
    # @option opts [Boolean] :include_old Include in result orders older than 2 days, default - &#x60;false&#x60;
    # @option opts [Boolean] :include_unfilled Include in result fully unfilled closed orders, default - &#x60;false&#x60;
    # @return [Array<(Object, Integer, Hash)>] Object data, response status code and response headers
    def private_get_order_history_by_currency_get_with_http_info(currency, opts = {})
      if @api_client.config.debugging
        @api_client.config.logger.debug 'Calling API: PrivateApi.private_get_order_history_by_currency_get ...'
      end
      # verify the required parameter 'currency' is set
      if @api_client.config.client_side_validation && currency.nil?
        fail ArgumentError, "Missing the required parameter 'currency' when calling PrivateApi.private_get_order_history_by_currency_get"
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
      local_var_path = '/private/get_order_history_by_currency'

      # query parameters
      query_params = opts[:query_params] || {}
      query_params[:'currency'] = currency
      query_params[:'kind'] = opts[:'kind'] if !opts[:'kind'].nil?
      query_params[:'count'] = opts[:'count'] if !opts[:'count'].nil?
      query_params[:'offset'] = opts[:'offset'] if !opts[:'offset'].nil?
      query_params[:'include_old'] = opts[:'include_old'] if !opts[:'include_old'].nil?
      query_params[:'include_unfilled'] = opts[:'include_unfilled'] if !opts[:'include_unfilled'].nil?

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
        @api_client.config.logger.debug "API called: PrivateApi#private_get_order_history_by_currency_get\nData: #{data.inspect}\nStatus code: #{status_code}\nHeaders: #{headers}"
      end
      return data, status_code, headers
    end

    # Retrieves history of orders that have been partially or fully filled.
    # @param instrument_name [String] Instrument name
    # @param [Hash] opts the optional parameters
    # @option opts [Integer] :count Number of requested items, default - &#x60;20&#x60;
    # @option opts [Integer] :offset The offset for pagination, default - &#x60;0&#x60;
    # @option opts [Boolean] :include_old Include in result orders older than 2 days, default - &#x60;false&#x60;
    # @option opts [Boolean] :include_unfilled Include in result fully unfilled closed orders, default - &#x60;false&#x60;
    # @return [Object]
    def private_get_order_history_by_instrument_get(instrument_name, opts = {})
      data, _status_code, _headers = private_get_order_history_by_instrument_get_with_http_info(instrument_name, opts)
      data
    end

    # Retrieves history of orders that have been partially or fully filled.
    # @param instrument_name [String] Instrument name
    # @param [Hash] opts the optional parameters
    # @option opts [Integer] :count Number of requested items, default - &#x60;20&#x60;
    # @option opts [Integer] :offset The offset for pagination, default - &#x60;0&#x60;
    # @option opts [Boolean] :include_old Include in result orders older than 2 days, default - &#x60;false&#x60;
    # @option opts [Boolean] :include_unfilled Include in result fully unfilled closed orders, default - &#x60;false&#x60;
    # @return [Array<(Object, Integer, Hash)>] Object data, response status code and response headers
    def private_get_order_history_by_instrument_get_with_http_info(instrument_name, opts = {})
      if @api_client.config.debugging
        @api_client.config.logger.debug 'Calling API: PrivateApi.private_get_order_history_by_instrument_get ...'
      end
      # verify the required parameter 'instrument_name' is set
      if @api_client.config.client_side_validation && instrument_name.nil?
        fail ArgumentError, "Missing the required parameter 'instrument_name' when calling PrivateApi.private_get_order_history_by_instrument_get"
      end
      # resource path
      local_var_path = '/private/get_order_history_by_instrument'

      # query parameters
      query_params = opts[:query_params] || {}
      query_params[:'instrument_name'] = instrument_name
      query_params[:'count'] = opts[:'count'] if !opts[:'count'].nil?
      query_params[:'offset'] = opts[:'offset'] if !opts[:'offset'].nil?
      query_params[:'include_old'] = opts[:'include_old'] if !opts[:'include_old'].nil?
      query_params[:'include_unfilled'] = opts[:'include_unfilled'] if !opts[:'include_unfilled'].nil?

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
        @api_client.config.logger.debug "API called: PrivateApi#private_get_order_history_by_instrument_get\nData: #{data.inspect}\nStatus code: #{status_code}\nHeaders: #{headers}"
      end
      return data, status_code, headers
    end

    # Retrieves initial margins of given orders
    # @param ids [Array<String>] Ids of orders
    # @param [Hash] opts the optional parameters
    # @return [Object]
    def private_get_order_margin_by_ids_get(ids, opts = {})
      data, _status_code, _headers = private_get_order_margin_by_ids_get_with_http_info(ids, opts)
      data
    end

    # Retrieves initial margins of given orders
    # @param ids [Array<String>] Ids of orders
    # @param [Hash] opts the optional parameters
    # @return [Array<(Object, Integer, Hash)>] Object data, response status code and response headers
    def private_get_order_margin_by_ids_get_with_http_info(ids, opts = {})
      if @api_client.config.debugging
        @api_client.config.logger.debug 'Calling API: PrivateApi.private_get_order_margin_by_ids_get ...'
      end
      # verify the required parameter 'ids' is set
      if @api_client.config.client_side_validation && ids.nil?
        fail ArgumentError, "Missing the required parameter 'ids' when calling PrivateApi.private_get_order_margin_by_ids_get"
      end
      # resource path
      local_var_path = '/private/get_order_margin_by_ids'

      # query parameters
      query_params = opts[:query_params] || {}
      query_params[:'ids'] = @api_client.build_collection_param(ids, :multi)

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
        @api_client.config.logger.debug "API called: PrivateApi#private_get_order_margin_by_ids_get\nData: #{data.inspect}\nStatus code: #{status_code}\nHeaders: #{headers}"
      end
      return data, status_code, headers
    end

    # Retrieve the current state of an order.
    # @param order_id [String] The order id
    # @param [Hash] opts the optional parameters
    # @return [Object]
    def private_get_order_state_get(order_id, opts = {})
      data, _status_code, _headers = private_get_order_state_get_with_http_info(order_id, opts)
      data
    end

    # Retrieve the current state of an order.
    # @param order_id [String] The order id
    # @param [Hash] opts the optional parameters
    # @return [Array<(Object, Integer, Hash)>] Object data, response status code and response headers
    def private_get_order_state_get_with_http_info(order_id, opts = {})
      if @api_client.config.debugging
        @api_client.config.logger.debug 'Calling API: PrivateApi.private_get_order_state_get ...'
      end
      # verify the required parameter 'order_id' is set
      if @api_client.config.client_side_validation && order_id.nil?
        fail ArgumentError, "Missing the required parameter 'order_id' when calling PrivateApi.private_get_order_state_get"
      end
      # resource path
      local_var_path = '/private/get_order_state'

      # query parameters
      query_params = opts[:query_params] || {}
      query_params[:'order_id'] = order_id

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
        @api_client.config.logger.debug "API called: PrivateApi#private_get_order_state_get\nData: #{data.inspect}\nStatus code: #{status_code}\nHeaders: #{headers}"
      end
      return data, status_code, headers
    end

    # Retrieve user position.
    # @param instrument_name [String] Instrument name
    # @param [Hash] opts the optional parameters
    # @return [Object]
    def private_get_position_get(instrument_name, opts = {})
      data, _status_code, _headers = private_get_position_get_with_http_info(instrument_name, opts)
      data
    end

    # Retrieve user position.
    # @param instrument_name [String] Instrument name
    # @param [Hash] opts the optional parameters
    # @return [Array<(Object, Integer, Hash)>] Object data, response status code and response headers
    def private_get_position_get_with_http_info(instrument_name, opts = {})
      if @api_client.config.debugging
        @api_client.config.logger.debug 'Calling API: PrivateApi.private_get_position_get ...'
      end
      # verify the required parameter 'instrument_name' is set
      if @api_client.config.client_side_validation && instrument_name.nil?
        fail ArgumentError, "Missing the required parameter 'instrument_name' when calling PrivateApi.private_get_position_get"
      end
      # resource path
      local_var_path = '/private/get_position'

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
        @api_client.config.logger.debug "API called: PrivateApi#private_get_position_get\nData: #{data.inspect}\nStatus code: #{status_code}\nHeaders: #{headers}"
      end
      return data, status_code, headers
    end

    # Retrieve user positions.
    # @param currency [String] 
    # @param [Hash] opts the optional parameters
    # @option opts [String] :kind Kind filter on positions
    # @return [Object]
    def private_get_positions_get(currency, opts = {})
      data, _status_code, _headers = private_get_positions_get_with_http_info(currency, opts)
      data
    end

    # Retrieve user positions.
    # @param currency [String] 
    # @param [Hash] opts the optional parameters
    # @option opts [String] :kind Kind filter on positions
    # @return [Array<(Object, Integer, Hash)>] Object data, response status code and response headers
    def private_get_positions_get_with_http_info(currency, opts = {})
      if @api_client.config.debugging
        @api_client.config.logger.debug 'Calling API: PrivateApi.private_get_positions_get ...'
      end
      # verify the required parameter 'currency' is set
      if @api_client.config.client_side_validation && currency.nil?
        fail ArgumentError, "Missing the required parameter 'currency' when calling PrivateApi.private_get_positions_get"
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
      local_var_path = '/private/get_positions'

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
        @api_client.config.logger.debug "API called: PrivateApi#private_get_positions_get\nData: #{data.inspect}\nStatus code: #{status_code}\nHeaders: #{headers}"
      end
      return data, status_code, headers
    end

    # Retrieves settlement, delivery and bankruptcy events that have affected your account.
    # @param currency [String] The currency symbol
    # @param [Hash] opts the optional parameters
    # @option opts [String] :type Settlement type
    # @option opts [Integer] :count Number of requested items, default - &#x60;20&#x60;
    # @return [Object]
    def private_get_settlement_history_by_currency_get(currency, opts = {})
      data, _status_code, _headers = private_get_settlement_history_by_currency_get_with_http_info(currency, opts)
      data
    end

    # Retrieves settlement, delivery and bankruptcy events that have affected your account.
    # @param currency [String] The currency symbol
    # @param [Hash] opts the optional parameters
    # @option opts [String] :type Settlement type
    # @option opts [Integer] :count Number of requested items, default - &#x60;20&#x60;
    # @return [Array<(Object, Integer, Hash)>] Object data, response status code and response headers
    def private_get_settlement_history_by_currency_get_with_http_info(currency, opts = {})
      if @api_client.config.debugging
        @api_client.config.logger.debug 'Calling API: PrivateApi.private_get_settlement_history_by_currency_get ...'
      end
      # verify the required parameter 'currency' is set
      if @api_client.config.client_side_validation && currency.nil?
        fail ArgumentError, "Missing the required parameter 'currency' when calling PrivateApi.private_get_settlement_history_by_currency_get"
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
      local_var_path = '/private/get_settlement_history_by_currency'

      # query parameters
      query_params = opts[:query_params] || {}
      query_params[:'currency'] = currency
      query_params[:'type'] = opts[:'type'] if !opts[:'type'].nil?
      query_params[:'count'] = opts[:'count'] if !opts[:'count'].nil?

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
        @api_client.config.logger.debug "API called: PrivateApi#private_get_settlement_history_by_currency_get\nData: #{data.inspect}\nStatus code: #{status_code}\nHeaders: #{headers}"
      end
      return data, status_code, headers
    end

    # Retrieves public settlement, delivery and bankruptcy events filtered by instrument name
    # @param instrument_name [String] Instrument name
    # @param [Hash] opts the optional parameters
    # @option opts [String] :type Settlement type
    # @option opts [Integer] :count Number of requested items, default - &#x60;20&#x60;
    # @return [Object]
    def private_get_settlement_history_by_instrument_get(instrument_name, opts = {})
      data, _status_code, _headers = private_get_settlement_history_by_instrument_get_with_http_info(instrument_name, opts)
      data
    end

    # Retrieves public settlement, delivery and bankruptcy events filtered by instrument name
    # @param instrument_name [String] Instrument name
    # @param [Hash] opts the optional parameters
    # @option opts [String] :type Settlement type
    # @option opts [Integer] :count Number of requested items, default - &#x60;20&#x60;
    # @return [Array<(Object, Integer, Hash)>] Object data, response status code and response headers
    def private_get_settlement_history_by_instrument_get_with_http_info(instrument_name, opts = {})
      if @api_client.config.debugging
        @api_client.config.logger.debug 'Calling API: PrivateApi.private_get_settlement_history_by_instrument_get ...'
      end
      # verify the required parameter 'instrument_name' is set
      if @api_client.config.client_side_validation && instrument_name.nil?
        fail ArgumentError, "Missing the required parameter 'instrument_name' when calling PrivateApi.private_get_settlement_history_by_instrument_get"
      end
      allowable_values = ["settlement", "delivery", "bankruptcy"]
      if @api_client.config.client_side_validation && opts[:'type'] && !allowable_values.include?(opts[:'type'])
        fail ArgumentError, "invalid value for \"type\", must be one of #{allowable_values}"
      end
      # resource path
      local_var_path = '/private/get_settlement_history_by_instrument'

      # query parameters
      query_params = opts[:query_params] || {}
      query_params[:'instrument_name'] = instrument_name
      query_params[:'type'] = opts[:'type'] if !opts[:'type'].nil?
      query_params[:'count'] = opts[:'count'] if !opts[:'count'].nil?

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
        @api_client.config.logger.debug "API called: PrivateApi#private_get_settlement_history_by_instrument_get\nData: #{data.inspect}\nStatus code: #{status_code}\nHeaders: #{headers}"
      end
      return data, status_code, headers
    end

    # Get information about subaccounts
    # @param [Hash] opts the optional parameters
    # @option opts [Boolean] :with_portfolio 
    # @return [Object]
    def private_get_subaccounts_get(opts = {})
      data, _status_code, _headers = private_get_subaccounts_get_with_http_info(opts)
      data
    end

    # Get information about subaccounts
    # @param [Hash] opts the optional parameters
    # @option opts [Boolean] :with_portfolio 
    # @return [Array<(Object, Integer, Hash)>] Object data, response status code and response headers
    def private_get_subaccounts_get_with_http_info(opts = {})
      if @api_client.config.debugging
        @api_client.config.logger.debug 'Calling API: PrivateApi.private_get_subaccounts_get ...'
      end
      # resource path
      local_var_path = '/private/get_subaccounts'

      # query parameters
      query_params = opts[:query_params] || {}
      query_params[:'with_portfolio'] = opts[:'with_portfolio'] if !opts[:'with_portfolio'].nil?

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
        @api_client.config.logger.debug "API called: PrivateApi#private_get_subaccounts_get\nData: #{data.inspect}\nStatus code: #{status_code}\nHeaders: #{headers}"
      end
      return data, status_code, headers
    end

    # Adds new entry to address book of given type
    # @param currency [String] The currency symbol
    # @param [Hash] opts the optional parameters
    # @option opts [Integer] :count Number of requested items, default - &#x60;10&#x60;
    # @option opts [Integer] :offset The offset for pagination, default - &#x60;0&#x60;
    # @return [Object]
    def private_get_transfers_get(currency, opts = {})
      data, _status_code, _headers = private_get_transfers_get_with_http_info(currency, opts)
      data
    end

    # Adds new entry to address book of given type
    # @param currency [String] The currency symbol
    # @param [Hash] opts the optional parameters
    # @option opts [Integer] :count Number of requested items, default - &#x60;10&#x60;
    # @option opts [Integer] :offset The offset for pagination, default - &#x60;0&#x60;
    # @return [Array<(Object, Integer, Hash)>] Object data, response status code and response headers
    def private_get_transfers_get_with_http_info(currency, opts = {})
      if @api_client.config.debugging
        @api_client.config.logger.debug 'Calling API: PrivateApi.private_get_transfers_get ...'
      end
      # verify the required parameter 'currency' is set
      if @api_client.config.client_side_validation && currency.nil?
        fail ArgumentError, "Missing the required parameter 'currency' when calling PrivateApi.private_get_transfers_get"
      end
      # verify enum value
      allowable_values = ["BTC", "ETH"]
      if @api_client.config.client_side_validation && !allowable_values.include?(currency)
        fail ArgumentError, "invalid value for \"currency\", must be one of #{allowable_values}"
      end
      # resource path
      local_var_path = '/private/get_transfers'

      # query parameters
      query_params = opts[:query_params] || {}
      query_params[:'currency'] = currency
      query_params[:'count'] = opts[:'count'] if !opts[:'count'].nil?
      query_params[:'offset'] = opts[:'offset'] if !opts[:'offset'].nil?

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
        @api_client.config.logger.debug "API called: PrivateApi#private_get_transfers_get\nData: #{data.inspect}\nStatus code: #{status_code}\nHeaders: #{headers}"
      end
      return data, status_code, headers
    end

    # Retrieve the latest user trades that have occurred for instruments in a specific currency symbol and within given time range.
    # @param currency [String] The currency symbol
    # @param start_timestamp [Integer] The earliest timestamp to return result for
    # @param end_timestamp [Integer] The most recent timestamp to return result for
    # @param [Hash] opts the optional parameters
    # @option opts [String] :kind Instrument kind, if not provided instruments of all kinds are considered
    # @option opts [Integer] :count Number of requested items, default - &#x60;10&#x60;
    # @option opts [Boolean] :include_old Include trades older than 7 days, default - &#x60;false&#x60;
    # @option opts [String] :sorting Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database)
    # @return [Object]
    def private_get_user_trades_by_currency_and_time_get(currency, start_timestamp, end_timestamp, opts = {})
      data, _status_code, _headers = private_get_user_trades_by_currency_and_time_get_with_http_info(currency, start_timestamp, end_timestamp, opts)
      data
    end

    # Retrieve the latest user trades that have occurred for instruments in a specific currency symbol and within given time range.
    # @param currency [String] The currency symbol
    # @param start_timestamp [Integer] The earliest timestamp to return result for
    # @param end_timestamp [Integer] The most recent timestamp to return result for
    # @param [Hash] opts the optional parameters
    # @option opts [String] :kind Instrument kind, if not provided instruments of all kinds are considered
    # @option opts [Integer] :count Number of requested items, default - &#x60;10&#x60;
    # @option opts [Boolean] :include_old Include trades older than 7 days, default - &#x60;false&#x60;
    # @option opts [String] :sorting Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database)
    # @return [Array<(Object, Integer, Hash)>] Object data, response status code and response headers
    def private_get_user_trades_by_currency_and_time_get_with_http_info(currency, start_timestamp, end_timestamp, opts = {})
      if @api_client.config.debugging
        @api_client.config.logger.debug 'Calling API: PrivateApi.private_get_user_trades_by_currency_and_time_get ...'
      end
      # verify the required parameter 'currency' is set
      if @api_client.config.client_side_validation && currency.nil?
        fail ArgumentError, "Missing the required parameter 'currency' when calling PrivateApi.private_get_user_trades_by_currency_and_time_get"
      end
      # verify enum value
      allowable_values = ["BTC", "ETH"]
      if @api_client.config.client_side_validation && !allowable_values.include?(currency)
        fail ArgumentError, "invalid value for \"currency\", must be one of #{allowable_values}"
      end
      # verify the required parameter 'start_timestamp' is set
      if @api_client.config.client_side_validation && start_timestamp.nil?
        fail ArgumentError, "Missing the required parameter 'start_timestamp' when calling PrivateApi.private_get_user_trades_by_currency_and_time_get"
      end
      # verify the required parameter 'end_timestamp' is set
      if @api_client.config.client_side_validation && end_timestamp.nil?
        fail ArgumentError, "Missing the required parameter 'end_timestamp' when calling PrivateApi.private_get_user_trades_by_currency_and_time_get"
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
      local_var_path = '/private/get_user_trades_by_currency_and_time'

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
        @api_client.config.logger.debug "API called: PrivateApi#private_get_user_trades_by_currency_and_time_get\nData: #{data.inspect}\nStatus code: #{status_code}\nHeaders: #{headers}"
      end
      return data, status_code, headers
    end

    # Retrieve the latest user trades that have occurred for instruments in a specific currency symbol.
    # @param currency [String] The currency symbol
    # @param [Hash] opts the optional parameters
    # @option opts [String] :kind Instrument kind, if not provided instruments of all kinds are considered
    # @option opts [String] :start_id The ID number of the first trade to be returned
    # @option opts [String] :end_id The ID number of the last trade to be returned
    # @option opts [Integer] :count Number of requested items, default - &#x60;10&#x60;
    # @option opts [Boolean] :include_old Include trades older than 7 days, default - &#x60;false&#x60;
    # @option opts [String] :sorting Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database)
    # @return [Object]
    def private_get_user_trades_by_currency_get(currency, opts = {})
      data, _status_code, _headers = private_get_user_trades_by_currency_get_with_http_info(currency, opts)
      data
    end

    # Retrieve the latest user trades that have occurred for instruments in a specific currency symbol.
    # @param currency [String] The currency symbol
    # @param [Hash] opts the optional parameters
    # @option opts [String] :kind Instrument kind, if not provided instruments of all kinds are considered
    # @option opts [String] :start_id The ID number of the first trade to be returned
    # @option opts [String] :end_id The ID number of the last trade to be returned
    # @option opts [Integer] :count Number of requested items, default - &#x60;10&#x60;
    # @option opts [Boolean] :include_old Include trades older than 7 days, default - &#x60;false&#x60;
    # @option opts [String] :sorting Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database)
    # @return [Array<(Object, Integer, Hash)>] Object data, response status code and response headers
    def private_get_user_trades_by_currency_get_with_http_info(currency, opts = {})
      if @api_client.config.debugging
        @api_client.config.logger.debug 'Calling API: PrivateApi.private_get_user_trades_by_currency_get ...'
      end
      # verify the required parameter 'currency' is set
      if @api_client.config.client_side_validation && currency.nil?
        fail ArgumentError, "Missing the required parameter 'currency' when calling PrivateApi.private_get_user_trades_by_currency_get"
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
      local_var_path = '/private/get_user_trades_by_currency'

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
        @api_client.config.logger.debug "API called: PrivateApi#private_get_user_trades_by_currency_get\nData: #{data.inspect}\nStatus code: #{status_code}\nHeaders: #{headers}"
      end
      return data, status_code, headers
    end

    # Retrieve the latest user trades that have occurred for a specific instrument and within given time range.
    # @param instrument_name [String] Instrument name
    # @param start_timestamp [Integer] The earliest timestamp to return result for
    # @param end_timestamp [Integer] The most recent timestamp to return result for
    # @param [Hash] opts the optional parameters
    # @option opts [Integer] :count Number of requested items, default - &#x60;10&#x60;
    # @option opts [Boolean] :include_old Include trades older than 7 days, default - &#x60;false&#x60;
    # @option opts [String] :sorting Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database)
    # @return [Object]
    def private_get_user_trades_by_instrument_and_time_get(instrument_name, start_timestamp, end_timestamp, opts = {})
      data, _status_code, _headers = private_get_user_trades_by_instrument_and_time_get_with_http_info(instrument_name, start_timestamp, end_timestamp, opts)
      data
    end

    # Retrieve the latest user trades that have occurred for a specific instrument and within given time range.
    # @param instrument_name [String] Instrument name
    # @param start_timestamp [Integer] The earliest timestamp to return result for
    # @param end_timestamp [Integer] The most recent timestamp to return result for
    # @param [Hash] opts the optional parameters
    # @option opts [Integer] :count Number of requested items, default - &#x60;10&#x60;
    # @option opts [Boolean] :include_old Include trades older than 7 days, default - &#x60;false&#x60;
    # @option opts [String] :sorting Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database)
    # @return [Array<(Object, Integer, Hash)>] Object data, response status code and response headers
    def private_get_user_trades_by_instrument_and_time_get_with_http_info(instrument_name, start_timestamp, end_timestamp, opts = {})
      if @api_client.config.debugging
        @api_client.config.logger.debug 'Calling API: PrivateApi.private_get_user_trades_by_instrument_and_time_get ...'
      end
      # verify the required parameter 'instrument_name' is set
      if @api_client.config.client_side_validation && instrument_name.nil?
        fail ArgumentError, "Missing the required parameter 'instrument_name' when calling PrivateApi.private_get_user_trades_by_instrument_and_time_get"
      end
      # verify the required parameter 'start_timestamp' is set
      if @api_client.config.client_side_validation && start_timestamp.nil?
        fail ArgumentError, "Missing the required parameter 'start_timestamp' when calling PrivateApi.private_get_user_trades_by_instrument_and_time_get"
      end
      # verify the required parameter 'end_timestamp' is set
      if @api_client.config.client_side_validation && end_timestamp.nil?
        fail ArgumentError, "Missing the required parameter 'end_timestamp' when calling PrivateApi.private_get_user_trades_by_instrument_and_time_get"
      end
      allowable_values = ["asc", "desc", "default"]
      if @api_client.config.client_side_validation && opts[:'sorting'] && !allowable_values.include?(opts[:'sorting'])
        fail ArgumentError, "invalid value for \"sorting\", must be one of #{allowable_values}"
      end
      # resource path
      local_var_path = '/private/get_user_trades_by_instrument_and_time'

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
        @api_client.config.logger.debug "API called: PrivateApi#private_get_user_trades_by_instrument_and_time_get\nData: #{data.inspect}\nStatus code: #{status_code}\nHeaders: #{headers}"
      end
      return data, status_code, headers
    end

    # Retrieve the latest user trades that have occurred for a specific instrument.
    # @param instrument_name [String] Instrument name
    # @param [Hash] opts the optional parameters
    # @option opts [Integer] :start_seq The sequence number of the first trade to be returned
    # @option opts [Integer] :end_seq The sequence number of the last trade to be returned
    # @option opts [Integer] :count Number of requested items, default - &#x60;10&#x60;
    # @option opts [Boolean] :include_old Include trades older than 7 days, default - &#x60;false&#x60;
    # @option opts [String] :sorting Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database)
    # @return [Object]
    def private_get_user_trades_by_instrument_get(instrument_name, opts = {})
      data, _status_code, _headers = private_get_user_trades_by_instrument_get_with_http_info(instrument_name, opts)
      data
    end

    # Retrieve the latest user trades that have occurred for a specific instrument.
    # @param instrument_name [String] Instrument name
    # @param [Hash] opts the optional parameters
    # @option opts [Integer] :start_seq The sequence number of the first trade to be returned
    # @option opts [Integer] :end_seq The sequence number of the last trade to be returned
    # @option opts [Integer] :count Number of requested items, default - &#x60;10&#x60;
    # @option opts [Boolean] :include_old Include trades older than 7 days, default - &#x60;false&#x60;
    # @option opts [String] :sorting Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database)
    # @return [Array<(Object, Integer, Hash)>] Object data, response status code and response headers
    def private_get_user_trades_by_instrument_get_with_http_info(instrument_name, opts = {})
      if @api_client.config.debugging
        @api_client.config.logger.debug 'Calling API: PrivateApi.private_get_user_trades_by_instrument_get ...'
      end
      # verify the required parameter 'instrument_name' is set
      if @api_client.config.client_side_validation && instrument_name.nil?
        fail ArgumentError, "Missing the required parameter 'instrument_name' when calling PrivateApi.private_get_user_trades_by_instrument_get"
      end
      allowable_values = ["asc", "desc", "default"]
      if @api_client.config.client_side_validation && opts[:'sorting'] && !allowable_values.include?(opts[:'sorting'])
        fail ArgumentError, "invalid value for \"sorting\", must be one of #{allowable_values}"
      end
      # resource path
      local_var_path = '/private/get_user_trades_by_instrument'

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
        @api_client.config.logger.debug "API called: PrivateApi#private_get_user_trades_by_instrument_get\nData: #{data.inspect}\nStatus code: #{status_code}\nHeaders: #{headers}"
      end
      return data, status_code, headers
    end

    # Retrieve the list of user trades that was created for given order
    # @param order_id [String] The order id
    # @param [Hash] opts the optional parameters
    # @option opts [String] :sorting Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database)
    # @return [Object]
    def private_get_user_trades_by_order_get(order_id, opts = {})
      data, _status_code, _headers = private_get_user_trades_by_order_get_with_http_info(order_id, opts)
      data
    end

    # Retrieve the list of user trades that was created for given order
    # @param order_id [String] The order id
    # @param [Hash] opts the optional parameters
    # @option opts [String] :sorting Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database)
    # @return [Array<(Object, Integer, Hash)>] Object data, response status code and response headers
    def private_get_user_trades_by_order_get_with_http_info(order_id, opts = {})
      if @api_client.config.debugging
        @api_client.config.logger.debug 'Calling API: PrivateApi.private_get_user_trades_by_order_get ...'
      end
      # verify the required parameter 'order_id' is set
      if @api_client.config.client_side_validation && order_id.nil?
        fail ArgumentError, "Missing the required parameter 'order_id' when calling PrivateApi.private_get_user_trades_by_order_get"
      end
      allowable_values = ["asc", "desc", "default"]
      if @api_client.config.client_side_validation && opts[:'sorting'] && !allowable_values.include?(opts[:'sorting'])
        fail ArgumentError, "invalid value for \"sorting\", must be one of #{allowable_values}"
      end
      # resource path
      local_var_path = '/private/get_user_trades_by_order'

      # query parameters
      query_params = opts[:query_params] || {}
      query_params[:'order_id'] = order_id
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
        @api_client.config.logger.debug "API called: PrivateApi#private_get_user_trades_by_order_get\nData: #{data.inspect}\nStatus code: #{status_code}\nHeaders: #{headers}"
      end
      return data, status_code, headers
    end

    # Retrieve the latest users withdrawals
    # @param currency [String] The currency symbol
    # @param [Hash] opts the optional parameters
    # @option opts [Integer] :count Number of requested items, default - &#x60;10&#x60;
    # @option opts [Integer] :offset The offset for pagination, default - &#x60;0&#x60;
    # @return [Object]
    def private_get_withdrawals_get(currency, opts = {})
      data, _status_code, _headers = private_get_withdrawals_get_with_http_info(currency, opts)
      data
    end

    # Retrieve the latest users withdrawals
    # @param currency [String] The currency symbol
    # @param [Hash] opts the optional parameters
    # @option opts [Integer] :count Number of requested items, default - &#x60;10&#x60;
    # @option opts [Integer] :offset The offset for pagination, default - &#x60;0&#x60;
    # @return [Array<(Object, Integer, Hash)>] Object data, response status code and response headers
    def private_get_withdrawals_get_with_http_info(currency, opts = {})
      if @api_client.config.debugging
        @api_client.config.logger.debug 'Calling API: PrivateApi.private_get_withdrawals_get ...'
      end
      # verify the required parameter 'currency' is set
      if @api_client.config.client_side_validation && currency.nil?
        fail ArgumentError, "Missing the required parameter 'currency' when calling PrivateApi.private_get_withdrawals_get"
      end
      # verify enum value
      allowable_values = ["BTC", "ETH"]
      if @api_client.config.client_side_validation && !allowable_values.include?(currency)
        fail ArgumentError, "invalid value for \"currency\", must be one of #{allowable_values}"
      end
      # resource path
      local_var_path = '/private/get_withdrawals'

      # query parameters
      query_params = opts[:query_params] || {}
      query_params[:'currency'] = currency
      query_params[:'count'] = opts[:'count'] if !opts[:'count'].nil?
      query_params[:'offset'] = opts[:'offset'] if !opts[:'offset'].nil?

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
        @api_client.config.logger.debug "API called: PrivateApi#private_get_withdrawals_get\nData: #{data.inspect}\nStatus code: #{status_code}\nHeaders: #{headers}"
      end
      return data, status_code, headers
    end

    # Adds new entry to address book of given type
    # @param currency [String] The currency symbol
    # @param type [String] Address book type
    # @param address [String] Address in currency format, it must be in address book
    # @param [Hash] opts the optional parameters
    # @option opts [String] :tfa TFA code, required when TFA is enabled for current account
    # @return [Object]
    def private_remove_from_address_book_get(currency, type, address, opts = {})
      data, _status_code, _headers = private_remove_from_address_book_get_with_http_info(currency, type, address, opts)
      data
    end

    # Adds new entry to address book of given type
    # @param currency [String] The currency symbol
    # @param type [String] Address book type
    # @param address [String] Address in currency format, it must be in address book
    # @param [Hash] opts the optional parameters
    # @option opts [String] :tfa TFA code, required when TFA is enabled for current account
    # @return [Array<(Object, Integer, Hash)>] Object data, response status code and response headers
    def private_remove_from_address_book_get_with_http_info(currency, type, address, opts = {})
      if @api_client.config.debugging
        @api_client.config.logger.debug 'Calling API: PrivateApi.private_remove_from_address_book_get ...'
      end
      # verify the required parameter 'currency' is set
      if @api_client.config.client_side_validation && currency.nil?
        fail ArgumentError, "Missing the required parameter 'currency' when calling PrivateApi.private_remove_from_address_book_get"
      end
      # verify enum value
      allowable_values = ["BTC", "ETH"]
      if @api_client.config.client_side_validation && !allowable_values.include?(currency)
        fail ArgumentError, "invalid value for \"currency\", must be one of #{allowable_values}"
      end
      # verify the required parameter 'type' is set
      if @api_client.config.client_side_validation && type.nil?
        fail ArgumentError, "Missing the required parameter 'type' when calling PrivateApi.private_remove_from_address_book_get"
      end
      # verify enum value
      allowable_values = ["transfer", "withdrawal"]
      if @api_client.config.client_side_validation && !allowable_values.include?(type)
        fail ArgumentError, "invalid value for \"type\", must be one of #{allowable_values}"
      end
      # verify the required parameter 'address' is set
      if @api_client.config.client_side_validation && address.nil?
        fail ArgumentError, "Missing the required parameter 'address' when calling PrivateApi.private_remove_from_address_book_get"
      end
      # resource path
      local_var_path = '/private/remove_from_address_book'

      # query parameters
      query_params = opts[:query_params] || {}
      query_params[:'currency'] = currency
      query_params[:'type'] = type
      query_params[:'address'] = address
      query_params[:'tfa'] = opts[:'tfa'] if !opts[:'tfa'].nil?

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
        @api_client.config.logger.debug "API called: PrivateApi#private_remove_from_address_book_get\nData: #{data.inspect}\nStatus code: #{status_code}\nHeaders: #{headers}"
      end
      return data, status_code, headers
    end

    # Places a sell order for an instrument.
    # @param instrument_name [String] Instrument name
    # @param amount [Float] It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH
    # @param [Hash] opts the optional parameters
    # @option opts [String] :type The order type, default: &#x60;\&quot;limit\&quot;&#x60;
    # @option opts [String] :label user defined label for the order (maximum 32 characters)
    # @option opts [Float] :price &lt;p&gt;The order price in base currency (Only for limit and stop_limit orders)&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;usd, the field price should be the option price value in USD.&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;implv, the field price should be a value of implied volatility in percentages. For example,  price&#x3D;100, means implied volatility of 100%&lt;/p&gt;
    # @option opts [String] :time_in_force &lt;p&gt;Specifies how long the order remains in effect. Default &#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; &lt;ul&gt; &lt;li&gt;&#x60;\&quot;good_til_cancelled\&quot;&#x60; - unfilled order remains in order book until cancelled&lt;/li&gt; &lt;li&gt;&#x60;\&quot;fill_or_kill\&quot;&#x60; - execute a transaction immediately and completely or not at all&lt;/li&gt; &lt;li&gt;&#x60;\&quot;immediate_or_cancel\&quot;&#x60; - execute a transaction immediately, and any portion of the order that cannot be immediately filled is cancelled&lt;/li&gt; &lt;/ul&gt; (default to 'good_til_cancelled')
    # @option opts [Float] :max_show Maximum amount within an order to be shown to other customers, &#x60;0&#x60; for invisible order (default to 1)
    # @option opts [Boolean] :post_only &lt;p&gt;If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.&lt;/p&gt; &lt;p&gt;Only valid in combination with time_in_force&#x3D;&#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; (default to true)
    # @option opts [Boolean] :reduce_only If &#x60;true&#x60;, the order is considered reduce-only which is intended to only reduce a current position (default to false)
    # @option opts [Float] :stop_price Stop price, required for stop limit orders (Only for stop orders)
    # @option opts [String] :trigger Defines trigger type, required for &#x60;\&quot;stop_limit\&quot;&#x60; order type
    # @option opts [String] :advanced Advanced option order type. (Only for options)
    # @return [Object]
    def private_sell_get(instrument_name, amount, opts = {})
      data, _status_code, _headers = private_sell_get_with_http_info(instrument_name, amount, opts)
      data
    end

    # Places a sell order for an instrument.
    # @param instrument_name [String] Instrument name
    # @param amount [Float] It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH
    # @param [Hash] opts the optional parameters
    # @option opts [String] :type The order type, default: &#x60;\&quot;limit\&quot;&#x60;
    # @option opts [String] :label user defined label for the order (maximum 32 characters)
    # @option opts [Float] :price &lt;p&gt;The order price in base currency (Only for limit and stop_limit orders)&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;usd, the field price should be the option price value in USD.&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;implv, the field price should be a value of implied volatility in percentages. For example,  price&#x3D;100, means implied volatility of 100%&lt;/p&gt;
    # @option opts [String] :time_in_force &lt;p&gt;Specifies how long the order remains in effect. Default &#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; &lt;ul&gt; &lt;li&gt;&#x60;\&quot;good_til_cancelled\&quot;&#x60; - unfilled order remains in order book until cancelled&lt;/li&gt; &lt;li&gt;&#x60;\&quot;fill_or_kill\&quot;&#x60; - execute a transaction immediately and completely or not at all&lt;/li&gt; &lt;li&gt;&#x60;\&quot;immediate_or_cancel\&quot;&#x60; - execute a transaction immediately, and any portion of the order that cannot be immediately filled is cancelled&lt;/li&gt; &lt;/ul&gt;
    # @option opts [Float] :max_show Maximum amount within an order to be shown to other customers, &#x60;0&#x60; for invisible order
    # @option opts [Boolean] :post_only &lt;p&gt;If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.&lt;/p&gt; &lt;p&gt;Only valid in combination with time_in_force&#x3D;&#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt;
    # @option opts [Boolean] :reduce_only If &#x60;true&#x60;, the order is considered reduce-only which is intended to only reduce a current position
    # @option opts [Float] :stop_price Stop price, required for stop limit orders (Only for stop orders)
    # @option opts [String] :trigger Defines trigger type, required for &#x60;\&quot;stop_limit\&quot;&#x60; order type
    # @option opts [String] :advanced Advanced option order type. (Only for options)
    # @return [Array<(Object, Integer, Hash)>] Object data, response status code and response headers
    def private_sell_get_with_http_info(instrument_name, amount, opts = {})
      if @api_client.config.debugging
        @api_client.config.logger.debug 'Calling API: PrivateApi.private_sell_get ...'
      end
      # verify the required parameter 'instrument_name' is set
      if @api_client.config.client_side_validation && instrument_name.nil?
        fail ArgumentError, "Missing the required parameter 'instrument_name' when calling PrivateApi.private_sell_get"
      end
      # verify the required parameter 'amount' is set
      if @api_client.config.client_side_validation && amount.nil?
        fail ArgumentError, "Missing the required parameter 'amount' when calling PrivateApi.private_sell_get"
      end
      allowable_values = ["limit", "stop_limit", "market", "stop_market"]
      if @api_client.config.client_side_validation && opts[:'type'] && !allowable_values.include?(opts[:'type'])
        fail ArgumentError, "invalid value for \"type\", must be one of #{allowable_values}"
      end
      allowable_values = ["good_til_cancelled", "fill_or_kill", "immediate_or_cancel"]
      if @api_client.config.client_side_validation && opts[:'time_in_force'] && !allowable_values.include?(opts[:'time_in_force'])
        fail ArgumentError, "invalid value for \"time_in_force\", must be one of #{allowable_values}"
      end
      allowable_values = ["index_price", "mark_price", "last_price"]
      if @api_client.config.client_side_validation && opts[:'trigger'] && !allowable_values.include?(opts[:'trigger'])
        fail ArgumentError, "invalid value for \"trigger\", must be one of #{allowable_values}"
      end
      allowable_values = ["usd", "implv"]
      if @api_client.config.client_side_validation && opts[:'advanced'] && !allowable_values.include?(opts[:'advanced'])
        fail ArgumentError, "invalid value for \"advanced\", must be one of #{allowable_values}"
      end
      # resource path
      local_var_path = '/private/sell'

      # query parameters
      query_params = opts[:query_params] || {}
      query_params[:'instrument_name'] = instrument_name
      query_params[:'amount'] = amount
      query_params[:'type'] = opts[:'type'] if !opts[:'type'].nil?
      query_params[:'label'] = opts[:'label'] if !opts[:'label'].nil?
      query_params[:'price'] = opts[:'price'] if !opts[:'price'].nil?
      query_params[:'time_in_force'] = opts[:'time_in_force'] if !opts[:'time_in_force'].nil?
      query_params[:'max_show'] = opts[:'max_show'] if !opts[:'max_show'].nil?
      query_params[:'post_only'] = opts[:'post_only'] if !opts[:'post_only'].nil?
      query_params[:'reduce_only'] = opts[:'reduce_only'] if !opts[:'reduce_only'].nil?
      query_params[:'stop_price'] = opts[:'stop_price'] if !opts[:'stop_price'].nil?
      query_params[:'trigger'] = opts[:'trigger'] if !opts[:'trigger'].nil?
      query_params[:'advanced'] = opts[:'advanced'] if !opts[:'advanced'].nil?

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
        @api_client.config.logger.debug "API called: PrivateApi#private_sell_get\nData: #{data.inspect}\nStatus code: #{status_code}\nHeaders: #{headers}"
      end
      return data, status_code, headers
    end

    # Marks an announcement as read, so it will not be shown in `get_new_announcements`.
    # @param announcement_id [Float] the ID of the announcement
    # @param [Hash] opts the optional parameters
    # @return [Object]
    def private_set_announcement_as_read_get(announcement_id, opts = {})
      data, _status_code, _headers = private_set_announcement_as_read_get_with_http_info(announcement_id, opts)
      data
    end

    # Marks an announcement as read, so it will not be shown in &#x60;get_new_announcements&#x60;.
    # @param announcement_id [Float] the ID of the announcement
    # @param [Hash] opts the optional parameters
    # @return [Array<(Object, Integer, Hash)>] Object data, response status code and response headers
    def private_set_announcement_as_read_get_with_http_info(announcement_id, opts = {})
      if @api_client.config.debugging
        @api_client.config.logger.debug 'Calling API: PrivateApi.private_set_announcement_as_read_get ...'
      end
      # verify the required parameter 'announcement_id' is set
      if @api_client.config.client_side_validation && announcement_id.nil?
        fail ArgumentError, "Missing the required parameter 'announcement_id' when calling PrivateApi.private_set_announcement_as_read_get"
      end
      # resource path
      local_var_path = '/private/set_announcement_as_read'

      # query parameters
      query_params = opts[:query_params] || {}
      query_params[:'announcement_id'] = announcement_id

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
        @api_client.config.logger.debug "API called: PrivateApi#private_set_announcement_as_read_get\nData: #{data.inspect}\nStatus code: #{status_code}\nHeaders: #{headers}"
      end
      return data, status_code, headers
    end

    # Assign an email address to a subaccount. User will receive an email with confirmation link.
    # @param sid [Integer] The user id for the subaccount
    # @param email [String] The email address for the subaccount
    # @param [Hash] opts the optional parameters
    # @return [Object]
    def private_set_email_for_subaccount_get(sid, email, opts = {})
      data, _status_code, _headers = private_set_email_for_subaccount_get_with_http_info(sid, email, opts)
      data
    end

    # Assign an email address to a subaccount. User will receive an email with confirmation link.
    # @param sid [Integer] The user id for the subaccount
    # @param email [String] The email address for the subaccount
    # @param [Hash] opts the optional parameters
    # @return [Array<(Object, Integer, Hash)>] Object data, response status code and response headers
    def private_set_email_for_subaccount_get_with_http_info(sid, email, opts = {})
      if @api_client.config.debugging
        @api_client.config.logger.debug 'Calling API: PrivateApi.private_set_email_for_subaccount_get ...'
      end
      # verify the required parameter 'sid' is set
      if @api_client.config.client_side_validation && sid.nil?
        fail ArgumentError, "Missing the required parameter 'sid' when calling PrivateApi.private_set_email_for_subaccount_get"
      end
      # verify the required parameter 'email' is set
      if @api_client.config.client_side_validation && email.nil?
        fail ArgumentError, "Missing the required parameter 'email' when calling PrivateApi.private_set_email_for_subaccount_get"
      end
      # resource path
      local_var_path = '/private/set_email_for_subaccount'

      # query parameters
      query_params = opts[:query_params] || {}
      query_params[:'sid'] = sid
      query_params[:'email'] = email

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
        @api_client.config.logger.debug "API called: PrivateApi#private_set_email_for_subaccount_get\nData: #{data.inspect}\nStatus code: #{status_code}\nHeaders: #{headers}"
      end
      return data, status_code, headers
    end

    # Changes the language to be used for emails.
    # @param language [String] The abbreviated language name. Valid values include &#x60;\&quot;en\&quot;&#x60;, &#x60;\&quot;ko\&quot;&#x60;, &#x60;\&quot;zh\&quot;&#x60;
    # @param [Hash] opts the optional parameters
    # @return [Object]
    def private_set_email_language_get(language, opts = {})
      data, _status_code, _headers = private_set_email_language_get_with_http_info(language, opts)
      data
    end

    # Changes the language to be used for emails.
    # @param language [String] The abbreviated language name. Valid values include &#x60;\&quot;en\&quot;&#x60;, &#x60;\&quot;ko\&quot;&#x60;, &#x60;\&quot;zh\&quot;&#x60;
    # @param [Hash] opts the optional parameters
    # @return [Array<(Object, Integer, Hash)>] Object data, response status code and response headers
    def private_set_email_language_get_with_http_info(language, opts = {})
      if @api_client.config.debugging
        @api_client.config.logger.debug 'Calling API: PrivateApi.private_set_email_language_get ...'
      end
      # verify the required parameter 'language' is set
      if @api_client.config.client_side_validation && language.nil?
        fail ArgumentError, "Missing the required parameter 'language' when calling PrivateApi.private_set_email_language_get"
      end
      # resource path
      local_var_path = '/private/set_email_language'

      # query parameters
      query_params = opts[:query_params] || {}
      query_params[:'language'] = language

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
        @api_client.config.logger.debug "API called: PrivateApi#private_set_email_language_get\nData: #{data.inspect}\nStatus code: #{status_code}\nHeaders: #{headers}"
      end
      return data, status_code, headers
    end

    # Set the password for the subaccount
    # @param sid [Integer] The user id for the subaccount
    # @param password [String] The password for the subaccount
    # @param [Hash] opts the optional parameters
    # @return [Object]
    def private_set_password_for_subaccount_get(sid, password, opts = {})
      data, _status_code, _headers = private_set_password_for_subaccount_get_with_http_info(sid, password, opts)
      data
    end

    # Set the password for the subaccount
    # @param sid [Integer] The user id for the subaccount
    # @param password [String] The password for the subaccount
    # @param [Hash] opts the optional parameters
    # @return [Array<(Object, Integer, Hash)>] Object data, response status code and response headers
    def private_set_password_for_subaccount_get_with_http_info(sid, password, opts = {})
      if @api_client.config.debugging
        @api_client.config.logger.debug 'Calling API: PrivateApi.private_set_password_for_subaccount_get ...'
      end
      # verify the required parameter 'sid' is set
      if @api_client.config.client_side_validation && sid.nil?
        fail ArgumentError, "Missing the required parameter 'sid' when calling PrivateApi.private_set_password_for_subaccount_get"
      end
      # verify the required parameter 'password' is set
      if @api_client.config.client_side_validation && password.nil?
        fail ArgumentError, "Missing the required parameter 'password' when calling PrivateApi.private_set_password_for_subaccount_get"
      end
      # resource path
      local_var_path = '/private/set_password_for_subaccount'

      # query parameters
      query_params = opts[:query_params] || {}
      query_params[:'sid'] = sid
      query_params[:'password'] = password

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
        @api_client.config.logger.debug "API called: PrivateApi#private_set_password_for_subaccount_get\nData: #{data.inspect}\nStatus code: #{status_code}\nHeaders: #{headers}"
      end
      return data, status_code, headers
    end

    # Transfer funds to a subaccount.
    # @param currency [String] The currency symbol
    # @param amount [Float] Amount of funds to be transferred
    # @param destination [Integer] Id of destination subaccount
    # @param [Hash] opts the optional parameters
    # @return [Object]
    def private_submit_transfer_to_subaccount_get(currency, amount, destination, opts = {})
      data, _status_code, _headers = private_submit_transfer_to_subaccount_get_with_http_info(currency, amount, destination, opts)
      data
    end

    # Transfer funds to a subaccount.
    # @param currency [String] The currency symbol
    # @param amount [Float] Amount of funds to be transferred
    # @param destination [Integer] Id of destination subaccount
    # @param [Hash] opts the optional parameters
    # @return [Array<(Object, Integer, Hash)>] Object data, response status code and response headers
    def private_submit_transfer_to_subaccount_get_with_http_info(currency, amount, destination, opts = {})
      if @api_client.config.debugging
        @api_client.config.logger.debug 'Calling API: PrivateApi.private_submit_transfer_to_subaccount_get ...'
      end
      # verify the required parameter 'currency' is set
      if @api_client.config.client_side_validation && currency.nil?
        fail ArgumentError, "Missing the required parameter 'currency' when calling PrivateApi.private_submit_transfer_to_subaccount_get"
      end
      # verify enum value
      allowable_values = ["BTC", "ETH"]
      if @api_client.config.client_side_validation && !allowable_values.include?(currency)
        fail ArgumentError, "invalid value for \"currency\", must be one of #{allowable_values}"
      end
      # verify the required parameter 'amount' is set
      if @api_client.config.client_side_validation && amount.nil?
        fail ArgumentError, "Missing the required parameter 'amount' when calling PrivateApi.private_submit_transfer_to_subaccount_get"
      end
      # verify the required parameter 'destination' is set
      if @api_client.config.client_side_validation && destination.nil?
        fail ArgumentError, "Missing the required parameter 'destination' when calling PrivateApi.private_submit_transfer_to_subaccount_get"
      end
      # resource path
      local_var_path = '/private/submit_transfer_to_subaccount'

      # query parameters
      query_params = opts[:query_params] || {}
      query_params[:'currency'] = currency
      query_params[:'amount'] = amount
      query_params[:'destination'] = destination

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
        @api_client.config.logger.debug "API called: PrivateApi#private_submit_transfer_to_subaccount_get\nData: #{data.inspect}\nStatus code: #{status_code}\nHeaders: #{headers}"
      end
      return data, status_code, headers
    end

    # Transfer funds to a another user.
    # @param currency [String] The currency symbol
    # @param amount [Float] Amount of funds to be transferred
    # @param destination [String] Destination address from address book
    # @param [Hash] opts the optional parameters
    # @option opts [String] :tfa TFA code, required when TFA is enabled for current account
    # @return [Object]
    def private_submit_transfer_to_user_get(currency, amount, destination, opts = {})
      data, _status_code, _headers = private_submit_transfer_to_user_get_with_http_info(currency, amount, destination, opts)
      data
    end

    # Transfer funds to a another user.
    # @param currency [String] The currency symbol
    # @param amount [Float] Amount of funds to be transferred
    # @param destination [String] Destination address from address book
    # @param [Hash] opts the optional parameters
    # @option opts [String] :tfa TFA code, required when TFA is enabled for current account
    # @return [Array<(Object, Integer, Hash)>] Object data, response status code and response headers
    def private_submit_transfer_to_user_get_with_http_info(currency, amount, destination, opts = {})
      if @api_client.config.debugging
        @api_client.config.logger.debug 'Calling API: PrivateApi.private_submit_transfer_to_user_get ...'
      end
      # verify the required parameter 'currency' is set
      if @api_client.config.client_side_validation && currency.nil?
        fail ArgumentError, "Missing the required parameter 'currency' when calling PrivateApi.private_submit_transfer_to_user_get"
      end
      # verify enum value
      allowable_values = ["BTC", "ETH"]
      if @api_client.config.client_side_validation && !allowable_values.include?(currency)
        fail ArgumentError, "invalid value for \"currency\", must be one of #{allowable_values}"
      end
      # verify the required parameter 'amount' is set
      if @api_client.config.client_side_validation && amount.nil?
        fail ArgumentError, "Missing the required parameter 'amount' when calling PrivateApi.private_submit_transfer_to_user_get"
      end
      # verify the required parameter 'destination' is set
      if @api_client.config.client_side_validation && destination.nil?
        fail ArgumentError, "Missing the required parameter 'destination' when calling PrivateApi.private_submit_transfer_to_user_get"
      end
      # resource path
      local_var_path = '/private/submit_transfer_to_user'

      # query parameters
      query_params = opts[:query_params] || {}
      query_params[:'currency'] = currency
      query_params[:'amount'] = amount
      query_params[:'destination'] = destination
      query_params[:'tfa'] = opts[:'tfa'] if !opts[:'tfa'].nil?

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
        @api_client.config.logger.debug "API called: PrivateApi#private_submit_transfer_to_user_get\nData: #{data.inspect}\nStatus code: #{status_code}\nHeaders: #{headers}"
      end
      return data, status_code, headers
    end

    # Enable or disable deposit address creation
    # @param currency [String] The currency symbol
    # @param state [Boolean] 
    # @param [Hash] opts the optional parameters
    # @return [Object]
    def private_toggle_deposit_address_creation_get(currency, state, opts = {})
      data, _status_code, _headers = private_toggle_deposit_address_creation_get_with_http_info(currency, state, opts)
      data
    end

    # Enable or disable deposit address creation
    # @param currency [String] The currency symbol
    # @param state [Boolean] 
    # @param [Hash] opts the optional parameters
    # @return [Array<(Object, Integer, Hash)>] Object data, response status code and response headers
    def private_toggle_deposit_address_creation_get_with_http_info(currency, state, opts = {})
      if @api_client.config.debugging
        @api_client.config.logger.debug 'Calling API: PrivateApi.private_toggle_deposit_address_creation_get ...'
      end
      # verify the required parameter 'currency' is set
      if @api_client.config.client_side_validation && currency.nil?
        fail ArgumentError, "Missing the required parameter 'currency' when calling PrivateApi.private_toggle_deposit_address_creation_get"
      end
      # verify enum value
      allowable_values = ["BTC", "ETH"]
      if @api_client.config.client_side_validation && !allowable_values.include?(currency)
        fail ArgumentError, "invalid value for \"currency\", must be one of #{allowable_values}"
      end
      # verify the required parameter 'state' is set
      if @api_client.config.client_side_validation && state.nil?
        fail ArgumentError, "Missing the required parameter 'state' when calling PrivateApi.private_toggle_deposit_address_creation_get"
      end
      # resource path
      local_var_path = '/private/toggle_deposit_address_creation'

      # query parameters
      query_params = opts[:query_params] || {}
      query_params[:'currency'] = currency
      query_params[:'state'] = state

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
        @api_client.config.logger.debug "API called: PrivateApi#private_toggle_deposit_address_creation_get\nData: #{data.inspect}\nStatus code: #{status_code}\nHeaders: #{headers}"
      end
      return data, status_code, headers
    end

    # Enable or disable sending of notifications for the subaccount.
    # @param sid [Integer] The user id for the subaccount
    # @param state [Boolean] enable (&#x60;true&#x60;) or disable (&#x60;false&#x60;) notifications
    # @param [Hash] opts the optional parameters
    # @return [Object]
    def private_toggle_notifications_from_subaccount_get(sid, state, opts = {})
      data, _status_code, _headers = private_toggle_notifications_from_subaccount_get_with_http_info(sid, state, opts)
      data
    end

    # Enable or disable sending of notifications for the subaccount.
    # @param sid [Integer] The user id for the subaccount
    # @param state [Boolean] enable (&#x60;true&#x60;) or disable (&#x60;false&#x60;) notifications
    # @param [Hash] opts the optional parameters
    # @return [Array<(Object, Integer, Hash)>] Object data, response status code and response headers
    def private_toggle_notifications_from_subaccount_get_with_http_info(sid, state, opts = {})
      if @api_client.config.debugging
        @api_client.config.logger.debug 'Calling API: PrivateApi.private_toggle_notifications_from_subaccount_get ...'
      end
      # verify the required parameter 'sid' is set
      if @api_client.config.client_side_validation && sid.nil?
        fail ArgumentError, "Missing the required parameter 'sid' when calling PrivateApi.private_toggle_notifications_from_subaccount_get"
      end
      # verify the required parameter 'state' is set
      if @api_client.config.client_side_validation && state.nil?
        fail ArgumentError, "Missing the required parameter 'state' when calling PrivateApi.private_toggle_notifications_from_subaccount_get"
      end
      # resource path
      local_var_path = '/private/toggle_notifications_from_subaccount'

      # query parameters
      query_params = opts[:query_params] || {}
      query_params[:'sid'] = sid
      query_params[:'state'] = state

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
        @api_client.config.logger.debug "API called: PrivateApi#private_toggle_notifications_from_subaccount_get\nData: #{data.inspect}\nStatus code: #{status_code}\nHeaders: #{headers}"
      end
      return data, status_code, headers
    end

    # Enable or disable login for a subaccount. If login is disabled and a session for the subaccount exists, this session will be terminated.
    # @param sid [Integer] The user id for the subaccount
    # @param state [String] enable or disable login.
    # @param [Hash] opts the optional parameters
    # @return [Object]
    def private_toggle_subaccount_login_get(sid, state, opts = {})
      data, _status_code, _headers = private_toggle_subaccount_login_get_with_http_info(sid, state, opts)
      data
    end

    # Enable or disable login for a subaccount. If login is disabled and a session for the subaccount exists, this session will be terminated.
    # @param sid [Integer] The user id for the subaccount
    # @param state [String] enable or disable login.
    # @param [Hash] opts the optional parameters
    # @return [Array<(Object, Integer, Hash)>] Object data, response status code and response headers
    def private_toggle_subaccount_login_get_with_http_info(sid, state, opts = {})
      if @api_client.config.debugging
        @api_client.config.logger.debug 'Calling API: PrivateApi.private_toggle_subaccount_login_get ...'
      end
      # verify the required parameter 'sid' is set
      if @api_client.config.client_side_validation && sid.nil?
        fail ArgumentError, "Missing the required parameter 'sid' when calling PrivateApi.private_toggle_subaccount_login_get"
      end
      # verify the required parameter 'state' is set
      if @api_client.config.client_side_validation && state.nil?
        fail ArgumentError, "Missing the required parameter 'state' when calling PrivateApi.private_toggle_subaccount_login_get"
      end
      # verify enum value
      allowable_values = ["enable", "disable"]
      if @api_client.config.client_side_validation && !allowable_values.include?(state)
        fail ArgumentError, "invalid value for \"state\", must be one of #{allowable_values}"
      end
      # resource path
      local_var_path = '/private/toggle_subaccount_login'

      # query parameters
      query_params = opts[:query_params] || {}
      query_params[:'sid'] = sid
      query_params[:'state'] = state

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
        @api_client.config.logger.debug "API called: PrivateApi#private_toggle_subaccount_login_get\nData: #{data.inspect}\nStatus code: #{status_code}\nHeaders: #{headers}"
      end
      return data, status_code, headers
    end

    # Creates a new withdrawal request
    # @param currency [String] The currency symbol
    # @param address [String] Address in currency format, it must be in address book
    # @param amount [Float] Amount of funds to be withdrawn
    # @param [Hash] opts the optional parameters
    # @option opts [String] :priority Withdrawal priority, optional for BTC, default: &#x60;high&#x60;
    # @option opts [String] :tfa TFA code, required when TFA is enabled for current account
    # @return [Object]
    def private_withdraw_get(currency, address, amount, opts = {})
      data, _status_code, _headers = private_withdraw_get_with_http_info(currency, address, amount, opts)
      data
    end

    # Creates a new withdrawal request
    # @param currency [String] The currency symbol
    # @param address [String] Address in currency format, it must be in address book
    # @param amount [Float] Amount of funds to be withdrawn
    # @param [Hash] opts the optional parameters
    # @option opts [String] :priority Withdrawal priority, optional for BTC, default: &#x60;high&#x60;
    # @option opts [String] :tfa TFA code, required when TFA is enabled for current account
    # @return [Array<(Object, Integer, Hash)>] Object data, response status code and response headers
    def private_withdraw_get_with_http_info(currency, address, amount, opts = {})
      if @api_client.config.debugging
        @api_client.config.logger.debug 'Calling API: PrivateApi.private_withdraw_get ...'
      end
      # verify the required parameter 'currency' is set
      if @api_client.config.client_side_validation && currency.nil?
        fail ArgumentError, "Missing the required parameter 'currency' when calling PrivateApi.private_withdraw_get"
      end
      # verify enum value
      allowable_values = ["BTC", "ETH"]
      if @api_client.config.client_side_validation && !allowable_values.include?(currency)
        fail ArgumentError, "invalid value for \"currency\", must be one of #{allowable_values}"
      end
      # verify the required parameter 'address' is set
      if @api_client.config.client_side_validation && address.nil?
        fail ArgumentError, "Missing the required parameter 'address' when calling PrivateApi.private_withdraw_get"
      end
      # verify the required parameter 'amount' is set
      if @api_client.config.client_side_validation && amount.nil?
        fail ArgumentError, "Missing the required parameter 'amount' when calling PrivateApi.private_withdraw_get"
      end
      allowable_values = ["insane", "extreme_high", "very_high", "high", "mid", "low", "very_low"]
      if @api_client.config.client_side_validation && opts[:'priority'] && !allowable_values.include?(opts[:'priority'])
        fail ArgumentError, "invalid value for \"priority\", must be one of #{allowable_values}"
      end
      # resource path
      local_var_path = '/private/withdraw'

      # query parameters
      query_params = opts[:query_params] || {}
      query_params[:'currency'] = currency
      query_params[:'address'] = address
      query_params[:'amount'] = amount
      query_params[:'priority'] = opts[:'priority'] if !opts[:'priority'].nil?
      query_params[:'tfa'] = opts[:'tfa'] if !opts[:'tfa'].nil?

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
        @api_client.config.logger.debug "API called: PrivateApi#private_withdraw_get\nData: #{data.inspect}\nStatus code: #{status_code}\nHeaders: #{headers}"
      end
      return data, status_code, headers
    end
  end
end
