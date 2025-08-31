(ns thornjad.screeps.core
  (:require [thornjad.screeps.constants :as const]
            [thornjad.screeps.version :as version]
            [thornjad.screeps.utils :as utils]))

(defn game-loop []
  (js/console.log (str "Running Thornjad Screeps AI v" version/version))

  ;; Spawn creeps if we have energy and need workers
  (doseq [[spawn-name spawn] (js->clj js/Game.spawns)]
    (let [energy (utils/get-energy spawn)]
      (js/console.log (str "Spawn " spawn-name " has " energy " energy"))
      (when (and (< (count (js->clj js/Game.creeps)) const/max-workers)
                 (>= (or energy 0) const/spawn-energy-threshold))
        (js/console.log "Attempting to spawn creep...")
        (let [result (utils/spawn-creep spawn (clj->js const/worker-body)
                                       (str "Worker" (js/Math.floor (* (js/Math.random) 1000))))]
          (js/console.log (str "Spawn result: " result))))))

  ;; Manage existing creeps
  (doseq [[creep-name creep] (js->clj js/Game.creeps)]
    (when (.-room creep)  ;; Only proceed if creep has a room
      (let [creep-energy (utils/get-energy creep)
            creep-capacity (utils/get-capacity creep)]
        (if (< creep-energy creep-capacity)
          ;; Harvest energy if not full
          (let [sources (utils/find-in-room (.-room creep) js/FIND_SOURCES)]
            (when (pos? (alength sources))
              (if (= (utils/harvest creep (aget sources 0)) js/ERR_NOT_IN_RANGE)
                (utils/move-to creep (aget sources 0)))))
          ;; Transfer energy to spawn if full
          (let [spawn (utils/find-closest creep (utils/find-in-room (.-room creep) js/FIND_MY_SPAWNS))]
            (if (= (utils/transfer creep spawn js/RESOURCE_ENERGY) js/ERR_NOT_IN_RANGE)
              (utils/move-to creep spawn))))))))

;; Export the loop function that Screeps expects
(set! js/module.exports #js {:loop game-loop})
