package org.kodenkel.test.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.kodenkel.test.Capability;
import org.kodenkel.test.TestContext;
import org.kodenkel.test.module.BackendModule;

import java.util.List;

import static org.junit.Assert.*;

public class PriceListSteps {
    TestContext context;
    BackendModule backend;

    public PriceListSteps(TestContext context) {
        this.context = context;
        this.backend = (BackendModule) context.getModule(Capability.BACKEND);
    }

    @Given("^the Price endpoint has been called through GET$")
    public void call_price_endpoint() {
        backend.simpleGetURI("coindeskPriceURL");
    }

    @Then("^the response code is (\\d{3})$")
    public void verify_response_ok(int expectedResponseCode) {
        assertEquals(backend.getResponse().getStatusCode(), expectedResponseCode);
    }

    @And("^the response is a valid JSON$")
    public void verify_response_mime_and_content() {
        assertEquals(backend.getResponse().getContentType().toLowerCase(), "application/javascript");
    }

    @And("^the response includes the following currency rates:$")
    public void verify_price_list_includes_currencies(List<String> currencyCodes) {
        for (String currencyCode : currencyCodes) {
            assertTrue(backend.isKeyPresentInJSONResponse("bpi." + currencyCode + ".code"));
        }
    }
}
