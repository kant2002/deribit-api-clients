(ns deribit-api.specs.currency
  (:require [clojure.spec.alpha :as s]
            [spec-tools.data-spec :as ds]
            [deribit-api.specs.currency-withdrawal-priorities :refer :all]
            )
  (:import (java.io File)))


(def currency-data
  {
   (ds/opt :min_confirmations) int?
   (ds/opt :min_withdrawal_fee) float?
   (ds/opt :disabled_deposit_address_creation) boolean?
   (ds/req :currency) string?
   (ds/req :currency_long) string?
   (ds/req :withdrawal_fee) float?
   (ds/opt :fee_precision) int?
   (ds/opt :withdrawal_priorities) (s/coll-of currency-withdrawal-priorities-spec)
   (ds/req :coin_type) string?
   })

(def currency-spec
  (ds/spec
    {:name ::currency
     :spec currency-data}))
