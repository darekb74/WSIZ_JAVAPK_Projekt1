/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikacjadesktop;

import Obiekty.MenuBar;
import Obiekty.TabelaDanych;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;

/**
 *
 * @author Darek Xperia
 */
public class AplikacjaDesktop extends JFrame {

    private TabelaDanych tabela;
    private MenuBar menuBar;

    AplikacjaDesktop() {
        super("Serwis komuterowy - aplikacja desktop");
        this.setLayout(new BorderLayout());
        menuBar = new MenuBar(Obiekty.Def.LVL0);
        this.add(menuBar,BorderLayout.NORTH);

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
                "Kierownik serwisu", new Byte((byte)(Obiekty.Def.LVL2 | Obiekty.Def.LVL5)), new Boolean(true)},
            {"Rafał", "Bogowski",
                "Administrator", new Byte(Obiekty.Def.ADM), new Boolean(true)},
            {"Darek", "Problemski",
                "klient", new Byte(Obiekty.Def.LVL0), new Boolean(false)}
        };
        
        tabela = new TabelaDanych(dane, nazwyKolumn, this);

    }

    public static void main(String[] args) {

        JFrame main = new AplikacjaDesktop();
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main.pack();
        main.setSize(new Dimension(550, 300));
        main.setVisible(true);
    }

}
