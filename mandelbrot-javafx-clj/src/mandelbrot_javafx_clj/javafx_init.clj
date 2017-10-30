(ns mandelbrot-javafx-clj.javafx-init
  (:require [mandelbrot-javafx-clj.core :as core])
  (:import (javafx.application Application)
           (javafx.stage Stage))
  (:gen-class
   :extends javafx.application.Application))

(def window (promise))

(defn start-fx
  "This is template from \n
  https://qiita.com/foozea/items/c7932a8b18ed282e9784 "
  ([] (start-fx {:root-stage? true}))
  ([{:keys [root-stage?]}]
   (core/swap root-stage?)
   (when (not (realized? window))
     (doto (Thread.
            #(Application/launch
              mandelbrot_javafx_clj.javafx_init
              (into-array String [])))
       .start))
   @window))

(defn -start
  "This is template from \n
  https://qiita.com/foozea/items/c7932a8b18ed282e9784 "
  [this ^Stage stage]
  (deliver window stage)
  (.show stage))

(defn -main
  "This is template from \n
  https://qiita.com/foozea/items/c7932a8b18ed282e9784 "
  [& args]
  (let [stage (start-fx)]
    (doto stage
      (core/root-stage {:root-stage? false})
      .show)))
