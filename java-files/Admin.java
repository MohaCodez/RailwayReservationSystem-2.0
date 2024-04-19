import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class Admin implements Serializable {
    private HashMap<String, User> registeredUsers;
    File userFile = new File(Main.userDataFilePath);

    public Admin() {
        // System.out.println("Constructor invoked!");
        if (userFile.length() == 0) {
            this.registeredUsers = new HashMap<String, User>();
        } else {
            this.registeredUsers = UserHashMapIO.readHashMapFromFile(Main.userDataFilePath);
            // System.out.println("Hashmap Read in Constructor!");
        }

    }

    public void signUserUp(String enteredName, int currentAge, String phoneNum, String enteredEmail,
            String enteredPassword) {
        File file = new File(Main.userDataFilePath);
        if (file.length() != 0) {
            registeredUsers = UserHashMapIO.readHashMapFromFile(Main.userDataFilePath);
            System.out.println(registeredUsers);
            // System.out.println(registeredUsers);

            int flag = 0;
            Set<String> keys = registeredUsers.keySet();
            for (String key : keys) {
                // System.out.println("hello there!" + key);
                if (key.equals(phoneNum)) {
                    // System.out.println(phoneNum + " " + key);
                    System.out.println(
                            "An account with this phone number already exists! Kindly use a different phone number to create an account!");
                    flag = 1;
                }
            }
            if (flag == 0) {
                User newUser = new User(enteredName, currentAge, phoneNum, enteredEmail, enteredPassword, true);
                registeredUsers.put(newUser.getPhoneNumber(), newUser);
                UserHashMapIO.writeHashMapToFile(Main.userDataFilePath, registeredUsers);

            } else {
                System.out.println("Exiting Program...");
                System.exit(0);
            }
        } else {
            User newUser = new User(enteredName, currentAge, phoneNum, enteredEmail, enteredPassword, true);
            registeredUsers.put(newUser.getPhoneNumber(), newUser);
            UserHashMapIO.writeHashMapToFile(Main.userDataFilePath, registeredUsers);
            System.out.println("Sign-Up successful!");
            // System.out.println(registeredUsers);
        }
        // } else {
        // }

        // TODO Auto-generated method stub

    }

    public void loginExistingUser() {
        // TODO Auto-generated method stub
    }

}
