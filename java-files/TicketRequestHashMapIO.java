import java.io.*;
import java.util.HashMap;

public class TicketRequestHashMapIO {

    // Method to write HashMap<String, Ticket> to a .ser file
    public static void writeHashMapToFile(String filePath, HashMap<String, TicketRequest> hashMap) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filePath))) {
            outputStream.writeObject(hashMap);
            System.out.println("HashMap has been successfully written to file: " + filePath);
        } catch (IOException e) {
            System.err.println("Error writing HashMap to file: " + e.getMessage());
        }
    }

    // Method to read HashMap<String, Ticket> from a .ser file
    public static HashMap<String, TicketRequest> readHashMapFromFile(String filePath) {
        HashMap<String, TicketRequest> hashMap = null;
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filePath))) {
            Object obj = inputStream.readObject();
            if (obj instanceof HashMap<?, ?>) {
                HashMap<?, ?> rawMap = (HashMap<?, ?>) obj;
                // Check if the HashMap contains the correct types
                if (rawMap.isEmpty() || rawMap.entrySet().iterator().next().getKey() instanceof String &&
                        rawMap.entrySet().iterator().next().getValue() instanceof TicketRequest) {
                    hashMap = (HashMap<String, TicketRequest>) rawMap;
                    System.out.println("HashMap has been successfully read from file: " + filePath);
                } else {
                    System.err.println("Error: File does not contain a HashMap<String, TicketRequest>");
                }
            } else {
                System.err.println("Error: File does not contain a HashMap");
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error reading HashMap from file: " + e.getMessage());
        }
        return hashMap;
    }


}
