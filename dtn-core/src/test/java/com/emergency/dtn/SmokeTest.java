package com.emergency.dtn;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Stage 1 smoke test.
 *
 * This test has no real logic yet — its only job is to confirm:
 *   1. The Maven project compiles correctly
 *   2. JUnit 5 is wired up and running
 *   3. "mvn test" exits with code 0 (green)
 *
 * Real tests with meaningful assertions start in Stage 2 (data model).
 */
class SmokeTest {

    @Test
    void projectCompilesAndTestsRun() {
        // If this line executes, the whole build pipeline is working.
        assertTrue(true, "Stage 1 setup is complete — project compiles and tests run!");
    }
}

