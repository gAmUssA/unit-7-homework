package com.farata.course.mwd.auction.resource;

import com.farata.course.mwd.auction.entity.Bid;
import com.farata.course.mwd.auction.service.QueueService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import java.math.BigDecimal;

@Path("bid")
@Produces(MediaType.APPLICATION_JSON)
public class BidResource {

    private static final Logger logger = LoggerFactory.getLogger(BidResource.class);

    private QueueService queueService;

    @Inject
    public void setQueueService(final QueueService queueService) {
        this.queueService = queueService;
    }

    // TODO: Provide actual implementation
    @GET
    @Path("/{id}/")
    public Bid getBid(@PathParam("id") int id, @Context HttpHeaders headers) {
        return new Bid(id, new BigDecimal(42));
    }

    // TODO: Provide actual implementation
    @POST
    public Bid placeBid(/*@Valid*/ Bid bid) {

        return new Bid();

        //if ()
    }

}
