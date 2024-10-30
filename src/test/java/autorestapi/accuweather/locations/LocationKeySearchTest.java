package autorestapi.accuweather.locations;

import autorestapi.accuweather.AbstractAccuweatherTest;
import autorestapi.accuweather.locations.locationKey.LocationKey;
import io.qameta.allure.*;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;

public class LocationKeySearchTest extends AbstractAccuweatherTest {

    @Test
    @DisplayName("Search by locationKey")
    @Description("Returns information about a specific location, by location key. ")
    @Severity(SeverityLevel.CRITICAL)
    @Story(value = "Request testing By Location key 316938")
    @Link("https://developer.accuweather.com/accuweather-locations-api/apis")
    @Owner("Ekaterina Saldik")
    void getLocationKeySearch() {

        LocationKey response = given()
                .queryParam("apikey", getApiKey())
                .when()
                .get(getBaseUrl()+"/locations/v1/316938")
                .then()
                .statusCode(200)
                .time(Matchers.lessThan(2000L))
                .extract()
                .response()
                .body().as(LocationKey.class);
        Assertions.assertEquals("Ankara", response.getLocalizedName());
    }
}