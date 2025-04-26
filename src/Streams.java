import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Streams {

    public static void main(String[] args) {

        ArrayList<String> fruits = new ArrayList<>(Arrays.asList("Apple", "Banana", "Cherry", "ASDDD", "America", "as"));

        ArrayList<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4));

        ArrayList<Student> names = new ArrayList<>();
        names.add(new Student("sam", 43, 100));
        names.add(new Student("ram", 23, 32));
        names.add(new Student("sham", 12, 4343));


        //filter
        List<String> newList = fruits.stream().filter(a -> a.toLowerCase().startsWith("a")).collect(Collectors.toList());
        newList.stream().forEach(a -> System.out.println(a));


        //sorted
        List<Student> sorted_student = names.stream().sorted((a, b) -> {
            int diff = a.age - b.age;
            if (diff > 0) {
                return a.marks - b.marks;
            } else
                return a.age - b.age;
        }).collect(Collectors.toList());

        //map
        List<Student> mappedStudents = fruits.stream().map(a -> {
            return new Student(a, 12);
        }).collect(Collectors.toList());
        mappedStudents.stream().forEach(a -> System.out.println(a));


////
        List<Integer> numbersdd = new ArrayList<>();
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
        numbers.add(4);

        long count = names.stream().mapToInt(a -> a.marks).sum();
        System.out.println("sum of martks : " + count);
        ////
        //collect to map
        Map<String, Integer> test = names.stream().collect(Collectors.toMap(student -> student.name, student -> student.marks));
        Map<String, Student> test3 = names.stream().collect(Collectors.toMap(student -> student.name, student -> student));

        System.out.println("Student Marks Map:");
        test.forEach((key, value) -> System.out.println(key + ": " + value));

        System.out.println("Student Name Map:");
        test3.forEach((key, value) -> System.out.println(key + ": " + value.name + ", " + value.age + ", " + value.marks));


    }
}
