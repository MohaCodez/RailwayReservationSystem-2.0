import java.util.ArrayList;
import java.util.HashMap;
import java.util.*;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Main {
    private static HashMap<String, Train> trainMap = new HashMap<>();
    private static HashMap<String, Route> routeMap = new HashMap<>();
    private static HashMap<String, User> registeredUsers = new HashMap<String, User>();
    private static String filePath = "traindata.ser"; // train data file path
    public static String userDataFilePath = "userdata.ser"; // user data file path
    static User loggedInUser = new User();

    public static void main(String[] args) {

        TicketManager ticketManager = new TicketManager();
        Admin rrsAdmin = new Admin();
        Scanner scanner = new Scanner(System.in);
        int choice;
        boolean isTatkaalPossible = false;

        LocalTime startTime = LocalTime.of(9, 0); // Example: 9:00 AM
        LocalTime endTime = LocalTime.of(10, 0); // Example: 5:00 PM

        // Get the current time
        LocalTime currentTime = LocalTime.now();
        LocalDate currentDate = LocalDate.now();

        // Check if the current time is within the specified range
        if (currentTime.isAfter(startTime) && currentTime.isBefore(endTime)) {
            isTatkaalPossible = true;
            System.out.println("Tatkaal Option is available!");
        } else {
            System.out.println("Tatkaal Option is not available!");
        }

        Route goaToMumbaiRoute = new Route(1, "Goa", "Mumbai", LocalTime.of(10, 0), LocalTime.of(14, 0),
                currentDate.plusDays(1), 1500.0);
        Route mumbaiToGoaRoute = new Route(2, "Mumbai", "Goa", LocalTime.of(12, 0), LocalTime.of(16, 0),
                currentDate.plusDays(2), 1500.0);
        Route goaToBangaloreRoute = new Route(3, "Goa", "Bangalore", LocalTime.of(14, 0), LocalTime.of(16, 0),
                currentDate.plusDays(2), 2000.0);

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

        File dataFile2 = new File(userDataFilePath);
        boolean signUpCompulsory = false;
        if (dataFile2.length() == 0) {
            // User Deven = new User("Deven", 21, "9871422650", "sassysniper007@gmail.com");
            // registeredUsers.put(Deven.getPhoneNumber(), Deven);

            signUpCompulsory = true;
            // UserHashMapIO.writeHashMapToFile(filePath, registeredUsers);

        } else {
            registeredUsers = UserHashMapIO.readHashMapFromFile(userDataFilePath);
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
                if (signUpCompulsory == true) {
                    System.out.println("No accounts exist! You must Sign-Up first to continue.");
                    signUp(rrsAdmin, scanner);
                } else {
                    login(rrsAdmin, scanner);
                }

                break;
            case 2:
                signUp(rrsAdmin, scanner);
                login(rrsAdmin, scanner);

                break;

            default:
                System.out.println("Invalid choice. Please enter a valid input!");
                break;
        }

        do {
            System.out.println("\nWelcome to Railway Reservation System Main Menu! \nPress:");
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
        } while (choice != 4 && loggedInUser.getIsUserLoggedIn());

        scanner.close();

    }

    private static void login(Admin rrsAdmin, Scanner scanner) {

        System.out.println("\nWelcome to the login page of RRS");
        System.out.println("Enter your phone number: ");
        String enteredPhoneNumber = scanner.next(); // Read user input
        String enteredPassword;

        registeredUsers = UserHashMapIO.readHashMapFromFile(userDataFilePath);
        System.out.println(registeredUsers);
        Set<String> keys = registeredUsers.keySet();
        System.out.println(keys);
        boolean flag = false;
        for (String key : keys) {
            if (key.equals(enteredPhoneNumber)) {
                flag = true;
                do {
                    System.out.println("Enter your password: ");
                    enteredPassword = scanner.next();

                    if (registeredUsers.get(key).getPassword().equals(enteredPassword)) {
                        loggedInUser = registeredUsers.get(key);
                        loggedInUser.setUserLoggedIn(true);
                        System.out.println("User logged in successfully!");
                    } else {
                        System.out.println("Incorrect password! Please try again.");
                        loggedInUser.setUserLoggedIn(false);

                    }
                } while (!registeredUsers.get(key).getPassword().equals(enteredPassword));
            } else {
                continue;
            }

        }
        if (flag == false) {
            System.out.println(
                    "This phone number does not exist in the database. Create a new account with this number or login.");
            System.exit(0);
        }

    }

    private static void signUp(Admin rrsAdmin, Scanner scanner) {
        System.out.println("Enter your first name: ");
        String enteredName = scanner.next(); // Read user input

        boolean flag2 = false;
        String dobString = "";

        do {

            try {
                System.out.println("Enter your date of birth (DD-MM-YYYY):");
                dobString = scanner.next();
                flag2 = false;
            } catch (DateTimeParseException e) {
                System.out.println("Bad date entered! Try again!");
                flag2 = true;
            }

        } while (flag2);
        // Define the date format
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        // Parse the date of birth string into a LocalDate object
        LocalDate dob = LocalDate.parse(dobString, dateFormatter);

        // Get the current date
        LocalDate currentDate = LocalDate.now();

        // Calculate the age using Period class
        Period age = Period.between(dob, currentDate);
        int currentAge = age.getYears();

        System.out.println("Enter your email: ");
        String enteredEmail = scanner.next(); // Read user input

        System.out.println("Enter your phone number: ");
        String enteredPhoneNumber = scanner.next(); // Read user input

        String enteredPassword = "";
        String enteredConfirmPassword = "";

        do {
            System.out.println("Enter your password: ");
            enteredPassword = scanner.next(); // Read user input

            System.out.println("Confirm your password: ");
            enteredConfirmPassword = scanner.next();
            if (enteredConfirmPassword.equals(enteredPassword) == false) {
                System.out.println("The passwords do not match! Enter the passwords again:");
            }

        } while (!enteredConfirmPassword.equals(enteredPassword));

        // System.out.println("reached here!");

        rrsAdmin.signUserUp(enteredName, currentAge, enteredPhoneNumber, enteredEmail, enteredPassword);

        // System.out.println("reached here!2");
        // Read user input
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

        System.out.print("Would you like to auto-fill details?(Y/N): ");
        String aChoice = scanner.next().toLowerCase();

        String name = "foo", phoneNumber = "foo", email = "foo@foo.com";
        int age = 0;

        if (aChoice.equals("y")) {
            // foo
            registeredUsers = UserHashMapIO.readHashMapFromFile(userDataFilePath);
            name = loggedInUser.getName();
            age = loggedInUser.getAge();
            phoneNumber = loggedInUser.getPhoneNumber();
            email = loggedInUser.getEmailId();

        } else {
            // Ask for person details
            System.out.print("Enter passenger's name: ");
            name = scanner.nextLine();
            System.out.print("Enter passenger's age: ");
            try {
                age = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Enter a valid age!");
            }
            scanner.nextLine(); // Consume newline
            System.out.print("Enter passenger's phone number: ");
            phoneNumber = scanner.nextLine();
            System.out.print("Enter passenger's email: ");
            email = scanner.nextLine();

        }

        // Create the Person object based on the provided category and details
        Passenger passenger;
        switch (categoryChoice) {
            case 1:
                passenger = new Student(name, age, phoneNumber, email);
                break;
            case 2:
                passenger = new SeniorCitizen(name, age, phoneNumber, email);
                break;
            case 3:
                passenger = new Military(name, age, phoneNumber, email);
                break;
            case 4:
                passenger = new Disabled(name, age, phoneNumber, email);
                break;
            case 5:
            default:
                passenger = new General(name, age, phoneNumber, email);
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
                "\nList of Available Coaches: \n[1] for AC1 [Rs. " + AC1Coach.coachPrice() + "]\n[2] for AC2 [Rs. "
                        + AC2Coach.coachPrice() + "]\n[3] for AC3 [Rs. " + AC3Coach.coachPrice()
                        + "]\n[4] for Sleeper [Rs. "
                        + SleeperCoach.coachPrice() + "]\nEnter Coach Type: ");
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
        Ticket ticket = ticketManager.bookTicket(passenger, train, coachType, seatNumber, coachNumber);
        if (ticket != null) {
            System.out.println("\nTicket booked successfully:");
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
            System.out.println("Passenger Name: " + ticket.getPassenger().getName());
            System.out.println("Passenger Age: " + ticket.getPassenger().getAge());
            System.out.println("Passenger Phone Number: " + ticket.getPassenger().getPhoneNumber());
            System.out.println("Passenger Email: " + ticket.getPassenger().getEmailId());
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