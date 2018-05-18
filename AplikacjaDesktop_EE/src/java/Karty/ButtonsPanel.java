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
    private JButton add = new JButton("Dodaj");

    private Container rodzic;

    public ButtonsPanel(Byte vMask, Byte eMask, Container rodzic) {
        // vMask - maska widzialności
        // eMask - maska dstępności (enabled)
        // s - search, r - refresh, u - update, d - delete, a - add
        // 0b000adurs
        
        this.rodzic = rodzic;
        column = new JComboBox(((Karta) rodzic).getData(0, null));
        //search
        column.setVisible((vMask & (byte)0b1) != 0);
        column.setEnabled((eMask & (byte)0b1) != 0);
        operator.setVisible((vMask & (byte)0b1) != 0);
        operator.setEnabled((eMask & (byte)0b1) != 0);
        keyword.setVisible((vMask & (byte)0b1) != 0);
        keyword.setEnabled((eMask & (byte)0b1) != 0);
        search.setVisible((vMask & (byte)0b1) != 0);
        search.setEnabled((eMask & (byte)0b1) != 0);
        // refresh
        refresh.setVisible((vMask & (byte)0b10) != 0);
        refresh.setEnabled((eMask & (byte)0b10) != 0);
        // update
        update.setVisible((vMask & (byte)0b100) != 0);
        update.setEnabled((eMask & (byte)0b100) != 0);
        // delete
        delete.setVisible((vMask & (byte)0b1000) != 0);
        delete.setEnabled((eMask & (byte)0b1000) != 0);
        // add
        add.setVisible((vMask & (byte)0b10000) != 0);
        add.setEnabled((eMask & (byte)0b10000) != 0);
        
        dodajElementy();
    }
    
    public void dodajElementy() {
        
        this.add(column);
        this.add(operator);
        this.add(keyword);
        this.add(search);
        this.add(Box.createRigidArea(new Dimension(10, 1)));
        this.add(refresh);
        this.add(update);
        this.add(delete);
        keyword.setPreferredSize(new Dimension(100, 27));
        this.add(add);
        
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
