package autorestapi.accuweather.current;

import autorestapi.accuweather.AbstractAccuweatherTest;
import autorestapi.accuweather.conditions.current.CurrentCondition;
import io.qameta.allure.*;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class CurrentConditionsTest extends AbstractAccuweatherTest {

    @Test
    @DisplayName("CurrentConditionsTest")
    @Description("GET Current Conditions")
    @Severity(SeverityLevel.NORMAL)
    @Story(value = "Request testing By ID 15")
    @Link("https://developer.accuweather.com/accuweather-current-conditions-api/apis")
    @Owner("Ekaterina Saldik")
    void getCurrentConditions() {

        List<CurrentCondition> response = given()
                .queryParam("apikey", getApiKey())
                .when()
                .get(getBaseUrl()+"/currentconditions/v1/15")
                .then()
                .statusCode(200)
                .time(Matchers.lessThan(2000L))
                .extract()
                .body().jsonPath().getList(".", CurrentCondition.class);

        Assertions.assertEquals(1,response.size());
        Assertions.assertEquals("http://www.accuweather.com/en/jp/oga-shi/16/current-weather/16?lang=en-us",
                response.get(0).getMobileLink());
    }
}
