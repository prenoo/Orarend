package all;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class MainApp {

    public static void main(String[] args) throws IOException {

        List<String> orarendDoc = DocReader.docToArrayList(DocReader.readDoc("geik_orarend"));
        DocReader.removeNotNeededLines(orarendDoc);
        List<Course> subjects = DocReader.stringListToCourseList(orarendDoc);
        List<Course> result = subjects.stream().filter(item -> item.getEloado().equals("CserÁk")).collect(Collectors.toList());
        Course.search(subjects, 6);

    }
}
