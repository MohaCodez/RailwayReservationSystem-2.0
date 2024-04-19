
public class Student extends Passenger {
    private static final double DISCOUNT_PERCENTAGE = 0.3;

    public Student(String name, int age, String phoneNumber, String emailId) {
        super(name, age, phoneNumber, emailId);
    }

    @Override
    public double calculateDiscount(double ticketPrice) {
        return ticketPrice * DISCOUNT_PERCENTAGE;
    }
}