(defproject todo-list "0.1.0-SNAPSHOT"
  :description "A Todo List server-side webapp using Ring & Compojure"
  :url "TEMP"
  :license {:name "MIT License"
            :url "https://opensource.org/licenses/MIT"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [ring "1.4.0"]
                 [compojure "1.3.4"]
                 [hiccup "1.0.5"]]
  :plugins [[lein-ring "0.9.6" :exclusions [org.clojure/clojure]]]
  :main todo-list.core
  :profiles {:dev
                   {:main todo-list.core/-dev-main}}
  :ring {:handler todo-list.core/app})
