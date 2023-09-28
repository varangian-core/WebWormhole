package com.varangian.webwormhole;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;

@Path("/url")
public class UrlShortenerResource {

    @Inject
    UrlShortenerService urlShortenerService;

    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public Response createShortUrl(String longUrl) {
        String shortUrl = urlShortenerService.createShortUrl(longUrl);
        URI uri = URI.create(shortUrl);
        return Response.created(uri).entity(shortUrl).build();
    }
}