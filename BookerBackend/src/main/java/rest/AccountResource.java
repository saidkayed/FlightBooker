/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import entity.Account;
import entity.FlightTicket;
import facade.AccountFacade;
import facade.TicketFacade;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.security.RolesAllowed;
import javax.naming.AuthenticationException;
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
import static javax.ws.rs.client.Entity.json;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;


/**
 * REST Web Service
 *
 * @author sidad
 */


@Path("account")
public class AccountResource {
  
    
    Gson gson;

     AccountFacade af = new AccountFacade(Persistence.createEntityManagerFactory("pu"));


    @Context
    private UriInfo context;

    public AccountResource() {
         gson = new GsonBuilder().setPrettyPrinting().create();
    }

 @GET
 @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
 @Path("login")
    public Response getJson(String json,@QueryParam("username") String username,@QueryParam("password") String password) throws AuthenticationException {
        Account acc = gson.fromJson(json, Account.class);

        af.getVeryfiedUser(username, password);

        return Response.ok(json).build();
    }

 
    
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putJson(String json) {
        Account acc = gson.fromJson(json, Account.class);

        af.createAccount(acc);

        return Response.ok(json).build();
    }
}
