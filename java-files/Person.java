import java.io.Serializable;

public abstract class Person implements Serializable {
    private String name;
    private int age;
    private String phoneNumber;
    private String emailId;

    public Person(String name, int age, String phoneNumber, String emailId) {
        this.name = name;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.emailId = emailId;
    }

    // Abstract method to calculate discount
    public abstract double calculateDiscount(double ticketPrice);

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
}