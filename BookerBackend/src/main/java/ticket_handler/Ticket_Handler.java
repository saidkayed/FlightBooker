/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ticket_handler;

import entity.FlightTicket;
import facade.TicketFacade;
import java.io.IOException;
import java.util.List;
import javax.persistence.Persistence;

/**
 *
 * @author sidad
 */
public class Ticket_Handler {
    
    
    
    public List<FlightTicket> Ticket_Handler(String dept,String dest,String airline) throws IOException{
        TicketFacade tf = new TicketFacade(Persistence.createEntityManagerFactory("pu"));
        
        
       List<FlightTicket> active_ticket = tf.getAllTickets();
       Lis
       
       if(active_ticket.contains(dept) && active_ticket.contains(dest) && active_ticket.contains(airline)){
           
       }
       
        
        
        
    }
    public static void main(String[] args) {
        
    }
    
}
