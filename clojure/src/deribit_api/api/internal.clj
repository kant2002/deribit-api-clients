(ns deribit-api.api.internal
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


(defn-spec public-get-footer-get-with-http-info any?
  "Get information to be displayed in the footer of the website."
  []
  (call-api "/public/get_footer" :get
            {:path-params   {}
             :header-params {}
             :query-params  {}
             :form-params   {}
             :content-types []
             :accepts       ["application/json"]
             :auth-names    ["bearerAuth"]}))

(defn-spec public-get-footer-get any?
  "Get information to be displayed in the footer of the website."
  []
  (let [res (:data (public-get-footer-get-with-http-info))]
    (if (:decode-models *api-context*)
       (st/decode any? res st/string-transformer)
       res)))


(defn-spec public-get-option-mark-prices-get-with-http-info any?
  "Retrives market prices and its implied volatility of options instruments"
  [currency string?]
  (check-required-params currency)
  (call-api "/public/get_option_mark_prices" :get
            {:path-params   {}
             :header-params {}
             :query-params  {"currency" currency }
             :form-params   {}
             :content-types []
             :accepts       ["application/json"]
             :auth-names    ["bearerAuth"]}))

(defn-spec public-get-option-mark-prices-get any?
  "Retrives market prices and its implied volatility of options instruments"
  [currency string?]
  (let [res (:data (public-get-option-mark-prices-get-with-http-info currency))]
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


