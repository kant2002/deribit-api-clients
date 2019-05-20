(ns deribit-api.specs.transfer-item
  (:require [clojure.spec.alpha :as s]
            [spec-tools.data-spec :as ds]
            )
  (:import (java.io File)))


(def transfer-item-data
  {
   (ds/req :updated_timestamp) int?
   (ds/opt :direction) string?
   (ds/req :amount) float?
   (ds/req :other_side) string?
   (ds/req :currency) string?
   (ds/req :state) string?
   (ds/req :created_timestamp) int?
   (ds/req :type) string?
   (ds/req :id) int?
   })

(def transfer-item-spec
  (ds/spec
    {:name ::transfer-item
     :spec transfer-item-data}))
