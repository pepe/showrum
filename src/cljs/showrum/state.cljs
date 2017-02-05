(ns showrum.state
  (:require [rum.core :refer [cursor-in derived-atom]]))

(defonce app
  (atom {:db           {:initialized nil}
         :current      {:slide (js/Number.parseInt (.getItem js/localStorage "current-slide"))
                        :deck  (js/Number.parseInt (.getItem js/localStorage "current-deck"))
                        :slides-count 0}
         :loop-running false}))

(def db-initialized? (cursor-in app [:db :initialized]))
(def current-slide (cursor-in app [:current :slide]))
(def current-slides-count (cursor-in app [:current :slides-count]))
(def current-deck (cursor-in app [:current :deck]))
(def loop-running? (cursor-in app [:loop-running]))
(def searching (cursor-in app [:searching]))
(def search-term (cursor-in app [:search-term]))

(defn loop-running [] (reset! loop-running? true))

(defn- set-slide
  [slide-no from-storage]
  (let [slide-no (js/Number.parseInt slide-no)]
    (when-not from-storage
      (.setItem js/localStorage "current-slide" slide-no))
    (reset! current-slide slide-no)))

(defn- set-deck
  [deck-no]
  (set-slide 1 false)
  (reset! current-deck deck-no))

(defn next-slide
  []
  (if (< @current-slide @current-slides-count)
    (set-slide (inc @current-slide) false)))

(defn prev-slide
  []
  (if (> @current-slide 1)
    (set-slide (dec @current-slide) false)))

(defn toggle-search
  []
  (swap! searching not))

(defn search-toggler
  [e]
  (toggle-search))

(defn activate-search-result
  [[deck _ slide _]]
  (set-deck deck)
  (set-slide slide false)
  (toggle-search))

(defn set-search-term
  [term]
  (reset! search-term term))

(defn search-term-updater
  [e]
  (.preventDefault e)
  (.stopPropagation e)
  (set-search-term (-> e .-target .-value)))

(defn init-local-storage
  []
  (set-deck 1)
  (set-slide 1 false))

(defn local-storage-handler
  []
  (aset js/window "onstorage"
        (fn [e]
          (case (.-key e)
            "current-slide" (set-slide (.-newValue e) true)
            "current-deck" (set-deck (.-newValue e))))))

(defn db-initialized []
  (init-local-storage)
  (local-storage-handler)
  (reset! db-initialized? true))

(defn db-cleared [] (reset! db-initialized? false))

(defn set-slides-count
  [slides-count]
  (reset! current-slides-count slides-count))

