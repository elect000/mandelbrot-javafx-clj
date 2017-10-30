(ns mandelbrot-javafx-clj.core
  (:import (javafx.scene.paint Color)
           (javafx.application Application)
           (javafx.stage Stage)
           (javafx.awt.Color)
           (javafx.scene Scene)
           (javafx.scene.layout GridPane HBox)
           (javafx.geometry Pos Insets)
           (javafx.scene.text Text Font FontWeight)
           (javafx.scene.control Label TextField PasswordField Button)
           (javafx.event EventHandler)))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Here is the function of calculating Mandelbrot sets / returns int RGB array ;;;

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; You set below data, which is atomic data ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(def initial-state {:data []
                    :root-stage? false})

;; initialize atomic data
(def data-state (ref initial-state))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; You should add some button scene h-box and etc below ;;;;;;;;;;;;;;;;;;;;;;;;;;

;; (def set-text2 (Text.))

;; (def set-btn (doto (Button.)
;;                (.setText "Sign in")))

;; (def set-hbox
;;   (doto (HBox. 10)
;;     (.setAlignment Pos/BOTTOM_RIGHT)))

;; (def set-passwordfield
;;   (PasswordField.))

;; (def set-label
;;   (Label. "Username"))

;; (def set-text1
;;   "If you do not change this, declare it as a variable"
;;   (doto (Text. "Welcome")
;;     (.setFont (Font/font "Tahoma" FontWeight/NORMAL 20.0))))

;; (defn set-grid
;;   []
;;   (doto (GridPane.)
;;     (.setAlignment Pos/CENTER)
;;     (.setHgap 10)
;;     (.setVgap 10)
;;     (.setPadding (Insets. 25 25 25 25))
;;     (.add set-text1 0 0 2 1)
;;     (.add set-label 0 1)
;;     (.add set-passwordfield 1 2)
;;     (.add set-hbox 1 4)
;;     (.add set-text2 1 6)
;;     ))
(defn set-grid [] (promise))

(defn set-label []
  (Label. "User Name:"))

(defn set-scene
  " arguments means children \n
  example:
  scene -
        |- grid ... this is scene's children
            |- Text
            |- text
            |- Label (Warning!!! We cannot use Label)
            |- Text Field
            |- Label
            |- Password Field
            |- H box (Warning!!! We cannot use HBox)
            |- text
  "
  []
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
               (.add (set-label) 0 1)
               (.add (TextField.) 1 1)
               (.add (Label. "Password:") 0 2)
               (.add (PasswordField.) 1 2)
               (.add btn 1 4)
               (.add atxt 1 6))]
  (doto (Scene. grid 300 275))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; If you are Japanese, you can read below ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; http://tnoda-clojure.tumblr.com/post/ ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;     51991955022/clojure-%E3%81%A7-java-fx-1-hello-world ;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; NECESSARY UTILITY ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn force-exit
  " This is close function \n
  Please don't change it"
  [root-stage?]
  (reify javafx.event.EventHandler
    (handle [this event]
      (when (not root-stage?)
        (do (println "Closing application")
            (javafx.application.Platform/exit))))))

(defn swap
  " This is change state of initialize \n
  Please don't change it"
  [root-stage?]
  (dosync (ref-set data-state {:data (:data (deref data-state))
                               :root-stage? root-stage?})))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
