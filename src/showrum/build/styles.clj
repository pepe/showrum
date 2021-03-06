(ns showrum.build.styles
  (:require [garden.core :refer [css]]
            [garden.stylesheet :refer [at-media]]))

(defonce black "hsl(0, 0%, 15%)")
(defonce grey  "hsl(0, 0%, 97%)")
(defonce lite-grey  "hsla(0, 0%, 97%, 0.97)")

(def rendered
  (css
   [:body
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
        :background-color grey
        :border-radius "1.5rem"
        :transition [[:opacity "3s"]]
        :transition-delay "2s"
        :opacity 0.075
        :z-index 10
        :overflow :hidden}
       [:&.hovered
        {:opacity 1
         :transition [[:opacity "1s"]]
         :transition-delay 0}
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
         [:button:first-child
          {:margin-right "1rem"}]]]
       [:div.counter
        {:font-size "2.0rem"
         :font-weight 900
         :font-family "\"Roboto\", \"Helvetica\", \"Arial\", sans-serif"
         :width "10vw"
         :text-align :right
         :padding-right "1rem"}]]
      [:div.search-panel
       {:position :absolute
        :z-index 10
        :top "4rem"
        :left "7vw"
        :background-color lite-grey
        :opacity 0.9}
       [:div.search-results
        [:li.active
         {:cursor :pointer
          :background-color :white}]]]
      [:.deck :.notes
       {:position :absolute
        :display :flex
        :top 0
        :left 0
        :width "100vw"
        :height "100vh"
        :transition [[:transform "750ms" "cubic-bezier(0.18, 0.89, 0.41, 1.05)"]]}
       [:h1 {:font-size "6rem"
             :font-variant :small-caps
             :font-weight 100}]
       [:.slide :.note
        {:margin "10vh 10vw"
         :height "80vh"
         :width "80vw"
         :display :flex
         :flex-direction :column
         :justify-content :space-around}
        [:&.bullets
         {:justify-content :space-between}
         [:ul
          {:margin-bottom "10rem"
           :padding-left 0}
          [:&>li
           {:font-size "4.299696rem"
            :font-family "\"Roboto\", \"Helvetica\", \"Arial\", sans-serif"
            :line-height "6.191562244rem"
            :list-style-type :none}
           [:&:before
            {:width "2rem"
             :margin "1px 2rem 1px 0"
             :border-radius "4px"
             :display :inline-block
             :content "\" \""
             :background-color grey}]]]]
        [:&.main-header
         [:h1 {:font-size "10rem"
               :font-weight 900
               :font-variant :none
               :padding-left "5vw"
               :margin-top "15vh"
               :border-left [["2rem" grey :solid]]}]]
        [:&.text
         [:p
          {:font-size "2.985984rem"
           :font-family "\"Roboto\", \"Helvetica\", \"Arial\", sans-serif"
           :line-height "4.29981696rem"}]]
        [:&.image
         [:h1.title
          {:font-size "2.985984rem"}]]
        [:pre
         {:font-size "2.985984rem"
          :line-height "4rem"}]]
       [:.note
        {:font-size "3rem"
         :line-height "4.5rem"}]]]
     [:footer
      {:display :flex
       :justify-content :space-between
       :position :absolute
       :opacity 0.3
       :bottom "-2.5rem"
       :right "0"
       :width "60vw"
       :padding "0.5rem 2vw 0.5rem 4vw"
       :border-radius "0.5rem 0 0 0"
       :color lite-grey
       :background-color black
       :font-family "\"Roboto\", \"Helvetica\", \"Arial\", sans-serif"
       :font-weight 100
       :transition [:all :ease-out "750ms"]}
      [:&.hovered
       {:bottom "0"
        :opacity 1}]]
     [:.loading
      {:padding [["20vh" "20vw"]]}]]]))
