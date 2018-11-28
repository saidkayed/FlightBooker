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
public class Ticket_Handler {

    public static List<FlightTicket> ticketHandler(String airline, String dept, String dest, String deptDate, String arrDate) throws IOException {
        TicketFacade tf = new TicketFacade(Persistence.createEntityManagerFactory("pu"));

        
        //Hvad vi skal have CPHFlights, CPH, TUN, 2019-10-10T15-00, 2019-10-08T18-00
        
<<<<<<< HEAD
       List<FlightTicket> active_ticket = tf.getAllTickets();
       Lis
       
       if(active_ticket.contains(dept) && active_ticket.contains(dest) && active_ticket.contains(airline)){
           
       }
       
=======
>>>>>>> 1cb8a29a994e1f137a6f6b4c62747b11bb9d29d4
        
        //Hvad vi har CPHFlights, CPH, TUN, 2019-10-10, 2019-10-08
        
        
        
        List<FlightTicket> allTickets = tf.getAllTickets();
        List<FlightTicket> myTicks = new ArrayList();

        for (int i = 0; i < allTickets.size(); i++) {
            if (allTickets.get(i).getAirline().equalsIgnoreCase(airline)
                    && allTickets.get(i).getDeparture().equalsIgnoreCase(dept)
                    && allTickets.get(i).getDestination().equalsIgnoreCase(dest)
                    && allTickets.get(i).getDepTime().equalsIgnoreCase(deptDate)
                    && allTickets.get(i).getArrTime().equalsIgnoreCase(arrDate)) {
             
                String newDeptDate = deptDate.substring(0, 10);
                String newArrDate = arrDate.substring(0, 10);
                
                
                
                allTickets.get(i).setDepTime(newDeptDate);
                allTickets.get(i).setArrTime(newArrDate);
                
                myTicks.add(allTickets.get(i));
            }
        }

        return myTicks;
    }

    public static void main(String[] args) throws IOException {
        
        TicketFacade tf = new TicketFacade(Persistence.createEntityManagerFactory("pu"));

        List<FlightTicket> tickets = ticketHandler("DATFlights", "CPH", "TUN", "2019-10-10T15-00", "2019-10-08T18-00");

        for (int i = 0; i < tickets.size(); i++) {
            System.out.println(tickets.get(i).toString());

        }

        
        
                
    }

}
