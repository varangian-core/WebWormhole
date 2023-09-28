import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import com.varangian.webwormhole.UrlShortenerService;
import jakarta.inject.Inject;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.notNullValue;

@QuarkusTest
public class UrlShortenerResourceTest {

    @Inject
    UrlShortenerService urlShortenerService;

    @Test
public void testCreateShortUrl() {
    given()
            .contentType("application/json")
            .body("{\"longUrl\":\"http://www.example.com\"}")
            .when()
            .log().all() // log request details
            .post("/shorten")
            .then()
            .log().all() // log response details
            .statusCode(200)
            .body("shortUrl", notNullValue());
}
}
