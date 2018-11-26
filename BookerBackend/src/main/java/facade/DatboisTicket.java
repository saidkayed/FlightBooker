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
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import static javax.ws.rs.client.Entity.json;

/**
 *
 * @author sidad
 */
public class DatboisTicket {

    Gson gson;

    public List<FlightTicket> getDatbois() throws ProtocolException, MalformedURLException, IOException {
        gson = new GsonBuilder().setPrettyPrinting().create();

        URL url = new URL("https://emilvh.dk/DATFlights/api/flights");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Accept", "application/json;charset=UTF-8");
        con.setRequestProperty("User-Agent", "server");
        Scanner scan = new Scanner(con.getInputStream());
        String jsonStr = "";
        while (scan.hasNext()) {
            jsonStr += scan.nextLine();

        }
        List<FlightTicket> datbois = new ArrayList<>();
        FlightTicket[] jsonArr = gson.fromJson(jsonStr, FlightTicket[].class);
        for (int i = 0; i < jsonArr.length; i++) {
            datbois.add(jsonArr[i]);
        }

        scan.close();
        return datbois;

    }

    public static void main(String[] args) throws MalformedURLException, IOException {
        DatboisTicket db = new DatboisTicket();
        System.out.println(db.getDatbois());

    }
}
