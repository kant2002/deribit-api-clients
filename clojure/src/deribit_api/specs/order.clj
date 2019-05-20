(ns deribit-api.specs.order
  (:require [clojure.spec.alpha :as s]
            [spec-tools.data-spec :as ds]
            )
  (:import (java.io File)))


(def order-data
  {
   (ds/req :direction) string?
   (ds/opt :reduce_only) boolean?
   (ds/opt :triggered) boolean?
   (ds/req :order_id) string?
   (ds/req :price) float?
   (ds/req :time_in_force) string?
   (ds/req :api) boolean?
   (ds/req :order_state) string?
   (ds/opt :implv) float?
   (ds/opt :advanced) string?
   (ds/req :post_only) boolean?
   (ds/opt :usd) float?
   (ds/opt :stop_price) float?
   (ds/req :order_type) string?
   (ds/req :last_update_timestamp) int?
   (ds/opt :original_order_type) string?
   (ds/req :max_show) float?
   (ds/opt :profit_loss) float?
   (ds/req :is_liquidation) boolean?
   (ds/opt :filled_amount) float?
   (ds/req :label) string?
   (ds/opt :commission) float?
   (ds/opt :amount) float?
   (ds/opt :trigger) string?
   (ds/opt :instrument_name) string?
   (ds/req :creation_timestamp) int?
   (ds/opt :average_price) float?
   })

(def order-spec
  (ds/spec
    {:name ::order
     :spec order-data}))
