.PHONY: install dev build clean repl deploy

install:
	npm install

go: build deploy

dev:
	npx shadow-cljs watch main

build:
	npx shadow-cljs release main

clean:
	rm -rf dist/ .shadow-cljs/

repl:
	npx shadow-cljs clj-repl

deploy:
	npx grunt screeps
