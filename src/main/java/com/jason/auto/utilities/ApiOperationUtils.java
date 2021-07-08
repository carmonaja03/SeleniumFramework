package com.jason.auto.utilities;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import lombok.extern.slf4j.Slf4j;

import static io.restassured.RestAssured.given;

@Slf4j
public class ApiOperationUtils {
    private static final String GET_TOKEN_ENDPOINT = "URL.API.TOKEN";
    private static final String URL = "URL.API.BASEPATH";

    public void SetBaseUri() {
        RestAssured.baseURI = UrlOperationUtils.buildApiURL(URL);
    }

    public String authenticateUser(String role) {
        log.info("Retrieving Authentication Token");
        HelperUtils helperUtils = new HelperUtils();
        String username = helperUtils.getUsernameForRole(role);
        String password = PropertyReader.getProperty("PASSWORD");
        String client_id = PropertyReader.getProperty("CLIENT.ID");
        String client_secret = PropertyReader.getProperty("CLIENT.SECRET");

        String response = given()
                .params("username", username, "password", password,
                        "grant_type", "password", "scope", "openid")
                .auth()
                .preemptive()
                .basic(client_id, client_secret)
                .when()
                .get(UrlOperationUtils.buildLoginURL(GET_TOKEN_ENDPOINT))
                .then().log().ifError().extract().asString();
        JsonPath jsonPath = new JsonPath(response);
        return jsonPath.getString("access_token");
    }
}
