package autorestapi.accuweather.locations;

import autorestapi.accuweather.AbstractAccuweatherTest;
import autorestapi.accuweather.locations.list.Country;
import io.qameta.allure.*;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import static io.restassured.RestAssured.given;

public class ListCountryTest extends AbstractAccuweatherTest {

    @Test
    @DisplayName("Country List")
    @Description("Returns basic information about all countries within a specified region.")
    @Severity(SeverityLevel.MINOR)
    @Story(value = "Request testing By Location key OCN")
    @Link("https://developer.accuweather.com/accuweather-locations-api/apis")
    @Owner("Ekaterina Saldik")
    void getListCountry() {

        List<Country> response = given()
                .queryParam("apikey", getApiKey())
                .when()
                .get(getBaseUrl()+"/locations/v1/countries/OCN")
                .then()
                .statusCode(200)
                .time(Matchers.lessThan(2000L))
                .extract()
                .body().jsonPath().getList(".", Country.class);

        Assertions.assertEquals(27,response.size());
        Assertions.assertEquals("American Samoa", response.get(0).getLocalizedName());
    }
}