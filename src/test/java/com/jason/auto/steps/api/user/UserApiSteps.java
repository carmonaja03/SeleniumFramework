package com.jason.auto.steps.api.user;

import com.jason.auto.utilities.ApiUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import io.restassured.response.ValidatableResponse;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.core.IsInstanceOf;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.is;

@Slf4j
public class UserApiSteps {

    private ApiUtils apiUtils;
    private ValidatableResponse response;

    public UserApiSteps(ApiUtils apiUtils) {
        this.apiUtils = apiUtils;
    }

    @When("I send request to find all users")
    public void iSendRequestToFindAllUsers() {
        log.info("Find all users");
        response = given()
                .spec(apiUtils.getRequestSpec())
                .when().get("/user")
                .then().assertThat().log().ifError();
        apiUtils.setValidatableResponse(response);
    }

    @And("^The response to find all users is as expected$")
    public void theResponseToFindAllUsersIsAsExpected() {
        apiUtils.getValidatableResponse().assertThat()
                .body("content.id", everyItem(new IsInstanceOf(String.class)))
                .body("content.email", hasItem(startsWith("iris-")))
                .body("content.firstName", hasItem("Starter Project"))
                .body("content.lastName", hasItem(startsWith("User")))
                .body("content.name", hasItem(startsWith("Starter")))
                .body("pageable.sort.sorted", is(false))
                .body("pageable.sort.unsorted", is(true))
                .body("pageable.sort.empty", is(true))
                .body("pageable.pageSize", is(greaterThanOrEqualTo(1)))
                .body("pageable.pageNumber", is(greaterThanOrEqualTo(0)))
                .body("pageable.offset", is(greaterThanOrEqualTo(0)))
                .body("pageable.paged", is(true))
                .body("pageable.unpaged", is(false))
                .body("totalPages", is(greaterThanOrEqualTo(0)))
                .body("last", is(true))
                .body("totalElements", is(greaterThanOrEqualTo(0)))
                .body("first", is(true))
                .body("number", is(greaterThanOrEqualTo(0)))
                .body("sort.sorted", is(false))
                .body("sort.unsorted", is(true))
                .body("sort.empty", is(true))
                .body("numberOfElements", is(greaterThanOrEqualTo(0)))
                .body("size", is(greaterThanOrEqualTo(0)))
                .body("empty", is(false));
    }
}
