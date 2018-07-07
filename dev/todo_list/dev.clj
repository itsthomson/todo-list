(ns todo-list.dev
  (:require [ring.adapter.jetty :as jetty]
            [ring.middleware.reload :refer [wrap-reload]]
            [todo-list.core :as core]))


(defn -main
  "A very simple web server using Ring & Jetty that reloads code changes via the
   development profile of Leiningen"
  [port-number]
  (jetty/run-jetty (wrap-reload #'core/app)
       {:port (Integer. port-number)}))
