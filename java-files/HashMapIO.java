import java.io.*;
import java.util.HashMap;

public class HashMapIO implements Serializable{
	private static final long serialVersionUID = 1L;
    // Method to write HashMap<String, Train> to a .ser file
    public static void writeHashMapToFile(String filePath, HashMap<String, Train> hashMap) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filePath))) {
            outputStream.writeObject(hashMap);
            System.out.println("HashMap has been successfully written to file: " + filePath);
        } catch (IOException e) {
            System.err.println("Error writing HashMap to file: " + e.getMessage());
        }
    }

    // Method to read HashMap<String, Train> from a .ser file
    public static HashMap<String, Train> readHashMapFromFile(String filePath) {
        HashMap<String, Train> hashMap = null;
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filePath))) {
            Object obj = inputStream.readObject();
            if (obj instanceof HashMap) {
                hashMap = (HashMap<String, Train>) obj;
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
