import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Main {


    record Person(String firstName, String lastName) {
        @Override
        public String toString() {
            return firstName + " " + lastName;
        }
    }

    public static void main(String[] args) {

        List<Person> people = new ArrayList<>(Arrays.asList(
                new Person("james", "joice"),
                new Person("charlie", "chaplin"),
                new Person("bud", "spenser")));

        // using anonymous class

        var comparatorLastName = new Comparator<Person>() {

            @Override
            public int compare(Person o1, Person o2) {
                return o1.lastName().compareTo(o2.lastName());
            }

        };
        people.sort((o1, o2) -> o1.lastName().compareTo(o2.lastName()));
        System.out.println(people);


        interface EnhancedComperator<T> extends Comparator<T> {
            int secondLevel(T o1, T o2);
        }
        var comperatorMixed = new EnhancedComperator<Person>() {

            @Override
            public int compare(Person o1, Person o2) {
                int result = o1.firstName().compareTo(o2.firstName());
                return (result == 0 ? secondLevel(o1, o2) : result);
            }

            @Override
            public int secondLevel(Person o1, Person o2) {
                return o1.firstName().compareTo(o2.firstName());
            }
        };

        people.sort(comperatorMixed);
        System.out.println(people);

    }
}
