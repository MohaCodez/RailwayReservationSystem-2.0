
public class General extends Person {
    public General(String name, int age, String phoneNumber, String emailId) {
        super(name, age, phoneNumber, emailId);
    }

    @Override
    public double calculateDiscount(double ticketPrice) {
        return 0; // No discount for general category
    }
}