(ns deribit-api.specs.order-id-initial-margin-pair
  (:require [clojure.spec.alpha :as s]
            [spec-tools.data-spec :as ds]
            )
  (:import (java.io File)))


(def order-id-initial-margin-pair-data
  {
   (ds/req :order_id) string?
   (ds/req :initial_margin) float?
   })

(def order-id-initial-margin-pair-spec
  (ds/spec
    {:name ::order-id-initial-margin-pair
     :spec order-id-initial-margin-pair-data}))
