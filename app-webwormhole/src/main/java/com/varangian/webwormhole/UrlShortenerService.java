package com.varangian.webwormhole;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.UUID;

@ApplicationScoped
public class UrlShortenerService {

    @Inject
    UrlShortenerRepository urlShortenerRepository;

    public String createShortUrl(String longUrl) {
        String shortUrl = urlShortenerRepository.getShortUrl(longUrl);
        if (shortUrl == null) {
            shortUrl = generateShortUrl();
            urlShortenerRepository.saveUrlMapping(shortUrl, longUrl);
        }
        return shortUrl;
    }

    public String getLongUrl(String shortUrl) {
        return urlShortenerRepository.getLongUrl(shortUrl);
    }

    private String generateShortUrl() {
        String shortUrl = null;
        do {
            shortUrl = "http://localhost:8081/" + UUID.randomUUID().toString().substring(0, 8);
        } while (urlShortenerRepository.getLongUrl(shortUrl) != null);
        return shortUrl;
    }
}