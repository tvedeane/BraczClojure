(defproject bracz-clojure "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.10.0"]
                 ;[org.clojure/tools.nrepl "0.2.13"]
                 [compojure "1.6.1"]
                 [ring/ring-jetty-adapter "1.7.1"]
                 [ring/ring-defaults "0.3.2"]
                 [environ "1.1.0"]]
  :plugins [[environ/environ.lein "0.3.1"]
            [lein-ring "0.12.5"]]
  :ring {:handler bracz-clojure.handler/app}
  :hooks [environ.leiningen.hooks]
  :uberjar-name "bracz-clojure-standalone.jar"
  :profiles {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                                  [ring/ring-mock "0.3.2"]]}
             :production {:env {:production true}}})
