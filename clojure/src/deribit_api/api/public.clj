(ns deribit-api.api.public
  (:require [deribit-api.core :refer [call-api check-required-params with-collection-format *api-context*]]
            [clojure.spec.alpha :as s]
            [spec-tools.core :as st]
            [orchestra.core :refer [defn-spec]]
            [deribit-api.specs.public-trade :refer :all]
            [deribit-api.specs.currency-withdrawal-priorities :refer :all]
            [deribit-api.specs.address-book-item :refer :all]
            [deribit-api.specs.types :refer :all]
            [deribit-api.specs.transfer-item :refer :all]
            [deribit-api.specs.order-id-initial-margin-pair :refer :all]
            [deribit-api.specs.key-number-pair :refer :all]
            [deribit-api.specs.trades-volumes :refer :all]
            [deribit-api.specs.instrument :refer :all]
            [deribit-api.specs.withdrawal :refer :all]
            [deribit-api.specs.portfolio-eth :refer :all]
            [deribit-api.specs.settlement :refer :all]
            [deribit-api.specs.user-trade :refer :all]
            [deribit-api.specs.portfolio :refer :all]
            [deribit-api.specs.currency-portfolio :refer :all]
            [deribit-api.specs.deposit :refer :all]
            [deribit-api.specs.book-summary :refer :all]
            [deribit-api.specs.currency :refer :all]
            [deribit-api.specs.position :refer :all]
            [deribit-api.specs.order :refer :all]
            )
  (:import (java.io File)))


