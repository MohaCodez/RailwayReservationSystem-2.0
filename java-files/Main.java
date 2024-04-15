import java.util.ArrayList;
import java.util.HashMap;
import java.util.*;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalTime;

public class Main {
    private static Map<String, Train> trainMap = new HashMap<>();
    private static Map<String, Route> routeMap = new HashMap<>();

    public static void main(String[] args) {

        TicketManager ticketManager = new TicketManager();
        Scanner scanner = new Scanner(System.in);
        int choice;

        // Create routes
        // Map<String, Route> routeMap = new HashMap<>();

        Route goaToMumbaiRoute = new Route(1, "Goa", "Mumbai", LocalTime.of(8, 0), LocalTime.of(14, 0),
                LocalDate.of(2024, 4, 15), 1500.0);
        Route mumbaiToGoaRoute = new Route(2, "Mumbai", "Goa", LocalTime.of(10, 0), LocalTime.of(16, 0),
                LocalDate.of(2024, 4, 16), 1500.0);
        Route goaToBangaloreRoute = new Route(3, "Goa", "Bangalore", LocalTime.of(9, 0), LocalTime.of(18, 0),
                LocalDate.of(2024, 4, 17), 2000.0);

        routeMap.put("1", goaToMumbaiRoute);
        routeMap.put("2", mumbaiToGoaRoute);
        routeMap.put("3", goaToBangaloreRoute);

        // Create a HashMap to store trains
        // Map<String, Train> trainMap = new HashMap<>();

        // Add trains to the HashMap
        trainMap.put("RJ123", new Train("RJ123", goaToMumbaiRoute));
        trainMap.put("RJ126", new Train("RJ126", goaToMumbaiRoute));
        trainMap.put("RJ129", new Train("RJ129", goaToMumbaiRoute));
        trainMap.put("RJ131", new Train("RJ131", mumbaiToGoaRoute));
        trainMap.put("RJ135", new Train("RJ135", goaToBangaloreRoute));

        do {
            System.out.println("\nWelcome to Railway Reservation System Main Menu! Press:");
            System.out.println("[1] Book a ticket");
            System.out.println("[2] Cancel a ticket");
            System.out.println("[3] Check ticket status");
            System.out.println("[4] Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    bookTicket(ticketManager, scanner);
                    break;
                case 2:
                    cancelTicket(ticketManager, scanner);
                    break;
                case 3:
                    checkTicketStatus(ticketManager, scanner);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 4.");
                    break;
            }
        } while (choice != 4);

        scanner.close();
    }

    private static void bookTicket(TicketManager ticketManager, Scanner scanner) {
        // Ask for person category
        System.out.println("\nChoose passenger category:");
        System.out.println("[1] Student");
        System.out.println("[2] Senior Citizen");
        System.out.println("[3] Military");
        System.out.println("[4] Disabled");
        System.out.println("[5] General");
        System.out.print("Enter category number: ");
        int categoryChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        // Ask for person details
        System.out.print("Enter passenger's name: ");
        String name = scanner.nextLine();
        System.out.print("Enter passenger's age: ");
        int age = 0;
        try {
            age = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Enter a valid age!");
        }
        scanner.nextLine(); // Consume newline
        System.out.print("Enter passenger's phone number: ");
        String phoneNumber = scanner.nextLine();
        System.out.print("Enter passenger's email: ");
        String email = scanner.nextLine();

        // Create the Person object based on the provided category and details
        Person person;
        switch (categoryChoice) {
            case 1:
                person = new Student(name, age, phoneNumber, email);
                break;
            case 2:
                person = new SeniorCitizen(name, age, phoneNumber, email);
                break;
            case 3:
                person = new Military(name, age, phoneNumber, email);
                break;
            case 4:
                person = new Disabled(name, age, phoneNumber, email);
                break;
            case 5:
            default:
                person = new General(name, age, phoneNumber, email);
                break;
        }

        // Ask for train details

        System.out.print(
                "\nEnter route  \n[1] Goa to Mumbai \n[2] Mumbai to Goa \n[3] Goa to Bangalore\n Enter Choice: ");
        String routeChoice = scanner.nextLine();
        while (!routeMap.containsKey(routeChoice)) {
            System.out
                    .print("Enter route  \n[1] Goa to Mumbai \n[2] Mumbai to Goa \n[3] Goa to Bangalore\n Enter Choice: ");
            String routeChoicex = scanner.nextLine();
            routeChoice = routeChoicex;
        }
        Route route = routeMap.get(routeChoice);

        List<Train> trains = new ArrayList<>(trainMap.values());

        System.out.println("Following are the Train IDs for desired Route");

        for (Train train : trains) {
            if (train.getRoute() == route) {
                System.out.println("\nTrain ID:" + train.getTrainId() + "\nRoute Description \n"
                        + train.getRoute().getDescription());
            }

        }

        System.out.println("Enter train ID: ");
        String trainId = scanner.nextLine();
        while ((!trainMap.containsKey(trainId))) {

            System.out.println("Invalid train ID. Please enter a valid train ID.:");
            String trainId2 = scanner.nextLine();
            trainId = trainId2;

        }

        Train train = trainMap.get(trainId);

        // Ask for coach details
        System.out.print(
                "\nList of Available Coaches: \n[1] for AC1 \n[2] for AC2 \n[3] for AC3c\n[4] for Sleeper \nEnter Coach Type: ");
        int coachType = scanner.nextInt();
        HashMap<String, String> stringMap = new HashMap<>();
        stringMap.put("HEADER_AC1", "    A   B     C   D");
        // stringMap.put("EMPTY_ROW_AC1", "  [ ] [ ]   [ ] [ ]");
        // stringMap.put("BOOKED_ROW_AC1", "  [x] [x]   [x] [x]");
        stringMap.put("BOOKED_SEAT", "[x]");
        stringMap.put("EMPTY_SEAT", "[ ]");

        System.out.println(stringMap.get("HEADER_AC1"));
        for (Integer i = 1; i < 11; i++) {
            if (i>=10) {
                
            }
            System.out.println(i.toString() + stringMap.get("EMPTY_ROW_AC1"));
        }

        System.out.print("Enter seat number: ");
        int seatNumber = scanner.nextInt();
        System.out.print("Enter coach number: ");
        int coachNumber = scanner.nextInt();

        // Book the ticket using the TicketManager
        Ticket ticket = ticketManager.bookTicket(person, train, coachType, seatNumber, coachNumber);
        if (ticket != null) {
            System.out.println("Ticket booked successfully:");
            System.out.println(ticket.getDetails());
        } else {
            System.out.println("Failed to book the ticket. Please try again.");
        }
    }

