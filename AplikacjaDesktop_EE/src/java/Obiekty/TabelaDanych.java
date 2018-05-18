/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Obiekty;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import static java.lang.Math.pow;
import java.util.Date;
import java.util.EventObject;
import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Darek Xperia
 */
public class TabelaDanych extends JTable {

    private DefaultTableModel model;
    private Byte maskaEdycji;

    public TabelaDanych(DefaultTableModel model, DefaultTableModel modelb, Byte maskaEdycji, Container rodzic) {
        super(model);
        this.model = modelb;
        this.maskaEdycji = maskaEdycji;
        if (Def.DEBUG) {
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseReleased(MouseEvent e) { // mouseReleased zapewnia prawidłowe działanie, mouseClicked czasami nie odpala :/
                    pokazDane();
                }
            });
        }
        this.setDefaultRenderer(Date.class, new CustomDateRenderer());
        this.setDefaultEditor(Date.class, new CustomDateEditor());
    }

    @Override
    public Component prepareRenderer(TableCellRenderer renderer, int row, int col) {
        Component comp = super.prepareRenderer(renderer, row, col);
        Object value = getModel().getValueAt(row, col);
        if (!value.equals(model.getValueAt(row, col))) {
            comp.setBackground(Color.YELLOW);
        } else if (!isCellEditable(row, col)) {
            comp.setBackground(new Color(235, 235, 235));
            //} else if (sprawdzPoprawnosc(value)) {
            //  comp.setBackground(Color.red);
        } else {
            comp.setBackground(Color.white);
        }

        return comp;
    }

    public void pokazDane() { //DEBUG=1
        if (getSelectedColumn() < 0) {
            return;
        }
        System.out.println("Zaznaczona komórka zawiera wartość: '"
                + getValueAt(getSelectedRow(), getSelectedColumn()) + "' type:"
                + getColumnClass(getSelectedColumn()).toString()
                + ".");
        if (!isCellEditable(getSelectedRow(), getSelectedColumn())) {
            System.out.println("Edycja tej komórki jest niemożliwa.");
        }
        for (int i = 0; i < this.getColumnCount(); i++) {
            System.out.println("[" + i + "]" + this.getColumnName(i) + ": '"
                    + this.getValueAt(getSelectedRow(), i).toString()
                    + "'('" + model.getValueAt(getSelectedRow(), i).toString()
                    + "').");
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return this.getValueAt(0, columnIndex).getClass();
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        Double d = pow(2, column);
        return (d.intValue() & maskaEdycji) == 0;
    }

    // Edycja Date
    public class CustomDateEditor extends AbstractCellEditor implements TableCellEditor {

        Date cDate;
        JTextField text;

        public CustomDateEditor() {
            text = new JTextField();
        }

        @Override
        public boolean isCellEditable(EventObject anEvent) {
            if (anEvent instanceof MouseEvent) {
                return ((MouseEvent) anEvent).getClickCount() >= 2;
            }
            return true;
        }

        @Override
        public Object getCellEditorValue() {
            return Utils.Utils.zamienNaDate(text.getText());
        }

        @Override
        public boolean stopCellEditing() {
            if (!Utils.Utils.sprawdzPoprawnoscDanych(5, text.getText())) {
                text.setBorder(new LineBorder(Color.red));
                return false;
            } else {
                text.setBorder(new LineBorder(Color.black));
            }
            return super.stopCellEditing();
        }

        @Override
        public Component getTableCellEditorComponent(JTable table,
                Object value, boolean isSelected, int row, int column) {
            cDate = (Date) value;
            text.setText(Utils.Utils.foramtujDate(cDate));
            return text;
        }
    }

    // renderowanie Date
    public class CustomDateRenderer extends DefaultTableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {

            super.getTableCellRendererComponent(table, value, isSelected,
                    hasFocus, row, column);
            this.setText(Utils.Utils.foramtujDate((Date) value));
            return this;
        }
    }
}
