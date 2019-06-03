/**
 * Deribit API
 * #Overview  Deribit provides three different interfaces to access the API:  * [JSON-RPC over Websocket](#json-rpc) * [JSON-RPC over HTTP](#json-rpc) * [FIX](#fix-api) (Financial Information eXchange)  With the API Console you can use and test the JSON-RPC API, both via HTTP and  via Websocket. To visit the API console, go to __Account > API tab >  API Console tab.__   ##Naming Deribit tradeable assets or instruments use the following system of naming:  |Kind|Examples|Template|Comments| |----|--------|--------|--------| |Future|<code>BTC-25MAR16</code>, <code>BTC-5AUG16</code>|<code>BTC-DMMMYY</code>|<code>BTC</code> is currency, <code>DMMMYY</code> is expiration date, <code>D</code> stands for day of month (1 or 2 digits), <code>MMM</code> - month (3 first letters in English), <code>YY</code> stands for year.| |Perpetual|<code>BTC-PERPETUAL</code>                        ||Perpetual contract for currency <code>BTC</code>.| |Option|<code>BTC-25MAR16-420-C</code>, <code>BTC-5AUG16-580-P</code>|<code>BTC-DMMMYY-STRIKE-K</code>|<code>STRIKE</code> is option strike price in USD. Template <code>K</code> is option kind: <code>C</code> for call options or <code>P</code> for put options.|   # JSON-RPC JSON-RPC is a light-weight remote procedure call (RPC) protocol. The  [JSON-RPC specification](https://www.jsonrpc.org/specification) defines the data structures that are used for the messages that are exchanged between client and server, as well as the rules around their processing. JSON-RPC uses JSON (RFC 4627) as data format.  JSON-RPC is transport agnostic: it does not specify which transport mechanism must be used. The Deribit API supports both Websocket (preferred) and HTTP (with limitations: subscriptions are not supported over HTTP).  ## Request messages > An example of a request message:  ```json {     \"jsonrpc\": \"2.0\",     \"id\": 8066,     \"method\": \"public/ticker\",     \"params\": {         \"instrument\": \"BTC-24AUG18-6500-P\"     } } ```  According to the JSON-RPC sepcification the requests must be JSON objects with the following fields.  |Name|Type|Description| |----|----|-----------| |jsonrpc|string|The version of the JSON-RPC spec: \"2.0\"| |id|integer or string|An identifier of the request. If it is included, then the response will contain the same identifier| |method|string|The method to be invoked| |params|object|The parameters values for the method. The field names must match with the expected parameter names. The parameters that are expected are described in the documentation for the methods, below.|  <aside class=\"warning\"> The JSON-RPC specification describes two features that are currently not supported by the API:  <ul> <li>Specification of parameter values by position</li> <li>Batch requests</li> </ul>  </aside>   ## Response messages > An example of a response message:  ```json {     \"jsonrpc\": \"2.0\",     \"id\": 5239,     \"testnet\": false,     \"result\": [         {             \"currency\": \"BTC\",             \"currencyLong\": \"Bitcoin\",             \"minConfirmation\": 2,             \"txFee\": 0.0006,             \"isActive\": true,             \"coinType\": \"BITCOIN\",             \"baseAddress\": null         }     ],     \"usIn\": 1535043730126248,     \"usOut\": 1535043730126250,     \"usDiff\": 2 } ```  The JSON-RPC API always responds with a JSON object with the following fields.   |Name|Type|Description| |----|----|-----------| |id|integer|This is the same id that was sent in the request.| |result|any|If successful, the result of the API call. The format for the result is described with each method.| |error|error object|Only present if there was an error invoking the method. The error object is described below.| |testnet|boolean|Indicates whether the API in use is actually the test API.  <code>false</code> for production server, <code>true</code> for test server.| |usIn|integer|The timestamp when the requests was received (microseconds since the Unix epoch)| |usOut|integer|The timestamp when the response was sent (microseconds since the Unix epoch)| |usDiff|integer|The number of microseconds that was spent handling the request|  <aside class=\"notice\"> The fields <code>testnet</code>, <code>usIn</code>, <code>usOut</code> and <code>usDiff</code> are not part of the JSON-RPC standard.  <p>In order not to clutter the examples they will generally be omitted from the example code.</p> </aside>  > An example of a response with an error:  ```json {     \"jsonrpc\": \"2.0\",     \"id\": 8163,     \"error\": {         \"code\": 11050,         \"message\": \"bad_request\"     },     \"testnet\": false,     \"usIn\": 1535037392434763,     \"usOut\": 1535037392448119,     \"usDiff\": 13356 } ``` In case of an error the response message will contain the error field, with as value an object with the following with the following fields:  |Name|Type|Description |----|----|-----------| |code|integer|A number that indicates the kind of error.| |message|string|A short description that indicates the kind of error.| |data|any|Additional data about the error. This field may be omitted.|  ## Notifications  > An example of a notification:  ```json {     \"jsonrpc\": \"2.0\",     \"method\": \"subscription\",     \"params\": {         \"channel\": \"deribit_price_index.btc_usd\",         \"data\": {             \"timestamp\": 1535098298227,             \"price\": 6521.17,             \"index_name\": \"btc_usd\"         }     } } ```  API users can subscribe to certain types of notifications. This means that they will receive JSON-RPC notification-messages from the server when certain events occur, such as changes to the index price or changes to the order book for a certain instrument.   The API methods [public/subscribe](#public-subscribe) and [private/subscribe](#private-subscribe) are used to set up a subscription. Since HTTP does not support the sending of messages from server to client, these methods are only availble when using the Websocket transport mechanism.  At the moment of subscription a \"channel\" must be specified. The channel determines the type of events that will be received.  See [Subscriptions](#subscriptions) for more details about the channels.  In accordance with the JSON-RPC specification, the format of a notification  is that of a request message without an <code>id</code> field. The value of the <code>method</code> field will always be <code>\"subscription\"</code>. The <code>params</code> field will always be an object with 2 members: <code>channel</code> and <code>data</code>. The value of the <code>channel</code> member is the name of the channel (a string). The value of the <code>data</code> member is an object that contains data  that is specific for the channel.   ## Authentication  > An example of a JSON request with token:  ```json {     \"id\": 5647,     \"method\": \"private/get_subaccounts\",     \"params\": {         \"access_token\": \"67SVutDoVZSzkUStHSuk51WntMNBJ5mh5DYZhwzpiqDF\"     } } ```  The API consists of `public` and `private` methods. The public methods do not require authentication. The private methods use OAuth 2.0 authentication. This means that a valid OAuth access token must be included in the request, which can get achived by calling method [public/auth](#public-auth).  When the token was assigned to the user, it should be passed along, with other request parameters, back to the server:  |Connection type|Access token placement |----|-----------| |**Websocket**|Inside request JSON parameters, as an `access_token` field| |**HTTP (REST)**|Header `Authorization: bearer ```Token``` ` value|  ### Additional authorization method - basic user credentials  <span style=\"color:red\"><b> ! Not recommended - however, it could be useful for quick testing API</b></span></br>  Every `private` method could be accessed by providing, inside HTTP `Authorization: Basic XXX` header, values with user `ClientId` and assigned `ClientSecret` (both values can be found on the API page on the Deribit website) encoded with `Base64`:  <code>Authorization: Basic BASE64(`ClientId` + `:` + `ClientSecret`)</code>   ### Additional authorization method - Deribit signature credentials  The Derbit service provides dedicated authorization method, which harness user generated signature to increase security level for passing request data. Generated value is passed inside `Authorization` header, coded as:  <code>Authorization: deri-hmac-sha256 id=```ClientId```,ts=```Timestamp```,sig=```Signature```,nonce=```Nonce```</code>  where:  |Deribit credential|Description |----|-----------| |*ClientId*|Can be found on the API page on the Deribit website| |*Timestamp*|Time when the request was generated - given as **miliseconds**. It\'s valid for **60 seconds** since generation, after that time any request with an old timestamp will be rejected.| |*Signature*|Value for signature calculated as described below | |*Nonce*|Single usage, user generated initialization vector for the server token|  The signature is generated by the following formula:  <code> Signature = HEX_STRING( HMAC-SHA256( ClientSecret, StringToSign ) );</code></br>  <code> StringToSign =  Timestamp + \"\\n\" + Nonce + \"\\n\" + RequestData;</code></br>  <code> RequestData =  UPPERCASE(HTTP_METHOD())  + \"\\n\" + URI() + \"\\n\" + RequestBody + \"\\n\";</code></br>   e.g. (using shell with ```openssl``` tool):  <code>&nbsp;&nbsp;&nbsp;&nbsp;ClientId=AAAAAAAAAAA</code></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;ClientSecret=ABCD</code></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;Timestamp=$( date +%s000 )</code></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;Nonce=$( cat /dev/urandom | tr -dc \'a-z0-9\' | head -c8 )</code></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;URI=\"/api/v2/private/get_account_summary?currency=BTC\"</code></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;HttpMethod=GET</code></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;Body=\"\"</code></br></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;Signature=$( echo -ne \"${Timestamp}\\n${Nonce}\\n${HttpMethod}\\n${URI}\\n${Body}\\n\" | openssl sha256 -r -hmac \"$ClientSecret\" | cut -f1 -d\' \' )</code></br></br> <code>&nbsp;&nbsp;&nbsp;&nbsp;echo $Signature</code></br></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;shell output> ea40d5e5e4fae235ab22b61da98121fbf4acdc06db03d632e23c66bcccb90d2c  (**WARNING**: Exact value depends on current timestamp and client credentials</code></br></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;curl -s -X ${HttpMethod} -H \"Authorization: deri-hmac-sha256 id=${ClientId},ts=${Timestamp},nonce=${Nonce},sig=${Signature}\" \"https://www.deribit.com${URI}\"</code></br></br>    ### Additional authorization method - signature credentials (WebSocket API)  When connecting through Websocket, user can request for authorization using ```client_credential``` method, which requires providing following parameters (as a part of JSON request):  |JSON parameter|Description |----|-----------| |*grant_type*|Must be **client_signature**| |*client_id*|Can be found on the API page on the Deribit website| |*timestamp*|Time when the request was generated - given as **miliseconds**. It\'s valid for **60 seconds** since generation, after that time any request with an old timestamp will be rejected.| |*signature*|Value for signature calculated as described below | |*nonce*|Single usage, user generated initialization vector for the server token| |*data*|**Optional** field, which contains any user specific value|  The signature is generated by the following formula:  <code> StringToSign =  Timestamp + \"\\n\" + Nonce + \"\\n\" + Data;</code></br>  <code> Signature = HEX_STRING( HMAC-SHA256( ClientSecret, StringToSign ) );</code></br>   e.g. (using shell with ```openssl``` tool):  <code>&nbsp;&nbsp;&nbsp;&nbsp;ClientId=AAAAAAAAAAA</code></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;ClientSecret=ABCD</code></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;Timestamp=$( date +%s000 ) # e.g. 1554883365000 </code></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;Nonce=$( cat /dev/urandom | tr -dc \'a-z0-9\' | head -c8 ) # e.g. fdbmmz79 </code></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;Data=\"\"</code></br></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;Signature=$( echo -ne \"${Timestamp}\\n${Nonce}\\n${Data}\\n\" | openssl sha256 -r -hmac \"$ClientSecret\" | cut -f1 -d\' \' )</code></br></br> <code>&nbsp;&nbsp;&nbsp;&nbsp;echo $Signature</code></br></br>  <code>&nbsp;&nbsp;&nbsp;&nbsp;shell output> e20c9cd5639d41f8bbc88f4d699c4baf94a4f0ee320e9a116b72743c449eb994  (**WARNING**: Exact value depends on current timestamp and client credentials</code></br></br>   You can also check the signature value using some online tools like, e.g: [https://codebeautify.org/hmac-generator](https://codebeautify.org/hmac-generator) (but don\'t forget about adding *newline* after each part of the hashed text and remember that you **should use** it only with your **test credentials**).   Here\'s a sample JSON request created using the values from the example above:  <code> {                            </br> &nbsp;&nbsp;\"jsonrpc\" : \"2.0\",         </br> &nbsp;&nbsp;\"id\" : 9929,               </br> &nbsp;&nbsp;\"method\" : \"public/auth\",  </br> &nbsp;&nbsp;\"params\" :                 </br> &nbsp;&nbsp;{                        </br> &nbsp;&nbsp;&nbsp;&nbsp;\"grant_type\" : \"client_signature\",   </br> &nbsp;&nbsp;&nbsp;&nbsp;\"client_id\" : \"AAAAAAAAAAA\",         </br> &nbsp;&nbsp;&nbsp;&nbsp;\"timestamp\": \"1554883365000\",        </br> &nbsp;&nbsp;&nbsp;&nbsp;\"nonce\": \"fdbmmz79\",                 </br> &nbsp;&nbsp;&nbsp;&nbsp;\"data\": \"\",                          </br> &nbsp;&nbsp;&nbsp;&nbsp;\"signature\" : \"e20c9cd5639d41f8bbc88f4d699c4baf94a4f0ee320e9a116b72743c449eb994\"  </br> &nbsp;&nbsp;}                        </br> }                            </br> </code>   ### Access scope  When asking for `access token` user can provide the required access level (called `scope`) which defines what type of functionality he/she wants to use, and whether requests are only going to check for some data or also to update them.  Scopes are required and checked for `private` methods, so if you plan to use only `public` information you can stay with values assigned by default.  |Scope|Description |----|-----------| |*account:read*|Access to **account** methods - read only data| |*account:read_write*|Access to **account** methods - allows to manage account settings, add subaccounts, etc.| |*trade:read*|Access to **trade** methods - read only data| |*trade:read_write*|Access to **trade** methods - required to create and modify orders| |*wallet:read*|Access to **wallet** methods - read only data| |*wallet:read_write*|Access to **wallet** methods - allows to withdraw, generate new deposit address, etc.| |*wallet:none*, *account:none*, *trade:none*|Blocked access to specified functionality|    <span style=\"color:red\">**NOTICE:**</span> Depending on choosing an authentication method (```grant type```) some scopes could be narrowed by the server. e.g. when ```grant_type = client_credentials``` and ```scope = wallet:read_write``` it\'s modified by the server as ```scope = wallet:read```\"   ## JSON-RPC over websocket Websocket is the prefered transport mechanism for the JSON-RPC API, because it is faster and because it can support [subscriptions](#subscriptions) and [cancel on disconnect](#private-enable_cancel_on_disconnect). The code examples that can be found next to each of the methods show how websockets can be used from Python or Javascript/node.js.  ## JSON-RPC over HTTP Besides websockets it is also possible to use the API via HTTP. The code examples for \'shell\' show how this can be done using curl. Note that subscriptions and cancel on disconnect are not supported via HTTP.  #Methods 
 *
 * The version of the OpenAPI document: 2.0.0
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */

import localVarRequest = require('request');
import http = require('http');

/* tslint:disable:no-unused-locals */

import { ObjectSerializer, Authentication, VoidAuth } from '../model/models';
import { HttpBasicAuth } from '../model/models';

let defaultBasePath = 'https://www.deribit.com/api/v2';

// ===============================================
// This file is autogenerated - Please do not edit
// ===============================================

export enum PrivateApiApiKeys {
}

export class PrivateApi {
    protected _basePath = defaultBasePath;
    protected defaultHeaders : any = {};
    protected _useQuerystring : boolean = false;

    protected authentications = {
        'default': <Authentication>new VoidAuth(),
        'bearerAuth': new HttpBasicAuth(),
    }

    constructor(basePath?: string);
    constructor(username: string, password: string, basePath?: string);
    constructor(basePathOrUsername: string, password?: string, basePath?: string) {
        if (password) {
            this.username = basePathOrUsername;
            this.password = password
            if (basePath) {
                this.basePath = basePath;
            }
        } else {
            if (basePathOrUsername) {
                this.basePath = basePathOrUsername
            }
        }
    }

    set useQuerystring(value: boolean) {
        this._useQuerystring = value;
    }

    set basePath(basePath: string) {
        this._basePath = basePath;
    }

    get basePath() {
        return this._basePath;
    }

    public setDefaultAuthentication(auth: Authentication) {
        this.authentications.default = auth;
    }

    public setApiKey(key: PrivateApiApiKeys, value: string) {
        (this.authentications as any)[PrivateApiApiKeys[key]].apiKey = value;
    }
    set username(username: string) {
        this.authentications.bearerAuth.username = username;
    }

    set password(password: string) {
        this.authentications.bearerAuth.password = password;
    }

