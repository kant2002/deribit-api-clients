(ns deribit-api.api.supporting
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


