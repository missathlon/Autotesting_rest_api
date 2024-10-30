package autorestapi.accuweather.indices;

import autorestapi.accuweather.AbstractAccuweatherTest;
import autorestapi.accuweather.indices.oneDay.OneDay;
import io.qameta.allure.*;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import static io.restassured.RestAssured.given;

public class OneDayValuesSpecificIndexTest extends AbstractAccuweatherTest {

    @Test
    @DisplayName("1 Day of Daily Index Values for a Specific Index")
    @Description("Returns daily index data for a specific index, by location key.")
    @Severity(SeverityLevel.NORMAL)
    @Story(value = "Request testing By Unique ID 5, group ID 8")
    @Link("https://developer.accuweather.com/accuweather-indices-api/apis")
    @Owner("Ekaterina Saldik")
    void getOneDayValuesGroup() {

        List<OneDay> response = given()
                .queryParam("apikey", getApiKey())
                .when()
                .get(getBaseUrl()+"/indices/v1/daily/1day/5/8")
                .then()
                .statusCode(200)
                .time(Matchers.lessThan(2000L))
                .extract()
                .body().jsonPath().getList(".", OneDay.class);

        Assertions.assertEquals(1,response.size());
        Assertions.assertEquals("Outdoor Concert Forecast", response.get(0).getName());
    }
}
