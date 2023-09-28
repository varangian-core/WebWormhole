package com.varangian.webwormhole;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

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
                .body("shortUrl", is("http://localhost:8081/1"));
    }
}