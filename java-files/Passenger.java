
public class Passenger {
    private Person person;

    public Passenger(Person person) {
        this.person = person;
    }

    // Constructor overloading for different types of passengers
    public Passenger(String name, int age, String phoneNumber, String emailId) {
        // Default to General category if no specific type is specified
        this(new General(name, age, phoneNumber, emailId));
    }

    public Passenger(String name, int age, String phoneNumber, String emailId, String category) {
        switch (category.toLowerCase()) {
            case "student":
                this.person = new Student(name, age, phoneNumber, emailId);
                break;
            case "military":
                this.person = new Military(name, age, phoneNumber, emailId);
                break;
            case "seniorcitizen":
                this.person = new SeniorCitizen(name, age, phoneNumber, emailId);
                break;
            case "disabled":
                this.person = new Disabled(name, age, phoneNumber, emailId);
                break;
            default:
                // Default to General category if invalid category is provided
                this.person = new General(name, age, phoneNumber, emailId);
                break;
        }
    }

    // Method to book tickets for the passenger
    public Ticket bookTicket(Train train, int coachType, int seatNumber) {
        return train.bookTicket();
    }
    
    public boolean cancelTicket(Train train) {
        if (bookedTicket != null) {
            boolean isCancelled = train.cancelTicketById(bookedTicket.getTicketId());
            if (isCancelled) {
                bookedTicket = null; // Reset bookedTicket after cancellation
            }
            return isCancelled;
        } else {
            System.out.println("No ticket booked for this passenger.");
            return false;
        }
    }
    
    // Getter for the Person object
    public Person getPerson() {
        return person;
    }
}
