(ns todo-list.core
  (:require [ring.adapter.jetty :as jetty]
            [ring.middleware.reload :refer [wrap-reload]]))

(defn welcome
  "A ring handler to process all requests sent to the webapp"
  [request]
  (if (= "/" (:uri request))
    {:status 200
     :body "<h1>Hello, Clojure World!</h1>
            <p>Just futzing around with Clojure. This should automatically 
               update thanks to <b>wrap-reload</b>!</p>"
     :headers {}}
    {:status 404
     :body "<h1>This is a sample 404</h1>
            <p>Derp derp derp.</p>"
     :headers {}}))

(defn -dev-main
  "A very simple web server using Ring & Jetty that reloads code changes via the
   development profile of Leiningen"
  [port-number]
  (jetty/run-jetty (wrap-reload #'welcome)
       {:port (Integer. port-number)}))

(defn -main
  "A very simple web server using Ring & Jetty"
  [port-number]
  (jetty/run-jetty welcome
      {:port (Integer. port-number)}))
