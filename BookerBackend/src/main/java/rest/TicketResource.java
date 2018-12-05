/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import cors.CorsResponseFilter;
import entity.FlightTicket;
import facade.DatboisTicket;
import facade.MixTicket;
import facade.TicketFacade;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
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

/**
 * REST Web Service
 *
 * @author sidad
 */
@Path("ticket")
public class TicketResource {

    @Context
    private UriInfo context;
    
    public String[] hostlist = {
            "http://localhost:8080/BookerBackend/api/ticket/mixtickets", 
            "https://emilvh.dk/DATFlights/api/flights"};

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
    @Path("alltickets")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllTickets(String json) throws MalformedURLException, IOException {
        MixTicket mt = new MixTicket();
        DatboisTicket dt = new DatboisTicket();
        List<FlightTicket> mix = mt.getMix();
        
        List<FlightTicket> datbois = dt.getDatbois();
        
        for(int i = 0; i < datbois.size(); i++ ){
            mix.add(datbois.get(i));
        }
        return Response.ok(gson.toJson(mix)).build();
    }
    
    @GET
    @Path("mixtickets")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMix(String json){
        List<FlightTicket> list = tf.getMixTickets();
        
        return Response.ok(gson.toJson(list)).build();
    }


    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("foundtickets")
    public Response getAllTickets(String json, @QueryParam("from") int from, @QueryParam("to") int to, @QueryParam("dept") String dept, @QueryParam("dest") String dest) throws MalformedURLException, IOException, InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        List<FlightTicket> ticks = new ArrayList();
        List<Future<String>> list = new ArrayList();
        ArrayList<FlightTicket> returnList = new ArrayList();
        for (int i = 0; i < hostlist.length; i++){
            Callable<String> callable = new TicketFacade(hostlist[i]);
            Future<String> future = executor.submit(callable);
            FlightTicket[] mylist = gson.fromJson(future.get(), FlightTicket[].class);
            //list.add(future);
            for (FlightTicket mylist1 : mylist) {
                returnList.add(mylist1);
            }
        }
        executor.shutdown();
        
        List<FlightTicket> blaList = returnList.stream().filter(ticket -> ticket.getDeparture().equals(dept) && ticket.getDestination().equals(dest)).collect(Collectors.toList());
        
         if (from != 0 || to != 0) {
            if (to > blaList.size()) {
                to = blaList.size();
            }
            for (int i = from; i < to; i++) {
                FlightTicket ticket = blaList.get(i);
                ticks.add(ticket);
            }
            blaList = ticks;
        }
        return Response.ok(gson.toJson(blaList)).build();
    
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
