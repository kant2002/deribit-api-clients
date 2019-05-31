# openapi-android-client

## Requirements

Building the API client library requires [Maven](https://maven.apache.org/) to be installed.

## Installation

To install the API client library to your local Maven repository, simply execute:

```shell
mvn install
```

To deploy it to a remote Maven repository instead, configure the settings of the repository and execute:

```shell
mvn deploy
```

Refer to the [official documentation](https://maven.apache.org/plugins/maven-deploy-plugin/usage.html) for more information.

### Maven users

Add this dependency to your project's POM:

```xml
<dependency>
    <groupId>org.openapitools</groupId>
    <artifactId>openapi-android-client</artifactId>
    <version>1.0.0</version>
    <scope>compile</scope>
</dependency>
```

### Gradle users

Add this dependency to your project's build file:

```groovy
compile "org.openapitools:openapi-android-client:1.0.0"
```

### Others

At first generate the JAR by executing:

    mvn package

Then manually install the following JARs:

- target/openapi-android-client-1.0.0.jar
- target/lib/*.jar

## Getting Started

Please follow the [installation](#installation) instruction and execute the following Java code:

```java

import org.openapitools.client.api.AccountManagementApi;

public class AccountManagementApiExample {

    public static void main(String[] args) {
        AccountManagementApi apiInstance = new AccountManagementApi();
        Integer sid = null; // Integer | The user id for the subaccount
        String name = newUserName; // String | The new user name
        try {
            Object result = apiInstance.privateChangeSubaccountNameGet(sid, name);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling AccountManagementApi#privateChangeSubaccountNameGet");
            e.printStackTrace();
        }
    }
}

```

## Documentation for API Endpoints

All URIs are relative to *https://www.deribit.com/api/v2*

Class | Method | HTTP request | Description
------------ | ------------- | ------------- | -------------
*AccountManagementApi* | [**privateChangeSubaccountNameGet**](docs/AccountManagementApi.md#privateChangeSubaccountNameGet) | **GET** /private/change_subaccount_name | Change the user name for a subaccount
*AccountManagementApi* | [**privateCreateSubaccountGet**](docs/AccountManagementApi.md#privateCreateSubaccountGet) | **GET** /private/create_subaccount | Create a new subaccount
*AccountManagementApi* | [**privateDisableTfaForSubaccountGet**](docs/AccountManagementApi.md#privateDisableTfaForSubaccountGet) | **GET** /private/disable_tfa_for_subaccount | Disable two factor authentication for a subaccount.
*AccountManagementApi* | [**privateGetAccountSummaryGet**](docs/AccountManagementApi.md#privateGetAccountSummaryGet) | **GET** /private/get_account_summary | Retrieves user account summary.
*AccountManagementApi* | [**privateGetEmailLanguageGet**](docs/AccountManagementApi.md#privateGetEmailLanguageGet) | **GET** /private/get_email_language | Retrieves the language to be used for emails.
*AccountManagementApi* | [**privateGetNewAnnouncementsGet**](docs/AccountManagementApi.md#privateGetNewAnnouncementsGet) | **GET** /private/get_new_announcements | Retrieves announcements that have not been marked read by the user.
*AccountManagementApi* | [**privateGetPositionGet**](docs/AccountManagementApi.md#privateGetPositionGet) | **GET** /private/get_position | Retrieve user position.
*AccountManagementApi* | [**privateGetPositionsGet**](docs/AccountManagementApi.md#privateGetPositionsGet) | **GET** /private/get_positions | Retrieve user positions.
*AccountManagementApi* | [**privateGetSubaccountsGet**](docs/AccountManagementApi.md#privateGetSubaccountsGet) | **GET** /private/get_subaccounts | Get information about subaccounts
*AccountManagementApi* | [**privateSetAnnouncementAsReadGet**](docs/AccountManagementApi.md#privateSetAnnouncementAsReadGet) | **GET** /private/set_announcement_as_read | Marks an announcement as read, so it will not be shown in &#x60;get_new_announcements&#x60;.
*AccountManagementApi* | [**privateSetEmailForSubaccountGet**](docs/AccountManagementApi.md#privateSetEmailForSubaccountGet) | **GET** /private/set_email_for_subaccount | Assign an email address to a subaccount. User will receive an email with confirmation link.
*AccountManagementApi* | [**privateSetEmailLanguageGet**](docs/AccountManagementApi.md#privateSetEmailLanguageGet) | **GET** /private/set_email_language | Changes the language to be used for emails.
*AccountManagementApi* | [**privateSetPasswordForSubaccountGet**](docs/AccountManagementApi.md#privateSetPasswordForSubaccountGet) | **GET** /private/set_password_for_subaccount | Set the password for the subaccount
*AccountManagementApi* | [**privateToggleNotificationsFromSubaccountGet**](docs/AccountManagementApi.md#privateToggleNotificationsFromSubaccountGet) | **GET** /private/toggle_notifications_from_subaccount | Enable or disable sending of notifications for the subaccount.
*AccountManagementApi* | [**privateToggleSubaccountLoginGet**](docs/AccountManagementApi.md#privateToggleSubaccountLoginGet) | **GET** /private/toggle_subaccount_login | Enable or disable login for a subaccount. If login is disabled and a session for the subaccount exists, this session will be terminated.
*AccountManagementApi* | [**publicGetAnnouncementsGet**](docs/AccountManagementApi.md#publicGetAnnouncementsGet) | **GET** /public/get_announcements | Retrieves announcements from the last 30 days.
*AuthenticationApi* | [**publicAuthGet**](docs/AuthenticationApi.md#publicAuthGet) | **GET** /public/auth | Authenticate
*InternalApi* | [**privateAddToAddressBookGet**](docs/InternalApi.md#privateAddToAddressBookGet) | **GET** /private/add_to_address_book | Adds new entry to address book of given type
*InternalApi* | [**privateDisableTfaWithRecoveryCodeGet**](docs/InternalApi.md#privateDisableTfaWithRecoveryCodeGet) | **GET** /private/disable_tfa_with_recovery_code | Disables TFA with one time recovery code
*InternalApi* | [**privateGetAddressBookGet**](docs/InternalApi.md#privateGetAddressBookGet) | **GET** /private/get_address_book | Retrieves address book of given type
*InternalApi* | [**privateRemoveFromAddressBookGet**](docs/InternalApi.md#privateRemoveFromAddressBookGet) | **GET** /private/remove_from_address_book | Adds new entry to address book of given type
*InternalApi* | [**privateSubmitTransferToSubaccountGet**](docs/InternalApi.md#privateSubmitTransferToSubaccountGet) | **GET** /private/submit_transfer_to_subaccount | Transfer funds to a subaccount.
*InternalApi* | [**privateSubmitTransferToUserGet**](docs/InternalApi.md#privateSubmitTransferToUserGet) | **GET** /private/submit_transfer_to_user | Transfer funds to a another user.
*InternalApi* | [**privateToggleDepositAddressCreationGet**](docs/InternalApi.md#privateToggleDepositAddressCreationGet) | **GET** /private/toggle_deposit_address_creation | Enable or disable deposit address creation
*InternalApi* | [**publicGetFooterGet**](docs/InternalApi.md#publicGetFooterGet) | **GET** /public/get_footer | Get information to be displayed in the footer of the website.
*InternalApi* | [**publicGetOptionMarkPricesGet**](docs/InternalApi.md#publicGetOptionMarkPricesGet) | **GET** /public/get_option_mark_prices | Retrives market prices and its implied volatility of options instruments
*InternalApi* | [**publicValidateFieldGet**](docs/InternalApi.md#publicValidateFieldGet) | **GET** /public/validate_field | Method used to introduce the client software connected to Deribit platform over websocket. Provided data may have an impact on the maintained connection and will be collected for internal statistical purposes. In response, Deribit will also introduce itself.
*MarketDataApi* | [**publicGetBookSummaryByCurrencyGet**](docs/MarketDataApi.md#publicGetBookSummaryByCurrencyGet) | **GET** /public/get_book_summary_by_currency | Retrieves the summary information such as open interest, 24h volume, etc. for all instruments for the currency (optionally filtered by kind).
*MarketDataApi* | [**publicGetBookSummaryByInstrumentGet**](docs/MarketDataApi.md#publicGetBookSummaryByInstrumentGet) | **GET** /public/get_book_summary_by_instrument | Retrieves the summary information such as open interest, 24h volume, etc. for a specific instrument.
*MarketDataApi* | [**publicGetContractSizeGet**](docs/MarketDataApi.md#publicGetContractSizeGet) | **GET** /public/get_contract_size | Retrieves contract size of provided instrument.
*MarketDataApi* | [**publicGetCurrenciesGet**](docs/MarketDataApi.md#publicGetCurrenciesGet) | **GET** /public/get_currencies | Retrieves all cryptocurrencies supported by the API.
*MarketDataApi* | [**publicGetFundingChartDataGet**](docs/MarketDataApi.md#publicGetFundingChartDataGet) | **GET** /public/get_funding_chart_data | Retrieve the latest user trades that have occurred for PERPETUAL instruments in a specific currency symbol and within given time range.
*MarketDataApi* | [**publicGetHistoricalVolatilityGet**](docs/MarketDataApi.md#publicGetHistoricalVolatilityGet) | **GET** /public/get_historical_volatility | Provides information about historical volatility for given cryptocurrency.
*MarketDataApi* | [**publicGetIndexGet**](docs/MarketDataApi.md#publicGetIndexGet) | **GET** /public/get_index | Retrieves the current index price for the instruments, for the selected currency.
*MarketDataApi* | [**publicGetInstrumentsGet**](docs/MarketDataApi.md#publicGetInstrumentsGet) | **GET** /public/get_instruments | Retrieves available trading instruments. This method can be used to see which instruments are available for trading, or which instruments have existed historically.
*MarketDataApi* | [**publicGetLastSettlementsByCurrencyGet**](docs/MarketDataApi.md#publicGetLastSettlementsByCurrencyGet) | **GET** /public/get_last_settlements_by_currency | Retrieves historical settlement, delivery and bankruptcy events coming from all instruments within given currency.
*MarketDataApi* | [**publicGetLastSettlementsByInstrumentGet**](docs/MarketDataApi.md#publicGetLastSettlementsByInstrumentGet) | **GET** /public/get_last_settlements_by_instrument | Retrieves historical public settlement, delivery and bankruptcy events filtered by instrument name.
*MarketDataApi* | [**publicGetLastTradesByCurrencyAndTimeGet**](docs/MarketDataApi.md#publicGetLastTradesByCurrencyAndTimeGet) | **GET** /public/get_last_trades_by_currency_and_time | Retrieve the latest trades that have occurred for instruments in a specific currency symbol and within given time range.
*MarketDataApi* | [**publicGetLastTradesByCurrencyGet**](docs/MarketDataApi.md#publicGetLastTradesByCurrencyGet) | **GET** /public/get_last_trades_by_currency | Retrieve the latest trades that have occurred for instruments in a specific currency symbol.
*MarketDataApi* | [**publicGetLastTradesByInstrumentAndTimeGet**](docs/MarketDataApi.md#publicGetLastTradesByInstrumentAndTimeGet) | **GET** /public/get_last_trades_by_instrument_and_time | Retrieve the latest trades that have occurred for a specific instrument and within given time range.
*MarketDataApi* | [**publicGetLastTradesByInstrumentGet**](docs/MarketDataApi.md#publicGetLastTradesByInstrumentGet) | **GET** /public/get_last_trades_by_instrument | Retrieve the latest trades that have occurred for a specific instrument.
*MarketDataApi* | [**publicGetOrderBookGet**](docs/MarketDataApi.md#publicGetOrderBookGet) | **GET** /public/get_order_book | Retrieves the order book, along with other market values for a given instrument.
*MarketDataApi* | [**publicGetTradeVolumesGet**](docs/MarketDataApi.md#publicGetTradeVolumesGet) | **GET** /public/get_trade_volumes | Retrieves aggregated 24h trade volumes for different instrument types and currencies.
*MarketDataApi* | [**publicGetTradingviewChartDataGet**](docs/MarketDataApi.md#publicGetTradingviewChartDataGet) | **GET** /public/get_tradingview_chart_data | Publicly available market data used to generate a TradingView candle chart.
*MarketDataApi* | [**publicTickerGet**](docs/MarketDataApi.md#publicTickerGet) | **GET** /public/ticker | Get ticker for an instrument.
*PrivateApi* | [**privateAddToAddressBookGet**](docs/PrivateApi.md#privateAddToAddressBookGet) | **GET** /private/add_to_address_book | Adds new entry to address book of given type
*PrivateApi* | [**privateBuyGet**](docs/PrivateApi.md#privateBuyGet) | **GET** /private/buy | Places a buy order for an instrument.
*PrivateApi* | [**privateCancelAllByCurrencyGet**](docs/PrivateApi.md#privateCancelAllByCurrencyGet) | **GET** /private/cancel_all_by_currency | Cancels all orders by currency, optionally filtered by instrument kind and/or order type.
*PrivateApi* | [**privateCancelAllByInstrumentGet**](docs/PrivateApi.md#privateCancelAllByInstrumentGet) | **GET** /private/cancel_all_by_instrument | Cancels all orders by instrument, optionally filtered by order type.
*PrivateApi* | [**privateCancelAllGet**](docs/PrivateApi.md#privateCancelAllGet) | **GET** /private/cancel_all | This method cancels all users orders and stop orders within all currencies and instrument kinds.
*PrivateApi* | [**privateCancelGet**](docs/PrivateApi.md#privateCancelGet) | **GET** /private/cancel | Cancel an order, specified by order id
*PrivateApi* | [**privateCancelTransferByIdGet**](docs/PrivateApi.md#privateCancelTransferByIdGet) | **GET** /private/cancel_transfer_by_id | Cancel transfer
*PrivateApi* | [**privateCancelWithdrawalGet**](docs/PrivateApi.md#privateCancelWithdrawalGet) | **GET** /private/cancel_withdrawal | Cancels withdrawal request
*PrivateApi* | [**privateChangeSubaccountNameGet**](docs/PrivateApi.md#privateChangeSubaccountNameGet) | **GET** /private/change_subaccount_name | Change the user name for a subaccount
*PrivateApi* | [**privateClosePositionGet**](docs/PrivateApi.md#privateClosePositionGet) | **GET** /private/close_position | Makes closing position reduce only order .
*PrivateApi* | [**privateCreateDepositAddressGet**](docs/PrivateApi.md#privateCreateDepositAddressGet) | **GET** /private/create_deposit_address | Creates deposit address in currency
*PrivateApi* | [**privateCreateSubaccountGet**](docs/PrivateApi.md#privateCreateSubaccountGet) | **GET** /private/create_subaccount | Create a new subaccount
*PrivateApi* | [**privateDisableTfaForSubaccountGet**](docs/PrivateApi.md#privateDisableTfaForSubaccountGet) | **GET** /private/disable_tfa_for_subaccount | Disable two factor authentication for a subaccount.
*PrivateApi* | [**privateDisableTfaWithRecoveryCodeGet**](docs/PrivateApi.md#privateDisableTfaWithRecoveryCodeGet) | **GET** /private/disable_tfa_with_recovery_code | Disables TFA with one time recovery code
*PrivateApi* | [**privateEditGet**](docs/PrivateApi.md#privateEditGet) | **GET** /private/edit | Change price, amount and/or other properties of an order.
*PrivateApi* | [**privateGetAccountSummaryGet**](docs/PrivateApi.md#privateGetAccountSummaryGet) | **GET** /private/get_account_summary | Retrieves user account summary.
*PrivateApi* | [**privateGetAddressBookGet**](docs/PrivateApi.md#privateGetAddressBookGet) | **GET** /private/get_address_book | Retrieves address book of given type
*PrivateApi* | [**privateGetCurrentDepositAddressGet**](docs/PrivateApi.md#privateGetCurrentDepositAddressGet) | **GET** /private/get_current_deposit_address | Retrieve deposit address for currency
*PrivateApi* | [**privateGetDepositsGet**](docs/PrivateApi.md#privateGetDepositsGet) | **GET** /private/get_deposits | Retrieve the latest users deposits
*PrivateApi* | [**privateGetEmailLanguageGet**](docs/PrivateApi.md#privateGetEmailLanguageGet) | **GET** /private/get_email_language | Retrieves the language to be used for emails.
*PrivateApi* | [**privateGetMarginsGet**](docs/PrivateApi.md#privateGetMarginsGet) | **GET** /private/get_margins | Get margins for given instrument, amount and price.
*PrivateApi* | [**privateGetNewAnnouncementsGet**](docs/PrivateApi.md#privateGetNewAnnouncementsGet) | **GET** /private/get_new_announcements | Retrieves announcements that have not been marked read by the user.
*PrivateApi* | [**privateGetOpenOrdersByCurrencyGet**](docs/PrivateApi.md#privateGetOpenOrdersByCurrencyGet) | **GET** /private/get_open_orders_by_currency | Retrieves list of user&#39;s open orders.
*PrivateApi* | [**privateGetOpenOrdersByInstrumentGet**](docs/PrivateApi.md#privateGetOpenOrdersByInstrumentGet) | **GET** /private/get_open_orders_by_instrument | Retrieves list of user&#39;s open orders within given Instrument.
*PrivateApi* | [**privateGetOrderHistoryByCurrencyGet**](docs/PrivateApi.md#privateGetOrderHistoryByCurrencyGet) | **GET** /private/get_order_history_by_currency | Retrieves history of orders that have been partially or fully filled.
*PrivateApi* | [**privateGetOrderHistoryByInstrumentGet**](docs/PrivateApi.md#privateGetOrderHistoryByInstrumentGet) | **GET** /private/get_order_history_by_instrument | Retrieves history of orders that have been partially or fully filled.
*PrivateApi* | [**privateGetOrderMarginByIdsGet**](docs/PrivateApi.md#privateGetOrderMarginByIdsGet) | **GET** /private/get_order_margin_by_ids | Retrieves initial margins of given orders
*PrivateApi* | [**privateGetOrderStateGet**](docs/PrivateApi.md#privateGetOrderStateGet) | **GET** /private/get_order_state | Retrieve the current state of an order.
*PrivateApi* | [**privateGetPositionGet**](docs/PrivateApi.md#privateGetPositionGet) | **GET** /private/get_position | Retrieve user position.
*PrivateApi* | [**privateGetPositionsGet**](docs/PrivateApi.md#privateGetPositionsGet) | **GET** /private/get_positions | Retrieve user positions.
*PrivateApi* | [**privateGetSettlementHistoryByCurrencyGet**](docs/PrivateApi.md#privateGetSettlementHistoryByCurrencyGet) | **GET** /private/get_settlement_history_by_currency | Retrieves settlement, delivery and bankruptcy events that have affected your account.
*PrivateApi* | [**privateGetSettlementHistoryByInstrumentGet**](docs/PrivateApi.md#privateGetSettlementHistoryByInstrumentGet) | **GET** /private/get_settlement_history_by_instrument | Retrieves public settlement, delivery and bankruptcy events filtered by instrument name
*PrivateApi* | [**privateGetSubaccountsGet**](docs/PrivateApi.md#privateGetSubaccountsGet) | **GET** /private/get_subaccounts | Get information about subaccounts
*PrivateApi* | [**privateGetTransfersGet**](docs/PrivateApi.md#privateGetTransfersGet) | **GET** /private/get_transfers | Adds new entry to address book of given type
*PrivateApi* | [**privateGetUserTradesByCurrencyAndTimeGet**](docs/PrivateApi.md#privateGetUserTradesByCurrencyAndTimeGet) | **GET** /private/get_user_trades_by_currency_and_time | Retrieve the latest user trades that have occurred for instruments in a specific currency symbol and within given time range.
*PrivateApi* | [**privateGetUserTradesByCurrencyGet**](docs/PrivateApi.md#privateGetUserTradesByCurrencyGet) | **GET** /private/get_user_trades_by_currency | Retrieve the latest user trades that have occurred for instruments in a specific currency symbol.
*PrivateApi* | [**privateGetUserTradesByInstrumentAndTimeGet**](docs/PrivateApi.md#privateGetUserTradesByInstrumentAndTimeGet) | **GET** /private/get_user_trades_by_instrument_and_time | Retrieve the latest user trades that have occurred for a specific instrument and within given time range.
*PrivateApi* | [**privateGetUserTradesByInstrumentGet**](docs/PrivateApi.md#privateGetUserTradesByInstrumentGet) | **GET** /private/get_user_trades_by_instrument | Retrieve the latest user trades that have occurred for a specific instrument.
*PrivateApi* | [**privateGetUserTradesByOrderGet**](docs/PrivateApi.md#privateGetUserTradesByOrderGet) | **GET** /private/get_user_trades_by_order | Retrieve the list of user trades that was created for given order
*PrivateApi* | [**privateGetWithdrawalsGet**](docs/PrivateApi.md#privateGetWithdrawalsGet) | **GET** /private/get_withdrawals | Retrieve the latest users withdrawals
*PrivateApi* | [**privateRemoveFromAddressBookGet**](docs/PrivateApi.md#privateRemoveFromAddressBookGet) | **GET** /private/remove_from_address_book | Adds new entry to address book of given type
*PrivateApi* | [**privateSellGet**](docs/PrivateApi.md#privateSellGet) | **GET** /private/sell | Places a sell order for an instrument.
*PrivateApi* | [**privateSetAnnouncementAsReadGet**](docs/PrivateApi.md#privateSetAnnouncementAsReadGet) | **GET** /private/set_announcement_as_read | Marks an announcement as read, so it will not be shown in &#x60;get_new_announcements&#x60;.
*PrivateApi* | [**privateSetEmailForSubaccountGet**](docs/PrivateApi.md#privateSetEmailForSubaccountGet) | **GET** /private/set_email_for_subaccount | Assign an email address to a subaccount. User will receive an email with confirmation link.
*PrivateApi* | [**privateSetEmailLanguageGet**](docs/PrivateApi.md#privateSetEmailLanguageGet) | **GET** /private/set_email_language | Changes the language to be used for emails.
*PrivateApi* | [**privateSetPasswordForSubaccountGet**](docs/PrivateApi.md#privateSetPasswordForSubaccountGet) | **GET** /private/set_password_for_subaccount | Set the password for the subaccount
*PrivateApi* | [**privateSubmitTransferToSubaccountGet**](docs/PrivateApi.md#privateSubmitTransferToSubaccountGet) | **GET** /private/submit_transfer_to_subaccount | Transfer funds to a subaccount.
*PrivateApi* | [**privateSubmitTransferToUserGet**](docs/PrivateApi.md#privateSubmitTransferToUserGet) | **GET** /private/submit_transfer_to_user | Transfer funds to a another user.
*PrivateApi* | [**privateToggleDepositAddressCreationGet**](docs/PrivateApi.md#privateToggleDepositAddressCreationGet) | **GET** /private/toggle_deposit_address_creation | Enable or disable deposit address creation
*PrivateApi* | [**privateToggleNotificationsFromSubaccountGet**](docs/PrivateApi.md#privateToggleNotificationsFromSubaccountGet) | **GET** /private/toggle_notifications_from_subaccount | Enable or disable sending of notifications for the subaccount.
*PrivateApi* | [**privateToggleSubaccountLoginGet**](docs/PrivateApi.md#privateToggleSubaccountLoginGet) | **GET** /private/toggle_subaccount_login | Enable or disable login for a subaccount. If login is disabled and a session for the subaccount exists, this session will be terminated.
*PrivateApi* | [**privateWithdrawGet**](docs/PrivateApi.md#privateWithdrawGet) | **GET** /private/withdraw | Creates a new withdrawal request
*PublicApi* | [**publicAuthGet**](docs/PublicApi.md#publicAuthGet) | **GET** /public/auth | Authenticate
*PublicApi* | [**publicGetAnnouncementsGet**](docs/PublicApi.md#publicGetAnnouncementsGet) | **GET** /public/get_announcements | Retrieves announcements from the last 30 days.
*PublicApi* | [**publicGetBookSummaryByCurrencyGet**](docs/PublicApi.md#publicGetBookSummaryByCurrencyGet) | **GET** /public/get_book_summary_by_currency | Retrieves the summary information such as open interest, 24h volume, etc. for all instruments for the currency (optionally filtered by kind).
*PublicApi* | [**publicGetBookSummaryByInstrumentGet**](docs/PublicApi.md#publicGetBookSummaryByInstrumentGet) | **GET** /public/get_book_summary_by_instrument | Retrieves the summary information such as open interest, 24h volume, etc. for a specific instrument.
*PublicApi* | [**publicGetContractSizeGet**](docs/PublicApi.md#publicGetContractSizeGet) | **GET** /public/get_contract_size | Retrieves contract size of provided instrument.
*PublicApi* | [**publicGetCurrenciesGet**](docs/PublicApi.md#publicGetCurrenciesGet) | **GET** /public/get_currencies | Retrieves all cryptocurrencies supported by the API.
*PublicApi* | [**publicGetFundingChartDataGet**](docs/PublicApi.md#publicGetFundingChartDataGet) | **GET** /public/get_funding_chart_data | Retrieve the latest user trades that have occurred for PERPETUAL instruments in a specific currency symbol and within given time range.
*PublicApi* | [**publicGetHistoricalVolatilityGet**](docs/PublicApi.md#publicGetHistoricalVolatilityGet) | **GET** /public/get_historical_volatility | Provides information about historical volatility for given cryptocurrency.
*PublicApi* | [**publicGetIndexGet**](docs/PublicApi.md#publicGetIndexGet) | **GET** /public/get_index | Retrieves the current index price for the instruments, for the selected currency.
*PublicApi* | [**publicGetInstrumentsGet**](docs/PublicApi.md#publicGetInstrumentsGet) | **GET** /public/get_instruments | Retrieves available trading instruments. This method can be used to see which instruments are available for trading, or which instruments have existed historically.
*PublicApi* | [**publicGetLastSettlementsByCurrencyGet**](docs/PublicApi.md#publicGetLastSettlementsByCurrencyGet) | **GET** /public/get_last_settlements_by_currency | Retrieves historical settlement, delivery and bankruptcy events coming from all instruments within given currency.
*PublicApi* | [**publicGetLastSettlementsByInstrumentGet**](docs/PublicApi.md#publicGetLastSettlementsByInstrumentGet) | **GET** /public/get_last_settlements_by_instrument | Retrieves historical public settlement, delivery and bankruptcy events filtered by instrument name.
*PublicApi* | [**publicGetLastTradesByCurrencyAndTimeGet**](docs/PublicApi.md#publicGetLastTradesByCurrencyAndTimeGet) | **GET** /public/get_last_trades_by_currency_and_time | Retrieve the latest trades that have occurred for instruments in a specific currency symbol and within given time range.
*PublicApi* | [**publicGetLastTradesByCurrencyGet**](docs/PublicApi.md#publicGetLastTradesByCurrencyGet) | **GET** /public/get_last_trades_by_currency | Retrieve the latest trades that have occurred for instruments in a specific currency symbol.
*PublicApi* | [**publicGetLastTradesByInstrumentAndTimeGet**](docs/PublicApi.md#publicGetLastTradesByInstrumentAndTimeGet) | **GET** /public/get_last_trades_by_instrument_and_time | Retrieve the latest trades that have occurred for a specific instrument and within given time range.
*PublicApi* | [**publicGetLastTradesByInstrumentGet**](docs/PublicApi.md#publicGetLastTradesByInstrumentGet) | **GET** /public/get_last_trades_by_instrument | Retrieve the latest trades that have occurred for a specific instrument.
*PublicApi* | [**publicGetOrderBookGet**](docs/PublicApi.md#publicGetOrderBookGet) | **GET** /public/get_order_book | Retrieves the order book, along with other market values for a given instrument.
*PublicApi* | [**publicGetTimeGet**](docs/PublicApi.md#publicGetTimeGet) | **GET** /public/get_time | Retrieves the current time (in milliseconds). This API endpoint can be used to check the clock skew between your software and Deribit&#39;s systems.
*PublicApi* | [**publicGetTradeVolumesGet**](docs/PublicApi.md#publicGetTradeVolumesGet) | **GET** /public/get_trade_volumes | Retrieves aggregated 24h trade volumes for different instrument types and currencies.
*PublicApi* | [**publicGetTradingviewChartDataGet**](docs/PublicApi.md#publicGetTradingviewChartDataGet) | **GET** /public/get_tradingview_chart_data | Publicly available market data used to generate a TradingView candle chart.
*PublicApi* | [**publicTestGet**](docs/PublicApi.md#publicTestGet) | **GET** /public/test | Tests the connection to the API server, and returns its version. You can use this to make sure the API is reachable, and matches the expected version.
*PublicApi* | [**publicTickerGet**](docs/PublicApi.md#publicTickerGet) | **GET** /public/ticker | Get ticker for an instrument.
*PublicApi* | [**publicValidateFieldGet**](docs/PublicApi.md#publicValidateFieldGet) | **GET** /public/validate_field | Method used to introduce the client software connected to Deribit platform over websocket. Provided data may have an impact on the maintained connection and will be collected for internal statistical purposes. In response, Deribit will also introduce itself.
*SupportingApi* | [**publicGetTimeGet**](docs/SupportingApi.md#publicGetTimeGet) | **GET** /public/get_time | Retrieves the current time (in milliseconds). This API endpoint can be used to check the clock skew between your software and Deribit&#39;s systems.
*SupportingApi* | [**publicTestGet**](docs/SupportingApi.md#publicTestGet) | **GET** /public/test | Tests the connection to the API server, and returns its version. You can use this to make sure the API is reachable, and matches the expected version.
*TradingApi* | [**privateBuyGet**](docs/TradingApi.md#privateBuyGet) | **GET** /private/buy | Places a buy order for an instrument.
*TradingApi* | [**privateCancelAllByCurrencyGet**](docs/TradingApi.md#privateCancelAllByCurrencyGet) | **GET** /private/cancel_all_by_currency | Cancels all orders by currency, optionally filtered by instrument kind and/or order type.
*TradingApi* | [**privateCancelAllByInstrumentGet**](docs/TradingApi.md#privateCancelAllByInstrumentGet) | **GET** /private/cancel_all_by_instrument | Cancels all orders by instrument, optionally filtered by order type.
*TradingApi* | [**privateCancelAllGet**](docs/TradingApi.md#privateCancelAllGet) | **GET** /private/cancel_all | This method cancels all users orders and stop orders within all currencies and instrument kinds.
*TradingApi* | [**privateCancelGet**](docs/TradingApi.md#privateCancelGet) | **GET** /private/cancel | Cancel an order, specified by order id
*TradingApi* | [**privateClosePositionGet**](docs/TradingApi.md#privateClosePositionGet) | **GET** /private/close_position | Makes closing position reduce only order .
*TradingApi* | [**privateEditGet**](docs/TradingApi.md#privateEditGet) | **GET** /private/edit | Change price, amount and/or other properties of an order.
*TradingApi* | [**privateGetMarginsGet**](docs/TradingApi.md#privateGetMarginsGet) | **GET** /private/get_margins | Get margins for given instrument, amount and price.
*TradingApi* | [**privateGetOpenOrdersByCurrencyGet**](docs/TradingApi.md#privateGetOpenOrdersByCurrencyGet) | **GET** /private/get_open_orders_by_currency | Retrieves list of user&#39;s open orders.
*TradingApi* | [**privateGetOpenOrdersByInstrumentGet**](docs/TradingApi.md#privateGetOpenOrdersByInstrumentGet) | **GET** /private/get_open_orders_by_instrument | Retrieves list of user&#39;s open orders within given Instrument.
*TradingApi* | [**privateGetOrderHistoryByCurrencyGet**](docs/TradingApi.md#privateGetOrderHistoryByCurrencyGet) | **GET** /private/get_order_history_by_currency | Retrieves history of orders that have been partially or fully filled.
*TradingApi* | [**privateGetOrderHistoryByInstrumentGet**](docs/TradingApi.md#privateGetOrderHistoryByInstrumentGet) | **GET** /private/get_order_history_by_instrument | Retrieves history of orders that have been partially or fully filled.
*TradingApi* | [**privateGetOrderMarginByIdsGet**](docs/TradingApi.md#privateGetOrderMarginByIdsGet) | **GET** /private/get_order_margin_by_ids | Retrieves initial margins of given orders
*TradingApi* | [**privateGetOrderStateGet**](docs/TradingApi.md#privateGetOrderStateGet) | **GET** /private/get_order_state | Retrieve the current state of an order.
*TradingApi* | [**privateGetSettlementHistoryByCurrencyGet**](docs/TradingApi.md#privateGetSettlementHistoryByCurrencyGet) | **GET** /private/get_settlement_history_by_currency | Retrieves settlement, delivery and bankruptcy events that have affected your account.
*TradingApi* | [**privateGetSettlementHistoryByInstrumentGet**](docs/TradingApi.md#privateGetSettlementHistoryByInstrumentGet) | **GET** /private/get_settlement_history_by_instrument | Retrieves public settlement, delivery and bankruptcy events filtered by instrument name
*TradingApi* | [**privateGetUserTradesByCurrencyAndTimeGet**](docs/TradingApi.md#privateGetUserTradesByCurrencyAndTimeGet) | **GET** /private/get_user_trades_by_currency_and_time | Retrieve the latest user trades that have occurred for instruments in a specific currency symbol and within given time range.
*TradingApi* | [**privateGetUserTradesByCurrencyGet**](docs/TradingApi.md#privateGetUserTradesByCurrencyGet) | **GET** /private/get_user_trades_by_currency | Retrieve the latest user trades that have occurred for instruments in a specific currency symbol.
*TradingApi* | [**privateGetUserTradesByInstrumentAndTimeGet**](docs/TradingApi.md#privateGetUserTradesByInstrumentAndTimeGet) | **GET** /private/get_user_trades_by_instrument_and_time | Retrieve the latest user trades that have occurred for a specific instrument and within given time range.
*TradingApi* | [**privateGetUserTradesByInstrumentGet**](docs/TradingApi.md#privateGetUserTradesByInstrumentGet) | **GET** /private/get_user_trades_by_instrument | Retrieve the latest user trades that have occurred for a specific instrument.
*TradingApi* | [**privateGetUserTradesByOrderGet**](docs/TradingApi.md#privateGetUserTradesByOrderGet) | **GET** /private/get_user_trades_by_order | Retrieve the list of user trades that was created for given order
*TradingApi* | [**privateSellGet**](docs/TradingApi.md#privateSellGet) | **GET** /private/sell | Places a sell order for an instrument.
*WalletApi* | [**privateAddToAddressBookGet**](docs/WalletApi.md#privateAddToAddressBookGet) | **GET** /private/add_to_address_book | Adds new entry to address book of given type
*WalletApi* | [**privateCancelTransferByIdGet**](docs/WalletApi.md#privateCancelTransferByIdGet) | **GET** /private/cancel_transfer_by_id | Cancel transfer
*WalletApi* | [**privateCancelWithdrawalGet**](docs/WalletApi.md#privateCancelWithdrawalGet) | **GET** /private/cancel_withdrawal | Cancels withdrawal request
*WalletApi* | [**privateCreateDepositAddressGet**](docs/WalletApi.md#privateCreateDepositAddressGet) | **GET** /private/create_deposit_address | Creates deposit address in currency
*WalletApi* | [**privateGetAddressBookGet**](docs/WalletApi.md#privateGetAddressBookGet) | **GET** /private/get_address_book | Retrieves address book of given type
*WalletApi* | [**privateGetCurrentDepositAddressGet**](docs/WalletApi.md#privateGetCurrentDepositAddressGet) | **GET** /private/get_current_deposit_address | Retrieve deposit address for currency
*WalletApi* | [**privateGetDepositsGet**](docs/WalletApi.md#privateGetDepositsGet) | **GET** /private/get_deposits | Retrieve the latest users deposits
*WalletApi* | [**privateGetTransfersGet**](docs/WalletApi.md#privateGetTransfersGet) | **GET** /private/get_transfers | Adds new entry to address book of given type
*WalletApi* | [**privateGetWithdrawalsGet**](docs/WalletApi.md#privateGetWithdrawalsGet) | **GET** /private/get_withdrawals | Retrieve the latest users withdrawals
*WalletApi* | [**privateRemoveFromAddressBookGet**](docs/WalletApi.md#privateRemoveFromAddressBookGet) | **GET** /private/remove_from_address_book | Adds new entry to address book of given type
*WalletApi* | [**privateSubmitTransferToSubaccountGet**](docs/WalletApi.md#privateSubmitTransferToSubaccountGet) | **GET** /private/submit_transfer_to_subaccount | Transfer funds to a subaccount.
*WalletApi* | [**privateSubmitTransferToUserGet**](docs/WalletApi.md#privateSubmitTransferToUserGet) | **GET** /private/submit_transfer_to_user | Transfer funds to a another user.
*WalletApi* | [**privateToggleDepositAddressCreationGet**](docs/WalletApi.md#privateToggleDepositAddressCreationGet) | **GET** /private/toggle_deposit_address_creation | Enable or disable deposit address creation
*WalletApi* | [**privateWithdrawGet**](docs/WalletApi.md#privateWithdrawGet) | **GET** /private/withdraw | Creates a new withdrawal request


## Documentation for Models

 - [AddressBookItem](docs/AddressBookItem.md)
 - [BookSummary](docs/BookSummary.md)
 - [Currency](docs/Currency.md)
 - [CurrencyPortfolio](docs/CurrencyPortfolio.md)
 - [CurrencyWithdrawalPriorities](docs/CurrencyWithdrawalPriorities.md)
 - [Deposit](docs/Deposit.md)
 - [Instrument](docs/Instrument.md)
 - [KeyNumberPair](docs/KeyNumberPair.md)
 - [Order](docs/Order.md)
 - [OrderIdInitialMarginPair](docs/OrderIdInitialMarginPair.md)
 - [Portfolio](docs/Portfolio.md)
 - [PortfolioEth](docs/PortfolioEth.md)
 - [Position](docs/Position.md)
 - [PublicTrade](docs/PublicTrade.md)
 - [Settlement](docs/Settlement.md)
 - [TradesVolumes](docs/TradesVolumes.md)
 - [TransferItem](docs/TransferItem.md)
 - [Types](docs/Types.md)
 - [UserTrade](docs/UserTrade.md)
 - [Withdrawal](docs/Withdrawal.md)


## Documentation for Authorization

Authentication schemes defined for the API:
### bearerAuth

- **Type**: HTTP basic authentication


## Recommendation

It's recommended to create an instance of `ApiClient` per thread in a multithreaded environment to avoid any potential issues.

## Author



