# Thornjad's Screeps AI

Thornjad's AI bot for the Screeps MMO strategy game.

## What is Screeps?

Screeps is a real-time strategy game designed for programmers. Unlike traditional RTS games, you don't control your units directly—instead, you write code that autonomously manages your entire colony.

## AI Architecture

### Structure

```
src/
├── core.cljs       # Main game loop and entry point
├── creeps.cljs     # All creep behaviors and roles
├── rooms.cljs      # Room management and building
├── utils.cljs      # Helper functions and utilities
└── constants.cljs  # Game constants and configuration
```

### Creep Roles

- **Harvesters**: Collect energy from sources
- **Builders**: Construct and repair structures
- **Upgraders**: Improve room controller levels
- **Carriers**: Transport resources efficiently
- **Defenders**: Protect against threats
- **Scouts**: Explore new rooms and gather intel

## Learning Resources

- [Screeps Documentation](https://docs.screeps.com/)
- [ClojureScript Guides](https://clojurescript.org/)
- [shadow-cljs User Guide](https://shadow-cljs.github.io/docs/UsersGuide.html)

---

*Built with ❤️ in Minnesota*
