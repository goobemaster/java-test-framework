package org.kodenkel.test.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "classpath:features/Website.feature",
        tags = "@smoke and not @wip",
        glue = {"org/kodenkel/test/stepDefinitions"}
)
public class SmokeTest {

}
