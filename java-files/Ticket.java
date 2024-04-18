import java.io.Serializable;
import java.util.UUID;

public class Ticket implements Serializable{
	private static final long serialVersionUID = 1L;
	private String ticketId;
    private String trainID;
    private int coachType;
    private int seatNumber;
    private int coachNumber; // Added field
    private Passenger passenger;

    public Ticket(String trainID, int coachType, int seatNumber, int coachNumber, Passenger passenger) {
        this.trainID = trainID;
        this.ticketId = generateTicketId();
        this.coachType = coachType;
        this.seatNumber = seatNumber;
        this.coachNumber = coachNumber; // Set coachNumber
        this.passenger = passenger;
    }

    private String generateTicketId() {
        // Generate a unique ticket ID using UUID
        return UUID.randomUUID().toString();
    }

    public String getTicketId() {
        return ticketId;
    }

    public String getTrainID() {
        return trainID;
    }

    public int getCoachType() {
        return coachType;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public int getCoachNumber() {
        return coachNumber;
    }
    
    public Passenger getPassenger() {
    	return this.passenger;
    }
    
    public String getDetails() {
        // Construct and return the ticket details as a string
        StringBuilder sb = new StringBuilder();
        sb.append("Ticket ID: ").append(ticketId).append("\n");
        sb.append("Train ID: ").append(trainID).append("\n");
        sb.append("Coach Type: ").append(coachType).append("\n");
        sb.append("Seat Number: ").append(seatNumber).append("\n");
        sb.append("Coach Number: ").append(coachNumber).append("\n");
        sb.append("Passenger Name: ").append(passenger.getName()).append("\n");
        sb.append("Passenger Age: ").append(passenger.getAge()).append("\n");
        sb.append("Passenger Phone Number: ").append(passenger.getPhoneNumber()).append("\n");
        sb.append("Passenger Email: ").append(passenger.getEmailId()).append("\n");
        return sb.toString();
    }
    
}
