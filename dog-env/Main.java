import java.io.Serializable;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        // Create a HashMap
        HashMap<String, Dog> dogDataHashMap = new HashMap<>();
        Dog jhonny = new Dog(12, "doggo", "jhonny", "black");
        Dog bonny = new Dog(10, "boggo", "bonny", "white");
        Dog tom = new Dog(9, "toggo", "tom", "pink");

        dogDataHashMap.put(jhonny.getName(), jhonny);
        dogDataHashMap.put(bonny.getName(), bonny);
        dogDataHashMap.put(tom.getName(), tom);

        System.out.println(dogDataHashMap);

        String filePath = "dogData.ser";

        DogHashMapIO.writeHashMapToFile(filePath, dogDataHashMap);

        HashMap<String, Dog> incomingHashMap = new HashMap<>();
        HashMap<String, Dog> incomingHashMap2 = new HashMap<>();
        incomingHashMap = DogHashMapIO.readHashMapFromFile(filePath);

        System.out.println(incomingHashMap);

        incomingHashMap.get("jhonny").setAge(23);

        DogHashMapIO.writeHashMapToFile(filePath, incomingHashMap);

        incomingHashMap2 = DogHashMapIO.readHashMapFromFile(filePath);

        int age2 = incomingHashMap2.get("jhonny").getAge();

        if (age2 == 69) {
            System.out.println("yay2");
        }

    }
}

class Dog implements Serializable {
    private int age;
    private String breed;
    private String name;
    private String color;

    Dog(int age, String breed, String name, String color) {
        this.age = age;
        this.breed = breed;
        this.color = color;
        this.name = name;
    }

    public void setAge(int inputAge) {
        // TODO Auto-generated method stub
        this.age = inputAge;
    }

    public int getAge() {
        // TODO Auto-generated method stub
        return this.age;
    }

    public String getName() {
        // TODO Auto-generated method stub
        return this.name;

    }

    public void display() {
        System.out.println("The name " + this.name + "the breed " + this.breed + " is " + this.color + " and "
                + this.age + "years old.");
    }

}
