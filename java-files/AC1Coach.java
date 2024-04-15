
class AC1Coach extends BaseCoach {
    static final private double COACH_PRICE = 3000.0;

    public AC1Coach() {
        super(40);
    }

    @Override
    public void displayCoachDetails() {
        System.out.println("AC1 Coach - Number of Seats: " + (CAPACITY - this.bookedSeats));
    }
}