/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Menadzery;

import Menadzery.Def;
import Tabele.User;
import java.util.ArrayList;

/**
 *
 * @author Darek Xperia
 */
public class MgrUzytkownikow {

    private ArrayList<User> uLista = new ArrayList<>();

    public MgrUzytkownikow() {

    }

    public void dodajUzytkownika(User u) {
        if (!uLista.contains(u)) {
            uLista.add(u);
        } else if (Def.DEBUG) {
            System.out.println("Użytkownik '" + u.getUsername() + "' już istnieje.");
        }
    }

    public void usunUzytkownika(User u) {
        if (uLista.contains(u)) {
            uLista.remove(u);
        } else if (Def.DEBUG) {
            System.out.println("Użytkownik '" + u.getUsername() + "' nie znaleziony.");
        }
    }
}
