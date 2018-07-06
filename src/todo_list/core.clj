(ns todo-list.core
  (:require [ring.adapter.jetty :as jetty]
            [ring.middleware.reload :refer [wrap-reload]]
            [compojure.core :refer [routes]]
            [todo-list.handlers.base-routes :refer [base-routes]]
            [compojure.route :refer [not-found]]
            [ring.handler.dump :refer [handle-dump]]))

(defroutes app
  (routes #'base-routes)
  (not-found "<h1>This is a sample 404 page</h1>
              <p>I'm now using compojure to route stuff.</p>"))

(defn -dev-main
  "A very simple web server using Ring & Jetty that reloads code changes via the
   development profile of Leiningen"
  [port-number]
  (jetty/run-jetty (wrap-reload #'app)
       {:port (Integer. port-number)}))

(defn -main
  "A very simple web server using Ring & Jetty"
  [port-number]
  (jetty/run-jetty app
      {:port (Integer. port-number)}))
