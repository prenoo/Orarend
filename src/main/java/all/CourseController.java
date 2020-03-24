package all;

import javax.swing.*;
import java.lang.reflect.Field;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CourseController {


    public static void printList(List<Course> list) {
        for (Course c : list) {
            System.out.println(c);
        }
    }


    public static void searchList(List<Course> courses, Function<Course, String> transform, JTextField jTextField) {
        String searchValue = jTextField.getText();
        List<Course> result = courses.stream().filter(item -> transform.apply(item).equals(searchValue)).collect(Collectors.toList());
        printList(result);
        System.out.println("Találatok száma (/ összes:) " + result.size() + " (/ " + courses.size() + ")");
    }
}
