package autorestapi.accuweather.indices;

import autorestapi.accuweather.AbstractAccuweatherTest;
import autorestapi.accuweather.indices.metadata.Metadata;
import io.qameta.allure.*;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import static io.restassured.RestAssured.given;

public class ListInASpecificGroupTest extends AbstractAccuweatherTest {

    @Test
    @DisplayName("List of Indices in a Specific Group")
    @Description("Returns metadata for all indices in a specified group.")
    @Severity(SeverityLevel.TRIVIAL)
    @Story(value = "Request testing By group ID 8")
    @Link("https://developer.accuweather.com/accuweather-indices-api/apis")
    @Owner("Ekaterina Saldik")
    void getListInASpecificGroup() {

        List<Metadata> response = given()
                .queryParam("apikey", getApiKey())
                .when()
                .get(getBaseUrl()+"/indices/v1/daily/groups/8")
                .then()
                .statusCode(200)
                .time(Matchers.lessThan(2000L))
                .extract()
                .body().jsonPath().getList(".", Metadata.class);

        Assertions.assertEquals(3,response.size());
        Assertions.assertEquals("Fishing Forecast", response.get(0).getName());
    }
}
