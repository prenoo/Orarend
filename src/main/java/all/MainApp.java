package all;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
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
        for (int i = 0; i < dok.size() -1; i++) {
            if (dok.get(i).contains(".lap")  ||
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
         * Split the elements of the ArrayList into columns
         */

        List<String[]> targyak = new ArrayList<String[]>();
        for(String asd : subjects) {
            String[] egyTargy =  {
                    asd.substring(2,4).trim(), //0. oszlop
                    asd.substring(4,8).trim(), //1. oszlop
                    asd.substring(8,10).trim(), //2. oszlop
                    asd.substring(10,13).trim(), //3. oszlop
                    asd.substring(16,52).trim(), //4. oszlop
                    asd.substring(52,55).trim(), //5. oszlop
                    asd.substring(56,62).trim(), //6. oszlop
                    asd.substring(63,68).trim().replaceAll("\\s+", ""), //7. oszlop
                    asd.substring(70,73).trim(), //8. oszlop
                    asd.substring(76,78).trim(), //9. oszlop
                    asd.substring(79,81).trim(), //10. oszlop
                    asd.substring(83,84).trim(), //11. oszlop
                    asd.substring(85,87).trim(), //12. oszlop
                    asd.substring(87,93).trim() //13. oszlop
            };
            targyak.add(egyTargy);
        }

        for (String[] s : targyak) {
            for (int i = 0; i < s.length; i++) {
                switch (i) {
                    case 0:
                        System.out.println("Félév: " + s[i]);
                        break;
                    case 1:
                        System.out.println("Kar: " + s[i]);
                        break;
                    case 2:
                        System.out.println("?: " + s[i]);
                        break;
                    case 3:
                        System.out.println("?: " + s[i]);
                        break;
                    case 4:
                        System.out.println("Tárgynév: " + s[i]);
                        break;
                    case 5:
                        System.out.println("Tanszék: " + s[i]);
                        break;
                    case 6:
                        System.out.println("Tanár: " + s[i]);
                        break;
                    case 7:
                        System.out.println("Csoport: " + s[i]);
                        break;
                    case 8:
                        System.out.println("Fő: " + s[i]);
                        break;
                    case 9:
                        System.out.println("Nap: " + s[i]);
                        break;
                    case 10:
                        System.out.println("Kezdés: " + s[i]);
                        break;
                    case 11:
                        System.out.println("Hossz: " + s[i]);
                        break;
                    case 12:
                        System.out.println("Típus: " + s[i]);
                        break;
                    case 13:
                        System.out.println("Terem: " + s[i]);
                        break;

                }
            }
            System.out.println();
            System.out.println();
        }


    }


    /**
     * Method that reads a .doc file
     * TODO: replace hardcoded filepath
     * @return WordExtractor object that should be converted to ArrayList
     */
    private static WordExtractor readDoc() {
        String filePath = "C:\\Users\\Renato\\IdeaProjects\\Komplex\\src\\main\\java\\all\\geik_orarend.doc";
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
