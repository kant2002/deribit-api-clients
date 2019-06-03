=begin
#Deribit API

##Overview  Deribit provides three different interfaces to access the API:  * [JSON-RPC over Websocket](#json-rpc) * [JSON-RPC over HTTP](#json-rpc) * [FIX](#fix-api) (Financial Information eXchange)  With the API Console you can use and test the JSON-RPC API, both via HTTP and  via Websocket. To visit the API console, go to __Account > API tab >  API Console tab.__   ##Naming Deribit tradeable assets or instruments use the following system of naming:  |Kind|Examples|Template|Comments| |----|--------|--------|--------| |Future|<code>BTC-25MAR16</code>, <code>BTC-5AUG16</code>|<code>BTC-DMMMYY</code>|<code>BTC</code> is currency, <code>DMMMYY</code> is expiration date, <code>D</code> stands for day of month (1 or 2 digits), <code>MMM</code> - month (3 first letters in English), <code>YY</code> stands for year.| |Perpetual|<code>BTC-PERPETUAL</code>                        ||Perpetual contract for currency <code>BTC</code>.| |Option|<code>BTC-25MAR16-420-C</code>, <code>BTC-5AUG16-580-P</code>|<code>BTC-DMMMYY-STRIKE-K</code>|<code>STRIKE</code> is option strike price in USD. Template <code>K</code> is option kind: <code>C</code> for call options or <code>P</code> for put options.|   # JSON-RPC JSON-RPC is a light-weight remote procedure call (RPC) protocol. The  [JSON-RPC specification](https://www.jsonrpc.org/specification) defines the data structures that are used for the messages that are exchanged between client and server, as well as the rules around their processing. JSON-RPC uses JSON (RFC 4627) as data format.  JSON-RPC is transport agnostic: it does not specify which transport mechanism must be used. The Deribit API supports both Websocket (preferred) and HTTP (with limitations: subscriptions are not supported over HTTP).  ## Request messages > An example of a request message:  ```json {     \"jsonrpc\": \"2.0\",     \"id\": 8066,     \"method\": \"public/ticker\",     \"params\": {         \"instrument\": \"BTC-24AUG18-6500-P\"     } } ```  According to the JSON-RPC sepcification the requests must be JSON objects with the following fields.  |Name|Type|Description| |----|----|-----------| |jsonrpc|string|The version of the JSON-RPC spec: \"2.0\"| |id|integer or string|An identifier of the request. If it is included, then the response will contain the same identifier| |method|string|The method to be invoked| |params|object|The parameters values for the method. The field names must match with the expected parameter names. The parameters that are expected are described in the documentation for the methods, below.|  <aside class=\"warning\"> The JSON-RPC specification describes two features that are currently not supported by the API:  <ul> <li>Specification of parameter values by position</li> <li>Batch requests</li> </ul>  </aside>   ## Response messages > An example of a response message:  ```json {     \"jsonrpc\": \"2.0\",     \"id\": 5239,     \"testnet\": false,     \"result\": [         {             \"currency\": \"BTC\",             \"currencyLong\": \"Bitcoin\",             \"minConfirmation\": 2,             \"txFee\": 0.0006,             \"isActive\": true,             \"coinType\": \"BITCOIN\",             \"baseAddress\": null         }     ],     \"usIn\": 1535043730126248,     \"usOut\": 1535043730126250,     \"usDiff\": 2 } ```  The JSON-RPC API always responds with a JSON object with the following fields.   |Name|Type|Description| |----|----|-----------| |id|integer|This is the same id that was sent in the request.| |result|any|If successful, the result of the API call. The format for the result is described with each method.| |error|error object|Only present if there was an error invoking the method. The error object is described below.| |testnet|boolean|Indicates whether the API in use is actually the test API.  <code>false</code> for production server, <code>true</code> for test server.| |usIn|integer|The timestamp when the requests was received (microseconds since the Unix epoch)| |usOut|integer|The timestamp when the response was sent (microseconds since the Unix epoch)| |usDiff|integer|The number of microseconds that was spent handling the request|  <aside class=\"notice\"> The fields <code>testnet</code>, <code>usIn</code>, <code>usOut</code> and <code>usDiff</code> are not part of the JSON-RPC standard.  <p>In order not to clutter the examples they will generally be omitted from the example code.</p> </aside>  > An example of a response with an error:  ```json {     \"jsonrpc\": \"2.0\",     \"id\": 8163,     \"error\": {         \"code\": 11050,         \"message\": \"bad_request\"     },     \"testnet\": false,     \"usIn\": 1535037392434763,     \"usOut\": 1535037392448119,     \"usDiff\": 13356 } ``` In case of an error the response message will contain the error field, with as value an object with the following with the following fields:  |Name|Type|Description |----|----|-----------| |code|integer|A number that indicates the kind of error.| |message|string|A short description that indicates the kind of error.| |data|any|Additional data about the error. This field may be omitted.|  ## Notifications  > An example of a notification:  ```json {     \"jsonrpc\": \"2.0\",     \"method\": \"subscription\",     \"params\": {         \"channel\": \"deribit_price_index.btc_usd\",         \"data\": {             \"timestamp\": 1535098298227,             \"price\": 6521.17,             \"index_name\": \"btc_usd\"         }     } } ```  API users can subscribe to certain types of notifications. This means that they will receive JSON-RPC notification-messages from the server when certain events occur, such as changes to the index price or changes to the order book for a certain instrument.   The API methods [public/subscribe](#public-subscribe) and [private/subscribe](#private-subscribe) are used to set up a subscription. Since HTTP does not support the sending of messages from server to client, these methods are only availble when using the Websocket transport mechanism.  At the moment of subscription a \"channel\" must be specified. The channel determines the type of events that will be received.  See [Subscriptions](#subscriptions) for more details about the channels.  In accordance with the JSON-RPC specification, the format of a notification  is that of a request message without an <code>id</code> field. The value of the <code>method</code> field will always be <code>\"subscription\"</code>. The <code>params</code> field will always be an object with 2 members: <code>channel</code> and <code>data</code>. The value of the <code>channel</code> member is the name of the channel (a string). The value of the <code>data</code> member is an object that contains data  that is specific for the channel.   ## Authentication  > An example of a JSON request with token:  ```json {     \"id\": 5647,     \"method\": \"private/get_subaccounts\",     \"params\": {         \"access_token\": \"67SVutDoVZSzkUStHSuk51WntMNBJ5mh5DYZhwzpiqDF\"     } } ```  The API consists of `public` and `private` methods. The public methods do not require authentication. The private methods use OAuth 2.0 authentication. This means that a valid OAuth access token must be included in the request, which can get achived by calling method [public/auth](#public-auth).  When the token was assigned to the user, it should be passed along, with other request parameters, back to the server:  |Connection type|Access token placement |----|-----------| |**Websocket**|Inside request JSON parameters, as an `access_token` field| |**HTTP (REST)**|Header `Authorization: bearer ```Token``` ` value|  ### Additional authorization method - basic user credentials  <span style=\"color:red\"><b> ! Not recommended - however, it could be useful for quick testing API</b></span></br>  Every `private` method could be accessed by providing, inside HTTP `Authorization: Basic XXX` header, values with user `ClientId` and assigned `ClientSecret` (both values can be found on the API page on the Deribit website) encoded with `Base64`:  <code>Authorization: Basic BASE64(`ClientId` + `:` + `ClientSecret`)</code>   ### Additional authorization method - Deribit signature credentials  The Derbit service provides dedicated authorization method, which harness user generated signature to increase security level for passing request data. Generated value is passed inside `Authorization` header, coded as:  <code>Authorization: deri-hmac-sha256 id=```ClientId```,ts=```Timestamp```,sig=```Signature```,nonce=```Nonce```</code>  where:  |Deribit credential|Description |----|-----------| |*ClientId*|Can be found on the API page on the Deribit website| |*Timestamp*|Time when the request was generated - given as **miliseconds**. It's valid for **60 seconds** since generation, after that time any request with an old timestamp will be rejected.| |*Signature*|Value for signature calculated as described below | |*Nonce*|Single usage, user generated initialization vector for the server token|  The signature is generated by the following formula:  <code> Signature = HEX_STRING( HMAC-SHA256( ClientSecret, StringToSign ) );</code></br>  <code> StringToSign =  Timestamp + \"\\n\" + Nonce + \"\\n\" + RequestData;</code></br>  <code> RequestData =  UPPERCASE(HTTP_METHOD())  + \"\\n\" + URI() + \"\\n\" + RequestBody + \"\\n\";</code></br>   e.g. (using shell with ```openssl``` tool):  <code>&nbsp;&nbsp;&nbsp;&nbsp;ClientId=AAAAAAAAAAA</code></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;ClientSecret=ABCD</code></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;Timestamp=$( date +%s000 )</code></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;Nonce=$( cat /dev/urandom | tr -dc 'a-z0-9' | head -c8 )</code></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;URI=\"/api/v2/private/get_account_summary?currency=BTC\"</code></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;HttpMethod=GET</code></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;Body=\"\"</code></br></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;Signature=$( echo -ne \"${Timestamp}\\n${Nonce}\\n${HttpMethod}\\n${URI}\\n${Body}\\n\" | openssl sha256 -r -hmac \"$ClientSecret\" | cut -f1 -d' ' )</code></br></br> <code>&nbsp;&nbsp;&nbsp;&nbsp;echo $Signature</code></br></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;shell output> ea40d5e5e4fae235ab22b61da98121fbf4acdc06db03d632e23c66bcccb90d2c  (**WARNING**: Exact value depends on current timestamp and client credentials</code></br></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;curl -s -X ${HttpMethod} -H \"Authorization: deri-hmac-sha256 id=${ClientId},ts=${Timestamp},nonce=${Nonce},sig=${Signature}\" \"https://www.deribit.com${URI}\"</code></br></br>    ### Additional authorization method - signature credentials (WebSocket API)  When connecting through Websocket, user can request for authorization using ```client_credential``` method, which requires providing following parameters (as a part of JSON request):  |JSON parameter|Description |----|-----------| |*grant_type*|Must be **client_signature**| |*client_id*|Can be found on the API page on the Deribit website| |*timestamp*|Time when the request was generated - given as **miliseconds**. It's valid for **60 seconds** since generation, after that time any request with an old timestamp will be rejected.| |*signature*|Value for signature calculated as described below | |*nonce*|Single usage, user generated initialization vector for the server token| |*data*|**Optional** field, which contains any user specific value|  The signature is generated by the following formula:  <code> StringToSign =  Timestamp + \"\\n\" + Nonce + \"\\n\" + Data;</code></br>  <code> Signature = HEX_STRING( HMAC-SHA256( ClientSecret, StringToSign ) );</code></br>   e.g. (using shell with ```openssl``` tool):  <code>&nbsp;&nbsp;&nbsp;&nbsp;ClientId=AAAAAAAAAAA</code></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;ClientSecret=ABCD</code></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;Timestamp=$( date +%s000 ) # e.g. 1554883365000 </code></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;Nonce=$( cat /dev/urandom | tr -dc 'a-z0-9' | head -c8 ) # e.g. fdbmmz79 </code></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;Data=\"\"</code></br></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;Signature=$( echo -ne \"${Timestamp}\\n${Nonce}\\n${Data}\\n\" | openssl sha256 -r -hmac \"$ClientSecret\" | cut -f1 -d' ' )</code></br></br> <code>&nbsp;&nbsp;&nbsp;&nbsp;echo $Signature</code></br></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;shell output> e20c9cd5639d41f8bbc88f4d699c4baf94a4f0ee320e9a116b72743c449eb994  (**WARNING**: Exact value depends on current timestamp and client credentials</code></br></br>   You can also check the signature value using some online tools like, e.g: [https://codebeautify.org/hmac-generator](https://codebeautify.org/hmac-generator) (but don't forget about adding *newline* after each part of the hashed text and remember that you **should use** it only with your **test credentials**).   Here's a sample JSON request created using the values from the example above:  <code> {                            </br> &nbsp;&nbsp;\"jsonrpc\" : \"2.0\",         </br> &nbsp;&nbsp;\"id\" : 9929,               </br> &nbsp;&nbsp;\"method\" : \"public/auth\",  </br> &nbsp;&nbsp;\"params\" :                 </br> &nbsp;&nbsp;{                        </br> &nbsp;&nbsp;&nbsp;&nbsp;\"grant_type\" : \"client_signature\",   </br> &nbsp;&nbsp;&nbsp;&nbsp;\"client_id\" : \"AAAAAAAAAAA\",         </br> &nbsp;&nbsp;&nbsp;&nbsp;\"timestamp\": \"1554883365000\",        </br> &nbsp;&nbsp;&nbsp;&nbsp;\"nonce\": \"fdbmmz79\",                 </br> &nbsp;&nbsp;&nbsp;&nbsp;\"data\": \"\",                          </br> &nbsp;&nbsp;&nbsp;&nbsp;\"signature\" : \"e20c9cd5639d41f8bbc88f4d699c4baf94a4f0ee320e9a116b72743c449eb994\"  </br> &nbsp;&nbsp;}                        </br> }                            </br> </code>   ### Access scope  When asking for `access token` user can provide the required access level (called `scope`) which defines what type of functionality he/she wants to use, and whether requests are only going to check for some data or also to update them.  Scopes are required and checked for `private` methods, so if you plan to use only `public` information you can stay with values assigned by default.  |Scope|Description |----|-----------| |*account:read*|Access to **account** methods - read only data| |*account:read_write*|Access to **account** methods - allows to manage account settings, add subaccounts, etc.| |*trade:read*|Access to **trade** methods - read only data| |*trade:read_write*|Access to **trade** methods - required to create and modify orders| |*wallet:read*|Access to **wallet** methods - read only data| |*wallet:read_write*|Access to **wallet** methods - allows to withdraw, generate new deposit address, etc.| |*wallet:none*, *account:none*, *trade:none*|Blocked access to specified functionality|    <span style=\"color:red\">**NOTICE:**</span> Depending on choosing an authentication method (```grant type```) some scopes could be narrowed by the server. e.g. when ```grant_type = client_credentials``` and ```scope = wallet:read_write``` it's modified by the server as ```scope = wallet:read```\"   ## JSON-RPC over websocket Websocket is the prefered transport mechanism for the JSON-RPC API, because it is faster and because it can support [subscriptions](#subscriptions) and [cancel on disconnect](#private-enable_cancel_on_disconnect). The code examples that can be found next to each of the methods show how websockets can be used from Python or Javascript/node.js.  ## JSON-RPC over HTTP Besides websockets it is also possible to use the API via HTTP. The code examples for 'shell' show how this can be done using curl. Note that subscriptions and cancel on disconnect are not supported via HTTP.  #Methods 

