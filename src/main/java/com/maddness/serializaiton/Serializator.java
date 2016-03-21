package com.maddness.serializaiton;

import java.io.*;

/**
 * Created by maddness on 19/03/2016.
 */
public class Serializator {

    public static void main(String[] args) {

        String path = "src/main/resources/person.bin";

        dumpObject(new Person(), path);

        Person newBorn = loadObjectFrom(path);
        System.out.println(newBorn.dnaCode);

    }

    private static Person loadObjectFrom(String path) {
        try (ObjectInputStream is = new ObjectInputStream(new FileInputStream(path))) {
            return (Person) is.readObject();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Object not found");
        }
    }

    private static void dumpObject(Person person, String path) {
        try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(path))) {

            Person user = person;
            os.writeObject(user);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Organism {
    // non-serializable constructor will run!!!
    public int dnaCode = 123114123;

    public Organism() {
        this.dnaCode = 9999999;
    }
}

class Person extends Organism implements Serializable {
    private int age = 10;
    private String name = "Paul";
    private transient String nickname = "redhead";

    private void writeObject(ObjectOutputStream os) throws IOException {
        os.defaultWriteObject();
        os.writeObject(nickname);
    }

    private void readObject(ObjectInputStream is) throws IOException, ClassNotFoundException {
        is.defaultReadObject();
        nickname = (String) is.readObject();
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}



