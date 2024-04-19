import java.io.Serializable;
import java.util.UUID;

public class Ticket implements Serializable {
    private static final long serialVersionUID = -4553063748262744606L;

    private String ticketId;
    private String trainID;
    private int coachType;
    private int seatNumber;
    private int coachNumber; // Added field
    private Passenger passenger;
    private Route route;
    private double coachFare;

    public Ticket(String trainID, int coachType, int seatNumber, int coachNumber, Passenger passenger, Route route,
            double coachFare) {
        this.trainID = trainID;
        this.ticketId = generateTicketId();
        this.coachType = coachType;
        this.seatNumber = seatNumber;
        this.coachNumber = coachNumber; // Set coachNumber
        this.passenger = passenger;
        this.route = route;
        this.coachFare = coachFare;
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

    public String getCoachType() {
        switch (this.coachType) {
            case 1:
                return "AC - 1st Class Coach";
            case 2:
                return "AC - 2nd Class Coach";
            case 3:
                return "AC - 3rd Class Coach";
            case 4:
                return "Sleeper Coach";
            default:
                return "Invalid Coach";
        }
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

    public String getCoachNameFromType(int num) {
        switch (num) {
            case 1:
                return "AC - 1st Class Coach";
            case 2:
                return "AC - 2nd Class Coach";
            case 3:
                return "AC - 3rd Class Coach";
            case 4:
                return "Sleeper Coach";
            default:
                return "Invalid Coach";
        }
    }

    public String getDetails() {
        // Construct and return the ticket details as a string
        StringBuilder sb = new StringBuilder();
        sb.append("Ticket ID: ").append(ticketId).append("\n");
        sb.append("Train ID: ").append(trainID).append("\n");
        sb.append("Coach Type: ").append(getCoachNameFromType(coachType)).append("\n");
        sb.append("Coach Number: ").append(coachNumber).append("\n");
        sb.append("Seat Number: ").append(seatNumber).append("\n");
        sb.append("Passenger Name: ").append(passenger.getName()).append("\n");
        sb.append("Passenger Age: ").append(passenger.getAge()).append("\n");
        sb.append("Passenger Phone Number: ").append(passenger.getPhoneNumber()).append("\n");
        sb.append("Passenger Email: ").append(passenger.getEmailId()).append("\n");
        sb.append("\n##### Pricing Information #####").append("\n");

        sb.append("(+)       Route Fare: ").append(route.getPrice()).append("\n");
        sb.append("(+)       Coach Fare: ").append(coachFare).append("\n");
        double totalBaseFare = route.getPrice() + coachFare;
        sb.append("     Total Base Fare: ").append(totalBaseFare).append("\n\n");
        double discount = passenger.calculateDiscount(totalBaseFare);
        sb.append("(-)         Discount: ").append(discount).append("\n");
        sb.append("-------------------------------").append("\n");
        sb.append("Total Amount Payable: ").append(totalBaseFare - discount).append("\n");
        sb.append("-------------------------------").append("\n");

        return sb.toString();
    }

    public int getCoachIntType() {

        return this.coachType;
        // TODO Auto-generated method stub
    }

}
