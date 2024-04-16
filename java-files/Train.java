import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Train implements Serializable {
    private String trainId;
    private Route route;
    private AC1Coach[] ac1Coach;
    private AC2Coach[] ac2Coaches;
    private AC3Coach[] ac3Coaches;
    private SleeperCoach[] sleeperCoaches;
    private Map<String, Ticket> bookedTickets;

    public Train(String trainId, Route route) {
        this.trainId = trainId;
        this.route = route;
        initializeCoaches();
        this.bookedTickets = new HashMap<>();
    }

    private void initializeCoaches() {
        // Initialize AC1Coach
        this.ac1Coach = new AC1Coach[1];
        this.ac1Coach[0] = new AC1Coach();

        // Initialize AC2Coaches
        this.ac2Coaches = new AC2Coach[2];
        for (int i = 0; i < ac2Coaches.length; i++) {
            ac2Coaches[i] = new AC2Coach();
        }

        // Initialize AC3Coaches
        this.ac3Coaches = new AC3Coach[3];
        for (int i = 0; i < ac3Coaches.length; i++) {
            ac3Coaches[i] = new AC3Coach();
        }

        // Initialize SleeperCoaches
        this.sleeperCoaches = new SleeperCoach[3];
        for (int i = 0; i < sleeperCoaches.length; i++) {
            sleeperCoaches[i] = new SleeperCoach();
        }
    }

    public void displayAvailableSeats() {
        System.out.println("Available Seats for Train " + trainId + " - Route: " + this.route.getDescription());
        for (int i = 0; i < ac1Coach.length; i++) {
            ac1Coach[i].displayAvailableSeats();
        }
        for (AC2Coach ac2Coach : ac2Coaches) {
            ac2Coach.displayAvailableSeats();
        }
        for (AC3Coach ac3Coach : ac3Coaches) {
            ac3Coach.displayAvailableSeats();
        }
        for (SleeperCoach sleeperCoach : sleeperCoaches) {
            sleeperCoach.displayAvailableSeats();
        }
    }

    public Ticket bookTicket(Person person, int coachType, int seatNumber, int coachNumber) {
        Ticket ticket = new Ticket(this.trainId, coachType, seatNumber, coachNumber, person);
        bookedTickets.put(ticket.getTicketId(), ticket);
        return ticket;
    }

    public boolean cancelTicketById(String ticketId) {
        if (bookedTickets.containsKey(ticketId)) {
            Ticket ticket = bookedTickets.get(ticketId);
            int coachType = ticket.getCoachType();
            int seatNumber = ticket.getSeatNumber();

            switch (coachType) {
                case 1:
                    ac1Coach[0].cancelSeat(seatNumber);
                    break;
                case 2:
                    ac2Coaches[ticket.getCoachNumber()].cancelSeat(seatNumber);
                    break;
                case 3:
                    ac3Coaches[ticket.getCoachNumber()].cancelSeat(seatNumber);
                    break;
                case 4:
                    sleeperCoaches[ticket.getCoachNumber()].cancelSeat(seatNumber);
                    break;
                default:
                    System.out.println("Invalid coach type.");
                    return false;
            }

            bookedTickets.remove(ticketId);
            return true;
        }
        System.out.println("Ticket with ID " + ticketId + " not found.");
        return false;
    }

    public void displayBookedTickets() {
        System.out.println("Booked Tickets for Train " + this.trainId + ":");
        for (Ticket ticket : bookedTickets.values()) {
            System.out.println("Ticket ID: " + ticket.getTicketId() + ", Coach Type: " + ticket.getCoachType()
                    + ", Seat Number: " + ticket.getSeatNumber());
        }
    }

    // Getters and Setters
    public String getTrainId() {
        return this.trainId;
    }

    public void setTrainId(String trainId) {
        this.trainId = trainId;
    }

    public Route getRoute() {
        return this.route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }
    
    
    
    
    public BaseCoach getCoach(int coachType, int coachNumber) {
    	switch(coachType) {
    	case 1: return this.ac1Coach[coachNumber];
    	case 2: return this.ac2Coaches[coachNumber];
    	case 3: return this.ac3Coaches[coachNumber];
    	case 4: return this.sleeperCoaches[coachNumber];
    	default: return null;
    	}
    }
    
    public void setCoach(BaseCoach coach, int coachNumber) {
    	int coachType = 0;
    	if (coach instanceof AC1Coach) {
    		coachType = 1;
    	}
    	else if(coach instanceof AC2Coach){
    		coachType = 2;
    	}
    	else if(coach instanceof AC3Coach){
    		coachType = 3;
    	}
    	else if(coach instanceof SleeperCoach){
    		coachType = 4;
    	}
    	
    		
    	
    	
    	
    	
    	switch(coachType) {
    	case 1: this.ac1Coach[coachNumber] = (AC1Coach)coach;
    		break;
    	case 2: this.ac2Coaches[coachNumber] = (AC2Coach)coach;
    		break;
    	case 3: this.ac3Coaches[coachNumber] = (AC3Coach)coach;
    		break;
    	case 4: this.sleeperCoaches[coachNumber] = (SleeperCoach)coach;
    		break;
    	default: break;
    	}
    }
    
    public void updateCoachState(int coachType, int coachNumber, int seatNumber, boolean book) {
        BaseCoach coach = getCoach(coachType, coachNumber);
        if (book) {
            coach.bookSeat(seatNumber);
        } else {
            coach.cancelSeat(seatNumber);
            this.setCoach(coach, coachNumber);
        }
    }
    
}
