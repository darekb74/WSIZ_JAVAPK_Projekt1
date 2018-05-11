/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Obiekty;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 *
 * @author Darek Xperia
 */
public class MenuBar extends JMenuBar implements ActionListener {

    public MenuBar(Byte poziomDostepu) {
        super();

        setRights(poziomDostepu);
    }
    
    public void setRights(Byte rmask) {

        JMenu menu, subMenu;
        JMenuItem menuItem;

        // doajemy całe menu a potem wyłączamy elementy niedostępne dla danego poziomu
        menu = new JMenu("Program");
        menu.setMnemonic(KeyEvent.VK_P);
        add(menu);
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
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JMenuItem source = (JMenuItem) (e.getSource());
        switch (source.getText()) {
            case Def.O_ZAKONCZ:
                System.exit(0);
                break;
            case Def.O_SPRAWDZ_STANY:
                
                break;
            case Def.O_SZUKAJ_CZESCI:
                
                break;
            default:
                break;
        }
    }
}
