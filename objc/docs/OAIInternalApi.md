# OAIInternalApi

All URIs are relative to *https://www.deribit.com/api/v2*

Method | HTTP request | Description
------------- | ------------- | -------------
[**privateAddToAddressBookGet**](OAIInternalApi.md#privateaddtoaddressbookget) | **GET** /private/add_to_address_book | Adds new entry to address book of given type
[**privateDisableTfaWithRecoveryCodeGet**](OAIInternalApi.md#privatedisabletfawithrecoverycodeget) | **GET** /private/disable_tfa_with_recovery_code | Disables TFA with one time recovery code
[**privateGetAddressBookGet**](OAIInternalApi.md#privategetaddressbookget) | **GET** /private/get_address_book | Retrieves address book of given type
[**privateRemoveFromAddressBookGet**](OAIInternalApi.md#privateremovefromaddressbookget) | **GET** /private/remove_from_address_book | Adds new entry to address book of given type
[**privateSubmitTransferToSubaccountGet**](OAIInternalApi.md#privatesubmittransfertosubaccountget) | **GET** /private/submit_transfer_to_subaccount | Transfer funds to a subaccount.
[**privateSubmitTransferToUserGet**](OAIInternalApi.md#privatesubmittransfertouserget) | **GET** /private/submit_transfer_to_user | Transfer funds to a another user.
[**privateToggleDepositAddressCreationGet**](OAIInternalApi.md#privatetoggledepositaddresscreationget) | **GET** /private/toggle_deposit_address_creation | Enable or disable deposit address creation
[**publicGetFooterGet**](OAIInternalApi.md#publicgetfooterget) | **GET** /public/get_footer | Get information to be displayed in the footer of the website.
[**publicGetOptionMarkPricesGet**](OAIInternalApi.md#publicgetoptionmarkpricesget) | **GET** /public/get_option_mark_prices | Retrives market prices and its implied volatility of options instruments
[**publicValidateFieldGet**](OAIInternalApi.md#publicvalidatefieldget) | **GET** /public/validate_field | Method used to introduce the client software connected to Deribit platform over websocket. Provided data may have an impact on the maintained connection and will be collected for internal statistical purposes. In response, Deribit will also introduce itself.


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

OAIInternalApi*apiInstance = [[OAIInternalApi alloc] init];

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
                            NSLog(@"Error calling OAIInternalApi->privateAddToAddressBookGet: %@", error);
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

OAIInternalApi*apiInstance = [[OAIInternalApi alloc] init];

// Disables TFA with one time recovery code
[apiInstance privateDisableTfaWithRecoveryCodeGetWithPassword:password
              code:code
          completionHandler: ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAIInternalApi->privateDisableTfaWithRecoveryCodeGet: %@", error);
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

OAIInternalApi*apiInstance = [[OAIInternalApi alloc] init];

// Retrieves address book of given type
[apiInstance privateGetAddressBookGetWithCurrency:currency
              type:type
          completionHandler: ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAIInternalApi->privateGetAddressBookGet: %@", error);
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

OAIInternalApi*apiInstance = [[OAIInternalApi alloc] init];

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
                            NSLog(@"Error calling OAIInternalApi->privateRemoveFromAddressBookGet: %@", error);
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

OAIInternalApi*apiInstance = [[OAIInternalApi alloc] init];

// Transfer funds to a subaccount.
[apiInstance privateSubmitTransferToSubaccountGetWithCurrency:currency
              amount:amount
              destination:destination
          completionHandler: ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAIInternalApi->privateSubmitTransferToSubaccountGet: %@", error);
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

OAIInternalApi*apiInstance = [[OAIInternalApi alloc] init];

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
                            NSLog(@"Error calling OAIInternalApi->privateSubmitTransferToUserGet: %@", error);
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

OAIInternalApi*apiInstance = [[OAIInternalApi alloc] init];

// Enable or disable deposit address creation
[apiInstance privateToggleDepositAddressCreationGetWithCurrency:currency
              state:state
          completionHandler: ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAIInternalApi->privateToggleDepositAddressCreationGet: %@", error);
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

# **publicGetFooterGet**
```objc
-(NSURLSessionTask*) publicGetFooterGetWithCompletionHandler: 
        (void (^)(NSObject* output, NSError* error)) handler;
```

Get information to be displayed in the footer of the website.

### Example 
```objc
OAIDefaultConfiguration *apiConfig = [OAIDefaultConfiguration sharedConfig];
// Configure HTTP basic authorization (authentication scheme: bearerAuth)
[apiConfig setUsername:@"YOUR_USERNAME"];
[apiConfig setPassword:@"YOUR_PASSWORD"];



OAIInternalApi*apiInstance = [[OAIInternalApi alloc] init];

// Get information to be displayed in the footer of the website.
[apiInstance publicGetFooterGetWithCompletionHandler: 
          ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAIInternalApi->publicGetFooterGet: %@", error);
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

# **publicGetOptionMarkPricesGet**
```objc
-(NSURLSessionTask*) publicGetOptionMarkPricesGetWithCurrency: (NSString*) currency
        completionHandler: (void (^)(NSObject* output, NSError* error)) handler;
```

Retrives market prices and its implied volatility of options instruments

### Example 
```objc
OAIDefaultConfiguration *apiConfig = [OAIDefaultConfiguration sharedConfig];
// Configure HTTP basic authorization (authentication scheme: bearerAuth)
[apiConfig setUsername:@"YOUR_USERNAME"];
[apiConfig setPassword:@"YOUR_PASSWORD"];


NSString* currency = @"currency_example"; // The currency symbol

OAIInternalApi*apiInstance = [[OAIInternalApi alloc] init];

// Retrives market prices and its implied volatility of options instruments
[apiInstance publicGetOptionMarkPricesGetWithCurrency:currency
          completionHandler: ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAIInternalApi->publicGetOptionMarkPricesGet: %@", error);
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

# **publicValidateFieldGet**
```objc
-(NSURLSessionTask*) publicValidateFieldGetWithField: (NSString*) field
    value: (NSString*) value
    value2: (NSString*) value2
        completionHandler: (void (^)(NSObject* output, NSError* error)) handler;
```

Method used to introduce the client software connected to Deribit platform over websocket. Provided data may have an impact on the maintained connection and will be collected for internal statistical purposes. In response, Deribit will also introduce itself.

### Example 
```objc
OAIDefaultConfiguration *apiConfig = [OAIDefaultConfiguration sharedConfig];
// Configure HTTP basic authorization (authentication scheme: bearerAuth)
[apiConfig setUsername:@"YOUR_USERNAME"];
[apiConfig setPassword:@"YOUR_PASSWORD"];


NSString* field = @"field_example"; // Name of the field to be validated, examples: postal_code, date_of_birth
NSString* value = @"value_example"; // Value to be checked
NSString* value2 = @"value2_example"; // Additional value to be compared with (optional)

OAIInternalApi*apiInstance = [[OAIInternalApi alloc] init];

// Method used to introduce the client software connected to Deribit platform over websocket. Provided data may have an impact on the maintained connection and will be collected for internal statistical purposes. In response, Deribit will also introduce itself.
[apiInstance publicValidateFieldGetWithField:field
              value:value
              value2:value2
          completionHandler: ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAIInternalApi->publicValidateFieldGet: %@", error);
                        }
                    }];
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **field** | **NSString***| Name of the field to be validated, examples: postal_code, date_of_birth | 
 **value** | **NSString***| Value to be checked | 
 **value2** | **NSString***| Additional value to be compared with | [optional] 

### Return type

**NSObject***

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

