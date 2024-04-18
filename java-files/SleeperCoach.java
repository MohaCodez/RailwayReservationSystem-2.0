
class SleeperCoach extends BaseCoach {
	private static final long serialVersionUID = 1L;
	static final private double COACH_PRICE = 500.0;

    public SleeperCoach() {
        super(100);
    }

    @Override
    public void displayCoachDetails() {
        System.out.println("Sleeper Coach - Number of Seats: " + (CAPACITY - this.bookedSeats));
    }

}