import java.io.Serializable;

class AC1Coach extends BaseCoach implements Serializable {
    private static final long serialVersionUID = -2517922310436061952L;

    static final private double COACH_PRICE = 3000.0;

    public AC1Coach() {
        super(40);
    }

    @Override
    public void displayCoachDetails() {
        System.out.println("AC1 Coach - Number of Seats: " + (CAPACITY - this.bookedSeats));
    }

    public static double coachPrice() {
        return COACH_PRICE;
    }

}