(ns thornjad.screeps.core
  (:require [thornjad.screeps.constants :as const]
            [thornjad.screeps.version :as version]))

(defn game-loop []
  (js/console.log (str "Running Thornjad Screeps AI v" version/version))

  ;; Spawn creeps if we have energy and need workers
  (doseq [[spawn-name spawn] (js->clj js/Game.spawns)]
    (when (and (< (count (js->clj js/Game.creeps)) const/max-workers)
               (>= (.-energy spawn) const/spawn-energy-threshold))
      (.spawnCreep spawn (clj->js const/worker-body)
                   (str "Worker" (js/Math.floor (* (js/Math.random) 1000))))))

  ;; Manage existing creeps
  (doseq [[creep-name creep] (js->clj js/Game.creeps)]
    (if (< (.-carry.energy creep) (.-carryCapacity creep))
      ;; Harvest energy if not full
      (let [sources (.find (.-room creep) js/FIND_SOURCES)]
        (when (pos? (alength sources))
          (if (= (.harvest creep (aget sources 0)) js/ERR_NOT_IN_RANGE)
            (.moveTo creep (aget sources 0)))))
      ;; Transfer energy to spawn if full
      (let [spawn (.findClosestByPath creep (.find (.-room creep) js/FIND_MY_SPAWNS))]
        (if (= (.transfer creep spawn js/RESOURCE_ENERGY) js/ERR_NOT_IN_RANGE)
          (.moveTo creep spawn))))))

;; Export the loop function that Screeps expects
(set! js/module.exports #js {:loop game-loop})
