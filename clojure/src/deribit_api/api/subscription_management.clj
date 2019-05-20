(ns deribit-api.api.subscription-management
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


(defn-spec private-subscribe-get-with-http-info any?
  "Subscribe to one or more channels.
  Subscribe to one or more channels.

The name of the channel determines what information will be provided, and
in what form."
  [channels (s/coll-of string?)]
  (check-required-params channels)
  (call-api "/private/subscribe" :get
            {:path-params   {}
             :header-params {}
             :query-params  {"channels" (with-collection-format channels :multi) }
             :form-params   {}
             :content-types []
             :accepts       ["application/json"]
             :auth-names    ["bearerAuth"]}))

(defn-spec private-subscribe-get any?
  "Subscribe to one or more channels.
  Subscribe to one or more channels.

The name of the channel determines what information will be provided, and
in what form."
  [channels (s/coll-of string?)]
  (let [res (:data (private-subscribe-get-with-http-info channels))]
    (if (:decode-models *api-context*)
       (st/decode any? res st/string-transformer)
       res)))


(defn-spec private-unsubscribe-get-with-http-info any?
  "Unsubscribe from one or more channels."
  [channels (s/coll-of string?)]
  (check-required-params channels)
  (call-api "/private/unsubscribe" :get
            {:path-params   {}
             :header-params {}
             :query-params  {"channels" (with-collection-format channels :multi) }
             :form-params   {}
             :content-types []
             :accepts       ["application/json"]
             :auth-names    ["bearerAuth"]}))

(defn-spec private-unsubscribe-get any?
  "Unsubscribe from one or more channels."
  [channels (s/coll-of string?)]
  (let [res (:data (private-unsubscribe-get-with-http-info channels))]
    (if (:decode-models *api-context*)
       (st/decode any? res st/string-transformer)
       res)))


(defn-spec public-subscribe-get-with-http-info any?
  "Subscribe to one or more channels.
  Subscribe to one or more channels.

This is the same method as [/private/subscribe](#private_subscribe), but it can only
be used for 'public' channels."
  [channels (s/coll-of string?)]
  (check-required-params channels)
  (call-api "/public/subscribe" :get
            {:path-params   {}
             :header-params {}
             :query-params  {"channels" (with-collection-format channels :multi) }
             :form-params   {}
             :content-types []
             :accepts       ["application/json"]
             :auth-names    ["bearerAuth"]}))

(defn-spec public-subscribe-get any?
  "Subscribe to one or more channels.
  Subscribe to one or more channels.

This is the same method as [/private/subscribe](#private_subscribe), but it can only
be used for 'public' channels."
  [channels (s/coll-of string?)]
  (let [res (:data (public-subscribe-get-with-http-info channels))]
    (if (:decode-models *api-context*)
       (st/decode any? res st/string-transformer)
       res)))


(defn-spec public-unsubscribe-get-with-http-info any?
  "Unsubscribe from one or more channels."
  [channels (s/coll-of string?)]
  (check-required-params channels)
  (call-api "/public/unsubscribe" :get
            {:path-params   {}
             :header-params {}
             :query-params  {"channels" (with-collection-format channels :multi) }
             :form-params   {}
             :content-types []
             :accepts       ["application/json"]
             :auth-names    ["bearerAuth"]}))

(defn-spec public-unsubscribe-get any?
  "Unsubscribe from one or more channels."
  [channels (s/coll-of string?)]
  (let [res (:data (public-unsubscribe-get-with-http-info channels))]
    (if (:decode-models *api-context*)
       (st/decode any? res st/string-transformer)
       res)))


