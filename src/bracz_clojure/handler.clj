(ns bracz-clojure.handler
  (:require [compojure.core :refer :all]
            [compojure.handler :refer [site]]
            [compojure.route :as route]
            [ring.util.response :as resp]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [clojure.java.io :as io]
            [ring.adapter.jetty :as jetty]
            [environ.core :refer [env]]))

(defroutes app-routes
  (GET "/" [] (resp/redirect "/index.html"))
  (GET "/hello" [] "Hello!")
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes site-defaults))

(defn -main [& [port]]
  (let [port (Integer. (or port (env :port) 5000))]
    (jetty/run-jetty (site #'app) {:port port :join? false})))
