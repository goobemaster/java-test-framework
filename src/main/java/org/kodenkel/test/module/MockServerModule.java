package org.kodenkel.test.module;

import org.kodenkel.test.TestModule;

public class MockServerModule extends TestModule {
    public boolean start() {
        return true;
    }

    public boolean stop() {
        return true;
    }
}
