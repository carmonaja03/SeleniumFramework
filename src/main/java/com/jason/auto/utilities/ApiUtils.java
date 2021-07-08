package com.jason.auto.utilities;

import io.restassured.config.RestAssuredConfig;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@Slf4j
public class ApiUtils {

    private ValidatableResponse validatableResponse;
    private RequestSpecification requestSpec;
    private String accessToken;
    private RestAssuredConfig requestConfig;
}
