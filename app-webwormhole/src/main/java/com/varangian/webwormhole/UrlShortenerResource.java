package com.varangian.webwormhole;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;

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