/**
 * Az admin felülethez tartozó GUI
 */

package all;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.List;


public class AdminGUI extends JFrame{
    private JPanel panel;
    private JButton beolvasButton;
    private JButton kilepesButton;
    private JComboBox comboBoxKeres;
    private JTextField textFieldKeres;
    private JButton keresButton;
    private JButton listazButton;
    private JButton ujOraButton;
    private JButton torlesButton;
    private JButton generalButton;
    private JTextField textFieldGeneral;
    private JComboBox comboBox1;
    private File inputFile;
    private String filePath;
    private List<Course> subjects;
    private CourseController courseController;

    private List<Course> courseList;


    public AdminGUI() {
        CourseDatabaseManager courseDatabaseManager = new CourseDatabaseManager();
        courseList = courseDatabaseManager.loadAllData(); //az adatbázisból az összes tárgy beolvasása

        /**
         * FileDialog az előzetes órarend fájl beolvasására.
         * A fájl beolvasása után automatikusan létrehozza az adatbázist is.
         */
        beolvasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FileDialog fileDialog = new FileDialog(new Frame(), " ", FileDialog.LOAD);
                fileDialog.setFile("*.doc");
                fileDialog.setVisible(true);

                if (fileDialog.getFile() != null) {
                    inputFile = new File(fileDialog.getDirectory(), fileDialog.getFile());
                    filePath = inputFile.toString();
                    List<String> orarendDoc = null; //
                    try {
                        orarendDoc = CourseController.docToArrayList(CourseController.readDoc(filePath));
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    CourseController.removeUnnecessaryLines(orarendDoc);
                    subjects = CourseController.stringListToCourseList(orarendDoc);
                    JOptionPane.showMessageDialog(null, "Fájl beolvasva!\n " + filePath, "Beolvasás", JOptionPane.PLAIN_MESSAGE);

                    CourseController.createDatabase(subjects);
                }
            }
        });


        /**
         * Összes óra megjelenítése egy táblázatban
         */
        listazButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    courseList = courseDatabaseManager.loadAllData();
                    JDialog dialog = new TableListAll(courseList);
                    dialog.setVisible(true);
                } catch (NullPointerException e1) {
                    JOptionPane.showMessageDialog(null, "Nincs beolvasva adat!", "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        });

        /**
         * A kiválasztott oszlop alapján keresés a tárgyakban
         */
        keresButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Course> list = null;
                try {
                    switch (comboBoxKeres.getSelectedIndex()) {
                        case 0:
                            list = courseController.searchList(courseList, Course::getFelev, textFieldKeres);
                            break;
                        case 1:
                            list = courseController.searchList(courseList, Course::getKar, textFieldKeres);
                            break;
                        case 2:
                            list = courseController.searchList(courseList, Course::getSzki, textFieldKeres);
                            break;
                        case 3:
                            list = courseController.searchList(courseList, Course::getTi, textFieldKeres);
                            break;
                        case 4:
                            list = courseController.searchList(courseList, Course::getTantargy, textFieldKeres);
                            break;
                        case 5:
                            list = courseController.searchList(courseList, Course::getTanszek, textFieldKeres);
                            break;
                        case 6:
                            list = courseController.searchList(courseList, Course::getEloado, textFieldKeres);
                            break;
                        case 7:
                            list = courseController.searchList(courseList, Course::getCsoport, textFieldKeres);
                            break;
                        case 8:
                            list = courseController.searchList(courseList, Course::getFo, textFieldKeres);
                            break;
                        case 9:
                            list = courseController.searchList(courseList, Course::getKezdes, textFieldKeres);
                            break;
                        case 10:
                            list = courseController.searchList(courseList, Course::getHossz, textFieldKeres);
                            break;
                        case 11:
                            list = courseController.searchList(courseList, Course::getTerem, textFieldKeres);
                            break;
                        case 12:
                            list = courseController.searchList(courseList, Course::getNap, textFieldKeres);
                            break;
                        case 13:
                            list = courseController.searchList(courseList, Course::getTipus, textFieldKeres);
                            break;
                    }

                    JDialog dialog = new TableListAll(list);
                    dialog.setVisible(true);
                } catch (NullPointerException e1) {
                    JOptionPane.showMessageDialog(null, "Nincs beolvasva fájl!", "Beolvasási hiba", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        /**
         * Új óra hozzáadása az adatbázisba.
         * A felugró JDialogba lehet megadni a szükséges adatokat.
         */
        ujOraButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    JDialog dialog = new AddCourse();
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(null, "Hiba történt", "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        });

        /**
         * Program bezárása
         */
        kilepesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    /**
     * GUI létrehozása
     */
    public void AdminPage() {
        JFrame frame = new JFrame("GUI");
        frame.setContentPane(new AdminGUI().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}