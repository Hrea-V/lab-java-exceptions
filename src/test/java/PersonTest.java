import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PersonTest {

    @Test
    void setAge() {
        Person person = new Person(1, "John Doe", 25, "Engineer");

        Throwable exception = Assertions.assertThrows(IllegalArgumentException.class, () -> person.setAge(-1));
        Assertions.assertEquals("Age cannot be less than 0", exception.getMessage());
    }
}