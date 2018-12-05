/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ticket_handler;

import entity.FlightTicket;
import facade.TicketFacade;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Persistence;

/**
 *
 * @author sidad
 */
public class TicketHandler {

    public List<FlightTicket> ticketHandler(String dept, String dest) throws IOException {
        TicketFacade tf = new TicketFacade(Persistence.createEntityManagerFactory("pu"));

        List<FlightTicket> allTickets = tf.getAllTickets();
        List<FlightTicket> myTicks = new ArrayList();

        for (int i = 0; i < allTickets.size(); i++) {
            if (allTickets.get(i).getDeparture().equalsIgnoreCase(dept)
                    && allTickets.get(i).getDestination().equalsIgnoreCase(dest)) {

                myTicks.add(allTickets.get(i));
            }
        }

        return myTicks;
    }
    
    
    


    

}
