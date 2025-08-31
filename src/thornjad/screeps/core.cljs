(ns thornjad.screeps.core)

(defn game-loop []
  (js/console.log "Hello Screeps from ClojureScript!"))

;; Export the loop function that Screeps expects
(set! js/module.exports #js {:loop game-loop})