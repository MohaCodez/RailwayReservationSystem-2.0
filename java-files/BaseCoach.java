
abstract class BaseCoach implements Coach {
	private static final long serialVersionUID = 1L;
	protected final int CAPACITY;
    protected int bookedSeats;
    protected Seat[] seats;

    public BaseCoach(int capacity) {
        this.CAPACITY = capacity;
        this.seats = new Seat[CAPACITY];
        for (int i = 0; i < CAPACITY; i++) {
            this.seats[i] = new Seat(); // Initialize each seat
        }
        this.bookedSeats = 0;
    }

    @Override
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

    @Override
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

    @Override
    public void displayAvailableSeats() {
        System.out.print("Available seats: ");
        for (int i = 0; i < CAPACITY; i++) {
            if (!this.seats[i].isBooked()) {
                System.out.print(i + " ");
            }
        }
        System.out.println();
    }
    
    public boolean isSeatBooked(int seatNumber) {
        if (seatNumber < 0 || seatNumber >= CAPACITY) {
            throw new IllegalArgumentException("Invalid seat number");
        }
        return this.seats[seatNumber].isBooked();
    }
}

