/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.Account;
import javax.naming.AuthenticationException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author tobbe
 */
public class AccountFacade {
   

    public AccountFacade() {
    }
        
     
     
     private static final AccountFacade instance = new AccountFacade();
    
        public static AccountFacade getInstance(){
        return instance;
    }
        
        
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
    
    public Account getVeryfiedUser(String username, String password) throws AuthenticationException {
        EntityManager em = emf.createEntityManager();
        Account account;
        try {
            account = em.find(Account.class, username);
            if (account == null || !account.verifyPassword(password)) {
                throw new AuthenticationException("Invalid user name or password");
            }
        } finally {
            em.close();
        }
        return account;
    }
    
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        AccountFacade af = new AccountFacade(emf);
        
        Account acc = new Account("Said", "Kayed");
        af.createAccount(acc);
        
        
        System.out.println("true password");
        System.out.println(acc.verifyPassword("Kayed"));
        
        
        System.out.println("false password");
        System.out.println(acc.verifyPassword("kayeddddd"));
    }
}
