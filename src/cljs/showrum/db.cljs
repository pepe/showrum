(ns showrum.db
  (:require [cljs.spec :as s]
            [datascript.core :as d]
            [showrum.data :as data]
            [showrum.spec]))

(defonce schema
  {:deck/slides {:db/cardinality :db.cardinality/many
                 :db/valueType   :db.type/ref}})

(defonce conn
  (d/create-conn schema))

(defn deck
  "Returns deck with author and date"
  [id]
  (d/entity @conn id))

(defn decks
  "Returns all decks in db"
  []
  (sort-by
   second
   (d/q
    '[:find ?e ?do ?dt
      :where
      [?e :deck/order ?do]
      [?e :deck/title ?dt]]
    @conn)))

(defn init
  "Initializes the db"
  []
  (d/reset-conn! conn (d/empty-db schema))
  (let [decks data/decks]
    (if (s/valid? :showrum.spec/decks decks)
      (d/transact! conn decks)
      (.alert js/window "Attention! Bad data!"))))
