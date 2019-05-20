(ns deribit-api.specs.currency-portfolio
  (:require [clojure.spec.alpha :as s]
            [spec-tools.data-spec :as ds]
            )
  (:import (java.io File)))


(def currency-portfolio-data
  {
   (ds/req :maintenance_margin) float?
   (ds/req :available_withdrawal_funds) float?
   (ds/req :initial_margin) float?
   (ds/req :available_funds) float?
   (ds/req :currency) string?
   (ds/req :margin_balance) float?
   (ds/req :equity) float?
   (ds/req :balance) float?
   })

(def currency-portfolio-spec
  (ds/spec
    {:name ::currency-portfolio
     :spec currency-portfolio-data}))
