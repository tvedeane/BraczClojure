(ns bracz-clojure.scraper
  (:require [net.cgrand.enlive-html :refer :all]))

(def search-url
  "https://br-hip.pfsl.poznan.pl/ipac20/ipac.jsp?menu=search&aspect=basic_search&npp=10&ipp=20&spp=20&profile=br-mar&ri=&index=ALTITLE&term=problem+trzech+cia%C5%82&x=0&y=0&aspect=basic_search")

(defn search-url-param [s]
  (format "https://br-hip.pfsl.poznan.pl/ipac20/ipac.jsp?menu=search&aspect=basic_search&npp=10&ipp=20&spp=20&profile=br-mar&ri=&index=ALTITLE&term=%s&x=0&y=0&aspect=basic_search" (java.net.URLEncoder/encode s java.nio.charset.StandardCharsets/UTF_8)))

(def search-content (html-resource (java.net.URL. (search-url-param "problem trzech ciał"))))

(doall
 (map #(println %)
      (->> (select search-content [(attr? :class) :a])
           (filter #(= "smallBoldAnchor" (:class (:attrs %))))
           (filter #(get-in % [:attrs :href]))
           (map #(hash-map :href (get-in % [:attrs :href]) :title (first (get-in % [:content])))))))