    /**
     * 
     * @summary Adds new entry to address book of given type
     * @param currency The currency symbol
     * @param type Address book type
     * @param address Address in currency format, it must be in address book
     * @param name Name of address book entry
     * @param tfa TFA code, required when TFA is enabled for current account
     */
    public async privateAddToAddressBookGet (currency: 'BTC' | 'ETH', type: 'transfer' | 'withdrawal', address: string, name: string, tfa?: string, options: {headers: {[name: string]: string}} = {headers: {}}) : Promise<{ response: http.ClientResponse; body: object;  }> {
        const localVarPath = this.basePath + '/private/add_to_address_book';
        let localVarQueryParameters: any = {};
        let localVarHeaderParams: any = (<any>Object).assign({}, this.defaultHeaders);
        let localVarFormParams: any = {};

        // verify required parameter 'currency' is not null or undefined
        if (currency === null || currency === undefined) {
            throw new Error('Required parameter currency was null or undefined when calling privateAddToAddressBookGet.');
        }

        // verify required parameter 'type' is not null or undefined
        if (type === null || type === undefined) {
            throw new Error('Required parameter type was null or undefined when calling privateAddToAddressBookGet.');
        }

        // verify required parameter 'address' is not null or undefined
        if (address === null || address === undefined) {
            throw new Error('Required parameter address was null or undefined when calling privateAddToAddressBookGet.');
        }

        // verify required parameter 'name' is not null or undefined
        if (name === null || name === undefined) {
            throw new Error('Required parameter name was null or undefined when calling privateAddToAddressBookGet.');
        }

        if (currency !== undefined) {
            localVarQueryParameters['currency'] = ObjectSerializer.serialize(currency, "'BTC' | 'ETH'");
        }

        if (type !== undefined) {
            localVarQueryParameters['type'] = ObjectSerializer.serialize(type, "'transfer' | 'withdrawal'");
        }

        if (address !== undefined) {
            localVarQueryParameters['address'] = ObjectSerializer.serialize(address, "string");
        }

        if (name !== undefined) {
            localVarQueryParameters['name'] = ObjectSerializer.serialize(name, "string");
        }

        if (tfa !== undefined) {
            localVarQueryParameters['tfa'] = ObjectSerializer.serialize(tfa, "string");
        }

        (<any>Object).assign(localVarHeaderParams, options.headers);

        let localVarUseFormData = false;

        let localVarRequestOptions: localVarRequest.Options = {
            method: 'GET',
            qs: localVarQueryParameters,
            headers: localVarHeaderParams,
            uri: localVarPath,
            useQuerystring: this._useQuerystring,
            json: true,
        };

        this.authentications.bearerAuth.applyToRequest(localVarRequestOptions);

        this.authentications.default.applyToRequest(localVarRequestOptions);

        if (Object.keys(localVarFormParams).length) {
            if (localVarUseFormData) {
                (<any>localVarRequestOptions).formData = localVarFormParams;
            } else {
                localVarRequestOptions.form = localVarFormParams;
            }
        }
        return new Promise<{ response: http.ClientResponse; body: object;  }>((resolve, reject) => {
            localVarRequest(localVarRequestOptions, (error, response, body) => {
                if (error) {
                    reject(error);
                } else {
                    body = ObjectSerializer.deserialize(body, "object");
                    if (response.statusCode && response.statusCode >= 200 && response.statusCode <= 299) {
                        resolve({ response: response, body: body });
                    } else {
                        reject({ response: response, body: body });
                    }
                }
            });
        });
    }
    /**
     * 
     * @summary Places a buy order for an instrument.
     * @param instrumentName Instrument name
     * @param amount It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH
     * @param type The order type, default: &#x60;\&quot;limit\&quot;&#x60;
     * @param label user defined label for the order (maximum 32 characters)
     * @param price &lt;p&gt;The order price in base currency (Only for limit and stop_limit orders)&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;usd, the field price should be the option price value in USD.&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;implv, the field price should be a value of implied volatility in percentages. For example,  price&#x3D;100, means implied volatility of 100%&lt;/p&gt;
     * @param timeInForce &lt;p&gt;Specifies how long the order remains in effect. Default &#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; &lt;ul&gt; &lt;li&gt;&#x60;\&quot;good_til_cancelled\&quot;&#x60; - unfilled order remains in order book until cancelled&lt;/li&gt; &lt;li&gt;&#x60;\&quot;fill_or_kill\&quot;&#x60; - execute a transaction immediately and completely or not at all&lt;/li&gt; &lt;li&gt;&#x60;\&quot;immediate_or_cancel\&quot;&#x60; - execute a transaction immediately, and any portion of the order that cannot be immediately filled is cancelled&lt;/li&gt; &lt;/ul&gt;
     * @param maxShow Maximum amount within an order to be shown to other customers, &#x60;0&#x60; for invisible order
     * @param postOnly &lt;p&gt;If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.&lt;/p&gt; &lt;p&gt;Only valid in combination with time_in_force&#x3D;&#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt;
     * @param reduceOnly If &#x60;true&#x60;, the order is considered reduce-only which is intended to only reduce a current position
     * @param stopPrice Stop price, required for stop limit orders (Only for stop orders)
     * @param trigger Defines trigger type, required for &#x60;\&quot;stop_limit\&quot;&#x60; order type
     * @param advanced Advanced option order type. (Only for options)
     */
    public async privateBuyGet (instrumentName: string, amount: number, type?: 'limit' | 'stop_limit' | 'market' | 'stop_market', label?: string, price?: number, timeInForce?: 'good_til_cancelled' | 'fill_or_kill' | 'immediate_or_cancel', maxShow?: number, postOnly?: boolean, reduceOnly?: boolean, stopPrice?: number, trigger?: 'index_price' | 'mark_price' | 'last_price', advanced?: 'usd' | 'implv', options: {headers: {[name: string]: string}} = {headers: {}}) : Promise<{ response: http.ClientResponse; body: object;  }> {
        const localVarPath = this.basePath + '/private/buy';
        let localVarQueryParameters: any = {};
        let localVarHeaderParams: any = (<any>Object).assign({}, this.defaultHeaders);
        let localVarFormParams: any = {};

        // verify required parameter 'instrumentName' is not null or undefined
        if (instrumentName === null || instrumentName === undefined) {
            throw new Error('Required parameter instrumentName was null or undefined when calling privateBuyGet.');
        }

        // verify required parameter 'amount' is not null or undefined
        if (amount === null || amount === undefined) {
            throw new Error('Required parameter amount was null or undefined when calling privateBuyGet.');
        }

        if (instrumentName !== undefined) {
            localVarQueryParameters['instrument_name'] = ObjectSerializer.serialize(instrumentName, "string");
        }

        if (amount !== undefined) {
            localVarQueryParameters['amount'] = ObjectSerializer.serialize(amount, "number");
        }

        if (type !== undefined) {
            localVarQueryParameters['type'] = ObjectSerializer.serialize(type, "'limit' | 'stop_limit' | 'market' | 'stop_market'");
        }

        if (label !== undefined) {
            localVarQueryParameters['label'] = ObjectSerializer.serialize(label, "string");
        }

        if (price !== undefined) {
            localVarQueryParameters['price'] = ObjectSerializer.serialize(price, "number");
        }

        if (timeInForce !== undefined) {
            localVarQueryParameters['time_in_force'] = ObjectSerializer.serialize(timeInForce, "'good_til_cancelled' | 'fill_or_kill' | 'immediate_or_cancel'");
        }

        if (maxShow !== undefined) {
            localVarQueryParameters['max_show'] = ObjectSerializer.serialize(maxShow, "number");
        }

        if (postOnly !== undefined) {
            localVarQueryParameters['post_only'] = ObjectSerializer.serialize(postOnly, "boolean");
        }

        if (reduceOnly !== undefined) {
            localVarQueryParameters['reduce_only'] = ObjectSerializer.serialize(reduceOnly, "boolean");
        }

        if (stopPrice !== undefined) {
            localVarQueryParameters['stop_price'] = ObjectSerializer.serialize(stopPrice, "number");
        }

        if (trigger !== undefined) {
            localVarQueryParameters['trigger'] = ObjectSerializer.serialize(trigger, "'index_price' | 'mark_price' | 'last_price'");
        }

        if (advanced !== undefined) {
            localVarQueryParameters['advanced'] = ObjectSerializer.serialize(advanced, "'usd' | 'implv'");
        }

        (<any>Object).assign(localVarHeaderParams, options.headers);

        let localVarUseFormData = false;

        let localVarRequestOptions: localVarRequest.Options = {
            method: 'GET',
            qs: localVarQueryParameters,
            headers: localVarHeaderParams,
            uri: localVarPath,
            useQuerystring: this._useQuerystring,
            json: true,
        };

        this.authentications.bearerAuth.applyToRequest(localVarRequestOptions);

        this.authentications.default.applyToRequest(localVarRequestOptions);

        if (Object.keys(localVarFormParams).length) {
            if (localVarUseFormData) {
                (<any>localVarRequestOptions).formData = localVarFormParams;
            } else {
                localVarRequestOptions.form = localVarFormParams;
            }
        }
        return new Promise<{ response: http.ClientResponse; body: object;  }>((resolve, reject) => {
            localVarRequest(localVarRequestOptions, (error, response, body) => {
                if (error) {
                    reject(error);
                } else {
                    body = ObjectSerializer.deserialize(body, "object");
                    if (response.statusCode && response.statusCode >= 200 && response.statusCode <= 299) {
                        resolve({ response: response, body: body });
                    } else {
                        reject({ response: response, body: body });
                    }
                }
            });
        });
    }
    /**
     * 
     * @summary Cancels all orders by currency, optionally filtered by instrument kind and/or order type.
     * @param currency The currency symbol
     * @param kind Instrument kind, if not provided instruments of all kinds are considered
     * @param type Order type - limit, stop or all, default - &#x60;all&#x60;
     */
    public async privateCancelAllByCurrencyGet (currency: 'BTC' | 'ETH', kind?: 'future' | 'option', type?: 'all' | 'limit' | 'stop', options: {headers: {[name: string]: string}} = {headers: {}}) : Promise<{ response: http.ClientResponse; body: object;  }> {
        const localVarPath = this.basePath + '/private/cancel_all_by_currency';
        let localVarQueryParameters: any = {};
        let localVarHeaderParams: any = (<any>Object).assign({}, this.defaultHeaders);
        let localVarFormParams: any = {};

        // verify required parameter 'currency' is not null or undefined
        if (currency === null || currency === undefined) {
            throw new Error('Required parameter currency was null or undefined when calling privateCancelAllByCurrencyGet.');
        }

        if (currency !== undefined) {
            localVarQueryParameters['currency'] = ObjectSerializer.serialize(currency, "'BTC' | 'ETH'");
        }

        if (kind !== undefined) {
            localVarQueryParameters['kind'] = ObjectSerializer.serialize(kind, "'future' | 'option'");
        }

        if (type !== undefined) {
            localVarQueryParameters['type'] = ObjectSerializer.serialize(type, "'all' | 'limit' | 'stop'");
        }

        (<any>Object).assign(localVarHeaderParams, options.headers);

        let localVarUseFormData = false;

        let localVarRequestOptions: localVarRequest.Options = {
            method: 'GET',
            qs: localVarQueryParameters,
            headers: localVarHeaderParams,
            uri: localVarPath,
            useQuerystring: this._useQuerystring,
            json: true,
        };

        this.authentications.bearerAuth.applyToRequest(localVarRequestOptions);

        this.authentications.default.applyToRequest(localVarRequestOptions);

        if (Object.keys(localVarFormParams).length) {
            if (localVarUseFormData) {
                (<any>localVarRequestOptions).formData = localVarFormParams;
            } else {
                localVarRequestOptions.form = localVarFormParams;
            }
        }
        return new Promise<{ response: http.ClientResponse; body: object;  }>((resolve, reject) => {
            localVarRequest(localVarRequestOptions, (error, response, body) => {
                if (error) {
                    reject(error);
                } else {
                    body = ObjectSerializer.deserialize(body, "object");
                    if (response.statusCode && response.statusCode >= 200 && response.statusCode <= 299) {
                        resolve({ response: response, body: body });
                    } else {
                        reject({ response: response, body: body });
                    }
                }
            });
        });
    }
    /**
     * 
     * @summary Cancels all orders by instrument, optionally filtered by order type.
     * @param instrumentName Instrument name
     * @param type Order type - limit, stop or all, default - &#x60;all&#x60;
     */
    public async privateCancelAllByInstrumentGet (instrumentName: string, type?: 'all' | 'limit' | 'stop', options: {headers: {[name: string]: string}} = {headers: {}}) : Promise<{ response: http.ClientResponse; body: object;  }> {
        const localVarPath = this.basePath + '/private/cancel_all_by_instrument';
        let localVarQueryParameters: any = {};
        let localVarHeaderParams: any = (<any>Object).assign({}, this.defaultHeaders);
        let localVarFormParams: any = {};

        // verify required parameter 'instrumentName' is not null or undefined
        if (instrumentName === null || instrumentName === undefined) {
            throw new Error('Required parameter instrumentName was null or undefined when calling privateCancelAllByInstrumentGet.');
        }

        if (instrumentName !== undefined) {
            localVarQueryParameters['instrument_name'] = ObjectSerializer.serialize(instrumentName, "string");
        }

        if (type !== undefined) {
            localVarQueryParameters['type'] = ObjectSerializer.serialize(type, "'all' | 'limit' | 'stop'");
        }

        (<any>Object).assign(localVarHeaderParams, options.headers);

        let localVarUseFormData = false;

        let localVarRequestOptions: localVarRequest.Options = {
            method: 'GET',
            qs: localVarQueryParameters,
            headers: localVarHeaderParams,
            uri: localVarPath,
            useQuerystring: this._useQuerystring,
            json: true,
        };

        this.authentications.bearerAuth.applyToRequest(localVarRequestOptions);

        this.authentications.default.applyToRequest(localVarRequestOptions);

        if (Object.keys(localVarFormParams).length) {
            if (localVarUseFormData) {
                (<any>localVarRequestOptions).formData = localVarFormParams;
            } else {
                localVarRequestOptions.form = localVarFormParams;
            }
        }
        return new Promise<{ response: http.ClientResponse; body: object;  }>((resolve, reject) => {
            localVarRequest(localVarRequestOptions, (error, response, body) => {
                if (error) {
                    reject(error);
                } else {
                    body = ObjectSerializer.deserialize(body, "object");
                    if (response.statusCode && response.statusCode >= 200 && response.statusCode <= 299) {
                        resolve({ response: response, body: body });
                    } else {
                        reject({ response: response, body: body });
                    }
                }
            });
        });
    }
    /**
     * 
     * @summary This method cancels all users orders and stop orders within all currencies and instrument kinds.
     */
    public async privateCancelAllGet (options: {headers: {[name: string]: string}} = {headers: {}}) : Promise<{ response: http.ClientResponse; body: object;  }> {
        const localVarPath = this.basePath + '/private/cancel_all';
        let localVarQueryParameters: any = {};
        let localVarHeaderParams: any = (<any>Object).assign({}, this.defaultHeaders);
        let localVarFormParams: any = {};

        (<any>Object).assign(localVarHeaderParams, options.headers);

        let localVarUseFormData = false;

        let localVarRequestOptions: localVarRequest.Options = {
            method: 'GET',
            qs: localVarQueryParameters,
            headers: localVarHeaderParams,
            uri: localVarPath,
            useQuerystring: this._useQuerystring,
            json: true,
        };

        this.authentications.bearerAuth.applyToRequest(localVarRequestOptions);

        this.authentications.default.applyToRequest(localVarRequestOptions);

        if (Object.keys(localVarFormParams).length) {
            if (localVarUseFormData) {
                (<any>localVarRequestOptions).formData = localVarFormParams;
            } else {
                localVarRequestOptions.form = localVarFormParams;
            }
        }
        return new Promise<{ response: http.ClientResponse; body: object;  }>((resolve, reject) => {
            localVarRequest(localVarRequestOptions, (error, response, body) => {
                if (error) {
                    reject(error);
                } else {
                    body = ObjectSerializer.deserialize(body, "object");
                    if (response.statusCode && response.statusCode >= 200 && response.statusCode <= 299) {
                        resolve({ response: response, body: body });
                    } else {
                        reject({ response: response, body: body });
                    }
                }
            });
        });
    }
    /**
     * 
     * @summary Cancel an order, specified by order id
     * @param orderId The order id
     */
    public async privateCancelGet (orderId: string, options: {headers: {[name: string]: string}} = {headers: {}}) : Promise<{ response: http.ClientResponse; body: object;  }> {
        const localVarPath = this.basePath + '/private/cancel';
        let localVarQueryParameters: any = {};
        let localVarHeaderParams: any = (<any>Object).assign({}, this.defaultHeaders);
        let localVarFormParams: any = {};

        // verify required parameter 'orderId' is not null or undefined
        if (orderId === null || orderId === undefined) {
            throw new Error('Required parameter orderId was null or undefined when calling privateCancelGet.');
        }

        if (orderId !== undefined) {
            localVarQueryParameters['order_id'] = ObjectSerializer.serialize(orderId, "string");
        }

        (<any>Object).assign(localVarHeaderParams, options.headers);

        let localVarUseFormData = false;

        let localVarRequestOptions: localVarRequest.Options = {
            method: 'GET',
            qs: localVarQueryParameters,
            headers: localVarHeaderParams,
            uri: localVarPath,
            useQuerystring: this._useQuerystring,
            json: true,
        };

        this.authentications.bearerAuth.applyToRequest(localVarRequestOptions);

        this.authentications.default.applyToRequest(localVarRequestOptions);

        if (Object.keys(localVarFormParams).length) {
            if (localVarUseFormData) {
                (<any>localVarRequestOptions).formData = localVarFormParams;
            } else {
                localVarRequestOptions.form = localVarFormParams;
            }
        }
        return new Promise<{ response: http.ClientResponse; body: object;  }>((resolve, reject) => {
            localVarRequest(localVarRequestOptions, (error, response, body) => {
                if (error) {
                    reject(error);
                } else {
                    body = ObjectSerializer.deserialize(body, "object");
                    if (response.statusCode && response.statusCode >= 200 && response.statusCode <= 299) {
                        resolve({ response: response, body: body });
                    } else {
                        reject({ response: response, body: body });
                    }
                }
            });
        });
    }
    /**
     * 
     * @summary Cancel transfer
     * @param currency The currency symbol
     * @param id Id of transfer
     * @param tfa TFA code, required when TFA is enabled for current account
     */
    public async privateCancelTransferByIdGet (currency: 'BTC' | 'ETH', id: number, tfa?: string, options: {headers: {[name: string]: string}} = {headers: {}}) : Promise<{ response: http.ClientResponse; body: object;  }> {
        const localVarPath = this.basePath + '/private/cancel_transfer_by_id';
        let localVarQueryParameters: any = {};
        let localVarHeaderParams: any = (<any>Object).assign({}, this.defaultHeaders);
        let localVarFormParams: any = {};

        // verify required parameter 'currency' is not null or undefined
        if (currency === null || currency === undefined) {
            throw new Error('Required parameter currency was null or undefined when calling privateCancelTransferByIdGet.');
        }

        // verify required parameter 'id' is not null or undefined
        if (id === null || id === undefined) {
            throw new Error('Required parameter id was null or undefined when calling privateCancelTransferByIdGet.');
        }

        if (currency !== undefined) {
            localVarQueryParameters['currency'] = ObjectSerializer.serialize(currency, "'BTC' | 'ETH'");
        }

        if (id !== undefined) {
            localVarQueryParameters['id'] = ObjectSerializer.serialize(id, "number");
        }

        if (tfa !== undefined) {
            localVarQueryParameters['tfa'] = ObjectSerializer.serialize(tfa, "string");
        }

        (<any>Object).assign(localVarHeaderParams, options.headers);

        let localVarUseFormData = false;

        let localVarRequestOptions: localVarRequest.Options = {
            method: 'GET',
            qs: localVarQueryParameters,
            headers: localVarHeaderParams,
            uri: localVarPath,
            useQuerystring: this._useQuerystring,
            json: true,
        };

        this.authentications.bearerAuth.applyToRequest(localVarRequestOptions);

        this.authentications.default.applyToRequest(localVarRequestOptions);

        if (Object.keys(localVarFormParams).length) {
            if (localVarUseFormData) {
                (<any>localVarRequestOptions).formData = localVarFormParams;
            } else {
                localVarRequestOptions.form = localVarFormParams;
            }
        }
        return new Promise<{ response: http.ClientResponse; body: object;  }>((resolve, reject) => {
            localVarRequest(localVarRequestOptions, (error, response, body) => {
                if (error) {
                    reject(error);
                } else {
                    body = ObjectSerializer.deserialize(body, "object");
                    if (response.statusCode && response.statusCode >= 200 && response.statusCode <= 299) {
                        resolve({ response: response, body: body });
                    } else {
                        reject({ response: response, body: body });
                    }
                }
            });
        });
    }
    /**
     * 
     * @summary Cancels withdrawal request
     * @param currency The currency symbol
     * @param id The withdrawal id
     */
    public async privateCancelWithdrawalGet (currency: 'BTC' | 'ETH', id: number, options: {headers: {[name: string]: string}} = {headers: {}}) : Promise<{ response: http.ClientResponse; body: object;  }> {
        const localVarPath = this.basePath + '/private/cancel_withdrawal';
        let localVarQueryParameters: any = {};
        let localVarHeaderParams: any = (<any>Object).assign({}, this.defaultHeaders);
        let localVarFormParams: any = {};

        // verify required parameter 'currency' is not null or undefined
        if (currency === null || currency === undefined) {
            throw new Error('Required parameter currency was null or undefined when calling privateCancelWithdrawalGet.');
        }

        // verify required parameter 'id' is not null or undefined
        if (id === null || id === undefined) {
            throw new Error('Required parameter id was null or undefined when calling privateCancelWithdrawalGet.');
        }

        if (currency !== undefined) {
            localVarQueryParameters['currency'] = ObjectSerializer.serialize(currency, "'BTC' | 'ETH'");
        }

        if (id !== undefined) {
            localVarQueryParameters['id'] = ObjectSerializer.serialize(id, "number");
        }

        (<any>Object).assign(localVarHeaderParams, options.headers);

        let localVarUseFormData = false;

        let localVarRequestOptions: localVarRequest.Options = {
            method: 'GET',
            qs: localVarQueryParameters,
            headers: localVarHeaderParams,
            uri: localVarPath,
            useQuerystring: this._useQuerystring,
            json: true,
        };

        this.authentications.bearerAuth.applyToRequest(localVarRequestOptions);

        this.authentications.default.applyToRequest(localVarRequestOptions);

        if (Object.keys(localVarFormParams).length) {
            if (localVarUseFormData) {
                (<any>localVarRequestOptions).formData = localVarFormParams;
            } else {
                localVarRequestOptions.form = localVarFormParams;
            }
        }
        return new Promise<{ response: http.ClientResponse; body: object;  }>((resolve, reject) => {
            localVarRequest(localVarRequestOptions, (error, response, body) => {
                if (error) {
                    reject(error);
                } else {
                    body = ObjectSerializer.deserialize(body, "object");
                    if (response.statusCode && response.statusCode >= 200 && response.statusCode <= 299) {
                        resolve({ response: response, body: body });
                    } else {
                        reject({ response: response, body: body });
                    }
                }
            });
        });
    }
    /**
     * 
     * @summary Change the user name for a subaccount
     * @param sid The user id for the subaccount
     * @param name The new user name
     */
    public async privateChangeSubaccountNameGet (sid: number, name: string, options: {headers: {[name: string]: string}} = {headers: {}}) : Promise<{ response: http.ClientResponse; body: object;  }> {
        const localVarPath = this.basePath + '/private/change_subaccount_name';
        let localVarQueryParameters: any = {};
        let localVarHeaderParams: any = (<any>Object).assign({}, this.defaultHeaders);
        let localVarFormParams: any = {};

        // verify required parameter 'sid' is not null or undefined
        if (sid === null || sid === undefined) {
            throw new Error('Required parameter sid was null or undefined when calling privateChangeSubaccountNameGet.');
        }

        // verify required parameter 'name' is not null or undefined
        if (name === null || name === undefined) {
            throw new Error('Required parameter name was null or undefined when calling privateChangeSubaccountNameGet.');
        }

        if (sid !== undefined) {
            localVarQueryParameters['sid'] = ObjectSerializer.serialize(sid, "number");
        }

        if (name !== undefined) {
            localVarQueryParameters['name'] = ObjectSerializer.serialize(name, "string");
        }

        (<any>Object).assign(localVarHeaderParams, options.headers);

        let localVarUseFormData = false;

        let localVarRequestOptions: localVarRequest.Options = {
            method: 'GET',
            qs: localVarQueryParameters,
            headers: localVarHeaderParams,
            uri: localVarPath,
            useQuerystring: this._useQuerystring,
            json: true,
        };

        this.authentications.bearerAuth.applyToRequest(localVarRequestOptions);

        this.authentications.default.applyToRequest(localVarRequestOptions);

        if (Object.keys(localVarFormParams).length) {
            if (localVarUseFormData) {
                (<any>localVarRequestOptions).formData = localVarFormParams;
            } else {
                localVarRequestOptions.form = localVarFormParams;
            }
        }
        return new Promise<{ response: http.ClientResponse; body: object;  }>((resolve, reject) => {
            localVarRequest(localVarRequestOptions, (error, response, body) => {
                if (error) {
                    reject(error);
                } else {
                    body = ObjectSerializer.deserialize(body, "object");
                    if (response.statusCode && response.statusCode >= 200 && response.statusCode <= 299) {
                        resolve({ response: response, body: body });
                    } else {
                        reject({ response: response, body: body });
                    }
                }
            });
        });
    }
    /**
     * 
     * @summary Makes closing position reduce only order .
     * @param instrumentName Instrument name
     * @param type The order type
     * @param price Optional price for limit order.
     */
    public async privateClosePositionGet (instrumentName: string, type: 'limit' | 'market', price?: number, options: {headers: {[name: string]: string}} = {headers: {}}) : Promise<{ response: http.ClientResponse; body: object;  }> {
        const localVarPath = this.basePath + '/private/close_position';
        let localVarQueryParameters: any = {};
        let localVarHeaderParams: any = (<any>Object).assign({}, this.defaultHeaders);
        let localVarFormParams: any = {};

        // verify required parameter 'instrumentName' is not null or undefined
        if (instrumentName === null || instrumentName === undefined) {
            throw new Error('Required parameter instrumentName was null or undefined when calling privateClosePositionGet.');
        }

        // verify required parameter 'type' is not null or undefined
        if (type === null || type === undefined) {
            throw new Error('Required parameter type was null or undefined when calling privateClosePositionGet.');
        }

        if (instrumentName !== undefined) {
            localVarQueryParameters['instrument_name'] = ObjectSerializer.serialize(instrumentName, "string");
        }

        if (type !== undefined) {
            localVarQueryParameters['type'] = ObjectSerializer.serialize(type, "'limit' | 'market'");
        }

        if (price !== undefined) {
            localVarQueryParameters['price'] = ObjectSerializer.serialize(price, "number");
        }

        (<any>Object).assign(localVarHeaderParams, options.headers);

        let localVarUseFormData = false;

        let localVarRequestOptions: localVarRequest.Options = {
            method: 'GET',
            qs: localVarQueryParameters,
            headers: localVarHeaderParams,
            uri: localVarPath,
            useQuerystring: this._useQuerystring,
            json: true,
        };

        this.authentications.bearerAuth.applyToRequest(localVarRequestOptions);

        this.authentications.default.applyToRequest(localVarRequestOptions);

        if (Object.keys(localVarFormParams).length) {
            if (localVarUseFormData) {
                (<any>localVarRequestOptions).formData = localVarFormParams;
            } else {
                localVarRequestOptions.form = localVarFormParams;
            }
        }
        return new Promise<{ response: http.ClientResponse; body: object;  }>((resolve, reject) => {
            localVarRequest(localVarRequestOptions, (error, response, body) => {
                if (error) {
                    reject(error);
                } else {
                    body = ObjectSerializer.deserialize(body, "object");
                    if (response.statusCode && response.statusCode >= 200 && response.statusCode <= 299) {
                        resolve({ response: response, body: body });
                    } else {
                        reject({ response: response, body: body });
                    }
                }
            });
        });
    }
    /**
     * 
     * @summary Creates deposit address in currency
     * @param currency The currency symbol
     */
    public async privateCreateDepositAddressGet (currency: 'BTC' | 'ETH', options: {headers: {[name: string]: string}} = {headers: {}}) : Promise<{ response: http.ClientResponse; body: object;  }> {
        const localVarPath = this.basePath + '/private/create_deposit_address';
        let localVarQueryParameters: any = {};
        let localVarHeaderParams: any = (<any>Object).assign({}, this.defaultHeaders);
        let localVarFormParams: any = {};

        // verify required parameter 'currency' is not null or undefined
        if (currency === null || currency === undefined) {
            throw new Error('Required parameter currency was null or undefined when calling privateCreateDepositAddressGet.');
        }

        if (currency !== undefined) {
            localVarQueryParameters['currency'] = ObjectSerializer.serialize(currency, "'BTC' | 'ETH'");
        }

        (<any>Object).assign(localVarHeaderParams, options.headers);

        let localVarUseFormData = false;

        let localVarRequestOptions: localVarRequest.Options = {
            method: 'GET',
            qs: localVarQueryParameters,
            headers: localVarHeaderParams,
            uri: localVarPath,
            useQuerystring: this._useQuerystring,
            json: true,
        };

        this.authentications.bearerAuth.applyToRequest(localVarRequestOptions);

        this.authentications.default.applyToRequest(localVarRequestOptions);

        if (Object.keys(localVarFormParams).length) {
            if (localVarUseFormData) {
                (<any>localVarRequestOptions).formData = localVarFormParams;
            } else {
                localVarRequestOptions.form = localVarFormParams;
            }
        }
        return new Promise<{ response: http.ClientResponse; body: object;  }>((resolve, reject) => {
            localVarRequest(localVarRequestOptions, (error, response, body) => {
                if (error) {
                    reject(error);
                } else {
                    body = ObjectSerializer.deserialize(body, "object");
                    if (response.statusCode && response.statusCode >= 200 && response.statusCode <= 299) {
                        resolve({ response: response, body: body });
                    } else {
                        reject({ response: response, body: body });
                    }
                }
            });
        });
    }
    /**
     * 
     * @summary Create a new subaccount
     */
    public async privateCreateSubaccountGet (options: {headers: {[name: string]: string}} = {headers: {}}) : Promise<{ response: http.ClientResponse; body: object;  }> {
        const localVarPath = this.basePath + '/private/create_subaccount';
        let localVarQueryParameters: any = {};
        let localVarHeaderParams: any = (<any>Object).assign({}, this.defaultHeaders);
        let localVarFormParams: any = {};

        (<any>Object).assign(localVarHeaderParams, options.headers);

        let localVarUseFormData = false;

        let localVarRequestOptions: localVarRequest.Options = {
            method: 'GET',
            qs: localVarQueryParameters,
            headers: localVarHeaderParams,
            uri: localVarPath,
            useQuerystring: this._useQuerystring,
            json: true,
        };

        this.authentications.bearerAuth.applyToRequest(localVarRequestOptions);

        this.authentications.default.applyToRequest(localVarRequestOptions);

        if (Object.keys(localVarFormParams).length) {
            if (localVarUseFormData) {
                (<any>localVarRequestOptions).formData = localVarFormParams;
            } else {
                localVarRequestOptions.form = localVarFormParams;
            }
        }
        return new Promise<{ response: http.ClientResponse; body: object;  }>((resolve, reject) => {
            localVarRequest(localVarRequestOptions, (error, response, body) => {
                if (error) {
                    reject(error);
                } else {
                    body = ObjectSerializer.deserialize(body, "object");
                    if (response.statusCode && response.statusCode >= 200 && response.statusCode <= 299) {
                        resolve({ response: response, body: body });
                    } else {
                        reject({ response: response, body: body });
                    }
                }
            });
        });
    }
    /**
     * 
     * @summary Disable two factor authentication for a subaccount.
     * @param sid The user id for the subaccount
     */
    public async privateDisableTfaForSubaccountGet (sid: number, options: {headers: {[name: string]: string}} = {headers: {}}) : Promise<{ response: http.ClientResponse; body: object;  }> {
        const localVarPath = this.basePath + '/private/disable_tfa_for_subaccount';
        let localVarQueryParameters: any = {};
        let localVarHeaderParams: any = (<any>Object).assign({}, this.defaultHeaders);
        let localVarFormParams: any = {};

        // verify required parameter 'sid' is not null or undefined
        if (sid === null || sid === undefined) {
            throw new Error('Required parameter sid was null or undefined when calling privateDisableTfaForSubaccountGet.');
        }

        if (sid !== undefined) {
            localVarQueryParameters['sid'] = ObjectSerializer.serialize(sid, "number");
        }

        (<any>Object).assign(localVarHeaderParams, options.headers);

        let localVarUseFormData = false;

        let localVarRequestOptions: localVarRequest.Options = {
            method: 'GET',
            qs: localVarQueryParameters,
            headers: localVarHeaderParams,
            uri: localVarPath,
            useQuerystring: this._useQuerystring,
            json: true,
        };

        this.authentications.bearerAuth.applyToRequest(localVarRequestOptions);

        this.authentications.default.applyToRequest(localVarRequestOptions);

        if (Object.keys(localVarFormParams).length) {
            if (localVarUseFormData) {
                (<any>localVarRequestOptions).formData = localVarFormParams;
            } else {
                localVarRequestOptions.form = localVarFormParams;
            }
        }
        return new Promise<{ response: http.ClientResponse; body: object;  }>((resolve, reject) => {
            localVarRequest(localVarRequestOptions, (error, response, body) => {
                if (error) {
                    reject(error);
                } else {
                    body = ObjectSerializer.deserialize(body, "object");
                    if (response.statusCode && response.statusCode >= 200 && response.statusCode <= 299) {
                        resolve({ response: response, body: body });
                    } else {
                        reject({ response: response, body: body });
                    }
                }
            });
        });
    }
    /**
     * 
     * @summary Disables TFA with one time recovery code
     * @param password The password for the subaccount
     * @param code One time recovery code
     */
    public async privateDisableTfaWithRecoveryCodeGet (password: string, code: string, options: {headers: {[name: string]: string}} = {headers: {}}) : Promise<{ response: http.ClientResponse; body: object;  }> {
        const localVarPath = this.basePath + '/private/disable_tfa_with_recovery_code';
        let localVarQueryParameters: any = {};
        let localVarHeaderParams: any = (<any>Object).assign({}, this.defaultHeaders);
        let localVarFormParams: any = {};

        // verify required parameter 'password' is not null or undefined
        if (password === null || password === undefined) {
            throw new Error('Required parameter password was null or undefined when calling privateDisableTfaWithRecoveryCodeGet.');
        }

        // verify required parameter 'code' is not null or undefined
        if (code === null || code === undefined) {
            throw new Error('Required parameter code was null or undefined when calling privateDisableTfaWithRecoveryCodeGet.');
        }

        if (password !== undefined) {
            localVarQueryParameters['password'] = ObjectSerializer.serialize(password, "string");
        }

        if (code !== undefined) {
            localVarQueryParameters['code'] = ObjectSerializer.serialize(code, "string");
        }

        (<any>Object).assign(localVarHeaderParams, options.headers);

        let localVarUseFormData = false;

        let localVarRequestOptions: localVarRequest.Options = {
            method: 'GET',
            qs: localVarQueryParameters,
            headers: localVarHeaderParams,
            uri: localVarPath,
            useQuerystring: this._useQuerystring,
            json: true,
        };

        this.authentications.bearerAuth.applyToRequest(localVarRequestOptions);

        this.authentications.default.applyToRequest(localVarRequestOptions);

        if (Object.keys(localVarFormParams).length) {
            if (localVarUseFormData) {
                (<any>localVarRequestOptions).formData = localVarFormParams;
            } else {
                localVarRequestOptions.form = localVarFormParams;
            }
        }
        return new Promise<{ response: http.ClientResponse; body: object;  }>((resolve, reject) => {
            localVarRequest(localVarRequestOptions, (error, response, body) => {
                if (error) {
                    reject(error);
                } else {
                    body = ObjectSerializer.deserialize(body, "object");
                    if (response.statusCode && response.statusCode >= 200 && response.statusCode <= 299) {
                        resolve({ response: response, body: body });
                    } else {
                        reject({ response: response, body: body });
                    }
                }
            });
        });
    }
    /**
     * 
     * @summary Change price, amount and/or other properties of an order.
     * @param orderId The order id
     * @param amount It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH
     * @param price &lt;p&gt;The order price in base currency.&lt;/p&gt; &lt;p&gt;When editing an option order with advanced&#x3D;usd, the field price should be the option price value in USD.&lt;/p&gt; &lt;p&gt;When editing an option order with advanced&#x3D;implv, the field price should be a value of implied volatility in percentages. For example,  price&#x3D;100, means implied volatility of 100%&lt;/p&gt;
     * @param postOnly &lt;p&gt;If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.&lt;/p&gt; &lt;p&gt;Only valid in combination with time_in_force&#x3D;&#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt;
     * @param advanced Advanced option order type. If you have posted an advanced option order, it is necessary to re-supply this parameter when editing it (Only for options)
     * @param stopPrice Stop price, required for stop limit orders (Only for stop orders)
     */
    public async privateEditGet (orderId: string, amount: number, price: number, postOnly?: boolean, advanced?: 'usd' | 'implv', stopPrice?: number, options: {headers: {[name: string]: string}} = {headers: {}}) : Promise<{ response: http.ClientResponse; body: object;  }> {
        const localVarPath = this.basePath + '/private/edit';
        let localVarQueryParameters: any = {};
        let localVarHeaderParams: any = (<any>Object).assign({}, this.defaultHeaders);
        let localVarFormParams: any = {};

        // verify required parameter 'orderId' is not null or undefined
        if (orderId === null || orderId === undefined) {
            throw new Error('Required parameter orderId was null or undefined when calling privateEditGet.');
        }

        // verify required parameter 'amount' is not null or undefined
        if (amount === null || amount === undefined) {
            throw new Error('Required parameter amount was null or undefined when calling privateEditGet.');
        }

        // verify required parameter 'price' is not null or undefined
        if (price === null || price === undefined) {
            throw new Error('Required parameter price was null or undefined when calling privateEditGet.');
        }

        if (orderId !== undefined) {
            localVarQueryParameters['order_id'] = ObjectSerializer.serialize(orderId, "string");
        }

        if (amount !== undefined) {
            localVarQueryParameters['amount'] = ObjectSerializer.serialize(amount, "number");
        }

        if (price !== undefined) {
            localVarQueryParameters['price'] = ObjectSerializer.serialize(price, "number");
        }

        if (postOnly !== undefined) {
            localVarQueryParameters['post_only'] = ObjectSerializer.serialize(postOnly, "boolean");
        }

        if (advanced !== undefined) {
            localVarQueryParameters['advanced'] = ObjectSerializer.serialize(advanced, "'usd' | 'implv'");
        }

        if (stopPrice !== undefined) {
            localVarQueryParameters['stop_price'] = ObjectSerializer.serialize(stopPrice, "number");
        }

        (<any>Object).assign(localVarHeaderParams, options.headers);

        let localVarUseFormData = false;

        let localVarRequestOptions: localVarRequest.Options = {
            method: 'GET',
            qs: localVarQueryParameters,
            headers: localVarHeaderParams,
            uri: localVarPath,
            useQuerystring: this._useQuerystring,
            json: true,
        };

        this.authentications.bearerAuth.applyToRequest(localVarRequestOptions);

        this.authentications.default.applyToRequest(localVarRequestOptions);

        if (Object.keys(localVarFormParams).length) {
            if (localVarUseFormData) {
                (<any>localVarRequestOptions).formData = localVarFormParams;
            } else {
                localVarRequestOptions.form = localVarFormParams;
            }
        }
        return new Promise<{ response: http.ClientResponse; body: object;  }>((resolve, reject) => {
            localVarRequest(localVarRequestOptions, (error, response, body) => {
                if (error) {
                    reject(error);
                } else {
                    body = ObjectSerializer.deserialize(body, "object");
                    if (response.statusCode && response.statusCode >= 200 && response.statusCode <= 299) {
                        resolve({ response: response, body: body });
                    } else {
                        reject({ response: response, body: body });
                    }
                }
            });
        });
    }
    /**
     * 
     * @summary Retrieves user account summary.
     * @param currency The currency symbol
     * @param extended Include additional fields
     */
    public async privateGetAccountSummaryGet (currency: 'BTC' | 'ETH', extended?: boolean, options: {headers: {[name: string]: string}} = {headers: {}}) : Promise<{ response: http.ClientResponse; body: object;  }> {
        const localVarPath = this.basePath + '/private/get_account_summary';
        let localVarQueryParameters: any = {};
        let localVarHeaderParams: any = (<any>Object).assign({}, this.defaultHeaders);
        let localVarFormParams: any = {};

        // verify required parameter 'currency' is not null or undefined
        if (currency === null || currency === undefined) {
            throw new Error('Required parameter currency was null or undefined when calling privateGetAccountSummaryGet.');
        }

        if (currency !== undefined) {
            localVarQueryParameters['currency'] = ObjectSerializer.serialize(currency, "'BTC' | 'ETH'");
        }

        if (extended !== undefined) {
            localVarQueryParameters['extended'] = ObjectSerializer.serialize(extended, "boolean");
        }

        (<any>Object).assign(localVarHeaderParams, options.headers);

        let localVarUseFormData = false;

        let localVarRequestOptions: localVarRequest.Options = {
            method: 'GET',
            qs: localVarQueryParameters,
            headers: localVarHeaderParams,
            uri: localVarPath,
            useQuerystring: this._useQuerystring,
            json: true,
        };

        this.authentications.bearerAuth.applyToRequest(localVarRequestOptions);

        this.authentications.default.applyToRequest(localVarRequestOptions);

        if (Object.keys(localVarFormParams).length) {
            if (localVarUseFormData) {
                (<any>localVarRequestOptions).formData = localVarFormParams;
            } else {
                localVarRequestOptions.form = localVarFormParams;
            }
        }
        return new Promise<{ response: http.ClientResponse; body: object;  }>((resolve, reject) => {
            localVarRequest(localVarRequestOptions, (error, response, body) => {
                if (error) {
                    reject(error);
                } else {
                    body = ObjectSerializer.deserialize(body, "object");
                    if (response.statusCode && response.statusCode >= 200 && response.statusCode <= 299) {
                        resolve({ response: response, body: body });
                    } else {
                        reject({ response: response, body: body });
                    }
                }
            });
        });
    }
    /**
     * 
     * @summary Retrieves address book of given type
     * @param currency The currency symbol
     * @param type Address book type
     */
    public async privateGetAddressBookGet (currency: 'BTC' | 'ETH', type: 'transfer' | 'withdrawal', options: {headers: {[name: string]: string}} = {headers: {}}) : Promise<{ response: http.ClientResponse; body: object;  }> {
        const localVarPath = this.basePath + '/private/get_address_book';
        let localVarQueryParameters: any = {};
        let localVarHeaderParams: any = (<any>Object).assign({}, this.defaultHeaders);
        let localVarFormParams: any = {};

        // verify required parameter 'currency' is not null or undefined
        if (currency === null || currency === undefined) {
            throw new Error('Required parameter currency was null or undefined when calling privateGetAddressBookGet.');
        }

        // verify required parameter 'type' is not null or undefined
        if (type === null || type === undefined) {
            throw new Error('Required parameter type was null or undefined when calling privateGetAddressBookGet.');
        }

        if (currency !== undefined) {
            localVarQueryParameters['currency'] = ObjectSerializer.serialize(currency, "'BTC' | 'ETH'");
        }

        if (type !== undefined) {
            localVarQueryParameters['type'] = ObjectSerializer.serialize(type, "'transfer' | 'withdrawal'");
        }

        (<any>Object).assign(localVarHeaderParams, options.headers);

        let localVarUseFormData = false;

        let localVarRequestOptions: localVarRequest.Options = {
            method: 'GET',
            qs: localVarQueryParameters,
            headers: localVarHeaderParams,
            uri: localVarPath,
            useQuerystring: this._useQuerystring,
            json: true,
        };

        this.authentications.bearerAuth.applyToRequest(localVarRequestOptions);

        this.authentications.default.applyToRequest(localVarRequestOptions);

        if (Object.keys(localVarFormParams).length) {
            if (localVarUseFormData) {
                (<any>localVarRequestOptions).formData = localVarFormParams;
            } else {
                localVarRequestOptions.form = localVarFormParams;
            }
        }
        return new Promise<{ response: http.ClientResponse; body: object;  }>((resolve, reject) => {
            localVarRequest(localVarRequestOptions, (error, response, body) => {
                if (error) {
                    reject(error);
                } else {
                    body = ObjectSerializer.deserialize(body, "object");
                    if (response.statusCode && response.statusCode >= 200 && response.statusCode <= 299) {
                        resolve({ response: response, body: body });
                    } else {
                        reject({ response: response, body: body });
                    }
                }
            });
        });
    }
    /**
     * 
     * @summary Retrieve deposit address for currency
     * @param currency The currency symbol
     */
    public async privateGetCurrentDepositAddressGet (currency: 'BTC' | 'ETH', options: {headers: {[name: string]: string}} = {headers: {}}) : Promise<{ response: http.ClientResponse; body: object;  }> {
        const localVarPath = this.basePath + '/private/get_current_deposit_address';
        let localVarQueryParameters: any = {};
        let localVarHeaderParams: any = (<any>Object).assign({}, this.defaultHeaders);
        let localVarFormParams: any = {};

        // verify required parameter 'currency' is not null or undefined
        if (currency === null || currency === undefined) {
            throw new Error('Required parameter currency was null or undefined when calling privateGetCurrentDepositAddressGet.');
        }

        if (currency !== undefined) {
            localVarQueryParameters['currency'] = ObjectSerializer.serialize(currency, "'BTC' | 'ETH'");
        }

        (<any>Object).assign(localVarHeaderParams, options.headers);

        let localVarUseFormData = false;

        let localVarRequestOptions: localVarRequest.Options = {
            method: 'GET',
            qs: localVarQueryParameters,
            headers: localVarHeaderParams,
            uri: localVarPath,
            useQuerystring: this._useQuerystring,
            json: true,
        };

        this.authentications.bearerAuth.applyToRequest(localVarRequestOptions);

        this.authentications.default.applyToRequest(localVarRequestOptions);

        if (Object.keys(localVarFormParams).length) {
            if (localVarUseFormData) {
                (<any>localVarRequestOptions).formData = localVarFormParams;
            } else {
                localVarRequestOptions.form = localVarFormParams;
            }
        }
        return new Promise<{ response: http.ClientResponse; body: object;  }>((resolve, reject) => {
            localVarRequest(localVarRequestOptions, (error, response, body) => {
                if (error) {
                    reject(error);
                } else {
                    body = ObjectSerializer.deserialize(body, "object");
                    if (response.statusCode && response.statusCode >= 200 && response.statusCode <= 299) {
                        resolve({ response: response, body: body });
                    } else {
                        reject({ response: response, body: body });
                    }
                }
            });
        });
    }
    /**
     * 
     * @summary Retrieve the latest users deposits
     * @param currency The currency symbol
     * @param count Number of requested items, default - &#x60;10&#x60;
     * @param offset The offset for pagination, default - &#x60;0&#x60;
     */
    public async privateGetDepositsGet (currency: 'BTC' | 'ETH', count?: number, offset?: number, options: {headers: {[name: string]: string}} = {headers: {}}) : Promise<{ response: http.ClientResponse; body: object;  }> {
        const localVarPath = this.basePath + '/private/get_deposits';
        let localVarQueryParameters: any = {};
        let localVarHeaderParams: any = (<any>Object).assign({}, this.defaultHeaders);
        let localVarFormParams: any = {};

        // verify required parameter 'currency' is not null or undefined
        if (currency === null || currency === undefined) {
            throw new Error('Required parameter currency was null or undefined when calling privateGetDepositsGet.');
        }

        if (currency !== undefined) {
            localVarQueryParameters['currency'] = ObjectSerializer.serialize(currency, "'BTC' | 'ETH'");
        }

        if (count !== undefined) {
            localVarQueryParameters['count'] = ObjectSerializer.serialize(count, "number");
        }

        if (offset !== undefined) {
            localVarQueryParameters['offset'] = ObjectSerializer.serialize(offset, "number");
        }

        (<any>Object).assign(localVarHeaderParams, options.headers);

        let localVarUseFormData = false;

        let localVarRequestOptions: localVarRequest.Options = {
            method: 'GET',
            qs: localVarQueryParameters,
            headers: localVarHeaderParams,
            uri: localVarPath,
            useQuerystring: this._useQuerystring,
            json: true,
        };

        this.authentications.bearerAuth.applyToRequest(localVarRequestOptions);

        this.authentications.default.applyToRequest(localVarRequestOptions);

        if (Object.keys(localVarFormParams).length) {
            if (localVarUseFormData) {
                (<any>localVarRequestOptions).formData = localVarFormParams;
            } else {
                localVarRequestOptions.form = localVarFormParams;
            }
        }
        return new Promise<{ response: http.ClientResponse; body: object;  }>((resolve, reject) => {
            localVarRequest(localVarRequestOptions, (error, response, body) => {
                if (error) {
                    reject(error);
                } else {
                    body = ObjectSerializer.deserialize(body, "object");
                    if (response.statusCode && response.statusCode >= 200 && response.statusCode <= 299) {
                        resolve({ response: response, body: body });
                    } else {
                        reject({ response: response, body: body });
                    }
                }
            });
        });
    }
    /**
     * 
     * @summary Retrieves the language to be used for emails.
     */
    public async privateGetEmailLanguageGet (options: {headers: {[name: string]: string}} = {headers: {}}) : Promise<{ response: http.ClientResponse; body: object;  }> {
        const localVarPath = this.basePath + '/private/get_email_language';
        let localVarQueryParameters: any = {};
        let localVarHeaderParams: any = (<any>Object).assign({}, this.defaultHeaders);
        let localVarFormParams: any = {};

        (<any>Object).assign(localVarHeaderParams, options.headers);

        let localVarUseFormData = false;

        let localVarRequestOptions: localVarRequest.Options = {
            method: 'GET',
            qs: localVarQueryParameters,
            headers: localVarHeaderParams,
            uri: localVarPath,
            useQuerystring: this._useQuerystring,
            json: true,
        };

        this.authentications.bearerAuth.applyToRequest(localVarRequestOptions);

        this.authentications.default.applyToRequest(localVarRequestOptions);

        if (Object.keys(localVarFormParams).length) {
            if (localVarUseFormData) {
                (<any>localVarRequestOptions).formData = localVarFormParams;
            } else {
                localVarRequestOptions.form = localVarFormParams;
            }
        }
        return new Promise<{ response: http.ClientResponse; body: object;  }>((resolve, reject) => {
            localVarRequest(localVarRequestOptions, (error, response, body) => {
                if (error) {
                    reject(error);
                } else {
                    body = ObjectSerializer.deserialize(body, "object");
                    if (response.statusCode && response.statusCode >= 200 && response.statusCode <= 299) {
                        resolve({ response: response, body: body });
                    } else {
                        reject({ response: response, body: body });
                    }
                }
            });
        });
    }
    /**
     * 
     * @summary Get margins for given instrument, amount and price.
     * @param instrumentName Instrument name
     * @param amount Amount, integer for future, float for option. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH.
     * @param price Price
     */
    public async privateGetMarginsGet (instrumentName: string, amount: number, price: number, options: {headers: {[name: string]: string}} = {headers: {}}) : Promise<{ response: http.ClientResponse; body: object;  }> {
        const localVarPath = this.basePath + '/private/get_margins';
        let localVarQueryParameters: any = {};
        let localVarHeaderParams: any = (<any>Object).assign({}, this.defaultHeaders);
        let localVarFormParams: any = {};

        // verify required parameter 'instrumentName' is not null or undefined
        if (instrumentName === null || instrumentName === undefined) {
            throw new Error('Required parameter instrumentName was null or undefined when calling privateGetMarginsGet.');
        }

        // verify required parameter 'amount' is not null or undefined
        if (amount === null || amount === undefined) {
            throw new Error('Required parameter amount was null or undefined when calling privateGetMarginsGet.');
        }

        // verify required parameter 'price' is not null or undefined
        if (price === null || price === undefined) {
            throw new Error('Required parameter price was null or undefined when calling privateGetMarginsGet.');
        }

        if (instrumentName !== undefined) {
            localVarQueryParameters['instrument_name'] = ObjectSerializer.serialize(instrumentName, "string");
        }

        if (amount !== undefined) {
            localVarQueryParameters['amount'] = ObjectSerializer.serialize(amount, "number");
        }

        if (price !== undefined) {
            localVarQueryParameters['price'] = ObjectSerializer.serialize(price, "number");
        }

        (<any>Object).assign(localVarHeaderParams, options.headers);

        let localVarUseFormData = false;

        let localVarRequestOptions: localVarRequest.Options = {
            method: 'GET',
            qs: localVarQueryParameters,
            headers: localVarHeaderParams,
            uri: localVarPath,
            useQuerystring: this._useQuerystring,
            json: true,
        };

        this.authentications.bearerAuth.applyToRequest(localVarRequestOptions);

        this.authentications.default.applyToRequest(localVarRequestOptions);

        if (Object.keys(localVarFormParams).length) {
            if (localVarUseFormData) {
                (<any>localVarRequestOptions).formData = localVarFormParams;
            } else {
                localVarRequestOptions.form = localVarFormParams;
            }
        }
        return new Promise<{ response: http.ClientResponse; body: object;  }>((resolve, reject) => {
            localVarRequest(localVarRequestOptions, (error, response, body) => {
                if (error) {
                    reject(error);
                } else {
                    body = ObjectSerializer.deserialize(body, "object");
                    if (response.statusCode && response.statusCode >= 200 && response.statusCode <= 299) {
                        resolve({ response: response, body: body });
                    } else {
                        reject({ response: response, body: body });
                    }
                }
            });
        });
    }
    /**
     * 
     * @summary Retrieves announcements that have not been marked read by the user.
     */
    public async privateGetNewAnnouncementsGet (options: {headers: {[name: string]: string}} = {headers: {}}) : Promise<{ response: http.ClientResponse; body: object;  }> {
        const localVarPath = this.basePath + '/private/get_new_announcements';
        let localVarQueryParameters: any = {};
        let localVarHeaderParams: any = (<any>Object).assign({}, this.defaultHeaders);
        let localVarFormParams: any = {};

        (<any>Object).assign(localVarHeaderParams, options.headers);

        let localVarUseFormData = false;

        let localVarRequestOptions: localVarRequest.Options = {
            method: 'GET',
            qs: localVarQueryParameters,
            headers: localVarHeaderParams,
            uri: localVarPath,
            useQuerystring: this._useQuerystring,
            json: true,
        };

        this.authentications.bearerAuth.applyToRequest(localVarRequestOptions);

        this.authentications.default.applyToRequest(localVarRequestOptions);

        if (Object.keys(localVarFormParams).length) {
            if (localVarUseFormData) {
                (<any>localVarRequestOptions).formData = localVarFormParams;
            } else {
                localVarRequestOptions.form = localVarFormParams;
            }
        }
        return new Promise<{ response: http.ClientResponse; body: object;  }>((resolve, reject) => {
            localVarRequest(localVarRequestOptions, (error, response, body) => {
                if (error) {
                    reject(error);
                } else {
                    body = ObjectSerializer.deserialize(body, "object");
                    if (response.statusCode && response.statusCode >= 200 && response.statusCode <= 299) {
                        resolve({ response: response, body: body });
                    } else {
                        reject({ response: response, body: body });
                    }
                }
            });
        });
    }
    /**
     * 
     * @summary Retrieves list of user\'s open orders.
     * @param currency The currency symbol
     * @param kind Instrument kind, if not provided instruments of all kinds are considered
     * @param type Order type, default - &#x60;all&#x60;
     */
    public async privateGetOpenOrdersByCurrencyGet (currency: 'BTC' | 'ETH', kind?: 'future' | 'option', type?: 'all' | 'limit' | 'stop_all' | 'stop_limit' | 'stop_market', options: {headers: {[name: string]: string}} = {headers: {}}) : Promise<{ response: http.ClientResponse; body: object;  }> {
        const localVarPath = this.basePath + '/private/get_open_orders_by_currency';
        let localVarQueryParameters: any = {};
        let localVarHeaderParams: any = (<any>Object).assign({}, this.defaultHeaders);
        let localVarFormParams: any = {};

        // verify required parameter 'currency' is not null or undefined
        if (currency === null || currency === undefined) {
            throw new Error('Required parameter currency was null or undefined when calling privateGetOpenOrdersByCurrencyGet.');
        }

        if (currency !== undefined) {
            localVarQueryParameters['currency'] = ObjectSerializer.serialize(currency, "'BTC' | 'ETH'");
        }

        if (kind !== undefined) {
            localVarQueryParameters['kind'] = ObjectSerializer.serialize(kind, "'future' | 'option'");
        }

        if (type !== undefined) {
            localVarQueryParameters['type'] = ObjectSerializer.serialize(type, "'all' | 'limit' | 'stop_all' | 'stop_limit' | 'stop_market'");
        }

        (<any>Object).assign(localVarHeaderParams, options.headers);

        let localVarUseFormData = false;

        let localVarRequestOptions: localVarRequest.Options = {
            method: 'GET',
            qs: localVarQueryParameters,
            headers: localVarHeaderParams,
            uri: localVarPath,
            useQuerystring: this._useQuerystring,
            json: true,
        };

        this.authentications.bearerAuth.applyToRequest(localVarRequestOptions);

        this.authentications.default.applyToRequest(localVarRequestOptions);

        if (Object.keys(localVarFormParams).length) {
            if (localVarUseFormData) {
                (<any>localVarRequestOptions).formData = localVarFormParams;
            } else {
                localVarRequestOptions.form = localVarFormParams;
            }
        }
        return new Promise<{ response: http.ClientResponse; body: object;  }>((resolve, reject) => {
            localVarRequest(localVarRequestOptions, (error, response, body) => {
                if (error) {
                    reject(error);
                } else {
                    body = ObjectSerializer.deserialize(body, "object");
                    if (response.statusCode && response.statusCode >= 200 && response.statusCode <= 299) {
                        resolve({ response: response, body: body });
                    } else {
                        reject({ response: response, body: body });
                    }
                }
            });
        });
    }
    /**
     * 
     * @summary Retrieves list of user\'s open orders within given Instrument.
     * @param instrumentName Instrument name
     * @param type Order type, default - &#x60;all&#x60;
     */
    public async privateGetOpenOrdersByInstrumentGet (instrumentName: string, type?: 'all' | 'limit' | 'stop_all' | 'stop_limit' | 'stop_market', options: {headers: {[name: string]: string}} = {headers: {}}) : Promise<{ response: http.ClientResponse; body: object;  }> {
        const localVarPath = this.basePath + '/private/get_open_orders_by_instrument';
        let localVarQueryParameters: any = {};
        let localVarHeaderParams: any = (<any>Object).assign({}, this.defaultHeaders);
        let localVarFormParams: any = {};

        // verify required parameter 'instrumentName' is not null or undefined
        if (instrumentName === null || instrumentName === undefined) {
            throw new Error('Required parameter instrumentName was null or undefined when calling privateGetOpenOrdersByInstrumentGet.');
        }

        if (instrumentName !== undefined) {
            localVarQueryParameters['instrument_name'] = ObjectSerializer.serialize(instrumentName, "string");
        }

        if (type !== undefined) {
            localVarQueryParameters['type'] = ObjectSerializer.serialize(type, "'all' | 'limit' | 'stop_all' | 'stop_limit' | 'stop_market'");
        }

        (<any>Object).assign(localVarHeaderParams, options.headers);

        let localVarUseFormData = false;

        let localVarRequestOptions: localVarRequest.Options = {
            method: 'GET',
            qs: localVarQueryParameters,
            headers: localVarHeaderParams,
            uri: localVarPath,
            useQuerystring: this._useQuerystring,
            json: true,
        };

        this.authentications.bearerAuth.applyToRequest(localVarRequestOptions);

        this.authentications.default.applyToRequest(localVarRequestOptions);

        if (Object.keys(localVarFormParams).length) {
            if (localVarUseFormData) {
                (<any>localVarRequestOptions).formData = localVarFormParams;
            } else {
                localVarRequestOptions.form = localVarFormParams;
            }
        }
        return new Promise<{ response: http.ClientResponse; body: object;  }>((resolve, reject) => {
            localVarRequest(localVarRequestOptions, (error, response, body) => {
                if (error) {
                    reject(error);
                } else {
                    body = ObjectSerializer.deserialize(body, "object");
                    if (response.statusCode && response.statusCode >= 200 && response.statusCode <= 299) {
                        resolve({ response: response, body: body });
                    } else {
                        reject({ response: response, body: body });
                    }
                }
            });
        });
    }
    /**
     * 
     * @summary Retrieves history of orders that have been partially or fully filled.
     * @param currency The currency symbol
     * @param kind Instrument kind, if not provided instruments of all kinds are considered
     * @param count Number of requested items, default - &#x60;20&#x60;
     * @param offset The offset for pagination, default - &#x60;0&#x60;
     * @param includeOld Include in result orders older than 2 days, default - &#x60;false&#x60;
     * @param includeUnfilled Include in result fully unfilled closed orders, default - &#x60;false&#x60;
     */
    public async privateGetOrderHistoryByCurrencyGet (currency: 'BTC' | 'ETH', kind?: 'future' | 'option', count?: number, offset?: number, includeOld?: boolean, includeUnfilled?: boolean, options: {headers: {[name: string]: string}} = {headers: {}}) : Promise<{ response: http.ClientResponse; body: object;  }> {
        const localVarPath = this.basePath + '/private/get_order_history_by_currency';
        let localVarQueryParameters: any = {};
        let localVarHeaderParams: any = (<any>Object).assign({}, this.defaultHeaders);
        let localVarFormParams: any = {};

        // verify required parameter 'currency' is not null or undefined
        if (currency === null || currency === undefined) {
            throw new Error('Required parameter currency was null or undefined when calling privateGetOrderHistoryByCurrencyGet.');
        }

        if (currency !== undefined) {
            localVarQueryParameters['currency'] = ObjectSerializer.serialize(currency, "'BTC' | 'ETH'");
        }

        if (kind !== undefined) {
            localVarQueryParameters['kind'] = ObjectSerializer.serialize(kind, "'future' | 'option'");
        }

        if (count !== undefined) {
            localVarQueryParameters['count'] = ObjectSerializer.serialize(count, "number");
        }

        if (offset !== undefined) {
            localVarQueryParameters['offset'] = ObjectSerializer.serialize(offset, "number");
        }

        if (includeOld !== undefined) {
            localVarQueryParameters['include_old'] = ObjectSerializer.serialize(includeOld, "boolean");
        }

        if (includeUnfilled !== undefined) {
            localVarQueryParameters['include_unfilled'] = ObjectSerializer.serialize(includeUnfilled, "boolean");
        }

        (<any>Object).assign(localVarHeaderParams, options.headers);

        let localVarUseFormData = false;

        let localVarRequestOptions: localVarRequest.Options = {
            method: 'GET',
            qs: localVarQueryParameters,
            headers: localVarHeaderParams,
            uri: localVarPath,
            useQuerystring: this._useQuerystring,
            json: true,
        };

        this.authentications.bearerAuth.applyToRequest(localVarRequestOptions);

        this.authentications.default.applyToRequest(localVarRequestOptions);

        if (Object.keys(localVarFormParams).length) {
            if (localVarUseFormData) {
                (<any>localVarRequestOptions).formData = localVarFormParams;
            } else {
                localVarRequestOptions.form = localVarFormParams;
            }
        }
        return new Promise<{ response: http.ClientResponse; body: object;  }>((resolve, reject) => {
            localVarRequest(localVarRequestOptions, (error, response, body) => {
                if (error) {
                    reject(error);
                } else {
                    body = ObjectSerializer.deserialize(body, "object");
                    if (response.statusCode && response.statusCode >= 200 && response.statusCode <= 299) {
                        resolve({ response: response, body: body });
                    } else {
                        reject({ response: response, body: body });
                    }
                }
            });
        });
    }
    /**
     * 
     * @summary Retrieves history of orders that have been partially or fully filled.
     * @param instrumentName Instrument name
     * @param count Number of requested items, default - &#x60;20&#x60;
     * @param offset The offset for pagination, default - &#x60;0&#x60;
     * @param includeOld Include in result orders older than 2 days, default - &#x60;false&#x60;
     * @param includeUnfilled Include in result fully unfilled closed orders, default - &#x60;false&#x60;
     */
    public async privateGetOrderHistoryByInstrumentGet (instrumentName: string, count?: number, offset?: number, includeOld?: boolean, includeUnfilled?: boolean, options: {headers: {[name: string]: string}} = {headers: {}}) : Promise<{ response: http.ClientResponse; body: object;  }> {
        const localVarPath = this.basePath + '/private/get_order_history_by_instrument';
        let localVarQueryParameters: any = {};
        let localVarHeaderParams: any = (<any>Object).assign({}, this.defaultHeaders);
        let localVarFormParams: any = {};

        // verify required parameter 'instrumentName' is not null or undefined
        if (instrumentName === null || instrumentName === undefined) {
            throw new Error('Required parameter instrumentName was null or undefined when calling privateGetOrderHistoryByInstrumentGet.');
        }

        if (instrumentName !== undefined) {
            localVarQueryParameters['instrument_name'] = ObjectSerializer.serialize(instrumentName, "string");
        }

        if (count !== undefined) {
            localVarQueryParameters['count'] = ObjectSerializer.serialize(count, "number");
        }

        if (offset !== undefined) {
            localVarQueryParameters['offset'] = ObjectSerializer.serialize(offset, "number");
        }

        if (includeOld !== undefined) {
            localVarQueryParameters['include_old'] = ObjectSerializer.serialize(includeOld, "boolean");
        }

        if (includeUnfilled !== undefined) {
            localVarQueryParameters['include_unfilled'] = ObjectSerializer.serialize(includeUnfilled, "boolean");
        }

        (<any>Object).assign(localVarHeaderParams, options.headers);

        let localVarUseFormData = false;

        let localVarRequestOptions: localVarRequest.Options = {
            method: 'GET',
            qs: localVarQueryParameters,
            headers: localVarHeaderParams,
            uri: localVarPath,
            useQuerystring: this._useQuerystring,
            json: true,
        };

        this.authentications.bearerAuth.applyToRequest(localVarRequestOptions);

        this.authentications.default.applyToRequest(localVarRequestOptions);

        if (Object.keys(localVarFormParams).length) {
            if (localVarUseFormData) {
                (<any>localVarRequestOptions).formData = localVarFormParams;
            } else {
                localVarRequestOptions.form = localVarFormParams;
            }
        }
        return new Promise<{ response: http.ClientResponse; body: object;  }>((resolve, reject) => {
            localVarRequest(localVarRequestOptions, (error, response, body) => {
                if (error) {
                    reject(error);
                } else {
                    body = ObjectSerializer.deserialize(body, "object");
                    if (response.statusCode && response.statusCode >= 200 && response.statusCode <= 299) {
                        resolve({ response: response, body: body });
                    } else {
                        reject({ response: response, body: body });
                    }
                }
            });
        });
    }
    /**
     * 
     * @summary Retrieves initial margins of given orders
     * @param ids Ids of orders
     */
    public async privateGetOrderMarginByIdsGet (ids: Array<string>, options: {headers: {[name: string]: string}} = {headers: {}}) : Promise<{ response: http.ClientResponse; body: object;  }> {
        const localVarPath = this.basePath + '/private/get_order_margin_by_ids';
        let localVarQueryParameters: any = {};
        let localVarHeaderParams: any = (<any>Object).assign({}, this.defaultHeaders);
        let localVarFormParams: any = {};

        // verify required parameter 'ids' is not null or undefined
        if (ids === null || ids === undefined) {
            throw new Error('Required parameter ids was null or undefined when calling privateGetOrderMarginByIdsGet.');
        }

        if (ids !== undefined) {
            localVarQueryParameters['ids'] = ObjectSerializer.serialize(ids, "Array<string>");
        }

        (<any>Object).assign(localVarHeaderParams, options.headers);

        let localVarUseFormData = false;

        let localVarRequestOptions: localVarRequest.Options = {
            method: 'GET',
            qs: localVarQueryParameters,
            headers: localVarHeaderParams,
            uri: localVarPath,
            useQuerystring: this._useQuerystring,
            json: true,
        };

        this.authentications.bearerAuth.applyToRequest(localVarRequestOptions);

        this.authentications.default.applyToRequest(localVarRequestOptions);

        if (Object.keys(localVarFormParams).length) {
            if (localVarUseFormData) {
                (<any>localVarRequestOptions).formData = localVarFormParams;
            } else {
                localVarRequestOptions.form = localVarFormParams;
            }
        }
        return new Promise<{ response: http.ClientResponse; body: object;  }>((resolve, reject) => {
            localVarRequest(localVarRequestOptions, (error, response, body) => {
                if (error) {
                    reject(error);
                } else {
                    body = ObjectSerializer.deserialize(body, "object");
                    if (response.statusCode && response.statusCode >= 200 && response.statusCode <= 299) {
                        resolve({ response: response, body: body });
                    } else {
                        reject({ response: response, body: body });
                    }
                }
            });
        });
    }
    /**
     * 
     * @summary Retrieve the current state of an order.
     * @param orderId The order id
     */
    public async privateGetOrderStateGet (orderId: string, options: {headers: {[name: string]: string}} = {headers: {}}) : Promise<{ response: http.ClientResponse; body: object;  }> {
        const localVarPath = this.basePath + '/private/get_order_state';
        let localVarQueryParameters: any = {};
        let localVarHeaderParams: any = (<any>Object).assign({}, this.defaultHeaders);
        let localVarFormParams: any = {};

        // verify required parameter 'orderId' is not null or undefined
        if (orderId === null || orderId === undefined) {
            throw new Error('Required parameter orderId was null or undefined when calling privateGetOrderStateGet.');
        }

        if (orderId !== undefined) {
            localVarQueryParameters['order_id'] = ObjectSerializer.serialize(orderId, "string");
        }

        (<any>Object).assign(localVarHeaderParams, options.headers);

        let localVarUseFormData = false;

        let localVarRequestOptions: localVarRequest.Options = {
            method: 'GET',
            qs: localVarQueryParameters,
            headers: localVarHeaderParams,
            uri: localVarPath,
            useQuerystring: this._useQuerystring,
            json: true,
        };

        this.authentications.bearerAuth.applyToRequest(localVarRequestOptions);

        this.authentications.default.applyToRequest(localVarRequestOptions);

        if (Object.keys(localVarFormParams).length) {
            if (localVarUseFormData) {
                (<any>localVarRequestOptions).formData = localVarFormParams;
            } else {
                localVarRequestOptions.form = localVarFormParams;
            }
        }
        return new Promise<{ response: http.ClientResponse; body: object;  }>((resolve, reject) => {
            localVarRequest(localVarRequestOptions, (error, response, body) => {
                if (error) {
                    reject(error);
                } else {
                    body = ObjectSerializer.deserialize(body, "object");
                    if (response.statusCode && response.statusCode >= 200 && response.statusCode <= 299) {
                        resolve({ response: response, body: body });
                    } else {
                        reject({ response: response, body: body });
                    }
                }
            });
        });
    }
    /**
     * 
     * @summary Retrieve user position.
     * @param instrumentName Instrument name
     */
    public async privateGetPositionGet (instrumentName: string, options: {headers: {[name: string]: string}} = {headers: {}}) : Promise<{ response: http.ClientResponse; body: object;  }> {
        const localVarPath = this.basePath + '/private/get_position';
        let localVarQueryParameters: any = {};
        let localVarHeaderParams: any = (<any>Object).assign({}, this.defaultHeaders);
        let localVarFormParams: any = {};

        // verify required parameter 'instrumentName' is not null or undefined
        if (instrumentName === null || instrumentName === undefined) {
            throw new Error('Required parameter instrumentName was null or undefined when calling privateGetPositionGet.');
        }

        if (instrumentName !== undefined) {
            localVarQueryParameters['instrument_name'] = ObjectSerializer.serialize(instrumentName, "string");
        }

        (<any>Object).assign(localVarHeaderParams, options.headers);

        let localVarUseFormData = false;

        let localVarRequestOptions: localVarRequest.Options = {
            method: 'GET',
            qs: localVarQueryParameters,
            headers: localVarHeaderParams,
            uri: localVarPath,
            useQuerystring: this._useQuerystring,
            json: true,
        };

        this.authentications.bearerAuth.applyToRequest(localVarRequestOptions);

        this.authentications.default.applyToRequest(localVarRequestOptions);

        if (Object.keys(localVarFormParams).length) {
            if (localVarUseFormData) {
                (<any>localVarRequestOptions).formData = localVarFormParams;
            } else {
                localVarRequestOptions.form = localVarFormParams;
            }
        }
        return new Promise<{ response: http.ClientResponse; body: object;  }>((resolve, reject) => {
            localVarRequest(localVarRequestOptions, (error, response, body) => {
                if (error) {
                    reject(error);
                } else {
                    body = ObjectSerializer.deserialize(body, "object");
                    if (response.statusCode && response.statusCode >= 200 && response.statusCode <= 299) {
                        resolve({ response: response, body: body });
                    } else {
                        reject({ response: response, body: body });
                    }
                }
            });
        });
    }
    /**
     * 
     * @summary Retrieve user positions.
     * @param currency 
     * @param kind Kind filter on positions
     */
    public async privateGetPositionsGet (currency: 'BTC' | 'ETH', kind?: 'future' | 'option', options: {headers: {[name: string]: string}} = {headers: {}}) : Promise<{ response: http.ClientResponse; body: object;  }> {
        const localVarPath = this.basePath + '/private/get_positions';
        let localVarQueryParameters: any = {};
        let localVarHeaderParams: any = (<any>Object).assign({}, this.defaultHeaders);
        let localVarFormParams: any = {};

        // verify required parameter 'currency' is not null or undefined
        if (currency === null || currency === undefined) {
            throw new Error('Required parameter currency was null or undefined when calling privateGetPositionsGet.');
        }

        if (currency !== undefined) {
            localVarQueryParameters['currency'] = ObjectSerializer.serialize(currency, "'BTC' | 'ETH'");
        }

        if (kind !== undefined) {
            localVarQueryParameters['kind'] = ObjectSerializer.serialize(kind, "'future' | 'option'");
        }

        (<any>Object).assign(localVarHeaderParams, options.headers);

        let localVarUseFormData = false;

        let localVarRequestOptions: localVarRequest.Options = {
            method: 'GET',
            qs: localVarQueryParameters,
            headers: localVarHeaderParams,
            uri: localVarPath,
            useQuerystring: this._useQuerystring,
            json: true,
        };

        this.authentications.bearerAuth.applyToRequest(localVarRequestOptions);

        this.authentications.default.applyToRequest(localVarRequestOptions);

        if (Object.keys(localVarFormParams).length) {
            if (localVarUseFormData) {
                (<any>localVarRequestOptions).formData = localVarFormParams;
            } else {
                localVarRequestOptions.form = localVarFormParams;
            }
        }
        return new Promise<{ response: http.ClientResponse; body: object;  }>((resolve, reject) => {
            localVarRequest(localVarRequestOptions, (error, response, body) => {
                if (error) {
                    reject(error);
                } else {
                    body = ObjectSerializer.deserialize(body, "object");
                    if (response.statusCode && response.statusCode >= 200 && response.statusCode <= 299) {
                        resolve({ response: response, body: body });
                    } else {
                        reject({ response: response, body: body });
                    }
                }
            });
        });
    }
    /**
     * 
     * @summary Retrieves settlement, delivery and bankruptcy events that have affected your account.
     * @param currency The currency symbol
     * @param type Settlement type
     * @param count Number of requested items, default - &#x60;20&#x60;
     */
    public async privateGetSettlementHistoryByCurrencyGet (currency: 'BTC' | 'ETH', type?: 'settlement' | 'delivery' | 'bankruptcy', count?: number, options: {headers: {[name: string]: string}} = {headers: {}}) : Promise<{ response: http.ClientResponse; body: object;  }> {
        const localVarPath = this.basePath + '/private/get_settlement_history_by_currency';
        let localVarQueryParameters: any = {};
        let localVarHeaderParams: any = (<any>Object).assign({}, this.defaultHeaders);
        let localVarFormParams: any = {};

        // verify required parameter 'currency' is not null or undefined
        if (currency === null || currency === undefined) {
            throw new Error('Required parameter currency was null or undefined when calling privateGetSettlementHistoryByCurrencyGet.');
        }

        if (currency !== undefined) {
            localVarQueryParameters['currency'] = ObjectSerializer.serialize(currency, "'BTC' | 'ETH'");
        }

        if (type !== undefined) {
            localVarQueryParameters['type'] = ObjectSerializer.serialize(type, "'settlement' | 'delivery' | 'bankruptcy'");
        }

        if (count !== undefined) {
            localVarQueryParameters['count'] = ObjectSerializer.serialize(count, "number");
        }

        (<any>Object).assign(localVarHeaderParams, options.headers);

        let localVarUseFormData = false;

        let localVarRequestOptions: localVarRequest.Options = {
            method: 'GET',
            qs: localVarQueryParameters,
            headers: localVarHeaderParams,
            uri: localVarPath,
            useQuerystring: this._useQuerystring,
            json: true,
        };

        this.authentications.bearerAuth.applyToRequest(localVarRequestOptions);

        this.authentications.default.applyToRequest(localVarRequestOptions);

        if (Object.keys(localVarFormParams).length) {
            if (localVarUseFormData) {
                (<any>localVarRequestOptions).formData = localVarFormParams;
            } else {
                localVarRequestOptions.form = localVarFormParams;
            }
        }
        return new Promise<{ response: http.ClientResponse; body: object;  }>((resolve, reject) => {
            localVarRequest(localVarRequestOptions, (error, response, body) => {
                if (error) {
                    reject(error);
                } else {
                    body = ObjectSerializer.deserialize(body, "object");
                    if (response.statusCode && response.statusCode >= 200 && response.statusCode <= 299) {
                        resolve({ response: response, body: body });
                    } else {
                        reject({ response: response, body: body });
                    }
                }
            });
        });
    }
    /**
     * 
     * @summary Retrieves public settlement, delivery and bankruptcy events filtered by instrument name
     * @param instrumentName Instrument name
     * @param type Settlement type
     * @param count Number of requested items, default - &#x60;20&#x60;
     */
    public async privateGetSettlementHistoryByInstrumentGet (instrumentName: string, type?: 'settlement' | 'delivery' | 'bankruptcy', count?: number, options: {headers: {[name: string]: string}} = {headers: {}}) : Promise<{ response: http.ClientResponse; body: object;  }> {
        const localVarPath = this.basePath + '/private/get_settlement_history_by_instrument';
        let localVarQueryParameters: any = {};
        let localVarHeaderParams: any = (<any>Object).assign({}, this.defaultHeaders);
        let localVarFormParams: any = {};

        // verify required parameter 'instrumentName' is not null or undefined
        if (instrumentName === null || instrumentName === undefined) {
            throw new Error('Required parameter instrumentName was null or undefined when calling privateGetSettlementHistoryByInstrumentGet.');
        }

        if (instrumentName !== undefined) {
            localVarQueryParameters['instrument_name'] = ObjectSerializer.serialize(instrumentName, "string");
        }

        if (type !== undefined) {
            localVarQueryParameters['type'] = ObjectSerializer.serialize(type, "'settlement' | 'delivery' | 'bankruptcy'");
        }

        if (count !== undefined) {
            localVarQueryParameters['count'] = ObjectSerializer.serialize(count, "number");
        }

        (<any>Object).assign(localVarHeaderParams, options.headers);

        let localVarUseFormData = false;

        let localVarRequestOptions: localVarRequest.Options = {
            method: 'GET',
            qs: localVarQueryParameters,
            headers: localVarHeaderParams,
            uri: localVarPath,
            useQuerystring: this._useQuerystring,
            json: true,
        };

        this.authentications.bearerAuth.applyToRequest(localVarRequestOptions);

        this.authentications.default.applyToRequest(localVarRequestOptions);

        if (Object.keys(localVarFormParams).length) {
            if (localVarUseFormData) {
                (<any>localVarRequestOptions).formData = localVarFormParams;
            } else {
                localVarRequestOptions.form = localVarFormParams;
            }
        }
        return new Promise<{ response: http.ClientResponse; body: object;  }>((resolve, reject) => {
            localVarRequest(localVarRequestOptions, (error, response, body) => {
                if (error) {
                    reject(error);
                } else {
                    body = ObjectSerializer.deserialize(body, "object");
                    if (response.statusCode && response.statusCode >= 200 && response.statusCode <= 299) {
                        resolve({ response: response, body: body });
                    } else {
                        reject({ response: response, body: body });
                    }
                }
            });
        });
    }
    /**
     * 
     * @summary Get information about subaccounts
     * @param withPortfolio 
     */
    public async privateGetSubaccountsGet (withPortfolio?: boolean, options: {headers: {[name: string]: string}} = {headers: {}}) : Promise<{ response: http.ClientResponse; body: object;  }> {
        const localVarPath = this.basePath + '/private/get_subaccounts';
        let localVarQueryParameters: any = {};
        let localVarHeaderParams: any = (<any>Object).assign({}, this.defaultHeaders);
        let localVarFormParams: any = {};

        if (withPortfolio !== undefined) {
            localVarQueryParameters['with_portfolio'] = ObjectSerializer.serialize(withPortfolio, "boolean");
        }

        (<any>Object).assign(localVarHeaderParams, options.headers);

        let localVarUseFormData = false;

        let localVarRequestOptions: localVarRequest.Options = {
            method: 'GET',
            qs: localVarQueryParameters,
            headers: localVarHeaderParams,
            uri: localVarPath,
            useQuerystring: this._useQuerystring,
            json: true,
        };

        this.authentications.bearerAuth.applyToRequest(localVarRequestOptions);

        this.authentications.default.applyToRequest(localVarRequestOptions);

        if (Object.keys(localVarFormParams).length) {
            if (localVarUseFormData) {
                (<any>localVarRequestOptions).formData = localVarFormParams;
            } else {
                localVarRequestOptions.form = localVarFormParams;
            }
        }
        return new Promise<{ response: http.ClientResponse; body: object;  }>((resolve, reject) => {
            localVarRequest(localVarRequestOptions, (error, response, body) => {
                if (error) {
                    reject(error);
                } else {
                    body = ObjectSerializer.deserialize(body, "object");
                    if (response.statusCode && response.statusCode >= 200 && response.statusCode <= 299) {
                        resolve({ response: response, body: body });
                    } else {
                        reject({ response: response, body: body });
                    }
                }
            });
        });
    }
    /**
     * 
     * @summary Adds new entry to address book of given type
     * @param currency The currency symbol
     * @param count Number of requested items, default - &#x60;10&#x60;
     * @param offset The offset for pagination, default - &#x60;0&#x60;
     */
    public async privateGetTransfersGet (currency: 'BTC' | 'ETH', count?: number, offset?: number, options: {headers: {[name: string]: string}} = {headers: {}}) : Promise<{ response: http.ClientResponse; body: object;  }> {
        const localVarPath = this.basePath + '/private/get_transfers';
        let localVarQueryParameters: any = {};
        let localVarHeaderParams: any = (<any>Object).assign({}, this.defaultHeaders);
        let localVarFormParams: any = {};

        // verify required parameter 'currency' is not null or undefined
        if (currency === null || currency === undefined) {
            throw new Error('Required parameter currency was null or undefined when calling privateGetTransfersGet.');
        }

        if (currency !== undefined) {
            localVarQueryParameters['currency'] = ObjectSerializer.serialize(currency, "'BTC' | 'ETH'");
        }

        if (count !== undefined) {
            localVarQueryParameters['count'] = ObjectSerializer.serialize(count, "number");
        }

        if (offset !== undefined) {
            localVarQueryParameters['offset'] = ObjectSerializer.serialize(offset, "number");
        }

        (<any>Object).assign(localVarHeaderParams, options.headers);

        let localVarUseFormData = false;

        let localVarRequestOptions: localVarRequest.Options = {
            method: 'GET',
            qs: localVarQueryParameters,
            headers: localVarHeaderParams,
            uri: localVarPath,
            useQuerystring: this._useQuerystring,
            json: true,
        };

        this.authentications.bearerAuth.applyToRequest(localVarRequestOptions);

        this.authentications.default.applyToRequest(localVarRequestOptions);

        if (Object.keys(localVarFormParams).length) {
            if (localVarUseFormData) {
                (<any>localVarRequestOptions).formData = localVarFormParams;
            } else {
                localVarRequestOptions.form = localVarFormParams;
            }
        }
        return new Promise<{ response: http.ClientResponse; body: object;  }>((resolve, reject) => {
            localVarRequest(localVarRequestOptions, (error, response, body) => {
                if (error) {
                    reject(error);
                } else {
                    body = ObjectSerializer.deserialize(body, "object");
                    if (response.statusCode && response.statusCode >= 200 && response.statusCode <= 299) {
                        resolve({ response: response, body: body });
                    } else {
                        reject({ response: response, body: body });
                    }
                }
            });
        });
    }
    /**
     * 
     * @summary Retrieve the latest user trades that have occurred for instruments in a specific currency symbol and within given time range.
     * @param currency The currency symbol
     * @param startTimestamp The earliest timestamp to return result for
     * @param endTimestamp The most recent timestamp to return result for
     * @param kind Instrument kind, if not provided instruments of all kinds are considered
     * @param count Number of requested items, default - &#x60;10&#x60;
     * @param includeOld Include trades older than 7 days, default - &#x60;false&#x60;
     * @param sorting Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database)
     */
    public async privateGetUserTradesByCurrencyAndTimeGet (currency: 'BTC' | 'ETH', startTimestamp: number, endTimestamp: number, kind?: 'future' | 'option', count?: number, includeOld?: boolean, sorting?: 'asc' | 'desc' | 'default', options: {headers: {[name: string]: string}} = {headers: {}}) : Promise<{ response: http.ClientResponse; body: object;  }> {
        const localVarPath = this.basePath + '/private/get_user_trades_by_currency_and_time';
        let localVarQueryParameters: any = {};
        let localVarHeaderParams: any = (<any>Object).assign({}, this.defaultHeaders);
        let localVarFormParams: any = {};

        // verify required parameter 'currency' is not null or undefined
        if (currency === null || currency === undefined) {
            throw new Error('Required parameter currency was null or undefined when calling privateGetUserTradesByCurrencyAndTimeGet.');
        }

        // verify required parameter 'startTimestamp' is not null or undefined
        if (startTimestamp === null || startTimestamp === undefined) {
            throw new Error('Required parameter startTimestamp was null or undefined when calling privateGetUserTradesByCurrencyAndTimeGet.');
        }

        // verify required parameter 'endTimestamp' is not null or undefined
        if (endTimestamp === null || endTimestamp === undefined) {
            throw new Error('Required parameter endTimestamp was null or undefined when calling privateGetUserTradesByCurrencyAndTimeGet.');
        }

        if (currency !== undefined) {
            localVarQueryParameters['currency'] = ObjectSerializer.serialize(currency, "'BTC' | 'ETH'");
        }

        if (kind !== undefined) {
            localVarQueryParameters['kind'] = ObjectSerializer.serialize(kind, "'future' | 'option'");
        }

        if (startTimestamp !== undefined) {
            localVarQueryParameters['start_timestamp'] = ObjectSerializer.serialize(startTimestamp, "number");
        }

        if (endTimestamp !== undefined) {
            localVarQueryParameters['end_timestamp'] = ObjectSerializer.serialize(endTimestamp, "number");
        }

        if (count !== undefined) {
            localVarQueryParameters['count'] = ObjectSerializer.serialize(count, "number");
        }

        if (includeOld !== undefined) {
            localVarQueryParameters['include_old'] = ObjectSerializer.serialize(includeOld, "boolean");
        }

        if (sorting !== undefined) {
            localVarQueryParameters['sorting'] = ObjectSerializer.serialize(sorting, "'asc' | 'desc' | 'default'");
        }

        (<any>Object).assign(localVarHeaderParams, options.headers);

        let localVarUseFormData = false;

        let localVarRequestOptions: localVarRequest.Options = {
            method: 'GET',
            qs: localVarQueryParameters,
            headers: localVarHeaderParams,
            uri: localVarPath,
            useQuerystring: this._useQuerystring,
            json: true,
        };

        this.authentications.bearerAuth.applyToRequest(localVarRequestOptions);

        this.authentications.default.applyToRequest(localVarRequestOptions);

        if (Object.keys(localVarFormParams).length) {
            if (localVarUseFormData) {
                (<any>localVarRequestOptions).formData = localVarFormParams;
            } else {
                localVarRequestOptions.form = localVarFormParams;
            }
        }
        return new Promise<{ response: http.ClientResponse; body: object;  }>((resolve, reject) => {
            localVarRequest(localVarRequestOptions, (error, response, body) => {
                if (error) {
                    reject(error);
                } else {
                    body = ObjectSerializer.deserialize(body, "object");
                    if (response.statusCode && response.statusCode >= 200 && response.statusCode <= 299) {
                        resolve({ response: response, body: body });
                    } else {
                        reject({ response: response, body: body });
                    }
                }
            });
        });
    }
    /**
     * 
     * @summary Retrieve the latest user trades that have occurred for instruments in a specific currency symbol.
     * @param currency The currency symbol
     * @param kind Instrument kind, if not provided instruments of all kinds are considered
     * @param startId The ID number of the first trade to be returned
     * @param endId The ID number of the last trade to be returned
     * @param count Number of requested items, default - &#x60;10&#x60;
     * @param includeOld Include trades older than 7 days, default - &#x60;false&#x60;
     * @param sorting Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database)
     */
    public async privateGetUserTradesByCurrencyGet (currency: 'BTC' | 'ETH', kind?: 'future' | 'option', startId?: string, endId?: string, count?: number, includeOld?: boolean, sorting?: 'asc' | 'desc' | 'default', options: {headers: {[name: string]: string}} = {headers: {}}) : Promise<{ response: http.ClientResponse; body: object;  }> {
        const localVarPath = this.basePath + '/private/get_user_trades_by_currency';
        let localVarQueryParameters: any = {};
        let localVarHeaderParams: any = (<any>Object).assign({}, this.defaultHeaders);
        let localVarFormParams: any = {};

        // verify required parameter 'currency' is not null or undefined
        if (currency === null || currency === undefined) {
            throw new Error('Required parameter currency was null or undefined when calling privateGetUserTradesByCurrencyGet.');
        }

        if (currency !== undefined) {
            localVarQueryParameters['currency'] = ObjectSerializer.serialize(currency, "'BTC' | 'ETH'");
        }

        if (kind !== undefined) {
            localVarQueryParameters['kind'] = ObjectSerializer.serialize(kind, "'future' | 'option'");
        }

        if (startId !== undefined) {
            localVarQueryParameters['start_id'] = ObjectSerializer.serialize(startId, "string");
        }

        if (endId !== undefined) {
            localVarQueryParameters['end_id'] = ObjectSerializer.serialize(endId, "string");
        }

        if (count !== undefined) {
            localVarQueryParameters['count'] = ObjectSerializer.serialize(count, "number");
        }

        if (includeOld !== undefined) {
            localVarQueryParameters['include_old'] = ObjectSerializer.serialize(includeOld, "boolean");
        }

        if (sorting !== undefined) {
            localVarQueryParameters['sorting'] = ObjectSerializer.serialize(sorting, "'asc' | 'desc' | 'default'");
        }

        (<any>Object).assign(localVarHeaderParams, options.headers);

        let localVarUseFormData = false;

        let localVarRequestOptions: localVarRequest.Options = {
            method: 'GET',
            qs: localVarQueryParameters,
            headers: localVarHeaderParams,
            uri: localVarPath,
            useQuerystring: this._useQuerystring,
            json: true,
        };

        this.authentications.bearerAuth.applyToRequest(localVarRequestOptions);

        this.authentications.default.applyToRequest(localVarRequestOptions);

        if (Object.keys(localVarFormParams).length) {
            if (localVarUseFormData) {
                (<any>localVarRequestOptions).formData = localVarFormParams;
            } else {
                localVarRequestOptions.form = localVarFormParams;
            }
        }
        return new Promise<{ response: http.ClientResponse; body: object;  }>((resolve, reject) => {
            localVarRequest(localVarRequestOptions, (error, response, body) => {
                if (error) {
                    reject(error);
                } else {
                    body = ObjectSerializer.deserialize(body, "object");
                    if (response.statusCode && response.statusCode >= 200 && response.statusCode <= 299) {
                        resolve({ response: response, body: body });
                    } else {
                        reject({ response: response, body: body });
                    }
                }
            });
        });
    }
    /**
     * 
     * @summary Retrieve the latest user trades that have occurred for a specific instrument and within given time range.
     * @param instrumentName Instrument name
     * @param startTimestamp The earliest timestamp to return result for
     * @param endTimestamp The most recent timestamp to return result for
     * @param count Number of requested items, default - &#x60;10&#x60;
     * @param includeOld Include trades older than 7 days, default - &#x60;false&#x60;
     * @param sorting Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database)
     */
    public async privateGetUserTradesByInstrumentAndTimeGet (instrumentName: string, startTimestamp: number, endTimestamp: number, count?: number, includeOld?: boolean, sorting?: 'asc' | 'desc' | 'default', options: {headers: {[name: string]: string}} = {headers: {}}) : Promise<{ response: http.ClientResponse; body: object;  }> {
        const localVarPath = this.basePath + '/private/get_user_trades_by_instrument_and_time';
        let localVarQueryParameters: any = {};
        let localVarHeaderParams: any = (<any>Object).assign({}, this.defaultHeaders);
        let localVarFormParams: any = {};

        // verify required parameter 'instrumentName' is not null or undefined
        if (instrumentName === null || instrumentName === undefined) {
            throw new Error('Required parameter instrumentName was null or undefined when calling privateGetUserTradesByInstrumentAndTimeGet.');
        }

        // verify required parameter 'startTimestamp' is not null or undefined
        if (startTimestamp === null || startTimestamp === undefined) {
            throw new Error('Required parameter startTimestamp was null or undefined when calling privateGetUserTradesByInstrumentAndTimeGet.');
        }

        // verify required parameter 'endTimestamp' is not null or undefined
        if (endTimestamp === null || endTimestamp === undefined) {
            throw new Error('Required parameter endTimestamp was null or undefined when calling privateGetUserTradesByInstrumentAndTimeGet.');
        }

        if (instrumentName !== undefined) {
            localVarQueryParameters['instrument_name'] = ObjectSerializer.serialize(instrumentName, "string");
        }

        if (startTimestamp !== undefined) {
            localVarQueryParameters['start_timestamp'] = ObjectSerializer.serialize(startTimestamp, "number");
        }

        if (endTimestamp !== undefined) {
            localVarQueryParameters['end_timestamp'] = ObjectSerializer.serialize(endTimestamp, "number");
        }

        if (count !== undefined) {
            localVarQueryParameters['count'] = ObjectSerializer.serialize(count, "number");
        }

        if (includeOld !== undefined) {
            localVarQueryParameters['include_old'] = ObjectSerializer.serialize(includeOld, "boolean");
        }

        if (sorting !== undefined) {
            localVarQueryParameters['sorting'] = ObjectSerializer.serialize(sorting, "'asc' | 'desc' | 'default'");
        }

        (<any>Object).assign(localVarHeaderParams, options.headers);

        let localVarUseFormData = false;

        let localVarRequestOptions: localVarRequest.Options = {
            method: 'GET',
            qs: localVarQueryParameters,
            headers: localVarHeaderParams,
            uri: localVarPath,
            useQuerystring: this._useQuerystring,
            json: true,
        };

        this.authentications.bearerAuth.applyToRequest(localVarRequestOptions);

        this.authentications.default.applyToRequest(localVarRequestOptions);

        if (Object.keys(localVarFormParams).length) {
            if (localVarUseFormData) {
                (<any>localVarRequestOptions).formData = localVarFormParams;
            } else {
                localVarRequestOptions.form = localVarFormParams;
            }
        }
        return new Promise<{ response: http.ClientResponse; body: object;  }>((resolve, reject) => {
            localVarRequest(localVarRequestOptions, (error, response, body) => {
                if (error) {
                    reject(error);
                } else {
                    body = ObjectSerializer.deserialize(body, "object");
                    if (response.statusCode && response.statusCode >= 200 && response.statusCode <= 299) {
                        resolve({ response: response, body: body });
                    } else {
                        reject({ response: response, body: body });
                    }
                }
            });
        });
    }
    /**
     * 
     * @summary Retrieve the latest user trades that have occurred for a specific instrument.
     * @param instrumentName Instrument name
     * @param startSeq The sequence number of the first trade to be returned
     * @param endSeq The sequence number of the last trade to be returned
     * @param count Number of requested items, default - &#x60;10&#x60;
     * @param includeOld Include trades older than 7 days, default - &#x60;false&#x60;
     * @param sorting Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database)
     */
    public async privateGetUserTradesByInstrumentGet (instrumentName: string, startSeq?: number, endSeq?: number, count?: number, includeOld?: boolean, sorting?: 'asc' | 'desc' | 'default', options: {headers: {[name: string]: string}} = {headers: {}}) : Promise<{ response: http.ClientResponse; body: object;  }> {
        const localVarPath = this.basePath + '/private/get_user_trades_by_instrument';
        let localVarQueryParameters: any = {};
        let localVarHeaderParams: any = (<any>Object).assign({}, this.defaultHeaders);
        let localVarFormParams: any = {};

        // verify required parameter 'instrumentName' is not null or undefined
        if (instrumentName === null || instrumentName === undefined) {
            throw new Error('Required parameter instrumentName was null or undefined when calling privateGetUserTradesByInstrumentGet.');
        }

        if (instrumentName !== undefined) {
            localVarQueryParameters['instrument_name'] = ObjectSerializer.serialize(instrumentName, "string");
        }

        if (startSeq !== undefined) {
            localVarQueryParameters['start_seq'] = ObjectSerializer.serialize(startSeq, "number");
        }

        if (endSeq !== undefined) {
            localVarQueryParameters['end_seq'] = ObjectSerializer.serialize(endSeq, "number");
        }

        if (count !== undefined) {
            localVarQueryParameters['count'] = ObjectSerializer.serialize(count, "number");
        }

        if (includeOld !== undefined) {
            localVarQueryParameters['include_old'] = ObjectSerializer.serialize(includeOld, "boolean");
        }

        if (sorting !== undefined) {
            localVarQueryParameters['sorting'] = ObjectSerializer.serialize(sorting, "'asc' | 'desc' | 'default'");
        }

        (<any>Object).assign(localVarHeaderParams, options.headers);

        let localVarUseFormData = false;

        let localVarRequestOptions: localVarRequest.Options = {
            method: 'GET',
            qs: localVarQueryParameters,
            headers: localVarHeaderParams,
            uri: localVarPath,
            useQuerystring: this._useQuerystring,
            json: true,
        };

        this.authentications.bearerAuth.applyToRequest(localVarRequestOptions);

        this.authentications.default.applyToRequest(localVarRequestOptions);

        if (Object.keys(localVarFormParams).length) {
            if (localVarUseFormData) {
                (<any>localVarRequestOptions).formData = localVarFormParams;
            } else {
                localVarRequestOptions.form = localVarFormParams;
            }
        }
        return new Promise<{ response: http.ClientResponse; body: object;  }>((resolve, reject) => {
            localVarRequest(localVarRequestOptions, (error, response, body) => {
                if (error) {
                    reject(error);
                } else {
                    body = ObjectSerializer.deserialize(body, "object");
                    if (response.statusCode && response.statusCode >= 200 && response.statusCode <= 299) {
                        resolve({ response: response, body: body });
                    } else {
                        reject({ response: response, body: body });
                    }
                }
            });
        });
    }
    /**
     * 
     * @summary Retrieve the list of user trades that was created for given order
     * @param orderId The order id
     * @param sorting Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database)
     */
    public async privateGetUserTradesByOrderGet (orderId: string, sorting?: 'asc' | 'desc' | 'default', options: {headers: {[name: string]: string}} = {headers: {}}) : Promise<{ response: http.ClientResponse; body: object;  }> {
        const localVarPath = this.basePath + '/private/get_user_trades_by_order';
        let localVarQueryParameters: any = {};
        let localVarHeaderParams: any = (<any>Object).assign({}, this.defaultHeaders);
        let localVarFormParams: any = {};

        // verify required parameter 'orderId' is not null or undefined
        if (orderId === null || orderId === undefined) {
            throw new Error('Required parameter orderId was null or undefined when calling privateGetUserTradesByOrderGet.');
        }

        if (orderId !== undefined) {
            localVarQueryParameters['order_id'] = ObjectSerializer.serialize(orderId, "string");
        }

        if (sorting !== undefined) {
            localVarQueryParameters['sorting'] = ObjectSerializer.serialize(sorting, "'asc' | 'desc' | 'default'");
        }

        (<any>Object).assign(localVarHeaderParams, options.headers);

        let localVarUseFormData = false;

        let localVarRequestOptions: localVarRequest.Options = {
            method: 'GET',
            qs: localVarQueryParameters,
            headers: localVarHeaderParams,
            uri: localVarPath,
            useQuerystring: this._useQuerystring,
            json: true,
        };

        this.authentications.bearerAuth.applyToRequest(localVarRequestOptions);

        this.authentications.default.applyToRequest(localVarRequestOptions);

        if (Object.keys(localVarFormParams).length) {
            if (localVarUseFormData) {
                (<any>localVarRequestOptions).formData = localVarFormParams;
            } else {
                localVarRequestOptions.form = localVarFormParams;
            }
        }
        return new Promise<{ response: http.ClientResponse; body: object;  }>((resolve, reject) => {
            localVarRequest(localVarRequestOptions, (error, response, body) => {
                if (error) {
                    reject(error);
                } else {
                    body = ObjectSerializer.deserialize(body, "object");
                    if (response.statusCode && response.statusCode >= 200 && response.statusCode <= 299) {
                        resolve({ response: response, body: body });
                    } else {
                        reject({ response: response, body: body });
                    }
                }
            });
        });
    }
    /**
     * 
     * @summary Retrieve the latest users withdrawals
     * @param currency The currency symbol
     * @param count Number of requested items, default - &#x60;10&#x60;
     * @param offset The offset for pagination, default - &#x60;0&#x60;
     */
    public async privateGetWithdrawalsGet (currency: 'BTC' | 'ETH', count?: number, offset?: number, options: {headers: {[name: string]: string}} = {headers: {}}) : Promise<{ response: http.ClientResponse; body: object;  }> {
        const localVarPath = this.basePath + '/private/get_withdrawals';
        let localVarQueryParameters: any = {};
        let localVarHeaderParams: any = (<any>Object).assign({}, this.defaultHeaders);
        let localVarFormParams: any = {};

        // verify required parameter 'currency' is not null or undefined
        if (currency === null || currency === undefined) {
            throw new Error('Required parameter currency was null or undefined when calling privateGetWithdrawalsGet.');
        }

        if (currency !== undefined) {
            localVarQueryParameters['currency'] = ObjectSerializer.serialize(currency, "'BTC' | 'ETH'");
        }

        if (count !== undefined) {
            localVarQueryParameters['count'] = ObjectSerializer.serialize(count, "number");
        }

        if (offset !== undefined) {
            localVarQueryParameters['offset'] = ObjectSerializer.serialize(offset, "number");
        }

        (<any>Object).assign(localVarHeaderParams, options.headers);

        let localVarUseFormData = false;

        let localVarRequestOptions: localVarRequest.Options = {
            method: 'GET',
            qs: localVarQueryParameters,
            headers: localVarHeaderParams,
            uri: localVarPath,
            useQuerystring: this._useQuerystring,
            json: true,
        };

        this.authentications.bearerAuth.applyToRequest(localVarRequestOptions);

        this.authentications.default.applyToRequest(localVarRequestOptions);

        if (Object.keys(localVarFormParams).length) {
            if (localVarUseFormData) {
                (<any>localVarRequestOptions).formData = localVarFormParams;
            } else {
                localVarRequestOptions.form = localVarFormParams;
            }
        }
        return new Promise<{ response: http.ClientResponse; body: object;  }>((resolve, reject) => {
            localVarRequest(localVarRequestOptions, (error, response, body) => {
                if (error) {
                    reject(error);
                } else {
                    body = ObjectSerializer.deserialize(body, "object");
                    if (response.statusCode && response.statusCode >= 200 && response.statusCode <= 299) {
                        resolve({ response: response, body: body });
                    } else {
                        reject({ response: response, body: body });
                    }
                }
            });
        });
    }
    /**
     * 
     * @summary Adds new entry to address book of given type
     * @param currency The currency symbol
     * @param type Address book type
     * @param address Address in currency format, it must be in address book
     * @param tfa TFA code, required when TFA is enabled for current account
     */
    public async privateRemoveFromAddressBookGet (currency: 'BTC' | 'ETH', type: 'transfer' | 'withdrawal', address: string, tfa?: string, options: {headers: {[name: string]: string}} = {headers: {}}) : Promise<{ response: http.ClientResponse; body: object;  }> {
        const localVarPath = this.basePath + '/private/remove_from_address_book';
        let localVarQueryParameters: any = {};
        let localVarHeaderParams: any = (<any>Object).assign({}, this.defaultHeaders);
        let localVarFormParams: any = {};

        // verify required parameter 'currency' is not null or undefined
        if (currency === null || currency === undefined) {
            throw new Error('Required parameter currency was null or undefined when calling privateRemoveFromAddressBookGet.');
        }

        // verify required parameter 'type' is not null or undefined
        if (type === null || type === undefined) {
            throw new Error('Required parameter type was null or undefined when calling privateRemoveFromAddressBookGet.');
        }

        // verify required parameter 'address' is not null or undefined
        if (address === null || address === undefined) {
            throw new Error('Required parameter address was null or undefined when calling privateRemoveFromAddressBookGet.');
        }

        if (currency !== undefined) {
            localVarQueryParameters['currency'] = ObjectSerializer.serialize(currency, "'BTC' | 'ETH'");
        }

        if (type !== undefined) {
            localVarQueryParameters['type'] = ObjectSerializer.serialize(type, "'transfer' | 'withdrawal'");
        }

        if (address !== undefined) {
            localVarQueryParameters['address'] = ObjectSerializer.serialize(address, "string");
        }

        if (tfa !== undefined) {
            localVarQueryParameters['tfa'] = ObjectSerializer.serialize(tfa, "string");
        }

        (<any>Object).assign(localVarHeaderParams, options.headers);

        let localVarUseFormData = false;

        let localVarRequestOptions: localVarRequest.Options = {
            method: 'GET',
            qs: localVarQueryParameters,
            headers: localVarHeaderParams,
            uri: localVarPath,
            useQuerystring: this._useQuerystring,
            json: true,
        };

        this.authentications.bearerAuth.applyToRequest(localVarRequestOptions);

        this.authentications.default.applyToRequest(localVarRequestOptions);

        if (Object.keys(localVarFormParams).length) {
            if (localVarUseFormData) {
                (<any>localVarRequestOptions).formData = localVarFormParams;
            } else {
                localVarRequestOptions.form = localVarFormParams;
            }
        }
        return new Promise<{ response: http.ClientResponse; body: object;  }>((resolve, reject) => {
            localVarRequest(localVarRequestOptions, (error, response, body) => {
                if (error) {
                    reject(error);
                } else {
                    body = ObjectSerializer.deserialize(body, "object");
                    if (response.statusCode && response.statusCode >= 200 && response.statusCode <= 299) {
                        resolve({ response: response, body: body });
                    } else {
                        reject({ response: response, body: body });
                    }
                }
            });
        });
    }
    /**
     * 
     * @summary Places a sell order for an instrument.
     * @param instrumentName Instrument name
     * @param amount It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH
     * @param type The order type, default: &#x60;\&quot;limit\&quot;&#x60;
     * @param label user defined label for the order (maximum 32 characters)
     * @param price &lt;p&gt;The order price in base currency (Only for limit and stop_limit orders)&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;usd, the field price should be the option price value in USD.&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;implv, the field price should be a value of implied volatility in percentages. For example,  price&#x3D;100, means implied volatility of 100%&lt;/p&gt;
     * @param timeInForce &lt;p&gt;Specifies how long the order remains in effect. Default &#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; &lt;ul&gt; &lt;li&gt;&#x60;\&quot;good_til_cancelled\&quot;&#x60; - unfilled order remains in order book until cancelled&lt;/li&gt; &lt;li&gt;&#x60;\&quot;fill_or_kill\&quot;&#x60; - execute a transaction immediately and completely or not at all&lt;/li&gt; &lt;li&gt;&#x60;\&quot;immediate_or_cancel\&quot;&#x60; - execute a transaction immediately, and any portion of the order that cannot be immediately filled is cancelled&lt;/li&gt; &lt;/ul&gt;
     * @param maxShow Maximum amount within an order to be shown to other customers, &#x60;0&#x60; for invisible order
     * @param postOnly &lt;p&gt;If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.&lt;/p&gt; &lt;p&gt;Only valid in combination with time_in_force&#x3D;&#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt;
     * @param reduceOnly If &#x60;true&#x60;, the order is considered reduce-only which is intended to only reduce a current position
     * @param stopPrice Stop price, required for stop limit orders (Only for stop orders)
     * @param trigger Defines trigger type, required for &#x60;\&quot;stop_limit\&quot;&#x60; order type
     * @param advanced Advanced option order type. (Only for options)
     */
    public async privateSellGet (instrumentName: string, amount: number, type?: 'limit' | 'stop_limit' | 'market' | 'stop_market', label?: string, price?: number, timeInForce?: 'good_til_cancelled' | 'fill_or_kill' | 'immediate_or_cancel', maxShow?: number, postOnly?: boolean, reduceOnly?: boolean, stopPrice?: number, trigger?: 'index_price' | 'mark_price' | 'last_price', advanced?: 'usd' | 'implv', options: {headers: {[name: string]: string}} = {headers: {}}) : Promise<{ response: http.ClientResponse; body: object;  }> {
        const localVarPath = this.basePath + '/private/sell';
        let localVarQueryParameters: any = {};
        let localVarHeaderParams: any = (<any>Object).assign({}, this.defaultHeaders);
        let localVarFormParams: any = {};

        // verify required parameter 'instrumentName' is not null or undefined
        if (instrumentName === null || instrumentName === undefined) {
            throw new Error('Required parameter instrumentName was null or undefined when calling privateSellGet.');
        }

        // verify required parameter 'amount' is not null or undefined
        if (amount === null || amount === undefined) {
            throw new Error('Required parameter amount was null or undefined when calling privateSellGet.');
        }

        if (instrumentName !== undefined) {
            localVarQueryParameters['instrument_name'] = ObjectSerializer.serialize(instrumentName, "string");
        }

        if (amount !== undefined) {
            localVarQueryParameters['amount'] = ObjectSerializer.serialize(amount, "number");
        }

        if (type !== undefined) {
            localVarQueryParameters['type'] = ObjectSerializer.serialize(type, "'limit' | 'stop_limit' | 'market' | 'stop_market'");
        }

        if (label !== undefined) {
            localVarQueryParameters['label'] = ObjectSerializer.serialize(label, "string");
        }

        if (price !== undefined) {
            localVarQueryParameters['price'] = ObjectSerializer.serialize(price, "number");
        }

        if (timeInForce !== undefined) {
            localVarQueryParameters['time_in_force'] = ObjectSerializer.serialize(timeInForce, "'good_til_cancelled' | 'fill_or_kill' | 'immediate_or_cancel'");
        }

        if (maxShow !== undefined) {
            localVarQueryParameters['max_show'] = ObjectSerializer.serialize(maxShow, "number");
        }

        if (postOnly !== undefined) {
            localVarQueryParameters['post_only'] = ObjectSerializer.serialize(postOnly, "boolean");
        }

        if (reduceOnly !== undefined) {
            localVarQueryParameters['reduce_only'] = ObjectSerializer.serialize(reduceOnly, "boolean");
        }

        if (stopPrice !== undefined) {
            localVarQueryParameters['stop_price'] = ObjectSerializer.serialize(stopPrice, "number");
        }

        if (trigger !== undefined) {
            localVarQueryParameters['trigger'] = ObjectSerializer.serialize(trigger, "'index_price' | 'mark_price' | 'last_price'");
        }

        if (advanced !== undefined) {
            localVarQueryParameters['advanced'] = ObjectSerializer.serialize(advanced, "'usd' | 'implv'");
        }

        (<any>Object).assign(localVarHeaderParams, options.headers);

        let localVarUseFormData = false;

        let localVarRequestOptions: localVarRequest.Options = {
            method: 'GET',
            qs: localVarQueryParameters,
            headers: localVarHeaderParams,
            uri: localVarPath,
            useQuerystring: this._useQuerystring,
            json: true,
        };

        this.authentications.bearerAuth.applyToRequest(localVarRequestOptions);

        this.authentications.default.applyToRequest(localVarRequestOptions);

        if (Object.keys(localVarFormParams).length) {
            if (localVarUseFormData) {
                (<any>localVarRequestOptions).formData = localVarFormParams;
            } else {
                localVarRequestOptions.form = localVarFormParams;
            }
        }
        return new Promise<{ response: http.ClientResponse; body: object;  }>((resolve, reject) => {
            localVarRequest(localVarRequestOptions, (error, response, body) => {
                if (error) {
                    reject(error);
                } else {
                    body = ObjectSerializer.deserialize(body, "object");
                    if (response.statusCode && response.statusCode >= 200 && response.statusCode <= 299) {
                        resolve({ response: response, body: body });
                    } else {
                        reject({ response: response, body: body });
                    }
                }
            });
        });
    }
    /**
     * 
     * @summary Marks an announcement as read, so it will not be shown in `get_new_announcements`.
     * @param announcementId the ID of the announcement
     */
    public async privateSetAnnouncementAsReadGet (announcementId: number, options: {headers: {[name: string]: string}} = {headers: {}}) : Promise<{ response: http.ClientResponse; body: object;  }> {
        const localVarPath = this.basePath + '/private/set_announcement_as_read';
        let localVarQueryParameters: any = {};
        let localVarHeaderParams: any = (<any>Object).assign({}, this.defaultHeaders);
        let localVarFormParams: any = {};

        // verify required parameter 'announcementId' is not null or undefined
        if (announcementId === null || announcementId === undefined) {
            throw new Error('Required parameter announcementId was null or undefined when calling privateSetAnnouncementAsReadGet.');
        }

        if (announcementId !== undefined) {
            localVarQueryParameters['announcement_id'] = ObjectSerializer.serialize(announcementId, "number");
        }

        (<any>Object).assign(localVarHeaderParams, options.headers);

        let localVarUseFormData = false;

        let localVarRequestOptions: localVarRequest.Options = {
            method: 'GET',
            qs: localVarQueryParameters,
            headers: localVarHeaderParams,
            uri: localVarPath,
            useQuerystring: this._useQuerystring,
            json: true,
        };

        this.authentications.bearerAuth.applyToRequest(localVarRequestOptions);

        this.authentications.default.applyToRequest(localVarRequestOptions);

        if (Object.keys(localVarFormParams).length) {
            if (localVarUseFormData) {
                (<any>localVarRequestOptions).formData = localVarFormParams;
            } else {
                localVarRequestOptions.form = localVarFormParams;
            }
        }
        return new Promise<{ response: http.ClientResponse; body: object;  }>((resolve, reject) => {
            localVarRequest(localVarRequestOptions, (error, response, body) => {
                if (error) {
                    reject(error);
                } else {
                    body = ObjectSerializer.deserialize(body, "object");
                    if (response.statusCode && response.statusCode >= 200 && response.statusCode <= 299) {
                        resolve({ response: response, body: body });
                    } else {
                        reject({ response: response, body: body });
                    }
                }
            });
        });
    }
    /**
     * 
     * @summary Assign an email address to a subaccount. User will receive an email with confirmation link.
     * @param sid The user id for the subaccount
     * @param email The email address for the subaccount
     */
    public async privateSetEmailForSubaccountGet (sid: number, email: string, options: {headers: {[name: string]: string}} = {headers: {}}) : Promise<{ response: http.ClientResponse; body: object;  }> {
        const localVarPath = this.basePath + '/private/set_email_for_subaccount';
        let localVarQueryParameters: any = {};
        let localVarHeaderParams: any = (<any>Object).assign({}, this.defaultHeaders);
        let localVarFormParams: any = {};

        // verify required parameter 'sid' is not null or undefined
        if (sid === null || sid === undefined) {
            throw new Error('Required parameter sid was null or undefined when calling privateSetEmailForSubaccountGet.');
        }

        // verify required parameter 'email' is not null or undefined
        if (email === null || email === undefined) {
            throw new Error('Required parameter email was null or undefined when calling privateSetEmailForSubaccountGet.');
        }

        if (sid !== undefined) {
            localVarQueryParameters['sid'] = ObjectSerializer.serialize(sid, "number");
        }

        if (email !== undefined) {
            localVarQueryParameters['email'] = ObjectSerializer.serialize(email, "string");
        }

        (<any>Object).assign(localVarHeaderParams, options.headers);

        let localVarUseFormData = false;

        let localVarRequestOptions: localVarRequest.Options = {
            method: 'GET',
            qs: localVarQueryParameters,
            headers: localVarHeaderParams,
            uri: localVarPath,
            useQuerystring: this._useQuerystring,
            json: true,
        };

        this.authentications.bearerAuth.applyToRequest(localVarRequestOptions);

        this.authentications.default.applyToRequest(localVarRequestOptions);

        if (Object.keys(localVarFormParams).length) {
            if (localVarUseFormData) {
                (<any>localVarRequestOptions).formData = localVarFormParams;
            } else {
                localVarRequestOptions.form = localVarFormParams;
            }
        }
        return new Promise<{ response: http.ClientResponse; body: object;  }>((resolve, reject) => {
            localVarRequest(localVarRequestOptions, (error, response, body) => {
                if (error) {
                    reject(error);
                } else {
                    body = ObjectSerializer.deserialize(body, "object");
                    if (response.statusCode && response.statusCode >= 200 && response.statusCode <= 299) {
                        resolve({ response: response, body: body });
                    } else {
                        reject({ response: response, body: body });
                    }
                }
            });
        });
    }
    /**
     * 
     * @summary Changes the language to be used for emails.
     * @param language The abbreviated language name. Valid values include &#x60;\&quot;en\&quot;&#x60;, &#x60;\&quot;ko\&quot;&#x60;, &#x60;\&quot;zh\&quot;&#x60;
     */
    public async privateSetEmailLanguageGet (language: string, options: {headers: {[name: string]: string}} = {headers: {}}) : Promise<{ response: http.ClientResponse; body: object;  }> {
        const localVarPath = this.basePath + '/private/set_email_language';
        let localVarQueryParameters: any = {};
        let localVarHeaderParams: any = (<any>Object).assign({}, this.defaultHeaders);
        let localVarFormParams: any = {};

        // verify required parameter 'language' is not null or undefined
        if (language === null || language === undefined) {
            throw new Error('Required parameter language was null or undefined when calling privateSetEmailLanguageGet.');
        }

        if (language !== undefined) {
            localVarQueryParameters['language'] = ObjectSerializer.serialize(language, "string");
        }

        (<any>Object).assign(localVarHeaderParams, options.headers);

        let localVarUseFormData = false;

        let localVarRequestOptions: localVarRequest.Options = {
            method: 'GET',
            qs: localVarQueryParameters,
            headers: localVarHeaderParams,
            uri: localVarPath,
            useQuerystring: this._useQuerystring,
            json: true,
        };

        this.authentications.bearerAuth.applyToRequest(localVarRequestOptions);

        this.authentications.default.applyToRequest(localVarRequestOptions);

        if (Object.keys(localVarFormParams).length) {
            if (localVarUseFormData) {
                (<any>localVarRequestOptions).formData = localVarFormParams;
            } else {
                localVarRequestOptions.form = localVarFormParams;
            }
        }
        return new Promise<{ response: http.ClientResponse; body: object;  }>((resolve, reject) => {
            localVarRequest(localVarRequestOptions, (error, response, body) => {
                if (error) {
                    reject(error);
                } else {
                    body = ObjectSerializer.deserialize(body, "object");
                    if (response.statusCode && response.statusCode >= 200 && response.statusCode <= 299) {
                        resolve({ response: response, body: body });
                    } else {
                        reject({ response: response, body: body });
                    }
                }
            });
        });
    }
    /**
     * 
     * @summary Set the password for the subaccount
     * @param sid The user id for the subaccount
     * @param password The password for the subaccount
     */
    public async privateSetPasswordForSubaccountGet (sid: number, password: string, options: {headers: {[name: string]: string}} = {headers: {}}) : Promise<{ response: http.ClientResponse; body: object;  }> {
        const localVarPath = this.basePath + '/private/set_password_for_subaccount';
        let localVarQueryParameters: any = {};
        let localVarHeaderParams: any = (<any>Object).assign({}, this.defaultHeaders);
        let localVarFormParams: any = {};

        // verify required parameter 'sid' is not null or undefined
        if (sid === null || sid === undefined) {
            throw new Error('Required parameter sid was null or undefined when calling privateSetPasswordForSubaccountGet.');
        }

        // verify required parameter 'password' is not null or undefined
        if (password === null || password === undefined) {
            throw new Error('Required parameter password was null or undefined when calling privateSetPasswordForSubaccountGet.');
        }

        if (sid !== undefined) {
            localVarQueryParameters['sid'] = ObjectSerializer.serialize(sid, "number");
        }

        if (password !== undefined) {
            localVarQueryParameters['password'] = ObjectSerializer.serialize(password, "string");
        }

        (<any>Object).assign(localVarHeaderParams, options.headers);

        let localVarUseFormData = false;

        let localVarRequestOptions: localVarRequest.Options = {
            method: 'GET',
            qs: localVarQueryParameters,
            headers: localVarHeaderParams,
            uri: localVarPath,
            useQuerystring: this._useQuerystring,
            json: true,
        };

        this.authentications.bearerAuth.applyToRequest(localVarRequestOptions);

        this.authentications.default.applyToRequest(localVarRequestOptions);

        if (Object.keys(localVarFormParams).length) {
            if (localVarUseFormData) {
                (<any>localVarRequestOptions).formData = localVarFormParams;
            } else {
                localVarRequestOptions.form = localVarFormParams;
            }
        }
        return new Promise<{ response: http.ClientResponse; body: object;  }>((resolve, reject) => {
            localVarRequest(localVarRequestOptions, (error, response, body) => {
                if (error) {
                    reject(error);
                } else {
                    body = ObjectSerializer.deserialize(body, "object");
                    if (response.statusCode && response.statusCode >= 200 && response.statusCode <= 299) {
                        resolve({ response: response, body: body });
                    } else {
                        reject({ response: response, body: body });
                    }
                }
            });
        });
    }
    /**
     * 
     * @summary Transfer funds to a subaccount.
     * @param currency The currency symbol
     * @param amount Amount of funds to be transferred
     * @param destination Id of destination subaccount
     */
    public async privateSubmitTransferToSubaccountGet (currency: 'BTC' | 'ETH', amount: number, destination: number, options: {headers: {[name: string]: string}} = {headers: {}}) : Promise<{ response: http.ClientResponse; body: object;  }> {
        const localVarPath = this.basePath + '/private/submit_transfer_to_subaccount';
        let localVarQueryParameters: any = {};
        let localVarHeaderParams: any = (<any>Object).assign({}, this.defaultHeaders);
        let localVarFormParams: any = {};

        // verify required parameter 'currency' is not null or undefined
        if (currency === null || currency === undefined) {
            throw new Error('Required parameter currency was null or undefined when calling privateSubmitTransferToSubaccountGet.');
        }

        // verify required parameter 'amount' is not null or undefined
        if (amount === null || amount === undefined) {
            throw new Error('Required parameter amount was null or undefined when calling privateSubmitTransferToSubaccountGet.');
        }

        // verify required parameter 'destination' is not null or undefined
        if (destination === null || destination === undefined) {
            throw new Error('Required parameter destination was null or undefined when calling privateSubmitTransferToSubaccountGet.');
        }

        if (currency !== undefined) {
            localVarQueryParameters['currency'] = ObjectSerializer.serialize(currency, "'BTC' | 'ETH'");
        }

        if (amount !== undefined) {
            localVarQueryParameters['amount'] = ObjectSerializer.serialize(amount, "number");
        }

        if (destination !== undefined) {
            localVarQueryParameters['destination'] = ObjectSerializer.serialize(destination, "number");
        }

        (<any>Object).assign(localVarHeaderParams, options.headers);

        let localVarUseFormData = false;

        let localVarRequestOptions: localVarRequest.Options = {
            method: 'GET',
            qs: localVarQueryParameters,
            headers: localVarHeaderParams,
            uri: localVarPath,
            useQuerystring: this._useQuerystring,
            json: true,
        };

        this.authentications.bearerAuth.applyToRequest(localVarRequestOptions);

        this.authentications.default.applyToRequest(localVarRequestOptions);

        if (Object.keys(localVarFormParams).length) {
            if (localVarUseFormData) {
                (<any>localVarRequestOptions).formData = localVarFormParams;
            } else {
                localVarRequestOptions.form = localVarFormParams;
            }
        }
        return new Promise<{ response: http.ClientResponse; body: object;  }>((resolve, reject) => {
            localVarRequest(localVarRequestOptions, (error, response, body) => {
                if (error) {
                    reject(error);
                } else {
                    body = ObjectSerializer.deserialize(body, "object");
                    if (response.statusCode && response.statusCode >= 200 && response.statusCode <= 299) {
                        resolve({ response: response, body: body });
                    } else {
                        reject({ response: response, body: body });
                    }
                }
            });
        });
    }
    /**
     * 
     * @summary Transfer funds to a another user.
     * @param currency The currency symbol
     * @param amount Amount of funds to be transferred
     * @param destination Destination address from address book
     * @param tfa TFA code, required when TFA is enabled for current account
     */
    public async privateSubmitTransferToUserGet (currency: 'BTC' | 'ETH', amount: number, destination: string, tfa?: string, options: {headers: {[name: string]: string}} = {headers: {}}) : Promise<{ response: http.ClientResponse; body: object;  }> {
        const localVarPath = this.basePath + '/private/submit_transfer_to_user';
        let localVarQueryParameters: any = {};
        let localVarHeaderParams: any = (<any>Object).assign({}, this.defaultHeaders);
        let localVarFormParams: any = {};

        // verify required parameter 'currency' is not null or undefined
        if (currency === null || currency === undefined) {
            throw new Error('Required parameter currency was null or undefined when calling privateSubmitTransferToUserGet.');
        }

        // verify required parameter 'amount' is not null or undefined
        if (amount === null || amount === undefined) {
            throw new Error('Required parameter amount was null or undefined when calling privateSubmitTransferToUserGet.');
        }

        // verify required parameter 'destination' is not null or undefined
        if (destination === null || destination === undefined) {
            throw new Error('Required parameter destination was null or undefined when calling privateSubmitTransferToUserGet.');
        }

        if (currency !== undefined) {
            localVarQueryParameters['currency'] = ObjectSerializer.serialize(currency, "'BTC' | 'ETH'");
        }

        if (amount !== undefined) {
            localVarQueryParameters['amount'] = ObjectSerializer.serialize(amount, "number");
        }

        if (destination !== undefined) {
            localVarQueryParameters['destination'] = ObjectSerializer.serialize(destination, "string");
        }

        if (tfa !== undefined) {
            localVarQueryParameters['tfa'] = ObjectSerializer.serialize(tfa, "string");
        }

        (<any>Object).assign(localVarHeaderParams, options.headers);

        let localVarUseFormData = false;

        let localVarRequestOptions: localVarRequest.Options = {
            method: 'GET',
            qs: localVarQueryParameters,
            headers: localVarHeaderParams,
            uri: localVarPath,
            useQuerystring: this._useQuerystring,
            json: true,
        };

        this.authentications.bearerAuth.applyToRequest(localVarRequestOptions);

        this.authentications.default.applyToRequest(localVarRequestOptions);

        if (Object.keys(localVarFormParams).length) {
            if (localVarUseFormData) {
                (<any>localVarRequestOptions).formData = localVarFormParams;
            } else {
                localVarRequestOptions.form = localVarFormParams;
            }
        }
        return new Promise<{ response: http.ClientResponse; body: object;  }>((resolve, reject) => {
            localVarRequest(localVarRequestOptions, (error, response, body) => {
                if (error) {
                    reject(error);
                } else {
                    body = ObjectSerializer.deserialize(body, "object");
                    if (response.statusCode && response.statusCode >= 200 && response.statusCode <= 299) {
                        resolve({ response: response, body: body });
                    } else {
                        reject({ response: response, body: body });
                    }
                }
            });
        });
    }
    /**
     * 
     * @summary Enable or disable deposit address creation
     * @param currency The currency symbol
     * @param state 
     */
    public async privateToggleDepositAddressCreationGet (currency: 'BTC' | 'ETH', state: boolean, options: {headers: {[name: string]: string}} = {headers: {}}) : Promise<{ response: http.ClientResponse; body: object;  }> {
        const localVarPath = this.basePath + '/private/toggle_deposit_address_creation';
        let localVarQueryParameters: any = {};
        let localVarHeaderParams: any = (<any>Object).assign({}, this.defaultHeaders);
        let localVarFormParams: any = {};

        // verify required parameter 'currency' is not null or undefined
        if (currency === null || currency === undefined) {
            throw new Error('Required parameter currency was null or undefined when calling privateToggleDepositAddressCreationGet.');
        }

        // verify required parameter 'state' is not null or undefined
        if (state === null || state === undefined) {
            throw new Error('Required parameter state was null or undefined when calling privateToggleDepositAddressCreationGet.');
        }

        if (currency !== undefined) {
            localVarQueryParameters['currency'] = ObjectSerializer.serialize(currency, "'BTC' | 'ETH'");
        }

        if (state !== undefined) {
            localVarQueryParameters['state'] = ObjectSerializer.serialize(state, "boolean");
        }

        (<any>Object).assign(localVarHeaderParams, options.headers);

        let localVarUseFormData = false;

        let localVarRequestOptions: localVarRequest.Options = {
            method: 'GET',
            qs: localVarQueryParameters,
            headers: localVarHeaderParams,
            uri: localVarPath,
            useQuerystring: this._useQuerystring,
            json: true,
        };

        this.authentications.bearerAuth.applyToRequest(localVarRequestOptions);

        this.authentications.default.applyToRequest(localVarRequestOptions);

        if (Object.keys(localVarFormParams).length) {
            if (localVarUseFormData) {
                (<any>localVarRequestOptions).formData = localVarFormParams;
            } else {
                localVarRequestOptions.form = localVarFormParams;
            }
        }
        return new Promise<{ response: http.ClientResponse; body: object;  }>((resolve, reject) => {
            localVarRequest(localVarRequestOptions, (error, response, body) => {
                if (error) {
                    reject(error);
                } else {
                    body = ObjectSerializer.deserialize(body, "object");
                    if (response.statusCode && response.statusCode >= 200 && response.statusCode <= 299) {
                        resolve({ response: response, body: body });
                    } else {
                        reject({ response: response, body: body });
                    }
                }
            });
        });
    }
    /**
     * 
     * @summary Enable or disable sending of notifications for the subaccount.
     * @param sid The user id for the subaccount
     * @param state enable (&#x60;true&#x60;) or disable (&#x60;false&#x60;) notifications
     */
    public async privateToggleNotificationsFromSubaccountGet (sid: number, state: boolean, options: {headers: {[name: string]: string}} = {headers: {}}) : Promise<{ response: http.ClientResponse; body: object;  }> {
        const localVarPath = this.basePath + '/private/toggle_notifications_from_subaccount';
        let localVarQueryParameters: any = {};
        let localVarHeaderParams: any = (<any>Object).assign({}, this.defaultHeaders);
        let localVarFormParams: any = {};

        // verify required parameter 'sid' is not null or undefined
        if (sid === null || sid === undefined) {
            throw new Error('Required parameter sid was null or undefined when calling privateToggleNotificationsFromSubaccountGet.');
        }

        // verify required parameter 'state' is not null or undefined
        if (state === null || state === undefined) {
            throw new Error('Required parameter state was null or undefined when calling privateToggleNotificationsFromSubaccountGet.');
        }

        if (sid !== undefined) {
            localVarQueryParameters['sid'] = ObjectSerializer.serialize(sid, "number");
        }

        if (state !== undefined) {
            localVarQueryParameters['state'] = ObjectSerializer.serialize(state, "boolean");
        }

        (<any>Object).assign(localVarHeaderParams, options.headers);

        let localVarUseFormData = false;

        let localVarRequestOptions: localVarRequest.Options = {
            method: 'GET',
            qs: localVarQueryParameters,
            headers: localVarHeaderParams,
            uri: localVarPath,
            useQuerystring: this._useQuerystring,
            json: true,
        };

        this.authentications.bearerAuth.applyToRequest(localVarRequestOptions);

        this.authentications.default.applyToRequest(localVarRequestOptions);

        if (Object.keys(localVarFormParams).length) {
            if (localVarUseFormData) {
                (<any>localVarRequestOptions).formData = localVarFormParams;
            } else {
                localVarRequestOptions.form = localVarFormParams;
            }
        }
        return new Promise<{ response: http.ClientResponse; body: object;  }>((resolve, reject) => {
            localVarRequest(localVarRequestOptions, (error, response, body) => {
                if (error) {
                    reject(error);
                } else {
                    body = ObjectSerializer.deserialize(body, "object");
                    if (response.statusCode && response.statusCode >= 200 && response.statusCode <= 299) {
                        resolve({ response: response, body: body });
                    } else {
                        reject({ response: response, body: body });
                    }
                }
            });
        });
    }
    /**
     * 
     * @summary Enable or disable login for a subaccount. If login is disabled and a session for the subaccount exists, this session will be terminated.
     * @param sid The user id for the subaccount
     * @param state enable or disable login.
     */
    public async privateToggleSubaccountLoginGet (sid: number, state: 'enable' | 'disable', options: {headers: {[name: string]: string}} = {headers: {}}) : Promise<{ response: http.ClientResponse; body: object;  }> {
        const localVarPath = this.basePath + '/private/toggle_subaccount_login';
        let localVarQueryParameters: any = {};
        let localVarHeaderParams: any = (<any>Object).assign({}, this.defaultHeaders);
        let localVarFormParams: any = {};

        // verify required parameter 'sid' is not null or undefined
        if (sid === null || sid === undefined) {
            throw new Error('Required parameter sid was null or undefined when calling privateToggleSubaccountLoginGet.');
        }

        // verify required parameter 'state' is not null or undefined
        if (state === null || state === undefined) {
            throw new Error('Required parameter state was null or undefined when calling privateToggleSubaccountLoginGet.');
        }

        if (sid !== undefined) {
            localVarQueryParameters['sid'] = ObjectSerializer.serialize(sid, "number");
        }

        if (state !== undefined) {
            localVarQueryParameters['state'] = ObjectSerializer.serialize(state, "'enable' | 'disable'");
        }

        (<any>Object).assign(localVarHeaderParams, options.headers);

        let localVarUseFormData = false;

        let localVarRequestOptions: localVarRequest.Options = {
            method: 'GET',
            qs: localVarQueryParameters,
            headers: localVarHeaderParams,
            uri: localVarPath,
            useQuerystring: this._useQuerystring,
            json: true,
        };

        this.authentications.bearerAuth.applyToRequest(localVarRequestOptions);

        this.authentications.default.applyToRequest(localVarRequestOptions);

        if (Object.keys(localVarFormParams).length) {
            if (localVarUseFormData) {
                (<any>localVarRequestOptions).formData = localVarFormParams;
            } else {
                localVarRequestOptions.form = localVarFormParams;
            }
        }
        return new Promise<{ response: http.ClientResponse; body: object;  }>((resolve, reject) => {
            localVarRequest(localVarRequestOptions, (error, response, body) => {
                if (error) {
                    reject(error);
                } else {
                    body = ObjectSerializer.deserialize(body, "object");
                    if (response.statusCode && response.statusCode >= 200 && response.statusCode <= 299) {
                        resolve({ response: response, body: body });
                    } else {
                        reject({ response: response, body: body });
                    }
                }
            });
        });
    }
    /**
     * 
     * @summary Creates a new withdrawal request
     * @param currency The currency symbol
     * @param address Address in currency format, it must be in address book
     * @param amount Amount of funds to be withdrawn
     * @param priority Withdrawal priority, optional for BTC, default: &#x60;high&#x60;
     * @param tfa TFA code, required when TFA is enabled for current account
     */
    public async privateWithdrawGet (currency: 'BTC' | 'ETH', address: string, amount: number, priority?: 'insane' | 'extreme_high' | 'very_high' | 'high' | 'mid' | 'low' | 'very_low', tfa?: string, options: {headers: {[name: string]: string}} = {headers: {}}) : Promise<{ response: http.ClientResponse; body: object;  }> {
        const localVarPath = this.basePath + '/private/withdraw';
        let localVarQueryParameters: any = {};
        let localVarHeaderParams: any = (<any>Object).assign({}, this.defaultHeaders);
        let localVarFormParams: any = {};

        // verify required parameter 'currency' is not null or undefined
        if (currency === null || currency === undefined) {
            throw new Error('Required parameter currency was null or undefined when calling privateWithdrawGet.');
        }

        // verify required parameter 'address' is not null or undefined
        if (address === null || address === undefined) {
            throw new Error('Required parameter address was null or undefined when calling privateWithdrawGet.');
        }

        // verify required parameter 'amount' is not null or undefined
        if (amount === null || amount === undefined) {
            throw new Error('Required parameter amount was null or undefined when calling privateWithdrawGet.');
        }

        if (currency !== undefined) {
            localVarQueryParameters['currency'] = ObjectSerializer.serialize(currency, "'BTC' | 'ETH'");
        }

        if (address !== undefined) {
            localVarQueryParameters['address'] = ObjectSerializer.serialize(address, "string");
        }

        if (amount !== undefined) {
            localVarQueryParameters['amount'] = ObjectSerializer.serialize(amount, "number");
        }

        if (priority !== undefined) {
            localVarQueryParameters['priority'] = ObjectSerializer.serialize(priority, "'insane' | 'extreme_high' | 'very_high' | 'high' | 'mid' | 'low' | 'very_low'");
        }

        if (tfa !== undefined) {
            localVarQueryParameters['tfa'] = ObjectSerializer.serialize(tfa, "string");
        }

        (<any>Object).assign(localVarHeaderParams, options.headers);

        let localVarUseFormData = false;

        let localVarRequestOptions: localVarRequest.Options = {
            method: 'GET',
            qs: localVarQueryParameters,
            headers: localVarHeaderParams,
            uri: localVarPath,
            useQuerystring: this._useQuerystring,
            json: true,
        };

        this.authentications.bearerAuth.applyToRequest(localVarRequestOptions);

        this.authentications.default.applyToRequest(localVarRequestOptions);

        if (Object.keys(localVarFormParams).length) {
            if (localVarUseFormData) {
                (<any>localVarRequestOptions).formData = localVarFormParams;
            } else {
                localVarRequestOptions.form = localVarFormParams;
            }
        }
        return new Promise<{ response: http.ClientResponse; body: object;  }>((resolve, reject) => {
            localVarRequest(localVarRequestOptions, (error, response, body) => {
                if (error) {
                    reject(error);
                } else {
                    body = ObjectSerializer.deserialize(body, "object");
                    if (response.statusCode && response.statusCode >= 200 && response.statusCode <= 299) {
                        resolve({ response: response, body: body });
                    } else {
                        reject({ response: response, body: body });
                    }
                }
            });
        });
    }
}
