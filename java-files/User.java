import java.io.Serializable;

public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
    private int age;
    private String phoneNumber;
    private String emailId;
    private String password;
    private boolean isLoggedIn;

    public User(String name, int age, String phoneNumber, String emailId, String password, boolean isLoggedIn) {
        this.name = name;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.emailId = emailId;
        this.isLoggedIn = isLoggedIn;
        this.password = password;
        // this.userID = userID;
    }

    public User() {
        this.isLoggedIn = false;
    }

    public boolean getIsUserLoggedIn() {
        return this.isLoggedIn;
    }

    public void setUserLoggedIn(boolean status) {
        this.isLoggedIn = status;
    }

    // Getters for all fields
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmailId() {
        return emailId;
    }

    public String getPassword() {
        // TODO Auto-generated method stub}
        return this.password;
    }


}
