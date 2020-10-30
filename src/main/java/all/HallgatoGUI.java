package all;

import javax.swing.*;

public class HallgatoGUI {
    private JPanel panel;

    public void HallgatoPage() {
        JFrame frame = new JFrame("GUI");
        frame.setContentPane(new HallgatoGUI().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
