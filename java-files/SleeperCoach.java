public class SleeperCoach implements Coach {
	static final private double COACH_PRICE = 500.0;

    private int bookedSeats;
    final int CAPACITY = 100;
    private Seat[] seats;
    
    public SleeperCoach(){
        this.seats = new Seat[CAPACITY];
        for (int i = 0; i < CAPACITY; i++) {
            this.seats[i] = new Seat(); // Initialize each seat
        }
        this.bookedSeats = 0;
    }
    
    public boolean bookSeat(int seatNumber) {
        if (seatNumber < 0 || seatNumber >= CAPACITY) {
            throw new IllegalArgumentException("Invalid seat number");
        }
        
        if (!this.seats[seatNumber].isBooked()) {
            this.seats[seatNumber].markAsBooked();
            this.bookedSeats++;
            return true;
        } else {
            System.out.println("Seat " + seatNumber + " is already booked");
            return false;
        }
    }
    
    public boolean cancelSeat(int seatNumber) {
        if (seatNumber < 0 || seatNumber >= CAPACITY) {
            throw new IllegalArgumentException("Invalid seat number");
        }
        
        if (this.seats[seatNumber].isBooked()) {
            this.seats[seatNumber].markAsNotBooked();
            this.bookedSeats--;
            return true;
        } else {
            System.out.println("Seat " + seatNumber + " is not booked");
            return false;
        }
    }
    
    
    public void displayAvailableSeats() {
        System.out.print("Available seats: ");
        for (int i = 0; i < CAPACITY; i++) {
            if (!this.seats[i].isBooked()) {
                System.out.print(i + " ");
            }
        }
        System.out.println();
    }
    
    
    @Override
    public void displayCoachDetails() {
        System.out.println("Sleeper Coach - Number of Seats: " + (CAPACITY - this.bookedSeats));
    }
}
