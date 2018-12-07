package entity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import exceptions.AuthenticationException;
import facade.TicketFacade;
import javax.persistence.Query;

/**
 *
 * @author lam@cphbusiness.dk
 */
public class UserFacade {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
    private static final UserFacade instance = new UserFacade();

    public UserFacade() {
    }

    public static UserFacade getInstance() {
        return instance;
    }

    public User GetUser(String username) {
        EntityManager em = emf.createEntityManager();
        try {
            Query q = em.createQuery("SELECT e FROM User e WHERE e.userName = :id");
            q.setParameter("id", username);
            return (User) q.getSingleResult();
        } finally {
            em.clear();
        }
    }

    public User CreateUser(User user) {
        EntityManager em = emf.createEntityManager();
        Role userRole = new Role("user");
        user.addRole(userRole);

        try {
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();

        } finally {
            em.close();
        }
        return user;
    }

    public User getVeryfiedUser(String username, String password) throws AuthenticationException {
        EntityManager em = emf.createEntityManager();
        User user = null;
        try {
            Query q = em.createQuery("SELECT p FROM User p WHERE p.userName = :name");
            q.setParameter("name", username);
            user = (User) q.getSingleResult();
            // user = em.find(User.class, username);

            if (username == null || !user.verifyPassword(password)) {
                throw new AuthenticationException("Invalid user name or password");

            }
        } finally {
            em.close();
        }
        return user;
    }

    public Data Save_Ticket_With_User(Data data, User user) {
        EntityManager em = emf.createEntityManager();

        Data ticket_user = new Data(user.getUserName(), data.getDeparture(), data.getDestination(), data.getDepTime());
        ticket_user.setUser(user);

        try {
            em.getTransaction().begin();
            em.merge(ticket_user);
            em.getTransaction().commit();

        } finally {
            em.close();
        }
        return data;
    }

    public static void main(String[] args) {
        UserFacade uf = new UserFacade();
        //User user = uf.GetUser("TOB");

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        TicketFacade tf = new TicketFacade(emf);

        User user = uf.GetUser("Zaid");
        Data data = new Data(user.getUserName(), "CPH", "TUN", "123");
        System.out.println(uf.Save_Ticket_With_User(data, user));

    }

}
