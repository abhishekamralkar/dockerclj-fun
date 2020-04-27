(ns dockerclj.dockerclj
  (:gen-class)
  (:require [clj-docker-client.core :as docker]))



(defn client
  "docker connection function"
  ([category uri]
   (docker/client {:category category
                   :conn     {:uri uri}}))
  ([category uri api]
   (docker/client {:category    category
                   :conn        {:uri uri}
                   :api-version api})))


(def images
  (client :images "unix:///var/run/docker.sock"))

(def containers
  (client :containers "unix:///var/run/docker.sock" "v1.40"))

;;where as v1.40  is the docker api

;; What all are options supported by ops
(def ops
  (docker/ops images))

(defn pull-image
  "function pulls the docker images
   for operation see ops supported"

  [operation base-image]
  (docker/invoke images {:op operation
                         :params {:fromImage base-image}}))

(pull-image :ImageCreate "busybox:latest")
