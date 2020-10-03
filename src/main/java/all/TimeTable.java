package all;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class TimeTable extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTable tableWeek;
    private JPanel timetablePane;

    public TimeTable(List<Course> list) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        String[] columnNames = {"Hour", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};

        DefaultTableModel model = new DefaultTableModel();

        tableWeek.setAutoCreateRowSorter(true);
        tableWeek.setFillsViewportHeight(true);
        tableWeek.setPreferredScrollableViewportSize(new Dimension(550, 200));

        for (int i = 0; i < columnNames.length; i++) {
            model.addColumn(columnNames[i]);
        }

        model.addRow(new Object[]{"08:00"});
        model.addRow(new Object[]{"09:00"});
        model.addRow(new Object[]{"10:00"});
        model.addRow(new Object[]{"11:00"});
        model.addRow(new Object[]{"12:00"});
        model.addRow(new Object[]{"13:00"});
        model.addRow(new Object[]{"14:00"});
        model.addRow(new Object[]{"15:00"});
        model.addRow(new Object[]{"16:00"});
        model.addRow(new Object[]{"17:00"});


        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 5; j++) {
                if(list.get(i).getTanszek().equals("IAK")) {
                    if(list.get(i).getNap().equals("H"))
                        model.insertRow(1, new Object[]{null, list.get(i).getTantargy()});
                    if(list.get(i).getNap().equals("K"))
                        model.insertRow(2,new Object[]{null, null, list.get(i).getTantargy()});
                    if(list.get(i).getNap().equals("Sz"))
                        model.insertRow(3, new Object[]{null, null, null, list.get(i).getTantargy()});
                    if(list.get(i).getNap().equals("Cs"))
                        model.insertRow(4, new Object[]{null, null, null, null,list.get(i).getTantargy()});
                    if(list.get(i).getNap().equals("P"))
                        model.insertRow(5, new Object[]{null, null, null, null, null, list.get(i).getTantargy()});
                } else
                    continue;
            }

        }

        tableWeek.setModel(model);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
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
    }

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}
