package ddg;

import com.google.gson.Gson;
import entity.FlightTicket;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

public class Generator
{

    private final Random rnd = new Random();
    private final List<String> airlines;
    private final List<String> airports;
    private final List<String> registrations;
    private final List<String> types;
    private final LocalDateTime startDate;
    private final int futureDays;
    private final int tickets;
    private final int durMin;
    private final int durMax;
    private final int priceMin;
    private final int priceMax;
    private final int capMin;
    private final int capMax;
    private final int cancelFee;

    public Generator(List<String> airlines, List<String> airports, List<String> registrations, List<String> types, LocalDateTime startDate, int futureDays, int tickets, int durMin, int durMax, int priceMin, int priceMax, int capMin, int capMax, int cancelFee)
    {
        this.airlines = airlines;
        this.airports = airports;
        this.registrations = registrations;
        this.types = types;
        this.startDate = startDate;
        this.futureDays = futureDays;
        this.tickets = tickets;
        this.durMin = durMin;
        this.durMax = durMax;
        this.priceMin = priceMin;
        this.priceMax = priceMax;
        this.capMin = capMin;
        this.capMax = capMax;
        this.cancelFee = cancelFee;
    }

    public void ticketPrint()
    {
        Gson gson = new Gson();
        for (int i = 0; i < tickets; i++)
        {
            String airline = airlines.get(rnd.nextInt(airlines.size()));
            String departure = airports.get(rnd.nextInt(airports.size()));
            String destination = airports.get(rnd.nextInt(airports.size()));
            while (departure.equalsIgnoreCase(destination))
            {
                destination = airports.get(rnd.nextInt(airports.size()));
            }
            LocalDateTime dep = startDate.plusDays(rnd.nextInt(futureDays));
            String depString = Main.FORMATTER2.format(dep);
            System.out.println(depString);
            int depHour = rnd.nextInt(24);
            int depMinute = rnd.nextInt(60);
            depString += "T"+depHour+"-"+depMinute;
            System.out.println(depString);
            dep = dep.plusHours(rnd.nextInt(24));
            dep = dep.plusMinutes(rnd.nextInt(60));
            int duration = rnd.nextInt(durMax - durMin + 1) + durMin;
            LocalDateTime arr = dep.plusMinutes(duration);
            int arrHour = arr.getHour();
            int arrMinute = arr.getMinute();
            String arrString = Main.FORMATTER2.format(arr);
            arrString += "T"+arrHour+"-"+arrMinute;
            System.out.println(arrString);
            int price = rnd.nextInt(priceMax - priceMin + 1) + priceMin;
            int cancel = rnd.nextInt(cancelFee);
            String airplane = registrations.get(rnd.nextInt(registrations.size()));
            String model = types.get(rnd.nextInt(types.size()));
            int capacity = rnd.nextInt(capMax - capMin + 1) + capMin;
            

            FlightTicket ticket = new FlightTicket(airline, departure, destination, depString, arrString,
                    duration, price, cancel, airplane, model, capacity);
            System.out.println(gson.toJson(ticket));
        }
    }
}
