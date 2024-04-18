import java.io.Serializable;

public class TatkalTicketBooker implements Serializable{
	private static final long serialVersionUID = 1L;
	static private String password = "TATKAL";
	
	public String getPassword(){
		return password;
	}
}
