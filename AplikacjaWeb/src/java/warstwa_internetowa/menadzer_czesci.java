/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package warstwa_internetowa;

import DTO.CzesciDTO;
import EE_ejb.FasadaCzesciD_ejbRemote;
import Utils.Pagination;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Darek Xperia
 */
@ManagedBean
@SessionScoped
//@Stateful
//@Named(value = "menadzer_czesci")
public class menadzer_czesci {

    @EJB(mappedName = "ejb/FasadaCzesciD_ejb")
    FasadaCzesciD_ejbRemote fasadaCzesci;

    @PostConstruct
    public void init() {
        setPagination();
    }
    
    private List<CzesciDTO> lista;
    private Pagination<CzesciDTO> strony;

    private String nazwa, producent, model, jednostka, cena_jednostkowa;

    public List<CzesciDTO> getLista() {
        return lista;
    }

    public void setLista(List<CzesciDTO> lista) {
        this.lista = lista;
    }

    public Pagination<CzesciDTO> getStrony() {
        return strony;
    }

    public void setStrony(Pagination<CzesciDTO> strony) {
        this.strony = strony;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getProducent() {
        return producent;
    }

    public void setProducent(String producent) {
        this.producent = producent;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getJednostka() {
        return jednostka;
    }

    public void setJednostka(String jednostka) {
        this.jednostka = jednostka;
    }

    public String getCena_jednostkowa() {
        return cena_jednostkowa;
    }

    public void setCena_jednostkowa(String cena_jednostkowa) {
        this.cena_jednostkowa = cena_jednostkowa;
    }

    public boolean czyRenderowacTabele() {
        if (strony == null) {
            return false;
        }
        return (lista == null ? false : !lista.isEmpty());
    }

    public void refresh() {
        int cPage = strony.getPage();
        strony.setCollection((ArrayList<CzesciDTO>) fasadaCzesci.listaCzesci());
        lista = strony.generateDataArray();
        if (cPage > strony.getPagesCount()) {
            strony.setPage(cPage - 1 < 1 ? 1 : cPage - 1);
            lista = strony.previousPage();
        } else {
            strony.setPage(cPage);
            lista = strony.generateDataArray();
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
        if (strony != null) {
            lista = strony.getPageNo(pageNo);
        }
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
        strony = new Pagination(5, fasadaCzesci.listaCzesci());
        lista = strony.generateDataArray();
    }
}
