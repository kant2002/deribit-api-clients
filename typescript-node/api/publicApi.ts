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

export enum PublicApiApiKeys {
}

export class PublicApi {
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

    public setApiKey(key: PublicApiApiKeys, value: string) {
        (this.authentications as any)[PublicApiApiKeys[key]].apiKey = value;
    }
    set username(username: string) {
        this.authentications.bearerAuth.username = username;
    }

    set password(password: string) {
        this.authentications.bearerAuth.password = password;
    }

    /**
     * Retrieve an Oauth access token, to be used for authentication of \'private\' requests.  Three methods of authentication are supported:  - <code>password</code> - using email and and password as when logging on to the website - <code>client_credentials</code> - using the access key and access secret that can be found on the API page on the website - <code>client_signature</code> - using the access key that can be found on the API page on the website and user generated signature. The signature is calculated using some fields provided in the request, using formula described here [Deribit signature credentials](#additional-authorization-method-deribit-signature-credentials) - <code>refresh_token</code> - using a refresh token that was received from an earlier invocation  The response will contain an access token, expiration period (number of seconds that the token is valid) and a refresh token that can be used to get a new set of tokens. 
     * @summary Authenticate
     * @param grantType Method of authentication
     * @param username Required for grant type &#x60;password&#x60;
     * @param password Required for grant type &#x60;password&#x60;
     * @param clientId Required for grant type &#x60;client_credentials&#x60; and &#x60;client_signature&#x60;
     * @param clientSecret Required for grant type &#x60;client_credentials&#x60;
     * @param refreshToken Required for grant type &#x60;refresh_token&#x60;
     * @param timestamp Required for grant type &#x60;client_signature&#x60;, provides time when request has been generated
     * @param signature Required for grant type &#x60;client_signature&#x60;; it\&#39;s a cryptographic signature calculated over provided fields using user **secret key**. The signature should be calculated as an HMAC (Hash-based Message Authentication Code) with &#x60;SHA256&#x60; hash algorithm
     * @param nonce Optional for grant type &#x60;client_signature&#x60;; delivers user generated initialization vector for the server token
     * @param state Will be passed back in the response
     * @param scope Describes type of the access for assigned token, possible values: &#x60;connection&#x60;, &#x60;session&#x60;, &#x60;session:name&#x60;, &#x60;trade:[read, read_write, none]&#x60;, &#x60;wallet:[read, read_write, none]&#x60;, &#x60;account:[read, read_write, none]&#x60;, &#x60;expires:NUMBER&#x60; (token will expire after &#x60;NUMBER&#x60; of seconds).&lt;/BR&gt;&lt;/BR&gt; **NOTICE:** Depending on choosing an authentication method (&#x60;&#x60;&#x60;grant type&#x60;&#x60;&#x60;) some scopes could be narrowed by the server. e.g. when &#x60;&#x60;&#x60;grant_type &#x3D; client_credentials&#x60;&#x60;&#x60; and &#x60;&#x60;&#x60;scope &#x3D; wallet:read_write&#x60;&#x60;&#x60; it\&#39;s modified by the server as &#x60;&#x60;&#x60;scope &#x3D; wallet:read&#x60;&#x60;&#x60;
     */
    public async publicAuthGet (grantType: 'password' | 'client_credentials' | 'client_signature' | 'refresh_token', username: string, password: string, clientId: string, clientSecret: string, refreshToken: string, timestamp: string, signature: string, nonce?: string, state?: string, scope?: string, options: {headers: {[name: string]: string}} = {headers: {}}) : Promise<{ response: http.ClientResponse; body: object;  }> {
        const localVarPath = this.basePath + '/public/auth';
        let localVarQueryParameters: any = {};
        let localVarHeaderParams: any = (<any>Object).assign({}, this.defaultHeaders);
        let localVarFormParams: any = {};

        // verify required parameter 'grantType' is not null or undefined
        if (grantType === null || grantType === undefined) {
            throw new Error('Required parameter grantType was null or undefined when calling publicAuthGet.');
        }

        // verify required parameter 'username' is not null or undefined
        if (username === null || username === undefined) {
            throw new Error('Required parameter username was null or undefined when calling publicAuthGet.');
        }

        // verify required parameter 'password' is not null or undefined
        if (password === null || password === undefined) {
            throw new Error('Required parameter password was null or undefined when calling publicAuthGet.');
        }

        // verify required parameter 'clientId' is not null or undefined
        if (clientId === null || clientId === undefined) {
            throw new Error('Required parameter clientId was null or undefined when calling publicAuthGet.');
        }

        // verify required parameter 'clientSecret' is not null or undefined
        if (clientSecret === null || clientSecret === undefined) {
            throw new Error('Required parameter clientSecret was null or undefined when calling publicAuthGet.');
        }

        // verify required parameter 'refreshToken' is not null or undefined
        if (refreshToken === null || refreshToken === undefined) {
            throw new Error('Required parameter refreshToken was null or undefined when calling publicAuthGet.');
        }

        // verify required parameter 'timestamp' is not null or undefined
        if (timestamp === null || timestamp === undefined) {
            throw new Error('Required parameter timestamp was null or undefined when calling publicAuthGet.');
        }

        // verify required parameter 'signature' is not null or undefined
        if (signature === null || signature === undefined) {
            throw new Error('Required parameter signature was null or undefined when calling publicAuthGet.');
        }

        if (grantType !== undefined) {
            localVarQueryParameters['grant_type'] = ObjectSerializer.serialize(grantType, "'password' | 'client_credentials' | 'client_signature' | 'refresh_token'");
        }

        if (username !== undefined) {
            localVarQueryParameters['username'] = ObjectSerializer.serialize(username, "string");
        }

        if (password !== undefined) {
            localVarQueryParameters['password'] = ObjectSerializer.serialize(password, "string");
        }

        if (clientId !== undefined) {
            localVarQueryParameters['client_id'] = ObjectSerializer.serialize(clientId, "string");
        }

        if (clientSecret !== undefined) {
            localVarQueryParameters['client_secret'] = ObjectSerializer.serialize(clientSecret, "string");
        }

        if (refreshToken !== undefined) {
            localVarQueryParameters['refresh_token'] = ObjectSerializer.serialize(refreshToken, "string");
        }

        if (timestamp !== undefined) {
            localVarQueryParameters['timestamp'] = ObjectSerializer.serialize(timestamp, "string");
        }

        if (signature !== undefined) {
            localVarQueryParameters['signature'] = ObjectSerializer.serialize(signature, "string");
        }

        if (nonce !== undefined) {
            localVarQueryParameters['nonce'] = ObjectSerializer.serialize(nonce, "string");
        }

        if (state !== undefined) {
            localVarQueryParameters['state'] = ObjectSerializer.serialize(state, "string");
        }

        if (scope !== undefined) {
            localVarQueryParameters['scope'] = ObjectSerializer.serialize(scope, "string");
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
     * @summary Retrieves announcements from the last 30 days.
     */
    public async publicGetAnnouncementsGet (options: {headers: {[name: string]: string}} = {headers: {}}) : Promise<{ response: http.ClientResponse; body: object;  }> {
        const localVarPath = this.basePath + '/public/get_announcements';
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
     * @summary Retrieves the summary information such as open interest, 24h volume, etc. for all instruments for the currency (optionally filtered by kind).
     * @param currency The currency symbol
     * @param kind Instrument kind, if not provided instruments of all kinds are considered
     */
    public async publicGetBookSummaryByCurrencyGet (currency: 'BTC' | 'ETH', kind?: 'future' | 'option', options: {headers: {[name: string]: string}} = {headers: {}}) : Promise<{ response: http.ClientResponse; body: object;  }> {
        const localVarPath = this.basePath + '/public/get_book_summary_by_currency';
        let localVarQueryParameters: any = {};
        let localVarHeaderParams: any = (<any>Object).assign({}, this.defaultHeaders);
        let localVarFormParams: any = {};

        // verify required parameter 'currency' is not null or undefined
        if (currency === null || currency === undefined) {
            throw new Error('Required parameter currency was null or undefined when calling publicGetBookSummaryByCurrencyGet.');
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
     * @summary Retrieves the summary information such as open interest, 24h volume, etc. for a specific instrument.
     * @param instrumentName Instrument name
     */
    public async publicGetBookSummaryByInstrumentGet (instrumentName: string, options: {headers: {[name: string]: string}} = {headers: {}}) : Promise<{ response: http.ClientResponse; body: object;  }> {
        const localVarPath = this.basePath + '/public/get_book_summary_by_instrument';
        let localVarQueryParameters: any = {};
        let localVarHeaderParams: any = (<any>Object).assign({}, this.defaultHeaders);
        let localVarFormParams: any = {};

        // verify required parameter 'instrumentName' is not null or undefined
        if (instrumentName === null || instrumentName === undefined) {
            throw new Error('Required parameter instrumentName was null or undefined when calling publicGetBookSummaryByInstrumentGet.');
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
     * @summary Retrieves contract size of provided instrument.
     * @param instrumentName Instrument name
     */
    public async publicGetContractSizeGet (instrumentName: string, options: {headers: {[name: string]: string}} = {headers: {}}) : Promise<{ response: http.ClientResponse; body: object;  }> {
        const localVarPath = this.basePath + '/public/get_contract_size';
        let localVarQueryParameters: any = {};
        let localVarHeaderParams: any = (<any>Object).assign({}, this.defaultHeaders);
        let localVarFormParams: any = {};

        // verify required parameter 'instrumentName' is not null or undefined
        if (instrumentName === null || instrumentName === undefined) {
            throw new Error('Required parameter instrumentName was null or undefined when calling publicGetContractSizeGet.');
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
     * @summary Retrieves all cryptocurrencies supported by the API.
     */
    public async publicGetCurrenciesGet (options: {headers: {[name: string]: string}} = {headers: {}}) : Promise<{ response: http.ClientResponse; body: object;  }> {
        const localVarPath = this.basePath + '/public/get_currencies';
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
     * @summary Retrieve the latest user trades that have occurred for PERPETUAL instruments in a specific currency symbol and within given time range.
     * @param instrumentName Instrument name
     * @param length Specifies time period. &#x60;8h&#x60; - 8 hours, &#x60;24h&#x60; - 24 hours
     */
    public async publicGetFundingChartDataGet (instrumentName: string, length?: '8h' | '24h', options: {headers: {[name: string]: string}} = {headers: {}}) : Promise<{ response: http.ClientResponse; body: object;  }> {
        const localVarPath = this.basePath + '/public/get_funding_chart_data';
        let localVarQueryParameters: any = {};
        let localVarHeaderParams: any = (<any>Object).assign({}, this.defaultHeaders);
        let localVarFormParams: any = {};

        // verify required parameter 'instrumentName' is not null or undefined
        if (instrumentName === null || instrumentName === undefined) {
            throw new Error('Required parameter instrumentName was null or undefined when calling publicGetFundingChartDataGet.');
        }

        if (instrumentName !== undefined) {
            localVarQueryParameters['instrument_name'] = ObjectSerializer.serialize(instrumentName, "string");
        }

        if (length !== undefined) {
            localVarQueryParameters['length'] = ObjectSerializer.serialize(length, "'8h' | '24h'");
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
     * @summary Provides information about historical volatility for given cryptocurrency.
     * @param currency The currency symbol
     */
    public async publicGetHistoricalVolatilityGet (currency: 'BTC' | 'ETH', options: {headers: {[name: string]: string}} = {headers: {}}) : Promise<{ response: http.ClientResponse; body: object;  }> {
        const localVarPath = this.basePath + '/public/get_historical_volatility';
        let localVarQueryParameters: any = {};
        let localVarHeaderParams: any = (<any>Object).assign({}, this.defaultHeaders);
        let localVarFormParams: any = {};

        // verify required parameter 'currency' is not null or undefined
        if (currency === null || currency === undefined) {
            throw new Error('Required parameter currency was null or undefined when calling publicGetHistoricalVolatilityGet.');
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
     * @summary Retrieves the current index price for the instruments, for the selected currency.
     * @param currency The currency symbol
     */
    public async publicGetIndexGet (currency: 'BTC' | 'ETH', options: {headers: {[name: string]: string}} = {headers: {}}) : Promise<{ response: http.ClientResponse; body: object;  }> {
        const localVarPath = this.basePath + '/public/get_index';
        let localVarQueryParameters: any = {};
        let localVarHeaderParams: any = (<any>Object).assign({}, this.defaultHeaders);
        let localVarFormParams: any = {};

        // verify required parameter 'currency' is not null or undefined
        if (currency === null || currency === undefined) {
            throw new Error('Required parameter currency was null or undefined when calling publicGetIndexGet.');
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
     * @summary Retrieves available trading instruments. This method can be used to see which instruments are available for trading, or which instruments have existed historically.
     * @param currency The currency symbol
     * @param kind Instrument kind, if not provided instruments of all kinds are considered
     * @param expired Set to true to show expired instruments instead of active ones.
     */
    public async publicGetInstrumentsGet (currency: 'BTC' | 'ETH', kind?: 'future' | 'option', expired?: boolean, options: {headers: {[name: string]: string}} = {headers: {}}) : Promise<{ response: http.ClientResponse; body: object;  }> {
        const localVarPath = this.basePath + '/public/get_instruments';
        let localVarQueryParameters: any = {};
        let localVarHeaderParams: any = (<any>Object).assign({}, this.defaultHeaders);
        let localVarFormParams: any = {};

        // verify required parameter 'currency' is not null or undefined
        if (currency === null || currency === undefined) {
            throw new Error('Required parameter currency was null or undefined when calling publicGetInstrumentsGet.');
        }

        if (currency !== undefined) {
            localVarQueryParameters['currency'] = ObjectSerializer.serialize(currency, "'BTC' | 'ETH'");
        }

        if (kind !== undefined) {
            localVarQueryParameters['kind'] = ObjectSerializer.serialize(kind, "'future' | 'option'");
        }

        if (expired !== undefined) {
            localVarQueryParameters['expired'] = ObjectSerializer.serialize(expired, "boolean");
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
     * @summary Retrieves historical settlement, delivery and bankruptcy events coming from all instruments within given currency.
     * @param currency The currency symbol
     * @param type Settlement type
     * @param count Number of requested items, default - &#x60;20&#x60;
     * @param continuation Continuation token for pagination
     * @param searchStartTimestamp The latest timestamp to return result for
     */
    public async publicGetLastSettlementsByCurrencyGet (currency: 'BTC' | 'ETH', type?: 'settlement' | 'delivery' | 'bankruptcy', count?: number, continuation?: string, searchStartTimestamp?: number, options: {headers: {[name: string]: string}} = {headers: {}}) : Promise<{ response: http.ClientResponse; body: object;  }> {
        const localVarPath = this.basePath + '/public/get_last_settlements_by_currency';
        let localVarQueryParameters: any = {};
        let localVarHeaderParams: any = (<any>Object).assign({}, this.defaultHeaders);
        let localVarFormParams: any = {};

        // verify required parameter 'currency' is not null or undefined
        if (currency === null || currency === undefined) {
            throw new Error('Required parameter currency was null or undefined when calling publicGetLastSettlementsByCurrencyGet.');
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

        if (continuation !== undefined) {
            localVarQueryParameters['continuation'] = ObjectSerializer.serialize(continuation, "string");
        }

        if (searchStartTimestamp !== undefined) {
            localVarQueryParameters['search_start_timestamp'] = ObjectSerializer.serialize(searchStartTimestamp, "number");
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
     * @summary Retrieves historical public settlement, delivery and bankruptcy events filtered by instrument name.
     * @param instrumentName Instrument name
     * @param type Settlement type
     * @param count Number of requested items, default - &#x60;20&#x60;
     * @param continuation Continuation token for pagination
     * @param searchStartTimestamp The latest timestamp to return result for
     */
    public async publicGetLastSettlementsByInstrumentGet (instrumentName: string, type?: 'settlement' | 'delivery' | 'bankruptcy', count?: number, continuation?: string, searchStartTimestamp?: number, options: {headers: {[name: string]: string}} = {headers: {}}) : Promise<{ response: http.ClientResponse; body: object;  }> {
        const localVarPath = this.basePath + '/public/get_last_settlements_by_instrument';
        let localVarQueryParameters: any = {};
        let localVarHeaderParams: any = (<any>Object).assign({}, this.defaultHeaders);
        let localVarFormParams: any = {};

        // verify required parameter 'instrumentName' is not null or undefined
        if (instrumentName === null || instrumentName === undefined) {
            throw new Error('Required parameter instrumentName was null or undefined when calling publicGetLastSettlementsByInstrumentGet.');
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

        if (continuation !== undefined) {
            localVarQueryParameters['continuation'] = ObjectSerializer.serialize(continuation, "string");
        }

        if (searchStartTimestamp !== undefined) {
            localVarQueryParameters['search_start_timestamp'] = ObjectSerializer.serialize(searchStartTimestamp, "number");
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
     * @summary Retrieve the latest trades that have occurred for instruments in a specific currency symbol and within given time range.
     * @param currency The currency symbol
     * @param startTimestamp The earliest timestamp to return result for
     * @param endTimestamp The most recent timestamp to return result for
     * @param kind Instrument kind, if not provided instruments of all kinds are considered
     * @param count Number of requested items, default - &#x60;10&#x60;
     * @param includeOld Include trades older than 7 days, default - &#x60;false&#x60;
     * @param sorting Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database)
     */
    public async publicGetLastTradesByCurrencyAndTimeGet (currency: 'BTC' | 'ETH', startTimestamp: number, endTimestamp: number, kind?: 'future' | 'option', count?: number, includeOld?: boolean, sorting?: 'asc' | 'desc' | 'default', options: {headers: {[name: string]: string}} = {headers: {}}) : Promise<{ response: http.ClientResponse; body: object;  }> {
        const localVarPath = this.basePath + '/public/get_last_trades_by_currency_and_time';
        let localVarQueryParameters: any = {};
        let localVarHeaderParams: any = (<any>Object).assign({}, this.defaultHeaders);
        let localVarFormParams: any = {};

        // verify required parameter 'currency' is not null or undefined
        if (currency === null || currency === undefined) {
            throw new Error('Required parameter currency was null or undefined when calling publicGetLastTradesByCurrencyAndTimeGet.');
        }

        // verify required parameter 'startTimestamp' is not null or undefined
        if (startTimestamp === null || startTimestamp === undefined) {
            throw new Error('Required parameter startTimestamp was null or undefined when calling publicGetLastTradesByCurrencyAndTimeGet.');
        }

        // verify required parameter 'endTimestamp' is not null or undefined
        if (endTimestamp === null || endTimestamp === undefined) {
            throw new Error('Required parameter endTimestamp was null or undefined when calling publicGetLastTradesByCurrencyAndTimeGet.');
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
     * @summary Retrieve the latest trades that have occurred for instruments in a specific currency symbol.
     * @param currency The currency symbol
     * @param kind Instrument kind, if not provided instruments of all kinds are considered
     * @param startId The ID number of the first trade to be returned
     * @param endId The ID number of the last trade to be returned
     * @param count Number of requested items, default - &#x60;10&#x60;
     * @param includeOld Include trades older than 7 days, default - &#x60;false&#x60;
     * @param sorting Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database)
     */
    public async publicGetLastTradesByCurrencyGet (currency: 'BTC' | 'ETH', kind?: 'future' | 'option', startId?: string, endId?: string, count?: number, includeOld?: boolean, sorting?: 'asc' | 'desc' | 'default', options: {headers: {[name: string]: string}} = {headers: {}}) : Promise<{ response: http.ClientResponse; body: object;  }> {
        const localVarPath = this.basePath + '/public/get_last_trades_by_currency';
        let localVarQueryParameters: any = {};
        let localVarHeaderParams: any = (<any>Object).assign({}, this.defaultHeaders);
        let localVarFormParams: any = {};

        // verify required parameter 'currency' is not null or undefined
        if (currency === null || currency === undefined) {
            throw new Error('Required parameter currency was null or undefined when calling publicGetLastTradesByCurrencyGet.');
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
     * @summary Retrieve the latest trades that have occurred for a specific instrument and within given time range.
     * @param instrumentName Instrument name
     * @param startTimestamp The earliest timestamp to return result for
     * @param endTimestamp The most recent timestamp to return result for
     * @param count Number of requested items, default - &#x60;10&#x60;
     * @param includeOld Include trades older than 7 days, default - &#x60;false&#x60;
     * @param sorting Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database)
     */
    public async publicGetLastTradesByInstrumentAndTimeGet (instrumentName: string, startTimestamp: number, endTimestamp: number, count?: number, includeOld?: boolean, sorting?: 'asc' | 'desc' | 'default', options: {headers: {[name: string]: string}} = {headers: {}}) : Promise<{ response: http.ClientResponse; body: object;  }> {
        const localVarPath = this.basePath + '/public/get_last_trades_by_instrument_and_time';
        let localVarQueryParameters: any = {};
        let localVarHeaderParams: any = (<any>Object).assign({}, this.defaultHeaders);
        let localVarFormParams: any = {};

        // verify required parameter 'instrumentName' is not null or undefined
        if (instrumentName === null || instrumentName === undefined) {
            throw new Error('Required parameter instrumentName was null or undefined when calling publicGetLastTradesByInstrumentAndTimeGet.');
        }

        // verify required parameter 'startTimestamp' is not null or undefined
        if (startTimestamp === null || startTimestamp === undefined) {
            throw new Error('Required parameter startTimestamp was null or undefined when calling publicGetLastTradesByInstrumentAndTimeGet.');
        }

        // verify required parameter 'endTimestamp' is not null or undefined
        if (endTimestamp === null || endTimestamp === undefined) {
            throw new Error('Required parameter endTimestamp was null or undefined when calling publicGetLastTradesByInstrumentAndTimeGet.');
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
     * @summary Retrieve the latest trades that have occurred for a specific instrument.
     * @param instrumentName Instrument name
     * @param startSeq The sequence number of the first trade to be returned
     * @param endSeq The sequence number of the last trade to be returned
     * @param count Number of requested items, default - &#x60;10&#x60;
     * @param includeOld Include trades older than 7 days, default - &#x60;false&#x60;
     * @param sorting Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database)
     */
    public async publicGetLastTradesByInstrumentGet (instrumentName: string, startSeq?: number, endSeq?: number, count?: number, includeOld?: boolean, sorting?: 'asc' | 'desc' | 'default', options: {headers: {[name: string]: string}} = {headers: {}}) : Promise<{ response: http.ClientResponse; body: object;  }> {
        const localVarPath = this.basePath + '/public/get_last_trades_by_instrument';
        let localVarQueryParameters: any = {};
        let localVarHeaderParams: any = (<any>Object).assign({}, this.defaultHeaders);
        let localVarFormParams: any = {};

        // verify required parameter 'instrumentName' is not null or undefined
        if (instrumentName === null || instrumentName === undefined) {
            throw new Error('Required parameter instrumentName was null or undefined when calling publicGetLastTradesByInstrumentGet.');
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
     * @summary Retrieves the order book, along with other market values for a given instrument.
     * @param instrumentName The instrument name for which to retrieve the order book, see [&#x60;getinstruments&#x60;](#getinstruments) to obtain instrument names.
     * @param depth The number of entries to return for bids and asks.
     */
    public async publicGetOrderBookGet (instrumentName: string, depth?: number, options: {headers: {[name: string]: string}} = {headers: {}}) : Promise<{ response: http.ClientResponse; body: object;  }> {
        const localVarPath = this.basePath + '/public/get_order_book';
        let localVarQueryParameters: any = {};
        let localVarHeaderParams: any = (<any>Object).assign({}, this.defaultHeaders);
        let localVarFormParams: any = {};

        // verify required parameter 'instrumentName' is not null or undefined
        if (instrumentName === null || instrumentName === undefined) {
            throw new Error('Required parameter instrumentName was null or undefined when calling publicGetOrderBookGet.');
        }

        if (instrumentName !== undefined) {
            localVarQueryParameters['instrument_name'] = ObjectSerializer.serialize(instrumentName, "string");
        }

        if (depth !== undefined) {
            localVarQueryParameters['depth'] = ObjectSerializer.serialize(depth, "number");
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
     * @summary Retrieves the current time (in milliseconds). This API endpoint can be used to check the clock skew between your software and Deribit\'s systems.
     */
    public async publicGetTimeGet (options: {headers: {[name: string]: string}} = {headers: {}}) : Promise<{ response: http.ClientResponse; body: object;  }> {
        const localVarPath = this.basePath + '/public/get_time';
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
     * @summary Retrieves aggregated 24h trade volumes for different instrument types and currencies.
     */
    public async publicGetTradeVolumesGet (options: {headers: {[name: string]: string}} = {headers: {}}) : Promise<{ response: http.ClientResponse; body: object;  }> {
        const localVarPath = this.basePath + '/public/get_trade_volumes';
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
     * @summary Publicly available market data used to generate a TradingView candle chart.
     * @param instrumentName Instrument name
     * @param startTimestamp The earliest timestamp to return result for
     * @param endTimestamp The most recent timestamp to return result for
     */
    public async publicGetTradingviewChartDataGet (instrumentName: string, startTimestamp: number, endTimestamp: number, options: {headers: {[name: string]: string}} = {headers: {}}) : Promise<{ response: http.ClientResponse; body: object;  }> {
        const localVarPath = this.basePath + '/public/get_tradingview_chart_data';
        let localVarQueryParameters: any = {};
        let localVarHeaderParams: any = (<any>Object).assign({}, this.defaultHeaders);
        let localVarFormParams: any = {};

        // verify required parameter 'instrumentName' is not null or undefined
        if (instrumentName === null || instrumentName === undefined) {
            throw new Error('Required parameter instrumentName was null or undefined when calling publicGetTradingviewChartDataGet.');
        }

        // verify required parameter 'startTimestamp' is not null or undefined
        if (startTimestamp === null || startTimestamp === undefined) {
            throw new Error('Required parameter startTimestamp was null or undefined when calling publicGetTradingviewChartDataGet.');
        }

        // verify required parameter 'endTimestamp' is not null or undefined
        if (endTimestamp === null || endTimestamp === undefined) {
            throw new Error('Required parameter endTimestamp was null or undefined when calling publicGetTradingviewChartDataGet.');
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
     * @summary Tests the connection to the API server, and returns its version. You can use this to make sure the API is reachable, and matches the expected version.
     * @param expectedResult The value \&quot;exception\&quot; will trigger an error response. This may be useful for testing wrapper libraries.
     */
    public async publicTestGet (expectedResult?: 'exception', options: {headers: {[name: string]: string}} = {headers: {}}) : Promise<{ response: http.ClientResponse; body: object;  }> {
        const localVarPath = this.basePath + '/public/test';
        let localVarQueryParameters: any = {};
        let localVarHeaderParams: any = (<any>Object).assign({}, this.defaultHeaders);
        let localVarFormParams: any = {};

        if (expectedResult !== undefined) {
            localVarQueryParameters['expected_result'] = ObjectSerializer.serialize(expectedResult, "'exception'");
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
     * @summary Get ticker for an instrument.
     * @param instrumentName Instrument name
     */
    public async publicTickerGet (instrumentName: string, options: {headers: {[name: string]: string}} = {headers: {}}) : Promise<{ response: http.ClientResponse; body: object;  }> {
        const localVarPath = this.basePath + '/public/ticker';
        let localVarQueryParameters: any = {};
        let localVarHeaderParams: any = (<any>Object).assign({}, this.defaultHeaders);
        let localVarFormParams: any = {};

        // verify required parameter 'instrumentName' is not null or undefined
        if (instrumentName === null || instrumentName === undefined) {
            throw new Error('Required parameter instrumentName was null or undefined when calling publicTickerGet.');
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
     * @summary Method used to introduce the client software connected to Deribit platform over websocket. Provided data may have an impact on the maintained connection and will be collected for internal statistical purposes. In response, Deribit will also introduce itself.
     * @param field Name of the field to be validated, examples: postal_code, date_of_birth
     * @param value Value to be checked
     * @param value2 Additional value to be compared with
     */
    public async publicValidateFieldGet (field: string, value: string, value2?: string, options: {headers: {[name: string]: string}} = {headers: {}}) : Promise<{ response: http.ClientResponse; body: object;  }> {
        const localVarPath = this.basePath + '/public/validate_field';
        let localVarQueryParameters: any = {};
        let localVarHeaderParams: any = (<any>Object).assign({}, this.defaultHeaders);
        let localVarFormParams: any = {};

        // verify required parameter 'field' is not null or undefined
        if (field === null || field === undefined) {
            throw new Error('Required parameter field was null or undefined when calling publicValidateFieldGet.');
        }

        // verify required parameter 'value' is not null or undefined
        if (value === null || value === undefined) {
            throw new Error('Required parameter value was null or undefined when calling publicValidateFieldGet.');
        }

        if (field !== undefined) {
            localVarQueryParameters['field'] = ObjectSerializer.serialize(field, "string");
        }

        if (value !== undefined) {
            localVarQueryParameters['value'] = ObjectSerializer.serialize(value, "string");
        }

        if (value2 !== undefined) {
            localVarQueryParameters['value2'] = ObjectSerializer.serialize(value2, "string");
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
