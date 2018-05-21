/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Obiekty;


import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Darek Xperia
 */
public class PanelStron extends JPanel {

    private JLabel[] elementy = new JLabel[9];

    public PanelStron() {
        this.setOpaque(false);
        MouseAdapter ma = obslugaMyszy();
        for (int i = 0; i < elementy.length; i++) {
            elementy[i] = new JLabel();
            this.add(elementy[i]);
            elementy[i].addMouseListener(ma);
        }
        ukryjWszystkieElementy();
    }

    private void ukryjWszystkieElementy() {
        for (JLabel l : elementy) {
            l.setVisible(false);
        }
    }

    public void wygenerujPanel(String[] nazwyElementow) {
        int i = 0;
        ukryjWszystkieElementy();
        for (String s : nazwyElementow) {
            elementy[i].setText(s);
            elementy[i++].setVisible(true);
        }
        validate();
    }

    private MouseAdapter obslugaMyszy() {
        return new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (Def.DEBUG) {
                    System.out.println("" + (Component) e.getSource() + "X:" + e.getX() + " Y:" + e.getY());
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                ((Component) e.getSource()).setCursor(new Cursor(Cursor.HAND_CURSOR));
                ((Component) e.getSource()).setForeground(Color.BLUE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                ((Component) e.getSource()).setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                ((Component) e.getSource()).setForeground(Color.BLACK);
            }
        };
    }

}
