/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package warstwa_internetowa;

import DTO.CzesciDTO;
import DTO.MagazynDTO;
import EE_ejb.FasadaMagazynD_ejbRemote;
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
public class menadzer_magazynu {

    @EJB(mappedName = "ejb/FasadaMagazynD_ejb")
    FasadaMagazynD_ejbRemote fasadaMagazynu;

    @PostConstruct
    public void init() {
        setPagination();
    }
    
    private List<MagazynDTO> lista;
    private Pagination<MagazynDTO> strony;

    private CzesciDTO czesc;
    private Integer regal, polka, ilosc;
    
    // zmienne dodatkowe
    private String nazwa, model, producent, jednostka;
    private Double cena_jednostkowa, wartosc;

    public List<MagazynDTO> getLista() {
        return lista;
    }

    public void setLista(List<MagazynDTO> lista) {
        this.lista = lista;
    }

    public Pagination<MagazynDTO> getStrony() {
        return strony;
    }

    public void setStrony(Pagination<MagazynDTO> strony) {
        this.strony = strony;
    }

    public CzesciDTO getCzesc() {
        return czesc;
    }

    public void setCzesc(CzesciDTO czesc) {
        this.czesc = czesc;
    }

    public Integer getRegal() {
        return regal;
    }

    public void setRegal(Integer regal) {
        this.regal = regal;
    }

    public Integer getPolka() {
        return polka;
    }

    public void setPolka(Integer polka) {
        this.polka = polka;
    }

    public Integer getIlosc() {
        return ilosc;
    }

    public void setIlosc(Integer ilosc) {
        this.ilosc = ilosc;
    }

    public String getNazwa() {
        return czesc.getNazwa();
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getModel() {
        return czesc.getModel();
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getProducent() {
        return czesc.getProducent();
    }

    public void setProducent(String producent) {
        this.producent = producent;
    }

    public String getJednostka() {
        return czesc.getJednostka();
    }

    public void setJednostka(String jednostka) {
        this.jednostka = jednostka;
    }

    public Double getCena_jednostkowa() {
        return czesc.getCena_jednostkowa();
    }

    public void setCena_jednostkowa(Double cena_jednostkowa) {
        this.cena_jednostkowa = cena_jednostkowa;
    }

    public Double getWartosc() {
        return ilosc * czesc.getCena_jednostkowa();
    }

    public void setWartosc(Double wartosc) {
        this.wartosc = wartosc;
    }

    public boolean czyRenderowacTabele() {
        if (strony == null) {
            return false;
        }
        return (lista == null ? false : !lista.isEmpty());
    }

    public void refresh() {
        int cPage = strony.getPage();
        strony.setCollection((ArrayList<MagazynDTO>) fasadaMagazynu.listaCzesci());
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
        strony = new Pagination(5, fasadaMagazynu.listaCzesci());
        lista = strony.generateDataArray();
    }
}
