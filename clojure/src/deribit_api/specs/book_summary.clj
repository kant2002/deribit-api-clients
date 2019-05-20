(ns deribit-api.specs.book-summary
  (:require [clojure.spec.alpha :as s]
            [spec-tools.data-spec :as ds]
            )
  (:import (java.io File)))


(def book-summary-data
  {
   (ds/opt :underlying_index) string?
   (ds/req :volume) float?
   (ds/opt :volume_usd) float?
   (ds/opt :underlying_price) float?
   (ds/req :bid_price) float?
   (ds/req :open_interest) float?
   (ds/req :quote_currency) string?
   (ds/req :high) float?
   (ds/opt :estimated_delivery_price) float?
   (ds/req :last) float?
   (ds/req :mid_price) float?
   (ds/opt :interest_rate) float?
   (ds/opt :funding_8h) float?
   (ds/req :mark_price) float?
   (ds/req :ask_price) float?
   (ds/req :instrument_name) string?
   (ds/req :low) float?
   (ds/opt :base_currency) string?
   (ds/req :creation_timestamp) int?
   (ds/opt :current_funding) float?
   })

(def book-summary-spec
  (ds/spec
    {:name ::book-summary
     :spec book-summary-data}))
