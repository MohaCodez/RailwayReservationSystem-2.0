import java.util.UUID;

public class Ticket {
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
}
