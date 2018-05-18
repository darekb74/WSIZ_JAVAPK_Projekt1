/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Karty;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import layout.SpringUtilities;

/**
 *
 * @author Darek Xperia
 */
public class NewUserD extends JPanel implements Karta {

    private JPanel leftP = new JPanel();
    private JPanel rightP = new JPanel();
    private JPanel przyciski = new JPanel();
    // lewey panel
    private JTextField userName = new JTextField(20);
    private JPasswordField userPassword = new JPasswordField();
    private JPasswordField userPasswordR = new JPasswordField();
    private JTextField eMail = new JTextField();
    private JTextField rmask = new JTextField();

    // prawy panel
    private JTextField name = new JTextField(20);
    private JTextField address = new JTextField();
    private JTextField phone1 = new JTextField();
    private JTextField phone2 = new JTextField();
    private JTextField nip = new JTextField();
    private JComboBox type = new JComboBox(new String[] {"Klient", "Pracownik", "Dostawca"});
    
    private JCheckBox addPI = new JCheckBox("Dodaj dane osobowe");
    
    @Override
    public void init(Object[] args) {
        this.setLayout(new BorderLayout());
        wypelnij();
        przyciski = new ButtonsPanel((byte) 0b00010000, (byte) 0b00010000, this);
        ActionListener cAL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelMgr(rightP, addPI.isSelected());
            }
        };
        addPI.addActionListener(cAL);
    }

    private void wypelnij() {
        JPanel cent = new JPanel();
        leftP.setLayout(new FlowLayout(FlowLayout.RIGHT));
        rightP.setLayout(new FlowLayout(FlowLayout.LEFT));
        add(cent, BorderLayout.CENTER);
        cent.add(leftP);
        cent.add(rightP);
        add(przyciski, BorderLayout.SOUTH);

        // lewy panel
        JPanel group = new JPanel(new SpringLayout());
        JLabel lbl = new JLabel("Nazwa użytkownika", JLabel.TRAILING);
        group.add(lbl);
        group.add(userName);
        lbl.setLabelFor(userName);
        lbl = new JLabel("Hasło", JLabel.TRAILING);
        group.add(lbl);
        lbl.setLabelFor(userPassword);
        group.add(userPassword);
        lbl = new JLabel("Powtórz hasło", JLabel.TRAILING);
        group.add(lbl);
        lbl.setLabelFor(userPasswordR);
        group.add(userPasswordR);
        lbl = new JLabel("E-mail", JLabel.TRAILING);
        group.add(lbl);
        lbl.setLabelFor(eMail);
        group.add(eMail);
        lbl = new JLabel("Uprawnienia", JLabel.TRAILING);
        group.add(lbl);
        lbl.setLabelFor(rmask);
        group.add(rmask);
        lbl = new JLabel("");
        group.add(lbl);
        group.add(addPI);
        leftP.add(group);
        
        SpringUtilities.makeCompactGrid(group,
                                        6, 2, //rows, cols
                                        6, 6, //initX, initY
                                        6, 6); //xPad, yPad

        // prawy panel
        group = new JPanel(new SpringLayout());
        lbl = new JLabel("Nazwa", JLabel.TRAILING);
        group.add(lbl);
        group.add(name);
        lbl.setLabelFor(name);
        lbl = new JLabel("Adres", JLabel.TRAILING);
        group.add(lbl);
        lbl.setLabelFor(address);
        group.add(address);
        lbl = new JLabel("Telefon", JLabel.TRAILING);
        group.add(lbl);
        lbl.setLabelFor(phone1);
        group.add(phone1);
        lbl = new JLabel("Telefon 2", JLabel.TRAILING);
        group.add(lbl);
        lbl.setLabelFor(phone2);
        group.add(phone2);
        lbl = new JLabel("NIP", JLabel.TRAILING);
        group.add(lbl);
        lbl.setLabelFor(nip);
        group.add(nip);
        lbl = new JLabel("Typ", JLabel.TRAILING);
        group.add(lbl);
        lbl.setLabelFor(type);
        group.add(type);
        rightP.add(group);
        panelMgr(rightP,false);
        
        SpringUtilities.makeCompactGrid(group,
                                        6, 2, //rows, cols
                                        6, 6, //initX, initY
                                        6, 6); //xPad, yPad
    }

    private void panelMgr(Container c, boolean onOff) {
        for (Component cnt : ((Container)(c.getComponents()[0])).getComponents()) {
            cnt.setEnabled(onOff);
        }
    }

    @Override
    public void logout() {
    }

    @Override
    public void takeAction(int type, Object[] args) {
    }

    @Override
    public Object[] getData(int type, Object[] args) {
        switch (type) {
            default:
                return new Object[]{""};

        }
    }

}
