(ns mandelbrot-javafx-clj.javafx-init
     (:require [mandelbrot-javafx-clj.core :as core])
     (:import (javafx.application Application)
              (javafx.stage Stage))
     (:gen-class
   :extends javafx.application.Application))

(defn -start
  " This is javafx start funnction \n
  If you want to change attribute of 'javafx.stage.Stage', You add in (doto ...) \n
  And also, You want to add some button or canvas etc..., You add in (let ...) and (doto ...) "
  [this ^Stage stage]
  (let []
    (doto stage
      (.setTitle "Fractals: Mandelbrot")
      (.setOnCloseRequest (core/force-exit {:root-stage? false}))
      (.setScene (core/set-scene))
      ;;(core/root-stage {:root-stage? false})
      .show)))

(defn -main
  " This is javafx launch function \n Please don't chage it"
  [& args]
  (core/swap false)
  (Application/launch mandelbrot_javafx_clj.javafx_init (into-array String [])))
