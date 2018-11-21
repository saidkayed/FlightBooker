/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entity.FlightTicket;
import facade.TicketFacade;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import javax.persistence.Persistence;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
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
        gson = new GsonBuilder().setPrettyPrinting().create();
    }

    /**
     * Retrieves representation of an instance of rest.GenericResource
     *
     * @return an instance of java.lang.String
     */
    @Path("alltickets/start={id}&end={id2}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJson(String json,@PathParam("id") int id,@PathParam("id2")int id2) {
        System.out.println(tf.Ticket_Pagination(id, id2));
       
//String json = gson.toJson(tf.getAllTickets());
        //return Response.ok("{\"petCount\":\""+json+"\"}").build();
        
        return Response.ok(gson.toJson(tf.Ticket_Pagination(id,id2))).build();
    }
    
    @Path("alltickets")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJson(String json) {
       
//String json = gson.toJson(tf.getAllTickets());
        //return Response.ok("{\"petCount\":\""+json+"\"}").build();
        
        return Response.ok(gson.toJson(tf.getAllTickets())).build();
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
