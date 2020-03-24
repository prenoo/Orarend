package all;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MainApp {



    public static void main(String[] args) throws IOException {

        List<String> orarendDoc = DocReader.docToArrayList(DocReader.readDoc("C:\\Users\\Renato\\IdeaProjects\\Komplex\\src\\resources\\geik_orarend.doc"));
        DocReader.removeNotNeededLines(orarendDoc);
        List<Course> subjects = DocReader.stringListToCourseList(orarendDoc);
        //List<Course> result = subjects.stream().filter(item -> item.getEloado().equals("CserÁk")).collect(Collectors.toList());
        //Course.search(subjects, 6);


        /*
        Class c = subjects.get(0).getClass();
        Method[] fields = ();
        for (int i = 0; i < fields.length; i++) {
            System.out.println("The field is: " + fields[i]);
        }

         */

    }
}