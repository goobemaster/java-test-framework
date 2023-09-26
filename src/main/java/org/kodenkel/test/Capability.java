package org.kodenkel.test;

import org.kodenkel.test.module.BackendModule;
import org.kodenkel.test.module.MockServerModule;
import org.kodenkel.test.module.WebModule;

public enum Capability {
    WEB         (WebModule.class),
    BACKEND     (BackendModule.class),
    MOCKSERVER  (MockServerModule.class);

    public final Class<? extends TestModule> moduleImplementation;

    Capability(Class<? extends TestModule> moduleImplementation) {
        this.moduleImplementation = moduleImplementation;
    }

    public static Capability[] getAll() {
        return Capability.values();
    }

    public Class<? extends TestModule> getModule() {
        return this.moduleImplementation;
    }
}
