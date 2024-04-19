import java.io.Serializable;

class SleeperCoach extends BaseCoach implements Serializable {
    private static final long serialVersionUID = -8215664554432974467L;

    static final private double COACH_PRICE = 500.0;

    public SleeperCoach() {
        super(100);
    }

    @Override
    public void displayCoachDetails() {
        System.out.println("Sleeper Coach - Number of Seats: " + (CAPACITY - this.bookedSeats));
    }

    public static double coachPrice() {
        return COACH_PRICE;
    }

}