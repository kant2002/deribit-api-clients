(ns deribit-api.specs.withdrawal
  (:require [clojure.spec.alpha :as s]
            [spec-tools.data-spec :as ds]
            )
  (:import (java.io File)))


(def withdrawal-data
  {
   (ds/req :updated_timestamp) int?
   (ds/opt :fee) float?
   (ds/opt :confirmed_timestamp) int?
   (ds/req :amount) float?
   (ds/opt :priority) float?
   (ds/req :currency) string?
   (ds/req :state) string?
   (ds/req :address) string?
   (ds/opt :created_timestamp) int?
   (ds/opt :id) int?
   (ds/req :transaction_id) string?
   })

(def withdrawal-spec
  (ds/spec
    {:name ::withdrawal
     :spec withdrawal-data}))
