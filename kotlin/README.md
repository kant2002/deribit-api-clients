# org.openapitools.client - Kotlin client library for Deribit API

## Requires

* Kotlin 1.3.31
* Gradle 4.9

## Build

First, create the gradle wrapper script:

```
gradle wrapper
```

Then, run:

```
./gradlew check assemble
```

This runs all tests and packages the library.

## Features/Implementation Notes

* Supports JSON inputs/outputs, File inputs, and Form inputs.
* Supports collection formats for query parameters: csv, tsv, ssv, pipes.
* Some Kotlin and Java types are fully qualified to avoid conflicts with types defined in OpenAPI definitions.
* Implementation of ApiClient is intended to reduce method counts, specifically to benefit Android targets.

<a name="documentation-for-api-endpoints"></a>
## Documentation for API Endpoints

All URIs are relative to *https://www.deribit.com/api/v2*

Class | Method | HTTP request | Description
------------ | ------------- | ------------- | -------------
*AccountManagementApi* | [**privateChangeSubaccountNameGet**](docs/AccountManagementApi.md#privatechangesubaccountnameget) | **GET** /private/change_subaccount_name | Change the user name for a subaccount
*AccountManagementApi* | [**privateCreateSubaccountGet**](docs/AccountManagementApi.md#privatecreatesubaccountget) | **GET** /private/create_subaccount | Create a new subaccount
*AccountManagementApi* | [**privateDisableTfaForSubaccountGet**](docs/AccountManagementApi.md#privatedisabletfaforsubaccountget) | **GET** /private/disable_tfa_for_subaccount | Disable two factor authentication for a subaccount.
*AccountManagementApi* | [**privateGetAccountSummaryGet**](docs/AccountManagementApi.md#privategetaccountsummaryget) | **GET** /private/get_account_summary | Retrieves user account summary.
*AccountManagementApi* | [**privateGetEmailLanguageGet**](docs/AccountManagementApi.md#privategetemaillanguageget) | **GET** /private/get_email_language | Retrieves the language to be used for emails.
*AccountManagementApi* | [**privateGetNewAnnouncementsGet**](docs/AccountManagementApi.md#privategetnewannouncementsget) | **GET** /private/get_new_announcements | Retrieves announcements that have not been marked read by the user.
*AccountManagementApi* | [**privateGetPositionGet**](docs/AccountManagementApi.md#privategetpositionget) | **GET** /private/get_position | Retrieve user position.
*AccountManagementApi* | [**privateGetPositionsGet**](docs/AccountManagementApi.md#privategetpositionsget) | **GET** /private/get_positions | Retrieve user positions.
*AccountManagementApi* | [**privateGetSubaccountsGet**](docs/AccountManagementApi.md#privategetsubaccountsget) | **GET** /private/get_subaccounts | Get information about subaccounts
*AccountManagementApi* | [**privateSetAnnouncementAsReadGet**](docs/AccountManagementApi.md#privatesetannouncementasreadget) | **GET** /private/set_announcement_as_read | Marks an announcement as read, so it will not be shown in `get_new_announcements`.
*AccountManagementApi* | [**privateSetEmailForSubaccountGet**](docs/AccountManagementApi.md#privatesetemailforsubaccountget) | **GET** /private/set_email_for_subaccount | Assign an email address to a subaccount. User will receive an email with confirmation link.
*AccountManagementApi* | [**privateSetEmailLanguageGet**](docs/AccountManagementApi.md#privatesetemaillanguageget) | **GET** /private/set_email_language | Changes the language to be used for emails.
*AccountManagementApi* | [**privateSetPasswordForSubaccountGet**](docs/AccountManagementApi.md#privatesetpasswordforsubaccountget) | **GET** /private/set_password_for_subaccount | Set the password for the subaccount
*AccountManagementApi* | [**privateToggleNotificationsFromSubaccountGet**](docs/AccountManagementApi.md#privatetogglenotificationsfromsubaccountget) | **GET** /private/toggle_notifications_from_subaccount | Enable or disable sending of notifications for the subaccount.
*AccountManagementApi* | [**privateToggleSubaccountLoginGet**](docs/AccountManagementApi.md#privatetogglesubaccountloginget) | **GET** /private/toggle_subaccount_login | Enable or disable login for a subaccount. If login is disabled and a session for the subaccount exists, this session will be terminated.
*AccountManagementApi* | [**publicGetAnnouncementsGet**](docs/AccountManagementApi.md#publicgetannouncementsget) | **GET** /public/get_announcements | Retrieves announcements from the last 30 days.
*AuthenticationApi* | [**publicAuthGet**](docs/AuthenticationApi.md#publicauthget) | **GET** /public/auth | Authenticate
*InternalApi* | [**privateAddToAddressBookGet**](docs/InternalApi.md#privateaddtoaddressbookget) | **GET** /private/add_to_address_book | Adds new entry to address book of given type
*InternalApi* | [**privateDisableTfaWithRecoveryCodeGet**](docs/InternalApi.md#privatedisabletfawithrecoverycodeget) | **GET** /private/disable_tfa_with_recovery_code | Disables TFA with one time recovery code
*InternalApi* | [**privateGetAddressBookGet**](docs/InternalApi.md#privategetaddressbookget) | **GET** /private/get_address_book | Retrieves address book of given type
*InternalApi* | [**privateRemoveFromAddressBookGet**](docs/InternalApi.md#privateremovefromaddressbookget) | **GET** /private/remove_from_address_book | Adds new entry to address book of given type
*InternalApi* | [**privateSubmitTransferToSubaccountGet**](docs/InternalApi.md#privatesubmittransfertosubaccountget) | **GET** /private/submit_transfer_to_subaccount | Transfer funds to a subaccount.
*InternalApi* | [**privateSubmitTransferToUserGet**](docs/InternalApi.md#privatesubmittransfertouserget) | **GET** /private/submit_transfer_to_user | Transfer funds to a another user.
*InternalApi* | [**privateToggleDepositAddressCreationGet**](docs/InternalApi.md#privatetoggledepositaddresscreationget) | **GET** /private/toggle_deposit_address_creation | Enable or disable deposit address creation
*InternalApi* | [**publicGetFooterGet**](docs/InternalApi.md#publicgetfooterget) | **GET** /public/get_footer | Get information to be displayed in the footer of the website.
*InternalApi* | [**publicGetOptionMarkPricesGet**](docs/InternalApi.md#publicgetoptionmarkpricesget) | **GET** /public/get_option_mark_prices | Retrives market prices and its implied volatility of options instruments
*InternalApi* | [**publicValidateFieldGet**](docs/InternalApi.md#publicvalidatefieldget) | **GET** /public/validate_field | Method used to introduce the client software connected to Deribit platform over websocket. Provided data may have an impact on the maintained connection and will be collected for internal statistical purposes. In response, Deribit will also introduce itself.
*MarketDataApi* | [**publicGetBookSummaryByCurrencyGet**](docs/MarketDataApi.md#publicgetbooksummarybycurrencyget) | **GET** /public/get_book_summary_by_currency | Retrieves the summary information such as open interest, 24h volume, etc. for all instruments for the currency (optionally filtered by kind).
*MarketDataApi* | [**publicGetBookSummaryByInstrumentGet**](docs/MarketDataApi.md#publicgetbooksummarybyinstrumentget) | **GET** /public/get_book_summary_by_instrument | Retrieves the summary information such as open interest, 24h volume, etc. for a specific instrument.
*MarketDataApi* | [**publicGetContractSizeGet**](docs/MarketDataApi.md#publicgetcontractsizeget) | **GET** /public/get_contract_size | Retrieves contract size of provided instrument.
*MarketDataApi* | [**publicGetCurrenciesGet**](docs/MarketDataApi.md#publicgetcurrenciesget) | **GET** /public/get_currencies | Retrieves all cryptocurrencies supported by the API.
*MarketDataApi* | [**publicGetFundingChartDataGet**](docs/MarketDataApi.md#publicgetfundingchartdataget) | **GET** /public/get_funding_chart_data | Retrieve the latest user trades that have occurred for PERPETUAL instruments in a specific currency symbol and within given time range.
*MarketDataApi* | [**publicGetHistoricalVolatilityGet**](docs/MarketDataApi.md#publicgethistoricalvolatilityget) | **GET** /public/get_historical_volatility | Provides information about historical volatility for given cryptocurrency.
*MarketDataApi* | [**publicGetIndexGet**](docs/MarketDataApi.md#publicgetindexget) | **GET** /public/get_index | Retrieves the current index price for the instruments, for the selected currency.
*MarketDataApi* | [**publicGetInstrumentsGet**](docs/MarketDataApi.md#publicgetinstrumentsget) | **GET** /public/get_instruments | Retrieves available trading instruments. This method can be used to see which instruments are available for trading, or which instruments have existed historically.
*MarketDataApi* | [**publicGetLastSettlementsByCurrencyGet**](docs/MarketDataApi.md#publicgetlastsettlementsbycurrencyget) | **GET** /public/get_last_settlements_by_currency | Retrieves historical settlement, delivery and bankruptcy events coming from all instruments within given currency.
*MarketDataApi* | [**publicGetLastSettlementsByInstrumentGet**](docs/MarketDataApi.md#publicgetlastsettlementsbyinstrumentget) | **GET** /public/get_last_settlements_by_instrument | Retrieves historical public settlement, delivery and bankruptcy events filtered by instrument name.
*MarketDataApi* | [**publicGetLastTradesByCurrencyAndTimeGet**](docs/MarketDataApi.md#publicgetlasttradesbycurrencyandtimeget) | **GET** /public/get_last_trades_by_currency_and_time | Retrieve the latest trades that have occurred for instruments in a specific currency symbol and within given time range.
*MarketDataApi* | [**publicGetLastTradesByCurrencyGet**](docs/MarketDataApi.md#publicgetlasttradesbycurrencyget) | **GET** /public/get_last_trades_by_currency | Retrieve the latest trades that have occurred for instruments in a specific currency symbol.
*MarketDataApi* | [**publicGetLastTradesByInstrumentAndTimeGet**](docs/MarketDataApi.md#publicgetlasttradesbyinstrumentandtimeget) | **GET** /public/get_last_trades_by_instrument_and_time | Retrieve the latest trades that have occurred for a specific instrument and within given time range.
*MarketDataApi* | [**publicGetLastTradesByInstrumentGet**](docs/MarketDataApi.md#publicgetlasttradesbyinstrumentget) | **GET** /public/get_last_trades_by_instrument | Retrieve the latest trades that have occurred for a specific instrument.
*MarketDataApi* | [**publicGetOrderBookGet**](docs/MarketDataApi.md#publicgetorderbookget) | **GET** /public/get_order_book | Retrieves the order book, along with other market values for a given instrument.
*MarketDataApi* | [**publicGetTradeVolumesGet**](docs/MarketDataApi.md#publicgettradevolumesget) | **GET** /public/get_trade_volumes | Retrieves aggregated 24h trade volumes for different instrument types and currencies.
*MarketDataApi* | [**publicGetTradingviewChartDataGet**](docs/MarketDataApi.md#publicgettradingviewchartdataget) | **GET** /public/get_tradingview_chart_data | Publicly available market data used to generate a TradingView candle chart.
*MarketDataApi* | [**publicTickerGet**](docs/MarketDataApi.md#publictickerget) | **GET** /public/ticker | Get ticker for an instrument.
*PrivateApi* | [**privateAddToAddressBookGet**](docs/PrivateApi.md#privateaddtoaddressbookget) | **GET** /private/add_to_address_book | Adds new entry to address book of given type
*PrivateApi* | [**privateBuyGet**](docs/PrivateApi.md#privatebuyget) | **GET** /private/buy | Places a buy order for an instrument.
*PrivateApi* | [**privateCancelAllByCurrencyGet**](docs/PrivateApi.md#privatecancelallbycurrencyget) | **GET** /private/cancel_all_by_currency | Cancels all orders by currency, optionally filtered by instrument kind and/or order type.
*PrivateApi* | [**privateCancelAllByInstrumentGet**](docs/PrivateApi.md#privatecancelallbyinstrumentget) | **GET** /private/cancel_all_by_instrument | Cancels all orders by instrument, optionally filtered by order type.
*PrivateApi* | [**privateCancelAllGet**](docs/PrivateApi.md#privatecancelallget) | **GET** /private/cancel_all | This method cancels all users orders and stop orders within all currencies and instrument kinds.
*PrivateApi* | [**privateCancelGet**](docs/PrivateApi.md#privatecancelget) | **GET** /private/cancel | Cancel an order, specified by order id
*PrivateApi* | [**privateCancelTransferByIdGet**](docs/PrivateApi.md#privatecanceltransferbyidget) | **GET** /private/cancel_transfer_by_id | Cancel transfer
*PrivateApi* | [**privateCancelWithdrawalGet**](docs/PrivateApi.md#privatecancelwithdrawalget) | **GET** /private/cancel_withdrawal | Cancels withdrawal request
*PrivateApi* | [**privateChangeSubaccountNameGet**](docs/PrivateApi.md#privatechangesubaccountnameget) | **GET** /private/change_subaccount_name | Change the user name for a subaccount
*PrivateApi* | [**privateClosePositionGet**](docs/PrivateApi.md#privateclosepositionget) | **GET** /private/close_position | Makes closing position reduce only order .
*PrivateApi* | [**privateCreateDepositAddressGet**](docs/PrivateApi.md#privatecreatedepositaddressget) | **GET** /private/create_deposit_address | Creates deposit address in currency
*PrivateApi* | [**privateCreateSubaccountGet**](docs/PrivateApi.md#privatecreatesubaccountget) | **GET** /private/create_subaccount | Create a new subaccount
*PrivateApi* | [**privateDisableTfaForSubaccountGet**](docs/PrivateApi.md#privatedisabletfaforsubaccountget) | **GET** /private/disable_tfa_for_subaccount | Disable two factor authentication for a subaccount.
*PrivateApi* | [**privateDisableTfaWithRecoveryCodeGet**](docs/PrivateApi.md#privatedisabletfawithrecoverycodeget) | **GET** /private/disable_tfa_with_recovery_code | Disables TFA with one time recovery code
*PrivateApi* | [**privateEditGet**](docs/PrivateApi.md#privateeditget) | **GET** /private/edit | Change price, amount and/or other properties of an order.
*PrivateApi* | [**privateGetAccountSummaryGet**](docs/PrivateApi.md#privategetaccountsummaryget) | **GET** /private/get_account_summary | Retrieves user account summary.
*PrivateApi* | [**privateGetAddressBookGet**](docs/PrivateApi.md#privategetaddressbookget) | **GET** /private/get_address_book | Retrieves address book of given type
*PrivateApi* | [**privateGetCurrentDepositAddressGet**](docs/PrivateApi.md#privategetcurrentdepositaddressget) | **GET** /private/get_current_deposit_address | Retrieve deposit address for currency
*PrivateApi* | [**privateGetDepositsGet**](docs/PrivateApi.md#privategetdepositsget) | **GET** /private/get_deposits | Retrieve the latest users deposits
*PrivateApi* | [**privateGetEmailLanguageGet**](docs/PrivateApi.md#privategetemaillanguageget) | **GET** /private/get_email_language | Retrieves the language to be used for emails.
*PrivateApi* | [**privateGetMarginsGet**](docs/PrivateApi.md#privategetmarginsget) | **GET** /private/get_margins | Get margins for given instrument, amount and price.
*PrivateApi* | [**privateGetNewAnnouncementsGet**](docs/PrivateApi.md#privategetnewannouncementsget) | **GET** /private/get_new_announcements | Retrieves announcements that have not been marked read by the user.
*PrivateApi* | [**privateGetOpenOrdersByCurrencyGet**](docs/PrivateApi.md#privategetopenordersbycurrencyget) | **GET** /private/get_open_orders_by_currency | Retrieves list of user's open orders.
*PrivateApi* | [**privateGetOpenOrdersByInstrumentGet**](docs/PrivateApi.md#privategetopenordersbyinstrumentget) | **GET** /private/get_open_orders_by_instrument | Retrieves list of user's open orders within given Instrument.
*PrivateApi* | [**privateGetOrderHistoryByCurrencyGet**](docs/PrivateApi.md#privategetorderhistorybycurrencyget) | **GET** /private/get_order_history_by_currency | Retrieves history of orders that have been partially or fully filled.
*PrivateApi* | [**privateGetOrderHistoryByInstrumentGet**](docs/PrivateApi.md#privategetorderhistorybyinstrumentget) | **GET** /private/get_order_history_by_instrument | Retrieves history of orders that have been partially or fully filled.
*PrivateApi* | [**privateGetOrderMarginByIdsGet**](docs/PrivateApi.md#privategetordermarginbyidsget) | **GET** /private/get_order_margin_by_ids | Retrieves initial margins of given orders
*PrivateApi* | [**privateGetOrderStateGet**](docs/PrivateApi.md#privategetorderstateget) | **GET** /private/get_order_state | Retrieve the current state of an order.
*PrivateApi* | [**privateGetPositionGet**](docs/PrivateApi.md#privategetpositionget) | **GET** /private/get_position | Retrieve user position.
*PrivateApi* | [**privateGetPositionsGet**](docs/PrivateApi.md#privategetpositionsget) | **GET** /private/get_positions | Retrieve user positions.
*PrivateApi* | [**privateGetSettlementHistoryByCurrencyGet**](docs/PrivateApi.md#privategetsettlementhistorybycurrencyget) | **GET** /private/get_settlement_history_by_currency | Retrieves settlement, delivery and bankruptcy events that have affected your account.
*PrivateApi* | [**privateGetSettlementHistoryByInstrumentGet**](docs/PrivateApi.md#privategetsettlementhistorybyinstrumentget) | **GET** /private/get_settlement_history_by_instrument | Retrieves public settlement, delivery and bankruptcy events filtered by instrument name
*PrivateApi* | [**privateGetSubaccountsGet**](docs/PrivateApi.md#privategetsubaccountsget) | **GET** /private/get_subaccounts | Get information about subaccounts
*PrivateApi* | [**privateGetTransfersGet**](docs/PrivateApi.md#privategettransfersget) | **GET** /private/get_transfers | Adds new entry to address book of given type
*PrivateApi* | [**privateGetUserTradesByCurrencyAndTimeGet**](docs/PrivateApi.md#privategetusertradesbycurrencyandtimeget) | **GET** /private/get_user_trades_by_currency_and_time | Retrieve the latest user trades that have occurred for instruments in a specific currency symbol and within given time range.
*PrivateApi* | [**privateGetUserTradesByCurrencyGet**](docs/PrivateApi.md#privategetusertradesbycurrencyget) | **GET** /private/get_user_trades_by_currency | Retrieve the latest user trades that have occurred for instruments in a specific currency symbol.
*PrivateApi* | [**privateGetUserTradesByInstrumentAndTimeGet**](docs/PrivateApi.md#privategetusertradesbyinstrumentandtimeget) | **GET** /private/get_user_trades_by_instrument_and_time | Retrieve the latest user trades that have occurred for a specific instrument and within given time range.
*PrivateApi* | [**privateGetUserTradesByInstrumentGet**](docs/PrivateApi.md#privategetusertradesbyinstrumentget) | **GET** /private/get_user_trades_by_instrument | Retrieve the latest user trades that have occurred for a specific instrument.
*PrivateApi* | [**privateGetUserTradesByOrderGet**](docs/PrivateApi.md#privategetusertradesbyorderget) | **GET** /private/get_user_trades_by_order | Retrieve the list of user trades that was created for given order
*PrivateApi* | [**privateGetWithdrawalsGet**](docs/PrivateApi.md#privategetwithdrawalsget) | **GET** /private/get_withdrawals | Retrieve the latest users withdrawals
*PrivateApi* | [**privateRemoveFromAddressBookGet**](docs/PrivateApi.md#privateremovefromaddressbookget) | **GET** /private/remove_from_address_book | Adds new entry to address book of given type
*PrivateApi* | [**privateSellGet**](docs/PrivateApi.md#privatesellget) | **GET** /private/sell | Places a sell order for an instrument.
*PrivateApi* | [**privateSetAnnouncementAsReadGet**](docs/PrivateApi.md#privatesetannouncementasreadget) | **GET** /private/set_announcement_as_read | Marks an announcement as read, so it will not be shown in `get_new_announcements`.
*PrivateApi* | [**privateSetEmailForSubaccountGet**](docs/PrivateApi.md#privatesetemailforsubaccountget) | **GET** /private/set_email_for_subaccount | Assign an email address to a subaccount. User will receive an email with confirmation link.
*PrivateApi* | [**privateSetEmailLanguageGet**](docs/PrivateApi.md#privatesetemaillanguageget) | **GET** /private/set_email_language | Changes the language to be used for emails.
*PrivateApi* | [**privateSetPasswordForSubaccountGet**](docs/PrivateApi.md#privatesetpasswordforsubaccountget) | **GET** /private/set_password_for_subaccount | Set the password for the subaccount
*PrivateApi* | [**privateSubmitTransferToSubaccountGet**](docs/PrivateApi.md#privatesubmittransfertosubaccountget) | **GET** /private/submit_transfer_to_subaccount | Transfer funds to a subaccount.
*PrivateApi* | [**privateSubmitTransferToUserGet**](docs/PrivateApi.md#privatesubmittransfertouserget) | **GET** /private/submit_transfer_to_user | Transfer funds to a another user.
*PrivateApi* | [**privateToggleDepositAddressCreationGet**](docs/PrivateApi.md#privatetoggledepositaddresscreationget) | **GET** /private/toggle_deposit_address_creation | Enable or disable deposit address creation
*PrivateApi* | [**privateToggleNotificationsFromSubaccountGet**](docs/PrivateApi.md#privatetogglenotificationsfromsubaccountget) | **GET** /private/toggle_notifications_from_subaccount | Enable or disable sending of notifications for the subaccount.
*PrivateApi* | [**privateToggleSubaccountLoginGet**](docs/PrivateApi.md#privatetogglesubaccountloginget) | **GET** /private/toggle_subaccount_login | Enable or disable login for a subaccount. If login is disabled and a session for the subaccount exists, this session will be terminated.
*PrivateApi* | [**privateWithdrawGet**](docs/PrivateApi.md#privatewithdrawget) | **GET** /private/withdraw | Creates a new withdrawal request
*PublicApi* | [**publicAuthGet**](docs/PublicApi.md#publicauthget) | **GET** /public/auth | Authenticate
*PublicApi* | [**publicGetAnnouncementsGet**](docs/PublicApi.md#publicgetannouncementsget) | **GET** /public/get_announcements | Retrieves announcements from the last 30 days.
*PublicApi* | [**publicGetBookSummaryByCurrencyGet**](docs/PublicApi.md#publicgetbooksummarybycurrencyget) | **GET** /public/get_book_summary_by_currency | Retrieves the summary information such as open interest, 24h volume, etc. for all instruments for the currency (optionally filtered by kind).
*PublicApi* | [**publicGetBookSummaryByInstrumentGet**](docs/PublicApi.md#publicgetbooksummarybyinstrumentget) | **GET** /public/get_book_summary_by_instrument | Retrieves the summary information such as open interest, 24h volume, etc. for a specific instrument.
*PublicApi* | [**publicGetContractSizeGet**](docs/PublicApi.md#publicgetcontractsizeget) | **GET** /public/get_contract_size | Retrieves contract size of provided instrument.
*PublicApi* | [**publicGetCurrenciesGet**](docs/PublicApi.md#publicgetcurrenciesget) | **GET** /public/get_currencies | Retrieves all cryptocurrencies supported by the API.
*PublicApi* | [**publicGetFundingChartDataGet**](docs/PublicApi.md#publicgetfundingchartdataget) | **GET** /public/get_funding_chart_data | Retrieve the latest user trades that have occurred for PERPETUAL instruments in a specific currency symbol and within given time range.
*PublicApi* | [**publicGetHistoricalVolatilityGet**](docs/PublicApi.md#publicgethistoricalvolatilityget) | **GET** /public/get_historical_volatility | Provides information about historical volatility for given cryptocurrency.
*PublicApi* | [**publicGetIndexGet**](docs/PublicApi.md#publicgetindexget) | **GET** /public/get_index | Retrieves the current index price for the instruments, for the selected currency.
*PublicApi* | [**publicGetInstrumentsGet**](docs/PublicApi.md#publicgetinstrumentsget) | **GET** /public/get_instruments | Retrieves available trading instruments. This method can be used to see which instruments are available for trading, or which instruments have existed historically.
*PublicApi* | [**publicGetLastSettlementsByCurrencyGet**](docs/PublicApi.md#publicgetlastsettlementsbycurrencyget) | **GET** /public/get_last_settlements_by_currency | Retrieves historical settlement, delivery and bankruptcy events coming from all instruments within given currency.
*PublicApi* | [**publicGetLastSettlementsByInstrumentGet**](docs/PublicApi.md#publicgetlastsettlementsbyinstrumentget) | **GET** /public/get_last_settlements_by_instrument | Retrieves historical public settlement, delivery and bankruptcy events filtered by instrument name.
*PublicApi* | [**publicGetLastTradesByCurrencyAndTimeGet**](docs/PublicApi.md#publicgetlasttradesbycurrencyandtimeget) | **GET** /public/get_last_trades_by_currency_and_time | Retrieve the latest trades that have occurred for instruments in a specific currency symbol and within given time range.
*PublicApi* | [**publicGetLastTradesByCurrencyGet**](docs/PublicApi.md#publicgetlasttradesbycurrencyget) | **GET** /public/get_last_trades_by_currency | Retrieve the latest trades that have occurred for instruments in a specific currency symbol.
*PublicApi* | [**publicGetLastTradesByInstrumentAndTimeGet**](docs/PublicApi.md#publicgetlasttradesbyinstrumentandtimeget) | **GET** /public/get_last_trades_by_instrument_and_time | Retrieve the latest trades that have occurred for a specific instrument and within given time range.
*PublicApi* | [**publicGetLastTradesByInstrumentGet**](docs/PublicApi.md#publicgetlasttradesbyinstrumentget) | **GET** /public/get_last_trades_by_instrument | Retrieve the latest trades that have occurred for a specific instrument.
*PublicApi* | [**publicGetOrderBookGet**](docs/PublicApi.md#publicgetorderbookget) | **GET** /public/get_order_book | Retrieves the order book, along with other market values for a given instrument.
*PublicApi* | [**publicGetTimeGet**](docs/PublicApi.md#publicgettimeget) | **GET** /public/get_time | Retrieves the current time (in milliseconds). This API endpoint can be used to check the clock skew between your software and Deribit's systems.
*PublicApi* | [**publicGetTradeVolumesGet**](docs/PublicApi.md#publicgettradevolumesget) | **GET** /public/get_trade_volumes | Retrieves aggregated 24h trade volumes for different instrument types and currencies.
*PublicApi* | [**publicGetTradingviewChartDataGet**](docs/PublicApi.md#publicgettradingviewchartdataget) | **GET** /public/get_tradingview_chart_data | Publicly available market data used to generate a TradingView candle chart.
*PublicApi* | [**publicTestGet**](docs/PublicApi.md#publictestget) | **GET** /public/test | Tests the connection to the API server, and returns its version. You can use this to make sure the API is reachable, and matches the expected version.
*PublicApi* | [**publicTickerGet**](docs/PublicApi.md#publictickerget) | **GET** /public/ticker | Get ticker for an instrument.
*PublicApi* | [**publicValidateFieldGet**](docs/PublicApi.md#publicvalidatefieldget) | **GET** /public/validate_field | Method used to introduce the client software connected to Deribit platform over websocket. Provided data may have an impact on the maintained connection and will be collected for internal statistical purposes. In response, Deribit will also introduce itself.
*SupportingApi* | [**publicGetTimeGet**](docs/SupportingApi.md#publicgettimeget) | **GET** /public/get_time | Retrieves the current time (in milliseconds). This API endpoint can be used to check the clock skew between your software and Deribit's systems.
*SupportingApi* | [**publicTestGet**](docs/SupportingApi.md#publictestget) | **GET** /public/test | Tests the connection to the API server, and returns its version. You can use this to make sure the API is reachable, and matches the expected version.
*TradingApi* | [**privateBuyGet**](docs/TradingApi.md#privatebuyget) | **GET** /private/buy | Places a buy order for an instrument.
*TradingApi* | [**privateCancelAllByCurrencyGet**](docs/TradingApi.md#privatecancelallbycurrencyget) | **GET** /private/cancel_all_by_currency | Cancels all orders by currency, optionally filtered by instrument kind and/or order type.
*TradingApi* | [**privateCancelAllByInstrumentGet**](docs/TradingApi.md#privatecancelallbyinstrumentget) | **GET** /private/cancel_all_by_instrument | Cancels all orders by instrument, optionally filtered by order type.
*TradingApi* | [**privateCancelAllGet**](docs/TradingApi.md#privatecancelallget) | **GET** /private/cancel_all | This method cancels all users orders and stop orders within all currencies and instrument kinds.
*TradingApi* | [**privateCancelGet**](docs/TradingApi.md#privatecancelget) | **GET** /private/cancel | Cancel an order, specified by order id
*TradingApi* | [**privateClosePositionGet**](docs/TradingApi.md#privateclosepositionget) | **GET** /private/close_position | Makes closing position reduce only order .
*TradingApi* | [**privateEditGet**](docs/TradingApi.md#privateeditget) | **GET** /private/edit | Change price, amount and/or other properties of an order.
*TradingApi* | [**privateGetMarginsGet**](docs/TradingApi.md#privategetmarginsget) | **GET** /private/get_margins | Get margins for given instrument, amount and price.
*TradingApi* | [**privateGetOpenOrdersByCurrencyGet**](docs/TradingApi.md#privategetopenordersbycurrencyget) | **GET** /private/get_open_orders_by_currency | Retrieves list of user's open orders.
*TradingApi* | [**privateGetOpenOrdersByInstrumentGet**](docs/TradingApi.md#privategetopenordersbyinstrumentget) | **GET** /private/get_open_orders_by_instrument | Retrieves list of user's open orders within given Instrument.
*TradingApi* | [**privateGetOrderHistoryByCurrencyGet**](docs/TradingApi.md#privategetorderhistorybycurrencyget) | **GET** /private/get_order_history_by_currency | Retrieves history of orders that have been partially or fully filled.
*TradingApi* | [**privateGetOrderHistoryByInstrumentGet**](docs/TradingApi.md#privategetorderhistorybyinstrumentget) | **GET** /private/get_order_history_by_instrument | Retrieves history of orders that have been partially or fully filled.
*TradingApi* | [**privateGetOrderMarginByIdsGet**](docs/TradingApi.md#privategetordermarginbyidsget) | **GET** /private/get_order_margin_by_ids | Retrieves initial margins of given orders
*TradingApi* | [**privateGetOrderStateGet**](docs/TradingApi.md#privategetorderstateget) | **GET** /private/get_order_state | Retrieve the current state of an order.
*TradingApi* | [**privateGetSettlementHistoryByCurrencyGet**](docs/TradingApi.md#privategetsettlementhistorybycurrencyget) | **GET** /private/get_settlement_history_by_currency | Retrieves settlement, delivery and bankruptcy events that have affected your account.
*TradingApi* | [**privateGetSettlementHistoryByInstrumentGet**](docs/TradingApi.md#privategetsettlementhistorybyinstrumentget) | **GET** /private/get_settlement_history_by_instrument | Retrieves public settlement, delivery and bankruptcy events filtered by instrument name
*TradingApi* | [**privateGetUserTradesByCurrencyAndTimeGet**](docs/TradingApi.md#privategetusertradesbycurrencyandtimeget) | **GET** /private/get_user_trades_by_currency_and_time | Retrieve the latest user trades that have occurred for instruments in a specific currency symbol and within given time range.
*TradingApi* | [**privateGetUserTradesByCurrencyGet**](docs/TradingApi.md#privategetusertradesbycurrencyget) | **GET** /private/get_user_trades_by_currency | Retrieve the latest user trades that have occurred for instruments in a specific currency symbol.
*TradingApi* | [**privateGetUserTradesByInstrumentAndTimeGet**](docs/TradingApi.md#privategetusertradesbyinstrumentandtimeget) | **GET** /private/get_user_trades_by_instrument_and_time | Retrieve the latest user trades that have occurred for a specific instrument and within given time range.
*TradingApi* | [**privateGetUserTradesByInstrumentGet**](docs/TradingApi.md#privategetusertradesbyinstrumentget) | **GET** /private/get_user_trades_by_instrument | Retrieve the latest user trades that have occurred for a specific instrument.
*TradingApi* | [**privateGetUserTradesByOrderGet**](docs/TradingApi.md#privategetusertradesbyorderget) | **GET** /private/get_user_trades_by_order | Retrieve the list of user trades that was created for given order
*TradingApi* | [**privateSellGet**](docs/TradingApi.md#privatesellget) | **GET** /private/sell | Places a sell order for an instrument.
*WalletApi* | [**privateAddToAddressBookGet**](docs/WalletApi.md#privateaddtoaddressbookget) | **GET** /private/add_to_address_book | Adds new entry to address book of given type
*WalletApi* | [**privateCancelTransferByIdGet**](docs/WalletApi.md#privatecanceltransferbyidget) | **GET** /private/cancel_transfer_by_id | Cancel transfer
*WalletApi* | [**privateCancelWithdrawalGet**](docs/WalletApi.md#privatecancelwithdrawalget) | **GET** /private/cancel_withdrawal | Cancels withdrawal request
*WalletApi* | [**privateCreateDepositAddressGet**](docs/WalletApi.md#privatecreatedepositaddressget) | **GET** /private/create_deposit_address | Creates deposit address in currency
*WalletApi* | [**privateGetAddressBookGet**](docs/WalletApi.md#privategetaddressbookget) | **GET** /private/get_address_book | Retrieves address book of given type
*WalletApi* | [**privateGetCurrentDepositAddressGet**](docs/WalletApi.md#privategetcurrentdepositaddressget) | **GET** /private/get_current_deposit_address | Retrieve deposit address for currency
*WalletApi* | [**privateGetDepositsGet**](docs/WalletApi.md#privategetdepositsget) | **GET** /private/get_deposits | Retrieve the latest users deposits
*WalletApi* | [**privateGetTransfersGet**](docs/WalletApi.md#privategettransfersget) | **GET** /private/get_transfers | Adds new entry to address book of given type
*WalletApi* | [**privateGetWithdrawalsGet**](docs/WalletApi.md#privategetwithdrawalsget) | **GET** /private/get_withdrawals | Retrieve the latest users withdrawals
*WalletApi* | [**privateRemoveFromAddressBookGet**](docs/WalletApi.md#privateremovefromaddressbookget) | **GET** /private/remove_from_address_book | Adds new entry to address book of given type
*WalletApi* | [**privateSubmitTransferToSubaccountGet**](docs/WalletApi.md#privatesubmittransfertosubaccountget) | **GET** /private/submit_transfer_to_subaccount | Transfer funds to a subaccount.
*WalletApi* | [**privateSubmitTransferToUserGet**](docs/WalletApi.md#privatesubmittransfertouserget) | **GET** /private/submit_transfer_to_user | Transfer funds to a another user.
*WalletApi* | [**privateToggleDepositAddressCreationGet**](docs/WalletApi.md#privatetoggledepositaddresscreationget) | **GET** /private/toggle_deposit_address_creation | Enable or disable deposit address creation
*WalletApi* | [**privateWithdrawGet**](docs/WalletApi.md#privatewithdrawget) | **GET** /private/withdraw | Creates a new withdrawal request


<a name="documentation-for-models"></a>
## Documentation for Models

 - [org.openapitools.client.models.AddressBookItem](docs/AddressBookItem.md)
 - [org.openapitools.client.models.BookSummary](docs/BookSummary.md)
 - [org.openapitools.client.models.Currency](docs/Currency.md)
 - [org.openapitools.client.models.CurrencyPortfolio](docs/CurrencyPortfolio.md)
 - [org.openapitools.client.models.CurrencyWithdrawalPriorities](docs/CurrencyWithdrawalPriorities.md)
 - [org.openapitools.client.models.Deposit](docs/Deposit.md)
 - [org.openapitools.client.models.Instrument](docs/Instrument.md)
 - [org.openapitools.client.models.KeyNumberPair](docs/KeyNumberPair.md)
 - [org.openapitools.client.models.Order](docs/Order.md)
 - [org.openapitools.client.models.OrderIdInitialMarginPair](docs/OrderIdInitialMarginPair.md)
 - [org.openapitools.client.models.Portfolio](docs/Portfolio.md)
 - [org.openapitools.client.models.PortfolioEth](docs/PortfolioEth.md)
 - [org.openapitools.client.models.Position](docs/Position.md)
 - [org.openapitools.client.models.PublicTrade](docs/PublicTrade.md)
 - [org.openapitools.client.models.Settlement](docs/Settlement.md)
 - [org.openapitools.client.models.TradesVolumes](docs/TradesVolumes.md)
 - [org.openapitools.client.models.TransferItem](docs/TransferItem.md)
 - [org.openapitools.client.models.Types](docs/Types.md)
 - [org.openapitools.client.models.UserTrade](docs/UserTrade.md)
 - [org.openapitools.client.models.Withdrawal](docs/Withdrawal.md)


<a name="documentation-for-authorization"></a>
## Documentation for Authorization

<a name="bearerAuth"></a>
### bearerAuth

- **Type**: HTTP basic authentication

