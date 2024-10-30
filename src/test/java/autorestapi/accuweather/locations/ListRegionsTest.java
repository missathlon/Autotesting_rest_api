package autorestapi.accuweather.locations;

import autorestapi.accuweather.AbstractAccuweatherTest;
import autorestapi.accuweather.locations.list.Regions;
import io.qameta.allure.*;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import static io.restassured.RestAssured.given;

public class ListRegionsTest extends AbstractAccuweatherTest {



    @Test
    @DisplayName("Region List")
    @Description("Returns basic information about all regions.")
    @Severity(SeverityLevel.MINOR)
    @Story(value = "Request testing")
    @Link("https://developer.accuweather.com/accuweather-locations-api/apis")
    @Owner("Ekaterina Saldik")
    void getListRegions() {

        List<Regions> response = given()
                .queryParam("apikey", getApiKey())
                .when()
                .get(getBaseUrl()+"/locations/v1/regions")
                .then()
                .statusCode(200)
                .time(Matchers.lessThan(2000L))
                .extract()
                .body().jsonPath().getList(".", Regions.class);

        Assertions.assertEquals(10,response.size());
        Assertions.assertEquals("Oceania", response.get(8).getLocalizedName());
    }
}