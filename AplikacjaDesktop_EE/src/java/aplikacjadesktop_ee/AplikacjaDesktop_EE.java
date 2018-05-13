/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikacjadesktop_ee;

import Karty.Login;
import Karty.UserDTable;
import Obiekty.MenuBar;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;

/**
 *
 * @author Darek Xperia
 */
public class AplikacjaDesktop_EE extends JFrame {
    
    private MenuBar menuBar = new MenuBar(null, this);
    private Login panel = new Login(this, menuBar);
    private UserDTable tabela = new UserDTable();

    AplikacjaDesktop_EE() {
        super("Serwis komuterowy - aplikacja desktop");
        this.setLayout(new BorderLayout());
        this.add(menuBar, BorderLayout.NORTH);

        // tabela użytkowników
        tabela.init(null);
        this.add(tabela);
        
        // login form
        Object[] init = {this, menuBar};
        this.add(panel, BorderLayout.SOUTH);
        
    }

    public void wyloguj() {
        panel.logout();
    }

    public static void main(String[] args) {

        JFrame main = new AplikacjaDesktop_EE();
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main.pack();
        main.setSize(new Dimension(750, 300));
        main.setVisible(true);
    }

}
