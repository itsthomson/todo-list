(ns todo-list.datomic.core
  "Functions for interacting with Datomic.

   Schema is defined in todo-list.datomic.schema"
  (:require [datomic.client.api :as d]
            [todo-list.datomic.schema :as schema]))

; In the Datomic directory locally:
; bin/run -m datomic.peer-server -h localhost -p 8998 -a myaccesskey,mysecret\ 
; -d hello,datomic:mem://todo-list

(def dev-cfg {:server-type :peer-server
              :access-key "myaccesskey"
              :secret "mysecret"
              :endpoint "localhost:8998"})

(def client (d/client dev-cfg))

(def conn (d/connect client {:db-name "todo-list"}))

(defn schema-exists?
  [uri]
  (not
    (empty?
      (d/q '[:find ?e
             :where [?e :db/ident :task/name]]
           (-> uri d/connect d/db)))))

(defn transact-schema
  "Transact the schema into Datomic"
  [conn schema-map]
  (d/transact conn schema-map))
