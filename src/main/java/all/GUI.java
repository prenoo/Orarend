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
    private JButton kilepesButton;
    private JComboBox comboBoxKeres;
    private JTextField textFieldKeres;
    private JButton keresButton;
    private JButton listazButton;
    private JButton generalButton;
    private JButton makedb;
    private JButton irdki;
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
                        orarendDoc = CourseController.docToArrayList(CourseController.readDoc(filePath));
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    CourseController.removeNotNeededLines(orarendDoc);
                    subjects = CourseController.stringListToCourseList(orarendDoc);
                    JOptionPane.showMessageDialog(null, "Fájl beolvasva!\n " + filePath, "Beolvasás", JOptionPane.PLAIN_MESSAGE);
                }
            }
        });
        kilepesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        listazButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    JDialog dialog = new Table(subjects);
                    dialog.setVisible(true);
                } catch (NullPointerException e1) {
                    JOptionPane.showMessageDialog(null, "Nincs beolvasva adat!", "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        });
        keresButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Course> list = null;
                try {
                    switch (comboBoxKeres.getSelectedIndex()) {
                        case 0:
                            list = courseController.searchList(subjects, Course::getFelev, textFieldKeres);
                            break;
                        case 1:
                            list = courseController.searchList(subjects, Course::getKar, textFieldKeres);
                            break;
                        case 2:
                            list = courseController.searchList(subjects, Course::getSzki, textFieldKeres);
                            break;
                        case 3:
                            list = courseController.searchList(subjects, Course::getTi, textFieldKeres);
                            break;
                        case 4:
                            list = courseController.searchList(subjects, Course::getTantargy, textFieldKeres);
                            break;
                        case 5:
                            list = courseController.searchList(subjects, Course::getTanszek, textFieldKeres);
                            break;
                        case 6:
                            list = courseController.searchList(subjects, Course::getEloado, textFieldKeres);
                            break;
                        case 7:
                            list = courseController.searchList(subjects, Course::getCsoport, textFieldKeres);
                            break;
                        case 8:
                            list = courseController.searchList(subjects, Course::getFo, textFieldKeres);
                            break;
                        case 9:
                            list = courseController.searchList(subjects, Course::getKezdes, textFieldKeres);
                            break;
                        case 10:
                            list = courseController.searchList(subjects, Course::getHossz, textFieldKeres);
                            break;
                        case 11:
                            list = courseController.searchList(subjects, Course::getTerem, textFieldKeres);
                            break;
                        case 12:
                            list = courseController.searchList(subjects, Course::getNap, textFieldKeres);
                            break;
                        case 13:
                            list = courseController.searchList(subjects, Course::getTipus, textFieldKeres);
                            break;
                    }

                    JDialog dialog = new Table(list);
                    dialog.setVisible(true);
                } catch (NullPointerException e1) {
                    JOptionPane.showMessageDialog(null, "Nincs beolvasva fájl!", "Beolvasási hiba", JOptionPane.ERROR_MESSAGE);
                }
        }
        });
        generalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    JDialog dialog = new TimeTable(subjects);
                    dialog.setVisible(true);
                } catch (NullPointerException e1) {
                    JOptionPane.showMessageDialog(null, "Nincs beolvasva adat!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        makedb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CourseManager courseManager = new CourseManager();
                courseManager.setup();
                for (Course c : subjects) {
                    courseManager.create(c);
                }
            }
        });
        irdki.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CourseManager courseManager = new CourseManager();
                courseManager.setup();
                courseManager.read(444);
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