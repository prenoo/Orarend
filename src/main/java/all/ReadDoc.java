package all;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReadDoc {

    public static void main(String[] args) throws IOException {

        String filePath = "C:\\Users\\Renato\\Downloads\\9781789131895_Code\\JavaProjects_Code\\Chapter03\\Komplex\\src\\feladat\\elozetes_orarend.doc";
        FileInputStream fileInputStream;

        List<String> doksi = new ArrayList<String>();
        BufferedReader reader;
            try {
                fileInputStream = new FileInputStream(new File(filePath));
                HWPFDocument doc = new HWPFDocument(fileInputStream);
                WordExtractor extractor = new WordExtractor(doc);

                reader = new BufferedReader(new StringReader(extractor.getText()));

                String line = reader.readLine();
                while (line != null) {
                    //System.out.println(line);
                    line = reader.readLine();
                    doksi.add(line);
                }
                reader.close();
                //System.out.println(extractor.getText());
            } catch (IOException e) {
                e.printStackTrace();
            }

        for (String asd : doksi) {
            System.out.println(asd);
        }

    }
}
