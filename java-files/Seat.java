import java.io.Serializable;

class Seat implements Serializable {
	private static final long serialVersionUID = 1L;
	private boolean bookingStatus;

    public boolean isBooked() {
        return this.bookingStatus;
    }

    public void markAsBooked() {
        this.bookingStatus = true;
    }

    public void markAsNotBooked() {
        this.bookingStatus = false;
    }
}
