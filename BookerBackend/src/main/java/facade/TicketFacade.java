/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import DTO.FlightDTO;
import entity.FlightTicket;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.ws.rs.core.Response;
import ticket_handler.TicketHandler;

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

    public List<FlightTicket> getMixTickets() {
        EntityManager em = emf.createEntityManager();

        try {
            Query q = em.createQuery("SELECT e FROM FlightTicket e");
            return q.getResultList();
        } finally {
            em.close();
        }

    }


    
    public List<FlightDTO> getFlightDTO() {
        EntityManager em = emf.createEntityManager();

        TypedQuery<FlightDTO> query = em.createQuery("SELECT NEW DTO.FlightDTO"
                + "(c1.id ,c1.airplane,c1.arrTime,c1.depTime,c1.departure,c1.destination,c1.duration,c1.model,c2.username)"
                + "FROM FlightTicket AS c1 INNER JOIN c1.id AS c2", FlightDTO.class);
        
        try {
            
            return query.getResultList();
        } finally {
            em.close();
        }

    }
    
    public List<FlightTicket> getAllTickets() throws MalformedURLException, IOException{
        DatboisTicket dt = new DatboisTicket();
        EntityManager em = emf.createEntityManager();
        List<FlightTicket> listMix = getMixTickets();
        List<FlightTicket> listDatbois = dt.getDatbois();
        
        for (int i = 0; i < listMix.size(); i++) {
            listDatbois.add(listMix.get(i));
        }

        return listDatbois;
    }

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");

        TicketFacade tf = new TicketFacade(emf);

        java.util.Date dt = new java.util.Date();

        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm");

        String currentTime = sdf.format(dt);

        for (int i = 0; i < 1; i++) {
            FlightTicket t1 = new FlightTicket("MixFlight", "CPH", "TUN", "2019-09-11T09-00", "2019-10-09T09-00", 0, 300, 0, "JumboJet", "ABC123", 0);
            FlightTicket t2 = new FlightTicket("MixFlight", "CPH", "LAX", "2019-09-11T09-00", "2019-08-12T09-00", 0, 500, 0, "JumboJet", "ABC123", 0);
            FlightTicket t3 = new FlightTicket("MixFlight", "CPH", "SWE", "2019-09-11T09-00", "2019-07-07T09-00", 0, 200, 0, "JumboJet", "ABC123", 0);
            FlightTicket t4 = new FlightTicket("MixFlight", "CPH", "IST", "2019-09-11T09-00", "2019-09-10T09-00", 0, 400, 0, "JumboJet", "ABC123", 0);
            tf.CreateTicket(t1);
            tf.CreateTicket(t2);
            tf.CreateTicket(t3);
            tf.CreateTicket(t4);

        }
        System.out.println(tf.getMixTickets());
    }

}
