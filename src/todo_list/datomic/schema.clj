(ns todo-list.datomic.schema
  "A Datomic schema for todo lists.
   Tasks have an id, name, description, boolean state (not done or done), 
   datetime last modified, and datetime created."
  (:require [datomic.client.api :as d]))

(def schema-txn
  [{:db/doc "Task unique ID"
    :db/ident :task/id
    :db/valueType :db.type/uuid
    :db/unique :db.unique/identity
    :db/cardinality :db.cardinality/one}
   {:db/doc "Task name"
    :db/ident :task/name
    :db/valueType :db.type/string
    :db/cardinality :db.cardinality/one}
   {:db/doc "Task description"
    :db/ident :task/description
    :db/valueType :db.type/string
    :db/cardinality :db.cardinality/one}
   {:db/doc "Task state"
    :db/ident :task/state
    :db/valueType :db.type/boolean
    :db/cardinality :db.cardinality/one}
   {:db/doc "Task last modified"
    :db/ident :task/modified
    :db/valueType :db.type/instant
    :db/cardinality :db.cardinality/one}
   {:db/doc "Task created"
    :db/ident :task/created
    :db/valueType :db.type/instant
    :db/cardinality :db.cardinality/one}])
