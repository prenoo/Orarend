package all;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class Table extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTable table;
    private JPanel tablePanel;
    private JScrollPane jscrollpane;


    public Table(List<Course> list) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        String[] columnNames = {"Félév", "Kar", "Szki", "Ti", "Tárgynév", "Tanszék", "Előadó", "Csoport", "Fő", "Kezdés", "Hossz", "Terem", "Nap", "Típus"};

        DefaultTableModel model = new DefaultTableModel();

        table.setAutoCreateRowSorter(true);
        table.setFillsViewportHeight(true);
        table.setPreferredScrollableViewportSize(new Dimension(550, 200));

        for (int i = 0; i < columnNames.length; i++) {
            model.addColumn(columnNames[i]);
        }

        for (int i = 0; i < list.size(); i++) {
            model.addRow(new Object[]{list.get(i).getFelev(), list.get(i).getKar(), list.get(i).getSzki(), list.get(i).getTi(),
                    list.get(i).getTantargy(), list.get(i).getTanszek(), list.get(i).getEloado(), list.get(i).getCsoport(),
                    list.get(i).getFo(), list.get(i).getKezdes(), list.get(i).getHossz(), list.get(i).getTerem(), list.get(i).getNap(), list.get(i).getTipus()
            });
        }
        table.setModel(model);


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

        TableColumn tableColumn = null;
        for (int i = 0; i < table.getColumnCount(); i++) {
            tableColumn = table.getColumnModel().getColumn(i);
            if(i == 4)
                tableColumn.setMinWidth(250);
            else if(i == 5 || i == 6)
                tableColumn.setMinWidth(80);
            else
                tableColumn.setMinWidth(40);
        }

        setMinimumSize(new Dimension(900,400));
        pack();
    }

    private void onCancel() {
        dispose();
    }
}
