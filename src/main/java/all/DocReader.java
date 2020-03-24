package all;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DocReader {

    /**
     * Method that reads a .doc file
     * TODO: replace hardcoded filepath
     * @return WordExtractor object that should be converted to ArrayList
     */
    public static WordExtractor readDoc(String fileName) {
        String filePath = fileName;
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
     * @throws IOException
     */
    public static List<String> docToArrayList(WordExtractor extractor) throws IOException {

        List<String> doksi = new ArrayList<>();
        BufferedReader reader;
        reader = new BufferedReader(new StringReader(extractor.getText()));

        String line = reader.readLine();
        while (line != null) {
            line = reader.readLine();
            doksi.add(line);
        }
        reader.close();

        return doksi;
    }

    /**
     * Removes lines that don't contain any useful data
     * @param inputList list read in from the .doc file
     */
    public static void removeNotNeededLines(List<String> inputList) {
        for (int i = 0; i < inputList.size() - 1; i++) {
            if (inputList.get(i).contains(".lap") ||
                    inputList.get(i).contains("Ti.Tantárgy") ||
                    inputList.get(i).contains("tanszék órái") ||
                    inputList.get(i).contains("─────") ||
                    inputList.get(i).isEmpty() ||
                    inputList.get(i).length() < 90
            ) {
                inputList.remove(i);
                i--;
            } else
                continue;
        }
    }

    /**
     * Creates a list containing Course objects from the input list which contains strings
     * @param list a String list with only the useful data, where one line is one subject
     * @return a Course list
     */
    public static List<Course> stringListToCourseList(List<String> list) {
        int felev;
        List<Course> courses = new ArrayList<Course>();

        for (String asd : list) {
            try {


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

            } catch (NullPointerException e) {
                continue;
            }
        }

        return courses;
    }

}
