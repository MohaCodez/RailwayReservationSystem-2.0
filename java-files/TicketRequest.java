import java.io.Serializable;
import java.util.UUID;

public class TicketRequest implements Serializable{
	private Passenger passenger;
	private Train train;
	private int coachType;
	private int seatNumber;
	private int coachNumber;
	private String requestId;
	private String status;
	private Ticket ticket = null;
	
	public TicketRequest(Passenger passenger, Train train, int coachType, int seatNumber, int coachNumber){
		this.passenger = passenger;
		this.train = train;
		this.coachType = coachType;
		this.coachNumber = coachNumber;
		this.seatNumber = seatNumber;
		this.status = "In Waiting List";
		this.requestId = UUID.randomUUID().toString();
	}
	
	public void displayRequestDetails() {
	    System.out.println("\nRequest ID: " + this.requestId);
	    System.out.println("Train ID: " + this.train.getTrainId());
	    this.train.getRoute().getDescription();
	    System.out.println("Passenger Name: " + this.passenger.getName());
	    System.out.println("Passenger Age: " + this.passenger.getAge());
	    System.out.println("Coach Type: " + this.coachType);
	    System.out.println("Seat Number: " + this.seatNumber);
	    System.out.println("Coach Number: " + this.coachNumber);
	    System.out.println("Request Status: " + this.status);
	    
	    if(this.ticket!=null) {
	    	System.out.println("\nYour Ticket Details are as follows:");
	    	System.out.println(this.ticket.getDetails());
	    }
	}
	
	public void setStatusAsBooked() {
		this.status = "Booked Successfully";
	}
	
	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}
	
	public void displayTicket() {
		this.ticket.getDetails();	
	}
	
	public Passenger getPassenger() {
		return this.passenger;
	}
	public Train getTrain() {
		return this.train;
	}
	public int getCoachType() {
		return this.coachType;
	}
	public int getCoachNumber() {
		return this.coachNumber;
	}
	public int getSeatNumber() {
		return this.seatNumber;
	}
	public String getRequestId() {
		return this.requestId;
	}
	public String getStatus() {
		return this.status;
	}
	public Ticket getTicket() {
		return this.ticket;
	}
}
