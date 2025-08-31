(ns thornjad.screeps.core
  (:require [thornjad.screeps.constants :as const]
            [thornjad.screeps.version :as version]
            [thornjad.screeps.utils :as utils]))

(defn game-loop []
  (js/console.log (str "Running Thornjad Screeps AI v" version/version))

  ;; Spawn creeps if we have energy and need workers
  (doseq [spawn-name (js/Object.keys js/Game.spawns)]
    (let [spawn (aget js/Game.spawns spawn-name)
          energy (utils/get-energy spawn)]
      (js/console.log (str "Spawn " spawn-name " has " energy " energy"))
      (when (and (< (count (js/Object.keys js/Game.creeps)) const/max-workers)
                 (>= (or energy 0) const/spawn-energy-threshold))
        (js/console.log "Attempting to spawn creep...")
        (let [result (utils/spawn-creep spawn (clj->js const/worker-body)
                                        (str "Worker" (js/Math.floor (* (js/Math.random) 1000))))]
          (js/console.log (str "Spawn result: " result))))))

  ;; Manage existing creeps
  (doseq [creep-name (js/Object.keys js/Game.creeps)]
    (let [creep (aget js/Game.creeps creep-name)]
      (when (aget creep "room")  ;; Only proceed if creep has a room
      (let [creep-energy (utils/get-energy creep)
            creep-capacity (utils/get-capacity creep)]
        (js/console.log (str "Creep " creep-name " has " creep-energy "/" creep-capacity " energy"))
        (if (< creep-energy creep-capacity)
          ;; Harvest energy if not full
          (let [sources (utils/find-in-room (aget creep "room") js/FIND_SOURCES)]
            (js/console.log (str "Found " (alength sources) " sources"))
            (when (pos? (alength sources))
              (let [harvest-result (utils/harvest creep (aget sources 0))]
                (if (= harvest-result js/ERR_NOT_IN_RANGE)
                  (let [move-result (utils/move-to creep (aget sources 0))]
                    (js/console.log (str "Moving to source, result: " move-result)))))))
          ;; Transfer energy to spawn if full
          (let [spawn (utils/find-closest creep (utils/find-in-room (aget creep "room") js/FIND_MY_SPAWNS))]
            (js/console.log (str "Found spawn: " (boolean spawn)))
            (when spawn
              (let [transfer-result (utils/transfer creep spawn js/RESOURCE_ENERGY)]
                (js/console.log (str "Transfer result: " transfer-result))
                (if (= transfer-result js/ERR_NOT_IN_RANGE)
                  (let [move-result (utils/move-to creep spawn)]
                    (js/console.log (str "Moving to spawn, result: " move-result)))))))))))))

;; Export the loop function that Screeps expects
(set! js/module.exports #js {:loop game-loop})
