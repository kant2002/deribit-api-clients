(ns deribit-api.api.account-management
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


