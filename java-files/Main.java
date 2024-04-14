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
                    System.out.print("Enter your full name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter your age: ");
                    int age = scanner.nextInt();
                    System.out.print("Enter your phone number: ");
                    String phnNo = scanner.nextLine();
                    System.out.print("Enter your email ID: ");
                    String emailID = scanner.nextLine();
                    System.out.print(
                            "Do you belong to any of these categories: Student/Senior/Military/Disabled?\n(Y/N): ");
                    String isSpecialCategory = scanner.nextLine().toLowerCase();

                    if (isSpecialCategory.equals("y")) {
                        System.out.print("Enter your category(Student/Senior/Military/Disabled):");
                        String specialCategory = scanner.nextLine().toLowerCase();
                        Passenger passenger = new Passenger(name, age, phnNo, emailID, specialCategory);

                    } else {
                        Passenger passenger = new Passenger(name, age, phnNo, emailID);
                    }

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
        scanner.close();
    }
}