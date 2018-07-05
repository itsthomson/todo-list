(ns todo-list.core
  (:require [ring.adapter.jetty :as jetty]))

(defn welcome
  "A ring handler to process all requests sent to the webapp"
  [request]
  (if (= "/" (:uri request))
    {:status 200
     :body "<h1>Hello, Clojure World!</h1>
            <p>Just futzing around with Clojure.</p>"
     :headers {}}
    {:status 404
     :body "<h1>This is a sample 404</h1>
            <p>Derp derp derp.</p>"
     :headers {}}))

(defn -main
  "A very simple web server using Ring & Jetty"
  [port-number]
  (jetty/run-jetty welcome
      {:port (Integer. port-number)}))
