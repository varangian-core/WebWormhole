package com.varangian.webwormhole;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/")
public class UrlShortenerResource {

    @Inject
    UrlShortenerService urlShortenerService;

    @POST
    @Path("/shorten")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response shortenUrl(UrlShortenerRequest request) {
        String shortUrl = urlShortenerService.createShortUrl(request.getLongUrl());
        UrlShortenerResponse response = new UrlShortenerResponse(shortUrl);
        return Response.ok(response).build();
    }
}
