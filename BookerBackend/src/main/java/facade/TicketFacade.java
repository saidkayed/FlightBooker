/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

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
import ticket_handler.TicketHandler;

/**
 *
 * @author sidad
 */
public class TicketFacade implements Callable<String> {

    EntityManagerFactory emf;
    String url;
    int id;
    String dept;
    String dest;

    public TicketFacade(EntityManagerFactory emf) {
        this.emf = emf;
    }

    EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public TicketFacade(String url, String dept, String dest, int id){
        this.url = url;
        this.dept = dept;
        this.dest = dest;
        this.id = id;
    }
    
    @Override
    public String call() throws Exception {
        return getFlightTickets(url, dest, dept, id);
    }
    
    public String getFlightTickets(String url, String dept, String dest, int id) throws MalformedURLException, IOException{
        URL myUrl = new URL(url + "&dept=" + dept + "&dest=" + dest);
        HttpURLConnection con = (HttpURLConnection) myUrl.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Accept", "application/json;charset=UTF-8");
        con.setRequestProperty("User-Agent", "Server");
        Scanner scan = new Scanner(con.getInputStream());
        String jsonStr = "";
        while(scan.hasNext()){
            jsonStr += scan.nextLine();
        }
        scan.close();
        return jsonStr;
        
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
