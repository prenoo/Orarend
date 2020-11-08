/**
 * Osztály az összes tantárgy listázására
 */

package all;


import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class CourseTable extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTable table;
    private JPanel tablePanel;
    private JScrollPane jscrollpane;


    public CourseTable(List<Course> list) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);


        DefaultTableModel model = new DefaultTableModel();
        table.setAutoCreateRowSorter(true);
        table.setFillsViewportHeight(true);
        table.setPreferredScrollableViewportSize(new Dimension(550, 200));

        //Oszlopnevek megadása
        String[] columnNames = {"ID", "Félév", "Kar", "Szki", "Ti", "Tárgynév", "Tanszék", "Előadó", "Csoport", "Fő", "Kezdés", "Hossz", "Terem", "Nap", "Típus"};
        for (int i = 0; i < columnNames.length; i++) {
            model.addColumn(columnNames[i]);
        }

        //tábla feltöltése az adatbázisból
        for (int i = 0; i < list.size(); i++) {
            model.addRow(new Object[]{list.get(i).getId(), list.get(i).getFelev(), list.get(i).getKar(), list.get(i).getSzki(), list.get(i).getTi(),
                    list.get(i).getTantargy(), list.get(i).getTanszek(), list.get(i).getEloado(), list.get(i).getCsoport(),
                    list.get(i).getFo(), list.get(i).getKezdes(), list.get(i).getHossz(), list.get(i).getTerem(), list.get(i).getNap(), list.get(i).getTipus()
            });
        }
        table.setModel(model);

        /**
         * Adatmódosítás, a kilistázott tárgyak közül megvizsgálja melyik sor és melyik oszlop van kiválasztva,
         * majd az adatbázisba módosítja a
         */
        model.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                if (e.getType() == TableModelEvent.UPDATE) {
                    int column = e.getColumn();

                    DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();

                    String data = String.valueOf(defaultTableModel.getValueAt(table.getSelectedRow(), table.getSelectedColumn())); //a cellába beírt új érték
                    String stringId = defaultTableModel.getValueAt(table.getSelectedRow(), 0).toString(); //a kiválasztott sor 1. oszlopa az id, ami alapján megkeresi az adatbázisban a módosítandó rekordot
                    long id = Long.parseLong(stringId); //a Course objektum id adattagja long típusú, konverzió szükséges

                    CourseDatabaseManager.update(id, data, column);
                }

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


        //oszlopszélesség beállítása
        TableColumn tableColumn = null;
        for (int i = 0; i < table.getColumnCount(); i++) {
            tableColumn = table.getColumnModel().getColumn(i);
            if(i == 5)
                tableColumn.setMinWidth(250);
            else if(i == 6 || i == 7)
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
