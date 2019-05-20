(ns deribit-api.specs.deposit
  (:require [clojure.spec.alpha :as s]
            [spec-tools.data-spec :as ds]
            )
  (:import (java.io File)))


(def deposit-data
  {
   (ds/req :updated_timestamp) int?
   (ds/req :state) string?
   (ds/req :received_timestamp) int?
   (ds/req :currency) string?
   (ds/req :address) string?
   (ds/req :amount) float?
   (ds/req :transaction_id) string?
   })

(def deposit-spec
  (ds/spec
    {:name ::deposit
     :spec deposit-data}))
