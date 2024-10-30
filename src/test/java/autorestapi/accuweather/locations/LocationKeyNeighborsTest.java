package autorestapi.accuweather.locations;

import autorestapi.accuweather.AbstractAccuweatherTest;
import autorestapi.accuweather.locations.locationKey.LocationKey;
import io.qameta.allure.*;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import static io.restassured.RestAssured.given;

public class LocationKeyNeighborsTest extends AbstractAccuweatherTest {

    @Test
    @DisplayName("City Neighbors by locationKey")
    @Description("Returns information about neighboring cities, by location key.")
    @Severity(SeverityLevel.CRITICAL)
    @Story(value = "Request testing By Location key 56")
    @Link("https://developer.accuweather.com/accuweather-locations-api/apis")
    @Owner("Ekaterina Saldik")
    void getLocationKeyNeighbors() {
        List<LocationKey> response = given()
                .queryParam("apikey", getApiKey())
                .when()
                .get(getBaseUrl()+"/locations/v1/cities/neighbors/56")
                .then()
                .statusCode(200)
                .time(Matchers.lessThan(2000L))
                .extract()
                .body().jsonPath().getList(".", LocationKey.class);

        Assertions.assertEquals(10,response.size());
        Assertions.assertEquals("Beaver Lake", response.get(0).getLocalizedName());
    }
}