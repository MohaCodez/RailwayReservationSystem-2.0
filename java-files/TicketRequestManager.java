import java.io.File;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

public class TicketRequestManager implements Serializable{
    private HashMap<String, TicketRequest> requests;
    private String ticketRequestFilePath = "ticketRequests.ser";
    File ticketRequestFile = new File(ticketRequestFilePath);
    
    private HashMap<String, Ticket> bookedTickets;
    private String ticketFilePath = "tatkalTicketData.ser";
    File ticketFile = new File(ticketFilePath);
    
    public TicketRequestManager() {
        System.out.println("Constructor invoked!");
        if (ticketRequestFile.length() == 0) {
            this.requests = new HashMap<>();
        } else {
            System.out.println("Hashmap Read in Constructor!");
            this.requests = TicketRequestHashMapIO.readHashMapFromFile(ticketRequestFilePath);
        }
        
        if (ticketFile.length() == 0) {
            this.bookedTickets = new HashMap<>();
        } else {
            System.out.println("Hashmap Read in Constructor!");
            this.bookedTickets = TicketHashMapIO.readHashMapFromFile(ticketFilePath);
        }
        
        

    }
    
    public HashMap<String, TicketRequest> getRequests(){
    	return this.requests;
    }
    
    public void addRequestToWaitingList(TicketRequest request) {
    	this.requests.put(request.getRequestId(), request);
        TicketRequestHashMapIO.writeHashMapToFile(ticketRequestFilePath, this.requests);
    }
    
    
    public Ticket bookTicket(TicketRequest request) {
        TicketRequestHashMapIO.readHashMapFromFile(ticketFilePath);
        Ticket ticket = request.getTrain().bookTicket(request.getPassenger(), request.getCoachType(), request.getSeatNumber(), request.getCoachNumber());
        if (ticket != null) {
            bookedTickets.put(ticket.getTicketId(), ticket);
            TicketHashMapIO.writeHashMapToFile(ticketFilePath, bookedTickets);
            ///////////////////////////////////////
            request.setStatusAsBooked();
            ///////////////////////////////////////
            this.requests.put(request.getRequestId(), request);
            TicketRequestHashMapIO.writeHashMapToFile(ticketRequestFilePath, this.requests);

        }
        request.setTicket(ticket);
        this.bookedTickets.put(ticket.getTicketId(), ticket);
        TicketHashMapIO.writeHashMapToFile(ticketFilePath, this.bookedTickets);
        return ticket;
    }
    
}
