import java.io.*;
import java.util.HashMap;

public class HashMapFileHandler {
    // Function to read a HashMap from a text file
    public static HashMap<Integer, String> readHashMapFromFile(String filePath) {
        HashMap<Integer, String> map = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    int key = Integer.parseInt(parts[0].trim());
                    String value = parts[1].trim();
                    map.put(key, value);
                }
            }
            System.out.println("HashMap loaded from file: " + filePath);
        } catch (IOException e) {
            System.out.println("Error reading HashMap from file: " + e.getMessage());
        }
        return map;
    }

    // Function to write a HashMap to a text file
    public static void writeHashMapToFile(HashMap<Integer, String> map, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Integer key : map.keySet()) {
                String line = key + ": " + map.get(key);
                writer.write(line);
                writer.newLine();
            }
            System.out.println("HashMap saved to file: " + filePath);
        } catch (IOException e) {
            System.out.println("Error writing HashMap to file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        // Example usage:
        // Create a new HashMap
        HashMap<Integer, String> myMap = new HashMap<>();
        myMap.put(1, "value1");
        myMap.put(2, "value2");
        myMap.put(3, "value3");

        // File path to save or load the HashMap
        String filePath = "hashmap_data.txt";

        // Write the HashMap to a text file
        writeHashMapToFile(myMap, filePath);

        // Read the HashMap from the text file
        HashMap<Integer, String> loadedMap = readHashMapFromFile(filePath);

        // Print the loaded HashMap
        System.out.println("Loaded HashMap: " + loadedMap);
    }
}
