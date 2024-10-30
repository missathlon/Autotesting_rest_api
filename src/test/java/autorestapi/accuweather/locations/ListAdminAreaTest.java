package autorestapi.accuweather.locations;

import autorestapi.accuweather.AbstractAccuweatherTest;
import autorestapi.accuweather.locations.list.AdminArea;
import io.qameta.allure.*;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import static io.restassured.RestAssured.given;


public class ListAdminAreaTest extends AbstractAccuweatherTest {


    @Test
    @DisplayName("Admin Area List")
    @Description("Returns basic information about administrative areas in the specified country.")
    @Severity(SeverityLevel.MINOR)
    @Story(value = "Request testing By Country key by")
    @Link("https://developer.accuweather.com/accuweather-locations-api/apis")
    @Owner("Ekaterina Saldik")
    void getListAdminArea() {

        List<AdminArea> response = given()
                .queryParam("apikey", getApiKey())
                .when()
                .get(getBaseUrl()+"/locations/v1/adminareas/by")
                .then()
                .statusCode(200)
                .time(Matchers.lessThan(2000L))
                .extract()
                .body().jsonPath().getList(".", AdminArea.class);

        Assertions.assertEquals(7,response.size());
        Assertions.assertEquals("Brest", response.get(0).getLocalizedName());
    }
}
