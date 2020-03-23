package all;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MainApp {

    public static void main(String[] args) throws IOException {

        /**
         * Read the doc file into an ArrayList
         * One line is one element
         */
        List<String> dok = new ArrayList<String>();
        docToArrayList(readDoc(), dok);


        /*
         * Removes not useful elements
         */
        List<String> subjects = new ArrayList<String>();
        for (int i = 0; i < dok.size() - 1; i++) {
            if (dok.get(i).contains(".lap") ||
                    dok.get(i).contains("Ti.Tantárgy") ||
                    dok.get(i).contains("tanszék órái") ||
                    dok.get(i).contains("─────") ||
                    dok.get(i).isEmpty() ||
                    dok.get(i).length() < 90
            ) {
                continue;
            } else
                subjects.add(dok.get(i));
        }

        /**
         * Split the lines of the ArrayList into substring,
         * and adding them to the courses
         */
        List<Course> courses = new ArrayList<Course>();
        int felev;
        for (String asd : subjects) {
            String[] egyTargy = {
                    asd.substring(2, 4).trim(), //0. oszlop - félév
                    asd.substring(4, 8).trim(), //1. oszlop - kar
                    asd.substring(8, 10).trim(), //2. oszlop - szki
                    asd.substring(10, 13).trim(), //3. oszlop - ti
                    asd.substring(16, 52).trim(), //4. oszlop - tantárgy
                    asd.substring(52, 55).trim(), //5. oszlop - tanszék
                    asd.substring(56, 62).trim(), //6. oszlop - tanár
                    asd.substring(63, 68).trim().replaceAll("\\s+", ""), //7. oszlop - csoport
                    asd.substring(70, 73).trim(), //8. oszlop - fő
                    asd.substring(76, 78).trim(), //9. oszlop - nap
                    asd.substring(79, 81).trim(), //10. oszlop - kezdés
                    asd.substring(83, 84).trim(), //11. oszlop - hossz
                    asd.substring(85, 87).trim(), //12. oszlop - tipus
                    asd.substring(87, 93).trim() //13. oszlop - terem
            };

            try {
                felev = Integer.parseInt(egyTargy[0]);
            } catch (NumberFormatException e) {
                felev = 0;
            }

            courses.add(new Course(
                    felev,
                    egyTargy[1],
                    egyTargy[2],
                    egyTargy[3],
                    egyTargy[4],
                    egyTargy[5],
                    egyTargy[6],
                    egyTargy[7],
                    Integer.parseInt(egyTargy[8]),
                    egyTargy[9],
                    Integer.parseInt(egyTargy[10]),
                    Integer.parseInt(egyTargy[11]),
                    egyTargy[12],
                    egyTargy[13]
            ));
        }

        for (Course c : courses) {
            System.out.println(c);
        }
    }

    /**
     * Method that reads a .doc file
     * TODO: replace hardcoded filepath
     * @return WordExtractor object that should be converted to ArrayList
     */
    private static WordExtractor readDoc() {
        String filePath = "C:\\Users\\Renato\\IdeaProjects\\Komplex\\src\\resources\\geik_orarend.doc";
        FileInputStream fileInputStream;

        WordExtractor extractor = null;
        try {
            fileInputStream = new FileInputStream(new File(filePath));
            HWPFDocument doc = new HWPFDocument(fileInputStream);
            extractor = new WordExtractor(doc);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return extractor;
    }


    /**
     * Takes the .doc file as a WordExtractor object and adds it to an ArrayList.
     * One line in the doc is one element in the ArrayList
     * @param extractor The input doc file
     * @param doksi The ArrayList used to store lines
     * @throws IOException
     */
    private static void docToArrayList(WordExtractor extractor, List<String> doksi) throws IOException {

        BufferedReader reader;
        reader = new BufferedReader(new StringReader(extractor.getText()));

        String line = reader.readLine();
        while (line != null) {
            line = reader.readLine();
            doksi.add(line);
        }
        reader.close();
    }
}