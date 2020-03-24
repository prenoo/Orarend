package all;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class GUI {
    private JPanel panel;
    private JButton beolvasButton;
    private JButton kilépésButton;
    private JComboBox comboBoxKeres;
    private JTextField textFieldKeres;
    private JButton keresButton;
    private JButton listazButton;
    private File inputFile;
    private Course course;
    private String filePath;
    private List<Course> subjects;

    private CourseController courseController;


    public GUI() {

        beolvasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FileDialog fileDialog = new FileDialog(new Frame(), " ", FileDialog.LOAD);
                fileDialog.setFile("*.doc");
                fileDialog.setVisible(true);

                if (fileDialog.getFile() != null) {
                    inputFile = new File(fileDialog.getDirectory(), fileDialog.getFile());
                    filePath = inputFile.toString();
                    List<String> orarendDoc = null;
                    try {
                        orarendDoc = DocReader.docToArrayList(DocReader.readDoc(filePath));
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    DocReader.removeNotNeededLines(orarendDoc);
                    subjects = DocReader.stringListToCourseList(orarendDoc);
                    JOptionPane.showMessageDialog(null, "Fájl beolvasva!\n " + filePath, "Beolvasás", JOptionPane.PLAIN_MESSAGE);
                }
            }
        });
        kilépésButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        listazButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                courseController.printList(subjects);
            }
        });
        keresButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                courseController.searchList(subjects, Course::getKar, textFieldKeres);
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("GUI");
        frame.setContentPane(new GUI().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
