/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Obiekty;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Darek Xperia
 */
public class TabelaDanych extends JTable {

    public TabelaDanych(DefaultTableModel model, Container rodzic) {
        super(model);
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

    public void pokazDane() {
        if (getSelectedColumn() < 0) return;
        System.out.println("Zaznaczona komórka zawiera wartość: '"
                + getValueAt(getSelectedRow(), getSelectedColumn()) + "'.");
        for (int i=0; i < this.getColumnCount(); i++) {
            System.out.println("[" + i + "]" + this.getColumnName(i)+": '"
                + getValueAt(getSelectedRow(), i) + "'.");
        }
    }

}
