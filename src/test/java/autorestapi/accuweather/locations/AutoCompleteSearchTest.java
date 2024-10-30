package autorestapi.accuweather.locations;

import autorestapi.accuweather.AbstractAccuweatherTest;
import autorestapi.accuweather.locations.autocomplete.AutocompleteSearch;
import io.qameta.allure.*;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import static io.restassured.RestAssured.given;

public class AutoCompleteSearchTest extends AbstractAccuweatherTest {

    @Test
    @DisplayName("Autocomplete search")
    @Description("Returns basic information about locations matching an autocomplete of the search text.")
    @Severity(SeverityLevel.MINOR)
    @Story(value = "Request testing By Location key Ankara")
    @Link("https://developer.accuweather.com/accuweather-locations-api/apis")
    @Owner("Ekaterina Saldik")
    void getAutoCompleteSearch() {

        List<AutocompleteSearch> response = given()
                .queryParam("apikey", getApiKey())
                .param("q", "Ankara")
                .when()
                .get(getBaseUrl()+"/locations/v1/cities/autocomplete")
                .then()
                .statusCode(200)
                .time(Matchers.lessThan(2000L))
                .extract()
                .body().jsonPath().getList(".", AutocompleteSearch.class);

        Assertions.assertEquals(10,response.size());
        Assertions.assertEquals("Ankara", response.get(0).getLocalizedName());
        Assertions.assertEquals("316938", response.get(0).getKey());

    }
}
