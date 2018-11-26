/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.Account;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author tobbe
 */
public class AccountFacade {
    
    EntityManagerFactory emf;

    public AccountFacade(EntityManagerFactory emf) {
        this.emf = emf;
    }

    EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    
    public Account createAccount(Account acc){
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(acc);
            em.getTransaction().commit();

        } finally {
            em.close();
        }
        return acc;

    }
    
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        AccountFacade af = new AccountFacade(emf);
        Account acc = new Account("Said", "Kayed");
        af.createAccount(acc);
    }
}
