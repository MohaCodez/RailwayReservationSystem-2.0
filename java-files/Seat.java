
class Seat {
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

