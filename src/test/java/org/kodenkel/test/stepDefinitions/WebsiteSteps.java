package org.kodenkel.test.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import org.kodenkel.test.Capability;
import org.kodenkel.test.TestContext;
import org.kodenkel.test.module.WebModule;

import static org.junit.Assert.*;

public class WebsiteSteps {
    TestContext context;
    WebModule web;

    long pageLoadRequestedAt;

    public WebsiteSteps(TestContext context) {
        this.context = context;
        this.web = (WebModule) context.getModule(Capability.WEB);
    }

    @Given("^the website visitor is on the landing page$")
    public void user_is_on_the_landing_page() {
        this.pageLoadRequestedAt = System.currentTimeMillis();
        this.web.getDriver().get("https://www.competitionlabs.com");
    }

    @And("^the website loads in a reasonable time$")
    public void verify_page_load_time() {
        long pageFullyLoadedAt = System.currentTimeMillis();
        long pageLoadTime = pageFullyLoadedAt - this.pageLoadRequestedAt;
        assertTrue("Page load took " + pageLoadTime + " ms! Page was expected to load under 3 seconds.", pageLoadTime < 3000);
    }
}
