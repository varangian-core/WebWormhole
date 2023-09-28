package com.varangian.webwormhole;

import jakarta.enterprise.context.ApplicationScoped;
import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class UrlShortenerRepository {

    private final Map<String, String> urlMappings = new HashMap<>();

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
}
