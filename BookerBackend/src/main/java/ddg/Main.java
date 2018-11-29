package ddg;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class Main {
    static DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm");
    static DateTimeFormatter FORMATTER2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    static DateTimeFormatter FORMATTER3 = DateTimeFormatter.ofPattern("HH-mm");

    public static void main(String[] args) {
        Main main = new Main();
        int tickets = 1;
        if (args.length > 0) {
            tickets = Integer.parseInt(args[0]);
        }
        main.execute(tickets);
    }

    private void execute(int numberOfTickets) {
        Properties props = loadProps();
        if (numberOfTickets == 0) {
            numberOfTickets = Integer.parseInt(props.getProperty("tickets.number"));
        }
        LocalDateTime startDate = LocalDateTime.now();
        if (props.containsKey("date.start")) {
            startDate = LocalDateTime.parse(props.getProperty("date.start"), FORMATTER);
        }
        List<String> airlines = Arrays.asList(props.getProperty("airlines").split("\\s*,\\s*"));
        List<String> airports = Arrays.asList(props.getProperty("airports").split("\\s*,\\s*"));
        List<String> regs = Arrays.asList(props.getProperty("plane.reg").split("\\s*,\\s*"));
        List<String> types = Arrays.asList(props.getProperty("plane.type").split("\\s*,\\s*"));
        int futureDays = Integer.parseInt(props.getProperty("days.in.future"));
        int durMin = Integer.parseInt(props.getProperty("duration.min"));
        int durMax = Integer.parseInt(props.getProperty("duration.max"));
        int priceMin = Integer.parseInt(props.getProperty("price.min"));
        int priceMax = Integer.parseInt(props.getProperty("price.max"));
        int capMin = Integer.parseInt(props.getProperty("capacity.min"));
        int capMax = Integer.parseInt(props.getProperty("capacity.max"));
        int cancelFee = Integer.parseInt(props.getProperty("cancelation.max"));

        Generator generator = new Generator(airlines, airports, regs, types, startDate, futureDays, numberOfTickets, durMin, durMax, priceMin, priceMax, capMin, capMax, cancelFee);
        generator.ticketPrint();
    }

    private Properties loadProps() {
        Properties props = null;
        InputStream input = null;

        try {
            input = new FileInputStream("gen.properties");
            props = new Properties();
            props.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return props;
    }
}
