
public interface Coach {
    void displayCoachDetails();
    boolean bookSeat(int seatNumber);
    boolean cancelSeat(int seatNumber);
    void displayAvailableSeats();
}