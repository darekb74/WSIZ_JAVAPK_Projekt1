/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Karty;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Darek Xperia
 * @classdesc Panel przycisków Odśwież, Aktualizuj, Usuń
 */
public class ButtonsPanel extends JPanel {

    private JButton refresh = new JButton("Odśwież");
    private JButton update = new JButton("Aktualizuj");
    private JButton delete = new JButton("Usuń");

    private Container rodzic;

    public ButtonsPanel(boolean r, boolean u, boolean d, Container rodzic) {
        this.rodzic = rodzic;
        this.add(refresh);
        this.add(update);
        this.add(delete);
        refresh.setEnabled(r);
        update.setEnabled(u);
        delete.setEnabled(d);

        ActionListener ucL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((Karta) rodzic).takeAction(1);
            }
        };
        update.addActionListener(ucL);

        ActionListener dcL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((Karta) rodzic).takeAction(2);
            }
        };
        delete.addActionListener(dcL);

        ActionListener rcL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((Karta) rodzic).takeAction(3);
            }
        };
        refresh.addActionListener(rcL);
    }
}
