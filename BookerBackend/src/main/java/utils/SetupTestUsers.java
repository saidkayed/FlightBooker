package utils;

import entity.Role;
import entity.User;
import java.io.IOException;
import java.net.MalformedURLException;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class SetupTestUsers {

  public static void main(String[] args) throws MalformedURLException, IOException {

    EntityManager em = Persistence.createEntityManagerFactory("pu").createEntityManager();
    
   // SwapiFacade sf = new SwapiFacade();
    
    //ArrayList<String> json = sf.getAllSwapi();
    
      //System.out.println(json);
            
    
    // IMPORTAAAAAAAAAANT!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    // This breaks one of the MOST fundamental security rules in that it ships with default users and passwords
    // CHANGE the three passwords below, before you uncomment and execute the code below
    
   // throw new UnsupportedOperationException("REMOVE THIS LINE, WHEN YOU HAVE READ WARNING");
    
    em.getTransaction().begin();
    Role userRole = new Role("user");
    Role adminRole = new Role("admin");
   User user = new User("user", "test");
//    user.addRole(userRole);
//    User admin = new User("admin", "test");
//    admin.addRole(adminRole);
//    User myUser = new User("Tobias", "1234");
//    myUser.addRole(userRole);
     User mySaid = new User("Zaid", "1234");
    mySaid.addRole(userRole);
    
    em.persist(userRole);
    em.persist(adminRole);
    //em.persist(user);
    //em.persist(admin);
    //em.persist(myUser);
    em.persist(mySaid);
    em.getTransaction().commit();
    System.out.println("PW: " + user.getUserPass());
    System.out.println("Testing user with OK password: " + user.verifyPassword("test"));
    System.out.println("Testing user with wrong password: " + user.verifyPassword("test1"));
    System.out.println("Created TEST Users");

    
   
  }

}
