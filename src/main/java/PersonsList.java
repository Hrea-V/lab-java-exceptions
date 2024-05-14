import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class PersonsList {
    String person_info = "person_info.txt";
    File file = new File(person_info);
    ArrayList<Person> persons;
    Random rand = new Random();

    public PersonsList() {
        persons = new ArrayList<>();
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(ArrayList<Person> persons) {
        this.persons = persons;
    }

    public void add(Person person) {
        persons.add(person);
    }

    public Optional<Person> findByName(String name) {
        if (!name.matches("^[a-zA-Z]+ [a-zA-Z]+$")) {
            throw new IllegalArgumentException("Name must be properly formatted as 'firstName lastName'");
        }
        return persons.stream()
                .filter(p -> p.getName().equals(name))
                .findFirst();
    }

    public Person clonePerson(Person original) {
        return new Person(generateNewId(), original.getName(), original.getAge(), original.getOccupation());
    }

    Integer generateNewId() {
        return (rand.nextInt(1000));
    }

    public void writePersonInfoToFile(String person) {
        try (FileWriter fw = new FileWriter(file, true)) {
            fw.write(person + "\n");
        } catch (IOException e) {
            // Log or print exception details
            System.err.println("An IOException occurred: " + e.getMessage());
        }
    }
}