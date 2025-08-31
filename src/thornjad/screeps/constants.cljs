(ns thornjad.screeps.constants)

;; Creep management
(def max-workers 2)
(def worker-cost 200) ; WORK + CARRY + MOVE = 50 + 50 + 50

;; Creep body parts
(def worker-body [js/WORK js/CARRY js/MOVE])

;; Energy thresholds
(def spawn-energy-threshold worker-cost)