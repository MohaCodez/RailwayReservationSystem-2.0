import java.io.Serializable;
import java.util.UUID;

public class Ticket implements Serializable{
    private String ticketId;
    private String trainID;
    private int coachType;
    private int seatNumber;
    private int coachNumber; // Added field
    private Person person;

    public Ticket(String trainID, int coachType, int seatNumber, int coachNumber, Person person) {
        this.trainID = trainID;
        this.ticketId = generateTicketId();
        this.coachType = coachType;
        this.seatNumber = seatNumber;
        this.coachNumber = coachNumber; // Set coachNumber
        this.person = person;
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
    
    public Person getPerson() {
    	return this.person;
    }
    
    public String getDetails() {
        // Construct and return the ticket details as a string
        StringBuilder sb = new StringBuilder();
        sb.append("Ticket ID: ").append(ticketId).append("\n");
        sb.append("Train ID: ").append(trainID).append("\n");
        sb.append("Coach Type: ").append(coachType).append("\n");
        sb.append("Seat Number: ").append(seatNumber).append("\n");
        sb.append("Coach Number: ").append(coachNumber).append("\n");
        sb.append("Passenger Name: ").append(person.getName()).append("\n");
        sb.append("Passenger Age: ").append(person.getAge()).append("\n");
        sb.append("Passenger Phone Number: ").append(person.getPhoneNumber()).append("\n");
        sb.append("Passenger Email: ").append(person.getEmailId()).append("\n");
        return sb.toString();
    }
    
}
