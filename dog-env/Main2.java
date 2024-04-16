import java.util.HashMap;

public class Main2 {

    public static void main(String[] args) {
        HashMap<String, Dog> incomingMap = new HashMap<String, Dog>();
        incomingMap = DogHashMapIO.readHashMapFromFile("dogData.ser");

        System.out.println(incomingMap.get("jhonny").getAge());

    }

}