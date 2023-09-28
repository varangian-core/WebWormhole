package com.varangian.webwormhole;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

@QuarkusTest
public class UrlShortenerResourceTest {

    @Inject
    UrlShortenerService urlShortenerService;

    @Test
    public void testCreateShortUrl() {
        String longUrl = "https://www.google.com/";
        String shortUrl = urlShortenerService.createShortUrl(longUrl);

        given()
                .contentType(ContentType.JSON)
                .body("{\"longUrl\": \"" + longUrl + "\"}")
                .when().post("/shorten")
                .then()
                .statusCode(200)
                .body("shortUrl", equalTo(shortUrl));
    }
}