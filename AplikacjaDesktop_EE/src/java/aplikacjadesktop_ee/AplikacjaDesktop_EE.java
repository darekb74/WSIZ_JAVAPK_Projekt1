/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikacjadesktop_ee;

import Karty.Login;
import Karty.NewUserD;
import Karty.UserDTable;
import Karty.WelcomeCard;
import Obiekty.Def;
import Obiekty.MenuBar;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Darek Xperia
 */
public class AplikacjaDesktop_EE extends JFrame {

    private MenuBar menuBar = new MenuBar(null, this);
    private Login panel = new Login(this, menuBar);
    private UserDTable userDt = new UserDTable();
    private NewUserD userDa = new NewUserD();

    public JPanel karty = new JPanel(new CardLayout());

    public CardLayout cl;

    private WelcomeCard sStartowa = new WelcomeCard();

    AplikacjaDesktop_EE() {
        super("Serwis komuterowy - aplikacja desktop");
        this.setLayout(new BorderLayout());
        this.add(menuBar, BorderLayout.NORTH);

        // tabela użytkowników
        userDt.init(null);
        // dodaj użytkownika
        userDa.init(null);
        
        this.add(karty);
        cl = (CardLayout) karty.getLayout();

        karty.add(sStartowa, Def.O_STARTOWA);
        karty.add(userDt, Def.O_L_USERD);
        karty.add(userDa, Def.O_A_USERD);

        // login form
        this.add(panel, BorderLayout.SOUTH);

        //logout przy zakończeniu
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                panel.logout();
            }
        });
    }

    public void wyloguj() {
        panel.logout();
        // przenieś na stronę startową
        cl.show(karty, Def.O_STARTOWA);
    }

    public static void main(String[] args) {

        JFrame main = new AplikacjaDesktop_EE();
        main.setDefaultCloseOperation(EXIT_ON_CLOSE);
        main.pack();
        main.setSize(new Dimension(750, 300));
        main.setVisible(true);
        main.setLocationRelativeTo(null);
    }

}