    private static void cancelTicket(TicketManager ticketManager, Scanner scanner) {
        // Ask the user for the ticket ID to cancel
        System.out.print("Enter the ticket ID to cancel: ");
        String ticketId = scanner.nextLine();

        // Check if the ticket ID exists in the TicketManager's list of booked tickets
        if (ticketManager.getBookedTickets().containsKey(ticketId)) {
            // Retrieve the corresponding ticket from the TicketManager
            Ticket ticket = ticketManager.getBookedTickets().get(ticketId);

            // Extract information from the ticket
            String trainId = ticket.getTrainID();
            int coachType = ticket.getCoachType();
            int seatNumber = ticket.getSeatNumber();
            int coachNumber = ticket.getCoachNumber();

            // Retrieve the corresponding train from the trainMap
            Train train = trainMap.get(trainId);

            // Cancel the seat in the train's coach
            boolean seatCancelled = train.cancelTicketById(ticketId);

            // Remove the ticket from the TicketManager's list of booked tickets
            if (seatCancelled) {
                ticketManager.cancelTicketById(ticketId);
                System.out.println("Ticket with ID " + ticketId + " has been cancelled successfully.");
            } else {
                System.out.println("Failed to cancel the ticket. Please try again.");
            }
        } else {
            System.out.println("Ticket with ID " + ticketId + " does not exist.");
        }
    }

    private static void checkTicketStatus(TicketManager ticketManager, Scanner scanner) {
        // Ask the user for the ticket ID to check the status for
        System.out.print("Enter the ticket ID to check the status: ");
        String ticketId = scanner.nextLine();

        // Check if the ticket ID exists in the TicketManager's list of booked tickets
        if (ticketManager.getBookedTickets().containsKey(ticketId)) {
            // Retrieve the corresponding ticket from the TicketManager
            Ticket ticket = ticketManager.getBookedTickets().get(ticketId);

            // Display ticket information
            System.out.println("\nTicket Information:");
            System.out.println("Ticket ID: " + ticket.getTicketId());
            System.out.println("Passenger Name: " + ticket.getPerson().getName());
            System.out.println("Passenger Age: " + ticket.getPerson().getAge());
            System.out.println("Passenger Phone Number: " + ticket.getPerson().getPhoneNumber());
            System.out.println("Passenger Email: " + ticket.getPerson().getEmailId());
            System.out.println("Train ID: " + ticket.getTrainID());
            System.out.println("Coach Type: " + ticket.getCoachType());
            System.out.println("Seat Number: " + ticket.getSeatNumber());
            System.out.println("Coach Number: " + ticket.getCoachNumber());
            // You can add more ticket details if needed

        } else {
            System.out.println("Ticket with ID " + ticketId + " does not exist.");
        }
    }

}
