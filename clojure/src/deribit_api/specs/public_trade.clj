(ns deribit-api.specs.public-trade
  (:require [clojure.spec.alpha :as s]
            [spec-tools.data-spec :as ds]
            )
  (:import (java.io File)))


(def public-trade-data
  {
   (ds/req :direction) string?
   (ds/req :tick_direction) int?
   (ds/req :timestamp) int?
   (ds/req :price) float?
   (ds/req :trade_seq) int?
   (ds/req :trade_id) string?
   (ds/opt :iv) float?
   (ds/req :index_price) float?
   (ds/req :amount) float?
   (ds/req :instrument_name) string?
   })

(def public-trade-spec
  (ds/spec
    {:name ::public-trade
     :spec public-trade-data}))
