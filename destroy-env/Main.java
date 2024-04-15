import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        // Create a HashMap
        HashMap<Integer, String> myMap = new HashMap<>();
        myMap.put(1, "value1");
        myMap.put(2, "value2");
        myMap.put(3, null);

        // File path to save or load the HashMap
        String filePath = "hashmap_data.txt";

        // Write the HashMap to a text file
        HashMapFileHandler.writeHashMapToFile(myMap, filePath);

        // Read the HashMap from the text file
        HashMap<Integer, String> loadedMap = HashMapFileHandler.readHashMapFromFile(filePath);

        // Print the loaded HashMap
        System.out.println("Loaded HashMap: " + loadedMap);
    }
}
