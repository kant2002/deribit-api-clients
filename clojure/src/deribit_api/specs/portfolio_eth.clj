(ns deribit-api.specs.portfolio-eth
  (:require [clojure.spec.alpha :as s]
            [spec-tools.data-spec :as ds]
            )
  (:import (java.io File)))


(def portfolio-eth-data
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

(def portfolio-eth-spec
  (ds/spec
    {:name ::portfolio-eth
     :spec portfolio-eth-data}))
