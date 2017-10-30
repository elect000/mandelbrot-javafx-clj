(ns mandelbrot-javafx-clj.core
  (:import (javafx.scene.paint Color)
           (javafx.application Application)
           (javafx.stage Stage)
           (javafx.awt.Color)))

;; Here is the function of calculating Mandelbrot sets ;;;

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(def initial-state {:data []
                    :root-stage? false})

(def data-state (atom initial-state))

(defn force-exit
  [root-stage?]
  (reify javafx.event.EventHandler
    (handle [this event]
      (when (not root-stage?)
        (do (println "Closing application")
            (javafx.application.Platform/exit))))))

(defn set-scene [] (promise))

(defn root-stage
  "Require: Stage object"
  [{:keys [root-stage?]} ^Stage stage]
  (doto stage
    (.setTitle "Fractals: Mandelbrot")
    (.setOnCloseRequesst (force-exit root-stage?))
    ;;(.setScene (set-scene))
    ))

(defn swap [root-stage?]
  (swap! data-state assoc :root-stage? root-stage?))

