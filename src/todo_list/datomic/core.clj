(ns todo-list.datomic.core
  "Functions for interacting with Datomic.

   Schema is defined in todo-list.datomic.schema"
  (:require [datomic.client.api :as d]
            [todo-list.datomic.schema :as schema]))

(def db-uri "datomic:dev://localhost:4334/todo-list")

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
