(ns deribit-api.specs.types
  (:require [clojure.spec.alpha :as s]
            [spec-tools.data-spec :as ds]
            )
  (:import (java.io File)))


(def types-data
  {
   })

(def types-spec
  (ds/spec
    {:name ::types
     :spec types-data}))
