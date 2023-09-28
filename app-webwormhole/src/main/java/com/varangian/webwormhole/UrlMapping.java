package com.varangian.webwormhole;

public class UrlMapping {

    private String shortUrl;
    private String longUrl;

    public UrlMapping() {
    }

    public UrlMapping(String longUrl) {
        this.longUrl = longUrl;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }
}