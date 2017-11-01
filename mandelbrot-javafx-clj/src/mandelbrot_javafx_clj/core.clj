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
           (javafx.event EventHandler))
  (:gen-class
   :extends javafx.application.Application))

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

(defn set-label2 []
  (Label. "Password:"))

(defn set-textfield []
  (doto (TextField.)))

(defn set-text2 []
  (Text.))

(defn set-btn []
  (doto (Button.)
    (.setText "Sign in")))

(defn set-hbox []
   (doto (HBox. 10)
     (.setAlignment Pos/BOTTOM_RIGHT)))

(defn set-passwordfield []
   (PasswordField.))

(defn set-label []
  (Label. "Username"))

(defn set-text1 []
   "If you do/do not change this, You must declear as Functions"
   (doto (Text. "Welcome")
     (.setFont (Font/font "Tahoma" FontWeight/NORMAL 20.0))))

(def texts {:text (doto (Text.))})
(def atxt (ref texts))
(defn set-btn []
   (doto (Button. "Sign in")
     (.setOnAction (proxy [EventHandler] []
                     (handle [_]
                       (dosync
                        (ref-set atxt (doto @atxt
                                         (.setFill Color/FIREBRICK)
                                         (.setText "Sign in button pressed")))))))))

(defn set-grid
  "(let [something])
  ... something will be changed with app's internal change"
  []
  (doto (GridPane.)
    (.setAlignment Pos/CENTER)
    (.setHgap 10)
    (.setVgap 10)
    (.setPadding (Insets. 25 25 25 25))
    (.add (set-text1) 0 0 2 1)
    (.add (set-label) 0 1)
    (.add (set-textfield) 1 1)
    (.add (set-label2) 0 2)
    (.add (set-passwordfield) 1 2)
    (.add (doto (set-hbox)
            (.add (set-btn))) 1 4)
    (.add (set-text2) 1 6)
    (.add (set-btn) 1 4)
    (.add (:text @atxt))
    ))

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
