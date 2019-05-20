(ns deribit-api.api.wallet
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


