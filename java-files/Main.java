import java.util.ArrayList;
import java.util.HashMap;
import java.util.*;
import java.util.List;
import java.util.Scanner;
import java.io.Console;
import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Main {
    // clearConsole();
    public static boolean isTatkaalPossible = false;
    private static HashMap<String, Train> trainMap = new HashMap<>();
    private static HashMap<String, Route> routeMap = new HashMap<>();
    private static HashMap<String, User> registeredUsers = new HashMap<String, User>();
    private static String filePath = "traindata.ser"; // train data file path
    public static String userDataFilePath = "userdata.ser"; // user data file path
    static User loggedInUser = new User();

    public static void main(String[] args) {
        clearConsole();

        TicketManager ticketManager = new TicketManager();
        TicketRequestManager requestManager = new TicketRequestManager();
        Admin rrsAdmin = new Admin();
        Scanner scanner = new Scanner(System.in);
        int choice;

        int tatkalStartHrs = 11;
        int tatkalEndHrs = tatkalStartHrs + 1;

        LocalTime startTime = LocalTime.of(tatkalStartHrs, 0); // Example: 9:00 AM
        LocalTime endTime = LocalTime.of(tatkalEndHrs, 0); // Example: 5:00 PM

        // Get the current time
        LocalTime currentTime = LocalTime.now();
        LocalDate currentDate = LocalDate.now();

        // Check if the current time is within the specified range
        System.out.println("#####################################################################\n");
        if (currentTime.isAfter(startTime) && currentTime.isBefore(endTime)) {
            isTatkaalPossible = true;
            System.out.println("                   Tatkal Option is available!                   ");
        } else {
            System.out.println("Tatkal Option is not available! Come back between " + tatkalStartHrs + ":00 and "
                    + tatkalEndHrs + ":00 HRS.");
        }
        System.out.println("\n#####################################################################\n");

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
                System.out.print("Press:\n[1] Login\n[2] Sign-up\nEnter your choice: ");
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
            System.out.println("[4] Employee Login");
            System.out.println("[5] Exit");
            System.out.println("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    int bChoice = 1;
                    if (isTatkaalPossible) {
                        System.out.print(
                                "\nWould you like to do a:\n[1] Normal Booking\n[2] Tatkal Booking\nEnter your choice: ");
                        bChoice = scanner.nextInt();
                        switch (bChoice) {
                            case 1:
                                bookTicket(ticketManager, scanner);
                                break;
                            case 2:
                                tatkalBookingRequest(requestManager, scanner);
                                break;
                            default:
                                System.out.println("Invalid choice. Please enter 1 or 2.");
                                break;

                        }

                    } else {
                        bookTicket(ticketManager, scanner);
                        break;

                    }
                    break;
                case 2:
                    cancelTicket(ticketManager, scanner);
                    break;
                case 3:
                    System.out.println("\n[1] Normal Ticket");
                    System.out.println("[2] Tatkal Ticket");
                    System.out.print("Enter your choice: ");
                    int ticketType1 = scanner.nextInt();

                    switch (ticketType1) {
                        case 1:
                            Scanner scanner2 = new Scanner(System.in);
                            System.out.println("Enter the ticket ID(s) to check the status: ");
                            String ticketIDs = scanner2.nextLine();
                            // System.out.println("Input ticket IDs: " + ticketIDs); // Debug statement
                            String[] arrayOfTicketIDs = ticketIDs.split("\\s+");
                            // System.out.println("Array of ticket IDs: " +
                            // Arrays.toString(arrayOfTicketIDs)); // Debug
                            // statement
                            checkTicketStatus(ticketManager, scanner2, arrayOfTicketIDs);
                            break;

                        case 2:
                            // TatkalStatusCheck
                            checkTatkalStatus(requestManager, scanner);
                            break;
                        default:
                            System.out.println("Invalid choice. Please enter a number between 1 and 4.");
                            break;
                    }
                    break;
                case 4:
                    tatkalBookerEmployeeTasks(requestManager, scanner);
                    break;
                case 5:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
                    break;
            }
        } while (choice != 5 && loggedInUser.getIsUserLoggedIn());

        scanner.close();

    }

    private static void tatkalBookerEmployeeTasks(TicketRequestManager requestManager, Scanner scanner) {
        // TODO Auto-generated method stub
        TatkalTicketBooker Employee = new TatkalTicketBooker();
        System.out.print("If you are an EMPLOYEE, enter ");
        String password = readPassword();
        if (!password.equals(Employee.getPassword())) {
            System.out.print("Incorrect Password");
        } else {
            List<TicketRequest> requests = new ArrayList<>(requestManager.getRequests().values());
            for (TicketRequest request : requests) {
                requestManager.bookTicket(request);
            }
        }

    }

    private static void checkTatkalStatus(TicketRequestManager requestManager, Scanner scanner2) {
        // TODO Auto-generated method stub
        System.out.print("Enter the request ID to check the status: ");
        String requestId = scanner2.next();

        if (requestManager.getRequests().containsKey(requestId)) {
            requestManager.getRequests().get(requestId).displayRequestDetails();
        }
    }

    private static String readPassword() {
        Console console = System.console();
        if (console == null) {
            throw new RuntimeException("Console not available");
        }

        char[] passwordChars = console.readPassword("your password: ");
        return new String(passwordChars);
    }

    public static void clearConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                Runtime.getRuntime().exec("clear");
            }
        } catch (Exception e) {
            // Handle any exceptions
            e.printStackTrace();
        }
    }

    private static void tatkalBookingRequest(TicketRequestManager requestManager, Scanner scanner) {
        // TODO Auto-generated method stub
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
        String aChoice = scanner.nextLine().toLowerCase();

        String name = "foo", phoneNumber = "foo", email = "foo@foo.com";// just to initialize I believe.

        int age = 0;

        if (aChoice.equals("y")) {

            registeredUsers = UserHashMapIO.readHashMapFromFile(userDataFilePath);
            name = loggedInUser.getName();
            age = loggedInUser.getAge();
            phoneNumber = loggedInUser.getPhoneNumber();
            email = loggedInUser.getEmailId();

        } else {
            // Ask for person details
            System.out.print("Enter passenger's name: ");
            name = scanner.next();

            boolean isAgeInt = true;

            do {
                System.out.print("Enter passenger's age: ");
                try {
                    age = scanner.nextInt();
                    isAgeInt = true;
                } catch (InputMismatchException e) {
                    System.out.println("Enter a valid age!");
                    isAgeInt = false;
                    // Clear scanner buffer
                    scanner.next();
                }
            } while (!isAgeInt);
            System.out.print("Enter passenger's phone number: ");
            phoneNumber = scanner.next();
            System.out.print("Enter passenger's email: ");
            email = scanner.next();

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

        System.out
                .print("\nEnter route  \n[1] Goa to Mumbai \n[2] Mumbai to Goa \n[3] Goa to Bangalore\nEnter Choice: ");
        String routeChoice = scanner.next();

        while (!routeMap.containsKey(routeChoice)) {
            System.out.print(
                    "Enter route  \n[1] Goa to Mumbai \n[2] Mumbai to Goa \n[3] Goa to Bangalore\nEnter Choice: ");
            String routeChoicex = scanner.next();
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

        // System.out.print("Enter coach number: ");
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
            case 4:// Sleeper
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

        System.out.print("Enter seat number: ");
        int seatNumber = scanner.nextInt();
        while (coach.isSeatBooked(seatNumber)) {
            System.out.print("Seat " + seatNumber + " is already booked. Enter a different seat number: ");
            seatNumber = scanner.nextInt();
        }

        TicketRequest request = new TicketRequest(passenger, train, coachType, seatNumber, coachNumber);
        request.displayRequestDetails();
        requestManager.addRequestToWaitingList(request);

    }

    private static void login(Admin rrsAdmin, Scanner scanner) {

        System.out.println("\nWelcome to the login page of Railway Reservation System!");
        System.out.print("Enter your phone number: ");
        String enteredPhoneNumber = scanner.next(); // Read user input
        String enteredPassword;

        registeredUsers = UserHashMapIO.readHashMapFromFile(userDataFilePath);
        // System.out.println(registeredUsers);
        Set<String> keys = registeredUsers.keySet();
        // System.out.println(keys);
        boolean flag = false;
        for (String key : keys) {
            if (key.equals(enteredPhoneNumber)) {
                flag = true;
                do {
                    System.out.print("Enter ");
                    enteredPassword = readPassword(); // Read user input

                    if (registeredUsers.get(key).getPassword().equals(enteredPassword)) {
                        loggedInUser = registeredUsers.get(key);
                        loggedInUser.setUserLoggedIn(true);
                        clearConsole();
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
        System.out.print("Enter your first name: ");
        String enteredName = scanner.next(); // Read user input

        boolean flag2 = false;
        String dobString = "";

        do {

            try {
                System.out.print("Enter your date of birth (DD-MM-YYYY): ");
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

        System.out.print("Enter your email: ");
        String enteredEmail = scanner.next(); // Read user input

        System.out.print("Enter your phone number: ");
        String enteredPhoneNumber = scanner.next(); // Read user input

        String enteredPassword = "";
        String enteredConfirmPassword = "";

        do {
            System.out.print("Enter ");
            enteredPassword = readPassword(); // Read user input
            System.out.print("Confirm ");
            enteredConfirmPassword = readPassword();
            if (enteredConfirmPassword.equals(enteredPassword) == false) {
                System.out.println("The passwords do not match! Enter the passwords again: ");
            }

        } while (!enteredConfirmPassword.equals(enteredPassword));

        // System.out.println("reached here!");

        rrsAdmin.signUserUp(enteredName, currentAge, enteredPhoneNumber, enteredEmail, enteredPassword);

        // System.out.println("reached here!2");
        // Read user input
    }

    private static void bookTicket(TicketManager ticketManager, Scanner scanner) {
        // Ask for person category

        System.out.println("\nChoose passenger category: ");
        System.out.println("[1] Student (30% OFF)");
        System.out.println("[2] Senior Citizen (40% OFF)");
        System.out.println("[3] Military (50% OFF)");
        System.out.println("[4] Disabled (55% OFF)");
        System.out.println("[5] General");
        System.out.print("Enter category number: ");
        int categoryChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Would you like to auto-fill details?(Y/N): ");
        String aChoice = scanner.nextLine().toLowerCase();

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
            name = scanner.next();

            boolean isAgeInt = true;

            do {
                System.out.print("Enter passenger's age: ");
                try {
                    age = scanner.nextInt();
                    isAgeInt = true;
                } catch (InputMismatchException e) {
                    System.out.println("Enter a valid age!");
                    isAgeInt = false;
                    // Clear scanner buffer
                    scanner.next();
                }
            } while (!isAgeInt);
            System.out.print("Enter passenger's phone number: ");
            phoneNumber = scanner.next();
            System.out.print("Enter passenger's email: ");
            email = scanner.next();

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

        System.out
                .print("\nEnter route  \n[1] Goa to Mumbai \n[2] Mumbai to Goa \n[3] Goa to Bangalore\nEnter Choice: ");
        String routeChoice = scanner.next();
        while (!routeMap.containsKey(routeChoice)) {
            System.out.print(
                    "Enter route  \n[1] Goa to Mumbai \n[2] Mumbai to Goa \n[3] Goa to Bangalore\nEnter Choice: ");
            String routeChoicex = scanner.next();
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
                System.out.println("\nTrain ID: " + train.getTrainId() + "\nRoute Description \n"
                        + train.getRoute().getDescription());
            }

        }

        System.out.println("\nEnter train ID: ");
        String trainId = scanner.next();
        while ((!trainMap.containsKey(trainId))) {

            System.out.println("Invalid train ID. Please enter a valid train ID.: ");
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
            case 4:
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

        boolean allSeatsBooked = false;

        String[] arrayofSeatNos = null;

        String seatNumbers = "";

        Scanner scanner3 = new Scanner(System.in);
        do {
            System.out.println("Enter seat number(s): ");
            // scanner3 implementation
            String seatNumbers2 = scanner3.nextLine();
            seatNumbers = seatNumbers2;

            arrayofSeatNos = seatNumbers.split("\\s+");

            // scnner1 implementation
            // String seatNumbers2 = scanner.nextLine();
            // seatNumbers = seatNumbers2;
            // arrayofSeatNos = seatNumbers.split("\\s+");

            allSeatsBooked = true; // Assume all seats are booked until proven otherwise

            for (String seatNum : arrayofSeatNos) {
                if (coach.isSeatBooked(Integer.parseInt(seatNum))) {
                    System.out.print("Seat " + seatNum + " is already booked. ");
                    allSeatsBooked = false; // At least one seat is not booked
                    break; // Exit the loop, no need to check further
                }
            }

            if (!allSeatsBooked) {
                System.out.println("\nEnter different seat number(s) and try again!\n");
            }

        } while (!allSeatsBooked);

        // scanner3.close();

        // System.out.println(arrayofSeatNos.length);

        if (arrayofSeatNos.length == 1) {
            while (coach.isSeatBooked(Integer.parseInt(arrayofSeatNos[0]))) {
                System.out.print("Seat " + arrayofSeatNos[0] + " is already booked. Enter different seat number(s): ");
                arrayofSeatNos[0] = scanner.next();
                // arrayofSeatNos = seatNumbers.split("\\s+");
            }

            Ticket ticket = ticketManager.bookTicket(passenger, train, coachType, coachNumber,
                    Integer.parseInt(seatNumbers));
            if (ticket != null) {
                System.out.println("\nTicket booked successfully!\n ");
                // Update the state of the coach after booking a ticket
                train.updateCoachState(coachType, coachNumber, ticket.getSeatNumber(), true);

                System.out.println(ticket.getDetails());
                // ADDING BACK TO THE TRAINMAP
                trainMap.put(trainId, train);
                HashMapIO.writeHashMapToFile(filePath, trainMap);
            } else {
                System.out.println("Failed to book the ticket. Please try again.");
            }

        } else {

            List<Ticket> listOfTickets = ticketManager.bookTicket(passenger, train, coachType, coachNumber,
                    arrayofSeatNos);

            for (Ticket t : listOfTickets) {
                if (t != null) {
                    System.out.println("\nTicket booked successfully: ");
                    // Update the state of the coach after booking a ticket
                    train.updateCoachState(coachType, coachNumber, t.getSeatNumber(), true);

                    System.out.println(t.getDetails());
                    // ADDING BACK TO THE TRAINMAP
                    trainMap.put(trainId, train);
                    HashMapIO.writeHashMapToFile(filePath, trainMap);
                } else {
                    System.out.println("Failed to book the ticket. Please try again.");
                }

            }
        }

    }

    private static void cancelTicket(TicketManager ticketManager, Scanner scanner2) {
        // Ask the user for the ticket ID to cancel
        System.out.print("Enter the ticket ID to cancel: ");
        String ticketId = scanner2.next();

        // Check if the ticket ID exists in the TicketManager's list of booked tickets
        if (ticketManager.getBookedTickets().containsKey(ticketId)) {
            // Retrieve the corresponding ticket from the TicketManager
            Ticket ticket = ticketManager.getBookedTickets().get(ticketId);

            // Extract information from the ticket
            String trainId = ticket.getTrainID();
            // Extract coach and seat information from the ticket
            int coachType = ticket.getCoachIntType();
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

    private static void checkTicketStatus(TicketManager ticketManager, Scanner scanner, String... ticketIDs) {
        // Ask the user for the ticket ID to check the status for

        // Check if the ticket ID exists in the TicketManager's list of booked tickets
        for (String ticketId : ticketIDs) {
            // System.out.println("debug!");

            if (ticketManager.getBookedTickets().containsKey(ticketId)) {
                // Retrieve the corresponding ticket from the TicketManager
                Ticket ticket = ticketManager.getBookedTickets().get(ticketId);

                // Display ticket information
                System.out.println("\nTicket Information: ");
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
}
