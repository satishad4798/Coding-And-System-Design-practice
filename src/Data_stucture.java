import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Data_stucture {


    public static void main(String[] args) {

        ArrayList<String> array = new ArrayList<>();

        ArrayList<Integer> array3 = new ArrayList<>(Arrays.asList(1, 2, 3, 4));

        ArrayList<Integer> test = new ArrayList<>(List.of(1, 2, 3, 4, 5));

        ArrayList<Integer> list = new ArrayList<>(Collections.nCopies(5, 7));
        int[] numbers = {5, 3, 9, 1, 7};

        // Sort the array
        Collections.sort(test);
        System.out.println(Collections.binarySearch(test, 4)); // Output: [1, 3, 5, 7, 9]

        ArrayList<Student> names = new ArrayList<>();
        names.add(new Student("sam", 43));
        names.add(new Student("ram", 23));
        names.add(new Student("sham", 12));


        //names.sort(new Agecompare());
        names.sort((s1, s2) -> s2.age - s1.age);
        Collections.sort(names, (s1, s2) -> s2.age - s1.age);
        names.forEach(a -> System.out.println(a.name + a.age));


    }


}
