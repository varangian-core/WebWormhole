package com.varangian.webwormhole;

import jakarta.enterprise.context.ApplicationScoped;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class UrlShortenerRepository implements PanacheRepositoryBase<UrlMapping, Long> {

    private final Map<String, String> urlMappings = new HashMap<>();

    @ConfigProperty(name = "url.shortener.storage.type", defaultValue = "memory")
    String storageType;

    public String getShortUrl(String longUrl) {
        if ("db".equals(storageType)) {
            UrlMapping mapping = find("longUrl", longUrl).firstResult();
            return mapping != null ? mapping.getShortUrl() : null;
        } else {  // default is memory
            return urlMappings.entrySet()
                    .stream()
                    .filter(entry -> entry.getValue().equals(longUrl))
                    .map(Map.Entry::getKey)
                    .findFirst()
                    .orElse(null);
        }
    }

    public String getLongUrl(String shortUrl) {
        if ("db".equals(storageType)) {
            UrlMapping mapping = find("shortUrl", shortUrl).firstResult();
            return mapping != null ? mapping.getLongUrl() : null;
        } else {
            return urlMappings.get(shortUrl);
        }
    }

    public void saveUrlMapping(String shortUrl, String longUrl) {
        if ("db".equals(storageType)) {
            UrlMapping mapping = new UrlMapping();
            mapping.setShortUrl(shortUrl);
            mapping.setLongUrl(longUrl);
            persist(mapping);
        } else {
            urlMappings.put(shortUrl, longUrl);
        }
    }
}
