/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package warstwa_internetowa;

import DTO.CzesciDTO;
import DTO.MagazynDTO;
import EE_ejb.FasadaCzesciD_ejbRemote;
import EE_ejb.FasadaMagazynD_ejbRemote;
import Utils.Pagination;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Darek Xperia
 */
@ManagedBean
@SessionScoped
//@Stateful
//@Named(value = "menadzer_czesci")
public class menadzer_magazynu implements Serializable {

    @EJB(mappedName = "ejb/FasadaMagazynD_ejb")
    FasadaMagazynD_ejbRemote fasadaMagazynu;

    @EJB(mappedName = "ejb/FasadaCzesciD_ejb")
    FasadaCzesciD_ejbRemote fasadaCzesci;

    @PostConstruct
    public void init() {
        setPagination();
    }

    private List<MagazynDTO> lista;
    private Pagination<MagazynDTO> strony;

    private Long id;
    private CzesciDTO czesc;
    private Integer regal, polka, ilosc;
    private MagazynDTO toEdit;

    public List<MagazynDTO> getLista() {
        return lista;
    }

    public MagazynDTO getToEdit() {
        return toEdit;
    }

    public void setToEdit(MagazynDTO toEdit) {
        this.toEdit = toEdit;
    }

    public String dodaj() {
        try {
            fasadaMagazynu.dodajPozycje(new MagazynDTO(fasadaMagazynu.znajdzNastepneID(), czesc, regal, polka, ilosc));
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Część została dodana.", "Część została dodana.");
            FacesContext.getCurrentInstance().addMessage(null, facesMsg);
        } catch (Exception e) {
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błąd w czasie dodawania części.", "Błąd w czasie dodawania części.");
            FacesContext.getCurrentInstance().addMessage(null, facesMsg);
        }
        int cPage = strony.getPage();
        setPagination();
        strony.setPage(cPage);
        lista = strony.generateDataArray();
        return "/resources/magazyn/zawartosc_magazynu";
    }
        
    public String aktualizuj() {
        try {
            fasadaMagazynu.aktualizujDane(toEdit);
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Część została zaktualizowana.", "Część została zaktualizowana.");
            FacesContext.getCurrentInstance().addMessage(null, facesMsg);
        } catch (Exception e) {
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błąd aktualizacji danych części.", "Błąd aktualizacji danych części");
            FacesContext.getCurrentInstance().addMessage(null, facesMsg);
        }
        return "/resources/magazyn/zawartosc_magazynu";
    }

    public FasadaCzesciD_ejbRemote getFasadaCzesci() {
        return fasadaCzesci;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Double getWartosc(Double cena_jednostkowa, Double ilosc) {
        return ilosc * cena_jednostkowa;
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

    public String czescDTOcolor() {
        return czesc != null ? "green" : "red";
    }

    public String edytuj(MagazynDTO item) {
        toEdit = item;
        return "/resources/magazyn/edycja_czesci";
    }

    public String usun(MagazynDTO item) {
        try {
            fasadaMagazynu.usunPozycje(item);
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Część została usunięta z magazynu.", "Część została usunięta z magazynu.");
            FacesContext.getCurrentInstance().addMessage(null, facesMsg);
        } catch (Exception e) {
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błąd w czasie usuwania części.", "Błąd w czasie usuwania części.");
            FacesContext.getCurrentInstance().addMessage(null, facesMsg);
        }
        int cPage = strony.getPage();
        setPagination();
        strony.setPage(cPage);
        lista = strony.generateDataArray();
        return "zawartosc_magazynu";
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
