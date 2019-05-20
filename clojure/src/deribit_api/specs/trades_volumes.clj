(ns deribit-api.specs.trades-volumes
  (:require [clojure.spec.alpha :as s]
            [spec-tools.data-spec :as ds]
            )
  (:import (java.io File)))


(def trades-volumes-data
  {
   (ds/req :calls_volume) float?
   (ds/req :puts_volume) float?
   (ds/req :currency_pair) string?
   (ds/req :futures_volume) float?
   })

(def trades-volumes-spec
  (ds/spec
    {:name ::trades-volumes
     :spec trades-volumes-data}))
