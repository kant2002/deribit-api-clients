# OAIAccountManagementApi

All URIs are relative to *https://www.deribit.com/api/v2*

Method | HTTP request | Description
------------- | ------------- | -------------
[**privateChangeSubaccountNameGet**](OAIAccountManagementApi.md#privatechangesubaccountnameget) | **GET** /private/change_subaccount_name | Change the user name for a subaccount
[**privateCreateSubaccountGet**](OAIAccountManagementApi.md#privatecreatesubaccountget) | **GET** /private/create_subaccount | Create a new subaccount
[**privateDisableTfaForSubaccountGet**](OAIAccountManagementApi.md#privatedisabletfaforsubaccountget) | **GET** /private/disable_tfa_for_subaccount | Disable two factor authentication for a subaccount.
[**privateGetAccountSummaryGet**](OAIAccountManagementApi.md#privategetaccountsummaryget) | **GET** /private/get_account_summary | Retrieves user account summary.
[**privateGetEmailLanguageGet**](OAIAccountManagementApi.md#privategetemaillanguageget) | **GET** /private/get_email_language | Retrieves the language to be used for emails.
[**privateGetNewAnnouncementsGet**](OAIAccountManagementApi.md#privategetnewannouncementsget) | **GET** /private/get_new_announcements | Retrieves announcements that have not been marked read by the user.
[**privateGetPositionGet**](OAIAccountManagementApi.md#privategetpositionget) | **GET** /private/get_position | Retrieve user position.
[**privateGetPositionsGet**](OAIAccountManagementApi.md#privategetpositionsget) | **GET** /private/get_positions | Retrieve user positions.
[**privateGetSubaccountsGet**](OAIAccountManagementApi.md#privategetsubaccountsget) | **GET** /private/get_subaccounts | Get information about subaccounts
[**privateSetAnnouncementAsReadGet**](OAIAccountManagementApi.md#privatesetannouncementasreadget) | **GET** /private/set_announcement_as_read | Marks an announcement as read, so it will not be shown in &#x60;get_new_announcements&#x60;.
[**privateSetEmailForSubaccountGet**](OAIAccountManagementApi.md#privatesetemailforsubaccountget) | **GET** /private/set_email_for_subaccount | Assign an email address to a subaccount. User will receive an email with confirmation link.
[**privateSetEmailLanguageGet**](OAIAccountManagementApi.md#privatesetemaillanguageget) | **GET** /private/set_email_language | Changes the language to be used for emails.
[**privateSetPasswordForSubaccountGet**](OAIAccountManagementApi.md#privatesetpasswordforsubaccountget) | **GET** /private/set_password_for_subaccount | Set the password for the subaccount
[**privateToggleNotificationsFromSubaccountGet**](OAIAccountManagementApi.md#privatetogglenotificationsfromsubaccountget) | **GET** /private/toggle_notifications_from_subaccount | Enable or disable sending of notifications for the subaccount.
[**privateToggleSubaccountLoginGet**](OAIAccountManagementApi.md#privatetogglesubaccountloginget) | **GET** /private/toggle_subaccount_login | Enable or disable login for a subaccount. If login is disabled and a session for the subaccount exists, this session will be terminated.
[**publicGetAnnouncementsGet**](OAIAccountManagementApi.md#publicgetannouncementsget) | **GET** /public/get_announcements | Retrieves announcements from the last 30 days.


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

OAIAccountManagementApi*apiInstance = [[OAIAccountManagementApi alloc] init];

// Change the user name for a subaccount
[apiInstance privateChangeSubaccountNameGetWithSid:sid
              name:name
          completionHandler: ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAIAccountManagementApi->privateChangeSubaccountNameGet: %@", error);
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



OAIAccountManagementApi*apiInstance = [[OAIAccountManagementApi alloc] init];

// Create a new subaccount
[apiInstance privateCreateSubaccountGetWithCompletionHandler: 
          ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAIAccountManagementApi->privateCreateSubaccountGet: %@", error);
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

OAIAccountManagementApi*apiInstance = [[OAIAccountManagementApi alloc] init];

// Disable two factor authentication for a subaccount.
[apiInstance privateDisableTfaForSubaccountGetWithSid:sid
          completionHandler: ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAIAccountManagementApi->privateDisableTfaForSubaccountGet: %@", error);
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

OAIAccountManagementApi*apiInstance = [[OAIAccountManagementApi alloc] init];

// Retrieves user account summary.
[apiInstance privateGetAccountSummaryGetWithCurrency:currency
              extended:extended
          completionHandler: ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAIAccountManagementApi->privateGetAccountSummaryGet: %@", error);
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



OAIAccountManagementApi*apiInstance = [[OAIAccountManagementApi alloc] init];

// Retrieves the language to be used for emails.
[apiInstance privateGetEmailLanguageGetWithCompletionHandler: 
          ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAIAccountManagementApi->privateGetEmailLanguageGet: %@", error);
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



OAIAccountManagementApi*apiInstance = [[OAIAccountManagementApi alloc] init];

// Retrieves announcements that have not been marked read by the user.
[apiInstance privateGetNewAnnouncementsGetWithCompletionHandler: 
          ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAIAccountManagementApi->privateGetNewAnnouncementsGet: %@", error);
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

OAIAccountManagementApi*apiInstance = [[OAIAccountManagementApi alloc] init];

// Retrieve user position.
[apiInstance privateGetPositionGetWithInstrumentName:instrumentName
          completionHandler: ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAIAccountManagementApi->privateGetPositionGet: %@", error);
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

OAIAccountManagementApi*apiInstance = [[OAIAccountManagementApi alloc] init];

// Retrieve user positions.
[apiInstance privateGetPositionsGetWithCurrency:currency
              kind:kind
          completionHandler: ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAIAccountManagementApi->privateGetPositionsGet: %@", error);
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

OAIAccountManagementApi*apiInstance = [[OAIAccountManagementApi alloc] init];

// Get information about subaccounts
[apiInstance privateGetSubaccountsGetWithWithPortfolio:withPortfolio
          completionHandler: ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAIAccountManagementApi->privateGetSubaccountsGet: %@", error);
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

OAIAccountManagementApi*apiInstance = [[OAIAccountManagementApi alloc] init];

// Marks an announcement as read, so it will not be shown in `get_new_announcements`.
[apiInstance privateSetAnnouncementAsReadGetWithAnnouncementId:announcementId
          completionHandler: ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAIAccountManagementApi->privateSetAnnouncementAsReadGet: %@", error);
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

OAIAccountManagementApi*apiInstance = [[OAIAccountManagementApi alloc] init];

// Assign an email address to a subaccount. User will receive an email with confirmation link.
[apiInstance privateSetEmailForSubaccountGetWithSid:sid
              email:email
          completionHandler: ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAIAccountManagementApi->privateSetEmailForSubaccountGet: %@", error);
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

OAIAccountManagementApi*apiInstance = [[OAIAccountManagementApi alloc] init];

// Changes the language to be used for emails.
[apiInstance privateSetEmailLanguageGetWithLanguage:language
          completionHandler: ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAIAccountManagementApi->privateSetEmailLanguageGet: %@", error);
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

OAIAccountManagementApi*apiInstance = [[OAIAccountManagementApi alloc] init];

// Set the password for the subaccount
[apiInstance privateSetPasswordForSubaccountGetWithSid:sid
              password:password
          completionHandler: ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAIAccountManagementApi->privateSetPasswordForSubaccountGet: %@", error);
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

OAIAccountManagementApi*apiInstance = [[OAIAccountManagementApi alloc] init];

// Enable or disable sending of notifications for the subaccount.
[apiInstance privateToggleNotificationsFromSubaccountGetWithSid:sid
              state:state
          completionHandler: ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAIAccountManagementApi->privateToggleNotificationsFromSubaccountGet: %@", error);
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

OAIAccountManagementApi*apiInstance = [[OAIAccountManagementApi alloc] init];

// Enable or disable login for a subaccount. If login is disabled and a session for the subaccount exists, this session will be terminated.
[apiInstance privateToggleSubaccountLoginGetWithSid:sid
              state:state
          completionHandler: ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAIAccountManagementApi->privateToggleSubaccountLoginGet: %@", error);
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

# **publicGetAnnouncementsGet**
```objc
-(NSURLSessionTask*) publicGetAnnouncementsGetWithCompletionHandler: 
        (void (^)(NSObject* output, NSError* error)) handler;
```

Retrieves announcements from the last 30 days.

### Example 
```objc
OAIDefaultConfiguration *apiConfig = [OAIDefaultConfiguration sharedConfig];
// Configure HTTP basic authorization (authentication scheme: bearerAuth)
[apiConfig setUsername:@"YOUR_USERNAME"];
[apiConfig setPassword:@"YOUR_PASSWORD"];



OAIAccountManagementApi*apiInstance = [[OAIAccountManagementApi alloc] init];

// Retrieves announcements from the last 30 days.
[apiInstance publicGetAnnouncementsGetWithCompletionHandler: 
          ^(NSObject* output, NSError* error) {
                        if (output) {
                            NSLog(@"%@", output);
                        }
                        if (error) {
                            NSLog(@"Error calling OAIAccountManagementApi->publicGetAnnouncementsGet: %@", error);
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

