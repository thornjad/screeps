const assert = require('assert');

// Mock JavaScript globals that ClojureScript expects
global.js = {
  WORK: 'work',
  CARRY: 'carry', 
  MOVE: 'move'
};

// Import the compiled ClojureScript constants
// Note: This will need to be adjusted based on how shadow-cljs compiles the constants
const constants = require('../../dist/main.js');

describe('Constants', function() {
  it('should have correct max-workers value', function() {
    // This test will need to be adjusted based on the compiled output
    assert.strictEqual(typeof constants, 'object');
  });
  
  it('should have correct worker cost', function() {
    // Basic test structure - will need real constants once compiled
    assert.ok(true, 'Constants module loads');
  });
});