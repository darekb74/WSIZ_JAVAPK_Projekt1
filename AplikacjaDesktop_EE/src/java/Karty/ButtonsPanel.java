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
import javax.swing.Box;
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
    private JComboBox table;
    private JComboBox operator = new JComboBox(new String[] {"LIKE","=", "<>",
        "<=", ">=", "NOT LIKE", "BETWEEN", "NOT BETWEEN", "IS NULL",
        "IS NOT NULL", "IS EMPTY", "IS NOT EMPTY"});
    private JButton search = new JButton("Szukaj");
    private JButton refresh = new JButton("Odśwież");
    private JButton update = new JButton("Aktualizuj");
    private JButton delete = new JButton("Usuń");
    

    private Container rodzic;

    public ButtonsPanel(boolean s, boolean r, boolean u, boolean d, Container rodzic) {
        this.rodzic = rodzic;
        table = new JComboBox(((Karta) rodzic).getData(0,null));
        this.add(table);
        this.add(operator);
        this.add(keyword);
        this.add(search);
        this.add(Box.createRigidArea(new Dimension(10,1)));
        this.add(refresh);
        this.add(update);
        this.add(delete);
        keyword.setPreferredSize(new Dimension(100,27));
        keyword.setVisible(s);
        operator.setVisible(s);
        table.setVisible(s);
        search.setVisible(s);
        refresh.setEnabled(r);
        update.setEnabled(u);
        delete.setEnabled(d);
        

        ActionListener ucL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((Karta) rodzic).takeAction(1,null);
            }
        };
        update.addActionListener(ucL);

        ActionListener dcL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((Karta) rodzic).takeAction(2,null);
            }
        };
        delete.addActionListener(dcL);

        ActionListener rcL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((Karta) rodzic).takeAction(3,null);
            }
        };
        refresh.addActionListener(rcL);
        
        ActionListener scL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((Karta) rodzic).takeAction(4, new Object[] {table.getSelectedIndex(), operator.getSelectedItem(), keyword.getText()});
                if(Def.DEBUG)
                    System.out.println("[SEARCH] tabela:" + table.getSelectedItem() + ", operator:" + operator.getSelectedItem()
                            + ", keyword:" + keyword.getText());
            }
        };
        search.addActionListener(scL);
    }
}
