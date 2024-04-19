import java.io.Serializable;

class AC3Coach extends BaseCoach implements Serializable {
    private static final long serialVersionUID = -3225477767340293008L;

    static final private double COACH_PRICE = 1000.0;

    public AC3Coach() {
        super(90);
    }

    @Override
    public void displayCoachDetails() {
        System.out.println("AC3 Coach - Number of Seats: " + (CAPACITY - this.bookedSeats));
    }

    public static double coachPrice() {
        return COACH_PRICE;
    }

}