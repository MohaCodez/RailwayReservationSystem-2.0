import java.io.Serializable;

public interface Coach extends Serializable {
    void displayCoachDetails();

    boolean bookSeat(int seatNumber);

    boolean cancelSeat(int seatNumber);

    void displayAvailableSeats();
}