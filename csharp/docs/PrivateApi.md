# Org.OpenAPITools.Api.PrivateApi

All URIs are relative to *https://www.deribit.com/api/v2*

Method | HTTP request | Description
------------- | ------------- | -------------
[**PrivateAddToAddressBookGet**](PrivateApi.md#privateaddtoaddressbookget) | **GET** /private/add_to_address_book | Adds new entry to address book of given type
[**PrivateBuyGet**](PrivateApi.md#privatebuyget) | **GET** /private/buy | Places a buy order for an instrument.
[**PrivateCancelAllByCurrencyGet**](PrivateApi.md#privatecancelallbycurrencyget) | **GET** /private/cancel_all_by_currency | Cancels all orders by currency, optionally filtered by instrument kind and/or order type.
[**PrivateCancelAllByInstrumentGet**](PrivateApi.md#privatecancelallbyinstrumentget) | **GET** /private/cancel_all_by_instrument | Cancels all orders by instrument, optionally filtered by order type.
[**PrivateCancelAllGet**](PrivateApi.md#privatecancelallget) | **GET** /private/cancel_all | This method cancels all users orders and stop orders within all currencies and instrument kinds.
[**PrivateCancelGet**](PrivateApi.md#privatecancelget) | **GET** /private/cancel | Cancel an order, specified by order id
[**PrivateCancelTransferByIdGet**](PrivateApi.md#privatecanceltransferbyidget) | **GET** /private/cancel_transfer_by_id | Cancel transfer
[**PrivateCancelWithdrawalGet**](PrivateApi.md#privatecancelwithdrawalget) | **GET** /private/cancel_withdrawal | Cancels withdrawal request
[**PrivateChangeSubaccountNameGet**](PrivateApi.md#privatechangesubaccountnameget) | **GET** /private/change_subaccount_name | Change the user name for a subaccount
[**PrivateClosePositionGet**](PrivateApi.md#privateclosepositionget) | **GET** /private/close_position | Makes closing position reduce only order .
[**PrivateCreateDepositAddressGet**](PrivateApi.md#privatecreatedepositaddressget) | **GET** /private/create_deposit_address | Creates deposit address in currency
[**PrivateCreateSubaccountGet**](PrivateApi.md#privatecreatesubaccountget) | **GET** /private/create_subaccount | Create a new subaccount
[**PrivateDisableTfaForSubaccountGet**](PrivateApi.md#privatedisabletfaforsubaccountget) | **GET** /private/disable_tfa_for_subaccount | Disable two factor authentication for a subaccount.
[**PrivateDisableTfaWithRecoveryCodeGet**](PrivateApi.md#privatedisabletfawithrecoverycodeget) | **GET** /private/disable_tfa_with_recovery_code | Disables TFA with one time recovery code
[**PrivateEditGet**](PrivateApi.md#privateeditget) | **GET** /private/edit | Change price, amount and/or other properties of an order.
[**PrivateGetAccountSummaryGet**](PrivateApi.md#privategetaccountsummaryget) | **GET** /private/get_account_summary | Retrieves user account summary.
[**PrivateGetAddressBookGet**](PrivateApi.md#privategetaddressbookget) | **GET** /private/get_address_book | Retrieves address book of given type
[**PrivateGetCurrentDepositAddressGet**](PrivateApi.md#privategetcurrentdepositaddressget) | **GET** /private/get_current_deposit_address | Retrieve deposit address for currency
[**PrivateGetDepositsGet**](PrivateApi.md#privategetdepositsget) | **GET** /private/get_deposits | Retrieve the latest users deposits
[**PrivateGetEmailLanguageGet**](PrivateApi.md#privategetemaillanguageget) | **GET** /private/get_email_language | Retrieves the language to be used for emails.
[**PrivateGetMarginsGet**](PrivateApi.md#privategetmarginsget) | **GET** /private/get_margins | Get margins for given instrument, amount and price.
[**PrivateGetNewAnnouncementsGet**](PrivateApi.md#privategetnewannouncementsget) | **GET** /private/get_new_announcements | Retrieves announcements that have not been marked read by the user.
[**PrivateGetOpenOrdersByCurrencyGet**](PrivateApi.md#privategetopenordersbycurrencyget) | **GET** /private/get_open_orders_by_currency | Retrieves list of user&#39;s open orders.
[**PrivateGetOpenOrdersByInstrumentGet**](PrivateApi.md#privategetopenordersbyinstrumentget) | **GET** /private/get_open_orders_by_instrument | Retrieves list of user&#39;s open orders within given Instrument.
[**PrivateGetOrderHistoryByCurrencyGet**](PrivateApi.md#privategetorderhistorybycurrencyget) | **GET** /private/get_order_history_by_currency | Retrieves history of orders that have been partially or fully filled.
[**PrivateGetOrderHistoryByInstrumentGet**](PrivateApi.md#privategetorderhistorybyinstrumentget) | **GET** /private/get_order_history_by_instrument | Retrieves history of orders that have been partially or fully filled.
[**PrivateGetOrderMarginByIdsGet**](PrivateApi.md#privategetordermarginbyidsget) | **GET** /private/get_order_margin_by_ids | Retrieves initial margins of given orders
[**PrivateGetOrderStateGet**](PrivateApi.md#privategetorderstateget) | **GET** /private/get_order_state | Retrieve the current state of an order.
[**PrivateGetPositionGet**](PrivateApi.md#privategetpositionget) | **GET** /private/get_position | Retrieve user position.
[**PrivateGetPositionsGet**](PrivateApi.md#privategetpositionsget) | **GET** /private/get_positions | Retrieve user positions.
[**PrivateGetSettlementHistoryByCurrencyGet**](PrivateApi.md#privategetsettlementhistorybycurrencyget) | **GET** /private/get_settlement_history_by_currency | Retrieves settlement, delivery and bankruptcy events that have affected your account.
[**PrivateGetSettlementHistoryByInstrumentGet**](PrivateApi.md#privategetsettlementhistorybyinstrumentget) | **GET** /private/get_settlement_history_by_instrument | Retrieves public settlement, delivery and bankruptcy events filtered by instrument name
[**PrivateGetSubaccountsGet**](PrivateApi.md#privategetsubaccountsget) | **GET** /private/get_subaccounts | Get information about subaccounts
[**PrivateGetTransfersGet**](PrivateApi.md#privategettransfersget) | **GET** /private/get_transfers | Adds new entry to address book of given type
[**PrivateGetUserTradesByCurrencyAndTimeGet**](PrivateApi.md#privategetusertradesbycurrencyandtimeget) | **GET** /private/get_user_trades_by_currency_and_time | Retrieve the latest user trades that have occurred for instruments in a specific currency symbol and within given time range.
[**PrivateGetUserTradesByCurrencyGet**](PrivateApi.md#privategetusertradesbycurrencyget) | **GET** /private/get_user_trades_by_currency | Retrieve the latest user trades that have occurred for instruments in a specific currency symbol.
[**PrivateGetUserTradesByInstrumentAndTimeGet**](PrivateApi.md#privategetusertradesbyinstrumentandtimeget) | **GET** /private/get_user_trades_by_instrument_and_time | Retrieve the latest user trades that have occurred for a specific instrument and within given time range.
[**PrivateGetUserTradesByInstrumentGet**](PrivateApi.md#privategetusertradesbyinstrumentget) | **GET** /private/get_user_trades_by_instrument | Retrieve the latest user trades that have occurred for a specific instrument.
[**PrivateGetUserTradesByOrderGet**](PrivateApi.md#privategetusertradesbyorderget) | **GET** /private/get_user_trades_by_order | Retrieve the list of user trades that was created for given order
[**PrivateGetWithdrawalsGet**](PrivateApi.md#privategetwithdrawalsget) | **GET** /private/get_withdrawals | Retrieve the latest users withdrawals
[**PrivateRemoveFromAddressBookGet**](PrivateApi.md#privateremovefromaddressbookget) | **GET** /private/remove_from_address_book | Adds new entry to address book of given type
[**PrivateSellGet**](PrivateApi.md#privatesellget) | **GET** /private/sell | Places a sell order for an instrument.
[**PrivateSetAnnouncementAsReadGet**](PrivateApi.md#privatesetannouncementasreadget) | **GET** /private/set_announcement_as_read | Marks an announcement as read, so it will not be shown in &#x60;get_new_announcements&#x60;.
[**PrivateSetEmailForSubaccountGet**](PrivateApi.md#privatesetemailforsubaccountget) | **GET** /private/set_email_for_subaccount | Assign an email address to a subaccount. User will receive an email with confirmation link.
[**PrivateSetEmailLanguageGet**](PrivateApi.md#privatesetemaillanguageget) | **GET** /private/set_email_language | Changes the language to be used for emails.
[**PrivateSetPasswordForSubaccountGet**](PrivateApi.md#privatesetpasswordforsubaccountget) | **GET** /private/set_password_for_subaccount | Set the password for the subaccount
[**PrivateSubmitTransferToSubaccountGet**](PrivateApi.md#privatesubmittransfertosubaccountget) | **GET** /private/submit_transfer_to_subaccount | Transfer funds to a subaccount.
[**PrivateSubmitTransferToUserGet**](PrivateApi.md#privatesubmittransfertouserget) | **GET** /private/submit_transfer_to_user | Transfer funds to a another user.
[**PrivateToggleDepositAddressCreationGet**](PrivateApi.md#privatetoggledepositaddresscreationget) | **GET** /private/toggle_deposit_address_creation | Enable or disable deposit address creation
[**PrivateToggleNotificationsFromSubaccountGet**](PrivateApi.md#privatetogglenotificationsfromsubaccountget) | **GET** /private/toggle_notifications_from_subaccount | Enable or disable sending of notifications for the subaccount.
[**PrivateToggleSubaccountLoginGet**](PrivateApi.md#privatetogglesubaccountloginget) | **GET** /private/toggle_subaccount_login | Enable or disable login for a subaccount. If login is disabled and a session for the subaccount exists, this session will be terminated.
[**PrivateWithdrawGet**](PrivateApi.md#privatewithdrawget) | **GET** /private/withdraw | Creates a new withdrawal request



## PrivateAddToAddressBookGet

> Object PrivateAddToAddressBookGet (string currency, string type, string address, string name, string tfa = null)

Adds new entry to address book of given type

### Example

```csharp
using System.Diagnostics;
using Org.OpenAPITools.Api;
using Org.OpenAPITools.Client;
using Org.OpenAPITools.Model;

namespace Example
{
    public class PrivateAddToAddressBookGetExample
    {
        public static void Main()
        {
            Configuration.Default.BasePath = "https://www.deribit.com/api/v2";
            // Configure HTTP basic authorization: bearerAuth
            Configuration.Default.Username = "YOUR_USERNAME";
            Configuration.Default.Password = "YOUR_PASSWORD";

            var apiInstance = new PrivateApi(Configuration.Default);
            var currency = currency_example;  // string | The currency symbol
            var type = type_example;  // string | Address book type
            var address = address_example;  // string | Address in currency format, it must be in address book
            var name = Main address;  // string | Name of address book entry
            var tfa = tfa_example;  // string | TFA code, required when TFA is enabled for current account (optional) 

            try
            {
                // Adds new entry to address book of given type
                Object result = apiInstance.PrivateAddToAddressBookGet(currency, type, address, name, tfa);
                Debug.WriteLine(result);
            }
            catch (ApiException e)
            {
                Debug.Print("Exception when calling PrivateApi.PrivateAddToAddressBookGet: " + e.Message );
                Debug.Print("Status Code: "+ e.ErrorCode);
                Debug.Print(e.StackTrace);
            }
        }
    }
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **string**| The currency symbol | 
 **type** | **string**| Address book type | 
 **address** | **string**| Address in currency format, it must be in address book | 
 **name** | **string**| Name of address book entry | 
 **tfa** | **string**| TFA code, required when TFA is enabled for current account | [optional] 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#)
[[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateBuyGet

> Object PrivateBuyGet (string instrumentName, decimal? amount, string type = null, string label = null, decimal? price = null, string timeInForce = null, decimal? maxShow = null, bool? postOnly = null, bool? reduceOnly = null, decimal? stopPrice = null, string trigger = null, string advanced = null)

Places a buy order for an instrument.

### Example

```csharp
using System.Diagnostics;
using Org.OpenAPITools.Api;
using Org.OpenAPITools.Client;
using Org.OpenAPITools.Model;

namespace Example
{
    public class PrivateBuyGetExample
    {
        public static void Main()
        {
            Configuration.Default.BasePath = "https://www.deribit.com/api/v2";
            // Configure HTTP basic authorization: bearerAuth
            Configuration.Default.Username = "YOUR_USERNAME";
            Configuration.Default.Password = "YOUR_PASSWORD";

            var apiInstance = new PrivateApi(Configuration.Default);
            var instrumentName = BTC-PERPETUAL;  // string | Instrument name
            var amount = 8.14;  // decimal? | It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH
            var type = type_example;  // string | The order type, default: `\"limit\"` (optional) 
            var label = label_example;  // string | user defined label for the order (maximum 32 characters) (optional) 
            var price = 8.14;  // decimal? | <p>The order price in base currency (Only for limit and stop_limit orders)</p> <p>When adding order with advanced=usd, the field price should be the option price value in USD.</p> <p>When adding order with advanced=implv, the field price should be a value of implied volatility in percentages. For example,  price=100, means implied volatility of 100%</p> (optional) 
            var timeInForce = timeInForce_example;  // string | <p>Specifies how long the order remains in effect. Default `\"good_til_cancelled\"`</p> <ul> <li>`\"good_til_cancelled\"` - unfilled order remains in order book until cancelled</li> <li>`\"fill_or_kill\"` - execute a transaction immediately and completely or not at all</li> <li>`\"immediate_or_cancel\"` - execute a transaction immediately, and any portion of the order that cannot be immediately filled is cancelled</li> </ul> (optional)  (default to good_til_cancelled)
            var maxShow = 8.14;  // decimal? | Maximum amount within an order to be shown to other customers, `0` for invisible order (optional)  (default to 1M)
            var postOnly = true;  // bool? | <p>If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.</p> <p>Only valid in combination with time_in_force=`\"good_til_cancelled\"`</p> (optional)  (default to true)
            var reduceOnly = true;  // bool? | If `true`, the order is considered reduce-only which is intended to only reduce a current position (optional)  (default to false)
            var stopPrice = 8.14;  // decimal? | Stop price, required for stop limit orders (Only for stop orders) (optional) 
            var trigger = trigger_example;  // string | Defines trigger type, required for `\"stop_limit\"` order type (optional) 
            var advanced = advanced_example;  // string | Advanced option order type. (Only for options) (optional) 

            try
            {
                // Places a buy order for an instrument.
                Object result = apiInstance.PrivateBuyGet(instrumentName, amount, type, label, price, timeInForce, maxShow, postOnly, reduceOnly, stopPrice, trigger, advanced);
                Debug.WriteLine(result);
            }
            catch (ApiException e)
            {
                Debug.Print("Exception when calling PrivateApi.PrivateBuyGet: " + e.Message );
                Debug.Print("Status Code: "+ e.ErrorCode);
                Debug.Print(e.StackTrace);
            }
        }
    }
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrumentName** | **string**| Instrument name | 
 **amount** | **decimal?**| It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH | 
 **type** | **string**| The order type, default: &#x60;\&quot;limit\&quot;&#x60; | [optional] 
 **label** | **string**| user defined label for the order (maximum 32 characters) | [optional] 
 **price** | **decimal?**| &lt;p&gt;The order price in base currency (Only for limit and stop_limit orders)&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;usd, the field price should be the option price value in USD.&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;implv, the field price should be a value of implied volatility in percentages. For example,  price&#x3D;100, means implied volatility of 100%&lt;/p&gt; | [optional] 
 **timeInForce** | **string**| &lt;p&gt;Specifies how long the order remains in effect. Default &#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; &lt;ul&gt; &lt;li&gt;&#x60;\&quot;good_til_cancelled\&quot;&#x60; - unfilled order remains in order book until cancelled&lt;/li&gt; &lt;li&gt;&#x60;\&quot;fill_or_kill\&quot;&#x60; - execute a transaction immediately and completely or not at all&lt;/li&gt; &lt;li&gt;&#x60;\&quot;immediate_or_cancel\&quot;&#x60; - execute a transaction immediately, and any portion of the order that cannot be immediately filled is cancelled&lt;/li&gt; &lt;/ul&gt; | [optional] [default to good_til_cancelled]
 **maxShow** | **decimal?**| Maximum amount within an order to be shown to other customers, &#x60;0&#x60; for invisible order | [optional] [default to 1M]
 **postOnly** | **bool?**| &lt;p&gt;If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.&lt;/p&gt; &lt;p&gt;Only valid in combination with time_in_force&#x3D;&#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; | [optional] [default to true]
 **reduceOnly** | **bool?**| If &#x60;true&#x60;, the order is considered reduce-only which is intended to only reduce a current position | [optional] [default to false]
 **stopPrice** | **decimal?**| Stop price, required for stop limit orders (Only for stop orders) | [optional] 
 **trigger** | **string**| Defines trigger type, required for &#x60;\&quot;stop_limit\&quot;&#x60; order type | [optional] 
 **advanced** | **string**| Advanced option order type. (Only for options) | [optional] 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#)
[[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateCancelAllByCurrencyGet

> Object PrivateCancelAllByCurrencyGet (string currency, string kind = null, string type = null)

Cancels all orders by currency, optionally filtered by instrument kind and/or order type.

### Example

```csharp
using System.Diagnostics;
using Org.OpenAPITools.Api;
using Org.OpenAPITools.Client;
using Org.OpenAPITools.Model;

namespace Example
{
    public class PrivateCancelAllByCurrencyGetExample
    {
        public static void Main()
        {
            Configuration.Default.BasePath = "https://www.deribit.com/api/v2";
            // Configure HTTP basic authorization: bearerAuth
            Configuration.Default.Username = "YOUR_USERNAME";
            Configuration.Default.Password = "YOUR_PASSWORD";

            var apiInstance = new PrivateApi(Configuration.Default);
            var currency = currency_example;  // string | The currency symbol
            var kind = kind_example;  // string | Instrument kind, if not provided instruments of all kinds are considered (optional) 
            var type = type_example;  // string | Order type - limit, stop or all, default - `all` (optional) 

            try
            {
                // Cancels all orders by currency, optionally filtered by instrument kind and/or order type.
                Object result = apiInstance.PrivateCancelAllByCurrencyGet(currency, kind, type);
                Debug.WriteLine(result);
            }
            catch (ApiException e)
            {
                Debug.Print("Exception when calling PrivateApi.PrivateCancelAllByCurrencyGet: " + e.Message );
                Debug.Print("Status Code: "+ e.ErrorCode);
                Debug.Print(e.StackTrace);
            }
        }
    }
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **string**| The currency symbol | 
 **kind** | **string**| Instrument kind, if not provided instruments of all kinds are considered | [optional] 
 **type** | **string**| Order type - limit, stop or all, default - &#x60;all&#x60; | [optional] 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#)
[[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateCancelAllByInstrumentGet

> Object PrivateCancelAllByInstrumentGet (string instrumentName, string type = null)

Cancels all orders by instrument, optionally filtered by order type.

### Example

```csharp
using System.Diagnostics;
using Org.OpenAPITools.Api;
using Org.OpenAPITools.Client;
using Org.OpenAPITools.Model;

namespace Example
{
    public class PrivateCancelAllByInstrumentGetExample
    {
        public static void Main()
        {
            Configuration.Default.BasePath = "https://www.deribit.com/api/v2";
            // Configure HTTP basic authorization: bearerAuth
            Configuration.Default.Username = "YOUR_USERNAME";
            Configuration.Default.Password = "YOUR_PASSWORD";

            var apiInstance = new PrivateApi(Configuration.Default);
            var instrumentName = BTC-PERPETUAL;  // string | Instrument name
            var type = type_example;  // string | Order type - limit, stop or all, default - `all` (optional) 

            try
            {
                // Cancels all orders by instrument, optionally filtered by order type.
                Object result = apiInstance.PrivateCancelAllByInstrumentGet(instrumentName, type);
                Debug.WriteLine(result);
            }
            catch (ApiException e)
            {
                Debug.Print("Exception when calling PrivateApi.PrivateCancelAllByInstrumentGet: " + e.Message );
                Debug.Print("Status Code: "+ e.ErrorCode);
                Debug.Print(e.StackTrace);
            }
        }
    }
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrumentName** | **string**| Instrument name | 
 **type** | **string**| Order type - limit, stop or all, default - &#x60;all&#x60; | [optional] 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#)
[[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateCancelAllGet

> Object PrivateCancelAllGet ()

This method cancels all users orders and stop orders within all currencies and instrument kinds.

### Example

```csharp
using System.Diagnostics;
using Org.OpenAPITools.Api;
using Org.OpenAPITools.Client;
using Org.OpenAPITools.Model;

namespace Example
{
    public class PrivateCancelAllGetExample
    {
        public static void Main()
        {
            Configuration.Default.BasePath = "https://www.deribit.com/api/v2";
            // Configure HTTP basic authorization: bearerAuth
            Configuration.Default.Username = "YOUR_USERNAME";
            Configuration.Default.Password = "YOUR_PASSWORD";

            var apiInstance = new PrivateApi(Configuration.Default);

            try
            {
                // This method cancels all users orders and stop orders within all currencies and instrument kinds.
                Object result = apiInstance.PrivateCancelAllGet();
                Debug.WriteLine(result);
            }
            catch (ApiException e)
            {
                Debug.Print("Exception when calling PrivateApi.PrivateCancelAllGet: " + e.Message );
                Debug.Print("Status Code: "+ e.ErrorCode);
                Debug.Print(e.StackTrace);
            }
        }
    }
}
```

### Parameters

This endpoint does not need any parameter.

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#)
[[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateCancelGet

> Object PrivateCancelGet (string orderId)

Cancel an order, specified by order id

### Example

```csharp
using System.Diagnostics;
using Org.OpenAPITools.Api;
using Org.OpenAPITools.Client;
using Org.OpenAPITools.Model;

namespace Example
{
    public class PrivateCancelGetExample
    {
        public static void Main()
        {
            Configuration.Default.BasePath = "https://www.deribit.com/api/v2";
            // Configure HTTP basic authorization: bearerAuth
            Configuration.Default.Username = "YOUR_USERNAME";
            Configuration.Default.Password = "YOUR_PASSWORD";

            var apiInstance = new PrivateApi(Configuration.Default);
            var orderId = ETH-100234;  // string | The order id

            try
            {
                // Cancel an order, specified by order id
                Object result = apiInstance.PrivateCancelGet(orderId);
                Debug.WriteLine(result);
            }
            catch (ApiException e)
            {
                Debug.Print("Exception when calling PrivateApi.PrivateCancelGet: " + e.Message );
                Debug.Print("Status Code: "+ e.ErrorCode);
                Debug.Print(e.StackTrace);
            }
        }
    }
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **orderId** | **string**| The order id | 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#)
[[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateCancelTransferByIdGet

> Object PrivateCancelTransferByIdGet (string currency, int? id, string tfa = null)

Cancel transfer

### Example

```csharp
using System.Diagnostics;
using Org.OpenAPITools.Api;
using Org.OpenAPITools.Client;
using Org.OpenAPITools.Model;

namespace Example
{
    public class PrivateCancelTransferByIdGetExample
    {
        public static void Main()
        {
            Configuration.Default.BasePath = "https://www.deribit.com/api/v2";
            // Configure HTTP basic authorization: bearerAuth
            Configuration.Default.Username = "YOUR_USERNAME";
            Configuration.Default.Password = "YOUR_PASSWORD";

            var apiInstance = new PrivateApi(Configuration.Default);
            var currency = currency_example;  // string | The currency symbol
            var id = 12;  // int? | Id of transfer
            var tfa = tfa_example;  // string | TFA code, required when TFA is enabled for current account (optional) 

            try
            {
                // Cancel transfer
                Object result = apiInstance.PrivateCancelTransferByIdGet(currency, id, tfa);
                Debug.WriteLine(result);
            }
            catch (ApiException e)
            {
                Debug.Print("Exception when calling PrivateApi.PrivateCancelTransferByIdGet: " + e.Message );
                Debug.Print("Status Code: "+ e.ErrorCode);
                Debug.Print(e.StackTrace);
            }
        }
    }
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **string**| The currency symbol | 
 **id** | **int?**| Id of transfer | 
 **tfa** | **string**| TFA code, required when TFA is enabled for current account | [optional] 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#)
[[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateCancelWithdrawalGet

> Object PrivateCancelWithdrawalGet (string currency, decimal? id)

Cancels withdrawal request

### Example

```csharp
using System.Diagnostics;
using Org.OpenAPITools.Api;
using Org.OpenAPITools.Client;
using Org.OpenAPITools.Model;

namespace Example
{
    public class PrivateCancelWithdrawalGetExample
    {
        public static void Main()
        {
            Configuration.Default.BasePath = "https://www.deribit.com/api/v2";
            // Configure HTTP basic authorization: bearerAuth
            Configuration.Default.Username = "YOUR_USERNAME";
            Configuration.Default.Password = "YOUR_PASSWORD";

            var apiInstance = new PrivateApi(Configuration.Default);
            var currency = currency_example;  // string | The currency symbol
            var id = 1;  // decimal? | The withdrawal id

            try
            {
                // Cancels withdrawal request
                Object result = apiInstance.PrivateCancelWithdrawalGet(currency, id);
                Debug.WriteLine(result);
            }
            catch (ApiException e)
            {
                Debug.Print("Exception when calling PrivateApi.PrivateCancelWithdrawalGet: " + e.Message );
                Debug.Print("Status Code: "+ e.ErrorCode);
                Debug.Print(e.StackTrace);
            }
        }
    }
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **string**| The currency symbol | 
 **id** | **decimal?**| The withdrawal id | 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#)
[[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateChangeSubaccountNameGet

> Object PrivateChangeSubaccountNameGet (int? sid, string name)

Change the user name for a subaccount

### Example

```csharp
using System.Diagnostics;
using Org.OpenAPITools.Api;
using Org.OpenAPITools.Client;
using Org.OpenAPITools.Model;

namespace Example
{
    public class PrivateChangeSubaccountNameGetExample
    {
        public static void Main()
        {
            Configuration.Default.BasePath = "https://www.deribit.com/api/v2";
            // Configure HTTP basic authorization: bearerAuth
            Configuration.Default.Username = "YOUR_USERNAME";
            Configuration.Default.Password = "YOUR_PASSWORD";

            var apiInstance = new PrivateApi(Configuration.Default);
            var sid = 56;  // int? | The user id for the subaccount
            var name = newUserName;  // string | The new user name

            try
            {
                // Change the user name for a subaccount
                Object result = apiInstance.PrivateChangeSubaccountNameGet(sid, name);
                Debug.WriteLine(result);
            }
            catch (ApiException e)
            {
                Debug.Print("Exception when calling PrivateApi.PrivateChangeSubaccountNameGet: " + e.Message );
                Debug.Print("Status Code: "+ e.ErrorCode);
                Debug.Print(e.StackTrace);
            }
        }
    }
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **sid** | **int?**| The user id for the subaccount | 
 **name** | **string**| The new user name | 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#)
[[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateClosePositionGet

> Object PrivateClosePositionGet (string instrumentName, string type, decimal? price = null)

Makes closing position reduce only order .

### Example

```csharp
using System.Diagnostics;
using Org.OpenAPITools.Api;
using Org.OpenAPITools.Client;
using Org.OpenAPITools.Model;

namespace Example
{
    public class PrivateClosePositionGetExample
    {
        public static void Main()
        {
            Configuration.Default.BasePath = "https://www.deribit.com/api/v2";
            // Configure HTTP basic authorization: bearerAuth
            Configuration.Default.Username = "YOUR_USERNAME";
            Configuration.Default.Password = "YOUR_PASSWORD";

            var apiInstance = new PrivateApi(Configuration.Default);
            var instrumentName = BTC-PERPETUAL;  // string | Instrument name
            var type = type_example;  // string | The order type
            var price = 8.14;  // decimal? | Optional price for limit order. (optional) 

            try
            {
                // Makes closing position reduce only order .
                Object result = apiInstance.PrivateClosePositionGet(instrumentName, type, price);
                Debug.WriteLine(result);
            }
            catch (ApiException e)
            {
                Debug.Print("Exception when calling PrivateApi.PrivateClosePositionGet: " + e.Message );
                Debug.Print("Status Code: "+ e.ErrorCode);
                Debug.Print(e.StackTrace);
            }
        }
    }
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrumentName** | **string**| Instrument name | 
 **type** | **string**| The order type | 
 **price** | **decimal?**| Optional price for limit order. | [optional] 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#)
[[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateCreateDepositAddressGet

> Object PrivateCreateDepositAddressGet (string currency)

Creates deposit address in currency

### Example

```csharp
using System.Diagnostics;
using Org.OpenAPITools.Api;
using Org.OpenAPITools.Client;
using Org.OpenAPITools.Model;

namespace Example
{
    public class PrivateCreateDepositAddressGetExample
    {
        public static void Main()
        {
            Configuration.Default.BasePath = "https://www.deribit.com/api/v2";
            // Configure HTTP basic authorization: bearerAuth
            Configuration.Default.Username = "YOUR_USERNAME";
            Configuration.Default.Password = "YOUR_PASSWORD";

            var apiInstance = new PrivateApi(Configuration.Default);
            var currency = currency_example;  // string | The currency symbol

            try
            {
                // Creates deposit address in currency
                Object result = apiInstance.PrivateCreateDepositAddressGet(currency);
                Debug.WriteLine(result);
            }
            catch (ApiException e)
            {
                Debug.Print("Exception when calling PrivateApi.PrivateCreateDepositAddressGet: " + e.Message );
                Debug.Print("Status Code: "+ e.ErrorCode);
                Debug.Print(e.StackTrace);
            }
        }
    }
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **string**| The currency symbol | 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#)
[[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateCreateSubaccountGet

> Object PrivateCreateSubaccountGet ()

Create a new subaccount

### Example

```csharp
using System.Diagnostics;
using Org.OpenAPITools.Api;
using Org.OpenAPITools.Client;
using Org.OpenAPITools.Model;

namespace Example
{
    public class PrivateCreateSubaccountGetExample
    {
        public static void Main()
        {
            Configuration.Default.BasePath = "https://www.deribit.com/api/v2";
            // Configure HTTP basic authorization: bearerAuth
            Configuration.Default.Username = "YOUR_USERNAME";
            Configuration.Default.Password = "YOUR_PASSWORD";

            var apiInstance = new PrivateApi(Configuration.Default);

            try
            {
                // Create a new subaccount
                Object result = apiInstance.PrivateCreateSubaccountGet();
                Debug.WriteLine(result);
            }
            catch (ApiException e)
            {
                Debug.Print("Exception when calling PrivateApi.PrivateCreateSubaccountGet: " + e.Message );
                Debug.Print("Status Code: "+ e.ErrorCode);
                Debug.Print(e.StackTrace);
            }
        }
    }
}
```

### Parameters

This endpoint does not need any parameter.

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#)
[[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateDisableTfaForSubaccountGet

> Object PrivateDisableTfaForSubaccountGet (int? sid)

Disable two factor authentication for a subaccount.

### Example

```csharp
using System.Diagnostics;
using Org.OpenAPITools.Api;
using Org.OpenAPITools.Client;
using Org.OpenAPITools.Model;

namespace Example
{
    public class PrivateDisableTfaForSubaccountGetExample
    {
        public static void Main()
        {
            Configuration.Default.BasePath = "https://www.deribit.com/api/v2";
            // Configure HTTP basic authorization: bearerAuth
            Configuration.Default.Username = "YOUR_USERNAME";
            Configuration.Default.Password = "YOUR_PASSWORD";

            var apiInstance = new PrivateApi(Configuration.Default);
            var sid = 56;  // int? | The user id for the subaccount

            try
            {
                // Disable two factor authentication for a subaccount.
                Object result = apiInstance.PrivateDisableTfaForSubaccountGet(sid);
                Debug.WriteLine(result);
            }
            catch (ApiException e)
            {
                Debug.Print("Exception when calling PrivateApi.PrivateDisableTfaForSubaccountGet: " + e.Message );
                Debug.Print("Status Code: "+ e.ErrorCode);
                Debug.Print(e.StackTrace);
            }
        }
    }
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **sid** | **int?**| The user id for the subaccount | 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#)
[[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateDisableTfaWithRecoveryCodeGet

> Object PrivateDisableTfaWithRecoveryCodeGet (string password, string code)

Disables TFA with one time recovery code

### Example

```csharp
using System.Diagnostics;
using Org.OpenAPITools.Api;
using Org.OpenAPITools.Client;
using Org.OpenAPITools.Model;

namespace Example
{
    public class PrivateDisableTfaWithRecoveryCodeGetExample
    {
        public static void Main()
        {
            Configuration.Default.BasePath = "https://www.deribit.com/api/v2";
            // Configure HTTP basic authorization: bearerAuth
            Configuration.Default.Username = "YOUR_USERNAME";
            Configuration.Default.Password = "YOUR_PASSWORD";

            var apiInstance = new PrivateApi(Configuration.Default);
            var password = password_example;  // string | The password for the subaccount
            var code = code_example;  // string | One time recovery code

            try
            {
                // Disables TFA with one time recovery code
                Object result = apiInstance.PrivateDisableTfaWithRecoveryCodeGet(password, code);
                Debug.WriteLine(result);
            }
            catch (ApiException e)
            {
                Debug.Print("Exception when calling PrivateApi.PrivateDisableTfaWithRecoveryCodeGet: " + e.Message );
                Debug.Print("Status Code: "+ e.ErrorCode);
                Debug.Print(e.StackTrace);
            }
        }
    }
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **password** | **string**| The password for the subaccount | 
 **code** | **string**| One time recovery code | 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#)
[[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateEditGet

> Object PrivateEditGet (string orderId, decimal? amount, decimal? price, bool? postOnly = null, string advanced = null, decimal? stopPrice = null)

Change price, amount and/or other properties of an order.

### Example

```csharp
using System.Diagnostics;
using Org.OpenAPITools.Api;
using Org.OpenAPITools.Client;
using Org.OpenAPITools.Model;

namespace Example
{
    public class PrivateEditGetExample
    {
        public static void Main()
        {
            Configuration.Default.BasePath = "https://www.deribit.com/api/v2";
            // Configure HTTP basic authorization: bearerAuth
            Configuration.Default.Username = "YOUR_USERNAME";
            Configuration.Default.Password = "YOUR_PASSWORD";

            var apiInstance = new PrivateApi(Configuration.Default);
            var orderId = ETH-100234;  // string | The order id
            var amount = 8.14;  // decimal? | It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH
            var price = 8.14;  // decimal? | <p>The order price in base currency.</p> <p>When editing an option order with advanced=usd, the field price should be the option price value in USD.</p> <p>When editing an option order with advanced=implv, the field price should be a value of implied volatility in percentages. For example,  price=100, means implied volatility of 100%</p>
            var postOnly = true;  // bool? | <p>If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.</p> <p>Only valid in combination with time_in_force=`\"good_til_cancelled\"`</p> (optional)  (default to true)
            var advanced = advanced_example;  // string | Advanced option order type. If you have posted an advanced option order, it is necessary to re-supply this parameter when editing it (Only for options) (optional) 
            var stopPrice = 8.14;  // decimal? | Stop price, required for stop limit orders (Only for stop orders) (optional) 

            try
            {
                // Change price, amount and/or other properties of an order.
                Object result = apiInstance.PrivateEditGet(orderId, amount, price, postOnly, advanced, stopPrice);
                Debug.WriteLine(result);
            }
            catch (ApiException e)
            {
                Debug.Print("Exception when calling PrivateApi.PrivateEditGet: " + e.Message );
                Debug.Print("Status Code: "+ e.ErrorCode);
                Debug.Print(e.StackTrace);
            }
        }
    }
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **orderId** | **string**| The order id | 
 **amount** | **decimal?**| It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH | 
 **price** | **decimal?**| &lt;p&gt;The order price in base currency.&lt;/p&gt; &lt;p&gt;When editing an option order with advanced&#x3D;usd, the field price should be the option price value in USD.&lt;/p&gt; &lt;p&gt;When editing an option order with advanced&#x3D;implv, the field price should be a value of implied volatility in percentages. For example,  price&#x3D;100, means implied volatility of 100%&lt;/p&gt; | 
 **postOnly** | **bool?**| &lt;p&gt;If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.&lt;/p&gt; &lt;p&gt;Only valid in combination with time_in_force&#x3D;&#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; | [optional] [default to true]
 **advanced** | **string**| Advanced option order type. If you have posted an advanced option order, it is necessary to re-supply this parameter when editing it (Only for options) | [optional] 
 **stopPrice** | **decimal?**| Stop price, required for stop limit orders (Only for stop orders) | [optional] 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#)
[[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateGetAccountSummaryGet

> Object PrivateGetAccountSummaryGet (string currency, bool? extended = null)

Retrieves user account summary.

### Example

```csharp
using System.Diagnostics;
using Org.OpenAPITools.Api;
using Org.OpenAPITools.Client;
using Org.OpenAPITools.Model;

namespace Example
{
    public class PrivateGetAccountSummaryGetExample
    {
        public static void Main()
        {
            Configuration.Default.BasePath = "https://www.deribit.com/api/v2";
            // Configure HTTP basic authorization: bearerAuth
            Configuration.Default.Username = "YOUR_USERNAME";
            Configuration.Default.Password = "YOUR_PASSWORD";

            var apiInstance = new PrivateApi(Configuration.Default);
            var currency = currency_example;  // string | The currency symbol
            var extended = false;  // bool? | Include additional fields (optional) 

            try
            {
                // Retrieves user account summary.
                Object result = apiInstance.PrivateGetAccountSummaryGet(currency, extended);
                Debug.WriteLine(result);
            }
            catch (ApiException e)
            {
                Debug.Print("Exception when calling PrivateApi.PrivateGetAccountSummaryGet: " + e.Message );
                Debug.Print("Status Code: "+ e.ErrorCode);
                Debug.Print(e.StackTrace);
            }
        }
    }
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **string**| The currency symbol | 
 **extended** | **bool?**| Include additional fields | [optional] 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#)
[[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateGetAddressBookGet

> Object PrivateGetAddressBookGet (string currency, string type)

Retrieves address book of given type

### Example

```csharp
using System.Diagnostics;
using Org.OpenAPITools.Api;
using Org.OpenAPITools.Client;
using Org.OpenAPITools.Model;

namespace Example
{
    public class PrivateGetAddressBookGetExample
    {
        public static void Main()
        {
            Configuration.Default.BasePath = "https://www.deribit.com/api/v2";
            // Configure HTTP basic authorization: bearerAuth
            Configuration.Default.Username = "YOUR_USERNAME";
            Configuration.Default.Password = "YOUR_PASSWORD";

            var apiInstance = new PrivateApi(Configuration.Default);
            var currency = currency_example;  // string | The currency symbol
            var type = type_example;  // string | Address book type

            try
            {
                // Retrieves address book of given type
                Object result = apiInstance.PrivateGetAddressBookGet(currency, type);
                Debug.WriteLine(result);
            }
            catch (ApiException e)
            {
                Debug.Print("Exception when calling PrivateApi.PrivateGetAddressBookGet: " + e.Message );
                Debug.Print("Status Code: "+ e.ErrorCode);
                Debug.Print(e.StackTrace);
            }
        }
    }
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **string**| The currency symbol | 
 **type** | **string**| Address book type | 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#)
[[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateGetCurrentDepositAddressGet

> Object PrivateGetCurrentDepositAddressGet (string currency)

Retrieve deposit address for currency

### Example

```csharp
using System.Diagnostics;
using Org.OpenAPITools.Api;
using Org.OpenAPITools.Client;
using Org.OpenAPITools.Model;

namespace Example
{
    public class PrivateGetCurrentDepositAddressGetExample
    {
        public static void Main()
        {
            Configuration.Default.BasePath = "https://www.deribit.com/api/v2";
            // Configure HTTP basic authorization: bearerAuth
            Configuration.Default.Username = "YOUR_USERNAME";
            Configuration.Default.Password = "YOUR_PASSWORD";

            var apiInstance = new PrivateApi(Configuration.Default);
            var currency = currency_example;  // string | The currency symbol

            try
            {
                // Retrieve deposit address for currency
                Object result = apiInstance.PrivateGetCurrentDepositAddressGet(currency);
                Debug.WriteLine(result);
            }
            catch (ApiException e)
            {
                Debug.Print("Exception when calling PrivateApi.PrivateGetCurrentDepositAddressGet: " + e.Message );
                Debug.Print("Status Code: "+ e.ErrorCode);
                Debug.Print(e.StackTrace);
            }
        }
    }
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **string**| The currency symbol | 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#)
[[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateGetDepositsGet

> Object PrivateGetDepositsGet (string currency, int? count = null, int? offset = null)

Retrieve the latest users deposits

### Example

```csharp
using System.Diagnostics;
using Org.OpenAPITools.Api;
using Org.OpenAPITools.Client;
using Org.OpenAPITools.Model;

namespace Example
{
    public class PrivateGetDepositsGetExample
    {
        public static void Main()
        {
            Configuration.Default.BasePath = "https://www.deribit.com/api/v2";
            // Configure HTTP basic authorization: bearerAuth
            Configuration.Default.Username = "YOUR_USERNAME";
            Configuration.Default.Password = "YOUR_PASSWORD";

            var apiInstance = new PrivateApi(Configuration.Default);
            var currency = currency_example;  // string | The currency symbol
            var count = 56;  // int? | Number of requested items, default - `10` (optional) 
            var offset = 10;  // int? | The offset for pagination, default - `0` (optional) 

            try
            {
                // Retrieve the latest users deposits
                Object result = apiInstance.PrivateGetDepositsGet(currency, count, offset);
                Debug.WriteLine(result);
            }
            catch (ApiException e)
            {
                Debug.Print("Exception when calling PrivateApi.PrivateGetDepositsGet: " + e.Message );
                Debug.Print("Status Code: "+ e.ErrorCode);
                Debug.Print(e.StackTrace);
            }
        }
    }
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **string**| The currency symbol | 
 **count** | **int?**| Number of requested items, default - &#x60;10&#x60; | [optional] 
 **offset** | **int?**| The offset for pagination, default - &#x60;0&#x60; | [optional] 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#)
[[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateGetEmailLanguageGet

> Object PrivateGetEmailLanguageGet ()

Retrieves the language to be used for emails.

### Example

```csharp
using System.Diagnostics;
using Org.OpenAPITools.Api;
using Org.OpenAPITools.Client;
using Org.OpenAPITools.Model;

namespace Example
{
    public class PrivateGetEmailLanguageGetExample
    {
        public static void Main()
        {
            Configuration.Default.BasePath = "https://www.deribit.com/api/v2";
            // Configure HTTP basic authorization: bearerAuth
            Configuration.Default.Username = "YOUR_USERNAME";
            Configuration.Default.Password = "YOUR_PASSWORD";

            var apiInstance = new PrivateApi(Configuration.Default);

            try
            {
                // Retrieves the language to be used for emails.
                Object result = apiInstance.PrivateGetEmailLanguageGet();
                Debug.WriteLine(result);
            }
            catch (ApiException e)
            {
                Debug.Print("Exception when calling PrivateApi.PrivateGetEmailLanguageGet: " + e.Message );
                Debug.Print("Status Code: "+ e.ErrorCode);
                Debug.Print(e.StackTrace);
            }
        }
    }
}
```

### Parameters

This endpoint does not need any parameter.

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#)
[[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateGetMarginsGet

> Object PrivateGetMarginsGet (string instrumentName, decimal? amount, decimal? price)

Get margins for given instrument, amount and price.

### Example

```csharp
using System.Diagnostics;
using Org.OpenAPITools.Api;
using Org.OpenAPITools.Client;
using Org.OpenAPITools.Model;

namespace Example
{
    public class PrivateGetMarginsGetExample
    {
        public static void Main()
        {
            Configuration.Default.BasePath = "https://www.deribit.com/api/v2";
            // Configure HTTP basic authorization: bearerAuth
            Configuration.Default.Username = "YOUR_USERNAME";
            Configuration.Default.Password = "YOUR_PASSWORD";

            var apiInstance = new PrivateApi(Configuration.Default);
            var instrumentName = BTC-PERPETUAL;  // string | Instrument name
            var amount = 1;  // decimal? | Amount, integer for future, float for option. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH.
            var price = 8.14;  // decimal? | Price

            try
            {
                // Get margins for given instrument, amount and price.
                Object result = apiInstance.PrivateGetMarginsGet(instrumentName, amount, price);
                Debug.WriteLine(result);
            }
            catch (ApiException e)
            {
                Debug.Print("Exception when calling PrivateApi.PrivateGetMarginsGet: " + e.Message );
                Debug.Print("Status Code: "+ e.ErrorCode);
                Debug.Print(e.StackTrace);
            }
        }
    }
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrumentName** | **string**| Instrument name | 
 **amount** | **decimal?**| Amount, integer for future, float for option. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH. | 
 **price** | **decimal?**| Price | 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#)
[[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateGetNewAnnouncementsGet

> Object PrivateGetNewAnnouncementsGet ()

Retrieves announcements that have not been marked read by the user.

### Example

```csharp
using System.Diagnostics;
using Org.OpenAPITools.Api;
using Org.OpenAPITools.Client;
using Org.OpenAPITools.Model;

namespace Example
{
    public class PrivateGetNewAnnouncementsGetExample
    {
        public static void Main()
        {
            Configuration.Default.BasePath = "https://www.deribit.com/api/v2";
            // Configure HTTP basic authorization: bearerAuth
            Configuration.Default.Username = "YOUR_USERNAME";
            Configuration.Default.Password = "YOUR_PASSWORD";

            var apiInstance = new PrivateApi(Configuration.Default);

            try
            {
                // Retrieves announcements that have not been marked read by the user.
                Object result = apiInstance.PrivateGetNewAnnouncementsGet();
                Debug.WriteLine(result);
            }
            catch (ApiException e)
            {
                Debug.Print("Exception when calling PrivateApi.PrivateGetNewAnnouncementsGet: " + e.Message );
                Debug.Print("Status Code: "+ e.ErrorCode);
                Debug.Print(e.StackTrace);
            }
        }
    }
}
```

### Parameters

This endpoint does not need any parameter.

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#)
[[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateGetOpenOrdersByCurrencyGet

> Object PrivateGetOpenOrdersByCurrencyGet (string currency, string kind = null, string type = null)

Retrieves list of user's open orders.

### Example

```csharp
using System.Diagnostics;
using Org.OpenAPITools.Api;
using Org.OpenAPITools.Client;
using Org.OpenAPITools.Model;

namespace Example
{
    public class PrivateGetOpenOrdersByCurrencyGetExample
    {
        public static void Main()
        {
            Configuration.Default.BasePath = "https://www.deribit.com/api/v2";
            // Configure HTTP basic authorization: bearerAuth
            Configuration.Default.Username = "YOUR_USERNAME";
            Configuration.Default.Password = "YOUR_PASSWORD";

            var apiInstance = new PrivateApi(Configuration.Default);
            var currency = currency_example;  // string | The currency symbol
            var kind = kind_example;  // string | Instrument kind, if not provided instruments of all kinds are considered (optional) 
            var type = type_example;  // string | Order type, default - `all` (optional) 

            try
            {
                // Retrieves list of user's open orders.
                Object result = apiInstance.PrivateGetOpenOrdersByCurrencyGet(currency, kind, type);
                Debug.WriteLine(result);
            }
            catch (ApiException e)
            {
                Debug.Print("Exception when calling PrivateApi.PrivateGetOpenOrdersByCurrencyGet: " + e.Message );
                Debug.Print("Status Code: "+ e.ErrorCode);
                Debug.Print(e.StackTrace);
            }
        }
    }
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **string**| The currency symbol | 
 **kind** | **string**| Instrument kind, if not provided instruments of all kinds are considered | [optional] 
 **type** | **string**| Order type, default - &#x60;all&#x60; | [optional] 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#)
[[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateGetOpenOrdersByInstrumentGet

> Object PrivateGetOpenOrdersByInstrumentGet (string instrumentName, string type = null)

Retrieves list of user's open orders within given Instrument.

### Example

```csharp
using System.Diagnostics;
using Org.OpenAPITools.Api;
using Org.OpenAPITools.Client;
using Org.OpenAPITools.Model;

namespace Example
{
    public class PrivateGetOpenOrdersByInstrumentGetExample
    {
        public static void Main()
        {
            Configuration.Default.BasePath = "https://www.deribit.com/api/v2";
            // Configure HTTP basic authorization: bearerAuth
            Configuration.Default.Username = "YOUR_USERNAME";
            Configuration.Default.Password = "YOUR_PASSWORD";

            var apiInstance = new PrivateApi(Configuration.Default);
            var instrumentName = BTC-PERPETUAL;  // string | Instrument name
            var type = type_example;  // string | Order type, default - `all` (optional) 

            try
            {
                // Retrieves list of user's open orders within given Instrument.
                Object result = apiInstance.PrivateGetOpenOrdersByInstrumentGet(instrumentName, type);
                Debug.WriteLine(result);
            }
            catch (ApiException e)
            {
                Debug.Print("Exception when calling PrivateApi.PrivateGetOpenOrdersByInstrumentGet: " + e.Message );
                Debug.Print("Status Code: "+ e.ErrorCode);
                Debug.Print(e.StackTrace);
            }
        }
    }
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrumentName** | **string**| Instrument name | 
 **type** | **string**| Order type, default - &#x60;all&#x60; | [optional] 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#)
[[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateGetOrderHistoryByCurrencyGet

> Object PrivateGetOrderHistoryByCurrencyGet (string currency, string kind = null, int? count = null, int? offset = null, bool? includeOld = null, bool? includeUnfilled = null)

Retrieves history of orders that have been partially or fully filled.

### Example

```csharp
using System.Diagnostics;
using Org.OpenAPITools.Api;
using Org.OpenAPITools.Client;
using Org.OpenAPITools.Model;

namespace Example
{
    public class PrivateGetOrderHistoryByCurrencyGetExample
    {
        public static void Main()
        {
            Configuration.Default.BasePath = "https://www.deribit.com/api/v2";
            // Configure HTTP basic authorization: bearerAuth
            Configuration.Default.Username = "YOUR_USERNAME";
            Configuration.Default.Password = "YOUR_PASSWORD";

            var apiInstance = new PrivateApi(Configuration.Default);
            var currency = currency_example;  // string | The currency symbol
            var kind = kind_example;  // string | Instrument kind, if not provided instruments of all kinds are considered (optional) 
            var count = 56;  // int? | Number of requested items, default - `20` (optional) 
            var offset = 10;  // int? | The offset for pagination, default - `0` (optional) 
            var includeOld = false;  // bool? | Include in result orders older than 2 days, default - `false` (optional) 
            var includeUnfilled = false;  // bool? | Include in result fully unfilled closed orders, default - `false` (optional) 

            try
            {
                // Retrieves history of orders that have been partially or fully filled.
                Object result = apiInstance.PrivateGetOrderHistoryByCurrencyGet(currency, kind, count, offset, includeOld, includeUnfilled);
                Debug.WriteLine(result);
            }
            catch (ApiException e)
            {
                Debug.Print("Exception when calling PrivateApi.PrivateGetOrderHistoryByCurrencyGet: " + e.Message );
                Debug.Print("Status Code: "+ e.ErrorCode);
                Debug.Print(e.StackTrace);
            }
        }
    }
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **string**| The currency symbol | 
 **kind** | **string**| Instrument kind, if not provided instruments of all kinds are considered | [optional] 
 **count** | **int?**| Number of requested items, default - &#x60;20&#x60; | [optional] 
 **offset** | **int?**| The offset for pagination, default - &#x60;0&#x60; | [optional] 
 **includeOld** | **bool?**| Include in result orders older than 2 days, default - &#x60;false&#x60; | [optional] 
 **includeUnfilled** | **bool?**| Include in result fully unfilled closed orders, default - &#x60;false&#x60; | [optional] 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#)
[[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateGetOrderHistoryByInstrumentGet

> Object PrivateGetOrderHistoryByInstrumentGet (string instrumentName, int? count = null, int? offset = null, bool? includeOld = null, bool? includeUnfilled = null)

Retrieves history of orders that have been partially or fully filled.

### Example

```csharp
using System.Diagnostics;
using Org.OpenAPITools.Api;
using Org.OpenAPITools.Client;
using Org.OpenAPITools.Model;

namespace Example
{
    public class PrivateGetOrderHistoryByInstrumentGetExample
    {
        public static void Main()
        {
            Configuration.Default.BasePath = "https://www.deribit.com/api/v2";
            // Configure HTTP basic authorization: bearerAuth
            Configuration.Default.Username = "YOUR_USERNAME";
            Configuration.Default.Password = "YOUR_PASSWORD";

            var apiInstance = new PrivateApi(Configuration.Default);
            var instrumentName = BTC-PERPETUAL;  // string | Instrument name
            var count = 56;  // int? | Number of requested items, default - `20` (optional) 
            var offset = 10;  // int? | The offset for pagination, default - `0` (optional) 
            var includeOld = false;  // bool? | Include in result orders older than 2 days, default - `false` (optional) 
            var includeUnfilled = false;  // bool? | Include in result fully unfilled closed orders, default - `false` (optional) 

            try
            {
                // Retrieves history of orders that have been partially or fully filled.
                Object result = apiInstance.PrivateGetOrderHistoryByInstrumentGet(instrumentName, count, offset, includeOld, includeUnfilled);
                Debug.WriteLine(result);
            }
            catch (ApiException e)
            {
                Debug.Print("Exception when calling PrivateApi.PrivateGetOrderHistoryByInstrumentGet: " + e.Message );
                Debug.Print("Status Code: "+ e.ErrorCode);
                Debug.Print(e.StackTrace);
            }
        }
    }
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrumentName** | **string**| Instrument name | 
 **count** | **int?**| Number of requested items, default - &#x60;20&#x60; | [optional] 
 **offset** | **int?**| The offset for pagination, default - &#x60;0&#x60; | [optional] 
 **includeOld** | **bool?**| Include in result orders older than 2 days, default - &#x60;false&#x60; | [optional] 
 **includeUnfilled** | **bool?**| Include in result fully unfilled closed orders, default - &#x60;false&#x60; | [optional] 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#)
[[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateGetOrderMarginByIdsGet

> Object PrivateGetOrderMarginByIdsGet (List<string> ids)

Retrieves initial margins of given orders

### Example

```csharp
using System.Diagnostics;
using Org.OpenAPITools.Api;
using Org.OpenAPITools.Client;
using Org.OpenAPITools.Model;

namespace Example
{
    public class PrivateGetOrderMarginByIdsGetExample
    {
        public static void Main()
        {
            Configuration.Default.BasePath = "https://www.deribit.com/api/v2";
            // Configure HTTP basic authorization: bearerAuth
            Configuration.Default.Username = "YOUR_USERNAME";
            Configuration.Default.Password = "YOUR_PASSWORD";

            var apiInstance = new PrivateApi(Configuration.Default);
            var ids = new List<string>(); // List<string> | Ids of orders

            try
            {
                // Retrieves initial margins of given orders
                Object result = apiInstance.PrivateGetOrderMarginByIdsGet(ids);
                Debug.WriteLine(result);
            }
            catch (ApiException e)
            {
                Debug.Print("Exception when calling PrivateApi.PrivateGetOrderMarginByIdsGet: " + e.Message );
                Debug.Print("Status Code: "+ e.ErrorCode);
                Debug.Print(e.StackTrace);
            }
        }
    }
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **ids** | [**List&lt;string&gt;**](string.md)| Ids of orders | 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#)
[[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateGetOrderStateGet

> Object PrivateGetOrderStateGet (string orderId)

Retrieve the current state of an order.

### Example

```csharp
using System.Diagnostics;
using Org.OpenAPITools.Api;
using Org.OpenAPITools.Client;
using Org.OpenAPITools.Model;

namespace Example
{
    public class PrivateGetOrderStateGetExample
    {
        public static void Main()
        {
            Configuration.Default.BasePath = "https://www.deribit.com/api/v2";
            // Configure HTTP basic authorization: bearerAuth
            Configuration.Default.Username = "YOUR_USERNAME";
            Configuration.Default.Password = "YOUR_PASSWORD";

            var apiInstance = new PrivateApi(Configuration.Default);
            var orderId = ETH-100234;  // string | The order id

            try
            {
                // Retrieve the current state of an order.
                Object result = apiInstance.PrivateGetOrderStateGet(orderId);
                Debug.WriteLine(result);
            }
            catch (ApiException e)
            {
                Debug.Print("Exception when calling PrivateApi.PrivateGetOrderStateGet: " + e.Message );
                Debug.Print("Status Code: "+ e.ErrorCode);
                Debug.Print(e.StackTrace);
            }
        }
    }
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **orderId** | **string**| The order id | 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#)
[[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateGetPositionGet

> Object PrivateGetPositionGet (string instrumentName)

Retrieve user position.

### Example

```csharp
using System.Diagnostics;
using Org.OpenAPITools.Api;
using Org.OpenAPITools.Client;
using Org.OpenAPITools.Model;

namespace Example
{
    public class PrivateGetPositionGetExample
    {
        public static void Main()
        {
            Configuration.Default.BasePath = "https://www.deribit.com/api/v2";
            // Configure HTTP basic authorization: bearerAuth
            Configuration.Default.Username = "YOUR_USERNAME";
            Configuration.Default.Password = "YOUR_PASSWORD";

            var apiInstance = new PrivateApi(Configuration.Default);
            var instrumentName = BTC-PERPETUAL;  // string | Instrument name

            try
            {
                // Retrieve user position.
                Object result = apiInstance.PrivateGetPositionGet(instrumentName);
                Debug.WriteLine(result);
            }
            catch (ApiException e)
            {
                Debug.Print("Exception when calling PrivateApi.PrivateGetPositionGet: " + e.Message );
                Debug.Print("Status Code: "+ e.ErrorCode);
                Debug.Print(e.StackTrace);
            }
        }
    }
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrumentName** | **string**| Instrument name | 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#)
[[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateGetPositionsGet

> Object PrivateGetPositionsGet (string currency, string kind = null)

Retrieve user positions.

### Example

```csharp
using System.Diagnostics;
using Org.OpenAPITools.Api;
using Org.OpenAPITools.Client;
using Org.OpenAPITools.Model;

namespace Example
{
    public class PrivateGetPositionsGetExample
    {
        public static void Main()
        {
            Configuration.Default.BasePath = "https://www.deribit.com/api/v2";
            // Configure HTTP basic authorization: bearerAuth
            Configuration.Default.Username = "YOUR_USERNAME";
            Configuration.Default.Password = "YOUR_PASSWORD";

            var apiInstance = new PrivateApi(Configuration.Default);
            var currency = currency_example;  // string | 
            var kind = kind_example;  // string | Kind filter on positions (optional) 

            try
            {
                // Retrieve user positions.
                Object result = apiInstance.PrivateGetPositionsGet(currency, kind);
                Debug.WriteLine(result);
            }
            catch (ApiException e)
            {
                Debug.Print("Exception when calling PrivateApi.PrivateGetPositionsGet: " + e.Message );
                Debug.Print("Status Code: "+ e.ErrorCode);
                Debug.Print(e.StackTrace);
            }
        }
    }
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **string**|  | 
 **kind** | **string**| Kind filter on positions | [optional] 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#)
[[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateGetSettlementHistoryByCurrencyGet

> Object PrivateGetSettlementHistoryByCurrencyGet (string currency, string type = null, int? count = null)

Retrieves settlement, delivery and bankruptcy events that have affected your account.

### Example

```csharp
using System.Diagnostics;
using Org.OpenAPITools.Api;
using Org.OpenAPITools.Client;
using Org.OpenAPITools.Model;

namespace Example
{
    public class PrivateGetSettlementHistoryByCurrencyGetExample
    {
        public static void Main()
        {
            Configuration.Default.BasePath = "https://www.deribit.com/api/v2";
            // Configure HTTP basic authorization: bearerAuth
            Configuration.Default.Username = "YOUR_USERNAME";
            Configuration.Default.Password = "YOUR_PASSWORD";

            var apiInstance = new PrivateApi(Configuration.Default);
            var currency = currency_example;  // string | The currency symbol
            var type = type_example;  // string | Settlement type (optional) 
            var count = 56;  // int? | Number of requested items, default - `20` (optional) 

            try
            {
                // Retrieves settlement, delivery and bankruptcy events that have affected your account.
                Object result = apiInstance.PrivateGetSettlementHistoryByCurrencyGet(currency, type, count);
                Debug.WriteLine(result);
            }
            catch (ApiException e)
            {
                Debug.Print("Exception when calling PrivateApi.PrivateGetSettlementHistoryByCurrencyGet: " + e.Message );
                Debug.Print("Status Code: "+ e.ErrorCode);
                Debug.Print(e.StackTrace);
            }
        }
    }
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **string**| The currency symbol | 
 **type** | **string**| Settlement type | [optional] 
 **count** | **int?**| Number of requested items, default - &#x60;20&#x60; | [optional] 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#)
[[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateGetSettlementHistoryByInstrumentGet

> Object PrivateGetSettlementHistoryByInstrumentGet (string instrumentName, string type = null, int? count = null)

Retrieves public settlement, delivery and bankruptcy events filtered by instrument name

### Example

```csharp
using System.Diagnostics;
using Org.OpenAPITools.Api;
using Org.OpenAPITools.Client;
using Org.OpenAPITools.Model;

namespace Example
{
    public class PrivateGetSettlementHistoryByInstrumentGetExample
    {
        public static void Main()
        {
            Configuration.Default.BasePath = "https://www.deribit.com/api/v2";
            // Configure HTTP basic authorization: bearerAuth
            Configuration.Default.Username = "YOUR_USERNAME";
            Configuration.Default.Password = "YOUR_PASSWORD";

            var apiInstance = new PrivateApi(Configuration.Default);
            var instrumentName = BTC-PERPETUAL;  // string | Instrument name
            var type = type_example;  // string | Settlement type (optional) 
            var count = 56;  // int? | Number of requested items, default - `20` (optional) 

            try
            {
                // Retrieves public settlement, delivery and bankruptcy events filtered by instrument name
                Object result = apiInstance.PrivateGetSettlementHistoryByInstrumentGet(instrumentName, type, count);
                Debug.WriteLine(result);
            }
            catch (ApiException e)
            {
                Debug.Print("Exception when calling PrivateApi.PrivateGetSettlementHistoryByInstrumentGet: " + e.Message );
                Debug.Print("Status Code: "+ e.ErrorCode);
                Debug.Print(e.StackTrace);
            }
        }
    }
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrumentName** | **string**| Instrument name | 
 **type** | **string**| Settlement type | [optional] 
 **count** | **int?**| Number of requested items, default - &#x60;20&#x60; | [optional] 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#)
[[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateGetSubaccountsGet

> Object PrivateGetSubaccountsGet (bool? withPortfolio = null)

Get information about subaccounts

### Example

```csharp
using System.Diagnostics;
using Org.OpenAPITools.Api;
using Org.OpenAPITools.Client;
using Org.OpenAPITools.Model;

namespace Example
{
    public class PrivateGetSubaccountsGetExample
    {
        public static void Main()
        {
            Configuration.Default.BasePath = "https://www.deribit.com/api/v2";
            // Configure HTTP basic authorization: bearerAuth
            Configuration.Default.Username = "YOUR_USERNAME";
            Configuration.Default.Password = "YOUR_PASSWORD";

            var apiInstance = new PrivateApi(Configuration.Default);
            var withPortfolio = true;  // bool? |  (optional) 

            try
            {
                // Get information about subaccounts
                Object result = apiInstance.PrivateGetSubaccountsGet(withPortfolio);
                Debug.WriteLine(result);
            }
            catch (ApiException e)
            {
                Debug.Print("Exception when calling PrivateApi.PrivateGetSubaccountsGet: " + e.Message );
                Debug.Print("Status Code: "+ e.ErrorCode);
                Debug.Print(e.StackTrace);
            }
        }
    }
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **withPortfolio** | **bool?**|  | [optional] 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#)
[[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateGetTransfersGet

> Object PrivateGetTransfersGet (string currency, int? count = null, int? offset = null)

Adds new entry to address book of given type

### Example

```csharp
using System.Diagnostics;
using Org.OpenAPITools.Api;
using Org.OpenAPITools.Client;
using Org.OpenAPITools.Model;

namespace Example
{
    public class PrivateGetTransfersGetExample
    {
        public static void Main()
        {
            Configuration.Default.BasePath = "https://www.deribit.com/api/v2";
            // Configure HTTP basic authorization: bearerAuth
            Configuration.Default.Username = "YOUR_USERNAME";
            Configuration.Default.Password = "YOUR_PASSWORD";

            var apiInstance = new PrivateApi(Configuration.Default);
            var currency = currency_example;  // string | The currency symbol
            var count = 56;  // int? | Number of requested items, default - `10` (optional) 
            var offset = 10;  // int? | The offset for pagination, default - `0` (optional) 

            try
            {
                // Adds new entry to address book of given type
                Object result = apiInstance.PrivateGetTransfersGet(currency, count, offset);
                Debug.WriteLine(result);
            }
            catch (ApiException e)
            {
                Debug.Print("Exception when calling PrivateApi.PrivateGetTransfersGet: " + e.Message );
                Debug.Print("Status Code: "+ e.ErrorCode);
                Debug.Print(e.StackTrace);
            }
        }
    }
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **string**| The currency symbol | 
 **count** | **int?**| Number of requested items, default - &#x60;10&#x60; | [optional] 
 **offset** | **int?**| The offset for pagination, default - &#x60;0&#x60; | [optional] 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#)
[[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateGetUserTradesByCurrencyAndTimeGet

> Object PrivateGetUserTradesByCurrencyAndTimeGet (string currency, int? startTimestamp, int? endTimestamp, string kind = null, int? count = null, bool? includeOld = null, string sorting = null)

Retrieve the latest user trades that have occurred for instruments in a specific currency symbol and within given time range.

### Example

```csharp
using System.Diagnostics;
using Org.OpenAPITools.Api;
using Org.OpenAPITools.Client;
using Org.OpenAPITools.Model;

namespace Example
{
    public class PrivateGetUserTradesByCurrencyAndTimeGetExample
    {
        public static void Main()
        {
            Configuration.Default.BasePath = "https://www.deribit.com/api/v2";
            // Configure HTTP basic authorization: bearerAuth
            Configuration.Default.Username = "YOUR_USERNAME";
            Configuration.Default.Password = "YOUR_PASSWORD";

            var apiInstance = new PrivateApi(Configuration.Default);
            var currency = currency_example;  // string | The currency symbol
            var startTimestamp = 1536569522277;  // int? | The earliest timestamp to return result for
            var endTimestamp = 1536569522277;  // int? | The most recent timestamp to return result for
            var kind = kind_example;  // string | Instrument kind, if not provided instruments of all kinds are considered (optional) 
            var count = 56;  // int? | Number of requested items, default - `10` (optional) 
            var includeOld = true;  // bool? | Include trades older than 7 days, default - `false` (optional) 
            var sorting = sorting_example;  // string | Direction of results sorting (`default` value means no sorting, results will be returned in order in which they left the database) (optional) 

            try
            {
                // Retrieve the latest user trades that have occurred for instruments in a specific currency symbol and within given time range.
                Object result = apiInstance.PrivateGetUserTradesByCurrencyAndTimeGet(currency, startTimestamp, endTimestamp, kind, count, includeOld, sorting);
                Debug.WriteLine(result);
            }
            catch (ApiException e)
            {
                Debug.Print("Exception when calling PrivateApi.PrivateGetUserTradesByCurrencyAndTimeGet: " + e.Message );
                Debug.Print("Status Code: "+ e.ErrorCode);
                Debug.Print(e.StackTrace);
            }
        }
    }
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **string**| The currency symbol | 
 **startTimestamp** | **int?**| The earliest timestamp to return result for | 
 **endTimestamp** | **int?**| The most recent timestamp to return result for | 
 **kind** | **string**| Instrument kind, if not provided instruments of all kinds are considered | [optional] 
 **count** | **int?**| Number of requested items, default - &#x60;10&#x60; | [optional] 
 **includeOld** | **bool?**| Include trades older than 7 days, default - &#x60;false&#x60; | [optional] 
 **sorting** | **string**| Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) | [optional] 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#)
[[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateGetUserTradesByCurrencyGet

> Object PrivateGetUserTradesByCurrencyGet (string currency, string kind = null, string startId = null, string endId = null, int? count = null, bool? includeOld = null, string sorting = null)

Retrieve the latest user trades that have occurred for instruments in a specific currency symbol.

### Example

```csharp
using System.Diagnostics;
using Org.OpenAPITools.Api;
using Org.OpenAPITools.Client;
using Org.OpenAPITools.Model;

namespace Example
{
    public class PrivateGetUserTradesByCurrencyGetExample
    {
        public static void Main()
        {
            Configuration.Default.BasePath = "https://www.deribit.com/api/v2";
            // Configure HTTP basic authorization: bearerAuth
            Configuration.Default.Username = "YOUR_USERNAME";
            Configuration.Default.Password = "YOUR_PASSWORD";

            var apiInstance = new PrivateApi(Configuration.Default);
            var currency = currency_example;  // string | The currency symbol
            var kind = kind_example;  // string | Instrument kind, if not provided instruments of all kinds are considered (optional) 
            var startId = startId_example;  // string | The ID number of the first trade to be returned (optional) 
            var endId = endId_example;  // string | The ID number of the last trade to be returned (optional) 
            var count = 56;  // int? | Number of requested items, default - `10` (optional) 
            var includeOld = true;  // bool? | Include trades older than 7 days, default - `false` (optional) 
            var sorting = sorting_example;  // string | Direction of results sorting (`default` value means no sorting, results will be returned in order in which they left the database) (optional) 

            try
            {
                // Retrieve the latest user trades that have occurred for instruments in a specific currency symbol.
                Object result = apiInstance.PrivateGetUserTradesByCurrencyGet(currency, kind, startId, endId, count, includeOld, sorting);
                Debug.WriteLine(result);
            }
            catch (ApiException e)
            {
                Debug.Print("Exception when calling PrivateApi.PrivateGetUserTradesByCurrencyGet: " + e.Message );
                Debug.Print("Status Code: "+ e.ErrorCode);
                Debug.Print(e.StackTrace);
            }
        }
    }
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **string**| The currency symbol | 
 **kind** | **string**| Instrument kind, if not provided instruments of all kinds are considered | [optional] 
 **startId** | **string**| The ID number of the first trade to be returned | [optional] 
 **endId** | **string**| The ID number of the last trade to be returned | [optional] 
 **count** | **int?**| Number of requested items, default - &#x60;10&#x60; | [optional] 
 **includeOld** | **bool?**| Include trades older than 7 days, default - &#x60;false&#x60; | [optional] 
 **sorting** | **string**| Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) | [optional] 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#)
[[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateGetUserTradesByInstrumentAndTimeGet

> Object PrivateGetUserTradesByInstrumentAndTimeGet (string instrumentName, int? startTimestamp, int? endTimestamp, int? count = null, bool? includeOld = null, string sorting = null)

Retrieve the latest user trades that have occurred for a specific instrument and within given time range.

### Example

```csharp
using System.Diagnostics;
using Org.OpenAPITools.Api;
using Org.OpenAPITools.Client;
using Org.OpenAPITools.Model;

namespace Example
{
    public class PrivateGetUserTradesByInstrumentAndTimeGetExample
    {
        public static void Main()
        {
            Configuration.Default.BasePath = "https://www.deribit.com/api/v2";
            // Configure HTTP basic authorization: bearerAuth
            Configuration.Default.Username = "YOUR_USERNAME";
            Configuration.Default.Password = "YOUR_PASSWORD";

            var apiInstance = new PrivateApi(Configuration.Default);
            var instrumentName = BTC-PERPETUAL;  // string | Instrument name
            var startTimestamp = 1536569522277;  // int? | The earliest timestamp to return result for
            var endTimestamp = 1536569522277;  // int? | The most recent timestamp to return result for
            var count = 56;  // int? | Number of requested items, default - `10` (optional) 
            var includeOld = true;  // bool? | Include trades older than 7 days, default - `false` (optional) 
            var sorting = sorting_example;  // string | Direction of results sorting (`default` value means no sorting, results will be returned in order in which they left the database) (optional) 

            try
            {
                // Retrieve the latest user trades that have occurred for a specific instrument and within given time range.
                Object result = apiInstance.PrivateGetUserTradesByInstrumentAndTimeGet(instrumentName, startTimestamp, endTimestamp, count, includeOld, sorting);
                Debug.WriteLine(result);
            }
            catch (ApiException e)
            {
                Debug.Print("Exception when calling PrivateApi.PrivateGetUserTradesByInstrumentAndTimeGet: " + e.Message );
                Debug.Print("Status Code: "+ e.ErrorCode);
                Debug.Print(e.StackTrace);
            }
        }
    }
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrumentName** | **string**| Instrument name | 
 **startTimestamp** | **int?**| The earliest timestamp to return result for | 
 **endTimestamp** | **int?**| The most recent timestamp to return result for | 
 **count** | **int?**| Number of requested items, default - &#x60;10&#x60; | [optional] 
 **includeOld** | **bool?**| Include trades older than 7 days, default - &#x60;false&#x60; | [optional] 
 **sorting** | **string**| Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) | [optional] 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#)
[[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateGetUserTradesByInstrumentGet

> Object PrivateGetUserTradesByInstrumentGet (string instrumentName, int? startSeq = null, int? endSeq = null, int? count = null, bool? includeOld = null, string sorting = null)

Retrieve the latest user trades that have occurred for a specific instrument.

### Example

```csharp
using System.Diagnostics;
using Org.OpenAPITools.Api;
using Org.OpenAPITools.Client;
using Org.OpenAPITools.Model;

namespace Example
{
    public class PrivateGetUserTradesByInstrumentGetExample
    {
        public static void Main()
        {
            Configuration.Default.BasePath = "https://www.deribit.com/api/v2";
            // Configure HTTP basic authorization: bearerAuth
            Configuration.Default.Username = "YOUR_USERNAME";
            Configuration.Default.Password = "YOUR_PASSWORD";

            var apiInstance = new PrivateApi(Configuration.Default);
            var instrumentName = BTC-PERPETUAL;  // string | Instrument name
            var startSeq = 56;  // int? | The sequence number of the first trade to be returned (optional) 
            var endSeq = 56;  // int? | The sequence number of the last trade to be returned (optional) 
            var count = 56;  // int? | Number of requested items, default - `10` (optional) 
            var includeOld = true;  // bool? | Include trades older than 7 days, default - `false` (optional) 
            var sorting = sorting_example;  // string | Direction of results sorting (`default` value means no sorting, results will be returned in order in which they left the database) (optional) 

            try
            {
                // Retrieve the latest user trades that have occurred for a specific instrument.
                Object result = apiInstance.PrivateGetUserTradesByInstrumentGet(instrumentName, startSeq, endSeq, count, includeOld, sorting);
                Debug.WriteLine(result);
            }
            catch (ApiException e)
            {
                Debug.Print("Exception when calling PrivateApi.PrivateGetUserTradesByInstrumentGet: " + e.Message );
                Debug.Print("Status Code: "+ e.ErrorCode);
                Debug.Print(e.StackTrace);
            }
        }
    }
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrumentName** | **string**| Instrument name | 
 **startSeq** | **int?**| The sequence number of the first trade to be returned | [optional] 
 **endSeq** | **int?**| The sequence number of the last trade to be returned | [optional] 
 **count** | **int?**| Number of requested items, default - &#x60;10&#x60; | [optional] 
 **includeOld** | **bool?**| Include trades older than 7 days, default - &#x60;false&#x60; | [optional] 
 **sorting** | **string**| Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) | [optional] 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#)
[[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateGetUserTradesByOrderGet

> Object PrivateGetUserTradesByOrderGet (string orderId, string sorting = null)

Retrieve the list of user trades that was created for given order

### Example

```csharp
using System.Diagnostics;
using Org.OpenAPITools.Api;
using Org.OpenAPITools.Client;
using Org.OpenAPITools.Model;

namespace Example
{
    public class PrivateGetUserTradesByOrderGetExample
    {
        public static void Main()
        {
            Configuration.Default.BasePath = "https://www.deribit.com/api/v2";
            // Configure HTTP basic authorization: bearerAuth
            Configuration.Default.Username = "YOUR_USERNAME";
            Configuration.Default.Password = "YOUR_PASSWORD";

            var apiInstance = new PrivateApi(Configuration.Default);
            var orderId = ETH-100234;  // string | The order id
            var sorting = sorting_example;  // string | Direction of results sorting (`default` value means no sorting, results will be returned in order in which they left the database) (optional) 

            try
            {
                // Retrieve the list of user trades that was created for given order
                Object result = apiInstance.PrivateGetUserTradesByOrderGet(orderId, sorting);
                Debug.WriteLine(result);
            }
            catch (ApiException e)
            {
                Debug.Print("Exception when calling PrivateApi.PrivateGetUserTradesByOrderGet: " + e.Message );
                Debug.Print("Status Code: "+ e.ErrorCode);
                Debug.Print(e.StackTrace);
            }
        }
    }
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **orderId** | **string**| The order id | 
 **sorting** | **string**| Direction of results sorting (&#x60;default&#x60; value means no sorting, results will be returned in order in which they left the database) | [optional] 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#)
[[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateGetWithdrawalsGet

> Object PrivateGetWithdrawalsGet (string currency, int? count = null, int? offset = null)

Retrieve the latest users withdrawals

### Example

```csharp
using System.Diagnostics;
using Org.OpenAPITools.Api;
using Org.OpenAPITools.Client;
using Org.OpenAPITools.Model;

namespace Example
{
    public class PrivateGetWithdrawalsGetExample
    {
        public static void Main()
        {
            Configuration.Default.BasePath = "https://www.deribit.com/api/v2";
            // Configure HTTP basic authorization: bearerAuth
            Configuration.Default.Username = "YOUR_USERNAME";
            Configuration.Default.Password = "YOUR_PASSWORD";

            var apiInstance = new PrivateApi(Configuration.Default);
            var currency = currency_example;  // string | The currency symbol
            var count = 56;  // int? | Number of requested items, default - `10` (optional) 
            var offset = 10;  // int? | The offset for pagination, default - `0` (optional) 

            try
            {
                // Retrieve the latest users withdrawals
                Object result = apiInstance.PrivateGetWithdrawalsGet(currency, count, offset);
                Debug.WriteLine(result);
            }
            catch (ApiException e)
            {
                Debug.Print("Exception when calling PrivateApi.PrivateGetWithdrawalsGet: " + e.Message );
                Debug.Print("Status Code: "+ e.ErrorCode);
                Debug.Print(e.StackTrace);
            }
        }
    }
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **string**| The currency symbol | 
 **count** | **int?**| Number of requested items, default - &#x60;10&#x60; | [optional] 
 **offset** | **int?**| The offset for pagination, default - &#x60;0&#x60; | [optional] 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#)
[[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateRemoveFromAddressBookGet

> Object PrivateRemoveFromAddressBookGet (string currency, string type, string address, string tfa = null)

Adds new entry to address book of given type

### Example

```csharp
using System.Diagnostics;
using Org.OpenAPITools.Api;
using Org.OpenAPITools.Client;
using Org.OpenAPITools.Model;

namespace Example
{
    public class PrivateRemoveFromAddressBookGetExample
    {
        public static void Main()
        {
            Configuration.Default.BasePath = "https://www.deribit.com/api/v2";
            // Configure HTTP basic authorization: bearerAuth
            Configuration.Default.Username = "YOUR_USERNAME";
            Configuration.Default.Password = "YOUR_PASSWORD";

            var apiInstance = new PrivateApi(Configuration.Default);
            var currency = currency_example;  // string | The currency symbol
            var type = type_example;  // string | Address book type
            var address = address_example;  // string | Address in currency format, it must be in address book
            var tfa = tfa_example;  // string | TFA code, required when TFA is enabled for current account (optional) 

            try
            {
                // Adds new entry to address book of given type
                Object result = apiInstance.PrivateRemoveFromAddressBookGet(currency, type, address, tfa);
                Debug.WriteLine(result);
            }
            catch (ApiException e)
            {
                Debug.Print("Exception when calling PrivateApi.PrivateRemoveFromAddressBookGet: " + e.Message );
                Debug.Print("Status Code: "+ e.ErrorCode);
                Debug.Print(e.StackTrace);
            }
        }
    }
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **string**| The currency symbol | 
 **type** | **string**| Address book type | 
 **address** | **string**| Address in currency format, it must be in address book | 
 **tfa** | **string**| TFA code, required when TFA is enabled for current account | [optional] 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#)
[[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateSellGet

> Object PrivateSellGet (string instrumentName, decimal? amount, string type = null, string label = null, decimal? price = null, string timeInForce = null, decimal? maxShow = null, bool? postOnly = null, bool? reduceOnly = null, decimal? stopPrice = null, string trigger = null, string advanced = null)

Places a sell order for an instrument.

### Example

```csharp
using System.Diagnostics;
using Org.OpenAPITools.Api;
using Org.OpenAPITools.Client;
using Org.OpenAPITools.Model;

namespace Example
{
    public class PrivateSellGetExample
    {
        public static void Main()
        {
            Configuration.Default.BasePath = "https://www.deribit.com/api/v2";
            // Configure HTTP basic authorization: bearerAuth
            Configuration.Default.Username = "YOUR_USERNAME";
            Configuration.Default.Password = "YOUR_PASSWORD";

            var apiInstance = new PrivateApi(Configuration.Default);
            var instrumentName = BTC-PERPETUAL;  // string | Instrument name
            var amount = 8.14;  // decimal? | It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH
            var type = type_example;  // string | The order type, default: `\"limit\"` (optional) 
            var label = label_example;  // string | user defined label for the order (maximum 32 characters) (optional) 
            var price = 8.14;  // decimal? | <p>The order price in base currency (Only for limit and stop_limit orders)</p> <p>When adding order with advanced=usd, the field price should be the option price value in USD.</p> <p>When adding order with advanced=implv, the field price should be a value of implied volatility in percentages. For example,  price=100, means implied volatility of 100%</p> (optional) 
            var timeInForce = timeInForce_example;  // string | <p>Specifies how long the order remains in effect. Default `\"good_til_cancelled\"`</p> <ul> <li>`\"good_til_cancelled\"` - unfilled order remains in order book until cancelled</li> <li>`\"fill_or_kill\"` - execute a transaction immediately and completely or not at all</li> <li>`\"immediate_or_cancel\"` - execute a transaction immediately, and any portion of the order that cannot be immediately filled is cancelled</li> </ul> (optional)  (default to good_til_cancelled)
            var maxShow = 8.14;  // decimal? | Maximum amount within an order to be shown to other customers, `0` for invisible order (optional)  (default to 1M)
            var postOnly = true;  // bool? | <p>If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.</p> <p>Only valid in combination with time_in_force=`\"good_til_cancelled\"`</p> (optional)  (default to true)
            var reduceOnly = true;  // bool? | If `true`, the order is considered reduce-only which is intended to only reduce a current position (optional)  (default to false)
            var stopPrice = 8.14;  // decimal? | Stop price, required for stop limit orders (Only for stop orders) (optional) 
            var trigger = trigger_example;  // string | Defines trigger type, required for `\"stop_limit\"` order type (optional) 
            var advanced = advanced_example;  // string | Advanced option order type. (Only for options) (optional) 

            try
            {
                // Places a sell order for an instrument.
                Object result = apiInstance.PrivateSellGet(instrumentName, amount, type, label, price, timeInForce, maxShow, postOnly, reduceOnly, stopPrice, trigger, advanced);
                Debug.WriteLine(result);
            }
            catch (ApiException e)
            {
                Debug.Print("Exception when calling PrivateApi.PrivateSellGet: " + e.Message );
                Debug.Print("Status Code: "+ e.ErrorCode);
                Debug.Print(e.StackTrace);
            }
        }
    }
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **instrumentName** | **string**| Instrument name | 
 **amount** | **decimal?**| It represents the requested order size. For perpetual and futures the amount is in USD units, for options it is amount of corresponding cryptocurrency contracts, e.g., BTC or ETH | 
 **type** | **string**| The order type, default: &#x60;\&quot;limit\&quot;&#x60; | [optional] 
 **label** | **string**| user defined label for the order (maximum 32 characters) | [optional] 
 **price** | **decimal?**| &lt;p&gt;The order price in base currency (Only for limit and stop_limit orders)&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;usd, the field price should be the option price value in USD.&lt;/p&gt; &lt;p&gt;When adding order with advanced&#x3D;implv, the field price should be a value of implied volatility in percentages. For example,  price&#x3D;100, means implied volatility of 100%&lt;/p&gt; | [optional] 
 **timeInForce** | **string**| &lt;p&gt;Specifies how long the order remains in effect. Default &#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; &lt;ul&gt; &lt;li&gt;&#x60;\&quot;good_til_cancelled\&quot;&#x60; - unfilled order remains in order book until cancelled&lt;/li&gt; &lt;li&gt;&#x60;\&quot;fill_or_kill\&quot;&#x60; - execute a transaction immediately and completely or not at all&lt;/li&gt; &lt;li&gt;&#x60;\&quot;immediate_or_cancel\&quot;&#x60; - execute a transaction immediately, and any portion of the order that cannot be immediately filled is cancelled&lt;/li&gt; &lt;/ul&gt; | [optional] [default to good_til_cancelled]
 **maxShow** | **decimal?**| Maximum amount within an order to be shown to other customers, &#x60;0&#x60; for invisible order | [optional] [default to 1M]
 **postOnly** | **bool?**| &lt;p&gt;If true, the order is considered post-only. If the new price would cause the order to be filled immediately (as taker), the price will be changed to be just below the bid.&lt;/p&gt; &lt;p&gt;Only valid in combination with time_in_force&#x3D;&#x60;\&quot;good_til_cancelled\&quot;&#x60;&lt;/p&gt; | [optional] [default to true]
 **reduceOnly** | **bool?**| If &#x60;true&#x60;, the order is considered reduce-only which is intended to only reduce a current position | [optional] [default to false]
 **stopPrice** | **decimal?**| Stop price, required for stop limit orders (Only for stop orders) | [optional] 
 **trigger** | **string**| Defines trigger type, required for &#x60;\&quot;stop_limit\&quot;&#x60; order type | [optional] 
 **advanced** | **string**| Advanced option order type. (Only for options) | [optional] 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#)
[[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateSetAnnouncementAsReadGet

> Object PrivateSetAnnouncementAsReadGet (decimal? announcementId)

Marks an announcement as read, so it will not be shown in `get_new_announcements`.

### Example

```csharp
using System.Diagnostics;
using Org.OpenAPITools.Api;
using Org.OpenAPITools.Client;
using Org.OpenAPITools.Model;

namespace Example
{
    public class PrivateSetAnnouncementAsReadGetExample
    {
        public static void Main()
        {
            Configuration.Default.BasePath = "https://www.deribit.com/api/v2";
            // Configure HTTP basic authorization: bearerAuth
            Configuration.Default.Username = "YOUR_USERNAME";
            Configuration.Default.Password = "YOUR_PASSWORD";

            var apiInstance = new PrivateApi(Configuration.Default);
            var announcementId = 8.14;  // decimal? | the ID of the announcement

            try
            {
                // Marks an announcement as read, so it will not be shown in `get_new_announcements`.
                Object result = apiInstance.PrivateSetAnnouncementAsReadGet(announcementId);
                Debug.WriteLine(result);
            }
            catch (ApiException e)
            {
                Debug.Print("Exception when calling PrivateApi.PrivateSetAnnouncementAsReadGet: " + e.Message );
                Debug.Print("Status Code: "+ e.ErrorCode);
                Debug.Print(e.StackTrace);
            }
        }
    }
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **announcementId** | **decimal?**| the ID of the announcement | 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#)
[[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateSetEmailForSubaccountGet

> Object PrivateSetEmailForSubaccountGet (int? sid, string email)

Assign an email address to a subaccount. User will receive an email with confirmation link.

### Example

```csharp
using System.Diagnostics;
using Org.OpenAPITools.Api;
using Org.OpenAPITools.Client;
using Org.OpenAPITools.Model;

namespace Example
{
    public class PrivateSetEmailForSubaccountGetExample
    {
        public static void Main()
        {
            Configuration.Default.BasePath = "https://www.deribit.com/api/v2";
            // Configure HTTP basic authorization: bearerAuth
            Configuration.Default.Username = "YOUR_USERNAME";
            Configuration.Default.Password = "YOUR_PASSWORD";

            var apiInstance = new PrivateApi(Configuration.Default);
            var sid = 56;  // int? | The user id for the subaccount
            var email = subaccount_1@email.com;  // string | The email address for the subaccount

            try
            {
                // Assign an email address to a subaccount. User will receive an email with confirmation link.
                Object result = apiInstance.PrivateSetEmailForSubaccountGet(sid, email);
                Debug.WriteLine(result);
            }
            catch (ApiException e)
            {
                Debug.Print("Exception when calling PrivateApi.PrivateSetEmailForSubaccountGet: " + e.Message );
                Debug.Print("Status Code: "+ e.ErrorCode);
                Debug.Print(e.StackTrace);
            }
        }
    }
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **sid** | **int?**| The user id for the subaccount | 
 **email** | **string**| The email address for the subaccount | 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#)
[[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateSetEmailLanguageGet

> Object PrivateSetEmailLanguageGet (string language)

Changes the language to be used for emails.

### Example

```csharp
using System.Diagnostics;
using Org.OpenAPITools.Api;
using Org.OpenAPITools.Client;
using Org.OpenAPITools.Model;

namespace Example
{
    public class PrivateSetEmailLanguageGetExample
    {
        public static void Main()
        {
            Configuration.Default.BasePath = "https://www.deribit.com/api/v2";
            // Configure HTTP basic authorization: bearerAuth
            Configuration.Default.Username = "YOUR_USERNAME";
            Configuration.Default.Password = "YOUR_PASSWORD";

            var apiInstance = new PrivateApi(Configuration.Default);
            var language = en;  // string | The abbreviated language name. Valid values include `\"en\"`, `\"ko\"`, `\"zh\"`

            try
            {
                // Changes the language to be used for emails.
                Object result = apiInstance.PrivateSetEmailLanguageGet(language);
                Debug.WriteLine(result);
            }
            catch (ApiException e)
            {
                Debug.Print("Exception when calling PrivateApi.PrivateSetEmailLanguageGet: " + e.Message );
                Debug.Print("Status Code: "+ e.ErrorCode);
                Debug.Print(e.StackTrace);
            }
        }
    }
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **language** | **string**| The abbreviated language name. Valid values include &#x60;\&quot;en\&quot;&#x60;, &#x60;\&quot;ko\&quot;&#x60;, &#x60;\&quot;zh\&quot;&#x60; | 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#)
[[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateSetPasswordForSubaccountGet

> Object PrivateSetPasswordForSubaccountGet (int? sid, string password)

Set the password for the subaccount

### Example

```csharp
using System.Diagnostics;
using Org.OpenAPITools.Api;
using Org.OpenAPITools.Client;
using Org.OpenAPITools.Model;

namespace Example
{
    public class PrivateSetPasswordForSubaccountGetExample
    {
        public static void Main()
        {
            Configuration.Default.BasePath = "https://www.deribit.com/api/v2";
            // Configure HTTP basic authorization: bearerAuth
            Configuration.Default.Username = "YOUR_USERNAME";
            Configuration.Default.Password = "YOUR_PASSWORD";

            var apiInstance = new PrivateApi(Configuration.Default);
            var sid = 56;  // int? | The user id for the subaccount
            var password = password_example;  // string | The password for the subaccount

            try
            {
                // Set the password for the subaccount
                Object result = apiInstance.PrivateSetPasswordForSubaccountGet(sid, password);
                Debug.WriteLine(result);
            }
            catch (ApiException e)
            {
                Debug.Print("Exception when calling PrivateApi.PrivateSetPasswordForSubaccountGet: " + e.Message );
                Debug.Print("Status Code: "+ e.ErrorCode);
                Debug.Print(e.StackTrace);
            }
        }
    }
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **sid** | **int?**| The user id for the subaccount | 
 **password** | **string**| The password for the subaccount | 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#)
[[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateSubmitTransferToSubaccountGet

> Object PrivateSubmitTransferToSubaccountGet (string currency, decimal? amount, int? destination)

Transfer funds to a subaccount.

### Example

```csharp
using System.Diagnostics;
using Org.OpenAPITools.Api;
using Org.OpenAPITools.Client;
using Org.OpenAPITools.Model;

namespace Example
{
    public class PrivateSubmitTransferToSubaccountGetExample
    {
        public static void Main()
        {
            Configuration.Default.BasePath = "https://www.deribit.com/api/v2";
            // Configure HTTP basic authorization: bearerAuth
            Configuration.Default.Username = "YOUR_USERNAME";
            Configuration.Default.Password = "YOUR_PASSWORD";

            var apiInstance = new PrivateApi(Configuration.Default);
            var currency = currency_example;  // string | The currency symbol
            var amount = 8.14;  // decimal? | Amount of funds to be transferred
            var destination = 1;  // int? | Id of destination subaccount

            try
            {
                // Transfer funds to a subaccount.
                Object result = apiInstance.PrivateSubmitTransferToSubaccountGet(currency, amount, destination);
                Debug.WriteLine(result);
            }
            catch (ApiException e)
            {
                Debug.Print("Exception when calling PrivateApi.PrivateSubmitTransferToSubaccountGet: " + e.Message );
                Debug.Print("Status Code: "+ e.ErrorCode);
                Debug.Print(e.StackTrace);
            }
        }
    }
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **string**| The currency symbol | 
 **amount** | **decimal?**| Amount of funds to be transferred | 
 **destination** | **int?**| Id of destination subaccount | 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#)
[[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateSubmitTransferToUserGet

> Object PrivateSubmitTransferToUserGet (string currency, decimal? amount, string destination, string tfa = null)

Transfer funds to a another user.

### Example

```csharp
using System.Diagnostics;
using Org.OpenAPITools.Api;
using Org.OpenAPITools.Client;
using Org.OpenAPITools.Model;

namespace Example
{
    public class PrivateSubmitTransferToUserGetExample
    {
        public static void Main()
        {
            Configuration.Default.BasePath = "https://www.deribit.com/api/v2";
            // Configure HTTP basic authorization: bearerAuth
            Configuration.Default.Username = "YOUR_USERNAME";
            Configuration.Default.Password = "YOUR_PASSWORD";

            var apiInstance = new PrivateApi(Configuration.Default);
            var currency = currency_example;  // string | The currency symbol
            var amount = 8.14;  // decimal? | Amount of funds to be transferred
            var destination = destination_example;  // string | Destination address from address book
            var tfa = tfa_example;  // string | TFA code, required when TFA is enabled for current account (optional) 

            try
            {
                // Transfer funds to a another user.
                Object result = apiInstance.PrivateSubmitTransferToUserGet(currency, amount, destination, tfa);
                Debug.WriteLine(result);
            }
            catch (ApiException e)
            {
                Debug.Print("Exception when calling PrivateApi.PrivateSubmitTransferToUserGet: " + e.Message );
                Debug.Print("Status Code: "+ e.ErrorCode);
                Debug.Print(e.StackTrace);
            }
        }
    }
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **string**| The currency symbol | 
 **amount** | **decimal?**| Amount of funds to be transferred | 
 **destination** | **string**| Destination address from address book | 
 **tfa** | **string**| TFA code, required when TFA is enabled for current account | [optional] 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#)
[[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateToggleDepositAddressCreationGet

> Object PrivateToggleDepositAddressCreationGet (string currency, bool? state)

Enable or disable deposit address creation

### Example

```csharp
using System.Diagnostics;
using Org.OpenAPITools.Api;
using Org.OpenAPITools.Client;
using Org.OpenAPITools.Model;

namespace Example
{
    public class PrivateToggleDepositAddressCreationGetExample
    {
        public static void Main()
        {
            Configuration.Default.BasePath = "https://www.deribit.com/api/v2";
            // Configure HTTP basic authorization: bearerAuth
            Configuration.Default.Username = "YOUR_USERNAME";
            Configuration.Default.Password = "YOUR_PASSWORD";

            var apiInstance = new PrivateApi(Configuration.Default);
            var currency = currency_example;  // string | The currency symbol
            var state = true;  // bool? | 

            try
            {
                // Enable or disable deposit address creation
                Object result = apiInstance.PrivateToggleDepositAddressCreationGet(currency, state);
                Debug.WriteLine(result);
            }
            catch (ApiException e)
            {
                Debug.Print("Exception when calling PrivateApi.PrivateToggleDepositAddressCreationGet: " + e.Message );
                Debug.Print("Status Code: "+ e.ErrorCode);
                Debug.Print(e.StackTrace);
            }
        }
    }
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **string**| The currency symbol | 
 **state** | **bool?**|  | 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#)
[[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateToggleNotificationsFromSubaccountGet

> Object PrivateToggleNotificationsFromSubaccountGet (int? sid, bool? state)

Enable or disable sending of notifications for the subaccount.

### Example

```csharp
using System.Diagnostics;
using Org.OpenAPITools.Api;
using Org.OpenAPITools.Client;
using Org.OpenAPITools.Model;

namespace Example
{
    public class PrivateToggleNotificationsFromSubaccountGetExample
    {
        public static void Main()
        {
            Configuration.Default.BasePath = "https://www.deribit.com/api/v2";
            // Configure HTTP basic authorization: bearerAuth
            Configuration.Default.Username = "YOUR_USERNAME";
            Configuration.Default.Password = "YOUR_PASSWORD";

            var apiInstance = new PrivateApi(Configuration.Default);
            var sid = 56;  // int? | The user id for the subaccount
            var state = true;  // bool? | enable (`true`) or disable (`false`) notifications

            try
            {
                // Enable or disable sending of notifications for the subaccount.
                Object result = apiInstance.PrivateToggleNotificationsFromSubaccountGet(sid, state);
                Debug.WriteLine(result);
            }
            catch (ApiException e)
            {
                Debug.Print("Exception when calling PrivateApi.PrivateToggleNotificationsFromSubaccountGet: " + e.Message );
                Debug.Print("Status Code: "+ e.ErrorCode);
                Debug.Print(e.StackTrace);
            }
        }
    }
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **sid** | **int?**| The user id for the subaccount | 
 **state** | **bool?**| enable (&#x60;true&#x60;) or disable (&#x60;false&#x60;) notifications | 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#)
[[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateToggleSubaccountLoginGet

> Object PrivateToggleSubaccountLoginGet (int? sid, string state)

Enable or disable login for a subaccount. If login is disabled and a session for the subaccount exists, this session will be terminated.

### Example

```csharp
using System.Diagnostics;
using Org.OpenAPITools.Api;
using Org.OpenAPITools.Client;
using Org.OpenAPITools.Model;

namespace Example
{
    public class PrivateToggleSubaccountLoginGetExample
    {
        public static void Main()
        {
            Configuration.Default.BasePath = "https://www.deribit.com/api/v2";
            // Configure HTTP basic authorization: bearerAuth
            Configuration.Default.Username = "YOUR_USERNAME";
            Configuration.Default.Password = "YOUR_PASSWORD";

            var apiInstance = new PrivateApi(Configuration.Default);
            var sid = 56;  // int? | The user id for the subaccount
            var state = state_example;  // string | enable or disable login.

            try
            {
                // Enable or disable login for a subaccount. If login is disabled and a session for the subaccount exists, this session will be terminated.
                Object result = apiInstance.PrivateToggleSubaccountLoginGet(sid, state);
                Debug.WriteLine(result);
            }
            catch (ApiException e)
            {
                Debug.Print("Exception when calling PrivateApi.PrivateToggleSubaccountLoginGet: " + e.Message );
                Debug.Print("Status Code: "+ e.ErrorCode);
                Debug.Print(e.StackTrace);
            }
        }
    }
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **sid** | **int?**| The user id for the subaccount | 
 **state** | **string**| enable or disable login. | 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#)
[[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)


## PrivateWithdrawGet

> Object PrivateWithdrawGet (string currency, string address, decimal? amount, string priority = null, string tfa = null)

Creates a new withdrawal request

### Example

```csharp
using System.Diagnostics;
using Org.OpenAPITools.Api;
using Org.OpenAPITools.Client;
using Org.OpenAPITools.Model;

namespace Example
{
    public class PrivateWithdrawGetExample
    {
        public static void Main()
        {
            Configuration.Default.BasePath = "https://www.deribit.com/api/v2";
            // Configure HTTP basic authorization: bearerAuth
            Configuration.Default.Username = "YOUR_USERNAME";
            Configuration.Default.Password = "YOUR_PASSWORD";

            var apiInstance = new PrivateApi(Configuration.Default);
            var currency = currency_example;  // string | The currency symbol
            var address = address_example;  // string | Address in currency format, it must be in address book
            var amount = 8.14;  // decimal? | Amount of funds to be withdrawn
            var priority = priority_example;  // string | Withdrawal priority, optional for BTC, default: `high` (optional) 
            var tfa = tfa_example;  // string | TFA code, required when TFA is enabled for current account (optional) 

            try
            {
                // Creates a new withdrawal request
                Object result = apiInstance.PrivateWithdrawGet(currency, address, amount, priority, tfa);
                Debug.WriteLine(result);
            }
            catch (ApiException e)
            {
                Debug.Print("Exception when calling PrivateApi.PrivateWithdrawGet: " + e.Message );
                Debug.Print("Status Code: "+ e.ErrorCode);
                Debug.Print(e.StackTrace);
            }
        }
    }
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **currency** | **string**| The currency symbol | 
 **address** | **string**| Address in currency format, it must be in address book | 
 **amount** | **decimal?**| Amount of funds to be withdrawn | 
 **priority** | **string**| Withdrawal priority, optional for BTC, default: &#x60;high&#x60; | [optional] 
 **tfa** | **string**| TFA code, required when TFA is enabled for current account | [optional] 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

[[Back to top]](#)
[[Back to API list]](../README.md#documentation-for-api-endpoints)
[[Back to Model list]](../README.md#documentation-for-models)
[[Back to README]](../README.md)

