
public class SeniorCitizen extends Person {
    private static final double DISCOUNT_PERCENTAGE = 0.5;

    public SeniorCitizen(String name, int age, String phoneNumber, String emailId) {
        super(name, age, phoneNumber, emailId);
    }

    @Override
    public double calculateDiscount(double ticketPrice) {
        return ticketPrice * DISCOUNT_PERCENTAGE;
    }
}