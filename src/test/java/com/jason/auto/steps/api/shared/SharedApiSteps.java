package com.jason.auto.steps.api.shared;

import com.jason.auto.utilities.ApiOperationUtils;
import com.jason.auto.utilities.ApiUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;

import java.util.Map;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

@Slf4j
public class SharedApiSteps {
    private ApiUtils apiUtils;
    private ValidatableResponse json;

    public SharedApiSteps(ApiUtils apiUtils) {
        this.apiUtils = apiUtils;
    }

    @Given("^I am authenticated as (.*)")
    public void getTokenAndSetHeader(String role) {
        ApiOperationUtils apiOperationUtils = new ApiOperationUtils();
        apiOperationUtils.SetBaseUri();
        apiUtils.setAccessToken(apiOperationUtils.authenticateUser(role));
        Assert.assertNotNull("Authentication Token is null", apiUtils.getAccessToken());
        log.info("Successfully retrieved Authentication Token");
        RestAssuredConfig setRequestSpec = RestAssured.config().httpClient(HttpClientConfig.httpClientConfig().
                setParam("http.connection.timeout", 30000).
                setParam("http.socket.timeout", 30000).
                setParam("http.connection-manager.timeout", 30000));
        apiUtils.setRequestConfig(setRequestSpec);
        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.setContentType(ContentType.JSON);
        builder.addHeader("Authorization", "bearer " + apiUtils.getAccessToken());
        RequestSpecification requestSpec = builder.build();
        apiUtils.setRequestSpec(requestSpec);
        log.info("Request header setting complete");
    }

    @Then("^The status code is (\\d+)$")
    public void verifyStatusCode(int statusCode) {
        json = apiUtils.getValidatableResponse().assertThat().statusCode(statusCode);
    }

    @And("Response includes the following$")
    public void responseEquals(Map<String, String> responseFields) {
        for (Map.Entry<String, String> field : responseFields.entrySet()) {
            if (StringUtils.isNumeric(field.getValue())) {
                json.body(field.getKey(), equalTo(Integer.parseInt(field.getValue())));
            } else {
                json.body(field.getKey(), containsString((field.getValue())));
            }
        }
    }

    @Then("The response content-type is (.*)")
    public void checkThatPdfIsReturned(String contentType) {
        json = apiUtils.getValidatableResponse().assertThat().contentType(contentType);
    }
}
