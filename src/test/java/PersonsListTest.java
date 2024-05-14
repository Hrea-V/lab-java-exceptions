import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import java.util.Optional;

@Nested
class PersonsListTest {

    @Test
    void findByNameShouldReturnCorrectPerson() {
        // Given: you have a PersonsList with some Persons
        PersonsList personsList = new PersonsList();
        Person johnDoe = new Person(1, "John Doe", 30, "Engineer");
        Person janeDoe = new Person(2, "Jane Doe", 32, "Doctor");
        personsList.add(johnDoe);
        personsList.add(janeDoe);

        // When: you try to find a person by their properly formatted name
        Optional<Person> foundPerson = personsList.findByName("John Doe");

        // Then: the method should return the correct Person
        Assertions.assertTrue(foundPerson.isPresent(), "A person should be found");
        Assertions.assertEquals(johnDoe, foundPerson.get(), "The found person should be John Doe");
    }

    @Test
    void findByNameShouldThrowExceptionWhenNameNotProperlyFormatted() {
        // Given: you have a PersonsList
        PersonsList personsList = new PersonsList();

        // When and Then: you try to find by improper name format, it should throw an exception
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> personsList.findByName("John Doe 1"));
    }

    @Test
    void clonePersonShouldCreateNewPersonWithDifferentId() {
        // Given: you have a PersonsList
        PersonsList personsList = new PersonsList();

        // And: a Person is created
        Person johnDoe = new Person(1, "John Doe", 30, "Engineer");
        personsList.add(johnDoe);

        // When: you clone that person
        Person cloneOfJohnDoe = personsList.clonePerson(johnDoe);

        // Then: the clone should have the same details but a different ID
        Assertions.assertNotEquals(johnDoe.getId(), cloneOfJohnDoe.getId(), "The clone should have a different ID");
        Assertions.assertEquals(johnDoe.getName(), cloneOfJohnDoe.getName(), "The clone should have the same name");
        Assertions.assertEquals(johnDoe.getAge(), cloneOfJohnDoe.getAge(), "The clone should have the same age");
        Assertions.assertEquals(johnDoe.getOccupation(), cloneOfJohnDoe.getOccupation(), "The clone should have the same occupation");
    }

}