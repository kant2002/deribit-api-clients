# OAIPrivateApi

All URIs are relative to *https://www.deribit.com/api/v2*

Method | HTTP request | Description
------------- | ------------- | -------------
[**privateAddToAddressBookGet**](OAIPrivateApi.md#privateaddtoaddressbookget) | **GET** /private/add_to_address_book | Adds new entry to address book of given type
[**privateBuyGet**](OAIPrivateApi.md#privatebuyget) | **GET** /private/buy | Places a buy order for an instrument.
[**privateCancelAllByCurrencyGet**](OAIPrivateApi.md#privatecancelallbycurrencyget) | **GET** /private/cancel_all_by_currency | Cancels all orders by currency, optionally filtered by instrument kind and/or order type.
[**privateCancelAllByInstrumentGet**](OAIPrivateApi.md#privatecancelallbyinstrumentget) | **GET** /private/cancel_all_by_instrument | Cancels all orders by instrument, optionally filtered by order type.
[**privateCancelAllGet**](OAIPrivateApi.md#privatecancelallget) | **GET** /private/cancel_all | This method cancels all users orders and stop orders within all currencies and instrument kinds.
[**privateCancelGet**](OAIPrivateApi.md#privatecancelget) | **GET** /private/cancel | Cancel an order, specified by order id
[**privateCancelTransferByIdGet**](OAIPrivateApi.md#privatecanceltransferbyidget) | **GET** /private/cancel_transfer_by_id | Cancel transfer
[**privateCancelWithdrawalGet**](OAIPrivateApi.md#privatecancelwithdrawalget) | **GET** /private/cancel_withdrawal | Cancels withdrawal request
[**privateChangeSubaccountNameGet**](OAIPrivateApi.md#privatechangesubaccountnameget) | **GET** /private/change_subaccount_name | Change the user name for a subaccount
[**privateClosePositionGet**](OAIPrivateApi.md#privateclosepositionget) | **GET** /private/close_position | Makes closing position reduce only order .
[**privateCreateDepositAddressGet**](OAIPrivateApi.md#privatecreatedepositaddressget) | **GET** /private/create_deposit_address | Creates deposit address in currency
[**privateCreateSubaccountGet**](OAIPrivateApi.md#privatecreatesubaccountget) | **GET** /private/create_subaccount | Create a new subaccount
[**privateDisableTfaForSubaccountGet**](OAIPrivateApi.md#privatedisabletfaforsubaccountget) | **GET** /private/disable_tfa_for_subaccount | Disable two factor authentication for a subaccount.
[**privateDisableTfaWithRecoveryCodeGet**](OAIPrivateApi.md#privatedisabletfawithrecoverycodeget) | **GET** /private/disable_tfa_with_recovery_code | Disables TFA with one time recovery code
[**privateEditGet**](OAIPrivateApi.md#privateeditget) | **GET** /private/edit | Change price, amount and/or other properties of an order.
[**privateGetAccountSummaryGet**](OAIPrivateApi.md#privategetaccountsummaryget) | **GET** /private/get_account_summary | Retrieves user account summary.
[**privateGetAddressBookGet**](OAIPrivateApi.md#privategetaddressbookget) | **GET** /private/get_address_book | Retrieves address book of given type
[**privateGetCurrentDepositAddressGet**](OAIPrivateApi.md#privategetcurrentdepositaddressget) | **GET** /private/get_current_deposit_address | Retrieve deposit address for currency
[**privateGetDepositsGet**](OAIPrivateApi.md#privategetdepositsget) | **GET** /private/get_deposits | Retrieve the latest users deposits
[**privateGetEmailLanguageGet**](OAIPrivateApi.md#privategetemaillanguageget) | **GET** /private/get_email_language | Retrieves the language to be used for emails.
[**privateGetMarginsGet**](OAIPrivateApi.md#privategetmarginsget) | **GET** /private/get_margins | Get margins for given instrument, amount and price.
[**privateGetNewAnnouncementsGet**](OAIPrivateApi.md#privategetnewannouncementsget) | **GET** /private/get_new_announcements | Retrieves announcements that have not been marked read by the user.
[**privateGetOpenOrdersByCurrencyGet**](OAIPrivateApi.md#privategetopenordersbycurrencyget) | **GET** /private/get_open_orders_by_currency | Retrieves list of user&#39;s open orders.
[**privateGetOpenOrdersByInstrumentGet**](OAIPrivateApi.md#privategetopenordersbyinstrumentget) | **GET** /private/get_open_orders_by_instrument | Retrieves list of user&#39;s open orders within given Instrument.
[**privateGetOrderHistoryByCurrencyGet**](OAIPrivateApi.md#privategetorderhistorybycurrencyget) | **GET** /private/get_order_history_by_currency | Retrieves history of orders that have been partially or fully filled.
[**privateGetOrderHistoryByInstrumentGet**](OAIPrivateApi.md#privategetorderhistorybyinstrumentget) | **GET** /private/get_order_history_by_instrument | Retrieves history of orders that have been partially or fully filled.
[**privateGetOrderMarginByIdsGet**](OAIPrivateApi.md#privategetordermarginbyidsget) | **GET** /private/get_order_margin_by_ids | Retrieves initial margins of given orders
[**privateGetOrderStateGet**](OAIPrivateApi.md#privategetorderstateget) | **GET** /private/get_order_state | Retrieve the current state of an order.
[**privateGetPositionGet**](OAIPrivateApi.md#privategetpositionget) | **GET** /private/get_position | Retrieve user position.
[**privateGetPositionsGet**](OAIPrivateApi.md#privategetpositionsget) | **GET** /private/get_positions | Retrieve user positions.
[**privateGetSettlementHistoryByCurrencyGet**](OAIPrivateApi.md#privategetsettlementhistorybycurrencyget) | **GET** /private/get_settlement_history_by_currency | Retrieves settlement, delivery and bankruptcy events that have affected your account.
[**privateGetSettlementHistoryByInstrumentGet**](OAIPrivateApi.md#privategetsettlementhistorybyinstrumentget) | **GET** /private/get_settlement_history_by_instrument | Retrieves public settlement, delivery and bankruptcy events filtered by instrument name
[**privateGetSubaccountsGet**](OAIPrivateApi.md#privategetsubaccountsget) | **GET** /private/get_subaccounts | Get information about subaccounts
[**privateGetTransfersGet**](OAIPrivateApi.md#privategettransfersget) | **GET** /private/get_transfers | Adds new entry to address book of given type
[**privateGetUserTradesByCurrencyAndTimeGet**](OAIPrivateApi.md#privategetusertradesbycurrencyandtimeget) | **GET** /private/get_user_trades_by_currency_and_time | Retrieve the latest user trades that have occurred for instruments in a specific currency symbol and within given time range.
[**privateGetUserTradesByCurrencyGet**](OAIPrivateApi.md#privategetusertradesbycurrencyget) | **GET** /private/get_user_trades_by_currency | Retrieve the latest user trades that have occurred for instruments in a specific currency symbol.
[**privateGetUserTradesByInstrumentAndTimeGet**](OAIPrivateApi.md#privategetusertradesbyinstrumentandtimeget) | **GET** /private/get_user_trades_by_instrument_and_time | Retrieve the latest user trades that have occurred for a specific instrument and within given time range.
[**privateGetUserTradesByInstrumentGet**](OAIPrivateApi.md#privategetusertradesbyinstrumentget) | **GET** /private/get_user_trades_by_instrument | Retrieve the latest user trades that have occurred for a specific instrument.
[**privateGetUserTradesByOrderGet**](OAIPrivateApi.md#privategetusertradesbyorderget) | **GET** /private/get_user_trades_by_order | Retrieve the list of user trades that was created for given order
[**privateGetWithdrawalsGet**](OAIPrivateApi.md#privategetwithdrawalsget) | **GET** /private/get_withdrawals | Retrieve the latest users withdrawals
[**privateRemoveFromAddressBookGet**](OAIPrivateApi.md#privateremovefromaddressbookget) | **GET** /private/remove_from_address_book | Adds new entry to address book of given type
[**privateSellGet**](OAIPrivateApi.md#privatesellget) | **GET** /private/sell | Places a sell order for an instrument.
[**privateSetAnnouncementAsReadGet**](OAIPrivateApi.md#privatesetannouncementasreadget) | **GET** /private/set_announcement_as_read | Marks an announcement as read, so it will not be shown in &#x60;get_new_announcements&#x60;.
[**privateSetEmailForSubaccountGet**](OAIPrivateApi.md#privatesetemailforsubaccountget) | **GET** /private/set_email_for_subaccount | Assign an email address to a subaccount. User will receive an email with confirmation link.
[**privateSetEmailLanguageGet**](OAIPrivateApi.md#privatesetemaillanguageget) | **GET** /private/set_email_language | Changes the language to be used for emails.
[**privateSetPasswordForSubaccountGet**](OAIPrivateApi.md#privatesetpasswordforsubaccountget) | **GET** /private/set_password_for_subaccount | Set the password for the subaccount
[**privateSubmitTransferToSubaccountGet**](OAIPrivateApi.md#privatesubmittransfertosubaccountget) | **GET** /private/submit_transfer_to_subaccount | Transfer funds to a subaccount.
[**privateSubmitTransferToUserGet**](OAIPrivateApi.md#privatesubmittransfertouserget) | **GET** /private/submit_transfer_to_user | Transfer funds to a another user.
[**privateToggleDepositAddressCreationGet**](OAIPrivateApi.md#privatetoggledepositaddresscreationget) | **GET** /private/toggle_deposit_address_creation | Enable or disable deposit address creation
[**privateToggleNotificationsFromSubaccountGet**](OAIPrivateApi.md#privatetogglenotificationsfromsubaccountget) | **GET** /private/toggle_notifications_from_subaccount | Enable or disable sending of notifications for the subaccount.
[**privateToggleSubaccountLoginGet**](OAIPrivateApi.md#privatetogglesubaccountloginget) | **GET** /private/toggle_subaccount_login | Enable or disable login for a subaccount. If login is disabled and a session for the subaccount exists, this session will be terminated.
[**privateWithdrawGet**](OAIPrivateApi.md#privatewithdrawget) | **GET** /private/withdraw | Creates a new withdrawal request


# **privateAddToAddressBookGet**
```objc
-(NSURLSessionTask*) privateAddToAddressBookGetWithCurrency: (NSString*) currency
    type: (NSString*) type
    address: (NSString*) address
    name: (NSString*) name
    tfa: (NSString*) tfa
        completionHandler: (void (^)(NSObject* output, NSError* error)) handler;
```

Adds new entry to address book of given type

### Example 
```objc
OAIDefaultConfiguration *apiConfig = [OAIDefaultConfiguration sharedConfig];
// Configure HTTP basic authorization (authentication scheme: bearerAuth)
[apiConfig setUsername:@"YOUR_USERNAME"];
[apiConfig setPassword:@"YOUR_PASSWORD"];


NSString* currency = @"currency_example"; // The currency symbol
NSString* type = @"type_example"; // Address book type
NSString* address = @"address_example"; // Address in currency format, it must be in address book
NSString* name = Main address; // Name of address book entry
NSString* tfa = @"tfa_example"; // TFA code, required when TFA is enabled for current account (optional)

OAIPrivateApi*apiInstance = [[OAIPrivateApi alloc] init];

// Adds new entry to address book of given type
[apiInstance privateAddToAddressBookGetWithCurrency:currency
              type:type
              address:address
              name:name
              tfa:tfa
          completionHandler: ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAIPrivateApi->privateAddToAddressBookGet: %@", error);
                        }
                    }];
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **NSString***| The currency symbol | 
 **type** | **NSString***| Address book type | 
 **address** | **NSString***| Address in currency format, it must be in address book | 
 **name** | **NSString***| Name of address book entry | 
 **tfa** | **NSString***| TFA code, required when TFA is enabled for current account | [optional] 

### Return type

**NSObject***

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateBuyGet**
```objc
-(NSURLSessionTask*) privateBuyGetWithInstrumentName: (NSString*) instrumentName
    amount: (NSNumber*) amount
    type: (NSString*) type
    label: (NSString*) label
    price: (NSNumber*) price
    timeInForce: (NSString*) timeInForce
    maxShow: (NSNumber*) maxShow
    postOnly: (NSNumber*) postOnly
    reduceOnly: (NSNumber*) reduceOnly
    stopPrice: (NSNumber*) stopPrice
    trigger: (NSString*) trigger
    advanced: (NSString*) advanced
        completionHandler: (void (^)(NSObject* output, NSError* error)) handler;
```

Places a buy order for an instrument.

### Example 
```objc
OAIDefaultConfiguration *apiConfig = [OAIDefaultConfiguration sharedConfig];
// Configure HTTP basic authorization (authentication scheme: bearerAuth)
[apiConfig setUsername:@"YOUR_USERNAME"];
[apiConfig setPassword:@"YOUR_PASSWORD"];


NSString* instrumentName = BTC-PERPETUAL; // Instrument name
NSNumber* amount = @56; // It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH
NSString* type = @"type_example"; // The order type, default: `\"limit\"` (optional)
NSString* label = @"label_example"; // user defined label for the order (maximum 32 characters) (optional)
NSNumber* price = @56; // <p>The order price in base currency (Only for limit and stop_limit orders)</p> <p>When adding order with advanced=usd, the field price should be the option price value in USD.</p> <p>When adding order with advanced=implv, the field price should be a value of implied volatility in percentages. For example,  price=100, means implied volatility of 100%</p> (optional)
NSString* timeInForce = @"good_til_cancelled"; // <p>Specifies how long the order remains in effect. Default `\"good_til_cancelled\"`</p> <ul> <li>`\"good_til_cancelled\"` - unfilled order remains in order book until cancelled</li> <li>`\"fill_or_kill\"` - execute a transaction immediately and completely or not at all</li> <li>`\"immediate_or_cancel\"` - execute a transaction immediately, and any portion of the order that cannot be immediately filled is cancelled</li> </ul> (optional) (default to @"good_til_cancelled")
NSNumber* maxShow = @1; // Maximum amount within an order to be shown to other customers, `0` for invisible order (optional) (default to @1)
NSNumber* postOnly = @(YES); // <p>If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.</p> <p>Only valid in combination with time_in_force=`\"good_til_cancelled\"`</p> (optional) (default to @(YES))
NSNumber* reduceOnly = @(NO); // If `true`, the order is considered reduce-only which is intended to only reduce a current position (optional) (default to @(NO))
NSNumber* stopPrice = @56; // Stop price, required for stop limit orders (Only for stop orders) (optional)
NSString* trigger = @"trigger_example"; // Defines trigger type, required for `\"stop_limit\"` order type (optional)
NSString* advanced = @"advanced_example"; // Advanced option order type. (Only for options) (optional)

OAIPrivateApi*apiInstance = [[OAIPrivateApi alloc] init];

// Places a buy order for an instrument.
[apiInstance privateBuyGetWithInstrumentName:instrumentName
              amount:amount
              type:type
              label:label
              price:price
              timeInForce:timeInForce
              maxShow:maxShow
              postOnly:postOnly
              reduceOnly:reduceOnly
              stopPrice:stopPrice
              trigger:trigger
              advanced:advanced
          completionHandler: ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAIPrivateApi->privateBuyGet: %@", error);
                        }
                    }];
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrumentName** | **NSString***| Instrument name | 
 **amount** | **NSNumber***| It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH | 
 **type** | **NSString***| The order type, default: &#x60;\&quot;limit\&quot;&#x60; | [optional] 
 **label** | **NSString***| user defined label for the order (maximum 32 characters) | [optional] 
 **price** | **NSNumber***| &lt;p&gt;The order price in base currency (Only for limit and stop_limit orders)&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;usd, the field price should be the option price value in USD.&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;implv, the field price should be a value of implied volatility in percentages. For example,  price&#x3D;100, means implied volatility of 100%&lt;/p&gt; | [optional] 
 **timeInForce** | **NSString***| &lt;p&gt;Specifies how long the order remains in effect. Default &#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; &lt;ul&gt; &lt;li&gt;&#x60;\&quot;good_til_cancelled\&quot;&#x60; - unfilled order remains in order book until cancelled&lt;/li&gt; &lt;li&gt;&#x60;\&quot;fill_or_kill\&quot;&#x60; - execute a transaction immediately and completely or not at all&lt;/li&gt; &lt;li&gt;&#x60;\&quot;immediate_or_cancel\&quot;&#x60; - execute a transaction immediately, and any portion of the order that cannot be immediately filled is cancelled&lt;/li&gt; &lt;/ul&gt; | [optional] [default to @&quot;good_til_cancelled&quot;]
 **maxShow** | **NSNumber***| Maximum amount within an order to be shown to other customers, &#x60;0&#x60; for invisible order | [optional] [default to @1]
 **postOnly** | **NSNumber***| &lt;p&gt;If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.&lt;/p&gt; &lt;p&gt;Only valid in combination with time_in_force&#x3D;&#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; | [optional] [default to @(YES)]
 **reduceOnly** | **NSNumber***| If &#x60;true&#x60;, the order is considered reduce-only which is intended to only reduce a current position | [optional] [default to @(NO)]
 **stopPrice** | **NSNumber***| Stop price, required for stop limit orders (Only for stop orders) | [optional] 
 **trigger** | **NSString***| Defines trigger type, required for &#x60;\&quot;stop_limit\&quot;&#x60; order type | [optional] 
 **advanced** | **NSString***| Advanced option order type. (Only for options) | [optional] 

### Return type

**NSObject***

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateCancelAllByCurrencyGet**
```objc
-(NSURLSessionTask*) privateCancelAllByCurrencyGetWithCurrency: (NSString*) currency
    kind: (NSString*) kind
    type: (NSString*) type
        completionHandler: (void (^)(NSObject* output, NSError* error)) handler;
```

Cancels all orders by currency, optionally filtered by instrument kind and/or order type.

### Example 
```objc
OAIDefaultConfiguration *apiConfig = [OAIDefaultConfiguration sharedConfig];
// Configure HTTP basic authorization (authentication scheme: bearerAuth)
[apiConfig setUsername:@"YOUR_USERNAME"];
[apiConfig setPassword:@"YOUR_PASSWORD"];


NSString* currency = @"currency_example"; // The currency symbol
NSString* kind = @"kind_example"; // Instrument kind, if not provided instruments of all kinds are considered (optional)
NSString* type = @"type_example"; // Order type - limit, stop or all, default - `all` (optional)

OAIPrivateApi*apiInstance = [[OAIPrivateApi alloc] init];

// Cancels all orders by currency, optionally filtered by instrument kind and/or order type.
[apiInstance privateCancelAllByCurrencyGetWithCurrency:currency
              kind:kind
              type:type
          completionHandler: ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAIPrivateApi->privateCancelAllByCurrencyGet: %@", error);
                        }
                    }];
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **NSString***| The currency symbol | 
 **kind** | **NSString***| Instrument kind, if not provided instruments of all kinds are considered | [optional] 
 **type** | **NSString***| Order type - limit, stop or all, default - &#x60;all&#x60; | [optional] 

### Return type

**NSObject***

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateCancelAllByInstrumentGet**
```objc
-(NSURLSessionTask*) privateCancelAllByInstrumentGetWithInstrumentName: (NSString*) instrumentName
    type: (NSString*) type
        completionHandler: (void (^)(NSObject* output, NSError* error)) handler;
```

Cancels all orders by instrument, optionally filtered by order type.

### Example 
```objc
OAIDefaultConfiguration *apiConfig = [OAIDefaultConfiguration sharedConfig];
// Configure HTTP basic authorization (authentication scheme: bearerAuth)
[apiConfig setUsername:@"YOUR_USERNAME"];
[apiConfig setPassword:@"YOUR_PASSWORD"];


NSString* instrumentName = BTC-PERPETUAL; // Instrument name
NSString* type = @"type_example"; // Order type - limit, stop or all, default - `all` (optional)

OAIPrivateApi*apiInstance = [[OAIPrivateApi alloc] init];

// Cancels all orders by instrument, optionally filtered by order type.
[apiInstance privateCancelAllByInstrumentGetWithInstrumentName:instrumentName
              type:type
          completionHandler: ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAIPrivateApi->privateCancelAllByInstrumentGet: %@", error);
                        }
                    }];
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrumentName** | **NSString***| Instrument name | 
 **type** | **NSString***| Order type - limit, stop or all, default - &#x60;all&#x60; | [optional] 

### Return type

**NSObject***

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateCancelAllGet**
```objc
-(NSURLSessionTask*) privateCancelAllGetWithCompletionHandler: 
        (void (^)(NSObject* output, NSError* error)) handler;
```

This method cancels all users orders and stop orders within all currencies and instrument kinds.

### Example 
```objc
OAIDefaultConfiguration *apiConfig = [OAIDefaultConfiguration sharedConfig];
// Configure HTTP basic authorization (authentication scheme: bearerAuth)
[apiConfig setUsername:@"YOUR_USERNAME"];
[apiConfig setPassword:@"YOUR_PASSWORD"];



OAIPrivateApi*apiInstance = [[OAIPrivateApi alloc] init];

// This method cancels all users orders and stop orders within all currencies and instrument kinds.
[apiInstance privateCancelAllGetWithCompletionHandler: 
          ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAIPrivateApi->privateCancelAllGet: %@", error);
                        }
                    }];
```

### Parameters
This endpoint does not need any parameter.

### Return type

**NSObject***

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateCancelGet**
```objc
-(NSURLSessionTask*) privateCancelGetWithOrderId: (NSString*) orderId
        completionHandler: (void (^)(NSObject* output, NSError* error)) handler;
```

Cancel an order, specified by order id

### Example 
```objc
OAIDefaultConfiguration *apiConfig = [OAIDefaultConfiguration sharedConfig];
// Configure HTTP basic authorization (authentication scheme: bearerAuth)
[apiConfig setUsername:@"YOUR_USERNAME"];
[apiConfig setPassword:@"YOUR_PASSWORD"];


NSString* orderId = ETH-100234; // The order id

OAIPrivateApi*apiInstance = [[OAIPrivateApi alloc] init];

// Cancel an order, specified by order id
[apiInstance privateCancelGetWithOrderId:orderId
          completionHandler: ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAIPrivateApi->privateCancelGet: %@", error);
                        }
                    }];
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **orderId** | **NSString***| The order id | 

### Return type

**NSObject***

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateCancelTransferByIdGet**
```objc
-(NSURLSessionTask*) privateCancelTransferByIdGetWithCurrency: (NSString*) currency
    _id: (NSNumber*) _id
    tfa: (NSString*) tfa
        completionHandler: (void (^)(NSObject* output, NSError* error)) handler;
```

Cancel transfer

### Example 
```objc
OAIDefaultConfiguration *apiConfig = [OAIDefaultConfiguration sharedConfig];
// Configure HTTP basic authorization (authentication scheme: bearerAuth)
[apiConfig setUsername:@"YOUR_USERNAME"];
[apiConfig setPassword:@"YOUR_PASSWORD"];


NSString* currency = @"currency_example"; // The currency symbol
NSNumber* _id = 12; // Id of transfer
NSString* tfa = @"tfa_example"; // TFA code, required when TFA is enabled for current account (optional)

OAIPrivateApi*apiInstance = [[OAIPrivateApi alloc] init];

// Cancel transfer
[apiInstance privateCancelTransferByIdGetWithCurrency:currency
              _id:_id
              tfa:tfa
          completionHandler: ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAIPrivateApi->privateCancelTransferByIdGet: %@", error);
                        }
                    }];
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **NSString***| The currency symbol | 
 **_id** | **NSNumber***| Id of transfer | 
 **tfa** | **NSString***| TFA code, required when TFA is enabled for current account | [optional] 

### Return type

**NSObject***

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateCancelWithdrawalGet**
```objc
-(NSURLSessionTask*) privateCancelWithdrawalGetWithCurrency: (NSString*) currency
    _id: (NSNumber*) _id
        completionHandler: (void (^)(NSObject* output, NSError* error)) handler;
```

Cancels withdrawal request

### Example 
```objc
OAIDefaultConfiguration *apiConfig = [OAIDefaultConfiguration sharedConfig];
// Configure HTTP basic authorization (authentication scheme: bearerAuth)
[apiConfig setUsername:@"YOUR_USERNAME"];
[apiConfig setPassword:@"YOUR_PASSWORD"];


NSString* currency = @"currency_example"; // The currency symbol
NSNumber* _id = 1; // The withdrawal id

OAIPrivateApi*apiInstance = [[OAIPrivateApi alloc] init];

// Cancels withdrawal request
[apiInstance privateCancelWithdrawalGetWithCurrency:currency
              _id:_id
          completionHandler: ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAIPrivateApi->privateCancelWithdrawalGet: %@", error);
                        }
                    }];
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **NSString***| The currency symbol | 
 **_id** | **NSNumber***| The withdrawal id | 

### Return type

**NSObject***

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateChangeSubaccountNameGet**
```objc
-(NSURLSessionTask*) privateChangeSubaccountNameGetWithSid: (NSNumber*) sid
    name: (NSString*) name
        completionHandler: (void (^)(NSObject* output, NSError* error)) handler;
```

Change the user name for a subaccount

### Example 
```objc
OAIDefaultConfiguration *apiConfig = [OAIDefaultConfiguration sharedConfig];
// Configure HTTP basic authorization (authentication scheme: bearerAuth)
[apiConfig setUsername:@"YOUR_USERNAME"];
[apiConfig setPassword:@"YOUR_PASSWORD"];


NSNumber* sid = @56; // The user id for the subaccount
NSString* name = newUserName; // The new user name

OAIPrivateApi*apiInstance = [[OAIPrivateApi alloc] init];

// Change the user name for a subaccount
[apiInstance privateChangeSubaccountNameGetWithSid:sid
              name:name
          completionHandler: ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAIPrivateApi->privateChangeSubaccountNameGet: %@", error);
                        }
                    }];
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **sid** | **NSNumber***| The user id for the subaccount | 
 **name** | **NSString***| The new user name | 

### Return type

**NSObject***

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateClosePositionGet**
```objc
-(NSURLSessionTask*) privateClosePositionGetWithInstrumentName: (NSString*) instrumentName
    type: (NSString*) type
    price: (NSNumber*) price
        completionHandler: (void (^)(NSObject* output, NSError* error)) handler;
```

Makes closing position reduce only order .

### Example 
```objc
OAIDefaultConfiguration *apiConfig = [OAIDefaultConfiguration sharedConfig];
// Configure HTTP basic authorization (authentication scheme: bearerAuth)
[apiConfig setUsername:@"YOUR_USERNAME"];
[apiConfig setPassword:@"YOUR_PASSWORD"];


NSString* instrumentName = BTC-PERPETUAL; // Instrument name
NSString* type = @"type_example"; // The order type
NSNumber* price = @56; // Optional price for limit order. (optional)

OAIPrivateApi*apiInstance = [[OAIPrivateApi alloc] init];

// Makes closing position reduce only order .
[apiInstance privateClosePositionGetWithInstrumentName:instrumentName
              type:type
              price:price
          completionHandler: ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAIPrivateApi->privateClosePositionGet: %@", error);
                        }
                    }];
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrumentName** | **NSString***| Instrument name | 
 **type** | **NSString***| The order type | 
 **price** | **NSNumber***| Optional price for limit order. | [optional] 

### Return type

**NSObject***

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateCreateDepositAddressGet**
```objc
-(NSURLSessionTask*) privateCreateDepositAddressGetWithCurrency: (NSString*) currency
        completionHandler: (void (^)(NSObject* output, NSError* error)) handler;
```

Creates deposit address in currency

### Example 
```objc
OAIDefaultConfiguration *apiConfig = [OAIDefaultConfiguration sharedConfig];
// Configure HTTP basic authorization (authentication scheme: bearerAuth)
[apiConfig setUsername:@"YOUR_USERNAME"];
[apiConfig setPassword:@"YOUR_PASSWORD"];


NSString* currency = @"currency_example"; // The currency symbol

OAIPrivateApi*apiInstance = [[OAIPrivateApi alloc] init];

// Creates deposit address in currency
[apiInstance privateCreateDepositAddressGetWithCurrency:currency
          completionHandler: ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAIPrivateApi->privateCreateDepositAddressGet: %@", error);
                        }
                    }];
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **NSString***| The currency symbol | 

### Return type

**NSObject***

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateCreateSubaccountGet**
```objc
-(NSURLSessionTask*) privateCreateSubaccountGetWithCompletionHandler: 
        (void (^)(NSObject* output, NSError* error)) handler;
```

Create a new subaccount

### Example 
```objc
OAIDefaultConfiguration *apiConfig = [OAIDefaultConfiguration sharedConfig];
// Configure HTTP basic authorization (authentication scheme: bearerAuth)
[apiConfig setUsername:@"YOUR_USERNAME"];
[apiConfig setPassword:@"YOUR_PASSWORD"];



OAIPrivateApi*apiInstance = [[OAIPrivateApi alloc] init];

// Create a new subaccount
[apiInstance privateCreateSubaccountGetWithCompletionHandler: 
          ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAIPrivateApi->privateCreateSubaccountGet: %@", error);
                        }
                    }];
```

### Parameters
This endpoint does not need any parameter.

### Return type

**NSObject***

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateDisableTfaForSubaccountGet**
```objc
-(NSURLSessionTask*) privateDisableTfaForSubaccountGetWithSid: (NSNumber*) sid
        completionHandler: (void (^)(NSObject* output, NSError* error)) handler;
```

Disable two factor authentication for a subaccount.

### Example 
```objc
OAIDefaultConfiguration *apiConfig = [OAIDefaultConfiguration sharedConfig];
// Configure HTTP basic authorization (authentication scheme: bearerAuth)
[apiConfig setUsername:@"YOUR_USERNAME"];
[apiConfig setPassword:@"YOUR_PASSWORD"];


NSNumber* sid = @56; // The user id for the subaccount

OAIPrivateApi*apiInstance = [[OAIPrivateApi alloc] init];

// Disable two factor authentication for a subaccount.
[apiInstance privateDisableTfaForSubaccountGetWithSid:sid
          completionHandler: ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAIPrivateApi->privateDisableTfaForSubaccountGet: %@", error);
                        }
                    }];
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **sid** | **NSNumber***| The user id for the subaccount | 

### Return type

**NSObject***

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateDisableTfaWithRecoveryCodeGet**
```objc
-(NSURLSessionTask*) privateDisableTfaWithRecoveryCodeGetWithPassword: (NSString*) password
    code: (NSString*) code
        completionHandler: (void (^)(NSObject* output, NSError* error)) handler;
```

Disables TFA with one time recovery code

### Example 
```objc
OAIDefaultConfiguration *apiConfig = [OAIDefaultConfiguration sharedConfig];
// Configure HTTP basic authorization (authentication scheme: bearerAuth)
[apiConfig setUsername:@"YOUR_USERNAME"];
[apiConfig setPassword:@"YOUR_PASSWORD"];


NSString* password = @"password_example"; // The password for the subaccount
NSString* code = @"code_example"; // One time recovery code

OAIPrivateApi*apiInstance = [[OAIPrivateApi alloc] init];

// Disables TFA with one time recovery code
[apiInstance privateDisableTfaWithRecoveryCodeGetWithPassword:password
              code:code
          completionHandler: ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAIPrivateApi->privateDisableTfaWithRecoveryCodeGet: %@", error);
                        }
                    }];
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **password** | **NSString***| The password for the subaccount | 
 **code** | **NSString***| One time recovery code | 

### Return type

**NSObject***

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateEditGet**
```objc
-(NSURLSessionTask*) privateEditGetWithOrderId: (NSString*) orderId
    amount: (NSNumber*) amount
    price: (NSNumber*) price
    postOnly: (NSNumber*) postOnly
    advanced: (NSString*) advanced
    stopPrice: (NSNumber*) stopPrice
        completionHandler: (void (^)(NSObject* output, NSError* error)) handler;
```

Change price, amount and/or other properties of an order.

### Example 
```objc
OAIDefaultConfiguration *apiConfig = [OAIDefaultConfiguration sharedConfig];
// Configure HTTP basic authorization (authentication scheme: bearerAuth)
[apiConfig setUsername:@"YOUR_USERNAME"];
[apiConfig setPassword:@"YOUR_PASSWORD"];


NSString* orderId = ETH-100234; // The order id
NSNumber* amount = @56; // It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH
NSNumber* price = @56; // <p>The order price in base currency.</p> <p>When editing an option order with advanced=usd, the field price should be the option price value in USD.</p> <p>When editing an option order with advanced=implv, the field price should be a value of implied volatility in percentages. For example,  price=100, means implied volatility of 100%</p>
NSNumber* postOnly = @(YES); // <p>If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.</p> <p>Only valid in combination with time_in_force=`\"good_til_cancelled\"`</p> (optional) (default to @(YES))
NSString* advanced = @"advanced_example"; // Advanced option order type. If you have posted an advanced option order, it is necessary to re-supply this parameter when editing it (Only for options) (optional)
NSNumber* stopPrice = @56; // Stop price, required for stop limit orders (Only for stop orders) (optional)

OAIPrivateApi*apiInstance = [[OAIPrivateApi alloc] init];

// Change price, amount and/or other properties of an order.
[apiInstance privateEditGetWithOrderId:orderId
              amount:amount
              price:price
              postOnly:postOnly
              advanced:advanced
              stopPrice:stopPrice
          completionHandler: ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAIPrivateApi->privateEditGet: %@", error);
                        }
                    }];
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **orderId** | **NSString***| The order id | 
 **amount** | **NSNumber***| It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH | 
 **price** | **NSNumber***| &lt;p&gt;The order price in base currency.&lt;/p&gt; &lt;p&gt;When editing an option order with advanced&#x3D;usd, the field price should be the option price value in USD.&lt;/p&gt; &lt;p&gt;When editing an option order with advanced&#x3D;implv, the field price should be a value of implied volatility in percentages. For example,  price&#x3D;100, means implied volatility of 100%&lt;/p&gt; | 
 **postOnly** | **NSNumber***| &lt;p&gt;If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.&lt;/p&gt; &lt;p&gt;Only valid in combination with time_in_force&#x3D;&#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; | [optional] [default to @(YES)]
 **advanced** | **NSString***| Advanced option order type. If you have posted an advanced option order, it is necessary to re-supply this parameter when editing it (Only for options) | [optional] 
 **stopPrice** | **NSNumber***| Stop price, required for stop limit orders (Only for stop orders) | [optional] 

### Return type

**NSObject***

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateGetAccountSummaryGet**
```objc
-(NSURLSessionTask*) privateGetAccountSummaryGetWithCurrency: (NSString*) currency
    extended: (NSNumber*) extended
        completionHandler: (void (^)(NSObject* output, NSError* error)) handler;
```

Retrieves user account summary.

### Example 
```objc
OAIDefaultConfiguration *apiConfig = [OAIDefaultConfiguration sharedConfig];
// Configure HTTP basic authorization (authentication scheme: bearerAuth)
[apiConfig setUsername:@"YOUR_USERNAME"];
[apiConfig setPassword:@"YOUR_PASSWORD"];


NSString* currency = @"currency_example"; // The currency symbol
NSNumber* extended = false; // Include additional fields (optional)

OAIPrivateApi*apiInstance = [[OAIPrivateApi alloc] init];

// Retrieves user account summary.
[apiInstance privateGetAccountSummaryGetWithCurrency:currency
              extended:extended
          completionHandler: ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAIPrivateApi->privateGetAccountSummaryGet: %@", error);
                        }
                    }];
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **NSString***| The currency symbol | 
 **extended** | **NSNumber***| Include additional fields | [optional] 

### Return type

**NSObject***

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateGetAddressBookGet**
```objc
-(NSURLSessionTask*) privateGetAddressBookGetWithCurrency: (NSString*) currency
    type: (NSString*) type
        completionHandler: (void (^)(NSObject* output, NSError* error)) handler;
```

Retrieves address book of given type

### Example 
```objc
OAIDefaultConfiguration *apiConfig = [OAIDefaultConfiguration sharedConfig];
// Configure HTTP basic authorization (authentication scheme: bearerAuth)
[apiConfig setUsername:@"YOUR_USERNAME"];
[apiConfig setPassword:@"YOUR_PASSWORD"];


NSString* currency = @"currency_example"; // The currency symbol
NSString* type = @"type_example"; // Address book type

OAIPrivateApi*apiInstance = [[OAIPrivateApi alloc] init];

// Retrieves address book of given type
[apiInstance privateGetAddressBookGetWithCurrency:currency
              type:type
          completionHandler: ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAIPrivateApi->privateGetAddressBookGet: %@", error);
                        }
                    }];
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **NSString***| The currency symbol | 
 **type** | **NSString***| Address book type | 

### Return type

**NSObject***

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateGetCurrentDepositAddressGet**
```objc
-(NSURLSessionTask*) privateGetCurrentDepositAddressGetWithCurrency: (NSString*) currency
        completionHandler: (void (^)(NSObject* output, NSError* error)) handler;
```

Retrieve deposit address for currency

### Example 
```objc
OAIDefaultConfiguration *apiConfig = [OAIDefaultConfiguration sharedConfig];
// Configure HTTP basic authorization (authentication scheme: bearerAuth)
[apiConfig setUsername:@"YOUR_USERNAME"];
[apiConfig setPassword:@"YOUR_PASSWORD"];


NSString* currency = @"currency_example"; // The currency symbol

OAIPrivateApi*apiInstance = [[OAIPrivateApi alloc] init];

// Retrieve deposit address for currency
[apiInstance privateGetCurrentDepositAddressGetWithCurrency:currency
          completionHandler: ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAIPrivateApi->privateGetCurrentDepositAddressGet: %@", error);
                        }
                    }];
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **NSString***| The currency symbol | 

### Return type

**NSObject***

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateGetDepositsGet**
```objc
-(NSURLSessionTask*) privateGetDepositsGetWithCurrency: (NSString*) currency
    count: (NSNumber*) count
    offset: (NSNumber*) offset
        completionHandler: (void (^)(NSObject* output, NSError* error)) handler;
```

Retrieve the latest users deposits

### Example 
```objc
OAIDefaultConfiguration *apiConfig = [OAIDefaultConfiguration sharedConfig];
// Configure HTTP basic authorization (authentication scheme: bearerAuth)
[apiConfig setUsername:@"YOUR_USERNAME"];
[apiConfig setPassword:@"YOUR_PASSWORD"];


NSString* currency = @"currency_example"; // The currency symbol
NSNumber* count = @56; // Number of requested items, default - `10` (optional)
NSNumber* offset = 10; // The offset for pagination, default - `0` (optional)

OAIPrivateApi*apiInstance = [[OAIPrivateApi alloc] init];

// Retrieve the latest users deposits
[apiInstance privateGetDepositsGetWithCurrency:currency
              count:count
              offset:offset
          completionHandler: ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAIPrivateApi->privateGetDepositsGet: %@", error);
                        }
                    }];
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **NSString***| The currency symbol | 
 **count** | **NSNumber***| Number of requested items, default - &#x60;10&#x60; | [optional] 
 **offset** | **NSNumber***| The offset for pagination, default - &#x60;0&#x60; | [optional] 

### Return type

**NSObject***

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateGetEmailLanguageGet**
```objc
-(NSURLSessionTask*) privateGetEmailLanguageGetWithCompletionHandler: 
        (void (^)(NSObject* output, NSError* error)) handler;
```

Retrieves the language to be used for emails.

### Example 
```objc
OAIDefaultConfiguration *apiConfig = [OAIDefaultConfiguration sharedConfig];
// Configure HTTP basic authorization (authentication scheme: bearerAuth)
[apiConfig setUsername:@"YOUR_USERNAME"];
[apiConfig setPassword:@"YOUR_PASSWORD"];



OAIPrivateApi*apiInstance = [[OAIPrivateApi alloc] init];

// Retrieves the language to be used for emails.
[apiInstance privateGetEmailLanguageGetWithCompletionHandler: 
          ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAIPrivateApi->privateGetEmailLanguageGet: %@", error);
                        }
                    }];
```

### Parameters
This endpoint does not need any parameter.

### Return type

**NSObject***

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateGetMarginsGet**
```objc
-(NSURLSessionTask*) privateGetMarginsGetWithInstrumentName: (NSString*) instrumentName
    amount: (NSNumber*) amount
    price: (NSNumber*) price
        completionHandler: (void (^)(NSObject* output, NSError* error)) handler;
```

Get margins for given instrument, amount and price.

### Example 
```objc
OAIDefaultConfiguration *apiConfig = [OAIDefaultConfiguration sharedConfig];
// Configure HTTP basic authorization (authentication scheme: bearerAuth)
[apiConfig setUsername:@"YOUR_USERNAME"];
[apiConfig setPassword:@"YOUR_PASSWORD"];


NSString* instrumentName = BTC-PERPETUAL; // Instrument name
NSNumber* amount = 1; // Amount, integer for future, float for option. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH.
NSNumber* price = @56; // Price

OAIPrivateApi*apiInstance = [[OAIPrivateApi alloc] init];

// Get margins for given instrument, amount and price.
[apiInstance privateGetMarginsGetWithInstrumentName:instrumentName
              amount:amount
              price:price
          completionHandler: ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAIPrivateApi->privateGetMarginsGet: %@", error);
                        }
                    }];
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrumentName** | **NSString***| Instrument name | 
 **amount** | **NSNumber***| Amount, integer for future, float for option. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH. | 
 **price** | **NSNumber***| Price | 

### Return type

**NSObject***

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateGetNewAnnouncementsGet**
```objc
-(NSURLSessionTask*) privateGetNewAnnouncementsGetWithCompletionHandler: 
        (void (^)(NSObject* output, NSError* error)) handler;
```

Retrieves announcements that have not been marked read by the user.

### Example 
```objc
OAIDefaultConfiguration *apiConfig = [OAIDefaultConfiguration sharedConfig];
// Configure HTTP basic authorization (authentication scheme: bearerAuth)
[apiConfig setUsername:@"YOUR_USERNAME"];
[apiConfig setPassword:@"YOUR_PASSWORD"];



OAIPrivateApi*apiInstance = [[OAIPrivateApi alloc] init];

// Retrieves announcements that have not been marked read by the user.
[apiInstance privateGetNewAnnouncementsGetWithCompletionHandler: 
          ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAIPrivateApi->privateGetNewAnnouncementsGet: %@", error);
                        }
                    }];
```

### Parameters
This endpoint does not need any parameter.

### Return type

**NSObject***

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateGetOpenOrdersByCurrencyGet**
```objc
-(NSURLSessionTask*) privateGetOpenOrdersByCurrencyGetWithCurrency: (NSString*) currency
    kind: (NSString*) kind
    type: (NSString*) type
        completionHandler: (void (^)(NSObject* output, NSError* error)) handler;
```

Retrieves list of user's open orders.

### Example 
```objc
OAIDefaultConfiguration *apiConfig = [OAIDefaultConfiguration sharedConfig];
// Configure HTTP basic authorization (authentication scheme: bearerAuth)
[apiConfig setUsername:@"YOUR_USERNAME"];
[apiConfig setPassword:@"YOUR_PASSWORD"];


NSString* currency = @"currency_example"; // The currency symbol
NSString* kind = @"kind_example"; // Instrument kind, if not provided instruments of all kinds are considered (optional)
NSString* type = @"type_example"; // Order type, default - `all` (optional)

OAIPrivateApi*apiInstance = [[OAIPrivateApi alloc] init];

// Retrieves list of user's open orders.
[apiInstance privateGetOpenOrdersByCurrencyGetWithCurrency:currency
              kind:kind
              type:type
          completionHandler: ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAIPrivateApi->privateGetOpenOrdersByCurrencyGet: %@", error);
                        }
                    }];
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **NSString***| The currency symbol | 
 **kind** | **NSString***| Instrument kind, if not provided instruments of all kinds are considered | [optional] 
 **type** | **NSString***| Order type, default - &#x60;all&#x60; | [optional] 

### Return type

**NSObject***

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateGetOpenOrdersByInstrumentGet**
```objc
-(NSURLSessionTask*) privateGetOpenOrdersByInstrumentGetWithInstrumentName: (NSString*) instrumentName
    type: (NSString*) type
        completionHandler: (void (^)(NSObject* output, NSError* error)) handler;
```

Retrieves list of user's open orders within given Instrument.

### Example 
```objc
OAIDefaultConfiguration *apiConfig = [OAIDefaultConfiguration sharedConfig];
// Configure HTTP basic authorization (authentication scheme: bearerAuth)
[apiConfig setUsername:@"YOUR_USERNAME"];
[apiConfig setPassword:@"YOUR_PASSWORD"];


NSString* instrumentName = BTC-PERPETUAL; // Instrument name
NSString* type = @"type_example"; // Order type, default - `all` (optional)

OAIPrivateApi*apiInstance = [[OAIPrivateApi alloc] init];

// Retrieves list of user's open orders within given Instrument.
[apiInstance privateGetOpenOrdersByInstrumentGetWithInstrumentName:instrumentName
              type:type
          completionHandler: ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAIPrivateApi->privateGetOpenOrdersByInstrumentGet: %@", error);
                        }
                    }];
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrumentName** | **NSString***| Instrument name | 
 **type** | **NSString***| Order type, default - &#x60;all&#x60; | [optional] 

### Return type

**NSObject***

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateGetOrderHistoryByCurrencyGet**
```objc
-(NSURLSessionTask*) privateGetOrderHistoryByCurrencyGetWithCurrency: (NSString*) currency
    kind: (NSString*) kind
    count: (NSNumber*) count
    offset: (NSNumber*) offset
    includeOld: (NSNumber*) includeOld
    includeUnfilled: (NSNumber*) includeUnfilled
        completionHandler: (void (^)(NSObject* output, NSError* error)) handler;
```

Retrieves history of orders that have been partially or fully filled.

### Example 
```objc
OAIDefaultConfiguration *apiConfig = [OAIDefaultConfiguration sharedConfig];
// Configure HTTP basic authorization (authentication scheme: bearerAuth)
[apiConfig setUsername:@"YOUR_USERNAME"];
[apiConfig setPassword:@"YOUR_PASSWORD"];


NSString* currency = @"currency_example"; // The currency symbol
NSString* kind = @"kind_example"; // Instrument kind, if not provided instruments of all kinds are considered (optional)
NSNumber* count = @56; // Number of requested items, default - `20` (optional)
NSNumber* offset = 10; // The offset for pagination, default - `0` (optional)
NSNumber* includeOld = false; // Include in result orders older than 2 days, default - `false` (optional)
NSNumber* includeUnfilled = false; // Include in result fully unfilled closed orders, default - `false` (optional)

OAIPrivateApi*apiInstance = [[OAIPrivateApi alloc] init];

// Retrieves history of orders that have been partially or fully filled.
[apiInstance privateGetOrderHistoryByCurrencyGetWithCurrency:currency
              kind:kind
              count:count
              offset:offset
              includeOld:includeOld
              includeUnfilled:includeUnfilled
          completionHandler: ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAIPrivateApi->privateGetOrderHistoryByCurrencyGet: %@", error);
                        }
                    }];
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **NSString***| The currency symbol | 
 **kind** | **NSString***| Instrument kind, if not provided instruments of all kinds are considered | [optional] 
 **count** | **NSNumber***| Number of requested items, default - &#x60;20&#x60; | [optional] 
 **offset** | **NSNumber***| The offset for pagination, default - &#x60;0&#x60; | [optional] 
 **includeOld** | **NSNumber***| Include in result orders older than 2 days, default - &#x60;false&#x60; | [optional] 
 **includeUnfilled** | **NSNumber***| Include in result fully unfilled closed orders, default - &#x60;false&#x60; | [optional] 

### Return type

**NSObject***

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateGetOrderHistoryByInstrumentGet**
```objc
-(NSURLSessionTask*) privateGetOrderHistoryByInstrumentGetWithInstrumentName: (NSString*) instrumentName
    count: (NSNumber*) count
    offset: (NSNumber*) offset
    includeOld: (NSNumber*) includeOld
    includeUnfilled: (NSNumber*) includeUnfilled
        completionHandler: (void (^)(NSObject* output, NSError* error)) handler;
```

Retrieves history of orders that have been partially or fully filled.

### Example 
```objc
OAIDefaultConfiguration *apiConfig = [OAIDefaultConfiguration sharedConfig];
// Configure HTTP basic authorization (authentication scheme: bearerAuth)
[apiConfig setUsername:@"YOUR_USERNAME"];
[apiConfig setPassword:@"YOUR_PASSWORD"];


NSString* instrumentName = BTC-PERPETUAL; // Instrument name
NSNumber* count = @56; // Number of requested items, default - `20` (optional)
NSNumber* offset = 10; // The offset for pagination, default - `0` (optional)
NSNumber* includeOld = false; // Include in result orders older than 2 days, default - `false` (optional)
NSNumber* includeUnfilled = false; // Include in result fully unfilled closed orders, default - `false` (optional)

OAIPrivateApi*apiInstance = [[OAIPrivateApi alloc] init];

// Retrieves history of orders that have been partially or fully filled.
[apiInstance privateGetOrderHistoryByInstrumentGetWithInstrumentName:instrumentName
              count:count
              offset:offset
              includeOld:includeOld
              includeUnfilled:includeUnfilled
          completionHandler: ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAIPrivateApi->privateGetOrderHistoryByInstrumentGet: %@", error);
                        }
                    }];
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrumentName** | **NSString***| Instrument name | 
 **count** | **NSNumber***| Number of requested items, default - &#x60;20&#x60; | [optional] 
 **offset** | **NSNumber***| The offset for pagination, default - &#x60;0&#x60; | [optional] 
 **includeOld** | **NSNumber***| Include in result orders older than 2 days, default - &#x60;false&#x60; | [optional] 
 **includeUnfilled** | **NSNumber***| Include in result fully unfilled closed orders, default - &#x60;false&#x60; | [optional] 

### Return type

**NSObject***

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateGetOrderMarginByIdsGet**
```objc
-(NSURLSessionTask*) privateGetOrderMarginByIdsGetWithIds: (NSArray<NSString*>*) ids
        completionHandler: (void (^)(NSObject* output, NSError* error)) handler;
```

Retrieves initial margins of given orders

### Example 
```objc
OAIDefaultConfiguration *apiConfig = [OAIDefaultConfiguration sharedConfig];
// Configure HTTP basic authorization (authentication scheme: bearerAuth)
[apiConfig setUsername:@"YOUR_USERNAME"];
[apiConfig setPassword:@"YOUR_PASSWORD"];


NSArray<NSString*>* ids = @[@"ids_example"]; // Ids of orders

OAIPrivateApi*apiInstance = [[OAIPrivateApi alloc] init];

// Retrieves initial margins of given orders
[apiInstance privateGetOrderMarginByIdsGetWithIds:ids
          completionHandler: ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAIPrivateApi->privateGetOrderMarginByIdsGet: %@", error);
                        }
                    }];
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **ids** | [**NSArray&lt;NSString*&gt;***](NSString*.md)| Ids of orders | 

### Return type

**NSObject***

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateGetOrderStateGet**
```objc
-(NSURLSessionTask*) privateGetOrderStateGetWithOrderId: (NSString*) orderId
        completionHandler: (void (^)(NSObject* output, NSError* error)) handler;
```

Retrieve the current state of an order.

### Example 
```objc
OAIDefaultConfiguration *apiConfig = [OAIDefaultConfiguration sharedConfig];
// Configure HTTP basic authorization (authentication scheme: bearerAuth)
[apiConfig setUsername:@"YOUR_USERNAME"];
[apiConfig setPassword:@"YOUR_PASSWORD"];


NSString* orderId = ETH-100234; // The order id

OAIPrivateApi*apiInstance = [[OAIPrivateApi alloc] init];

// Retrieve the current state of an order.
[apiInstance privateGetOrderStateGetWithOrderId:orderId
          completionHandler: ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAIPrivateApi->privateGetOrderStateGet: %@", error);
                        }
                    }];
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **orderId** | **NSString***| The order id | 

### Return type

**NSObject***

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateGetPositionGet**
```objc
-(NSURLSessionTask*) privateGetPositionGetWithInstrumentName: (NSString*) instrumentName
        completionHandler: (void (^)(NSObject* output, NSError* error)) handler;
```

Retrieve user position.

### Example 
```objc
OAIDefaultConfiguration *apiConfig = [OAIDefaultConfiguration sharedConfig];
// Configure HTTP basic authorization (authentication scheme: bearerAuth)
[apiConfig setUsername:@"YOUR_USERNAME"];
[apiConfig setPassword:@"YOUR_PASSWORD"];


NSString* instrumentName = BTC-PERPETUAL; // Instrument name

OAIPrivateApi*apiInstance = [[OAIPrivateApi alloc] init];

// Retrieve user position.
[apiInstance privateGetPositionGetWithInstrumentName:instrumentName
          completionHandler: ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAIPrivateApi->privateGetPositionGet: %@", error);
                        }
                    }];
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrumentName** | **NSString***| Instrument name | 

### Return type

**NSObject***

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateGetPositionsGet**
```objc
-(NSURLSessionTask*) privateGetPositionsGetWithCurrency: (NSString*) currency
    kind: (NSString*) kind
        completionHandler: (void (^)(NSObject* output, NSError* error)) handler;
```

Retrieve user positions.

### Example 
```objc
OAIDefaultConfiguration *apiConfig = [OAIDefaultConfiguration sharedConfig];
// Configure HTTP basic authorization (authentication scheme: bearerAuth)
[apiConfig setUsername:@"YOUR_USERNAME"];
[apiConfig setPassword:@"YOUR_PASSWORD"];


NSString* currency = @"currency_example"; // 
NSString* kind = @"kind_example"; // Kind filter on positions (optional)

OAIPrivateApi*apiInstance = [[OAIPrivateApi alloc] init];

// Retrieve user positions.
[apiInstance privateGetPositionsGetWithCurrency:currency
              kind:kind
          completionHandler: ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAIPrivateApi->privateGetPositionsGet: %@", error);
                        }
                    }];
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **NSString***|  | 
 **kind** | **NSString***| Kind filter on positions | [optional] 

### Return type

**NSObject***

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateGetSettlementHistoryByCurrencyGet**
```objc
-(NSURLSessionTask*) privateGetSettlementHistoryByCurrencyGetWithCurrency: (NSString*) currency
    type: (NSString*) type
    count: (NSNumber*) count
        completionHandler: (void (^)(NSObject* output, NSError* error)) handler;
```

Retrieves settlement, delivery and bankruptcy events that have affected your account.

### Example 
```objc
OAIDefaultConfiguration *apiConfig = [OAIDefaultConfiguration sharedConfig];
// Configure HTTP basic authorization (authentication scheme: bearerAuth)
[apiConfig setUsername:@"YOUR_USERNAME"];
[apiConfig setPassword:@"YOUR_PASSWORD"];


NSString* currency = @"currency_example"; // The currency symbol
NSString* type = @"type_example"; // Settlement type (optional)
NSNumber* count = @56; // Number of requested items, default - `20` (optional)

OAIPrivateApi*apiInstance = [[OAIPrivateApi alloc] init];

// Retrieves settlement, delivery and bankruptcy events that have affected your account.
[apiInstance privateGetSettlementHistoryByCurrencyGetWithCurrency:currency
              type:type
              count:count
          completionHandler: ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAIPrivateApi->privateGetSettlementHistoryByCurrencyGet: %@", error);
                        }
                    }];
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **NSString***| The currency symbol | 
 **type** | **NSString***| Settlement type | [optional] 
 **count** | **NSNumber***| Number of requested items, default - &#x60;20&#x60; | [optional] 

### Return type

**NSObject***

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateGetSettlementHistoryByInstrumentGet**
```objc
-(NSURLSessionTask*) privateGetSettlementHistoryByInstrumentGetWithInstrumentName: (NSString*) instrumentName
    type: (NSString*) type
    count: (NSNumber*) count
        completionHandler: (void (^)(NSObject* output, NSError* error)) handler;
```

Retrieves public settlement, delivery and bankruptcy events filtered by instrument name

### Example 
```objc
OAIDefaultConfiguration *apiConfig = [OAIDefaultConfiguration sharedConfig];
// Configure HTTP basic authorization (authentication scheme: bearerAuth)
[apiConfig setUsername:@"YOUR_USERNAME"];
[apiConfig setPassword:@"YOUR_PASSWORD"];


NSString* instrumentName = BTC-PERPETUAL; // Instrument name
NSString* type = @"type_example"; // Settlement type (optional)
NSNumber* count = @56; // Number of requested items, default - `20` (optional)

OAIPrivateApi*apiInstance = [[OAIPrivateApi alloc] init];

// Retrieves public settlement, delivery and bankruptcy events filtered by instrument name
[apiInstance privateGetSettlementHistoryByInstrumentGetWithInstrumentName:instrumentName
              type:type
              count:count
          completionHandler: ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAIPrivateApi->privateGetSettlementHistoryByInstrumentGet: %@", error);
                        }
                    }];
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrumentName** | **NSString***| Instrument name | 
 **type** | **NSString***| Settlement type | [optional] 
 **count** | **NSNumber***| Number of requested items, default - &#x60;20&#x60; | [optional] 

### Return type

**NSObject***

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateGetSubaccountsGet**
```objc
-(NSURLSessionTask*) privateGetSubaccountsGetWithWithPortfolio: (NSNumber*) withPortfolio
        completionHandler: (void (^)(NSObject* output, NSError* error)) handler;
```

Get information about subaccounts

### Example 
```objc
OAIDefaultConfiguration *apiConfig = [OAIDefaultConfiguration sharedConfig];
// Configure HTTP basic authorization (authentication scheme: bearerAuth)
[apiConfig setUsername:@"YOUR_USERNAME"];
[apiConfig setPassword:@"YOUR_PASSWORD"];


NSNumber* withPortfolio = @56; //  (optional)

OAIPrivateApi*apiInstance = [[OAIPrivateApi alloc] init];

// Get information about subaccounts
[apiInstance privateGetSubaccountsGetWithWithPortfolio:withPortfolio
          completionHandler: ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAIPrivateApi->privateGetSubaccountsGet: %@", error);
                        }
                    }];
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **withPortfolio** | **NSNumber***|  | [optional] 

### Return type

**NSObject***

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateGetTransfersGet**
```objc
-(NSURLSessionTask*) privateGetTransfersGetWithCurrency: (NSString*) currency
    count: (NSNumber*) count
    offset: (NSNumber*) offset
        completionHandler: (void (^)(NSObject* output, NSError* error)) handler;
```

Adds new entry to address book of given type

### Example 
```objc
OAIDefaultConfiguration *apiConfig = [OAIDefaultConfiguration sharedConfig];
// Configure HTTP basic authorization (authentication scheme: bearerAuth)
[apiConfig setUsername:@"YOUR_USERNAME"];
[apiConfig setPassword:@"YOUR_PASSWORD"];


NSString* currency = @"currency_example"; // The currency symbol
NSNumber* count = @56; // Number of requested items, default - `10` (optional)
NSNumber* offset = 10; // The offset for pagination, default - `0` (optional)

OAIPrivateApi*apiInstance = [[OAIPrivateApi alloc] init];

// Adds new entry to address book of given type
[apiInstance privateGetTransfersGetWithCurrency:currency
              count:count
              offset:offset
          completionHandler: ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAIPrivateApi->privateGetTransfersGet: %@", error);
                        }
                    }];
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **NSString***| The currency symbol | 
 **count** | **NSNumber***| Number of requested items, default - &#x60;10&#x60; | [optional] 
 **offset** | **NSNumber***| The offset for pagination, default - &#x60;0&#x60; | [optional] 

### Return type

**NSObject***

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateGetUserTradesByCurrencyAndTimeGet**
```objc
-(NSURLSessionTask*) privateGetUserTradesByCurrencyAndTimeGetWithCurrency: (NSString*) currency
    startTimestamp: (NSNumber*) startTimestamp
    endTimestamp: (NSNumber*) endTimestamp
    kind: (NSString*) kind
    count: (NSNumber*) count
    includeOld: (NSNumber*) includeOld
    sorting: (NSString*) sorting
        completionHandler: (void (^)(NSObject* output, NSError* error)) handler;
```

Retrieve the latest user trades that have occurred for instruments in a specific currency symbol and within given time range.

### Example 
```objc
OAIDefaultConfiguration *apiConfig = [OAIDefaultConfiguration sharedConfig];
// Configure HTTP basic authorization (authentication scheme: bearerAuth)
[apiConfig setUsername:@"YOUR_USERNAME"];
[apiConfig setPassword:@"YOUR_PASSWORD"];


NSString* currency = @"currency_example"; // The currency symbol
NSNumber* startTimestamp = 1536569522277; // The earliest timestamp to return result for
NSNumber* endTimestamp = 1536569522277; // The most recent timestamp to return result for
NSString* kind = @"kind_example"; // Instrument kind, if not provided instruments of all kinds are considered (optional)
NSNumber* count = @56; // Number of requested items, default - `10` (optional)
NSNumber* includeOld = @56; // Include trades older than 7 days, default - `false` (optional)
NSString* sorting = @"sorting_example"; // Direction of results sorting (`default` value means no sorting, results will be returned in order in which they left the database) (optional)

OAIPrivateApi*apiInstance = [[OAIPrivateApi alloc] init];

// Retrieve the latest user trades that have occurred for instruments in a specific currency symbol and within given time range.
[apiInstance privateGetUserTradesByCurrencyAndTimeGetWithCurrency:currency
              startTimestamp:startTimestamp
              endTimestamp:endTimestamp
              kind:kind
              count:count
              includeOld:includeOld
              sorting:sorting
          completionHandler: ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAIPrivateApi->privateGetUserTradesByCurrencyAndTimeGet: %@", error);
                        }
                    }];
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **NSString***| The currency symbol | 
 **startTimestamp** | **NSNumber***| The earliest timestamp to return result for | 
 **endTimestamp** | **NSNumber***| The most recent timestamp to return result for | 
 **kind** | **NSString***| Instrument kind, if not provided instruments of all kinds are considered | [optional] 
 **count** | **NSNumber***| Number of requested items, default - &#x60;10&#x60; | [optional] 
 **includeOld** | **NSNumber***| Include trades older than 7 days, default - &#x60;false&#x60; | [optional] 
 **sorting** | **NSString***| Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) | [optional] 

### Return type

**NSObject***

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateGetUserTradesByCurrencyGet**
```objc
-(NSURLSessionTask*) privateGetUserTradesByCurrencyGetWithCurrency: (NSString*) currency
    kind: (NSString*) kind
    startId: (NSString*) startId
    endId: (NSString*) endId
    count: (NSNumber*) count
    includeOld: (NSNumber*) includeOld
    sorting: (NSString*) sorting
        completionHandler: (void (^)(NSObject* output, NSError* error)) handler;
```

Retrieve the latest user trades that have occurred for instruments in a specific currency symbol.

### Example 
```objc
OAIDefaultConfiguration *apiConfig = [OAIDefaultConfiguration sharedConfig];
// Configure HTTP basic authorization (authentication scheme: bearerAuth)
[apiConfig setUsername:@"YOUR_USERNAME"];
[apiConfig setPassword:@"YOUR_PASSWORD"];


NSString* currency = @"currency_example"; // The currency symbol
NSString* kind = @"kind_example"; // Instrument kind, if not provided instruments of all kinds are considered (optional)
NSString* startId = @"startId_example"; // The ID number of the first trade to be returned (optional)
NSString* endId = @"endId_example"; // The ID number of the last trade to be returned (optional)
NSNumber* count = @56; // Number of requested items, default - `10` (optional)
NSNumber* includeOld = @56; // Include trades older than 7 days, default - `false` (optional)
NSString* sorting = @"sorting_example"; // Direction of results sorting (`default` value means no sorting, results will be returned in order in which they left the database) (optional)

OAIPrivateApi*apiInstance = [[OAIPrivateApi alloc] init];

// Retrieve the latest user trades that have occurred for instruments in a specific currency symbol.
[apiInstance privateGetUserTradesByCurrencyGetWithCurrency:currency
              kind:kind
              startId:startId
              endId:endId
              count:count
              includeOld:includeOld
              sorting:sorting
          completionHandler: ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAIPrivateApi->privateGetUserTradesByCurrencyGet: %@", error);
                        }
                    }];
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **NSString***| The currency symbol | 
 **kind** | **NSString***| Instrument kind, if not provided instruments of all kinds are considered | [optional] 
 **startId** | **NSString***| The ID number of the first trade to be returned | [optional] 
 **endId** | **NSString***| The ID number of the last trade to be returned | [optional] 
 **count** | **NSNumber***| Number of requested items, default - &#x60;10&#x60; | [optional] 
 **includeOld** | **NSNumber***| Include trades older than 7 days, default - &#x60;false&#x60; | [optional] 
 **sorting** | **NSString***| Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) | [optional] 

### Return type

**NSObject***

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateGetUserTradesByInstrumentAndTimeGet**
```objc
-(NSURLSessionTask*) privateGetUserTradesByInstrumentAndTimeGetWithInstrumentName: (NSString*) instrumentName
    startTimestamp: (NSNumber*) startTimestamp
    endTimestamp: (NSNumber*) endTimestamp
    count: (NSNumber*) count
    includeOld: (NSNumber*) includeOld
    sorting: (NSString*) sorting
        completionHandler: (void (^)(NSObject* output, NSError* error)) handler;
```

Retrieve the latest user trades that have occurred for a specific instrument and within given time range.

### Example 
```objc
OAIDefaultConfiguration *apiConfig = [OAIDefaultConfiguration sharedConfig];
// Configure HTTP basic authorization (authentication scheme: bearerAuth)
[apiConfig setUsername:@"YOUR_USERNAME"];
[apiConfig setPassword:@"YOUR_PASSWORD"];


NSString* instrumentName = BTC-PERPETUAL; // Instrument name
NSNumber* startTimestamp = 1536569522277; // The earliest timestamp to return result for
NSNumber* endTimestamp = 1536569522277; // The most recent timestamp to return result for
NSNumber* count = @56; // Number of requested items, default - `10` (optional)
NSNumber* includeOld = @56; // Include trades older than 7 days, default - `false` (optional)
NSString* sorting = @"sorting_example"; // Direction of results sorting (`default` value means no sorting, results will be returned in order in which they left the database) (optional)

OAIPrivateApi*apiInstance = [[OAIPrivateApi alloc] init];

// Retrieve the latest user trades that have occurred for a specific instrument and within given time range.
[apiInstance privateGetUserTradesByInstrumentAndTimeGetWithInstrumentName:instrumentName
              startTimestamp:startTimestamp
              endTimestamp:endTimestamp
              count:count
              includeOld:includeOld
              sorting:sorting
          completionHandler: ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAIPrivateApi->privateGetUserTradesByInstrumentAndTimeGet: %@", error);
                        }
                    }];
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrumentName** | **NSString***| Instrument name | 
 **startTimestamp** | **NSNumber***| The earliest timestamp to return result for | 
 **endTimestamp** | **NSNumber***| The most recent timestamp to return result for | 
 **count** | **NSNumber***| Number of requested items, default - &#x60;10&#x60; | [optional] 
 **includeOld** | **NSNumber***| Include trades older than 7 days, default - &#x60;false&#x60; | [optional] 
 **sorting** | **NSString***| Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) | [optional] 

### Return type

**NSObject***

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateGetUserTradesByInstrumentGet**
```objc
-(NSURLSessionTask*) privateGetUserTradesByInstrumentGetWithInstrumentName: (NSString*) instrumentName
    startSeq: (NSNumber*) startSeq
    endSeq: (NSNumber*) endSeq
    count: (NSNumber*) count
    includeOld: (NSNumber*) includeOld
    sorting: (NSString*) sorting
        completionHandler: (void (^)(NSObject* output, NSError* error)) handler;
```

Retrieve the latest user trades that have occurred for a specific instrument.

### Example 
```objc
OAIDefaultConfiguration *apiConfig = [OAIDefaultConfiguration sharedConfig];
// Configure HTTP basic authorization (authentication scheme: bearerAuth)
[apiConfig setUsername:@"YOUR_USERNAME"];
[apiConfig setPassword:@"YOUR_PASSWORD"];


NSString* instrumentName = BTC-PERPETUAL; // Instrument name
NSNumber* startSeq = @56; // The sequence number of the first trade to be returned (optional)
NSNumber* endSeq = @56; // The sequence number of the last trade to be returned (optional)
NSNumber* count = @56; // Number of requested items, default - `10` (optional)
NSNumber* includeOld = @56; // Include trades older than 7 days, default - `false` (optional)
NSString* sorting = @"sorting_example"; // Direction of results sorting (`default` value means no sorting, results will be returned in order in which they left the database) (optional)

OAIPrivateApi*apiInstance = [[OAIPrivateApi alloc] init];

// Retrieve the latest user trades that have occurred for a specific instrument.
[apiInstance privateGetUserTradesByInstrumentGetWithInstrumentName:instrumentName
              startSeq:startSeq
              endSeq:endSeq
              count:count
              includeOld:includeOld
              sorting:sorting
          completionHandler: ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAIPrivateApi->privateGetUserTradesByInstrumentGet: %@", error);
                        }
                    }];
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrumentName** | **NSString***| Instrument name | 
 **startSeq** | **NSNumber***| The sequence number of the first trade to be returned | [optional] 
 **endSeq** | **NSNumber***| The sequence number of the last trade to be returned | [optional] 
 **count** | **NSNumber***| Number of requested items, default - &#x60;10&#x60; | [optional] 
 **includeOld** | **NSNumber***| Include trades older than 7 days, default - &#x60;false&#x60; | [optional] 
 **sorting** | **NSString***| Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) | [optional] 

### Return type

**NSObject***

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateGetUserTradesByOrderGet**
```objc
-(NSURLSessionTask*) privateGetUserTradesByOrderGetWithOrderId: (NSString*) orderId
    sorting: (NSString*) sorting
        completionHandler: (void (^)(NSObject* output, NSError* error)) handler;
```

Retrieve the list of user trades that was created for given order

### Example 
```objc
OAIDefaultConfiguration *apiConfig = [OAIDefaultConfiguration sharedConfig];
// Configure HTTP basic authorization (authentication scheme: bearerAuth)
[apiConfig setUsername:@"YOUR_USERNAME"];
[apiConfig setPassword:@"YOUR_PASSWORD"];


NSString* orderId = ETH-100234; // The order id
NSString* sorting = @"sorting_example"; // Direction of results sorting (`default` value means no sorting, results will be returned in order in which they left the database) (optional)

OAIPrivateApi*apiInstance = [[OAIPrivateApi alloc] init];

// Retrieve the list of user trades that was created for given order
[apiInstance privateGetUserTradesByOrderGetWithOrderId:orderId
              sorting:sorting
          completionHandler: ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAIPrivateApi->privateGetUserTradesByOrderGet: %@", error);
                        }
                    }];
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **orderId** | **NSString***| The order id | 
 **sorting** | **NSString***| Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) | [optional] 

### Return type

**NSObject***

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateGetWithdrawalsGet**
```objc
-(NSURLSessionTask*) privateGetWithdrawalsGetWithCurrency: (NSString*) currency
    count: (NSNumber*) count
    offset: (NSNumber*) offset
        completionHandler: (void (^)(NSObject* output, NSError* error)) handler;
```

Retrieve the latest users withdrawals

### Example 
```objc
OAIDefaultConfiguration *apiConfig = [OAIDefaultConfiguration sharedConfig];
// Configure HTTP basic authorization (authentication scheme: bearerAuth)
[apiConfig setUsername:@"YOUR_USERNAME"];
[apiConfig setPassword:@"YOUR_PASSWORD"];


NSString* currency = @"currency_example"; // The currency symbol
NSNumber* count = @56; // Number of requested items, default - `10` (optional)
NSNumber* offset = 10; // The offset for pagination, default - `0` (optional)

OAIPrivateApi*apiInstance = [[OAIPrivateApi alloc] init];

// Retrieve the latest users withdrawals
[apiInstance privateGetWithdrawalsGetWithCurrency:currency
              count:count
              offset:offset
          completionHandler: ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAIPrivateApi->privateGetWithdrawalsGet: %@", error);
                        }
                    }];
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **NSString***| The currency symbol | 
 **count** | **NSNumber***| Number of requested items, default - &#x60;10&#x60; | [optional] 
 **offset** | **NSNumber***| The offset for pagination, default - &#x60;0&#x60; | [optional] 

### Return type

**NSObject***

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateRemoveFromAddressBookGet**
```objc
-(NSURLSessionTask*) privateRemoveFromAddressBookGetWithCurrency: (NSString*) currency
    type: (NSString*) type
    address: (NSString*) address
    tfa: (NSString*) tfa
        completionHandler: (void (^)(NSObject* output, NSError* error)) handler;
```

Adds new entry to address book of given type

### Example 
```objc
OAIDefaultConfiguration *apiConfig = [OAIDefaultConfiguration sharedConfig];
// Configure HTTP basic authorization (authentication scheme: bearerAuth)
[apiConfig setUsername:@"YOUR_USERNAME"];
[apiConfig setPassword:@"YOUR_PASSWORD"];


NSString* currency = @"currency_example"; // The currency symbol
NSString* type = @"type_example"; // Address book type
NSString* address = @"address_example"; // Address in currency format, it must be in address book
NSString* tfa = @"tfa_example"; // TFA code, required when TFA is enabled for current account (optional)

OAIPrivateApi*apiInstance = [[OAIPrivateApi alloc] init];

// Adds new entry to address book of given type
[apiInstance privateRemoveFromAddressBookGetWithCurrency:currency
              type:type
              address:address
              tfa:tfa
          completionHandler: ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAIPrivateApi->privateRemoveFromAddressBookGet: %@", error);
                        }
                    }];
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **NSString***| The currency symbol | 
 **type** | **NSString***| Address book type | 
 **address** | **NSString***| Address in currency format, it must be in address book | 
 **tfa** | **NSString***| TFA code, required when TFA is enabled for current account | [optional] 

### Return type

**NSObject***

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateSellGet**
```objc
-(NSURLSessionTask*) privateSellGetWithInstrumentName: (NSString*) instrumentName
    amount: (NSNumber*) amount
    type: (NSString*) type
    label: (NSString*) label
    price: (NSNumber*) price
    timeInForce: (NSString*) timeInForce
    maxShow: (NSNumber*) maxShow
    postOnly: (NSNumber*) postOnly
    reduceOnly: (NSNumber*) reduceOnly
    stopPrice: (NSNumber*) stopPrice
    trigger: (NSString*) trigger
    advanced: (NSString*) advanced
        completionHandler: (void (^)(NSObject* output, NSError* error)) handler;
```

Places a sell order for an instrument.

### Example 
```objc
OAIDefaultConfiguration *apiConfig = [OAIDefaultConfiguration sharedConfig];
// Configure HTTP basic authorization (authentication scheme: bearerAuth)
[apiConfig setUsername:@"YOUR_USERNAME"];
[apiConfig setPassword:@"YOUR_PASSWORD"];


NSString* instrumentName = BTC-PERPETUAL; // Instrument name
NSNumber* amount = @56; // It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH
NSString* type = @"type_example"; // The order type, default: `\"limit\"` (optional)
NSString* label = @"label_example"; // user defined label for the order (maximum 32 characters) (optional)
NSNumber* price = @56; // <p>The order price in base currency (Only for limit and stop_limit orders)</p> <p>When adding order with advanced=usd, the field price should be the option price value in USD.</p> <p>When adding order with advanced=implv, the field price should be a value of implied volatility in percentages. For example,  price=100, means implied volatility of 100%</p> (optional)
NSString* timeInForce = @"good_til_cancelled"; // <p>Specifies how long the order remains in effect. Default `\"good_til_cancelled\"`</p> <ul> <li>`\"good_til_cancelled\"` - unfilled order remains in order book until cancelled</li> <li>`\"fill_or_kill\"` - execute a transaction immediately and completely or not at all</li> <li>`\"immediate_or_cancel\"` - execute a transaction immediately, and any portion of the order that cannot be immediately filled is cancelled</li> </ul> (optional) (default to @"good_til_cancelled")
NSNumber* maxShow = @1; // Maximum amount within an order to be shown to other customers, `0` for invisible order (optional) (default to @1)
NSNumber* postOnly = @(YES); // <p>If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.</p> <p>Only valid in combination with time_in_force=`\"good_til_cancelled\"`</p> (optional) (default to @(YES))
NSNumber* reduceOnly = @(NO); // If `true`, the order is considered reduce-only which is intended to only reduce a current position (optional) (default to @(NO))
NSNumber* stopPrice = @56; // Stop price, required for stop limit orders (Only for stop orders) (optional)
NSString* trigger = @"trigger_example"; // Defines trigger type, required for `\"stop_limit\"` order type (optional)
NSString* advanced = @"advanced_example"; // Advanced option order type. (Only for options) (optional)

OAIPrivateApi*apiInstance = [[OAIPrivateApi alloc] init];

// Places a sell order for an instrument.
[apiInstance privateSellGetWithInstrumentName:instrumentName
              amount:amount
              type:type
              label:label
              price:price
              timeInForce:timeInForce
              maxShow:maxShow
              postOnly:postOnly
              reduceOnly:reduceOnly
              stopPrice:stopPrice
              trigger:trigger
              advanced:advanced
          completionHandler: ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAIPrivateApi->privateSellGet: %@", error);
                        }
                    }];
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrumentName** | **NSString***| Instrument name | 
 **amount** | **NSNumber***| It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH | 
 **type** | **NSString***| The order type, default: &#x60;\&quot;limit\&quot;&#x60; | [optional] 
 **label** | **NSString***| user defined label for the order (maximum 32 characters) | [optional] 
 **price** | **NSNumber***| &lt;p&gt;The order price in base currency (Only for limit and stop_limit orders)&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;usd, the field price should be the option price value in USD.&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;implv, the field price should be a value of implied volatility in percentages. For example,  price&#x3D;100, means implied volatility of 100%&lt;/p&gt; | [optional] 
 **timeInForce** | **NSString***| &lt;p&gt;Specifies how long the order remains in effect. Default &#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; &lt;ul&gt; &lt;li&gt;&#x60;\&quot;good_til_cancelled\&quot;&#x60; - unfilled order remains in order book until cancelled&lt;/li&gt; &lt;li&gt;&#x60;\&quot;fill_or_kill\&quot;&#x60; - execute a transaction immediately and completely or not at all&lt;/li&gt; &lt;li&gt;&#x60;\&quot;immediate_or_cancel\&quot;&#x60; - execute a transaction immediately, and any portion of the order that cannot be immediately filled is cancelled&lt;/li&gt; &lt;/ul&gt; | [optional] [default to @&quot;good_til_cancelled&quot;]
 **maxShow** | **NSNumber***| Maximum amount within an order to be shown to other customers, &#x60;0&#x60; for invisible order | [optional] [default to @1]
 **postOnly** | **NSNumber***| &lt;p&gt;If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.&lt;/p&gt; &lt;p&gt;Only valid in combination with time_in_force&#x3D;&#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; | [optional] [default to @(YES)]
 **reduceOnly** | **NSNumber***| If &#x60;true&#x60;, the order is considered reduce-only which is intended to only reduce a current position | [optional] [default to @(NO)]
 **stopPrice** | **NSNumber***| Stop price, required for stop limit orders (Only for stop orders) | [optional] 
 **trigger** | **NSString***| Defines trigger type, required for &#x60;\&quot;stop_limit\&quot;&#x60; order type | [optional] 
 **advanced** | **NSString***| Advanced option order type. (Only for options) | [optional] 

### Return type

**NSObject***

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateSetAnnouncementAsReadGet**
```objc
-(NSURLSessionTask*) privateSetAnnouncementAsReadGetWithAnnouncementId: (NSNumber*) announcementId
        completionHandler: (void (^)(NSObject* output, NSError* error)) handler;
```

Marks an announcement as read, so it will not be shown in `get_new_announcements`.

### Example 
```objc
OAIDefaultConfiguration *apiConfig = [OAIDefaultConfiguration sharedConfig];
// Configure HTTP basic authorization (authentication scheme: bearerAuth)
[apiConfig setUsername:@"YOUR_USERNAME"];
[apiConfig setPassword:@"YOUR_PASSWORD"];


NSNumber* announcementId = @56; // the ID of the announcement

OAIPrivateApi*apiInstance = [[OAIPrivateApi alloc] init];

// Marks an announcement as read, so it will not be shown in `get_new_announcements`.
[apiInstance privateSetAnnouncementAsReadGetWithAnnouncementId:announcementId
          completionHandler: ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAIPrivateApi->privateSetAnnouncementAsReadGet: %@", error);
                        }
                    }];
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **announcementId** | **NSNumber***| the ID of the announcement | 

### Return type

**NSObject***

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateSetEmailForSubaccountGet**
```objc
-(NSURLSessionTask*) privateSetEmailForSubaccountGetWithSid: (NSNumber*) sid
    email: (NSString*) email
        completionHandler: (void (^)(NSObject* output, NSError* error)) handler;
```

Assign an email address to a subaccount. User will receive an email with confirmation link.

### Example 
```objc
OAIDefaultConfiguration *apiConfig = [OAIDefaultConfiguration sharedConfig];
// Configure HTTP basic authorization (authentication scheme: bearerAuth)
[apiConfig setUsername:@"YOUR_USERNAME"];
[apiConfig setPassword:@"YOUR_PASSWORD"];


NSNumber* sid = @56; // The user id for the subaccount
NSString* email = subaccount_1@email.com; // The email address for the subaccount

OAIPrivateApi*apiInstance = [[OAIPrivateApi alloc] init];

// Assign an email address to a subaccount. User will receive an email with confirmation link.
[apiInstance privateSetEmailForSubaccountGetWithSid:sid
              email:email
          completionHandler: ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAIPrivateApi->privateSetEmailForSubaccountGet: %@", error);
                        }
                    }];
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **sid** | **NSNumber***| The user id for the subaccount | 
 **email** | **NSString***| The email address for the subaccount | 

### Return type

**NSObject***

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateSetEmailLanguageGet**
```objc
-(NSURLSessionTask*) privateSetEmailLanguageGetWithLanguage: (NSString*) language
        completionHandler: (void (^)(NSObject* output, NSError* error)) handler;
```

Changes the language to be used for emails.

### Example 
```objc
OAIDefaultConfiguration *apiConfig = [OAIDefaultConfiguration sharedConfig];
// Configure HTTP basic authorization (authentication scheme: bearerAuth)
[apiConfig setUsername:@"YOUR_USERNAME"];
[apiConfig setPassword:@"YOUR_PASSWORD"];


NSString* language = en; // The abbreviated language name. Valid values include `\"en\"`, `\"ko\"`, `\"zh\"`

OAIPrivateApi*apiInstance = [[OAIPrivateApi alloc] init];

// Changes the language to be used for emails.
[apiInstance privateSetEmailLanguageGetWithLanguage:language
          completionHandler: ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAIPrivateApi->privateSetEmailLanguageGet: %@", error);
                        }
                    }];
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **language** | **NSString***| The abbreviated language name. Valid values include &#x60;\&quot;en\&quot;&#x60;, &#x60;\&quot;ko\&quot;&#x60;, &#x60;\&quot;zh\&quot;&#x60; | 

### Return type

**NSObject***

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateSetPasswordForSubaccountGet**
```objc
-(NSURLSessionTask*) privateSetPasswordForSubaccountGetWithSid: (NSNumber*) sid
    password: (NSString*) password
        completionHandler: (void (^)(NSObject* output, NSError* error)) handler;
```

Set the password for the subaccount

### Example 
```objc
OAIDefaultConfiguration *apiConfig = [OAIDefaultConfiguration sharedConfig];
// Configure HTTP basic authorization (authentication scheme: bearerAuth)
[apiConfig setUsername:@"YOUR_USERNAME"];
[apiConfig setPassword:@"YOUR_PASSWORD"];


NSNumber* sid = @56; // The user id for the subaccount
NSString* password = @"password_example"; // The password for the subaccount

OAIPrivateApi*apiInstance = [[OAIPrivateApi alloc] init];

// Set the password for the subaccount
[apiInstance privateSetPasswordForSubaccountGetWithSid:sid
              password:password
          completionHandler: ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAIPrivateApi->privateSetPasswordForSubaccountGet: %@", error);
                        }
                    }];
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **sid** | **NSNumber***| The user id for the subaccount | 
 **password** | **NSString***| The password for the subaccount | 

### Return type

**NSObject***

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateSubmitTransferToSubaccountGet**
```objc
-(NSURLSessionTask*) privateSubmitTransferToSubaccountGetWithCurrency: (NSString*) currency
    amount: (NSNumber*) amount
    destination: (NSNumber*) destination
        completionHandler: (void (^)(NSObject* output, NSError* error)) handler;
```

Transfer funds to a subaccount.

### Example 
```objc
OAIDefaultConfiguration *apiConfig = [OAIDefaultConfiguration sharedConfig];
// Configure HTTP basic authorization (authentication scheme: bearerAuth)
[apiConfig setUsername:@"YOUR_USERNAME"];
[apiConfig setPassword:@"YOUR_PASSWORD"];


NSString* currency = @"currency_example"; // The currency symbol
NSNumber* amount = @56; // Amount of funds to be transferred
NSNumber* destination = 1; // Id of destination subaccount

OAIPrivateApi*apiInstance = [[OAIPrivateApi alloc] init];

// Transfer funds to a subaccount.
[apiInstance privateSubmitTransferToSubaccountGetWithCurrency:currency
              amount:amount
              destination:destination
          completionHandler: ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAIPrivateApi->privateSubmitTransferToSubaccountGet: %@", error);
                        }
                    }];
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **NSString***| The currency symbol | 
 **amount** | **NSNumber***| Amount of funds to be transferred | 
 **destination** | **NSNumber***| Id of destination subaccount | 

### Return type

**NSObject***

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateSubmitTransferToUserGet**
```objc
-(NSURLSessionTask*) privateSubmitTransferToUserGetWithCurrency: (NSString*) currency
    amount: (NSNumber*) amount
    destination: (NSString*) destination
    tfa: (NSString*) tfa
        completionHandler: (void (^)(NSObject* output, NSError* error)) handler;
```

Transfer funds to a another user.

### Example 
```objc
OAIDefaultConfiguration *apiConfig = [OAIDefaultConfiguration sharedConfig];
// Configure HTTP basic authorization (authentication scheme: bearerAuth)
[apiConfig setUsername:@"YOUR_USERNAME"];
[apiConfig setPassword:@"YOUR_PASSWORD"];


NSString* currency = @"currency_example"; // The currency symbol
NSNumber* amount = @56; // Amount of funds to be transferred
NSString* destination = @"destination_example"; // Destination address from address book
NSString* tfa = @"tfa_example"; // TFA code, required when TFA is enabled for current account (optional)

OAIPrivateApi*apiInstance = [[OAIPrivateApi alloc] init];

// Transfer funds to a another user.
[apiInstance privateSubmitTransferToUserGetWithCurrency:currency
              amount:amount
              destination:destination
              tfa:tfa
          completionHandler: ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAIPrivateApi->privateSubmitTransferToUserGet: %@", error);
                        }
                    }];
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **NSString***| The currency symbol | 
 **amount** | **NSNumber***| Amount of funds to be transferred | 
 **destination** | **NSString***| Destination address from address book | 
 **tfa** | **NSString***| TFA code, required when TFA is enabled for current account | [optional] 

### Return type

**NSObject***

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateToggleDepositAddressCreationGet**
```objc
-(NSURLSessionTask*) privateToggleDepositAddressCreationGetWithCurrency: (NSString*) currency
    state: (NSNumber*) state
        completionHandler: (void (^)(NSObject* output, NSError* error)) handler;
```

Enable or disable deposit address creation

### Example 
```objc
OAIDefaultConfiguration *apiConfig = [OAIDefaultConfiguration sharedConfig];
// Configure HTTP basic authorization (authentication scheme: bearerAuth)
[apiConfig setUsername:@"YOUR_USERNAME"];
[apiConfig setPassword:@"YOUR_PASSWORD"];


NSString* currency = @"currency_example"; // The currency symbol
NSNumber* state = @56; // 

OAIPrivateApi*apiInstance = [[OAIPrivateApi alloc] init];

// Enable or disable deposit address creation
[apiInstance privateToggleDepositAddressCreationGetWithCurrency:currency
              state:state
          completionHandler: ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAIPrivateApi->privateToggleDepositAddressCreationGet: %@", error);
                        }
                    }];
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **NSString***| The currency symbol | 
 **state** | **NSNumber***|  | 

