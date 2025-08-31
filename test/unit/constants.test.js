const assert = require('assert');

describe('Constants', function() {
  it('should validate basic math', function() {
    // Basic test to ensure mocha works
    assert.strictEqual(2 + 2, 4);
  });
  
  it('should validate worker cost calculation', function() {
    // Test the logic behind our constants
    const WORK_COST = 100;
    const CARRY_COST = 50; 
    const MOVE_COST = 50;
    const TOTAL_WORKER_COST = WORK_COST + CARRY_COST + MOVE_COST;
    
    assert.strictEqual(TOTAL_WORKER_COST, 200, 'Worker should cost 200 energy');
  });
});