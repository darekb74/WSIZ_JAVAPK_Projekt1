/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Karty;

import Obiekty.PanelStron;
import Obiekty.TextComponentLimit;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Arrays;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import layout.SpringUtilities;

/**
 *
 * @author Darek Xperia
 */
public class NewUserD extends JPanel implements Karta {
    
    private PanelStron panelStron;

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
    private JComboBox type = new JComboBox(new String[]{"Klient", "Pracownik", "Dostawca"});

    private JCheckBox addPI = new JCheckBox("Dodaj dane osobowe");

    @Override
    public void init(Object[] args) {
        panelStron = (PanelStron) args[0];
        this.setLayout(new BorderLayout());
        wypelnij();
        ActionListener cAL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelMgr(rightP, addPI.isSelected());
            }
        };
        addPI.addActionListener(cAL);

        // setName
        userName.setName("userName");
        userPassword.setName("userPassword");
        userPasswordR.setName("userPasswordR");
        eMail.setName("eMail");
        rmask.setName("rmask");

        name.setName("name");
        address.setName("address");
        phone1.setName("phone1");
        phone2.setName("phone2");
        nip.setName("nip");
        type.setName("type");

        TextComponentLimit.addTo(userName, 32);
        TextComponentLimit.addTo(userPassword, 32);
        TextComponentLimit.addTo(userPasswordR, 32);
        TextComponentLimit.addTo(eMail, 64);

        TextComponentLimit.addTo(name, 128);
        TextComponentLimit.addTo(address, 128);
        TextComponentLimit.addTo(phone1, 16);
        TextComponentLimit.addTo(phone2, 16);
        TextComponentLimit.addTo(nip, 32);

        FocusListener FL = new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
            }

            @Override
            public void focusLost(FocusEvent e) {
                switch (e.getComponent().getName()) {
                    case "userName":
                        if (!Utils.Utils.sprawdzPoprawnoscDanych(100, userName.getText())) {
                            userName.setBorder(new LineBorder(Color.red));
                            //SwingUtilities.invokeLater(new FocusGrabber(userName));
                            userName.setToolTipText("Nazwa użytkownika powinna się składać z od 3 do 32 zanków alfanumerycznych.");
                        } else {
                            userName.setBorder(UIManager.getBorder("TextField.border"));
                            userName.setToolTipText("");
                        }
                        break;
                    case "userPassword":
                        if (!Utils.Utils.sprawdzPoprawnoscDanych(101, String.valueOf(userPassword.getPassword()))) {
                            userPassword.setBorder(new LineBorder(Color.red));
                            //SwingUtilities.invokeLater(new FocusGrabber(userName));
                            userPassword.setToolTipText("Hasło powinno się składać z od 5 do 32 zanków alfanumerycznych, zawierać minimum jedną dużą literę, jedną małą literę oraz jedną cyfrę.");
                        } else {
                            userPassword.setBorder(UIManager.getBorder("TextField.border"));
                            userPassword.setToolTipText("");
                        }
                        break;
                    case "userPasswordR":
                        if (!Arrays.equals(userPasswordR.getPassword(), userPassword.getPassword())) {
                            userPasswordR.setBorder(new LineBorder(Color.red));
                            //SwingUtilities.invokeLater(new FocusGrabber(userName));
                            userPasswordR.setToolTipText("Hasła nie są identyczne!");
                        } else {
                            userPasswordR.setBorder(UIManager.getBorder("TextField.border"));
                            userPasswordR.setToolTipText("");
                        }
                        break;
                    case "eMail":
                        if (!Utils.Utils.sprawdzPoprawnoscDanych(102, eMail.getText())) {
                            eMail.setBorder(new LineBorder(Color.red));
                            //SwingUtilities.invokeLater(new FocusGrabber(eMail));
                            eMail.setToolTipText("Należy wprowadzić prawdiłowy adres e-mail!");
                        } else {
                            eMail.setBorder(UIManager.getBorder("TextField.border"));
                            eMail.setToolTipText("");
                        }
                        break;
                    case "rmask":
                        Short sh = null;
                        try {
                            sh = Short.parseShort(rmask.getText());
                        } catch (NumberFormatException ex) {
                        }
                        if (sh == null || sh < 0 || sh > 255) {
                            rmask.setBorder(new LineBorder(Color.red));
                            //SwingUtilities.invokeLater(new FocusGrabber(eMail));
                            rmask.setToolTipText("Nieprawidłowa maska uprawnień! Wymagana liczba z zakresu 0 - 255.");
                        } else {
                            rmask.setBorder(UIManager.getBorder("TextField.border"));
                            rmask.setToolTipText("");
                        }
                        break;
                    default:
                        break;
                }
            }
        };
        userName.addFocusListener(FL);
        userPassword.addFocusListener(FL);
        userPasswordR.addFocusListener(FL);
        eMail.addFocusListener(FL);
        rmask.addFocusListener(FL);

        name.addFocusListener(FL);
        address.addFocusListener(FL);
        phone1.addFocusListener(FL);
        phone2.addFocusListener(FL);
        nip.addFocusListener(FL);
        type.addFocusListener(FL);
    }

    private void wypelnij() {
        JPanel cent = new JPanel();
        leftP.setLayout(new FlowLayout(FlowLayout.RIGHT));
        rightP.setLayout(new FlowLayout(FlowLayout.LEFT));
        cent.add(leftP);
        cent.add(rightP);
        przyciski = new ButtonsPanel((byte) 0b00010000, (byte) 0b00010000, this);

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
        panelMgr(rightP, false);

        SpringUtilities.makeCompactGrid(group,
                6, 2, //rows, cols
                6, 6, //initX, initY
                6, 6); //xPad, yPad
        add(cent, BorderLayout.CENTER);
        add(przyciski, BorderLayout.SOUTH);
    }

    private void panelMgr(Container c, boolean onOff) {
        for (Component cnt : ((Container) (c.getComponents()[0])).getComponents()) {
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

    class FocusGrabber implements Runnable {

        private JComponent component;

        public FocusGrabber(JComponent component) {
            this.component = component;
        }

        public void run() {
            component.grabFocus();
        }
    }
}