### Return type

**NSObject***

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateToggleNotificationsFromSubaccountGet**
```objc
-(NSURLSessionTask*) privateToggleNotificationsFromSubaccountGetWithSid: (NSNumber*) sid
    state: (NSNumber*) state
        completionHandler: (void (^)(NSObject* output, NSError* error)) handler;
```

Enable or disable sending of notifications for the subaccount.

### Example 
```objc
OAIDefaultConfiguration *apiConfig = [OAIDefaultConfiguration sharedConfig];
// Configure HTTP basic authorization (authentication scheme: bearerAuth)
[apiConfig setUsername:@"YOUR_USERNAME"];
[apiConfig setPassword:@"YOUR_PASSWORD"];


NSNumber* sid = @56; // The user id for the subaccount
NSNumber* state = @56; // enable (`true`) or disable (`false`) notifications

OAIPrivateApi*apiInstance = [[OAIPrivateApi alloc] init];

// Enable or disable sending of notifications for the subaccount.
[apiInstance privateToggleNotificationsFromSubaccountGetWithSid:sid
              state:state
          completionHandler: ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAIPrivateApi->privateToggleNotificationsFromSubaccountGet: %@", error);
                        }
                    }];
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **sid** | **NSNumber***| The user id for the subaccount | 
 **state** | **NSNumber***| enable (&#x60;true&#x60;) or disable (&#x60;false&#x60;) notifications | 

### Return type

**NSObject***

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateToggleSubaccountLoginGet**
```objc
-(NSURLSessionTask*) privateToggleSubaccountLoginGetWithSid: (NSNumber*) sid
    state: (NSString*) state
        completionHandler: (void (^)(NSObject* output, NSError* error)) handler;
