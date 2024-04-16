
class SleeperCoach extends BaseCoach {
    static final private double COACH_PRICE = 500.0;

    public SleeperCoach() {
        super(100);
    }

    @Override
    public void displayCoachDetails() {
        System.out.println("Sleeper Coach - Number of Seats: " + (CAPACITY - this.bookedSeats));
    }

}