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
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.ejb.EJB;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

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
    private JPasswordField  ha = new JPasswordField();
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
        String[] nazwyKolumn = {"Id",
            "Nazwa użytkownika",
            "Hasło",
            "e-Mail",
            "Ostatni login",
            "Online",
            "Poziom dostępu"};

        DefaultTableModel model = new DefaultTableModel(nazwyKolumn, 0);
        DefaultTableModel modelb = new DefaultTableModel(nazwyKolumn, 0);
        List<UserDTO> tmp = fasada_EE_ejb.listaUzytkownikow();
        formatujDane(tmp, model, modelb);
        tabela = new TabelaDanych(model, modelb, this);
        tabela.getTableHeader().setReorderingAllowed(false); // wyłączenie przenoszenia kolumn
        // login form
        this.add(panel, BorderLayout.SOUTH);
        nu.setPreferredSize(new Dimension(100, 25));
        ha.setPreferredSize(new Dimension(100, 25));
        
        wypelnijPanel();
        ActionListener acL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!nu.getText().isEmpty() && ha.getPassword().length>0) {
                    
                    UserDTO tmp = fasada_EE_ejb.znajdzUzytkownika(nu.getText());
                    if(tmp.getId()>0) { // jest uzytkownik o takim username
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
    }

    private void formatujDane(List<UserDTO> in, DefaultTableModel model, DefaultTableModel modelb) {
       // najpierw List<UserDTO> to List<String>
       for(UserDTO e : in) {
           model.addRow(e.toArray());
           modelb.addRow(e.toArray());
       }
    }
    private void wypelnijPanel() {
        if (zalogowany) {
            panel.removeAll();
            JLabel l3 = new JLabel("Zalogowany jako " + luser.getUsername() +".");
            panel.add(l3);
            menuBar.removeAll();
            menuBar.setRights(luser.getRmask());
            this.validate();
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
            this.validate();
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
