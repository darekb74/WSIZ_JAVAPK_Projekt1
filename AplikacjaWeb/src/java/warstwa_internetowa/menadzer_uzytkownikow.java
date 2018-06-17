/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package warstwa_internetowa;

import DTO.UserDTO;
import EE_ejb.FasadaUserD_ejbRemote;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author Darek Xperia
 */
@ManagedBean
@SessionScoped
@Named(value = "menadzer_uzytkownikow")
public class menadzer_uzytkownikow implements Serializable {

    private FasadaUserD_ejbRemote fasadaUser;

    @PostConstruct
    public void init() {
        uDTO = null;
        fasadaUser = lookupFasadaUserD_ejbRemote();
        zalogowany = false;
    }

    private boolean zalogowany;
    private UserDTO uDTO;

    private String uzytkownik;
    private String haslo;

    public boolean isZalogowany() {
        System.out.println("Zalogowany:" + zalogowany);
        return zalogowany;
    }

    public String getUzytkownik() {
        return uzytkownik;
    }

    public void setUzytkownik(String uzytkownik) {
        this.uzytkownik = uzytkownik;
    }

    public String getHaslo() {
        return haslo;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }

    public FasadaUserD_ejbRemote getFasadaUser() {
        return fasadaUser;
    }

    public void setFasadaUser(FasadaUserD_ejbRemote fasadaUser) {
        this.fasadaUser = fasadaUser;
    }

    public String logout() {
        if (zalogowany) {
            uDTO = null;
            zalogowany = false;
            System.out.println("LOGOUT");
        }
        return "index";
    }

    public String login() {
        System.out.println("Uzytkownik:" + uzytkownik + ", HASLO:" + haslo + ", FASADA:" + fasadaUser.toString());
        if (fasadaUser != null && uzytkownik != null && haslo != null) {
            uDTO = fasadaUser.znajdzUzytkownika(uzytkownik);
            if (uDTO != null) {
                if (uDTO.getPassword_hash().equals(Utils.Utils.md5(String.valueOf(haslo)))) {
                    zalogowany = true;
                    System.out.println("LOGIN");
                } else {
                    uDTO = null;
                    zalogowany = false;
                }
            }
        }

        return "index";
    }

    public boolean posiadaUprawnienia(String typ) {
        if (!isZalogowany() || uDTO == null) {
            return false;
        }
        switch (typ) {
            case "magazynier":
                if ((uDTO.getRmask() & Def.LVL1) > 0) {
                    return true;
                }
                break;
            case "administrator":
                if ((uDTO.getRmask() & Def.ADM) > 0) {
                    return true;
                }
                break;
        }
        return false;
    }

    private FasadaUserD_ejbRemote lookupFasadaUserD_ejbRemote() {
        try {
            Context c = new InitialContext();
            return (FasadaUserD_ejbRemote) c.lookup("ejb/FasadaUserD_ejb");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
