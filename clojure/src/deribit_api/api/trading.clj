(ns deribit-api.api.trading
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


(defn-spec private-buy-get-with-http-info any?
  "Places a buy order for an instrument."
  ([instrument_name string?, amount float?, ] (private-buy-get-with-http-info instrument_name amount nil))
  ([instrument_name string?, amount float?, {:keys [type label price time_in_force max_show post_only reduce_only stop_price trigger advanced]} (s/map-of keyword? any?)]
   (check-required-params instrument_name amount)
   (call-api "/private/buy" :get
             {:path-params   {}
              :header-params {}
              :query-params  {"instrument_name" instrument_name "amount" amount "type" type "label" label "price" price "time_in_force" time_in_force "max_show" max_show "post_only" post_only "reduce_only" reduce_only "stop_price" stop_price "trigger" trigger "advanced" advanced }
              :form-params   {}
              :content-types []
              :accepts       ["application/json"]
              :auth-names    ["bearerAuth"]})))

(defn-spec private-buy-get any?
  "Places a buy order for an instrument."
  ([instrument_name string?, amount float?, ] (private-buy-get instrument_name amount nil))
  ([instrument_name string?, amount float?, optional-params any?]
   (let [res (:data (private-buy-get-with-http-info instrument_name amount optional-params))]
     (if (:decode-models *api-context*)
        (st/decode any? res st/string-transformer)
        res))))


(defn-spec private-cancel-all-by-currency-get-with-http-info any?
  "Cancels all orders by currency, optionally filtered by instrument kind and/or order type."
  ([currency string?, ] (private-cancel-all-by-currency-get-with-http-info currency nil))
  ([currency string?, {:keys [kind type]} (s/map-of keyword? any?)]
   (check-required-params currency)
   (call-api "/private/cancel_all_by_currency" :get
             {:path-params   {}
              :header-params {}
              :query-params  {"currency" currency "kind" kind "type" type }
              :form-params   {}
              :content-types []
              :accepts       ["application/json"]
              :auth-names    ["bearerAuth"]})))

(defn-spec private-cancel-all-by-currency-get any?
  "Cancels all orders by currency, optionally filtered by instrument kind and/or order type."
  ([currency string?, ] (private-cancel-all-by-currency-get currency nil))
  ([currency string?, optional-params any?]
   (let [res (:data (private-cancel-all-by-currency-get-with-http-info currency optional-params))]
     (if (:decode-models *api-context*)
        (st/decode any? res st/string-transformer)
        res))))


(defn-spec private-cancel-all-by-instrument-get-with-http-info any?
  "Cancels all orders by instrument, optionally filtered by order type."
  ([instrument_name string?, ] (private-cancel-all-by-instrument-get-with-http-info instrument_name nil))
  ([instrument_name string?, {:keys [type]} (s/map-of keyword? any?)]
   (check-required-params instrument_name)
   (call-api "/private/cancel_all_by_instrument" :get
             {:path-params   {}
              :header-params {}
              :query-params  {"instrument_name" instrument_name "type" type }
              :form-params   {}
              :content-types []
              :accepts       ["application/json"]
              :auth-names    ["bearerAuth"]})))

(defn-spec private-cancel-all-by-instrument-get any?
  "Cancels all orders by instrument, optionally filtered by order type."
  ([instrument_name string?, ] (private-cancel-all-by-instrument-get instrument_name nil))
  ([instrument_name string?, optional-params any?]
   (let [res (:data (private-cancel-all-by-instrument-get-with-http-info instrument_name optional-params))]
     (if (:decode-models *api-context*)
        (st/decode any? res st/string-transformer)
        res))))


(defn-spec private-cancel-all-get-with-http-info any?
  "This method cancels all users orders and stop orders within all currencies and instrument kinds."
  []
  (call-api "/private/cancel_all" :get
            {:path-params   {}
             :header-params {}
             :query-params  {}
             :form-params   {}
             :content-types []
             :accepts       ["application/json"]
             :auth-names    ["bearerAuth"]}))

(defn-spec private-cancel-all-get any?
  "This method cancels all users orders and stop orders within all currencies and instrument kinds."
  []
  (let [res (:data (private-cancel-all-get-with-http-info))]
    (if (:decode-models *api-context*)
       (st/decode any? res st/string-transformer)
       res)))


(defn-spec private-cancel-get-with-http-info any?
  "Cancel an order, specified by order id"
  [order_id string?]
  (check-required-params order_id)
  (call-api "/private/cancel" :get
            {:path-params   {}
             :header-params {}
             :query-params  {"order_id" order_id }
             :form-params   {}
             :content-types []
             :accepts       ["application/json"]
             :auth-names    ["bearerAuth"]}))

(defn-spec private-cancel-get any?
  "Cancel an order, specified by order id"
  [order_id string?]
  (let [res (:data (private-cancel-get-with-http-info order_id))]
    (if (:decode-models *api-context*)
       (st/decode any? res st/string-transformer)
       res)))


(defn-spec private-close-position-get-with-http-info any?
  "Makes closing position reduce only order ."
  ([instrument_name string?, type string?, ] (private-close-position-get-with-http-info instrument_name type nil))
  ([instrument_name string?, type string?, {:keys [price]} (s/map-of keyword? any?)]
   (check-required-params instrument_name type)
   (call-api "/private/close_position" :get
             {:path-params   {}
              :header-params {}
              :query-params  {"instrument_name" instrument_name "type" type "price" price }
              :form-params   {}
              :content-types []
              :accepts       ["application/json"]
              :auth-names    ["bearerAuth"]})))

(defn-spec private-close-position-get any?
  "Makes closing position reduce only order ."
  ([instrument_name string?, type string?, ] (private-close-position-get instrument_name type nil))
  ([instrument_name string?, type string?, optional-params any?]
   (let [res (:data (private-close-position-get-with-http-info instrument_name type optional-params))]
     (if (:decode-models *api-context*)
        (st/decode any? res st/string-transformer)
        res))))


(defn-spec private-edit-get-with-http-info any?
  "Change price, amount and/or other properties of an order."
  ([order_id string?, amount float?, price float?, ] (private-edit-get-with-http-info order_id amount price nil))
  ([order_id string?, amount float?, price float?, {:keys [post_only advanced stop_price]} (s/map-of keyword? any?)]
   (check-required-params order_id amount price)
   (call-api "/private/edit" :get
             {:path-params   {}
              :header-params {}
              :query-params  {"order_id" order_id "amount" amount "price" price "post_only" post_only "advanced" advanced "stop_price" stop_price }
              :form-params   {}
              :content-types []
              :accepts       ["application/json"]
              :auth-names    ["bearerAuth"]})))

(defn-spec private-edit-get any?
  "Change price, amount and/or other properties of an order."
  ([order_id string?, amount float?, price float?, ] (private-edit-get order_id amount price nil))
  ([order_id string?, amount float?, price float?, optional-params any?]
   (let [res (:data (private-edit-get-with-http-info order_id amount price optional-params))]
     (if (:decode-models *api-context*)
        (st/decode any? res st/string-transformer)
        res))))


(defn-spec private-get-margins-get-with-http-info any?
  "Get margins for given instrument, amount and price."
  [instrument_name string?, amount float?, price float?]
  (check-required-params instrument_name amount price)
  (call-api "/private/get_margins" :get
            {:path-params   {}
             :header-params {}
             :query-params  {"instrument_name" instrument_name "amount" amount "price" price }
             :form-params   {}
             :content-types []
             :accepts       ["application/json"]
             :auth-names    ["bearerAuth"]}))

(defn-spec private-get-margins-get any?
  "Get margins for given instrument, amount and price."
  [instrument_name string?, amount float?, price float?]
  (let [res (:data (private-get-margins-get-with-http-info instrument_name amount price))]
    (if (:decode-models *api-context*)
       (st/decode any? res st/string-transformer)
       res)))


(defn-spec private-get-open-orders-by-currency-get-with-http-info any?
  "Retrieves list of user's open orders."
  ([currency string?, ] (private-get-open-orders-by-currency-get-with-http-info currency nil))
  ([currency string?, {:keys [kind type]} (s/map-of keyword? any?)]
   (check-required-params currency)
   (call-api "/private/get_open_orders_by_currency" :get
             {:path-params   {}
              :header-params {}
              :query-params  {"currency" currency "kind" kind "type" type }
              :form-params   {}
              :content-types []
              :accepts       ["application/json"]
              :auth-names    ["bearerAuth"]})))

(defn-spec private-get-open-orders-by-currency-get any?
  "Retrieves list of user's open orders."
  ([currency string?, ] (private-get-open-orders-by-currency-get currency nil))
  ([currency string?, optional-params any?]
   (let [res (:data (private-get-open-orders-by-currency-get-with-http-info currency optional-params))]
     (if (:decode-models *api-context*)
        (st/decode any? res st/string-transformer)
        res))))


(defn-spec private-get-open-orders-by-instrument-get-with-http-info any?
  "Retrieves list of user's open orders within given Instrument."
  ([instrument_name string?, ] (private-get-open-orders-by-instrument-get-with-http-info instrument_name nil))
  ([instrument_name string?, {:keys [type]} (s/map-of keyword? any?)]
   (check-required-params instrument_name)
   (call-api "/private/get_open_orders_by_instrument" :get
             {:path-params   {}
              :header-params {}
              :query-params  {"instrument_name" instrument_name "type" type }
              :form-params   {}
              :content-types []
              :accepts       ["application/json"]
              :auth-names    ["bearerAuth"]})))

(defn-spec private-get-open-orders-by-instrument-get any?
  "Retrieves list of user's open orders within given Instrument."
  ([instrument_name string?, ] (private-get-open-orders-by-instrument-get instrument_name nil))
  ([instrument_name string?, optional-params any?]
   (let [res (:data (private-get-open-orders-by-instrument-get-with-http-info instrument_name optional-params))]
     (if (:decode-models *api-context*)
        (st/decode any? res st/string-transformer)
        res))))


(defn-spec private-get-order-history-by-currency-get-with-http-info any?
  "Retrieves history of orders that have been partially or fully filled."
  ([currency string?, ] (private-get-order-history-by-currency-get-with-http-info currency nil))
  ([currency string?, {:keys [kind count offset include_old include_unfilled]} (s/map-of keyword? any?)]
   (check-required-params currency)
   (call-api "/private/get_order_history_by_currency" :get
             {:path-params   {}
              :header-params {}
              :query-params  {"currency" currency "kind" kind "count" count "offset" offset "include_old" include_old "include_unfilled" include_unfilled }
              :form-params   {}
              :content-types []
              :accepts       ["application/json"]
              :auth-names    ["bearerAuth"]})))

(defn-spec private-get-order-history-by-currency-get any?
  "Retrieves history of orders that have been partially or fully filled."
  ([currency string?, ] (private-get-order-history-by-currency-get currency nil))
  ([currency string?, optional-params any?]
   (let [res (:data (private-get-order-history-by-currency-get-with-http-info currency optional-params))]
     (if (:decode-models *api-context*)
        (st/decode any? res st/string-transformer)
        res))))


(defn-spec private-get-order-history-by-instrument-get-with-http-info any?
  "Retrieves history of orders that have been partially or fully filled."
  ([instrument_name string?, ] (private-get-order-history-by-instrument-get-with-http-info instrument_name nil))
  ([instrument_name string?, {:keys [count offset include_old include_unfilled]} (s/map-of keyword? any?)]
   (check-required-params instrument_name)
   (call-api "/private/get_order_history_by_instrument" :get
             {:path-params   {}
              :header-params {}
              :query-params  {"instrument_name" instrument_name "count" count "offset" offset "include_old" include_old "include_unfilled" include_unfilled }
              :form-params   {}
              :content-types []
              :accepts       ["application/json"]
              :auth-names    ["bearerAuth"]})))

(defn-spec private-get-order-history-by-instrument-get any?
  "Retrieves history of orders that have been partially or fully filled."
  ([instrument_name string?, ] (private-get-order-history-by-instrument-get instrument_name nil))
  ([instrument_name string?, optional-params any?]
   (let [res (:data (private-get-order-history-by-instrument-get-with-http-info instrument_name optional-params))]
     (if (:decode-models *api-context*)
        (st/decode any? res st/string-transformer)
        res))))


(defn-spec private-get-order-margin-by-ids-get-with-http-info any?
  "Retrieves initial margins of given orders"
  [ids (s/coll-of string?)]
  (check-required-params ids)
  (call-api "/private/get_order_margin_by_ids" :get
            {:path-params   {}
             :header-params {}
             :query-params  {"ids" (with-collection-format ids :multi) }
             :form-params   {}
             :content-types []
             :accepts       ["application/json"]
             :auth-names    ["bearerAuth"]}))

(defn-spec private-get-order-margin-by-ids-get any?
  "Retrieves initial margins of given orders"
  [ids (s/coll-of string?)]
  (let [res (:data (private-get-order-margin-by-ids-get-with-http-info ids))]
    (if (:decode-models *api-context*)
       (st/decode any? res st/string-transformer)
       res)))


(defn-spec private-get-order-state-get-with-http-info any?
  "Retrieve the current state of an order."
  [order_id string?]
  (check-required-params order_id)
  (call-api "/private/get_order_state" :get
            {:path-params   {}
             :header-params {}
             :query-params  {"order_id" order_id }
             :form-params   {}
             :content-types []
             :accepts       ["application/json"]
             :auth-names    ["bearerAuth"]}))

(defn-spec private-get-order-state-get any?
  "Retrieve the current state of an order."
  [order_id string?]
  (let [res (:data (private-get-order-state-get-with-http-info order_id))]
    (if (:decode-models *api-context*)
       (st/decode any? res st/string-transformer)
       res)))


(defn-spec private-get-settlement-history-by-currency-get-with-http-info any?
  "Retrieves settlement, delivery and bankruptcy events that have affected your account."
  ([currency string?, ] (private-get-settlement-history-by-currency-get-with-http-info currency nil))
  ([currency string?, {:keys [type count]} (s/map-of keyword? any?)]
   (check-required-params currency)
   (call-api "/private/get_settlement_history_by_currency" :get
             {:path-params   {}
              :header-params {}
              :query-params  {"currency" currency "type" type "count" count }
              :form-params   {}
              :content-types []
              :accepts       ["application/json"]
              :auth-names    ["bearerAuth"]})))

(defn-spec private-get-settlement-history-by-currency-get any?
  "Retrieves settlement, delivery and bankruptcy events that have affected your account."
  ([currency string?, ] (private-get-settlement-history-by-currency-get currency nil))
  ([currency string?, optional-params any?]
   (let [res (:data (private-get-settlement-history-by-currency-get-with-http-info currency optional-params))]
     (if (:decode-models *api-context*)
        (st/decode any? res st/string-transformer)
        res))))


(defn-spec private-get-settlement-history-by-instrument-get-with-http-info any?
  "Retrieves public settlement, delivery and bankruptcy events filtered by instrument name"
  ([instrument_name string?, ] (private-get-settlement-history-by-instrument-get-with-http-info instrument_name nil))
  ([instrument_name string?, {:keys [type count]} (s/map-of keyword? any?)]
   (check-required-params instrument_name)
   (call-api "/private/get_settlement_history_by_instrument" :get
             {:path-params   {}
              :header-params {}
              :query-params  {"instrument_name" instrument_name "type" type "count" count }
              :form-params   {}
              :content-types []
              :accepts       ["application/json"]
              :auth-names    ["bearerAuth"]})))

(defn-spec private-get-settlement-history-by-instrument-get any?
  "Retrieves public settlement, delivery and bankruptcy events filtered by instrument name"
  ([instrument_name string?, ] (private-get-settlement-history-by-instrument-get instrument_name nil))
  ([instrument_name string?, optional-params any?]
   (let [res (:data (private-get-settlement-history-by-instrument-get-with-http-info instrument_name optional-params))]
     (if (:decode-models *api-context*)
        (st/decode any? res st/string-transformer)
        res))))


(defn-spec private-get-user-trades-by-currency-and-time-get-with-http-info any?
  "Retrieve the latest user trades that have occurred for instruments in a specific currency symbol and within given time range."
  ([currency string?, start_timestamp int?, end_timestamp int?, ] (private-get-user-trades-by-currency-and-time-get-with-http-info currency start_timestamp end_timestamp nil))
  ([currency string?, start_timestamp int?, end_timestamp int?, {:keys [kind count include_old sorting]} (s/map-of keyword? any?)]
   (check-required-params currency start_timestamp end_timestamp)
   (call-api "/private/get_user_trades_by_currency_and_time" :get
             {:path-params   {}
              :header-params {}
              :query-params  {"currency" currency "kind" kind "start_timestamp" start_timestamp "end_timestamp" end_timestamp "count" count "include_old" include_old "sorting" sorting }
              :form-params   {}
              :content-types []
              :accepts       ["application/json"]
              :auth-names    ["bearerAuth"]})))

(defn-spec private-get-user-trades-by-currency-and-time-get any?
  "Retrieve the latest user trades that have occurred for instruments in a specific currency symbol and within given time range."
  ([currency string?, start_timestamp int?, end_timestamp int?, ] (private-get-user-trades-by-currency-and-time-get currency start_timestamp end_timestamp nil))
  ([currency string?, start_timestamp int?, end_timestamp int?, optional-params any?]
   (let [res (:data (private-get-user-trades-by-currency-and-time-get-with-http-info currency start_timestamp end_timestamp optional-params))]
     (if (:decode-models *api-context*)
        (st/decode any? res st/string-transformer)
        res))))


(defn-spec private-get-user-trades-by-currency-get-with-http-info any?
  "Retrieve the latest user trades that have occurred for instruments in a specific currency symbol."
  ([currency string?, ] (private-get-user-trades-by-currency-get-with-http-info currency nil))
  ([currency string?, {:keys [kind start_id end_id count include_old sorting]} (s/map-of keyword? any?)]
   (check-required-params currency)
   (call-api "/private/get_user_trades_by_currency" :get
             {:path-params   {}
              :header-params {}
              :query-params  {"currency" currency "kind" kind "start_id" start_id "end_id" end_id "count" count "include_old" include_old "sorting" sorting }
              :form-params   {}
              :content-types []
              :accepts       ["application/json"]
              :auth-names    ["bearerAuth"]})))

(defn-spec private-get-user-trades-by-currency-get any?
  "Retrieve the latest user trades that have occurred for instruments in a specific currency symbol."
  ([currency string?, ] (private-get-user-trades-by-currency-get currency nil))
  ([currency string?, optional-params any?]
   (let [res (:data (private-get-user-trades-by-currency-get-with-http-info currency optional-params))]
     (if (:decode-models *api-context*)
        (st/decode any? res st/string-transformer)
        res))))


(defn-spec private-get-user-trades-by-instrument-and-time-get-with-http-info any?
  "Retrieve the latest user trades that have occurred for a specific instrument and within given time range."
  ([instrument_name string?, start_timestamp int?, end_timestamp int?, ] (private-get-user-trades-by-instrument-and-time-get-with-http-info instrument_name start_timestamp end_timestamp nil))
  ([instrument_name string?, start_timestamp int?, end_timestamp int?, {:keys [count include_old sorting]} (s/map-of keyword? any?)]
   (check-required-params instrument_name start_timestamp end_timestamp)
   (call-api "/private/get_user_trades_by_instrument_and_time" :get
             {:path-params   {}
              :header-params {}
              :query-params  {"instrument_name" instrument_name "start_timestamp" start_timestamp "end_timestamp" end_timestamp "count" count "include_old" include_old "sorting" sorting }
              :form-params   {}
              :content-types []
              :accepts       ["application/json"]
              :auth-names    ["bearerAuth"]})))

(defn-spec private-get-user-trades-by-instrument-and-time-get any?
  "Retrieve the latest user trades that have occurred for a specific instrument and within given time range."
  ([instrument_name string?, start_timestamp int?, end_timestamp int?, ] (private-get-user-trades-by-instrument-and-time-get instrument_name start_timestamp end_timestamp nil))
  ([instrument_name string?, start_timestamp int?, end_timestamp int?, optional-params any?]
   (let [res (:data (private-get-user-trades-by-instrument-and-time-get-with-http-info instrument_name start_timestamp end_timestamp optional-params))]
     (if (:decode-models *api-context*)
        (st/decode any? res st/string-transformer)
        res))))


(defn-spec private-get-user-trades-by-instrument-get-with-http-info any?
  "Retrieve the latest user trades that have occurred for a specific instrument."
  ([instrument_name string?, ] (private-get-user-trades-by-instrument-get-with-http-info instrument_name nil))
  ([instrument_name string?, {:keys [start_seq end_seq count include_old sorting]} (s/map-of keyword? any?)]
   (check-required-params instrument_name)
   (call-api "/private/get_user_trades_by_instrument" :get
             {:path-params   {}
              :header-params {}
              :query-params  {"instrument_name" instrument_name "start_seq" start_seq "end_seq" end_seq "count" count "include_old" include_old "sorting" sorting }
              :form-params   {}
              :content-types []
              :accepts       ["application/json"]
              :auth-names    ["bearerAuth"]})))

(defn-spec private-get-user-trades-by-instrument-get any?
  "Retrieve the latest user trades that have occurred for a specific instrument."
  ([instrument_name string?, ] (private-get-user-trades-by-instrument-get instrument_name nil))
  ([instrument_name string?, optional-params any?]
   (let [res (:data (private-get-user-trades-by-instrument-get-with-http-info instrument_name optional-params))]
     (if (:decode-models *api-context*)
        (st/decode any? res st/string-transformer)
        res))))


(defn-spec private-get-user-trades-by-order-get-with-http-info any?
  "Retrieve the list of user trades that was created for given order"
  ([order_id string?, ] (private-get-user-trades-by-order-get-with-http-info order_id nil))
  ([order_id string?, {:keys [sorting]} (s/map-of keyword? any?)]
   (check-required-params order_id)
   (call-api "/private/get_user_trades_by_order" :get
             {:path-params   {}
              :header-params {}
              :query-params  {"order_id" order_id "sorting" sorting }
              :form-params   {}
              :content-types []
              :accepts       ["application/json"]
              :auth-names    ["bearerAuth"]})))

(defn-spec private-get-user-trades-by-order-get any?
  "Retrieve the list of user trades that was created for given order"
  ([order_id string?, ] (private-get-user-trades-by-order-get order_id nil))
  ([order_id string?, optional-params any?]
   (let [res (:data (private-get-user-trades-by-order-get-with-http-info order_id optional-params))]
     (if (:decode-models *api-context*)
        (st/decode any? res st/string-transformer)
        res))))


(defn-spec private-sell-get-with-http-info any?
  "Places a sell order for an instrument."
  ([instrument_name string?, amount float?, ] (private-sell-get-with-http-info instrument_name amount nil))
  ([instrument_name string?, amount float?, {:keys [type label price time_in_force max_show post_only reduce_only stop_price trigger advanced]} (s/map-of keyword? any?)]
   (check-required-params instrument_name amount)
   (call-api "/private/sell" :get
             {:path-params   {}
              :header-params {}
              :query-params  {"instrument_name" instrument_name "amount" amount "type" type "label" label "price" price "time_in_force" time_in_force "max_show" max_show "post_only" post_only "reduce_only" reduce_only "stop_price" stop_price "trigger" trigger "advanced" advanced }
              :form-params   {}
              :content-types []
              :accepts       ["application/json"]
              :auth-names    ["bearerAuth"]})))

(defn-spec private-sell-get any?
  "Places a sell order for an instrument."
  ([instrument_name string?, amount float?, ] (private-sell-get instrument_name amount nil))
  ([instrument_name string?, amount float?, optional-params any?]
   (let [res (:data (private-sell-get-with-http-info instrument_name amount optional-params))]
     (if (:decode-models *api-context*)
        (st/decode any? res st/string-transformer)
        res))))


