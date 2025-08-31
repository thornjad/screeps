.PHONY: install dev build clean repl deploy version

install:
	npm install

go: build deploy

dev:
	npx shadow-cljs watch main

build: version
	npx shadow-cljs release main

version:
	@echo "(ns thornjad.screeps.version)" > src/thornjad/screeps/version.cljs
	@echo "" >> src/thornjad/screeps/version.cljs
	@echo "(def version \"$$(git rev-parse --short HEAD)-$$(date +%Y%m%d%H%M%S)\")" >> src/thornjad/screeps/version.cljs

clean:
	rm -rf dist/ .shadow-cljs/

repl:
	npx shadow-cljs clj-repl

deploy:
	npx grunt screeps
