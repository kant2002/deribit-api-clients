(ns deribit-api.specs.key-number-pair
  (:require [clojure.spec.alpha :as s]
            [spec-tools.data-spec :as ds]
            )
  (:import (java.io File)))


(def key-number-pair-data
  {
   (ds/req :name) string?
   (ds/req :value) float?
   })

(def key-number-pair-spec
  (ds/spec
    {:name ::key-number-pair
     :spec key-number-pair-data}))
