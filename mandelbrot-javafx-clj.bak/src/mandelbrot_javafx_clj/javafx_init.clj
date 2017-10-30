(ns mandelbrot-javafx-clj.javafx-init
  (:import (javafx.application Application)
           (javafx.scene Scene)
           (javafx.scene.text Text Font FontWeight)
           (javafx.scene.control Label TextField PasswordField Button)
           (javafx.scene.layout GridPane HBox)
           (javafx.scene.paint Color)
           (javafx.geometry Pos Insets)
           (javafx.event EventHandler)
           (javafx.stage Stage))
  (:gen-class
   :extends javafx.application.Application))

(defn -start
  [this ^Stage stage]
  (let [atxt (Text.)
        btn  (doto (Button. "Sign in")
               (.setOnAction (proxy [EventHandler] []
                               (handle [_]
                                 (doto atxt
                                   (.setFill Color/FIREBRICK)
                                   (.setText "Sign in button pressed"))))))
        grid (doto (GridPane.)
               (.setAlignment Pos/CENTER)
               (.setHgap 10)
               (.setVgap 10)
               (.setPadding (Insets. 25 25 25 25))
               (.add (doto (Text. "Welcome")
                       (.setFont (Font/font "Tahoma" FontWeight/NORMAL 20.0)))
                     0 0 2 1)
               (.add (Label. "User Name:") 0 1)
               (.add (TextField.) 1 1)
               (.add (Label. "Password:") 0 2)
               (.add (PasswordField.) 1 2)
               (.add btn 1 4)
               (.add atxt 1 6))]
    (doto stage
      (.setTitle "JavaFX Welcome")
      (.setScene (Scene. grid 300 275))
      .show)))

(defn -main [& args]
  (javafx.application.Application/launch mandelbrot_javafx_clj.javafx_init (into-array String []))
  )
;; (def window (promise))

;; (defn start-fx
;;   "This is template from \n
;;   https://qiita.com/foozea/items/c7932a8b18ed282e9784 "
;;   ([] (start-fx {:root-stage? true}))
;;   ([{:keys [root-stage?]}]
;;    (core/swap root-stage?)
;;    (when (not (realized? window))
;;      (doto (Thread.
;;             #(Application/launch
;;               mandelbrot_javafx_clj.javafx_init
;;               (into-array String [])))
;;        .start))
;;    @window))

;; (defn -start
;;   "This is template from \n
;;   https://qiita.com/foozea/items/c7932a8b18ed282e9784 "
;;   [this ^Stage stage]
;;   (deliver window stage)
;;   (.show stage))

;; (defn -main
;;   "This is template from \n
;;   https://qiita.com/foozea/items/c7932a8b18ed282e9784 "
;;   [& args]
;;   (let [stage (start-fx)]
;;     (doto stage
;;       (core/root-stage {:root-stage? false})
;;       .show)))
