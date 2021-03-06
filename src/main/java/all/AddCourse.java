/**
 * Osztály egy új óra adatbázishoz való hozzáadására
 */

package all;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AddCourse extends JDialog {
    private JPanel contentPane;
    private JButton createButton;
    private JButton buttonCancel;
    private JTextField karTextField;
    private JTextField szkiTextField;
    private JTextField tiTextField;
    private JTextField tantargyTextField;
    private JTextField tanszekTextField;
    private JTextField eloladoTextField;
    private JTextField csoportTextField;
    private JTextField foTextField;
    private JTextField kezdesTextField;
    private JTextField hosszTextField;
    private JTextField teremTextField;
    private JTextField napTextField;
    private JTextField tipusTextField;
    private JTextField felevTextField;

    public AddCourse() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(createButton);

        CourseDatabaseManager courseDatabaseManager = new CourseDatabaseManager();

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);



        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Course temp = new Course();
                    temp.setFelev(Integer.parseInt(felevTextField.getText()));
                    temp.setKar(karTextField.getText());
                    temp.setSzki(szkiTextField.getText());
                    temp.setTi(tiTextField.getText());
                    temp.setTantargy(tantargyTextField.getText());
                    temp.setTanszek(tanszekTextField.getText());
                    temp.setEloado(eloladoTextField.getText());
                    temp.setCsoport(csoportTextField.getText());
                    temp.setFo(Integer.parseInt(foTextField.getText()));
                    temp.setNap(napTextField.getText());
                    temp.setKezdes(Integer.parseInt(kezdesTextField.getText()));
                    temp.setHossz(Integer.parseInt(hosszTextField.getText()));
                    temp.setTipus(tipusTextField.getText());
                    temp.setTerem(teremTextField.getText());

                    courseDatabaseManager.create(temp);
                    JOptionPane.showMessageDialog(null, "Hozzaadva", "Hozzá lett adva", JOptionPane.INFORMATION_MESSAGE);

                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(null, "Fos", "Szar", JOptionPane.ERROR_MESSAGE);

                }
            }
        });

        setMinimumSize(new Dimension(900,400));
        pack();
        setVisible(true);
    }

    private void onCancel() {
        dispose();
    }

}
