package org.test.covid_api;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.get;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;

public class CovidApiRootTest extends BaseApiTest {

    @Test
    @DisplayName("Check that response for root path contains 'allRoute' and 'summaryRoute'")
    void testRoutes() {
        get()
                .then()
                .assertThat()
                .spec(responseSpecification)
                .and()
                .assertThat()
                .rootPath("allRoute")
                .body("Name", equalTo("Get All Data"))
                .body("Description", equalTo("Returns all data in the system. " +
                        "Warning: this request returns 8MB+ and takes 5+ seconds"))
                .body("Path", equalTo("/all"))
                .detachRootPath("")
                .rootPath("summaryRoute")
                .body("Name", equalTo("Summary of new and total cases per country"))
                .body("Description", equalTo("A summary of new and total cases per country"))
                .body("Path", equalTo("/summary"));
    }


    @Test
    @DisplayName("Check that json schema is valid for 'List all the current routes'")
    void checkRoutesSchema() {
        get()
                .then()
                .assertThat()
                .spec(responseSpecification)
                .and()
                .assertThat()
                .body(matchesJsonSchemaInClasspath("routes-schema.json"));
    }
}
