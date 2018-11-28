/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entity.FlightTicket;
import facade.DatboisTicket;
import facade.TicketFacade;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collections;
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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import ticket_handler.TicketHandler;

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
    @Path("mixtickets")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMix(String json, @QueryParam("from") int id, @QueryParam("to") int id2, @QueryParam("sort") String sort) {
        List<FlightTicket> pricesort = tf.getMixTickets();
        List<FlightTicket> ticks = new ArrayList<>();

        if (sort != null) {
            Collections.sort(pricesort);
        }

        if (id != 0 || id2 != 0) {
            if (id2 > pricesort.size()) {
                id2 = pricesort.size();
            }
            for (int i = id; i < id2; i++) {
                FlightTicket ticket = pricesort.get(i);
                ticks.add(ticket);
            }
            pricesort = ticks;
        }

        return Response.ok(gson.toJson(pricesort)).build();
    }

    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("alltickets")
    public Response getAllTickets(String json, @QueryParam("from") int id, @QueryParam("to") int id2, @QueryParam("Sort") String sort) throws MalformedURLException, IOException {
        
       
        
        List<FlightTicket> pricesort = tf.getAllTickets();
        List<FlightTicket> ticks = new ArrayList();
        
 
        if (sort != null) {
            Collections.sort(pricesort);
        }

        if (id != 0 || id2 != 0) {
            if (id2 > pricesort.size()) {
                id2 = pricesort.size();
            }
            for (int i = id; i < id2; i++) {
                FlightTicket ticket = pricesort.get(i);
                ticks.add(ticket);
            }
            pricesort = ticks;
        }
        return Response.ok(gson.toJson(pricesort)).build();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("foundtickets")
    public Response getFoundTickets(String json, @QueryParam("from") int id, @QueryParam("to") int id2, 
            @QueryParam("Sort") String sort,@QueryParam("dept") String dept,@QueryParam("dest")String dest,
            @QueryParam("airline") String airline, @QueryParam("deptDate") String deptDate, @QueryParam("arrDate") String arrDate) throws MalformedURLException, IOException {
        
        TicketHandler th = new TicketHandler();
        
        List<FlightTicket> pricesort = th.ticketHandler(airline, dept, dest, deptDate, arrDate);
        List<FlightTicket> ticks = new ArrayList();
   
        if (sort != null) {
            Collections.sort(pricesort);
        }

        if (id != 0 || id2 != 0) {
            if (id2 > pricesort.size()) {
                id2 = pricesort.size();
            }
            for (int i = id; i < id2; i++) {
                FlightTicket ticket = pricesort.get(i);
                ticks.add(ticket);
            }
            pricesort = ticks;
        }
        return Response.ok(gson.toJson(pricesort)).build();
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
