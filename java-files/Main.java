import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


    	
    	Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("Railway Reservation System Menu:");
            System.out.println("[1] Book Ticket");
            System.out.println("[2] Cancel Ticket");
            System.out.println("[3] View Booking Status");
            System.out.println("[4] Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            
            switch (choice) {
                case 1:
                    // Handle booking ticket
                    // Example: bookingSystem.bookTicket();
                    break;
                case 2:
                    // Handle canceling ticket
                    // Example: bookingSystem.cancelTicket();
                    break;
                case 3:
                    // Handle viewing booking status
                    // Example: bookingSystem.viewBookingStatus();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (choice != 4);
    }
}