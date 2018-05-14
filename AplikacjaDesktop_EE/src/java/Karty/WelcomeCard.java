/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Karty;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Darek Xperia
 * @classdesc
 * Karta startowa
 */
public class WelcomeCard extends JPanel {
    
    private JLabel l1 = new JLabel("MAGAZYN SERWISU KOMPUTEROWEGO");
    private JLabel l2 = new JLabel("Programowanie Komponentowe JAVA - Praca semestralna");
    private JLabel l3 = new JLabel("Autorzy: Rafał B., Darek B.");
    private JLabel empty = new JLabel(" ");
    private JPanel cont = new JPanel();
    
    public WelcomeCard() {
        this.setLayout(new GridBagLayout());
        cont.setLayout(new BoxLayout(cont, BoxLayout.Y_AXIS));
        
        l1.setFont(new Font(l1.getFont().getName(), Font.BOLD, 20));
        l1.setAlignmentX(Component.CENTER_ALIGNMENT);
        l2.setAlignmentX(Component.CENTER_ALIGNMENT);
        l3.setAlignmentX(Component.CENTER_ALIGNMENT);
        cont.add(l1);
        cont.add(Box.createRigidArea(new Dimension(1,20)));
        cont.add(l2);
        cont.add(l3);
        
        
        add(cont);
    }
}
