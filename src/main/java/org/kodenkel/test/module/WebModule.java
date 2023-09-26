package org.kodenkel.test.module;

import org.kodenkel.test.TestModule;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class WebModule extends TestModule {
    WebDriver driver;

    public boolean start() {
        try {
            System.setProperty("webdriver.firefox.driver", WebModule.class.getClassLoader().getResource("driver/geckodriver").getPath());
            driver = new FirefoxDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    public boolean stop() {
        this.driver.quit();
        return this.driver == null;
    }

    public WebDriver getDriver() {
        return this.driver;
    }
}
