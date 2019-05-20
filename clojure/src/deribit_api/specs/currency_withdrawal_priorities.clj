(ns deribit-api.specs.currency-withdrawal-priorities
  (:require [clojure.spec.alpha :as s]
            [spec-tools.data-spec :as ds]
            )
  (:import (java.io File)))


(def currency-withdrawal-priorities-data
  {
   (ds/req :name) string?
   (ds/req :value) float?
   })

(def currency-withdrawal-priorities-spec
  (ds/spec
    {:name ::currency-withdrawal-priorities
     :spec currency-withdrawal-priorities-data}))
