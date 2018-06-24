/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package warstwa_internetowa;

import DTO.CzesciDTO;
import EE_ejb.FasadaCzesciD_ejbRemote;
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
public class menadzer_czesci implements Serializable {

    @EJB(mappedName = "ejb/FasadaCzesciD_ejb")
    FasadaCzesciD_ejbRemote fasadaCzesci;

    @PostConstruct
    public void init() {
        setPagination();
    }

    private List<CzesciDTO> lista;
    private Pagination<CzesciDTO> strony;

    private CzesciDTO toEdit;

    private String nazwa, producent, model, jednostka;
    private Double cena_jednostkowa;
    private String nazwa_s;

    public FasadaCzesciD_ejbRemote getFasadaCzesci() {
        return fasadaCzesci;
    }

    public CzesciDTO getToEdit() {
        return toEdit;
    }

    public void setToEdit(CzesciDTO toEdit) {
        this.toEdit = toEdit;
    }

    public String edytuj(CzesciDTO item) {
        toEdit = item;
        return "/resources/zaopatrzenie/edytuj_czesc";
    }

    public String usun(CzesciDTO item) {
        try {
            fasadaCzesci.usunPozycje(item);
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Definicja części została usunięta.", "Definicja części została usunięta.");
            FacesContext.getCurrentInstance().addMessage(null, facesMsg);
        } catch (Exception e) {
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błąd w czasie usuwania definicji części.", "Błąd w czasie usuwania definicji części.");
            FacesContext.getCurrentInstance().addMessage(null, facesMsg);
        }
        int cPage = strony.getPage();
        setPagination();
        strony.setPage(cPage);
        lista = strony.generateDataArray();
        return "/resources/zaopatrzenie/lista_czesci";
    }

    public String aktualizuj() {
        try {
            fasadaCzesci.aktualizujDane(toEdit);
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Definicja części została zaktualizowana.", "Definicja części została zaktualizowana.");
            FacesContext.getCurrentInstance().addMessage(null, facesMsg);
        } catch (Exception e) {
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błąd aktualizacji danych definicji części.", "Błąd aktualizacji danych definicji części");
            FacesContext.getCurrentInstance().addMessage(null, facesMsg);
        }
        return "/resources/zaopatrzenie/lista_czesci";
    }

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

    public String getNazwa_s() {
        return nazwa_s;
    }

    public void setNazwa_s(String nazwa_s) {
        this.nazwa_s = nazwa_s;
    }

    public String filtruj() {
        List<CzesciDTO> aktualnaLista = strony.getCollection();
        List<CzesciDTO> filtrowanaLista = new ArrayList<>();
        for (CzesciDTO mD : aktualnaLista) {
            if (mD.getNazwa().contains(nazwa_s)) {
                filtrowanaLista.add(mD);
            }
        }
        strony.setF_collection(filtrowanaLista);
        strony.refresh(5);
        lista = strony.generateDataArray();
        return "/resources/zaopatrzenie/lista_czesci";
    }

    public String resetuj() {
        setPagination();
        nazwa_s = "";
        return "/resources/zaopatrzenie/lista_czesci";
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

    public Double getCena_jednostkowa() {
        return cena_jednostkowa;
    }

    public void setCena_jednostkowa(Double cena_jednostkowa) {
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

    public String dodaj() {
        try {
            fasadaCzesci.dodajCzesc(new CzesciDTO(fasadaCzesci.znajdzNastepneID(), nazwa, producent, model, jednostka, cena_jednostkowa));
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Definicja części została dodana.", "Definicja części została dodana.");
            FacesContext.getCurrentInstance().addMessage(null, facesMsg);
        } catch (Exception e) {
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błąd w czasie dodawani definicji części.", "Błąd w czasie dodawania definicji części.");
            FacesContext.getCurrentInstance().addMessage(null, facesMsg);
        }
        int cPage = strony.getPage();
        setPagination();
        strony.setPage(cPage);
        lista = strony.generateDataArray();
        return "/resources/zaopatrzenie/lista_czesci";
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
