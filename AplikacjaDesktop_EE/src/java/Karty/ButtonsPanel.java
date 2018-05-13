/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Karty;

import DTO.UserDTO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Darek Xperia
 */
public class ButtonsPanel extends JPanel {

    private JButton update = new JButton("Aktualizuj");
    private JButton delete = new JButton("Usuń");

    public ButtonsPanel(boolean a, boolean u) {
        if (a) {
            this.add(update);
        }
        if (u) {
            this.add(delete);
        }

        ActionListener ucL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        };
        update.addActionListener(ucL);
        
        ActionListener dcL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        };
        delete.addActionListener(dcL);
    }
}
