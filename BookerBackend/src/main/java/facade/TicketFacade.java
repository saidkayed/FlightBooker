/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entity.FlightTicket;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.ws.rs.core.Response;

/**
 *
 * @author sidad
 */
public class TicketFacade implements Callable<String> {

    EntityManagerFactory emf;
    static Gson gson;
    String url;

    public TicketFacade(EntityManagerFactory emf) {
        this.emf = emf;
    }

    EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public TicketFacade(String url){
        this.url = url;
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
    
    @Override
    public String call() throws Exception {
        return getAllTickets(url);
    }
    
    public String getAllTickets(String url) throws MalformedURLException, IOException{
        gson = new GsonBuilder().setPrettyPrinting().create();

        URL myurl = new URL(url);
        HttpURLConnection con = (HttpURLConnection) myurl.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Accept", "application/json;charset=UTF-8");
        con.setRequestProperty("User-Agent", "server");
        Scanner scan = new Scanner(con.getInputStream());
        String jsonStr = "";
        while (scan.hasNext()) {
            jsonStr += scan.nextLine();
        }
        scan.close();
        return jsonStr;

        
    }
/*
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

*/
}
