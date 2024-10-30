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

public class OneDayValuesAllIndicesTest extends AbstractAccuweatherTest {

    @Test
    @DisplayName("1 Day of Daily Index Values for All Indices")
    @Description("Returns daily index data for all indices, by location key.")
    @Severity(SeverityLevel.NORMAL)
    @Story(value = "Request testing By ID 5")
    @Link("https://developer.accuweather.com/accuweather-indices-api/apis")
    @Owner("Ekaterina Saldik")
    void getOneDayValuesGroup() {

        List<OneDay> response = given()
                .queryParam("apikey", getApiKey())
                .when()
                .get(getBaseUrl()+"/indices/v1/daily/1day/5")
                .then()
                .statusCode(200)
                .time(Matchers.lessThan(2000L))
                .extract()
                .body().jsonPath().getList(".", OneDay.class);

        Assertions.assertEquals(48,response.size());
        Assertions.assertEquals("Flight Delays", response.get(0).getName());
    }
}
