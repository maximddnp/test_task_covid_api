package org.test.covid_api;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.get;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

public class CountriesTest extends BaseApiTest {

    @Test
    @DisplayName("Check that list of countries contains info about 'Liechtenstein'")
    void testCountries() {
        get("/countries")
                .then()
                .assertThat()
                .spec(responseSpecification)
                .and()
                .assertThat()
                .rootPath("find {it.Country == 'Liechtenstein'}")
                .body("Slug",  is("liechtenstein"))
                .body("ISO2", is("LI"));
    }

    @Test
    @DisplayName("Check that json schema is valid for 'List all the countries'")
    void checkCountriesSchema() {
        get("/countries")
                .then()
                .assertThat()
                .spec(responseSpecification)
                .and()
                .assertThat()
                .body(matchesJsonSchemaInClasspath("countries-schema.json"));
    }
}
