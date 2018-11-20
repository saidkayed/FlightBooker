/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import entity.FlightTicket;
import facade.TicketFacade;
import javax.persistence.Persistence;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author sidad
 */
@Path("ticket")
public class TicketResource {

    @Context
    private UriInfo context;

    Gson gson;

    TicketFacade tf = new TicketFacade(Persistence.createEntityManagerFactory("pu"));

    /**
     * Creates a new instance of GenericResource
     */
    public TicketResource() {
    }

    /**
     * Retrieves representation of an instance of rest.GenericResource
     *
     * @return an instance of java.lang.String
     */
    @Path("alltickets")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJson() {
        String json = gson.toJson(tf.getAllTickets().toString());

        //return Response.ok("{\"petCount\":\""+json+"\"}").build();
        return Response.ok(json).build();
    }

    /**
     * PUT method for updating or creating an instance of GenericResource
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postJson(String json) {
        FlightTicket t = gson.fromJson(json, FlightTicket.class);

        tf.CreateTicket(t);

        return Response.ok(json).build();
    }
}
