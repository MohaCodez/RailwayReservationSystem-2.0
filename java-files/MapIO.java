import java.io.*;
import java.util.*;

public class MapIO {

    // Read function
    public static Map<String, Train> readMapFromFile(String filePath) {
        Map<String, Train> map = new HashMap<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            map = (Map<String, Train>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return map;
    }

    // Write function
    public static void writeMapToFile(Map<String, Train> map, String filePath) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(map);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}