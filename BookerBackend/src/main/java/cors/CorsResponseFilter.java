package cors;

import facade.TicketFacade;
import java.io.IOException;
import java.util.logging.Logger;
import javax.persistence.Persistence;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.ext.Provider;

//Comment out the two annotations below to disable CORS-handling
@Provider
@PreMatching
public class CorsResponseFilter implements ContainerResponseFilter {
     TicketFacade tf = new TicketFacade(Persistence.createEntityManagerFactory("pu"));
  private final static Logger LOG = Logger.getLogger(CorsResponseFilter.class.getName());
  @Override
  public void filter( ContainerRequestContext requestCtx, ContainerResponseContext res )
    throws IOException {
    LOG.info( "Executing REST response filter" );
    res.getHeaders().add("Access-Control-Allow-Origin", "*" );
    res.getHeaders().add("Access-Control-Allow-Credentials", "true" );
    res.getHeaders().add("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT" );
    res.getHeaders().add("Access-Control-Allow-Headers", "Origin, Accept, Content-Type, Authorization,x-access-token");
     res.getHeaders().add("Access-Control-Expose-Headers","x-total-count" );  
    res.getHeaders().add("X-Total-Count", String.valueOf(tf.getAllTickets().size()));
  
  }
}