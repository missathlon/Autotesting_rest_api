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

public class TextSearchCityTest extends AbstractAccuweatherTest {

    @Test
    @DisplayName("City Search")
    @Description("Returns information for an array of cities that match the search text.")
    @Severity(SeverityLevel.CRITICAL)
    @Story(value = "Request testing By Ankara")
    @Link("https://developer.accuweather.com/accuweather-locations-api/apis")
    @Owner("Ekaterina Saldik")
    void getTextSearchCity() {

        List<LocationKey> response = given()
                .queryParam("apikey", getApiKey())
                .queryParam("q", "Ankara")
                .when()
                .get(getBaseUrl()+"/locations/v1/cities/search")
                .then()
                .statusCode(200)
                .time(Matchers.lessThan(2000L))
                .extract()
                .body().jsonPath().getList(".", LocationKey.class);

        Assertions.assertEquals("Ankara", response.get(0).getEnglishName());
    }
}
