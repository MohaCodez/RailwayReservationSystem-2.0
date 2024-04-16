import java.io.*;
import java.util.HashMap;

public class DogHashMapIO {

    // Method to write HashMap<String, Dog> to a .ser file
    public static void writeHashMapToFile(String filePath, HashMap<String, Dog> hashMap) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filePath))) {
            outputStream.writeObject(hashMap);
            System.out.println("HashMap has been successfully written to file: " + filePath);
        } catch (IOException e) {
            System.err.println("Error writing HashMap to file: " + e.getMessage());
        }
    }

    // Method to read HashMap<String, Dog> from a .ser file
    public static HashMap<String, Dog> readHashMapFromFile(String filePath) {
        HashMap<String, Dog> hashMap = null;
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filePath))) {
            Object obj = inputStream.readObject();
            if (obj instanceof HashMap) {
                hashMap = (HashMap<String, Dog>) obj;
                System.out.println("HashMap has been successfully read from file: " + filePath);
            } else {
                System.err.println("Error: File does not contain a HashMap");
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error reading HashMap from file: " + e.getMessage());
        }
        return hashMap;
    }

}
