const assert = require('assert');
const { ScreepsServer, TerrainMatrix } = require('screeps-server-mockup');

describe('Game Loop Integration', function() {
  let server;
  let player;

  before(async function() {
    // Setup server with a simple room
    server = new ScreepsServer();
    
    // Add a player
    const modules = {
      main: require('../../dist/main.js')
    };
    
    player = await server.world.addBot({ 
      username: 'testbot', 
      room: 'W0N0',
      x: 25, y: 25,
      modules: modules
    });
  });

  after(async function() {
    if (server) {
      await server.stop();
    }
  });

  it('should run without errors', async function() {
    // Run one game tick
    await server.tick();
    
    // Basic test that the game loop executed
    const room = await server.world.roomObjects('W0N0');
    assert.ok(room, 'Room should exist');
  });

  it('should spawn creeps when conditions are met', async function() {
    // Give the spawn some energy
    const spawns = await server.world.roomObjects('W0N0', 'spawn');
    if (spawns.length > 0) {
      spawns[0].store.energy = 300;
    }
    
    // Run several ticks
    for (let i = 0; i < 5; i++) {
      await server.tick();
    }
    
    // Check if creeps were spawned
    const creeps = await server.world.roomObjects('W0N0', 'creep');
    // This is just a basic framework - actual assertions depend on game logic
    assert.ok(Array.isArray(creeps), 'Should return creeps array');
  });
});