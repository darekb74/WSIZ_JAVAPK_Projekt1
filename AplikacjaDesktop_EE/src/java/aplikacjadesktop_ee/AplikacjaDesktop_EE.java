/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikacjadesktop_ee;

import DTO.UserDTO;
import EE_ejb.Fasada_EE_ejbRemote;
import Obiekty.MenuBar;
import Obiekty.TabelaDanych;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.ejb.EJB;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Darek Xperia
 */
public class AplikacjaDesktop_EE extends JFrame {

    @EJB
    private static Fasada_EE_ejbRemote fasada_EE_ejb;

    private TabelaDanych tabela;
    private MenuBar menuBar;
    private JTextField nu = new JTextField();
    private JTextField ha = new JTextField();
    private JButton bu = new JButton("Zaloguj");
    private JPanel panel = new JPanel();
    private JLabel wynik = new JLabel("Wprowadź dane i wciśnij Zaloguj");
    private boolean zalogowany = false;
    private UserDTO luser = null;

    AplikacjaDesktop_EE() {
        super("Serwis komuterowy - aplikacja desktop");
        this.setLayout(new BorderLayout());
        menuBar = new MenuBar(Obiekty.Def.LVL0);
        this.add(menuBar, BorderLayout.NORTH);

        // testowe dane
        String[] nazwyKolumn = {"Imię",
            "Nazwisko",
            "Stanowisko",
            "Poziom dostępu",
            "Pracownik"};

        Object[][] dane = {
            {"Katarzyna", "Iksińska",
                "Księgowa", new Byte(Obiekty.Def.LVL6), new Boolean(true)},
            {"Jerzy", "Jabłoński",
                "Magazynier", new Byte(Obiekty.Def.LVL1), new Boolean(true)},
            {"Wacław", "Cyniński",
                "Serwisant", new Byte(Obiekty.Def.LVL2), new Boolean(true)},
            {"Andrzej", "Menadżerski",
                "Kierownik serwisu", new Byte((byte) (Obiekty.Def.LVL2 | Obiekty.Def.LVL5)), new Boolean(true)},
            {"Rafał", "Bogowski",
                "Administrator", new Byte(Obiekty.Def.ADM), new Boolean(true)},
            {"Darek", "Problemski",
                "klient", new Byte(Obiekty.Def.LVL0), new Boolean(false)}
        };

        tabela = new TabelaDanych(dane, nazwyKolumn, this);
        tabela.getTableHeader().setReorderingAllowed(false); // wyłączenie przenoszenia kolumn
        // login form
        this.add(panel, BorderLayout.SOUTH);
        nu.setPreferredSize(new Dimension(100, 25));
        ha.setPreferredSize(new Dimension(100, 25));
        
        wypelnijPanel();
        ActionListener acL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!nu.getText().isEmpty() && !ha.getText().isEmpty()) {
                    
                    UserDTO tmp = fasada_EE_ejb.znajdzUzytkownika(nu.getText());
                    if(tmp.getId()>0) { // jest uzytkownik o takim username
                        if (tmp.getPassword_hash().equals(Utils.Utils.md5(ha.getText()))) {
                            // hasło ok
                            zalogowany = true;
                            luser = tmp;
                            wypelnijPanel();
                        } else {
                            wynik.setText("Nieprtawidłowe login lub hasło!");
                            wynik.setForeground(Color.red);
                        }
                    } else {
                        wynik.setText("Nieprtawidłowe login lub hasło!");
                        wynik.setForeground(Color.red);
                    }
                } else {
                    wynik.setText("Proszę wypełnić wszystkie pola!");
                    wynik.setForeground(Color.red);
                }
            }
        };
        bu.addActionListener(acL);
    }

    private void wypelnijPanel() {
        if (zalogowany) {
            panel.removeAll();
            JLabel l3 = new JLabel("Zalogowany jako " + luser.getUsername() +".");
            panel.add(l3);
        } else {
            panel.removeAll();
            JLabel l1 = new JLabel("Nazwa uzytkownika:");
            panel.add(l1);
            panel.add(nu);
            JLabel l2 = new JLabel("Hasło:");
            panel.add(l2);
            panel.add(ha);
            panel.add(bu);
            panel.add(wynik);
        }
    }

    public static void main(String[] args) {

        JFrame main = new AplikacjaDesktop_EE();
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main.pack();
        main.setSize(new Dimension(750, 300));
        main.setVisible(true);
    }

}
