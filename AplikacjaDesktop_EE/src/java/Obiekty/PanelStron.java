/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Obiekty;

import Karty.Karta;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.util.Map;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Darek Xperia
 */
public class PanelStron extends JPanel {

    private Container rodzic;
    private JLabel[] elementy = new JLabel[11];
    private Object wylaczony;

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

    public void setRodzic(Container rodzic) {
        this.rodzic = rodzic;
    }

    public void ukryjWszystkieElementy() {
        for (JLabel l : elementy) {
            l.setVisible(false);
        }
    }

    public void wygenerujPanel(String[] nazwyElementow, int page) {
        int i = 0;
        ukryjWszystkieElementy();
        for (String s : nazwyElementow) {
            elementy[i].setText(s);
            elementy[i].setVisible(true);
            try {
                Font font = elementy[i].getFont();
                Map attributes = font.getAttributes();
                attributes.put(TextAttribute.UNDERLINE, -1);
                elementy[i].setFont(font.deriveFont(attributes));
                if (Integer.parseInt(s) == page) {
                    attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
                    elementy[i].setFont(font.deriveFont(attributes));
                    wylaczony = elementy[i];
                }
            } catch (NumberFormatException e) {
            }
            i++;
        }
        validate();
    }

    private MouseAdapter obslugaMyszy() {
        return new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (!((Component) e.getSource()).equals(wylaczony)) {
                    ((Karta) rodzic).takeAction(6, new Object [] {((JLabel) e.getSource()).getText()});
                    if (Def.DEBUG) {
                        System.out.println("" + (Component) e.getSource() + "X:" + e.getX() + " Y:" + e.getY());
                    }
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if (!((Component) e.getSource()).equals(wylaczony)) {
                    ((Component) e.getSource()).setCursor(new Cursor(Cursor.HAND_CURSOR));
                    ((Component) e.getSource()).setForeground(Color.BLUE);
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                ((Component) e.getSource()).setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                ((Component) e.getSource()).setForeground(Color.BLACK);
            }
        };
    }

}
