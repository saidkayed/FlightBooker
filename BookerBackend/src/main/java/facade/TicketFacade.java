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
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

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

    public int X_total_count_header() throws MalformedURLException, IOException {
        DatboisTicket sf = new DatboisTicket();
        List<FlightTicket> pricesort = sf.getDatbois();
        List<FlightTicket> mixTicket = getMixTickets();

        for (int i = 0; i < getMixTickets().size(); i++) {
            pricesort.add(mixTicket.get(i));
        }

        return pricesort.size();
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

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");

        TicketFacade tf = new TicketFacade(emf);

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
        System.out.println(tf.getMixTickets());
    }

}
