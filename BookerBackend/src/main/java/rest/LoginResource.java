package rest;

import com.google.gson.Gson;
import entity.User;
import entity.UserFacade;
import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

/**
 * REST Web Service
 *
 * @author lam@cphbusiness.dk
 */
@Path("info")
public class LoginResource {
 EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
 
    
    Gson gson;
    @Context
    private UriInfo context;
    
    @Context
    SecurityContext securityContext;

    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("user")
    @RolesAllowed("user")
    public String getFromUser(){
        String user = securityContext.getUserPrincipal().getName();
        return "\"Rest endpoint from User "+"\"";
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("admin")
    @RolesAllowed("admin")
    public String getFromAdmin() {
        String user = securityContext.getUserPrincipal().getName();
        return "\"Rest endpoint from Admin"+"\"";
    }
   
    
}


