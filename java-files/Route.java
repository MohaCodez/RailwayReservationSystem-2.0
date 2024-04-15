import java.time.LocalDate;
import java.time.LocalTime;

public class Route {
    private int id;
    private String start;
    private String destination;
    private LocalTime departureTime;
    private LocalTime arrivalTime;
    private LocalDate date;
    private double price;

    public Route(int id, String start, String destination, LocalTime departureTime, LocalTime arrivalTime, LocalDate date, double price) {
        this.id = id;
        this.start = start;
        this.destination = destination;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.date = date;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
    public String getDescription() {
        return "Route ID: " + id + "\n" +
               "Start: " + start + "\n" +
               "Destination: " + destination + "\n" +
               "Departure Time: " + departureTime + "\n" +
               "Arrival Time: " + arrivalTime + "\n" +
               "Date: " + date + "\n" +
               "Price: " + price;
    }   
}
