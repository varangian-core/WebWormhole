package com.varangian.webwormhole;

public class UrlShortenerResponse {

    private String shortUrl;

    public UrlShortenerResponse() {
    }

    public UrlShortenerResponse(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }
}