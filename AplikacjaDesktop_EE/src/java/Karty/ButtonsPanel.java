/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Karty;

import Menadzery.Def;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Darek Xperia
 * @classdesc Panel przycisków Odśwież, Aktualizuj, Usuń
 */
public class ButtonsPanel extends JPanel {

    private JTextField keyword = new JTextField();
    private JComboBox column;
    private JComboBox operator = new JComboBox(new String[]{"=", "<>",
        "<=", ">=", "BETWEEN", "NOT BETWEEN"});
    private JButton search = new JButton("Szukaj");
    private JButton refresh = new JButton("Odśwież");
    private JButton update = new JButton("Aktualizuj");
    private JButton delete = new JButton("Usuń");

    private Container rodzic;

    public ButtonsPanel(boolean s, boolean r, boolean u, boolean d, Container rodzic) {
        this.rodzic = rodzic;
        column = new JComboBox(((Karta) rodzic).getData(0, null));
        this.add(column);
        this.add(operator);
        this.add(keyword);
        this.add(search);
        this.add(Box.createRigidArea(new Dimension(10, 1)));
        this.add(refresh);
        this.add(update);
        this.add(delete);
        keyword.setPreferredSize(new Dimension(100, 27));
        keyword.setVisible(s);
        operator.setVisible(s);
        column.setVisible(s);
        search.setVisible(s);
        refresh.setEnabled(r);
        update.setEnabled(u);
        delete.setEnabled(d);

        ItemListener oIL = new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                DefaultComboBoxModel dcm;
                switch ( (int)((Karta) rodzic).getData(2, null)[column.getSelectedIndex()]) {
                    case 0: // Long
                    case 1: // Integer
                    case 2: // Byte
                    case 6: // Short
                        dcm = new DefaultComboBoxModel(new String[]{"=", "<>",
                            "<=", ">=", "BETWEEN", "NOT BETWEEN"});
                        operator.removeAllItems();
                        operator.setModel(dcm);
                        break;
                    case 4: //Boolean
                        dcm = new DefaultComboBoxModel(new String[]{"=", "<>"});
                        operator.removeAllItems();
                        operator.setModel(dcm);
                        break;
                    case 5: //Date
                        dcm = new DefaultComboBoxModel(new String[]{"=", "<>",
                            "<=", ">=", "BETWEEN", "NOT BETWEEN"});
                        operator.removeAllItems();
                        operator.setModel(dcm);
                        break;
                    case 3: // Strring
                    default:
                        dcm = new DefaultComboBoxModel(new String[]{"LIKE",
                            "NOT LIKE", "=", "<>", "IS NULL", "IS NOT NULL"});
                        operator.removeAllItems();
                        operator.setModel(dcm);
                        break;
                }
            }

        };
        column.addItemListener(oIL);

        ActionListener ucL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((Karta) rodzic).takeAction(1, null);
            }
        };
        update.addActionListener(ucL);

        ActionListener dcL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((Karta) rodzic).takeAction(2, null);
            }
        };
        delete.addActionListener(dcL);

        ActionListener rcL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((Karta) rodzic).takeAction(3, null);
            }
        };
        refresh.addActionListener(rcL);

        ActionListener scL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((Karta) rodzic).takeAction(4, new Object[]{column.getSelectedIndex(), operator.getSelectedItem(), keyword.getText()});
                if (Def.DEBUG) {
                    System.out.println("[SEARCH] tabela:" + column.getSelectedItem() + ", operator:" + operator.getSelectedItem()
                            + ", keyword:" + keyword.getText());
                }
            }
        };
        search.addActionListener(scL);
    }
}
