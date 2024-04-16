import java.io.*;
import java.util.*;

public class MapFileHandler {

    // Method to write a Map<String, Object> to a file
    public static void writeMapToFile(Map<String, Object> map, String filename) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filename))) {
            outputStream.writeObject(map);
            System.out.println("Map has been successfully written to file.");
        } catch (IOException e) {
            System.err.println("Error writing map to file: " + e.getMessage());
        }
    }

    // Method to read a Map<String, Object> from a file
    public static Map<String, Object> readMapFromFile(String filename) {
        Map<String, Object> map = new HashMap<>();
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filename))) {
            map = (Map<String, Object>) inputStream.readObject();
            System.out.println("Map has been successfully read from file.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error reading map from file: " + e.getMessage());
        }
        return map;
    }

    // Example usage:
    public static void main(String[] args) {
        // Example map
        Map<String, Object> exampleMap = new HashMap<>();
        exampleMap.put("key1", "value1");
        exampleMap.put("key2", 123);
        exampleMap.put("key3", new SerializableObject());

        // Writing map to file
        writeMapToFile(exampleMap, "data.ser");

        // Reading map from file
        Map<String, Object> resultMap = readMapFromFile("data.ser");

        // Displaying contents of read map
        System.out.println("Contents of read map:");
        for (Map.Entry<String, Object> entry : resultMap.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}

// Example SerializableObject class
class SerializableObject implements Serializable {
    // SerializableObject class definition
}
