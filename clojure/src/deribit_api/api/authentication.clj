(ns deribit-api.api.authentication
  (:require [deribit-api.core :refer [call-api check-required-params with-collection-format *api-context*]]
            [clojure.spec.alpha :as s]
            [spec-tools.core :as st]
            [orchestra.core :refer [defn-spec]]
            [deribit-api.specs.public-trade :refer :all]
            [deribit-api.specs.currency-withdrawal-priorities :refer :all]
            [deribit-api.specs.address-book-item :refer :all]
            [deribit-api.specs.types :refer :all]
            [deribit-api.specs.transfer-item :refer :all]
            [deribit-api.specs.order-id-initial-margin-pair :refer :all]
            [deribit-api.specs.key-number-pair :refer :all]
            [deribit-api.specs.trades-volumes :refer :all]
            [deribit-api.specs.instrument :refer :all]
            [deribit-api.specs.withdrawal :refer :all]
            [deribit-api.specs.portfolio-eth :refer :all]
            [deribit-api.specs.settlement :refer :all]
            [deribit-api.specs.user-trade :refer :all]
            [deribit-api.specs.portfolio :refer :all]
            [deribit-api.specs.currency-portfolio :refer :all]
            [deribit-api.specs.deposit :refer :all]
            [deribit-api.specs.book-summary :refer :all]
            [deribit-api.specs.currency :refer :all]
            [deribit-api.specs.position :refer :all]
            [deribit-api.specs.order :refer :all]
            )
  (:import (java.io File)))


(defn-spec public-auth-get-with-http-info any?
  "Authenticate
  Retrieve an Oauth access token, to be used for authentication of 'private' requests.

Three methods of authentication are supported:

- <code>password</code> - using email and and password as when logging on to the website
- <code>client_credentials</code> - using the access key and access secret that can be found on the API page on the website
- <code>client_signature</code> - using the access key that can be found on the API page on the website and user generated signature. The signature is calculated using some fields provided in the request, using formula described here [Deribit signature credentials](#additional-authorization-method-deribit-signature-credentials)
- <code>refresh_token</code> - using a refresh token that was received from an earlier invocation

The response will contain an access token, expiration period (number of seconds that the token is valid) and a refresh token that can
be used to get a new set of tokens."
  ([grant_type string?, username string?, password string?, client_id string?, client_secret string?, refresh_token string?, timestamp string?, signature string?, ] (public-auth-get-with-http-info grant_type username password client_id client_secret refresh_token timestamp signature nil))
  ([grant_type string?, username string?, password string?, client_id string?, client_secret string?, refresh_token string?, timestamp string?, signature string?, {:keys [nonce state scope]} (s/map-of keyword? any?)]
   (check-required-params grant_type username password client_id client_secret refresh_token timestamp signature)
   (call-api "/public/auth" :get
             {:path-params   {}
              :header-params {}
              :query-params  {"grant_type" grant_type "username" username "password" password "client_id" client_id "client_secret" client_secret "refresh_token" refresh_token "timestamp" timestamp "signature" signature "nonce" nonce "state" state "scope" scope }
              :form-params   {}
              :content-types []
              :accepts       ["application/json"]
              :auth-names    ["bearerAuth"]})))

(defn-spec public-auth-get any?
  "Authenticate
  Retrieve an Oauth access token, to be used for authentication of 'private' requests.

Three methods of authentication are supported:

- <code>password</code> - using email and and password as when logging on to the website
- <code>client_credentials</code> - using the access key and access secret that can be found on the API page on the website
- <code>client_signature</code> - using the access key that can be found on the API page on the website and user generated signature. The signature is calculated using some fields provided in the request, using formula described here [Deribit signature credentials](#additional-authorization-method-deribit-signature-credentials)
- <code>refresh_token</code> - using a refresh token that was received from an earlier invocation

The response will contain an access token, expiration period (number of seconds that the token is valid) and a refresh token that can
be used to get a new set of tokens."
  ([grant_type string?, username string?, password string?, client_id string?, client_secret string?, refresh_token string?, timestamp string?, signature string?, ] (public-auth-get grant_type username password client_id client_secret refresh_token timestamp signature nil))
  ([grant_type string?, username string?, password string?, client_id string?, client_secret string?, refresh_token string?, timestamp string?, signature string?, optional-params any?]
   (let [res (:data (public-auth-get-with-http-info grant_type username password client_id client_secret refresh_token timestamp signature optional-params))]
     (if (:decode-models *api-context*)
        (st/decode any? res st/string-transformer)
        res))))


