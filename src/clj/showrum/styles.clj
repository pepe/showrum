(ns showrum.styles
  (:require [garden.def :refer [defrule defstyles]]
            [garden.stylesheet :refer [rule]]))

(def black "hsl(0, 0%, 15%)")

(defstyles screen
  (let [body (rule :body)]
    (body
     {:color black
      :overflow :hidden}
     [:#container
      {:width "100vw"
       :height "100vh"}
      [:.page
       {:width "100vw"
        :height "100vh"}
       [:div.navigation
        {:display :flex
         :justify-content :space-between
         :align-items :center
         :position :absolute
         :top "1vh"
         :left "5vw"
         :width "90vw"
         :z-index 10}
        [:&.hovered
         [:nav
          {:opacity [[1 :!important]]
           :transition [[:opacity "250ms"]]}]
         [:div.counter
          {:opacity [[1 :!important]]
           :transition [[:opacity "150ms"]]}]]
        [:nav
         {:display :flex
          :justify-content :space-between
          :align-items :center
          :transition [[:opacity "1s"]]
          :opacity 0}
         [:&.slides
          {:width "10vw"}]
         [:&.decks
          {:width "50vw"}]]
        [:div.counter
         {:font-size "1.44rem"
          :font-weight :bold
          :width "30vw"
          :opacity 0.25
          :transition [[:opacity "1s"]]
          :text-align :right
          :padding-right "1rem"}]]
       [:.deck
        {:position :absolute
         :display :flex
         :top 0
         :left 0
         :width "100vw"
         :height "100vh"
         :transition [[:opacity "500ms" "cubic-bezier(0.22, 0.61, 0.36, 1)"]]}
        [:.slide
         {:margin "10vh 10vw"
          :height "80vh"
          :width "80vw"
          :display :flex
          :flex-direction :column
          :justify-content :space-around}
         [:&.bullets
          {:justify-content :space-between}
          [:ul
           {:margin-bottom "10rem"}
           [:&>li
            {:font-size "2.0736rem"
             :line-height "2.985rem"}]]]
         [:&.header
          [:h1 {:font-size "4rem"
                :font-variant :small-caps}]
          [:&.main
           [:h1 {:font-size "8rem"
                 :font-weight :bold
                 :font-variant :none}]]]]]
       [:footer
        {:display :flex
         :justify-content :space-between
         :position :absolute
         :bottom "0"
         :right "0"
         :width "40vw"
         :padding "0.25rem 2vw 0.25rem 4vw"
         :border-radius "0.25rem 0 0 0"
         :color :white
         :background-color black}]]])))
