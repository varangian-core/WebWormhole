import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import jakarta.inject.Inject;
import static org.hamcrest.MatcherAssert.assertThat;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import com.varangian.webwormhole.UrlShortenerService;
import com.varangian.webwormhole.UrlShortenerRepository;
import com.varangian.webwormhole.UrlMapping;


@QuarkusTest
public class UrlShortenerResourceTest {

    @Inject
    UrlShortenerService urlShortenerService;

    @Inject
    UrlShortenerRepository urlShortenerRepository;

    @Test
    public void testRedirectToLongUrl() {
        String longUrl = "http://www.example.com";
        UrlMapping urlMapping = urlShortenerService.createShortUrl(new UrlMapping(longUrl));
        String shortUrl = urlMapping.getShortUrl();

        given()
                .when()
                .get(shortUrl)
                .then()
                .statusCode(302)
                .header("Location", equalTo(longUrl));
    }

    @Test
    public void testCreateShortUrl() {
        String longUrl = "http://www.example.com";
        given()
                .contentType("application/json")
                .body("{\"longUrl\":\"" + longUrl + "\"}")
                .when()
                .log().all() // log request details
                .post("/shorten")
                .then()
                .log().all() // log response details
                .statusCode(200)
                .body("shortUrl", notNullValue());

        // Check that the short URL is present in the database
        String shortUrl = "http://localhost:8081/";
        UrlMapping urlMapping = urlShortenerRepository.findByShortUrl(shortUrl);
        assertThat(urlMapping, notNullValue());
        assertThat(urlMapping.getLongUrl(), equalTo(longUrl));
    }
}