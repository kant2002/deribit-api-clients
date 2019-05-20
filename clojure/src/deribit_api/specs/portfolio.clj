(ns deribit-api.specs.portfolio
  (:require [clojure.spec.alpha :as s]
            [spec-tools.data-spec :as ds]
            [deribit-api.specs.portfolio-eth :refer :all]
            [deribit-api.specs.portfolio-eth :refer :all]
            )
  (:import (java.io File)))


(def portfolio-data
  {
   (ds/req :eth) portfolio-eth-spec
   (ds/req :btc) portfolio-eth-spec
   })

(def portfolio-spec
  (ds/spec
    {:name ::portfolio
     :spec portfolio-data}))
