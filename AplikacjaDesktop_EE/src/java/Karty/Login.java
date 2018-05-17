/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Karty;

import DTO.UserDTO;
import EE_ejb.FasadaUserD_ejbRemote;
import Obiekty.MenuBar;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author Darek Xperia
 * @classdesc Panel logowania
 */
public class Login extends JPanel {

    private JTextField nu = new JTextField();
    private JPasswordField ha = new JPasswordField();
    private JButton bu = new JButton("Zaloguj");
    private JLabel wynik = new JLabel("Wprowadź dane i wciśnij Zaloguj");

    private Container rodzic;
    private MenuBar menuBar;

    public boolean zalogowany;
    public UserDTO luser;

    public Login(Container rodzic, MenuBar menuBar) {
        if (rodzic == null || menuBar == null) {
            // nieprawidowa inicjacjia
            System.out.println("[BŁĄD] Nieprawidłowa inicjacja panelu logowania!");
            return;
        }
        this.rodzic = rodzic;
        this.menuBar = menuBar;
        nu.setPreferredSize(new Dimension(100, 27));
        ha.setPreferredSize(new Dimension(100, 27));

        ActionListener acL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!nu.getText().isEmpty() && ha.getPassword().length > 0) {
                    FasadaUserD_ejbRemote fasada = lookupFasadaUserD_ejbRemote();
                    UserDTO tmp = fasada.znajdzUzytkownika(nu.getText());
                    if (tmp != null) {
                        if (tmp.getId() > 0) { // jest uzytkownik o takim username
                            if (tmp.getPassword_hash().equals(Utils.Utils.md5(String.valueOf(ha.getPassword())))) {
                                // hasło ok
                                zalogowany = true;
                                luser = tmp;
                                wypelnijPanel();
                                //pustaw datę i stan online
                                luser.setIsOnline(true);
                                luser.setLast_login(Utils.Utils.usunMS(new Date()));
                                fasada.aktualizujDane(luser);
                            } else {
                                wynik.setText("Nieprawidłowy login lub hasło!");
                                wynik.setForeground(Color.red);
                                ha.setText("");
                            }
                        } else {
                            wynik.setText("Nieprawidłowy login lub hasło!");
                            wynik.setForeground(Color.red);
                            ha.setText("");
                        }
                    } else {
                        wynik.setText("Nieprawidłowy login lub hasło!");
                        wynik.setForeground(Color.red);
                        ha.setText("");
                    }
                } else {
                    wynik.setText("Proszę wypełnić wszystkie pola!");
                    wynik.setForeground(Color.red);
                }

            }
        };
        bu.addActionListener(acL);
        wypelnijPanel();

    }

    private void wypelnijPanel() {
        if (zalogowany) {
            this.removeAll();
            JLabel l3 = new JLabel("Zalogowany jako " + luser.getUsername() + ".");
            this.add(l3);
            menuBar.removeAll();
            menuBar.setRights(luser);
            rodzic.validate();
        } else {
            this.removeAll();
            wynik.setText("Wprowadź dane i wciśnij Zaloguj");
            wynik.setForeground(Color.black);
            JLabel l1 = new JLabel("Nazwa uzytkownika:");
            this.add(l1);
            this.add(nu);
            JLabel l2 = new JLabel("Hasło:");
            this.add(l2);
            this.add(ha);
            this.add(bu);
            this.add(wynik);
            ha.setText("");
            menuBar.removeAll();
            menuBar.setRights(luser);
            rodzic.validate();
        }
    }

    public void logout() {
        if (zalogowany) {
            luser.setIsOnline(false);
            lookupFasadaUserD_ejbRemote().aktualizujDane(luser);
            zalogowany = false;
            luser = null;
            wypelnijPanel();
        }
    }

    private FasadaUserD_ejbRemote lookupFasadaUserD_ejbRemote() {
        try {
            Context c = new InitialContext();
            return (FasadaUserD_ejbRemote) c.lookup("ejb/FasadaUserD_ejb");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
