(ns deribit-api.specs.settlement
  (:require [clojure.spec.alpha :as s]
            [spec-tools.data-spec :as ds]
            )
  (:import (java.io File)))


(def settlement-data
  {
   (ds/req :session_profit_loss) float?
   (ds/opt :mark_price) float?
   (ds/req :funding) float?
   (ds/opt :socialized) float?
   (ds/opt :session_bankrupcy) float?
   (ds/req :timestamp) int?
   (ds/opt :profit_loss) float?
   (ds/opt :funded) float?
   (ds/req :index_price) float?
   (ds/opt :session_tax) float?
   (ds/opt :session_tax_rate) float?
   (ds/req :instrument_name) string?
   (ds/req :position) float?
   (ds/req :type) string?
   })

(def settlement-spec
  (ds/spec
    {:name ::settlement
     :spec settlement-data}))
