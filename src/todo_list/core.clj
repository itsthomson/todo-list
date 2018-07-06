(ns todo-list.core
  (:require [ring.adapter.jetty :as jetty]
            [ring.middleware.reload :refer [wrap-reload]]
            [compojure.core :refer [defroutes GET]]
            [compojure.route :refer [not-found]]))

(defn welcome
  "A ring handler to process all requests sent to the webapp"
  [request]
  {:status 200
   :body "<h1>Hello, Clojure World!</h1>
          <p>Just futzing around with Clojure. This should automatically 
             update thanks to <b>wrap-reload</b>!</p>"
   :headers {}})

(defn goodbye
  "Saying Goodbye"
  [request]
  {:status 200
   :body "<h1>Goodbyeeeeeeeeee</h1>
          <p>Getting the hang of how routes work in Clojure.</p>"
   :headers {}})

(defn about
  "Information about the website developer"
  [request]
  {:status 200
   :body "<p>Making the most of my EIR time...</p>"
   :headers {}})

(defn request-info
  "View the information contained in the request, useful for debugging"
  [request]
  {:status 200
   :body (pr-str request)
   :headers {}})

(defn hello
  "Personalized greeting demonstrating variable path elements"
  [request]
  (let [name (get-in request [:route-params :name])]
    {:status 200
     :body (str "Hello " name ". I got your name from the URL.")
     :headers {}}))

(def operands {"+" + "-" - "*" * ":" /})

(defn calculator
  "A calculator that can add, divide, subtract, and multiply. More stuff with
   variable paths."
  [request]
  (let [a  (Integer. (get-in request [:route-params :a]))
        b  (Integer. (get-in request [:route-params :b]))
        op (get-in request [:route-params :op])
        f (get operands op)]
     (if f
        {:status 200
         :body (str "Calculated result: " (f a b))
         :headers {}}
        {:status 404
         :body "Sorry, unknown operator. I only recognize [+, -, *, :] (: is 
                for division)."
         :headers {}})))

(defroutes app
  (GET "/" [] welcome)
  (GET "/goodbye" [] goodbye)
  (GET "/about" [] about)
  (GET "/request-info" [] request-info)
  (GET "/hello/:name" [] hello)
  (GET "/calculator/:op/:a/:b" [] calculator)
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
