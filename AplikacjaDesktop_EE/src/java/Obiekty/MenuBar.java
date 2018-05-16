/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Obiekty;

import DTO.UserDTO;
import aplikacjadesktop_ee.AplikacjaDesktop_EE;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.Box;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 *
 * @author Darek Xperia
 */
public class MenuBar extends JMenuBar implements ActionListener {

    private Container rodzic;

    public MenuBar(UserDTO user, Container rodzic) {
        super();
        this.rodzic = rodzic;
        setRights(user);
    }

    public void setRights(UserDTO user) {

        Short rmask = (user != null ? user.getRmask() : Obiekty.Def.LVL0);
        JMenu menu, subMenu;
        JMenuItem menuItem;

        // doajemy całe menu a potem wyłączamy elementy niedostępne dla danego poziomu
        menu = new JMenu("Program");
        menu.setMnemonic(KeyEvent.VK_P);
        add(menu);
        menuItem = new JMenuItem(Def.O_STARTOWA, KeyEvent.VK_T);
        menuItem.addActionListener(this);
        menu.add(menuItem);
        menuItem = new JMenuItem(Def.O_ZAKONCZ, KeyEvent.VK_Z);
        menuItem.addActionListener(this);
        menu.add(menuItem);

        menu = new JMenu("Magazyn");
        menu.setMnemonic(KeyEvent.VK_M);
        add(menu);
        menuItem = new JMenuItem(Def.O_SPRAWDZ_STANY, KeyEvent.VK_S);
        menuItem.addActionListener(this);
        if ((rmask & (Def.LVL1 | Def.LVL5)) == 0) {
            menuItem.setEnabled(false); // brak uprawnień
        }
        menu.add(menuItem);
        menuItem = new JMenuItem(Def.O_SZUKAJ_CZESCI, KeyEvent.VK_C);
        menuItem.addActionListener(this);
        if ((rmask & Def.LVL1) == 0) {
            menuItem.setEnabled(false); // brak uprawnień
        }
        menu.add(menuItem);
        
        menu = new JMenu("Administracja");
        menu.setMnemonic(KeyEvent.VK_A);
        add(menu);
        menuItem = new JMenuItem(Def.O_L_USERD, KeyEvent.VK_L);
        menuItem.addActionListener(this);
        if ((rmask & Def.LVL5) == 0) {
            menuItem.setEnabled(false); // brak uprawnień
        }
        menu.add(menuItem);
        
        // menu użytkownika
        if (user != null) {
            menu = new JMenu("[" + user.getUsername() + "]");
            add(Box.createHorizontalGlue());
            menu.setMnemonic(KeyEvent.VK_U);
            add(menu);
            menuItem = new JMenuItem(Def.O_WYLOGUJ, KeyEvent.VK_W);
            menuItem.addActionListener(this);
            menu.add(menuItem);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JMenuItem source = (JMenuItem) (e.getSource());
        switch (source.getText()) {
            case Def.O_ZAKONCZ:
                ((AplikacjaDesktop_EE) rodzic).wyloguj();
                System.exit(0);
                break;
            case Def.O_SPRAWDZ_STANY:

                break;
            case Def.O_SZUKAJ_CZESCI:

                break;
            case Def.O_STARTOWA:
                ((AplikacjaDesktop_EE) rodzic).cl.show(((AplikacjaDesktop_EE) rodzic).karty, Def.O_STARTOWA);
                break;
            case Def.O_L_USERD:
                ((AplikacjaDesktop_EE) rodzic).cl.show(((AplikacjaDesktop_EE) rodzic).karty, Def.O_L_USERD);
                break;
            case Def.O_WYLOGUJ:
                ((AplikacjaDesktop_EE) rodzic).wyloguj();
                break;
            default:
                break;
        }
    }
}
