/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package warstwa_internetowa;

import DTO.UserDTO;
import EE_ejb.FasadaUserD_ejbRemote;
import Utils.Pagination;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

/**
 *
 * @author Darek Xperia
 */
@ManagedBean
//@Named(value = "menadzer_uzytkownikow")
@SessionScoped
//@Stateful
public class menadzer_uzytkownikow implements Serializable {

    @EJB(mappedName = "ejb/FasadaUserD_ejb")
    private FasadaUserD_ejbRemote fasadaUser;

    @PostConstruct
    public void init() {
        setPagination();
    }
    //private FasadaUserD_ejbRemote fasadaUser = lookupFasadaUserD_ejbRemote();
    private boolean zalogowany = false;
    private UserDTO uDTO = null;

    private String uzytkownik;
    private String haslo;

    private List<UserDTO> listaU;
    private Pagination<UserDTO> strony;

    private String username, password, password2, pasword_hash, email;
    private Short rmask;

    public Pagination<UserDTO> getStrony() {
        return strony;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String eMail) {
        this.email = eMail;
    }

    public Short getRmask() {
        return rmask;
    }

    public void setRmask(Short rmask) {
        this.rmask = rmask;
    }

    public void lastPage() {
        if (strony != null) {
            listaU = strony.lastPage();
        }
    }

    public void firstPage() {
        if (strony != null) {
            listaU = strony.firstPage();
        }
    }

    public void nextPage() {
        if (strony != null) {
            listaU = strony.nextPage();
        }
    }

    public void previousPage() {
        if (strony != null) {
            listaU = strony.previousPage();
        }
    }

    public void setPage(int pageNo) {
        if (strony != null) {
            listaU = strony.getPageNo(pageNo);
        }
    }

    public void dodaj() {
        fasadaUser.dodajUzytkownika(new UserDTO(fasadaUser.znajdzNastepneID(), username, Utils.Utils.md5(password), email, null, false, rmask));
        setPagination();
    }

    public String czyWylaczony(int pageNo) {
        if (strony != null) {
            if (strony.getPage() == pageNo) {
                return "disabled";
            }
        }
        return "enabled";
    }

    private void setPagination() {
        strony = new Pagination(5, fasadaUser.listaUzytkownikow());
        listaU = strony.generateDataArray();
    }

    public void redirectTest() {
        try {
            if (!zalogowany) {
                ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
                ec.redirect(ec.getRequestContextPath() + "/");
            }
        } catch (IOException e) {
        }
    }

    public void refresh() {
        int cPage = strony.getPage();
        strony.setCollection((ArrayList<UserDTO>) fasadaUser.listaUzytkownikow());
        listaU = strony.generateDataArray();
        if (cPage > strony.getPagesCount()) {
            strony.setPage(cPage - 1 < 1 ? 1 : cPage - 1);
            listaU = strony.previousPage();
        } else {
            strony.setPage(cPage);
            listaU = strony.generateDataArray();
        }
    }

    public Boolean doRenderowania(int pos) {
        if (strony == null) {
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
                String code = strony.getControlArrayItem(pos - 2);
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
            return strony.getControlArrayItem(pos);
        } else {
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

    public boolean czyRenderowacTabele() {
        if (strony == null) {
            return false;
        }
        return (listaU == null ? false : !listaU.isEmpty());
    }

    public DataModel getListaU() {

        if (fasadaUser != null) {
            if (listaU == null) {
                setPagination();
            } else if (listaU.isEmpty()) {
                setPagination();
            }
        } else {
            return null;
        }

        return new ListDataModel(listaU);
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
                if ((uDTO.getRmask() & Def.LVL9) > 0) {
                    return true;
                }
                break;
            case "zaopatrzenie":
                if ((uDTO.getRmask() & Def.LVL4) > 0) {
                    return true;
                }
                break;
        }
        return false;
    }
}
