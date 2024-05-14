import com.github.javafaker.Faker;
import com.google.gson.Gson;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final Faker faker = new Faker();
        Scanner input = new Scanner(System.in);
        Gson gson = new Gson();
        boolean loop;
        PersonsList list = new PersonsList();
        do {
            loop = true;
            System.out.println("What do you want to do?");
            System.out.println("1. Add");
            System.out.println("2. Search");
            System.out.println("3. Exit");
            String asnwer = input.nextLine();
            switch (asnwer) {
                case "1" -> {
                    Integer newID = list.generateNewId();
                    Person pe = new Person(newID, faker.name().firstName() + " " + faker.name().lastName(), faker.number().numberBetween(0, 100), faker.job().title());
                    System.out.println(gson.toJson(pe) + " Has been added");
                    list.add(pe);
                    list.writePersonInfoToFile(gson.toJson(pe));

                }
                case "2" -> {
                    System.out.println("Please input the name of the person you wish to look for or write 'all' if you wish to show everyone");
                    String nameSearch = input.nextLine();
                    if (nameSearch.equalsIgnoreCase("all")) {
                        System.out.println(gson.toJson(list.getPersons()));
                    } else {
                        Optional<Person> searchResult = list.findByName(nameSearch);
                        if (searchResult.isPresent()) {
                            System.out.println(searchResult.get());
                        } else {
                            System.out.println("Person with name " + nameSearch + " not found.");
                        }
                    }
                }
                case "3" -> loop = false;
                default -> System.out.println("Invalid option");
            }
            System.out.println("Do you want to continue? (y/n)");
            var continueResponse = input.nextLine();
            loop = switch (continueResponse) {
                case "y" -> true;
                case "n" -> false;
                default -> loop;
            };

        } while (loop);
    }
}
