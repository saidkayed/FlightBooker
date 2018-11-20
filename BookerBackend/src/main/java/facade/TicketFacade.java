/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.FlightTicket;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

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
    
   public FlightTicket CreateTicket(FlightTicket ft){
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
    
}