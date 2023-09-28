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
                .when()
                .body("{\"longUrl\":\"http://www.example.com\"}")
                .post("/shorten")
                .then()
                .statusCode(200)
                .body("shortUrl", notNullValue());
    }
}