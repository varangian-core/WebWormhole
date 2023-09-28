package com.varangian.webwormhole;

public class UrlShortenerRequest {

    private String longUrl;

    public UrlShortenerRequest() {
    }

    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }
}