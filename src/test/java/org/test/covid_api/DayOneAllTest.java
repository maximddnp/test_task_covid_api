package org.test.covid_api;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.test.covid_api.domain.Countries;

import java.util.stream.Stream;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.test.covid_api.domain.Countries.FRANCE;
import static org.test.covid_api.domain.Countries.UKRAINE;

public class DayOneAllTest extends BaseApiTest {

    @ParameterizedTest(name = "Check that list of all cases by case type for a {0} " +
                                "from the first recorded case is {1}.")
    @MethodSource("provideCountries")
    void testListCases(Countries country, String expectedCases) {
        String cases = get("/dayone/country/" + country.getSlug())
                .then()
                .assertThat()
                .spec(responseSpecification)
                .and()
                .extract()
                .jsonPath()
                .getList("findAll {it.Date == '2020-04-13T00:00:00Z' && it.Province == ''}.Confirmed")
                .get(0)
                .toString();
        assertEquals(expectedCases, cases);
    }

    private static Stream<Arguments> provideCountries() {
        return Stream.of(
                Arguments.of(UKRAINE, "3102"),
                Arguments.of(FRANCE, "124298")
        );
    }

    @ParameterizedTest(name = "Check that json schema is valid for {0} 'Day One All Status'")
    @MethodSource("provideCountries")
    void checkCountriesSchema(Countries country) {
        get("/dayone/country/" + country.getSlug())
                .then()
                .assertThat()
                .spec(responseSpecification)
                .and()
                .assertThat()
                .body(matchesJsonSchemaInClasspath("cases-schema.json"));
    }

    @ParameterizedTest(name = "Check that list of all cases by case type for a {0} " +
                                "from the first recorded case is {1}.")
    @MethodSource("provideCountriesForDate")
    void testListCasesTotalByDate(Countries country, String expectedCases) {
        String cases =
                given()
                        .queryParam("from", "2020-03-24T00:00:00Z")
                        .queryParam("to", "2020-03-25T00:00:00Z")
                        .when()
                .get("/total/country/" + country.getSlug()  +"/status/confirmed")
                .then()
                .assertThat()
                .spec(responseSpecification)
                .and()
                .extract()
                .jsonPath()
                .getList("findAll {it.Date == '2020-03-24T00:00:00Z'}.Cases")
                .get(0)
                .toString();
        assertEquals(expectedCases, cases);
    }

    private static Stream<Arguments> provideCountriesForDate() {
        return Stream.of(
                Arguments.of(UKRAINE, "97"),
                Arguments.of(FRANCE, "22304")
        );
    }
}
