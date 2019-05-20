(ns deribit-api.specs.address-book-item
  (:require [clojure.spec.alpha :as s]
            [spec-tools.data-spec :as ds]
            )
  (:import (java.io File)))


(def address-book-item-data
  {
   (ds/req :currency) string?
   (ds/req :address) string?
   (ds/opt :type) string?
   (ds/req :creation_timestamp) int?
   })

(def address-book-item-spec
  (ds/spec
    {:name ::address-book-item
     :spec address-book-item-data}))
