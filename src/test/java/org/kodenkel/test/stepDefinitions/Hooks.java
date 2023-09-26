package org.kodenkel.test.stepDefinitions;

import io.cucumber.java.*;
import org.kodenkel.test.TestContext;

public class Hooks {
    private final TestContext context;

    public Hooks(TestContext context) {
        this.context = context;
    }

    @Before
    public void beforeEachScenario(Scenario scenario) throws Exception {
        TestContext.startModulesFromTags(scenario.getSourceTagNames());
    }

    @AfterAll
    public static void afterAll() {
        TestContext.stopAllModules();
    }
}
