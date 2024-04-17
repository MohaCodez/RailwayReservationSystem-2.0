import java.util.ArrayList;
import java.util.HashMap;
import java.util.*;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;

public class Main {
    private static HashMap<String, Train> trainMap = new HashMap<>();
    private static HashMap<String, Route> routeMap = new HashMap<>();
    private static String filePath = "data.ser";

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
        File dataFile = new File(filePath);
        if (dataFile.length() == 0) {
            trainMap.put("RJ123", new Train("RJ123", goaToMumbaiRoute));
            trainMap.put("RJ126", new Train("RJ126", goaToMumbaiRoute));
            trainMap.put("RJ129", new Train("RJ129", goaToMumbaiRoute));
            trainMap.put("RJ131", new Train("RJ131", mumbaiToGoaRoute));
            trainMap.put("RJ135", new Train("RJ135", goaToBangaloreRoute));

            HashMapIO.writeHashMapToFile(filePath, trainMap);

        } else {
            trainMap = HashMapIO.readHashMapFromFile(filePath);
        }

        System.out.println("Hi! Welcome to the Railway Reservation System!");
        // Scanner scanner = new Scanner(System.in); // Create a Scanner object to read
        // input
        int input = -1;
        do {
            try {
                System.out.println("Press:\n[1] Login\n[2] Sign-up\nEnter your choice: ");
                input = scanner.nextInt();
                if (input != 1 && input != 2) {
                    System.out.println("Please enter a valid value.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter an integer value!");
                scanner.next(); // Consume the invalid input
            }
        } while (input != 1 && input != 2);

        switch (input) {
            case 1:

                break;
            case 2:

                break;

            default:
                System.out.println("Invalid choice. Please enter a valid input!");
                break;
        }

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

        // for (Map.Entry<String, Train> entry : trainMap.entrySet()) {
        // System.out.println("Key: " + entry.getKey() + ", Value: " +
        // entry.getValue());
        // }
        //
        // for (Map.Entry<String, Ticket> entry :
        // ticketManager.getBookedTickets().entrySet()) {
        // System.out.println("Key: " + entry.getKey() + ", Value: " +
        // entry.getValue());
        // }

    }

    private static void bookTicket(TicketManager ticketManager, Scanner scanner) {
        // Ask for person category
        System.out.println("\nChoose passenger category:");
        System.out.println("[1] Student (30% OFF)");
        System.out.println("[2] Senior Citizen (40% OFF)");
        System.out.println("[3] Military (50% OFF)");
        System.out.println("[4] Disabled (55% OFF)");
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
                "\nEnter route  \n[1] Goa to Mumbai \n[2] Mumbai to Goa \n[3] Goa to Bangalore\nEnter Choice: ");
        String routeChoice = scanner.nextLine();
        while (!routeMap.containsKey(routeChoice)) {
            System.out
                    .print("Enter route  \n[1] Goa to Mumbai \n[2] Mumbai to Goa \n[3] Goa to Bangalore\nEnter Choice: ");
            String routeChoicex = scanner.nextLine();
            routeChoice = routeChoicex;
        }
        Route route = routeMap.get(routeChoice);

        trainMap = HashMapIO.readHashMapFromFile(filePath);
        List<Train> trains = new ArrayList<>(trainMap.values());
        // System.out.println(trains);

        System.out.println("Following are the Train IDs for desired Route");
        // System.out.println(trainMap);

        for (Train train : trains) {
            // System.out.println(train.getRoute().getId() + " debug! " + route.getId());
            if (train.getRoute().getId() == route.getId()) {
                // System.out.println("Hi");
                System.out.println("\nTrain ID:" + train.getTrainId() + "\nRoute Description \n"
                        + train.getRoute().getDescription());
            }

        }

        System.out.println("\nEnter train ID: ");
        String trainId = scanner.next();
        while ((!trainMap.containsKey(trainId))) {

            System.out.println("Invalid train ID. Please enter a valid train ID.:");
            String trainId2 = scanner.nextLine();
            trainId = trainId2;

        }

        Train train = trainMap.get(trainId);

        // Ask for coach details
        System.out.print(
                "\nList of Available Coaches: \n[1] for AC1 \n[2] for AC2 \n[3] for AC3\n[4] for Sleeper \nEnter Coach Type: ");
        int coachType = scanner.nextInt();

        System.out.print("Enter coach number: ");
        int coachNumber = -1;

        switch (coachType) {
            case 1: // AC1
                System.out.print("Enter coach number (0): ");
                coachNumber = scanner.nextInt();
                while (coachNumber != 0) {
                    System.out.print("Invalid coach number. Please enter 0: ");
                    coachNumber = scanner.nextInt();
                }
                break;
            case 2: // AC2
                System.out.print("Enter coach number (0 or 1): ");
                coachNumber = scanner.nextInt();
                while (coachNumber != 0 && coachNumber != 1) {
                    System.out.print("Invalid coach number. Please enter 0 or 1: ");
                    coachNumber = scanner.nextInt();
                }
                break;
            case 3: // AC3
                System.out.print("Enter coach number (0, 1, or 2): ");
                coachNumber = scanner.nextInt();
                while (coachNumber != 0 && coachNumber != 1 && coachNumber != 2) {
                    System.out.print("Invalid coach number. Please enter 0, 1, or 2: ");
                    coachNumber = scanner.nextInt();
                }
                break;
            default:
                System.out.println("Invalid coach type.");
                return; // Exit the method if coach type is invalid
        }

        BaseCoach coach = train.getCoach(coachType, coachNumber);
        coach.displayAvailableSeats();

        // IMPLEMENT MULTIPLE SEAT BOOKING ALSO

        System.out.print("Enter seat number: ");
        int seatNumber = scanner.nextInt();
        while (coach.isSeatBooked(seatNumber)) {
            System.out.print("Seat " + seatNumber + " is already booked. Enter a different seat number: ");
            seatNumber = scanner.nextInt();
        }

        // Book the ticket using the TicketManager
        Ticket ticket = ticketManager.bookTicket(person, train, coachType, seatNumber, coachNumber);
        if (ticket != null) {
            System.out.println("Ticket booked successfully:");
            // Update the state of the coach after booking a ticket
            train.updateCoachState(coachType, coachNumber, seatNumber, true);

            System.out.println(ticket.getDetails());
            // ADDING BACK TO THE TRAINMAP
            trainMap.put(trainId, train);
            HashMapIO.writeHashMapToFile(filePath, trainMap);
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
            // Extract coach and seat information from the ticket
            int coachType = ticket.getCoachType();
            int coachNumber = ticket.getCoachNumber();
            int seatNumber = ticket.getSeatNumber();
            // Retrieve the corresponding train from the trainMap
            trainMap = HashMapIO.readHashMapFromFile(filePath);
            Train train = trainMap.get(trainId);

            // Cancel the seat in the train's coach
            boolean seatCancelled = train.cancelTicketById(ticketId);

            // Remove the ticket from the TicketManager's list of booked tickets
            if (seatCancelled) {
                ticketManager.cancelTicketById(ticketId);
                System.out.println("Ticket with ID " + ticketId + " has been cancelled successfully.");
                // Update the state of the coach after booking a ticket
                train.updateCoachState(coachType, coachNumber, seatNumber, false);

                // ADDING BACK TO THE TRAINMAP
                trainMap.put(trainId, train);
                HashMapIO.writeHashMapToFile(filePath, trainMap);
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