The version of the OpenAPI document: 2.0.0

Generated by: https://openapi-generator.tech
OpenAPI Generator version: 4.0.2-SNAPSHOT

=end

require 'date'

module OpenapiClient
  class Order
    # direction, `buy` or `sell`
    attr_accessor :direction

    # `true` for reduce-only orders only
    attr_accessor :reduce_only

    # Whether the stop order has been triggered (Only for stop orders)
    attr_accessor :triggered

    # Unique order identifier
    attr_accessor :order_id

    # Price in base currency
    attr_accessor :price

    # Order time in force: `\"good_til_cancelled\"`, `\"fill_or_kill\"`, `\"immediate_or_cancel\"`
    attr_accessor :time_in_force

    # `true` if created with API
    attr_accessor :api

    # order state, `\"open\"`, `\"filled\"`, `\"rejected\"`, `\"cancelled\"`, `\"untriggered\"`
    attr_accessor :order_state

    # Implied volatility in percent. (Only if `advanced=\"implv\"`)
    attr_accessor :implv

    # advanced type: `\"usd\"` or `\"implv\"` (Only for options; field is omitted if not applicable). 
    attr_accessor :advanced

    # `true` for post-only orders only
    attr_accessor :post_only

    # Option price in USD (Only if `advanced=\"usd\"`)
    attr_accessor :usd

    # stop price (Only for future stop orders)
    attr_accessor :stop_price

    # order type, `\"limit\"`, `\"market\"`, `\"stop_limit\"`, `\"stop_market\"`
    attr_accessor :order_type

    # The timestamp (seconds since the Unix epoch, with millisecond precision)
    attr_accessor :last_update_timestamp

    # Original order type. Optional field
    attr_accessor :original_order_type

    # Maximum amount within an order to be shown to other traders, 0 for invisible order.
    attr_accessor :max_show

    # Profit and loss in base currency.
    attr_accessor :profit_loss

    # `true` if order was automatically created during liquidation
    attr_accessor :is_liquidation

    # Filled amount of the order. For perpetual and futures the filled_amount is in USD units, for options - in units or corresponding cryptocurrency contracts, e.g., BTC or ETH.
    attr_accessor :filled_amount

    # user defined label (up to 32 characters)
    attr_accessor :label

    # Commission paid so far (in base currency)
    attr_accessor :commission

    # It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH.
    attr_accessor :amount

    # Trigger type (Only for stop orders). Allowed values: `\"index_price\"`, `\"mark_price\"`, `\"last_price\"`.
    attr_accessor :trigger

    # Unique instrument identifier
    attr_accessor :instrument_name

    # The timestamp (seconds since the Unix epoch, with millisecond precision)
    attr_accessor :creation_timestamp

    # Average fill price of the order
    attr_accessor :average_price

    class EnumAttributeValidator
      attr_reader :datatype
      attr_reader :allowable_values

      def initialize(datatype, allowable_values)
        @allowable_values = allowable_values.map do |value|
          case datatype.to_s
          when /Integer/i
            value.to_i
          when /Float/i
            value.to_f
          else
            value
          end
        end
      end

      def valid?(value)
        !value || allowable_values.include?(value)
      end
    end

    # Attribute mapping from ruby-style variable name to JSON key.
    def self.attribute_map
      {
        :'direction' => :'direction',
        :'reduce_only' => :'reduce_only',
        :'triggered' => :'triggered',
        :'order_id' => :'order_id',
        :'price' => :'price',
        :'time_in_force' => :'time_in_force',
        :'api' => :'api',
        :'order_state' => :'order_state',
        :'implv' => :'implv',
        :'advanced' => :'advanced',
        :'post_only' => :'post_only',
        :'usd' => :'usd',
        :'stop_price' => :'stop_price',
        :'order_type' => :'order_type',
        :'last_update_timestamp' => :'last_update_timestamp',
        :'original_order_type' => :'original_order_type',
        :'max_show' => :'max_show',
        :'profit_loss' => :'profit_loss',
        :'is_liquidation' => :'is_liquidation',
        :'filled_amount' => :'filled_amount',
        :'label' => :'label',
        :'commission' => :'commission',
        :'amount' => :'amount',
        :'trigger' => :'trigger',
        :'instrument_name' => :'instrument_name',
        :'creation_timestamp' => :'creation_timestamp',
        :'average_price' => :'average_price'
      }
    end

    # Attribute type mapping.
    def self.openapi_types
      {
        :'direction' => :'String',
        :'reduce_only' => :'Boolean',
        :'triggered' => :'Boolean',
        :'order_id' => :'String',
        :'price' => :'Float',
        :'time_in_force' => :'String',
        :'api' => :'Boolean',
        :'order_state' => :'String',
        :'implv' => :'Float',
        :'advanced' => :'String',
        :'post_only' => :'Boolean',
        :'usd' => :'Float',
        :'stop_price' => :'Float',
        :'order_type' => :'String',
        :'last_update_timestamp' => :'Integer',
        :'original_order_type' => :'String',
        :'max_show' => :'Float',
        :'profit_loss' => :'Float',
        :'is_liquidation' => :'Boolean',
        :'filled_amount' => :'Float',
        :'label' => :'String',
        :'commission' => :'Float',
        :'amount' => :'Float',
        :'trigger' => :'String',
        :'instrument_name' => :'String',
        :'creation_timestamp' => :'Integer',
        :'average_price' => :'Float'
      }
    end

    # Initializes the object
    # @param [Hash] attributes Model attributes in the form of hash
    def initialize(attributes = {})
      if (!attributes.is_a?(Hash))
        fail ArgumentError, "The input argument (attributes) must be a hash in `OpenapiClient::Order` initialize method"
      end

      # check to see if the attribute exists and convert string to symbol for hash key
      attributes = attributes.each_with_object({}) { |(k, v), h|
        if (!self.class.attribute_map.key?(k.to_sym))
          fail ArgumentError, "`#{k}` is not a valid attribute in `OpenapiClient::Order`. Please check the name to make sure it's valid. List of attributes: " + self.class.attribute_map.keys.inspect
        end
        h[k.to_sym] = v
      }

      if attributes.key?(:'direction')
        self.direction = attributes[:'direction']
      end

      if attributes.key?(:'reduce_only')
        self.reduce_only = attributes[:'reduce_only']
      end

      if attributes.key?(:'triggered')
        self.triggered = attributes[:'triggered']
      end

      if attributes.key?(:'order_id')
        self.order_id = attributes[:'order_id']
      end

      if attributes.key?(:'price')
        self.price = attributes[:'price']
      end

      if attributes.key?(:'time_in_force')
        self.time_in_force = attributes[:'time_in_force']
      end

      if attributes.key?(:'api')
        self.api = attributes[:'api']
      end

      if attributes.key?(:'order_state')
        self.order_state = attributes[:'order_state']
      end

      if attributes.key?(:'implv')
        self.implv = attributes[:'implv']
      end

      if attributes.key?(:'advanced')
        self.advanced = attributes[:'advanced']
      end

      if attributes.key?(:'post_only')
        self.post_only = attributes[:'post_only']
      end

      if attributes.key?(:'usd')
        self.usd = attributes[:'usd']
      end

      if attributes.key?(:'stop_price')
        self.stop_price = attributes[:'stop_price']
      end

      if attributes.key?(:'order_type')
        self.order_type = attributes[:'order_type']
      end

      if attributes.key?(:'last_update_timestamp')
        self.last_update_timestamp = attributes[:'last_update_timestamp']
      end

      if attributes.key?(:'original_order_type')
        self.original_order_type = attributes[:'original_order_type']
      end

      if attributes.key?(:'max_show')
        self.max_show = attributes[:'max_show']
      end

      if attributes.key?(:'profit_loss')
        self.profit_loss = attributes[:'profit_loss']
      end

      if attributes.key?(:'is_liquidation')
        self.is_liquidation = attributes[:'is_liquidation']
      end

      if attributes.key?(:'filled_amount')
        self.filled_amount = attributes[:'filled_amount']
      end

      if attributes.key?(:'label')
        self.label = attributes[:'label']
      end

      if attributes.key?(:'commission')
        self.commission = attributes[:'commission']
      end

      if attributes.key?(:'amount')
        self.amount = attributes[:'amount']
      end

      if attributes.key?(:'trigger')
        self.trigger = attributes[:'trigger']
      end

      if attributes.key?(:'instrument_name')
        self.instrument_name = attributes[:'instrument_name']
      end

      if attributes.key?(:'creation_timestamp')
        self.creation_timestamp = attributes[:'creation_timestamp']
      end

      if attributes.key?(:'average_price')
        self.average_price = attributes[:'average_price']
      end
    end

    # Show invalid properties with the reasons. Usually used together with valid?
    # @return Array for valid properties with the reasons
    def list_invalid_properties
      invalid_properties = Array.new
      if @direction.nil?
        invalid_properties.push('invalid value for "direction", direction cannot be nil.')
      end

      if @order_id.nil?
        invalid_properties.push('invalid value for "order_id", order_id cannot be nil.')
      end

      if @price.nil?
        invalid_properties.push('invalid value for "price", price cannot be nil.')
      end

      if @time_in_force.nil?
        invalid_properties.push('invalid value for "time_in_force", time_in_force cannot be nil.')
      end

      if @api.nil?
        invalid_properties.push('invalid value for "api", api cannot be nil.')
      end

      if @order_state.nil?
        invalid_properties.push('invalid value for "order_state", order_state cannot be nil.')
      end

      if @post_only.nil?
        invalid_properties.push('invalid value for "post_only", post_only cannot be nil.')
      end

      if @order_type.nil?
        invalid_properties.push('invalid value for "order_type", order_type cannot be nil.')
      end

      if @last_update_timestamp.nil?
        invalid_properties.push('invalid value for "last_update_timestamp", last_update_timestamp cannot be nil.')
      end

      if @max_show.nil?
        invalid_properties.push('invalid value for "max_show", max_show cannot be nil.')
      end

      if @is_liquidation.nil?
        invalid_properties.push('invalid value for "is_liquidation", is_liquidation cannot be nil.')
      end

      if @label.nil?
        invalid_properties.push('invalid value for "label", label cannot be nil.')
      end

      if @creation_timestamp.nil?
        invalid_properties.push('invalid value for "creation_timestamp", creation_timestamp cannot be nil.')
      end

      invalid_properties
    end

    # Check to see if the all the properties in the model are valid
    # @return true if the model is valid
    def valid?
      return false if @direction.nil?
      direction_validator = EnumAttributeValidator.new('String', ["buy", "sell"])
      return false unless direction_validator.valid?(@direction)
      return false if @order_id.nil?
      return false if @price.nil?
      return false if @time_in_force.nil?
      time_in_force_validator = EnumAttributeValidator.new('String', ["good_til_cancelled", "fill_or_kill", "immediate_or_cancel"])
      return false unless time_in_force_validator.valid?(@time_in_force)
      return false if @api.nil?
      return false if @order_state.nil?
      order_state_validator = EnumAttributeValidator.new('String', ["open", "filled", "rejected", "cancelled", "untriggered", "triggered"])
      return false unless order_state_validator.valid?(@order_state)
      advanced_validator = EnumAttributeValidator.new('String', ["usd", "implv"])
      return false unless advanced_validator.valid?(@advanced)
      return false if @post_only.nil?
      return false if @order_type.nil?
      order_type_validator = EnumAttributeValidator.new('String', ["market", "limit", "stop_market", "stop_limit"])
      return false unless order_type_validator.valid?(@order_type)
      return false if @last_update_timestamp.nil?
      original_order_type_validator = EnumAttributeValidator.new('String', ["market"])
      return false unless original_order_type_validator.valid?(@original_order_type)
      return false if @max_show.nil?
      return false if @is_liquidation.nil?
      return false if @label.nil?
      trigger_validator = EnumAttributeValidator.new('String', ["index_price", "mark_price", "last_price"])
      return false unless trigger_validator.valid?(@trigger)
      return false if @creation_timestamp.nil?
      true
    end

    # Custom attribute writer method checking allowed values (enum).
    # @param [Object] direction Object to be assigned
    def direction=(direction)
      validator = EnumAttributeValidator.new('String', ["buy", "sell"])
      unless validator.valid?(direction)
        fail ArgumentError, "invalid value for \"direction\", must be one of #{validator.allowable_values}."
      end
      @direction = direction
    end

    # Custom attribute writer method checking allowed values (enum).
    # @param [Object] time_in_force Object to be assigned
    def time_in_force=(time_in_force)
      validator = EnumAttributeValidator.new('String', ["good_til_cancelled", "fill_or_kill", "immediate_or_cancel"])
      unless validator.valid?(time_in_force)
        fail ArgumentError, "invalid value for \"time_in_force\", must be one of #{validator.allowable_values}."
      end
      @time_in_force = time_in_force
    end

    # Custom attribute writer method checking allowed values (enum).
    # @param [Object] order_state Object to be assigned
    def order_state=(order_state)
      validator = EnumAttributeValidator.new('String', ["open", "filled", "rejected", "cancelled", "untriggered", "triggered"])
      unless validator.valid?(order_state)
        fail ArgumentError, "invalid value for \"order_state\", must be one of #{validator.allowable_values}."
      end
      @order_state = order_state
    end

    # Custom attribute writer method checking allowed values (enum).
    # @param [Object] advanced Object to be assigned
    def advanced=(advanced)
      validator = EnumAttributeValidator.new('String', ["usd", "implv"])
      unless validator.valid?(advanced)
        fail ArgumentError, "invalid value for \"advanced\", must be one of #{validator.allowable_values}."
      end
      @advanced = advanced
    end

    # Custom attribute writer method checking allowed values (enum).
    # @param [Object] order_type Object to be assigned
    def order_type=(order_type)
      validator = EnumAttributeValidator.new('String', ["market", "limit", "stop_market", "stop_limit"])
      unless validator.valid?(order_type)
        fail ArgumentError, "invalid value for \"order_type\", must be one of #{validator.allowable_values}."
      end
      @order_type = order_type
    end

    # Custom attribute writer method checking allowed values (enum).
    # @param [Object] original_order_type Object to be assigned
    def original_order_type=(original_order_type)
      validator = EnumAttributeValidator.new('String', ["market"])
      unless validator.valid?(original_order_type)
        fail ArgumentError, "invalid value for \"original_order_type\", must be one of #{validator.allowable_values}."
      end
      @original_order_type = original_order_type
    end

    # Custom attribute writer method checking allowed values (enum).
    # @param [Object] trigger Object to be assigned
    def trigger=(trigger)
      validator = EnumAttributeValidator.new('String', ["index_price", "mark_price", "last_price"])
      unless validator.valid?(trigger)
        fail ArgumentError, "invalid value for \"trigger\", must be one of #{validator.allowable_values}."
      end
      @trigger = trigger
    end

    # Checks equality by comparing each attribute.
    # @param [Object] Object to be compared
    def ==(o)
      return true if self.equal?(o)
      self.class == o.class &&
          direction == o.direction &&
          reduce_only == o.reduce_only &&
          triggered == o.triggered &&
          order_id == o.order_id &&
          price == o.price &&
          time_in_force == o.time_in_force &&
          api == o.api &&
          order_state == o.order_state &&
          implv == o.implv &&
          advanced == o.advanced &&
          post_only == o.post_only &&
          usd == o.usd &&
          stop_price == o.stop_price &&
          order_type == o.order_type &&
          last_update_timestamp == o.last_update_timestamp &&
          original_order_type == o.original_order_type &&
          max_show == o.max_show &&
          profit_loss == o.profit_loss &&
          is_liquidation == o.is_liquidation &&
          filled_amount == o.filled_amount &&
          label == o.label &&
          commission == o.commission &&
          amount == o.amount &&
          trigger == o.trigger &&
          instrument_name == o.instrument_name &&
          creation_timestamp == o.creation_timestamp &&
          average_price == o.average_price
    end

    # @see the `==` method
    # @param [Object] Object to be compared
    def eql?(o)
      self == o
    end

    # Calculates hash code according to all attributes.
    # @return [Integer] Hash code
    def hash
      [direction, reduce_only, triggered, order_id, price, time_in_force, api, order_state, implv, advanced, post_only, usd, stop_price, order_type, last_update_timestamp, original_order_type, max_show, profit_loss, is_liquidation, filled_amount, label, commission, amount, trigger, instrument_name, creation_timestamp, average_price].hash
    end

    # Builds the object from hash
    # @param [Hash] attributes Model attributes in the form of hash
    # @return [Object] Returns the model itself
    def self.build_from_hash(attributes)
      new.build_from_hash(attributes)
    end

    # Builds the object from hash
    # @param [Hash] attributes Model attributes in the form of hash
    # @return [Object] Returns the model itself
    def build_from_hash(attributes)
      return nil unless attributes.is_a?(Hash)
      self.class.openapi_types.each_pair do |key, type|
        if type =~ /\AArray<(.*)>/i
          # check to ensure the input is an array given that the attribute
          # is documented as an array but the input is not
          if attributes[self.class.attribute_map[key]].is_a?(Array)
            self.send("#{key}=", attributes[self.class.attribute_map[key]].map { |v| _deserialize($1, v) })
          end
        elsif !attributes[self.class.attribute_map[key]].nil?
          self.send("#{key}=", _deserialize(type, attributes[self.class.attribute_map[key]]))
        end # or else data not found in attributes(hash), not an issue as the data can be optional
      end

      self
    end

    # Deserializes the data based on type
    # @param string type Data type
    # @param string value Value to be deserialized
    # @return [Object] Deserialized data
    def _deserialize(type, value)
      case type.to_sym
      when :DateTime
        DateTime.parse(value)
      when :Date
        Date.parse(value)
      when :String
        value.to_s
      when :Integer
        value.to_i
      when :Float
        value.to_f
      when :Boolean
        if value.to_s =~ /\A(true|t|yes|y|1)\z/i
          true
        else
          false
        end
      when :Object
        # generic object (usually a Hash), return directly
        value
      when /\AArray<(?<inner_type>.+)>\z/
        inner_type = Regexp.last_match[:inner_type]
        value.map { |v| _deserialize(inner_type, v) }
      when /\AHash<(?<k_type>.+?), (?<v_type>.+)>\z/
        k_type = Regexp.last_match[:k_type]
        v_type = Regexp.last_match[:v_type]
        {}.tap do |hash|
          value.each do |k, v|
            hash[_deserialize(k_type, k)] = _deserialize(v_type, v)
          end
        end
      else # model
        OpenapiClient.const_get(type).build_from_hash(value)
      end
    end

    # Returns the string representation of the object
    # @return [String] String presentation of the object
    def to_s
      to_hash.to_s
    end

    # to_body is an alias to to_hash (backward compatibility)
    # @return [Hash] Returns the object in the form of hash
    def to_body
      to_hash
    end

    # Returns the object in the form of hash
    # @return [Hash] Returns the object in the form of hash
    def to_hash
      hash = {}
      self.class.attribute_map.each_pair do |attr, param|
        value = self.send(attr)
        next if value.nil?
        hash[param] = _to_hash(value)
      end
      hash
    end

    # Outputs non-array value in the form of hash
    # For object, use to_hash. Otherwise, just return the value
    # @param [Object] value Any valid value
    # @return [Hash] Returns the value in the form of hash
    def _to_hash(value)
      if value.is_a?(Array)
        value.compact.map { |v| _to_hash(v) }
      elsif value.is_a?(Hash)
        {}.tap do |hash|
          value.each { |k, v| hash[k] = _to_hash(v) }
        end
      elsif value.respond_to? :to_hash
        value.to_hash
      else
        value
      end
    end
  end
end
