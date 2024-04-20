import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TicketManager implements Serializable {
    private HashMap<String, Ticket> bookedTickets;
    private String ticketFilePath = "ticketData.ser";
    File ticketFile = new File(ticketFilePath);

    public TicketManager() {
        // System.out.println("Constructor invoked!");
        if (ticketFile.length() == 0) {
            this.bookedTickets = new HashMap<>();
        } else {
            // System.out.println("Hashmap Read in Constructor!");
            this.bookedTickets = TicketHashMapIO.readHashMapFromFile(ticketFilePath);
        }

    }

    // Method to book a ticket for a passenger on a train
    public Ticket bookTicket(Passenger passenger, Train train, int coachType, int coachNumber, int seatNumber) {
        TicketHashMapIO.readHashMapFromFile(ticketFilePath);
        Ticket ticket = train.bookTicket(passenger, coachType, seatNumber, coachNumber);
        if (ticket != null) {
            bookedTickets.put(ticket.getTicketId(), ticket);
            TicketHashMapIO.writeHashMapToFile(ticketFilePath, bookedTickets);
        }
        return ticket;
    }

    public List<Ticket> bookTicket(Passenger passenger, Train train, int coachType, int coachNumber,
            String... seatNumbers) {
        TicketHashMapIO.readHashMapFromFile(ticketFilePath);
        List<Ticket> tickets = new ArrayList<Ticket>();

        for (String s : seatNumbers) {
            int seatNumber = Integer.parseInt(s);
            Ticket ticket = train.bookTicket(passenger, coachType, seatNumber, coachNumber);
            if (ticket != null) {
                bookedTickets.put(ticket.getTicketId(), ticket);
                TicketHashMapIO.writeHashMapToFile(ticketFilePath, bookedTickets);
                tickets.add(ticket);
            }
        }
        return tickets;

    }

    // Method to cancel a ticket by ID
    public boolean cancelTicketById(String ticketId) {
        TicketHashMapIO.readHashMapFromFile(ticketFilePath);
        if (bookedTickets.containsKey(ticketId)) {
            bookedTickets.remove(ticketId);
            TicketHashMapIO.writeHashMapToFile(ticketFilePath, bookedTickets);
            return true;
        }
        return false;
    }

    // Method to display booked tickets
    public void displayBookedTickets() {
        // TicketHashMapIO.readHashMapFromFile(ticketFilePath);
        System.out.println("Booked Tickets:");
        for (Ticket ticket : bookedTickets.values()) {
            System.out.println(ticket);
        }
    }

    public Map<String, Ticket> getBookedTickets() {
        return TicketHashMapIO.readHashMapFromFile(ticketFilePath);
        // return this.bookedTickets;
    }
}
