(ns deribit-api.specs.position
  (:require [clojure.spec.alpha :as s]
            [spec-tools.data-spec :as ds]
            )
  (:import (java.io File)))


(def position-data
  {
   (ds/req :direction) string?
   (ds/opt :average_price_usd) float?
   (ds/opt :estimated_liquidation_price) float?
   (ds/req :floating_profit_loss) float?
   (ds/opt :floating_profit_loss_usd) float?
   (ds/req :open_orders_margin) float?
   (ds/req :total_profit_loss) float?
   (ds/opt :realized_profit_loss) float?
   (ds/req :delta) float?
   (ds/req :initial_margin) float?
   (ds/req :size) float?
   (ds/req :maintenance_margin) float?
   (ds/req :kind) string?
   (ds/req :mark_price) float?
   (ds/req :average_price) float?
   (ds/req :settlement_price) float?
   (ds/req :index_price) float?
   (ds/req :instrument_name) string?
   (ds/opt :size_currency) float?
   })

(def position-spec
  (ds/spec
    {:name ::position
     :spec position-data}))
