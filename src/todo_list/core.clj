(ns todo-list.core
  (:require [ring.adapter.jetty :as jetty]
            [ring.middleware.reload :refer [wrap-reload]]
            [compojure.core :refer [defroutes GET]]
            [compojure.route :refer [not-found]]
            [ring.handler.dump :refer [handle-dump]]
            [hiccup.core :refer :all]
            [hiccup.page :refer :all]))

(defn welcome
  "A ring handler to process all requests sent to the webapp"
  [request]
  (html [:h1 "Hello, Clojure World!"]
        [:p "This app should auto-update thanks to wrap-reload, and now looks 
             prettier with hiccup."]))

(defn goodbye
  "Saying Goodbye"
  [request]
  (html [:h1 "Goodbyeeeeeeee"]
        [:p "Getting the hang of how routes and hiccup work in Clojure."]))

(defn about
  "Information about the website developer"
  [request]
  (html5 {:lang "en"}
         [:head (include-js "myscript.js") (include-css "mystyle.css")]
         [:body
           [:div [:h1 {:class "info"} "About Me"]]
           [:div [:p "I'm making the most of my EIR time..."]]]))

(defn request-info
  "View the information contained in the request, useful for debugging"
  [request]
  (html [:h1 "HTML Request"]
        [:p (pr-str request)]))

(defn hello
  "Personalized greeting demonstrating variable path elements"
  [request]
  (let [name (get-in request [:route-params :name])]
    (html [:h1 "Variable Path Example"]
          [:p (str "Hello " name ". I got your name from the URL.")])))

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
        (html [:h1 "Calculator Example"]
              [:p (str "Calculated result: " (f a b))])
        (html [:h1 "Calculator Example (Error)"]
              [:p "Sorry, unknown operator. I only recognize [+, -, *, :] (: is 
                   for division"]))))

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
