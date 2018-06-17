/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package warstwa_internetowa;

import javax.ejb.Stateless;
import javax.inject.Named;

/**
 *
 * @author Darek Xperia
 */
@Stateless
@Named(value = "menadzer_uzytkownikow")
public class menadzer_uzytkownikow {

    public boolean posiadaUprawnienia(String typ) {
        return true;
    }
}