(defn-spec public-auth-get-with-http-info any?
  "Authenticate
  Retrieve an Oauth access token, to be used for authentication of 'private' requests.

Three methods of authentication are supported:

- <code>password</code> - using email and and password as when logging on to the website
- <code>client_credentials</code> - using the access key and access secret that can be found on the API page on the website
- <code>client_signature</code> - using the access key that can be found on the API page on the website and user generated signature. The signature is calculated using some fields provided in the request, using formula described here [Deribit signature credentials](#additional-authorization-method-deribit-signature-credentials)
- <code>refresh_token</code> - using a refresh token that was received from an earlier invocation

The response will contain an access token, expiration period (number of seconds that the token is valid) and a refresh token that can
be used to get a new set of tokens."
  ([grant_type string?, username string?, password string?, client_id string?, client_secret string?, refresh_token string?, timestamp string?, signature string?, ] (public-auth-get-with-http-info grant_type username password client_id client_secret refresh_token timestamp signature nil))
  ([grant_type string?, username string?, password string?, client_id string?, client_secret string?, refresh_token string?, timestamp string?, signature string?, {:keys [nonce state scope]} (s/map-of keyword? any?)]
   (check-required-params grant_type username password client_id client_secret refresh_token timestamp signature)
   (call-api "/public/auth" :get
             {:path-params   {}
              :header-params {}
              :query-params  {"grant_type" grant_type "username" username "password" password "client_id" client_id "client_secret" client_secret "refresh_token" refresh_token "timestamp" timestamp "signature" signature "nonce" nonce "state" state "scope" scope }
              :form-params   {}
              :content-types []
              :accepts       ["application/json"]
              :auth-names    ["bearerAuth"]})))

(defn-spec public-auth-get any?
  "Authenticate
  Retrieve an Oauth access token, to be used for authentication of 'private' requests.

Three methods of authentication are supported:

- <code>password</code> - using email and and password as when logging on to the website
- <code>client_credentials</code> - using the access key and access secret that can be found on the API page on the website
- <code>client_signature</code> - using the access key that can be found on the API page on the website and user generated signature. The signature is calculated using some fields provided in the request, using formula described here [Deribit signature credentials](#additional-authorization-method-deribit-signature-credentials)
- <code>refresh_token</code> - using a refresh token that was received from an earlier invocation

The response will contain an access token, expiration period (number of seconds that the token is valid) and a refresh token that can
be used to get a new set of tokens."
  ([grant_type string?, username string?, password string?, client_id string?, client_secret string?, refresh_token string?, timestamp string?, signature string?, ] (public-auth-get grant_type username password client_id client_secret refresh_token timestamp signature nil))
  ([grant_type string?, username string?, password string?, client_id string?, client_secret string?, refresh_token string?, timestamp string?, signature string?, optional-params any?]
   (let [res (:data (public-auth-get-with-http-info grant_type username password client_id client_secret refresh_token timestamp signature optional-params))]
     (if (:decode-models *api-context*)
        (st/decode any? res st/string-transformer)
        res))))


(defn-spec public-get-announcements-get-with-http-info any?
  "Retrieves announcements from the last 30 days."
  []
  (call-api "/public/get_announcements" :get
            {:path-params   {}
             :header-params {}
             :query-params  {}
             :form-params   {}
             :content-types []
             :accepts       ["application/json"]
             :auth-names    ["bearerAuth"]}))

(defn-spec public-get-announcements-get any?
  "Retrieves announcements from the last 30 days."
  []
  (let [res (:data (public-get-announcements-get-with-http-info))]
    (if (:decode-models *api-context*)
       (st/decode any? res st/string-transformer)
       res)))


(defn-spec public-get-book-summary-by-currency-get-with-http-info any?
  "Retrieves the summary information such as open interest, 24h volume, etc. for all instruments for the currency (optionally filtered by kind)."
  ([currency string?, ] (public-get-book-summary-by-currency-get-with-http-info currency nil))
  ([currency string?, {:keys [kind]} (s/map-of keyword? any?)]
   (check-required-params currency)
   (call-api "/public/get_book_summary_by_currency" :get
             {:path-params   {}
              :header-params {}
              :query-params  {"currency" currency "kind" kind }
              :form-params   {}
              :content-types []
              :accepts       ["application/json"]
              :auth-names    ["bearerAuth"]})))

(defn-spec public-get-book-summary-by-currency-get any?
  "Retrieves the summary information such as open interest, 24h volume, etc. for all instruments for the currency (optionally filtered by kind)."
  ([currency string?, ] (public-get-book-summary-by-currency-get currency nil))
  ([currency string?, optional-params any?]
   (let [res (:data (public-get-book-summary-by-currency-get-with-http-info currency optional-params))]
     (if (:decode-models *api-context*)
        (st/decode any? res st/string-transformer)
        res))))


(defn-spec public-get-book-summary-by-instrument-get-with-http-info any?
  "Retrieves the summary information such as open interest, 24h volume, etc. for a specific instrument."
  [instrument_name string?]
  (check-required-params instrument_name)
  (call-api "/public/get_book_summary_by_instrument" :get
            {:path-params   {}
             :header-params {}
             :query-params  {"instrument_name" instrument_name }
             :form-params   {}
             :content-types []
             :accepts       ["application/json"]
             :auth-names    ["bearerAuth"]}))

(defn-spec public-get-book-summary-by-instrument-get any?
  "Retrieves the summary information such as open interest, 24h volume, etc. for a specific instrument."
  [instrument_name string?]
  (let [res (:data (public-get-book-summary-by-instrument-get-with-http-info instrument_name))]
    (if (:decode-models *api-context*)
       (st/decode any? res st/string-transformer)
       res)))


(defn-spec public-get-contract-size-get-with-http-info any?
  "Retrieves contract size of provided instrument."
  [instrument_name string?]
  (check-required-params instrument_name)
  (call-api "/public/get_contract_size" :get
            {:path-params   {}
             :header-params {}
             :query-params  {"instrument_name" instrument_name }
             :form-params   {}
             :content-types []
             :accepts       ["application/json"]
             :auth-names    ["bearerAuth"]}))

(defn-spec public-get-contract-size-get any?
  "Retrieves contract size of provided instrument."
  [instrument_name string?]
  (let [res (:data (public-get-contract-size-get-with-http-info instrument_name))]
    (if (:decode-models *api-context*)
       (st/decode any? res st/string-transformer)
       res)))


(defn-spec public-get-currencies-get-with-http-info any?
  "Retrieves all cryptocurrencies supported by the API."
  []
  (call-api "/public/get_currencies" :get
            {:path-params   {}
             :header-params {}
             :query-params  {}
             :form-params   {}
             :content-types []
             :accepts       ["application/json"]
             :auth-names    ["bearerAuth"]}))

(defn-spec public-get-currencies-get any?
  "Retrieves all cryptocurrencies supported by the API."
  []
  (let [res (:data (public-get-currencies-get-with-http-info))]
    (if (:decode-models *api-context*)
       (st/decode any? res st/string-transformer)
       res)))


(defn-spec public-get-funding-chart-data-get-with-http-info any?
  "Retrieve the latest user trades that have occurred for PERPETUAL instruments in a specific currency symbol and within given time range."
  ([instrument_name string?, ] (public-get-funding-chart-data-get-with-http-info instrument_name nil))
  ([instrument_name string?, {:keys [length]} (s/map-of keyword? any?)]
   (check-required-params instrument_name)
   (call-api "/public/get_funding_chart_data" :get
             {:path-params   {}
              :header-params {}
              :query-params  {"instrument_name" instrument_name "length" length }
              :form-params   {}
              :content-types []
              :accepts       ["application/json"]
              :auth-names    ["bearerAuth"]})))

(defn-spec public-get-funding-chart-data-get any?
  "Retrieve the latest user trades that have occurred for PERPETUAL instruments in a specific currency symbol and within given time range."
  ([instrument_name string?, ] (public-get-funding-chart-data-get instrument_name nil))
  ([instrument_name string?, optional-params any?]
   (let [res (:data (public-get-funding-chart-data-get-with-http-info instrument_name optional-params))]
     (if (:decode-models *api-context*)
        (st/decode any? res st/string-transformer)
        res))))


(defn-spec public-get-historical-volatility-get-with-http-info any?
  "Provides information about historical volatility for given cryptocurrency."
  [currency string?]
  (check-required-params currency)
  (call-api "/public/get_historical_volatility" :get
            {:path-params   {}
             :header-params {}
             :query-params  {"currency" currency }
             :form-params   {}
             :content-types []
             :accepts       ["application/json"]
             :auth-names    ["bearerAuth"]}))

(defn-spec public-get-historical-volatility-get any?
  "Provides information about historical volatility for given cryptocurrency."
  [currency string?]
  (let [res (:data (public-get-historical-volatility-get-with-http-info currency))]
    (if (:decode-models *api-context*)
       (st/decode any? res st/string-transformer)
       res)))


(defn-spec public-get-index-get-with-http-info any?
  "Retrieves the current index price for the instruments, for the selected currency."
  [currency string?]
  (check-required-params currency)
  (call-api "/public/get_index" :get
            {:path-params   {}
             :header-params {}
             :query-params  {"currency" currency }
             :form-params   {}
             :content-types []
             :accepts       ["application/json"]
             :auth-names    ["bearerAuth"]}))

(defn-spec public-get-index-get any?
  "Retrieves the current index price for the instruments, for the selected currency."
  [currency string?]
  (let [res (:data (public-get-index-get-with-http-info currency))]
    (if (:decode-models *api-context*)
       (st/decode any? res st/string-transformer)
       res)))


(defn-spec public-get-instruments-get-with-http-info any?
  "Retrieves available trading instruments. This method can be used to see which instruments are available for trading, or which instruments have existed historically."
  ([currency string?, ] (public-get-instruments-get-with-http-info currency nil))
  ([currency string?, {:keys [kind expired]} (s/map-of keyword? any?)]
   (check-required-params currency)
   (call-api "/public/get_instruments" :get
             {:path-params   {}
              :header-params {}
              :query-params  {"currency" currency "kind" kind "expired" expired }
              :form-params   {}
              :content-types []
              :accepts       ["application/json"]
              :auth-names    ["bearerAuth"]})))

(defn-spec public-get-instruments-get any?
  "Retrieves available trading instruments. This method can be used to see which instruments are available for trading, or which instruments have existed historically."
  ([currency string?, ] (public-get-instruments-get currency nil))
  ([currency string?, optional-params any?]
   (let [res (:data (public-get-instruments-get-with-http-info currency optional-params))]
     (if (:decode-models *api-context*)
        (st/decode any? res st/string-transformer)
        res))))


(defn-spec public-get-last-settlements-by-currency-get-with-http-info any?
  "Retrieves historical settlement, delivery and bankruptcy events coming from all instruments within given currency."
  ([currency string?, ] (public-get-last-settlements-by-currency-get-with-http-info currency nil))
  ([currency string?, {:keys [type count continuation search_start_timestamp]} (s/map-of keyword? any?)]
   (check-required-params currency)
   (call-api "/public/get_last_settlements_by_currency" :get
             {:path-params   {}
              :header-params {}
              :query-params  {"currency" currency "type" type "count" count "continuation" continuation "search_start_timestamp" search_start_timestamp }
              :form-params   {}
              :content-types []
              :accepts       ["application/json"]
              :auth-names    ["bearerAuth"]})))

(defn-spec public-get-last-settlements-by-currency-get any?
  "Retrieves historical settlement, delivery and bankruptcy events coming from all instruments within given currency."
  ([currency string?, ] (public-get-last-settlements-by-currency-get currency nil))
  ([currency string?, optional-params any?]
   (let [res (:data (public-get-last-settlements-by-currency-get-with-http-info currency optional-params))]
     (if (:decode-models *api-context*)
        (st/decode any? res st/string-transformer)
        res))))


(defn-spec public-get-last-settlements-by-instrument-get-with-http-info any?
  "Retrieves historical public settlement, delivery and bankruptcy events filtered by instrument name."
  ([instrument_name string?, ] (public-get-last-settlements-by-instrument-get-with-http-info instrument_name nil))
  ([instrument_name string?, {:keys [type count continuation search_start_timestamp]} (s/map-of keyword? any?)]
   (check-required-params instrument_name)
   (call-api "/public/get_last_settlements_by_instrument" :get
             {:path-params   {}
              :header-params {}
              :query-params  {"instrument_name" instrument_name "type" type "count" count "continuation" continuation "search_start_timestamp" search_start_timestamp }
              :form-params   {}
              :content-types []
              :accepts       ["application/json"]
              :auth-names    ["bearerAuth"]})))

(defn-spec public-get-last-settlements-by-instrument-get any?
  "Retrieves historical public settlement, delivery and bankruptcy events filtered by instrument name."
  ([instrument_name string?, ] (public-get-last-settlements-by-instrument-get instrument_name nil))
  ([instrument_name string?, optional-params any?]
   (let [res (:data (public-get-last-settlements-by-instrument-get-with-http-info instrument_name optional-params))]
     (if (:decode-models *api-context*)
        (st/decode any? res st/string-transformer)
        res))))


(defn-spec public-get-last-trades-by-currency-and-time-get-with-http-info any?
  "Retrieve the latest trades that have occurred for instruments in a specific currency symbol and within given time range."
  ([currency string?, start_timestamp int?, end_timestamp int?, ] (public-get-last-trades-by-currency-and-time-get-with-http-info currency start_timestamp end_timestamp nil))
  ([currency string?, start_timestamp int?, end_timestamp int?, {:keys [kind count include_old sorting]} (s/map-of keyword? any?)]
   (check-required-params currency start_timestamp end_timestamp)
   (call-api "/public/get_last_trades_by_currency_and_time" :get
             {:path-params   {}
              :header-params {}
              :query-params  {"currency" currency "kind" kind "start_timestamp" start_timestamp "end_timestamp" end_timestamp "count" count "include_old" include_old "sorting" sorting }
              :form-params   {}
              :content-types []
              :accepts       ["application/json"]
              :auth-names    ["bearerAuth"]})))

(defn-spec public-get-last-trades-by-currency-and-time-get any?
  "Retrieve the latest trades that have occurred for instruments in a specific currency symbol and within given time range."
  ([currency string?, start_timestamp int?, end_timestamp int?, ] (public-get-last-trades-by-currency-and-time-get currency start_timestamp end_timestamp nil))
  ([currency string?, start_timestamp int?, end_timestamp int?, optional-params any?]
   (let [res (:data (public-get-last-trades-by-currency-and-time-get-with-http-info currency start_timestamp end_timestamp optional-params))]
     (if (:decode-models *api-context*)
        (st/decode any? res st/string-transformer)
        res))))


(defn-spec public-get-last-trades-by-currency-get-with-http-info any?
  "Retrieve the latest trades that have occurred for instruments in a specific currency symbol."
  ([currency string?, ] (public-get-last-trades-by-currency-get-with-http-info currency nil))
  ([currency string?, {:keys [kind start_id end_id count include_old sorting]} (s/map-of keyword? any?)]
   (check-required-params currency)
   (call-api "/public/get_last_trades_by_currency" :get
             {:path-params   {}
              :header-params {}
              :query-params  {"currency" currency "kind" kind "start_id" start_id "end_id" end_id "count" count "include_old" include_old "sorting" sorting }
              :form-params   {}
              :content-types []
              :accepts       ["application/json"]
              :auth-names    ["bearerAuth"]})))

(defn-spec public-get-last-trades-by-currency-get any?
  "Retrieve the latest trades that have occurred for instruments in a specific currency symbol."
  ([currency string?, ] (public-get-last-trades-by-currency-get currency nil))
  ([currency string?, optional-params any?]
   (let [res (:data (public-get-last-trades-by-currency-get-with-http-info currency optional-params))]
     (if (:decode-models *api-context*)
        (st/decode any? res st/string-transformer)
        res))))


(defn-spec public-get-last-trades-by-instrument-and-time-get-with-http-info any?
  "Retrieve the latest trades that have occurred for a specific instrument and within given time range."
  ([instrument_name string?, start_timestamp int?, end_timestamp int?, ] (public-get-last-trades-by-instrument-and-time-get-with-http-info instrument_name start_timestamp end_timestamp nil))
  ([instrument_name string?, start_timestamp int?, end_timestamp int?, {:keys [count include_old sorting]} (s/map-of keyword? any?)]
   (check-required-params instrument_name start_timestamp end_timestamp)
   (call-api "/public/get_last_trades_by_instrument_and_time" :get
             {:path-params   {}
              :header-params {}
              :query-params  {"instrument_name" instrument_name "start_timestamp" start_timestamp "end_timestamp" end_timestamp "count" count "include_old" include_old "sorting" sorting }
              :form-params   {}
              :content-types []
              :accepts       ["application/json"]
              :auth-names    ["bearerAuth"]})))

(defn-spec public-get-last-trades-by-instrument-and-time-get any?
  "Retrieve the latest trades that have occurred for a specific instrument and within given time range."
  ([instrument_name string?, start_timestamp int?, end_timestamp int?, ] (public-get-last-trades-by-instrument-and-time-get instrument_name start_timestamp end_timestamp nil))
  ([instrument_name string?, start_timestamp int?, end_timestamp int?, optional-params any?]
   (let [res (:data (public-get-last-trades-by-instrument-and-time-get-with-http-info instrument_name start_timestamp end_timestamp optional-params))]
     (if (:decode-models *api-context*)
        (st/decode any? res st/string-transformer)
        res))))


(defn-spec public-get-last-trades-by-instrument-get-with-http-info any?
  "Retrieve the latest trades that have occurred for a specific instrument."
  ([instrument_name string?, ] (public-get-last-trades-by-instrument-get-with-http-info instrument_name nil))
  ([instrument_name string?, {:keys [start_seq end_seq count include_old sorting]} (s/map-of keyword? any?)]
   (check-required-params instrument_name)
   (call-api "/public/get_last_trades_by_instrument" :get
             {:path-params   {}
              :header-params {}
              :query-params  {"instrument_name" instrument_name "start_seq" start_seq "end_seq" end_seq "count" count "include_old" include_old "sorting" sorting }
              :form-params   {}
              :content-types []
              :accepts       ["application/json"]
              :auth-names    ["bearerAuth"]})))

(defn-spec public-get-last-trades-by-instrument-get any?
  "Retrieve the latest trades that have occurred for a specific instrument."
  ([instrument_name string?, ] (public-get-last-trades-by-instrument-get instrument_name nil))
  ([instrument_name string?, optional-params any?]
   (let [res (:data (public-get-last-trades-by-instrument-get-with-http-info instrument_name optional-params))]
     (if (:decode-models *api-context*)
        (st/decode any? res st/string-transformer)
        res))))


(defn-spec public-get-order-book-get-with-http-info any?
  "Retrieves the order book, along with other market values for a given instrument."
  ([instrument_name string?, ] (public-get-order-book-get-with-http-info instrument_name nil))
  ([instrument_name string?, {:keys [depth]} (s/map-of keyword? any?)]
   (check-required-params instrument_name)
   (call-api "/public/get_order_book" :get
             {:path-params   {}
              :header-params {}
              :query-params  {"instrument_name" instrument_name "depth" depth }
              :form-params   {}
              :content-types []
              :accepts       ["application/json"]
              :auth-names    ["bearerAuth"]})))

(defn-spec public-get-order-book-get any?
  "Retrieves the order book, along with other market values for a given instrument."
  ([instrument_name string?, ] (public-get-order-book-get instrument_name nil))
  ([instrument_name string?, optional-params any?]
   (let [res (:data (public-get-order-book-get-with-http-info instrument_name optional-params))]
     (if (:decode-models *api-context*)
        (st/decode any? res st/string-transformer)
        res))))


(defn-spec public-get-time-get-with-http-info any?
  "Retrieves the current time (in milliseconds). This API endpoint can be used to check the clock skew between your software and Deribit's systems."
  []
  (call-api "/public/get_time" :get
            {:path-params   {}
             :header-params {}
             :query-params  {}
             :form-params   {}
             :content-types []
             :accepts       ["application/json"]
             :auth-names    ["bearerAuth"]}))

(defn-spec public-get-time-get any?
  "Retrieves the current time (in milliseconds). This API endpoint can be used to check the clock skew between your software and Deribit's systems."
  []
  (let [res (:data (public-get-time-get-with-http-info))]
    (if (:decode-models *api-context*)
       (st/decode any? res st/string-transformer)
       res)))


(defn-spec public-get-trade-volumes-get-with-http-info any?
  "Retrieves aggregated 24h trade volumes for different instrument types and currencies."
  []
  (call-api "/public/get_trade_volumes" :get
            {:path-params   {}
             :header-params {}
             :query-params  {}
             :form-params   {}
             :content-types []
             :accepts       ["application/json"]
             :auth-names    ["bearerAuth"]}))

(defn-spec public-get-trade-volumes-get any?
  "Retrieves aggregated 24h trade volumes for different instrument types and currencies."
  []
  (let [res (:data (public-get-trade-volumes-get-with-http-info))]
    (if (:decode-models *api-context*)
       (st/decode any? res st/string-transformer)
       res)))


(defn-spec public-get-tradingview-chart-data-get-with-http-info any?
  "Publicly available market data used to generate a TradingView candle chart."
  [instrument_name string?, start_timestamp int?, end_timestamp int?]
  (check-required-params instrument_name start_timestamp end_timestamp)
  (call-api "/public/get_tradingview_chart_data" :get
            {:path-params   {}
             :header-params {}
             :query-params  {"instrument_name" instrument_name "start_timestamp" start_timestamp "end_timestamp" end_timestamp }
             :form-params   {}
             :content-types []
             :accepts       ["application/json"]
             :auth-names    ["bearerAuth"]}))

(defn-spec public-get-tradingview-chart-data-get any?
  "Publicly available market data used to generate a TradingView candle chart."
  [instrument_name string?, start_timestamp int?, end_timestamp int?]
  (let [res (:data (public-get-tradingview-chart-data-get-with-http-info instrument_name start_timestamp end_timestamp))]
    (if (:decode-models *api-context*)
       (st/decode any? res st/string-transformer)
       res)))


(defn-spec public-test-get-with-http-info any?
  "Tests the connection to the API server, and returns its version. You can use this to make sure the API is reachable, and matches the expected version."
  ([] (public-test-get-with-http-info nil))
  ([{:keys [expected_result]} (s/map-of keyword? any?)]
   (call-api "/public/test" :get
             {:path-params   {}
              :header-params {}
              :query-params  {"expected_result" expected_result }
              :form-params   {}
              :content-types []
              :accepts       ["application/json"]
              :auth-names    ["bearerAuth"]})))

(defn-spec public-test-get any?
  "Tests the connection to the API server, and returns its version. You can use this to make sure the API is reachable, and matches the expected version."
  ([] (public-test-get nil))
  ([optional-params any?]
   (let [res (:data (public-test-get-with-http-info optional-params))]
     (if (:decode-models *api-context*)
        (st/decode any? res st/string-transformer)
        res))))


(defn-spec public-ticker-get-with-http-info any?
  "Get ticker for an instrument."
  [instrument_name string?]
  (check-required-params instrument_name)
  (call-api "/public/ticker" :get
            {:path-params   {}
             :header-params {}
             :query-params  {"instrument_name" instrument_name }
             :form-params   {}
             :content-types []
             :accepts       ["application/json"]
             :auth-names    ["bearerAuth"]}))

(defn-spec public-ticker-get any?
  "Get ticker for an instrument."
  [instrument_name string?]
  (let [res (:data (public-ticker-get-with-http-info instrument_name))]
    (if (:decode-models *api-context*)
       (st/decode any? res st/string-transformer)
       res)))


(defn-spec public-validate-field-get-with-http-info any?
  "Method used to introduce the client software connected to Deribit platform over websocket. Provided data may have an impact on the maintained connection and will be collected for internal statistical purposes. In response, Deribit will also introduce itself."
  ([field string?, value string?, ] (public-validate-field-get-with-http-info field value nil))
  ([field string?, value string?, {:keys [value2]} (s/map-of keyword? any?)]
   (check-required-params field value)
   (call-api "/public/validate_field" :get
             {:path-params   {}
              :header-params {}
              :query-params  {"field" field "value" value "value2" value2 }
              :form-params   {}
              :content-types []
              :accepts       ["application/json"]
              :auth-names    ["bearerAuth"]})))

(defn-spec public-validate-field-get any?
  "Method used to introduce the client software connected to Deribit platform over websocket. Provided data may have an impact on the maintained connection and will be collected for internal statistical purposes. In response, Deribit will also introduce itself."
  ([field string?, value string?, ] (public-validate-field-get field value nil))
  ([field string?, value string?, optional-params any?]
   (let [res (:data (public-validate-field-get-with-http-info field value optional-params))]
     (if (:decode-models *api-context*)
        (st/decode any? res st/string-transformer)
        res))))


