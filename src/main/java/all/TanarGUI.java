package all;

import javax.swing.*;

public class TanarGUI {
    private JPanel panel;

    public void TanarPage() {
        JFrame frame = new JFrame("GUI");
        frame.setContentPane(new TanarGUI().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
