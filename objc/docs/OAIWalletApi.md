# OAIWalletApi

All URIs are relative to *https://www.deribit.com/api/v2*

Method | HTTP request | Description
------------- | ------------- | -------------
[**privateAddToAddressBookGet**](OAIWalletApi.md#privateaddtoaddressbookget) | **GET** /private/add_to_address_book | Adds new entry to address book of given type
[**privateCancelTransferByIdGet**](OAIWalletApi.md#privatecanceltransferbyidget) | **GET** /private/cancel_transfer_by_id | Cancel transfer
[**privateCancelWithdrawalGet**](OAIWalletApi.md#privatecancelwithdrawalget) | **GET** /private/cancel_withdrawal | Cancels withdrawal request
[**privateCreateDepositAddressGet**](OAIWalletApi.md#privatecreatedepositaddressget) | **GET** /private/create_deposit_address | Creates deposit address in currency
[**privateGetAddressBookGet**](OAIWalletApi.md#privategetaddressbookget) | **GET** /private/get_address_book | Retrieves address book of given type
[**privateGetCurrentDepositAddressGet**](OAIWalletApi.md#privategetcurrentdepositaddressget) | **GET** /private/get_current_deposit_address | Retrieve deposit address for currency
[**privateGetDepositsGet**](OAIWalletApi.md#privategetdepositsget) | **GET** /private/get_deposits | Retrieve the latest users deposits
[**privateGetTransfersGet**](OAIWalletApi.md#privategettransfersget) | **GET** /private/get_transfers | Adds new entry to address book of given type
[**privateGetWithdrawalsGet**](OAIWalletApi.md#privategetwithdrawalsget) | **GET** /private/get_withdrawals | Retrieve the latest users withdrawals
[**privateRemoveFromAddressBookGet**](OAIWalletApi.md#privateremovefromaddressbookget) | **GET** /private/remove_from_address_book | Adds new entry to address book of given type
[**privateSubmitTransferToSubaccountGet**](OAIWalletApi.md#privatesubmittransfertosubaccountget) | **GET** /private/submit_transfer_to_subaccount | Transfer funds to a subaccount.
[**privateSubmitTransferToUserGet**](OAIWalletApi.md#privatesubmittransfertouserget) | **GET** /private/submit_transfer_to_user | Transfer funds to a another user.
[**privateToggleDepositAddressCreationGet**](OAIWalletApi.md#privatetoggledepositaddresscreationget) | **GET** /private/toggle_deposit_address_creation | Enable or disable deposit address creation
[**privateWithdrawGet**](OAIWalletApi.md#privatewithdrawget) | **GET** /private/withdraw | Creates a new withdrawal request


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

OAIWalletApi*apiInstance = [[OAIWalletApi alloc] init];

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
                            NSLog(@"Error calling OAIWalletApi->privateAddToAddressBookGet: %@", error);
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

OAIWalletApi*apiInstance = [[OAIWalletApi alloc] init];

// Cancel transfer
[apiInstance privateCancelTransferByIdGetWithCurrency:currency
              _id:_id
              tfa:tfa
          completionHandler: ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAIWalletApi->privateCancelTransferByIdGet: %@", error);
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

OAIWalletApi*apiInstance = [[OAIWalletApi alloc] init];

// Cancels withdrawal request
[apiInstance privateCancelWithdrawalGetWithCurrency:currency
              _id:_id
          completionHandler: ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAIWalletApi->privateCancelWithdrawalGet: %@", error);
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

OAIWalletApi*apiInstance = [[OAIWalletApi alloc] init];

// Creates deposit address in currency
[apiInstance privateCreateDepositAddressGetWithCurrency:currency
          completionHandler: ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAIWalletApi->privateCreateDepositAddressGet: %@", error);
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

OAIWalletApi*apiInstance = [[OAIWalletApi alloc] init];

// Retrieves address book of given type
[apiInstance privateGetAddressBookGetWithCurrency:currency
              type:type
          completionHandler: ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAIWalletApi->privateGetAddressBookGet: %@", error);
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

OAIWalletApi*apiInstance = [[OAIWalletApi alloc] init];

// Retrieve deposit address for currency
[apiInstance privateGetCurrentDepositAddressGetWithCurrency:currency
          completionHandler: ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAIWalletApi->privateGetCurrentDepositAddressGet: %@", error);
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

OAIWalletApi*apiInstance = [[OAIWalletApi alloc] init];

// Retrieve the latest users deposits
[apiInstance privateGetDepositsGetWithCurrency:currency
              count:count
              offset:offset
          completionHandler: ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAIWalletApi->privateGetDepositsGet: %@", error);
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

OAIWalletApi*apiInstance = [[OAIWalletApi alloc] init];

// Adds new entry to address book of given type
[apiInstance privateGetTransfersGetWithCurrency:currency
              count:count
              offset:offset
          completionHandler: ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAIWalletApi->privateGetTransfersGet: %@", error);
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

OAIWalletApi*apiInstance = [[OAIWalletApi alloc] init];

// Retrieve the latest users withdrawals
[apiInstance privateGetWithdrawalsGetWithCurrency:currency
              count:count
              offset:offset
          completionHandler: ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAIWalletApi->privateGetWithdrawalsGet: %@", error);
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

OAIWalletApi*apiInstance = [[OAIWalletApi alloc] init];

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
                            NSLog(@"Error calling OAIWalletApi->privateRemoveFromAddressBookGet: %@", error);
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

OAIWalletApi*apiInstance = [[OAIWalletApi alloc] init];

// Transfer funds to a subaccount.
[apiInstance privateSubmitTransferToSubaccountGetWithCurrency:currency
              amount:amount
              destination:destination
          completionHandler: ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAIWalletApi->privateSubmitTransferToSubaccountGet: %@", error);
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

OAIWalletApi*apiInstance = [[OAIWalletApi alloc] init];

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
                            NSLog(@"Error calling OAIWalletApi->privateSubmitTransferToUserGet: %@", error);
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

OAIWalletApi*apiInstance = [[OAIWalletApi alloc] init];

// Enable or disable deposit address creation
[apiInstance privateToggleDepositAddressCreationGetWithCurrency:currency
              state:state
          completionHandler: ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAIWalletApi->privateToggleDepositAddressCreationGet: %@", error);
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

OAIWalletApi*apiInstance = [[OAIWalletApi alloc] init];

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
                            NSLog(@"Error calling OAIWalletApi->privateWithdrawGet: %@", error);
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

