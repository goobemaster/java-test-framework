package org.kodenkel.test;

import java.util.Collection;
import java.util.HashMap;

public final class TestContext {
    private static HashMap<Capability, TestModule> modules = new HashMap<>();

    public static void startModulesFromTags(Collection<String> tags) {
        try {
            for (Capability capability : Capability.getAll()) {
                if (tags.contains("@" + capability.name().toLowerCase()) && !TestContext.modules.containsKey(capability)) {
                    TestModule moduleInstance = capability.moduleImplementation.getConstructor().newInstance();
                    TestContext.modules.put(capability, moduleInstance);
                    moduleInstance.start();
                }
            }
        } catch (Exception e) {
            System.out.println("Failed to start one more modules...");
            System.exit(1);
        }
    }

    public static void stopAllModules() {
        TestContext.modules.forEach((capability, module) -> module.stop());
    }

    public TestModule getModule(Capability capability) {
        Class<? extends TestModule> moduleImplementation = capability.getModule();
        if (!TestContext.modules.containsKey(capability)) {
            throw new RuntimeException(
                    "The " + moduleImplementation.descriptorString() + " module isn't running." +
                    "Please use the associated capability tag (" + capability.name().toLowerCase() + ") for the scenario.");
        }

        return TestContext.modules.get(capability);
    }
}
