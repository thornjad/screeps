.PHONY: install dev build clean repl deploy version-file version-patch version-minor version-major

install:
	npm install

go: build deploy

dev:
	npx shadow-cljs watch main

build: version-file
	npx shadow-cljs release main

version-file:
	@echo "(ns thornjad.screeps.version)" > src/thornjad/screeps/version.cljs
	@echo "" >> src/thornjad/screeps/version.cljs
	@SEMVER=$$(node -pe "require('./package.json').version"); \
	TIMESTAMP=$$(date +%Y%m%d%H%M%S); \
	echo "(def version \"$$SEMVER.$$TIMESTAMP\")" >> src/thornjad/screeps/version.cljs

version-patch:
	npm version patch

version-minor:
	npm version minor

version-major:
	npm version major

clean:
	rm -rf dist/ .shadow-cljs/

repl:
	npx shadow-cljs clj-repl

deploy:
	npx grunt screeps
