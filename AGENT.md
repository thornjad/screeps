# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

This is a ClojureScript AI bot for the Screeps MMO strategy game. The codebase compiles ClojureScript to JavaScript for the Screeps runtime environment.

## Development Commands

### Building and Development
- `make build` - Compile ClojureScript to JavaScript (production build)
- `make dev` - Watch mode for development with hot reloading
- `make go` - Build and deploy in one command

### Deployment
- `make deploy` - Deploy compiled code to Screeps servers using Grunt
- `npx grunt screeps` - Direct deployment command

### Testing
- `make test` - Run unit tests with Mocha
- `npm test` - Alternative test command

### Other Utilities
- `make clean` - Remove build artifacts (dist/ and .shadow-cljs/)
- `make repl` - Start ClojureScript REPL for interactive development
- `make version-patch/minor/major` - Bump version numbers

## Architecture

### Core Structure
```
src/thornjad/screeps/
├── core.cljs       # Main game loop and entry point (exports game-loop function)
├── creeps.cljs     # Creep behaviors and role management
├── rooms.cljs      # Room management and building strategies
├── utils.cljs      # Helper functions for Screeps API interactions
├── constants.cljs  # Game constants and configuration values
└── version.cljs    # Auto-generated version file (do not edit manually)
```

### Build System
- Uses shadow-cljs for ClojureScript compilation
- Compiles to `dist/main.js` as a Node.js library
- Exports `loop` function from `thornjad.screeps.core/game-loop`
- Version file is auto-generated during build with timestamp

### Configuration Files
- `shadow-cljs.edn` - ClojureScript build configuration
- `.screeps.json` - Screeps server credentials (email/token)
- `Gruntfile.js` - Deployment configuration for Screeps servers
- `Makefile` - Primary build automation (preferred over npm scripts)

### Key Implementation Details
- All Screeps API interactions are wrapped in utility functions
- Game loop handles spawn management and basic creep behaviors
- Currently implements simple harvester/carrier pattern
- Version tracking includes build timestamp for deployment identification

## Development Workflow

1. Use `make dev` for development with hot reloading
2. Test changes locally before deploying
3. Use `make go` to build and deploy to Screeps servers
4. Monitor in-game console for AI behavior and debugging