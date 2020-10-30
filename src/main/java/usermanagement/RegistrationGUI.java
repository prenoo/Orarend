/**
 * Osztály a regisztráció GUI-hoz-
 */

package usermanagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import static usermanagement.UserController.*;

public class RegistrationGUI extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField nevTextField;
    private JTextField emailTextField;
    private JTextField usernameTextField;
    private JTextField passwordTextField;

    public RegistrationGUI() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        /**
         * A gombra kattintva a registerToDatabase() hívodik meg, ami a megadott adatok alapján új felhasználót ír az adatbázisba
         */
        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                registerToDatabase(nevTextField.getText(), emailTextField.getText(), usernameTextField.getText(), passwordTextField.getText());
                JOptionPane.showMessageDialog(null, "Sikeres regisztráció", "Regisztrálás", JOptionPane.PLAIN_MESSAGE);
            }
        });

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

        setMinimumSize(new Dimension(500,300));
        pack();
    }


    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}
