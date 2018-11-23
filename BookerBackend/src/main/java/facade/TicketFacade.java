/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.FlightTicket;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author sidad
 */
public class TicketFacade {

    EntityManagerFactory emf;

    public TicketFacade(EntityManagerFactory emf) {
        this.emf = emf;
    }

    EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public FlightTicket CreateTicket(FlightTicket ft) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(ft);
            em.getTransaction().commit();

        } finally {
            em.close();
        }
        return ft;

    }

    public List<FlightTicket> getAllTickets() {
        EntityManager em = emf.createEntityManager();

        try {
            Query q = em.createQuery    ("SELECT e FROM FlightTicket e");
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    
    
    public List<FlightTicket> Ticket_Pagination(int id, int id2) {
        List<FlightTicket> ticks = new ArrayList<>();
        if(id2 > getAllTickets().size()){
            id2 = getAllTickets().size();
        }
            for (int i = id; i < id2; i++) {
                FlightTicket ticket = getAllTickets().get(i);
                ticks.add(ticket);
            }
        return ticks;
        }
    
    
    
    
    

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");

        TicketFacade tf = new TicketFacade(emf);
//    List<FlightTicket> hej = tf.getAllTickets();
//        Collections.sort(hej);
//        System.out.println(hej);
        
      

       // System.out.println(tf.Ticket_Pagination(1, 5));
        java.util.Date dt = new java.util.Date();

        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm");

        String currentTime = sdf.format(dt);

        for (int i = 0; i < 3; i++) {
        FlightTicket t1 = new FlightTicket("THR", "CPH", "IST", currentTime, currentTime, 0, 300, 0, "JumboJet", "ABC123", 0);    
        FlightTicket t2 = new FlightTicket("HEJ", "CPH", "IST", currentTime, currentTime, 0, 500, 0, "JumboJet", "ABC123", 0);
        FlightTicket t3 = new FlightTicket("TEST", "CPH", "IST", currentTime, currentTime, 0, 200, 0, "JumboJet", "ABC123", 0);
        FlightTicket t4 = new FlightTicket("hvad", "CPH", "IST", currentTime, currentTime, 0, 400, 0, "JumboJet", "ABC123", 0);
        tf.CreateTicket(t1);
        tf.CreateTicket(t2);
        tf.CreateTicket(t3);
        tf.CreateTicket(t4);
        
        }
        System.out.println(tf.getAllTickets());
    }

}
