package com.varangian.webwormhole;

import javax.enterprise.context.ApplicationScoped;
import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class UrlShortenerService {

    private Map<String, String> urlMap = new HashMap<>();

    public String createShortUrl(String longUrl) {
        String shortUrl = "http://localhost:8080/" + Integer.toHexString(longUrl.hashCode());
        urlMap.put(shortUrl, longUrl);
        return shortUrl;
    }

    public String getLongUrl(String shortUrl) {
        return urlMap.get(shortUrl);
    }
}