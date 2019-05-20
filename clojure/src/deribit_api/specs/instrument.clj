(ns deribit-api.specs.instrument
  (:require [clojure.spec.alpha :as s]
            [spec-tools.data-spec :as ds]
            )
  (:import (java.io File)))


(def instrument-data
  {
   (ds/req :quote_currency) string?
   (ds/req :kind) string?
   (ds/req :tick_size) float?
   (ds/req :contract_size) int?
   (ds/req :is_active) boolean?
   (ds/opt :option_type) string?
   (ds/req :min_trade_amount) float?
   (ds/req :instrument_name) string?
   (ds/req :settlement_period) string?
   (ds/opt :strike) float?
   (ds/req :base_currency) string?
   (ds/req :creation_timestamp) int?
   (ds/req :expiration_timestamp) int?
   })

(def instrument-spec
  (ds/spec
    {:name ::instrument
     :spec instrument-data}))
