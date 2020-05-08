package org.test.covid_api;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.parallel.Execution;
import org.test.covid_api.domain.TestConfig;

import static org.junit.jupiter.api.parallel.ExecutionMode.CONCURRENT;

@Execution(CONCURRENT)
public class BaseApiTest {

    static ResponseSpecification responseSpecification;

    @BeforeAll
    static void setup() {
        RestAssured.baseURI = TestConfig.BASE_URI;
        RestAssured.filters(new AllureRestAssured());
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        responseSpecification = new ResponseSpecBuilder()
                .expectContentType("application/json; charset=UTF-8")
                .expectStatusCode(HttpStatus.SC_OK)
                .build();
    }


}
