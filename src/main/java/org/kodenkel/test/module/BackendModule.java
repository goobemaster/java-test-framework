package org.kodenkel.test.module;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.kodenkel.test.TestModule;
import io.restassured.RestAssured;

public class BackendModule extends TestModule {
    RequestSpecification request;
    Response response;

    public boolean start() {
        RestAssured.baseURI = this.getProperty("coindeskBaseURL");
        RestAssured.config().getHttpClientConfig().reuseHttpClientInstance();
        return true;
    }

    public boolean stop() {
        return true;
    }

    public void simpleGetURI(String propertyKey) {
        request = RestAssured.given().header("User-Agent", "spoofUserAgent");
        response = request.get(this.getProperty(propertyKey));
    }

    public Response getResponse() {
        return response;
    }

    public boolean isKeyPresentInJSONResponse(String jsonPath) {
        JsonPath jsonPathEvaluator = response.jsonPath();
        String result = jsonPathEvaluator.get(jsonPath);
        return result != null;
    }
}
