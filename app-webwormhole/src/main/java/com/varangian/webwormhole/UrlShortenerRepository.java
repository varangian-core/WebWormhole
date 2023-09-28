package com.varangian.webwormhole;

import javax.enterprise.context.ApplicationScoped;
import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class UrlShortenerRepository {

    private Map<String, String> urlMappings = new HashMap<>();

    public String getShortUrl(String longUrl) {
        return urlMappings.entrySet()
                .stream()
                .filter(entry -> entry.getValue().equals(longUrl))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null);
    }

    public String getLongUrl(String shortUrl) {
        return urlMappings.get(shortUrl);
    }

    public void saveUrlMapping(String shortUrl, String longUrl) {
        urlMappings.put(shortUrl, longUrl);
    }

    public String generateShortUrl() {
        do {
            shortUrl = "http://localhost:8081/" + UUID.randomUUID().toString().substring(0, 8);
        } while (urlShortenerRepository.getLongUrl(shortUrl) != null);
        return shortUrl;
    }
  
}