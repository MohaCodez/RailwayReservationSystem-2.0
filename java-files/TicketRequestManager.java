import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;

public class TicketRequestManager implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private HashMap<String, TicketRequest> requests;
    private String ticketRequestFilePath = "ticketRequests.ser";
    private File ticketRequestFile;

    private HashMap<String, Ticket> bookedTickets;
    private String ticketFilePath = "tatkalTicketData.ser";
    private File ticketFile;

    public TicketRequestManager() {
        System.out.println("Constructor invoked!");
        this.requests = new HashMap<>();
        this.bookedTickets = new HashMap<>();
        this.ticketRequestFile = new File(ticketRequestFilePath);
        this.ticketFile = new File(ticketFilePath);
        
        initializeRequests();
        initializeBookedTickets();
    }

    private void initializeRequests() {
        if (!ticketRequestFile.exists()) {
            try {
                ticketRequestFile.createNewFile();
            } catch (IOException e) {
                System.err.println("Error creating file: " + ticketRequestFile.getAbsolutePath());
                e.printStackTrace();
            }
        } else {
            System.out.println("Hashmap Read in Constructor!");
            this.requests = TicketRequestHashMapIO.readHashMapFromFile(ticketRequestFilePath);
        }
    }

    private void initializeBookedTickets() {
        if (!ticketFile.exists()) {
            try {
                ticketFile.createNewFile();
            } catch (IOException e) {
                System.err.println("Error creating file: " + ticketFile.getAbsolutePath());
                e.printStackTrace();
            }
        } else {
            System.out.println("Hashmap Read in Constructor!");
            this.bookedTickets = TicketHashMapIO.readHashMapFromFile(ticketFilePath);
        }
    }


    public HashMap<String, TicketRequest> getRequests() {
        return new HashMap<>(requests); // Return a copy to maintain encapsulation
    }

    public void addRequestToWaitingList(TicketRequest request) {
        requests.put(request.getRequestId(), request);
        TicketRequestHashMapIO.writeHashMapToFile(ticketRequestFilePath, requests);
    }

    public Ticket bookTicket(TicketRequest request) {
        requests = TicketRequestHashMapIO.readHashMapFromFile(ticketRequestFilePath);
        Ticket ticket = request.getTrain().bookTicket(request.getPassenger(), request.getCoachType(), request.getSeatNumber(), request.getCoachNumber());
        if (ticket != null) {
            request.setStatusAsBooked();
            requests.put(request.getRequestId(), request);
            TicketRequestHashMapIO.writeHashMapToFile(ticketRequestFilePath, requests);
            bookedTickets.put(ticket.getTicketId(), ticket);
            TicketHashMapIO.writeHashMapToFile(ticketFilePath, bookedTickets);
        }
        request.setTicket(ticket);
        return ticket;
    }
    
}
