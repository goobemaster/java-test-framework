package org.kodenkel.test;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public abstract class TestModule {
    Properties properties;

    public TestModule() {
        String moduleName = this.getClass().getSimpleName().toLowerCase().replace("module", "");
        String propertiesFilePath = TestModule.class.getClassLoader().getResource(moduleName + ".properties").getPath();
        File propertiesFile = new File(propertiesFilePath);
        if (!propertiesFile.exists()) return;

        try {
            this.properties = new Properties();
            this.properties.load(new FileInputStream(propertiesFile));
        } catch (Exception e) {
            System.out.println("Properties file could not be read for Module: " + this.getClass().getName());
        }
    }

    @Override
    protected void finalize() {
        if (!this.stop()) {
            throw new RuntimeException("Error during teardown of " + this.getClass().getName() + " module!");
        }
    }

    public abstract boolean start();
    public abstract boolean stop();

    public String getProperty(String key) {
        return this.properties.getProperty(key);
    }
}
