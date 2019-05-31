(ns deribit-api.api.private
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


(defn-spec private-add-to-address-book-get-with-http-info any?
  "Adds new entry to address book of given type"
  ([currency string?, type string?, address string?, name string?, ] (private-add-to-address-book-get-with-http-info currency type address name nil))
  ([currency string?, type string?, address string?, name string?, {:keys [tfa]} (s/map-of keyword? any?)]
   (check-required-params currency type address name)
   (call-api "/private/add_to_address_book" :get
             {:path-params   {}
              :header-params {}
              :query-params  {"currency" currency "type" type "address" address "name" name "tfa" tfa }
              :form-params   {}
              :content-types []
              :accepts       ["application/json"]
              :auth-names    ["bearerAuth"]})))

(defn-spec private-add-to-address-book-get any?
  "Adds new entry to address book of given type"
  ([currency string?, type string?, address string?, name string?, ] (private-add-to-address-book-get currency type address name nil))
  ([currency string?, type string?, address string?, name string?, optional-params any?]
   (let [res (:data (private-add-to-address-book-get-with-http-info currency type address name optional-params))]
     (if (:decode-models *api-context*)
        (st/decode any? res st/string-transformer)
        res))))


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


(defn-spec private-cancel-transfer-by-id-get-with-http-info any?
  "Cancel transfer"
  ([currency string?, id int?, ] (private-cancel-transfer-by-id-get-with-http-info currency id nil))
  ([currency string?, id int?, {:keys [tfa]} (s/map-of keyword? any?)]
   (check-required-params currency id)
   (call-api "/private/cancel_transfer_by_id" :get
             {:path-params   {}
              :header-params {}
              :query-params  {"currency" currency "id" id "tfa" tfa }
              :form-params   {}
              :content-types []
              :accepts       ["application/json"]
              :auth-names    ["bearerAuth"]})))

(defn-spec private-cancel-transfer-by-id-get any?
  "Cancel transfer"
  ([currency string?, id int?, ] (private-cancel-transfer-by-id-get currency id nil))
  ([currency string?, id int?, optional-params any?]
   (let [res (:data (private-cancel-transfer-by-id-get-with-http-info currency id optional-params))]
     (if (:decode-models *api-context*)
        (st/decode any? res st/string-transformer)
        res))))


(defn-spec private-cancel-withdrawal-get-with-http-info any?
  "Cancels withdrawal request"
  [currency string?, id float?]
  (check-required-params currency id)
  (call-api "/private/cancel_withdrawal" :get
            {:path-params   {}
             :header-params {}
             :query-params  {"currency" currency "id" id }
             :form-params   {}
             :content-types []
             :accepts       ["application/json"]
             :auth-names    ["bearerAuth"]}))

(defn-spec private-cancel-withdrawal-get any?
  "Cancels withdrawal request"
  [currency string?, id float?]
  (let [res (:data (private-cancel-withdrawal-get-with-http-info currency id))]
    (if (:decode-models *api-context*)
       (st/decode any? res st/string-transformer)
       res)))


(defn-spec private-change-subaccount-name-get-with-http-info any?
  "Change the user name for a subaccount"
  [sid int?, name string?]
  (check-required-params sid name)
  (call-api "/private/change_subaccount_name" :get
            {:path-params   {}
             :header-params {}
             :query-params  {"sid" sid "name" name }
             :form-params   {}
             :content-types []
             :accepts       ["application/json"]
             :auth-names    ["bearerAuth"]}))

(defn-spec private-change-subaccount-name-get any?
  "Change the user name for a subaccount"
  [sid int?, name string?]
  (let [res (:data (private-change-subaccount-name-get-with-http-info sid name))]
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


(defn-spec private-create-deposit-address-get-with-http-info any?
  "Creates deposit address in currency"
  [currency string?]
  (check-required-params currency)
  (call-api "/private/create_deposit_address" :get
            {:path-params   {}
             :header-params {}
             :query-params  {"currency" currency }
             :form-params   {}
             :content-types []
             :accepts       ["application/json"]
             :auth-names    ["bearerAuth"]}))

(defn-spec private-create-deposit-address-get any?
  "Creates deposit address in currency"
  [currency string?]
  (let [res (:data (private-create-deposit-address-get-with-http-info currency))]
    (if (:decode-models *api-context*)
       (st/decode any? res st/string-transformer)
       res)))


(defn-spec private-create-subaccount-get-with-http-info any?
  "Create a new subaccount"
  []
  (call-api "/private/create_subaccount" :get
            {:path-params   {}
             :header-params {}
             :query-params  {}
             :form-params   {}
             :content-types []
             :accepts       ["application/json"]
             :auth-names    ["bearerAuth"]}))

(defn-spec private-create-subaccount-get any?
  "Create a new subaccount"
  []
  (let [res (:data (private-create-subaccount-get-with-http-info))]
    (if (:decode-models *api-context*)
       (st/decode any? res st/string-transformer)
       res)))


(defn-spec private-disable-tfa-for-subaccount-get-with-http-info any?
  "Disable two factor authentication for a subaccount."
  [sid int?]
  (check-required-params sid)
  (call-api "/private/disable_tfa_for_subaccount" :get
            {:path-params   {}
             :header-params {}
             :query-params  {"sid" sid }
             :form-params   {}
             :content-types []
             :accepts       ["application/json"]
             :auth-names    ["bearerAuth"]}))

(defn-spec private-disable-tfa-for-subaccount-get any?
  "Disable two factor authentication for a subaccount."
  [sid int?]
  (let [res (:data (private-disable-tfa-for-subaccount-get-with-http-info sid))]
    (if (:decode-models *api-context*)
       (st/decode any? res st/string-transformer)
       res)))


(defn-spec private-disable-tfa-with-recovery-code-get-with-http-info any?
  "Disables TFA with one time recovery code"
  [password string?, code string?]
  (check-required-params password code)
  (call-api "/private/disable_tfa_with_recovery_code" :get
            {:path-params   {}
             :header-params {}
             :query-params  {"password" password "code" code }
             :form-params   {}
             :content-types []
             :accepts       ["application/json"]
             :auth-names    ["bearerAuth"]}))

(defn-spec private-disable-tfa-with-recovery-code-get any?
  "Disables TFA with one time recovery code"
  [password string?, code string?]
  (let [res (:data (private-disable-tfa-with-recovery-code-get-with-http-info password code))]
    (if (:decode-models *api-context*)
       (st/decode any? res st/string-transformer)
       res)))


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


(defn-spec private-get-account-summary-get-with-http-info any?
  "Retrieves user account summary."
  ([currency string?, ] (private-get-account-summary-get-with-http-info currency nil))
  ([currency string?, {:keys [extended]} (s/map-of keyword? any?)]
   (check-required-params currency)
   (call-api "/private/get_account_summary" :get
             {:path-params   {}
              :header-params {}
              :query-params  {"currency" currency "extended" extended }
              :form-params   {}
              :content-types []
              :accepts       ["application/json"]
              :auth-names    ["bearerAuth"]})))

(defn-spec private-get-account-summary-get any?
  "Retrieves user account summary."
  ([currency string?, ] (private-get-account-summary-get currency nil))
  ([currency string?, optional-params any?]
   (let [res (:data (private-get-account-summary-get-with-http-info currency optional-params))]
     (if (:decode-models *api-context*)
        (st/decode any? res st/string-transformer)
        res))))


(defn-spec private-get-address-book-get-with-http-info any?
  "Retrieves address book of given type"
  [currency string?, type string?]
  (check-required-params currency type)
  (call-api "/private/get_address_book" :get
            {:path-params   {}
             :header-params {}
             :query-params  {"currency" currency "type" type }
             :form-params   {}
             :content-types []
             :accepts       ["application/json"]
             :auth-names    ["bearerAuth"]}))

(defn-spec private-get-address-book-get any?
  "Retrieves address book of given type"
  [currency string?, type string?]
  (let [res (:data (private-get-address-book-get-with-http-info currency type))]
    (if (:decode-models *api-context*)
       (st/decode any? res st/string-transformer)
       res)))


(defn-spec private-get-current-deposit-address-get-with-http-info any?
  "Retrieve deposit address for currency"
  [currency string?]
  (check-required-params currency)
  (call-api "/private/get_current_deposit_address" :get
            {:path-params   {}
             :header-params {}
             :query-params  {"currency" currency }
             :form-params   {}
             :content-types []
             :accepts       ["application/json"]
             :auth-names    ["bearerAuth"]}))

(defn-spec private-get-current-deposit-address-get any?
  "Retrieve deposit address for currency"
  [currency string?]
  (let [res (:data (private-get-current-deposit-address-get-with-http-info currency))]
    (if (:decode-models *api-context*)
       (st/decode any? res st/string-transformer)
       res)))


(defn-spec private-get-deposits-get-with-http-info any?
  "Retrieve the latest users deposits"
  ([currency string?, ] (private-get-deposits-get-with-http-info currency nil))
  ([currency string?, {:keys [count offset]} (s/map-of keyword? any?)]
   (check-required-params currency)
   (call-api "/private/get_deposits" :get
             {:path-params   {}
              :header-params {}
              :query-params  {"currency" currency "count" count "offset" offset }
              :form-params   {}
              :content-types []
              :accepts       ["application/json"]
              :auth-names    ["bearerAuth"]})))

(defn-spec private-get-deposits-get any?
  "Retrieve the latest users deposits"
  ([currency string?, ] (private-get-deposits-get currency nil))
  ([currency string?, optional-params any?]
   (let [res (:data (private-get-deposits-get-with-http-info currency optional-params))]
     (if (:decode-models *api-context*)
        (st/decode any? res st/string-transformer)
        res))))


(defn-spec private-get-email-language-get-with-http-info any?
  "Retrieves the language to be used for emails."
  []
  (call-api "/private/get_email_language" :get
            {:path-params   {}
             :header-params {}
             :query-params  {}
             :form-params   {}
             :content-types []
             :accepts       ["application/json"]
             :auth-names    ["bearerAuth"]}))

(defn-spec private-get-email-language-get any?
  "Retrieves the language to be used for emails."
  []
  (let [res (:data (private-get-email-language-get-with-http-info))]
    (if (:decode-models *api-context*)
       (st/decode any? res st/string-transformer)
       res)))


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


(defn-spec private-get-new-announcements-get-with-http-info any?
  "Retrieves announcements that have not been marked read by the user."
  []
  (call-api "/private/get_new_announcements" :get
            {:path-params   {}
             :header-params {}
             :query-params  {}
             :form-params   {}
             :content-types []
             :accepts       ["application/json"]
             :auth-names    ["bearerAuth"]}))

(defn-spec private-get-new-announcements-get any?
  "Retrieves announcements that have not been marked read by the user."
  []
  (let [res (:data (private-get-new-announcements-get-with-http-info))]
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


(defn-spec private-get-position-get-with-http-info any?
  "Retrieve user position."
  [instrument_name string?]
  (check-required-params instrument_name)
  (call-api "/private/get_position" :get
            {:path-params   {}
             :header-params {}
             :query-params  {"instrument_name" instrument_name }
             :form-params   {}
             :content-types []
             :accepts       ["application/json"]
             :auth-names    ["bearerAuth"]}))

(defn-spec private-get-position-get any?
  "Retrieve user position."
  [instrument_name string?]
  (let [res (:data (private-get-position-get-with-http-info instrument_name))]
    (if (:decode-models *api-context*)
       (st/decode any? res st/string-transformer)
       res)))


(defn-spec private-get-positions-get-with-http-info any?
  "Retrieve user positions."
  ([currency string?, ] (private-get-positions-get-with-http-info currency nil))
  ([currency string?, {:keys [kind]} (s/map-of keyword? any?)]
   (check-required-params currency)
   (call-api "/private/get_positions" :get
             {:path-params   {}
              :header-params {}
              :query-params  {"currency" currency "kind" kind }
              :form-params   {}
              :content-types []
              :accepts       ["application/json"]
              :auth-names    ["bearerAuth"]})))

(defn-spec private-get-positions-get any?
  "Retrieve user positions."
  ([currency string?, ] (private-get-positions-get currency nil))
  ([currency string?, optional-params any?]
   (let [res (:data (private-get-positions-get-with-http-info currency optional-params))]
     (if (:decode-models *api-context*)
        (st/decode any? res st/string-transformer)
        res))))


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


(defn-spec private-get-subaccounts-get-with-http-info any?
  "Get information about subaccounts"
  ([] (private-get-subaccounts-get-with-http-info nil))
  ([{:keys [with_portfolio]} (s/map-of keyword? any?)]
   (call-api "/private/get_subaccounts" :get
             {:path-params   {}
              :header-params {}
              :query-params  {"with_portfolio" with_portfolio }
              :form-params   {}
              :content-types []
              :accepts       ["application/json"]
              :auth-names    ["bearerAuth"]})))

(defn-spec private-get-subaccounts-get any?
  "Get information about subaccounts"
  ([] (private-get-subaccounts-get nil))
  ([optional-params any?]
   (let [res (:data (private-get-subaccounts-get-with-http-info optional-params))]
     (if (:decode-models *api-context*)
        (st/decode any? res st/string-transformer)
        res))))


(defn-spec private-get-transfers-get-with-http-info any?
  "Adds new entry to address book of given type"
  ([currency string?, ] (private-get-transfers-get-with-http-info currency nil))
  ([currency string?, {:keys [count offset]} (s/map-of keyword? any?)]
   (check-required-params currency)
   (call-api "/private/get_transfers" :get
             {:path-params   {}
              :header-params {}
              :query-params  {"currency" currency "count" count "offset" offset }
              :form-params   {}
              :content-types []
              :accepts       ["application/json"]
              :auth-names    ["bearerAuth"]})))

(defn-spec private-get-transfers-get any?
  "Adds new entry to address book of given type"
  ([currency string?, ] (private-get-transfers-get currency nil))
  ([currency string?, optional-params any?]
   (let [res (:data (private-get-transfers-get-with-http-info currency optional-params))]
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


(defn-spec private-get-withdrawals-get-with-http-info any?
  "Retrieve the latest users withdrawals"
  ([currency string?, ] (private-get-withdrawals-get-with-http-info currency nil))
  ([currency string?, {:keys [count offset]} (s/map-of keyword? any?)]
   (check-required-params currency)
   (call-api "/private/get_withdrawals" :get
             {:path-params   {}
              :header-params {}
              :query-params  {"currency" currency "count" count "offset" offset }
              :form-params   {}
              :content-types []
              :accepts       ["application/json"]
              :auth-names    ["bearerAuth"]})))

(defn-spec private-get-withdrawals-get any?
  "Retrieve the latest users withdrawals"
  ([currency string?, ] (private-get-withdrawals-get currency nil))
  ([currency string?, optional-params any?]
   (let [res (:data (private-get-withdrawals-get-with-http-info currency optional-params))]
     (if (:decode-models *api-context*)
        (st/decode any? res st/string-transformer)
        res))))


(defn-spec private-remove-from-address-book-get-with-http-info any?
  "Adds new entry to address book of given type"
  ([currency string?, type string?, address string?, ] (private-remove-from-address-book-get-with-http-info currency type address nil))
  ([currency string?, type string?, address string?, {:keys [tfa]} (s/map-of keyword? any?)]
   (check-required-params currency type address)
   (call-api "/private/remove_from_address_book" :get
             {:path-params   {}
              :header-params {}
              :query-params  {"currency" currency "type" type "address" address "tfa" tfa }
              :form-params   {}
              :content-types []
              :accepts       ["application/json"]
              :auth-names    ["bearerAuth"]})))

(defn-spec private-remove-from-address-book-get any?
  "Adds new entry to address book of given type"
  ([currency string?, type string?, address string?, ] (private-remove-from-address-book-get currency type address nil))
  ([currency string?, type string?, address string?, optional-params any?]
   (let [res (:data (private-remove-from-address-book-get-with-http-info currency type address optional-params))]
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


(defn-spec private-set-announcement-as-read-get-with-http-info any?
  "Marks an announcement as read, so it will not be shown in `get_new_announcements`."
  [announcement_id float?]
  (check-required-params announcement_id)
  (call-api "/private/set_announcement_as_read" :get
            {:path-params   {}
             :header-params {}
             :query-params  {"announcement_id" announcement_id }
             :form-params   {}
             :content-types []
             :accepts       ["application/json"]
             :auth-names    ["bearerAuth"]}))

(defn-spec private-set-announcement-as-read-get any?
  "Marks an announcement as read, so it will not be shown in `get_new_announcements`."
  [announcement_id float?]
  (let [res (:data (private-set-announcement-as-read-get-with-http-info announcement_id))]
    (if (:decode-models *api-context*)
       (st/decode any? res st/string-transformer)
       res)))


(defn-spec private-set-email-for-subaccount-get-with-http-info any?
  "Assign an email address to a subaccount. User will receive an email with confirmation link."
  [sid int?, email string?]
  (check-required-params sid email)
  (call-api "/private/set_email_for_subaccount" :get
            {:path-params   {}
             :header-params {}
             :query-params  {"sid" sid "email" email }
             :form-params   {}
             :content-types []
             :accepts       ["application/json"]
             :auth-names    ["bearerAuth"]}))

(defn-spec private-set-email-for-subaccount-get any?
  "Assign an email address to a subaccount. User will receive an email with confirmation link."
  [sid int?, email string?]
  (let [res (:data (private-set-email-for-subaccount-get-with-http-info sid email))]
    (if (:decode-models *api-context*)
       (st/decode any? res st/string-transformer)
       res)))


(defn-spec private-set-email-language-get-with-http-info any?
  "Changes the language to be used for emails."
  [language string?]
  (check-required-params language)
  (call-api "/private/set_email_language" :get
            {:path-params   {}
             :header-params {}
             :query-params  {"language" language }
             :form-params   {}
             :content-types []
             :accepts       ["application/json"]
             :auth-names    ["bearerAuth"]}))

(defn-spec private-set-email-language-get any?
  "Changes the language to be used for emails."
  [language string?]
  (let [res (:data (private-set-email-language-get-with-http-info language))]
    (if (:decode-models *api-context*)
       (st/decode any? res st/string-transformer)
       res)))


(defn-spec private-set-password-for-subaccount-get-with-http-info any?
  "Set the password for the subaccount"
  [sid int?, password string?]
  (check-required-params sid password)
  (call-api "/private/set_password_for_subaccount" :get
            {:path-params   {}
             :header-params {}
             :query-params  {"sid" sid "password" password }
             :form-params   {}
             :content-types []
             :accepts       ["application/json"]
             :auth-names    ["bearerAuth"]}))

(defn-spec private-set-password-for-subaccount-get any?
  "Set the password for the subaccount"
  [sid int?, password string?]
  (let [res (:data (private-set-password-for-subaccount-get-with-http-info sid password))]
    (if (:decode-models *api-context*)
       (st/decode any? res st/string-transformer)
       res)))


(defn-spec private-submit-transfer-to-subaccount-get-with-http-info any?
  "Transfer funds to a subaccount."
  [currency string?, amount float?, destination int?]
  (check-required-params currency amount destination)
  (call-api "/private/submit_transfer_to_subaccount" :get
            {:path-params   {}
             :header-params {}
             :query-params  {"currency" currency "amount" amount "destination" destination }
             :form-params   {}
             :content-types []
             :accepts       ["application/json"]
             :auth-names    ["bearerAuth"]}))

(defn-spec private-submit-transfer-to-subaccount-get any?
  "Transfer funds to a subaccount."
  [currency string?, amount float?, destination int?]
  (let [res (:data (private-submit-transfer-to-subaccount-get-with-http-info currency amount destination))]
    (if (:decode-models *api-context*)
       (st/decode any? res st/string-transformer)
       res)))


(defn-spec private-submit-transfer-to-user-get-with-http-info any?
  "Transfer funds to a another user."
  ([currency string?, amount float?, destination string?, ] (private-submit-transfer-to-user-get-with-http-info currency amount destination nil))
  ([currency string?, amount float?, destination string?, {:keys [tfa]} (s/map-of keyword? any?)]
   (check-required-params currency amount destination)
   (call-api "/private/submit_transfer_to_user" :get
             {:path-params   {}
              :header-params {}
              :query-params  {"currency" currency "amount" amount "destination" destination "tfa" tfa }
              :form-params   {}
              :content-types []
              :accepts       ["application/json"]
              :auth-names    ["bearerAuth"]})))

(defn-spec private-submit-transfer-to-user-get any?
  "Transfer funds to a another user."
  ([currency string?, amount float?, destination string?, ] (private-submit-transfer-to-user-get currency amount destination nil))
  ([currency string?, amount float?, destination string?, optional-params any?]
   (let [res (:data (private-submit-transfer-to-user-get-with-http-info currency amount destination optional-params))]
     (if (:decode-models *api-context*)
        (st/decode any? res st/string-transformer)
        res))))


(defn-spec private-toggle-deposit-address-creation-get-with-http-info any?
  "Enable or disable deposit address creation"
  [currency string?, state boolean?]
  (check-required-params currency state)
  (call-api "/private/toggle_deposit_address_creation" :get
            {:path-params   {}
             :header-params {}
             :query-params  {"currency" currency "state" state }
             :form-params   {}
             :content-types []
             :accepts       ["application/json"]
             :auth-names    ["bearerAuth"]}))

(defn-spec private-toggle-deposit-address-creation-get any?
  "Enable or disable deposit address creation"
  [currency string?, state boolean?]
  (let [res (:data (private-toggle-deposit-address-creation-get-with-http-info currency state))]
    (if (:decode-models *api-context*)
       (st/decode any? res st/string-transformer)
       res)))


(defn-spec private-toggle-notifications-from-subaccount-get-with-http-info any?
  "Enable or disable sending of notifications for the subaccount."
  [sid int?, state boolean?]
  (check-required-params sid state)
  (call-api "/private/toggle_notifications_from_subaccount" :get
            {:path-params   {}
             :header-params {}
             :query-params  {"sid" sid "state" state }
             :form-params   {}
             :content-types []
             :accepts       ["application/json"]
             :auth-names    ["bearerAuth"]}))

(defn-spec private-toggle-notifications-from-subaccount-get any?
  "Enable or disable sending of notifications for the subaccount."
  [sid int?, state boolean?]
  (let [res (:data (private-toggle-notifications-from-subaccount-get-with-http-info sid state))]
    (if (:decode-models *api-context*)
       (st/decode any? res st/string-transformer)
       res)))


(defn-spec private-toggle-subaccount-login-get-with-http-info any?
  "Enable or disable login for a subaccount. If login is disabled and a session for the subaccount exists, this session will be terminated."
  [sid int?, state string?]
  (check-required-params sid state)
  (call-api "/private/toggle_subaccount_login" :get
            {:path-params   {}
             :header-params {}
             :query-params  {"sid" sid "state" state }
             :form-params   {}
             :content-types []
             :accepts       ["application/json"]
             :auth-names    ["bearerAuth"]}))

(defn-spec private-toggle-subaccount-login-get any?
  "Enable or disable login for a subaccount. If login is disabled and a session for the subaccount exists, this session will be terminated."
  [sid int?, state string?]
  (let [res (:data (private-toggle-subaccount-login-get-with-http-info sid state))]
    (if (:decode-models *api-context*)
       (st/decode any? res st/string-transformer)
       res)))


(defn-spec private-withdraw-get-with-http-info any?
  "Creates a new withdrawal request"
  ([currency string?, address string?, amount float?, ] (private-withdraw-get-with-http-info currency address amount nil))
  ([currency string?, address string?, amount float?, {:keys [priority tfa]} (s/map-of keyword? any?)]
   (check-required-params currency address amount)
   (call-api "/private/withdraw" :get
             {:path-params   {}
              :header-params {}
              :query-params  {"currency" currency "address" address "amount" amount "priority" priority "tfa" tfa }
              :form-params   {}
              :content-types []
              :accepts       ["application/json"]
              :auth-names    ["bearerAuth"]})))

(defn-spec private-withdraw-get any?
  "Creates a new withdrawal request"
  ([currency string?, address string?, amount float?, ] (private-withdraw-get currency address amount nil))
  ([currency string?, address string?, amount float?, optional-params any?]
   (let [res (:data (private-withdraw-get-with-http-info currency address amount optional-params))]
     (if (:decode-models *api-context*)
        (st/decode any? res st/string-transformer)
        res))))


