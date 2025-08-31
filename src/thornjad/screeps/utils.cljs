(ns thornjad.screeps.utils)

;; Helper functions for Screeps API to avoid name mangling

;; Store operations
(defn get-energy [obj]
  "Get energy from store"
  (js-invoke (.-store obj) "getUsedCapacity" js/RESOURCE_ENERGY))

(defn get-capacity [obj]
  "Get total energy capacity from store"
  (js-invoke (.-store obj) "getCapacity" js/RESOURCE_ENERGY))

;; Spawn operations
(defn spawn-creep [spawn body name]
  "Spawn a creep with given body and name"
  (js-invoke spawn "spawnCreep" body name))

;; Creep operations
(defn move-to [creep target]
  "Move creep to target"
  (js-invoke creep "moveTo" target))

(defn harvest [creep source]
  "Harvest from source"
  (js-invoke creep "harvest" source))

(defn transfer [creep target resource]
  "Transfer resource to target"
  (js-invoke creep "transfer" target resource))

;; Room operations
(defn find-in-room [room find-type]
  "Find objects in room"
  (js-invoke room "find" find-type))

(defn find-closest [creep objects]
  "Find closest object to creep"
  (js-invoke creep "findClosestByPath" objects))