```

Enable or disable login for a subaccount. If login is disabled and a session for the subaccount exists, this session will be terminated.

### Example 
```objc
OAIDefaultConfiguration *apiConfig = [OAIDefaultConfiguration sharedConfig];
// Configure HTTP basic authorization (authentication scheme: bearerAuth)
[apiConfig setUsername:@"YOUR_USERNAME"];
[apiConfig setPassword:@"YOUR_PASSWORD"];


NSNumber* sid = @56; // The user id for the subaccount
NSString* state = @"state_example"; // enable or disable login.

OAIPrivateApi*apiInstance = [[OAIPrivateApi alloc] init];

// Enable or disable login for a subaccount. If login is disabled and a session for the subaccount exists, this session will be terminated.
[apiInstance privateToggleSubaccountLoginGetWithSid:sid
              state:state
          completionHandler: ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAIPrivateApi->privateToggleSubaccountLoginGet: %@", error);
                        }
                    }];
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **sid** | **NSNumber***| The user id for the subaccount | 
 **state** | **NSString***| enable or disable login. | 

### Return type

**NSObject***

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **privateWithdrawGet**
```objc
-(NSURLSessionTask*) privateWithdrawGetWithCurrency: (NSString*) currency
    address: (NSString*) address
    amount: (NSNumber*) amount
    priority: (NSString*) priority
    tfa: (NSString*) tfa
        completionHandler: (void (^)(NSObject* output, NSError* error)) handler;
```

