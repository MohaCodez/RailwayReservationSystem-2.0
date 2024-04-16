import java.io.Serializable;

class AC2Coach extends BaseCoach implements Serializable {
    static final private double COACH_PRICE = 2000.0;

    public AC2Coach() {
        super(60);
    }

    @Override
    public void displayCoachDetails() {
        System.out.println("AC2 Coach - Number of Seats: " + (CAPACITY - this.bookedSeats));
    }
}