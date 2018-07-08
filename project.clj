(defproject todo-list "0.1.0-SNAPSHOT"
  :description "A Todo List server-side webapp using Ring & Compojure"
  :url "https://sakura-20180705.appspot.com/"
  :license {:name "MIT License"
            :url "https://opensource.org/licenses/MIT"}
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [ring "1.6.1"]
                 [compojure "1.3.4"]
                 [hiccup "1.0.5"]
                 [com.datomic/client-pro "0.8.17"
                  :exclusions [org.eclipse.jetty/jetty-client
                               org.eclipse.jetty/jetty-http
                               org.eclipse.jetty/jetty-util]]]
  :plugins [[lein-ring "0.12.4" :exclusions [org.clojure/clojure]]]
  :main todo-list.core
  :profiles {:dev
                   {:source-paths ["src", "dev"]
                    :main todo-list.dev}}
  :ring {:handler todo-list.core/app})
