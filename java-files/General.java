
public class General extends Passenger {
	private static final long serialVersionUID = 1L;
	public General(String name, int age, String phoneNumber, String emailId) {
        super(name, age, phoneNumber, emailId);
    }

    @Override
    public double calculateDiscount(double ticketPrice) {
        return 0; // No discount for general category
    }
}