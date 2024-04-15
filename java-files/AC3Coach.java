
class AC3Coach extends BaseCoach {
    static final private double COACH_PRICE = 1000.0;

    public AC3Coach() {
        super(90);
    }

    @Override
    public void displayCoachDetails() {
        System.out.println("AC3 Coach - Number of Seats: " + (CAPACITY - this.bookedSeats));
    }
    
}