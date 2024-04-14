import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Train {
    private String trainId;
    private Route route;
    private AC1Coach[] ac1Coach;
    private AC2Coach[] ac2Coaches;
    private AC3Coach[] ac3Coaches;
    private SleeperCoach[] sleeperCoaches;
    private List<Ticket> tickets;
    private Scanner scanner;

    public Train(String trainId, Route route) {
        this.trainId = trainId;
        this.route = route;
        // Initialize AC1Coach as a single-element array
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
        this.tickets = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    // Method to display available seats in all coaches
    public void displayAvailableSeats() {
        System.out.println("Available Seats for Train " + trainId + " - Route: " + route.getDescription());
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

    // Method to book a ticket
    public Ticket bookTicket() {
        System.out.println("Enter the coach type (1: AC1, 2: AC2, 3: AC3, 4: Sleeper):");
        int coachType = scanner.nextInt();
        int coachNumber = -1;
        
        // Ask for coach number if applicable
        if (coachType == 2 || coachType == 3 || coachType == 4) {
            System.out.println("Enter the coach number:");
            coachNumber = scanner.nextInt();
        }

        System.out.println("Enter the seat number:");
        int seatNumber = scanner.nextInt();

        switch (coachType) {
            case 1:
                if (ac1Coach[0].bookSeat(seatNumber)) {
                    Ticket ticket = new Ticket(trainId, coachType, seatNumber, 0); // Coach number 0 for AC1Coach
                    tickets.add(ticket);
                    return ticket;
                }
                break;
            case 2:
                if (bookSeatInAC2Coaches(coachNumber, seatNumber)) {
                    Ticket ticket = new Ticket(trainId, coachType, seatNumber, coachNumber);
                    tickets.add(ticket);
                    return ticket;
                }
                break;
            case 3:
                if (bookSeatInAC3Coaches(coachNumber, seatNumber)) {
                    Ticket ticket = new Ticket(trainId, coachType, seatNumber, coachNumber);
                    tickets.add(ticket);
                    return ticket;
                }
                break;
            case 4:
                if (bookSeatInSleeperCoaches(coachNumber, seatNumber)) {
                    Ticket ticket = new Ticket(trainId, coachType, seatNumber, coachNumber);
                    tickets.add(ticket);
                    return ticket;
                }
                break;
            default:
                System.out.println("Invalid coach type.");
                break;
        }
        return null;
    }

    private boolean bookSeatInAC2Coaches(int coachNumber, int seatNumber) {
        if (coachNumber >= 0 && coachNumber < ac2Coaches.length) {
            return ac2Coaches[coachNumber].bookSeat(seatNumber);
        }
        return false;
    }

    private boolean bookSeatInAC3Coaches(int coachNumber, int seatNumber) {
        if (coachNumber >= 0 && coachNumber < ac3Coaches.length) {
            return ac3Coaches[coachNumber].bookSeat(seatNumber);
        }
        return false;
    }

    private boolean bookSeatInSleeperCoaches(int coachNumber, int seatNumber) {
        if (coachNumber >= 0 && coachNumber < sleeperCoaches.length) {
            return sleeperCoaches[coachNumber].bookSeat(seatNumber);
        }
        return false;
    }
    
    // Method to cancel a ticket by ID
    public boolean cancelTicketById(String ticketId) {
        for (Ticket ticket : tickets) {
            if (ticket.getTicketId().equals(ticketId)) {
                switch (ticket.getCoachType()) {
                    case 1:
                        if (ac1Coach[0].cancelSeat(ticket.getSeatNumber())) {
                            tickets.remove(ticket);
                            return true;
                        }
                        break;
                    case 2:
                        if (cancelSeatInAC2Coaches(ticket.getSeatNumber())) {
                            tickets.remove(ticket);
                            return true;
                        }
                        break;
                    case 3:
                        if (cancelSeatInAC3Coaches(ticket.getSeatNumber())) {
                            tickets.remove(ticket);
                            return true;
                        }
                        break;
                    case 4:
                        if (cancelSeatInSleeperCoaches(ticket.getSeatNumber())) {
                            tickets.remove(ticket);
                            return true;
                        }
                        break;
                }
            }
        }
        System.out.println("Ticket with ID " + ticketId + " not found.");
        return false;
    }

    private boolean cancelSeatInAC2Coaches(int seatNumber) {
        for (AC2Coach coach : ac2Coaches) {
            if (coach.cancelSeat(seatNumber)) {
                return true;
            }
        }
        return false;
    }

    private boolean cancelSeatInAC3Coaches(int seatNumber) {
        for (AC3Coach coach : ac3Coaches) {
            if (coach.cancelSeat(seatNumber)) {
                return true;
            }
        }
        return false;
    }

    private boolean cancelSeatInSleeperCoaches(int seatNumber) {
        for (SleeperCoach coach : sleeperCoaches) {
            if (coach.cancelSeat(seatNumber)) {
                return true;
            }
        }
        return false;
    }
    
    public void displayBookedTickets() {
        System.out.println("Booked Tickets for Train " + trainId + ":");
        for (Ticket ticket : tickets) {
            System.out.println("Ticket ID: " + ticket.getTicketId() + ", Coach Type: " + ticket.getCoachType() + ", Seat Number: " + ticket.getSeatNumber());
        }
    }

    public String getTrainId() {
        return this.trainId;
    }
}
