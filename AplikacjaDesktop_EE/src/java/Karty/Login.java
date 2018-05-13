/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Karty;

import DTO.UserDTO;
import EE_ejb.Fasada_EE_ejbRemote;
import Obiekty.MenuBar;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
 */
public class Login extends JPanel implements Karta {
    
    private JTextField nu = new JTextField();
    private JPasswordField ha = new JPasswordField();
    private JButton bu = new JButton("Zaloguj");
    private JLabel wynik = new JLabel("Wprowadź dane i wciśnij Zaloguj");

    private Container rodzic;
    private MenuBar menuBar;
    
    public boolean zalogowany;
    public UserDTO luser;

    @Override
    public void init(Object[] args) {
        if (args[0] == null || args[1] == null ) {
            // nieprawidowa inicjacjia
            System.out.println("[BŁĄD] Nieprawidłowa inicjacja panelu logowania!");
            return;
        }
        rodzic = (Container) args[0];
        menuBar = (MenuBar) args[1];
        nu.setPreferredSize(new Dimension(100, 25));
        ha.setPreferredSize(new Dimension(100, 25));
        
        ActionListener acL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!nu.getText().isEmpty() && ha.getPassword().length > 0) {

                    UserDTO tmp = lookupFasada_EE_ejbRemote().znajdzUzytkownika(nu.getText());
                    if (tmp.getId() > 0) { // jest uzytkownik o takim username
                        if (tmp.getPassword_hash().equals(Utils.Utils.md5(String.valueOf(ha.getPassword())))) {
                            // hasło ok
                            zalogowany = true;
                            luser = tmp;
                            wypelnijPanel();
                        } else {
                            wynik.setText("Nieprawidłowy login lub hasło!");
                            wynik.setForeground(Color.red);
                        }
                    } else {
                        wynik.setText("Nieprawidłowy login lub hasło!");
                        wynik.setForeground(Color.red);
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

    @Override
    public void logout() {
        if (zalogowany) {
            zalogowany = false;
            luser = null;
            wypelnijPanel();
        }
    }

    @Override
    public void takeAction(int type) {

    }

    private Fasada_EE_ejbRemote lookupFasada_EE_ejbRemote() {
        try {
            Context c = new InitialContext();
            return (Fasada_EE_ejbRemote) c.lookup("java:comp/env/Fasada_EE_ejb");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
