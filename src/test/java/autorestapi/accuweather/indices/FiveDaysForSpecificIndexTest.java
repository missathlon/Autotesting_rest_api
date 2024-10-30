package autorestapi.accuweather.indices;

import autorestapi.accuweather.AbstractAccuweatherTest;
import autorestapi.accuweather.indices.fiveDay.FiveDay;
import io.qameta.allure.*;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import static io.restassured.RestAssured.given;

public class FiveDaysForSpecificIndexTest extends AbstractAccuweatherTest {

    @Test
    @DisplayName("5 Days of Daily Index Values for a Specific Index")
    @Description("Returns 5 days of daily index data for a specific index, by location key.")
    @Severity(SeverityLevel.NORMAL)
    @Story(value = "Request testing By Unique ID 52, group ID 8")
    @Link("https://developer.accuweather.com/accuweather-indices-api/apis")
    @Owner("Ekaterina Saldik")
    void getForFiveDaysForASpecificIndex() {

        List<FiveDay> response = given()
                .queryParam("apikey", getApiKey())
                .when()
                .get(getBaseUrl()+"/indices/v1/daily/5day/52/8")
                .then()
                .statusCode(200)
                .time(Matchers.lessThan(2000L))
                .extract()
                .body().jsonPath().getList(".", FiveDay.class);

        Assertions.assertEquals(5,response.size());
        Assertions.assertEquals("Outdoor Concert Forecast", response.get(0).getName());
    }
}
