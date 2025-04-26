import java.util.Comparator;

public class Agecompare implements Comparator<Student> {


    @Override
    public int compare(Student o1, Student o2) {
        return o1.age - o2.age;
        //  return Integer.compare(o1.age, o1.age); // Compare by age

    }
}
