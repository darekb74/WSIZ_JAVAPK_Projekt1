/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Obiekty;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Darek Xperia
 */
public class TabelaDanych extends JTable {

    private DefaultTableModel model;

    public TabelaDanych(DefaultTableModel model, DefaultTableModel modelb, Container rodzic) {
        super(model);
        this.model = modelb;
        setPreferredScrollableViewportSize(new Dimension(500, 70));
        setFillsViewportHeight(true);
        if (Def.DEBUG) {
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseReleased(MouseEvent e) { // mouseReleased zapewnia prawidłowe działanie, mouseClicked czasami nie odpala :/
                    pokazDane();
                }
            });
        }
        JScrollPane scrollPane = new JScrollPane(this);
        rodzic.add(scrollPane, BorderLayout.CENTER);

    }

    @Override
    public Component prepareRenderer(TableCellRenderer renderer, int row, int col) {
        Component comp = super.prepareRenderer(renderer, row, col);
        Object value = getModel().getValueAt(row, col);
        if (!value.equals(model.getValueAt(row, col))) {
            comp.setBackground(Color.YELLOW);
            //} else if (sprawdzPoprawnosc(value)) {
            //  comp.setBackground(Color.red);
        } else {
            comp.setBackground(Color.white);
        }

        return comp;
    }

    public void pokazDane() {
        if (getSelectedColumn() < 0) {
            return;
        }
        System.out.println("Zaznaczona komórka zawiera wartość: '"
                + getValueAt(getSelectedRow(), getSelectedColumn()) + "'.");
        if (!getValueAt(getSelectedRow(), getSelectedColumn()).equals(getValueAt(getSelectedRow(), getSelectedColumn()))) {

        }
        for (int i = 0; i < this.getColumnCount(); i++) {
            System.out.println("[" + i + "]" + this.getColumnName(i) + ": '"
                    + getValueAt(getSelectedRow(), i)
                    + "'('" + model.getValueAt(getSelectedRow(), i)
                    + "').");
        }
    }

}