Creates a new withdrawal request

### Example 
```objc
OAIDefaultConfiguration *apiConfig = [OAIDefaultConfiguration sharedConfig];
// Configure HTTP basic authorization (authentication scheme: bearerAuth)
[apiConfig setUsername:@"YOUR_USERNAME"];
[apiConfig setPassword:@"YOUR_PASSWORD"];


NSString* currency = @"currency_example"; // The currency symbol
NSString* address = @"address_example"; // Address in currency format, it must be in address book
NSNumber* amount = @56; // Amount of funds to be withdrawn
NSString* priority = @"priority_example"; // Withdrawal priority, optional for BTC, default: `high` (optional)
NSString* tfa = @"tfa_example"; // TFA code, required when TFA is enabled for current account (optional)

OAIPrivateApi*apiInstance = [[OAIPrivateApi alloc] init];

// Creates a new withdrawal request
[apiInstance privateWithdrawGetWithCurrency:currency
              address:address
              amount:amount
              priority:priority
              tfa:tfa
          completionHandler: ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAIPrivateApi->privateWithdrawGet: %@", error);
                        }
                    }];
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **NSString***| The currency symbol | 
 **address** | **NSString***| Address in currency format, it must be in address book | 
 **amount** | **NSNumber***| Amount of funds to be withdrawn | 
 **priority** | **NSString***| Withdrawal priority, optional for BTC, default: &#x60;high&#x60; | [optional] 
 **tfa** | **NSString***| TFA code, required when TFA is enabled for current account | [optional] 

### Return type

**NSObject***

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

