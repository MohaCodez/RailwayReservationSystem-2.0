import java.util.HashMap;
import java.util.Map;

public class TicketManager {
    private Map<String, Ticket> bookedTickets;

    public TicketManager() {
        this.bookedTickets = new HashMap<>();
    }

    // Method to book a ticket for a passenger on a train
    public Ticket bookTicket(Person person, Train train, int coachType, int seatNumber, int coachNumber) {
        Ticket ticket = train.bookTicket(person, coachType, seatNumber, coachNumber);
        if (ticket != null) {
            bookedTickets.put(ticket.getTicketId(), ticket);
        }
        return ticket;
    }

    // Method to cancel a ticket by ID
    public boolean cancelTicketById(String ticketId) {
        if (bookedTickets.containsKey(ticketId)) {
            bookedTickets.remove(ticketId);
            return true;
        }
        return false;
    }

    // Method to display booked tickets
    public void displayBookedTickets() {
        System.out.println("Booked Tickets:");
        for (Ticket ticket : bookedTickets.values()) {
            System.out.println(ticket);
        }
    }
    
    public Map<String, Ticket> getBookedTickets(){
    	return this.bookedTickets;
    }
}
