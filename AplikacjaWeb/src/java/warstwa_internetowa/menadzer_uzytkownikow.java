/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package warstwa_internetowa;

import DTO.UserDTO;
import EE_ejb.FasadaUserD_ejbRemote;
import Utils.Pagination;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.ManagedBean;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
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

    private FasadaUserD_ejbRemote fasadaUser = lookupFasadaUserD_ejbRemote();
    ;

    private boolean zalogowany = false;
    private UserDTO uDTO = null;

    private String uzytkownik;
    private String haslo;

    private List<UserDTO> lista;
    private Pagination<UserDTO> strony;

    public Pagination<UserDTO> getStrony() {
        return strony;
    }

    public void lastPage() {
        if (strony != null) {
            lista = strony.lastPage();
        }
    }

    public void firstPage() {
        if (strony != null) {
            lista = strony.firstPage();
        }
    }

    public void nextPage() {
        System.out.println("STRONY:" + strony.toString());
        if (strony != null) {
            lista = strony.nextPage();
        }
    }

    public void previousPage() {
        if (strony != null) {
            lista = strony.previousPage();
        }
    }

    public void setPage(int pageNo) {
        System.out.println("STRONY:" + strony.toString() + ", PAGE:" + pageNo);
        if (strony != null) {
            lista = strony.getPageNo(pageNo);
        }
    }

    private void setPagination() {
        strony = new Pagination(5, fasadaUser.listaUzytkownikow());
        lista = strony.generateDataArray();
    }
    
    public Boolean doRenderowania(int pos) {
        if (strony==null) {
            setPagination();
        }
        List<String> tmp = strony.generateControlList();
        switch (pos) {
            case 0:
                if (tmp.contains("<<")) {
                    return true;
                }
                break;
            case 1:
                if (tmp.contains("<")) {
                    return true;
                }
                break;
            case 12:
                if (tmp.contains(">")) {
                    return true;
                }
                break;
            case 13:
                if (tmp.contains(">>")) {
                    return true;
                }
                break;
            default:
                String code = strony.getControlArrayItem(pos-2);
                try {
                    if (Long.parseLong(code) > 0) {
                        return true;
                    }
                } catch (NumberFormatException e) {
                    return false;
                }
                break;
        }
        return false;
    }

    public String getControlArrayItem(int pos) {
        if (strony != null) {
            System.out.println("DEBUG 1");
            return strony.getControlArrayItem(pos);
        } else {
            System.out.println("DEBUG 2");
            setPagination();
            return strony.getControlArrayItem(pos);
        }
    }

    public boolean isZalogowany() {
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

    public DataModel getLista() {

        if (fasadaUser != null) {
            if (lista == null) {
                setPagination();
            }
        } else {
            return null;
        }

        return new ListDataModel(lista);
    }

    public String logout() {
        if (zalogowany) {
            if (fasadaUser != null && uDTO != null) {
                uDTO.setIsOnline(false); // uaktualnij status online
                fasadaUser.aktualizujDane(uDTO); // uaktualnij bazę
            }
            uDTO = null;
            zalogowany = false;
        }
        return "/index";
    }

    public String login() {
        if (fasadaUser != null && uzytkownik != null && haslo != null) {
            uDTO = fasadaUser.znajdzUzytkownika(uzytkownik);
            if (uDTO != null) {
                if (uDTO.getPassword_hash().equals(Utils.Utils.md5(String.valueOf(haslo)))) {
                    zalogowany = true;
                    uDTO.setIsOnline(true); // uaktualnij status online
                    uDTO.setLast_login(Utils.Utils.usunMS(new Date())); // uaktualnij dane ostatniego zalogowania
                    fasadaUser.aktualizujDane(uDTO); // uaktualnij bazę
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
