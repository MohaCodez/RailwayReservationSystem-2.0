import java.io.Serializable;

class AC2Coach extends BaseCoach implements Serializable {
    private static final long serialVersionUID = 6329203720461406622L;

    static final private double COACH_PRICE = 2000.0;

    public AC2Coach() {
        super(60);
    }

    @Override
    public void displayCoachDetails() {
        System.out.println("AC2 Coach - Number of Seats: " + (CAPACITY - this.bookedSeats));
    }

    public static double coachPrice() {
        return COACH_PRICE;
    }

}