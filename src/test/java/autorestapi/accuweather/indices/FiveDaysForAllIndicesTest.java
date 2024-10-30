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

public class FiveDaysForAllIndicesTest extends AbstractAccuweatherTest {

    @Test
    @DisplayName("5 Days of Daily Index Values for All Indices")
    @Description("Returns 5 days of daily index data for all indices, by location key.")
    @Severity(SeverityLevel.NORMAL)
    @Story(value = "Request testing By Unique ID 52")
    @Link("https://developer.accuweather.com/accuweather-indices-api/apis")
    @Owner("Ekaterina Saldik")
    void getForFiveDaysForAllIndex() {

        List<FiveDay> response = given()
                .queryParam("apikey", getApiKey())
                .when()
                .get(getBaseUrl()+"/indices/v1/daily/5day/52")
                .then()
                .statusCode(200)
                .time(Matchers.lessThan(2000L))
                .extract()
                .body().jsonPath().getList(".", FiveDay.class);

        Assertions.assertEquals(240,response.size());
        Assertions.assertEquals("Flight Delays", response.get(0).getName());
    }
}
