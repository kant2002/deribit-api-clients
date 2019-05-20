(ns deribit-api.specs.user-trade
  (:require [clojure.spec.alpha :as s]
            [spec-tools.data-spec :as ds]
            )
  (:import (java.io File)))


(def user-trade-data
  {
   (ds/req :direction) string?
   (ds/req :fee_currency) string?
   (ds/req :order_id) string?
   (ds/req :timestamp) int?
   (ds/req :price) float?
   (ds/opt :iv) float?
   (ds/req :trade_id) string?
   (ds/req :fee) float?
   (ds/opt :order_type) string?
   (ds/req :trade_seq) int?
   (ds/req :self_trade) boolean?
   (ds/req :state) string?
   (ds/opt :label) string?
   (ds/req :index_price) float?
   (ds/req :amount) float?
   (ds/req :instrument_name) string?
   (ds/req :tick_direction) int?
   (ds/req :matching_id) string?
   (ds/opt :liquidity) string?
   })

(def user-trade-spec
  (ds/spec
    {:name ::user-trade
     :spec user-trade-data}))
